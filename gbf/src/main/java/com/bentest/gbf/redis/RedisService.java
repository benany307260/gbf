package com.bentest.gbf.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisService {

	@Autowired
	JedisPool jedisPool;
	
	@Autowired
	RedisConfig redisConfig;
	
	public <T> T get(String key, Class<T> clazz) {
		
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			closeRedisConn(jedis);
		}
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
