package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.text.SimpleDateFormat;
import java.util.*;

import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_out_order_mx表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-11-13 11:22:28
 */
@Component
public class MdOutOrderMxApiDao extends SupperDao implements IMdOutOrderMxApiDao {

	/**
	 * 根据md_out_order_mx表主键查找MdOutOrderMx对象记录
	 * 
	 * @param  MomId  Integer类型(md_out_order_mx表主键)
	 * @return MdOutOrderMx md_out_order_mx表记录
	 * @throws HSKDBException
	 */	
	public MdOutOrderMx findMdOutOrderMxById(Integer MomId) throws HSKDBException{
				MdOutOrderMx  att_MdOutOrderMx=new MdOutOrderMx();				
				if(MomId!=null){
					att_MdOutOrderMx.setMomId(MomId);	
				    Object obj=	this.getOne(att_MdOutOrderMx);
					if(obj!=null){
						att_MdOutOrderMx=(MdOutOrderMx) obj;
					}
				}
				return  att_MdOutOrderMx;
	}
	 /**
	  * 根据md_out_order_mx表主键删除MdOutOrderMx对象记录
	  * @param  MomId  Integer类型(md_out_order_mx表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdOutOrderMxById(Integer MomId) throws HSKDBException{ 
		 MdOutOrderMx  att_MdOutOrderMx=new MdOutOrderMx();	
		  if(MomId!=null){
					  att_MdOutOrderMx.setMomId(MomId);
				  	  Object obj=	this.getOne(att_MdOutOrderMx);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_out_order_mx表主键修改MdOutOrderMx对象记录
     * @param  MomId  Integer类型(md_out_order_mx表主键)
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
     * @return MdOutOrderMx  修改后的MdOutOrderMx对象记录
	 * @throws HSKDBException
	 */
	public  MdOutOrderMx updateMdOutOrderMxById(Integer MomId,MdOutOrderMx att_MdOutOrderMx) throws HSKDBException{
		  if(MomId!=null){
					att_MdOutOrderMx.setMomId(MomId);
				   	Object obj=	this.getOne(att_MdOutOrderMx);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdOutOrderMx;
	}
	
	/**
	 * 新增md_out_order_mx表 记录
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdOutOrderMx(MdOutOrderMx att_MdOutOrderMx) throws HSKDBException{
		 return this.newObject(att_MdOutOrderMx);
	} 
		
	/**
	 * 新增或修改md_out_order_mx表记录 ,如果md_out_order_mx表主键MdOutOrderMx.MomId为空就是添加，如果非空就是修改
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
	 * @throws HSKDBException
	 */
	public  MdOutOrderMx saveOrUpdateMdOutOrderMx(MdOutOrderMx att_MdOutOrderMx) throws HSKDBException{
		  this.getHibernateTemplate().saveOrUpdate(att_MdOutOrderMx);
		  return att_MdOutOrderMx;
	}
	
	/**
	 *根据MdOutOrderMx对象作为对(md_out_order_mx表进行查询，获取列表对象
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
     * @return List<MdOutOrderMx>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdOutOrderMx> getListByMdOutOrderMx(MdOutOrderMx att_MdOutOrderMx) throws HSKDBException{
		String Hql=this.getHql( att_MdOutOrderMx); 
		List<MdOutOrderMx> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}

	@Override
	public List<MdOutOrderMxMaterielInfoViewEntity> getListByMdOutOrderMxMaterielInfoView(MdOutOrderMxMaterielInfoViewEntity att_mdOutOrderMx) throws HSKDBException {
		String Hql=this.getViewHql( att_mdOutOrderMx);
		List<MdOutOrderMxMaterielInfoViewEntity> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}

	/**
	 *根据MdOutOrderMx对象作为对(md_out_order_mx表进行查询，获取分页对象
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
     * @return List<MdOutOrderMx>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdOutOrderMx(MdOutOrderMx att_MdOutOrderMx)
			throws HSKDBException {
		String Hql=this.getHql(att_MdOutOrderMx);
		return this.getHibernateDao().findByPage(Hql); 
	}

	private String getViewHql(MdOutOrderMxMaterielInfoViewEntity att_MdOutOrderMx){
		StringBuffer sbuffer = new StringBuffer(" from  MdOutOrderMxMaterielInfoViewEntity  where  1=1  ");
		String likeStr=  att_MdOutOrderMx.getTab_like();
		String orderStr= att_MdOutOrderMx.getTab_order();

		//*****************无关字段处理begin*****************
		//处理in条件 ID(momId)
		if(att_MdOutOrderMx.getMomId_str()!=null&&
				!"".equals(att_MdOutOrderMx.getMomId_str().trim())){
			String  intStr=att_MdOutOrderMx.getMomId_str().trim();
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
		//处理in条件 申领单信息(mooId)
		if(att_MdOutOrderMx.getMooId_str()!=null&&
				!"".equals(att_MdOutOrderMx.getMooId_str().trim())){
			String  intStr=att_MdOutOrderMx.getMooId_str().trim();
			String[]  arrayStr=intStr.split(",");

			if(arrayStr.length>0){
				sbuffer.append(" and ( ");
				for(int i=0;i<arrayStr.length;i++){
					String did=arrayStr[i];
					if(i==arrayStr.length-1){
						sbuffer.append("  mooId="+did+"   ");
					}else {
						sbuffer.append("  mooId="+did+" or ");
					}
				}
				sbuffer.append(" ) ");
			}

		}
		//处理in条件 物料信息表id(wmsMiId)
		if(att_MdOutOrderMx.getWmsMiId_str()!=null&&
				!"".equals(att_MdOutOrderMx.getWmsMiId_str().trim())){
			String  intStr=att_MdOutOrderMx.getWmsMiId_str().trim();
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
		//处理in条件 规格ID(mmfId)
		if(att_MdOutOrderMx.getMmfId_str()!=null&&
				!"".equals(att_MdOutOrderMx.getMmfId_str().trim())){
			String  intStr=att_MdOutOrderMx.getMmfId_str().trim();
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
		//时间类型开始条件处理  修改时间(editDate)
		if(att_MdOutOrderMx.getEditDate_start()!=null){
			sbuffer.append( " and  editDate<='" +att_MdOutOrderMx.getEditDate_start()+"'" );
		}
		//时间类型结束条件处理 修改时间(editDate)
		if(att_MdOutOrderMx.getEditDate_end()!=null){
			sbuffer.append( " and  editDate>'" +att_MdOutOrderMx.getEditDate_end()+"'" );
		}
		//时间类型开始条件处理  创建时间(createDate)
		if(att_MdOutOrderMx.getCreateDate_start()!=null){
			sbuffer.append( " and  createDate<='" +att_MdOutOrderMx.getCreateDate_start()+"'" );
		}
		//时间类型结束条件处理 创建时间(createDate)
		if(att_MdOutOrderMx.getCreateDate_end()!=null){
			sbuffer.append( " and  createDate>'" +att_MdOutOrderMx.getCreateDate_end()+"'" );
		}
		//*****************无关字段处理end*****************
		//*****************数据库字段处理begin*************
		//ID(momId)
		if(att_MdOutOrderMx.getMomId()!=null){
			sbuffer.append( " and momId=" +att_MdOutOrderMx.getMomId() );
		}
		//申领单信息(mooId)
		if(att_MdOutOrderMx.getMooId()!=null){
			sbuffer.append( " and mooId=" +att_MdOutOrderMx.getMooId() );
		}

		//物料信息表id(wmsMiId)
		if(att_MdOutOrderMx.getWmsMiId()!=null){
			sbuffer.append( " and wmsMiId=" +att_MdOutOrderMx.getWmsMiId() );
		}
		//规格ID(mmfId)
		if(att_MdOutOrderMx.getMmfId()!=null){
			sbuffer.append( " and mmfId=" +att_MdOutOrderMx.getMmfId() );
		}
		//物料编码(matCode)
		if(att_MdOutOrderMx.getMatCode()!=null&&
				!"".equals(att_MdOutOrderMx.getMatCode().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matCode")!=-1){
				sbuffer.append( " and matCode  like '%"+att_MdOutOrderMx.getMatCode().toUpperCase()+"%'"   );
			}else {
				sbuffer.append( " and matCode   ='"+att_MdOutOrderMx.getMatCode()+"'"   );
			}
		}
		//物料名称拼音
		if ((att_MdOutOrderMx.getPyName() != null && !"".equals(att_MdOutOrderMx.getPyName().trim()))
				&& (att_MdOutOrderMx.getMatName() != null && !"".equals(att_MdOutOrderMx.getMatName().trim()))) {
			if ((likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("pyName") != -1) && (likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matName")!=-1)) {
				sbuffer.append(" and (matName  like '%"+att_MdOutOrderMx.getMatName()+"%' or pyName  like '%" + att_MdOutOrderMx.getPyName() + "%')");
			} else {
				sbuffer.append(" and (matName   ='"+att_MdOutOrderMx.getMatName()+"' or pyName   ='" + att_MdOutOrderMx.getPyName() + "')");
			}
		} else if(att_MdOutOrderMx.getMatName()!=null && !"".equals(att_MdOutOrderMx.getMatName().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matName")!=-1){
				sbuffer.append( " and matName  like '%"+att_MdOutOrderMx.getMatName()+"%'"   );
			}else {
				sbuffer.append( " and matName   ='"+att_MdOutOrderMx.getMatName()+"'"   );
			}
		} else if((att_MdOutOrderMx.getPyName() != null && !"".equals(att_MdOutOrderMx.getPyName().trim()))){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("pyName")!=-1){
				sbuffer.append( " and pyName  like '%"+att_MdOutOrderMx.getPyName()+"%'"   );
			}else {
				sbuffer.append( " and pyName   ='"+att_MdOutOrderMx.getPyName()+"'"   );
			}
		}

		//规格(norm)
		if(att_MdOutOrderMx.getNorm()!=null&&
				!"".equals(att_MdOutOrderMx.getNorm().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("norm")!=-1){
				sbuffer.append( " and norm  like '%"+att_MdOutOrderMx.getNorm()+"%'"   );
			}else {
				sbuffer.append( " and norm   ='"+att_MdOutOrderMx.getNorm()+"'"   );
			}
		}
		//基本单位(basicUnit)
		if(att_MdOutOrderMx.getBasicUnit()!=null&&
				!"".equals(att_MdOutOrderMx.getBasicUnit().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("basicUnit")!=-1){
				sbuffer.append( " and basicUnit  like '%"+att_MdOutOrderMx.getBasicUnit()+"%'"   );
			}else {
				sbuffer.append( " and basicUnit   ='"+att_MdOutOrderMx.getBasicUnit()+"'"   );
			}
		}
		//批次属性(itemKeyId)
		if(att_MdOutOrderMx.getItemKeyId()!=null&&
				!"".equals(att_MdOutOrderMx.getItemKeyId().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("itemKeyId")!=-1){
				sbuffer.append( " and itemKeyId  like '%"+att_MdOutOrderMx.getItemKeyId()+"%'"   );
			}else {
				sbuffer.append( " and itemKeyId   ='"+att_MdOutOrderMx.getItemKeyId()+"'"   );
			}
		}
		//录入方式(inputMode)
		if(att_MdOutOrderMx.getInputMode()!=null&&
				!"".equals(att_MdOutOrderMx.getInputMode().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("inputMode")!=-1){
				sbuffer.append( " and inputMode  like '%"+att_MdOutOrderMx.getInputMode()+"%'"   );
			}else {
				sbuffer.append( " and inputMode   ='"+att_MdOutOrderMx.getInputMode()+"'"   );
			}
		}
		//行号(lineNo)
		if(att_MdOutOrderMx.getLineNo()!=null&&
				!"".equals(att_MdOutOrderMx.getLineNo().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("lineNo")!=-1){
				sbuffer.append( " and lineNo  like '%"+att_MdOutOrderMx.getLineNo()+"%'"   );
			}else {
				sbuffer.append( " and lineNo   ='"+att_MdOutOrderMx.getLineNo()+"'"   );
			}
		}
		//收货单号(soi)
		if(att_MdOutOrderMx.getSoi()!=null&&
				!"".equals(att_MdOutOrderMx.getSoi().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("soi")!=-1){
				sbuffer.append( " and soi  like '%"+att_MdOutOrderMx.getSoi()+"%'"   );
			}else {
				sbuffer.append( " and soi   ='"+att_MdOutOrderMx.getSoi()+"'"   );
			}
		}
		//供应商(supplier)
		if(att_MdOutOrderMx.getSupplier()!=null&&
				!"".equals(att_MdOutOrderMx.getSupplier().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("supplier")!=-1){
				sbuffer.append( " and supplier  like '%"+att_MdOutOrderMx.getSupplier()+"%'"   );
			}else {
				sbuffer.append( " and supplier   ='"+att_MdOutOrderMx.getSupplier()+"'"   );
			}
		}
		//扩展属性1(extendPropc1)
		if(att_MdOutOrderMx.getExtendPropc1()!=null&&
				!"".equals(att_MdOutOrderMx.getExtendPropc1().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc1")!=-1){
				sbuffer.append( " and extendPropc1  like '%"+att_MdOutOrderMx.getExtendPropc1()+"%'"   );
			}else {
				sbuffer.append( " and extendPropc1   ='"+att_MdOutOrderMx.getExtendPropc1()+"'"   );
			}
		}
		//扩展属性1(extendPropc2)
		if(att_MdOutOrderMx.getExtendPropc2()!=null&&
				!"".equals(att_MdOutOrderMx.getExtendPropc2().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc2")!=-1){
				sbuffer.append( " and extendPropc2  like '%"+att_MdOutOrderMx.getExtendPropc2()+"%'"   );
			}else {
				sbuffer.append( " and extendPropc2   ='"+att_MdOutOrderMx.getExtendPropc2()+"'"   );
			}
		}
		//扩展属性1(extendPropc3)
		if(att_MdOutOrderMx.getExtendPropc3()!=null&&
				!"".equals(att_MdOutOrderMx.getExtendPropc3().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc3")!=-1){
				sbuffer.append( " and extendPropc3  like '%"+att_MdOutOrderMx.getExtendPropc3()+"%'"   );
			}else {
				sbuffer.append( " and extendPropc3   ='"+att_MdOutOrderMx.getExtendPropc3()+"'"   );
			}
		}
		//扩展属性1(extendPropc4)
		if(att_MdOutOrderMx.getExtendPropc4()!=null&&
				!"".equals(att_MdOutOrderMx.getExtendPropc4().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc4")!=-1){
				sbuffer.append( " and extendPropc4  like '%"+att_MdOutOrderMx.getExtendPropc4()+"%'"   );
			}else {
				sbuffer.append( " and extendPropc4   ='"+att_MdOutOrderMx.getExtendPropc4()+"'"   );
			}
		}
		//扩展属性1(extendPropc5)
		if(att_MdOutOrderMx.getExtendPropc5()!=null&&
				!"".equals(att_MdOutOrderMx.getExtendPropc5().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc5")!=-1){
				sbuffer.append( " and extendPropc5  like '%"+att_MdOutOrderMx.getExtendPropc5()+"%'"   );
			}else {
				sbuffer.append( " and extendPropc5   ='"+att_MdOutOrderMx.getExtendPropc5()+"'"   );
			}
		}
		//扩展属性1(extendPropc6)
		if(att_MdOutOrderMx.getExtendPropc6()!=null&&
				!"".equals(att_MdOutOrderMx.getExtendPropc6().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc6")!=-1){
				sbuffer.append( " and extendPropc6  like '%"+att_MdOutOrderMx.getExtendPropc6()+"%'"   );
			}else {
				sbuffer.append( " and extendPropc6   ='"+att_MdOutOrderMx.getExtendPropc6()+"'"   );
			}
		}
		//扩展属性1(extendPropc7)
		if(att_MdOutOrderMx.getExtendPropc7()!=null&&
				!"".equals(att_MdOutOrderMx.getExtendPropc7().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc7")!=-1){
				sbuffer.append( " and extendPropc7  like '%"+att_MdOutOrderMx.getExtendPropc7()+"%'"   );
			}else {
				sbuffer.append( " and extendPropc7   ='"+att_MdOutOrderMx.getExtendPropc7()+"'"   );
			}
		}
		//扩展属性1(extendPropc8)
		if(att_MdOutOrderMx.getExtendPropc8()!=null&&
				!"".equals(att_MdOutOrderMx.getExtendPropc8().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc8")!=-1){
				sbuffer.append( " and extendPropc8  like '%"+att_MdOutOrderMx.getExtendPropc8()+"%'"   );
			}else {
				sbuffer.append( " and extendPropc8   ='"+att_MdOutOrderMx.getExtendPropc8()+"'"   );
			}
		}
		//扩展属性1(extendPropc9)
		if(att_MdOutOrderMx.getExtendPropc9()!=null&&
				!"".equals(att_MdOutOrderMx.getExtendPropc9().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc9")!=-1){
				sbuffer.append( " and extendPropc9  like '%"+att_MdOutOrderMx.getExtendPropc9()+"%'"   );
			}else {
				sbuffer.append( " and extendPropc9   ='"+att_MdOutOrderMx.getExtendPropc9()+"'"   );
			}
		}
		//扩展属性1(extendPropc10)
		if(att_MdOutOrderMx.getExtendPropc10()!=null&&
				!"".equals(att_MdOutOrderMx.getExtendPropc10().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc10")!=-1){
				sbuffer.append( " and extendPropc10  like '%"+att_MdOutOrderMx.getExtendPropc10()+"%'"   );
			}else {
				sbuffer.append( " and extendPropc10   ='"+att_MdOutOrderMx.getExtendPropc10()+"'"   );
			}
		}
		//备注(remarks)
		if(att_MdOutOrderMx.getRemarks()!=null&&
				!"".equals(att_MdOutOrderMx.getRemarks().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("remarks")!=-1){
				sbuffer.append( " and remarks  like '%"+att_MdOutOrderMx.getRemarks()+"%'"   );
			}else {
				sbuffer.append( " and remarks   ='"+att_MdOutOrderMx.getRemarks()+"'"   );
			}
		}
		//修改时间(editDate)
		if(att_MdOutOrderMx.getEditDate()!=null){
			sbuffer.append( " and  editDate='" +att_MdOutOrderMx.getEditDate()+"'" );
		}
		//修改人(editRen)
		if(att_MdOutOrderMx.getEditRen()!=null&&
				!"".equals(att_MdOutOrderMx.getEditRen().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("editRen")!=-1){
				sbuffer.append( " and editRen  like '%"+att_MdOutOrderMx.getEditRen()+"%'"   );
			}else {
				sbuffer.append( " and editRen   ='"+att_MdOutOrderMx.getEditRen()+"'"   );
			}
		}
		//创建时间(createDate)
		if(att_MdOutOrderMx.getCreateDate()!=null){
			sbuffer.append( " and  createDate='" +att_MdOutOrderMx.getCreateDate()+"'" );
		}
		//创建人(createRen)
		if(att_MdOutOrderMx.getCreateRen()!=null&&
				!"".equals(att_MdOutOrderMx.getCreateRen().trim())){
			if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("createRen")!=-1){
				sbuffer.append( " and createRen  like '%"+att_MdOutOrderMx.getCreateRen()+"%'"   );
			}else {
				sbuffer.append( " and createRen   ='"+att_MdOutOrderMx.getCreateRen()+"'"   );
			}
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
	/**
	 * 根据MdOutOrderMx对象获取Hql字符串
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
	 *  @return hql字符串
	 */
	private String  getHql(MdOutOrderMx att_MdOutOrderMx){
			 StringBuffer sbuffer = new StringBuffer( " from  MdOutOrderMx  where  1=1  ");
			 String likeStr=  att_MdOutOrderMx.getTab_like();
			 String orderStr= att_MdOutOrderMx.getTab_order();
			 
			 //*****************无关字段处理begin*****************
						   		 //处理in条件 ID(momId)
							     if(att_MdOutOrderMx.getMomId_str()!=null&&
						   		    !"".equals(att_MdOutOrderMx.getMomId_str().trim())){ 
											 String  intStr=att_MdOutOrderMx.getMomId_str().trim();
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
						   		 //处理in条件 申领单信息(mooId)
							     if(att_MdOutOrderMx.getMooId_str()!=null&&
						   		    !"".equals(att_MdOutOrderMx.getMooId_str().trim())){ 
											 String  intStr=att_MdOutOrderMx.getMooId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  mooId="+did+"   "); 
													 }else {
													 sbuffer.append("  mooId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 物料信息表id(wmsMiId)
							     if(att_MdOutOrderMx.getWmsMiId_str()!=null&&
						   		    !"".equals(att_MdOutOrderMx.getWmsMiId_str().trim())){ 
											 String  intStr=att_MdOutOrderMx.getWmsMiId_str().trim();
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
						   		 //处理in条件 规格ID(mmfId)
							     if(att_MdOutOrderMx.getMmfId_str()!=null&&
						   		    !"".equals(att_MdOutOrderMx.getMmfId_str().trim())){ 
											 String  intStr=att_MdOutOrderMx.getMmfId_str().trim();
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
								 		//时间类型开始条件处理  修改时间(editDate)
									  if(att_MdOutOrderMx.getEditDate_start()!=null){
								   	    	sbuffer.append( " and  editDate<='" +att_MdOutOrderMx.getEditDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 修改时间(editDate)
									 	if(att_MdOutOrderMx.getEditDate_end()!=null){
						   	      			sbuffer.append( " and  editDate>'" +att_MdOutOrderMx.getEditDate_end()+"'" );  
								  	     } 
								 		//时间类型开始条件处理  创建时间(createDate)
									  if(att_MdOutOrderMx.getCreateDate_start()!=null){
								   	    	sbuffer.append( " and  createDate<='" +att_MdOutOrderMx.getCreateDate_start()+"'" );  
										 }
								 	  //时间类型结束条件处理 创建时间(createDate)
									 	if(att_MdOutOrderMx.getCreateDate_end()!=null){
						   	      			sbuffer.append( " and  createDate>'" +att_MdOutOrderMx.getCreateDate_end()+"'" );  
								  	     } 
			 //*****************无关字段处理end*****************
			 //*****************数据库字段处理begin*************
								  		//ID(momId)
					 					if(att_MdOutOrderMx.getMomId()!=null){
											 sbuffer.append( " and momId=" +att_MdOutOrderMx.getMomId() );
										 }
								  		//申领单信息(mooId)
					 					if(att_MdOutOrderMx.getMooId()!=null){
											 sbuffer.append( " and mooId=" +att_MdOutOrderMx.getMooId() );
										 }
								  		//物料信息表id(wmsMiId)
					 					if(att_MdOutOrderMx.getWmsMiId()!=null){
											 sbuffer.append( " and wmsMiId=" +att_MdOutOrderMx.getWmsMiId() );
										 }
								  		//规格ID(mmfId)
					 					if(att_MdOutOrderMx.getMmfId()!=null){
											 sbuffer.append( " and mmfId=" +att_MdOutOrderMx.getMmfId() );
										 }
					       				//物料编码(matCode)		 					 
									 if(att_MdOutOrderMx.getMatCode()!=null&&
									  !"".equals(att_MdOutOrderMx.getMatCode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matCode")!=-1){
											  sbuffer.append( " and matCode  like '%"+att_MdOutOrderMx.getMatCode().toUpperCase()+"%'"   );
										  }else {
											  sbuffer.append( " and matCode   ='"+att_MdOutOrderMx.getMatCode()+"'"   );
										  }
									 }
					       				//物料名称(matName)		 					 
									 if(att_MdOutOrderMx.getMatName()!=null&&
									  !"".equals(att_MdOutOrderMx.getMatName().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("matName")!=-1){
											  sbuffer.append( " and matName  like '%"+att_MdOutOrderMx.getMatName()+"%'"   );
										  }else {
											  sbuffer.append( " and matName   ='"+att_MdOutOrderMx.getMatName()+"'"   );
										  }
									 }
					       				//规格(norm)		 					 
									 if(att_MdOutOrderMx.getNorm()!=null&&
									  !"".equals(att_MdOutOrderMx.getNorm().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("norm")!=-1){
											  sbuffer.append( " and norm  like '%"+att_MdOutOrderMx.getNorm()+"%'"   );
										  }else {
											  sbuffer.append( " and norm   ='"+att_MdOutOrderMx.getNorm()+"'"   );
										  }
									 }
					       				//基本单位(basicUnit)		 					 
									 if(att_MdOutOrderMx.getBasicUnit()!=null&&
									  !"".equals(att_MdOutOrderMx.getBasicUnit().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("basicUnit")!=-1){
											  sbuffer.append( " and basicUnit  like '%"+att_MdOutOrderMx.getBasicUnit()+"%'"   );
										  }else {
											  sbuffer.append( " and basicUnit   ='"+att_MdOutOrderMx.getBasicUnit()+"'"   );
										  }
									 }
					       				//批次属性(itemKeyId)		 					 
									 if(att_MdOutOrderMx.getItemKeyId()!=null&&
									  !"".equals(att_MdOutOrderMx.getItemKeyId().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("itemKeyId")!=-1){
											  sbuffer.append( " and itemKeyId  like '%"+att_MdOutOrderMx.getItemKeyId()+"%'"   );
										  }else {
											  sbuffer.append( " and itemKeyId   ='"+att_MdOutOrderMx.getItemKeyId()+"'"   );
										  }
									 }
					       				//录入方式(inputMode)		 					 
									 if(att_MdOutOrderMx.getInputMode()!=null&&
									  !"".equals(att_MdOutOrderMx.getInputMode().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("inputMode")!=-1){
											  sbuffer.append( " and inputMode  like '%"+att_MdOutOrderMx.getInputMode()+"%'"   );
										  }else {
											  sbuffer.append( " and inputMode   ='"+att_MdOutOrderMx.getInputMode()+"'"   );
										  }
									 }
					       				//行号(lineNo)		 					 
									 if(att_MdOutOrderMx.getLineNo()!=null&&
									  !"".equals(att_MdOutOrderMx.getLineNo().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("lineNo")!=-1){
											  sbuffer.append( " and lineNo  like '%"+att_MdOutOrderMx.getLineNo()+"%'"   );
										  }else {
											  sbuffer.append( " and lineNo   ='"+att_MdOutOrderMx.getLineNo()+"'"   );
										  }
									 }
					       				//收货单号(soi)		 					 
									 if(att_MdOutOrderMx.getSoi()!=null&&
									  !"".equals(att_MdOutOrderMx.getSoi().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("soi")!=-1){
											  sbuffer.append( " and soi  like '%"+att_MdOutOrderMx.getSoi()+"%'"   );
										  }else {
											  sbuffer.append( " and soi   ='"+att_MdOutOrderMx.getSoi()+"'"   );
										  }
									 }
					       				//供应商(supplier)		 					 
									 if(att_MdOutOrderMx.getSupplier()!=null&&
									  !"".equals(att_MdOutOrderMx.getSupplier().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("supplier")!=-1){
											  sbuffer.append( " and supplier  like '%"+att_MdOutOrderMx.getSupplier()+"%'"   );
										  }else {
											  sbuffer.append( " and supplier   ='"+att_MdOutOrderMx.getSupplier()+"'"   );
										  }
									 }
					       				//扩展属性1(extendPropc1)		 					 
									 if(att_MdOutOrderMx.getExtendPropc1()!=null&&
									  !"".equals(att_MdOutOrderMx.getExtendPropc1().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc1")!=-1){
											  sbuffer.append( " and extendPropc1  like '%"+att_MdOutOrderMx.getExtendPropc1()+"%'"   );
										  }else {
											  sbuffer.append( " and extendPropc1   ='"+att_MdOutOrderMx.getExtendPropc1()+"'"   );
										  }
									 }
					       				//扩展属性1(extendPropc2)		 					 
									 if(att_MdOutOrderMx.getExtendPropc2()!=null&&
									  !"".equals(att_MdOutOrderMx.getExtendPropc2().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc2")!=-1){
											  sbuffer.append( " and extendPropc2  like '%"+att_MdOutOrderMx.getExtendPropc2()+"%'"   );
										  }else {
											  sbuffer.append( " and extendPropc2   ='"+att_MdOutOrderMx.getExtendPropc2()+"'"   );
										  }
									 }
					       				//扩展属性1(extendPropc3)		 					 
									 if(att_MdOutOrderMx.getExtendPropc3()!=null&&
									  !"".equals(att_MdOutOrderMx.getExtendPropc3().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc3")!=-1){
											  sbuffer.append( " and extendPropc3  like '%"+att_MdOutOrderMx.getExtendPropc3()+"%'"   );
										  }else {
											  sbuffer.append( " and extendPropc3   ='"+att_MdOutOrderMx.getExtendPropc3()+"'"   );
										  }
									 }
					       				//扩展属性1(extendPropc4)		 					 
									 if(att_MdOutOrderMx.getExtendPropc4()!=null&&
									  !"".equals(att_MdOutOrderMx.getExtendPropc4().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc4")!=-1){
											  sbuffer.append( " and extendPropc4  like '%"+att_MdOutOrderMx.getExtendPropc4()+"%'"   );
										  }else {
											  sbuffer.append( " and extendPropc4   ='"+att_MdOutOrderMx.getExtendPropc4()+"'"   );
										  }
									 }
					       				//扩展属性1(extendPropc5)		 					 
									 if(att_MdOutOrderMx.getExtendPropc5()!=null&&
									  !"".equals(att_MdOutOrderMx.getExtendPropc5().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc5")!=-1){
											  sbuffer.append( " and extendPropc5  like '%"+att_MdOutOrderMx.getExtendPropc5()+"%'"   );
										  }else {
											  sbuffer.append( " and extendPropc5   ='"+att_MdOutOrderMx.getExtendPropc5()+"'"   );
										  }
									 }
					       				//扩展属性1(extendPropc6)		 					 
									 if(att_MdOutOrderMx.getExtendPropc6()!=null&&
									  !"".equals(att_MdOutOrderMx.getExtendPropc6().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc6")!=-1){
											  sbuffer.append( " and extendPropc6  like '%"+att_MdOutOrderMx.getExtendPropc6()+"%'"   );
										  }else {
											  sbuffer.append( " and extendPropc6   ='"+att_MdOutOrderMx.getExtendPropc6()+"'"   );
										  }
									 }
					       				//扩展属性1(extendPropc7)		 					 
									 if(att_MdOutOrderMx.getExtendPropc7()!=null&&
									  !"".equals(att_MdOutOrderMx.getExtendPropc7().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc7")!=-1){
											  sbuffer.append( " and extendPropc7  like '%"+att_MdOutOrderMx.getExtendPropc7()+"%'"   );
										  }else {
											  sbuffer.append( " and extendPropc7   ='"+att_MdOutOrderMx.getExtendPropc7()+"'"   );
										  }
									 }
					       				//扩展属性1(extendPropc8)		 					 
									 if(att_MdOutOrderMx.getExtendPropc8()!=null&&
									  !"".equals(att_MdOutOrderMx.getExtendPropc8().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc8")!=-1){
											  sbuffer.append( " and extendPropc8  like '%"+att_MdOutOrderMx.getExtendPropc8()+"%'"   );
										  }else {
											  sbuffer.append( " and extendPropc8   ='"+att_MdOutOrderMx.getExtendPropc8()+"'"   );
										  }
									 }
					       				//扩展属性1(extendPropc9)		 					 
									 if(att_MdOutOrderMx.getExtendPropc9()!=null&&
									  !"".equals(att_MdOutOrderMx.getExtendPropc9().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc9")!=-1){
											  sbuffer.append( " and extendPropc9  like '%"+att_MdOutOrderMx.getExtendPropc9()+"%'"   );
										  }else {
											  sbuffer.append( " and extendPropc9   ='"+att_MdOutOrderMx.getExtendPropc9()+"'"   );
										  }
									 }
					       				//扩展属性1(extendPropc10)		 					 
									 if(att_MdOutOrderMx.getExtendPropc10()!=null&&
									  !"".equals(att_MdOutOrderMx.getExtendPropc10().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("extendPropc10")!=-1){
											  sbuffer.append( " and extendPropc10  like '%"+att_MdOutOrderMx.getExtendPropc10()+"%'"   );
										  }else {
											  sbuffer.append( " and extendPropc10   ='"+att_MdOutOrderMx.getExtendPropc10()+"'"   );
										  }
									 }
					       				//备注(remarks)		 					 
									 if(att_MdOutOrderMx.getRemarks()!=null&&
									  !"".equals(att_MdOutOrderMx.getRemarks().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("remarks")!=-1){
											  sbuffer.append( " and remarks  like '%"+att_MdOutOrderMx.getRemarks()+"%'"   );
										  }else {
											  sbuffer.append( " and remarks   ='"+att_MdOutOrderMx.getRemarks()+"'"   );
										  }
									 }
						   				//修改时间(editDate)
						 				if(att_MdOutOrderMx.getEditDate()!=null){
						   	                   sbuffer.append( " and  editDate='" +att_MdOutOrderMx.getEditDate()+"'" );  
								  		}
					       				//修改人(editRen)		 					 
									 if(att_MdOutOrderMx.getEditRen()!=null&&
									  !"".equals(att_MdOutOrderMx.getEditRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("editRen")!=-1){
											  sbuffer.append( " and editRen  like '%"+att_MdOutOrderMx.getEditRen()+"%'"   );
										  }else {
											  sbuffer.append( " and editRen   ='"+att_MdOutOrderMx.getEditRen()+"'"   );
										  }
									 }
						   				//创建时间(createDate)
						 				if(att_MdOutOrderMx.getCreateDate()!=null){
						   	                   sbuffer.append( " and  createDate='" +att_MdOutOrderMx.getCreateDate()+"'" );  
								  		}
					       				//创建人(createRen)		 					 
									 if(att_MdOutOrderMx.getCreateRen()!=null&&
									  !"".equals(att_MdOutOrderMx.getCreateRen().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("createRen")!=-1){
											  sbuffer.append( " and createRen  like '%"+att_MdOutOrderMx.getCreateRen()+"%'"   );
										  }else {
											  sbuffer.append( " and createRen   ='"+att_MdOutOrderMx.getCreateRen()+"'"   );
										  }
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
	public List<Map<Object, Object>> getMxListByOrder(MdOutOrder att_MdOutOrder,MdOutOrderMx att_MdOutOrderMx) throws HSKDBException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sql ="SELECT a.*,b.* FROM md_out_order a,md_out_order_mx b WHERE a.moo_id=b.moo_id";
		if(att_MdOutOrder != null && att_MdOutOrder.getMooCode()!=null && !att_MdOutOrder.getMooCode().trim().equals(""))
			sql += " and moo_code '%"+att_MdOutOrder.getMooCode().trim()+"%'";
		if(att_MdOutOrder != null && att_MdOutOrder.getUserName()!=null && !att_MdOutOrder.getUserName().trim().equals(""))
			sql += " and user_name '%"+att_MdOutOrder.getUserName().trim()+"%'";
		if(att_MdOutOrder != null && att_MdOutOrder.getFlowState()!=null && !att_MdOutOrder.getFlowState().trim().equals(""))
			sql += " and flow_state ='"+att_MdOutOrder.getFlowState().trim()+"'";
		if(att_MdOutOrder != null && att_MdOutOrder.getSuiId()!=null )
			sql += " and sui_id ='"+att_MdOutOrder.getSuiId()+"'";
		if(att_MdOutOrder != null && att_MdOutOrder.getFlowState()!=null && !att_MdOutOrder.getFlowState().trim().equals(""))
			sql += " and flow_state ='"+att_MdOutOrder.getFlowState().trim()+"'";
		//时间类型开始条件处理  申领时间(orderTime)
		if(att_MdOutOrder.getOrderTime_start()!=null)
			sql += " and a.order_time >='"+sdf.format(att_MdOutOrder.getOrderTime_start())+" 00:00:00'";
	 	  //时间类型结束条件处理 申领时间(orderTime)
		if(att_MdOutOrder.getOrderTime_end()!=null)
			sql += " and a.order_time <='"+sdf.format(att_MdOutOrder.getOrderTime_start())+" 23:59:59'";
		sql +=" and a.state='1'";
		sql += " order by a.order_time";
		return this.getJdbcDao().query(sql);
	}
	
	
}