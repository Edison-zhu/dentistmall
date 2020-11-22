package com.hsk.dentistmall.api.daobbase;

import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/7/31 10:24
 */
public interface IMdModelMaterielDao {
    PagerModel getMdModelMaterielPagerModel(MdModelMaterielEntity mdModelMaterielEntity) throws HSKDBException;

    /**
     * 获取模型库被引用的数量
     * @param mdModelIds
     * @return
     * @throws HSKDBException
     */
    List<Map<String, Object>> getMdModelMaterielRefCount(String mdModelIds) throws HSKDBException;

    /**
     * 获取模型库的图片
     * @param sysFileIds
     * @return
     * @throws HSKDBException
     */
    List<Map<String, Object>> getMdModelMaterielFilePath(String mdModelIds) throws HSKDBException;

    /**
     * 获取对应的品牌
     * @param mdBrandIds
     * @return
     * @throws HSKDBException
     */
    List<Map<String, Object>> getMdModelMaterielBrand(String mdModelIds) throws HSKDBException;

    /**
     * 仅仅获取被引用的数量
     * @param toString
     * @return
     * @throws HSKDBException
     */
    List<Map<String, Object>> getMdModelMaterielRefCountOnly(String mdModelIds) throws HSKDBException;
    Integer getMdModelMaterielRefCountOnly(Integer mdModelId) throws HSKDBException;

    void deleteMdModelMateruel(String wmsModelIds) throws HSKDBException;

    List<MdMaterielType> getMdMaterielTypeFirst(Integer mmtId) throws HSKDBException;

    List<MdMaterielType> getMdMaterielTypeSecond(Integer mmtId, Integer firstId) throws HSKDBException;

    List<MdMaterielType> getMdMaterielTypeThird(Integer mmtId, Integer secondId, Integer firstId) throws HSKDBException;

    List<Map<String, Object>> getMdMaterielTypeLinkList(String searchName, Integer suiId) throws HSKDBException;

    Integer getMdModelTypeLinkId(MdModelTypeLinkInfoEntity mdModelTypeLinkInfoEntity) throws HSKDBException;

    Map<String, String> getMdModelMaterielLastCode() throws HSKDBException;

    /**
     * 检查是否有重复的商品编号
     * @param matCode
     * @param wmsModelId
     * @return
     * @throws HSKDBException
     */
    Integer getMdModelMaterielCodeCountCheck(String matCode, Integer wmsModelId) throws HSKDBException;

    void deleteMdModelFileInfo(Integer wmsModelId) throws HSKDBException;

    void deleteMdModelFileInfo(String wmsModelIds) throws HSKDBException;

    void deleteMdModelBrand(String wmsModelIds) throws HSKDBException;

    List<MdMaterielParameter> getMdModelParameterListByMmpIds(String mmpIdStr) throws HSKDBException;

    List<MdModelParameterInfoEntity> getMdModelParameterListByModelId(Integer wmsModelId) throws HSKDBException;

    Integer getMdModelMaterielNameChange(String modelMatName, Integer wmsModelId) throws HSKDBException;

    PagerModel getMdModelOpratePagerModel(Integer wmsModelId) throws HSKDBException;

    Integer getMdMaterielTypeLinkCount(MdModelTypeLinkInfoEntity mdModelTypeLinkInfoEntity) throws HSKDBException;

    Integer getMdModelMaterielMatNameCountCheck(String matName, Integer wmsModelId) throws HSKDBException;

    void deleteMdModelFormat(String wmsModelIds) throws HSKDBException;

    void deleteMdModelLog(String wmsModelIds) throws HSKDBException;

    List<Map<String, Object>> getImgIds(Integer wmsModelId, String imgIds) throws HSKDBException;

    void deleteMdModelParameter(String wmsModelId) throws HSKDBException;

    Integer getMdModelMaterielStateChange(String state, Integer wmsModelId) throws HSKDBException;
}
