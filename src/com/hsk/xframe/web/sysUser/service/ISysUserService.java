package com.hsk.xframe.web.sysUser.service;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
/**
 * 
 * @author 张曙
 * 用户Service
 *
 */
public interface ISysUserService {
	/**
	 * 查看用户分页信息
	 * @param sysUserInfo
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getSysUserPager(SysUserInfo sysUserInfo, Integer exclude) throws HSKException;
	/**
	 * 保存用户信息
	 * @param sysUserInfo
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveSysUser(SysUserInfo sysUserInfo) throws HSKException;

	//重置采购商安全码
	public SysRetrunMessage updateSysUser(Integer suiId) throws HSKException;


	//修改采购商安全码以及开关
	public SysRetrunMessage updateSysUserSecurityCode(Integer suiId,Integer openSecurity,String securityCode) throws HSKException;
	/**
	 * 修改用户状态
	 * @param suiId
	 * @param state
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage updateSysUserState(Integer suiId,Integer state) throws HSKException;
	/**
	 * 删除用户信息
	 * @param suiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage delSysUser(Integer suiId) throws HSKException;
	/**
	 * 查找用户信息
	 * @param suiId
	 * @return
	 * @throws HSKException
	 */
	public SysUserInfo findSysUser(SysUserInfo sysUserInfo) throws HSKException;
	/**
	 * 检查用户账号
	 * @param sysUserInfo
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage checkSysUser(SysUserInfo sysUserInfo) throws HSKException;
	
	/**
	 * 修改用户密码
	 * @param suiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage updateSysUserPass(Integer suiId) throws HSKException;
	/**
	 * 修改登录用户密码
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage updateLoginUserPass(String oldPwd,String newPwd) throws HSKException;
	/**
	 * 获取用户角色列表
	 * @param suiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getUserRole(Integer suiId) throws HSKException;
	/**
	 * 获取角色菜单列表
	 * @param suiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getUserMenu(Integer suiId) throws HSKException;
	/**
	 * 获取角色控件列表
	 * @param suiId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getUserControl(Integer suiId,Integer smenuId) throws HSKException;
	/**
	 * 保存用户岗位信息
	 * @param suiId
	 * @param sroleIds
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveUserRole(Integer suiId,String sroleIds) throws HSKException;
	/**
	 * 保存用户菜单信息
	 * @param suiId
	 * @param sconIds
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveUserMenu(Integer suiId,String smenuIds) throws HSKException;
	/**
	 * 保存用户控件信息
	 * @param suiId
	 * @param sconIds
	 * @param oprates
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveUserControl(Integer suiId,String sconIds,String oprates) throws HSKException;
	
	public SysRetrunMessage getSysUserList(SysUserInfo sysUserInfo) throws HSKException;
	
	public void toSel(Integer suiId) throws HSKException;
	
	public SysRetrunMessage getSysUserMenuList() throws HSKException;
	
	public SysRetrunMessage getSysUserControlList() throws HSKException;
	
	public void saveBD() throws HSKException; 
	
	
	public SysRetrunMessage saveWebSysUser(SysUserInfo sysUserInfo) throws HSKException;
	/**
	 * 修改头像信息
	 * @param imgSrc
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage updateHeard(String imgSrc) throws HSKException;
	/**
	 * 展现头像
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage showHeard() throws HSKException;

	/**
	 * 更新安全码
	 * @param oldSecurity
	 * @param newSecurity
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage updateSecurityCode (String oldSecurity, String newSecurity) throws HSKException;

	/**
	 * 获取是否打开安全码
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage getOpenSecurity() throws HSKException;

	/**
	 * 检查安全码是否正确
	 * @param securityCode
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage updateOpenSecurityState(String securityCode) throws HSKException;
}