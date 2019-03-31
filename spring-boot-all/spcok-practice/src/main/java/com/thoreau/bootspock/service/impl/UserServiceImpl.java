package com.thoreau.bootspock.service.impl;

import com.thoreau.bootspock.dao.UserDao;
import com.thoreau.bootspock.entity.GithubRepo;
import com.thoreau.bootspock.entity.UserDO;
import com.thoreau.bootspock.resource.vo.UserVO;
import com.thoreau.bootspock.service.GithubClient;
import com.thoreau.bootspock.service.UserService;
import com.thoreau.bootspock.utils.MyStringUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2019/3/28 11:21 PM.
 *
 * @author zhaozhou
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private GithubClient githubClient;

    @Autowired
    private UserDao userDao;

    @Override
    public UserVO getUserByName(String name) {
        if (MyStringUtils.isEmpty(name)) {
            return null;
        }
        UserDO userDO = userDao.selectUserByNick(name);
        if (userDO == null) {
            return null;
        }
        UserVO userVO = getUserVO(userDO);
        List<GithubRepo> githubRepos = githubClient.githubRepos(name);
        userVO.setRepos(githubRepos);
        return userVO;
    }

    private UserVO getUserVO(UserDO userDO) {
        if (userDO == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        userVO.setId(userDO.getId());
        userVO.setName(userDO.getNick());
        userVO.setGender(userDO.getGender());
        userVO.setCreateTime(userDO.getCreateTime());
        userVO.setUpdateTime(userDO.getUpdateTime());
        return userVO;
    }

    @Override
    public void createUser(UserVO userVO) {

    }
}
