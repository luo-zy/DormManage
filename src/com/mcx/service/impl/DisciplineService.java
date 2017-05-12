package com.mcx.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcx.dao.DisciplineDao;
import com.mcx.model.Discipline;
import com.mcx.service.IDisciplineService;

@Service
public class DisciplineService implements IDisciplineService {

	@Autowired
	private DisciplineDao disciplineDao;

	@Override
	public Discipline getDiscipline(Integer disciplineId) {
		return disciplineDao.get(Discipline.class, disciplineId);
	}

	@Override
	public List<Discipline> findByCriteria(DetachedCriteria detachedCriteria) {
		return disciplineDao.findByCriteria(detachedCriteria);
	}

	@Override
	public String save(Discipline discipline) {
		String message = "";
		try {
			disciplineDao.save(discipline);
			message = "保存成功";
		} catch (Exception e) {
			message = "保存失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String update(Discipline discipline) {
		String message = "";
		try {
			disciplineDao.update(discipline);
			message = "修改成功";
		} catch (Exception e) {
			message = "修改失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String delete(Integer disciplineId) {
		Discipline discipline = getDiscipline(disciplineId);
		String message = "";
		try {
			if (discipline != null) {
				disciplineDao.delete(discipline);
				message = "删除成功";
			}
		} catch (Exception e) {
			message = "删除失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public Discipline findEntityByCriteria(DetachedCriteria dc) {
		return disciplineDao.findEntityByCriteria(dc);
	}

}
