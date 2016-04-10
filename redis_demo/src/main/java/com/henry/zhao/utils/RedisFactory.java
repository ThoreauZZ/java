package com.henry.zhao.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * jedisCluster 单例工厂
 * 
 * @author henry.zhao
 *
 */
public class RedisFactory implements FactoryBean<JedisCluster> ,InitializingBean{
	// redis集群实例
	private JedisCluster jedisCluster;

	// redis集群地址，
	public String redisServers;

	// redis超时时间
	private int redisTimeout = 2000;

	// redis最大重试次数
	private int maxRedirections = 5;

	// 连接池配置
	public GenericObjectPoolConfig poolConfig;
	
	// 匹配集群地址格式
	private Pattern pattern = Pattern.compile("^.+[:]\\d{1,5}\\s*$");
	
	

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	public void setRedisServers(String redisServers) {
		this.redisServers = redisServers;
	}

	public void setRedisTimeout(int redisTimeout) {
		this.redisTimeout = redisTimeout;
	}

	public void setMaxRedirections(int maxRedirections) {
		this.maxRedirections = maxRedirections;
	}

	public void setPoolConfig(GenericObjectPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Set<HostAndPort> jedisClusterNodes = getClusterNodes();		
		// 创建集群实例
		jedisCluster = new JedisCluster(jedisClusterNodes, redisTimeout, maxRedirections, poolConfig);
	}
	
	/**
	 * 获取集群实例
	 * @return
	 * @throws Exception
	 */
	@Override
	public JedisCluster getObject() throws Exception {
		return this.jedisCluster;
	}

	/**
	 * 获取实例类型
	 * @return
	 */
	@Override
	public Class<?> getObjectType() {
		return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);
	}
	
	/**
	 * 是否为单列
	 * @return
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}
	
	/**
	 * 获取集群节点列表 
	 * @return
	 */
	private Set<HostAndPort> getClusterNodes() {
		if (redisServers == null || redisServers.length() == 0) {
			throw new IllegalArgumentException("redisServers不能为空！");
		}
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		String[] addressArray = redisServers.split(",");
		for (String address : addressArray) {
			boolean isIpPort = pattern.matcher(address).matches();
			if (!isIpPort) {
				throw new IllegalArgumentException("ip或 port不合法！");
			}
			String[] ipAndPort = address.split(":");
			HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
			jedisClusterNodes.add(hap);
		}	
		return jedisClusterNodes;
	}
}
