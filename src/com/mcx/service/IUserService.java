package com.mcx.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mcx.model.User;

public interface IUserService {

	public User getUser(Integer userId);
	
	public User findEntityByCriteria(DetachedCriteria dc);
	
	public List<User> findByCriteria(DetachedCriteria dc);
	
	public String save(User user);
	
	public String update(User user);
	
	public String delete(Integer userId);
	
}
