package qiye.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//留言反馈
@Entity
@Table(name="t_Message")
public class Message {

	@Id
	@GeneratedValue
	private int id;
	
	private String xingming;//姓名
	
	private String youjianl;//邮件地址
	
	private String dianhua;//电话
	
	private String chuanzhen;//传真
	
	private String dizhi;//地址
	
	private String youbian;//邮编
	
	@Column(name="neirong", columnDefinition="TEXT")
	private String neirong;//留言内容
	
	private String shijian;//留言时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}

	public String getYoujianl() {
		return youjianl;
	}

	public void setYoujianl(String youjianl) {
		this.youjianl = youjianl;
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

	public String getNeirong() {
		return neirong;
	}

	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}
	
	
	
	
	
}
