package com.hsk.dentistmall.api.daobbase;

import com.hsk.dentistmall.api.persistence.MdModelFormatAttrInfoEntity;
import com.hsk.dentistmall.api.persistence.MdModelFormatEntity;
import com.hsk.exception.HSKDBException;

import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/8/3 14:11
 */
public interface IMdModelFormatDao {
    List<MdModelFormatEntity> getMdModelFormatList(MdModelFormatEntity mdModelFormatEntity) throws HSKDBException;

    Integer getMdModelFormatMmfNameCount(MdModelFormatEntity mdModelFormatEntity) throws HSKDBException;

    Integer getMdModelFormatMmfNameCountByString(Integer wmsModelId, String mmfIds, String mmfNames) throws HSKDBException;

    void deleteMdFormatAttrInfo(String modelMmfIds) throws HSKDBException;

    void deleteMdFormats(Integer wmsModelId, String mmfIds) throws HSKDBException;

    List<Map<String, String>> getMdModelFormatAttrInfoList(String mmfIds) throws HSKDBException;

    List<Map<String, Object>> getMdModelFormatAttrInfoCanSearchList(String mmfIds) throws HSKDBException;
}
