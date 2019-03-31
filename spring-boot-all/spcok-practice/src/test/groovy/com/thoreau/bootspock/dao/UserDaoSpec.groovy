package com.thoreau.bootspock.dao


import com.thoreau.bootspock.entity.UserDO
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import spock.genesis.Gen
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification

/**
 * 2019/3/29 8:16 PM.
 * @author zhaozhou
 */


@Import([UserDao.class])
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserDaoSpec extends Specification {


    @Autowired
    private UserDao userDao

    // @sql放在setupSpec不生效，因为setupSpec 在Spring inject 之前执行的
    @Sql(value = "classpath:init-db.sql")
    def setupSpec() {

    }

    @Shared
    def userGen = Gen.type(UserDO,
            id: Gen.integer(200..10000),
            nick: Gen.string(~/[A-Z][a-z]+([A-Z][a-z]+)?/),
            email: Gen.string(~/[a-zA-Z0-9]{2,10}+\@[a-zA-Z0-9]+\.[A-Za-z]{2,4}/),
            gender: Gen.character('MF'),
            createTime: Gen.date(Date.parse('yyyy-MM-dd', '2018-10-10'), new Date()).millisProvider
    )

    def setup() {

    }

    def "create user"() {
        given: "give a user"
        def user = userGen.iterator().next()

        when:
        userDao.createUser(user)

        then:
        noExceptionThrown()
    }

    def "get user"() {
        given: "give a user"
        def user = userGen.iterator().next()

        when:
        userDao.createUser(user)
        and:
        def userResult = userDao.selectUserById(user.id)

        then:
        with(userResult) {
            nick == user.nick
            id == user.id
        }
        userResult.id > 199
        userResult.id < 10001
        userResult.gender in ['M', 'F'].collect { it as String }

    }

    @Ignore
    def 'user  genesis sample'() {
        expect:
        user instanceof UserDO
        user.gender in ['M', 'F', 'T', 'U'].collect { it as String }
        user.id > 199
        user.id < 10001
        user.createTime >= Date.parse('MM/dd/yyyy', '01/01/1940').getTime()
        user.updateTime <= new Date().getTime()

        where:
        user << Gen.type(UserDO,
                id: Gen.integer(200..10000),
                nick: Gen.string(~/[A-Z][a-z]+( [A-Z][a-z]+)?/),
                createTime: Gen.date(Date.parse('MM/dd/yyyy',
                        '01/01/1940'), new Date()).millisProvider,
                gender: Gen.character('MFTU')
        ).take(3)
    }
}
