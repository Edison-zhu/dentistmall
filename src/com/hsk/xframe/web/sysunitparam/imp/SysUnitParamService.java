package com.hsk.xframe.web.sysunitparam.imp;

import com.hsk.dentistmall.api.persistence.MdDeliveryAddress;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.ISysSameProductDao;
import com.hsk.xframe.api.daobbase.ISysUnitParamDao;
import com.hsk.xframe.api.persistence.SysUnitParamEntity;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.xframe.web.sysunitparam.ISysUnitParamService;
import org.jbpm.pvm.internal.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/5/9 16:20
 */
@Service
public class SysUnitParamService extends DSTService implements ISysUnitParamService {
    @Autowired
    private ISysUnitParamDao sysUnitParamDao;

    @Override
    public SysRetrunMessage saveObject(SysUnitParamEntity sysUnitParamEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (sysUnitParamEntity == null) {
            sm.setCode(0L);
            return sm;
        }
        try {
            this.newObject(sysUnitParamEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage findFormObject(Integer upId, Integer supId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUnitParamEntity sysUnitParamEntity = sysUnitParamDao.findSysUnitParam(upId, supId);
            sm.setObj(sysUnitParamEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysUnitParamEntity findObject(Integer upId, Integer supId) throws HSKException {
        SysUnitParamEntity sysUnitParamEntity = null;
        try {
            sysUnitParamEntity = sysUnitParamDao.findSysUnitParam(upId, supId);
        } catch (Exception e) {
            sysUnitParamEntity = null;
            e.printStackTrace();
        }
        return sysUnitParamEntity;
    }

    @Override
    public SysRetrunMessage deleteObject(Integer upId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (upId == null) {
            sm.setCode(0L);
            return sm;
        }
        try {
            sysUnitParamDao.deleteSysUnitParamById(upId);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteSysUnitParamParentObject(Integer upId) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (upId == null) {
            sm.setCode(0L);
            return sm;
        }
        try {
            List<SysUnitParamEntity> list = sysUnitParamDao.findSysUnitParamChild(upId);
            if (!list.isEmpty()) {
                sm.setCode(2L);//存在单位不允许删除
                return sm;
            }
            sysUnitParamDao.deleteSysUnitParamById(upId);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteAllObject(String upIds) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (upIds == null || upIds.equals("")) {
            sm.setCode(0L);
            return sm;
        }
        try {
            // 需要判断是否被引用，被引用则不能被删除
            sysUnitParamDao.deleteSysUnitParamByIds(upIds);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage deleteSysUnitParamParentObjects(String upIds) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        if (upIds == null || upIds.equals("")) {
            sm.setCode(0L);
            return sm;
        }
        try {
            String[] upIdList = upIds.split(",");
            List<SysUnitParamEntity> list;
            boolean canDelete = true;
            for (String upId : upIdList) {
                list = sysUnitParamDao.findSysUnitParamChild(Integer.parseInt(upId));
                if (!list.isEmpty()) {
                    canDelete = false;
                    break;
                }
            }
            if (!canDelete) {
                sm.setCode(2L);//存在单位不允许删除
                return sm;
            }
            sysUnitParamDao.deleteSysUnitParamByIds(upIds);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveOrUpdateObject(SysUnitParamEntity sysUnitParamEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try { SysUserInfo sysUserInfo = this.GetOneSessionAccount();
            if (sysUnitParamEntity.getUpId() == null || sysUnitParamEntity.getUpId().equals("")) {
                SysUnitParamEntity sysUnitParamEntity1 = sysUnitParamDao.findSysUnitParamByName(sysUnitParamEntity);
                if (sysUnitParamEntity1 != null) {
                    sm.setCode(2L); // 不能重名
                    return sm;
                }
                sysUnitParamEntity.setUnitCode(CreateCodeUtil.getNo("UP"));
                sysUnitParamEntity.setCreateDate(new Date());
                sysUnitParamEntity.setCreateRen(sysUserInfo.getUserName());
                sysUnitParamEntity = sysUnitParamDao.saveOrUpdateSysUnitParam(sysUnitParamEntity);
            } else {
                SysUnitParamEntity sysUnitParamEntity1 = sysUnitParamDao.findSysUnitParam(sysUnitParamEntity.getUpId(), null);
                sysUnitParamEntity1.setUnitName(sysUnitParamEntity.getUnitName());
                sysUnitParamEntity1.setBelongType(sysUnitParamEntity.getBelongType());
                sysUnitParamEntity1.setNeedShow(sysUnitParamEntity.getNeedShow());
                sysUnitParamEntity1.setEditeDate(new Date());
                sysUnitParamEntity1.setEditeRen(sysUserInfo.getUserName());
                sysUnitParamEntity = sysUnitParamDao.saveOrUpdateSysUnitParam(sysUnitParamEntity1);
            }
            sm.setObj(sysUnitParamEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage saveOrUpdateUnitParamParentObject(SysUnitParamEntity sysUnitParamEntity) throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUserInfo sysUserInfo = this.GetOneSessionAccount();
            if (sysUnitParamEntity.getUpId() == null) {
                SysUnitParamEntity sysUnitParamEntity1 = sysUnitParamDao.findSysUnitParamParentByName(sysUnitParamEntity);
                if (sysUnitParamEntity1 != null) {
                    sm.setCode(2L); // 不能重名
                    return sm;
                }
                sysUnitParamEntity.setUnitCode(CreateCodeUtil.getNo("UP"));
                sysUnitParamEntity.setCreateDate(new Date());
                sysUnitParamEntity.setCreateRen(sysUserInfo.getUserName());
                sysUnitParamEntity = sysUnitParamDao.saveOrUpdateSysUnitParam(sysUnitParamEntity);
            } else {
                SysUnitParamEntity sysUnitParamEntity1 = sysUnitParamDao.findSysUnitParam(sysUnitParamEntity.getUpId(), null);
                sysUnitParamEntity1.setUnitName(sysUnitParamEntity.getUnitName());
                sysUnitParamEntity1.setBelongType(sysUnitParamEntity.getBelongType());
                sysUnitParamEntity1.setNeedShow(sysUnitParamEntity.getNeedShow());
                sysUnitParamEntity1.setEditeDate(new Date());
                sysUnitParamEntity1.setEditeRen(sysUserInfo.getUserName());
                sysUnitParamEntity = sysUnitParamDao.saveOrUpdateSysUnitParam(sysUnitParamEntity1);
            }
            sm.setObj(sysUnitParamEntity);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public PagerModel getPagerModelObject(SysUnitParamEntity sysUnitParamEntity) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<SysUnitParamEntity>());
        try {
            pm = sysUnitParamDao.getPagetModelByObject(sysUnitParamEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public PagerModel getSysUnitParamParentPagerModel(SysUnitParamEntity sysUnitParamEntity) throws HSKException {
        PagerModel pm = new PagerModel(new ArrayList<SysUnitParamEntity>());
        try {
            pm = sysUnitParamDao.getSysUnitParamParentPagerModel(sysUnitParamEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }

    @Override
    public SysRetrunMessage getSysUnitParamSellUnit() throws HSKException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            SysUnitParamEntity sysUnitParamEntity = new SysUnitParamEntity();
//            sysUnitParamEntity.setUnitName("计数单位");
            sysUnitParamEntity.setUnitCode("UP200608082116208");
            sysUnitParamEntity = sysUnitParamDao.findSysUnitParamByName(sysUnitParamEntity);
            if (sysUnitParamEntity.getUpId() == null)
                return sm;
            List<SysUnitParamEntity> list = sysUnitParamDao.findSysUnitParamChild(sysUnitParamEntity.getUpId());
            sm.setObj(list);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public SysRetrunMessage getUnitParamBelongType(Integer upId) throws HSKDBException {
        SysRetrunMessage sm = new SysRetrunMessage(1L);
        try {
            List<Map<String, Object>> mapList = sysUnitParamDao.getUnitParamBelongTypeByIdOrAll(upId);
            sm.setObj(mapList);
        } catch (Exception e) {
            sm.setCode(0L);
            e.printStackTrace();
        }
        return sm;
    }

    @Override
    public List getUnitParamSite() throws HSKException {
        List result = null;
        try {
            result = sysUnitParamDao.findSitePart();
        } catch (HSKDBException e) {
            e.printStackTrace();
        }

        return result;
    }
}
