package com.hsk.dentistmall.api.daobbase;

import java.util.List;

import com.hsk.dentistmall.api.persistence.MdFavorites;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

public interface IMdFavoritesDao {
	
	public MdFavorites findMdFavoritesById(Integer mfId) throws HSKDBException;

	public void deleteMdFavoritesById(Integer mfId) throws HSKDBException;

	/**
	 * 通过登录用户删除所有收藏
	 * @param suiId
	 * @throws HSKDBException
	 */
	public void delectAllObjectBySuiId(Integer suiId) throws HSKDBException;

	/**
	 * 通过登录用户删除选择收藏
	 * @param suiId
	 * @param mfIds
	 * @throws HSKDBException
	 */
	public void deleteMdFavoritesBySuiIdsAndMfIds(Integer suiId, String mfIds) throws HSKDBException;
	
	public MdFavorites updateMdFavoritesById(Integer mfId,MdFavorites mdFavorites) throws HSKDBException;
	
	public Integer saveMdFavorites(MdFavorites MdFavorites) throws HSKDBException;

	
	public MdFavorites saveOrUpdateMdFavorites(MdFavorites mdFavorites) throws HSKDBException;
	
	public PagerModel getPagerModelByMdFavorites(MdFavorites mdFavorites) throws HSKDBException;

	public List<MdFavorites> getListByMdFavorites (MdFavorites mdFavorites) throws HSKDBException;
	/**
	 * 查询收藏夹商品分页信息
	 * @param applicantName 供应商名称
	 * @param matName 商品名称
	 * @param suiId 用户ID
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getMdFavoritesMapPagerModel(String applicantName,String matName,Integer suiId) throws HSKDBException;

	/**
	 * 根据条件查询
	 * @param suiId
	 * @param searchName
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel searchMdFavoritesBySearch(Integer suiId, String searchName) throws HSKDBException;

	//批量移除后台收藏数据
	public void deleteMdCollectBySuiIdsAndMfIds(Integer suiId, String mfIds) throws HSKDBException;
	//批量移除后台收藏数据
	public void deleteMdCollectBySuiIdsAndMicIds(Integer suiId, String micIds) throws HSKDBException;
}


