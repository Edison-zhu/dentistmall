package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.text.SimpleDateFormat;
import java.util.*;
import com.hsk.supper.dto.comm.PagerModel;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_enter_warehouse表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:54
 */
@Component
public class MdEnterWarehouseApiDao extends SupperDao implements IMdEnterWarehouseApiDao {

	/**
	 * 根据md_enter_warehouse表主键查找MdEnterWarehouse对象记录
	 * 
	 * @param  WewId  Integer类型(md_enter_warehouse表主键)
	 * @return MdEnterWarehouse md_enter_warehouse表记录
	 * @throws HSKDBException
	 */	
	public MdEnterWarehouse findMdEnterWarehouseById(Integer WewId) throws HSKDBException{
				MdEnterWarehouse  att_MdEnterWarehouse=new MdEnterWarehouse();				
				if(WewId!=null){
					att_MdEnterWarehouse.setWewId(WewId);	
				    Object obj=	this.getOne(att_MdEnterWarehouse);
					if(obj!=null){
						att_MdEnterWarehouse=(MdEnterWarehouse) obj;
					}
				}
				return  att_MdEnterWarehouse;
	}
	 /**
	  * 根据md_enter_warehouse表主键删除MdEnterWarehouse对象记录
	  * @param  WewId  Integer类型(md_enter_warehouse表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdEnterWarehouseById(Integer WewId) throws HSKDBException{ 
		 MdEnterWarehouse  att_MdEnterWarehouse=new MdEnterWarehouse();	
		  if(WewId!=null){
					  att_MdEnterWarehouse.setWewId(WewId);
				  	  Object obj=	this.getOne(att_MdEnterWarehouse);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_enter_warehouse表主键修改MdEnterWarehouse对象记录
     * @param  WewId  Integer类型(md_enter_warehouse表主键)
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
     * @return MdEnterWarehouse  修改后的MdEnterWarehouse对象记录
	 * @throws HSKDBException
	 */
	public  MdEnterWarehouse updateMdEnterWarehouseById(Integer WewId,MdEnterWarehouse att_MdEnterWarehouse) throws HSKDBException{
		  if(WewId!=null){
					att_MdEnterWarehouse.setWewId(WewId);
				   	Object obj=	this.getOne(att_MdEnterWarehouse);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdEnterWarehouse;
	}
	
	/**
	 * 新增md_enter_warehouse表 记录
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdEnterWarehouse(MdEnterWarehouse att_MdEnterWarehouse) throws HSKDBException{
		 return this.newObject(att_MdEnterWarehouse);
	} 
		
	/**
	 * 新增或修改md_enter_warehouse表记录 ,如果md_enter_warehouse表主键MdEnterWarehouse.WewId为空就是添加，如果非空就是修改
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
	 * @throws HSKDBException
	 */
	public  MdEnterWarehouse saveOrUpdateMdEnterWarehouse(MdEnterWarehouse att_MdEnterWarehouse) throws HSKDBException{
		  this.getHibernateTemplate().saveOrUpdate(att_MdEnterWarehouse);
		  return att_MdEnterWarehouse;
	}
	
	/**
	 *根据MdEnterWarehouse对象作为对(md_enter_warehouse表进行查询，获取列表对象
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
     * @return List<MdEnterWarehouse>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdEnterWarehouse> getListByMdEnterWarehouse(MdEnterWarehouse att_MdEnterWarehouse) throws HSKDBException{
		String Hql=this.getHql( att_MdEnterWarehouse); 
		List<MdEnterWarehouse> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	/**
	 *根据MdEnterWarehouse对象作为对(md_enter_warehouse表进行查询，获取分页对象
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
     * @return List<MdEnterWarehouse>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdEnterWarehouse(MdEnterWarehouse att_MdEnterWarehouse)
			throws HSKDBException {
		String Hql=this.getHql(att_MdEnterWarehouse);
		return this.getHibernateDao().findByPage(Hql); 
	} 
	/**
	 * 根据MdEnterWarehouse对象获取Hql字符串
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
	 *  @return hql字符串
	 */
	private String  getHql(MdEnterWarehouse att_MdEnterWarehouse){
			 StringBuffer sbuffer = new StringBuffer( " from  MdEnterWarehouse  where  1=1  ");
			 String likeStr=  att_MdEnterWarehouse.getTab_like();
			 String orderStr= att_MdEnterWarehouse.getTab_order();
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 
			 //*****************无关字段处理begin*****************
						   		 //处理in条件 入库单信息表id(wewId)
							     if(att_MdEnterWarehouse.getWewId_str()!=null&&
						   		    !"".equals(att_MdEnterWarehouse.getWewId_str().trim())){ 
											 String  intStr=att_MdEnterWarehouse.getWewId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  wewId="+did+"   "); 
													 }else {
													 sbuffer.append("  wewId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 集团公司id(rbaId)
							     if(att_MdEnterWarehouse.getRbaId_str()!=null&&
						   		    !"".equals(att_MdEnterWarehouse.getRbaId_str().trim())){ 
											 String  intStr=att_MdEnterWarehouse.getRbaId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  rbaId="+did+"   "); 
													 }else {
													 sbuffer.append("  rbaId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 牙医医院id(rbsId)
							     if(att_MdEnterWarehouse.getRbsId_str()!=null&&
						   		    !"".equals(att_MdEnterWarehouse.getRbsId_str().trim())){ 
											 String  intStr=att_MdEnterWarehouse.getRbsId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  rbsId="+did+"   "); 
													 }else {
													 sbuffer.append("  rbsId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 牙医门诊id(rbbId)
							     if(att_MdEnterWarehouse.getRbbId_str()!=null&&
						   		    !"".equals(att_MdEnterWarehouse.getRbbId_str().trim())){ 
											 String  intStr=att_MdEnterWarehouse.getRbbId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  rbbId="+did+"   "); 
													 }else {
													 sbuffer.append("  rbbId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
								 		//时间类型开始条件处理  预计到货时间(expectDatetime)
									  if(att_MdEnterWarehouse.getExpectDatetime_start()!=null){
								   	    	sbuffer.append( " and  expectDatetime<='" +att_MdEnterWarehouse.getExpectDatetime_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 预计到货时间(expectDatetime)
									 	if(att_MdEnterWarehouse.getExpectDatetime_end()!=null){
						   	      			sbuffer.append( " and  expectDatetime>'" +att_MdEnterWarehouse.getExpectDatetime_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  收货完成时间(receiptDatetime)
									  if(att_MdEnterWarehouse.getReceiptDatetime_start()!=null){
								   	    	sbuffer.append( " and  receiptDatetime>='" +sdf.format(att_MdEnterWarehouse.getReceiptDatetime_start())+" 00:00:00'" );
										 }
								 	  //时间类型结束条件处理 收货完成时间(receiptDatetime)
									 	if(att_MdEnterWarehouse.getReceiptDatetime_end()!=null){
						   	      			sbuffer.append( " and  receiptDatetime<='" +sdf.format(att_MdEnterWarehouse.getReceiptDatetime_end())+" 23:59:59'" );
								  	     } 
								 		//时间类型开始条件处理  订单日期(orderDatetime)
									  if(att_MdEnterWarehouse.getOrderDatetime_start()!=null){
								   	    	sbuffer.append( " and  orderDatetime<='" +att_MdEnterWarehouse.getOrderDatetime_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 订单日期(orderDatetime)
									 	if(att_MdEnterWarehouse.getOrderDatetime_end()!=null){
						   	      			sbuffer.append( " and  orderDatetime>'" +att_MdEnterWarehouse.getOrderDatetime_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  参数数值01(paramInt01)
									  if(att_MdEnterWarehouse.getParamInt01_start()!=null){
								   	    	sbuffer.append( " and  paramInt01<='" +att_MdEnterWarehouse.getParamInt01_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 参数数值01(paramInt01)
									 	if(att_MdEnterWarehouse.getParamInt01_end()!=null){
						   	      			sbuffer.append( " and  paramInt01>'" +att_MdEnterWarehouse.getParamInt01_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  参数数值02(paramInt02)
									  if(att_MdEnterWarehouse.getParamInt02_start()!=null){
								   	    	sbuffer.append( " and  paramInt02<='" +att_MdEnterWarehouse.getParamInt02_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 参数数值02(paramInt02)
									 	if(att_MdEnterWarehouse.getParamInt02_end()!=null){
						   	      			sbuffer.append( " and  paramInt02>'" +att_MdEnterWarehouse.getParamInt02_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  参数数值03(paramInt03)
									  if(att_MdEnterWarehouse.getParamInt03_start()!=null){
								   	    	sbuffer.append( " and  paramInt03<='" +att_MdEnterWarehouse.getParamInt03_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 参数数值03(paramInt03)
									 	if(att_MdEnterWarehouse.getParamInt03_end()!=null){
						   	      			sbuffer.append( " and  paramInt03>'" +att_MdEnterWarehouse.getParamInt03_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  参数数值04(paramInt04)
									  if(att_MdEnterWarehouse.getParamInt04_start()!=null){
								   	    	sbuffer.append( " and  paramInt04<='" +att_MdEnterWarehouse.getParamInt04_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 参数数值04(paramInt04)
									 	if(att_MdEnterWarehouse.getParamInt04_end()!=null){
						   	      			sbuffer.append( " and  paramInt04>'" +att_MdEnterWarehouse.getParamInt04_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  参数数值05(paramInt05)
									  if(att_MdEnterWarehouse.getParamInt05_start()!=null){
								   	    	sbuffer.append( " and  paramInt05<='" +att_MdEnterWarehouse.getParamInt05_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 参数数值05(paramInt05)
									 	if(att_MdEnterWarehouse.getParamInt05_end()!=null){
						   	      			sbuffer.append( " and  paramInt05>'" +att_MdEnterWarehouse.getParamInt05_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  创建时间(createDate)
									  if(att_MdEnterWarehouse.getCreateDate_start()!=null){
								   	    	sbuffer.append( " and  createDate<='" +att_MdEnterWarehouse.getCreateDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 创建时间(createDate)
									 	if(att_MdEnterWarehouse.getCreateDate_end()!=null){
						   	      			sbuffer.append( " and  createDate>'" +att_MdEnterWarehouse.getCreateDate_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  修改时间(editDate)
									  if(att_MdEnterWarehouse.getEditDate_start()!=null){
								   	    	sbuffer.append( " and  editDate<='" +att_MdEnterWarehouse.getEditDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 修改时间(editDate)
									 	if(att_MdEnterWarehouse.getEditDate_end()!=null){
						   	      			sbuffer.append( " and  editDate>'" +att_MdEnterWarehouse.getEditDate_end()+"'" );  
								  	     } 
			 //*****************无关字段处理end*****************
			 //*****************数据库字段处理begin*************
								  		//入库单信息表id(wewId)
					 					if(att_MdEnterWarehouse.getWewId()!=null){
											 sbuffer.append( " and wewId=" +att_MdEnterWarehouse.getWewId() );
										 }
								  		//集团公司id(rbaId)
					 					if(att_MdEnterWarehouse.getRbaId()!=null){
											 sbuffer.append( " and rbaId=" +att_MdEnterWarehouse.getRbaId() );
										 }
								  		//牙医医院id(rbsId)
					 					if(att_MdEnterWarehouse.getRbsId()!=null){
											 sbuffer.append( " and rbsId=" +att_MdEnterWarehouse.getRbsId() );
										 }
								  		//牙医门诊id(rbbId)
					 					if(att_MdEnterWarehouse.getRbbId()!=null){
											 sbuffer.append( " and rbbId=" +att_MdEnterWarehouse.getRbbId() );
										 }
					 					if(att_MdEnterWarehouse.getSuiId()!=null){
											 sbuffer.append( " and suiId=" +att_MdEnterWarehouse.getSuiId() );
										 }
					       				//采购单位类型(purchaseType)		 					 
									 if(att_MdEnterWarehouse.getPurchaseType()!=null&&
									  !"".equals(att_MdEnterWarehouse.getPurchaseType().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("purchaseType")!=-1){
											  sbuffer.append( " and purchaseType  like '%"+att_MdEnterWarehouse.getPurchaseType()+"%'"   );
										  }else {
											  sbuffer.append( " and purchaseType   ='"+att_MdEnterWarehouse.getPurchaseType()+"'"   );
											  
										  }
									 }
									// Restrictions.like(’username’,'mp3′).ignoreCase()
									// Restrictions.like(propertyName, value);
					       				//单据号(billcode)		 					 
									 if(att_MdEnterWarehouse.getBillcode()!=null&&
									  !"".equals(att_MdEnterWarehouse.getBillcode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("billcode")!=-1){
											  /**
											   * 2019-12-05
											   * yanglei
											   * 更改添加模糊查询小写后台转换为大写
											   */
											  sbuffer.append( " and billcode  like '%"+att_MdEnterWarehouse.getBillcode().toUpperCase()+"%'"   );
										  }else {
											  sbuffer.append( " and billcode   ='"+att_MdEnterWarehouse.getBillcode()+"'"   );
										  }
									 }
					       				//关联单据号(relationBillcode)		 					 
									 if(att_MdEnterWarehouse.getRelationBillCode()!=null&&
									  !"".equals(att_MdEnterWarehouse.getRelationBillCode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("relationBillCode")!=-1){
											  sbuffer.append( " and relationBillCode  like '%"+att_MdEnterWarehouse.getRelationBillCode().toUpperCase()+"%'"   );
										  }else {
											  sbuffer.append( " and relationBillCode   ='"+att_MdEnterWarehouse.getRelationBillCode()+"'"   );
										  }
									 }
					       				//录入方式(inputMode)		 					 
									 if(att_MdEnterWarehouse.getInputMode()!=null&&
									  !"".equals(att_MdEnterWarehouse.getInputMode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("inputMode")!=-1){
											  sbuffer.append( " and inputMode  like '%"+att_MdEnterWarehouse.getInputMode()+"%'"   );
										  }else {
											  sbuffer.append( " and inputMode   ='"+att_MdEnterWarehouse.getInputMode()+"'"   );
										  }
									 }
					       				//货主(owner)		 					 
									 if(att_MdEnterWarehouse.getOwner()!=null&&
									  !"".equals(att_MdEnterWarehouse.getOwner().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("owner")!=-1){
											  sbuffer.append( " and owner  like '%"+att_MdEnterWarehouse.getOwner()+"%'"   );
										  }else {
											  sbuffer.append( " and owner   ='"+att_MdEnterWarehouse.getOwner()+"'"   );
										  }
									 }
					       				//单据类型(billType)		 					 
									 if(att_MdEnterWarehouse.getBillType()!=null&&
									  !"".equals(att_MdEnterWarehouse.getBillType().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("billType")!=-1){
											  sbuffer.append( " and billType  like '%"+att_MdEnterWarehouse.getBillType()+"%'"   );
										  }else {
											  sbuffer.append( " and billType   ='"+att_MdEnterWarehouse.getBillType()+"'"   );
										  }
									 }
					       				//收货人(consignee)		 					 
									 if(att_MdEnterWarehouse.getConsignee()!=null&&
									  !"".equals(att_MdEnterWarehouse.getConsignee().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("consignee")!=-1){
											  sbuffer.append( " and consignee  like '%"+att_MdEnterWarehouse.getConsignee()+"%'"   );
										  }else {
											  sbuffer.append( " and consignee   ='"+att_MdEnterWarehouse.getConsignee()+"'"   );
										  }
									 }
						   				//预计到货时间(expectDatetime)
						 				if(att_MdEnterWarehouse.getExpectDatetime()!=null){
						   	                   sbuffer.append( " and  expectDatetime='" +att_MdEnterWarehouse.getExpectDatetime()+"'" );  
								  		}
						   				//收货完成时间(receiptDatetime)
						 				if(att_MdEnterWarehouse.getReceiptDatetime()!=null){
						   	                   sbuffer.append( " and  receiptDatetime='" +att_MdEnterWarehouse.getReceiptDatetime()+"'" );  
								  		}
					       				//联系人(contacts)		 					 
									 if(att_MdEnterWarehouse.getContacts()!=null&&
									  !"".equals(att_MdEnterWarehouse.getContacts().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("contacts")!=-1){
											  sbuffer.append( " and contacts  like '%"+att_MdEnterWarehouse.getContacts()+"%'"   );
										  }else {
											  sbuffer.append( " and contacts   ='"+att_MdEnterWarehouse.getContacts()+"'"   );
										  }
									 }
					       				//联系电话(telephone)		 					 
									 if(att_MdEnterWarehouse.getTelephone()!=null&&
									  !"".equals(att_MdEnterWarehouse.getTelephone().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("telephone")!=-1){
											  sbuffer.append( " and telephone  like '%"+att_MdEnterWarehouse.getTelephone()+"%'"   );
										  }else {
											  sbuffer.append( " and telephone   ='"+att_MdEnterWarehouse.getTelephone()+"'"   );
										  }
									 }
					       				//联系地址(address)		 					 
									 if(att_MdEnterWarehouse.getAddress()!=null&&
									  !"".equals(att_MdEnterWarehouse.getAddress().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("address")!=-1){
											  sbuffer.append( " and address  like '%"+att_MdEnterWarehouse.getAddress()+"%'"   );
										  }else {
											  sbuffer.append( " and address   ='"+att_MdEnterWarehouse.getAddress()+"'"   );
										  }
									 }
					       				//供应商编号(supplierCode)		 					 
									 if(att_MdEnterWarehouse.getSupplierCode()!=null&&
									  !"".equals(att_MdEnterWarehouse.getSupplierCode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("supplierCode")!=-1){
											  sbuffer.append( " and supplierCode  like '%"+att_MdEnterWarehouse.getSupplierCode()+"%'"   );
										  }else {
											  sbuffer.append( " and supplierCode   ='"+att_MdEnterWarehouse.getSupplierCode()+"'"   );
										  }
									 }
					       				//供应商名称(supplierName)		 					 
									 if(att_MdEnterWarehouse.getSupplierName()!=null&&
									  !"".equals(att_MdEnterWarehouse.getSupplierName().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("supplierName")!=-1){
											  sbuffer.append( " and supplierName  like '%"+att_MdEnterWarehouse.getSupplierName()+"%'"   );
										  }else {
											  sbuffer.append( " and supplierName   ='"+att_MdEnterWarehouse.getSupplierName()+"'"   );
										  }
									 }
					       				//到货通知单(alertCode)		 					 
									 if(att_MdEnterWarehouse.getAlertCode()!=null&&
									  !"".equals(att_MdEnterWarehouse.getAlertCode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("alertCode")!=-1){
											  sbuffer.append( " and alertCode  like '%"+att_MdEnterWarehouse.getAlertCode()+"%'"   );
										  }else {
											  sbuffer.append( " and alertCode   ='"+att_MdEnterWarehouse.getAlertCode()+"'"   );
										  }
									 }
					       				//asn类别(asnType)		 					 
									 if(att_MdEnterWarehouse.getAsnType()!=null&&
									  !"".equals(att_MdEnterWarehouse.getAsnType().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("asnType")!=-1){
											  sbuffer.append( " and asnType  like '%"+att_MdEnterWarehouse.getAsnType()+"%'"   );
										  }else {
											  sbuffer.append( " and asnType   ='"+att_MdEnterWarehouse.getAsnType()+"'"   );
										  }
									 }
						   				//订单日期(orderDatetime)
						 				if(att_MdEnterWarehouse.getOrderDatetime()!=null){
						   	                   sbuffer.append( " and  orderDatetime='" +att_MdEnterWarehouse.getOrderDatetime()+"'" );  
								  		}
					       				//状态(state)		 					 
									 if(att_MdEnterWarehouse.getState()!=null&&
									  !"".equals(att_MdEnterWarehouse.getState().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("state")!=-1){
											  sbuffer.append( " and state  like '%"+att_MdEnterWarehouse.getState()+"%'"   );
										  }else {
											  sbuffer.append( " and state   ='"+att_MdEnterWarehouse.getState()+"'"   );
										  }
									 }
					       				//发货人(consignor)		 					 
									 if(att_MdEnterWarehouse.getConsignor()!=null&&
									  !"".equals(att_MdEnterWarehouse.getConsignor().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("consignor")!=-1){
											  sbuffer.append( " and consignor  like '%"+att_MdEnterWarehouse.getConsignor()+"%'"   );
										  }else {
											  sbuffer.append( " and consignor   ='"+att_MdEnterWarehouse.getConsignor()+"'"   );
										  }
									 }
					       				//发货地址(consignorAddress)		 					 
									 if(att_MdEnterWarehouse.getConsignorAddress()!=null&&
									  !"".equals(att_MdEnterWarehouse.getConsignorAddress().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("consignorAddress")!=-1){
											  sbuffer.append( " and consignorAddress  like '%"+att_MdEnterWarehouse.getConsignorAddress()+"%'"   );
										  }else {
											  sbuffer.append( " and consignorAddress   ='"+att_MdEnterWarehouse.getConsignorAddress()+"'"   );
										  }
									 }
					       				//参数值10(paramStr10)		 					 
									 if(att_MdEnterWarehouse.getParamStr10()!=null&&
									  !"".equals(att_MdEnterWarehouse.getParamStr10().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("paramStr10")!=-1){
											  sbuffer.append( " and paramStr10  like '%"+att_MdEnterWarehouse.getParamStr10()+"%'"   );
										  }else {
											  sbuffer.append( " and paramStr10   ='"+att_MdEnterWarehouse.getParamStr10()+"'"   );
										  }
									 }
					       				//参数值02(paramStr02)		 					 
									 if(att_MdEnterWarehouse.getParamStr02()!=null&&
									  !"".equals(att_MdEnterWarehouse.getParamStr02().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("paramStr02")!=-1){
											  sbuffer.append( " and paramStr02  like '%"+att_MdEnterWarehouse.getParamStr02()+"%'"   );
										  }else {
											  sbuffer.append( " and paramStr02   ='"+att_MdEnterWarehouse.getParamStr02()+"'"   );
										  }
									 }
					       				//参数值01(paramStr01)		 					 
									 if(att_MdEnterWarehouse.getParamStr01()!=null&&
									  !"".equals(att_MdEnterWarehouse.getParamStr01().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("paramStr01")!=-1){
											  sbuffer.append( " and paramStr01  like '%"+att_MdEnterWarehouse.getParamStr01()+"%'"   );
										  }else {
											  sbuffer.append( " and paramStr01   ='"+att_MdEnterWarehouse.getParamStr01()+"'"   );
										  }
									 }
					       				//参数值03(paramStr03)		 					 
									 if(att_MdEnterWarehouse.getParamStr03()!=null&&
									  !"".equals(att_MdEnterWarehouse.getParamStr03().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("paramStr03")!=-1){
											  sbuffer.append( " and paramStr03  like '%"+att_MdEnterWarehouse.getParamStr03()+"%'"   );
										  }else {
											  sbuffer.append( " and paramStr03   ='"+att_MdEnterWarehouse.getParamStr03()+"'"   );
										  }
									 }
					       				//参数值07(paramStr04)		 					 
									 if(att_MdEnterWarehouse.getParamStr04()!=null&&
									  !"".equals(att_MdEnterWarehouse.getParamStr04().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("paramStr04")!=-1){
											  sbuffer.append( " and paramStr04  like '%"+att_MdEnterWarehouse.getParamStr04()+"%'"   );
										  }else {
											  sbuffer.append( " and paramStr04   ='"+att_MdEnterWarehouse.getParamStr04()+"'"   );
										  }
									 }
					       				//参数值05(paramStr05)		 					 
									 if(att_MdEnterWarehouse.getParamStr05()!=null&&
									  !"".equals(att_MdEnterWarehouse.getParamStr05().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("paramStr05")!=-1){
											  sbuffer.append( " and paramStr05  like '%"+att_MdEnterWarehouse.getParamStr05()+"%'"   );
										  }else {
											  sbuffer.append( " and paramStr05   ='"+att_MdEnterWarehouse.getParamStr05()+"'"   );
										  }
									 }
					       				//参数值06(paramStr06)		 					 
									 if(att_MdEnterWarehouse.getParamStr06()!=null&&
									  !"".equals(att_MdEnterWarehouse.getParamStr06().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("paramStr06")!=-1){
											  sbuffer.append( " and paramStr06  like '%"+att_MdEnterWarehouse.getParamStr06()+"%'"   );
										  }else {
											  sbuffer.append( " and paramStr06   ='"+att_MdEnterWarehouse.getParamStr06()+"'"   );
										  }
									 }
					       				//参数值07(paramStr07)		 					 
									 if(att_MdEnterWarehouse.getParamStr07()!=null&&
									  !"".equals(att_MdEnterWarehouse.getParamStr07().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("paramStr07")!=-1){
											  sbuffer.append( " and paramStr07  like '%"+att_MdEnterWarehouse.getParamStr07()+"%'"   );
										  }else {
											  sbuffer.append( " and paramStr07   ='"+att_MdEnterWarehouse.getParamStr07()+"'"   );
										  }
									 }
					       				//参数值08(paramStr08)		 					 
									 if(att_MdEnterWarehouse.getParamStr08()!=null&&
									  !"".equals(att_MdEnterWarehouse.getParamStr08().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("paramStr08")!=-1){
											  sbuffer.append( " and paramStr08  like '%"+att_MdEnterWarehouse.getParamStr08()+"%'"   );
										  }else {
											  sbuffer.append( " and paramStr08   ='"+att_MdEnterWarehouse.getParamStr08()+"'"   );
										  }
									 }
					       				//参数值09(paramStr09)		 					 
									 if(att_MdEnterWarehouse.getParamStr09()!=null&&
									  !"".equals(att_MdEnterWarehouse.getParamStr09().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("paramStr09")!=-1){
											  sbuffer.append( " and paramStr09  like '%"+att_MdEnterWarehouse.getParamStr09()+"%'"   );
										  }else {
											  sbuffer.append( " and paramStr09   ='"+att_MdEnterWarehouse.getParamStr09()+"'"   );
										  }
									 }
						   				//参数数值01(paramInt01)
						 				if(att_MdEnterWarehouse.getParamInt01()!=null){
						   	                   sbuffer.append( " and  paramInt01='" +att_MdEnterWarehouse.getParamInt01()+"'" );  
								  		}
						   				//参数数值02(paramInt02)
						 				if(att_MdEnterWarehouse.getParamInt02()!=null){
						   	                   sbuffer.append( " and  paramInt02='" +att_MdEnterWarehouse.getParamInt02()+"'" );  
								  		}
						   				//参数数值03(paramInt03)
						 				if(att_MdEnterWarehouse.getParamInt03()!=null){
						   	                   sbuffer.append( " and  paramInt03='" +att_MdEnterWarehouse.getParamInt03()+"'" );  
								  		}
						   				//参数数值04(paramInt04)
						 				if(att_MdEnterWarehouse.getParamInt04()!=null){
						   	                   sbuffer.append( " and  paramInt04='" +att_MdEnterWarehouse.getParamInt04()+"'" );  
								  		}
						   				//参数数值05(paramInt05)
						 				if(att_MdEnterWarehouse.getParamInt05()!=null){
						   	                   sbuffer.append( " and  paramInt05='" +att_MdEnterWarehouse.getParamInt05()+"'" );  
								  		}
					       				//创建人(createRen)		 					 
									 if(att_MdEnterWarehouse.getCreateRen()!=null&&
									  !"".equals(att_MdEnterWarehouse.getCreateRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("createRen")!=-1){
											  sbuffer.append( " and createRen  like '%"+att_MdEnterWarehouse.getCreateRen()+"%'"   );
										  }else {
											  sbuffer.append( " and createRen   ='"+att_MdEnterWarehouse.getCreateRen()+"'"   );
										  }
									 }
						   				//创建时间(createDate)
						 				if(att_MdEnterWarehouse.getCreateDate()!=null){
						   	                   sbuffer.append( " and  createDate='" +att_MdEnterWarehouse.getCreateDate()+"'" );  
								  		}
					       				//修改人(editRen)		 					 
									 if(att_MdEnterWarehouse.getEditRen()!=null&&
									  !"".equals(att_MdEnterWarehouse.getEditRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("editRen")!=-1){
											  sbuffer.append( " and editRen  like '%"+att_MdEnterWarehouse.getEditRen()+"%'"   );
										  }else {
											  sbuffer.append( " and editRen   ='"+att_MdEnterWarehouse.getEditRen()+"'"   );
										  }
									 }
						   				//修改时间(editDate)
						 				if(att_MdEnterWarehouse.getEditDate()!=null){
						   	                   sbuffer.append( " and  editDate='" +att_MdEnterWarehouse.getEditDate()+"'" );  
								  		}
			 //*****************数据库字段处理end***************
			  		if(orderStr!=null&&!"".equals(orderStr.trim())){
									 sbuffer.append( " order by "+orderStr);
						 }
						 /*
						 else {
							  sbuffer.append( " order by  WewId   desc " );
					      }
					      */
			 
			 return  sbuffer.toString();
	}
}