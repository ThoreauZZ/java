package com.henry.zhao.service;

import com.henry.zhao.utils.SimpleJedisTemplate;
import com.henry.zhao.utils.SimpleJedisTemplate.RedisCallback;

import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisService {
	private static String addr ="192.168.217.100";
	private static JedisPoolConfig config = new JedisPoolConfig();
	private static JedisPool jedisPool =  null;
	private static SimpleJedisTemplate simpleJedisTemplate=null;
	static{
		config.setMaxTotal(20);
		config.setMaxIdle(2);
		jedisPool = new JedisPool(config,addr,6379);
		simpleJedisTemplate= new SimpleJedisTemplate(jedisPool);
	}
	
	public static void main(String[] args) throws Exception {
		
		String ret = simpleJedisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(JedisCommands commands) {
				 return commands.set("name", "hhh");
			}
		});
		System.out.println(ret);
	}
}
