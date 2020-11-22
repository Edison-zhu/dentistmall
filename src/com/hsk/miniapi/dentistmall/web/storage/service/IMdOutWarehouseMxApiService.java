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
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:55
 */
public interface IMdOutWarehouseMxApiService {


		/**
	 * 查找MdOutWarehouseMx对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wowMxId  Integer类型(md_out_warehouse_mx表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer wowMxId) throws HSKException;

	
	/**
	 * 查找MdOutWarehouseMx对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wowMxId  Integer类型(md_out_warehouse_mx表主键)
	 * @return MdOutWarehouseMx md_out_warehouse_mx表记录
	 * @throws HSKException
	 */	
	public MdOutWarehouseMx findObject(Integer wowMxId) throws HSKException;
	
	/**
	 * 根据md_out_warehouse_mx表主键删除MdOutWarehouseMx对象记录
     * @param  wowMxId  Integer类型(md_out_warehouse_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wowMxId) throws HSKException;
	
	
	
	/**
	 * 根据md_out_warehouse_mx表主键删除多条MdOutWarehouseMx对象记录
     * @param  wowMxIds  Integer类型(md_out_warehouse_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wowMxIds) throws HSKException;
	
	 /**
	 * 新增或修改md_out_warehouse_mx表记录 ,如果md_out_warehouse_mx表主键MdOutWarehouseMx.wowMxId为空就是添加，如果非空就是修改
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKException;

	/**
	 *根据MdOutWarehouseMx对象作为对(md_out_warehouse_mx表进行查询，获取分页对象
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKException;

	/**
	 * 根据入库单ID查询入库单明细列表
	 * @param moiId
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdOutWarehouseMxListByMoiId(Integer wowId)  throws HSKException;
	/**
	 * 根据物料明细ID查询本单位出库明细信息
	 * @param mmfId
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdOutWarehouseMxListByMmfId(MdOutWarehouse att_MdOutWarehouse, MdOutWarehouseMx att_MdOutWarehouseMx)  throws HSKException;
	/**
	 * 根据库存ID查询出库明细信息
	 * @param att_MdOutWarehouse
	 * @param att_MdOutWarehouseMx
	 * @param wiId
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdOutWarehouseMxListByWiId(MdOutWarehouse att_MdOutWarehouse, MdOutWarehouseMx att_MdOutWarehouseMx, Integer wiId)  throws HSKException;
}