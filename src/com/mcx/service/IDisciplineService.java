package com.mcx.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mcx.model.Discipline;

public interface IDisciplineService {

	public Discipline getDiscipline(Integer disciplineId);
	
	public Discipline findEntityByCriteria(DetachedCriteria dc);

	public List<Discipline> findByCriteria(DetachedCriteria detachedCriteria);

	public String save(Discipline discipline);

	public String update(Discipline discipline);

	public String delete(Integer disciplineId);
}
