package com.mcx.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 用户ID
	@Column
	private String username;// 用户名
	@Column
	private String password;// 密码
	@Column
	private String name;// 姓名
	@Column
	private String sex;// 性别
	@Column
	private Integer age;// 年龄
	@Column
	private String address;// 家庭住址
	@Column
	private String tel;// 电话
	@Column
	private String role;// 用户角色

	// --------------学生信息
	@Column
	private String stuNumber;// 学号
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,	CascadeType.MERGE })
	private Dorm dorm;// 学生对应宿舍

	// --------------宿舍管理员信息
	@Column
	private String manNumber;// 工号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}

	public Dorm getDorm() {
		return dorm;
	}

	public void setDorm(Dorm dorm) {
		this.dorm = dorm;
	}

	public String getManNumber() {
		return manNumber;
	}

	public void setManNumber(String manNumber) {
		this.manNumber = manNumber;
	}

}
