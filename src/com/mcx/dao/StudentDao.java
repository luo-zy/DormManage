package com.mcx.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mcx.model.Student;

@Repository("studentDao")
public class StudentDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Student> getUserList() {
		String hql = "from Student";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

}
