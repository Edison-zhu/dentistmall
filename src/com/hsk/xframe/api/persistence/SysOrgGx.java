package com.hsk.xframe.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "sys_org_gx")
public class SysOrgGx {
	
	private Integer orgGxId;
	private Integer mxxOrgGxId;
	private String organizaType;
	private String nodeName;
	 
	private Integer oldId;
	private String oldCode;
	
	private String oldIdPath;
	private String oldCodePath;
	private String orgGxIdPath; 
	private Integer mxxOrgcount;
	
	public SysOrgGx(){
		
	}
	
	public SysOrgGx(Integer orgGxId){
		this.orgGxId=orgGxId;
	}

	public SysOrgGx(Integer orgGxId, Integer mxxOrgGxId, String organizaType,
			String nodeName) {
		super();
		this.orgGxId = orgGxId;
		this.mxxOrgGxId = mxxOrgGxId;
		this.organizaType = organizaType;
		this.nodeName = nodeName;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "org_gx_id", unique = true, nullable = false)
	public Integer getOrgGxId() {
		return orgGxId;
	}

	public void setOrgGxId(Integer orgGxId) {
		this.orgGxId = orgGxId;
	}
	@Column(name = "mxx_org_gx_id")
	public Integer getMxxOrgGxId() {
		return mxxOrgGxId;
	}

	public void setMxxOrgGxId(Integer mxxOrgGxId) {
		this.mxxOrgGxId = mxxOrgGxId;
	}
	@Column(name = "organiza_type", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getOrganizaType() {
		return organizaType;
	}

	public void setOrganizaType(String organizaType) {
		this.organizaType = organizaType;
	}
	@Column(name = "node_name", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	@Column(name = "old_id")
	public Integer getOldId() {
		return oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
	}
	@Column(name = "old_code",length = 100)
	public String getOldCode() {
		return oldCode;
	}

	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}
	
	@Column(name = "old_id_path")
	public String getOldIdPath() {
		return oldIdPath;
	}

	public void setOldIdPath(String oldIdPath) {
		this.oldIdPath = oldIdPath;
	}

	
	@Column(name = "old_code_path")
	public String getOldCodePath() {
		return oldCodePath;
	}

	public void setOldCodePath(String oldCodePath) {
		this.oldCodePath = oldCodePath;
	}
	@Column(name = "org_gx_id_path")
	public String getOrgGxIdPath() {
		return orgGxIdPath;
	}

	public void setOrgGxIdPath(String orgGxIdPath) {
		this.orgGxIdPath = orgGxIdPath;
	}

	
	@Formula("(select count(t.org_gx_id) from sys_org_gx t where t.mxx_org_gx_id=org_gx_id)"  )
	public Integer getMxxOrgcount() {
		return mxxOrgcount;
	}

	public void setMxxOrgcount(Integer mxxOrgcount) {
		this.mxxOrgcount = mxxOrgcount;
	}
	
	
	
	
	
}
