package com.hsk.xframe.web.sysCodeAutomation.service;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysTableInfo;

public interface ISysTableInfoService {

	public SysRetrunMessage saveTable(SysTableInfo sti) throws HSKException;
	/**
	 * 获取表对象信息
	 * @param SysTableInfo
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getTableList(SysTableInfo sti) throws HSKException;
	
	
	public  SysTableInfo  getOneSysTableInfo(Integer id )throws HSKException;

}