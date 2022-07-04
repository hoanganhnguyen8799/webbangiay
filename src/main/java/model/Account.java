package model;

import dao.AccountDAO;

public class Account {
	private int id;
	private String user;
	private String pass;
	private int idSell;
	private int isAdmin;
	
	public Account() {
		
	}
	public Account(int id, String user, String pass, int idSell, int isAdmin) {
		
		this.id = id;
		this.user = user;
		this.pass = pass;
		this.idSell = idSell;
		this.isAdmin = isAdmin;
	}
	public Account( String user, String pass, int idSell, int isAdmin) {
		
		this.user = user;
		this.pass = pass;
		this.idSell = idSell;
		this.isAdmin = isAdmin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getIdSell() {
		return idSell;
	}
	public void setIdSell(int idSell) {
		this.idSell = idSell;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", user=" + user + ", pass=" + pass + ", idSell=" + idSell + ", isAdmin=" + isAdmin
				+ "]";
	}
	public static void main(String[] args) {
		System.out.println(new AccountDAO().login("admin1", "123"));
	}
	
}
