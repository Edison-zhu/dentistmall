package com.hsk.xframe.api.daobbase;

import java.util.List;
import java.util.Map;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysClassColumns;

public interface ISysClassColumnsDao {
	public Integer newSysClassColumns(SysClassColumns st) throws HSKDBException;
	 
	public void updateSysClassColumns(SysClassColumns st) throws HSKDBException;
	 
	public void delSysClassColumns(SysClassColumns st) throws HSKDBException;
	 
	public SysClassColumns findSysClassColumnsById(Integer stableId) throws HSKDBException;
	 
	public SysClassColumns findSysClassColumnsByModel(SysClassColumns st) throws HSKDBException;
	 
	public List<Map<String,Object>> getSysClassColumnsMapList(SysClassColumns st) throws HSKDBException; 
	 
	public PagerModel getSysClassColumnsPageParam(SysClassColumns st) throws HSKDBException;
}
