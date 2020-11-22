package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

/**
 * 根据md_out_order表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdOutOrder</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table> 
 * <tr><th>字段名称:</th><td>ID(mooId)</td><th>字段类型:</th><td>Integer(主键)</td></tr> 
 * <tr><th>字段名称:</th><td>集团公司id(rbaId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>牙医医院id(rbsId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>牙医门诊id(rbbId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>操作人ID(suiId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>采购单位类型(purchaseType)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>申领单号(mooCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>业务人员 /申领人/外借人---相关人员(userName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>业务人员所属部门/部门(groupName)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>数量1(number1)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>数量1(number2)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>数量1(number3)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>数量1(number4)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>数量1(number5)</td><th>字段类型:</th><td>Double(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>申领时间(orderTime)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>状态(state)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>流程状态(flowState)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * </table>
 *===============无关字段属性===============
 *  <table> 
 * 	<tr><th>字段名称:</th><td>ID(mooId)	</td><th>属性名称:</th><td>mooId</td></tr>
 * 	<tr><th>字段名称:</th><td>集团公司id(rbaId)	</td><th>属性名称:</th><td>rbaId</td></tr>
 * 	<tr><th>字段名称:</th><td>牙医医院id(rbsId)	</td><th>属性名称:</th><td>rbsId</td></tr>
 * 	<tr><th>字段名称:</th><td>牙医门诊id(rbbId)	</td><th>属性名称:</th><td>rbbId</td></tr>
 * 	<tr><th>字段名称:</th><td>操作人ID(suiId)	</td><th>属性名称:</th><td>suiId</td></tr>
 * 	<tr><th>字段名称:</th><td>申领时间(orderTime)	</td><th>属性名称:</th><td>orderTime</td></tr>
 * 	<tr><th>字段名称:</th><td>申领时间(orderTime)	</td><th>属性名称:</th><td>orderTime</td></tr>
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
@Table(name = "md_out_order" )
public class MdOutOrder {
///===============数据库表字段属性begin==========
			/**
			 *ID(mooId):字段类型为Integer  
			 */
 			private Integer mooId; 
 	
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
			 *操作人ID(suiId):字段类型为Integer  
			 */
 			private Integer suiId; 
 	
			/**
			 *采购单位类型(purchaseType):字段类型为String  
			 */
 			private String purchaseType; 
 	
			/**
			 *申领单号(mooCode):字段类型为String  
			 */
 			private String mooCode; 
 	
			/**
			 *业务人员 /申领人/外借人---相关人员(userName):字段类型为String  
			 */
 			private String userName; 
 	
			/**
			 *业务人员所属部门/部门(groupName):字段类型为String  
			 */
 			private String groupName; 
 	
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
 			//最小单位数量与最小单位数量统计
			private Double number6;

 			private Double number7;


 	
			/**
			 *申领时间(orderTime):字段类型为Date  
			 */
 			private Date orderTime; 
 	
			/**
			 *状态(state):字段类型为String  
			 */
 			private String state; 
 	
			/**
			 *流程状态(flowState):字段类型为String  
			 */
 			private String flowState; 
 	
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
			 * 备注
			 */
			private String remarks;
			/**
			 * 是否已读
			 * yanglei 2020 3-31
			 */
			private String isItRead;
	       @Column(name = "IsItRead" )
	       public String getIsItRead() {
				return isItRead;
				}
			public void setIsItRead(String isItRead) {
				this.isItRead = isItRead;
			}

	/**
	 * 申领订单出库时间
	 */
	private Date outTimeStart;
	private Date outTimeEnd;
	/**
			 *设置ID(moo_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdOutOrder.MooId</b>的值
			 */	 
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "moo_id")
			public Integer getMooId(){
			    return this.mooId;
			}

		  /**
		   *设置 moo_id字段方法 
		   *@param att_mooId 输入<b>MdOutOrder.mooId</b>字段的值
		   */
		  public void setMooId(Integer att_mooId){
		    this.mooId = att_mooId;
		  }
  
			/**
			 *设置集团公司id(rba_id)字段方法 
			 *@return  返回 <b>MdOutOrder.RbaId</b>的值
			 */	 
			@Column(name = "rba_id" ) 
			public Integer getRbaId(){
			    return this.rbaId;
			}

		  /**
		   *设置 rba_id字段方法 
		   *@param att_rbaId 输入<b>MdOutOrder.rbaId</b>字段的值
		   */
		  public void setRbaId(Integer att_rbaId){
		    this.rbaId = att_rbaId;
		  }
  
			/**
			 *设置牙医医院id(rbs_id)字段方法 
			 *@return  返回 <b>MdOutOrder.RbsId</b>的值
			 */	 
			@Column(name = "rbs_id" ) 
			public Integer getRbsId(){
			    return this.rbsId;
			}

		  /**
		   *设置 rbs_id字段方法 
		   *@param att_rbsId 输入<b>MdOutOrder.rbsId</b>字段的值
		   */
		  public void setRbsId(Integer att_rbsId){
		    this.rbsId = att_rbsId;
		  }
  
			/**
			 *设置牙医门诊id(rbb_id)字段方法 
			 *@return  返回 <b>MdOutOrder.RbbId</b>的值
			 */	 
			@Column(name = "rbb_id" ) 
			public Integer getRbbId(){
			    return this.rbbId;
			}

		  /**
		   *设置 rbb_id字段方法 
		   *@param att_rbbId 输入<b>MdOutOrder.rbbId</b>字段的值
		   */
		  public void setRbbId(Integer att_rbbId){
		    this.rbbId = att_rbbId;
		  }
  
			/**
			 *设置操作人ID(sui_id)字段方法 
			 *@return  返回 <b>MdOutOrder.SuiId</b>的值
			 */	 
			@Column(name = "sui_id" ) 
			public Integer getSuiId(){
			    return this.suiId;
			}

		  /**
		   *设置 sui_id字段方法 
		   *@param att_suiId 输入<b>MdOutOrder.suiId</b>字段的值
		   */
		  public void setSuiId(Integer att_suiId){
		    this.suiId = att_suiId;
		  }
  
			/**
			 *设置采购单位类型(purchase_type)字段方法 
			 *@return  返回 <b>MdOutOrder.PurchaseType</b>的值
			 */	 
			@Column(name = "purchase_type" ) 
			public String getPurchaseType(){
			    return this.purchaseType;
			}

		  /**
		   *设置 purchase_type字段方法 
		   *@param att_purchaseType 输入<b>MdOutOrder.purchaseType</b>字段的值
		   */
		  public void setPurchaseType(String att_purchaseType){
		    this.purchaseType = att_purchaseType;
		  }
  
			/**
			 *设置申领单号(moo_code)字段方法 
			 *@return  返回 <b>MdOutOrder.MooCode</b>的值
			 */	 
			@Column(name = "moo_code" ) 
			public String getMooCode(){
			    return this.mooCode;
			}

		  /**
		   *设置 moo_code字段方法 
		   *@param att_mooCode 输入<b>MdOutOrder.mooCode</b>字段的值
		   */
		  public void setMooCode(String att_mooCode){
		    this.mooCode = att_mooCode;
		  }
  
			/**
			 *设置业务人员 /申领人/外借人---相关人员(USER_name)字段方法 
			 *@return  返回 <b>MdOutOrder.UserName</b>的值
			 */	 
			@Column(name = "USER_name" ) 
			public String getUserName(){
			    return this.userName;
			}

		  /**
		   *设置 USER_name字段方法 
		   *@param att_userName 输入<b>MdOutOrder.userName</b>字段的值
		   */
		  public void setUserName(String att_userName){
		    this.userName = att_userName;
		  }
  
			/**
			 *设置业务人员所属部门/部门(GROUP_name)字段方法 
			 *@return  返回 <b>MdOutOrder.GroupName</b>的值
			 */	 
			@Column(name = "GROUP_name" ) 
			public String getGroupName(){
			    return this.groupName;
			}

		  /**
		   *设置 GROUP_name字段方法 
		   *@param att_groupName 输入<b>MdOutOrder.groupName</b>字段的值
		   */
		  public void setGroupName(String att_groupName){
		    this.groupName = att_groupName;
		  }
  
			/**
			 *设置数量1(number1)字段方法 
			 *@return  返回 <b>MdOutOrder.Number1</b>的值
			 */	 
			@Column(name = "number1" ) 
			public Double getNumber1(){
			    return this.number1;
			}

		  /**
		   *设置 number1字段方法 
		   *@param att_number1 输入<b>MdOutOrder.number1</b>字段的值
		   */
		  public void setNumber1(Double att_number1){
		    this.number1 = att_number1;
		  }
  
			/**
			 *设置数量1(number2)字段方法 
			 *@return  返回 <b>MdOutOrder.Number2</b>的值
			 */	 
			@Column(name = "number2" ) 
			public Double getNumber2(){
			    return this.number2;
			}

		  /**
		   *设置 number2字段方法 
		   *@param att_number2 输入<b>MdOutOrder.number2</b>字段的值
		   */
		  public void setNumber2(Double att_number2){
		    this.number2 = att_number2;
		  }
  
			/**
			 *设置数量1(number3)字段方法 
			 *@return  返回 <b>MdOutOrder.Number3</b>的值
			 */	 
			@Column(name = "number3" ) 
			public Double getNumber3(){
			    return this.number3;
			}

		  /**
		   *设置 number3字段方法 
		   *@param att_number3 输入<b>MdOutOrder.number3</b>字段的值
		   */
		  public void setNumber3(Double att_number3){
		    this.number3 = att_number3;
		  }
  
			/**
			 *设置数量1(number4)字段方法 
			 *@return  返回 <b>MdOutOrder.Number4</b>的值
			 */	 
			@Column(name = "number4" ) 
			public Double getNumber4(){
			    return this.number4;
			}

		  /**
		   *设置 number4字段方法 
		   *@param att_number4 输入<b>MdOutOrder.number4</b>字段的值
		   */
		  public void setNumber4(Double att_number4){
		    this.number4 = att_number4;
		  }
  
			/**
			 *设置数量1(number5)字段方法 
			 *@return  返回 <b>MdOutOrder.Number5</b>的值
			 */	 
			@Column(name = "number5" ) 
			public Double getNumber5(){
			    return this.number5;
			}

		  /**
		   *设置 number5字段方法 
		   *@param att_number5 输入<b>MdOutOrder.number5</b>字段的值
		   */
		  public void setNumber5(Double att_number5){
		    this.number5 = att_number5;
		  }
  
			/**
			 *设置申领时间(order_time)字段方法 
			 *@return  返回 <b>MdOutOrder.OrderTime</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "order_time" ) 
			public Date getOrderTime(){
			    return this.orderTime;
			}

		  /**
		   *设置 order_time字段方法 
		   *@param att_orderTime 输入<b>MdOutOrder.orderTime</b>字段的值
		   */
		  public void setOrderTime(Date att_orderTime){
		    this.orderTime = att_orderTime;
		  }
  
			/**
			 *设置状态(state)字段方法 
			 *@return  返回 <b>MdOutOrder.State</b>的值
			 */	 
			@Column(name = "state" ) 
			public String getState(){
			    return this.state;
			}

		  /**
		   *设置 state字段方法 
		   *@param att_state 输入<b>MdOutOrder.state</b>字段的值
		   */
		  public void setState(String att_state){
		    this.state = att_state;
		  }
  
			/**
			 *设置流程状态(flow_state)字段方法 
			 *@return  返回 <b>MdOutOrder.FlowState</b>的值
			 */	 
			@Column(name = "flow_state" ) 
			public String getFlowState(){
			    return this.flowState;
			}

		  /**
		   *设置 flow_state字段方法 
		   *@param att_flowState 输入<b>MdOutOrder.flowState</b>字段的值
		   */
		  public void setFlowState(String att_flowState){
		    this.flowState = att_flowState;
		  }
  
			/**
			 *设置修改时间(edit_date)字段方法 
			 *@return  返回 <b>MdOutOrder.EditDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "edit_date" ) 
			public Date getEditDate(){
			    return this.editDate;
			}

		  /**
		   *设置 edit_date字段方法 
		   *@param att_editDate 输入<b>MdOutOrder.editDate</b>字段的值
		   */
		  public void setEditDate(Date att_editDate){
		    this.editDate = att_editDate;
		  }
  
			/**
			 *设置修改人(edit_ren)字段方法 
			 *@return  返回 <b>MdOutOrder.EditRen</b>的值
			 */	 
			@Column(name = "edit_ren" ) 
			public String getEditRen(){
			    return this.editRen;
			}

		  /**
		   *设置 edit_ren字段方法 
		   *@param att_editRen 输入<b>MdOutOrder.editRen</b>字段的值
		   */
		  public void setEditRen(String att_editRen){
		    this.editRen = att_editRen;
		  }
  
			/**
			 *设置创建时间(create_date)字段方法 
			 *@return  返回 <b>MdOutOrder.CreateDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "create_date" ) 
			public Date getCreateDate(){
			    return this.createDate;
			}

		  /**
		   *设置 create_date字段方法 
		   *@param att_createDate 输入<b>MdOutOrder.createDate</b>字段的值
		   */
		  public void setCreateDate(Date att_createDate){
		    this.createDate = att_createDate;
		  }
  
			/**
			 *设置创建人(create_ren)字段方法 
			 *@return  返回 <b>MdOutOrder.CreateRen</b>的值
			 */	 
			@Column(name = "create_ren" ) 
			public String getCreateRen(){
			    return this.createRen;
			}

		  /**
		   *设置 create_ren字段方法 
		   *@param att_createRen 输入<b>MdOutOrder.createRen</b>字段的值
		   */
		  public void setCreateRen(String att_createRen){
		    this.createRen = att_createRen;
		  }

			/**
			 * 20191021 yangfeng 新增字段
			 * 获得备注
			 * @return
			 */
			public String getRemarks() {
				return remarks;
			}

			public void setRemarks(String remarks) {
				this.remarks = remarks;
			}

			@Column(name = "number6" )
			public Double getNumber6() {
				return number6;
			}

			public void setNumber6(Double number6) {
				this.number6 = number6;
			}
			@Column(name = "number7" )
			public Double getNumber7() {
				return number7;
			}

			public void setNumber7(Double number7) {
				this.number7 = number7;
			}


	///===============数据库表字段属性end==========

///===============数据库表无关子段字段属性begin==========
			/**
			 *ID(moo_id):字段类型为String
			 */
		  private String mooId_str;  
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
			 *操作人ID(sui_id):字段类型为String
			 */
		  private String suiId_str;  
			/**
			 *申领时间(order_time):字段类型为Date
			 */
		  private Date orderTime_start;  
			/**
			 *申领时间(order_time):字段类型为Date
			 */
		  private Date orderTime_end;  
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
			 *md_out_order表里不用like作为条件的字符串
			 */
		  private String Tab_like;  
			/**
			 *():字段类型为String
			 *md_out_order表里order by 字符串
			 */
		  private String Tab_order;  
		  
		  private String flowState_str;
			/**
			 *设置mooId字段方法  
			 *@return  返回 <b>MdOutOrder.mooId</b>的值
			 */ 
			@Transient
			public String getMooId_str(){
				return this.mooId_str;
			}
			/**
			  *设置 moo_id字段方法 
			  *@param att_mooId 输入<b>MdOutOrder.mooId</b>字段的值
			  */
			public void setMooId_str(String att_mooId_str){
				this.mooId_str = att_mooId_str;
			}
			/**
			 *设置rbaId字段方法  
			 *@return  返回 <b>MdOutOrder.rbaId</b>的值
			 */ 
			@Transient
			public String getRbaId_str(){
				return this.rbaId_str;
			}
			/**
			  *设置 rba_id字段方法 
			  *@param att_rbaId 输入<b>MdOutOrder.rbaId</b>字段的值
			  */
			public void setRbaId_str(String att_rbaId_str){
				this.rbaId_str = att_rbaId_str;
			}
			/**
			 *设置rbsId字段方法  
			 *@return  返回 <b>MdOutOrder.rbsId</b>的值
			 */ 
			@Transient
			public String getRbsId_str(){
				return this.rbsId_str;
			}
			/**
			  *设置 rbs_id字段方法 
			  *@param att_rbsId 输入<b>MdOutOrder.rbsId</b>字段的值
			  */
			public void setRbsId_str(String att_rbsId_str){
				this.rbsId_str = att_rbsId_str;
			}
			/**
			 *设置rbbId字段方法  
			 *@return  返回 <b>MdOutOrder.rbbId</b>的值
			 */ 
			@Transient
			public String getRbbId_str(){
				return this.rbbId_str;
			}
			/**
			  *设置 rbb_id字段方法 
			  *@param att_rbbId 输入<b>MdOutOrder.rbbId</b>字段的值
			  */
			public void setRbbId_str(String att_rbbId_str){
				this.rbbId_str = att_rbbId_str;
			}
			/**
			 *设置suiId字段方法  
			 *@return  返回 <b>MdOutOrder.suiId</b>的值
			 */ 
			@Transient
			public String getSuiId_str(){
				return this.suiId_str;
			}
			/**
			  *设置 sui_id字段方法 
			  *@param att_suiId 输入<b>MdOutOrder.suiId</b>字段的值
			  */
			public void setSuiId_str(String att_suiId_str){
				this.suiId_str = att_suiId_str;
			}
			/**
			 *设置orderTime字段方法  
			 *@return  返回 <b>MdOutOrder.orderTime</b>的值
			 */ 
			@Transient
			public Date getOrderTime_start(){
				return this.orderTime_start;
			}
			/**
			  *设置 order_time字段方法 
			  *@param att_orderTime 输入<b>MdOutOrder.orderTime</b>字段的值
			  */
			public void setOrderTime_start(Date att_orderTime_start){
				this.orderTime_start = att_orderTime_start;
			}
			/**
			 *设置orderTime字段方法  
			 *@return  返回 <b>MdOutOrder.orderTime</b>的值
			 */ 
			@Transient
			public Date getOrderTime_end(){
				return this.orderTime_end;
			}
			/**
			  *设置 order_time字段方法 
			  *@param att_orderTime 输入<b>MdOutOrder.orderTime</b>字段的值
			  */
			public void setOrderTime_end(Date att_orderTime_end){
				this.orderTime_end = att_orderTime_end;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdOutOrder.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_start(){
				return this.editDate_start;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdOutOrder.editDate</b>字段的值
			  */
			public void setEditDate_start(Date att_editDate_start){
				this.editDate_start = att_editDate_start;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdOutOrder.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_end(){
				return this.editDate_end;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdOutOrder.editDate</b>字段的值
			  */
			public void setEditDate_end(Date att_editDate_end){
				this.editDate_end = att_editDate_end;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdOutOrder.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_start(){
				return this.createDate_start;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdOutOrder.createDate</b>字段的值
			  */
			public void setCreateDate_start(Date att_createDate_start){
				this.createDate_start = att_createDate_start;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdOutOrder.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_end(){
				return this.createDate_end;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdOutOrder.createDate</b>字段的值
			  */
			public void setCreateDate_end(Date att_createDate_end){
				this.createDate_end = att_createDate_end;
			}
			/**
			 *设置tab_like字段方法  
			 *@return  返回 <b>MdOutOrder.tab_like</b>的值
			 */ 
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_like 输入<b>MdOutOrder.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法  
			 *@return  返回 <b>MdOutOrder.tab_order</b>的值
			 */ 
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_order 输入<b>MdOutOrder.tab_order</b>字段的值
			  */
			public void setTab_order(String att_Tab_order){
				this.Tab_order = att_Tab_order;
			}
			@Transient
			public String getFlowState_str() {
				return flowState_str;
			}

			public void setFlowState_str(String flowState_str) {
				this.flowState_str = flowState_str;
			}

			@Transient
			public Date getOutTimeStart() {
				return outTimeStart;
			}

			public void setOutTimeStart(Date outTimeStart) {
				this.outTimeStart = outTimeStart;
			}
			@Transient
			public Date getOutTimeEnd() {
				return outTimeEnd;
			}

			public void setOutTimeEnd(Date outTimeEnd) {
				this.outTimeEnd = outTimeEnd;
			}

			//增加出库备注，出库类型，出库单号，出库编号，出库时间字段
			public String wowCode;

			public Integer wowId;

			public String wowType;

			public  Date finshDate;

			public  String  wowRemarks;

			public Integer missingNumber;

			private String flowStateName;
			//接收对象字段
			public String receivingObject;
	@Formula("(SELECT a.receiving_object FROM md_out_warehouse a WHERE a.RELATED_BILL1= moo_code limit 1)")
			public String getReceivingObject() {
				return receivingObject;
			}
			public void setReceivingObject(String receivingObject) {
				this.receivingObject = receivingObject;
			}

	@Formula("(SELECT a.wow_code FROM md_out_warehouse a WHERE a.RELATED_BILL1= moo_code limit 1)")
			public String getWowCode() {
				return wowCode;
			}

			public void setWowCode(String wowCode) {
				this.wowCode = wowCode;
			}
	@Formula("(SELECT a.wow_type FROM md_out_warehouse a WHERE a.RELATED_BILL1= moo_code limit 1)")
			public String getWowType() {
				return wowType;
			}

			public void setWowType(String wowType) {
				this.wowType = wowType;
			}
	@Formula("(SELECT a.FINSH_DATE FROM md_out_warehouse a WHERE a.RELATED_BILL1= moo_code limit 1)")
	public Date getFinshDate() {
		return finshDate;
	}

	public void setFinshDate(Date finshDate) {
		this.finshDate = finshDate;
	}

	@Formula("(SELECT a.wow_remarks FROM md_out_warehouse a WHERE a.RELATED_BILL1= moo_code limit 1)")
			public String getWowRemarks() {
				return wowRemarks;
			}

			public void setWowRemarks(String wowRemarks) {
				this.wowRemarks = wowRemarks;
			}
	@Formula("(SELECT a.wow_id FROM md_out_warehouse a WHERE a.RELATED_BILL1= moo_code limit 1)")
	public Integer getWowId() {
		return wowId;
	}

	public void setWowId(Integer wowId) {
		this.wowId = wowId;
	}

	@Formula("(SELECT a.number1 - a.number2 FROM md_out_order a where a.moo_id = moo_id )")
	public Integer getMissingNumber() {
		return missingNumber;
	}

	public void setMissingNumber(Integer missingNumber) {
		this.missingNumber = missingNumber;
	}

	@Formula("(SELECT a.param_name FROM sys_parameter a,sys_parameter b WHERE a.param_value=flow_state  and a.sys_spar_id=b.spar_id and b.param_code='PAR171113111313225')")
	public String getFlowStateName() {
		return flowStateName;
	}

	public void setFlowStateName(String flowStateName) {
		this.flowStateName = flowStateName;
	}

	///===============数据库表无关子段字段属性end==========
} 