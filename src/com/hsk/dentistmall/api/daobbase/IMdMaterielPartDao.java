package com.hsk.dentistmall.api.daobbase;

import com.hsk.dentistmall.api.persistence.MdMaterielPartEntity;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/5/12 8:13
 */
public interface IMdMaterielPartDao {
    /**
     * 获取物料分类
     * @param mdMaterielPartEntity
     * @return
     * @throws HSKDBException
     */
    MdMaterielPartEntity findMdMateriel(MdMaterielPartEntity mdMaterielPartEntity) throws HSKDBException;

    /**
     * 批量删除或者删除一个
     * @param mdpIds
     * @throws HSKDBException
     */
    void deleteObjects(String mdpIds) throws HSKDBException;

    MdMaterielPartEntity saveOrUpdateMaterielPart(MdMaterielPartEntity mdMaterielPartEntity) throws HSKDBException;

    PagerModel getMaterielPartPagerModel(MdMaterielPartEntity mdMaterielPartEntity) throws HSKDBException;

    List<Map<String, Object>> getMaterielPartMapList(MdMaterielPartEntity mdMaterielPartEntity, Integer limit, Integer page) throws HSKDBException;

    List findSitePart(Integer mdpId, Integer mdpsId, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException;

    Integer getMaterielPartCount(Integer mdpId) throws HSKDBException;
    Integer getMaterielPartSecondCount(Integer mdpsId) throws HSKDBException;

    List findSecondSiteTree(Integer mdpId, Integer mdpsId) throws HSKDBException;

    /**
     * 上移下移
     * @param mdpIdBefore 主动移动id
     * @param seqBefore 主动移动id的未改变序号
     * @param mdpIdAfter 被动id
     * @param seqAfter 被动移动id的未改变序号
     * @throws HSKDBException
     */
    void saveOrUpdateMaterielPartSeq(Integer mdpIdBefore, Integer seqBefore, Integer mdpIdAfter, Integer seqAfter, MdMaterielPartEntity mdMaterielPartEntity) throws HSKDBException;

    void saveOrUpdateMaterielPartSeqBatch(Integer mdpIdBefore, Integer seqBefore, Integer mdpIdAfter, Integer seqAfter, MdMaterielPartEntity mdMaterielPartEntity) throws HSKDBException;

    Map<String, Object> finMdMaterielPartByUpDown(Integer upDown, MdMaterielPartEntity mdMaterielPartEntity, Integer length) throws HSKDBException;

    /**
     * 查询最大seq
     * @return
     * @throws HSKDBException
     */
    Integer getMaterielPartMaxSeq(MdMaterielPartEntity mdMaterielPartEntity) throws HSKDBException;

    Integer getMaterielPartSecondByMdpIdCount(Integer mdpId);

    PagerModel getMaterielNormDetailPagerModel(Integer mdpId, Integer mdpsId, String searchName) throws HSKDBException;

    List getMdMaterielPartList(Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException;

    Integer getMaterielInfoCount(String mdpIds) throws HSKDBException;

    Integer findMdMaterielIdByName(String mdpName) throws HSKDBException;

    Map<String, Object> getMdMaterielPartLatest(Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException;
}
