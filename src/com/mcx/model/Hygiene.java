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
 * @Description: 公寓卫生
 *
 */
@Entity
@Table(name = "hygiene")
public class Hygiene {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 纪律ID
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Dorm dorm;// 对应宿舍
	@Column
	private String score;// 卫生分数
	@Column
	private String detail;// 卫生详情
	@Column
	private String time;// 检查时间
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User manager;// 对应宿管

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Dorm getDorm() {
		return dorm;
	}

	public void setDorm(Dorm dorm) {
		this.dorm = dorm;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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
