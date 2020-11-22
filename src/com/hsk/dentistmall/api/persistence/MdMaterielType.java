package com.hsk.dentistmall.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "md_materiel_type" )
public class MdMaterielType {
	
	private Integer mmtId;
	private Integer mdMmtId;
	private String mmtCode;
	private String mmtName;
	private String remark;
	private String mmtLevel;
	private String mmtPath;
	private String state;
	private String createRen; 
	private Date createDate; 
	private String editRen; 
	private Date editDate; 
	
	private String mmtIds;
	
	public MdMaterielType(){
		
	}
	
	public MdMaterielType(Integer mmtId){
		this.mmtId=mmtId;
	}
	
	public MdMaterielType(Integer mmtId, Integer mdMmtId, String mmtCode,
			String mmtName, String remark, String state, String createRen,
			Date createDate, String editRen, Date editDate) {
		super();
		this.mmtId = mmtId;
		this.mdMmtId = mdMmtId;
		this.mmtCode = mmtCode;
		this.mmtName = mmtName;
		this.remark = remark;
		this.state = state;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mmt_id")
	public Integer getMmtId() {
		return mmtId;
	}

	public void setMmtId(Integer mmtId) {
		this.mmtId = mmtId;
	}
	@Column(name = "md_mmt_id")
	public Integer getMdMmtId() {
		return mdMmtId;
	}

	public void setMdMmtId(Integer mdMmtId) {
		this.mdMmtId = mdMmtId;
	}
	@Column(name = "mmt_code")
	public String getMmtCode() {
		return mmtCode;
	}

	public void setMmtCode(String mmtCode) {
		this.mmtCode = mmtCode;
	}
	@Column(name = "mmt_name")
	public String getMmtName() {
		return mmtName;
	}

	public void setMmtName(String mmtName) {
		this.mmtName = mmtName;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	@Column(name = "mmt_level")
	public String getMmtLevel() {
		return mmtLevel;
	}

	public void setMmtLevel(String mmtLevel) {
		this.mmtLevel = mmtLevel;
	}
	@Column(name = "mmt_path")
	public String getMmtPath() {
		return mmtPath;
	}

	public void setMmtPath(String mmtPath) {
		this.mmtPath = mmtPath;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 *设置创建人(create_ren)字段方法 
	 *@return  返回 <b>MdCompanyGroup.CreateRen</b>的值
	 */	 
	@Column(name = "create_ren" ) 
	public String getCreateRen(){
	    return this.createRen;
	}

  /**
   *设置 create_ren字段方法 
   *@param att_createRen 输入<b>MdCompanyGroup.createRen</b>字段的值
   */
  public void setCreateRen(String att_createRen){
    this.createRen = att_createRen;
  }

	/**
	 *设置创建时间(create_date)字段方法 
	 *@return  返回 <b>MdCompanyGroup.CreateDate</b>的值
	 */	 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date" ) 
	public Date getCreateDate(){
	    return this.createDate;
	}

	  /**
	   *设置 create_date字段方法 
	   *@param att_createDate 输入<b>MdCompanyGroup.createDate</b>字段的值
	   */
	  public void setCreateDate(Date att_createDate){
	    this.createDate = att_createDate;
	  }

	/**
	 *设置修改人(edit_ren)字段方法 
	 *@return  返回 <b>MdCompanyGroup.EditRen</b>的值
	 */	 
	@Column(name = "edit_ren" ) 
	public String getEditRen(){
	    return this.editRen;
	}

  /**
   *设置 edit_ren字段方法 
   *@param att_editRen 输入<b>MdCompanyGroup.editRen</b>字段的值
   */
  public void setEditRen(String att_editRen){
    this.editRen = att_editRen;
  }

	/**
	 *设置修改时间(edit_date)字段方法 
	 *@return  返回 <b>MdCompanyGroup.EditDate</b>的值
	 */	 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_date" ) 
	public Date getEditDate(){
	    return this.editDate;
	}

  /**
   *设置 edit_date字段方法 
   *@param att_editDate 输入<b>MdCompanyGroup.editDate</b>字段的值
   */
  public void setEditDate(Date att_editDate){
    this.editDate = att_editDate;
  }
  @Transient
	public String getMmtIds() {
		return mmtIds;
	}
	
	public void setMmtIds(String mmtIds) {
		this.mmtIds = mmtIds;
	}
  
  
}
