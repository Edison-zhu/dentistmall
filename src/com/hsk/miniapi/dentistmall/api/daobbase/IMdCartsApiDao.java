package com.hsk.miniapi.dentistmall.api.daobbase;

import com.hsk.dentistmall.api.persistence.MdCarts;
import com.hsk.dentistmall.api.persistence.MdFavorites;
import com.hsk.dentistmall.api.persistence.MdPurchased;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

public interface IMdCartsApiDao {
	/**
	 * 根据id查询购物车
	 * @param suiId
	 * @param mcId
	 * @return
	 * @throws HSKDBException
	 */
	MdCarts findMdCartsById(Integer suiId, Integer mcId) throws HSKDBException;

	/**
	 * 根据mmfid规格查询
	 * @param suiId
	 * @param mmfId
	 * @return
	 * @throws HSKDBException
	 */
	List<MdCarts> findMdCartsByMmfIdHql(Integer suiId, Integer mmfId) throws HSKDBException;

	/**
	 * 根据规格mmfid查询
	 * @param mmfId
	 * @param suiId
	 * @return
	 * @throws HSKDBException
	 */
	List<Map<String, Object>> findMdCartsByMmfId(Integer suiId, Integer mmfId) throws HSKDBException;

	List<MdCarts> findMdCartsAll(Integer suiId) throws HSKDBException;

	/**
	 * 根据id删除购物车
	 * @param suiId
	 * @param mcId
	 * @throws HSKDBException
	 */
	void deleteMdCartsById(Integer suiId, Integer mcId) throws HSKDBException;

	/**
	 * 根据id删除购物车
	 * @param suiId
	 * @throws HSKDBException
	 */
	void deleteMdCartsAll(Integer suiId) throws HSKDBException;

	/**
	 * 根据用户id和购物车id删除
	 * @param suiId
	 * @param mcIds
	 * @throws HSKDBException
	 */
	void deleteMdCartsBySuiIdsAndMcIds(Integer suiId, String mcIds) throws HSKDBException;

	/**
	 * 保存
	 * @param mdCarts
	 * @return
	 * @throws HSKDBException
	 */
	Integer saveMdCarts(MdCarts mdCarts) throws HSKDBException;

	/**
	 * 更新or保存
	 * @param mdCarts
	 * @return
	 * @throws HSKDBException
	 */
	MdCarts saveOrUpdateMdCarts(MdCarts mdCarts) throws HSKDBException;

	/**
	 * 根据id更新
	 * @param mcId
	 * @param mdCarts
	 * @return
	 * @throws HSKDBException
	 */
	MdCarts updateMdCartsById(Integer mcId, MdCarts mdCarts) throws HSKDBException;

	/**
	 * 查询购物车数量和总价
	 * @param sui_id
	 * @return
	 * @throws HSKDBException
	 */
	List<Map<String, Object>> getCartsCountAndPrice(Integer sui_id) throws HSKDBException;

	/**
	 * 根据mmfid删除
	 * @param sui_id
	 * @param mmfIds
	 */
	void deleteMdCartsBySuiIdsAndMfIds(Integer sui_id, String mmfIds) throws HSKDBException;

    Integer getCartCountOnlyByMmfId(Integer mmfId, Integer suiId) throws HSKDBException;
	//查询已购商品列表
	public List<Map<String, Object>> getPurchasedInfo(Integer suiId) throws HSKDBException;
	//通过规格与用户ID查询已购表中型号是否存在
	public Integer getPurchasedMmfId(Integer mmfId,Integer suiId) throws HSKDBException;
	//通过型号ID查询MdPurchased
	MdPurchased findPurchasedMmfId(Integer suiId, Integer mmfId) throws HSKDBException;
	//查询已购商品列表2
	public List<Map<String, Object>> getPurchasedInfoList(Integer mmfId,Integer suiId,String matNameAndCode,Integer limit,Integer page,Integer date1) throws HSKDBException;
	//删除已购商品
	void deletePurchasedInfoById(Integer suiId,Integer purchasedId) throws HSKDBException;
}


