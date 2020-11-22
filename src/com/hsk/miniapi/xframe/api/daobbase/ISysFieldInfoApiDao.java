package com.hsk.miniapi.xframe.api.daobbase;

import java.util.List;
import java.util.Map;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysFieldInfo;

public interface ISysFieldInfoApiDao {
	public Integer newSysFieldInfo(SysFieldInfo st) throws HSKDBException;
	 
	public void updateSysFieldInfo(SysFieldInfo st) throws HSKDBException;
	 
	public void delSysFieldInfo(SysFieldInfo st) throws HSKDBException;
	 
	public SysFieldInfo findSysFieldInfoById(Integer stableId) throws HSKDBException;
	 
	public SysFieldInfo findSysFieldInfoByModel(SysFieldInfo st) throws HSKDBException;
	
	public List<Map<String,Object>> getSysFieldInfoMapList(SysFieldInfo st) throws HSKDBException; 
	 
	public PagerModel getSysFieldInfoPageParam(SysFieldInfo st) throws HSKDBException;
	
	
}
