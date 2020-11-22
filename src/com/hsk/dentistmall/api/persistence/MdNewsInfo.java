package com.hsk.dentistmall.api.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MdNewsInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_news_info")
public class MdNewsInfo implements java.io.Serializable {

	// Fields

	private Integer mniId;
	private Integer uiId;
	private Integer orderId;
	private Integer uiType;
	private Integer newsType;
	private String content;
	private Integer isView;
	private Date createDate; 

	// Constructors

	/** default constructor */
	public MdNewsInfo() {
	}

	/** full constructor */
	public MdNewsInfo(Integer uiId, Integer orderId, Integer uiType,
			Integer newsType, String content, Integer isView, Date createDate) {
		this.uiId = uiId;
		this.orderId = orderId;
		this.uiType = uiType;
		this.newsType = newsType;
		this.content = content;
		this.isView = isView;
		this.createDate = createDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mni_id", unique = true, nullable = false)
	public Integer getMniId() {
		return this.mniId;
	}

	public void setMniId(Integer mniId) {
		this.mniId = mniId;
	}

	@Column(name = "ui_id")
	public Integer getUiId() {
		return this.uiId;
	}

	public void setUiId(Integer uiId) {
		this.uiId = uiId;
	}

	@Column(name = "order_id")
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name = "ui_type")
	public Integer getUiType() {
		return this.uiType;
	}

	public void setUiType(Integer uiType) {
		this.uiType = uiType;
	}

	@Column(name = "news_type")
	public Integer getNewsType() {
		return this.newsType;
	}

	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}

	@Column(name = "content", length = 100)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "is_view")
	public Integer getIsView() {
		return this.isView;
	}

	public void setIsView(Integer isView) {
		this.isView = isView;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date" ) 
	public Date getCreateDate(){
	    return this.createDate;
	}

  
    public void setCreateDate(Date att_createDate){
    	this.createDate = att_createDate;
    }

}