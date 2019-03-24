package com.thoreauz.bootlearn.service.impl;

import com.thoreauz.bootlearn.service.UserMongo;
import com.thoreauz.bootlearn.service.UserService;
import com.thoreauz.bootlearn.utils.StringUtils;
import com.thoreauz.bootlearn.vo.UserDO;
import com.thoreauz.bootlearn.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2019/3/24 1:33 AM.
 *
 * @author zhaozhou
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMongo userMongo;

    public UserServiceImpl(UserMongo userMongo) {
        this.userMongo = userMongo;
    }


    @Override
    public UserVO getUserByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("name is empty");
        }
        UserDO userDO = userMongo.getUserByName(name);
        if (userDO == null) {
            throw new IllegalArgumentException("user " + name + " not exist");
        }
        return new UserVO(userDO.getId(), userDO.getName(), userDO.getAge());
    }
}
