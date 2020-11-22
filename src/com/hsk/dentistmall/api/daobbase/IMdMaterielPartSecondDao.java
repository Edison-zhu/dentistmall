package com.hsk.dentistmall.api.daobbase;

import com.hsk.dentistmall.api.persistence.MdMaterielPartEntity;
import com.hsk.dentistmall.api.persistence.MdMaterielPartSecondEntity;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/5/12 9:31
 */
public interface IMdMaterielPartSecondDao {
    /**
     * 获取物料分类
     * @param mdMaterielPartSecondEntity
     * @return
     * @throws HSKDBException
     */
    MdMaterielPartSecondEntity findMdMateriel(MdMaterielPartSecondEntity mdMaterielPartSecondEntity) throws HSKDBException;

    /**
     * 批量删除或者删除一个
     * @param mdpIds
     * @throws HSKDBException
     */
    void deleteObjects(Integer mdpId, String mdpIds) throws HSKDBException;

    MdMaterielPartSecondEntity saveOrUpdateMaterielPart(MdMaterielPartSecondEntity mdMaterielPartSecondEntity) throws HSKDBException;

    PagerModel getMaterielPartPagerModel(MdMaterielPartSecondEntity mdMaterielPartSecondEntity) throws HSKDBException;

    List<Map<String, Object>> getMaterielPartMapList(MdMaterielPartSecondEntity mdMaterielPartSecondEntity, Integer limit, Integer page) throws HSKDBException;

    /**
     * 上移下移
     * @param mdpsIdBefore 主动移动id
     * @param seqBefore 主动移动id的未改变序号
     * @param mdpsIdAfter 被动id
     * @param seqAfter 被动移动id的未改变序号
     * @throws HSKDBException
     */
    void saveOrUpdateMaterielPartSeq(Integer mdpsIdBefore, Integer seqBefore, Integer mdpsIdAfter, Integer seqAfter) throws HSKDBException;

    void saveOrUpdateMaterielPartSeqBatch(Integer mdpsIdBefore, Integer seqBefore, Integer mdpsIdAfter, Integer seqAfter) throws HSKDBException;

    Integer getMaterielPartMaxSeq(Integer mdpId) throws HSKDBException;

    Map<String, Object> finMdMaterielPartSecondByUpDown(Integer upDown, Integer seqBefore, Integer length) throws HSKDBException;

    Integer getMaterielInfoCount(Integer mdpId, String mdpsIds) throws HSKDBException;

    List<Map<String, Object>> getMaterielPartMapListByMdpsId(String checkParts) throws HSKDBException;

    Integer findMdMaterielIdByName(String mdpsName) throws HSKDBException;

    Integer getMaterielPartSecondCount(Integer mdpId) throws HSKDBException;

    Map<String, Object> getMdMaterielPartSecondLatest(Integer mdpId, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException;
}
