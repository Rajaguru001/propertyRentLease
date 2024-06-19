package com.propertyrent.model;

public class SellerDashBoardRequest {
	private int owner_id;
	private int rent_id;
	private int request_id;
	private boolean approval;
	private int property_id;
	@Override
	public String toString() {
		return "SellerDashBoardRequest [owner_id=" + owner_id + ", rent_id=" + rent_id + ", request_id=" + request_id
				+ ", approval=" + approval + ", property_id=" + property_id + "]";
	}
	public SellerDashBoardRequest (){
		
	}
	public SellerDashBoardRequest(int owner_id, int rent_id, int request_id, boolean approval,int property_id) {
		super();
		this.owner_id = owner_id;
		this.rent_id = rent_id;
		this.request_id = request_id;
		this.approval = approval;
		this.property_id=property_id;
	}

	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
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
	public int getProperty_id() {
		return property_id;
	}
	public void setProperty_id(int property_id) {
		this.property_id = property_id;
	}
	
	

}
