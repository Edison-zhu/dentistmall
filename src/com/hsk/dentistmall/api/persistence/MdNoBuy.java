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
 * MdNoBuy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_no_buy")
public class MdNoBuy implements java.io.Serializable {

	// Fields

	private Integer mnbId;
	private Integer mmfId;
	private Integer suiId;
	private String state;
	private String content;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;

	// Constructors

	/** default constructor */
	public MdNoBuy() {
	}

	/** full constructor */
	public MdNoBuy(Integer mmfId, Integer suiId, String state, String content,
			String createRen, Date createDate, String editRen,
			Date editDate) {
		this.mmfId = mmfId;
		this.suiId = suiId;
		this.state = state;
		this.content = content;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mnb_id", unique = true, nullable = false)
	public Integer getMnbId() {
		return this.mnbId;
	}

	public void setMnbId(Integer mnbId) {
		this.mnbId = mnbId;
	}

	@Column(name = "mmf_id")
	public Integer getMmfId() {
		return this.mmfId;
	}

	public void setMmfId(Integer mmfId) {
		this.mmfId = mmfId;
	}

	@Column(name = "sui_id")
	public Integer getSuiId() {
		return this.suiId;
	}

	public void setSuiId(Integer suiId) {
		this.suiId = suiId;
	}

	@Column(name = "state", length = 32)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "create_ren", length = 512)
	public String getCreateRen() {
		return this.createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "edit_ren", length = 512)
	public String getEditRen() {
		return this.editRen;
	}

	public void setEditRen(String editRen) {
		this.editRen = editRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_date", length = 0)
	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

}