package com.hsk.xframe.web.sysLogin.service;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.SysRetrunMessage;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * 用户登录接口
 * @author zhangshu
 *
 */
public interface ISysLoginService {
	/**
	 * 基础平台用户登录
	 * @param userName
	 * @param password
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage sysLogin(String userName,String password) throws HSKException;
	/**
	 * 商城登陆
	 * @param userName
	 * @param password
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage shoppingLogin(String userName,String password) throws HSKException;
	/**
	 * 用户登出
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage ReceptionCancellation() throws HSKException;

	/**
	 * 记录商城登录日志
	 * @param suiId
	 */
	void addSysUserLoginShoppingLog(Integer suiId,String ip) throws HSKException ;

	/**
	 * 记录后台登录日志
	 * @param suiId
	 */
	void addSysUserLoginSysLog(Integer suiId,String ip) throws HSKException ;
	
}
