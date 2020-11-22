package com.hsk.xframe.api.persistence;

 
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
 
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_MODEL_OBJECT" )
public class SysModelObject implements java.io.Serializable {

	// Fields

	private Integer smoId;
	private Integer sysSmoId;
	private String smoName;
	private String smoComment;
	private String smoIdPath;
	private String smoNamePath;
	private String smoType;
	private String sysCode;

	// Constructors

	/** default constructor */
	public SysModelObject() {
	}

	/** minimal constructor */
	public SysModelObject(Integer smoId) {
		this.smoId = smoId;
	}

	/** full constructor */
	public SysModelObject(Integer smoId, Integer sysSmoId, String smoName,
			String smoComment, String smoIdPath, String smoNamePath,
			String smoType) {
		this.smoId = smoId;
		this.sysSmoId = sysSmoId;
		this.smoName = smoName;
		this.smoComment = smoComment;
		this.smoIdPath = smoIdPath;
		this.smoNamePath = smoNamePath;
		this.smoType = smoType;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SMO_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getSmoId() {
		return this.smoId;
	}

	public void setSmoId(Integer smoId) {
		this.smoId = smoId;
	}

	@Column(name = "SYS_SMO_ID", precision = 22, scale = 0)
	public Integer getSysSmoId() {
		return this.sysSmoId;
	}

	public void setSysSmoId(Integer sysSmoId) {
		this.sysSmoId = sysSmoId;
	}

	@Column(name = "SMO_NAME", length = 64)
	public String getSmoName() {
		return this.smoName;
	}

	public void setSmoName(String smoName) {
		this.smoName = smoName;
	}

	@Column(name = "SMO_COMMENT", length = 512)
	public String getSmoComment() {
		return this.smoComment;
	}

	public void setSmoComment(String smoComment) {
		this.smoComment = smoComment;
	}

	@Column(name = "SMO_ID_PATH", length = 512)
	public String getSmoIdPath() {
		return this.smoIdPath;
	}

	public void setSmoIdPath(String smoIdPath) {
		this.smoIdPath = smoIdPath;
	}

	@Column(name = "SMO_NAME_PATH", length = 512)
	public String getSmoNamePath() {
		return this.smoNamePath;
	}

	public void setSmoNamePath(String smoNamePath) {
		this.smoNamePath = smoNamePath;
	}

	@Column(name = "SMO_TYPE", length = 32)
	public String getSmoType() {
		return this.smoType;
	}

	public void setSmoType(String smoType) {
		this.smoType = smoType;
	}
	@Column(name = "sys_code", length = 32)
	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	
	

}