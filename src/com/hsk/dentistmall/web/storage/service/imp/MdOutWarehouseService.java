package com.hsk.dentistmall.web.storage.service.imp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.internal.Objects;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.xsom.impl.scd.SCDImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.web.storage.service.IMdOutWarehouseService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.IorgDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;

import freemarker.template.Configuration;
import freemarker.template.Template;


/** 
  storage业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:55
 */
 
@Service
public class  MdOutWarehouseService extends DSTService implements IMdOutWarehouseService {	
   /**
   *业务处理dao类  mdOutWarehouseDao 
   */
	@Autowired
	protected IMdOutWarehouseDao mdOutWarehouseDao;
	@Autowired
	private IMdInventoryDao mdInventoryDao;
	@Autowired
	protected IorgDao orgDao;
	@Autowired
	private IMdOutWarehouseMxDao mdOutWarehouseMxDao;
	//增加包装方式字段的方法
	@Autowired
	private IMdMaterielInfoDao mdMaterielInfoDao;
	@Autowired
	private IMdInventoryExtendDao mdInventoryExtendDao;
	@Autowired
	protected IMdOutOrderDao mdOutOrderDao;
 /**
	 * 根据md_out_warehouse表主键删除MdOutWarehouse对象记录
     * @param  wowId  Integer类型(md_out_warehouse表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer wowId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);
			  	
			try{
		   			MdOutWarehouse     att_MdOutWarehouse= mdOutWarehouseDao.findMdOutWarehouseById( wowId) ;
					srm.setObj(att_MdOutWarehouse);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	}

	@Override
	public SysRetrunMessage findFormObject1(Integer wowId, Integer wiId, Integer moiId, Integer wewId) throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);

		try{
			MdOutWarehouse     att_MdOutWarehouse= new MdOutWarehouse();
			if (wowId != null)
				att_MdOutWarehouse.setWowId(wowId);
			if (wiId != null)
				att_MdOutWarehouse.setWiId(wiId);
			if (moiId != null)
				att_MdOutWarehouse.setMoiId(moiId);
			if (wewId != null)
				att_MdOutWarehouse.setWewId(wewId);
			att_MdOutWarehouse = (MdOutWarehouse) this.getOne(att_MdOutWarehouse);
			att_MdOutWarehouse.setNumber1(att_MdOutWarehouse.getBaseNumber());
			att_MdOutWarehouse.setRemarks(att_MdOutWarehouse.getWowRemarks());
			srm.setObj(att_MdOutWarehouse);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
		}
		return srm ;
	}

	/**
	 * 根据md_out_warehouse表主键删除MdOutWarehouse对象记录
     * @param  wowId  Integer类型(md_out_warehouse表主键)
	 * @throws HSKException
	 */

	public MdOutWarehouse findObject(Integer wowId) throws HSKException{
			MdOutWarehouse  att_MdOutWarehouse=new MdOutWarehouse();		
			try{
		        att_MdOutWarehouse= mdOutWarehouseDao.findMdOutWarehouseById( wowId) ;
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdOutWarehouse;
	}
	
	 
	 /**
	 * 根据md_out_warehouse表主键删除MdOutWarehouse对象记录
     * @param  wowId  Integer类型(md_out_warehouse表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wowId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
				mdOutWarehouseDao.deleteMdOutWarehouseById(wowId);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}
	
	/**
	 * 根据md_out_warehouse表主键删除多条MdOutWarehouse对象记录
     * @param  WowIds  Integer类型(md_out_warehouse表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wowIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = wowIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					mdOutWarehouseDao.deleteMdOutWarehouseById(Integer
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
	 * 新增或修改md_out_warehouse表记录 ,如果md_out_warehouse表主键MdOutWarehouse.WowId为空就是添加，如果非空就是修改
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
     * @return MdOutWarehouse  修改后的MdOutWarehouse对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject( MdOutWarehouse att_MdOutWarehouse) throws HSKException{
			 SysRetrunMessage srm = new SysRetrunMessage(1l);
			  try{
					mdOutWarehouseDao.saveOrUpdateMdOutWarehouse(att_MdOutWarehouse); 
					srm.setObj(att_MdOutWarehouse);
			    } catch (Exception e) {
					e.printStackTrace(); 
					throw new HSKException(e);
		        }
			return srm;	  
	}
	
	 
	/**
	 *根据MdOutWarehouse对象作为对(md_out_warehouse表进行查询，获取分页对象
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdOutWarehouse att_MdOutWarehouse, String orderTimeStart, String orderTimeEnd, String outTimeStart, String outTimeEnd, String flowState) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdOutwarehouseOutorderEntity>());
			  try{
				  SysUserInfo account = this.GetOneSessionAccount();
				  if(account.getOrganizaType().equals("20001")){
					  att_MdOutWarehouse.setRbaId(account.getOldId());
					  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
					  }else
						  att_MdOutWarehouse.setSuiId(account.getSuiId());
					  att_MdOutWarehouse.setPurchaseType("1");
				  }else if(account.getOrganizaType().equals("20002")){
					  att_MdOutWarehouse.setRbsId(account.getOldId());
					  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
					  }else
						  att_MdOutWarehouse.setSuiId(account.getSuiId());
					  att_MdOutWarehouse.setPurchaseType("2");
				  }else if(account.getOrganizaType().equals("20003")){
					  att_MdOutWarehouse.setRbbId(account.getOldId());
					  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
					  }else
						  att_MdOutWarehouse.setSuiId(account.getSuiId());
					  att_MdOutWarehouse.setPurchaseType("3");
				  }
				  att_MdOutWarehouse.setState("1");
				  att_MdOutWarehouse.setTab_like("wowCode,relatedBill1,customerName,supplierName");
				  att_MdOutWarehouse.setTab_order("wowId desc");

				  Map<String, Object> map = new HashMap<String, Object>();
                  if (flowState == null) {
                      map.put("flowState", "");
                  } else {
                      map.put("flowState", flowState);
                  }

				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				  if(outTimeStart != null && !outTimeStart.equals("")) {
					  att_MdOutWarehouse.setCreateDate_start(sdf.parse(outTimeStart));
				  }
				  if(outTimeEnd != null && !outTimeEnd.equals("")) {
					  att_MdOutWarehouse.setCreateDate_end(sdf.parse(outTimeEnd));
				  }
				  if(orderTimeStart != null && !orderTimeStart.equals("")) {
					  att_MdOutWarehouse.setOrderTime_start(sdf.parse(orderTimeStart));
				  }
				  if(orderTimeEnd != null && !orderTimeEnd.equals("")) {
					  att_MdOutWarehouse.setOrderTime_end(sdf.parse(orderTimeEnd));
				  }

				  pm=mdOutWarehouseDao.getPagerModelByMdOutWarehouse(att_MdOutWarehouse, map);

			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
		return pm;
	}
	public PagerModel getPagerModelObjectDistinct (MdOutWarehouse att_MdOutWarehouse, String orderTimeStart, String orderTimeEnd, String outTimeStart, String outTimeEnd, String flowState, String distinctName) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdOutwarehouseOutorderEntity>());
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			if(account.getOrganizaType().equals("20001")){
				att_MdOutWarehouse.setRbaId(account.getOldId());
				if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				}else
					att_MdOutWarehouse.setSuiId(account.getSuiId());
				att_MdOutWarehouse.setPurchaseType("1");
			}else if(account.getOrganizaType().equals("20002")){
				att_MdOutWarehouse.setRbsId(account.getOldId());
				if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				}else
					att_MdOutWarehouse.setSuiId(account.getSuiId());
				att_MdOutWarehouse.setPurchaseType("2");
			}else if(account.getOrganizaType().equals("20003")){
				att_MdOutWarehouse.setRbbId(account.getOldId());
				if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				}else
					att_MdOutWarehouse.setSuiId(account.getSuiId());
				att_MdOutWarehouse.setPurchaseType("3");
			}
			att_MdOutWarehouse.setState("1");
			att_MdOutWarehouse.setTab_like("wowCode,relatedBill1,customerName,supplierName");
			att_MdOutWarehouse.setTab_order("wowId desc");

			Map<String, Object> map = new HashMap<String, Object>();
			if (flowState == null) {
				map.put("flowState", "");
			} else {
				map.put("flowState", flowState);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(outTimeStart != null && !outTimeStart.equals("")) {
				att_MdOutWarehouse.setCreateDate_start(sdf.parse(outTimeStart));
			}
			if(outTimeEnd != null && !outTimeEnd.equals("")) {
				att_MdOutWarehouse.setCreateDate_end(sdf.parse(outTimeEnd));
			}
			if(orderTimeStart != null && !orderTimeStart.equals("")) {
				att_MdOutWarehouse.setOrderTime_start(sdf.parse(orderTimeStart));
			}
			if(orderTimeEnd != null && !orderTimeEnd.equals("")) {
				att_MdOutWarehouse.setOrderTime_end(sdf.parse(orderTimeEnd));
			}

			pm=mdOutWarehouseDao.getPagerModelObjectDistinct(att_MdOutWarehouse, map, distinctName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public SysRetrunMessage saveEditMdOutWarehouse(MdOutWarehouse att_MdOutWarehouse, String mieIds, String shus,
												   String shuSplits,String momIds,String number1s, String splitQuantitys, String wowMxIds, String wewMxIds, String remarks, String avgPrices,
												   String batchCodes, String valiedDates, String backPrintings) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			//保存入库单信息
			String[] mieIdArray = mieIds.split(",");
			String[] shuArray = shus.split(",");
			String[] shuSplitArray = shuSplits.split(",");
			String[] number1Array = number1s.split(",");
			String[] splitQuantityArray = splitQuantitys.split(",");
			String[] wowMxIdArray = wowMxIds != null ? wowMxIds.split(",") : null;
			String[] wewMxIdArray = wewMxIds != null ? wewMxIds.split(",") : null;
			String[] momIdArray=null;
			String[] remarkArray = remarks == null ? null : remarks.split("##");
			String[] priceArray = avgPrices == null ? null : avgPrices.split(",");
			String[] batchCodeArray = batchCodes == null ? null : batchCodes.split(",");
			String[] valiedDateArray = valiedDates == null ? null : valiedDates.split(",");
			String[] backPrintingArray = backPrintings == null ? null : backPrintings.split(",");

			boolean saveOrUpdate = false;
			if(momIds !=null)
				momIdArray=momIds.split(",");
			Double baseNumber = 0d;
			Double splitNumber = 0d;
			for(String shu : shuArray) {
				if (shu.equals("null"))
					continue;
				baseNumber += Double.parseDouble(shu);
			}
			for(String shu : shuSplitArray) {
				if (shu.equals("null"))
					continue;
				splitNumber += Double.parseDouble(shu);
			}
			SysUserInfo account = this.GetOneSessionAccount();
			//查看当前组织是否存在集团、医院
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				att_MdOutWarehouse.setRbaId(account.getOldId());
				att_MdOutWarehouse.setPurchaseType("1");
			}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				if(orgMap.containsKey("one")){//如果存在上级集团
					att_MdOutWarehouse.setRbaId(Integer.parseInt(orgMap.get("one")));
				}
				att_MdOutWarehouse.setRbsId(account.getOldId());
				att_MdOutWarehouse.setPurchaseType("2");
			}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
				if(orgMap.containsKey("one")){//如果存在上级集团
					att_MdOutWarehouse.setRbaId(Integer.parseInt(orgMap.get("one")));
				}
				if(orgMap.containsKey("tow")){//如果存在上级医院
					att_MdOutWarehouse.setRbsId(Integer.parseInt(orgMap.get("tow")));
				}
				att_MdOutWarehouse.setRbbId(account.getOldId());
				att_MdOutWarehouse.setPurchaseType("3");
			}
			att_MdOutWarehouse.setUserName(account.getUserName());
			if (att_MdOutWarehouse.getCompanyType().equals("2") || att_MdOutWarehouse.getCompanyType().equals("3") || att_MdOutWarehouse.getCompanyType().equals("4"))
				att_MdOutWarehouse.setGroupName(account.getOrgName());
			att_MdOutWarehouse.setSuiId(account.getSuiId());
			att_MdOutWarehouse.setFinshDate(new Date());
			att_MdOutWarehouse.setState("1");
			if (att_MdOutWarehouse.getWowId() != null) {
				saveOrUpdate = true;
				String recieveObj = att_MdOutWarehouse.getReceivingObject();
				String remarks1 = att_MdOutWarehouse.getWowRemarks();
				MdOutWarehouse md = new MdOutWarehouse();
				md.setWowId(att_MdOutWarehouse.getWowId());
				att_MdOutWarehouse = (MdOutWarehouse) this.getOne(md);
				att_MdOutWarehouse.setReceivingObject(recieveObj);
				att_MdOutWarehouse.setWowRemarks(remarks1);
				att_MdOutWarehouse.setFinshDate(new Date());
				att_MdOutWarehouse.setState("1");
				this.updateObject(att_MdOutWarehouse);
			}
			else
				this.newObject(att_MdOutWarehouse);
			//保存入库明细信息和修改库存信息以及修改订单明细信息
			Integer moiId=null;
			Integer mooId=null;
			Double count=0d;
			Double splitCount = 0d;
			Integer wowMxId;
			Double originNumber = 0d;
			Double originSplit = 0d;
			Double sq = 0d; // 最小单位扣除最大单位数量
			for(int i=0;i < mieIdArray.length;i++){
				if (shuArray[i].equals("null"))
					continue;
				Integer mieId = Integer.parseInt(mieIdArray[i]);
				Double number = Double.parseDouble(shuArray[i]);
				Double split = Double.parseDouble(shuSplitArray[i]);
				Double number1 = Double.parseDouble(number1Array[i]);
				Double splitQuantity = Double.parseDouble(splitQuantityArray[i]);

				String remark = null;
				Double avgPrice = null;
				String batchCode = null;
				Date valiedDate = null;
				String backPrinting = null;
				if (remarkArray != null && remarkArray.length > i) {
					remark = remarkArray[i];
				}
				if (priceArray != null && priceArray.length > i && !priceArray[i].equals("")) {
					avgPrice = Double.parseDouble(priceArray[i]);
				}
				if (batchCodeArray != null && batchCodeArray.length > i) {
					batchCode = batchCodeArray[i];
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (valiedDateArray != null && valiedDateArray.length > i && !valiedDateArray[i].equals("")) {
					valiedDate = sdf.parse(valiedDateArray[i]);
				}
				if (backPrintingArray != null && backPrintingArray.length > i) {
					backPrinting = backPrintingArray[i];
				}

				MdInventoryExtend att_MdInventoryExtend = new MdInventoryExtend();
				att_MdInventoryExtend.setMieId(mieId);
				att_MdInventoryExtend = (MdInventoryExtend) this.getOne(att_MdInventoryExtend);
				//查询库存视图信息
				//MdInventoryView view = mdInventoryDao.findMdInventoryViewById(att_MdInventoryExtend.getWiId());

				MdInventory att_MdInventory = mdInventoryDao.findMdInventoryById(att_MdInventoryExtend.getWiId());
				//查询库存字表信息
				//保存出库明细信息
				MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
				mdMaterielInfo.setWmsMiId(att_MdInventoryExtend.getWmsMiId());
				mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
				MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
				mdMaterielFormat.setMmfId(att_MdInventoryExtend.getMmfId());
				mdMaterielFormat = (MdMaterielFormat) this.getOne(mdMaterielFormat);
				MdOutWarehouseMx mdOutWarehouseMx = new MdOutWarehouseMx();
				if (saveOrUpdate && wowMxIdArray.length > i && !wowMxIdArray[i].equals("-1")) {
					wowMxId = Integer.parseInt(wowMxIdArray[i]);
					mdOutWarehouseMx.setWowMxId(wowMxId);
					mdOutWarehouseMx = (MdOutWarehouseMx) this.getOne(mdOutWarehouseMx);
					originNumber = mdOutWarehouseMx.getBaseNumber();
					originSplit = mdOutWarehouseMx.getSplitQuantity();
				}
				mdOutWarehouseMx.setWowId(att_MdOutWarehouse.getWowId());
				mdOutWarehouseMx.setWmsMiId(att_MdInventoryExtend.getWmsMiId());
				mdOutWarehouseMx.setMmfId(att_MdInventoryExtend.getMmfId());
				mdOutWarehouseMx.setNorm(att_MdInventoryExtend.getMmfName());
				mdOutWarehouseMx.setBaseNumber(number);
				mdOutWarehouseMx.setRetailMoney(mdMaterielFormat == null ? 0d : (mdMaterielFormat.getRetailPrice() == null ? 0d : mdMaterielFormat.getRetailPrice()));
				mdOutWarehouseMx.setSplitQuantity(split);
				mdOutWarehouseMx.setNumber1(number1);
				mdOutWarehouseMx.setSplitNumber(splitQuantity);
				mdOutWarehouseMx.setPrice(avgPrice);
//				mdOutWarehouseMx.setPrice(att_MdInventoryExtend.getBasePrice());
				mdOutWarehouseMx.setMatCode(mdMaterielInfo.getMatCode() == null ? "" : mdMaterielInfo.getMatCode());
				mdOutWarehouseMx.setMatName(att_MdInventoryExtend.getMatName());
				mdOutWarehouseMx.setBasicUnit(att_MdInventoryExtend.getBasicUnit());
				mdOutWarehouseMx.setSupplier(mdMaterielInfo.getApplicantName());
				mdOutWarehouseMx.setWiId(att_MdInventoryExtend.getWiId());
				mdOutWarehouseMx.setRemarks(remark);
				mdOutWarehouseMx.setBatchCodeData(batchCode);
				mdOutWarehouseMx.setValiedDateData(valiedDate);
				mdOutWarehouseMx.setBackPrintingData(backPrinting);
				//mdOutWarehouseMx.setInputMode(view.getMatPurchase());
				if (saveOrUpdate == true)
					this.updateObject(mdOutWarehouseMx);
				else
					this.newObject(mdOutWarehouseMx);
				//修改申领单明细
				if(att_MdOutWarehouse.getCompanyType().equals("1")){
					//修改库存明细
//					Double oSq = originSplit % ((att_MdInventory.getRatio() == 0 || att_MdInventory.getRatio() == null) ? 1 : att_MdInventory.getRatio());
//					oSq = Math.ceil(oSq);
//					sq = split % ((att_MdInventory.getRatio() == 0 || att_MdInventory.getRatio() == null) ? 1 : att_MdInventory.getRatio());
//					sq = Math.ceil(sq); // 最小单位需要扣除大单位的数量
					Double nNumber = number - originNumber; // 加上最小单位扣除的数量
					Double nSq = split - originSplit;
					Double nBase = nNumber * att_MdInventory.getRatio() + nSq;
					att_MdInventoryExtend.setBaseNumber(att_MdInventoryExtend.getBaseNumber() - nBase);
					att_MdInventoryExtend.setQuantity(Math.floor(att_MdInventoryExtend.getBaseNumber() / att_MdInventory.getRatio()));
					//修改库存
					att_MdInventory.setBaseNumber(att_MdInventory.getBaseNumber() - nBase);
					att_MdInventory.setQuantity(Math.floor(att_MdInventory.getBaseNumber() / att_MdInventory.getRatio()));

//					att_MdInventoryExtend.setBaseNumber((att_MdInventoryExtend.getBaseNumber()-(number - originNumber))>0?(att_MdInventoryExtend.getBaseNumber()-(number - originNumber)):0);
//					att_MdInventoryExtend.setQuantity(att_MdInventoryExtend.getBaseNumber()/att_MdInventory.getRatio() - split);
					//修改库存
//					att_MdInventory.setBaseNumber((att_MdInventory.getBaseNumber()-(number - originNumber))>0?(att_MdInventory.getBaseNumber()-(number - originNumber)):0);
//					att_MdInventory.setQuantity(att_MdInventory.getBaseNumber()/att_MdInventory.getRatio() - split);
					this.updateObject(att_MdInventoryExtend);
					mdInventoryDao.saveOrUpdateMdInventory(att_MdInventory);

					mdOutWarehouseMx.setCurNumber(att_MdInventory.getBaseNumber());
					this.updateObject(mdOutWarehouseMx);

					Integer momId = Integer.parseInt(momIdArray[i]);
					//查询订单明细信息
					MdOutOrderMx mdOutOrderMx = new MdOutOrderMx();
					mdOutOrderMx.setMomId(momId);
					mdOutOrderMx = (MdOutOrderMx) this.getOne(mdOutOrderMx);
					mdOutOrderMx.setNumber1((mdOutOrderMx.getNumber1()!=null?mdOutOrderMx.getNumber1():0d)+(number - originNumber));
					if(mdOutOrderMx.getNumber1() > mdOutOrderMx.getBaseNumber()){
						throw new  HSKException("操作失败!");
					}
					//最小单位出库数量
					mdOutOrderMx.setSplitNumber1((mdOutOrderMx.getSplitNumber1() != null ? mdOutOrderMx.getSplitNumber1() : 0d) + split);
					if(mdOutOrderMx.getNumber1() > (mdOutOrderMx.getSplitQuantity() == null ? 0d : mdOutOrderMx.getSplitQuantity())){
						throw new  HSKException("[" + mdOutOrderMx.getMatName() + "]库存不足!");
					}
					mooId=mdOutOrderMx.getMooId();
					this.updateObject(mdOutOrderMx);
				}else if(att_MdOutWarehouse.getCompanyType().equals("2")){
//					Double oSq = originSplit % ((att_MdInventory.getRatio() == 0 || att_MdInventory.getRatio() == null) ? 1 : att_MdInventory.getRatio());
//					oSq = Math.ceil(oSq);
//					sq = split % ((att_MdInventory.getRatio() == 0 || att_MdInventory.getRatio() == null) ? 1 : att_MdInventory.getRatio());
//					sq = Math.ceil(sq); // 最小单位需要扣除大单位的数量
//					Double nNumber = (originNumber - number) + (oSq - sq); // 加上最小单位扣除的数量

					Double nNumber = number - originNumber; // 加上最小单位扣除的数量
					Double nSq = split - originSplit;
					Double nBase = nNumber * att_MdInventory.getRatio() + nSq;
					att_MdInventoryExtend.setBaseNumber(att_MdInventoryExtend.getBaseNumber() - nBase);
					att_MdInventoryExtend.setQuantity(Math.floor(att_MdInventoryExtend.getBaseNumber() / att_MdInventory.getRatio()));
					//修改库存
					att_MdInventory.setBaseNumber(att_MdInventory.getBaseNumber() - nBase);
					att_MdInventory.setQuantity(Math.floor(att_MdInventory.getBaseNumber() / att_MdInventory.getRatio()));
					//修改库存明细
//					att_MdInventoryExtend.setQuantity((att_MdInventoryExtend.getQuantity()-(number - originNumber))>0?(att_MdInventoryExtend.getQuantity()-(number - originNumber)):0);
//					att_MdInventoryExtend.setBaseNumber(att_MdInventoryExtend.getQuantity()*att_MdInventory.getRatio() - split);
					//修改库存
//					att_MdInventory.setQuantity((att_MdInventory.getQuantity()-(number - originNumber))>0?(att_MdInventory.getQuantity()-(number - originNumber)):0);
//					att_MdInventory.setBaseNumber(att_MdInventory.getQuantity()*att_MdInventory.getRatio() - split);
					this.updateObject(att_MdInventoryExtend);
					mdInventoryDao.saveOrUpdateMdInventory(att_MdInventory);

					mdOutWarehouseMx.setCurNumber(att_MdInventory.getBaseNumber());
//					this.updateObject(mdOutWarehouseMx);

					//退货用的是原单位数量
					Integer momId = Integer.parseInt(momIdArray[i]);
					//查询订单明细信息
					MdOrderMx mdOrderMx = new MdOrderMx();
					mdOrderMx.setMomId(momId);
					mdOrderMx = (MdOrderMx) this.getOne(mdOrderMx);
					mdOrderMx.setNumber3((mdOrderMx.getNumber3()!=null?mdOrderMx.getNumber3():0d)+(number - originNumber));
					moiId=mdOrderMx.getMoiId();
					this.updateObject(mdOrderMx);
					mdOutWarehouseMx.setMoiId(moiId);
					this.updateObject(mdOutWarehouseMx);
					att_MdOutWarehouse.setMoiId(moiId);
					att_MdOutWarehouse.setWiId(att_MdInventory.getWiId());
					this.updateObject(att_MdOutWarehouse);
				} else if(att_MdOutWarehouse.getCompanyType().equals("3") || att_MdOutWarehouse.getCompanyType().equals("4")) { //4物料入库退货出库，3破损出库
					if (att_MdOutWarehouse.getCompanyType().equals("4")) {
						Integer wewMxId;
						MdEnterWarehouseMx mdEnterWarehouseMx;
						if (wewMxIdArray != null && wewMxIdArray.length > i && !wewMxIdArray[i].equals("-1")) {
							wewMxId = Integer.parseInt(wewMxIdArray[i]);
							mdEnterWarehouseMx = new MdEnterWarehouseMx();
							mdEnterWarehouseMx.setWewMxId(wewMxId);
							mdEnterWarehouseMx = (MdEnterWarehouseMx) this.getOne(mdEnterWarehouseMx);
							if (mdEnterWarehouseMx != null) {
								att_MdOutWarehouse.setWewId(mdEnterWarehouseMx.getWewId());
								mdOutWarehouseMx.setWewId(mdEnterWarehouseMx.getWewId());
							}
						}

					}

//					Double oSq = originSplit % ((att_MdInventory.getRatio() == 0 || att_MdInventory.getRatio() == null) ? 1 : att_MdInventory.getRatio());
//					oSq = Math.ceil(oSq);
//					sq = split % ((att_MdInventory.getRatio() == 0 || att_MdInventory.getRatio() == null) ? 1 : att_MdInventory.getRatio());
//					sq = Math.ceil(sq); // 最小单位需要扣除大单位的数量
//					Double nNumber = (originNumber - number) + (oSq - sq); // 加上最小单位扣除的数量

					Double nNumber = number - originNumber; // 加上最小单位扣除的数量
					Double nSq = split - originSplit;
					Double nBase = nNumber * att_MdInventory.getRatio() + nSq;
					att_MdInventoryExtend.setBaseNumber(att_MdInventoryExtend.getBaseNumber() - nBase);
					att_MdInventoryExtend.setQuantity(Math.floor(att_MdInventoryExtend.getBaseNumber() / att_MdInventory.getRatio()));
					//修改库存
					att_MdInventory.setBaseNumber(att_MdInventory.getBaseNumber() - nBase);
					att_MdInventory.setQuantity(Math.floor(att_MdInventory.getBaseNumber() / att_MdInventory.getRatio()));
//					att_MdInventoryExtend.setQuantity((att_MdInventoryExtend.getQuantity()-(number - originNumber))>0?(att_MdInventoryExtend.getQuantity()-(number - originNumber)):0);
//					att_MdInventoryExtend.setBaseNumber(att_MdInventoryExtend.getQuantity()*att_MdInventory.getRatio() - split);
					//修改库存
//					att_MdInventory.setQuantity((att_MdInventory.getQuantity()-(number - originNumber))>0?(att_MdInventory.getQuantity()-(number - originNumber)):0);
//					att_MdInventory.setBaseNumber(att_MdInventory.getQuantity()*att_MdInventory.getRatio() - split);
					this.updateObject(att_MdInventoryExtend);
					mdInventoryDao.saveOrUpdateMdInventory(att_MdInventory);

					mdOutWarehouseMx.setCurNumber(att_MdInventory.getBaseNumber());
					this.updateObject(mdOutWarehouseMx);
					att_MdOutWarehouse.setWiId(att_MdInventory.getWiId());
					this.updateObject(att_MdOutWarehouse);
				}
				count += number;
				splitCount += split;
			}
			if(att_MdOutWarehouse.getCompanyType().equals("1")){
				MdOutOrder mdOutOrder = new MdOutOrder();
				mdOutOrder.setMooId(mooId);
				mdOutOrder = (MdOutOrder) this.getOne(mdOutOrder);
				mdOutOrder.setNumber2((mdOutOrder.getNumber2()!=null?mdOutOrder.getNumber2():0)+count);
				mdOutOrder.setNumber7((mdOutOrder.getNumber7() == null ? 0 : mdOutOrder.getNumber7()) + splitCount);
				Double number6 = mdOutOrder.getNumber6() == null ? 0 : mdOutOrder.getNumber6(); // 最小单位数量
				if(mdOutOrder.getNumber2() < mdOutOrder.getNumber1() || mdOutOrder.getNumber7() < number6)
					mdOutOrder.setFlowState("3");
				else if(mdOutOrder.getNumber2() >= mdOutOrder.getNumber1() && mdOutOrder.getNumber7() >= number6)
					mdOutOrder.setFlowState("4");
				this.updateObject(mdOutOrder);

				MdOutOrderLog att_MdOutOrderLog=new MdOutOrderLog();
				att_MdOutOrderLog.setMooId(mdOutOrder.getMooId());
				att_MdOutOrderLog.setMooCode(mdOutOrder.getMooCode());
				att_MdOutOrderLog.setCreateRen(account.getUserName());
				att_MdOutOrderLog.setOperationState("申领出库");
				att_MdOutOrderLog.setCreateDate(new Date());
				String createLog = mdOutOrder.getFlowState().equals("3") ? "部分出库" : "已出库";
				att_MdOutOrderLog.setCreateLog(createLog);
				this.newObject(att_MdOutOrderLog);
			}else if(att_MdOutWarehouse.getCompanyType().equals("2")){
				MdOrderInfo mdOrderInfo = new MdOrderInfo();
				mdOrderInfo.setMoiId(moiId);
				mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);
				mdOrderInfo.setNumber4((mdOrderInfo.getNumber4()!=null?mdOrderInfo.getNumber4():0)+count);
				this.updateObject(mdOrderInfo);
			}else if(att_MdOutWarehouse.getCompanyType().equals("3")) { //破损出库

			}
			att_MdOutWarehouse.setBaseNumber(count);
			att_MdOutWarehouse.setSplitNumber(splitCount);
			this.updateObject(att_MdOutWarehouse);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
			throw new  HSKException(e);
		}
		return srm;
	}

	@Override
	public SysRetrunMessage saveMdOutWarehouse(MdOutWarehouse att_MdOutWarehouse, String mieIds, String shus, String shuSplits,String momIds,String number1s,
											   String splitQuatitys, String wiIds, String leftNumbers, String leftSplitNumbers, String remarks, String avgPrices) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] wiIdArray = wiIds.split(",");
			String[] leftNumberArray = leftNumbers.split(",");
			String[] leftSplitArray = leftSplitNumbers.split(",");
			String[] mieIdArray = mieIds.split(",");
			String[] numberTempArray = number1s.split(",");
			String[] splitNumberTempArray = splitQuatitys.split(",");
			String[] momIdTempArray = momIds.split(",");
			String[] wiIdTempArray = wiIdArray;
			String[] remarkArray = remarks == null ? null : remarks.split("##");
			String[] avgPriceArray = avgPrices == null ? null : avgPrices.split(",");
			Boolean needRefresh = false;
			boolean saveOrUpdate = false;
			if(mieIds.indexOf("null") >= 0){
				needRefresh = true;
			}
			//替换没有mieId的值
			for(int i = 0; i < mieIdArray.length; i++){
				if(!mieIdArray[i].equals("null")){
					continue;
				}
				String numberTemp = numberTempArray[i];
				String splitNumberTemp = splitNumberTempArray[i];
				String momIdTemp = momIdTempArray[i];
				String wiIdTemp = wiIdTempArray[i];
				Integer wiId = Integer.parseInt(wiIdArray[i]);
				Map<String, String> map = getMieId(wiId, leftNumberArray[i], leftSplitArray[i], numberTemp, splitNumberTemp, momIdTemp);
				mieIds = mieIds.replaceFirst("null", map.get("mieIds"));
				shus = shus.replaceFirst("null", map.get("shus"));
				shuSplits = shuSplits.replaceFirst("null", map.get("shuSplits"));

				numberTempArray[i] = map.get("number1s");
				splitNumberTempArray[i] = map.get("splitQuantitys");
				wiIdTempArray[i] = map.get("wiIds");
				momIdTempArray[i] = map.get("momIds");
			}
			if(needRefresh){  //拼接新的数值，仅仅是前端不进行选择入库单号的情况执行
				mieIdArray = mieIds.split(",");
				number1s = "";
				splitQuatitys = "";
				momIds = "";
				wiIds = "";
				for (String n : numberTempArray){
					number1s += n + ",";
				}
				for (String n : splitNumberTempArray)
					splitQuatitys += n + ",";
				for (String n : wiIdTempArray){
					wiIds += n + ",";
				}
				for (String n : momIdTempArray){
					momIds += n + ",";
				}
				number1s = number1s.substring(0, number1s.length() - 1);
				splitQuatitys = splitQuatitys.substring(0, splitQuatitys.length() - 1);
				wiIds = wiIds.substring(0, wiIds.length() - 1);
				momIds = momIds.substring(0, momIds.length() - 1);
			}

			String[] mieIdArrayTemp = mieIdArray;
			String[] shuArray = shus.split(",");
			String[] shuSplitArray = shuSplits.split(",");
			String[] number1Array = number1s.split(",");
			String[] splitNumberArray = splitQuatitys.split(",");

			Integer shuCount = 0;
			Integer splitShuCount = 0;
			for (String s : shuArray){
				if(s.equals("0")){
					shuCount ++;
				}
			}
			for (String s : shuSplitArray){
				if(s.equals("0")){
					splitShuCount ++;
				}
			}
			if(shuCount == shuArray.length && splitShuCount == shuSplitArray.length){
				srm.setMeg("全部无库存！");
				srm.setCode(2l);
				return srm;
			}

			String[] momIdArray=null;
			if(momIds !=null)
				momIdArray=momIds.split(",");
			Double baseNumber = 0d;
			Double splitNumber = 0d;
			for(String shu : shuArray)
				baseNumber += Double.parseDouble(shu);
			for (String shuSplit : shuSplitArray)
				splitNumber += Double.parseDouble(shuSplit);
			SysUserInfo account = this.GetOneSessionAccount();
			//查看当前组织是否存在集团、医院
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				att_MdOutWarehouse.setRbaId(account.getOldId());
				att_MdOutWarehouse.setPurchaseType("1");
			}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				if(orgMap.containsKey("one")){//如果存在上级集团
					att_MdOutWarehouse.setRbaId(Integer.parseInt(orgMap.get("one")));
				}
				att_MdOutWarehouse.setRbsId(account.getOldId());
				att_MdOutWarehouse.setPurchaseType("2");
			}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
				if(orgMap.containsKey("one")){//如果存在上级集团
					att_MdOutWarehouse.setRbaId(Integer.parseInt(orgMap.get("one")));
				}
				if(orgMap.containsKey("tow")){//如果存在上级医院
					att_MdOutWarehouse.setRbsId(Integer.parseInt(orgMap.get("tow")));
				}
				att_MdOutWarehouse.setRbbId(account.getOldId());
				att_MdOutWarehouse.setPurchaseType("3");
			}
			att_MdOutWarehouse.setUserName(account.getUserName());
			att_MdOutWarehouse.setSuiId(account.getSuiId());
			att_MdOutWarehouse.setFinshDate(new Date());
			att_MdOutWarehouse.setState("1");
//			if (att_MdOutWarehouse.getWowId() != null){
//				saveOrUpdate = true;
//				this.updateObject(att_MdOutWarehouse);
//			} else
			this.newObject(att_MdOutWarehouse);
			//保存入库明细信息和修改库存信息以及修改订单明细信息
			Integer moiId=null;
			Integer mooId=null;
			Double count=0d;
			Double splitCount = 0d;
			Double sq = 0d; // 最小单位需要扣除的最大单位数量
			//保存入库单信息
			for(int i=0;i < mieIdArray.length;i++){
				Double number = Double.parseDouble(shuArray[i]);
				Double split = Double.parseDouble(shuSplitArray[i]);
				if(Objects.equal(number, 0D) && Objects.equal(split, 0D)){
					continue;
				}
				Integer mieId = Integer.parseInt(mieIdArray[i]);
				Double number1 = Double.parseDouble(number1Array[i]);
				Double splitNumber1 = Double.parseDouble(splitNumberArray[i]);
				String remark = null;
				Double avgPrice = null;
				if (remarkArray != null && remarkArray.length > i) {
					remark = remarkArray[i];
				}
				if (avgPriceArray != null && avgPriceArray.length > i && !avgPriceArray[i].equals(""))
					avgPrice = Double.parseDouble(avgPriceArray[i]);

				MdInventoryExtend att_MdInventoryExtend = new MdInventoryExtend();
				att_MdInventoryExtend.setMieId(mieId);
				att_MdInventoryExtend = (MdInventoryExtend) this.getOne(att_MdInventoryExtend);
				//查询库存视图信息
				//MdInventoryView view = mdInventoryDao.findMdInventoryViewById(att_MdInventoryExtend.getWiId());
				
				MdInventory att_MdInventory = mdInventoryDao.findMdInventoryById(att_MdInventoryExtend.getWiId());
				//查询库存字表信息
				//保存出库明细信息
				MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
				mdMaterielInfo.setWmsMiId(att_MdInventoryExtend.getWmsMiId());
				mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
				MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
				mdMaterielFormat.setMmfId(att_MdInventoryExtend.getMmfId());
				mdMaterielFormat = (MdMaterielFormat) this.getOne(mdMaterielFormat);
				MdOutWarehouseMx mdOutWarehouseMx = new MdOutWarehouseMx();
				mdOutWarehouseMx.setWowId(att_MdOutWarehouse.getWowId());
				mdOutWarehouseMx.setWmsMiId(att_MdInventoryExtend.getWmsMiId());
				mdOutWarehouseMx.setMmfId(att_MdInventoryExtend.getMmfId());
				mdOutWarehouseMx.setNorm(att_MdInventoryExtend.getMmfName());
				mdOutWarehouseMx.setBaseNumber(number);
				mdOutWarehouseMx.setSplitQuantity(split);
				mdOutWarehouseMx.setNumber1(number1);
				mdOutWarehouseMx.setSplitNumber(splitNumber1);
				mdOutWarehouseMx.setPrice(avgPrice);
//				mdOutWarehouseMx.setPrice(att_MdInventoryExtend.getBasePrice());
				mdMaterielFormat.getRetailPrice();
				mdOutWarehouseMx.setRetailMoney(mdMaterielFormat == null ? 0d : (mdMaterielFormat.getRetailPrice() == null ? 0d : mdMaterielFormat.getRetailPrice()));
				mdOutWarehouseMx.setMatCode(mdMaterielInfo.getMatCode());
				mdOutWarehouseMx.setMatName(att_MdInventoryExtend.getMatName());
				mdOutWarehouseMx.setBasicUnit(att_MdInventoryExtend.getBasicUnit());
				mdOutWarehouseMx.setSupplier(mdMaterielInfo.getApplicantName());
				mdOutWarehouseMx.setWiId(att_MdInventoryExtend.getWiId());
				mdOutWarehouseMx.setRemarks(remark);
				//mdOutWarehouseMx.setInputMode(view.getMatPurchase());
				this.newObject(mdOutWarehouseMx);
				//修改申领单明细
				if(att_MdOutWarehouse.getCompanyType().equals("1")){

//					sq = split % ((att_MdInventory.getRatio() == 0 || att_MdInventory.getRatio() == null) ? 1 : att_MdInventory.getRatio());
//					sq = Math.ceil(sq); // 最小单位需要扣除大单位的数量
//					Double mNumber = sq + number; // 加上最小单位扣除的数量
					Double addSplit = split + number * ((att_MdInventory.getRatio() == 0 || att_MdInventory.getRatio() == null) ? 1 : att_MdInventory.getRatio()); //计算成最小单位
					addSplit = addSplit > 0 ? addSplit : 0;
					//修改库存明细
//					att_MdInventoryExtend.setQuantity(att_MdInventoryExtend.getQuantity() - mNumber);
//					att_MdInventoryExtend.setBaseNumber(att_MdInventoryExtend.getBaseNumber() - number * att_MdInventory.getRatio() - split);
					att_MdInventoryExtend.setBaseNumber(att_MdInventoryExtend.getBaseNumber() - (addSplit > 0 ? addSplit : 0));
					att_MdInventoryExtend.setQuantity(Math.floor(att_MdInventoryExtend.getBaseNumber() / att_MdInventory.getRatio()));
					//修改库存
//					att_MdInventory.setQuantity(att_MdInventory.getQuantity() - mNumber);
//					att_MdInventory.setBaseNumber(att_MdInventory.getBaseNumber() - number * att_MdInventory.getRatio() - split);
					att_MdInventory.setBaseNumber(att_MdInventory.getBaseNumber() - addSplit);
					att_MdInventory.setQuantity(Math.floor(att_MdInventory.getBaseNumber() / att_MdInventory.getRatio()));
					this.updateObject(att_MdInventoryExtend);
					mdInventoryDao.saveOrUpdateMdInventory(att_MdInventory);

					Integer momId = Integer.parseInt(momIdArray[i]);
					//查询订单明细信息
					MdOutOrderMx mdOutOrderMx = new MdOutOrderMx();
					mdOutOrderMx.setMomId(momId);
					mdOutOrderMx = (MdOutOrderMx) this.getOne(mdOutOrderMx);

					Double bs = mdOutOrderMx.getBaseNumber() * att_MdInventory.getRatio();
					Double ss = bs + (mdOutOrderMx.getSplitQuantity() == null ? 0d : mdOutOrderMx.getSplitQuantity());

//					if (ss < addSplit) {
//
//					}

					mdOutOrderMx.setNumber1((mdOutOrderMx.getNumber1()!=null?mdOutOrderMx.getNumber1():0d)+number);
					if(mdOutOrderMx.getNumber1() > mdOutOrderMx.getBaseNumber()){
						throw new  HSKException("[" + mdOutOrderMx.getMatName() + "]库存不足!");
					}
					//最小单位出库数量
					mdOutOrderMx.setSplitNumber1((mdOutOrderMx.getSplitNumber1() != null ? mdOutOrderMx.getSplitNumber1() : 0d) + split);
					if(mdOutOrderMx.getSplitNumber1() > (mdOutOrderMx.getSplitQuantity() == null ? 0d : mdOutOrderMx.getSplitQuantity())){
						throw new  HSKException("[" + mdOutOrderMx.getMatName() + "]库存不足!");
					}
					mooId=mdOutOrderMx.getMooId();
					this.updateObject(mdOutOrderMx);
					mdOutWarehouseMx.setCurNumber(att_MdInventory.getBaseNumber());
					this.updateObject(mdOutWarehouseMx);

				}else if(att_MdOutWarehouse.getCompanyType().equals("2")){ //退货出库

//					sq = split % ((att_MdInventory.getRatio() == 0 || att_MdInventory.getRatio() == null) ? 1 : att_MdInventory.getRatio());
//					sq = Math.ceil(sq); // 最小单位需要扣除大单位的数量
//					Double mNumber = sq + number; // 加上最小单位扣除的数量
					Double addSplit = split + number * ((att_MdInventory.getRatio() == 0 || att_MdInventory.getRatio() == null) ? 1 : att_MdInventory.getRatio()); //计算成最小单位

					//修改库存明细
//					att_MdInventoryExtend.setQuantity(att_MdInventoryExtend.getQuantity() - mNumber);
//					att_MdInventoryExtend.setBaseNumber(att_MdInventoryExtend.getBaseNumber() - number * att_MdInventory.getRatio() - split);
					att_MdInventoryExtend.setBaseNumber(addSplit > 0 ? addSplit : 0);
					att_MdInventoryExtend.setQuantity(Math.floor(att_MdInventoryExtend.getBaseNumber() / att_MdInventory.getRatio()));
					//修改库存
//					att_MdInventory.setQuantity(att_MdInventory.getQuantity() - mNumber);
//					att_MdInventory.setBaseNumber(att_MdInventory.getBaseNumber() - number * att_MdInventory.getRatio() - split);
					att_MdInventory.setBaseNumber(att_MdInventory.getBaseNumber() - (addSplit > 0 ? addSplit : 0));
					att_MdInventory.setQuantity(Math.floor(att_MdInventory.getBaseNumber() / att_MdInventory.getRatio()));
					this.updateObject(att_MdInventoryExtend);
					mdInventoryDao.saveOrUpdateMdInventory(att_MdInventory);

					//退货用的是原单位数量
					Integer momId = Integer.parseInt(momIdArray[i]);
					//查询订单明细信息
					MdOrderMx mdOrderMx = new MdOrderMx();
					mdOrderMx.setMomId(momId);
					mdOrderMx = (MdOrderMx) this.getOne(mdOrderMx);
					mdOrderMx.setNumber3((mdOrderMx.getNumber3()!=null?mdOrderMx.getNumber3():0d)+number);
					moiId=mdOrderMx.getMoiId();
					this.updateObject(mdOrderMx);
					mdOutWarehouseMx.setMoiId(moiId);
					mdOutWarehouseMx.setCurNumber(att_MdInventory.getBaseNumber());
					this.updateObject(mdOutWarehouseMx);
					att_MdOutWarehouse.setMoiId(moiId);
					this.updateObject(att_MdOutWarehouse);
				}else if(att_MdOutWarehouse.getCompanyType().equals("3")) { //破损出库

//					sq = split % ((att_MdInventory.getRatio() == 0 || att_MdInventory.getRatio() == null) ? 1 : att_MdInventory.getRatio());
//					sq = Math.ceil(sq); // 最小单位需要扣除大单位的数量
//					Double mNumber = sq + number; // 加上最小单位扣除的数量
					Double addSplit = split + number * ((att_MdInventory.getRatio() == 0 || att_MdInventory.getRatio() == null) ? 1 : att_MdInventory.getRatio()); //计算成最小单位

					//修改库存明细
//					att_MdInventoryExtend.setQuantity(att_MdInventoryExtend.getQuantity() - mNumber);
//					att_MdInventoryExtend.setBaseNumber(att_MdInventoryExtend.getBaseNumber() - number * att_MdInventory.getRatio() - split);
					att_MdInventoryExtend.setBaseNumber(addSplit > 0 ? addSplit : 0);
					att_MdInventoryExtend.setQuantity(Math.floor(att_MdInventoryExtend.getBaseNumber() / att_MdInventory.getRatio()));
					//修改库存
//					att_MdInventory.setQuantity(att_MdInventory.getQuantity() - mNumber);
//					att_MdInventory.setBaseNumber(att_MdInventory.getBaseNumber() - number * att_MdInventory.getRatio() - split);
					att_MdInventory.setBaseNumber(att_MdInventory.getBaseNumber() - (addSplit > 0 ? addSplit : 0));
					att_MdInventory.setQuantity(Math.floor(att_MdInventory.getBaseNumber() / att_MdInventory.getRatio()));
					this.updateObject(att_MdInventoryExtend);
					mdInventoryDao.saveOrUpdateMdInventory(att_MdInventory);

					mdOutWarehouseMx.setCurNumber(att_MdInventory.getBaseNumber());
					this.updateObject(mdOutWarehouseMx);
					att_MdOutWarehouse.setWiId(att_MdInventory.getWiId());
					this.updateObject(att_MdOutWarehouse);
				}
				count += number;
				splitCount += split;
			}
			if(att_MdOutWarehouse.getCompanyType().equals("1")){
				MdOutOrder mdOutOrder = new MdOutOrder();
				mdOutOrder.setMooId(mooId);
				mdOutOrder = (MdOutOrder) this.getOne(mdOutOrder);
				mdOutOrder.setNumber2((mdOutOrder.getNumber2()!=null?mdOutOrder.getNumber2():0)+count);
				mdOutOrder.setNumber7((mdOutOrder.getNumber7() == null ? 0 : mdOutOrder.getNumber7()) + splitCount);
				Double number6 = mdOutOrder.getNumber6() == null ? 0 : mdOutOrder.getNumber6(); // 最小单位数量
				Double number7 = mdOutOrder.getNumber7() == null ? 0 : mdOutOrder.getNumber7();
				if(mdOutOrder.getNumber2() < mdOutOrder.getNumber1() || number7 < number6)
					mdOutOrder.setFlowState("3");
				else if(mdOutOrder.getNumber2() >= mdOutOrder.getNumber1() && number7 >= number6)
					mdOutOrder.setFlowState("4");
				this.updateObject(mdOutOrder);

				MdOutOrderLog att_MdOutOrderLog=new MdOutOrderLog();
				att_MdOutOrderLog.setMooId(mdOutOrder.getMooId());
				att_MdOutOrderLog.setMooCode(mdOutOrder.getMooCode());
				att_MdOutOrderLog.setCreateRen(account.getUserName());
				att_MdOutOrderLog.setOperationState("申领出库");
				att_MdOutOrderLog.setCreateDate(new Date());
				String createLog = mdOutOrder.getFlowState().equals("3") ? "部分出库" : "已出库";
				att_MdOutOrderLog.setCreateLog(createLog);
				this.newObject(att_MdOutOrderLog);
			}else if(att_MdOutWarehouse.getCompanyType().equals("2")){ // 退货出库
				MdOrderInfo mdOrderInfo = new MdOrderInfo();
				mdOrderInfo.setMoiId(moiId);
				mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);
				mdOrderInfo.setNumber4((mdOrderInfo.getNumber4()!=null?mdOrderInfo.getNumber4():0)+count);
//				mdOrderInfo.setNumber5((mdOrderInfo.getNumber5()!=null?mdOrderInfo.getNumber5():0) + splitCount);
				this.updateObject(mdOrderInfo);
			}
			att_MdOutWarehouse.setBaseNumber(count);
			att_MdOutWarehouse.setSplitNumber(splitCount);
			this.updateObject(att_MdOutWarehouse);

		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
			throw new  HSKException("库存不足");
		}
		return srm;
	}

	/**
	 * 若是前端没有进行选择，则执行以下方法，填充字符串null，将需要的值重新拼接
	 * @param wiId
	 * @param leftNumberStr
	 * @Param number1 申领数量
	 * @Param momId
	 * @return
	 */
	private Map<String, String> getMieId(Integer wiId, String leftNumberStr, String leftSplitNumberStr, String number1, String splitQauntity, String momId){
		Map<String, String> map = new HashMap<String, String>();
		try {
			List<MdInventoryEnterwarehouserViewEntity> list=mdInventoryExtendDao.getExtendEnterWarehouseViewPagerModel(wiId, null, null, null);
			Double baseNumber = 0d;
			Double splitNumber = 0d;
			Double shu = 0d;
			Double shuSplit = 0d;
			Integer mieId = null;
			String shus = "";
			String shuSplits = "";
			String mieIds = "";
			String number1s = "";
			String splitQuantitys = "";
			String wiIds = "";
			String momIds = "";
			Double leftNumber = Double.parseDouble(leftNumberStr);
			Double leftSplitNumber = Double.parseDouble(leftSplitNumberStr);

			Double ln = 0D;
			Double lsn = 0D;
			Double bn = 0D;
			Double sn = 0D;
			Double ratio = 1D;
			for (MdInventoryEnterwarehouserViewEntity mdInventoryExtend : list){
				baseNumber = mdInventoryExtend.getQuantity();
				splitNumber = mdInventoryExtend.getBaseNumber() == null ? 0 : mdInventoryExtend.getBaseNumber();

				ratio = mdInventoryExtend.getRatio();
				bn = baseNumber * ratio;

				ln = leftNumber * ratio;
				lsn = ln + leftSplitNumber;

				bn = baseNumber * ratio;
				sn = splitNumber - bn + bn;

				if(baseNumber <= 0 && splitNumber <= 0){
					shus += "0,";
					shuSplits += "0,";
					mieIds += "0,";
					number1s += "0,";
					splitQuantitys += "0,";
					wiIds += "0,";
					momIds += "0,";
					continue;
				}

				if (lsn <= sn) {
					shu = leftNumber;
					shuSplit = leftSplitNumber;
					leftNumber = 0D;
					leftSplitNumber = 0D;
				} else if (lsn > sn) {
					if (leftNumber * ratio <= sn) {
						shu = leftNumber;
						leftNumber = 0D;
					} else {
						shu = baseNumber;
						leftNumber -= baseNumber;
					}
					lsn -= shu * ratio;
					sn -= shu * ratio;
					if (sn <= 0) {
						shuSplit = 0d;
						leftSplitNumber -= 0D;
					} else if (lsn <= 0) {
						shuSplit = 0D;
						leftSplitNumber -= 0D;
					} else if (lsn <= sn) {
						shuSplit = splitNumber;
						leftSplitNumber = 0D;
					} else {
						shuSplit = splitNumber;
						leftNumber -= splitNumber;
					}
				}

//				if(baseNumber <= 0 && splitNumber <= 0){
//					shus += "0,";
//					shuSplits += "0,";
//					mieIds += "0,";
//					number1s += "0,";
//					splitQuantitys += "0,";
//					wiIds += "0,";
//					momIds += "0,";
//					continue;
//				}
//				if(leftNumber > baseNumber){
//					shu = baseNumber;
//				} else {
//					shu = leftNumber;
//				}
//				if (leftSplitNumber > splitNumber)
//					shuSplit = splitNumber;
//				else
//					shuSplit = leftSplitNumber;
				shus += shu + ",";
				shuSplits += shuSplit + ",";
				mieId = mdInventoryExtend.getMieId();
				mieIds += mieId + ",";
				number1s += number1 + ",";
				splitQuantitys += splitQauntity + ",";
				wiIds += wiId + ",";
				momIds += momId + ",";
//				leftNumber -= baseNumber;
				if(leftNumber <= 0 && leftSplitNumber <= 0){
					break;
				}
//				if(leftNumber <= 0){
//					break;
//				}
			}
			if(list == null || list.size() == 0){
				shus += "0,";
				shuSplits += "0,";
				mieIds += "0,";
				number1s += "0,";
				splitQuantitys += "0,";
				wiIds += "0,";
				momIds += "0,";
			}
			if (shus.length() > 0){
				shus = shus.substring(0, shus.length() - 1);
			}
			if (shuSplits.length() > 0)
				shuSplits = shuSplits.substring(0, shuSplits.length() - 1);
			if(mieIds.length() > 0){
				mieIds = mieIds.substring(0, mieIds.length() - 1);
			}
			if(number1s.length() > 0){
				number1s = number1s.substring(0, number1s.length() - 1);
			}
			if (splitQuantitys.length() > 0)
				splitQuantitys = splitQuantitys.substring(0, splitQuantitys.length() - 1);
			if(wiIds.length() > 0){
				wiIds = wiIds.substring(0, wiIds.length() - 1);
			}
			if(momIds.length() > 0){
				momIds = momIds.substring(0, momIds.length() - 1);
			}

			map.put("shus", shus);
			map.put("shuSplits", shuSplits);
			map.put("mieIds", mieIds);
			map.put("number1s", number1s);
			map.put("splitQuantitys", splitQuantitys);
			map.put("wiIds", wiIds);
			map.put("momIds", momIds);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 新增
	 */
	@Override
	public SysRetrunMessage exportInfoApply(Integer wowId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			MdOutWarehouse att_MdOutWarehouse= mdOutWarehouseDao.findMdOutWarehouseById(wowId) ;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			DecimalFormat df= new DecimalFormat("######0.00"); 
			DecimalFormat df2= new DecimalFormat("######0"); 
			MdOutWarehouseMx mx = new MdOutWarehouseMx();
			mx.setWowId(att_MdOutWarehouse.getWowId());
			List<MdOutWarehouseMx> mxList = this.getList(mx);
			Map<String, Object> dataMap = new HashMap<String,Object>();
			List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
			int rowCount=0;
			if (att_MdOutWarehouse.getWowCode()!=null) {
				dataMap.put("wowCode", att_MdOutWarehouse.getWowCode());
			}else{
				dataMap.put("wowCode", "");
			}
			if(att_MdOutWarehouse.getFinshDate()!=null){
				dataMap.put("finshDate", sdf.format(att_MdOutWarehouse.getFinshDate()));
			}else{
				dataMap.put("finshDate","");
			}
			dataMap.put("orgName", account.getOrgName());
			dataMap.put("customerName", att_MdOutWarehouse.getCustomerName()!=null?att_MdOutWarehouse.getCustomerName():"");
			dataMap.put("customer", att_MdOutWarehouse.getCustomer()!=null?att_MdOutWarehouse.getCustomer():"");
			dataMap.put("consignee", att_MdOutWarehouse.getConsignee());
			dataMap.put("reamark", att_MdOutWarehouse.getDescription()!=null?att_MdOutWarehouse.getDescription():" ");
			dataMap.put("userName", att_MdOutWarehouse.getUserName());
			dataMap.put("supplierName", att_MdOutWarehouse.getSupplierName());
			if(att_MdOutWarehouse.getOrderTime()!=null && !att_MdOutWarehouse.getOrderTime().toString().trim().equals(""))
				dataMap.put("orderTime", att_MdOutWarehouse.getOrderTime().toString().substring(0, 10));
			  else
				  dataMap.put("orderTime", ""); 
			dataMap.put("relatedBill1", att_MdOutWarehouse.getRelatedBill1());
			//部门
			dataMap.put("groupName", att_MdOutWarehouse.getCustomer()!=null?att_MdOutWarehouse.getCustomer():" ");
			//申领状态重新定义
			int in=Integer.parseInt(att_MdOutWarehouse.getFlowState());
//			System.out.println();
			if (in==1) {
				dataMap.put("flowState", "所有");
			}
			if (in==2) {
				dataMap.put("flowState", "申请中");
			}
			if (in==3) {
				dataMap.put("flowState", "部分发货");
			}
			if (in==7) {
				dataMap.put("flowState", "售后");
			}
			if (in==4) {
				dataMap.put("flowState", "已完成");
			}
			if (in==5) {
				dataMap.put("flowState", "撤销");
			}
			//申领人
			dataMap.put("userName", att_MdOutWarehouse.getCustomerName()!=null?att_MdOutWarehouse.getCustomerName():" ");
			Double allShu=0d;
			Double allMoney =0d;
			String tempPath="";
		
			if(att_MdOutWarehouse.getCompanyType().equals("1")){//申购导出
				rowCount=8;
				tempPath="exportApplyOutInfoApply.ftl";
				Double numberCount1=0d;
				Double numberCount2=0d;
				Double numberLack1=0d;
				Double numberLack=0d;
				int index=0;
				for(MdOutWarehouseMx obj:mxList){
					index++;
					Map<String,String> reMap = new HashMap<String,String>();
					reMap.put("index", index+"");
					//单位
					reMap.put("basicUnit", obj.getBasicUnit());
					//包装方式
					MdMaterielInfo att_mdMaterielInfo=mdMaterielInfoDao.findMdMaterielInfoById(obj.getWmsMiId());
					if (att_mdMaterielInfo.getProductName()!=null) {
						reMap.put("productName",att_mdMaterielInfo.getProductName());
					}else{
						reMap.put("productName","");
					}
					/*reMap.put("productName",
							 att_mdMaterielInfo.getProductName() != null ?  att_mdMaterielInfo.getProductName(): " ");*/
					//增加出货量 缺少数量
					reMap.put("number1", df2.format(obj.getNumber1()));
					//缺少数量
					if (obj.getNumber1()!=null) {
						numberLack=obj.getNumber1()-obj.getBaseNumber();
					}if (obj.getNumber1()==null) {
						numberLack=obj.getBaseNumber()-0.0;
					}
					reMap.put("numberLack",df2.format(numberLack).toString()!=null?df2.format(numberLack).toString():"");
					
					reMap.put("matCode", obj.getMatCode());
					reMap.put("matName", obj.getMatName());
					reMap.put("mmfName", obj.getNorm());
					Double price=0d;
					if(obj.getNowPrice() != null){
						reMap.put("price", df.format(obj.getNowPrice()));
						price = obj.getNowPrice();
					}else
						reMap.put("price", "0.00");
					
					reMap.put("baseNumber", df2.format(obj.getBaseNumber()));
					
					Double countMoney= obj.getBaseNumber()*price;
					reMap.put("countMoney", df.format(countMoney)+"");
					reList.add(reMap);
					//出库数量
					//申请数量 总和
					if (obj.getBaseNumber()!=null) {
						numberCount1+=obj.getBaseNumber();
					}
					if (obj.getNumber1()!=null) {
						numberCount2+=obj.getNumber1();
					}
					numberLack1+=numberLack;
					dataMap.put("numberCount1", numberCount1);
					dataMap.put("numberCount2", numberCount2);
					dataMap.put("numberLack1", numberLack1);
					//制作部门
					//account.setSuiId();
					//制作部门
					account.setSuiId(att_MdOutWarehouse.getSuiId());
					dataMap.put("OrgGxIdStr", account.getOrgGxId_Str()!= null?account.getOrgGxId_Str():"");
					allShu += obj.getBaseNumber();
					allMoney += countMoney;
				}
			}else{
				rowCount=9;
				tempPath="exportApplyOutInfoApplyC.ftl";
				int index=0;
				Double numberCount1=0d;
				Double numberCount2=0d;
				Double numberLack1=0d;
				Double numberLack=0d;
				for(MdOutWarehouseMx obj:mxList){
					index++;
					Map<String,String> reMap = new HashMap<String,String>();
					reMap.put("index", index+"");
					//单位
					reMap.put("basicUnit", obj.getBasicUnit());
					//包装方式
					MdMaterielInfo att_mdMaterielInfo=mdMaterielInfoDao.findMdMaterielInfoById(obj.getWmsMiId());
					if (att_mdMaterielInfo.getProductName()!=null) {
						reMap.put("productName",att_mdMaterielInfo.getProductName());
					}else{
						reMap.put("productName","");
					}
					reMap.put("matCode", obj.getMatCode());
					reMap.put("matName", obj.getMatName());
					reMap.put("mmfName", obj.getNorm());
					Double price=0d;
					if(obj.getPrice() != null){
						reMap.put("price", df.format(obj.getPrice()));
						price = obj.getNowPrice();
					}else
						reMap.put("price", "0.00");
					
					reMap.put("baseNumber", df2.format(obj.getBaseNumber()));
					
					//增加出货量 缺少数量
					reMap.put("number1", df2.format(obj.getNumber1()));
					//缺少数量
					if (obj.getNumber1()!=null) {
						numberLack=obj.getBaseNumber()-obj.getNumber1();
					}if (obj.getNumber1()==null) {
						numberLack=obj.getBaseNumber()-0.0;
					}
					reMap.put("numberLack",df2.format(numberLack).toString()!=null?df2.format(numberLack).toString():"");
					
					Double countMoney= obj.getBaseNumber()*price;
					reMap.put("countMoney", df2.format(countMoney)+"");
					reList.add(reMap);
					numberCount1+=obj.getBaseNumber();
					//出库数量
					if (obj.getNumber1()!=null) {
						numberCount2+=obj.getNumber1();
					}
					numberLack1+=numberLack;
					
					allShu += obj.getBaseNumber();
					allMoney += countMoney;
				}
			}
			rowCount += reList.size();
			dataMap.put("allShu", df2.format(allShu));
			dataMap.put("allMoney", df.format(allMoney)+"");
			dataMap.put("rowCount", rowCount);
			
			//制作部门
			//account.setSuiId();
			//制作部门
			account.setSuiId(att_MdOutWarehouse.getSuiId());
			dataMap.put("OrgGxIdStr", account.getOrgGxId_Str()!= null?account.getOrgGxId_Str():"");
			dataMap.put("mxList", reList);
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
			// 这里我们的模板是放在org.cnzjw.template包下面
			configuration.setClassForTemplateLoading(this.getClass(),
					"/ftl");
			Template t = configuration.getTemplate(tempPath);
			// 输出文档路径及名称
			Calendar now = Calendar.getInstance();
			long lo = now.getTimeInMillis();
			Date date=new Date(lo);
			SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmss");
			String l=sd.format(date);
			String tmpPath = SystemContext.updown_File_path+TEMP_EXPORT_PATH;//获得工程运行web的目录
			File file = new File(tmpPath);
			if(!file.exists()) {
				file.mkdirs();
			}
	        String realPath = tmpPath +"CGSLCKDG"+l+ ".xls";
			File outFile = new File(realPath);
			Writer out = null;
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile),"UTF-8"));
			t.process(dataMap, out);
			out.close();
			String rootUrl = request.getContextPath() + EXPORT_PATH+"CGSLCKDG"+l+ ".xls";
			Map<String,String> map = new HashMap<String,String>();
			map.put("path", rootUrl);
			map.put("fileName","CGSLCKDG"+l+ ".xls");
			srm.setObj(map);
			
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}
	
	
	@Override
	public SysRetrunMessage exportInfo(Integer wowId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			MdOutWarehouse att_MdOutWarehouse= mdOutWarehouseDao.findMdOutWarehouseById(wowId) ;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			DecimalFormat df= new DecimalFormat("######0.00"); 
			DecimalFormat df2= new DecimalFormat("######0"); 
			MdOutWarehouseMx mx = new MdOutWarehouseMx();
			mx.setWowId(att_MdOutWarehouse.getWowId());
			List<MdOutWarehouseMx> mxList = this.getList(mx);
			Map<String, Object> dataMap = new HashMap<String,Object>();
			List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
			int rowCount=0;
			dataMap.put("wowCode", att_MdOutWarehouse.getWowCode());
			if(att_MdOutWarehouse!=null){
				dataMap.put("finshDate", sdf.format(att_MdOutWarehouse.getFinshDate()));
			}else{
				dataMap.put("finshDate","");
			}
			dataMap.put("orgName", account.getOrgName());
			dataMap.put("customerName", att_MdOutWarehouse.getCustomerName()!=null?att_MdOutWarehouse.getCustomerName():"");
			dataMap.put("relatedBill1", att_MdOutWarehouse.getRelatedBill1());
			dataMap.put("customer", att_MdOutWarehouse.getCustomer()!=null?att_MdOutWarehouse.getCustomer():"");
			dataMap.put("consignee", att_MdOutWarehouse.getConsignee());
			dataMap.put("reamark", att_MdOutWarehouse.getDescription()!=null?att_MdOutWarehouse.getDescription():" ");
			dataMap.put("userName", att_MdOutWarehouse.getUserName());
			dataMap.put("supplierName", att_MdOutWarehouse.getSupplierName());
			Double allShu=0d;
			Double allMoney =0d;
			String tempPath="";
			if(att_MdOutWarehouse.getCompanyType().equals("1")){//申购导出
				rowCount=8;
				tempPath="exportApplyOutInfo.ftl";
				for(MdOutWarehouseMx obj:mxList){
					Map<String,String> reMap = new HashMap<String,String>();
					reMap.put("matCode", obj.getMatCode());
					reMap.put("matName", obj.getMatName());
					reMap.put("mmfName", obj.getNorm());
					Double price=0d;
					if(obj.getNowPrice() != null){
						reMap.put("price", df.format(obj.getNowPrice()));
						price = obj.getNowPrice();
					}else
						reMap.put("price", "0.00");
					
					reMap.put("baseNumber", df2.format(obj.getBaseNumber()));
					Double countMoney= obj.getBaseNumber()*price;
					reMap.put("countMoney", df.format(countMoney)+"");
					reList.add(reMap);
					allShu += obj.getBaseNumber();
					allMoney += countMoney;
				}
			}else{
				rowCount=9;
				tempPath="exportOutInfo.ftl";
				for(MdOutWarehouseMx obj:mxList){
					Map<String,String> reMap = new HashMap<String,String>();
					reMap.put("matCode", obj.getMatCode());
					reMap.put("matName", obj.getMatName());
					reMap.put("mmfName", obj.getNorm());
					Double price=0d;
					if(obj.getPrice() != null){
						reMap.put("price", df.format(obj.getPrice()));
						price = obj.getNowPrice();
					}else
						reMap.put("price", "0.00");
					
					reMap.put("baseNumber", df2.format(obj.getBaseNumber()));
					Double countMoney= obj.getBaseNumber()*price;
					reMap.put("countMoney", df2.format(countMoney)+"");
					reList.add(reMap);
					allShu += obj.getBaseNumber();
					allMoney += countMoney;
				}
			}
			rowCount += reList.size();
			dataMap.put("allShu", df2.format(allShu));
			dataMap.put("allMoney", df.format(allMoney)+"");
			dataMap.put("rowCount", rowCount);
			dataMap.put("mxList", reList);
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
			// 这里我们的模板是放在org.cnzjw.template包下面
			configuration.setClassForTemplateLoading(this.getClass(),
					"/ftl");
			Template t = configuration.getTemplate(tempPath);
			// 输出文档路径及名称
			Calendar now = Calendar.getInstance();
			long l = now.getTimeInMillis();
			String tmpPath = SystemContext.updown_File_path+TEMP_EXPORT_PATH;//获得工程运行web的目录
			File file = new File(tmpPath);
			if(!file.exists()) {
				file.mkdirs();
			}
	        String realPath = tmpPath +l+ ".xls";
			File outFile = new File(realPath);
			Writer out = null;
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile),"UTF-8"));
			t.process(dataMap, out);
			out.close();
			String rootUrl = request.getContextPath() + EXPORT_PATH+l+ ".xls";
			Map<String,String> map = new HashMap<String,String>();
			map.put("path", rootUrl);
			map.put("fileName", l+ ".xls");
			srm.setObj(map);
			
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}
	/**
	 * 新增申领已出库批量导出
	 * 2019-12-04
	 * yanglei
	 */
	@Override
	public SysRetrunMessage exportApplyListPi(MdOutWarehouse att_MdOutWarehouse,MdOutWarehouseMx att_MdOutWarehouseMx,String wowIds) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		String[] wowIdsArray = wowIds.split(",");
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			  if(account.getOrganizaType().equals("20001")){
				  att_MdOutWarehouse.setRbaId(account.getOldId());
				  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				  }else
					  att_MdOutWarehouse.setSuiId(account.getSuiId());
				  att_MdOutWarehouse.setPurchaseType("1");
			  }else if(account.getOrganizaType().equals("20002")){
				  att_MdOutWarehouse.setRbsId(account.getOldId());
				  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				  }else
					  att_MdOutWarehouse.setSuiId(account.getSuiId());
				  att_MdOutWarehouse.setPurchaseType("2");
			  }else if(account.getOrganizaType().equals("20003")){
				  att_MdOutWarehouse.setRbbId(account.getOldId());
				  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				  }else
					  att_MdOutWarehouse.setSuiId(account.getSuiId());
				  att_MdOutWarehouse.setPurchaseType("3");
			  }
			  att_MdOutWarehouse.setCompanyType("1");
				/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
				DecimalFormat df= new DecimalFormat("######0.00"); 
				DecimalFormat df2= new DecimalFormat("######0"); */
			  Map<String, Object> dataMap = null;
			  int rowCount = 4;
			  Double numberCount1=0d;
				Double numberCount2=0d;
				Double numberLack1=0d;
				int index=0;
				//reList放在外面
				List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
				for (int i = 0; i < wowIdsArray.length; i++) {
				//List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
				Integer wowId = Integer.parseInt(wowIdsArray[i]);
				MdOutWarehouse attMdOutWarehouse=mdOutWarehouseDao.findMdOutWarehouseById(wowId);
				MdOutWarehouseMx mx = new MdOutWarehouseMx();
				mx.setWowId(wowId);
				List<MdOutWarehouseMx> mxList = (List<MdOutWarehouseMx>) this.getList(mx);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				DecimalFormat df = new DecimalFormat("######0.00");
				DecimalFormat df2 = new DecimalFormat("######0");
				dataMap = new HashMap<String, Object>();
				String timeArea="";
				if(attMdOutWarehouse.getOrderTime_start()!=null){
					timeArea+=sdf.format(attMdOutWarehouse.getOrderTime_start());
				}if (attMdOutWarehouse.getOrderTime_start()!=null||attMdOutWarehouse.getOrderTime_end()!=null) {
					timeArea+="-";
				}if (attMdOutWarehouse.getOrderTime_end()!=null) {
					timeArea+=sdf.format(attMdOutWarehouse.getOrderTime_end());
				}				
				dataMap.put("timeArea", timeArea);
				//制作日期
				if(attMdOutWarehouse.getCreateDate()!=null){
					dataMap.put("createDate", sdf.format(attMdOutWarehouse.getCreateDate()));
				}else{
					dataMap.put("createDate", "");
				}
				int in = Integer.parseInt(attMdOutWarehouse.getFlowState()); 
				if (in==2) {
					dataMap.put("flowState", "申请中");
				}
				if (in==3) {
					dataMap.put("flowState", "部分发货");
				}
				if (in==4) {
					dataMap.put("flowState", "已完成");
				}
				if (in==5) {
					dataMap.put("flowState", "撤销");
				}
				//SysUserInfo account = this.GetOneSessionAccount();
				account.setSuiId(attMdOutWarehouse.getSuiId());
				//制作人
				dataMap.put("createRen",account.getUserName()!= null?account.getUserName():"");
				//制作部门
				dataMap.put("OrgGxIdStr", account.getOrgGxId_Str()!= null?account.getOrgGxId_Str():"");
				//dataMap.put("index", index);*/
				Double numberLack=0d;
				for (MdOutWarehouseMx obj : mxList) {
					index++;
				Map<String, String> reMap = new HashMap<String, String>();	
				reMap.put("index", index+"");
				reMap.put("matCode",attMdOutWarehouse.getRelatedBill1());
				reMap.put("matName", obj.getMatName());
				reMap.put("mmfName", obj.getNorm());
				reMap.put("basicUnit",
						obj.getBasicUnit() != null ? obj.getBasicUnit(): " ");
				reMap.put("userName", attMdOutWarehouse.getCustomerName()!= null?attMdOutWarehouse.getCustomerName():"");
				
				reMap.put("groupName",
						attMdOutWarehouse.getCustomer() != null ? attMdOutWarehouse
								.getCustomer() : " ");
				//包装方式
				reMap.put("productName",
						 obj.getProductName() != null ?  obj.getProductName(): " ");
				//申领数量
				reMap.put("baseNumber",df2.format(obj.getBaseNumber()!=null?obj.getBaseNumber():0d));
				reMap.put("number1", df2.format(obj.getNumber1()!=null?obj.getNumber1():0d));
				
				//出库日期
				if(attMdOutWarehouse.getFinshDate()!=null){
					reMap.put("finshDate", sdf.format(attMdOutWarehouse.getFinshDate()));
				}else{
					reMap.put("finshDate", "");
				}
				//reMap.put("number2", df2.format(obj.getNumber2()));
				//缺少数量
				//obj.getBaseNumber()-obj.getNumber1()
				
				//numberLack=obj.getBaseNumber()-obj.getNumber1();
				if (obj.getNumber1()!=null) {
					numberLack=obj.getNumber1()-obj.getBaseNumber();
				}if (obj.getNumber1()==null) {
					numberLack=obj.getBaseNumber()-0.0;
				}
					reMap.put("numberLack",df2.format(numberLack).toString()!=null?df2.format(numberLack).toString():"");
				if (attMdOutWarehouse.getOrderTime() != null) {
					reMap.put("orderTime",
							sdf.format(attMdOutWarehouse.getOrderTime()));
				} else {
					reMap.put("orderTime", "");
				}
				reMap.put(
						"userName",
						attMdOutWarehouse.getCustomerName() != null ? attMdOutWarehouse
								.getCustomerName() : " ");
				reList.add(reMap);
				numberCount1+=obj.getBaseNumber();
					//出库数量
					if (obj.getNumber1()!=null) {
						numberCount2+=obj.getNumber1();
					}
					//缺少数量
					numberLack1+=numberLack;
				}
				rowCount += mxList.size();
				dataMap.put("rowCount", rowCount);
				dataMap.put("mxList", reList);
				dataMap.put("numberCount1", numberCount1);
				dataMap.put("numberCount2", numberCount2);
				dataMap.put("numberLack1", numberLack1);
				}			
				Configuration configuration = new Configuration();
				configuration.setDefaultEncoding("UTF-8");
				// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
				// 这里我们的模板是放在org.cnzjw.template包下面
				configuration.setClassForTemplateLoading(this.getClass(), "/ftl");
				Template t = configuration.getTemplate("exportOutOrderInfoCPiC.ftl");
				// 输出文档路径及名称
				Calendar now = Calendar.getInstance();
				long lo = now.getTimeInMillis();
				Date date=new Date(lo);
				SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmss");
				String l=sd.format(date);
				String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;// 获得工程运行web的目录
				File file = new File(tmpPath);
				if (!file.exists()) {
					file.mkdirs();
				}
				String realPath = tmpPath +"CGSLCKPL"+ l + ".xls";
				File outFile = new File(realPath);
				Writer out = null;
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(outFile), "UTF-8"));
				if (dataMap != null) {
					t.process(dataMap, out);
				}
				out.close();
				String rootUrl = request.getContextPath() + EXPORT_PATH +"CGSLCKPL"+ l
						+ ".xls";
				Map<String, String> map = new HashMap<String, String>();
				map.put("path", rootUrl);
				map.put("fileName", "CGSLCKPL"+l + ".xls");
				srm.setObj(map);

			} catch (Exception e) {
				e.printStackTrace();
				srm.setCode(0l);
				srm.setMeg("操作失败!");
			}
			  return srm;
		}
	@Override
	public SysRetrunMessage exportApplyList(MdOutWarehouse att_MdOutWarehouse,MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			  if(account.getOrganizaType().equals("20001")){
				  att_MdOutWarehouse.setRbaId(account.getOldId());
				  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				  }else
					  att_MdOutWarehouse.setSuiId(account.getSuiId());
				  att_MdOutWarehouse.setPurchaseType("1");
			  }else if(account.getOrganizaType().equals("20002")){
				  att_MdOutWarehouse.setRbsId(account.getOldId());
				  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				  }else
					  att_MdOutWarehouse.setSuiId(account.getSuiId());
				  att_MdOutWarehouse.setPurchaseType("2");
			  }else if(account.getOrganizaType().equals("20003")){
				  att_MdOutWarehouse.setRbbId(account.getOldId());
				  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				  }else
					  att_MdOutWarehouse.setSuiId(account.getSuiId());
				  att_MdOutWarehouse.setPurchaseType("3");
			  }
			att_MdOutWarehouse.setCompanyType("1");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			DecimalFormat df= new DecimalFormat("######0.00"); 
			DecimalFormat df2= new DecimalFormat("######0"); 
			Map<String, Object> dataMap = new HashMap<String,Object>();
			int rowCount=4;
			dataMap.put("orgName", account.getOrgName());
			String timeArea="";
			if(att_MdOutWarehouse.getFinshDate_start()!=null)
				timeArea += sdf.format(att_MdOutWarehouse.getFinshDate_start());
			if(att_MdOutWarehouse.getFinshDate_end()!=null || att_MdOutWarehouse.getFinshDate_end()!=null)
				timeArea+="-";
			if(att_MdOutWarehouse.getFinshDate_end()!=null)
				timeArea += sdf.format(att_MdOutWarehouse.getFinshDate_end());
			dataMap.put("timeArea", timeArea);
			dataMap.put("userName", account.getUserName());
			List<Map<Object,Object>> list = mdOutWarehouseMxDao.getMxListByOut(att_MdOutWarehouse, att_MdOutWarehouseMx);
			  List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
			  int index=0;
			  if(list != null && list.size() > 0){
				  for(Map<Object,Object> obj: list){
					  index++;
					  Map<String,String> reMap=new HashMap<String,String>();
					  reMap.put("index", index+"");
					  reMap.put("finshDate", obj.get("FINSH_DATE").toString().substring(0, 10));
					  reMap.put("supplier", obj.get("supplier").toString());
					  reMap.put("matName", obj.get("mat_name").toString());
					  reMap.put("typeName", obj.get("type_name")!=null?obj.get("type_name").toString():"");
					  reMap.put("norm", obj.get("NORM").toString());
					  Double price=Double.parseDouble(obj.get("price")!=null?obj.get("price").toString():"0");
					  reMap.put("price", df.format(price));
					  reMap.put("basicUnit", obj.get("Basic_unit").toString());
					  Double number1=obj.get("number1")!=null?Double.parseDouble(obj.get("number1").toString()):0d;
					  reMap.put("number1", df2.format(number1));
					  Double baseNumber=obj.get("base_number")!=null?Double.parseDouble(obj.get("base_number").toString()):0d;
					  reMap.put("baseNumber", df2.format(baseNumber));
					  Double countMoney=price*baseNumber;
					  reMap.put("countMoney", df.format(countMoney));
					  reMap.put("customer", obj.get("CUSTOMER")!=null?obj.get("CUSTOMER").toString():"");
					  reMap.put("customerName", obj.get("CUSTOMER_name")!=null?obj.get("CUSTOMER_name").toString():"");
					  reList.add(reMap);
				  }
				  rowCount+=list.size();
			  }
			  dataMap.put("rowCount", rowCount);
			  dataMap.put("mxList", reList);
				Configuration configuration = new Configuration();
				configuration.setDefaultEncoding("UTF-8");
				// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
				// 这里我们的模板是放在org.cnzjw.template包下面
				configuration.setClassForTemplateLoading(this.getClass(),
						"/ftl");
				Template t = configuration.getTemplate("exportApplyOutList.ftl");
				// 输出文档路径及名称
				Calendar now = Calendar.getInstance();
				long l = now.getTimeInMillis();
				String tmpPath = SystemContext.updown_File_path+TEMP_EXPORT_PATH;//获得工程运行web的目录
				File file = new File(tmpPath);
				if(!file.exists()) {
					file.mkdirs();
				}
		        String realPath = tmpPath +l+ ".xls";
				File outFile = new File(realPath);
				Writer out = null;
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(outFile),"UTF-8"));
				t.process(dataMap, out);
				out.close();
				String rootUrl = request.getContextPath() + EXPORT_PATH+l+ ".xls";
				Map<String,String> map = new HashMap<String,String>();
				map.put("path", rootUrl);
				map.put("fileName", l+ ".xls");
				srm.setObj(map);
			
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}
	
	@Override
	public SysRetrunMessage exportBackList(MdOutWarehouse att_MdOutWarehouse,MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			  if(account.getOrganizaType().equals("20001")){
				  att_MdOutWarehouse.setRbaId(account.getOldId());
				  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				  }else
					  att_MdOutWarehouse.setSuiId(account.getSuiId());
				  att_MdOutWarehouse.setPurchaseType("1");
			  }else if(account.getOrganizaType().equals("20002")){
				  att_MdOutWarehouse.setRbsId(account.getOldId());
				  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				  }else
					  att_MdOutWarehouse.setSuiId(account.getSuiId());
				  att_MdOutWarehouse.setPurchaseType("2");
			  }else if(account.getOrganizaType().equals("20003")){
				  att_MdOutWarehouse.setRbbId(account.getOldId());
				  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				  }else
					  att_MdOutWarehouse.setSuiId(account.getSuiId());
				  att_MdOutWarehouse.setPurchaseType("3");
			  }
			att_MdOutWarehouse.setCompanyType("2");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			DecimalFormat df= new DecimalFormat("######0.00"); 
			DecimalFormat df2= new DecimalFormat("######0"); 
			Map<String, Object> dataMap = new HashMap<String,Object>();
			int rowCount=4;
			dataMap.put("orgName", account.getOrgName());
			String timeArea="";
			if(att_MdOutWarehouse.getFinshDate_start()!=null)
				timeArea += sdf.format(att_MdOutWarehouse.getFinshDate_start());
			if(att_MdOutWarehouse.getFinshDate_end()!=null || att_MdOutWarehouse.getFinshDate_end()!=null)
				timeArea+="-";
			if(att_MdOutWarehouse.getFinshDate_end()!=null)
				timeArea += sdf.format(att_MdOutWarehouse.getFinshDate_end());
			dataMap.put("timeArea", timeArea);
			dataMap.put("userName", account.getUserName());
			List<Map<Object,Object>> list = mdOutWarehouseMxDao.getMxListByOut(att_MdOutWarehouse, att_MdOutWarehouseMx);
			  List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
			  int index=0;
			  if(list != null && list.size() > 0){
				  for(Map<Object,Object> obj: list){
					  index++;
					  Map<String,String> reMap=new HashMap<String,String>();
					  reMap.put("index", index+"");
					  reMap.put("finshDate", obj.get("FINSH_DATE").toString().substring(0, 10));
					  reMap.put("supplier", obj.get("supplier").toString());
					  reMap.put("matName", obj.get("mat_name").toString());
					  reMap.put("typeName", obj.get("type_name")!=null?obj.get("type_name").toString():"");
					  reMap.put("norm", obj.get("NORM").toString());
					  reMap.put("description", obj.get("DESCRIPTION")!=null?obj.get("DESCRIPTION").toString():"");
					  reMap.put("baseNumber", df2.format(Double.parseDouble(obj.get("base_number").toString())));
					  reMap.put("basicUnit", obj.get("Basic_unit").toString());
					  reMap.put("consignee", obj.get("consignee")!=null?obj.get("consignee").toString():"");
					  if(obj.get("order_time")!=null && !obj.get("order_time").toString().trim().equals(""))
						  reMap.put("orderTime", obj.get("order_time").toString().substring(0, 10));
					  else
						  reMap.put("orderTime", ""); 
					  reMap.put("userName", obj.get("USER_name")!=null?obj.get("USER_name").toString():"");
					  reList.add(reMap);
				  }
				  rowCount+=list.size();
			  }
			  dataMap.put("rowCount", rowCount);
			  dataMap.put("mxList", reList);
				Configuration configuration = new Configuration();
				configuration.setDefaultEncoding("UTF-8");
				// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
				// 这里我们的模板是放在org.cnzjw.template包下面
				configuration.setClassForTemplateLoading(this.getClass(),
						"/ftl");
				Template t = configuration.getTemplate("exportBackOutList.ftl");
				// 输出文档路径及名称
				Calendar now = Calendar.getInstance();
				long l = now.getTimeInMillis();
				String tmpPath = SystemContext.updown_File_path+TEMP_EXPORT_PATH;//获得工程运行web的目录
				File file = new File(tmpPath);
				if(!file.exists()) {
					file.mkdirs();
				}
		        String realPath = tmpPath +l+ ".xls";
				File outFile = new File(realPath);
				Writer out = null;
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(outFile),"UTF-8"));
				t.process(dataMap, out);
				out.close();
				String rootUrl = request.getContextPath() + EXPORT_PATH+l+ ".xls";
				Map<String,String> map = new HashMap<String,String>();
				map.put("path", rootUrl);
				map.put("fileName", l+ ".xls");
				srm.setObj(map);
			
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage deleteOutWarehouseMx(Integer wowId, Integer wowType, Integer wowMxId, Integer wiId, Integer momId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
//			MdOutWarehouse mdOutWarehouse = new MdOutWarehouse();
//			mdOutWarehouse.setWowId(wowId);
//			mdOutWarehouse.setWowType(wowType);
//			mdOutWarehouse = (MdOutWarehouse) this.getOne(mdOutWarehouse);
//			if (mdOutWarehouse == null) {
//				sm.setCode(2L);
//				return sm;
//			}
//			MdOutWarehouseMx mdOutWarehouseMx = new MdOutWarehouseMx();
//			mdOutWarehouseMx.setWowMxId(wowMxId);
//			mdOutWarehouseMx = (MdOutWarehouseMx) this.getOne(mdOutWarehouseMx);
//			if (mdOutWarehouseMx == null) {
//				sm.setCode(2L);
//				return sm;
//			}
//
//			Double originNumber = mdOutWarehouseMx.getBaseNumber() == null ? 0 : mdOutWarehouseMx.getBaseNumber();
//			Double originSplit = mdOutWarehouseMx.getSplitQuantity() == null ? 0 : mdOutWarehouseMx.getSplitQuantity();
//			Double nNumber = mdOutWarehouse.getBaseNumber() == null ? 0 : mdOutWarehouse.getBaseNumber();
//			Double sNumber = mdOutWarehouse.getSplitNumber() == null ? 0 : mdOutWarehouse.getSplitNumber();
//			// 修改出库数量信息
//			mdOutWarehouse.setBaseNumber((nNumber + originNumber) >= 0 ? (nNumber + originNumber) : 0);
//			mdOutWarehouse.setSplitNumber((sNumber + originSplit) >= 0 ? (sNumber + originSplit) : 0);

			// 需要计算最小单位数量影响的最大单位数量
//			Double sq = originSplit % ((mdInventory.getRatio() == 0 || mdInventory.getRatio() == null ? 1 : mdInventory.getRatio()));
//			sq = Math.ceil(sq);
//			Double oNumber = originNumber + sq;

			if (wowType == null || Objects.equal(wowType, 1)) { //领料出库
				//修改对应的领料单数目
				MdOutOrderMx mdOutOrderMx = new MdOutOrderMx();
//				mdOutOrderMx.setMooId(mdOutOrder.getMooId());
				mdOutOrderMx.setMomId(momId);
				mdOutOrderMx = (MdOutOrderMx) this.getOne(mdOutOrderMx);
				if (mdOutOrderMx == null) {
//					this.deleteObjects(mdOutWarehouseMx);
					return sm;
				}

				MdOutOrder mdOutOrder = new MdOutOrder();
				mdOutOrder.setMooId(mdOutOrderMx.getMooId());
//				mdOutOrder.setMooCode(mdOutWarehouse.getRelatedBill1());
				mdOutOrder = mdOutOrderDao.findMdOutOrderByMdOutOrder(mdOutOrder);
				if (mdOutOrder == null){
//					this.deleteObjects(mdOutWarehouseMx);
					return sm;
				}

				Double originNumber = 0D;
				Double originSplit = 0D;
				Double nNumber = 0D;
				Double sNumber = 0D;

				//MdOutWarehouse mdOutWarehouse = new MdOutWarehouse();
				//mdOutWarehouse.setWowId(wowId);
				//mdOutWarehouse.setWowType(wowType);
				//mdOutWarehouse = (MdOutWarehouse) this.getOne(mdOutWarehouse);
				//if (mdOutWarehouse != null) {
				//	nNumber = mdOutWarehouse.getBaseNumber() == null ? 0 : mdOutWarehouse.getBaseNumber();
				//	sNumber = mdOutWarehouse.getSplitNumber() == null ? 0 : mdOutWarehouse.getSplitNumber();
				//}
				//MdOutWarehouseMx mdOutWarehouseMx = new MdOutWarehouseMx();
				//mdOutWarehouseMx.setWowMxId(wowMxId);
				//mdOutWarehouseMx = (MdOutWarehouseMx) this.getOne(mdOutWarehouseMx);
				//if (mdOutWarehouseMx != null) {
				//	originNumber = mdOutWarehouseMx.getBaseNumber() == null ? 0 : mdOutWarehouseMx.getBaseNumber();
				//	originSplit = mdOutWarehouseMx.getSplitQuantity() == null ? 0 : mdOutWarehouseMx.getSplitQuantity();
				//}

				// 修改出库数量信息
				//if (mdOutWarehouse != null) {
				//	mdOutWarehouse.setBaseNumber((nNumber + originNumber) >= 0 ? (nNumber + originNumber) : 0);
				//	mdOutWarehouse.setSplitNumber((sNumber + originSplit) >= 0 ? (sNumber + originSplit) : 0);
				//}
				originNumber = mdOutOrderMx.getNumber1();
				originNumber = originSplit == null ? 0d : originNumber;
				originSplit = mdOutOrderMx.getSplitNumber1();
				originSplit = originSplit == null ? 0d : originSplit;
				mdOutOrderMx.setBaseNumber((mdOutOrderMx.getBaseNumber() - originNumber) <= 0  ? 0d : (mdOutOrderMx.getBaseNumber() - originNumber));
				mdOutOrderMx.setSplitQuantity((mdOutOrderMx.getSplitQuantity() - originSplit) <= 0 ? 0 : (mdOutOrderMx.getSplitQuantity() - originSplit));

				mdOutOrder.setNumber1(mdOutOrder.getNumber1() - originNumber);
				mdOutOrder.setNumber6((mdOutOrder.getNumber6() == null ? 0 : mdOutOrder.getNumber6()) - originSplit);
				mdOutOrder.setNumber2((mdOutOrder.getNumber2() != null ? mdOutOrder.getNumber2() : 0) - originNumber);
				mdOutOrder.setNumber7((mdOutOrder.getNumber7() == null ? 0 : mdOutOrder.getNumber7()) - originSplit);
				if (mdOutOrder.getNumber2() <= 0 && mdOutOrder.getNumber7() <= 0)
					mdOutOrder.setFlowState("2");
				else
					mdOutOrder.setFlowState("3");
				this.updateObject(mdOutOrder);

//				this.updateObject(mdOutOrderMx);
				this.deleteObjects(mdOutOrderMx);
				//if (mdOutWarehouseMx != null)
				//	this.deleteObjects(mdOutWarehouseMx);
			} else if (Objects.equal(wowType, 2)) { //订单退货
				//修改对应的订单数目
				MdOutWarehouse mdOutWarehouse = new MdOutWarehouse();
				mdOutWarehouse.setWowId(wowId);
				mdOutWarehouse.setWowType(wowType);
				mdOutWarehouse = (MdOutWarehouse) this.getOne(mdOutWarehouse);
				if (mdOutWarehouse == null) {
					sm.setCode(2L);
					return sm;
				}
				MdOutWarehouseMx mdOutWarehouseMx = new MdOutWarehouseMx();
				mdOutWarehouseMx.setWowMxId(wowMxId);
				mdOutWarehouseMx = (MdOutWarehouseMx) this.getOne(mdOutWarehouseMx);
				if (mdOutWarehouseMx == null) {
					sm.setCode(2L);
					return sm;
				}

				Double originNumber = mdOutWarehouseMx.getBaseNumber() == null ? 0 : mdOutWarehouseMx.getBaseNumber();
				Double originSplit = mdOutWarehouseMx.getSplitQuantity() == null ? 0 : mdOutWarehouseMx.getSplitQuantity();
				Double nNumber = mdOutWarehouse.getBaseNumber() == null ? 0 : mdOutWarehouse.getBaseNumber();
				Double sNumber = mdOutWarehouse.getSplitNumber() == null ? 0 : mdOutWarehouse.getSplitNumber();
				// 修改出库数量信息
				mdOutWarehouse.setBaseNumber((nNumber + originNumber) >= 0 ? (nNumber + originNumber) : 0);
				mdOutWarehouse.setSplitNumber((sNumber + originSplit) >= 0 ? (sNumber + originSplit) : 0);

				MdOrderMx mdOrderMx = new MdOrderMx();
				mdOrderMx.setMomId(momId);
				mdOrderMx = (MdOrderMx) this.getOne(mdOrderMx);
				if (mdOrderMx == null) {
					this.deleteObjects(mdOutWarehouseMx);
					return sm;
				}
				mdOrderMx.setNumber3((mdOrderMx.getNumber3() != null ? mdOrderMx.getNumber3() : 0d) - originNumber);
				mdOrderMx.setNumber3(mdOrderMx.getNumber3() < 0 ? 0 : mdOrderMx.getNumber3());
				Integer moiId = mdOrderMx.getMoiId();
				this.updateObject(mdOrderMx);

				MdOrderInfo mdOrderInfo = new MdOrderInfo();
				mdOrderInfo.setMoiId(moiId);
				mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);
				if (mdOrderInfo == null) {
					this.deleteObjects(mdOutWarehouseMx);
					return sm;
				}
				mdOrderInfo.setNumber4((mdOrderInfo.getNumber4() != null ? mdOrderInfo.getNumber4() : 0) - originNumber);
				mdOrderInfo.setNumber4(mdOrderInfo.getNumber4() < 0 ? 0 : mdOrderInfo.getNumber4());
				this.updateObject(mdOrderInfo);

				Integer mxWiId = mdOutWarehouseMx.getWiId();
				if (!Objects.equal(wiId, mxWiId))
					wiId = mxWiId;
				MdInventory mdInventory = new MdInventory();
				mdInventory.setWiId(wiId);
				mdInventory = (MdInventory) this.getOne(mdInventory);
				if (mdInventory == null) {
					sm.setCode(3L);
					return sm;
				}
				MdInventoryExtend mdInventoryExtend = new MdInventoryExtend();
				mdInventoryExtend.setWiId(wiId);
				mdInventoryExtend.setMmfId(mdOutWarehouseMx.getMmfId());
				mdInventoryExtend = (MdInventoryExtend) this.getOne(mdInventoryExtend);
				if (mdInventoryExtend == null) {
					sm.setCode(4L);
					return sm;
				}
				Double addSplit = originSplit + originNumber * ((mdInventory.getRatio() == 0 || mdInventory.getRatio() == null) ? 1 : mdInventory.getRatio()); //计算成最小单位
				//修改库存信息
//			mdInventory.setQuantity(mdInventory.getQuantity() + oNumber);
//			mdInventory.setBaseNumber((mdInventory.getBaseNumber() == null ? 0 : mdInventory.getBaseNumber()) + originNumber * mdInventory.getRatio() + originSplit);
				mdInventory.setBaseNumber(mdInventory.getBaseNumber() + (addSplit > 0 ? addSplit : 0));
				mdInventory.setQuantity(Math.ceil(mdInventory.getBaseNumber() / mdInventory.getRatio()));
				//修改库存明细
//			mdInventoryExtend.setQuantity(mdInventoryExtend.getQuantity() + oNumber);
//			mdInventoryExtend.setBaseNumber((mdInventoryExtend.getBaseNumber() == null ? 0 : mdInventoryExtend.getBaseNumber()) + originNumber * mdInventory.getRatio() + originSplit);
				mdInventoryExtend.setBaseNumber(mdInventoryExtend.getBaseNumber() + (addSplit > 0 ? addSplit : 0));
				mdInventoryExtend.setQuantity(Math.ceil(mdInventoryExtend.getBaseNumber() / mdInventory.getRatio()));
				this.deleteObjects(mdOutWarehouseMx);
			} else {
				MdOutWarehouse mdOutWarehouse = new MdOutWarehouse();
				mdOutWarehouse.setWowId(wowId);
				mdOutWarehouse.setWowType(wowType);
				mdOutWarehouse = (MdOutWarehouse) this.getOne(mdOutWarehouse);
				if (mdOutWarehouse == null) {
					sm.setCode(2L);
					return sm;
				}
				MdOutWarehouseMx mdOutWarehouseMx = new MdOutWarehouseMx();
				mdOutWarehouseMx.setWowMxId(wowMxId);
				mdOutWarehouseMx = (MdOutWarehouseMx) this.getOne(mdOutWarehouseMx);
				if (mdOutWarehouseMx == null) {
					sm.setCode(2L);
					return sm;
				}

				Double originNumber = mdOutWarehouseMx.getBaseNumber() == null ? 0 : mdOutWarehouseMx.getBaseNumber();
				Double originSplit = mdOutWarehouseMx.getSplitQuantity() == null ? 0 : mdOutWarehouseMx.getSplitQuantity();
				Double nNumber = mdOutWarehouse.getBaseNumber() == null ? 0 : mdOutWarehouse.getBaseNumber();
				Double sNumber = mdOutWarehouse.getSplitNumber() == null ? 0 : mdOutWarehouse.getSplitNumber();
				// 修改出库数量信息
				mdOutWarehouse.setBaseNumber((nNumber + originNumber) >= 0 ? (nNumber + originNumber) : 0);
				mdOutWarehouse.setSplitNumber((sNumber + originSplit) >= 0 ? (sNumber + originSplit) : 0);
				//其余两个类型，属于直接操作库存
				Integer mxWiId = mdOutWarehouseMx.getWiId();
				if (!Objects.equal(wiId, mxWiId))
					wiId = mxWiId;
				MdInventory mdInventory = new MdInventory();
				mdInventory.setWiId(wiId);
				mdInventory = (MdInventory) this.getOne(mdInventory);
				if (mdInventory == null) {
					sm.setCode(3L);
					return sm;
				}
				MdInventoryExtend mdInventoryExtend = new MdInventoryExtend();
				mdInventoryExtend.setWiId(wiId);
				mdInventoryExtend.setMmfId(mdOutWarehouseMx.getMmfId());
				mdInventoryExtend = (MdInventoryExtend) this.getOne(mdInventoryExtend);
				if (mdInventoryExtend == null) {
					sm.setCode(4L);
					return sm;
				}
				Double addSplit = originSplit + originNumber * ((mdInventory.getRatio() == 0 || mdInventory.getRatio() == null) ? 1 : mdInventory.getRatio()); //计算成最小单位
				//修改库存信息
//			mdInventory.setQuantity(mdInventory.getQuantity() + oNumber);
//			mdInventory.setBaseNumber((mdInventory.getBaseNumber() == null ? 0 : mdInventory.getBaseNumber()) + originNumber * mdInventory.getRatio() + originSplit);
				mdInventory.setBaseNumber(mdInventory.getBaseNumber() + (addSplit > 0 ? addSplit : 0));
				mdInventory.setQuantity(Math.ceil(mdInventory.getBaseNumber() / mdInventory.getRatio()));
				//修改库存明细
//			mdInventoryExtend.setQuantity(mdInventoryExtend.getQuantity() + oNumber);
//			mdInventoryExtend.setBaseNumber((mdInventoryExtend.getBaseNumber() == null ? 0 : mdInventoryExtend.getBaseNumber()) + originNumber * mdInventory.getRatio() + originSplit);
				mdInventoryExtend.setBaseNumber(mdInventoryExtend.getBaseNumber() + (addSplit > 0 ? addSplit : 0));
				mdInventoryExtend.setQuantity(Math.ceil(mdInventoryExtend.getBaseNumber() / mdInventory.getRatio()));
				this.deleteObjects(mdOutWarehouseMx);
			}

		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getOutWareHouseCount(MdOutOrder att_MdOutOrder, String orderTimeStart, String orderTimeEnd, String outTimeStart, String outTimeEnd) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			Integer rbaId = null;
			Integer rbsId = null;
			Integer rbbId = null;
			if (account.getOrganizaType().equals("20001")) {//集团增加
				rbaId = account.getOldId();
				att_MdOutOrder.setRbaId(rbaId);
			} else if (account.getOrganizaType().equals("20002")) {//医院增加
				rbsId = account.getOldId();
				att_MdOutOrder.setRbsId(rbsId);
			} else if (account.getOrganizaType().equals("20003")) {//门诊增加
				rbbId = account.getOldId();
				att_MdOutOrder.setRbbId(rbbId);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(outTimeStart != null && !outTimeStart.equals("")) {
				att_MdOutOrder.setOutTimeStart(sdf.parse(outTimeStart));
			}
			if(outTimeEnd != null && !outTimeEnd.equals("")) {
				att_MdOutOrder.setOutTimeEnd(sdf.parse(outTimeEnd));
			}
			if(orderTimeStart != null && !orderTimeStart.equals("")) {
				att_MdOutOrder.setOrderTime_start(sdf.parse(orderTimeStart));
			}
			if(orderTimeEnd != null && !orderTimeEnd.equals("")) {
				att_MdOutOrder.setOrderTime_end(sdf.parse(orderTimeEnd));
			}


			Integer wowType = 0;
			Integer wowType1 = 0;
			Integer wowType2 = 0;
			Integer wowType3 = 0;
			wowType = mdOutWarehouseDao.getWowTypeCount(att_MdOutOrder, null);
			wowType1 = mdOutWarehouseDao.getWowTypeCount(att_MdOutOrder, 1);
			wowType2 = mdOutWarehouseDao.getWowTypeCount(att_MdOutOrder, 2);
			wowType3 = mdOutWarehouseDao.getWowTypeCount(att_MdOutOrder, 3);

			Integer flowState = 0;
			Integer flowState2 = 0;
			Integer flowState3 = 0;
			Integer flowState4 = 0;
			Integer flowState5 = 0;

			flowState = mdOutWarehouseDao.fetFlowStateCount(att_MdOutOrder, null);
			flowState2 = mdOutWarehouseDao.fetFlowStateCount(att_MdOutOrder, 2);
			flowState3 = mdOutWarehouseDao.fetFlowStateCount(att_MdOutOrder, 3);
			flowState4 = mdOutWarehouseDao.fetFlowStateCount(att_MdOutOrder, 4);
			flowState5 = mdOutWarehouseDao.fetFlowStateCount(att_MdOutOrder, 5);

			Map<String, Integer> map = new HashMap<>();
			map.put("wowType", wowType);
			map.put("wowType1", wowType1);
			map.put("wowType2", wowType2);
			map.put("wowType3", wowType3);

			map.put("flowState", flowState);
			map.put("flowState2", flowState2);
			map.put("flowState3", flowState3);
			map.put("flowState4", flowState4);
			map.put("flowState5", flowState5);
			sm.setObj(map);
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

}