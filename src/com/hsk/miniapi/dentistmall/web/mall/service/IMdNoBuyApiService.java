package com.hsk.miniapi.dentistmall.web.mall.service;

import com.hsk.dentistmall.api.persistence.MdNoBuy;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

/**
 * MdNoBuy业务操作接口类 
 * @author  作者:张曙
 */
public interface IMdNoBuyApiService {
	
	/**
	 * 查找MdNoBuy对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mnbId  Integer类型(md_materiel_type表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer mnbId) throws HSKException;

	
	/**
	 * 查找MdNoBuy对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mnbId  Integer类型(md_materiel_type表主键)
	 * @return MdNoBuy md_materiel_type表记录
	 * @throws HSKException
	 */	
	public MdNoBuy findObject(Integer mnbId) throws HSKException;
	
	/**
	 * 根据md_materiel_type表主键删除MdNoBuy对象记录
     * @param  mnbId  Integer类型(md_materiel_type表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer mnbId) throws HSKException;
	
	/**
	 * 根据md_materiel_type表主键删除多条MdNoBuy对象记录
     * @param  mnbIds  Integer类型(md_materiel_type表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String mnbIds) throws HSKException;
	
	 /**
	 * 新增或修改md_materiel_type表记录 ,如果md_materiel_type表主键MdNoBuy.mnbId为空就是添加，如果非空就是修改
     * @param  att_MdNoBuy  MdNoBuy类型(md_materiel_type表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdNoBuy att_MdNoBuy) throws HSKException;

	/**
	 *根据MdNoBuy对象作为对(md_materiel_type表进行查询，获取分页对象
     * @param  att_MdNoBuy  MdNoBuy类型(md_materiel_type表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdNoBuy att_MdNoBuy) throws HSKException;
	/**
	 * 查询弃购车分页信息
	 * @param applicantName
	 * @param matName
	 * @param suiId
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdNoBuyMapPagerModel(String applicantName, String matName) throws HSKException;

	/**
	 * 根据登录用户删除所有弃购车商品
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage delectAllObjectBySuiId() throws HSKException;

	/**
	 * 根据登录用户删除所选择商品
	 * @param suiId
	 * @param mnbIds
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteMdNoBuyBySuiIdsAndMnbIds(String mnbIds) throws HSKException;

	/**
	 * 根据条件查询
	 * @param searchName
	 * @return
	 * @throws HSKException
	 */
	public PagerModel searchMdNoBuyBySearch(String searchName) throws HSKException;
}
