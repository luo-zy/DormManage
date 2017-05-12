package com.mcx.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mcx.model.Lostproperty;

public interface ILostpropertyService {

	public Lostproperty getLostproperty(Integer lostpropertyId);
	
	public Lostproperty findEntityByCriteria(DetachedCriteria dc);
	
	public List<Lostproperty> findByCriteria(DetachedCriteria detachedCriteria);
	
	public String save(Lostproperty lostproperty);
	
	public String update(Lostproperty lostproperty);
	
	public String delete(Integer lostpropertyId);
}
