package com.mcx.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcx.dao.LostpropertyDao;
import com.mcx.model.Lostproperty;
import com.mcx.service.ILostpropertyService;
@Service
public class LostpropertyService implements ILostpropertyService {
	
	@Autowired
	private LostpropertyDao lostpropertyDao;

	@Override
	public Lostproperty getLostproperty(Integer lostpropertyId) {
		return lostpropertyDao.get(Lostproperty.class, lostpropertyId);
	}

	@Override
	public List<Lostproperty> findByCriteria(DetachedCriteria detachedCriteria) {
		return lostpropertyDao.findByCriteria(detachedCriteria);
	}

	@Override
	public String save(Lostproperty lostproperty) {
		String message = "";
		try {
			lostpropertyDao.save(lostproperty);
			message = "保存成功";
		} catch (Exception e) {
			message = "保存失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String update(Lostproperty lostproperty) {
		String message = "";
		try {
			lostpropertyDao.update(lostproperty);
			message = "修改成功";
		} catch (Exception e) {
			message = "修改失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String delete(Integer lostpropertyId) {
		Lostproperty lostproperty = getLostproperty(lostpropertyId);
		String message = "";
		try {
			if(lostproperty != null){
				lostpropertyDao.delete(lostproperty);
				message = "删除成功";
			}
		} catch (Exception e) {
			message = "删除失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public Lostproperty findEntityByCriteria(DetachedCriteria dc) {
		return lostpropertyDao.findEntityByCriteria(dc);
	}

}
