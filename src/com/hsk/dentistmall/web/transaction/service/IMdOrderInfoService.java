package com.hsk.dentistmall.web.transaction.service;

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
public interface IMdOrderInfoService{


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
	
	public MdOrderInfo doFindObject2(Integer moiId,SysUserInfo account) throws HSKException;
	
	/**
	 * 根据md_order_info表主键删除MdOrderInfo对象记录
     * @param  moiId  Integer类型(md_order_info表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer moiId) throws HSKException;

	//添加保存摘要
	public SysRetrunMessage   saveZhaiYao( Integer moiId, String test) throws HSKException;

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
	public SysRetrunMessage   saveOrUpdateObject( MdOrderInfo att_MdOrderInfo) throws HSKException;

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
	public PagerModel getPagerModelObject (MdOrderInfo att_MdOrderInfo) throws HSKException;

	PagerModel getPagerModelObject2 (MdOrderInfo att_MdOrderInfo) throws HSKException;
	
	public PagerModel getPagerModelObjectForCk (MdOrderInfo att_MdOrderInfo) throws HSKException;
	
	/**
	 * 根据订单ID查看订单详细信息
	 * @param moiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getMdOrderInfo(Integer moiId) throws HSKException;
	
	public SysRetrunMessage updateOrderInfo(Integer moiId,Double money3,String momIds,String prices,String test) throws HSKException;
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
	public SysRetrunMessage saveSendOrderInfo(Integer moiId,String expressName,String expressCode,String momIds,String shus,String test) throws HSKException;
	/**
	 * 订单确认
	 * @param moiId
	 * @param momIds
	 * @param shus
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveReceiveOrderInfo(Integer moiId,String momIds,String shus, String masIds) throws HSKException;
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
	public PagerModel getPagerModelEnterOrder (MdOrderInfo att_MdOrderInfo) throws HSKException;
	/**
	 * 查询出库需要的订单列表
	 * @param att_MdOrderInfo
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getPagerModelOutOrder (MdOrderInfo att_MdOrderInfo) throws HSKException;
	PagerModel getPagerModelOutOrderEnter(MdOrderInfo att_MdOrderInfo) throws HSKException;
	/**
	 * 获取全部订单
	 * @param att_MdOrderInfo
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getAllPagerModelObject (MdOrderInfo att_MdOrderInfo) throws HSKException;

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
	public SysRetrunMessage exportSupplierInfoSheetC(Integer moiId,String test) throws HSKException;
	
	/*public SysRetrunMessage exportCgOrderList(MdOrderInfo att_MdOrderInfo,MdOrderMx att_MdOrderMx) throws HSKException;*/
	/**
	 * 2020-01-16 更换导出全部订单模板
	 * @param att_MdOrderInfo
	 * @param att_MdOrderMx
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage exportCgOrderList(MdOrderInfo att_MdOrderInfo,MdOrderMx att_MdOrderMx,String moiIds, String excludeMoiIds) throws HSKException;
	public SysRetrunMessage exportGyOrderList(MdOrderInfo att_MdOrderInfo,MdOrderMx att_MdOrderMx) throws HSKException;
	public SysRetrunMessage exportAllCgOrderList(MdOrderInfo att_MdOrderInfo,MdOrderMx att_MdOrderMx) throws HSKException;
	//增加批量导出全部订单
	public SysRetrunMessage exportAllBatchList(MdOrderInfo att_MdOrderInfo,MdOrderMx att_MdOrderMx,String moiIds) throws HSKException;
	public SysRetrunMessage exportDzOrderList(MdOrderInfo att_MdOrderInfo,MdOrderMx att_MdOrderMx,String moiIds, String excludeMoiIds) throws HSKException;
	
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

	   //供应商首页四大模块以及显示的数据

	   public SysRetrunMessage countOrderMoney1() throws HSKException;
	   public SysRetrunMessage countOrderSuppiler(MdOrderInfo att_MdOrderInfo) throws HSKException;
		//增加订单总金额
//		public SysRetrunMessage countOrderMoney1() throws HSKException;
	//	交易实时战报中的数据
		public PagerModel getOrderMxListByTransaction1(Integer limit, Integer page) throws HSKException;
		//商品排行榜前十
		public PagerModel getOrderMxListmaterielTop1(Integer limit, Integer page) throws HSKException;
		//商品总览
		public SysRetrunMessage materielState1() throws HSKException;
		//交易分析
		public SysRetrunMessage materielAnalysis1(Integer value) throws HSKException;
		//交易转化率
		public SysRetrunMessage materielConversion1(Integer value) throws HSKException;
	/*供应商首页 #########end#######*/
	//供应商交易收支
	public SysRetrunMessage branchleft1() throws HSKException;
	//供应商交易收支mxList数据
	public PagerModel getBranchMxList(String dateInput1,String dateInput2,Integer selectGuanjian,String inputGuanjian,Integer zhiFu,Integer state,String jinE1,String jinE2,Integer ziJin,Integer limit,Integer page) throws HSKException;
	//导出供应商收支数据
	public SysRetrunMessage exportBranchMxList(String dateInput1,String dateInput2,Integer selectGuanjian,String inputGuanjian,Integer zhiFu,Integer state,String jinE1,String jinE2,Integer ziJin) throws HSKException;
	//计算已结算未结算 退款 未退款等金额
	public SysRetrunMessage getBranchCount(String dateInput1,String dateInput2,Integer selectGuanjian,String inputGuanjian,Integer zhiFu,Integer state,String jinE1,String jinE2,Integer ziJin,Integer limit,Integer page) throws HSKException;

	// 保存发票功能
	SysRetrunMessage saveBill(MdBillEntity mdBillEntity) throws HSKException;
	// 更新发票
	SysRetrunMessage updateBill(MdBillEntity mdBillEntity) throws HSKException;
	// 删除发票
	SysRetrunMessage deleteBill(String billIds) throws HSKException;
	// 发票分页对象
	PagerModel getBillPagerModel(MdBillEntity mdBillEntity) throws HSKException;
	// 发票集合
	SysRetrunMessage getBillList(MdBillEntity mdBillEntity) throws HSKException;
	//获取最新的发票信息
	SysRetrunMessage getBillLatest() throws HSKException;
	}