package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.util.*;

import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_evaluate表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2018-03-13 14:44:35
 */
@Component
public class MdEvaluateApiDao extends SupperDao implements IMdEvaluateApiDao {

	/**
	 * 根据md_evaluate表主键查找MdEvaluate对象记录
	 * 
	 * @param  MevaId  Integer类型(md_evaluate表主键)
	 * @return MdEvaluate md_evaluate表记录
	 * @throws HSKDBException
	 */	
	public MdEvaluate findMdEvaluateById(Integer MevaId) throws HSKDBException{
				MdEvaluate  att_MdEvaluate=new MdEvaluate();				
				if(MevaId!=null){
					att_MdEvaluate.setMevaId(MevaId);	
				    Object obj=	this.getOne(att_MdEvaluate);
					if(obj!=null){
						att_MdEvaluate=(MdEvaluate) obj;
					}
				}
				return  att_MdEvaluate;
	}
	 /**
	  * 根据md_evaluate表主键删除MdEvaluate对象记录
	  * @param  MevaId  Integer类型(md_evaluate表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdEvaluateById(Integer MevaId) throws HSKDBException{ 
		 MdEvaluate  att_MdEvaluate=new MdEvaluate();	
		  if(MevaId!=null){
					  att_MdEvaluate.setMevaId(MevaId);
				  	  Object obj=	this.getOne(att_MdEvaluate);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_evaluate表主键修改MdEvaluate对象记录
     * @param  MevaId  Integer类型(md_evaluate表主键)
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
     * @return MdEvaluate  修改后的MdEvaluate对象记录
	 * @throws HSKDBException
	 */
	public  MdEvaluate updateMdEvaluateById(Integer MevaId,MdEvaluate att_MdEvaluate) throws HSKDBException{
		  if(MevaId!=null){
					att_MdEvaluate.setMevaId(MevaId);
				   	Object obj=	this.getOne(att_MdEvaluate);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdEvaluate;
	}
	
	/**
	 * 新增md_evaluate表 记录
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdEvaluate(MdEvaluate att_MdEvaluate) throws HSKDBException{
		 return this.newObject(att_MdEvaluate);
	} 
		
	/**
	 * 新增或修改md_evaluate表记录 ,如果md_evaluate表主键MdEvaluate.MevaId为空就是添加，如果非空就是修改
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
	 * @throws HSKDBException
	 */
	public  MdEvaluate saveOrUpdateMdEvaluate(MdEvaluate att_MdEvaluate) throws HSKDBException{
		  this.getHibernateTemplate().saveOrUpdate(att_MdEvaluate);
		  return att_MdEvaluate;
	}
	
	/**
	 *根据MdEvaluate对象作为对(md_evaluate表进行查询，获取列表对象
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
     * @return List<MdEvaluate>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdEvaluate> getListByMdEvaluate(MdEvaluate att_MdEvaluate) throws HSKDBException{
		String Hql=this.getHql( att_MdEvaluate); 
		List<MdEvaluate> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	/**
	 *根据MdEvaluate对象作为对(md_evaluate表进行查询，获取分页对象
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
     * @return List<MdEvaluate>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdEvaluate(MdEvaluate att_MdEvaluate)
			throws HSKDBException {
		String Hql=this.getHql(att_MdEvaluate);
		return this.getHibernateDao().findByPage(Hql); 
	} 
	
	public PagerModel getPagerModelByCount(MdEvaluate att_MdEvaluate)
			throws HSKDBException {
		
		String wheresql="";
		if(att_MdEvaluate.getMatName()!=null&&!"".equals(att_MdEvaluate.getMatName().trim())){
			wheresql=" and  mat_name like '%"+att_MdEvaluate.getMatName()+"%'";
		}
		
		if(att_MdEvaluate.getMmfCode()!=null&&!"".equals(att_MdEvaluate.getMmfCode().trim())){
			wheresql=" and  mmf_code like '%"+att_MdEvaluate.getMmfCode()+"%'";
		}
		
		String sql="SELECT  t.mmf_id,t.mat_name,t.mat_code,t.org_name,t.applicant_Name,t.mmf_name,t.price , "+
			"(SELECT  COUNT(*)   FROM  md_evaluate s  WHERE s.Price_review='1' AND  s.mmf_id=t.mmf_id   )AS  numb01 ,"+
			"(SELECT  COUNT(*)  FROM  md_evaluate s  WHERE s.Price_review='2'  AND  s.mmf_id=t.mmf_id   )AS  numb02, "+
			"(SELECT  COUNT(*)  FROM  md_evaluate s  WHERE s.Price_review='3' AND  s.mmf_id=t.mmf_id   )AS  numb03"+
			" FROM   md_evaluate t  where   1=1 "+wheresql+"   GROUP BY  t.mmf_id, t.mat_name,t.mat_code,t.org_name,t.applicant_Name,t.mmf_name,t.price";
		
		PagerModel  pm=this.getJdbcDao().findByPage(sql); 
		List<MdEvaluate> list_back=new ArrayList<MdEvaluate>();
		if(pm.getRows()!=null&&pm.getRows().size()>0){
			for(int i=0;i<pm.getRows().size();i++){
				MdEvaluate att_one=new MdEvaluate ();
				Map<String,Object> did=(Map<String, Object>) pm.getRows().get(i);
				if(did.containsKey("MAT_NAME")){
					Object val=did.get("MAT_NAME");
					if(val!=null){
						att_one.setMatName(val.toString());
					}
				}
				if(did.containsKey("MAT_CODE")){
					Object val=did.get("MAT_CODE");
					if(val!=null){
						att_one.setMatCode(val.toString());
					}
				}
				
				if(did.containsKey("ORG_NAME")){
					Object val=did.get("ORG_NAME");
					if(val!=null){
						att_one.setOrgName(val.toString());
					}
				}
				
				if(did.containsKey("APPLICANT_NAME")){
					Object val=did.get("APPLICANT_NAME");
					if(val!=null){
						att_one.setApplicantName(val.toString());
					}
				}
				
				
				if(did.containsKey("MMF_NAME")){
					Object val=did.get("MMF_NAME");
					if(val!=null){
						att_one.setMmfName(val.toString());
					}
				}
				
				if(did.containsKey("PRICE")){
					Object val=did.get("PRICE");
					if(val!=null){
						att_one.setPrice(Double.valueOf(val.toString()));
					}
				}
				
				if(did.containsKey("MMF_ID")){
					Object val=did.get("MMF_ID");
					if(val!=null){
						att_one.setMmfId(Integer.valueOf(val.toString()));
					}
				}
				
				if(did.containsKey("NUMB01")){
					Object val=did.get("NUMB01");
					if(val!=null){
						att_one.setNumber01(Integer.valueOf(val.toString()));
					}
				}
				if(did.containsKey("NUMB02")){
					Object val=did.get("NUMB02");
					if(val!=null){
						att_one.setNumber02(Integer.valueOf(val.toString()));
					}
				}
				if(did.containsKey("NUMB03")){
					Object val=did.get("NUMB03");
					if(val!=null){
						att_one.setNumber03(Integer.valueOf(val.toString()));
					}
				}
				list_back.add(att_one);
			}
		}
		pm.setItems(list_back);
		pm.setRows(list_back);
		return pm;
	} 
	/**
	 * 根据MdEvaluate对象获取Hql字符串
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
	 *  @return hql字符串
	 */
	private String  getHql(MdEvaluate att_MdEvaluate){
			 StringBuffer sbuffer = new StringBuffer( " from  MdEvaluate  where  1=1  ");
			 String likeStr=  att_MdEvaluate.getTab_like();
			 String orderStr= att_MdEvaluate.getTab_order();
			 
			 //*****************无关字段处理begin*****************
						   		 //处理in条件 评价信息表id(mevaId)
							     if(att_MdEvaluate.getMevaId_str()!=null&&
						   		    !"".equals(att_MdEvaluate.getMevaId_str().trim())){ 
											 String  intStr=att_MdEvaluate.getMevaId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  mevaId="+did+"   "); 
													 }else {
													 sbuffer.append("  mevaId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 规格ID(mmfId)
							     if(att_MdEvaluate.getMmfId_str()!=null&&
						   		    !"".equals(att_MdEvaluate.getMmfId_str().trim())){ 
											 String  intStr=att_MdEvaluate.getMmfId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  mmfId="+did+"   "); 
													 }else {
													 sbuffer.append("  mmfId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
								 		//时间类型开始条件处理  创建时间(createDate)
									  if(att_MdEvaluate.getCreateDate_start()!=null){
								   	    	sbuffer.append( " and  createDate<='" +att_MdEvaluate.getCreateDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 创建时间(createDate)
									 	if(att_MdEvaluate.getCreateDate_end()!=null){
						   	      			sbuffer.append( " and  createDate>'" +att_MdEvaluate.getCreateDate_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  修改时间(editDate)
									  if(att_MdEvaluate.getEditDate_start()!=null){
								   	    	sbuffer.append( " and  editDate<='" +att_MdEvaluate.getEditDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 修改时间(editDate)
									 	if(att_MdEvaluate.getEditDate_end()!=null){
						   	      			sbuffer.append( " and  editDate>'" +att_MdEvaluate.getEditDate_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  点评时间(evaluateTime)
									  if(att_MdEvaluate.getEvaluateTime_start()!=null){
								   	    	sbuffer.append( " and  evaluateTime<='" +att_MdEvaluate.getEvaluateTime_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 点评时间(evaluateTime)
									 	if(att_MdEvaluate.getEvaluateTime_end()!=null){
						   	      			sbuffer.append( " and  evaluateTime>'" +att_MdEvaluate.getEvaluateTime_end()+"'" );  
								  	     } 
			 //*****************无关字段处理end*****************
			 //*****************数据库字段处理begin*************
								  		//评价信息表id(mevaId)
					 					if(att_MdEvaluate.getMevaId()!=null){
											 sbuffer.append( " and mevaId=" +att_MdEvaluate.getMevaId() );
										 }
					 					if(att_MdEvaluate.getWmsMiId()!=null){
											 sbuffer.append( " and wmsMiId=" +att_MdEvaluate.getWmsMiId() );
										 }
					 					if(att_MdEvaluate.getSuiId()!=null){
											 sbuffer.append( " and suiId=" +att_MdEvaluate.getSuiId() );
										 }
								  		//规格ID(mmfId)
					 					if(att_MdEvaluate.getMmfId()!=null){
											 sbuffer.append( " and mmfId=" +att_MdEvaluate.getMmfId() );
										 }
					 					
					 					 if(att_MdEvaluate.getOrgName()!=null&&
												  !"".equals(att_MdEvaluate.getOrgName().trim())){
													  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("orgName")!=-1){
														  sbuffer.append( " and orgName  like '%"+att_MdEvaluate.getOrgName()+"%'"   );
													  }else {
														  sbuffer.append( " and orgName   ='"+att_MdEvaluate.getOrgName()+"'"   );
													  }
												 }
					 					 
					 					 if(att_MdEvaluate.getEvaluatePhoneNumber()!=null&&
												  !"".equals(att_MdEvaluate.getEvaluatePhoneNumber().trim())){
													  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("evaluatePhoneNumber")!=-1){
														  sbuffer.append( " and evaluatePhoneNumber  like '%"+att_MdEvaluate.getEvaluatePhoneNumber()+"%'"   );
													  }else {
														  sbuffer.append( " and evaluatePhoneNumber   ='"+att_MdEvaluate.getEvaluatePhoneNumber()+"'"   );
													  }
												 }
					 					
					 					
					 					
					 					
					       				//价格点评(priceReview)		 					 
									 if(att_MdEvaluate.getPriceReview()!=null&&
									  !"".equals(att_MdEvaluate.getPriceReview().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("priceReview")!=-1){
											  sbuffer.append( " and priceReview  like '%"+att_MdEvaluate.getPriceReview()+"%'"   );
										  }else {
											  sbuffer.append( " and priceReview   ='"+att_MdEvaluate.getPriceReview()+"'"   );
										  }
									 }
					       				//价格比较(priceComparison)		 					 
									 if(att_MdEvaluate.getPriceComparison()!=null&&
									  !"".equals(att_MdEvaluate.getPriceComparison().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("priceComparison")!=-1){
											  sbuffer.append( " and priceComparison  like '%"+att_MdEvaluate.getPriceComparison()+"%'"   );
										  }else {
											  sbuffer.append( " and priceComparison   ='"+att_MdEvaluate.getPriceComparison()+"'"   );
										  }
									 }
					       				//点评内容(reviewContent)		 					 
									 if(att_MdEvaluate.getReviewContent()!=null&&
									  !"".equals(att_MdEvaluate.getReviewContent().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("reviewContent")!=-1){
											  sbuffer.append( " and reviewContent  like '%"+att_MdEvaluate.getReviewContent()+"%'"   );
										  }else {
											  sbuffer.append( " and reviewContent   ='"+att_MdEvaluate.getReviewContent()+"'"   );
										  }
									 }
					       				//状态(state)		 					 
									 if(att_MdEvaluate.getState()!=null&&
									  !"".equals(att_MdEvaluate.getState().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("state")!=-1){
											  sbuffer.append( " and state  like '%"+att_MdEvaluate.getState()+"%'"   );
										  }else {
											  sbuffer.append( " and state   ='"+att_MdEvaluate.getState()+"'"   );
										  }
									 }
					       				//创建人(createRen)		 					 
									 if(att_MdEvaluate.getCreateRen()!=null&&
									  !"".equals(att_MdEvaluate.getCreateRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("createRen")!=-1){
											  sbuffer.append( " and createRen  like '%"+att_MdEvaluate.getCreateRen()+"%'"   );
										  }else {
											  sbuffer.append( " and createRen   ='"+att_MdEvaluate.getCreateRen()+"'"   );
										  }
									 }
						   				//创建时间(createDate)
						 				if(att_MdEvaluate.getCreateDate()!=null){
						   	                   sbuffer.append( " and  createDate='" +att_MdEvaluate.getCreateDate()+"'" );  
								  		}
					       				//修改人(editRen)		 					 
									 if(att_MdEvaluate.getEditRen()!=null&&
									  !"".equals(att_MdEvaluate.getEditRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("editRen")!=-1){
											  sbuffer.append( " and editRen  like '%"+att_MdEvaluate.getEditRen()+"%'"   );
										  }else {
											  sbuffer.append( " and editRen   ='"+att_MdEvaluate.getEditRen()+"'"   );
										  }
									 }
						   				//修改时间(editDate)
						 				if(att_MdEvaluate.getEditDate()!=null){
						   	                   sbuffer.append( " and  editDate='" +att_MdEvaluate.getEditDate()+"'" );  
								  		}
						   				//点评时间(evaluateTime)
						 				if(att_MdEvaluate.getEvaluateTime()!=null){
						   	                   sbuffer.append( " and  evaluateTime='" +att_MdEvaluate.getEvaluateTime()+"'" );  
								  		}
					       				//点评人昵称(evaluateRen)		 					 
									 if(att_MdEvaluate.getEvaluateRen()!=null&&
									  !"".equals(att_MdEvaluate.getEvaluateRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("evaluateRen")!=-1){
											  sbuffer.append( " and evaluateRen  like '%"+att_MdEvaluate.getEvaluateRen()+"%'"   );
										  }else {
											  sbuffer.append( " and evaluateRen   ='"+att_MdEvaluate.getEvaluateRen()+"'"   );
										  }
									 }
					       				//点评人账号(evaluateZh)		 					 
									 if(att_MdEvaluate.getEvaluateZh()!=null&&
									  !"".equals(att_MdEvaluate.getEvaluateZh().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("evaluateZh")!=-1){
											  sbuffer.append( " and evaluateZh  like '%"+att_MdEvaluate.getEvaluateZh()+"%'"   );
										  }else {
											  sbuffer.append( " and evaluateZh   ='"+att_MdEvaluate.getEvaluateZh()+"'"   );
										  }
									 }
					       				//企业名称(applicantName)		 					 
									 if(att_MdEvaluate.getApplicantName()!=null&&
									  !"".equals(att_MdEvaluate.getApplicantName().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("applicantName")!=-1){
											  sbuffer.append( " and applicantName  like '%"+att_MdEvaluate.getApplicantName()+"%'"   );
										  }else {
											  sbuffer.append( " and applicantName   ='"+att_MdEvaluate.getApplicantName()+"'"   );
										  }
									 }
					       				//法人手机号码(phoneNumber)		 					 
									 if(att_MdEvaluate.getPhoneNumber()!=null&&
									  !"".equals(att_MdEvaluate.getPhoneNumber().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("phoneNumber")!=-1){
											  sbuffer.append( " and phoneNumber  like '%"+att_MdEvaluate.getPhoneNumber()+"%'"   );
										  }else {
											  sbuffer.append( " and phoneNumber   ='"+att_MdEvaluate.getPhoneNumber()+"'"   );
										  }
									 }
					       				//企业住所地(corporateDomicile)		 					 
									 if(att_MdEvaluate.getCorporateDomicile()!=null&&
									  !"".equals(att_MdEvaluate.getCorporateDomicile().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("corporateDomicile")!=-1){
											  sbuffer.append( " and corporateDomicile  like '%"+att_MdEvaluate.getCorporateDomicile()+"%'"   );
										  }else {
											  sbuffer.append( " and corporateDomicile   ='"+att_MdEvaluate.getCorporateDomicile()+"'"   );
										  }
									 }
					       				//生产厂家(productName)		 					 
									 if(att_MdEvaluate.getProductName()!=null&&
									  !"".equals(att_MdEvaluate.getProductName().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("productName")!=-1){
											  sbuffer.append( " and productName  like '%"+att_MdEvaluate.getProductName()+"%'"   );
										  }else {
											  sbuffer.append( " and productName   ='"+att_MdEvaluate.getProductName()+"'"   );
										  }
									 }
					       				//物料编码(matCode)		 					 
									 if(att_MdEvaluate.getMatCode()!=null&&
									  !"".equals(att_MdEvaluate.getMatCode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matCode")!=-1){
											  sbuffer.append( " and matCode  like '%"+att_MdEvaluate.getMatCode()+"%'"   );
										  }else {
											  sbuffer.append( " and matCode   ='"+att_MdEvaluate.getMatCode()+"'"   );
										  }
									 }
					       				//物料名称(matName)		 					 
									 if(att_MdEvaluate.getMatName()!=null&&
									  !"".equals(att_MdEvaluate.getMatName().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matName")!=-1){
											  sbuffer.append( " and matName  like '%"+att_MdEvaluate.getMatName()+"%'"   );
										  }else {
											  sbuffer.append( " and matName   ='"+att_MdEvaluate.getMatName()+"'"   );
										  }
									 }
					       				//规格编号(mmfCode)		 					 
									 if(att_MdEvaluate.getMmfCode()!=null&&
									  !"".equals(att_MdEvaluate.getMmfCode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("mmfCode")!=-1){
											  sbuffer.append( " and mmfCode  like '%"+att_MdEvaluate.getMmfCode()+"%'"   );
										  }else {
											  sbuffer.append( " and mmfCode   ='"+att_MdEvaluate.getMmfCode()+"'"   );
										  }
									 }
					       				//规格名称(mmfName)		 					 
									 if(att_MdEvaluate.getMmfName()!=null&&
									  !"".equals(att_MdEvaluate.getMmfName().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("mmfName")!=-1){
											  sbuffer.append( " and mmfName  like '%"+att_MdEvaluate.getMmfName()+"%'"   );
										  }else {
											  sbuffer.append( " and mmfName   ='"+att_MdEvaluate.getMmfName()+"'"   );
										  }
									 }
					       				//规格说明(remark)		 					 
									 if(att_MdEvaluate.getRemark()!=null&&
									  !"".equals(att_MdEvaluate.getRemark().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("remark")!=-1){
											  sbuffer.append( " and remark  like '%"+att_MdEvaluate.getRemark()+"%'"   );
										  }else {
											  sbuffer.append( " and remark   ='"+att_MdEvaluate.getRemark()+"'"   );
										  }
									 }
			 //*****************数据库字段处理end***************
			  		if(orderStr!=null&&!"".equals(orderStr.trim())){
									 sbuffer.append( " order by "+orderStr);
						 }
						 /*
						 else {
							  sbuffer.append( " order by  MevaId   desc " );
					      }
					      */
			 
			 return  sbuffer.toString();
	}
}