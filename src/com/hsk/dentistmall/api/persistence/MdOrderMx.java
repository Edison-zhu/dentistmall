package com.hsk.dentistmall.api.persistence;

import javax.persistence.*; 

import org.hibernate.annotations.Formula;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

/**
 * 根据md_order_mx表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdOrderMx</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table> 
 * <tr><th>字段名称:</th><td>订单明细id(momId)</td><th>字段类型:</th><td>Integer(主键)</td></tr> 
 * <tr><th>字段名称:</th><td>订单信息id(moiId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>物料信息表id(wmsMiId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>下单时间(placeOrderTime)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>下单单价金额(unitMoney)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>商品总价(totalMoney)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
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
 * <tr><th>字段名称:</th><td>描述(describes)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>预计到货天数(daysArrival)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * </table>
 *===============无关字段属性===============
 *  <table> 
 * 	<tr><th>字段名称:</th><td>订单明细id(momId)	</td><th>属性名称:</th><td>momId</td></tr>
 * 	<tr><th>字段名称:</th><td>订单信息id(moiId)	</td><th>属性名称:</th><td>moiId</td></tr>
 * 	<tr><th>字段名称:</th><td>物料信息表id(wmsMiId)	</td><th>属性名称:</th><td>wmsMiId</td></tr>
 * 	<tr><th>字段名称:</th><td>下单时间(placeOrderTime)	</td><th>属性名称:</th><td>placeOrderTime</td></tr>
 * 	<tr><th>字段名称:</th><td>下单时间(placeOrderTime)	</td><th>属性名称:</th><td>placeOrderTime</td></tr>
 * 	<tr><th>字段名称:</th><td>数量1(number1)	</td><th>属性名称:</th><td>number1</td></tr>
 * 	<tr><th>字段名称:</th><td>数量1(number2)	</td><th>属性名称:</th><td>number2</td></tr>
 * 	<tr><th>字段名称:</th><td>数量1(number3)	</td><th>属性名称:</th><td>number3</td></tr>
 * 	<tr><th>字段名称:</th><td>数量1(number4)	</td><th>属性名称:</th><td>number4</td></tr>
 * 	<tr><th>字段名称:</th><td>数量1(number5)	</td><th>属性名称:</th><td>number5</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 * @author  作者:admin  
 * @version  v1.0 创建时间: 2017-09-28 17:16:59
 */
 
 
@SuppressWarnings("serial")
@Entity
@Table(name = "md_order_mx" )
public class MdOrderMx {
///===============数据库表字段属性begin==========
			/**
			 *订单明细id(momId):字段类型为Integer  
			 */
 			private Integer momId; 
 	
			/**
			 *订单信息id(moiId):字段类型为Integer  
			 */
 			private Integer moiId; 
 	
			/**
			 *物料信息表id(wmsMiId):字段类型为Integer  
			 */
 			private Integer wmsMiId; 
 			/**
			 *物料规格id(mmfId):字段类型为Integer  
			 */
 			private Integer mmfId;
 	
			/**
			 *下单时间(placeOrderTime):字段类型为Date  
			 */
 			private Date placeOrderTime; 
 	
			/**
			 *下单单价金额(unitMoney):字段类型为Double  
			 */
 			private Double unitMoney; 
 	
			/**
			 *商品总价(totalMoney):字段类型为Double  
			 */
 			private Double totalMoney; 
 	
			/**
			 *下单商品数量(matNumber):字段类型为Double  
			 */
 			private Double matNumber; 
 	
			/**
			 *采购商名称(purchaseUnit):字段类型为String  
			 */
 			private String purchaseUnit; 
 	
			/**
			 *商品编码(matCode):字段类型为String  
			 */
 			private String matCode;
 			private String matCode1;
 	
			/**
			 *商品名称(matName):字段类型为String  
			 */
 			private String matName; 
 	
			/**
			 *基本单位(basicUnit):字段类型为String  
			 */
 			private String basicUnit; 
 	
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
			 *数量1(number1):字段类型为Integer  
			 */
 			private Double number1; 
 	
			/**
			 *数量1(number2):字段类型为Integer  
			 */
 			private Double number2; 
 	
			/**
			 *数量1(number3):字段类型为Integer  
			 */
 			private Double number3; 
 	
			/**
			 *数量1(number4):字段类型为Integer  
			 */
 			private Double number4; 
 	
			/**
			 *数量1(number5):字段类型为Integer  
			 */
 			private Double number5; 
 	
			/**
			 *描述(describes):字段类型为String  
			 */
 			private String describes; 
 	
			/**
			 *预计到货天数(daysArrival):字段类型为Double  
			 */
 			private Double daysArrival; 
 	
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
 			 * 入库数量
 			 */
 			private Double enterNumber;
 			/**
 			 * 确认数量
 			 */
 			private Double shureNumber;
 			/**
 			 * 退货数量
 			 */
 			private Double backNumber;
	private Double splitQuantity;
	private String searchMatName;
	private String searchMmfName;
	private Integer wowMxId;
	private Double baseNumber;
	private Double splitNumber;
	private Double ratio;

	/**
			 *设置订单明细id(mom_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdOrderMx.MomId</b>的值
			 */	 
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "mom_id")
			public Integer getMomId(){
			    return this.momId;
			}

		  /**
		   *设置 mom_id字段方法 
		   *@param att_momId 输入<b>MdOrderMx.momId</b>字段的值
		   */
		  public void setMomId(Integer att_momId){
		    this.momId = att_momId;
		  }
  
			/**
			 *设置订单信息id(moi_id)字段方法 
			 *@return  返回 <b>MdOrderMx.MoiId</b>的值
			 */	 
			@Column(name = "moi_id" ) 
			public Integer getMoiId(){
			    return this.moiId;
			}

		  /**
		   *设置 moi_id字段方法 
		   *@param att_moiId 输入<b>MdOrderMx.moiId</b>字段的值
		   */
		  public void setMoiId(Integer att_moiId){
		    this.moiId = att_moiId;
		  }
  
			/**
			 *设置物料信息表id(wms_mi_id)字段方法 
			 *@return  返回 <b>MdOrderMx.WmsMiId</b>的值
			 */	 
			@Column(name = "wms_mi_id" ) 
			public Integer getWmsMiId(){
			    return this.wmsMiId;
			}

		  /**
		   *设置 wms_mi_id字段方法 
		   *@param att_wmsMiId 输入<b>MdOrderMx.wmsMiId</b>字段的值
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
			 *设置下单时间(Place_order_time)字段方法 
			 *@return  返回 <b>MdOrderMx.PlaceOrderTime</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "Place_order_time" ) 
			public Date getPlaceOrderTime(){
			    return this.placeOrderTime;
			}

		  /**
		   *设置 Place_order_time字段方法 
		   *@param att_placeOrderTime 输入<b>MdOrderMx.placeOrderTime</b>字段的值
		   */
		  public void setPlaceOrderTime(Date att_placeOrderTime){
		    this.placeOrderTime = att_placeOrderTime;
		  }
  
			/**
			 *设置下单单价金额(Unit_money)字段方法 
			 *@return  返回 <b>MdOrderMx.UnitMoney</b>的值
			 */	 
			@Column(name = "Unit_money" ) 
			public Double getUnitMoney(){
			    return this.unitMoney;
			}

		  /**
		   *设置 Unit_money字段方法 
		   *@param att_unitMoney 输入<b>MdOrderMx.unitMoney</b>字段的值
		   */
		  public void setUnitMoney(Double att_unitMoney){
		    this.unitMoney = att_unitMoney;
		  }
  
			/**
			 *设置商品总价(Total_money)字段方法 
			 *@return  返回 <b>MdOrderMx.TotalMoney</b>的值
			 */	 
			@Column(name = "Total_money" ) 
			public Double getTotalMoney(){
			    return this.totalMoney;
			}

		  /**
		   *设置 Total_money字段方法 
		   *@param att_totalMoney 输入<b>MdOrderMx.totalMoney</b>字段的值
		   */
		  public void setTotalMoney(Double att_totalMoney){
		    this.totalMoney = att_totalMoney;
		  }
  
			/**
			 *设置下单商品数量(mat_number)字段方法 
			 *@return  返回 <b>MdOrderMx.MatNumber</b>的值
			 */	 
			@Column(name = "mat_number" ) 
			public Double getMatNumber(){
			    return this.matNumber;
			}

		  /**
		   *设置 mat_number字段方法 
		   *@param att_matNumber 输入<b>MdOrderMx.matNumber</b>字段的值
		   */
		  public void setMatNumber(Double att_matNumber){
		    this.matNumber = att_matNumber;
		  }
  
			/**
			 *设置采购商名称(Purchase_unit)字段方法 
			 *@return  返回 <b>MdOrderMx.PurchaseUnit</b>的值
			 */	 
			@Column(name = "Purchase_unit" ) 
			public String getPurchaseUnit(){
			    return this.purchaseUnit;
			}

		  /**
		   *设置 Purchase_unit字段方法 
		   *@param att_purchaseUnit 输入<b>MdOrderMx.purchaseUnit</b>字段的值
		   */
		  public void setPurchaseUnit(String att_purchaseUnit){
		    this.purchaseUnit = att_purchaseUnit;
		  }
  
			/**
			 *设置商品编码(mat_code)字段方法 
			 *@return  返回 <b>MdOrderMx.MatCode</b>的值
			 */	 
			@Column(name = "mat_code" ) 
			public String getMatCode(){
			    return this.matCode;
			}

		  /**
		   *设置 mat_code字段方法 
		   *@param att_matCode 输入<b>MdOrderMx.matCode</b>字段的值
		   */
		  public void setMatCode(String att_matCode){
		    this.matCode = att_matCode;
		  }

		  @Transient
	public String getMatCode1() {
		return matCode;
	}

	public void setMatCode1(String matCode1) {
		this.matCode1 = matCode1;
	}

	/**
			 *设置商品名称(mat_name)字段方法 
			 *@return  返回 <b>MdOrderMx.MatName</b>的值
			 */	 
			@Column(name = "mat_name" ) 
			public String getMatName(){
			    return this.matName;
			}

		  /**
		   *设置 mat_name字段方法 
		   *@param att_matName 输入<b>MdOrderMx.matName</b>字段的值
		   */
		  public void setMatName(String att_matName){
		    this.matName = att_matName;
		  }
  
			/**
			 *设置基本单位(Basic_unit)字段方法 
			 *@return  返回 <b>MdOrderMx.BasicUnit</b>的值
			 */	 
			@Column(name = "Basic_unit" ) 
			public String getBasicUnit(){
			    return this.basicUnit;
			}

		  /**
		   *设置 Basic_unit字段方法 
		   *@param att_basicUnit 输入<b>MdOrderMx.basicUnit</b>字段的值
		   */
		  public void setBasicUnit(String att_basicUnit){
		    this.basicUnit = att_basicUnit;
		  }
  
			/**
			 *设置规格(NORM)字段方法 
			 *@return  返回 <b>MdOrderMx.Norm</b>的值
			 */	 
			@Column(name = "NORM" ) 
			public String getNorm(){
			    return this.norm;
			}

		  /**
		   *设置 NORM字段方法 
		   *@param att_norm 输入<b>MdOrderMx.norm</b>字段的值
		   */
		  public void setNorm(String att_norm){
		    this.norm = att_norm;
		  }
  
			/**
			 *设置物料类别(mat_type)字段方法 
			 *@return  返回 <b>MdOrderMx.MatType</b>的值
			 */	 
			@Column(name = "mat_type" ) 
			public String getMatType(){
			    return this.matType;
			}

		  /**
		   *设置 mat_type字段方法 
		   *@param att_matType 输入<b>MdOrderMx.matType</b>字段的值
		   */
		  public void setMatType(String att_matType){
		    this.matType = att_matType;
		  }
  
			/**
			 *设置物料类别(mat_type1)字段方法 
			 *@return  返回 <b>MdOrderMx.MatType1</b>的值
			 */	 
			@Column(name = "mat_type1" ) 
			public String getMatType1(){
			    return this.matType1;
			}

		  /**
		   *设置 mat_type1字段方法 
		   *@param att_matType1 输入<b>MdOrderMx.matType1</b>字段的值
		   */
		  public void setMatType1(String att_matType1){
		    this.matType1 = att_matType1;
		  }
  
			/**
			 *设置拣选分类(mat_type2)字段方法 
			 *@return  返回 <b>MdOrderMx.MatType2</b>的值
			 */	 
			@Column(name = "mat_type2" ) 
			public String getMatType2(){
			    return this.matType2;
			}

		  /**
		   *设置 mat_type2字段方法 
		   *@param att_matType2 输入<b>MdOrderMx.matType2</b>字段的值
		   */
		  public void setMatType2(String att_matType2){
		    this.matType2 = att_matType2;
		  }
  
			/**
			 *设置码托分类(mat_type3)字段方法 
			 *@return  返回 <b>MdOrderMx.MatType3</b>的值
			 */	 
			@Column(name = "mat_type3" ) 
			public String getMatType3(){
			    return this.matType3;
			}

		  /**
		   *设置 mat_type3字段方法 
		   *@param att_matType3 输入<b>MdOrderMx.matType3</b>字段的值
		   */
		  public void setMatType3(String att_matType3){
		    this.matType3 = att_matType3;
		  }
  
			/**
			 *设置价格1(money1)字段方法 
			 *@return  返回 <b>MdOrderMx.Money1</b>的值
			 */	 
			@Column(name = "money1" ) 
			public Double getMoney1(){
			    return this.money1;
			}

		  /**
		   *设置 money1字段方法 
		   *@param att_money1 输入<b>MdOrderMx.money1</b>字段的值
		   */
		  public void setMoney1(Double att_money1){
		    this.money1 = att_money1;
		  }
  
			/**
			 *设置价格1(money2)字段方法 
			 *@return  返回 <b>MdOrderMx.Money2</b>的值
			 */	 
			@Column(name = "money2" ) 
			public Double getMoney2(){
			    return this.money2;
			}

		  /**
		   *设置 money2字段方法 
		   *@param att_money2 输入<b>MdOrderMx.money2</b>字段的值
		   */
		  public void setMoney2(Double att_money2){
		    this.money2 = att_money2;
		  }
  
			/**
			 *设置价格1(money3)字段方法 
			 *@return  返回 <b>MdOrderMx.Money3</b>的值
			 */	 
			@Column(name = "money3" ) 
			public Double getMoney3(){
			    return this.money3;
			}

		  /**
		   *设置 money3字段方法 
		   *@param att_money3 输入<b>MdOrderMx.money3</b>字段的值
		   */
		  public void setMoney3(Double att_money3){
		    this.money3 = att_money3;
		  }
  
			/**
			 *设置价格1(money4)字段方法 
			 *@return  返回 <b>MdOrderMx.Money4</b>的值
			 */	 
			@Column(name = "money4" ) 
			public Double getMoney4(){
			    return this.money4;
			}

		  /**
		   *设置 money4字段方法 
		   *@param att_money4 输入<b>MdOrderMx.money4</b>字段的值
		   */
		  public void setMoney4(Double att_money4){
		    this.money4 = att_money4;
		  }
  
			/**
			 *设置价格1(money5)字段方法 
			 *@return  返回 <b>MdOrderMx.Money5</b>的值
			 */	 
			@Column(name = "money5" ) 
			public Double getMoney5(){
			    return this.money5;
			}

		  /**
		   *设置 money5字段方法 
		   *@param att_money5 输入<b>MdOrderMx.money5</b>字段的值
		   */
		  public void setMoney5(Double att_money5){
		    this.money5 = att_money5;
		  }
  
			/**
			 *设置数量1(number1)字段方法 
			 *@return  返回 <b>MdOrderMx.Number1</b>的值
			 */	 
			@Column(name = "number1" ) 
			public Double getNumber1(){
			    return this.number1;
			}

		  /**
		   *设置 number1字段方法 
		   *@param att_number1 输入<b>MdOrderMx.number1</b>字段的值
		   */
		  public void setNumber1(Double att_number1){
		    this.number1 = att_number1;
		  }
  
			/**
			 *设置数量1(number2)字段方法 
			 *@return  返回 <b>MdOrderMx.Number2</b>的值
			 */	 
			@Column(name = "number2" ) 
			public Double getNumber2(){
			    return this.number2;
			}

		  /**
		   *设置 number2字段方法 
		   *@param att_number2 输入<b>MdOrderMx.number2</b>字段的值
		   */
		  public void setNumber2(Double att_number2){
		    this.number2 = att_number2;
		  }
  
			/**
			 *设置数量1(number3)字段方法 
			 *@return  返回 <b>MdOrderMx.Number3</b>的值
			 */	 
			@Column(name = "number3" ) 
			public Double getNumber3(){
			    return this.number3;
			}

		  /**
		   *设置 number3字段方法 
		   *@param att_number3 输入<b>MdOrderMx.number3</b>字段的值
		   */
		  public void setNumber3(Double att_number3){
		    this.number3 = att_number3;
		  }
  
			/**
			 *设置数量1(number4)字段方法 
			 *@return  返回 <b>MdOrderMx.Number4</b>的值
			 */	 
			@Column(name = "number4" ) 
			public Double getNumber4(){
			    return this.number4;
			}

		  /**
		   *设置 number4字段方法 
		   *@param att_number4 输入<b>MdOrderMx.number4</b>字段的值
		   */
		  public void setNumber4(Double att_number4){
		    this.number4 = att_number4;
		  }
  
			/**
			 *设置数量1(number5)字段方法 
			 *@return  返回 <b>MdOrderMx.Number5</b>的值
			 */	 
			@Column(name = "number5" ) 
			public Double getNumber5(){
			    return this.number5;
			}

		  /**
		   *设置 number5字段方法 
		   *@param att_number5 输入<b>MdOrderMx.number5</b>字段的值
		   */
		  public void setNumber5(Double att_number5){
		    this.number5 = att_number5;
		  }
  
			/**
			 *设置描述(describes)字段方法 
			 *@return  返回 <b>MdOrderMx.Describes</b>的值
			 */	 
			@Column(name = "describes" ) 
			public String getDescribes(){
			    return this.describes;
			}

		  /**
		   *设置 describes字段方法 
		   *@param att_describes 输入<b>MdOrderMx.describes</b>字段的值
		   */
		  public void setDescribes(String att_describes){
		    this.describes = att_describes;
		  }
  
			/**
			 *设置预计到货天数(Days_arrival)字段方法 
			 *@return  返回 <b>MdOrderMx.DaysArrival</b>的值
			 */	 
			@Column(name = "Days_arrival" ) 
			public Double getDaysArrival(){
			    return this.daysArrival;
			}

		  /**
		   *设置 Days_arrival字段方法 
		   *@param att_daysArrival 输入<b>MdOrderMx.daysArrival</b>字段的值
		   */
		  public void setDaysArrival(Double att_daysArrival){
		    this.daysArrival = att_daysArrival;
		  }
  
			/**
			 *设置创建人(create_ren)字段方法 
			 *@return  返回 <b>MdOrderMx.CreateRen</b>的值
			 */	 
			@Column(name = "create_ren" ) 
			public String getCreateRen(){
			    return this.createRen;
			}

		  /**
		   *设置 create_ren字段方法 
		   *@param att_createRen 输入<b>MdOrderMx.createRen</b>字段的值
		   */
		  public void setCreateRen(String att_createRen){
		    this.createRen = att_createRen;
		  }
  
			/**
			 *设置创建时间(create_date)字段方法 
			 *@return  返回 <b>MdOrderMx.CreateDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "create_date" ) 
			public Date getCreateDate(){
			    return this.createDate;
			}

		  /**
		   *设置 create_date字段方法 
		   *@param att_createDate 输入<b>MdOrderMx.createDate</b>字段的值
		   */
		  public void setCreateDate(Date att_createDate){
		    this.createDate = att_createDate;
		  }
  
			/**
			 *设置修改人(edit_ren)字段方法 
			 *@return  返回 <b>MdOrderMx.EditRen</b>的值
			 */	 
			@Column(name = "edit_ren" ) 
			public String getEditRen(){
			    return this.editRen;
			}

		  /**
		   *设置 edit_ren字段方法 
		   *@param att_editRen 输入<b>MdOrderMx.editRen</b>字段的值
		   */
		  public void setEditRen(String att_editRen){
		    this.editRen = att_editRen;
		  }
  
			/**
			 *设置修改时间(edit_date)字段方法 
			 *@return  返回 <b>MdOrderMx.EditDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "edit_date" ) 
			public Date getEditDate(){
			    return this.editDate;
			}

		  /**
		   *设置 edit_date字段方法 
		   *@param att_editDate 输入<b>MdOrderMx.editDate</b>字段的值
		   */
		  public void setEditDate(Date att_editDate){
		    this.editDate = att_editDate;
		  }
		  
		  @Column(name = "enter_number" )
		  public Double getEnterNumber() {
				return enterNumber;
		  }

			public void setEnterNumber(Double enterNumber) {
				this.enterNumber = enterNumber;
			}
			@Column(name = "shure_number" )
			public Double getShureNumber() {
				return shureNumber;
			}

			public void setShureNumber(Double shureNumber) {
				this.shureNumber = shureNumber;
			}
			@Column(name = "back_number" )
			public Double getBackNumber() {
				return backNumber;
			}

			public void setBackNumber(Double backNumber) {
				this.backNumber = backNumber;
			}

///===============数据库表字段属性end==========

///===============数据库表无关子段字段属性begin==========
			/**
			 *订单明细id(mom_id):字段类型为String
			 */
		  private String momId_str;  
			/**
			 *订单信息id(moi_id):字段类型为String
			 */
		  private String moiId_str;  
			/**
			 *物料信息表id(wms_mi_id):字段类型为String
			 */
		  private String wmsMiId_str;  
			/**
			 *下单时间(Place_order_time):字段类型为Date
			 */
		  private Date placeOrderTime_start;  
			/**
			 *下单时间(Place_order_time):字段类型为Date
			 */
		  private Date placeOrderTime_end;  
			/**
			 *数量1(number1):字段类型为String
			 */
		  private String number1_str;  
			/**
			 *数量1(number2):字段类型为String
			 */
		  private String number2_str;  
			/**
			 *数量1(number3):字段类型为String
			 */
		  private String number3_str;  
			/**
			 *数量1(number4):字段类型为String
			 */
		  private String number4_str;  
			/**
			 *数量1(number5):字段类型为String
			 */
		  private String number5_str;  
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
			 *md_order_mx表里不用like作为条件的字符串
			 */
		  private String Tab_like;  
			/**
			 *():字段类型为String
			 *md_order_mx表里order by 字符串
			 */
		  private String Tab_order;

	/**
	 * 售后
	 */
	private Integer masId;
			/**
			 *设置momId字段方法  
			 *@return  返回 <b>MdOrderMx.momId</b>的值
			 */ 
			@Transient
			public String getMomId_str(){
				return this.momId_str;
			}
			/**
			  *设置 mom_id字段方法 
			  *@param att_momId 输入<b>MdOrderMx.momId</b>字段的值
			  */
			public void setMomId_str(String att_momId_str){
				this.momId_str = att_momId_str;
			}
			/**
			 *设置moiId字段方法  
			 *@return  返回 <b>MdOrderMx.moiId</b>的值
			 */ 
			@Transient
			public String getMoiId_str(){
				return this.moiId_str;
			}
			/**
			  *设置 moi_id字段方法 
			  *@param att_moiId 输入<b>MdOrderMx.moiId</b>字段的值
			  */
			public void setMoiId_str(String att_moiId_str){
				this.moiId_str = att_moiId_str;
			}
			/**
			 *设置wmsMiId字段方法  
			 *@return  返回 <b>MdOrderMx.wmsMiId</b>的值
			 */ 
			@Transient
			public String getWmsMiId_str(){
				return this.wmsMiId_str;
			}
			/**
			  *设置 wms_mi_id字段方法 
			  *@param att_wmsMiId 输入<b>MdOrderMx.wmsMiId</b>字段的值
			  */
			public void setWmsMiId_str(String att_wmsMiId_str){
				this.wmsMiId_str = att_wmsMiId_str;
			}
			/**
			 *设置placeOrderTime字段方法  
			 *@return  返回 <b>MdOrderMx.placeOrderTime</b>的值
			 */ 
			@Transient
			public Date getPlaceOrderTime_start(){
				return this.placeOrderTime_start;
			}
			/**
			  *设置 Place_order_time字段方法 
			  *@param att_placeOrderTime 输入<b>MdOrderMx.placeOrderTime</b>字段的值
			  */
			public void setPlaceOrderTime_start(Date att_placeOrderTime_start){
				this.placeOrderTime_start = att_placeOrderTime_start;
			}
			/**
			 *设置placeOrderTime字段方法  
			 *@return  返回 <b>MdOrderMx.placeOrderTime</b>的值
			 */ 
			@Transient
			public Date getPlaceOrderTime_end(){
				return this.placeOrderTime_end;
			}
			/**
			  *设置 Place_order_time字段方法 
			  *@param att_placeOrderTime 输入<b>MdOrderMx.placeOrderTime</b>字段的值
			  */
			public void setPlaceOrderTime_end(Date att_placeOrderTime_end){
				this.placeOrderTime_end = att_placeOrderTime_end;
			}
			/**
			 *设置number1字段方法  
			 *@return  返回 <b>MdOrderMx.number1</b>的值
			 */ 
			@Transient
			public String getNumber1_str(){
				return this.number1_str;
			}
			/**
			  *设置 number1字段方法 
			  *@param att_number1 输入<b>MdOrderMx.number1</b>字段的值
			  */
			public void setNumber1_str(String att_number1_str){
				this.number1_str = att_number1_str;
			}
			/**
			 *设置number2字段方法  
			 *@return  返回 <b>MdOrderMx.number2</b>的值
			 */ 
			@Transient
			public String getNumber2_str(){
				return this.number2_str;
			}
			/**
			  *设置 number2字段方法 
			  *@param att_number2 输入<b>MdOrderMx.number2</b>字段的值
			  */
			public void setNumber2_str(String att_number2_str){
				this.number2_str = att_number2_str;
			}
			/**
			 *设置number3字段方法  
			 *@return  返回 <b>MdOrderMx.number3</b>的值
			 */ 
			@Transient
			public String getNumber3_str(){
				return this.number3_str;
			}
			/**
			  *设置 number3字段方法 
			  *@param att_number3 输入<b>MdOrderMx.number3</b>字段的值
			  */
			public void setNumber3_str(String att_number3_str){
				this.number3_str = att_number3_str;
			}
			/**
			 *设置number4字段方法  
			 *@return  返回 <b>MdOrderMx.number4</b>的值
			 */ 
			@Transient
			public String getNumber4_str(){
				return this.number4_str;
			}
			/**
			  *设置 number4字段方法 
			  *@param att_number4 输入<b>MdOrderMx.number4</b>字段的值
			  */
			public void setNumber4_str(String att_number4_str){
				this.number4_str = att_number4_str;
			}
			/**
			 *设置number5字段方法  
			 *@return  返回 <b>MdOrderMx.number5</b>的值
			 */ 
			@Transient
			public String getNumber5_str(){
				return this.number5_str;
			}
			/**
			  *设置 number5字段方法 
			  *@param att_number5 输入<b>MdOrderMx.number5</b>字段的值
			  */
			public void setNumber5_str(String att_number5_str){
				this.number5_str = att_number5_str;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdOrderMx.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_start(){
				return this.createDate_start;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdOrderMx.createDate</b>字段的值
			  */
			public void setCreateDate_start(Date att_createDate_start){
				this.createDate_start = att_createDate_start;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdOrderMx.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_end(){
				return this.createDate_end;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdOrderMx.createDate</b>字段的值
			  */
			public void setCreateDate_end(Date att_createDate_end){
				this.createDate_end = att_createDate_end;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdOrderMx.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_start(){
				return this.editDate_start;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdOrderMx.editDate</b>字段的值
			  */
			public void setEditDate_start(Date att_editDate_start){
				this.editDate_start = att_editDate_start;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdOrderMx.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_end(){
				return this.editDate_end;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdOrderMx.editDate</b>字段的值
			  */
			public void setEditDate_end(Date att_editDate_end){
				this.editDate_end = att_editDate_end;
			}
			/**
			 *设置tab_like字段方法  
			 *@return  返回 <b>MdOrderMx.tab_like</b>的值
			 */ 
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_like 输入<b>MdOrderMx.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法  
			 *@return  返回 <b>MdOrderMx.tab_order</b>的值
			 */ 
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_order 输入<b>MdOrderMx.tab_order</b>字段的值
			  */
			public void setTab_order(String att_Tab_order){
				this.Tab_order = att_Tab_order;
			}
///===============数据库表无关子段字段属性end==========
			///===============数据库表自定义无关子段字段属性========
			private Integer wiId;
			private Integer mieId;
			private Double quantity;
			private String matName2;
			private String norm2;
			//增加产品分类
			private String matTypeName;
			@Formula("(SELECT t.mmt_name FROM md_materiel_type t WHERE t.mmt_path= mat_type)")
			public String getMatTypeName() {
				return matTypeName;
			}
			public void setMatTypeName(String matTypeName) {
				this.matTypeName = matTypeName;
			}

			//增加md_materiel_format中的规格名称
			private String mmfCode;
			@Formula("(SELECT f.mmf_code FROM md_materiel_format f WHERE f.mmf_id= mmf_id)")
			public String getMmfCode() {
				return mmfCode;
			}

			public void setMmfCode(String mmfCode) {
				this.mmfCode = mmfCode;
			}

			private String productName;
			
			@Transient
			public String getProductName() {
				return productName;
			}

			public void setProductName(String productName) {
				this.productName = productName;
			}

			@Transient
			public Integer getWiId() {
				return wiId;
			}

			public void setWiId(Integer wiId) {
				this.wiId = wiId;
			}
			@Transient
			public Integer getMieId() {
				return mieId;
			}

			public void setMieId(Integer mieId) {
				this.mieId = mieId;
			}

			@Transient
			public Double getQuantity() {
				return quantity;
			}

			public void setQuantity(Double quantity) {
				this.quantity = quantity;
			}
			@Transient
			public String getMatName2() {
				return matName2;
			}

			public void setMatName2(String matName2) {
				this.matName2 = matName2;
			}
			@Transient
			public String getNorm2() {
				return norm2;
			}

			public void setNorm2(String norm2) {
				this.norm2 = norm2;
			}

			@Formula("(SELECT max(a.mas_id) FROM md_order_after_sale_mx a WHERE a.mom_id = mom_id and a.after_sale != 3)")
	public Integer getMasId() {
		return masId;
	}

	public void setMasId(Integer masId) {
		this.masId = masId;
	}

	///===============数据库表自定义无关子段字段属性end=====
	//入库时改变订单明细状态,入库时临时删除
	private Integer changeState;
	@Column(name = "changeStae" )
	public Integer getChangeState() {
		return changeState;
	}

	public void setChangeState(Integer changeState) {
		this.changeState = changeState;
	}

    public void setSplitQuantity(Double splitQuantity) {
        this.splitQuantity = splitQuantity;
    }
	@Transient
    public Double getSplitQuantity() {
        return splitQuantity;
    }

    // 需要的额外数据
	private String logoPath;
	private String brand;
	private Double retailPrice;
	private Double avgPrice;
	private Double allPrice;
	private Double allRetailPrice;
	private String batchCode;
	private String valiedDate;
	private String basicUnitAccuracy; // 注册证号有效期
	private String backPrinting; //注册证号

//	@Formula("(SELECT a.batch_code FROM md_materiel_info a where a.wms_mi_id = wms_mi_id)")
	@Transient
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
	@Transient
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

	@Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code = (select b.lessen_filecode from md_materiel_info b where b.wms_mi_id = wms_mi_id))")
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

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
	@Transient
    public Double getRatio() {
        return ratio;
    }
}