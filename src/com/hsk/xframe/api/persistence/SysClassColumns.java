package com.hsk.xframe.api.persistence;
 
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysClassColumns entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "SYS_CLASS_COLUMNS" )
public class SysClassColumns implements java.io.Serializable {

	// Fields

	private Integer sccId;
	private Integer sciId;
	private String sccCode;
	private String inParamComment;
	private String inParamStr;
	private String outParamComment;
	private String outParamStr;
	private String sccName;
	private String fieldName;
	private String fieldNode;
	private String fieldCode;
	private String dataType;
	private Integer fieldLength;
	private Integer fieldPrecision;
	private String defaultValue;
	private String ifTransient;
	private String ifNull;
	private String ifPk;
	private String formula;

	private Integer orderNumber;
	
	

	@Column(name = "ORDER_NUMBER", precision = 22, scale = 0)
	public Integer getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
	// Constructors

	/** default constructor */
	public SysClassColumns() {
	}

	/** minimal constructor */
	public SysClassColumns(Integer sccId) {
		this.sccId = sccId;
	}

	/** full constructor */
	public SysClassColumns(Integer sccId, Integer sciId, String sccCode,
			String inParamComment, String inParamStr, String outParamComment,
			String outParamStr, String sccName, String fieldName,
			String fieldNode, String fieldCode, String dataType,
			Integer fieldLength, Integer fieldPrecision,
			String defaultValue, String ifNull, String ifPk) {
		this.sccId = sccId;
		this.sciId = sciId;
		this.sccCode = sccCode;
		this.inParamComment = inParamComment;
		this.inParamStr = inParamStr;
		this.outParamComment = outParamComment;
		this.outParamStr = outParamStr;
		this.sccName = sccName;
		this.fieldName = fieldName;
		this.fieldNode = fieldNode;
		this.fieldCode = fieldCode;
		this.dataType = dataType;
		this.fieldLength = fieldLength;
		this.fieldPrecision = fieldPrecision;
		this.defaultValue = defaultValue;
		this.ifNull = ifNull;
		this.ifPk = ifPk;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SCC_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getSccId() {
		return this.sccId;
	}

	public void setSccId(Integer sccId) {
		this.sccId = sccId;
	}

	@Column(name = "SCI_ID", precision = 22, scale = 0)
	public Integer getSciId() {
		return this.sciId;
	}

	public void setSciId(Integer sciId) {
		this.sciId = sciId;
	}

	@Column(name = "SCC_CODE", length = 64)
	public String getSccCode() {
		return this.sccCode;
	}

	public void setSccCode(String sccCode) {
		this.sccCode = sccCode;
	}

	@Column(name = "IN_PARAM_COMMENT", length = 512)
	public String getInParamComment() {
		return this.inParamComment;
	}

	public void setInParamComment(String inParamComment) {
		this.inParamComment = inParamComment;
	}

	@Column(name = "IN_PARAM_STR", length = 512)
	public String getInParamStr() {
		return this.inParamStr;
	}

	public void setInParamStr(String inParamStr) {
		this.inParamStr = inParamStr;
	}

	@Column(name = "OUT_PARAM_COMMENT", length = 512)
	public String getOutParamComment() {
		return this.outParamComment;
	}

	public void setOutParamComment(String outParamComment) {
		this.outParamComment = outParamComment;
	}

	@Column(name = "OUT_PARAM_STR", length = 512)
	public String getOutParamStr() {
		return this.outParamStr;
	}

	public void setOutParamStr(String outParamStr) {
		this.outParamStr = outParamStr;
	}

	@Column(name = "SCC_NAME", length = 128)
	public String getSccName() {
		return this.sccName;
	}

	public void setSccName(String sccName) {
		this.sccName = sccName;
	}

	@Column(name = "FIELD_NAME", length = 64)
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "FIELD_NODE", length = 215)
	public String getFieldNode() {
		return this.fieldNode;
	}

	public void setFieldNode(String fieldNode) {
		this.fieldNode = fieldNode;
	}

	@Column(name = "FIELD_CODE", length = 32)
	public String getFieldCode() {
		return this.fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	@Column(name = "DATA_TYPE", length = 32)
	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Column(name = "FIELD_LENGTH", precision = 22, scale = 0)
	public Integer getFieldLength() {
		return this.fieldLength;
	}

	public void setFieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}

	@Column(name = "FIELD_PRECISION", precision = 22, scale = 0)
	public Integer getFieldPrecision() {
		return this.fieldPrecision;
	}

	public void setFieldPrecision(Integer fieldPrecision) {
		this.fieldPrecision = fieldPrecision;
	}

	@Column(name = "DEFAULT_VALUE", length = 64)
	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Column(name = "IF_NULL", length = 32)
	public String getIfNull() {
		return this.ifNull;
	}

	public void setIfNull(String ifNull) {
		this.ifNull = ifNull;
	}

	@Column(name = "IF_PK", length = 32)
	public String getIfPk() {
		return this.ifPk;
	}

	public void setIfPk(String ifPk) {
		this.ifPk = ifPk;
	}
	@Column(name = "if_Transient", length = 32)
	public String getIfTransient() {
		return ifTransient;
	}

	public void setIfTransient(String ifTransient) {
		this.ifTransient = ifTransient;
	}
	@Column(name = "Formula")
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	
	

}