package com.hsk.miniapi.dentistmall.web.outOrder.model;


public class ExportOrderInfoApi {
	
	private String mooCode;
	private String matName;
	private String mmfName;
	private String supplierName;
	private String groupName; 
	private Double number1; 
	private String orderTime; 
	private String userName;
	public String getMooCode() {
		return mooCode;
	}
	public void setMooCode(String mooCode) {
		this.mooCode = mooCode;
	}
	public String getMatName() {
		return matName;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public String getMmfName() {
		return mmfName;
	}
	public void setMmfName(String mmfName) {
		this.mmfName = mmfName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Double getNumber1() {
		return number1;
	}
	public void setNumber1(Double number1) {
		this.number1 = number1;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
