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
@Table(name = "SYS_GX_ROLE_CONTROL")
public class SysGxRoleControl {
	
	private Integer sgrcId;
	private Integer sconId;
	private Integer sroleId;
	private Integer ifOperate;
	private String propertiesSet;
	
	public SysGxRoleControl(){
		
	}
	
	public SysGxRoleControl(Integer sgrcId){
		this.sgrcId=sgrcId;
	}

	public SysGxRoleControl(Integer sgrcId, Integer sconId, Integer sroleId,
			Integer ifOperate, String propertiesSet) {
		super();
		this.sgrcId = sgrcId;
		this.sconId = sconId;
		this.sroleId = sroleId;
		this.ifOperate = ifOperate;
		this.propertiesSet = propertiesSet;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sgrc_id", unique = true, nullable = false)
	public Integer getSgrcId() {
		return sgrcId;
	}

	public void setSgrcId(Integer sgrcId) {
		this.sgrcId = sgrcId;
	}

	@Column(name = "scon_id", unique = true, nullable = false)
	public Integer getSconId() {
		return sconId;
	}

	public void setSconId(Integer sconId) {
		this.sconId = sconId;
	}
	@Column(name = "srole_id", unique = true, nullable = false)
	public Integer getSroleId() {
		return sroleId;
	}

	public void setSroleId(Integer sroleId) {
		this.sroleId = sroleId;
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
	
}
