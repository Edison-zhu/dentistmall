package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.persistence.MdMaterielType;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

public interface IMdMaterielTypeApiDao {
	/**
	 * 根据主键查询MdMaterielType实体
	 * @param mmtId
	 * @return
	 * @throws HSKDBException
	 */
	public MdMaterielType findMdMaterielTypeById(Integer mmtId) throws HSKDBException;
	/**
	 * 根据主键删除MdMaterielType实体
	 * @param mmtId
	 * @throws HSKDBException
	 */
	public void deleteMdMaterielTypeById(Integer mmtId) throws HSKDBException;
	/**
	 * 根据主键修改MdMaterielType实体
	 * @param mmtId
	 * @param mdMaterielType
	 * @return
	 * @throws HSKDBException
	 */
	public MdMaterielType updateMdMaterielTypeById(Integer mmtId, MdMaterielType mdMaterielType) throws HSKDBException;
	/**
	 * 新建MdMaterielType实体
	 * @param mdMaterielType
	 * @return
	 * @throws HSKDBException
	 */
	public Integer saveMdMaterielType(MdMaterielType mdMaterielType) throws HSKDBException;
	/**
	 * 新增或修改MdMaterielType实体
	 * @param mdMaterielType
	 * @return
	 * @throws HSKDBException
	 */
	public MdMaterielType saveOrUpdateMdMaterielType(MdMaterielType mdMaterielType) throws HSKDBException;
	/**
	 * 查询MdMaterielType实体分页信息
	 * @param mdMaterielType
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdMaterielType(MdMaterielType mdMaterielType) throws HSKDBException;
	/**
	 * 查询MdMaterielType实体list信息
	 * @param mdMaterielType
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdMaterielType> getListByMdMaterielType(MdMaterielType mdMaterielType) throws HSKDBException;
	/**
	 * 根据父节点ID获取子节点列表
	 * @param mdMmtId
	 * @return
	 * @throws HSKDBException
	 */
	public List<Map<Object,Object>> getTreeListByMdMmtId(Integer mdMmtId) throws HSKDBException;

    List<Map<Object, Object>> getTreeListByMdMmtIdNoLogin(Integer mdMmtId) throws HSKDBException;
}
