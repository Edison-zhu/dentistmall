package com.hsk.dentistmall.web.materiel.service;

import com.hsk.dentistmall.api.persistence.MdModelMaterielEntity;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

/**
 * author: yangfeng
 * time: 2020/7/31 9:54
 */
public interface IMdModelMaterielService {
    /**
     * 获取商品库的分页对象
     * @param mdModelMaterielEntity
     * @return
     * @throws HSKException
     */
    PagerModel getMdModelMaterielPagerModel(MdModelMaterielEntity mdModelMaterielEntity) throws HSKException;

    /**
     * 获取某个商品模型信息
     * @param wmsModelId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getMdModelMaterielInfo(Integer wmsModelId) throws HSKException;

    /**
     * 删除某个商品模型
     * @param wmsModelId
     * @return
     * @throws HSKDBException
     */
    SysRetrunMessage deleteMdModelMateriel(Integer wmsModelId) throws HSKException;

    /**
     * 批量删除商品模型
     * @param wmsModelIds
     * @return
     * @throws HSKException
     */
    SysRetrunMessage deleteMdModelMaterielBatch(String wmsModelIds) throws HSKException;

    /**
     * 更新商品模型的状态
     * @param wmsModelId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage updateMdModelMaterielState(Integer wmsModelId) throws HSKException;

    /**
     * 获取编号
     * @return
     */
    SysRetrunMessage getNewCode() throws HSKException;

    /**
     * 保存新的商品模型
     * @param mdModelMaterielEntity
     * @return
     * @throws HSKException
     */
    SysRetrunMessage saveMdModelMaterielInfo(MdModelMaterielEntity mdModelMaterielEntity) throws HSKException;

    /**
     * 更新商品模型
     * @param mdModelMaterielEntity
     * @return
     * @throws HSKException
     */
    SysRetrunMessage updateMdModelMaterielInfo(MdModelMaterielEntity mdModelMaterielEntity) throws HSKException;

    /**
     * 保存模型参数
     * @param wmsModelId
     * @param mmpIds
     * @param mmpContents
     * @return
     * @throws HSKException
     */
    SysRetrunMessage saveMdModelMaterielInfoParameter(Integer wmsModelId, String mmpIds, String mmpContents) throws HSKException;

    /**
     * 更新模型参数，具体修改
     * @param wmsModelId
     * @param mmpIds
     * @param mmpContents
     * @return
     * @throws HSKException
     */
    SysRetrunMessage updateMdModelMaterielInfoParameter(Integer wmsModelId, String mdMpIds, String mmpIds, String mmpContents) throws HSKException;

    /**
     * 获取模型的参数
     * @param wmsModelId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getMdModelMaterielInfoList(Integer wmsModelId) throws HSKException;

    /**
     * 获取一级分类
     * @param mmtId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getFirstMdMaterielTypeList(Integer mmtId) throws HSKException;

    /**
     * 获取二级分类
     * @param mmtId
     * @param firstId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getSecondMdMaterielTypeList(Integer mmtId, Integer firstId) throws HSKException;

    /**
     * 获取三级分类
     * @param mmtId
     * @param secondId
     * @param firstId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getThirdMdMaterielTypeList(Integer mmtId, Integer secondId, Integer firstId) throws HSKException;

    /**
     * 保存使用过的分类
     * @param firstId
     * @param secondId
     * @param thirdId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage saveMdMaterielType(Integer firstId, Integer secondId, Integer thirdId) throws HSKException;

    /**
     * 根据搜索名，查询对应的分类列表
     * @param searchName
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getMdMaterielTypeLink(String searchName) throws HSKException;

    /**
     * 获取操作日志
     * @param wmsModelId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getMdModelOperateLogList(Integer wmsModelId) throws HSKException;

    /**
     * 获取操作日志分页对象
     * @param wmsModelId
     * @return
     * @throws HSKException
     */
    PagerModel getMdModelOperateLodPagerModel(Integer wmsModelId) throws HSKException;

    /**
     * 保存商品模型品牌
     * @param wmsModelId
     * @param mbdIds
     * @return
     * @throws HSKException
     */
    SysRetrunMessage saveMdModelMaterielBrand(Integer wmsModelId, String mbdIds) throws HSKException;

    /**
     * 更新商品模型品牌
     * @param wmsModelId
     * @param mbdIds
     * @return
     * @throws HSKException
     */
    SysRetrunMessage updateMdModelMaterielBrand(Integer wmsModelId, String mbdIds) throws HSKException;

    /**
     * 保存商品模型销售单位
     * @param wmsModelId
     * @param sellUnit
     * @return
     * @throws HSKException
     */
    SysRetrunMessage saveMdModelMaterielSellUnit(Integer wmsModelId, String sellUnit) throws HSKException;

    /**
     * 更新商品模型销售单位
     * @param wmsModelId
     * @param sellUnit
     * @return
     * @throws HSKException
     */
    SysRetrunMessage updateMdModelMaterielSellUnit(Integer wmsModelId, String sellUnit) throws HSKException;
}
