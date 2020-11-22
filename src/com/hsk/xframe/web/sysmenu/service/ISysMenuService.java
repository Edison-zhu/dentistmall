package com.hsk.xframe.web.sysmenu.service;

import java.util.List;
import java.util.Map;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysControlInfo;
import com.hsk.xframe.api.persistence.SysMenuInfo;
/**
 * 
 * @author 张曙
 * 菜单Service
 */
public interface ISysMenuService {
	/**
	 * 根据父菜单查询子菜单列表
	 * @param parentId
	 * @return
	 * @throws HSKException
	 */
	public List<Map<String,Object>> getSysMenuList(Integer id) throws HSKException;
	/**
	 * 保存菜单信息
	 * @param sysMenuInfo
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveSysMenu(SysMenuInfo sysMenuInfo) throws HSKException;
	/**
	 * 删除菜单信息
	 * @param smenuId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage delSysMenu(Integer smenuId) throws HSKException;
	/**
	 * 查找菜单信息,当没有smenuId时为新建，则需要赋予sysSmenuId
	 * @param smenuId
	 * @param sysSmenuId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage findSysMenuInfo(Integer smenuId,Integer sysSmenuId) throws HSKException;
	/**
	 * 查询权限控件列表
	 * @param sysControlInfo
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getSysControlInfoPager(SysControlInfo sysControlInfo) throws HSKException;
	/**
	 * 保存权限控件信息
	 * @param sysControlInfo
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveSysControlInfo(SysControlInfo sysControlInfo) throws HSKException;
	/**
	 * 获取权限控件信息
	 * @param sconId
	 * @param smenuId
	 * @return
	 * @throws HSKException
	 */
	public SysControlInfo findSysControlInfo(Integer sconId,Integer smenuId) throws HSKException;
	/**
	 * 删除权限控件信息
	 * @param sconId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage delSysControlInfo(Integer sconId) throws HSKException;
	/**
	 * 获取整个菜单树
	 * @return
	 * @throws HSKException
	 */
	public List<Map<String, Object>> getAllSysMenuList() throws HSKException;
}
