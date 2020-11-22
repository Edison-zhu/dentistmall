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
@Table(name = "SYS_GX_USER_MENU")
public class SysGxUserMenu {
	
	private Integer sgumId;
	private Integer smenuId;
	private Integer suiId;
	private Integer type;
	private Integer sroleId;
	
	public SysGxUserMenu(){
		
	}
	
	public SysGxUserMenu(Integer sgumId){
		this.sgumId=sgumId;
	}
	
	public SysGxUserMenu(Integer sgumId, Integer smenuId, Integer suiId,
			Integer type, Integer sroleId) {
		super();
		this.sgumId = sgumId;
		this.smenuId = smenuId;
		this.suiId = suiId;
		this.type = type;
		this.sroleId = sroleId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sgum_id", unique = true, nullable = false)
	public Integer getSgumId() {
		return sgumId;
	}

	public void setSgumId(Integer sgumId) {
		this.sgumId = sgumId;
	}
	@Column(name = "smenu_id", unique = true, nullable = false)
	public Integer getSmenuId() {
		return smenuId;
	}

	public void setSmenuId(Integer smenuId) {
		this.smenuId = smenuId;
	}
	@Column(name = "sui_id", unique = true, nullable = false)
	public Integer getSuiId() {
		return suiId;
	}

	public void setSuiId(Integer suiId) {
		this.suiId = suiId;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name = "srole_id")
	public Integer getSroleId() {
		return sroleId;
	}

	public void setSroleId(Integer sroleId) {
		this.sroleId = sroleId;
	}
}
