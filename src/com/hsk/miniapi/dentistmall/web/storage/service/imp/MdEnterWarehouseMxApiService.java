package com.hsk.miniapi.dentistmall.web.storage.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.persistence.MdEnterwarehousemxMaterielEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdEnterWarehouseMxApiDao;
import com.hsk.dentistmall.api.persistence.MdEnterWarehouse;
import com.hsk.dentistmall.api.persistence.MdEnterWarehouseMx;
import com.hsk.dentistmall.api.persistence.MdInventoryExtend;
import com.hsk.miniapi.dentistmall.web.storage.service.IMdEnterWarehouseMxApiService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;


/** 
  storage业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:54
 */
 
@Service
public class MdEnterWarehouseMxApiService extends DSTApiService implements IMdEnterWarehouseMxApiService {
   /**
   *业务处理dao类  mdEnterWarehouseMxDao 
   */
	@Autowired
	protected IMdEnterWarehouseMxApiDao mdEnterWarehouseMxDao;


 /**
	 * 根据md_enter_warehouse_mx表主键删除MdEnterWarehouseMx对象记录
     * @param  wewMxId  Integer类型(md_enter_warehouse_mx表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer wewMxId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);
			  	
			try{
		   			MdEnterWarehouseMx     att_MdEnterWarehouseMx= mdEnterWarehouseMxDao.findMdEnterWarehouseMxById( wewMxId) ;
					srm.setObj(att_MdEnterWarehouseMx);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	} 

    /**
	 * 根据md_enter_warehouse_mx表主键删除MdEnterWarehouseMx对象记录
     * @param  wewMxId  Integer类型(md_enter_warehouse_mx表主键)
	 * @throws HSKException
	 */

	public MdEnterWarehouseMx findObject(Integer wewMxId) throws HSKException{
			MdEnterWarehouseMx  att_MdEnterWarehouseMx=new MdEnterWarehouseMx();		
			try{
		        att_MdEnterWarehouseMx= mdEnterWarehouseMxDao.findMdEnterWarehouseMxById( wewMxId) ;
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdEnterWarehouseMx;
	}
	
	 
	 /**
	 * 根据md_enter_warehouse_mx表主键删除MdEnterWarehouseMx对象记录
     * @param  wewMxId  Integer类型(md_enter_warehouse_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wewMxId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
				mdEnterWarehouseMxDao.deleteMdEnterWarehouseMxById(wewMxId);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}
	
	/**
	 * 根据md_enter_warehouse_mx表主键删除多条MdEnterWarehouseMx对象记录
     * @param  WewMxIds  Integer类型(md_enter_warehouse_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wewMxIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = wewMxIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					mdEnterWarehouseMxDao.deleteMdEnterWarehouseMxById(Integer
							.valueOf(did));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			HSKException hse = new HSKException(e);
			hse.setDisposeType("01");
			throw hse;
		} catch (HSKDBException e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
		return srm;	  
	 }
	 
 
	 /**
	 * 新增或修改md_enter_warehouse_mx表记录 ,如果md_enter_warehouse_mx表主键MdEnterWarehouseMx.WewMxId为空就是添加，如果非空就是修改
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
     * @return MdEnterWarehouseMx  修改后的MdEnterWarehouseMx对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject( MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKException{
			 SysRetrunMessage srm = new SysRetrunMessage(1l);
			  try{
					mdEnterWarehouseMxDao.saveOrUpdateMdEnterWarehouseMx(att_MdEnterWarehouseMx); 
					srm.setObj(att_MdEnterWarehouseMx);
			    } catch (Exception e) {
					e.printStackTrace(); 
					throw new HSKException(e);
		        }
			return srm;	  
	}
	
	 
	/**
	 *根据MdEnterWarehouseMx对象作为对(md_enter_warehouse_mx表进行查询，获取分页对象
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdEnterWarehouseMx>());
			  try{
					pm=mdEnterWarehouseMxDao.getPagerModelByMdEnterWarehouseMx(att_MdEnterWarehouseMx);
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
		return pm;
	}

	@Override
	public PagerModel getMdEnterWarehouseMxListByMoiId(Integer wewId)
			throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdEnterwarehousemxMaterielEntity>());
		try{
			MdEnterWarehouseMx att_MdEnterWarehouseMx = new MdEnterWarehouseMx();
			att_MdEnterWarehouseMx.setWewId(wewId);
			List<MdEnterwarehousemxMaterielEntity> list = mdEnterWarehouseMxDao.getListByMdEnterWarehouseMx(att_MdEnterWarehouseMx);
			pm.setItems(list);
			pm.setRows(list);
	    } catch (Exception e) {
			e.printStackTrace(); 
        }
		return pm;
	}

	@Override
	public PagerModel getMdEnterWarehouseMxListByMmfId(MdEnterWarehouse att_MdEnterWarehouse,MdEnterWarehouseMx att_MdEnterWarehouseMx)
			throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<Map<Object,Object>>());
		  try{
			  SysUserInfo account = this.GetOneSessionAccount();
			  if(account.getOrganizaType().equals("20001")){
				  att_MdEnterWarehouse.setRbaId(account.getOldId());
				  att_MdEnterWarehouse.setPurchaseType("1");
			  }else if(account.getOrganizaType().equals("20002")){
				  att_MdEnterWarehouse.setRbsId(account.getOldId());
				  att_MdEnterWarehouse.setPurchaseType("2");
			  }else if(account.getOrganizaType().equals("20003")){
				  att_MdEnterWarehouse.setRbbId(account.getOldId());
				  att_MdEnterWarehouse.setPurchaseType("3");
			  }
			  pm=mdEnterWarehouseMxDao.getPagerMdEnterWarehouseMxBymmfId(att_MdEnterWarehouse, att_MdEnterWarehouseMx);
		    } catch (Exception e) {
				e.printStackTrace(); 
	        }
		  return pm;
	}

	@Override
	public PagerModel getMdEnterWarehouseMxListByWiId(MdEnterWarehouse att_MdEnterWarehouse,MdEnterWarehouseMx att_MdEnterWarehouseMx, Integer wiId)
			throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<Map<Object,Object>>());
		  try{
			  SysUserInfo account = this.GetOneSessionAccount();
			  if(account.getOrganizaType().equals("20001")){
				  att_MdEnterWarehouse.setRbaId(account.getOldId());
				  att_MdEnterWarehouse.setPurchaseType("1");
			  }else if(account.getOrganizaType().equals("20002")){
				  att_MdEnterWarehouse.setRbsId(account.getOldId());
				  att_MdEnterWarehouse.setPurchaseType("2");
			  }else if(account.getOrganizaType().equals("20003")){
				  att_MdEnterWarehouse.setRbbId(account.getOldId());
				  att_MdEnterWarehouse.setPurchaseType("3");
			  }
			  MdInventoryExtend extend = new MdInventoryExtend();
			  extend.setWiId(wiId);
			  List<MdInventoryExtend> extendList = this.getList(extend);
			  if(extendList != null && extendList.size() > 0){
				  String mmfIds = ",";
				  for(MdInventoryExtend ex : extendList){
					  if(!mmfIds.contains(","+ex.getMmfId()+","))
						  mmfIds += ex.getMmfId()+",";
				  }
				  mmfIds = mmfIds.substring(1, mmfIds.length()-1);
				  att_MdEnterWarehouseMx.setMmfId_str(mmfIds);
				  pm=mdEnterWarehouseMxDao.getPagerMdEnterWarehouseMxBymmfId(att_MdEnterWarehouse, att_MdEnterWarehouseMx);
			  }
			  
		    } catch (Exception e) {
				e.printStackTrace(); 
	        }
		  return pm;
	} 
}