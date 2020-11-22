package com.hsk.xframe.api.persistence;

 
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * SysFieldInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_FIELD_INFO")
public class SysFieldInfo implements java.io.Serializable {

	// Fields

	private Integer sfieldId;
	private Integer stableId;
	private String fieldName;
	private String fieldNode;
	private String fieldCode;
	private String dataType;
	private Integer fieldLength;
	private Integer fieldPrecision;
	private String defaultValue;
	private String ifNull;
	private String ifPk;
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
	public SysFieldInfo() {
	}

	/** minimal constructor */
	public SysFieldInfo(Integer sfieldId) {
		this.sfieldId = sfieldId;
	}

	/** full constructor */
	public SysFieldInfo(Integer sfieldId, Integer stableId,
			String fieldName, String fieldNode, String fieldCode,
			String dataType, Integer fieldLength, Integer fieldPrecision,
			String defaultValue, String ifNull, String ifPk) {
		this.sfieldId = sfieldId;
		this.stableId = stableId;
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
	@Column(name = "SFIELD_ID", unique = true, nullable = false, precision = 6, scale = 0)
	public Integer getSfieldId() {
		return this.sfieldId;
	}

	public void setSfieldId(Integer sfieldId) {
		this.sfieldId = sfieldId;
	}

	@Column(name = "STABLE_ID", precision = 22, scale = 0)
	public Integer getStableId() {
		return this.stableId;
	}

	public void setStableId(Integer stableId) {
		this.stableId = stableId;
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

}