package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "md_out_order_log")
public class MdOutOrderLog {

	private Integer moologId;//moolog_id

	private Integer mooId;//moo_id

	private String  mooCode;//moo_code

	private String createRen;//create_ren

	private String createLog;//create_log

	private String operationState;//operation_state

	private Date createDate;//create_date
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "moolog_id", nullable = false)
	public Integer getMoologId() {
		return moologId;
	}

	public void setMoologId(Integer moologId) {
		this.moologId = moologId;
	}
	@Column(name = "moo_id")
	public Integer getMooId() {
		return mooId;
	}

	public void setMooId(Integer mooId) {
		this.mooId = mooId;
	}
	@Column(name = "moo_code")
	public String getMooCode() {
		return mooCode;
	}

	public void setMooCode(String mooCode) {
		this.mooCode = mooCode;
	}
	@Column(name = "create_ren")
	public String getCreateRen() {
		return createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}
	@Column(name = "create_log")
	public String getCreateLog() {
		return createLog;
	}

	public void setCreateLog(String createLog) {
		this.createLog = createLog;
	}
	@Column(name = "operation_state")
	public String getOperationState() {
		return operationState;
	}

	public void setOperationState(String operationState) {
		this.operationState = operationState;
	}
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
