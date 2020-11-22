package com.hsk.dentistmall.web.materiel.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.abel533.echarts.style.AreaSelectStyle;
import com.hsk.dentistmall.api.daobbase.IMdModelFormatDao;
import com.hsk.dentistmall.api.persistence.MdModelFormatAttrInfoEntity;
import com.hsk.dentistmall.api.persistence.MdModelFormatEntity;
import com.hsk.dentistmall.api.persistence.MdModelMaterielEntity;
import com.hsk.dentistmall.api.persistence.MdModelOperateLogEntity;
import com.hsk.dentistmall.web.materiel.service.IMdModelMaterielFormatService;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.ILogDao;
import com.hsk.xframe.api.persistence.SysFileFolderInfoEntity;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import org.jbpm.pvm.internal.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * author: yangfeng
 * time: 2020/8/3 14:03
 */
@Service
public class MdModelMaterielFormatService extends DSTService implements IMdModelMaterielFormatService {
    @Autowired
    IMdModelFormatDao mdModelFormatDao;
    @Autowired
    ILogDao logDao;

    @Override
    public List<MdModelFormatEntity> getMdModelMaterielFormatList(MdModelFormatEntity mdModelFormatEntity) throws HSKException {
        List<MdModelFormatEntity> list = new ArrayList<>();
        if (mdModelFormatEntity.getWmsModelId() == null) {
            return list; // 如果没有传入商品模型id，则返回
        }
        try {
            list = mdModelFormatDao.getMdModelFormatList(mdModelFormatEntity);
            String mmfIds = "";
            for (MdModelFormatEntity entity : list) {
                mmfIds += entity.getModelMmfId() + ",";
            }
            if (!mmfIds.equals(""))
                mmfIds = mmfIds.substring(0, mmfIds.length() - 1);
            List<Map<String, Object>> maps = mdModelFormatDao.getMdModelFormatAttrInfoCanSearchList(mmfIds);
            for (Map<String, Object> map : maps) {
                for (MdModelFormatEntity entity : list) {
                    if (Objects.equals(Integer.parseInt(map.get("mmfId").toString()), entity.getModelMmfId())) {
                        entity.setCanSearch(map.get("canSearch") == null ? "0" : map.get("canSearch").toString());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public SysRetrunMessage updateMdModelFormat(MdModelFormatEntity mdModelFormatEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            Integer count = mdModelFormatDao.getMdModelFormatMmfNameCount(mdModelFormatEntity);
            if (count > 0) {
                sm.setCode(2L); // 存在同名
                sm.setMeg("存在同名");
                return sm;
            }
            this.updateObject(mdModelFormatEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveMdModelFormat(Integer wmsModelId, String mmfNames, String mdAttrIds, Integer canSearch, String attrContents) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (wmsModelId == null) {
            sm.setCode(2L); // 商品模型id有误
            sm.setMeg("商品模型id有误");
            return sm;
        }
        if (mmfNames == null || mmfNames.equals("")) {
            sm.setCode(3L); // 规格名称有误
            sm.setMeg("规格名称有误");
            return sm;
        }
        try {
            // 保存时，优先删除此模型id下的规格
            MdModelFormatEntity mdModelFormatEntity = new MdModelFormatEntity();
            mdModelFormatEntity.setWmsModelId(wmsModelId);
            List<MdModelFormatEntity> formats = this.getList(mdModelFormatEntity);
            String mmfIds = "";
            String oldNames = "";
            for (MdModelFormatEntity entity : formats) {
                mmfIds += entity.getModelMmfId() + ",";
            }
            mdModelFormatDao.deleteMdFormats(wmsModelId, mmfIds);
            mdModelFormatDao.deleteMdFormatAttrInfo(mmfIds);

            String[] mmfNameArray = mmfNames.split(",");
            List<String> nameArray = Arrays.asList(mmfNameArray);
            if (nameArray.isEmpty() || nameArray.contains("")) {
                sm.setCode(5L); // 存在空字符串
                sm.setMeg("存在空字符");
                return sm;
            }
            Set<String> s = new HashSet<>();
            for (String mmfName : mmfNameArray) {
                s.add(mmfName);
            }
            if (s.size() < mmfNameArray.length) {
                sm.setCode(4L); // 存在同名
                sm.setMeg("存在同名");
                return sm;
            }
            List<MdModelFormatEntity> list = new ArrayList<>();
            for (String mmfName : mmfNameArray) {
                mdModelFormatEntity = new MdModelFormatEntity();
                mdModelFormatEntity.setWmsModelId(wmsModelId);
                mdModelFormatEntity.setMmfName(mmfName);
                this.newObject(mdModelFormatEntity);
                list.add(mdModelFormatEntity);
            }
            // 与属性关联
            boolean result = saveOrUpdateFormatAttr(list, mdAttrIds, attrContents, canSearch);
            if (result == false) {
                sm.setCode(6L);
            }
            // 更新商品模型下的可检索
            MdModelMaterielEntity mdModelMaterielEntity = new MdModelMaterielEntity();
            mdModelMaterielEntity.setWmsModelId(wmsModelId);
            mdModelMaterielEntity = (MdModelMaterielEntity) this.getOne(mdModelMaterielEntity);
            if (mdModelMaterielEntity != null) {
                mdModelMaterielEntity.setCanSearch(canSearch);
                this.updateObject(mdModelMaterielEntity);
            }
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateModelFormats(Integer wmsModelId, String mmfIds, String mmfNames, String mdAttrIds, Integer canSearch, String attrContents) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (wmsModelId == null) {
            sm.setCode(2L); // 商品模型id有误
            sm.setMeg("商品模型id有误");
            return sm;
        }
        if (mmfNames == null || mmfNames.equals("")) {
            sm.setCode(3L); // 规格名称有误
            sm.setMeg("规格名称有误");
            return sm;
        }
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account == null) {
                sm.setCode(7L);
                sm.setMeg("未登录");
                return sm;
            }
            // 先获取原有的规格数据，查询是否重复
            MdModelFormatEntity mdModelFormatEntity = new MdModelFormatEntity();
            mdModelFormatEntity.setWmsModelId(wmsModelId);
            List<MdModelFormatEntity> formats = this.getList(mdModelFormatEntity);

            mmfIds = "";
            String oldNames = "";
            for (MdModelFormatEntity entity : formats) {
                mmfIds += entity.getModelMmfId() + ",";
                oldNames += entity.getMmfName() + ",";
            }
            if (!mmfIds.equals(""))
                mmfIds = mmfIds.substring(0, mmfIds.length() - 1);

            Integer count = mdModelFormatDao.getMdModelFormatMmfNameCountByString(wmsModelId, mmfIds, mmfNames);
            if (count > 0) {
                sm.setCode(4L); // 存在同名
                sm.setMeg("存在同名");
                return sm;
            }
            // 删除原有的规格数据，以及关联属性数据
            mdModelFormatDao.deleteMdFormats(wmsModelId, mmfIds);
            mdModelFormatDao.deleteMdFormatAttrInfo(mmfIds);

            String[] mmfNameArray = mmfNames.split(",");
            List<String> nameArray = Arrays.asList(mmfNameArray);
            if (nameArray.isEmpty() || nameArray.contains("")) {
                sm.setCode(6L); // 存在空字符串
                sm.setMeg("存在空字符");
                return sm;
            }
            Set<String> s = new HashSet<>();
            for (String mmfName : mmfNameArray) {
                s.add(mmfName);
            }
            if (s.size() < mmfNameArray.length) {
                sm.setCode(4L); // 存在同名
                sm.setMeg("存在同名");
                return sm;
            }

            // 是否需要记录
            boolean needLog = true;
            if (mmfNames.equals("") && oldNames.equals(""))
                needLog = false;
            if (!oldNames.equals("")) {
                oldNames = oldNames.substring(0, oldNames.length() - 1);
                String[] oldNameArray = oldNames.split(",");
                if (oldNameArray.length != s.size())
                    needLog = true;
                else {
                    for (String oldName : oldNameArray) {
                        if (s.contains(oldName)) {
                            needLog = false;
                        } else {
                            needLog = true;
                        }
                    }
                }
            }
//            String[] mmfIdArray = mmfIds.split(",");
//            if (mmfIdArray.length != mmfNameArray.length) {
//                sm.setCode(5L); // 数据不匹配
//                sm.setMeg("规格id和规格名称数量不匹配");
//                return sm;
//            }
            List<MdModelFormatEntity> list = new ArrayList<>();

            for (String mmfName : mmfNameArray) {
                mdModelFormatEntity = new MdModelFormatEntity();
                mdModelFormatEntity.setWmsModelId(wmsModelId);
                mdModelFormatEntity.setMmfName(mmfName);
                this.newObject(mdModelFormatEntity);
                list.add(mdModelFormatEntity);
            }
            // 与属性关联
            boolean result = saveOrUpdateFormatAttr(list, mdAttrIds, attrContents, canSearch);
            if (result == false) {
                sm.setCode(6L);
            }
            // 更新商品模型下的可检索
            MdModelMaterielEntity mdModelMaterielEntity = new MdModelMaterielEntity();
            mdModelMaterielEntity.setWmsModelId(wmsModelId);
            mdModelMaterielEntity = (MdModelMaterielEntity) this.getOne(mdModelMaterielEntity);
            if (mdModelMaterielEntity != null) {
                mdModelMaterielEntity.setCanSearch(canSearch);
                this.updateObject(mdModelMaterielEntity);
            }
            if (needLog)
                logDao.saveMdModelMaterielOperateLog(account, 6, wmsModelId);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    private boolean saveOrUpdateFormatAttr(List<MdModelFormatEntity> list, String mdAttrIds, String contents, Integer canSearch) throws HSKException {
        boolean result = true;
        if (list.isEmpty())
            return true;
        if (list == null)
            return true;
        try {
            // 先删除有关的属性关联
            String modelMmfIds = "";
            for (MdModelFormatEntity entity : list) {
                modelMmfIds += entity.getModelMmfId() + ",";
            }
            if (!modelMmfIds.equals(""))
                modelMmfIds = modelMmfIds.substring(0, modelMmfIds.length() - 1);
            mdModelFormatDao.deleteMdFormatAttrInfo(modelMmfIds);

            if (mdAttrIds == null || mdAttrIds.equals("") || contents == null || contents.equals(""))
                return true;
            String[] mdAttrArray = mdAttrIds.split(",");
            String[] attrContentArray = contents.split(",");
//            if ((mdAttrArray.length - attrContentArray.length) != 0)
//                return true;
            if (mdAttrArray.length < 1 || attrContentArray.length < 1)
                return true;
            if (mdAttrArray.length == 1 && mdAttrArray[0].equals(""))
                return true;
            if (attrContentArray.length == 1 && attrContentArray[0].equals(""))
                return true;

            MdModelFormatAttrInfoEntity mdModelFormatAttrInfoEntity;
            Integer index = 0;
            for (MdModelFormatEntity entity : list) {
                for (String mdAttrIdStr : mdAttrArray) {
                    if (mdAttrIdStr.equals(""))
                        continue;
                    if ((attrContentArray.length - 1) < index)
                        continue;
                    mdModelFormatAttrInfoEntity = new MdModelFormatAttrInfoEntity();
                    mdModelFormatAttrInfoEntity.setModelMmfId(entity.getModelMmfId());
                    mdModelFormatAttrInfoEntity.setAttrContent(contents);
//                    mdModelFormatAttrInfoEntity.setMmaMxId(Integer.parseInt(mdAttrIdStr));
                    mdModelFormatAttrInfoEntity.setMmaId(Integer.parseInt(mdAttrIdStr));

                    if (canSearch != null) {
                        if (canSearch == 1)
                            mdModelFormatAttrInfoEntity.setCanSearch(1);
                        else
                            mdModelFormatAttrInfoEntity.setCanSearch(0);
                    }
                    this.newObject(mdModelFormatAttrInfoEntity);
                    index++;
                }
            }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public SysRetrunMessage deleteMdModelFormat(Integer wmsModelId, Integer mmfId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account == null) {
                sm.setCode(2L);
                sm.setMeg("未登录");
                return sm;
            }
            MdModelFormatEntity mdModelFormatEntity = new MdModelFormatEntity();
            mdModelFormatEntity.setWmsModelId(wmsModelId);
            mdModelFormatEntity.setModelMmfId(mmfId);
            this.deleteObjects(mdModelFormatEntity);
            mdModelFormatDao.deleteMdFormatAttrInfo(mmfId.toString()); // 删除相关联的数据
            logDao.saveMdModelMaterielOperateLog(account, 7, wmsModelId);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteMdModelFormats(Integer wmsModelId, String mmfIds) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (wmsModelId == null || mmfIds == null || mmfIds.equals("")) {
            sm.setCode(3L);
            sm.setMeg("传入数据有误");
            return sm;
        }
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account == null) {
                sm.setCode(2L); // 未登录
                sm.setMeg("未登录");
                return sm;
            }
            mdModelFormatDao.deleteMdFormats(wmsModelId, mmfIds);
            mdModelFormatDao.deleteMdFormatAttrInfo(mmfIds); // 删除相关联的数据
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getMdModelAttrs(Integer wmsModelId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            MdModelFormatEntity mdModelFormatEntity = new MdModelFormatEntity();
            mdModelFormatEntity.setWmsModelId(wmsModelId);
            List<MdModelFormatEntity> list = this.getList(mdModelFormatEntity);
            String mmfIds = "";
            for (MdModelFormatEntity entity : list) {
                mmfIds += entity.getModelMmfId() + ",";
            }
            if (!mmfIds.equals("")) {
                mmfIds = mmfIds.substring(0, mmfIds.length() - 1);
            }
            List<Map<String, String>> attrList = mdModelFormatDao.getMdModelFormatAttrInfoList(mmfIds);
            sm.setObj(attrList);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getTest(String jsonStr) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        sm.setObj(map);
        return sm;
    }
}
