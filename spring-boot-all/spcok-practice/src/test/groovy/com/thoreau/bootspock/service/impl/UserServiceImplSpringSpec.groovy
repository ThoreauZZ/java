package com.thoreau.bootspock.service.impl

import com.thoreau.bootspock.dao.UserDao
import com.thoreau.bootspock.entity.UserDO
import com.thoreau.bootspock.service.GithubClient
import com.thoreau.bootspock.service.UserService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

/**
 * 2019/3/28 11:38 PM.
 * @author zhaozhou
 */
@Narrative('''
''')
@Title("测试用户服务-spring service")
@SpringBootTest(classes = UserServiceImpl.class)
class UserServiceImplSpringSpec extends Specification {

    @Autowired
    private UserService userService

    // 自动注入@SpringBean，类似spring-boot+mockito的@MockBean
    @SpringBean
    private UserDao userDao = Mock()

    @SpringBean
    private GithubClient githubClient = Stub(){
        // 可以直接mock返回值
        githubRepos(_) >> null
    }


    def "Get user by #userName"() {
        given: "generate a user"
        def userDO = new UserDO(id: 1L, nick: "ThoreauZZ")

        and: "mock"
        userDao.selectUserByNick(_) >> userDO

        when:
        def userVO = userService.getUserByName("ThoreauZZ")

        then:
        userVO.id == 1
        userVO.repos == null
    }

}
