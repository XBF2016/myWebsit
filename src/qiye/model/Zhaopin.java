package qiye.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//招聘
@Entity
@Table(name="t_Zhaopin")
public class Zhaopin {

	@Id
	@GeneratedValue
	private int id;
	
	private String zhiwei;//职位
	
	@Column(name="jieshao", columnDefinition="TEXT")
	private String jieshao;//职位介绍
	
	private String createtime;//添加时间
	
	private String tuijian;//推荐

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZhiwei() {
		return zhiwei;
	}

	public void setZhiwei(String zhiwei) {
		this.zhiwei = zhiwei;
	}

	public String getJieshao() {
		return jieshao;
	}

	public void setJieshao(String jieshao) {
		this.jieshao = jieshao;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getTuijian() {
		return tuijian;
	}

	public void setTuijian(String tuijian) {
		this.tuijian = tuijian;
	}

	
	
	
	
	
}
