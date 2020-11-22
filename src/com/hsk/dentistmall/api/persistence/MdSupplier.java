package com.hsk.dentistmall.api.persistence;

import javax.persistence.*; 

import org.hibernate.annotations.Formula;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

/**
 * 根据md_supplier表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdSupplier</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table> 
 * <tr><th>字段名称:</th><td>供应商id(wzId)</td><th>字段类型:</th><td>Integer(主键)</td></tr> 
 * <tr><th>字段名称:</th><td>组织机构关系id(orgGxId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>法人手机号码(phoneNumber)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>法人姓名(fullName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>法人邮箱(mailbox)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>法人学历(education)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>法人毕业时间(graduationTime)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>个人和创业简介(businessIntroduction)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 *  * <tr><th>字段名称:</th><td>企业编号(legalCertificateNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>企业名称(applicantName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>企业住所地(corporateDomicile)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>注册资本(registeredCapital)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>企业类型(enterpriseType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>经营范围(scopeBusiness)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>证照号码(licenseNumber)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>三证附件(certificateAnnexFilecode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>公司类型(0表示未注册公司，1表示已注册公司)(companyType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>公司状态(state)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>投资人(legalPerson)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 

 * <tr><th>字段名称:</th><td>所属省(selprovince)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>所属市(selcity)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>所属区(selarea)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>税务登记号码(taxRegistrationNumber)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * </table>
 *===============无关字段属性===============
 *  <table> 
 * 	<tr><th>字段名称:</th><td>供应商id(wzId)	</td><th>属性名称:</th><td>wzId</td></tr>
 * 	<tr><th>字段名称:</th><td>组织机构关系id(orgGxId)	</td><th>属性名称:</th><td>orgGxId</td></tr>
 * 	<tr><th>字段名称:</th><td>法人毕业时间(graduationTime)	</td><th>属性名称:</th><td>graduationTime</td></tr>
 * 	<tr><th>字段名称:</th><td>法人毕业时间(graduationTime)	</td><th>属性名称:</th><td>graduationTime</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 * @author  作者:admin  
 * @version  v1.0 创建时间: 2017-09-25 14:17:00
 */
 
 
@SuppressWarnings("serial")
@Entity
@Table(name = "md_supplier" )
public class MdSupplier {
///===============数据库表字段属性begin==========
			/**
			 *供应商id(wzId):字段类型为Integer  
			 */
 			private Integer wzId; 
 	
			/**
			 *组织机构关系id(orgGxId):字段类型为Integer  
			 */
 			private Integer orgGxId; 
 			
 			private String customs;
 	
			/**
			 *法人手机号码(phoneNumber):字段类型为String  
			 */
 			private String phoneNumber; 
 	
			/**
			 *法人姓名(fullName):字段类型为String  
			 */
 			private String fullName; 
 	
			/**
			 *法人邮箱(mailbox):字段类型为String  
			 */
 			private String mailbox; 
 	
			/**
			 *法人学历(education):字段类型为String  
			 */
 			private String education; 
 	
			/**
			 *法人毕业时间(graduationTime):字段类型为Date  
			 */
 			private Date graduationTime; 
 	
			/**
			 *个人和创业简介(businessIntroduction):字段类型为String  
			 */
 			private String businessIntroduction; 
 	
			/**
			 *企业名称(applicantName):字段类型为String  
			 */
 			private String applicantName; 
 			/**
 			 * 名称拼音
 			 */
 			private String pyName;
 			/**
 			 * 企业logo
 			 */
 			private String logoCode;
 	
			/**
			 *企业住所地(corporateDomicile):字段类型为String  
			 */
 			private String corporateDomicile; 
 	
			/**
			 *注册资本(registeredCapital):字段类型为Double  
			 */
 			private Double registeredCapital; 
 	
			/**
			 *企业类型(enterpriseType):字段类型为String  
			 */
 			private String enterpriseType; 
 	
			/**
			 *经营范围(scopeBusiness):字段类型为String  
			 */
 			private String scopeBusiness; 
 	
			/**
			 *证照号码(licenseNumber):字段类型为String  
			 */
 			private String licenseNumber; 
 	
			/**
			 *三证附件(certificateAnnexFilecode):字段类型为String  
			 */
 			private String certificateAnnexFilecode; 
 	
			/**
			 *公司类型(0表示未注册公司，1表示已注册公司)(companyType):字段类型为String  
			 */
 			private String companyType; 
 	
			/**
			 *公司状态(state):字段类型为String  
			 */
 			private String state; 
 	
			/**
			 *投资人(legalPerson):字段类型为String  
			 */
 			private String legalPerson; 
 	
			/**
			 *公司编号(legalCertificateNo):字段类型为String  
			 */
 			private String legalCertificateNo; 
 	
			/**
			 *所属省(selprovince):字段类型为String  
			 */
 			private String selprovince; 
 	
			/**
			 *所属市(selcity):字段类型为String  
			 */
 			private String selcity; 
 	
			/**
			 *所属区(selarea):字段类型为String  
			 */
 			private String selarea; 
 	
			/**
			 *税务登记号码(taxRegistrationNumber):字段类型为String  
			 */
 			private String taxRegistrationNumber; 
 			
 			private Double noExpressMoney;
 			
 			private Double expressMoney;
 	
			/**
			 *创建人(createRen):字段类型为String  
			 */
 			private String createRen; 
 	
			/**
			 *创建时间(createDate):字段类型为Date  
			 */
 			private Date createDate; 
 	
			/**
			 *修改人(editRen):字段类型为String  
			 */
 			private String editRen; 
 	
			/**
			 *修改时间(editDate):字段类型为Date  
			 */
 			private Date editDate;

	private String salesmanIds;
	private String salesName;
	private Date salesmanTime;

	/**
			 *设置供应商id(wz_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdSupplier.WzId</b>的值
			 */	 
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "wz_id")
			public Integer getWzId(){
			    return this.wzId;
			}

		  /**
		   *设置 wz_id字段方法 
		   *@param att_wzId 输入<b>MdSupplier.wzId</b>字段的值
		   */
		  public void setWzId(Integer att_wzId){
		    this.wzId = att_wzId;
		  }
  
			/**
			 *设置组织机构关系id(org_gx_id)字段方法 
			 *@return  返回 <b>MdSupplier.OrgGxId</b>的值
			 */	 
			@Column(name = "org_gx_id" ) 
			public Integer getOrgGxId(){
			    return this.orgGxId;
			}

		  /**
		   *设置 org_gx_id字段方法 
		   *@param att_orgGxId 输入<b>MdSupplier.orgGxId</b>字段的值
		   */
		  public void setOrgGxId(Integer att_orgGxId){
		    this.orgGxId = att_orgGxId;
		  }
  
			/**
			 *设置法人手机号码(Phone_number)字段方法 
			 *@return  返回 <b>MdSupplier.PhoneNumber</b>的值
			 */	 
			@Column(name = "Phone_number" ) 
			public String getPhoneNumber(){
			    return this.phoneNumber;
			}

		  /**
		   *设置 Phone_number字段方法 
		   *@param att_phoneNumber 输入<b>MdSupplier.phoneNumber</b>字段的值
		   */
		  public void setPhoneNumber(String att_phoneNumber){
		    this.phoneNumber = att_phoneNumber;
		  }
		  
		  @Column(name = "customs" )
			public String getCustoms() {
				return customs;
			}
	
			public void setCustoms(String customs) {
				this.customs = customs;
			}

			/**
			 *设置法人姓名(Full_name)字段方法 
			 *@return  返回 <b>MdSupplier.FullName</b>的值
			 */	 
			@Column(name = "Full_name" ) 
			public String getFullName(){
			    return this.fullName;
			}

		  /**
		   *设置 Full_name字段方法 
		   *@param att_fullName 输入<b>MdSupplier.fullName</b>字段的值
		   */
		  public void setFullName(String att_fullName){
		    this.fullName = att_fullName;
		  }
  
			/**
			 *设置法人邮箱(mailbox)字段方法 
			 *@return  返回 <b>MdSupplier.Mailbox</b>的值
			 */	 
			@Column(name = "mailbox" ) 
			public String getMailbox(){
			    return this.mailbox;
			}

		  /**
		   *设置 mailbox字段方法 
		   *@param att_mailbox 输入<b>MdSupplier.mailbox</b>字段的值
		   */
		  public void setMailbox(String att_mailbox){
		    this.mailbox = att_mailbox;
		  }
  
			/**
			 *设置法人学历(Education)字段方法 
			 *@return  返回 <b>MdSupplier.Education</b>的值
			 */	 
			@Column(name = "Education" ) 
			public String getEducation(){
			    return this.education;
			}

		  /**
		   *设置 Education字段方法 
		   *@param att_education 输入<b>MdSupplier.education</b>字段的值
		   */
		  public void setEducation(String att_education){
		    this.education = att_education;
		  }
  
			/**
			 *设置法人毕业时间(Graduation_time)字段方法 
			 *@return  返回 <b>MdSupplier.GraduationTime</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "Graduation_time" ) 
			public Date getGraduationTime(){
			    return this.graduationTime;
			}

		  /**
		   *设置 Graduation_time字段方法 
		   *@param att_graduationTime 输入<b>MdSupplier.graduationTime</b>字段的值
		   */
		  public void setGraduationTime(Date att_graduationTime){
		    this.graduationTime = att_graduationTime;
		  }
  
			/**
			 *设置个人和创业简介(Business_introduction)字段方法 
			 *@return  返回 <b>MdSupplier.BusinessIntroduction</b>的值
			 */	 
			@Column(name = "Business_introduction" ) 
			public String getBusinessIntroduction(){
			    return this.businessIntroduction;
			}

		  /**
		   *设置 Business_introduction字段方法 
		   *@param att_businessIntroduction 输入<b>MdSupplier.businessIntroduction</b>字段的值
		   */
		  public void setBusinessIntroduction(String att_businessIntroduction){
		    this.businessIntroduction = att_businessIntroduction;
		  }
  
			/**
			 *设置企业名称(applicant_Name)字段方法 
			 *@return  返回 <b>MdSupplier.ApplicantName</b>的值
			 */	 
			@Column(name = "applicant_Name" ) 
			public String getApplicantName(){
			    return this.applicantName;
			}

		  /**
		   *设置 applicant_Name字段方法 
		   *@param att_applicantName 输入<b>MdSupplier.applicantName</b>字段的值
		   */
		  public void setApplicantName(String att_applicantName){
		    this.applicantName = att_applicantName;
		  }
		  
		  @Column(name = "py_name" ) 
		  public String getPyName() {
				return pyName;
			}
	
			public void setPyName(String pyName) {
				this.pyName = pyName;
			}

		/**
			 *设置企业logo(logo_code)字段方法 
			 *@return  返回 <b>MdSupplier.ApplicantName</b>的值
			 */	 
			@Column(name = "logo_code" ) 
			public String getLogoCode() {
				return logoCode;
			}
	
			public void setLogoCode(String logoCode) {
				this.logoCode = logoCode;
			}

			/**
			 *设置企业住所地(Corporate_domicile)字段方法 
			 *@return  返回 <b>MdSupplier.CorporateDomicile</b>的值
			 */	 
			@Column(name = "Corporate_domicile" ) 
			public String getCorporateDomicile(){
			    return this.corporateDomicile;
			}

		  /**
		   *设置 Corporate_domicile字段方法 
		   *@param att_corporateDomicile 输入<b>MdSupplier.corporateDomicile</b>字段的值
		   */
		  public void setCorporateDomicile(String att_corporateDomicile){
		    this.corporateDomicile = att_corporateDomicile;
		  }
  
			/**
			 *设置注册资本(registered_capital)字段方法 
			 *@return  返回 <b>MdSupplier.RegisteredCapital</b>的值
			 */	 
			@Column(name = "registered_capital" ) 
			public Double getRegisteredCapital(){
			    return this.registeredCapital;
			}

		  /**
		   *设置 registered_capital字段方法 
		   *@param att_registeredCapital 输入<b>MdSupplier.registeredCapital</b>字段的值
		   */
		  public void setRegisteredCapital(Double att_registeredCapital){
		    this.registeredCapital = att_registeredCapital;
		  }
  
			/**
			 *设置企业类型(enterprise_type)字段方法 
			 *@return  返回 <b>MdSupplier.EnterpriseType</b>的值
			 */	 
			@Column(name = "enterprise_type" ) 
			public String getEnterpriseType(){
			    return this.enterpriseType;
			}

		  /**
		   *设置 enterprise_type字段方法 
		   *@param att_enterpriseType 输入<b>MdSupplier.enterpriseType</b>字段的值
		   */
		  public void setEnterpriseType(String att_enterpriseType){
		    this.enterpriseType = att_enterpriseType;
		  }
  
			/**
			 *设置经营范围(scope_business)字段方法 
			 *@return  返回 <b>MdSupplier.ScopeBusiness</b>的值
			 */	 
			@Column(name = "scope_business" ) 
			public String getScopeBusiness(){
			    return this.scopeBusiness;
			}

		  /**
		   *设置 scope_business字段方法 
		   *@param att_scopeBusiness 输入<b>MdSupplier.scopeBusiness</b>字段的值
		   */
		  public void setScopeBusiness(String att_scopeBusiness){
		    this.scopeBusiness = att_scopeBusiness;
		  }
  
			/**
			 *设置证照号码(License_number)字段方法 
			 *@return  返回 <b>MdSupplier.LicenseNumber</b>的值
			 */	 
			@Column(name = "License_number" ) 
			public String getLicenseNumber(){
			    return this.licenseNumber;
			}

		  /**
		   *设置 License_number字段方法 
		   *@param att_licenseNumber 输入<b>MdSupplier.licenseNumber</b>字段的值
		   */
		  public void setLicenseNumber(String att_licenseNumber){
		    this.licenseNumber = att_licenseNumber;
		  }
  
			/**
			 *设置三证附件(certificate_annex_fileCode)字段方法 
			 *@return  返回 <b>MdSupplier.CertificateAnnexFilecode</b>的值
			 */	 
			@Column(name = "certificate_annex_fileCode" ) 
			public String getCertificateAnnexFilecode(){
			    return this.certificateAnnexFilecode;
			}

		  /**
		   *设置 certificate_annex_fileCode字段方法 
		   *@param att_certificateAnnexFilecode 输入<b>MdSupplier.certificateAnnexFilecode</b>字段的值
		   */
		  public void setCertificateAnnexFilecode(String att_certificateAnnexFilecode){
		    this.certificateAnnexFilecode = att_certificateAnnexFilecode;
		  }
  
			/**
			 *设置公司类型(0表示未注册公司，1表示已注册公司)(company_type)字段方法 
			 *@return  返回 <b>MdSupplier.CompanyType</b>的值
			 */	 
			@Column(name = "company_type" ) 
			public String getCompanyType(){
			    return this.companyType;
			}

		  /**
		   *设置 company_type字段方法 
		   *@param att_companyType 输入<b>MdSupplier.companyType</b>字段的值
		   */
		  public void setCompanyType(String att_companyType){
		    this.companyType = att_companyType;
		  }
  
			/**
			 *设置公司状态(state)字段方法 
			 *@return  返回 <b>MdSupplier.State</b>的值
			 */	 
			@Column(name = "state" ) 
			public String getState(){
			    return this.state;
			}

		  /**
		   *设置 state字段方法 
		   *@param att_state 输入<b>MdSupplier.state</b>字段的值
		   */
		  public void setState(String att_state){
		    this.state = att_state;
		  }
  
			/**
			 *设置投资人(legal_person)字段方法 
			 *@return  返回 <b>MdSupplier.LegalPerson</b>的值
			 */	 
			@Column(name = "legal_person" ) 
			public String getLegalPerson(){
			    return this.legalPerson;
			}

		  /**
		   *设置 legal_person字段方法 
		   *@param att_legalPerson 输入<b>MdSupplier.legalPerson</b>字段的值
		   */
		  public void setLegalPerson(String att_legalPerson){
		    this.legalPerson = att_legalPerson;
		  }
  
			/**
			 *设置投资人证件号(legal_Certificate_No)字段方法 
			 *@return  返回 <b>MdSupplier.LegalCertificateNo</b>的值
			 */	 
			@Column(name = "legal_Certificate_No" ) 
			public String getLegalCertificateNo(){
			    return this.legalCertificateNo;
			}

		  /**
		   *设置 legal_Certificate_No字段方法 
		   *@param att_legalCertificateNo 输入<b>MdSupplier.legalCertificateNo</b>字段的值
		   */
		  public void setLegalCertificateNo(String att_legalCertificateNo){
		    this.legalCertificateNo = att_legalCertificateNo;
		  }
  
			/**
			 *设置所属省(selProvince)字段方法 
			 *@return  返回 <b>MdSupplier.Selprovince</b>的值
			 */	 
			@Column(name = "selProvince" ) 
			public String getSelprovince(){
			    return this.selprovince;
			}

		  /**
		   *设置 selProvince字段方法 
		   *@param att_selprovince 输入<b>MdSupplier.selprovince</b>字段的值
		   */
		  public void setSelprovince(String att_selprovince){
		    this.selprovince = att_selprovince;
		  }
  
			/**
			 *设置所属市(selCity)字段方法 
			 *@return  返回 <b>MdSupplier.Selcity</b>的值
			 */	 
			@Column(name = "selCity" ) 
			public String getSelcity(){
			    return this.selcity;
			}

		  /**
		   *设置 selCity字段方法 
		   *@param att_selcity 输入<b>MdSupplier.selcity</b>字段的值
		   */
		  public void setSelcity(String att_selcity){
		    this.selcity = att_selcity;
		  }
  
			/**
			 *设置所属区(selArea)字段方法 
			 *@return  返回 <b>MdSupplier.Selarea</b>的值
			 */	 
			@Column(name = "selArea" ) 
			public String getSelarea(){
			    return this.selarea;
			}

		  /**
		   *设置 selArea字段方法 
		   *@param att_selarea 输入<b>MdSupplier.selarea</b>字段的值
		   */
		  public void setSelarea(String att_selarea){
		    this.selarea = att_selarea;
		  }
  
			/**
			 *设置税务登记号码(tax_registration_number)字段方法 
			 *@return  返回 <b>MdSupplier.TaxRegistrationNumber</b>的值
			 */	 
			@Column(name = "tax_registration_number" ) 
			public String getTaxRegistrationNumber(){
			    return this.taxRegistrationNumber;
			}

		  /**
		   *设置 tax_registration_number字段方法 
		   *@param att_taxRegistrationNumber 输入<b>MdSupplier.taxRegistrationNumber</b>字段的值
		   */
		  public void setTaxRegistrationNumber(String att_taxRegistrationNumber){
		    this.taxRegistrationNumber = att_taxRegistrationNumber;
		  }
		  
		    @Column(name = "no_express_money" ) 
			public Double getNoExpressMoney() {
				return noExpressMoney;
			}
	
			public void setNoExpressMoney(Double noExpressMoney) {
				this.noExpressMoney = noExpressMoney;
			}
			@Column(name = "express_money" ) 
			public Double getExpressMoney() {
				return expressMoney;
			}
	
			public void setExpressMoney(Double expressMoney) {
				this.expressMoney = expressMoney;
			}

			/**
			 *设置创建人(create_ren)字段方法 
			 *@return  返回 <b>MdSupplier.CreateRen</b>的值
			 */	 
			@Column(name = "create_ren" ) 
			public String getCreateRen(){
			    return this.createRen;
			}

		  /**
		   *设置 create_ren字段方法 
		   *@param att_createRen 输入<b>MdSupplier.createRen</b>字段的值
		   */
		  public void setCreateRen(String att_createRen){
		    this.createRen = att_createRen;
		  }
  
			/**
			 *设置创建时间(create_date)字段方法 
			 *@return  返回 <b>MdSupplier.CreateDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "create_date" ) 
			public Date getCreateDate(){
			    return this.createDate;
			}

		  /**
		   *设置 create_date字段方法 
		   *@param att_createDate 输入<b>MdSupplier.createDate</b>字段的值
		   */
		  public void setCreateDate(Date att_createDate){
		    this.createDate = att_createDate;
		  }
  
			/**
			 *设置修改人(edit_ren)字段方法 
			 *@return  返回 <b>MdSupplier.EditRen</b>的值
			 */	 
			@Column(name = "edit_ren" ) 
			public String getEditRen(){
			    return this.editRen;
			}

		  /**
		   *设置 edit_ren字段方法 
		   *@param att_editRen 输入<b>MdSupplier.editRen</b>字段的值
		   */
		  public void setEditRen(String att_editRen){
		    this.editRen = att_editRen;
		  }
  
			/**
			 *设置修改时间(edit_date)字段方法 
			 *@return  返回 <b>MdSupplier.EditDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "edit_date" ) 
			public Date getEditDate(){
			    return this.editDate;
			}

		  /**
		   *设置 edit_date字段方法 
		   *@param att_editDate 输入<b>MdSupplier.editDate</b>字段的值
		   */
		  public void setEditDate(Date att_editDate){
		    this.editDate = att_editDate;
		  }
		  

///===============数据库表字段属性end==========

///===============数据库表无关子段字段属性begin==========
			/**
			 *供应商id(wz_id):字段类型为String
			 */
		  private String wzId_str;  
			/**
			 *组织机构关系id(org_gx_id):字段类型为String
			 */
		  private String orgGxId_str;  
			/**
			 *法人毕业时间(Graduation_time):字段类型为Date
			 */
		  private Date graduationTime_start;  
			/**
			 *法人毕业时间(Graduation_time):字段类型为Date
			 */
		  private Date graduationTime_end;  
			/**
			 *创建时间(create_date):字段类型为Date
			 */
		  private Date createDate_start;  
			/**
			 *创建时间(create_date):字段类型为Date
			 */
		  private Date createDate_end;  
			/**
			 *修改时间(edit_date):字段类型为Date
			 */
		  private Date editDate_start;  
			/**
			 *修改时间(edit_date):字段类型为Date
			 */
		  private Date editDate_end;  
			/**
			 *():字段类型为String
			 *md_supplier表里不用like作为条件的字符串
			 */
		  private String Tab_like;  
			/**
			 *():字段类型为String
			 *md_supplier表里order by 字符串
			 */
		  private String Tab_order;  
			/**
			 *设置wzId字段方法  
			 *@return  返回 <b>MdSupplier.wzId</b>的值
			 */ 
			@Transient
			public String getWzId_str(){
				return this.wzId_str;
			}
			/**
			  *设置 wz_id字段方法 
			  *@param att_wzId 输入<b>MdSupplier.wzId</b>字段的值
			  */
			public void setWzId_str(String att_wzId_str){
				this.wzId_str = att_wzId_str;
			}
			/**
			 *设置orgGxId字段方法  
			 *@return  返回 <b>MdSupplier.orgGxId</b>的值
			 */ 
			@Transient
			public String getOrgGxId_str(){
				return this.orgGxId_str;
			}
			/**
			  *设置 org_gx_id字段方法 
			  *@param att_orgGxId 输入<b>MdSupplier.orgGxId</b>字段的值
			  */
			public void setOrgGxId_str(String att_orgGxId_str){
				this.orgGxId_str = att_orgGxId_str;
			}
			/**
			 *设置graduationTime字段方法  
			 *@return  返回 <b>MdSupplier.graduationTime</b>的值
			 */ 
			@Transient
			public Date getGraduationTime_start(){
				return this.graduationTime_start;
			}
			/**
			  *设置 Graduation_time字段方法 
			  *@param att_graduationTime 输入<b>MdSupplier.graduationTime</b>字段的值
			  */
			public void setGraduationTime_start(Date att_graduationTime_start){
				this.graduationTime_start = att_graduationTime_start;
			}
			/**
			 *设置graduationTime字段方法  
			 *@return  返回 <b>MdSupplier.graduationTime</b>的值
			 */ 
			@Transient
			public Date getGraduationTime_end(){
				return this.graduationTime_end;
			}
			/**
			  *设置 Graduation_time字段方法 
			  *@param att_graduationTime 输入<b>MdSupplier.graduationTime</b>字段的值
			  */
			public void setGraduationTime_end(Date att_graduationTime_end){
				this.graduationTime_end = att_graduationTime_end;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdSupplier.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_start(){
				return this.createDate_start;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdSupplier.createDate</b>字段的值
			  */
			public void setCreateDate_start(Date att_createDate_start){
				this.createDate_start = att_createDate_start;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdSupplier.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_end(){
				return this.createDate_end;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdSupplier.createDate</b>字段的值
			  */
			public void setCreateDate_end(Date att_createDate_end){
				this.createDate_end = att_createDate_end;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdSupplier.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_start(){
				return this.editDate_start;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdSupplier.editDate</b>字段的值
			  */
			public void setEditDate_start(Date att_editDate_start){
				this.editDate_start = att_editDate_start;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdSupplier.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_end(){
				return this.editDate_end;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdSupplier.editDate</b>字段的值
			  */
			public void setEditDate_end(Date att_editDate_end){
				this.editDate_end = att_editDate_end;
			}
			/**
			 *设置tab_like字段方法  
			 *@return  返回 <b>MdSupplier.tab_like</b>的值
			 */ 
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_like 输入<b>MdSupplier.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法  
			 *@return  返回 <b>MdSupplier.tab_order</b>的值
			 */ 
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_order 输入<b>MdSupplier.tab_order</b>字段的值
			  */
			public void setTab_order(String att_Tab_order){
				this.Tab_order = att_Tab_order;
			}
///===============数据库表无关子段字段属性end==========
			private String logoFilePath;
			private String state_str;
			
			@Transient
			public String getState_str() {
				return state_str;
			}

			public void setState_str(String state_str) {
				this.state_str = state_str;
			}

			@Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code= logo_code)")
			public String getLogoFilePath() {
				return logoFilePath;
			}

			public void setLogoFilePath(String logoFilePath) {
				this.logoFilePath = logoFilePath;
			}


	@Column(name = "salesman_ids")
	public String getSalesmanIds() {
		return salesmanIds;
	}

	public void setSalesmanIds(String salesmanIds) {
		this.salesmanIds = salesmanIds;
	}

	@Column(name = "sales_name")
	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

    public void setSalesmanTime(Date salesmanTime) {
        this.salesmanTime = salesmanTime;
    }
	@Column(name = "sales_time")
    public Date getSalesmanTime() {
        return salesmanTime;
    }
}