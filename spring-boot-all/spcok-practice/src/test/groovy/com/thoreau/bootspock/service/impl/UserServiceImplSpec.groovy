package com.thoreau.bootspock.service.impl

import com.thoreau.bootspock.dao.UserDao
import com.thoreau.bootspock.entity.GithubRepo
import com.thoreau.bootspock.entity.UserDO
import com.thoreau.bootspock.service.GithubClient
import spock.lang.*

/**
 * 2019/3/28 11:38 PM.
 * @author zhaozhou
 */
@Narrative('''
    1. def userDao = Mock(UserDO); UserDO userDao = Mock()
    2. userDao.select(_,"name");_匹配任意输入，如果方法前面不同，也支持。不要求mockito那样要么都使用匹配符要么都确定值
    3. 注意mock >> 希望值，不能出现两次。 如果前面出现，后续再用 1 * userDao.select(_,"name")，相当于重新Mock一次。
    4. >>> 表示每次调用返回不同结果
''')
@Title("测试用户服务")
class UserServiceImplSpec extends Specification {

    // @Subject 标准测试类，清晰直观(此处没有具体作用)
    @Subject
    UserServiceImpl userService = new UserServiceImpl()


    @Unroll
    def "Get user by #userName , return null"() {

        given: "generate a user"
        def userDO = new UserDO(id: 1L, nick: "ThoreauZZ")

        and: "mock"
        GithubClient client = Mock(GithubClient) {
            githubRepos("ThoreauZZ") >> [new GithubRepo(name: "java")]
            githubRepos(_) >> null // 任何参数
        }
        UserDao userDao = Mock(UserDao) {
            selectUserByNick("ThoreauZZ") >> userDO
        }
        // 没有setter方法，强行注入，虽然能做到，但不建议
        userService.githubClient = client
        userService.userDao = userDao


        expect:
        userService.getUserByName(userName) == null


        where:
        userName << [null, "", "xx"]
    }


    def "Get a exist user"() {
        given: "generate a user"
        def userDO = new UserDO(id: 1L, nick: "ThoreauZZ")

        and: "mock"
        GithubClient client = Mock(GithubClient);
        UserDao userDao = Mock(UserDao) {
            selectUserByNick("ThoreauZZ") >> userDO
        }
        // 没有setter方法，强行注入，虽然能做到，但不建议
        userService.githubClient = client
        userService.userDao = userDao


        when:
        def userVO = userService.getUserByName("ThoreauZZ")


        then:
        userVO.id == 1
        userVO.repos.size() > 0

        and: "verify"
        1 * client.githubRepos("ThoreauZZ") >> [new GithubRepo(id: 1, name: "java")]
        // 表示没有其他client的调用，如果以后加入其他方法，马上报错，有这个好处。
        0 * client._
    }
    def "mock user >>>"() {
        given: "generate a user"
        def userDO = new UserDO(id: 1L, nick: "ThoreauZZ")

        and: "mock"
        GithubClient client = Mock(GithubClient);
        client.githubRepos(_) >>> [[new GithubRepo(id: 1, name: "java")],null]
        UserDao userDao = Mock(UserDao) {
            selectUserByNick(_) >> userDO
        }
        // 没有setter方法，强行注入，虽然能做到，但不建议
        userService.githubClient = client
        userService.userDao = userDao


        when:
        def userVO1 = userService.getUserByName("ThoreauZZ")
        def userVO2 = userService.getUserByName("orther")


        then:
        userVO1.id == 1
        userVO1.repos.size() > 0
        userVO2.repos == null
    }
}
