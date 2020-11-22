package com.hsk.dentistmall.api.daobbase;

import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.persistence.MdEnterOutCount;
import com.hsk.dentistmall.api.persistence.MdEnterWarehouse;
import com.hsk.dentistmall.api.persistence.MdInventoryView;
import com.hsk.exception.HSKDBException;

public interface IMdEnterOutCountDao {
	public Integer saveMdEnterWarehouse(MdEnterOutCount att_MdEnterOutCount) throws HSKDBException;
	public List<MdEnterOutCount> getMdEnterWarehouseList(String monthTime,MdInventoryView att_MdInventoryView) throws HSKDBException;
	/**
	 * 获取月统计
	 * @param monthTime
	 * @return
	 * @throws HSKDBException
	 */
	public List<Map<String,Object>> getInvemtoryEnterCount(String monthTime,MdInventoryView att_MdInventoryView) throws HSKDBException;
	/**
	 * 查询当前库存数量
	 * @return
	 * @throws HSKDBException
	 */
	public List<Map<String,Object>> getNowInvemtoryCount(MdInventoryView att_MdInventoryView) throws HSKDBException;
}
