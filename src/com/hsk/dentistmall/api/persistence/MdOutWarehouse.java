package com.hsk.dentistmall.api.persistence;

import javax.persistence.*; 

import org.hibernate.annotations.Formula;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

/**
 * 根据md_out_warehouse表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdOutWarehouse</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table> 
 * <tr><th>字段名称:</th><td>出库单信息表id(wowId)</td><th>字段类型:</th><td>Integer(主键)</td></tr> 
 * <tr><th>字段名称:</th><td>集团公司id(rbaId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>牙医医院id(rbsId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>牙医门诊id(rbbId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>采购单位类型(purchaseType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>货主(ower)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>单据类型(companyType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>发货单号(wowCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>销售订单号(relatedBill1)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>相关单号2(relatedBill2)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>相关单号3(relatedBill3)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>收货方(customer)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>收货人姓名(customerName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>状态(1:打开,2生效,3:失效4:拣货中,5:分配中,6:作业中,7作业完成)(state)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>基本数量(baseNumber)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>描述(description)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>完成时间(finshDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>业务人员 /申领人/外借人---相关人员(userId)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>业务人员所属部门/部门(groupId)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>生效时间(activeTime)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * </table>
 *===============无关字段属性===============
 *  <table> 
 * 	<tr><th>字段名称:</th><td>出库单信息表id(wowId)	</td><th>属性名称:</th><td>wowId</td></tr>
 * 	<tr><th>字段名称:</th><td>集团公司id(rbaId)	</td><th>属性名称:</th><td>rbaId</td></tr>
 * 	<tr><th>字段名称:</th><td>牙医医院id(rbsId)	</td><th>属性名称:</th><td>rbsId</td></tr>
 * 	<tr><th>字段名称:</th><td>牙医门诊id(rbbId)	</td><th>属性名称:</th><td>rbbId</td></tr>
 * 	<tr><th>字段名称:</th><td>完成时间(finshDate)	</td><th>属性名称:</th><td>finshDate</td></tr>
 * 	<tr><th>字段名称:</th><td>完成时间(finshDate)	</td><th>属性名称:</th><td>finshDate</td></tr>
 * 	<tr><th>字段名称:</th><td>生效时间(activeTime)	</td><th>属性名称:</th><td>activeTime</td></tr>
 * 	<tr><th>字段名称:</th><td>生效时间(activeTime)	</td><th>属性名称:</th><td>activeTime</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 * @author  作者:admin  
 * @version  v1.0 创建时间: 2017-09-25 14:14:55
 */
 
 
@SuppressWarnings("serial")
@Entity
@Table(name = "md_out_warehouse" )
public class MdOutWarehouse {
///===============数据库表字段属性begin==========
			/**
			 *出库单信息表id(wowId):字段类型为Integer  
			 */
 			private Integer wowId; 
 	
			/**
			 *集团公司id(rbaId):字段类型为Integer  
			 */
 			private Integer rbaId; 
 	
			/**
			 *牙医医院id(rbsId):字段类型为Integer  
			 */
 			private Integer rbsId; 
 	
			/**
			 *牙医门诊id(rbbId):字段类型为Integer  
			 */
 			private Integer rbbId; 
 			/**
 			 * 用户姓名
 			 */
 			private Integer suiId;
 	
			/**
			 *采购单位类型(purchaseType):字段类型为String  
			 */
 			private String purchaseType; 
 	
			/**
			 *货主(ower):字段类型为String  
			 */
 			private String ower; 
 	
			/**
			 *单据类型(companyType):字段类型为String  
			 */
 			private String companyType; 
 	
			/**
			 *发货单号(wowCode):字段类型为String  
			 */
 			private String wowCode; 
 	
			/**
			 *销售订单号(relatedBill1):字段类型为String  
			 */
 			private String relatedBill1; 
 	
			/**
			 *相关单号2(relatedBill2):字段类型为String  
			 */
 			private String relatedBill2; 
 	
			/**
			 *相关单号3(relatedBill3):字段类型为String  
			 */
 			private String relatedBill3; 
 			
 			/**
			 *收货人(consignee):字段类型为String  
			 */
 			private String consignee; 
 			/**
			 *供应商名称(supplierName):字段类型为String  
			 */
 			private String supplierName; 
 	
			/**
			 *收货方(customer):字段类型为String  
			 */
 			private String customer; 
 	
			/**
			 *收货人姓名(customerName):字段类型为String  
			 */
 			private String customerName; 
 	
			/**
			 *状态(1:打开,2生效,3:失效4:拣货中,5:分配中,6:作业中,7作业完成)(state):字段类型为String  
			 */
 			private String state; 
 	
			/**
			 *基本数量(baseNumber):字段类型为Double  
			 */
 			private Double baseNumber; 
 	
			/**
			 *描述(description):字段类型为String  
			 */
 			private String description; 
 	
			/**
			 *完成时间(finshDate):字段类型为Date  
			 */
 			private Date finshDate; 
 			
 			private Date orderTime;
 	
			/**
			 *业务人员 /申领人/外借人---相关人员(userId):字段类型为String  
			 */
 			private String userName; 
 	
			/**
			 *业务人员所属部门/部门(groupId):字段类型为String  
			 */
 			private String groupName; 
 	
			/**
			 *生效时间(activeTime):字段类型为Date  
			 */
 			private Date activeTime; 
 	
			/**
			 *修改时间(editDate):字段类型为Date  
			 */
 			private Date editDate; 
 	
			/**
			 *修改人(editRen):字段类型为String  
			 */
 			private String editRen; 
 	
			/**
			 *创建时间(createDate):字段类型为Date  
			 */
 			private Date createDate; 
 	
			/**
			 *创建人(createRen):字段类型为String  
			 */
 			private String createRen;

			/**
			 * 实际申领数量
			 */
			private Double allNumber;

			private Double splitNumber;
	private Double number1;
	private String remarks;
	private Integer wewId;

	private Integer isDelete;

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	/**
			 *设置出库单信息表id(wow_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdOutWarehouse.WowId</b>的值
			 */	 
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "wow_id")
			public Integer getWowId(){
			    return this.wowId;
			}

		  /**
		   *设置 wow_id字段方法 
		   *@param att_wowId 输入<b>MdOutWarehouse.wowId</b>字段的值
		   */
		  public void setWowId(Integer att_wowId){
		    this.wowId = att_wowId;
		  }
  
			/**
			 *设置集团公司id(rba_id)字段方法 
			 *@return  返回 <b>MdOutWarehouse.RbaId</b>的值
			 */	 
			@Column(name = "rba_id" ) 
			public Integer getRbaId(){
			    return this.rbaId;
			}

		  /**
		   *设置 rba_id字段方法 
		   *@param att_rbaId 输入<b>MdOutWarehouse.rbaId</b>字段的值
		   */
		  public void setRbaId(Integer att_rbaId){
		    this.rbaId = att_rbaId;
		  }
  
			/**
			 *设置牙医医院id(rbs_id)字段方法 
			 *@return  返回 <b>MdOutWarehouse.RbsId</b>的值
			 */	 
			@Column(name = "rbs_id" ) 
			public Integer getRbsId(){
			    return this.rbsId;
			}

		  /**
		   *设置 rbs_id字段方法 
		   *@param att_rbsId 输入<b>MdOutWarehouse.rbsId</b>字段的值
		   */
		  public void setRbsId(Integer att_rbsId){
		    this.rbsId = att_rbsId;
		  }
  
			/**
			 *设置牙医门诊id(rbb_id)字段方法 
			 *@return  返回 <b>MdOutWarehouse.RbbId</b>的值
			 */	 
			@Column(name = "rbb_id" ) 
			public Integer getRbbId(){
			    return this.rbbId;
			}

		  /**
		   *设置 rbb_id字段方法 
		   *@param att_rbbId 输入<b>MdOutWarehouse.rbbId</b>字段的值
		   */
		  public void setRbbId(Integer att_rbbId){
		    this.rbbId = att_rbbId;
		  }
		  
		  
		  @Column(name = "sui_id" )
			public Integer getSuiId() {
			return suiId;
		}

		public void setSuiId(Integer suiId) {
			this.suiId = suiId;
		}

			/**
			 *设置采购单位类型(purchase_type)字段方法 
			 *@return  返回 <b>MdOutWarehouse.PurchaseType</b>的值
			 */	 
			@Column(name = "purchase_type" ) 
			public String getPurchaseType(){
			    return this.purchaseType;
			}

		  /**
		   *设置 purchase_type字段方法 
		   *@param att_purchaseType 输入<b>MdOutWarehouse.purchaseType</b>字段的值
		   */
		  public void setPurchaseType(String att_purchaseType){
		    this.purchaseType = att_purchaseType;
		  }
  
			/**
			 *设置货主(ower)字段方法 
			 *@return  返回 <b>MdOutWarehouse.Ower</b>的值
			 */	 
			@Column(name = "ower" ) 
			public String getOwer(){
			    return this.ower;
			}

		  /**
		   *设置 ower字段方法 
		   *@param att_ower 输入<b>MdOutWarehouse.ower</b>字段的值
		   */
		  public void setOwer(String att_ower){
		    this.ower = att_ower;
		  }
  
			/**
			 *设置单据类型(COMPANY_type)字段方法 
			 *@return  返回 <b>MdOutWarehouse.CompanyType</b>的值
			 */	 
			@Column(name = "COMPANY_type" ) 
			public String getCompanyType(){
			    return this.companyType;
			}

		  /**
		   *设置 COMPANY_type字段方法 
		   *@param att_companyType 输入<b>MdOutWarehouse.companyType</b>字段的值
		   */
		  public void setCompanyType(String att_companyType){
		    this.companyType = att_companyType;
		  }
  
			/**
			 *设置发货单号(wow_code)字段方法 
			 *@return  返回 <b>MdOutWarehouse.WowCode</b>的值
			 */	 
			@Column(name = "wow_code" ) 
			public String getWowCode(){
			    return this.wowCode;
			}

		  /**
		   *设置 wow_code字段方法 
		   *@param att_wowCode 输入<b>MdOutWarehouse.wowCode</b>字段的值
		   */
		  public void setWowCode(String att_wowCode){
		    this.wowCode = att_wowCode;
		  }
  
			/**
			 *设置销售订单号(RELATED_BILL1)字段方法 
			 *@return  返回 <b>MdOutWarehouse.RelatedBill1</b>的值
			 */	 
			@Column(name = "RELATED_BILL1" ) 
			public String getRelatedBill1(){
			    return this.relatedBill1;
			}

		  /**
		   *设置 RELATED_BILL1字段方法 
		   *@param att_relatedBill1 输入<b>MdOutWarehouse.relatedBill1</b>字段的值
		   */
		  public void setRelatedBill1(String att_relatedBill1){
		    this.relatedBill1 = att_relatedBill1;
		  }
  
			/**
			 *设置相关单号2(RELATED_BILL2)字段方法 
			 *@return  返回 <b>MdOutWarehouse.RelatedBill2</b>的值
			 */	 
			@Column(name = "RELATED_BILL2" ) 
			public String getRelatedBill2(){
			    return this.relatedBill2;
			}

		  /**
		   *设置 RELATED_BILL2字段方法 
		   *@param att_relatedBill2 输入<b>MdOutWarehouse.relatedBill2</b>字段的值
		   */
		  public void setRelatedBill2(String att_relatedBill2){
		    this.relatedBill2 = att_relatedBill2;
		  }
  
			/**
			 *设置相关单号3(RELATED_BILL3)字段方法 
			 *@return  返回 <b>MdOutWarehouse.RelatedBill3</b>的值
			 */	 
			@Column(name = "RELATED_BILL3" ) 
			public String getRelatedBill3(){
			    return this.relatedBill3;
			}

		  /**
		   *设置 RELATED_BILL3字段方法 
		   *@param att_relatedBill3 输入<b>MdOutWarehouse.relatedBill3</b>字段的值
		   */
		  public void setRelatedBill3(String att_relatedBill3){
		    this.relatedBill3 = att_relatedBill3;
		  }
		  /**
			 *设置收货人(Consignee)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.Consignee</b>的值
			 */	 
			@Column(name = "Consignee" ) 
			public String getConsignee(){
			    return this.consignee;
			}

		  /**
		   *设置 Consignee字段方法 
		   *@param att_consignee 输入<b>MdEnterWarehouse.consignee</b>字段的值
		   */
		  public void setConsignee(String att_consignee){
		    this.consignee = att_consignee;
		  }
		  /**
			 *设置供应商名称(Supplier_name)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.SupplierName</b>的值
			 */	 
			@Column(name = "Supplier_name" ) 
			public String getSupplierName(){
			    return this.supplierName;
			}

		  /**
		   *设置 Supplier_name字段方法 
		   *@param att_supplierName 输入<b>MdEnterWarehouse.supplierName</b>字段的值
		   */
		  public void setSupplierName(String att_supplierName){
		    this.supplierName = att_supplierName;
		  }
			/**
			 *设置收货方(CUSTOMER)字段方法 
			 *@return  返回 <b>MdOutWarehouse.Customer</b>的值
			 */	 
			@Column(name = "CUSTOMER" ) 
			public String getCustomer(){
			    return this.customer;
			}

		  /**
		   *设置 CUSTOMER字段方法 
		   *@param att_customer 输入<b>MdOutWarehouse.customer</b>字段的值
		   */
		  public void setCustomer(String att_customer){
		    this.customer = att_customer;
		  }
  
			/**
			 *设置收货人姓名(CUSTOMER_name)字段方法 
			 *@return  返回 <b>MdOutWarehouse.CustomerName</b>的值
			 */	 
			@Column(name = "CUSTOMER_name" ) 
			public String getCustomerName(){
			    return this.customerName;
			}

		  /**
		   *设置 CUSTOMER_name字段方法 
		   *@param att_customerName 输入<b>MdOutWarehouse.customerName</b>字段的值
		   */
		  public void setCustomerName(String att_customerName){
		    this.customerName = att_customerName;
		  }
  
			/**
			 *设置状态(1:打开,2生效,3:失效4:拣货中,5:分配中,6:作业中,7作业完成)(state)字段方法 
			 *@return  返回 <b>MdOutWarehouse.State</b>的值
			 */	 
			@Column(name = "state" ) 
			public String getState(){
			    return this.state;
			}

		  /**
		   *设置 state字段方法 
		   *@param att_state 输入<b>MdOutWarehouse.state</b>字段的值
		   */
		  public void setState(String att_state){
		    this.state = att_state;
		  }
  
			/**
			 *设置基本数量(base_number)字段方法 
			 *@return  返回 <b>MdOutWarehouse.BaseNumber</b>的值
			 */	 
			@Column(name = "base_number" ) 
			public Double getBaseNumber(){
			    return this.baseNumber;
			}

		  /**
		   *设置 base_number字段方法 
		   *@param att_baseNumber 输入<b>MdOutWarehouse.baseNumber</b>字段的值
		   */
		  public void setBaseNumber(Double att_baseNumber){
		    this.baseNumber = att_baseNumber;
		  }
  
			/**
			 *设置描述(DESCRIPTION)字段方法 
			 *@return  返回 <b>MdOutWarehouse.Description</b>的值
			 */	 
			@Column(name = "DESCRIPTION" ) 
			public String getDescription(){
			    return this.description;
			}

		  /**
		   *设置 DESCRIPTION字段方法 
		   *@param att_description 输入<b>MdOutWarehouse.description</b>字段的值
		   */
		  public void setDescription(String att_description){
		    this.description = att_description;
		  }
  
			/**
			 *设置完成时间(FINSH_DATE)字段方法 
			 *@return  返回 <b>MdOutWarehouse.FinshDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "FINSH_DATE" ) 
			public Date getFinshDate(){
			    return this.finshDate;
			}

		  /**
		   *设置 FINSH_DATE字段方法 
		   *@param att_finshDate 输入<b>MdOutWarehouse.finshDate</b>字段的值
		   */
		  public void setFinshDate(Date att_finshDate){
		    this.finshDate = att_finshDate;
		  }
		  
		  	@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "order_time" ) 
			public Date getOrderTime() {
				return orderTime;
			}
	
			public void setOrderTime(Date orderTime) {
				this.orderTime = orderTime;
			}

			/**
			 *设置业务人员 /申领人/外借人---相关人员(USER_id)字段方法 
			 *@return  返回 <b>MdOutWarehouse.UserId</b>的值
			 */	 
			@Column(name = "USER_name" ) 
			public String getUserName(){
			    return this.userName;
			}

		  /**
		   *设置 USER_id字段方法 
		   *@param att_userId 输入<b>MdOutWarehouse.userId</b>字段的值
		   */
		  public void setUserName(String att_userName){
		    this.userName = att_userName;
		  }
  
			/**
			 *设置业务人员所属部门/部门(GROUP_ID)字段方法 
			 *@return  返回 <b>MdOutWarehouse.GroupId</b>的值
			 */	 
			@Column(name = "GROUP_NAME" ) 
			public String getGroupName(){
			    return this.groupName;
			}

		  /**
		   *设置 GROUP_ID字段方法 
		   *@param att_groupId 输入<b>MdOutWarehouse.groupId</b>字段的值
		   */
		  public void setGroupName(String att_groupName){
		    this.groupName = att_groupName;
		  }
  
			/**
			 *设置生效时间(ACTIVE_TIME)字段方法 
			 *@return  返回 <b>MdOutWarehouse.ActiveTime</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "ACTIVE_TIME" ) 
			public Date getActiveTime(){
			    return this.activeTime;
			}

		  /**
		   *设置 ACTIVE_TIME字段方法 
		   *@param att_activeTime 输入<b>MdOutWarehouse.activeTime</b>字段的值
		   */
		  public void setActiveTime(Date att_activeTime){
		    this.activeTime = att_activeTime;
		  }
  
			/**
			 *设置修改时间(edit_date)字段方法 
			 *@return  返回 <b>MdOutWarehouse.EditDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "edit_date" ) 
			public Date getEditDate(){
			    return this.editDate;
			}

		  /**
		   *设置 edit_date字段方法 
		   *@param att_editDate 输入<b>MdOutWarehouse.editDate</b>字段的值
		   */
		  public void setEditDate(Date att_editDate){
		    this.editDate = att_editDate;
		  }
  
			/**
			 *设置修改人(edit_ren)字段方法 
			 *@return  返回 <b>MdOutWarehouse.EditRen</b>的值
			 */	 
			@Column(name = "edit_ren" ) 
			public String getEditRen(){
			    return this.editRen;
			}

		  /**
		   *设置 edit_ren字段方法 
		   *@param att_editRen 输入<b>MdOutWarehouse.editRen</b>字段的值
		   */
		  public void setEditRen(String att_editRen){
		    this.editRen = att_editRen;
		  }
  
			/**
			 *设置创建时间(create_date)字段方法 
			 *@return  返回 <b>MdOutWarehouse.CreateDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "create_date" ) 
			public Date getCreateDate(){
			    return this.createDate;
			}

		  /**
		   *设置 create_date字段方法 
		   *@param att_createDate 输入<b>MdOutWarehouse.createDate</b>字段的值
		   */
		  public void setCreateDate(Date att_createDate){
		    this.createDate = att_createDate;
		  }
  
			/**
			 *设置创建人(create_ren)字段方法 
			 *@return  返回 <b>MdOutWarehouse.CreateRen</b>的值
			 */	 
			@Column(name = "create_ren" ) 
			public String getCreateRen(){
			    return this.createRen;
			}

		  /**
		   *设置 create_ren字段方法 
		   *@param att_createRen 输入<b>MdOutWarehouse.createRen</b>字段的值
		   */
		  public void setCreateRen(String att_createRen){
		    this.createRen = att_createRen;
		  }
  

///===============数据库表字段属性end==========

///===============数据库表无关子段字段属性begin==========
			/**
			 *出库单信息表id(wow_id):字段类型为String
			 */
		  private String wowId_str;  
			/**
			 *集团公司id(rba_id):字段类型为String
			 */
		  private String rbaId_str;  
			/**
			 *牙医医院id(rbs_id):字段类型为String
			 */
		  private String rbsId_str;  
			/**
			 *牙医门诊id(rbb_id):字段类型为String
			 */
		  private String rbbId_str;  
			/**
			 *完成时间(FINSH_DATE):字段类型为Date
			 */
		  private Date finshDate_start;  
			/**
			 *完成时间(FINSH_DATE):字段类型为Date
			 */
		  private Date finshDate_end;  
			/**
			 *生效时间(ACTIVE_TIME):字段类型为Date
			 */
		  private Date activeTime_start;  
			/**
			 *生效时间(ACTIVE_TIME):字段类型为Date
			 */
		  private Date activeTime_end;  
			/**
			 *修改时间(edit_date):字段类型为Date
			 */
		  private Date editDate_start;  
			/**
			 *修改时间(edit_date):字段类型为Date
			 */
		  private Date editDate_end;  
			/**
			 *创建时间(create_date):字段类型为Date
			 */
		  private Date createDate_start;  
			/**
			 *创建时间(create_date):字段类型为Date
			 */
		  private Date createDate_end;

			/**
			 * 申领单开始时间
			 */
			private Date orderTime_start;
			/**
			 * 申领单结束时间
			 */
		  private Date orderTime_end;
			/**
			 *():字段类型为String
			 *md_out_warehouse表里不用like作为条件的字符串
			 */
		  private String Tab_like;  
			/**
			 *():字段类型为String
			 *md_out_warehouse表里order by 字符串
			 */
		  private String Tab_order;  
			/**
			 *设置wowId字段方法  
			 *@return  返回 <b>MdOutWarehouse.wowId</b>的值
			 */ 
			@Transient
			public String getWowId_str(){
				return this.wowId_str;
			}
			/**
			  *设置 wow_id字段方法 
			  *@param att_wowId 输入<b>MdOutWarehouse.wowId</b>字段的值
			  */
			public void setWowId_str(String att_wowId_str){
				this.wowId_str = att_wowId_str;
			}
			/**
			 *设置rbaId字段方法  
			 *@return  返回 <b>MdOutWarehouse.rbaId</b>的值
			 */ 
			@Transient
			public String getRbaId_str(){
				return this.rbaId_str;
			}
			/**
			  *设置 rba_id字段方法 
			  *@param att_rbaId 输入<b>MdOutWarehouse.rbaId</b>字段的值
			  */
			public void setRbaId_str(String att_rbaId_str){
				this.rbaId_str = att_rbaId_str;
			}
			/**
			 *设置rbsId字段方法  
			 *@return  返回 <b>MdOutWarehouse.rbsId</b>的值
			 */ 
			@Transient
			public String getRbsId_str(){
				return this.rbsId_str;
			}
			/**
			  *设置 rbs_id字段方法 
			  *@param att_rbsId 输入<b>MdOutWarehouse.rbsId</b>字段的值
			  */
			public void setRbsId_str(String att_rbsId_str){
				this.rbsId_str = att_rbsId_str;
			}
			/**
			 *设置rbbId字段方法  
			 *@return  返回 <b>MdOutWarehouse.rbbId</b>的值
			 */ 
			@Transient
			public String getRbbId_str(){
				return this.rbbId_str;
			}
			/**
			  *设置 rbb_id字段方法 
			  *@param att_rbbId 输入<b>MdOutWarehouse.rbbId</b>字段的值
			  */
			public void setRbbId_str(String att_rbbId_str){
				this.rbbId_str = att_rbbId_str;
			}
			/**
			 *设置finshDate字段方法  
			 *@return  返回 <b>MdOutWarehouse.finshDate</b>的值
			 */ 
			@Transient
			public Date getFinshDate_start(){
				return this.finshDate_start;
			}
			/**
			  *设置 FINSH_DATE字段方法 
			  *@param att_finshDate 输入<b>MdOutWarehouse.finshDate</b>字段的值
			  */
			public void setFinshDate_start(Date att_finshDate_start){
				this.finshDate_start = att_finshDate_start;
			}
			/**
			 *设置finshDate字段方法  
			 *@return  返回 <b>MdOutWarehouse.finshDate</b>的值
			 */ 
			@Transient
			public Date getFinshDate_end(){
				return this.finshDate_end;
			}
			/**
			  *设置 FINSH_DATE字段方法 
			  *@param att_finshDate 输入<b>MdOutWarehouse.finshDate</b>字段的值
			  */
			public void setFinshDate_end(Date att_finshDate_end){
				this.finshDate_end = att_finshDate_end;
			}
			/**
			 *设置activeTime字段方法  
			 *@return  返回 <b>MdOutWarehouse.activeTime</b>的值
			 */ 
			@Transient
			public Date getActiveTime_start(){
				return this.activeTime_start;
			}
			/**
			  *设置 ACTIVE_TIME字段方法 
			  *@param att_activeTime 输入<b>MdOutWarehouse.activeTime</b>字段的值
			  */
			public void setActiveTime_start(Date att_activeTime_start){
				this.activeTime_start = att_activeTime_start;
			}
			/**
			 *设置activeTime字段方法  
			 *@return  返回 <b>MdOutWarehouse.activeTime</b>的值
			 */ 
			@Transient
			public Date getActiveTime_end(){
				return this.activeTime_end;
			}
			/**
			  *设置 ACTIVE_TIME字段方法 
			  *@param att_activeTime 输入<b>MdOutWarehouse.activeTime</b>字段的值
			  */
			public void setActiveTime_end(Date att_activeTime_end){
				this.activeTime_end = att_activeTime_end;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdOutWarehouse.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_start(){
				return this.editDate_start;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdOutWarehouse.editDate</b>字段的值
			  */
			public void setEditDate_start(Date att_editDate_start){
				this.editDate_start = att_editDate_start;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdOutWarehouse.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_end(){
				return this.editDate_end;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdOutWarehouse.editDate</b>字段的值
			  */
			public void setEditDate_end(Date att_editDate_end){
				this.editDate_end = att_editDate_end;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdOutWarehouse.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_start(){
				return this.createDate_start;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdOutWarehouse.createDate</b>字段的值
			  */
			public void setCreateDate_start(Date att_createDate_start){
				this.createDate_start = att_createDate_start;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdOutWarehouse.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_end(){
				return this.createDate_end;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdOutWarehouse.createDate</b>字段的值
			  */
			public void setCreateDate_end(Date att_createDate_end){
				this.createDate_end = att_createDate_end;
			}
			/**
			 *设置tab_like字段方法  
			 *@return  返回 <b>MdOutWarehouse.tab_like</b>的值
			 */ 
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_like 输入<b>MdOutWarehouse.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法  
			 *@return  返回 <b>MdOutWarehouse.tab_order</b>的值
			 */ 
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_order 输入<b>MdOutWarehouse.tab_order</b>字段的值
			  */
			public void setTab_order(String att_Tab_order){
				this.Tab_order = att_Tab_order;
			}

			@Transient
			public Date getOrderTime_start() {
				return orderTime_start;
			}

			public void setOrderTime_start(Date orderTime_start) {
				this.orderTime_start = orderTime_start;
			}

			@Transient
			public Date getOrderTime_end() {
				return orderTime_end;
			}

			public void setOrderTime_end(Date orderTime_end) {
				this.orderTime_end = orderTime_end;
			}

			@Transient
			public Double getAllNumber() {
				return allNumber;
			}

			public void setAllNumber(Double allNumber) {
				this.allNumber = allNumber;
			}
			//	设置数据表无关字段 用于批量导出时获取数据使用
			/**
			 * 2019-12-04
			 * yanglei
			 */
			private String flowState;
			@Formula("(SELECT o.flow_state FROM md_out_order o WHERE o.moo_code=RELATED_BILL1 LIMIT 1)")
			public String getFlowState() {
				return flowState;
			}

			public void setFlowState(String flowState) {
				this.flowState = flowState;
			}
			
			
			
	///===============数据库表无关子段字段属性end==========


	@Column(name = "split_number")
	public Double getSplitNumber() {
		return splitNumber;
	}

	public void setSplitNumber(Double splitNumber) {
		this.splitNumber = splitNumber;
	}

	private Integer moiId;
			private Integer wiId;

			@Column(name = "moi_id")
	public Integer getMoiId() {
		return moiId;
	}

	public void setMoiId(Integer moiId) {
		this.moiId = moiId;
	}

	@Column(name = "wi_id")
	public Integer getWiId() {
		return wiId;
	}

	public void setWiId(Integer wiId) {
		this.wiId = wiId;
	}

	private Integer wowType;
			private String wowRemarks;
			private String receivingObject;

	@Column(name = "wow_type")
	public Integer getWowType() {
		return wowType;
	}

	public void setWowType(Integer wowType) {
		this.wowType = wowType;
	}

	@Column(name = "wow_remarks")
	public String getWowRemarks() {
		return wowRemarks;
	}

	public void setWowRemarks(String wowRemarks) {
		this.wowRemarks = wowRemarks;
	}

	@Column(name = "receiving_object")
	public String getReceivingObject() {
		return receivingObject;
	}

	public void setReceivingObject(String receivingObject) {
		this.receivingObject = receivingObject;
	}

    public void setNumber1(Double number1) {
        this.number1 = number1;
    }
	@Transient
    public Double getNumber1() {
        return number1;
    }

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Transient
	public String getRemarks() {
		return remarks;
	}

    public void setWewId(Integer wewId) {
        this.wewId = wewId;
    }
	@Column(name = "wew_id")
    public Integer getWewId() {
        return wewId;
    }
}