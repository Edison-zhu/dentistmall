package com.hsk.xframe.api.daobbase;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysUnitParamEntity;

import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/5/9 16:28
 */
public interface ISysUnitParamDao {
    void deleteSysUnitParamById(Integer upId) throws HSKDBException;

    void deleteSysUnitParamByIds(String upIds) throws HSKDBException;

    PagerModel getPagetModelByObject(SysUnitParamEntity sysUnitParamEntity) throws HSKDBException;

    PagerModel getSysUnitParamParentPagerModel(SysUnitParamEntity sysUnitParamEntity) throws HSKDBException;

    SysUnitParamEntity saveOrUpdateSysUnitParam(SysUnitParamEntity sysUnitParamEntity) throws HSKDBException;

    SysUnitParamEntity findSysUnitParam(Integer upId, Integer supId) throws HSKDBException;

    SysUnitParamEntity findSysUnitParamParentByName(SysUnitParamEntity sysUnitParamEntity) throws HSKDBException;

    List<Map<String, Object>> getUnitParamBelongTypeByIdOrAll(Integer upId) throws HSKDBException;

    SysUnitParamEntity findSysUnitParamByName(SysUnitParamEntity sysUnitParamEntity) throws HSKDBException;

    List<SysUnitParamEntity> findSysUnitParamChild(Integer upId) throws HSKDBException;

    List findSitePart() throws HSKDBException;

    Integer getSysUnitParamParentCount() throws HSKDBException;
    Integer getSysUnitParamChildCount(Integer supId) throws HSKDBException;
    List findSysUnitParamChildTree(Integer upId) throws HSKDBException;
}
