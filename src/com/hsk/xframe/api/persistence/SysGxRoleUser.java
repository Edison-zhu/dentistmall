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
@Table(name = "SYS_GX_ROLE_USER")
public class SysGxRoleUser {
	
	private Integer sgruId;
	private Integer suiId;
	private Integer sroleId;
	
	public SysGxRoleUser(){
		
	}
	
	public SysGxRoleUser(Integer sgruId){
		this.sgruId=sgruId;
	}

	public SysGxRoleUser(Integer sgruId, Integer suiId, Integer sroleId) {
		super();
		this.sgruId = sgruId;
		this.suiId = suiId;
		this.sroleId = sroleId;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sgru_id", unique = true, nullable = false)
	public Integer getSgruId() {
		return sgruId;
	}

	public void setSgruId(Integer sgruId) {
		this.sgruId = sgruId;
	}
	@Column(name = "sui_id", unique = true, nullable = false)
	public Integer getSuiId() {
		return suiId;
	}

	public void setSuiId(Integer suiId) {
		this.suiId = suiId;
	}
	@Column(name = "srole_id", unique = true, nullable = false)
	public Integer getSroleId() {
		return sroleId;
	}

	public void setSroleId(Integer sroleId) {
		this.sroleId = sroleId;
	}
	
}
