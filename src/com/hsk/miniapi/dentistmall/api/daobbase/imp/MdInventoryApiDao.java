package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.util.*;

import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_inventory表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:54
 */
@Component
public class MdInventoryApiDao extends SupperDao implements IMdInventoryApiDao {

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
	public MdInventory insertMdInventory(MdInventory att_MdInventory)throws HSKDBException {
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
			model.setBaseNumber(model.getBaseNumber()+(att_MdInventory.getQuantity()*ratio));
			model.setQuantity(model.getQuantity()+att_MdInventory.getQuantity());
			model.setRatio(ratio);
			model.setState("1");
			model.setEditDate(new Date());
			this.getHibernateDao().update(model);
			return model;
		}else{
			att_MdInventory.setRatio(1d);
			att_MdInventory.setBaseNumber(att_MdInventory.getQuantity());
			att_MdInventory.setBasicUnit(att_MdInventory.getUnit());
			att_MdInventory.setState("1");
			att_MdInventory.setCreateDate(new Date());
			att_MdInventory.setEditDate(new Date());
			this.newObject(att_MdInventory);
			return att_MdInventory;
		}
	}
	@Override
	public PagerModel getPagerModelByMdInventoryView(
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
		if(att_MdInventoryView.getTabOrder() != null && !att_MdInventoryView.getTabOrder().trim().equals(""))
			hql += " order by "+att_MdInventoryView.getTabOrder().trim();
		else
			hql +=" order by editDate desc";
		return this.getHibernateDao().findByPage(hql); 
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

}