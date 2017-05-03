package com.mcx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcx.dao.UserDao;
import com.mcx.model.User;
import com.mcx.service.IUserService;
@Service
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User login(String username,String password){
		return userDao.login(username, password);
	}

	@Override
	public User getUser(int id) {
		return userDao.getUser(id);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public boolean delUser(String id) {

		return userDao.delUser(id);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

}
