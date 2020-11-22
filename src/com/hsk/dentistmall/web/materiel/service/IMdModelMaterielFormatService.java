package com.hsk.dentistmall.web.materiel.service;

import com.hsk.dentistmall.api.persistence.MdModelFormatEntity;
import com.hsk.dentistmall.web.materiel.service.impl.MdModelMaterielFormatService;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.SysRetrunMessage;

import java.util.List;

/**
 * author: yangfeng
 * time: 2020/8/3 14:03
 */
public interface IMdModelMaterielFormatService {
    /***
     * 获取规格列表
     * @param mdModelFormatEntity
     * @return
     */
    List<MdModelFormatEntity> getMdModelMaterielFormatList(MdModelFormatEntity mdModelFormatEntity) throws HSKException;

    /**
     * 更新模型库规格
     * @param mdModelFormatEntity
     * @return
     */
    SysRetrunMessage updateMdModelFormat(MdModelFormatEntity mdModelFormatEntity) throws HSKException;

    /**
     * 保存模型库规格
     * @return
     * @throws HSKException
     */
    SysRetrunMessage saveMdModelFormat(Integer wmsModelId, String mmfNames, String mdAttrIds, Integer canSearch, String attrContents) throws HSKException;

    /**
     * 一次性更新多个模型规格, 根据修改的规格id更新
     * @return
     * @throws HSKException
     */
    SysRetrunMessage updateModelFormats(Integer wmsModelId, String mmfIds, String mmfNames, String mdAttrIds, Integer canSearch, String attrContents) throws HSKException;

    /**
     * 删除规格
     * @param wmsModelId
     * @param mmfId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage deleteMdModelFormat(Integer wmsModelId, Integer mmfId) throws HSKException;

    /**
     * 删除多个规格
     * @param wmsModelId
     * @param mmfIds
     * @return
     * @throws HSKException
     */
    SysRetrunMessage deleteMdModelFormats(Integer wmsModelId, String mmfIds) throws HSKException;

    /**
     * 获取被引用的属性
     * @param wmsModelId
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getMdModelAttrs(Integer wmsModelId) throws HSKException;

    SysRetrunMessage getTest(String jsonStr) throws HSKException;
}
