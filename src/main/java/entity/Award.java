package entity;

public class Award {
	private int id;
	private String prize_name;
	private int prize_amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrize_name() {
		return prize_name;
	}
	public void setPrize_name(String prize_name) {
		this.prize_name = prize_name;
	}
	public int getPrize_amount() {
		return prize_amount;
	}
	public void setPrize_amount(int prize_amount) {
		this.prize_amount = prize_amount;
	}
	
}
