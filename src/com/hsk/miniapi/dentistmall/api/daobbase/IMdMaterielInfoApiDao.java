package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_materiel_info表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-29 10:30:22
 */
public interface IMdMaterielInfoApiDao {
	
	/**
	 * 根据md_materiel_info表主键查找MdMaterielInfo对象记录
	 * @param  WmsMiId  Integer类型(md_materiel_info表主键)
	 * @return MdMaterielInfo md_materiel_info表记录
	 * @throws HSKDBException
	 */	
	public MdMaterielInfo findMdMaterielInfoById(Integer WmsMiId) throws HSKDBException;
	
	/**
	 * 根据md_materiel_info表主键删除MdMaterielInfo对象记录
     * @param  WmsMiId  Integer类型(md_materiel_info表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdMaterielInfoById(Integer WmsMiId) throws HSKDBException;
	 
	/**
 	 * 根据md_materiel_info表主键修改MdMaterielInfo对象记录
	 * @param  WmsMiId  Integer类型(md_materiel_info表主键)
	 * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
	 * @return MdMaterielInfo  修改后的MdMaterielInfo对象记录
	 * @throws HSKDBException
	 */
	public  MdMaterielInfo updateMdMaterielInfoById(Integer WmsMiId, MdMaterielInfo att_MdMaterielInfo) throws HSKDBException;

	/**
	 * 新增md_materiel_info表 记录
     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
     * @return md_materiel_info表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdMaterielInfo(MdMaterielInfo att_MdMaterielInfo) throws HSKDBException;

	 /**
	 * 新增或修改md_materiel_info表记录 ,如果md_materiel_info表主键MdMaterielInfo.WmsMiId为空就是添加，如果非空就是修改
     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
     * @return MdMaterielInfo  修改后的MdMaterielInfo对象记录
	 * @throws HSKDBException
	 */
	public  MdMaterielInfo saveOrUpdateMdMaterielInfo(MdMaterielInfo att_MdMaterielInfo) throws HSKDBException;

	/**
	 *根据MdMaterielInfo对象作为对(md_materiel_info表进行查询，获取分页对象
     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdMaterielInfo(MdMaterielInfo att_MdMaterielInfo) throws HSKDBException;

    /**
	 *根据MdMaterielInfo对象作为对(md_materiel_info表进行查询，获取分页对象
     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
     * @return List<MdMaterielInfo>  列表对象
	 * @throws HSKDBException
	 */
	public List<MdMaterielInfo> getListByMdMaterielInfo(MdMaterielInfo att_MdMaterielInfo) throws HSKDBException;
	/**
	 * 根据视图查询商品列表
	 * @param mdMaterielFormatView
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdMdMaterielFormatView(MdMaterielFormatView mdMaterielFormatView) throws HSKDBException;
	/**
	 * 根据名称等条件查询商品分页信息
	 * @param serachName
	 * @param mmtId
	 * @param brand
	 * @param state
	 * @param orderStr
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelBySearchName(String serachName, Integer mmtId, Integer wzId, String brand, String state, String wzState, String purchaseType, String orderStr) throws HSKDBException;
	/**
	 * 根据视图查询商品List
	 * @param mdMaterielFormatView
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdMaterielFormatView> getListModelByMdMdMaterielFormatView(MdMaterielFormatView mdMaterielFormatView) throws HSKDBException;
	/**
	 * 修改所有商品供应商名称
	 * @param applicantName
	 * @throws HSKDBException
	 */
	public void updateMdMaterielInfoApplicantName(String applicantName, Integer wzId) throws HSKDBException;
	/**
	 * 获取商品名称List
	 * @param matName
	 * @return
	 * @throws HSKDBException
	 */
	public List<Map<String,Object>> getMatNameList(String matName) throws HSKDBException;
	/**
	 * 修改商品商家状态
	 * @param wzId
	 * @param wzState
	 * @throws HSKDBException
	 */
	public void updateMatWzState(Integer wzId, String wzState) throws HSKDBException;

	/**
	 * 发现好货
	 * @param page
	 * @param limit
	 * @return
	 * @throws HSKDBException
	 */
    PagerModel getHotMaterielInfo(Integer page, Integer limit) throws HSKDBException;

	PagerModel getPagerModelByBrandSearch(String searchName, String s) throws HSKDBException;

	List<MdMaterielInfo> getListByMdMaterielInfoLimit(MdMaterielInfo att_mdMaterielInfo, Integer limit, Integer page) throws HSKDBException;

	List<Map<String, Object>> getHotMaterielInfoDetail(Integer page, Integer limit, String excludeIds) throws HSKDBException;

    List<Map<String, Object>> getMaterielInfoByHomeType(Integer index, Integer limit, Integer page) throws HSKDBException;

    PagerModel getPagerModelByBrandSearchNoLogin(String searchName, String s) throws HSKDBException;

    List<Map<String, Object>> getHotMaterielInfoDetailNoLogin(Integer page, Integer limit, String excludeIds) throws HSKDBException;

	List<Map<String, Object>> getMaterielInfoByHomeTypeNoLogin(Integer index, Integer limit, Integer page) throws HSKDBException;

    PagerModel getPagerModelBySearchNameNoLogin(String serachName, Integer mmtId, Integer wzId, String brand, String state, String wzState, String purchaseType, String orderStr) throws HSKDBException;

	MdMaterielInfoTemp findMdMaterielInfoByIdNoLogin(Integer wmsMiId) throws HSKDBException;

	// 实时查询
	PagerModel getPagerModelByInTimeSearchName(String searchName, String orderStr, String purchaseType) throws HSKDBException;
}