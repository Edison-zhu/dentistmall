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

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "sys_company")
public class SysCompany {
	
	private Integer wzId;
	private Integer orgGxId;
	private String fullName;
	private String legalPerson;
	private String phoneNumber;
	private String mailBox;
	private String education;
	private String businessIntroduction;
	private String applicantName;
	private String corporateDomicile;
	private String enterpriseType;
	private String scopeBusiness;
	private String licenseNumber;
	private String certificateAnnexFileCode;
	private String state;
	private String selProvince;
	private String selCity;
	private String selArea;
	private String orgName;
	private Integer printNum;
	private Date createDate;
	private String createRen;
	private String barCode;
	private String barCodeFileCode;
	private String editRen;
	private Date editDate;
	
	public SysCompany(){
		
	}
	
	public SysCompany(Integer wzId){
		this.wzId=wzId;
	}

	public SysCompany(Integer wzId, Integer orgGxId, String fullName,
			String legalPerson, String phoneNumber, String mailBox,
			String education, String businessIntroduction,
			String applicantName, String corporateDomicile,
			String enterpriseType, String scopeBusiness, String licenseNumber,
			String certificateAnnexFileCode, String state, String selProvince,
			String selCity, String selArea, String orgName, Integer printNum,
			Date createDate, String createRen, String barCode,
			String barCodeFileCode, String editRen, Date editDate) {
		super();
		this.wzId = wzId;
		this.orgGxId = orgGxId;
		this.fullName = fullName;
		this.legalPerson = legalPerson;
		this.phoneNumber = phoneNumber;
		this.mailBox = mailBox;
		this.education = education;
		this.businessIntroduction = businessIntroduction;
		this.applicantName = applicantName;
		this.corporateDomicile = corporateDomicile;
		this.enterpriseType = enterpriseType;
		this.scopeBusiness = scopeBusiness;
		this.licenseNumber = licenseNumber;
		this.certificateAnnexFileCode = certificateAnnexFileCode;
		this.state = state;
		this.selProvince = selProvince;
		this.selCity = selCity;
		this.selArea = selArea;
		this.orgName = orgName;
		this.printNum = printNum;
		this.createDate = createDate;
		this.createRen = createRen;
		this.barCode = barCode;
		this.barCodeFileCode = barCodeFileCode;
		this.editRen = editRen;
		this.editDate = editDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "wz_id", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getWzId() {
		return wzId;
	}

	public void setWzId(Integer wzId) {
		this.wzId = wzId;
	}
	@Column(name = "org_gx_id")
	public Integer getOrgGxId() {
		return orgGxId;
	}

	public void setOrgGxId(Integer orgGxId) {
		this.orgGxId = orgGxId;
	}
	@Column(name = "full_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Column(name = "legal_person", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	@Column(name = "phone_number", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name = "mailbox", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getMailBox() {
		return mailBox;
	}

	public void setMailBox(String mailBox) {
		this.mailBox = mailBox;
	}
	@Column(name = "education", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	@Column(name = "business_introduction", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getBusinessIntroduction() {
		return businessIntroduction;
	}

	public void setBusinessIntroduction(String businessIntroduction) {
		this.businessIntroduction = businessIntroduction;
	}
	@Column(name = "applicant_name", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	@Column(name = "corporate_domicile", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCorporateDomicile() {
		return corporateDomicile;
	}

	public void setCorporateDomicile(String corporateDomicile) {
		this.corporateDomicile = corporateDomicile;
	}
	@Column(name = "enterprise_type", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}
	@Column(name = "scope_business", unique = false, nullable = true, insertable = true, updatable = true, length = 400)
	public String getScopeBusiness() {
		return scopeBusiness;
	}

	public void setScopeBusiness(String scopeBusiness) {
		this.scopeBusiness = scopeBusiness;
	}
	@Column(name = "license_number", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	@Column(name = "certificate_annex_fileCode", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCertificateAnnexFileCode() {
		return certificateAnnexFileCode;
	}

	public void setCertificateAnnexFileCode(String certificateAnnexFileCode) {
		this.certificateAnnexFileCode = certificateAnnexFileCode;
	}
	@Column(name = "state", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "selprovince", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getSelProvince() {
		return selProvince;
	}

	public void setSelProvince(String selProvince) {
		this.selProvince = selProvince;
	}
	@Column(name = "selcity", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getSelCity() {
		return selCity;
	}

	public void setSelCity(String selCity) {
		this.selCity = selCity;
	}
	@Column(name = "selarea", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getSelArea() {
		return selArea;
	}

	public void setSelArea(String selArea) {
		this.selArea = selArea;
	}
	@Formula("(SELECT a.node_name FROM sys_org_gx a WHERE a.org_gx_id= org_gx_id)")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	@Column(name = "print_num")
	public Integer getPrintNum() {
		return printNum;
	}

	public void setPrintNum(Integer printNum) {
		this.printNum = printNum;
	}
	
}
