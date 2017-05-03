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
 * @Description: 记录内容实体类
 *
 */
@Entity
@Table(name = "record")
public class Record implements Serializable {

	private static final long serialVersionUID = -3578232278449329517L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordId;// 记录ID
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User student;// 对应学生
	@Column
	private String date;// 记录时间
	@Column
	private String detail;// 记录详情
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User manager;// 对应宿管

}
