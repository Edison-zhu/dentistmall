package com.hsk.dentistmall.api.persistence;

import javax.persistence.*; 

import org.hibernate.annotations.Formula;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

/**
 * 根据md_company_group表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdCompanyGroup</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table> 
 * <tr><th>字段名称:</th><td>集团公司id(rbaId)</td><th>字段类型:</th><td>Integer(主键)</td></tr> 
 * <tr><th>字段名称:</th><td>集团公司编号(rbaCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>集团公司名称(rbaName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>集团公司级别(level)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>公司所在地址(address)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>省(province)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>市(city)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>地区(area)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>负责人(personName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>电话(phoneNumber)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>邮箱(email)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>备注(remark)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>营业执照(businessNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>营业执照文件(businessFile)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>负责人身份证(personCertificateNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>负责人身份证文件(personCertificateFile)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>组织机构代码证(organizationNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>组织机构代码证文件(organizationFile)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>代理协议(agentAgreement)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>代理协议文件(agentFile)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>开户银行(bankName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>银行卡号(bankNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>开户人(bankUser)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>账号类型(bankType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>开户支行名称(branchName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>手机号(balancePhone)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>证件类型(certificateType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>证件号码(certificateNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>支行所在省(bankProvince)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>支行所在市(bankCity)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>汇出方银行卡号(fromBankNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>是否行内帐号:1是；2否(inBank)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>流程状态(1.待经理审核，2经理审核不通过，3经理审核通过)(flowState)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>状态:1正常，2删除(state)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>网点号(银行号)(pointNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * </table>
 *===============无关字段属性===============
 *  <table> 
 * 	<tr><th>字段名称:</th><td>集团公司id(rbaId)	</td><th>属性名称:</th><td>rbaId</td></tr>
 * 	<tr><th>字段名称:</th><td>流程状态(1.待经理审核，2经理审核不通过，3经理审核通过)(flowState)	</td><th>属性名称:</th><td>flowState</td></tr>
 * 	<tr><th>字段名称:</th><td>状态:1正常，2删除(state)	</td><th>属性名称:</th><td>state</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 * @author  作者:admin  
 * @version  v1.0 创建时间: 2017-09-25 14:21:16
 */
 
 
@SuppressWarnings("serial")
@Entity
@Table(name = "md_company_group" )
public class MdCompanyGroup {
///===============数据库表字段属性begin==========
			/**
			 *集团公司id(rbaId):字段类型为Integer  
			 */
 			private Integer rbaId; 
 	
			/**
			 *集团公司编号(rbaCode):字段类型为String  
			 */
 			private String rbaCode; 
 	
			/**
			 *集团公司名称(rbaName):字段类型为String  
			 */
 			private String rbaName; 
 	
			/**
			 *集团公司级别(level):字段类型为String  
			 */
 			private String level; 
 	
			/**
			 *公司所在地址(address):字段类型为String  
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
			 *负责人(personName):字段类型为String  
			 */
 			private String personName; 
 	
			/**
			 *电话(phoneNumber):字段类型为String  
			 */
 			private String phoneNumber; 
 	
			/**
			 *邮箱(email):字段类型为String  
			 */
 			private String email; 
 	
			/**
			 *备注(remark):字段类型为String  
			 */
 			private String remark; 
 	
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
			 *代理协议(agentAgreement):字段类型为String  
			 */
 			private String agentAgreement; 
 	
			/**
			 *代理协议文件(agentFile):字段类型为String  
			 */
 			private String agentFile; 
 	
			/**
			 *开户银行(bankName):字段类型为String  
			 */
 			private String bankName; 
 	
			/**
			 *银行卡号(bankNo):字段类型为String  
			 */
 			private String bankNo; 
 	
			/**
			 *开户人(bankUser):字段类型为String  
			 */
 			private String bankUser; 
 	
			/**
			 *账号类型(bankType):字段类型为String  
			 */
 			private String bankType; 
 	
			/**
			 *开户支行名称(branchName):字段类型为String  
			 */
 			private String branchName; 
 	
			/**
			 *手机号(balancePhone):字段类型为String  
			 */
 			private String balancePhone; 
 	
			/**
			 *证件类型(certificateType):字段类型为String  
			 */
 			private String certificateType; 
 	
			/**
			 *证件号码(certificateNo):字段类型为String  
			 */
 			private String certificateNo; 
 	
			/**
			 *支行所在省(bankProvince):字段类型为String  
			 */
 			private String bankProvince; 
 	
			/**
			 *支行所在市(bankCity):字段类型为String  
			 */
 			private String bankCity; 
 	
			/**
			 *汇出方银行卡号(fromBankNo):字段类型为String  
			 */
 			private String fromBankNo; 
 	
			/**
			 *是否行内帐号:1是；2否(inBank):字段类型为String  
			 */
 			private String inBank; 
 	
			/**
			 *流程状态(1.待经理审核，2经理审核不通过，3经理审核通过)(flowState):字段类型为Integer  
			 */
 			private Integer flowState; 
 	
			/**
			 *状态:1正常，2删除(state):字段类型为Integer  
			 */
 			private Integer state; 
 	
			/**
			 *网点号(银行号)(pointNo):字段类型为String  
			 */
 			private String pointNo; 
 	
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
 			 * 上级公司id
 			 */
 			private Integer levelId; 
 	
 			 private String salesmanIds;
	private String salesName;

	private String salesPhone;
	private Integer verifyState;
	private String verifyRemark;

	private String logo;

	/**
			 *设置集团公司id(rba_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdCompanyGroup.RbaId</b>的值
			 */	 
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "rba_id")
			public Integer getRbaId(){
			    return this.rbaId;
			}

		  /**
		   *设置 rba_id字段方法 
		   *@param att_rbaId 输入<b>MdCompanyGroup.rbaId</b>字段的值
		   */
		  public void setRbaId(Integer att_rbaId){
		    this.rbaId = att_rbaId;
		  }
   
		  /**
		   * 上级公司id
		   * @return
		   */
		  @Column(name = "level_id" ) 
			public Integer getLevelId() {
			return levelId;
		}

		public void setLevelId(Integer levelId) {
			this.levelId = levelId;
		}

			/**
			 *设置集团公司编号(rba_code)字段方法 
			 *@return  返回 <b>MdCompanyGroup.RbaCode</b>的值
			 */	 
			@Column(name = "rba_code" ) 
			public String getRbaCode(){
			    return this.rbaCode;
			}

		  /**
		   *设置 rba_code字段方法 
		   *@param att_rbaCode 输入<b>MdCompanyGroup.rbaCode</b>字段的值
		   */
		  public void setRbaCode(String att_rbaCode){
		    this.rbaCode = att_rbaCode;
		  }
  
			/**
			 *设置集团公司名称(rba_name)字段方法 
			 *@return  返回 <b>MdCompanyGroup.RbaName</b>的值
			 */	 
			@Column(name = "rba_name" ) 
			public String getRbaName(){
			    return this.rbaName;
			}

		  /**
		   *设置 rba_name字段方法 
		   *@param att_rbaName 输入<b>MdCompanyGroup.rbaName</b>字段的值
		   */
		  public void setRbaName(String att_rbaName){
		    this.rbaName = att_rbaName;
		  }
  
			/**
			 *设置集团公司级别(level)字段方法 
			 *@return  返回 <b>MdCompanyGroup.Level</b>的值
			 */	 
			@Column(name = "level" ) 
			public String getLevel(){
			    return this.level;
			}

		  /**
		   *设置 level字段方法 
		   *@param att_level 输入<b>MdCompanyGroup.level</b>字段的值
		   */
		  public void setLevel(String att_level){
		    this.level = att_level;
		  }
  
			/**
			 *设置公司所在地址(address)字段方法 
			 *@return  返回 <b>MdCompanyGroup.Address</b>的值
			 */	 
			@Column(name = "address" ) 
			public String getAddress(){
			    return this.address;
			}

		  /**
		   *设置 address字段方法 
		   *@param att_address 输入<b>MdCompanyGroup.address</b>字段的值
		   */
		  public void setAddress(String att_address){
		    this.address = att_address;
		  }
  
			/**
			 *设置省(province)字段方法 
			 *@return  返回 <b>MdCompanyGroup.Province</b>的值
			 */	 
			@Column(name = "province" ) 
			public String getProvince(){
			    return this.province;
			}

		  /**
		   *设置 province字段方法 
		   *@param att_province 输入<b>MdCompanyGroup.province</b>字段的值
		   */
		  public void setProvince(String att_province){
		    this.province = att_province;
		  }
  
			/**
			 *设置市(city)字段方法 
			 *@return  返回 <b>MdCompanyGroup.City</b>的值
			 */	 
			@Column(name = "city" ) 
			public String getCity(){
			    return this.city;
			}

		  /**
		   *设置 city字段方法 
		   *@param att_city 输入<b>MdCompanyGroup.city</b>字段的值
		   */
		  public void setCity(String att_city){
		    this.city = att_city;
		  }
  
			/**
			 *设置地区(area)字段方法 
			 *@return  返回 <b>MdCompanyGroup.Area</b>的值
			 */	 
			@Column(name = "area" ) 
			public String getArea(){
			    return this.area;
			}

		  /**
		   *设置 area字段方法 
		   *@param att_area 输入<b>MdCompanyGroup.area</b>字段的值
		   */
		  public void setArea(String att_area){
		    this.area = att_area;
		  }
  
			/**
			 *设置负责人(person_name)字段方法 
			 *@return  返回 <b>MdCompanyGroup.PersonName</b>的值
			 */	 
			@Column(name = "person_name" ) 
			public String getPersonName(){
			    return this.personName;
			}

		  /**
		   *设置 person_name字段方法 
		   *@param att_personName 输入<b>MdCompanyGroup.personName</b>字段的值
		   */
		  public void setPersonName(String att_personName){
		    this.personName = att_personName;
		  }
  
			/**
			 *设置电话(phone_number)字段方法 
			 *@return  返回 <b>MdCompanyGroup.PhoneNumber</b>的值
			 */	 
			@Column(name = "phone_number" ) 
			public String getPhoneNumber(){
			    return this.phoneNumber;
			}

		  /**
		   *设置 phone_number字段方法 
		   *@param att_phoneNumber 输入<b>MdCompanyGroup.phoneNumber</b>字段的值
		   */
		  public void setPhoneNumber(String att_phoneNumber){
		    this.phoneNumber = att_phoneNumber;
		  }
  
			/**
			 *设置邮箱(email)字段方法 
			 *@return  返回 <b>MdCompanyGroup.Email</b>的值
			 */	 
			@Column(name = "email" ) 
			public String getEmail(){
			    return this.email;
			}

		  /**
		   *设置 email字段方法 
		   *@param att_email 输入<b>MdCompanyGroup.email</b>字段的值
		   */
		  public void setEmail(String att_email){
		    this.email = att_email;
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
			 *设置营业执照(business_no)字段方法 
			 *@return  返回 <b>MdCompanyGroup.BusinessNo</b>的值
			 */	 
			@Column(name = "business_no" ) 
			public String getBusinessNo(){
			    return this.businessNo;
			}

		  /**
		   *设置 business_no字段方法 
		   *@param att_businessNo 输入<b>MdCompanyGroup.businessNo</b>字段的值
		   */
		  public void setBusinessNo(String att_businessNo){
		    this.businessNo = att_businessNo;
		  }
  
			/**
			 *设置营业执照文件(business_file)字段方法 
			 *@return  返回 <b>MdCompanyGroup.BusinessFile</b>的值
			 */	 
			@Column(name = "business_file" ) 
			public String getBusinessFile(){
			    return this.businessFile;
			}

		  /**
		   *设置 business_file字段方法 
		   *@param att_businessFile 输入<b>MdCompanyGroup.businessFile</b>字段的值
		   */
		  public void setBusinessFile(String att_businessFile){
		    this.businessFile = att_businessFile;
		  }
  
			/**
			 *设置负责人身份证(person_certificate_no)字段方法 
			 *@return  返回 <b>MdCompanyGroup.PersonCertificateNo</b>的值
			 */	 
			@Column(name = "person_certificate_no" ) 
			public String getPersonCertificateNo(){
			    return this.personCertificateNo;
			}

		  /**
		   *设置 person_certificate_no字段方法 
		   *@param att_personCertificateNo 输入<b>MdCompanyGroup.personCertificateNo</b>字段的值
		   */
		  public void setPersonCertificateNo(String att_personCertificateNo){
		    this.personCertificateNo = att_personCertificateNo;
		  }
  
			/**
			 *设置负责人身份证文件(person_certificate_file)字段方法 
			 *@return  返回 <b>MdCompanyGroup.PersonCertificateFile</b>的值
			 */	 
			@Column(name = "person_certificate_file" ) 
			public String getPersonCertificateFile(){
			    return this.personCertificateFile;
			}

		  /**
		   *设置 person_certificate_file字段方法 
		   *@param att_personCertificateFile 输入<b>MdCompanyGroup.personCertificateFile</b>字段的值
		   */
		  public void setPersonCertificateFile(String att_personCertificateFile){
		    this.personCertificateFile = att_personCertificateFile;
		  }
  
			/**
			 *设置组织机构代码证(organization_no)字段方法 
			 *@return  返回 <b>MdCompanyGroup.OrganizationNo</b>的值
			 */	 
			@Column(name = "organization_no" ) 
			public String getOrganizationNo(){
			    return this.organizationNo;
			}

		  /**
		   *设置 organization_no字段方法 
		   *@param att_organizationNo 输入<b>MdCompanyGroup.organizationNo</b>字段的值
		   */
		  public void setOrganizationNo(String att_organizationNo){
		    this.organizationNo = att_organizationNo;
		  }
  
			/**
			 *设置组织机构代码证文件(organization_file)字段方法 
			 *@return  返回 <b>MdCompanyGroup.OrganizationFile</b>的值
			 */	 
			@Column(name = "organization_file" ) 
			public String getOrganizationFile(){
			    return this.organizationFile;
			}

		  /**
		   *设置 organization_file字段方法 
		   *@param att_organizationFile 输入<b>MdCompanyGroup.organizationFile</b>字段的值
		   */
		  public void setOrganizationFile(String att_organizationFile){
		    this.organizationFile = att_organizationFile;
		  }
  
			/**
			 *设置代理协议(agent_agreement)字段方法 
			 *@return  返回 <b>MdCompanyGroup.AgentAgreement</b>的值
			 */	 
			@Column(name = "agent_agreement" ) 
			public String getAgentAgreement(){
			    return this.agentAgreement;
			}

		  /**
		   *设置 agent_agreement字段方法 
		   *@param att_agentAgreement 输入<b>MdCompanyGroup.agentAgreement</b>字段的值
		   */
		  public void setAgentAgreement(String att_agentAgreement){
		    this.agentAgreement = att_agentAgreement;
		  }
  
			/**
			 *设置代理协议文件(agent_file)字段方法 
			 *@return  返回 <b>MdCompanyGroup.AgentFile</b>的值
			 */	 
			@Column(name = "agent_file" ) 
			public String getAgentFile(){
			    return this.agentFile;
			}

		  /**
		   *设置 agent_file字段方法 
		   *@param att_agentFile 输入<b>MdCompanyGroup.agentFile</b>字段的值
		   */
		  public void setAgentFile(String att_agentFile){
		    this.agentFile = att_agentFile;
		  }
  
			/**
			 *设置开户银行(bank_name)字段方法 
			 *@return  返回 <b>MdCompanyGroup.BankName</b>的值
			 */	 
			@Column(name = "bank_name" ) 
			public String getBankName(){
			    return this.bankName;
			}

		  /**
		   *设置 bank_name字段方法 
		   *@param att_bankName 输入<b>MdCompanyGroup.bankName</b>字段的值
		   */
		  public void setBankName(String att_bankName){
		    this.bankName = att_bankName;
		  }
  
			/**
			 *设置银行卡号(bank_no)字段方法 
			 *@return  返回 <b>MdCompanyGroup.BankNo</b>的值
			 */	 
			@Column(name = "bank_no" ) 
			public String getBankNo(){
			    return this.bankNo;
			}

		  /**
		   *设置 bank_no字段方法 
		   *@param att_bankNo 输入<b>MdCompanyGroup.bankNo</b>字段的值
		   */
		  public void setBankNo(String att_bankNo){
		    this.bankNo = att_bankNo;
		  }
  
			/**
			 *设置开户人(bank_user)字段方法 
			 *@return  返回 <b>MdCompanyGroup.BankUser</b>的值
			 */	 
			@Column(name = "bank_user" ) 
			public String getBankUser(){
			    return this.bankUser;
			}

		  /**
		   *设置 bank_user字段方法 
		   *@param att_bankUser 输入<b>MdCompanyGroup.bankUser</b>字段的值
		   */
		  public void setBankUser(String att_bankUser){
		    this.bankUser = att_bankUser;
		  }
  
			/**
			 *设置账号类型(bank_type)字段方法 
			 *@return  返回 <b>MdCompanyGroup.BankType</b>的值
			 */	 
			@Column(name = "bank_type" ) 
			public String getBankType(){
			    return this.bankType;
			}

		  /**
		   *设置 bank_type字段方法 
		   *@param att_bankType 输入<b>MdCompanyGroup.bankType</b>字段的值
		   */
		  public void setBankType(String att_bankType){
		    this.bankType = att_bankType;
		  }
  
			/**
			 *设置开户支行名称(branch_name)字段方法 
			 *@return  返回 <b>MdCompanyGroup.BranchName</b>的值
			 */	 
			@Column(name = "branch_name" ) 
			public String getBranchName(){
			    return this.branchName;
			}

		  /**
		   *设置 branch_name字段方法 
		   *@param att_branchName 输入<b>MdCompanyGroup.branchName</b>字段的值
		   */
		  public void setBranchName(String att_branchName){
		    this.branchName = att_branchName;
		  }
  
			/**
			 *设置手机号(balance_phone)字段方法 
			 *@return  返回 <b>MdCompanyGroup.BalancePhone</b>的值
			 */	 
			@Column(name = "balance_phone" ) 
			public String getBalancePhone(){
			    return this.balancePhone;
			}

		  /**
		   *设置 balance_phone字段方法 
		   *@param att_balancePhone 输入<b>MdCompanyGroup.balancePhone</b>字段的值
		   */
		  public void setBalancePhone(String att_balancePhone){
		    this.balancePhone = att_balancePhone;
		  }
  
			/**
			 *设置证件类型(certificate_type)字段方法 
			 *@return  返回 <b>MdCompanyGroup.CertificateType</b>的值
			 */	 
			@Column(name = "certificate_type" ) 
			public String getCertificateType(){
			    return this.certificateType;
			}

		  /**
		   *设置 certificate_type字段方法 
		   *@param att_certificateType 输入<b>MdCompanyGroup.certificateType</b>字段的值
		   */
		  public void setCertificateType(String att_certificateType){
		    this.certificateType = att_certificateType;
		  }
  
			/**
			 *设置证件号码(certificate_no)字段方法 
			 *@return  返回 <b>MdCompanyGroup.CertificateNo</b>的值
			 */	 
			@Column(name = "certificate_no" ) 
			public String getCertificateNo(){
			    return this.certificateNo;
			}

		  /**
		   *设置 certificate_no字段方法 
		   *@param att_certificateNo 输入<b>MdCompanyGroup.certificateNo</b>字段的值
		   */
		  public void setCertificateNo(String att_certificateNo){
		    this.certificateNo = att_certificateNo;
		  }
  
			/**
			 *设置支行所在省(bank_province)字段方法 
			 *@return  返回 <b>MdCompanyGroup.BankProvince</b>的值
			 */	 
			@Column(name = "bank_province" ) 
			public String getBankProvince(){
			    return this.bankProvince;
			}

		  /**
		   *设置 bank_province字段方法 
		   *@param att_bankProvince 输入<b>MdCompanyGroup.bankProvince</b>字段的值
		   */
		  public void setBankProvince(String att_bankProvince){
		    this.bankProvince = att_bankProvince;
		  }
  
			/**
			 *设置支行所在市(bank_city)字段方法 
			 *@return  返回 <b>MdCompanyGroup.BankCity</b>的值
			 */	 
			@Column(name = "bank_city" ) 
			public String getBankCity(){
			    return this.bankCity;
			}

		  /**
		   *设置 bank_city字段方法 
		   *@param att_bankCity 输入<b>MdCompanyGroup.bankCity</b>字段的值
		   */
		  public void setBankCity(String att_bankCity){
		    this.bankCity = att_bankCity;
		  }
  
			/**
			 *设置汇出方银行卡号(from_bank_no)字段方法 
			 *@return  返回 <b>MdCompanyGroup.FromBankNo</b>的值
			 */	 
			@Column(name = "from_bank_no" ) 
			public String getFromBankNo(){
			    return this.fromBankNo;
			}

		  /**
		   *设置 from_bank_no字段方法 
		   *@param att_fromBankNo 输入<b>MdCompanyGroup.fromBankNo</b>字段的值
		   */
		  public void setFromBankNo(String att_fromBankNo){
		    this.fromBankNo = att_fromBankNo;
		  }
  
			/**
			 *设置是否行内帐号:1是；2否(in_bank)字段方法 
			 *@return  返回 <b>MdCompanyGroup.InBank</b>的值
			 */	 
			@Column(name = "in_bank" ) 
			public String getInBank(){
			    return this.inBank;
			}

		  /**
		   *设置 in_bank字段方法 
		   *@param att_inBank 输入<b>MdCompanyGroup.inBank</b>字段的值
		   */
		  public void setInBank(String att_inBank){
		    this.inBank = att_inBank;
		  }
  
			/**
			 *设置流程状态(1.待经理审核，2经理审核不通过，3经理审核通过)(flow_state)字段方法 
			 *@return  返回 <b>MdCompanyGroup.FlowState</b>的值
			 */	 
			@Column(name = "flow_state" ) 
			public Integer getFlowState(){
			    return this.flowState;
			}

		  /**
		   *设置 flow_state字段方法 
		   *@param att_flowState 输入<b>MdCompanyGroup.flowState</b>字段的值
		   */
		  public void setFlowState(Integer att_flowState){
		    this.flowState = att_flowState;
		  }
  
			/**
			 *设置状态:1正常，2删除(state)字段方法 
			 *@return  返回 <b>MdCompanyGroup.State</b>的值
			 */	 
			@Column(name = "state" ) 
			public Integer getState(){
			    return this.state;
			}

		  /**
		   *设置 state字段方法 
		   *@param att_state 输入<b>MdCompanyGroup.state</b>字段的值
		   */
		  public void setState(Integer att_state){
		    this.state = att_state;
		  }
  
			/**
			 *设置网点号(银行号)(point_no)字段方法 
			 *@return  返回 <b>MdCompanyGroup.PointNo</b>的值
			 */	 
			@Column(name = "point_no" ) 
			public String getPointNo(){
			    return this.pointNo;
			}

		  /**
		   *设置 point_no字段方法 
		   *@param att_pointNo 输入<b>MdCompanyGroup.pointNo</b>字段的值
		   */
		  public void setPointNo(String att_pointNo){
		    this.pointNo = att_pointNo;
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
			 *集团公司id(rba_id):字段类型为String
			 */
		  private String rbaId_str;  
			/**
			 *流程状态(1.待经理审核，2经理审核不通过，3经理审核通过)(flow_state):字段类型为String
			 */
		  private String flowState_str;  
			/**
			 *状态:1正常，2删除(state):字段类型为String
			 */
		  private String state_str;  
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
			 *md_company_group表里不用like作为条件的字符串
			 */
		  private String Tab_like;  
			/**
			 *():字段类型为String
			 *md_company_group表里order by 字符串
			 */
		  private String Tab_order;  
			/**
			 *设置rbaId字段方法  
			 *@return  返回 <b>MdCompanyGroup.rbaId</b>的值
			 */ 
			@Transient
			public String getRbaId_str(){
				return this.rbaId_str;
			}
			/**
			  *设置 rba_id字段方法 
			  *@param att_rbaId 输入<b>MdCompanyGroup.rbaId</b>字段的值
			  */
			public void setRbaId_str(String att_rbaId_str){
				this.rbaId_str = att_rbaId_str;
			}
			/**
			 *设置flowState字段方法  
			 *@return  返回 <b>MdCompanyGroup.flowState</b>的值
			 */ 
			@Transient
			public String getFlowState_str(){
				return this.flowState_str;
			}
			/**
			  *设置 flow_state字段方法 
			  *@param att_flowState 输入<b>MdCompanyGroup.flowState</b>字段的值
			  */
			public void setFlowState_str(String att_flowState_str){
				this.flowState_str = att_flowState_str;
			}
			/**
			 *设置state字段方法  
			 *@return  返回 <b>MdCompanyGroup.state</b>的值
			 */ 
			@Transient
			public String getState_str(){
				return this.state_str;
			}
			/**
			  *设置 state字段方法 
			  *@param att_state 输入<b>MdCompanyGroup.state</b>字段的值
			  */
			public void setState_str(String att_state_str){
				this.state_str = att_state_str;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdCompanyGroup.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_start(){
				return this.createDate_start;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdCompanyGroup.createDate</b>字段的值
			  */
			public void setCreateDate_start(Date att_createDate_start){
				this.createDate_start = att_createDate_start;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdCompanyGroup.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_end(){
				return this.createDate_end;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdCompanyGroup.createDate</b>字段的值
			  */
			public void setCreateDate_end(Date att_createDate_end){
				this.createDate_end = att_createDate_end;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdCompanyGroup.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_start(){
				return this.editDate_start;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdCompanyGroup.editDate</b>字段的值
			  */
			public void setEditDate_start(Date att_editDate_start){
				this.editDate_start = att_editDate_start;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdCompanyGroup.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_end(){
				return this.editDate_end;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdCompanyGroup.editDate</b>字段的值
			  */
			public void setEditDate_end(Date att_editDate_end){
				this.editDate_end = att_editDate_end;
			}
			/**
			 *设置tab_like字段方法  
			 *@return  返回 <b>MdCompanyGroup.tab_like</b>的值
			 */ 
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_like 输入<b>MdCompanyGroup.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法  
			 *@return  返回 <b>MdCompanyGroup.tab_order</b>的值
			 */ 
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_order 输入<b>MdCompanyGroup.tab_order</b>字段的值
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

	///===============数据库表无关子段字段属性end==========

	@Column(name = "logo")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
}