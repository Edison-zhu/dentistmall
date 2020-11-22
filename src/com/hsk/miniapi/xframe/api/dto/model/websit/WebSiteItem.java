package com.hsk.miniapi.xframe.api.dto.model.websit;

import com.hsk.xframe.api.dto.model.websit.Content;

import java.util.List;

public class WebSiteItem {
	
	private Integer mszId;
	private Integer mwiId;
	private Integer mxxMszId;
	private String columnIconFileCode;
	
	private String columnIconPath;
	
	private String templateName;
	private String columnName;
	private String state;
	
	private String columnscode;
	private Integer serialNumber;
	
	private List<com.hsk.xframe.api.dto.model.websit.WebSiteItem> children;
	
	private List<Content> contents;

	public WebSiteItem() {
	}




	public WebSiteItem(Integer mszId, Integer mwiId, Integer mxxMszId,
			String columnIconFileCode, String columnName, String columnscode) {
		super();
		this.mszId = mszId;
		this.mwiId = mwiId;
		this.mxxMszId = mxxMszId;
		this.columnIconFileCode = columnIconFileCode;
		this.columnName = columnName;
		this.columnscode = columnscode;
	}






	public Integer getMszId() {
		return mszId;
	}
	public void setMszId(Integer mszId) {
		this.mszId = mszId;
	}
	public Integer getMwiId() {
		return mwiId;
	}
	public void setMwiId(Integer mwiId) {
		this.mwiId = mwiId;
	}
	public Integer getMxxMszId() {
		return mxxMszId;
	}
	public void setMxxMszId(Integer mxxMszId) {
		this.mxxMszId = mxxMszId;
	}
	public String getColumnIconFileCode() {
		return columnIconFileCode;
	}
	public void setColumnIconFileCode(String columnIconFileCode) {
		this.columnIconFileCode = columnIconFileCode;
	}
	public String getColumnIconPath() {
		return columnIconPath;
	}




	public void setColumnIconPath(String columnIconPath) {
		this.columnIconPath = columnIconPath;
	}




	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getColumnscode() {
		return columnscode;
	}
	public void setColumnscode(String columnscode) {
		this.columnscode = columnscode;
	}
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}




	public List<com.hsk.xframe.api.dto.model.websit.WebSiteItem> getChildren() {
		return children;
	}




	public void setChildren(List<com.hsk.xframe.api.dto.model.websit.WebSiteItem> children) {
		this.children = children;
	}




	public List<Content> getContents() {
		return contents;
	}




	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
	
	
	
	
	
	

}
