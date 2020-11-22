package com.hsk.dentistmall.web.storage.service.imp;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.inject.internal.Objects;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.xframe.api.daobbase.ISysFileInfoDao;
import org.apache.commons.lang.StringUtils;
import org.jbpm.pvm.internal.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.api.security.ReadExcel;
import com.hsk.dentistmall.web.storage.model.ErrorImpDataModel;
import com.hsk.dentistmall.web.storage.service.IMdInventoryService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.IorgDao;
import com.hsk.xframe.api.persistence.SysFileInfo;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.CheckUtil;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.xframe.api.utils.freeMarker.ExportExcel;
import com.hsk.xframe.api.utils.freeMarker.PingYinUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;


/**
 * storage业务操作实现类
 *
 * @author 作者:admin
 * @version 版本信息:v1.0   创建时间: 2017-09-25 14:14:55
 */

@Service
public class MdInventoryService extends DSTService implements IMdInventoryService {
    /**
     * 业务处理dao类  mdInventoryDao
     */
    @Autowired
    protected IMdInventoryDao mdInventoryDao;
    @Autowired
    protected IMdInventoryExtendDao mdInventoryExtendDao;
    @Autowired
    protected IorgDao orgDao;
    @Autowired
    private IMdItemKeyDao mdItemKeyDao;
    //设置安全预警
    @Autowired
    private IHomePageSupplierDao homePageSupplierDao;
    @Autowired
    private ISysFileInfoDao sysFileInfoDao;
    @Autowired
    private IMdMaterielInfoDao mdMaterielInfoDao;
    @Autowired
    private IMdMaterielFormatDao mdMaterielFormatDao;
    @Autowired
    private IMdMaterielPartDao mdMaterielPartDao;
    @Autowired
    private IMdMaterielPartSecondDao mdMaterielPartSecondDaoDaoDao;
    @Autowired
    private IMdEnterWarehouseDao mdEnterWarehouseDao;
    @Autowired
    private IMdEnterWarehouseMxDao mdEnterWarehouseMxDao;
    @Autowired
    private IMdOutWarehouseDao mdOutWarehouseDao;
    @Autowired
    private IMdOutWarehouseMxDao mdOutWarehouseMxDao;

    /**
     * 根据md_inventory表主键删除MdInventory对象记录
     *
     * @param wiId Integer类型(md_inventory表主键)
     * @return SysRetrunMessage 对象记录
     * @throws HSKException
     */

    public SysRetrunMessage findFormObject(Integer wiId) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);

        try {
            MdInventoryView att_MdInventory = mdInventoryDao.findMdInventoryViewById(wiId);
            srm.setObj(att_MdInventory);
        } catch (HSKDBException e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg(e.getMessage());
        }
        return srm;
    }

    /**
     * 根据md_inventory表主键删除MdInventory对象记录
     *
     * @param wiId Integer类型(md_inventory表主键)
     * @throws HSKException
     */

    public MdInventoryView findObject(Integer wiId) throws HSKException {
        MdInventoryView att_MdInventory = new MdInventoryView();
        try {
            att_MdInventory = mdInventoryDao.findMdInventoryViewById(wiId);
        } catch (HSKDBException e) {
            e.printStackTrace();
            throw new HSKException(e);
        }
        return att_MdInventory;
    }


    /**
     * 根据md_inventory表主键删除MdInventory对象记录
     *
     * @param wiId Integer类型(md_inventory表主键)
     * @return SysRetrunMessage 对象记录
     * @throws HSKException
     */
    public SysRetrunMessage deleteObject(Integer wiId) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdInventory att_MdInventory = mdInventoryDao.findMdInventoryById(wiId);
            att_MdInventory.setState("0");
            mdInventoryDao.saveOrUpdateMdInventory(att_MdInventory);
            //mdInventoryDao.deleteMdInventoryById(wiId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HSKException(e);
        }
        return srm;
    }

    /**
     * 根据md_inventory表主键删除多条MdInventory对象记录
     *
     * @param  Integer类型(md_inventory表主键)
     * @return SysRetrunMessage 对象记录
     * @throws HSKException
     */
    public SysRetrunMessage deleteAllObject(String wiIds) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            String[] ids = wiIds.split(",");
            for (String did : ids) {
                if (did != null && !"".equals(did.trim())) {
                    mdInventoryDao.deleteMdInventoryById(Integer
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
     * 新增或修改md_inventory表记录 ,如果md_inventory表主键MdInventory.WiId为空就是添加，如果非空就是修改
     *
     * @param att_MdInventory MdInventory类型(md_inventory表记录)
     * @return MdInventory  修改后的MdInventory对象记录
     * @throws HSKDBException
     */
    public SysRetrunMessage saveOrUpdateObject(MdInventory att_MdInventory) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            mdInventoryDao.saveOrUpdateMdInventory(att_MdInventory);
            srm.setObj(att_MdInventory);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HSKException(e);
        }
        return srm;
    }


    /**
     * 根据MdInventory对象作为对(md_inventory表进行查询，获取分页对象
     *
     * @param att_MdInventory MdInventory类型(md_inventory表记录)
     * @return PagerModel  分页对象
     * @throws HSKException
     */
    public PagerModel getPagerModelObject(MdInventory att_MdInventory) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdInventory>());
        try {
            pm = mdInventoryDao.getPagerModelByMdInventory(att_MdInventory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    public  SysRetrunMessage getPriceSum(MdInventoryView att_MdInventoryView) throws HSKException{
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        DecimalFormat df= new DecimalFormat("######0.00");
        DecimalFormat df2= new DecimalFormat("######0");
        Map<String, String> reMap = new HashMap<String, String>();
        try {
            List<Map<String, Object>> list =mdInventoryDao.getPriceSum(att_MdInventoryView);
            if (list!=null&&list.size()>0){
                for (Map<String, Object> map : list) {
                   Object money1s =map.get("money1s");
                   if (money1s!=null){
                       reMap.put("money1s",df.format(money1s));
                   }else {
                       reMap.put("money1s","0");
                   }
                    Object money2s =map.get("money2s");
                    if (money2s!=null){
                        reMap.put("money2s",df.format(money2s));
                    }else {
                        reMap.put("money2s","0");
                    }
                }
            }
            srm.setObj(reMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  srm;
    }
    @Override
    public PagerModel getPagerViewObject(MdInventoryView att_MdInventoryView, String sort, Double InventoryStart, Double InventoryEnd, Integer safeIsNot
            , Integer inventoryWarn, Integer validdateWarn, Integer zeroWarn)
            throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<>());
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account.getOrganizaType().equals("20001")) {
                att_MdInventoryView.setRbaId(account.getOldId());
                att_MdInventoryView.setPurchaseType("1");
            } else if (account.getOrganizaType().equals("20002")) {
                att_MdInventoryView.setRbsId(account.getOldId());
                att_MdInventoryView.setPurchaseType("2");

            } else if (account.getOrganizaType().equals("20003")) {
                att_MdInventoryView.setRbbId(account.getOldId());
                att_MdInventoryView.setPurchaseType("3");
            }
            if (sort != null && !sort.trim().equals("")) {
                sort = sort.replace(".", ",");
                String[] array = sort.trim().split(",");
                att_MdInventoryView.setTabOrder(array[0] + " " + array[1]);
            }
            if (validdateWarn!=null){
                pm = mdInventoryDao.getPagerModelByMdInventoryView2(att_MdInventoryView, InventoryStart, InventoryEnd, safeIsNot, inventoryWarn, validdateWarn, zeroWarn);
            }else {
                pm = mdInventoryDao.getPagerModelByMdInventoryView(att_MdInventoryView, InventoryStart, InventoryEnd, safeIsNot, inventoryWarn, validdateWarn, zeroWarn);
            }
            List<MdInventoryView> reList = pm.getRows();
            if (reList != null && reList.size() > 0) {
                //查询收藏列表
                MdInventoryCollect mdInventoryCollect = new MdInventoryCollect();
                mdInventoryCollect.setSuiId(account.getSuiId());
                mdInventoryCollect.setState("1");
                List<MdInventoryCollect> collectList = this.getList(mdInventoryCollect);
                if (collectList != null && collectList.size() > 0) {
                    SysFileInfo filebg1;
                    for (MdInventoryView view : reList) {
                        filebg1 = sysFileInfoDao.getSysFileInfoByFileCode(view.getLessenFilecode());
                        if (filebg1 != null)
                            view.setLogoPath(filebg1.getRootPath());
                        for (MdInventoryCollect collect : collectList) {
                            if (view.getWiId().equals(collect.getWiId())) {
                                view.setMicId(collect.getMicId());
                                break;
                            }
                        }
                    }
                }
            }
            //设置日期是否预警
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d1;
            Date d2;
            if (validdateWarn!=null){
//                List<Map<String,Object>> reList1 = pm.getRows();
//                for (Map<String,Object> view: reList1) {
//
//                }
            }else {
                for (MdInventoryView view : reList) {
                    if (view.getValiedDate() != null) {
                        c.setTime(view.getValiedDate());
                        if (view.getWarningDay() != null) {
                            c.add(Calendar.DATE, view.getWarningDay() * -1);
                            d1 = sdf.parse(sdf.format(c.getTime()));
                            d2 = sdf.parse(sdf.format(new Date()));
                            if (d1.before(d2) || d1.equals(d2))
                                view.setDateIsNot(1);
                            else
                                view.setDateIsNot(2);
                        }
                    }
                }
            }
            pm.setRows(reList);
            pm.setItems(reList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage saveWanning(Integer wiId, Double maxShu, Integer minDay, Double wanningShu, Double ratio, String baseUnit, String matName, String mmfName)
            throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdInventory att_MdInventory = mdInventoryDao.findMdInventoryById(wiId);
            MdItemKey mdItemKey = new MdItemKey();
            mdItemKey.setItemKeyId(Integer.parseInt(att_MdInventory.getItemKeyId()));
            mdItemKey = (MdItemKey) this.getOne(mdItemKey);
            mdItemKey.setMmfName(mmfName);
            mdItemKey.setMatName(matName);
            String mmfNamePy = "";
            String matNamePy = "";
            if (!mmfName.trim().equals("")) {
                for (int i = 0; i < mmfName.trim().length(); i++)
                    mmfNamePy += PingYinUtil.getLetterFormChinese(mmfName.trim().charAt(i));
            }
            if (!matName.trim().equals("")) {
                for (int i = 0; i < matName.trim().length(); i++)
                    matNamePy += PingYinUtil.getLetterFormChinese(matName.trim().charAt(i));
            }
            mdItemKey.setMatNamePy(matNamePy);
            mdItemKey.setMmfNamePy(mmfNamePy);
            this.updateObject(mdItemKey);
            att_MdInventory.setWarningShu(wanningShu);
            att_MdInventory.setMaxShu(maxShu);
            att_MdInventory.setMinDay(minDay);
            att_MdInventory.setRatio(ratio);
            att_MdInventory.setBasicUnit(baseUnit);
            att_MdInventory.setBaseNumber(att_MdInventory.getQuantity() * att_MdInventory.getRatio());
            MdInventoryExtend mdInventoryExtend = new MdInventoryExtend();
            mdInventoryExtend.setWiId(wiId);
            List<MdInventoryExtend> mdInventoryExtendList = this.getList(mdInventoryExtend);
            if (mdInventoryExtendList != null && mdInventoryExtendList.size() > 0) {
                for (MdInventoryExtend extend : mdInventoryExtendList) {
                    extend.setBaseNumber(extend.getQuantity() * att_MdInventory.getRatio());
                    extend.setBasicUnit(baseUnit);
                    this.updateObject(extend);
                }
            }
            mdInventoryDao.saveOrUpdateMdInventory(att_MdInventory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }

    @Override
    public SysRetrunMessage exportList(MdInventoryView att_MdInventoryView)
            throws HSKException {
        att_MdInventoryView.getSafeIsNot();
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
//			SysUserInfo account = this.GetOneSessionAccount();
//			  if(account.getOrganizaType().equals("20001")){
//				  att_MdInventoryView.setRbaId(account.getOldId());
//			  }else if(account.getOrganizaType().equals("20002")){
//				  att_MdInventoryView.setRbsId(account.getOldId());
//
//			  }else if(account.getOrganizaType().equals("20003")){
//				  att_MdInventoryView.setRbbId(account.getOldId());
//			  }
            SysUserInfo account = this.GetOneSessionAccount();
            if (account.getOrganizaType().equals("20001")) {
                att_MdInventoryView.setRbaId(account.getOldId());
                att_MdInventoryView.setPurchaseType("1");
            } else if (account.getOrganizaType().equals("20002")) {
                att_MdInventoryView.setRbsId(account.getOldId());
                att_MdInventoryView.setPurchaseType("2");

            } else if (account.getOrganizaType().equals("20003")) {
                att_MdInventoryView.setRbbId(account.getOldId());
                att_MdInventoryView.setPurchaseType("3");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            DecimalFormat df = new DecimalFormat("######0.00");
            DecimalFormat df2 = new DecimalFormat("######0");
            Map<String, Object> dataMap = new HashMap<String, Object>();
            int rowCount = 4;
            dataMap.put("orgName", account.getOrgName());
            dataMap.put("timeArea", sdf.format(new Date()));
            dataMap.put("userName", account.getUserName());
            List<MdInventoryView> list = mdInventoryDao.getListByMdInventoryView(att_MdInventoryView);
            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int index = 0;
            if (list != null && list.size() > 0) {
                for (MdInventoryView obj : list) {
                    index++;
                    Map<String, String> reMap = new HashMap<String, String>();
                    reMap.put("index", index + "");
                    reMap.put("matName", obj.getMatName());
                    // reMap.put("brand", obj.getBrand());
                    reMap.put("mmfName", obj.getMmfName());
                    //reMap.put("typeName", obj.getTypeName()!=null?obj.getTypeName():"");
                    reMap.put("quantity", df2.format(obj.getQuantity()));
                    reMap.put("basicUnit", obj.getBasicUnit());
                    reList.add(reMap);
                }
                rowCount += list.size();
            }
            dataMap.put("rowCount", rowCount);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportInventoryList.ftl");
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
            srm.setCode(0l);
            srm.setMeg("操作失败!");
        }
        return srm;
    }

    @Override
    public PagerModel getExtendPagerModel(Integer wiId, String relatedCode, String startDate, String endDate) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdInventoryExtend>());
        try {
            List<MdInventoryExtend> list = mdInventoryExtendDao.getMdInventoryExtendByWiIdToSel(wiId, relatedCode, startDate, endDate);
            pm.setItems(list);
            pm.setRows(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getExtendEnterViewPagerModel(Integer wiId, String relatedCode, String startDate, String endDate, Integer billType, String receiptDatetime) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdInventoryExtend>());
        try {
            List<MdInventoryExtend> list = mdInventoryExtendDao.getExtendEnterViewPagerModel(wiId, relatedCode, startDate, endDate, billType, receiptDatetime);
            pm.setItems(list);
            pm.setRows(list);
            pm.setTotalCount(list.size());
            pm.setTotal(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage saveImpData(String fileCode) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            Date star_date = new Date();
            //查看当前组织是否存在集团、医院
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            Integer one = null;
            Integer two = null;
            Integer three = null;
            String purchaseType = "";
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                one = account.getOldId();
                purchaseType = "2";
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one"))//如果存在上级集团
                    one = Integer.parseInt(orgMap.get("one"));
                two = account.getOldId();
                purchaseType = "3";
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one"))//如果存在上级集团
                    one = Integer.parseInt(orgMap.get("one"));
                if (orgMap.containsKey("tow"))//如果存在上级医院
                    two = Integer.parseInt(orgMap.get("tow"));
                three = account.getOldId();
                purchaseType = "4";
            }
            //文件读取
            SysFileInfo sfi = new SysFileInfo();
            sfi.setFileCode(fileCode);
            Object obj = this.getOne(sfi);
            if (obj != null) {
                sfi = (SysFileInfo) obj;
                ReadExcel re = new ReadExcel();
                List<List<String>> list_read = re.readExcel(sfi.getFilePath());
                List<Object> errorList = new ArrayList<Object>();
                int tureCount = 0;
                for (List<String> did : list_read) {
                    if (did != null && did.size() > 0) {
                        String errorStr = this.checkImpData(did);
                        if (errorStr.equals("")) {
                            MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
                            mdMaterielInfo.setWzId(account.getOldId());
                            mdMaterielInfo.setPurchaseType(purchaseType);
                            mdMaterielInfo.setMatCode(CreateCodeUtil.getNo("MAT"));
                            mdMaterielInfo.setMatName(did.get(0).trim());
                            mdMaterielInfo.setApplicantName(did.get(2));

                            /**
                             * yanglei
                             * 修改字段ProductNumber 改为ProductFactory set这个字段时用到
                             */
                            mdMaterielInfo.setProductFactory(did.get(3));
                            mdMaterielInfo.setBrand(did.get(4));
                            mdMaterielInfo.setBasicUnit(did.get(5));
                            mdMaterielInfo.setNorm(did.get(1).trim());
                            mdMaterielInfo.setLabelInfo(did.get(6));
                            mdMaterielInfo.setMoney1(Double.parseDouble(did.get(7).trim()));
                            mdMaterielInfo.setState("1");
                            this.newObject(mdMaterielInfo);
                            MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
                            mdMaterielFormat.setWmsMiId(mdMaterielInfo.getWmsMiId());
                            mdMaterielFormat.setMmfCode(CreateCodeUtil.getNo("MMF"));
                            mdMaterielFormat.setMmfName(did.get(1).trim());
                            mdMaterielFormat.setPrice(Double.parseDouble(did.get(7).trim()));
                            mdMaterielFormat.setState("1");
                            this.newObject(mdMaterielFormat);
                            MdItemKey mdItemKey = new MdItemKey();
                            mdItemKey.setRbaId(one);//从session中获取集团ID
                            mdItemKey.setRbsId(two);//从session中获取医院ID
                            mdItemKey.setRbbId(three);//从session中获取门诊ID
                            mdItemKey.setPurchaseType((Integer.parseInt(purchaseType) - 1) + "");//从session中采购单位类型
                            mdItemKey.setMatName(mdMaterielInfo.getMatName());
                            mdItemKey.setMmfName(mdMaterielFormat.getMmfName());
                            String mmfNamePy = "";
                            String matNamePy = "";
                            if (!mdMaterielFormat.getMmfName().trim().equals("")) {
                                for (int i = 0; i < mdMaterielFormat.getMmfName().trim().length(); i++)
                                    mmfNamePy += PingYinUtil.getLetterFormChinese(mdMaterielFormat.getMmfName().trim().charAt(i));
                            }
                            if (!mdMaterielInfo.getMatName().trim().equals("")) {
                                for (int i = 0; i < mdMaterielInfo.getMatName().trim().length(); i++)
                                    matNamePy += PingYinUtil.getLetterFormChinese(mdMaterielInfo.getMatName().trim().charAt(i));
                            }
                            mdItemKey.setMatNamePy(matNamePy);
                            mdItemKey.setMmfNamePy(mmfNamePy);
                            this.newObject(mdItemKey);
                            MdItemMx new_MdItemMx = new MdItemMx();
                            new_MdItemMx.setItemKeyId(mdItemKey.getItemKeyId());
                            new_MdItemMx.setMmfId(mdMaterielFormat.getMmfId());
                            new_MdItemMx.setWmsMiId(mdMaterielInfo.getWmsMiId());
                            this.newObject(new_MdItemMx);
                            //增加库存
                            MdInventory att_MdInventory = new MdInventory();
                            att_MdInventory.setRbaId(one);//从session中获取集团ID
                            att_MdInventory.setRbsId(two);//从session中获取医院ID
                            att_MdInventory.setRbbId(three);//从session中获取门诊ID
                            att_MdInventory.setUnit(mdMaterielInfo.getBasicUnit());
                            att_MdInventory.setPurchaseType((Integer.parseInt(purchaseType) - 1) + "");//从session中采购单位类型
                            //att_MdInventory.setWmsMiId(mdMaterielInfo.getWmsMiId());
                            //att_MdInventory.setMmfId(mdMaterielFormat.getMmfId());
                            att_MdInventory.setItemKeyId(mdItemKey.getItemKeyId() + "");
                            att_MdInventory.setState("1");
                            att_MdInventory.setBasicUnit(mdMaterielInfo.getBasicUnit());
                            att_MdInventory.setQuantity(Double.parseDouble(did.get(8).trim()));
                            att_MdInventory.setRatio(1d);
                            att_MdInventory.setBaseNumber(att_MdInventory.getQuantity());
                            this.newObject(att_MdInventory);
                            //保存库存明细信息
                            MdInventoryExtend att_MdInventoryExtend = new MdInventoryExtend();
                            att_MdInventoryExtend.setWiId(att_MdInventory.getWiId());
                            att_MdInventoryExtend.setWmsMiId(mdMaterielInfo.getWmsMiId());
                            att_MdInventoryExtend.setMmfId(mdMaterielFormat.getMmfId());
                            att_MdInventoryExtend.setBasicUnit(att_MdInventory.getBasicUnit());
                            att_MdInventoryExtend.setUnit(att_MdInventory.getUnit());
                            att_MdInventoryExtend.setQuantity(att_MdInventory.getQuantity());
                            att_MdInventoryExtend.setBaseNumber(att_MdInventory.getBaseNumber());
                            att_MdInventoryExtend.setPrice(mdMaterielFormat.getPrice());
                            att_MdInventoryExtend.setBasePrice(mdMaterielFormat.getPrice());
                            att_MdInventoryExtend.setRelatedCode(null);
                            att_MdInventoryExtend.setPurchaseUser(null);
                            att_MdInventoryExtend.setCreateDate(new Date());
                            att_MdInventoryExtend.setEditDate(new Date());
                            att_MdInventoryExtend.setMmfName(mdMaterielFormat.getMmfName());
                            att_MdInventoryExtend.setMatName(mdMaterielInfo.getMatName());
                            /**
                             * yanglei
                             * 修改字段ProductNumber 改为ProductFactory get这个字段时用到
                             */
                            att_MdInventoryExtend.setProductName(mdMaterielInfo.getProductFactory());
                            att_MdInventoryExtend.setBrand(mdMaterielInfo.getBrand());
                            att_MdInventoryExtend.setLabelInfo(mdMaterielInfo.getLabelInfo());
                            att_MdInventoryExtend.setApplicantName(mdMaterielInfo.getApplicantName());
                            att_MdInventoryExtend.setState("1");
                            this.newObject(att_MdInventoryExtend);
                            tureCount++;
                        } else {
                            ErrorImpDataModel errorImpDataModel = new ErrorImpDataModel();
                            errorImpDataModel.setMatName(did.get(0));
                            if (did.size() > 1)
                                errorImpDataModel.setMmfName(did.get(1));
                            if (did.size() > 2)
                                errorImpDataModel.setApplicantName(did.get(2));
                            if (did.size() > 3)
                                errorImpDataModel.setProductName(did.get(3));
                            if (did.size() > 4)
                                errorImpDataModel.setBrand(did.get(4));
                            if (did.size() > 5)
                                errorImpDataModel.setUnit(did.get(5));
                            if (did.size() > 6)
                                errorImpDataModel.setMatType(did.get(6));
                            if (did.size() > 7)
                                errorImpDataModel.setPrice(did.get(7));
                            if (did.size() > 8)
                                errorImpDataModel.setNum(did.get(8));
                            errorImpDataModel.setErrorStr(errorStr);
                            errorList.add(errorImpDataModel);
                        }
                    }
                }
                Map<String, Object> reMap = new HashMap<String, Object>();
                reMap.put("rightCount", tureCount);
                if (errorList.size() > 0) {
                    reMap.put("errorCount", errorList.size());
                    //创建错误文件
                    srm.setCode(2l);
                    Calendar now = Calendar.getInstance();
                    long l = now.getTimeInMillis();
                    String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
                    File file = new File(tmpPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    String realPath = tmpPath + l + ".xls";
                    ExportExcel<Object> ex = new ExportExcel<Object>();
                    String[] headers = {"物料名称", "型号名称", "供应商", "生产厂家", "品牌", "单位", "物料类别", "单价", "数量", "错误信息"};
                    OutputStream out = null;
                    out = new FileOutputStream(realPath);
                    ex.exportExcel("错误数据", headers, errorList, out, "yyyy-MM-dd HH:mm:ss");
                    out.close();
                    String rootUrl = request.getContextPath() + EXPORT_PATH + l + ".xls";
                    reMap.put("rootPath", rootUrl);
                    reMap.put("filePath", realPath);
                }
                Date end_date = new Date();
                long interval = (end_date.getTime() - star_date.getTime()) / 1000;
                reMap.put("timeCount", interval);
                srm.setObj(reMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("操作失败！");
            throw new HSKException(e);
        }
        return srm;
    }

    @Override
    public SysRetrunMessage saveImpData1(String fileCode) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            Date star_date = new Date();
            //查看当前组织是否存在集团、医院
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            String orgNameStr = orgDao.getNameStr(sysOrgGx);
            Integer one = null;
            Integer two = null;
            Integer three = null;
            String purchaseType = "";
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                one = account.getOldId();
                purchaseType = "2";
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one"))//如果存在上级集团
                    one = Integer.parseInt(orgMap.get("one"));
                two = account.getOldId();
                purchaseType = "3";
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one"))//如果存在上级集团
                    one = Integer.parseInt(orgMap.get("one"));
                if (orgMap.containsKey("tow"))//如果存在上级医院
                    two = Integer.parseInt(orgMap.get("tow"));
                three = account.getOldId();
                purchaseType = "4";
            }
            //文件读取
            SysFileInfo sfi = new SysFileInfo();
            sfi.setFileCode(fileCode);
            Object obj = this.getOne(sfi);
            if (obj != null) {
                sfi = (SysFileInfo) obj;
                ReadExcel re = new ReadExcel();
                List<List<String>> list_read = re.readExcel(sfi.getFilePath());
                List<Object> errorList = new ArrayList<Object>();
                int tureCount = 0;
                Integer idx = 0;
                Double expectNumber = 0d;
                Double allRetailPrice = 0d;
                Double allPrice = 0d;
                MdEnterWarehouse att_MdEnterWarehouse = new MdEnterWarehouse();
                att_MdEnterWarehouse.setRbaId(one);
                att_MdEnterWarehouse.setRbsId(two);
                att_MdEnterWarehouse.setRbbId(three);
                att_MdEnterWarehouse.setPurchaseType((Integer.parseInt(purchaseType) - 1) + "");
                att_MdEnterWarehouse.setBillcode(CreateCodeUtil.getNo("RK"));
                att_MdEnterWarehouse.setSuiId(account.getSuiId());
                att_MdEnterWarehouse.setConsignor(account.getUserName());
                att_MdEnterWarehouse.setParamStr01(orgNameStr);
                att_MdEnterWarehouse.setState("1");
                att_MdEnterWarehouse.setReceiptDatetime(new Date());
                att_MdEnterWarehouse.setBillType("1");
                this.newObject(att_MdEnterWarehouse);
                SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher;
                Double ratio;

                List<String> indexs = new ArrayList<>();
                String matCodes = "";
                String matNames = "";
                String parts = "";
                String secondParts = "";
                String basicUnits = "";
                String units = "";
                String packages = "";
                for (List<String> did : list_read) {
                    if (idx > 2 && did != null && did.size() > 0) {
                        String errorStr = this.checkImpData1(did);
                        if (errorStr.equals("")) {
                            indexs.add(did.get(0));
                            matCodes += did.get(1) + ",";
                        } else {
                            srm.setCode(4L);
                            srm.setObj(idx);
                            srm.setMeg(errorStr);
                            return srm;
                        }
                    }
                    idx ++;
                }
                List<MdMaterielInfo> mdMaterielInfos = null;
                int matCodesLength = 0;
                String[] matCodeArray = null;
                if (!matCodes.equals("")) {
                    matCodes = matCodes.substring(0, matCodes.length() - 1);
                    matCodeArray = matCodes.split(",");
                    matCodesLength = matCodeArray.length;
                    String tempMatCodes = "";
                    for (String codes : matCodeArray) {
                        tempMatCodes += "'" + codes.trim() + "',";
                    }
                    tempMatCodes = tempMatCodes.substring(0, tempMatCodes.length() - 1);
                    MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
                    mdMaterielInfo.setWzId(account.getOldId());
                    mdMaterielInfo.setPurchaseType(purchaseType);
                    mdMaterielInfo.setMatCodes(tempMatCodes);
//                    mdMaterielInfo.setMatNames(matNames);
//                    mdMaterielInfo.setBasicUnits(basicUnits);
//                    mdMaterielInfo.setUnits(units);
//                    mdMaterielInfo.setParts(parts);
//                    mdMaterielInfo.setSecondParts(secondParts);
//                    mdMaterielInfo.setPackages(packages);
                    mdMaterielInfos = mdMaterielInfoDao.getListByMdMaterielInfo(mdMaterielInfo);
                }
                if (matCodeArray == null || mdMaterielInfos == null || matCodesLength == 0 || mdMaterielInfos.isEmpty()) {
                    srm.setCode(4L);
                    srm.setMeg("导入数据都不存在！");
                    return srm;
                } else {
                    //需要根据规格名称判断是否能够入库
//                    if (mdMaterielInfos.size() < matCodesLength) {
                        int index = 0;
                        boolean notExsit = false;
                        String notExsitIndex = "";
                        for (String matCode : matCodeArray) {
                            List<String> did = list_read.get(index + 3);
                            System.out.println(did.get(2));
                            String matName = (did.size() >= 3 && did.get(2) != null) ? did.get(2) : "";
                            String part = (did.size() >= 4 && did.get(3) != null) ? did.get(3) : "";
                            matcher = pattern.matcher(part);
                            Integer mdpId = null;
                            Integer mdpsId = null;
                            String mdpName = "";
                            String mdpsName = "";
                            if (matcher.find())
                                mdpId = Integer.parseInt(matcher.group(0));
                            if (matcher.find())
                                mdpsId = Integer.parseInt(matcher.group(0));
                            if (mdpId == null && mdpsId == null) {
                                String[] pA = part.split("/");
                                if (pA.length >= 1 && !pA[0].equals(""))
                                    mdpName = pA[0] == null ? "" : pA[0];
                                if (pA.length >= 2 && !pA[1].equals(""))
                                    mdpsName = pA[1] == null ? "" : pA[1];
                            }
                            String bu = (did.size() >= 7 && did.get(6) != null) ? did.get(6) : "";
                            String basicUnit = null;
                            String unit = null;
                            if (bu != null) {
                                String[] buA = bu.split("/");
                                if (buA.length >= 1 && !buA[0].equals(""))
                                    basicUnit = buA[0] == null ? "" : buA[0];
                                if (buA.length >= 2 && !buA[1].equals(""))
                                    unit = buA[1] == null ? "" : buA[1];
                            }
                            String pc = (did.size() >= 10 && did.get(9) != null) ? did.get(9) : "";
                            for (MdMaterielInfo mdMaterielInfo : mdMaterielInfos) {
                                System.out.println(Objects.equal(mdpId, mdMaterielInfo.getMdpId()) && Objects.equal(mdpsId, mdMaterielInfo.getMdpsId()));
                                System.out.println(mdpName.equals(mdMaterielInfo.getMdpName() == null ? "" : mdMaterielInfo.getMdpName()) && (mdpsName.equals(mdMaterielInfo.getMdpsName() == null ? "" : mdMaterielInfo.getMdpsName())));
                                if (matCode.equals(mdMaterielInfo.getMatCode()) && matName.equals(mdMaterielInfo.getMatName()) &&
                                        ((Objects.equal(mdpId, mdMaterielInfo.getMdpId()) && Objects.equal(mdpsId, mdMaterielInfo.getMdpsId())) ||
                                                (mdpName.equals(mdMaterielInfo.getMdpName() == null ? "" : mdMaterielInfo.getMdpName()) && (mdpsName.equals(mdMaterielInfo.getMdpsName() == null ? "" : mdMaterielInfo.getMdpsName())))) &&
                                        basicUnit.equals(mdMaterielInfo.getBasicUnit() == null ? "" : mdMaterielInfo.getBasicUnit()) && unit.equals(mdMaterielInfo.getSplitUnit() == null ? "" : mdMaterielInfo.getSplitUnit()) &&
                                        pc.equals(mdMaterielInfo.getProductName() == null ? "" : mdMaterielInfo.getProductName())) {
                                    notExsit = false;
                                    break;
                                } else {
                                    notExsit = true;
                                }
                            }
                            if (notExsit) {
                                notExsitIndex += indexs.get(index) + "、";
                            }
                            index++;
                        }
                        if (!notExsitIndex.equals("")) {
                            srm.setCode(3L);
                            srm.setObj(notExsitIndex.substring(0, notExsitIndex.length() - 1));
                            return srm;
                        }
//                    }
                }
                idx = 0;
                List<MdEnterWarehouseMx> mdEnterWarehouseMxList = new ArrayList<>();
                List<MdInventoryExtend> mdInventoryExtendList = new ArrayList<>();
                for (List<String> did : list_read) {
                    if (idx == 0) {
                        Map<String, String> map = this.getImpData1(did);
                        String purchaseName = map.get("purchaseName");
                        if (purchaseName != null && !purchaseName.equals(account.getOrgName())) {

                        }
                        att_MdEnterWarehouse.setCreateRen(map.get("createRen"));
                        String date = map.get("createDate");
                        if (date != null && !date.equals("") && date.split(" ").length <= 1) {
                            date += " 00:00:00";
                            att_MdEnterWarehouse.setCreateDate(sdf.parse(date));
                        }
                        this.updateObject(att_MdEnterWarehouse);
                    }else if (idx > 2 && did != null && did.size() > 0) {
                        String errorStr = this.checkImpData1(did);
                        if (errorStr.equals("")) {
                            MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
                            for (String matCode : matCodeArray) {
                                for (MdMaterielInfo mdMaterielInfo1 : mdMaterielInfos) {
                                    if (matCode.equals(mdMaterielInfo1.getMatCode())) {
                                        mdMaterielInfo = mdMaterielInfo1;
                                    }
                                }
                            }

                            String invenStr = did.get(5);
                            matcher = pattern.matcher(invenStr);
                            Double quantity = 0d;
                            Double baseNumber = 0d;
                            if (matcher.find())
                                quantity = Double.parseDouble(matcher.group(0));

                            if (matcher.find())
                                baseNumber = Double.parseDouble(matcher.group(0));
//                            ratio = baseNumber / quantity;

                            String formatName = did.get(4);
                            String[] formatNames = formatName.split(",");
                            Double matNumber = quantity;
                            expectNumber += matNumber;

                            Date productPTime = null;
                            Date productValiTime = (did.size() > 12 && did.get(12) != null) ? sdf.parse(did.get(12)) : null;
                            String packasg = (did.size() > 9 && did.get(9) != null) ? did.get(9) : mdMaterielInfo.getProductName();
                            String factory = (did.size() > 10 && did.get(10) != null) ? did.get(10) : mdMaterielInfo.getProductFactory();
                            String certno = (did.size() > 11 && did.get(11) != null) ? did.get(11) : mdMaterielInfo.getBatchCertNo();

                            String unit = null;
                            Double splitNumber = baseNumber;
                            splitNumber += (mdMaterielInfo.getBasicCoefficent() == null ? 1d : Double.parseDouble(mdMaterielInfo.getBasicCoefficent())) * quantity;
                            String backPrinting = null;

                            Double warningShu = null;
                            Double maxShu = null;

                            String warning = did.get(7);
                            String[] warnings ;
                            if (warning != null && !warning.trim().equals("")) {
                                warnings = warning.split("/");
                                warningShu = Double.parseDouble(warnings[0]);
                                if (warnings.length > 1)
                                    maxShu = Double.parseDouble(warnings[1]);
                            }

//                            String part = did.get(3);
//                            matcher = pattern.matcher(part);
                            Integer mdpId = null;
                            Integer mdpsId = null;
//                            if (matcher.find())
//                                mdpId = Integer.parseInt(matcher.group(0));
//                            if (matcher.find())
//                                mdpsId = Integer.parseInt(matcher.group(0));
//                            if (mdpId == null && mdpsId == null){
//                                String[] parts = part.split(",");
//                                if (parts.length > 1) {
//                                    String mdpName = "";
//                                    mdpName = parts[0];
//                                    mdpId = mdMaterielPartDao.findMdMaterielIdByName(mdpName);
//                                }
//                                if (parts.length > 2) {
//                                    String mdpsName = "";
//                                    mdpsName = parts[1];
//                                    mdpsId = mdMaterielPartSecondDaoDaoDao.findMdMaterielIdByName(mdpsName);
//                                }
//                                if ( mdpId == null)
                                    mdpId = mdMaterielInfo.getMdpId();
//                                if (mdpsId == null)
                                    mdpsId = mdMaterielInfo.getMdpsId();
//                            }

                            MdMaterielFormat format = null;
                            for (String formatNameS : formatNames) {
                                List<MdMaterielFormat> formats = mdMaterielFormatDao.findMdMaterielFormatByMffName(formatNameS, mdMaterielInfo.getWmsMiId(), null, null, null);
                                if (formats == null || formats.isEmpty()) {
                                    format = new MdMaterielFormat();
                                    format.setWmsMiId(mdMaterielInfo.getWmsMiId());
                                    format.setMmfCode(CreateCodeUtil.getNo("MMF"));
                                    format.setMmfName(formatNameS);
                                    format.setState("1");
                                    this.newObject(format);
                                } else {
                                    format = formats.get(0);
                                }



                                Double price = (did.size() > 16 && did.get(16) != null) ? Double.parseDouble(did.get(16)) : (format.getPrice() == null ? 0 : format.getPrice());
                                Double retialPrice = (did.size() > 17 && did.get(17) != null) ? Double.parseDouble(did.get(17)) : (format.getRetailPrice() == null ? 0 : format.getRetailPrice());
                                allPrice += price;
                                allRetailPrice += retialPrice;
                                //保存入库明细信息
                                MdEnterWarehouseMx mdEnterWarehouseMx = new MdEnterWarehouseMx();
                                mdEnterWarehouseMx.setWewId(att_MdEnterWarehouse.getWewId());
                                mdEnterWarehouseMx.setWmsMiId(mdMaterielInfo.getWmsMiId());
                                mdEnterWarehouseMx.setMmfId(format.getMmfId());
                                mdEnterWarehouseMx.setLinkMmfId(format.getMmfId());
                                mdEnterWarehouseMx.setLinkWmsMiId(mdMaterielInfo.getWmsMiId());
                                mdEnterWarehouseMx.setPrice(price);
                                mdEnterWarehouseMx.setRetailMoney(retialPrice);
                                mdEnterWarehouseMx.setMatNumber(matNumber);
                                mdEnterWarehouseMx.setNumber1(splitNumber);
                                mdEnterWarehouseMx.setSplitQuantity(splitNumber);
                                mdEnterWarehouseMx.setMatCode(mdMaterielInfo.getMatCode());
                                mdEnterWarehouseMx.setMatName(mdMaterielInfo.getMatName());
                                mdEnterWarehouseMx.setBasicUnit(mdMaterielInfo.getBasicUnit());
                                mdEnterWarehouseMx.setNorm(format.getMmfName());
                                mdEnterWarehouseMx.setMatType(mdMaterielInfo.getMatType());
                                mdEnterWarehouseMx.setMatType1(mdMaterielInfo.getMatType1());
                                mdEnterWarehouseMx.setMatType2(mdMaterielInfo.getMatType2());
                                mdEnterWarehouseMx.setMatType3(mdMaterielInfo.getMatType3());
                                mdEnterWarehouseMx.setReceiptDatetime(att_MdEnterWarehouse.getReceiptDatetime());
                                mdEnterWarehouseMx.setProductTime(productPTime);
                                mdEnterWarehouseMx.setProductValitime(productValiTime);
                                mdEnterWarehouseMx.setPackageWay(packasg);
                                mdEnterWarehouseMx.setProductFactory(factory);
                                mdEnterWarehouseMx.setBatchCertNo(certno);
                                if (retialPrice != null)
                                    mdEnterWarehouseMx.setRetailMoney(retialPrice);
//					if (unit != null)
                                mdEnterWarehouseMx.setUnit((mdMaterielInfo.getSplitUnit() == null ? mdMaterielInfo.getBasicUnit() : mdMaterielInfo.getSplitUnit()));
                                mdEnterWarehouseMx.setBasicUnit(mdMaterielInfo.getBasicUnit());
//                                this.newObject(mdEnterWarehouseMx);

                                //增加ItemKey值
                                MdItemMxView mdItemMxView = new MdItemMxView();
                                mdItemMxView.setRbaId(one);//从session中获取集团ID
                                mdItemMxView.setRbsId(two);//从session中获取医院ID
                                mdItemMxView.setRbbId(three);//从session中获取门诊ID
                                mdItemMxView.setPurchaseType(att_MdEnterWarehouse.getPurchaseType());//从session中采购单位类型
                                mdItemMxView.setMmfId(format.getMmfId());
                                mdItemMxView.setWmsMiId(mdMaterielInfo.getWmsMiId());
//                                mdItemMxView.setLinkMmfId(format.getMmfId());
//                                mdItemMxView.setLinkWmsMiId(mdMaterielInfo.getWmsMiId());
                                mdItemMxView.setMatName(mdMaterielInfo.getMatName());
                                mdItemMxView.setMmfName(format.getMmfName());
                                mdItemMxView = mdItemKeyDao.newMdItemKey(mdItemMxView, format.getMmfId(), mdMaterielInfo.getWmsMiId());
                                //增加库存
                                MdInventory att_MdInventory = new MdInventory();
                                att_MdInventory.setRbaId(one);//从session中获取集团ID
                                att_MdInventory.setRbsId(two);//从session中获取医院ID
                                att_MdInventory.setRbbId(three);//从session中获取门诊ID
                                att_MdInventory.setPurchaseType((Integer.parseInt(purchaseType) - 1) + "");//从session中采购单位类型
                                att_MdInventory.setItemKeyId(mdItemMxView.getItemKeyId()+"");
                                att_MdInventory.setState("1");
                                att_MdInventory.setUnit(mdMaterielInfo.getBasicUnit());
                                att_MdInventory.setBasicUnit(mdMaterielInfo.getSplitUnit() == null ? mdMaterielInfo.getBasicUnit() : mdMaterielInfo.getSplitUnit());
//                                att_MdInventory.setQuantity(matNumber);
                                if (warningShu != null)
                                    att_MdInventory.setWarningShu(warningShu);
                                if (maxShu != null)
                                    att_MdInventory.setMaxShu(maxShu);
                                att_MdInventory =mdInventoryDao.insertMdInventory(att_MdInventory, mdMaterielInfo.getBasicCoefficent(), matNumber, splitNumber);
                                //保存库存明细信息
                                MdInventoryExtend att_MdInventoryExtend=new MdInventoryExtend();
                                att_MdInventoryExtend.setWiId(att_MdInventory.getWiId());
                                att_MdInventoryExtend.setWmsMiId(mdMaterielInfo.getWmsMiId());
                                att_MdInventoryExtend.setMmfId(format.getMmfId());
                                att_MdInventoryExtend.setLinkWmsMiId(mdMaterielInfo.getWmsMiId());
                                att_MdInventoryExtend.setLinkMmfId(format.getMmfId());
                                att_MdInventoryExtend.setBasicUnit(att_MdInventory.getBasicUnit());
                                att_MdInventoryExtend.setUnit(att_MdInventory.getUnit());
                                att_MdInventoryExtend.setBaseNumber(splitNumber);
                                att_MdInventoryExtend.setQuantity(Math.floor(att_MdInventoryExtend.getBaseNumber() / att_MdInventory.getRatio()));
                                att_MdInventoryExtend.setBasePrice(price/att_MdInventory.getRatio());
                                att_MdInventoryExtend.setPrice(price);
                                att_MdInventoryExtend.setRelatedCode(att_MdEnterWarehouse.getBillcode());
                                att_MdInventoryExtend.setPurchaseUser(att_MdEnterWarehouse.getConsignee());
                                att_MdInventoryExtend.setCreateDate(new Date());
                                att_MdInventoryExtend.setEditDate(new Date());
                                att_MdInventoryExtend.setBatchCode(mdMaterielInfo.getBatchCode());
                                att_MdInventoryExtend.setMatName(mdMaterielInfo.getMatName());
                                att_MdInventoryExtend.setMmfName(format.getMmfName());
                                att_MdInventoryExtend.setApplicantName(mdMaterielInfo.getApplicantName());
                                att_MdInventoryExtend.setLabelInfo(mdMaterielInfo.getLabelInfo());
                                att_MdInventoryExtend.setMdpId(mdpId);
                                att_MdInventoryExtend.setMdpsId(mdpsId);
                                /**
                                 * yanglei
                                 * 修改字段ProductNumber 改为ProductFactory get这个字段时用到
                                 */
                                att_MdInventoryExtend.setProductName(mdMaterielInfo.getProductFactory());
                                att_MdInventoryExtend.setBrand(mdMaterielInfo.getBrand());
                                att_MdInventoryExtend.setState("1");
                                att_MdInventoryExtend.setRetailPrice(retialPrice == null ? format.getRetailPrice() : retialPrice);
                                if (unit != null)
                                    att_MdInventoryExtend.setUnit(unit);
                                if (splitNumber != null)
                                    att_MdInventoryExtend.setSplitQuantity(splitNumber);
                                if (backPrinting != null)
                                    att_MdInventoryExtend.setBackPrinting(backPrinting);
                                att_MdInventoryExtend.setWewMxId(mdEnterWarehouseMx.getWewMxId());
//                                this.newObject(att_MdInventoryExtend);
                                mdInventoryExtendList.add(att_MdInventoryExtend);


                                mdEnterWarehouseMx.setRatio1(att_MdInventory.getRatio());
                                mdEnterWarehouseMx.setCurNumber(att_MdInventory.getBaseNumber());
                                mdEnterWarehouseMx.setWiId(att_MdInventory.getWiId());
//					mdEnterWarehouseMx.setMieId(att_MdInventoryExtend.getMieId());
                                mdEnterWarehouseMxList.add(mdEnterWarehouseMx);
//                                this.updateObject(mdEnterWarehouseMx);
                            }
                            tureCount++;
                        } else {
                            ErrorImpDataModel errorImpDataModel = new ErrorImpDataModel();
                            errorImpDataModel.setMatName(did.get(0));
                            if (did.size() > 1)
                                errorImpDataModel.setMmfName(did.get(1));
                            if (did.size() > 2)
                                errorImpDataModel.setApplicantName(did.get(2));
                            if (did.size() > 3)
                                errorImpDataModel.setProductName(did.get(3));
                            if (did.size() > 4)
                                errorImpDataModel.setBrand(did.get(4));
                            if (did.size() > 5)
                                errorImpDataModel.setUnit(did.get(5));
                            if (did.size() > 6)
                                errorImpDataModel.setMatType(did.get(6));
                            if (did.size() > 7)
                                errorImpDataModel.setPrice(did.get(7));
                            if (did.size() > 8)
                                errorImpDataModel.setNum(did.get(8));
                            errorImpDataModel.setErrorStr(errorStr);
                            errorList.add(errorImpDataModel);
                        }
                    }
                    idx ++;
                }
                att_MdEnterWarehouse.setExpectNumber(expectNumber);
//                att_MdEnterWarehouse.setRetailMoney(allRetailPrice);
//                att_MdEnterWarehouse.setPurchaseMoney(allPrice);
                this.updateObject(att_MdEnterWarehouse);

                if (!mdInventoryExtendList.isEmpty())
                    mdInventoryDao.saveMdInventoryExtendByBatch(mdInventoryExtendList);
                if (!mdEnterWarehouseMxList.isEmpty())
                    mdInventoryDao.saveMdEnterWarehouseMxByBatch(mdEnterWarehouseMxList);

                Map<String, Object> reMap = new HashMap<String, Object>();
                reMap.put("rightCount", tureCount);
                if (errorList.size() > 0) {
                    reMap.put("errorCount", errorList.size());
                    //创建错误文件
                    srm.setCode(2l);
                    Calendar now = Calendar.getInstance();
                    long l = now.getTimeInMillis();
                    String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
                    File file = new File(tmpPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    String realPath = tmpPath + l + ".xls";
                    ExportExcel<Object> ex = new ExportExcel<Object>();
                    String[] headers = {"物料名称", "型号名称", "供应商", "生产厂家", "品牌", "单位", "物料类别", "单价", "数量", "错误信息"};
                    OutputStream out = null;
                    out = new FileOutputStream(realPath);
                    ex.exportExcel("错误数据", headers, errorList, out, "yyyy-MM-dd HH:mm:ss");
                    out.close();
                    String rootUrl = request.getContextPath() + EXPORT_PATH + l + ".xls";
                    reMap.put("rootPath", rootUrl);
                    reMap.put("filePath", realPath);
                }
                Date end_date = new Date();
                long interval = (end_date.getTime() - star_date.getTime()) / 1000;
                reMap.put("timeCount", interval);
                srm.setObj(reMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(0l);
            srm.setMeg("操作失败！");
            throw new HSKException(e);
        }
        return srm;
    }

    private String checkImpData(List<String> did) {
        String errorStr = "";
        if (did.get(0) == null || "".equals(did.get(0).trim()))
            errorStr = "物料名称为空";
        else if (did.size() == 1 || did.get(1) == null || "".equals(did.get(1).trim()))
            errorStr = "型号名称为空";
        else if (did.size() < 8 || did.get(7) == null || "".equals(did.get(7).trim())) {
            errorStr = "单价为空";
        } else if (!CheckUtil.isNumeric(did.get(7).trim())) {
            errorStr = "单价格式不正确";
        } else if (did.size() < 9 || did.get(8) == null || "".equals(did.get(8).trim())) {
            errorStr = "数量为空";
        } else if (!CheckUtil.isNumeric(did.get(8).trim())) {
            errorStr = "数量格式不正确";
        }
        return errorStr;
    }

    private String checkImpData1(List<String> did) {
        String errorStr = "";
        if (did.get(1) == null || "".equals(did.get(1).trim()))
            errorStr = "物料编码为空";
        else if (did.size() == 2 || did.get(2) == null || "".equals(did.get(2).trim()))
            errorStr = "物料名称为空";
        else if (did.size() == 3 || did.get(3) == null || "".equals(did.get(3).trim()))
            errorStr = "分类为空";
        else if (did.size() == 4 || did.get(4) == null || "".equals(did.get(4).trim()))
            errorStr = "规格为空";
        else if (did.size() == 5 || did.get(5) == null || "".equals(did.get(5).trim()))
                errorStr = "库存数为空";
        else if (did.size() == 6 || did.get(6) == null || "".equals(did.get(6).trim()))
            errorStr = "单位为空";
        else if (did.size() == 9 || did.get(9) == null || "".equals(did.get(9).trim()))
            errorStr = "包装方式为空";
//        else if (!CheckUtil.isNumeric(did.get(7).trim())) {
//            errorStr = "单价格式不正确";
//        } else if (did.size() < 9 || did.get(8) == null || "".equals(did.get(8).trim())) {
//            errorStr = "数量为空";
//        } else if (!CheckUtil.isNumeric(did.get(8).trim())) {
//            errorStr = "数量格式不正确";
//        }
        return errorStr;
    }
    private Map<String, String> getImpData1(List<String> did) {
        Map<String, String> result = new HashMap<>();
        if(did.get(1) != null && !did.get(1).trim().equals(""))
            result.put("purchaseName", did.get(1));
        if(did.get(6) != null && !did.get(6).trim().equals(""))
            result.put("createRen", did.get(6));
        if(did.get(13) != null && !did.get(13).trim().equals(""))
            result.put("createDate", did.get(13));
        if(did.size() > 16 && did.get(16) != null && !did.get(16).trim().equals(""))
            result.put("rangeDate", did.get(16));
        return result;
    }

    @Override
    public SysRetrunMessage deleteExtendObject(Integer mieId)
            throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            MdInventoryExtend att_MdInventoryExtend = new MdInventoryExtend();
            att_MdInventoryExtend.setMieId(mieId);
            att_MdInventoryExtend = (MdInventoryExtend) this.getOne(att_MdInventoryExtend);
            att_MdInventoryExtend.setState("0");
            this.updateObject(att_MdInventoryExtend);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }

    @Override
    public PagerModel getExtendViewPagerModel(MdInventoryExtendView mdInventoryExtendView, String startDate, String endDate) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdInventoryExtendView>());
        try {
            pm = mdInventoryExtendDao.getMdInventoryViewPager(mdInventoryExtendView, startDate, endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage addItemKeyMx(Integer wiId, String addWiIds)
            throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            SysUserInfo sysUserInfo = this.GetOneSessionAccount();
            MdInventory att_MdInventory = mdInventoryDao.findMdInventoryById(wiId);
            Double quantityAll = 0d;
            Double baseNumberAll = 0d;
            String[] addWiIdArray = addWiIds.split(",");

            MdInventoryMergeLogEntity mdInventoryMergeLogEntity;
            List<MdInventory> mdInventories = new ArrayList<>();
            for (String addWiId : addWiIdArray) {
                MdInventory add_MdInventory = mdInventoryDao.findMdInventoryById(Integer.parseInt(addWiId));
                if (add_MdInventory.getRatio() != null && !add_MdInventory.getRatio().equals(att_MdInventory.getRatio())) {
                    srm.setMeg("折算系数不一致不允许合并!");
                    srm.setCode(2L);
                    return srm;
                }
                if (add_MdInventory.getBasicUnit() != null && !add_MdInventory.getBasicUnit().equals(att_MdInventory.getBasicUnit())){
                    srm.setMeg("单位不同，不允许合并!");
                    srm.setCode(2L);
                    return srm;
                }
                if (add_MdInventory.getUnit() != null && !add_MdInventory.getUnit().equals(att_MdInventory.getUnit())) {
                    srm.setMeg("最小单位不同，不允许合并!");
                    srm.setCode(2L);
                    return srm;
                }
                mdInventories.add(add_MdInventory);
            }
            if (mdInventories.isEmpty()) {
                return srm;
            }
            SysUserInfo account = this.GetOneSessionAccount();
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            String purchaseType = null;
            if (account.getOrganizaType().equals("20001")) {
                rbaId = account.getOldId();
                purchaseType = "1";
            } else if (account.getOrganizaType().equals("20002")) {
                rbsId = account.getOldId();
                purchaseType = "2";
            } else if (account.getOrganizaType().equals("20003")) {
                rbbId = account.getOldId();
                purchaseType = "3";
            }
            List<String> mmfIdArray = new ArrayList<>();
            for (MdInventory add_MdInventory : mdInventories) {
//                MdInventory add_MdInventory = mdInventoryDao.findMdInventoryById(Integer.parseInt(addWiId));
//                if (add_MdInventory.getRatio() != null && !add_MdInventory.getRatio().equals(att_MdInventory.getRatio())) {
//                    Exception e = new Exception("折算系数不一致不允许合并");
//                    srm.setMeg("折算系数不一致不允许合并!");
//                    throw new HSKException("折算系数不一致不允许合并");
//                }
//                if (add_MdInventory.getBasicUnit() != null && !add_MdInventory.getBasicUnit().equals(att_MdInventory.getBasicUnit())){
//                    Exception e = new Exception("单位不同");
//                    srm.setMeg("单位不同，不允许合并!");
//                    throw new HSKException("单位不同");
//                }
//                if (add_MdInventory.getUnit() != null && !add_MdInventory.getUnit().equals(att_MdInventory.getUnit())) {
//                    Exception e = new Exception("最小单位不同");
//                    srm.setMeg("最小单位不同，不允许合并!");
//                    throw new HSKException("最小单位不同");
//                }
                mdInventoryMergeLogEntity = new MdInventoryMergeLogEntity();
                MdItemMx addItemMx = new MdItemMx();
                addItemMx.setItemKeyId(Integer.parseInt(add_MdInventory.getItemKeyId()));
                List<MdItemMx> addItemMxList = this.getList(addItemMx);
                if (addItemMxList != null && addItemMxList.size() > 0) {
                    for (MdItemMx mx : addItemMxList) {
                        mx.setItemKeyId(Integer.parseInt(att_MdInventory.getItemKeyId()));
                        this.updateObject(mx);
                    }
                    //修改库存明细
                    MdInventoryExtend mdInventoryExtend = new MdInventoryExtend();
                    mdInventoryExtend.setWiId(add_MdInventory.getWiId());
                    List<MdInventoryExtend> mdInventoryExtendList = this.getList(mdInventoryExtend);

                    if (mdInventoryExtendList != null && mdInventoryExtendList.size() > 0) {
                        for (MdInventoryExtend extend : mdInventoryExtendList) {
                            extend.setBasicUnit(att_MdInventory.getBasicUnit());
                            extend.setWiId(att_MdInventory.getWiId());
                            quantityAll += extend.getQuantity();
                            baseNumberAll += extend.getBaseNumber();
                            this.updateObject(extend);

                            MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
                            mdMaterielFormat.setMmfId(extend.getMmfId());
                            mdMaterielFormat = (MdMaterielFormat) this.getOne(mdMaterielFormat);

                            mdInventoryMergeLogEntity.setWiId(extend.getWiId());
                            mdInventoryMergeLogEntity.setWmsMiId(extend.getWmsMiId());
                            mdInventoryMergeLogEntity.setMmfId(extend.getMmfId());
                            mdInventoryMergeLogEntity.setMmfName(extend.getMmfName());
//                            mdInventoryMergeLogEntity.setMmfCode(extend.getMdnCode());
                            mdInventoryMergeLogEntity.setAvgPrice(mdInventoryDao.getInventoryExtendAvgPrice(extend.getWiId()));
                            mdInventoryMergeLogEntity.setBaseNumber(Double.parseDouble(extend.getBaseNumber().toString()));
                            mdInventoryMergeLogEntity.setQuantity(Math.floor(mdInventoryMergeLogEntity.getBaseNumber() / att_MdInventory.getRatio()));
                            mdInventoryMergeLogEntity.setBasicUnit(extend.getBasicUnit());
                            mdInventoryMergeLogEntity.setUnit(extend.getUnit());
                            mdInventoryMergeLogEntity.setMdpId(extend.getMdpId());
                            mdInventoryMergeLogEntity.setMdpsId(extend.getMdpsId());
                            mdInventoryMergeLogEntity.setBrand(extend.getBrand());
                            mdInventoryMergeLogEntity.setProductFactory(extend.getProductName());
                            mdInventoryMergeLogEntity.setRetailPrice(mdMaterielFormat == null ? extend.getRetailPrice() : mdMaterielFormat.getRetailPrice());
                            // 当前库存变化 后的数据
                            mdInventoryMergeLogEntity.setCurNumber(att_MdInventory.getBaseNumber() + (extend.getBaseNumber() - att_MdInventory.getBaseNumber()));
                            mdInventoryMergeLogEntity.setSuiId(sysUserInfo.getSuiId());
                            mdInventoryMergeLogEntity.setMatName(extend.getMatName());
                            this.newObject(mdInventoryMergeLogEntity);
                            mmfIdArray.add(extend.getMmfId().toString());
                        }
                    }
                }
                MdItemKey addItemKey = new MdItemKey();
                addItemKey.setItemKeyId(Integer.parseInt(add_MdInventory.getItemKeyId()));
                addItemKey = (MdItemKey) this.getOne(addItemKey);
                this.deleteObjects(addItemKey);
                this.deleteObjects(add_MdInventory);

            }

            if (!mmfIdArray.isEmpty()) {
                String addMmfIds = StringUtils.join(mmfIdArray.toArray(), ",");
                // 逻辑删除
                mdEnterWarehouseDao.updateEnterwarehouseDeleteLogic(addMmfIds, rbaId, rbsId, rbbId, purchaseType, 1);
                mdOutWarehouseDao.updateEnterwarehouseDeleteLogic(addMmfIds, rbaId, rbsId, rbbId, purchaseType, 1);
            }

            att_MdInventory.setBaseNumber(att_MdInventory.getBaseNumber() + baseNumberAll);
            att_MdInventory.setQuantity(Math.floor(att_MdInventory.getBaseNumber() / att_MdInventory.getRatio()));
            this.updateObject(att_MdInventory);

        } catch (Exception e) {
            e.printStackTrace();
            srm.setCode(2L);
//            srm.setMeg("折算系数不一致不允许合并!");
            throw new HSKException("单位或者系数不同，无法合并");
        }
        return srm;
    }

    @Override
    public SysRetrunMessage delItemKeyMx(Integer wiId, Integer mimId)
            throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            String purchaseType = null;
            if (account.getOrganizaType().equals("20001")) {
                rbaId = account.getOldId();
                purchaseType = "1";
            } else if (account.getOrganizaType().equals("20002")) {
                rbsId = account.getOldId();
                purchaseType = "2";
            } else if (account.getOrganizaType().equals("20003")) {
                rbbId = account.getOldId();
                purchaseType = "3";
            }
            List<String> mmfIdArray = new ArrayList<>();
            //查询现有的库存
            MdInventory att_MdInventory = mdInventoryDao.findMdInventoryById(wiId);
            //查询itemkey
            MdItemKey att_MdItemKey = new MdItemKey();
            att_MdItemKey.setItemKeyId(Integer.parseInt(att_MdInventory.getItemKeyId()));
            att_MdItemKey = (MdItemKey) this.getOne(att_MdItemKey);
            //查询itemkey明细
            MdItemMx att_MdItemMx = new MdItemMx();
            att_MdItemMx.setMimId(mimId);
            att_MdItemMx = (MdItemMx) this.getOne(att_MdItemMx);
            //查询商品信息
            MdMaterielFormatView formatView = new MdMaterielFormatView();
            formatView.setMmfId(att_MdItemMx.getMmfId());
            formatView = (MdMaterielFormatView) this.getOne(formatView);
            //新建库存信息
            MdItemKey new_MdItemKey = new MdItemKey();
            new_MdItemKey.setRbaId(att_MdItemKey.getRbaId());
            new_MdItemKey.setRbsId(att_MdItemKey.getRbsId());
            new_MdItemKey.setRbbId(att_MdItemKey.getRbbId());
            new_MdItemKey.setPurchaseType(att_MdItemKey.getPurchaseType());
            new_MdItemKey.setMatName(formatView.getMatName());
            new_MdItemKey.setMmfName(formatView.getMmfName());
            String mmfNamePy = "";
            String matNamePy = "";
            if (!formatView.getMmfName().trim().equals("")) {
                for (int i = 0; i < formatView.getMmfName().trim().length(); i++)
                    mmfNamePy += PingYinUtil.getLetterFormChinese(formatView.getMmfName().trim().charAt(i));
            }
            if (!formatView.getMatName().trim().equals("")) {
                for (int i = 0; i < formatView.getMatName().trim().length(); i++)
                    matNamePy += PingYinUtil.getLetterFormChinese(formatView.getMatName().trim().charAt(i));
            }
            new_MdItemKey.setMatNamePy(matNamePy);
            new_MdItemKey.setMmfNamePy(mmfNamePy);

            this.newObject(new_MdItemKey);
            MdInventory new_MdInventory = new MdInventory();
            new_MdInventory.setRbaId(att_MdInventory.getRbaId());
            new_MdInventory.setRbsId(att_MdInventory.getRbsId());
            new_MdInventory.setRbbId(att_MdInventory.getRbbId());
            new_MdInventory.setPurchaseType(att_MdInventory.getPurchaseType());
            new_MdInventory.setItemKeyId(new_MdItemKey.getItemKeyId() + "");
            new_MdInventory.setState("1");
            new_MdInventory.setRatio(att_MdInventory.getRatio());
            new_MdInventory.setUnit(att_MdInventory.getUnit());
            new_MdInventory.setBasicUnit(att_MdInventory.getBasicUnit());
            new_MdInventory.setWarningShu(att_MdInventory.getWarningShu());
            new_MdInventory.setMaxShu(att_MdInventory.getMaxShu());
            new_MdInventory.setMinDay(att_MdInventory.getMinDay());
            this.newObject(new_MdInventory);
            att_MdItemMx.setItemKeyId(new_MdItemKey.getItemKeyId());
            this.updateObject(att_MdItemMx);
            //修改库存明细
            MdInventoryExtend mdInventoryExtend = new MdInventoryExtend();
            mdInventoryExtend.setWiId(wiId);
            mdInventoryExtend.setMmfId(att_MdItemMx.getMmfId());
            List<MdInventoryExtend> mdInventoryExtendList = this.getList(mdInventoryExtend);
            if (mdInventoryExtendList != null && mdInventoryExtendList.size() > 0) {
                Double baseNumberAll = 0d;
                for (MdInventoryExtend extend : mdInventoryExtendList) {
                    extend.setBasicUnit(att_MdInventory.getBasicUnit());
                    extend.setWiId(new_MdInventory.getWiId());
                    baseNumberAll += extend.getBaseNumber();
                    this.updateObject(extend);
                    mmfIdArray.add(extend.getMmfId().toString());
                }
                //修改库存数量
                new_MdInventory.setBaseNumber(baseNumberAll);
                new_MdInventory.setQuantity(Math.floor(baseNumberAll / new_MdInventory.getRatio()));
                this.updateObject(new_MdInventory);
                att_MdInventory.setBaseNumber((att_MdInventory.getBaseNumber() - baseNumberAll) <= 0 ? 0 : (att_MdInventory.getBaseNumber() - baseNumberAll));
                att_MdInventory.setQuantity(Math.floor(att_MdInventory.getBaseNumber() / att_MdInventory.getRatio()));
                this.updateObject(att_MdInventory);
            }
            if (!mmfIdArray.isEmpty()) {
                String addMmfIds = StringUtils.join(mmfIdArray.toArray(), ",");
                mdInventoryDao.deleteMergeLog(wiId, addMmfIds);
                // 逻辑删除
                mdEnterWarehouseDao.updateEnterwarehouseDeleteLogic(addMmfIds, rbaId, rbsId, rbbId, purchaseType, 0);
                mdOutWarehouseDao.updateEnterwarehouseDeleteLogic(addMmfIds, rbaId, rbsId, rbbId, purchaseType, 0);
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
    public PagerModel getItemKeyMxList(Integer wiId, String addWiIds) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdItemMxView>());
        try {
            MdInventory att_MdInventory = mdInventoryDao.findMdInventoryById(wiId);
            MdItemMxView itemMx = new MdItemMxView();
            itemMx.setItemKeyId(Integer.parseInt(att_MdInventory.getItemKeyId()));
            String itemKeyIds = "";
            if (addWiIds != null && !addWiIds.equals("")) {
                String[] wiIds = addWiIds.split(",");
//                Integer wiId;
                for (String wiIdStr : wiIds) {
                    wiId = Integer.parseInt(wiIdStr);
                    att_MdInventory = mdInventoryDao.findMdInventoryById(wiId);
                    itemKeyIds += att_MdInventory.getItemKeyId() + ",";
                }
                if (itemKeyIds != "")
                    itemKeyIds = itemKeyIds.substring(0, itemKeyIds.length() - 1);
                itemMx.setItemKeyIds(itemKeyIds);
            }
            List<MdItemMxView> itemMxList = mdInventoryDao.getMdItemMxView(itemMx);// this.getList(itemMx);
            // 获取相关的信息

            //物料编号 x

            //物料图标 x

            //库存数量 x

            //生产厂家 x

            //采购均价 x

            //零售价 x
            pm.setItems(itemMxList);
            pm.setRows(itemMxList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    //设置安全预警最大安全数跟最小安全数
    public SysRetrunMessage saveSafety(String wmsIdStrs, Double inputMin, Double inputMax) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        try {
            String wmsIdStr = wmsIdStrs;
            if (wmsIdStr == "") {
                wmsIdStr = "00";
            }
            String check[] = wmsIdStr.split(",");
            MdInventory mdInventory = null;
            for (String wiIds : check) {
                Integer wiId = Integer.valueOf(wiIds);
                mdInventory = mdInventoryDao.findMdInventoryById(wiId);
                mdInventory.setMaxShu(inputMax);
                mdInventory.setWarningShu(inputMin);
                mdInventoryDao.saveOrUpdateMdInventory(mdInventory);
            }
            srm.setObj(mdInventory);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HSKException(e);
        }
        return srm;
    }

    public SysRetrunMessage saveSafetyNew(String inputDay, String input1, String input2, String checkValue, String checkValue2, String select1, String select2, Double inputMin, Double inputMax) throws HSKException {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        SysUserInfo account = this.GetOneSessionAccount();
        Integer rbaId = 0;
        Integer rbsId = 0;
        Integer rbbId = 0;
        String PurchaseType = null;
        if (account.getOrganizaType().equals("20001")) {
            rbaId = account.getOldId();
            PurchaseType = "1";
        } else if (account.getOrganizaType().equals("20002")) {
            rbsId = account.getOldId();
            PurchaseType = "2";
        } else if (account.getOrganizaType().equals("20003")) {
            rbbId = account.getOldId();
            PurchaseType = "3";
        }
        try {
            MdInventory mdInventory = null;
            if (checkValue.equals("0") && checkValue2.equals("1") && select1.equals("1") && select2.equals("1")) {//满足全部条件
                System.out.println("all");
                List<Map<String, Integer>> list = homePageSupplierDao.getSafetyNewId(input1, input2, rbaId, rbsId, rbbId, PurchaseType, null);
                if (list != null && list.size() > 0) {

                    for (Map<String, Integer> map : list) {
                        Integer wzId = map.get("wi_id");
                        mdInventory = mdInventoryDao.findMdInventoryById(wzId);
                        if (!inputMax.equals("-1"))
                            mdInventory.setMaxShu(inputMax);
                        if (!inputMin.equals("-1"))
                            mdInventory.setWarningShu(inputMin);
                        if (inputDay != null && !inputDay.equals(""))
                            mdInventory.setWarningDay(Integer.parseInt(inputDay));
                        mdInventoryDao.saveOrUpdateMdInventory(mdInventory);
                    }
                }
            } else if (checkValue.equals("0") && select1.equals("1")) {//满足包含产品名称的
                List<Map<String, Integer>> list = homePageSupplierDao.getSafetyNewId(input1, null, rbaId, rbsId, rbbId, PurchaseType, null);
                if (list != null && list.size() > 0) {
                    for (Map<String, Integer> map : list) {
                        Integer wzId = map.get("wi_id");
                        mdInventory = mdInventoryDao.findMdInventoryById(wzId);
                        if (!inputMax.equals("-1"))
                            mdInventory.setMaxShu(inputMax);
                        if (!inputMin.equals("-1"))
                            mdInventory.setWarningShu(inputMin);
                        if (inputDay != null && !inputDay.equals(""))
                            mdInventory.setWarningDay(Integer.parseInt(inputDay));
                        mdInventoryDao.saveOrUpdateMdInventory(mdInventory);
                    }
                }
            } else if (checkValue2.equals("1") && select2.equals("1")) {//满足包含产品规格的
                List<Map<String, Integer>> list = homePageSupplierDao.getSafetyNewId(null, input2, rbaId, rbsId, rbbId, PurchaseType, null);
                if (list != null && list.size() > 0) {
                    for (Map<String, Integer> map : list) {
                        Integer wzId = map.get("wi_id");
                        mdInventory = mdInventoryDao.findMdInventoryById(wzId);
                        if (!inputMax.equals("-1"))
                            mdInventory.setMaxShu(inputMax);
                        if (!inputMin.equals("-1"))
                            mdInventory.setWarningShu(inputMin);
                        if (inputDay != null && !inputDay.equals(""))
                            mdInventory.setWarningDay(Integer.parseInt(inputDay));
                        mdInventoryDao.saveOrUpdateMdInventory(mdInventory);
                    }
                }
            } else if (checkValue.equals("0") && select1.equals("2")) {
                List<Map<String, Integer>> list = homePageSupplierDao.getSafetyNewId(input1, null, rbaId, rbsId, rbbId, PurchaseType, 1);
                if (list != null && list.size() > 0) {
                    for (Map<String, Integer> map : list) {
                        Integer wzId = map.get("wi_id");
                        mdInventory = mdInventoryDao.findMdInventoryById(wzId);
                        if (!inputMax.equals("-1"))
                            mdInventory.setMaxShu(inputMax);
                        if (!inputMin.equals("-1"))
                            mdInventory.setWarningShu(inputMin);
                        if (inputDay != null && !inputDay.equals(""))
                            mdInventory.setWarningDay(Integer.parseInt(inputDay));
                        mdInventoryDao.saveOrUpdateMdInventory(mdInventory);
                    }
                }
            } else if (checkValue2.equals("1") && select2.equals("2")) {
                List<Map<String, Integer>> list = homePageSupplierDao.getSafetyNewId(null, input2, rbaId, rbsId, rbbId, PurchaseType, 2);
                if (list != null && list.size() > 0) {
                    for (Map<String, Integer> map : list) {
                        Integer wzId = map.get("wi_id");
                        mdInventory = mdInventoryDao.findMdInventoryById(wzId);
                        if (!inputMax.equals("-1"))
                            mdInventory.setMaxShu(inputMax);
                        if (!inputMin.equals("-1"))
                            mdInventory.setWarningShu(inputMin);
                        if (inputDay != null && !inputDay.equals(""))
                            mdInventory.setWarningDay(Integer.parseInt(inputDay));
                        mdInventoryDao.saveOrUpdateMdInventory(mdInventory);
                    }
                }
            }
            srm.setObj(mdInventory);
        } catch (HSKDBException e) {
            srm.setCode(3L);
            e.printStackTrace();
        }
        return srm;
    }

    @Override
    public SysRetrunMessage saveSafetyNewByWiId(Integer wiId, String inputDay, Double inputMin, Double inputMax) {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            MdInventory mdInventory = mdInventoryDao.findMdInventoryById(wiId);
            if (mdInventory == null) {
                sm.setCode(2L); //未找到
                return sm;
            }
            if (inputDay != null && !inputDay.equals(""))
                mdInventory.setWarningDay(Integer.parseInt(inputDay));
            if (inputMin != null && inputMin >= 0)
                mdInventory.setWarningShu(inputMin);
            if (inputMax != null && inputMax >= 0)
                mdInventory.setMaxShu(inputMax);
            this.updateObject(mdInventory);
            //设置日期是否预警
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d1;
            Date d2;
//            if (mdInventory.getValiedDate() != null) {
//                c.setTime(mdInventory.getValiedDate());
//                if (mdInventory.getWarningDay() != null) {
//                    c.add(Calendar.DATE, mdInventory.getWarningDay() * -1);
//                    d1 = sdf.parse(sdf.format(c.getTime()));
//                    d2 = sdf.parse(sdf.format(new Date()));
//                    if (d1.before(d2))
//                        mdInventory.setDateIsNot(1);
//                    else
//                        mdInventory.setDateIsNot(2);
//                }
//            }
            sm.setObj(mdInventory);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public PagerModel getPagerModelCheckInventory(MdInventoryCheckViewEntity mdCheckInventoryEntity) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdInventoryCheckViewEntity>());
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                mdCheckInventoryEntity.setRbaId(account.getOldId());
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdCheckInventoryEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                mdCheckInventoryEntity.setRbsId(account.getOldId());
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdCheckInventoryEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    mdCheckInventoryEntity.setRbsId(Integer.parseInt(orgMap.get("tow")));
                }
                mdCheckInventoryEntity.setRbbId(account.getOldId());
            }
            pm = mdInventoryExtendDao.getMdInventoryExtendByCheckInventory(mdCheckInventoryEntity);
            Integer count = mdInventoryExtendDao.getMdInventoryExtendByCheckInventoryCount(mdCheckInventoryEntity);
            pm.setTotal(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }


    @Override
    public PagerModel getPagerModelCI(MdCheckInventoryEntity mdCheckInventoryEntity) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdCheckInventoryEntity>());
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                mdCheckInventoryEntity.setRbaId(account.getOldId());
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdCheckInventoryEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                mdCheckInventoryEntity.setRbsId(account.getOldId());
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdCheckInventoryEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    mdCheckInventoryEntity.setRbsId(Integer.parseInt(orgMap.get("tow")));
                }
                mdCheckInventoryEntity.setRbbId(account.getOldId());
            }
            mdCheckInventoryEntity.setLikeTab("checkName,createRen,checkCode");
            pm = mdInventoryExtendDao.getMdCheckInventory(mdCheckInventoryEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage getOneCheckInventory(Integer ciId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            MdCheckInventoryEntity mdCheckInventoryEntity = new MdCheckInventoryEntity();
            mdCheckInventoryEntity.setCiId(ciId);
            mdCheckInventoryEntity = (MdCheckInventoryEntity) this.getOne(mdCheckInventoryEntity);
            List<Map<String,Object>> list = mdMaterielPartSecondDaoDaoDao.getMaterielPartMapListByMdpsId(mdCheckInventoryEntity.getCheckParts());
            String checkPartsCombine = "";
            for (Map<String, Object> map : list) {
                if (map.get("mdp_id") != null && map.get("mdps_id") != null)
                    checkPartsCombine += map.get("mdps_id").toString() + "_" + map.get("mdp_id").toString() + ",";
            }
            mdCheckInventoryEntity.setCheckPartCombine(checkPartsCombine);
            sm.setObj(mdCheckInventoryEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveCheckInventory(MdCheckInventoryEntity mdCheckInventoryEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                mdCheckInventoryEntity.setRbaId(account.getOldId());
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdCheckInventoryEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                mdCheckInventoryEntity.setRbsId(account.getOldId());
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdCheckInventoryEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    mdCheckInventoryEntity.setRbsId(Integer.parseInt(orgMap.get("tow")));
                }
                mdCheckInventoryEntity.setRbbId(account.getOldId());
            }
            MdCheckInventoryEntity mdCheckInventoryEntity1 = new MdCheckInventoryEntity();
            mdCheckInventoryEntity1.setCheckName(mdCheckInventoryEntity.getCheckName());
            mdCheckInventoryEntity1.setRbaId(mdCheckInventoryEntity.getRbaId());
            mdCheckInventoryEntity1.setRbsId(mdCheckInventoryEntity.getRbsId());
            mdCheckInventoryEntity1.setRbbId(mdCheckInventoryEntity.getRbbId());
            mdCheckInventoryEntity1 = mdInventoryExtendDao.findCheckInventory(mdCheckInventoryEntity1);
            if (mdCheckInventoryEntity1 != null) {
                sm.setCode(3L); // 存在同名
                return sm;
            }
            Integer count = this.newObject(mdCheckInventoryEntity);
            if (count == null || count <= 0) {
                sm.setCode(2L);
            } else {
                sm.setObj(mdCheckInventoryEntity);
            }
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateCheckInventory(MdCheckInventoryEntity mdCheckInventoryEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                mdCheckInventoryEntity.setRbaId(account.getOldId());
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdCheckInventoryEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                mdCheckInventoryEntity.setRbsId(account.getOldId());
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdCheckInventoryEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    mdCheckInventoryEntity.setRbsId(Integer.parseInt(orgMap.get("tow")));
                }
                mdCheckInventoryEntity.setRbbId(account.getOldId());
            }
            MdCheckInventoryEntity mdCheckInventoryEntity1 = new MdCheckInventoryEntity();
            mdCheckInventoryEntity1.setCheckName(mdCheckInventoryEntity.getCheckName());
            mdCheckInventoryEntity1.setRbaId(mdCheckInventoryEntity.getRbaId());
            mdCheckInventoryEntity1.setRbsId(mdCheckInventoryEntity.getRbsId());
            mdCheckInventoryEntity1.setRbbId(mdCheckInventoryEntity.getRbbId());
            if (mdCheckInventoryEntity.getCiId() != null)
                mdCheckInventoryEntity1.setExcludeCiIds(mdCheckInventoryEntity.getCiId().toString());
            mdCheckInventoryEntity1 = mdInventoryExtendDao.findCheckInventory(mdCheckInventoryEntity1);
            if (mdCheckInventoryEntity1 != null) {
                sm.setCode(3L); // 存在同名
                return sm;
            }
            MdCheckInventoryEntity mdCheckInventoryEntity2 = new MdCheckInventoryEntity();
            mdCheckInventoryEntity2.setCiId(mdCheckInventoryEntity.getCiId());
            mdCheckInventoryEntity2 = (MdCheckInventoryEntity) this.getOne(mdCheckInventoryEntity2);
            mdCheckInventoryEntity2.setCheckName(mdCheckInventoryEntity.getCheckName());
            mdCheckInventoryEntity2.setCheckType(mdCheckInventoryEntity.getCheckType());
            mdCheckInventoryEntity2.setCheckPart(mdCheckInventoryEntity.getCheckPart());
            mdCheckInventoryEntity2.setCheckParts(mdCheckInventoryEntity.getCheckParts());
            mdCheckInventoryEntity2.setRemark(mdCheckInventoryEntity.getRemark());
            this.updateObject(mdCheckInventoryEntity2);
            sm.setObj(mdCheckInventoryEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveCheckInventoryMx(MdInventoryView mdInventoryExtend, String wiIds, String nowNumbers, String nowSplitNumbers, Integer ciId, Integer updateOrSave) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        SysUserInfo account = this.GetOneSessionAccount();
        try {
            //增加获取系数与入库一样 2020-07-16 yanglei
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            String orgNameStr = orgDao.getNameStr(sysOrgGx);
            Integer one = null;
            Integer two = null;
            Integer three = null;
            String PurchaseType = "";
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                PurchaseType = "1";
                one = account.getOldId();
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    one = Integer.parseInt(orgMap.get("one"));
                }
                PurchaseType = "2";
                two = account.getOldId();
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    one = Integer.parseInt(orgMap.get("one"));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    two = Integer.parseInt(orgMap.get("tow"));
                }
                PurchaseType = "3";
                three = account.getOldId();
            }

            if (wiIds == null || wiIds.equals("")) {
                if (updateOrSave == null)
                    saveOtherCheck(mdInventoryExtend, ciId);
                return sm;
            }
            String[] wiIdList = wiIds.split(",");
            String[] nowNumberList = nowNumbers.split(",");
            String[] nowSplitNumberList = nowSplitNumbers.split(",");
//            if (mieIdList.length != nowNumberList.length ||
//                    mieIdList.length != nowSplitNumberList.length ||
//                    nowNumberList.length != nowSplitNumberList.length) {
//                sm.setCode(2L); //
//                sm.setMeg("传入数据有误");
//                return sm;
//            }
            Integer idx = 0;
            Integer wiId;
            Integer nowNumber = 0;
            Integer nowSplitNumebr = 0;
            boolean saveOrUpdate = false;
            MdCheckInventoryExEntity mdCheckInventoryExEntity;
            //库存总览
            // quantity 大单位总数
            // baseNumber 小单位数量
            // MdInventoryExtend 库存明细 wiId 库存总览id
            // ratio 拆分系数， quantity * ratio = baseNumber
            // nowNumber * ratio = nowBaseNumber 现有大单位转换成小单位数量
            //
            MdInventory mdInventoryExtend1;

            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> map;
            String excludeMieIds = "";

            for (String wiIdStr : wiIdList) {
                wiId = Integer.parseInt(wiIdStr);
                excludeMieIds += wiIdStr + ",";
                mdInventoryExtend1 = new MdInventory();
                mdInventoryExtend1.setWiId(wiId);
                mdInventoryExtend1 = (MdInventory) this.getOne(mdInventoryExtend1);

                mdCheckInventoryExEntity = new MdCheckInventoryExEntity();
                mdCheckInventoryExEntity.setWiId(wiId);
                mdCheckInventoryExEntity.setCiId(ciId);
                mdCheckInventoryExEntity = (MdCheckInventoryExEntity) this.getOne(mdCheckInventoryExEntity);
                if (mdCheckInventoryExEntity == null) {
                    mdCheckInventoryExEntity = new MdCheckInventoryExEntity();
                    mdCheckInventoryExEntity.setWiId(wiId);
                    mdCheckInventoryExEntity.setCiId(ciId);
                    saveOrUpdate = true;
                }

                map = new HashMap<>();

                if (nowNumberList.length < idx && (nowNumberList[idx] == null || nowNumberList[idx].equals("") || nowSplitNumberList[idx].equals("-1"))) {
                    if (saveOrUpdate) {
                        mdCheckInventoryExEntity.setBaseNumber(mdInventoryExtend1.getQuantity());
                        mdCheckInventoryExEntity.setSplitNumber(mdInventoryExtend1.getBaseNumber());
                        this.newObject(mdCheckInventoryExEntity);
                    } else {
                        this.updateObject(mdCheckInventoryExEntity);
                    }
                    map.put("wiId", mdCheckInventoryExEntity.getWiId());
                    map.put("cieId", mdCheckInventoryExEntity.getCieId());
                    list.add(map);
                    idx++;
                    continue;
                }
                nowNumber = Integer.parseInt(nowNumberList[idx]);
                mdCheckInventoryExEntity.setNowNumber(nowNumber);

//                mdInventoryExtend1.setBaseNumber(Double.parseDouble(String.valueOf(nowNumber)));
                if (nowSplitNumberList.length > idx && nowSplitNumberList[idx] != null && !nowSplitNumberList[idx].equals("") && !nowSplitNumberList[idx].equals("-1")) {
                    nowSplitNumebr = Integer.parseInt(nowSplitNumberList[idx]);
                    mdCheckInventoryExEntity.setNowSplitNumber(nowSplitNumebr);


//                    mdInventoryExtend1.setQuantity(Double.parseDouble(String.valueOf(nowSplitNumebr)));
                }
                mdCheckInventoryExEntity.setCheckInventory(1);
                mdCheckInventoryExEntity.setIsCheck(1);
                if (saveOrUpdate) {
                    mdCheckInventoryExEntity.setBaseNumber(mdInventoryExtend1.getQuantity());
                    mdCheckInventoryExEntity.setSplitNumber(mdInventoryExtend1.getBaseNumber());
                    this.newObject(mdCheckInventoryExEntity);
//                    this.updateObject(mdInventoryExtend1);
                } else {
                    this.updateObject(mdCheckInventoryExEntity);
//                    this.updateObject(mdInventoryExtend1);
                }
                map.put("wiId", mdCheckInventoryExEntity.getWiId());
                map.put("cieId", mdCheckInventoryExEntity.getCieId());
                list.add(map);
                idx++;

                //增加库存 修改库存
                MdInventory att_MdInventory = new MdInventory();
//                att_MdInventory.setItemKeyId(String.valueOf(mdItemMxView.getItemKeyId()));
                att_MdInventory.setWiId(wiId);
                att_MdInventory = (MdInventory) this.getOne(att_MdInventory);
                Double nn = att_MdInventory.getRatio() * nowNumber;
                nn += nowSplitNumebr;
                nn = nn <= 0 ? 0 : nn;
                att_MdInventory.setBaseNumber(nn);
                att_MdInventory.setQuantity(Math.floor(att_MdInventory.getBaseNumber() / att_MdInventory.getRatio()));
                this.updateObject(att_MdInventory);
                if (nn <= 0) {
                    mdInventoryExtendDao.updateMdInventoryExtendZero(att_MdInventory.getWiId());
                } else {
                    MdInventoryExtend att_MdInventoryExtend = new MdInventoryExtend();
                    att_MdInventoryExtend.setWiId(att_MdInventory.getWiId());
                    List<MdInventoryExtend> list1 = this.getList(att_MdInventoryExtend);
                    Double ss = 0D;
                    for (MdInventoryExtend mdExtend : list1) {
                        Double a = mdExtend.getBaseNumber() + mdExtend.getQuantity() * att_MdInventory.getRatio();
                        ss = nn - a;
                        if (ss <= 0D) {
                            mdExtend.setBaseNumber(nn);
                            mdExtend.setQuantity(Math.floor(nn / att_MdInventory.getRatio()));
                            this.updateObject(mdExtend);
                            break;
                        }
                        nn -= a;
                        mdExtend.setBaseNumber(0D);
                        mdExtend.setQuantity(0D);
                        this.updateObject(mdExtend);
                    }
                }
            }

//            PagerModel pm = mdInventoryExtendDao.getPagerModelMdInventoryExtend(mdInventoryExtend);

            if (updateOrSave != null) {
                sm.setObj(list);
                return sm;
            }
            if (!excludeMieIds.equals(""))
                excludeMieIds = excludeMieIds.substring(0, excludeMieIds.length() - 1);
            mdInventoryExtend.setExcludeWiIds(excludeMieIds);
            saveOtherCheck(mdInventoryExtend, ciId);

            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    private void saveOtherCheck(MdInventoryView mdInventoryExtend, Integer ciId) throws HSKException {
        try {
            // 排除修改过的信息，保存查询但未盘点的数据
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                mdInventoryExtend.setRbaId(account.getOldId());
                mdInventoryExtend.setPurchaseType("1");
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdInventoryExtend.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                mdInventoryExtend.setRbsId(account.getOldId());
                mdInventoryExtend.setPurchaseType("2");
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdInventoryExtend.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    mdInventoryExtend.setRbsId(Integer.parseInt(orgMap.get("tow")));
                }
                mdInventoryExtend.setRbbId(account.getOldId());
                mdInventoryExtend.setPurchaseType("3");
            }

//            PagerModel pm = mdInventoryDao.getPagerModelByMdInventoryView(mdInventoryExtend, null, null, null, null, null, null);
//            List<MdInventoryView> list = mdInventoryDao.getListByMdInventoryView(mdInventoryExtend);
            List<MdInventoryView> mdInventoryExtendViewlist = mdInventoryDao.getListByMdInventoryView(mdInventoryExtend);
            MdInventory mdInventoryExtend1;
            MdCheckInventoryExEntity mdCheckInventoryExEntity;
            for (MdInventoryView mdInventoryExtendView : mdInventoryExtendViewlist) {
                mdInventoryExtend1 = new MdInventory();
                mdInventoryExtend1.setWiId(mdInventoryExtendView.getWiId());
                mdInventoryExtend1 = (MdInventory) this.getOne(mdInventoryExtend1);

                mdCheckInventoryExEntity = new MdCheckInventoryExEntity();
                mdCheckInventoryExEntity.setWiId(mdInventoryExtendView.getWiId());
                mdCheckInventoryExEntity.setCiId(ciId);
                mdCheckInventoryExEntity.setBaseNumber(mdInventoryExtend1.getQuantity());
                mdCheckInventoryExEntity.setSplitNumber(mdInventoryExtend1.getBaseNumber());
                this.newObject(mdCheckInventoryExEntity);
            }
        } catch (Exception e) {
            throw new HSKException();
        }
    }


    @Override
    public SysRetrunMessage saveCheckInventoryMxRemark(MdInventoryExtendView mdInventoryExtend, Integer ciId, String wiIds, String checkRemarks, String cieIds) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
//            String[] cieIdArray = cieIds.split(",");
            String[] remarkArray = checkRemarks.split("#*#");
//            String[] mieIdArray = mieIds.split(",");
            Integer cieId;
            Integer wiId;
            MdCheckInventoryExEntity mdCheckInventoryExEntity = new MdCheckInventoryExEntity();
            mdCheckInventoryExEntity.setCiId(ciId);
            List<MdCheckInventoryExEntity> list = this.getList(mdCheckInventoryExEntity);
            Integer index;
            for (MdCheckInventoryExEntity mx : list) {
                cieId = mx.getCieId();
                wiId = mx.getWiId();
                index = wiIds.indexOf(wiId.toString());
                if (index >= 0 && remarkArray.length > index) {
                    mx.setCheckRemark(remarkArray[index]);
                    this.updateObject(mx);
                }
            }

//            Integer idx = 0;
//            for (String mieIdS : mieIdArray) {
//                if (mieIdS.equals(""))
//                    continue;
//                mieId = Integer.parseInt(mieIdS);
//                mdCheckInventoryExEntity = new MdCheckInventoryExEntity();
//                mdCheckInventoryExEntity.setMieId(mieId);
//                mdCheckInventoryExEntity = (MdCheckInventoryExEntity) this.getOne(mdCheckInventoryExEntity);
//                if (mdCheckInventoryExEntity == null)
//                    continue;
//                mdCheckInventoryExEntity.setCheckRemark(remarkArray[idx]);
//                this.updateObject(mdCheckInventoryExEntity);
//                idx ++;
//            }
//            for (String cieIdS : cieIdArray) {
//                if (cieIdS.equals(""))
//                    continue;
//                cieId = Integer.parseInt(cieIdS);
//                mdCheckInventoryExEntity = new MdCheckInventoryExEntity();
//                mdCheckInventoryExEntity.setCieId(cieId);
//                mdCheckInventoryExEntity.setCheckRemark(remarkArray[idx]);
//                this.updateObject(mdCheckInventoryExEntity);
//                idx ++;
//            }

        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
            throw new HSKException(e);
        }
        return sm;
    }

    @Override
    public PagerModel getPagerModelPA(MdInventoryPriceAdjustmentEntity mdInventoryPriceAdjustmentEntity) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdInventoryPriceAdjustmentEntity>());
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                mdInventoryPriceAdjustmentEntity.setRbaId(account.getOldId());
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdInventoryPriceAdjustmentEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                mdInventoryPriceAdjustmentEntity.setRbsId(account.getOldId());
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdInventoryPriceAdjustmentEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    mdInventoryPriceAdjustmentEntity.setRbsId(Integer.parseInt(orgMap.get("tow")));
                }
                mdInventoryPriceAdjustmentEntity.setRbbId(account.getOldId());
            }
            pm = mdInventoryExtendDao.getPagerModelPA(mdInventoryPriceAdjustmentEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getPagerModelPAEx(MdInventoryPriceAdjustmentEntity mdInventoryPriceAdjustmentEntity, String wmsMiIds, String mmfIds) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList());
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            String purchaseType = ""; // 物料是从供应商开始，所以集团要从2开始
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                mdInventoryPriceAdjustmentEntity.setRbaId(account.getOldId());
                rbaId = account.getOldId();
                purchaseType = "2";
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdInventoryPriceAdjustmentEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                    rbaId = Integer.parseInt(orgMap.get("one"));
                }
                rbsId = account.getOldId();
                mdInventoryPriceAdjustmentEntity.setRbsId(account.getOldId());
                purchaseType = "3";
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdInventoryPriceAdjustmentEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                    rbaId = Integer.parseInt(orgMap.get("one"));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    mdInventoryPriceAdjustmentEntity.setRbsId(Integer.parseInt(orgMap.get("tow")));
                    rbsId = Integer.parseInt(orgMap.get("tow"));
                }
                rbbId = account.getOldId();
                mdInventoryPriceAdjustmentEntity.setRbbId(account.getOldId());
                purchaseType = "4";
            }
            pm = mdInventoryExtendDao.getPagerModelPAEx(mdInventoryPriceAdjustmentEntity);

            List<MdInventoryExtendPaExEntity> list = pm.getItems();
            Map<String, Object> map;
            Double baseNumber = 0d;
            Double quantity = 0d;
            Double ratio = 0d;
            Double avgPrice = 0d;

            String purchaseTypes = (Integer.parseInt(purchaseType) - 1) + "";
            List<Integer> purchaseList = new ArrayList<>();
            String mmfIds1 = "";
            List<Integer> mmfIdList = new ArrayList<>();
            MdMaterielFormatView mdMaterielFormatView;
            for (MdInventoryExtendPaExEntity mdMaterielFormatView1 : list) {
                if (mdMaterielFormatView1.getMmfId() != null)
                    mmfIdList.add(mdMaterielFormatView1.getMmfId());
            }
            if (mmfIdList != null && !mmfIdList.isEmpty()) {
                mmfIds1 = StringUtils.join(mmfIdList.toArray(), ",");
            }

            List<Map<String, Object>> mapList = mdInventoryExtendDao.getMdInventoryBaseNumberAndAvgPriceByList(purchaseTypes, mmfIds1, rbaId, rbsId, rbbId);

            Integer mmfId = null;
            for (Map<String, Object> maps : mapList) {
                if (maps.isEmpty() || maps.get("mmf_id") == null)
                    continue;
                mmfId = Integer.parseInt(maps.get("mmf_id").toString());
                for (MdInventoryExtendPaExEntity mdMaterielFormatView1 : list) {
                    if (java.util.Objects.equals(mdMaterielFormatView1.getMmfId(), mmfId)) {
                        if (maps.get("base_number") != null && !maps.get("base_number").toString().equals("")) {
                            baseNumber = Double.parseDouble(maps.get("base_number").toString());
                            mdMaterielFormatView1.setBaseNumber(baseNumber);
                        }
                        if (maps.get("quantity") != null && !maps.get("quantity").toString().equals("")) {
                            quantity = Double.parseDouble(maps.get("quantity").toString());
                            mdMaterielFormatView1.setQuantity1(quantity);
                        }
                        if (maps.get("ratio") != null && !maps.get("ratio").toString().equals("")) {
                            ratio = Double.parseDouble(maps.get("ratio").toString());
                            mdMaterielFormatView1.setRatio(ratio);
                        }
                        if (maps.get("avg_price") != null && !maps.get("avg_price").toString().equals("")) {
                            avgPrice = Double.parseDouble(maps.get("avg_price").toString());
                            mdMaterielFormatView1.setAvgPrice(avgPrice);
                        } else {
                            mdMaterielFormatView1.setAvgPrice(mdMaterielFormatView1.getPrice());
                        }
                    }
                }
            }

//            MdMaterielFormatView mdMaterielFormatView;
//            for (MdInventoryExtendPaExEntity mdMaterielFormatView1 : list) {
//                mdMaterielFormatView = new MdMaterielFormatView();
//                mdMaterielFormatView.setPurchaseType(purchaseType);
//                mdMaterielFormatView.setMmfId(mdMaterielFormatView1.getMmfId());
//                map = mdInventoryExtendDao.getMdInventoryBaseNumberAndAvgPrice(mdMaterielFormatView, rbaId, rbsId, rbbId);
//                if (map == null || map.isEmpty())
//                    continue;
//                if (map.get("base_number") != null && !map.get("base_number").toString().equals("")) {
//                    baseNumber = Double.parseDouble(map.get("base_number").toString());
//                    mdMaterielFormatView1.setBaseNumber(baseNumber);
//                }
//                if (map.get("avg_price") != null && !map.get("avg_price").toString().equals("")) {
//                    avgPrice = Double.parseDouble(map.get("avg_price").toString());
//                    mdMaterielFormatView1.setAvgPrice1(avgPrice);
//                } else {
//                    mdMaterielFormatView1.setAvgPrice1(mdMaterielFormatView1.getPrice());
//                }
//
//            }
            if (wmsMiIds != null && !wmsMiIds.equals("") && mmfIds != null && !mmfIds.equals("")) {
                List formats = getNewMaterielFormat(wmsMiIds, mmfIds);
                pm.getRows().addAll(formats);
                pm.setTotal(pm.getRows().size());
                pm.setTotalCount(pm.getRows().size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    private List getNewMaterielFormat(String wmsMiIds, String mmfIds) {
        PagerModel pm=new PagerModel(new ArrayList<MdMaterielFormatView>());
        try{
            MdMaterielFormatView mdMaterielFormatView = new MdMaterielFormatView();
            SysUserInfo account = this.GetOneSessionAccount();
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            if (account.getOrganizaType().equals("100")) {//供应商增加

                mdMaterielFormatView.setPurchaseType("1");
            } else if (account.getOrganizaType().equals("20001")) {//集团增加
                rbaId = account.getOldId();
                mdMaterielFormatView.setPurchaseType("2");
            } else if (account.getOrganizaType().equals("20002")) {//医院增加
                rbsId = account.getOldId();
                mdMaterielFormatView.setPurchaseType("3");
            } else if (account.getOrganizaType().equals("20003")) {//门诊增加
                rbbId = account.getOldId();
                mdMaterielFormatView.setPurchaseType("4");
            }
            mdMaterielFormatView.setWzId(account.getOldId());
            mdMaterielFormatView.setState("1");
            mdMaterielFormatView.setWmsMiIds(wmsMiIds);
            mdMaterielFormatView.setMmfIds(mmfIds);
            pm = mdMaterielInfoDao.getPagerModelByMdMdMaterielFormatView(mdMaterielFormatView);

            List<Map<String, Object>> list = pm.getItems();
            Map<String, Object> map;
            Double baseNumber = 0d;
            Double quantity = 0d;
            Double ratio = 0d;
            Double avgPrice = 0d;

            String purchaseTypes = "";
            List<Integer> purchaseList = new ArrayList<>();
            String mmfIds1 = "";
            String wmsMiIds1 = "";
            List<Integer> mmfIdList = new ArrayList<>();
            List<Integer> wmsMiIdList = new ArrayList<>();
            for (Map<String, Object> mdMaterielFormatView1 : list) {
                if (mdMaterielFormatView1.get("purchaseType") != null && !mdMaterielFormatView1.get("purchaseType").equals(""))
                    purchaseList.add(Integer.parseInt(mdMaterielFormatView1.get("purchaseType").toString()) - 1);
                if (mdMaterielFormatView1.get("mmfId") != null && !mdMaterielFormatView1.get("mmfId").toString().equals(""))
                    mmfIdList.add(Integer.parseInt(mdMaterielFormatView1.get("mmfId").toString()));
                if (mdMaterielFormatView1.get("wmsMiId") != null && !mdMaterielFormatView1.get("wmsMiId").toString().equals(""))
                    wmsMiIdList.add(Integer.parseInt(mdMaterielFormatView1.get("wmsMiId").toString()));
            }
            if (purchaseList != null && !purchaseList.isEmpty()) {
                purchaseTypes = StringUtils.join(purchaseList.toArray(), ",");
            }
            if (mmfIdList != null && !mmfIdList.isEmpty()) {
                mmfIds1 = StringUtils.join(mmfIdList.toArray(), ",");
            }
            if (wmsMiIdList != null && !wmsMiIdList.isEmpty())
                wmsMiIds1 = StringUtils.join(wmsMiIdList.toArray(), ",");


            List<Map<String, Object>> fileList = mdMaterielInfoDao.getLessenFilePath(wmsMiIds1, null, null, null, null);
            Integer wmsMiId = null;
            for (Map<String, Object> mdMaterielFormatView1 : list) {
                for (Map<String, Object> maps : fileList) {
                    if (maps.get("wmsMiId") == null && maps.get("wmsMiId").toString().trim().equals("")) {

                    } else {
                        wmsMiId = Integer.parseInt(maps.get("wmsMiId").toString());
                        if (java.util.Objects.equals(mdMaterielFormatView1.get("wmsMiId"), wmsMiId)) {
                            mdMaterielFormatView1.put("lessenFilePath", maps.get("lessenFilePath").toString());
                            break;
                        }
                    }
                }
            }

            List<Map<String, Object>> mapList = mdInventoryExtendDao.getMdInventoryBaseNumberAndAvgPriceByList(purchaseTypes, mmfIds1, rbaId, rbsId, rbbId);

            Integer mmfId = null;
            for (Map<String, Object> mdMaterielFormatView1 : list) {
                mdMaterielFormatView1.put("avgPrice", mdMaterielFormatView1.get("price"));
                for (Map<String, Object> maps : mapList) {
                    if (maps.isEmpty() || maps.get("mmf_id") == null)
                        continue;
                    mmfId = Integer.parseInt(maps.get("mmf_id").toString());

                    if (mdMaterielFormatView1.get("mmfId") != null && !mdMaterielFormatView1.get("mmfId").toString().equals("") && java.util.Objects.equals(Integer.parseInt(mdMaterielFormatView1.get("mmfId").toString()), mmfId)) {
                        if (maps.get("base_number") != null && !maps.get("base_number").toString().equals("")) {
                            baseNumber = Double.parseDouble(maps.get("base_number").toString());
                            mdMaterielFormatView1.put("baseNumber", (baseNumber));
                        }
                        if (maps.get("quantity") != null && !maps.get("quantity").toString().equals("")) {
                            quantity = Double.parseDouble(maps.get("quantity").toString());
                            mdMaterielFormatView1.put("quantity1", quantity);
                        }
                        if (maps.get("ratio") != null && !maps.get("ratio").toString().equals("")) {
                            ratio = Double.parseDouble(maps.get("ratio").toString());
                            mdMaterielFormatView1.put("ratio", ratio);
                        }
                        if (maps.get("avg_price") != null && !maps.get("avg_price").toString().equals("")) {
                            avgPrice = Double.parseDouble(maps.get("avg_price").toString());
                            mdMaterielFormatView1.put("avgPrice", avgPrice);
                        } else {
                            mdMaterielFormatView1.put("avgPrice", mdMaterielFormatView1.get("price"));
                        }
                        if (maps.get("split_quantity_sum") != null && !maps.get("split_quantity_sum").toString().equals("")) {
                            mdMaterielFormatView1.put("splitQuantitySum", Double.parseDouble(maps.get("split_quantity_sum").toString()));
                        }
                        break;
                    }
                }
            }
//            for (MdMaterielFormatView mdMaterielFormatView1 : list) {
//                map = mdInventoryExtendDao.getMdInventoryBaseNumberAndAvgPrice(mdMaterielFormatView1, rbaId, rbsId, rbbId);
//                if (map == null || map.isEmpty())
//                    continue;
//                if (map.get("base_number") != null && !map.get("base_number").toString().equals("")) {
//                    baseNumber = Double.parseDouble(map.get("base_number").toString());
//                    mdMaterielFormatView1.setBaseNumber(baseNumber);
//                }
//                if (map.get("avg_price") != null && !map.get("avg_price").toString().equals("")) {
//                    avgPrice = Double.parseDouble(map.get("avg_price").toString());
//                    mdMaterielFormatView1.setAvgPrice(avgPrice);
//                } else {
//                    mdMaterielFormatView1.setAvgPrice(mdMaterielFormatView1.getPrice());
//                }
//
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm.getItems();
    }

    @Override
    public PagerModel getPagerModelPAWithSave(String mddIds, String mdnIds) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdInventoryPriceAdjustmentEntity>());
        try {
            pm = mdInventoryExtendDao.getPagerModelPAWithSave(mddIds, mdnIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage saveMdInventoryPA(MdInventoryPriceAdjustmentEntity mdInventoryPriceAdjustmentEntity, String mmfIds, String wmsMiIds,
                                              String mieIds, String units, String origionRetailPrices, String retailPrices, String includeWmsMiIds, String includeMmfIds) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            if (mdInventoryPriceAdjustmentEntity.getPaiId() == null) {
                Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
                if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                    mdInventoryPriceAdjustmentEntity.setRbaId(account.getOldId());
                } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                    if (orgMap.containsKey("one")) {//如果存在上级集团
                        mdInventoryPriceAdjustmentEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                    }
                    mdInventoryPriceAdjustmentEntity.setRbsId(account.getOldId());
                } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                    if (orgMap.containsKey("one")) {//如果存在上级集团
                        mdInventoryPriceAdjustmentEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                    }
                    if (orgMap.containsKey("tow")) {//如果存在上级医院
                        mdInventoryPriceAdjustmentEntity.setRbsId(Integer.parseInt(orgMap.get("tow")));
                    }
                    mdInventoryPriceAdjustmentEntity.setRbbId(account.getOldId());
                }


//            String paiCode = CreateCodeUtil.getNo("PAI");
//            mdInventoryPriceAdjustmentEntity.setPaiCode(paiCode);
                String createRen = mdInventoryPriceAdjustmentEntity.getCreateRen();
                this.newObject(mdInventoryPriceAdjustmentEntity);
                mdInventoryPriceAdjustmentEntity.setCreateRen(createRen);
                this.updateObject(mdInventoryPriceAdjustmentEntity);
            }
            Integer paiId = mdInventoryPriceAdjustmentEntity.getPaiId();
            //保存没有修改的数据
            saveIncludeNoAlterData(includeMmfIds, includeWmsMiIds, mdInventoryPriceAdjustmentEntity);
            if (mmfIds == null)
                return sm;
            String[] mmfIdList = mmfIds.split(",");
            String[] wmsMiIdList = wmsMiIds.split(",");
//            String[] mieIdList = mieIds.split(",");
            String[] unitList = units.split(",");
            String[] origionRetailPriceList = origionRetailPrices.split(",");
            String[] retailPriceList = retailPrices.split(",");
            if ((mmfIdList.length - wmsMiIdList.length) == 0 && (mmfIdList.length - unitList.length) == 0 &&
                    (mmfIdList.length - - origionRetailPriceList.length) == 0 && (mmfIdList.length - retailPriceList.length) == 0) {
                sm.setCode(2L);
                sm.setMeg("数据有误");
                return sm;
            }

            Integer mieId;
            Integer mmfId;
            Integer wmsMiId;
            String unit;
            Double basePrice;
            Double retailPrice;
            Double percent;

            Integer idx = 0;
            MdInventoryExtend mdInventoryExtend;
            MdInventoryPriceAdjustmentExEntity mdInventoryPriceAdjustmentExEntity;
            MdMaterielFormat mdMaterielFormat;
            MdMaterielInfo mdMaterielInfo;

            MdMaterielPartDetailLogEntity mdMaterielPartDetailLogEntity;

            for (String mmfIdStr : mmfIdList) {
                mmfId = Integer.parseInt(mmfIdStr);
                wmsMiId = Integer.parseInt(wmsMiIdList[idx]);
                unit = "";
                basePrice = 0d;
                retailPrice = 0d;
                percent = 0d;

//                mdInventoryExtend = new MdInventoryExtend();
//                mieId = Integer.parseInt(mieIdStr);
//                mdInventoryExtend.setMieId(mieId);
//                mdInventoryExtend = (MdInventoryExtend) this.getOne(mdInventoryExtend);
//                if (mdInventoryExtend == null) {
//                    throw new HSKException();
//                }
                mdMaterielFormat = new MdMaterielFormat();
                mdMaterielFormat.setMmfId(mmfId);
                mdMaterielFormat = (MdMaterielFormat) this.getOne(mdMaterielFormat);
                if (mdMaterielFormat == null) {
                    throw new HSKException();
                }
                mdMaterielInfo = new MdMaterielInfo();
                mdMaterielInfo.setWmsMiId(wmsMiId);
                mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
                mdInventoryPriceAdjustmentExEntity = new MdInventoryPriceAdjustmentExEntity();
                mdInventoryPriceAdjustmentExEntity.setPaiId(paiId);
//                mdInventoryPriceAdjustmentExEntity.setMieId(mieId);
                mdInventoryPriceAdjustmentExEntity.setWmsMiId(wmsMiId);
                mdInventoryPriceAdjustmentExEntity.setMmfId(mmfId);
                if (mdMaterielInfo != null)
                    unit = mdMaterielInfo.getBasicUnit();
                else if (unitList[idx] == null || unitList[idx].equals("")) {
                    unit = "";
                } else {
                    unit = unitList[idx];
                }
                mdInventoryPriceAdjustmentExEntity.setBaseUnit(unit);

                if (origionRetailPriceList[idx] == null || origionRetailPriceList[idx].equals("-1")) {
//                    basePrice = mdMaterielPartNormEntity.getBasePrice();
                } else {
                    basePrice = Double.valueOf(origionRetailPriceList[idx]);
//                    mdMaterielPartNormEntity.setBasePrice(basePrice);
                    mdInventoryPriceAdjustmentExEntity.setOrigionRetailPrice(basePrice);
                }
                if (retailPriceList[idx] == null || retailPriceList[idx].equals("-1")) {
//                    retailPeice = mdMaterielPartNormEntity.getBasePrice();
                    percent = 0d;
//                    mdMaterielPartNormEntity.setRetailPrice(retailPeice);
                    mdInventoryPriceAdjustmentExEntity.setRetailPrice(retailPrice);
                    mdInventoryPriceAdjustmentExEntity.setPercent(percent);
                } else {
                    retailPrice = Double.valueOf(retailPriceList[idx]);
//                    mdMaterielPartNormEntity.setRetailPrice(retailPeice);
                    mdInventoryPriceAdjustmentExEntity.setRetailPrice(retailPrice);
                    if (Objects.equal(basePrice, 0d)) {
                        percent = 0d;
                    } else {
                        percent = ((retailPrice - basePrice) / basePrice) * 100;
                    }
                    if (!percent.equals(mdInventoryPriceAdjustmentEntity.getPaiPercent())) {
                        mdInventoryPriceAdjustmentExEntity.setPercent(percent);
                    } else {
                        mdInventoryPriceAdjustmentExEntity.setPercent(mdInventoryPriceAdjustmentEntity.getPaiPercent());
                    }
                }

                this.newObject(mdInventoryPriceAdjustmentExEntity);

//                if (!Objects.equal(mdMaterielFormat.getPrice(), basePrice))
//                    mdMaterielFormat.setPrice(basePrice);
                mdMaterielFormat.setRetailPrice(mdInventoryPriceAdjustmentExEntity.getRetailPrice());
                this.updateObject(mdMaterielFormat);

                mdMaterielPartDetailLogEntity = new MdMaterielPartDetailLogEntity();
                mdMaterielPartDetailLogEntity.setLogName("修改");
                mdMaterielPartDetailLogEntity.setLog("调价");
                mdMaterielPartDetailLogEntity.setWmsMiId(mdMaterielFormat.getWmsMiId());
                this.newObject(mdMaterielPartDetailLogEntity);

                idx ++;
            }

            sm.setObj(mdInventoryPriceAdjustmentEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
            throw new HSKException();
        }
        return sm;
    }

    private void saveIncludeNoAlterData(String includeMmfIds, String includeWmsMiIds, MdInventoryPriceAdjustmentEntity mdInventoryPriceAdjustmentEntity) throws HSKException {
        try {
            if (includeMmfIds == null || includeMmfIds.equals(""))
                return;
            String[] mmfIdList = includeMmfIds.split(",");
            String[] wmsMiIdList = includeWmsMiIds.split(",");
            Integer mmfId;
            Integer wmsMiId;
            Double percent;

            Integer idx = 0;
            MdInventoryExtend mdInventoryExtend;
            MdInventoryPriceAdjustmentExEntity mdInventoryPriceAdjustmentExEntity;
            MdMaterielFormat mdMaterielFormat;
            MdMaterielInfo mdMaterielInfo;

            MdMaterielPartDetailLogEntity mdMaterielPartDetailLogEntity;

            for (String mmfIdStr : mmfIdList) {
                mmfId = Integer.parseInt(mmfIdStr);
                wmsMiId = Integer.parseInt(wmsMiIdList[idx]);
                percent = 0d;

                mdMaterielFormat = new MdMaterielFormat();
                mdMaterielFormat.setMmfId(mmfId);
                mdMaterielFormat = (MdMaterielFormat) this.getOne(mdMaterielFormat);
                if (mdMaterielFormat == null) {
                    throw new HSKException();
                }
                mdMaterielInfo = new MdMaterielInfo();
                mdMaterielInfo.setWmsMiId(wmsMiId);
                mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);

                mdInventoryPriceAdjustmentExEntity = new MdInventoryPriceAdjustmentExEntity();
                mdInventoryPriceAdjustmentExEntity.setPaiId(mdInventoryPriceAdjustmentEntity.getPaiId());
                mdInventoryPriceAdjustmentExEntity.setWmsMiId(wmsMiId);
                mdInventoryPriceAdjustmentExEntity.setMmfId(mmfId);
                mdInventoryPriceAdjustmentExEntity.setBaseUnit(mdMaterielInfo.getBasicUnit() == null ? "" : mdMaterielInfo.getBasicUnit());
                mdInventoryPriceAdjustmentExEntity.setOrigionRetailPrice(mdMaterielFormat.getPrice());
                percent = mdInventoryPriceAdjustmentEntity.getPaiPercent();
                mdInventoryPriceAdjustmentExEntity.setRetailPrice(mdMaterielFormat.getPrice() * (percent / 100));
                mdInventoryPriceAdjustmentExEntity.setPercent(percent);
                this.newObject(mdInventoryPriceAdjustmentExEntity);

                mdMaterielFormat.setRetailPrice(mdInventoryPriceAdjustmentExEntity.getRetailPrice());
                this.updateObject(mdMaterielFormat);

                mdMaterielPartDetailLogEntity = new MdMaterielPartDetailLogEntity();
                mdMaterielPartDetailLogEntity.setLogName("修改");
                mdMaterielPartDetailLogEntity.setLog("调价");
                mdMaterielPartDetailLogEntity.setWmsMiId(mdMaterielFormat.getWmsMiId());
                this.newObject(mdMaterielPartDetailLogEntity);

                idx ++;
            }
        } catch (Exception e) {
            throw new HSKException();
        }
    }

    @Override
    public SysRetrunMessage updateMdInventoryPA(MdInventoryPriceAdjustmentEntity mdInventoryPriceAdjustmentEntity1, String mmfIds, String wmsMiIds, String paieIds, String mieIds, String units, String origionRetailPrices, String retailPrices) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (mdInventoryPriceAdjustmentEntity1.getPaiId() == null) {
            sm.setCode(3L);
            return sm;
        }
        if (paieIds == null || paieIds.equals(""))
            return sm;
        try {
            String[] paieIdList = paieIds.split(",");
            String[] mmfIdList = mmfIds.split(",");
            String[] wmsMiIdList = wmsMiIds.split(",");
//            String[] mieIdList = mieIds.split(",");
            String[] unitList = units == null ? null : units.split(",");
            String[] origionRetailPriceList = origionRetailPrices.split(",");
            String[] retailPriceList = retailPrices.split(",");
            Integer unitLength = 0;
            if (unitList != null)
                unitLength = paieIdList.length;
            if ((paieIdList.length - mmfIdList.length) == 0 && (paieIdList.length - wmsMiIdList.length) == 0 && (paieIdList.length - unitLength) == 0 &&
                    (paieIdList.length - - origionRetailPriceList.length) == 0 && (paieIdList.length - retailPriceList.length) == 0) {
                sm.setCode(2L);
                sm.setMeg("数据有误");
                return sm;
            }

            MdInventoryPriceAdjustmentEntity mdInventoryPriceAdjustmentEntity = new MdInventoryPriceAdjustmentEntity();
            mdInventoryPriceAdjustmentEntity.setPaiId(mdInventoryPriceAdjustmentEntity1.getPaiId());
            mdInventoryPriceAdjustmentEntity = (MdInventoryPriceAdjustmentEntity) this.getOne(mdInventoryPriceAdjustmentEntity);

            mdInventoryPriceAdjustmentEntity.setRemark(mdInventoryPriceAdjustmentEntity1.getRemark());
            mdInventoryPriceAdjustmentEntity.setPaiPercent(mdInventoryPriceAdjustmentEntity1.getPaiPercent());
            mdInventoryPriceAdjustmentEntity.setPaiType(mdInventoryPriceAdjustmentEntity1.getPaiType());
            this.updateObject(mdInventoryPriceAdjustmentEntity);

            Integer paieId;
            Integer mieId;
            String unit;
            Double basePrice;
            Double retailPeice;
            Double percent;

            Integer idx = 0;
            MdInventoryExtend mdInventoryExtend;
            MdMaterielFormat mdMaterielFormat;
            MdInventoryPriceAdjustmentExEntity mdInventoryPriceAdjustmentExEntity;

            MdMaterielPartDetailLogEntity mdMaterielPartDetailLogEntity;
            for (String paieIdStr : paieIdList) {
                if (paieIdStr.equals("")) {
                    continue;
                }
                unit = "";
                basePrice = 0d;
                retailPeice = 0d;
                percent = 0d;

                paieId = Integer.parseInt(paieIdStr);

                mdInventoryPriceAdjustmentExEntity = new MdInventoryPriceAdjustmentExEntity();
                mdInventoryPriceAdjustmentExEntity.setPaieId(paieId);
                mdInventoryPriceAdjustmentExEntity = (MdInventoryPriceAdjustmentExEntity) this.getOne(mdInventoryPriceAdjustmentExEntity);

                if (mdInventoryPriceAdjustmentExEntity == null) {
                    throw new HSKException();
                }

//                mdInventoryExtend = new MdInventoryExtend();
//
//                mieId = Integer.parseInt(mieIdList[idx]);
//                mdInventoryExtend.setMieId(mieId);
//                mdInventoryExtend = (MdInventoryExtend) this.getOne(mdInventoryExtend);
//                if (mdInventoryExtend == null) {
//                    throw new HSKException();
//                }

                mdMaterielFormat = new MdMaterielFormat();
                mdMaterielFormat.setMmfId(mdInventoryPriceAdjustmentExEntity.getMmfId());
                mdMaterielFormat = (MdMaterielFormat) this.getOne(mdMaterielFormat);
                if (mdMaterielFormat == null) {
                    throw new HSKException();
                }

                if (unitList != null) {
                    if (unitList[idx] == null || unitList[idx].equals("")) {
                        unit = "";
                    } else {
                        unit = unitList[idx];
                    }
                    mdInventoryPriceAdjustmentExEntity.setBaseUnit(unit);
                }

                if (origionRetailPriceList[idx] == null || origionRetailPriceList[idx].equals("-1")) {
//                    basePrice = mdMaterielPartNormEntity.getBasePrice();
                } else {
                    basePrice = Double.valueOf(origionRetailPriceList[idx]);
//                    mdMaterielPartNormEntity.setBasePrice(basePrice);
                    mdInventoryPriceAdjustmentExEntity.setOrigionRetailPrice(basePrice);
                }
                if (retailPriceList[idx] == null || retailPriceList[idx].equals("-1")) {
//                    retailPeice = mdMaterielPartNormEntity.getBasePrice();
                    percent = 0d;
//                    mdMaterielPartNormEntity.setRetailPrice(retailPeice);
                    mdInventoryPriceAdjustmentExEntity.setRetailPrice(retailPeice);
                    mdInventoryPriceAdjustmentExEntity.setPercent(percent);
                } else {
                    retailPeice = Double.valueOf(retailPriceList[idx]);
//                    mdMaterielPartNormEntity.setRetailPrice(retailPeice);
                    mdInventoryPriceAdjustmentExEntity.setRetailPrice(retailPeice);
                    if (Objects.equal(basePrice, 0d)) {
                        percent = 0d;
                    } else {
                        percent = ((retailPeice - basePrice) / basePrice) * 100;
                    }
                    if (!percent.equals(mdInventoryPriceAdjustmentEntity.getPaiPercent())) {
                        mdInventoryPriceAdjustmentExEntity.setPercent(percent);
                    } else {
                        mdInventoryPriceAdjustmentExEntity.setPercent(mdInventoryPriceAdjustmentEntity.getPaiPercent());
                    }
                }

                this.updateObject(mdInventoryPriceAdjustmentExEntity);

//                if (!Objects.equal(mdMaterielFormat.getPrice(), basePrice))
//                    mdMaterielFormat.setPrice(basePrice);
                mdMaterielFormat.setRetailPrice(mdInventoryPriceAdjustmentExEntity.getRetailPrice());
                this.updateObject(mdMaterielFormat);

                mdMaterielPartDetailLogEntity = new MdMaterielPartDetailLogEntity();
                mdMaterielPartDetailLogEntity.setLogName("修改");
                mdMaterielPartDetailLogEntity.setLog("调价");
                mdMaterielPartDetailLogEntity.setWmsMiId(mdMaterielFormat.getWmsMiId());
                this.newObject(mdMaterielPartDetailLogEntity);
            }
            sm.setObj(mdInventoryPriceAdjustmentEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
            throw new HSKException();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteMdInventoryPA(String paiIds) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (paiIds == null || paiIds.equals("")) {
            sm.setCode(2L);
            return sm;
        }
        try {
            mdInventoryDao.deleteMdInventoryPa(paiIds);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteMdInventoryPaEx(String paieIds) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (paieIds == null || paieIds.equals("")) {
            sm.setCode(2L);
            return sm;
        }
        try {
            mdInventoryDao.deleteMdInventoryPaEx(paieIds);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage findPriceAjustmentFormObject(Integer paiId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            MdInventoryPriceAdjustmentEntity mdInventoryPriceAdjustmentEntity = new MdInventoryPriceAdjustmentEntity();
            mdInventoryPriceAdjustmentEntity.setPaiId(paiId);
            mdInventoryPriceAdjustmentEntity = (MdInventoryPriceAdjustmentEntity) this.getOne(mdInventoryPriceAdjustmentEntity);
            sm.setObj(mdInventoryPriceAdjustmentEntity);
        }catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public PagerModel getPagerModelObject1(MdInventoryView mdInventoryExtend) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdInventoryExtendView>());
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                mdInventoryExtend.setRbaId(account.getOldId());
                mdInventoryExtend.setPurchaseType("1");
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdInventoryExtend.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                mdInventoryExtend.setRbsId(account.getOldId());
                mdInventoryExtend.setPurchaseType("2");
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    mdInventoryExtend.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    mdInventoryExtend.setRbsId(Integer.parseInt(orgMap.get("tow")));
                }
                mdInventoryExtend.setRbbId(account.getOldId());
                mdInventoryExtend.setPurchaseType("3");
            }
//            pm = mdInventoryExtendDao.getPagerModelMdInventoryExtend(mdInventoryExtend);
            pm = mdInventoryDao.getPagerModelByMdInventoryView(mdInventoryExtend, null, null, null, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getMdInventoryLogMxListByWiId(MdInventoryEnOutLogViewEntity mdInventoryEnOutLogViewEntity) throws HSKException {
        PagerModel pm=new PagerModel(new ArrayList<>());
        try{
            SysUserInfo account = this.GetOneSessionAccount();
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            String purchaseType = null;
            if(account.getOrganizaType().equals("20001")){
                rbaId = account.getOldId();
                purchaseType = "2";
            }else if(account.getOrganizaType().equals("20002")){
                rbsId = account.getOldId();
                purchaseType = "3";
            }else if(account.getOrganizaType().equals("20003")){
                rbbId = account.getOldId();
                purchaseType = "4";
            }
            pm = mdInventoryExtendDao.getMdInventoryLogMxList(mdInventoryEnOutLogViewEntity, rbaId, rbsId, rbbId, purchaseType);

            Map<String, Object> otherMap = new HashMap<>();
            List<Map<String, Object>> list = pm.getItems();
            for (Map<String, Object> mdInventoryEnOutLogViewEntity1 : list) {
//                if (Objects.equal(mdInventoryEnOutLogViewEntity1.get("stype").toString(), "1"))
//                    continue;
                otherMap = mdInventoryDao.getOhterInfo(Integer.parseInt(mdInventoryEnOutLogViewEntity1.get("wiId").toString()));
                mdInventoryEnOutLogViewEntity1.put("backPrinting", otherMap.get("backPrinting").toString());
                mdInventoryEnOutLogViewEntity1.put("batchCode1", otherMap.get("batchCode").toString());
                mdInventoryEnOutLogViewEntity1.put("productValitime", otherMap.get("valiedDate").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getMdInventoryEnOutLogMxListByWiId(MdInventoryEnOutLogViewEntity mdInventoryEnOutLogViewEntity) throws HSKException {
        PagerModel pm=new PagerModel(new ArrayList<MdInventoryEnOutLogViewEntity>());
        try{
            SysUserInfo account = this.GetOneSessionAccount();
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            String purchaseType = null;
            if(account.getOrganizaType().equals("20001")){
                rbaId = account.getOldId();
                purchaseType = "2";
            }else if(account.getOrganizaType().equals("20002")){
                rbsId = account.getOldId();
                purchaseType = "3";
            }else if(account.getOrganizaType().equals("20003")){
                rbbId = account.getOldId();
                purchaseType = "4";
            }
            pm = mdInventoryExtendDao.getMdInventoryEnOutLogMxList(mdInventoryEnOutLogViewEntity, rbaId, rbsId, rbbId, purchaseType);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getPagerMergeInventoryLog(MdInventoryMergeLogEntity mdInventoryMergeLogEntity) throws HSKException {
        PagerModel pm=new PagerModel(new ArrayList<MdInventoryMergeLogEntity>());
        try{
            SysUserInfo account = this.GetOneSessionAccount();
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            String purchaseType = null;
            if(account.getOrganizaType().equals("20001")){
                rbaId = account.getOldId();
                purchaseType = "2";
            }else if(account.getOrganizaType().equals("20002")){
                rbsId = account.getOldId();
                purchaseType = "3";
            }else if(account.getOrganizaType().equals("20003")){
                rbbId = account.getOldId();
                purchaseType = "4";
            }

            pm = mdInventoryExtendDao.getMdInventoryMergeLog(mdInventoryMergeLogEntity);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage getBrandDistinct(String checkPart, String checkParts) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            String purchaseType = null;
            if(account.getOrganizaType().equals("20001")){
                rbaId = account.getOldId();
                purchaseType = "1";
            }else if(account.getOrganizaType().equals("20002")){
                rbsId = account.getOldId();
                purchaseType = "2";
            }else if(account.getOrganizaType().equals("20003")){
                rbbId = account.getOldId();
                purchaseType = "3";
            }
            List<Map<String, Object>> list = mdInventoryExtendDao.getBrandDistinct(rbaId, rbsId, rbbId, purchaseType, checkPart, checkParts);
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getCheckBrandDistinct(Integer ciId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            String purchaseType = null;
            if(account.getOrganizaType().equals("20001")){
                rbaId = account.getOldId();
                purchaseType = "1";
            }else if(account.getOrganizaType().equals("20002")){
                rbsId = account.getOldId();
                purchaseType = "2";
            }else if(account.getOrganizaType().equals("20003")){
                rbbId = account.getOldId();
                purchaseType = "3";
            }
            List<Map<String, Object>> list = mdInventoryExtendDao.getCheckBrandDistinct(ciId, rbaId, rbsId, rbbId, purchaseType);
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public PagerModel getInventoryMxList(String wiIds, String str, Integer wowId) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList());
        try {
            String searchName1 = "";
            String searchName2 = "";
            if (str != null) {
                String[] arary = str.split(",");
                searchName1 = arary[0];
                if (arary.length > 1)
                    searchName2 = arary[1];
            }
            SysUserInfo account = this.GetOneSessionAccount();
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
//            itemKeyIds = itemKeyIds.length() > 0 ? itemKeyIds.substring(0, itemKeyIds.length() - 1) : null;
//            mmfIds = mmfIds.length() > 0 ? mmfIds.substring(0, mmfIds.length() - 1) : null;
//            att_MdInventoryView.setItemKeyIdStr(itemKeyIds);
//            att_MdInventoryExtendView.setMmfIdsStr(mmfIds);
            att_MdInventoryView.setWiIds(wiIds);
            if (!searchName1.equals(""))
                att_MdInventoryView.setSearchMatName(searchName1);
            if (!searchName2.equals(""))
                att_MdInventoryView.setSearchMmfName(searchName2);
            att_MdInventoryExtendView.setWiIds(wiIds);
            //一次性取出符合条件的数据
            pm = mdInventoryDao.findMdInventoryViewPagerModelByMdInventoryView(att_MdInventoryView);
            MdOutWarehouseMx mdOutWarehouseMx = new MdOutWarehouseMx();
            List<MdOutWarehouseMx> mxList = null;
            if (wowId != null) {
                mdOutWarehouseMx.setWowId(wowId);
                mxList = this.getList(mdOutWarehouseMx);
            }
//            pm = mdInventoryDao.findMdInventoryViewPagerModelByMdInventoryView(att_MdInventoryExtendView);
            List<MdInventoryView> mdInventoryExtendViewList = pm.getItems();
            Map<String, Object> otherMap = new HashMap<>();
            for (MdInventoryView mdInventoryExtendView : mdInventoryExtendViewList) {
//                mdInventoryExtendView.setLogoPath(mdMaterielInfoDao.getLogoPath(mdInventoryExtendView.getWmsMiId(), null));

                if (mdInventoryExtendView != null && mdInventoryExtendView.getWiId() != null) {
                    otherMap = mdInventoryDao.getOhterInfo(att_MdInventoryView.getWiId());
                    mdInventoryExtendView.setBackPrinting(otherMap.get("backPrinting").toString());
                    mdInventoryExtendView.setBatchCode(otherMap.get("batchCode").toString());
                    mdInventoryExtendView.setValiedDate1(otherMap.get("valiedDate").toString());
                    if (mxList != null){
                        for (MdOutWarehouseMx md : mxList) {
                            if (Objects.equal(md.getWiId(), mdInventoryExtendView.getWiId())) {
                                mdInventoryExtendView.setWowMxId(md.getWowMxId());
                                mdInventoryExtendView.setBaseNumber1(md.getBaseNumber());
                                mdInventoryExtendView.setSplitNumber1(md.getSplitQuantity());
                            }
                        }
                    }
//                    mx.setWiId(mdInventoryExtendView.getWiId());
//                    mdInventoryExtendView.setQuantity(mdInventoryExtendView.getQuantity());
//                    mdInventoryExtendView.setSplitQuantity(mdInventoryExtendView.getBaseNumber());
//                    mx.setWmsMiId(mdInventoryExtendView.getWmsMiId());
                    break;
                } else {
//                    mdInventoryExtendView.setQuantity(0d);
//                    mdInventoryExtendView.setSplitQuantity(0d);
                }
            }
//            mx.setQuantity(mdInventoryView.getQuantity());
//            mx.setSplitQuantity(mdInventoryView.getBaseNumber());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage getCheckCount() throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            String purchaseType = null;
            if(account.getOrganizaType().equals("20001")){
                rbaId = account.getOldId();
                purchaseType = "1";
            }else if(account.getOrganizaType().equals("20002")){
                rbsId = account.getOldId();
                purchaseType = "2";
            }else if(account.getOrganizaType().equals("20003")){
                rbbId = account.getOldId();
                purchaseType = "3";
            }
            Integer allCount = mdInventoryDao.getCheckCount(rbaId, rbsId, rbbId, purchaseType, null);
            Integer monthCount = mdInventoryDao.getCheckCount(rbaId, rbsId, rbbId, purchaseType, 1);
            Integer seasonCount = mdInventoryDao.getCheckCount(rbaId, rbsId, rbbId, purchaseType, 2);
            Integer yearCount = mdInventoryDao.getCheckCount(rbaId, rbsId, rbbId, purchaseType, 3);
            Integer everyCount = mdInventoryDao.getCheckCount(rbaId, rbsId, rbbId, purchaseType, 4);
            Map<String, Integer> map = new HashMap<>();
            map.put("allCount", allCount);
            map.put("monthCount", monthCount);
            map.put("seasonCout", seasonCount);
            map.put("yearCount", yearCount);
            map.put("everyCount", everyCount);
            sm.setObj(map);

        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getCheckNoCheckCount(Integer ciId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            Integer allCount = mdInventoryDao.getCheckIsCheckCount(ciId, null);
             Integer checkCount = mdInventoryDao.getCheckIsCheckCount(ciId, 1);
             Integer noCheckCount = mdInventoryDao.getCheckIsCheckCount(ciId, 2);
             Map<String, Integer> map = new HashMap<>();
            map.put("allCount", allCount);
            map.put("checkCount", checkCount);
            map.put("noCheckCount", noCheckCount);
            sm.setObj(map);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteCheck(Integer ciId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            String purchaseType = null;
            if(account.getOrganizaType().equals("20001")){
                rbaId = account.getOldId();
                purchaseType = "1";
            }else if(account.getOrganizaType().equals("20002")){
                rbsId = account.getOldId();
                purchaseType = "2";
            }else if(account.getOrganizaType().equals("20003")){
                rbbId = account.getOldId();
                purchaseType = "3";
            }
            mdInventoryDao.deleteCheckMx(ciId, rbaId, rbsId, rbbId);
        } catch (Exception e) {
            e.printStackTrace();
            sm.setCode(0L);
        }
        return sm;
    }
}