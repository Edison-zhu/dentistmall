package com.hsk.dentistmall.api.persistence;

import javax.persistence.*; 

import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

/**
 * 根据md_delivery_address表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdDeliveryAddress</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table> 
 * <tr><th>字段名称:</th><td>收货地址id(mdaId)</td><th>字段类型:</th><td>Integer(主键)</td></tr> 
 * <tr><th>字段名称:</th><td>所在区域(location)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>配送地址(deliveryAddress)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>邮编(zipCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>收件人(addressee)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>收件联系电话(addresseeTelephone)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>收件人手机(addresseeMobile)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>是否默认值(1是,2否)(ifDefault)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr> 
 * </table>
 *===============无关字段属性===============
 *  <table> 
 * 	<tr><th>字段名称:</th><td>收货地址id(mdaId)	</td><th>属性名称:</th><td>mdaId</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 * @author  作者:admin  
 * @version  v1.0 创建时间: 2017-09-25 14:22:54
 */
 
 
@SuppressWarnings("serial")
@Entity
@Table(name = "md_delivery_address" )
public class MdDeliveryAddress {
///===============数据库表字段属性begin==========
			/**
			 *收货地址id(mdaId):字段类型为Integer  
			 */
 			private Integer mdaId; 
 			
 			private Integer suiId;
 	
			/**
			 *所在区域(location):字段类型为String  
			 */
 			private String location; 
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
			 *配送地址(deliveryAddress):字段类型为String  
			 */
 			private String deliveryAddress; 
 	
			/**
			 *邮编(zipCode):字段类型为String  
			 */
 			private String zipCode; 
 	
			/**
			 *收件人(addressee):字段类型为String  
			 */
 			private String addressee; 
 	
			/**
			 *收件联系电话(addresseeTelephone):字段类型为String  
			 */
 			private String addresseeTelephone; 
 	
			/**
			 *收件人手机(addresseeMobile):字段类型为String  
			 */
 			private String addresseeMobile; 
 	
			/**
			 *是否默认值(1是,2否)(ifDefault):字段类型为String  
			 */
 			private String ifDefault; 
 	
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
			 *设置收货地址id(mda_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdDeliveryAddress.MdaId</b>的值
			 */	 
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "mda_id")
			public Integer getMdaId(){
			    return this.mdaId;
			}

		  /**
		   *设置 mda_id字段方法 
		   *@param att_mdaId 输入<b>MdDeliveryAddress.mdaId</b>字段的值
		   */
		  public void setMdaId(Integer att_mdaId){
		    this.mdaId = att_mdaId;
		  }
		  
		  
		  	@Column(name = "sui_id")
			public Integer getSuiId() {
				return suiId;
			}
	
			public void setSuiId(Integer suiId) {
				this.suiId = suiId;
			}

			/**
			 *设置所在区域(Location)字段方法 
			 *@return  返回 <b>MdDeliveryAddress.Location</b>的值
			 */	 
			@Column(name = "Location" ) 
			public String getLocation(){
			    return this.location;
			}

		  /**
		   *设置 Location字段方法 
		   *@param att_location 输入<b>MdDeliveryAddress.location</b>字段的值
		   */
		  public void setLocation(String att_location){
		    this.location = att_location;
		  }
		  
		  /**
			 *设置省(province)字段方法 
			 *@return  返回 <b>MdDentalClinic.Province</b>的值
			 */	 
			@Column(name = "province" ) 
			public String getProvince(){
			    return this.province;
			}

		  /**
		   *设置 province字段方法 
		   *@param att_province 输入<b>MdDentalClinic.province</b>字段的值
		   */
		  public void setProvince(String att_province){
		    this.province = att_province;
		  }

			/**
			 *设置市(city)字段方法 
			 *@return  返回 <b>MdDentalClinic.City</b>的值
			 */	 
			@Column(name = "city" ) 
			public String getCity(){
			    return this.city;
			}

		  /**
		   *设置 city字段方法 
		   *@param att_city 输入<b>MdDentalClinic.city</b>字段的值
		   */
		  public void setCity(String att_city){
		    this.city = att_city;
		  }

			/**
			 *设置地区(area)字段方法 
			 *@return  返回 <b>MdDentalClinic.Area</b>的值
			 */	 
			@Column(name = "area" ) 
			public String getArea(){
			    return this.area;
			}

		  /**
		   *设置 area字段方法 
		   *@param att_area 输入<b>MdDentalClinic.area</b>字段的值
		   */
		  public void setArea(String att_area){
		    this.area = att_area;
		  }
			/**
			 *设置配送地址(Delivery_address)字段方法 
			 *@return  返回 <b>MdDeliveryAddress.DeliveryAddress</b>的值
			 */	 
			@Column(name = "Delivery_address" ) 
			public String getDeliveryAddress(){
			    return this.deliveryAddress;
			}

		  /**
		   *设置 Delivery_address字段方法 
		   *@param att_deliveryAddress 输入<b>MdDeliveryAddress.deliveryAddress</b>字段的值
		   */
		  public void setDeliveryAddress(String att_deliveryAddress){
		    this.deliveryAddress = att_deliveryAddress;
		  }
  
			/**
			 *设置邮编(zip_code)字段方法 
			 *@return  返回 <b>MdDeliveryAddress.ZipCode</b>的值
			 */	 
			@Column(name = "zip_code" ) 
			public String getZipCode(){
			    return this.zipCode;
			}

		  /**
		   *设置 zip_code字段方法 
		   *@param att_zipCode 输入<b>MdDeliveryAddress.zipCode</b>字段的值
		   */
		  public void setZipCode(String att_zipCode){
		    this.zipCode = att_zipCode;
		  }
  
			/**
			 *设置收件人(Addressee)字段方法 
			 *@return  返回 <b>MdDeliveryAddress.Addressee</b>的值
			 */	 
			@Column(name = "Addressee" ) 
			public String getAddressee(){
			    return this.addressee;
			}

		  /**
		   *设置 Addressee字段方法 
		   *@param att_addressee 输入<b>MdDeliveryAddress.addressee</b>字段的值
		   */
		  public void setAddressee(String att_addressee){
		    this.addressee = att_addressee;
		  }
  
			/**
			 *设置收件联系电话(Addressee_telephone)字段方法 
			 *@return  返回 <b>MdDeliveryAddress.AddresseeTelephone</b>的值
			 */	 
			@Column(name = "Addressee_telephone" ) 
			public String getAddresseeTelephone(){
			    return this.addresseeTelephone;
			}

		  /**
		   *设置 Addressee_telephone字段方法 
		   *@param att_addresseeTelephone 输入<b>MdDeliveryAddress.addresseeTelephone</b>字段的值
		   */
		  public void setAddresseeTelephone(String att_addresseeTelephone){
		    this.addresseeTelephone = att_addresseeTelephone;
		  }
  
			/**
			 *设置收件人手机(Addressee_Mobile)字段方法 
			 *@return  返回 <b>MdDeliveryAddress.AddresseeMobile</b>的值
			 */	 
			@Column(name = "Addressee_Mobile" ) 
			public String getAddresseeMobile(){
			    return this.addresseeMobile;
			}

		  /**
		   *设置 Addressee_Mobile字段方法 
		   *@param att_addresseeMobile 输入<b>MdDeliveryAddress.addresseeMobile</b>字段的值
		   */
		  public void setAddresseeMobile(String att_addresseeMobile){
		    this.addresseeMobile = att_addresseeMobile;
		  }
  
			/**
			 *设置是否默认值(1是,2否)(if_default)字段方法 
			 *@return  返回 <b>MdDeliveryAddress.IfDefault</b>的值
			 */	 
			@Column(name = "if_default" ) 
			public String getIfDefault(){
			    return this.ifDefault;
			}

		  /**
		   *设置 if_default字段方法 
		   *@param att_ifDefault 输入<b>MdDeliveryAddress.ifDefault</b>字段的值
		   */
		  public void setIfDefault(String att_ifDefault){
		    this.ifDefault = att_ifDefault;
		  }
  
			/**
			 *设置创建人(create_ren)字段方法 
			 *@return  返回 <b>MdDeliveryAddress.CreateRen</b>的值
			 */	 
			@Column(name = "create_ren" ) 
			public String getCreateRen(){
			    return this.createRen;
			}

		  /**
		   *设置 create_ren字段方法 
		   *@param att_createRen 输入<b>MdDeliveryAddress.createRen</b>字段的值
		   */
		  public void setCreateRen(String att_createRen){
		    this.createRen = att_createRen;
		  }
  
			/**
			 *设置创建时间(create_date)字段方法 
			 *@return  返回 <b>MdDeliveryAddress.CreateDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "create_date" ) 
			public Date getCreateDate(){
			    return this.createDate;
			}

		  /**
		   *设置 create_date字段方法 
		   *@param att_createDate 输入<b>MdDeliveryAddress.createDate</b>字段的值
		   */
		  public void setCreateDate(Date att_createDate){
		    this.createDate = att_createDate;
		  }
  
			/**
			 *设置修改人(edit_ren)字段方法 
			 *@return  返回 <b>MdDeliveryAddress.EditRen</b>的值
			 */	 
			@Column(name = "edit_ren" ) 
			public String getEditRen(){
			    return this.editRen;
			}

		  /**
		   *设置 edit_ren字段方法 
		   *@param att_editRen 输入<b>MdDeliveryAddress.editRen</b>字段的值
		   */
		  public void setEditRen(String att_editRen){
		    this.editRen = att_editRen;
		  }
  
			/**
			 *设置修改时间(edit_date)字段方法 
			 *@return  返回 <b>MdDeliveryAddress.EditDate</b>的值
			 */	 
			@Temporal(TemporalType.TIMESTAMP)
			@Column(name = "edit_date" ) 
			public Date getEditDate(){
			    return this.editDate;
			}

		  /**
		   *设置 edit_date字段方法 
		   *@param att_editDate 输入<b>MdDeliveryAddress.editDate</b>字段的值
		   */
		  public void setEditDate(Date att_editDate){
		    this.editDate = att_editDate;
		  }
  

///===============数据库表字段属性end==========

///===============数据库表无关子段字段属性begin==========
			/**
			 *收货地址id(mda_id):字段类型为String
			 */
		  private String mdaId_str;  
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
			 *md_delivery_address表里不用like作为条件的字符串
			 */
		  private String Tab_like;  
			/**
			 *():字段类型为String
			 *md_delivery_address表里order by 字符串
			 */
		  private String Tab_order;  
			/**
			 *设置mdaId字段方法  
			 *@return  返回 <b>MdDeliveryAddress.mdaId</b>的值
			 */ 
			@Transient
			public String getMdaId_str(){
				return this.mdaId_str;
			}
			/**
			  *设置 mda_id字段方法 
			  *@param att_mdaId 输入<b>MdDeliveryAddress.mdaId</b>字段的值
			  */
			public void setMdaId_str(String att_mdaId_str){
				this.mdaId_str = att_mdaId_str;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdDeliveryAddress.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_start(){
				return this.createDate_start;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdDeliveryAddress.createDate</b>字段的值
			  */
			public void setCreateDate_start(Date att_createDate_start){
				this.createDate_start = att_createDate_start;
			}
			/**
			 *设置createDate字段方法  
			 *@return  返回 <b>MdDeliveryAddress.createDate</b>的值
			 */ 
			@Transient
			public Date getCreateDate_end(){
				return this.createDate_end;
			}
			/**
			  *设置 create_date字段方法 
			  *@param att_createDate 输入<b>MdDeliveryAddress.createDate</b>字段的值
			  */
			public void setCreateDate_end(Date att_createDate_end){
				this.createDate_end = att_createDate_end;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdDeliveryAddress.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_start(){
				return this.editDate_start;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdDeliveryAddress.editDate</b>字段的值
			  */
			public void setEditDate_start(Date att_editDate_start){
				this.editDate_start = att_editDate_start;
			}
			/**
			 *设置editDate字段方法  
			 *@return  返回 <b>MdDeliveryAddress.editDate</b>的值
			 */ 
			@Transient
			public Date getEditDate_end(){
				return this.editDate_end;
			}
			/**
			  *设置 edit_date字段方法 
			  *@param att_editDate 输入<b>MdDeliveryAddress.editDate</b>字段的值
			  */
			public void setEditDate_end(Date att_editDate_end){
				this.editDate_end = att_editDate_end;
			}
			/**
			 *设置tab_like字段方法  
			 *@return  返回 <b>MdDeliveryAddress.tab_like</b>的值
			 */ 
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_like 输入<b>MdDeliveryAddress.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法  
			 *@return  返回 <b>MdDeliveryAddress.tab_order</b>的值
			 */ 
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_order 输入<b>MdDeliveryAddress.tab_order</b>字段的值
			  */
			public void setTab_order(String att_Tab_order){
				this.Tab_order = att_Tab_order;
			}
///===============数据库表无关子段字段属性end==========
} 