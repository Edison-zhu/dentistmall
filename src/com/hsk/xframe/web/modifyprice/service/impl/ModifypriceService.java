package com.hsk.xframe.web.modifyprice.service.impl;

import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.daobbase.imp.MdItemKeyDao;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.IWarehousingManagementDao;
import com.hsk.xframe.api.daobbase.IorgDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.web.modifyprice.service.IModifypriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ModifypriceService extends DSTService implements IModifypriceService {
    @Autowired
    private IWarehousingManagementDao warehousingManagementDao;
    @Autowired
    private IMdOrderInfoDao mdorderInfoDao;
    @Autowired
    private IMdOrderMxDao mdOrderMxDao;
    @Autowired
    private IMdEnterWarehouseDao mdEnterWarehouseDao;
    @Autowired
    private IMdEnterWarehouseMxDao mdEnterWarehouseMxDao;
    @Autowired
    protected IorgDao orgDao;
    @Autowired
    private IMdItemKeyDao mdItemKeyDao;
    @Autowired
    private IMdInventoryDao mdInventoryDao;

    public PagerModel getWarehousingList(String warehousCode, Integer select1, String remarks, String billCode, String operationDate, Integer desc, Integer limit, Integer page,String wewId1) throws HSKException {
        PagerModel pm = new PagerModel();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            Integer rba = null;
            Integer rbs = null;
            Integer rbb = null;
            Integer suiId = null;
            String purchaseType = null;
            if (account.getOrganizaType().equals("20001")) {
                rba = account.getOldId();
                if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
                } else
                    suiId = account.getSuiId();
                purchaseType = "1";
            }
            if (account.getOrganizaType().equals("20002")) {
                rbs = account.getOldId();
                if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
                } else
                    suiId = account.getSuiId();
                purchaseType = "2";
            }
            if (account.getOrganizaType().equals("20003")) {
                rbb = account.getOldId();
                if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
                } else
                    suiId = account.getSuiId();
                purchaseType = "3";
            }
            if (wewId1!=null&&!wewId1.equals("")){
                wewId1 = wewId1.substring(0, wewId1.length() - 1);
            }
            List<Map<String, Object>> mxList = warehousingManagementDao.getWarehousingList(warehousCode, select1, remarks, billCode, operationDate, desc,
                    rba, rbs, rbb, suiId, purchaseType, limit, page,wewId1);
            if (mxList != null && mxList.size() > 0) {
                for (Map<String, Object> map : mxList) {
                    Object wewIds = map.get("wew_id");
                    map.put("wewId", wewIds);
                    Object createDate = map.get("create_date");
                    if (createDate != null && createDate != "") {
                        String createDate1 = createDate.toString();
                        map.put("createDate1", sdf.format(createDate));
                    } else {
                        map.put("createDate1", "");
                    }
                    Object Billcode1 = map.get("Billcode");
                    if (Billcode1 != null && Billcode1 != "null") {
                        map.put("Billcode", Billcode1);
                    } else {
                        map.put("Billcode", "");
                    }
                    Object billType1 = map.get("bill_type");
                    if (billType1 != null && billType1 != "null") {
                        if (billType1.equals("1")) {
                            map.put("billType", "物料入库");
                        } else if (billType1.equals("2")) {
                            map.put("billType", "订单入库");
                        }
                    } else {
                        map.put("billType", "");
                    }
                    Object warehousingRemarks1 = map.get("warehousing_remarks");
                    if (warehousingRemarks1 != null && warehousingRemarks1 != "null") {
                        map.put("warehousingRemarks", warehousingRemarks1);
                    } else {
                        map.put("warehousingRemarks", "");
                    }

                    Object purchaseMoney1 = map.get("purchase_money");
                    if (purchaseMoney1 != null && purchaseMoney1 != "null") {
                        map.put("purchaseMoney", df.format(purchaseMoney1));
                    } else {
                        map.put("purchaseMoney", "");
                    }

                    Object retailMoney1 = map.get("retail_money");
                    if (retailMoney1 != null && retailMoney1 != "null") {
                        map.put("retailMoney", df.format(retailMoney1));
                    } else {
                        map.put("retailMoney", "");
                    }
                    Object createRen1 = map.get("create_ren");
                    if (createRen1 != null && createRen1 != "null") {
                        map.put("createRen", createRen1);
                    } else {
                        map.put("createRen", "");
                    }

                    Object invoiceCode1 = map.get("invoice_code");
                    if (invoiceCode1 != null && invoiceCode1 != "null") {
                        map.put("invoiceCode", invoiceCode1);
                    } else {
                        map.put("invoiceCode", "");
                    }
                }
            }
            List<Map<String, Object>> mxList1 = warehousingManagementDao.getWarehousingList(warehousCode, select1, remarks, billCode, operationDate, desc,
                    rba, rbs, rbb, suiId, purchaseType,null, null,wewId1);
            Integer mxListCount1 = mxList1.size();
            pm.setItems(mxList);
            pm.setRows(mxList);
            pm.setTotal(mxListCount1);
            pm.setTotalCount(mxList.size());
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return pm;
    }

    public SysRetrunMessage deleteWare(Integer wewId) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdEnterWarehouseMx mdEnterWarehouseMx = new MdEnterWarehouseMx();
            mdEnterWarehouseMx.setWewId(wewId);
            List<MdInventoryExtend> list = mdEnterWarehouseMxDao.getInventoryListByWewId(mdEnterWarehouseMx);

            MdEnterWarehouse mdEnterWarehouse = mdEnterWarehouseDao.findMdEnterWarehouseById(wewId);
            MdOrderInfo mdOrderInfo = new MdOrderInfo();

            MdInventory mdInventory;
            MdOrderMx mdOrderMx;

            MdEnterWarehouseMx mdEnterWarehouseMx1 = new MdEnterWarehouseMx();
            mdEnterWarehouseMx1.setWewId(wewId);
            Double count = 0d;
            Integer moiId = null;
            List<MdEnterWarehouseMx> mxList = this.getList(mdEnterWarehouseMx1);
            for (MdEnterWarehouseMx mx : mxList) {
                count += mx.getMatNumber();
                mdOrderMx = warehousingManagementDao.findMdOrderMx(mdEnterWarehouse.getRelationBillCode(), mx.getMmfId());

                //修改订单入库数量
                if (mdOrderMx == null)
                    continue;
                moiId = mdOrderMx.getMoiId();
                mdOrderMx.setEnterNumber((mdOrderMx.getEnterNumber() - mx.getMatNumber()) < 0 ? 0 : (mdOrderMx.getEnterNumber() - mx.getMatNumber()));
                mdOrderMx.setChangeState(1);
                mdOrderMxDao.updateMdOrderMxById(mdOrderMx.getMomId(), mdOrderMx);
            }
            if (moiId != null) {
                if (mdOrderInfo == null)
                    mdOrderInfo = new MdOrderInfo();
                mdOrderInfo.setMoiId(moiId);
                mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);
                if (mdOrderInfo != null && !Objects.equals(count, 0d)) {
                    count = mdOrderInfo.getNumber3() - count;
                    mdOrderInfo.setNumber3(count < 0 ? 0 : count);
                    this.updateObject(mdOrderInfo);
                }
            }

            for (MdInventoryExtend mdInventoryExtend : list) {
                mdInventory = mdInventoryDao.findMdInventoryById(mdInventoryExtend.getWiId());

                if (mdEnterWarehouse.getRelationBillCode() != null && !mdEnterWarehouse.getRelationBillCode().equals("")) {
                    mdOrderMx = warehousingManagementDao.findMdOrderMx(mdEnterWarehouse.getRelationBillCode(), mdInventoryExtend.getLinkMmfId() == null ? mdInventoryExtend.getMmfId() : mdInventoryExtend.getLinkMmfId());
                    //修改订单入库数量
                    mdOrderMx.setEnterNumber((mdOrderMx.getEnterNumber() - mdInventoryExtend.getBaseNumber()) < 0 ? 0 : (mdOrderMx.getEnterNumber() - mdInventoryExtend.getBaseNumber()));
                    mdOrderMx.setChangeState(1);
                    mdOrderMxDao.updateMdOrderMxById(mdOrderMx.getMomId(), mdOrderMx);
                }

                //修改库存
                mdInventory.setBaseNumber((mdInventory.getBaseNumber() - mdInventoryExtend.getBaseNumber()) > 0 ? (mdInventory.getBaseNumber() -  mdInventoryExtend.getBaseNumber()) : 0);
                mdInventory.setQuantity(Math.floor(mdInventory.getBaseNumber() / mdInventory.getRatio()));

                mdInventoryDao.updateMdInventoryById(mdInventory.getWiId(), mdInventory);
                this.deleteObjects(mdInventoryExtend);
            }

            warehousingManagementDao.deleteWare(wewId);
            warehousingManagementDao.deleteEnterMx(wewId);
        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(0l);
            throw new HSKException(e);
        }
        return srm;
    }

    @Override
    public SysRetrunMessage deleteWareMx(Integer wewMxId) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdEnterWarehouseMx mdEnterWarehouseMx = new MdEnterWarehouseMx();
            mdEnterWarehouseMx.setWewMxId(wewMxId);
            mdEnterWarehouseMx = (MdEnterWarehouseMx) this.getOne(mdEnterWarehouseMx);
            if (mdEnterWarehouseMx == null) {
                srm.setCode(2L); //未找到入库详细
                return srm;
            }
            List<MdInventoryExtend> list = mdEnterWarehouseMxDao.getInventoryListByWewMxId(mdEnterWarehouseMx);

            MdEnterWarehouse mdEnterWarehouse = mdEnterWarehouseDao.findMdEnterWarehouseById(mdEnterWarehouseMx.getWewId());
            MdOrderInfo mdOrderInfo = new MdOrderInfo();
            mdOrderInfo.setBillCode(mdEnterWarehouse.getRelationBillCode());
            mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);

            MdInventory mdInventory;
            MdOrderMx mdOrderMx;
            Double count = 0d;
            Integer moiId = null;
            Double price = 0d;
            Double retailPrice = 0d;
            if (mdEnterWarehouse.getRelationBillCode() != null && !mdEnterWarehouse.getRelationBillCode().equals("")) {
                mdOrderMx = warehousingManagementDao.findMdOrderMx(mdEnterWarehouse.getRelationBillCode(), mdEnterWarehouseMx.getMmfId());
                count += mdEnterWarehouseMx.getMatNumber();
                price += mdEnterWarehouseMx.getPrice() != null ? mdEnterWarehouseMx.getPrice() : 0d;
                retailPrice += mdEnterWarehouseMx.getRetailMoney() != null ? mdEnterWarehouseMx.getRetailMoney() : 0d;
                //修改订单入库数量
                if (mdOrderMx != null) {
                    moiId = mdOrderMx.getMoiId();
                    mdOrderMx.setEnterNumber((mdOrderMx.getEnterNumber() - mdEnterWarehouseMx.getMatNumber()) < 0 ? 0 : (mdOrderMx.getEnterNumber() - mdEnterWarehouseMx.getMatNumber()));
                    mdOrderMx.setChangeState(1);
                    mdOrderMxDao.updateMdOrderMxById(mdOrderMx.getMomId(), mdOrderMx);
                }
            }
            if (moiId != null) {
                if (mdOrderInfo == null)
                    mdOrderInfo = new MdOrderInfo();
                mdOrderInfo.setMoiId(moiId);
                mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);
                if (mdOrderInfo != null && !Objects.equals(count, 0d)) {
                    count = mdOrderInfo.getNumber3() - count;
                    mdOrderInfo.setNumber3(count < 0 ? 0 : count);
                    this.updateObject(mdOrderInfo);
                }
            }
            for (MdInventoryExtend mdInventoryExtend : list) {
                mdInventory = mdInventoryDao.findMdInventoryById(mdInventoryExtend.getWiId());

                //修改库存
                mdInventory.setBaseNumber((mdInventory.getBaseNumber() - mdInventoryExtend.getBaseNumber()) > 0 ? (mdInventory.getBaseNumber() -  mdInventoryExtend.getBaseNumber()) : 0);
                mdInventory.setQuantity(Math.floor(mdInventory.getBaseNumber() / mdInventory.getRatio()));

                mdInventoryDao.updateMdInventoryById(mdInventory.getWiId(), mdInventory);
                this.deleteObjects(mdInventoryExtend);
            }
            Double purchaseMoney = mdEnterWarehouse.getPurchaseMoney();
            if (purchaseMoney != null) {
                mdEnterWarehouse.setPurchaseMoney((purchaseMoney - price <= 0 ? 0 : purchaseMoney - price));
                mdEnterWarehouse.setRetailMoney((mdEnterWarehouse.getRetailMoney() - retailPrice <= 0 ? 0 : mdEnterWarehouse.getRetailMoney() - retailPrice));
            }
            this.updateObject(mdEnterWarehouse);

//            warehousingManagementDao.deleteWare(wewId);
            warehousingManagementDao.deleteEnterMxByMxId(wewMxId);
        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(0l);
            throw  new HSKException(e);
        }
        return srm;
    }

    @Override
    public PagerModel getPagerModelEnterOrder(String gjz, String cgDate, String cgRen, Integer limit, Integer page) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdOrderInfo>());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        try {
            MdOrderInfo att_MdOrderInfo = new MdOrderInfo();
            att_MdOrderInfo.setOrderCode(gjz);
            att_MdOrderInfo.setPurchaseAccount(cgRen);
            SysUserInfo account = this.GetOneSessionAccount();
            if (account.getOrganizaType().equals("20001")) {
                att_MdOrderInfo.setRbaId(account.getOldId());
                if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
                } else
                    att_MdOrderInfo.setPurchaseId(account.getSuiId());
                att_MdOrderInfo.setPurchaseType("1");
            }
            if (account.getOrganizaType().equals("20002")) {
                att_MdOrderInfo.setRbsId(account.getOldId());
                if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
                } else
                    att_MdOrderInfo.setPurchaseId(account.getSuiId());
                att_MdOrderInfo.setPurchaseType("2");
            }
            if (account.getOrganizaType().equals("20003")) {
                att_MdOrderInfo.setRbbId(account.getOldId());
                if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
                } else
                    att_MdOrderInfo.setPurchaseId(account.getSuiId());
                att_MdOrderInfo.setPurchaseType("3");
            }
            att_MdOrderInfo.setOrderState("1");
//            pm=warehousingManagementDao.getPagerModelByEnterMdOrderInfo(att_MdOrderInfo);
            List<Map<String, Object>> mxList = warehousingManagementDao.getPagerModelByEnterMdOrderInfo(att_MdOrderInfo, cgDate, limit, page);
            if (mxList != null && mxList.size() > 0) {
                for (Map<String, Object> map : mxList) {

                    Object moiId1 = map.get("moiId");
                    map.put("moiId", moiId1);
                    Object orderCode1 = map.get("orderCode");
                    if (orderCode1 != null && orderCode1 != "null") {
                        map.put("orderCode", orderCode1);
                    } else {
                        map.put("orderCode", "");
                    }
                    Object PlaceOrderTime = map.get("PlaceOrderTime");
                    if (PlaceOrderTime != null && PlaceOrderTime != "") {
                        String PlaceOrderTime1 = PlaceOrderTime.toString();
                        map.put("PlaceOrderTime", sdf.format(PlaceOrderTime));
                    } else {
                        map.put("PlaceOrderTime", "");
                    }

                    Object placeOrderMoney1 = map.get("placeOrderMoney");
                    if (placeOrderMoney1 != null && placeOrderMoney1 != "null") {
                        map.put("placeOrderMoney", df.format(placeOrderMoney1));
                    } else {
                        map.put("placeOrderMoney", "");
                    }

                    Object commodityNumber1 = map.get("commodityNumber");
                    if (commodityNumber1 != null && commodityNumber1 != "null") {
                        map.put("commodityNumber", df2.format(commodityNumber1));
                    } else {
                        map.put("commodityNumber", "");
                    }

                    Object number21 = map.get("number2");
                    Double number2 = 0.0;
                    if (number21 != null && number21 != "null") {
                        number2 = Double.parseDouble(number21.toString());
                    } else {
                        number2 = 0.0;
                    }
                    Object enterNumber1 = map.get("enterNumber");
                    Double enterNumber = 0.0;
                    if (enterNumber1 != null && enterNumber1 != "null") {
                        enterNumber = Double.parseDouble(enterNumber1.toString());
                    } else {
                        enterNumber = 0.0;
                    }
                    map.put("enterNumber2", df2.format(number2) + "/" + df2.format(enterNumber));


                    Object applicantName1 = map.get("applicantName");
                    if (applicantName1 != null && applicantName1 != "null") {
                        map.put("applicantName", applicantName1);
                    } else {
                        map.put("applicantName", "");
                    }

                    Object purchaseAccount1 = map.get("purchaseAccount");
                    if (purchaseAccount1 != null && purchaseAccount1 != "null") {
                        map.put("purchaseAccount", purchaseAccount1);
                    } else {
                        map.put("purchaseAccount", "");
                    }
                    Object ProcessStatus1 = map.get("ProcessStatus");
                    if (ProcessStatus1 != null && ProcessStatus1 != "null") {
                        if (ProcessStatus1.equals("3")) {
                            map.put("ProcessStatus", "待收货");
                        } else if (ProcessStatus1.equals("4") || ProcessStatus1.equals("1")) {
                            map.put("ProcessStatus", "待发货");
                        } else if (ProcessStatus1.equals("2")){
                            map.put("ProcessStatus", "待付款");
                        } else if (ProcessStatus1.equals("5")){
                            map.put("ProcessStatus", "交易成功");
                        }else if (ProcessStatus1.equals("6")){
                            map.put("ProcessStatus", "交易失败");
                        }
                    } else {
                        map.put("ProcessStatus", "");
                    }

                }
            }
            List<Map<String, Object>> mxList1 = warehousingManagementDao.getPagerModelByEnterMdOrderInfo(att_MdOrderInfo, cgDate, null, null);
            Integer mxListCount1 = mxList1.size();
            pm.setItems(mxList);
            pm.setRows(mxList);
            pm.setTotal(mxListCount1);
            pm.setTotalCount(mxList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    public PagerModel getAddgetWarehousingMx(Integer moiId, Integer state1, Integer limit, Integer page) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdOrderInfo>());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        try {
            if (state1 != null && state1 == 1) {
                List<Map<String, Object>> mxList2 = warehousingManagementDao.getAddgetWarehousingMx(moiId, null, null, null);
                if (mxList2 != null && mxList2.size() > 0) {
                    for (Map<String, Object> map2 : mxList2) {
                        Object momIdObj = map2.get("momId");
                        Integer momId = Integer.parseInt(momIdObj.toString());
                        MdOrderMx mdOrderMx = mdOrderMxDao.findMdOrderMxById(momId);
                        mdOrderMx.setChangeState(null);
                        mdOrderMxDao.saveOrUpdateMdOrderMx(mdOrderMx);
                    }
                }
            }
            List<Map<String, Object>> mxList = warehousingManagementDao.getAddgetWarehousingMx(moiId, 1, limit, page);
            if (mxList != null && mxList.size() > 0) {
                for (Map<String, Object> map : mxList) {
                    Object momId = map.get("momId");
                    map.put("momId", momId);
                    Object wmsMiId = map.get("wmsMiId");
                    map.put("wmsMiId", wmsMiId);
                    Object mmfId = map.get("mmfId");
                    map.put("mmfId", mmfId);
                    Object mmtId = map.get("mmtId");
                    map.put("mmtId", mmtId);
                    Object matCode1 = map.get("matCode");
                    if (matCode1 != null && matCode1 != "null") {
                        map.put("matCode", matCode1);
                    } else {
                        map.put("matCode", "");
                    }

                    Object matName1 = map.get("matName");
                    if (matName1 != null && matName1 != "null") {
                        map.put("matName", matName1);
                    } else {
                        map.put("matName", "");
                    }

                    Object brand1 = map.get("brand");
                    if (brand1 != null && brand1 != "null") {
                        map.put("brand", brand1);
                    } else {
                        map.put("brand", "");
                    }
                    Object mmfName = map.get("mmfName");
                    if (mmfName != null && mmfName != "null") {
                        map.put("mmfName", mmfName);
                    } else {
                        map.put("mmfName", "");
                    }
                    Object mmtCode = map.get("mmtCode");
                    if (mmtCode != null && mmtCode != "null") {
                        map.put("mmtCode", mmtCode);
                    } else {
                        map.put("mmtCode", "");
                    }
                    Object BasicUnit = map.get("BasicUnit");
                    if (BasicUnit != null && BasicUnit != "null") {
                        map.put("BasicUnit", BasicUnit);
                    } else {
                        map.put("BasicUnit", "");
                    }
                    Object matNumber = map.get("matNumber");
                    if (matNumber != null && matNumber != "null") {
                        map.put("matNumber", df2.format(matNumber));
                    } else {
                        map.put("matNumber", "");
                    }

                    Object number2 = map.get("number2");
                    if (number2 != null && number2 != "null") {
                        map.put("number2", df2.format(number2));
                    } else {
                        map.put("number2", "");
                    }

                    Object productName = map.get("productName");
                    if (productName != null && productName != "null") {
                        map.put("productName", productName);
                    } else {
                        map.put("productName", "");
                    }

                    Object unitMoney = map.get("unitMoney");
                    if (unitMoney != null && unitMoney != "null") {
                        map.put("unitMoney", df.format(unitMoney));
                    } else {
                        map.put("unitMoney", "");
                    }
                    Object backPrinting = map.get("backPrinting");
                    if (backPrinting != null && backPrinting != "null") {
                        map.put("backPrinting", backPrinting);
                    } else {
                        map.put("backPrinting", "");
                    }
                }
            }
            List<Map<String, Object>> mxList1 = warehousingManagementDao.getAddgetWarehousingMx(moiId, 1, null, null);
            Integer mxListCount1 = mxList1.size();
            pm.setItems(mxList);
            pm.setRows(mxList);
            pm.setTotal(mxListCount1);
            pm.setTotalCount(mxList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    public SysRetrunMessage saveWarehousing(Integer moiIds, String warehousCode, String orderWarehous, String remarks, String billCode, Double number2s, Double lsjg) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SysUserInfo account = this.GetOneSessionAccount();
        MdEnterWarehouse att_MdEnterWarehouse = new MdEnterWarehouse();
        try {
            MdOrderInfo mdOrderInfo = mdorderInfoDao.findMdOrderInfoById(moiIds);
            //查看当前组织是否存在集团、医院
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            String orgNameStr = orgDao.getNameStr(sysOrgGx);
            Integer one = null;
            Integer two = null;
            Integer three = null;
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                att_MdEnterWarehouse.setRbaId(account.getOldId());
                att_MdEnterWarehouse.setPurchaseType("1");
                one = account.getOldId();
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    att_MdEnterWarehouse.setRbaId(Integer.parseInt(orgMap.get("one")));
                    one = Integer.parseInt(orgMap.get("one"));
                }
                att_MdEnterWarehouse.setRbsId(account.getOldId());
                att_MdEnterWarehouse.setPurchaseType("2");
                two = account.getOldId();
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    att_MdEnterWarehouse.setRbaId(Integer.parseInt(orgMap.get("one")));
                    one = Integer.parseInt(orgMap.get("one"));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
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

            att_MdEnterWarehouse.setBillcode(warehousCode);
            att_MdEnterWarehouse.setRelationBillCode(mdOrderInfo.getOrderCode());
            att_MdEnterWarehouse.setBillType(orderWarehous);
            att_MdEnterWarehouse.setConsignee(mdOrderInfo.getPurchaseAccount());
            att_MdEnterWarehouse.setTelephone(account.getPhoneNumber());
//            att_MdEnterWarehouse.setAddress();
            att_MdEnterWarehouse.setSupplierName(mdOrderInfo.getApplicantName());
            //转换时间
            String d1 = mdOrderInfo.getExpressDate();
            Date newDate1 = new Date();
            att_MdEnterWarehouse.setOrderDatetime(mdOrderInfo.getPlaceOrderTime());
            att_MdEnterWarehouse.setExpectNumber(Double.parseDouble(mdOrderInfo.getCommodityNumber().toString()));
            att_MdEnterWarehouse.setCreateRen(account.getUserName());
            att_MdEnterWarehouse.setCreateDate(new Date());

            att_MdEnterWarehouse.setEditRen(account.getUserName());
            att_MdEnterWarehouse.setEditDate(new Date());

            att_MdEnterWarehouse.setWarehousingRemarks(remarks);
            att_MdEnterWarehouse.setInvoiceCode(billCode);
            att_MdEnterWarehouse.setPurchaseMoney(mdOrderInfo.getPlaceOrderMoney());
            att_MdEnterWarehouse.setRetailMoney(lsjg);
            mdEnterWarehouseDao.saveMdEnterWarehouse(att_MdEnterWarehouse);
            mdOrderInfo.setNumber3(number2s);
            mdorderInfoDao.saveOrUpdateMdOrderInfo(mdOrderInfo);
            srm.setObj(att_MdEnterWarehouse.getWewId());
        } catch (HSKDBException e) {
            e.printStackTrace();
            srm.setCode(0l);
        }
        return srm;
    }

    //改变状态
    public SysRetrunMessage saveChangeState(Integer StateMomId) {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdOrderMx mdOrderMx = mdOrderMxDao.findMdOrderMxById(StateMomId);
            mdOrderMx.setChangeState(1);
            mdOrderMxDao.saveOrUpdateMdOrderMx(mdOrderMx);
        } catch (HSKDBException e) {
            e.printStackTrace();
            srm.setCode(0l);
        }
        return srm;
    }

    public SysRetrunMessage saveWarehousingMx(Integer m1, Integer wmsMiId, Integer mmfId, Integer mmtId, String matCode, String matName, String brand, String mmfName, String mmtCode, String basicUnit, Double matNumber, Double number2, Double rksl, String productName, Double unitMoney, Double lsj, String ph, String yxq, String backPrinting, Integer wewId2) throws Exception {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SysUserInfo account = this.GetOneSessionAccount();
        //查看当前组织是否存在集团、医院
        SysOrgGx sysOrgGx = new SysOrgGx();
        sysOrgGx.setOrgGxId(account.getOrgGxId());
        Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
        String orgNameStr = orgDao.getNameStr(sysOrgGx);
        Integer one = null;
        Integer two = null;
        Integer three = null;
        if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
            one = account.getOldId();
        } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
            if (orgMap.containsKey("one")) {//如果存在上级集团
                one = Integer.parseInt(orgMap.get("one"));
            }
            two = account.getOldId();
        } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
            if (orgMap.containsKey("one")) {//如果存在上级集团
                one = Integer.parseInt(orgMap.get("one"));
            }
            if (orgMap.containsKey("tow")) {//如果存在上级医院
                two = Integer.parseInt(orgMap.get("tow"));
            }
            three = account.getOldId();
        }

        try {
            MdOrderMx mdOrderMx = mdOrderMxDao.findMdOrderMxById(m1);
            //查询规格信息
            MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
            mdMaterielFormat.setMmfId(mmfId);
            mdMaterielFormat = (MdMaterielFormat) this.getOne(mdMaterielFormat);
            //查询商品明细信息
            MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
            mdMaterielInfo.setWmsMiId(wmsMiId);
            mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
            //查询入库信息
            MdEnterWarehouse mdEnterWarehouse = mdEnterWarehouseDao.findMdEnterWarehouseById(wewId2);
//        //保存入库明细信息
            MdEnterWarehouseMx mdEnterWarehouseMx = new MdEnterWarehouseMx();
            mdEnterWarehouseMx.setWewId(wewId2);
            mdEnterWarehouseMx.setWmsMiId(wmsMiId);
            mdEnterWarehouseMx.setMmfId(mmfId);
            mdEnterWarehouseMx.setPlaceOrderTime(mdOrderMx.getPlaceOrderTime());
            mdEnterWarehouseMx.setPurchaseUnit(mdOrderMx.getPurchaseUnit());
            mdEnterWarehouseMx.setPrice(unitMoney);
            mdEnterWarehouseMx.setMatNumber(matNumber);
            mdEnterWarehouseMx.setMatCode(mdMaterielInfo.getMatCode());
            mdEnterWarehouseMx.setMatName(mdMaterielInfo.getMatName());
            mdEnterWarehouseMx.setBasicUnit(mdMaterielInfo.getBasicUnit());
            mdEnterWarehouseMx.setNorm(mdMaterielFormat.getMmfName());
            mdEnterWarehouseMx.setMatType(mdMaterielInfo.getMatType());
            mdEnterWarehouseMx.setMatType1(mdMaterielInfo.getMatType1());
            mdEnterWarehouseMx.setMatType2(mdMaterielInfo.getMatType2());
            mdEnterWarehouseMx.setMatType3(mdMaterielInfo.getMatType3());
            mdEnterWarehouseMx.setReceiptDatetime(mdEnterWarehouse.getReceiptDatetime());
//            mdEnterWarehouseMx.setCreateRen(account.getUserName());
//            mdEnterWarehouseMx.setCreateDate(new Date());
//            mdEnterWarehouseMx.setEditRen(account.getUserName());
//            mdEnterWarehouseMx.setEditDate(new Date());
            Date yxqDate = sim.parse(yxq + " 00:00:00");
//        mdEnterWarehouseMx.setProductTime(productPTime);
            mdEnterWarehouseMx.setProductValitime(yxqDate);
            mdEnterWarehouseMx.setPackageWay(productName);
//        mdEnterWarehouseMx.setPackageWay(packasg);
//        mdEnterWarehouseMx.setProductFactory(factory);
            mdEnterWarehouseMx.setBatchCertNo(ph);
            mdEnterWarehouseMx.setRetailMoney(lsj);
//        mdOrderMx.setEnterNumber(rksl);

//增加ItemKey值
            //MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
//            mdMaterielInfo.setWmsMiId(mdOrderMx.getWmsMiId());
//            mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
//            MdEnterWarehouse mdEnterWarehouse1
            MdItemMxView mdItemMxView = new MdItemMxView();
            mdItemMxView.setRbaId(one);//从session中获取集团ID
            mdItemMxView.setRbsId(two);//从session中获取医院ID
            mdItemMxView.setRbbId(three);//从session中获取门诊ID
            mdItemMxView.setPurchaseType(mdEnterWarehouse.getPurchaseType());//从session中采购单位类型
            mdItemMxView.setMmfId(mdOrderMx.getMmfId());
            mdItemMxView.setWmsMiId(mdOrderMx.getWmsMiId());
            mdItemMxView.setMatName(mdOrderMx.getMatName());
            mdItemMxView.setMmfName(mdOrderMx.getNorm());
            mdItemMxView = mdItemKeyDao.newMdItemKey(mdItemMxView, null, null);


            //增加库存
            MdInventory att_MdInventory = new MdInventory();
            att_MdInventory.setRbaId(one);//从session中获取集团ID
            att_MdInventory.setRbsId(two);//从session中获取医院ID
            att_MdInventory.setRbbId(three);//从session中获取门诊ID
            att_MdInventory.setUnit(mdEnterWarehouseMx.getBasicUnit());
            att_MdInventory.setPurchaseType(mdEnterWarehouse.getPurchaseType());//从session中采购单位类型
            att_MdInventory.setItemKeyId(mdItemMxView.getItemKeyId() + "");
            att_MdInventory.setState("1");
            att_MdInventory.setBasicUnit(mdOrderMx.getBasicUnit());
            att_MdInventory.setQuantity(rksl);
            att_MdInventory = mdInventoryDao.insertMdInventory(att_MdInventory, mdMaterielInfo.getBasicCoefficent(), null, null);

            //保存库存明细信息
            MdInventoryExtend att_MdInventoryExtend = new MdInventoryExtend();
            att_MdInventoryExtend.setWiId(att_MdInventory.getWiId());
            att_MdInventoryExtend.setWmsMiId(mdOrderMx.getWmsMiId());
            att_MdInventoryExtend.setMmfId(mdOrderMx.getMmfId());
            att_MdInventoryExtend.setBasicUnit(att_MdInventory.getBasicUnit());
            att_MdInventoryExtend.setUnit(att_MdInventory.getUnit());
            att_MdInventoryExtend.setQuantity(rksl);
            att_MdInventoryExtend.setBaseNumber(rksl * att_MdInventory.getRatio());
            att_MdInventoryExtend.setPrice(mdOrderMx.getUnitMoney());
            att_MdInventoryExtend.setBasePrice(mdOrderMx.getUnitMoney() / att_MdInventory.getRatio());
            att_MdInventoryExtend.setRelatedCode(mdEnterWarehouse.getRelationBillCode());
            att_MdInventoryExtend.setPurchaseUser(mdEnterWarehouse.getConsignee());
            att_MdInventoryExtend.setCreateDate(new Date());
            att_MdInventoryExtend.setEditDate(new Date());

            att_MdInventoryExtend.setMatName(mdOrderMx.getMatName());
            att_MdInventoryExtend.setBasicUnit(mdOrderMx.getBasicUnit());
            att_MdInventoryExtend.setMmfName(mdOrderMx.getNorm());
            att_MdInventoryExtend.setApplicantName(mdMaterielInfo.getApplicantName());
            att_MdInventoryExtend.setMatType(mdMaterielInfo.getMatType());
            att_MdInventoryExtend.setMatType1(mdMaterielInfo.getMatType1());
            att_MdInventoryExtend.setMatType2(mdMaterielInfo.getMatType2());
            att_MdInventoryExtend.setMatType3(mdMaterielInfo.getMdWmsMiId() + "");
            /**
             * yanglei
             * 修改字段ProductNumber 改为ProductFactory get这个字段时用到
             */
            att_MdInventoryExtend.setProductName(mdMaterielInfo.getProductFactory());
            att_MdInventoryExtend.setBrand(mdMaterielInfo.getBrand());
            att_MdInventoryExtend.setState("1");
            this.newObject(att_MdInventoryExtend);
            //修改订单明细入库确认数量
            mdOrderMx.setEnterNumber(mdOrderMx.getEnterNumber() + rksl);
            this.updateObject(mdOrderMx);


            this.newObject(mdEnterWarehouseMx);
        } catch (HSKDBException e) {
            e.printStackTrace();
            srm.setCode(0l);
        }
        return srm;
    }

    public SysRetrunMessage saveWarehousingMx1(String wewId2, String yxq) throws Exception {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        System.out.println(wewId2);
        return srm;
    }

}
