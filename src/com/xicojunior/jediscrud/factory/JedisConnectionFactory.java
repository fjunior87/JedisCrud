package com.xicojunior.jediscrud.factory;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 * @author junior ribeiro
 *
 */

public class JedisConnectionFactory {

	@Inject
	private JedisPool jedisPool;
	
	@Produces 
	public Jedis getJedis(){
		return jedisPool.getResource();
	}
	
	public void returnResource(@Disposes Jedis jedis){
		System.out.println("Returning jedis Connection");
		jedisPool.returnResource(jedis);
	}
	
	
}
