package com.thoreau.bootspock.resource;

import com.thoreau.bootspock.resource.vo.UserVO;
import com.thoreau.bootspock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2019/3/29 7:45 PM.
 *
 * @author zhaozhou
 */
@RestController
@RequestMapping("/user/user")
public class UserResource {
    @Autowired
    private UserService userService;

    @GetMapping
    public UserVO doGet(String name) {
        return userService.getUserByName(name);
    }

    @PostMapping
    public void doPost(UserVO userDO) {
        userService.createUser(userDO);
    }
}
