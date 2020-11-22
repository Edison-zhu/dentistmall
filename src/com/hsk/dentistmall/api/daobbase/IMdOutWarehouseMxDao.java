package com.hsk.dentistmall.api.daobbase;

import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.dentistmall.api.persistence.MdOrderMx;
import com.hsk.dentistmall.api.persistence.MdOutWarehouse;
import com.hsk.dentistmall.api.persistence.MdOutWarehouseMx;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_out_warehouse_mx表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:55
 */
public interface IMdOutWarehouseMxDao{
	
	/**
	 * 根据md_out_warehouse_mx表主键查找MdOutWarehouseMx对象记录
	 * @param  WowMxId  Integer类型(md_out_warehouse_mx表主键)
	 * @return MdOutWarehouseMx md_out_warehouse_mx表记录
	 * @throws HSKDBException
	 */	
	public MdOutWarehouseMx findMdOutWarehouseMxById(Integer WowMxId) throws HSKDBException;
	
	/**
	 * 根据md_out_warehouse_mx表主键删除MdOutWarehouseMx对象记录
     * @param  WowMxId  Integer类型(md_out_warehouse_mx表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdOutWarehouseMxById(Integer WowMxId) throws HSKDBException;
	 
	/**
 	 * 根据md_out_warehouse_mx表主键修改MdOutWarehouseMx对象记录
	 * @param  WowMxId  Integer类型(md_out_warehouse_mx表主键)
	 * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
	 * @return MdOutWarehouseMx  修改后的MdOutWarehouseMx对象记录
	 * @throws HSKDBException
	 */
	public  MdOutWarehouseMx updateMdOutWarehouseMxById(Integer WowMxId,MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKDBException;
	
	/**
	 * 新增md_out_warehouse_mx表 记录
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
     * @return md_out_warehouse_mx表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdOutWarehouseMx(MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKDBException;
	
	 /**
	 * 新增或修改md_out_warehouse_mx表记录 ,如果md_out_warehouse_mx表主键MdOutWarehouseMx.WowMxId为空就是添加，如果非空就是修改
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
     * @return MdOutWarehouseMx  修改后的MdOutWarehouseMx对象记录
	 * @throws HSKDBException
	 */
	public  MdOutWarehouseMx saveOrUpdateMdOutWarehouseMx( MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKDBException;
	
	/**
	 *根据MdOutWarehouseMx对象作为对(md_out_warehouse_mx表进行查询，获取分页对象
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdOutWarehouseMx (MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKDBException;
	
    /**
	 *根据MdOutWarehouseMx对象作为对(md_out_warehouse_mx表进行查询，获取分页对象
     * @param  att_MdOutWarehouseMx  MdOutWarehouseMx类型(md_out_warehouse_mx表记录)
     * @return List<MdOutWarehouseMx>  列表对象
	 * @throws HSKDBException 
	 */
	public List<MdOutWarehouseMx> getListByMdOutWarehouseMx ( MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKDBException;
	/**
	 * 根据型号ID查询出库明细分页信息
	 * @param att_MdOutWarehouse
	 * @param att_MdOutWarehouseMx
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerMdOutOrderMxByMmfId(MdOutWarehouse att_MdOutWarehouse,MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKDBException;
	/**
	 * 根据出库信息、出库详细信息查询列表
	 * @param att_MdOutWarehouse
	 * @param att_MdOutWarehouseMx
	 * @return
	 * @throws HSKDBException
	 */
	public List<Map<Object,Object>> getMxListByOut(MdOutWarehouse att_MdOutWarehouse,MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKDBException;

	List<MdOutWarehouseMx> getListByMdOutWarehouseMxByMooId(Integer mooId) throws HSKDBException;

    Map<String, Object> getMissingNumber(MdOutWarehouseMx mdEnterWarehouseMx) throws HSKDBException;
}