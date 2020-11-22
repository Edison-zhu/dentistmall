package com.hsk.dentistmall.api.daobbase;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_inventory表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:54
 */
public interface IMdInventoryDao{
	
	/**
	 * 根据md_inventory表主键查找MdInventory对象记录
	 * @param  WiId  Integer类型(md_inventory表主键)
	 * @return MdInventory md_inventory表记录
	 * @throws HSKDBException
	 */	
	public MdInventory findMdInventoryById(Integer WiId) throws HSKDBException;
	
	/**
	 * 根据md_inventory表主键删除MdInventory对象记录
     * @param  WiId  Integer类型(md_inventory表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdInventoryById(Integer WiId) throws HSKDBException;


	public List<Map<String, Object>> getPriceSum(MdInventoryView att_MdInventoryView) throws HSKDBException;
	 
	/**
 	 * 根据md_inventory表主键修改MdInventory对象记录
	 * @param  WiId  Integer类型(md_inventory表主键)
	 * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
	 * @return MdInventory  修改后的MdInventory对象记录
	 * @throws HSKDBException
	 */
	public  MdInventory updateMdInventoryById(Integer WiId,MdInventory att_MdInventory) throws HSKDBException;
	
	/**
	 * 新增md_inventory表 记录
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
     * @return md_inventory表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdInventory(MdInventory att_MdInventory) throws HSKDBException;
	
	 /**
	 * 新增或修改md_inventory表记录 ,如果md_inventory表主键MdInventory.WiId为空就是添加，如果非空就是修改
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
     * @return MdInventory  修改后的MdInventory对象记录
	 * @throws HSKDBException
	 */
	public  MdInventory saveOrUpdateMdInventory( MdInventory att_MdInventory) throws HSKDBException;
	
	/**
	 *根据MdInventory对象作为对(md_inventory表进行查询，获取分页对象
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdInventory (MdInventory att_MdInventory) throws HSKDBException;
	
    /**
	 *根据MdInventory对象作为对(md_inventory表进行查询，获取分页对象
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
     * @return List<MdInventory>  列表对象
	 * @throws HSKDBException 
	 */
	public List<MdInventory> getListByMdInventory ( MdInventory att_MdInventory) throws HSKDBException;
	/**
	 * 插入库存信息s
	 * @param att_MdInventory
	 * @throws HSKDBException
	 */
	public MdInventory insertMdInventory(MdInventory att_MdInventory, String retio, Double matNumber, Double splitNumber) throws HSKDBException;
	/**
	 * 查询视图分页信息
	 * @param att_MdInventoryView
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdInventoryView (MdInventoryView att_MdInventoryView,Double InventoryStart,Double InventoryEnd,Integer safeIsNot, Integer inventoryWarn, Integer validdateWarn, Integer zeroWarn) throws HSKDBException;

	/**
	 * 查询视图分页信息2
	 * @param att_MdInventoryView
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdInventoryView2 (MdInventoryView att_MdInventoryView,Double InventoryStart,Double InventoryEnd,Integer safeIsNot, Integer inventoryWarn, Integer validdateWarn, Integer zeroWarn) throws HSKDBException;

	/**
	 * 查询库存信息列表
	 * @param att_MdInventoryView
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdInventoryView> getListByMdInventoryView (MdInventoryView att_MdInventoryView) throws HSKDBException;
	/**
	 * 根据主键ID查询视图对象
	 * @param wiId
	 * @return
	 * @throws HSKDBException
	 */
	public MdInventoryView findMdInventoryViewById(Integer wiId) throws HSKDBException;
	/**
	 * 根据查询条件查询视图对象
	 * @param mdInventoryView
	 * @return
	 * @throws HSKDBException
	 */
	public MdInventoryView findMdInventoryViewByMdInventoryView(MdInventoryView att_MdInventoryView) throws HSKDBException;

	/**
	 * 根据查询条件查询视图list对象
	 * @param att_MdInventoryView
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdInventoryView> findMdInventoryViewListByMdInventoryView(MdInventoryView att_MdInventoryView) throws HSKDBException;
	/**
	 * 根据查询条件查询MdInventoryExtendView视图对象
	 * @param att_MdInventoryExtendView
	 * @return
	 * @throws HSKDBException
	 */
	public MdInventoryExtendView findMdInventoryExtendViewByMdInventoryExtendView(MdInventoryExtendView att_MdInventoryExtendView) throws HSKDBException;

	/**
	 * 根据查询条件查询MdInventoryExtendView视图list对象
	 * @param att_MdInventoryExtendView
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdInventoryExtendView> findMdInventoryExtendViewListByMdInventoryExtendView(MdInventoryExtendView att_MdInventoryExtendView) throws HSKDBException;
	/**
	 * 查询库存中物料信息名称
	 * @param att_MdInventoryView
	 * @return
	 * @throws HSKDBException
	 */
	public Map<String,String> getInventoryName(MdInventoryView att_MdInventoryView) throws HSKDBException;
	/**
	 * 查询收藏库存视图信息
	 * @param att_MdInventoryCollectView
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdInventoryCollectView (MdInventoryCollectView att_MdInventoryCollectView) throws HSKDBException;

	/**
	 * 获取订单入库详细表
	 * @param att_mdInventoryView
	 * @return
	 * @throws HSKDBException
	 */
    List<MdInventoryViewEx> getListByMdInventoryViewEx(MdInventoryViewEx att_mdInventoryView) throws  HSKDBException;

    Double getPagerModelAllIventoryByMdInventoryView(MdInventoryView att_mdInventoryView) throws  HSKDBException;

	List<Map<String, Object>> getPagerModelAllIventoryByMdInventoryViewMatName(MdInventoryView att_mdInventoryView, String matName) throws HSKDBException;

    void deleteMdInventoryPaEx(String paieIds) throws HSKDBException;

	void deleteMdInventoryPa(String paiIds) throws HSKDBException;

    Double getInventoryExtendAvgPrice(Integer wiId) throws HSKDBException;

    List<MdItemMxView> getMdItemMxView(MdItemMxView itemMx) throws HSKDBException;

    PagerModel findMdInventoryViewPagerModelByMdInventoryView(MdInventoryView att_MdInventoryExtendView) throws HSKDBException;

    Integer getCheckCount(Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType, Integer checkType) throws HSKDBException;

    Integer getCheckIsCheckCount(Integer ciId, Integer isCheck) throws HSKDBException;

    List<MdMaterielFormat> findMdMaterielFormat(Integer wmsMiId) throws HSKDBException;

    void deleteCheck(Integer ciId, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException;

	void deleteCheckMx(Integer ciId, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException;

    void saveMdInventoryExtendByBatch(List<MdInventoryExtend> mdInventoryExtendList) throws HSKDBException;

	void saveMdEnterWarehouseMxByBatch(List<MdEnterWarehouseMx> mdEnterWarehouseMxList) throws HSKDBException;

    List<Map<String, Object>> getInventoryNormStringList(String wmsMiIds, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException;

	MdInventory findMdInventoryByExtend(MdInventoryExtend mdInventoryExtend, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException;

    Double getRatioByMdMaterielFomart(MdMaterielFormat mdMaterielFormat, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException;

    void updateMdInventoryRatioByInventoryExtend(MdInventoryExtend mdInventoryExtend, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType, String ratio) throws HSKDBException;

    void deleteMergeLog(Integer wiId, String addMmfIds) throws HSKDBException;

    Map<String, Object> getOhterInfo(Integer wiId) throws HSKDBException;
}