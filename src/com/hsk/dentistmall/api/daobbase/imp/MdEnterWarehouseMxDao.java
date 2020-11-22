package com.hsk.dentistmall.api.daobbase.imp;

import java.text.SimpleDateFormat;
import java.util.*;

import com.hsk.supper.dto.comm.PagerModel;

import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_enter_warehouse_mx表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:54
 */
@Component
public class  MdEnterWarehouseMxDao extends SupperDao implements IMdEnterWarehouseMxDao {	

	/**
	 * 根据md_enter_warehouse_mx表主键查找MdEnterWarehouseMx对象记录
	 * 
	 * @param  WewMxId  Integer类型(md_enter_warehouse_mx表主键)
	 * @return MdEnterWarehouseMx md_enter_warehouse_mx表记录
	 * @throws HSKDBException
	 */	
	public MdEnterWarehouseMx findMdEnterWarehouseMxById(Integer WewMxId) throws HSKDBException{
				MdEnterWarehouseMx  att_MdEnterWarehouseMx=new MdEnterWarehouseMx();				
				if(WewMxId!=null){
					att_MdEnterWarehouseMx.setWewMxId(WewMxId);	
				    Object obj=	this.getOne(att_MdEnterWarehouseMx);
					if(obj!=null){
						att_MdEnterWarehouseMx=(MdEnterWarehouseMx) obj;
					}
				}
				return  att_MdEnterWarehouseMx;
	}
	 /**
	  * 根据md_enter_warehouse_mx表主键删除MdEnterWarehouseMx对象记录
	  * @param  WewMxId  Integer类型(md_enter_warehouse_mx表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdEnterWarehouseMxById(Integer WewMxId) throws HSKDBException{ 
		 MdEnterWarehouseMx  att_MdEnterWarehouseMx=new MdEnterWarehouseMx();	
		  if(WewMxId!=null){
					  att_MdEnterWarehouseMx.setWewMxId(WewMxId);
				  	  Object obj=	this.getOne(att_MdEnterWarehouseMx);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_enter_warehouse_mx表主键修改MdEnterWarehouseMx对象记录
     * @param  WewMxId  Integer类型(md_enter_warehouse_mx表主键)
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
     * @return MdEnterWarehouseMx  修改后的MdEnterWarehouseMx对象记录
	 * @throws HSKDBException
	 */
	public  MdEnterWarehouseMx updateMdEnterWarehouseMxById(Integer WewMxId,MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException{
		  if(WewMxId!=null){
					att_MdEnterWarehouseMx.setWewMxId(WewMxId);
				   	Object obj=	this.getOne(att_MdEnterWarehouseMx);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdEnterWarehouseMx;
	}
	
	/**
	 * 新增md_enter_warehouse_mx表 记录
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdEnterWarehouseMx(MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException{
		 return this.newObject(att_MdEnterWarehouseMx);
	} 
		
	/**
	 * 新增或修改md_enter_warehouse_mx表记录 ,如果md_enter_warehouse_mx表主键MdEnterWarehouseMx.WewMxId为空就是添加，如果非空就是修改
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
	 * @throws HSKDBException
	 */
	public  MdEnterWarehouseMx saveOrUpdateMdEnterWarehouseMx(MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException{
		  this.getHibernateTemplate().saveOrUpdate(att_MdEnterWarehouseMx);
		  return att_MdEnterWarehouseMx;
	}
	
	/**
	 *根据MdEnterWarehouseMx对象作为对(md_enter_warehouse_mx表进行查询，获取列表对象
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
     * @return List<MdEnterWarehouseMx>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdEnterwarehousemxMaterielEntity> getListByMdEnterWarehouseMx(MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException{
		String Hql=this.getHqlEWareMat( att_MdEnterWarehouseMx);
		List<MdEnterwarehousemxMaterielEntity> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}

	@Override
	public PagerModel getMdEnterwarehousemxMaterielEntityByMdEnterWarehouseMx(MdEnterWarehouseMx att_mdEnterWarehouseMx) throws HSKDBException {
		String Hql=this.getHqlEWareMat( att_mdEnterWarehouseMx);
		return this.getHibernateDao().findByPage(Hql);
	}

	/**
	 *根据MdEnterWarehouseMx对象作为对(md_enter_warehouse_mx表进行查询，获取分页对象
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
     * @return List<MdEnterWarehouseMx>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdEnterWarehouseMx(MdEnterWarehouseMx att_MdEnterWarehouseMx)
			throws HSKDBException {
		String Hql=this.getHql(att_MdEnterWarehouseMx, null);
		return this.getHibernateDao().findByPage(Hql); 
	}

	/**
	 * 查询物料入库明细表与物料表的信息
	 * @param att_MdEnterWarehouseMx
	 * @return
	 */
	private String getHqlEWareMat(MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException{
		StringBuffer sbuffer = new StringBuffer( " from  MdEnterwarehousemxMaterielEntity  where  1=1  ");
		String sql = getHql(att_MdEnterWarehouseMx, sbuffer);
		return sql;
	}
	/**
	 * 根据MdEnterWarehouseMx对象获取Hql字符串
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
	 *  @return hql字符串
	 */
	private String  getHql(MdEnterWarehouseMx att_MdEnterWarehouseMx, StringBuffer sbuffer){
			 if(sbuffer == null){
			 	sbuffer = new StringBuffer( " from  MdEnterWarehouseMx  where  1=1  ");
			 }
			 String likeStr=  att_MdEnterWarehouseMx.getTab_like();
			 String orderStr= att_MdEnterWarehouseMx.getTab_order();
			 
			 //*****************无关字段处理begin*****************
						   		 //处理in条件 入库单明细表id(wewMxId)
							     if(att_MdEnterWarehouseMx.getWewMxId_str()!=null&&
						   		    !"".equals(att_MdEnterWarehouseMx.getWewMxId_str().trim())){ 
											 String  intStr=att_MdEnterWarehouseMx.getWewMxId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  wewMxId="+did+"   "); 
													 }else {
													 sbuffer.append("  wewMxId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 入库单信息表id(wewId)
							     if(att_MdEnterWarehouseMx.getWewId_str()!=null&&
						   		    !"".equals(att_MdEnterWarehouseMx.getWewId_str().trim())){ 
											 String  intStr=att_MdEnterWarehouseMx.getWewId_str().trim();
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
						   		 //处理in条件 物料信息表id(wmsMiId)
							     if(att_MdEnterWarehouseMx.getWmsMiId_str()!=null&&
						   		    !"".equals(att_MdEnterWarehouseMx.getWmsMiId_str().trim())){ 
											 String  intStr=att_MdEnterWarehouseMx.getWmsMiId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  wmsMiId="+did+"   "); 
													 }else {
													 sbuffer.append("  wmsMiId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 行号(lineNumber)
							     if(att_MdEnterWarehouseMx.getLineNumber_str()!=null&&
						   		    !"".equals(att_MdEnterWarehouseMx.getLineNumber_str().trim())){ 
											 String  intStr=att_MdEnterWarehouseMx.getLineNumber_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  lineNumber="+did+"   "); 
													 }else {
													 sbuffer.append("  lineNumber="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
								 		//时间类型开始条件处理  下单时间(placeOrderTime)
									  if(att_MdEnterWarehouseMx.getPlaceOrderTime_start()!=null){
								   	    	sbuffer.append( " and  placeOrderTime<='" +att_MdEnterWarehouseMx.getPlaceOrderTime_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 下单时间(placeOrderTime)
									 	if(att_MdEnterWarehouseMx.getPlaceOrderTime_end()!=null){
						   	      			sbuffer.append( " and  placeOrderTime>'" +att_MdEnterWarehouseMx.getPlaceOrderTime_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  收货完成时间(receiptDatetime)
									  if(att_MdEnterWarehouseMx.getReceiptDatetime_start()!=null){
								   	    	sbuffer.append( " and  receiptDatetime<='" +att_MdEnterWarehouseMx.getReceiptDatetime_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 收货完成时间(receiptDatetime)
									 	if(att_MdEnterWarehouseMx.getReceiptDatetime_end()!=null){
						   	      			sbuffer.append( " and  receiptDatetime>'" +att_MdEnterWarehouseMx.getReceiptDatetime_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  创建时间(createDate)
									  if(att_MdEnterWarehouseMx.getCreateDate_start()!=null){
								   	    	sbuffer.append( " and  createDate<='" +att_MdEnterWarehouseMx.getCreateDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 创建时间(createDate)
									 	if(att_MdEnterWarehouseMx.getCreateDate_end()!=null){
						   	      			sbuffer.append( " and  createDate>'" +att_MdEnterWarehouseMx.getCreateDate_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  修改时间(editDate)
									  if(att_MdEnterWarehouseMx.getEditDate_start()!=null){
								   	    	sbuffer.append( " and  editDate<='" +att_MdEnterWarehouseMx.getEditDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 修改时间(editDate)
									 	if(att_MdEnterWarehouseMx.getEditDate_end()!=null){
						   	      			sbuffer.append( " and  editDate>'" +att_MdEnterWarehouseMx.getEditDate_end()+"'" );  
								  	     } 
			 //*****************无关字段处理end*****************
			 //*****************数据库字段处理begin*************
								  		//入库单明细表id(wewMxId)
					 					if(att_MdEnterWarehouseMx.getWewMxId()!=null){
											 sbuffer.append( " and wewMxId=" +att_MdEnterWarehouseMx.getWewMxId() );
										 }
								  		//入库单信息表id(wewId)
					 					if(att_MdEnterWarehouseMx.getWewId()!=null){
											 sbuffer.append( " and wewId=" +att_MdEnterWarehouseMx.getWewId() );
										 }
								  		//物料信息表id(wmsMiId)
					 					if(att_MdEnterWarehouseMx.getWmsMiId()!=null){
											 sbuffer.append( " and wmsMiId=" +att_MdEnterWarehouseMx.getWmsMiId() );
										 }
					 					//规格id(mmfId)
					 					if(att_MdEnterWarehouseMx.getMmfId()!=null){
											 sbuffer.append( " and mmfId=" +att_MdEnterWarehouseMx.getMmfId() );
										 }
					       				//批次属性(itemKeyId)		 					 
									 if(att_MdEnterWarehouseMx.getItemKeyId()!=null&&
									  !"".equals(att_MdEnterWarehouseMx.getItemKeyId().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("itemKeyId")!=-1){
											  sbuffer.append( " and itemKeyId  like '%"+att_MdEnterWarehouseMx.getItemKeyId()+"%'"   );
										  }else {
											  sbuffer.append( " and itemKeyId   ='"+att_MdEnterWarehouseMx.getItemKeyId()+"'"   );
										  }
									 }
								  		//行号(lineNumber)
					 					if(att_MdEnterWarehouseMx.getLineNumber()!=null){
											 sbuffer.append( " and lineNumber=" +att_MdEnterWarehouseMx.getLineNumber() );
										 }
						   				//下单时间(placeOrderTime)
						 				if(att_MdEnterWarehouseMx.getPlaceOrderTime()!=null){
						   	                   sbuffer.append( " and  placeOrderTime='" +att_MdEnterWarehouseMx.getPlaceOrderTime()+"'" );  
								  		}
					       				//采购商名称(purchaseUnit)		 					 
									 if(att_MdEnterWarehouseMx.getPurchaseUnit()!=null&&
									  !"".equals(att_MdEnterWarehouseMx.getPurchaseUnit().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("purchaseUnit")!=-1){
											  sbuffer.append( " and purchaseUnit  like '%"+att_MdEnterWarehouseMx.getPurchaseUnit()+"%'"   );
										  }else {
											  sbuffer.append( " and purchaseUnit   ='"+att_MdEnterWarehouseMx.getPurchaseUnit()+"'"   );
										  }
									 }
					       				//商品编码(matCode)		 					 
									 if(att_MdEnterWarehouseMx.getMatCode()!=null&&
									  !"".equals(att_MdEnterWarehouseMx.getMatCode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matCode")!=-1){
											  sbuffer.append( " and matCode  like '%"+att_MdEnterWarehouseMx.getMatCode()+"%'"   );
										  }else {
											  sbuffer.append( " and matCode   ='"+att_MdEnterWarehouseMx.getMatCode()+"'"   );
										  }
									 }
					       				//商品名称(matName)		 					 
									 if(att_MdEnterWarehouseMx.getMatName()!=null&&
									  !"".equals(att_MdEnterWarehouseMx.getMatName().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matName")!=-1){
											  sbuffer.append( " and matName  like '%"+att_MdEnterWarehouseMx.getMatName()+"%'"   );
										  }else {
											  sbuffer.append( " and matName   ='"+att_MdEnterWarehouseMx.getMatName()+"'"   );
										  }
									 }
					       				//基本单位(basicUnit)		 					 
									 if(att_MdEnterWarehouseMx.getBasicUnit()!=null&&
									  !"".equals(att_MdEnterWarehouseMx.getBasicUnit().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("basicUnit")!=-1){
											  sbuffer.append( " and basicUnit  like '%"+att_MdEnterWarehouseMx.getBasicUnit()+"%'"   );
										  }else {
											  sbuffer.append( " and basicUnit   ='"+att_MdEnterWarehouseMx.getBasicUnit()+"'"   );
										  }
									 }
					       				//规格(norm)		 					 
									 if(att_MdEnterWarehouseMx.getNorm()!=null&&
									  !"".equals(att_MdEnterWarehouseMx.getNorm().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("norm")!=-1){
											  sbuffer.append( " and norm  like '%"+att_MdEnterWarehouseMx.getNorm()+"%'"   );
										  }else {
											  sbuffer.append( " and norm   ='"+att_MdEnterWarehouseMx.getNorm()+"'"   );
										  }
									 }
					       				//物料类别(matType)		 					 
									 if(att_MdEnterWarehouseMx.getMatType()!=null&&
									  !"".equals(att_MdEnterWarehouseMx.getMatType().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matType")!=-1){
											  sbuffer.append( " and matType  like '%"+att_MdEnterWarehouseMx.getMatType()+"%'"   );
										  }else {
											  sbuffer.append( " and matType   ='"+att_MdEnterWarehouseMx.getMatType()+"'"   );
										  }
									 }
					       				//物料类别(matType1)		 					 
									 if(att_MdEnterWarehouseMx.getMatType1()!=null&&
									  !"".equals(att_MdEnterWarehouseMx.getMatType1().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matType1")!=-1){
											  sbuffer.append( " and matType1  like '%"+att_MdEnterWarehouseMx.getMatType1()+"%'"   );
										  }else {
											  sbuffer.append( " and matType1   ='"+att_MdEnterWarehouseMx.getMatType1()+"'"   );
										  }
									 }
					       				//拣选分类(matType2)		 					 
									 if(att_MdEnterWarehouseMx.getMatType2()!=null&&
									  !"".equals(att_MdEnterWarehouseMx.getMatType2().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matType2")!=-1){
											  sbuffer.append( " and matType2  like '%"+att_MdEnterWarehouseMx.getMatType2()+"%'"   );
										  }else {
											  sbuffer.append( " and matType2   ='"+att_MdEnterWarehouseMx.getMatType2()+"'"   );
										  }
									 }
					       				//码托分类(matType3)		 					 
									 if(att_MdEnterWarehouseMx.getMatType3()!=null&&
									  !"".equals(att_MdEnterWarehouseMx.getMatType3().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matType3")!=-1){
											  sbuffer.append( " and matType3  like '%"+att_MdEnterWarehouseMx.getMatType3()+"%'"   );
										  }else {
											  sbuffer.append( " and matType3   ='"+att_MdEnterWarehouseMx.getMatType3()+"'"   );
										  }
									 }
						   				//收货完成时间(receiptDatetime)
						 				if(att_MdEnterWarehouseMx.getReceiptDatetime()!=null){
						   	                   sbuffer.append( " and  receiptDatetime='" +att_MdEnterWarehouseMx.getReceiptDatetime()+"'" );  
								  		}
					       				//描述(describes)		 					 
									 if(att_MdEnterWarehouseMx.getDescribes()!=null&&
									  !"".equals(att_MdEnterWarehouseMx.getDescribes().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("describes")!=-1){
											  sbuffer.append( " and describes  like '%"+att_MdEnterWarehouseMx.getDescribes()+"%'"   );
										  }else {
											  sbuffer.append( " and describes   ='"+att_MdEnterWarehouseMx.getDescribes()+"'"   );
										  }
									 }
					       				//创建人(createRen)		 					 
									 if(att_MdEnterWarehouseMx.getCreateRen()!=null&&
									  !"".equals(att_MdEnterWarehouseMx.getCreateRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("createRen")!=-1){
											  sbuffer.append( " and createRen  like '%"+att_MdEnterWarehouseMx.getCreateRen()+"%'"   );
										  }else {
											  sbuffer.append( " and createRen   ='"+att_MdEnterWarehouseMx.getCreateRen()+"'"   );
										  }
									 }
						   				//创建时间(createDate)
						 				if(att_MdEnterWarehouseMx.getCreateDate()!=null){
						   	                   sbuffer.append( " and  createDate='" +att_MdEnterWarehouseMx.getCreateDate()+"'" );  
								  		}
					       				//修改人(editRen)		 					 
									 if(att_MdEnterWarehouseMx.getEditRen()!=null&&
									  !"".equals(att_MdEnterWarehouseMx.getEditRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("editRen")!=-1){
											  sbuffer.append( " and editRen  like '%"+att_MdEnterWarehouseMx.getEditRen()+"%'"   );
										  }else {
											  sbuffer.append( " and editRen   ='"+att_MdEnterWarehouseMx.getEditRen()+"'"   );
										  }
									 }
						   				//修改时间(editDate)
						 				if(att_MdEnterWarehouseMx.getEditDate()!=null){
				 		   	                   sbuffer.append( " and  editDate='" +att_MdEnterWarehouseMx.getEditDate()+"'" );  
								  		}
			 //*****************数据库字段处理end***************
			  		if(orderStr!=null&&!"".equals(orderStr.trim())){
									 sbuffer.append( " order by "+orderStr);
						 }
						 /*
						 else {
							  sbuffer.append( " order by  WewMxId   desc " );
					      }
					      */
			 
			 return  sbuffer.toString();
	}
	@Override
	public PagerModel getPagerMdEnterWarehouseMxBymmfId(MdEnterWarehouse att_MdEnterWarehouse,MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException {
		String sql = "SELECT DATE_FORMAT(a.receipt_datetime,'%Y-%m-%d %H:%i:%s') as receipt_datetime,a.input_mode,a.consignor,a.consignee,"
					+"DATE_FORMAT(a.order_datetime,'%Y-%m-%d %H:%i:%s') as order_datetime,b.price,b.mat_name,b.mat_number,b.norm,a.supplier_name FROM md_enter_warehouse a,md_enter_warehouse_mx b where a.wew_id=b.wew_id";
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getRbaId() != null)
			sql += " and rba_id="+att_MdEnterWarehouse.getRbaId();
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getRbbId() != null)
			sql += " and rbb_id="+att_MdEnterWarehouse.getRbbId();
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getRbsId() != null)
			sql += " and rbs_id="+att_MdEnterWarehouse.getRbsId();
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getPurchaseType() != null && !"".equals(att_MdEnterWarehouse.getPurchaseType().trim()))
			sql += " and purchase_type="+att_MdEnterWarehouse.getPurchaseType().trim();
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getConsignee() != null && !"".equals(att_MdEnterWarehouse.getConsignee().trim()))
			sql += " and consignee like '%"+att_MdEnterWarehouse.getConsignee().trim()+"%'";
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getConsignor() != null && !"".equals(att_MdEnterWarehouse.getConsignor().trim()))
			sql += " and consignor like '%"+att_MdEnterWarehouse.getConsignor().trim()+"%'";
		if(att_MdEnterWarehouseMx != null && att_MdEnterWarehouseMx.getMmfId() != null)
			sql += " and mmf_id="+att_MdEnterWarehouseMx.getMmfId();
		if(att_MdEnterWarehouseMx != null && att_MdEnterWarehouseMx.getMmfId_str() != null && !"".equals(att_MdEnterWarehouseMx.getMmfId_str().trim()) )
			sql += " and mmf_id in ("+att_MdEnterWarehouseMx.getMmfId_str().trim()+")";
		sql += " order by receipt_datetime desc";
		return this.getJdbcDao().findByPage(sql);
	}
	@Override
	public List<Map<Object, Object>> getMxListByEnter(
			MdEnterWarehouse att_MdEnterWarehouse,
			MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "SELECT a.*,b.*,case when f.mmt_name is not null then"
				+" CONCAT(f.mmt_name,'/',e.mmt_name,'/',d.mmt_name) else c.label_info end AS 'type_name'"
				+" FROM md_enter_warehouse a,md_enter_warehouse_mx b ,md_materiel_info c "
				+" LEFT JOIN md_materiel_type d ON c.md_wms_mi_id=d.mmt_id"
				+" LEFT JOIN md_materiel_type e ON c.mat_type2=e.mmt_id"
				+" LEFT JOIN md_materiel_type f ON c.mat_type1=f.mmt_id"
			+" WHERE a.wew_id=b.wew_id AND b.wms_mi_id=c.wms_mi_id";
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getRbaId() != null)
			sql += " and a.rba_id="+att_MdEnterWarehouse.getRbaId();
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getRbbId() != null)
			sql += " and a.rbb_id="+att_MdEnterWarehouse.getRbbId();
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getRbsId() != null)
			sql += " and a.rbs_id="+att_MdEnterWarehouse.getRbsId();
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getPurchaseType() != null && !"".equals(att_MdEnterWarehouse.getPurchaseType().trim()))
			sql += " and a.purchase_type="+att_MdEnterWarehouse.getPurchaseType().trim();
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getBillType() != null && !"".equals(att_MdEnterWarehouse.getBillType().trim()))
			sql += " and a.bill_type="+att_MdEnterWarehouse.getBillType().trim();
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getConsignee() != null && !"".equals(att_MdEnterWarehouse.getConsignee().trim()))
			sql += " and a.consignee like '%"+att_MdEnterWarehouse.getConsignee().trim()+"%'";
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getConsignor() != null && !"".equals(att_MdEnterWarehouse.getConsignor().trim()))
			sql += " and a.consignor like '%"+att_MdEnterWarehouse.getConsignor().trim()+"%'";
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getBillcode() != null && !"".equals(att_MdEnterWarehouse.getBillcode().trim()))
			sql += " and a.billcode like '%"+att_MdEnterWarehouse.getBillcode().trim()+"%'";
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getRelationBillCode() != null && !"".equals(att_MdEnterWarehouse.getRelationBillCode().trim()))
			sql += " and a.relationBillCode like '%"+att_MdEnterWarehouse.getRelationBillCode().trim()+"%'";
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getReceiptDatetime_start()!=null)
			sql += " and a.receipt_datetime >='"+sdf.format(att_MdEnterWarehouse.getReceiptDatetime_start())+" 00:00:00'";
		if(att_MdEnterWarehouse != null && att_MdEnterWarehouse.getReceiptDatetime_end()!=null)
			sql += " and a.receipt_datetime <='"+sdf.format(att_MdEnterWarehouse.getReceiptDatetime_end())+" 23:59:59'";
		sql += " and a.state='1'";
		sql += " order by a.receipt_datetime";
		return this.getJdbcDao().query(sql);
	}

	@Override
	public List<MdInventoryExtend> getInventoryListByWewMxId(MdEnterWarehouseMx mdEnterWarehouseMx) throws HSKDBException {
		String Hql= "from MdInventoryExtend where 1=1";
		if (mdEnterWarehouseMx.getWewMxId() != null) {
			Hql += " and wewMxId = " + mdEnterWarehouseMx.getWewMxId();
		}
//		if (mdEnterWarehouseMx.getWewId() != null) {
//			Hql += " and wewMxId in (select wewMxId from MdEnterWarehouseMx where wewId = " + mdEnterWarehouseMx.getWewId() + ")";
//		}
		List<MdInventoryExtend> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}

	@Override
	public List<MdInventoryExtend> getInventoryListByWewId(MdEnterWarehouseMx mdEnterWarehouseMx) throws HSKDBException {
		String Hql= "from MdInventoryExtend where 1=1";
//		if (mdEnterWarehouseMx.getWewMxId() != null) {
//			Hql += " and wewMxId = " + mdEnterWarehouseMx.getWewMxId();
//		}
		if (mdEnterWarehouseMx.getWewId() != null) {
			Hql += " and wewMxId in (select wewMxId from MdEnterWarehouseMx where wewId = " + mdEnterWarehouseMx.getWewId() + ")";
		}
		List<MdInventoryExtend> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}

	@Override
	public Double getEnterMxExpectNumber(MdEnterWarehouse mdEnterWarehouse) throws HSKDBException {
		String sql = "select sum(mat_number) as expectNumber from md_enter_warehouse_mx where 1=1";
		if (mdEnterWarehouse.getWewId() != null) {
			sql += " and wew_id = " + mdEnterWarehouse.getWewId();
		}
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list == null || list.isEmpty()) {
			return 0d;
		}
		Map<String, Object> map = list.get(0);
		if (map == null || map.isEmpty()) {
			return 0d;
		}
		return Double.parseDouble(map.get("expectNumber").toString());
	}

	@Override
	public Double getEnterMxRetailMoeny(MdEnterWarehouse mdEnterWarehouse) throws HSKDBException {
		String sql = "select sum(retail_money) as retail_money from md_enter_warehouse_mx where 1=1";
		if (mdEnterWarehouse.getWewId() != null) {
			sql += " and wew_id = " + mdEnterWarehouse.getWewId();
		}
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list == null || list.isEmpty()) {
			return 0d;
		}
		Map<String, Object> map = list.get(0);
		if (map == null || map.isEmpty()) {
			return 0d;
		}
		return Double.parseDouble(map.get("retail_money").toString());
	}

	@Override
	public Double getEnterMxPurchasePeice(MdEnterWarehouse mdEnterWarehouse) throws HSKDBException {
		String sql = "select sum(price) as price from md_enter_warehouse_mx where 1=1";
		if (mdEnterWarehouse.getWewId() != null) {
			sql += " and wew_id = " + mdEnterWarehouse.getWewId();
		}
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list == null || list.isEmpty()) {
			return 0d;
		}
		Map<String, Object> map = list.get(0);
		if (map == null || map.isEmpty()) {
			return 0d;
		}
		return Double.parseDouble(map.get("price").toString());
	}
}