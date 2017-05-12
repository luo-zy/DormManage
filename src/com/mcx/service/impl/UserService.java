package com.mcx.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
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
	public User getUser(Integer userId) {
		return userDao.get(User.class, userId);
	}

	@Override
	public List<User> findByCriteria(DetachedCriteria dc) {
		return userDao.findByCriteria(dc);
	}

	@Override
	public String save(User user) {
		String message = "";
		try {
			userDao.save(user);
			message = "保存成功";
		} catch (Exception e) {
			message = "保存失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String update(User user) {
		String message = "";
		try {
			userDao.update(user);
			message = "修改成功";
		} catch (Exception e) {
			message = "修改失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String delete(Integer userId) {
		User user = getUser(userId);
		String message = "";
		try {
			if (user != null) {
				userDao.delete(user);
				message = "删除成功";
			}
		} catch (Exception e) {
			message = "删除失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public User findEntityByCriteria(DetachedCriteria dc) {
		return userDao.findEntityByCriteria(dc);
	}

}
