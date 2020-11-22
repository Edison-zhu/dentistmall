package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.util.*;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdEnclosureApiDao;
import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_enclosure表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:17
 */
@Component
public class MdEnclosureApiDao extends SupperDao implements IMdEnclosureApiDao {

	/**
	 * 根据md_enclosure表主键查找MdEnclosure对象记录
	 * 
	 * @param  RbenId  Integer类型(md_enclosure表主键)
	 * @return MdEnclosure md_enclosure表记录
	 * @throws HSKDBException
	 */	
	public MdEnclosure findMdEnclosureById(Integer RbenId) throws HSKDBException{
				MdEnclosure att_MdEnclosure=new MdEnclosure();
				if(RbenId!=null){
					att_MdEnclosure.setRbenId(RbenId);	
				    Object obj=	this.getOne(att_MdEnclosure);
					if(obj!=null){
						att_MdEnclosure=(MdEnclosure) obj;
					}
				}
				return  att_MdEnclosure;
	}
	 /**
	  * 根据md_enclosure表主键删除MdEnclosure对象记录
	  * @param  RbenId  Integer类型(md_enclosure表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdEnclosureById(Integer RbenId) throws HSKDBException{ 
		 MdEnclosure att_MdEnclosure=new MdEnclosure();
		  if(RbenId!=null){
					  att_MdEnclosure.setRbenId(RbenId);
				  	  Object obj=	this.getOne(att_MdEnclosure);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_enclosure表主键修改MdEnclosure对象记录
     * @param  RbenId  Integer类型(md_enclosure表主键)
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
     * @return MdEnclosure  修改后的MdEnclosure对象记录
	 * @throws HSKDBException
	 */
	public MdEnclosure updateMdEnclosureById(Integer RbenId, MdEnclosure att_MdEnclosure) throws HSKDBException{
		  if(RbenId!=null){
					att_MdEnclosure.setRbenId(RbenId);
				   	Object obj=	this.getOne(att_MdEnclosure);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdEnclosure;
	}
	
	/**
	 * 新增md_enclosure表 记录
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdEnclosure(MdEnclosure att_MdEnclosure) throws HSKDBException{
		 return this.newObject(att_MdEnclosure);
	} 
		
	/**
	 * 新增或修改md_enclosure表记录 ,如果md_enclosure表主键MdEnclosure.RbenId为空就是添加，如果非空就是修改
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
	 * @throws HSKDBException
	 */
	public MdEnclosure saveOrUpdateMdEnclosure(MdEnclosure att_MdEnclosure) throws HSKDBException{
		  this.getHibernateTemplate().saveOrUpdate(att_MdEnclosure);
		  return att_MdEnclosure;
	}
	
	/**
	 *根据MdEnclosure对象作为对(md_enclosure表进行查询，获取列表对象
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
     * @return List<MdEnclosure>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdEnclosure> getListByMdEnclosure(MdEnclosure att_MdEnclosure) throws HSKDBException{
		String Hql=this.getHql( att_MdEnclosure, null);
		List<MdEnclosure> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	/**
	 *根据MdEnclosure对象作为对(md_enclosure表进行查询，获取分页对象
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
     * @return List<MdEnclosure>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdEnclosure(MdEnclosure att_MdEnclosure)
			throws HSKDBException {
		String Hql=this.getHql(att_MdEnclosure, null);
		return this.getHibernateDao().findByPage(Hql); 
	} 
	/**
	 * 根据MdEnclosure对象获取Hql字符串
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
	 *  @return hql字符串
	 */
	private String  getHql(MdEnclosure att_MdEnclosure, StringBuffer sb){
		StringBuffer sbuffer;
		if (sb == null) {
			sbuffer = new StringBuffer( " from  MdEnclosure  where  1=1  ");
		} else {
			sbuffer = sb;
		}
			 String likeStr=  att_MdEnclosure.getTab_like();
			 String orderStr= att_MdEnclosure.getTab_order();
			 
			 //*****************无关字段处理begin*****************
						   		 //处理in条件 附件表id(rbenId)
							     if(att_MdEnclosure.getRbenId_str()!=null&&
						   		    !"".equals(att_MdEnclosure.getRbenId_str().trim())){ 
											 String  intStr=att_MdEnclosure.getRbenId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  rbenId="+did+"   "); 
													 }else {
													 sbuffer.append("  rbenId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 (orgGxId)
							     if(att_MdEnclosure.getOrgGxId_str()!=null&&
						   		    !"".equals(att_MdEnclosure.getOrgGxId_str().trim())){ 
											 String  intStr=att_MdEnclosure.getOrgGxId_str().trim();
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
						   		 //处理in条件 物料信息表id(wmsMiId)
							     if(att_MdEnclosure.getWmsMiId_str()!=null&&
						   		    !"".equals(att_MdEnclosure.getWmsMiId_str().trim())){ 
											 String  intStr=att_MdEnclosure.getWmsMiId_str().trim();
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
			 //*****************无关字段处理end*****************
			 //*****************数据库字段处理begin*************
								  		//附件表id(rbenId)
					 					if(att_MdEnclosure.getRbenId()!=null){
											 sbuffer.append( " and rbenId=" +att_MdEnclosure.getRbenId() );
										 }
								  		//(orgGxId)
					 					if(att_MdEnclosure.getOrgGxId()!=null){
											 sbuffer.append( " and orgGxId=" +att_MdEnclosure.getOrgGxId() );
										 }
								  		//物料信息表id(wmsMiId)
					 					if(att_MdEnclosure.getWmsMiId()!=null){
											 sbuffer.append( " and wmsMiId=" +att_MdEnclosure.getWmsMiId() );
										 }
					       				//文件编号(fileCode)		 					 
									 if(att_MdEnclosure.getFileCode()!=null&&
									  !"".equals(att_MdEnclosure.getFileCode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("fileCode")!=-1){
											  sbuffer.append( " and fileCode  like '%"+att_MdEnclosure.getFileCode().toUpperCase()+"%'"   );
										  }else {
											  sbuffer.append( " and fileCode   ='"+att_MdEnclosure.getFileCode()+"'"   );
										  }
									 }
					       				//文件说明(fileNode)		 					 
									 if(att_MdEnclosure.getFileNode()!=null&&
									  !"".equals(att_MdEnclosure.getFileNode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("fileNode")!=-1){
											  sbuffer.append( " and fileNode  like '%"+att_MdEnclosure.getFileNode()+"%'"   );
										  }else {
											  sbuffer.append( " and fileNode   ='"+att_MdEnclosure.getFileNode()+"'"   );
										  }
									 }
			 //*****************数据库字段处理end***************
			  		if(orderStr!=null&&!"".equals(orderStr.trim())){
									 sbuffer.append( " order by "+orderStr);
						 }
						 /*
						 else {
							  sbuffer.append( " order by  RbenId   desc " );
					      }
					      */
			 
			 return  sbuffer.toString();
	}
	@Override
	public void delMdEnclosureList(MdEnclosure att_MdEnclosure)
			throws HSKDBException {
		boolean flag=false;
		String sql = "delete from md_enclosure where 1=1";
		if(att_MdEnclosure != null && att_MdEnclosure.getWmsMiId() != null){
			sql += " and wms_mi_id="+att_MdEnclosure.getWmsMiId();
			flag=true;
		}
		if(att_MdEnclosure != null && att_MdEnclosure.getOrgGxId() != null){
			sql += " and org_gx_id="+att_MdEnclosure.getOrgGxId();
			flag=true;
		}
		if(att_MdEnclosure != null && att_MdEnclosure.getFileCode() != null && !att_MdEnclosure.getFileCode().trim().equals("")){
			sql += " and file_code='"+att_MdEnclosure.getFileCode().trim()+"'";
			flag=true;
		}
		if(flag)
			this.getJdbcDao().execute(sql);
	}

	@Override
	public List<MdEnclosure> getListByMdEnclosureNoLogin(MdEnclosure mdEnclosure) throws HSKDBException {
		String Hql=this.getHql( mdEnclosure, new StringBuffer( " from  MdEnclosureTemp  where  1=1  "));
		List<MdEnclosure> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
}