package com.mcx.service;

import java.util.List;

import com.mcx.model.User;

public interface IUserService {
	
	public User login(String username,String password);

	public User getUser(int userId);
	
	public List<User> getAllUser();
	
	public void addUser(User user);
	
	public boolean delUser(String id);
	
	public boolean updateUser(User user);

}
