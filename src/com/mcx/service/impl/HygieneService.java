package com.mcx.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcx.dao.HygieneDao;
import com.mcx.model.Hygiene;
import com.mcx.service.IHygieneService;

@Service
public class HygieneService implements IHygieneService {

	@Autowired
	private HygieneDao hygieneDao;

	@Override
	public Hygiene gethygiene(Integer hygieneId) {
		return hygieneDao.get(Hygiene.class, hygieneId);
	}

	@Override
	public List<Hygiene> findByCriteria(DetachedCriteria detachedCriteria) {
		return hygieneDao.findByCriteria(detachedCriteria);
	}

	@Override
	public String save(Hygiene hygiene) {
		String message = "";
		try {
			hygieneDao.save(hygiene);
			message = "保存成功";
		} catch (Exception e) {
			message = "保存失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String update(Hygiene hygiene) {
		String message = "";
		try {
			hygieneDao.update(hygiene);
			message = "修改成功";
		} catch (Exception e) {
			message = "修改失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String delete(Integer hygieneId) {
		Hygiene hygiene = gethygiene(hygieneId);
		String message = "";
		try {
			if (hygiene != null) {
				hygieneDao.delete(hygiene);
				message = "删除成功";
			}
		} catch (Exception e) {
			message = "删除失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public Hygiene findEntityByCriteria(DetachedCriteria dc) {
		return hygieneDao.findEntityByCriteria(dc);
	}

}
