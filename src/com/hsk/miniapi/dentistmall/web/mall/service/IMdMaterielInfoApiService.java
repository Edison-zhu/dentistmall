package com.hsk.miniapi.dentistmall.web.mall.service;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.service.IDSTService;
import com.hsk.exception.HSKException;

/**
 * mall业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-29 10:30:22
 */
public interface IMdMaterielInfoApiService {


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
	
	
	
	/**
	 * 根据md_materiel_info表主键删除多条MdMaterielInfo对象记录
     * @param  wmsMiIds  Integer类型(md_materiel_info表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wmsMiIds) throws HSKException;
	
	 /**
	 * 新增或修改md_materiel_info表记录 ,如果md_materiel_info表主键MdMaterielInfo.wmsMiId为空就是添加，如果非空就是修改
     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdMaterielInfo att_MdMaterielInfo) throws HSKException;
	/**
	 * 更改数据状态
	 * @param wmsMiId
	 * @param state
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage updateObjectState(Integer wmsMiId, String state) throws HSKException;

	/**
	 *根据MdMaterielInfo对象作为对(md_materiel_info表进行查询，获取分页对象
     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdMaterielInfo att_MdMaterielInfo) throws HSKException;
	/**
	 *根据MdMaterielInfo对象作为对(md_materiel_info表进行查询，获取本医院添加的分页对象
     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getMyPagerModelObject(MdMaterielFormatView mdMaterielFormatView) throws HSKException;
	/**
	 * 查询同类热门
	 * @param att_MdMaterielInfo
	 * @param size
	 * @return
	 * @throws HSKException
	 */
	public List<MdMaterielInfo> getHotMdMaterielInfoListBySize(MdMaterielInfo att_MdMaterielInfo, Integer size) throws HSKException;
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

	/**
	 * 获取商品详情
	 * @param wmsMiId
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage getMaterielXiangxi(Integer wmsMiId) throws HSKException;
	
}