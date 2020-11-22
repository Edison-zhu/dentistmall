package com.hsk.dentistmall.web.mall.service;

import com.hsk.dentistmall.api.persistence.MdFavorites;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;


public interface IMdFavoritesService {
	/**
	 * 保存收藏夹信息
	 * @param wmsMiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveMdFavorites(Integer wmsMiId) throws HSKException;
	
	public SysRetrunMessage deleteObject(Integer mfId) throws HSKException;

	/**
	 * 通过登录用户删除所有收藏
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage delectAllObjectBySuiId() throws HSKException;

	/**
	 * 通过登录用户删除选择的收藏
	 * @param mfIds
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteMdFavoritesBySuiIdsAndMfIds(String mfIds) throws HSKException;
	
	public PagerModel getPagerModelObject (MdFavorites att_MdFavorites) throws HSKException;

	/**
	 * 检查是否收藏过
	 * @param wmsMiId
	 * @return
	 */
	public SysRetrunMessage checkFavorites(Integer wmsMiId) throws HSKException;
	/**
	 * 查询收藏夹商品分页信息
	 * @param applicantName
	 * @param matName
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdFavoritesMapPagerModel(String applicantName,String matName) throws HSKException;

	/**
	 * 根据条件查询
	 * @param searchName
	 * @return
	 * @throws HSKException
	 */
	public PagerModel searchMdFavoritesBySearch(String searchName) throws HSKException;
	//批量移除后台收藏夹数据
	public SysRetrunMessage deleteMdCollectBySuiIdsAndMfIds(String mfIds) throws HSKException;

	//批量移除复选框后台收藏夹数据
	public SysRetrunMessage deleteMdCollectBySuiIdsAndMcIds(String micIds) throws HSKException;
}
