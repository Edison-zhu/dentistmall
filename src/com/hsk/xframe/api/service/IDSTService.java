package com.hsk.xframe.api.service ;

import com.hsk.exception.HSKException;
import com.hsk.supper.service.ISupperService;
import com.hsk.xframe.api.persistence.SysUserInfo;

import java.util.List;
import java.util.Map;

/**
 * 基础业务处理函数接口类
 * @author xun
 * @version v1.0 2015-02-23
 *
 */
public interface IDSTService  extends ISupperService { 
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

	/**
	 * 获取用户一些归属值
	 * purchaseType说明: 是根据供应商开始。供应商：1，集团：2，医院：3，门诊：4。
	 * 在使用的时候，某些情况下，是需要转换成Integer类型，再减 1
	 * @return
	 */
	Map<String, Object> getAccountBelong();

	/**
	 * 检查是都需要安全码
	 * @return
	 * @throws HSKException
	 */
	boolean checkNeedSecurity() throws HSKException;
}
