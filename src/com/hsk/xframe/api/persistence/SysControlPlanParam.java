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
 * SysControlPlanParam entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_CONTROL_PLAN_PARAM" )
public class SysControlPlanParam implements java.io.Serializable {

	// Fields

	private Integer sconparId;
	private Integer smoId;
	private String sconparType;
	private String paramState;
	private Integer paramOrder;
	private String paramCode;
	private String scontparName;
	private String scontparId;
	private String tableName;
	private String tableCode;
	private Integer showColumnNumber;
	private String cssClassName;
	private String cssStyle;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;

	// Constructors

	/** default constructor */
	public SysControlPlanParam() {
	}

	/** minimal constructor */
	public SysControlPlanParam(Integer sconparId) {
		this.sconparId = sconparId;
	}

	/** full constructor */
	public SysControlPlanParam(Integer sconparId, Integer smoId,
			String sconparType, String paramState, Integer paramOrder,
			String paramCode, String scontparName, String scontparId,
			String tableName, String tableCode, Integer showColumnNumber,
			String cssClassName, String cssStyle, String createRen,
			Date createDate, String editRen, Date editDate) {
		this.sconparId = sconparId;
		this.smoId = smoId;
		this.sconparType = sconparType;
		this.paramState = paramState;
		this.paramOrder = paramOrder;
		this.paramCode = paramCode;
		this.scontparName = scontparName;
		this.scontparId = scontparId;
		this.tableName = tableName;
		this.tableCode = tableCode;
		this.showColumnNumber = showColumnNumber;
		this.cssClassName = cssClassName;
		this.cssStyle = cssStyle;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SCONPAR_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getSconparId() {
		return this.sconparId;
	}

	public void setSconparId(Integer sconparId) {
		this.sconparId = sconparId;
	}

	@Column(name = "SMO_ID", precision = 22, scale = 0)
	public Integer getSmoId() {
		return this.smoId;
	}

	public void setSmoId(Integer smoId) {
		this.smoId = smoId;
	}

	@Column(name = "SCONPAR_TYPE", length = 32)
	public String getSconparType() {
		return this.sconparType;
	}

	public void setSconparType(String sconparType) {
		this.sconparType = sconparType;
	}

	@Column(name = "PARAM_STATE", length = 32)
	public String getParamState() {
		return this.paramState;
	}

	public void setParamState(String paramState) {
		this.paramState = paramState;
	}

	@Column(name = "PARAM_ORDER", precision = 22, scale = 0)
	public Integer getParamOrder() {
		return this.paramOrder;
	}

	public void setParamOrder(Integer paramOrder) {
		this.paramOrder = paramOrder;
	}

	@Column(name = "PARAM_CODE", length = 32)
	public String getParamCode() {
		return this.paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	@Column(name = "SCONTPAR_NAME", length = 64)
	public String getScontparName() {
		return this.scontparName;
	}

	public void setScontparName(String scontparName) {
		this.scontparName = scontparName;
	}

	@Column(name = "SCONTPAR_ID", length = 64)
	public String getScontparId() {
		return this.scontparId;
	}

	public void setScontparId(String scontparId) {
		this.scontparId = scontparId;
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

	@Column(name = "SHOW_COLUMN_NUMBER", precision = 22, scale = 0)
	public Integer getShowColumnNumber() {
		return this.showColumnNumber;
	}

	public void setShowColumnNumber(Integer showColumnNumber) {
		this.showColumnNumber = showColumnNumber;
	}

	@Column(name = "CSS_CLASS_NAME", length = 32)
	public String getCssClassName() {
		return this.cssClassName;
	}

	public void setCssClassName(String cssClassName) {
		this.cssClassName = cssClassName;
	}

	@Column(name = "CSS_STYLE", length = 512)
	public String getCssStyle() {
		return this.cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	@Column(name = "CREATE_REN", length = 512)
	public String getCreateRen() {
		return this.createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 11)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "EDIT_REN", length = 512)
	public String getEditRen() {
		return this.editRen;
	}

	public void setEditRen(String editRen) {
		this.editRen = editRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EDIT_DATE", length = 11)
	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

}