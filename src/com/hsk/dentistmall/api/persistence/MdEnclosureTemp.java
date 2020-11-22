package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 根据md_enclosure表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdEnclosure</B>
 *<hr/>
 *===============数据库表字段属性==========
 *  <table> 
 * <tr><th>字段名称:</th><td>附件表id(rbenId)</td><th>字段类型:</th><td>Integer(主键)</td></tr> 
 * <tr><th>字段名称:</th><td>(orgGxId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>物料信息表id(wmsMiId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>文件编号(fileCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * <tr><th>字段名称:</th><td>文件说明(fileNode)</td><th>字段类型:</th><td>String(可以为空)</td></tr> 
 * </table>
 *===============无关字段属性===============
 *  <table> 
 * 	<tr><th>字段名称:</th><td>附件表id(rbenId)	</td><th>属性名称:</th><td>rbenId</td></tr>
 * 	<tr><th>字段名称:</th><td>(orgGxId)	</td><th>属性名称:</th><td>orgGxId</td></tr>
 * 	<tr><th>字段名称:</th><td>物料信息表id(wmsMiId)	</td><th>属性名称:</th><td>wmsMiId</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 * @author  作者:admin  
 * @version  v1.0 创建时间: 2017-09-25 14:21:17
 */
 
 
@SuppressWarnings("serial")
@Entity
@Table(name = "md_enclosure_temp" )
public class MdEnclosureTemp {
///===============数据库表字段属性begin==========
			/**
			 *附件表id(rbenId):字段类型为Integer  
			 */
 			private Integer rbenId; 
 	
			/**
			 *(orgGxId):字段类型为Integer  
			 */
 			private Integer orgGxId; 
 	
			/**
			 *物料信息表id(wmsMiId):字段类型为Integer  
			 */
 			private Integer wmsMiId; 
 	
			/**
			 *文件编号(fileCode):字段类型为String  
			 */
 			private String fileCode; 
 	
			/**
			 *文件说明(fileNode):字段类型为String  
			 */
 			private String fileNode; 
 	
			/**
			 *设置附件表id(rben_id)字段方法 (该字段是主键)
			 *@return  返回 <b>MdEnclosure.RbenId</b>的值
			 */	 
			@Id
			@GeneratedValue(strategy = IDENTITY)
			@Column(name = "rben_id")
			public Integer getRbenId(){
			    return this.rbenId;
			}

		  /**
		   *设置 rben_id字段方法 
		   *@param att_rbenId 输入<b>MdEnclosure.rbenId</b>字段的值
		   */
		  public void setRbenId(Integer att_rbenId){
		    this.rbenId = att_rbenId;
		  }
  
			/**
			 *设置(org_gx_id)字段方法 
			 *@return  返回 <b>MdEnclosure.OrgGxId</b>的值
			 */	 
			@Column(name = "org_gx_id" ) 
			public Integer getOrgGxId(){
			    return this.orgGxId;
			}

		  /**
		   *设置 org_gx_id字段方法 
		   *@param att_orgGxId 输入<b>MdEnclosure.orgGxId</b>字段的值
		   */
		  public void setOrgGxId(Integer att_orgGxId){
		    this.orgGxId = att_orgGxId;
		  }
  
			/**
			 *设置物料信息表id(wms_mi_id)字段方法 
			 *@return  返回 <b>MdEnclosure.WmsMiId</b>的值
			 */	 
			@Column(name = "wms_mi_id" ) 
			public Integer getWmsMiId(){
			    return this.wmsMiId;
			}

		  /**
		   *设置 wms_mi_id字段方法 
		   *@param att_wmsMiId 输入<b>MdEnclosure.wmsMiId</b>字段的值
		   */
		  public void setWmsMiId(Integer att_wmsMiId){
		    this.wmsMiId = att_wmsMiId;
		  }
  
			/**
			 *设置文件编号(file_code)字段方法 
			 *@return  返回 <b>MdEnclosure.FileCode</b>的值
			 */	 
			@Column(name = "file_code" ) 
			public String getFileCode(){
			    return this.fileCode;
			}

		  /**
		   *设置 file_code字段方法 
		   *@param att_fileCode 输入<b>MdEnclosure.fileCode</b>字段的值
		   */
		  public void setFileCode(String att_fileCode){
		    this.fileCode = att_fileCode;
		  }
  
			/**
			 *设置文件说明(file_Node)字段方法 
			 *@return  返回 <b>MdEnclosure.FileNode</b>的值
			 */	 
			@Column(name = "file_Node" ) 
			public String getFileNode(){
			    return this.fileNode;
			}

		  /**
		   *设置 file_Node字段方法 
		   *@param att_fileNode 输入<b>MdEnclosure.fileNode</b>字段的值
		   */
		  public void setFileNode(String att_fileNode){
		    this.fileNode = att_fileNode;
		  }
  

///===============数据库表字段属性end==========

///===============数据库表无关子段字段属性begin==========
			/**
			 *附件表id(rben_id):字段类型为String
			 */
		  private String rbenId_str;  
			/**
			 *(org_gx_id):字段类型为String
			 */
		  private String orgGxId_str;  
			/**
			 *物料信息表id(wms_mi_id):字段类型为String
			 */
		  private String wmsMiId_str;  
			/**
			 *():字段类型为String
			 *md_enclosure表里不用like作为条件的字符串
			 */
		  private String Tab_like;  
			/**
			 *():字段类型为String
			 *md_enclosure表里order by 字符串
			 */
		  private String Tab_order;  
			/**
			 *设置rbenId字段方法  
			 *@return  返回 <b>MdEnclosure.rbenId</b>的值
			 */ 
			@Transient
			public String getRbenId_str(){
				return this.rbenId_str;
			}
			/**
			  *设置 rben_id字段方法 
			  *@param att_rbenId 输入<b>MdEnclosure.rbenId</b>字段的值
			  */
			public void setRbenId_str(String att_rbenId_str){
				this.rbenId_str = att_rbenId_str;
			}
			/**
			 *设置orgGxId字段方法  
			 *@return  返回 <b>MdEnclosure.orgGxId</b>的值
			 */ 
			@Transient
			public String getOrgGxId_str(){
				return this.orgGxId_str;
			}
			/**
			  *设置 org_gx_id字段方法 
			  *@param att_orgGxId 输入<b>MdEnclosure.orgGxId</b>字段的值
			  */
			public void setOrgGxId_str(String att_orgGxId_str){
				this.orgGxId_str = att_orgGxId_str;
			}
			/**
			 *设置wmsMiId字段方法  
			 *@return  返回 <b>MdEnclosure.wmsMiId</b>的值
			 */ 
			@Transient
			public String getWmsMiId_str(){
				return this.wmsMiId_str;
			}
			/**
			  *设置 wms_mi_id字段方法 
			  *@param att_wmsMiId 输入<b>MdEnclosure.wmsMiId</b>字段的值
			  */
			public void setWmsMiId_str(String att_wmsMiId_str){
				this.wmsMiId_str = att_wmsMiId_str;
			}
			/**
			 *设置tab_like字段方法  
			 *@return  返回 <b>MdEnclosure.tab_like</b>的值
			 */ 
			@Transient
			public String getTab_like(){
				return this.Tab_like;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_like 输入<b>MdEnclosure.tab_like</b>字段的值
			  */
			public void setTab_like(String att_Tab_like){
				this.Tab_like = att_Tab_like;
			}
			/**
			 *设置tab_order字段方法  
			 *@return  返回 <b>MdEnclosure.tab_order</b>的值
			 */ 
			@Transient
			public String getTab_order(){
				return this.Tab_order;
			}
			/**
			  *设置 字段方法 
			  *@param att_tab_order 输入<b>MdEnclosure.tab_order</b>字段的值
			  */
			public void setTab_order(String att_Tab_order){
				this.Tab_order = att_Tab_order;
			}
///===============数据库表无关子段字段属性end==========
} 