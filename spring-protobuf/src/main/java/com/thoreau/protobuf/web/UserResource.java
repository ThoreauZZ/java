package com.thoreau.protobuf.web;

import com.thoreau.protobuf.generated.vo.User;
import com.thoreau.protobuf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2018/3/23 13:15.
 *
 * @author zhaozhou
 */
@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(produces = "application/x-protobuf")
    public User getPersonProto() {
        return userRepository.getUser();
    }
}
