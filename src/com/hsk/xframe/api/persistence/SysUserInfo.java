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

import org.hibernate.annotations.Formula;

@SuppressWarnings("serial")
@Entity
@Table(name = "sys_user_info")
public class SysUserInfo {
	
	private Integer suiId;
	private Integer sroleId;
	private String userName;
	private String userCode;
	private String email;
	private String phoneNumber;
	private String account;
	private String password;
	private String authentCode;
	private Date createDate;
	private String createRen;
	private Integer state;
	private String barCode;
	private String barCodeFileCode;
	private String editRen;
	private Date editDate;
	 
	private Integer orgGxId; 
	private String orgName;
	private Integer userType;//用户类型:1:管理员；2：供应商；3集团；4医院；5门诊;6业务员
	private String userRole;//当userType为2时:1代表供应商管理员；2供应商普通员工；当userType为集团、医院、门诊时:1代表管理员；2代表采购人员；3代表仓库管理员;4代表医生；当usertype为6：1是业务员，2是代理商
	private String thirdParty;//第三方绑定账号

	private Integer openSecurity;
	private String securityCode;
	private Integer needSecurity;

	private String organizaType; 
	
	private Integer oldId;
	
	private String barCodePath;

	private String companyName;
	//代理商账号
	private String salesCode ;
	@Formula("(select s.sales_code from sys_sales_man s where s.sales_account=account)")
	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	public SysUserInfo(){
		
	}
	
	public SysUserInfo(Integer suiId){
		this.suiId = suiId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sui_id", unique = true, nullable = false)
	public Integer getSuiId() {
		return suiId;
	}

	public void setSuiId(Integer suiId) {
		this.suiId = suiId;
	}
	
	@Column(name = "user_name", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "user_code", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Column(name = "user_email", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "user_phone_number", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name = "password", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "authent_code", unique = false, nullable = true, insertable = true, updatable = true, length = 16)
	public String getAuthentCode() {
		return authentCode;
	}

	public void setAuthentCode(String authentCode) {
		this.authentCode = authentCode;
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
	@Column(name = "account", unique = false, nullable = true, insertable = true, updatable = true, length = 215)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "state", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	@Column(name = "srole_id")
	public Integer getSroleId() {
		return sroleId;
	}

	public void setSroleId(Integer sroleId) {
		this.sroleId = sroleId;
	}
	@Column(name = "barCode", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	@Column(name = "barCode_filecode", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	public String getBarCodeFileCode() {
		return barCodeFileCode;
	}

	public void setBarCodeFileCode(String barCodeFileCode) {
		this.barCodeFileCode = barCodeFileCode;
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
	
	@Column(name = "user_type")
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	@Column(name = "org_gx_id")
	public Integer getOrgGxId() {
		return orgGxId;
	}

	public void setOrgGxId(Integer orgGxId) {
		this.orgGxId = orgGxId;
	}
	
	@Column(name = "user_role")
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	@Column(name = "thirdParty")
	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	
	@Formula("(select t.node_name from sys_org_gx t where t.org_gx_id=org_gx_id)")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Formula("(select t.organiza_Type from sys_org_gx t where t.org_gx_id=org_gx_id)")
	public String getOrganizaType() {
		return organizaType;
	}

	public void setOrganizaType(String organizaType) {
		this.organizaType = organizaType;
	}
	
	@Formula("(select t.old_id from sys_org_gx t where t.org_gx_id=org_gx_id)")
	public Integer getOldId() {
		return oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
	} 
	
	public  String suiId_str ;

	@Transient
	public String getSuiId_str() {
		return suiId_str;
	}

	public void setSuiId_str(String suiId_str) {
		this.suiId_str = suiId_str;
	}
	//getOrgGxId添加字段方法
	private  String OrgGxId_Str;
	@Formula("(select g.node_name from sys_org_gx g where g.org_gx_id=org_gx_id)")
	public String getOrgGxId_Str() {
		return OrgGxId_Str;
	}

	public void setOrgGxId_Str(String orgGxId_Str) {
		OrgGxId_Str = orgGxId_Str;
	}

	public  String state_str ;

	@Transient
	public String getState_str() {
		return state_str;
	}

	public void setState_str(String state_str) {
		this.state_str = state_str;
	}
	@Formula("(select t.root_path from sys_file_info t where t.file_code=barcode)")
	public String getBarCodePath() {
		return barCodePath;
	}

	public void setBarCodePath(String barCodePath) {
		this.barCodePath = barCodePath;
	}

	@Transient
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "open_security")
//	@Transient
	public Integer getOpenSecurity() {
		return openSecurity;
	}

	public void setOpenSecurity(Integer openSecurity) {
		this.openSecurity = openSecurity;
	}

	@Column(name = "security_code")
//	@Transient
	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	@Column(name = "need_security")
	public Integer getNeedSecurity() {
		return needSecurity;
	}

	public void setNeedSecurity(Integer needSecurity) {
		this.needSecurity = needSecurity;
	}
}
