package com.hsk.dentistmall.api.daobbase;

import com.hsk.dentistmall.api.persistence.MdMaterielVerifyEntity;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/7/30 14:37
 */
public interface IMdMaterielVerifyDao {
    Integer getMdMaterielVerifyCountByWmsMiId(Integer wmsMiId, Map<String, Object> map) throws HSKDBException;

    Integer getMdMaterielVerifyCountByVerifyId(Integer verifyId) throws HSKDBException;

    Integer getMdMaterielVerifyCount(Integer verifyType) throws HSKDBException;

    PagerModel getMdMaterielVerifyPagerModel(MdMaterielVerifyEntity mdMaterielVerifyEntity) throws HSKDBException;

    List<MdMaterielVerifyEntity> getMdMaterielVerifyList(MdMaterielVerifyEntity mdMaterielVerifyEntity) throws HSKDBException;
}
