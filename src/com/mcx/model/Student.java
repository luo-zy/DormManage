package com.mcx.model;

import java.io.Serializable;

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
 * @Description: 学生信息实体类
 *
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {
	private static final long serialVersionUID = 6638052173327030292L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;// 学生ID
	@Column
	private String stuNumber;// 学号
	@Column
	private String name;// 学生姓名
	@Column
	private String sex;// 性别
	@Column
	private String tel;// 电话
	@Column
	private String userName;// 用户名
	@Column
	private String password;// 密码
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private DormBuild dormBuildId;// 宿舍ID

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
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

	public DormBuild getDormBuildId() {
		return dormBuildId;
	}

	public void setDormBuildId(DormBuild dormBuildId) {
		this.dormBuildId = dormBuildId;
	}

}
