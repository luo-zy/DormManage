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
 * 出入管理
 *
 */
@Entity
@Table(name = "inAndOut")
public class InAndOut {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 记录ID
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User student;// 关联学生
	@Column
	private String leaveTime;// 离开时间
	@Column
	private String returnTime;// 返回时间
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User manager;// 对应宿管

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

}
