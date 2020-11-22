package com.hsk.dentistmall.web.transaction.service.imp;

import java.util.*;

import com.google.inject.internal.Objects;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage; 
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.dentistmall.web.transaction.service.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;


/** 
  transaction业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:17:00
 */
 
@Service
public class  MdOrderMxService extends DSTService implements IMdOrderMxService {	
   /**
   *业务处理dao类  mdOrderMxDao 
   */
	@Autowired
	protected IMdOrderMxDao mdOrderMxDao;
	@Autowired
	private IMdOrderInfoDao mdOrderInfoDao;
	@Autowired
	private IMdInventoryDao mIMdInventoryDao;
	@Autowired
	private IMdInventoryExtendDao mdInventoryExtendDao;

 /**
	 * 根据md_order_mx表主键删除MdOrderMx对象记录
     * @param  momId  Integer类型(md_order_mx表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer momId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);
			  	
			try{
		   			MdOrderMx     att_MdOrderMx= mdOrderMxDao.findMdOrderMxById( momId) ;
					srm.setObj(att_MdOrderMx);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	} 

    /**
	 * 根据md_order_mx表主键删除MdOrderMx对象记录
     * @param  momId  Integer类型(md_order_mx表主键)
	 * @throws HSKException
	 */

	public MdOrderMx findObject(Integer momId) throws HSKException{
			MdOrderMx  att_MdOrderMx=new MdOrderMx();		
			try{
		        att_MdOrderMx= mdOrderMxDao.findMdOrderMxById( momId) ;
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdOrderMx;
	}
	
	 
	 /**
	 * 根据md_order_mx表主键删除MdOrderMx对象记录
     * @param  momId  Integer类型(md_order_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer momId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
				mdOrderMxDao.deleteMdOrderMxById(momId);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}
	
	/**
	 * 根据md_order_mx表主键删除多条MdOrderMx对象记录
//     * @param  MomIds  Integer类型(md_order_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String momIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = momIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					mdOrderMxDao.deleteMdOrderMxById(Integer
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
	 * 新增或修改md_order_mx表记录 ,如果md_order_mx表主键MdOrderMx.MomId为空就是添加，如果非空就是修改
     * @param  att_MdOrderMx  MdOrderMx类型(md_order_mx表记录)
     * @return MdOrderMx  修改后的MdOrderMx对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject( MdOrderMx att_MdOrderMx) throws HSKException{
			 SysRetrunMessage srm = new SysRetrunMessage(1l);
			  try{
					mdOrderMxDao.saveOrUpdateMdOrderMx(att_MdOrderMx); 
					srm.setObj(att_MdOrderMx);
			    } catch (Exception e) {
					e.printStackTrace(); 
					throw new HSKException(e);
		        }
			return srm;	  
	}
	
	 
	/**
	 *根据MdOrderMx对象作为对(md_order_mx表进行查询，获取分页对象
     * @param  att_MdOrderMx  MdOrderMx类型(md_order_mx表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdOrderMx att_MdOrderMx) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdOrderMx>());
			  try{
					pm=mdOrderMxDao.getPagerModelByMdOrderMx(att_MdOrderMx);
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
		return pm;
	}

	@Override
	public PagerModel getMdOrderMxListByMoiId(Integer moiId, String str, Integer wowId, Integer wiId)throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdOrderMx>());
		try{
			String searchName1 = "";
			String searchName2 = "";
			if (str != null) {
				String[] arary = str.split(",");
				searchName1 = arary[0];
				if (arary.length > 1)
					searchName2 = arary[1];
			}
			MdOrderInfo mdOrderInfo = new MdOrderInfo();
			mdOrderInfo.setMoiId(moiId);
			mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);
			MdOrderMx att_MdOrderMx = new MdOrderMx();
			att_MdOrderMx.setMoiId(moiId);
			if (!searchName1.equals(""))
				att_MdOrderMx.setSearchMatName(searchName1);
			if (!searchName2.equals(""))
				att_MdOrderMx.setSearchMmfName(searchName2);
			List<MdOrderMx> list = mdOrderMxDao.getListByMdOrderMx(att_MdOrderMx);

			MdOutWarehouseMx mdOutWarehouseMx = new MdOutWarehouseMx();
			List<MdOutWarehouseMx> mxList = null;
			if (wowId != null) {
				mdOutWarehouseMx.setWowId(wowId);
				mxList = this.getList(mdOutWarehouseMx);
			}

			SysUserInfo account = this.GetOneSessionAccount();
			if(list != null && list.size() > 0){
				List<MdOrderMx> xlist = new ArrayList<MdOrderMx>();
				for(MdOrderMx mx: list){
					if(mx.getEnterNumber() !=null && mx.getEnterNumber() > 0)
						xlist.add(mx);
				}
				list=xlist;
				List<MdItemMxView> itemMxList;
				MdItemMxView mdItemMxView;
				boolean hh;
				Map<String, Object> otherMap = new HashMap<>();
				for(MdOrderMx mx: list){
					hh = false;
					//查询库存数并显示到页面
					MdInventoryView att_MdInventoryView= new MdInventoryView();
					if(account.getOrganizaType().equals("20001")){
						att_MdInventoryView.setRbaId(account.getOldId());
						att_MdInventoryView.setPurchaseType("1");
					  }else if(account.getOrganizaType().equals("20002")){
						  att_MdInventoryView.setRbsId(account.getOldId());
						  att_MdInventoryView.setPurchaseType("2");
					  }else if(account.getOrganizaType().equals("20003")){
						  att_MdInventoryView.setRbbId(account.getOldId());
						  att_MdInventoryView.setPurchaseType("3");
					  }
					att_MdInventoryView.setMmfId(mx.getMmfId());
//					if (wiId != null)
//						att_MdInventoryView.setWiId(wiId);
					att_MdInventoryView = mIMdInventoryDao.findMdInventoryViewByMdInventoryView(att_MdInventoryView);
					mx.setRatio(att_MdInventoryView.getRatio());
					otherMap = mIMdInventoryDao.getOhterInfo(att_MdInventoryView.getWiId());
					mx.setBackPrinting(otherMap.get("backPrinting").toString());
					mx.setBatchCode(otherMap.get("batchCode").toString());
					mx.setValiedDate(otherMap.get("valiedDate").toString());
					mdItemMxView = new MdItemMxView();
					mdItemMxView.setItemKeyId(Integer.parseInt(att_MdInventoryView.getItemKeyId()));
					itemMxList = mIMdInventoryDao.getMdItemMxView(mdItemMxView);
					if (mx.getMoiId() != null) {
						if (mxList != null) {
							for (MdOutWarehouseMx md : mxList) {
//								if (Objects.equal(md.getMoiId(), mx.getMoiId())) {
								for (MdItemMxView mdItemMxView1 : itemMxList) {
									if (Objects.equal(mdItemMxView1.getMmfId(), md.getMmfId())) {
										hh = true;
										break;
									}
								}
								if (hh) {
									mx.setWowMxId(md.getWowMxId());
									mx.setBaseNumber((mx.getBaseNumber() == null ? 0 : mx.getBaseNumber()) + (md.getBaseNumber() == null ? 0 : md.getBaseNumber()));
									mx.setSplitNumber((mx.getSplitNumber() == null ? 0 : mx.getSplitNumber()) + (md.getSplitQuantity() == null ? 0 : md.getSplitQuantity()));
								}
								hh = false;
//									else {
//										mx.setWowMxId(md.getWowMxId());
//										mx.setBaseNumber(md.getBaseNumber());
//										mx.setSplitNumber(md.getSplitQuantity());
//									}
//								}
							}
						}
					}
					if(att_MdInventoryView != null && att_MdInventoryView.getWiId() != null){
						mx.setWiId(att_MdInventoryView.getWiId());
						mx.setMieId(att_MdInventoryView.getMieId());
						mx.setAvgPrice(att_MdInventoryView.getAvgPrice());
						mx.setAllPrice(mx.getTotalMoney());
						mx.setAllRetailPrice(att_MdInventoryView.getAvgRetailPrice());
						MdInventoryExtend mdInventoryExtend = mdInventoryExtendDao.getMdInventoryExtendByWiId(att_MdInventoryView.getWiId(), mdOrderInfo.getOrderCode());
						if (mdInventoryExtend == null) {
							mx.setQuantity(0d);
							mx.setSplitQuantity(0d);
							continue;
						}
						mx.setMieId(mdInventoryExtend.getMieId());
						mx.setQuantity(mdInventoryExtend.getQuantity());
						mx.setSplitQuantity(mdInventoryExtend.getBaseNumber());
					}else {
						mx.setQuantity(0d);
						mx.setSplitQuantity(0d);
						mx.setRatio(0D);
					}
				}
			}
			pm.setItems(list);
			pm.setRows(list);
	    } catch (Exception e) {
			e.printStackTrace(); 
        }
		return pm;
	}
	
	@Override
	public PagerModel getMdOrderMxListByMoiIdForEnter(Integer moiId)throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdOrderMx>());
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			
			MdOrderInfo mdOrderInfo = new MdOrderInfo();
			mdOrderInfo.setMoiId(moiId);
			mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);
			MdOrderMx att_MdOrderMx = new MdOrderMx();
			att_MdOrderMx.setMoiId(moiId);
			List<MdOrderMx> list = mdOrderMxDao.getListByMdOrderMx(att_MdOrderMx);
			for(MdOrderMx mx: list){
				MdInventoryView att_MdInventoryView= new MdInventoryView();
				att_MdInventoryView.setMmfId(mx.getMmfId());
				if(account.getOrganizaType().equals("20001")){
					att_MdInventoryView.setRbaId(account.getOldId());
					att_MdInventoryView.setPurchaseType("1");
				}else if(account.getOrganizaType().equals("20002")){
					att_MdInventoryView.setRbsId(account.getOldId());
					att_MdInventoryView.setPurchaseType("2");
				}else if(account.getOrganizaType().equals("20003")){
					att_MdInventoryView.setRbbId(account.getOldId());
					att_MdInventoryView.setPurchaseType("3");
				}
				Map<String,String> nameMap = mIMdInventoryDao.getInventoryName(att_MdInventoryView);
				if(nameMap!=null && nameMap.get("matName")!= null && !"".equals(nameMap.get("matName"))){
					//mx.setmat
				}else{
					
				}
				
			}
			
			pm.setItems(list);
			pm.setRows(list);
	    } catch (Exception e) {
			e.printStackTrace();
        }
		return pm;
	}

	@Override
	public PagerModel getOrderMxListByMoiId(Integer moiId, Integer limit, Integer page)
			throws HSKException {
		PagerModel pm = new PagerModel();
		try {
			List<Map<String, Object>> mxList = mdOrderMxDao.getMxListModelByMoiId(moiId, null, limit, page, null);
			List<Map<String, Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(moiId, null);
			Map<String, Object> map = listCount.get(0);
			mxList.add(map);
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotalCount(Integer.parseInt(map.get("total_count").toString()));
			pm.setTotal(Integer.parseInt(map.get("total_count").toString()));
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public PagerModel getOrderMxListBySearch(Integer moiId, Integer limit, Integer page, String matName, Integer state) throws HSKException{
		PagerModel pm = new PagerModel();
		try {
			List<Map<String, Object>> mxList = mdOrderMxDao.getMxListModelByMoiId(moiId, limit, page, matName, state);
			List<Map<String, Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(moiId, null);
			Map<String, Object> map = listCount.get(0);
			mxList.add(map);
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotalCount(Integer.parseInt(map.get("total_count").toString()));
			pm.setTotal(Integer.parseInt(map.get("total_count").toString()));
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public SysRetrunMessage getOrderMxListByMoiId2(Integer moiId)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		MdOrderMx att_MdOrderMx = new MdOrderMx();
		att_MdOrderMx.setMoiId(moiId);
		try {
			List<MdOrderMx> list = mdOrderMxDao.getListByMdOrderMx(att_MdOrderMx);
			srm.setObj(list);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return srm;
	}

	@Override
	public PagerModel getOrderMxName(Integer moiId, String matName, Integer limit, Integer page) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList<Map<String,Object>>());
		try {
			List<Map<String,Object>> mxList = mdOrderMxDao.getMxListByMatName(moiId, matName, limit, page);
			List<Map<String,Object>> listCount = mdOrderMxDao.getMxListByMatNameCount(moiId, matName);
			Map<String,Object> map = listCount.get(0);
			mxList.add(map);
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotalCount(Integer.parseInt(map.get("total_count").toString()));
			pm.setTotal(Integer.parseInt(map.get("total_count").toString()));
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return pm;
	}

}