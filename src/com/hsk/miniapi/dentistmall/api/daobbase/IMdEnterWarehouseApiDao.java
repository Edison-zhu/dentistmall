package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_enter_warehouse表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:54
 */
public interface IMdEnterWarehouseApiDao {
	
	/**
	 * 根据md_enter_warehouse表主键查找MdEnterWarehouse对象记录
	 * @param  WewId  Integer类型(md_enter_warehouse表主键)
	 * @return MdEnterWarehouse md_enter_warehouse表记录
	 * @throws HSKDBException
	 */	
	public MdEnterWarehouse findMdEnterWarehouseById(Integer WewId) throws HSKDBException;
	
	/**
	 * 根据md_enter_warehouse表主键删除MdEnterWarehouse对象记录
     * @param  WewId  Integer类型(md_enter_warehouse表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdEnterWarehouseById(Integer WewId) throws HSKDBException;
	 
	/**
 	 * 根据md_enter_warehouse表主键修改MdEnterWarehouse对象记录
	 * @param  WewId  Integer类型(md_enter_warehouse表主键)
	 * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
	 * @return MdEnterWarehouse  修改后的MdEnterWarehouse对象记录
	 * @throws HSKDBException
	 */
	public  MdEnterWarehouse updateMdEnterWarehouseById(Integer WewId, MdEnterWarehouse att_MdEnterWarehouse) throws HSKDBException;

	/**
	 * 新增md_enter_warehouse表 记录
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
     * @return md_enter_warehouse表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdEnterWarehouse(MdEnterWarehouse att_MdEnterWarehouse) throws HSKDBException;

	 /**
	 * 新增或修改md_enter_warehouse表记录 ,如果md_enter_warehouse表主键MdEnterWarehouse.WewId为空就是添加，如果非空就是修改
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
     * @return MdEnterWarehouse  修改后的MdEnterWarehouse对象记录
	 * @throws HSKDBException
	 */
	public  MdEnterWarehouse saveOrUpdateMdEnterWarehouse(MdEnterWarehouse att_MdEnterWarehouse) throws HSKDBException;

	/**
	 *根据MdEnterWarehouse对象作为对(md_enter_warehouse表进行查询，获取分页对象
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdEnterWarehouse(MdEnterWarehouse att_MdEnterWarehouse) throws HSKDBException;

    /**
	 *根据MdEnterWarehouse对象作为对(md_enter_warehouse表进行查询，获取分页对象
     * @param  att_MdEnterWarehouse  MdEnterWarehouse类型(md_enter_warehouse表记录)
     * @return List<MdEnterWarehouse>  列表对象
	 * @throws HSKDBException
	 */
	public List<MdEnterWarehouse> getListByMdEnterWarehouse(MdEnterWarehouse att_MdEnterWarehouse) throws HSKDBException;

}