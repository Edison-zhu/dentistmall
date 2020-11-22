package com.hsk.miniapi.xframe.api.daobbase;

import java.util.List;

import com.hsk.exception.HSKDBException;
import com.hsk.xframe.api.persistence.SysControlParam;

/**
 * datagrid 列显示
 * @author 张曙
 *
 */
public interface ISysControlParamApiDao {
	/**
	 * 获取列显示列表
	 * @param sysControlParam
	 * @return
	 * @throws HSKDBException
	 */
	public List<SysControlParam> getSysControlParamList(SysControlParam sysControlParam) throws HSKDBException;
	/**
	 * 删除列显示列表
	 * @param sysControlParam
	 * @throws HSKDBException
	 */
	public void delSysControlParamList(SysControlParam sysControlParam) throws HSKDBException;

}
