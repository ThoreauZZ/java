package com.thoreau.bootspock.resource

import com.thoreau.bootspock.resource.vo.UserVO
import com.thoreau.bootspock.service.UserService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

/**
 * 2019/3/31 4:47 PM.
 * @author zhaozhou
 */
@AutoConfigureMockMvc
@WebMvcTest
class UserResourceSpec extends Specification {

    @Autowired
    private MockMvc mvc

    @SpringBean
    private UserService userService = Mock()


    def "rest: get /user"() {

        given:
        1 * userService.getUserByName("zz") >> new UserVO(id: 1, name: "zz")

        expect:
        mvc.perform(get("/user/user?name=zz"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.name").value("zz"))
                .andExpect(jsonPath("\$.id").value("1"));
    }
}
