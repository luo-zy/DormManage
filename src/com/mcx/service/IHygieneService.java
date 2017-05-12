package com.mcx.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mcx.model.Hygiene;

public interface IHygieneService {

	public Hygiene gethygiene(Integer hygieneId);
	
	public Hygiene findEntityByCriteria(DetachedCriteria dc);

	public List<Hygiene> findByCriteria(DetachedCriteria detachedCriteria);

	public String save(Hygiene hygiene);

	public String update(Hygiene hygiene);

	public String delete(Integer hygieneId);
}
