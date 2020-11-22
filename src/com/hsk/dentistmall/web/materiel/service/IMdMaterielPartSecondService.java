package com.hsk.dentistmall.web.materiel.service;

import com.hsk.dentistmall.api.persistence.MdMaterielPartSecondEntity;
import com.hsk.dentistmall.api.persistence.MdMaterielPartSecondEntity;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

/**
 * author: yangfeng
 * time: 2020/5/12 9:29
 */
public interface IMdMaterielPartSecondService {
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
    MdMaterielPartSecondEntity findObject(Integer mdpId) throws HSKException;

    /**
     * 根据物料分类id删除
     *
     * @param mdpId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage deleteObject(Integer mdpId, Integer mdpsId) throws HSKException;

    /**
     * 根据物料分类ids删除
     *
     * @param mdpIds
     * @return
     * @throws HSKException
     */
    SysRetrunMessage deleteAllObject(Integer mdpId, String mdpIds) throws HSKException;

    /**
     * 保存或者更新
     *
     * @param mdMaterielPartSecondEntity
     * @return
     * @throws HSKException
     */
    SysRetrunMessage saveOrUpdateObject(MdMaterielPartSecondEntity mdMaterielPartSecondEntity) throws HSKException;

    SysRetrunMessage saveObject(MdMaterielPartSecondEntity mdMaterielPartSecondEntity) throws HSKException;

    /**
     * 返回pagermodel数据
     *
     * @param mdMaterielPartSecondEntity
     * @return
     * @throws HSKException
     */
    PagerModel getPagerModelObject(MdMaterielPartSecondEntity mdMaterielPartSecondEntity) throws HSKException;

    /**
     * 获取list
     * @param mdMaterielPartSecondEntity
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getMdMaterielPartList(MdMaterielPartSecondEntity mdMaterielPartSecondEntity, Integer limit, Integer page) throws HSKException;

    /**
     * 上移下移
     * @param mdpsIdBefore 主动移动的id
     * @param upDown 被动移动的id
     * @return
     * @throws HSKException
     */
    SysRetrunMessage updateMaterielPartSecondSeq(Integer mdpsIdBefore, Integer upDown) throws HSKException;
    SysRetrunMessage updateMaterielPartSecondSeqBatch(Integer mdpsIdBefore, Integer upDown, Integer length) throws HSKException;
}
