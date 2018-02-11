package qiye.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//用户
@Entity
@Table(name="t_User")
public class User {

	@Id
	@GeneratedValue
	private int id;
	
	private String username;//用户名
	
	private String password;//密码
	
	private int role;//1表示管理员
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	

	

	
	
	
	
}
