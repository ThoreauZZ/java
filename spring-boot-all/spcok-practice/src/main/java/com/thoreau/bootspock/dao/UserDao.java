package com.thoreau.bootspock.dao;

import com.thoreau.bootspock.entity.UserDO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2019/3/29 7:41 PM.
 *
 * @author zhaozhou
 */
@Service
public class UserDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public UserDO selectUserById(Long id) {
        return sqlSessionTemplate.selectOne("selectUserById", id);
    }

    public UserDO selectUserByNick(String nick) {
        return sqlSessionTemplate.selectOne("selectUserByNick", nick);
    }

    public void createUser(UserDO userDO) {
        sqlSessionTemplate.insert("insertUser", userDO);
    }
}
