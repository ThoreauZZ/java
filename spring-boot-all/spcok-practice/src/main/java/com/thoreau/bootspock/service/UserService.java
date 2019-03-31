package com.thoreau.bootspock.service;

import com.thoreau.bootspock.resource.vo.UserVO;

/**
 * 2019/3/28 11:20 PM.
 *
 * @author zhaozhou
 */
public interface UserService {
    UserVO getUserByName(String name);

    void createUser(UserVO userVO);
}
