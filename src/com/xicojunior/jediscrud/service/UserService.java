package com.xicojunior.jediscrud.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.xicojunior.jediscrud.dao.UserDAO;
import com.xicojunior.jediscrud.model.User;

@RequestScoped
public class UserService {

	@Inject
	UserDAO userDAO;
	
	public User addUser(User user){
		return userDAO.addUser(user);
	}
	
	public User getUser(long userId){
		return userDAO.getUser(userId);
	}
	
	public boolean delete(long userId){
		return userDAO.remove(userId);
	}
	
	public User update(User user){
		return userDAO.update(user);
	}
	
	public List<User> list(){
		return userDAO.list();
	}
	
}
