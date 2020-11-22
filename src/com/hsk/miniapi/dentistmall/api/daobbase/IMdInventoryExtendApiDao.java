package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.List;

import com.hsk.dentistmall.api.persistence.MdInventoryEnterwarehouserViewEntity;
import com.hsk.dentistmall.api.persistence.MdInventoryExtend;
import com.hsk.dentistmall.api.persistence.MdInventoryExtendView;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * 库存字表管理
 * @author Administrator
 *
 */
public interface IMdInventoryExtendApiDao {
	/**
	 * 根据库存ID，单号查询库存字表信息
	 * @param wzId
	 * @param relatedCode
	 * @return
	 * @throws HSKDBException
	 */
	public MdInventoryExtend getMdInventoryExtendByWiId(Integer wzId, String relatedCode) throws HSKDBException;
	/**
	 * 根据库存ID查询选择库存详细信息
	 * @param wiId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdInventoryExtend> getMdInventoryExtendByWiIdToSel(Integer wiId, String relatedCode, String startDate, String endDate) throws HSKDBException;
	/**
	 * 查询所有的库存详细列表
	 * @return
	 * @throws HSKDBException
	 */
	public List<MdInventoryExtend> getAllList()throws HSKDBException;
	/**
	 * 查询库存详细列表视图分页信息
	 * @param mdInventoryExtendView
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getMdInventoryViewPager(MdInventoryExtendView mdInventoryExtendView, String startDate, String endDate) throws HSKDBException;

    List<MdInventoryExtend> getExtendEnterViewPagerModel(Integer wiId, String relatedCode, String startDate, String endDate) throws HSKDBException;

	List<MdInventoryEnterwarehouserViewEntity> getExtendEnterWarehouseViewPagerModel(Integer wiId, String relatedCode, String startDate, String endDate) throws HSKDBException;
}
