package entity;
/**
 * 实体类：
 * 		实体类与表的结构要一致，表有哪些字段，对应的实体类也要有对应的属性匹配(类型要匹配)。
 */
public class User {
	private int id;
	private String name;
	private String password;
	private String email;
	private int prize_id;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", prize_id="
				+ prize_id + "]";
	}
	public int getPrize_id() {
		return prize_id;
	}
	public void setPrize_id(int prize_id) {
		this.prize_id = prize_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
