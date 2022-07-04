package model;

public class Order {
	private int id,accountID;
	private String address;
	private double totalMoney;
	public Order() {
		
	}
	public Order(int id, int accountID, String address, double totalMoney) {
		super();
		this.id = id;
		this.accountID = accountID;
		this.address = address;
		this.totalMoney = totalMoney;
	}
	public Order( int accountID, String address, double totalMoney) {
		
		this.accountID = accountID;
		this.address = address;
		this.totalMoney = totalMoney;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
}
