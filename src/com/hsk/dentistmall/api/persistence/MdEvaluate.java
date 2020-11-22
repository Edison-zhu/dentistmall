package com.hsk.dentistmall.api.persistence;

import javax.persistence.*; 

import org.hibernate.annotations.Formula;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

/**
 * 根据md_evaluate表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdEvaluate</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table> 
 * <tr><th>字段名称:</th><td>评价信息表id(mevaId)</td><th>字段类型:</th><td>Integer(主键)</td></tr> 
 * <tr><th>字段名称:</th><td>规格ID(mmfId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>价格点评(priceReview)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>价格比较(priceComparison)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>点评内容(reviewContent)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>差价(difference)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>预购数量(preOrderNumber)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>状态(state)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>点评时间(evaluateTime)</td><th>字段类型:</th><td>Date</td></tr> 
 * <tr><th>字段名称:</th><td>点评人昵称(evaluateRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>点评人账号(evaluateZh)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>企业名称(applicantName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>法人手机号码(phoneNumber)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>企业住所地(corporateDomicile)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>生产厂家(productName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>物料编码(matCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>物料名称(matName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>规格编号(mmfCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>规格名称(mmfName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>规格说明(remark)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>价格(price)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * </table>
 *===============无关字段属性===============
 *  <table> 
 * 	<tr><th>字段名称:</th><td>评价信息表id(mevaId)	</td><th>属性名称:</th><td>mevaId</td></tr>
 * 	<tr><th>字段名称:</th><td>规格ID(mmfId)	</td><th>属性名称:</th><td>mmfId</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>点评时间(evaluateTime)	</td><th>属性名称:</th><td>evaluateTime</td></tr>
 * 	<tr><th>字段名称:</th><td>点评时间(evaluateTime)	</td><th>属性名称:</th><td>evaluateTime</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 * @author  作者:admin  
 * @version  v1.0 创建时间: 2018-03-13 14:44:35
 */
 
 
@SuppressWarnings("serial")
@Entity
@Table(name = "md_evaluate" )
public class MdEvaluate {
///===============数据库表字段属性begin==========
			/**
			 *评价信息表id(mevaId):字段类型为Integer  
			 */
 			private Integer mevaId; 
 			/**
 			 * 商品ID
 			 */
 			private Integer wmsMiId;
			/**
			 *规格ID(mmfId):字段类型为Integer  
			 */
 			private Integer mmfId; 
 	
			/**
			 *价格点评(priceReview):字段类型为String  
			 */
 			private String priceReview; 
 	
			/**
			 *价格比较(priceComparison):字段类型为String  
			 */
 			private String priceComparison; 
 	
			/**
			 *点评内容(reviewContent):字段类型为String  
			 */
 			private String reviewContent; 
 	
			/**
			 *差价(difference):字段类型为Double  
			 */
 			private Double difference; 
 	
			/**
			 *预购数量(preOrderNumber):字段类型为Double  
			 */
 			private Double preOrderNumber; 
 	
			/**
			 *状态(state):字段类型为String  
			 */
 			private String state; 
 	
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
 			 * 点评人ID
 			 */
 			private Integer suiId;
			/**
			 *点评时间(evaluateTime):字段类型为Date  
			 */
 			private Date evaluateTime; 
 	
			/**
			 *点评人昵称(evaluateRen):字段类型为String  
			 */
 			private String evaluateRen; 
 			/**
 			 * 点评人所在公司
 			 */
 			private String  orgName;
 	
			/**
			 *点评人账号(evaluateZh):字段类型为String  
			 */
 			private String evaluateZh; 
 	
			/**
			 *企业名称(applicantName):字段类型为String  
			 */
 			private String applicantName; 
 	
			/**
			 *法人手机号码(phoneNumber):字段类型为String  
			 */
 			private String phoneNumber; 
 	
			/**
			 *企业住所地(corporateDomicile):字段类型为String  
			 */
 			private String corporateDomicile; 
 	
			/**
			 *生产厂家(productName):字段类型为String  
			 */
 			private String productName; 
 	
			/**
			 *物料编码(matCode):字段类型为String  
			 */
 			private String matCode; 
 	
			/**
			 *物料名称(matName):字段类型为String  
			 */
 			private String matName; 
 	
			/**
			 *规格编号(mmfCode):字段类型为String  
			 */
 			private String mmfCode; 
 	
			/**
			 *规格名称(mmfName):字段类型为String  
			 */
 			private String mmfName; 
 	
			/**
			 *规格说明(remark):字段类型为String  
			 */
 			private String remark; 
 	
			/**
			 *价格(price):字段类型为Double  
			 */
 			private Double price; 
 			 private String evaluatePhoneNumber;
			/**
			 *设置评价信息表id(meva_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdEvaluate.MevaId</b>的值
			 */	 
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "meva_id")
			public Integer getMevaId(){
			    return this.mevaId;
			}

		  /**
		   *设置 meva_id字段方法 
		   *@param att_mevaId 输入<b>MdEvaluate.mevaId</b>字段的值
		   */
		  public void setMevaId(Integer att_mevaId){
		    this.mevaId = att_mevaId;
		  }
  
		  
		 
		  
		  
		  
		  @Column(name = "evaluate_Phone_number" ) 
			public String getEvaluatePhoneNumber() {
			return evaluatePhoneNumber;
		}

		public void setEvaluatePhoneNumber(String evaluatePhoneNumber) {
			this.evaluatePhoneNumber = evaluatePhoneNumber;
		}

			@Column(name = "org_name" ) 
			public String getOrgName() {
			return orgName;
		}

		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}

			/**
			 *设置规格ID(mmf_id)字段方法 
			 *@return  返回 <b>MdEvaluate.MmfId</b>的值
			 */	 
			@Column(name = "mmf_id" ) 
			public Integer getMmfId(){
			    return this.mmfId;
			}

		  /**
		   *设置 mmf_id字段方法 
		   *@param att_mmfId 输入<b>MdEvaluate.mmfId</b>字段的值
		   */
		  public void setMmfId(Integer att_mmfId){
		    this.mmfId = att_mmfId;
		  }
  
			/**
			 *设置价格点评(Price_review)字段方法 
			 *@return  返回 <b>MdEvaluate.PriceReview</b>的值
			 */	 
			@Column(name = "Price_review" ) 
			public String getPriceReview(){
			    return this.priceReview;
			}

		  /**
		   *设置 Price_review字段方法 
		   *@param att_priceReview 输入<b>MdEvaluate.priceReview</b>字段的值
		   */
		  public void setPriceReview(String att_priceReview){
		    this.priceReview = att_priceReview;
		  }
  
			/**
			 *设置价格比较(Price_comparison)字段方法 
			 *@return  返回 <b>MdEvaluate.PriceComparison</b>的值
			 */	 
			@Column(name = "Price_comparison" ) 
			public String getPriceComparison(){
			    return this.priceComparison;
			}

		  /**
		   *设置 Price_comparison字段方法 
		   *@param att_priceComparison 输入<b>MdEvaluate.priceComparison</b>字段的值
		   */
		  public void setPriceComparison(String att_priceComparison){
		    this.priceComparison = att_priceComparison;
		  }
  
			/**
			 *设置点评内容(review_content)字段方法 
			 *@return  返回 <b>MdEvaluate.ReviewContent</b>的值
			 */	 
			@Column(name = "review_content" ) 
			public String getReviewContent(){
			    return this.reviewContent;
			}

		  /**
		   *设置 review_content字段方法 
		   *@param att_reviewContent 输入<b>MdEvaluate.reviewContent</b>字段的值
		   */
		  public void setReviewContent(String att_reviewContent){
		    this.reviewContent = att_reviewContent;
		  }
  
			/**
			 *设置差价(difference)字段方法 
			 *@return  返回 <b>MdEvaluate.Difference</b>的值
			 */	 
			@Column(name = "difference" ) 
			public Double getDifference(){
			    return this.difference;
			}

		  /**
		   *设置 difference字段方法 
		   *@param att_difference 输入<b>MdEvaluate.difference</b>字段的值
		   */
		  public void setDifference(Double att_difference){
		    this.difference = att_difference;
		  }
  
			/**
			 *设置预购数量(Pre_order_number)字段方法 
			 *@return  返回 <b>MdEvaluate.PreOrderNumber</b>的值
			 */	 
			@Column(name = "Pre_order_number" ) 
			public Double getPreOrderNumber(){
			    return this.preOrderNumber;
			}

		  /**
		   *设置 Pre_order_number字段方法 
		   *@param att_preOrderNumber 输入<b>MdEvaluate.preOrderNumber</b>字段的值
		   */
		  public void setPreOrderNumber(Double att_preOrderNumber){
		    this.preOrderNumber = att_preOrderNumber;
		  }
  
			/**
			 *设置状态(state)字段方法 
			 *@return  返回 <b>MdEvaluate.State</b>的值
			 */	 
			@Column(name = "state" ) 
			public String getState(){
			    return this.state;
			}

		  /**
		   *设置 state字段方法 
		   *@param att_state 输入<b>MdEvaluate.state</b>字段的值
		   */
		  public void setState(String att_state){
		    this.state = att_state;
		  }
  
			/**
			 *设置创建人(create_ren)字段方法 
			 *@return  返回 <b>MdEvaluate.CreateRen</b>的值
			 */	 
			@Column(name = "create_ren" ) 
			public String getCreateRen(){
			    return this.createRen;
			}

		  /**
		   *设置 create_ren字段方法 
		   *@param att_createRen 输入<b>MdEvaluate.createRen</b>字段的值
		   */
		  public void setCreateRen(String att_createRen){
		    this.createRen = att_createRen;
		  }
  
			/**
			 *设置创建时间(create_date)字段方法 
			 *@return  返回 <b>MdEvaluate.CreateDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "create_date" ) 
			public Date getCreateDate(){
			    return this.createDate;
			}

		  /**
		   *设置 create_date字段方法 
		   *@param att_createDate 输入<b>MdEvaluate.createDate</b>字段的值
		   */
		  public void setCreateDate(Date att_createDate){
		    this.createDate = att_createDate;
		  }
  
			/**
			 *设置修改人(edit_ren)字段方法 
			 *@return  返回 <b>MdEvaluate.EditRen</b>的值
			 */	 
			@Column(name = "edit_ren" ) 
			public String getEditRen(){
			    return this.editRen;
			}

		  /**
		   *设置 edit_ren字段方法 
		   *@param att_editRen 输入<b>MdEvaluate.editRen</b>字段的值
		   */
		  public void setEditRen(String att_editRen){
		    this.editRen = att_editRen;
		  }
  
			/**
			 *设置修改时间(edit_date)字段方法 
			 *@return  返回 <b>MdEvaluate.EditDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "edit_date" ) 
			public Date getEditDate(){
			    return this.editDate;
			}

		  /**
		   *设置 edit_date字段方法 
		   *@param att_editDate 输入<b>MdEvaluate.editDate</b>字段的值
		   */
		  public void setEditDate(Date att_editDate){
		    this.editDate = att_editDate;
		  }
  
			/**
			 *设置点评时间(evaluate_time)字段方法 
			 *@return  返回 <b>MdEvaluate.EvaluateTime</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "evaluate_time" ) 
			public Date getEvaluateTime(){
			    return this.evaluateTime;
			}

		  /**
		   *设置 evaluate_time字段方法 
		   *@param att_evaluateTime 输入<b>MdEvaluate.evaluateTime</b>字段的值
		   */
		  public void setEvaluateTime(Date att_evaluateTime){
		    this.evaluateTime = att_evaluateTime;
		  }
  
			/**
			 *设置点评人昵称(evaluate_ren)字段方法 
			 *@return  返回 <b>MdEvaluate.EvaluateRen</b>的值
			 */	 
			@Column(name = "evaluate_ren" ) 
			public String getEvaluateRen(){
			    return this.evaluateRen;
			}

		  /**
		   *设置 evaluate_ren字段方法 
		   *@param att_evaluateRen 输入<b>MdEvaluate.evaluateRen</b>字段的值
		   */
		  public void setEvaluateRen(String att_evaluateRen){
		    this.evaluateRen = att_evaluateRen;
		  }
  
			/**
			 *设置点评人账号(evaluate_zh)字段方法 
			 *@return  返回 <b>MdEvaluate.EvaluateZh</b>的值
			 */	 
			@Column(name = "evaluate_zh" ) 
			public String getEvaluateZh(){
			    return this.evaluateZh;
			}

		  /**
		   *设置 evaluate_zh字段方法 
		   *@param att_evaluateZh 输入<b>MdEvaluate.evaluateZh</b>字段的值
		   */
		  public void setEvaluateZh(String att_evaluateZh){
		    this.evaluateZh = att_evaluateZh;
		  }
  
			/**
			 *设置企业名称(applicant_Name)字段方法 
			 *@return  返回 <b>MdEvaluate.ApplicantName</b>的值
			 */	 
			@Column(name = "applicant_Name" ) 
			public String getApplicantName(){
			    return this.applicantName;
			}

		  /**
		   *设置 applicant_Name字段方法 
		   *@param att_applicantName 输入<b>MdEvaluate.applicantName</b>字段的值
		   */
		  public void setApplicantName(String att_applicantName){
		    this.applicantName = att_applicantName;
		  }
  
			/**
			 *设置法人手机号码(Phone_number)字段方法 
			 *@return  返回 <b>MdEvaluate.PhoneNumber</b>的值
			 */	 
			@Column(name = "Phone_number" ) 
			public String getPhoneNumber(){
			    return this.phoneNumber;
			}

		  /**
		   *设置 Phone_number字段方法 
		   *@param att_phoneNumber 输入<b>MdEvaluate.phoneNumber</b>字段的值
		   */
		  public void setPhoneNumber(String att_phoneNumber){
		    this.phoneNumber = att_phoneNumber;
		  }
  
			/**
			 *设置企业住所地(Corporate_domicile)字段方法 
			 *@return  返回 <b>MdEvaluate.CorporateDomicile</b>的值
			 */	 
			@Column(name = "Corporate_domicile" ) 
			public String getCorporateDomicile(){
			    return this.corporateDomicile;
			}

		  /**
		   *设置 Corporate_domicile字段方法 
		   *@param att_corporateDomicile 输入<b>MdEvaluate.corporateDomicile</b>字段的值
		   */
		  public void setCorporateDomicile(String att_corporateDomicile){
		    this.corporateDomicile = att_corporateDomicile;
		  }
  
			/**
			 *设置生产厂家(product_name)字段方法 
			 *@return  返回 <b>MdEvaluate.ProductName</b>的值
			 */	 
			@Column(name = "product_name" ) 
			public String getProductName(){
			    return this.productName;
			}

		  /**
		   *设置 product_name字段方法 
		   *@param att_productName 输入<b>MdEvaluate.productName</b>字段的值
		   */
		  public void setProductName(String att_productName){
		    this.productName = att_productName;
		  }
  
			/**
			 *设置物料编码(mat_code)字段方法 
			 *@return  返回 <b>MdEvaluate.MatCode</b>的值
			 */	 
			@Column(name = "mat_code" ) 
			public String getMatCode(){
			    return this.matCode;
			}

		  /**
		   *设置 mat_code字段方法 
		   *@param att_matCode 输入<b>MdEvaluate.matCode</b>字段的值
		   */
		  public void setMatCode(String att_matCode){
		    this.matCode = att_matCode;
		  }
  
			/**
			 *设置物料名称(mat_name)字段方法 
			 *@return  返回 <b>MdEvaluate.MatName</b>的值
			 */	 
			@Column(name = "mat_name" ) 
			public String getMatName(){
			    return this.matName;
			}

		  /**
		   *设置 mat_name字段方法 
		   *@param att_matName 输入<b>MdEvaluate.matName</b>字段的值
		   */
		  public void setMatName(String att_matName){
		    this.matName = att_matName;
		  }
  
			/**
			 *设置规格编号(mmf_code)字段方法 
			 *@return  返回 <b>MdEvaluate.MmfCode</b>的值
			 */	 
			@Column(name = "mmf_code" ) 
			public String getMmfCode(){
			    return this.mmfCode;
			}

		  /**
		   *设置 mmf_code字段方法 
		   *@param att_mmfCode 输入<b>MdEvaluate.mmfCode</b>字段的值
		   */
		  public void setMmfCode(String att_mmfCode){
		    this.mmfCode = att_mmfCode;
		  }
  
			/**
			 *设置规格名称(mmf_name)字段方法 
			 *@return  返回 <b>MdEvaluate.MmfName</b>的值
			 */	 
			@Column(name = "mmf_name" ) 
			public String getMmfName(){
			    return this.mmfName;
			}

		  /**
		   *设置 mmf_name字段方法 
		   *@param att_mmfName 输入<b>MdEvaluate.mmfName</b>字段的值
		   */
		  public void setMmfName(String att_mmfName){
		    this.mmfName = att_mmfName;
		  }
  
			/**
			 *设置规格说明(remark)字段方法 
			 *@return  返回 <b>MdEvaluate.Remark</b>的值
			 */	 
			@Column(name = "remark" ) 
			public String getRemark(){
			    return this.remark;
			}

		  /**
		   *设置 remark字段方法 
		   *@param att_remark 输入<b>MdEvaluate.remark</b>字段的值
		   */
		  public void setRemark(String att_remark){
		    this.remark = att_remark;
		  }
  
			/**
			 *设置价格(price)字段方法 
			 *@return  返回 <b>MdEvaluate.Price</b>的值
			 */	 
			@Column(name = "price" ) 
			public Double getPrice(){
			    return this.price;
			}

		  /**
		   *设置 price字段方法 
		   *@param att_price 输入<b>MdEvaluate.price</b>字段的值
		   */
		  public void setPrice(Double att_price){
		    this.price = att_price;
		  }
  
		@Column(name = "wms_mi_id" ) 
		public Integer getWmsMiId() {
			return wmsMiId;
		}

		public void setWmsMiId(Integer wmsMiId) {
			this.wmsMiId = wmsMiId;
		}
		@Column(name = "sui_id" ) 
		public Integer getSuiId() {
			return suiId;
		}

		public void setSuiId(Integer suiId) {
			this.suiId = suiId;
		}
		///===============数据库表字段属性end==========
		  private String barCodePath;
		  @Column(name = "bar_Code_Path" ) 
			public String getBarCodePath() {
				return barCodePath;
			}

			public void setBarCodePath(String barCodePath) {
				this.barCodePath = barCodePath;
			}
			

///===============数据库表无关子段字段属性begin==========
			/**
			 *评价信息表id(meva_id):字段类型为String
			 */
		  private String mevaId_str;  
			/**
			 *规格ID(mmf_id):字段类型为String
			 */
		  private String mmfId_str;  
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
			 *点评时间(evaluate_time):字段类型为Date
			 */
		  private Date evaluateTime_start;  
			/**
			 *点评时间(evaluate_time):字段类型为Date
			 */
		  private Date evaluateTime_end;  
			/**
			 *():字段类型为String
			 *md_evaluate表里不用like作为条件的字符串
			 */
		  private String Tab_like;  
			/**
			 *():字段类型为String
			 *md_evaluate表里order by 字符串
			 */
		  private String Tab_order;  
		  
		  
		  private Integer number01;
		  private Integer number02;
		  private Integer number03; 
		  private Integer number04;  
		  
		  @Transient
		  public Integer getNumber04() {
			return (number01!=null?number01:0)+(number02!=null?number02:0)+(number03!=null?number03:0);
		}

		public void setNumber04(Integer number04) {
			this.number04 = number04;
		}

		@Transient
			public Integer getNumber01() {
			return number01;
		}

		public void setNumber01(Integer number01) {
			this.number01 = number01;
		}
		@Transient
		public Integer getNumber02() {
			return number02;
		}

		public void setNumber02(Integer number02) {
			this.number02 = number02;
		}
		@Transient
		public Integer getNumber03() {
			return number03;
		}

		public void setNumber03(Integer number03) {
			this.number03 = number03;
		}

			/**
			 *设置mevaId字段方法  
			 *@return  返回 <b>MdEvaluate.mevaId</b>的值
			 */ 
			@Transient
			public String getMevaId_str(){
				return this.mevaId_str;
			}
			/**
			  *设置 meva_id字段方法 
			  *@param att_mevaId 输入<b>MdEvaluate.mevaId</b>字段的值
			  */
			public void setMevaId_str(String att_mevaId_str){
				this.mevaId_str = att_mevaId_str;
			}
			/**
			 *设置mmfId字段方法  
			 *@return  返回 <b>MdEvaluate.mmfId</b>的值
			 */ 
			@Transient
			public String getMmfId_str(){
				return this.mmfId_str;
			}
			/**
			  *设置 mmf_id字段方法 
			  *@param att_mmfId 输入<b>MdEvaluate.mmfId</b>字段的值
			  */
			public void setMmfId_str(String att_mmfId_str){
				this.mmfId_str = att_mmfId_str;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdEvaluate.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_start(){
				return this.createDate_start;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdEvaluate.createDate</b>字段的值
			  */
			public void setCreateDate_start(Date att_createDate_start){
				this.createDate_start = att_createDate_start;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdEvaluate.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_end(){
				return this.createDate_end;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdEvaluate.createDate</b>字段的值
			  */
			public void setCreateDate_end(Date att_createDate_end){
				this.createDate_end = att_createDate_end;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdEvaluate.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_start(){
				return this.editDate_start;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdEvaluate.editDate</b>字段的值
			  */
			public void setEditDate_start(Date att_editDate_start){
				this.editDate_start = att_editDate_start;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdEvaluate.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_end(){
				return this.editDate_end;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdEvaluate.editDate</b>字段的值
			  */
			public void setEditDate_end(Date att_editDate_end){
				this.editDate_end = att_editDate_end;
			}
			/**
			 *设置evaluateTime字段方法  
			 *@return  返回 <b>MdEvaluate.evaluateTime</b>的值
			 */ 
			@Transient
			public Date getEvaluateTime_start(){
				return this.evaluateTime_start;
			}
			/**
			  *设置 evaluate_time字段方法 
			  *@param att_evaluateTime 输入<b>MdEvaluate.evaluateTime</b>字段的值
			  */
			public void setEvaluateTime_start(Date att_evaluateTime_start){
				this.evaluateTime_start = att_evaluateTime_start;
			}
			/**
			 *设置evaluateTime字段方法  
			 *@return  返回 <b>MdEvaluate.evaluateTime</b>的值
			 */ 
			@Transient
			public Date getEvaluateTime_end(){
				return this.evaluateTime_end;
			}
			/**
			  *设置 evaluate_time字段方法 
			  *@param att_evaluateTime 输入<b>MdEvaluate.evaluateTime</b>字段的值
			  */
			public void setEvaluateTime_end(Date att_evaluateTime_end){
				this.evaluateTime_end = att_evaluateTime_end;
			}
			/**
			 *设置tab_like字段方法  
			 *@return  返回 <b>MdEvaluate.tab_like</b>的值
			 */ 
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_like 输入<b>MdEvaluate.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法  
			 *@return  返回 <b>MdEvaluate.tab_order</b>的值
			 */ 
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_order 输入<b>MdEvaluate.tab_order</b>字段的值
			  */
			public void setTab_order(String att_Tab_order){
				this.Tab_order = att_Tab_order;
			}
///===============数据库表无关子段字段属性end==========
			
			private String barRootPath;
			
			@Formula("(SELECT b.root_path FROM sys_user_info a,sys_file_info b WHERE a.barcode=b.file_code and a.sui_id=sui_id)")
			public String getBarRootPath() {
				return barRootPath;
			}

			public void setBarRootPath(String barRootPath) {
				this.barRootPath = barRootPath;
			}
} 