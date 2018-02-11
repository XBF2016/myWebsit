package qiye.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//企业信息
@Entity
@Table(name="t_Qiye")
public class Qiye {

	@Id
	@GeneratedValue
	private int id;
	
	private String logo;//企业logo
	
	@Column(name="jianjie", columnDefinition="TEXT")
	private String jianjie;//企业简介
	
	private String dizhi;//地址
	
	private String youbian;//邮编
	
	private String lianxiren;//联系人
	
	private String dianhua;//电话
	
	private String chuanzhen;//传真
	
	private String shouji;//手机
	
	private String youxiang;//邮箱
	
	private String mingchen;//企业名称

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

	public String getJianjie() {
		return jianjie;
	}

	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
	}

	public String getDizhi() {
		return dizhi;
	}

	public void setDizhi(String dizhi) {
		this.dizhi = dizhi;
	}

	public String getYoubian() {
		return youbian;
	}

	public void setYoubian(String youbian) {
		this.youbian = youbian;
	}

	public String getLianxiren() {
		return lianxiren;
	}

	public void setLianxiren(String lianxiren) {
		this.lianxiren = lianxiren;
	}

	public String getDianhua() {
		return dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}

	public String getChuanzhen() {
		return chuanzhen;
	}

	public void setChuanzhen(String chuanzhen) {
		this.chuanzhen = chuanzhen;
	}

	public String getShouji() {
		return shouji;
	}

	public void setShouji(String shouji) {
		this.shouji = shouji;
	}

	public String getYouxiang() {
		return youxiang;
	}

	public void setYouxiang(String youxiang) {
		this.youxiang = youxiang;
	}

	public String getMingchen() {
		return mingchen;
	}

	public void setMingchen(String mingchen) {
		this.mingchen = mingchen;
	}
	
	
	
	
	

	
	
	
	
}
