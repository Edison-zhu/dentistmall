package com.hsk.dentistmall.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MdSiteComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_site_comment")
public class MdSiteComment implements java.io.Serializable {

	// Fields

	private Integer mscId;
	private Integer swcId;
	private Integer commId;
	private String mscType;
	private Integer serialComm;
	private String content;
	private String commState;

	// Constructors

	/** default constructor */
	public MdSiteComment() {
	}

	/** full constructor */
	public MdSiteComment(Integer swcId, Integer commId, String mscType,
			Integer serialComm, String content, String commState) {
		this.swcId = swcId;
		this.commId = commId;
		this.mscType = mscType;
		this.serialComm = serialComm;
		this.content = content;
		this.commState = commState;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "msc_id", unique = true, nullable = false)
	public Integer getMscId() {
		return this.mscId;
	}

	public void setMscId(Integer mscId) {
		this.mscId = mscId;
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