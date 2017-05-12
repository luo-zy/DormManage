package com.mcx.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcx.dao.DormDao;
import com.mcx.model.Dorm;
import com.mcx.service.IDormService;
@Service
public class DormService implements IDormService {

	@Autowired
	private DormDao dormDao;
	
	@Override
	public Dorm getDorm(Integer dormId) {
		return dormDao.get(Dorm.class, dormId);
	}

	@Override
	public List<Dorm> findByCriteria(DetachedCriteria detachedCriteria){
		return dormDao.findByCriteria(detachedCriteria);
	}

	@Override
	public String save(Dorm dorm) {
		String message = "";
		try {
			dormDao.save(dorm);
			message = "保存成功";
		} catch (Exception e) {
			message = "保存失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String update(Dorm dorm) {
		String message = "";
		try {
			dormDao.update(dorm);
			message = "修改成功";
		} catch (Exception e) {
			message = "修改失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String delete(Integer dormId) {
		Dorm dorm = getDorm(dormId);
		String message = "";
		try {
			if(dorm != null){
				dormDao.delete(dorm);
				message = "删除成功";
			}
		} catch (Exception e) {
			message = "删除失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public Dorm findEntityByCriteria(DetachedCriteria dc) {
		return dormDao.findEntityByCriteria(dc);
	}

}
