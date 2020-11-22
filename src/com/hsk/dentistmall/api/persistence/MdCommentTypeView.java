package com.hsk.dentistmall.api.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MdCommentType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_comment_type")
public class MdCommentTypeView implements java.io.Serializable {
	
	private Integer mmtId;
	private Integer mdMmtId;
	private String mmtCode;
	private String mmtName;
	private String remark;
	private String state;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;
	private Integer mscId;
	private Integer swcId;
	private Integer commId;
	private String mscType;
	private Integer serialComm;
	private String content;
	private String commState;

	// Constructors

	/** default constructor */
	public MdCommentTypeView() {
	}

	/** minimal constructor */
	public MdCommentTypeView(Integer mscId) {
		this.mscId = mscId;
	}

	/** full constructor */
	public MdCommentTypeView(Integer mmtId, Integer mdMmtId, String mmtCode,
			String mmtName, String remark, String state, String createRen,
			Date createDate, String editRen, Date editDate,
			Integer mscId, Integer swcId, Integer commId, String mscType,
			Integer serialComm, String content, String commState) {
		this.mmtId = mmtId;
		this.mdMmtId = mdMmtId;
		this.mmtCode = mmtCode;
		this.mmtName = mmtName;
		this.remark = remark;
		this.state = state;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
		this.mscId = mscId;
		this.swcId = swcId;
		this.commId = commId;
		this.mscType = mscType;
		this.serialComm = serialComm;
		this.content = content;
		this.commState = commState;
	}

	// Property accessors
	@Id
	@Column(name = "msc_id", nullable = false)
	public Integer getMscId() {
		return this.mscId;
	}

	public void setMscId(Integer mscId) {
		this.mscId = mscId;
	}
	@Column(name = "mmt_id", nullable = false)
	public Integer getMmtId() {
		return this.mmtId;
	}

	public void setMmtId(Integer mmtId) {
		this.mmtId = mmtId;
	}

	@Column(name = "md_mmt_id")
	public Integer getMdMmtId() {
		return this.mdMmtId;
	}

	public void setMdMmtId(Integer mdMmtId) {
		this.mdMmtId = mdMmtId;
	}

	@Column(name = "mmt_code", length = 50)
	public String getMmtCode() {
		return this.mmtCode;
	}

	public void setMmtCode(String mmtCode) {
		this.mmtCode = mmtCode;
	}

	@Column(name = "mmt_name", length = 100)
	public String getMmtName() {
		return this.mmtName;
	}

	public void setMmtName(String mmtName) {
		this.mmtName = mmtName;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "state", length = 32)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
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
	@Column(name = "swc_id")
	public Integer getSwcId() {
		return this.swcId;
	}

	public void setSwcId(Integer swcId) {
		this.swcId = swcId;
	}

	@Column(name = "comm_id")
	public Integer getCommId() {
		return this.commId;
	}

	public void setCommId(Integer commId) {
		this.commId = commId;
	}

	@Column(name = "msc_type", length = 10)
	public String getMscType() {
		return this.mscType;
	}

	public void setMscType(String mscType) {
		this.mscType = mscType;
	}

	@Column(name = "serial_comm")
	public Integer getSerialComm() {
		return this.serialComm;
	}

	public void setSerialComm(Integer serialComm) {
		this.serialComm = serialComm;
	}

	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "comm_state", length = 32)
	public String getCommState() {
		return this.commState;
	}

	public void setCommState(String commState) {
		this.commState = commState;
	}
}