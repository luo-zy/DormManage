package com.mcx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @Description: 宿舍信息实体类
 *
 */
@Entity
@Table(name = "dorm")
public class Dorm implements Serializable {
	private static final long serialVersionUID = 4730668496392266323L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dormId;// 宿舍ID
	@Column
	private String dormName;// 宿舍名称
	@Column
	private String detail;// 宿舍介绍

	public int getDormId() {
		return dormId;
	}

	public void setDormId(int dormId) {
		this.dormId = dormId;
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
