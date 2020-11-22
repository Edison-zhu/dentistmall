package com.hsk.dentistmall.api.persistence;

import javax.persistence.*; 

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

/**
 * 根据md_inventory表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdInventory</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table> 
 * <tr><th>字段名称:</th><td>库存表id(wiId)</td><th>字段类型:</th><td>Integer(主键)</td></tr> 
 * <tr><th>字段名称:</th><td>集团公司id(rbaId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>牙医医院id(rbsId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>牙医门诊id(rbbId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>采购单位类型(purchaseType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>物料信息表id(wmsMiId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>批次属性(itemKeyId)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>状态(state)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>库存数量(quantity)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>基本单位(basicUnit)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>基本数量(baseNumber)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * </table>
 *===============无关字段属性===============
 *  <table> 
 * 	<tr><th>字段名称:</th><td>库存表id(wiId)	</td><th>属性名称:</th><td>wiId</td></tr>
 * 	<tr><th>字段名称:</th><td>集团公司id(rbaId)	</td><th>属性名称:</th><td>rbaId</td></tr>
 * 	<tr><th>字段名称:</th><td>牙医医院id(rbsId)	</td><th>属性名称:</th><td>rbsId</td></tr>
 * 	<tr><th>字段名称:</th><td>牙医门诊id(rbbId)	</td><th>属性名称:</th><td>rbbId</td></tr>
 * 	<tr><th>字段名称:</th><td>物料信息表id(wmsMiId)	</td><th>属性名称:</th><td>wmsMiId</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 * @author  作者:admin  
 * @version  v1.0 创建时间: 2017-09-25 14:14:54
 */
 
 
@SuppressWarnings("serial")
@Entity
@Table(name = "md_inventory" )
public class MdInventory {
///===============数据库表字段属性begin==========
			/**
			 *库存表id(wiId):字段类型为Integer  
			 */
 			private Integer wiId; 
 	
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
			 *采购单位类型(purchaseType):字段类型为String  
			 */
 			private String purchaseType; 
 	
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
			 *状态(state):字段类型为String  
			 */
 			private String state; 
 	
			/**
			 *库存数量(quantity):字段类型为Double  
			 */
 			private Double quantity; 
 			/**
 			 * 系数
 			 */
 			private Double ratio;
 			
 			private String unit;
 	
			/**
			 *基本单位(basicUnit):字段类型为String  
			 */
 			private String basicUnit; 
 	
			/**
			 *基本数量(baseNumber):字段类型为Double  
			 */
 			private Double baseNumber; 
 			
 			private Double maxShu;
 			private Integer minDay;
 			
 			private Double warningShu;
 			private Integer warningDay;
 			
 			private Date createDate;
 			private Date editDate;

 			private Date valiedDate;


			/**
			 *设置库存表id(wi_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdInventory.WiId</b>的值
			 */	 
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "wi_id")
			public Integer getWiId(){
			    return this.wiId;
			}

		  /**
		   *设置 wi_id字段方法 
		   *@param att_wiId 输入<b>MdInventory.wiId</b>字段的值
		   */
		  public void setWiId(Integer att_wiId){
		    this.wiId = att_wiId;
		  }
  
			/**
			 *设置集团公司id(rba_id)字段方法 
			 *@return  返回 <b>MdInventory.RbaId</b>的值
			 */	 
			@Column(name = "rba_id" ) 
			public Integer getRbaId(){
			    return this.rbaId;
			}

		  /**
		   *设置 rba_id字段方法 
		   *@param att_rbaId 输入<b>MdInventory.rbaId</b>字段的值
		   */
		  public void setRbaId(Integer att_rbaId){
		    this.rbaId = att_rbaId;
		  }
  
			/**
			 *设置牙医医院id(rbs_id)字段方法 
			 *@return  返回 <b>MdInventory.RbsId</b>的值
			 */	 
			@Column(name = "rbs_id" ) 
			public Integer getRbsId(){
			    return this.rbsId;
			}

		  /**
		   *设置 rbs_id字段方法 
		   *@param att_rbsId 输入<b>MdInventory.rbsId</b>字段的值
		   */
		  public void setRbsId(Integer att_rbsId){
		    this.rbsId = att_rbsId;
		  }
  
			/**
			 *设置牙医门诊id(rbb_id)字段方法 
			 *@return  返回 <b>MdInventory.RbbId</b>的值
			 */	 
			@Column(name = "rbb_id" ) 
			public Integer getRbbId(){
			    return this.rbbId;
			}

		  /**
		   *设置 rbb_id字段方法 
		   *@param att_rbbId 输入<b>MdInventory.rbbId</b>字段的值
		   */
		  public void setRbbId(Integer att_rbbId){
		    this.rbbId = att_rbbId;
		  }
  
			/**
			 *设置采购单位类型(purchase_type)字段方法 
			 *@return  返回 <b>MdInventory.PurchaseType</b>的值
			 */	 
			@Column(name = "purchase_type" ) 
			public String getPurchaseType(){
			    return this.purchaseType;
			}
			@Column(name = "ratio" ) 
		  public Double getRatio() {
				return ratio;
			}

			public void setRatio(Double ratio) {
				this.ratio = ratio;
			}

		/**
		   *设置 purchase_type字段方法 
		   *@param att_purchaseType 输入<b>MdInventory.purchaseType</b>字段的值
		   */
		  public void setPurchaseType(String att_purchaseType){
		    this.purchaseType = att_purchaseType;
		  }
  
			/**
			 *设置物料信息表id(wms_mi_id)字段方法 
			 *@return  返回 <b>MdInventory.WmsMiId</b>的值
			 */	 
			@Column(name = "wms_mi_id" ) 
			public Integer getWmsMiId(){
			    return this.wmsMiId;
			}

		  /**
		   *设置 wms_mi_id字段方法 
		   *@param att_wmsMiId 输入<b>MdInventory.wmsMiId</b>字段的值
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
			 *@return  返回 <b>MdInventory.ItemKeyId</b>的值
			 */	 
			@Column(name = "ITEM_KEY_ID" ) 
			public String getItemKeyId(){
			    return this.itemKeyId;
			}

		  /**
		   *设置 ITEM_KEY_ID字段方法 
		   *@param att_itemKeyId 输入<b>MdInventory.itemKeyId</b>字段的值
		   */
		  public void setItemKeyId(String att_itemKeyId){
		    this.itemKeyId = att_itemKeyId;
		  }
  
			/**
			 *设置状态(state)字段方法 
			 *@return  返回 <b>MdInventory.State</b>的值
			 */	 
			@Column(name = "state" ) 
			public String getState(){
			    return this.state;
			}

		  /**
		   *设置 state字段方法 
		   *@param att_state 输入<b>MdInventory.state</b>字段的值
		   */
		  public void setState(String att_state){
		    this.state = att_state;
		  }
  
			/**
			 *设置库存数量(QUANTITY)字段方法 
			 *@return  返回 <b>MdInventory.Quantity</b>的值
			 */	 
			@Column(name = "QUANTITY" ) 
			public Double getQuantity(){
			    return this.quantity;
			}

		  /**
		   *设置 QUANTITY字段方法 
		   *@param att_quantity 输入<b>MdInventory.quantity</b>字段的值
		   */
		  public void setQuantity(Double att_quantity){
		    this.quantity = att_quantity;
		  }
		  @Column(name = "unit" ) 
			public String getUnit() {
				return unit;
			}
	
			public void setUnit(String unit) {
				this.unit = unit;
			}

			/**
			 *设置基本单位(Basic_unit)字段方法 
			 *@return  返回 <b>MdInventory.BasicUnit</b>的值
			 */	 
			@Column(name = "Basic_unit" ) 
			public String getBasicUnit(){
			    return this.basicUnit;
			}

		  /**
		   *设置 Basic_unit字段方法 
		   *@param att_basicUnit 输入<b>MdInventory.basicUnit</b>字段的值
		   */
		  public void setBasicUnit(String att_basicUnit){
		    this.basicUnit = att_basicUnit;
		  }
  
			/**
			 *设置基本数量(base_number)字段方法 
			 *@return  返回 <b>MdInventory.BaseNumber</b>的值
			 */	 
			@Column(name = "base_number" ) 
			public Double getBaseNumber(){
			    return this.baseNumber;
			}

		  /**
		   *设置 base_number字段方法 
		   *@param att_baseNumber 输入<b>MdInventory.baseNumber</b>字段的值
		   */
		  public void setBaseNumber(Double att_baseNumber){
		    this.baseNumber = att_baseNumber;
		  }
		  
		  @Column(name = "max_shu" )
		  public Double getMaxShu() {
			return maxShu;
		  }

			public void setMaxShu(Double maxShu) {
				this.maxShu = maxShu;
			}
			 @Column(name = "min_day" )
			public Integer getMinDay() {
				return minDay;
			}
	
			public void setMinDay(Integer minDay) {
				this.minDay = minDay;
			}

		  @Column(name = "warning_shu" )
		  public Double getWarningShu() {
				return warningShu;
			}

			public void setWarningShu(Double warningShu) {
				this.warningShu = warningShu;
			}

			@Column(name = "warning_day" )
	public Integer getWarningDay() {
		return warningDay;
	}

	public void setWarningDay(Integer warningDay) {
		this.warningDay = warningDay;
	}

	@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "create_date", length = 0)
			public Date getCreateDate() {
				return this.createDate;
			}

			public void setCreateDate(Date createDate) {
				this.createDate = createDate;
			}

			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "edit_date", length = 0)
			public Date getEditDate() {
				return this.editDate;
			}

			public void setEditDate(Date editDate) {
				this.editDate = editDate;
			}
  

///===============数据库表字段属性end==========

			///===============数据库表无关子段字段属性begin==========
			/**
			 *库存表id(wi_id):字段类型为String
			 */
		  private String wiId_str;  
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
			 *物料信息表id(wms_mi_id):字段类型为String
			 */
		  private String wmsMiId_str;  
			/**
			 *():字段类型为String
			 *md_inventory表里不用like作为条件的字符串
			 */
		  private String Tab_like;  
			/**
			 *():字段类型为String
			 *md_inventory表里order by 字符串
			 */
	private String Tab_order;

	/**
	 * 商品名称
	 */
	private String matName;
	/**
	 * 商品编号
	 */
	private String matCode;

	@Transient
	public String getMatName() {
		return matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}

	@Transient
	public String getMatCode() {
		return matCode;
	}

	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}
			  
			 

			/**
			 *设置wiId字段方法  
			 *@return  返回 <b>MdInventory.wiId</b>的值
			 */ 
			@Transient
			public String getWiId_str(){
				return this.wiId_str;
			}
			

			/**
			  *设置 wi_id字段方法 
			  *@param att_wiId 输入<b>MdInventory.wiId</b>字段的值
			  */
			public void setWiId_str(String att_wiId_str){
				this.wiId_str = att_wiId_str;
			}
			/**
			 *设置rbaId字段方法  
			 *@return  返回 <b>MdInventory.rbaId</b>的值
			 */ 
			@Transient
			public String getRbaId_str(){
				return this.rbaId_str;
			}
			/**
			  *设置 rba_id字段方法 
			  *@param att_rbaId 输入<b>MdInventory.rbaId</b>字段的值
			  */
			public void setRbaId_str(String att_rbaId_str){
				this.rbaId_str = att_rbaId_str;
			}
			/**
			 *设置rbsId字段方法  
			 *@return  返回 <b>MdInventory.rbsId</b>的值
			 */ 
			@Transient
			public String getRbsId_str(){
				return this.rbsId_str;
			}
			/**
			  *设置 rbs_id字段方法 
			  *@param att_rbsId 输入<b>MdInventory.rbsId</b>字段的值
			  */
			public void setRbsId_str(String att_rbsId_str){
				this.rbsId_str = att_rbsId_str;
			}
			/**
			 *设置rbbId字段方法  
			 *@return  返回 <b>MdInventory.rbbId</b>的值
			 */ 
			@Transient
			public String getRbbId_str(){
				return this.rbbId_str;
			}
			/**
			  *设置 rbb_id字段方法 
			  *@param att_rbbId 输入<b>MdInventory.rbbId</b>字段的值
			  */
			public void setRbbId_str(String att_rbbId_str){
				this.rbbId_str = att_rbbId_str;
			}
			/**
			 *设置wmsMiId字段方法  
			 *@return  返回 <b>MdInventory.wmsMiId</b>的值
			 */ 
			@Transient
			public String getWmsMiId_str(){
				return this.wmsMiId_str;
			}
			/**
			  *设置 wms_mi_id字段方法 
			  *@param att_wmsMiId 输入<b>MdInventory.wmsMiId</b>字段的值
			  */
			public void setWmsMiId_str(String att_wmsMiId_str){
				this.wmsMiId_str = att_wmsMiId_str;
			}
			/**
			 *设置tab_like字段方法  
			 *@return  返回 <b>MdInventory.tab_like</b>的值
			 */ 
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_like 输入<b>MdInventory.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法  
			 *@return  返回 <b>MdInventory.tab_order</b>的值
			 */ 
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_order 输入<b>MdInventory.tab_order</b>字段的值
			  */
			public void setTab_order(String att_Tab_order){
				this.Tab_order = att_Tab_order;
			}
///===============数据库表无关子段字段属性end==========

	@Column(name = "valied_date")
	public Date getValiedDate() {
		return valiedDate;
	}

	public void setValiedDate(Date valiedDate) {
		this.valiedDate = valiedDate;
	}
}