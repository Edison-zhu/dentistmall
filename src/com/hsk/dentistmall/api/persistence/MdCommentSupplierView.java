package com.hsk.dentistmall.api.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

/**
 * MdCommentSupplier entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_comment_supplier")
public class MdCommentSupplierView implements java.io.Serializable {

	private Integer wzId;
	private Integer orgGxId;
	private String phoneNumber;
	private String fullName;
	private String mailbox;
	private String education;
	private Date graduationTime;
	private String businessIntroduction;
	private String applicantName;
	private String corporateDomicile;
	private Double registeredCapital;
	private String enterpriseType;
	private String scopeBusiness;
	private String licenseNumber;
	private String certificateAnnexFileCode;
	private String companyType;
	private String state;
	private String legalPerson;
	private String legalCertificateNo;
	private String selProvince;
	private String selCity;
	private String selArea;
	private String taxRegistrationNumber;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;
	private String logoCode;
	private Integer mscId;
	private Integer swcId;
	private Integer commId;
	private String mscType;
	private Integer serialComm;
	private String content;
	private String commState;
	private String pyName;
	
	private String logoFilePath;
	// Constructors

	/** default constructor */
	public MdCommentSupplierView() {
	}

	/** minimal constructor */
	public MdCommentSupplierView(Integer mscId) {
		this.mscId = mscId;
	}

	/** full constructor */
	public MdCommentSupplierView(Integer wzId, Integer orgGxId,
			String phoneNumber, String fullName, String mailbox,
			String education, Date graduationTime,
			String businessIntroduction, String applicantName,
			String corporateDomicile, Double registeredCapital,
			String enterpriseType, String scopeBusiness, String licenseNumber,
			String certificateAnnexFileCode, String companyType, String state,
			String legalPerson, String legalCertificateNo, String selProvince,
			String selCity, String selArea, String taxRegistrationNumber,
			String createRen, Date createDate, String editRen,
			Date editDate, String logoCode, Integer mscId, Integer swcId,
			Integer commId, String mscType, Integer serialComm, String content,
			String commState) {
		this.wzId = wzId;
		this.orgGxId = orgGxId;
		this.phoneNumber = phoneNumber;
		this.fullName = fullName;
		this.mailbox = mailbox;
		this.education = education;
		this.graduationTime = graduationTime;
		this.businessIntroduction = businessIntroduction;
		this.applicantName = applicantName;
		this.corporateDomicile = corporateDomicile;
		this.registeredCapital = registeredCapital;
		this.enterpriseType = enterpriseType;
		this.scopeBusiness = scopeBusiness;
		this.licenseNumber = licenseNumber;
		this.certificateAnnexFileCode = certificateAnnexFileCode;
		this.companyType = companyType;
		this.state = state;
		this.legalPerson = legalPerson;
		this.legalCertificateNo = legalCertificateNo;
		this.selProvince = selProvince;
		this.selCity = selCity;
		this.selArea = selArea;
		this.taxRegistrationNumber = taxRegistrationNumber;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
		this.logoCode = logoCode;
		this.mscId = mscId;
		this.swcId = swcId;
		this.commId = commId;
		this.mscType = mscType;
		this.serialComm = serialComm;
		this.content = content;
		this.commState = commState;
	}

	// Property accessors
	@Id
	@Column(name = "msc_id", nullable = false)
	public Integer getMscId() {
		return this.mscId;
	}

	public void setMscId(Integer mscId) {
		this.mscId = mscId;
	}
	@Column(name = "wz_id", nullable = false)
	public Integer getWzId() {
		return this.wzId;
	}

	public void setWzId(Integer wzId) {
		this.wzId = wzId;
	}

	@Column(name = "org_gx_id")
	public Integer getOrgGxId() {
		return this.orgGxId;
	}

	public void setOrgGxId(Integer orgGxId) {
		this.orgGxId = orgGxId;
	}

	@Column(name = "Phone_number", length = 32)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "Full_name", length = 64)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "mailbox", length = 64)
	public String getMailbox() {
		return this.mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	@Column(name = "Education", length = 32)
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "Graduation_time", length = 0)
	public Date getGraduationTime() {
		return this.graduationTime;
	}

	public void setGraduationTime(Date graduationTime) {
		this.graduationTime = graduationTime;
	}

	@Column(name = "Business_introduction", length = 1024)
	public String getBusinessIntroduction() {
		return this.businessIntroduction;
	}

	public void setBusinessIntroduction(String businessIntroduction) {
		this.businessIntroduction = businessIntroduction;
	}

	@Column(name = "applicant_Name", length = 64)
	public String getApplicantName() {
		return this.applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	@Column(name = "Corporate_domicile", length = 64)
	public String getCorporateDomicile() {
		return this.corporateDomicile;
	}

	public void setCorporateDomicile(String corporateDomicile) {
		this.corporateDomicile = corporateDomicile;
	}

	@Column(name = "registered_capital", precision = 10)
	public Double getRegisteredCapital() {
		return this.registeredCapital;
	}

	public void setRegisteredCapital(Double registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	@Column(name = "enterprise_type", length = 32)
	public String getEnterpriseType() {
		return this.enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	@Column(name = "scope_business", length = 400)
	public String getScopeBusiness() {
		return this.scopeBusiness;
	}

	public void setScopeBusiness(String scopeBusiness) {
		this.scopeBusiness = scopeBusiness;
	}

	@Column(name = "License_number", length = 32)
	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	@Column(name = "certificate_annex_fileCode", length = 32)
	public String getCertificateAnnexFileCode() {
		return this.certificateAnnexFileCode;
	}

	public void setCertificateAnnexFileCode(String certificateAnnexFileCode) {
		this.certificateAnnexFileCode = certificateAnnexFileCode;
	}

	@Column(name = "company_type", length = 16)
	public String getCompanyType() {
		return this.companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	@Column(name = "state", length = 32)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "legal_person", length = 32)
	public String getLegalPerson() {
		return this.legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	@Column(name = "legal_Certificate_No", length = 64)
	public String getLegalCertificateNo() {
		return this.legalCertificateNo;
	}

	public void setLegalCertificateNo(String legalCertificateNo) {
		this.legalCertificateNo = legalCertificateNo;
	}

	@Column(name = "selProvince", length = 32)
	public String getSelProvince() {
		return this.selProvince;
	}

	public void setSelProvince(String selProvince) {
		this.selProvince = selProvince;
	}

	@Column(name = "selCity", length = 32)
	public String getSelCity() {
		return this.selCity;
	}

	public void setSelCity(String selCity) {
		this.selCity = selCity;
	}

	@Column(name = "selArea", length = 32)
	public String getSelArea() {
		return this.selArea;
	}

	public void setSelArea(String selArea) {
		this.selArea = selArea;
	}

	@Column(name = "tax_registration_number", length = 32)
	public String getTaxRegistrationNumber() {
		return this.taxRegistrationNumber;
	}

	public void setTaxRegistrationNumber(String taxRegistrationNumber) {
		this.taxRegistrationNumber = taxRegistrationNumber;
	}

	@Column(name = "create_ren", length = 512)
	public String getCreateRen() {
		return this.createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "edit_ren", length = 512)
	public String getEditRen() {
		return this.editRen;
	}

	public void setEditRen(String editRen) {
		this.editRen = editRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_date", length = 0)
	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	@Column(name = "logo_code", length = 32)
	public String getLogoCode() {
		return this.logoCode;
	}

	public void setLogoCode(String logoCode) {
		this.logoCode = logoCode;
	}

	@Column(name = "swc_id")
	public Integer getSwcId() {
		return this.swcId;
	}

	public void setSwcId(Integer swcId) {
		this.swcId = swcId;
	}

	@Column(name = "comm_id")
	public Integer getCommId() {
		return this.commId;
	}

	public void setCommId(Integer commId) {
		this.commId = commId;
	}

	@Column(name = "msc_type", length = 10)
	public String getMscType() {
		return this.mscType;
	}

	public void setMscType(String mscType) {
		this.mscType = mscType;
	}

	@Column(name = "serial_comm")
	public Integer getSerialComm() {
		return this.serialComm;
	}

	public void setSerialComm(Integer serialComm) {
		this.serialComm = serialComm;
	}

	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "comm_state", length = 32)
	public String getCommState() {
		return this.commState;
	}

	public void setCommState(String commState) {
		this.commState = commState;
	}
	@Column(name = "py_name", length = 100)
	public String getPyName() {
		return pyName;
	}

	public void setPyName(String pyName) {
		this.pyName = pyName;
	}
	@Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code= logo_code)")
	public String getLogoFilePath() {
		return logoFilePath;
	}

	public void setLogoFilePath(String logoFilePath) {
		this.logoFilePath = logoFilePath;
	}
}