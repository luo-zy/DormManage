package com.mcx.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mcx.model.User;
@Repository
public class UserDao{

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public User login(String username,String password) {
		
		String hql = "from User u where u.username=? and u.password=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, username);
		query.setString(1, password);
		return (User)query.uniqueResult();
	}
	
	public User getUser(int id) {
		
		String hql = "from User u where u.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setLong(0, id);
		
		return (User)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUser() {
		
		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public boolean delUser(String id) {
		
		String hql = "delete User u where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, id);
		
		return (query.executeUpdate() > 0);
	}

	public boolean updateUser(User user) {
		
		String hql = "update User u set u.userName = ?,u.age=? where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, user.getUsername());
		query.setString(1, user.getAge());
		query.setLong(2, user.getUserId());
		
		return (query.executeUpdate() > 0);
	}

}
