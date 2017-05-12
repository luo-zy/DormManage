package com.mcx.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mcx.model.InAndOut;

public interface IInAndOutService {

	public InAndOut getInAndOut(Integer inAndOutId);
	
	public InAndOut findEntityByCriteria(DetachedCriteria dc);

	public List<InAndOut> findByCriteria(DetachedCriteria detachedCriteria);

	public String save(InAndOut inAndOut);

	public String update(InAndOut inAndOut);

	public String delete(Integer inAndOutId);
}
