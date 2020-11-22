package com.hsk.dentistmall.api.daobbase;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_out_warehouse表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:55
 */
public interface IMdOutWarehouseDao{
	
	/**
	 * 根据md_out_warehouse表主键查找MdOutWarehouse对象记录
	 * @param  WowId  Integer类型(md_out_warehouse表主键)
	 * @return MdOutWarehouse md_out_warehouse表记录
	 * @throws HSKDBException
	 */	
	public MdOutWarehouse findMdOutWarehouseById(Integer WowId) throws HSKDBException;
	
	/**
	 * 根据md_out_warehouse表主键删除MdOutWarehouse对象记录
     * @param  WowId  Integer类型(md_out_warehouse表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdOutWarehouseById(Integer WowId) throws HSKDBException;
	 
	/**
 	 * 根据md_out_warehouse表主键修改MdOutWarehouse对象记录
	 * @param  WowId  Integer类型(md_out_warehouse表主键)
	 * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
	 * @return MdOutWarehouse  修改后的MdOutWarehouse对象记录
	 * @throws HSKDBException
	 */
	public  MdOutWarehouse updateMdOutWarehouseById(Integer WowId,MdOutWarehouse att_MdOutWarehouse) throws HSKDBException;
	
	/**
	 * 新增md_out_warehouse表 记录
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
     * @return md_out_warehouse表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdOutWarehouse(MdOutWarehouse att_MdOutWarehouse) throws HSKDBException;
	
	 /**
	 * 新增或修改md_out_warehouse表记录 ,如果md_out_warehouse表主键MdOutWarehouse.WowId为空就是添加，如果非空就是修改
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
     * @return MdOutWarehouse  修改后的MdOutWarehouse对象记录
	 * @throws HSKDBException
	 */
	public  MdOutWarehouse saveOrUpdateMdOutWarehouse( MdOutWarehouse att_MdOutWarehouse) throws HSKDBException;
	
	/**
	 *根据MdOutWarehouse对象作为对(md_out_warehouse表进行查询，获取分页对象
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdOutWarehouse (MdOutWarehouse att_MdOutWarehouse, Map<String, Object> map) throws HSKDBException;
	
    /**
	 *根据MdOutWarehouse对象作为对(md_out_warehouse表进行查询，获取分页对象
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
     * @return List<MdOutWarehouse>  列表对象
	 * @throws HSKDBException 
	 */
	public List<MdOutWarehouse> getListByMdOutWarehouse ( MdOutWarehouse att_MdOutWarehouse) throws HSKDBException;

    PagerModel getPagerModelObjectDistinct(MdOutWarehouse att_mdOutWarehouse, Map<String, Object> map, String distinctName) throws HSKDBException;

    Integer getWowTypeCount(MdOutOrder att_MdOutOrder, Integer wowType) throws HSKDBException;

	Integer fetFlowStateCount(MdOutOrder att_MdOutOrder, Integer flowState) throws HSKDBException;

	/**
	 * 逻辑删除
	 * @param addMmfIds
	 * @param rbaId
	 * @param rbsId
	 * @param rbbId
	 * @param purchaseType
	 * @param isDelete
	 * @throws HSKDBException
	 */
    void updateEnterwarehouseDeleteLogic(String addMmfIds, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType, Integer isDelete) throws HSKDBException;
}