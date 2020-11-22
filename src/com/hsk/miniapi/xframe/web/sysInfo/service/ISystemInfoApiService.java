package com.hsk.miniapi.xframe.web.sysInfo.service;

import java.util.List;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysSystemInfo;

/**
 * 
 * @author 张曙
 * 系统管理接口
 *
 */
public interface ISystemInfoApiService {
	/**
	 * 获取系统分页信息
	 * @param sysSystemInfo
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getSystemInfoPager(SysSystemInfo sysSystemInfo) throws HSKException;
	/**
	 * 保存系统信息
	 * @param sysSystemInfo
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveSystemInfo(SysSystemInfo sysSystemInfo) throws HSKException;
	/**
	 * 查找系统信息
	 * @param ssiId
	 * @return
	 * @throws HSKException
	 */
	public SysSystemInfo findSystemInfo(Integer ssiId) throws HSKException;
	/**
	 * 删除系统信息
	 * @param ssiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage delSystemInfo(Integer ssiId) throws HSKException;
	/**
	 * 拷贝系统
	 * @param ssiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage copySystemInfo(SysSystemInfo sysSystemInfo) throws HSKException;
	/**
	 * 获取所有系统列表
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getAllSystemInfo() throws HSKException;
	
	/**
	 * 查询出系统 信息列表 
	 * @param sysSystemInfo
	 * @return
	 * @throws HSKException
	 */
	public List<SysSystemInfo> findeListSystemInfo(SysSystemInfo sysSystemInfo) throws HSKException;
	
/**
 * 测试系统接口
 * @param sysSystemInfo
 * @return
 * @throws HSKException
 */
	public SysRetrunMessage testIOSystemInfo(SysSystemInfo sysSystemInfo) throws HSKException;
	
	
	/**
	 * 
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage runSetMaping() throws HSKException;
	/***
	 *
	 * @param datetimeCode
	 * @param mapCode
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage updateSetMaping(String datetimeCode, String mapCode) throws HSKException;
	
	
	public SysRetrunMessage  findeListNodeSystemInfo() throws HSKException;
	
}
