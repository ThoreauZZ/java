package com.thoreau.springboot.sample.controller;

import com.thoreau.springboot.sample.domain.Account;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 17/7/15 下午10:16.
 *
 * @author zhaozhou
 */
public class AccountController {

    @RequestMapping(method = RequestMethod.GET, value = "/account/{id}")
    public Account getAccount(@PathVariable String id) {
        return new Account(id, "friends", "thoreau@gmail.com");
    }
}
