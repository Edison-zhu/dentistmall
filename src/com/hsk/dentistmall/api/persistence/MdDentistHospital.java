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

import org.hibernate.annotations.Formula;

/**
 * 根据md_dentist_hospital表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdDentistHospital</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table> 
 * <tr><th>字段名称:</th><td>牙医医院id(rbsId)</td><th>字段类型:</th><td>Integer(主键)</td></tr> 
 * <tr><th>字段名称:</th><td>集团公司id(rbaId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>牙医医院编号(rbsCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>牙医医院名(rbsName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>牙医医院行业(industry)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>牙医医院地址(address)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>省(province)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>市(city)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>地区(area)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>所有人(legalPerson)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>所有人电话(legalPhone)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>所有人法人身份证(legalCertificateNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>联系人(connUser)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>联系人电话(connPhone)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>联系人法人身份证(connCertificateNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>营业执照(businessNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>营业执照文件(businessFile)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>负责人身份证(personCertificateNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>负责人身份证文件(personCertificateFile)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>组织机构代码证(organizationNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>组织机构代码证文件(organizationFile)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>账号类型(bankType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>开户人(bankUser)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>开户银行(bankName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>商户额度(quota)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>提现方式(cashType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>流程状态(flowState)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>状态:1正常，2删除(state)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>银行账号(bankCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>申请人(applyUserId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * </table>
 *===============无关字段属性===============
 *  <table> 
 * 	<tr><th>字段名称:</th><td>牙医医院id(rbsId)	</td><th>属性名称:</th><td>rbsId</td></tr>
 * 	<tr><th>字段名称:</th><td>集团公司id(rbaId)	</td><th>属性名称:</th><td>rbaId</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>流程状态(flowState)	</td><th>属性名称:</th><td>flowState</td></tr>
 * 	<tr><th>字段名称:</th><td>状态:1正常，2删除(state)	</td><th>属性名称:</th><td>state</td></tr>
 * 	<tr><th>字段名称:</th><td>申请人(applyUserId)	</td><th>属性名称:</th><td>applyUserId</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 * @author  作者:admin  
 * @version  v1.0 创建时间: 2017-09-25 14:21:17
 */
 
 
@SuppressWarnings("serial")
@Entity
@Table(name = "md_dentist_hospital" )
public class MdDentistHospital {
///===============数据库表字段属性begin==========
			/**
			 *牙医医院id(rbsId):字段类型为Integer  
			 */
 			private Integer rbsId; 
 	
			/**
			 *集团公司id(rbaId):字段类型为Integer  
			 */
 			private Integer rbaId; 
 	
			/**
			 *牙医医院编号(rbsCode):字段类型为String  
			 */
 			private String rbsCode; 
 	
			/**
			 *牙医医院名(rbsName):字段类型为String  
			 */
 			private String rbsName; 
 	
			/**
			 *牙医医院行业(industry):字段类型为String  
			 */
 			private String industry; 
 	
			/**
			 *牙医医院地址(address):字段类型为String  
			 */
 			private String address; 
 	
			/**
			 *省(province):字段类型为String  
			 */
 			private String province; 
 	
			/**
			 *市(city):字段类型为String  
			 */
 			private String city; 
 	
			/**
			 *地区(area):字段类型为String  
			 */
 			private String area; 
 	
			/**
			 *所有人(legalPerson):字段类型为String  
			 */
 			private String legalPerson; 
 	
			/**
			 *所有人电话(legalPhone):字段类型为String  
			 */
 			private String legalPhone; 
 	
			/**
			 *所有人法人身份证(legalCertificateNo):字段类型为String  
			 */
 			private String legalCertificateNo; 
 	
			/**
			 *联系人(connUser):字段类型为String  
			 */
 			private String connUser; 
 	
			/**
			 *联系人电话(connPhone):字段类型为String  
			 */
 			private String connPhone; 
 	
			/**
			 *联系人法人身份证(connCertificateNo):字段类型为String  
			 */
 			private String connCertificateNo; 
 	
			/**
			 *营业执照(businessNo):字段类型为String  
			 */
 			private String businessNo; 
 	
			/**
			 *营业执照文件(businessFile):字段类型为String  
			 */
 			private String businessFile; 
 	
			/**
			 *负责人身份证(personCertificateNo):字段类型为String  
			 */
 			private String personCertificateNo; 
 	
			/**
			 *负责人身份证文件(personCertificateFile):字段类型为String  
			 */
 			private String personCertificateFile; 
 	
			/**
			 *组织机构代码证(organizationNo):字段类型为String  
			 */
 			private String organizationNo; 
 	
			/**
			 *组织机构代码证文件(organizationFile):字段类型为String  
			 */
 			private String organizationFile; 
 	
			/**
			 *账号类型(bankType):字段类型为String  
			 */
 			private String bankType; 
 	
			/**
			 *开户人(bankUser):字段类型为String  
			 */
 			private String bankUser; 
 	
			/**
			 *开户银行(bankName):字段类型为String  
			 */
 			private String bankName; 
 	
			/**
			 *商户额度(quota):字段类型为Double  
			 */
 			private Double quota; 
 	
			/**
			 *提现方式(cashType):字段类型为String  
			 */
 			private String cashType; 
 	
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
 	
			/**
			 *流程状态(flowState):字段类型为Integer  
			 */
 			private Integer flowState; 
 	
			/**
			 *状态:1正常，2删除(state):字段类型为Integer  
			 */
 			private Integer state; 
 	
			/**
			 *银行账号(bankCode):字段类型为String  
			 */
 			private String bankCode; 
 	
			/**
			 *申请人(applyUserId):字段类型为Integer  
			 */
 			private Integer applyUserId; 

 			private String salesmanIds;
 			
 			/**
 			 * 集团名称
 			 */
 			private String rbaName;
 			
 			/**
			 *备注(remark):字段类型为String  
			 */
 			private String remark;
	private String salesName;
	private String salesPhone;
	private Integer verifyState;
	private String verifyRemark;
	private String logo;

	@Formula("(SELECT a.rba_name FROM md_company_group a  WHERE a.rba_id=rba_id  )") 
			public String getRbaName() {
				return rbaName;
			}

			public void setRbaName(String rbaName) {
				this.rbaName = rbaName;
			}

			/**
			 *设置牙医医院id(rbs_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdDentistHospital.RbsId</b>的值
			 */	 
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "rbs_id")
			public Integer getRbsId(){
			    return this.rbsId;
			}

		  /**
		   *设置 rbs_id字段方法 
		   *@param att_rbsId 输入<b>MdDentistHospital.rbsId</b>字段的值
		   */
		  public void setRbsId(Integer att_rbsId){
		    this.rbsId = att_rbsId;
		  }
  
			/**
			 *设置集团公司id(rba_id)字段方法 
			 *@return  返回 <b>MdDentistHospital.RbaId</b>的值
			 */	 
			@Column(name = "rba_id" ) 
			public Integer getRbaId(){
			    return this.rbaId;
			}

		  /**
		   *设置 rba_id字段方法 
		   *@param att_rbaId 输入<b>MdDentistHospital.rbaId</b>字段的值
		   */
		  public void setRbaId(Integer att_rbaId){
		    this.rbaId = att_rbaId;
		  }
  
			/**
			 *设置牙医医院编号(rbs_code)字段方法 
			 *@return  返回 <b>MdDentistHospital.RbsCode</b>的值
			 */	 
			@Column(name = "rbs_code" ) 
			public String getRbsCode(){
			    return this.rbsCode;
			}

		  /**
		   *设置 rbs_code字段方法 
		   *@param att_rbsCode 输入<b>MdDentistHospital.rbsCode</b>字段的值
		   */
		  public void setRbsCode(String att_rbsCode){
		    this.rbsCode = att_rbsCode;
		  }
  
			/**
			 *设置牙医医院名(rbs_name)字段方法 
			 *@return  返回 <b>MdDentistHospital.RbsName</b>的值
			 */	 
			@Column(name = "rbs_name" ) 
			public String getRbsName(){
			    return this.rbsName;
			}

		  /**
		   *设置 rbs_name字段方法 
		   *@param att_rbsName 输入<b>MdDentistHospital.rbsName</b>字段的值
		   */
		  public void setRbsName(String att_rbsName){
		    this.rbsName = att_rbsName;
		  }
  
			/**
			 *设置牙医医院行业(industry)字段方法 
			 *@return  返回 <b>MdDentistHospital.Industry</b>的值
			 */	 
			@Column(name = "industry" ) 
			public String getIndustry(){
			    return this.industry;
			}

		  /**
		   *设置 industry字段方法 
		   *@param att_industry 输入<b>MdDentistHospital.industry</b>字段的值
		   */
		  public void setIndustry(String att_industry){
		    this.industry = att_industry;
		  }
  
			/**
			 *设置牙医医院地址(address)字段方法 
			 *@return  返回 <b>MdDentistHospital.Address</b>的值
			 */	 
			@Column(name = "address" ) 
			public String getAddress(){
			    return this.address;
			}

		  /**
		   *设置 address字段方法 
		   *@param att_address 输入<b>MdDentistHospital.address</b>字段的值
		   */
		  public void setAddress(String att_address){
		    this.address = att_address;
		  }
  
			/**
			 *设置省(province)字段方法 
			 *@return  返回 <b>MdDentistHospital.Province</b>的值
			 */	 
			@Column(name = "province" ) 
			public String getProvince(){
			    return this.province;
			}

		  /**
		   *设置 province字段方法 
		   *@param att_province 输入<b>MdDentistHospital.province</b>字段的值
		   */
		  public void setProvince(String att_province){
		    this.province = att_province;
		  }
		  /**
			 *设置备注(remark)字段方法 
			 *@return  返回 <b>MdCompanyGroup.Remark</b>的值
			 */	 
			@Column(name = "remark" ) 
			public String getRemark(){
			    return this.remark;
			}

		  /**
		   *设置 remark字段方法 
		   *@param att_remark 输入<b>MdCompanyGroup.remark</b>字段的值
		   */
		  public void setRemark(String att_remark){
		    this.remark = att_remark;
		  }
			/**
			 *设置市(city)字段方法 
			 *@return  返回 <b>MdDentistHospital.City</b>的值
			 */	 
			@Column(name = "city" ) 
			public String getCity(){
			    return this.city;
			}

		  /**
		   *设置 city字段方法 
		   *@param att_city 输入<b>MdDentistHospital.city</b>字段的值
		   */
		  public void setCity(String att_city){
		    this.city = att_city;
		  }
  
			/**
			 *设置地区(area)字段方法 
			 *@return  返回 <b>MdDentistHospital.Area</b>的值
			 */	 
			@Column(name = "area" ) 
			public String getArea(){
			    return this.area;
			}

		  /**
		   *设置 area字段方法 
		   *@param att_area 输入<b>MdDentistHospital.area</b>字段的值
		   */
		  public void setArea(String att_area){
		    this.area = att_area;
		  }
  
			/**
			 *设置所有人(legal_person)字段方法 
			 *@return  返回 <b>MdDentistHospital.LegalPerson</b>的值
			 */	 
			@Column(name = "legal_person" ) 
			public String getLegalPerson(){
			    return this.legalPerson;
			}

		  /**
		   *设置 legal_person字段方法 
		   *@param att_legalPerson 输入<b>MdDentistHospital.legalPerson</b>字段的值
		   */
		  public void setLegalPerson(String att_legalPerson){
		    this.legalPerson = att_legalPerson;
		  }
  
			/**
			 *设置所有人电话(legal_phone)字段方法 
			 *@return  返回 <b>MdDentistHospital.LegalPhone</b>的值
			 */	 
			@Column(name = "legal_phone" ) 
			public String getLegalPhone(){
			    return this.legalPhone;
			}

		  /**
		   *设置 legal_phone字段方法 
		   *@param att_legalPhone 输入<b>MdDentistHospital.legalPhone</b>字段的值
		   */
		  public void setLegalPhone(String att_legalPhone){
		    this.legalPhone = att_legalPhone;
		  }
  
			/**
			 *设置所有人法人身份证(legal_certificate_no)字段方法 
			 *@return  返回 <b>MdDentistHospital.LegalCertificateNo</b>的值
			 */	 
			@Column(name = "legal_certificate_no" ) 
			public String getLegalCertificateNo(){
			    return this.legalCertificateNo;
			}

		  /**
		   *设置 legal_certificate_no字段方法 
		   *@param att_legalCertificateNo 输入<b>MdDentistHospital.legalCertificateNo</b>字段的值
		   */
		  public void setLegalCertificateNo(String att_legalCertificateNo){
		    this.legalCertificateNo = att_legalCertificateNo;
		  }
  
			/**
			 *设置联系人(conn_user)字段方法 
			 *@return  返回 <b>MdDentistHospital.ConnUser</b>的值
			 */	 
			@Column(name = "conn_user" ) 
			public String getConnUser(){
			    return this.connUser;
			}

		  /**
		   *设置 conn_user字段方法 
		   *@param att_connUser 输入<b>MdDentistHospital.connUser</b>字段的值
		   */
		  public void setConnUser(String att_connUser){
		    this.connUser = att_connUser;
		  }
  
			/**
			 *设置联系人电话(conn_phone)字段方法 
			 *@return  返回 <b>MdDentistHospital.ConnPhone</b>的值
			 */	 
			@Column(name = "conn_phone" ) 
			public String getConnPhone(){
			    return this.connPhone;
			}

		  /**
		   *设置 conn_phone字段方法 
		   *@param att_connPhone 输入<b>MdDentistHospital.connPhone</b>字段的值
		   */
		  public void setConnPhone(String att_connPhone){
		    this.connPhone = att_connPhone;
		  }
  
			/**
			 *设置联系人法人身份证(conn_certificate_no)字段方法 
			 *@return  返回 <b>MdDentistHospital.ConnCertificateNo</b>的值
			 */	 
			@Column(name = "conn_certificate_no" ) 
			public String getConnCertificateNo(){
			    return this.connCertificateNo;
			}

		  /**
		   *设置 conn_certificate_no字段方法 
		   *@param att_connCertificateNo 输入<b>MdDentistHospital.connCertificateNo</b>字段的值
		   */
		  public void setConnCertificateNo(String att_connCertificateNo){
		    this.connCertificateNo = att_connCertificateNo;
		  }
  
			/**
			 *设置营业执照(business_no)字段方法 
			 *@return  返回 <b>MdDentistHospital.BusinessNo</b>的值
			 */	 
			@Column(name = "business_no" ) 
			public String getBusinessNo(){
			    return this.businessNo;
			}

		  /**
		   *设置 business_no字段方法 
		   *@param att_businessNo 输入<b>MdDentistHospital.businessNo</b>字段的值
		   */
		  public void setBusinessNo(String att_businessNo){
		    this.businessNo = att_businessNo;
		  }
  
			/**
			 *设置营业执照文件(business_file)字段方法 
			 *@return  返回 <b>MdDentistHospital.BusinessFile</b>的值
			 */	 
			@Column(name = "business_file" ) 
			public String getBusinessFile(){
			    return this.businessFile;
			}

		  /**
		   *设置 business_file字段方法 
		   *@param att_businessFile 输入<b>MdDentistHospital.businessFile</b>字段的值
		   */
		  public void setBusinessFile(String att_businessFile){
		    this.businessFile = att_businessFile;
		  }
  
			/**
			 *设置负责人身份证(person_certificate_no)字段方法 
			 *@return  返回 <b>MdDentistHospital.PersonCertificateNo</b>的值
			 */	 
			@Column(name = "person_certificate_no" ) 
			public String getPersonCertificateNo(){
			    return this.personCertificateNo;
			}

		  /**
		   *设置 person_certificate_no字段方法 
		   *@param att_personCertificateNo 输入<b>MdDentistHospital.personCertificateNo</b>字段的值
		   */
		  public void setPersonCertificateNo(String att_personCertificateNo){
		    this.personCertificateNo = att_personCertificateNo;
		  }
  
			/**
			 *设置负责人身份证文件(person_certificate_file)字段方法 
			 *@return  返回 <b>MdDentistHospital.PersonCertificateFile</b>的值
			 */	 
			@Column(name = "person_certificate_file" ) 
			public String getPersonCertificateFile(){
			    return this.personCertificateFile;
			}

		  /**
		   *设置 person_certificate_file字段方法 
		   *@param att_personCertificateFile 输入<b>MdDentistHospital.personCertificateFile</b>字段的值
		   */
		  public void setPersonCertificateFile(String att_personCertificateFile){
		    this.personCertificateFile = att_personCertificateFile;
		  }
  
			/**
			 *设置组织机构代码证(organization_no)字段方法 
			 *@return  返回 <b>MdDentistHospital.OrganizationNo</b>的值
			 */	 
			@Column(name = "organization_no" ) 
			public String getOrganizationNo(){
			    return this.organizationNo;
			}

		  /**
		   *设置 organization_no字段方法 
		   *@param att_organizationNo 输入<b>MdDentistHospital.organizationNo</b>字段的值
		   */
		  public void setOrganizationNo(String att_organizationNo){
		    this.organizationNo = att_organizationNo;
		  }
  
			/**
			 *设置组织机构代码证文件(organization_file)字段方法 
			 *@return  返回 <b>MdDentistHospital.OrganizationFile</b>的值
			 */	 
			@Column(name = "organization_file" ) 
			public String getOrganizationFile(){
			    return this.organizationFile;
			}

		  /**
		   *设置 organization_file字段方法 
		   *@param att_organizationFile 输入<b>MdDentistHospital.organizationFile</b>字段的值
		   */
		  public void setOrganizationFile(String att_organizationFile){
		    this.organizationFile = att_organizationFile;
		  }
  
			/**
			 *设置账号类型(bank_type)字段方法 
			 *@return  返回 <b>MdDentistHospital.BankType</b>的值
			 */	 
			@Column(name = "bank_type" ) 
			public String getBankType(){
			    return this.bankType;
			}

		  /**
		   *设置 bank_type字段方法 
		   *@param att_bankType 输入<b>MdDentistHospital.bankType</b>字段的值
		   */
		  public void setBankType(String att_bankType){
		    this.bankType = att_bankType;
		  }
  
			/**
			 *设置开户人(bank_user)字段方法 
			 *@return  返回 <b>MdDentistHospital.BankUser</b>的值
			 */	 
			@Column(name = "bank_user" ) 
			public String getBankUser(){
			    return this.bankUser;
			}

		  /**
		   *设置 bank_user字段方法 
		   *@param att_bankUser 输入<b>MdDentistHospital.bankUser</b>字段的值
		   */
		  public void setBankUser(String att_bankUser){
		    this.bankUser = att_bankUser;
		  }
  
			/**
			 *设置开户银行(bank_name)字段方法 
			 *@return  返回 <b>MdDentistHospital.BankName</b>的值
			 */	 
			@Column(name = "bank_name" ) 
			public String getBankName(){
			    return this.bankName;
			}

		  /**
		   *设置 bank_name字段方法 
		   *@param att_bankName 输入<b>MdDentistHospital.bankName</b>字段的值
		   */
		  public void setBankName(String att_bankName){
		    this.bankName = att_bankName;
		  }
  
			/**
			 *设置商户额度(quota)字段方法 
			 *@return  返回 <b>MdDentistHospital.Quota</b>的值
			 */	 
			@Column(name = "quota" ) 
			public Double getQuota(){
			    return this.quota;
			}

		  /**
		   *设置 quota字段方法 
		   *@param att_quota 输入<b>MdDentistHospital.quota</b>字段的值
		   */
		  public void setQuota(Double att_quota){
		    this.quota = att_quota;
		  }
  
			/**
			 *设置提现方式(cash_type)字段方法 
			 *@return  返回 <b>MdDentistHospital.CashType</b>的值
			 */	 
			@Column(name = "cash_type" ) 
			public String getCashType(){
			    return this.cashType;
			}

		  /**
		   *设置 cash_type字段方法 
		   *@param att_cashType 输入<b>MdDentistHospital.cashType</b>字段的值
		   */
		  public void setCashType(String att_cashType){
		    this.cashType = att_cashType;
		  }
  
			/**
			 *设置创建人(create_ren)字段方法 
			 *@return  返回 <b>MdDentistHospital.CreateRen</b>的值
			 */	 
			@Column(name = "create_ren" ) 
			public String getCreateRen(){
			    return this.createRen;
			}

		  /**
		   *设置 create_ren字段方法 
		   *@param att_createRen 输入<b>MdDentistHospital.createRen</b>字段的值
		   */
		  public void setCreateRen(String att_createRen){
		    this.createRen = att_createRen;
		  }
  
			/**
			 *设置创建时间(create_date)字段方法 
			 *@return  返回 <b>MdDentistHospital.CreateDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "create_date" ) 
			public Date getCreateDate(){
			    return this.createDate;
			}

		  /**
		   *设置 create_date字段方法 
		   *@param att_createDate 输入<b>MdDentistHospital.createDate</b>字段的值
		   */
		  public void setCreateDate(Date att_createDate){
		    this.createDate = att_createDate;
		  }
  
			/**
			 *设置修改人(edit_ren)字段方法 
			 *@return  返回 <b>MdDentistHospital.EditRen</b>的值
			 */	 
			@Column(name = "edit_ren" ) 
			public String getEditRen(){
			    return this.editRen;
			}

		  /**
		   *设置 edit_ren字段方法 
		   *@param att_editRen 输入<b>MdDentistHospital.editRen</b>字段的值
		   */
		  public void setEditRen(String att_editRen){
		    this.editRen = att_editRen;
		  }
  
			/**
			 *设置修改时间(edit_date)字段方法 
			 *@return  返回 <b>MdDentistHospital.EditDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "edit_date" ) 
			public Date getEditDate(){
			    return this.editDate;
			}

		  /**
		   *设置 edit_date字段方法 
		   *@param att_editDate 输入<b>MdDentistHospital.editDate</b>字段的值
		   */
		  public void setEditDate(Date att_editDate){
		    this.editDate = att_editDate;
		  }
  
			/**
			 *设置流程状态(flow_state)字段方法 
			 *@return  返回 <b>MdDentistHospital.FlowState</b>的值
			 */	 
			@Column(name = "flow_state" ) 
			public Integer getFlowState(){
			    return this.flowState;
			}

		  /**
		   *设置 flow_state字段方法 
		   *@param att_flowState 输入<b>MdDentistHospital.flowState</b>字段的值
		   */
		  public void setFlowState(Integer att_flowState){
		    this.flowState = att_flowState;
		  }
  
			/**
			 *设置状态:1正常，2删除(state)字段方法 
			 *@return  返回 <b>MdDentistHospital.State</b>的值
			 */	 
			@Column(name = "state" ) 
			public Integer getState(){
			    return this.state;
			}

		  /**
		   *设置 state字段方法 
		   *@param att_state 输入<b>MdDentistHospital.state</b>字段的值
		   */
		  public void setState(Integer att_state){
		    this.state = att_state;
		  }
  
			/**
			 *设置银行账号(bank_code)字段方法 
			 *@return  返回 <b>MdDentistHospital.BankCode</b>的值
			 */	 
			@Column(name = "bank_code" ) 
			public String getBankCode(){
			    return this.bankCode;
			}

		  /**
		   *设置 bank_code字段方法 
		   *@param att_bankCode 输入<b>MdDentistHospital.bankCode</b>字段的值
		   */
		  public void setBankCode(String att_bankCode){
		    this.bankCode = att_bankCode;
		  }
  
			/**
			 *设置申请人(apply_user_id)字段方法 
			 *@return  返回 <b>MdDentistHospital.ApplyUserId</b>的值
			 */	 
			@Column(name = "apply_user_id" ) 
			public Integer getApplyUserId(){
			    return this.applyUserId;
			}

		  /**
		   *设置 apply_user_id字段方法 
		   *@param att_applyUserId 输入<b>MdDentistHospital.applyUserId</b>字段的值
		   */
		  public void setApplyUserId(Integer att_applyUserId){
		    this.applyUserId = att_applyUserId;
		  }

		  @Column(name = "salesman_ids")
	public String getSalesmanIds() {
		return salesmanIds;
	}

	public void setSalesmanIds(String salesmanIds) {
		this.salesmanIds = salesmanIds;
	}

///===============数据库表字段属性end==========

///===============数据库表无关子段字段属性begin==========
			/**
			 *牙医医院id(rbs_id):字段类型为String
			 */
		  private String rbsId_str;  
			/**
			 *集团公司id(rba_id):字段类型为String
			 */
		  private String rbaId_str;  
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
			 *流程状态(flow_state):字段类型为String
			 */
		  private String flowState_str;  
			/**
			 *状态:1正常，2删除(state):字段类型为String
			 */
		  private String state_str;  
			/**
			 *申请人(apply_user_id):字段类型为String
			 */
		  private String applyUserId_str;  
			/**
			 *():字段类型为String
			 *md_dentist_hospital表里不用like作为条件的字符串
			 */
		  private String Tab_like;  
			/**
			 *():字段类型为String
			 *md_dentist_hospital表里order by 字符串
			 */
		  private String Tab_order;  
			/**
			 *设置rbsId字段方法  
			 *@return  返回 <b>MdDentistHospital.rbsId</b>的值
			 */ 
			@Transient
			public String getRbsId_str(){
				return this.rbsId_str;
			}
			/**
			  *设置 rbs_id字段方法 
			  *@param att_rbsId 输入<b>MdDentistHospital.rbsId</b>字段的值
			  */
			public void setRbsId_str(String att_rbsId_str){
				this.rbsId_str = att_rbsId_str;
			}
			/**
			 *设置rbaId字段方法  
			 *@return  返回 <b>MdDentistHospital.rbaId</b>的值
			 */ 
			@Transient
			public String getRbaId_str(){
				return this.rbaId_str;
			}
			/**
			  *设置 rba_id字段方法 
			  *@param att_rbaId 输入<b>MdDentistHospital.rbaId</b>字段的值
			  */
			public void setRbaId_str(String att_rbaId_str){
				this.rbaId_str = att_rbaId_str;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdDentistHospital.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_start(){
				return this.createDate_start;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdDentistHospital.createDate</b>字段的值
			  */
			public void setCreateDate_start(Date att_createDate_start){
				this.createDate_start = att_createDate_start;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdDentistHospital.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_end(){
				return this.createDate_end;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdDentistHospital.createDate</b>字段的值
			  */
			public void setCreateDate_end(Date att_createDate_end){
				this.createDate_end = att_createDate_end;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdDentistHospital.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_start(){
				return this.editDate_start;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdDentistHospital.editDate</b>字段的值
			  */
			public void setEditDate_start(Date att_editDate_start){
				this.editDate_start = att_editDate_start;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdDentistHospital.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_end(){
				return this.editDate_end;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdDentistHospital.editDate</b>字段的值
			  */
			public void setEditDate_end(Date att_editDate_end){
				this.editDate_end = att_editDate_end;
			}
			/**
			 *设置flowState字段方法  
			 *@return  返回 <b>MdDentistHospital.flowState</b>的值
			 */ 
			@Transient
			public String getFlowState_str(){
				return this.flowState_str;
			}
			/**
			  *设置 flow_state字段方法 
			  *@param att_flowState 输入<b>MdDentistHospital.flowState</b>字段的值
			  */
			public void setFlowState_str(String att_flowState_str){
				this.flowState_str = att_flowState_str;
			}
			/**
			 *设置state字段方法  
			 *@return  返回 <b>MdDentistHospital.state</b>的值
			 */ 
			@Transient
			public String getState_str(){
				return this.state_str;
			}
			/**
			  *设置 state字段方法 
			  *@param att_state 输入<b>MdDentistHospital.state</b>字段的值
			  */
			public void setState_str(String att_state_str){
				this.state_str = att_state_str;
			}
			/**
			 *设置applyUserId字段方法  
			 *@return  返回 <b>MdDentistHospital.applyUserId</b>的值
			 */ 
			@Transient
			public String getApplyUserId_str(){
				return this.applyUserId_str;
			}
			/**
			  *设置 apply_user_id字段方法 
			  *@param att_applyUserId 输入<b>MdDentistHospital.applyUserId</b>字段的值
			  */
			public void setApplyUserId_str(String att_applyUserId_str){
				this.applyUserId_str = att_applyUserId_str;
			}
			/**
			 *设置tab_like字段方法  
			 *@return  返回 <b>MdDentistHospital.tab_like</b>的值
			 */ 
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_like 输入<b>MdDentistHospital.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法  
			 *@return  返回 <b>MdDentistHospital.tab_order</b>的值
			 */ 
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_order 输入<b>MdDentistHospital.tab_order</b>字段的值
			  */
			public void setTab_order(String att_Tab_order){
				this.Tab_order = att_Tab_order;
			}

	@Column(name = "sales_name")
	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	@Formula("(SELECT a.sales_phone FROM sys_sales_man a WHERE a.salesman_id in (salesman_ids))")
	public String getSalesPhone() {
		return salesPhone;
	}

	public void setSalesPhone(String salesPhone) {
		this.salesPhone = salesPhone;
	}

    public void setVerifyState(Integer verifyState) {
        this.verifyState = verifyState;
    }
	@Column(name = "verify_state")
    public Integer getVerifyState() {
        return verifyState;
    }

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}
	@Column(name = "verify_remark")
	public String getVerifyRemark() {
		return verifyRemark;
	}

    public void setLogo(String logo) {
        this.logo = logo;
    }
	@Column(name = "logo")
    public String getLogo() {
        return logo;
    }
///===============数据库表无关子段字段属性end==========
} 