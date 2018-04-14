package com.myWebsit.bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//企业信息
@Entity
@Table(name="t_company")
public class Company {

	@Id
	@GeneratedValue
	private int id;
	
	private String logo;//企业logo
	
	@Column(name="info", columnDefinition="TEXT")
	private String info;//企业简介
	
	private String address;//地址
	
	private String postcode;//邮编
	
	private String contact;//联系人
	
	private String tel;//电话
	
	private String fax;//传真
	
	private String phone;//手机
	
	private String mailbox;//邮箱
	
	private String name;//企业名称

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name =name;
	}

	
}
