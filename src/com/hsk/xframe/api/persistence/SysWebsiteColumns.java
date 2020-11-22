package com.hsk.xframe.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

/**
 * MxxzSiteColumns entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_website_columns")
public class SysWebsiteColumns implements java.io.Serializable {

	// Fields

	private Integer swcId;
	private Integer swiId;
	private Integer sysSwcId;
	private String columnIconFileCode;
	private String templateName;
	private String columnName;
	private String state;
	private Integer mszType;
	
	private String columnscode;
	private String serialNumber;
	
	private Integer isMore;
	private Integer showNumber;
	/**
	 * 扩展属性
	 */
	private String columnIconFilePath;
	public void setColumnIconFilePath(String columnIconFilePath) {
		this.columnIconFilePath = columnIconFilePath;
	}

	private String columnPictureFilecode01;
	private String columnPictureFilecode02;
	private String columnPictureFilecode03;
	@Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code= Column_icon_fileCode)")
	public String getColumnIconFilePath() {
		return columnIconFilePath;
	}
	@Column(name = "linkUrl", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	private String linkUrl;//链接地址

	// Constructors

	/** default constructor */
	public SysWebsiteColumns() {
	}

	/** minimal constructor */
	public SysWebsiteColumns(Integer swcId) {
		this.swcId = swcId;
	}

	/** full constructor */
	public SysWebsiteColumns(Integer swcId, Integer swiId, Integer sysSwcId,
			String columnIconFileCode, String templateName, String columnName,
			String state) {
		this.swcId = swcId;
		this.swiId = swiId;
		this.sysSwcId = sysSwcId;
		this.columnIconFileCode = columnIconFileCode;
		this.templateName = templateName;
		this.columnName = columnName;
		this.state = state;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "swc_id", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getSwcId() {
		return this.swcId;
	}

	public void setSwcId(Integer swcId) {
		this.swcId = swcId;
	}

	@Column(name = "swi_id", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getSwiId() {
		return this.swiId;
	}

	public void setSwiId(Integer swiId) {
		this.swiId = swiId;
	}

	@Column(name = "sys_swc_id", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getSysSwcId() {
		return this.sysSwcId;
	}

	public void setSysSwcId(Integer sysSwcId) {
		this.sysSwcId = sysSwcId;
	}

	@Column(name = "Column_icon_fileCode", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getColumnIconFileCode() {
		return this.columnIconFileCode;
	}

	public void setColumnIconFileCode(String columnIconFileCode) {
		this.columnIconFileCode = columnIconFileCode;
	}

	@Column(name = "template_name", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Column(name = "column_name", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getColumnName() {
		return this.columnName;
	}
	
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Column(name = "state", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	@Column(name = "columnsCode", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getColumnscode() {
		return columnscode;
	}

	public void setColumnscode(String columnscode) {
		this.columnscode = columnscode;
	}

	@Column(name = "column_picture_filecode01", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getColumnPictureFilecode01() {
		return columnPictureFilecode01;
	}

	public void setColumnPictureFilecode01(String columnPictureFilecode01) {
		this.columnPictureFilecode01 = columnPictureFilecode01;
	}
	@Column(name = "column_picture_filecode02", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getColumnPictureFilecode02() {
		return columnPictureFilecode02;
	}

	public void setColumnPictureFilecode02(String columnPictureFilecode02) {
		this.columnPictureFilecode02 = columnPictureFilecode02;
	}
	@Column(name = "column_picture_filecode03", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getColumnPictureFilecode03() {
		return columnPictureFilecode03;
	}

	public void setColumnPictureFilecode03(String columnPictureFilecode03) {
		this.columnPictureFilecode03 = columnPictureFilecode03;
	}
	@Column(name = "serial_number", unique = false, nullable = true, insertable = true, updatable = true)
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	@Column(name = "msz_type")
	public Integer getMszType() {
		return mszType;
	}
	public void setMszType(Integer mszType) {
		this.mszType = mszType;
	}
	@Column(name = "is_more")
	public Integer getIsMore() {
		return isMore;
	}
	public void setIsMore(Integer isMore) {
		this.isMore = isMore;
	}
	@Column(name = "show_number")
	public Integer getShowNumber() {
		return showNumber;
	}
	public void setShowNumber(Integer showNumber) {
		this.showNumber = showNumber;
	}
	
	
	
}