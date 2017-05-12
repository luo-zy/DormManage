package com.mcx.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mcx.model.Charge;

public interface IChargeService {

	public Charge getCharge(Integer chargeId);
	
	public Charge findEntityByCriteria(DetachedCriteria dc);

	public List<Charge> findByCriteria(DetachedCriteria detachedCriteria);

	public String save(Charge charge);

	public String update(Charge charge);

	public String delete(Integer chargeId);
}
