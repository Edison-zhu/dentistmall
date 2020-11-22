package com.hsk.dentistmall.api.daobbase.imp;

import java.text.SimpleDateFormat;
import java.util.*;

import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysSalesManEntity;
import com.sun.xml.bind.v2.model.core.EnumLeafInfo;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_inventory表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:54
 */
@Component
public class  MdInventoryDao extends SupperDao implements IMdInventoryDao {	

	/**
	 * 根据md_inventory表主键查找MdInventory对象记录
	 * 
	 * @param  WiId  Integer类型(md_inventory表主键)
	 * @return MdInventory md_inventory表记录
	 * @throws HSKDBException
	 */	
	public MdInventory findMdInventoryById(Integer WiId) throws HSKDBException{
				MdInventory  att_MdInventory=new MdInventory();				
				if(WiId!=null){
					att_MdInventory.setWiId(WiId);	
				    Object obj=	this.getOne(att_MdInventory);
					if(obj!=null){
						att_MdInventory=(MdInventory) obj;
					}
				}
				return  att_MdInventory;
	}
	 /**
	  * 根据md_inventory表主键删除MdInventory对象记录
	  * @param  WiId  Integer类型(md_inventory表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdInventoryById(Integer WiId) throws HSKDBException{ 
		 MdInventory  att_MdInventory=new MdInventory();	
		  if(WiId!=null){
					  att_MdInventory.setWiId(WiId);
				  	  Object obj=	this.getOne(att_MdInventory);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_inventory表主键修改MdInventory对象记录
     * @param  WiId  Integer类型(md_inventory表主键)
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
     * @return MdInventory  修改后的MdInventory对象记录
	 * @throws HSKDBException
	 */
	public  MdInventory updateMdInventoryById(Integer WiId,MdInventory att_MdInventory) throws HSKDBException{
		  if(WiId!=null){
					att_MdInventory.setWiId(WiId);
				   	Object obj=	this.getOne(att_MdInventory);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdInventory;
	}
	
	/**
	 * 新增md_inventory表 记录
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdInventory(MdInventory att_MdInventory) throws HSKDBException{
		 return this.newObject(att_MdInventory);
	} 
		
	/**
	 * 新增或修改md_inventory表记录 ,如果md_inventory表主键MdInventory.WiId为空就是添加，如果非空就是修改
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
	 * @throws HSKDBException
	 */
	public  MdInventory saveOrUpdateMdInventory(MdInventory att_MdInventory) throws HSKDBException{
		  this.getHibernateTemplate().saveOrUpdate(att_MdInventory);
		  return att_MdInventory;
	}
	
	/**
	 *根据MdInventory对象作为对(md_inventory表进行查询，获取列表对象
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
     * @return List<MdInventory>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdInventory> getListByMdInventory(MdInventory att_MdInventory) throws HSKDBException{
		String Hql=this.getHql( att_MdInventory); 
		List<MdInventory> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	/**
	 *根据MdInventory对象作为对(md_inventory表进行查询，获取分页对象
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
     * @return List<MdInventory>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdInventory(MdInventory att_MdInventory)
			throws HSKDBException {
		String Hql=this.getHql(att_MdInventory);
		return this.getHibernateDao().findByPage(Hql); 
	} 
	/**
	 * 根据MdInventory对象获取Hql字符串
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
	 *  @return hql字符串
	 */
	private String  getHql(MdInventory att_MdInventory){
			 StringBuffer sbuffer = new StringBuffer( " from  MdInventory  where  1=1  ");
			 String likeStr=  att_MdInventory.getTab_like();
			 String orderStr= att_MdInventory.getTab_order();
			 
			 //*****************无关字段处理begin*****************
						   		 //处理in条件 库存表id(wiId)
							     if(att_MdInventory.getWiId_str()!=null&&
						   		    !"".equals(att_MdInventory.getWiId_str().trim())){ 
											 String  intStr=att_MdInventory.getWiId_str().trim();
											 String[]  arrayStr=intStr.split(","); 
											 
											  if(arrayStr.length>0){
												 sbuffer.append(" and ( ");
												 for(int i=0;i<arrayStr.length;i++){
													 String did=arrayStr[i];
													 if(i==arrayStr.length-1){
														 sbuffer.append("  wiId="+did+"   "); 
													 }else {
													 sbuffer.append("  wiId="+did+" or "); 
													 }
												 }
												 sbuffer.append(" ) "); 
											 }
											   
								 	}
						   		 //处理in条件 集团公司id(rbaId)
							     if(att_MdInventory.getRbaId_str()!=null&&
						   		    !"".equals(att_MdInventory.getRbaId_str().trim())){ 
											 String  intStr=att_MdInventory.getRbaId_str().trim();
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
							     if(att_MdInventory.getRbsId_str()!=null&&
						   		    !"".equals(att_MdInventory.getRbsId_str().trim())){ 
											 String  intStr=att_MdInventory.getRbsId_str().trim();
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
							     if(att_MdInventory.getRbbId_str()!=null&&
						   		    !"".equals(att_MdInventory.getRbbId_str().trim())){ 
											 String  intStr=att_MdInventory.getRbbId_str().trim();
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
						   		 //处理in条件 物料信息表id(wmsMiId)
							     if(att_MdInventory.getWmsMiId_str()!=null&&
						   		    !"".equals(att_MdInventory.getWmsMiId_str().trim())){ 
											 String  intStr=att_MdInventory.getWmsMiId_str().trim();
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
								  		//库存表id(wiId)
					 					if(att_MdInventory.getWiId()!=null){
											 sbuffer.append( " and wiId=" +att_MdInventory.getWiId() );
										 }
								  		//集团公司id(rbaId)
					 					if(att_MdInventory.getRbaId()!=null){
											 sbuffer.append( " and rbaId=" +att_MdInventory.getRbaId() );
										 }
								  		//牙医医院id(rbsId)
					 					if(att_MdInventory.getRbsId()!=null){
											 sbuffer.append( " and rbsId=" +att_MdInventory.getRbsId() );
										 }
								  		//牙医门诊id(rbbId)
					 					if(att_MdInventory.getRbbId()!=null){
											 sbuffer.append( " and rbbId=" +att_MdInventory.getRbbId() );
										 }
					       				//采购单位类型(purchaseType)		 					 
									 if(att_MdInventory.getPurchaseType()!=null&&
									  !"".equals(att_MdInventory.getPurchaseType().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("purchaseType")!=-1){
											  sbuffer.append( " and purchaseType  like '%"+att_MdInventory.getPurchaseType()+"%'"   );
										  }else {
											  sbuffer.append( " and purchaseType   ='"+att_MdInventory.getPurchaseType()+"'"   );
										  }
									 }
								  		//物料信息表id(wmsMiId)
					 					if(att_MdInventory.getWmsMiId()!=null){
											 sbuffer.append( " and wmsMiId=" +att_MdInventory.getWmsMiId() );
										 }
					 					//规格id(mmfId)
					 					if(att_MdInventory.getMmfId()!=null){
											 sbuffer.append( " and mmfId=" +att_MdInventory.getMmfId() );
										 }
					       				//批次属性(itemKeyId)		 					 
									 if(att_MdInventory.getItemKeyId()!=null&&
									  !"".equals(att_MdInventory.getItemKeyId().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("itemKeyId")!=-1){
											  sbuffer.append( " and itemKeyId  like '%"+att_MdInventory.getItemKeyId()+"%'"   );
										  }else {
											  sbuffer.append( " and itemKeyId   ='"+att_MdInventory.getItemKeyId()+"'"   );
										  }
									 }
					       				//状态(state)		 					 
									 if(att_MdInventory.getState()!=null&&
									  !"".equals(att_MdInventory.getState().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("state")!=-1){
											  sbuffer.append( " and state  like '%"+att_MdInventory.getState()+"%'"   );
										  }else {
											  sbuffer.append( " and state   ='"+att_MdInventory.getState()+"'"   );
										  }
									 }
					       				//基本单位(basicUnit)		 					 
									 if(att_MdInventory.getBasicUnit()!=null&&
									  !"".equals(att_MdInventory.getBasicUnit().trim())){
										  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("basicUnit")!=-1){
											  sbuffer.append( " and basicUnit  like '%"+att_MdInventory.getBasicUnit()+"%'"   );
										  }else {
											  sbuffer.append( " and basicUnit   ='"+att_MdInventory.getBasicUnit()+"'"   );
										  }
									 }
			 //*****************数据库字段处理end***************
			  		if(orderStr!=null&&!"".equals(orderStr.trim())){
									 sbuffer.append( " order by "+orderStr);
						 }
						 /*
						 else {
							  sbuffer.append( " order by  WiId   desc " );
					      }
					      */
			 
			 return  sbuffer.toString();
	}
	@Override
	public MdInventory insertMdInventory(MdInventory att_MdInventory, String ratio1, Double matNumber, Double splitNumber)throws HSKDBException {
		String hql ="from MdInventory where 1=1";
		if(att_MdInventory.getWmsMiId() != null)
			hql += " and wmsMiId="+att_MdInventory.getWmsMiId();
		if(att_MdInventory.getMmfId() != null)
			hql += " and mmfId="+att_MdInventory.getMmfId();
		if(att_MdInventory.getItemKeyId() != null)
			hql += " and itemKeyId="+att_MdInventory.getItemKeyId();
		if(att_MdInventory.getRbaId() != null)
			hql += " and rbaId="+att_MdInventory.getRbaId();
		if(att_MdInventory.getRbsId() != null)
			hql += " and rbsId="+att_MdInventory.getRbsId();
		if(att_MdInventory.getRbbId() != null)
			hql += " and rbbId="+att_MdInventory.getRbbId();
		if(att_MdInventory.getPurchaseType() != null)
			hql += " and purchaseType="+att_MdInventory.getPurchaseType();
		List<MdInventory> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			MdInventory model = list.get(0);
			Double ratio = model.getRatio()!=null?model.getRatio():1d;

//			Double retio = (att_MdInventory.getRatio() == null || att_MdInventory.getRatio() == 0) ? 1 : att_MdInventory.getRatio();
			matNumber = matNumber == null ? 0 : matNumber;
			matNumber = ratio * matNumber; //计算小单位数量
			splitNumber = splitNumber == null ? 0 : splitNumber;
//			splitNumber += matNumber; // 小单位总数量

			model.setBaseNumber(model.getBaseNumber() + splitNumber);
			model.setQuantity(Math.floor(model.getBaseNumber() / ratio));
//			model.setQuantity(model.getQuantity()+att_MdInventory.getQuantity());
			model.setValiedDate(att_MdInventory.getValiedDate());
			model.setRatio(ratio);
			model.setState("1");
			model.setEditDate(new Date());
			this.getHibernateDao().update(model);
			return model;
		}else{
			if (ratio1 != null && !ratio1.equals("")) {
				att_MdInventory.setRatio(Double.parseDouble(ratio1));
			} else {
				att_MdInventory.setRatio(1d);
			}
			matNumber = matNumber == null ? 0 : matNumber;
			matNumber = att_MdInventory.getRatio() * matNumber; //计算小单位数量
			splitNumber = splitNumber == null ? 0 : splitNumber;
//			splitNumber += matNumber; // 小单位总数量
			att_MdInventory.setBaseNumber(splitNumber);
			att_MdInventory.setQuantity(Math.floor(att_MdInventory.getBaseNumber() / att_MdInventory.getRatio()));
			att_MdInventory.setUnit(att_MdInventory.getUnit());
			att_MdInventory.setBasicUnit(att_MdInventory.getBasicUnit());
			att_MdInventory.setState("1");
			att_MdInventory.setCreateDate(new Date());
			att_MdInventory.setEditDate(new Date());
			this.newObject(att_MdInventory);
			return att_MdInventory;
		}
	}
	@Override
	public PagerModel getPagerModelByMdInventoryView(
			MdInventoryView att_MdInventoryView,Double InventoryStart,Double InventoryEnd,Integer safeIsNot, Integer inventoryWarn, Integer validdateWarn, Integer zeroWarn) throws HSKDBException {
		String hql="from MdInventoryView where state=1";
		if(att_MdInventoryView.getRbaId() != null)
			hql += " and rbaId="+att_MdInventoryView.getRbaId();
		if(att_MdInventoryView.getRbsId() != null)
			hql += " and rbsId="+att_MdInventoryView.getRbsId();
		if(att_MdInventoryView.getRbbId() != null)
			hql += " and rbbId="+att_MdInventoryView.getRbbId();
		if(att_MdInventoryView.getMatName() != null && !att_MdInventoryView.getMatName().trim().equals(""))
			hql += " and (matName like '%"+att_MdInventoryView.getMatName().trim()+"%' or matNamePy like '%"+att_MdInventoryView.getMatName().trim().toUpperCase()+"%')";
		if(att_MdInventoryView.getPurchaseType() != null && !att_MdInventoryView.getPurchaseType().trim().equals(""))
			hql += " and purchaseType like '%"+att_MdInventoryView.getPurchaseType().trim()+"%' ";
		if(att_MdInventoryView.getMmfName() != null && !att_MdInventoryView.getMmfName().trim().equals(""))
			hql += " and (mmfName like '%"+att_MdInventoryView.getMmfName().trim()+"%' or mmfNamePy like '%"+att_MdInventoryView.getMmfName().trim().toUpperCase()+"%')";
		//增加是否预警，库存数量筛选等查询条件
		if (safeIsNot!=null&&safeIsNot==1){
			hql+=" and baseNumber > maxShu or baseNumber<warningShu";
		}if (safeIsNot!=null&&safeIsNot==2){
			hql+=" and baseNumber < maxShu and baseNumber>warningShu";
		}
		if (InventoryStart!=null){
			hql+=" and baseNumber>= "+InventoryStart;
		}
		if (InventoryStart!=null){
			hql+=" and baseNumber<= "+InventoryEnd;
		}
		if (inventoryWarn != null) {
//			hql += " and baseNumber <= warningShu ";
			hql += " and (baseNumber > maxShu or baseNumber<warningShu) ";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		if (validdateWarn != null) {
			hql += " and date_sub(valiedDate, interval warningDay day) <= '" + sdf.format(new Date()) + "'";
//			hql += " DATEDIFF('"+sdf1.format(new Date())+"',date_format(valiedDate, '%Y-%m-%d' )) <= warningDay ";
//			('2015-06-12','2015-06-29')
//			hql
//			date_add
//			hql += " DATE_ADD( "+sdf.format(new Date())+",INTERVAL,warningDay day) <=valiedDate";
		}
		if (zeroWarn != null) {
			hql += " and baseNumber <= 0";
		}

		if (att_MdInventoryView.getMdpId() != null) {
			hql += " and mdpId = " + att_MdInventoryView.getMdpId();
		}
		if (att_MdInventoryView.getMdpsId() != null) {
			hql += " and mdpsId = " + att_MdInventoryView.getMdpsId();
		}
		if (att_MdInventoryView.getCheckPart() != null && att_MdInventoryView.getCheckParts() != null) {
			hql += " and (mdpId in (" + att_MdInventoryView.getCheckPart() + ")";
			hql += " or mdpsId in (" + att_MdInventoryView.getCheckParts() + "))";
		} else if (att_MdInventoryView.getCheckPart() != null)
			hql += " and mdpId in (" + att_MdInventoryView.getCheckPart() + ")";
		else if (att_MdInventoryView.getCheckParts() != null)
			hql += " and mdpsId in (" + att_MdInventoryView.getCheckParts() + ")";
		if (att_MdInventoryView.getSearchName() != null && !att_MdInventoryView.getSearchName().equals("")) {
			hql += " and (mmfName like '%" + att_MdInventoryView.getSearchName() + "%'" +
					" or matName like '%" + att_MdInventoryView.getSearchName() + "%'" +
					" or matCode like '%" + att_MdInventoryView.getSearchName() + "%'" +
					" or mmfId2 like '%" + att_MdInventoryView.getSearchName() + "%'" +
					" or mmfCode like '%" + att_MdInventoryView.getSearchName() + "%'" +
					" or wmsMiId2 in (select wmsMiId from MdMaterielInfo where aliasName like '%" + att_MdInventoryView.getSearchName()+ "%'))";
//			hql += " and (wiId in (select wiId from MdInventoryExtend where mmfName like '%" + att_MdInventoryView.getSearchName() + "%'" +
//					" or matName like '%" + att_MdInventoryView.getSearchName() + "%'" +
//					" or matCode like '%" + att_MdInventoryView.getSearchName() + "%'" +
//					" or mmfId like '%" + att_MdInventoryView.getSearchName() + "%'" +
//					" or mmfCode like '%" + att_MdInventoryView.getSearchName() + "%'" +
//					" or wmsMiId in (select wmsMiId from MdMaterielInfo where aliasName like '%" + att_MdInventoryView.getSearchName()+ "%')))";
		}
		if (att_MdInventoryView.getBrand() != null && !att_MdInventoryView.getBrand().equals("")) {
			hql += " and brand like '%" + att_MdInventoryView.getBrand() + "%'";
		}
		if (att_MdInventoryView.getCheckParts() != null) {
			hql += " or mdpsId in (" + att_MdInventoryView.getCheckParts() + "))";
		} else if (att_MdInventoryView.getCheckPart() != null){
			hql += ")";
		}
		if (att_MdInventoryView.getExcludeWiIds() != null && !att_MdInventoryView.getExcludeWiIds().equals("")) {
			hql += " and wiId not in (" + att_MdInventoryView.getExcludeWiIds() + ")";
		}
		if(att_MdInventoryView.getTabOrder() != null && !att_MdInventoryView.getTabOrder().trim().equals(""))
			if (att_MdInventoryView.getTabOrder()=="dateIsNot desc"||att_MdInventoryView.getTabOrder().equals("dateIsNot desc")){
				hql += " order by warningDay asc";
			}else if (att_MdInventoryView.getTabOrder().equals("dateIsNot asc")||att_MdInventoryView.getTabOrder()=="dateIsNot asc"){
				hql += " order by warningDay desc";
			}else if (att_MdInventoryView.getTabOrder().equals("price asc")||att_MdInventoryView.getTabOrder().equals("price asc")){
				hql += " order by avgPrice asc";
			}else if (att_MdInventoryView.getTabOrder().equals("price desc")||att_MdInventoryView.getTabOrder().equals("price desc")){
				hql += " order by avgPrice desc";
			}
			else {
				hql += " order by "+att_MdInventoryView.getTabOrder().trim();
			}
		else
			hql +=" order by editDate desc";

		return this.getHibernateDao().findByPage(hql);
	}

//	getPagerModelByMdInventoryView2
public PagerModel getPagerModelByMdInventoryView2 (MdInventoryView att_MdInventoryView,Double InventoryStart,Double InventoryEnd,Integer safeIsNot, Integer inventoryWarn, Integer validdateWarn, Integer zeroWarn) throws HSKDBException{
		String sql="  SELECT\n" +
				"mdinventor0_.wi_id AS wiId,\n" +
				"mdinventor0_.avg_price AS avgPrice,\n" +
				"mdinventor0_.avg_retail_price AS avgRetailPrice,\n" +
				"mdinventor0_.base_number AS baseNumber,\n" +
				"mdinventor0_.Basic_unit AS basicUnit,\n" +
				"mdinventor0_.brand AS brand,\n" +
				"mdinventor0_.create_date AS createDate,\n" +
				"mdinventor0_.edit_date AS editDate,\n" +
				"mdinventor0_.ITEM_KEY_ID AS itemKeyId,\n" +
				"mdinventor0_.lessen_filecode AS lessenFilecode,\n" +
				"mdinventor0_.mat_code AS matCode,\n" +
				"mdinventor0_.mat_name AS matName,\n" +
				"mdinventor0_.mat_name_py AS matNamePy,\n" +
				"mdinventor0_.max_shu AS maxShu,\n" +
				"mdinventor0_.mdp_id AS mdpId,\n" +
				"mdinventor0_.mdps_id AS mdpsId,\n" +
				"mdinventor0_.mie_id AS mieId,\n" +
				"mdinventor0_.min_day AS minDay,\n" +
				"mdinventor0_.mmf_code AS mmfCode,\n" +
				"mdinventor0_.mmf_id AS mmfId,\n" +
				"mdinventor0_.mmf_id2 AS mmfId2,\n" +
				"mdinventor0_.mmf_name AS mmfName,\n" +
				"mdinventor0_.mmf_name_py AS mmfNamePy,\n" +
				"mdinventor0_.product_factory AS productFactory,\n" +
				"mdinventor0_.purchase_type AS purchaseType,\n" +
				"mdinventor0_.QUANTITY AS quantity,\n" +
				"mdinventor0_.ratio AS ratio,\n" +
				"mdinventor0_.rba_id AS rbaId,\n" +
				"mdinventor0_.rbb_id AS rbbId,\n" +
				"mdinventor0_.rbs_id AS rbsId,\n" +
				"mdinventor0_.state AS state,\n" +
				"mdinventor0_.unit AS unit,\n" +
				"mdinventor0_.valied_date AS valiedDate,\n" +
				"mdinventor0_.warning_day AS warningDay,\n" +
				"mdinventor0_.warning_shu AS warningShu,\n" +
				"mdinventor0_.wms_mi_id AS wmsMiId,\n" +
				"mdinventor0_.wms_mi_id2 AS wmsMiId2,1 AS dateIsNot,\n" +
				"( SELECT a.back_printing FROM md_materiel_info a WHERE a.wms_mi_id = mdinventor0_.wms_mi_id2 ) AS backPrinting,\n" +
				"( SELECT a.Basic_unit_accuracy FROM md_materiel_info a WHERE a.wms_mi_id = mdinventor0_.wms_mi_id2 ) AS basicUnitAccuracy,\n" +
				"( SELECT a.batch_code FROM md_materiel_info a WHERE a.wms_mi_id = mdinventor0_.wms_mi_id2 ) AS batchCode,\n" +
				"( SELECT a.root_path FROM sys_file_info a WHERE a.file_code = mdinventor0_.lessen_filecode ) AS logoPath,\n" +
				"( SELECT a.mat_code FROM md_materiel_info a WHERE a.wms_mi_id = mdinventor0_.wms_mi_id2 ) AS matCode1,\n" +
				"( SELECT a.mdp_name FROM md_materiel_part a WHERE a.mdp_id = mdinventor0_.mdp_id ) AS mdpName,\n" +
				"( SELECT a.mdps_name FROM md_materiel_part_second a WHERE a.mdps_id = mdinventor0_.mdps_id ) AS mdpsName,\n" +
				"( SELECT a.retail_price FROM md_materiel_format a WHERE a.mmf_id = mdinventor0_.mmf_id2 ) AS retailPrice \n" +
				"FROM\n" +
				"\tmd_inventory_view mdinventor0_ \n" +
				"WHERE\n" +
				"\tmdinventor0_.state = 1 ";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	if(att_MdInventoryView.getMatName() != null && !att_MdInventoryView.getMatName().trim().equals(""))
		sql += " and (mdinventor0_.mat_name like '%"+att_MdInventoryView.getMatName().trim()+"%' or mdinventor0_.mat_name_py like '%"+att_MdInventoryView.getMatName().trim().toUpperCase()+"%')";
	if(att_MdInventoryView.getMmfName() != null && !att_MdInventoryView.getMmfName().trim().equals(""))
		sql += " and (mdinventor0_.mmf_name like '%"+att_MdInventoryView.getMmfName().trim()+"%' or mdinventor0_.mmf_name_py like '%"+att_MdInventoryView.getMmfName().trim().toUpperCase()+"%')";
//增加是否预警，库存数量筛选等查询条件
	if (safeIsNot!=null&&safeIsNot==1){
		sql+=" and mdinventor0_.base_number > mdinventor0_.max_shu or mdinventor0_.base_number<mdinventor0_.warning_shu";
	}if (safeIsNot!=null&&safeIsNot==2){
		sql+=" and mdinventor0_.base_number < mdinventor0_.max_shu and mdinventor0_.base_number>mdinventor0_.warning_shu";
	}
	if (InventoryStart!=null){
		sql+=" and mdinventor0_.base_number>= "+InventoryStart;
	}
	if (InventoryStart!=null){
		sql+=" and mdinventor0_.base_number<= "+InventoryEnd;
	}
	if (inventoryWarn != null) {
		sql += " and (mdinventor0_.base_number > mdinventor0_.max_shu or mdinventor0_.base_number<mdinventor0_.warning_shu) ";
	}
	if (validdateWarn != null) {
		sql += " and date_sub(mdinventor0_.valied_date, interval mdinventor0_.warning_day day) <= '" + sdf.format(new Date()) + "'";
	}
	if (att_MdInventoryView.getSearchName() != null && !att_MdInventoryView.getSearchName().equals("")) {
		sql += " and (mdinventor0_.mmf_name like '%" + att_MdInventoryView.getSearchName() + "%'" +
				" or mdinventor0_.mat_name like '%" + att_MdInventoryView.getSearchName() + "%'" +
				" or mdinventor0_.mat_code like '%" + att_MdInventoryView.getSearchName() + "%'" +
				" or mdinventor0_.mmf_id2 like '%" + att_MdInventoryView.getSearchName() + "%'" +
				" or mdinventor0_.mmf_code like '%" + att_MdInventoryView.getSearchName() + "%'" +
				" or mdinventor0_.wms_mi_id2 in (select wms_mi_id from md_materiel_info where alias_name like '%" + att_MdInventoryView.getSearchName()+ "%'))";
	}
	if(att_MdInventoryView.getRbaId() != null)
		sql += " and mdinventor0_.rba_id="+att_MdInventoryView.getRbaId();
	if(att_MdInventoryView.getRbsId() != null)
		sql += " and mdinventor0_.rbs_id="+att_MdInventoryView.getRbsId();
	if(att_MdInventoryView.getRbbId() != null)
		sql += " and mdinventor0_.rbb_id="+att_MdInventoryView.getRbbId();
	if(att_MdInventoryView.getPurchaseType() != null && !att_MdInventoryView.getPurchaseType().trim().equals(""))
		sql += " and mdinventor0_.purchase_type like '%"+att_MdInventoryView.getPurchaseType().trim()+"%' ";
	sql +=" order by mdinventor0_.edit_date desc";

	List<Map<String, Object>> cList = this.getJdbcDao().query("select count(*) as totalCount from (" + sql + ") as t");
	sql += " limit " + ((SystemContext.getOffset() == 0 ? 1 : SystemContext.getOffset()) - 1) * SystemContext.getPageSize() + "," + SystemContext.getPageSize();
	List<Map<String, Object>> list = this.getJdbcDao().query(sql);
	PagerModel pm = new PagerModel();
	pm.setRows(list);
	pm.setItems(list);
	Integer count = 0;
	if (cList == null || cList.isEmpty())
		count = 0;
	Map<String, Object> map = cList.get(0);
	if (map == null || map.isEmpty())
		count = 0;
	else
		count = Integer.parseInt(map.get("totalCount").toString());
	pm.setTotalCount(count);
	pm.setTotal(count);
	return pm;
}



	public List<Map<String, Object>> getPriceSum(MdInventoryView att_MdInventoryView) throws HSKDBException {
		String sql = "  SELECT " +
				"SUM(mdinventor0_.avg_price) as money1s, " +
				"SUM(mdinventor0_.avg_retail_price) as money2s" +
				" FROM " +
				" md_inventory_view mdinventor0_  " +
				"WHERE  " +
				"  mdinventor0_.state = 1 ";

		if (att_MdInventoryView.getSearchName() != null && !att_MdInventoryView.getSearchName().equals("")) {
			sql += " and (mdinventor0_.mmf_name like '%" + att_MdInventoryView.getSearchName() + "%'" +
					" or mdinventor0_.mat_name like '%" + att_MdInventoryView.getSearchName() + "%'" +
					" or mdinventor0_.mat_code like '%" + att_MdInventoryView.getSearchName() + "%'" +
					" or mdinventor0_.mmf_id2 like '%" + att_MdInventoryView.getSearchName() + "%'" +
					" or mdinventor0_.mmf_code like '%" + att_MdInventoryView.getSearchName() + "%'" +
					" )";
		}
		return this.getJdbcDao().query(sql);
	}
	@Override
	public MdInventoryView findMdInventoryViewById(Integer wiId)
			throws HSKDBException {
		MdInventoryView view = new MdInventoryView();
		view.setWiId(wiId);
		view = (MdInventoryView) this.getOne(view);
		return view;
	}

	@Override
	public MdInventoryView findMdInventoryViewByMdInventoryView(
			MdInventoryView att_MdInventoryView) throws HSKDBException {
		MdInventoryView view = new MdInventoryView();
		String hql = "from MdInventoryView where state=1";
		if (att_MdInventoryView.getRbaId() != null)
			hql += " and rbaId=" + att_MdInventoryView.getRbaId();
		if (att_MdInventoryView.getRbsId() != null)
			hql += " and rbsId=" + att_MdInventoryView.getRbsId();
		if (att_MdInventoryView.getRbbId() != null)
			hql += " and rbbId=" + att_MdInventoryView.getRbbId();
		if (att_MdInventoryView.getMmfId() != null) {
			hql += " and mmfId2 = " + att_MdInventoryView.getMmfId();
		}
		if (att_MdInventoryView.getPurchaseType() != null && !att_MdInventoryView.getPurchaseType().equals("")) {
			hql += " and purchaseType = '" + att_MdInventoryView.getPurchaseType() + "'";
		}
		if (att_MdInventoryView.getItemKeyId() != null)
			hql += " and itemKeyId=" + att_MdInventoryView.getItemKeyId();
		List<MdInventoryView> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0)
			view = list.get(0);
		return view;
	}

	@Override
	public List<MdInventoryView> findMdInventoryViewListByMdInventoryView(
			MdInventoryView att_MdInventoryView) throws HSKDBException {
		MdInventoryView view = new MdInventoryView();
		String hql = "from MdInventoryView where state=1";
		if (att_MdInventoryView.getRbaId() != null)
			hql += " and rbaId=" + att_MdInventoryView.getRbaId();
		if (att_MdInventoryView.getRbsId() != null)
			hql += " and rbsId=" + att_MdInventoryView.getRbsId();
		if (att_MdInventoryView.getRbbId() != null)
			hql += " and rbbId=" + att_MdInventoryView.getRbbId();
		if (att_MdInventoryView.getItemKeyIdStr() != null)
			hql += " and itemKeyId in (" + att_MdInventoryView.getItemKeyIdStr() + ")";
		if (att_MdInventoryView.getMatName() != null && !att_MdInventoryView.getMatName().equals(""))
			hql += " and matName like '%" + att_MdInventoryView.getMatName() + "%'";
		if (att_MdInventoryView.getMmfName() != null && !att_MdInventoryView.getMmfName().equals(""))
			hql += " and mmfName like '%" + att_MdInventoryView.getMmfName() + "%'";
		List<MdInventoryView> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0)
			view = list.get(0);
		return list;
	}

	@Override
	public MdInventoryExtendView findMdInventoryExtendViewByMdInventoryExtendView(
			MdInventoryExtendView att_MdInventoryExtendView)
			throws HSKDBException {
		MdInventoryExtendView view = new MdInventoryExtendView();
		String hql = "from MdInventoryExtendView where 1=1";
		if (att_MdInventoryExtendView.getRbaId() != null)
			hql += " and rbaId=" + att_MdInventoryExtendView.getRbaId();
		if (att_MdInventoryExtendView.getRbsId() != null)
			hql += " and rbsId=" + att_MdInventoryExtendView.getRbsId();
		if (att_MdInventoryExtendView.getRbbId() != null)
			hql += " and rbbId=" + att_MdInventoryExtendView.getRbbId();
		if (att_MdInventoryExtendView.getMmfId() != null)
			hql += " and mmfId=" + att_MdInventoryExtendView.getMmfId();
		List<MdInventoryExtendView> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0)
			view = list.get(0);
		return view;
	}

	@Override
	public List<MdInventoryExtendView> findMdInventoryExtendViewListByMdInventoryExtendView(
			MdInventoryExtendView att_MdInventoryExtendView)
			throws HSKDBException {
		MdInventoryExtendView view = new MdInventoryExtendView();
		String hql = "from MdInventoryExtendView where 1=1";
		if (att_MdInventoryExtendView.getRbaId() != null)
			hql += " and rbaId=" + att_MdInventoryExtendView.getRbaId();
		if (att_MdInventoryExtendView.getRbsId() != null)
			hql += " and rbsId=" + att_MdInventoryExtendView.getRbsId();
		if (att_MdInventoryExtendView.getRbbId() != null)
			hql += " and rbbId=" + att_MdInventoryExtendView.getRbbId();
		if (att_MdInventoryExtendView.getMmfIdsStr() != null)
			hql += " and mmfId in (" + att_MdInventoryExtendView.getMmfIdsStr() + ")";
		if (att_MdInventoryExtendView.getMatName() != null && !att_MdInventoryExtendView.getMatName().equals(""))
			hql += " and matName like '%" + att_MdInventoryExtendView.getMatName() + "%'";
		if (att_MdInventoryExtendView.getMmfName() != null && !att_MdInventoryExtendView.getMmfName().equals(""))
			hql += " and mmfName like '%" + att_MdInventoryExtendView.getMmfName() + "%'";
		List<MdInventoryExtendView> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0)
			view = list.get(0);
		return list;
	}
	@Override
	public List<MdInventoryView> getListByMdInventoryView(
			MdInventoryView att_MdInventoryView) throws HSKDBException {
		String hql="from MdInventoryView where state=1";
		if(att_MdInventoryView.getRbaId() != null)
			hql += " and rbaId="+att_MdInventoryView.getRbaId();
		if(att_MdInventoryView.getRbsId() != null)
			hql += " and rbsId="+att_MdInventoryView.getRbsId();
		if(att_MdInventoryView.getRbbId() != null)
			hql += " and rbbId="+att_MdInventoryView.getRbbId();
		if(att_MdInventoryView.getMatName() != null && !att_MdInventoryView.getMatName().trim().equals(""))
			hql += " and (matName like '%"+att_MdInventoryView.getMatName().trim()+"%' or matNamePy like '%"+att_MdInventoryView.getMatName().trim().toUpperCase()+"%')";
		if(att_MdInventoryView.getPurchaseType() != null && !att_MdInventoryView.getPurchaseType().trim().equals(""))
			hql += " and purchaseType like '%"+att_MdInventoryView.getPurchaseType().trim()+"%' ";
		if(att_MdInventoryView.getMmfName() != null && !att_MdInventoryView.getMmfName().trim().equals(""))
			hql += " and (mmfName like '%"+att_MdInventoryView.getMmfName().trim()+"%' or mmfNamePy like '%"+att_MdInventoryView.getMmfName().trim().toUpperCase()+"%')";
		if(att_MdInventoryView.getItemKeyIdStr() != null && !att_MdInventoryView.getItemKeyIdStr().trim().equals(""))
			hql += " and itemKeyId in ("+att_MdInventoryView.getItemKeyIdStr().trim()+")";

		if (att_MdInventoryView.getMdpId() != null) {
			hql += " and mdpId = " + att_MdInventoryView.getMdpId();
		}
		if (att_MdInventoryView.getMdpsId() != null) {
			hql += " and mdpsId = " + att_MdInventoryView.getMdpsId();
		}

		if (att_MdInventoryView.getSearchName() != null && !att_MdInventoryView.getSearchName().equals("")) {
			hql += " and (mmfName like '%" + att_MdInventoryView.getSearchName() + "%'" +
					" or matName like '%" + att_MdInventoryView.getSearchName() + "%'" +
					" or mmfId2 like '%" + att_MdInventoryView.getSearchName() + "%'" +
					" or mmfCode like '%" + att_MdInventoryView.getMmfCode() + "%')";
		}
		if (att_MdInventoryView.getBrand() != null && !att_MdInventoryView.getBrand().equals("")) {
			hql += " and brand like '%" + att_MdInventoryView.getBrand() + "%'";
		}
		if (att_MdInventoryView.getCheckPart() != null && att_MdInventoryView.getCheckParts() != null) {
			hql += " and (mdpId in (" + att_MdInventoryView.getCheckPart() + ")";
			hql += " or mdpsId in (" + att_MdInventoryView.getCheckParts() + "))";
		} else if (att_MdInventoryView.getCheckPart() != null)
			hql += " and mdpId in (" + att_MdInventoryView.getCheckPart() + ")";
		else if (att_MdInventoryView.getCheckParts() != null)
			hql += " and mdpsId in (" + att_MdInventoryView.getCheckParts() + ")";
		if (att_MdInventoryView.getExcludeWiIds() != null && !att_MdInventoryView.getExcludeWiIds().equals("")) {
			hql += " and wiId not in (" + att_MdInventoryView.getExcludeWiIds() + ")";
		}

		//增加是否预警，库存数量筛选等查询条件
		if (att_MdInventoryView.getSafeIsNot()!=null&&att_MdInventoryView.getSafeIsNot().equals("1")){
			hql+=" and baseNumber > maxShu or baseNumber<warningShu";
		}if (att_MdInventoryView.getSafeIsNot()!=null&&att_MdInventoryView.getSafeIsNot().equals("2")){
			hql+=" and baseNumber < maxShu and baseNumber>warningShu";
		}
		hql +=" order by editDate desc";
		return this.getHibernateTemplate().find(hql);
	}
	@Override
	public Map<String, String> getInventoryName(MdInventoryView att_MdInventoryView) throws HSKDBException {
		String sql = "select c.mat_name,c.mmf_name from md_inventory_extend a,md_inventory b,md_item_key c where a.wi_id=b.wi_id and b.item_key_id=c.item_key_id";
		if(att_MdInventoryView != null && att_MdInventoryView.getMmfId() != null)
			sql += " and a.mmf_id="+att_MdInventoryView.getMmfId();
		if(att_MdInventoryView != null && att_MdInventoryView.getRbaId() != null)
			sql += " and b.rba_id="+att_MdInventoryView.getRbaId();
		if(att_MdInventoryView != null && att_MdInventoryView.getRbsId() != null)
			sql += " and b.rbs_id="+att_MdInventoryView.getRbsId();
		if(att_MdInventoryView != null && att_MdInventoryView.getRbbId() != null)
			sql += " and b.rbb_id="+att_MdInventoryView.getRbbId();
		if(att_MdInventoryView.getPurchaseType() != null && !att_MdInventoryView.getPurchaseType().trim().equals(""))
			sql += " and b.purchase_type = '"+att_MdInventoryView.getPurchaseType().trim()+"'";
		sql += " order by a.mmf_id desc";
		List<Map<Object,Object>> reList = this.getJdbcDao().query(sql);
		if(reList != null && reList.size() > 0){
			Map<String,String> reMap = new HashMap<String,String>();
			reMap.put("matName", reList.get(0).get("mat_name")!=null?reList.get(0).get("mat_name").toString():"");
			reMap.put("mmfName", reList.get(0).get("mmf_name")!=null?reList.get(0).get("mmf_name").toString():"");
			return reMap;
		}else
			return null;
	}
	@Override
	public PagerModel getPagerModelByMdInventoryCollectView(
			MdInventoryCollectView att_MdInventoryCollectView)
			throws HSKDBException {
		String hql="from MdInventoryCollectView where state=1";
		if(att_MdInventoryCollectView.getSuiId() != null)
			hql += " and suiId="+att_MdInventoryCollectView.getSuiId();
		if(att_MdInventoryCollectView.getMatName() != null && !att_MdInventoryCollectView.getMatName().trim().equals(""))
			hql += " and (matName like '%"+att_MdInventoryCollectView.getMatName().trim()+"%' or matNamePy like '%"+att_MdInventoryCollectView.getMatName().trim().toUpperCase()+"%')";
		if(att_MdInventoryCollectView.getMmfName() != null && !att_MdInventoryCollectView.getMmfName().trim().equals(""))
			hql += " and (mmfName like '%"+att_MdInventoryCollectView.getMmfName().trim()+"%' or mmfNamePy like '%"+att_MdInventoryCollectView.getMmfName().trim().toUpperCase()+"%')";
		hql += " order by micId desc";
		return this.getHibernateDao().findByPage(hql); 
	}

	@Override
	public List<MdInventoryViewEx> getListByMdInventoryViewEx(MdInventoryViewEx att_mdInventoryView) throws HSKDBException {
		String hql="from MdInventoryViewEx where state=1";
		if(att_mdInventoryView.getRbaId() != null)
			hql += " and rbaId="+att_mdInventoryView.getRbaId();
		if(att_mdInventoryView.getRbsId() != null)
			hql += " and rbsId="+att_mdInventoryView.getRbsId();
		if(att_mdInventoryView.getRbbId() != null)
			hql += " and rbbId="+att_mdInventoryView.getRbbId();
		if(att_mdInventoryView.getMatName() != null && !att_mdInventoryView.getMatName().trim().equals(""))
			hql += " and (matName like '%"+att_mdInventoryView.getMatName().trim()+"%' or matNamePy like '%"+att_mdInventoryView.getMatName().trim().toUpperCase()+"%')";
		if(att_mdInventoryView.getPurchaseType() != null && !att_mdInventoryView.getPurchaseType().trim().equals(""))
			hql += " and purchaseType like '%"+att_mdInventoryView.getPurchaseType().trim()+"%' ";
		if(att_mdInventoryView.getMmfName() != null && !att_mdInventoryView.getMmfName().trim().equals(""))
			hql += " and (mmfName like '%"+att_mdInventoryView.getMmfName().trim()+"%' or mmfNamePy like '%"+att_mdInventoryView.getMmfName().trim().toUpperCase()+"%')";
		if(att_mdInventoryView.getItemKeyIdStr() != null && !att_mdInventoryView.getItemKeyIdStr().trim().equals(""))
			hql += " and itemKeyId in ("+att_mdInventoryView.getItemKeyIdStr().trim()+")";
		if(att_mdInventoryView.getMooId() != null)
			hql += " and moo_id=" + att_mdInventoryView.getMooId();
		hql +=" order by editDate desc";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public Double getPagerModelAllIventoryByMdInventoryView(MdInventoryView att_mdInventoryView) throws HSKDBException {
		String sql = "select sum(QUANTITY) as allCount from md_inventory_view where 1=1";
		if (att_mdInventoryView.getMatName() != null && !att_mdInventoryView.getMatName().equals("")){
			sql += " and mat_name = '" + att_mdInventoryView.getMatName() + "'";
		}
		if (att_mdInventoryView.getWmsMiId2() != null) {
			sql += " and wms_mi_id2 = '" + att_mdInventoryView.getWmsMiId2() + "'";
		}
		if(att_mdInventoryView.getRbaId() != null)
			sql += " and rba_id="+att_mdInventoryView.getRbaId();
		if(att_mdInventoryView.getRbsId() != null)
			sql += " and rbs_id="+att_mdInventoryView.getRbsId();
		if(att_mdInventoryView.getRbbId() != null)
			sql += " and rbb_id="+att_mdInventoryView.getRbbId();
		if(att_mdInventoryView.getPurchaseType() != null && !att_mdInventoryView.getPurchaseType().trim().equals(""))
			sql += " and purchase_type like '%"+att_mdInventoryView.getPurchaseType().trim()+"%' ";
		if(att_mdInventoryView.getMmfName() != null && !att_mdInventoryView.getMmfName().trim().equals(""))
			sql += " and (mmf_name like '%"+att_mdInventoryView.getMmfName().trim()+"%' or mmf_name_py like '%"+att_mdInventoryView.getMmfName().trim().toUpperCase()+"%')";

		sql += " group by mat_name";

		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list == null || list.size() <= 0) {
			return 0d;
		}
		Map<String, Object> map = list.get(0);
		if (map == null || map.size() <= 0) {
			return 0d;
		}
		Object ob = map.get("allCount");
		if (ob == null) {
			return 0d;
		}
		String obs = ob.toString();
		Double res = Double.parseDouble((obs != null && !obs.equals("")) ? obs : "0");
		return res;
	}

	@Override
	public List<Map<String, Object>> getPagerModelAllIventoryByMdInventoryViewMatName(MdInventoryView att_mdInventoryView,String matName) throws HSKDBException {
		String sql = "select wms_mi_id2, mat_name,sum(QUANTITY) as allCount from md_inventory_view where 1=1";
		if (att_mdInventoryView.getMatName() != null && !att_mdInventoryView.getMatName().equals("")){
			sql += " and mat_name = '" + att_mdInventoryView.getMatName() + "'";
		}
		if (att_mdInventoryView.getWmsMiId2() != null) {
			sql += " and wms_mi_id2 = '" + att_mdInventoryView.getWmsMiId2() + "'";
		}
		if(att_mdInventoryView.getRbaId() != null)
			sql += " and rba_id="+att_mdInventoryView.getRbaId();
		if(att_mdInventoryView.getRbsId() != null)
			sql += " and rbs_id="+att_mdInventoryView.getRbsId();
		if(att_mdInventoryView.getRbbId() != null)
			sql += " and rbb_id=" + att_mdInventoryView.getRbbId();
		if (att_mdInventoryView.getPurchaseType() != null && !att_mdInventoryView.getPurchaseType().trim().equals(""))
			sql += " and purchase_type like '%" + att_mdInventoryView.getPurchaseType().trim() + "%' ";
		if (att_mdInventoryView.getMmfName() != null && !att_mdInventoryView.getMmfName().trim().equals(""))
			sql += " and (mmf_name like '%" + att_mdInventoryView.getMmfName().trim() + "%' or mmf_name_py like '%" + att_mdInventoryView.getMmfName().trim().toUpperCase() + "%')";
		if (att_mdInventoryView.getWmsMiIds() != null && !att_mdInventoryView.getWmsMiIds().equals("")) {
			sql += " and wms_mi_id2 in (" + att_mdInventoryView.getWmsMiIds() + ")";
		}
		if (matName != null && !matName.equals("")){
			String[] matNames = matName.split(",");
			sql += " and mat_name in (";
			String middle = "";
			for (String name : matNames) {
				middle += "'" + name + "',";
			}
			if (!middle.equals("")){
				middle = middle.substring(0, middle.length() - 1);
			}
			sql += middle + ")";
		}
		sql += " group by mat_name,wms_mi_id2, mat_name";

		return this.getJdbcDao().query(sql);
	}

	@Override
	public void deleteMdInventoryPaEx(String paieIds) throws HSKDBException {
		String sql = "delete from md_inventory_price_adjustment_ex where paie_id in (" + paieIds + ")";
		this.getJdbcDao().execute(sql);
	}

	@Override
	public void deleteMdInventoryPa(String paiIds) throws HSKDBException {
		String sql = "delete from md_inventory_price_adjustment where pai_id in (" + paiIds + ")";
		this.getJdbcDao().execute(sql);
	}

	@Override
	public Double getInventoryExtendAvgPrice(Integer wiId) throws HSKDBException {
		if (wiId == null) {
			return 0d;
		}
		String sql = "select avg(price) from md_inventory_extend where wi_id = " + wiId;

		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list.isEmpty()) {
			return 0d;
		}
		Map<String, Object> map = list.get(0);
		if (map.isEmpty()) {
			return 0d;
		}
		if (map.get("avg_price") == null)
			return 0d;
		return Double.valueOf(map.get("avg_price").toString());
	}

	@Override
	public List<MdItemMxView> getMdItemMxView(MdItemMxView itemMx) throws HSKDBException {
		String hql = "from MdItemMxView where 1=1";
		if (itemMx.getItemKeyId() != null)
			hql += " and itemKeyId = " + itemMx.getItemKeyId();
		if (itemMx.getItemKeyIds() != null && !itemMx.getItemKeyIds().equals(""))
			hql += " or itemKeyId in (" + itemMx.getItemKeyIds() + ")";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public PagerModel findMdInventoryViewPagerModelByMdInventoryView(MdInventoryView att_MdInventoryExtendView) throws HSKDBException {
		MdInventoryExtendView view = new MdInventoryExtendView();
		String hql = "from MdInventoryView where 1=1";
		if (att_MdInventoryExtendView.getRbaId() != null)
			hql += " and rbaId=" + att_MdInventoryExtendView.getRbaId();
		if (att_MdInventoryExtendView.getRbsId() != null)
			hql += " and rbsId=" + att_MdInventoryExtendView.getRbsId();
		if (att_MdInventoryExtendView.getRbbId() != null)
			hql += " and rbbId=" + att_MdInventoryExtendView.getRbbId();
//		if (att_MdInventoryExtendView.getMmfIdsStr() != null)
//			hql += " and mmfId in (" + att_MdInventoryExtendView.getMmfIdsStr() + ")";
		if (att_MdInventoryExtendView.getMieIds() != null && !att_MdInventoryExtendView.getMieIds().equals(""))
			hql += " and mieId in (" + att_MdInventoryExtendView.getMieIds() + ")";
		if (att_MdInventoryExtendView.getWiIds() != null && !att_MdInventoryExtendView.getWiIds().equals(""))
			hql += " and wiId in (" + att_MdInventoryExtendView.getWiIds() + ")";
		if (att_MdInventoryExtendView.getSearchMatName() != null && !att_MdInventoryExtendView.getSearchMatName().equals(""))
			hql += " and matName like '%" + att_MdInventoryExtendView.getSearchMatName() + "%'";
		if (att_MdInventoryExtendView.getSearchMmfName() != null && !att_MdInventoryExtendView.getSearchMmfName().equals(""))
			hql += " and mmfName like '%" + att_MdInventoryExtendView.getSearchMmfName() + "%'";
		return this.getHibernateDao().findByPage(hql);
	}

	@Override
	public Integer getCheckCount(Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType, Integer checkType) throws HSKDBException {
		String sql = "select count(*) as count from md_check_inventory where 1=1";
		if (rbaId != null)
			sql += " and rba_id = " + rbaId;
		if (rbbId != null)
			sql += " and rbb_id = " + rbbId;
		if (rbsId != null)
			sql += " and rbs_id = " + rbsId;
		if (checkType != null)
			sql += " and check_type = " + checkType;
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list == null && list.isEmpty())
			return 0;
		Map<String, Object> map = list.get(0);
		if (map == null && map.isEmpty())
			return 0;
		Integer count = Integer.parseInt(map.get("count").toString());
		return count;
	}

	@Override
	public Integer getCheckIsCheckCount(Integer ciId, Integer isCheck) throws HSKDBException {
		String sql = "select count(*) as count from md_check_inventory_ex where 1=1";
		if (ciId != null)
			sql += " and ci_id = " + ciId;
		if (isCheck != null) {
			if (isCheck == 1)
				sql += " and is_check = 1";
			if (isCheck == 2)
				sql += " and (is_check <> 1 or is_check is null)";
		}
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list == null && list.isEmpty())
			return 0;
		Map<String, Object> map = list.get(0);
		if (map == null && map.isEmpty())
			return 0;
		Integer count = Integer.parseInt(map.get("count").toString());
		return count;
	}

	@Override
	public List<MdMaterielFormat> findMdMaterielFormat(Integer wmsMiId) throws HSKDBException {
		String sql = "from MdMaterielFormat where 1=1";
		if (wmsMiId != null) {
			sql += " and mmfId in (select mmfId from MdInventoryExtend where wmsMiId = " + wmsMiId + ")";
		}
		return this.getHibernateTemplate().find(sql);
	}

	@Override
	public void deleteCheck(Integer ciId, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException {
		if (ciId == null)
			return;
		String sql = "delete from md_check_inventory where 1=1 and ci_id = " + ciId;
		if (rbaId != null)
			sql += " and rba_id = " + rbaId;
		if (rbsId != null)
			sql += " and rbs_id = " + rbsId;
		if (rbbId != null)
			sql += " and rbb_id = " + rbbId;
		this.getJdbcDao().execute(sql);
	}

	@Override
	public void deleteCheckMx(Integer ciId, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException {
		if (ciId == null)
			return;
		String sql = "delete from md_check_inventory_ex where 1=1 and ci_id = " + ciId + " and ci_id in (select ci_id from md_check_inventory where ci_id = " + ciId + ")";
		this.getJdbcDao().execute(sql);
	}

	@Override
	public void saveMdInventoryExtendByBatch(List<MdInventoryExtend> mdInventoryExtendList) throws HSKDBException {
		for (int idx = 0; idx < mdInventoryExtendList.size(); idx ++) {
			getHibernatesession().save(mdInventoryExtendList.get(idx));
			if (idx % 100 == 0) {
				getHibernatesession().flush();
				getHibernatesession().clear();
			}
		}
	}

	@Override
	public void saveMdEnterWarehouseMxByBatch(List<MdEnterWarehouseMx> mdEnterWarehouseMxList) throws HSKDBException {
		for (int idx = 0; idx < mdEnterWarehouseMxList.size(); idx ++) {
			getHibernatesession().save(mdEnterWarehouseMxList.get(idx));
			if (idx % 100 == 0) {
				getHibernatesession().flush();
				getHibernatesession().clear();
			}
		}
	}

	@Override
	public List<Map<String, Object>> getInventoryNormStringList(String wmsMiIds, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException {
		StringBuffer hql = new StringBuffer("select a.wms_mi_id as wmsMiId, group_concat(DISTINCT c.mmf_name separator '、') as normList " +
				"from md_inventory_extend a " +
				"left join md_inventory b on a.wi_id = b.wi_id " +
				"left join md_materiel_format c on a.mmf_id = c.mmf_id  where 1=1");
		if (rbaId != null)
			hql.append(" and b.rba_id = " + rbaId);
		if (rbsId != null)
			hql.append(" and b.rbs_id = " + rbsId);
		if (rbbId != null)
			hql.append(" and b.rbb_id = " + rbbId);
		if (wmsMiIds != null && !wmsMiIds.equals(""))
			hql.append(" and a.wms_mi_id in (" + wmsMiIds + ")");
		hql.append(" group by a.wms_mi_id");
		return this.getJdbcDao().query(hql.toString());
	}

	@Override
	public MdInventory findMdInventoryByExtend(MdInventoryExtend mdInventoryExtend, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException {
		String sql = "from MdInventory where 1=1";
		if (mdInventoryExtend.getMmfId() != null)
			sql += " and wiId in (select wiId from MdInventoryExtend where mmfId = " + mdInventoryExtend.getMmfId() + ")";
		if (rbaId != null)
			sql += " and rbaId = " + rbaId;
		if (rbbId != null)
			sql += " and rbbId = " + rbbId;
		if (rbsId != null)
			sql += " and rbsId = " + rbsId;
//		if (purchaseType != null && !purchaseType.equals(""))
////			sql += " and purchaseType = '" + purchaseType + "'";
		sql += " order by quantity";
		List<MdInventory> list = this.getHibernateTemplate().find(sql);
		if (list == null || list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public Double getRatioByMdMaterielFomart(MdMaterielFormat mdMaterielFormat, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException {
		StringBuffer hql = new StringBuffer("from MdInventory where wiId in (select wiId from MdInventoryExtend where 1=1");
		if (mdMaterielFormat.getWmsMiId() != null)
			hql.append(" and wmsMiId = " + mdMaterielFormat.getWmsMiId());
		if (mdMaterielFormat.getMmfId() != null)
			hql.append(" and mmfId = " + mdMaterielFormat.getMmfId());
		hql.append(")");
		if (rbaId != null || rbsId != null || rbbId != null) {
			if (rbaId != null)
				hql.append(" and rbaId = " + rbaId);
			if (rbsId != null)
				hql.append(" and rbsId = " + rbsId);
			if (rbbId != null)
				hql.append(" and rbbId = " + rbbId);
		}
		String sql = hql.toString();
		List<MdInventory> list = this.getHibernateTemplate().find(sql);
		if (list == null || list.isEmpty())
			return 0d;
		MdInventory mdInventory = list.get(0);
		return mdInventory.getRatio();
	}

	@Override
	public void updateMdInventoryRatioByInventoryExtend(MdInventoryExtend mdInventoryExtend, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType, String ratio) throws HSKDBException {
		Double r = 1D;
		if (ratio == null || ratio.equals(""))
			r = 1D;
		else
			r = Double.parseDouble(ratio);
		String sql = "update md_inventory set ratio = " + r + " where 1=1";
		if (mdInventoryExtend.getWmsMiId() != null)
			sql += " and wi_id in (select wi_id from md_inventory_extend where wms_mi_id = " + mdInventoryExtend.getWmsMiId() + ")";
		if (rbsId != null)
			sql += " and rbs_id = " + rbsId;
		if (rbaId != null)
			sql += " and rba_id = " + rbaId;
		if (rbbId != null)
			sql += " and rbb_id = " + rbbId;
		if (purchaseType != null && !purchaseType.equals(""))
			sql += " and purchase_type = '" + purchaseType + "'";
		this.getJdbcDao().execute(sql);
	}

	@Override
	public void deleteMergeLog(Integer wiId, String addMmfIds) throws HSKDBException {
		if (wiId == null && (addMmfIds == null || addMmfIds.equals("")))
			return;
		String sql = "delete from md_inventory_merge_log where 1=1";
		if (wiId != null)
			sql += " and wi_id = " + wiId;
		if (addMmfIds != null && !addMmfIds.equals(""))
			sql += " and mmf_id in (" + addMmfIds + ")";
		this.getJdbcDao().execute(sql);
	}

	@Override
	public Map<String, Object> getOhterInfo(Integer wiId) throws HSKDBException {
		String sql = "select back_printing as backPrinting from md_enter_warehouse_mx where 1=1";
		if (wiId != null)
			sql += " and mmf_id in (select mmf_id from md_inventory_extend where wi_id = " + wiId + ")";
		sql += " and back_printing is not null limit 1";
		List<Map<String, String>> list = this.getJdbcDao().query(sql);
		String bp = "";
		if (!list.isEmpty())
			bp = list.get(0).get("backPrinting");

		String sql1 = "select batch_certNo as batchCode from md_enter_warehouse_mx where 1=1";
		if (wiId != null)
			sql1 += " and mmf_id in (select mmf_id from md_inventory_extend where wi_id = " + wiId + ")";
		sql1 += " and batch_certNo is not null limit 1";
		list = this.getJdbcDao().query(sql1);
		String bc = "";
		if (!list.isEmpty())
			bc = list.get(0).get("batchCode");

		String sql2 = "select if(product_valitime is null, '', date_format(product_valitime, '%Y-%m-%d %H:%i:%s' )) as valiedDate from md_enter_warehouse_mx where 1=1";
		if (wiId != null)
			sql2 += " and mmf_id in (select mmf_id from md_inventory_extend where wi_id = " + wiId + ")";
		sql2 += " and product_valitime is not null limit 1";
		list = this.getJdbcDao().query(sql2);
		String pv = "";
		if (!list.isEmpty())
			pv = list.get(0).get("valiedDate");

		Map<String, Object> map = new HashMap<>();
		map.put("backPrinting", bp);
		map.put("batchCode", bc);
		map.put("valiedDate", pv);

		return map;
	}
}