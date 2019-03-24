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
        userService = new UserServiceImpl(userMongo);
    }

    def "GetUserByName"() {
        given:
        1 * userMongo.getUserByName('thoreau') >> new UserDO("1", "thoreau", 22)

        when:
        def userVO = userService.getUserByName('thoreau')

        then:
        userVO.name == 'thoreau'

    }
}
