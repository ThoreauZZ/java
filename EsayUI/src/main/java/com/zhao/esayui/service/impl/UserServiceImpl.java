package com.zhao.esayui.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;



import org.springframework.stereotype.Service;

import com.zhao.esayui.dao.UserDao;
import com.zhao.esayui.domain.ResultEntity;
import com.zhao.esayui.domain.User;
import com.zhao.esayui.domain.page.UserPage;
import com.zhao.esayui.service.UserService;
import com.zhao.esayui.util.MessageUtil;
/**
 * 
 * <p></p>
 * @author henry.zhao
 * @version v1.0 2015年11月12日 下午2:07:30
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;

	public User getUserByName(String name) {
		return userDao.getUserByName(name);
	}

	@Override
	public ResultEntity checkLogin(User user) {
		ResultEntity rs = new ResultEntity();
		User u = userDao.getUserByName(user.getUsername());
		if(u==null){
			rs.setStatus("2");
			rs.setMsg("此用户不存在");
			return rs;
		}
		if(user.getPassword().equals(u.getPassword())){
			rs.setStatus("0");
			rs.setMsg("登陆成功");
			return rs;
		}else{
			rs.setStatus("1");
			rs.setMsg("密码错误");
			return rs;
		}
	}

	@Override
	public ResultEntity regist(User user) {
		ResultEntity rs = new ResultEntity();
		User u = userDao.getUserByName(user.getUsername());
		if(u != null){
			rs.setStatus("1");
			rs.setMsg("此用户名被占用");
			return rs;
		}
		//生成主键
		String uuid = MessageUtil.getUUID();
		user.setId(uuid);
		//md5加密密码
		String md5_password = MessageUtil.md5(user.getPassword());
		user.setPassword(md5_password);
		userDao.saveUser(user);
		
		rs.setStatus("0");
		rs.setMsg("注册成功！");
		return rs;
	}

	@Override
	public Map<String, Object> getUserPages(UserPage userPage) {
		Map<String, Object> m =new HashMap<String, Object>();
		int total =userDao.getUserTotalRows();
		List<User> users= userDao.getUserPages(userPage);
		m.put("total", total);
		m.put("rows", users);
		return m;
	}

	@Override
	public ResultEntity updateUser(User user) {
		ResultEntity rs = new ResultEntity();
		int i = userDao.update("sql_updateUser", user);
		if(i>0){
			rs.setStatus("0");
			rs.setMsg("更新成功");
			return rs;
		}
		
		rs.setStatus("1");
		rs.setMsg("更新失败，请重试！");
		return rs;
		
	}
	
}
