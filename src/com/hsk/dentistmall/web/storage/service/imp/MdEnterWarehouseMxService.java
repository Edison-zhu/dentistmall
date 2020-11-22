package com.hsk.dentistmall.web.storage.service.imp;

import java.text.SimpleDateFormat;
import java.util.*;

import com.google.inject.internal.Objects;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.daobbase.imp.MdInventoryExtendDao;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.xframe.api.daobbase.IWarehousingManagementDao;
import com.hsk.xframe.api.daobbase.IorgDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.web.storage.service.IMdEnterWarehouseMxService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;

import javax.jws.Oneway;


/**
  storage业务操作实现类
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:54
 */

@Service
public class  MdEnterWarehouseMxService extends DSTService implements IMdEnterWarehouseMxService {
   /**
   *业务处理dao类  mdEnterWarehouseMxDao
   */
	@Autowired
	protected IMdEnterWarehouseMxDao mdEnterWarehouseMxDao;
	@Autowired
	private IWarehousingManagementDao warehousingManagementDao;
	@Autowired
	private IMdEnterWarehouseDao mdEnterWarehouseDao;
	@Autowired
	private IMdInventoryDao mdInventoryDao;
	@Autowired
	private IMdOrderMxDao mdOrderMxDao;
	@Autowired
	private IMdMaterielInfoDao mdMaterielInfoDao;
	@Autowired
	private IMdInventoryExtendDao mdInventoryExtendDao;
	@Autowired
	protected IorgDao orgDao;
	@Autowired
	private IMdItemKeyDao mdItemKeyDao;

//	@Autowired
//	private IMdItemKeyDao mdItemKeyDao;


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
     * @param    (md_enter_warehouse
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
     * @param
     * @return MdEnterWarehouseMx  修改后的MdEnterWarehouseMx对象记录
	 * @throws HSKDBException
	 */
	 public SysRetrunMessage saveOrUpdateObject(MdEnterWarehouse att_MdEnterWarehouse, String wewMxIds, Integer moiIds, String mmfIds, String momIds, String shus, String number1s,
												String prices, String productPTimes, String productValiTimes, String packasgs, String factories,
												String certnos, String masIds, String retailPrices, String units, String splitNumbers, String backPrintings,String linkmmfIds,String linkwmsIds, String ratio1s) throws HSKException {
		 SysRetrunMessage srm = new SysRetrunMessage(1l);
		 try {

			 SysUserInfo account = this.GetOneSessionAccount();
			 SysOrgGx sysOrgGx=new SysOrgGx();
			 sysOrgGx.setOrgGxId(account.getOrgGxId());
			 Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			 String orgNameStr = orgDao.getNameStr(sysOrgGx);
			 Integer one=null;
			 Integer two=null;
			 Integer three=null;
			 String purchaseType = "";
			 if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				 att_MdEnterWarehouse.setRbaId(account.getOldId());
				 att_MdEnterWarehouse.setPurchaseType("1");
				 one = account.getOldId();
				 purchaseType = "2";
			 }else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				 if(orgMap.containsKey("one")){//如果存在上级集团
					 att_MdEnterWarehouse.setRbaId(Integer.parseInt(orgMap.get("one")));
					 one = Integer.parseInt(orgMap.get("one"));
				 }
				 att_MdEnterWarehouse.setRbsId(account.getOldId());
				 att_MdEnterWarehouse.setPurchaseType("2");
				 two = account.getOldId();
				 purchaseType = "3";
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
				 purchaseType = "4";
			 }
			 Integer wzId = account.getOldId();

			 // 遍历wewMxIds，找到对应的入库明细，修改明细信息
			 String[] wewMxIdArray = (wewMxIds == null || wewMxIds == "") ? null : wewMxIds.split(",");
			 String[] shuArray = shus.split(",");
//			 String[] mmfIdArray = mmfIds.split(",");
			 String[] number1Array = number1s == null ? null : number1s.split(",");
			 String[] pricesArray = prices == null ? null : prices.split(",");
//			 String[] productPTimeArray = productPTimes == null ? null : productPTimes.split(",");
			 String[] productPVTimeArray = productValiTimes == null ? null : productValiTimes.split(",");
//			 String[] packageArray = packasgs == null ? null : packasgs.split(",");
//			 String[] factoryArray = factories == null ? null : factories.split(",");
			 String[] cernoArray = certnos == null ? null : certnos.split(",");
			 String[] retailArray = retailPrices == null ? null : retailPrices.split(",");
			 String[] unitArray = units == null ? null : units.split(",");
			 String[] splitArray = splitNumbers == null ? null : splitNumbers.split(",");
			 String[] backArray = backPrintings == null ? null : backPrintings.split(",");
			 String[] ratio1Array = ratio1s == null ? null : ratio1s.split(",");

			 String[] linkmmfIdArray=linkmmfIds == null ? null : linkmmfIds.split(",");
			 String[] linkwmsIdIdArray=linkwmsIds == null ? null : linkwmsIds.split(",");
			 //保存入库明细信息和库存信息
			 MdEnterWarehouse  mdEnterWarehouse = mdEnterWarehouseDao.findMdEnterWarehouseById(att_MdEnterWarehouse.getWewId());

			 MdOrderInfo mdOrderInfo = new MdOrderInfo();
			 mdOrderInfo.setOrderCode(mdEnterWarehouse.getRelationBillCode());
			 mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);

			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 MdEnterWarehouseMx mdEnterWarehouseMx;
			 MdInventory mdInventory;
			 MdOrderMx mdOrderMx;
			 Double baseNumber = 0d;
			 Double quantity = 0D;
			 Integer idx = 0;
			 Integer wewMxId;

			 Double oldPurchasePrice = 0d;
			 Double oldRetailPrice = 0d;
			 Double oldExpectNumber = 0d;
			 Double newPurchasePrice = 0d;
			 Double newRetailPrice = 0d;
			 Double newExpectNumber = 0d;

			 Double oldCount = 0d;
			 Double count = 0d;
			 Double ratio1 = 1d;

			 Integer moiId = null;

			 MdMaterielInfo mdMaterielInfo;
			 String billType = att_MdEnterWarehouse.getBillType();

			 List<MdInventoryExtend> list;
			 if (wewMxIdArray != null) {
				 for (String wewIdStr : wewMxIdArray) {
					 Double matNumber = Double.parseDouble(shuArray[idx]);
					 Double number1 = number1Array != null ? Double.parseDouble(number1Array[idx]) : null;
					 Double price = pricesArray != null ? Double.parseDouble(pricesArray[idx]) : null;
					 Date productValiTime = (productPVTimeArray != null && productPVTimeArray.length > idx && !productPVTimeArray[idx].equals("")) ? sdf.parse(productPVTimeArray[idx]) : null;
					 String certno = (cernoArray != null && cernoArray.length > idx) ? cernoArray[idx] : null;
					 Double retialPrice = (retailArray != null && retailArray.length > idx && !retailArray[idx].equals("")) ? Double.parseDouble(retailArray[idx]) : null;
					 String unit = (unitArray != null && unitArray.length > idx) ? unitArray[idx] : null;
					 Double splitNumber = (splitArray != null && splitArray.length > idx && !splitArray[idx].equals("")) ? Double.parseDouble(splitArray[idx]) : null;
					 String backPrinting = (backArray != null && backArray.length > idx) ? backArray[idx] : null;
					Integer mmfId= (linkmmfIdArray != null && linkmmfIdArray.length > idx && !linkmmfIdArray[idx].equals("")) ? Integer.parseInt(linkmmfIdArray[idx]):null;
					Integer wmsMiId=(linkwmsIdIdArray != null && linkwmsIdIdArray.length > idx && !linkwmsIdIdArray[idx].equals("")) ? Integer.parseInt(linkwmsIdIdArray[idx]):null;

					if (!ratio1Array.equals(""))
						ratio1 = Double.parseDouble(ratio1Array[idx]);

					 wewMxId = Integer.parseInt(wewIdStr);
					 mdEnterWarehouseMx = mdEnterWarehouseMxDao.findMdEnterWarehouseMxById(wewMxId);

					 baseNumber = mdEnterWarehouseMx.getMatNumber();
					 quantity = mdEnterWarehouseMx.getSplitQuantity();

					 oldCount += quantity;
					 oldPurchasePrice += (mdEnterWarehouseMx.getPrice() == null ? 0d : mdEnterWarehouseMx.getPrice());
					 oldExpectNumber += (mdEnterWarehouseMx.getMatNumber() == null ? 0d : mdEnterWarehouseMx.getMatNumber());
					 oldRetailPrice += (mdEnterWarehouseMx.getRetailMoney() == null ? 0d : mdEnterWarehouseMx.getRetailMoney());

					 if (matNumber != null) {
					 	count += matNumber;
						 mdEnterWarehouseMx.setMatNumber(matNumber);
					 }
					 if (number1 != null)
						 mdEnterWarehouseMx.setNumber1(number1);
					 if (productValiTime != null)
						 mdEnterWarehouseMx.setProductValitime(productValiTime);
					 if (certno != null)
						 mdEnterWarehouseMx.setBatchCertNo(certno);
					 if (backPrinting != null)
						 mdEnterWarehouseMx.setBackPrinting(backPrinting);
					 if (retialPrice != null)
						 mdEnterWarehouseMx.setRetailMoney(retialPrice);
					 if (unit != null && !unit.equals(""))
						 mdEnterWarehouseMx.setUnit(unit);
					 if (splitNumber != null)
						 mdEnterWarehouseMx.setSplitQuantity(splitNumber);
					 if (billType.equals("2")) {
						 mdEnterWarehouseMx.setLinkWmsMiId(wmsMiId);
						 mdEnterWarehouseMx.setLinkMmfId(mmfId);
					 }

					 this.updateObject(mdEnterWarehouseMx);
					 newPurchasePrice += (mdEnterWarehouseMx.getPrice() == null ? 0d : mdEnterWarehouseMx.getPrice());
					 newExpectNumber += (mdEnterWarehouseMx.getMatNumber() == null ? 0d : mdEnterWarehouseMx.getMatNumber());
					 newRetailPrice += (mdEnterWarehouseMx.getRetailMoney() == null ? 0d : mdEnterWarehouseMx.getRetailMoney());

					 list = mdEnterWarehouseMxDao.getInventoryListByWewMxId(mdEnterWarehouseMx);
					 if (billType.equals("2")) {//如果是订单入库
					 	String mmfIdArray = "";
					 	String wmsMiIdArray = "";
						 //修改订单入库，需要修改原订单数据
						 if (mdEnterWarehouse != null && mdEnterWarehouse.getRelationBillCode() != null && !mdEnterWarehouse.getRelationBillCode().equals("")) {
							 mdOrderMx = warehousingManagementDao.findMdOrderMx(mdEnterWarehouse.getRelationBillCode(), mdEnterWarehouseMx.getMmfId());
							 //修改订单入库数量
							 if (mdOrderMx != null) {
								 moiId = mdOrderMx.getMoiId();
								 mdOrderMx.setEnterNumber((mdOrderMx.getEnterNumber() - baseNumber + matNumber) < 0 ? 0 : (mdOrderMx.getEnterNumber() - baseNumber + matNumber));
								 mdOrderMx.setChangeState(1);
								 mdOrderMxDao.updateMdOrderMxById(mdOrderMx.getMomId(), mdOrderMx);
							 }
						 }

						 // 先将原有的数据删除
						 for (MdInventoryExtend mdInventoryExtend : list) {
							 mdInventory = mdInventoryDao.findMdInventoryById(mdInventoryExtend.getWiId());
							 //修改库存
							 mdInventory.setBaseNumber((mdInventory.getBaseNumber() - quantity) > 0 ? (mdInventory.getBaseNumber() - quantity) : 0); // mdInventory.getQuantity() * mdInventory.getRatio());
							 mdInventory.setQuantity(Math.floor(mdInventory.getBaseNumber() / mdInventory.getRatio()));
//						 mdInventory.setQuantity((mdInventory.getQuantity() - baseNumber + matNumber) > 0 ? (mdInventory.getQuantity() - baseNumber + matNumber) : 0);
							 if (productValiTime != null)
								 mdInventory.setValiedDate(productValiTime);

							 mdMaterielInfo = new MdMaterielInfo();
							 mdMaterielInfo.setWmsMiId(mdInventoryExtend.getWmsMiId());
							 mdMaterielInfo.setPurchaseType(purchaseType);
							 mdMaterielInfo.setWzId(wzId);
							 mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
							 if (mdMaterielInfo != null && Objects.equal(mdMaterielInfo.getWmsMiId(), mdInventoryExtend.getWmsMiId())) {
								 mdMaterielInfo.setValiedDate(productValiTime);
								 this.updateObject(mdMaterielInfo);
							 }
							mdInventoryExtend.setWmsMiId(wmsMiId);
							 mdInventoryExtend.setMmfId(mmfId);
//							 mdInventoryDao.updateMdInventoryById(mdInventory.getWiId(), mdInventory);
							 this.updateObject(mdInventory);

							 // 更新库存明细
							 mdInventoryExtend.setBaseNumber((mdInventoryExtend.getBaseNumber() - quantity + splitNumber) > 0 ? (mdInventoryExtend.getBaseNumber() - quantity + splitNumber) : 0);
							 mdInventoryExtend.setQuantity(Math.floor(mdInventoryExtend.getBaseNumber() / mdInventory.getRatio()));

							 MdItemMxView mdItemMxView = new MdItemMxView();
							 mdItemMxView.setRbaId(one);//从session中获取集团ID
							 mdItemMxView.setRbsId(two);//从session中获取医院ID
							 mdItemMxView.setRbbId(three);//从session中获取门诊ID
							 mdItemMxView.setPurchaseType(att_MdEnterWarehouse.getPurchaseType());//从session中采购单位类型

							 mdItemMxView.setMmfId(mdInventoryExtend.getMmfId());
							 mdItemMxView.setWmsMiId(mdInventoryExtend.getWmsMiId());
							 mdItemMxView.setMatName(mdMaterielInfo.getMatName());
							 mdItemMxView.setMmfName(mdInventoryExtend.getMmfName());
							 mdItemMxView = mdItemKeyDao.getMdItemKeyView(mdItemMxView);
							 if (mdItemMxView == null)
								 mdItemMxView = mdItemKeyDao.newMdItemKey(mdItemMxView, mmfId, wmsMiId);
							 MdInventory att_MdInventory = new MdInventory();
							 att_MdInventory.setRbaId(one);//从session中获取集团ID
							 att_MdInventory.setRbsId(two);//从session中获取医院ID
							 att_MdInventory.setRbbId(three);//从session中获取门诊ID
							 att_MdInventory.setBasicUnit(mdMaterielInfo.getBasicUnit());
							 att_MdInventory.setUnit(mdMaterielInfo.getSplitUnit() == null ? mdMaterielInfo.getBasicUnit() : mdMaterielInfo.getSplitUnit());
							 att_MdInventory.setPurchaseType(att_MdEnterWarehouse.getPurchaseType());//从session中采购单位类型
							 att_MdInventory.setItemKeyId(mdItemMxView.getItemKeyId()+"");
							 att_MdInventory.setState("1");
							 // 修改本仓库关联物品的有效期?
							 att_MdInventory.setValiedDate(productValiTime);
							 att_MdInventory=mdInventoryDao.insertMdInventory(att_MdInventory, mdMaterielInfo.getBasicCoefficent(), matNumber, splitNumber);

							 mdInventoryExtend.setWiId(att_MdInventory.getWiId());
							 mdEnterWarehouseMx.setCurNumber(mdInventory.getBaseNumber());
							 this.updateObject(mdInventoryExtend);
						 }

						 mdOrderInfo.setNumber3((mdOrderInfo.getNumber3()!=null?mdOrderInfo.getNumber3():0)+count);
						 if(mdOrderInfo.getNumber3() >= Double.parseDouble(mdOrderInfo.getCommodityNumber().toString())){
							 mdOrderInfo.setProcessStatus("5");
						 }
						 this.updateObject(mdOrderInfo);
					 } else if (billType.equals("1")) {// 物料入库
						 //修改物料入库，不需要操作
						 for (MdInventoryExtend mdInventoryExtend : list) {
							 mdInventory = mdInventoryDao.findMdInventoryById(mdInventoryExtend.getWiId());
							 //修改库存
							 mdInventory.setBaseNumber((mdInventory.getBaseNumber() - quantity + splitNumber) > 0 ? (mdInventory.getBaseNumber() - quantity + splitNumber) : 0); // mdInventory.getQuantity() * mdInventory.getRatio());
							 mdInventory.setQuantity(Math.floor(mdInventory.getBaseNumber() / mdInventory.getRatio()));
//						 mdInventory.setQuantity((mdInventory.getQuantity() - baseNumber + matNumber) > 0 ? (mdInventory.getQuantity() - baseNumber + matNumber) : 0);
							 if (productValiTime != null)
								 mdInventory.setValiedDate(productValiTime);
							 mdMaterielInfo = new MdMaterielInfo();
							 mdMaterielInfo.setWmsMiId(mdInventoryExtend.getWmsMiId());
							 mdMaterielInfo.setPurchaseType(purchaseType);
							 mdMaterielInfo.setWzId(wzId);
							 mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
							 if (mdMaterielInfo != null && Objects.equal(mdMaterielInfo.getWmsMiId(), mdInventoryExtend.getWmsMiId())) {
								 mdMaterielInfo.setValiedDate(productValiTime);
								 this.updateObject(mdMaterielInfo);
							 }

							 mdInventoryDao.updateMdInventoryById(mdInventory.getWiId(), mdInventory);
							 mdInventoryExtend.setBaseNumber((mdInventoryExtend.getBaseNumber() - quantity + splitNumber) > 0 ? (mdInventoryExtend.getBaseNumber() - quantity + splitNumber) : 0);
							 mdInventoryExtend.setQuantity(Math.floor(mdInventoryExtend.getBaseNumber() / mdInventory.getRatio()));
							 this.updateObject(mdInventoryExtend);
							 this.updateObject(mdInventory);
							 mdEnterWarehouseMx.setCurNumber(mdInventory.getBaseNumber());
						 }
					 }
//					 mdEnterWarehouseMx.setMatNumber(0d);
//					 mdEnterWarehouseMx.setSplitQuantity(0d);
					 mdEnterWarehouseMx.setRatio1(ratio1);
					 mdEnterWarehouseMxDao.saveOrUpdateMdEnterWarehouseMx(mdEnterWarehouseMx);
					 idx++;
				 }
			 }
			 if (moiId != null) {
				 if (mdOrderInfo == null)
					 mdOrderInfo = new MdOrderInfo();
				 mdOrderInfo.setMoiId(moiId);
				 mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);
				 if (mdOrderInfo != null && !Objects.equal(count, 0d)) {
					 count = mdOrderInfo.getNumber3() - oldCount + count;
					 mdOrderInfo.setNumber3(count < 0 ? 0 : count);
					 this.updateObject(mdOrderInfo);
				 }
			 }
			 // 先获取入库表，通过wewMxId获取,入库表唯一，不用遍历，只需要拿一个wewMxId即可
			 //最后重新设置att_MdEnterWarehouse.setExpectNumber(expectNumber);  att_MdEnterWarehouse.setRetailMoney(retailPrice); att_MdEnterWarehouse.setPurchaseMoney(price);
			 if (mdEnterWarehouse != null) {
				 mdEnterWarehouse.setBillcode(att_MdEnterWarehouse.getBillcode());
				 mdEnterWarehouse.setWarehousingRemarks(att_MdEnterWarehouse.getWarehousingRemarks());
				 mdEnterWarehouse.setInvoiceCode(att_MdEnterWarehouse.getInvoiceCode());
				 if (mdEnterWarehouse.getExpectNumber() != null)
					 mdEnterWarehouse.setExpectNumber(mdEnterWarehouse.getExpectNumber() - oldExpectNumber + newExpectNumber);
				 if (mdEnterWarehouse.getRetailMoney() != null)
					 mdEnterWarehouse.setRetailMoney(mdEnterWarehouse.getRetailMoney() - oldRetailPrice + newRetailPrice);
				 if (mdEnterWarehouse.getPurchaseMoney() != null)
					 mdEnterWarehouse.setPurchaseMoney(mdEnterWarehouse.getPurchaseMoney() - oldPurchasePrice + newPurchasePrice);
				 this.updateObject(mdEnterWarehouse);
			 }


//			 mdEnterWarehouseMxDao.saveOrUpdateMdEnterWarehouseMx(att_MdEnterWarehouseMx);
//			 srm.setObj(att_MdEnterWarehouseMx);
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
			MdEnterWarehouse mdEnterWarehouse = mdEnterWarehouseDao.findMdEnterWarehouseById(wewId);
			MdEnterWarehouseMx att_MdEnterWarehouseMx = new MdEnterWarehouseMx();
			att_MdEnterWarehouseMx.setWewId(wewId);
			pm = mdEnterWarehouseMxDao.getMdEnterwarehousemxMaterielEntityByMdEnterWarehouseMx(att_MdEnterWarehouseMx);
//			List<MdEnterwarehousemxMaterielEntity> list = mdEnterWarehouseMxDao.getListByMdEnterWarehouseMx(att_MdEnterWarehouseMx);
			List<MdEnterwarehousemxMaterielEntity> list = pm.getItems();
			MdOrderMx mdOrderMx;
			for (MdEnterwarehousemxMaterielEntity mdEnterwarehousemxMaterielEntity : list) {
				if (mdEnterWarehouse.getRelationBillCode() != null && !mdEnterWarehouse.getRelationBillCode().equals("")) {
					mdOrderMx = warehousingManagementDao.findMdOrderMx(mdEnterWarehouse.getRelationBillCode(), mdEnterwarehousemxMaterielEntity.getMmfId());
					if (mdOrderMx == null)
						continue;
					mdEnterwarehousemxMaterielEntity.setOrderNumber(mdOrderMx.getMatNumber());
					mdEnterwarehousemxMaterielEntity.setSendNumber(mdOrderMx.getNumber2());
					mdEnterwarehousemxMaterielEntity.setMomId(mdOrderMx.getMomId());
				}
			}
//
//
//			pm.setItems(list);
//			pm.setRows(list);
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

	@Override
	public PagerModel getMdEnterWarehouseMxListByWewId(Integer wewId, String str, Integer wowId, Integer wiId) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdEnterWarehouseMx>());
		try{
			String searchName1 = "";
			String searchName2 = "";
			if (str != null) {
				String[] arary = str.split(",");
				searchName1 = arary[0];
				if (arary.length > 1)
					searchName2 = arary[1];
			}
			MdEnterWarehouse mdEnterWarehouse = new MdEnterWarehouse();
			mdEnterWarehouse.setWewId(wewId);
			mdEnterWarehouse = (MdEnterWarehouse) this.getOne(mdEnterWarehouse);

			MdEnterWarehouseMx mdEnterWarehouseMx = new MdEnterWarehouseMx();
			mdEnterWarehouseMx.setWewId(wewId);
			if (!searchName1.equals(""))
				mdEnterWarehouseMx.setSearchMatName(searchName1);
			if (!searchName2.equals(""))
				mdEnterWarehouseMx.setSearchMmfName(searchName2);
			List<MdEnterWarehouseMx> list = this.getList(mdEnterWarehouseMx);

//			MdOrderMx att_MdOrderMx = new MdOrderMx();
//			att_MdOrderMx.setMoiId(moiId);
//			if (!searchName1.equals(""))
//				att_MdOrderMx.setSearchMatName(searchName1);
//			if (!searchName2.equals(""))
//				att_MdOrderMx.setSearchMmfName(searchName2);
//			List<MdOrderMx> list = mdOrderMxDao.getListByMdOrderMx(att_MdOrderMx);

			MdOutWarehouseMx mdOutWarehouseMx = new MdOutWarehouseMx();
			List<MdOutWarehouseMx> mxList = null;
			List<MdEnterWarehouseMx> newlist = null;
			if (wowId != null) {
				mdOutWarehouseMx.setWowId(wowId);
				mxList = this.getList(mdOutWarehouseMx);
				newlist = new ArrayList<>();
			}
			List<MdItemMxView> itemMxList;
			MdItemMxView mdItemMxView;
			boolean hh;
			SysUserInfo account = this.GetOneSessionAccount();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Map<String, Object> otherMap = new HashMap<>();
			if(list != null && list.size() > 0){
				for(MdEnterWarehouseMx mx: list){
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
					if (wiId != null)
						att_MdInventoryView.setWiId(wiId);
					att_MdInventoryView = mdInventoryDao.findMdInventoryViewByMdInventoryView(att_MdInventoryView);
//					if (mx.getWiId() != null) {

//					otherMap = mdInventoryDao.getOhterInfo(att_MdInventoryView.getWiId());
//					mx.setBackPrinting(otherMap.get("backPrinting").toString());
//					mx.setBatchCode(otherMap.get("batchCode").toString());
//					mx.setValiedDate(otherMap.get("valiedDate").toString());
					if (mx.getProductValitime() != null)
						mx.setValiedDate(sdf.format(mx.getProductValitime()));
					if (mxList != null) {
						for (MdOutWarehouseMx md : mxList) {

//							mdItemMxView = new MdItemMxView();
//							mdItemMxView.setWiId(att_MdInventoryView.getWiId());
//							mdItemMxView.setMmfId(md.getMmfId());
//							mdItemMxView.setItemKeyId(Integer.parseInt(att_MdInventoryView.getItemKeyId()));
//							itemMxList = mdInventoryDao.getMdItemMxView(mdItemMxView);
//							for (MdItemMxView mdItemMxView1 : itemMxList) {
//								if (Objects.equal(mdItemMxView1.getMmfId(), md.getMmfId())) {
//									hh = true;
//									break;
//								}
//							}
							if (Objects.equal(md.getWewId(), mx.getWewId()) && Objects.equal(att_MdInventoryView.getMmfId2(), md.getMmfId())) {
								Integer index = newlist.indexOf(mx);
								if (index >= 0) {
									MdEnterWarehouseMx mdEnterWarehouseMx1 = newlist.get(index);
									mdEnterWarehouseMx1.setBaseNumber(mdEnterWarehouseMx1.getBaseNumber1() + md.getBaseNumber());
									mdEnterWarehouseMx1.setSplitNumber1(mdEnterWarehouseMx1.getSplitNumber1() + md.getSplitQuantity());
									mdEnterWarehouseMx1.setRatio(att_MdInventoryView.getRatio());
									mdEnterWarehouseMx1.setAvgPrice(att_MdInventoryView.getAvgPrice());
									mdEnterWarehouseMx1.setAvgRetailPrice(att_MdInventoryView.getAvgRetailPrice());
									mdEnterWarehouseMx1.setAllPrice(att_MdInventoryView.getAllPrice());
									mdEnterWarehouseMx1.setAllRetailPrice(att_MdInventoryView.getAvgRetailPrice());
									mdEnterWarehouseMx1.setProductName(att_MdInventoryView.getProductName());
									continue;
								}
								if (att_MdInventoryView != null && att_MdInventoryView.getWiId() != null) {
									mx.setWiId(att_MdInventoryView.getWiId());
									mx.setMieId(att_MdInventoryView.getMieId());
									mx.setAvgPrice(att_MdInventoryView.getAvgPrice());
									mx.setAvgRetailPrice(att_MdInventoryView.getAvgRetailPrice());
									mx.setAllPrice(att_MdInventoryView.getAllPrice());
									mx.setAllRetailPrice(att_MdInventoryView.getAvgRetailPrice());
									mx.setProductName(att_MdInventoryView.getProductName());
									mx.setBaseNumber(md.getBaseNumber());
									mx.setSplitNumber1(md.getSplitQuantity());
									mx.setRatio(att_MdInventoryView.getRatio());
									MdInventoryExtend mdInventoryExtend = mdInventoryExtendDao.getMdInventoryExtendByWiId(att_MdInventoryView.getWiId(), null);
									if (mdInventoryExtend == null) {
										mx.setQuantity(0d);
										mx.setSplitQuantity1(0d);
										mx.setRatio(att_MdInventoryView.getRatio());
										newlist.add(mx);
										continue;
									}
									mx.setMieId(mdInventoryExtend.getMieId());
									mx.setQuantity(att_MdInventoryView.getQuantity());
									mx.setSplitQuantity1(att_MdInventoryView.getBaseNumber());
									mx.setRatio(att_MdInventoryView.getRatio());
								} else {
									mx.setQuantity(0d);
									mx.setSplitQuantity1(0d);
									mx.setRatio(0D);
								}

								mx.setWowMxId(md.getWowMxId());
								mx.setBaseNumber(md.getBaseNumber());
								mx.setSplitNumber(md.getSplitNumber());
								if (att_MdInventoryView != null) {
									mx.setRatio(att_MdInventoryView.getRatio());
									mx.setProductName(att_MdInventoryView.getProductName());
									mx.setAvgPrice(att_MdInventoryView.getAvgPrice());
									mx.setAvgRetailPrice(att_MdInventoryView.getAvgRetailPrice());
									mx.setAllPrice(att_MdInventoryView.getAllPrice());
									mx.setAllRetailPrice(att_MdInventoryView.getAvgRetailPrice());
								}
								newlist.add(mx);
							}
							hh = false;
						}
					} else {
//					}
						if (att_MdInventoryView != null && att_MdInventoryView.getWiId() != null) {
							mx.setWiId(att_MdInventoryView.getWiId());
							mx.setMieId(att_MdInventoryView.getMieId());
							mx.setAvgPrice(att_MdInventoryView.getAvgPrice());
							mx.setAvgRetailPrice(att_MdInventoryView.getAvgRetailPrice());
							mx.setAllPrice(att_MdInventoryView.getAllPrice());
							mx.setAllRetailPrice(att_MdInventoryView.getAvgRetailPrice());
							mx.setRatio(att_MdInventoryView.getRatio());
							mx.setProductName(att_MdInventoryView.getProductName());
							MdInventoryExtend mdInventoryExtend = mdInventoryExtendDao.getMdInventoryExtendByWiId(att_MdInventoryView.getWiId(), null);
							if (mdInventoryExtend == null) {
								mx.setQuantity(0d);
								mx.setSplitQuantity1(0d);
								mx.setRatio(att_MdInventoryView.getRatio());
								continue;
							}

							mx.setMieId(mdInventoryExtend.getMieId());
							mx.setQuantity(att_MdInventoryView.getQuantity());
							mx.setSplitQuantity1(att_MdInventoryView.getBaseNumber());
							mx.setRatio(att_MdInventoryView.getRatio());
						} else {
							mx.setQuantity(0d);
							mx.setSplitQuantity1(0d);
							mx.setRatio(0D);
						}
					}
				}
			}
			if (newlist != null) {
				pm.setItems(newlist);
				pm.setRows(newlist);
			} else {
				pm.setItems(list);
				pm.setRows(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
}