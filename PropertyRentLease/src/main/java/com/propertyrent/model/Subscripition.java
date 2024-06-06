package com.propertyrent.model;

public class Subscripition {
  private int ownerid;
  private boolean payment_status;
public int getOwnerid() {
	return ownerid;
}
public void setOwnerid(int ownerid) {
	this.ownerid = ownerid;
}
public Subscripition(int ownerid,boolean payment_status) {
	super();
	this.ownerid = ownerid;
	
	this.payment_status = payment_status;
}
public Subscripition(boolean payment_status) {
	super();
	
	this.payment_status = payment_status;
}

public Subscripition(int ownerid) {
	super();
	this.ownerid = ownerid;
}
public boolean isPayment_status() {
	return payment_status;
}
public void setPayment_status(boolean payment_status) {
	this.payment_status = payment_status;
}

}
