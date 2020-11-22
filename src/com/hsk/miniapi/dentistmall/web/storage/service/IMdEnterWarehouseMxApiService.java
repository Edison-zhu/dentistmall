package com.hsk.miniapi.dentistmall.web.storage.service;

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
public interface IMdEnterWarehouseMxApiService {


		/**
	 * 查找MdEnterWarehouseMx对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wewMxId  Integer类型(md_enter_warehouse_mx表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer wewMxId) throws HSKException;

	
	/**
	 * 查找MdEnterWarehouseMx对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wewMxId  Integer类型(md_enter_warehouse_mx表主键)
	 * @return MdEnterWarehouseMx md_enter_warehouse_mx表记录
	 * @throws HSKException
	 */	
	public MdEnterWarehouseMx findObject(Integer wewMxId) throws HSKException;
	
	/**
	 * 根据md_enter_warehouse_mx表主键删除MdEnterWarehouseMx对象记录
     * @param  wewMxId  Integer类型(md_enter_warehouse_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wewMxId) throws HSKException;
	
	
	
	/**
	 * 根据md_enter_warehouse_mx表主键删除多条MdEnterWarehouseMx对象记录
     * @param  wewMxIds  Integer类型(md_enter_warehouse_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wewMxIds) throws HSKException;
	
	 /**
	 * 新增或修改md_enter_warehouse_mx表记录 ,如果md_enter_warehouse_mx表主键MdEnterWarehouseMx.wewMxId为空就是添加，如果非空就是修改
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKException;

	/**
	 *根据MdEnterWarehouseMx对象作为对(md_enter_warehouse_mx表进行查询，获取分页对象
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKException;
	/**
	 * 根据入库单ID查询入库单明细列表
	 * @param moiId
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdEnterWarehouseMxListByMoiId(Integer wewId)  throws HSKException;
	/**
	 * 根据入库明细ID查询本单位入库明细信息
	 * @param mmfId
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdEnterWarehouseMxListByMmfId(MdEnterWarehouse att_MdEnterWarehouse, MdEnterWarehouseMx att_MdEnterWarehouseMx)  throws HSKException;
	/**
	 * 根据库存ID查询入库明细
	 * @param att_MdEnterWarehouse
	 * @param att_MdEnterWarehouseMx
	 * @param wiId
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdEnterWarehouseMxListByWiId(MdEnterWarehouse att_MdEnterWarehouse, MdEnterWarehouseMx att_MdEnterWarehouseMx, Integer wiId)  throws HSKException;
}