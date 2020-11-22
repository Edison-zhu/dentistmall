package com.hsk.xframe.api.dto.model;

/**
 * 系统运行参数持久对象
 * 
 * @author x_nam
 * @version v1.0 2015-03-22
 * 
 */
public class SystemInfo {
	/**
	 * 上传文件存储地址
	 */
	private String updownFilePath;
	/**
	 * 配置文件存储地址
	 */
	private String xmlFilePath;
	/**
	 * 数据库类型
	 */
	private String dbType;
	
	
	public String getUpdownFilePath() {
		return updownFilePath;
	}
	public void setUpdownFilePath(String updownFilePath) {
		this.updownFilePath = updownFilePath;
	}
	public String getXmlFilePath() {
		return xmlFilePath;
	}
	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
}
