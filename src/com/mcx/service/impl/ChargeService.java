package com.mcx.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcx.dao.ChargeDao;
import com.mcx.model.Charge;
import com.mcx.service.IChargeService;

@Service
public class ChargeService implements IChargeService {

	@Autowired
	private ChargeDao chargeDao;

	@Override
	public Charge getCharge(Integer chargeId) {
		return chargeDao.get(Charge.class, chargeId);
	}

	@Override
	public Charge findEntityByCriteria(DetachedCriteria dc) {
		return chargeDao.findEntityByCriteria(dc);
	}
	
	@Override
	public List<Charge> findByCriteria(DetachedCriteria detachedCriteria) {
		return chargeDao.findByCriteria(detachedCriteria);
	}

	@Override
	public String save(Charge charge) {
		String message = "";
		try {
			chargeDao.save(charge);
			message = "保存成功";
		} catch (Exception e) {
			message = "保存失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String update(Charge charge) {
		String message = "";
		try {
			chargeDao.update(charge);
			message = "修改成功";
		} catch (Exception e) {
			message = "修改失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String delete(Integer chargeId) {
		Charge charge = getCharge(chargeId);
		String message = "";
		try {
			if (charge != null) {
				chargeDao.delete(charge);
				message = "删除成功";
			}
		} catch (Exception e) {
			message = "删除失败";
			e.printStackTrace();
		}
		return message;
	}

}
