package com.xicojunior.jediscrud.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.xicojunior.jediscrud.model.User;
import com.xicojunior.jediscrud.service.UserService;
import com.xicojunior.jediscrud.util.BeanUtil;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject UserService userService;
	
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
		
		String action = request.getParameter("action");
		if(action == null || action.equals("") || action.equals("list")){
			dispatch(request, response, list(request));
			return;
		}
		
		if(action.equals("update")){
			dispatch(request, response, update(request));
			return;
		}
		
		if(action.equals("read")){
			long userId = Long.valueOf(request.getParameter("id"));
			dispatch(request, response, read(request, userId));
			return;
		}
		if(action.equals("edit")){
			long userId = Long.valueOf(request.getParameter("id"));
			dispatch(request, response, edit(request, userId));
			return;
		}
		
		if(action.equals("delete")){
			long userId = Long.valueOf(request.getParameter("id"));
			dispatch(request, response, delete(request, userId));
			return;
		}
		
		if(action.equals("create")){
			dispatch(request, response, create(request));
			return;
		}
		
	}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected String list(HttpServletRequest request){
		request.setAttribute("users", userService.list());
		return "index.jsp";
	}
	
	protected String read(HttpServletRequest request, long userId){
		request.setAttribute("user", userService.getUser(userId));
		return "detail.jsp";
	}
	
	protected String edit(HttpServletRequest request, long userId){
		request.setAttribute("user", userService.getUser(userId));
		return "edit.jsp";
	}
	
	protected String update(HttpServletRequest request){
		request.setAttribute("user", userService.update(BeanUtil.populate(request.getParameterMap(), new User())));
		return "detail.jsp";
	}
	
	protected String delete(HttpServletRequest request, long userId){
		userService.delete(userId);
		request.setAttribute("users", userService.list());
		return "index.jsp";
	}
	
	protected String create(HttpServletRequest request){
		request.setAttribute("user", userService.addUser(BeanUtil.populate(request.getParameterMap(), new User())));
		return "detail.jsp";
	}
	
	protected void dispatch(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
	
}
