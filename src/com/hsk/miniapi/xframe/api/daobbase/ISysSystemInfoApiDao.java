package com.hsk.miniapi.xframe.api.daobbase;

import java.util.List;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysSystemInfo;

/**
 * 
 * @author 张曙
 * 系统管理DAO
 *
 */
public interface ISysSystemInfoApiDao {
	/**
	 * 新建系统
	 * @param sysSystemInfo 系统信息
	 * @return
	 * @throws HSKDBException
	 */
	public Integer newSysSystemInfo(SysSystemInfo sysSystemInfo) throws HSKDBException;
	/**
	 * 修改系统
	 * @param sysSystemInfo 系统信息
	 * @throws HSKDBException
	 */
	public void updateSysSystemInfo(SysSystemInfo sysSystemInfo) throws HSKDBException;
	/**
	 * 删除系统
	 * @param sysSystemInfo 系统信息
	 * @throws HSKDBException
	 */
	public void delSysSystemInfo(SysSystemInfo sysSystemInfo) throws HSKDBException;
	/**
	 * 按照ID查找系统信息
	 * @param ssiId 系统ID
	 * @return
	 * @throws HSKDBException
	 */
	public SysSystemInfo findSysSystemInfoById(Integer ssiId) throws HSKDBException;
	/**
	 * 根据model查询系统信息
	 * @param sysSystemInfo 
	 * @return
	 * @throws HSKDBException
	 */
	public SysSystemInfo findSysSystemInfoByModel(SysSystemInfo sysSystemInfo) throws HSKDBException;
	/**
	 * 查看系统分页信息列表
	 * @param sysSystemInfo
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getSysSystemInfoPage(SysSystemInfo sysSystemInfo) throws HSKDBException;
	/**
	 * 查看所有系统信息
	 * @return
	 * @throws HSKDBException
	 */
	public List<SysSystemInfo> getSysSystemInfoList() throws HSKDBException;
	
	/**
	 * 查询出系统 信息列表 
	 * @param sysSystemInfo
	 * @return
	 * @throws HSKException
	 */
	public List<SysSystemInfo> findeListSystemInfo(SysSystemInfo sysSystemInfo) throws HSKException;
}
