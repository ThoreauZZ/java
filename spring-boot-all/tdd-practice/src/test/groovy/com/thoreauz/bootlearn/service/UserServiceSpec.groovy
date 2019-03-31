package com.thoreauz.bootlearn.service

import com.thoreauz.bootlearn.service.impl.UserServiceImpl
import com.thoreauz.bootlearn.vo.UserDO
import spock.lang.Specification
import spock.lang.Subject

/**
 * 2019/3/24 10:47 AM.
 * @author zhaozhou
 */
class UserServiceSpec extends Specification {
    @Subject
    UserService userService

    UserMongo userMongo = Mock()

    def setup() {
//        userService = new UserServiceImpl(userMongo);
        userService = new UserServiceImpl();

        // 没有setter方法也动态加入
        userService.userMongo = userMongo
    }

    def "GetUserByName"() {
        given:
        1 * userMongo.getUserByName('thoreau') >> new UserDO("1", "thoreau", 22)

        when:
        def userVO = userService.getUserByName('thoreau')

        then:
        userVO.name == 'thoreau'

    }

    def "GetUserByName with null name"() {

        when:
        userService.getUserByName(null)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "name is empty"
    }

    def "GetUserByName with not exist name"() {

        given:
        1 * userMongo.getUserByName('henry') >> null

        when:
        userService.getUserByName('henry')


        then:
        def e = thrown(IllegalArgumentException)
        e.message == "user henry not exist"
    }

    def "private method test"() {
        given:
        def userDO = new UserDO("1", "thoreau", 22)

        when:
        def userVO = userService.converToVO(userDO)

        then:
        assert userVO.id == '1'
        assert userVO == userDO
    }
}
