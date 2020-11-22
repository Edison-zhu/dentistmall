package com.hsk.dentistmall.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MdFavorites entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_favorites")
public class MdFavorites implements java.io.Serializable {

	// Fields
	private Integer mfId;
	private Integer suiId;
	private Integer wmsMiId;
	private String mfState;
	private String content;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;
	private Integer mmfId;
	// Constructors

	/** default constructor */
	public MdFavorites() {
	}
	public MdFavorites(Integer mfId){
		this.mfId=mfId;
	}

	/** full constructor */
	public MdFavorites(Integer wmsMiId, Integer mfId, Integer suiId,
			String mfState, String content, String createRen,
			Date createDate, String editRen, Date editDate) {
		this.wmsMiId = wmsMiId;
		this.mfId = mfId;
		this.suiId = suiId;
		this.mfState = mfState;
		this.content = content;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mf_id")
	public Integer getMfId() {
		return this.mfId;
	}

	public void setMfId(Integer mfId) {
		this.mfId = mfId;
	}
	@Column(name = "wms_mi_id")
	public Integer getWmsMiId() {
		return this.wmsMiId;
	}

	public void setWmsMiId(Integer wmsMiId) {
		this.wmsMiId = wmsMiId;
	}

	@Column(name = "sui_id")
	public Integer getSuiId() {
		return this.suiId;
	}

	public void setSuiId(Integer suiId) {
		this.suiId = suiId;
	}

	@Column(name = "mf_state", length = 32)
	public String getMfState() {
		return this.mfState;
	}

	public void setMfState(String mfState) {
		this.mfState = mfState;
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

	@Column(name = "mmf_id")
	public Integer getMmfId() {
		return mmfId;
	}

	public void setMmfId(Integer mmfId) {
		this.mmfId = mmfId;
	}
}