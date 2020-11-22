package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.text.SimpleDateFormat;
import java.util.*;

import com.hsk.supper.dto.comm.PagerModel;

import org.drools.lang.DRLParser.date_effective_key_return;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_order_mx表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-28 17:16:59
 */
@Component
public class MdOrderMxApiDao extends SupperDao implements IMdOrderMxApiDao {

	/**
	 * 根据md_order_mx表主键查找MdOrderMx对象记录
	 * 
	 * @param  MomId  Integer类型(md_order_mx表主键)
	 * @return MdOrderMx md_order_mx表记录
	 * @throws HSKDBException
	 */	
	public MdOrderMx findMdOrderMxById(Integer MomId) throws HSKDBException{
				MdOrderMx  att_MdOrderMx=new MdOrderMx();				
				if(MomId!=null){
					att_MdOrderMx.setMomId(MomId);	
				    Object obj=	this.getOne(att_MdOrderMx);
					if(obj!=null){
						att_MdOrderMx=(MdOrderMx) obj;
					}
				}
				return  att_MdOrderMx;
	}
	 /**
	  * 根据md_order_mx表主键删除MdOrderMx对象记录
	  * @param  MomId  Integer类型(md_order_mx表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdOrderMxById(Integer MomId) throws HSKDBException{ 
		 MdOrderMx  att_MdOrderMx=new MdOrderMx();	
		  if(MomId!=null){
					  att_MdOrderMx.setMomId(MomId);
				  	  Object obj=	this.getOne(att_MdOrderMx);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_order_mx表主键修改MdOrderMx对象记录
     * @param  MomId  Integer类型(md_order_mx表主键)
     * @param  att_MdOrderMx  MdOrderMx类型(md_order_mx表记录)
     * @return MdOrderMx  修改后的MdOrderMx对象记录
	 * @throws HSKDBException
	 */
	public  MdOrderMx updateMdOrderMxById(Integer MomId,MdOrderMx att_MdOrderMx) throws HSKDBException{
		  if(MomId!=null){
					att_MdOrderMx.setMomId(MomId);
				   	Object obj=	this.getOne(att_MdOrderMx);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdOrderMx;
	}
	
	/**
	 * 新增md_order_mx表 记录
     * @param  att_MdOrderMx  MdOrderMx类型(md_order_mx表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdOrderMx(MdOrderMx att_MdOrderMx) throws HSKDBException{
		 return this.newObject(att_MdOrderMx);
	} 
		
	/**
	 * 新增或修改md_order_mx表记录 ,如果md_order_mx表主键MdOrderMx.MomId为空就是添加，如果非空就是修改
     * @param  att_MdOrderMx  MdOrderMx类型(md_order_mx表记录)
	 * @throws HSKDBException
	 */
	public  MdOrderMx saveOrUpdateMdOrderMx(MdOrderMx att_MdOrderMx) throws HSKDBException{
		  this.getHibernateTemplate().saveOrUpdate(att_MdOrderMx);
		  return att_MdOrderMx;
	}
	
	/**
	 *根据MdOrderMx对象作为对(md_order_mx表进行查询，获取列表对象
     * @param  att_MdOrderMx  MdOrderMx类型(md_order_mx表记录)
     * @return List<MdOrderMx>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdOrderMx> getListByMdOrderMx(MdOrderMx att_MdOrderMx) throws HSKDBException{
		String Hql=this.getHql(att_MdOrderMx); 
		List<MdOrderMx> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	/**
	 *根据MdOrderMx对象作为对(md_order_mx表进行查询，获取分页对象
     * @param  att_MdOrderMx  MdOrderMx类型(md_order_mx表记录)
     * @return List<MdOrderMx>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdOrderMx(MdOrderMx att_MdOrderMx)
			throws HSKDBException {
		String Hql=this.getHql(att_MdOrderMx);
		return this.getHibernateDao().findByPage(Hql); 
	} 
	/**
	 * 根据MdOrderMx对象获取Hql字符串
     * @param  att_MdOrderMx  MdOrderMx类型(md_order_mx表记录)
	 *  @return hql字符串
	 */
	private String  getHql(MdOrderMx att_MdOrderMx){
			/* StringBuffer sbuffer = new StringBuffer( " from  MdOrderMx  where  1=1  ");*/
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		StringBuffer sbuffer = new StringBuffer( " from  MdOrderMx  where  1=1  ");
			 String likeStr=  att_MdOrderMx.getTab_like();
			 String orderStr= att_MdOrderMx.getTab_order();
			 
			 //*****************无关字段处理begin*****************
						   		 //处理in条件 订单明细id(momId)
							     if(att_MdOrderMx.getMomId_str()!=null&&
						   		    !"".equals(att_MdOrderMx.getMomId_str().trim())){ 
											 String  intStr=att_MdOrderMx.getMomId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  momId="+did+"   "); 
													 }else {
													 sbuffer.append("  momId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 订单信息id(moiId)
							     if(att_MdOrderMx.getMoiId_str()!=null&&
						   		    !"".equals(att_MdOrderMx.getMoiId_str().trim())){ 
											 String  intStr=att_MdOrderMx.getMoiId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  moiId="+did+"   "); 
													 }else {
													 sbuffer.append("  moiId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 物料信息表id(wmsMiId)
							     if(att_MdOrderMx.getWmsMiId_str()!=null&&
						   		    !"".equals(att_MdOrderMx.getWmsMiId_str().trim())){ 
											 String  intStr=att_MdOrderMx.getWmsMiId_str().trim();
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
								 		//时间类型开始条件处理  下单时间(placeOrderTime)
									  if(att_MdOrderMx.getPlaceOrderTime_start()!=null){
								   	    	sbuffer.append( " and  placeOrderTime<='" +sdf.format(att_MdOrderMx.getPlaceOrderTime_start())+"'" );  
										 }
								 	  //时间类型结束条件处理 下单时间(placeOrderTime)
									 	if(att_MdOrderMx.getPlaceOrderTime_end()!=null){
						   	      			sbuffer.append( " and  placeOrderTime>'" +sdf.format(att_MdOrderMx.getPlaceOrderTime_end())+"'" );  
								  	     } 
						   		 //处理in条件 数量1(number1)
							     if(att_MdOrderMx.getNumber1_str()!=null&&
						   		    !"".equals(att_MdOrderMx.getNumber1_str().trim())){ 
											 String  intStr=att_MdOrderMx.getNumber1_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  number1="+did+"   "); 
													 }else {
													 sbuffer.append("  number1="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 数量1(number2)
							     if(att_MdOrderMx.getNumber2_str()!=null&&
						   		    !"".equals(att_MdOrderMx.getNumber2_str().trim())){ 
											 String  intStr=att_MdOrderMx.getNumber2_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  number2="+did+"   "); 
													 }else {
													 sbuffer.append("  number2="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 数量1(number3)
							     if(att_MdOrderMx.getNumber3_str()!=null&&
						   		    !"".equals(att_MdOrderMx.getNumber3_str().trim())){ 
											 String  intStr=att_MdOrderMx.getNumber3_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  number3="+did+"   "); 
													 }else {
													 sbuffer.append("  number3="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 数量1(number4)
							     if(att_MdOrderMx.getNumber4_str()!=null&&
						   		    !"".equals(att_MdOrderMx.getNumber4_str().trim())){ 
											 String  intStr=att_MdOrderMx.getNumber4_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  number4="+did+"   "); 
													 }else {
													 sbuffer.append("  number4="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 数量1(number5)
							     if(att_MdOrderMx.getNumber5_str()!=null&&
						   		    !"".equals(att_MdOrderMx.getNumber5_str().trim())){ 
											 String  intStr=att_MdOrderMx.getNumber5_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  number5="+did+"   "); 
													 }else {
													 sbuffer.append("  number5="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
								 		//时间类型开始条件处理  创建时间(createDate)
									  if(att_MdOrderMx.getCreateDate_start()!=null){
								   	    	sbuffer.append( " and  createDate<='" +att_MdOrderMx.getCreateDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 创建时间(createDate)
									 	if(att_MdOrderMx.getCreateDate_end()!=null){
						   	      			sbuffer.append( " and  createDate>'" +att_MdOrderMx.getCreateDate_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  修改时间(editDate)
									  if(att_MdOrderMx.getEditDate_start()!=null){
								   	    	sbuffer.append( " and  editDate<='" +att_MdOrderMx.getEditDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 修改时间(editDate)
									 	if(att_MdOrderMx.getEditDate_end()!=null){
						   	      			sbuffer.append( " and  editDate>'" +att_MdOrderMx.getEditDate_end()+"'" );  
								  	     } 
			 //*****************无关字段处理end*****************
			 //*****************数据库字段处理begin*************
								  		//订单明细id(momId)
					 					if(att_MdOrderMx.getMomId()!=null){
											 sbuffer.append( " and momId=" +att_MdOrderMx.getMomId() );
										 }
								  		//订单信息id(moiId)
					 					if(att_MdOrderMx.getMoiId()!=null){
											 sbuffer.append( " and moiId=" +att_MdOrderMx.getMoiId() );
										 }
								  		//物料信息表id(wmsMiId)
					 					if(att_MdOrderMx.getWmsMiId()!=null){
											 sbuffer.append( " and wmsMiId=" +att_MdOrderMx.getWmsMiId() );
										 }
					 					//规格表id(mmfId)
					 					if(att_MdOrderMx.getMmfId()!=null){
											 sbuffer.append( " and mmfId=" +att_MdOrderMx.getMmfId() );
										 }
						   				//下单时间(placeOrderTime)
						 				if(att_MdOrderMx.getPlaceOrderTime()!=null){
						   	                   sbuffer.append( " and  placeOrderTime='" +att_MdOrderMx.getPlaceOrderTime()+"'" );  
								  		}
					       				//采购商名称(purchaseUnit)		 					 
									 if(att_MdOrderMx.getPurchaseUnit()!=null&&
									  !"".equals(att_MdOrderMx.getPurchaseUnit().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("purchaseUnit")!=-1){
											  sbuffer.append( " and purchaseUnit  like '%"+att_MdOrderMx.getPurchaseUnit()+"%'"   );
										  }else {
											  sbuffer.append( " and purchaseUnit   ='"+att_MdOrderMx.getPurchaseUnit()+"'"   );
										  }
									 }
					       				//商品编码(matCode)		 					 
									 if(att_MdOrderMx.getMatCode()!=null&&
									  !"".equals(att_MdOrderMx.getMatCode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matCode")!=-1){
											  sbuffer.append( " and matCode  like '%"+att_MdOrderMx.getMatCode().toUpperCase()+"%'"   );
										  }else {
											  sbuffer.append( " and matCode   ='"+att_MdOrderMx.getMatCode()+"'"   );
										  }
									 }
					       				//商品名称(matName)		 					 
									 if(att_MdOrderMx.getMatName()!=null&&
									  !"".equals(att_MdOrderMx.getMatName().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matName")!=-1){
											  sbuffer.append( " and matName  like '%"+att_MdOrderMx.getMatName()+"%'"   );
										  }else {
											  sbuffer.append( " and matName   ='"+att_MdOrderMx.getMatName()+"'"   );
										  }
									 }
					       				//基本单位(basicUnit)		 					 
									 if(att_MdOrderMx.getBasicUnit()!=null&&
									  !"".equals(att_MdOrderMx.getBasicUnit().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("basicUnit")!=-1){
											  sbuffer.append( " and basicUnit  like '%"+att_MdOrderMx.getBasicUnit()+"%'"   );
										  }else {
											  sbuffer.append( " and basicUnit   ='"+att_MdOrderMx.getBasicUnit()+"'"   );
										  }
									 }
					       				//规格(norm)		 					 
									 if(att_MdOrderMx.getNorm()!=null&&
									  !"".equals(att_MdOrderMx.getNorm().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("norm")!=-1){
											  sbuffer.append( " and norm  like '%"+att_MdOrderMx.getNorm()+"%'"   );
										  }else {
											  sbuffer.append( " and norm   ='"+att_MdOrderMx.getNorm()+"'"   );
										  }
									 }
					       				//物料类别(matType)		 					 
									 if(att_MdOrderMx.getMatType()!=null&&
									  !"".equals(att_MdOrderMx.getMatType().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matType")!=-1){
											  sbuffer.append( " and matType  like '%"+att_MdOrderMx.getMatType()+"%'"   );
										  }else {
											  sbuffer.append( " and matType   ='"+att_MdOrderMx.getMatType()+"'"   );
										  }
									 }
					       				//物料类别(matType1)		 					 
									 if(att_MdOrderMx.getMatType1()!=null&&
									  !"".equals(att_MdOrderMx.getMatType1().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matType1")!=-1){
											  sbuffer.append( " and matType1  like '%"+att_MdOrderMx.getMatType1()+"%'"   );
										  }else {
											  sbuffer.append( " and matType1   ='"+att_MdOrderMx.getMatType1()+"'"   );
										  }
									 }
					       				//拣选分类(matType2)		 					 
									 if(att_MdOrderMx.getMatType2()!=null&&
									  !"".equals(att_MdOrderMx.getMatType2().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matType2")!=-1){
											  sbuffer.append( " and matType2  like '%"+att_MdOrderMx.getMatType2()+"%'"   );
										  }else {
											  sbuffer.append( " and matType2   ='"+att_MdOrderMx.getMatType2()+"'"   );
										  }
									 }
					       				//码托分类(matType3)		 					 
									 if(att_MdOrderMx.getMatType3()!=null&&
									  !"".equals(att_MdOrderMx.getMatType3().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matType3")!=-1){
											  sbuffer.append( " and matType3  like '%"+att_MdOrderMx.getMatType3()+"%'"   );
										  }else {
											  sbuffer.append( " and matType3   ='"+att_MdOrderMx.getMatType3()+"'"   );
										  }
									 }
								  		//数量1(number1)
					 					if(att_MdOrderMx.getNumber1()!=null){
											 sbuffer.append( " and number1=" +att_MdOrderMx.getNumber1() );
										 }
								  		//数量1(number2)
					 					if(att_MdOrderMx.getNumber2()!=null){
											 sbuffer.append( " and number2=" +att_MdOrderMx.getNumber2() );
										 }
								  		//数量1(number3)
					 					if(att_MdOrderMx.getNumber3()!=null){
											 sbuffer.append( " and number3=" +att_MdOrderMx.getNumber3() );
										 }
								  		//数量1(number4)
					 					if(att_MdOrderMx.getNumber4()!=null){
											 sbuffer.append( " and number4=" +att_MdOrderMx.getNumber4() );
										 }
								  		//数量1(number5)
					 					if(att_MdOrderMx.getNumber5()!=null){
											 sbuffer.append( " and number5=" +att_MdOrderMx.getNumber5() );
										 }
					       				//描述(describes)		 					 
									 if(att_MdOrderMx.getDescribes()!=null&&
									  !"".equals(att_MdOrderMx.getDescribes().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("describes")!=-1){
											  sbuffer.append( " and describes  like '%"+att_MdOrderMx.getDescribes()+"%'"   );
										  }else {
											  sbuffer.append( " and describes   ='"+att_MdOrderMx.getDescribes()+"'"   );
										  }
									 }
					       				//创建人(createRen)		 					 
									 if(att_MdOrderMx.getCreateRen()!=null&&
									  !"".equals(att_MdOrderMx.getCreateRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("createRen")!=-1){
											  sbuffer.append( " and createRen  like '%"+att_MdOrderMx.getCreateRen()+"%'"   );
										  }else {
											  sbuffer.append( " and createRen   ='"+att_MdOrderMx.getCreateRen()+"'"   );
										  }
									 }
						   				//创建时间(createDate)
						 				if(att_MdOrderMx.getCreateDate()!=null){
						   	                   sbuffer.append( " and  createDate='" +att_MdOrderMx.getCreateDate()+"'" );  
								  		}
					       				//修改人(editRen)		 					 
									 if(att_MdOrderMx.getEditRen()!=null&&
									  !"".equals(att_MdOrderMx.getEditRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("editRen")!=-1){
											  sbuffer.append( " and editRen  like '%"+att_MdOrderMx.getEditRen()+"%'"   );
										  }else {
											  sbuffer.append( " and editRen   ='"+att_MdOrderMx.getEditRen()+"'"   );
										  }
									 }
						   				//修改时间(editDate)
						 				if(att_MdOrderMx.getEditDate()!=null){
						   	                   sbuffer.append( " and  editDate='" +att_MdOrderMx.getEditDate()+"'" );  
								  		}
			 //*****************数据库字段处理end***************
			  		if(orderStr!=null&&!"".equals(orderStr.trim())){
									 sbuffer.append( " order by "+orderStr);
						 }
						 /*
						 else {
							  sbuffer.append( " order by  MomId   desc " );
					      }
					      */
			 
			 return  sbuffer.toString();
	}
	@Override
	public List<Map<String, Object>> getMxListByMoiIds(String moiIds)
			throws HSKDBException {
		String sql = null;
		sql = "SELECT a.*," +
				"(SELECT f.mas_id FROM md_order_after_sale_mx f WHERE f.mom_id = a.mom_id ORDER BY mas_mx_id desc limit 1) AS mas_id," +
				"(SELECT f.after_sale FROM md_order_after_sale_mx f WHERE f.mom_id = a.mom_id ORDER BY mas_mx_id desc limit 1) AS after_sale," +
				" d.mmf_code,c.root_path AS 'less_file_path' FROM md_order_mx a left join md_materiel_format d on a.mmf_id = d.mmf_id" +
//				" LEFT JOIN md_order_after_sale_mx e on e.mom_id = a.mom_id "// and (e.after_sale != 6 and e.after_sale != 4)"
				" ,md_materiel_info b "
				+ "LEFT JOIN sys_file_info c ON b.lessen_filecode=c.file_code "
				+ "WHERE a.wms_mi_id=b.wms_mi_id ";
		sql += " and moi_id in (" + moiIds + ") order by a.mom_id";
		return this.getJdbcDao().query(sql);

	}
	@Override
	public List<Map<Object, Object>> getMxListByOrder(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx,String moiIds)throws HSKDBException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql="SELECT a.*,b.*,c.product_name,CONCAT(f.mmt_name,'/',e.mmt_name,'/',d.mmt_name) AS 'type_name' FROM md_order_info a,md_order_mx b,md_materiel_info c "
				+" LEFT JOIN md_materiel_type d ON c.md_wms_mi_id=d.mmt_id"
				+" LEFT JOIN md_materiel_type e ON c.mat_type2=e.mmt_id"
				+" LEFT JOIN md_materiel_type f ON c.mat_type1=f.mmt_id"
				+" WHERE a.moi_id=b.moi_id AND b.wms_mi_id=c.wms_mi_id";
		if(att_MdOrderInfo!=null && att_MdOrderInfo.getWzId()!= null)
			sql += " and a.wz_id="+att_MdOrderInfo.getWzId();
		if(att_MdOrderInfo!=null && att_MdOrderInfo.getPurchaseType()!= null && !att_MdOrderInfo.getPurchaseType().equals(""))
			sql += " and a.purchase_type="+att_MdOrderInfo.getPurchaseType();
		if(att_MdOrderInfo!=null && att_MdOrderInfo.getRbaId()!= null)
			sql += " and a.rba_id="+att_MdOrderInfo.getRbaId();
		if(att_MdOrderInfo!=null && att_MdOrderInfo.getRbbId()!= null)
			sql += " and a.rbb_id="+att_MdOrderInfo.getRbbId();
		if(att_MdOrderInfo!=null && att_MdOrderInfo.getRbsId()!= null)
			sql += " and a.rbs_id="+att_MdOrderInfo.getRbsId();
		if(att_MdOrderInfo!=null && att_MdOrderInfo.getPurchaseId()!= null)
			sql += " and a.purchase_id="+att_MdOrderInfo.getPurchaseId();
		if(att_MdOrderInfo != null && att_MdOrderInfo.getOrderCode()!=null && !att_MdOrderInfo.getOrderCode().trim().equals(""))
			sql += " and a.order_code like '%"+att_MdOrderInfo.getOrderCode().trim()+"%'";
		if(att_MdOrderInfo != null && att_MdOrderInfo.getApplicantName()!=null && !att_MdOrderInfo.getApplicantName().trim().equals(""))
			sql += " and a.applicant_name like '%"+att_MdOrderInfo.getApplicantName().trim()+"%'";
		if(att_MdOrderInfo != null && att_MdOrderInfo.getPurchaseUnit()!=null && !att_MdOrderInfo.getPurchaseUnit().trim().equals(""))
			sql += " and a.purchase_unit like '%"+att_MdOrderInfo.getPurchaseUnit().trim()+"%'";
		if(att_MdOrderInfo != null && att_MdOrderInfo.getProcessStatus()!=null && !att_MdOrderInfo.getProcessStatus().trim().equals(""))
			sql += " and a.process_status like '%"+att_MdOrderInfo.getProcessStatus().trim()+"%'";
		if(att_MdOrderInfo != null && att_MdOrderInfo.getProcessStatus_str()!=null && !att_MdOrderInfo.getProcessStatus_str().trim().equals(""))
			sql += " and a.process_status in ("+att_MdOrderInfo.getProcessStatus_str().trim()+")";
		if(att_MdOrderMx != null && att_MdOrderMx.getMatName()!=null && !att_MdOrderMx.getMatName().trim().equals(""))
			sql += " and b.mat_name like '%"+att_MdOrderMx.getMatName().trim()+"%'";
		if(att_MdOrderInfo != null && att_MdOrderInfo.getPlaceOrderTime_start()!=null)
			sql += " and a.place_order_time >='"+sdf.format(att_MdOrderInfo.getPlaceOrderTime_start())+" 00:00:00'";
		if(att_MdOrderInfo != null && att_MdOrderInfo.getPlaceOrderTime_end()!=null)
			sql += " and a.place_order_time <='"+sdf.format(att_MdOrderInfo.getPlaceOrderTime_end())+" 23:59:59'";
		if (moiIds!=null) {
			sql +=" and a.moi_id in("+moiIds+")";
		}
		sql += " order by a.place_order_time desc";
		return this.getJdbcDao().query(sql);
	}
	@Override
	public List<Map<Object, Object>> getMxListByOrderTwo(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx,String moiId)throws HSKDBException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql="SELECT a.*,b.*,c.product_name,CONCAT(f.mmt_name,'/',e.mmt_name,'/',d.mmt_name) AS 'type_name' FROM md_order_info a,md_order_mx b,md_materiel_info c "
				+" LEFT JOIN md_materiel_type d ON c.md_wms_mi_id=d.mmt_id"
				+" LEFT JOIN md_materiel_type e ON c.mat_type2=e.mmt_id"
				+" LEFT JOIN md_materiel_type f ON c.mat_type1=f.mmt_id"
				+" WHERE a.moi_id=b.moi_id AND b.wms_mi_id=c.wms_mi_id";
		if(att_MdOrderInfo!=null && att_MdOrderInfo.getWzId()!= null)
			sql += " and a.wz_id="+att_MdOrderInfo.getWzId();
		if(att_MdOrderInfo!=null && att_MdOrderInfo.getPurchaseType()!= null && !att_MdOrderInfo.getPurchaseType().equals(""))
			sql += " and a.purchase_type="+att_MdOrderInfo.getPurchaseType();
		if(att_MdOrderInfo!=null && att_MdOrderInfo.getRbaId()!= null)
			sql += " and a.rba_id="+att_MdOrderInfo.getRbaId();
		if(att_MdOrderInfo!=null && att_MdOrderInfo.getRbbId()!= null)
			sql += " and a.rbb_id="+att_MdOrderInfo.getRbbId();
		if(att_MdOrderInfo!=null && att_MdOrderInfo.getRbsId()!= null)
			sql += " and a.rbs_id="+att_MdOrderInfo.getRbsId();
		if(att_MdOrderInfo!=null && att_MdOrderInfo.getPurchaseId()!= null)
			sql += " and a.purchase_id="+att_MdOrderInfo.getPurchaseId();
		if(att_MdOrderInfo != null && att_MdOrderInfo.getOrderCode()!=null && !att_MdOrderInfo.getOrderCode().trim().equals(""))
			sql += " and a.order_code like '%"+att_MdOrderInfo.getOrderCode().trim()+"%'";
		if(att_MdOrderInfo != null && att_MdOrderInfo.getApplicantName()!=null && !att_MdOrderInfo.getApplicantName().trim().equals(""))
			sql += " and a.applicant_name like '%"+att_MdOrderInfo.getApplicantName().trim()+"%'";
		if(att_MdOrderInfo != null && att_MdOrderInfo.getPurchaseUnit()!=null && !att_MdOrderInfo.getPurchaseUnit().trim().equals(""))
			sql += " and a.purchase_unit like '%"+att_MdOrderInfo.getPurchaseUnit().trim()+"%'";
		if(att_MdOrderInfo != null && att_MdOrderInfo.getProcessStatus()!=null && !att_MdOrderInfo.getProcessStatus().trim().equals(""))
			sql += " and a.process_status like '%"+att_MdOrderInfo.getProcessStatus().trim()+"%'";
		if(att_MdOrderInfo != null && att_MdOrderInfo.getProcessStatus_str()!=null && !att_MdOrderInfo.getProcessStatus_str().trim().equals(""))
			sql += " and a.process_status in ("+att_MdOrderInfo.getProcessStatus_str().trim()+")";
		if(att_MdOrderMx != null && att_MdOrderMx.getMatName()!=null && !att_MdOrderMx.getMatName().trim().equals(""))
			sql += " and b.mat_name like '%"+att_MdOrderMx.getMatName().trim()+"%'";
		if(att_MdOrderInfo != null && att_MdOrderInfo.getPlaceOrderTime_start()!=null)
			sql += " and a.place_order_time >='"+sdf.format(att_MdOrderInfo.getPlaceOrderTime_start())+" 00:00:00'";
		if(att_MdOrderInfo != null && att_MdOrderInfo.getPlaceOrderTime_end()!=null)
			sql += " and a.place_order_time <='"+sdf.format(att_MdOrderInfo.getPlaceOrderTime_end())+" 23:59:59'";
		if (moiId!=null) {
			sql+=" and a.moi_id in("+moiId+")";
		}
		sql += " order by a.place_order_time";
		return this.getJdbcDao().query(sql);	
}

	private String getHql(String moiIds, String searchName, String brand, Integer limit, Integer page, String orderName) {
		String sql = "SELECT a.*, /*MAX(e.mas_id) as mas_id, MAX(e.after_sale) as after_sale,*/ d.mmf_code,c.root_path AS 'less_file_path', (SELECT f.param_name from sys_parameter f,sys_parameter g WHERE f.param_value= " +
				"(SELECT f.after_sale FROM md_order_after_sale_mx f WHERE f.mom_id = a.mom_id ORDER BY f.mas_mx_id desc limit 1)" +
//				" max(e.after_sale)" +
				" and f.sys_spar_id=g.spar_id and g.param_code='PAR191230112633225') as after_sale_name," +
				" (select SUM(h.QUANTITY) from md_inventory_extend h where h.wms_mi_id = a.wms_mi_id and h.mmf_id = a.mmf_id) as inventory_count," +
				"(SELECT f.mas_id FROM md_order_after_sale_mx f WHERE f.mom_id = a.mom_id ORDER BY mas_mx_id desc limit 1) AS mas_id," +
				"(SELECT g.after_sale from md_order_after_sale g where mas_id = (SELECT f.mas_id FROM md_order_after_sale_mx f WHERE f.mom_id = a.mom_id ORDER BY mas_mx_id desc limit 1)) AS after_sale_state," +
				"(SELECT f.after_sale FROM md_order_after_sale_mx f WHERE f.mom_id = a.mom_id ORDER BY mas_mx_id desc limit 1) AS after_sale" +
				" FROM md_order_mx a left join md_materiel_format d on a.mmf_id = d.mmf_id" +
//				" LEFT JOIN md_order_after_sale_mx e on e.mom_id = a.mom_id " +
				" LEFT JOIN ( SELECT DISTINCT mom_id, mas_id, after_sale FROM md_order_after_sale_mx" +
				" where mas_id in (select GROUP_CONCAT(h.mas_id) from md_order_after_sale h where h.moi_id in (" + moiIds + "))" +
				" ) AS e ON e.mom_id = a.mom_id" +
//				" and (ISNULL(e.after_sale) != true and e.after_sale != 4)" +
				" ,md_materiel_info b "
				+ "LEFT JOIN sys_file_info c ON b.lessen_filecode=c.file_code "
				+ "WHERE a.wms_mi_id=b.wms_mi_id";
		if (moiIds != null && !moiIds.equals("")) {
			sql += " and a.moi_id in (" + moiIds + ")";
		}
		if(searchName != null && !searchName.trim().equals("")){
			if (brand != null && !brand.equals("")){
				sql += " and (a.mat_name like '%" + searchName.trim().toUpperCase() + "%' or a.wms_mi_id in (select wms_mi_id from md_materiel_info where brand like '%" + searchName.trim() + "%'))";
			}else {
				sql += " and a.mat_name like '%" + searchName.trim().toUpperCase() + "%'";
			}
		}
//				" GROUP BY e.mom_id HAVING mas_id >= mas_id or mas_id is null" +
		if(orderName != null && !orderName.equals("")){
//			IF (ISNULL( e.mas_id ), 0, 1 ),e.mas_id
			sql += " order by  IF (ISNULL(" + orderName + "), 0, 1 ), " + orderName;
		}else {
			sql += " order by a.mom_id";
		}
		if (limit != null && page != null && limit != -1 && page != -1) {
			sql += " limit " + (page - 1) * limit + "," + limit;
		}
		return sql;
	}

	@Override
	public List<Map<String, Object>> getMxListByMoiIdsAndSearch(String moiIds, String searchName, Integer limit, Integer page) throws HSKDBException {
		String sql = getHql(moiIds, searchName, null, 5, 1, null);
		return this.getJdbcDao().query(sql);
	}

	@Override
	public List<Map<String, Object>> getMxListByMoiIdsAndSearch(String moiIds, String searchName, String brand, Integer limit, Integer page) throws HSKDBException {
		String sql = getHql(moiIds, searchName, brand, 5, 1, null);
		return this.getJdbcDao().query(sql);
	}

	@Override
	public List<Map<String, Object>> getMxListByMoiId(Integer moiId) throws HSKDBException {
		String sql = "SELECT mmf_id, mat_number FROM md_order_mx where moi_id ="+moiId+" order by mom_id";
		return this.getJdbcDao().query(sql);
	}

	@Override
	public List<Map<String, Object>> getMxListModelByMoiId(Integer moiId, String searchName, Integer limit, Integer page, String orderName) throws HSKDBException {
//		String sql = "SELECT a.*, /*MAX(e.mas_id) as mas_id, MAX(e.after_sale) as after_sale,*/ d.mmf_code,c.root_path AS 'less_file_path', (SELECT f.param_name from sys_parameter f,sys_parameter g WHERE f.param_value= " +
//				"(SELECT f.after_sale FROM md_order_after_sale_mx f WHERE f.mom_id = a.mom_id ORDER BY f.mas_mx_id desc limit 1)" +
////				" max(e.after_sale)" +
//				" and f.sys_spar_id=g.spar_id and g.param_code='PAR191230112633225') as after_sale_name," +
//				" (select SUM(h.QUANTITY) from md_inventory_extend h where h.wms_mi_id = a.wms_mi_id and h.mmf_id = a.mmf_id) as inventory_count," +
//				"(SELECT f.mas_id FROM md_order_after_sale_mx f WHERE f.mom_id = a.mom_id ORDER BY mas_mx_id desc limit 1) AS mas_id," +
//				"(SELECT f.after_sale FROM md_order_after_sale_mx f WHERE f.mom_id = a.mom_id ORDER BY mas_mx_id desc limit 1) AS after_sale" +
//				" FROM md_order_mx a left join md_materiel_format d on a.mmf_id = d.mmf_id" +
////				" LEFT JOIN md_order_after_sale_mx e on e.mom_id = a.mom_id " +
//				" LEFT JOIN ( SELECT DISTINCT mom_id, mas_id, after_sale FROM md_order_after_sale_mx" +
//				" where mas_id in (select GROUP_CONCAT(h.mas_id) from md_order_after_sale h where h.moi_id = " + moiId + ")" +
//				" ) AS e ON e.mom_id = a.mom_id" +
////				" and (ISNULL(e.after_sale) != true and e.after_sale != 4)" +
//				" ,md_materiel_info b "
//				+ "LEFT JOIN sys_file_info c ON b.lessen_filecode=c.file_code "
//				+ "WHERE a.wms_mi_id=b.wms_mi_id";
//		sql += " and a.moi_id in (" + moiId + ")" +
////				" GROUP BY e.mom_id HAVING mas_id >= mas_id or mas_id is null" +
//				" order by a.mom_id";
//		if (limit != null && page != null) {
//			sql += " limit " + (page - 1) * limit + "," + limit;
//		}
		String sql = getHql(moiId.toString(), searchName, null, limit, page, orderName);;
		return this.getJdbcDao().query(sql);
	}

	@Override
	public List<Map<String, Object>> getMxListModelByMoiIdCount(Integer moiId, String searchName) throws HSKDBException {
		String sql = "SELECT (select money2 from md_order_info where moi_id = " + moiId + ") as express_money," +
				" count(a.mom_id) as total_count, sum(a.total_money) as mx_total_money, " +
				" (select COUNT(e.mas_id) from md_order_after_sale_mx e where e.mom_id = a.mom_id) as as_count" +
				", d.mmf_code,c.root_path AS 'less_file_path'" +
				" FROM md_order_mx a left join md_materiel_format d on a.mmf_id = d.mmf_id" +
//				" left join md_order_after_sale_mx e on e.mom_id = a.mom_id" +//  and (e.after_sale != 4 and e.after_sale != 6)" +
				" ,md_materiel_info b "
				+ "LEFT JOIN sys_file_info c ON b.lessen_filecode=c.file_code "
				+ "WHERE a.wms_mi_id=b.wms_mi_id";
		if (searchName != null && !searchName.equals("")) {
			sql += " and a.mat_name like '%" + searchName + "%' ";
		}
		sql += " and moi_id in ("+moiId+") order by a.mom_id";
		return this.getJdbcDao().query(sql);
	}

	@Override
	public List<Map<String, Object>> getMxListModelByMoiId(Integer moiId, Integer limit, Integer page, String matName, Integer state) throws HSKDBException {
		String sql = "SELECT a.*,/*MAX(e.mas_id), MAX(e.after_sale),*/ d.mmf_code,c.root_path AS 'less_file_path', (SELECT f.param_name from sys_parameter f,sys_parameter g WHERE f.param_value= " +
				"(SELECT f.after_sale FROM md_order_after_sale_mx f WHERE f.mom_id = a.mom_id ORDER BY f.mas_mx_id desc limit 1)" +
//				" max(e.after_sale)" +
				" and f.sys_spar_id=g.spar_id and g.param_code='PAR191230112633225') as after_sale_name," +
				" (select SUM(h.QUANTITY) from md_inventory_extend h where h.wms_mi_id = a.wms_mi_id and h.mmf_id = a.mmf_id) as inventory_count," +
				"(SELECT f.mas_id FROM md_order_after_sale_mx f WHERE f.mom_id = a.mom_id ORDER BY mas_mx_id desc limit 1) AS mas_id," +
				"(SELECT g.after_sale from md_order_after_sale g where mas_id = (SELECT f.mas_id FROM md_order_after_sale_mx f WHERE f.mom_id = a.mom_id ORDER BY mas_mx_id desc limit 1)) AS after_sale_state," +
				"(SELECT f.after_sale FROM md_order_after_sale_mx f WHERE f.mom_id = a.mom_id ORDER BY mas_mx_id desc limit 1) AS after_sale" +" FROM md_order_info i left join" +
				" md_order_mx a on i.moi_id = a.moi_id left join md_materiel_format d on a.mmf_id = d.mmf_id" +
//				" LEFT JOIN md_order_after_sale_mx e on e.mom_id = a.mom_id " +
				" LEFT JOIN ( SELECT DISTINCT mom_id, mas_id, after_sale FROM md_order_after_sale_mx " +
				" where mas_id in (select GROUP_CONCAT(h.mas_id) from md_order_after_sale h where h.moi_id = " + moiId + ")" +
				") AS e ON e.mom_id = a.mom_id" +
//				" and (ISNULL(e.after_sale) != true and e.after_sale != 4)" +
				" ,md_materiel_info b "
				+ "LEFT JOIN sys_file_info c ON b.lessen_filecode=c.file_code "
				+ "WHERE a.wms_mi_id=b.wms_mi_id";
		if(matName != null || !matName.equals("")){
			sql += " and a.mat_name like '%" + matName + "%'";
		}
		if(state != null){
			sql += " and i.Process_status = " + state;
		}
		sql += " and a.moi_id in ("+moiId+") " +
//				"GROUP BY e.mom_id HAVING mas_id >= mas_id or mas_id is null " +
				"order by a.mom_id";
		if(limit != null && page != null) {
			sql += " limit " + (page - 1) * limit + "," + limit;
		}
		return this.getJdbcDao().query(sql);
	}

	@Override
	public List<Map<String, Object>> getMxListByMatName(Integer moiId, String matName, Integer limit, Integer page) throws HSKDBException {
		String sql = "SELECT a.*, d.mmf_code,c.root_path AS 'less_file_path' FROM md_order_mx a left join md_materiel_format d on a.mmf_id = d.mmf_id ,md_materiel_info b "
				+ "LEFT JOIN sys_file_info c ON b.lessen_filecode=c.file_code "
				+ "WHERE a.wms_mi_id=b.wms_mi_id";
		sql += " and moi_id = "+moiId;
		if(matName != null && !matName.equals("")){
			sql += " and (a.mat_name like '%" + matName.trim() + "%' or b.py_name like '%"+matName.toUpperCase()+"%')";
		}
		sql += " order by a.mom_id";
		if(limit != null && page != null){
			sql += " limit " + (page - 1) * limit + "," + limit;
		}
		return this.getJdbcDao().query(sql);
	}

	@Override
	public List<Map<String, Object>> getMxListByMatNameCount(Integer moiId, String matName) throws HSKDBException {
		String sql = "SELECT count(a.mom_id) as total_count, sum(a.total_money) as mx_total_money, d.mmf_code,c.root_path AS 'less_file_path' FROM md_order_mx a left join md_materiel_format d on a.mmf_id = d.mmf_id ,md_materiel_info b "
				+ "LEFT JOIN sys_file_info c ON b.lessen_filecode=c.file_code "
				+ "WHERE a.wms_mi_id=b.wms_mi_id";
		sql += " and moi_id = "+moiId;
		if(matName != null && !matName.equals("")){
			sql += " and ( a.mat_name like '%" + matName.trim() + "%' or b.py_name like '%"+matName.toUpperCase()+"%')";
		}
		sql += " order by a.mom_id";
		return this.getJdbcDao().query(sql);
	}
	/*
	 * 7天时间开始*****start*****
	 */
	public List<Map<String, Object>> getSevenListCountDate0(Date ToDate) throws HSKDBException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sql="SELECT a.moi_id as moiId from md_order_info a where 1=1";
		sql+=" and a.Place_order_time<='"+sdf.format(ToDate)+" 23:59:59'";
		sql+=" and a.Place_order_time>='"+sdf.format(ToDate)+" 00:00:00'";
		return this.getJdbcDao().query(sql);
	}
	public List<Map<String, Object>> getSevenListCountDate1(Date ToDate) throws HSKDBException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sql="SELECT a.moi_id as moiId from md_order_info a where 1=1";
		sql+=" and a.Place_order_time<='"+sdf.format(ToDate)+" 23:59:59'";
		sql+=" and a.Place_order_time>='"+sdf.format(ToDate)+" 00:00:00'";
		return this.getJdbcDao().query(sql);
	}
	public List<Map<String, Object>> getSevenListCountDate2(Date ToDate) throws HSKDBException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sql="SELECT a.moi_id as moiId from md_order_info a where 1=1";
		sql+=" and a.Place_order_time<='"+sdf.format(ToDate)+" 23:59:59'";
		sql+=" and a.Place_order_time>='"+sdf.format(ToDate)+" 00:00:00'";
		return this.getJdbcDao().query(sql);
	}
	public List<Map<String, Object>> getSevenListCountDate3(Date ToDate) throws HSKDBException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sql="SELECT a.moi_id as moiId from md_order_info a where 1=1";
		sql+=" and a.Place_order_time<='"+sdf.format(ToDate)+" 23:59:59'";
		sql+=" and a.Place_order_time>='"+sdf.format(ToDate)+" 00:00:00'";
		return this.getJdbcDao().query(sql);
	}
	public List<Map<String, Object>> getSevenListCountDate4(Date ToDate) throws HSKDBException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sql="SELECT a.moi_id as moiId from md_order_info a where 1=1";
		sql+=" and a.Place_order_time<='"+sdf.format(ToDate)+" 23:59:59'";
		sql+=" and a.Place_order_time>='"+sdf.format(ToDate)+" 00:00:00'";
		return this.getJdbcDao().query(sql);
	}
	public List<Map<String, Object>> getSevenListCountDate5(Date ToDate) throws HSKDBException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sql="SELECT a.moi_id as moiId from md_order_info a where 1=1";
		sql+=" and a.Place_order_time<='"+sdf.format(ToDate)+" 23:59:59'";
		sql+=" and a.Place_order_time>='"+sdf.format(ToDate)+" 00:00:00'";
		return this.getJdbcDao().query(sql);
	}
	public List<Map<String, Object>> getSevenListCountDate6(Date ToDate) throws HSKDBException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sql="SELECT a.moi_id as moiId from md_order_info a where 1=1";
		sql+=" and a.Place_order_time<='"+sdf.format(ToDate)+" 23:59:59'";
		sql+=" and a.Place_order_time>='"+sdf.format(ToDate)+" 00:00:00'";
		return this.getJdbcDao().query(sql);
	}
	/*
	 * 7天时间结束*****end*****
	 */
	@Override
	public List<Map<String, Object>> getSevenListCountTwo(Date Date1, Date Date2) throws HSKDBException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sql="SELECT a.moi_id as moiId,a.rba_id AS rbaId,a.rbs_id AS rbsId,a.rbb_id AS rbbId,a.Place_order_time AS PlaceOrderTimeO,(SELECT mcg.rba_name FROM md_company_group mcg WHERE mcg.rba_id=a.rba_id) AS rbaName,(SELECT mdh.rbs_name FROM md_dentist_hospital mdh WHERE mdh.rbs_id=a.rbs_id) AS rbsName,(SELECT mdc.rbb_name FROM md_dental_clinic mdc WHERE mdc.rbb_id=a.rbb_id) AS rbbName,a.commodity_number AS commodityNumber,a.number2 AS number2O from md_order_info a where 1=1";
		sql+=" and a.Place_order_time<='"+sdf.format(Date2)+" 23:59:59'";
		sql+=" and a.Place_order_time>='"+sdf.format(Date1)+" 00:00:00'";
		sql += " order by a.moi_id";
		return this.getJdbcDao().query(sql);
	}
	@Override
	public List<Map<String, String>> getSevenListCountTwo2(String moiId) throws HSKDBException {
		//String sql = "SELECT a.rba_id AS rbaId,a.rbs_id AS rbsId,a.rbb_id AS rbbId,a.Place_order_time AS PlaceOrderTimeO,a.commodity_number AS commodityNumber,a.number2 AS number2O,b.mat_type AS matType,b.Unit_money AS UnitMoney,b.Total_money AS TotalMoney,(SELECT mt.mmt_name FROM md_materiel_type mt WHERE mt.mmt_path=b.mat_type) AS matName from md_order_info a left join md_order_mx b on a.moi_id=b.moi_id where 1=1";
		String sql="SELECT mx.Unit_money AS UnitMoney, mx.Total_money AS TotalMoney,(SELECT mt.mmt_name FROM md_materiel_type mt WHERE mt.mmt_path=mx.mat_type) AS matTypeName FROM md_order_mx mx WHERE 1=1 ";
		sql+="AND moi_id IN("+moiId+")";

		return this.getJdbcDao().query(sql);
	}
	@Override
	public List<Map<String, String>> getSevenListCountTwo3(String PlaceOrderTimeO) throws HSKDBException {
		String sql="SELECT COUNT(*) as countAll FROM md_order_info WHERE 1 = 1";
		sql+=" AND Place_order_time >='"+PlaceOrderTimeO+" 00:00:00'";
		sql+=" AND Place_order_time <='"+PlaceOrderTimeO+" 23:59:59'";
		return this.getJdbcDao().query(sql);
	}
	//获取产品分类查询0
			public List<Map<String, String>> getListMatType0(String moiId) throws HSKDBException{
				String sql="SELECT COUNT(mx.mat_type) AS matType0  FROM md_order_mx mx LEFT JOIN md_materiel_type mt ON mx.mat_type=mt.mmt_path WHERE 1=1 AND mx.`mat_type` LIKE '/2200%' ";
				sql+="AND moi_id IN("+moiId+")";
				return this.getJdbcDao().query(sql);
			}
		//获取产品分类查询1
		public List<Map<String, String>> getListMatType1(String moiId) throws HSKDBException{
			String sql="SELECT COUNT(mx.mat_type) AS matType1  FROM md_order_mx mx LEFT JOIN md_materiel_type mt ON mx.mat_type=mt.mmt_path WHERE 1=1 AND mx.`mat_type` LIKE '/2201%' ";
			sql+="AND moi_id IN("+moiId+")";
			return this.getJdbcDao().query(sql);
		}
		//获取产品分类2
		public List<Map<String, String>> getListMatType2(String moiId) throws HSKDBException{
			String sql="SELECT COUNT(mx.mat_type) AS matType2  FROM md_order_mx mx LEFT JOIN md_materiel_type mt ON mx.mat_type=mt.mmt_path WHERE 1=1 AND mx.`mat_type` LIKE '/2202%' ";
			sql+="AND moi_id IN("+moiId+")";
			System.out.println("sql+"+sql);
			return this.getJdbcDao().query(sql);
		}
		//获取产品分类3
		public List<Map<String, String>> getListMatType3(String moiId) throws HSKDBException{
			String sql="SELECT COUNT(mx.mat_type) AS matType3  FROM md_order_mx mx LEFT JOIN md_materiel_type mt ON mx.mat_type=mt.mmt_path WHERE 1=1 AND mx.`mat_type` LIKE '/2203%' ";
			sql+="AND moi_id IN("+moiId+")";
			return this.getJdbcDao().query(sql);
		}
		//获取产品分类4
		public List<Map<String, String>> getListMatType4(String moiId) throws HSKDBException{
			String sql="SELECT COUNT(mx.mat_type) AS matType4  FROM md_order_mx mx LEFT JOIN md_materiel_type mt ON mx.mat_type=mt.mmt_path WHERE 1=1 AND mx.`mat_type` LIKE '/2204%' ";
			sql+="AND moi_id IN("+moiId+")";
			return this.getJdbcDao().query(sql);
		}
		//获取产品分类5
		public List<Map<String, String>> getListMatType5(String moiId) throws HSKDBException{
			String sql="SELECT COUNT(mx.mat_type) AS matType5  FROM md_order_mx mx LEFT JOIN md_materiel_type mt ON mx.mat_type=mt.mmt_path WHERE 1=1 AND mx.`mat_type` LIKE '/2205%' ";
			sql+="AND moi_id IN("+moiId+")";
			return this.getJdbcDao().query(sql);
		}
		//获取产品分类6
		public List<Map<String, String>> getListMatType6(String moiId) throws HSKDBException{
			String sql="SELECT COUNT(mx.mat_type) AS matType6  FROM md_order_mx mx LEFT JOIN md_materiel_type mt ON mx.mat_type=mt.mmt_path WHERE 1=1 AND mx.`mat_type` LIKE '/2206%' ";
			sql+="AND moi_id IN("+moiId+")";
			return this.getJdbcDao().query(sql);
		}
		//获取产品分类7
		public List<Map<String, String>> getListMatType7(String moiId) throws HSKDBException{
					String sql="SELECT COUNT(mx.mat_type) AS matType7  FROM md_order_mx mx LEFT JOIN md_materiel_type mt ON mx.mat_type=mt.mmt_path WHERE 1=1 AND mx.`mat_type` LIKE '/2207%' ";
					sql+="AND moi_id IN("+moiId+")";
					return this.getJdbcDao().query(sql);
			}
		//获取产品分类8
		public List<Map<String, String>> getListMatType8(String moiId) throws HSKDBException{
				String sql="SELECT COUNT(mx.mat_type) AS matType8  FROM md_order_mx mx LEFT JOIN md_materiel_type mt ON mx.mat_type=mt.mmt_path WHERE 1=1 AND mx.`mat_type` LIKE '/2208%' ";
				sql+="AND moi_id IN("+moiId+")";
				return this.getJdbcDao().query(sql);
			}
	//总的sql 主要用来导出首页7张表的数据
		public List<Map<String, Object>> getSevenListCountAll(Date Date1, Date Date2) throws HSKDBException{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String sql="SELECT t2.rba_name, t3.`rbs_name`,t4.`rbb_name`, DATE_FORMAT(t1.Place_order_time, '%Y-%m-%d') AS time1, COUNT(t1.order_code) AS OrderNum,(SELECT SUM(t5.mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id) AS matNumber,";
			sql+=" (SELECT SUM(t5.Total_money) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id) AS TotalMoney, ((SELECT SUM(t5.Total_money) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id) / (COUNT(t1.order_code))) AS price,";
			sql+="CONCAT('修复填充类(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2200),'0')),')',CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),  '车针/扩锉/磨头(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2201),'0')),') ',  ";
			sql+="'口腔常用材料(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2202),'0')),')',CHAR(10),CHAR(10),CHAR(10),";
			sql+="'医用耗材(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2203),'0')),')',CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),";
			sql+="'口腔常用器械(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2204),'0')),')',CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),";
			sql+="'正畸产品类(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2205),'0')),')',CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),";
			sql+="'口腔护理(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2206),'0')),')',CHAR(13),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),";
			sql+="'种植产品类(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2207),'0')),')',CHAR(13),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),";
			sql+="'口腔设备(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1=2208),'0')),')', CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),CHAR(10),";
			sql+=" 	'其他类别(',FLOOR(IFNULL((SELECT SUM(mat_number) FROM md_order_mx t5 WHERE t1.moi_id=t5.moi_id AND mat_type1 !=2200 AND mat_type1!=2201 AND mat_type1!=2202 AND mat_type1!=2203 AND mat_type1!=2204 AND mat_type1!=2205 AND mat_type1!=2206 AND mat_type1!=2207 AND mat_type1!=2208),'0')),')'";
			sql+=" ) AS typenum";
			sql+=" FROM md_order_info t1 ";
			sql+=" LEFT JOIN md_company_group t2 ON t1.rba_id=t2.rba_id ";
			sql+=" LEFT JOIN md_dentist_hospital t3 ON t1.rbs_id =t3.`rbs_id` ";
			sql+=" LEFT JOIN md_dental_clinic t4 ON t1.rbb_id =t4.`rbb_id` ";
			sql+=" WHERE t1.Place_order_time<= '"+sdf.format(Date2)+" 23:59:59'";
			sql+=" AND t1.Place_order_time>= '"+sdf.format(Date1)+" 00:00:00'";
			sql+=" GROUP BY t2.rba_name, t3.`rbs_name`,t4.`rbb_name`, DATE_FORMAT(t1.Place_order_time, '%Y-%m-%d') ";
			return this.getJdbcDao().query(sql);
		}
		//导出全部订单中的模板数据
		public List<Map<String,Object>> getMxListbyOrderAndMx(MdOrderInfo att_MdOrderInfo,MdOrderMx att_MdOrderMx,String moiIds) throws HSKDBException{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sql="SELECT  t1.order_code,t2.rba_name, t3.`rbs_name`,t4.`rbb_name`,t5.`user_name`,DATE_FORMAT(t1.Place_order_time, '%Y-%m-%d') AS TIME,";
				sql+="(SELECT GROUP_CONCAT(t6.mat_name SEPARATOR '\r\n') FROM md_order_mx t6 WHERE t6.moi_id=t1.moi_id ) as matName,";
				sql+="(SELECT GROUP_CONCAT(t6.NORM SEPARATOR ',') FROM md_order_mx t6 WHERE t6.moi_id=t1.moi_id) AS NORM,";
				sql+="(SELECT GROUP_CONCAT(t7.mmt_name SEPARATOR ',') FROM md_materiel_type t7 WHERE t7.mmt_path IN(SELECT t6.mat_type FROM md_order_mx t6 WHERE t6.moi_id=t1.moi_id)) AS matType,";
				sql+="(SELECT GROUP_CONCAT(ROUND(t6.Unit_money,2) SEPARATOR ',') FROM md_order_mx t6 WHERE t6.moi_id=t1.moi_id) AS UnitMoney,";
				sql+="(SELECT GROUP_CONCAT(ROUND(t6.mat_number,0) SEPARATOR ',') FROM md_order_mx t6 WHERE t6.moi_id=t1.moi_id) AS matNumber,";
				sql+="ROUND((SELECT SUM(t6.mat_number) FROM  md_order_mx t6 WHERE t6.moi_id=t1.moi_id),0) AS matNameSum, ";
				sql+="ROUND((SELECT SUM(t6.Total_money) FROM  md_order_mx t6 WHERE t6.moi_id=t1.moi_id),2) AS TotalMoneySum1, ";
				sql+="(SELECT GROUP_CONCAT(ROUND(t6.Total_money,2) SEPARATOR ',') FROM md_order_mx t6 WHERE t6.moi_id=t1.moi_id) AS TotalMoney,";
				sql+="(SELECT a.PARAM_NAME FROM sys_parameter a, sys_parameter b WHERE a.SYS_SPAR_ID = '127' AND a.PARAM_VALUE = t1.pay_type AND a.SYS_SPAR_ID = b.spar_id AND b.param_code = 'PAR191222092351998') AS payName,";
				sql+="(SELECT a.PARAM_NAME FROM sys_parameter a, sys_parameter b WHERE a.SYS_SPAR_ID = '107' AND a.PARAM_VALUE = t1.Process_status AND a.SYS_SPAR_ID = b.spar_id AND b.param_code = 'PAR171023031218563') AS ProcessName,";
				sql+="CONCAT('总额',t1.Actual_money,'(含12元快递费)','发票抬头:',t1.bill_heard,'税号：',t1.bill_code)AS ddmx,";
				sql+="CONCAT('收货人',t1.Addressee,'联系电话',t1.Addressee_telephone,'收货地址',Delivery_address,'物流信息:',express_name,express_code)AS wlxx,";
				sql+="t1.enterprise_type,t1.scope_business,";
				sql+="(SELECT GROUP_CONCAT(ROUND(t6.number2,0) SEPARATOR ',') FROM md_order_mx t6 WHERE t6.moi_id=t1.moi_id) AS number2,";
				sql+="(SELECT GROUP_CONCAT(ROUND(t6.shure_number,0) SEPARATOR ',') FROM md_order_mx t6 WHERE t6.moi_id=t1.moi_id) AS shureNumber,";
				sql+="(SELECT GROUP_CONCAT(ROUND(t6.back_number,0) SEPARATOR ',') FROM md_order_mx t6 WHERE t6.moi_id=t1.moi_id) AS backNumber,";
				sql+="(SELECT GROUP_CONCAT(ROUND((t6.back_number*t6.Unit_money),2)  SEPARATOR ',') FROM md_order_mx t6 WHERE t6.moi_id=t1.moi_id) AS backMoney,";
				sql+="(SELECT GROUP_CONCAT(ROUND(t6.Total_money,2) SEPARATOR ',') FROM md_order_mx t6 WHERE t6.moi_id=t1.moi_id) AS TotalMoney,";
				sql+="ROUND((SELECT SUM(Total_money) FROM md_order_mx t6 WHERE t6.moi_id=t1.moi_id),2) AS TotalMoneySum ";
				sql+=" FROM md_order_info t1 ";
				sql+=" LEFT JOIN md_company_group t2 ON t1.rba_id=t2.rba_id";
				sql+=" LEFT JOIN md_dentist_hospital t3 ON t1.rbs_id =t3.`rbs_id`";
				sql+=" LEFT JOIN md_dental_clinic t4 ON t1.rbb_id =t4.`rbb_id`";
				sql+=" LEFT JOIN sys_user_info t5 ON t1.purchase_id=t5.`sui_id` WHERE 1=1 and t1.`order_code` is not null ";
			if(att_MdOrderInfo!=null && att_MdOrderInfo.getWzId()!= null)
				sql += " and t1.wz_id="+att_MdOrderInfo.getWzId();
			if(att_MdOrderInfo!=null && att_MdOrderInfo.getPurchaseType()!= null && !att_MdOrderInfo.getPurchaseType().equals(""))
				sql += " and t1.purchase_type="+att_MdOrderInfo.getPurchaseType();
			if(att_MdOrderInfo!=null && att_MdOrderInfo.getRbaId()!= null)
				sql += " and t1.rba_id="+att_MdOrderInfo.getRbaId();
			if(att_MdOrderInfo!=null && att_MdOrderInfo.getRbbId()!= null)
				sql += " and t1.rbb_id="+att_MdOrderInfo.getRbbId();
			if(att_MdOrderInfo!=null && att_MdOrderInfo.getRbsId()!= null)
				sql += " and t1.rbs_id="+att_MdOrderInfo.getRbsId();
			if(att_MdOrderInfo!=null && att_MdOrderInfo.getPurchaseId()!= null)
				sql += " and t1.purchase_id="+att_MdOrderInfo.getPurchaseId();
			if(att_MdOrderInfo != null && att_MdOrderInfo.getOrderCode()!=null && !att_MdOrderInfo.getOrderCode().trim().equals(""))
				sql += " and t1.order_code like '%"+att_MdOrderInfo.getOrderCode().trim()+"%'";
			if(att_MdOrderInfo != null && att_MdOrderInfo.getApplicantName()!=null && !att_MdOrderInfo.getApplicantName().trim().equals(""))
				sql += " and t1.applicant_name like '%"+att_MdOrderInfo.getApplicantName().trim()+"%'";
			if(att_MdOrderInfo != null && att_MdOrderInfo.getPurchaseUnit()!=null && !att_MdOrderInfo.getPurchaseUnit().trim().equals(""))
				sql += " and t1.purchase_unit like '%"+att_MdOrderInfo.getPurchaseUnit().trim()+"%'";
			if(att_MdOrderInfo != null && att_MdOrderInfo.getProcessStatus()!=null && !att_MdOrderInfo.getProcessStatus().trim().equals(""))
				sql += " and t1.process_status like '%"+att_MdOrderInfo.getProcessStatus().trim()+"%'";
			if(att_MdOrderInfo != null && att_MdOrderInfo.getProcessStatus_str()!=null && !att_MdOrderInfo.getProcessStatus_str().trim().equals(""))
				sql += " and t1.process_status in ("+att_MdOrderInfo.getProcessStatus_str().trim()+")";
			if(att_MdOrderMx != null && att_MdOrderMx.getMatName()!=null && !att_MdOrderMx.getMatName().trim().equals(""))
				sql += " and t1.mat_name like '%"+att_MdOrderMx.getMatName().trim()+"%'";
			if(att_MdOrderInfo != null && att_MdOrderInfo.getPlaceOrderTime_start()!=null)
				sql += " and t1.place_order_time >='"+sdf.format(att_MdOrderInfo.getPlaceOrderTime_start())+" 00:00:00'";
			if(att_MdOrderInfo != null && att_MdOrderInfo.getPlaceOrderTime_end()!=null)
				sql += " and t1.place_order_time <='"+sdf.format(att_MdOrderInfo.getPlaceOrderTime_end())+" 23:59:59'";
			if (moiIds!=null) {
				sql+=" and t1.moi_id in("+moiIds+")";
			}
			sql += " order by DATE_FORMAT(t1.Place_order_time, '%Y-%m-%d')";
			return this.getJdbcDao().query(sql);
		}
		
}