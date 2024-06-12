package com.propertyrent.model;

public class Comments {
	private int user_id;
	private int comment_id;
	private String comment_section;
	private int property_id;
	public Comments(){
		
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment_section() {
		return comment_section;
	}
	public void setComment_section(String comment_section) {
		this.comment_section = comment_section;
	}
	public int getProperty_id() {
		return property_id;
	}
	public void setProperty_id(int property_id) {
		this.property_id = property_id;
	}
	@Override
	public String toString() {
		return "Comments [user_id=" + user_id + ", comment_id=" + comment_id + ", comment_section=" + comment_section
				+ ", property_id=" + property_id + "]";
	}
	

}
