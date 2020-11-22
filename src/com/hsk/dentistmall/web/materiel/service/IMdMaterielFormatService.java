package com.hsk.dentistmall.web.materiel.service;

import java.util.List;

import com.hsk.dentistmall.api.persistence.MdMaterielFormat;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

/**
 * MdMaterielFormat业务操作接口类 
 * @author  作者:张曙
 */
public interface IMdMaterielFormatService {
	
	/**
	 * 查找MdMaterielFormat对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mmfId  Integer类型(md_materiel_format表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer mmfId) throws HSKException;

	/**
	 * 查询mmfCode是否重复
	 * @param mmfCode
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage checkFormMmfCode(String mmfCode, Integer wmsMiId, Integer mmfId) throws HSKException;

	SysRetrunMessage checkFormMmfName(String mmfName, Integer wmsMiId, Integer mmfId) throws HSKException;

	
	/**
	 * 查找MdMaterielFormat对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mmfId  Integer类型(md_materiel_format表主键)
	 * @return MdMaterielFormat md_materiel_format表记录
	 * @throws HSKException
	 */	
	public MdMaterielFormat findObject(Integer mmfId) throws HSKException;
	
	/**
	 * 根据md_materiel_format表主键删除MdMaterielFormat对象记录
     * @param  mmfId  Integer类型(md_materiel_format表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer mmfId) throws HSKException;
	
	/**
	 * 根据md_materiel_format表主键删除多条MdMaterielFormat对象记录
     * @param  mmfIds  Integer类型(md_materiel_format表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String mmfIds) throws HSKException;
	
	 /**
	 * 新增或修改md_materiel_format表记录 ,如果md_materiel_format表主键MdMaterielFormat.mmfId为空就是添加，如果非空就是修改
     * @param  att_MdMaterielFormat  MdMaterielFormat类型(md_materiel_format表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject( MdMaterielFormat att_MdMaterielFormat) throws HSKException;

	/**
	 * 集团医院门诊保存更新规格
	 * @param att_MdMaterielFormat
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage   saveOrUpdateObject1( MdMaterielFormat att_MdMaterielFormat) throws HSKException;
	/**
	 * 更改数据状态
	 * @param mmfId
	 * @param state
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage updateObjectState(Integer mmfId,String state) throws HSKException;
	/**
	 *根据MdMaterielFormat对象作为对(md_materiel_format表进行查询，获取分页对象
     * @param  att_MdMaterielFormat  MdMaterielFormat类型(md_materiel_format表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdMaterielFormat att_MdMaterielFormat) throws HSKException;

	/**
	 * 排除空名字
	 * @param att_MdMaterielFormat
	 * @return
	 * @throws HSKException
	 */
	PagerModel getPagerModelObject1 (MdMaterielFormat att_MdMaterielFormat) throws HSKException;
	/**
	 * 根据商品ID查询规格列表
	 * @param wmsMiId
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdMaterielFormatListByWmsMiId(Integer wmsMiId) throws HSKException;
	PagerModel getMdMaterielFormatPagerModelByWmsMiId(Integer wmsMiId, Integer mmfId) throws HSKException;
	/**
	 * 根据商品ID获取规格List
	 * @param wmsMiId
	 * @return
	 * @throws HSKException
	 */
	public List<MdMaterielFormat> getFormatListByWmsMiId(Integer wmsMiId) throws HSKException;
	/**
	 * 查询价格曲线图
	 * @param mmfIds
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getFormatPriceList(String mmfIds) throws HSKException;

	SysRetrunMessage saveOrUpdateBatchObject(Integer wmsMiId, String mmfIds, String prices, String retailPrices, String mmfCodes, String mmfNames) throws HSKException;
}
