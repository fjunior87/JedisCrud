package com.xicojunior.jediscrud.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import com.xicojunior.jediscrud.model.Keys;
import com.xicojunior.jediscrud.model.User;
import com.xicojunior.jediscrud.util.BeanUtil;

/**
 * 
 * @author Usuario
 *
 */

public class UserDAO {
	
	
	@Inject Jedis jedis;
	/**
	 * Method that adds the user to the system.
	 * For that it handles some redis keys
	 * <ul>
	 * <li>user:ids - Used to incr the user ids</li>
	 * <li>user:<id>:data - Redis Hash that will store the user data</li>
	 * <li>user:all - Redis list with all user ids</li>
	 * </ul>
	 * 
	 * 
	 * To avoid too many network connections it is used Redis Pipeline.
	 * @param user
	 */
	public User addUser(User user){
		
		long userId = jedis.incr(Keys.USER_IDS.key());
		user.setId(userId);
		
		//Getting the Pipeline
		Pipeline pipeline = jedis.pipelined();
		//add to users list
		pipeline.lpush(Keys.USER_ALL.key(), String.valueOf(userId));
		//add to the hash
		
		pipeline.hmset(Keys.USER_DATA.formated(String.valueOf(userId)), BeanUtil.toMap(user));
		
		pipeline.sync();
		
		return user;
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public User getUser(long userId){
		
		String userInfoKey = Keys.USER_DATA.formated(String.valueOf(userId));
		Map<String, String> properties = jedis.hgetAll(userInfoKey);
		return BeanUtil.populate(properties, new User());
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public boolean remove(long userId){
		String userInfoKey = Keys.USER_DATA.formated(String.valueOf(userId));
		Pipeline pipeline = jedis.pipelined();
		Response<Long> responseDel = pipeline.del(userInfoKey);
		Response<Long> responseLrem = pipeline.lrem(Keys.USER_ALL.key(), 0, String.valueOf(userId));
		pipeline.sync();
		return responseDel.get() > 0 && responseLrem.get() > 0;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public User update(User user){
		String userInfoKey = Keys.USER_DATA.formated(String.valueOf(user.getId()));
		jedis.hmset(userInfoKey,BeanUtil.toMap(user));
		return user;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<User> list(){
		List<User> users = new ArrayList<User>();
		//Get all user ids from the redis list using LRANGE
		List<String> allUserIds = jedis.lrange(Keys.USER_ALL.key(), 0, -1);
		if(allUserIds != null && !allUserIds.isEmpty()){
			List<Response<Map<String,String>>> responseList = new ArrayList<Response<Map<String,String>>>();
			
			Pipeline pipeline = jedis.pipelined();
			for(String userId : allUserIds){
				//call HGETALL for each user id
				responseList.add(pipeline.hgetAll(Keys.USER_DATA.formated(userId)));
			}
			pipeline.sync();
			//iterate over the pipelined results
			for(Response<Map<String, String>> properties : responseList){
				users.add(BeanUtil.populate(properties.get(), new User()));
			}
		}
		return users;
	}
	
}
