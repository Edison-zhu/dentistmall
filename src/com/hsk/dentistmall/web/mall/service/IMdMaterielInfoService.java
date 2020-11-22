package com.hsk.dentistmall.web.mall.service;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.service.IDSTService;
import com.hsk.exception.HSKException;

/**
 * mall业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-29 10:30:22
 */
public interface IMdMaterielInfoService{


		/**
	 * 查找MdMaterielInfo对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wmsMiId  Integer类型(md_materiel_info表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer wmsMiId) throws HSKException;

	
	/**
	 * 查找MdMaterielInfo对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wmsMiId  Integer类型(md_materiel_info表主键)
	 * @return MdMaterielInfo md_materiel_info表记录
	 * @throws HSKException
	 */	
	public MdMaterielInfo findObject(Integer wmsMiId) throws HSKException;
	
	/**
	 * 根据md_materiel_info表主键删除MdMaterielInfo对象记录
     * @param  wmsMiId  Integer类型(md_materiel_info表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wmsMiId) throws HSKException;
	SysRetrunMessage deleteObjectInventory(Integer wmsMiId) throws HSKException;
	
	
	/**
	 * 根据md_materiel_info表主键删除多条MdMaterielInfo对象记录
     * @param  wmsMiIds  Integer类型(md_materiel_info表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wmsMiIds) throws HSKException;
	SysRetrunMessage deleteAllObjectInventory(String wmsMiIds) throws HSKException;
	
	 /**
	 * 新增或修改md_materiel_info表记录 ,如果md_materiel_info表主键MdMaterielInfo.wmsMiId为空就是添加，如果非空就是修改
     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject( MdMaterielInfo att_MdMaterielInfo) throws HSKException;

	/**
	 * 集团医院门诊添加物料
	 * @param att_MdMaterielInfo
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage   saveOrUpdateObject1( MdMaterielInfo att_MdMaterielInfo) throws HSKException;
	/**
	 * 更改数据状态
	 * @param wmsMiId
	 * @param state
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage updateObjectState(Integer wmsMiId,String state) throws HSKException;
	
	/**
	 *根据MdMaterielInfo对象作为对(md_materiel_info表进行查询，获取分页对象
     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdMaterielInfo att_MdMaterielInfo) throws HSKException;
	/**
	 *根据MdMaterielInfo对象作为对(md_materiel_info表进行查询，获取本医院添加的分页对象
     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getMyPagerModelObject (MdMaterielFormatView mdMaterielFormatView) throws HSKException;

	PagerModel getMyPagerModelObjectWithString(String wmsMiIds, String mmfIds) throws HSKException;

	/**
	 * 根据物料编号、名字、 拼音
	 * @param mdMaterielInfo
	 * @return
	 * @throws HSKException
	 */
	PagerModel getMyPagerModelObjectBySomeCase (MdMaterielInfo mdMaterielInfo) throws HSKException;
	/**
	 * 查询同类热门
	 * @param att_MdMaterielInfo
	 * @param size
	 * @return
	 * @throws HSKException
	 */
	public List<MdMaterielInfo> getHotMdMaterielInfoListBySize(MdMaterielInfo att_MdMaterielInfo,Integer size) throws HSKException;
	/**
	 * 根据物料类型查询物料列表
	 * @param labelInfo
	 * @return
	 * @throws HSKException
	 */
	public List<MdMaterielInfo> getMdMaterielInfoByLabelInfo(String labelInfo) throws HSKException;
	/**
	 * 根据名称获取物料名称列表
	 * @param matName
	 * @return
	 * @throws HSKException
	 */
	public List<Map<String,Object>> getMatNameList(String matName) throws HSKException;
	/**
	 * 根据物料名称查询物料信息
	 * @param matName
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getMdMaterielInfoByMatName(String matName) throws HSKException;
	
	public SysRetrunMessage exportList(MdMaterielFormatView mdMaterielFormatView) throws HSKException;
	/**
	 * 初始化商品和商家拼音信息
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage savePy() throws HSKException;

	SysRetrunMessage getPagerModelAllIventory(String matNames) throws HSKException;

	SysRetrunMessage saveAliasName(Integer wmsMiId, String aliasName) throws HSKException;
	public SysRetrunMessage saveDeleteAliasName(Integer wmsMiId, String aliasName) throws HSKException;

	/**
	 * 查询日志
	 * @param wmsMiId
	 * @return
	 * @throws HSKException
	 */
	PagerModel getPagerViewLogObject(Integer wmsMiId) throws HSKException;


	SysRetrunMessage updateMaterielPart(String wmsMiIds, Integer mdpsId, Integer mdpId) throws HSKException;

	SysRetrunMessage getPriceCount(Integer paiId) throws HSKException;

	SysRetrunMessage getMatCodeCount(String matCode, Integer wmsMiId) throws HSKException;

	/*
	 * 2020-07-29
	 * yanglei
	 * 运营人员获取商品信息列表
	 * */
	public PagerModel getOperatorsPagerModelObject (MdMaterielInfo att_MdMaterielInfo) throws HSKException;
	//运营人员统计所有商品上下架数量
	public  SysRetrunMessage getMdMaterielInfoCount() throws HSKException;
	//运营人员查看sku价格
	public PagerModel getMdmaterielFormatList(Integer wmsmiId,String mmfCode,Integer limit,Integer page) throws HSKException;
	//选择品牌接口
	public PagerModel getBrandList(String brand) throws HSKException;
	//选择商家接口
	public PagerModel getApplicantNameList(String applicantName) throws HSKException;
	//运营人员批量上下架
	public SysRetrunMessage saveMdMaterielInfo(String wmsmiIds,String matRemovalReasons) throws HSKException;
	//查看商品日志接口
	public PagerModel getMdMaterielInfoLog(Integer wmsmiId,Integer limit,Integer page) throws HSKException;
	//查看品牌管理中 品牌列表
	public PagerModel getMdMaterielBrandInfo(String mbdName,Integer state,Integer limit,Integer page) throws HSKException;
	//新增品牌或编辑品牌
	public SysRetrunMessage saveOrUpdateMdMaterielBrand(Integer mbdId,Integer lessenFilecodeId,String filecodeId,String  mbdName,String describe,String state,String mbdManufacturer ) throws HSKException;
	//品牌状态修改
	public SysRetrunMessage saveBrandState(Integer mbdId,String state) throws HSKException;
	//删除品牌
	public SysRetrunMessage deleteMdMaterielBrand(String mbdIds) throws HSKException;
	//编辑品牌数据接口
	public  SysRetrunMessage getUpdateMdMaterielBrand(Integer mbdId) throws HSKException;
	//查看参数管理中 参数列表
	public PagerModel getMdMaterielParameter(String mmpName,String isRequired,String state,Integer limit,Integer page) throws HSKException;
	//新增参数接口
	public SysRetrunMessage saveOrUpdateMdMaterielParameter(Integer mmpId,String isRequired,String mmpName,String isSearch,String relation,String optionaValue,String state,String mmpSort,String inputMode) throws HSKException;
	//删除参数接口
	public SysRetrunMessage deleteMdMaterielParameter(String mmpId) throws HSKException;
	//查看属性列表接口
	public PagerModel getmdMaterielAttributeInfo(String mmaName,String state,String isOptional,Integer limit,Integer page) throws HSKException;
	//查看属性列表详情接口
	public PagerModel getmdMaterielAttributeMxInfo(Integer mmaId,String mmamxName,String state,String isOptional,Integer limit,Integer page) throws HSKException;
	//新增属性接口
	public SysRetrunMessage saveOrUpdateMdMaterielAttribute(mdMaterielAttribute mdMaterielAttribute) throws HSKException;
	//新增或修改属性详情接口
	public SysRetrunMessage saveOrUpdateMdMaterielAttributeMx(mdMaterielAttributeMx att_mdMaterielAttribute,Integer fileId) throws HSKException;
	//修改属性可选值接口中的数据
//	public SysRetrunMessage saveOrUpdateMdMaterielAttributeMx1(mdMaterielAttributeMx att_mdMaterielAttributeMx) throws HSKException;
	//删除属性接口
	public SysRetrunMessage deleteMaterielAttribute(String mmaIds) throws HSKException;
	//删除属性详情接口
	public SysRetrunMessage deleteMaterielAttributeMx(String mmamxId) throws HSKException;
	//通过属性ID查询可选属性值
	public  SysRetrunMessage findAttributeOptionalValue(Integer mmaId) throws HSKException;
	//通过属性ID 查询编辑参数的接口
	public  SysRetrunMessage findMdMaterielParameter(Integer mmpId) throws HSKException;
	//通过品牌删除图片接口
	public  SysRetrunMessage deleteBrandLogo(Integer mbdId,Integer fileCode) throws HSKException;
	//通过属性Id修改状态
	public SysRetrunMessage saveOrUpdateMdMaterielAttributeState(Integer  mmaId,String state) throws HSKException;
	//获取新编号
	SysRetrunMessage getNewCode() throws HSKException;
	}