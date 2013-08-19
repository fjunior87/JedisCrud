package com.xicojunior.jediscrud.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Generated;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xicojunior.jediscrud.dao.UserDAO;
import com.xicojunior.jediscrud.model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject UserDAO userDao;
	
    /**
     * Default constructor. 
     */
    public UserController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User("Junior", "Ribeiro", "fjunior@gmail.com", "M", 0);
		userDao.addUser(user);
		
		PrintWriter writer = response.getWriter();  
		writer.print("New User Id:" + user.getId());
		
		user = new User("Junior1", "Ribeiro2", "fjunior2@gmail.com", "M", 0);
		userDao.addUser(user);
		
		writer.print("New User Id2:" + user.getId());
		writer.print("Hello World" + userDao);
		
		List<User> users = userDao.list();
		for(User u : users){
			writer.print(  u.getId() + " " + u.getEmail() + "<br/>");
		}
		
		userDao.remove(2);
		
		users = userDao.list();
		for(User u : users){
			writer.print(  u.getId() + " " + u.getEmail() + "<br/>");
		}
		
		user = userDao.getUser(3);
		user.setEmail("emial3");
		userDao.update(user);
		
		users = userDao.list();
		for(User u : users){
			writer.print(  u.getId() + " " + u.getEmail() + "<br/>");
		}
		
		writer.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
