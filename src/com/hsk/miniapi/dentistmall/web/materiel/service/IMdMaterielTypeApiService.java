package com.hsk.miniapi.dentistmall.web.materiel.service;

import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.persistence.MdMaterielType;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

/**
 * MdMaterielType业务操作接口类 
 * @author  作者:张曙
 */
public interface IMdMaterielTypeApiService {
	
	/**
	 * 查找MdMaterielType对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mmtId  Integer类型(md_materiel_type表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer mmtId) throws HSKException;

	
	/**
	 * 查找MdMaterielType对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mmtId  Integer类型(md_materiel_type表主键)
	 * @return MdMaterielType md_materiel_type表记录
	 * @throws HSKException
	 */	
	public MdMaterielType findObject(Integer mmtId) throws HSKException;
	
	/**
	 * 根据md_materiel_type表主键删除MdMaterielType对象记录
     * @param  mmtId  Integer类型(md_materiel_type表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer mmtId) throws HSKException;
	
	
	
	/**
	 * 根据md_materiel_type表主键删除多条MdMaterielType对象记录
     * @param  mmtIds  Integer类型(md_materiel_type表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String mmtIds) throws HSKException;
	
	 /**
	 * 新增或修改md_materiel_type表记录 ,如果md_materiel_type表主键MdMaterielType.mmtId为空就是添加，如果非空就是修改
     * @param  att_MdMaterielType  MdMaterielType类型(md_materiel_type表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdMaterielType att_MdMaterielType) throws HSKException;

	/**
	 *根据MdMaterielType对象作为对(md_materiel_type表进行查询，获取分页对象
     * @param  att_MdMaterielType  MdMaterielType类型(md_materiel_type表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdMaterielType att_MdMaterielType) throws HSKException;
	/**
	 * 根据父节点ID获取子节点列表
	 * @param mdMmtId
	 * @return
	 * @throws HSKException
	 */
	public List<Map<Object, Object>> getTreeListByMdMmtId(Integer id) throws HSKException;
	/**
	 * 将类别数据保存到JSON文件中
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveDataToJosn() throws HSKException;

	public List<MdMaterielType> getListObject(MdMaterielType att_MdMaterielType) throws HSKException;

	public List<MdMaterielType> getListObjectByMatName(MdMaterielType att_MdMaterielType, String matName) throws HSKException;

}
