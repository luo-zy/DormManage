package com.mcx.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcx.dao.FloorDao;
import com.mcx.model.Floor;
import com.mcx.service.IFloorService;
@Service
public class FloorService implements IFloorService {
	
	@Autowired
	private FloorDao floorDao;

	@Override
	public Floor getFloor(Integer floorId) {
		return floorDao.get(Floor.class, floorId);
	}

	@Override
	public List<Floor> findByCriteria(DetachedCriteria detachedCriteria) {
		return floorDao.findByCriteria(detachedCriteria);
	}

	@Override
	public String save(Floor floor) {
		String message = "";
		try {
			floorDao.save(floor);
			message = "保存成功";
		} catch (Exception e) {
			message = "保存失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String update(Floor floor) {
		String message = "";
		try {
			floorDao.update(floor);
			message = "修改成功";
		} catch (Exception e) {
			message = "修改失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public String delete(Integer floorId) {
		Floor floor = getFloor(floorId);
		String message = "";
		try {
			if(floor != null){
				floorDao.delete(floor);
				message = "删除成功";
			}
		} catch (Exception e) {
			message = "删除失败";
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public Floor findEntityByCriteria(DetachedCriteria dc) {
		return floorDao.findEntityByCriteria(dc);
	}

}
