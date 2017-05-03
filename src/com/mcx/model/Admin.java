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
 * @Description: 系统管路员信息实体类
 *
 */
@Entity
@Table(name = "admin")
public class Admin implements Serializable {
	private static final long serialVersionUID = 4087827388936649387L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;// 管路员ID
	@Column
	private String userName;// 用户名
	@Column
	private String password;// 密码
	@Column
	private String name;// 姓名
	@Column
	private String sex;// 性别
	@Column
	private String tel;// 电话

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
