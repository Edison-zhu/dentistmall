package com.hsk.dentistmall.web.storage.service.imp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.web.storage.service.IMdEnterWarehouseService;
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
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:54
 */

@Service
public class  MdEnterWarehouseService extends DSTService implements IMdEnterWarehouseService {
   /**
   *业务处理dao类  mdEnterWarehouseDao
   */
	@Autowired
	protected IMdEnterWarehouseDao mdEnterWarehouseDao;
	@Autowired
	private IMdInventoryDao mdInventoryDao;
	@Autowired
	protected IorgDao orgDao;
	@Autowired
	protected IMdNewsInfoDao mdNewsInfoDao;
	@Autowired
	private IMdEnterWarehouseMxDao mdEnterWarehouseMxDao;
	@Autowired
	protected IMdOrderInfoDao mdOrderInfoDao;
	@Autowired
	private IMdItemKeyDao mdItemKeyDao;
	@Autowired
	private IMdOrderAfterSaleDao mdOrderAfterSaleDao;
	@Autowired
	private IMdOrderInfoDao mdorderInfoDao;
 /**
	 * 根据md_enter_warehouse表主键删除MdEnterWarehouse对象记录
     * @param  wewId  Integer类型(md_enter_warehouse表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer wewId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);

			try{
		   			MdEnterWarehouse     att_MdEnterWarehouse= mdEnterWarehouseDao.findMdEnterWarehouseById( wewId) ;
					srm.setObj(att_MdEnterWarehouse);
				} catch (HSKDBException e) {
					e.printStackTrace();
					srm.setCode(0l);
					srm.setMeg(e.getMessage());
				}
				 return srm ;
	}

	@Override
	public SysRetrunMessage findFormObjectNewApply(Integer wewId, Integer moiId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			MdEnterWarehouse mdEnterWarehouse = new MdEnterWarehouse();
			mdEnterWarehouse.setWewId(wewId);
			mdEnterWarehouse = (MdEnterWarehouse) this.getOne(mdEnterWarehouse);
			if (mdEnterWarehouse == null) {
				sm.setCode(2L);
				return sm;
			}
			MdOrderInfo mdOrderInfo;
			if (moiId != null) {
				mdOrderInfo = new MdOrderInfo();
				mdOrderInfo.setMoiId(moiId);
				mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);

			}

			sm.setObj(mdEnterWarehouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sm;
	}

	/**
	 * 根据md_enter_warehouse表主键删除MdEnterWarehouse对象记录
     * @param  wewId  Integer类型(md_enter_warehouse表主键)
	 * @throws HSKException
	 */

	public MdEnterWarehouse findObject(Integer wewId) throws HSKException{
			MdEnterWarehouse  att_MdEnterWarehouse=new MdEnterWarehouse();
			try{
		        att_MdEnterWarehouse= mdEnterWarehouseDao.findMdEnterWarehouseById( wewId) ;
				} catch (HSKDBException e) {
					e.printStackTrace();
					throw new  HSKException(e);
				}
				return  att_MdEnterWarehouse;
	}


	 /**
	 * 根据md_enter_warehouse表主键删除MdEnterWarehouse对象记录
     * @param  wewId  Integer类型(md_enter_warehouse表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wewId) throws HSKException{
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{
				mdEnterWarehouseDao.deleteMdEnterWarehouseById(wewId);
			 } catch (Exception e) {
					e.printStackTrace();
					throw new  HSKException(e);
		     }
		   return srm;
	}

	/**
	 * 根据md_enter_warehouse表主键删除多条MdEnterWarehouse对象记录
     * @param  WewIds  Integer类型(md_enter_warehouse表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wewIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = wewIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					mdEnterWarehouseDao.deleteMdEnterWarehouseById(Integer
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
	 * 新增或修改md_enter_warehouse表记录 ,如果md_enter_warehouse表主键MdEnterWarehouse.WewId为空就是添加，如果非空就是修改
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
     * @return MdEnterWarehouse  修改后的MdEnterWarehouse对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject( MdEnterWarehouse att_MdEnterWarehouse) throws HSKException{
			 SysRetrunMessage srm = new SysRetrunMessage(1l);
			  try{
					mdEnterWarehouseDao.saveOrUpdateMdEnterWarehouse(att_MdEnterWarehouse);
					srm.setObj(att_MdEnterWarehouse);
			    } catch (Exception e) {
					e.printStackTrace();
					throw new HSKException(e);
		        }
			return srm;
	}


	/**
	 *根据MdEnterWarehouse对象作为对(md_enter_warehouse表进行查询，获取分页对象
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject (MdEnterWarehouse att_MdEnterWarehouse) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdEnterWarehouse>());
			  try{
				  SysUserInfo account = this.GetOneSessionAccount();
				  if(account.getOrganizaType().equals("20001")){
					  att_MdEnterWarehouse.setRbaId(account.getOldId());
					  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
					  }else
						  att_MdEnterWarehouse.setSuiId(account.getSuiId());
					  att_MdEnterWarehouse.setPurchaseType("1");
				  }else if(account.getOrganizaType().equals("20002")){
					  att_MdEnterWarehouse.setRbsId(account.getOldId());
					  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
					  }else
						  att_MdEnterWarehouse.setSuiId(account.getSuiId());
					  att_MdEnterWarehouse.setPurchaseType("2");
				  }else if(account.getOrganizaType().equals("20003")){
					  att_MdEnterWarehouse.setRbbId(account.getOldId());
					  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
					  }else
						  att_MdEnterWarehouse.setSuiId(account.getSuiId());
					  att_MdEnterWarehouse.setPurchaseType("3");
				  }
				  att_MdEnterWarehouse.setState("1");
				  att_MdEnterWarehouse.setTab_like("billcode,consignee,relationBillCode");
				  att_MdEnterWarehouse.setTab_order("wewId desc");
				  pm=mdEnterWarehouseDao.getPagerModelByMdEnterWarehouse(att_MdEnterWarehouse);
			    } catch (Exception e) {
					e.printStackTrace();
		        }
		return pm;
	}

	/**
	 * 清除相关的售后
	 * @param masIds
	 * @throws HSKException
	 */
	private void deleteMdAfterSale(String masIds) throws HSKException {
		if (masIds == null || !masIds.equals("")) {
			return;
		}
		try {
			String[] masIdArray = masIds.split(",");
			MdOrderAfterSaleEntity mdOrderAfterSaleEntity = null;
			for (String masIdStr : masIdArray) {
				Integer masId = Integer.parseInt(masIdStr);
				mdOrderAfterSaleEntity = mdOrderAfterSaleDao.getMdOrderSaleAfterByMasId(null, masId);
				if(mdOrderAfterSaleEntity.getMasId() == null){
					continue;
				}
//				mdOrderAfterSaleEntity.setAfterSale(3);
				mdOrderAfterSaleEntity.setAsState(3);
				List<MdOrderAfterSaleMxEntity> list = mdOrderAfterSaleDao.getMdOrderSaleASMxList(mdOrderAfterSaleEntity.getMasId());
				for (MdOrderAfterSaleMxEntity mdOrderAfterSaleMxEntity : list) {
					mdOrderAfterSaleMxEntity.setAfterSale(3);
					mdOrderAfterSaleDao.updateMdOrderSaleAfterSaleMx(mdOrderAfterSaleMxEntity);
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
			throw new HSKException();
		}
	}

	/**
	 * 该方法只允许做新增
	 */
	@Override
	public SysRetrunMessage saveMdEnterWarehouse(MdEnterWarehouse att_MdEnterWarehouse, Integer moiIds, String mmfIds,String momIds,String shus,String number1s,
												 String prices, String pricesAll, String productPTimes, String productValiTimes, String packasgs, String factories,
												 String certnos, String masIds, String retailPrices, String units, String splitNumbers, String backPrintings, String wmsMiIds, String ratio1s, String remarks)throws HSKException {
		/**
		 * 目前订单入库增加了与当前库存联系的字段link_wms_mi_id,link_mmf_id
		 * 订单入库的一些操作会改变拥有联系的物品，若无联系，库存表保存相关的字段，不改变订单物品的属性，因为那是商家的，不是库存的
		 * 若后期修改，别忘了改变联系字段的逻辑，包括领料角色领料时改别名、修改入库明细时候的
		 */
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			MdOrderInfo mdOrderInfo1 = null;
			if (moiIds != null) {
				mdOrderInfo1 = mdorderInfoDao.findMdOrderInfoById(moiIds);

				att_MdEnterWarehouse.setRelationBillCode(mdOrderInfo1.getOrderCode());
				att_MdEnterWarehouse.setConsignee(mdOrderInfo1.getPurchaseAccount());
				att_MdEnterWarehouse.setTelephone(account.getPhoneNumber());
//            att_MdEnterWarehouse.setAddress();
				att_MdEnterWarehouse.setSupplierName(mdOrderInfo1.getApplicantName());
				//转换时间
				String d1 = mdOrderInfo1.getExpressDate();
				Date newDate1 = new Date();
				att_MdEnterWarehouse.setOrderDatetime(mdOrderInfo1.getPlaceOrderTime());
				att_MdEnterWarehouse.setExpectNumber(Double.parseDouble(mdOrderInfo1.getCommodityNumber().toString()));
			}
			//保存入库单信息
			String[] momIdArray = null;
			if(momIds!= null)
				momIdArray=momIds.split(",");
			String[] shuArray = shus.split(",");
			String[] mmfIdArray = mmfIds.split(",");
			String[] wmsMiIdArray = wmsMiIds == null ? null : wmsMiIds.split(",");
			String[] ratio1Array = ratio1s == null ? null : ratio1s.split(",");
			String[] number1Array = null;
			String[] pricesArray = null;
			String[] productPTimeArray = null;
			String[] productPVTimeArray = null;
			String[] packageArray = null;
			String[] factoryArray = null;
			String[] cernoArray = null;
			String[] retailArray = null;
			String[] unitArray  = null;
			String[] splitArray = null;
			String[] backArray = null;
			String[] remarkArray = remarks == null ? null : remarks.split("##");
			if(number1s != null){
				number1Array=number1s.split(",");
			}
			if(prices != null){
				pricesArray=prices.split(",");
			}
			// 20191118 yangfeng 增加五个新字段
			if(productPTimes != null){
				productPTimeArray=productPTimes.split(",");
			}
			if(productValiTimes != null){
				productPVTimeArray=productValiTimes.split(",");
			}
			if(packasgs != null){
				packageArray=packasgs.split(",");
			}
			if(factories != null){
				factoryArray=factories.split(",");
			}
			if(certnos != null){
				cernoArray=certnos.split(",");
			}
			if (retailPrices != null) {
				retailArray = retailPrices.split(",");
			}
			if (units != null) {
				unitArray = units.split(",");
			}
			if (splitNumbers != null) {
				splitArray = splitNumbers.split(",");
			}
			if (backPrintings != null) {
				backArray = backPrintings.split(",");
			}
			Double expectNumber = 0d;
			for(String shu : shuArray)
				expectNumber += Double.parseDouble(shu);
			att_MdEnterWarehouse.setExpectNumber(expectNumber);

			if (retailArray != null && retailArray.length > 0) {
				Double retailPrice = 0d;
				for (String retail : retailArray) {
					if (!retail.equals("")) {
						retailPrice += Double.parseDouble(retail);
					}
				}
				att_MdEnterWarehouse.setRetailMoney(retailPrice);
			}
			if (pricesArray != null && pricesArray.length > 0) {
				Double price = 0d;
				for (String p : pricesArray) {
					if (!p.equals("")) {
						price += Double.parseDouble(p);
					}
				}
				att_MdEnterWarehouse.setPurchaseMoney(price);
			}
			Integer wmsMiId = null;
			Integer mmfId = null;
			Double ratio1 = 1d;
			MdMaterielInfo mdMaterielInfo1;
			MdMaterielFormat mdMaterielFormat1;
			if (att_MdEnterWarehouse.getBillType().equals("2")) {
				for (int i = 0; i < wmsMiIdArray.length; i++) {
					if (!wmsMiIdArray[i].equals(""))
						wmsMiId = Integer.parseInt(wmsMiIdArray[i]);
					if (!mmfIdArray[i].equals(""))
						mmfId = Integer.parseInt(mmfIdArray[i]);
					if (wmsMiId == null || mmfId == null) {
						srm.setCode(7L); //存在空
						return srm;
					}
					mdMaterielInfo1 = new MdMaterielInfo();
					mdMaterielInfo1.setWmsMiId(wmsMiId);
					mdMaterielInfo1 = (MdMaterielInfo) this.getOne(mdMaterielInfo1);
					mdMaterielFormat1 = new MdMaterielFormat();
					mdMaterielFormat1.setMmfId(mmfId);
					mdMaterielFormat1 = (MdMaterielFormat) this.getOne(mdMaterielFormat1);
					if (!Objects.equals(mdMaterielInfo1.getWmsMiId(), mdMaterielFormat1.getWmsMiId())) {
						srm.setCode(6L); // 查到的数据不匹配
						return srm;
					}
					wmsMiId = null;
					mmfId = null;
				}
			}

			//查看当前组织是否存在集团、医院
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			String orgNameStr = orgDao.getNameStr(sysOrgGx);
			Integer one=null;
			Integer two=null;
			Integer three=null;
			if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				att_MdEnterWarehouse.setRbaId(account.getOldId());
				att_MdEnterWarehouse.setPurchaseType("1");
				one = account.getOldId();
			}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				if(orgMap.containsKey("one")){//如果存在上级集团
					att_MdEnterWarehouse.setRbaId(Integer.parseInt(orgMap.get("one")));
					one = Integer.parseInt(orgMap.get("one"));
				}
				att_MdEnterWarehouse.setRbsId(account.getOldId());
				att_MdEnterWarehouse.setPurchaseType("2");
				two = account.getOldId();
			}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
				if(orgMap.containsKey("one")){//如果存在上级集团
					att_MdEnterWarehouse.setRbaId(Integer.parseInt(orgMap.get("one")));
					one = Integer.parseInt(orgMap.get("one"));
				}
				if(orgMap.containsKey("tow")){//如果存在上级医院
					att_MdEnterWarehouse.setRbsId(Integer.parseInt(orgMap.get("tow")));
					two = Integer.parseInt(orgMap.get("tow"));
				}
				att_MdEnterWarehouse.setRbbId(account.getOldId());
				att_MdEnterWarehouse.setPurchaseType("3");
				three = account.getOldId();
			}
			att_MdEnterWarehouse.setSuiId(account.getSuiId());
			att_MdEnterWarehouse.setConsignor(account.getUserName());
			att_MdEnterWarehouse.setParamStr01(orgNameStr);
			att_MdEnterWarehouse.setState("1");
			att_MdEnterWarehouse.setReceiptDatetime(new Date());


			if (att_MdEnterWarehouse.getWewId() == null) {
				// 判断是否重复提交
				Integer count = mdEnterWarehouseDao.getRepeatMdEnterwarehouse(att_MdEnterWarehouse);
				if (att_MdEnterWarehouse.getBillcode() == null || att_MdEnterWarehouse.getBillcode().equals("")) {
					srm.setCode(-3L); // 没有入库编号
					return srm;
				}
				if (count > 0) {
					srm.setCode(-2L); // 重复提交
					return srm;
				}
				this.newObject(att_MdEnterWarehouse);
			} else {
				this.updateObject(att_MdEnterWarehouse);
			}

			//保存入库明细信息和库存信息
			if(att_MdEnterWarehouse.getBillType().equals("2")){//如果是订单入库
				Integer moiId=null;
				Double count=0d;
				Double aPrice = this.getDiscount(att_MdEnterWarehouse.getRelationBillCode());
//				List<Integer> wmsArray = new ArrayList<>();
//				Map<Integer, List<String>> mapList = new HashMap<>();
//				String[] tempC = certnos.split(",");
//				String[] tempB = backPrintings.split(",");
//				for(int i=0;i < momIdArray.length;i++) {
//					Integer momId = Integer.parseInt(momIdArray[i]);
//					MdOrderMx mdOrderMx = new MdOrderMx();
//					mdOrderMx.setMomId(momId);
//					mdOrderMx = (MdOrderMx) this.getOne(mdOrderMx);
//					wmsArray.add(mdOrderMx.getWmsMiId());
//					if (mapList.get(mdOrderMx.getWmsMiId()) == null)
//						mapList.put(mdOrderMx.getWmsMiId(), new ArrayList<String>());
//					if (tempC.length > i && !tempC[i].equals(""))
//						mapList.get(mdOrderMx.getWmsMiId()).add(tempC[i]);
//					if (tempB.length > i && !tempB[i].equals(""))
//						mapList.get(mdOrderMx.getWmsMiId()).add(tempB[i]);
//
//				}
//				// 判断注册证号和批号是否存在重复
//				Set s = new HashSet(Arrays.asList(tempC));
//				if (s.size() < tempC.length) {
//					srm.setCode(4L);
//					return srm;
//				}
//				s = new HashSet(Arrays.asList(tempC));
//				if (s.size() < tempC.length) {
//					srm.setCode(5L);
//					return srm;
//				}

				for(int i=0;i < momIdArray.length;i++){
					Integer momId = Integer.parseInt(momIdArray[i]);
					if (wmsMiIdArray != null && wmsMiIdArray.length > i && !wmsMiIdArray[i].equals(""))
						wmsMiId = Integer.parseInt(wmsMiIdArray[i]);
					if (mmfIdArray != null && mmfIdArray.length > i && !mmfIdArray[i].equals(""))
						mmfId = Integer.parseInt(mmfIdArray[i]);
					if (!ratio1Array[i].equals(""))
						ratio1 = Double.parseDouble(ratio1Array[i]);
					mdMaterielInfo1 = new MdMaterielInfo();
					mdMaterielInfo1.setWmsMiId(wmsMiId);
					mdMaterielInfo1 = (MdMaterielInfo) this.getOne(mdMaterielInfo1);

					mdMaterielFormat1 = new MdMaterielFormat();
					mdMaterielFormat1.setMmfId(mmfId);
					mdMaterielFormat1 = (MdMaterielFormat) this.getOne(mdMaterielFormat1);
					Double matNumber = Double.parseDouble(shuArray[i]);
					Double number1 = null;
					Double price=null;
					Date productPTime = null;
					Date productValiTime = null;
					String packasg = null;
					String factory = null;
					String certno = null;
					Double retialPrice = null;
					String unit = null;
					Double splitNumber = null;
					String backPrinting = null;
					String remark = null;
					if(number1Array !=null){
						number1 = Double.parseDouble(number1Array[i]);
					}
					if(pricesArray !=null){
						price = Double.parseDouble(pricesArray[i]);
					}
					if (retailArray != null && retailArray.length > i && !retailArray[i].equals("")) {
						retialPrice = Double.parseDouble(retailArray[i]);
					}
					if (unitArray != null && unitArray.length > i) {
						unit = unitArray[i];
					}
					if (splitArray != null && splitArray.length > i && !splitArray[i].equals("")) {
						splitNumber = Double.parseDouble(splitArray[i]);
					}
					if (backArray != null && backArray.length > i) {
						backPrinting = backArray[i];
					}
					// 20191118 yangfeng 增加五个新字段
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if(productPTimeArray != null && productPTimeArray.length > i){
						productPTime = sdf.parse(productPTimeArray[i]);
					}
					if(productPVTimeArray != null && productPVTimeArray.length > i && !productPVTimeArray[i].equals("")){
						productValiTime = sdf.parse(productPVTimeArray[i]);
					}
					if(packageArray != null && packageArray.length > i){
						packasg = packageArray[i];
					}
					if(factoryArray != null && factoryArray.length > i){
						factory = factoryArray[i];
					}
					if(cernoArray != null && cernoArray.length > i){
						certno = cernoArray[i];
					}
					if (remarkArray != null && remarkArray.length > i)
						remark = remarkArray[i];

					count += matNumber;
					//查询订单明细信息
					MdOrderMx mdOrderMx = new MdOrderMx();
					mdOrderMx.setMomId(momId);
					mdOrderMx = (MdOrderMx) this.getOne(mdOrderMx);
					moiId=mdOrderMx.getMoiId();

					MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
					mdMaterielInfo.setWmsMiId(mdOrderMx.getWmsMiId());
					mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
					//保存入库明细信息
					MdEnterWarehouseMx mdEnterWarehouseMx = new MdEnterWarehouseMx();
					mdEnterWarehouseMx.setWewId(att_MdEnterWarehouse.getWewId());
//					if (wmsMiId != null)
						mdEnterWarehouseMx.setLinkWmsMiId(wmsMiId);
//					if (mmfId != null)
						mdEnterWarehouseMx.setLinkMmfId(mmfId);
//					mdEnterWarehouseMx.setWmsMiId(wmsMiId);
//					mdEnterWarehouseMx.setMmfId(mmfId);
					mdEnterWarehouseMx.setWmsMiId(mdOrderMx.getWmsMiId());
					mdEnterWarehouseMx.setMmfId(mdOrderMx.getMmfId());
					mdEnterWarehouseMx.setPlaceOrderTime(mdOrderMx.getPlaceOrderTime());
					mdEnterWarehouseMx.setMatNumber(matNumber);
					mdEnterWarehouseMx.setNumber1(number1);
					mdEnterWarehouseMx.setPrice(mdOrderMx.getUnitMoney()-aPrice);
					mdEnterWarehouseMx.setPurchaseUnit(mdOrderMx.getPurchaseUnit());
					mdEnterWarehouseMx.setMatCode(mdOrderMx.getMatCode());
					mdEnterWarehouseMx.setMatName(mdOrderMx.getMatName());
					mdEnterWarehouseMx.setBasicUnit(mdOrderMx.getBasicUnit());
					mdEnterWarehouseMx.setNorm(mdMaterielFormat1.getMmfName());
					mdEnterWarehouseMx.setMatType(mdOrderMx.getMatType());
					mdEnterWarehouseMx.setMatType1(mdOrderMx.getMatType1());
					mdEnterWarehouseMx.setMatType2(mdOrderMx.getMatType2());
					mdEnterWarehouseMx.setMatType3(mdOrderMx.getMatType3());
					mdEnterWarehouseMx.setDescribes(mdOrderMx.getDescribes());
					mdEnterWarehouseMx.setReceiptDatetime(att_MdEnterWarehouse.getReceiptDatetime());
					mdEnterWarehouseMx.setProductTime(productPTime);
					mdEnterWarehouseMx.setProductValitime(productValiTime);
					mdEnterWarehouseMx.setPackageWay(mdMaterielInfo == null ? mdMaterielInfo1.getProductName() : mdMaterielInfo.getProductName());
					mdEnterWarehouseMx.setProductFactory(mdMaterielInfo == null ? mdMaterielInfo1.getProductFactory() : mdMaterielInfo.getProductFactory());
					mdEnterWarehouseMx.setBatchCertNo(certno);
					mdEnterWarehouseMx.setRatio1(ratio1);
					mdEnterWarehouseMx.setRemark(remark);
					if (backPrinting != null)
						mdEnterWarehouseMx.setBackPrinting(backPrinting);

//					MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
//					mdMaterielInfo.setWmsMiId(mdOrderMx.getWmsMiId());
//					mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);

					if (retialPrice != null)
						mdEnterWarehouseMx.setRetailMoney(retialPrice);
//					if (unit != null)
						mdEnterWarehouseMx.setUnit((mdMaterielInfo1.getSplitUnit() == null ? mdMaterielInfo1.getBasicUnit() : mdMaterielInfo1.getSplitUnit()));
					if (splitNumber != null)
						mdEnterWarehouseMx.setSplitQuantity(splitNumber);

					this.newObject(mdEnterWarehouseMx);

//					mdMaterielInfo1.setValiedDate(productValiTime);
//					this.updateObject(mdMaterielInfo1);

					//增加ItemKey值


					MdItemMxView mdItemMxView = new MdItemMxView();
					mdItemMxView.setRbaId(one);//从session中获取集团ID
					mdItemMxView.setRbsId(two);//从session中获取医院ID
					mdItemMxView.setRbbId(three);//从session中获取门诊ID
					mdItemMxView.setPurchaseType(att_MdEnterWarehouse.getPurchaseType());//从session中采购单位类型
//					if (mmfId != null)
//						mdItemMxView.setLinkMmfId(mmfId);
//					if (wmsMiId != null)
//						mdItemMxView.setLinkWmsMiId(wmsMiId);

					mdItemMxView.setMmfId(mmfId);
					mdItemMxView.setWmsMiId(wmsMiId);
//					mdItemMxView.setMmfId(mdOrderMx.getMmfId());
//					mdItemMxView.setWmsMiId(mdOrderMx.getWmsMiId());
					mdItemMxView.setMatName(mdMaterielInfo1.getMatName());
					mdItemMxView.setMmfName(mdMaterielFormat1.getMmfName());
					mdItemMxView = mdItemKeyDao.newMdItemKey(mdItemMxView, mmfId, wmsMiId);
					//增加库存
					MdInventory att_MdInventory = new MdInventory();
					att_MdInventory.setRbaId(one);//从session中获取集团ID
					att_MdInventory.setRbsId(two);//从session中获取医院ID
					att_MdInventory.setRbbId(three);//从session中获取门诊ID
//					att_MdInventory.setUnit(mdEnterWarehouseMx.getBasicUnit());
					att_MdInventory.setBasicUnit(mdMaterielInfo1.getBasicUnit());
					att_MdInventory.setUnit(mdMaterielInfo1.getSplitUnit() == null ? mdMaterielInfo1.getBasicUnit() : mdMaterielInfo1.getSplitUnit());
					att_MdInventory.setPurchaseType(att_MdEnterWarehouse.getPurchaseType());//从session中采购单位类型
					att_MdInventory.setItemKeyId(mdItemMxView.getItemKeyId()+"");
					att_MdInventory.setState("1");
//					att_MdInventory.setBasicUnit(mdOrderMx.getBasicUnit());
//					att_MdInventory.setQuantity(matNumber);
//					att_MdInventory.setBaseNumber(splitNumber);

					// 修改本仓库关联物品的有效期?
//					if (mmfId != null) {
//						mdMaterielInfo.setValiedDate(productValiTime);
//					}
					att_MdInventory.setValiedDate(productValiTime);
					att_MdInventory=mdInventoryDao.insertMdInventory(att_MdInventory, mdMaterielInfo1.getBasicCoefficent(), matNumber, splitNumber);
					//保存库存明细信息
					MdInventoryExtend att_MdInventoryExtend=new MdInventoryExtend();
					att_MdInventoryExtend.setWiId(att_MdInventory.getWiId());
//					if (wmsMiId != null)
						att_MdInventoryExtend.setLinkWmsMiId(mdOrderMx.getWmsMiId());
//					if (mmfId != null)
						att_MdInventoryExtend.setLinkMmfId(mdOrderMx.getMmfId());
					att_MdInventoryExtend.setWmsMiId(wmsMiId);
					att_MdInventoryExtend.setMmfId(mmfId);
//					att_MdInventoryExtend.setWmsMiId(mdOrderMx.getWmsMiId());
//					att_MdInventoryExtend.setMmfId(mdOrderMx.getMmfId());
					att_MdInventoryExtend.setBasicUnit(att_MdInventory.getBasicUnit());
					att_MdInventoryExtend.setUnit(att_MdInventory.getUnit());
					att_MdInventoryExtend.setBaseNumber((splitNumber == null ? 0 : splitNumber));
					att_MdInventoryExtend.setQuantity(Math.floor(att_MdInventoryExtend.getBaseNumber() / att_MdInventory.getRatio()));
					att_MdInventoryExtend.setPrice(mdOrderMx.getUnitMoney()-aPrice);
					att_MdInventoryExtend.setBasePrice((mdOrderMx.getUnitMoney()-aPrice)/att_MdInventory.getRatio());
					att_MdInventoryExtend.setRelatedCode(att_MdEnterWarehouse.getRelationBillCode());
					att_MdInventoryExtend.setPurchaseUser(att_MdEnterWarehouse.getConsignee());
					att_MdInventoryExtend.setCreateDate(new Date());
					att_MdInventoryExtend.setEditDate(new Date());

					att_MdInventoryExtend.setMatName(mdMaterielInfo1.getMatName());
					att_MdInventoryExtend.setBasicUnit(mdMaterielInfo1.getBasicUnit());
					att_MdInventoryExtend.setMmfName(mdMaterielFormat1.getMmfName());
					att_MdInventoryExtend.setApplicantName(mdMaterielInfo1.getApplicantName());
					att_MdInventoryExtend.setMatType(mdMaterielInfo1.getMatType());
					att_MdInventoryExtend.setMatType1(mdMaterielInfo1.getMatType1());
					att_MdInventoryExtend.setMatType2(mdMaterielInfo1.getMatType2());
					att_MdInventoryExtend.setMatType3(mdMaterielInfo1.getMdWmsMiId()+"");
					/**
					 * yanglei
					 * 修改字段ProductNumber 改为ProductFactory get这个字段时用到
					 */
					att_MdInventoryExtend.setProductName(mdMaterielInfo1.getProductName());
					att_MdInventoryExtend.setBrand(mdMaterielInfo1.getBrand());
					att_MdInventoryExtend.setState("1");
					if (retialPrice != null)
						att_MdInventoryExtend.setRetailPrice(retialPrice);
//					if (unit != null)
//						att_MdInventoryExtend.setUnit(unit);
					if (splitNumber != null)
						att_MdInventoryExtend.setSplitQuantity(att_MdInventoryExtend.getQuantity() * att_MdInventory.getRatio() + splitNumber);
					if (backPrinting != null)
						att_MdInventoryExtend.setBackPrinting(backPrinting);

					att_MdInventoryExtend.setWewMxId(mdEnterWarehouseMx.getWewMxId());
					this.newObject(att_MdInventoryExtend);
					//修改订单明细入库确认数量
					Double number2 = mdOrderMx.getNumber2() == null ? 0d : mdOrderMx.getNumber2();
					if (mdOrderMx.getEnterNumber()!=null&&!mdOrderMx.getEnterNumber().equals("")){
						mdOrderMx.setEnterNumber((mdOrderMx.getEnterNumber()+matNumber) > number2 ? number2 : (mdOrderMx.getEnterNumber()+matNumber));
					}else {
						mdOrderMx.setEnterNumber((0.0+matNumber) > number2 ? number2 : (0.0+matNumber));
					}
					this.updateObject(mdOrderMx);

					mdEnterWarehouseMx.setCurNumber(att_MdInventory.getBaseNumber());
					this.updateObject(mdEnterWarehouseMx);
				}
				MdOrderInfo mdOrderInfo = new MdOrderInfo();
				mdOrderInfo.setMoiId(moiId);
				mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);
				mdOrderInfo.setNumber3((mdOrderInfo.getNumber3()!=null?mdOrderInfo.getNumber3():0)+count);
				if(mdOrderInfo.getNumber3() > (mdOrderInfo.getNumber1()!=null?mdOrderInfo.getNumber1():0d)){
					//消息提醒,提醒采购人确认入库
					MdNewsInfo mdNewsInfo = new MdNewsInfo();
					mdNewsInfo.setOrderId(mdOrderInfo.getMoiId());
					mdNewsInfo.setUiId(mdOrderInfo.getPurchaseId());
					mdNewsInfo.setUiType(2);
					mdNewsInfo.setNewsType(6);//订单发货
					mdNewsInfo.setIsView(1);
					mdNewsInfo.setCreateDate(new Date());
					mdNewsInfo.setContent("订单 "+mdOrderInfo.getOrderCode()+"已入库 请确认订单！");
					mdNewsInfoDao.saveMdNewsInfo(mdNewsInfo);
				}
				if(mdOrderInfo.getNumber3() >= Double.parseDouble(mdOrderInfo.getCommodityNumber().toString())){
					mdOrderInfo.setProcessStatus("5");
				}
				this.updateObject(mdOrderInfo);

//				deleteMdAfterSale(masIds);
			}else if(att_MdEnterWarehouse.getBillType().equals("1")){//如果是商品入库
				for(int i=0;i < mmfIdArray.length;i++){
					mmfId = Integer.parseInt(mmfIdArray[i]);
					if (!ratio1Array[i].equals(""))
						ratio1 = Double.parseDouble(ratio1Array[i]);
					Double matNumber = Double.parseDouble(shuArray[i]);
					Double price=null;
					Date productPTime = null;
					Date productValiTime = null;
					String packasg = null;
					String factory = null;
					String certno = null;
					Double retialPrice = null;
					String unit = null;
					Double splitNumber = null;
					String backPrinting = null;
					String remark = null;
					if(pricesArray !=null){
						price = Double.parseDouble(pricesArray[i]);
					}
					if (retailArray != null && retailArray.length > i && !retailArray[i].equals("")) {
						retialPrice = Double.parseDouble(retailArray[i]);
					}
					if (unitArray != null && unitArray.length > i) {
						unit = unitArray[i];
					}
					if (splitArray != null && splitArray.length > i && !splitArray[i].equals("")) {
						splitNumber = Double.parseDouble(splitArray[i]);
					}
					if (backArray != null && backArray.length > i) {
						backPrinting = backArray[i];
					}
					if (remarkArray != null && remarkArray.length > i)
						remark = remarkArray[i];


					// 20191118 yangfeng 增加五个新字段
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if(productPTimeArray != null && productPTimeArray.length > i && !productPTimeArray[i].equals("")){
						productPTime = sdf.parse(productPTimeArray[i]);
					}
					if(productPVTimeArray != null && productPVTimeArray.length > i && !productPVTimeArray[i].equals("")){
						productValiTime = sdf.parse(productPVTimeArray[i]);
					}
					if(packageArray != null && packageArray.length > i){
						packasg = packageArray[i];
					}
					if(factoryArray != null && factoryArray.length > i){
						factory = factoryArray[i];
					}
					if(cernoArray != null && cernoArray.length > i){
						certno = cernoArray[i];
					}
					//查询规格信息
					MdMaterielFormat mdMaterielFormat=new MdMaterielFormat();
					mdMaterielFormat.setMmfId(mmfId);
					mdMaterielFormat = (MdMaterielFormat)this.getOne(mdMaterielFormat);
					//查询商品明细信息
					MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
					mdMaterielInfo.setWmsMiId(mdMaterielFormat.getWmsMiId());
					mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
					if (productValiTime != null)
						mdMaterielInfo.setValiedDate(productValiTime);
					//保存入库明细信息
					MdEnterWarehouseMx mdEnterWarehouseMx = new MdEnterWarehouseMx();
					mdEnterWarehouseMx.setWewId(att_MdEnterWarehouse.getWewId());
					mdEnterWarehouseMx.setWmsMiId(mdMaterielInfo.getWmsMiId());
					mdEnterWarehouseMx.setMmfId(mmfId);
					mdEnterWarehouseMx.setLinkMmfId(mmfId);
					mdEnterWarehouseMx.setLinkWmsMiId(mdMaterielInfo.getWmsMiId());
					mdEnterWarehouseMx.setPrice(price);
					mdEnterWarehouseMx.setMatNumber(matNumber);
					mdEnterWarehouseMx.setMatCode(mdMaterielInfo.getMatCode());
					mdEnterWarehouseMx.setMatName(mdMaterielInfo.getMatName());
					mdEnterWarehouseMx.setBasicUnit(mdMaterielInfo.getBasicUnit());
					mdEnterWarehouseMx.setNorm(mdMaterielFormat.getMmfName());
					mdEnterWarehouseMx.setMatType(mdMaterielInfo.getMatType());
					mdEnterWarehouseMx.setMatType1(mdMaterielInfo.getMatType1());
					mdEnterWarehouseMx.setMatType2(mdMaterielInfo.getMatType2());
					mdEnterWarehouseMx.setMatType3(mdMaterielInfo.getMatType3());
					mdEnterWarehouseMx.setReceiptDatetime(att_MdEnterWarehouse.getReceiptDatetime());
					mdEnterWarehouseMx.setProductTime(productPTime);
					mdEnterWarehouseMx.setProductValitime(productValiTime);
					mdEnterWarehouseMx.setPackageWay(mdMaterielInfo.getProductName());
					mdEnterWarehouseMx.setProductFactory(mdMaterielInfo.getProductFactory());
					mdEnterWarehouseMx.setBatchCertNo(certno);
					mdEnterWarehouseMx.setRatio1(ratio1);
					mdEnterWarehouseMx.setRemark(remark);
					if (backPrinting != null)
						mdEnterWarehouseMx.setBackPrinting(backPrinting);
					if (retialPrice != null)
						mdEnterWarehouseMx.setRetailMoney(retialPrice);
//					if (unit != null)
					mdEnterWarehouseMx.setUnit((mdMaterielInfo.getSplitUnit() == null ? mdMaterielInfo.getBasicUnit() : mdMaterielInfo.getSplitUnit()));
					if (splitNumber != null)
						mdEnterWarehouseMx.setSplitQuantity(splitNumber);
					this.newObject(mdEnterWarehouseMx);
//					mdMaterielInfo.setValiedDate(productValiTime);
//					this.updateObject(mdMaterielInfo);
					//增加ItemKey值
					MdItemMxView mdItemMxView = new MdItemMxView();
					mdItemMxView.setRbaId(one);//从session中获取集团ID
					mdItemMxView.setRbsId(two);//从session中获取医院ID
					mdItemMxView.setRbbId(three);//从session中获取门诊ID
					mdItemMxView.setPurchaseType(att_MdEnterWarehouse.getPurchaseType());//从session中采购单位类型
					mdItemMxView.setMmfId(mmfId);
					mdItemMxView.setWmsMiId(mdMaterielInfo.getWmsMiId());
//					mdItemMxView.setLinkMmfId(mmfId);
//					mdItemMxView.setLinkWmsMiId(mdMaterielInfo.getWmsMiId());
					mdItemMxView.setMatName(mdMaterielInfo.getMatName());
					mdItemMxView.setMmfName(mdMaterielFormat.getMmfName());
					mdItemMxView = mdItemKeyDao.newMdItemKey(mdItemMxView, mmfId, mdMaterielInfo.getWmsMiId());
					//增加库存
					MdInventory att_MdInventory = new MdInventory();
					att_MdInventory.setRbaId(one);//从session中获取集团ID
					att_MdInventory.setRbsId(two);//从session中获取医院ID
					att_MdInventory.setRbbId(three);//从session中获取门诊ID
					att_MdInventory.setPurchaseType(att_MdEnterWarehouse.getPurchaseType());//从session中采购单位类型
					att_MdInventory.setItemKeyId(mdItemMxView.getItemKeyId()+"");
					att_MdInventory.setState("1");
					att_MdInventory.setBasicUnit(mdMaterielInfo.getBasicUnit());
					att_MdInventory.setUnit(mdMaterielInfo.getSplitUnit() == null ? mdMaterielInfo.getBasicUnit() : mdMaterielInfo.getSplitUnit());
//					att_MdInventory.setQuantity(matNumber);
					att_MdInventory.setValiedDate(productValiTime);
					att_MdInventory =mdInventoryDao.insertMdInventory(att_MdInventory, mdMaterielInfo.getBasicCoefficent(), matNumber, splitNumber);
					//保存库存明细信息
					MdInventoryExtend att_MdInventoryExtend=new MdInventoryExtend();
					att_MdInventoryExtend.setWiId(att_MdInventory.getWiId());
					att_MdInventoryExtend.setWmsMiId(mdMaterielInfo.getWmsMiId());
					att_MdInventoryExtend.setMmfId(mmfId);
					att_MdInventoryExtend.setLinkWmsMiId(mdMaterielInfo.getWmsMiId());
					att_MdInventoryExtend.setLinkMmfId(mmfId);
					att_MdInventoryExtend.setBasicUnit(att_MdInventory.getBasicUnit());
					att_MdInventoryExtend.setUnit(att_MdInventory.getUnit());
					att_MdInventoryExtend.setBaseNumber((splitNumber == null ? 0 : splitNumber));
					att_MdInventoryExtend.setQuantity(Math.floor(att_MdInventoryExtend.getBaseNumber() / att_MdInventory.getRatio()));
					att_MdInventoryExtend.setBasePrice(price/att_MdInventory.getRatio());
					att_MdInventoryExtend.setPrice(price);
					att_MdInventoryExtend.setRelatedCode(att_MdEnterWarehouse.getBillcode());
					att_MdInventoryExtend.setPurchaseUser(att_MdEnterWarehouse.getConsignee());
					att_MdInventoryExtend.setCreateDate(new Date());
					att_MdInventoryExtend.setEditDate(new Date());

					att_MdInventoryExtend.setMatName(mdMaterielInfo.getMatName());
					att_MdInventoryExtend.setMmfName(mdMaterielFormat.getMmfName());
					att_MdInventoryExtend.setApplicantName(mdMaterielInfo.getApplicantName());
					att_MdInventoryExtend.setLabelInfo(mdMaterielInfo.getLabelInfo());
					/**
					 * yanglei
					 * 修改字段ProductNumber 改为ProductFactory get这个字段时用到
					 */
					att_MdInventoryExtend.setProductName(mdMaterielInfo.getProductName());
					att_MdInventoryExtend.setBrand(mdMaterielInfo.getBrand());
					att_MdInventoryExtend.setState("1");
					if (retialPrice != null)
						att_MdInventoryExtend.setRetailPrice(retialPrice);
//					if (unit != null)
//						att_MdInventoryExtend.setUnit(unit);
					if (splitNumber != null)
						att_MdInventoryExtend.setSplitQuantity(splitNumber);
					if (backPrinting != null)
						att_MdInventoryExtend.setBackPrinting(backPrinting);
					att_MdInventoryExtend.setWewMxId(mdEnterWarehouseMx.getWewMxId());
					this.newObject(att_MdInventoryExtend);

					mdEnterWarehouseMx.setCurNumber(att_MdInventory.getBaseNumber());
					mdEnterWarehouseMx.setWiId(att_MdInventory.getWiId());
//					mdEnterWarehouseMx.setMieId(att_MdInventoryExtend.getMieId());
					this.updateObject(mdEnterWarehouseMx);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
			throw new  HSKException(e);
		}
		return srm;
	}

	@Override
	public SysRetrunMessage exportInfo(Integer wewId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			DecimalFormat df= new DecimalFormat("######0.00");
			DecimalFormat df2= new DecimalFormat("######0");
			Map<String, Object> dataMap = new HashMap<String,Object>();
			int rowCount=10;
			MdEnterWarehouse att_MdEnterWarehouse= mdEnterWarehouseDao.findMdEnterWarehouseById( wewId) ;
			//MdEnterWarehouseMx mx = new MdEnterWarehouseMx();
			MdEnterwarehousemxMaterielEntity mx=new MdEnterwarehousemxMaterielEntity();
			//mx.setWewId(att_MdEnterWarehouse.getWewId());
			mx.setWewId(att_MdEnterWarehouse.getWewId());
			List<MdEnterwarehousemxMaterielEntity> mxList = this.getList(mx);
			MdMaterielInfo mm=new MdMaterielInfo();
			dataMap.put("billcode", att_MdEnterWarehouse.getBillcode());
			dataMap.put("receiptDatetime", sdf.format(att_MdEnterWarehouse.getReceiptDatetime()));
			dataMap.put("orgName", account.getOrgName());
			dataMap.put("relationBillCode", att_MdEnterWarehouse.getRelationBillCode());
			dataMap.put("supplierName", att_MdEnterWarehouse.getSupplierName()!=null?att_MdEnterWarehouse.getSupplierName():"");
			SysUserInfo sysUserInfo=this.GetOneSessionAccount();
			sysUserInfo.setSuiId(att_MdEnterWarehouse.getSuiId());
			dataMap.put("orgGxId", sysUserInfo.getOrgGxId_Str()!=null?sysUserInfo.getOrgGxId_Str():"");
			if(att_MdEnterWarehouse.getOrderDatetime() != null)
				dataMap.put("orderDatetime", sdf.format(att_MdEnterWarehouse.getOrderDatetime()));
			else
				dataMap.put("orderDatetime", "");
			dataMap.put("consignor", att_MdEnterWarehouse.getConsignor());
			List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
			Double allShu=0d;
			Double allMoney =0d;

			for(MdEnterwarehousemxMaterielEntity obj : mxList){
				Map<String,String> reMap = new HashMap<String,String>();
				//reMap.put("matName", obj.getMatName());
				//重新画表格数据
				//供应商
				reMap.put("supplierName", att_MdEnterWarehouse.getSupplierName()!=null?att_MdEnterWarehouse.getSupplierName():"");
				//商品
				reMap.put("matName", obj.getMatName());
				//型号规格
				reMap.put("mmfName", obj.getNorm());
				//单位
				reMap.put("basicUnit", obj.getBasicUnit());
				//matCode
				reMap.put("matCode",obj.getMatCode()!=null?obj.getMatCode():"");
				//包装方式
				reMap.put("productName",obj.getProductName()!=null?obj.getProductName():"");
				//生产日期
				if (obj.getProductTime()!=null) {
					reMap.put("productTime", sdf.format(obj.getProductTime()));
				}else{
					reMap.put("productTime", "");
				}
				if (obj.getProductValitime()!=null) {
					reMap.put("productValitime", sdf.format(obj.getProductValitime()));
				}else{
					reMap.put("productValitime", "");
				}
				//生产厂家
				reMap.put("productFactory", obj.getProductFactory()!=null?obj.getProductFactory():"");
				//批次号
				reMap.put("itemKeyId", obj.getItemKeyId()!=null?obj.getItemKeyId():"");
				//注册证号
				reMap.put("backPrinting", obj.getBackPrinting()!=null?obj.getBackPrinting():"");
				//单价
				Double price=0d;
				if(obj.getPrice() != null){
					reMap.put("price", df.format(obj.getPrice()));
					price = obj.getPrice();
				}else
					reMap.put("price", "0.00");
				//数量
				reMap.put("matNumber", df2.format(obj.getMatNumber()));
				Double countMoney= obj.getMatNumber()*price;
				//金额
				reMap.put("countMoney", df.format(countMoney));
				reList.add(reMap);
				allShu += obj.getMatNumber();
				allMoney += countMoney;
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
			String ftlStr="";
			if(att_MdEnterWarehouse.getBillType().equals("2"))
				ftlStr="exportEnterInfo.ftl";
			else if(att_MdEnterWarehouse.getBillType().equals("1"))
				//ftlStr="exportWlEnterInfo.ftl";
				ftlStr="exportWlEnterInfoC.ftl";
			Template t = configuration.getTemplate(ftlStr);
			// 输出文档路径及名称
			Calendar now = Calendar.getInstance();
			long lo = now.getTimeInMillis();
			Date date=new Date(lo);
			SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
			String l=sd.format(date);
			String le="WLRK—DG-"+l;
			String tmpPath = SystemContext.updown_File_path+TEMP_EXPORT_PATH;//获得工程运行web的目录
			File file = new File(tmpPath);
			if(!file.exists()) {
				file.mkdirs();
			}
	        String realPath = tmpPath +"WLRKDG"+l+ ".xls";
			File outFile = new File(realPath);
			Writer out = null;
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile),"UTF-8"));
			t.process(dataMap, out);
			out.close();
			String rootUrl = request.getContextPath() + EXPORT_PATH+""+"WLRKDG"+l+ ".xls";
			Map<String,String> map = new HashMap<String,String>();
			map.put("path", rootUrl);
			//map.put("fileName",le+ ".xls");
			map.put("fileName","WLRKDG"+l+ ".xls");
			srm.setObj(map);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage exportList(MdEnterWarehouse att_MdEnterWarehouse,
			MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			  if(account.getOrganizaType().equals("20001")){
				  att_MdEnterWarehouse.setRbaId(account.getOldId());
				  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				  }else
					  att_MdEnterWarehouse.setSuiId(account.getSuiId());
				  att_MdEnterWarehouse.setPurchaseType("1");
			  }else if(account.getOrganizaType().equals("20002")){
				  att_MdEnterWarehouse.setRbsId(account.getOldId());
				  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				  }else
					  att_MdEnterWarehouse.setSuiId(account.getSuiId());
				  att_MdEnterWarehouse.setPurchaseType("2");
			  }else if(account.getOrganizaType().equals("20003")){
				  att_MdEnterWarehouse.setRbbId(account.getOldId());
				  if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
				  }else
					  att_MdEnterWarehouse.setSuiId(account.getSuiId());
				  att_MdEnterWarehouse.setPurchaseType("3");
			  }
			//att_MdEnterWarehouse.setBillType("2");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			DecimalFormat df= new DecimalFormat("######0.00");
			DecimalFormat df2= new DecimalFormat("######0");
			Map<String, Object> dataMap = new HashMap<String,Object>();
			int rowCount=4;
			dataMap.put("orgName", account.getOrgName());
			String timeArea="";
			if(att_MdEnterWarehouse.getReceiptDatetime_start()!=null)
				timeArea += sdf.format(att_MdEnterWarehouse.getReceiptDatetime_start());
			if(att_MdEnterWarehouse.getReceiptDatetime_start()!=null || att_MdEnterWarehouse.getReceiptDatetime_end()!=null)
				timeArea+="-";
			if(att_MdEnterWarehouse.getReceiptDatetime_end()!=null)
				timeArea += sdf.format(att_MdEnterWarehouse.getReceiptDatetime_end());
			dataMap.put("timeArea", timeArea);
			dataMap.put("userName", account.getUserName());

			//制作部门
			account.setSuiId(att_MdEnterWarehouse.getSuiId());
			//制作部门
			dataMap.put("OrgGxIdStr", account.getOrgGxId_Str()!= null?account.getOrgGxId_Str():"");
			List<Map<Object,Object>> list = mdEnterWarehouseMxDao.getMxListByEnter(att_MdEnterWarehouse, att_MdEnterWarehouseMx);
			  List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
			  int index=0;
			  Double allShu=0d;
			  Double allMoney =0d;
			  MdMaterielInfo mm=new MdMaterielInfo();
			  if(list != null && list.size() > 0){
				  for(Map<Object,Object> obj: list){
					  index++;
					  Map<String,String> reMap=new HashMap<String,String>();
					  reMap.put("index", index+"");
					  reMap.put("receiptDatetime", obj.get("receipt_datetime").toString().substring(0, 10));
					  reMap.put("supplierName", obj.get("Supplier_name")!=null?obj.get("Supplier_name").toString():"");
					  reMap.put("matName", obj.get("mat_name").toString());
					  reMap.put("typeName", obj.get("type_name")!=null?obj.get("type_name").toString():"");
					  reMap.put("norm", obj.get("NORM").toString());
					  Double number1=obj.get("number1")!=null?Double.parseDouble(obj.get("number1").toString()):0d;
					  reMap.put("number1", df2.format(number1));
					  reMap.put("matNumber", df2.format(Double.parseDouble(obj.get("mat_number").toString())));
					  reMap.put("basicUnit", obj.get("Basic_unit").toString());
					  reMap.put("consignee", obj.get("consignee")!=null?obj.get("consignee").toString():"");
					  reMap.put("orderDatetime", obj.get("Order_datetime")!=null?obj.get("Order_datetime").toString().substring(0, 10):"");
					  reMap.put("consignor", obj.get("consignor")!=null?obj.get("consignor").toString():"");
					  dataMap.put("supplierName", att_MdEnterWarehouse.getSupplierName()!=null?att_MdEnterWarehouse.getSupplierName():"");
					  //入库单号
					  reMap.put("billcode", obj.get("Billcode")!=null?obj.get("Billcode").toString():"");
					  //单价
					  Double price=obj.get("price")!=null?Double.parseDouble(obj.get("price").toString()):0d;
					  reMap.put("price", df.format(price));
						Double countMoney= Double.parseDouble(obj.get("mat_number").toString())*price;
						//金额
						reMap.put("countMoney", df.format(countMoney));
						//数量
						Double countShu=Double.parseDouble(obj.get("mat_number").toString());

					  //包装方式
					  reMap.put("productName", obj.get("product_name")!=null?obj.get("product_name").toString():"");
					  //生产日期
					  reMap.put("productTime", obj.get("product_time")!=null?obj.get("product_time").toString().substring(0, 10):"");
					  //有效期
					  reMap.put("productValitime", obj.get("product_valitime")!=null?obj.get("product_valitime").toString().substring(0, 10):"");
					  //生产厂家
					  reMap.put("productFactory", obj.get("product_factory")!=null?obj.get("product_factory").toString():"");
					  //批次号
					  reMap.put("batchCertNo", obj.get("batch_certNo")!=null?obj.get("batch_certNo").toString():"");

					  //注册证号
					  reMap.put("backPrinting", mm.getBackPrinting()!=null?mm.getBackPrinting().toString():"");
					  reList.add(reMap);
					 // allShu +=  df2.format(Double.parseDouble(obj.get("mat_number").toString()));
					  allMoney += countMoney;
					  allShu += countShu;
				  }
				  rowCount+=list.size();

			  }
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
				Template t = configuration.getTemplate("exportEnterListC.ftl");
				// 输出文档路径及名称
				Calendar now = Calendar.getInstance();
				long lo = now.getTimeInMillis();
				Date date=new Date(lo);
				SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
				String l=sd.format(date);
				//long l = now.getTimeInMillis();
				String tmpPath = SystemContext.updown_File_path+TEMP_EXPORT_PATH;//获得工程运行web的目录
				File file = new File(tmpPath);
				if(!file.exists()) {
					file.mkdirs();
				}
		        String realPath = tmpPath +"WLRKPL"+l+ ".xls";
				File outFile = new File(realPath);
				Writer out = null;
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(outFile),"UTF-8"));
				t.process(dataMap, out);
				out.close();
				String rootUrl = request.getContextPath() + EXPORT_PATH+"WLRKPL"+l+ ".xls";
				Map<String,String> map = new HashMap<String,String>();
				map.put("path", rootUrl);
				map.put("fileName","WLRKPL"+l+ ".xls");
				srm.setObj(map);

		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	private Double getDiscount(String billCode) throws HSKDBException{
		Double aPrice=0d;
		MdOrderInfo att_MdOrderInfo =new MdOrderInfo();
		att_MdOrderInfo.setOrderCode(billCode);
		List<MdOrderInfo> list = mdOrderInfoDao.getListByMdOrderInfo(att_MdOrderInfo);
		MdOrderInfo mdOrderInfo = list.get(0);
		MdOrderMx mdOrderMx = new MdOrderMx();
		mdOrderMx.setMoiId(mdOrderInfo.getMoiId());
		List<MdOrderMx> mxList = this.getList(mdOrderMx);
		Double countMoney=0d;
		Double countShu=0d;
		for(MdOrderMx mx:mxList){
			countMoney+=mx.getTotalMoney();
			if(mx.getNumber2()!=null && mx.getNumber2()>mx.getMatNumber())
				countShu+=mx.getNumber2();
			else
				countShu+=mx.getMatNumber();
		}
		Double allMoney = mdOrderInfo.getPlaceOrderMoney()-(mdOrderInfo.getMoney2()!=null?mdOrderInfo.getMoney2():0d);
		aPrice =(allMoney-countMoney)/countShu;
		return aPrice;
	}
}