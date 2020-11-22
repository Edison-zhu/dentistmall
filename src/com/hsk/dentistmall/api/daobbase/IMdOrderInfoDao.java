package com.hsk.dentistmall.api.daobbase;

import java.util.*;

import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_order_info表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-28 17:16:59
 */
public interface IMdOrderInfoDao{
	
	/**
	 * 根据md_order_info表主键查找MdOrderInfo对象记录
	 * @param  MoiId  Integer类型(md_order_info表主键)
	 * @return MdOrderInfo md_order_info表记录
	 * @throws HSKDBException
	 */	
	public MdOrderInfo findMdOrderInfoById(Integer MoiId) throws HSKDBException;

	public MdOrderInfoAfterSaleViewEntity findMdOrderInfoIncludeAsById(Integer MoiId) throws HSKDBException;
	/**
	 * 增加根据日期查询方法
	 */
	public MdOrderInfo findMdOrderInfoByDate(Date EndDate,Date StartDate) throws HSKDBException;
	
	/**
	 * 根据md_order_info表主键删除MdOrderInfo对象记录
     * @param  MoiId  Integer类型(md_order_info表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdOrderInfoById(Integer MoiId) throws HSKDBException;
	 
	/**
 	 * 根据md_order_info表主键修改MdOrderInfo对象记录
	 * @param  MoiId  Integer类型(md_order_info表主键)
	 * @param  att_MdOrderInfo  MdOrderInfo类型(md_order_info表记录)
	 * @return MdOrderInfo  修改后的MdOrderInfo对象记录
	 * @throws HSKDBException
	 */
	public  MdOrderInfo updateMdOrderInfoById(Integer MoiId,MdOrderInfo att_MdOrderInfo) throws HSKDBException;
	
	/**
	 * 新增md_order_info表 记录
     * @param  att_MdOrderInfo  MdOrderInfo类型(md_order_info表记录)
     * @return md_order_info表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdOrderInfo(MdOrderInfo att_MdOrderInfo) throws HSKDBException;
	
	 /**
	 * 新增或修改md_order_info表记录 ,如果md_order_info表主键MdOrderInfo.MoiId为空就是添加，如果非空就是修改
     * @param  att_MdOrderInfo  MdOrderInfo类型(md_order_info表记录)
     * @return MdOrderInfo  修改后的MdOrderInfo对象记录
	 * @throws HSKDBException
	 */
	public  MdOrderInfo saveOrUpdateMdOrderInfo( MdOrderInfo att_MdOrderInfo) throws HSKDBException;
	
	/**
	 *根据MdOrderInfo对象作为对(md_order_info表进行查询，获取分页对象
     * @param  att_MdOrderInfo  MdOrderInfo类型(md_order_info表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdOrderInfo (MdOrderInfo att_MdOrderInfo) throws HSKDBException;
	PagerModel getPagerModelIncludeAsByMdOrderInfo(MdOrderInfo att_MdOrderInfo) throws HSKDBException;
	public PagerModel getAllPagerModelObjectDistinct(MdOrderInfo att_MdOrderInfo, String distinctName) throws HSKDBException;
	
    /**
	 *根据MdOrderInfo对象作为对(md_order_info表进行查询，获取分页对象
     * @param  att_MdOrderInfo  MdOrderInfo类型(md_order_info表记录)
     * @return List<MdOrderInfo>  列表对象
	 * @throws HSKDBException 
	 */
	public List<MdOrderInfo> getListByMdOrderInfo ( MdOrderInfo att_MdOrderInfo) throws HSKDBException;
	/**
	 * 根据流程状态统计订单信息
	 * @param processState
	 * @return
	 * @throws HSKDBException
	 */
	public Integer countOrderInfoByProcessState(Integer PurchaseId,String processState) throws HSKDBException;

	/**
	 * 查询有售后的订单
	 * @return
	 * @throws HSKDBException
	 */
	Integer countOrderInfoByAfterSale(Integer suiId) throws HSKDBException;
	Integer countOrderInfoByAfterSaleTwo(Integer suiId, MdOrderInfo att_MdOrderInfo) throws HSKDBException;
	/**
	 * 增加状态count
	 * 2019-12-19
	 * yanglei
	 */
	public Integer countOrderInfoByProcessStateTwo(Integer PurchaseId,String processState, MdOrderInfo att_MdOrderInfo) throws HSKDBException;
	/**
	 * 根据类型查询订单列表
	 * @param PurchaseId
	 * @param processState
	 * @return
	 * @throws HSKDBException
	 */
	
	public PagerModel getOrderInfoListByProcessState(Integer PurchaseId,String processState) throws HSKDBException;
	PagerModel getOrderInfoListByAfterSale(Integer PurchaseId) throws HSKDBException;

	PagerModel getOrderInfoListByMoiid(Integer suiId, Integer moiId) throws HSKDBException;
	/**
	 * 查询入库需要的订单列表
	 * @param att_MdOrderInfo
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByEnterMdOrderInfo (MdOrderInfo att_MdOrderInfo) throws HSKDBException;
	/**
	 * 查询入库需要的订单列表
	 * @param att_MdOrderInfo
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByOutMdOrderInfo (MdOrderInfo att_MdOrderInfo) throws HSKDBException;

	PagerModel getPagerModelObjectForCkDistinct(MdOrderInfo att_mdOrderInfo, String distinctName) throws HSKDBException;

    PagerModel findOrderBySearch(Integer suiId, String processStatus, String searchName, String placeOrderTime, String payType) throws HSKDBException;

	PagerModel findOrderIncludeAsBySearch(Integer suiId, String processStatus, String searchName, String placeOrderTime, String payType) throws HSKDBException;
	PagerModel findOrderByMoiIds(Integer suiId, String ids) throws HSKDBException;
	List<Map<String, Object>> findOderByMx(Integer suiId, String processStatus, String searchName, Integer limit, Integer page) throws  HSKDBException;
	/**
	 * =================================================================
	 * 以下是首页中的数据Dao内容
	 * 2019-12-29
	 * yanglei
	 */
	/**
	 * 订单数
	 */
	public Integer CountOrers(Integer SuiId) throws HSKDBException;
	//金额总数
	public Double CountMoneys(Integer SuiId) throws HSKDBException;
	//退货总金额
	public Double RetreatCountMoney(Integer SuiId) throws HSKDBException;
	//增加交易实时战报中的今日销售额
	public Double TransactionCountMoney(Integer SuiId) throws HSKDBException;
	//增加交易实时战报中的今日订单
	public Integer TransactionCount(Integer SuiId) throws HSKDBException;
	//增加交易实战是战报中的今日下单百分比
	public Integer PercentageCount(Integer SuiId) throws HSKDBException;
	//交易实时战报
	public List<Map<String, Object>> getOrderMxListByTransaction(Integer limit, Integer page) throws HSKDBException;
	//商品总览
	public Integer materielState(Integer state) throws HSKDBException;
	//商品种类统计
	public Integer materielType(String matType) throws HSKDBException;
	//交易分析中成交量
	/*####################################与供应商共用方法      start       */
	public Integer materielAnalysis(Integer value,Integer oldId) throws HSKDBException;
	//交易分析中成交总量
	public Integer materielAnalysisCounts(Integer value,Integer oldId) throws HSKDBException;
	//交易分析中成交额
	public Double materielAnalysisMoney(Integer value,Integer oldId) throws HSKDBException;
	//交易分析中成交额
	public Double materielAnalysisMoneys(Integer value,Integer oldId) throws HSKDBException;
	//交易分析中人均消费
	public Integer materielAvgMoney(Integer value,Integer oldId) throws HSKDBException;
	//交易分析中的总消费
	public Double materielAvgMoneys(Integer value,Integer oldId) throws HSKDBException;
	/*####################################与供应商共用方法        end     */
	//商品排行榜 top10
	public List<Map<String, Object>> getOrderMxListmaterielTop(Integer limit, Integer page) throws HSKDBException;
	//商品排行榜中收藏数据
	public List<Map<String, String>> getOrderMxListmaterielTopFavorites(String wmsiId) throws HSKDBException;
	//仓管入库次数
	public Integer getOrderWarehouseCount(Integer SuiId) throws HSKDBException;
	//仓管入库金额
	public Double getOrderWarehouseMoney(Integer SuiId) throws HSKDBException;
	//仓管申领次数
	public Integer getOrderApplyCount(Integer SuiId) throws HSKDBException;
	//仓管申领金额
	public Double getOrderApplyMoney(Integer SuiId) throws HSKDBException;
	//退款退货次数
	public Integer getOrderRetreatCount(Integer SuiId) throws HSKDBException;
	//退款退货金额
	public Double getOrderRetreatMoney(Integer SuiId) throws HSKDBException;
	//交易转换率中的浏览量
	public Integer materielConversionBrowse(Integer value,Integer oldId) throws HSKDBException;
	//交易转换率中下单量
	public Integer materielConversionOrder(Integer value,Integer oldId) throws HSKDBException;
	//交易转换率中付款率
	public Integer materielConversionPayment(Integer value,Integer oldId) throws HSKDBException;
	//新老客户中 新客户付款金额
	public Double NewSumMoney(Integer value) throws HSKDBException;
	//新老客户中 上个月新客户付款金额 
	public Double TopNewSumMoney(Integer value) throws HSKDBException;
	//新老客户中 新客户付款人数
	public Integer NewSumCount(Integer value) throws HSKDBException;
	//新老客户中 上个月新客户付款人数
	public Integer TopNewSumCount(Integer value) throws HSKDBException;
	//新老客户中 老客户付款金额
	public Double OldSumMoney(Integer value) throws HSKDBException;
	//新老客户中 上个月老客户付款金额 
	public Double TopOldSumMoney(Integer value) throws HSKDBException;
	//新老客户中 老客户付款人数
	public Integer OldSumCount(Integer value) throws HSKDBException;
	//新老客户中 上个月老客户付款人数
	public Integer TopOldSumCount(Integer value) throws HSKDBException;
	//集团用户统计
	public Integer groupCount(Integer value) throws HSKDBException;
	//医院用户统计
	public Integer hospitalCount(Integer value) throws HSKDBException;
	//门诊用户统计
	public Integer departmentCount(Integer value) throws HSKDBException;

	Integer countOrderInfoHasAfterSale(Integer suiId, Integer moiId) throws HSKDBException;
	Integer countOrderInfoHasAfterSaleTwo(Integer suiId, Integer moiId) throws HSKDBException;

	List<Map<String, Object>> findMdOrderInfoTotalMoneyById(Integer moiId) throws HSKDBException;

	List<MdOrderInfo> findMdOrderInfoByIds(String moiId) throws HSKDBException;

    PagerModel getPagerModelByOutMdOrderInfoEnter(MdOrderInfo att_mdOrderInfo) throws HSKDBException;

    // 发票分页对象
    PagerModel getBillPagerModel(MdBillEntity mdBillEntity) throws HSKDBException;

	List<MdBillEntity> getBillList(MdBillEntity mdBillEntity) throws HSKDBException;

    MdBillEntity getBillLatest(Integer suiId, Integer billType) throws HSKDBException;
}