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
 * @Description: 宿舍信息实体类
 *
 */
@Entity
@Table(name = "dorm")
public class Dorm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 宿舍ID
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Floor floor;// 楼栋
	@Column
	private String dormName;// 宿舍名称
	@Column
	private String detail;// 宿舍介绍

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public String getDormName() {
		return dormName;
	}

	public void setDormName(String dormName) {
		this.dormName = dormName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
