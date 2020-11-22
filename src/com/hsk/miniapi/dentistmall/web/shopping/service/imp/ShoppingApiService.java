package com.hsk.miniapi.dentistmall.web.shopping.service.imp;

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
import com.hsk.dentistmall.api.daobbase.IMdCartsDao;
import com.hsk.miniapi.SessionUtil;
import com.hsk.miniapi.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.miniapi.xframe.api.daobbase.ISysUserInfoApiDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysWebsiteHotSearchApiDao;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.persistence.SysSwitchEntity;
import org.apache.derby.tools.sysinfo;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.openjpa.kernel.FillStrategy;
import org.jbpm.pvm.internal.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.miniapi.dentistmall.web.shopping.service.IShoppingApiService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.IorgApiDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class ShoppingApiService extends DSTApiService implements IShoppingApiService {

	@Autowired
	protected IMdMaterielInfoApiDao mdMaterielInfoDao;
	@Autowired
	protected IMdMaterielFormatApiDao mdMaterielFormatDao;
	@Autowired
	protected IMdSupplierApiDao mdSupplierDao;
	@Autowired
	protected IMdMaterielTypeApiDao mdMaterielTypeDao;
	@Autowired
	protected IMdOrderInfoApiDao mdOrderInfoDao;
	@Autowired
	protected IMdOrderMxApiDao mdOrderMxDao;
	@Autowired
	protected IMdNewsInfoApiDao mdNewsInfoDao;
	@Autowired
	protected IorgApiDao orgDao;
	@Autowired
	protected ISysWebsiteHotSearchApiDao sysWebsiteHotSearchDao;
	@Autowired
	protected IMdCartsApiDao mdCartsDao;
	@Autowired
	protected IMdOrderAfterSaleApiDao mdOrderAfterSaleDao;
	@Autowired
	private ISysUserInfoApiDao sysUserInfoDao;

	@Override
	public PagerModel getSupplierPager(String searchName) throws HSKException {
		PagerModel model = new PagerModel(new ArrayList<Map<String,Object>>());
		List<Map<String,Object>> reList = new ArrayList<Map<String,Object>>();
		PagerModel mdSupplierPager = new PagerModel(new ArrayList<MdSupplier>());
		try {
			mdSupplierPager = mdSupplierDao.getPagerModelByMdSupplierSearch(searchName,"1");
			if(mdSupplierPager != null && mdSupplierPager.getRows()!= null && mdSupplierPager.getRows().size() > 0){
				String wzIds="";
				for(int i =0 ;i < mdSupplierPager.getRows().size();i++){
					MdSupplier supplier = (MdSupplier) mdSupplierPager.getRows().get(i);
					wzIds += supplier.getWzId()+",";
				}
				wzIds = wzIds.substring(0, wzIds.length()-1);
				MdMaterielInfo  att_MdMaterielInfo=new MdMaterielInfo();
				att_MdMaterielInfo.setPurchaseType("1");
				att_MdMaterielInfo.setWzId_str(wzIds);
				att_MdMaterielInfo.setState("1");
				att_MdMaterielInfo.setTab_order("wzId,serialNumber,wmsMiId desc");
				List<MdMaterielInfo> matList = mdMaterielInfoDao.getListByMdMaterielInfo(att_MdMaterielInfo);
				for(int i =0 ;i < mdSupplierPager.getRows().size();i++){
					Map<String,Object> reMap = new HashMap<String,Object>();
					MdSupplier supplier = (MdSupplier) mdSupplierPager.getRows().get(i);
					reMap.put("wzId", supplier.getWzId());
					reMap.put("applicantName", supplier.getApplicantName());
					reMap.put("logoFilePath", supplier.getLogoFilePath());
					reMap.put("corporateDomicile", supplier.getCorporateDomicile());
					reMap.put("state", supplier.getState());
					reMap.put("describe", supplier.getScopeBusiness());
					List<MdMaterielInfo> infoList = new ArrayList<MdMaterielInfo>();

					if(matList != null && matList.size() > 0){
						Integer index=1;
						for(MdMaterielInfo obj : matList){
							if(obj.getWzId().equals(supplier.getWzId())){
								infoList.add(obj);
								index++;
								if(index >3)
									break;
							}
						}
					}
					reMap.put("matList", infoList);
					reList.add(reMap);
				}
			}
			model.setTotal(mdSupplierPager.getTotal());
			model.setTotalCount(mdSupplierPager.getTotalCount());
			model.setFooter(mdSupplierPager.getFooter());
			model.setItems(reList);
			model.setRows(reList);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public SysRetrunMessage getSuppliers(String searchName) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try {
			PagerModel mdSupplierPager;
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			SysSwitchEntity sysSwitchEntity = this.getApiSessionSwitch(1);
			if (sysUserInfo == null && sysSwitchEntity != null && java.util.Objects.equals(sysSwitchEntity.getState(), 1)) {
				mdSupplierPager = mdSupplierDao.getPagerModelByMdSupplierSearchNoLogin(searchName, "1");
			} else {
				mdSupplierPager = mdSupplierDao.getPagerModelByMdSupplierSearch(searchName, "1");
			}
			sm.setObj(mdSupplierPager.getItems());
		} catch (HSKDBException e) {
			sm.setCode(0l);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getBrands(String searchName) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			PagerModel brands;
			SysSwitchEntity sysSwitchEntity = this.getApiSessionSwitch(1);
			if (sysUserInfo == null && sysSwitchEntity != null && java.util.Objects.equals(sysSwitchEntity.getState(), 1)) {
				brands = mdMaterielInfoDao.getPagerModelByBrandSearchNoLogin(searchName, "1");
			} else {
				brands = mdMaterielInfoDao.getPagerModelByBrandSearch(searchName, "1");
			}
			sm.setObj(brands.getItems());
		} catch (HSKDBException e) {
			sm.setCode(0l);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getSuppliersMat(Integer wzId, Integer limit, Integer page) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try {
			MdMaterielInfo att_MdMaterielInfo = new MdMaterielInfo();
			att_MdMaterielInfo.setWzId(wzId);
			List<MdMaterielInfo> matList = mdMaterielInfoDao.getListByMdMaterielInfoLimit(att_MdMaterielInfo, limit, page);
			sm.setObj(matList);
		} catch (HSKDBException e) {
			sm.setCode(0l);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getBrandsMat(String brandName, Integer limit, Integer page) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try {
			MdMaterielInfo att_MdMaterielInfo = new MdMaterielInfo();
			att_MdMaterielInfo.setBrand(brandName);
			List<MdMaterielInfo> matList = mdMaterielInfoDao.getListByMdMaterielInfoLimit(att_MdMaterielInfo, limit, page);
			sm.setObj(matList);
		} catch (HSKDBException e) {
			sm.setCode(0l);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getHotMateriel(Integer tab, Integer limit, Integer page) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try {
			List<MdMaterielInfo> list;
			StringBuilder excludIds = new StringBuilder();
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			if (tab != null && tab == 3) { //购物车需要排除已加入购物车的物品
				if (sysUserInfo != null) {
					List<MdCarts> mdCarts = mdCartsDao.findMdCartsAll(sysUserInfo.getSuiId());
					for (MdCarts mdCarts1 : mdCarts) {
						excludIds.append(mdCarts1.getMmfId() + ",");
					}
 				}
			}
			List<Map<String, Object>> pm;
			SysSwitchEntity sysSwitchEntity = this.getApiSessionSwitch(1);
			if (sysUserInfo == null && sysSwitchEntity != null && java.util.Objects.equals(sysSwitchEntity.getState(), 1)) {
				pm = mdMaterielInfoDao.getHotMaterielInfoDetailNoLogin(page, limit, excludIds.toString());
			} else {
				pm = mdMaterielInfoDao.getHotMaterielInfoDetail(page, limit, excludIds.toString());
			}
			if (pm.isEmpty()) {
				sm.setCode(0L);
				return sm;
			}
			sm.setObj(pm);
		} catch (HSKDBException e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
		return sm;
	}

	@Override
	public PagerModel getMatInfoPager(String searchName, Integer mmtId,Integer wzId,String brand, Integer isDefault, Integer priceOrder,Integer numOrder) throws HSKException {
		PagerModel model = new PagerModel(new ArrayList<MdMaterielInfo>());
		String orderStr = "";
		if(isDefault !=null && isDefault==1){//默认
			orderStr+="wmsMiId desc";
		}else if(priceOrder !=null && priceOrder==1){
			orderStr += "money1,wmsMiId desc";
		}else if(priceOrder !=null && priceOrder==2){
			orderStr += "money1 desc,wmsMiId desc";
		}else if(numOrder !=null && numOrder==1){
			orderStr += "number1,wmsMiId desc";
		}else if(numOrder !=null && numOrder==2){
			orderStr += "number1 desc,wmsMiId desc";
		}else
			orderStr+="number1 desc, money1 desc";
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			SysSwitchEntity sysSwitchEntity = this.getApiSessionSwitch(1);
			if (sysUserInfo == null && sysSwitchEntity != null && java.util.Objects.equals(sysSwitchEntity.getState(), 1)) {
				model = mdMaterielInfoDao.getPagerModelBySearchNameNoLogin(searchName, mmtId, wzId, brand, "1", "1", "1", orderStr);
				if (searchName != null && model.getItems().size() > 0 && !searchName.equals("")) {
					sysWebsiteHotSearchDao.saveSysWebsiteHotSearchNoLogin(searchName);
				}
			} else {
				model = mdMaterielInfoDao.getPagerModelBySearchName(searchName, mmtId, wzId, brand, "1", "1", "1", orderStr);
				if (searchName != null && model.getItems().size() > 0 && !searchName.equals("")) {
					sysWebsiteHotSearchDao.saveSysWebsiteHotSearch(searchName);
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();

		}
		return model;
	}

	@Override
	public PagerModel getMatInfoPagerSearch(String searchName, Integer mmtId, Integer wzId, String brand, Integer isDefault, Integer priceOrder, Integer numOrder) throws HSKException {
		PagerModel model = new PagerModel(new ArrayList<MdMaterielInfo>());
		String orderStr = "";
		orderStr += "wmsMiId desc";

		try {
			model = mdMaterielInfoDao.getPagerModelByInTimeSearchName(searchName, orderStr, "1");
			if (searchName != null && model.getItems().size() > 0 &&  !searchName.equals("")){
				sysWebsiteHotSearchDao.saveSysWebsiteHotSearch(searchName);
			}
		} catch (HSKDBException e) {
			e.printStackTrace();

		}
		return model;
	}

	@Override
	public SysRetrunMessage getMdMaterielTypeByWzId(Integer wzId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
			mdMaterielInfo.setWzId(wzId);
			mdMaterielInfo.setState("1");
			List<MdMaterielInfo> matList = mdMaterielInfoDao.getListByMdMaterielInfo(mdMaterielInfo);
			List<Map<String,Object>> reList = new ArrayList<Map<String,Object>>();
			if(matList != null && matList.size() > 0){
				String mmtIds = ",";
				for(MdMaterielInfo mat : matList){
					if(mat.getMdWmsMiId() != null){
						if(!mmtIds.contains(","+mat.getMatType1()+","))
							mmtIds += mat.getMatType1()+",";
						if(!mmtIds.contains(","+mat.getMatType2()+","))
							mmtIds += mat.getMatType2()+",";
						if(!mmtIds.contains(","+mat.getMdWmsMiId()+","))
							mmtIds += mat.getMdWmsMiId()+",";
					}
				}
				if(mmtIds != null && !mmtIds.equals(",")){
					mmtIds = mmtIds.substring(1, mmtIds.length()-1);
					MdMaterielType  att_MdMaterielType=new MdMaterielType();
					att_MdMaterielType.setMmtIds(mmtIds);
					att_MdMaterielType.setState("1");
					List<MdMaterielType> mdMaterielTypeList = mdMaterielTypeDao.getListByMdMaterielType(att_MdMaterielType);
					if(mdMaterielTypeList != null && mdMaterielTypeList.size() > 0)
						reList = this.initJsonData(mdMaterielTypeList, null);
				}
			}
			srm.setObj(reList);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return srm;
	}

	private List<Map<String,Object>> initJsonData(List<MdMaterielType> mdMaterielTypeList,Integer pId){
		List<Map<String,Object>> reList = new ArrayList<Map<String,Object>>();
		if(pId==null){
			for(MdMaterielType mdMaterielType : mdMaterielTypeList){
				if(mdMaterielType.getMdMmtId() == null){
					Map<String,Object> reMap = new HashMap<String,Object>();
					reMap.put("id", mdMaterielType.getMmtId());
					reMap.put("name", mdMaterielType.getMmtName());
					List<Map<String,Object>> childList = initJsonData(mdMaterielTypeList,mdMaterielType.getMmtId());
					if(childList != null && childList.size() > 0)
						reMap.put("childList", childList);
					reList.add(reMap);
				}
			}
		}else{
			for(MdMaterielType mdMaterielType : mdMaterielTypeList){
				if(mdMaterielType.getMdMmtId() !=null && mdMaterielType.getMdMmtId().equals(pId)){
					Map<String,Object> reMap = new HashMap<String,Object>();
					reMap.put("id", mdMaterielType.getMmtId());
					reMap.put("name", mdMaterielType.getMmtName());
					reMap.put("pId", mdMaterielType.getMdMmtId());
					List<Map<String,Object>> childList = initJsonData(mdMaterielTypeList,mdMaterielType.getMmtId());
					if(childList != null && childList.size() > 0)
						reMap.put("childList", childList);
					reList.add(reMap);
				}
			}
		}
		return reList;
	}

	@Override
	public SysRetrunMessage getCarMatInfo(String mmfIds, String shus) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			List<Map<String,Object>> reList = this.initMatInfoList(mmfIds, shus);
			srm.setObj(reList);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}

		return srm;
	}

	@Override
	public SysRetrunMessage saveOrder(Integer payType, String mmfIds, String shus, String wzIds,String contents,String expressMoneys,String needBill, String billType, String billHeard,String billCode, Integer mdaId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			String[] shuArray = shus.split(",");
			Integer shuCount = 0;
			for (String shu : shuArray){
				if(shu.equals("0")){
					shuCount ++;
				}
			}
			if(shuCount == shuArray.length){
				srm.setCode(2l);
				srm.setMeg("购买数量不能全为0！");
				return srm;
			}
			SysUserInfo account = this.getApiSessionAccount();
			//查看当前组织是否存在集团、医院
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			String orgNameStr = orgDao.getNameStr(sysOrgGx);
			List<Map<String,Object>> allList = this.initMatInfoList(mmfIds, shus);
			//获取送货地址信息
			MdDeliveryAddress mdDeliveryAddress = new MdDeliveryAddress();
			mdDeliveryAddress.setMdaId(mdaId);
			mdDeliveryAddress = (MdDeliveryAddress) this.getOne(mdDeliveryAddress);
			//组合商家留言信息
			Map<String,String> contentMap = new HashMap<String,String>();
			Map<String,String> expressMap = new HashMap<String,String>();
			if(wzIds != null && !wzIds.equals("")){
				String[] wzIdArray = wzIds.split(",");
				String[] contentArray = contents.split(",");
				String[] expressArray = expressMoneys.split(",");
				for(int i=0 ;i < wzIdArray.length;i++){
					contentMap.put(wzIdArray[i], contentArray[i]);
					expressMap.put(wzIdArray[i], expressArray[i]);
				}
			}
			String moiIds = "";
			for(Map<String,Object> map : allList){
				MdSupplier supplier =(MdSupplier) map.get("mdSupplier");
				List<MdMaterielFormatView> viewList = (List<MdMaterielFormatView>) map.get("matList");
				Integer allShu=Integer.parseInt(map.get("allShu")+"");
				Double allMoney = Double.parseDouble(map.get("allMoney")+"");
				//创建订单信息
				MdOrderInfo mdOrderInfo = this.initMdOrderInfo(supplier, account, orgMap,orgNameStr, mdDeliveryAddress, allShu, allMoney,
						Double.parseDouble(expressMap.get(supplier.getWzId()+"")),
						contentMap.get(supplier.getWzId()+""),needBill, billType, billHeard, billCode, payType);
				mdOrderInfo.setCreateDate(new Date());
				mdOrderInfo.setCreateRen(account.getUserName());
				mdOrderInfo.setEditDate(new Date());
				mdOrderInfo.setEditRen(account.getUserName());
				mdOrderInfoDao.saveMdOrderInfo(mdOrderInfo);
				for(MdMaterielFormatView view : viewList){
					//创建订单明细信息
					MdOrderMx mdOrderMx = this.initMdOrderMx(view);
					mdOrderMx.setPurchaseUnit(orgNameStr);
					mdOrderMx.setMoiId(mdOrderInfo.getMoiId());
					mdOrderMxDao.saveMdOrderMx(mdOrderMx);
					//修改商品销售量信息
					MdMaterielInfo mdMaterielInfo= new MdMaterielInfo();
					mdMaterielInfo.setWmsMiId(view.getWmsMiId());
					mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
					mdMaterielInfo.setNumber1((mdMaterielInfo.getNumber1()!=null?mdMaterielInfo.getNumber1():0d)+Double.parseDouble(view.getCountShu()));
					mdMaterielInfo.setEditDate(new Date());
					mdMaterielInfo.setEditRen(account.getUserName());
					mdMaterielInfoDao.updateMdMaterielInfoById(mdMaterielInfo.getWmsMiId(), mdMaterielInfo);
				}
				//消息提醒,提醒商家有新的订单
				MdNewsInfo mdNewsInfo = new MdNewsInfo();
				mdNewsInfo.setOrderId(mdOrderInfo.getMoiId());
				mdNewsInfo.setUiId(mdOrderInfo.getWzId());
				mdNewsInfo.setUiType(1);
				mdNewsInfo.setNewsType(1);//新的订单
				mdNewsInfo.setIsView(1);
				mdNewsInfo.setCreateDate(new Date());
				mdNewsInfo.setContent("您有新的订单 "+mdOrderInfo.getOrderCode()+" 请查看！");
				mdNewsInfoDao.saveMdNewsInfo(mdNewsInfo);
				moiIds += mdOrderInfo.getMoiId() + ",";
			}
			if (moiIds != ""){
				moiIds = moiIds.substring(0, moiIds.length() - 1);
				srm.setObj(moiIds);
			}

		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
			throw new  HSKException(e);
		}
		return srm;
	}

	@Override
	public SysRetrunMessage savePayOrder(String moiIds, Integer payType, String money) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try {
			boolean need = this.checkNeedSecurity();
			if (need) {
				sm.setCode(4L);
				sm.setMeg("需要安全码");
				return sm;
			}
			String[] moneys = money.split(",");
			String[] moiis = moiIds.split(",");
			List<MdOrderInfo> mdOrderInfos = mdOrderInfoDao.findMdOrderInfoByIds(moiIds);
			int index = 0;
			for (String moiId : moiis) {
				MdOrderInfo mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(Integer.valueOf(moiId));

				if (moneys[index] != null && !moneys[index].equals("")) {
					Double m = mdOrderInfo.getPlaceOrderMoney() == null ? 0d : mdOrderInfo.getPlaceOrderMoney();
					Double mm = Double.parseDouble(moneys[index]);
					if (!mm.equals(m)) {
						sm.setCode(3l);
						sm.setMeg("订单金额发生改变，请刷新页面查看");
						return sm;
					}
				}

				mdOrderInfo.setProcessStatus("1");
				mdOrderInfo.setPayType(payType);
				mdOrderInfo = mdOrderInfoDao.updateMdOrderInfoById(mdOrderInfo.getMoiId(), mdOrderInfo);
				if (mdOrderInfo == null) {
					sm.setCode(0l);
				}
				index ++;
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
			sm.setCode(0l);
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getOrderByMoiId(String moiIds) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try {
			List<MdOrderInfo> mdOrderInfos = mdOrderInfoDao.findMdOrderInfoByIds(moiIds);
			if(mdOrderInfos == null){
				sm.setCode(0l);
				return sm;
			}
			Map<String, Object> map;
			String orders = "";
			Double allMoney = 0d;
			String payType = "月结";
			Double totalMoney = 0d;
			Double money2 = 0d;
			Double money3 = 0d;
			List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
			for (MdOrderInfo mdOrderInfo : mdOrderInfos) {
				map = new HashMap<String, Object>();
				Integer moiId = mdOrderInfo.getMoiId();
				List<Map<String, Object>> totalMoney_Str = mdOrderInfoDao.findMdOrderInfoTotalMoneyById(moiId);
				if (totalMoney_Str.size() > 0) {
					totalMoney = Double.parseDouble(totalMoney_Str.get(0).get("total_money").toString());
				}
				map.put("orderCode", mdOrderInfo.getOrderCode());
				map.put("orderMoney", mdOrderInfo.getPlaceOrderMoney());
				map.put("payTypeName", mdOrderInfo.getPayTypeName());
				map.put("totalMoney", totalMoney);
				map.put("money2", mdOrderInfo.getMoney2() == null ? 0d : mdOrderInfo.getMoney2());
				map.put("money3", mdOrderInfo.getMoney2() == null ? 0d : mdOrderInfo.getMoney2());
				listmap.add(map);
			}
			sm.setObj(listmap);
		} catch (HSKDBException e) {
			e.printStackTrace();
			sm.setCode(0l);
		}
		return sm;
	}

	//组装订单详细信息
	private MdOrderMx initMdOrderMx(MdMaterielFormatView view){
		MdOrderMx mdOrderMx = new MdOrderMx();
		mdOrderMx.setWmsMiId(view.getWmsMiId());
		mdOrderMx.setMmfId(view.getMmfId());
		mdOrderMx.setPlaceOrderTime(new Date());
		mdOrderMx.setUnitMoney(view.getPrice());
		mdOrderMx.setMoney2(view.getPrice());
		mdOrderMx.setTotalMoney(Double.parseDouble(view.getCountMoney()));
		mdOrderMx.setMatNumber(Double.parseDouble(view.getCountShu()));
		mdOrderMx.setPurchaseUnit(view.getApplicantName());
		mdOrderMx.setMatCode(view.getMatCode());
		mdOrderMx.setMatName(view.getMatName());
		mdOrderMx.setBasicUnit(view.getBasicUnit());
		mdOrderMx.setNorm(view.getMmfName());
		mdOrderMx.setMatType(view.getMatType());
		mdOrderMx.setMatType1(view.getMatType1());
		mdOrderMx.setMatType2(view.getMatType2());
		mdOrderMx.setMatType3(view.getMatType3());
		mdOrderMx.setEnterNumber(0d);
		mdOrderMx.setShureNumber(0d);
		mdOrderMx.setBackNumber(0d);
		return mdOrderMx;
	}
	//组装订单信息
	private MdOrderInfo initMdOrderInfo(MdSupplier supplier,SysUserInfo account,Map<String,String> orgMap,String orgNameStr,MdDeliveryAddress mdDeliveryAddress,
			Integer allShu,Double allMoney,Double expressMoney,String content,String needBill,String billType, String billHeard,String billCode, Integer payType){
		//创建订单信息
		MdOrderInfo mdOrderInfo = new MdOrderInfo();
		mdOrderInfo.setWzId(supplier.getWzId());//设置供应商
		if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
			mdOrderInfo.setRbaId(account.getOldId());
			mdOrderInfo.setPurchaseType("1");
		}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
			if(orgMap.containsKey("one"))//如果存在上级集团
				mdOrderInfo.setRbaId(Integer.parseInt(orgMap.get("one")));
			mdOrderInfo.setRbsId(account.getOldId());
			mdOrderInfo.setPurchaseType("2");
		}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
			if(orgMap.containsKey("one"))//如果存在上级集团
				mdOrderInfo.setRbaId(Integer.parseInt(orgMap.get("one")));
			if(orgMap.containsKey("tow"))//如果存在上级医院
				mdOrderInfo.setRbsId(Integer.parseInt(orgMap.get("tow")));
			mdOrderInfo.setRbbId(account.getOldId());
			mdOrderInfo.setPurchaseType("3");
		}
		mdOrderInfo.setPurchaseId(account.getSuiId());
		mdOrderInfo.setOrderCode(CreateCodeUtil.getNo("OR"));
		mdOrderInfo.setPlaceOrderTime(new Date());
		mdOrderInfo.setPlaceOrderMoney(allMoney+expressMoney);
		mdOrderInfo.setMoney2(expressMoney);//邮费
		mdOrderInfo.setCommodityNumber(allShu);
		mdOrderInfo.setOrderState("1");//订单状态正常
//		if(payType != null && payType != 3) {
			mdOrderInfo.setProcessStatus("2"); //流程状态：待付款
//		}else {
//			mdOrderInfo.setProcessStatus("1");//流程状态：待发货
//			mdOrderInfo.setPayType(payType);
//		}
		mdOrderInfo.setApplicantName(supplier.getApplicantName());
		mdOrderInfo.setPurchaseUnit(orgNameStr);//采购商名称
		mdOrderInfo.setPurchaseAccount(account.getUserName());//采购人员账号
		mdOrderInfo.setLocation(mdDeliveryAddress.getLocation());
		mdOrderInfo.setProvince(mdDeliveryAddress.getProvince());
		mdOrderInfo.setCity(mdDeliveryAddress.getCity());
		mdOrderInfo.setArea(mdDeliveryAddress.getArea());
		mdOrderInfo.setAddressee(mdDeliveryAddress.getAddressee());
		mdOrderInfo.setAddresseeMobile(mdDeliveryAddress.getAddresseeMobile());
		mdOrderInfo.setAddresseeTelephone(mdDeliveryAddress.getAddresseeTelephone());
		mdOrderInfo.setZipCode(mdDeliveryAddress.getZipCode());
		mdOrderInfo.setDeliveryAddress(mdDeliveryAddress.getDeliveryAddress());
		mdOrderInfo.setNeedBill(needBill);
		mdOrderInfo.setBillType(billType);
		mdOrderInfo.setBillCode(billCode);
		mdOrderInfo.setBillHeard(billHeard);
		if(content != null && !content.trim().equals(""))
			mdOrderInfo.setScopeBusiness(content);
		mdOrderInfo.setReceivingStatus("1");//收货状态:未收货
		return mdOrderInfo;
	}

	private List<Map<String,Object>> initMatInfoList(String mmfIds, String shus) throws Exception{
		List<Map<String,Object>> reList = new ArrayList<Map<String,Object>>();
		String[] mmfIdArray = mmfIds.split(",");
		String[] shuArray = shus.split(",");

		Map<String,String> shuMap = new HashMap<String,String>();
		Integer shuArrayLength = shuArray.length;
		for(int i = 0;i < mmfIdArray.length;i++) {
			if(shuArrayLength <= i){
				continue;
			}
			shuMap.put(mmfIdArray[i], shuArray[i]);
		}
		MdMaterielFormatView mdMaterielFormatView = new MdMaterielFormatView();
		mdMaterielFormatView.setMmfIds(mmfIds);
		List<MdMaterielFormatView> matList = mdMaterielInfoDao.getListModelByMdMdMaterielFormatView(mdMaterielFormatView);

		if(matList != null && matList.size() > 0){
			String wzIds = "";
			for(MdMaterielFormatView view : matList)
				wzIds += view.getWzId() + ",";
			wzIds = wzIds.substring(0, wzIds.length()-1);
			MdSupplier supplier = new MdSupplier();
			supplier.setWzId_str(wzIds);
			List<MdSupplier> supplierList = mdSupplierDao.getListByMdSupplier(supplier);
			if(supplierList != null && supplierList.size() > 0){
				for(MdSupplier mdSupplier : supplierList){
					Integer allShu=0;
					Double allMoney=0.0;
					Map<String,Object> reMap = new HashMap<String,Object>();
					reMap.put("mdSupplier", mdSupplier);
					List<MdMaterielFormatView> infoList = new ArrayList<MdMaterielFormatView>();
					for(MdMaterielFormatView view : matList){
						if(view.getWzId().equals(mdSupplier.getWzId())){
							String shuMmfId = shuMap.get(""+view.getMmfId());
							if(shuMmfId == null){
								continue;
							}
							Integer shu = Integer.parseInt(shuMap.get(""+view.getMmfId()));
							if(shu == 0){
								continue;
							}
							view.setCountShu(shu+"");
							view.setCountMoney((shu*view.getPrice())+"");
							//view.setMoney1(view.getPrice());
							infoList.add(view);
							allShu += shu;
							allMoney += shu*view.getPrice();
						}
					}
					reMap.put("matList", infoList);
					reMap.put("allShu", allShu);
					reMap.put("allMoney", allMoney);
					reList.add(reMap);
				}
			}
		}
		return reList;
	}

	private PagerModel getOrderPagerByOrderInfo() throws HSKException {
		PagerModel model = new PagerModel();
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			model = mdOrderInfoDao.getOrderInfoListByAfterSale(sysUserInfo.getSuiId());
			if(model.getItems() != null && model.getItems().size() > 0) {
				String moiIds = "";
				List<MdOrderInfoAfterSaleViewEntity> orderList = model.getItems();
				for (MdOrderInfoAfterSaleViewEntity order : orderList) {
					List<Map<String, Object>> addList = mdOrderMxDao.getMxListModelByMoiId(order.getMoiId(), null, 5, 1, null);
					List<Map<String, Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(order.getMoiId(), null);
					Map<String, Object> map = listCount.get(0);
					addList.add(map);
					order.setOrderMxList(addList);
				}
				model.setItems(orderList);
				model.setRows(orderList);
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public PagerModel getOrderPager(String processStatus) throws HSKException {
		PagerModel model = new PagerModel();
		try {
			MdOrderInfo mdOrderInfo = new MdOrderInfo();
			mdOrderInfo.setProcessStatus_str(processStatus);
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			if(processStatus != null && processStatus.equals("7")){
				model = getOrderPagerByOrderInfo(); //售后信息
				return model;
			}else {
				model = mdOrderInfoDao.getOrderInfoListByProcessState(sysUserInfo.getSuiId(), processStatus);
			}
			if(model.getItems() != null && model.getItems().size() > 0){
				String moiIds = "";
				List<MdOrderInfo> orderList = model.getItems();
				for(MdOrderInfo order : orderList){
					List<Map<String,Object>> addList = mdOrderMxDao.getMxListModelByMoiId(order.getMoiId(), null, 5, 1, null);
					List<Map<String,Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(order.getMoiId(), null);
					Map<String,Object> map = listCount.get(0);
					addList.add(map);
					order.setOrderMxList(addList);
//					moiIds += order.getMoiId()+",";
				}
//				moiIds = moiIds.substring(0, moiIds.length()-1);
//				List<Map<String,Object>> mxList = mdOrderMxDao.getMxListByMoiIds(moiIds);
//				for(MdOrderInfo order : orderList){
//					List<Map<String,Object>> addList = new ArrayList<Map<String,Object>>();
//					for(Map<String,Object> mx : mxList){
//						if(mx.get("moi_id").toString().equals(order.getMoiId()+""))
//							addList.add(mx);
//					}
//					order.setOrderMxList(addList);
//				}
				model.setItems(orderList);
				model.setRows(orderList);
			}

		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public PagerModel getAfterSalePager(Integer moiId, String masCode, String moCode, String serchaName) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList<MdOrderAfterSaleEntity>());
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			pm = mdOrderAfterSaleDao.getMdOrderSaleAfterPageModelBySearch1(sysUserInfo.getSuiId(), moiId, masCode, moCode, serchaName);
			if (pm.getItems() != null && pm.getItems().size() > 0) {
				List<MdOrderAfterSaleEntity> orderList = pm.getItems();
				for (MdOrderAfterSaleEntity order : orderList) {
					Integer masId = order.getMasId();
					List<Map<String, Object>> addList = mdOrderAfterSaleDao.getMdOrderSaleAfterMxList1(masId, moiId, masCode, moCode, serchaName, 5, 1);
					List<Map<String, Object>> listCount = mdOrderAfterSaleDao.getMdOrderSaleAfterMxListCountByMasId1(masId, moiId, masCode, moCode, serchaName);
					Map<String, Object> map = listCount.get(0);
					addList.add(map);
					order.setAsMxList(addList);
				}
				pm.setItems(orderList);
				pm.setRows(orderList);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
    public PagerModel getOrderPagerOne(Integer moiId) throws HSKException {
        PagerModel model = new PagerModel(new ArrayList<MdOrderInfo>());
        try {
        	SysUserInfo sysUserInfo = this.getApiSessionAccount();
            MdOrderInfo mdOrderInfo = new MdOrderInfo();
            mdOrderInfo.setMoiId(moiId);
            model = mdOrderInfoDao.getOrderInfoListByMoiid(sysUserInfo.getSuiId(), moiId);
//            if(model.getItems() != null && model.getItems().size() > 0){
//                List<MdOrderInfo> orderList = model.getItems();
//                for(MdOrderInfo order : orderList){
//                    List<Map<String,Object>> addList = mdOrderMxDao.getMxListModelByMoiId(order.getMoiId(), 5, 1);
//                    List<Map<String,Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(order.getMoiId());
//                    Map<String,Object> map = listCount.get(0);
//                    addList.add(map);
//                    order.setOrderMxList(addList);
//                }
//                model.setItems(orderList);
//                model.setRows(orderList);
//            }

        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
	public PagerModel buyAgain(Integer moiId) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList<Map<String, String>>());
		try {
			List<Map<String,Object>> mxList = mdOrderMxDao.getMxListByMoiId(moiId);
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxList.size());
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return pm;
	}

	private PagerModel findOrderIncludeAsBySearch(String processStatus, String processStatus2, String searchName, String placeOrderTime, String payType, Integer limit, Integer page) throws HSKException {
		PagerModel model = new PagerModel(new ArrayList<MdOrderInfoAfterSaleViewEntity>());
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			model = mdOrderInfoDao.findOrderIncludeAsBySearch(sysUserInfo.getSuiId(), processStatus2.equals("") ? processStatus : processStatus2, searchName, placeOrderTime, payType);
			List<Map<String, Object>> listMoiids = new ArrayList<Map<String, Object>>();
//			if (!processStatus.equals("") || !searchName.equals("")) {
//				listMoiids = mdOrderInfoDao.findOderByMx(sysUserInfo.getSuiId(), processStatus2.equals("") ? processStatus : processStatus2, searchName, limit, page);
//			}

//			String moiIds = "";
			List<MdOrderInfoAfterSaleViewEntity> orderList = model.getItems();
			List<Map<String, Object>> mxList = new ArrayList<Map<String, Object>>();
//			for (MdOrderInfoAfterSaleViewEntity order : orderList)
//				moiIds += order.getMoiId() + ",";
//			if (!moiIds.equals("")) {
//				moiIds = moiIds.substring(0, moiIds.length() - 1);
//				mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(moiIds, null, limit, page);
//				moiIds = "";
//			}

//			for (Map<String, Object> moiid : listMoiids) {
//				String moi_id = moiid.get("moi_id").toString() + ",";
//				if (moiIds.indexOf(moi_id) < 0) {
//					moiIds += moi_id;
//				}
//			}
//			if (!moiIds.equals("")) {
//				moiIds = moiIds.substring(0, moiIds.length() - 1);
//				mxList.addAll(mdOrderMxDao.getMxListByMoiIdsAndSearch(moiIds, searchName, limit, page));
//			}

//			if (orderList.size() <= 0) {
//				for (Map<String, Object> moiid : listMoiids) {
//					orderList.add(mdOrderInfoDao.findMdOrderInfoIncludeAsById(Integer.parseInt(moiid.get("moi_id").toString())));
//				}
//			}
			for (MdOrderInfoAfterSaleViewEntity order : orderList) {
//				List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
//				for (Map<String, Object> mx : mxList) {
//					if (mx.get("moi_id").toString().equals(order.getMoiId() + ""))
//						addList.add(mx);
//				}
				mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(order.getMoiId().toString(), searchName, limit, page);
				if(mxList.size() <= 0){
					mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(order.getMoiId().toString(), null, limit, page);
				}
				List<Map<String, Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(order.getMoiId(), null);
				Map<String, Object> map = listCount.get(0);
				mxList.add(map);
				order.setOrderMxList(mxList);
			}
			model.setItems(orderList);
			model.setRows(orderList);

		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
    public PagerModel findOrderBySearch(String processStatus, String processStatus2, String searchName, String placeOrderTime, String payType, Integer limit, Integer page) throws HSKException {
		PagerModel model = new PagerModel(new ArrayList<MdOrderInfo>());
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			MdOrderInfo mdOrderInfo = new MdOrderInfo();
			if (processStatus.equals("7") || processStatus2.equals("7")){
				return findOrderIncludeAsBySearch(processStatus, processStatus2, searchName, placeOrderTime, payType, limit, page);
			}else{
				mdOrderInfo.setProcessStatus_str(processStatus2.equals("") ? processStatus : processStatus2);
				model = mdOrderInfoDao.findOrderBySearch(sysUserInfo.getSuiId(), processStatus2.equals("") ? processStatus : processStatus2, searchName, placeOrderTime, payType);
			}


//			List<Map<String, Object>> listMoiids = new ArrayList<Map<String, Object>>();
//			if(!processStatus.equals("") || !searchName.equals("")) {
//				listMoiids = mdOrderInfoDao.findOderByMx(sysUserInfo.getSuiId(), processStatus2.equals("") ? processStatus : processStatus2, searchName, limit, page);
//			}

//			String moiIds = "";
			List<MdOrderInfo> orderList = model.getItems();
			List<Map<String, Object>> mxList = new ArrayList<Map<String, Object>>();
//			for (MdOrderInfo order : orderList)
//				moiIds += order.getMoiId() + ",";
//			if(!moiIds.equals("")) {
//				moiIds = moiIds.substring(0, moiIds.length() - 1);
//				mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(moiIds, null, limit, page);
//				moiIds = "";
//			}

//			for (Map<String, Object> moiid : listMoiids) {
//				String moi_id = moiid.get("moi_id").toString() + ",";
//				if (moiIds.indexOf(moi_id) < 0) {
//					moiIds += moi_id;
//				}
//			}
//			if(!moiIds.equals("")){
//				moiIds = moiIds.substring(0, moiIds.length() - 1);
//				mxList.addAll(mdOrderMxDao.getMxListByMoiIdsAndSearch(moiIds, searchName, limit, page));
//			}

//			if(orderList.size() <= 0){
//				for (Map<String, Object> moiid : listMoiids) {
//					orderList.add(mdOrderInfoDao.findMdOrderInfoById(Integer.parseInt(moiid.get("moi_id").toString())));
//				}
//			}
			String countName = searchName;
			for (MdOrderInfo order : orderList) {
//				List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
				mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(order.getMoiId().toString(), searchName, limit, page);
				if(mxList.size() <= 0){
					mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(order.getMoiId().toString(), null, limit, page);
					countName = "";
				}
//				for (Map<String, Object> mx : mxList) {
//					if (mx.get("moi_id").toString().equals(order.getMoiId() + ""))
//						addList.add(mx);
//				}
//				addList.add(mdOrderMxDao.getMxListByMoiIdsAndSearch(order.getMoiId().toString(), null, limit, page));
				List<Map<String,Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(order.getMoiId(), countName);
				Map<String,Object> map = listCount.get(0);
				mxList.add(map);
				order.setOrderMxList(mxList);
			}
			model.setItems(orderList);
			model.setRows(orderList);

		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return model;
    }

	@Override
	public PagerModel findOrderBySearchName(String processStatus, String processStatus2, String searchName, String placeOrderTime, String payType, Integer limit, Integer page) throws HSKException {
		PagerModel model = new PagerModel(new ArrayList<MdOrderInfo>());
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			MdOrderInfo mdOrderInfo = new MdOrderInfo();
			if (processStatus.equals("7") || processStatus2.equals("7")){
				return findOrderIncludeAsBySearch(processStatus, processStatus2, searchName, placeOrderTime, payType, limit, page);
			}else{
				mdOrderInfo.setProcessStatus_str(processStatus2.equals("") ? processStatus : processStatus2);
				model = mdOrderInfoDao.findOrderBySearchName(sysUserInfo.getSuiId(), processStatus2.equals("") ? processStatus : processStatus2, searchName, placeOrderTime, payType);
			}

			List<MdOrderInfo> orderList = model.getItems();
			List<Map<String, Object>> mxList = new ArrayList<Map<String, Object>>();
			String countName = searchName;
			for (MdOrderInfo order : orderList) {
				mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(order.getMoiId().toString(), searchName, searchName, limit, page);
				if(mxList.size() <= 0){
					mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(order.getMoiId().toString(), null, limit, page);
					countName = "";
				}
				List<Map<String,Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(order.getMoiId(), countName);
				Map<String,Object> map = listCount.get(0);
				mxList.add(map);
				order.setOrderMxList(mxList);
			}
			model.setItems(orderList);
			model.setRows(orderList);

		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public SysRetrunMessage getAllPrice(String processStatus, String processStatus2, String searchName, String placeOrderTime, String payType) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try{
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			Double placeOrderMoneys = mdOrderInfoDao.CountMoneys(sysUserInfo.getSuiId(), processStatus, searchName, placeOrderTime, payType);
			sm.setObj(placeOrderMoneys);
		} catch (Exception e){
			sm.setCode(0l);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getAllAsPrice(String processStatus, String processStatus2, String searchName, String placeOrderTime, String payType) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try{
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			Double placeOrderMoneys = mdOrderInfoDao.CountAsMoneys(sysUserInfo.getSuiId(), processStatus, searchName, placeOrderTime, payType);
			sm.setObj(placeOrderMoneys);
		} catch (Exception e){
			sm.setCode(0l);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage countOrder() throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		Map<String,Integer> reMap =  new HashMap<String,Integer>();
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			Integer suiId = sysUserInfo.getSuiId();
			Integer allCount = mdOrderInfoDao.countOrderInfoByProcessState(suiId,null);
			Integer dfhCount = mdOrderInfoDao.countOrderInfoByProcessState(suiId,"1");
			Integer dfkCount = mdOrderInfoDao.countOrderInfoByProcessState(suiId,"2");
			Integer dshCount = mdOrderInfoDao.countOrderInfoByProcessState(suiId,"3");
			Integer bfhCount = mdOrderInfoDao.countOrderInfoByProcessState(suiId,"4");
			Integer succCount = mdOrderInfoDao.countOrderInfoByProcessState(suiId,"5");
			Integer failCount = mdOrderInfoDao.countOrderInfoByProcessState(suiId,"6");
			Integer shCount = mdOrderInfoDao.countOrderInfoByAfterSale(suiId);
			reMap.put("all", allCount);
			reMap.put("dfh", dfhCount);
			reMap.put("dfk", dfkCount);
			reMap.put("dsh", dshCount);
			reMap.put("bfh", bfhCount);
			reMap.put("succ", succCount);
			reMap.put("fail", failCount);
			reMap.put("sh", shCount);
			srm.setObj(reMap);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}

		return srm;
	}

	@Override
	public SysRetrunMessage getOrderMxByMoiId(Integer moiId)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			List<Map<String,Object>> mxList = mdOrderMxDao.getMxListByMoiIds(moiId+"");
			srm.setObj(mxList);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return srm;
	}

	@Override
	public PagerModel getOrderMxModelByMoiId(Integer moiId, String searchName, Integer limit, Integer page) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList<Map<String,Object>>());
		try {
			List<Map<String,Object>> mxList = mdOrderMxDao.getMxListModelByMoiId(moiId, searchName, limit, page, "mas_id");
			List<Map<String,Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(moiId, searchName);
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

	@Override
	public PagerModel getOrderMxApplyAsByMoiId(Integer moiId, Integer limit, Integer page) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList<Map<String,Object>>());
		try {
			List<Map<String,Object>> mxList = mdOrderMxDao.getMxListModelByMoiId(moiId, null, limit, page, "mas_id");
			List<Map<String,Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(moiId, null);
			Integer wmsMiId;
			Integer wmsMiIdPre = -1;
			MdMaterielInfo att_MdMaterielInfo;


			List<Integer> wmsMiIdList = new ArrayList<>();
			for (Map<String, Object> map : mxList) {
				wmsMiId = Integer.parseInt(map.get("wms_mi_id").toString());
				if (wmsMiIdList.contains(wmsMiId)) {
					continue;
				}

				wmsMiIdPre = wmsMiId;
				wmsMiIdList.add(wmsMiId);

			}

			List<Map<String, Object>> list1;
			List<Map<String, Object>> list2 = new ArrayList<>();
			Map<String, Object> mapList;
			for (Integer wms : wmsMiIdList) {
				list1 = new ArrayList<>();
				mapList = new HashMap<>();
				for (Map<String, Object> map : mxList) {
					wmsMiId = Integer.parseInt(map.get("wms_mi_id").toString());
					if (java.util.Objects.equals(wmsMiId, wms)) {
						list1.add(map);
					}
				}
				att_MdMaterielInfo= mdMaterielInfoDao.findMdMaterielInfoById(wms);
				mapList.put("materiel", att_MdMaterielInfo);
				mapList.put("items", list1);
				list2.add(mapList);
			}

			Map<String,Object> map = listCount.get(0);
			list2.add(map);
			pm.setItems(list2);
			pm.setRows(list2);
			pm.setTotalCount(Integer.parseInt(map.get("total_count").toString()));
			pm.setTotal(Integer.parseInt(map.get("total_count").toString()));
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return pm;
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

	@Override
	public SysRetrunMessage exportOrderInfo(String mmfIds, String shus,
			String wzIds, String expressMoneys) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		DecimalFormat df= new DecimalFormat("######0.00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Map<String,String> expressMap = new HashMap<String,String>();
			if(wzIds != null && !wzIds.equals("")){
				String[] wzIdArray = wzIds.split(",");
				String[] expressArray = expressMoneys.split(",");
				for(int i=0 ;i < wzIdArray.length;i++){
					expressMap.put(wzIdArray[i], expressArray[i]);
				}
			}
			List<Map<String,Object>> allList = this.initMatInfoList(mmfIds, shus);
			Map<String, Object> dataMap = new HashMap<String,Object>();
			List<Map<String,Object>> reList = new ArrayList<Map<String,Object>>();
			int rowCount=0;
			for(Map<String,Object> map : allList){

				MdSupplier supplier =(MdSupplier) map.get("mdSupplier");


				List<MdMaterielFormatView> viewList = (List<MdMaterielFormatView>) map.get("matList");
				rowCount += 4+viewList.size();
				Integer allShu=Integer.parseInt(map.get("allShu")+"");
				Double allMoney = Double.parseDouble(map.get("allMoney")+"");
				String expressMess="运费";
				Map<String,Object> reMap = new HashMap<String,Object>();
				reMap.put("applicantName", supplier.getApplicantName());
				SysUserInfo sysUserInfo=this.getApiSessionAccount();
				reMap.put("orgGxIdStr",sysUserInfo.getOrgGxId_Str());
				if(!expressMap.containsKey(supplier.getWzId().toString()))
					reMap.put("expressMoney", "0.00");
				else{
					reMap.put("expressMoney", expressMap.get(supplier.getWzId().toString()));
					allMoney += Double.parseDouble(expressMap.get(supplier.getWzId().toString()));
				}
				if(supplier.getNoExpressMoney()!= null && supplier.getNoExpressMoney()>0
						&& supplier.getExpressMoney()!=null && supplier.getExpressMoney() >0)
					expressMess += "（满"+df.format(supplier.getNoExpressMoney())+"金额，包邮）";
				reMap.put("allShu", allShu);
				reMap.put("allMoney", df.format(allMoney));
				reMap.put("matShu", viewList.size()-1);
				reMap.put("matShu2", viewList.size());

				reMap.put("expressMess", expressMess);
				MdMaterielFormatView view = viewList.get(0);
				reMap.put("firstName", view.getMatName()+"("+view.getMatName()+")");
				//制作日期
				//reMap.put("firstBasicUnit", view.get!= null ?view.getBasicUnit():"");
//				if(view.getCreateDate()!=null){
//					reMap.put("createDate", sdf.format(view.getCreateDate()));
//				}else{
//					reMap.put("createDate", "");
//				}
				Date Cdate =new Date();
				//SimpleDateFormat sdfC=new SimpleDateFormat("yyyy-MM-dd");
				reMap.put("createDate", sdf.format(Cdate));
				//制作人

//				reMap.put("createRen", view.getCreateRen()!= null ?view.getCreateRen():"");
				reMap.put("createRen", sysUserInfo.getUserName());
				//订单号
				//单位
				reMap.put("firstBasicUnit", view.getBasicUnit()!= null ?view.getBasicUnit():"");
				//规格
				reMap.put("firstNorm", view.getNorm()!= null ?view.getNorm():"");
				//规格编号
				reMap.put("firstMmfCode", view.getMmfCode()!= null ?view.getMmfCode():"");
				/*reMap.put("firstName", view.getMatName()+"("+view.getMmfName()+")");*/
				reMap.put("firstPrice", df.format(view.getPrice()));
				reMap.put("firstShu", view.getCountShu());
				reMap.put("firstMoney", view.getCountMoney());
				List<Map<String,Object>> infoList = new ArrayList<Map<String,Object>>();
				if(viewList.size() > 1){
					for(int i =1;i < viewList.size();i++){
						Map<String,Object> infoMap = new HashMap<String,Object>();
						MdMaterielFormatView info = viewList.get(i);
						infoMap.put("infoName", info.getMatName()+"("+info.getMatName()+")");
						//单位
						infoMap.put("infoBasicUnit", info.getBasicUnit()!= null ?info.getBasicUnit():"");
						//规格编号
						infoMap.put("infoMmfCode", info.getMmfCode()!= null ?info.getMmfCode():"");
						//规格
						infoMap.put("infoNorm", info.getNorm()!= null ?info.getNorm():"");
						infoMap.put("infoPrice", df.format(info.getPrice()));
						infoMap.put("infoShu", info.getCountShu());
						infoMap.put("infoMoney", info.getCountMoney());
						infoList.add(infoMap);
					}
				}
				reMap.put("infoList", infoList);
				reList.add(reMap);
			}
			dataMap.put("rowCount", rowCount);
			dataMap.put("reList", reList);
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
			// 这里我们的模板是放在org.cnzjw.template包下面
			configuration.setClassForTemplateLoading(this.getClass(),
					"/ftl");
			Template t = configuration.getTemplate("exportGwcInfoCC.ftl");
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
			SysUserInfo sysUserInfo=this.getApiSessionAccount();
	        String realPath = tmpPath +sysUserInfo.getOrgGxId_Str()+"-(采购角色)采购明细单-SCCGQD-"+l+ ".xls";
			File outFile = new File(realPath);
			Writer out = null;
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile),"UTF-8"));
			t.process(dataMap, out);
			out.close();
			String rootUrl = request.getContextPath() + EXPORT_PATH+sysUserInfo.getOrgGxId_Str()+"-(采购角色)采购明细单-SCCGQD-"+l+ ".xls";
			Map<String,String> map = new HashMap<String,String>();

			map.put("fileName", sysUserInfo.getOrgGxId_Str()+"-(采购角色)采购明细单-SCCGQD-"+l+ ".xls");
			map.put("path", rootUrl);
			srm.setObj(map);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
			throw new  HSKException(e);
		}
		return srm;
	}

	/*@Override
	public SysRetrunMessage exportOrderInfo(String mmfIds, String shus,
			String wzIds, String expressMoneys) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		DecimalFormat df= new DecimalFormat("######0.00");
		try{
			Map<String,String> expressMap = new HashMap<String,String>();
			if(wzIds != null && !wzIds.equals("")){
				String[] wzIdArray = wzIds.split(",");
				String[] expressArray = expressMoneys.split(",");
				for(int i=0 ;i < wzIdArray.length;i++){
					expressMap.put(wzIdArray[i], expressArray[i]);
				}
			}
			List<Map<String,Object>> allList = this.initMatInfoList(mmfIds, shus);
			Map<String, Object> dataMap = new HashMap<String,Object>();
			List<Map<String,Object>> reList = new ArrayList<Map<String,Object>>();
			int rowCount=0;
			for(Map<String,Object> map : allList){
				MdSupplier supplier =(MdSupplier) map.get("mdSupplier");
				List<MdMaterielFormatView> viewList = (List<MdMaterielFormatView>) map.get("matList");
				rowCount += 4+viewList.size();
				Integer allShu=Integer.parseInt(map.get("allShu")+"");
				Double allMoney = Double.parseDouble(map.get("allMoney")+"");
				String expressMess="运费";
				Map<String,Object> reMap = new HashMap<String,Object>();
				reMap.put("applicantName", supplier.getApplicantName());
				if(!expressMap.containsKey(supplier.getWzId().toString()))
					reMap.put("expressMoney", "0.00");
				else{
					reMap.put("expressMoney", expressMap.get(supplier.getWzId().toString()));
					allMoney += Double.parseDouble(expressMap.get(supplier.getWzId().toString()));
				}
				if(supplier.getNoExpressMoney()!= null && supplier.getNoExpressMoney()>0 
						&& supplier.getExpressMoney()!=null && supplier.getExpressMoney() >0)
					expressMess += "（满"+df.format(supplier.getNoExpressMoney())+"金额，包邮）";
				reMap.put("allShu", allShu);
				reMap.put("allMoney", df.format(allMoney));
				reMap.put("matShu", viewList.size()-1);
				reMap.put("matShu2", viewList.size());
				reMap.put("expressMess", expressMess);
				MdMaterielFormatView view = viewList.get(0);
				reMap.put("firstName", view.getMatName()+"("+view.getMmfName()+")");
				reMap.put("firstPrice", df.format(view.getPrice()));
				reMap.put("firstShu", view.getCountShu());
				reMap.put("firstMoney", view.getCountMoney());
				List<Map<String,Object>> infoList = new ArrayList<Map<String,Object>>();
				if(viewList.size() > 1){
					for(int i =1;i < viewList.size();i++){
						Map<String,Object> infoMap = new HashMap<String,Object>();
						MdMaterielFormatView info = viewList.get(0);
						infoMap.put("infoName", info.getMatName()+"("+info.getMmfName()+")");
						infoMap.put("infoPrice", df.format(info.getPrice()));
						infoMap.put("infoShu", info.getCountShu());
						infoMap.put("infoMoney", info.getCountMoney());
						infoList.add(infoMap);
					}
				}
				reMap.put("infoList", infoList);
				reList.add(reMap);
			}
			
			dataMap.put("rowCount", rowCount);
			dataMap.put("reList", reList);
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
			// 这里我们的模板是放在org.cnzjw.template包下面
			configuration.setClassForTemplateLoading(this.getClass(),
					"/ftl");
			Template t = configuration.getTemplate("exportGwcInfoC.ftl");
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
			throw new  HSKException(e);
		}
		return srm;
	}*/
	/*@Override
	public SysRetrunMessage exportOrderInfo(String mmfIds, String shus,
			String wzIds, String expressMoneys) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		DecimalFormat df= new DecimalFormat("######0.00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//DecimalFormat df = new DecimalFormat("######0.00");
		DecimalFormat df2 = new DecimalFormat("######0");
		try{
			Map<String,String> expressMap = new HashMap<String,String>();
			if(wzIds != null && !wzIds.equals("")){
				String[] wzIdArray = wzIds.split(",");
				String[] expressArray = expressMoneys.split(",");
				for(int i=0 ;i < wzIdArray.length;i++){
					expressMap.put(wzIdArray[i], expressArray[i]);
				}
			}
			
			List<Map<String,Object>> allList = this.initMatInfoList(mmfIds, shus);
			Map<String, Object> dataMap = new HashMap<String,Object>();
			List<Map<String,Object>> reList = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> mtList = new ArrayList<Map<String,Object>>();
			int all=0;
			*//**
			 *
			 * 之前存在数据不同的BUG 重新通过mmfIds
			 *//*
			String[] mooIdsArray = mmfIds.split(",");
			for (int i = 0; i < mooIdsArray.length; i++) {
				Integer mmfId = Integer.parseInt(mooIdsArray[i]);
				MdMaterielFormat mmf=mdMaterielFormatDao.findMdMaterielFormatById(mmfId);
				//mmf.setMmfId(mmfId);
				try {
					List<MdMaterielFormat> mmfList=(List<MdMaterielFormat>) this.getList(mmf);
					//dataMap = new HashMap<String, Object>();
					MdMaterielInfo mmt=mdMaterielInfoDao.findMdMaterielInfoById(mmf.getWmsMiId());
					Map<String,Object> reMap = new HashMap<String,Object>();
					reMap.put("matName", mmt.getMatName());
					all=mmt.getMatName().length();
					all++;
					reList.add(reMap);
				} catch (HSKDBException e) {
					e.printStackTrace();
				} 
			}
			int rowCount=0;
			for(Map<String,Object> map : allList){
				MdSupplier supplier =(MdSupplier) map.get("mdSupplier");
				List<MdMaterielFormatView> viewList = (List<MdMaterielFormatView>) map.get("matList");
				rowCount += 4+viewList.size();
				Integer allShu=Integer.parseInt(map.get("allShu")+"");
				Double allMoney = Double.parseDouble(map.get("allMoney")+"");
				String expressMess="运费";
				Map<String,Object> reMap = new HashMap<String,Object>();
				reMap.put("applicantName", supplier.getApplicantName());
				if(!expressMap.containsKey(supplier.getWzId().toString()))
					reMap.put("expressMoney", "0.00");
				else{
					reMap.put("expressMoney", expressMap.get(supplier.getWzId().toString()));
					allMoney += Double.parseDouble(expressMap.get(supplier.getWzId().toString()));
				}
				if(supplier.getNoExpressMoney()!= null && supplier.getNoExpressMoney()>0 
						&& supplier.getExpressMoney()!=null && supplier.getExpressMoney() >0)
					expressMess += "（满"+df.format(supplier.getNoExpressMoney())+"金额，包邮）";
				reMap.put("allShu", allShu);
				reMap.put("allMoney", df.format(allMoney));
				reMap.put("matName", viewList.size()-1);
				reMap.put("matShu2", viewList.size());
				reMap.put("expressMess", expressMess);
				MdMaterielFormatView view = viewList.get(0);
				reMap.put("firstName", view.getMatName()+"("+view.getMmfName()+")");
				reMap.put("firstPrice", df.format(view.getPrice()));
				reMap.put("firstShu", view.getCountShu());
				reMap.put("firstMoney", view.getCountMoney());
				List<Map<String,Object>> infoList = new ArrayList<Map<String,Object>>();
				if(viewList.size() > 1){
					for(int i =1;i < viewList.size();i++){
						Map<String,Object> infoMap = new HashMap<String,Object>();
						MdMaterielFormatView info = viewList.get(0);
						infoMap.put("infoName", info.getMatName()+"("+info.getMmfName()+")");
						infoMap.put("infoPrice", df.format(info.getPrice()));
						infoMap.put("infoShu", info.getCountShu());
						infoMap.put("infoMoney", info.getCountMoney());
						infoList.add(infoMap);
					}
				}
				reMap.put("infoList", infoList);
				reList.add(reMap);
			}
			
			dataMap.put("rowCount", rowCount);
			dataMap.put("reList", reList);
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setClassForTemplateLoading(this.getClass(),
					"/ftl");
			Template t = configuration.getTemplate("exportGwcInfoC.ftl");
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
			throw new  HSKException(e);
		}
		return srm;
	}*/
	
	/*public SysRetrunMessage exportOrderInfo(String mmfIds, String shus,
			String wzIds, String expressMoneys) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		String[] mooIdsArray = mmfIds.split(",");
		try {
		Map<String, Object> dataMap = null;
		List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
		
		for (int i = 0; i < mooIdsArray.length; i++) {
			Integer mmfId = Integer.parseInt(mooIdsArray[i]);
			MdMaterielFormat mmf=mdMaterielFormatDao.findMdMaterielFormatById(mmfId);
			MdMaterielInfo am=new MdMaterielInfo();
			am.setWmsMiId(mmf.getWmsMiId());
			List<MdMaterielInfo> mxList = (List<MdMaterielInfo>) this
					.getList(am);
			DecimalFormat df= new DecimalFormat("######0.00");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DecimalFormat df2 = new DecimalFormat("######0");
			dataMap = new HashMap<String, Object>();
			for (MdMaterielInfo obj : mxList) {
				Map<String, String> reMap = new HashMap<String,String>();
				reMap.put("matName", obj.getMatName());
				reList.add(reMap);
			}	
			dataMap.put("mxList", reList);
		}
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
		// 这里我们的模板是放在org.cnzjw.template包下面
		configuration.setClassForTemplateLoading(this.getClass(),
				"/ftl");
		Template t = configuration.getTemplate("exportGwcInfoC.ftl");
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
		throw new  HSKException(e);
		}
		
		return srm; }*/



	@Override
	public SysRetrunMessage getMatFormat(Integer wmsMiId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
		mdMaterielFormat.setWmsMiId(wmsMiId);
		mdMaterielFormat.setState("1");
		try {
			List<MdMaterielFormat> list = mdMaterielFormatDao.getListByMdMaterielFormat(mdMaterielFormat);
			if(list != null && list.size() > 0)
				srm.setObj(list.get(0));
			else{
				srm.setCode(0l);
				srm.setMeg("操作失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage addCarts(Integer mcId, Integer mmfId, Integer shu, Double price) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		if(mmfId == null){
			srm.setCode(0l);
			srm.setMeg("操作失败");
			return srm;
		}
		String sessionId = request.getHeader("sessionId");
		String accountName = SessionUtil.getLocalAccount(sessionId);
		try{
			SysUserInfo sysUserInfo = new SysUserInfo();
			sysUserInfo.setAccount(accountName);
			sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
			Integer sui_id = sysUserInfo.getSuiId();
			MdCarts mdCarts = null;
			List<MdCarts> list = mdCartsDao.findMdCartsByMmfIdHql(sui_id, mmfId);
			if(list != null && list.size() > 0){
				mdCarts = list.get(0);
				if(shu == null){
					shu = mdCarts.getMcCount() + 1;
				}
				mdCarts.setMcCount(shu);
			} else{
				mdCarts = new MdCarts();
				if(shu == null){
					shu = 1;
				}
				mdCarts.setMmfId(mmfId);
				mdCarts.setMcCount(shu);
				mdCarts.setSuiId(sui_id);
				mdCarts.setPrice(price);
			}
			mdCartsDao.saveOrUpdateMdCarts(mdCarts);
		}catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage addCartsCache(String mcIds, String mmfIds, String shus, String prices) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		if (mmfIds == null || mmfIds.equals("")) {
			sm.setCode(0l);
			return sm;
		}
		if (shus == null || shus.equals("")) {
			sm.setCode(0l);
			return sm;
		}
		if (prices == null || prices.equals("")) {
			sm.setCode(0l);
			return sm;
		}
		try {
			String[] mmfIdList = mmfIds.split(",");
			String[] shusList = shus.split(",");
			String[] priceList = prices.split(",");
			Integer mmfId = 0;
			Integer shu = 0;
			Double price = 0d;

			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			Integer sui_id = sysUserInfo.getSuiId();
			for (Integer index = 0; index < mmfIdList.length; index ++) {
				mmfId = Integer.valueOf(mmfIdList[index]);
				shu = Integer.valueOf(shusList[index]);
				price = Double.parseDouble(priceList[index]);
				MdCarts mdCarts = null;
				List<MdCarts> list = mdCartsDao.findMdCartsByMmfIdHql(sui_id, mmfId);
				if(list != null && list.size() > 0){
					mdCarts = list.get(0);
					if(shu == null){
						shu = mdCarts.getMcCount() + 1;
					}
					mdCarts.setMcCount(shu);
				} else{
					mdCarts = new MdCarts();
					if(shu == null){
						shu = 1;
					}
					mdCarts.setMmfId(mmfId);
					mdCarts.setMcCount(shu);
					mdCarts.setSuiId(sui_id);
					mdCarts.setPrice(price);
				}
				mdCartsDao.saveOrUpdateMdCarts(mdCarts);
			}
		} catch (Exception e) {
			sm.setCode(0l);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage addCartsPlus(Integer mcId, Integer mmfId, Integer shu, Double price) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		if(mmfId == null){
			srm.setCode(0l);
			srm.setMeg("操作失败");
			return srm;
		}
		String sessionId = request.getHeader("sessionId");
		String accountName = SessionUtil.getLocalAccount(sessionId);
		try{
			SysUserInfo sysUserInfo = new SysUserInfo();
			sysUserInfo.setAccount(accountName);
			sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
			Integer sui_id = sysUserInfo.getSuiId();
			MdCarts mdCarts = null;
			List<MdCarts> list = mdCartsDao.findMdCartsByMmfIdHql(sui_id, mmfId);
			if(list != null && list.size() > 0){
				mdCarts = list.get(0);
				if(shu != null){
					shu = mdCarts.getMcCount() + shu;
				} else {
					shu = 1;
				}
				mdCarts.setMcCount(shu);
			} else{
				mdCarts = new MdCarts();
				if(shu == null){
					shu = 1;
				}
				mdCarts.setMmfId(mmfId);
				mdCarts.setMcCount(shu);
				mdCarts.setSuiId(sui_id);
				mdCarts.setPrice(price);
			}
			mdCartsDao.saveOrUpdateMdCarts(mdCarts);
		}catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage checkCarts(Integer mmfId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(2l);
		try{
			String sessionId = request.getHeader("sessionId");
			String accountName = SessionUtil.getLocalAccount(sessionId);
			SysUserInfo sysUserInfo = new SysUserInfo();
			sysUserInfo.setAccount(accountName);
			sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
			Integer sui_id = sysUserInfo.getSuiId();
			List<MdCarts> list = mdCartsDao.findMdCartsByMmfIdHql(sui_id, mmfId);
			if(list != null && list.size() > 0){
				srm.setCode(1l);
			}
		}catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败");
		}
		return srm;
	}

	@Override
	public PagerModel getCartsCountAndPrice() throws HSKException {
		PagerModel pm = new PagerModel();
		try{
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			Integer sui_id = sysUserInfo.getSuiId();
			List<Map<String, Object>> list = mdCartsDao.getCartsCountAndPrice(sui_id);
			pm.setItems(list);
			pm.setRows(list);
			pm.setTotal(list.size());
			pm.setTotalCount(list.size());
		}catch (Exception e){
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public PagerModel findMdCartsAll() throws HSKException {
		PagerModel pm = new PagerModel();
		String sessionId = request.getHeader("sessionId");
		String accountName = SessionUtil.getLocalAccount(sessionId);
		try{
			SysUserInfo sysUserInfo = new SysUserInfo();
			sysUserInfo.setAccount(accountName);
			sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
			Integer sui_id = sysUserInfo.getSuiId();
			List<MdCarts> list = mdCartsDao.findMdCartsAll(sui_id);
			if(list == null){
				list = new ArrayList<MdCarts>();
			}
			pm.setItems(list);
			pm.setRows(list);
			pm.setTotal(list.size());
			pm.setTotalCount(list.size());
		}catch (Exception e){
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public SysRetrunMessage deleteMdCartsByMmfId(String mmfIds) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		String sessionId = request.getHeader("sessionId");
		String accountName = SessionUtil.getLocalAccount(sessionId);
		try{
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			Integer sui_id = sysUserInfo.getSuiId();
			mdCartsDao.deleteMdCartsBySuiIdsAndMfIds(sui_id, mmfIds);
		}catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage deleteMdCartsAll() throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		String sessionId = request.getHeader("sessionId");
		String accountName = SessionUtil.getLocalAccount(sessionId);
		try{
			SysUserInfo sysUserInfo = new SysUserInfo();
			sysUserInfo.setAccount(accountName);
			sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
			Integer sui_id = sysUserInfo.getSuiId();
			mdCartsDao.deleteMdCartsAll(sui_id);
		}catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage getCartCountByMmfId(Integer mmfId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			if (sysUserInfo == null) {
				sm.setCode(0L);
				sm.setObj(0);
				return sm;
			}
			Integer count = mdCartsDao.getCartCountOnlyByMmfId(mmfId, sysUserInfo.getSuiId());
			sm.setObj(count);
		} catch (Exception e) {
			sm.setCode(0L);
			sm.setObj(0);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage updateMdOrderDelivery(Integer moiId, Integer mdaId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			MdOrderInfo mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
			Integer purchaseId = mdOrderInfo.getPurchaseId();
			Integer suiId = sysUserInfo.getSuiId();
			if (!Objects.equal(purchaseId, suiId)) {
				sm.setCode(0L);
				return sm;
			}
			MdDeliveryAddress mdDeliveryAddress = new MdDeliveryAddress();
			mdDeliveryAddress.setMdaId(mdaId);
			mdDeliveryAddress = (MdDeliveryAddress) this.getOne(mdDeliveryAddress);
			mdOrderInfo.setLocation(mdDeliveryAddress.getLocation());
			mdOrderInfo.setProvince(mdDeliveryAddress.getProvince());
			mdOrderInfo.setCity(mdDeliveryAddress.getCity());
			mdOrderInfo.setArea(mdDeliveryAddress.getArea());
			mdOrderInfo.setAddressee(mdDeliveryAddress.getAddressee());
			mdOrderInfo.setAddresseeMobile(mdDeliveryAddress.getAddresseeMobile());
			mdOrderInfo.setAddresseeTelephone(mdDeliveryAddress.getAddresseeTelephone());
			mdOrderInfo.setZipCode(mdDeliveryAddress.getZipCode());
			mdOrderInfo.setDeliveryAddress(mdDeliveryAddress.getDeliveryAddress());
			mdOrderInfo = mdOrderInfoDao.updateMdOrderInfoById(moiId, mdOrderInfo);
			sm.setObj(mdOrderInfo);
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	/**
	 * 记录售后
	 * @throws HSKException
	 */
	private void saveMdOrderAfterSaleEx(Integer masId, String content, String remarks, String orgName, Double money) throws HSKException{
		MdOrderAfterSaleExtendEntity mdOrderAfterSaleExtendEntity = new MdOrderAfterSaleExtendEntity();
		mdOrderAfterSaleExtendEntity.setMasId(masId);
		mdOrderAfterSaleExtendEntity.setCtlDate(new Date());
		mdOrderAfterSaleExtendEntity.setBuyerCtlContent(content);
		mdOrderAfterSaleExtendEntity.setSupplierCtlContent(content);
		mdOrderAfterSaleExtendEntity.setAsRemarks(remarks);
		mdOrderAfterSaleExtendEntity.setOrgName(orgName);
		mdOrderAfterSaleExtendEntity.setMoney(money);
		try {
			mdOrderAfterSaleDao.saveMdOrderSaleAfterEx(mdOrderAfterSaleExtendEntity);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 忽略将其他售后单的内容
	 * @param masId
	 * @param momIds
	 */
	private String ignoreOtherAfterSale(Integer masId, String momIds){
		String newMomIds = "";
		try {
			List<MdOrderAfterSaleMxEntity> list = mdOrderAfterSaleDao.getMdOrderSaleASMxListByMomIds(momIds.substring(0, momIds.length() - 1));
			if(list == null || list.size() <= 0){
				return momIds;
			}

			String[] momIdArray = momIds.split(",");
			for (String momIdStr : momIdArray) {
				for (MdOrderAfterSaleMxEntity item : list) {
					if (!item.getMasId().equals(masId) && momIdStr.equals(item.getMomId())) {
						continue;
					}
					newMomIds += momIdStr + ",";
				}
			}
			if(!newMomIds.equals("")){
				newMomIds = newMomIds.substring(0, newMomIds.length() - 1);
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return newMomIds;
	}

    @Override
    public SysRetrunMessage saveMdOrderSaleAfter(MdOrderAfterSaleEntity mdOrderAfterSaleEntity, String momIds, String listFilecode) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		SysUserInfo sysUserInfo=this.getApiSessionAccount();
		try {
			boolean need = this.checkNeedSecurity();
			if (need) {
				sm.setCode(3l);
				sm.setMeg("需要安全码");
				return sm;
			}
			if(mdOrderAfterSaleEntity.getMasId() != null){
				MdOrderAfterSaleEntity mdOrderAfterSaleEntity1 = mdOrderAfterSaleDao.getMdOrderSaleAfterByMasId(sysUserInfo.getSuiId(), mdOrderAfterSaleEntity.getMasId());
				if(mdOrderAfterSaleEntity1.getAsState() != 5 && mdOrderAfterSaleEntity1.getAsState() != 4){
					sm.setCode(2l);
					sm.setMeg("状态已改变，无法修改申请");
					return sm;
				}
				mdOrderAfterSaleDao.deleteAfterSaleMxAll(mdOrderAfterSaleEntity1.getMasId());
				momIds = ignoreOtherAfterSale(mdOrderAfterSaleEntity1.getMasId(), momIds); //去掉其中已属于其他售后单下的商品
			}
			mdOrderAfterSaleEntity.setSuiId(sysUserInfo.getSuiId());
			mdOrderAfterSaleEntity.setCreateName(sysUserInfo.getUserName());
			mdOrderAfterSaleEntity.setCreatePhone(sysUserInfo.getPhoneNumber());
			mdOrderAfterSaleEntity.setCreateNickname(sysUserInfo.getOrgName());
			mdOrderAfterSaleEntity.setCreateDate(new Date());
			mdOrderAfterSaleEntity.setAsState(5);
			if(listFilecode != null){
				String[] fileCodeArray = listFilecode.trim().split(",");
				if(fileCodeArray.length >= 1 && fileCodeArray[0] != null && !fileCodeArray[0].equals("")){
					mdOrderAfterSaleEntity.setAsImg1(fileCodeArray[0]);
				}
				if(fileCodeArray.length >= 2 && fileCodeArray[1] != null && !fileCodeArray[1].equals("")){
					mdOrderAfterSaleEntity.setAsImg2(fileCodeArray[1]);
				}
				if(fileCodeArray.length >= 3 && fileCodeArray[2] != null && !fileCodeArray[2].equals("")){
					mdOrderAfterSaleEntity.setAsImg3(fileCodeArray[2]);
				}
//				for(String fileCode : fileCodeArray){
//					MdEnclosure obj = new MdEnclosure();
//					obj.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
//					obj.setFileCode(fileCode);
//					mdEnclosureDao.saveOrUpdateMdEnclosure(obj);
//				}
			}

			mdOrderAfterSaleEntity = mdOrderAfterSaleDao.saveMdOrderSaleAfter(mdOrderAfterSaleEntity);
			Integer masId = mdOrderAfterSaleEntity.getMasId();

			//更新订单状态为售后
//			MdOrderInfo mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(mdOrderAfterSaleEntity.getMoiId());
//			mdOrderInfo.setOldProcessStatus(mdOrderInfo.getProcessStatus());
//			mdOrderInfo.setProcessStatus("7");
//			mdOrderInfoDao.updateMdOrderInfoById(mdOrderInfo.getMoiId(), mdOrderInfo);
			List<Map<String, Object>> list = mdOrderAfterSaleDao.getMdOrderSaleAfterMxListCountByMasId(masId, null);
			Integer count = 0;
			if(list.size() > 0){
				count = Integer.valueOf(list.get(0).get("total_count").toString());
			}

			String afterSale = "";
			if(mdOrderAfterSaleEntity.getAfterSale() == 1){
				afterSale = "退货";
			} else {
				afterSale = "退款";
			}
			if(count > 0){
				mdOrderAfterSaleDao.deleteAfterSaleMxAll(masId);
				saveMdOrderAfterSaleEx(masId, "修改为" + afterSale + "申请", sysUserInfo.getUserName() + "修改为" + afterSale + "申请", sysUserInfo.getUserName(), mdOrderAfterSaleEntity.getAfterSaleMoney());
			} else {
				saveMdOrderAfterSaleEx(masId, "创建了" + afterSale + "申请", sysUserInfo.getUserName() + "创建了" + afterSale + "申请", sysUserInfo.getUserName(), mdOrderAfterSaleEntity.getAfterSaleMoney());
			}

			if(momIds != null) {
				String[] momIdList = momIds.trim().split(",");
				MdOrderAfterSaleMxEntity mdOrderAfterSaleMxEntity = null;
				for (String momIdStr : momIdList){
					if(momIdStr.equals("")){
						continue;
					}
					Integer momId = Integer.parseInt(momIdStr);
					mdOrderAfterSaleMxEntity = new MdOrderAfterSaleMxEntity();
					mdOrderAfterSaleMxEntity.setMomId(momId);
					mdOrderAfterSaleMxEntity.setMasId(mdOrderAfterSaleEntity.getMasId());
					mdOrderAfterSaleMxEntity.setAfterSale(5);
					mdOrderAfterSaleDao.saveMdOrderSaleAfterMx(mdOrderAfterSaleMxEntity);
				}
			}
			//仅获取售后表
			sm.setObj(mdOrderAfterSaleEntity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HSKException();
		}
		return sm;
    }

    @Override
    public SysRetrunMessage getMdOrderSaleAfterByMasId(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		SysUserInfo sysUserInfo=this.getApiSessionAccount();
		try{
			Integer sui_id = sysUserInfo.getSuiId();
			mdOrderAfterSaleEntity = mdOrderAfterSaleDao.getMdOrderSaleAfter(mdOrderAfterSaleEntity);
			sm.setObj(mdOrderAfterSaleEntity);
		}catch (Exception e){
			e.printStackTrace();
		}
		return sm;
    }

	@Override
	public PagerModel getMdOrderSaleAfterMxByMasId(Integer masId, Integer limit, Integer page) throws HSKException {
		PagerModel pm = new PagerModel();
		SysUserInfo sysUserInfo=this.getApiSessionAccount();
		try{
			Integer sui_id = sysUserInfo.getSuiId();
			List<Map<String, Object>> list = mdOrderAfterSaleDao.getMdOrderSaleAfterMxList(masId, null, limit, page);
			list.add(new HashMap<String, Object>());//仅仅做一个占位
			pm.setRows(list);
			pm.setItems(list);
			pm.setTotalCount(list.size() - 1);
			pm.setTotal(list.size() - 1);
		}catch (Exception e){
			e.printStackTrace();
		}
		return pm;
	}

    @Override
    public PagerModel getMdOrderSaleAfterList() throws HSKException {
        return null;
    }

    @Override
    public PagerModel getMdOrderSaleAfterMxList(Integer masId) throws HSKException {
        return null;
    }

    @Override
    public SysRetrunMessage updateMdOrderSaleAfterState(Integer masId) throws HSKException {
        return null;
    }

    @Override
    public SysRetrunMessage updateMdOrderSaleAfterAllState(Integer masId) throws HSKException {
        return null;
    }

	@Override
	public SysRetrunMessage saveApplyASAddress(Integer masId, String expressName, String expressCode) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		SysUserInfo sysUserInfo=this.getApiSessionAccount();
		try{
			Integer sui_id = sysUserInfo.getSuiId();
			MdOrderAfterSaleEntity mas = mdOrderAfterSaleDao.getMdOrderSaleAfterByMasId(sui_id, masId);
			if(mas.getAsState() != 1){
				sm.setCode(2l);
				sm.setMeg("售后状态已改变，请刷新页面！");
				return sm;
			}
			MdOrderAfterSaleEntity mdOrderAfterSaleEntity = mdOrderAfterSaleDao.saveApplyASAddress(sui_id, masId, expressName, expressCode);

			saveMdOrderAfterSaleEx(masId, "快递公司名称：" + expressName + "  快递单号：" + expressCode, "已寄出商品",  sysUserInfo.getUserName(), mdOrderAfterSaleEntity.getAfterSaleMoney());
			sm.setObj(mdOrderAfterSaleEntity);
		}catch (Exception e){
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage deleteApplyAfterSale(Integer masId, Integer moiId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		SysUserInfo sysUserInfo=this.getApiSessionAccount();
		try{
			Integer sui_id = sysUserInfo.getSuiId();
			//取消申请
			MdOrderAfterSaleEntity as = mdOrderAfterSaleDao.getMdOrderSaleAfterByMasId(sui_id, masId);
			if(as.getAsState() == 3){
				sm.setCode(2l);
				sm.setMeg("售后已经完成，请刷新页面！");
				return sm;
			}
			if(as.getAsState() == 4){
				sm.setCode(2l);
				sm.setMeg("售后已经被拒绝，请刷新页面！");
				return sm;
			}
//			as.setAfterSale(4);
			as.setAsState(6);
			mdOrderAfterSaleDao.saveMdOrderSaleAfter(as);

			List<MdOrderAfterSaleMxEntity> mxlist = mdOrderAfterSaleDao.getMdOrderSaleASMxList(masId);
			for (MdOrderAfterSaleMxEntity mdOrderAfterSaleMxEntity : mxlist) {
				mdOrderAfterSaleMxEntity.setAfterSale(6);
				mdOrderAfterSaleDao.saveMdOrderSaleAfterMx(mdOrderAfterSaleMxEntity);
			}

//			List<MdOrderAfterSaleEntity> list = mdOrderAfterSaleDao.getMdOrderSaleAfterMoiId(sui_id, moiId);
//			if(list.size() <= 0){
//				MdOrderInfo mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
//				mdOrderInfo.setProcessStatus(mdOrderInfo.getOldProcessStatus());
//				mdOrderInfo.setOldProcessStatus(null);
//				mdOrderInfo = mdOrderInfoDao.saveOrUpdateMdOrderInfo(mdOrderInfo);
//			}

			saveMdOrderAfterSaleEx(masId, "已撤销售后申请", "", sysUserInfo.getUserName(), as.getAfterSaleMoney());
		}catch (Exception e){
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public PagerModel getOrderAfterSalePager(Integer moiId, Integer afterSale) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList<MdOrderAfterSaleEntity>());
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			pm = mdOrderAfterSaleDao.getMdOrderSaleAfterPageModelByMoiId(sysUserInfo.getSuiId(), moiId, afterSale);
			if(pm.getItems() != null && pm.getItems().size() > 0){
				List<MdOrderAfterSaleEntity> orderList = pm.getItems();
				for(MdOrderAfterSaleEntity order : orderList){
					Integer masId = order.getMasId();
					List<Map<String, Object>> addList = mdOrderAfterSaleDao.getMdOrderSaleAfterMxList(masId, null, 5, 1);
					List<Map<String, Object>> listCount = mdOrderAfterSaleDao.getMdOrderSaleAfterMxListCountByMasId(masId, null);
					Map<String, Object> map = listCount.get(0);
					addList.add(map);
					order.setAsMxList(addList);
				}
				pm.setItems(orderList);
				pm.setRows(orderList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public PagerModel getOrderAfterSaleMxModelByMasId(Integer masId, String searchName, Integer limit, Integer page) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList<Map<String, Object>>());
		try {
			List<Map<String,Object>> mxList = mdOrderAfterSaleDao.getMdOrderSaleAfterMxList(masId, searchName, limit, page);
			List<Map<String,Object>> listCount = mdOrderAfterSaleDao.getMdOrderSaleAfterMxListCountByMasId(masId, searchName);
			Map<String,Object> map = listCount.get(0);
			mxList.add(map);
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotalCount(Integer.parseInt(map.get("total_count").toString()));
			pm.setTotal(Integer.parseInt(map.get("total_count").toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public SysRetrunMessage countOrderAfterSale(Integer moiId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			Map<String,Integer> reMap =  new HashMap<String,Integer>();
			Integer suiId = sysUserInfo.getSuiId();
			Integer allCount = mdOrderAfterSaleDao.countOrderInfoByAfterSaleState(suiId, moiId, null);
			Integer thCount = mdOrderAfterSaleDao.countOrderInfoByAfterSaleState(suiId, moiId, "1");
			Integer tkCount = mdOrderAfterSaleDao.countOrderInfoByAfterSaleState(suiId, moiId, "2");
			Integer wcCount = mdOrderAfterSaleDao.countOrderInfoByAfterSaleState(suiId, moiId, "3");
			reMap.put("all", allCount);
			reMap.put("th", thCount);
			reMap.put("tk", tkCount);
			reMap.put("wc", wcCount);
			srm.setObj(reMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return srm;
	}

	@Override
	public PagerModel getMdOrderSaleAfterExPageModel(Integer masId) throws HSKException {
		PagerModel pm = new PagerModel();
		try {
			pm = mdOrderAfterSaleDao.getMdOrderSaleAfterExPageModel(masId);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public PagerModel findOrderAfterSaleBySearch(Integer moiId, String searchAsState, String processStatus2, String searchName, String placeOrderTime, String searchAsType, Integer limit, Integer page) throws HSKException {
		PagerModel model = new PagerModel(new ArrayList<MdOrderInfo>());
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			model = mdOrderAfterSaleDao.getMdOrderSaleAfterPageModelBySearch(sysUserInfo.getSuiId(), moiId, processStatus2.equals("") ? searchAsState : processStatus2, searchName, placeOrderTime,  searchAsType);
//			List<Map<String, Object>> listMasids = new ArrayList<Map<String, Object>>();
//			if(!searchAsState.equals("") || !searchName.equals("")) {
//				listMasids = mdOrderAfterSaleDao.getMdOrderSaleAfterMxListBySearch(sysUserInfo.getSuiId(), moiId, searchAsState, searchName, limit, page);
//			}
//			String masIds = "";
			List<MdOrderAfterSaleEntity> asList = model.getItems();
//			for (MdOrderAfterSaleEntity as : asList){
//				masIds += as.getMasId() + ",";
//			}
//			for (Map<String, Object> masId : listMasids) {
//				String mas_id = masId.get("mas_id").toString() + ",";
//				if (masIds.indexOf(mas_id) < 0) {
//					masIds += mas_id;
//				}
//			}
//			if(masIds.equals("")){
//				return model;
//			}
//			masIds = masIds.substring(0, masIds.length() - 1);
			List<Map<String, Object>> mxList = new ArrayList<Map<String, Object>>();//mdOrderAfterSaleDao.getMdOrderSaleAfterMxList(masIds, searchName);
//			if(asList.size() <= 0){
//				for (Map<String, Object> moiid : listMasids) {
//					asList.add(mdOrderAfterSaleDao.getMdOrderSaleAfterByMasId(sysUserInfo.getSuiId(), Integer.parseInt(moiid.get("mas_id").toString())));
//				}
//			}
			String countName = "";
			for (MdOrderAfterSaleEntity order : asList) {
//				List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
//				for (Map<String, Object> mx : mxList) {
//					if (mx.get("mas_id").toString().equals(order.getMasId() + ""))
//						addList.add(mx);
//				}
				mxList = mdOrderAfterSaleDao.getMdOrderSaleAfterMxList(order.getMasId(), searchName, limit, page);
				countName = searchName;
				if(mxList.size() <= 0){
					mxList = mdOrderAfterSaleDao.getMdOrderSaleAfterMxList(order.getMasId(), null, limit, page);
					countName = "";
				}
				List<Map<String,Object>> listCount = mdOrderAfterSaleDao.getMdOrderSaleAfterMxListCountByMasId(order.getMasId(), countName);
				Map<String,Object> map = listCount.get(0);
				mxList.add(map);
				order.setAsMxList(mxList);
			}

			model.setItems(asList);
			model.setRows(asList);

		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public SysRetrunMessage getLimitDate() throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try{
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			String limitDateMapMin = mdOrderInfoDao.getLimitDate(sysUserInfo.getSuiId(), 1);
			String limitDateMapMax = mdOrderInfoDao.getLimitDate(sysUserInfo.getSuiId(), 2);
			String[] min = limitDateMapMin.split("-");
			String minYear = min[0];
			String minMonth = min[1];
			String minDay = min[2].split(" ")[0];

			String[] max = limitDateMapMax.split("-");
			String maxYear = max[0];
			String maxMonth = max[1];
			String maxDay = max[2].split(" ")[0];

			Map<String, String> map = new HashMap<String, String>();
			map.put("minYear", minYear);
			map.put("minMonth", minMonth);
			map.put("minDay", minDay);
			map.put("maxYear", maxYear);
			map.put("maxMonth", maxMonth);
			map.put("maxDay", maxDay);
			map.put("minDate", minYear +"-" + minMonth + "-" + minDay);
			map.put("maxDate", maxYear +"-" + maxMonth + "-" + maxDay);
			sm.setObj(map);

		}catch (Exception e){
			sm.setCode(0l);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getOrderFailAfterSale(Integer moiId, Integer limit, Integer page) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			SysUserInfo userInfo = this.GetOneShoppingSessionAccount();
			MdOrderAfterSaleEntity afterSaleEntity = new MdOrderAfterSaleEntity();
			afterSaleEntity.setMoiId(moiId);
			afterSaleEntity.setSuiId(userInfo.getSuiId());
			List<Map<String, Object>> mapList = mdOrderAfterSaleDao.getMdOrderSaleAfterMxListSuccess(afterSaleEntity, null, limit, page);
			if (mapList.isEmpty()) {
				sm.setCode(0L);
				return sm;
			}
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String date = "";
			for (Map<String, Object> map : mapList) {
				date = sf.format(map.get("create_date"));
				map.put("createDate", date);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", mapList);
			Integer count = mdOrderAfterSaleDao.getMdOrderSaleASMXSuccessCount(afterSaleEntity);
			map.put("count", count);
			sm.setObj(map);
		} catch (Exception e) {
			sm.setCode(2L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getBrandImg() throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			List<Map<String, String>> list = new ArrayList<>();
			SysSwitchEntity sysSwitchEntity = this.getApiSessionSwitch(1);
			if (sysUserInfo == null && sysSwitchEntity != null && java.util.Objects.equals(sysSwitchEntity.getState(), 1)) {
				String[] brandNames = new String[3];
				brandNames[0] = "雅戈尔";
				brandNames[1] = "安踏";
				brandNames[2] = "优衣库";
				String[] imgs = new String[3];
				imgs[0] = "dentistmall/img/miniprogram/p1.jpg";
				imgs[1] = "dentistmall/img/miniprogram/p2.jpg";
				imgs[2] = "dentistmall/img/miniprogram/p3.jpg";

				Map<String, String> map;
				for (int idx = 0; idx < 3; idx++) {
					map = new HashMap<>();
					map.put("name", brandNames[idx]);
					map.put("img", imgs[idx]);
					list.add(map);
				}
			} else {
				String[] brandNames = new String[3];
				brandNames[0] = "3M";
				brandNames[1] = "登士柏";
				brandNames[2] = "麦迪康";

				String[] imgs = new String[3];
				imgs[0] = "dentistmall/img/miniprogram/brand1.jpg";
				imgs[1] = "dentistmall/img/miniprogram/brand2.jpg";
				imgs[2] = "dentistmall/img/miniprogram/brand3.jpg";

				Map<String, String> map;
				for (int idx = 0; idx < 3; idx++) {
					map = new HashMap<>();
					map.put("name", brandNames[idx]);
					map.put("img", imgs[idx]);
					list.add(map);
				}
			}
			sm.setObj(list);
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}
//	//增加已购商品
//	public 	SysRetrunMessage savePurchased(String mmfIds,String shus) throws HSKException{
//		SysRetrunMessage srm = new SysRetrunMessage(1l);
//		SysUserInfo sysUserInfo=this.getApiSessionAccount();
//		try {
//			if (mmfIds!=null&&!mmfIds.equals("")){
//				String [] arrMmfIds=mmfIds.split(",");
//				String [] arrShus=shus.split(",");
//				for (int i = 0; i <arrMmfIds.length ; i++) {
//					Integer mmfId= Integer.parseInt(arrMmfIds[i]);
//					Integer arrShu=Integer.parseInt(arrShus[i]);
//					MdCarts mdCarts=null;
//					List<MdCarts> list=mdCartsDao.findMdCartsByMmfIdHql(sysUserInfo.getSuiId(),mmfId);
//					mdCarts=list.get(0);
//					Integer count=mdCartsDao.getPurchasedMmfId(mmfId,sysUserInfo.getSuiId());
//					if (count>0){
//						MdPurchased mdPurchased=mdCartsDao.findPurchasedMmfId(sysUserInfo.getSuiId(),mmfId);
//						mdPurchased.setMcCount(arrShu+mdPurchased.getMcCount());
//						this.updateObject(mdPurchased);
//					}else {
//						MdPurchased mdPurchased=new MdPurchased();
//						mdPurchased.setWmsMiId(mdCarts.getWmsMiId());
//						mdPurchased.setSuiId(sysUserInfo.getSuiId());
//						mdPurchased.setPhState(mdCarts.getMcState());
//						mdPurchased.setMmfId(mdCarts.getMmfId());
//						mdPurchased.setMcCount(arrShu);
//						mdPurchased.setPrice(mdCarts.getPrice());
//						this.newObject(mdPurchased);
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			e.printStackTrace();
//			srm.setCode(0L);
//		}
//		return srm;
//	}
//增加已购商品
public 	SysRetrunMessage savePurchased(String mmfIds) throws HSKException{
	SysRetrunMessage srm = new SysRetrunMessage(1l);
	SysUserInfo sysUserInfo=this.getApiSessionAccount();
	try {
		if (mmfIds!=null&&!mmfIds.equals("")){
			String [] arrMmfIds=mmfIds.split(",");
			for (int i = 0; i <arrMmfIds.length ; i++) {
				Integer mmfId= Integer.parseInt(arrMmfIds[i]);
//				MdCarts mdCarts=null;
//				List<MdCarts> list=mdCartsDao.findMdCartsByMmfIdHql(sysUserInfo.getSuiId(),mmfId);
//				mdCarts=list.get(0);
				Integer count=mdCartsDao.getPurchasedMmfId(mmfId,sysUserInfo.getSuiId());
				if (count>0){
					MdPurchased mdPurchased=mdCartsDao.findPurchasedMmfId(sysUserInfo.getSuiId(),mmfId);
					mdPurchased.setMcCount(1);
					this.updateObject(mdPurchased);
				}else {
					MdPurchased mdPurchased=new MdPurchased();
//					mdPurchased.setWmsMiId(mdCarts.getWmsMiId());
					mdPurchased.setSuiId(sysUserInfo.getSuiId());
//					mdPurchased.setPhState(mdCarts.getMcState());
					mdPurchased.setMmfId(mmfId);
					mdPurchased.setMcCount(1);
//					mdPurchased.setPrice(mdCarts.getPrice());
					this.newObject(mdPurchased);
				}
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
		e.printStackTrace();
		srm.setCode(0L);
	}
	return srm;
}
	//查询已购商品
	public PagerModel getPurchasedInfo(String mmfIds,String mmfDate,String matNameAndCode,Integer limit,Integer page,Integer date1) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo sysUserInfo=this.getApiSessionAccount();
		PagerModel pm=new PagerModel();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<Map<String,Object>> mxList = mdCartsDao.getPurchasedInfoList(null,sysUserInfo.getSuiId(),matNameAndCode,limit,page,date1);
			List<Map<String,Object>> mxList1 = mdCartsDao.getPurchasedInfoList(null,sysUserInfo.getSuiId(),matNameAndCode,null,null,date1);
			Integer mxListCount1=mxList1.size();
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxListCount1);
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0L);
		}
		return pm;
	}
	//批量删除已购商品
	public SysRetrunMessage deletePurchasedInfo(String purchasedIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			if (purchasedIds != null&&!purchasedIds.equals("")) {
				String[] mbdIdArr=purchasedIds.split(",");
				for (String mbdIdStr :mbdIdArr) {
					Integer mbdId=Integer.parseInt(mbdIdStr);

					mdCartsDao.deletePurchasedInfoById(null,mbdId);
				}
			}else {
				srm.setCode(2L);
				srm.setMeg("请选择需要删除的数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0L);
			throw new HSKException(e);
		}
		return srm;
	}

	//public PagerModel getPurchasedInfo() throws HSKException{
	//		SysRetrunMessage srm = new SysRetrunMessage(1l);
	//		SysUserInfo sysUserInfo=this.getApiSessionAccount();
	//		PagerModel pm=new PagerModel();
	//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//		try {
	//			List<Map<String,Object>> mxList = mdCartsDao.getPurchasedInfo(sysUserInfo.getSuiId());
	//			if (mxList!=null&&mxList.size()>0){
	//				for (Map<String, Object> map : mxList) {
	//					Object createDate=map.get("create_date");
	//					if (createDate!=null&&!createDate.equals("")){
	//						map.put("create_date",sdf.format(createDate));
	//					}else {
	//						map.put("create_date","");
	//					}
	//					Object editDate=map.get("edit_date");
	//					if (editDate!=null&&!editDate.equals("")){
	//						map.put("edit_date",sdf.format(editDate));
	//					}else {
	//						map.put("edit_date","");
	//					}
	//				}
	//			}
	//			pm.setItems(mxList);
	//			pm.setRows(mxList);
	//			pm.setTotalCount(mxList.size());
	//		} catch (HSKDBException e) {
	//			e.printStackTrace(); }
	//		return pm;
	//	}
}
