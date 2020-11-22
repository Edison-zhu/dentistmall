package com.hsk.xframe.web.sysunitparam;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUnitParamEntity;

import java.util.List;

/**
 * author: yangfeng
 * time: 2020/5/9 16:19
 */
public interface ISysUnitParamService {

    SysRetrunMessage saveObject(SysUnitParamEntity sysUnitParamEntity) throws HSKException;

    /**
     * 根据单位id查询
     *
     * @param upId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage findFormObject(Integer upId, Integer supId) throws HSKException;

    /**
     * 根据单位id查询
     *
     * @param upId
     * @return
     * @throws HSKException
     */
    SysUnitParamEntity findObject(Integer upId, Integer supId) throws HSKException;

    /**
     * 根据单位id删除
     *
     * @param upId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage deleteObject(Integer upId) throws HSKException;

    SysRetrunMessage deleteSysUnitParamParentObject(Integer upId) throws HSKException;

    /**
     * 删除所有包含单位id的字符串
     *
     * @param upIds
     * @return
     * @throws HSKException
     */
    SysRetrunMessage deleteAllObject(String upIds) throws HSKException;

    SysRetrunMessage deleteSysUnitParamParentObjects(String upIds) throws HSKException;

    /**
     * 更新数据或者保存数据
     *
     * @param sysUnitParamEntity
     * @return
     * @throws HSKException
     */
    SysRetrunMessage saveOrUpdateObject(SysUnitParamEntity sysUnitParamEntity) throws HSKException;

    SysRetrunMessage saveOrUpdateUnitParamParentObject(SysUnitParamEntity sysUnitParamEntity) throws HSKException;

    /**
     * 获取分页的数据
     *
     * @param sysUnitParamEntity
     * @return
     * @throws HSKException
     */
    PagerModel getPagerModelObject(SysUnitParamEntity sysUnitParamEntity) throws HSKException;

    /**
     * 获取仅仅是父类的单位
     *
     * @param sysUnitParamEntity
     * @return
     * @throws HSKException
     */
    PagerModel getSysUnitParamParentPagerModel(SysUnitParamEntity sysUnitParamEntity) throws HSKException;

    SysRetrunMessage getSysUnitParamSellUnit() throws HSKException;

    /**
     * 获取所属分类
     *
     * @param upId
     * @return
     * @throws HSKDBException
     */
    SysRetrunMessage getUnitParamBelongType(Integer upId) throws HSKDBException;

    List getUnitParamSite() throws HSKException;
}
