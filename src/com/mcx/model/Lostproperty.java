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
 * @Description: 失物管理
 *
 */
@Entity
@Table(name = "lostproperty")
public class Lostproperty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 失物ID
	@Column
	private String itemName;// 物品名称
	@Column
	private String itemDetail;// 物品详情
	@Column
	private String pickupTime;// 捡到时间
	@Column
	private String claimSituation;// 认领情况
	@Column
	private String claimTime;// 认领时间
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User student;// 对应学生(认领人)
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User manager;// 对应宿管(记录人)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDetail() {
		return itemDetail;
	}

	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	public String getClaimSituation() {
		return claimSituation;
	}

	public void setClaimSituation(String claimSituation) {
		this.claimSituation = claimSituation;
	}

	public String getClaimTime() {
		return claimTime;
	}

	public void setClaimTime(String claimTime) {
		this.claimTime = claimTime;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

}
