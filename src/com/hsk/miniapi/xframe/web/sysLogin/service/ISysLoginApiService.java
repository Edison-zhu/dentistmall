package com.hsk.miniapi.xframe.web.sysLogin.service;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysFeedbackEntity;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * 用户登录接口
 * @author zhangshu
 *
 */
public interface ISysLoginApiService {
	/**
	 * 基础平台用户登录
	 * @param userName
	 * @param password
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage sysLogin(String userName, String password) throws HSKException;
	/**
	 * 商城登陆
	 * @param userName
	 * @param password
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage shoppingLogin(String userName, String password) throws HSKException;

	SysRetrunMessage salesManLogin(String userName, String password) throws HSKException;

	/**
	 * 检查是否处于登录状态
	 * @return
	 */
	SysRetrunMessage getCheckLogin();
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
	void addSysUserLoginShoppingLog(Integer suiId, String ip) throws HSKException ;

	/**
	 * 记录后台登录日志
	 * @param suiId
	 */
	void addSysUserLoginSysLog(Integer suiId, String ip) throws HSKException ;

	/**
	 * 微信登录
	 * @throws HSKException
	 */
	SysRetrunMessage weChatLogin(String code) throws HSKException;

	SysRetrunMessage weChatSalesManLogin(String code) throws HSKException;

	SysRetrunMessage updateThirdParty(String sessionKey) throws HSKException;

	SysRetrunMessage updateBindThirdParty(String sessionKey) throws HSKException;

	/**
	 * 验证用户
	 * @param phoneNumber
	 * @param account
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage findPassword(String phoneNumber, String account) throws HSKException;

	SysRetrunMessage saveResetPassword(String account, String password, String passwordAgain) throws HSKException;

	/**
	 * 改变密码
	 * @param account
	 * @param phoneNumber
	 * @param password
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage savePass(String account, String phoneNumber, String password) throws HSKException;

	/**
	 * 改变手机
	 * @param account
	 * @param password
	 * @param phoneNumber
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage savePhone(String account, String password, String phoneNumber) throws HSKException;

	SysRetrunMessage saveFeedBack(SysFeedbackEntity sysFeedbackEntity) throws HSKException;
}
