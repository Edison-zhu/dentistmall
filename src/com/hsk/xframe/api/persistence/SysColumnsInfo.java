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
 * SysColumnsInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_COLUMNS_INFO" )
public class SysColumnsInfo implements java.io.Serializable {

	// Fields

	private Integer scolumId;
	private Integer sconparId;
	private String title;
	private String field;
	private String sciHeight;
	private String sciWidth;
	private String rowspan;
	private String colspan;
	private String align;
	private String halign;
	private String formatter;
	private String ifHidden;
	private String columCode;
	private String contType;
	private String sccName;
	private String fieldName;
	private String fieldNode;
	private String fieldCode;
	private String dataType;
	private Integer fieldLength;
	private Integer fieldPrecision;
	private String defaultValue;
	private String ifNull;
	private String ifPk;

	// Constructors

	/** default constructor */
	public SysColumnsInfo() {
	}

	/** minimal constructor */
	public SysColumnsInfo(Integer scolumId) {
		this.scolumId = scolumId;
	}

	/** full constructor */
	public SysColumnsInfo(Integer scolumId, Integer sconparId, String title,
			String field, String sciHeight, String sciWidth, String rowspan,
			String colspan, String align, String halign, String formatter,
			String ifHidden, String columCode, String contType, String sccName,
			String fieldName, String fieldNode, String fieldCode,
			String dataType, Integer fieldLength, Integer fieldPrecision,
			String defaultValue, String ifNull, String ifPk) {
		this.scolumId = scolumId;
		this.sconparId = sconparId;
		this.title = title;
		this.field = field;
		this.sciHeight = sciHeight;
		this.sciWidth = sciWidth;
		this.rowspan = rowspan;
		this.colspan = colspan;
		this.align = align;
		this.halign = halign;
		this.formatter = formatter;
		this.ifHidden = ifHidden;
		this.columCode = columCode;
		this.contType = contType;
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
	@Column(name = "SCOLUM_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getScolumId() {
		return this.scolumId;
	}

	public void setScolumId(Integer scolumId) {
		this.scolumId = scolumId;
	}

	@Column(name = "SCONPAR_ID", precision = 22, scale = 0)
	public Integer getSconparId() {
		return this.sconparId;
	}

	public void setSconparId(Integer sconparId) {
		this.sconparId = sconparId;
	}

	@Column(name = "TITLE", length = 64)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "FIELD", length = 64)
	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Column(name = "SCI_HEIGHT", length = 32)
	public String getSciHeight() {
		return this.sciHeight;
	}

	public void setSciHeight(String sciHeight) {
		this.sciHeight = sciHeight;
	}

	@Column(name = "SCI_WIDTH", length = 32)
	public String getSciWidth() {
		return this.sciWidth;
	}

	public void setSciWidth(String sciWidth) {
		this.sciWidth = sciWidth;
	}

	@Column(name = "ROWSPAN", length = 32)
	public String getRowspan() {
		return this.rowspan;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	@Column(name = "COLSPAN", length = 32)
	public String getColspan() {
		return this.colspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	@Column(name = "ALIGN", length = 32)
	public String getAlign() {
		return this.align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	@Column(name = "HALIGN", length = 32)
	public String getHalign() {
		return this.halign;
	}

	public void setHalign(String halign) {
		this.halign = halign;
	}

	@Column(name = "FORMATTER", length = 512)
	public String getFormatter() {
		return this.formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	@Column(name = "IF_HIDDEN", length = 16)
	public String getIfHidden() {
		return this.ifHidden;
	}

	public void setIfHidden(String ifHidden) {
		this.ifHidden = ifHidden;
	}

	@Column(name = "COLUM_CODE", length = 64)
	public String getColumCode() {
		return this.columCode;
	}

	public void setColumCode(String columCode) {
		this.columCode = columCode;
	}

	@Column(name = "CONT_TYPE", length = 16)
	public String getContType() {
		return this.contType;
	}

	public void setContType(String contType) {
		this.contType = contType;
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

}