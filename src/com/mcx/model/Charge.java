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
 * 缴费管理
 *
 */
@Entity
@Table(name = "charge")
public class Charge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 记录ID
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User student;// 关联学生
	@Column
	private String item;// 缴费项目
	@Column
	private Double amount;// 缴费金额
	@Column
	private String time;// 缴费时间
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User manager;// 关联宿管

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

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

}
