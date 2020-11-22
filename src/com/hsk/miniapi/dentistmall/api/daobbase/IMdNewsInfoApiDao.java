package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.List;

import com.hsk.dentistmall.api.persistence.MdMaterielFormatView;
import com.hsk.dentistmall.api.persistence.MdNewsInfo;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

public interface IMdNewsInfoApiDao {
	
	/**
	 * 根据主键查询MdNewsInfo实体
	 * @param mniId
	 * @return
	 * @throws HSKDBException
	 */
	public MdNewsInfo findMdNewsInfoById(Integer mniId) throws HSKDBException;
	/**
	 * 根据主键删除MdNewsInfo实体
	 * @param mniId
	 * @throws HSKDBException
	 */
	public void deleteMdNewsInfoById(Integer mniId) throws HSKDBException;
	/**
	 * 根据主键修改MdNewsInfo实体
	 * @param mniId
	 * @param mdNewsInfo
	 * @return
	 * @throws HSKDBException
	 */
	public MdNewsInfo updateMdNewsInfoById(Integer mniId, MdNewsInfo mdNewsInfo) throws HSKDBException;
	/**
	 * 新建MdNewsInfo实体
	 * @param mdNewsInfo
	 * @return
	 * @throws HSKDBException
	 */
	public Integer saveMdNewsInfo(MdNewsInfo mdNewsInfo) throws HSKDBException;
	/**
	 * 新增或修改MdNewsInfo实体
	 * @param mdNewsInfo
	 * @return
	 * @throws HSKDBException
	 */
	public MdNewsInfo saveOrUpdateMdNewsInfo(MdNewsInfo mdNewsInfo) throws HSKDBException;
	/**
	 * 设置消息状态为已读
	 * @param mdNewsInfo
	 * @return
	 * @throws HSKDBException
	 */
	public void updateMdNewsInfoViewState(MdNewsInfo mdNewsInfo) throws HSKDBException;
	//查询消息列表
	public List<MdNewsInfo> getMdNewsInfoList(MdNewsInfo mdNewsInfo) throws HSKDBException;
	/**
	 * 查询缺货提醒分页信息
	 * @param mdNewsInfo
	 * @param mdMaterielFormatView
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getInventoryNewsPagerModel(MdNewsInfo mdNewsInfo, MdMaterielFormatView mdMaterielFormatView, String purchaseType, Integer oldId) throws HSKDBException;

}
