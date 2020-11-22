package com.hsk.dentistmall.api.daobbase;

import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

import java.util.List;
import java.util.Map;

/**
 * md_materiel_info表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-29 10:30:22
 */
public interface IOperatorsMaterielInfoDao {

//	/**
//	 * 根据md_materiel_info表主键查找MdMaterielInfo对象记录
//	 * @param  WmsMiId  Integer类型(md_materiel_info表主键)
//	 * @return MdMaterielInfo md_materiel_info表记录
//	 * @throws HSKDBException
//	 */
	public MdMaterielInfo findMdMaterielInfoById(Integer WmsMiId) throws HSKDBException;
//
//	/**
//	 * 根据md_materiel_info表主键删除MdMaterielInfo对象记录
//     * @param  WmsMiId  Integer类型(md_materiel_info表主键)
//	 * @throws HSKDBException
//	 */
//	public void deleteMdMaterielInfoById(Integer WmsMiId) throws HSKDBException;
//
	/**
 	 * 根据md_materiel_info表主键修改MdMaterielInfo对象记录
	 * @param  WmsMiId  Integer类型(md_materiel_info表主键)
	 * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
	 * @return MdMaterielInfo  修改后的MdMaterielInfo对象记录
	 * @throws HSKDBException
	 */
	public  MdMaterielInfo updateMdMaterielInfoById(Integer WmsMiId, MdMaterielInfo att_MdMaterielInfo) throws HSKDBException;
//
//	/**
//	 * 新增md_materiel_info表 记录
//     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
//     * @return md_materiel_info表主键值
//	 * @throws HSKDBException
//	 */
//	public Integer saveMdMaterielInfo(MdMaterielInfo att_MdMaterielInfo) throws HSKDBException;
//
//	 /**
//	 * 新增或修改md_materiel_info表记录 ,如果md_materiel_info表主键MdMaterielInfo.WmsMiId为空就是添加，如果非空就是修改
//     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
//     * @return MdMaterielInfo  修改后的MdMaterielInfo对象记录
//	 * @throws HSKDBException
//	 */
//	public  MdMaterielInfo saveOrUpdateMdMaterielInfo(MdMaterielInfo att_MdMaterielInfo) throws HSKDBException;

	/**
	 *根据MdMaterielInfo对象作为对(md_materiel_info表进行查询，获取分页对象
     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdMaterielInfo(MdMaterielInfo att_MdMaterielInfo) throws HSKDBException;

	//运营人员统计所有商品上下架数量
	public	Integer getMdMaterielInfoCount(Integer state) throws HSKDBException;

	//运营人员查看sku价格
	public List<Map<String, Object>> getMdmaterielFormatList(Integer wmsmiId,String mmfCode,Integer limit,Integer page) throws HSKDBException;
	//查询品牌列表
	public List<Map<String, Object>> getBrandList(String brand) throws HSKDBException;
	//查询商家列表
	public List<Map<String, Object>> getApplicantNameList(String applicantName) throws HSKDBException;
	//查询日志列表
	public List<Map<String, Object>> getMdMaterielInfoLog(Integer wmsmiId,Integer limit,Integer page) throws HSKDBException;
	//查询品牌管理
	public List<Map<String, Object>> getMdMaterielBrandInfo(String mbdName,Integer state,Integer limit,Integer page) throws HSKDBException;
	//查询品牌管理中的商品数量
	public	Integer getMdMaterielState(Integer mbdId) throws HSKDBException;
	//通过品牌Id查询
	public MdMaterielBrand findMdMaterielBrandInfoById(Integer mbdId) throws HSKDBException;
	//通过品牌名称查询
	public Integer findMdMaterielBrandInfoByName(String  mbdName) throws HSKDBException;
	//通过品牌Id修改
	public  MdMaterielBrand updateMaterielBrandInfoById(Integer mbdId, MdMaterielBrand mdMaterielBrand) throws HSKDBException;
	//删除品牌
	public void deleteMaterielBrandInfoById(Integer mbdId) throws HSKDBException;
	//查询参数管理中  参数列表
	public List<Map<String, Object>> getMdMaterielParameter(String mmpName,String isRequired,String state,Integer limit,Integer page) throws HSKDBException;
	//通过参数Id查询
	public MdMaterielParameter findMdMaterielParameterById(Integer mmpId) throws HSKDBException;
	//通过参数id列表查询
	List<MdMaterielParameter> findMdMaterielParameterByIds(String mmpIds) throws HSKDBException;
	//修改参数
	public  MdMaterielParameter updateMdMaterielParameterById(Integer mmpId, MdMaterielParameter mdMaterielParameter) throws HSKDBException;
	//删除参数
	public void deleteMdMaterielParameterById(String mmpId) throws HSKDBException;
	//查询是否被引用
    Integer getMdMaterielParameterRefCountOne(String mmpId) throws HSKDBException;
	//查询属性列表
	public List<Map<String, Object>> getmdMaterielAttributeInfo(String mmaName,String state,String isOptional,Integer limit,Integer page) throws HSKDBException;
	//查询属详情列表
	public List<Map<String, Object>> getmdMaterielAttributeMxInfo(Integer mmaId,String mmamxName,String state,String isOptional,Integer limit,Integer page) throws HSKDBException;

	//通过属性Id查询
	public mdMaterielAttribute findMdMaterielAttributeById(Integer mmaId) throws HSKDBException;
	//通过属性ID修改
	public  mdMaterielAttribute updateMdMaterielAttributeById(Integer mmaId, mdMaterielAttribute att_mdMaterielAttribute) throws HSKDBException;
	//通过属性ID 删除
	public void deleteMdMaterielAttributerById(Integer mmaId) throws HSKDBException;

	//通过属性Id查询
	public mdMaterielAttributeMx findMdMaterielAttributeMxById(Integer mmamxId) throws HSKDBException;
	//通过属性ID修改
	public  mdMaterielAttributeMx updatemdMaterielAttributeMxById(Integer mmamxId, mdMaterielAttributeMx att_mdMaterielAttributeMx) throws HSKDBException;
	//通过属性ID 删除
	public void deleteMdMaterielAttributeMxById(Integer mmamxId) throws HSKDBException;
	//保存属性详情时 查询属性详情数据
    public	String getMmaName(Integer mmaId) throws HSKDBException;
	// 获取属性被商品模型引用
    Integer getMdMaterielAttrRefCount(String mmaIds) throws HSKDBException;
	// 获取详细属性被商品模型引用
	Integer getMdMaterielAttrMxRefCount(Integer mmamxId) throws HSKDBException;
	//获取品牌被商品模型引用数量
	Integer getMdMaterielBrandRefCount(Integer mbdId) throws HSKDBException;

	//通过参数名称查询
	public Integer findMdMaterielParameterInfoByName(String  mmpName) throws HSKDBException;
	//通过属性名称查询
	public Integer findMdMaterielAttributeInfoByName(String  mmaName,Integer mmaId) throws HSKDBException;
}