package com.henry.zhao.service.ipml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.henry.zhao.domain.User;
import com.henry.zhao.service.UserService;
@Service("UserService")
public class UserServiceImpl implements UserService{
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public void addUser(User user) {
		ValueOperations<String, User> valueops = redisTemplate.opsForValue();
		valueops.set(user.getId(), user);
	}

	@Override
	public User getUser(String key) {
		ValueOperations<String, User> valueops = redisTemplate.opsForValue();
		return valueops.get(key);
	}
	@Override
	public void addFile(String key,byte[] bytes){
		ValueOperations<String, byte[]> valueops = redisTemplate.opsForValue();
		valueops.set(key, bytes);
	}
	@Override
	public byte[] getFile(String key){
		ValueOperations<String, byte[]> valueops = redisTemplate.opsForValue();
		return valueops.get(key);
	}
	

}
