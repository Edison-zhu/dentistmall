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
import javax.persistence.Transient;

@Entity
@Table(name = "sys_control_param")
public class SysControlParam {
	
	private Integer sconParId;
	private Integer suiId;
	private String controlId;
	private String paramType;
	private String paramName;
	private String paramNode;
	private Double paramSize;
	private String defaulValue;
	private String paramSource;
	private Integer paramRelationId;
	private String relationNode;
	private String paramState;
	private Integer paramOrder;
	private String paramCode;
	private Date createDate;
	private String createRen;
	private String editRen;
	private Date editDate;
	
	private String paramNodeJson;
	
	public SysControlParam(){
		
	}
	
	public SysControlParam(Integer sconParId){
		
	}

	public SysControlParam(Integer sconParId, Integer suiId, String controlId,
			String paramType, String paramName, String paramNode,
			Double paramSize, String defaulValue, String paramSource,
			Integer paramRelationId, String relationNode, String paramState,
			Integer paramOrder, String paramCode, Date createDate,
			String createRen, String editRen, Date editDate) {
		super();
		this.sconParId = sconParId;
		this.suiId = suiId;
		this.controlId = controlId;
		this.paramType = paramType;
		this.paramName = paramName;
		this.paramNode = paramNode;
		this.paramSize = paramSize;
		this.defaulValue = defaulValue;
		this.paramSource = paramSource;
		this.paramRelationId = paramRelationId;
		this.relationNode = relationNode;
		this.paramState = paramState;
		this.paramOrder = paramOrder;
		this.paramCode = paramCode;
		this.createDate = createDate;
		this.createRen = createRen;
		this.editRen = editRen;
		this.editDate = editDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "scon_par_id", unique = true, nullable = false)
	public Integer getSconParId() {
		return sconParId;
	}

	public void setSconParId(Integer sconParId) {
		this.sconParId = sconParId;
	}
	@Column(name = "sui_id", unique = true, nullable = false)
	public Integer getSuiId() {
		return suiId;
	}

	public void setSuiId(Integer suiId) {
		this.suiId = suiId;
	}
	@Column(name = "control_id", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	public String getControlId() {
		return controlId;
	}

	public void setControlId(String controlId) {
		this.controlId = controlId;
	}
	@Column(name = "param_type", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	@Column(name = "param_name", unique = false, nullable = true, insertable = true, updatable = true, length = 125)
	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	@Column(name = "param_node", unique = false, nullable = true, insertable = true, updatable = true, length = 1024)
	public String getParamNode() {
		return paramNode;
	}

	public void setParamNode(String paramNode) {
		this.paramNode = paramNode;
	}
	@Column(name = "param_size")
	public Double getParamSize() {
		return paramSize;
	}

	public void setParamSize(Double paramSize) {
		this.paramSize = paramSize;
	}
	@Column(name = "defaul_value", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getDefaulValue() {
		return defaulValue;
	}

	public void setDefaulValue(String defaulValue) {
		this.defaulValue = defaulValue;
	}
	@Column(name = "param_source", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getParamSource() {
		return paramSource;
	}

	public void setParamSource(String paramSource) {
		this.paramSource = paramSource;
	}
	@Column(name = "param_relation_id")
	public Integer getParamRelationId() {
		return paramRelationId;
	}

	public void setParamRelationId(Integer paramRelationId) {
		this.paramRelationId = paramRelationId;
	}
	@Column(name = "relation_node", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getRelationNode() {
		return relationNode;
	}

	public void setRelationNode(String relationNode) {
		this.relationNode = relationNode;
	}
	@Column(name = "param_state", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getParamState() {
		return paramState;
	}

	public void setParamState(String paramState) {
		this.paramState = paramState;
	}
	@Column(name = "param_order")
	public Integer getParamOrder() {
		return paramOrder;
	}

	public void setParamOrder(Integer paramOrder) {
		this.paramOrder = paramOrder;
	}
	@Column(name = "param_code", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name = "create_ren", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCreateRen() {
		return createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
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
	@Transient
	public String getParamNodeJson() {
		if(paramNode != null)
			paramNodeJson = paramNode.trim().replaceAll("\n", ",");
		return paramNodeJson;
	}

	public void setParamNodeJson(String paramNodeJson) {
		this.paramNodeJson = paramNodeJson;
	}
}
