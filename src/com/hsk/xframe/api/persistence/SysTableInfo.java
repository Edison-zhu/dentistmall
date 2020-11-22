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

/**
 * SysTableInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_TABLE_INFO")
public class SysTableInfo implements java.io.Serializable {

	// Fields

	private Integer stableId;
	private Integer smiId;
	private String tableName;
	private String tableCode;
	private String tableNode;
	private String tableState;
	private String dbuserName;
	private String dbName;
	private String createRen;
	private Date createDatetime;
	private String mappingclassName;
	private String linkAddree;
	private String pkName;
	private String dbType;
	private String seqstr;

	private String sysCode;
	@Column(name = "sys_code", length = 32)
	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	// Constructors

	/** default constructor */
	public SysTableInfo() {
	}

	/** minimal constructor */
	public SysTableInfo(Integer stableId) {
		this.stableId = stableId;
	}

	/** full constructor */
	public SysTableInfo(Integer stableId, Integer smiId, String tableName,
			String tableCode, String tableNode, String tableState,
			String dbuserName, String dbName, String createRen,
			Date createDatetime, String mappingclassName, String linkAddree,
			String pkName, String dbType, String seqstr) {
		this.stableId = stableId;
		this.smiId = smiId;
		this.tableName = tableName;
		this.tableCode = tableCode;
		this.tableNode = tableNode;
		this.tableState = tableState;
		this.dbuserName = dbuserName;
		this.dbName = dbName;
		this.createRen = createRen;
		this.createDatetime = createDatetime;
		this.mappingclassName = mappingclassName;
		this.linkAddree = linkAddree;
		this.pkName = pkName;
		this.dbType = dbType;
		this.seqstr = seqstr;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "STABLE_ID", unique = true, nullable = false, precision = 6, scale = 0)
	public Integer getStableId() {
		return this.stableId;
	}

	public void setStableId(Integer stableId) {
		this.stableId = stableId;
	}

	@Column(name = "SMI_ID", precision = 22, scale = 0)
	public Integer getSmiId() {
		return this.smiId;
	}

	public void setSmiId(Integer smiId) {
		this.smiId = smiId;
	}

	@Column(name = "TABLE_NAME", length = 64)
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "TABLE_CODE", length = 32)
	public String getTableCode() {
		return this.tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	@Column(name = "TABLE_NODE", length = 125)
	public String getTableNode() {
		return this.tableNode;
	}

	public void setTableNode(String tableNode) {
		this.tableNode = tableNode;
	}

	@Column(name = "TABLE_STATE", length = 32)
	public String getTableState() {
		return this.tableState;
	}

	public void setTableState(String tableState) {
		this.tableState = tableState;
	}

	@Column(name = "DBUSER_NAME", length = 128)
	public String getDbuserName() {
		return this.dbuserName;
	}

	public void setDbuserName(String dbuserName) {
		this.dbuserName = dbuserName;
	}

	@Column(name = "DB_NAME", length = 64)
	public String getDbName() {
		return this.dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	@Column(name = "CREATE_REN", length = 64)
	public String getCreateRen() {
		return this.createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATETIME", length = 7)
	public Date getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	@Column(name = "MAPPINGCLASS_NAME", length = 125)
	public String getMappingclassName() {
		return this.mappingclassName;
	}

	public void setMappingclassName(String mappingclassName) {
		this.mappingclassName = mappingclassName;
	}

	@Column(name = "LINK_ADDREE", length = 125)
	public String getLinkAddree() {
		return this.linkAddree;
	}

	public void setLinkAddree(String linkAddree) {
		this.linkAddree = linkAddree;
	}

	@Column(name = "PK_NAME", length = 64)
	public String getPkName() {
		return this.pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	@Column(name = "DB_TYPE", length = 16)
	public String getDbType() {
		return this.dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	@Column(name = "SEQSTR", length = 512)
	public String getSeqstr() {
		return this.seqstr;
	}

	public void setSeqstr(String seqstr) {
		this.seqstr = seqstr;
	}

}