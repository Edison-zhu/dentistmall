package com.hsk.dentistmall.api.daobbase;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_out_order表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-11-13 11:22:28
 */
public interface IMdOutOrderDao{
	
	/**
	 * 根据md_out_order表主键查找MdOutOrder对象记录
	 * @param  MooId  Integer类型(md_out_order表主键)
	 * @return MdOutOrder md_out_order表记录
	 * @throws HSKDBException
	 */	
	public MdOutOrder findMdOutOrderById(Integer MooId) throws HSKDBException;
	
	/**
	 * 根据md_out_order表主键删除MdOutOrder对象记录
     * @param  MooId  Integer类型(md_out_order表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdOutOrderById(Integer MooId) throws HSKDBException;
	 
	/**
 	 * 根据md_out_order表主键修改MdOutOrder对象记录
	 * @param  MooId  Integer类型(md_out_order表主键)
	 * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
	 * @return MdOutOrder  修改后的MdOutOrder对象记录
	 * @throws HSKDBException
	 */
	public  MdOutOrder updateMdOutOrderById(Integer MooId,MdOutOrder att_MdOutOrder) throws HSKDBException;
	
	/**
	 * 新增md_out_order表 记录
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return md_out_order表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdOutOrder(MdOutOrder att_MdOutOrder) throws HSKDBException;
	
	 /**
	 * 新增或修改md_out_order表记录 ,如果md_out_order表主键MdOutOrder.MooId为空就是添加，如果非空就是修改
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return MdOutOrder  修改后的MdOutOrder对象记录
	 * @throws HSKDBException
	 */
	public  MdOutOrder saveOrUpdateMdOutOrder( MdOutOrder att_MdOutOrder) throws HSKDBException;
	
	/**
	 *根据MdOutOrder对象作为对(md_out_order表进行查询，获取分页对象
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdOutOrder (MdOutOrder att_MdOutOrder) throws HSKDBException;
	PagerModel getPagerModelOutWare(MdOutOrder mdOutOrder,Integer rbaId, Integer rbsId, Integer rbbId, Integer warning) throws HSKDBException;
	
    /**
	 *根据MdOutOrder对象作为对(md_out_order表进行查询，获取分页对象
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return List<MdOutOrder>  列表对象
	 * @throws HSKDBException 
	 */
	public List<MdOutOrder> getListByMdOutOrder ( MdOutOrder att_MdOutOrder) throws HSKDBException;

    PagerModel getPagerModelByMdOutOrderDistinct(MdOutOrder att_mdOutOrder, String distinctName) throws HSKDBException;

	/**
	 * 统计报表中的数据
	 */
	public List<Map<String,Object>> departmentStatisticalReport(Integer value,Integer rbaId,Integer rbsId,Integer rbbId) throws HSKDBException;

	//待申领出库数据
	public Double outStock(Integer SuiId, Integer rbaId, Integer rbsId, Integer rbbId)throws HSKDBException;
	//订单待入库
	public Double warehouse(Integer SuiId, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType)throws HSKDBException;
	//退货出库
	public Double returnStock(Integer SuiId, Integer rbaId, Integer rbsId, Integer rbbId)throws HSKDBException;
	//申领库存告警
	public Double stockAlarm(Integer SuiId, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType)throws HSKDBException;
	//安全库存告警
	public Map<String, Object> safetyStockAlarm(Integer SuiId, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType)throws HSKDBException;
	//申领出库缺少数量
	public List<Map<String, Object>> exportCumulativeWarning() throws HSKDBException;
	//导出申请安全出库缺少数量
	public List<Map<String, Object>> SafetyEarlyWarning() throws HSKDBException;
	//7天动态数据
	public List<Map<String, Object>> sevenClaimant(Integer limit, Integer page,Integer suiId) throws HSKDBException;
	//申领人申领中订单
	public Double Claim(Integer SuiId)throws HSKDBException;
	//申领人部分出库订单
	public Double Partial(Integer SuiId)throws HSKDBException;
	//申领人已完成出库订单
	public Double CompleteOut(Integer SuiId)throws HSKDBException;
	//7天数据详情
	public List<Map<String, Object>> sevenOutMx(Integer mooId,Integer limit,Integer page) throws HSKDBException;
	//增加判断型号编码是否可操作1 入库的数据
	public Integer getMatCodeReadOnly1(Integer wmsMiId,Integer suiId) throws HSKDBException;
	//增加判断型号编码是否可操作1 出库的数据
	public Integer getMatCodeReadOnly2(Integer wmsMiId,Integer suiId) throws HSKDBException;

	public List<Map<String, Object>> getPicking(String searchDdName,String date1,String date2,Integer date,String state,Integer suiId,Integer limit,Integer page) throws HSKDBException;
	public List<Map<String, Object>> getPickingMx(Integer mooId,String gjz, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException;
	//添加物料
	public PagerModel getPagerModelBySearchName(String searchName,Integer wmsMiId,Integer mmtId,String brand, Integer mdpId, Integer mdpsId, Integer rbaId, Integer rbsId, Integer rbbId, Integer wzId, String purchaseType) throws HSKDBException;
	//获取wiId;
	public Integer returnWiId(Integer wmsMiId)throws HSKDBException;
	public Integer returnMmfId(Integer wmsMiId,Integer rbaId,Integer rbsId,Integer rbbId,Integer wzId,String purchaseType)throws HSKDBException;

	public PagerModel getMmfMx(Integer wmsMiId, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException;

	public Integer seachCollect(MdInventoryCollect mdInventoryCollect)throws HSKDBException;
	//
	public Integer seachCollectId(MdInventoryCollect mdInventoryCollect)throws HSKDBException;

	public PagerModel getCollectList(MdInventoryCollect mdInventoryCollect) throws HSKDBException;

	public void delectAllObjectBySuiId(Integer suiId) throws HSKDBException;
	//模糊查询收藏
	public PagerModel searchMdFavoritesBySearch(Integer suiId, String searchName) throws HSKDBException;
	//根据名字查询规格Id
//	public Integer getMmfId(Integer suiId, String searchName) throws HSKDBException;
	//根据规格ID 查询库存视图信息
	public MdInventoryView findMdInventoryViewByMdInventoryView(
			MdInventoryView att_MdInventoryView) throws HSKDBException;
	public Integer countCollect1(Integer suiId)throws HSKDBException;
	//添加物料管理明细
	public List<Map<String, Object>> getPickingXq(Integer mooId) throws HSKDBException;

	public Integer getPickingStateCount(String searchDdName,String date1,String date2,Integer state,Integer suiId)throws HSKDBException;
	//查询领料申请明细申请订单
	public List<Map<String, Object>> getPickingOrderInfo(Integer mooId) throws HSKDBException;
	//根据物料名称与规格名称查询
	public List<Map<String, Integer>> getmmfWmsId(String  matName,String mmfName) throws HSKDBException;

	//根据收藏ID 查询表中数据
	public MdInventoryCollect findMdInventoryCollectById(Integer micId) throws HSKDBException;
	//通过ID查询申领日志表
	public MdOutOrderLog findMdOutOrderLogById(Integer MooId) throws HSKDBException;
	//保存申领日志表
//	public  MdOutOrderLog updateMdOutOrderById(Integer MooId,MdOutOrderLog att_mdOutOrderLog) throws HSKDBException;
	public Integer saveMdOutOrderLog(MdOutOrderLog att_MdOutOrderLog) throws HSKDBException;
	//通过申领ID查询日志表
	public List<Map<String, Object>> getMdoutLog(Integer mooId1,Integer limit,Integer page) throws HSKDBException;
	//根据规格ID查询库存
	public List<Map<String, Object>> selectMmfId(Integer mmfId) throws HSKDBException;

    MdOutOrder findMdOutOrderByMdOutOrder(MdOutOrder mdOutOrder) throws HSKDBException;

	Double getCountQuantity(Integer wmsMiId, Integer mmfId, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException;

	Double getCountBaseNumber(Integer wmsMiId, Integer mmfId, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException;

    List<Map<String, Object>> getCountBaseNumberAndQuantity(String wmsMiIds, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException;
}