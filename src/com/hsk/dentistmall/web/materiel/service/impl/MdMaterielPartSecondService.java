package com.hsk.dentistmall.web.materiel.service.impl;

import com.hsk.dentistmall.api.daobbase.IMdMaterielPartDao;
import com.hsk.dentistmall.api.daobbase.IMdMaterielPartSecondDao;
import com.hsk.dentistmall.api.persistence.MdMaterielInfo;
import com.hsk.dentistmall.api.persistence.MdMaterielPartEntity;
import com.hsk.dentistmall.api.persistence.MdMaterielPartSecondEntity;
import com.hsk.dentistmall.web.materiel.service.IMdMaterielPartSecondService;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/5/12 9:29
 */
@Service
public class MdMaterielPartSecondService extends DSTService implements IMdMaterielPartSecondService {
    @Autowired
    private IMdMaterielPartSecondDao mdMaterielPartSecondDao;

    @Override
    public SysRetrunMessage findFormObject(Integer mdpId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            MdMaterielPartSecondEntity mdMaterielPartSecondEntity = new MdMaterielPartSecondEntity();
            mdMaterielPartSecondEntity.setMdpsId(mdpId);
            mdMaterielPartSecondEntity = mdMaterielPartSecondDao.findMdMateriel(mdMaterielPartSecondEntity);
            sm.setObj(mdMaterielPartSecondEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public MdMaterielPartSecondEntity findObject(Integer mdpId) throws HSKException {
        MdMaterielPartSecondEntity mdMaterielPartSecondEntity = new MdMaterielPartSecondEntity();
        try {
            mdMaterielPartSecondEntity.setMdpsId(mdpId);
            mdMaterielPartSecondEntity = mdMaterielPartSecondDao.findMdMateriel(mdMaterielPartSecondEntity);
        } catch (Exception e) {
            mdMaterielPartSecondEntity = null;
            e.printStackTrace();
        }
        return mdMaterielPartSecondEntity;
    }

    @Override
    public SysRetrunMessage deleteObject(Integer mdpId, Integer mdpsId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            Integer count = mdMaterielPartSecondDao.getMaterielInfoCount(mdpId, mdpsId.toString());
            if (count > 0) {
                sm.setCode(2L);
                return sm;
            }
            mdMaterielPartSecondDao.deleteObjects(mdpId, mdpsId.toString());
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteAllObject(Integer mdpId, String mdpsIds) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            Integer count = mdMaterielPartSecondDao.getMaterielInfoCount(mdpId, mdpsIds);
            if (count > 0) {
                sm.setCode(2L);
                return sm;
            }
            mdMaterielPartSecondDao.deleteObjects(mdpId, mdpsIds);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveOrUpdateObject(MdMaterielPartSecondEntity mdMaterielPartSecondEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (mdMaterielPartSecondEntity.getMdpId() == null) {
            sm.setCode(3L);
            return sm;
        }
        if (mdMaterielPartSecondEntity.getMdpsId() == null) {
            sm.setCode(2L);
            return sm;
        }
        try {
            SysUserInfo sysUserInfo = this.GetOneSessionAccount();
            mdMaterielPartSecondEntity.setEditDate(new Date());
            mdMaterielPartSecondEntity.setEditRen(sysUserInfo.getUserName());
            mdMaterielPartSecondEntity = mdMaterielPartSecondDao.saveOrUpdateMaterielPart(mdMaterielPartSecondEntity);
            sm.setObj(mdMaterielPartSecondEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveObject(MdMaterielPartSecondEntity mdMaterielPartSecondEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (mdMaterielPartSecondEntity.getMdpId() == null) {
            sm.setCode(2L);
            return sm;
        }
        try {
            MdMaterielPartSecondEntity mdMaterielPartEntity1 = mdMaterielPartSecondDao.findMdMateriel(mdMaterielPartSecondEntity);
            if (mdMaterielPartEntity1 != null) {
                sm.setCode(2L); // 存在同名
                return sm;
            }
            Integer maxSeq = mdMaterielPartSecondDao.getMaterielPartMaxSeq(mdMaterielPartSecondEntity.getMdpId());
            if (maxSeq == null)
                maxSeq = -1;
            mdMaterielPartSecondEntity.setSeq(maxSeq + 1);
            String mps = CreateCodeUtil.getNo("MPS");
            mdMaterielPartSecondEntity.setMdpsCode(mps);
            Integer mdpId = mdMaterielPartSecondEntity.getMdpId();
            MdMaterielPartEntity mdMaterielPartEntity = new MdMaterielPartEntity();
            mdMaterielPartEntity.setMdpId(mdpId);
            mdMaterielPartEntity = (MdMaterielPartEntity) this.getOne(mdMaterielPartEntity);
//            Integer mdpsId = mdMaterielPartSecondEntity.getMdpsId();
//            Integer count = mdMaterielPartSecondDao.getMaterielPartSecondCount(mdpId);
            Map<String, Object> map = mdMaterielPartSecondDao.getMdMaterielPartSecondLatest(mdpId, mdMaterielPartEntity.getRbaId(), mdMaterielPartEntity.getRbsId(), mdMaterielPartEntity.getRbbId());
            Integer count = 1;
            if (map == null || map.isEmpty())
                count = 1;
            else {
                String c = map.get("second_part_code").toString();
                c = c.split("-")[1];
                Integer cc = Integer.parseInt(c);
                count = cc + 1;
            }
            mdMaterielPartSecondEntity.setSecondPartCode(mdMaterielPartEntity.getPartCode() + "-" + count);
            this.newObject(mdMaterielPartSecondEntity);
//            this.updateObject(mdMaterielPartSecondEntity);
            sm.setObj(mdMaterielPartSecondEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public PagerModel getPagerModelObject(MdMaterielPartSecondEntity mdMaterielPartSecondEntity) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<MdMaterielPartSecondEntity>());
        try {
            pm = mdMaterielPartSecondDao.getMaterielPartPagerModel(mdMaterielPartSecondEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage getMdMaterielPartList(MdMaterielPartSecondEntity mdMaterielPartSecondEntity, Integer limit, Integer page) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            List<Map<String, Object>> list = mdMaterielPartSecondDao.getMaterielPartMapList(mdMaterielPartSecondEntity, limit, page);
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateMaterielPartSecondSeq(Integer mdpsIdBefore, Integer upDown) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            MdMaterielPartSecondEntity mdMaterielPartSecondEntity = new MdMaterielPartSecondEntity();
            mdMaterielPartSecondEntity.setMdpsId(mdpsIdBefore);
            mdMaterielPartSecondEntity = mdMaterielPartSecondDao.findMdMateriel(mdMaterielPartSecondEntity);

            Map<String, Object> map = mdMaterielPartSecondDao.finMdMaterielPartSecondByUpDown(upDown, mdMaterielPartSecondEntity.getSeq(), null);
            if (map == null && map.isEmpty()) {
                sm.setCode(2L);
                return sm;
            }
            Integer mdpsIdAfter = Integer.parseInt(map.get("mdps_id").toString());
            Integer seqAfter = Integer.parseInt(map.get("seq").toString());

            mdMaterielPartSecondDao.saveOrUpdateMaterielPartSeq(mdpsIdBefore, mdMaterielPartSecondEntity.getSeq(), mdpsIdAfter, seqAfter);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();;
        }
        return sm;
    }

    @Override
    public SysRetrunMessage updateMaterielPartSecondSeqBatch(Integer mdpsIdBefore, Integer upDown, Integer length) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            MdMaterielPartSecondEntity mdMaterielPartSecondEntity = new MdMaterielPartSecondEntity();
            mdMaterielPartSecondEntity.setMdpsId(mdpsIdBefore);
            mdMaterielPartSecondEntity = mdMaterielPartSecondDao.findMdMateriel(mdMaterielPartSecondEntity);

            Map<String, Object> map = mdMaterielPartSecondDao.finMdMaterielPartSecondByUpDown(upDown, mdMaterielPartSecondEntity.getSeq(), length);
            if (map == null) {
                sm.setCode(2L);
                return sm;
            }
            Integer mdpsIdAfter = Integer.parseInt(map.get("mdps_id").toString());
            Integer seqAfter = Integer.parseInt(map.get("seq").toString());
            mdMaterielPartSecondDao.saveOrUpdateMaterielPartSeqBatch(mdpsIdBefore, mdMaterielPartSecondEntity.getSeq(), mdpsIdAfter, seqAfter);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();;
        }
        return sm;
    }
}
