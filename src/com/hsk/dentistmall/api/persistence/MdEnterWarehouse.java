package com.hsk.dentistmall.api.persistence;

import javax.persistence.*; 
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

/**
 * 根据md_enter_warehouse表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdEnterWarehouse</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table> 
 * <tr><th>字段名称:</th><td>入库单信息表id(wewId)</td><th>字段类型:</th><td>Integer(主键)</td></tr> 
 * <tr><th>字段名称:</th><td>集团公司id(rbaId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>牙医医院id(rbsId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>牙医门诊id(rbbId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>采购单位类型(purchaseType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>单据号(billcode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>关联单据号(relationBillcode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>录入方式(inputMode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>货主(owner)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>单据类型(billType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>收货人(consignee)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>预计到货时间(expectDatetime)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>收货完成时间(receiptDatetime)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>联系人(contacts)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>联系电话(telephone)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>联系地址(address)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>供应商编号(supplierCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>供应商名称(supplierName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>到货通知单(alertCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>asn类别(asnType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>订单日期(orderDatetime)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>状态(state)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>期待数量(expectNumber)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>收货数量(receiptNumber)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>上架数量(shelvesNumber)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>发货人(consignor)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>发货地址(consignorAddress)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数值10(paramStr10)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数值02(paramStr02)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数值01(paramStr01)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数值03(paramStr03)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数值07(paramStr04)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数值05(paramStr05)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数值06(paramStr06)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数值07(paramStr07)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数值08(paramStr08)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数值09(paramStr09)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数数值01(paramInt01)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数数值02(paramInt02)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数数值03(paramInt03)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数数值04(paramInt04)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>参数数值05(paramInt05)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * </table>
 *===============无关字段属性===============
 *  <table> 
 * 	<tr><th>字段名称:</th><td>入库单信息表id(wewId)	</td><th>属性名称:</th><td>wewId</td></tr>
 * 	<tr><th>字段名称:</th><td>集团公司id(rbaId)	</td><th>属性名称:</th><td>rbaId</td></tr>
 * 	<tr><th>字段名称:</th><td>牙医医院id(rbsId)	</td><th>属性名称:</th><td>rbsId</td></tr>
 * 	<tr><th>字段名称:</th><td>牙医门诊id(rbbId)	</td><th>属性名称:</th><td>rbbId</td></tr>
 * 	<tr><th>字段名称:</th><td>预计到货时间(expectDatetime)	</td><th>属性名称:</th><td>expectDatetime</td></tr>
 * 	<tr><th>字段名称:</th><td>预计到货时间(expectDatetime)	</td><th>属性名称:</th><td>expectDatetime</td></tr>
 * 	<tr><th>字段名称:</th><td>收货完成时间(receiptDatetime)	</td><th>属性名称:</th><td>receiptDatetime</td></tr>
 * 	<tr><th>字段名称:</th><td>收货完成时间(receiptDatetime)	</td><th>属性名称:</th><td>receiptDatetime</td></tr>
 * 	<tr><th>字段名称:</th><td>订单日期(orderDatetime)	</td><th>属性名称:</th><td>orderDatetime</td></tr>
 * 	<tr><th>字段名称:</th><td>订单日期(orderDatetime)	</td><th>属性名称:</th><td>orderDatetime</td></tr>
 * 	<tr><th>字段名称:</th><td>参数数值01(paramInt01)	</td><th>属性名称:</th><td>paramInt01</td></tr>
 * 	<tr><th>字段名称:</th><td>参数数值01(paramInt01)	</td><th>属性名称:</th><td>paramInt01</td></tr>
 * 	<tr><th>字段名称:</th><td>参数数值02(paramInt02)	</td><th>属性名称:</th><td>paramInt02</td></tr>
 * 	<tr><th>字段名称:</th><td>参数数值02(paramInt02)	</td><th>属性名称:</th><td>paramInt02</td></tr>
 * 	<tr><th>字段名称:</th><td>参数数值03(paramInt03)	</td><th>属性名称:</th><td>paramInt03</td></tr>
 * 	<tr><th>字段名称:</th><td>参数数值03(paramInt03)	</td><th>属性名称:</th><td>paramInt03</td></tr>
 * 	<tr><th>字段名称:</th><td>参数数值04(paramInt04)	</td><th>属性名称:</th><td>paramInt04</td></tr>
 * 	<tr><th>字段名称:</th><td>参数数值04(paramInt04)	</td><th>属性名称:</th><td>paramInt04</td></tr>
 * 	<tr><th>字段名称:</th><td>参数数值05(paramInt05)	</td><th>属性名称:</th><td>paramInt05</td></tr>
 * 	<tr><th>字段名称:</th><td>参数数值05(paramInt05)	</td><th>属性名称:</th><td>paramInt05</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 * @author  作者:admin  
 * @version  v1.0 创建时间: 2017-09-25 14:14:54
 */
 
 
@SuppressWarnings("serial")
@Entity
@Table(name = "md_enter_warehouse" )
public class MdEnterWarehouse {
///===============数据库表字段属性begin==========
			/**
			 *入库单信息表id(wewId):字段类型为Integer  
			 */
 			private Integer wewId; 
 	
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
 			 * 用户ID
 			 */
 			private Integer suiId;
 	
			/**
			 *采购单位类型(purchaseType):字段类型为String  
			 */
 			private String purchaseType; 
 	
			/**
			 *单据号(billcode):字段类型为String  
			 */
 			private String billcode; 
 	
			/**
			 *关联单据号(relationBillcode):字段类型为String  
			 */
 			private String relationBillCode; 
 	
			/**
			 *录入方式(inputMode):字段类型为String  
			 */
 			private String inputMode; 
 	
			/**
			 *货主(owner):字段类型为String  
			 */
 			private String owner; 
 	
			/**
			 *单据类型(billType):字段类型为String  
			 */
 			private String billType; 
 	
			/**
			 *收货人(consignee):字段类型为String  
			 */
 			private String consignee; 
 	
			/**
			 *预计到货时间(expectDatetime):字段类型为Date  
			 */
 			private Date expectDatetime; 
 	
			/**
			 *收货完成时间(receiptDatetime):字段类型为Date  
			 */
 			private Date receiptDatetime; 
 	
			/**
			 *联系人(contacts):字段类型为String  
			 */
 			private String contacts; 
 	
			/**
			 *联系电话(telephone):字段类型为String  
			 */
 			private String telephone; 
 	
			/**
			 *联系地址(address):字段类型为String  
			 */
 			private String address; 
 	
			/**
			 *供应商编号(supplierCode):字段类型为String  
			 */
 			private String supplierCode; 
 	
			/**
			 *供应商名称(supplierName):字段类型为String  
			 */
 			private String supplierName; 
 	
			/**
			 *到货通知单(alertCode):字段类型为String  
			 */
 			private String alertCode; 
 	
			/**
			 *asn类别(asnType):字段类型为String  
			 */
 			private String asnType; 
 	
			/**
			 *订单日期(orderDatetime):字段类型为Date  
			 */
 			private Date orderDatetime; 
 	
			/**
			 *状态(state):字段类型为String  
			 */
 			private String state; 
 	
			/**
			 *期待数量(expectNumber):字段类型为Double  
			 */
 			private Double expectNumber; 
 	
			/**
			 *收货数量(receiptNumber):字段类型为Double  
			 */
 			private Double receiptNumber; 
 	
			/**
			 *上架数量(shelvesNumber):字段类型为Double  
			 */
 			private Double shelvesNumber; 
 	
			/**
			 *发货人(consignor):字段类型为String  
			 */
 			private String consignor; 
 	
			/**
			 *发货地址(consignorAddress):字段类型为String  
			 */
 			private String consignorAddress; 
 	
			/**
			 *参数值10(paramStr10):字段类型为String  
			 */
 			private String paramStr10; 
 	
			/**
			 *参数值02(paramStr02):字段类型为String  
			 */
 			private String paramStr02; 
 	
			/**
			 *参数值01(paramStr01):字段类型为String  
			 */
 			private String paramStr01; 
 	
			/**
			 *参数值03(paramStr03):字段类型为String  
			 */
 			private String paramStr03; 
 	
			/**
			 *参数值07(paramStr04):字段类型为String  
			 */
 			private String paramStr04; 
 	
			/**
			 *参数值05(paramStr05):字段类型为String  
			 */
 			private String paramStr05; 
 	
			/**
			 *参数值06(paramStr06):字段类型为String  
			 */
 			private String paramStr06; 
 	
			/**
			 *参数值07(paramStr07):字段类型为String  
			 */
 			private String paramStr07; 
 	
			/**
			 *参数值08(paramStr08):字段类型为String  
			 */
 			private String paramStr08; 
 	
			/**
			 *参数值09(paramStr09):字段类型为String  
			 */
 			private String paramStr09; 
 	
			/**
			 *参数数值01(paramInt01):字段类型为Date  
			 */
 			private Date paramInt01; 
 	
			/**
			 *参数数值02(paramInt02):字段类型为Date  
			 */
 			private Date paramInt02; 
 	
			/**
			 *参数数值03(paramInt03):字段类型为Date  
			 */
 			private Date paramInt03; 
 	
			/**
			 *参数数值04(paramInt04):字段类型为Date  
			 */
 			private Date paramInt04; 
 	
			/**
			 *参数数值05(paramInt05):字段类型为Date  
			 */
 			private Date paramInt05; 
 	
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
			 * 新增字段 purchase_money  warehousing_remarks  retail_money  invoice_code
			 *
			 */

			private Double purchaseMoney;

			private String warehousingRemarks;

			private Double retailMoney;

			private String invoiceCode;

			private Integer isDelete;

			@Column(name = "is_delete")
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 *设置入库单信息表id(wew_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdEnterWarehouse.WewId</b>的值
			 */	 
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "wew_id")
			public Integer getWewId(){
			    return this.wewId;
			}

		  /**
		   *设置 wew_id字段方法 
		   *@param att_wewId 输入<b>MdEnterWarehouse.wewId</b>字段的值
		   */
		  public void setWewId(Integer att_wewId){
		    this.wewId = att_wewId;
		  }
  
			/**
			 *设置集团公司id(rba_id)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.RbaId</b>的值
			 */	 
			@Column(name = "rba_id" ) 
			public Integer getRbaId(){
			    return this.rbaId;
			}

		  /**
		   *设置 rba_id字段方法 
		   *@param att_rbaId 输入<b>MdEnterWarehouse.rbaId</b>字段的值
		   */
		  public void setRbaId(Integer att_rbaId){
		    this.rbaId = att_rbaId;
		  }
  
			/**
			 *设置牙医医院id(rbs_id)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.RbsId</b>的值
			 */	 
			@Column(name = "rbs_id" ) 
			public Integer getRbsId(){
			    return this.rbsId;
			}

		  /**
		   *设置 rbs_id字段方法 
		   *@param att_rbsId 输入<b>MdEnterWarehouse.rbsId</b>字段的值
		   */
		  public void setRbsId(Integer att_rbsId){
		    this.rbsId = att_rbsId;
		  }
  
			/**
			 *设置牙医门诊id(rbb_id)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.RbbId</b>的值
			 */	 
			@Column(name = "rbb_id" ) 
			public Integer getRbbId(){
			    return this.rbbId;
			}

		  /**
		   *设置 rbb_id字段方法 
		   *@param att_rbbId 输入<b>MdEnterWarehouse.rbbId</b>字段的值
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
			 *@return  返回 <b>MdEnterWarehouse.PurchaseType</b>的值
			 */	 
			@Column(name = "purchase_type" ) 
			public String getPurchaseType(){
			    return this.purchaseType;
			}

		  /**
		   *设置 purchase_type字段方法 
		   *@param att_purchaseType 输入<b>MdEnterWarehouse.purchaseType</b>字段的值
		   */
		  public void setPurchaseType(String att_purchaseType){
		    this.purchaseType = att_purchaseType;
		  }
  
			/**
			 *设置单据号(Billcode)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.Billcode</b>的值
			 */	 
			@Column(name = "Billcode" ) 
			public String getBillcode(){
			    return this.billcode;
			}

		  /**
		   *设置 Billcode字段方法 
		   *@param att_billcode 输入<b>MdEnterWarehouse.billcode</b>字段的值
		   */
		  public void setBillcode(String att_billcode){
		    this.billcode = att_billcode;
		  }
  
			/**
			 *设置关联单据号(Relation_billCode)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.RelationBillcode</b>的值
			 */	 
			@Column(name = "Relation_billCode" ) 
			public String getRelationBillCode(){
			    return this.relationBillCode;
			}

		  /**
		   *设置 Relation_billCode字段方法 
		   *@param att_relationBillcode 输入<b>MdEnterWarehouse.relationBillcode</b>字段的值
		   */
		  public void setRelationBillCode(String att_relationBillCode){
		    this.relationBillCode = att_relationBillCode;
		  }
  
			/**
			 *设置录入方式(Input_mode)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.InputMode</b>的值
			 */	 
			@Column(name = "Input_mode" ) 
			public String getInputMode(){
			    return this.inputMode;
			}

		  /**
		   *设置 Input_mode字段方法 
		   *@param att_inputMode 输入<b>MdEnterWarehouse.inputMode</b>字段的值
		   */
		  public void setInputMode(String att_inputMode){
		    this.inputMode = att_inputMode;
		  }
  
			/**
			 *设置货主(owner)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.Owner</b>的值
			 */	 
			@Column(name = "owner" ) 
			public String getOwner(){
			    return this.owner;
			}

		  /**
		   *设置 owner字段方法 
		   *@param att_owner 输入<b>MdEnterWarehouse.owner</b>字段的值
		   */
		  public void setOwner(String att_owner){
		    this.owner = att_owner;
		  }
  
			/**
			 *设置单据类型(bill_type)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.BillType</b>的值
			 */	 
			@Column(name = "bill_type" ) 
			public String getBillType(){
			    return this.billType;
			}

		  /**
		   *设置 bill_type字段方法 
		   *@param att_billType 输入<b>MdEnterWarehouse.billType</b>字段的值
		   */
		  public void setBillType(String att_billType){
		    this.billType = att_billType;
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
			 *设置预计到货时间(expect_datetime)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ExpectDatetime</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "expect_datetime" ) 
			public Date getExpectDatetime(){
			    return this.expectDatetime;
			}

		  /**
		   *设置 expect_datetime字段方法 
		   *@param att_expectDatetime 输入<b>MdEnterWarehouse.expectDatetime</b>字段的值
		   */
		  public void setExpectDatetime(Date att_expectDatetime){
		    this.expectDatetime = att_expectDatetime;
		  }
  
			/**
			 *设置收货完成时间(receipt_datetime)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ReceiptDatetime</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "receipt_datetime" ) 
			public Date getReceiptDatetime(){
			    return this.receiptDatetime;
			}

		  /**
		   *设置 receipt_datetime字段方法 
		   *@param att_receiptDatetime 输入<b>MdEnterWarehouse.receiptDatetime</b>字段的值
		   */
		  public void setReceiptDatetime(Date att_receiptDatetime){
		    this.receiptDatetime = att_receiptDatetime;
		  }
  
			/**
			 *设置联系人(Contacts)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.Contacts</b>的值
			 */	 
			@Column(name = "Contacts" ) 
			public String getContacts(){
			    return this.contacts;
			}

		  /**
		   *设置 Contacts字段方法 
		   *@param att_contacts 输入<b>MdEnterWarehouse.contacts</b>字段的值
		   */
		  public void setContacts(String att_contacts){
		    this.contacts = att_contacts;
		  }
  
			/**
			 *设置联系电话(Telephone)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.Telephone</b>的值
			 */	 
			@Column(name = "Telephone" ) 
			public String getTelephone(){
			    return this.telephone;
			}

		  /**
		   *设置 Telephone字段方法 
		   *@param att_telephone 输入<b>MdEnterWarehouse.telephone</b>字段的值
		   */
		  public void setTelephone(String att_telephone){
		    this.telephone = att_telephone;
		  }
  
			/**
			 *设置联系地址(address)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.Address</b>的值
			 */	 
			@Column(name = "address" ) 
			public String getAddress(){
			    return this.address;
			}

		  /**
		   *设置 address字段方法 
		   *@param att_address 输入<b>MdEnterWarehouse.address</b>字段的值
		   */
		  public void setAddress(String att_address){
		    this.address = att_address;
		  }
  
			/**
			 *设置供应商编号(supplier_code)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.SupplierCode</b>的值
			 */	 
			@Column(name = "supplier_code" ) 
			public String getSupplierCode(){
			    return this.supplierCode;
			}

		  /**
		   *设置 supplier_code字段方法 
		   *@param att_supplierCode 输入<b>MdEnterWarehouse.supplierCode</b>字段的值
		   */
		  public void setSupplierCode(String att_supplierCode){
		    this.supplierCode = att_supplierCode;
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
			 *设置到货通知单(alert_code)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.AlertCode</b>的值
			 */	 
			@Column(name = "alert_code" ) 
			public String getAlertCode(){
			    return this.alertCode;
			}

		  /**
		   *设置 alert_code字段方法 
		   *@param att_alertCode 输入<b>MdEnterWarehouse.alertCode</b>字段的值
		   */
		  public void setAlertCode(String att_alertCode){
		    this.alertCode = att_alertCode;
		  }
  
			/**
			 *设置asn类别(asn_type)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.AsnType</b>的值
			 */	 
			@Column(name = "asn_type" ) 
			public String getAsnType(){
			    return this.asnType;
			}

		  /**
		   *设置 asn_type字段方法 
		   *@param att_asnType 输入<b>MdEnterWarehouse.asnType</b>字段的值
		   */
		  public void setAsnType(String att_asnType){
		    this.asnType = att_asnType;
		  }
  
			/**
			 *设置订单日期(Order_datetime)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.OrderDatetime</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "Order_datetime" ) 
			public Date getOrderDatetime(){
			    return this.orderDatetime;
			}

		  /**
		   *设置 Order_datetime字段方法 
		   *@param att_orderDatetime 输入<b>MdEnterWarehouse.orderDatetime</b>字段的值
		   */
		  public void setOrderDatetime(Date att_orderDatetime){
		    this.orderDatetime = att_orderDatetime;
		  }
  
			/**
			 *设置状态(state)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.State</b>的值
			 */	 
			@Column(name = "state" ) 
			public String getState(){
			    return this.state;
			}

		  /**
		   *设置 state字段方法 
		   *@param att_state 输入<b>MdEnterWarehouse.state</b>字段的值
		   */
		  public void setState(String att_state){
		    this.state = att_state;
		  }
  
			/**
			 *设置期待数量(expect_number)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ExpectNumber</b>的值
			 */	 
			@Column(name = "expect_number" ) 
			public Double getExpectNumber(){
			    return this.expectNumber;
			}

		  /**
		   *设置 expect_number字段方法 
		   *@param att_expectNumber 输入<b>MdEnterWarehouse.expectNumber</b>字段的值
		   */
		  public void setExpectNumber(Double att_expectNumber){
		    this.expectNumber = att_expectNumber;
		  }
  
			/**
			 *设置收货数量(receipt_number)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ReceiptNumber</b>的值
			 */	 
			@Column(name = "receipt_number" ) 
			public Double getReceiptNumber(){
			    return this.receiptNumber;
			}

		  /**
		   *设置 receipt_number字段方法 
		   *@param att_receiptNumber 输入<b>MdEnterWarehouse.receiptNumber</b>字段的值
		   */
		  public void setReceiptNumber(Double att_receiptNumber){
		    this.receiptNumber = att_receiptNumber;
		  }
  
			/**
			 *设置上架数量(shelves_number)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ShelvesNumber</b>的值
			 */	 
			@Column(name = "shelves_number" ) 
			public Double getShelvesNumber(){
			    return this.shelvesNumber;
			}

		  /**
		   *设置 shelves_number字段方法 
		   *@param att_shelvesNumber 输入<b>MdEnterWarehouse.shelvesNumber</b>字段的值
		   */
		  public void setShelvesNumber(Double att_shelvesNumber){
		    this.shelvesNumber = att_shelvesNumber;
		  }
  
			/**
			 *设置发货人(Consignor)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.Consignor</b>的值
			 */	 
			@Column(name = "Consignor" ) 
			public String getConsignor(){
			    return this.consignor;
			}

		  /**
		   *设置 Consignor字段方法 
		   *@param att_consignor 输入<b>MdEnterWarehouse.consignor</b>字段的值
		   */
		  public void setConsignor(String att_consignor){
		    this.consignor = att_consignor;
		  }
  
			/**
			 *设置发货地址(consignor_address)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ConsignorAddress</b>的值
			 */	 
			@Column(name = "consignor_address" ) 
			public String getConsignorAddress(){
			    return this.consignorAddress;
			}

		  /**
		   *设置 consignor_address字段方法 
		   *@param att_consignorAddress 输入<b>MdEnterWarehouse.consignorAddress</b>字段的值
		   */
		  public void setConsignorAddress(String att_consignorAddress){
		    this.consignorAddress = att_consignorAddress;
		  }
  
			/**
			 *设置参数值10(param_str10)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamStr10</b>的值
			 */	 
			@Column(name = "param_str10" ) 
			public String getParamStr10(){
			    return this.paramStr10;
			}

		  /**
		   *设置 param_str10字段方法 
		   *@param att_paramStr10 输入<b>MdEnterWarehouse.paramStr10</b>字段的值
		   */
		  public void setParamStr10(String att_paramStr10){
		    this.paramStr10 = att_paramStr10;
		  }
  
			/**
			 *设置参数值02(param_str02)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamStr02</b>的值
			 */	 
			@Column(name = "param_str02" ) 
			public String getParamStr02(){
			    return this.paramStr02;
			}

		  /**
		   *设置 param_str02字段方法 
		   *@param att_paramStr02 输入<b>MdEnterWarehouse.paramStr02</b>字段的值
		   */
		  public void setParamStr02(String att_paramStr02){
		    this.paramStr02 = att_paramStr02;
		  }
  
			/**
			 *设置参数值01(param_str01)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamStr01</b>的值
			 */	 
			@Column(name = "param_str01" ) 
			public String getParamStr01(){
			    return this.paramStr01;
			}

		  /**
		   *设置 param_str01字段方法 
		   *@param att_paramStr01 输入<b>MdEnterWarehouse.paramStr01</b>字段的值
		   */
		  public void setParamStr01(String att_paramStr01){
		    this.paramStr01 = att_paramStr01;
		  }
  
			/**
			 *设置参数值03(param_str03)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamStr03</b>的值
			 */	 
			@Column(name = "param_str03" ) 
			public String getParamStr03(){
			    return this.paramStr03;
			}

		  /**
		   *设置 param_str03字段方法 
		   *@param att_paramStr03 输入<b>MdEnterWarehouse.paramStr03</b>字段的值
		   */
		  public void setParamStr03(String att_paramStr03){
		    this.paramStr03 = att_paramStr03;
		  }
  
			/**
			 *设置参数值07(param_str04)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamStr04</b>的值
			 */	 
			@Column(name = "param_str04" ) 
			public String getParamStr04(){
			    return this.paramStr04;
			}

		  /**
		   *设置 param_str04字段方法 
		   *@param att_paramStr04 输入<b>MdEnterWarehouse.paramStr04</b>字段的值
		   */
		  public void setParamStr04(String att_paramStr04){
		    this.paramStr04 = att_paramStr04;
		  }
  
			/**
			 *设置参数值05(param_str05)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamStr05</b>的值
			 */	 
			@Column(name = "param_str05" ) 
			public String getParamStr05(){
			    return this.paramStr05;
			}

		  /**
		   *设置 param_str05字段方法 
		   *@param att_paramStr05 输入<b>MdEnterWarehouse.paramStr05</b>字段的值
		   */
		  public void setParamStr05(String att_paramStr05){
		    this.paramStr05 = att_paramStr05;
		  }
  
			/**
			 *设置参数值06(param_str06)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamStr06</b>的值
			 */	 
			@Column(name = "param_str06" ) 
			public String getParamStr06(){
			    return this.paramStr06;
			}

		  /**
		   *设置 param_str06字段方法 
		   *@param att_paramStr06 输入<b>MdEnterWarehouse.paramStr06</b>字段的值
		   */
		  public void setParamStr06(String att_paramStr06){
		    this.paramStr06 = att_paramStr06;
		  }
  
			/**
			 *设置参数值07(param_str07)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamStr07</b>的值
			 */	 
			@Column(name = "param_str07" ) 
			public String getParamStr07(){
			    return this.paramStr07;
			}

		  /**
		   *设置 param_str07字段方法 
		   *@param att_paramStr07 输入<b>MdEnterWarehouse.paramStr07</b>字段的值
		   */
		  public void setParamStr07(String att_paramStr07){
		    this.paramStr07 = att_paramStr07;
		  }
  
			/**
			 *设置参数值08(param_str08)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamStr08</b>的值
			 */	 
			@Column(name = "param_str08" ) 
			public String getParamStr08(){
			    return this.paramStr08;
			}

		  /**
		   *设置 param_str08字段方法 
		   *@param att_paramStr08 输入<b>MdEnterWarehouse.paramStr08</b>字段的值
		   */
		  public void setParamStr08(String att_paramStr08){
		    this.paramStr08 = att_paramStr08;
		  }
  
			/**
			 *设置参数值09(param_str09)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamStr09</b>的值
			 */	 
			@Column(name = "param_str09" ) 
			public String getParamStr09(){
			    return this.paramStr09;
			}

		  /**
		   *设置 param_str09字段方法 
		   *@param att_paramStr09 输入<b>MdEnterWarehouse.paramStr09</b>字段的值
		   */
		  public void setParamStr09(String att_paramStr09){
		    this.paramStr09 = att_paramStr09;
		  }
  
			/**
			 *设置参数数值01(param_int01)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamInt01</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "param_int01" ) 
			public Date getParamInt01(){
			    return this.paramInt01;
			}

		  /**
		   *设置 param_int01字段方法 
		   *@param att_paramInt01 输入<b>MdEnterWarehouse.paramInt01</b>字段的值
		   */
		  public void setParamInt01(Date att_paramInt01){
		    this.paramInt01 = att_paramInt01;
		  }
  
			/**
			 *设置参数数值02(param_int02)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamInt02</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "param_int02" ) 
			public Date getParamInt02(){
			    return this.paramInt02;
			}

		  /**
		   *设置 param_int02字段方法 
		   *@param att_paramInt02 输入<b>MdEnterWarehouse.paramInt02</b>字段的值
		   */
		  public void setParamInt02(Date att_paramInt02){
		    this.paramInt02 = att_paramInt02;
		  }
  
			/**
			 *设置参数数值03(param_int03)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamInt03</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "param_int03" ) 
			public Date getParamInt03(){
			    return this.paramInt03;
			}

		  /**
		   *设置 param_int03字段方法 
		   *@param att_paramInt03 输入<b>MdEnterWarehouse.paramInt03</b>字段的值
		   */
		  public void setParamInt03(Date att_paramInt03){
		    this.paramInt03 = att_paramInt03;
		  }
  
			/**
			 *设置参数数值04(param_int04)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamInt04</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "param_int04" ) 
			public Date getParamInt04(){
			    return this.paramInt04;
			}

		  /**
		   *设置 param_int04字段方法 
		   *@param att_paramInt04 输入<b>MdEnterWarehouse.paramInt04</b>字段的值
		   */
		  public void setParamInt04(Date att_paramInt04){
		    this.paramInt04 = att_paramInt04;
		  }
  
			/**
			 *设置参数数值05(param_int05)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.ParamInt05</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "param_int05" ) 
			public Date getParamInt05(){
			    return this.paramInt05;
			}

		  /**
		   *设置 param_int05字段方法 
		   *@param att_paramInt05 输入<b>MdEnterWarehouse.paramInt05</b>字段的值
		   */
		  public void setParamInt05(Date att_paramInt05){
		    this.paramInt05 = att_paramInt05;
		  }
  
			/**
			 *设置创建人(create_ren)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.CreateRen</b>的值
			 */	 
			@Column(name = "create_ren" ) 
			public String getCreateRen(){
			    return this.createRen;
			}

		  /**
		   *设置 create_ren字段方法 
		   *@param att_createRen 输入<b>MdEnterWarehouse.createRen</b>字段的值
		   */
		  public void setCreateRen(String att_createRen){
		    this.createRen = att_createRen;
		  }
  
			/**
			 *设置创建时间(create_date)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.CreateDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "create_date" ) 
			public Date getCreateDate(){
			    return this.createDate;
			}

		  /**
		   *设置 create_date字段方法 
		   *@param att_createDate 输入<b>MdEnterWarehouse.createDate</b>字段的值
		   */
		  public void setCreateDate(Date att_createDate){
		    this.createDate = att_createDate;
		  }
  
			/**
			 *设置修改人(edit_ren)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.EditRen</b>的值
			 */	 
			@Column(name = "edit_ren" ) 
			public String getEditRen(){
			    return this.editRen;
			}

		  /**
		   *设置 edit_ren字段方法 
		   *@param att_editRen 输入<b>MdEnterWarehouse.editRen</b>字段的值
		   */
		  public void setEditRen(String att_editRen){
		    this.editRen = att_editRen;
		  }
  
			/**
			 *设置修改时间(edit_date)字段方法 
			 *@return  返回 <b>MdEnterWarehouse.EditDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "edit_date" ) 
			public Date getEditDate(){
			    return this.editDate;
			}

		  /**
		   *设置 edit_date字段方法 
		   *@param att_editDate 输入<b>MdEnterWarehouse.editDate</b>字段的值
		   */
		  public void setEditDate(Date att_editDate){
		    this.editDate = att_editDate;
		  }

//	purchase_money  warehousing_remarks  retail_money  invoice_code
			@Column(name = "purchase_money" )
			public Double getPurchaseMoney() {
					return purchaseMoney;
				}

				public void setPurchaseMoney(Double purchaseMoney) {
					this.purchaseMoney = purchaseMoney;
				}
				@Column(name = "warehousing_remarks" )
				public String getWarehousingRemarks() {
					return warehousingRemarks;
				}

				public void setWarehousingRemarks(String warehousingRemarks) {
					this.warehousingRemarks = warehousingRemarks;
				}
				@Column(name = "retail_money" )
				public Double getRetailMoney() {
					return retailMoney;
				}

				public void setRetailMoney(Double retailMoney) {
					this.retailMoney = retailMoney;
				}
				@Column(name = "invoice_code" )
				public String getInvoiceCode() {
					return invoiceCode;
				}

				public void setInvoiceCode(String invoiceCode) {
					this.invoiceCode = invoiceCode;
				}

	///===============数据库表字段属性end==========

///===============数据库表无关子段字段属性begin==========
			/**
			 *入库单信息表id(wew_id):字段类型为String
			 */
		  private String wewId_str;  
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
			 *预计到货时间(expect_datetime):字段类型为Date
			 */
		  private Date expectDatetime_start;  
			/**
			 *预计到货时间(expect_datetime):字段类型为Date
			 */
		  private Date expectDatetime_end;  
			/**
			 *收货完成时间(receipt_datetime):字段类型为Date
			 */
		  private Date receiptDatetime_start;  
			/**
			 *收货完成时间(receipt_datetime):字段类型为Date
			 */
		  private Date receiptDatetime_end;  
			/**
			 *订单日期(Order_datetime):字段类型为Date
			 */
		  private Date orderDatetime_start;  
			/**
			 *订单日期(Order_datetime):字段类型为Date
			 */
		  private Date orderDatetime_end;  
			/**
			 *参数数值01(param_int01):字段类型为Date
			 */
		  private Date paramInt01_start;  
			/**
			 *参数数值01(param_int01):字段类型为Date
			 */
		  private Date paramInt01_end;  
			/**
			 *参数数值02(param_int02):字段类型为Date
			 */
		  private Date paramInt02_start;  
			/**
			 *参数数值02(param_int02):字段类型为Date
			 */
		  private Date paramInt02_end;  
			/**
			 *参数数值03(param_int03):字段类型为Date
			 */
		  private Date paramInt03_start;  
			/**
			 *参数数值03(param_int03):字段类型为Date
			 */
		  private Date paramInt03_end;  
			/**
			 *参数数值04(param_int04):字段类型为Date
			 */
		  private Date paramInt04_start;  
			/**
			 *参数数值04(param_int04):字段类型为Date
			 */
		  private Date paramInt04_end;  
			/**
			 *参数数值05(param_int05):字段类型为Date
			 */
		  private Date paramInt05_start;  
			/**
			 *参数数值05(param_int05):字段类型为Date
			 */
		  private Date paramInt05_end;  
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
			 *md_enter_warehouse表里不用like作为条件的字符串
			 */
		  private String Tab_like;  
			/**
			 *():字段类型为String
			 *md_enter_warehouse表里order by 字符串
			 */
		  private String Tab_order;  
			/**
			 *设置wewId字段方法  
			 *@return  返回 <b>MdEnterWarehouse.wewId</b>的值
			 */ 
			@Transient
			public String getWewId_str(){
				return this.wewId_str;
			}
			/**
			  *设置 wew_id字段方法 
			  *@param att_wewId 输入<b>MdEnterWarehouse.wewId</b>字段的值
			  */
			public void setWewId_str(String att_wewId_str){
				this.wewId_str = att_wewId_str;
			}
			/**
			 *设置rbaId字段方法  
			 *@return  返回 <b>MdEnterWarehouse.rbaId</b>的值
			 */ 
			@Transient
			public String getRbaId_str(){
				return this.rbaId_str;
			}
			/**
			  *设置 rba_id字段方法 
			  *@param att_rbaId 输入<b>MdEnterWarehouse.rbaId</b>字段的值
			  */
			public void setRbaId_str(String att_rbaId_str){
				this.rbaId_str = att_rbaId_str;
			}
			/**
			 *设置rbsId字段方法  
			 *@return  返回 <b>MdEnterWarehouse.rbsId</b>的值
			 */ 
			@Transient
			public String getRbsId_str(){
				return this.rbsId_str;
			}
			/**
			  *设置 rbs_id字段方法 
			  *@param att_rbsId 输入<b>MdEnterWarehouse.rbsId</b>字段的值
			  */
			public void setRbsId_str(String att_rbsId_str){
				this.rbsId_str = att_rbsId_str;
			}
			/**
			 *设置rbbId字段方法  
			 *@return  返回 <b>MdEnterWarehouse.rbbId</b>的值
			 */ 
			@Transient
			public String getRbbId_str(){
				return this.rbbId_str;
			}
			/**
			  *设置 rbb_id字段方法 
			  *@param att_rbbId 输入<b>MdEnterWarehouse.rbbId</b>字段的值
			  */
			public void setRbbId_str(String att_rbbId_str){
				this.rbbId_str = att_rbbId_str;
			}
			/**
			 *设置expectDatetime字段方法  
			 *@return  返回 <b>MdEnterWarehouse.expectDatetime</b>的值
			 */ 
			@Transient
			public Date getExpectDatetime_start(){
				return this.expectDatetime_start;
			}
			/**
			  *设置 expect_datetime字段方法 
			  *@param att_expectDatetime 输入<b>MdEnterWarehouse.expectDatetime</b>字段的值
			  */
			public void setExpectDatetime_start(Date att_expectDatetime_start){
				this.expectDatetime_start = att_expectDatetime_start;
			}
			/**
			 *设置expectDatetime字段方法  
			 *@return  返回 <b>MdEnterWarehouse.expectDatetime</b>的值
			 */ 
			@Transient
			public Date getExpectDatetime_end(){
				return this.expectDatetime_end;
			}
			/**
			  *设置 expect_datetime字段方法 
			  *@param att_expectDatetime 输入<b>MdEnterWarehouse.expectDatetime</b>字段的值
			  */
			public void setExpectDatetime_end(Date att_expectDatetime_end){
				this.expectDatetime_end = att_expectDatetime_end;
			}
			/**
			 *设置receiptDatetime字段方法  
			 *@return  返回 <b>MdEnterWarehouse.receiptDatetime</b>的值
			 */ 
			@Transient
			public Date getReceiptDatetime_start(){
				return this.receiptDatetime_start;
			}
			/**
			  *设置 receipt_datetime字段方法 
			  *@param att_receiptDatetime 输入<b>MdEnterWarehouse.receiptDatetime</b>字段的值
			  */
			public void setReceiptDatetime_start(Date att_receiptDatetime_start){
				this.receiptDatetime_start = att_receiptDatetime_start;
			}
			/**
			 *设置receiptDatetime字段方法  
			 *@return  返回 <b>MdEnterWarehouse.receiptDatetime</b>的值
			 */ 
			@Transient
			public Date getReceiptDatetime_end(){
				return this.receiptDatetime_end;
			}
			/**
			  *设置 receipt_datetime字段方法 
			  *@param att_receiptDatetime 输入<b>MdEnterWarehouse.receiptDatetime</b>字段的值
			  */
			public void setReceiptDatetime_end(Date att_receiptDatetime_end){
				this.receiptDatetime_end = att_receiptDatetime_end;
			}
			/**
			 *设置orderDatetime字段方法  
			 *@return  返回 <b>MdEnterWarehouse.orderDatetime</b>的值
			 */ 
			@Transient
			public Date getOrderDatetime_start(){
				return this.orderDatetime_start;
			}
			/**
			  *设置 Order_datetime字段方法 
			  *@param att_orderDatetime 输入<b>MdEnterWarehouse.orderDatetime</b>字段的值
			  */
			public void setOrderDatetime_start(Date att_orderDatetime_start){
				this.orderDatetime_start = att_orderDatetime_start;
			}
			/**
			 *设置orderDatetime字段方法  
			 *@return  返回 <b>MdEnterWarehouse.orderDatetime</b>的值
			 */ 
			@Transient
			public Date getOrderDatetime_end(){
				return this.orderDatetime_end;
			}
			/**
			  *设置 Order_datetime字段方法 
			  *@param att_orderDatetime 输入<b>MdEnterWarehouse.orderDatetime</b>字段的值
			  */
			public void setOrderDatetime_end(Date att_orderDatetime_end){
				this.orderDatetime_end = att_orderDatetime_end;
			}
			/**
			 *设置paramInt01字段方法  
			 *@return  返回 <b>MdEnterWarehouse.paramInt01</b>的值
			 */ 
			@Transient
			public Date getParamInt01_start(){
				return this.paramInt01_start;
			}
			/**
			  *设置 param_int01字段方法 
			  *@param att_paramInt01 输入<b>MdEnterWarehouse.paramInt01</b>字段的值
			  */
			public void setParamInt01_start(Date att_paramInt01_start){
				this.paramInt01_start = att_paramInt01_start;
			}
			/**
			 *设置paramInt01字段方法  
			 *@return  返回 <b>MdEnterWarehouse.paramInt01</b>的值
			 */ 
			@Transient
			public Date getParamInt01_end(){
				return this.paramInt01_end;
			}
			/**
			  *设置 param_int01字段方法 
			  *@param att_paramInt01 输入<b>MdEnterWarehouse.paramInt01</b>字段的值
			  */
			public void setParamInt01_end(Date att_paramInt01_end){
				this.paramInt01_end = att_paramInt01_end;
			}
			/**
			 *设置paramInt02字段方法  
			 *@return  返回 <b>MdEnterWarehouse.paramInt02</b>的值
			 */ 
			@Transient
			public Date getParamInt02_start(){
				return this.paramInt02_start;
			}
			/**
			  *设置 param_int02字段方法 
			  *@param att_paramInt02 输入<b>MdEnterWarehouse.paramInt02</b>字段的值
			  */
			public void setParamInt02_start(Date att_paramInt02_start){
				this.paramInt02_start = att_paramInt02_start;
			}
			/**
			 *设置paramInt02字段方法  
			 *@return  返回 <b>MdEnterWarehouse.paramInt02</b>的值
			 */ 
			@Transient
			public Date getParamInt02_end(){
				return this.paramInt02_end;
			}
			/**
			  *设置 param_int02字段方法 
			  *@param att_paramInt02 输入<b>MdEnterWarehouse.paramInt02</b>字段的值
			  */
			public void setParamInt02_end(Date att_paramInt02_end){
				this.paramInt02_end = att_paramInt02_end;
			}
			/**
			 *设置paramInt03字段方法  
			 *@return  返回 <b>MdEnterWarehouse.paramInt03</b>的值
			 */ 
			@Transient
			public Date getParamInt03_start(){
				return this.paramInt03_start;
			}
			/**
			  *设置 param_int03字段方法 
			  *@param att_paramInt03 输入<b>MdEnterWarehouse.paramInt03</b>字段的值
			  */
			public void setParamInt03_start(Date att_paramInt03_start){
				this.paramInt03_start = att_paramInt03_start;
			}
			/**
			 *设置paramInt03字段方法  
			 *@return  返回 <b>MdEnterWarehouse.paramInt03</b>的值
			 */ 
			@Transient
			public Date getParamInt03_end(){
				return this.paramInt03_end;
			}
			/**
			  *设置 param_int03字段方法 
			  *@param att_paramInt03 输入<b>MdEnterWarehouse.paramInt03</b>字段的值
			  */
			public void setParamInt03_end(Date att_paramInt03_end){
				this.paramInt03_end = att_paramInt03_end;
			}
			/**
			 *设置paramInt04字段方法  
			 *@return  返回 <b>MdEnterWarehouse.paramInt04</b>的值
			 */ 
			@Transient
			public Date getParamInt04_start(){
				return this.paramInt04_start;
			}
			/**
			  *设置 param_int04字段方法 
			  *@param att_paramInt04 输入<b>MdEnterWarehouse.paramInt04</b>字段的值
			  */
			public void setParamInt04_start(Date att_paramInt04_start){
				this.paramInt04_start = att_paramInt04_start;
			}
			/**
			 *设置paramInt04字段方法  
			 *@return  返回 <b>MdEnterWarehouse.paramInt04</b>的值
			 */ 
			@Transient
			public Date getParamInt04_end(){
				return this.paramInt04_end;
			}
			/**
			  *设置 param_int04字段方法 
			  *@param att_paramInt04 输入<b>MdEnterWarehouse.paramInt04</b>字段的值
			  */
			public void setParamInt04_end(Date att_paramInt04_end){
				this.paramInt04_end = att_paramInt04_end;
			}
			/**
			 *设置paramInt05字段方法  
			 *@return  返回 <b>MdEnterWarehouse.paramInt05</b>的值
			 */ 
			@Transient
			public Date getParamInt05_start(){
				return this.paramInt05_start;
			}
			/**
			  *设置 param_int05字段方法 
			  *@param att_paramInt05 输入<b>MdEnterWarehouse.paramInt05</b>字段的值
			  */
			public void setParamInt05_start(Date att_paramInt05_start){
				this.paramInt05_start = att_paramInt05_start;
			}
			/**
			 *设置paramInt05字段方法  
			 *@return  返回 <b>MdEnterWarehouse.paramInt05</b>的值
			 */ 
			@Transient
			public Date getParamInt05_end(){
				return this.paramInt05_end;
			}
			/**
			  *设置 param_int05字段方法 
			  *@param att_paramInt05 输入<b>MdEnterWarehouse.paramInt05</b>字段的值
			  */
			public void setParamInt05_end(Date att_paramInt05_end){
				this.paramInt05_end = att_paramInt05_end;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdEnterWarehouse.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_start(){
				return this.createDate_start;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdEnterWarehouse.createDate</b>字段的值
			  */
			public void setCreateDate_start(Date att_createDate_start){
				this.createDate_start = att_createDate_start;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdEnterWarehouse.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_end(){
				return this.createDate_end;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdEnterWarehouse.createDate</b>字段的值
			  */
			public void setCreateDate_end(Date att_createDate_end){
				this.createDate_end = att_createDate_end;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdEnterWarehouse.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_start(){
				return this.editDate_start;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdEnterWarehouse.editDate</b>字段的值
			  */
			public void setEditDate_start(Date att_editDate_start){
				this.editDate_start = att_editDate_start;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdEnterWarehouse.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_end(){
				return this.editDate_end;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdEnterWarehouse.editDate</b>字段的值
			  */
			public void setEditDate_end(Date att_editDate_end){
				this.editDate_end = att_editDate_end;
			}
			/**
			 *设置tab_like字段方法  
			 *@return  返回 <b>MdEnterWarehouse.tab_like</b>的值
			 */ 
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_like 输入<b>MdEnterWarehouse.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法  
			 *@return  返回 <b>MdEnterWarehouse.tab_order</b>的值
			 */ 
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_order 输入<b>MdEnterWarehouse.tab_order</b>字段的值
			  */
			public void setTab_order(String att_Tab_order){
				this.Tab_order = att_Tab_order;
			}
///===============数据库表无关子段字段属性end==========
} 