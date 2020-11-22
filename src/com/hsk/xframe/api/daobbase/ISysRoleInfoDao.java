package com.hsk.xframe.api.daobbase;

import java.util.List;
import java.util.Map;

import com.hsk.exception.HSKDBException;
import com.hsk.xframe.api.persistence.SysGxRoleControl;
import com.hsk.xframe.api.persistence.SysGxRoleMenu;
import com.hsk.xframe.api.persistence.SysRoleInfo;

public interface ISysRoleInfoDao {
	/**
	 * 查看角色组tree数据
	 * @param sysSroleId
	 * @return
	 * @throws HSKDBException
	 */
	List<SysRoleInfo> getSysRoleInfoTreeList(Integer sysSroleId) throws HSKDBException;
	/**
	 * 根据角色ID 删除角色菜单关系
	 * @param sroleId
	 * @throws HSKDBException
	 */
	public void delSysRoleMenuList(Integer sroleId,String menuIds) throws HSKDBException;
	/**
	 * 根据组织id和菜单ID查询角色控件关系
	 * @param sroleId
	 * @param smenuId
	 * @return
	 * @throws HSKDBException
	 */
	public List<Map<String,Object>> getSysRoleControlList(Integer sroleId,Integer smenuId) throws HSKDBException;
	/**
	 * 根据角色ID删除角色控件信息
	 * @param sroleId
	 * @param sconIds
	 * @throws HSKDBException
	 */
	public void delSysRoleControlList(Integer sroleId,String sconIds) throws HSKDBException;
	
	public List<SysGxRoleMenu> getSysGxRoleMenuBySroleIds(String sroleIds) throws HSKDBException;
	
	public List<SysGxRoleControl> getSysGxRoleControlBySroleIds(String sroleIds) throws HSKDBException;
}
