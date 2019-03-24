package com.thoreauz.bootlearn.service;

import com.thoreauz.bootlearn.vo.UserVO;

/**
 * 2019/3/24 1:22 AM.
 *
 * @author zhaozhou
 */
public interface UserService {
    UserVO getUserByName(String name);
}
