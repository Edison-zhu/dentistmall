package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 根据md_materiel_info表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdMaterielInfo</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table> 
 * <tr><th>字段名称:</th><td>物料信息表id(wmsMiId)</td><th>字段类型:</th><td>Integer(主键)</td></tr> 
 * <tr><th>字段名称:</th><td>供应商id(wzId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>单位类型(1:供应商,2:集团,3:医院,4:门诊)(purchaseType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>物料信息表id(mdWmsMiId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>企业名称(applicantName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>法人手机号码(phoneNumber)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>企业住所地(corporateDomicile)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>经营范围(scopeBusiness)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>物料编码(matCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>物料名称(matName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>价格1(money1)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>价格1(money2)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>价格1(money3)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>价格1(money4)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>价格1(money5)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>数量1(number1)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>数量1(number2)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>数量1(number3)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>数量1(number4)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>数量1(number5)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>基本单位(basicUnit)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>批次规则(batchRule)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>保质期限(validPeriod)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>预警时间(alertDay)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>规格(norm)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>物料类别(matType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>物料类别(matType1)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>拣选分类(matType2)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>码托分类(matType3)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>标注(labelInfo)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>缩小图(lessenFilecode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>描述(describe1)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>描述(describe2)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>条码(barcode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>条码图片文件(barcodeFilecode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>状态(state)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>基本单位精度(basicUnitAccuracy)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>背面印字(backPrinting)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>序号(serialNumber)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * </table>
 *===============无关字段属性===============
 *  <table> 
 * 	<tr><th>字段名称:</th><td>物料信息表id(wmsMiId)	</td><th>属性名称:</th><td>wmsMiId</td></tr>
 * 	<tr><th>字段名称:</th><td>供应商id(wzId)	</td><th>属性名称:</th><td>wzId</td></tr>
 * 	<tr><th>字段名称:</th><td>物料信息表id(mdWmsMiId)	</td><th>属性名称:</th><td>mdWmsMiId</td></tr>
 * 	<tr><th>字段名称:</th><td>保质期限(validPeriod)	</td><th>属性名称:</th><td>validPeriod</td></tr>
 * 	<tr><th>字段名称:</th><td>预警时间(alertDay)	</td><th>属性名称:</th><td>alertDay</td></tr>
 * 	<tr><th>字段名称:</th><td>序号(serialNumber)	</td><th>属性名称:</th><td>serialNumber</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 * @author  作者:admin  
 * @version  v1.0 创建时间: 2017-09-29 10:30:21
 */
 
 
@SuppressWarnings("serial")
@Entity
@Table(name = "md_materiel_info_temp" )
public class MdMaterielInfoTemp {
///===============数据库表字段属性begin==========
			/**
			 *物料信息表id(wmsMiId):字段类型为Integer  
			 */
 			private Integer wmsMiId; 
 	
			/**
			 *供应商id(wzId):字段类型为Integer  
			 */
 			private Integer wzId; 
 	
			/**
			 *单位类型(1:供应商,2:集团,3:医院,4:门诊)(purchaseType):字段类型为String  
			 */
 			private String purchaseType; 
 	
			/**
			 *物料信息表id(mdWmsMiId):字段类型为Integer  
			 */
 			private Integer mdWmsMiId; 
 	
			/**
			 *企业名称(applicantName):字段类型为String  
			 */
 			private String applicantName; 
 			
 			/**
 			 * 
 			 * yanglei  2019-11-22 新增包装方式字段  productWay 字段类型为String  
 			 */
 			private String productName;
 			/**
 			 * 生产厂家
 			 */
 			private String productFactory;
 			/**
 			 * 品牌
 			 */
 			private String brand;
			/**
			 *法人手机号码(phoneNumber):字段类型为String  
			 */
 			private String phoneNumber; 
 	
			/**
			 *企业住所地(corporateDomicile):字段类型为String  
			 */
 			private String corporateDomicile; 
 	
			/**
			 *经营范围(scopeBusiness):字段类型为String  
			 */
 			private String scopeBusiness; 
 	
			/**
			 *物料编码(matCode):字段类型为String  
			 */
 			private String matCode; 
 	
			/**
			 *物料名称(matName):字段类型为String  
			 */
 			private String matName; 
 			/**
 			 * 名称拼音
 			 */
 			private String pyName;
 	
			/**
			 *价格1(money1):字段类型为Double  
			 */
 			private Double money1; 
 	
			/**
			 *价格1(money2):字段类型为Double  
			 */
 			private Double money2; 
 	
			/**
			 *价格1(money3):字段类型为Double  
			 */
 			private Double money3; 
 	
			/**
			 *价格1(money4):字段类型为Double  
			 */
 			private Double money4; 
 	
			/**
			 *价格1(money5):字段类型为Double  
			 */
 			private Double money5; 
 	
			/**
			 *数量1(number1):字段类型为Double  
			 */
 			private Double number1; 
 	
			/**
			 *数量1(number2):字段类型为Double  
			 */
 			private Double number2; 
 	
			/**
			 *数量1(number3):字段类型为Double  
			 */
 			private Double number3; 
 	
			/**
			 *数量1(number4):字段类型为Double  
			 */
 			private Double number4; 
 	
			/**
			 *数量1(number5):字段类型为Double  
			 */
 			private Double number5; 
 	
			/**
			 *基本单位(basicUnit):字段类型为String  
			 */
 			private String basicUnit; 
 	
			/**
			 *批次规则(batchRule):字段类型为String  
			 */
 			private String batchRule; 
 	
			/**
			 *保质期限(validPeriod):字段类型为Integer  
			 */
 			private Integer validPeriod; 
 	
			/**
			 *预警时间(alertDay):字段类型为Integer  
			 */
 			private Integer alertDay; 
 	
			/**
			 *规格(norm):字段类型为String  
			 */
 			private String norm; 
 	
			/**
			 *物料类别(matType):字段类型为String  
			 */
 			private String matType; 
 	
			/**
			 *物料类别(matType1):字段类型为String  
			 */
 			private String matType1; 
 	
			/**
			 *拣选分类(matType2):字段类型为String  
			 */
 			private String matType2; 
 	
			/**
			 *码托分类(matType3):字段类型为String  
			 */
 			private String matType3; 
 	
			/**
			 *标注(labelInfo):字段类型为String  
			 */
 			private String labelInfo; 
 	
			/**
			 *缩小图(lessenFilecode):字段类型为String  
			 */
 			private String lessenFilecode; 
 	
			/**
			 *描述(describe1):字段类型为String  
			 */
 			private String describe1; 
 	
			/**
			 *描述(describe2):字段类型为String  
			 */
 			private String describe2; 
 	
			/**
			 *条码(barcode):字段类型为String  
			 */
 			private String barcode; 
 	
			/**
			 *条码图片文件(barcodeFilecode):字段类型为String  
			 */
 			private String barcodeFilecode; 
 	
			/**
			 *状态(state):字段类型为String  
			 */
 			private String state; 
 			/**
 			 * 公司状态
 			 */
 			private String wzState;
 	
			/**
			 *基本单位精度(basicUnitAccuracy):字段类型为String  
			 */
 			private String basicUnitAccuracy; 
 	
			/**
			 *背面印字(backPrinting):字段类型为String  
			 */
 			private String backPrinting; 
 	
			/**
			 *序号(serialNumber):字段类型为Integer  
			 */
 			private Integer serialNumber; 
 	
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
 			 * 注册证文件有效期01
 			 */
 			private String cfca01Date;
 			
 			/**
 			 * 注册证文件有效期02
 			 */
 			private String cfca02Date;
 			
 			
 			/**
 			 * 注册证文件编号01
 			 */
 			private String cfca01Filecode;
 			
 			/**
 			 * 注册证文件编号02
 			 */
 			private String cfca02Filecode;
 			/**
 			 * 注册证文件编号03
 			 */
 			private String cfca03Filecode;
 			
 			
 			/**
 			 * 注册证文件编号04
 			 */
 			private String cfca04Filecode;
 			
 			/**
 			 * 注册证文件编号05
 			 */
 			private String cfca05Filecode;
 			/**
 			 * 注册证文件编号06
 			 */
 			private String cfca06Filecode;

 			private String aliasName;
 			
 			
 	
			/**
			 *设置物料信息表id(wms_mi_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdMaterielInfo.WmsMiId</b>的值
			 */	 
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "wms_mi_id")
			public Integer getWmsMiId(){
			    return this.wmsMiId;
			}

		  /**
		   *设置 wms_mi_id字段方法 
		   *@param att_wmsMiId 输入<b>MdMaterielInfo.wmsMiId</b>字段的值
		   */
		  public void setWmsMiId(Integer att_wmsMiId){
		    this.wmsMiId = att_wmsMiId;
		  }
  
		  
		  
		  
			/**
			 *设置供应商id(wz_id)字段方法 
			 *@return  返回 <b>MdMaterielInfo.WzId</b>的值
			 */	 
			@Column(name = "wz_id" ) 
			public Integer getWzId(){
			    return this.wzId;
			}

		  /**
		   *设置 wz_id字段方法 
		   *@param att_wzId 输入<b>MdMaterielInfo.wzId</b>字段的值
		   */
		  public void setWzId(Integer att_wzId){
		    this.wzId = att_wzId;
		  }
  
			/**
			 *设置单位类型(1:供应商,2:集团,3:医院,4:门诊)(purchase_type)字段方法 
			 *@return  返回 <b>MdMaterielInfo.PurchaseType</b>的值
			 */	 
			@Column(name = "purchase_type" ) 
			public String getPurchaseType(){
			    return this.purchaseType;
			}

		  /**
		   *设置 purchase_type字段方法 
		   *@param att_purchaseType 输入<b>MdMaterielInfo.purchaseType</b>字段的值
		   */
		  public void setPurchaseType(String att_purchaseType){
		    this.purchaseType = att_purchaseType;
		  }
  
			/**
			 *设置物料信息表id(md_wms_mi_id)字段方法 
			 *@return  返回 <b>MdMaterielInfo.MdWmsMiId</b>的值
			 */	 
			@Column(name = "md_wms_mi_id" ) 
			public Integer getMdWmsMiId(){
			    return this.mdWmsMiId;
			}

		  /**
		   *设置 md_wms_mi_id字段方法 
		   *@param att_mdWmsMiId 输入<b>MdMaterielInfo.mdWmsMiId</b>字段的值
		   */
		  public void setMdWmsMiId(Integer att_mdWmsMiId){
		    this.mdWmsMiId = att_mdWmsMiId;
		  }
  
			/**
			 *设置企业名称(applicant_Name)字段方法 
			 *@return  返回 <b>MdMaterielInfo.ApplicantName</b>的值
			 */	 
			@Column(name = "applicant_Name" ) 
			public String getApplicantName(){
			    return this.applicantName;
			}

		  /**
		   *设置 applicant_Name字段方法 
		   *@param att_applicantName 输入<b>MdMaterielInfo.applicantName</b>字段的值
		   */
		  public void setApplicantName(String att_applicantName){
		    this.applicantName = att_applicantName;
		  }
		  
		  

			@Column(name = "product_name" ) 
			public String getProductName() {
				return productName;
			}

			public void setProductName(String productName) {
				this.productName = productName;
			}
			@Column(name = "brand" ) 
			public String getBrand() {
				return brand;
			}
	
	

			public void setBrand(String brand) {
				this.brand = brand;
			}

			/**
			 *设置法人手机号码(Phone_number)字段方法 
			 *@return  返回 <b>MdMaterielInfo.PhoneNumber</b>的值
			 */	 
			@Column(name = "Phone_number" ) 
			public String getPhoneNumber(){
			    return this.phoneNumber;
			}

		  /**
		   *设置 Phone_number字段方法 
		   *@param att_phoneNumber 输入<b>MdMaterielInfo.phoneNumber</b>字段的值
		   */
		  public void setPhoneNumber(String att_phoneNumber){
		    this.phoneNumber = att_phoneNumber;
		  }
  
			/**
			 *设置企业住所地(Corporate_domicile)字段方法 
			 *@return  返回 <b>MdMaterielInfo.CorporateDomicile</b>的值
			 */	 
			@Column(name = "Corporate_domicile" ) 
			public String getCorporateDomicile(){
			    return this.corporateDomicile;
			}

		  /**
		   *设置 Corporate_domicile字段方法 
		   *@param att_corporateDomicile 输入<b>MdMaterielInfo.corporateDomicile</b>字段的值
		   */
		  public void setCorporateDomicile(String att_corporateDomicile){
		    this.corporateDomicile = att_corporateDomicile;
		  }
  
			/**
			 *设置经营范围(scope_business)字段方法 
			 *@return  返回 <b>MdMaterielInfo.ScopeBusiness</b>的值
			 */	 
			@Column(name = "scope_business" ) 
			public String getScopeBusiness(){
			    return this.scopeBusiness;
			}

		  /**
		   *设置 scope_business字段方法 
		   *@param att_scopeBusiness 输入<b>MdMaterielInfo.scopeBusiness</b>字段的值
		   */
		  public void setScopeBusiness(String att_scopeBusiness){
		    this.scopeBusiness = att_scopeBusiness;
		  }
  
			/**
			 *设置物料编码(mat_code)字段方法 
			 *@return  返回 <b>MdMaterielInfo.MatCode</b>的值
			 */	 
			@Column(name = "mat_code" ) 
			public String getMatCode(){
			    return this.matCode;
			}

		  /**
		   *设置 mat_code字段方法 
		   *@param att_matCode 输入<b>MdMaterielInfo.matCode</b>字段的值
		   */
		  public void setMatCode(String att_matCode){
		    this.matCode = att_matCode;
		  }
  
			/**
			 *设置物料名称(mat_name)字段方法 
			 *@return  返回 <b>MdMaterielInfo.MatName</b>的值
			 */	 
			@Column(name = "mat_name" ) 
			public String getMatName(){
			    return this.matName;
			}
			
			@Column(name = "py_name" ) 
		  public String getPyName() {
				return pyName;
			}

			public void setPyName(String pyName) {
				this.pyName = pyName;
			}
			@Column(name = "wz_state" ) 
			public String getWzState() {
				return wzState;
			}

			public void setWzState(String wzState) {
				this.wzState = wzState;
			}

		/**
		   *设置 mat_name字段方法 
		   *@param att_matName 输入<b>MdMaterielInfo.matName</b>字段的值
		   */
		  public void setMatName(String att_matName){
		    this.matName = att_matName;
		  }
  
			/**
			 *设置价格1(money1)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Money1</b>的值
			 */	 
			@Column(name = "money1" ) 
			public Double getMoney1(){
			    return this.money1;
			}

		  /**
		   *设置 money1字段方法 
		   *@param att_money1 输入<b>MdMaterielInfo.money1</b>字段的值
		   */
		  public void setMoney1(Double att_money1){
		    this.money1 = att_money1;
		  }
  
			/**
			 *设置价格1(money2)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Money2</b>的值
			 */	 
			@Column(name = "money2" ) 
			public Double getMoney2(){
			    return this.money2;
			}

		  /**
		   *设置 money2字段方法 
		   *@param att_money2 输入<b>MdMaterielInfo.money2</b>字段的值
		   */
		  public void setMoney2(Double att_money2){
		    this.money2 = att_money2;
		  }
  
			/**
			 *设置价格1(money3)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Money3</b>的值
			 */	 
			@Column(name = "money3" ) 
			public Double getMoney3(){
			    return this.money3;
			}

		  /**
		   *设置 money3字段方法 
		   *@param att_money3 输入<b>MdMaterielInfo.money3</b>字段的值
		   */
		  public void setMoney3(Double att_money3){
		    this.money3 = att_money3;
		  }
  
			/**
			 *设置价格1(money4)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Money4</b>的值
			 */	 
			@Column(name = "money4" ) 
			public Double getMoney4(){
			    return this.money4;
			}

		  /**
		   *设置 money4字段方法 
		   *@param att_money4 输入<b>MdMaterielInfo.money4</b>字段的值
		   */
		  public void setMoney4(Double att_money4){
		    this.money4 = att_money4;
		  }
  
			/**
			 *设置价格1(money5)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Money5</b>的值
			 */	 
			@Column(name = "money5" ) 
			public Double getMoney5(){
			    return this.money5;
			}

		  /**
		   *设置 money5字段方法 
		   *@param att_money5 输入<b>MdMaterielInfo.money5</b>字段的值
		   */
		  public void setMoney5(Double att_money5){
		    this.money5 = att_money5;
		  }
  
			/**
			 *设置数量1(number1)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Number1</b>的值
			 */	 
			@Column(name = "number1" ) 
			public Double getNumber1(){
			    return this.number1;
			}

		  /**
		   *设置 number1字段方法 
		   *@param att_number1 输入<b>MdMaterielInfo.number1</b>字段的值
		   */
		  public void setNumber1(Double att_number1){
		    this.number1 = att_number1;
		  }
  
			/**
			 *设置数量1(number2)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Number2</b>的值
			 */	 
			@Column(name = "number2" ) 
			public Double getNumber2(){
			    return this.number2;
			}

		  /**
		   *设置 number2字段方法 
		   *@param att_number2 输入<b>MdMaterielInfo.number2</b>字段的值
		   */
		  public void setNumber2(Double att_number2){
		    this.number2 = att_number2;
		  }
  
			/**
			 *设置数量1(number3)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Number3</b>的值
			 */	 
			@Column(name = "number3" ) 
			public Double getNumber3(){
			    return this.number3;
			}

		  /**
		   *设置 number3字段方法 
		   *@param att_number3 输入<b>MdMaterielInfo.number3</b>字段的值
		   */
		  public void setNumber3(Double att_number3){
		    this.number3 = att_number3;
		  }
  
			/**
			 *设置数量1(number4)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Number4</b>的值
			 */	 
			@Column(name = "number4" ) 
			public Double getNumber4(){
			    return this.number4;
			}

		  /**
		   *设置 number4字段方法 
		   *@param att_number4 输入<b>MdMaterielInfo.number4</b>字段的值
		   */
		  public void setNumber4(Double att_number4){
		    this.number4 = att_number4;
		  }
  
			/**
			 *设置数量1(number5)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Number5</b>的值
			 */	 
			@Column(name = "number5" ) 
			public Double getNumber5(){
			    return this.number5;
			}
		  /**
		   *设置 number5字段方法 
		   *@param att_number5 输入<b>MdMaterielInfo.number5</b>字段的值
		   */
		  public void setNumber5(Double att_number5){
		    this.number5 = att_number5;
		  }
		 
  
			/**
			 *设置基本单位(Basic_unit)字段方法 
			 *@return  返回 <b>MdMaterielInfo.BasicUnit</b>的值
			 */	 
			@Column(name = "Basic_unit" ) 
			public String getBasicUnit(){
			    return this.basicUnit;
			}
			 /**
			   * yanglei 2019-11-22
			   */
			  
			@Column(name = "product_factory" )
		  public String getProductFactory() {
				return productFactory;
			}

			public void setProductFactory(String productFactory) {
				this.productFactory = productFactory;
			}

		/**
		   *设置 Basic_unit字段方法 
		   *@param att_basicUnit 输入<b>MdMaterielInfo.basicUnit</b>字段的值
		   */
		  public void setBasicUnit(String att_basicUnit){
		    this.basicUnit = att_basicUnit;
		  }
  
			/**
			 *设置批次规则(Batch_rule)字段方法 
			 *@return  返回 <b>MdMaterielInfo.BatchRule</b>的值
			 */	 
			@Column(name = "Batch_rule" ) 
			public String getBatchRule(){
			    return this.batchRule;
			}

		  /**
		   *设置 Batch_rule字段方法 
		   *@param att_batchRule 输入<b>MdMaterielInfo.batchRule</b>字段的值
		   */
		  public void setBatchRule(String att_batchRule){
		    this.batchRule = att_batchRule;
		  }
  
			/**
			 *设置保质期限(VALID_PERIOD)字段方法 
			 *@return  返回 <b>MdMaterielInfo.ValidPeriod</b>的值
			 */	 
			@Column(name = "VALID_PERIOD" ) 
			public Integer getValidPeriod(){
			    return this.validPeriod;
			}

		  /**
		   *设置 VALID_PERIOD字段方法 
		   *@param att_validPeriod 输入<b>MdMaterielInfo.validPeriod</b>字段的值
		   */
		  public void setValidPeriod(Integer att_validPeriod){
		    this.validPeriod = att_validPeriod;
		  }
  
			/**
			 *设置预警时间(ALERT_DAY)字段方法 
			 *@return  返回 <b>MdMaterielInfo.AlertDay</b>的值
			 */	 
			@Column(name = "ALERT_DAY" ) 
			public Integer getAlertDay(){
			    return this.alertDay;
			}

		  /**
		   *设置 ALERT_DAY字段方法 
		   *@param att_alertDay 输入<b>MdMaterielInfo.alertDay</b>字段的值
		   */
		  public void setAlertDay(Integer att_alertDay){
		    this.alertDay = att_alertDay;
		  }
  
			/**
			 *设置规格(NORM)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Norm</b>的值
			 */	 
			@Column(name = "NORM" ) 
			public String getNorm(){
			    return this.norm;
			}

		  /**
		   *设置 NORM字段方法 
		   *@param att_norm 输入<b>MdMaterielInfo.norm</b>字段的值
		   */
		  public void setNorm(String att_norm){
		    this.norm = att_norm;
		  }
  
			/**
			 *设置物料类别(mat_type)字段方法 
			 *@return  返回 <b>MdMaterielInfo.MatType</b>的值
			 */	 
			@Column(name = "mat_type" ) 
			public String getMatType(){
			    return this.matType;
			}

		  /**
		   *设置 mat_type字段方法 
		   *@param att_matType 输入<b>MdMaterielInfo.matType</b>字段的值
		   */
		  public void setMatType(String att_matType){
		    this.matType = att_matType;
		  }
  
			/**
			 *设置物料类别(mat_type1)字段方法 
			 *@return  返回 <b>MdMaterielInfo.MatType1</b>的值
			 */	 
			@Column(name = "mat_type1" ) 
			public String getMatType1(){
			    return this.matType1;
			}

		  /**
		   *设置 mat_type1字段方法 
		   *@param att_matType1 输入<b>MdMaterielInfo.matType1</b>字段的值
		   */
		  public void setMatType1(String att_matType1){
		    this.matType1 = att_matType1;
		  }
  
			/**
			 *设置拣选分类(mat_type2)字段方法 
			 *@return  返回 <b>MdMaterielInfo.MatType2</b>的值
			 */	 
			@Column(name = "mat_type2" ) 
			public String getMatType2(){
			    return this.matType2;
			}

		  /**
		   *设置 mat_type2字段方法 
		   *@param att_matType2 输入<b>MdMaterielInfo.matType2</b>字段的值
		   */
		  public void setMatType2(String att_matType2){
		    this.matType2 = att_matType2;
		  }
  
			/**
			 *设置码托分类(mat_type3)字段方法 
			 *@return  返回 <b>MdMaterielInfo.MatType3</b>的值
			 */	 
			@Column(name = "mat_type3" ) 
			public String getMatType3(){
			    return this.matType3;
			}

		  /**
		   *设置 mat_type3字段方法 
		   *@param att_matType3 输入<b>MdMaterielInfo.matType3</b>字段的值
		   */
		  public void setMatType3(String att_matType3){
		    this.matType3 = att_matType3;
		  }
  
			/**
			 *设置标注(Label_info)字段方法 
			 *@return  返回 <b>MdMaterielInfo.LabelInfo</b>的值
			 */	 
			@Column(name = "Label_info" ) 
			public String getLabelInfo(){
			    return this.labelInfo;
			}

		  /**
		   *设置 Label_info字段方法 
		   *@param att_labelInfo 输入<b>MdMaterielInfo.labelInfo</b>字段的值
		   */
		  public void setLabelInfo(String att_labelInfo){
		    this.labelInfo = att_labelInfo;
		  }
  
			/**
			 *设置缩小图(lessen_filecode)字段方法 
			 *@return  返回 <b>MdMaterielInfo.LessenFilecode</b>的值
			 */	 
			@Column(name = "lessen_filecode" ) 
			public String getLessenFilecode(){
			    return this.lessenFilecode;
			}

		  /**
		   *设置 lessen_filecode字段方法 
		   *@param att_lessenFilecode 输入<b>MdMaterielInfo.lessenFilecode</b>字段的值
		   */
		  public void setLessenFilecode(String att_lessenFilecode){
		    this.lessenFilecode = att_lessenFilecode;
		  }
  
			/**
			 *设置描述(describe1)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Describe1</b>的值
			 */	 
			@Column(name = "describe1" ) 
			public String getDescribe1(){
			    return this.describe1;
			}

		  /**
		   *设置 describe1字段方法 
		   *@param att_describe1 输入<b>MdMaterielInfo.describe1</b>字段的值
		   */
		  public void setDescribe1(String att_describe1){
		    this.describe1 = att_describe1;
		  }
  
			/**
			 *设置描述(describe2)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Describe2</b>的值
			 */	 
			@Column(name = "describe2" ) 
			public String getDescribe2(){
			    return this.describe2;
			}

		  /**
		   *设置 describe2字段方法 
		   *@param att_describe2 输入<b>MdMaterielInfo.describe2</b>字段的值
		   */
		  public void setDescribe2(String att_describe2){
		    this.describe2 = att_describe2;
		  }
  
			/**
			 *设置条码(barCode)字段方法 
			 *@return  返回 <b>MdMaterielInfo.Barcode</b>的值
			 */	 
			@Column(name = "barCode" ) 
			public String getBarcode(){
			    return this.barcode;
			}

		  /**
		   *设置 barCode字段方法 
		   *@param att_barcode 输入<b>MdMaterielInfo.barcode</b>字段的值
		   */
		  public void setBarcode(String att_barcode){
		    this.barcode = att_barcode;
		  }
  
			/**
			 *设置条码图片文件(barCode_filecode)字段方法 
			 *@return  返回 <b>MdMaterielInfo.BarcodeFilecode</b>的值
			 */	 
			@Column(name = "barCode_filecode" ) 
			public String getBarcodeFilecode(){
			    return this.barcodeFilecode;
			}

		  /**
		   *设置 barCode_filecode字段方法 
		   *@param att_barcodeFilecode 输入<b>MdMaterielInfo.barcodeFilecode</b>字段的值
		   */
		  public void setBarcodeFilecode(String att_barcodeFilecode){
		    this.barcodeFilecode = att_barcodeFilecode;
		  }
  
		  
		  
		    @Column(name = "cfca01_filecode" )
			public String getCfca01Filecode() {
			return cfca01Filecode;
			}
	
			public void setCfca01Filecode(String cfca01Filecode) {
				this.cfca01Filecode = cfca01Filecode;
			}
			
			 @Column(name = "cfca02_filecode" )
			public String getCfca02Filecode() {
				return cfca02Filecode;
			}

			public void setCfca02Filecode(String cfca02Filecode) {
				this.cfca02Filecode = cfca02Filecode;
			}
			 @Column(name = "cfca03_filecode" )
			public String getCfca03Filecode() {
				return cfca03Filecode;
			}

			public void setCfca03Filecode(String cfca03Filecode) {
				this.cfca03Filecode = cfca03Filecode;
			}
			 @Column(name = "cfca04_filecode" )
			public String getCfca04Filecode() {
				return cfca04Filecode;
			}

			public void setCfca04Filecode(String cfca04Filecode) {
				this.cfca04Filecode = cfca04Filecode;
			}
			 @Column(name = "cfca05_filecode" )
			public String getCfca05Filecode() {
				return cfca05Filecode;
			}

			public void setCfca05Filecode(String cfca05Filecode) {
				this.cfca05Filecode = cfca05Filecode;
			}
			 @Column(name = "cfca06_filecode" )
			public String getCfca06Filecode() {
				return cfca06Filecode;
			}

			public void setCfca06Filecode(String cfca06Filecode) {
				this.cfca06Filecode = cfca06Filecode;
			}

			
			
			

			/**
			 *设置状态(state)字段方法 
			 *@return  返回 <b>MdMaterielInfo.State</b>的值
			 */	 
			@Column(name = "state" ) 
			public String getState(){
			    return this.state;
			}

		  /**
		   *设置 state字段方法 
		   *@param att_state 输入<b>MdMaterielInfo.state</b>字段的值
		   */
		  public void setState(String att_state){
		    this.state = att_state;
		  }
  
			/**
			 *设置基本单位精度(Basic_unit_accuracy)字段方法 
			 *@return  返回 <b>MdMaterielInfo.BasicUnitAccuracy</b>的值
			 */	 
			@Column(name = "Basic_unit_accuracy" ) 
			public String getBasicUnitAccuracy(){
			    return this.basicUnitAccuracy;
			}

		  /**
		   *设置 Basic_unit_accuracy字段方法 
		   *@param att_basicUnitAccuracy 输入<b>MdMaterielInfo.basicUnitAccuracy</b>字段的值
		   */
		  public void setBasicUnitAccuracy(String att_basicUnitAccuracy){
		    this.basicUnitAccuracy = att_basicUnitAccuracy;
		  }
  
			/**
			 *设置背面印字(back_Printing)字段方法 
			 *@return  返回 <b>MdMaterielInfo.BackPrinting</b>的值
			 */	 
			@Column(name = "back_Printing" ) 
			public String getBackPrinting(){
			    return this.backPrinting;
			}

		  /**
		   *设置 back_Printing字段方法 
		   *@param att_backPrinting 输入<b>MdMaterielInfo.backPrinting</b>字段的值
		   */
		  public void setBackPrinting(String att_backPrinting){
		    this.backPrinting = att_backPrinting;
		  }
  
			/**
			 *设置序号(Serial_number)字段方法 
			 *@return  返回 <b>MdMaterielInfo.SerialNumber</b>的值
			 */	 
			@Column(name = "Serial_number" ) 
			public Integer getSerialNumber(){
			    return this.serialNumber;
			}

		  /**
		   *设置 Serial_number字段方法 
		   *@param att_serialNumber 输入<b>MdMaterielInfo.serialNumber</b>字段的值
		   */
		  public void setSerialNumber(Integer att_serialNumber){
		    this.serialNumber = att_serialNumber;
		  }
  
			/**
			 *设置创建人(create_ren)字段方法 
			 *@return  返回 <b>MdMaterielInfo.CreateRen</b>的值
			 */	 
			@Column(name = "create_ren" ) 
			public String getCreateRen(){
			    return this.createRen;
			}

		  /**
		   *设置 create_ren字段方法 
		   *@param att_createRen 输入<b>MdMaterielInfo.createRen</b>字段的值
		   */
		  public void setCreateRen(String att_createRen){
		    this.createRen = att_createRen;
		  }
  
			/**
			 *设置创建时间(create_date)字段方法 
			 *@return  返回 <b>MdMaterielInfo.CreateDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "create_date" ) 
			public Date getCreateDate(){
			    return this.createDate;
			}

		  /**
		   *设置 create_date字段方法 
		   *@param att_createDate 输入<b>MdMaterielInfo.createDate</b>字段的值
		   */
		  public void setCreateDate(Date att_createDate){
		    this.createDate = att_createDate;
		  }
  
			/**
			 *设置修改人(edit_ren)字段方法 
			 *@return  返回 <b>MdMaterielInfo.EditRen</b>的值
			 */	 
			@Column(name = "edit_ren" ) 
			public String getEditRen(){
			    return this.editRen;
			}

		  /**
		   *设置 edit_ren字段方法 
		   *@param att_editRen 输入<b>MdMaterielInfo.editRen</b>字段的值
		   */
		  public void setEditRen(String att_editRen){
		    this.editRen = att_editRen;
		  }
  
			/**
			 *设置修改时间(edit_date)字段方法 
			 *@return  返回 <b>MdMaterielInfo.EditDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "edit_date" ) 
			public Date getEditDate(){
			    return this.editDate;
			}

		  /**
		   *设置 edit_date字段方法 
		   *@param att_editDate 输入<b>MdMaterielInfo.editDate</b>字段的值
		   */
		  public void setEditDate(Date att_editDate){
		    this.editDate = att_editDate;
		  }
  

///===============数据库表字段属性end==========
		  
		  
			@Column(name = "cfca01_Date" )
			public String getCfca01Date() {
				return cfca01Date;
			}

			public void setCfca01Date(String cfca01Date) {
				this.cfca01Date = cfca01Date;
			}
			 
			@Column(name = "cfca02_Date" )
			public String getCfca02Date() {
				return cfca02Date;
			}

			public void setCfca02Date(String cfca02Date) {
				this.cfca02Date = cfca02Date;
			}
		  

///===============数据库表无关子段字段属性begin==========
			/**
			 *物料信息表id(wms_mi_id):字段类型为String
			 */
		  private String wmsMiId_str;  
			/**
			 *供应商id(wz_id):字段类型为String
			 */
		  private String wzId_str;  
			/**
			 *物料信息表id(md_wms_mi_id):字段类型为String
			 */
		  private String mdWmsMiId_str;  
			/**
			 *保质期限(VALID_PERIOD):字段类型为String
			 */
		  private String validPeriod_str;  
			/**
			 *预警时间(ALERT_DAY):字段类型为String
			 */
		  private String alertDay_str;  
			/**
			 *序号(Serial_number):字段类型为String
			 */
		  private String serialNumber_str;  
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
			 *md_materiel_info表里不用like作为条件的字符串
			 */
		  private String Tab_like;  
			/**
			 *():字段类型为String
			 *md_materiel_info表里order by 字符串
			 */
		  private String Tab_order;  
			/**
			 *设置wmsMiId字段方法  
			 *@return  返回 <b>MdMaterielInfo.wmsMiId</b>的值
			 */ 
			@Transient
			public String getWmsMiId_str(){
				return this.wmsMiId_str;
			}
			/**
			  *设置 wms_mi_id字段方法 
			  *@param att_wmsMiId 输入<b>MdMaterielInfo.wmsMiId</b>字段的值
			  */
			public void setWmsMiId_str(String att_wmsMiId_str){
				this.wmsMiId_str = att_wmsMiId_str;
			}
			/**
			 *设置wzId字段方法  
			 *@return  返回 <b>MdMaterielInfo.wzId</b>的值
			 */ 
			@Transient
			public String getWzId_str(){
				return this.wzId_str;
			}
			/**
			  *设置 wz_id字段方法 
			  *@param att_wzId 输入<b>MdMaterielInfo.wzId</b>字段的值
			  */
			public void setWzId_str(String att_wzId_str){
				this.wzId_str = att_wzId_str;
			}
			/**
			 *设置mdWmsMiId字段方法  
			 *@return  返回 <b>MdMaterielInfo.mdWmsMiId</b>的值
			 */ 
			@Transient
			public String getMdWmsMiId_str(){
				return this.mdWmsMiId_str;
			}
			/**
			  *设置 md_wms_mi_id字段方法 
			  *@param att_mdWmsMiId 输入<b>MdMaterielInfo.mdWmsMiId</b>字段的值
			  */
			public void setMdWmsMiId_str(String att_mdWmsMiId_str){
				this.mdWmsMiId_str = att_mdWmsMiId_str;
			}
			/**
			 *设置validPeriod字段方法  
			 *@return  返回 <b>MdMaterielInfo.validPeriod</b>的值
			 */ 
			@Transient
			public String getValidPeriod_str(){
				return this.validPeriod_str;
			}
			/**
			  *设置 VALID_PERIOD字段方法 
			  *@param att_validPeriod 输入<b>MdMaterielInfo.validPeriod</b>字段的值
			  */
			public void setValidPeriod_str(String att_validPeriod_str){
				this.validPeriod_str = att_validPeriod_str;
			}
			/**
			 *设置alertDay字段方法  
			 *@return  返回 <b>MdMaterielInfo.alertDay</b>的值
			 */ 
			@Transient
			public String getAlertDay_str(){
				return this.alertDay_str;
			}
			/**
			  *设置 ALERT_DAY字段方法 
			  *@param att_alertDay 输入<b>MdMaterielInfo.alertDay</b>字段的值
			  */
			public void setAlertDay_str(String att_alertDay_str){
				this.alertDay_str = att_alertDay_str;
			}
			/**
			 *设置serialNumber字段方法  
			 *@return  返回 <b>MdMaterielInfo.serialNumber</b>的值
			 */ 
			@Transient
			public String getSerialNumber_str(){
				return this.serialNumber_str;
			}
			/**
			  *设置 Serial_number字段方法 
			  *@param att_serialNumber 输入<b>MdMaterielInfo.serialNumber</b>字段的值
			  */
			public void setSerialNumber_str(String att_serialNumber_str){
				this.serialNumber_str = att_serialNumber_str;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdMaterielInfo.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_start(){
				return this.createDate_start;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdMaterielInfo.createDate</b>字段的值
			  */
			public void setCreateDate_start(Date att_createDate_start){
				this.createDate_start = att_createDate_start;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdMaterielInfo.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_end(){
				return this.createDate_end;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdMaterielInfo.createDate</b>字段的值
			  */
			public void setCreateDate_end(Date att_createDate_end){
				this.createDate_end = att_createDate_end;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdMaterielInfo.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_start(){
				return this.editDate_start;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdMaterielInfo.editDate</b>字段的值
			  */
			public void setEditDate_start(Date att_editDate_start){
				this.editDate_start = att_editDate_start;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdMaterielInfo.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_end(){
				return this.editDate_end;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdMaterielInfo.editDate</b>字段的值
			  */
			public void setEditDate_end(Date att_editDate_end){
				this.editDate_end = att_editDate_end;
			}
			/**
			 *设置tab_like字段方法  
			 *@return  返回 <b>MdMaterielInfo.tab_like</b>的值
			 */ 
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_like 输入<b>MdMaterielInfo.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法  
			 *@return  返回 <b>MdMaterielInfo.tab_order</b>的值
			 */ 
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_order 输入<b>MdMaterielInfo.tab_order</b>字段的值
			  */
			public void setTab_order(String att_Tab_order){
				this.Tab_order = att_Tab_order;
			}
///===============数据库表无关子段字段属性end==========
			private String mdWmsMiName;
			private String listFilecode;
			private String lessenFilePath;
			//文件名
			private String cfca01Filename;
			//文件路径
			private String cfca01FilePath;
			
			////生产日期
			private Date productTime;
			//有效日期
			private Date productValitime;
			//批次号
			private String batchCertNo;
			@Formula("(SELECT a.product_time FROM md_enter_warehouse_mx  a WHERE a.wms_mi_id=wms_mi_id LIMIT 1)")
			public Date getProductTime() {
				return productTime;
			}

			public void setProductTime(Date productTime) {
				this.productTime = productTime;
			}
			@Formula("(SELECT a.product_valitime FROM md_enter_warehouse_mx a WHERE a.wms_mi_id=wms_mi_id LIMIT 1)")
			public Date getProductValitime() {
				return productValitime;
			}

			public void setProductValitime(Date productValitime) {
				this.productValitime = productValitime;
			}
			@Formula("(SELECT a.batch_certNo FROM md_enter_warehouse_mx a WHERE a.wms_mi_id=wms_mi_id LIMIT 1)")
			public String getBatchCertNo() {
				return batchCertNo;
			}

			public void setBatchCertNo(String batchCertNo) {
				this.batchCertNo = batchCertNo;
			}


			//文件名2
			private String cfca02Filename;
			//文件路径2
			//生产厂家
			private String cfca02FilePath;
			
			
			
			//文件名3
			private String cfca03Filename;
			//文件路径3
			private String cfca03FilePath;
			
			
			//文件名4
			private String cfca04Filename;
			//文件路径4
			private String cfca04FilePath;
			 
			
			//文件名5
			private String cfca05Filename;
			//文件路径5
			private String cfca05FilePath;
			
			//文件名6
			private String cfca06Filename;
			//文件路径6
			private String cfca06FilePath;
			
			
			@Formula("(SELECT a.file_name FROM sys_file_info_temp a WHERE a.file_code= cfca01_filecode)")
			public String getCfca01Filename() {
				return cfca01Filename;
			}

			public void setCfca01Filename(String cfca01Filename) {
				this.cfca01Filename = cfca01Filename;
			} 
			
			
			@Formula("(SELECT a.root_path FROM sys_file_info_temp a WHERE a.file_code= cfca01_filecode)")
			public String getCfca01FilePath() {
				return cfca01FilePath;
			}

			public void setCfca01FilePath(String cfca01FilePath) {
				this.cfca01FilePath = cfca01FilePath;
			} 
			@Formula("(SELECT a.file_name FROM sys_file_info_temp a WHERE a.file_code= cfca02_filecode)")
			
			public String getCfca02Filename() {
				return cfca02Filename;
			}

			public void setCfca02Filename(String cfca02Filename) {
				this.cfca02Filename = cfca02Filename;
			}
			
			@Formula("(SELECT a.root_path FROM sys_file_info_temp a WHERE a.file_code= cfca02_filecode)")
			public String getCfca02FilePath() {
				return cfca02FilePath;
			}

			public void setCfca02FilePath(String cfca02FilePath) {
				this.cfca02FilePath = cfca02FilePath;
			}
			
			@Formula("(SELECT a.file_name FROM sys_file_info_temp a WHERE a.file_code= cfca03_filecode)")
			public String getCfca03Filename() {
				return cfca03Filename;
			}

			public void setCfca03Filename(String cfca03Filename) {
				this.cfca03Filename = cfca03Filename;
			}
			@Formula("(SELECT a.root_path FROM sys_file_info_temp a WHERE a.file_code= cfca03_filecode)")
			public String getCfca03FilePath() {
				return cfca03FilePath;
			}

			public void setCfca03FilePath(String cfca03FilePath) {
				this.cfca03FilePath = cfca03FilePath;
			}
			
			@Formula("(SELECT a.file_name FROM sys_file_info_temp a WHERE a.file_code= cfca04_filecode)")
			
			public String getCfca04Filename() {
				return cfca04Filename;
			}

			public void setCfca04Filename(String cfca04Filename) {
				this.cfca04Filename = cfca04Filename;
			}
			@Formula("(SELECT a.root_path FROM sys_file_info_temp a WHERE a.file_code= cfca04_filecode)")
			public String getCfca04FilePath() {
				return cfca04FilePath;
			}

			public void setCfca04FilePath(String cfca04FilePath) {
				this.cfca04FilePath = cfca04FilePath;
			}
			
			@Formula("(SELECT a.file_name FROM sys_file_info_temp a WHERE a.file_code= cfca05_filecode)") 
			public String getCfca05Filename() {
				return cfca05Filename;
			}

			public void setCfca05Filename(String cfca05Filename) {
				this.cfca05Filename = cfca05Filename;
			}
			
			@Formula("(SELECT a.root_path FROM sys_file_info_temp a WHERE a.file_code= cfca05_filecode)")
			public String getCfca05FilePath() {
				return cfca05FilePath;
			}

			public void setCfca05FilePath(String cfca05FilePath) {
				this.cfca05FilePath = cfca05FilePath;
			}
			@Formula("(SELECT a.file_name FROM sys_file_info_temp a WHERE a.file_code= cfca06_filecode)")
			public String getCfca06Filename() {
				return cfca06Filename;
			}

			public void setCfca06Filename(String cfca06Filename) {
				this.cfca06Filename = cfca06Filename;
			}

			@Formula("(SELECT a.root_path FROM sys_file_info_temp a WHERE a.file_code= cfca06_filecode)")
			public String getCfca06FilePath() {
				return cfca06FilePath;
			}

			public void setCfca06FilePath(String cfca06FilePath) {
				this.cfca06FilePath = cfca06FilePath;
			}

			@Formula("(select t.mmt_name from md_materiel_type t where t.mmt_id=md_wms_mi_id)")
			public String getMdWmsMiName() {
				return mdWmsMiName;
			}

			public void setMdWmsMiName(String mdWmsMiName) {
				this.mdWmsMiName = mdWmsMiName;
			}
			@Transient
			public String getListFilecode() {
				return listFilecode;
			}

			public void setListFilecode(String listFilecode) {
				this.listFilecode = listFilecode;
			}
			
			@Formula("(SELECT a.root_path FROM sys_file_info_temp a WHERE a.file_code= lessen_filecode)")
			public String getLessenFilePath() {
				return lessenFilePath;
			}

			public void setLessenFilePath(String lessenFilePath) {
				this.lessenFilePath = lessenFilePath;
			}

			/*别名字段*/
			@Column(name = "alias_name")
			public String getAliasName() {
				return aliasName;
			}

			public void setAliasName(String aliasName) {
				this.aliasName = aliasName;
			}
}