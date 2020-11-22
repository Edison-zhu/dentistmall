package com.hsk.xframe.api.daobbase;

import java.util.List;
import java.util.Map;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysTableInfo;

public interface ISysTableInfoDao {

	public Integer newSysTableInfo(SysTableInfo st) throws HSKDBException;
	 
	public void updateSysTableInfo(SysTableInfo st) throws HSKDBException;
	 
	public void delSysTableInfo(SysTableInfo st) throws HSKDBException;
	 
	public SysTableInfo findSysTableInfoById(Integer stableId) throws HSKDBException;
	 
	public SysTableInfo findSysTableInfoByModel(SysTableInfo st) throws HSKDBException;
	 
	public List<Map<String,Object>> getSysTableInfoMapList(SysTableInfo st) throws HSKDBException; 
	 
	public PagerModel getSysTableInfoPageParam(SysTableInfo st) throws HSKDBException;
}
