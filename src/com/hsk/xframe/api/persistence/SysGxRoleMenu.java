package com.hsk.xframe.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_GX_ROLE_MENU")
public class SysGxRoleMenu {
	
	private Integer sgrmId;
	private Integer sroleId;
	private Integer smenuId;
	
	public SysGxRoleMenu(){
		
	}
	
	public SysGxRoleMenu(Integer sgrmId){
		this.sgrmId=sgrmId;
	}

	public SysGxRoleMenu(Integer sgrmId, Integer sroleId, Integer smenuId) {
		super();
		this.sgrmId = sgrmId;
		this.sroleId = sroleId;
		this.smenuId = smenuId;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sgrm_id", unique = true, nullable = false)
	public Integer getSgrmId() {
		return sgrmId;
	}

	public void setSgrmId(Integer sgrmId) {
		this.sgrmId = sgrmId;
	}
	@Column(name = "srole_id", unique = true, nullable = false)
	public Integer getSroleId() {
		return sroleId;
	}

	public void setSroleId(Integer sroleId) {
		this.sroleId = sroleId;
	}
	@Column(name = "smenu_id", unique = true, nullable = false)
	public Integer getSmenuId() {
		return smenuId;
	}

	public void setSmenuId(Integer smenuId) {
		this.smenuId = smenuId;
	}

}
