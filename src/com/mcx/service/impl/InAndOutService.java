package com.mcx.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcx.dao.InAndOutDao;
import com.mcx.model.InAndOut;
import com.mcx.service.IInAndOutService;

@Service
public class InAndOutService implements IInAndOutService {

	@Autowired
	private InAndOutDao inAndOutDao;

	@Override
	public InAndOut getInAndOut(Integer InAndOutId) {
		return inAndOutDao.get(InAndOut.class, InAndOutId);
	}

	@Override
	public List<InAndOut> findByCriteria(DetachedCriteria detachedCriteria) {
		return inAndOutDao.findByCriteria(detachedCriteria);
	}

	@Override
	public String save(InAndOut InAndOut) {
		String message = "";
		try {
			inAndOutDao.save(InAndOut);
			message = "保存成功";
		} catch (Exception e) {
			message = "保存失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String update(InAndOut InAndOut) {
		String message = "";
		try {
			inAndOutDao.update(InAndOut);
			message = "修改成功";
		} catch (Exception e) {
			message = "修改失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String delete(Integer InAndOutId) {
		InAndOut InAndOut = getInAndOut(InAndOutId);
		String message = "";
		try {
			if (InAndOut != null) {
				inAndOutDao.delete(InAndOut);
				message = "删除成功";
			}
		} catch (Exception e) {
			message = "删除失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public InAndOut findEntityByCriteria(DetachedCriteria dc) {
		return inAndOutDao.findEntityByCriteria(dc);
	}

}
