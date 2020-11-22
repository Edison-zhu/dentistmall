package com.hsk.xframe.api.daobbase;

import java.util.List;
import java.util.Map;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysClassInfo;

public interface ISysClassInfoDao {
	public Integer newSysClassInfo(SysClassInfo st) throws HSKDBException;

	public void updateSysClassInfo(SysClassInfo st) throws HSKDBException;

	public void delSysClassInfo(SysClassInfo st) throws HSKDBException;

	public SysClassInfo findSysClassInfoById(Integer stableId)
			throws HSKDBException;

	public SysClassInfo findSysClassInfoByModel(SysClassInfo st)
			throws HSKDBException;

	public List<Map<String, Object>> getSysClassInfoMapList(SysClassInfo st)
			throws HSKDBException;

	public PagerModel getSysClassInfoPageParam(SysClassInfo st)
			throws HSKDBException;
}
