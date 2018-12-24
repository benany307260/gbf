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
	
	public <T> boolean set(String key, T value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			
			String value = valueToString(value);
			
			jedis.set(key, value);
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
	
	private <T> String valueToString(T value) {
		// TODO Auto-generated method stub
		return null;
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
