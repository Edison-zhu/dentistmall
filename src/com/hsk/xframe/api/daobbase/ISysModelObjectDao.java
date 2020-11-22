package com.hsk.xframe.api.daobbase;

import java.util.List;
import java.util.Map;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysModelObject;

public interface ISysModelObjectDao {
	public Integer newSysModelObject(SysModelObject st) throws HSKDBException;
	 
	public void updateSysModelObject(SysModelObject st) throws HSKDBException;
	 
	public void delSysModelObject(SysModelObject st) throws HSKDBException;
	 
	public SysModelObject findSysModelObjectById(Integer stableId) throws HSKDBException;
	 
	public SysModelObject findSysModelObjectByModel(SysModelObject st) throws HSKDBException;
	 
	public List<Map<String,Object>> getSysModelObjectMapList(SysModelObject st) throws HSKDBException; 
	 
	public PagerModel getSysModelObjectPageParam(SysModelObject st) throws HSKDBException;
}
