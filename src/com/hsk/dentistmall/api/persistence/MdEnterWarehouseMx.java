package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

/**
 * 根据md_enter_warehouse_mx表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdEnterWarehouseMx</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table>
 * <tr><th>字段名称:</th><td>入库单明细表id(wewMxId)</td><th>字段类型:</th><td>Integer(主键)</td></tr>
 * <tr><th>字段名称:</th><td>入库单信息表id(wewId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>物料信息表id(wmsMiId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>批次属性(itemKeyId)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>行号(lineNumber)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>下单时间(placeOrderTime)</td><th>字段类型:</th><td>Date(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>下单商品数量(matNumber)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>采购商名称(purchaseUnit)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>商品编码(matCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>商品名称(matName)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>基本单位(basicUnit)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>规格(norm)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>物料类别(matType)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>物料类别(matType1)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>拣选分类(matType2)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>码托分类(matType3)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>收货完成时间(receiptDatetime)</td><th>字段类型:</th><td>Date(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>描述(describes)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr>
 * </table>
 *===============无关字段属性===============
 *  <table>
 * 	<tr><th>字段名称:</th><td>入库单明细表id(wewMxId)	</td><th>属性名称:</th><td>wewMxId</td></tr>
 * 	<tr><th>字段名称:</th><td>入库单信息表id(wewId)	</td><th>属性名称:</th><td>wewId</td></tr>
 * 	<tr><th>字段名称:</th><td>物料信息表id(wmsMiId)	</td><th>属性名称:</th><td>wmsMiId</td></tr>
 * 	<tr><th>字段名称:</th><td>行号(lineNumber)	</td><th>属性名称:</th><td>lineNumber</td></tr>
 * 	<tr><th>字段名称:</th><td>下单时间(placeOrderTime)	</td><th>属性名称:</th><td>placeOrderTime</td></tr>
 * 	<tr><th>字段名称:</th><td>下单时间(placeOrderTime)	</td><th>属性名称:</th><td>placeOrderTime</td></tr>
 * 	<tr><th>字段名称:</th><td>收货完成时间(receiptDatetime)	</td><th>属性名称:</th><td>receiptDatetime</td></tr>
 * 	<tr><th>字段名称:</th><td>收货完成时间(receiptDatetime)	</td><th>属性名称:</th><td>receiptDatetime</td></tr>
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
@Table(name = "md_enter_warehouse_mx" )
public class MdEnterWarehouseMx {
///===============数据库表字段属性begin==========
			/**
			 *入库单明细表id(wewMxId):字段类型为Integer
			 */
 			private Integer wewMxId;

			/**
			 *入库单信息表id(wewId):字段类型为Integer
			 */
 			private Integer wewId;

			/**
			 *物料信息表id(wmsMiId):字段类型为Integer
			 */
 			private Integer wmsMiId;

 			private Integer mmfId;

			/**
			 *批次属性(itemKeyId):字段类型为String
			 */
 			private String itemKeyId;

			/**
			 *行号(lineNumber):字段类型为Integer
			 */
 			private Integer lineNumber;

			/**
			 *下单时间(placeOrderTime):字段类型为Date
			 */
 			private Date placeOrderTime;

			/**
			 * 生产厂家(productFactory):字段类型为String
			 */
			private String productFactory;

			/**
			 * 生产时间(productTime):字段类型为Date
			 */
			private Date productTime;

			/**
			 * 生产有效期(productValitime):字段类型为Date
			 */
			private Date productValitime;

			/**
			 * 包装方式(packageWay):字段类型为String
			 */
			private String packageWay;

			/**
			 * 批次证号(batchCertNo):字段类型为String
			 */
			private String batchCertNo;

			/**
			 *下单商品数量(matNumber):字段类型为Double
			 */
 			private Double matNumber;
 			private Double number1;
 			private Double price;

			/**
			 *采购商名称(purchaseUnit):字段类型为String
			 */
 			private String purchaseUnit;

			/**
			 *商品编码(matCode):字段类型为String
			 */
 			private String matCode;

			/**
			 *商品名称(matName):字段类型为String
			 */
 			private String matName;
 			/**
 			 * 商品别名
 			 */
 			private String matName2;

			/**
			 *基本单位(basicUnit):字段类型为String
			 */
 			private String basicUnit;

			/**
			 *规格(norm):字段类型为String
			 */
 			private String norm;
 			/**
 			 * 规格别名
 			 */
 			private String norm2;

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
			 *收货完成时间(receiptDatetime):字段类型为Date
			 */
 			private Date receiptDatetime;

			/**
			 *描述(describes):字段类型为String
			 */
 			private String describes;

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
	private String unit;
	private Double splitQuantity;
	private Integer linkWmsMiId;
	private Integer linkMmfId;
	private Double curNumber;
	private String searchMatName;
	private String searchMmfName;
	private Integer wiId;
	private Integer wowMxId;
	private Double baseNumber;
	private Double splitNumber;
	private Double quantity;
	private Integer mieId;
	private Double splitQuantity1;
	private Double baseNumber1;
	private Double splitNumber1;
	private Double ratio;
	private String mmfCode;

	private String backPrinting;

	private Double ratio1;
	private Double avgRetailPrice;
	private String productName;

	private String remark;

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ratio1")
	public Double getRatio1() {
		return ratio1;
	}

	public void setRatio1(Double ratio1) {
		this.ratio1 = ratio1;
	}

	/**
			 *设置入库单明细表id(wew_mx_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdEnterWarehouseMx.WewMxId</b>的值
			 */
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "wew_mx_id")
			public Integer getWewMxId(){
			    return this.wewMxId;
			}

		  /**
		   *设置 wew_mx_id字段方法
		   *@param att_wewMxId 输入<b>MdEnterWarehouseMx.wewMxId</b>字段的值
		   */
		  public void setWewMxId(Integer att_wewMxId){
		    this.wewMxId = att_wewMxId;
		  }

			/**
			 *设置入库单信息表id(wew_id)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.WewId</b>的值
			 */
			@Column(name = "wew_id" )
			public Integer getWewId(){
			    return this.wewId;
			}

		  /**
		   *设置 wew_id字段方法
		   *@param att_wewId 输入<b>MdEnterWarehouseMx.wewId</b>字段的值
		   */
		  public void setWewId(Integer att_wewId){
		    this.wewId = att_wewId;
		  }

			/**
			 *设置物料信息表id(wms_mi_id)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.WmsMiId</b>的值
			 */
			@Column(name = "wms_mi_id" )
			public Integer getWmsMiId(){
			    return this.wmsMiId;
			}

		  /**
		   *设置 wms_mi_id字段方法
		   *@param att_wmsMiId 输入<b>MdEnterWarehouseMx.wmsMiId</b>字段的值
		   */
		  public void setWmsMiId(Integer att_wmsMiId){
		    this.wmsMiId = att_wmsMiId;
		  }

		  @Column(name = "mmf_id" )
			public Integer getMmfId() {
				return mmfId;
			}

			public void setMmfId(Integer mmfId) {
				this.mmfId = mmfId;
			}
			/**
			 *设置批次属性(ITEM_KEY_ID)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.ItemKeyId</b>的值
			 */
			@Column(name = "ITEM_KEY_ID" )
			public String getItemKeyId(){
			    return this.itemKeyId;
			}

		  /**
		   *设置 ITEM_KEY_ID字段方法
		   *@param att_itemKeyId 输入<b>MdEnterWarehouseMx.itemKeyId</b>字段的值
		   */
		  public void setItemKeyId(String att_itemKeyId){
		    this.itemKeyId = att_itemKeyId;
		  }

			/**
			 *设置行号(Line_number)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.LineNumber</b>的值
			 */
			@Column(name = "Line_number" )
			public Integer getLineNumber(){
			    return this.lineNumber;
			}

		  /**
		   *设置 Line_number字段方法
		   *@param att_lineNumber 输入<b>MdEnterWarehouseMx.lineNumber</b>字段的值
		   */
		  public void setLineNumber(Integer att_lineNumber){
		    this.lineNumber = att_lineNumber;
		  }

			/**
			 *设置下单时间(Place_order_time)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.PlaceOrderTime</b>的值
			 */
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "Place_order_time" )
			public Date getPlaceOrderTime(){
			    return this.placeOrderTime;
			}

		  /**
		   *设置 Place_order_time字段方法
		   *@param att_placeOrderTime 输入<b>MdEnterWarehouseMx.placeOrderTime</b>字段的值
		   */
		  public void setPlaceOrderTime(Date att_placeOrderTime){
		    this.placeOrderTime = att_placeOrderTime;
		  }

			/**
			 *设置下单商品数量(mat_number)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.MatNumber</b>的值
			 */
			@Column(name = "mat_number" )
			public Double getMatNumber(){
			    return this.matNumber;
			}

		  /**
		   *设置 mat_number字段方法
		   *@param att_matNumber 输入<b>MdEnterWarehouseMx.matNumber</b>字段的值
		   */
		  public void setMatNumber(Double att_matNumber){
		    this.matNumber = att_matNumber;
		  }

		  @Column(name = "number1" )
			public Double getNumber1() {
				return number1;
			}

			public void setNumber1(Double number1) {
				this.number1 = number1;
			}

			@Column(name = "price" )
			public Double getPrice() {
				return price;
			}

			public void setPrice(Double price) {
				this.price = price;
			}

			/**
			 *设置采购商名称(Purchase_unit)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.PurchaseUnit</b>的值
			 */
			@Column(name = "Purchase_unit" )
			public String getPurchaseUnit(){
			    return this.purchaseUnit;
			}

		  /**
		   *设置 Purchase_unit字段方法
		   *@param att_purchaseUnit 输入<b>MdEnterWarehouseMx.purchaseUnit</b>字段的值
		   */
		  public void setPurchaseUnit(String att_purchaseUnit){
		    this.purchaseUnit = att_purchaseUnit;
		  }

			/**
			 *设置商品编码(mat_code)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.MatCode</b>的值
			 */
			@Column(name = "mat_code" )
			public String getMatCode(){
			    return this.matCode;
			}

		  /**
		   *设置 mat_code字段方法
		   *@param att_matCode 输入<b>MdEnterWarehouseMx.matCode</b>字段的值
		   */
		  public void setMatCode(String att_matCode){
		    this.matCode = att_matCode;
		  }

			/**
			 *设置商品名称(mat_name)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.MatName</b>的值
			 */
			@Column(name = "mat_name" )
			public String getMatName(){
			    return this.matName;
			}

		  /**
		   *设置 mat_name字段方法
		   *@param att_matName 输入<b>MdEnterWarehouseMx.matName</b>字段的值
		   */
		  public void setMatName(String att_matName){
		    this.matName = att_matName;
		  }


		  @Column(name = "mat_name2" )
			public String getMatName2() {
				return matName2;
			}

			public void setMatName2(String matName2) {
				this.matName2 = matName2;
			}
			@Column(name = "norm2" )
			public String getNorm2() {
				return norm2;
			}

			public void setNorm2(String norm2) {
				this.norm2 = norm2;
			}

			/**
			 *设置基本单位(Basic_unit)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.BasicUnit</b>的值
			 */
			@Column(name = "Basic_unit" )
			public String getBasicUnit(){
			    return this.basicUnit;
			}

		  /**
		   *设置 Basic_unit字段方法
		   *@param att_basicUnit 输入<b>MdEnterWarehouseMx.basicUnit</b>字段的值
		   */
		  public void setBasicUnit(String att_basicUnit){
		    this.basicUnit = att_basicUnit;
		  }

			/**
			 *设置规格(NORM)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.Norm</b>的值
			 */
			@Column(name = "NORM" )
			public String getNorm(){
			    return this.norm;
			}

		  /**
		   *设置 NORM字段方法
		   *@param att_norm 输入<b>MdEnterWarehouseMx.norm</b>字段的值
		   */
		  public void setNorm(String att_norm){
		    this.norm = att_norm;
		  }

			/**
			 *设置物料类别(mat_type)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.MatType</b>的值
			 */
			@Column(name = "mat_type" )
			public String getMatType(){
			    return this.matType;
			}

		  /**
		   *设置 mat_type字段方法
		   *@param att_matType 输入<b>MdEnterWarehouseMx.matType</b>字段的值
		   */
		  public void setMatType(String att_matType){
		    this.matType = att_matType;
		  }

			/**
			 *设置物料类别(mat_type1)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.MatType1</b>的值
			 */
			@Column(name = "mat_type1" )
			public String getMatType1(){
			    return this.matType1;
			}

		  /**
		   *设置 mat_type1字段方法
		   *@param att_matType1 输入<b>MdEnterWarehouseMx.matType1</b>字段的值
		   */
		  public void setMatType1(String att_matType1){
		    this.matType1 = att_matType1;
		  }

			/**
			 *设置拣选分类(mat_type2)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.MatType2</b>的值
			 */
			@Column(name = "mat_type2" )
			public String getMatType2(){
			    return this.matType2;
			}

		  /**
		   *设置 mat_type2字段方法
		   *@param att_matType2 输入<b>MdEnterWarehouseMx.matType2</b>字段的值
		   */
		  public void setMatType2(String att_matType2){
		    this.matType2 = att_matType2;
		  }

			/**
			 *设置码托分类(mat_type3)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.MatType3</b>的值
			 */
			@Column(name = "mat_type3" )
			public String getMatType3(){
			    return this.matType3;
			}

		  /**
		   *设置 mat_type3字段方法
		   *@param att_matType3 输入<b>MdEnterWarehouseMx.matType3</b>字段的值
		   */
		  public void setMatType3(String att_matType3){
		    this.matType3 = att_matType3;
		  }

			/**
			 *设置收货完成时间(receipt_datetime)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.ReceiptDatetime</b>的值
			 */
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "receipt_datetime" )
			public Date getReceiptDatetime(){
			    return this.receiptDatetime;
			}

		  /**
		   *设置 receipt_datetime字段方法
		   *@param att_receiptDatetime 输入<b>MdEnterWarehouseMx.receiptDatetime</b>字段的值
		   */
		  public void setReceiptDatetime(Date att_receiptDatetime){
		    this.receiptDatetime = att_receiptDatetime;
		  }

			/**
			 *设置描述(describes)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.Describes</b>的值
			 */
			@Column(name = "describes" )
			public String getDescribes(){
			    return this.describes;
			}

		  /**
		   *设置 describes字段方法
		   *@param att_describes 输入<b>MdEnterWarehouseMx.describes</b>字段的值
		   */
		  public void setDescribes(String att_describes){
		    this.describes = att_describes;
		  }

			/**
			 *设置创建人(create_ren)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.CreateRen</b>的值
			 */
			@Column(name = "create_ren" )
			public String getCreateRen(){
			    return this.createRen;
			}

		  /**
		   *设置 create_ren字段方法
		   *@param att_createRen 输入<b>MdEnterWarehouseMx.createRen</b>字段的值
		   */
		  public void setCreateRen(String att_createRen){
		    this.createRen = att_createRen;
		  }

			/**
			 *设置创建时间(create_date)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.CreateDate</b>的值
			 */
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "create_date" )
			public Date getCreateDate(){
			    return this.createDate;
			}

		  /**
		   *设置 create_date字段方法
		   *@param att_createDate 输入<b>MdEnterWarehouseMx.createDate</b>字段的值
		   */
		  public void setCreateDate(Date att_createDate){
		    this.createDate = att_createDate;
		  }

			/**
			 *设置修改人(edit_ren)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.EditRen</b>的值
			 */
			@Column(name = "edit_ren" )
			public String getEditRen(){
			    return this.editRen;
			}

		  /**
		   *设置 edit_ren字段方法
		   *@param att_editRen 输入<b>MdEnterWarehouseMx.editRen</b>字段的值
		   */
		  public void setEditRen(String att_editRen){
		    this.editRen = att_editRen;
		  }

			/**
			 *设置修改时间(edit_date)字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.EditDate</b>的值
			 */
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "edit_date" )
			public Date getEditDate(){
			    return this.editDate;
			}

		  /**
		   *设置 edit_date字段方法
		   *@param att_editDate 输入<b>MdEnterWarehouseMx.editDate</b>字段的值
		   */
		  public void setEditDate(Date att_editDate){
		    this.editDate = att_editDate;
		  }


///===============数据库表字段属性end==========

///===============数据库表无关子段字段属性begin==========
			/**
			 *入库单明细表id(wew_mx_id):字段类型为String
			 */
		  private String wewMxId_str;
			/**
			 *入库单信息表id(wew_id):字段类型为String
			 */
		  private String wewId_str;
			/**
			 *物料信息表id(wms_mi_id):字段类型为String
			 */
		  private String wmsMiId_str;
			/**
			 *行号(Line_number):字段类型为String
			 */
		  private String lineNumber_str;
			/**
			 *下单时间(Place_order_time):字段类型为Date
			 */
		  private Date placeOrderTime_start;
			/**
			 *下单时间(Place_order_time):字段类型为Date
			 */
		  private Date placeOrderTime_end;
			/**
			 *收货完成时间(receipt_datetime):字段类型为Date
			 */
		  private Date receiptDatetime_start;
			/**
			 *收货完成时间(receipt_datetime):字段类型为Date
			 */
		  private Date receiptDatetime_end;
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
			 *md_enter_warehouse_mx表里不用like作为条件的字符串
			 */
		  private String Tab_like;
			/**
			 *():字段类型为String
			 *md_enter_warehouse_mx表里order by 字符串
			 */
		  private String Tab_order;

		  private String mmfId_str;
		  
		  //增加一个字段 yanglei 
//		  private String backPrinting;


		  private Double sendNumber; //发货数量
		  private Double orderNumber; //订单数量

	@Transient
	public Double getSendNumber() {
		return sendNumber;
	}

	public void setSendNumber(Double sendNumber) {
		this.sendNumber = sendNumber;
	}

	@Transient
	public Double getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Double orderNumber) {
		this.orderNumber = orderNumber;
	}

//	@Transient
//		public String getBackPrinting() {
//			return backPrinting;
//		}
//
//		public void setBackPrinting(String backPrinting) {
//			this.backPrinting = backPrinting;
//		}

		@Transient
			public String getMmfId_str() {
				return mmfId_str;
			}

			public void setMmfId_str(String mmfId_str) {
				this.mmfId_str = mmfId_str;
			}
			/**
			 *设置wewMxId字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.wewMxId</b>的值
			 */
			@Transient
			public String getWewMxId_str(){
				return this.wewMxId_str;
			}
			/**
			  *设置 wew_mx_id字段方法
			  *@param att_wewMxId 输入<b>MdEnterWarehouseMx.wewMxId</b>字段的值
			  */
			public void setWewMxId_str(String att_wewMxId_str){
				this.wewMxId_str = att_wewMxId_str;
			}
			/**
			 *设置wewId字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.wewId</b>的值
			 */
			@Transient
			public String getWewId_str(){
				return this.wewId_str;
			}
			/**
			  *设置 wew_id字段方法
			  *@param att_wewId 输入<b>MdEnterWarehouseMx.wewId</b>字段的值
			  */
			public void setWewId_str(String att_wewId_str){
				this.wewId_str = att_wewId_str;
			}
			/**
			 *设置wmsMiId字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.wmsMiId</b>的值
			 */
			@Transient
			public String getWmsMiId_str(){
				return this.wmsMiId_str;
			}
			/**
			  *设置 wms_mi_id字段方法
			  *@param att_wmsMiId 输入<b>MdEnterWarehouseMx.wmsMiId</b>字段的值
			  */
			public void setWmsMiId_str(String att_wmsMiId_str){
				this.wmsMiId_str = att_wmsMiId_str;
			}
			/**
			 *设置lineNumber字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.lineNumber</b>的值
			 */
			@Transient
			public String getLineNumber_str(){
				return this.lineNumber_str;
			}
			/**
			  *设置 Line_number字段方法
			  *@param att_lineNumber 输入<b>MdEnterWarehouseMx.lineNumber</b>字段的值
			  */
			public void setLineNumber_str(String att_lineNumber_str){
				this.lineNumber_str = att_lineNumber_str;
			}
			/**
			 *设置placeOrderTime字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.placeOrderTime</b>的值
			 */
			@Transient
			public Date getPlaceOrderTime_start(){
				return this.placeOrderTime_start;
			}
			/**
			  *设置 Place_order_time字段方法
			  *@param att_placeOrderTime 输入<b>MdEnterWarehouseMx.placeOrderTime</b>字段的值
			  */
			public void setPlaceOrderTime_start(Date att_placeOrderTime_start){
				this.placeOrderTime_start = att_placeOrderTime_start;
			}
			/**
			 *设置placeOrderTime字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.placeOrderTime</b>的值
			 */
			@Transient
			public Date getPlaceOrderTime_end(){
				return this.placeOrderTime_end;
			}
			/**
			  *设置 Place_order_time字段方法
			  *@param att_placeOrderTime 输入<b>MdEnterWarehouseMx.placeOrderTime</b>字段的值
			  */
			public void setPlaceOrderTime_end(Date att_placeOrderTime_end){
				this.placeOrderTime_end = att_placeOrderTime_end;
			}
			/**
			 *设置receiptDatetime字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.receiptDatetime</b>的值
			 */
			@Transient
			public Date getReceiptDatetime_start(){
				return this.receiptDatetime_start;
			}
			/**
			  *设置 receipt_datetime字段方法
			  *@param att_receiptDatetime 输入<b>MdEnterWarehouseMx.receiptDatetime</b>字段的值
			  */
			public void setReceiptDatetime_start(Date att_receiptDatetime_start){
				this.receiptDatetime_start = att_receiptDatetime_start;
			}
			/**
			 *设置receiptDatetime字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.receiptDatetime</b>的值
			 */
			@Transient
			public Date getReceiptDatetime_end(){
				return this.receiptDatetime_end;
			}
			/**
			  *设置 receipt_datetime字段方法
			  *@param att_receiptDatetime 输入<b>MdEnterWarehouseMx.receiptDatetime</b>字段的值
			  */
			public void setReceiptDatetime_end(Date att_receiptDatetime_end){
				this.receiptDatetime_end = att_receiptDatetime_end;
			}
			/**
			 *设置createDate字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.createDate</b>的值
			 */
			@Transient
			public Date getCreateDate_start(){
				return this.createDate_start;
			}
			/**
			  *设置 create_date字段方法
			  *@param att_createDate 输入<b>MdEnterWarehouseMx.createDate</b>字段的值
			  */
			public void setCreateDate_start(Date att_createDate_start){
				this.createDate_start = att_createDate_start;
			}
			/**
			 *设置createDate字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.createDate</b>的值
			 */
			@Transient
			public Date getCreateDate_end(){
				return this.createDate_end;
			}
			/**
			  *设置 create_date字段方法
			  *@param att_createDate 输入<b>MdEnterWarehouseMx.createDate</b>字段的值
			  */
			public void setCreateDate_end(Date att_createDate_end){
				this.createDate_end = att_createDate_end;
			}
			/**
			 *设置editDate字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.editDate</b>的值
			 */
			@Transient
			public Date getEditDate_start(){
				return this.editDate_start;
			}
			/**
			  *设置 edit_date字段方法
			  *@param att_editDate 输入<b>MdEnterWarehouseMx.editDate</b>字段的值
			  */
			public void setEditDate_start(Date att_editDate_start){
				this.editDate_start = att_editDate_start;
			}
			/**
			 *设置editDate字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.editDate</b>的值
			 */
			@Transient
			public Date getEditDate_end(){
				return this.editDate_end;
			}
			/**
			  *设置 edit_date字段方法
			  *@param att_editDate 输入<b>MdEnterWarehouseMx.editDate</b>字段的值
			  */
			public void setEditDate_end(Date att_editDate_end){
				this.editDate_end = att_editDate_end;
			}
			/**
			 *设置tab_like字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.tab_like</b>的值
			 */
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法
			  *@param att_tab_like 输入<b>MdEnterWarehouseMx.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法
			 *@return  返回 <b>MdEnterWarehouseMx.tab_order</b>的值
			 */
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法
			  *@param att_tab_order 输入<b>MdEnterWarehouseMx.tab_order</b>字段的值
			  */
			public void setTab_order(String att_Tab_order){
				this.Tab_order = att_Tab_order;
			}

			// 20191118 yangfeng 增加五个新字段
			@Column(name = "product_factory")
			public String getProductFactory() {
				return productFactory;
			}

			public void setProductFactory(String productFactory) {
				this.productFactory = productFactory;
			}

			@Column(name = "product_time")
			public Date getProductTime() {
				return productTime;
			}

			public void setProductTime(Date productTime) {
				this.productTime = productTime;
			}

			@Column(name = "product_valitime")
			public Date getProductValitime() {
				return productValitime;
			}

			public void setProductValitime(Date productValitime) {
				this.productValitime = productValitime;
			}

			@Column(name = "product_name")
			public String getPackageWay() {
				return packageWay;
			}

			public void setPackageWay(String packageWay) {
				this.packageWay = packageWay;
			}

			@Column(name = "batch_certNo")
			public String getBatchCertNo() {
				return batchCertNo;
			}

			public void setBatchCertNo(String batchCertNo) {
				this.batchCertNo = batchCertNo;
			}
			//增加零售价字段

			private Double retailMoney;

			@Column(name = "retail_money")
			public Double getRetailMoney() {
				return retailMoney;
			}

			public void setRetailMoney(Double retailMoney) {
				this.retailMoney = retailMoney;
			}

    public void setUnit(String unit) {
        this.unit = unit;
    }
	@Column(name = "unit")
    public String getUnit() {
        return unit;
    }

	public void setSplitQuantity(Double splitQuantity) {
		this.splitQuantity = splitQuantity;
	}
	@Column(name = "split_quantity")
	public Double getSplitQuantity() {
		return splitQuantity;
	}


	public void setLinkWmsMiId(Integer linkWmsMiId) {
		this.linkWmsMiId = linkWmsMiId;
	}
	@Column(name = "link_wms_mi_id")
	public Integer getLinkWmsMiId() {
		return linkWmsMiId;
	}


	public void setLinkMmfId(Integer linkMmfId) {
		this.linkMmfId = linkMmfId;
	}
	@Column(name = "link_mmf_id")
	public Integer getLinkMmfId() {
		return linkMmfId;
	}

    public void setCurNumber(Double curNumber) {
        this.curNumber = curNumber;
    }
	@Column(name = "cur_number")
    public Double getCurNumber() {
        return curNumber;
    }

	@Formula("(SELECT f.mmf_code FROM md_materiel_format f where f.mmf_id = mmf_id)")
	public String getMmfCode() {
		return mmfCode;
	}

	public void setMmfCode(String mmfCode) {
		this.mmfCode = mmfCode;
	}

	///===============数据库表无关子段字段属性end==========

	private String matCode1;

	@Formula("(SELECT a.mat_code FROM md_materiel_info a WHERE a.wms_mi_id= wms_mi_id limit 1)")
	public String getMatCode1() {
		return matCode1;
	}

	public void setMatCode1(String matCode1) {
		this.matCode1 = matCode1;
	}

	private String logoPath;
	private String brand;
	private Double retailPrice;
	private Double avgPrice;
	private Double allPrice;
	private Double allRetailPrice;
	private String batchCode;
	private String valiedDate;
	private String basicUnitAccuracy; // 注册证号有效期
//	private String backPrinting; //注册证号

	@Formula("(SELECT a.batch_code FROM md_materiel_info a where a.wms_mi_id = wms_mi_id)")
	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

//	@Formula("(SELECT date_format( a.valied_date, '%Y-%m-%d %H:%i:%s' ) FROM md_materiel_info a where a.wms_mi_id = wms_mi_id)")
	@Transient
	public String getValiedDate() {
		return valiedDate;
	}

	public void setValiedDate(String valiedDate) {
		this.valiedDate = valiedDate;
	}

	@Formula("(SELECT a.Basic_unit_accuracy FROM md_materiel_info a where a.wms_mi_id = wms_mi_id)")
	public String getBasicUnitAccuracy() {
		return basicUnitAccuracy;
	}

	public void setBasicUnitAccuracy(String basicUnitAccuracy) {
		this.basicUnitAccuracy = basicUnitAccuracy;
	}

//	@Formula("(SELECT a.back_printing FROM md_materiel_info a where a.wms_mi_id = wms_mi_id)")
	@Column(name = "back_printing")
	public String getBackPrinting() {
		return backPrinting;
	}

	public void setBackPrinting(String backPrinting) {
		this.backPrinting = backPrinting;
	}

	@Transient
	public Double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}

	@Transient
	public Double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}

	@Transient
	public Double getAllRetailPrice() {
		return allRetailPrice;
	}

	public void setAllRetailPrice(Double allRetailPrice) {
		this.allRetailPrice = allRetailPrice;
	}

	@Formula("(SELECT a.retail_price FROM md_materiel_format a where a.mmf_id = mmf_id)")
	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	@Formula("(SELECT a.brand FROM md_materiel_info a where a.wms_mi_id = wms_mi_id)")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

//	@Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code = (select b.lessen_filecode from md_materiel_info b where b.wms_mi_id = wms_mi_id))")
	@Transient
	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

    public void setSearchMatName(String searchMatName) {
        this.searchMatName = searchMatName;
    }
	@Transient
    public String getSearchMatName() {
        return searchMatName;
    }

	public void setSearchMmfName(String searchMmfName) {
		this.searchMmfName = searchMmfName;
	}
	@Transient
	public String getSearchMmfName() {
		return searchMmfName;
	}

	public void setWiId(Integer wiId) {
		this.wiId = wiId;
	}
	@Transient
	public Integer getWiId() {
		return wiId;
	}


	public void setWowMxId(Integer wowMxId) {
		this.wowMxId = wowMxId;
	}
	@Transient
	public Integer getWowMxId() {
		return wowMxId;
	}

	public void setBaseNumber(Double baseNumber) {
		this.baseNumber = baseNumber;
	}
	@Transient
	public Double getBaseNumber() {
		return baseNumber;
	}

	public void setSplitNumber(Double splitNumber) {
		this.splitNumber = splitNumber;
	}
	@Transient
	public Double getSplitNumber() {
		return splitNumber;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	@Transient
	public Double getQuantity() {
		return quantity;
	}

	public void setMieId(Integer mieId) {
		this.mieId = mieId;
	}
	@Transient
	public Integer getMieId() {
		return mieId;
	}

	public void setSplitQuantity1(Double splitQuantity1) {
		this.splitQuantity1 = splitQuantity1;
	}
	@Transient
	public Double getSplitQuantity1() {
		return splitQuantity1;
	}

    public void setBaseNumber1(Double baseNumber1) {
        this.baseNumber1 = baseNumber1;
    }
	@Transient
    public Double getBaseNumber1() {
        return baseNumber1;
    }

	public void setSplitNumber1(Double splitNumber1) {
		this.splitNumber1 = splitNumber1;
	}
	@Transient
	public Double getSplitNumber1() {
		return splitNumber1;
	}

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
	@Transient
    public Double getRatio() {
        return ratio;
    }


	public void setAvgRetailPrice(Double avgRetailPrice) {
		this.avgRetailPrice = avgRetailPrice;
	}
	@Transient
	public Double getAvgRetailPrice() {
		return avgRetailPrice;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Transient
	public String getProductName() {
		return productName;
	}
}