package com.hsk.dentistmall.web.materiel.service.impl;

import com.google.inject.internal.Objects;
import com.hsk.dentistmall.api.daobbase.IMdMaterielInfoDao;
import com.hsk.dentistmall.api.daobbase.IMdModelMaterielDao;
import com.hsk.dentistmall.api.daobbase.IOperatorsMaterielInfoDao;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.web.materiel.service.IMdModelMaterielService;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.ILogDao;
import com.hsk.xframe.api.daobbase.ISysFileInfoDao;
import com.hsk.xframe.api.persistence.SysFileInfo;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import org.jbpm.pvm.internal.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.ClassEditor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * author: yangfeng
 * time: 2020/7/31 9:55
 */
@Service
public class MdModelMaterielService extends DSTService implements IMdModelMaterielService {
    @Autowired
    IMdModelMaterielDao mdModelMaterielDao;
    @Autowired
    ISysFileInfoDao sysFileInfoDao;
    @Autowired
    ILogDao logDao;
    @Autowired
    IOperatorsMaterielInfoDao operatorsMaterielInfoDao;

    @Override
    public PagerModel getMdModelMaterielPagerModel(MdModelMaterielEntity mdModelMaterielEntity) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList());
        try {
            pm = mdModelMaterielDao.getMdModelMaterielPagerModel(mdModelMaterielEntity);
            List<MdModelMaterielEntity> list = pm.getItems();
            String mdModelIds = "";
            for (MdModelMaterielEntity entity : list) {
                mdModelIds += entity.getWmsModelId() + ",";
            }

            List<Map<String, Object>> refCountList = null;
            List<Map<String, Object>> sysFiles = null;
            List<Map<String, Object>> mdBrands = null;

            if (!mdModelIds.equals("")) {
                mdModelIds = mdModelIds.substring(0, mdModelIds.length() - 1);
                // 获取被发布数量
                refCountList = mdModelMaterielDao.getMdModelMaterielRefCount(mdModelIds);
                // 获取商品图标
                sysFiles = mdModelMaterielDao.getMdModelMaterielFilePath(mdModelIds);
                // 获取品牌
                mdBrands = mdModelMaterielDao.getMdModelMaterielBrand(mdModelIds);
            }
            Integer mdModelId = null;
            String countStr = "";
            List<String> pathList = null;
            List<String> brandList = null;
            for (MdModelMaterielEntity entity : list) {
                entity.setRefCount(0);
                if (refCountList != null) {
                    // 被发布的数量
                    for (Map<String, Object> map : refCountList) {
                        if (map.get("mdModelId") != null && !map.get("mdModelId").equals("") && Objects.equal(Integer.parseInt(map.get("mdModelId").toString()), entity.getWmsModelId())) {
                            countStr = map.get("count") == null ? "0" : map.get("count").toString();
                            entity.setRefCount(Integer.parseInt(countStr.equals("") ? "0" : countStr));
                            break;
                        }
                    }
                }
                if (sysFiles != null) {
                    pathList = new ArrayList<>();
                    // 图片
                    for (Map<String, Object> map : sysFiles) {
                        if (map.get("mdModelId") != null && !map.get("mdModelId").equals("") && Objects.equal(Integer.parseInt(map.get("mdModelId").toString()), entity.getWmsModelId())) {
                            if (map.get("rootPath") != null) {
                                if (entity.getRootPath() == null)
                                    entity.setRootPath(map.get("rootPath").toString());
//                                break;
                                pathList.add(map.get("rootPath").toString());
                            }
                        }
                    }
                    entity.setPathList(pathList);
                }
                if (mdBrands != null) {
                    brandList = new ArrayList<>();
                    // 品牌
                    for (Map<String, Object> map : sysFiles) {
                        if (map.get("mdModelId") != null && !map.get("mdModelId").equals("") && Objects.equal(Integer.parseInt(map.get("mdModelId").toString()), entity.getWmsModelId())) {
                            if (map.get("brandName") != null) {
                                if (entity.getBrandName() == null)
                                    entity.setBrandName(map.get("brandName").toString());
//                               break;
                                brandList.add(map.get("brandName").toString());
                            }
                        }
                    }
                    entity.setBrandList(brandList);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage getMdModelMaterielInfo(Integer wmsModelId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            MdModelMaterielEntity mdModelMaterielEntity = new MdModelMaterielEntity();
            mdModelMaterielEntity.setWmsModelId(wmsModelId);
            mdModelMaterielEntity = (MdModelMaterielEntity) this.getOne(mdModelMaterielEntity);
            if (mdModelMaterielEntity == null) {
                sm.setCode(2L); // 没有找到此商品模型
                sm.setMeg("没有找到此商品模型");
                return sm;
            }

            List<Map<String, Object>> refCountList = null;
            List<Map<String, Object>> sysFiles = null;
            List<Map<String, Object>> mdBrands = null;
            // 获取被发布数量
            refCountList = mdModelMaterielDao.getMdModelMaterielRefCount(wmsModelId.toString());
            // 获取商品图标
            sysFiles = mdModelMaterielDao.getMdModelMaterielFilePath(wmsModelId.toString());
            // 获取品牌
            mdBrands = mdModelMaterielDao.getMdModelMaterielBrand(wmsModelId.toString());
            Integer mdModelId = null;
            String countStr = "";
            List<String> pathList = null;
            List<String> brandList = null;
            String imgIds = "";
            mdModelMaterielEntity.setRefCount(0);
            if (refCountList != null) {
                // 被发布的数量
                for (Map<String, Object> map : refCountList) {
                    if (map.get("mdModelId") != null && !map.get("mdModelId").equals("") && Objects.equal(Integer.parseInt(map.get("mdModelId").toString()), mdModelMaterielEntity.getWmsModelId())) {
                        countStr = map.get("count") == null ? "0" : map.get("count").toString();
                        mdModelMaterielEntity.setRefCount(Integer.parseInt(countStr.equals("") ? "0" : countStr));
                        break;
                    }
                }
            }
            if (sysFiles != null) {
                pathList = new ArrayList<>();
                // 图片
                for (Map<String, Object> map : sysFiles) {
                    if (map.get("mdModelId") != null && !map.get("mdModelId").equals("") && Objects.equal(Integer.parseInt(map.get("mdModelId").toString()), mdModelMaterielEntity.getWmsModelId())) {
                        if (map.get("rootPath") != null)
                            pathList.add(map.get("rootPath").toString());
                        imgIds += map.get("fileId") + ",";
                    }
                }
                mdModelMaterielEntity.setPathList(pathList);
                if (!imgIds.equals(""))
                    imgIds = imgIds.substring(0, imgIds.length() - 1);
                mdModelMaterielEntity.setImgIds(imgIds);
            }
            if (mdBrands != null) {
                brandList = new ArrayList<>();
                // 品牌
                for (Map<String, Object> map : sysFiles) {
                    if (map.get("mdModelId") != null && !map.get("mdModelId").equals("") && Objects.equal(Integer.parseInt(map.get("mdModelId").toString()), mdModelMaterielEntity.getWmsModelId())) {
                        if (map.get("brandName") != null)
                            brandList.add(map.get("brandName").toString());
                    }
                }
                mdModelMaterielEntity.setBrandList(brandList);
            }

            sm.setObj(mdModelMaterielEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteMdModelMateriel(Integer wmsModelId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (wmsModelId == null) {
            sm.setCode(3L); // 数据有问题
            sm.setMeg("商品模型id有误");
            return sm;
        }
        try {
            Integer count = mdModelMaterielDao.getMdModelMaterielRefCountOnly(wmsModelId);
            if (count > 0) {
                sm.setCode(2L); // 存在被引用的
                sm.setMeg("商品模型被引用");
                return sm;
            }
            mdModelMaterielDao.deleteMdModelMateruel(wmsModelId.toString());
            //删除图片关联
            mdModelMaterielDao.deleteMdModelFileInfo(wmsModelId);
            //删除品牌关联
            mdModelMaterielDao.deleteMdModelBrand(wmsModelId.toString());

            // 删除规格关联
            mdModelMaterielDao.deleteMdModelFormat(wmsModelId.toString());
            // 删除日志
            mdModelMaterielDao.deleteMdModelLog(wmsModelId.toString());
            // 删除关联参数
            mdModelMaterielDao.deleteMdModelParameter(wmsModelId.toString());
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteMdModelMaterielBatch(String wmsModelIds) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (wmsModelIds == null || wmsModelIds.equals("")) {
            sm.setCode(3L); // 数据有问题
            sm.setMeg("商品模型id有误");
            return sm;
        }
        try {
            List<Map<String, Object>> list = mdModelMaterielDao.getMdModelMaterielRefCountOnly(wmsModelIds);
            if (list != null) {
                for (Map<String, Object> map : list) {
                    if (map.get("count") != null && !map.get("count").toString().equals("")) {
                        if (Integer.parseInt(map.get("count").toString()) > 0) {
                            sm.setCode(2L); // 存在被引用的
                            sm.setMeg("商品模型被引用");
                            return sm;
                        }
                    }
                }
            }
            mdModelMaterielDao.deleteMdModelMateruel(wmsModelIds);

            //删除图片关联
            mdModelMaterielDao.deleteMdModelFileInfo(wmsModelIds);
            //删除品牌关联
            mdModelMaterielDao.deleteMdModelBrand(wmsModelIds);

            // 删除规格关联
            mdModelMaterielDao.deleteMdModelFormat(wmsModelIds);
            // 删除日志
            mdModelMaterielDao.deleteMdModelLog(wmsModelIds);
            // 删除关联参数
            mdModelMaterielDao.deleteMdModelParameter(wmsModelIds);

        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateMdModelMaterielState(Integer wmsModelId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account == null) {
                sm.setCode(3L); // 未登录
                sm.setMeg("未登录");
                return sm;
            }
            // 还要根据某些情况进行筛选更新
            MdModelMaterielEntity mdModelMaterielEntity = new MdModelMaterielEntity();
            mdModelMaterielEntity.setWmsModelId(wmsModelId);
            mdModelMaterielEntity = (MdModelMaterielEntity) this.getOne(mdModelMaterielEntity);
            if (mdModelMaterielEntity == null) {
                sm.setCode(2L); // 不存在此商品模型
                sm.setMeg("此商品模型不存在");
                return sm;
            }
            mdModelMaterielEntity.setState(mdModelMaterielEntity.getState().equals("2") ? "1" : "2");
            this.updateObject(mdModelMaterielEntity);
            sm.setObj(mdModelMaterielEntity);
            if (mdModelMaterielEntity.getState().equals("2"))
                logDao.saveMdModelMaterielOperateLog(account, 3, mdModelMaterielEntity.getWmsModelId());
            else
                logDao.saveMdModelMaterielOperateLog(account, 4, mdModelMaterielEntity.getWmsModelId());
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getNewCode() throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            Map<String, String> map = mdModelMaterielDao.getMdModelMaterielLastCode();
            String code = null;
            if (map == null) {
                code = "yyk-1";
                sm.setObj(code);
                return sm;
            }
            String lastCode = map.get("modelMatCode");
            if (lastCode == null) {
                code = "yyk-1";
                sm.setObj(code);
                return sm;
            }
            String[] codeArray = lastCode.split("-");
            Integer newCodeNumber = Integer.parseInt(codeArray[1]);
            newCodeNumber += 1;
            code = "yyk-" + newCodeNumber;
            sm.setObj(code);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveMdModelMaterielInfo(MdModelMaterielEntity mdModelMaterielEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account == null) {
                sm.setCode(5L); // 未登录
                sm.setMeg("未登录");
                return sm;
            }
//            if ((mdModelMaterielEntity.getMatType1() == null || mdModelMaterielEntity.getMatType2() == null || mdModelMaterielEntity.getMatType3() == null) ||
//                    (mdModelMaterielEntity.getModelMatName() == null || mdModelMaterielEntity.getModelMatName().equals("")) ||
//                    (mdModelMaterielEntity.getAliasName() == null || mdModelMaterielEntity.getAliasName().equals("")) ||
//                    (mdModelMaterielEntity.getKeyWord() == null || mdModelMaterielEntity.getKeyWord().equals("")) ||
//                    (mdModelMaterielEntity.getIntroduction() == null || mdModelMaterielEntity.getIntroduction().equals("")) ||
//                    (mdModelMaterielEntity.getImgIds() == null || mdModelMaterielEntity.getImgIds().equals(""))) {
//                sm.setCode(6L); // 存在必填项未填
//                sm.setMeg("存在必填项未填");
//                return sm;
//            }
            String matCode = mdModelMaterielEntity.getModelMatCode();
            if (matCode == null || matCode.equals("") || matCode.indexOf("yyk-") < 0) {
                sm.setCode(3L); // 商品编号有错误
                sm.setMeg("商品编号有误");
                return sm;
            }
            Integer count = mdModelMaterielDao.getMdModelMaterielCodeCountCheck(matCode, null);
            if (count > 0) {
                sm.setCode(2L); // 存在相同编号
                sm.setMeg("存在相同编号");
                return sm;
            }

            String matName = mdModelMaterielEntity.getModelMatName();
            count = mdModelMaterielDao.getMdModelMaterielMatNameCountCheck(matName, null);
            if (count > 0) {
                sm.setCode(7L); // 存在相同通用名称
                sm.setMeg("存在相同通用名称");
                return sm;
            }

            mdModelMaterielEntity.setWzId(account.getOldId());
            mdModelMaterielEntity.setSuiId(account.getSuiId());
            this.newObject(mdModelMaterielEntity);
            if (mdModelMaterielEntity.getMbdId() != null && !mdModelMaterielEntity.getMbdId().equals("")) {
                // 保存模型与品牌关联
                MdModelBrandInfoEntity mdModelBrandInfoEntity = new MdModelBrandInfoEntity();
                mdModelBrandInfoEntity.setMbdId(Integer.parseInt(mdModelMaterielEntity.getMbdId()));
                mdModelBrandInfoEntity.setMdModelId(mdModelMaterielEntity.getWmsModelId());
                this.newObject(mdModelBrandInfoEntity);
            }
            sm.setObj(mdModelMaterielEntity);
            logDao.saveMdModelMaterielOperateLog(account, 1, mdModelMaterielEntity.getWmsModelId());
            // 处理图片
            mdModelMaterielEntity = this.saveImgPathHandler(mdModelMaterielEntity, account, true);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateMdModelMaterielInfo(MdModelMaterielEntity mdModelMaterielEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account == null) {
                sm.setCode(5L); // 未登录
                sm.setMeg("未登录");
                return sm;
            }
//            if ((mdModelMaterielEntity.getMatType1() == null || mdModelMaterielEntity.getMatType2() == null || mdModelMaterielEntity.getMatType3() == null) ||
//                    (mdModelMaterielEntity.getModelMatName() == null || mdModelMaterielEntity.getModelMatName().equals("")) ||
//                    (mdModelMaterielEntity.getAliasName() == null || mdModelMaterielEntity.getAliasName().equals("")) ||
//                    (mdModelMaterielEntity.getKeyWord() == null || mdModelMaterielEntity.getKeyWord().equals("")) ||
//                    (mdModelMaterielEntity.getIntroduction() == null || mdModelMaterielEntity.getIntroduction().equals("")) ||
//                    (mdModelMaterielEntity.getImgIds() == null || mdModelMaterielEntity.getImgIds().equals(""))) {
//                sm.setCode(6L); // 存在必填项未填
//                sm.setMeg("存在必填项未填");
//                return sm;
//            }
            String matCode = mdModelMaterielEntity.getModelMatCode();
            if (matCode == null || matCode.equals("") || matCode.indexOf("yyk-") < 0) {
                sm.setCode(3L); // 商品编号有错误
                sm.setMeg("商品编号有误");
                return sm;
            }
            if (mdModelMaterielEntity.getWmsModelId() == null) {
                sm.setCode(4L); // 商品id不正确
                sm.setMeg("商品模型id有误");
                return sm;
            }
            Integer count = mdModelMaterielDao.getMdModelMaterielCodeCountCheck(matCode, mdModelMaterielEntity.getWmsModelId());
            if (count > 0) {
                sm.setCode(2L); // 存在相同编号
                sm.setMeg("存在相同编号");
                return sm;
            }
            String matName = mdModelMaterielEntity.getModelMatName();
            count = mdModelMaterielDao.getMdModelMaterielMatNameCountCheck(matName, mdModelMaterielEntity.getWmsModelId());
            if (count > 0) {
                sm.setCode(7L); // 存在相同通用名称
                sm.setMeg("存在相同通用名称");
                return sm;
            }
            // 检查name是否修改
            count = mdModelMaterielDao.getMdModelMaterielNameChange(mdModelMaterielEntity.getModelMatName(), mdModelMaterielEntity.getWmsModelId());
            if (count <= 0) {
                logDao.saveMdModelMaterielOperateLog(account, 5, mdModelMaterielEntity.getWmsModelId());
            }
            // 检查启用禁用状态
            count = mdModelMaterielDao.getMdModelMaterielStateChange(mdModelMaterielEntity.getState(), mdModelMaterielEntity.getWmsModelId());
            if (count <= 0) {
                if (mdModelMaterielEntity.getState().equals("1"))
                    logDao.saveMdModelMaterielOperateLog(account, 4, mdModelMaterielEntity.getWmsModelId());
                else
                    logDao.saveMdModelMaterielOperateLog(account, 3, mdModelMaterielEntity.getWmsModelId());
            }
            this.updateObject(mdModelMaterielEntity);
            // 处理图片
            mdModelMaterielEntity = this.saveImgPathHandler(mdModelMaterielEntity, account, false);

            //先删除原有的数据
            MdModelBrandInfoEntity mdModelBrandInfoEntity = new MdModelBrandInfoEntity();
            mdModelBrandInfoEntity.setMdModelId(mdModelMaterielEntity.getWmsModelId());
            this.deleteObjects(mdModelBrandInfoEntity);
            if (mdModelMaterielEntity.getMbdId() != null && !mdModelMaterielEntity.getMbdId().equals("")) {
                // 保存模型与品牌关联
                mdModelBrandInfoEntity = new MdModelBrandInfoEntity();
                mdModelBrandInfoEntity.setMbdId(Integer.parseInt(mdModelMaterielEntity.getMbdId()));
                mdModelBrandInfoEntity.setMdModelId(mdModelMaterielEntity.getWmsModelId());
                this.newObject(mdModelBrandInfoEntity);
            }
            sm.setObj(mdModelMaterielEntity);

        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
            throw new HSKException();
        }
        return sm;
    }

    // 处理图片
    private MdModelMaterielEntity saveImgPathHandler(MdModelMaterielEntity mdModelMaterielEntity, SysUserInfo account, boolean newOrUpdate) throws HSKException{
        try {
            String imgIds = mdModelMaterielEntity.getImgIds();
            List<Map<String, Object>> maps = mdModelMaterielDao.getImgIds(mdModelMaterielEntity.getWmsModelId(), imgIds);
            Integer count = 0;
            boolean needNewImg = false;
            String imgs = "";
            if (maps != null && !maps.isEmpty()) {
                for (Map<String, Object> map : maps) {
                    if (map.get("imgIds") != null)
                        imgs += map.get("imgIds").toString() + ",";
                }
//                Map<String, Object> map = maps.get(0);
//                imgs = map.get("imgIds") == null ? null : map.get("imgIds").toString();
            }
            String[] newImgArray = imgIds == null ? null : imgIds.split(",");
            if (imgs != null && newImgArray != null) {
                String[] imgArray = imgs.split(",");
                for (String img : imgArray) {
                    for (String newImg : newImgArray) {
                        if (img.equals(newImg)) {
                            count += 1;
                            break;
                        }
                    }
                }
                if (Objects.equal(count, newImgArray.length)) {
                    needNewImg = false;
                } else {
                    needNewImg = true;
                }
            }


            Integer wmsModelId = mdModelMaterielEntity.getWmsModelId();
            if (wmsModelId != null)
                mdModelMaterielDao.deleteMdModelFileInfo(wmsModelId); // 更新时候，先删除存在的，再添加现在新的
            List<SysFileInfo> list = sysFileInfoDao.getSysFileInfoByFileId(imgIds);
            if (list == null) {
                return mdModelMaterielEntity;
            }
            MdModelFileInfoEntity mdModelFileInfoEntity = null;
            for (SysFileInfo sysFileInfo : list) {
                mdModelFileInfoEntity = new MdModelFileInfoEntity();
                mdModelFileInfoEntity.setMdModelId(wmsModelId);
                mdModelFileInfoEntity.setFileCode(sysFileInfo.getFileCode());
                mdModelFileInfoEntity.setFileId(sysFileInfo.getFileId());
                this.newObject(mdModelFileInfoEntity);
            }
            if (needNewImg || newOrUpdate)
                logDao.saveMdModelMaterielOperateLog(account, 2, mdModelFileInfoEntity.getMdModelId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new HSKException();
        }
        return mdModelMaterielEntity;
    }

    @Override
    public SysRetrunMessage saveMdModelMaterielInfoParameter(Integer wmsModelId, String mmpIds, String mmpContents) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            if (mmpIds == null) {
                sm.setCode(2L);
                sm.setMeg("参数不匹配");
                return sm;
            }
            String[] mmpIdArray = mmpIds.split(",");
            mmpContents = mmpContents == null ? "" : mmpContents;
            String[] mmpContentArray = mmpContents.split("#");
//            if (mmpIdArray.length != mmpContentArray.length) {
//                sm.setCode(2L); // 参数不匹配
//                sm.setMeg("参数不匹配");
//                return sm;
//            }


            String mmpIdStr = "";
            for (String mmpdId : mmpIdArray) {
                mmpIdStr += mmpdId + ",";
            }
            if (!mmpIdStr.equals(""))
                mmpIdStr = mmpIdStr.substring(0, mmpIdStr.length() - 1);
            List<MdMaterielParameter> list = mdModelMaterielDao.getMdModelParameterListByMmpIds(mmpIdStr);
            MdModelParameterInfoEntity mdModelParameterInfoEntity;
            Integer index = 0;
            for (String mmpId : mmpIdArray) {
                if (mmpId.equals(""))
                    continue;
                for (MdMaterielParameter mdMaterielParameter : list) {
                    if (Objects.equal(mdMaterielParameter.getMmpId(), Integer.parseInt(mmpId))) {
                        if (mdMaterielParameter.getIsRequired() != null && mdMaterielParameter.getIsRequired().equals("1")) {
                            if ((mmpContentArray.length - 1) < index || mmpContentArray[index].equals("")) {
                                sm.setCode(3L); // 存在必填项
                                sm.setMeg(mdMaterielParameter.getMmpName() + "必填！");
                                return sm;
                            }
                        }
                    }
                }
                index ++;
            }
            index = 0;
            for (String mmpId : mmpIdArray) {
                if (mmpId.equals(""))
                    continue;
                mdModelParameterInfoEntity = new MdModelParameterInfoEntity();
                for (MdMaterielParameter mdMaterielParameter : list) {
                    if (Objects.equal(mdMaterielParameter.getMmpId(), Integer.parseInt(mmpId))) {
                        mdModelParameterInfoEntity.setMmpId(Integer.parseInt(mmpId));
                        mdModelParameterInfoEntity.setMmpName(mdMaterielParameter.getMmpName());
                        break;
                    }
                }
                mdModelParameterInfoEntity.setMdModelId(wmsModelId);
                mdModelParameterInfoEntity.setMmpContent((mmpContentArray.length - 1) >= index ? mmpContentArray[index] : "");
                this.newObject(mdModelParameterInfoEntity);
                index ++;
            }
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateMdModelMaterielInfoParameter(Integer wmsModelId, String mdMpIds, String mmpIds, String mmpContents) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            if (mmpIds == null) {
                sm.setCode(2L); // 参数不匹配
                sm.setMeg("参数不匹配");
                return sm;
            }
            MdModelParameterInfoEntity mdModelParameterInfoEntity = new MdModelParameterInfoEntity();
//            mdModelParameterInfoEntity.setMdModelId(wmsModelId);
//            List<MdModelParameterInfoEntity> params = this.getList(mdModelParameterInfoEntity);
//
//            String tempMmpIds = "";
//            for (MdModelParameterInfoEntity entity : params) {
//                tempMmpIds += entity.getMmpId() + ",";
//            }
//            if (!tempMmpIds.equals("")) {
//                tempMmpIds = tempMmpIds.substring(0, tempMmpIds.length() - 1);
            mdModelMaterielDao.deleteMdModelParameter(wmsModelId.toString()); // 删除关联参数
//            }

            String[] mmpIdArray = mmpIds.split(",");
            mmpContents = mmpContents == null ? "" : mmpContents;
            String[] mmpContentArray = mmpContents.split("#");
//            if (mmpIdArray.length != mmpContentArray.length) {
//                sm.setCode(2L); // 参数不匹配
//                sm.setMeg("参数不匹配");
//                return sm;
//            }

            String mmpIdStr = "";
            for (String mmpdId : mmpIdArray) {
                mmpIdStr += mmpdId + ",";
            }
            if (!mmpIdStr.equals(""))
                mmpIdStr = mmpIdStr.substring(0, mmpIdStr.length() - 1);
            List<MdMaterielParameter> list = mdModelMaterielDao.getMdModelParameterListByMmpIds(mmpIdStr);
//            MdModelParameterInfoEntity mdModelParameterInfoEntity;
            Integer index = 0;
            for (String mmpId : mmpIdArray) {
                if (mmpId.equals(""))
                    continue;
                mdModelParameterInfoEntity = new MdModelParameterInfoEntity();
                for (MdMaterielParameter mdMaterielParameter : list) {
                    if (Objects.equal(mdMaterielParameter.getMmpId(), Integer.parseInt(mmpId))) {
                        if (mdMaterielParameter.getIsRequired() != null && mdMaterielParameter.getIsRequired().equals("1")) {
                            if ((mmpContentArray.length - 1) < index || mmpContentArray[index].equals("")) {
                                sm.setCode(3L); // 存在必填项
                                sm.setMeg(mdMaterielParameter.getMmpName() + "必填！");
                                return sm;
                            }
                        }
                        mdModelParameterInfoEntity.setMmpId(Integer.parseInt(mmpId));
                        mdModelParameterInfoEntity.setMmpName(mdMaterielParameter.getMmpName());
                        break;
                    }
                }
                mdModelParameterInfoEntity.setMdModelId(wmsModelId);
                mdModelParameterInfoEntity.setMmpContent((mmpContentArray.length - 1) >= index ? mmpContentArray[index] : "");
                this.newObject(mdModelParameterInfoEntity);
                index ++;
            }
//            for (String mmpId : mmpIdArray) {
//                if (mmpId.equals(""))
//                    continue;
//                mdModelParameterInfoEntity = new MdModelParameterInfoEntity();
//                for (MdMaterielParameter mdMaterielParameter : list) {
//                    if (Objects.equal(mdMaterielParameter.getMmpId(), Integer.parseInt(mmpId))) {
//                        if (mdMaterielParameter.getIsRequired() != null && mdMaterielParameter.getIsRequired().equals("1")) {
//                            if ((mmpContentArray.length - 1) < index || mmpContentArray[index].equals("")) {
//                                sm.setCode(3L); // 存在必填项
//                                sm.setMeg(mdMaterielParameter.getMmpName() + "必填！");
//                                return sm;
//                            }
//                        }
////                        mdModelParameterInfoEntity.setMmpId(Integer.parseInt(mmpId));
////                        mdModelParameterInfoEntity.setMmpName(mdMaterielParameter.getMmpName());
//                        break;
//                    }
//                }
//                mdModelParameterInfoEntity.setMdModelId(wmsModelId);
//                mdModelParameterInfoEntity.setMmpId(Integer.parseInt(mmpId));
//                mdModelParameterInfoEntity = (MdModelParameterInfoEntity) this.getOne(mdModelParameterInfoEntity);
//                mdModelParameterInfoEntity.setMmpContent((mmpContentArray.length > index) ? mmpContentArray[index] : "");
//                this.updateObject(mdModelParameterInfoEntity);
//                index ++;
//            }
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getMdModelMaterielInfoList(Integer wmsModelId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (wmsModelId == null) {
            sm.setCode(2L); // 模型id有错误
            sm.setMeg("商品模型id有误");
            return sm;
        }
        try {
            List<MdModelParameterInfoEntity> list = mdModelMaterielDao.getMdModelParameterListByModelId(wmsModelId);
            String mmpIds = "";
            for (MdModelParameterInfoEntity entity : list) {
                    mmpIds += entity.getMmpId() + ",";
            }
            if (!mmpIds.equals("")) {
                mmpIds = mmpIds.substring(0, mmpIds.length() - 1);
                List<MdMaterielParameter> paramList =  operatorsMaterielInfoDao.findMdMaterielParameterByIds(mmpIds);
                Map<String, String> map;
                String[] mmpIdArray = mmpIds.split(",");
                Integer mmpId = null;
                List<Map<String, String>> mapList;
                for (MdModelParameterInfoEntity entity : list) {
                    if (entity.getMmpId() == null)
                        continue;
                    map = new HashMap<>();
                    mapList = new ArrayList<>();
                    mmpId = entity.getMmpId();
                    for (MdMaterielParameter param : paramList) {
                        if (Objects.equal(param.getMmpId(), mmpId)) {
                            map.put("value", param.getOptiona_value());
                            map.put("key", mmpId.toString());
                            mapList.add(map);
                            entity.setInputMode(param.getInputMode());
                            break;
                        }
                    }
                    entity.setParamOptions(map);
                }
            }
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getFirstMdMaterielTypeList(Integer mmtId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            List<MdMaterielType> list = mdModelMaterielDao.getMdMaterielTypeFirst(mmtId);
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getSecondMdMaterielTypeList(Integer mmtId, Integer firstId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            List<MdMaterielType> list = mdModelMaterielDao.getMdMaterielTypeSecond(mmtId, firstId);
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getThirdMdMaterielTypeList(Integer mmtId, Integer secondId, Integer firstId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            List<MdMaterielType> list = mdModelMaterielDao.getMdMaterielTypeThird(mmtId, secondId, firstId);
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveMdMaterielType(Integer firstId, Integer secondId, Integer thirdId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            Map<String, Object> map = this.getAccountBelong();
            if (map == null || account == null) {
                sm.setCode(2L); // 未登录
                sm.setMeg("未登录");
                return sm;
            }

            MdModelTypeLinkInfoEntity mdModelTypeLinkInfoEntity = new MdModelTypeLinkInfoEntity();
            mdModelTypeLinkInfoEntity.setFirstId(firstId);
            mdModelTypeLinkInfoEntity.setSecondId(secondId);
            mdModelTypeLinkInfoEntity.setThirdId(thirdId);

            Integer count = mdModelMaterielDao.getMdMaterielTypeLinkCount(mdModelTypeLinkInfoEntity);
            if (count > 0) {
                sm.setCode(3L); // 存在，不添加
                sm.setMeg("已存在，不重复添加");
                return sm;
            }

            mdModelTypeLinkInfoEntity.setSuiId(account.getSuiId());
            if (map.get("rbaId") != null && !map.get("rbaId").equals(""))
                mdModelTypeLinkInfoEntity.setRbaId(Integer.parseInt(map.get("rbaId").toString()));
            if (map.get("rbsId") != null && !map.get("rbsId").equals(""))
                mdModelTypeLinkInfoEntity.setRbsId(Integer.parseInt(map.get("rbsId").toString()));
            if (map.get("rbbId") != null && !map.get("rbbId").equals(""))
                mdModelTypeLinkInfoEntity.setRbbId(Integer.parseInt(map.get("rbbId").toString()));

            Integer mdMtId = mdModelMaterielDao.getMdModelTypeLinkId(mdModelTypeLinkInfoEntity);
            if (Objects.equal(mdMtId, -1))
                this.newObject(mdModelTypeLinkInfoEntity);
            else {
                MdModelTypeLinkInfoEntity mdModelTypeLinkInfoEntity1 = new MdModelTypeLinkInfoEntity();
                mdModelTypeLinkInfoEntity1.setMdMtId(mdMtId);
                mdModelTypeLinkInfoEntity1 = (MdModelTypeLinkInfoEntity) this.getOne(mdModelTypeLinkInfoEntity1);
//                mdModelTypeLinkInfoEntity.setMdMtId(mdMtId);
//                mdModelTypeLinkInfoEntity = (MdModelTypeLinkInfoEntity) this.getOne(mdModelTypeLinkInfoEntity);
                mdModelTypeLinkInfoEntity1.setFirstId(mdModelTypeLinkInfoEntity.getFirstId());
                mdModelTypeLinkInfoEntity1.setSecondId(mdModelTypeLinkInfoEntity.getSecondId());
                mdModelTypeLinkInfoEntity1.setThirdId(mdModelTypeLinkInfoEntity.getThirdId());
                mdModelTypeLinkInfoEntity1.setCreateDate(new Date());
                mdModelTypeLinkInfoEntity1.setCreateRen(account.getUserName());
                mdModelTypeLinkInfoEntity1.setEditDate(new Date());
                mdModelTypeLinkInfoEntity1.setEditRen(account.getUserName());
                this.updateObject(mdModelTypeLinkInfoEntity1);
            }
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getMdMaterielTypeLink(String searchName) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account == null) {
                sm.setObj(2L);
                sm.setMeg("未登录");
                return sm;
            }
            List<Map<String, Object>> list = mdModelMaterielDao.getMdMaterielTypeLinkList(searchName, account.getSuiId());
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getMdModelOperateLogList(Integer wmsModelId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            if (account == null) {
                sm.setCode(2L); // 未登录
                sm.setMeg("未登录");
                return sm;
            }
            MdModelOperateLogEntity mdModelOperateLogEntity = new MdModelOperateLogEntity();
            mdModelOperateLogEntity.setMdModelId(wmsModelId);
            List<MdModelOperateLogEntity> list = this.getList(mdModelOperateLogEntity);
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public PagerModel getMdModelOperateLodPagerModel(Integer wmsModelId) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList());
        if (wmsModelId == null) {
            return pm;
        }
        try {
            pm = mdModelMaterielDao.getMdModelOpratePagerModel(wmsModelId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage saveMdModelMaterielBrand(Integer wmsModelId, String mbdIds) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (wmsModelId == null || mbdIds == null || mbdIds.equals("")) {
            sm.setCode(2L); // 参数有问题
            sm.setMeg("参数有问题");
            return sm;
        }
        try {
            MdModelMaterielEntity mdModelMaterielEntity = new MdModelMaterielEntity();
            mdModelMaterielEntity.setWmsModelId(wmsModelId);
            mdModelMaterielEntity = (MdModelMaterielEntity) this.getOne(mdModelMaterielEntity);
            if (mdModelMaterielEntity == null) {
                sm.setCode(3L); // 商品模型不存在
                sm.setMeg("商品模型不存在");
                return sm;
            }
            // 先删除关联的品牌
            mdModelMaterielDao.deleteMdModelBrand(wmsModelId.toString());

            mdModelMaterielEntity.setMbdId(mbdIds);
            this.updateObject(mdModelMaterielEntity);

            String[] mbdArray = mbdIds.split(",");
            Integer mbdId = null;
            MdModelBrandInfoEntity mdModelBrandInfoEntity = null;
            for (String mbdStr : mbdArray) {
                if (mbdStr.equals(""))
                    continue;
                mbdId = Integer.parseInt(mbdStr);
                mdModelBrandInfoEntity = new MdModelBrandInfoEntity();
                mdModelBrandInfoEntity.setMbdId(mbdId);
                mdModelBrandInfoEntity.setMdModelId(wmsModelId);
                this.newObject(mdModelBrandInfoEntity);
            }
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateMdModelMaterielBrand(Integer wmsModelId, String mbdIds) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (wmsModelId == null || mbdIds == null || mbdIds.equals("")) {
            sm.setCode(2L); // 参数有问题
            sm.setMeg("参数有问题");
            return sm;
        }
        try {
            MdModelMaterielEntity mdModelMaterielEntity = new MdModelMaterielEntity();
            mdModelMaterielEntity.setWmsModelId(wmsModelId);
            mdModelMaterielEntity = (MdModelMaterielEntity) this.getOne(mdModelMaterielEntity);
            if (mdModelMaterielEntity == null) {
                sm.setCode(3L); // 商品模型不存在
                sm.setMeg("商品模型不存在");
                return sm;
            }
            // 先删除关联的品牌
            mdModelMaterielDao.deleteMdModelBrand(wmsModelId.toString());

            mdModelMaterielEntity.setMbdId(mbdIds);
            this.updateObject(mdModelMaterielEntity);

            String[] mbdArray = mbdIds.split(",");
            Integer mbdId = null;
            MdModelBrandInfoEntity mdModelBrandInfoEntity = null;
            for (String mbdStr : mbdArray) {
                if (mbdStr.equals(""))
                    continue;
                mbdId = Integer.parseInt(mbdStr);
                mdModelBrandInfoEntity = new MdModelBrandInfoEntity();
                mdModelBrandInfoEntity.setMbdId(mbdId);
                mdModelBrandInfoEntity.setMdModelId(wmsModelId);
                this.newObject(mdModelBrandInfoEntity);
            }
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveMdModelMaterielSellUnit(Integer wmsModelId, String sellUnit) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (wmsModelId == null || sellUnit == null || sellUnit.equals("")) {
            sm.setCode(2L); // 传入参数有问题
            sm.setMeg("传入参数有问题");
            return sm;
        }
        try {
            MdModelMaterielEntity mdModelMaterielEntity = new MdModelMaterielEntity();
            mdModelMaterielEntity.setWmsModelId(wmsModelId);
            mdModelMaterielEntity = (MdModelMaterielEntity) this.getOne(mdModelMaterielEntity);
            if (mdModelMaterielEntity == null) {
                sm.setCode(3L); // 商品模型不存在
                sm.setMeg("商品模型不存在");
                return sm;
            }
            mdModelMaterielEntity.setSellUnit(sellUnit);
            this.updateObject(mdModelMaterielEntity);
        } catch (Exception e){
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateMdModelMaterielSellUnit(Integer wmsModelId, String sellUnit) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (wmsModelId == null || sellUnit == null || sellUnit.equals("")) {
            sm.setCode(2L); // 传入参数有问题
            sm.setMeg("传入参数有问题");
            return sm;
        }
        try {
            MdModelMaterielEntity mdModelMaterielEntity = new MdModelMaterielEntity();
            mdModelMaterielEntity.setWmsModelId(wmsModelId);
            mdModelMaterielEntity = (MdModelMaterielEntity) this.getOne(mdModelMaterielEntity);
            if (mdModelMaterielEntity == null) {
                sm.setCode(3L); // 商品模型不存在
                sm.setMeg("商品模型不存在");
                return sm;
            }
            mdModelMaterielEntity.setSellUnit(sellUnit);
            this.updateObject(mdModelMaterielEntity);
        } catch (Exception e){
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }
}
