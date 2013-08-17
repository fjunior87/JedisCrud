package com.xicojunior.jediscrud.factory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@ApplicationScoped
public class JedisPoolFactory {

	private String host = "localhost";
	private JedisPool jedisPool;
	
	public @Produces JedisPool getJedisPool(){
		jedisPool = new JedisPool(new JedisPoolConfig(), host);
		return jedisPool;
	}
	
	public void detroy(@Disposes JedisPool jedisPool){
		jedisPool.destroy();
	}
}
