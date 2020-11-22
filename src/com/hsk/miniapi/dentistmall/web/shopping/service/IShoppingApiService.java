package com.hsk.miniapi.dentistmall.web.shopping.service;

import com.hsk.dentistmall.api.persistence.MdOrderAfterSaleEntity;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import org.jbpm.pvm.internal.query.Page;

/**
 * 商城所用到的service
 * @author Administrator
 *
 */
public interface IShoppingApiService {
	/**
	 * 查询商家列表
	 * @param applicantName
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getSupplierPager(String searchName) throws HSKException;

	SysRetrunMessage getSuppliers(String searchName) throws HSKException;
	SysRetrunMessage getBrands(String searchName) throws HSKException;
	SysRetrunMessage getSuppliersMat(Integer wzId, Integer limit, Integer page) throws HSKException;
	SysRetrunMessage getBrandsMat(String brandName, Integer limit, Integer page) throws HSKException;

	SysRetrunMessage getHotMateriel(Integer tab, Integer limit, Integer page) throws HSKException;
	/**
	 * 查询商品列表
	 * @param searchName 搜索名称
	 * @param mmtId 商品分类ID
	 * @param isDefault 是否默认排序
	 * @param priceOrder 价格排序
	 * @param numOrder 销售量排序
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMatInfoPager(String searchName, Integer mmtId, Integer wzId, String brand, Integer isDefault, Integer priceOrder, Integer numOrder) throws HSKException;
	/**
	 * 实时查询
	 * @param searchName
	 * @param mmtId
	 * @param wzId
	 * @param brand
	 * @param isDefault
	 * @param priceOrder
	 * @param numOrder
	 * @return
	 * @throws HSKException
	 */
	PagerModel getMatInfoPagerSearch(String searchName,Integer mmtId,Integer wzId,String brand,Integer isDefault,Integer priceOrder,Integer numOrder) throws HSKException;

	/**
	 * 根据供应商ID获取商品分类树
	 * @param wzId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getMdMaterielTypeByWzId(Integer wzId) throws HSKException;
	/**
	 * 根据购车信息查询购物车下商品列表
	 * @param mmfIds
	 * @param shus
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getCarMatInfo(String mmfIds, String shus) throws HSKException;
	/**
	 * 下订单
	 * @param mmfIds
	 * @param shus
	 * @param wzId
	 * @param contents
	 * @param billType
	 * @param billHeard
	 * @param billCode
	 * @param mdaId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveOrder(Integer payType, String mmfIds, String shus, String wzIds, String contents, String expressMoneys, String needBill, String billType, String billHeard, String billCode, Integer mdaId) throws HSKException;

	/**
	 * 20191223 yangfeng
	 * 支付后改变订单状态
	 * @param moiId
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage savePayOrder(String moiIds, Integer payType, String money) throws HSKException;

	/**
	 * 根据订单id查询订单
	 * @param moiId
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage getOrderByMoiId(String moiIds) throws HSKException;

	/**
	 * 查询我的订单裂变
	 * @param flowState
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getOrderPager(String processStatus) throws HSKException;

	PagerModel getAfterSalePager(Integer moiId, String masCode, String moCode, String serchaName) throws HSKException;

    /**
     * 查询特定订单以及订单详情
     * @param moiId
     * @return
     * @throws HSKException
     */
	PagerModel getOrderPagerOne(Integer moiId) throws HSKException;

	/**
	 * 再次购买
	 * @param moiId
	 * @return
	 * @throws HSKException
	 */
	PagerModel buyAgain(Integer moiId) throws HSKException;
	/**
	 * 搜索订单
	 * @param processStatus
	 * @param searchName
	 * @return
	 * @throws HSKException
	 */
	PagerModel findOrderBySearch(String processStatus, String processStatus2, String searchName, String placeOrderTime, String payType, Integer limit, Integer page) throws HSKException;

	/**
	 * 搜索包括物品名，供应商，订单号，品牌名
	 * @param processStatus
	 * @param processStatus2
	 * @param searchName
	 * @param placeOrderTime
	 * @param payType
	 * @param limit
	 * @param page
	 * @return
	 * @throws HSKException
	 */
	PagerModel findOrderBySearchName(String processStatus, String processStatus2, String searchName, String placeOrderTime, String payType, Integer limit, Integer page) throws HSKException;

	SysRetrunMessage getAllPrice(String processStatus, String processStatus2, String searchName, String placeOrderTime, String payType) throws HSKException;
	SysRetrunMessage getAllAsPrice(String processStatus, String processStatus2, String searchName, String placeOrderTime, String payType) throws HSKException;
	/**
	 *
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage countOrder() throws HSKException;
	/**
	 * 根据订单ID获取订单详细列表
	 * @param moiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getOrderMxByMoiId(Integer moiId) throws HSKException;

	PagerModel getOrderMxModelByMoiId(Integer moiId, String searchName, Integer limit, Integer page) throws HSKException;

	PagerModel getOrderMxApplyAsByMoiId(Integer moiId, Integer limit, Integer page) throws HSKException;
	/**
	 * 根据物品名称查询
	 * @param matName
	 * @return
	 * @throws HSKException
	 */
	PagerModel getOrderMxName(Integer moiId, String matName, Integer limit, Integer page) throws HSKException;
	/**
	 * 导出订单信息
	 * @param mmfIds
	 * @param shus
	 * @param wzIds
	 * @param expressMoneys
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage exportOrderInfo(String mmfIds, String shus, String wzIds, String expressMoneys) throws HSKException;
	/**
	 * 获取商品第一个类别
	 * @param wmsMiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getMatFormat(Integer wmsMiId) throws HSKException;

	/** 20191210 yangfeng 增加购物车保存服务器功能
	 * 保存购物车
	 * @param mcId
	 * @param mmfId
	 * @param shu
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage addCarts(Integer mcId, Integer mmfId, Integer shu, Double price) throws HSKException;

	SysRetrunMessage addCartsCache(String mcIds, String mmfIds, String shus, String prices) throws HSKException;

	/**
	 * 一直加入购物车，不减少
	 * @param mcId
	 * @param mmfId
	 * @param shu
	 * @param price
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage addCartsPlus(Integer mcId, Integer mmfId, Integer shu, Double price) throws HSKException;

	/**
	 * 检查购物车是否存在
	 * @param mmfId
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage checkCarts(Integer mmfId) throws HSKException;
	/**
	 * 获取购物车总数量与总价格
	 * @return
	 * @throws HSKException
	 */
	PagerModel getCartsCountAndPrice() throws HSKException;

	/**
	 * 获取当前用户所有购物车
	 * @return
	 * @throws HSKException
	 */
	PagerModel findMdCartsAll() throws HSKException;

	/**
	 * 根据mmfid删除购物车
	 * @param mmfIds
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage deleteMdCartsByMmfId(String mmfIds) throws HSKException;

	/**
	 * 删除所有购物车
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage deleteMdCartsAll() throws HSKException;

	SysRetrunMessage getCartCountByMmfId(Integer mmfId) throws HSKException;

	SysRetrunMessage updateMdOrderDelivery(Integer moiId, Integer mdaId) throws HSKException;

	/** 售后相关方法
	 * 20181227 yangfeng
	 */

	SysRetrunMessage saveMdOrderSaleAfter(MdOrderAfterSaleEntity mdOrderAfterSaleEntity, String momIds, String listFilecode) throws HSKException;

	SysRetrunMessage getMdOrderSaleAfterByMasId(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKException;

	PagerModel getMdOrderSaleAfterMxByMasId(Integer masCode, Integer limit, Integer page) throws HSKException;

	PagerModel getMdOrderSaleAfterList() throws HSKException;

	PagerModel getMdOrderSaleAfterMxList(Integer masId) throws HSKException;

	SysRetrunMessage updateMdOrderSaleAfterState(Integer masId) throws HSKException;

	SysRetrunMessage updateMdOrderSaleAfterAllState(Integer masId) throws HSKException;

	SysRetrunMessage saveApplyASAddress(Integer masId, String expressName, String expressCode) throws HSKException;

	SysRetrunMessage deleteApplyAfterSale(Integer masId, Integer moiId) throws HSKException;

	PagerModel getOrderAfterSalePager(Integer moiId, Integer afterSale) throws HSKException;

	PagerModel getOrderAfterSaleMxModelByMasId(Integer moiId, String searchName, Integer limit, Integer page) throws HSKException;

	SysRetrunMessage countOrderAfterSale(Integer moiId) throws HSKException;

	PagerModel getMdOrderSaleAfterExPageModel(Integer masId) throws HSKException;

	PagerModel findOrderAfterSaleBySearch(Integer moiId, String searchAsState, String processStatus2, String searchName, String placeOrderTime, String searchAsType, Integer limit, Integer page) throws HSKException;

	/** 售后相关方法end ***/

	SysRetrunMessage getLimitDate() throws HSKException;

	SysRetrunMessage getOrderFailAfterSale(Integer moiId, Integer limit, Integer page) throws HSKException;

	SysRetrunMessage getBrandImg() throws HSKException;
//	//保存购物车已购商品
//	SysRetrunMessage savePurchased(String mmfIds,String shus) throws HSKException;
	//保存购物车已购商品
	SysRetrunMessage savePurchased(String mmfIds) throws HSKException;
	//查询已购商品
	public PagerModel getPurchasedInfo(String mmfIds,String mmfDate,String matNameAndCode,Integer limit,Integer page,Integer date1) throws HSKException;
	//删除已购商品
	public SysRetrunMessage deletePurchasedInfo(String purchasedIds) throws HSKException;
}
