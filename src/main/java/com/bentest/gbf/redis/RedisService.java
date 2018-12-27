package com.bentest.gbf.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	JedisPool jedisPool;
	
	@Autowired
	RedisConfig redisConfig;
	
	public <T> T get(String key, Class<T> clazz) {
		
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			
			String jsonStrValue = jedis.get(key);
			
			return JSON.parseObject(jsonStrValue, clazz);
			
		} catch (Exception e) {
			logger.error("redis，获取，异常。", e);
			return null;
		}
		finally
		{
			closeRedisConn(jedis);
		}
	}
	
	public String get(String key) {
		
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			
			return jedis.get(key);
			
		} catch (Exception e) {
			logger.error("redis，获取StringValue，异常。", e);
			return null;
		}
		finally
		{
			closeRedisConn(jedis);
		}
	}
	
	public <T> boolean set(String key, T value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			
			String valueStr = valueToString(value);
			
			jedis.set(key, valueStr);
			
			return true;
			
		} catch (Exception e) {
			logger.error("redis，设置，异常。", e);
			return false;
		}
		finally
		{
			closeRedisConn(jedis);
		}
	}
	
	private <T> String valueToString(T value) {
		if(value == null) {
			return null;
		}
		
		// 基础类型则直接返回，不需要转json
		if(value.getClass() == int.class || value.getClass() == Integer.class) {
			return String.valueOf(value);
		}else if(value.getClass() == long.class || value.getClass() == Long.class) {
			return String.valueOf(value);
		}else if(value.getClass() == float.class || value.getClass() == Float.class) {
			return String.valueOf(value);
		}else if(value.getClass() == double.class || value.getClass() == Double.class) {
			return String.valueOf(value);
		}
		
		return JSON.toJSONString(value);
	}

	private void closeRedisConn(Jedis jedis) {
		if(jedis != null)
		{
			jedis.close();
		}
		
	}

	@Bean
	public JedisPool JedisPoolFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
		poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
		poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait()*1000);
		
		JedisPool jedisPool = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(), 
				redisConfig.getTimeout() * 1000, redisConfig.getPassword());
		return jedisPool;
	}
}
