package com.hsk.dentistmall.web.storage.service;

import com.hsk.dentistmall.api.persistence.MdInventoryView;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.SysRetrunMessage;

public interface IMdExportService {
	/**
	 * 导出应扣耗材
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage exportYkhc(String startTime,String endTime) throws HSKException;
	/**
	 * 
	 * @param time
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage exportSfchz(String time) throws HSKException;
	/**
	 * 导出盘点单
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage exportPdd(MdInventoryView att_MdInventoryView) throws HSKException;
	
	public SysRetrunMessage newMdEnterOutCount(String month) throws HSKException;
	
	public SysRetrunMessage exportSfhz(String month) throws HSKException;

}
