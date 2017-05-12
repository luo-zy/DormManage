package com.mcx.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.mcx.model.Floor;

public interface IFloorService {

	public Floor getFloor(Integer floorId);
	
	public Floor findEntityByCriteria(DetachedCriteria dc);
	
	public List<Floor> findByCriteria(DetachedCriteria detachedCriteria);
	
	public String save(Floor floor);
	
	public String update(Floor floor);
	
	public String delete(Integer floorId);
}
