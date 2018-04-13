package com.myWebsit.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//新闻
@Entity
@Table(name="t_News")
public class News {

	@Id
	@GeneratedValue
	private int id;
	
	private String title;//标题
	
	@Column(name="content", columnDefinition="TEXT")
	private String content;//内容
	
	private String created_time;//添加时间
	
	private String is_recommend;//推荐

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
