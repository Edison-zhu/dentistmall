package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.hsk.supper.dto.comm.PagerModel;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_order_info表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-28 17:16:59
 */
@Component
public class MdOrderInfoApiDao extends SupperDao implements IMdOrderInfoApiDao {

	/**
	 * 根据md_order_info表主键查找MdOrderInfo对象记录
	 * 
	 * @param  MoiId  Integer类型(md_order_info表主键)
	 * @return MdOrderInfo md_order_info表记录
	 * @throws HSKDBException
	 */	
	public MdOrderInfo findMdOrderInfoById(Integer MoiId) throws HSKDBException{
				MdOrderInfo  att_MdOrderInfo=new MdOrderInfo();				
				if(MoiId!=null){
					att_MdOrderInfo.setMoiId(MoiId);	
				    Object obj=	this.getOne(att_MdOrderInfo);
					if(obj!=null){
						att_MdOrderInfo=(MdOrderInfo) obj;
					}
				}
				return  att_MdOrderInfo;
	}

	public MdOrderInfoAfterSaleViewEntity findMdOrderInfoIncludeAsById(Integer MoiId) throws HSKDBException{
		MdOrderInfoAfterSaleViewEntity  att_MdOrderInfo=new MdOrderInfoAfterSaleViewEntity();
		if(MoiId!=null){
			att_MdOrderInfo.setMoiId(MoiId);
			Object obj=	this.getOne(att_MdOrderInfo);
			if(obj!=null){
				att_MdOrderInfo=(MdOrderInfoAfterSaleViewEntity) obj;
			}
		}
		return  att_MdOrderInfo;
	}
	/**
	 * 增加根据日期查询方法
	 */
	public MdOrderInfo findMdOrderInfoByDate(Date EndDate,Date StartDate) throws HSKDBException{
		MdOrderInfo  att_MdOrderInfo=new MdOrderInfo();				
		if(EndDate!=null&&StartDate!=null){
			att_MdOrderInfo.setPlaceOrderTime_start(StartDate);
			att_MdOrderInfo.setPlaceOrderTime_end(EndDate);
		    Object obj=	this.getOne(att_MdOrderInfo);
			if(obj!=null){
				att_MdOrderInfo=(MdOrderInfo) obj;
			}
		}
		return  att_MdOrderInfo;
}
	 /**
	  * 根据md_order_info表主键删除MdOrderInfo对象记录
	  * @param  MoiId  Integer类型(md_order_info表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdOrderInfoById(Integer MoiId) throws HSKDBException{ 
		 MdOrderInfo  att_MdOrderInfo=new MdOrderInfo();	
		  if(MoiId!=null){
					  att_MdOrderInfo.setMoiId(MoiId);
				  	  Object obj=	this.getOne(att_MdOrderInfo);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_order_info表主键修改MdOrderInfo对象记录
     * @param  MoiId  Integer类型(md_order_info表主键)
     * @param  att_MdOrderInfo  MdOrderInfo类型(md_order_info表记录)
     * @return MdOrderInfo  修改后的MdOrderInfo对象记录
	 * @throws HSKDBException
	 */
	public  MdOrderInfo updateMdOrderInfoById(Integer MoiId,MdOrderInfo att_MdOrderInfo) throws HSKDBException{
		  if(MoiId!=null){
					att_MdOrderInfo.setMoiId(MoiId);
				   	Object obj=	this.getOne(att_MdOrderInfo);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdOrderInfo;
	}
	
	/**
	 * 新增md_order_info表 记录
     * @param  att_MdOrderInfo  MdOrderInfo类型(md_order_info表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdOrderInfo(MdOrderInfo att_MdOrderInfo) throws HSKDBException{
		 return this.newObject(att_MdOrderInfo);
	} 
		
	/**
	 * 新增或修改md_order_info表记录 ,如果md_order_info表主键MdOrderInfo.MoiId为空就是添加，如果非空就是修改
     * @param  att_MdOrderInfo  MdOrderInfo类型(md_order_info表记录)
	 * @throws HSKDBException
	 */
	public  MdOrderInfo saveOrUpdateMdOrderInfo(MdOrderInfo att_MdOrderInfo) throws HSKDBException{
		  this.getHibernateTemplate().saveOrUpdate(att_MdOrderInfo);
		  return att_MdOrderInfo;
	}
	
	/**
	 *根据MdOrderInfo对象作为对(md_order_info表进行查询，获取列表对象
     * @param  att_MdOrderInfo  MdOrderInfo类型(md_order_info表记录)
     * @return List<MdOrderInfo>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdOrderInfo> getListByMdOrderInfo(MdOrderInfo att_MdOrderInfo) throws HSKDBException{
		String Hql=this.getHql( att_MdOrderInfo, null);
		List<MdOrderInfo> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}

	@Override
	public PagerModel getPagerModelIncludeAsByMdOrderInfo(MdOrderInfo att_MdOrderInfo) throws HSKDBException {
		StringBuffer sbuffer = new StringBuffer("from MdOrderInfoAfterSaleViewEntity where 1=1 ");
		att_MdOrderInfo.setProcessStatus("");
		att_MdOrderInfo.setProcessStatus_str("");
		att_MdOrderInfo.setTab_order("as_create desc");
		String Hql=this.getHql(att_MdOrderInfo, sbuffer);
		return this.getHibernateDao().findByPage(Hql);
	}

	/**
	 *根据MdOrderInfo对象作为对(md_order_info表进行查询，获取分页对象
     * @param  att_MdOrderInfo  MdOrderInfo类型(md_order_info表记录)
     * @return List<MdOrderInfo>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdOrderInfo(MdOrderInfo att_MdOrderInfo)
			throws HSKDBException {
		String Hql=this.getHql(att_MdOrderInfo, null);
		return this.getHibernateDao().findByPage(Hql); 
	}

	@Override
	public PagerModel getAllPagerModelObjectDistinct(MdOrderInfo att_MdOrderInfo, String distinctName) throws HSKDBException {
		StringBuffer sbuffer = new StringBuffer("select distinct " + distinctName + " from MdOrderInfo where 1=1 ");
		String Hql=this.getHql(att_MdOrderInfo, sbuffer);
		return this.getHibernateDao().findByPage(Hql);
	}

	/**
	 * 根据MdOrderInfo对象获取Hql字符串
     * @param  att_MdOrderInfo  MdOrderInfo类型(md_order_info表记录)
	 *  @return hql字符串
	 */
	private String  getHql(MdOrderInfo att_MdOrderInfo, StringBuffer sbuffer){
		if(sbuffer == null){
			 sbuffer = new StringBuffer( " from  MdOrderInfo  where  1=1  ");
		}
			 String likeStr=  att_MdOrderInfo.getTab_like();
			 String orderStr= att_MdOrderInfo.getTab_order();
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 
			 //*****************无关字段处理begin*****************
						   		 //处理in条件 订单信息id(moiId)
							     if(att_MdOrderInfo.getMoiId_str()!=null&&
						   		    !"".equals(att_MdOrderInfo.getMoiId_str().trim())){ 
											 String  intStr=att_MdOrderInfo.getMoiId_str().trim();
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
						   		 //处理in条件 供应商id(wzId)
							     if(att_MdOrderInfo.getWzId_str()!=null&&
						   		    !"".equals(att_MdOrderInfo.getWzId_str().trim())){ 
											 String  intStr=att_MdOrderInfo.getWzId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  wzId="+did+"   "); 
													 }else {
													 sbuffer.append("  wzId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 集团公司id(rbaId)
							     if(att_MdOrderInfo.getRbaId_str()!=null&&
						   		    !"".equals(att_MdOrderInfo.getRbaId_str().trim())){ 
											 String  intStr=att_MdOrderInfo.getRbaId_str().trim();
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
							     if(att_MdOrderInfo.getRbsId_str()!=null&&
						   		    !"".equals(att_MdOrderInfo.getRbsId_str().trim())){ 
											 String  intStr=att_MdOrderInfo.getRbsId_str().trim();
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
							     if(att_MdOrderInfo.getRbbId_str()!=null&&
						   		    !"".equals(att_MdOrderInfo.getRbbId_str().trim())){ 
											 String  intStr=att_MdOrderInfo.getRbbId_str().trim();
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
								 	  //时间类型开始条件处理  下单时间(placeOrderTime)
									  if(att_MdOrderInfo.getPlaceOrderTime_start()!=null){
								   	    	sbuffer.append( " and  placeOrderTime>='" +sdf.format(att_MdOrderInfo.getPlaceOrderTime_start())+" 00:00:00'" );  
										 }
								 	  //时间类型结束条件处理 下单时间(placeOrderTime)
									 	if(att_MdOrderInfo.getPlaceOrderTime_end()!=null){
						   	      			sbuffer.append( " and  placeOrderTime <='" +sdf.format(att_MdOrderInfo.getPlaceOrderTime_end())+" 23:59:59'" );  
								  	     } 
						   		 //处理in条件 商品数量(commodityNumber)
							     if(att_MdOrderInfo.getCommodityNumber_str()!=null&&
						   		    !"".equals(att_MdOrderInfo.getCommodityNumber_str().trim())){ 
											 String  intStr=att_MdOrderInfo.getCommodityNumber_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  commodityNumber="+did+"   "); 
													 }else {
													 sbuffer.append("  commodityNumber="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 数量1(number1)
							     if(att_MdOrderInfo.getNumber1_str()!=null&&
						   		    !"".equals(att_MdOrderInfo.getNumber1_str().trim())){ 
											 String  intStr=att_MdOrderInfo.getNumber1_str().trim();
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
							     if(att_MdOrderInfo.getNumber2_str()!=null&&
						   		    !"".equals(att_MdOrderInfo.getNumber2_str().trim())){ 
											 String  intStr=att_MdOrderInfo.getNumber2_str().trim();
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
							     if(att_MdOrderInfo.getNumber3_str()!=null&&
						   		    !"".equals(att_MdOrderInfo.getNumber3_str().trim())){ 
											 String  intStr=att_MdOrderInfo.getNumber3_str().trim();
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
							     if(att_MdOrderInfo.getNumber4_str()!=null&&
						   		    !"".equals(att_MdOrderInfo.getNumber4_str().trim())){ 
											 String  intStr=att_MdOrderInfo.getNumber4_str().trim();
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
							     if(att_MdOrderInfo.getNumber5_str()!=null&&
						   		    !"".equals(att_MdOrderInfo.getNumber5_str().trim())){ 
											 String  intStr=att_MdOrderInfo.getNumber5_str().trim();
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
							     
							     if(att_MdOrderInfo.getProcessStatus_str()!=null&&
								   		    !"".equals(att_MdOrderInfo.getProcessStatus_str().trim())){ 
													 String  intStr=att_MdOrderInfo.getProcessStatus_str().trim();
													 sbuffer.append(" and processStatus in ("+intStr+")");
								}
							     
							     
								 		//时间类型开始条件处理  创建时间(createDate)
									  if(att_MdOrderInfo.getCreateDate_start()!=null){
								   	    	sbuffer.append( " and  createDate<='" +att_MdOrderInfo.getCreateDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 创建时间(createDate)
									 	if(att_MdOrderInfo.getCreateDate_end()!=null){
						   	      			sbuffer.append( " and  createDate>'" +att_MdOrderInfo.getCreateDate_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  修改时间(editDate)
									  if(att_MdOrderInfo.getEditDate_start()!=null){
								   	    	sbuffer.append( " and  editDate<='" +att_MdOrderInfo.getEditDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 修改时间(editDate)
									 	if(att_MdOrderInfo.getEditDate_end()!=null){
						   	      			sbuffer.append( " and  editDate>'" +att_MdOrderInfo.getEditDate_end()+"'" );  
								  	     } 
			 //*****************无关字段处理end*****************
			 //*****************数据库字段处理begin*************
								  		//订单信息id(moiId)
					 					if(att_MdOrderInfo.getMoiId()!=null){
											 sbuffer.append( " and moiId=" +att_MdOrderInfo.getMoiId() );
										 }
					 					if(att_MdOrderInfo.getPurchaseId()!=null){
											 sbuffer.append( " and purchaseId=" +att_MdOrderInfo.getPurchaseId() );
										 }
								  		//供应商id(wzId)
					 					if(att_MdOrderInfo.getWzId()!=null){
											 sbuffer.append( " and wzId=" +att_MdOrderInfo.getWzId() );
										 }
								  		//集团公司id(rbaId)
					 					if(att_MdOrderInfo.getRbaId()!=null){
											 sbuffer.append( " and rbaId=" +att_MdOrderInfo.getRbaId() );
										 }
								  		//牙医医院id(rbsId)
					 					if(att_MdOrderInfo.getRbsId()!=null){
											 sbuffer.append( " and rbsId=" +att_MdOrderInfo.getRbsId() );
										 }
								  		//牙医门诊id(rbbId)
					 					if(att_MdOrderInfo.getRbbId()!=null){
											 sbuffer.append( " and rbbId=" +att_MdOrderInfo.getRbbId() );
										 }
					       				//采购单位类型(purchaseType)		 					 
									 if(att_MdOrderInfo.getPurchaseType()!=null&&
									  !"".equals(att_MdOrderInfo.getPurchaseType().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("purchaseType")!=-1){
											  sbuffer.append( " and purchaseType  like '%"+att_MdOrderInfo.getPurchaseType()+"%'"   );
										  }else {
											  sbuffer.append( " and purchaseType   ='"+att_MdOrderInfo.getPurchaseType()+"'"   );
										  }
									 }
									 //前端传小写，后端自动处理为大写
									 /**
									  * yanglei
									  */
					       				//订单编号(orderCode)		 					 
									 if(att_MdOrderInfo.getOrderCode()!=null&&
									  !"".equals(att_MdOrderInfo.getOrderCode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("orderCode")!=-1){//  or Delivery_address like '%"+att_MdOrderInfo.getOrderCode().toUpperCase()+"%'
											  sbuffer.append( " and (orderCode  like '%"+att_MdOrderInfo.getOrderCode().toUpperCase()+"%' " +
													  "or moiId in (select moiId from MdOrderMx where matName like '%" + att_MdOrderInfo.getOrderCode().trim().toUpperCase() + "%'))");
										  }else {
											  sbuffer.append( " and orderCode   ='"+att_MdOrderInfo.getOrderCode()+"'"   );
										  }
									 }
						   				//下单时间(placeOrderTime)
						 				if(att_MdOrderInfo.getPlaceOrderTime()!=null){
						   	                   sbuffer.append( " and  placeOrderTime='" +att_MdOrderInfo.getPlaceOrderTime()+"'" );  
								  		}
								  		//商品数量(commodityNumber)
					 					if(att_MdOrderInfo.getCommodityNumber()!=null){
											 sbuffer.append( " and commodityNumber=" +att_MdOrderInfo.getCommodityNumber() );
										 }
					       				//订单状态(orderState)		 					 
									 if(att_MdOrderInfo.getOrderState()!=null&&
									  !"".equals(att_MdOrderInfo.getOrderState().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("orderState")!=-1){
											  sbuffer.append( " and orderState  like '%"+att_MdOrderInfo.getOrderState()+"%'"   );
										  }else {
											  sbuffer.append( " and orderState   ='"+att_MdOrderInfo.getOrderState()+"'"   );
										  }
									 }
					       				//流程状态(1打开,2结束,3操作)(processStatus)		 					 
									 if(att_MdOrderInfo.getProcessStatus()!=null&&
									  !"".equals(att_MdOrderInfo.getProcessStatus().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("processStatus")!=-1){
											  sbuffer.append( " and processStatus  like '%"+att_MdOrderInfo.getProcessStatus()+"%'"   );
										  }else {
											  sbuffer.append( " and processStatus   ='"+att_MdOrderInfo.getProcessStatus()+"'"   );
										  }
									 }
					       				//采购商名称(purchaseUnit)		 					 
									 if(att_MdOrderInfo.getPurchaseUnit()!=null&&
									  !"".equals(att_MdOrderInfo.getPurchaseUnit().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("purchaseUnit")!=-1){
											  sbuffer.append( " and purchaseUnit  like '%"+att_MdOrderInfo.getPurchaseUnit()+"%'"   );
										  }else {
											  sbuffer.append( " and purchaseUnit   ='"+att_MdOrderInfo.getPurchaseUnit()+"'"   );
										  }
									 }
					       				//采购人帐号(purchaseAccount)		 					 
									 if(att_MdOrderInfo.getPurchaseAccount()!=null&&
									  !"".equals(att_MdOrderInfo.getPurchaseAccount().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("purchaseAccount")!=-1){
											  sbuffer.append( " and purchaseAccount  like '%"+att_MdOrderInfo.getPurchaseAccount()+"%'"   );
										  }else {
											  sbuffer.append( " and purchaseAccount   ='"+att_MdOrderInfo.getPurchaseAccount()+"'"   );
										  }
									 }
					       				//所在区域(location)		 					 
									 if(att_MdOrderInfo.getLocation()!=null&&
									  !"".equals(att_MdOrderInfo.getLocation().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("location")!=-1){
											  sbuffer.append( " and location  like '%"+att_MdOrderInfo.getLocation()+"%'"   );
										  }else {
											  sbuffer.append( " and location   ='"+att_MdOrderInfo.getLocation()+"'"   );
										  }
									 }
					       				//配送地址(deliveryAddress)		 					 
									 if(att_MdOrderInfo.getDeliveryAddress()!=null&&
									  !"".equals(att_MdOrderInfo.getDeliveryAddress().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("deliveryAddress")!=-1){
											  sbuffer.append( " and deliveryAddress  like '%"+att_MdOrderInfo.getDeliveryAddress()+"%'"   );
										  }else {
											  sbuffer.append( " and deliveryAddress   ='"+att_MdOrderInfo.getDeliveryAddress()+"'"   );
										  }
									 }
					       				//邮编(zipCode)		 					 
									 if(att_MdOrderInfo.getZipCode()!=null&&
									  !"".equals(att_MdOrderInfo.getZipCode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("zipCode")!=-1){
											  sbuffer.append( " and zipCode  like '%"+att_MdOrderInfo.getZipCode()+"%'"   );
										  }else {
											  sbuffer.append( " and zipCode   ='"+att_MdOrderInfo.getZipCode()+"'"   );
										  }
									 }
					       				//收件人(addressee)	
									 /**
									  * yanglei
									  * 关键字查询（支持多个人姓名查询并用/隔开）
									  * 2019-12-18
									  */
									 if(att_MdOrderInfo.getAddressee()!=null&&
									  !"".equals(att_MdOrderInfo.getAddressee().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("addressee")!=-1){
											  sbuffer.append( " and addressee  like '%"+att_MdOrderInfo.getAddressee()+"%'"   );
										  }else {
											  //sbuffer.append( " and addressee   ='"+att_MdOrderInfo.getAddressee()+"'"   );
											  String inStr=att_MdOrderInfo.getAddressee().trim();
											  String[] arrStr=inStr.split("/");
											  if (arrStr.length>0) {
												  sbuffer.append(" and ( ");
												  for (int i = 0; i < arrStr.length; i++) {
													  String did=arrStr[i];
													  if(i==arrStr.length-1){
														  sbuffer.append("  addressee='"+did+"'   "); 
													  }else{
														  sbuffer.append("  addressee='"+did+"' or "); 
													  }
												}
												 sbuffer.append(" ) ");   
											}
										  }
										  
									 }
					       				//收件联系电话(addresseeTelephone)	
									 /**
									  * yanglei
									  * 关键字查询（支持多个联系电话查询并用/隔开）
									  * 2019-12-18
									  */
									 if(att_MdOrderInfo.getAddresseeTelephone()!=null&&
									  !"".equals(att_MdOrderInfo.getAddresseeTelephone().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("addresseeTelephone")!=-1){
											  sbuffer.append( " and addresseeTelephone  like '%"+att_MdOrderInfo.getAddresseeTelephone()+"%'"   );
										  }else {
											 /* sbuffer.append( " and addresseeTelephone   ='"+att_MdOrderInfo.getAddresseeTelephone()+"'"   );*/
											  String inStr=att_MdOrderInfo.getAddresseeTelephone().trim();
											  String[] arrStr=inStr.split("/");
											  if (arrStr.length>0) {
												  sbuffer.append(" and ( ");
												  for (int i = 0; i < arrStr.length; i++) {
													  String did=arrStr[i];
													  if(i==arrStr.length-1){
														  sbuffer.append("  addresseeTelephone='"+did+"'   "); 
													  }else{
														  sbuffer.append("  addresseeTelephone='"+did+"' or "); 
													  }
												}
												 sbuffer.append(" ) ");   
											}
										  }
									 }
									 /**
									  * yanglei
									  * 是否开票：全部、是、否。默认是显示全部。
									  * 2019-12-18
									  */
									 if(att_MdOrderInfo.getNeedBill()!=null&&
											  !"".equals(att_MdOrderInfo.getNeedBill().trim())){
												  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("needBill")!=-1){
													  sbuffer.append( " and needBill  like '%"+att_MdOrderInfo.getNeedBill()+"%'"   );
												  }else {
													  sbuffer.append( " and needBill   ='"+att_MdOrderInfo.getNeedBill()+"'"   );
												  }
											 }
					       				//收件人手机(addresseeMobile)		 					 
									 if(att_MdOrderInfo.getAddresseeMobile()!=null&&
									  !"".equals(att_MdOrderInfo.getAddresseeMobile().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("addresseeMobile")!=-1){
											  sbuffer.append( " and addresseeMobile  like '%"+att_MdOrderInfo.getAddresseeMobile()+"%'"   );
										  }else {
											  sbuffer.append( " and addresseeMobile   ='"+att_MdOrderInfo.getAddresseeMobile()+"'"   );
										  }
									 }
								  		//数量1(number1)
					 					if(att_MdOrderInfo.getNumber1()!=null){
											 sbuffer.append( " and number1=" +att_MdOrderInfo.getNumber1() );
										 }
								  		//数量1(number2)
					 					if(att_MdOrderInfo.getNumber2()!=null){
											 sbuffer.append( " and number2=" +att_MdOrderInfo.getNumber2() );
										 }
								  		//数量1(number3)
					 					if(att_MdOrderInfo.getNumber3()!=null){
											 sbuffer.append( " and number3=" +att_MdOrderInfo.getNumber3() );
										 }
								  		//数量1(number4)
					 					if(att_MdOrderInfo.getNumber4()!=null){
											 sbuffer.append( " and number4=" +att_MdOrderInfo.getNumber4() );
										 }
								  		//数量1(number5)
					 					if(att_MdOrderInfo.getNumber5()!=null){
											 sbuffer.append( " and number5=" +att_MdOrderInfo.getNumber5() );
										 }
					       				//法人手机号码(phoneNumber)		 					 
									 if(att_MdOrderInfo.getPhoneNumber()!=null&&
									  !"".equals(att_MdOrderInfo.getPhoneNumber().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("phoneNumber")!=-1){
											  sbuffer.append( " and phoneNumber  like '%"+att_MdOrderInfo.getPhoneNumber()+"%'"   );
										  }else {
											  sbuffer.append( " and phoneNumber   ='"+att_MdOrderInfo.getPhoneNumber()+"'"   );
										  }
									 }
									 if(att_MdOrderInfo.getApplicantName()!=null&&
											  !"".equals(att_MdOrderInfo.getApplicantName().trim())){
												  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("applicantName")!=-1){
													  sbuffer.append( " and applicantName  like '%"+att_MdOrderInfo.getApplicantName()+"%'"   );
												  }else {
													  sbuffer.append( " and applicantName   ='"+att_MdOrderInfo.getApplicantName()+"'"   );
												  }
											 }
									 if(att_MdOrderInfo.getExpressCode()!=null&&
											  !"".equals(att_MdOrderInfo.getExpressCode().trim())){
												  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("expressCode")!=-1){
													  sbuffer.append( " and expressCode  like '%"+att_MdOrderInfo.getExpressCode()+"%'"   );
												  }else {
													  sbuffer.append( " and expressCode   ='"+att_MdOrderInfo.getExpressCode()+"'"   );
												  }
											 }
					       				//法人姓名(fullName)		 					 
									 if(att_MdOrderInfo.getFullName()!=null&&
									  !"".equals(att_MdOrderInfo.getFullName().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("fullName")!=-1){
											  sbuffer.append( " and fullName  like '%"+att_MdOrderInfo.getFullName()+"%'"   );
										  }else {
											  sbuffer.append( " and fullName   ='"+att_MdOrderInfo.getFullName()+"'"   );
										  }
									 }
					       				//法人邮箱(mailbox)		 					 
									 if(att_MdOrderInfo.getMailbox()!=null&&
									  !"".equals(att_MdOrderInfo.getMailbox().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("mailbox")!=-1){
											  sbuffer.append( " and mailbox  like '%"+att_MdOrderInfo.getMailbox()+"%'"   );
										  }else {
											  sbuffer.append( " and mailbox   ='"+att_MdOrderInfo.getMailbox()+"'"   );
										  }
									 }
					       				//企业住所地(corporateDomicile)		 					 
									 if(att_MdOrderInfo.getCorporateDomicile()!=null&&
									  !"".equals(att_MdOrderInfo.getCorporateDomicile().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("corporateDomicile")!=-1){
											  sbuffer.append( " and corporateDomicile  like '%"+att_MdOrderInfo.getCorporateDomicile()+"%'"   );
										  }else {
											  sbuffer.append( " and corporateDomicile   ='"+att_MdOrderInfo.getCorporateDomicile()+"'"   );
										  }
									 }
					       				//企业类型(enterpriseType)		 					 
									 if(att_MdOrderInfo.getEnterpriseType()!=null&&
									  !"".equals(att_MdOrderInfo.getEnterpriseType().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("enterpriseType")!=-1){
											  sbuffer.append( " and enterpriseType  like '%"+att_MdOrderInfo.getEnterpriseType()+"%'"   );
										  }else {
											  sbuffer.append( " and enterpriseType   ='"+att_MdOrderInfo.getEnterpriseType()+"'"   );
										  }
									 }
					       				//经营范围(scopeBusiness)		 					 
									 if(att_MdOrderInfo.getScopeBusiness()!=null&&
									  !"".equals(att_MdOrderInfo.getScopeBusiness().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("scopeBusiness")!=-1){
											  sbuffer.append( " and scopeBusiness  like '%"+att_MdOrderInfo.getScopeBusiness()+"%'"   );
										  }else {
											  sbuffer.append( " and scopeBusiness   ='"+att_MdOrderInfo.getScopeBusiness()+"'"   );
										  }
									 }
					       				//证照号码(licenseNumber)		 					 
									 if(att_MdOrderInfo.getLicenseNumber()!=null&&
									  !"".equals(att_MdOrderInfo.getLicenseNumber().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("licenseNumber")!=-1){
											  sbuffer.append( " and licenseNumber  like '%"+att_MdOrderInfo.getLicenseNumber()+"%'"   );
										  }else {
											  sbuffer.append( " and licenseNumber   ='"+att_MdOrderInfo.getLicenseNumber()+"'"   );
										  }
									 }
					       				//收货状态(receivingStatus)		 					 
									 if(att_MdOrderInfo.getReceivingStatus()!=null&&
									  !"".equals(att_MdOrderInfo.getReceivingStatus().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("receivingStatus")!=-1){
											  sbuffer.append( " and receivingStatus  like '%"+att_MdOrderInfo.getReceivingStatus()+"%'"   );
										  }else {
											  sbuffer.append( " and receivingStatus   ='"+att_MdOrderInfo.getReceivingStatus()+"'"   );
										  }
									 }
					       				//创建人(createRen)		 					 
									 if(att_MdOrderInfo.getCreateRen()!=null&&
									  !"".equals(att_MdOrderInfo.getCreateRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("createRen")!=-1){
											  sbuffer.append( " and createRen  like '%"+att_MdOrderInfo.getCreateRen()+"%'"   );
										  }else {
											  sbuffer.append( " and createRen   ='"+att_MdOrderInfo.getCreateRen()+"'"   );
										  }
									 }
						   				//创建时间(createDate)
						 				if(att_MdOrderInfo.getCreateDate()!=null){
						   	                   sbuffer.append( " and  createDate='" +att_MdOrderInfo.getCreateDate()+"'" );  
								  		}
					       				//修改人(editRen)		 					 
									 if(att_MdOrderInfo.getEditRen()!=null&&
									  !"".equals(att_MdOrderInfo.getEditRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("editRen")!=-1){
											  sbuffer.append( " and editRen  like '%"+att_MdOrderInfo.getEditRen()+"%'"   );
										  }else {
											  sbuffer.append( " and editRen   ='"+att_MdOrderInfo.getEditRen()+"'"   );
										  }
									 }
						   				//修改时间(editDate)
						 				if(att_MdOrderInfo.getEditDate()!=null){
						   	                   sbuffer.append( " and  editDate='" +att_MdOrderInfo.getEditDate()+"'" );  
								  		}
						 				//增加支付方式字段 pyType
						 				 if(att_MdOrderInfo.getPay_type_str()!=null&&
										   		    !"".equals(att_MdOrderInfo.getPay_type_str().trim())){ 
															 String  intStr=att_MdOrderInfo.getPay_type_str().trim();
															 String[]  arrayStr=intStr.split(","); 
															 
															  if(arrayStr.length>0){
																 sbuffer.append(" and ( ");
																 for(int i=0;i<arrayStr.length;i++){
																	 String did=arrayStr[i];
																	 if(i==arrayStr.length-1){
																		 sbuffer.append("  payType='"+did+"'   ");
																	 }else {
																	 sbuffer.append("  payType='"+did+"' or ");
																	 }
																 }
																 sbuffer.append(" ) "); 
															 }
															   
												 	}
			 //*****************数据库字段处理end***************
			  		if(orderStr!=null&&!"".equals(orderStr.trim())){
									 sbuffer.append( " order by "+orderStr);
						 }
						 /*
						 else {
							  sbuffer.append( " order by  MoiId   desc " );
					      }
					      */
			 System.out.println("sbuffer+"+sbuffer);
			 return  sbuffer.toString();
	}
	@Override
	public Integer countOrderInfoByProcessState(Integer PurchaseId,String processState)
			throws HSKDBException {
		String sql ="select count(1) as 'shu' from md_order_info where order_state='1' and purchase_id="+PurchaseId;
//		if(processState != null && processState.trim().equals("1")){//查询待发货
//			sql += " and process_status not in (5,6,7) and (commodity_number>number2 or number2 is null)";
//		}else if(processState != null && processState.trim().equals("4")){
//			sql += " and process_status not in (5,6,7) and number2 is not null and (number1<number2 or number1 is null)";
		if(processState != null && !processState.trim().equals("")){
			sql += " and process_status in (" + processState + ") ";
		}
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Integer.parseInt(list.get(0).get("shu").toString());
		}
		return 0;
	}

	@Override
	public Integer countOrderInfoByAfterSale(Integer suiId) throws HSKDBException {
		String sql = "SELECT COUNT( DISTINCT a.moi_id ) as count FROM md_order_after_sale a LEFT JOIN md_order_info b ON a.moi_id = b.moi_id where as_state <> 6 ";
		if (suiId != null) {
			sql += " and a.sui_id = " + suiId;
		}

//		sql += " WHERE a.as_state != 6 and a.as_state != 4";
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list.size() <= 0) {
			return 0;
		}
		Map<String, Object> map = list.get(0);
		if (map == null) {
			return 0;
		}
		return Integer.parseInt(map.get("count").toString());
	}

	@Override
	public Integer countOrderInfoByAfterSaleTwo(Integer suiId, MdOrderInfo att_MdOrderInfo) throws HSKDBException {
		String sql = "SELECT COUNT( DISTINCT a.moi_id ) as count FROM md_order_after_sale a LEFT JOIN md_order_info b ON a.moi_id = b.moi_id where as_state <> 6 and as_state <> 4 ";
		if (suiId != null) {
			if (suiId != 1) {
				sql += " and b.wz_id = " + suiId;
			}
		}
		if (att_MdOrderInfo.getOrderCode() != null && !att_MdOrderInfo.getOrderCode().equals("")){
			sql += " and (order_code like '%" + att_MdOrderInfo.getOrderCode() + "%' or" +
					" a.moi_id in (select moi_id from md_order_mx where mat_name like '%" + att_MdOrderInfo.getOrderCode() + "%'))";
		}
		if(att_MdOrderInfo.getAddressee() != null && !att_MdOrderInfo.getAddressee().equals("")){
			sql += " and addressee like '%" + att_MdOrderInfo.getAddressee() +"%'";
		}
		if (att_MdOrderInfo.getPay_type_str() != null && !att_MdOrderInfo.getPay_type_str().equals("")){
			String  intStr=att_MdOrderInfo.getPay_type_str().trim();
			String[]  arrayStr=intStr.split(",");

			if(arrayStr.length>0) {
				sql += " and ( ";
				for (int i = 0; i < arrayStr.length; i++) {
					String did = arrayStr[i];
					if (i == arrayStr.length - 1) {
						sql +="  pay_type='" + did + "'   ";
					} else {
						sql +="  pay_type='" + did + "' or ";
					}
				}
				sql += " ) ";
			}
		}
		if(att_MdOrderInfo.getPurchaseUnit() != null && !att_MdOrderInfo.getPurchaseUnit().equals("")){
			sql += " and purchase_unit like '%"+ att_MdOrderInfo.getPurchaseUnit() +"%'";
		}
		if(att_MdOrderInfo.getAddresseeTelephone() != null && !att_MdOrderInfo.getAddresseeTelephone().equals("")){
			sql += " nad addressee_telephone like '%" + att_MdOrderInfo.getAddresseeTelephone() + "%'";
		}

		if (att_MdOrderInfo.getNeedBill() != null && !"".equals(att_MdOrderInfo.getNeedBill().trim())) {
			sql += " and need_bill  like '%" + att_MdOrderInfo.getNeedBill() + "%'";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		//时间类型开始条件处理  下单时间(placeOrderTime)
		if (att_MdOrderInfo.getPlaceOrderTime_start() != null) {
			sql += " and  place_order_time>='" + sdf.format(att_MdOrderInfo.getPlaceOrderTime_start()) + " 00:00:00'";
		}
		//时间类型结束条件处理 下单时间(placeOrderTime)
		if (att_MdOrderInfo.getPlaceOrderTime_end() != null) {
			sql += " and  place_order_time <='" + sdf.format(att_MdOrderInfo.getPlaceOrderTime_end()) + " 23:59:59'";
		}
//		sql += " WHERE  a.as_state != 6 and a.as_state != 4";
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list.size() <= 0) {
			return 0;
		}
		Map<String, Object> map = list.get(0);
		if (map == null) {
			return 0;
		}
		return Integer.parseInt(map.get("count").toString());
	}

	/**
	 * 增加状态count
	 * 2019-12-19
	 * yanglei
	 */
	@Override
	public Integer countOrderInfoByProcessStateTwo(Integer PurchaseId,String processState, MdOrderInfo att_MdOrderInfo)
			throws HSKDBException {
		String sql ="select count(*) as shu from md_order_info where order_state='1'";
		if (PurchaseId != null && PurchaseId!=1) {
			sql+="and wz_id="+PurchaseId;
		}
		if(processState != null){//待发货的次数
			sql += " and process_status in (" + processState + ")";//and (commodity_number>number2 or number2 is null)
		}
		if (att_MdOrderInfo.getOrderCode() != null && !att_MdOrderInfo.getOrderCode().equals("")){
			sql += " and (order_code like '%" + att_MdOrderInfo.getOrderCode() + "%' or" +
					" moi_id in (select moi_id from md_order_mx where mat_name like '%" + att_MdOrderInfo.getOrderCode() + "%'))";
		}
		if(att_MdOrderInfo.getAddressee() != null && !att_MdOrderInfo.getAddressee().equals("")){
			sql += " and addressee like '%" + att_MdOrderInfo.getAddressee() +"%'";
		}
		if (att_MdOrderInfo.getPay_type_str() != null && !att_MdOrderInfo.getPay_type_str().equals("")){
			String  intStr=att_MdOrderInfo.getPay_type_str().trim();
			String[]  arrayStr=intStr.split(",");

			if(arrayStr.length>0) {
				sql += " and ( ";
				for (int i = 0; i < arrayStr.length; i++) {
					String did = arrayStr[i];
					if (i == arrayStr.length - 1) {
						sql +="  pay_type='" + did + "'   ";
					} else {
						sql +="  pay_type='" + did + "' or ";
					}
				}
				sql += " ) ";
			}
		}
		if(att_MdOrderInfo.getPurchaseUnit() != null && !att_MdOrderInfo.getPurchaseUnit().equals("")){
			sql += " and purchase_unit like '%"+ att_MdOrderInfo.getPurchaseUnit() +"%'";
		}
		if(att_MdOrderInfo.getAddresseeTelephone() != null && !att_MdOrderInfo.getAddresseeTelephone().equals("")){
			sql += " nad addressee_telephone like '%" + att_MdOrderInfo.getAddresseeTelephone() + "%'";
		}

		if (att_MdOrderInfo.getNeedBill() != null && !"".equals(att_MdOrderInfo.getNeedBill().trim())) {
			sql += " and need_bill  like '%" + att_MdOrderInfo.getNeedBill() + "%'";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		//时间类型开始条件处理  下单时间(placeOrderTime)
		if (att_MdOrderInfo.getPlaceOrderTime_start() != null) {
			sql += " and  place_order_time>='" + sdf.format(att_MdOrderInfo.getPlaceOrderTime_start()) + " 00:00:00'";
		}
		//时间类型结束条件处理 下单时间(placeOrderTime)
		if (att_MdOrderInfo.getPlaceOrderTime_end() != null) {
			sql += " and  place_order_time <='" + sdf.format(att_MdOrderInfo.getPlaceOrderTime_end()) + " 23:59:59'";
		}
//		else if(processState != null && processState.trim().equals("2")){//待付款
//			sql += " and process_status in (2)";//and number2 is not null and (number1<number2 or number1 is null)
//		}else if(processState != null && processState.trim().equals("3")){//待收货
//			sql += " and process_status in (3)";//and number2 is not null and (number1<number2 or number1 is null)
//		}else if(processState != null && processState.trim().equals("4")){//交易成功
//			sql += " and process_status in (4)";//and number2 is not null and (number1<number2 or number1 is null)
//		}else if(processState != null && processState.trim().equals("5")){//交易成功
//			sql += " and process_status in (5)";//and number2 is not null and (number1<number2 or number1 is null)
//		}else if(processState != null && processState.trim().equals("6")){//交易失败
//			sql += " and process_status in (6)";//and number2 is not null and (number1<number2 or number1 is null)
//		}
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Integer.parseInt(list.get(0).get("shu").toString());
		}
		return 0;
	}
	
	@Override
	public PagerModel getOrderInfoListByProcessState(Integer PurchaseId,String processState) throws HSKDBException {
		String hql ="from MdOrderInfo where orderState='1' and purchaseId="+PurchaseId;
//		if(processState != null && processState.trim().equals("1")){//查询待发货
//			hql += " and processStatus not in (5,6,7) and (commodityNumber>number2 or number2 is null)";
//		}else if(processState != null && processState.trim().equals("2")){
//			hql += " and processStatus not in (5,6,7) and number2 is not null and (number1<number2 or number1 is null)";
//		}
		if(processState != null && !processState.trim().equals("")){
			hql += " and process_status in (" + processState + ") ";
		}
		hql += " order by placeOrderTime desc";
		return this.getHibernateDao().findByPage(hql); 
	}

	@Override
	public PagerModel getOrderInfoListByAfterSale(Integer PurchaseId) throws HSKDBException {
		String hql = "from MdOrderInfoAfterSaleViewEntity where orderState='1' and purchaseId = " + PurchaseId;
		hql += " order by as_create desc";
		return this.getHibernateDao().findByPage(hql);
	}

	@Override
	public PagerModel getOrderInfoListByMoiid(Integer suiId, Integer moiId) throws HSKDBException {
		String hql ="from MdOrderInfo where orderState='1' and purchaseId="+suiId;
		if(moiId != null){
			hql += " and moiId = " + moiId;
		}
		return this.getHibernateDao().findByPage(hql);
	}

	@Override
	public PagerModel getPagerModelByEnterMdOrderInfo(MdOrderInfo att_MdOrderInfo) throws HSKDBException {
		String hql = "from MdOrderInfo where orderState='1'";
		if(att_MdOrderInfo.getRbaId() != null)
			hql += " and rbaId="+att_MdOrderInfo.getRbaId();
		if(att_MdOrderInfo.getRbbId() != null)
			hql += " and rbbId="+att_MdOrderInfo.getRbbId();
		if(att_MdOrderInfo.getRbsId() != null)
			hql += " and rbsId="+att_MdOrderInfo.getRbsId();
		if(att_MdOrderInfo.getOrderCode() != null && !att_MdOrderInfo.getOrderCode().trim().equals(""))
			hql += " and orderCode like '%"+att_MdOrderInfo.getOrderCode().trim()+"%'";
		if(att_MdOrderInfo.getApplicantName() != null && !att_MdOrderInfo.getApplicantName().trim().equals(""))
			hql += " and applicantName like '%"+att_MdOrderInfo.getApplicantName().trim()+"%'";
		if(att_MdOrderInfo.getExpressCode() != null && !att_MdOrderInfo.getExpressCode().trim().equals(""))
			hql += " and expressCode like '%"+att_MdOrderInfo.getExpressCode().trim()+"%'";
		if(att_MdOrderInfo.getPurchaseType() != null && !att_MdOrderInfo.getPurchaseType().trim().equals(""))
			hql += " and purchaseType='"+att_MdOrderInfo.getPurchaseType().trim()+"'";
		hql += " and processStatus not in (6) and number2 is not null and (number3<number2 or number3 is null)";
		hql += " order by placeOrderTime desc";
		return this.getHibernateDao().findByPage(hql); 
	}
	@Override
	public PagerModel getPagerModelByOutMdOrderInfo(MdOrderInfo att_MdOrderInfo)
			throws HSKDBException {
		String hql = "from MdOrderInfo where orderState='1'";
		if(att_MdOrderInfo.getRbaId() != null)
			hql += " and rbaId="+att_MdOrderInfo.getRbaId();
		if(att_MdOrderInfo.getRbbId() != null)
			hql += " and rbbId="+att_MdOrderInfo.getRbbId();
		if(att_MdOrderInfo.getRbsId() != null)
			hql += " and rbsId="+att_MdOrderInfo.getRbsId();
		if(att_MdOrderInfo.getOrderCode() != null && !att_MdOrderInfo.getOrderCode().trim().equals(""))
			hql += " and orderCode like '%"+att_MdOrderInfo.getOrderCode().trim()+"%'";
		if(att_MdOrderInfo.getApplicantName() != null && !att_MdOrderInfo.getApplicantName().trim().equals(""))
			hql += " and applicantName like '%"+att_MdOrderInfo.getApplicantName().trim()+"%'";
		if(att_MdOrderInfo.getExpressCode() != null && !att_MdOrderInfo.getExpressCode().trim().equals(""))
			hql += " and expressCode like '%"+att_MdOrderInfo.getExpressCode().trim()+"%'";
		if(att_MdOrderInfo.getPurchaseType() != null && !att_MdOrderInfo.getPurchaseType().trim().equals(""))
			hql += " and purchaseType='"+att_MdOrderInfo.getPurchaseType().trim()+"'";
		hql += "  and number3 is not null and (number4<number2 or number4 is null)";
		hql += " order by placeOrderTime desc";
		return this.getHibernateDao().findByPage(hql); 
	}

	@Override
	public PagerModel getPagerModelObjectForCkDistinct(MdOrderInfo att_mdOrderInfo, String distinctName) throws HSKDBException {
		StringBuffer sbuffer = new StringBuffer("select distinct " + distinctName + " from MdOrderInfo where 1=1 ");
		String Hql=this.getHql(att_mdOrderInfo, sbuffer);
		return this.getHibernateDao().findByPage(Hql);
	}

	@Override
	public PagerModel findOrderBySearch(Integer suiId, String processStatus, String searchName, String placeOrderTime, String payType) throws HSKDBException {
		String hql ="from MdOrderInfo where orderState='1' and purchaseId="+suiId;
//		if(processStatus != null && processStatus.trim().equals("1")){//查询待发货
//			hql += " and (process_status not in (5,6,7) and (commodity_number>number2 or number2 is null))";
//		}else if(processStatus != null && processStatus.trim().equals("2")){
//			hql += " and (process_status not in (5,6,7) and number2 is not null and (number1<number2 or number1 is null))";
//		}else
		if(processStatus != null && !processStatus.trim().equals("")){//查询待发货
			hql += " and (processStatus in (" + processStatus + "))";
		}
		if(searchName != null && !searchName.trim().equals("")){
			hql += " and (orderCode like '%" + searchName.trim().toUpperCase() +
					"%' or moiId in (select moiId from MdOrderMx where matName like '%" + searchName.trim().toUpperCase() + "%'))";
		}
		if(placeOrderTime != null && !placeOrderTime.trim().equals("")){
			hql += " and (placeOrderTime >= '" + placeOrderTime.split("~")[0] + "' and placeOrderTime <= '" + placeOrderTime.split("~")[1] +"')";
		}
		if(payType != null && !payType.equals("")){
			hql += " and payType=" + payType;
		}
		hql += " order by placeOrderTime desc";
		return this.getHibernateDao().findByPage(hql);
	}

	@Override
	public PagerModel findOrderBySearchName(Integer suiId, String processStatus, String searchName, String placeOrderTime, String payType) throws HSKDBException {
		String hql ="from MdOrderInfo where orderState='1' and purchaseId="+suiId;
//		if(processStatus != null && processStatus.trim().equals("1")){//查询待发货
//			hql += " and (process_status not in (5,6,7) and (commodity_number>number2 or number2 is null))";
//		}else if(processStatus != null && processStatus.trim().equals("2")){
//			hql += " and (process_status not in (5,6,7) and number2 is not null and (number1<number2 or number1 is null))";
//		}else
		if(processStatus != null && !processStatus.trim().equals("")){//查询待发货
			hql += " and (processStatus in (" + processStatus + "))";
		}
		if(searchName != null && !searchName.trim().equals("")){
			hql += " and (orderCode like '%" + searchName.trim().toUpperCase() +
					"%' or moiId in (select moiId from MdOrderMx where matName like '%" + searchName.trim().toUpperCase() + "%')" +
					" or moiId in (select moiId from MdOrderMx where wmsMiId in (select wmsMiId from MdMaterielInfo where brand like '%" + searchName.trim() + "%'))" +
					" or moiId in (select moiId from MdOrderMx where wmsMiId in (select wmsMiId from MdMaterielInfo where applicantName like '%" + searchName.trim() + "%')))";
		}
		if(placeOrderTime != null && !placeOrderTime.trim().equals("")){
			hql += " and (placeOrderTime >= '" + placeOrderTime.split("~")[0] + "' and placeOrderTime <= '" + placeOrderTime.split("~")[1] +"')";
		}
		if(payType != null && !payType.equals("")){
			hql += " and payType=" + payType;
		}
		hql += " order by placeOrderTime desc";
		return this.getHibernateDao().findByPage(hql);
	}

	@Override
	public PagerModel findOrderIncludeAsBySearch(Integer suiId, String processStatus, String searchName, String placeOrderTime, String payType) throws HSKDBException {
		String hql ="from MdOrderInfoAfterSaleViewEntity where orderState='1' and purchaseId="+suiId;
//		if(processStatus != null && processStatus.trim().equals("1")){//查询待发货
//			hql += " and (process_status not in (5,6,7) and (commodity_number>number2 or number2 is null))";
//		}else if(processStatus != null && processStatus.trim().equals("2")){
//			hql += " and (process_status not in (5,6,7) and number2 is not null and (number1<number2 or number1 is null))";
//		}else
		if(searchName != null && !searchName.trim().equals("")){
			hql += " and (orderCode like '%" + searchName.trim().toUpperCase() +
					"%' or moiId in (select moiId from MdOrderMx where matName like '%" + searchName.trim().toUpperCase() + "%'))";
		}
		if(placeOrderTime != null && !placeOrderTime.trim().equals("")){
			hql += " and (placeOrderTime >= '" + placeOrderTime.split("~")[0] + " 00:00:00' and placeOrderTime <= '" + placeOrderTime.split("~")[1] +" 23:59:59')";
		}
		if(payType != null && !payType.equals("")){
			hql += " and payType=" + payType;
		}
		hql += " order by as_create desc";
		return this.getHibernateDao().findByPage(hql);
	}

	@Override
	public PagerModel findOrderByMoiIds(Integer suiId, String ids) throws HSKDBException {
		String hql ="from MdOrderInfo where orderState='1' and purchaseId="+suiId;
		if (ids != null && !ids.equals("")){
			hql += " and moiId in (" + ids + ") ";
		}
//		hql += " order by placeOrderTime desc";
		return this.getHibernateDao().findByPage(hql);
	}

	@Override
	public List<Map<String, Object>> findOderByMx(Integer suiId, String processStatus, String searchName, Integer limit, Integer page) throws HSKDBException {
		String sql = "select distinct d.moi_id from (select b.mat_name, b.mom_id,a.moi_id, a.process_status, c.py_name from md_order_info a " +
				"LEFT JOIN md_order_mx b on a.moi_id = b.moi_id " +
				"left join md_materiel_info c on b.wms_mi_id = c.wms_mi_id where 1 = 1";
		if (searchName != null && !searchName.equals("")) {
			sql += " and c.mat_name like '%" + searchName.trim().toUpperCase() + "%'";
		}
//		if(processStatus != null && processStatus.trim().equals("1")){//查询待发货
//			sql += " and (a.process_status not in (5,6,7) and (a.commodity_number>a.number2 or a.number2 is null))";
//		}else if(processStatus != null && processStatus.trim().equals("2")){
//			sql += " and (a.process_status not in (5,6,7) and a.number2 is not null and (a.number1<a.number2 or number1 is null))";
//		}else
		if(processStatus != null && !processStatus.trim().equals("")){//查询待发货
			sql += " and (a.process_status like '%" + processStatus + "%')";
		}
		sql += ") d limit " + (page - 1) * limit + "," + limit;
		return this.getJdbcDao().query(sql);
	}
	/**
	 * =================================================================
	 * 以下是首页中的数据Dao内容
	 * 2019-12-29
	 * yanglei
	 */
	
	 //  累计订单总额
	public Integer CountOrers(Integer SuiId)throws HSKDBException{
		String sql ="select count(*) as countOrers from md_order_info where order_state='1'";
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Integer.parseInt(list.get(0).get("countOrers").toString());
		}
		return 0;
	}
	//增加金额总数
	public Double CountMoneys(Integer SuiId, String processStatus, String searchName, String placeOrderTime, String payTyp)throws HSKDBException{
		String sql ="SELECT SUM(Actual_money) AS placeOrderMoneys  FROM md_order_info WHERE order_state='1' and purchase_id=" + SuiId;
		if(placeOrderTime != null && !placeOrderTime.trim().equals("")){
			sql += " and (create_date >= '" + placeOrderTime.split("~")[0] + "' and create_date <= '" + placeOrderTime.split("~")[1] +"')";
		}
		if (processStatus != null && !processStatus.equals("")){
			sql += " and process_status in ("+processStatus+")";
		}
		if(searchName != null && !searchName.trim().equals("")){
			sql += " and (order_code like '%" + searchName.trim().toUpperCase() +
					"%' or moi_id in (select moi_id from md_order_mx where mat_name like '%" + searchName.trim().toUpperCase() + "%'))";
		}
		if(payTyp != null && !payTyp.equals("")){
			sql += " and pay_type in (" + payTyp + ")";
		}
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0) {
			if (list.get(0).get("placeOrderMoneys") != null)
				return Double.parseDouble(list.get(0).get("placeOrderMoneys").toString());
		}
		return 0.0000;
	}

	@Override
	public Double CountAsMoneys(Integer suiId, String processStatus, String searchName, String placeOrderTime, String payType) throws HSKDBException {
		String sql ="SELECT SUM(after_sale_money) AS placeOrderMoneys  FROM md_order_after_sale WHERE as_state=3 and sui_id=" + suiId;
		if(placeOrderTime != null && !placeOrderTime.trim().equals("")){
			sql += " and (create_date >= '" + placeOrderTime.split("~")[0] + "' and create_date <= '" + placeOrderTime.split("~")[1] +"')";
		}
//		if (processStatus != null && !processStatus.equals("")){
//			sql += " and process_status in ("+processStatus+")";
//		}
		if(searchName != null && !searchName.trim().equals("")){
			sql += " and (order_code like '%" + searchName.trim().toUpperCase() +
					"%' or moi_id in (select moi_id from md_order_mx where mat_name like '%" + searchName.trim().toUpperCase() + "%'))";
		}
		if(payType != null && !payType.equals("")){
			sql += " and pay_type in (" + payType + ")";
		}
		List<Map<Object, Object>> list = this.getJdbcDao().query(sql);
		if (list != null && list.size() > 0) {
			if (list.get(0).get("placeOrderMoneys") != null)
				return Double.parseDouble(list.get(0).get("placeOrderMoneys").toString());
		}
		return 0.0000;
	}

	//增加退货总金额
	public Double RetreatCountMoney(Integer SuiId)throws HSKDBException{
		String sql ="SELECT SUM(place_order_money) as RetreatPlaceOrderMoney FROM md_order_info WHERE order_state='1' AND Process_status='6'";
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Double.parseDouble(list.get(0).get("RetreatPlaceOrderMoney").toString());
		}
		return 0.0000;
		
	}
	//增加交易实时战报中的今日销售额
		public Double TransactionCountMoney(Integer SuiId)throws HSKDBException{
			Date NewDate=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			String sql ="SELECT SUM(place_order_money) AS TransactionMoneyCount FROM md_order_info WHERE order_state = '1'"; 
			sql+=" AND Place_order_time >='"+sf.format(NewDate)+" 00:00:00' AND Place_order_time <='"+sf.format(NewDate)+" 23:59:59'";
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
			if(list != null && list.size() > 0&&list.get(0).get("TransactionMoneyCount")!=null){
				return Double.parseDouble(list.get(0).get("TransactionMoneyCount").toString());
			}
			
			return 0.0000;
			
		}
		//增加交易实时战报中的今日下单
				public Integer TransactionCount(Integer SuiId)throws HSKDBException{
					Date NewDate=new Date();
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql ="SELECT count(*) AS TransactionCount FROM md_order_info WHERE order_state = '1'"; //AND Place_order_time >= '2019-12-30 00:00:00' AND Place_order_time <= '2019-12-30 23:59:59' ";
					sql+=" AND Place_order_time >='"+sf.format(NewDate)+" 00:00:00' AND Place_order_time <='"+sf.format(NewDate)+" 23:59:59'";
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0){
						return Integer.parseInt(list.get(0).get("TransactionCount").toString());
					}
					return 0;
					
				}
		//增加交易实战是战报中的今日下单百分比
				public Integer PercentageCount(Integer SuiId) throws HSKDBException{
					Date NewDate=new Date();
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql ="SELECT COUNT(*) AS PercentageCount FROM md_order_info WHERE order_state = '1'";// AND Place_order_time >= '2019-12-30 00:00:00' AND Place_order_time <= '2019-12-30 23:59:59' AND Process_status ='2'";
					sql+=" AND Place_order_time >='"+sf.format(NewDate)+" 00:00:00' AND Place_order_time <='"+sf.format(NewDate)+" 23:59:59'";
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0){
						return Integer.parseInt(list.get(0).get("PercentageCount").toString());
					}
					return 0;
				}
				
	//增加订单实时战报
	public List<Map<String, Object>> getOrderMxListByTransaction(Integer limit, Integer page) throws HSKDBException {
		Date PlaceOrderDate=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		/*String PlaceOrderDate1= sf.format(PlaceOrderDate);*/
		String sql = "SELECT DATE_FORMAT(Place_order_time,'%T') AS PlaceOrderTime,Purchase_unit AS Purchase_unit,commodity_number AS commodityNumber,place_order_money AS placeOrderMoney FROM md_order_info WHERE order_state='1'";
		sql+="AND Place_order_time>='"+sf.format(PlaceOrderDate)+" 00:00:00'";
		sql+="AND Place_order_time<='"+sf.format(PlaceOrderDate)+" 23:59:59'";
		sql+="GROUP BY Place_order_time DESC";
		if(limit != null && page != null) {
			sql += " limit " + (page - 1) * limit + "," + limit;
		}
		return this.getJdbcDao().query(sql);
	}
	//商品总览
	public Integer materielState(Integer state) throws HSKDBException{
		String sql ="SELECT COUNT(*) AS materielState FROM  md_materiel_info WHERE 1=1";
		if (state==0) {
			sql+=" AND 1=1";
		}if (state==1) {
			sql+=" AND state="+1;
		}if (state==2) {
			sql+=" AND state="+2;
		}
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Integer.parseInt(list.get(0).get("materielState").toString());
		}
		return 0;
	}
	//商品种类统计
		public Integer materielType(String matType) throws HSKDBException{
			String sql ="SELECT COUNT(*) AS materielType FROM md_materiel_info  WHERE 1=1";
			if (matType==null) {
				sql+=" AND mat_type1=mat_type1";
			}
			if (matType=="2201") {
				sql+=" AND mat_type1='2201'";
			}
			if (matType=="2202") {
				sql+=" AND mat_type1='2202'";
			}
			if (matType=="2203") {
				sql+=" AND mat_type1='2203'";
			}
			if (matType=="2204") {
				sql+=" AND mat_type1='2204'";
			}
			if (matType=="2205") {
				sql+=" AND mat_type1='2205'";
			}
			if (matType=="2206") {
				sql+=" AND mat_type1='2206'";
			}
			if (matType=="2207") {
				sql+=" AND mat_type1='2207'";
			}
			if (matType=="2208") {
				sql+=" AND mat_type1='2208'";
			}
			List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
			if(list != null && list.size() > 0){
			return Integer.parseInt(list.get(0).get("materielType").toString());
			}
			return 0;
		}
		//交易分析中成交量
		public Integer materielAnalysis(Integer value) throws HSKDBException{
			Date AnalysisDate=new Date();
			Date yesterday=new Date(AnalysisDate.getTime()- 24*60*60*1000);
			Date sevendays=new Date(AnalysisDate.getTime()- 24*60*60*1000*7);
			Date month=new Date(AnalysisDate.getTime()- 24*60*60*1000*20-24*60*60*1000*10);
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			String sql ="SELECT COUNT(*) AS AnalysisCount FROM  md_order_info WHERE Process_status='5'";
			if (value==0) {
				sql+=" AND end_time>='"+sf.format(AnalysisDate)+" 00:00:00'";
				sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
			}
			if (value==1) {
				sql+=" AND end_time>='"+sf.format(yesterday)+" 00:00:00'";
				sql+=" AND end_time<='"+sf.format(yesterday)+" 23:59:59'";
			}
			if (value==2) {
				sql+=" AND end_time>='"+sf.format(sevendays)+" 00:00:00'";
				sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
			}
			if (value==3) {
				sql+=" AND end_time>='"+sf.format(month)+" 00:00:00'";
				sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
			}
			List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
			if(list != null && list.size() > 0){
				return Integer.parseInt(list.get(0).get("AnalysisCount").toString());
			}
			return 0;
		}
		//交易分析中成交量
				public Integer materielAnalysisCounts(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					Date yesterday=new Date(AnalysisDate.getTime()- 24*60*60*1000);
					Date sevendays=new Date(AnalysisDate.getTime()- 24*60*60*1000*7);
					Date month=new Date(AnalysisDate.getTime()- 24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql ="SELECT COUNT(*) AS AnalysisCount FROM  md_order_info WHERE Process_status=Process_status";
					if (value==0) {
						sql+=" AND end_time>='"+sf.format(AnalysisDate)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==1) {
						sql+=" AND end_time>='"+sf.format(yesterday)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(yesterday)+" 23:59:59'";
					}
					if (value==2) {
						sql+=" AND end_time>='"+sf.format(sevendays)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==3) {
						sql+=" AND end_time>='"+sf.format(month)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0){
						return Integer.parseInt(list.get(0).get("AnalysisCount").toString());
					}
					return 0;
				}
				//交易分析中成交量
				public Double materielAnalysisMoney(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					Date yesterday=new Date(AnalysisDate.getTime()- 24*60*60*1000);
					Date sevendays=new Date(AnalysisDate.getTime()- 24*60*60*1000*7);
					Date month=new Date(AnalysisDate.getTime()- 24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql ="SELECT SUM(Actual_money) AS ActualMoney FROM  md_order_info WHERE Process_status='5'";
					if (value==0) {
						sql+=" AND end_time>='"+sf.format(AnalysisDate)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==1) {
						sql+=" AND end_time>='"+sf.format(yesterday)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(yesterday)+" 23:59:59'";
					}
					if (value==2) {
						sql+=" AND end_time>='"+sf.format(sevendays)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==3) {
						sql+=" AND end_time>='"+sf.format(month)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (sql!=null) {
						List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
						if(list != null && list.size() > 0&&list.get(0).get("ActualMoney")!=null){
							return Double.parseDouble(list.get(0).get("ActualMoney").toString());
						}
					}else{
						
					}
					return 0.0000;
				}
				//交易分析中成交量
				public Double materielAnalysisMoneys(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					Date yesterday=new Date(AnalysisDate.getTime()- 24*60*60*1000);
					Date sevendays=new Date(AnalysisDate.getTime()- 24*60*60*1000*7);
					Date month=new Date(AnalysisDate.getTime()- 24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql ="SELECT SUM(place_order_money) AS ActualMoney FROM  md_order_info WHERE Process_status=Process_status";
					if (value==0) {
						sql+=" AND end_time>='"+sf.format(AnalysisDate)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==1) {
						sql+=" AND end_time>='"+sf.format(yesterday)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(yesterday)+" 23:59:59'";
					}
					if (value==2) {
						sql+=" AND end_time>='"+sf.format(sevendays)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==3) {
						sql+=" AND end_time>='"+sf.format(month)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0&&list.get(0).get("ActualMoney")!=null){
						return Double.parseDouble(list.get(0).get("ActualMoney").toString());
					}
					return 0.0000;
				}
				//交易分析中人均消费
				public Integer materielAvgMoney(Integer value) throws HSKDBException{
						Date AnalysisDate=new Date();
						Date yesterday=new Date(AnalysisDate.getTime()- 24*60*60*1000);
						Date sevendays=new Date(AnalysisDate.getTime()- 24*60*60*1000*7);
						Date month=new Date(AnalysisDate.getTime()- 24*60*60*1000*20-24*60*60*1000*10);
						SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql ="SELECT count(purchase_Account) AS Pepole FROM  md_order_info WHERE Process_status='5'";
					if (value==0) {
						sql+=" AND end_time>='"+sf.format(AnalysisDate)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==1) {
						sql+=" AND end_time>='"+sf.format(yesterday)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(yesterday)+" 23:59:59'";
					}
					if (value==2) {
						sql+=" AND end_time>='"+sf.format(sevendays)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==3) {
						sql+=" AND end_time>='"+sf.format(month)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0){
						return Integer.parseInt(list.get(0).get("Pepole").toString());
					}
					return 0;
				}
				//交易分析中的总消费
				public Double materielAvgMoneys(Integer value) throws HSKDBException{
						Date AnalysisDate=new Date();
						Date yesterday=new Date(AnalysisDate.getTime()- 24*60*60*1000);
						Date sevendays=new Date(AnalysisDate.getTime()- 24*60*60*1000*7);
						Date month=new Date(AnalysisDate.getTime()- 24*60*60*1000*20-24*60*60*1000*10);
						SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql ="SELECT SUM(Actual_money) AS MoneyAvgs FROM  md_order_info WHERE Process_status='5'";
					if (value==0) {
						sql+=" AND end_time>='"+sf.format(AnalysisDate)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==1) {
						sql+=" AND end_time>='"+sf.format(yesterday)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(yesterday)+" 23:59:59'";
					}
					if (value==2) {
						sql+=" AND end_time>='"+sf.format(sevendays)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==3) {
						sql+=" AND end_time>='"+sf.format(month)+" 00:00:00'";
						sql+=" AND end_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0&&list.get(0).get("MoneyAvgs")!=null){
						return Double.parseDouble(list.get(0).get("MoneyAvgs").toString());
					}
					return 0.0000;
				}
				//交易转换中的浏览量
				public Integer materielConversionBrowse(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					Date yesterday=new Date(AnalysisDate.getTime()- 24*60*60*1000);
					Date sevendays=new Date(AnalysisDate.getTime()- 24*60*60*1000*7);
					Date month=new Date(AnalysisDate.getTime()- 24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql ="select count(*) as browse  from sys_user_login_log where sui_id is null and login_type=1";
					if (value==0) {
						sql+=" AND login_date>='"+sf.format(AnalysisDate)+" 00:00:00'";
						sql+=" AND login_date<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==1) {
						sql+=" AND login_date>='"+sf.format(yesterday)+" 00:00:00'";
						sql+=" AND login_date<='"+sf.format(yesterday)+" 23:59:59'";
					}
					if (value==2) {
						sql+=" AND login_date>='"+sf.format(sevendays)+" 00:00:00'";
						sql+=" AND login_date<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==3) {
						sql+=" AND login_date>='"+sf.format(month)+" 00:00:00'";
						sql+=" AND login_date<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0){
						return Integer.parseInt(list.get(0).get("browse").toString());
					}
					return 0;
				}
				//交易转换率中下单量
				public Integer materielConversionOrder(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					Date yesterday=new Date(AnalysisDate.getTime()- 24*60*60*1000);
					Date sevendays=new Date(AnalysisDate.getTime()- 24*60*60*1000*7);
					Date month=new Date(AnalysisDate.getTime()- 24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql ="select count(*) as conversionOrder from md_order_info where Process_status=2";
					if (value==0) {
						sql+=" AND Place_order_time>='"+sf.format(AnalysisDate)+" 00:00:00'";
						sql+=" AND Place_order_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==1) {
						sql+=" AND Place_order_time>='"+sf.format(yesterday)+" 00:00:00'";
						sql+=" AND Place_order_time<='"+sf.format(yesterday)+" 23:59:59'";
					}
					if (value==2) {
						sql+=" AND Place_order_time>='"+sf.format(sevendays)+" 00:00:00'";
						sql+=" AND Place_order_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==3) {
						sql+=" AND Place_order_time>='"+sf.format(month)+" 00:00:00'";
						sql+=" AND Place_order_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0){
						return Integer.parseInt(list.get(0).get("conversionOrder").toString());
					}
					return 0;
				}
				//交易转换率中付款量
				public Integer materielConversionPayment(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					Date yesterday=new Date(AnalysisDate.getTime()- 24*60*60*1000);
					Date sevendays=new Date(AnalysisDate.getTime()- 24*60*60*1000*7);
					Date month=new Date(AnalysisDate.getTime()- 24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql ="select count(*) as conversionPayment from md_order_info where Process_status=3";
					if (value==0) {
						sql+=" AND Place_order_time>='"+sf.format(AnalysisDate)+" 00:00:00'";
						sql+=" AND Place_order_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==1) {
						sql+=" AND Place_order_time>='"+sf.format(yesterday)+" 00:00:00'";
						sql+=" AND Place_order_time<='"+sf.format(yesterday)+" 23:59:59'";
					}
					if (value==2) {
						sql+=" AND Place_order_time>='"+sf.format(sevendays)+" 00:00:00'";
						sql+=" AND Place_order_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					if (value==3) {
						sql+=" AND Place_order_time>='"+sf.format(month)+" 00:00:00'";
						sql+=" AND Place_order_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0){
						return Integer.parseInt(list.get(0).get("conversionPayment").toString());
					}
					return 0;
				}
				
				//商品排行榜 top10
				public List<Map<String, Object>> getOrderMxListmaterielTop(Integer limit, Integer page) throws HSKDBException {
					String sql = "SELECT mm.wms_mi_id AS wmsiId, mm.mat_name AS matName,mm.number1 AS matNumber,mx.`Total_money` AS TotalMoney FROM md_materiel_info mm LEFT JOIN md_order_mx mx ON mm.wms_mi_id = mx.wms_mi_id WHERE mm.state=1";
					sql+=" GROUP BY mm.number1 DESC";
					if(limit != null && page != null) {
						sql += " limit " + (page - 1) * limit + "," + limit;
					}
					return this.getJdbcDao().query(sql);
				}
				//商品排行榜中收藏数据
				public List<Map<String, String>> getOrderMxListmaterielTopFavorites(String wmsiId) throws HSKDBException{
					String sql = "SELECT COUNT(wms_mi_id) AS wmsMiId FROM md_favorites WHERE 1=1 ";
					sql+="AND wms_mi_id='"+wmsiId+"'";
					return this.getJdbcDao().query(sql);
				}
				//仓管申领入库次数
				public Integer getOrderWarehouseCount(Integer SuiId) throws HSKDBException{
					String sql="SELECT COUNT(*) AS warehouseCount  FROM md_enter_warehouse WHERE 1=1";
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0){
						return Integer.parseInt(list.get(0).get("warehouseCount").toString());
					}
					return 0;
				}
				//仓管申领入库金额
				public Double getOrderWarehouseMoney(Integer SuiId) throws HSKDBException{
					String sql="SELECT SUM(mat_number*price) AS warehouseMonry  FROM md_enter_warehouse_mx WHERE 1=1";
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0){
						return Double.parseDouble(list.get(0).get("warehouseMonry").toString());
					}
					return 0.0000;
				}
				//仓管申领次数
				public Integer getOrderApplyCount(Integer SuiId) throws HSKDBException{
					String sql="SELECT COUNT(*) AS applyCount FROM md_out_warehouse WHERE 1=1";
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0){
						return Integer.parseInt(list.get(0).get("applyCount").toString());
					}
					return 0;
				}
				//仓管申领金额
				public Double getOrderApplyMoney(Integer SuiId) throws HSKDBException{
					String sql="SELECT SUM(base_number*price) AS applyMonry  FROM md_out_warehouse_mx WHERE 1=1";
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0){
						return Double.parseDouble(list.get(0).get("applyMonry").toString());
					}
					return 0.0000;
				}
				//仓管退款退货次数
				public Integer getOrderRetreatCount(Integer SuiId) throws HSKDBException{
					String sql="select count(*) as retreatCount from md_order_info where Process_status=6";
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0){
						return Integer.parseInt(list.get(0).get("retreatCount").toString());
					}
					return 0;
				}
				//仓管退款退货金额
				public Double getOrderRetreatMoney(Integer SuiId) throws HSKDBException{
					String sql="SELECT SUM(place_order_money) AS retreatMoney FROM md_order_info WHERE Process_status=6";
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0){
						return Double.parseDouble(list.get(0).get("retreatMoney").toString());
					}
					return 0.0000;
				}
				//新老客户中 新客户付款金额
				public Double NewSumMoney(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					//一个月前的时间那一天
					Date month=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10);
					//两个月前的那一天
					Date month2=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql="SELECT SUM(mo.place_order_money) AS placeOrderMoney FROM sys_user_info su LEFT JOIN md_order_info mo ON su.`sui_id`=mo.`purchase_id` WHERE mo.moi_id IS NOT NULL";	
					if (value==0) {
						sql+=" AND su.create_date>='"+sf.format(month)+" 00:00:00'";
						sql+=" AND su.create_date<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}if (value==1) {
						sql+=" AND su.create_date>='"+sf.format(month2)+" 00:00:00'";
						sql+=" AND su.create_date<='"+sf.format(month)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0&&list.get(0).get("placeOrderMoney")!=null){
							return Double.parseDouble(list.get(0).get("placeOrderMoney").toString());
					}
				return 0.0000;
				};
				//新老客户中 上个月新客户付款金额 
				public Double TopNewSumMoney(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					//一个月前的时间那一天
					Date month=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10);
					//两个月前时间那一天
					Date month2=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10);
					//三个月个月前时间那一天
					Date month3=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql="SELECT SUM(mo.place_order_money) AS placeOrderMoney FROM sys_user_info su LEFT JOIN md_order_info mo ON su.`sui_id`=mo.`purchase_id` WHERE mo.moi_id IS NOT NULL";	
					if (value==0) {
						sql+=" AND su.create_date>='"+sf.format(month2)+" 00:00:00'";
						sql+=" AND su.create_date<='"+sf.format(month)+" 23:59:59'";
					}
					if (value==1) {
						sql+=" AND su.create_date>='"+sf.format(month2)+" 00:00:00'";
						sql+=" AND su.create_date<='"+sf.format(month2)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0&&list.get(0).get("placeOrderMoney")!=null){
						return Double.parseDouble(list.get(0).get("placeOrderMoney").toString());
					}
					return 0.0000;
				};
				//新老客户中 新客户付款人数
				public Integer NewSumCount(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					//一个月前的时间那一天
					Date month=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10);
					//两个月前的那一天
					Date month2=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql="SELECT COUNT(mo.`purchase_id`) AS purchaseId FROM sys_user_info su LEFT JOIN md_order_info mo ON su.`sui_id`=mo.`purchase_id` WHERE mo.moi_id IS NOT NULL";	
					if (value==0) {
						sql+=" AND su.create_date>='"+sf.format(month)+" 00:00:00'";
						sql+=" AND su.create_date<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}if (value==1) {
						sql+=" AND su.create_date>='"+sf.format(month2)+" 00:00:00'";
						sql+=" AND su.create_date<='"+sf.format(month)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0&&list.get(0).get("purchaseId")!=null){
						return Integer.parseInt(list.get(0).get("purchaseId").toString());
					}
				return 0;
				}
				//新老客户中 新客户上个月付款人数
				public Integer TopNewSumCount(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					//一个月前的时间那一天
					Date month=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10);
					//两个月前时间那一天
					Date month2=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10);
					//三个月付款那一天
					Date month3=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql="SELECT COUNT(mo.`purchase_id`) AS purchaseId FROM sys_user_info su LEFT JOIN md_order_info mo ON su.`sui_id`=mo.`purchase_id` WHERE mo.moi_id IS NOT NULL";	
					sql+=" AND su.create_date>='"+sf.format(month2)+" 00:00:00'";
					sql+=" AND su.create_date<='"+sf.format(month)+" 23:59:59'";
					if (value==0) {
						sql+=" AND su.create_date>='"+sf.format(month2)+" 00:00:00'";
						sql+=" AND su.create_date<='"+sf.format(month)+" 23:59:59'";
					}if (value==1) {
						sql+=" AND su.create_date>='"+sf.format(month3)+" 00:00:00'";
						sql+=" AND su.create_date<='"+sf.format(month2)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0&&list.get(0).get("purchaseId")!=null){
						return Integer.parseInt(list.get(0).get("purchaseId").toString());
					}
					return 0;
				}
				//新老客户中 老客户付款金额
				public Double OldSumMoney(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					Date month=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10);
					//两个月前的那一天
					Date month2=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql=" SELECT SUM(mo.place_order_money) AS placeOrderMoney FROM sys_user_info su LEFT JOIN md_order_info mo ON su.`sui_id`=mo.`purchase_id` AND mo.`moi_id` IS NOT NULL";
					sql+=" AND su.create_date<='"+ sf.format(month) +"23:59:59'";
					if (value==0) {
						sql+=" AND mo.Place_order_time>='"+sf.format(month)+" 00:00:00'";
						sql+=" AND mo.Place_order_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}if (value==1) {
						sql+=" AND mo.Place_order_time>='"+sf.format(month2)+" 00:00:00'";
						sql+=" AND mo.Place_order_time<='"+sf.format(month)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0&&list.get(0).get("placeOrderMoney")!=null){
						return Double.parseDouble(list.get(0).get("placeOrderMoney").toString());
					}
					return 0.0000;
				}
				//新老客户中 上个月老客户付款金额 
				public Double TopOldSumMoney(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					Date month=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10);
					//两个月前时间那一天
					Date month2=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10);
					//三个月付款那一天
					Date month3=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql=" SELECT SUM(mo.place_order_money) AS placeOrderMoney FROM sys_user_info su LEFT JOIN md_order_info mo ON su.`sui_id`=mo.`purchase_id` AND mo.`moi_id` IS NOT NULL";
					sql+=" AND su.create_date<='"+sf.format(month)+"23:59:59'";
					if (value==0) {
						sql+=" AND mo.Place_order_time>='"+sf.format(month2)+" 00:00:00'";
						sql+=" AND mo.Place_order_time<='"+sf.format(month)+" 23:59:59'";
					}if (value==1) {
						sql+=" AND mo.Place_order_time>='"+sf.format(month3)+" 00:00:00'";
						sql+=" AND mo.Place_order_time<='"+sf.format(month2)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0&&list.get(0).get("placeOrderMoney")!=null){
						return Double.parseDouble(list.get(0).get("placeOrderMoney").toString());
					}
					return 0.0000;
				}
				//新老客户中 老客户付款人数
				public Integer OldSumCount(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					Date month=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10);
					//两个月前的那一天
					Date month2=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql=" SELECT COUNT(mo.`purchase_id`) AS purchaseId FROM sys_user_info su LEFT JOIN md_order_info mo ON su.`sui_id`=mo.`purchase_id` AND mo.`moi_id` IS NOT NULL";
					sql+=" AND su.create_date<='"+sf.format(month)+"23:59:59'";
					if (value==0) {
						sql+=" AND mo.Place_order_time>='"+sf.format(month)+" 00:00:00'";
						sql+=" AND mo.Place_order_time<='"+sf.format(AnalysisDate)+" 23:59:59'";
					}if (value==1) {
						sql+=" AND mo.Place_order_time>='"+sf.format(month2)+" 00:00:00'";
						sql+=" AND mo.Place_order_time<='"+sf.format(month)+" 23:59:59'";
					}
//					System.out.println("老客户付款+"+sql);
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					System.out.println("list+"+list);
					if(list != null && list.size() > 0&&list.get(0).get("purchaseId")!=null){
						return Integer.parseInt(list.get(0).get("purchaseId").toString());
					}
					return 0;
				}
				//新老客户中 上个月老客户付款人数
				public Integer TopOldSumCount(Integer value) throws HSKDBException{
					Date AnalysisDate=new Date();
					Date month=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10);
					//两个月前时间那一天
					Date month2=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10);
					//三个月付款那一天
					Date month3=new Date(AnalysisDate.getTime()-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10);
					SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
					String sql=" SELECT COUNT(mo.`purchase_id`) AS purchaseId FROM sys_user_info su LEFT JOIN md_order_info mo ON su.`sui_id`=mo.`purchase_id` AND mo.`moi_id` IS NOT NULL";
					sql+=" AND su.create_date<='"+sf.format(month)+"23:59:59'";
					if (value==0) {
						sql+=" AND mo.Place_order_time>='"+sf.format(month2)+" 00:00:00'";
						sql+=" AND mo.Place_order_time<='"+sf.format(month)+" 23:59:59'";
					}if (value==1) {
						sql+=" AND mo.Place_order_time>='"+sf.format(month3)+" 00:00:00'";
						sql+=" AND mo.Place_order_time<='"+sf.format(month2)+" 23:59:59'";
					}
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0&&list.get(0).get("purchaseId")!=null){
						return Integer.parseInt(list.get(0).get("purchaseId").toString());
					}
					return 0;
				}
				//集团用户统计
				public Integer groupCount(Integer suiId) throws HSKDBException{
					String sql="SELECT COUNT(*) AS groupCount FROM  md_company_group";
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0&&list.get(0).get("groupCount")!=null){
						return Integer.parseInt(list.get(0).get("groupCount").toString());
					}
					return 0;
					
				}
				//医院用户统计
				public Integer hospitalCount(Integer suiId) throws HSKDBException{
					String sql="SELECT COUNT(*)  as hospitalCount FROM  md_dentist_hospital";
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0&&list.get(0).get("hospitalCount")!=null){
						return Integer.parseInt(list.get(0).get("hospitalCount").toString());
					}
					return 0;
				}
				//门诊用户统计
				public Integer departmentCount(Integer suiId) throws HSKDBException{
					String sql="SELECT COUNT(*) as departmentCount FROM  md_dental_clinic";
					List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
					if(list != null && list.size() > 0&&list.get(0).get("departmentCount")!=null){
						return Integer.parseInt(list.get(0).get("departmentCount").toString());
					}
					return 0;
				}

	@Override
	public Integer countOrderInfoHasAfterSale(Integer suiId, Integer moiId) throws HSKDBException {
		String sql = "SELECT COUNT( DISTINCT a.moi_id ) as count FROM md_order_after_sale a LEFT JOIN md_order_info b ON a.moi_id = b.moi_id where 1=1";
		if (moiId != null && !moiId.equals("")){
			sql += " and a.moi_id = " + moiId;
		}
		if (suiId != null) {
			sql += " and a.sui_id = " + suiId;
		}
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list.size() <= 0) {
			return 0;
		}
		Map<String, Object> map = list.get(0);
		if (map == null) {
			return 0;
		}
		return Integer.parseInt(map.get("count").toString());
	}
	@Override
	public Integer countOrderInfoHasAfterSaleTwo(Integer suiId, Integer moiId) throws HSKDBException {
		String sql = "SELECT COUNT( DISTINCT a.moi_id ) as count FROM md_order_after_sale a LEFT JOIN md_order_info b ON a.moi_id = b.moi_id where 1=1";
		if (moiId != null && !moiId.equals("")){
			sql += " and a.moi_id = " + moiId;
		}
		if (suiId != null) {
			sql += " and b.wz_id = " + suiId;
		}
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list.size() <= 0) {
			return 0;
		}
		Map<String, Object> map = list.get(0);
		if (map == null) {
			return 0;
		}
		return Integer.parseInt(map.get("count").toString());
	}

	@Override
	public List<Map<String, Object>> findMdOrderInfoTotalMoneyById(Integer moiId) throws HSKDBException {
		String hql = "select SUM(total_money) as total_money from md_order_mx where 1=1";
		if(moiId != null && !moiId.equals("")){
			hql += " and moi_id=" + moiId;
		}
		return this.getJdbcDao().query(hql);
	}

	@Override
	public String getLimitDate(Integer suiId, Integer order) throws HSKDBException {
		String sql ="SELECT create_date AS create_date  FROM md_order_info WHERE order_state='1' and purchase_id=" + suiId;
		if (order != null && order == 2){
			sql += " order by create_date desc";
		}
		sql += " limit 0,1";
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if (list.size() <= 0)
			return null;
		Map<Object, Object> map = list.get(0);
		if (map == null){
			return null;
		}

		return map.get("create_date").toString();
	}

	@Override
	public List<MdOrderInfo> findMdOrderInfoByIds(String moiId) throws HSKDBException {
		String hql = "from MdOrderInfo where 1=1";
		if (moiId != null && !moiId.equals("")){
			hql += " and moiId in (" + moiId + ")";
		}
		PagerModel pm = this.getHibernateDao().findByPage(hql);
		return pm.getItems();
	}
	//业务员查询本业务员下面机构订单
	public List<Map<String, Object>> getSalesmanOrderList(Integer limit, Integer page,String processStatus,String orderCode,String purchaseUnit,String placeOrderTimeStart,String placeOrderTimeEnd,String keyword,Integer salesmanId) throws HSKDBException {
		Date PlaceOrderDate=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String sql="SELECT\n" +
				"t5.sales_name,\n" +
				"t5.sales_type,\n" +
				"t2.sales_name AS cs_name,\n" +
				"t3.sales_name AS hs_name,\n" +
				"t4.sales_name AS cls_name,\n" +
				"t1.settlement,\n" +
				"t1.moi_id AS moi_id,\n" +
				"t1.rba_id,\n" +
				"t1.rbs_id,\n" +
				"t1.rbb_id,\n" +
				"t1.loan_log,\n" +
				"t1.Process_status AS process_status,\n" +
				"t1.order_code,\n" +
				"t1.place_order_time,\n" +
				"t1.pay_type AS pay_type,\n" +
				"t1.pay_code,\n" +
				"t1.pay_date,\n" +
				"t1.place_order_money,\n" +
				"t1.actual_money,\n" +
				"t1.purchase_unit,\n" +
				"t1.applicant_name,\n" +
				"t1.loan_state,\n" +
				"t1.loan_money,\n" +
				"t1.settlement,(SELECT file_code  FROM sys_file_info t6 WHERE t6.file_code=t2.logo) as rbalogo,\n" +
				"(SELECT file_code  FROM sys_file_info t6 WHERE t6.file_code=t3.logo) AS rbslogo,\n" +
				"(SELECT file_code  FROM sys_file_info t6 WHERE t6.file_code=t4.logo) AS rbblogo \n" +
				"FROM\n" +
				"md_order_info t1\n" +
				"LEFT JOIN md_company_group t2 ON t1.rba_id = t2.rba_id\n" +
				"LEFT JOIN md_dentist_hospital t3 ON t3.rbs_id = t1.rbs_id\n" +
				"LEFT JOIN md_dental_clinic t4 ON t4.rbb_id = t1.rbb_id,\n" +
				"sys_sales_man t5 \n" +
				"WHERE\n" +
				"( t5.salesman_id IN ( t2.salesman_ids ) OR t5.salesman_id IN ( t3.salesman_ids ) OR t5.salesman_id IN ( t4.salesman_ids ) ) ";
		if(salesmanId!=null){
			sql+=" AND t5.salesman_id="+salesmanId;
		}
		if (processStatus!=null&&!processStatus.equals("")){
			sql+=" AND t1.Process_status in ("+processStatus+")";
		}
		if (orderCode!=null&&!orderCode.equals("")){
			sql+=" AND t1.order_code like '%"+orderCode+"%'";
		}
		if (purchaseUnit!=null&&!purchaseUnit.equals("")){
			sql+=" AND t1.purchase_unit like '%"+purchaseUnit+"%'";
		}
		if (placeOrderTimeStart!=null&&!placeOrderTimeStart.equals("")){
			sql+=" AND t1.place_order_time>='"+placeOrderTimeStart+" 00:00:00'";
		}
		if (placeOrderTimeEnd!=null&&!placeOrderTimeEnd.equals("")){
			sql+=" AND t1.place_order_time<='"+placeOrderTimeEnd+" 23:59:59'";
		}
		if (keyword!=null&&!keyword.equals("")){
			sql+=" AND (t1.order_code like '%"+keyword.toUpperCase()+"%'  or t1.purchase_unit like '%"+keyword.toUpperCase()+"%' )";
		}
		sql+=" ORDER BY t1.place_order_time DESC";
		if(limit != null && page != null) {
			sql += " limit " + (page - 1) * limit + "," + limit;
		}
		return this.getJdbcDao().query(sql);
	}

    //业务员查询账单明细
    public List<Map<String, Object>> getSalesmanOrderBills(Integer limit, Integer page,Integer stateType,String minMoney,String maxMoney,String purchaseUnit,String processStatus,String keyword,String placeOrderTime,Integer salesmanId) throws HSKDBException {
        Date PlaceOrderDate=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        String sql="SELECT\n" +
                "t5.sales_name,\n" +
                "t5.sales_type,\n" +
                "t2.sales_name AS cs_name,\n" +
                "t3.sales_name AS hs_name,\n" +
                "t4.sales_name AS cls_name,\n" +
                "t1.settlement,\n" +
                "t1.moi_id AS moi_id,\n" +
                "t1.rba_id,\n" +
                "t1.rbs_id,\n" +
                "t1.rbb_id,\n" +
                "t1.loan_log,\n" +
                "t1.Process_status AS process_status,\n" +
                "t1.order_code,\n" +
                "t1.place_order_time,\n" +
                "t1.pay_type AS pay_type,\n" +
                "t1.pay_code,\n" +
                "t1.pay_date,\n" +
                "t1.place_order_money,\n" +
                "t1.actual_money,\n" +
                "t1.purchase_unit,\n" +
                "t1.applicant_name,\n" +
                "t1.loan_state,\n" +
                "t1.loan_money,(t1.place_order_money-t7.after_sale_money) AS successMoney,\n" +
                "t1.settlement,(SELECT file_code  FROM sys_file_info t6 WHERE t6.file_code=t2.logo) as rbalogo,\n" +
                "(SELECT file_code  FROM sys_file_info t6 WHERE t6.file_code=t3.logo) AS rbslogo,\n" +
                "(SELECT file_code  FROM sys_file_info t6 WHERE t6.file_code=t4.logo) AS rbblogo \n" +
                "FROM\n" +
                "md_order_info t1\n" +
                "LEFT JOIN md_company_group t2 ON t1.rba_id = t2.rba_id\n" +
                "LEFT JOIN md_dentist_hospital t3 ON t3.rbs_id = t1.rbs_id  LEFT JOIN md_order_after_sale t7  ON t1.moi_id=t7.moi_id\n" +
                "LEFT JOIN md_dental_clinic t4 ON t4.rbb_id = t1.rbb_id,\n" +
                "sys_sales_man t5 \n" +
                "WHERE\n" +
                "( t5.salesman_id IN ( t2.salesman_ids ) OR t5.salesman_id IN ( t3.salesman_ids ) OR t5.salesman_id IN ( t4.salesman_ids ) ) ";
		if (salesmanId!=null){
			sql+=" AND t5.salesman_id= "+salesmanId;
		}
		if (stateType!=null){
			if (stateType==1){
				sql+=" AND t1.Process_status = 5";
			}else if (stateType==2){
				sql+=" AND t1.Process_status = 6";
			}else {
//				sql+=" AND t1.Process_status in (5,6)";
			}

		}
		if (minMoney!=null&&!minMoney.equals("")){
			sql+=" AND t1.place_order_money>"+minMoney;
		}
		if (maxMoney!=null&&!maxMoney.equals("")){
			sql+=" AND t1.place_order_money<"+maxMoney;
		}
		if (purchaseUnit!=null&&!purchaseUnit.equals("")){
			sql+=" AND t1.purchase_unit in( "+purchaseUnit+")";
		}
//        if (salesmanId!=null){
//            sql+=" AND t5.salesman_id ="+salesmanId;
//        }
        if (placeOrderTime!=null&&!placeOrderTime.equals("")){
            sql+=" AND date_format(t1.place_order_time,'%Y-%m')= '"+placeOrderTime+"'";
        }
        if (keyword!=null&&!keyword.equals("")){
            sql+=" AND (t1.order_code like '%"+keyword.toUpperCase()+"%'  or t1.purchase_unit like '%"+keyword.toUpperCase()+"%' )";
        }
        sql+=" ORDER BY t1.place_order_time DESC";
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }
	public List<Map<String, Object>> getSalesmanOrderListMx(Integer moiId) throws HSKDBException{
		String sql=" SELECT t1.rba_id,t1.rbs_id,t1.rbb_id, t1.order_code ,t1.Place_order_time,t1.place_order_money,t2.after_sale_money,(t1.place_order_money-t2.after_sale_money) AS successMoney FROM md_order_info t1 LEFT JOIN md_order_after_sale t2  ON t1.moi_id=t2.moi_id where 1=1";
		if (moiId!=null){
			sql+=" AND t1.moi_id ="+moiId;
		}
		return this.getJdbcDao().query(sql);
	}
	public String getSalesmanStr(Integer rbaId,Integer rbsId,Integer rbbId) throws HSKDBException{
		String sql =" SELECT sales_name FROM";
		if (rbbId != null){
			sql += " md_dental_clinic where rbb_id="+rbbId;
		}
		if (rbsId != null){
			sql += " md_dentist_hospital where rbs_id="+rbsId;
		}
		if (rbaId != null){
			sql += " md_company_group where rba_id="+rbaId;
		}
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if (list.size() <= 0)
			return null;
		Map<Object, Object> map = list.get(0);
		if (map == null){
			return null;
		}
		return map.get("sales_name").toString();
	}

    public Double getSumPlaceOrderMoney(Integer salesmanId,String placeOrderTime,String year) throws HSKDBException{
					String sql="";
					if (placeOrderTime!=null&&!placeOrderTime.equals("")){
						sql = "SELECT (CASE \n" +
								"WHEN  NOT ISNULL(place_order_money) THEN\n" +
								"SUM(place_order_money)\n" +
								"ELSE\n" +
								"0.0000\n" +
								"END)  AS SumPlaceOrderMoney  FROM md_order_info t1\n" +
								"LEFT JOIN md_company_group t2 ON t1.rba_id = t2.rba_id\n" +
								"LEFT JOIN md_dentist_hospital t3 ON t3.rbs_id = t1.rbs_id\n" +
								"LEFT JOIN md_dental_clinic t4 ON t4.rbb_id = t1.rbb_id,\n" +
								"sys_sales_man t5  WHERE\n" +
								"( t5.salesman_id IN ( t2.salesman_ids ) OR t5.salesman_id IN ( t3.salesman_ids ) OR t5.salesman_id IN ( t4.salesman_ids ) )";
						if (salesmanId!=null){
							sql+=" AND t5.salesman_id="+salesmanId;
						}
						if (placeOrderTime!=null&&!placeOrderTime.equals("")){
							sql+=" AND date_format(t1.place_order_time,'%Y-%m')= '"+placeOrderTime+"'";
						}
					}
					if (year!=null&&!year.equals("")){
							sql = "SELECT (CASE \n" +
									"WHEN  NOT ISNULL(place_order_money) THEN\n" +
									"SUM(place_order_money)\n" +
									"ELSE\n" +
									"0.0000\n" +
									"END)  AS SumPlaceOrderMoney  FROM md_order_info t1\n" +
									"LEFT JOIN md_company_group t2 ON t1.rba_id = t2.rba_id\n" +
									"LEFT JOIN md_dentist_hospital t3 ON t3.rbs_id = t1.rbs_id\n" +
									"LEFT JOIN md_dental_clinic t4 ON t4.rbb_id = t1.rbb_id,\n" +
									"sys_sales_man t5  WHERE\n" +
									"( t5.salesman_id IN ( t2.salesman_ids ) OR t5.salesman_id IN ( t3.salesman_ids ) OR t5.salesman_id IN ( t4.salesman_ids ) )";
							if (salesmanId!=null){
								sql+=" AND t5.salesman_id="+salesmanId;
							}
							if (year!=null&&!year.equals("")){
								sql+=" AND date_format(t1.place_order_time,'%Y')= "+year+"";
							}
					}
					if ((placeOrderTime==null||placeOrderTime.equals(""))&&(year==null||year.equals(""))){
						sql = "SELECT (CASE \n" +
								"WHEN  NOT ISNULL(place_order_money) THEN\n" +
								"SUM(place_order_money)\n" +
								"ELSE\n" +
								"0.0000\n" +
								"END)  AS SumPlaceOrderMoney  FROM md_order_info t1\n" +
								"LEFT JOIN md_company_group t2 ON t1.rba_id = t2.rba_id\n" +
								"LEFT JOIN md_dentist_hospital t3 ON t3.rbs_id = t1.rbs_id\n" +
								"LEFT JOIN md_dental_clinic t4 ON t4.rbb_id = t1.rbb_id,\n" +
								"sys_sales_man t5  WHERE\n" +
								"( t5.salesman_id IN ( t2.salesman_ids ) OR t5.salesman_id IN ( t3.salesman_ids ) OR t5.salesman_id IN ( t4.salesman_ids ) )";
						if (salesmanId!=null){
							sql+=" AND t5.salesman_id="+salesmanId;
						}
					}
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list.size() <= 0) {
			return 0.00;
		}
		Map<String, Object> map = list.get(0);
		if (map == null) {
			return 0.00;
		}
		return Double.parseDouble(map.get("SumPlaceOrderMoney").toString());
	}

	public Double getSumPlaceOrderMoneyYear(Integer salesmanId,String year) throws HSKDBException{
		String sql = "SELECT (CASE \n" +
				"WHEN  NOT ISNULL(place_order_money) THEN\n" +
				"SUM(place_order_money)\n" +
				"ELSE\n" +
				"0.0000\n" +
				"END)  AS SumPlaceOrderMoney  FROM md_order_info t1\n" +
				"LEFT JOIN md_company_group t2 ON t1.rba_id = t2.rba_id\n" +
				"LEFT JOIN md_dentist_hospital t3 ON t3.rbs_id = t1.rbs_id\n" +
				"LEFT JOIN md_dental_clinic t4 ON t4.rbb_id = t1.rbb_id,\n" +
				"sys_sales_man t5  WHERE\n" +
				"( t5.salesman_id IN ( t2.salesman_ids ) OR t5.salesman_id IN ( t3.salesman_ids ) OR t5.salesman_id IN ( t4.salesman_ids ) )";
		if (salesmanId!=null){
			sql+=" AND t5.salesman_id="+salesmanId;
		}
		if (year!=null&&!year.equals("")){
			sql+=" AND year(t1.place_order_time)="+year;
		}
//		sql+=" AND date_format(t1.place_order_time,'%Y-%m')= '"+year+"'";
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list.size() <= 0) {
			return 0.00;
		}
		Map<String, Object> map = list.get(0);
		if (map == null) {
			return 0.00;
		}
		return Double.parseDouble(map.get("SumPlaceOrderMoney").toString());
	}

    public List<Map<String, Object>> getSalesmanVolume(Integer salesmanId,String placeOrderTime,String year) throws HSKDBException {
        Date PlaceOrderDate=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String sql="";
		if (placeOrderTime!=null&&!placeOrderTime.equals("")) {
			 sql = "SELECT\n" +
					"date_format(t1.Place_order_time, '%m-%d') dat, SUM(place_order_money) AS SumPlaceOrderMoney\n" +
					"FROM md_order_info t1\n" +
					"LEFT JOIN md_company_group t2 ON t1.rba_id = t2.rba_id\n" +
					"LEFT JOIN md_dentist_hospital t3 ON t3.rbs_id = t1.rbs_id\n" +
					"LEFT JOIN md_dental_clinic t4 ON t4.rbb_id = t1.rbb_id,\n" +
					"sys_sales_man t5 \n" +
					"WHERE ( t5.salesman_id IN ( t2.salesman_ids ) OR t5.salesman_id IN ( t3.salesman_ids ) OR t5.salesman_id IN ( t4.salesman_ids ) ) ";
			 if (salesmanId!=null){
				 sql += " AND t5.salesman_id=" + salesmanId;
			 }
			if (placeOrderTime != null && !placeOrderTime.equals("")) {
				sql += " AND date_format(t1.place_order_time,'%Y-%m')= '" + placeOrderTime + "'";
			}
			if (year != null && !year.equals("")) {
				sql += " AND year(t1.place_order_time)=" + year;
			}
			sql += " GROUP BY\n" +
					"date_format(t1.Place_order_time, '%m-%d')";
		}
		 if (year != null && !year.equals("")){
			 sql = "SELECT\n" +
					 "date_format(t1.Place_order_time, '%m') dat, SUM(place_order_money) AS SumPlaceOrderMoney\n" +
					 "FROM md_order_info t1\n" +
					 "LEFT JOIN md_company_group t2 ON t1.rba_id = t2.rba_id\n" +
					 "LEFT JOIN md_dentist_hospital t3 ON t3.rbs_id = t1.rbs_id\n" +
					 "LEFT JOIN md_dental_clinic t4 ON t4.rbb_id = t1.rbb_id,\n" +
					 "sys_sales_man t5 \n" +
					 "WHERE ( t5.salesman_id IN ( t2.salesman_ids ) OR t5.salesman_id IN ( t3.salesman_ids ) OR t5.salesman_id IN ( t4.salesman_ids ) ) ";
			 if (salesmanId!=null){
				 sql += " AND t5.salesman_id=" + salesmanId;
			 }
			 if (year != null && !year.equals("")) {
				 sql += " AND year(t1.place_order_time)=" + year;
			 }
			 sql += " GROUP BY\n" +
					 "date_format(t1.Place_order_time, '%m')";
		 }

        return this.getJdbcDao().query(sql);
    }

    public List<Map<String, Object>> getSalesmanMonthCount(Integer salesmanId,String placeOrderTime,String year) throws HSKDBException {
        Date PlaceOrderDate=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        String sql="SELECT t1.Purchase_unit as PurchaseUnit, SUM(place_order_money) AS SumPlaceOrderMoney\n" +
                "FROM md_order_info t1\n" +
                "LEFT JOIN md_company_group t2 ON t1.rba_id = t2.rba_id\n" +
                "LEFT JOIN md_dentist_hospital t3 ON t3.rbs_id = t1.rbs_id\n" +
                "LEFT JOIN md_dental_clinic t4 ON t4.rbb_id = t1.rbb_id,\n" +
                "sys_sales_man t5 \n" +
                "WHERE ( t5.salesman_id IN ( t2.salesman_ids ) OR t5.salesman_id IN ( t3.salesman_ids ) OR t5.salesman_id IN ( t4.salesman_ids ) ) ";
		if (salesmanId!=null){
			sql += " AND t5.salesman_id=" + salesmanId;
		}
		if (placeOrderTime!=null&&!placeOrderTime.equals("")){
			sql+=" AND date_format(t1.place_order_time,'%Y-%m')= '"+placeOrderTime+"'";
		}
		if (year!=null&&!year.equals("")){
			sql+=" AND year(t1.place_order_time)="+year;
		}
        sql+=" GROUP BY\n" +
                "t1.Purchase_unit";
        return this.getJdbcDao().query(sql);
    }

    public String getSalesmanCode(Integer salesmanId) throws HSKDBException{
			String sql=" SELECT t5.sales_code AS salesCode  FROM sys_sales_man t5 WHERE 1=1";
			if (salesmanId!=null){
			    sql+=" AND t5.salesman_id="+salesmanId;
            }
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if (list.size() <= 0)
            return null;
        Map<Object, Object> map = list.get(0);
        if (map == null){
            return null;
        }
        return map.get("salesCode").toString();
    }

	public String getSalesmanArea(Integer salesmanId) throws HSKDBException{
		String sql=" SELECT t5.sales_area AS salesCode  FROM sys_sales_man t5 WHERE 1=1";
		if (salesmanId!=null){
			sql+=" AND t5.salesman_id="+salesmanId;
		}
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if (list.size() <= 0)
			return null;
		Map<Object, Object> map = list.get(0);
		if (map == null){
			return null;
		}
		return map.get("salesArea").toString();
	}


	public Integer getSalesmanInstitutionsCount(Integer salesmanId) throws HSKDBException{
		String sql = "SELECT\n" +
				"\tCOUNT(*) AS institutionsCount\n" +
				"FROM\n" +
				"\t(\n" +
				"\tSELECT\n" +
				"\t\tsales_name,\n" +
				"\t\tagent_company,\n" +
				"\t\tNULL AS rba_name,\n" +
				"\t\tNULL AS rbs_name,\n" +
				"\t\tNULL AS rbb_name,\n" +
				"\t\tcreate_date,\n" +
				"\t\tstate \n" +
				"\tFROM\n" +
				"\t\tsys_sales_man \n" +
				"\tWHERE\n" +
				"\t\tleader_id =  "+salesmanId+" UNION ALL\n" +
				"\tSELECT NULL AS\n" +
				"\t\tsales_name,\n" +
				"\t\tNULL AS agent_company,\n" +
				"\t\trba_name,\n" +
				"\t\tNULL AS rbs_name,\n" +
				"\t\tNULL AS rbb_name,\n" +
				"\t\tcreate_date,\n" +
				"\t\tstate \n" +
				"\tFROM\n" +
				"\t\tmd_company_group \n" +
				"\tWHERE\n" +
				"\t\tif("+salesmanId+" is null, 1=1,FIND_IN_SET(  "+salesmanId+", salesman_ids )) UNION ALL\n" +
				"\tSELECT NULL AS\n" +
				"\t\tsales_name,\n" +
				"\t\tNULL AS agent_company,\n" +
				"\t\tNULL AS rba_name,\n" +
				"\t\trbs_name,\n" +
				"\t\tNULL AS rbb_name,\n" +
				"\t\tcreate_date,\n" +
				"\t\tstate \n" +
				"\tFROM\n" +
				"\t\tmd_dentist_hospital \n" +
				"\tWHERE\n" +
				"\t\tif("+salesmanId+" is null, 1=1,FIND_IN_SET(  "+salesmanId+", salesman_ids )) UNION ALL\n" +
				"\tSELECT NULL AS\n" +
				"\t\tsales_name,\n" +
				"\t\tNULL AS agent_company,\n" +
				"\t\tNULL AS rba_name,\n" +
				"\t\tNULL AS rbs_name,\n" +
				"\t\trbb_name,\n" +
				"\t\tcreate_date,\n" +
				"\t\tstate \n" +
				"\tFROM\n" +
				"\t\tmd_dental_clinic \n" +
				"\tWHERE\n" +
				"\t\tif("+salesmanId+" is null, 1=1,FIND_IN_SET(  "+salesmanId+", salesman_ids )) \n" +
				"\t) AS a \n" +
				"WHERE\n" +
				"\t1 = 1 AND  (rba_name IS NOT NULL OR rbs_name IS NOT NULL OR rbb_name IS NOT NULL)";
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list.size() <= 0) {
			return 0;
		}
		Map<String, Object> map = list.get(0);
		if (map == null) {
			return 0;
		}
		return Integer.parseInt(map.get("institutionsCount").toString());
	}
	public Integer getSalesmanAgentCount(Integer salesmanId) throws HSKDBException{
		String sql =" SELECT COUNT(*) AS agentCount FROM sys_sales_man syssalesma0_ WHERE 1=1";
		if (salesmanId!=null){
			sql+=" AND syssalesma0_.leader_id ="+salesmanId;
		}
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list.size() <= 0) {
			return 0;
		}
		Map<String, Object> map = list.get(0);
		if (map == null) {
			return 0;
		}
		return Integer.parseInt(map.get("agentCount").toString());
	}
	public PagerModel getSalesmanCountList(Integer limit,Integer page,Integer salesmanId) throws HSKDBException{
//					if (limit==null){
//						limit=1000;
//					}
//					if (page==null){
//						page=1000;
//					}
					String sql="SELECT\n" +
							" * \n" +
							"FROM\n" +
							" (\n" +
							" SELECT\n" +
							"  sales_name,\n" +
							"  agent_company,\n" +
							"  NULL AS rba_name,\n" +
							"  NULL AS rbs_name,\n" +
							"  NULL AS rbb_name,\n" +
							"  NULL AS rba_id,\n" +
							"  NULL AS rbs_id,\n" +
							"  NULL AS rbb_id,\n" +
							"  create_date,\n" +
							"  NULL AS Place_order_time,\n" +
							"  NULL AS lackDate,\n" +
							"  state \n" +
							" FROM\n" +
							"  sys_sales_man \n" +
							" WHERE\n" +
							"  leader_id = "+salesmanId+" UNION ALL\n" +
							" SELECT NULL AS\n" +
							"  sales_name,\n" +
							"  NULL AS agent_company,\n" +
							"  rba_name,\n" +
							"  NULL AS rbs_name,\n" +
							"  NULL AS rbb_name,\n" +
							"  t1.rba_id,\n" +
							"  NULL AS rbs_id,\n" +
							"  NULL AS rbb_id,\n" +
							"  t1.create_date,\n" +
							"  max( t2.Place_order_time ) AS Place_order_time,\n" +
							"  DATEDIFF( date_format( now(), '%Y-%m-%d' ), max( t2.Place_order_time ) ) AS lackDate,\n" +
							"  state \n" +
							" FROM\n" +
							"  md_company_group t1\n" +
							"  LEFT JOIN md_order_info t2 ON t1.rba_id = t2.rba_id \n" +
							" WHERE\n" +
							"  if("+salesmanId+" is null, 1=1,FIND_IN_SET( "+salesmanId+", salesman_ids )) \n" +
							" GROUP BY\n" +
							"  t1.rba_id UNION ALL\n" +
							" SELECT NULL AS\n" +
							"  sales_name,\n" +
							"  NULL AS agent_company,\n" +
							"  NULL AS rba_name,\n" +
							"  rbs_name,\n" +
							"  NULL AS rbb_name,\n" +
							"  NULL AS rba_id,\n" +
							"  t1.rbs_id,\n" +
							"  NULL AS rbb_id,\n" +
							"  t1.create_date,\n" +
							"  max( t2.Place_order_time ) AS Place_order_time,\n" +
							"  DATEDIFF( date_format( now(), '%Y-%m-%d' ), max( t2.Place_order_time ) ) AS lackDate,\n" +
							"  state \n" +
							" FROM\n" +
							"  md_dentist_hospital t1\n" +
							"  LEFT JOIN md_order_info t2 ON t1.rbs_id = t2.rbs_id \n" +
							" WHERE\n" +
							"  if("+salesmanId+" is null, 1=1,FIND_IN_SET( "+salesmanId+", salesman_ids )) \n" +
							" GROUP BY\n" +
							"  t1.rbs_id UNION ALL\n" +
							" SELECT NULL AS\n" +
							"  sales_name,\n" +
							"  NULL AS agent_company,\n" +
							"  NULL AS rba_name,\n" +
							"  NULL AS rbs_name,\n" +
							"  rbb_name,\n" +
							"  NULL AS rba_id,\n" +
							"  NULL AS rbs_id,\n" +
							"  t1.rbb_id,\n" +
							"  t1.create_date,\n" +
							"  max( t2.Place_order_time ) AS Place_order_time,\n" +
							"  DATEDIFF( date_format( now(), '%Y-%m-%d' ), max( t2.Place_order_time ) ) AS lackDate,\n" +
							"  state \n" +
							" FROM\n" +
							"  md_dental_clinic t1\n" +
							"  LEFT JOIN md_order_info t2 ON t1.rbb_id = t2.rbb_id \n" +
							" WHERE\n" +
							"  if("+salesmanId+" is null, 1=1,FIND_IN_SET( "+salesmanId+", salesman_ids )) \n" +
							" GROUP BY\n" +
							"  t1.rbb_id \n" +
							" ) AS a \n" +
							"WHERE\n" +
							" 1 = 1 \n" +
							" AND (\n" +
							"  rba_id IS NOT NULL \n" +
							"  OR rbs_id IS NOT NULL \n" +
							" OR rbb_id IS NOT NULL \n" +
							" ) ";
		return this.getJdbcDao().findByPage(sql);
	}
	public List<Map<String, Object>> getAgentCountList(Integer salesmanId,Integer limit,Integer page) throws HSKDBException{
			String sql=" SELECT \n" +
					"syssalesma0_.salesman_id AS salesmanId,\n" +
					"syssalesma0_.agent_company AS agentName FROM sys_sales_man syssalesma0_ WHERE\n" +
					"\t1 = 1 ";
			if (salesmanId!=null){
				sql+=" AND syssalesma0_.leader_id ="+salesmanId;
			}
			sql+=" ORDER BY\n" +
					"\tsyssalesma0_.create_date DESC ";
		if(limit != null && page != null) {
			sql += " limit " + (page - 1) * limit + "," + limit;
		}
		return this.getJdbcDao().query(sql);
	}
//	public Integer getSalesmanAgentListCount(Integer salesmanId) throws HSKDBException{
//		String sql=" ";
//		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
//		if (list.size() <= 0) {
//			return 0;
//		}
//		Map<String, Object> map = list.get(0);
//		if (map == null) {
//			return 0;
//		}
//		return Integer.parseInt(map.get("agentCount").toString());
//	}
}