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
@Table(name = "dormBuild")
public class DormBuild implements Serializable {
	private static final long serialVersionUID = 4730668496392266323L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dormBuildId;// 宿舍ID
	@Column
	private String dormBuildName;// 宿舍名称
	@Column
	private String detail;// 宿舍介绍

	public int getDormBuildId() {
		return dormBuildId;
	}

	public void setDormBuildId(int dormBuildId) {
		this.dormBuildId = dormBuildId;
	}

	public String getDormBuildName() {
		return dormBuildName;
	}

	public void setDormBuildName(String dormBuildName) {
		this.dormBuildName = dormBuildName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
