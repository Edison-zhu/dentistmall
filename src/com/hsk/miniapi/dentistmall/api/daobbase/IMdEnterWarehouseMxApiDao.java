package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.*;

import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_enter_warehouse_mx表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:54
 */
public interface IMdEnterWarehouseMxApiDao {
	
		
	
	/**
	 * 根据md_enter_warehouse_mx表主键查找MdEnterWarehouseMx对象记录
	 * @param  WewMxId  Integer类型(md_enter_warehouse_mx表主键)
	 * @return MdEnterWarehouseMx md_enter_warehouse_mx表记录
	 * @throws HSKDBException
	 */	
	public MdEnterWarehouseMx findMdEnterWarehouseMxById(Integer WewMxId) throws HSKDBException;
	
	/**
	 * 根据md_enter_warehouse_mx表主键删除MdEnterWarehouseMx对象记录
     * @param  WewMxId  Integer类型(md_enter_warehouse_mx表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdEnterWarehouseMxById(Integer WewMxId) throws HSKDBException;
	 
	/**
 	 * 根据md_enter_warehouse_mx表主键修改MdEnterWarehouseMx对象记录
	 * @param  WewMxId  Integer类型(md_enter_warehouse_mx表主键)
	 * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
	 * @return MdEnterWarehouseMx  修改后的MdEnterWarehouseMx对象记录
	 * @throws HSKDBException
	 */
	public  MdEnterWarehouseMx updateMdEnterWarehouseMxById(Integer WewMxId, MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException;

	/**
	 * 新增md_enter_warehouse_mx表 记录
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
     * @return md_enter_warehouse_mx表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdEnterWarehouseMx(MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException;

	 /**
	 * 新增或修改md_enter_warehouse_mx表记录 ,如果md_enter_warehouse_mx表主键MdEnterWarehouseMx.WewMxId为空就是添加，如果非空就是修改
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
     * @return MdEnterWarehouseMx  修改后的MdEnterWarehouseMx对象记录
	 * @throws HSKDBException
	 */
	public  MdEnterWarehouseMx saveOrUpdateMdEnterWarehouseMx(MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException;

	/**
	 *根据MdEnterWarehouseMx对象作为对(md_enter_warehouse_mx表进行查询，获取分页对象
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdEnterWarehouseMx(MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException;

    /**
	 *根据MdEnterWarehouseMx对象作为对(md_enter_warehouse_mx表进行查询，获取分页对象
     * @param  att_MdEnterWarehouseMx  MdEnterWarehouseMx类型(md_enter_warehouse_mx表记录)
     * @return List<MdEnterWarehouseMx>  列表对象
	 * @throws HSKDBException
	 */
	public List<MdEnterwarehousemxMaterielEntity> getListByMdEnterWarehouseMx(MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException;
	/**
	 * 查询入库明细分页信息
	 * @param att_MdEnterWarehouse
	 * @param att_MdEnterWarehouseMx
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerMdEnterWarehouseMxBymmfId(MdEnterWarehouse att_MdEnterWarehouse, MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException;
	/**
	 * 根据入库单、入库明细查询列表信息
	 * @param att_MdEnterWarehouse
	 * @param att_MdEnterWarehouseMx
	 * @return
	 * @throws HSKDBException
	 */
	public List<Map<Object,Object>> getMxListByEnter(MdEnterWarehouse att_MdEnterWarehouse, MdEnterWarehouseMx att_MdEnterWarehouseMx) throws HSKDBException;
}