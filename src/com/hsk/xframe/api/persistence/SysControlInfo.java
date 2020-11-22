package com.hsk.xframe.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sys_control_info")
public class SysControlInfo {
	
	private Integer sconId;
	private Integer smenuId;
	private String sconName;
	private String sconType;
	private String sconCode;
	private Integer sconRelevant;
	private String sconNode;
	private Integer sconOrder;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;
	
	public SysControlInfo(){
		
	}
	
	public SysControlInfo(Integer sconId){
		this.sconId = sconId;
	}

	public SysControlInfo(Integer sconId, Integer smenuId, String sconName,
			String sconType, String sconCode, Integer sconRelevant,
			String sconNode, Integer sconOrder, String createRen,
			Date createDate, String editRen, Date editDate) {
		super();
		this.sconId = sconId;
		this.smenuId = smenuId;
		this.sconName = sconName;
		this.sconType = sconType;
		this.sconCode = sconCode;
		this.sconRelevant = sconRelevant;
		this.sconNode = sconNode;
		this.sconOrder = sconOrder;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "scon_id", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getSconId() {
		return sconId;
	}

	public void setSconId(Integer sconId) {
		this.sconId = sconId;
	}
	@Column(name = "smenu_id")
	public Integer getSmenuId() {
		return smenuId;
	}

	public void setSmenuId(Integer smenuId) {
		this.smenuId = smenuId;
	}
	@Column(name = "scon_name", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getSconName() {
		return sconName;
	}

	public void setSconName(String sconName) {
		this.sconName = sconName;
	}
	@Column(name = "scon_type", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getSconType() {
		return sconType;
	}

	public void setSconType(String sconType) {
		this.sconType = sconType;
	}
	@Column(name = "scon_code", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getSconCode() {
		return sconCode;
	}

	public void setSconCode(String sconCode) {
		this.sconCode = sconCode;
	}
	@Column(name = "scon_relevant")
	public Integer getSconRelevant() {
		return sconRelevant;
	}

	public void setSconRelevant(Integer sconRelevant) {
		this.sconRelevant = sconRelevant;
	}
	@Column(name = "scon_node", unique = false, nullable = true, insertable = true, updatable = true, length = 125)
	public String getSconNode() {
		return sconNode;
	}

	public void setSconNode(String sconNode) {
		this.sconNode = sconNode;
	}
	@Column(name = "scon_order")
	public Integer getSconOrder() {
		return sconOrder;
	}

	public void setSconOrder(Integer sconOrder) {
		this.sconOrder = sconOrder;
	}

	@Column(name = "create_ren", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCreateRen() {
		return createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name = "edit_ren", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getEditRen() {
		return editRen;
	}

	public void setEditRen(String editRen) {
		this.editRen = editRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_date")
	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	
}
