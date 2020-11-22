package com.hsk.miniapi.xframe.service;

import com.hsk.exception.HSKException;
import com.hsk.supper.service.ISupperService;
import com.hsk.xframe.api.persistence.SysSalesManEntity;
import com.hsk.xframe.api.persistence.SysSwitchEntity;
import com.hsk.xframe.api.persistence.SysUserInfo;

/**
 * 基础业务处理函数接口类
 * @author xun
 * @version v1.0 2015-02-23
 *
 */
public interface IDSTApiService  extends ISupperService {
	/**
	 * 获取帐号对象
	 * @return
	 * @throws HSKException
	 */
	public SysUserInfo GetOneSessionAccount() throws HSKException ;
	
	/**
	 * 设置帐号对象
	 * @throws HSKException
	 */
	public void SetSessionAccount(SysUserInfo account) throws HSKException ;
	
	 /**
	  * 获取当前账号的下属组织id
	  * @param organizaType 组织类型数组
	  * @return
	  * @throws HSKException
	  */
	public String  getOrgIdStr(String[] organizaType) throws HSKException ;

	SysUserInfo getApiSessionAccount() throws HSKException;

	SysSwitchEntity getApiSessionSwitch(Integer switchId) throws HSKException;

	SysSalesManEntity getApiSessionSalesMan() throws HSKException;

	/**
	 * 检查是都需要安全码
	 * @return
	 * @throws HSKException
	 */
	boolean checkNeedSecurity() throws HSKException;
}
