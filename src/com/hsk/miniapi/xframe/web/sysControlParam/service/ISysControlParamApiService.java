package com.hsk.miniapi.xframe.web.sysControlParam.service;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysControlParam;

/**
 * datagrid 列显示
 * @author 张曙
 *
 */
public interface ISysControlParamApiService {
	/**
	 * 保存列显示信息
	 * @param controlId
	 * @param paramNames
	 * @param paramType
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveSysControlParamList(String controlId, String paramNames, String paramSource) throws HSKException;
	/**
	 * 获取登录用户列显示列表
	 * @param sysControlParam
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getMyUserSysControlParamList(SysControlParam sysControlParam) throws HSKException;
	
	/**
	 * 获取列表
	 * @param sysControlParam
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getSysControlParamList(SysControlParam sysControlParam) throws HSKException;
}
