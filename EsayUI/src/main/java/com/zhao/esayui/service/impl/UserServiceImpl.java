package com.zhao.esayui.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhao.esayui.domain.ResultEntity;
import com.zhao.esayui.domain.User;
import com.zhao.esayui.persistence.UserMapper;
import com.zhao.esayui.service.UserService;
import com.zhao.esayui.util.MessageUtil;
/**
 * 用户操作的service层
 * @author henry.zhao
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;

	public User getUserByName(String name) {
		return userMapper.getUserByName(name);
	}

	@Override
	public ResultEntity checkLogin(String username, String password) {
		ResultEntity rs = new ResultEntity();
		
		return null;
	}

	@Override
	public ResultEntity regist(User user) {
		ResultEntity rs = new ResultEntity();
		User usre = userMapper.getUserByName(user.getUsername());
		if(user != null){
			rs.setStatus("1");
			rs.setMsg("此用户已经注册");
			return rs;
		}
		//生成主键
		String uuid = MessageUtil.getUUID();
		user.setId(uuid);
		//密码加密
		String md5_password = MessageUtil.md5(user.getPassword());
		user.setPassword(md5_password);
		userMapper.saveUser(user);//插入信息
		
		rs.setStatus("0");
		rs.setMsg("注册成功");
		return rs;
	}
	
}
