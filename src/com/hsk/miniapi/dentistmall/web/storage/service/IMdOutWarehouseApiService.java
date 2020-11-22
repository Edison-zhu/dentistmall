package com.hsk.miniapi.dentistmall.web.storage.service;

import com.hsk.dentistmall.api.persistence.MdOutWarehouse;
import com.hsk.dentistmall.api.persistence.MdOutWarehouseMx;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

/**
 * storage业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:55
 */
public interface IMdOutWarehouseApiService {


		/**
	 * 查找MdOutWarehouse对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wowId  Integer类型(md_out_warehouse表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer wowId) throws HSKException;

	
	/**
	 * 查找MdOutWarehouse对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wowId  Integer类型(md_out_warehouse表主键)
	 * @return MdOutWarehouse md_out_warehouse表记录
	 * @throws HSKException
	 */	
	public MdOutWarehouse findObject(Integer wowId) throws HSKException;
	
	/**
	 * 根据md_out_warehouse表主键删除MdOutWarehouse对象记录
     * @param  wowId  Integer类型(md_out_warehouse表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wowId) throws HSKException;
	
	
	
	/**
	 * 根据md_out_warehouse表主键删除多条MdOutWarehouse对象记录
     * @param  wowIds  Integer类型(md_out_warehouse表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wowIds) throws HSKException;
	
	 /**
	 * 新增或修改md_out_warehouse表记录 ,如果md_out_warehouse表主键MdOutWarehouse.wowId为空就是添加，如果非空就是修改
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdOutWarehouse att_MdOutWarehouse) throws HSKException;

	/**
	 *根据MdOutWarehouse对象作为对(md_out_warehouse表进行查询，获取分页对象
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdOutWarehouse att_MdOutWarehouse, String orderTimeStart, String orderTimeEnd, String outTimeStart, String outTimeEnd, String flowState) throws HSKException;
	public PagerModel getPagerModelObjectDistinct(MdOutWarehouse att_MdOutWarehouse, String orderTimeStart, String orderTimeEnd, String outTimeStart, String outTimeEnd, String flowState, String distinctName) throws HSKException;
	/**
	 * 保存出库单详细信息
	 * @param att_MdOutWarehouse
	 * @param wiIds
	 * @param shus
	 * @param momIds
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveMdOutWarehouse(MdOutWarehouse att_MdOutWarehouse, String mieIds, String shus, String momIds, String number1s, String wiIds, String leftNumbers)  throws HSKException;
	/**
	 * 导出出库单详情
	 * @param wowId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage exportInfo(Integer wowId) throws HSKException;

	//新增导出出库单详情

	public SysRetrunMessage exportInfoApply(Integer wowId) throws HSKException;
	/**
	 * 新增参数rbsIds
	 * @param att_MdOutWarehouse
	 * @param att_MdOutWarehouseMx
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage exportApplyList(MdOutWarehouse att_MdOutWarehouse, MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKException;
	//增加批量导出方法
	public SysRetrunMessage exportApplyListPi(MdOutWarehouse att_MdOutWarehouse, MdOutWarehouseMx att_MdOutWarehouseMx, String wowIds) throws HSKException;

	public SysRetrunMessage exportBackList(MdOutWarehouse att_MdOutWarehouse, MdOutWarehouseMx att_MdOutWarehouseMx) throws HSKException;
}