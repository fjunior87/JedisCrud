package com.xicojunior.jediscrud.factory;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class JedisPoolFactory {

	private String host = "localhost";
	private JedisPool jedisPool;
	@Singleton
	public @Produces JedisPool getJedisPool(){
		jedisPool = new JedisPool(new JedisPoolConfig(), host);
		System.out.println("Jedis Pool: " + jedisPool);
		return jedisPool;
	}
	
	public void detroy(@Disposes JedisPool jedisPool){
		System.out.println("Destroying :" + jedisPool);
		jedisPool.destroy();
	}
}
