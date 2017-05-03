package com.mcx.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcx.model.User;
import com.mcx.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/toLogin")
	public String toLogin(){
		return  "/login";
	}
	
	
	@RequestMapping("/userLogin")
	public String login(String username,String password,String role){
		User user = userService.login(username, password);
		String url = "/login";
		if(user != null){
			if(role.equals("admin")){
				url = "/admin/index";
			}
			if(role.equals("dormManager")){
				url = "/dormManager/index";
			}
			if(role.equals("student")){
				url = "/student/index";
			}
		}
		return url;
	}
	
	

	@RequestMapping("/getAllUser")
	public String getAllUser(HttpServletRequest request){
		
		request.setAttribute("userList", userService.getAllUser());
		
		return "/index";
	}
	
	@RequestMapping("/getUser")
	public String getUser(int id,HttpServletRequest request){
		
		request.setAttribute("user", userService.getUser(id));
	
		return "/editUser";
	}
	
	@RequestMapping("/toAddUser")
	public String toAddUser(){
		return "/addUser";
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user,HttpServletRequest request){
		
		userService.addUser(user);
		
		return "redirect:/user/getAllUser";
	}
	
	@RequestMapping("/delUser")
	public void delUser(String id,HttpServletResponse response){
		
		String result = "{\"result\":\"error\"}";
		
		if(userService.delUser(id)){
			result = "{\"result\":\"success\"}";
		}
		
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(User user,HttpServletRequest request){
		
		if(userService.updateUser(user)){
			user = userService.getUser(user.getUserId());
			request.setAttribute("user", user);
			return "redirect:/user/getAllUser";
		}else{
			return "/error";
		}
	}
}