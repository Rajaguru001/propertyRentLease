package com.propertyrent.model;

public class Request {
	private int user_id;
	private int rent_id;
	private int request_id;
	private boolean approval;
	@Override
	public String toString() {
		return "Request [user_id=" + user_id + ", rent_id=" + rent_id + ", request_id=" + request_id + ", approval="
				+ approval + "]";
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRent_id() {
		return rent_id;
	}
	public void setRent_id(int rent_id) {
		this.rent_id = rent_id;
	}
	public int getRequest_id() {
		return request_id;
	}
	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}
	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approval) {
		this.approval = approval;
	}
	

}
