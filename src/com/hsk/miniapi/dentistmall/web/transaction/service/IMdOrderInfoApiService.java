package com.hsk.miniapi.dentistmall.web.transaction.service;

import java.util.*;

import javax.servlet.http.HttpServletRequest;


import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.IDSTService;
import com.hsk.exception.HSKException;

/**
 * transaction业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:16:59
 */
public interface IMdOrderInfoApiService {


		/**
	 * 查找MdOrderInfo对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  moiId  Integer类型(md_order_info表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage findFormObject(Integer moiId) throws HSKException;


	/**
	 * 查找MdOrderInfo对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  moiId  Integer类型(md_order_info表主键)
	 * @return MdOrderInfo md_order_info表记录
	 * @throws HSKException
	 */
	public MdOrderInfo doFindObject(Integer moiId) throws HSKException;

	public MdOrderInfo doFindObject2(Integer moiId, SysUserInfo account) throws HSKException;

	/**
	 * 根据md_order_info表主键删除MdOrderInfo对象记录
     * @param  moiId  Integer类型(md_order_info表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer moiId) throws HSKException;

	//添加保存摘要
	public SysRetrunMessage   saveZhaiYao(Integer moiId, String test) throws HSKException;

	/**
	 * 根据md_order_info表主键删除多条MdOrderInfo对象记录
     * @param  moiIds  Integer类型(md_order_info表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String moiIds) throws HSKException;

	 /**
	 * 新增或修改md_order_info表记录 ,如果md_order_info表主键MdOrderInfo.moiId为空就是添加，如果非空就是修改
     * @param  att_MdOrderInfo  MdOrderInfo类型(md_order_info表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdOrderInfo att_MdOrderInfo) throws HSKException;

	/**
	 * 供应商修改订单金额
	 * @param moiId
	 * @param placeOrderMoney
	 * @param exoressMoney
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage updateOrderMoney(Integer moiId, String placeOrderMoney, String exoressMoney, String saleMoney) throws HSKException;

	/**
	 *根据MdOrderInfo对象作为对(md_order_info表进行查询，获取分页对象
     * @param  att_MdOrderInfo  MdOrderInfo类型(md_order_info表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdOrderInfo att_MdOrderInfo) throws HSKException;

	PagerModel getPagerModelObject2(MdOrderInfo att_MdOrderInfo) throws HSKException;

	public PagerModel getPagerModelObjectForCk(MdOrderInfo att_MdOrderInfo) throws HSKException;

	/**
	 * 根据订单ID查看订单详细信息
	 * @param moiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getMdOrderInfo(Integer moiId) throws HSKException;

	public SysRetrunMessage updateOrderInfo(Integer moiId, Double money3, String momIds, String prices, String test) throws HSKException;
	/**
	 * 发货操作
	 * @param moiId 订单ID
	 * @param expressName 快递公司名称
	 * @param expressCode 运单号
	 * @param momIds 订单详情ID
	 * @param shus 发货数量组合
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveSendOrderInfo(Integer moiId, String expressName, String expressCode, String momIds, String shus, String test) throws HSKException;
	/**
	 * 订单确认
	 * @param moiId
	 * @param momIds
	 * @param shus
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveReceiveOrderInfo(Integer moiId, String momIds, String shus, String masIds) throws HSKException;
	/**
	 * 订单完结
	 * @param moiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveEndOrderInfo(Integer moiId) throws HSKException;
	/**
	 * 订单退货
	 * @param moiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveBackOrderInfo(Integer moiId) throws HSKException;

	/**
	 * 待发货取消
	 * @param moiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveCancelBackInfo(Integer moiId) throws  HSKException;
	/**
	 * 查询入库需要的订单列表
	 * @param att_MdOrderInfo
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getPagerModelEnterOrder(MdOrderInfo att_MdOrderInfo) throws HSKException;
	/**
	 * 查询出库需要的订单列表
	 * @param att_MdOrderInfo
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getPagerModelOutOrder(MdOrderInfo att_MdOrderInfo) throws HSKException;
	/**
	 * 获取全部订单
	 * @param att_MdOrderInfo
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getAllPagerModelObject(MdOrderInfo att_MdOrderInfo) throws HSKException;

	/**
	 * 获取非重复信息
	 * @param att_MdOrderInfo
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getAllPagerModelObjectDistinct(MdOrderInfo att_MdOrderInfo, String distinctName) throws HSKException;

	/**
	 * 获取查看页面非重复信息
	 * @param att_MdOrderInfo
	 * @param distinctName
	 * @return
	 * @throws HSKException
	 */
	PagerModel getPagerModelObjectForCkDistinct(MdOrderInfo att_MdOrderInfo, String distinctName) throws HSKException;
	/**
	 * 导出订单详情
	 * @param wowId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage exportInfo(Integer moiId) throws HSKException;
	/**
	 * 增加导出仓库出库单
	 */
	public SysRetrunMessage exportOutWarehouse(Integer moiId) throws HSKException;
	/**
	 * 导出配送点信息
	 * @param moiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage exportSupplierInfo(Integer moiId) throws HSKException;
	/**
	 * 增加导出对账单
	 * yanglei
	 * 2019-11-26
	 */
	public SysRetrunMessage exportSupplierInfoSheetC(Integer moiId, String test) throws HSKException;

	/*public SysRetrunMessage exportCgOrderList(MdOrderInfo att_MdOrderInfo,MdOrderMx att_MdOrderMx) throws HSKException;*/
	/**
	 * 2020-01-16 更换导出全部订单模板
	 * @param att_MdOrderInfo
	 * @param att_MdOrderMx
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage exportCgOrderList(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx, String moiIds) throws HSKException;
	public SysRetrunMessage exportGyOrderList(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx) throws HSKException;
	public SysRetrunMessage exportAllCgOrderList(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx) throws HSKException;
	//增加批量导出全部订单
	public SysRetrunMessage exportAllBatchList(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx, String moiIds) throws HSKException;
	public SysRetrunMessage exportDzOrderList(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx, String moiIds) throws HSKException;

	/**
	 * 获取状态count
	 */
	public SysRetrunMessage countOrder(MdOrderInfo att_MdOrderInfo) throws HSKException;
	/**
	 * =================================================================
	 * 以下是首页中的数据service内容
	 * 2019-12-29
	 * yanglei
	 */
	//增加导出7天数据分析
	public SysRetrunMessage exportSevenDayTwo() throws HSKException;
	  //增加订单总金额
	public SysRetrunMessage countOrderMoney() throws HSKException;
	   //增加商品交易实时战报中的数据
	public PagerModel getOrderMxListByTransaction(Integer limit, Integer page) throws HSKException;
	//商品总览
	public PagerModel getOrderMxListByAll() throws HSKException;
	  public SysRetrunMessage materielState() throws HSKException;
	//商品种类统计
	  public SysRetrunMessage materielType() throws HSKException;
	  //交易分析
	  public SysRetrunMessage materielAnalysis(Integer value) throws HSKException;
	  //商品排行榜
	  public PagerModel getOrderMxListmaterielTop(Integer limit, Integer page) throws HSKException;
	  //转化交易率
	   public SysRetrunMessage materielConversion(Integer value) throws HSKException;
	   //增加新老客户交易中的数据
	   public SysRetrunMessage NewOldMoneyCount(Integer value) throws HSKException;

	   //小程序增加业务员查询自己下面的机构的订单
	   public PagerModel getSalesmanOrderList(Integer limit, Integer page,String processStatus,String orderCode,String keyword,String purchaseUnit,String placeOrderTimeStart,String placeOrderTimeEnd) throws HSKException;
		//小程序增加业务员查询自己下面的机构的订单明细
	public SysRetrunMessage getSalesmanOrderListMx(Integer moiId) throws HSKException;
		//小程序增加业务员查询自己下面的机构账单总额  ---累计销售金额
	public SysRetrunMessage getSumPlaceOrderMoney() throws HSKException;
	//小程序增加业务员查询自己下面的机构账单总额  ---本月销售额与上月销售额
	public  SysRetrunMessage  getMonthSumPlaceOrderMoney(String placeOrderTime,String year) throws HSKException;
	//小程序增加业务员查询自己下面的机构账单总额  ---本月销售额与上月销售额
	public  SysRetrunMessage  getYearSumPlaceOrderMoney(String year) throws HSKException;
	//小程序增加业务员销售额统计
	public PagerModel getSalesmanVolume(String placeOrderTime,String year) throws HSKException;
	//小程序增加机构月统计
	public PagerModel getSalesmanMonthCount(String placeOrderTime,String year) throws HSKException;
	//查询账单明细
	public PagerModel getSalesmanOrderBills(Integer limit, Integer page,Integer stateType,String minMoney,String maxMoney,String purchaseUnit,String keyword,String placeOrderTime) throws HSKException;
	//小程序业务员机构我的机构代理商编号
	public  SysRetrunMessage  getSalesmanCode(Integer dateInt) throws HSKException;
	//业务员小程序 我的机构和我的代理商数量
	public  SysRetrunMessage  getSalesmanCount() throws HSKException;
	//小程序我的机构列表
	public PagerModel getSalesmanCountList(Integer limit, Integer page,Integer salesmanId1) throws HSKException;
	//小程序我的代理商
	public PagerModel getAgentCountList(Integer limit,Integer page) throws HSKException;
	//小程序代理商修改地址接口
	public  SysRetrunMessage  saveAgentAddress(String salesmanName,String salesmanPhone,String area,String address) throws HSKException;
	//小程序查询地址接口
	public  SysRetrunMessage  getAgentAddress() throws HSKException;
}