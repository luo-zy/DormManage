package com.mcx.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @Description: 楼栋实体类
 *
 */
@Entity
@Table(name = "floor")
public class Floor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 楼栋ID
	@Column
	private String floorName;// 楼栋名称
	@Column
	private String detail;// 楼栋介绍
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User manager;// 关联用户

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

}
