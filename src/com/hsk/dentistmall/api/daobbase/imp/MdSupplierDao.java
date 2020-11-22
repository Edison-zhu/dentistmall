package com.hsk.dentistmall.api.daobbase.imp;

import java.util.*;
import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_supplier表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:17:00
 */
@Component
public class  MdSupplierDao extends SupperDao implements IMdSupplierDao {	

	/**
	 * 根据md_supplier表主键查找MdSupplier对象记录
	 * 
	 * @param  WzId  Integer类型(md_supplier表主键)
	 * @return MdSupplier md_supplier表记录
	 * @throws HSKDBException
	 */	
	public MdSupplier findMdSupplierById(Integer WzId) throws HSKDBException{
				MdSupplier  att_MdSupplier=new MdSupplier();				
				if(WzId!=null){
					att_MdSupplier.setWzId(WzId);	
				    Object obj=	this.getOne(att_MdSupplier);
					if(obj!=null){
						att_MdSupplier=(MdSupplier) obj;
					}
				}
				return  att_MdSupplier;
	}
	 /**
	  * 根据md_supplier表主键删除MdSupplier对象记录
	  * @param  WzId  Integer类型(md_supplier表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdSupplierById(Integer WzId) throws HSKDBException{ 
		 MdSupplier  att_MdSupplier=new MdSupplier();	
		  if(WzId!=null){
					  att_MdSupplier.setWzId(WzId);
				  	  Object obj=	this.getOne(att_MdSupplier);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_supplier表主键修改MdSupplier对象记录
     * @param  WzId  Integer类型(md_supplier表主键)
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
     * @return MdSupplier  修改后的MdSupplier对象记录
	 * @throws HSKDBException
	 */
	public  MdSupplier updateMdSupplierById(Integer WzId,MdSupplier att_MdSupplier) throws HSKDBException{
		  if(WzId!=null){
					att_MdSupplier.setWzId(WzId);
				   	Object obj=	this.getOne(att_MdSupplier);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdSupplier;
	}
	
	/**
	 * 新增md_supplier表 记录
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdSupplier(MdSupplier att_MdSupplier) throws HSKDBException{
		 return this.newObject(att_MdSupplier);
	} 
		
	/**
	 * 新增或修改md_supplier表记录 ,如果md_supplier表主键MdSupplier.WzId为空就是添加，如果非空就是修改
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
	 * @throws HSKDBException
	 */
	public  MdSupplier saveOrUpdateMdSupplier(MdSupplier att_MdSupplier) throws HSKDBException{
		  this.getHibernateTemplate().saveOrUpdate(att_MdSupplier);
		  return att_MdSupplier;
	}
	
	/**
	 *根据MdSupplier对象作为对(md_supplier表进行查询，获取列表对象
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
     * @return List<MdSupplier>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdSupplier> getListByMdSupplier(MdSupplier att_MdSupplier) throws HSKDBException{
		String Hql=this.getHql( att_MdSupplier); 
		List<MdSupplier> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}

	@Override
	public MdSupplierTemp findMdSupplierByIdNoLogin(Integer wzId) throws HSKDBException {
		MdSupplierTemp  att_MdSupplier=new MdSupplierTemp();
		if(wzId!=null){
			att_MdSupplier.setWzId(wzId);
			Object obj=	this.getOne(att_MdSupplier);
			if(obj!=null){
				att_MdSupplier=(MdSupplierTemp) obj;
			}
		}
		return  att_MdSupplier;
	}

	/**
	 *根据MdSupplier对象作为对(md_supplier表进行查询，获取分页对象
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
     * @return List<MdSupplier>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdSupplier(MdSupplier att_MdSupplier)
			throws HSKDBException {
		String Hql=this.getHql(att_MdSupplier);
		return this.getHibernateDao().findByPage(Hql); 
	} 
	/**
	 * 根据MdSupplier对象获取Hql字符串
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
	 *  @return hql字符串
	 */
	private String  getHql(MdSupplier att_MdSupplier){
			 StringBuffer sbuffer = new StringBuffer( " from  MdSupplier  where  1=1  ");
			 String likeStr=  att_MdSupplier.getTab_like();
			 String orderStr= att_MdSupplier.getTab_order();
			 
			 //*****************无关字段处理begin*****************
						   		 //处理in条件 供应商id(wzId)
							     if(att_MdSupplier.getWzId_str()!=null&&
						   		    !"".equals(att_MdSupplier.getWzId_str().trim())){ 
											 String  intStr=att_MdSupplier.getWzId_str().trim();
											 sbuffer.append(" and wzId in ("+intStr+")"); 
								 	}
						   		 //处理in条件 组织机构关系id(orgGxId)
							     if(att_MdSupplier.getOrgGxId_str()!=null&&
						   		    !"".equals(att_MdSupplier.getOrgGxId_str().trim())){ 
											 String  intStr=att_MdSupplier.getOrgGxId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  orgGxId="+did+"   "); 
													 }else {
													 sbuffer.append("  orgGxId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
							     if(att_MdSupplier.getState_str()!=null&&
								   		    !"".equals(att_MdSupplier.getState_str().trim())){ 
													 String  intStr=att_MdSupplier.getState_str().trim();
													 String[]  arrayStr=intStr.split(","); 
													 
													  if(arrayStr.length>0){
														 sbuffer.append(" and ( ");
														 for(int i=0;i<arrayStr.length;i++){
															 String did=arrayStr[i];
															 if(i==arrayStr.length-1){
																 sbuffer.append("  state="+did+"   "); 
															 }else {
															 sbuffer.append("  state="+did+" or "); 
															 }
														 }
														 sbuffer.append(" ) "); 
													 }
													   
										 	}
								 		//时间类型开始条件处理  法人毕业时间(graduationTime)
									  if(att_MdSupplier.getGraduationTime_start()!=null){
								   	    	sbuffer.append( " and  graduationTime<='" +att_MdSupplier.getGraduationTime_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 法人毕业时间(graduationTime)
									 	if(att_MdSupplier.getGraduationTime_end()!=null){
						   	      			sbuffer.append( " and  graduationTime>'" +att_MdSupplier.getGraduationTime_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  创建时间(createDate)
									  if(att_MdSupplier.getCreateDate_start()!=null){
								   	    	sbuffer.append( " and  createDate<='" +att_MdSupplier.getCreateDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 创建时间(createDate)
									 	if(att_MdSupplier.getCreateDate_end()!=null){
						   	      			sbuffer.append( " and  createDate>'" +att_MdSupplier.getCreateDate_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  修改时间(editDate)
									  if(att_MdSupplier.getEditDate_start()!=null){
								   	    	sbuffer.append( " and  editDate<='" +att_MdSupplier.getEditDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 修改时间(editDate)
									 	if(att_MdSupplier.getEditDate_end()!=null){
						   	      			sbuffer.append( " and  editDate>'" +att_MdSupplier.getEditDate_end()+"'" );  
								  	     } 
			 //*****************无关字段处理end*****************
			 //*****************数据库字段处理begin*************
								  		//供应商id(wzId)
					 					if(att_MdSupplier.getWzId()!=null){
											 sbuffer.append( " and wzId=" +att_MdSupplier.getWzId() );
										 }
								  		//组织机构关系id(orgGxId)
					 					if(att_MdSupplier.getOrgGxId()!=null){
											 sbuffer.append( " and orgGxId=" +att_MdSupplier.getOrgGxId() );
										 }
					       				//法人手机号码(phoneNumber)		 					 
									 if(att_MdSupplier.getPhoneNumber()!=null&&
									  !"".equals(att_MdSupplier.getPhoneNumber().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("phoneNumber")!=-1){
											  sbuffer.append( " and phoneNumber  like '%"+att_MdSupplier.getPhoneNumber()+"%'"   );
										  }else {
											  sbuffer.append( " and phoneNumber   ='"+att_MdSupplier.getPhoneNumber()+"'"   );
										  }
									 }
					       				//法人姓名(fullName)		 					 
									 if(att_MdSupplier.getFullName()!=null&&
									  !"".equals(att_MdSupplier.getFullName().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("fullName")!=-1){
											  sbuffer.append( " and fullName  like '%"+att_MdSupplier.getFullName()+"%'"   );
										  }else {
											  sbuffer.append( " and fullName   ='"+att_MdSupplier.getFullName()+"'"   );
										  }
									 }
					       				//法人邮箱(mailbox)		 					 
									 if(att_MdSupplier.getMailbox()!=null&&
									  !"".equals(att_MdSupplier.getMailbox().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("mailbox")!=-1){
											  sbuffer.append( " and mailbox  like '%"+att_MdSupplier.getMailbox()+"%'"   );
										  }else {
											  sbuffer.append( " and mailbox   ='"+att_MdSupplier.getMailbox()+"'"   );
										  }
									 }
					       				//法人学历(education)		 					 
									 if(att_MdSupplier.getEducation()!=null&&
									  !"".equals(att_MdSupplier.getEducation().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("education")!=-1){
											  sbuffer.append( " and education  like '%"+att_MdSupplier.getEducation()+"%'"   );
										  }else {
											  sbuffer.append( " and education   ='"+att_MdSupplier.getEducation()+"'"   );
										  }
									 }
						   				//法人毕业时间(graduationTime)
						 				if(att_MdSupplier.getGraduationTime()!=null){
						   	                   sbuffer.append( " and  graduationTime='" +att_MdSupplier.getGraduationTime()+"'" );  
								  		}
					       				//个人和创业简介(businessIntroduction)		 					 
									 if(att_MdSupplier.getBusinessIntroduction()!=null&&
									  !"".equals(att_MdSupplier.getBusinessIntroduction().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("businessIntroduction")!=-1){
											  sbuffer.append( " and businessIntroduction  like '%"+att_MdSupplier.getBusinessIntroduction()+"%'"   );
										  }else {
											  sbuffer.append( " and businessIntroduction   ='"+att_MdSupplier.getBusinessIntroduction()+"'"   );
										  }
									 }
					       				//企业名称(applicantName)		 					 
									 if(att_MdSupplier.getApplicantName()!=null&&
									  !"".equals(att_MdSupplier.getApplicantName().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("applicantName")!=-1){
											  sbuffer.append( " and (applicantName  like '%"+att_MdSupplier.getApplicantName().trim()+"%' or pyName like '%"+att_MdSupplier.getApplicantName().trim().toUpperCase()+"%')"   );
										  }else {
											  sbuffer.append( " and applicantName   ='"+att_MdSupplier.getApplicantName()+"'"   );
										  }
									 }
					       				//企业住所地(corporateDomicile)		 					 
									 if(att_MdSupplier.getCorporateDomicile()!=null&&
									  !"".equals(att_MdSupplier.getCorporateDomicile().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("corporateDomicile")!=-1){
											  sbuffer.append( " and corporateDomicile  like '%"+att_MdSupplier.getCorporateDomicile()+"%'"   );
										  }else {
											  sbuffer.append( " and corporateDomicile   ='"+att_MdSupplier.getCorporateDomicile()+"'"   );
										  }
									 }
					       				//企业类型(enterpriseType)		 					 
									 if(att_MdSupplier.getEnterpriseType()!=null&&
									  !"".equals(att_MdSupplier.getEnterpriseType().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("enterpriseType")!=-1){
											  sbuffer.append( " and enterpriseType  like '%"+att_MdSupplier.getEnterpriseType()+"%'"   );
										  }else {
											  sbuffer.append( " and enterpriseType   ='"+att_MdSupplier.getEnterpriseType()+"'"   );
										  }
									 }
					       				//经营范围(scopeBusiness)		 					 
									 if(att_MdSupplier.getScopeBusiness()!=null&&
									  !"".equals(att_MdSupplier.getScopeBusiness().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("scopeBusiness")!=-1){
											  sbuffer.append( " and scopeBusiness  like '%"+att_MdSupplier.getScopeBusiness()+"%'"   );
										  }else {
											  sbuffer.append( " and scopeBusiness   ='"+att_MdSupplier.getScopeBusiness()+"'"   );
										  }
									 }
					       				//证照号码(licenseNumber)		 					 
									 if(att_MdSupplier.getLicenseNumber()!=null&&
									  !"".equals(att_MdSupplier.getLicenseNumber().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("licenseNumber")!=-1){
											  sbuffer.append( " and licenseNumber  like '%"+att_MdSupplier.getLicenseNumber()+"%'"   );
										  }else {
											  sbuffer.append( " and licenseNumber   ='"+att_MdSupplier.getLicenseNumber()+"'"   );
										  }
									 }
					       				//三证附件(certificateAnnexFilecode)		 					 
									 if(att_MdSupplier.getCertificateAnnexFilecode()!=null&&
									  !"".equals(att_MdSupplier.getCertificateAnnexFilecode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("certificateAnnexFilecode")!=-1){
											  sbuffer.append( " and certificateAnnexFilecode  like '%"+att_MdSupplier.getCertificateAnnexFilecode()+"%'"   );
										  }else {
											  sbuffer.append( " and certificateAnnexFilecode   ='"+att_MdSupplier.getCertificateAnnexFilecode()+"'"   );
										  }
									 }
					       				//公司类型(0表示未注册公司，1表示已注册公司)(companyType)		 					 
									 if(att_MdSupplier.getCompanyType()!=null&&
									  !"".equals(att_MdSupplier.getCompanyType().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("companyType")!=-1){
											  sbuffer.append( " and companyType  like '%"+att_MdSupplier.getCompanyType()+"%'"   );
										  }else {
											  sbuffer.append( " and companyType   ='"+att_MdSupplier.getCompanyType()+"'"   );
										  }
									 }
					       				//公司状态(state)		 					 
									 if(att_MdSupplier.getState()!=null&&
									  !"".equals(att_MdSupplier.getState().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("state")!=-1){
											  sbuffer.append( " and state  like '%"+att_MdSupplier.getState()+"%'"   );
										  }else {
											  sbuffer.append( " and state   ='"+att_MdSupplier.getState()+"'"   );
										  }
									 }
					       				//投资人(legalPerson)		 					 
									 if(att_MdSupplier.getLegalPerson()!=null&&
									  !"".equals(att_MdSupplier.getLegalPerson().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("legalPerson")!=-1){
											  sbuffer.append( " and legalPerson  like '%"+att_MdSupplier.getLegalPerson()+"%'"   );
										  }else {
											  sbuffer.append( " and legalPerson   ='"+att_MdSupplier.getLegalPerson()+"'"   );
										  }
									 }
					       				//投资人证件号(legalCertificateNo)		 					 
									 if(att_MdSupplier.getLegalCertificateNo()!=null&&
									  !"".equals(att_MdSupplier.getLegalCertificateNo().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("legalCertificateNo")!=-1){
											  sbuffer.append( " and legalCertificateNo  like '%"+att_MdSupplier.getLegalCertificateNo()+"%'"   );
										  }else {
											  sbuffer.append( " and legalCertificateNo   ='"+att_MdSupplier.getLegalCertificateNo()+"'"   );
										  }
									 }
					       				//所属省(selprovince)		 					 
									 if(att_MdSupplier.getSelprovince()!=null&&
									  !"".equals(att_MdSupplier.getSelprovince().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("selprovince")!=-1){
											  sbuffer.append( " and selprovince  like '%"+att_MdSupplier.getSelprovince()+"%'"   );
										  }else {
											  sbuffer.append( " and selprovince   ='"+att_MdSupplier.getSelprovince()+"'"   );
										  }
									 }
					       				//所属市(selcity)		 					 
									 if(att_MdSupplier.getSelcity()!=null&&
									  !"".equals(att_MdSupplier.getSelcity().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("selcity")!=-1){
											  sbuffer.append( " and selcity  like '%"+att_MdSupplier.getSelcity()+"%'"   );
										  }else {
											  sbuffer.append( " and selcity   ='"+att_MdSupplier.getSelcity()+"'"   );
										  }
									 }
					       				//所属区(selarea)		 					 
									 if(att_MdSupplier.getSelarea()!=null&&
									  !"".equals(att_MdSupplier.getSelarea().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("selarea")!=-1){
											  sbuffer.append( " and selarea  like '%"+att_MdSupplier.getSelarea()+"%'"   );
										  }else {
											  sbuffer.append( " and selarea   ='"+att_MdSupplier.getSelarea()+"'"   );
										  }
									 }
					       				//税务登记号码(taxRegistrationNumber)		 					 
									 if(att_MdSupplier.getTaxRegistrationNumber()!=null&&
									  !"".equals(att_MdSupplier.getTaxRegistrationNumber().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("taxRegistrationNumber")!=-1){
											  sbuffer.append( " and taxRegistrationNumber  like '%"+att_MdSupplier.getTaxRegistrationNumber()+"%'"   );
										  }else {
											  sbuffer.append( " and taxRegistrationNumber   ='"+att_MdSupplier.getTaxRegistrationNumber()+"'"   );
										  }
									 }
					       				//创建人(createRen)		 					 
									 if(att_MdSupplier.getCreateRen()!=null&&
									  !"".equals(att_MdSupplier.getCreateRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("createRen")!=-1){
											  sbuffer.append( " and createRen  like '%"+att_MdSupplier.getCreateRen()+"%'"   );
										  }else {
											  sbuffer.append( " and createRen   ='"+att_MdSupplier.getCreateRen()+"'"   );
										  }
									 }
						   				//创建时间(createDate)
						 				if(att_MdSupplier.getCreateDate()!=null){
						   	                   sbuffer.append( " and  createDate='" +att_MdSupplier.getCreateDate()+"'" );  
								  		}
					       				//修改人(editRen)		 					 
									 if(att_MdSupplier.getEditRen()!=null&&
									  !"".equals(att_MdSupplier.getEditRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("editRen")!=-1){
											  sbuffer.append( " and editRen  like '%"+att_MdSupplier.getEditRen()+"%'"   );
										  }else {
											  sbuffer.append( " and editRen   ='"+att_MdSupplier.getEditRen()+"'"   );
										  }
									 }
						   				//修改时间(editDate)
						 				if(att_MdSupplier.getEditDate()!=null){
						   	                   sbuffer.append( " and  editDate='" +att_MdSupplier.getEditDate()+"'" );  
								  		}
			 //*****************数据库字段处理end***************
			  		if(orderStr!=null&&!"".equals(orderStr.trim())){
									 sbuffer.append( " order by "+orderStr);
						 }
						 /*
						 else {
							  sbuffer.append( " order by  WzId   desc " );
					      }
					      */
			 
			 return  sbuffer.toString();
	}
	@Override
	public PagerModel getPagerModelByMdSupplierSearch(String searchName,String state) throws HSKDBException {
		String hql ="from MdSupplier where state='"+state+"'";
		if(searchName != null && !searchName.trim().equals(""))
			hql +="and (upper(applicantName) like '%"+searchName.trim().toUpperCase()+"%' or upper(corporateDomicile) like '%"
					+searchName.trim().toUpperCase()+"%' or upper(scopeBusiness) like '%"+searchName.trim().toUpperCase()+"%')";
		hql += " order by wzId desc";
		return this.getHibernateDao().findByPage(hql); 
	}
}