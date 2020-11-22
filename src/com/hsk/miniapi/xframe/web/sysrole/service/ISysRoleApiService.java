package com.hsk.miniapi.xframe.web.sysrole.service;

import java.util.List;
import java.util.Map;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysRoleInfo;

/**
 * 
 * @author 张曙
 * 角色服务接口
 *
 */
public interface ISysRoleApiService {
	/**
	 * 根据父菜单查询子菜单列表
	 * @param parentId
	 * @return
	 * @throws HSKException
	 */
	public List<Map<String,Object>> getSysRoleList(Integer id) throws HSKException;
	/**
	 * 获取整个角色树
	 * @return
	 * @throws HSKException
	 */
	public List<Map<String, Object>> getAllRoleTree() throws HSKException;
	/**
	 * 保存角色信息
	 * @param sysRoleInfo
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveSysRole(SysRoleInfo sysRoleInfo) throws HSKException;
	/**
	 * 删除角色信息
	 * @param sroleId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage delSysRole(Integer sroleId) throws HSKException;
	/**
	 * 查找角色信息
	 * @param sroleId
	 * @return
	 * @throws HSKException
	 */
	public SysRoleInfo findSysRole(Integer sroleId, Integer sysSroleId) throws HSKException;
	/**
	 * 根据关系ID获取角色关系信息
	 * @param sroleId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getRoleMenuListByRoleId(Integer sroleId) throws HSKException;
	/**
	 * 保存角色菜单信息
	 * @param sroleId
	 * @param smenuIds
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveSysRoleMenus(Integer sroleId, String smenuIds) throws HSKException;
	/**
	 * 根据角色ID,菜单ID获取角色控件列表
	 * @param sroleId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getRoleControlListByRoleId(Integer sroleId, Integer smenuId) throws HSKException;
	/**
	 * 保存角色控件信息
	 * @param sroleId
	 * @param sconIds
	 * @param oprates
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveSysRoleControls(Integer sroleId, String sconIds, String oprates) throws HSKException;
}
