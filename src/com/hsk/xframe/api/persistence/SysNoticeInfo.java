package com.hsk.xframe.api.persistence;

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
 * SysNoticeInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_notice_info")
public class SysNoticeInfo implements java.io.Serializable {

	// Fields

	private Integer sniId;
	private String sniCode;
	private Integer belongType;
	private String title;
	private String content;
	private String state;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;
	private String noticeImage;

	// Constructors

	/** default constructor */
	public SysNoticeInfo() {
	}

	/** full constructor */
	public SysNoticeInfo(String title, String content, String state,
			String createRen, Date createDate, String editRen,
			Date editDate) {
		this.title = title;
		this.content = content;
		this.state = state;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sni_id", unique = true, nullable = false)
	public Integer getSniId() {
		return this.sniId;
	}

	public void setSniId(Integer sniId) {
		this.sniId = sniId;
	}
	@Column(name = "sni_code")
	public String getSniCode() {
		return sniCode;
	}

	@Column(name = "belong_type")
	public Integer getBelongType() {
		return belongType;
	}

	public void setBelongType(Integer belongType) {
		this.belongType = belongType;
	}

	public void setSniCode(String sniCode) {
		this.sniCode = sniCode;
	}

	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "create_ren")
	public String getCreateRen() {
		return this.createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "edit_ren")
	public String getEditRen() {
		return this.editRen;
	}

	public void setEditRen(String editRen) {
		this.editRen = editRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_date")
	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	@Column(name = "notice_image")
	public String getNoticeImage() {
		return noticeImage;
	}

	public void setNoticeImage(String noticeImage) {
		this.noticeImage = noticeImage;
	}
}