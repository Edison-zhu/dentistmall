package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.List;
import com.hsk.dentistmall.api.persistence.MdNoBuy;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;

public interface IMdNoBuyApiDao {
	/**
	 * 根据主键查询MdNoBuy实体
	 * @param mnbId
	 * @return
	 * @throws HSKDBException
	 */
	public MdNoBuy findMdNoBuyById(Integer mnbId) throws HSKDBException;
	/**
	 * 根据主键删除MdNoBuy实体
	 * @param mnbId
	 * @throws HSKDBException
	 */
	public void deleteMdNoBuyById(Integer mnbId) throws HSKDBException;

	/**
	 * 根据登录用户删除所有
	 * @param suiId
	 * @throws HSKException
	 */
	public void deleteMdNoBuyBySuiId(Integer suiId) throws HSKDBException;

	/**
	 * 根据登录用户删除选择的物品
	 * @param suiIds
	 * @param mnbIds
	 * @throws HSKDBException
	 */
	public void deleteMdNoBuyBySuiIdsAndMnbIds(Integer suiId, String mnbIds) throws  HSKDBException;
	/**
	 * 根据主键修改MdNoBuy实体
	 * @param mnbId
	 * @param mdNoBuy
	 * @return
	 * @throws HSKDBException
	 */
	public MdNoBuy updateMdNoBuyById(Integer mnbId, MdNoBuy mdNoBuy) throws HSKDBException;
	/**
	 * 新建MdNoBuy实体
	 * @param mdNoBuy
	 * @return
	 * @throws HSKDBException
	 */
	public Integer saveMdNoBuy(MdNoBuy mdNoBuy) throws HSKDBException;
	/**
	 * 新增或修改MdNoBuy实体
	 * @param mdNoBuy
	 * @return
	 * @throws HSKDBException
	 */
	public MdNoBuy saveOrUpdateMdNoBuy(MdNoBuy mdNoBuy) throws HSKDBException;
	/**
	 * 查询MdNoBuy实体分页信息
	 * @param mdNoBuy
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdNoBuy(MdNoBuy mdNoBuy) throws HSKDBException;
	/**
	 * 查询MdNoBuy实体list信息
	 * @param mdNoBuy
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdNoBuy> getListByMdNoBuy(MdNoBuy mdNoBuy) throws HSKDBException;
	/**
	 * 根据用户和商品类别删除
	 * @param suiId
	 * @param mmfIds
	 * @throws HSKDBException
	 */
	public void delMdNoBuyByStrs(Integer suiId, String mmfIds) throws HSKDBException;

	public PagerModel getMdNoBuyMapPagerModel(String applicantName, String matName, Integer suiId) throws HSKDBException;

	/**
	 * 根据条件查询
	 * @param suiId
	 * @param searchName
	 * @return
	 */
    PagerModel searchMdNoBuyBySearch(Integer suiId, String searchName) throws HSKDBException;
}
