package com.hsk.dentistmall.web.outOrder.service.imp;

import java.util.*;

import com.hsk.dentistmall.api.daobbase.IMdMaterielInfoDao;
import com.hsk.dentistmall.api.persistence.*;

import com.hsk.xframe.api.daobbase.ISysFileInfoDao;
import org.apache.derby.tools.sysinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.api.daobbase.IMdInventoryDao;
import com.hsk.dentistmall.api.daobbase.IMdInventoryExtendDao;
import com.hsk.dentistmall.api.daobbase.IMdOutOrderMxDao;
import com.hsk.dentistmall.web.outOrder.service.IMdOutOrderMxService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;


/** 
  outOrder业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-11-13 11:22:28
 */
 
@Service
public class  MdOutOrderMxService extends DSTService implements IMdOutOrderMxService {	
   /**
   *业务处理dao类  mdOutOrderMxDao 
   */
	@Autowired
	protected IMdOutOrderMxDao mdOutOrderMxDao;
	@Autowired
	private IMdInventoryDao mIMdInventoryDao;
	@Autowired
	private IMdInventoryExtendDao mdInventoryExtendDao;
	@Autowired
	protected IMdMaterielInfoDao mdMaterielInfoDao;
	@Autowired
	private ISysFileInfoDao sysFileInfoDao;

 /**
	 * 根据md_out_order_mx表主键删除MdOutOrderMx对象记录
     * @param  momId  Integer类型(md_out_order_mx表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer momId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);
			  	
			try{
		   			MdOutOrderMx     att_MdOutOrderMx= mdOutOrderMxDao.findMdOutOrderMxById( momId) ;
					srm.setObj(att_MdOutOrderMx);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	} 

    /**
	 * 根据md_out_order_mx表主键删除MdOutOrderMx对象记录
     * @param  momId  Integer类型(md_out_order_mx表主键)
	 * @throws HSKException
	 */

	public MdOutOrderMx findObject(Integer momId) throws HSKException{
			MdOutOrderMx  att_MdOutOrderMx=new MdOutOrderMx();		
			try{
		        att_MdOutOrderMx= mdOutOrderMxDao.findMdOutOrderMxById( momId) ;
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdOutOrderMx;
	}
	
	 
	 /**
	 * 根据md_out_order_mx表主键删除MdOutOrderMx对象记录
     * @param  momId  Integer类型(md_out_order_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer momId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
				mdOutOrderMxDao.deleteMdOutOrderMxById(momId);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}
	
	/**
	 * 根据md_out_order_mx表主键删除多条MdOutOrderMx对象记录
     * @param  MomIds  Integer类型(md_out_order_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String momIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = momIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					mdOutOrderMxDao.deleteMdOutOrderMxById(Integer
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
	 * 新增或修改md_out_order_mx表记录 ,如果md_out_order_mx表主键MdOutOrderMx.MomId为空就是添加，如果非空就是修改
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
     * @return MdOutOrderMx  修改后的MdOutOrderMx对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject( MdOutOrderMx att_MdOutOrderMx) throws HSKException{
			 SysRetrunMessage srm = new SysRetrunMessage(1l);
			  try{
					mdOutOrderMxDao.saveOrUpdateMdOutOrderMx(att_MdOutOrderMx); 
					srm.setObj(att_MdOutOrderMx);
			    } catch (Exception e) {
					e.printStackTrace(); 
					throw new HSKException(e);
		        }
			return srm;	  
	}
	
	 
	/**
	 *根据MdOutOrderMx对象作为对(md_out_order_mx表进行查询，获取分页对象
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdOutOrderMx att_MdOutOrderMx) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdOutOrderMx>());
			  try{
					pm=mdOutOrderMxDao.getPagerModelByMdOutOrderMx(att_MdOutOrderMx);
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
		return pm;
	}

	@Override
	public PagerModel getMdOutOrderMxListByMooId(Integer mooId)
			throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdOutOrderMx>());
		try{
			MdOutOrderMx att_MdOutOrderMx = new MdOutOrderMx();
			att_MdOutOrderMx.setMooId(mooId);
			List<MdOutOrderMx> list = mdOutOrderMxDao.getListByMdOutOrderMx(att_MdOutOrderMx);
			pm.setItems(list);
			pm.setRows(list);
	    } catch (Exception e) {
			e.printStackTrace(); 
        }
		return pm;
	}
	
	/**
	 * yanglei
	 * 2019-12-9
	 * 根据商品名称,规格 模糊查询
	 */
	public PagerModel getMdOutOrderMxListByName(String str)
			throws HSKException {
		String[] strs = str.split(",");
		int mooId=Integer.valueOf(strs[2]);
		String matName=strs[0];
		String norm=strs[1];
		PagerModel pm=new PagerModel(new ArrayList<MdOutOrderMx>());
		try{
			MdOutOrderMxMaterielInfoViewEntity att_MdOutOrderMx = new MdOutOrderMxMaterielInfoViewEntity();
			if (matName != null && !"".equals(matName)) {
				att_MdOutOrderMx.setMatName(matName.toUpperCase());
				att_MdOutOrderMx.setPyName(matName.toUpperCase());
			}
			if(norm != null && !"".equals(norm)) {
				att_MdOutOrderMx.setNorm(norm);
			}
			att_MdOutOrderMx.setTab_like("matName,norm,pyName");
			att_MdOutOrderMx.setMooId(mooId);
			List<MdOutOrderMxMaterielInfoViewEntity> list = mdOutOrderMxDao.getListByMdOutOrderMxMaterielInfoView(att_MdOutOrderMx);
			//List<MdOutOrderMx> list=mdOutOrderMxDao.getMxListByOrderC(att_MdOutOrderMx);
			pm.setItems(list);
			pm.setRows(list);
		 } catch (Exception e) {
				e.printStackTrace(); 
	        }
		return pm;
	}

//	@Override //暂时不删除，后续可能继续作对比优化
//	public PagerModel getOutOrderMxListByMooId(Integer mooId)
//			throws HSKException {
//		PagerModel pm=new PagerModel(new ArrayList<MdOrderMx>());
//		try{
//			MdOutOrderMx att_MdOutOrderMx = new MdOutOrderMx();
//			att_MdOutOrderMx.setMooId(mooId);
//			List<MdOutOrderMx> list = mdOutOrderMxDao.getListByMdOutOrderMx(att_MdOutOrderMx);
//			List<MdOutOrderMx> list1 = new ArrayList<MdOutOrderMx>();
//			List<MdOutOrderMx> list2 = new ArrayList<MdOutOrderMx>();
//
//			SysUserInfo account = this.GetOneSessionAccount();
//			if(list != null && list.size() > 0){
//				for(MdOutOrderMx mx: list){
//					//查询库存数并显示到页面
//					if(mx.getItemKeyId() != null && !mx.getItemKeyId().trim().equals("")){
//						MdInventoryView att_MdInventoryView= new MdInventoryView();
//						if(account.getOrganizaType().equals("20001")){
//							att_MdInventoryView.setRbaId(account.getOldId());
//						  }else if(account.getOrganizaType().equals("20002")){
//							  att_MdInventoryView.setRbsId(account.getOldId());
//						  }else if(account.getOrganizaType().equals("20003")){
//							  att_MdInventoryView.setRbbId(account.getOldId());
//						  }
//						//att_MdInventoryView.setMmfId(mx.getMmfId());
//						att_MdInventoryView.setItemKeyId(mx.getItemKeyId());
//						att_MdInventoryView = mIMdInventoryDao.findMdInventoryViewByMdInventoryView(att_MdInventoryView);
//						if(att_MdInventoryView != null && att_MdInventoryView.getWiId() != null){
//							mx.setWiId(att_MdInventoryView.getWiId());
//							mx.setQuantity(att_MdInventoryView.getBaseNumber());
//						}else
//							mx.setQuantity(0d);
//					}else if(mx.getMmfId() != null){
//						MdInventoryExtendView att_MdInventoryExtendView= new MdInventoryExtendView();
//						if(account.getOrganizaType().equals("20001")){
//							att_MdInventoryExtendView.setRbaId(account.getOldId());
//						  }else if(account.getOrganizaType().equals("20002")){
//							  att_MdInventoryExtendView.setRbsId(account.getOldId());
//						  }else if(account.getOrganizaType().equals("20003")){
//							  att_MdInventoryExtendView.setRbbId(account.getOldId());
//						  }
//						att_MdInventoryExtendView.setMmfId(mx.getMmfId());
//						att_MdInventoryExtendView = mIMdInventoryDao.findMdInventoryExtendViewByMdInventoryExtendView(att_MdInventoryExtendView);
//						if(att_MdInventoryExtendView != null && att_MdInventoryExtendView.getWiId() != null){
//							mx.setWiId(att_MdInventoryExtendView.getWiId());
//							mx.setQuantity(att_MdInventoryExtendView.getBaseNumber());
//						}else
//							mx.setQuantity(0d);
//					}
//				}
//			}
//			pm.setItems(list);
//			pm.setRows(list);
//	    } catch (Exception e) {
//			e.printStackTrace();
//        }
//		return pm;
//	}
	//20191205 yangfeng 优化申领出库-立即出库效率
	@Override
	public PagerModel getOutOrderMxListByMooId(Integer mooId, String matName, String mmfName)
			throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdOrderMx>());
		try{
			MdOutOrderMx att_MdOutOrderMx = new MdOutOrderMx();
			att_MdOutOrderMx.setMooId(mooId);
			String likeStr = "";
			if (matName != null && !matName.equals("")) {
				att_MdOutOrderMx.setMatName(matName);
				likeStr += "matName";
			}
			if (mmfName != null && !mmfName.equals("")){
				att_MdOutOrderMx.setNorm(mmfName);
				likeStr += ",norm";
			}
			if (!likeStr.equals(""))
				att_MdOutOrderMx.setTab_like(likeStr);
			List<MdOutOrderMx> list = mdOutOrderMxDao.getListByMdOutOrderMx(att_MdOutOrderMx);
			List<MdOutOrderMx> listMxWithItemKeyId = new ArrayList<MdOutOrderMx>();
			List<MdOutOrderMx> listMxWithMmfIds = new ArrayList<MdOutOrderMx>();
			String itemKeyIds = "";
			String mmfIds = "";

			SysUserInfo account = this.GetOneSessionAccount();
			if(list != null && list.size() > 0){
				for(MdOutOrderMx mx: list){
					//分批查询
					if(mx.getItemKeyId() != null && !mx.getItemKeyId().trim().equals("")){
						listMxWithItemKeyId.add(mx);
						itemKeyIds += mx.getItemKeyId() + ",";
					}else if(mx.getMmfId() != null){
						listMxWithMmfIds.add(mx);
						mmfIds += mx.getMmfId() + ",";
					}
				}
				MdInventoryView att_MdInventoryView = new MdInventoryView();
				MdInventoryExtendView att_MdInventoryExtendView = new MdInventoryExtendView();
				if (account.getOrganizaType().equals("20001")) {
					att_MdInventoryView.setRbaId(account.getOldId());
					att_MdInventoryExtendView.setRbaId(account.getOldId());
				} else if (account.getOrganizaType().equals("20002")) {
					att_MdInventoryView.setRbsId(account.getOldId());
					att_MdInventoryExtendView.setRbsId(account.getOldId());
				} else if (account.getOrganizaType().equals("20003")) {
					att_MdInventoryView.setRbbId(account.getOldId());
					att_MdInventoryExtendView.setRbbId(account.getOldId());
				}
				itemKeyIds = itemKeyIds.length() > 0 ? itemKeyIds.substring(0, itemKeyIds.length() - 1) : null;
				mmfIds = mmfIds.length() > 0 ? mmfIds.substring(0, mmfIds.length() - 1) : null;
				att_MdInventoryView.setItemKeyIdStr(itemKeyIds);
//				if (matName != null && !matName.equals(""))
//					att_MdInventoryView.setMatName(matName);
//				if (mmfName != null && !mmfName.equals(""))
//					att_MdInventoryView.setMmfName(mmfName);
				att_MdInventoryExtendView.setMmfIdsStr(mmfIds);
//				if (matName != null && !matName.equals(""))
//					att_MdInventoryExtendView.setMatName(matName);
//				if (mmfName != null && !mmfName.equals(""))
//					att_MdInventoryExtendView.setMmfName(mmfName);
				//一次性取出符合条件的数据
				List<MdInventoryView> mdInventoryViewList = mIMdInventoryDao.findMdInventoryViewListByMdInventoryView(att_MdInventoryView);
				List<MdInventoryExtendView> mdInventoryExtendViewList = null;
				if (mmfIds != null)
					mdInventoryExtendViewList = mIMdInventoryDao.findMdInventoryExtendViewListByMdInventoryExtendView(att_MdInventoryExtendView);
				//符合条件数据赋值
				Map<String, Object> otherMap = new HashMap<>();
				for(MdOutOrderMx mx: listMxWithItemKeyId) {
					if(mx.getWiId() != null){
						continue;
					}
					for (MdInventoryView mdInventoryView : mdInventoryViewList) {
						if (mdInventoryView != null && mdInventoryView.getWiId() != null && mdInventoryView.getItemKeyId().equals(mx.getItemKeyId())) {
							mx.setAvgPrice(mdInventoryView.getAvgPrice());
							mx.setPrice1(mdInventoryView.getPrice());
							mx.setRetailPrice1(mdInventoryView.getRetailPrice());
							mx.setWiId(mdInventoryView.getWiId());
							mx.setQuantity(mdInventoryView.getQuantity());
							mx.setSplitQuantity1(mdInventoryView.getBaseNumber());
							mx.setWmsMiId(mdInventoryView.getWmsMiId2());
							mx.setRatio(mdInventoryView.getRatio());
							mx.setProductName(mdInventoryView.getProductName());
							mx.setAvgRetailPrice(mdInventoryView.getAvgRetailPrice());
							otherMap = mIMdInventoryDao.getOhterInfo(mdInventoryView.getWiId());
							mx.setBackPrinting(otherMap.get("backPrinting").toString());
							mx.setBatchCode(otherMap.get("batchCode").toString());
							mx.setValiedDate(otherMap.get("valiedDate").toString());
							break;
						} else {
							mx.setAvgPrice(0d);
							mx.setRetailPrice1(0d);
							mx.setPrice1(0d);
							mx.setQuantity(0d);
							mx.setSplitQuantity1(0d);
							mx.setRatio(0D);
						}
					}
				}
				if (mdInventoryExtendViewList != null)
					for (MdOutOrderMx mx : listMxWithMmfIds) {

						if (mx.getWiId() != null) {
							continue;
						}

						for (MdInventoryExtendView mdInventoryExtendView : mdInventoryExtendViewList) {
							mdInventoryExtendView.setLogoPath(mdMaterielInfoDao.getLogoPath(mdInventoryExtendView.getWmsMiId(), null));
							if (mdInventoryExtendView != null && mdInventoryExtendView.getWiId() != null && mdInventoryExtendView.getMmfId() == mx.getMmfId()) {
								mx.setAvgPrice(mdInventoryExtendView.getAvgPrice());
								mx.setRetailPrice1(mdInventoryExtendView.getRetailPrice());
								mx.setPrice1(mdInventoryExtendView.getPrice());
								mx.setWiId(mdInventoryExtendView.getWiId());
								mx.setQuantity(mdInventoryExtendView.getQuantity());
								mx.setSplitQuantity1(mdInventoryExtendView.getBaseNumber());
								mx.setWmsMiId(mdInventoryExtendView.getWmsMiId());
								mx.setRatio(mdInventoryExtendView.getRatio());
								mx.setProductName(mdInventoryExtendView.getProductName());
								mx.setAvgRetailPrice(mdInventoryExtendView.getAvgRetailPrice());
								otherMap = mIMdInventoryDao.getOhterInfo(mdInventoryExtendView.getWiId());
								mx.setBackPrinting(otherMap.get("backPrinting").toString());
								mx.setBatchCode(otherMap.get("batchCode").toString());
								mx.setValiedDate(otherMap.get("valiedDate").toString());
								break;
							} else {
								mx.setAvgPrice(0d);
								mx.setRetailPrice1(0d);
								mx.setPrice1(0d);
								mx.setQuantity(0d);
								mx.setSplitQuantity1(0d);
								mx.setRatio(0D);
							}
						}
					}
				list.clear();
				list.addAll(listMxWithItemKeyId);
				list.addAll(listMxWithMmfIds);
			}
			Collections.sort(list, new Comparator<MdOutOrderMx>() {//根据剩余申领出库数量排序，越小的排在越前面
				@Override
				public int compare(MdOutOrderMx o1, MdOutOrderMx o2) {
					Double quan1 = o1.getQuantity();
					Double leftNumeber1 = o1.getBaseNumber() - (o1.getNumber1() != null ? o1.getNumber1() : 0); // 缺少数量
					Double q1 = quan1 - leftNumeber1; // 库存不足数量

					Double quan2 = o2.getQuantity();
					Double leftNumeber2 = o2.getBaseNumber() - (o2.getNumber1() != null ? o2.getNumber1() : 0); // 缺少数量
					Double q2 = quan2 - leftNumeber2; // 库存不足数量

					if (quan1 <= 0 && quan2 > 0) { // 前者库存为0显示前面
						return -1;
					} else if (quan2 <= 0 && quan1 > 0) { // 后者库存为0显示后面
						return 1;
					} else if (quan1 <= 0 && quan2 <= 0) { // 两者库存都为0不排序
						return 0;
					} else if (q1 < 0 && q2 < 0) {
						if (q1 <= q2) { // 库存不足大的排前面
							return -1;
						} else if (q1 > q2) { // 库存不足小的排后面
							return 1;
						}
					} else if (q1 < 0 && q2 >= 0) {
						return -1;
					} else if (q2 < 0 && q1 >= 0) {
						return 1;
					} else if (leftNumeber1 <= 0 && leftNumeber2 > 0) { // 已经出库的排到后面
						return 1;
					} else if (leftNumeber2 <= 0 && leftNumeber1 > 0) {
						return -1;
					} else if (o1.getMomId() > o2.getMomId()) { // 默认根据出库申领明细id升序排序
						return 1;
					}
					return 0;
				}
			});
			pm.setItems(list);
			pm.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public List<MdInventoryViewEx> getOutOrderMxInventory(Integer mooId)
			throws HSKException {
		List<MdInventoryViewEx> reList = new ArrayList<MdInventoryViewEx>();
		try{
			//查询明细列表
			MdOutOrderMx att_MdOutOrderMx = new MdOutOrderMx();
			att_MdOutOrderMx.setMooId(mooId);
			List<MdOutOrderMx> list = mdOutOrderMxDao.getListByMdOutOrderMx(att_MdOutOrderMx);
			SysUserInfo account = this.GetOneSessionAccount();
			if(list != null && list.size() > 0){
				String itemKeyIdStr = "";
				for(MdOutOrderMx mx: list){
					if(mx.getItemKeyId() != null && !mx.getItemKeyId().trim().equals(""))
						itemKeyIdStr += mx.getItemKeyId()+",";
				}
				//查询库存明细列表
				if(!itemKeyIdStr.equals("")){
					itemKeyIdStr = itemKeyIdStr.substring(0, itemKeyIdStr.length()-1);
					MdInventoryViewEx att_MdInventoryView= new MdInventoryViewEx();
					att_MdInventoryView.setMooId(mooId);
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
					att_MdInventoryView.setItemKeyIdStr(itemKeyIdStr);
					reList = mIMdInventoryDao.getListByMdInventoryViewEx(att_MdInventoryView);
					if(reList != null && reList.size() > 0){
						//查询收藏列表
						MdInventoryCollect mdInventoryCollect = new MdInventoryCollect();
						mdInventoryCollect.setSuiId(account.getSuiId());
						mdInventoryCollect.setState("1");
						List<MdInventoryCollect> collectList = this.getList(mdInventoryCollect);
						if(collectList != null && collectList.size() > 0){
							for(MdInventoryViewEx view : reList){
								for(MdInventoryCollect collect : collectList){
									if(view.getWiId().equals(collect.getWiId())){
										view.setMicId(collect.getMicId());
										break;
									}
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return reList;
	}


	 
}