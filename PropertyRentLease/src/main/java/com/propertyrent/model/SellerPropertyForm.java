package com.propertyrent.model;

import java.io.InputStream;
import java.sql.Date;

import com.mysql.cj.jdbc.Blob;

public class SellerPropertyForm {
	    private int propertyId;
	    private String propertyType;
	    private int sqft;
	    private String furnishing;
	    private Date availableFrom;
	    private int rent;
	    private String address;
	    private Date postedOnDate;
	    private InputStream ebBillStream;
	    private String location;

	    public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public InputStream getEbBillStream() {
	        return ebBillStream;
	    }

	    public void setEbBillStream(InputStream ebBillStream) {
	        this.ebBillStream = ebBillStream;
	    }

	    private int ownerId;
	    private int rentId;
	    private int subscriptionId;
	    private boolean isApproval;
		public int getPropertyId() {
			return propertyId;
		}
		public void setPropertyId(int propertyId) {
			this.propertyId = propertyId;
		}
		public String getPropertyType() {
			return propertyType;
		}
		public void setPropertyType(String propertyType) {
			this.propertyType = propertyType;
		}
		public int getSqft() {
			return sqft;
		}
		public void setSqft(int sqft) {
			this.sqft = sqft;
		}
		public String getFurnishing() {
			return furnishing;
		}
		public void setFurnishing(String furnishing) {
			this.furnishing = furnishing;
		}
		public Date getAvailableFrom() {
			return availableFrom;
		}
		public void setAvailableFrom(Date availableFrom) {
			this.availableFrom = availableFrom;
		}
		public int getRent() {
			return rent;
		}
		public void setRent(int rent) {
			this.rent = rent;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public Date getPostedOnDate() {
			return postedOnDate;
		}
		public void setPostedOnDate(Date postedOnDate) {
			this.postedOnDate = postedOnDate;
		}
	
		public int getOwnerId() {
			return ownerId;
		}
		public void setOwnerId(int ownerId) {
			this.ownerId = ownerId;
		}
		public int getRentId() {
			return rentId;
		}
		public void setRentId(int rentId) {
			this.rentId = rentId;
		}
		public int getSubscriptionId() {
			return subscriptionId;
		}
		public SellerPropertyForm(int ownerid,int propertyId, String propertyType, int sqft, String furnishing, Date availableFrom,
		        int rent, String address, Date postedOnDate,String location, InputStream ebBillStream, int ownerId, int rentId, int subscriptionId,
		        boolean isApproval) {
		    super();
		    this.ownerId=ownerid;
		    this.propertyId = propertyId;
		    this.propertyType = propertyType;
		    this.sqft = sqft;
		    this.furnishing = furnishing;
		    this.availableFrom = availableFrom;
		    this.rent = rent;
		    this.address = address;
		    this.postedOnDate = postedOnDate;
		    this.location=location;
		    this.ebBillStream = ebBillStream;
		    this.ownerId = ownerId;
		    this.rentId = rentId;
		    this.subscriptionId = subscriptionId;
		    this.isApproval = isApproval;
		}

		
		public SellerPropertyForm(String location) {
			super();
			this.location = location;
		}

		public SellerPropertyForm() {
			// TODO Auto-generated constructor stub
		}

		public void setSubscriptionId(int subscriptionId) {
			this.subscriptionId = subscriptionId;
		}
		public boolean isApproval() {
			return isApproval;
		}
		public void setApproval(boolean isApproval) {
			this.isApproval = isApproval;
		}
 	
	

}
