package com.hsk.dentistmall.web.shopping.model;

import java.util.List;

import com.hsk.xframe.api.persistence.SysWebsiteComment;

public class MdCommentModel {
	
	private Integer swcId;
	private String columnName;
	private String linkUrl;//链接地址
	private Integer mszType;
	private List childList;
	private Integer isMore;
	private List<SysWebsiteComment> childMapList;
	private List<SysWebsiteComment> childWordList;
	
	public Integer getSwcId() {
		return swcId;
	}
	public void setSwcId(Integer swcId) {
		this.swcId = swcId;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Integer getMszType() {
		return mszType;
	}
	public void setMszType(Integer mszType) {
		this.mszType = mszType;
	}
	public List getChildList() {
		return childList;
	}
	public void setChildList(List childList) {
		this.childList = childList;
	}
	public Integer getIsMore() {
		return isMore;
	}
	public void setIsMore(Integer isMore) {
		this.isMore = isMore;
	}
	public List<SysWebsiteComment> getChildMapList() {
		return childMapList;
	}
	public void setChildMapList(List<SysWebsiteComment> childMapList) {
		this.childMapList = childMapList;
	}
	public List<SysWebsiteComment> getChildWordList() {
		return childWordList;
	}
	public void setChildWordList(List<SysWebsiteComment> childWordList) {
		this.childWordList = childWordList;
	}
}
