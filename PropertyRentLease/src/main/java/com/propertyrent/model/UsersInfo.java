package com.propertyrent.model;

public class UsersInfo {
	int id;
	String username;
	String password;
	String email;
	String phonenumber;
	boolean paymentStatus;
	@Override
	public String toString() {
		return "UsersInfo [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", phonenumber=" + phonenumber + ", paymentStatus=" + paymentStatus + "]";
	}
	public boolean isPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public UsersInfo(String username, String password, String email, String phonenumber) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phonenumber = phonenumber;
	}
	public UsersInfo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public UsersInfo() {
		
	}
	
	


}
