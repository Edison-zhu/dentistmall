package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.*;

import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_order_mx表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-28 17:16:59
 */
public interface IMdOrderMxApiDao {
	
	/**
	 * 根据md_order_mx表主键查找MdOrderMx对象记录
	 * @param  MomId  Integer类型(md_order_mx表主键)
	 * @return MdOrderMx md_order_mx表记录
	 * @throws HSKDBException
	 */	
	public MdOrderMx findMdOrderMxById(Integer MomId) throws HSKDBException;
	
	/**
	 * 根据md_order_mx表主键删除MdOrderMx对象记录
     * @param  MomId  Integer类型(md_order_mx表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdOrderMxById(Integer MomId) throws HSKDBException;
	 
	/**
 	 * 根据md_order_mx表主键修改MdOrderMx对象记录
	 * @param  MomId  Integer类型(md_order_mx表主键)
	 * @param  att_MdOrderMx  MdOrderMx类型(md_order_mx表记录)
	 * @return MdOrderMx  修改后的MdOrderMx对象记录
	 * @throws HSKDBException
	 */
	public  MdOrderMx updateMdOrderMxById(Integer MomId, MdOrderMx att_MdOrderMx) throws HSKDBException;

	/**
	 * 新增md_order_mx表 记录
     * @param  att_MdOrderMx  MdOrderMx类型(md_order_mx表记录)
     * @return md_order_mx表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdOrderMx(MdOrderMx att_MdOrderMx) throws HSKDBException;

	 /**
	 * 新增或修改md_order_mx表记录 ,如果md_order_mx表主键MdOrderMx.MomId为空就是添加，如果非空就是修改
     * @param  att_MdOrderMx  MdOrderMx类型(md_order_mx表记录)
     * @return MdOrderMx  修改后的MdOrderMx对象记录
	 * @throws HSKDBException
	 */
	public  MdOrderMx saveOrUpdateMdOrderMx(MdOrderMx att_MdOrderMx) throws HSKDBException;

	/**
	 *根据MdOrderMx对象作为对(md_order_mx表进行查询，获取分页对象
     * @param  att_MdOrderMx  MdOrderMx类型(md_order_mx表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdOrderMx(MdOrderMx att_MdOrderMx) throws HSKDBException;

    /**
	 *根据MdOrderMx对象作为对(md_order_mx表进行查询，获取分页对象
     * @param  att_MdOrderMx  MdOrderMx类型(md_order_mx表记录)
     * @return List<MdOrderMx>  列表对象
	 * @throws HSKDBException
	 */
	public List<MdOrderMx> getListByMdOrderMx(MdOrderMx att_MdOrderMx) throws HSKDBException;

	/**
	 * 根据订单IDs查询订单明细信息
	 * @param moidIds
	 * @return
	 * @throws HSKDBException
	 */
	public List<Map<String,Object>> getMxListByMoiIds(String moiIds) throws HSKDBException;
	/**
	 * 根据订单信息、订单明细信息查询订单列表
	 * @param att_MdOrderInfo
	 * @param att_MdOrderMx
	 * @return
	 * @throws HSKDBException
	 */
	public List<Map<Object,Object>> getMxListByOrder(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx, String moiIds) throws HSKDBException;
	//批量导出订单方法
	public List<Map<Object,Object>> getMxListByOrderTwo(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx, String moiId) throws HSKDBException;
	List<Map<String, Object>> getMxListByMoiIdsAndSearch(String moiIds, String searchName, Integer limit, Integer page) throws HSKDBException;
	List<Map<String, Object>> getMxListByMoiIdsAndSearch(String moiIds, String searchName, String brand, Integer limit, Integer page) throws HSKDBException;

	/**
	 * 根据订单表moiid查询详细
	 * @param moiId
	 * @return
	 * @throws HSKDBException
	 */
    List<Map<String, Object>> getMxListByMoiId(Integer moiId) throws HSKDBException;
	List<Map<String, Object>> getMxListModelByMoiId(Integer moiId, String searchName, Integer limit, Integer page, String orderName) throws HSKDBException;
	List<Map<String, Object>> getMxListModelByMoiIdCount(Integer moiId, String searchName) throws HSKDBException;
	List<Map<String, Object>> getMxListModelByMoiId(Integer moiId, Integer limit, Integer page, String matName, Integer state) throws HSKDBException;
	/**
	 * 根据物品名称查询
	 * @param matName
	 * @return
	 * @throws HSKDBException
	 */
    List<Map<String, Object>> getMxListByMatName(Integer moiId, String matName, Integer limit, Integer page) throws HSKDBException;
	List<Map<String, Object>> getMxListByMatNameCount(Integer moiId, String matName) throws HSKDBException;
	//获取7天数据分析报表
	List<Map<String, Object>> getSevenListCountTwo(Date Date1, Date Date2) throws HSKDBException;
	//根据获取同一天的日期 拿到同一天的moiid,进行查询;
	List<Map<String, Object>> getSevenListCountDate0(Date toDate1) throws HSKDBException;
	List<Map<String, Object>> getSevenListCountDate1(Date toDate1) throws HSKDBException;
	List<Map<String, Object>> getSevenListCountDate2(Date toDate1) throws HSKDBException;
	List<Map<String, Object>> getSevenListCountDate3(Date toDate1) throws HSKDBException;
	List<Map<String, Object>> getSevenListCountDate4(Date toDate1) throws HSKDBException;
	List<Map<String, Object>> getSevenListCountDate5(Date toDate1) throws HSKDBException;
	List<Map<String, Object>> getSevenListCountDate6(Date toDate1) throws HSKDBException;
	//获取7天数据分析报表 2
	List<Map<String, String>> getSevenListCountTwo2(String moiId) throws HSKDBException;
	List<Map<String, String>> getSevenListCountTwo3(String PlaceOrderTimeO) throws HSKDBException;
	//获取产品分类查询0
	List<Map<String, String>> getListMatType0(String moiId) throws HSKDBException;
	//获取产品分类查询1
	List<Map<String, String>> getListMatType1(String moiId) throws HSKDBException;
	//获取产品分类2
	List<Map<String, String>> getListMatType2(String moiId) throws HSKDBException;
	//获取产品分类3
	List<Map<String, String>> getListMatType3(String moiId) throws HSKDBException;
	//获取产品分类4
	List<Map<String, String>> getListMatType4(String moiId) throws HSKDBException;
	//获取产品分类5
	List<Map<String, String>> getListMatType5(String moiId) throws HSKDBException;
	//获取产品分类6
	List<Map<String, String>> getListMatType6(String moiId) throws HSKDBException;
	//获取产品分类7
	List<Map<String, String>> getListMatType7(String moiId) throws HSKDBException;
		//获取产品分类8
	List<Map<String, String>> getListMatType8(String moiId) throws HSKDBException;
	//总的sql  主要用于导出首页7张表的数据
	List<Map<String, Object>> getSevenListCountAll(Date Date1, Date Date2) throws HSKDBException;
	//导出全部订单中的模板数据
	public List<Map<String,Object>> getMxListbyOrderAndMx(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx, String moiIds) throws HSKDBException;
}