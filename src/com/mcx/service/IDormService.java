package com.mcx.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mcx.model.Dorm;

public interface IDormService {

	public Dorm getDorm(Integer dormId);
	
	public Dorm findEntityByCriteria(DetachedCriteria dc);
	
	public List<Dorm> findByCriteria(DetachedCriteria detachedCriteria);
	
	public String save(Dorm dorm);
	
	public String update(Dorm dorm);
	
	public String delete(Integer dormId);
}
