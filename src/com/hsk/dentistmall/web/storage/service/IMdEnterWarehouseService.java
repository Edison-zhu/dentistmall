package com.hsk.dentistmall.web.storage.service;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.service.IDSTService;
import com.hsk.exception.HSKException;

/**
 * storage业务操作接口类
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:54
 */
public interface IMdEnterWarehouseService{


		/**
	 * 查找MdEnterWarehouse对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wewId  Integer类型(md_enter_warehouse表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage findFormObject(Integer wewId) throws HSKException;

	SysRetrunMessage findFormObjectNewApply(Integer wewId, Integer moiId) throws HSKException;


	/**
	 * 查找MdEnterWarehouse对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wewId  Integer类型(md_enter_warehouse表主键)
	 * @return MdEnterWarehouse md_enter_warehouse表记录
	 * @throws HSKException
	 */
	public MdEnterWarehouse findObject(Integer wewId) throws HSKException;

	/**
	 * 根据md_enter_warehouse表主键删除MdEnterWarehouse对象记录
     * @param  wewId  Integer类型(md_enter_warehouse表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wewId) throws HSKException;



	/**
	 * 根据md_enter_warehouse表主键删除多条MdEnterWarehouse对象记录
     * @param  wewIds  Integer类型(md_enter_warehouse表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wewIds) throws HSKException;

	 /**
	 * 新增或修改md_enter_warehouse表记录 ,如果md_enter_warehouse表主键MdEnterWarehouse.wewId为空就是添加，如果非空就是修改
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject( MdEnterWarehouse att_MdEnterWarehouse) throws HSKException;

	/**
	 *根据MdEnterWarehouse对象作为对(md_enter_warehouse表进行查询，获取分页对象
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject (MdEnterWarehouse att_MdEnterWarehouse) throws HSKException;

	/**
	 * 保存入库单详细信息
	 * @param att_MdEnterWarehouse
	 * @param mmfIds
	 * @param momIds
	 * @param shus 入库数量
	 * @param number1s 订单收货数量
	 * @param prices 价格
	 * @param retailPrices 零售价
	 * @param units 最小拆分单位
	 * @param splitNumbers 最小拆分单位数量
	 * @param productPTimes 生产日期
	 * @param productValiTimes 有效期
	 * @param packasgs 包装
	 * @param factories 生产厂家
	 * @param cernos 批次
	 * @param masIds 售后
	 * @Param backPrintings 注册证号
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveMdEnterWarehouse(MdEnterWarehouse att_MdEnterWarehouse, Integer moiIds, String mmfIds,String momIds,String shus,String number1s,
												 String prices, String pricesAll, String productPTimes, String productValiTimes, String packasgs, String factories,
												 String certnos, String masIds, String retailPrices, String units, String splitNumbers, String backPrintings, String wmsMiIds, String ratio1s, String remarks)  throws HSKException;
	/**
	 * 导出入库单详情
	 * @param wowId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage exportInfo(Integer wewId) throws HSKException;

	public SysRetrunMessage exportList(MdEnterWarehouse att_MdEnterWarehouse,MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKException;
}