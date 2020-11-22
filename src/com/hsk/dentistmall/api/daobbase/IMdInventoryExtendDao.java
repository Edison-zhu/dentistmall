package com.hsk.dentistmall.api.daobbase;

import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * 库存字表管理
 * @author Administrator
 *
 */
public interface IMdInventoryExtendDao {
	/**
	 * 根据库存ID，单号查询库存字表信息
	 * @param wzId
	 * @param relatedCode
	 * @return
	 * @throws HSKDBException
	 */
	public MdInventoryExtend getMdInventoryExtendByWiId(Integer wzId,String relatedCode) throws HSKDBException;
	/**
	 * 根据库存ID查询选择库存详细信息
	 * @param wiId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdInventoryExtend> getMdInventoryExtendByWiIdToSel(Integer wiId,String relatedCode, String startDate,String endDate) throws HSKDBException;
	/**
	 * 查询所有的库存详细列表
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdInventoryExtend> getAllList()throws HSKDBException;
	/**
	 * 查询库存详细列表视图分页信息
	 * @param mdInventoryExtendView
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getMdInventoryViewPager(MdInventoryExtendView mdInventoryExtendView, String startDate,String endDate) throws HSKDBException;

    List<MdInventoryExtend> getExtendEnterViewPagerModel(Integer wiId, String relatedCode, String startDate, String endDate, Integer billType, String receiptDatetime) throws HSKDBException;

	List<MdInventoryEnterwarehouserViewEntity> getExtendEnterWarehouseViewPagerModel(Integer wiId, String relatedCode, String startDate, String endDate) throws HSKDBException;

	// 盘点
    PagerModel getMdInventoryExtendByCheckInventory(MdInventoryCheckViewEntity mdCheckInventoryEntity) throws HSKDBException;

	MdCheckInventoryEntity findCheckInventory(MdCheckInventoryEntity mdCheckInventoryEntity) throws HSKDBException;

	PagerModel getMdCheckInventory(MdCheckInventoryEntity mdCheckInventoryEntity) throws HSKDBException;

	// 调价
    PagerModel getPagerModelPA(MdInventoryPriceAdjustmentEntity mdInventoryPriceAdjustmentEntity) throws HSKDBException;

	PagerModel getPagerModelPAEx(MdInventoryPriceAdjustmentEntity mdInventoryPriceAdjustmentEntity) throws HSKDBException;

	PagerModel getPagerModelMdInventoryExtend(MdInventoryExtendView att_mdInventoryView) throws HSKDBException;

	PagerModel getPagerModelPAWithSave(String mddIds, String mdnIds) throws HSKDBException;

	PagerModel getMdInventoryLogMxList(MdInventoryEnOutLogViewEntity mdInventoryEnOutLogViewEntity, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException;

	PagerModel getMdInventoryEnOutLogMxList(MdInventoryEnOutLogViewEntity mdInventoryEnOutLogViewEntity, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException;

	PagerModel getMdInventoryMergeLog(MdInventoryMergeLogEntity mdInventoryMergeLogEntity) throws HSKDBException;

	List<Map<String, Object>> getBrandDistinct(Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType, String checkPart, String checkParts) throws HSKDBException;

    Map<String, Object> getMdInventoryBaseNumberAndAvgPrice(MdMaterielFormatView mdMaterielFormatView, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException;

	List<Map<String, Object>> getMdInventoryBaseNumberAndAvgPriceByList(String purchaseTypes, String mmfIds, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException;

	List<Map<String, Object>> getCheckBrandDistinct(Integer ciId, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException;

    Integer getMdInventoryExtendByCheckInventoryCount(MdInventoryCheckViewEntity mdCheckInventoryEntity) throws HSKDBException;

    void updateMdInventoryExtendZero(Integer wiId) throws HSKDBException;
}
