package com.hsk.miniapi.dentistmall.web.storage.service;

import com.hsk.dentistmall.api.persistence.MdInventory;
import com.hsk.dentistmall.api.persistence.MdInventoryExtendView;
import com.hsk.dentistmall.api.persistence.MdInventoryView;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

/**
 * storage业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:55
 */
public interface IMdInventoryApiService {


		/**
	 * 查找MdInventory对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wiId  Integer类型(md_inventory表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer wiId) throws HSKException;

	
	/**
	 * 查找MdInventory对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wiId  Integer类型(md_inventory表主键)
	 * @return MdInventory md_inventory表记录
	 * @throws HSKException
	 */	
	public MdInventoryView findObject(Integer wiId) throws HSKException;
	
	/**
	 * 根据md_inventory表主键删除MdInventory对象记录
     * @param  wiId  Integer类型(md_inventory表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wiId) throws HSKException;
	
	
	
	/**
	 * 根据md_inventory表主键删除多条MdInventory对象记录
     * @param  wiIds  Integer类型(md_inventory表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wiIds) throws HSKException;
	
	 /**
	 * 新增或修改md_inventory表记录 ,如果md_inventory表主键MdInventory.wiId为空就是添加，如果非空就是修改
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdInventory att_MdInventory) throws HSKException;

	/**
	 *根据MdInventory对象作为对(md_inventory表进行查询，获取分页对象
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdInventory att_MdInventory) throws HSKException;
	/**
	 * 查询视图对象
	 * @param att_MdInventoryView
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getPagerViewObject(MdInventoryView att_MdInventoryView, String sort) throws HSKException;
	/**
	 * 设置库存告警库存
	 * @param wiId
	 * @param wanningShu
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveWanning(Integer wiId, Double maxShu, Integer minDay, Double wanningShu, Double ratio, String baseUnit, String matName, String mmfName) throws HSKException;

	public SysRetrunMessage exportList(MdInventoryView att_MdInventoryView) throws HSKException;
	/**
	 * 导入库存数据
	 * @param fileCode
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveImpData(String fileCode) throws HSKException;
	/**
	 * 查询库存字表分页信息
	 * @param wiId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getExtendPagerModel(Integer wiId, String relatedCode, String startDate, String endDate) throws HSKException;
	PagerModel getExtendEnterViewPagerModel(Integer wiId, String relatedCode, String startDate, String endDate) throws HSKException;
	/**
	 * 删除库存明细信息
	 * @return
	 * @throws HSKException
	 */
    public SysRetrunMessage deleteExtendObject(Integer mieId) throws HSKException;
    /**
     * 查询库存明细视图
     * @param mdInventoryExtendView
     * @param startDate
     * @param endDate
     * @return
     * @throws HSKException
     */
    public PagerModel getExtendViewPagerModel(MdInventoryExtendView mdInventoryExtendView, String startDate, String endDate) throws HSKException;
    /**
     * 库存合并
     * @param wiId
     * @param addWiIds
     * @return
     * @throws HSKException
     */
    public SysRetrunMessage addItemKeyMx(Integer wiId, String addWiIds)  throws HSKException;
    /**
     * 库存拆分
     * @param wiId
     * @param mimId
     * @return
     * @throws HSKException
     */
    public SysRetrunMessage delItemKeyMx(Integer wiId, Integer mimId)  throws HSKException;
    /**
     * 根据库存ID获取库存组合明细
     * @param wiId
     * @return
     * @throws HSKException
     */
    public PagerModel getItemKeyMxList(Integer wiId) throws HSKException;
}