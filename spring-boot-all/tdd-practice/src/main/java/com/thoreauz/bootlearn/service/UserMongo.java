package com.thoreauz.bootlearn.service;

import com.thoreauz.bootlearn.vo.UserDO;

/**
 * 2019/3/24 1:25 AM.
 *
 * @author zhaozhou
 */
public interface UserMongo {
    UserDO getUserByName(String name);
}
