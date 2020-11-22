package com.hsk.dentistmall.web.materiel.service;

import com.hsk.dentistmall.api.persistence.MdMaterielInfo;
import com.hsk.dentistmall.api.persistence.MdMaterielVerifyEntity;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

import java.util.List;

/**
 * author: yangfeng
 * time: 2020/7/30 13:36
 * 审核记录与日志
 */
public interface IMdMaterielVerifyService {
    /*********************************** 审核记录 *****************************************/
    /**
     * 根据商品进行添加审核记录,不提供报错功能
     * @param mdMaterielInfo
     * @return
     * @throws HSKException
     */
    SysRetrunMessage saveMdMaterielVerifyByMateriel(MdMaterielInfo mdMaterielInfo) throws HSKException;

    /**
     * 根据审核记录添加,提供报错
     * @param mdMaterielVerifyEntity
     * @return
     * @throws HSKException
     */
    SysRetrunMessage saveMdMaterielVerifyByMaterielVerify(MdMaterielVerifyEntity mdMaterielVerifyEntity) throws HSKException;

    /**
     * 更新审核记录
     * @param mdMaterielVerifyEntity
     * @return
     * @throws HSKException
     */
    SysRetrunMessage updateMaterielVerify(MdMaterielVerifyEntity mdMaterielVerifyEntity) throws HSKException;

    /**
     * 弃用
     * 根据审核记录id更新审核状态
     * @param mdVerifyId
     * @param verifyType
     * @return
     * @throws HSKException
     */
    SysRetrunMessage updateMdMaterielVerifyType(Integer mdVerifyId, Integer verifyType) throws HSKException;

    /**
     * 更新审核记录状态
     * @param mdVerifyId
     * @param verifyState
     * @return
     * @throws HSKException
     */
    SysRetrunMessage updateMdMaterielVerifyState(Integer mdVerifyId, Integer verifyState) throws HSKException;

    /**
     * 获取审核记录各个状态数量
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getMdMaterielVerifyTypeCount() throws HSKException;

    /**
     * 获取审核记录分页对象
     * @param mdMaterielVerifyEntity
     * @return
     * @throws HSKException
     */
    PagerModel getMdMaterielVerifyPagerModel(MdMaterielVerifyEntity mdMaterielVerifyEntity) throws HSKException;

    /**
     * 获取审核记录列表，无分页
     * @param mdMaterielVerifyEntity
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getMdMaterielVerifyList(MdMaterielVerifyEntity mdMaterielVerifyEntity) throws HSKException;


    /*********************************** 审核日志 *****************************************/

}
