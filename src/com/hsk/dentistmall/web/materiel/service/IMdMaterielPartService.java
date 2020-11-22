package com.hsk.dentistmall.web.materiel.service;

import com.hsk.dentistmall.api.persistence.MdMaterielFormat;
import com.hsk.dentistmall.api.persistence.MdMaterielPartEntity;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import org.jbpm.pvm.internal.query.Page;

import java.util.List;

/**
 * author: yangfeng
 * time: 2020/5/12 8:12
 */
public interface IMdMaterielPartService {

    /**
     * 根据物料分类id查询
     *
     * @param mdpId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage findFormObject(Integer mdpId) throws HSKException;

    /**
     * 根据物料分类id查询
     *
     * @param mdpId
     * @return
     * @throws HSKException
     */
    MdMaterielPartEntity findObject(Integer mdpId) throws HSKException;

    /**
     * 根据物料分类id删除
     *
     * @param mdpId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage deleteObject(Integer mdpId) throws HSKException;

    /**
     * 根据物料分类ids删除
     *
     * @param mdpIds
     * @return
     * @throws HSKException
     */
    SysRetrunMessage deleteAllObject(String mdpIds) throws HSKException;

    /**
     * 保存或者更新
     *
     * @param mdMaterielPartEntity
     * @return
     * @throws HSKException
     */
    SysRetrunMessage saveOrUpdateObject(MdMaterielPartEntity mdMaterielPartEntity) throws HSKException;

    SysRetrunMessage saveObject(MdMaterielPartEntity mdMaterielPartEntity) throws HSKException;

    /**
     * 返回pagermodel数据
     *
     * @param mdMaterielPartEntity
     * @return
     * @throws HSKException
     */
    PagerModel getPagerModelObject(MdMaterielPartEntity mdMaterielPartEntity) throws HSKException;

    /**
     * 获取list
     * @param mdMaterielPartEntity
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getMdMaterielPartList(MdMaterielPartEntity mdMaterielPartEntity, Integer limit, Integer page) throws HSKException;

    List getFirstObject(Integer mdpId, Integer mdpsId) throws HSKException;
    List getMdMaterielPartList1() throws HSKException;

    /**
     * 上移下移
     * @param mdpIdBefore 主动移动的id
     * @param upDown 移动方向
     * @return
     * @throws HSKException
     */
    SysRetrunMessage updateMaterielPartSeq(Integer mdpIdBefore, Integer upDown) throws HSKException;
    SysRetrunMessage updateMaterielPartSeqBatch(Integer mdpIdBefore, Integer upDown, Integer length) throws HSKException;

    SysRetrunMessage updateMaterielPartNeedShow(Integer mdpId, Integer needShow) throws HSKException;

    PagerModel getMaterielPartNormPagerModel(Integer mdpId, Integer mdpsId, String searchName) throws HSKException;
}
