package com.hsk.xframe.api.dto.freeMarker;

import com.hsk.xframe.api.utils.freeMarker.AutoCodeUtils;

 

 

public class FMFieldInfo {

	private String fieldCode;
	private String fieldName;
	private String fieldType;
	private Float fieldLength;
	
	private Integer dataLength;	
	private Integer dataPrecision;
	
	private String fieldSeqence;
	private String fieldPrecision;
	private String fieldComment;
	private String isNullable;
	private String   columnKey;

	public String getFieldCode() {  
			return this.fieldCode; 
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	/**
	 * 
	 * @return
	 */
	public String getFieldNames() {
		if (fieldCode == null) {
			return null;
		} else {
			this.fieldName = AutoCodeUtils.toCamelCase(fieldCode);
			return this.fieldName;
		}
	}

	public String getFieldName() { 
			return fieldName; 
	} 
	
	public Integer getDataLength() {
		return dataLength;
	}

	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}

	public Integer getDataPrecision() {
		return dataPrecision;
	}

	public void setDataPrecision(Integer dataPrecision) {
		this.dataPrecision = dataPrecision;
	}

	/**
	 * 
	 * @param fieldName
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Float getFieldLength() {
		if(fieldLength==0){
			return null;
		}
		return fieldLength;
	}

	public void setFieldLength(Float fieldLength) {
		this.fieldLength = fieldLength;
	}

	public String getFieldSeqence() {
		return fieldSeqence;
	}

	public void setFieldSeqence(String fieldSeqence) {
		this.fieldSeqence = fieldSeqence;
	}

	public String getFieldPrecision() {
		return fieldPrecision;
	}

	public void setFieldPrecision(String fieldPrecision) {
		this.fieldPrecision = fieldPrecision;
	}

	public String getFieldComment() {
		return fieldComment;
	}

	public void setFieldComment(String fieldComment) {
		this.fieldComment = fieldComment;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	
	
	

}
