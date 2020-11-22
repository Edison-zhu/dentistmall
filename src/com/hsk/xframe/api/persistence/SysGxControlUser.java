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
@Table(name = "sys_gx_control_User")
public class SysGxControlUser {
	
	private Integer sgruId;
	private Integer sconId;
	private Integer suiId;
	private Integer ifOperate;
	private String propertiesSet;
	private Integer type;
	private Integer sroleId;
	
	public SysGxControlUser(){
		
	}
	
	public SysGxControlUser(Integer sgruId){
		this.sgruId=sgruId;
	}

	public SysGxControlUser(Integer sgruId, Integer sconId, Integer suiId,
			Integer ifOperate, String propertiesSet, Integer type,
			Integer sroleId) {
		super();
		this.sgruId = sgruId;
		this.sconId = sconId;
		this.suiId = suiId;
		this.ifOperate = ifOperate;
		this.propertiesSet = propertiesSet;
		this.type = type;
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
	@Column(name = "scon_id", unique = true, nullable = false)
	public Integer getSconId() {
		return sconId;
	}

	public void setSconId(Integer sconId) {
		this.sconId = sconId;
	}
	@Column(name = "sui_id", unique = true, nullable = false)
	public Integer getSuiId() {
		return suiId;
	}

	public void setSuiId(Integer suiId) {
		this.suiId = suiId;
	}
	@Column(name = "if_operate")
	public Integer getIfOperate() {
		return ifOperate;
	}

	public void setIfOperate(Integer ifOperate) {
		this.ifOperate = ifOperate;
	}
	@Column(name = "properties_set", length = 512)
	public String getPropertiesSet() {
		return propertiesSet;
	}

	public void setPropertiesSet(String propertiesSet) {
		this.propertiesSet = propertiesSet;
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
