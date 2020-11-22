package com.hsk.dentistmall.web.materiel.service.impl;

import com.hsk.dentistmall.api.daobbase.IMdMaterielPartDao;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.web.materiel.service.IMdMaterielPartService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.IorgDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * author: yangfeng
 * time: 2020/5/12 8:12
 */
@Service
public class MdMaterielPartService extends DSTService implements IMdMaterielPartService {
    @Autowired
    private IMdMaterielPartDao mdMaterielPartDao;
    @Autowired
    protected IorgDao orgDao;
    @Override
    public SysRetrunMessage findFormObject(Integer mdpId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            MdMaterielPartEntity mdMaterielPartEntity = new MdMaterielPartEntity();
            mdMaterielPartEntity.setMdpId(mdpId);
            mdMaterielPartEntity = mdMaterielPartDao.findMdMateriel(mdMaterielPartEntity);
            sm.setObj(mdMaterielPartEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public MdMaterielPartEntity findObject(Integer mdpId) throws HSKException {
        MdMaterielPartEntity mdMaterielPartEntity = new MdMaterielPartEntity();
        try {
            mdMaterielPartEntity.setMdpId(mdpId);
            mdMaterielPartEntity = mdMaterielPartDao.findMdMateriel(mdMaterielPartEntity);
        } catch (Exception e) {
            mdMaterielPartEntity = null;
            e.printStackTrace();
        }
        return mdMaterielPartEntity;
    }

    @Override
    public SysRetrunMessage deleteObject(Integer mdpId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            MdMaterielPartEntity mdMaterielPartEntity = new MdMaterielPartEntity();
            mdMaterielPartEntity.setMdpId(mdpId);
            mdMaterielPartEntity = (MdMaterielPartEntity) this.getOne(mdMaterielPartEntity);
            if (mdMaterielPartEntity != null && Objects.equals(mdMaterielPartEntity.getSeq(), -1)) {
                sm.setCode(4L);
                return sm;
            }
            Integer count = mdMaterielPartDao.getMaterielPartSecondByMdpIdCount(mdpId);
            if (count > 0) {
                sm.setCode(2L); // 存在二级分类
                return sm;
            }
            count = mdMaterielPartDao.getMaterielInfoCount(mdpId.toString());
            if (count > 0) {
                sm.setCode(3L);
                return sm;
            }
            mdMaterielPartDao.deleteObjects(mdpId.toString());
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteAllObject(String mdpIds) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            String[] mdpIdsList = mdpIds.split(",");
            Integer mdpId;
            Integer count;
            for (String mdpIdS : mdpIdsList) {
                mdpId = Integer.parseInt(mdpIdS);
                count = mdMaterielPartDao.getMaterielPartSecondByMdpIdCount(mdpId);
                if (count > 0) {
                    sm.setCode(2L); // 存在二级分类
                    return sm;
                }
            }
            count = mdMaterielPartDao.getMaterielInfoCount(mdpIds);
            if (count > 0) {
                sm.setCode(3L);
                return sm;
            }
            mdMaterielPartDao.deleteObjects(mdpIds);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveOrUpdateObject(MdMaterielPartEntity mdMaterielPartEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (mdMaterielPartEntity.getMdpId() == null) {
            sm.setCode(2L);
            return sm;
        }
        try {
            SysUserInfo sysUserInfo = this.GetOneSessionAccount();
            mdMaterielPartEntity.setEditDate(new Date());
            mdMaterielPartEntity.setEditRen(sysUserInfo.getUserName());
            mdMaterielPartEntity = mdMaterielPartDao.saveOrUpdateMaterielPart(mdMaterielPartEntity);
            sm.setObj(mdMaterielPartEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveObject(MdMaterielPartEntity mdMaterielPartEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                rbaId = account.getOldId();
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    rbaId = Integer.parseInt(orgMap.get("one"));
                }
                rbsId = account.getOldId();
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    rbaId = Integer.parseInt(orgMap.get("one"));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    rbsId = Integer.parseInt(orgMap.get("tow"));
                }
                rbbId = account.getOldId();
            }
            mdMaterielPartEntity.setRbaId(rbaId);
            mdMaterielPartEntity.setRbsId(rbsId);
            mdMaterielPartEntity.setRbbId(rbbId);
            MdMaterielPartEntity mdMaterielPartEntity1 = mdMaterielPartDao.findMdMateriel(mdMaterielPartEntity);
            if (mdMaterielPartEntity1 != null) {
                sm.setCode(2L); // 存在同名
                return sm;
            }


            Integer maxSeq = mdMaterielPartDao.getMaterielPartMaxSeq(mdMaterielPartEntity);
            if (maxSeq == null)
                maxSeq = -1;
            mdMaterielPartEntity.setSeq(maxSeq + 1);

            String mp = CreateCodeUtil.getNo("MP");
            mdMaterielPartEntity.setMdpCode(mp);
            Integer mdpId = mdMaterielPartEntity.getMdpId();
            Map<String, Object> map = mdMaterielPartDao.getMdMaterielPartLatest(mdMaterielPartEntity.getRbaId(), mdMaterielPartEntity.getRbsId(), mdMaterielPartEntity.getRbbId());
            Integer count = 1;
            if (map == null || map.isEmpty())
                count = 1;
            else {
                String c = map.get("part_code").toString();
                Integer cc = Integer.parseInt(c);
                count = cc + 1;
            }
            mdMaterielPartEntity.setPartCode(String.format("%0" + 3 + "d", count));
            this.newObject(mdMaterielPartEntity);
//            this.updateObject(mdMaterielPartEntity);
            sm.setObj(mdMaterielPartEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public PagerModel getPagerModelObject(MdMaterielPartEntity mdMaterielPartEntity) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdMaterielPartEntity>());
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
//            Integer rbaId = null;
//            Integer rbsId = null;
//            Integer rbbId = null;
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
//                rbaId = account.getOldId();
                mdMaterielPartEntity.setRbaId(account.getOldId());
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
//                    rbaId = Integer.parseInt(orgMap.get("one"));
                    mdMaterielPartEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
//                rbsId = account.getOldId();
                mdMaterielPartEntity.setRbsId(account.getOldId());
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
//                    rbaId = Integer.parseInt(orgMap.get("one"));
                    mdMaterielPartEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
//                    rbsId = Integer.parseInt(orgMap.get("tow"));
                    mdMaterielPartEntity.setRbaId(Integer.parseInt(orgMap.get("tow")));
                }
//                rbbId = account.getOldId();
                mdMaterielPartEntity.setRbbId(account.getOldId());
            }
            pm = mdMaterielPartDao.getMaterielPartPagerModel(mdMaterielPartEntity);
            // 新增默认
            List<MdMaterielPartEntity> list = pm.getItems();
            if (list.isEmpty()) {
                this.insertPartAlone(account, orgMap);
                pm = mdMaterielPartDao.getMaterielPartPagerModel(mdMaterielPartEntity);
            } else {
                boolean needAdd = false;
                for (MdMaterielPartEntity entity : list) {
                    if (Objects.equals(entity.getSeq(), -1)) {
                        needAdd = false;
                        break;
                    }
                    needAdd = true;
                }
                if (needAdd == true) {
                    this.insertPartAlone(account, orgMap);
                    pm = mdMaterielPartDao.getMaterielPartPagerModel(mdMaterielPartEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    private void insertPartAlone(SysUserInfo account, Map<String, String> orgMap) {
        try {
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                rbaId = account.getOldId();
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    rbaId = Integer.parseInt(orgMap.get("one"));
                }
                rbsId = account.getOldId();
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    rbaId = Integer.parseInt(orgMap.get("one"));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    rbsId = Integer.parseInt(orgMap.get("tow"));
                }
                rbbId = account.getOldId();
            }
            MdMaterielPartEntity mdMaterielPartEntity1 = new MdMaterielPartEntity();
            mdMaterielPartEntity1.setRbaId(rbaId);
            mdMaterielPartEntity1.setRbsId(rbsId);
            mdMaterielPartEntity1.setRbbId(rbbId);
            mdMaterielPartEntity1.setSeq(-1);
            mdMaterielPartEntity1.setMdpName("默认");
            mdMaterielPartEntity1.setNeedShow(0);
            String mp = CreateCodeUtil.getNo("MP");
            mdMaterielPartEntity1.setMdpCode(mp);
            this.newObject(mdMaterielPartEntity1);
            Integer mdpId = mdMaterielPartEntity1.getMdpId();
            mdMaterielPartEntity1.setPartCode(String.format("%0" + 3 + "d", 1));
            this.updateObject(mdMaterielPartEntity1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SysRetrunMessage getMdMaterielPartList(MdMaterielPartEntity mdMaterielPartEntity, Integer limit, Integer page) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            List<Map<String, Object>> list = mdMaterielPartDao.getMaterielPartMapList(mdMaterielPartEntity, limit, page);
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public List getFirstObject(Integer mdpId, Integer mdpsId) throws HSKException {
        List result = null;
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                rbaId = account.getOldId();
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    rbaId = Integer.parseInt(orgMap.get("one"));
                }
                rbsId = account.getOldId();
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    rbaId = Integer.parseInt(orgMap.get("one"));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    rbsId = Integer.parseInt(orgMap.get("tow"));
                }
                rbbId = account.getOldId();
            }
            result = mdMaterielPartDao.findSitePart(mdpId, mdpsId, rbaId, rbsId, rbbId);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List getMdMaterielPartList1() throws HSKException {
        List result = null;
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                rbaId = account.getOldId();
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    rbaId = Integer.parseInt(orgMap.get("one"));
                }
                rbsId = account.getOldId();
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    rbaId = Integer.parseInt(orgMap.get("one"));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    rbsId = Integer.parseInt(orgMap.get("tow"));
                }
                rbbId = account.getOldId();
            }
            result = mdMaterielPartDao.getMdMaterielPartList(rbaId, rbsId, rbbId);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public SysRetrunMessage updateMaterielPartSeq(Integer mdpIdBefore, Integer upDown) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo account = this.GetOneSessionAccount();
            SysOrgGx sysOrgGx = new SysOrgGx();
            sysOrgGx.setOrgGxId(account.getOrgGxId());
            Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
            Integer rbaId = null;
            Integer rbsId = null;
            Integer rbbId = null;
            if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
                rbaId = account.getOldId();
            } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    rbaId = Integer.parseInt(orgMap.get("one"));
                }
                rbsId = account.getOldId();
            } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
                if (orgMap.containsKey("one")) {//如果存在上级集团
                    rbaId = Integer.parseInt(orgMap.get("one"));
                }
                if (orgMap.containsKey("tow")) {//如果存在上级医院
                    rbsId = Integer.parseInt(orgMap.get("tow"));
                }
                rbbId = account.getOldId();
            }

            MdMaterielPartEntity mdMaterielPartEntity = new MdMaterielPartEntity();
            mdMaterielPartEntity.setMdpId(mdpIdBefore);
            mdMaterielPartEntity = mdMaterielPartDao.findMdMateriel(mdMaterielPartEntity);

            Map<String, Object> map = mdMaterielPartDao.finMdMaterielPartByUpDown(upDown, mdMaterielPartEntity, null);
            if (map == null && map.isEmpty()) {
                sm.setCode(2L);
                return sm;
            }
            Integer mdpIdAfter = Integer.parseInt(map.get("mdp_id").toString());
            Integer seqAfter = Integer.parseInt(map.get("seq").toString());

            mdMaterielPartDao.saveOrUpdateMaterielPartSeq(mdpIdBefore, mdMaterielPartEntity.getSeq(), mdpIdAfter, seqAfter, mdMaterielPartEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();;
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateMaterielPartSeqBatch(Integer mdpIdBefore, Integer upDown, Integer length) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            MdMaterielPartEntity mdMaterielPartEntity = new MdMaterielPartEntity();
            mdMaterielPartEntity.setMdpId(mdpIdBefore);
            mdMaterielPartEntity = mdMaterielPartDao.findMdMateriel(mdMaterielPartEntity);

            Map<String, Object> map = mdMaterielPartDao.finMdMaterielPartByUpDown(upDown, mdMaterielPartEntity, length);
            if (map == null) {
                sm.setCode(2L);
                return sm;
            }
            Integer mdpIdAfter = Integer.parseInt(map.get("mdp_id").toString());
            Integer seqAfter = Integer.parseInt(map.get("seq").toString());
            mdMaterielPartDao.saveOrUpdateMaterielPartSeqBatch(mdpIdBefore, mdMaterielPartEntity.getSeq(), mdpIdAfter, seqAfter, mdMaterielPartEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateMaterielPartNeedShow(Integer mdpId, Integer needShow) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            MdMaterielPartEntity mdMaterielPartEntity = new MdMaterielPartEntity();
            mdMaterielPartEntity.setMdpId(mdpId);
            mdMaterielPartEntity = mdMaterielPartDao.findMdMateriel(mdMaterielPartEntity);
            if (mdMaterielPartEntity == null) {
                sm.setCode(0L);
                return sm;
            }
            mdMaterielPartEntity.setNeedShow(needShow);
            this.updateObject(mdMaterielPartEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public PagerModel getMaterielPartNormPagerModel(Integer mdpId, Integer mdpsId, String searchName) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdMaterielNormDetailViewEntity>());
        try {
            pm = mdMaterielPartDao.getMaterielNormDetailPagerModel(mdpId, mdpsId, searchName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }
}
