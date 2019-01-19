package com.bentest.gbf.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JedisPool jedisPool;
	
	
	public <T> T get(KeyPre keyPre, String key, Class<T> clazz) {
		
		try {
			String redisKey = keyPre.getKeyPre() + key;
			
			String jsonStrValue = get(redisKey);
			
			return stringToBean(jsonStrValue, clazz);
			
		} catch (Exception e) {
			logger.error("redis，获取，异常。", e);
			return null;
		}
	}
	
	public Boolean exists(KeyPre keyPre, String key) {
		
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			
			String redisKey = keyPre.getKeyPre() + key;
			
			return jedis.exists(redisKey);
			
		} catch (Exception e) {
			logger.error("redis，存在，异常。", e);
			return false;
		}
		finally
		{
			closeRedisConn(jedis);
		}
	}
	
	public Long incr(KeyPre keyPre, String key) {
		
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			
			String redisKey = keyPre.getKeyPre() + key;
			
			return jedis.incr(redisKey);
			
		} catch (Exception e) {
			logger.error("redis，自增，异常。", e);
			return null;
		}
		finally
		{
			closeRedisConn(jedis);
		}
	}
	
	public Long decr(KeyPre keyPre, String key) {
		
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			
			String redisKey = keyPre.getKeyPre() + key;
			
			return jedis.decr(redisKey);
			
		} catch (Exception e) {
			logger.error("redis，自减，异常。", e);
			return null;
		}
		finally
		{
			closeRedisConn(jedis);
		}
	}
	
	public Long expire(KeyPre keyPre, String key, int seconds) {
		
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			
			String redisKey = keyPre.getKeyPre() + key;
			
			return jedis.expire(redisKey, seconds);
			
		} catch (Exception e) {
			logger.error("redis，设置失效时间，异常。", e);
			return null;
		}
		finally
		{
			closeRedisConn(jedis);
		}
	}
	
	private <T> T stringToBean(String jsonStrValue, Class<T> clazz) {
		if(jsonStrValue == null || jsonStrValue.length() < 1 || clazz == null) {
			return null;
		}
		
		// 基础类型则直接返回，不需要转json
		if(clazz == String.class) {
			return (T)String.valueOf(jsonStrValue);
		}else if(clazz == int.class || clazz == Integer.class) {
			return (T)Integer.valueOf(jsonStrValue);
		}else if(clazz == long.class || clazz == Long.class) {
			return (T)Long.valueOf(jsonStrValue);
		}else if(clazz == float.class || clazz == Float.class) {
			return (T)Float.valueOf(jsonStrValue);
		}else if(clazz == double.class || clazz == Double.class) {
			return (T)Double.valueOf(jsonStrValue);
		}else {
			return JSON.parseObject(jsonStrValue, clazz);
		}
	}

	private String get(String key) {
		
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
	
	public <T> boolean set(KeyPre keyPre, String key, T value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			
			String valueStr = valueToString(value);
			
			String redisKey = keyPre.getKeyPre() + key;
			
			int expireTimeSeconds = keyPre.getExpireTime();
			//永不过期
			if(expireTimeSeconds <= 0) {
				jedis.set(redisKey, valueStr);
			}else {
				jedis.setex(redisKey, expireTimeSeconds, valueStr);
			}
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
		if(value.getClass() == String.class) {
			return String.valueOf(value);
		}else if(value.getClass() == int.class || value.getClass() == Integer.class) {
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


}
