package com.hsk.dentistmall.web.outOrder.service;

import com.hsk.dentistmall.api.persistence.MdInventoryCollect;
import com.hsk.dentistmall.api.persistence.MdInventoryCollectView;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

public interface IMdInventoryCollectService {
	/**
	 * 保存收藏信息
	 * @param att_MdInventoryCollect
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage  saveOrUpdateObject( MdInventoryCollect att_MdInventoryCollect) throws HSKException;
	/**
	 * 删除收藏信息
	 * @param momId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer micId) throws HSKException;
	/**
	 * 获取收藏库存列表
	 * @param mdInventoryCollectView
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdInventoryCollectView mdInventoryCollectView) throws HSKException;
}
