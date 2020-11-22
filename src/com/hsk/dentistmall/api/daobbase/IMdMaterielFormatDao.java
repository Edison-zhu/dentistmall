package com.hsk.dentistmall.api.daobbase;

import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.persistence.MdMaterielFormat;
import com.hsk.dentistmall.api.persistence.MdPriceLog;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

public interface IMdMaterielFormatDao {
	/**
	 * 根据主键查询MdMaterielFormat实体
	 * @param mmfId
	 * @return
	 * @throws HSKDBException
	 */
	public MdMaterielFormat findMdMaterielFormatById(Integer mmfId) throws HSKDBException;
	/**
	 * 根据主键删除MdMaterielFormat实体
	 * @param mmfId
	 * @throws HSKDBException
	 */
	public void deleteMdMaterielFormatById(Integer mmfId) throws HSKDBException;
	/**
	 * 根据主键修改MdMaterielFormat实体
	 * @param mmfId
	 * @param mdMaterielFormat
	 * @return
	 * @throws HSKDBException
	 */
	public MdMaterielFormat updateMdMaterielFormatById(Integer mmfId,MdMaterielFormat mdMaterielFormat) throws HSKDBException;
	/**
	 * 新建MdMaterielFormat实体
	 * @param mdMaterielFormat
	 * @return
	 * @throws HSKDBException
	 */
	public Integer saveMdMaterielFormat(MdMaterielFormat mdMaterielFormat) throws HSKDBException;
	/**
	 * 新增或修改MdMaterielFormat实体
	 * @param mdMaterielFormat
	 * @return
	 * @throws HSKDBException
	 */
	public MdMaterielFormat saveOrUpdateMdMaterielFormat(MdMaterielFormat mdMaterielFormat) throws HSKDBException;
	/**
	 * 查询MdMaterielFormat实体分页信息
	 * @param mdMaterielFormat
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdMaterielFormat(MdMaterielFormat mdMaterielFormat) throws HSKDBException;
	/**
	 * 查询MdMaterielFormat实体list信息
	 * @param mdMaterielFormat
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdMaterielFormat> getListByMdMaterielFormat (MdMaterielFormat mdMaterielFormat) throws HSKDBException;
	/**
	 * 查询价格变动历史
	 * @param mdPriceLog
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdPriceLog> getListMdPriceLog(MdPriceLog mdPriceLog) throws HSKDBException;

	/**
	 * 检查是否存在同mmfcode的规格
	 * @param mmfCode
	 * @return
	 * @throws HSKDBException
	 */
    List<MdMaterielFormat> findMdMaterielFormatByMffCode(String mmfCode, Integer wmsMiId, Integer mmfId, String purchaseType, Integer wzId) throws HSKDBException;

	List<MdMaterielFormat> findMdMaterielFormatByMffName(String mmfName, Integer wmsMiId, Integer mmfId, String purchaseType, Integer wzId) throws HSKDBException;

    List<MdMaterielFormat> findMdMaterielFormatByMffName1(String formatName, Integer wmsMiId) throws HSKDBException;

	List<Map<String, Object>> getMdMaterielFormatMapList(Integer mmfId) throws HSKDBException;

    PagerModel getPagerModelByMdMaterielFormat1(MdMaterielFormat att_mdMaterielFormat) throws HSKDBException;
}
