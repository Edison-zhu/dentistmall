package com.hsk.xframe.web.sysSameProduct.service.imp;

import com.hsk.dentistmall.api.daobbase.IMdMaterielFormatDao;
import com.hsk.dentistmall.api.daobbase.IMdMaterielInfoDao;
import com.hsk.dentistmall.api.persistence.MdMaterielFormat;
import com.hsk.dentistmall.api.persistence.MdMaterielInfo;
import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.ISysSameProductDao;
import com.hsk.xframe.api.daobbase.imp.SysSameProductDao;
import com.hsk.xframe.api.persistence.SysSameProductEntity;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.xframe.web.sysSameProduct.service.ISysSameProductService;
import com.hsk.xframe.web.sysparam.service.ISysParameterService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author: yangfeng
 * time: 2020/3/24 15:02
 */
@Service
public class SysSameProductService extends DSTService implements ISysSameProductService {
    @Autowired
    ISysSameProductDao sysSameProductDao;
    @Autowired
    ISysParameterService sysParameterService;
    @Autowired
    protected IMdMaterielFormatDao mdMaterielFormatDao;
    @Autowired
    protected IMdMaterielInfoDao mdMaterielInfoDao;

    @Override
    public SysRetrunMessage saveSameProduct(SysSameProductEntity sysSameProductEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            if (sysSameProductEntity == null) {
                sm.setCode(2l);
                sm.setMeg("请上传数据！");
                return sm;
            }
            String sameProduct = CreateCodeUtil.getNo("TKSP");
            sysSameProductEntity.setSysSameProductCode(sameProduct);
            sysSameProductDao.saveSameProduct(sysSameProductEntity);
        } catch (Exception e) {
            sm.setCode(2l);
            sm.setMeg("操作出错！");
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveSameProductByWmsMiIds(SysSameProductEntity sysSameProductEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            if (sysSameProductEntity.getWmsMiId() == null && sysSameProductEntity.getWmsMiId().equals("")) {
                sm.setCode(2l);
                sm.setMeg("请上传数据！");
                return sm;
            }
            if (sysSameProductEntity.getSysSameProductCode() == null || sysSameProductEntity.getSysSameProductCode().equals("")) {
                sysSameProductEntity.setCreateDate(new Date());
                sysSameProductEntity.setEditDate(new Date());

                String sameProductCode = CreateCodeUtil.getNo("TKSP");
                sysSameProductEntity.setSysSameProductCode(sameProductCode);

                Integer count = sysSameProductDao.getProductByState(sysSameProductEntity.getWmsMiId(), 2);
                if (count >= 1) {
                    sysSameProductEntity.setSpState(2);
                } else {
                    sysSameProductEntity.setSpState(1);
                }

                sysSameProductDao.addSameProduct(sysSameProductEntity);
            } else {
                SysSameProductEntity entity = new SysSameProductEntity();
                entity.setSysSameProductCode(sysSameProductEntity.getSysSameProductCode());
                entity = sysSameProductDao.getObjectBySPIdAndWmsMiId(entity);
                entity.setEditDate(new Date());
                if (entity.getWmsMiId() == null || entity.getWmsMiId().equals("")) {
                    entity.setWmsMiId(sysSameProductEntity.getWmsMiId());
                } else {
                    entity.setWmsMiId(sysSameProductEntity.getWmsMiId() + "," + entity.getWmsMiId());
                }

                Integer count = sysSameProductDao.getProductByState(entity.getWmsMiId(), 2);

                String[] wmsMiIdList = entity.getWmsMiId().split(",");
                Integer sameCount = wmsMiIdList.length;

                Integer cha = sameCount - count;

                if (cha <= 1 || entity.getWmsMiId().indexOf(",") < 0) {
                    entity.setSpState(2);
                } else if (cha >= 1 && entity.getWmsMiId().indexOf(",") >= 0) {
                    entity.setSpState(1);
                } else {
                    entity.setSpState(1);
                }
                entity.setSysSameProductCode(sysSameProductEntity.getSysSameProductCode());
                sysSameProductDao.updateSysSameProductBySPId(entity);
            }
        } catch (Exception e) {
            sm.setCode(2l);
            sm.setMeg("操作出错！");
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public PagerModel getPagerModelObject(SysSameProductEntity sysSameProductEntity) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<SysSameProductEntity>());
        try {
            pm = sysSameProductDao.getPagerModelObject(sysSameProductEntity);
            List<SysSameProductEntity> list = pm.getItems();
            List<Map<String, Object>> mapList = null;
            Integer count = 0;
            List<SysSameProductEntity> addList = new ArrayList<SysSameProductEntity>();
            List<Map<String, Object>> sorMap;
            String wmsMiIdString = "";
            String[] wmsMiIdList;
            for (SysSameProductEntity entity : list) {
                if (entity.getWmsMiId() != null && !entity.getWmsMiId().equals("")) {
//                    if (wmsMiIdString.indexOf(sysSameProductEntity.getSysSameProductCode()) >= 0 ) {//只需要需要查询此wmsmiid
//                        mapList = sysSameProductDao.getSameProductProductOnlyWmsMiIdList(entity.getId(), entity.getSysSameProductCode(), entity.getWmsMiId());
//                        count = sysSameProductDao.countSameProductsOnlyWmsMiId(entity.getId(), entity.getSysSameProductCode(), entity.getWmsMiId());
//                    }
//                    else { //需要查询商品名包含
//                        mapList = sysSameProductDao.getSameProductProductOnlyMatNameList(entity.getId(), entity.getSysSameProductCode(), entity.getWmsMiId());
//                        count = sysSameProductDao.countSameProductsOnlyMatName(entity.getId(), entity.getSysSameProductCode(), entity.getWmsMiId());
//                    }
//                    if (mapList.size() <= 0){
                    mapList = sysSameProductDao.getSameProductProductList(entity.getSysSameProductCode(), entity.getWmsMiId());
                    wmsMiIdList = entity.getWmsMiId().split(",");
                    sorMap = new ArrayList<Map<String, Object>>();
                    for (String wmsMiId : wmsMiIdList) {
                        if (sorMap.size() >= 3) {
                            break;
                        }
                        for (Map<String, Object> map : mapList) {
                            if (map.get("wms_mi_id").toString().equals(wmsMiId)) {
                                sorMap.add(map);
                            }
                        }
                    }

                    count = sysSameProductDao.countSameProducts(entity.getSysSameProductCode());
//                    }
                    entity.setMaterielList(sorMap);
                }
                entity.setCount(count);
                addList.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage getObjectBySPId(String sameProductCode) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            List<SysSameProductEntity> list = sysSameProductDao.getObjectBySPId(sameProductCode);
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(2l);
            sm.setMeg("操作出错");
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getObjectBySPIdAndWmsMiId(SysSameProductEntity sysSameProductEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            if (sysSameProductEntity.getSysSameProductCode() == null || sysSameProductEntity.getSysSameProductCode().equals("")) {
                if (sysSameProductEntity.getWmsMiIdString() != null && !sysSameProductEntity.getWmsMiIdString().equals("")) {
                    mapList = sysSameProductDao.getMaterielInfoByWmsMiId(sysSameProductEntity);
                    if (mapList.size() <= 0) {
                        sm.setCode(3l); //不存在
                    } else {
                        sysSameProductEntity.setMaterielList(mapList);
                    }
                } else {
                    sm.setCode(4l); //有错误
                }
                sm.setObj(sysSameProductEntity);
                return sm;
            }
            SysSameProductEntity sysSameProductEntity1 = sysSameProductDao.getObjectBySPIdAndWmsMiId(sysSameProductEntity);
            if (sysSameProductEntity1 == null) {
                sm.setCode(1l);
            }
            String wmsmiIds = sysSameProductEntity1.getWmsMiId();
            String[] wmsmiIdList = wmsmiIds.split(",");
            for (String wmsMiId : wmsmiIdList){
                if (wmsMiId.equals(sysSameProductEntity.getWmsMiIdString())){
                    sm.setCode(2l); //已存在
                }
            }

            if (sysSameProductEntity.getWmsMiIdString() != null && !sysSameProductEntity.getWmsMiIdString().equals("")) {
                mapList = sysSameProductDao.getMaterielInfoByWmsMiId(sysSameProductEntity);
                sysSameProductEntity.setMaterielList(mapList);
            }
            sm.setObj(sysSameProductEntity);
        } catch (Exception e) {
            sm.setCode(5l);
            sm.setMeg("操作出错");
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public PagerModel getSameProductViewPagerModel(SysSameProductEntity sysSameProductEntity) throws HSKException {
        PagerModel pm = new PagerModel();
        try {
            pm = sysSameProductDao.getSameProductViewPagerModel(sysSameProductEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getSameProductProducts(SysSameProductEntity sysSameProductEntity) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<Map<String, Object>>());
        try {
//            pm = sysSameProductDao.getSameProductProducts(sysSameProductEntity);
            pm = sysSameProductDao.getSameProductMoreInfo(sysSameProductEntity);
            List<Map<String, Object>> mapList = pm.getItems();

            Integer wmsMiId = 0;
            List<MdMaterielFormat> list = null;
            MdMaterielFormat att_MdMaterielFormat = new MdMaterielFormat();
            Map<String, Object> mmfMap = null;
            for (Map<String, Object> matMaps : mapList) {
                wmsMiId = Integer.valueOf(matMaps.get("wms_mi_id").toString());
                att_MdMaterielFormat.setWmsMiId(wmsMiId);
                att_MdMaterielFormat.setState("1");
                list = mdMaterielFormatDao.getListByMdMaterielFormat(att_MdMaterielFormat);
                for (MdMaterielFormat mdMaterielFormat : list) {
                    String mmfName = mdMaterielFormat.getMmfName();
                    if (mmfName == null) {
                        continue;
                    }
                    mmfName = mmfName.replace("\"", "&quot;");
                    mmfName = mmfName.replace("'", "&apos;");
                    mdMaterielFormat.setMmfName(mmfName);
                }
//                mmfMap = new HashMap<String, Object>();
                matMaps.put("mmf", list);
//                mapList.add(mmfMap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public void updateSysSameProductBySPId(SysSameProductEntity sysSameProductEntity) throws HSKException {
        try {
            sysSameProductDao.updateSysSameProductBySPId(sysSameProductEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SysRetrunMessage deleteObject(String sameProductCode, String wmsMiId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            SysSameProductEntity sysSameProductEntity = new SysSameProductEntity();
            sysSameProductEntity.setSysSameProductCode(sameProductCode);
            sysSameProductEntity = sysSameProductDao.getObjectBySPIdAndWmsMiId(sysSameProductEntity);
            if (sysSameProductEntity != null) {
                String wmsMiIdOld = sysSameProductEntity.getWmsMiId();
                String wmsMiIdNew = "";
                if (wmsMiIdOld.indexOf(wmsMiId + ",") >= 0) {
                    wmsMiIdNew = wmsMiIdOld.replace(wmsMiId + ",", "");
                } else if (wmsMiIdOld.indexOf("," + wmsMiId) >= 0) {
                    wmsMiIdNew = wmsMiIdOld.replace("," + wmsMiId, "");
                } else {
                    wmsMiIdNew = wmsMiIdOld.replace(wmsMiId, "");
                }


                if (wmsMiIdNew.equals("")) {
                    sysSameProductDao.deleteAllObject(sameProductCode);
                    sm.setCode(3l);
                    return sm;
                }

                Integer count = sysSameProductDao.countSameProductsOnlyWmsMiId(sysSameProductEntity.getId(), sysSameProductEntity.getSysSameProductCode(), wmsMiIdNew);
                if(count <= 0){
                    sysSameProductDao.deleteAllObject(sameProductCode);
                    sm.setCode(3l);
                    return sm;
                }
                sysSameProductEntity.setWmsMiId(wmsMiIdNew);

                count = sysSameProductDao.getProductByState(wmsMiIdNew, 2);
                String[] wmsMiIdList = wmsMiIdNew.split(",");
                Integer sameCount = wmsMiIdList.length;

                Integer cha = sameCount - count;

                if (cha <= 1 || wmsMiIdNew.indexOf(",") < 0) {
                    sysSameProductEntity.setSpState(2);
                } else if (cha >= 1 && wmsMiIdNew.indexOf(",") >= 0) {
                    sysSameProductEntity.setSpState(1);
                } else {
                    sysSameProductEntity.setSpState(1);
                }

                sysSameProductDao.updateSysSameProductBySPId(sysSameProductEntity);


            }
        } catch (Exception e) {
            sm.setCode(2l);
            sm.setMeg("操作出错");
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteAllObject(String sameProductCode) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            sysSameProductDao.deleteAllObject(sameProductCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage countSameProducts(String sameProductCode) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            Integer count = sysSameProductDao.countSameProducts(sameProductCode);
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("count", count);
            sm.setObj(map);
        } catch (Exception e) {
            sm.setCode(2l);
            sm.setMeg("操作错误");
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getAllPagerModelObjectDistinct(SysSameProductEntity sysSameProductEntity, String distinctName) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1l);
        try {
            sm.setObj(sysSameProductDao.getAllPagerModelObjectDistinct(sysSameProductEntity, distinctName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sm;
    }
    //导出同款商品
    public SysRetrunMessage exportSameCommodity(String sameProductCode) throws HSKException{
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        DecimalFormat df = new DecimalFormat("######0.00");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
        SysUserInfo sysUserInfo=this.GetOneSessionAccount();
        try {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            List<Map<String,Object>> countList=sysSameProductDao.countMatnameState(sameProductCode);
            String wmsMiId = "";
            if (countList != null && countList.size() > 0) {
                for (Map<String, Object> map : countList) {
                    Object matNameCount1=map.get("matNameCount");
                    String matNameCount=String.valueOf(matNameCount1);
                    dataMap.put("matNameCount",matNameCount!="null" ? matNameCount:"0");

                    Object state1C=map.get("state1");
                    String state1=String.valueOf(state1C);
                    dataMap.put("state1",state1!="null" ? state1:"0");

                    Object state2C=map.get("state2");
                    String state2=String.valueOf(state2C);
                    dataMap.put("state2",state2!="null" ? state2:"0");

                    Object state0C=map.get("state0");
                    String state0=String.valueOf(state0C);
                    dataMap.put("state0",state0!="null" ? state0:"0");


                    Object wmsMiId1=map.get("wmsMiId");
                    wmsMiId=String.valueOf(wmsMiId1);
                    String[] split = wmsMiId.split(",");
                    if (split != null && split.length != 0) {
                        wmsMiId = split[split.length - 1];
                    }

                }

            }
            dataMap.put("userName",sysUserInfo.getUserName());
            Date date=new Date();
            dataMap.put("newDate",sdf.format(date));
            List<Map<String,Object>> list=sysSameProductDao.exportSameCommodity(sameProductCode);
            List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
            int index = 0;
            Double countMatName=0.0;
            Double countState1=0.0;
            Double countState2=0.0;
            Double countState0=0.0;
            String name="";

            if (list != null && list.size() > 0) {
                for (Map<String, Object> map : list) {
                    index++;
                    Map<String, String> reMap = new HashMap<String, String>();
                    reMap.put("index",index+"");
                    //商品ID
                    reMap.put("mmfId", map.get("mmfId").toString());
                    //商品名称
                    Object mat_name1=map.get("matName");
                    String mat_name=String.valueOf(mat_name1);
                    reMap.put("matName", mat_name!=null ? mat_name:"");
//                        name=mat_name;
                    //商品型号
                    Object mmfName1=map.get("mmfName");
                    String mmfName=String.valueOf(mmfName1);
                    if (map.get("mmfId").toString().equals(wmsMiId)){
                        name = mat_name;
                    }
                    reMap.put("mmfName", mmfName!=null ? mmfName:"");

                    //商品供应商
                    Object applicantName1=map.get("applicantName");
                    String applicantName=String.valueOf(applicantName1);
                    reMap.put("applicantName", applicantName!=null ? applicantName:"");
                    //价格范围
                    reMap.put("price",df.format(map.get("price")!=null ? map.get("price"):0d));
                    //商品状态
                    String state=map.get("state").toString();
                    if (state.equals("1")){
                        reMap.put("state","上架");
                    }else if (state.equals("2")){
                        reMap.put("state","下架");
                    }else{
                        reMap.put("state"," ");
                    }
                    reList.add(reMap);
                }
            }
            dataMap.put("sameProductCode",sameProductCode);
            dataMap.put("name",name);
            dataMap.put("mxList", reList);
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
            // 这里我们的模板是放在org.cnzjw.template包下面
            configuration.setClassForTemplateLoading(this.getClass(),
                    "/ftl");
            Template t = configuration.getTemplate("exportSameCommodity.ftl");
            // 输出文档路径及名称
            Calendar now = Calendar.getInstance();
            long lo = now.getTimeInMillis();
//            Date date1 = new Date(lo);
//            SimpleDateFormat sd = new SimpleDateFormat("hhmmss");
//            String l =sd.format(date1);
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String realPath = tmpPath + "TKSP"+sameProductCode+ ".xls";
            File outFile = new File(realPath);
            Writer out = null;
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile), "UTF-8"));
            t.process(dataMap, out);
            out.close();
            String rootUrl = request.getContextPath() + EXPORT_PATH + "TKSP"+sameProductCode+ ".xls";
            Map<String, String> map = new HashMap<String, String>();
            map.put("path", rootUrl);
            map.put("fileName", "TKSP"+sameProductCode + ".xls");
            srm.setObj(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srm;
    }

}
