package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

/**
 * 根据md_out_order_mx表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdOutOrderMx</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table>
 * <tr><th>字段名称:</th><td>ID(momId)</td><th>字段类型:</th><td>Integer(主键)</td></tr>
 * <tr><th>字段名称:</th><td>申领单信息(mooId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>物料信息表id(wmsMiId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>规格ID(mmfId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>物料编码(matCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>物料名称(matName)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>规格(norm)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>基本单位(basicUnit)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>批次属性(itemKeyId)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>基本数量(baseNumber)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>录入方式(inputMode)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>行号(lineNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>收货单号(soi)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>供应商(supplier)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>扩展属性1(extendPropc1)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>扩展属性1(extendPropc2)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>扩展属性1(extendPropc3)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>扩展属性1(extendPropc4)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>扩展属性1(extendPropc5)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>扩展属性1(extendPropc6)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>扩展属性1(extendPropc7)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>扩展属性1(extendPropc8)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>扩展属性1(extendPropc9)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>扩展属性1(extendPropc10)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>数量1(number1)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>数量1(number2)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>数量1(number3)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>数量1(number4)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>数量1(number5)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>备注(remarks)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * </table>
 *===============无关字段属性===============
 *  <table>
 * 	<tr><th>字段名称:</th><td>ID(momId)	</td><th>属性名称:</th><td>momId</td></tr>
 * 	<tr><th>字段名称:</th><td>申领单信息(mooId)	</td><th>属性名称:</th><td>mooId</td></tr>
 * 	<tr><th>字段名称:</th><td>物料信息表id(wmsMiId)	</td><th>属性名称:</th><td>wmsMiId</td></tr>
 * 	<tr><th>字段名称:</th><td>规格ID(mmfId)	</td><th>属性名称:</th><td>mmfId</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 * @author  作者:admin
 * @version  v1.0 创建时间: 2017-11-13 11:22:28
 */


@SuppressWarnings("serial")
@Entity
@Table(name = "md_out_order_mx" )
public class MdOutOrderMx {
///===============数据库表字段属性begin==========
			/**
			 *ID(momId):字段类型为Integer
			 */
 			private Integer momId;

			/**
			 *申领单信息(mooId):字段类型为Integer
			 */
 			private Integer mooId;

			/**
			 *物料信息表id(wmsMiId):字段类型为Integer
			 */
 			private Integer wmsMiId;

			/**
			 *规格ID(mmfId):字段类型为Integer
			 */
 			private Integer mmfId;

			/**
			 *物料编码(matCode):字段类型为String
			 */
 			private String matCode;

			/**
			 *物料名称(matName):字段类型为String
			 */
 			private String matName;

			/**
			 *规格(norm):字段类型为String
			 */
 			private String norm;

			/**
			 *基本单位(basicUnit):字段类型为String
			 */
 			private String basicUnit;

			/**
			 *批次属性(itemKeyId):字段类型为String
			 */
 			private String itemKeyId;

			/**
			 *基本数量(baseNumber):字段类型为Double
			 */
 			private Double baseNumber;

 			//最小单位数量
			private Double splitQuantity;

			//最小出库数量split_number1
			private Double splitNumber1;
			/**
			 *录入方式(inputMode):字段类型为String
			 */
 			private String inputMode;

			/**
			 *行号(lineNo):字段类型为String
			 */
 			private String lineNo;

			/**
			 *收货单号(soi):字段类型为String
			 */
 			private String soi;

			/**
			 *供应商(supplier):字段类型为String
			 */
 			private String supplier;

			/**
			 *扩展属性1(extendPropc1):字段类型为String
			 */
 			private String extendPropc1;

			/**
			 *扩展属性1(extendPropc2):字段类型为String
			 */
 			private String extendPropc2;

			/**
			 *扩展属性1(extendPropc3):字段类型为String
			 */
 			private String extendPropc3;

			/**
			 *扩展属性1(extendPropc4):字段类型为String
			 */
 			private String extendPropc4;

			/**
			 *扩展属性1(extendPropc5):字段类型为String
			 */
 			private String extendPropc5;

			/**
			 *扩展属性1(extendPropc6):字段类型为String
			 */
 			private String extendPropc6;

			/**
			 *扩展属性1(extendPropc7):字段类型为String
			 */
 			private String extendPropc7;

			/**
			 *扩展属性1(extendPropc8):字段类型为String
			 */
 			private String extendPropc8;

			/**
			 *扩展属性1(extendPropc9):字段类型为String
			 */
 			private String extendPropc9;

			/**
			 *扩展属性1(extendPropc10):字段类型为String
			 */
 			private String extendPropc10;

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
			 *备注(remarks):字段类型为String
			 */
 			private String remarks;

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
	private Double price;
	private Double price1;
	private String productName;


	/**
			 *设置ID(mom_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdOutOrderMx.MomId</b>的值
			 */
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "mom_id")
			public Integer getMomId(){
			    return this.momId;
			}

		  /**
		   *设置 mom_id字段方法
		   *@param att_momId 输入<b>MdOutOrderMx.momId</b>字段的值
		   */
		  public void setMomId(Integer att_momId){
		    this.momId = att_momId;
		  }

			/**
			 *设置申领单信息(moo_id)字段方法
			 *@return  返回 <b>MdOutOrderMx.MooId</b>的值
			 */
			@Column(name = "moo_id" )
			public Integer getMooId(){
			    return this.mooId;
			}

		  /**
		   *设置 moo_id字段方法
		   *@param att_mooId 输入<b>MdOutOrderMx.mooId</b>字段的值
		   */
		  public void setMooId(Integer att_mooId){
		    this.mooId = att_mooId;
		  }

			/**
			 *设置物料信息表id(wms_mi_id)字段方法
			 *@return  返回 <b>MdOutOrderMx.WmsMiId</b>的值
			 */
			@Column(name = "wms_mi_id" )
			public Integer getWmsMiId(){
			    return this.wmsMiId;
			}

		  /**
		   *设置 wms_mi_id字段方法
		   *@param att_wmsMiId 输入<b>MdOutOrderMx.wmsMiId</b>字段的值
		   */
		  public void setWmsMiId(Integer att_wmsMiId){
		    this.wmsMiId = att_wmsMiId;
		  }

			/**
			 *设置规格ID(mmf_id)字段方法
			 *@return  返回 <b>MdOutOrderMx.MmfId</b>的值
			 */
			@Column(name = "mmf_id" )
			public Integer getMmfId(){
			    return this.mmfId;
			}

		  /**
		   *设置 mmf_id字段方法
		   *@param att_mmfId 输入<b>MdOutOrderMx.mmfId</b>字段的值
		   */
		  public void setMmfId(Integer att_mmfId){
		    this.mmfId = att_mmfId;
		  }

			/**
			 *设置物料编码(mat_code)字段方法
			 *@return  返回 <b>MdOutOrderMx.MatCode</b>的值
			 */
			@Column(name = "mat_code" )
			public String getMatCode(){
			    return this.matCode;
			}

		  /**
		   *设置 mat_code字段方法
		   *@param att_matCode 输入<b>MdOutOrderMx.matCode</b>字段的值
		   */
		  public void setMatCode(String att_matCode){
		    this.matCode = att_matCode;
		  }

			/**
			 *设置物料名称(mat_name)字段方法
			 *@return  返回 <b>MdOutOrderMx.MatName</b>的值
			 */
			@Column(name = "mat_name" )
			public String getMatName(){
			    return this.matName;
			}

		  /**
		   *设置 mat_name字段方法
		   *@param att_matName 输入<b>MdOutOrderMx.matName</b>字段的值
		   */
		  public void setMatName(String att_matName){
		    this.matName = att_matName;
		  }

			/**
			 *设置规格(NORM)字段方法
			 *@return  返回 <b>MdOutOrderMx.Norm</b>的值
			 */
			@Column(name = "NORM" )
			public String getNorm(){
			    return this.norm;
			}

		  /**
		   *设置 NORM字段方法
		   *@param att_norm 输入<b>MdOutOrderMx.norm</b>字段的值
		   */
		  public void setNorm(String att_norm){
		    this.norm = att_norm;
		  }

			/**
			 *设置基本单位(Basic_unit)字段方法
			 *@return  返回 <b>MdOutOrderMx.BasicUnit</b>的值
			 */
			@Column(name = "Basic_unit" )
			public String getBasicUnit(){
			    return this.basicUnit;
			}

		  /**
		   *设置 Basic_unit字段方法
		   *@param att_basicUnit 输入<b>MdOutOrderMx.basicUnit</b>字段的值
		   */
		  public void setBasicUnit(String att_basicUnit){
		    this.basicUnit = att_basicUnit;
		  }

			/**
			 *设置批次属性(ITEM_KEY_ID)字段方法
			 *@return  返回 <b>MdOutOrderMx.ItemKeyId</b>的值
			 */
			@Column(name = "ITEM_KEY_ID" )
			public String getItemKeyId(){
			    return this.itemKeyId;
			}

		  /**
		   *设置 ITEM_KEY_ID字段方法
		   *@param att_itemKeyId 输入<b>MdOutOrderMx.itemKeyId</b>字段的值
		   */
		  public void setItemKeyId(String att_itemKeyId){
		    this.itemKeyId = att_itemKeyId;
		  }

			/**
			 *设置基本数量(base_number)字段方法
			 *@return  返回 <b>MdOutOrderMx.BaseNumber</b>的值
			 */
			@Column(name = "base_number" )
			public Double getBaseNumber(){
			    return this.baseNumber;
			}

		  /**
		   *设置 base_number字段方法
		   *@param att_baseNumber 输入<b>MdOutOrderMx.baseNumber</b>字段的值
		   */
		  public void setBaseNumber(Double att_baseNumber){
		    this.baseNumber = att_baseNumber;
		  }

			/**
			 *设置录入方式(Input_mode)字段方法
			 *@return  返回 <b>MdOutOrderMx.InputMode</b>的值
			 */
			@Column(name = "Input_mode" )
			public String getInputMode(){
			    return this.inputMode;
			}

		  /**
		   *设置 Input_mode字段方法
		   *@param att_inputMode 输入<b>MdOutOrderMx.inputMode</b>字段的值
		   */
		  public void setInputMode(String att_inputMode){
		    this.inputMode = att_inputMode;
		  }

			/**
			 *设置行号(LINE_NO)字段方法
			 *@return  返回 <b>MdOutOrderMx.LineNo</b>的值
			 */
			@Column(name = "LINE_NO" )
			public String getLineNo(){
			    return this.lineNo;
			}

		  /**
		   *设置 LINE_NO字段方法
		   *@param att_lineNo 输入<b>MdOutOrderMx.lineNo</b>字段的值
		   */
		  public void setLineNo(String att_lineNo){
		    this.lineNo = att_lineNo;
		  }

			/**
			 *设置收货单号(SOI)字段方法
			 *@return  返回 <b>MdOutOrderMx.Soi</b>的值
			 */
			@Column(name = "SOI" )
			public String getSoi(){
			    return this.soi;
			}

		  /**
		   *设置 SOI字段方法
		   *@param att_soi 输入<b>MdOutOrderMx.soi</b>字段的值
		   */
		  public void setSoi(String att_soi){
		    this.soi = att_soi;
		  }

			/**
			 *设置供应商(SUPPLIER)字段方法
			 *@return  返回 <b>MdOutOrderMx.Supplier</b>的值
			 */
			@Column(name = "SUPPLIER" )
			public String getSupplier(){
			    return this.supplier;
			}

		  /**
		   *设置 SUPPLIER字段方法
		   *@param att_supplier 输入<b>MdOutOrderMx.supplier</b>字段的值
		   */
		  public void setSupplier(String att_supplier){
		    this.supplier = att_supplier;
		  }

			/**
			 *设置扩展属性1(EXTEND_PROPC1)字段方法
			 *@return  返回 <b>MdOutOrderMx.ExtendPropc1</b>的值
			 */
			@Column(name = "EXTEND_PROPC1" )
			public String getExtendPropc1(){
			    return this.extendPropc1;
			}

		  /**
		   *设置 EXTEND_PROPC1字段方法
		   *@param att_extendPropc1 输入<b>MdOutOrderMx.extendPropc1</b>字段的值
		   */
		  public void setExtendPropc1(String att_extendPropc1){
		    this.extendPropc1 = att_extendPropc1;
		  }

			/**
			 *设置扩展属性1(EXTEND_PROPC2)字段方法
			 *@return  返回 <b>MdOutOrderMx.ExtendPropc2</b>的值
			 */
			@Column(name = "EXTEND_PROPC2" )
			public String getExtendPropc2(){
			    return this.extendPropc2;
			}

		  /**
		   *设置 EXTEND_PROPC2字段方法
		   *@param att_extendPropc2 输入<b>MdOutOrderMx.extendPropc2</b>字段的值
		   */
		  public void setExtendPropc2(String att_extendPropc2){
		    this.extendPropc2 = att_extendPropc2;
		  }

			/**
			 *设置扩展属性1(EXTEND_PROPC3)字段方法
			 *@return  返回 <b>MdOutOrderMx.ExtendPropc3</b>的值
			 */
			@Column(name = "EXTEND_PROPC3" )
			public String getExtendPropc3(){
			    return this.extendPropc3;
			}

		  /**
		   *设置 EXTEND_PROPC3字段方法
		   *@param att_extendPropc3 输入<b>MdOutOrderMx.extendPropc3</b>字段的值
		   */
		  public void setExtendPropc3(String att_extendPropc3){
		    this.extendPropc3 = att_extendPropc3;
		  }

			/**
			 *设置扩展属性1(EXTEND_PROPC4)字段方法
			 *@return  返回 <b>MdOutOrderMx.ExtendPropc4</b>的值
			 */
			@Column(name = "EXTEND_PROPC4" )
			public String getExtendPropc4(){
			    return this.extendPropc4;
			}

		  /**
		   *设置 EXTEND_PROPC4字段方法
		   *@param att_extendPropc4 输入<b>MdOutOrderMx.extendPropc4</b>字段的值
		   */
		  public void setExtendPropc4(String att_extendPropc4){
		    this.extendPropc4 = att_extendPropc4;
		  }

			/**
			 *设置扩展属性1(EXTEND_PROPC5)字段方法
			 *@return  返回 <b>MdOutOrderMx.ExtendPropc5</b>的值
			 */
			@Column(name = "EXTEND_PROPC5" )
			public String getExtendPropc5(){
			    return this.extendPropc5;
			}

		  /**
		   *设置 EXTEND_PROPC5字段方法
		   *@param att_extendPropc5 输入<b>MdOutOrderMx.extendPropc5</b>字段的值
		   */
		  public void setExtendPropc5(String att_extendPropc5){
		    this.extendPropc5 = att_extendPropc5;
		  }

			/**
			 *设置扩展属性1(EXTEND_PROPC6)字段方法
			 *@return  返回 <b>MdOutOrderMx.ExtendPropc6</b>的值
			 */
			@Column(name = "EXTEND_PROPC6" )
			public String getExtendPropc6(){
			    return this.extendPropc6;
			}

		  /**
		   *设置 EXTEND_PROPC6字段方法
		   *@param att_extendPropc6 输入<b>MdOutOrderMx.extendPropc6</b>字段的值
		   */
		  public void setExtendPropc6(String att_extendPropc6){
		    this.extendPropc6 = att_extendPropc6;
		  }

			/**
			 *设置扩展属性1(EXTEND_PROPC7)字段方法
			 *@return  返回 <b>MdOutOrderMx.ExtendPropc7</b>的值
			 */
			@Column(name = "EXTEND_PROPC7" )
			public String getExtendPropc7(){
			    return this.extendPropc7;
			}

		  /**
		   *设置 EXTEND_PROPC7字段方法
		   *@param att_extendPropc7 输入<b>MdOutOrderMx.extendPropc7</b>字段的值
		   */
		  public void setExtendPropc7(String att_extendPropc7){
		    this.extendPropc7 = att_extendPropc7;
		  }

			/**
			 *设置扩展属性1(EXTEND_PROPC8)字段方法
			 *@return  返回 <b>MdOutOrderMx.ExtendPropc8</b>的值
			 */
			@Column(name = "EXTEND_PROPC8" )
			public String getExtendPropc8(){
			    return this.extendPropc8;
			}

		  /**
		   *设置 EXTEND_PROPC8字段方法
		   *@param att_extendPropc8 输入<b>MdOutOrderMx.extendPropc8</b>字段的值
		   */
		  public void setExtendPropc8(String att_extendPropc8){
		    this.extendPropc8 = att_extendPropc8;
		  }

			/**
			 *设置扩展属性1(EXTEND_PROPC9)字段方法
			 *@return  返回 <b>MdOutOrderMx.ExtendPropc9</b>的值
			 */
			@Column(name = "EXTEND_PROPC9" )
			public String getExtendPropc9(){
			    return this.extendPropc9;
			}

		  /**
		   *设置 EXTEND_PROPC9字段方法
		   *@param att_extendPropc9 输入<b>MdOutOrderMx.extendPropc9</b>字段的值
		   */
		  public void setExtendPropc9(String att_extendPropc9){
		    this.extendPropc9 = att_extendPropc9;
		  }

			/**
			 *设置扩展属性1(EXTEND_PROPC10)字段方法
			 *@return  返回 <b>MdOutOrderMx.ExtendPropc10</b>的值
			 */
			@Column(name = "EXTEND_PROPC10" )
			public String getExtendPropc10(){
			    return this.extendPropc10;
			}

		  /**
		   *设置 EXTEND_PROPC10字段方法
		   *@param att_extendPropc10 输入<b>MdOutOrderMx.extendPropc10</b>字段的值
		   */
		  public void setExtendPropc10(String att_extendPropc10){
		    this.extendPropc10 = att_extendPropc10;
		  }

			/**
			 *设置数量1(number1)字段方法
			 *@return  返回 <b>MdOutOrderMx.Number1</b>的值
			 */
			@Column(name = "number1" )
			public Double getNumber1(){
			    return this.number1;
			}

		  /**
		   *设置 number1字段方法
		   *@param att_number1 输入<b>MdOutOrderMx.number1</b>字段的值
		   */
		  public void setNumber1(Double att_number1){
		    this.number1 = att_number1;
		  }

			/**
			 *设置数量1(number2)字段方法
			 *@return  返回 <b>MdOutOrderMx.Number2</b>的值
			 */
			@Column(name = "number2" )
			public Double getNumber2(){
			    return this.number2;
			}

		  /**
		   *设置 number2字段方法
		   *@param att_number2 输入<b>MdOutOrderMx.number2</b>字段的值
		   */
		  public void setNumber2(Double att_number2){
		    this.number2 = att_number2;
		  }

			/**
			 *设置数量1(number3)字段方法
			 *@return  返回 <b>MdOutOrderMx.Number3</b>的值
			 */
			@Column(name = "number3" )
			public Double getNumber3(){
			    return this.number3;
			}

		  /**
		   *设置 number3字段方法
		   *@param att_number3 输入<b>MdOutOrderMx.number3</b>字段的值
		   */
		  public void setNumber3(Double att_number3){
		    this.number3 = att_number3;
		  }

			/**
			 *设置数量1(number4)字段方法
			 *@return  返回 <b>MdOutOrderMx.Number4</b>的值
			 */
			@Column(name = "number4" )
			public Double getNumber4(){
			    return this.number4;
			}

		  /**
		   *设置 number4字段方法
		   *@param att_number4 输入<b>MdOutOrderMx.number4</b>字段的值
		   */
		  public void setNumber4(Double att_number4){
		    this.number4 = att_number4;
		  }

			/**
			 *设置数量1(number5)字段方法
			 *@return  返回 <b>MdOutOrderMx.Number5</b>的值
			 */
			@Column(name = "number5" )
			public Double getNumber5(){
			    return this.number5;
			}

		  /**
		   *设置 number5字段方法
		   *@param att_number5 输入<b>MdOutOrderMx.number5</b>字段的值
		   */
		  public void setNumber5(Double att_number5){
		    this.number5 = att_number5;
		  }

			/**
			 *设置备注(REMARKS)字段方法
			 *@return  返回 <b>MdOutOrderMx.Remarks</b>的值
			 */
			@Column(name = "REMARKS" )
			public String getRemarks(){
			    return this.remarks;
			}

		  /**
		   *设置 REMARKS字段方法
		   *@param att_remarks 输入<b>MdOutOrderMx.remarks</b>字段的值
		   */
		  public void setRemarks(String att_remarks){
		    this.remarks = att_remarks;
		  }

			/**
			 *设置修改时间(edit_date)字段方法
			 *@return  返回 <b>MdOutOrderMx.EditDate</b>的值
			 */
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "edit_date" )
			public Date getEditDate(){
			    return this.editDate;
			}

		  /**
		   *设置 edit_date字段方法
		   *@param att_editDate 输入<b>MdOutOrderMx.editDate</b>字段的值
		   */
		  public void setEditDate(Date att_editDate){
		    this.editDate = att_editDate;
		  }

			/**
			 *设置修改人(edit_ren)字段方法
			 *@return  返回 <b>MdOutOrderMx.EditRen</b>的值
			 */
			@Column(name = "edit_ren" )
			public String getEditRen(){
			    return this.editRen;
			}

		  /**
		   *设置 edit_ren字段方法
		   *@param att_editRen 输入<b>MdOutOrderMx.editRen</b>字段的值
		   */
		  public void setEditRen(String att_editRen){
		    this.editRen = att_editRen;
		  }

			/**
			 *设置创建时间(create_date)字段方法
			 *@return  返回 <b>MdOutOrderMx.CreateDate</b>的值
			 */
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "create_date" )
			public Date getCreateDate(){
			    return this.createDate;
			}

		  /**
		   *设置 create_date字段方法
		   *@param att_createDate 输入<b>MdOutOrderMx.createDate</b>字段的值
		   */
		  public void setCreateDate(Date att_createDate){
		    this.createDate = att_createDate;
		  }

			/**
			 *设置创建人(create_ren)字段方法
			 *@return  返回 <b>MdOutOrderMx.CreateRen</b>的值
			 */
			@Column(name = "create_ren" )
			public String getCreateRen(){
			    return this.createRen;
			}

		  /**
		   *设置 create_ren字段方法
		   *@param att_createRen 输入<b>MdOutOrderMx.createRen</b>字段的值
		   */
		  public void setCreateRen(String att_createRen){
		    this.createRen = att_createRen;
		  }


///===============数据库表字段属性end==========

///===============数据库表无关子段字段属性begin==========
			/**
			 *ID(mom_id):字段类型为String
			 */
		  private String momId_str;
			/**
			 *申领单信息(moo_id):字段类型为String
			 */
		  private String mooId_str;
			/**
			 *物料信息表id(wms_mi_id):字段类型为String
			 */
		  private String wmsMiId_str;
			/**
			 *规格ID(mmf_id):字段类型为String
			 */
		  private String mmfId_str;
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
			 *():字段类型为String
			 *md_out_order_mx表里不用like作为条件的字符串
			 */
		  private String Tab_like;
			/**
			 *():字段类型为String
			 *md_out_order_mx表里order by 字符串
			 */
		  private String Tab_order;
			/**
			 *设置momId字段方法
			 *@return  返回 <b>MdOutOrderMx.momId</b>的值
			 */
			@Transient
			public String getMomId_str(){
				return this.momId_str;
			}
			/**
			  *设置 mom_id字段方法
			  *@param att_momId 输入<b>MdOutOrderMx.momId</b>字段的值
			  */
			public void setMomId_str(String att_momId_str){
				this.momId_str = att_momId_str;
			}
			/**
			 *设置mooId字段方法
			 *@return  返回 <b>MdOutOrderMx.mooId</b>的值
			 */
			@Transient
			public String getMooId_str(){
				return this.mooId_str;
			}
			/**
			  *设置 moo_id字段方法
			  *@param att_mooId 输入<b>MdOutOrderMx.mooId</b>字段的值
			  */
			public void setMooId_str(String att_mooId_str){
				this.mooId_str = att_mooId_str;
			}
			/**
			 *设置wmsMiId字段方法
			 *@return  返回 <b>MdOutOrderMx.wmsMiId</b>的值
			 */
			@Transient
			public String getWmsMiId_str(){
				return this.wmsMiId_str;
			}
			/**
			  *设置 wms_mi_id字段方法
			  *@param att_wmsMiId 输入<b>MdOutOrderMx.wmsMiId</b>字段的值
			  */
			public void setWmsMiId_str(String att_wmsMiId_str){
				this.wmsMiId_str = att_wmsMiId_str;
			}
			/**
			 *设置mmfId字段方法
			 *@return  返回 <b>MdOutOrderMx.mmfId</b>的值
			 */
			@Transient
			public String getMmfId_str(){
				return this.mmfId_str;
			}
			/**
			  *设置 mmf_id字段方法
			  *@param att_mmfId 输入<b>MdOutOrderMx.mmfId</b>字段的值
			  */
			public void setMmfId_str(String att_mmfId_str){
				this.mmfId_str = att_mmfId_str;
			}
			/**
			 *设置editDate字段方法
			 *@return  返回 <b>MdOutOrderMx.editDate</b>的值
			 */
			@Transient
			public Date getEditDate_start(){
				return this.editDate_start;
			}
			/**
			  *设置 edit_date字段方法
			  *@param att_editDate 输入<b>MdOutOrderMx.editDate</b>字段的值
			  */
			public void setEditDate_start(Date att_editDate_start){
				this.editDate_start = att_editDate_start;
			}
			/**
			 *设置editDate字段方法
			 *@return  返回 <b>MdOutOrderMx.editDate</b>的值
			 */
			@Transient
			public Date getEditDate_end(){
				return this.editDate_end;
			}
			/**
			  *设置 edit_date字段方法
			  *@param att_editDate 输入<b>MdOutOrderMx.editDate</b>字段的值
			  */
			public void setEditDate_end(Date att_editDate_end){
				this.editDate_end = att_editDate_end;
			}
			/**
			 *设置createDate字段方法
			 *@return  返回 <b>MdOutOrderMx.createDate</b>的值
			 */
			@Transient
			public Date getCreateDate_start(){
				return this.createDate_start;
			}
			/**
			  *设置 create_date字段方法
			  *@param att_createDate 输入<b>MdOutOrderMx.createDate</b>字段的值
			  */
			public void setCreateDate_start(Date att_createDate_start){
				this.createDate_start = att_createDate_start;
			}
			/**
			 *设置createDate字段方法
			 *@return  返回 <b>MdOutOrderMx.createDate</b>的值
			 */
			@Transient
			public Date getCreateDate_end(){
				return this.createDate_end;
			}
			/**
			  *设置 create_date字段方法
			  *@param att_createDate 输入<b>MdOutOrderMx.createDate</b>字段的值
			  */
			public void setCreateDate_end(Date att_createDate_end){
				this.createDate_end = att_createDate_end;
			}
			/**
			 *设置tab_like字段方法
			 *@return  返回 <b>MdOutOrderMx.tab_like</b>的值
			 */
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法
			  *@param att_tab_like 输入<b>MdOutOrderMx.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法
			 *@return  返回 <b>MdOutOrderMx.tab_order</b>的值
			 */
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法
			  *@param att_tab_order 输入<b>MdOutOrderMx.tab_order</b>字段的值
			  */
			public void setTab_order(String att_Tab_order){
				this.Tab_order = att_Tab_order;
			}
	@Column(name = "split_quantity" )
	public Double getSplitQuantity() {
		return splitQuantity;
	}

	public void setSplitQuantity(Double splitQuantity) {
		this.splitQuantity = splitQuantity;
	}
//	split_number1

	@Column(name = "split_number1" )
	public Double getSplitNumber1() {
		return splitNumber1;
	}

	public void setSplitNumber1(Double splitNumber1) {
		this.splitNumber1 = splitNumber1;
	}

	///===============数据库表无关子段字段属性end==========
///===============数据库表自定义无关子段字段属性========
			private Integer wiId;
			private Double quantity;
			private Double splitQuantity1;
			@Transient
			public Integer getWiId() {
				return wiId;
			}

			public void setWiId(Integer wiId) {
				this.wiId = wiId;
			}

			@Transient
	public Double getSplitQuantity1() {
		return splitQuantity1;
	}

	public void setSplitQuantity1(Double splitQuantity1) {
		this.splitQuantity1 = splitQuantity1;
	}

	@Transient
			public Double getQuantity() {
				return quantity;
			}

			public void setQuantity(Double quantity) {
				this.quantity = quantity;
			}
///===============数据库表自定义无关子段字段属性end=====

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
	private Double retailPrice1;
	private Double avgPrice;
	private Double allPrice;
	private Double allRetailPrice;
	private String batchCode;
	private String valiedDate;
	private String basicUnitAccuracy; // 注册证号有效期
	private String backPrinting; //注册证号
	private Double ratio;

	private Double avgRetailPrice;

	@Transient
	public Double getAvgRetailPrice() {
		return avgRetailPrice;
	}

	public void setAvgRetailPrice(Double avgRetailPrice) {
		this.avgRetailPrice = avgRetailPrice;
	}

	@Transient
	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

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

	@Transient
	public Double getRetailPrice1() {
		return retailPrice1;
	}

	public void setRetailPrice1(Double retailPrice1) {
		this.retailPrice1 = retailPrice1;
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

	public void setPrice(Double price) {
		this.price = price;
	}
	@Transient
	public Double getPrice() {
		return price;
	}

	public void setPrice1(Double price1) {
		this.price1 = price1;
	}
	@Transient
	public Double getPrice1() {
		return price1;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Transient
	public String getProductName() {
		return productName;
	}
}