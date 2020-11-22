package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.List;

import com.hsk.dentistmall.api.persistence.MdCommentMaterielView;
import com.hsk.dentistmall.api.persistence.MdCommentSupplierView;
import com.hsk.dentistmall.api.persistence.MdCommentTypeView;
import com.hsk.dentistmall.api.persistence.MdSiteComment;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

public interface IMdSiteCommentApiDao {
	/**
	 * 根据主键查询MdSiteComment实体
	 * @param mscId
	 * @return
	 * @throws HSKDBException
	 */
	public MdSiteComment findMdSiteCommentById(Integer mscId) throws HSKDBException;
	/**
	 * 根据主键删除MdSiteComment实体
	 * @param mscId
	 * @throws HSKDBException
	 */
	public void deleteMdSiteCommentById(Integer mscId) throws HSKDBException;
	/**
	 * 根据主键修改MdSiteComment实体
	 * @param mscId
	 * @param mdSiteComment
	 * @return
	 * @throws HSKDBException
	 */
	public MdSiteComment updateMdSiteCommentById(Integer mscId, MdSiteComment mdSiteComment) throws HSKDBException;
	/**
	 * 新建MdSiteComment实体
	 * @param mdSiteComment
	 * @return
	 * @throws HSKDBException
	 */
	public Integer saveMdSiteComment(MdSiteComment mdSiteComment) throws HSKDBException;
	/**
	 * 新增或修改MdSiteComment实体
	 * @param mdSiteComment
	 * @return
	 * @throws HSKDBException
	 */
	public MdSiteComment saveOrUpdateMdSiteComment(MdSiteComment mdSiteComment) throws HSKDBException;
	/**
	 * 查询MdCommentMaterielView分页列表
	 * @param mdCommentMaterielView
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getMdCommentMaterielViewPager(MdCommentMaterielView mdCommentMaterielView) throws HSKDBException;
	/**
	 * 查询MdCommentSupplierView分页列表
	 * @param mdCommentSupplierView
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getMdCommentSupplierViewPager(MdCommentSupplierView mdCommentSupplierView) throws HSKDBException;
	/**
	 * 查询MdCommentTypeView分页列表
	 * @param mdCommentTypeView
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getMdCommentTypeViewPager(MdCommentTypeView mdCommentTypeView) throws HSKDBException;

	/**
	 * 获取该节点下的最大序号
	 * @param swcId
	 * @return
	 * @throws HSKDBException
	 */
	public int getMaxOrderByParentId(Integer swcId) throws HSKDBException;
	/**
	 * 根据栏目Id查询商品列表
	 * @param swcId
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdCommentMaterielView> getMdCommentMaterielViewListBySwcId(Integer swcId, Integer number) throws HSKDBException;
	List<MdCommentMaterielView> getMdCommentMaterielViewListBySwcId1(Integer swcId, Integer number) throws HSKDBException;
	/**
	 * 根据栏目ID查询供应商列表
	 * @param swcId
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdCommentSupplierView> getMdCommentSupplierViewListBySwcId(Integer swcId, Integer number) throws HSKDBException;
	/**
	 * 根据商品类型查询供应商列表
	 * @param swcId
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdCommentTypeView> getMdCommentTypeViewListBySwcId(Integer swcId, Integer number) throws HSKDBException;
}
