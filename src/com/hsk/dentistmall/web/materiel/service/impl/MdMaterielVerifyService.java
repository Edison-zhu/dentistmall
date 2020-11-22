package com.hsk.dentistmall.web.materiel.service.impl;

import com.hsk.dentistmall.api.daobbase.IMdMaterielInfoDao;
import com.hsk.dentistmall.api.daobbase.IMdMaterielVerifyDao;
import com.hsk.dentistmall.api.persistence.MdMaterielInfo;
import com.hsk.dentistmall.api.persistence.MdMaterielVerifyEntity;
import com.hsk.dentistmall.web.materiel.service.IMdMaterielVerifyService;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * author: yangfeng
 * time: 2020/7/30 14:21
 */
@Service
public class MdMaterielVerifyService extends DSTService implements IMdMaterielVerifyService {
    @Autowired
    IMdMaterielInfoDao mdMaterielInfoDao;
    @Autowired
    IMdMaterielVerifyDao mdMaterielVerifyDao;
    @Override
    public SysRetrunMessage saveMdMaterielVerifyByMateriel(MdMaterielInfo mdMaterielInfo) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            Map<String, Object> map = this.getAccountBelong();
            Integer suiId = account.getSuiId();
            Integer wmsMiId = mdMaterielInfo.getWmsMiId();
            MdMaterielVerifyEntity mdMaterielVerifyEntity = new MdMaterielVerifyEntity();
            mdMaterielVerifyEntity.setSuiId(suiId);
            mdMaterielVerifyEntity.setWmsMiId(wmsMiId);
            Integer count = mdMaterielVerifyDao.getMdMaterielVerifyCountByWmsMiId(wmsMiId, map);
            if (count > 0) {
                mdMaterielVerifyEntity = (MdMaterielVerifyEntity) this.getOne(mdMaterielVerifyEntity);
                mdMaterielVerifyEntity.setVerifyState(1);
                this.updateObject(mdMaterielVerifyEntity);
            } else {
                mdMaterielVerifyEntity.setVerifyState(1);
                this.newObject(mdMaterielVerifyEntity);
            }
            sm.setObj(mdMaterielVerifyEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveMdMaterielVerifyByMaterielVerify(MdMaterielVerifyEntity mdMaterielVerifyEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            if (Objects.equals(mdMaterielVerifyEntity.getVerifyType(), 3)) {
                //如果是报错，新增
                if (Objects.equals(mdMaterielVerifyEntity.getVerifyType(), 3))
                    mdMaterielVerifyEntity.setIsError(1);
                this.newObject(mdMaterielVerifyEntity);
            } else {
                //如果存在此条审核记录
                Integer count = mdMaterielVerifyDao.getMdMaterielVerifyCountByVerifyId(mdMaterielVerifyEntity.getMdVerifyId());
                if (count > 0) {
                    mdMaterielVerifyEntity.setVerifyState(1);
                    mdMaterielVerifyEntity.setVerifyType(1);
                    this.updateObject(mdMaterielVerifyEntity);
                } else {
                    if (Objects.equals(mdMaterielVerifyEntity.getVerifyType(), 3))
                        mdMaterielVerifyEntity.setIsError(1);
                    this.newObject(mdMaterielVerifyEntity);
                }
            }
            sm.setObj(mdMaterielVerifyEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateMaterielVerify(MdMaterielVerifyEntity mdMaterielVerifyEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            Integer count = mdMaterielVerifyDao.getMdMaterielVerifyCountByVerifyId(mdMaterielVerifyEntity.getMdVerifyId());
            if (count > 0) {
                if (Objects.equals(mdMaterielVerifyEntity.getVerifyType(), 3) && Objects.equals(mdMaterielVerifyEntity.getVerifyState(), 2)) {
                    Integer suiId = mdMaterielVerifyEntity.getSuiId();
                    // 报错审核返回给送审人消息
                }
                this.updateObject(mdMaterielVerifyEntity);
            } else
                sm.setCode(2L); // 不存在审核记录
            sm.setObj(mdMaterielVerifyEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateMdMaterielVerifyType(Integer mdVerifyId, Integer verifyType) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            Integer count = mdMaterielVerifyDao.getMdMaterielVerifyCountByVerifyId(mdVerifyId);
            if (count > 0) {
                MdMaterielVerifyEntity mdMaterielVerifyEntity = new MdMaterielVerifyEntity();
                mdMaterielVerifyEntity.setMdVerifyId(mdVerifyId);
                mdMaterielVerifyEntity = (MdMaterielVerifyEntity) this.getOne(mdMaterielVerifyEntity);
                mdMaterielVerifyEntity.setVerifyType(verifyType);
                if (1 <= verifyType && verifyType <= 4) // 待审核
                    mdMaterielVerifyEntity.setVerifyState(1);
                if (Objects.equals(verifyType, 5)) // 驳回
                    mdMaterielVerifyEntity.setVerifyState(3);
                if (Objects.equals(verifyType, 6)) // 通过
                    mdMaterielVerifyEntity.setVerifyState(2);
                this.updateObject(mdMaterielVerifyEntity);
                sm.setObj(mdMaterielVerifyEntity);
            } else
                sm.setCode(2L); // 不存在审核记录

        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateMdMaterielVerifyState(Integer mdVerifyId, Integer verifyState) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            Integer count = mdMaterielVerifyDao.getMdMaterielVerifyCountByVerifyId(mdVerifyId);
            if (count > 0) {
                MdMaterielVerifyEntity mdMaterielVerifyEntity = new MdMaterielVerifyEntity();
                mdMaterielVerifyEntity.setMdVerifyId(mdVerifyId);
                mdMaterielVerifyEntity = (MdMaterielVerifyEntity) this.getOne(mdMaterielVerifyEntity);
                mdMaterielVerifyEntity.setVerifyState(verifyState);
                this.updateObject(mdMaterielVerifyEntity);
                sm.setObj(mdMaterielVerifyEntity);
            } else
                sm.setCode(2L); // 不存在审核记录
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getMdMaterielVerifyTypeCount() throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            Integer allCount = mdMaterielVerifyDao.getMdMaterielVerifyCount(null);
            Integer newMaterielCount = mdMaterielVerifyDao.getMdMaterielVerifyCount(1);
            Integer newFormatCount = mdMaterielVerifyDao.getMdMaterielVerifyCount(2);
            Integer errorCount = mdMaterielVerifyDao.getMdMaterielVerifyCount(3);
            Integer breakRuleCount = mdMaterielVerifyDao.getMdMaterielVerifyCount(4);
            Integer rejectCount = mdMaterielVerifyDao.getMdMaterielVerifyCount(5);
            Integer passCount = mdMaterielVerifyDao.getMdMaterielVerifyCount(6);
            Map<String, Integer> map = new HashMap<>();
            map.put("allCount", allCount);
            map.put("newMaterielCount", newMaterielCount);
            map.put("newFormatCount", newFormatCount);
            map.put("errorCount", errorCount);
            map.put("breakRuleCount", breakRuleCount);
            map.put("rejectCount", rejectCount);
            map.put("passCount", passCount);
            sm.setObj(map);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public PagerModel getMdMaterielVerifyPagerModel(MdMaterielVerifyEntity mdMaterielVerifyEntity) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList());
        try {
            // 需要联合查询
            pm = mdMaterielVerifyDao.getMdMaterielVerifyPagerModel(mdMaterielVerifyEntity);


            List<MdMaterielVerifyEntity> list = pm.getItems();
            List<Map<String, Integer>> mapList = new ArrayList<>();
            Map<String, Integer> map;
            String wmsMiIds = "";
            String wmsModelIds = "";
            for (MdMaterielVerifyEntity entity : list) {
                map = new HashMap<>();
                if (Objects.equals(entity.getVerifyType(), 3))
                    wmsModelIds += mdMaterielVerifyEntity.getWmsModelId() + ",";
                else
                    wmsMiIds += mdMaterielVerifyEntity.getWmsMiId() + ",";
            }

            if (!wmsModelIds.equals(""))
                wmsModelIds = wmsModelIds.substring(0, wmsModelIds.length() - 1);
            if (!wmsMiIds.equals(""))
                wmsMiIds = wmsMiIds.substring(0, wmsMiIds.length() - 1);
            // 获取商品的信息

            // 获取商品模型的信息

            // 整合两个数据

            // 返回整合后的数据
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage getMdMaterielVerifyList(MdMaterielVerifyEntity mdMaterielVerifyEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            List<MdMaterielVerifyEntity> list = mdMaterielVerifyDao.getMdMaterielVerifyList(mdMaterielVerifyEntity);
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }
}
