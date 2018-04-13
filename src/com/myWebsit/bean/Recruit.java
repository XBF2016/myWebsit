package com.myWebsit.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//招聘
@Entity
@Table(name="t_Recruit")
public class Recruit {

	@Id
	@GeneratedValue
	private int id;
	
	private String position;//职位
	
	@Column(name="info", columnDefinition="TEXT")
	private String info;//职位介绍
	
	private String created_time;//添加时间
	
	private String is_recommend;//推荐

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getIs_recommend() {
		return is_recommend;
	}

	public void setIs_recommend(String is_recommend) {
		this.is_recommend = is_recommend;
	}

	
	
}
