package com.myWebsit.bean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//产品信息
@Entity
@Table(name="t_Product")
public class Product {

	@Id
	@GeneratedValue
	private int id;
	
	private String product_name;//产品名
	
	private String path;//产品图片
	
	private String info;//产品介绍
	
	private String is_recommend;//是否推荐
	
	private String created_time;//添加时间


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getIs_recommend() {
		return is_recommend;
	}

	public void setIs_recommend(String is_recommend) {
		this.is_recommend = is_recommend;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}


	
	
	
	
	
	
	
	
}
