package com.hsk.dentistmall.web.transaction.service;

import com.hsk.dentistmall.api.persistence.MdMaterielFormatView;
import com.hsk.dentistmall.api.persistence.MdNewsInfo;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

public interface IMdNewsInfoService {
	/**
	 * 查看消息信息
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getMdNewsInfoList() throws HSKException;
	/**
	 * 保存库存缺少消息提醒
	 * @param mmfId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveInventoryNew(Integer mmfId) throws HSKException;
	/**
	 * 查询缺货消息提醒分页信息
	 * @param mdNewsInfo
	 * @param mdMaterielFormatView
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getInventoryNewPagerModel(MdNewsInfo mdNewsInfo, MdMaterielFormatView mdMaterielFormatView) throws HSKException;
	/**
	 * 统计缺货提醒
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getInventoryNewList() throws HSKException;
	/**
	 * 保存提醒状态
	 * @param mniId
	 * @param isView
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveMdNewsInfoView(Integer mniId,Integer isView) throws HSKException;
}
