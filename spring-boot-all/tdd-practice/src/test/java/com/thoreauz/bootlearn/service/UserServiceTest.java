package com.thoreauz.bootlearn.service;


import com.thoreauz.bootlearn.service.impl.UserServiceImpl;
import com.thoreauz.bootlearn.vo.UserDO;
import com.thoreauz.bootlearn.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

/**
 * 2019/3/24 1:27 AM.
 *
 * @author zhaozhou
 */
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserMongo userMongo;

    @Configuration
    @Import(UserServiceImpl.class)
    static class Config {
    }

    @Test
    public void getUserByNameTest() {
        UserDO thoreau = new UserDO("123", "thoreau", 22);
        //执行前打桩
        doReturn(thoreau).when(userMongo).getUserByName(anyString());
        // run
        UserVO userVO = userService.getUserByName("thoreau");
        // asset
        assertThat(userVO.getId()).isEqualTo("123");
        assertThat(userVO).hasFieldOrPropertyWithValue("name", "thoreau");
    }
}