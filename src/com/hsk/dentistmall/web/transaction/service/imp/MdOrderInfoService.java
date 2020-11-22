package com.hsk.dentistmall.web.transaction.service.imp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import freemarker.template.SimpleDate;
import org.apache.commons.collections.Bag;
import org.apache.commons.collections.HashBag;
import org.apache.derby.tools.sysinfo;
import org.drools.lang.DRLParser.in_key_return;
import org.drools.lang.dsl.DSLMapParser.mapping_file_return;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;

import sun.org.mozilla.javascript.internal.Undefined;

import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.dentistmall.web.transaction.service.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

import freemarker.template.Configuration;
import freemarker.template.Template;


/**
 * transaction业务操作实现类
 *
 * @author 作者:admin
 * @version 版本信息:v1.0   创建时间: 2017-09-25 14:16:59
 */

@Service
public class MdOrderInfoService extends DSTService implements IMdOrderInfoService {
    /**
     * 业务处理dao类  mdOrderInfoDao
     */
    @Autowired
    protected IMdOrderInfoDao mdOrderInfoDao;

    @Autowired
    protected IMdSupplierDao mdSupplierDao;

    @Autowired
    protected IMdOrderMxDao mdOrderMxDao;
    @Autowired
    protected IMdNewsInfoDao mdNewsInfoDao;
    @Autowired
    protected IMdNoBuyDao mdNoBuyDao;
    //增加供应商首页数据 DAO
    @Autowired
    protected  IHomePageSupplierDao homePageSupplierDao;
    /**
     * 2019-11-24
     * yanglei
     * 添加物料信息表中数据 --packageWay
     */
    @Autowired
    protected IMdMaterielInfoDao mdMaterielInfoDao;
    /**
     * 2019-11-24
     * 添加物料入库表中数据
     */
    @Autowired
    protected IMdEnterWarehouseMxDao mdEnterWarehouseMxDao;
    @Autowired
    private IMdOrderAfterSaleDao mdOrderAfterSaleDao;
    @Autowired
    protected IMdEnterWarehouseDao mdEnterWarehouseDao;

    /**
     * 根据md_order_info表主键删除MdOrderInfo对象记录
     *
     * @param moiId Integer类型(md_order_info表主键)
     * @return SysRetrunMessage 对象记录
     * @throws HSKException
     */

    public SysRetrunMessage findFormObject(Integer moiId) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);

        try {
            MdOrderInfo att_MdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            srm.setObj(att_MdOrderInfo);
        } catch (HSKDBException e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg(e.getMessage());
        }
        return srm;
    }

    /**
     * 根据md_order_info表主键删除MdOrderInfo对象记录
     *
     * @param moiId Integer类型(md_order_info表主键)
     * @throws HSKException
     */

    public MdOrderInfo doFindObject(Integer moiId) throws HSKException {
        MdOrderInfo att_MdOrderInfo = new MdOrderInfo();
        try {
            att_MdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            //加入消息已查看
            MdNewsInfo mdNewsInfo = new MdNewsInfo();
            SysUserInfo account = this.GetOneSessionAccount();

            Integer suiId = null;
            if (account.getOrganizaType().equals("100")) {
                suiId = account.getOldId();
            }if(account.getOrganizaType().equals("0")){
                suiId = account.getOldId();
            }
            if(mdOrderInfoDao.countOrderInfoHasAfterSaleTwo(suiId, moiId) > 0){
                att_MdOrderInfo.setAfterSale(1);
            } else {
                att_MdOrderInfo.setAfterSale(0);
            }
            String expressNames = att_MdOrderInfo.getExpressName();
            String expressCodes = att_MdOrderInfo.getExpressCode();

            List<String> expressNameList = new ArrayList<String>();
            List<String> expressCodeList = new ArrayList<String>();
            String expressNameAndCode = "";
            if(expressNames != null && expressNames.indexOf(",") > 0){
                String[] names = expressNames.split(",");
                for (String name : names){
                    expressNameList.add(name);
                }
            }
            if(expressCodes != null && expressCodes.indexOf(",") > 0){
                String[] codes = expressCodes.split(",");
                for (String code : codes){
                    expressCodeList.add(code);
                }
            }
            if(expressNameList.size() > 0) {
                for (int i = 0; i < expressNameList.size(); i++) {
                    expressNameAndCode += expressNameList.get(i) + "#" + expressCodeList.get(i) + ",";
                }
            }
            if (!expressNameAndCode.equals("")) {
                expressNameAndCode = expressNameAndCode.substring(0, expressNameAndCode.length() - 1);
                att_MdOrderInfo.setExpressNameAndCode(expressNameAndCode);
            }

            if (account.getOrganizaType().equals("20001") || account.getOrganizaType().equals("20002") || account.getOrganizaType().equals("20003")) {
                if (account.getUserRole().contains("2")) {
                    mdNewsInfo.setUiId(account.getSuiId());
                    mdNewsInfo.setUiType(2);

                }
                if (account.getUserRole().contains("3")) {
                    mdNewsInfo.setUiId(account.getOldId());
                    mdNewsInfo.setNewsType(8);
                    if (account.getOrganizaType().equals("20001")) {
                        mdNewsInfo.setUiType(4);
                    } else if (account.getOrganizaType().equals("20002")) {
                        mdNewsInfo.setUiType(5);
                    } else if (account.getOrganizaType().equals("20003")) {
                        mdNewsInfo.setUiType(6);
                    }
                }
            } else if (account.getOrganizaType().equals("100")) {
                mdNewsInfo.setUiId(account.getOldId());
                mdNewsInfo.setUiType(1);
            }
            mdNewsInfo.setOrderId(moiId);
            mdNewsInfoDao.updateMdNewsInfoViewState(mdNewsInfo);
        } catch (HSKDBException e) {
            e.printStackTrace();
            throw new HSKException(e);
        }
        return att_MdOrderInfo;
    }

    @Override
    public MdOrderInfo doFindObject2(Integer moiId, SysUserInfo account)
            throws HSKException {
        MdOrderInfo att_MdOrderInfo = new MdOrderInfo();
        try {
            att_MdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            if(mdOrderInfoDao.countOrderInfoHasAfterSale(account.getSuiId(), moiId) > 0){
                att_MdOrderInfo.setAfterSale(1);
            } else {
                att_MdOrderInfo.setAfterSale(0);
            }

            String expressNames = att_MdOrderInfo.getExpressName();
            String expressCodes = att_MdOrderInfo.getExpressCode();

            List<String> expressNameList = new ArrayList<String>();
            List<String> expressCodeList = new ArrayList<String>();
            String expressNameAndCode = "";
            if(expressNames != null && expressNames.indexOf(",") > 0){
                String[] names = expressNames.split(",");
                for (String name : names){
                    expressNameList.add(name);
                }
            }
            if(expressCodes != null && expressCodes.indexOf(",") > 0){
                String[] codes = expressCodes.split(",");
                for (String code : codes){
                    expressCodeList.add(code);
                }
            }
            if(expressNameList.size() > 0) {
                for (int i = 0; i < expressNameList.size(); i++) {
                    expressNameAndCode += expressNameList.get(i) + "#" + expressCodeList.get(i) + ",";
                }
            }
            if (!expressNameAndCode.equals("")) {
                expressNameAndCode = expressNameAndCode.substring(0, expressNameAndCode.length() - 1);
                att_MdOrderInfo.setExpressNameAndCode(expressNameAndCode);
            }

            //加入消息已查看
            MdNewsInfo mdNewsInfo = new MdNewsInfo();
            if (account.getOrganizaType().equals("20001") || account.getOrganizaType().equals("20002") || account.getOrganizaType().equals("20003")) {
                mdNewsInfo.setUiId(account.getSuiId());
                mdNewsInfo.setUiType(2);
            } else if (account.getOrganizaType().equals("100")) {
                mdNewsInfo.setUiId(account.getOldId());
                mdNewsInfo.setUiType(1);
            }
            mdNewsInfo.setOrderId(moiId);
            mdNewsInfoDao.updateMdNewsInfoViewState(mdNewsInfo);
        } catch (HSKDBException e) {
            e.printStackTrace();
            throw new HSKException(e);
        }
        return att_MdOrderInfo;
    }

    /**
     * 根据md_order_info表主键删除MdOrderInfo对象记录
     *
     * @param moiId Integer类型(md_order_info表主键)
     * @return SysRetrunMessage 对象记录
     * @throws HSKException
     */
    public SysRetrunMessage deleteObject(Integer moiId) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            mdOrderInfoDao.deleteMdOrderInfoById(moiId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HSKException(e);
        }
        return srm;
    }

    /**
     * 根据md_order_info表主键删除多条MdOrderInfo对象记录
     *
     * @param MoiIds Integer类型(md_order_info表主键)
     * @return SysRetrunMessage 对象记录
     * @throws HSKException
     */
    public SysRetrunMessage deleteAllObject(String moiIds) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            String[] ids = moiIds.split(",");
            for (String did : ids) {
                if (did != null && !"".equals(did.trim())) {
                    mdOrderInfoDao.deleteMdOrderInfoById(Integer
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
     * 新增或修改md_order_info表记录 ,如果md_order_info表主键MdOrderInfo.MoiId为空就是添加，如果非空就是修改
     *
     * @param att_MdOrderInfo MdOrderInfo类型(md_order_info表记录)
     * @return MdOrderInfo  修改后的MdOrderInfo对象记录
     * @throws HSKDBException
     */
    public SysRetrunMessage saveOrUpdateObject(MdOrderInfo att_MdOrderInfo) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            mdOrderInfoDao.saveOrUpdateMdOrderInfo(att_MdOrderInfo);
            srm.setObj(att_MdOrderInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HSKException(e);
        }
        return srm;
    }

    public SysRetrunMessage  saveZhaiYao(Integer moiId, String test) throws HSKException{
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdOrderInfo att_MdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            att_MdOrderInfo.setEnterpriseType(test);
            mdOrderInfoDao.saveOrUpdateMdOrderInfo(att_MdOrderInfo);
            srm.setObj(att_MdOrderInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HSKException(e);
        }
        return srm;
    }

    @Override
    public SysRetrunMessage updateOrderMoney(Integer moiId, String placeOrderMoney, String exoressMoney, String saleMoney) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdOrderInfo mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            if(!mdOrderInfo.getProcessStatus().equals("2")){
                srm.setCode(2l);
                srm.setMeg("订单状态已改变，请刷新页面");
                return srm;
            }
            Double placeOrderMoney1 = mdOrderInfo.getPlaceOrderMoney() == null ? 0d : mdOrderInfo.getPlaceOrderMoney();
            Double orderExpressMoney = mdOrderInfo.getMoney2() == null ? 0d : mdOrderInfo.getMoney2();
            Double sMoney = mdOrderInfo.getMoney3() == null ? 0d : mdOrderInfo.getMoney3();
            placeOrderMoney1 -= (orderExpressMoney + sMoney);

            Double orderMoney = 0d;
            Double expressMoney = 0d;
            Double s_Money = 0d;

            if(exoressMoney != null && !exoressMoney.equals("")){
                expressMoney = Double.parseDouble(exoressMoney);
            }
            if(placeOrderMoney != null && !placeOrderMoney.equals("")){
                orderMoney = Double.parseDouble(placeOrderMoney);
            }
            if(saleMoney != null && !saleMoney.equals("")){
                s_Money = Double.parseDouble(saleMoney);
            }

            if(orderMoney.toString().equals(placeOrderMoney1.toString()) && expressMoney.toString().equals(orderExpressMoney.toString()) && s_Money.toString().equals(sMoney.toString())){
                srm.setCode(3l);
                srm.setMeg("数据并未发生更改，请确认");
                return srm;
            }

            if(orderExpressMoney != expressMoney){
                mdOrderInfo.setMoney2(expressMoney);
            }
            if(sMoney != s_Money){
                mdOrderInfo.setMoney3(s_Money);
            }
            Double allMoney = orderMoney + expressMoney - s_Money;
            if(allMoney <= 0d){
                allMoney = 0d;
            }
            mdOrderInfo.setPlaceOrderMoney(allMoney);
            mdOrderInfoDao.updateMdOrderInfoById(moiId, mdOrderInfo);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("moiId", mdOrderInfo.getMoiId());
            map.put("placeOrderMoney", mdOrderInfo.getPlaceOrderMoney());
            map.put("orderExpressMoney", mdOrderInfo.getMoney2());
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HSKException(e);
        }
        return srm;
    }

//    private PagerModel getOrderPagerByOrderInfo() throws HSKException {
//        PagerModel model = new PagerModel();
//        try {
//            model = mdOrderInfoDao.getOrderInfoListByAfterSale(this.get().getSuiId());
//            if(model.getItems() != null && model.getItems().size() > 0) {
//                String moiIds = "";
//                List<MdOrderInfoAfterSaleViewEntity> orderList = model.getItems();
//                for (MdOrderInfoAfterSaleViewEntity order : orderList) {
//                    List<Map<String, Object>> addList = mdOrderMxDao.getMxListModelByMoiId(order.getMoiId(), 5, 1);
//                    List<Map<String, Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(order.getMoiId());
//                    Map<String, Object> map = listCount.get(0);
//                    addList.add(map);
//                    order.setOrderMxList(addList);
//                }
//                model.setItems(orderList);
//                model.setRows(orderList);
//            }
//        } catch (HSKDBException e) {
//            e.printStackTrace();
//        }
//        return model;
//    }

    private PagerModel getPagerModelIncludeAsObject(MdOrderInfo att_MdOrderInfo) throws HSKException{
        PagerModel pm = new PagerModel(new ArrayList<MdOrderInfoAfterSaleViewEntity>());
        try{
            pm = mdOrderInfoDao.getPagerModelIncludeAsByMdOrderInfo(att_MdOrderInfo);

            List<MdOrderInfoAfterSaleViewEntity> orderList = pm.getItems();

            //此处开始进行调济安查询
            String moiIds = "";
            List<Map<String, Object>> mxList = new ArrayList<Map<String, Object>>();
//            List<Map<String, Object>> listMoiids = new ArrayList<Map<String, Object>>();
//            if((att_MdOrderInfo.getOrderCode() != null && !att_MdOrderInfo.getOrderCode().equals("")) || (att_MdOrderInfo.getProcessStatus() != null && !att_MdOrderInfo.getProcessStatus().equals(""))) {
//                listMoiids = mdOrderInfoDao.findOderByMx(null, att_MdOrderInfo.getProcessStatus(), att_MdOrderInfo.getOrderCode(), 5, 1);
//                for (MdOrderInfoAfterSaleViewEntity order : orderList)
//                    moiIds += order.getMoiId() + ",";
//                for (Map<String, Object> moiid : listMoiids) {
//                    String moi_id = moiid.get("moi_id").toString() + ",";
//                    if (moiIds.indexOf(moi_id) < 0) {
//                        moiIds += moi_id;
//                    }
//                }
//                if(moiIds.length() > 0) {
//                    moiIds = moiIds.substring(0, moiIds.length() - 1);
//                }
//                mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(moiIds, att_MdOrderInfo.getOrderCode(), 5, 1);
//                if(orderList.size() <= 0){
//                    for (Map<String, Object> moiid : listMoiids) {
//                        orderList.add(mdOrderInfoDao.findMdOrderInfoIncludeAsById(Integer.parseInt(moiid.get("moi_id").toString())));
//                    }
//                }
//            }

            //对数据进行整合
            Integer size = mxList.size();
            String countName = "";
            for (MdOrderInfoAfterSaleViewEntity order : orderList) {
//                List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
//                if(size <= 0){
//                    addList = mdOrderMxDao.getMxListModelByMoiId(order.getMoiId(), null, 5, 1);
//                    List<Map<String,Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(order.getMoiId(), null);
//                    Map<String,Object> map = listCount.get(0);
//                    addList.add(map);
//                }else {
//                    for (Map<String, Object> mx : mxList) {
//                        if (mx.get("moi_id").toString().equals(order.getMoiId() + ""))
//                            addList.add(mx);
//                    }
//                    addList.add(new HashMap<String, Object>());
//                }

                List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
                mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(order.getMoiId().toString(), att_MdOrderInfo.getOrderCode(), 5, 1);
                countName = att_MdOrderInfo.getOrderCode();
                if(mxList.size() <= 0){
                    mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(order.getMoiId().toString(), null, 5, 1);
                    countName = "";
                }
                List<Map<String, Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(order.getMoiId(), countName);
                Map<String, Object> map = listCount.get(0);
                mxList.add(map);
                order.setOrderMxList(mxList);
            }

            pm.setItems(orderList);
            pm.setRows(orderList);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    /**
     * 根据MdOrderInfo对象作为对(md_order_info表进行查询，获取分页对象
     *
     * @param att_MdOrderInfo MdOrderInfo类型(md_order_info表记录)
     * @return PagerModel  分页对象
     * @throws HSKException
     */
    public PagerModel getPagerModelObject(MdOrderInfo att_MdOrderInfo) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdOrderInfo>());
        try {
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
            } else if (account.getOrganizaType().equals("100"))
                att_MdOrderInfo.setWzId(account.getOldId());
            att_MdOrderInfo.setOrderState("1");
//            if (att_MdOrderInfo.getPay_type_str() != null && att_MdOrderInfo.getPay_type_str().equals("支付宝")) {
//                att_MdOrderInfo.setPay_type_str("1");
//            } else if (att_MdOrderInfo.getPay_type_str() != null && att_MdOrderInfo.getPay_type_str().equals("微信")) {
//                att_MdOrderInfo.setPay_type_str("2");
//            } else if (att_MdOrderInfo.getPay_type_str() != null && att_MdOrderInfo.getPay_type_str().equals("月结")) {
//                att_MdOrderInfo.setPay_type_str("3");
//            }
            att_MdOrderInfo.setPay_type_str("");
            att_MdOrderInfo.setTab_like("applicantName,purchaseUnit,orderCode");
            att_MdOrderInfo.setTab_order("placeOrderTime desc");
//            att_MdOrderInfo.setProcessStatus(att_MdOrderInfo.getProcessStatus().replace("7,", ""));
            int index = att_MdOrderInfo.getProcessStatus() != null ? att_MdOrderInfo.getProcessStatus().indexOf(",") : -1;
            if (index >= 0) {
                att_MdOrderInfo.setProcessStatus_str(att_MdOrderInfo.getProcessStatus());
                att_MdOrderInfo.setProcessStatus(null);
            }
            if(att_MdOrderInfo.getProcessStatus_str() != null && att_MdOrderInfo.getProcessStatus_str().equals("7")){
                return getPagerModelIncludeAsObject(att_MdOrderInfo);
            }
            pm = mdOrderInfoDao.getPagerModelByMdOrderInfo(att_MdOrderInfo);
//            String moiIds = "";
            List<MdOrderInfo> orderList = pm.getItems();

            //此处开始进行调济安查询
            String moiIds = "";
            List<Map<String, Object>> mxList = new ArrayList<Map<String, Object>>();
//            List<Map<String, Object>> listMoiids = new ArrayList<Map<String, Object>>();
//            if((att_MdOrderInfo.getOrderCode() != null && !att_MdOrderInfo.getOrderCode().equals("")) || (att_MdOrderInfo.getProcessStatus() != null && !att_MdOrderInfo.getProcessStatus().equals(""))) {
//                listMoiids = mdOrderInfoDao.findOderByMx(null, att_MdOrderInfo.getProcessStatus(), att_MdOrderInfo.getOrderCode(), 5, 1);
//                for (MdOrderInfo order : orderList)
//                    moiIds += order.getMoiId() + ",";
//                for (Map<String, Object> moiid : listMoiids) {
//                    String moi_id = moiid.get("moi_id").toString() + ",";
//                    if (moiIds.indexOf(moi_id) < 0) {
//                        moiIds += moi_id;
//                    }
//                }
//                if(moiIds.length() > 0) {
//                    moiIds = moiIds.substring(0, moiIds.length() - 1);
//                }
//                mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(moiIds, att_MdOrderInfo.getOrderCode(), 5, 1);
//                if(orderList.size() <= 0){
//                    for (Map<String, Object> moiid : listMoiids) {
//                        orderList.add(mdOrderInfoDao.findMdOrderInfoById(Integer.parseInt(moiid.get("moi_id").toString())));
//                    }
//                }
//            }

            //对数据进行整合
            Integer size = mxList.size();
            String countName = "";
            for (MdOrderInfo order : orderList) {
                List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
                mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(order.getMoiId().toString(), att_MdOrderInfo.getOrderCode(), 5, 1);
                countName = att_MdOrderInfo.getOrderCode();
                if(mxList.size() <= 0){
                    mxList = mdOrderMxDao.getMxListByMoiIdsAndSearch(order.getMoiId().toString(), null, 5, 1);
                    countName = "";
                }
                List<Map<String, Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(order.getMoiId(), countName);
                Map<String, Object> map = listCount.get(0);
                mxList.add(map);
//                if(size <= 0){
//                    addList = mdOrderMxDao.getMxListModelByMoiId(order.getMoiId(), 5, 1);
//                    List<Map<String,Object>> listCount = mdOrderMxDao.getMxListModelByMoiIdCount(order.getMoiId());
//                    Map<String,Object> map = listCount.get(0);
//                    addList.add(map);
//                }else {
//                    for (Map<String, Object> mx : mxList) {
//                        if (mx.get("moi_id").toString().equals(order.getMoiId() + ""))
//                            addList.add(mx);
//                    }
//                    addList.add(new HashMap<String, Object>());
//                }

                order.setOrderMxList(mxList);
            }
//            if (moiIds != null && moiIds.length() > 0) {
//                moiIds = moiIds.substring(0, moiIds.length() - 1);
//            } else {
//                System.out.println("截取错误");
                //pm=mdOrderInfoDao.getPagerModelByMdOrderInfo(att_MdOrderInfo);
//            }
//            List<Map<String, Object>> mxList = mdOrderMxDao.getMxListByMoiIds(moiIds);
//            for (MdOrderInfo order : orderList) {
//                List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
//                for (Map<String, Object> mx : mxList) {
//                    if (mx.get("moi_id").toString().equals(order.getMoiId() + ""))
//                        addList.add(mx);
//                }
//
//            }
            pm.setItems(orderList);
            pm.setRows(orderList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    public PagerModel getPagerModelObject2 (MdOrderInfo att_MdOrderInfo) throws HSKException{
        PagerModel pm=new PagerModel(new ArrayList<MdOrderInfo>());
        try{
            SysUserInfo account = this.GetOneSessionAccount();
            if(account.getOrganizaType().equals("20001")){
                att_MdOrderInfo.setRbaId(account.getOldId());
                if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
                }else
                    att_MdOrderInfo.setPurchaseId(account.getSuiId());
                att_MdOrderInfo.setPurchaseType("1");
            }if(account.getOrganizaType().equals("20002")){
                att_MdOrderInfo.setRbsId(account.getOldId());
                if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
                }else
                    att_MdOrderInfo.setPurchaseId(account.getSuiId());
                att_MdOrderInfo.setPurchaseType("2");
            }if(account.getOrganizaType().equals("20003")){
                att_MdOrderInfo.setRbbId(account.getOldId());
                if(account.getUserRole().equals("1")){//当拥有管理员角色时查询所有订单
                }else
                    att_MdOrderInfo.setPurchaseId(account.getSuiId());
                att_MdOrderInfo.setPurchaseType("3");
            }else if(account.getOrganizaType().equals("100"))
                att_MdOrderInfo.setWzId(account.getOldId());
            att_MdOrderInfo.setOrderState("1");
            att_MdOrderInfo.setTab_like("applicantName,purchaseUnit,orderCode");
            att_MdOrderInfo.setTab_order("placeOrderTime desc");
            pm=mdOrderInfoDao.getPagerModelByMdOrderInfo(att_MdOrderInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getPagerModelObjectForCk(MdOrderInfo att_MdOrderInfo)
            throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdOrderInfo>());
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account.getOrganizaType().equals("20001")) {
                att_MdOrderInfo.setRbaId(account.getOldId());
                att_MdOrderInfo.setPurchaseType("1");
            }
            if (account.getOrganizaType().equals("20002")) {
                att_MdOrderInfo.setRbsId(account.getOldId());
                att_MdOrderInfo.setPurchaseType("2");
            }
            if (account.getOrganizaType().equals("20003")) {
                att_MdOrderInfo.setRbbId(account.getOldId());
                att_MdOrderInfo.setPurchaseType("3");
            }
            att_MdOrderInfo.setOrderState("1");
            att_MdOrderInfo.setTab_like("applicantName,purchaseUnit,orderCode,expressCode");
            att_MdOrderInfo.setTab_order("placeOrderTime desc");
            pm = mdOrderInfoDao.getPagerModelByMdOrderInfo(att_MdOrderInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage getMdOrderInfo(Integer moiId) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        Map<String, Object> reMap = new HashMap<String, Object>();
        try {
            MdOrderInfo mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            reMap.put("mdOrderInfo", mdOrderInfo);
            MdSupplier mdSupplier = mdSupplierDao.findMdSupplierById(mdOrderInfo.getWzId());
            reMap.put("mdSupplier", mdSupplier);
            MdOrderMx mdOrderMx = new MdOrderMx();
            mdOrderMx.setMoiId(moiId);
            List<MdOrderMx> mxList = mdOrderMxDao.getListByMdOrderMx(mdOrderMx);
            reMap.put("mxList", mxList);
            srm.setObj(reMap);
        } catch (HSKDBException e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("查询失败!");
        }
        return srm;
    }

    @Override
    public SysRetrunMessage updateOrderInfo(Integer moiId, Double money3, String momIds, String prices, String test) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdOrderInfo mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            if (mdOrderInfo != null && mdOrderInfo.getProcessStatus().equals("1")) {
                MdOrderMx mdOrderMx = new MdOrderMx();
                mdOrderMx.setMoiId(moiId);
                List<MdOrderMx> mxList = mdOrderMxDao.getListByMdOrderMx(mdOrderMx);
                String[] momIdArray = momIds.split(",");
                String[] priceArray = prices.split(",");
                Double countMoney = 0d;
                if (mxList != null && mxList.size() > 0) {
                    for (MdOrderMx mx : mxList) {
                        for (int i = 0; i < momIdArray.length; i++) {
                            if (momIdArray[i].equals(mx.getMomId() + "")) {
                                Double price = Double.parseDouble(priceArray[i]);
                                mx.setUnitMoney(price);
                                mx.setTotalMoney(price * mx.getMatNumber());
                                break;
                            }
                        }
                        mdOrderMxDao.saveOrUpdateMdOrderMx(mx);
                        countMoney += mx.getTotalMoney();
                    }
                }
                mdOrderInfo.setMoney3(money3);
                mdOrderInfo.setEnterpriseType(test);
                mdOrderInfo.setPlaceOrderMoney(countMoney + (mdOrderInfo.getMoney2() != null ? mdOrderInfo.getMoney2() : 0d) - (mdOrderInfo.getMoney3() != null ? mdOrderInfo.getMoney3() : 0d));
                this.updateObject(mdOrderInfo);
            } else {
                srm.setCode(0l);
                srm.setMeg("操作失败,订单已发货!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("操作失败!");
            throw new HSKException(e);
        }
        return srm;
    }

    @Override
    public SysRetrunMessage saveSendOrderInfo(Integer moiId, String expressName, String expressCode, String momIds, String shus, String test) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        if(shus == null || shus.equals("")){
            srm.setCode(2l);
            srm.setMeg("发货数量不能全为0！");
            return srm;
        }
        try {
            MdOrderInfo mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            if (mdOrderInfo != null && mdOrderInfo.getProcessStatus().equals("1") || mdOrderInfo.getProcessStatus().equals("2") || mdOrderInfo.getProcessStatus().equals("4")) {
                MdOrderMx mdOrderMx = new MdOrderMx();
                mdOrderMx.setMoiId(moiId);
                List<MdOrderMx> mxList = mdOrderMxDao.getListByMdOrderMx(mdOrderMx);
                String[] momIdArray = momIds.split(",");
                String[] shuArray = shus.split(",");
                Double count = 0d;
                Boolean needAuto = false;
                if (mxList != null && mxList.size() > 0) {
                    for (MdOrderMx mx : mxList) {
                        Double sendNumber = mx.getNumber2() != null ? mx.getNumber2() : 0d;
                        for (int i = 0; i < momIdArray.length; i++) {
                            Double shu = Double.parseDouble(shuArray[i]);
                            if (momIdArray[i].equals(mx.getMomId() + "")) {
                                if(shu <= 0d){
                                    needAuto = false;
                                    break;
                                }
                                needAuto = false;
                                mx.setNumber2(shu + sendNumber);
                                break;
                            } else {
                                needAuto = true;
                            }
                        }
                        if(needAuto && sendNumber < mx.getMatNumber()){
                            mx.setNumber2(mx.getMatNumber());
                        }
                        mdOrderMxDao.saveOrUpdateMdOrderMx(mx);
                        count += (mx.getNumber2() != null ? mx.getNumber2() : 0d);
                    }
                }
                mdOrderInfo.setEnterpriseType(test);
                if (mdOrderInfo.getExpressName() != null) {
                    expressName = expressName + "," + mdOrderInfo.getExpressName();
                }
                mdOrderInfo.setExpressName(expressName);
                if (mdOrderInfo.getExpressCode() != null) {
                    expressCode = expressCode + "," + mdOrderInfo.getExpressCode();
                }
                mdOrderInfo.setExpressCode(expressCode);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (mdOrderInfo.getExpressDate() != null) {
                    mdOrderInfo.setExpressDate(sdf.format(new Date()) + "," + mdOrderInfo.getExpressDate());
                } else {
                    mdOrderInfo.setExpressDate(sdf.format(new Date()));
                }

                mdOrderInfo.setNumber2(count);
                if (count < mdOrderInfo.getCommodityNumber())//如果发货数量小于订单数量，则状态为部分发货
                    mdOrderInfo.setProcessStatus("4");
                else if (count >= mdOrderInfo.getCommodityNumber())//如果发货数量等于订单数量，则状态为待收货
                    mdOrderInfo.setProcessStatus("3");
                this.updateObject(mdOrderInfo);
                //消息提醒,提醒采购人订单已发货
                MdNewsInfo mdNewsInfo = new MdNewsInfo();
                mdNewsInfo.setOrderId(mdOrderInfo.getMoiId());
                mdNewsInfo.setUiId(mdOrderInfo.getPurchaseId());
                mdNewsInfo.setUiType(2);
                mdNewsInfo.setNewsType(3);//订单发货
                mdNewsInfo.setIsView(1);
                mdNewsInfo.setCreateDate(new Date());
                mdNewsInfo.setContent("订单 " + mdOrderInfo.getOrderCode() + "已发货 请查看！");
                //消息提醒仓管
                MdNewsInfo mdNewsInfo2 = new MdNewsInfo();
                mdNewsInfo2.setOrderId(mdOrderInfo.getMoiId());
                if (mdOrderInfo.getPurchaseType().equals("1")) {
                    mdNewsInfo2.setUiId(mdOrderInfo.getRbaId());
                    mdNewsInfo2.setUiType(4);
                } else if (mdOrderInfo.getPurchaseType().equals("2")) {
                    mdNewsInfo2.setUiId(mdOrderInfo.getRbsId());
                    mdNewsInfo2.setUiType(5);
                } else if (mdOrderInfo.getPurchaseType().equals("3")) {
                    mdNewsInfo2.setUiId(mdOrderInfo.getRbbId());
                    mdNewsInfo2.setUiType(6);
                }
                mdNewsInfo2.setNewsType(8);//订单发货
                mdNewsInfo2.setIsView(1);
                mdNewsInfo2.setCreateDate(new Date());
                mdNewsInfo2.setContent("订单 " + mdOrderInfo.getOrderCode() + "已发货 请注意查收！");
                mdNewsInfoDao.saveMdNewsInfo(mdNewsInfo2);
                srm.setObj(mdOrderInfo);
            } else {
                srm.setCode(0l);
                srm.setMeg("操作失败,订单已发货!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("操作失败!");
            throw new HSKException(e);
        }
        return srm;
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
//                mdOrderAfterSaleEntity.setAfterSale(3);
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

    @Override
    public SysRetrunMessage saveReceiveOrderInfo(Integer moiId, String momIds, String shus, String masIds) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            boolean needSecurity = this.checkNeedSecurity();
            if (needSecurity) {
                srm.setCode(2L); // 需要安全码
                return srm;
            }
            MdOrderInfo mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            MdOrderMx mdOrderMx = new MdOrderMx();
            mdOrderMx.setMoiId(moiId);
            List<MdOrderMx> mxList = mdOrderMxDao.getListByMdOrderMx(mdOrderMx);
            String[] momIdArray = momIds.split(",");
            String[] shuArray = shus.split(",");
            Double count = 0d;
            Double countMoney = 0d;
            if (mxList != null && mxList.size() > 0) {
                for (MdOrderMx mx : mxList) {
                    for (int i = 0; i < momIdArray.length; i++) {
                        if (momIdArray[i].equals(mx.getMomId() + "")) {
//                            mx.setShureNumber(Double.parseDouble(shuArray[i]) + (mx.getShureNumber() != null ? mx.getShureNumber() : 0d));
                            mx.setShureNumber(mx.getMatNumber());
                            if (mx.getShureNumber() <= mx.getMatNumber())//当确认的数量小于等于购买数量时
                                mx.setMoney1(mx.getShureNumber() * mx.getUnitMoney());
                            else//当确认数量大于购买数量时
                                mx.setMoney1(mx.getMatNumber() * mx.getUnitMoney());
                            break;
                        }
                    }
                    countMoney += (mx.getMoney1() != null ? mx.getMoney1() : 0d);
                    mdOrderMxDao.saveOrUpdateMdOrderMx(mx);
                    count += (mx.getShureNumber() != null ? mx.getShureNumber() : 0d);
                }
            }
            mdOrderInfo.setNumber1(count);
            //实际总价=数量*价格+快递费-优惠价格
            mdOrderInfo.setActualMoney(countMoney + (mdOrderInfo.getMoney2() != null ? mdOrderInfo.getMoney2() : 0d) - (mdOrderInfo.getMoney3() != null ? mdOrderInfo.getMoney3() : 0d));
            if (count < mdOrderInfo.getCommodityNumber())
                mdOrderInfo.setProcessStatus("4");
            else {
                mdOrderInfo.setProcessStatus("5");
                mdOrderInfo.setEndTime(new Date());
            }
            this.updateObject(mdOrderInfo);

            //消息提醒,提醒商家订单已收货
            MdNewsInfo mdNewsInfo = new MdNewsInfo();
            mdNewsInfo.setOrderId(mdOrderInfo.getMoiId());
            mdNewsInfo.setUiId(mdOrderInfo.getWzId());
            mdNewsInfo.setUiType(1);
            if (mdOrderInfo.getProcessStatus().equals("4")) {
                mdNewsInfo.setNewsType(4);//已收货
                mdNewsInfo.setContent("订单 " + mdOrderInfo.getOrderCode() + "已收货 请查看！");
            } else if (mdOrderInfo.getProcessStatus().equals("5")) {
                mdNewsInfo.setNewsType(5);//已完成
                mdNewsInfo.setContent("订单 " + mdOrderInfo.getOrderCode() + "已完结 请查看！");
            }
            mdNewsInfo.setIsView(1);
            mdNewsInfo.setCreateDate(new Date());
            mdNewsInfoDao.saveMdNewsInfo(mdNewsInfo);

            srm.setObj(mdOrderInfo);

//            deleteMdAfterSale(masIds);
        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("操作失败!");
            throw new HSKException(e);
        }
        return srm;
    }

    @Override
    public SysRetrunMessage saveEndOrderInfo(Integer moiId) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdOrderInfo mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            MdOrderMx mdOrderMx = new MdOrderMx();
            mdOrderMx.setMoiId(moiId);
            List<MdOrderMx> mxList = mdOrderMxDao.getListByMdOrderMx(mdOrderMx);
            //修改商品的销售数量
            if (mxList != null && mxList.size() > 0) {
                for (MdOrderMx mx : mxList) {
                    Double cha = mx.getShureNumber() - mx.getMatNumber();
                    MdMaterielInfo info = new MdMaterielInfo();
                    info.setWmsMiId(mx.getWmsMiId());
                    info = (MdMaterielInfo) this.getOne(info);
                    info.setNumber1(mx.getShureNumber() + cha);
                    this.updateObject(info);
                }
            }
            mdOrderInfo.setProcessStatus("5");
            mdOrderInfo.setEndTime(new Date());
            this.updateObject(mdOrderInfo);

            //消息提醒,提醒商家订单已完结
            MdNewsInfo mdNewsInfo = new MdNewsInfo();
            mdNewsInfo.setOrderId(mdOrderInfo.getMoiId());
            mdNewsInfo.setUiId(mdOrderInfo.getWzId());
            mdNewsInfo.setUiType(1);
            mdNewsInfo.setNewsType(4);//新的订单
            mdNewsInfo.setIsView(1);
            mdNewsInfo.setCreateDate(new Date());
            mdNewsInfo.setContent("订单 " + mdOrderInfo.getOrderCode() + "已完结 请查看！");
            mdNewsInfoDao.saveMdNewsInfo(mdNewsInfo);
        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("操作失败!");
            throw new HSKException(e);
        }
        return srm;
    }

    @Override
    public SysRetrunMessage saveBackOrderInfo(Integer moiId)
            throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdOrderInfo mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            MdOrderMx mdOrderMx = new MdOrderMx();
            mdOrderMx.setMoiId(moiId);
            List<MdOrderMx> mxList = mdOrderMxDao.getListByMdOrderMx(mdOrderMx);
            //修改商品的销售数量
            if (mxList != null && mxList.size() > 0) {
                for (MdOrderMx mx : mxList) {
                    MdMaterielInfo info = new MdMaterielInfo();
                    info.setWmsMiId(mx.getWmsMiId());
                    info = (MdMaterielInfo) this.getOne(info);
                    if (info.getNumber1() != null)
                        info.setNumber1((info.getNumber1() - mx.getMatNumber()) > 0 ? (info.getNumber1() - mx.getMatNumber()) : 0d);
                    this.updateObject(info);
                }
            }
            mdOrderInfo.setProcessStatus("7");//退货状态
            mdOrderInfo.setEndTime(new Date());
            this.updateObject(mdOrderInfo);

            //消息提醒,提醒商家有新的退货
            MdNewsInfo mdNewsInfo = new MdNewsInfo();
            mdNewsInfo.setOrderId(mdOrderInfo.getMoiId());
            mdNewsInfo.setUiId(mdOrderInfo.getWzId());
            mdNewsInfo.setUiType(1);
            mdNewsInfo.setNewsType(2);//新的订单
            mdNewsInfo.setIsView(1);
            mdNewsInfo.setCreateDate(new Date());
            mdNewsInfo.setContent("订单 " + mdOrderInfo.getOrderCode() + "已退货请查看！");
            mdNewsInfoDao.saveMdNewsInfo(mdNewsInfo);
        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("操作失败!");
            throw new HSKException(e);
        }
        return srm;
    }


    public SysRetrunMessage saveCancelBackInfo(Integer moiId) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdOrderInfo mdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            String canNoCancel = "1,2";
            if (canNoCancel.indexOf(mdOrderInfo.getProcessStatus()) < 0) {
                srm.setCode(2L); // 已经非代发货状态
                return srm;
            }
            MdOrderMx mdOrderMx = new MdOrderMx();
            mdOrderMx.setMoiId(moiId);
            List<MdOrderMx> mxList = mdOrderMxDao.getListByMdOrderMx(mdOrderMx);
            //修改商品的销售数量
            if (mxList != null && mxList.size() > 0) {
                String mmfIds = "";
                for (MdOrderMx mx : mxList) {
                    MdMaterielInfo info = new MdMaterielInfo();
                    info.setWmsMiId(mx.getWmsMiId());
                    info = (MdMaterielInfo) this.getOne(info);
                    if (info.getNumber1() != null)
                        info.setNumber1((info.getNumber1() - mx.getMatNumber()) > 0 ? (info.getNumber1() - mx.getMatNumber()) : 0d);
                    this.updateObject(info);
                    mmfIds += mx.getMmfId() + ",";
                }
                //将退货的商品放入退购车
                mmfIds = mmfIds.substring(0, mmfIds.length() - 1);
                mdNoBuyDao.delMdNoBuyByStrs(mdOrderInfo.getPurchaseId(), mmfIds);
                for (MdOrderMx mx : mxList) {
                    MdNoBuy mdNoBuy = new MdNoBuy();
                    mdNoBuy.setState("1");
                    mdNoBuy.setSuiId(mdOrderInfo.getPurchaseId());
                    mdNoBuy.setMmfId(mx.getMmfId());
                    this.newObject(mdNoBuy);
                }
            }
            mdOrderInfo.setProcessStatus("6"); //取消状态
            mdOrderInfo.setEndTime(new Date());
            this.updateObject(mdOrderInfo);
        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("操作失败!");
            throw new HSKException(e);
        }
        return srm;
    }

    @Override
    public PagerModel getPagerModelEnterOrder(MdOrderInfo att_MdOrderInfo) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdOrderInfo>());
        try {
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
            pm = mdOrderInfoDao.getPagerModelByEnterMdOrderInfo(att_MdOrderInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getPagerModelOutOrder(MdOrderInfo att_MdOrderInfo)
            throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdOrderInfo>());
        try {
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
            att_MdOrderInfo.setAfterSale(1);
            pm = mdOrderInfoDao.getPagerModelByOutMdOrderInfo(att_MdOrderInfo);
            List<MdOrderInfo> list = pm.getItems();
            MdEnterWarehouse mdEnterWarehouse;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            for (MdOrderInfo mdOrderInfo : list) {
                mdEnterWarehouse = mdEnterWarehouseDao.findMdEnterWarehouseByOrderCode(mdOrderInfo.getOrderCode());
                if (mdEnterWarehouse == null)
                    continue;
                mdOrderInfo.setEnterCode(mdEnterWarehouse.getBillcode());
                mdOrderInfo.setEnterType(mdEnterWarehouse.getBillType());
                mdOrderInfo.setEnterDate(sdf.format(mdEnterWarehouse.getCreateDate()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getPagerModelOutOrderEnter(MdOrderInfo att_MdOrderInfo) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdOrderInfo>());
        try {
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
            att_MdOrderInfo.setAfterSale(1);
//            pm = mdOrderInfoDao.getPagerModelByOutMdOrderInfo(att_MdOrderInfo);
//            List<MdOrderInfo> list = pm.getItems();
//            MdEnterWarehouse mdEnterWarehouse;
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            for (MdOrderInfo mdOrderInfo : list) {
//                mdEnterWarehouse = mdEnterWarehouseDao.findMdEnterWarehouseByOrderCode(mdOrderInfo.getOrderCode());
//                if (mdEnterWarehouse == null)
//                    continue;
//                mdOrderInfo.setEnterCode(mdEnterWarehouse.getBillcode());
//                mdOrderInfo.setEnterType(mdEnterWarehouse.getBillType());
//                mdOrderInfo.setEnterDate(sdf.format(mdEnterWarehouse.getCreateDate()));
//            }
            pm = mdOrderInfoDao.getPagerModelByOutMdOrderInfoEnter(att_MdOrderInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    /**
     * 增加状态count
     * 2019-12-19
     * yanglei
     */
    @Override
    public SysRetrunMessage countOrder(MdOrderInfo att_MdOrderInfo) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        Map<String, Integer> reMap = new HashMap<String, Integer>();
        Integer suiId = null;
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account.getOrganizaType().equals("100")) {
                suiId = account.getOldId();
            }if(account.getOrganizaType().equals("0")){
            	 suiId = account.getOldId();
            }
            if (att_MdOrderInfo.getPay_type_str() != null && att_MdOrderInfo.getPay_type_str().equals("支付宝")) {
                att_MdOrderInfo.setPay_type_str("1");
            } else if (att_MdOrderInfo.getPay_type_str() != null && att_MdOrderInfo.getPay_type_str().equals("微信")) {
                att_MdOrderInfo.setPay_type_str("2");
            } else if (att_MdOrderInfo.getPay_type_str() != null && att_MdOrderInfo.getPay_type_str().equals("月结")) {
                att_MdOrderInfo.setPay_type_str("3");
            }
            att_MdOrderInfo.setPay_type_str("");

            Integer allCount = mdOrderInfoDao.countOrderInfoByProcessStateTwo(suiId, null, att_MdOrderInfo);
            Integer dfhCount = mdOrderInfoDao.countOrderInfoByProcessStateTwo(suiId, "1", att_MdOrderInfo);
            Integer dfkCount = mdOrderInfoDao.countOrderInfoByProcessStateTwo(suiId, "2", att_MdOrderInfo);
            Integer dshCount = mdOrderInfoDao.countOrderInfoByProcessStateTwo(suiId, "3", att_MdOrderInfo);
            Integer bffhCount = mdOrderInfoDao.countOrderInfoByProcessStateTwo(suiId, "4", att_MdOrderInfo);
            Integer jycgCount = mdOrderInfoDao.countOrderInfoByProcessStateTwo(suiId, "5", att_MdOrderInfo);
            Integer jysbCount = mdOrderInfoDao.countOrderInfoByProcessStateTwo(suiId, "6", att_MdOrderInfo);
            Integer shCount = mdOrderInfoDao.countOrderInfoByAfterSaleTwo(suiId, att_MdOrderInfo);
            reMap.put("all", allCount);
            reMap.put("dfh", dfhCount);
            reMap.put("dfk", dfkCount);
            reMap.put("bffh", bffhCount);
            reMap.put("dsh", dshCount);
            reMap.put("jycg", jycgCount);
            reMap.put("jysb", jysbCount);
            reMap.put("sh", shCount);
            srm.setObj(reMap);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return srm;
    }
    /*
    	
    }*/

    @Override
    public PagerModel getAllPagerModelObject(MdOrderInfo att_MdOrderInfo)
            throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdOrderInfo>());
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account.getOrganizaType().equals("20001")) {
                att_MdOrderInfo.setRbaId(account.getOldId());
            }
            if (account.getOrganizaType().equals("20002")) {
                att_MdOrderInfo.setRbsId(account.getOldId());
            }
            att_MdOrderInfo.setOrderState("1");
            att_MdOrderInfo.setTab_like("applicantName,purchaseUnit,orderCode");
            att_MdOrderInfo.setTab_order("placeOrderTime desc");
            pm = mdOrderInfoDao.getPagerModelByMdOrderInfo(att_MdOrderInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getAllPagerModelObjectDistinct(MdOrderInfo att_MdOrderInfo, String distinctName) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdOrderInfo>());
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account.getOrganizaType().equals("20001")) {
                att_MdOrderInfo.setRbaId(account.getOldId());
            }
            if (account.getOrganizaType().equals("20002")) {
                att_MdOrderInfo.setRbsId(account.getOldId());
            }
            att_MdOrderInfo.setOrderState("1");
            att_MdOrderInfo.setTab_like("applicantName,purchaseUnit,orderCode");
            att_MdOrderInfo.setTab_order("placeOrderTime desc");
            pm = mdOrderInfoDao.getAllPagerModelObjectDistinct(att_MdOrderInfo, distinctName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getPagerModelObjectForCkDistinct(MdOrderInfo att_MdOrderInfo, String distinctName) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdOrderInfo>());
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account.getOrganizaType().equals("20001")) {
                att_MdOrderInfo.setRbaId(account.getOldId());
                att_MdOrderInfo.setPurchaseType("1");
            }
            if (account.getOrganizaType().equals("20002")) {
                att_MdOrderInfo.setRbsId(account.getOldId());
                att_MdOrderInfo.setPurchaseType("2");
            }
            if (account.getOrganizaType().equals("20003")) {
                att_MdOrderInfo.setRbbId(account.getOldId());
                att_MdOrderInfo.setPurchaseType("3");
            }
            att_MdOrderInfo.setOrderState("1");
            att_MdOrderInfo.setTab_like("applicantName,purchaseUnit,orderCode,expressCode");
            att_MdOrderInfo.setTab_order("placeOrderTime desc");
            pm = mdOrderInfoDao.getPagerModelObjectForCkDistinct(att_MdOrderInfo, distinctName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }
    /**
     * 增加导出仓库出库单
     * 2020-01-05
     * yanglei
     */
    @Override
    public SysRetrunMessage exportOutWarehouse(Integer moiId) throws HSKException{
    	SysRetrunMessage srm = new SysRetrunMessage(1l);
         try {
             MdOrderInfo att_MdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
             MdSupplier mdSupplier = mdSupplierDao.findMdSupplierById(att_MdOrderInfo.getWzId());
             MdOrderMx mdOrderMx = new MdOrderMx();
             mdOrderMx.setMoiId(moiId);
             List<MdOrderMx> mxList = mdOrderMxDao.getListByMdOrderMx(mdOrderMx);
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
             DecimalFormat df = new DecimalFormat("######0.00");
             DecimalFormat df2 = new DecimalFormat("######0");
             Map<String, Object> dataMap = new HashMap<String, Object>();
             int rowCount = 17;
             dataMap.put("supplierName", att_MdOrderInfo.getApplicantName());
             dataMap.put("purchaseUnit", att_MdOrderInfo.getPurchaseUnit());
             dataMap.put("legalPerson", mdSupplier.getLegalPerson() != null ? mdSupplier.getLegalPerson() : "");
             dataMap.put("phoneNumber", mdSupplier.getPhoneNumber() != null ? mdSupplier.getPhoneNumber() : "");
             dataMap.put("selprovince", mdSupplier.getSelprovince() != null ? mdSupplier.getSelprovince() : "");
             dataMap.put("selcity", mdSupplier.getSelcity() != null ? mdSupplier.getSelcity() : "");
             dataMap.put("selarea", mdSupplier.getSelarea() != null ? mdSupplier.getSelarea() : "");
             dataMap.put("corporateDomicile", mdSupplier.getCorporateDomicile() != null ? mdSupplier.getCorporateDomicile() : "");
             dataMap.put("addressee", att_MdOrderInfo.getAddressee());
             dataMap.put("addresseeTelephone", att_MdOrderInfo.getAddresseeTelephone());
             dataMap.put("province", att_MdOrderInfo.getProvince());
             dataMap.put("city", att_MdOrderInfo.getCity());
             dataMap.put("area", att_MdOrderInfo.getArea());
             dataMap.put("deliveryAddress", att_MdOrderInfo.getDeliveryAddress());
             //增加买家留言
             dataMap.put("enterpriseType", att_MdOrderInfo.getEnterpriseType()!=null ?  att_MdOrderInfo.getEnterpriseType():"");
             //增加订单号
             dataMap.put("orderCode", att_MdOrderInfo.getOrderCode()!=null ?  att_MdOrderInfo.getOrderCode():"");
             //增加创建时间
             Date NewDate=new Date();
             dataMap.put("newDate", sdf.format(NewDate));
             //增加制单人
             SysUserInfo sysUserInfo=this.GetOneSessionAccount();
             dataMap.put("userName", sysUserInfo.getUserName());
             if (att_MdOrderInfo.getMoney2() != null)
                 dataMap.put("money2", df.format(att_MdOrderInfo.getMoney2()));
             else
                 dataMap.put("money2", "0.00");
             if (att_MdOrderInfo.getMoney3() != null)
                 dataMap.put("money3", df.format(att_MdOrderInfo.getMoney3()));
             else
                 dataMap.put("money3", "0.00");
             dataMap.put("commodityNumber", df2.format(att_MdOrderInfo.getCommodityNumber()));
             dataMap.put("placeOrderMoney", df.format(att_MdOrderInfo.getPlaceOrderMoney()));
             dataMap.put("scopeBusiness", att_MdOrderInfo.getScopeBusiness() != null ? att_MdOrderInfo.getScopeBusiness() : " ");
             dataMap.put("purchaseAccount", att_MdOrderInfo.getPurchaseAccount());
             dataMap.put("placeOrderTime", sdf.format(att_MdOrderInfo.getPlaceOrderTime()));
             List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
             int index = 0;
            Double LackNumber=0.0;
//            Double countMatNumber=0.0;
//            Double countLackNumber=0.0;
             for (MdOrderMx obj : mxList) {
                 index++;
                 Map<String, String> reMap = new HashMap<String, String>();
                 reMap.put("index", index + "");
                 reMap.put("matName", obj.getMatName());
                 reMap.put("mmfName", obj.getNorm());
                 reMap.put("basicUnit", obj.getBasicUnit());
                 reMap.put("unitMoney", df.format(obj.getUnitMoney()));
                 //增加型号编号
                 reMap.put("mmfCode", obj.getMmfCode() != null ? obj.getMmfCode() : "");
                 reMap.put("matNumber", df2.format(obj.getMatNumber()));
                 reMap.put("totalMoney", df.format(obj.getTotalMoney()));
                // obj.getMatName()-obj.getBackNumber();
                 LackNumber=obj.getMatNumber()-obj.getShureNumber();
                 reMap.put("LackNumber", df2.format(LackNumber));
//                 obj.getWmsMiId();
                 MdMaterielInfo mdMaterielInfo=  mdMaterielInfoDao.findMdMaterielInfoById(obj.getWmsMiId());
                 if (mdMaterielInfo.getValiedDate()!=null&&!mdMaterielInfo.getValiedDate().equals("")){
                     reMap.put("valiedDate", sdf.format(mdMaterielInfo.getValiedDate()));
                 }else {
                     reMap.put("valiedDate", "");
                 }
                 if (mdMaterielInfo.getBatchCode()!=null&&!mdMaterielInfo.getBatchCode().equals("")){
                     reMap.put("batchCode", mdMaterielInfo.getBatchCode());
                 }else {
                     reMap.put("batchCode", "");
                 }
                 if (mdMaterielInfo.getBackPrinting()!=null&&!mdMaterielInfo.getBackPrinting().equals("")){
                     reMap.put("backPrinting", mdMaterielInfo.getBackPrinting());
                 }else {
                     reMap.put("backPrinting", "");
                 }
                 if (mdMaterielInfo.getSerialNumber()!=null){
                     reMap.put("SerialNumber", String.valueOf(mdMaterielInfo.getSerialNumber()));
                 }else {
                     reMap.put("SerialNumber", "");
                 }
                 reList.add(reMap);
             }
             rowCount += mxList.size();
           /*  dataMap.put("countMatNumber", df2.format(countMatNumber));
             dataMap.put("countLackNumber", df2.format(countLackNumber));*/
             dataMap.put("rowCount", rowCount);
             dataMap.put("rowCount2", rowCount - 1);
             dataMap.put("mxList", reList);
             Configuration configuration = new Configuration();
             configuration.setDefaultEncoding("UTF-8");
             // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
             // 这里我们的模板是放在org.cnzjw.template包下面
             configuration.setClassForTemplateLoading(this.getClass(),
                     "/ftl");
             Template t = configuration.getTemplate("exportOutWarehouse.ftl");
             // 输出文档路径及名称
             Calendar now = Calendar.getInstance();
//             long l = now.getTimeInMillis();
             String l=att_MdOrderInfo.getApplicantName()+"-拣货单";
             String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
             File file = new File(tmpPath);
             if (!file.exists()) {
                 file.mkdirs();
             }
             String realPath = tmpPath + l + ".xls";
             File outFile = new File(realPath);
             Writer out = null;
             out = new BufferedWriter(new OutputStreamWriter(
                     new FileOutputStream(outFile), "UTF-8"));
             t.process(dataMap, out);
             out.close();
             String rootUrl = request.getContextPath() + EXPORT_PATH + l + ".xls";
             Map<String, String> map = new HashMap<String, String>();
             map.put("path", rootUrl);
             map.put("fileName", l + ".xls");
             srm.setObj(map);
         } catch (Exception e) {
             e.printStackTrace();
         }
         return srm;
    }
    @Override
    public SysRetrunMessage exportInfo(Integer moiId) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdOrderInfo att_MdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            MdSupplier mdSupplier = mdSupplierDao.findMdSupplierById(att_MdOrderInfo.getWzId());
            MdOrderMx mdOrderMx = new MdOrderMx();
            mdOrderMx.setMoiId(moiId);
            List<MdOrderMx> mxList = mdOrderMxDao.getListByMdOrderMx(mdOrderMx);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            DecimalFormat df = new DecimalFormat("######0.00");
            DecimalFormat df2 = new DecimalFormat("######0");
            Map<String, Object> dataMap = new HashMap<String, Object>();
            int rowCount = 17;
            dataMap.put("supplierName", att_MdOrderInfo.getApplicantName());
            dataMap.put("purchaseUnit", att_MdOrderInfo.getPurchaseUnit());
            dataMap.put("legalPerson", mdSupplier.getLegalPerson() != null ? mdSupplier.getLegalPerson() : "");
            dataMap.put("phoneNumber", mdSupplier.getPhoneNumber() != null ? mdSupplier.getPhoneNumber() : "");
            dataMap.put("selprovince", mdSupplier.getSelprovince() != null ? mdSupplier.getSelprovince() : "");
            dataMap.put("selcity", mdSupplier.getSelcity() != null ? mdSupplier.getSelcity() : "");
            dataMap.put("selarea", mdSupplier.getSelarea() != null ? mdSupplier.getSelarea() : "");
            dataMap.put("corporateDomicile", mdSupplier.getCorporateDomicile() != null ? mdSupplier.getCorporateDomicile() : "");
            dataMap.put("addressee", att_MdOrderInfo.getAddressee());
            dataMap.put("addresseeTelephone", att_MdOrderInfo.getAddresseeTelephone());
            dataMap.put("province", att_MdOrderInfo.getProvince());
            dataMap.put("city", att_MdOrderInfo.getCity());
            dataMap.put("area", att_MdOrderInfo.getArea());
            dataMap.put("deliveryAddress", att_MdOrderInfo.getDeliveryAddress());
            dataMap.put("orderCode", att_MdOrderInfo.getOrderCode() != null ? att_MdOrderInfo.getOrderCode() : "");
            if (att_MdOrderInfo.getMoney2() != null)
                dataMap.put("money2", df.format(att_MdOrderInfo.getMoney2()));
            else
                dataMap.put("money2", "0.00");
            if (att_MdOrderInfo.getMoney3() != null)
                dataMap.put("money3", df.format(att_MdOrderInfo.getMoney3()));
            else
                dataMap.put("money3", "0.00");
            dataMap.put("commodityNumber", df2.format(att_MdOrderInfo.getCommodityNumber()));
            dataMap.put("placeOrderMoney", df.format(att_MdOrderInfo.getPlaceOrderMoney()));
            dataMap.put("scopeBusiness", att_MdOrderInfo.getScopeBusiness() != null ? att_MdOrderInfo.getScopeBusiness() : " ");
            dataMap.put("purchaseAccount", att_MdOrderInfo.getPurchaseAccount());
            dataMap.put("placeOrderTime", sdf.format(att_MdOrderInfo.getPlaceOrderTime()));
            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int index = 0;
            for (MdOrderMx obj : mxList) {
                index++;
                Map<String, String> reMap = new HashMap<String, String>();
                reMap.put("index", index + "");
                reMap.put("matName", obj.getMatName());
                reMap.put("mmfName", obj.getNorm());
                reMap.put("basicUnit", obj.getBasicUnit());
                reMap.put("unitMoney", df.format(obj.getUnitMoney()));
                //增加型号编号
                reMap.put("mmfCode", obj.getMmfCode() != null ? obj.getMmfCode() : "");
                reMap.put("matNumber", df2.format(obj.getMatNumber()));
                reMap.put("totalMoney", df.format(obj.getTotalMoney()));
                reList.add(reMap);
            }
            rowCount += mxList.size();
            dataMap.put("rowCount", rowCount);
            dataMap.put("rowCount2", rowCount - 1);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportOrderInfo.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            long l = now.getTimeInMillis();
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + l + ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + l + ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", l + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }

    /**
     * 增加导出配送单
     * yanglei
     * 2019-11-26
     */
    @Override
    public SysRetrunMessage exportSupplierInfoSheetC(Integer moiId, String test)
            throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdOrderInfo att_MdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            MdOrderMx mdOrderMx = new MdOrderMx();
            mdOrderMx.setMoiId(moiId);
            List<MdOrderMx> mxList = mdOrderMxDao.getListByMdOrderMx(mdOrderMx);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            DecimalFormat df = new DecimalFormat("######0.00");
            DecimalFormat df2 = new DecimalFormat("######0");
            Map<String, Object> dataMap = new HashMap<String, Object>();
            int rowCount = 21;
            dataMap.put("addressee", att_MdOrderInfo.getAddressee());
            dataMap.put("addresseeTelephone", att_MdOrderInfo.getAddresseeTelephone());
            dataMap.put("province", att_MdOrderInfo.getProvince());
            dataMap.put("city", att_MdOrderInfo.getCity());
            dataMap.put("area", att_MdOrderInfo.getArea());
            dataMap.put("deliveryAddress", att_MdOrderInfo.getDeliveryAddress());
            dataMap.put("expressName", att_MdOrderInfo.getExpressName() != null ? att_MdOrderInfo.getExpressName() : "");
            dataMap.put("expressCode", att_MdOrderInfo.getExpressCode() != null ? att_MdOrderInfo.getExpressCode() : "");
            dataMap.put("orderCode", att_MdOrderInfo.getOrderCode());
            dataMap.put("applicantName", att_MdOrderInfo.getApplicantName());
            dataMap.put("purchaseUnit", att_MdOrderInfo.getPurchaseUnit());
            SysUserInfo sysUserInfo = this.GetOneSessionAccount();
            //制单人
            dataMap.put("userName", sysUserInfo.getUserName());

            /**
             * 2019-11-24
             * yanglei
             * 添加订单状态字段
             */
            dataMap.put("processStatus", att_MdOrderInfo.getProcessStatusName());
            dataMap.put("purchaseUnit", att_MdOrderInfo.getPurchaseUnit());
            dataMap.put("placeOrderTime", sdf.format(att_MdOrderInfo.getPlaceOrderTime()));
            if (att_MdOrderInfo.getEndTime() != null) {
                dataMap.put("endTimeTitle", "完结日期:");
                dataMap.put("endTime", sdf.format(att_MdOrderInfo.getEndTime()));
            } else {
                dataMap.put("endTimeTitle", "");
                dataMap.put("endTime", "");
            }
            if (att_MdOrderInfo.getNeedBill() != null && att_MdOrderInfo.getNeedBill().equals("1")) {
                dataMap.put("billHeardTitle", "发票抬头:");
                String billHeard = att_MdOrderInfo.getBillHeard() != null ? att_MdOrderInfo.getBillHeard() : "";
                if (att_MdOrderInfo.getBillType() != null && att_MdOrderInfo.getBillType().equals("2")) {
                    billHeard += "(公司)";
                    dataMap.put("billCodeTitle", "纳税人识别号：");
                    dataMap.put("billCode", att_MdOrderInfo.getBillCode() != null ? att_MdOrderInfo.getBillCode() : "");
                } else {
                    billHeard += "(个人)";
                    dataMap.put("billCodeTitle", "");
                    dataMap.put("billCode", "");
                }
                dataMap.put("billHeard", billHeard);
            } else {
                dataMap.put("billHeardTitle", "无");
                dataMap.put("billHeard", "");
                dataMap.put("billCodeTitle", "");
                dataMap.put("billCode", "");
            }
            dataMap.put("scopeBusiness", att_MdOrderInfo.getScopeBusiness() != null ? att_MdOrderInfo.getScopeBusiness() : " ");

            if (att_MdOrderInfo.getMoney2() != null)
                dataMap.put("money2", df.format(att_MdOrderInfo.getMoney2()));
            else
                dataMap.put("money2", "0.00");
            if (att_MdOrderInfo.getMoney3() != null)
                dataMap.put("money3", df.format(att_MdOrderInfo.getMoney3()));
            else
                dataMap.put("money3", "0.00");
            dataMap.put("commodityNumber", df2.format(att_MdOrderInfo.getCommodityNumber()));
            dataMap.put("number2", df2.format(att_MdOrderInfo.getNumber2() != null ? att_MdOrderInfo.getNumber2() : 0d));
            dataMap.put("number1", df2.format(att_MdOrderInfo.getNumber1() != null ? att_MdOrderInfo.getNumber1() : 0d));
            dataMap.put("placeOrderMoney", df.format(att_MdOrderInfo.getPlaceOrderMoney()));
            dataMap.put("actualMoney", att_MdOrderInfo.getActualMoney() != null ? att_MdOrderInfo.getActualMoney() : 0d);
            //配送单摘要
            //att_MdOrderInfo.setEnterpriseType(test);
           // dataMap.put("test", att_MdOrderInfo.getEnterpriseType()!=null ?att_MdOrderInfo.getEnterpriseType():" ");
           // System.out.println("12345+"+att_MdOrderInfo.getEnterpriseType()+"++end");

            //MdOrderInfo md2=new MdOrderInfo();
            //mdOrderInfoDao.saveOrUpdateMdOrderInfo(att_MdOrderInfo);
            dataMap.put("test",att_MdOrderInfo.getEnterpriseType()!=null? att_MdOrderInfo.getEnterpriseType():" ");

            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int index = 0;
            Double counMoney = 0.0;
            Double countMatNumber = 0.0;
            Double LackNumber=0.0;
            for (MdOrderMx obj : mxList) {
                index++;
                Map<String, String> reMap = new HashMap<String, String>();
                reMap.put("index", index + "");
                reMap.put("matName", obj.getMatName());
                reMap.put("mmfName", obj.getNorm());
                reMap.put("basicUnit", obj.getBasicUnit());
                /**
                 * 2019-11-24
                 * yanglei
                 * 添加物料信息表中数据 --productName
                 */
                MdMaterielInfo att_mdMaterielInfo = mdMaterielInfoDao.findMdMaterielInfoById(obj.getWmsMiId());
                //包装方式
                reMap.put("productName", att_mdMaterielInfo.getProductName());
                //注册证号
                reMap.put("backPrinting", att_mdMaterielInfo.getBackPrinting() != null ? att_mdMaterielInfo.getBackPrinting() : "");
                //生产日期
                //reMap.put("productTime",  sdf.format(att_mdMaterielInfo.getProductTime())!=null?sdf.format(att_mdMaterielInfo.getProductTime()):"");
                if (att_mdMaterielInfo.getProductTime() != null) {
                    reMap.put("productTime", sdf.format(att_mdMaterielInfo.getProductTime()));
                } else {
                    reMap.put("productTime", "");
                }
                //有效日期
                if (att_mdMaterielInfo.getProductTime() != null) {
                    reMap.put("productValitime", sdf.format(att_mdMaterielInfo.getProductValitime()));
                } else {
                    reMap.put("productValitime", "");
                }
                //批次号
                reMap.put("batchCertNo", att_mdMaterielInfo.getBatchCertNo() != null ? att_mdMaterielInfo.getBatchCertNo() : "");
                //生产厂家
                reMap.put("productFactory", att_mdMaterielInfo.getProductFactory() != null ? att_mdMaterielInfo.getProductFactory() : "");

                reMap.put("unitMoney", df.format(obj.getUnitMoney()));
                reMap.put("matNumber", df2.format(obj.getMatNumber()));
                //增加型号编号
                reMap.put("mmfCode", obj.getMmfCode() != null ? obj.getMmfCode() : "");
                reMap.put("number2", df2.format(obj.getNumber2() != null ? obj.getNumber2() : 0d));
                reMap.put("shureNumber", df2.format(obj.getShureNumber() != null ? obj.getShureNumber() : 0d));
                reMap.put("totalMoney", df.format(obj.getTotalMoney()));
                reMap.put("money1", df.format(obj.getMoney1() != null ? obj.getMoney1() : 0d));
                if (obj.getNumber2()!=null) {
                	LackNumber=	obj.getMatNumber()-obj.getNumber2();
				}else{
					LackNumber=obj.getMatNumber();
				}
                reMap.put("LackNumber", df2.format(LackNumber));
                if (obj.getTotalMoney() != null) {
                    ///合计总额
                    counMoney += obj.getTotalMoney();
                }
                if (obj.getMatNumber() != null ) {
                    countMatNumber += obj.getMatNumber();
                }
                reList.add(reMap);
            }

            rowCount += mxList.size();
            dataMap.put("counMoney", counMoney);
            dataMap.put("countMatNumber", countMatNumber);
            dataMap.put("rowCount", rowCount);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportSupplierOrderInfoSheetC.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            /*	long l = now.getTimeInMillis();*/
            //修改文件名字格式为GYSOrderDetail+订单号
            String l = "GYSOrder" + (String) dataMap.get("orderCode");
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + l + "供应商配货单" + ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + l + "供应商配货单" + ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", l + "供应商配货单" + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }


    @Override
    public SysRetrunMessage exportSupplierInfo(Integer moiId)
            throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdOrderInfo att_MdOrderInfo = mdOrderInfoDao.findMdOrderInfoById(moiId);
            MdOrderMx mdOrderMx = new MdOrderMx();
            mdOrderMx.setMoiId(moiId);
            List<MdOrderMx> mxList = mdOrderMxDao.getListByMdOrderMx(mdOrderMx);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            DecimalFormat df = new DecimalFormat("######0.00");
            DecimalFormat df2 = new DecimalFormat("######0");
            Map<String, Object> dataMap = new HashMap<String, Object>();
            int rowCount = 21;
            dataMap.put("addressee", att_MdOrderInfo.getAddressee());
            dataMap.put("addresseeTelephone", att_MdOrderInfo.getAddresseeTelephone());
            dataMap.put("province", att_MdOrderInfo.getProvince());
            dataMap.put("city", att_MdOrderInfo.getCity());
            dataMap.put("area", att_MdOrderInfo.getArea());
            dataMap.put("deliveryAddress", att_MdOrderInfo.getDeliveryAddress());
            dataMap.put("expressName", att_MdOrderInfo.getExpressName() != null ? att_MdOrderInfo.getExpressName() : "");
            dataMap.put("expressCode", att_MdOrderInfo.getExpressCode() != null ? att_MdOrderInfo.getExpressCode() : "");
            dataMap.put("orderCode", att_MdOrderInfo.getOrderCode());
            dataMap.put("applicantName", att_MdOrderInfo.getApplicantName());
            //添加留言信息
            dataMap.put("enterpriseType", att_MdOrderInfo.getEnterpriseType()!=null ?  att_MdOrderInfo.getEnterpriseType():"");
            //添加摘要信息
            //dataMap.put("enterpriseType", att_MdOrderInfo.getEnterpriseType()!=null ?  att_MdOrderInfo.getEnterpriseType():"");

            /**
             * 2019-11-24
             * yanglei
             * 添加订单状态字段
             */
            dataMap.put("processStatus", att_MdOrderInfo.getProcessStatusName());
            dataMap.put("purchaseUnit", att_MdOrderInfo.getPurchaseUnit());
            dataMap.put("placeOrderTime", sdf.format(att_MdOrderInfo.getPlaceOrderTime()));
            if (att_MdOrderInfo.getEndTime() != null) {
                dataMap.put("endTimeTitle", "完结日期:");
                dataMap.put("endTime", sdf.format(att_MdOrderInfo.getEndTime()));
            } else {
                dataMap.put("endTimeTitle", "");
                dataMap.put("endTime", "");
            }
            if (att_MdOrderInfo.getNeedBill() != null && att_MdOrderInfo.getNeedBill().equals("1")) {
                dataMap.put("billHeardTitle", "发票抬头:");
                String billHeard = att_MdOrderInfo.getBillHeard() != null ? att_MdOrderInfo.getBillHeard() : "";
                if (att_MdOrderInfo.getBillType() != null && att_MdOrderInfo.getBillType().equals("2")) {
                    billHeard += "(公司)";
                    dataMap.put("billCodeTitle", "纳税人识别号：");
                    dataMap.put("billCode", att_MdOrderInfo.getBillCode() != null ? att_MdOrderInfo.getBillCode() : "");
                } else {
                    billHeard += "(个人)";
                    dataMap.put("billCodeTitle", "");
                    dataMap.put("billCode", "");
                }
                dataMap.put("billHeard", billHeard);
            } else {
                dataMap.put("billHeardTitle", "无");
                dataMap.put("billHeard", "");
                dataMap.put("billCodeTitle", "");
                dataMap.put("billCode", "");
            }
            dataMap.put("scopeBusiness", att_MdOrderInfo.getScopeBusiness() != null ? att_MdOrderInfo.getScopeBusiness() : " ");

            if (att_MdOrderInfo.getMoney2() != null)
                dataMap.put("money2", df.format(att_MdOrderInfo.getMoney2()));
            else
                dataMap.put("money2", "0.00");
            if (att_MdOrderInfo.getMoney3() != null)
                dataMap.put("money3", df.format(att_MdOrderInfo.getMoney3()));
            else
                dataMap.put("money3", "0.00");
            dataMap.put("commodityNumber", df2.format(att_MdOrderInfo.getCommodityNumber()));
            dataMap.put("number2", df2.format(att_MdOrderInfo.getNumber2() != null ? att_MdOrderInfo.getNumber2() : 0d));
            dataMap.put("number1", df2.format(att_MdOrderInfo.getNumber1() != null ? att_MdOrderInfo.getNumber1() : 0d));
            dataMap.put("placeOrderMoney", df.format(att_MdOrderInfo.getPlaceOrderMoney()));
            dataMap.put("actualMoney", att_MdOrderInfo.getActualMoney() != null ? att_MdOrderInfo.getActualMoney() : 0d);

            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int index = 0;
            for (MdOrderMx obj : mxList) {
                index++;
                Map<String, String> reMap = new HashMap<String, String>();
                reMap.put("index", index + "");
                reMap.put("matName", obj.getMatName());
                reMap.put("mmfName", obj.getNorm());
                reMap.put("basicUnit", obj.getBasicUnit());
                //添加型号编号字段：
                reMap.put("mmfCode", obj.getMmfCode() != null ? obj.getMmfCode() : "");
                /**
                 * 2019-11-24
                 * yanglei
                 * 添加物料信息表中数据 --productName
                 */
//
                if (obj.getWmsMiId()!=null){
                    MdMaterielInfo att_mdMaterielInfo = mdMaterielInfoDao.findMdMaterielInfoById(obj.getWmsMiId());
                    if (att_mdMaterielInfo.getProductName()!=null){
                        reMap.put("productName", att_mdMaterielInfo.getProductName());
                    }else{
                        reMap.put("productName"," ");
                    }
                }else{
                    reMap.put("productName", " ");
                }
                reMap.put("unitMoney", df.format(obj.getUnitMoney()));
                reMap.put("matNumber", df2.format(obj.getMatNumber()));
                reMap.put("number2", df2.format(obj.getNumber2() != null ? obj.getNumber2() : 0d));
                reMap.put("shureNumber", df2.format(obj.getShureNumber() != null ? obj.getShureNumber() : 0d));
                reMap.put("totalMoney", df.format(obj.getTotalMoney()));
                reMap.put("money1", df.format(obj.getMoney1() != null ? obj.getMoney1() : 0d));
                reList.add(reMap);
            }
            rowCount += mxList.size();
            dataMap.put("rowCount", rowCount);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportSupplierOrderInfoC.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            /*	long l = now.getTimeInMillis();*/
            //修改文件名字格式为GYSOrderDetail+订单号
            String l = "GYSOrderDetail" + (String) dataMap.get("orderCode");
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + l + ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + l + ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", l + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }

    /**
     * 2020-01-16 更换导出全部订单详情
     */
    public SysRetrunMessage exportCgOrderList(MdOrderInfo att_MdOrderInfo,MdOrderMx att_MdOrderMx,String moiIds, String excludeMoiIds) throws HSKException{
    	SysRetrunMessage srm = new SysRetrunMessage(1l);
    	DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> dataMap = new HashMap<String, Object>();
        try {
        	  SysUserInfo account = this.GetOneSessionAccount();
              if (account.getOrganizaType().equals("20001")) {
                  att_MdOrderInfo.setRbaId(account.getOldId());
              }
              if (account.getOrganizaType().equals("20002")) {
                  att_MdOrderInfo.setRbsId(account.getOldId());
              }if (moiIds==null) {
              	 if (account.getOrganizaType().equals("100")) {
                   	att_MdOrderInfo.setWzId(account.getOldId());
       			}if (account.getOrganizaType().equals("20003")) {
       				att_MdOrderInfo.setRbbId(account.getOldId());
       			}
  			}

              dataMap.put("orgName", account.getOrgName());
              String timeArea = "";
              if (att_MdOrderInfo.getPlaceOrderTime_start() != null)
                  timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_start());
              if (att_MdOrderInfo.getPlaceOrderTime_start() != null || att_MdOrderInfo.getPlaceOrderTime_end() != null)
                  timeArea += "-";
              if (att_MdOrderInfo.getPlaceOrderTime_end() != null)
                  timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_end());
              dataMap.put("timeArea", timeArea);
              dataMap.put("userName", account.getUserName());
              att_MdOrderInfo.setOrderState("1");
			List<Map<String, Object>> list =mdOrderMxDao.getMxListbyOrderAndMx(att_MdOrderInfo, att_MdOrderMx,moiIds, excludeMoiIds);
			 List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
			 Double countNumber=0.0;
			 Double countMoney=0.00;
			 if (list != null && list.size() > 0) {
				 for (Map<String, Object> map : list) {
					 Map<String, String> reMap = new HashMap<String, String>();
					 reMap.put("orderCode", map.get("order_code").toString());
					 reMap.put("rbaName", map.get("rba_name").toString());
					 reMap.put("rbsName", map.get("rbs_name").toString());
					 reMap.put("rbbName", map.get("rbb_name")!=null ? map.get("rbb_name").toString():" ");
					 reMap.put("userName", map.get("user_name").toString());
					 reMap.put("time", map.get("TIME").toString());
					 reMap.put("matName", map.get("matName").toString());
					 reMap.put("norm", map.get("NORM").toString());
					 reMap.put("matType", map.get("matType")!=null ? map.get("matType").toString():" ");
					 reMap.put("UnitMoney", map.get("UnitMoney").toString());
					 reMap.put("matNumber", map.get("matNumber").toString());
					 reMap.put("TotalMoney", map.get("TotalMoney").toString());
					 reMap.put("payName", map.get("payName")!=null ? map.get("payName").toString():" ");
					 reMap.put("ProcessName", map.get("ProcessName").toString());
					 reMap.put("ddmx", map.get("ddmx")!=null ?  map.get("ddmx").toString():" ");
					 reMap.put("wlxx", map.get("wlxx")!=null ?  map.get("wlxx").toString():" ");
					 reMap.put("enterpriseType", map.get("enterprise_type")!=null ? map.get("enterprise_type").toString():" ");
					 reMap.put("scopeBusiness", map.get("scope_business")!=null ? map.get("scope_business").toString():" ");
					 reMap.put("number2", map.get("number2")!=null ? map.get("number2").toString():" ");
					 reMap.put("shureNumber", map.get("shureNumber")!=null ? map.get("shureNumber").toString():" ");
					 reMap.put("backNumber", map.get("backNumber")!=null ? map.get("backNumber").toString():" ");
					 reMap.put("backMoney", map.get("backMoney")!=null ? map.get("backMoney").toString():" ");
					 reMap.put("TotalMoney", map.get("TotalMoney")!=null ? map.get("TotalMoney").toString():" ");
					 reMap.put("TotalMoneySum", map.get("TotalMoneySum")!=null ? map.get("TotalMoneySum").toString():" ");

					 Double d1= Double.parseDouble(map.get("matNameSum").toString());
					 countNumber+=d1;
					 Double d2= Double.parseDouble(map.get("TotalMoneySum1").toString());
					 countMoney+=d2;
					 reList.add(reMap);
				 }
			 }
			 dataMap.put("countNumber", countNumber!=null ?countNumber:0d);
			 dataMap.put("countMoney", countMoney!=null ?countMoney:0d);
			 dataMap.put("mxList", reList);
			 Configuration configuration = new Configuration();
	            configuration.setDefaultEncoding("UTF-8");
	            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
	            // 这里我们的模板是放在org.cnzjw.template包下面
	            configuration.setClassForTemplateLoading(this.getClass(),
	                    "/ftl");
	            Template t = configuration.getTemplate("exportAllCgOrderListC.ftl");
	            // 输出文档路径及名称
	            Calendar now = Calendar.getInstance();
	            long l = now.getTimeInMillis();
	            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
	            File file = new File(tmpPath);
	            if (!file.exists()) {
	                file.mkdirs();
	            }
	            String realPath = tmpPath + l + ".xls";
	            File outFile = new File(realPath);
	            Writer out = null;
	            out = new BufferedWriter(new OutputStreamWriter(
	                    new FileOutputStream(outFile), "UTF-8"));
	            t.process(dataMap, out);
	            out.close();
	            String rootUrl = request.getContextPath() + EXPORT_PATH + l + ".xls";
	            Map<String, String> map = new HashMap<String, String>();
	            map.put("path", rootUrl);
	            map.put("fileName", l + ".xls");
	            srm.setObj(map);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    	return srm;
    }

    @Override
    public SysRetrunMessage exportAllBatchList(MdOrderInfo att_MdOrderInfo,
                                                 MdOrderMx att_MdOrderMx,String moiIds) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account.getOrganizaType().equals("20001")) {
                att_MdOrderInfo.setRbaId(account.getOldId());
            }
            if (account.getOrganizaType().equals("20002")) {
                att_MdOrderInfo.setRbsId(account.getOldId());
            }if (moiIds==null) {
            	 if (account.getOrganizaType().equals("100")) {
                 	att_MdOrderInfo.setWzId(account.getOldId());
     			}if (account.getOrganizaType().equals("20003")) {
     				att_MdOrderInfo.setRbbId(account.getOldId());
     			}
			}
            att_MdOrderInfo.setOrderState("1");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            DecimalFormat df = new DecimalFormat("######0.00");
            DecimalFormat df2 = new DecimalFormat("######0");
            Map<String, Object> dataMap = new HashMap<String, Object>();
            int rowCount = 5;
            dataMap.put("orgName", account.getOrgName());
            String timeArea = "";
            if (att_MdOrderInfo.getPlaceOrderTime_start() != null)
                timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_start());
            if (att_MdOrderInfo.getPlaceOrderTime_start() != null || att_MdOrderInfo.getPlaceOrderTime_end() != null)
                timeArea += "-";
            if (att_MdOrderInfo.getPlaceOrderTime_end() != null)
                timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_end());
            dataMap.put("timeArea", timeArea);
            dataMap.put("userName", account.getUserName());
            Double countShu = 0d;
            Double countMoney = 0d;
            List<Map<Object, Object>> list = mdOrderMxDao.getMxListByOrderTwo(att_MdOrderInfo, att_MdOrderMx,moiIds);
            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int index = 0;
            int count=0;
            String countPurchaseUnit=null;
            Double backMoney=0d;
            Double number2Money=0d;
            if (list != null && list.size() > 0) {
                for (Map<Object, Object> obj : list) {
                    index++;
                    Map<String, String> reMap = new HashMap<String, String>();
                    reMap.put("index", index + "");
                    reMap.put("placeOrderTime", obj.get("Place_order_time") != null ? obj.get("Place_order_time").toString().substring(0, 10) : "");
                    String app=obj.get("applicant_Name") != null ? obj.get("applicant_Name").toString() : "";
                    reMap.put("applicantName",app);
                    String purchaseUnit=obj.get("Purchase_unit") != null ? obj.get("Purchase_unit").toString() : "";
                    reMap.put("purchaseUnit", purchaseUnit);
                    //int count1=0;
                    if (purchaseUnit.trim().equals(countPurchaseUnit)) {
						count++;
					}else{
						if (countPurchaseUnit!=null) {
							if (count==0) {
								count=1;
							}
							reMap.put("count",Integer.toString(count));
							//count1=count;
						}
						countPurchaseUnit=purchaseUnit;
						count=0;
					}
                    if (list.size()-1==index) {
                    	 reMap.put("count",Integer.toString(count));
                    	//count1+=count;
					}
                    //订单支付方式
                    reMap.put("payType", att_MdOrderInfo.getPayTypeName()!= null ? att_MdOrderInfo.getPayTypeName():" ");
                    //状态
                    reMap.put("ProcessStatus", att_MdOrderInfo.getProcessStatusName()!= null ? att_MdOrderInfo.getProcessStatusName():" ");
                    //总额
                    reMap.put("placeOrderMoneySum",df.format(att_MdOrderInfo.getPlaceOrderMoney()!= null ?att_MdOrderInfo.getPlaceOrderMoney():0d));
                    //发票抬头
                    reMap.put("billHeard",att_MdOrderInfo.getBillHeard()!= null ?att_MdOrderInfo.getBillHeard():" ");
                    //税号
                    reMap.put("billCode",att_MdOrderInfo.getBillCode()!= null ?att_MdOrderInfo.getBillCode():" ");
                    //收货人
                    reMap.put("Addressee",att_MdOrderInfo.getAddressee()!= null ?att_MdOrderInfo.getAddressee():" ");
                    //联系电话Addressee_telephone
                    reMap.put("AddresseeTelephone",att_MdOrderInfo.getAddresseeTelephone()!= null ?att_MdOrderInfo.getAddresseeTelephone():" ");
                    //收货地址
                    reMap.put("DeliveryAddress",att_MdOrderInfo.getDeliveryAddress()!= null ?att_MdOrderInfo.getDeliveryAddress():" ");
                    //快递公司
                    reMap.put("expressName",att_MdOrderInfo.getExpressName()!= null ?att_MdOrderInfo.getExpressName():" ");
                    //快递单号
                    reMap.put("expressCode",att_MdOrderInfo.getExpressCode()!= null ?att_MdOrderInfo.getExpressCode():" ");
                    //买家留言
                    reMap.put("enterpriseType",att_MdOrderInfo.getEnterpriseType()!= null ?att_MdOrderInfo.getEnterpriseType():" ");
                    //卖家备注
                    reMap.put("scopeBusiness1",att_MdOrderInfo.getScopeBusiness()!= null ?att_MdOrderInfo.getScopeBusiness():" ");
                    //发货数量
                    Double numberCount=obj.get("number2") != null ? Double.parseDouble(obj.get("number2").toString()) : 0d;
                    reMap.put("numberCount",numberCount.toString());
                    //退货数量
                    reMap.put("backNumber", df2.format(Double.parseDouble(obj.get("back_number").toString())));
                    if (obj.get("back_number")!=null) {
                     backMoney=Double.parseDouble(obj.get("back_number").toString()) * Double.parseDouble(obj.get("Unit_money").toString());
					}
                    //退款金额
                    reMap.put("backMoney", df.format(backMoney));
                    //实收金额
                   /* if (obj.get("number2")!=null) {
                    	number2Money=Double.parseDouble(obj.get("number2").toString()) * Double.parseDouble(obj.get("Unit_money").toString());
					}
                    reMap.put("backMoney", df.format(backMoney));*/
                    reMap.put("count", Integer.toString(count));
                    reMap.put("matName", obj.get("mat_name").toString());
                    reMap.put("norm", obj.get("NORM").toString());
                    reMap.put("purchaseAccount", obj.get("purchase_Account").toString());
                    reMap.put("matTypeName", obj.get("type_name") != null ? obj.get("type_name").toString() : "");
                    reMap.put("money2", df.format(Double.parseDouble(obj.get("Unit_money").toString())));
                    reMap.put("matNumber", df2.format(Double.parseDouble(obj.get("mat_number").toString())));
                    reMap.put("totalMoney", df.format(Double.parseDouble(obj.get("Total_money").toString())));
                    countShu += Double.parseDouble(obj.get("mat_number").toString());
                    countMoney += Double.parseDouble(obj.get("Total_money").toString());
                    reList.add(reMap);

                }
                rowCount += list.size();
            }
            dataMap.put("placeOrderMoney", df.format(countMoney));
            dataMap.put("commodityNumber", df2.format(countShu));
            dataMap.put("rowCount", rowCount);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportAllCgOrderList.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            long l = now.getTimeInMillis();
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + l + ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + l + ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", l + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }
    //增加批量导出全部
    @Override
    public SysRetrunMessage exportAllCgOrderList(MdOrderInfo att_MdOrderInfo,
                                                 MdOrderMx att_MdOrderMx) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account.getOrganizaType().equals("20001")) {
                att_MdOrderInfo.setRbaId(account.getOldId());
            }
            if (account.getOrganizaType().equals("20002")) {
                att_MdOrderInfo.setRbsId(account.getOldId());
            }
            att_MdOrderInfo.setOrderState("1");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            DecimalFormat df = new DecimalFormat("######0.00");
            DecimalFormat df2 = new DecimalFormat("######0");
            Map<String, Object> dataMap = new HashMap<String, Object>();
            int rowCount = 5;
            dataMap.put("orgName", account.getOrgName());
            String timeArea = "";
            if (att_MdOrderInfo.getPlaceOrderTime_start() != null)
                timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_start());
            if (att_MdOrderInfo.getPlaceOrderTime_start() != null || att_MdOrderInfo.getPlaceOrderTime_end() != null)
                timeArea += "-";
            if (att_MdOrderInfo.getPlaceOrderTime_end() != null)
                timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_end());
            dataMap.put("timeArea", timeArea);
            dataMap.put("userName", account.getUserName());
            Double countShu = 0d;
            Double countMoney = 0d;
            List<Map<Object, Object>> list = mdOrderMxDao.getMxListByOrder(att_MdOrderInfo, att_MdOrderMx,null, null);
            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int index = 0;
            Integer count=0;
            String countPurchaseUnit=null;
            Double backMoney=0d;
            Double number2Money=0d;
            if (list != null && list.size() > 0) {
                for (Map<Object, Object> obj : list) {
                    index++;
                    Map<String, String> reMap = new HashMap<String, String>();
                    reMap.put("index", index + "");
                    reMap.put("placeOrderTime", obj.get("Place_order_time") != null ? obj.get("Place_order_time").toString().substring(0, 10) : "");
                    String app=obj.get("applicant_Name") != null ? obj.get("applicant_Name").toString() : "";
                    reMap.put("applicantName",app);
                    String purchaseUnit=obj.get("Purchase_unit") != null ? obj.get("Purchase_unit").toString() : "";
                    reMap.put("purchaseUnit", purchaseUnit);
                    Integer count1=0;
                    if (purchaseUnit.trim().equals(countPurchaseUnit)) {
						count++;
					}else{
						if (countPurchaseUnit!=null) {
							if (count==0) {
								count=1;
							}
							/*reMap.put("count",Integer.toString(count));*/
							count1=count;
							reMap.put("count",Integer.toString(count1));
						}
						countPurchaseUnit=purchaseUnit;
						count=0;
					}
                    if (list.size()-1==index) {
                    	count1+=count;
                    	reMap.put("count",Integer.toString(count1));
					}
                    //String str=count1.toString();
                    reMap.put("count1",Integer.toString(count1));
                    //订单支付方式
                    reMap.put("payType", att_MdOrderInfo.getPayTypeName()!= null ? att_MdOrderInfo.getPayTypeName():" ");
                    //状态
                    reMap.put("ProcessStatus", att_MdOrderInfo.getProcessStatusName()!= null ? att_MdOrderInfo.getProcessStatusName():" ");
                    //总额
                    reMap.put("placeOrderMoneySum",df.format(att_MdOrderInfo.getPlaceOrderMoney()!= null ?att_MdOrderInfo.getPlaceOrderMoney():0d));
                    //发票抬头
                    reMap.put("billHeard",att_MdOrderInfo.getBillHeard()!= null ?att_MdOrderInfo.getBillHeard():" ");
                    //税号
                    reMap.put("billCode",att_MdOrderInfo.getBillCode()!= null ?att_MdOrderInfo.getBillCode():" ");
                    //收货人
                    reMap.put("Addressee",att_MdOrderInfo.getAddressee()!= null ?att_MdOrderInfo.getAddressee():" ");
                    //联系电话Addressee_telephone
                    reMap.put("AddresseeTelephone",att_MdOrderInfo.getAddresseeTelephone()!= null ?att_MdOrderInfo.getAddresseeTelephone():" ");
                    //收货地址
                    reMap.put("DeliveryAddress",att_MdOrderInfo.getDeliveryAddress()!= null ?att_MdOrderInfo.getDeliveryAddress():" ");
                    //快递公司
                    reMap.put("expressName",att_MdOrderInfo.getExpressName()!= null ?att_MdOrderInfo.getExpressName():" ");
                    //快递单号
                    reMap.put("expressCode",att_MdOrderInfo.getExpressCode()!= null ?att_MdOrderInfo.getExpressCode():" ");
                    //买家留言
                    reMap.put("enterpriseType",att_MdOrderInfo.getEnterpriseType()!= null ?att_MdOrderInfo.getEnterpriseType():" ");
                    //卖家备注
                    reMap.put("scopeBusiness1",att_MdOrderInfo.getScopeBusiness()!= null ?att_MdOrderInfo.getScopeBusiness():" ");
                    //发货数量
                    Double numberCount=obj.get("number2") != null ? Double.parseDouble(obj.get("number2").toString()) : 0d;
                    reMap.put("numberCount",numberCount.toString());
                    //退货数量
                    reMap.put("backNumber", df2.format(Double.parseDouble(obj.get("back_number").toString())));
                    if (obj.get("back_number")!=null) {
                     backMoney=Double.parseDouble(obj.get("back_number").toString()) * Double.parseDouble(obj.get("Unit_money").toString());
					}
                    //退款金额
                    reMap.put("backMoney", df.format(backMoney));
                    //实收金额
                   /* if (obj.get("number2")!=null) {
                    	number2Money=Double.parseDouble(obj.get("number2").toString()) * Double.parseDouble(obj.get("Unit_money").toString());
					}
                    reMap.put("backMoney", df.format(backMoney));*/
                   //reMap.put("count", Integer.toString(count));
                    reMap.put("matName", obj.get("mat_name").toString());
                    reMap.put("norm", obj.get("NORM").toString());
                    reMap.put("purchaseAccount", obj.get("purchase_Account").toString());
                    reMap.put("matTypeName", obj.get("type_name") != null ? obj.get("type_name").toString() : "");
                    reMap.put("money2", df.format(Double.parseDouble(obj.get("Unit_money").toString())));
                    reMap.put("matNumber", df2.format(Double.parseDouble(obj.get("mat_number").toString())));
                    reMap.put("totalMoney", df.format(Double.parseDouble(obj.get("Total_money").toString())));
                    countShu += Double.parseDouble(obj.get("mat_number").toString());
                    countMoney += Double.parseDouble(obj.get("Total_money").toString());
                    reList.add(reMap);

                }
                rowCount += list.size();
            }
            dataMap.put("placeOrderMoney", df.format(countMoney));
            dataMap.put("commodityNumber", df2.format(countShu));
            dataMap.put("rowCount", rowCount);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportAllCgOrderList.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            long l = now.getTimeInMillis();
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + l + ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + l + ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", l + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }

    //修改订单模板  yanglei
    @Override
    public SysRetrunMessage exportGyOrderList(MdOrderInfo att_MdOrderInfo,
                                              MdOrderMx att_MdOrderMx) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            att_MdOrderInfo.setWzId(account.getOldId());
            att_MdOrderInfo.setOrderState("1");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            DecimalFormat df = new DecimalFormat("######0.00");
            DecimalFormat df2 = new DecimalFormat("######0");
            Map<String, Object> dataMap = new HashMap<String, Object>();
            int rowCount = 5;
            dataMap.put("orgName", account.getOrgName());
            String timeArea = "";
            if (att_MdOrderInfo.getPlaceOrderTime_start() != null)
                timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_start());
            if (att_MdOrderInfo.getPlaceOrderTime_start() != null || att_MdOrderInfo.getPlaceOrderTime_end() != null)
                timeArea += "-";
            if (att_MdOrderInfo.getPlaceOrderTime_end() != null)
                timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_end());
            dataMap.put("timeArea", timeArea);
            dataMap.put("userName", account.getUserName());
            Double countShu = 0d;
            Double countMoney = 0d;
            Double number2Count = 0d;
            Double shureNumberCount = 0d;
            List<Map<Object, Object>> list = mdOrderMxDao.getMxListByOrder(att_MdOrderInfo, att_MdOrderMx,null, null);
            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int index = 0;
            int count=0;
            if (list != null && list.size() > 0) {
                for (Map<Object, Object> obj : list) {
                    index++;
                    Map<String, String> reMap = new HashMap<String, String>();
                    reMap.put("index", index + "");
                    reMap.put("orderCode", obj.get("order_code").toString());
                    reMap.put("purchaseUnit", obj.get("Purchase_unit") != null ? obj.get("Purchase_unit").toString() : "");
                    reMap.put("matName", obj.get("mat_name").toString());
                    reMap.put("norm", obj.get("NORM").toString());
                    reMap.put("money2", df.format(Double.parseDouble(obj.get("Unit_money").toString())));
                    reMap.put("basicUnit", obj.get("Basic_unit").toString());
                    /**
                     * 2019-11-24
                     * yanglei
                     * 添加物料信息表中数据 --
                     */
                    reMap.put("productName", obj.get("product_name") != null ? obj.get("product_name").toString() : "");
                    reMap.put("purchaseAccount", obj.get("purchase_Account").toString());
                    reMap.put("placeOrderTime", obj.get("Place_order_time").toString().substring(0, 10));
                    reMap.put("matNumber", df2.format(Double.parseDouble(obj.get("mat_number").toString())));
                    Double number2 = obj.get("number2") != null ? Double.parseDouble(obj.get("number2").toString()) : 0d;
                    Double money1 = obj.get("money1") != null ? Double.parseDouble(obj.get("money1").toString()) : 0d;
                    reMap.put("number2", df2.format(number2));
                    reMap.put("shureNumber", df2.format(Double.parseDouble(obj.get("shure_number").toString())));
                    reMap.put("money1", df.format(money1));
                    countShu += Double.parseDouble(obj.get("mat_number").toString());
                    number2Count += number2;
                    shureNumberCount += Double.parseDouble(obj.get("shure_number").toString());
                    countMoney += money1;
                    //订单支付方式
                    reMap.put("payType", att_MdOrderInfo.getPayTypeName()!= null ? att_MdOrderInfo.getPayTypeName():" ");
                    //状态
                    reMap.put("ProcessStatus", att_MdOrderInfo.getProcessStatusName()!= null ? att_MdOrderInfo.getProcessStatusName():" ");
                    //总额
                    reMap.put("placeOrderMoneySum",df.format(att_MdOrderInfo.getPlaceOrderMoney()!= null ?att_MdOrderInfo.getPlaceOrderMoney():0d));
                    //发票抬头
                    reMap.put("billHeard",att_MdOrderInfo.getBillHeard()!= null ?att_MdOrderInfo.getBillHeard():" ");
                    //税号
                    reMap.put("billCode",att_MdOrderInfo.getBillCode()!= null ?att_MdOrderInfo.getBillCode():" ");
                    //收货人
                    reMap.put("Addressee",att_MdOrderInfo.getAddressee()!= null ?att_MdOrderInfo.getAddressee():" ");
                    //联系电话Addressee_telephone
                    reMap.put("AddresseeTelephone",att_MdOrderInfo.getAddresseeTelephone()!= null ?att_MdOrderInfo.getAddresseeTelephone():" ");
                    //收货地址
                    reMap.put("DeliveryAddress",att_MdOrderInfo.getDeliveryAddress()!= null ?att_MdOrderInfo.getDeliveryAddress():" ");
                    //快递公司
                    reMap.put("expressName",att_MdOrderInfo.getExpressName()!= null ?att_MdOrderInfo.getExpressName():" ");
                    //快递单号
                    reMap.put("expressCode",att_MdOrderInfo.getExpressCode()!= null ?att_MdOrderInfo.getExpressCode():" ");
                    //买家留言
                    reMap.put("enterpriseType",att_MdOrderInfo.getEnterpriseType()!= null ?att_MdOrderInfo.getEnterpriseType():" ");
                    //卖家备注
                    reMap.put("scopeBusiness1",att_MdOrderInfo.getScopeBusiness()!= null ?att_MdOrderInfo.getScopeBusiness():" ");
                    //发货数量
                    if (att_MdOrderInfo.getProcessStatus()=="3"&&att_MdOrderInfo.getProcessStatus()=="4"&&att_MdOrderInfo.getProcessStatus()=="5") {
                    	reMap.put("commodityNumber1",df2.format(att_MdOrderInfo.getCommodityNumber())!= null ?df2.format(att_MdOrderInfo.getCommodityNumber()):" ");
    				}
                    reMap.put("count",Integer.toString(obj.get("mat_name").toString().length()));
                  /*  //退货数量
                    reMap.put("scopeBusiness1",df2.format(obj.get(key))!= null ?df2.format(obj.getBackNumber()):" "); 
                    //退款金额
                    Double BackMoney=obj.getBackNumber()*obj.getUnitMoney();
                    reMap.put("backMoney",df2.format(BackMoney)!= null ?df2.format(BackMoney):" ");
                    //实收金额
                    Double shiShou=obj.getShureNumber()*obj.getUnitMoney();
                    reMap.put("shiShou",df2.format(shiShou)!= null ?df2.format(shiShou):" ");
                    //实收总汇金额
                    reMap.put("shiShou",df2.format(att_MdOrderInfo.getActualMoney())!= null ?df2.format(att_MdOrderInfo.getActualMoney()):" ");*/

                    reList.add(reMap);
                }
                rowCount += list.size();
            }
            dataMap.put("money1Count", df.format(countMoney));
            dataMap.put("matNumberCount", df2.format(countShu));
            dataMap.put("number2Count", df2.format(number2Count));
            dataMap.put("shureNumberCount", df2.format(shureNumberCount));

            dataMap.put("rowCount", rowCount);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportGyOrderList.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            /**
             * 2019-11-24
             * yanglei
             * 更改命名
             */
            long lo = now.getTimeInMillis();
            Date date = new Date(lo);
            SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmss");
            String l = sd.format(date);
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + "GYSOrderList" + l + ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + "GYSOrderList" + l + ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", "GYSOrderList" + l + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }
    @Override
    public SysRetrunMessage exportDzOrderList(MdOrderInfo att_MdOrderInfo,
                                              MdOrderMx att_MdOrderMx,String moiIds, String excludeMoiIds) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account.getOrganizaType().equals("20001")) {
                att_MdOrderInfo.setRbaId(account.getOldId());
            }
            if (account.getOrganizaType().equals("20002")) {
                att_MdOrderInfo.setRbsId(account.getOldId());
            }if (moiIds==null) {
            	 if (account.getOrganizaType().equals("100")) {
                 	att_MdOrderInfo.setWzId(account.getOldId());
     			}if (account.getOrganizaType().equals("20003")) {
     				att_MdOrderInfo.setRbbId(account.getOldId());
     			}
			}
            /*att_MdOrderInfo.setWzId(account.getOldId());*/
            att_MdOrderInfo.setOrderState("1");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            DecimalFormat df = new DecimalFormat("######0.00");
            DecimalFormat df2 = new DecimalFormat("######0");
            Map<String, Object> dataMap = new HashMap<String, Object>();
            int rowCount = 6;
            dataMap.put("orgName", account.getOrgName());
            String timeArea = "";
            if (att_MdOrderInfo.getPlaceOrderTime_start() != null)
                timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_start());
            if (att_MdOrderInfo.getPlaceOrderTime_start() != null || att_MdOrderInfo.getPlaceOrderTime_end() != null)
                timeArea += "-";
            if (att_MdOrderInfo.getPlaceOrderTime_end() != null)
                timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_end());
            dataMap.put("timeArea", timeArea);
            Date newDate = new Date();
            dataMap.put("createDate", sdf.format(newDate));
            dataMap.put("createRen", account.getUserName());
            dataMap.put("purchaseUnit", att_MdOrderInfo.getPurchaseUnit() != null ? att_MdOrderInfo.getPurchaseUnit() : "");
            Double countMoney = 0d;
            Double number2Count = 0d;
            Double shureNumberCount = 0d;

            //商品金额
            Double countMoney2 = 0d;
            //商品金额合计
            Double countMoneyCount = 0d;
            List<Map<Object, Object>> list = mdOrderMxDao.getMxListByOrder(att_MdOrderInfo, att_MdOrderMx, moiIds, excludeMoiIds);
            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int index = 0;
            if (list != null && list.size() > 0) {
                for (Map<Object, Object> obj : list) {
                    index++;
                    Map<String, String> reMap = new HashMap<String, String>();
                    reMap.put("index", index + "");
                    reMap.put("orderCode", obj.get("order_code").toString());
                    reMap.put("placeOrderTime", obj.get("Place_order_time").toString().substring(0, 10));
                    reMap.put("matName", obj.get("mat_name").toString());
                    reMap.put("norm", obj.get("NORM").toString());
                    Double money2 = obj.get("money2") != null ? Double.parseDouble(obj.get("money2").toString()) : 0d;
                    reMap.put("money2", df.format(money2));
                    reMap.put("norm", obj.get("NORM").toString());
                    reMap.put("basicUnit", obj.get("Basic_unit").toString());
                    /**
                     * 2019-11-24
                     * yanglei
                     * 添加物料信息表中数据 --productName
                     */
                    reMap.put("productName", obj.get("product_name") != null ? obj.get("product_name").toString() : "");
                    reMap.put("salesCode", obj.get("sales_code") != null ? obj.get("sales_code").toString() : "");
                    Double number2 = obj.get("number2") != null ? Double.parseDouble(obj.get("number2").toString()) : 0d;
                    Double money1 = obj.get("money1") != null ? Double.parseDouble(obj.get("money1").toString()) : 0d;
                    reMap.put("number2", df2.format(number2));
                    reMap.put("shureNumber", df2.format(Double.parseDouble(obj.get("shure_number").toString())));
                    reMap.put("money1", df.format(money1));
                    number2Count += number2;
                    shureNumberCount += Double.parseDouble(obj.get("shure_number").toString());
                    countMoney += money1;
                    //数量
                    Double matNumber = obj.get("mat_number") != null ? Double.parseDouble(obj.get("mat_number").toString()) : 0d;
                    reMap.put("matNumber", df2.format(matNumber));
                    countMoney2 = money2 * matNumber;
                    //金额
                    reMap.put("countMoney2", df.format(countMoney2));
                    //商品金额统计
                    countMoneyCount += countMoney2;
                    reList.add(reMap);
                }
                rowCount += list.size();
            }
            //商品金额统计
            dataMap.put("countMoneyCount", df.format(countMoneyCount));
            dataMap.put("money1Count", df.format(countMoney));
            dataMap.put("number2Count", df2.format(number2Count));
            dataMap.put("shureNumberCount", df2.format(shureNumberCount));
            dataMap.put("rowCount", rowCount);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportDzOrderList.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            long lo = now.getTimeInMillis();
            Date date = new Date(lo);
            SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
            String l = sd.format(date);
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + "GYSdzd" + l + ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + "GYSdzd" + l + ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", "GYSdzd" + l + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }
    /**
     * 增加导出7天数据分析2
     */
  //增加导出7天数据分析
    //先导出md_order_info 表的数据  再导出 md_order_mx 的数据
    //再填进来
  	public SysRetrunMessage exportSevenDayTwo() throws HSKException{
  		 SysRetrunMessage srm = new SysRetrunMessage(1l);
  		DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
  		 Date StartDate=new Date();
  		 Date EndDate=new Date(StartDate.getTime()- 24*60*60*1000*7);
  		 Date sevenDayDate=new Date(EndDate.getTime()- 24*60*60*1000*7);
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
         SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy.MM.dd");
        // SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
         Map<String, Object> dataMap = new HashMap<String, Object>();
         Date NewDate=new Date();
         dataMap.put("newDate", sdf3.format(NewDate));
         //dataMap.put("newDate", sdf.format(NewDate));
         SysUserInfo sysUserInfo=this.GetOneSessionAccount();
         dataMap.put("userName", sysUserInfo.getUserName());
         dataMap.put("OrgGxIdStr",sysUserInfo.getOrgGxId_Str());

         //开始日期
         dataMap.put("EndDate", sdf3.format(EndDate));
         //截止日期
         dataMap.put("StartDate", sdf3.format(StartDate));
         //7天气日期
         dataMap.put("sevenDayDate", sdf3.format(sevenDayDate));
         try {
        	 List<Map<String, Object>> list = mdOrderMxDao.getSevenListCountAll(EndDate, StartDate);
 				List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
 				Double OrderNums=0.0;
 				Double prices=0.0;
 				Double TotalMoneys=0.0;
 				int index = 0;
 				if (list != null && list.size() > 0) {
 					for (Map<String, Object> map : list) {
 						 index++;
 						 Map<String, String> reMap = new HashMap<String, String>();
 						 reMap.put("index",index+"");
 						 //集团
 						 reMap.put("rbaName", map.get("rba_name").toString());
 						 //医院
 						 reMap.put("rbsName", map.get("rbs_name").toString());
 						 //门诊
 						 Object object=map.get("rbb_name");
 						 String rbbName=String.valueOf(object);
 						 reMap.put("rbbName", rbbName != "null" ? rbbName : " ");
 						 //日期
 						 Object object1=map.get("time1");
						 String time1=String.valueOf(object1);
						 //SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
 						 reMap.put("time1", time1.toString());
 						 //购买数量
 						//df.format(att_MdOrderInfo.getPlaceOrderMoney()!= null ?att_MdOrderInfo.getPlaceOrderMoney():0d)
 						reMap.put("matNumber", df2.format(map.get("matNumber")!= null ?map.get("matNumber"):0d));
 						//交易数量
 						reMap.put("OrderNum", df2.format(map.get("OrderNum")!= null ?map.get("OrderNum"):0d));
 						//产品分类
 						Object objectTypenum=map.get("typenum");
 						String str=String.valueOf(objectTypenum);
 						reMap.put("typenum", new String((byte[])objectTypenum,"UTF-8"));
 						//平均单价
 						reMap.put("price", df.format(map.get("price")!= null ?map.get("price"):0d));
 						//总金额
 						reMap.put("TotalMoney", df.format(map.get("TotalMoney")!= null ?map.get("TotalMoney"):0d));
 						Object OrderNumObj=map.get("OrderNum");
 						Double OrderNumObj1=Double.parseDouble(String.valueOf(OrderNumObj));
 						OrderNums+=OrderNumObj1;
 						/*Object priceObj=map.get("price");
 						Double priceObj1=Double.parseDouble(String.valueOf(priceObj));
 						prices+=priceObj1;*/
 						Object TotalMoneyObj=map.get("TotalMoney");
 						Double TotalMoneyObj1=Double.parseDouble(String.valueOf(TotalMoneyObj));
 						TotalMoneys+=TotalMoneyObj1;
 						reList.add(reMap);
				}
 					}
 				//总的平均单价
  				if (OrderNums!=0) {
  					prices=TotalMoneys/OrderNums;
				}
 				dataMap.put("OrderNums", OrderNums !=null ?df2.format(OrderNums):"");
 				dataMap.put("prices", prices !=null ?df.format(prices):"");
 				dataMap.put("TotalMoneys", TotalMoneys !=null ?df.format(TotalMoneys):"");

 				 List<Map<String, Object>> list1 = mdOrderMxDao.getSevenListCountAll(sevenDayDate, EndDate);
  				List<Map<String, String>> reList1 = new ArrayList<Map<String, String>>();
  				//后七天的数据
  				Double OrderNums1=0.0;
 				Double prices1=0.0;
 				Double TotalMoneys1=0.0;
  				if (list1 != null && list1.size() > 0) {
 					for (Map<String, Object> map : list1) {
 						 index++;
 						 Map<String, String> reMap = new HashMap<String, String>();
 						 reMap.put("index",index+"");
 						 //集团
 						 reMap.put("rbaName", map.get("rba_name").toString());
 						 //医院
 						 //reMap.put("rbsName", map.get("rbs_name")!=null ? map.get("rbs_name"):"");
 						 //门诊
 						 //reMap.put("rbbName", map.get("rbb_name")!=null ? map.get("rbb_name"):"");
 						 //日期
 						 //reMap.put("time1", map.get("time1")!=null ? map.get("time1"):"");
 						 //购买数量
 						//df.format(att_MdOrderInfo.getPlaceOrderMoney()!= null ?att_MdOrderInfo.getPlaceOrderMoney():0d)
 						reMap.put("matNumber", df2.format(map.get("matNumber")!= null ?map.get("matNumber"):0d));
 						//交易数量
 						reMap.put("OrderNum", df2.format(map.get("OrderNum")!= null ?map.get("OrderNum"):0d));
 						//产品分类
 						Object object=map.get("typenum");
 						String str=String.valueOf(object);
 						reMap.put("typenum", str!=null ? str:"");
 						//平均单价
 						reMap.put("price", df.format(map.get("price")!= null ?map.get("price"):0d));
 						//总金额
 						reMap.put("TotalMoney", df.format(map.get("TotalMoney")!= null ?map.get("TotalMoney"):0d));
 						Object OrderNumObj=map.get("OrderNum");
 						Double OrderNumObj1=Double.parseDouble(String.valueOf(OrderNumObj));
 						OrderNums1+=OrderNumObj1;
 						/*Object priceObj=map.get("price");
 						Double priceObj1=Double.parseDouble(String.valueOf(priceObj));
 						prices1+=priceObj1;*/
 						Object TotalMoneyObj=map.get("TotalMoney");
 						Double TotalMoneyObj1=Double.parseDouble(String.valueOf(TotalMoneyObj));
 						TotalMoneys1+=TotalMoneyObj1;

				}
 					}
  			//总的平均单价
  				if (OrderNums1!=0) {
  					prices1=TotalMoneys1/OrderNums1;
				}
  				dataMap.put("OrderNums1", OrderNums1 !=null ?df2.format(OrderNums1):"");
 				dataMap.put("prices1", prices1 !=null ?df.format(prices1):"");
 				dataMap.put("TotalMoneys1", TotalMoneys1 !=null ?df.format(TotalMoneys1):"");
			 dataMap.put("mxList", reList);
			 Configuration configuration = new Configuration();
             configuration.setDefaultEncoding("UTF-8");
             // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
             // 这里我们的模板是放在org.cnzjw.template包下面
             configuration.setClassForTemplateLoading(this.getClass(),
                     "/ftl");
             Template t = configuration.getTemplate("exportSevenCountC.ftl");
             // 输出文档路径及名称
             Calendar now = Calendar.getInstance();
             long lo = now.getTimeInMillis();
             Date date = new Date(lo);
             SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日");
             String l =sd.format(EndDate)+"至"+sd.format(date);
             String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
             File file = new File(tmpPath);
             if (!file.exists()) {
                 file.mkdirs();
             }
             String realPath = tmpPath + l +"交易分析表" + ".xls";
             File outFile = new File(realPath);
             Writer out = null;
             out = new BufferedWriter(new OutputStreamWriter(
                     new FileOutputStream(outFile), "UTF-8"));
             t.process(dataMap, out);
             out.close();
             String rootUrl = request.getContextPath() + EXPORT_PATH + l +"交易分析表"+ ".xls";
             Map<String, String> map = new HashMap<String, String>();
             map.put("path", rootUrl);
             map.put("fileName", l+"交易分析表" + ".xls");
             srm.setObj(map);
         } catch (Exception e) {
             e.printStackTrace();
         }
		return srm;
  	}
    /**
	 * =================================================================
	 * 以下是首页中的数据service内容
	 * 2019-12-29
	 * yanglei
	 */
 /* //增加导出7天数据分析
  	public SysRetrunMessage exportSevenDay() throws HSKException{
  		 SysRetrunMessage srm = new SysRetrunMessage(1l);
  		 Date StartDate=new Date();
  		 Date EndDate=new Date(StartDate.getTime()- 24*60*60*1000*7);
  		 Date sevenDayDate=new Date(EndDate.getTime()- 24*60*60*1000*7);
  		  try {
              MdOrderInfo att_MdOrderInfo = mdOrderInfoDao.findMdOrderInfoByDate(EndDate,StartDate);
              MdSupplier mdSupplier = mdSupplierDao.findMdSupplierById(att_MdOrderInfo.getWzId());
              MdOrderMx mdOrderMx = new MdOrderMx();
              mdOrderMx.setPlaceOrderTime_start(StartDate);
              mdOrderMx.setPlaceOrderTime_end(EndDate);
              List<MdOrderMx> mxList = mdOrderMxDao.getListByMdOrderMx(mdOrderMx);
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
              DecimalFormat df = new DecimalFormat("######0.00");
              DecimalFormat df2 = new DecimalFormat("######0");
              Map<String, Object> dataMap = new HashMap<String, Object>();
              int rowCount = 17;
              dataMap.put("supplierName", att_MdOrderInfo.getApplicantName());
              dataMap.put("purchaseUnit", att_MdOrderInfo.getPurchaseUnit());
              dataMap.put("legalPerson", mdSupplier.getLegalPerson() != null ? mdSupplier.getLegalPerson() : "");
              dataMap.put("phoneNumber", mdSupplier.getPhoneNumber() != null ? mdSupplier.getPhoneNumber() : "");
              dataMap.put("selprovince", mdSupplier.getSelprovince() != null ? mdSupplier.getSelprovince() : "");
              dataMap.put("selcity", mdSupplier.getSelcity() != null ? mdSupplier.getSelcity() : "");
              dataMap.put("selarea", mdSupplier.getSelarea() != null ? mdSupplier.getSelarea() : "");
              dataMap.put("corporateDomicile", mdSupplier.getCorporateDomicile() != null ? mdSupplier.getCorporateDomicile() : "");
              dataMap.put("addressee", att_MdOrderInfo.getAddressee());
              dataMap.put("addresseeTelephone", att_MdOrderInfo.getAddresseeTelephone());
              dataMap.put("province", att_MdOrderInfo.getProvince());
              dataMap.put("city", att_MdOrderInfo.getCity());
              dataMap.put("area", att_MdOrderInfo.getArea());
              dataMap.put("deliveryAddress", att_MdOrderInfo.getDeliveryAddress());
              //增加买家留言
              dataMap.put("enterpriseType", att_MdOrderInfo.getEnterpriseType()!=null ?  att_MdOrderInfo.getEnterpriseType():"");
              //增加订单号
              dataMap.put("orderCode", att_MdOrderInfo.getOrderCode()!=null ?  att_MdOrderInfo.getOrderCode():"");
              //增加创建时间
              Date NewDate=new Date();
              dataMap.put("newDate", sdf.format(NewDate)); 
              //增加单位
              SysUserInfo sysUserInfo=this.GetOneSessionAccount();
              dataMap.put("OrgGxIdStr",sysUserInfo.getOrgGxId_Str());
              dataMap.put("userName", sysUserInfo.getUserName());
              dataMap.put("StartDate",sdf.format(StartDate));
              dataMap.put("EndDate",sdf.format(EndDate));
              if (att_MdOrderInfo.getMoney2() != null)
                  dataMap.put("money2", df.format(att_MdOrderInfo.getMoney2()));
              else
                  dataMap.put("money2", "0.00");
              if (att_MdOrderInfo.getMoney3() != null)
                  dataMap.put("money3", df.format(att_MdOrderInfo.getMoney3()));
              else
                  dataMap.put("money3", "0.00");
              dataMap.put("commodityNumber", df2.format(att_MdOrderInfo.getCommodityNumber()));
              dataMap.put("placeOrderMoney", df.format(att_MdOrderInfo.getPlaceOrderMoney()));
              dataMap.put("scopeBusiness", att_MdOrderInfo.getScopeBusiness() != null ? att_MdOrderInfo.getScopeBusiness() : " ");
              dataMap.put("purchaseAccount", att_MdOrderInfo.getPurchaseAccount());
              dataMap.put("placeOrderTime", sdf.format(att_MdOrderInfo.getPlaceOrderTime()));
              dataMap.put("rbaId", att_MdOrderInfo.getRbaName()!=null ? att_MdOrderInfo.getRbaName() :" ");
              dataMap.put("rbsId", att_MdOrderInfo.getRbsName()!=null ? att_MdOrderInfo.getRbsName():" ");
              dataMap.put("rbbId", att_MdOrderInfo.getRbbName()!=null ? att_MdOrderInfo.getRbbName():" ");
              List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
              int index = 0;
             Double LackNumber=0.0;
             Double countNumber2=0.0;
             Double countUnitMoney=0.0;
             Double avgUnitMoney=0.0;
             Double countTotalMoney=0.0;
             int conutMxList=0;
              for (MdOrderMx obj : mxList) {
                  index++;
                  Map<String, String> reMap = new HashMap<String, String>();
                  reMap.put("index", index + "");
                  reMap.put("PurchaseUnit", obj.getPurchaseUnit()!= null ?obj.getPurchaseUnit() :"");
                  reMap.put("PlaceOrderTime", sdf.format(obj.getPlaceOrderTime())!= null ?sdf.format(obj.getPlaceOrderTime()) :"");
                  reMap.put("number2", df2.format(obj.getNumber2()!= null ? obj.getNumber2() :0d));
                  reMap.put("unitMoney", df.format(obj.getUnitMoney()!= null ? obj.getUnitMoney() :0d));
                  //reMap.put("unitMoney", df.format(obj.getUnitMoney()!= null ? obj.getUnitMoney() :0d));
                  reMap.put("matName", obj.getMatName());
                 // reMap.put("matType",  obj.getmat);
                  reMap.put("mmfName", obj.getNorm());
                  reMap.put("basicUnit", obj.getBasicUnit());
                  //增加型号名称
                  reMap.put("orderCode", att_MdOrderInfo.getOrderCode()!=null ?  att_MdOrderInfo.getOrderCode():"");
                  reMap.put("matTypeName", obj.getMatTypeName()!= null ? obj.getMatTypeName() : "");
                  //增加型号编号
                  reMap.put("mmfCode", obj.getMmfCode() != null ? obj.getMmfCode() : "");
                  reMap.put("matNumber", df2.format(obj.getMatNumber()));
                  reMap.put("totalMoney", df.format(obj.getTotalMoney()!= null ? obj.getTotalMoney() :0d));
                  LackNumber=obj.getMatNumber()-obj.getShureNumber();
                  reMap.put("LackNumber", df2.format(LackNumber));
                  if (obj.getNumber2()!=null) {
                	  countNumber2+=obj.getNumber2();
				}
                  if (obj.getUnitMoney()!=null&&obj.getNumber2()!=null&&obj.getNumber2()!=0) {
                	  countUnitMoney+=obj.getUnitMoney();
                	  avgUnitMoney=countUnitMoney/countNumber2;
				}
                  if (obj.getTotalMoney()!=null) {
                	  countTotalMoney+=obj.getTotalMoney();
				}
                  reMap.put("rbaId", att_MdOrderInfo.getRbaName()!=null ? att_MdOrderInfo.getRbaName() :" ");
                  reMap.put("rbsId", att_MdOrderInfo.getRbsName()!=null ? att_MdOrderInfo.getRbsName():" ");
                  reMap.put("rbbId", att_MdOrderInfo.getRbbName()!=null ? att_MdOrderInfo.getRbbName():" ");
                  reList.add(reMap);
                  conutMxList=mxList.size();
              }
              dataMap.put("conutMxList",Integer.toString(conutMxList));
              rowCount += mxList.size();
              dataMap.put("avgUnitMoney", df2.format(avgUnitMoney));
              dataMap.put("countTotalMoney", df.format(countTotalMoney));
              dataMap.put("countNumber2", df2.format(countNumber2));
              dataMap.put("rowCount", rowCount);
              dataMap.put("rowCount2", rowCount - 1);
              //7天-14天数据
              MdOrderMx mdOrderMx1 = new MdOrderMx();
              mdOrderMx.setPlaceOrderTime_start(EndDate);
              mdOrderMx.setPlaceOrderTime_end(sevenDayDate);
              List<MdOrderMx> mxList1 = mdOrderMxDao.getListByMdOrderMx(mdOrderMx);
              List<Map<String, String>> reList1 = new ArrayList<Map<String, String>>();
              Double countNumber2Seven=0.0;
              Double countUnitMoneySeven=0.0;
              Double avgUnitMoneySeven=0.0;
              Double countTotalMoneySeven=0.0;
              for (MdOrderMx obj : mxList1) {
            	  Map<String, String> reMap1 = new HashMap<String, String>();
            	  if (obj.getNumber2()!=null) {
            		  countNumber2Seven+=obj.getNumber2();
            	  }
                  if (obj.getUnitMoney()!=null&&obj.getNumber2()!=null&&obj.getNumber2()!=0) {
                	  countUnitMoneySeven+=obj.getUnitMoney();
                	  avgUnitMoneySeven=countUnitMoneySeven/countNumber2Seven;
                  }
                  if (obj.getTotalMoney()!=null) {
                	  countTotalMoneySeven+=obj.getTotalMoney();
                  }
                  reList1.add(reMap1);
              }
              dataMap.put("mxList", reList);
              dataMap.put("countNumber2Seven", df2.format(countNumber2Seven));
              dataMap.put("avgUnitMoneySeven", df.format(avgUnitMoneySeven));
              dataMap.put("countTotalMoneySeven", df2.format(countTotalMoneySeven));
              dataMap.put("EndDate1",sdf.format(EndDate));
              dataMap.put("sevenDayDate",sdf.format(sevenDayDate));
              Configuration configuration = new Configuration();
              configuration.setDefaultEncoding("UTF-8");
              // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
              // 这里我们的模板是放在org.cnzjw.template包下面
              configuration.setClassForTemplateLoading(this.getClass(),
                      "/ftl");
              Template t = configuration.getTemplate("exportSevenCountC.ftl");
              // 输出文档路径及名称
              Calendar now = Calendar.getInstance();
              long l = now.getTimeInMillis();
              String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
              File file = new File(tmpPath);
              if (!file.exists()) {
                  file.mkdirs();
              }
              String realPath = tmpPath + l + ".xls";
              File outFile = new File(realPath);
              Writer out = null;
              out = new BufferedWriter(new OutputStreamWriter(
                      new FileOutputStream(outFile), "UTF-8"));
              t.process(dataMap, out);
              out.close();
              String rootUrl = request.getContextPath() + EXPORT_PATH + l + ".xls";
              Map<String, String> map = new HashMap<String, String>();
              map.put("path", rootUrl);
              map.put("fileName", l + ".xls");
              srm.setObj(map);
          } catch (Exception e) {
              e.printStackTrace();
          }
  		 
		return srm;
  		
  	}*/
  //增加订单总金额
    @Override
    public SysRetrunMessage countOrderMoney() throws HSKException {
    	 SysRetrunMessage srm = new SysRetrunMessage(1l);
    	 Map<String, String> SeMap = new HashMap<String, String>();
    	 Integer suiId = null;
    	 try {
    	 SysUserInfo account = this.GetOneSessionAccount();
    	 if (account == null) {
    	     return srm;
         }
    	 if(account.getOrganizaType().equals("0")){
        	 suiId = account.getOldId();
        }
    	 Integer countOrers = mdOrderInfoDao.CountOrers(null);
    	 String countOrers1=Integer.toString(countOrers);
    	 SeMap.put("countOrers", countOrers1);
    	 Double placeOrderMoneys=mdOrderInfoDao.CountMoneys(null);
    	 SeMap.put("placeOrderMoneys", Double.toString(placeOrderMoneys));
    	 Double RetreatPlaceOrderMoney=mdOrderInfoDao.RetreatCountMoney(null);
    	 SeMap.put("RetreatPlaceOrderMoney", Double.toString(RetreatPlaceOrderMoney));
    	 Double TransactionMoneyCount=mdOrderInfoDao.TransactionCountMoney(null);
    	 SeMap.put("TransactionMoneyCount", Double.toString(TransactionMoneyCount));
    	//增加交易实时战报中的今日下单
    	 Integer TransactionCount = mdOrderInfoDao.TransactionCount(null);
    	 String TransactionCount1=Integer.toString(TransactionCount);
    	 SeMap.put("TransactionCount", TransactionCount1);
    	 //增加交易实战是战报中的今日下单百分比
    	 Integer PercentageCount = mdOrderInfoDao.PercentageCount(null);
    	 String PercentageCount1=Integer.toString(PercentageCount);
    	 SeMap.put("PercentageCount", PercentageCount1);
    	 //仓管申领中的数据
    	 //入库次数
    	 Integer WarehouseCount  = mdOrderInfoDao.getOrderWarehouseCount(null);
    	 SeMap.put("WarehouseCount", Integer.toString(WarehouseCount));
    	 //入库金额
    	 Double WarehouseMoney  = mdOrderInfoDao.getOrderWarehouseMoney(null);
    	 SeMap.put("WarehouseMoney", Double.toString(WarehouseMoney));
    	 //申领次数
    	 Integer ApplyCount  = mdOrderInfoDao.getOrderApplyCount(null);
    	 SeMap.put("ApplyCount", Integer.toString(ApplyCount));
    	 //申领金额
    	 Double  ApplyMoney  = mdOrderInfoDao.getOrderApplyMoney(null);
    	 SeMap.put("ApplyMoney", Double.toString(ApplyMoney));
    	 //退款退货次数
    	 Integer RetreatCount  = mdOrderInfoDao.getOrderRetreatCount(null);
    	 SeMap.put("RetreatCount", Integer.toString(RetreatCount));
    	 //退款退货金额
    	 Double  RetreatMoney  = mdOrderInfoDao.getOrderRetreatMoney(null);
    	 SeMap.put("RetreatMoney", Double.toString(RetreatMoney));
    	 //集团用户统计
    	 Integer groupCount=mdOrderInfoDao.groupCount(null);
    	 SeMap.put("groupCount", Integer.toString(groupCount));
    	 //医院用户统计
    	 Integer hospitalCount=mdOrderInfoDao.hospitalCount(null);
    	 SeMap.put("hospitalCount", Integer.toString(hospitalCount));
    	 //门诊用户统计
    	 Integer departmentCount=mdOrderInfoDao.departmentCount(null);
    	 SeMap.put("departmentCount", Integer.toString(departmentCount));
    	 /*Date newDate=new Date();
    	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");*/
    	 srm.setObj(SeMap);
    	 } catch (HSKDBException e) {
             e.printStackTrace();
         }
    	return srm;
    	}
    //增加商品交易实时战报中的数据
    public PagerModel getOrderMxListByTransaction(Integer limit, Integer page) throws HSKException {
    	PagerModel pm=new PagerModel();
    	try {
			List<Map<String,Object>> mxList = mdOrderInfoDao.getOrderMxListByTransaction(limit, page);
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxList.size());
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
    	 return pm;
    	 }
    //增加前端导出方法数据  2020-01-08
    public PagerModel getOrderMxListByAll() throws HSKException{
    	PagerModel pm=new PagerModel();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    	try {
			List<Map<String,Object>> mxList = mdOrderMxDao.getSevenListCountTwo(null, null);
			for (Map<String, Object> map : mxList) {
				if (map.get("rbbId")==null) {
					map.put("rbbId1", "");
				}else{
					map.put("rbbId1",map.get("rbbId"));
				}
				map.put("PlaceOrderTime1", sdf.format(map.get("PlaceOrderTimeO")));
			}
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxList.size());
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
    	 return pm;
    	 }
  //商品总览
    public SysRetrunMessage materielState() throws HSKException {
    	SysRetrunMessage srm=new SysRetrunMessage(1l);
    	 Map<String, Integer> reMap = new HashMap<String, Integer>();
    	 try {
    		 //全部商品
			Integer allmaterielCount = mdOrderInfoDao.materielState(0);
			//已上架商品
			Integer ysjCount = mdOrderInfoDao.materielState(1);
			//已下架商品
			Integer yxjCount = mdOrderInfoDao.materielState(2);
			reMap.put("allmaterielCount", allmaterielCount);
			reMap.put("ysjCount", ysjCount);
			reMap.put("yxjCount", yxjCount);
			srm.setObj(reMap);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return srm;

    }
    //商品种类统计
    public SysRetrunMessage materielType() throws HSKException{
    	SysRetrunMessage srm=new SysRetrunMessage(1l);
    	 Map<String, Integer> reMap = new HashMap<String, Integer>();
    	 try {
    		Integer matType = mdOrderInfoDao.materielType(null);
			Integer matType1 = mdOrderInfoDao.materielType("2201");
			Integer matType2 = mdOrderInfoDao.materielType("2202");
			Integer matType3 = mdOrderInfoDao.materielType("2203");
			Integer matType4 = mdOrderInfoDao.materielType("2204");
			Integer matType5 = mdOrderInfoDao.materielType("2205");
			Integer matType6 = mdOrderInfoDao.materielType("2206");
			Integer matType7 = mdOrderInfoDao.materielType("2207");
			Integer matType8 = mdOrderInfoDao.materielType("2208");
			reMap.put("matType", matType);
			reMap.put("matType1", matType1);
			reMap.put("matType2", matType2);
			reMap.put("matType3", matType3);
			reMap.put("matType4", matType4);
			reMap.put("matType5", matType5);
			reMap.put("matType6", matType6);
			reMap.put("matType7", matType7);
			reMap.put("matType8", matType8);
			srm.setObj(reMap);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return srm;
    }
    public SysRetrunMessage materielAnalysis(Integer value) throws HSKException{
    	SysRetrunMessage srm=new SysRetrunMessage(1l);
    	Map<String, String> reMap = new HashMap<String, String>();
    	try {
			Integer AnalysisCount0 = mdOrderInfoDao.materielAnalysis(value,null);
			Integer AnalysisCount1 = mdOrderInfoDao.materielAnalysis(value,null);
			Integer AnalysisCount2 = mdOrderInfoDao.materielAnalysis(value,null);
			Integer AnalysisCount3 = mdOrderInfoDao.materielAnalysis(value,null);
			//一天交易成功订单量
			reMap.put("AnalysisCount0", Integer.toString(AnalysisCount0));
			//昨天一天交易成功订单量
			reMap.put("AnalysisCount1",Integer.toString(AnalysisCount1));
			//一周一天交易成功订单量
			reMap.put("AnalysisCount2", Integer.toString(AnalysisCount2));
			//一月一天交易成功订单量
			reMap.put("AnalysisCount3", Integer.toString(AnalysisCount3));
			/**
			 * 总订单量
			 */
			Integer AnalysisCounts0 = mdOrderInfoDao.materielAnalysisCounts(value,null);
			Integer AnalysisCounts1 = mdOrderInfoDao.materielAnalysisCounts(value,null);
			Integer AnalysisCounts2 = mdOrderInfoDao.materielAnalysisCounts(value,null);
			Integer AnalysisCounts3 = mdOrderInfoDao.materielAnalysisCounts(value,null);
			reMap.put("AnalysisCounts0", Integer.toString(AnalysisCounts0));
			reMap.put("AnalysisCounts1", Integer.toString(AnalysisCounts1));
			reMap.put("AnalysisCounts2", Integer.toString(AnalysisCounts2));
			reMap.put("AnalysisCounts3", Integer.toString(AnalysisCounts3));
			/**
			 * 成交额
			 */
			Double ActualMoney0=mdOrderInfoDao.materielAnalysisMoney(value,null);
			Double ActualMoney1=mdOrderInfoDao.materielAnalysisMoney(value,null);
			Double ActualMoney2=mdOrderInfoDao.materielAnalysisMoney(value,null);
			Double ActualMoney3=mdOrderInfoDao.materielAnalysisMoney(value,null);
			reMap.put("ActualMoney0",Double.toString(ActualMoney0));
			reMap.put("ActualMoney1",Double.toString(ActualMoney1));
			reMap.put("ActualMoney2",Double.toString(ActualMoney2));
			reMap.put("ActualMoney3",Double.toString(ActualMoney3));
			/**
			 * 成交总额
			 */
			Double ActualMoneys0=mdOrderInfoDao.materielAnalysisMoneys(value,null);
			Double ActualMoneys1=mdOrderInfoDao.materielAnalysisMoneys(value,null);
			Double ActualMoneys2=mdOrderInfoDao.materielAnalysisMoneys(value,null);
			Double ActualMoneys3=mdOrderInfoDao.materielAnalysisMoneys(value,null);
			reMap.put("ActualMoneys0",Double.toString(ActualMoneys0));
			reMap.put("ActualMoneys1",Double.toString(ActualMoneys1));
			reMap.put("ActualMoneys2",Double.toString(ActualMoneys2));
			reMap.put("ActualMoneys3",Double.toString(ActualMoneys3));

			//人
			Integer Pepole0=mdOrderInfoDao.materielAvgMoney(value,null);
			Integer Pepole1=mdOrderInfoDao.materielAvgMoney(value,null);
			Integer Pepole2=mdOrderInfoDao.materielAvgMoney(value,null);
			Integer Pepole3=mdOrderInfoDao.materielAvgMoney(value,null);
			reMap.put("Pepole0", Integer.toString(Pepole0));
			reMap.put("Pepole1", Integer.toString(Pepole1));
			reMap.put("Pepole2", Integer.toString(Pepole2));
			reMap.put("Pepole3", Integer.toString(Pepole3));

			//总消费
			Double MoneyAvgs0=mdOrderInfoDao.materielAvgMoneys(value,null);
			Double MoneyAvgs1=mdOrderInfoDao.materielAvgMoneys(value,null);
			Double MoneyAvgs2=mdOrderInfoDao.materielAvgMoneys(value,null);
			Double MoneyAvgs3=mdOrderInfoDao.materielAvgMoneys(value,null);
			reMap.put("MoneyAvgs0",Double.toString(MoneyAvgs0));
			reMap.put("MoneyAvgs1",Double.toString(MoneyAvgs1));
			reMap.put("MoneyAvgs2",Double.toString(MoneyAvgs2));
			reMap.put("MoneyAvgs3",Double.toString(MoneyAvgs3));
			srm.setObj(reMap);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return srm;
    }
    //转化交易率
    public SysRetrunMessage materielConversion(Integer value) throws HSKException{
    	SysRetrunMessage srm=new SysRetrunMessage(1l);
    	Map<String, String> reMap = new HashMap<String, String>();
		try {
			//浏览量
			Integer browse0 = mdOrderInfoDao.materielConversionBrowse(value,null);
			Integer browse1 = mdOrderInfoDao.materielConversionBrowse(value,null);
			Integer browse2 = mdOrderInfoDao.materielConversionBrowse(value,null);
			Integer browse3 = mdOrderInfoDao.materielConversionBrowse(value,null);
			reMap.put("browse0", Integer.toString(browse0));
			reMap.put("browse1", Integer.toString(browse1));
			reMap.put("browse2", Integer.toString(browse2));
			reMap.put("browse3", Integer.toString(browse3));
			//下单量
			Integer conversionOrder0 = mdOrderInfoDao.materielConversionOrder(value,null);
			Integer conversionOrder1 = mdOrderInfoDao.materielConversionOrder(value,null);
			Integer conversionOrder2 = mdOrderInfoDao.materielConversionOrder(value,null);
			Integer conversionOrder3 = mdOrderInfoDao.materielConversionOrder(value,null);
			reMap.put("conversionOrder0", Integer.toString(conversionOrder0));
			reMap.put("conversionOrder1", Integer.toString(conversionOrder1));
			reMap.put("conversionOrder2", Integer.toString(conversionOrder2));
			reMap.put("conversionOrder3", Integer.toString(conversionOrder3));
			//付款量
			Integer conversionPayment0 = mdOrderInfoDao.materielConversionPayment(value,null);
			Integer conversionPayment1 = mdOrderInfoDao.materielConversionPayment(value,null);
			Integer conversionPayment2 = mdOrderInfoDao.materielConversionPayment(value,null);
			Integer conversionPayment3 = mdOrderInfoDao.materielConversionPayment(value,null);
			reMap.put("conversionPayment0", Integer.toString(conversionPayment0));
			reMap.put("conversionPayment1", Integer.toString(conversionPayment1));
			reMap.put("conversionPayment2", Integer.toString(conversionPayment2));
			reMap.put("conversionPayment3", Integer.toString(conversionPayment3));
			srm.setObj(reMap);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return srm;
    }
    //增加商品交易实时战报中的数据
    public PagerModel getOrderMxListmaterielTop(Integer limit, Integer page) throws HSKException {
    	PagerModel pm=new PagerModel();
    	try {
    		String wmsMiId="";
			List<Map<String,Object>> mxList = mdOrderInfoDao.getOrderMxListmaterielTop(limit, page);
			for (Map<String, Object> map : mxList) {
				Object object=map.get("wmsiId");
				String wmsiId=String.valueOf(object);
			List<Map<String,String>> favorites =mdOrderInfoDao.getOrderMxListmaterielTopFavorites(wmsiId);
				for (Map<String, String> map2 : favorites) {
					Object object2=map2.get("wmsMiId");
					wmsMiId=String.valueOf(object2);
				}
				map.put("wmsMiId", wmsMiId!="" ? wmsMiId:"0");
			}
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxList.size());
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
    	 return pm;
    	 }
    //增加新老客户交易中的数据
	   public SysRetrunMessage NewOldMoneyCount(Integer value) throws HSKException{
		   SysRetrunMessage srm = new SysRetrunMessage(1l);
		   Map<String, String> SeMap = new HashMap<String, String>();
		   try {
			//新客户付款金额
			Double NewSumMoney=mdOrderInfoDao.NewSumMoney(value);
			//新客户上个月付款金额
			Double TopNewSumMoney=mdOrderInfoDao.TopNewSumMoney(value);
			//System.out.println("TopNewSumMoney+"+TopNewSumMoney=="0.0");
			//System.out.println("TopNewSumMoney+"+TopNewSumMoney);
			//新客户付款次数
			Integer NewSumCount= mdOrderInfoDao.NewSumCount(value);
			//新客户上个月付款次数
			Integer TopNewSumCount= mdOrderInfoDao.TopNewSumCount(value);
			//老客户付款金额
			Double OldSumMoney=mdOrderInfoDao.OldSumMoney(value);
			//老客户上个月付款金额
			Double TopOldSumMoney=mdOrderInfoDao.TopOldSumMoney(value);
			//老客户付款次数
			Integer OldSumCount=mdOrderInfoDao.OldSumCount(value);
			//老客户上个月付款次数
			Integer TopOldSumCount=mdOrderInfoDao.TopOldSumCount(value);
			SeMap.put("NewSumMoney", Double.toString(NewSumMoney));
			SeMap.put("TopNewSumMoney", Double.toString(TopNewSumMoney));

			SeMap.put("NewSumCount", Integer.toString(NewSumCount));
			SeMap.put("TopNewSumCount", Integer.toString(TopNewSumCount));
			SeMap.put("OldSumMoney", Double.toString(OldSumMoney));
			SeMap.put("TopOldSumMoney", Double.toString(TopOldSumMoney));

			SeMap.put("OldSumCount", Integer.toString(OldSumCount));
			SeMap.put("TopOldSumCount", Integer.toString(TopOldSumCount));
			srm.setObj(SeMap);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return srm;
	   }
    /*供应商首页 #########start######################*/
    //供应商首页四大模块以及显示的数据
    @Override
    public SysRetrunMessage countOrderMoney1() throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        Map<String, String> SeMap = new HashMap<String, String>();
        Integer oldId = null;
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account == null) {
                return srm;
            }
            if(account.getOrganizaType().equals("100")){
                oldId = account.getOldId();
            }
                Integer countOrers = homePageSupplierDao.CountOrers(oldId);
                String countOrers1=Integer.toString(countOrers);
                SeMap.put("countOrers", countOrers1!=null ? countOrers1:"0");

            Double placeOrderMoneys = homePageSupplierDao.placeOrderMoneys(oldId);
            String placeOrderMoneys1=Double.toString(placeOrderMoneys);
            SeMap.put("placeOrderMoneys", placeOrderMoneys1!=null ? placeOrderMoneys1:"0.00");

            Double RetreatPlaceOrderMoney = homePageSupplierDao.RetreatPlaceOrderMoney(oldId);
            String RetreatPlaceOrderMoney1=Double.toString(RetreatPlaceOrderMoney);
            SeMap.put("RetreatPlaceOrderMoney", RetreatPlaceOrderMoney1!=null ? RetreatPlaceOrderMoney1:"0.00");

            Double RetreatPlaceOrderMoney2 = homePageSupplierDao.RetreatPlaceOrderMoney2(oldId);
            String RetreatPlaceOrderMoney21=Double.toString(RetreatPlaceOrderMoney2);
            SeMap.put("RetreatPlaceOrderMoney2", RetreatPlaceOrderMoney21!=null ? RetreatPlaceOrderMoney21:"0.00");

            Double RetreatPlaceOrderMoney3 = homePageSupplierDao.RetreatPlaceOrderMoney3(oldId);
            String RetreatPlaceOrderMoney31=Double.toString(RetreatPlaceOrderMoney3);
            SeMap.put("RetreatPlaceOrderMoney3", RetreatPlaceOrderMoney31!=null ? RetreatPlaceOrderMoney31:"0.00");


            Double TransactionMoneyCount=homePageSupplierDao.TransactionCountMoney(oldId);
            SeMap.put("TransactionMoneyCount", Double.toString(TransactionMoneyCount));
            //增加交易实时战报中的今日下单
            Integer TransactionCount = homePageSupplierDao.TransactionCount(oldId);
            String TransactionCount1=Integer.toString(TransactionCount);
            SeMap.put("TransactionCount", TransactionCount1);
            //增加交易实战是战报中的今日下单百分比
            Integer PercentageCount = homePageSupplierDao.PercentageCount(oldId);
            String PercentageCount1=Integer.toString(PercentageCount);
            SeMap.put("PercentageCount", PercentageCount1);

            srm.setObj(SeMap);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return srm;
    }
    /**
     * 增加状态count
     * 2020 - 04 -15
     * yanglei
     */
    @Override
    public SysRetrunMessage countOrderSuppiler(MdOrderInfo att_MdOrderInfo) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        Map<String, Integer> reMap = new HashMap<String, Integer>();
        Integer suiId = null;
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account.getOrganizaType().equals("100")) {
                suiId = account.getOldId();
            }if(account.getOrganizaType().equals("0")){
                suiId = account.getOldId();
            }
            if (att_MdOrderInfo.getPay_type_str() != null && att_MdOrderInfo.getPay_type_str().equals("支付宝")) {
                att_MdOrderInfo.setPay_type_str("1");
            } else if (att_MdOrderInfo.getPay_type_str() != null && att_MdOrderInfo.getPay_type_str().equals("微信")) {
                att_MdOrderInfo.setPay_type_str("2");
            } else if (att_MdOrderInfo.getPay_type_str() != null && att_MdOrderInfo.getPay_type_str().equals("月结")) {
                att_MdOrderInfo.setPay_type_str("3");
            }
            att_MdOrderInfo.setPay_type_str("");

            Integer allCount = homePageSupplierDao.countOrderInfoByProcessStateTwo(suiId, null, att_MdOrderInfo,null);
            Integer dfhCount = homePageSupplierDao.countOrderInfoByProcessStateTwo(suiId, "1", att_MdOrderInfo,null);
            Integer dfkCount = homePageSupplierDao.countOrderInfoByProcessStateTwo(suiId, "2", att_MdOrderInfo,null);
            Integer dshCount = homePageSupplierDao.countOrderInfoByProcessStateTwo(suiId, "3", att_MdOrderInfo,null);
            Integer bffhCount = homePageSupplierDao.countOrderInfoByProcessStateTwo(suiId, "4", att_MdOrderInfo,null);
            Integer jycgCount = homePageSupplierDao.countOrderInfoByProcessStateTwo(suiId, "5", att_MdOrderInfo,null);
            Integer jysbCount = homePageSupplierDao.countOrderInfoByProcessStateTwo(suiId, "6", att_MdOrderInfo,null);
            Integer shtkCount = homePageSupplierDao.countOrderInfoByProcessStateTwo(suiId, null, att_MdOrderInfo,2);
            Integer shthCount = homePageSupplierDao.countOrderInfoByProcessStateTwo(suiId, null, att_MdOrderInfo,1);
            Integer shwcCount = homePageSupplierDao.countOrderInfoByProcessStateTwo(suiId, null, att_MdOrderInfo,3);
            // Integer shCount = mdOrderInfoDao.countOrderInfoByAfterSaleTwo(suiId, att_MdOrderInfo);
            reMap.put("all", allCount);
            reMap.put("dfh", dfhCount);
            reMap.put("dfk", dfkCount);
            reMap.put("bffh", bffhCount);
            reMap.put("dsh", dshCount);
            reMap.put("jycg", jycgCount);
            reMap.put("jysb", jysbCount);
            reMap.put("shtkCount", shtkCount);
            reMap.put("shthCount", shthCount);
            reMap.put("shwcCount", shwcCount);
            //reMap.put("sh", shCount);
            srm.setObj(reMap);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return srm;
    }
    //增加商品交易实时战报中的数据
    public PagerModel getOrderMxListByTransaction1(Integer limit, Integer page) throws HSKException {
        PagerModel pm=new PagerModel();
        Integer oldId = null;
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account == null) {
                return pm;
            }
            if(account.getOrganizaType().equals("100")){
                oldId = account.getOldId();
            }
            List<Map<String,Object>> mxList = homePageSupplierDao.getOrderMxListByTransaction(limit, page,oldId);
            pm.setItems(mxList);
            pm.setRows(mxList);
            pm.setTotal(mxList.size());
            pm.setTotalCount(mxList.size());
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return pm;
    }
    //商品排行榜
    public PagerModel getOrderMxListmaterielTop1(Integer limit, Integer page) throws HSKException {
        PagerModel pm=new PagerModel();
        Integer oldId = null;
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account == null) {
                return pm;
            }
            if(account.getOrganizaType().equals("100")){
                oldId = account.getOldId();
            }
            String wmsMiId="";
            List<Map<String,Object>> mxList = homePageSupplierDao.getOrderMxListmaterielTop(limit, page,oldId);
            for (Map<String, Object> map : mxList) {
                Object object=map.get("wmsiId");
                String wmsiId=String.valueOf(object);
                List<Map<String,String>> favorites =homePageSupplierDao.getOrderMxListmaterielTopFavorites(wmsiId);
                for (Map<String, String> map2 : favorites) {
                    Object object2=map2.get("wmsMiId");
                    wmsMiId=String.valueOf(object2);
                }
                map.put("wmsMiId", wmsMiId!="" ? wmsMiId:"0");
            }
            pm.setItems(mxList);
            pm.setRows(mxList);
            pm.setTotal(mxList.size());
            pm.setTotalCount(mxList.size());
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return pm;
    }
    //商品总览
    public SysRetrunMessage materielState1() throws HSKException {
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        Map<String, Integer> reMap = new HashMap<String, Integer>();
        Integer oldId = null;
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if(account.getOrganizaType().equals("100")){
                oldId = account.getOldId();
            }
            //全部商品
            Integer allmaterielCount = homePageSupplierDao.materielState(0,oldId);
            //已上架商品
            Integer ysjCount = homePageSupplierDao.materielState(1,oldId);
            //已下架商品
            Integer yxjCount = homePageSupplierDao.materielState(2,oldId);

            //全部员工
            Integer allYg = homePageSupplierDao.allYg(0,oldId);
            Integer qy = homePageSupplierDao.allYg(1,oldId);
            Integer jy = homePageSupplierDao.allYg(2,oldId);
            reMap.put("allmaterielCount", allmaterielCount);
            reMap.put("ysjCount", ysjCount);
            reMap.put("yxjCount", yxjCount);

            reMap.put("allYg", allYg);
            reMap.put("qy1", qy);
            reMap.put("jy", jy);

            srm.setObj(reMap);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return srm;

    }
    //交易转换率
    public SysRetrunMessage materielAnalysis1(Integer value) throws HSKException{
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        Map<String, String> reMap = new HashMap<String, String>();
        Integer oldId = null;
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if(account.getOrganizaType().equals("100")){
                oldId = account.getOldId();
            }
            Integer AnalysisCount0 = mdOrderInfoDao.materielAnalysis(value,oldId);
            Integer AnalysisCount1 = mdOrderInfoDao.materielAnalysis(value,oldId);
            Integer AnalysisCount2 = mdOrderInfoDao.materielAnalysis(value,oldId);
            Integer AnalysisCount3 = mdOrderInfoDao.materielAnalysis(value,oldId);
            //一天交易成功订单量
            reMap.put("AnalysisCount0", Integer.toString(AnalysisCount0));
            //昨天一天交易成功订单量
            reMap.put("AnalysisCount1",Integer.toString(AnalysisCount1));
            //一周一天交易成功订单量
            reMap.put("AnalysisCount2", Integer.toString(AnalysisCount2));
            //一月一天交易成功订单量
            reMap.put("AnalysisCount3", Integer.toString(AnalysisCount3));
            /**
             * 总订单量
             */
            Integer AnalysisCounts0 = mdOrderInfoDao.materielAnalysisCounts(value,oldId);
            Integer AnalysisCounts1 = mdOrderInfoDao.materielAnalysisCounts(value,oldId);
            Integer AnalysisCounts2 = mdOrderInfoDao.materielAnalysisCounts(value,oldId);
            Integer AnalysisCounts3 = mdOrderInfoDao.materielAnalysisCounts(value,oldId);
            reMap.put("AnalysisCounts0", Integer.toString(AnalysisCounts0));
            reMap.put("AnalysisCounts1", Integer.toString(AnalysisCounts1));
            reMap.put("AnalysisCounts2", Integer.toString(AnalysisCounts2));
            reMap.put("AnalysisCounts3", Integer.toString(AnalysisCounts3));
            /**
             * 成交额
             */
            Double ActualMoney0=mdOrderInfoDao.materielAnalysisMoney(value,oldId);
            Double ActualMoney1=mdOrderInfoDao.materielAnalysisMoney(value,oldId);
            Double ActualMoney2=mdOrderInfoDao.materielAnalysisMoney(value,oldId);
            Double ActualMoney3=mdOrderInfoDao.materielAnalysisMoney(value,oldId);
            reMap.put("ActualMoney0",Double.toString(ActualMoney0));
            reMap.put("ActualMoney1",Double.toString(ActualMoney1));
            reMap.put("ActualMoney2",Double.toString(ActualMoney2));
            reMap.put("ActualMoney3",Double.toString(ActualMoney3));
            /**
             * 成交总额
             */
            Double ActualMoneys0=mdOrderInfoDao.materielAnalysisMoneys(value,oldId);
            Double ActualMoneys1=mdOrderInfoDao.materielAnalysisMoneys(value,oldId);
            Double ActualMoneys2=mdOrderInfoDao.materielAnalysisMoneys(value,oldId);
            Double ActualMoneys3=mdOrderInfoDao.materielAnalysisMoneys(value,oldId);
            reMap.put("ActualMoneys0",Double.toString(ActualMoneys0));
            reMap.put("ActualMoneys1",Double.toString(ActualMoneys1));
            reMap.put("ActualMoneys2",Double.toString(ActualMoneys2));
            reMap.put("ActualMoneys3",Double.toString(ActualMoneys3));

            //人
            Integer Pepole0=mdOrderInfoDao.materielAvgMoney(value,oldId);
            Integer Pepole1=mdOrderInfoDao.materielAvgMoney(value,oldId);
            Integer Pepole2=mdOrderInfoDao.materielAvgMoney(value,oldId);
            Integer Pepole3=mdOrderInfoDao.materielAvgMoney(value,oldId);
            reMap.put("Pepole0", Integer.toString(Pepole0));
            reMap.put("Pepole1", Integer.toString(Pepole1));
            reMap.put("Pepole2", Integer.toString(Pepole2));
            reMap.put("Pepole3", Integer.toString(Pepole3));

            //总消费
            Double MoneyAvgs0=mdOrderInfoDao.materielAvgMoneys(value,oldId);
            Double MoneyAvgs1=mdOrderInfoDao.materielAvgMoneys(value,oldId);
            Double MoneyAvgs2=mdOrderInfoDao.materielAvgMoneys(value,oldId);
            Double MoneyAvgs3=mdOrderInfoDao.materielAvgMoneys(value,oldId);
            reMap.put("MoneyAvgs0",Double.toString(MoneyAvgs0));
            reMap.put("MoneyAvgs1",Double.toString(MoneyAvgs1));
            reMap.put("MoneyAvgs2",Double.toString(MoneyAvgs2));
            reMap.put("MoneyAvgs3",Double.toString(MoneyAvgs3));
            srm.setObj(reMap);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return srm;
    }
    //交易转化率
    //转化交易率
    public SysRetrunMessage materielConversion1(Integer value) throws HSKException{
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        Map<String, String> reMap = new HashMap<String, String>();
        Integer oldId = null;
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if(account.getOrganizaType().equals("100")){
                oldId = account.getOldId();
            }
            //浏览量
            Integer browse0 = mdOrderInfoDao.materielConversionBrowse(value,oldId);
            Integer browse1 = mdOrderInfoDao.materielConversionBrowse(value,oldId);
            Integer browse2 = mdOrderInfoDao.materielConversionBrowse(value,oldId);
            Integer browse3 = mdOrderInfoDao.materielConversionBrowse(value,oldId);
            reMap.put("browse0", Integer.toString(browse0));
            reMap.put("browse1", Integer.toString(browse1));
            reMap.put("browse2", Integer.toString(browse2));
            reMap.put("browse3", Integer.toString(browse3));
            //下单量
            Integer conversionOrder0 = mdOrderInfoDao.materielConversionOrder(value,oldId);
            Integer conversionOrder1 = mdOrderInfoDao.materielConversionOrder(value,oldId);
            Integer conversionOrder2 = mdOrderInfoDao.materielConversionOrder(value,oldId);
            Integer conversionOrder3 = mdOrderInfoDao.materielConversionOrder(value,oldId);
            reMap.put("conversionOrder0", Integer.toString(conversionOrder0));
            reMap.put("conversionOrder1", Integer.toString(conversionOrder1));
            reMap.put("conversionOrder2", Integer.toString(conversionOrder2));
            reMap.put("conversionOrder3", Integer.toString(conversionOrder3));
            //付款量
            Integer conversionPayment0 = mdOrderInfoDao.materielConversionPayment(value,oldId);
            Integer conversionPayment1 = mdOrderInfoDao.materielConversionPayment(value,oldId);
            Integer conversionPayment2 = mdOrderInfoDao.materielConversionPayment(value,oldId);
            Integer conversionPayment3 = mdOrderInfoDao.materielConversionPayment(value,oldId);
            reMap.put("conversionPayment0", Integer.toString(conversionPayment0));
            reMap.put("conversionPayment1", Integer.toString(conversionPayment1));
            reMap.put("conversionPayment2", Integer.toString(conversionPayment2));
            reMap.put("conversionPayment3", Integer.toString(conversionPayment3));
            srm.setObj(reMap);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return srm;
    }
    /*供应商首页 #########end######################*/
    //供应商交易收支
    @Override
    public SysRetrunMessage branchleft1() throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        Map<String, String> SeMap = new HashMap<String, String>();
        Integer oldId = null;
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if(account.getOrganizaType().equals("100")){
                oldId = account.getOldId();
            }
            Double branchleft1 = homePageSupplierDao.branchleft1(oldId);
            String branchleft11=Double.toString(branchleft1);
            SeMap.put("branchleft1", branchleft11!=null ? branchleft11:"0");

            Double branchleft2 = homePageSupplierDao.branchleft2(oldId);
            String branchleft21=Double.toString(branchleft2);
            SeMap.put("branchleft2", branchleft21!=null ? branchleft21:"0");
            srm.setObj(SeMap);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return srm;
    }

    public SysRetrunMessage getBranchCount(String dateInput1,String dateInput2,Integer selectGuanjian,String inputGuanjian,Integer zhiFu,Integer state,String jinE1,String jinE2,Integer ziJin,Integer limit,Integer page) throws HSKException{
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        Map<String, String> SeMap = new HashMap<String, String>();
        Integer oldId = null;
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if(account.getOrganizaType().equals("100")){
                oldId = account.getOldId();
            }
//            List<Map<String,Object>> mxList =homePageSupplierDao.getBranchMxList(dateInput1,dateInput2,selectGuanjian,inputGuanjian,zhiFu,state,jinE1,jinE2,ziJin,null,null,oldId);
            Double yiJieSuanMoney=0.0;
            Integer yiJieSuanCount=0;
            //已结算的笔数和钱数
            List<Map<String,Object>> mxList1=homePageSupplierDao.getBranchCount(oldId,1,null);
            for (Map<String, Object> map : mxList1) {
                Object money=map.get("money");
                if (money!="null"&&money!=null){
                    yiJieSuanMoney=Double.parseDouble(String.valueOf(money));
                }else{
                    yiJieSuanMoney=0.0;
                }
                Object count=map.get("count");
                yiJieSuanCount=Integer.parseInt(String.valueOf(count));
                }
            //未结算的笔数与钱数
            Double weiJieSuanMoney=0.0;
            Integer weiJieSuanCount=0;
            List<Map<String,Object>> mxList2=homePageSupplierDao.getBranchCount(oldId,0,null);
            for (Map<String, Object> map : mxList2) {
                Object money=map.get("money");
                if (money!="null"&&money!=null){
                weiJieSuanMoney=Double.parseDouble(String.valueOf(money));
                }else{
                    weiJieSuanMoney=0.0;
                }
                Object count=map.get("count");
                weiJieSuanCount=Integer.parseInt(String.valueOf(count));
            }
            //已退款钱数
            Double yiTuiKuanMoney=0.0;
            Integer yiTuiKuanCount=0;
            List<Map<String,Object>> mxList3=homePageSupplierDao.getBranchCount(oldId,null,3);
            for (Map<String, Object> map : mxList3) {
                Object money=map.get("money");
                if (money!="null"&&money!=null){
                yiTuiKuanMoney=Double.parseDouble(String.valueOf(money));
                }else{
                    yiTuiKuanMoney=0.0;
                }

                Object count=map.get("count");
                yiTuiKuanCount=Integer.parseInt(String.valueOf(count));
            }
            //未退款钱数
            Double weiTuiKuanMoney=0.0;
            Integer weiTuiKuanCount=0;
            List<Map<String,Object>> mxList4=homePageSupplierDao.getBranchCount(oldId,null,2);
            for (Map<String, Object> map : mxList4) {
                Object money=map.get("money");
                if (money!="null"&&money!=null){
                weiTuiKuanMoney=Double.parseDouble(String.valueOf(money));
                }else{
                    weiTuiKuanMoney=0.0;
                }
                Object count=map.get("count");
                weiTuiKuanCount=Integer.parseInt(String.valueOf(count));
            }

            SeMap.put("yiJieSuanMoney",df.format(yiJieSuanMoney));
            SeMap.put("yiJieSuanCount",df2.format(yiJieSuanCount));

            SeMap.put("weiJieSuanMoney",df.format(weiJieSuanMoney));
            SeMap.put("weiJieSuanCount",df2.format(weiJieSuanCount));

            SeMap.put("yiTuiKuanMoney",df.format(yiTuiKuanMoney));
            SeMap.put("yiTuiKuanCount",df2.format(yiTuiKuanCount));

            SeMap.put("weiTuiKuanMoney",df.format(weiTuiKuanMoney));
            SeMap.put("weiTuiKuanCount",df2.format(weiTuiKuanCount));
            srm.setObj(SeMap);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return srm;

    }
    //
    public PagerModel getBranchMxList(String dateInput1,String dateInput2,Integer selectGuanjian,String inputGuanjian,Integer zhiFu,Integer state,String jinE1,String jinE2,Integer ziJin,Integer limit,Integer page) throws HSKException {
        PagerModel pm=new PagerModel();
        Integer oldId = null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if(account.getOrganizaType().equals("100")){
                oldId = account.getOldId();
            }
            List<Map<String,Object>> mxList =homePageSupplierDao.getBranchMxList(dateInput1,dateInput2,selectGuanjian,inputGuanjian,zhiFu,state,jinE1,jinE2,ziJin,limit,page,oldId);
            for (Map<String, Object> map : mxList) {
                Object PlaceOrderTime=map.get("PlaceOrderTime");
                map.put("PlaceOrderTime",sdf.format(PlaceOrderTime));
            }
            List<Map<String,Object>> mxList1 =homePageSupplierDao.getBranchMxList(dateInput1,dateInput2,selectGuanjian,inputGuanjian,zhiFu,state,jinE1,jinE2,ziJin,null,null,oldId);
            Integer mxListCount1=mxList1.size();
            pm.setItems(mxList);
            pm.setRows(mxList);
            pm.setTotal(mxListCount1);
            pm.setTotalCount(mxList.size());

        } catch (HSKDBException e) {
            e.printStackTrace(); }
        return pm;
    }

    //导出交易收支数据
    public SysRetrunMessage exportBranchMxList(String dateInput1,String dateInput2,Integer selectGuanjian,String inputGuanjian,Integer zhiFu,Integer state,String jinE1,String jinE2,Integer ziJin) throws HSKException{
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SysUserInfo SysUserInfo=this.GetOneSessionAccount();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName",SysUserInfo.getUserName());
        dataMap.put("nodeName",SysUserInfo.getOrgGxId_Str());
        Date newDate=new Date();
        dataMap.put("newDate",sdf.format(newDate));
        dataMap.put("DateStart",dateInput1!=" "? dateInput1:"");
        dataMap.put("endStart",dateInput2!=" "? dateInput1:"");

        Integer oldId = null;
        try{
            if(SysUserInfo.getOrganizaType().equals("100")){
                oldId = SysUserInfo.getOldId();
            }
            List<Map<String, Object>> list = homePageSupplierDao.getBranchMxList(dateInput1,dateInput2,selectGuanjian,inputGuanjian,zhiFu,state,jinE1,jinE2,ziJin,null,null,oldId);
            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int index = 0;
            Double countLack=0.0;
            Double countLackBack=0.0;
            Double weiMoney=0.0;
            Double yiMoney=0.0;
            if (list != null && list.size() > 0) {
                for (Map<String, Object> map : list) {
                    index++;
                    Map<String, String> reMap = new HashMap<String, String>();
                    reMap.put("index",index+"");

                    Object PlaceOrderTime=map.get("PlaceOrderTime");
                    reMap.put("PlaceOrderTime",sdf.format(PlaceOrderTime));

                    Object processState1=map.get("processState");
                    String processState=String.valueOf(processState1);
                    if (processState.equals("5")){
                        reMap.put("processState","交易成功");
                    }else if (processState.equals("6")){
                        reMap.put("processState","交易失败");
                   }
                    Object orderCode1=map.get("orderCode");
                    String orderCode=String.valueOf(orderCode1);
                    reMap.put("orderCode",orderCode!="null" ? orderCode:"");

                    Object masCode1=map.get("masCode");

                    String masCode=String.valueOf(masCode1);

                    reMap.put("masCode",masCode!="null"&&masCode!="" ? masCode:"");


                    Object PurchaseUnit1=map.get("PurchaseUnit");
                    String PurchaseUnit=String.valueOf(PurchaseUnit1);
                    reMap.put("PurchaseUnit",PurchaseUnit!="null" ? PurchaseUnit:"");

                    Object payType1=map.get("payType");
                    String payType=String.valueOf(payType1);
                    if (payType.equals("1")){
                        reMap.put("payType","支付宝");
                    }else if (payType.equals("2")){
                        reMap.put("payType","微信");
                    }else if (payType.equals("3")){
                        reMap.put("payType","月结");
                    }else {
                        reMap.put("payType","");
                    }
                    reMap.put("placeOrderMoney",df2.format(map.get("placeOrderMoney")!=null ? map.get("placeOrderMoney"):0d));


                    Object settlement1=map.get("settlement");
                    String settlement=String.valueOf(settlement1);
                    if (settlement.equals("1")){
                        reMap.put("settlement","已结算");

                    }else if (settlement.equals("0")){
                        reMap.put("settlement","未结算");
                    }else if (settlement.equals("2")){
                        reMap.put("settlement","部分结算");
                    }else {
                        reMap.put("settlement","未结算");
                    }
                    Object saleDate=map.get("saleDate");
                    if (saleDate!=null&&!saleDate.equals("")){
                        reMap.put("saleDate",sdf.format(saleDate));
                    }else{
                        reMap.put("saleDate","");
                    }
                    reMap.put("saleMoney",df2.format(map.get("saleMoney")!=null ? map.get("saleMoney"):0d));

                    Object placeOrderMoney=map.get("placeOrderMoney");
                    Double countLack1=Double.parseDouble(String.valueOf(placeOrderMoney));
                    countLack+=countLack1;


                    if (map.get("saleMoney")!=null){
                         countLackBack+=Double.parseDouble(String.valueOf(map.get("saleMoney")));
                    }else {
                        countLackBack+=0.0;
                    }

                    if (settlement.equals("1")){
                        Object placeOrderMoneyS1=map.get("placeOrderMoney");
                        Double weiMoney1=Double.parseDouble(String.valueOf(placeOrderMoneyS1));
                        weiMoney+=weiMoney1;
                    }else if (settlement.equals("0")||settlement.equals("")){
                        Object placeOrderMoneyS1=map.get("placeOrderMoney");
                        Double yiMoney1=Double.parseDouble(String.valueOf(placeOrderMoneyS1));
                        yiMoney+=yiMoney1;
                    }

                    reList.add(reMap);
                }
            }
            dataMap.put("listSize",list.size());
            dataMap.put("countLack",countLack);
            dataMap.put("countLackBack",countLackBack);
            dataMap.put("weiMoney",weiMoney);
            dataMap.put("yiMoney",yiMoney);

            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportBranchMxList.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            long lo = now.getTimeInMillis();
            Date date1 = new Date(lo);
            SimpleDateFormat sd = new SimpleDateFormat("hhmmss");
            String l =sd.format(date1);

            String str="";
            if (dateInput1!=null&&dateInput1!=""&&dateInput2!=null&&dateInput2!=""){
                str= dateInput1+"至"+dateInput2+SysUserInfo.getOrgGxId_Str()+"收支明细";
            }else{
                str=SysUserInfo.getOrgGxId_Str()+"收支明细";
            }

            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + str+ ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + str+ ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", str + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  srm;
        }

    @Override
    public SysRetrunMessage saveBill(MdBillEntity mdBillEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneShoppingSessionAccount();
            if (account == null) {
                sm.setCode(2L);
                sm.setMeg("未登录");
                return sm;
            }
            if (mdBillEntity.getMoiId() == null) {
                sm.setCode(3L);
                sm.setMeg("没有订单号");
                return sm;
            }
//            MdOrderInfo mdOrderInfo = new MdOrderInfo();
//            mdOrderInfo.setMoiId(mdBillEntity.getMoiId());
//            mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);
//            mdBillEntity.setOrderCode(mdOrderInfo == null ? null : mdOrderInfo.getOrderCode());
//
//            MdSupplier mdSupplier = new MdSupplier();
//            mdSupplier.setWzId(mdBillEntity.getWzId());
//            mdSupplier = (MdSupplier) this.getOne(mdSupplier);
//            mdBillEntity.setApplicantName(mdSupplier == null ? null : mdSupplier.getApplicantName());

            mdBillEntity.setSuiId(account.getSuiId());
            this.newObject(mdBillEntity);
            sm.setObj(mdBillEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateBill(MdBillEntity mdBillEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneShoppingSessionAccount();
            if (account == null) {
                sm.setCode(2L);
                sm.setMeg("未登录");
                return sm;
            }
            if (mdBillEntity.getMoiId() == null) {
                sm.setCode(3L);
                sm.setMeg("没有订单号");
                return sm;
            }
            if (mdBillEntity.getBillId() == null) {
                sm.setCode(4L);
                sm.setMeg("发票号有问题");
                return sm;
            }

//            MdOrderInfo mdOrderInfo = new MdOrderInfo();
//            mdOrderInfo.setMoiId(mdBillEntity.getMoiId());
//            mdOrderInfo = (MdOrderInfo) this.getOne(mdOrderInfo);
//            mdBillEntity.setOrderCode(mdOrderInfo == null ? null : mdOrderInfo.getOrderCode());
//
//            MdSupplier mdSupplier = new MdSupplier();
//            mdSupplier.setWzId(mdBillEntity.getWzId());
//            mdSupplier = (MdSupplier) this.getOne(mdSupplier);
//            mdBillEntity.setApplicantName(mdSupplier == null ? null : mdSupplier.getApplicantName());

            this.updateObject(mdBillEntity);
            sm.setObj(mdBillEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteBill(String billIds) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            if (billIds == null || billIds.equals("")) {
                sm.setCode(3L);
                sm.setMeg("传入数据有误");
                return sm;
            }
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public PagerModel getBillPagerModel(MdBillEntity mdBillEntity) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList());
        try {
            pm = mdOrderInfoDao.getBillPagerModel(mdBillEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage getBillList(MdBillEntity mdBillEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            List<MdBillEntity> list = mdOrderInfoDao.getBillList(mdBillEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getBillLatest() throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneShoppingSessionAccount();
            if (account == null) {
                return sm;
            }
            MdBillEntity perBill = mdOrderInfoDao.getBillLatest(account.getSuiId(), 1);
            MdBillEntity comBill = mdOrderInfoDao.getBillLatest(account.getSuiId(), 2);
            List<MdBillEntity> list = new ArrayList<>();
            list.add(perBill);
            list.add(comBill);
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }
}
