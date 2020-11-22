package com.hsk.miniapi.dentistmall.web.storage.service.imp;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage; 
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.miniapi.dentistmall.web.storage.service.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.miniapi.dentistmall.api.daobbase.*;


/** 
  storage业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:55
 */
 
@Service
public class MdOutWarehouseMxApiService extends DSTApiService implements IMdOutWarehouseMxApiService {
   /**
   *业务处理dao类  mdOutWarehouseMxDao 
   */
	@Autowired
	protected IMdOutWarehouseMxApiDao mdOutWarehouseMxDao;


 /**
	 * 根据md_out_warehouse_mx表主键删除MdOutWarehouseMx对象记录
     * @param  wowMxId  Integer类型(md_out_warehouse_mx表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer wowMxId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);
			  	
			try{
		   			MdOutWarehouseMx     att_MdOutWarehouseMx= mdOutWarehouseMxDao.findMdOutWarehouseMxById( wowMxId) ;
					srm.setObj(att_MdOutWarehouseMx);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	} 

    /**
	 * 根据md_out_warehouse_mx表主键删除MdOutWarehouseMx对象记录
     * @param  wowMxId  Integer类型(md_out_warehouse_mx表主键)
	 * @throws HSKException
	 */

	public MdOutWarehouseMx findObject(Integer wowMxId) throws HSKException{
			MdOutWarehouseMx  att_MdOutWarehouseMx=new MdOutWarehouseMx();		
			try{
		        att_MdOutWarehouseMx= mdOutWarehouseMxDao.findMdOutWarehouseMxById( wowMxId) ;
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdOutWarehouseMx;
	}
	
	 
	 /**
	 * 根据md_out_warehouse_mx表主键删除MdOutWarehouseMx对象记录
     * @param  wowMxId  Integer类型(md_out_warehouse_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wowMxId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
				mdOutWarehouseMxDao.deleteMdOutWarehouseMxById(wowMxId);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}
	
	/**
	 * 根据md_out_warehouse_mx表主键删除多条MdOutWarehouseMx对象记录
     * @param  WowMxIds  Integer类型(md_out_warehouse_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wowMxIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = wowMxIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					mdOutWarehouseMxDao.deleteMdOutWarehouseMxById(Integer
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
	 * 新增或修改md_out_warehouse_mx表记录 ,如果md_out_warehouse_mx表主键MdOutWarehouseMx.WowMxId为空就是添加，如果非空就是修改
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
     * @return MdOutWarehouseMx  修改后的MdOutWarehouseMx对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject( MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKException{
			 SysRetrunMessage srm = new SysRetrunMessage(1l);
			  try{
					mdOutWarehouseMxDao.saveOrUpdateMdOutWarehouseMx(att_MdOutWarehouseMx); 
					srm.setObj(att_MdOutWarehouseMx);
			    } catch (Exception e) {
					e.printStackTrace(); 
					throw new HSKException(e);
		        }
			return srm;	  
	}
	
	 
	/**
	 *根据MdOutWarehouseMx对象作为对(md_out_warehouse_mx表进行查询，获取分页对象
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdOutWarehouseMx>());
			  try{
					pm=mdOutWarehouseMxDao.getPagerModelByMdOutWarehouseMx(att_MdOutWarehouseMx);
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
		return pm;
	}

	@Override
	public PagerModel getMdOutWarehouseMxListByMoiId(Integer wowId)
			throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdOutWarehouseMx>());
		try{
			MdOutWarehouseMx att_MdOutWarehouseMx = new MdOutWarehouseMx();
			att_MdOutWarehouseMx.setWowId(wowId);
			List<MdOutWarehouseMx> list = mdOutWarehouseMxDao.getListByMdOutWarehouseMx(att_MdOutWarehouseMx);
			pm.setItems(list);
			pm.setRows(list);
	    } catch (Exception e) {
			e.printStackTrace(); 
        }
		return pm;
	}

	@Override
	public PagerModel getMdOutWarehouseMxListByMmfId(MdOutWarehouse att_MdOutWarehouse,MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<Map<Object,Object>>());
		  try{
			  SysUserInfo account = this.GetOneSessionAccount();
			  if(account.getOrganizaType().equals("20001")){
				  att_MdOutWarehouse.setRbaId(account.getOldId());
				  att_MdOutWarehouse.setPurchaseType("1");
			  }else if(account.getOrganizaType().equals("20002")){
				  att_MdOutWarehouse.setRbsId(account.getOldId());
				  att_MdOutWarehouse.setPurchaseType("2");
			  }else if(account.getOrganizaType().equals("20003")){
				  att_MdOutWarehouse.setRbbId(account.getOldId());
				  att_MdOutWarehouse.setPurchaseType("3");
			  }
			  pm=mdOutWarehouseMxDao.getPagerMdOutOrderMxByMmfId(att_MdOutWarehouse, att_MdOutWarehouseMx);
		    } catch (Exception e) {
				e.printStackTrace(); 
	        }
		  return pm;
	} 
	
	@Override
	public PagerModel getMdOutWarehouseMxListByWiId(MdOutWarehouse att_MdOutWarehouse,MdOutWarehouseMx att_MdOutWarehouseMx,Integer wiId) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<Map<Object,Object>>());
		  try{
			  SysUserInfo account = this.GetOneSessionAccount();
			  if(account.getOrganizaType().equals("20001")){
				  att_MdOutWarehouse.setRbaId(account.getOldId());
				  att_MdOutWarehouse.setPurchaseType("1");
			  }else if(account.getOrganizaType().equals("20002")){
				  att_MdOutWarehouse.setRbsId(account.getOldId());
				  att_MdOutWarehouse.setPurchaseType("2");
			  }else if(account.getOrganizaType().equals("20003")){
				  att_MdOutWarehouse.setRbbId(account.getOldId());
				  att_MdOutWarehouse.setPurchaseType("3");
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
				  att_MdOutWarehouseMx.setMmfId_str(mmfIds);
				  pm=mdOutWarehouseMxDao.getPagerMdOutOrderMxByMmfId(att_MdOutWarehouse, att_MdOutWarehouseMx);
			  }
			  
		    } catch (Exception e) {
				e.printStackTrace(); 
	        }
		  return pm;
	} 
	 
}