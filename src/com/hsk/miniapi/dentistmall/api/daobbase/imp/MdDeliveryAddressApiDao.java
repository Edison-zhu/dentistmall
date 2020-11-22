package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.util.*;
import com.hsk.supper.dto.comm.PagerModel;
import org.hibernate.FlushMode;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_delivery_address表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:22:54
 */
@Component
public class MdDeliveryAddressApiDao extends SupperDao implements IMdDeliveryAddressApiDao {

	/**
	 * 根据md_delivery_address表主键查找MdDeliveryAddress对象记录
	 * 
	 * @param  MdaId  Integer类型(md_delivery_address表主键)
	 * @return MdDeliveryAddress md_delivery_address表记录
	 * @throws HSKDBException
	 */	
	public MdDeliveryAddress findMdDeliveryAddressById(Integer MdaId) throws HSKDBException{
				MdDeliveryAddress  att_MdDeliveryAddress=new MdDeliveryAddress();				
				if(MdaId!=null){
					att_MdDeliveryAddress.setMdaId(MdaId);	
				    Object obj=	this.getOne(att_MdDeliveryAddress);
					if(obj!=null){
						att_MdDeliveryAddress=(MdDeliveryAddress) obj;
					}
				}
				return  att_MdDeliveryAddress;
	}
	 /**
	  * 根据md_delivery_address表主键删除MdDeliveryAddress对象记录
	  * @param  MdaId  Integer类型(md_delivery_address表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdDeliveryAddressById(Integer MdaId) throws HSKDBException{ 
		 MdDeliveryAddress  att_MdDeliveryAddress=new MdDeliveryAddress();	
		  if(MdaId!=null){
					  att_MdDeliveryAddress.setMdaId(MdaId);
				  	  Object obj=	this.getOne(att_MdDeliveryAddress);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_delivery_address表主键修改MdDeliveryAddress对象记录
     * @param  MdaId  Integer类型(md_delivery_address表主键)
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
     * @return MdDeliveryAddress  修改后的MdDeliveryAddress对象记录
	 * @throws HSKDBException
	 */
	public  MdDeliveryAddress updateMdDeliveryAddressById(Integer MdaId,MdDeliveryAddress att_MdDeliveryAddress) throws HSKDBException{
		  if(MdaId!=null){
					att_MdDeliveryAddress.setMdaId(MdaId);
				   	Object obj=	this.getOne(att_MdDeliveryAddress);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdDeliveryAddress;
	}
	
	/**
	 * 新增md_delivery_address表 记录
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdDeliveryAddress(MdDeliveryAddress att_MdDeliveryAddress) throws HSKDBException{
		 return this.newObject(att_MdDeliveryAddress);
	} 
		
	/**
	 * 新增或修改md_delivery_address表记录 ,如果md_delivery_address表主键MdDeliveryAddress.MdaId为空就是添加，如果非空就是修改
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
	 * @throws HSKDBException
	 */
	public  MdDeliveryAddress saveOrUpdateMdDeliveryAddress(MdDeliveryAddress att_MdDeliveryAddress) throws HSKDBException{
//		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		att_MdDeliveryAddress = (MdDeliveryAddress) getHibernatesession().merge(att_MdDeliveryAddress);
//		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.COMMIT);
		this.getHibernateTemplate().saveOrUpdate(att_MdDeliveryAddress);
		  return att_MdDeliveryAddress;
	}

	@Override
	public void updateMdDeliveryAddressDefualt(String excludeMaIds) throws HSKDBException {
		if (excludeMaIds == null || excludeMaIds.equals("")) {
			return;
		}
		String sql = "update md_delivery_address set if_default = 2 where mda_id not in (" + excludeMaIds + ")";
		this.getJdbcDao().execute(sql);
	}

	/**
	 *根据MdDeliveryAddress对象作为对(md_delivery_address表进行查询，获取列表对象
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
     * @return List<MdDeliveryAddress>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdDeliveryAddress> getListByMdDeliveryAddress(MdDeliveryAddress att_MdDeliveryAddress) throws HSKDBException{
		String Hql=this.getHql( att_MdDeliveryAddress); 
		List<MdDeliveryAddress> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	/**
	 *根据MdDeliveryAddress对象作为对(md_delivery_address表进行查询，获取分页对象
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
     * @return List<MdDeliveryAddress>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdDeliveryAddress(MdDeliveryAddress att_MdDeliveryAddress)
			throws HSKDBException {
		String Hql=this.getHql(att_MdDeliveryAddress);
		return this.getHibernateDao().findByPage(Hql); 
	} 
	/**
	 * 根据MdDeliveryAddress对象获取Hql字符串
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
	 *  @return hql字符串
	 */
	private String  getHql(MdDeliveryAddress att_MdDeliveryAddress){
			 StringBuffer sbuffer = new StringBuffer( " from  MdDeliveryAddress  where  1=1  ");
			 String likeStr=  att_MdDeliveryAddress.getTab_like();
			 String orderStr= att_MdDeliveryAddress.getTab_order();
			 
			 //*****************无关字段处理begin*****************
						   		 //处理in条件 收货地址id(mdaId)
							     if(att_MdDeliveryAddress.getMdaId_str()!=null&&
						   		    !"".equals(att_MdDeliveryAddress.getMdaId_str().trim())){ 
											 String  intStr=att_MdDeliveryAddress.getMdaId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  mdaId="+did+"   "); 
													 }else {
													 sbuffer.append("  mdaId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
								 		//时间类型开始条件处理  创建时间(createDate)
									  if(att_MdDeliveryAddress.getCreateDate_start()!=null){
								   	    	sbuffer.append( " and  createDate<='" +att_MdDeliveryAddress.getCreateDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 创建时间(createDate)
									 	if(att_MdDeliveryAddress.getCreateDate_end()!=null){
						   	      			sbuffer.append( " and  createDate>'" +att_MdDeliveryAddress.getCreateDate_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  修改时间(editDate)
									  if(att_MdDeliveryAddress.getEditDate_start()!=null){
								   	    	sbuffer.append( " and  editDate<='" +att_MdDeliveryAddress.getEditDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 修改时间(editDate)
									 	if(att_MdDeliveryAddress.getEditDate_end()!=null){
						   	      			sbuffer.append( " and  editDate>'" +att_MdDeliveryAddress.getEditDate_end()+"'" );  
								  	     } 
			 //*****************无关字段处理end*****************
			 //*****************数据库字段处理begin*************
								  		//收货地址id(mdaId)
					 					if(att_MdDeliveryAddress.getMdaId()!=null){
											 sbuffer.append( " and mdaId=" +att_MdDeliveryAddress.getMdaId() );
										 }
					 					if(att_MdDeliveryAddress.getSuiId()!=null){
											 sbuffer.append( " and suiId=" +att_MdDeliveryAddress.getSuiId() );
										 }
					       				//所在区域(location)		 					 
									 if(att_MdDeliveryAddress.getLocation()!=null&&
									  !"".equals(att_MdDeliveryAddress.getLocation().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("location")!=-1){
											  sbuffer.append( " and location  like '%"+att_MdDeliveryAddress.getLocation()+"%'"   );
										  }else {
											  sbuffer.append( " and location   ='"+att_MdDeliveryAddress.getLocation()+"'"   );
										  }
									 }
					       				//配送地址(deliveryAddress)		 					 
									 if(att_MdDeliveryAddress.getDeliveryAddress()!=null&&
									  !"".equals(att_MdDeliveryAddress.getDeliveryAddress().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("deliveryAddress")!=-1){
											  sbuffer.append( " and deliveryAddress  like '%"+att_MdDeliveryAddress.getDeliveryAddress()+"%'"   );
										  }else {
											  sbuffer.append( " and deliveryAddress   ='"+att_MdDeliveryAddress.getDeliveryAddress()+"'"   );
										  }
									 }
					       				//邮编(zipCode)		 					 
									 if(att_MdDeliveryAddress.getZipCode()!=null&&
									  !"".equals(att_MdDeliveryAddress.getZipCode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("zipCode")!=-1){
											  sbuffer.append( " and zipCode  like '%"+att_MdDeliveryAddress.getZipCode().toUpperCase()+"%'"   );
										  }else {
											  sbuffer.append( " and zipCode   ='"+att_MdDeliveryAddress.getZipCode()+"'"   );
										  }
									 }
					       				//收件人(addressee)		 					 
									 if(att_MdDeliveryAddress.getAddressee()!=null&&
									  !"".equals(att_MdDeliveryAddress.getAddressee().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("addressee")!=-1){
											  sbuffer.append( " and addressee  like '%"+att_MdDeliveryAddress.getAddressee()+"%'"   );
										  }else {
											  sbuffer.append( " and addressee   ='"+att_MdDeliveryAddress.getAddressee()+"'"   );
										  }
									 }
					       				//收件联系电话(addresseeTelephone)		 					 
									 if(att_MdDeliveryAddress.getAddresseeTelephone()!=null&&
									  !"".equals(att_MdDeliveryAddress.getAddresseeTelephone().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("addresseeTelephone")!=-1){
											  sbuffer.append( " and addresseeTelephone  like '%"+att_MdDeliveryAddress.getAddresseeTelephone()+"%'"   );
										  }else {
											  sbuffer.append( " and addresseeTelephone   ='"+att_MdDeliveryAddress.getAddresseeTelephone()+"'"   );
										  }
									 }
					       				//收件人手机(addresseeMobile)		 					 
									 if(att_MdDeliveryAddress.getAddresseeMobile()!=null&&
									  !"".equals(att_MdDeliveryAddress.getAddresseeMobile().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("addresseeMobile")!=-1){
											  sbuffer.append( " and addresseeMobile  like '%"+att_MdDeliveryAddress.getAddresseeMobile()+"%'"   );
										  }else {
											  sbuffer.append( " and addresseeMobile   ='"+att_MdDeliveryAddress.getAddresseeMobile()+"'"   );
										  }
									 }
					       				//是否默认值(1是,2否)(ifDefault)		 					 
									 if(att_MdDeliveryAddress.getIfDefault()!=null&&
									  !"".equals(att_MdDeliveryAddress.getIfDefault().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("ifDefault")!=-1){
											  sbuffer.append( " and ifDefault  like '%"+att_MdDeliveryAddress.getIfDefault()+"%'"   );
										  }else {
											  sbuffer.append( " and ifDefault   ='"+att_MdDeliveryAddress.getIfDefault()+"'"   );
										  }
									 }
					       				//创建人(createRen)		 					 
									 if(att_MdDeliveryAddress.getCreateRen()!=null&&
									  !"".equals(att_MdDeliveryAddress.getCreateRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("createRen")!=-1){
											  sbuffer.append( " and createRen  like '%"+att_MdDeliveryAddress.getCreateRen()+"%'"   );
										  }else {
											  sbuffer.append( " and createRen   ='"+att_MdDeliveryAddress.getCreateRen()+"'"   );
										  }
									 }
						   				//创建时间(createDate)
						 				if(att_MdDeliveryAddress.getCreateDate()!=null){
						   	                   sbuffer.append( " and  createDate='" +att_MdDeliveryAddress.getCreateDate()+"'" );  
								  		}
					       				//修改人(editRen)		 					 
									 if(att_MdDeliveryAddress.getEditRen()!=null&&
									  !"".equals(att_MdDeliveryAddress.getEditRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("editRen")!=-1){
											  sbuffer.append( " and editRen  like '%"+att_MdDeliveryAddress.getEditRen()+"%'"   );
										  }else {
											  sbuffer.append( " and editRen   ='"+att_MdDeliveryAddress.getEditRen()+"'"   );
										  }
									 }
						   				//修改时间(editDate)
						 				if(att_MdDeliveryAddress.getEditDate()!=null){
						   	                   sbuffer.append( " and  editDate='" +att_MdDeliveryAddress.getEditDate()+"'" );  
								  		}
			 //*****************数据库字段处理end***************
			  		if(orderStr!=null&&!"".equals(orderStr.trim())){
									 sbuffer.append( " order by "+orderStr);
						 }
						 /*
						 else {
							  sbuffer.append( " order by  MdaId   desc " );
					      }
					      */
			 
			 return  sbuffer.toString();
	}
}