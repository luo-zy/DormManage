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
 * @Description: 公寓纪律
 *
 */
@Entity
@Table(name = "discipline")
public class Discipline {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 纪律ID
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User student;// 对应学生
	@Column
	private String disciplinaryTime;// 违纪时间
	@Column
	private String disciplinaryDetail;// 违纪详情
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

	public String getDisciplinaryTime() {
		return disciplinaryTime;
	}

	public void setDisciplinaryTime(String disciplinaryTime) {
		this.disciplinaryTime = disciplinaryTime;
	}

	public String getDisciplinaryDetail() {
		return disciplinaryDetail;
	}

	public void setDisciplinaryDetail(String disciplinaryDetail) {
		this.disciplinaryDetail = disciplinaryDetail;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

}
