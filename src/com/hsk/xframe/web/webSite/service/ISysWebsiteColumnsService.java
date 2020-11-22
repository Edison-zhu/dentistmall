package com.hsk.xframe.web.webSite.service;

import java.util.List;
import java.util.Map;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysWebsiteColumns;

/**
 * 栏目信息业务接口类
 * 
 * @author cxd
 * @version v1.0 2015-02-27
 * 
 */
public interface ISysWebsiteColumnsService {

	/**
	 * 获取栏目信息对象
	 * 
	 * @param mszId
	 *            主键
	 * @return 栏目对象
	 * @throws HSKException
	 *             事务异常
	 */
	public SysWebsiteColumns GetOneSysWebsiteColumns(SysWebsiteColumns sysWebsiteColumns) throws HSKException;

	/**
	 * 删除栏目信息业务方法
	 * 
	 * @param mszId
	 *            主键
	 * @return 系统返回信息对象 SysRetrunMessage
	 * @throws HSKException
	 *             事务异常
	 */
	public SysRetrunMessage deleteSysWebsiteColumns(SysWebsiteColumns sysWebsiteColumns) throws HSKException;

	/**
	 * 提交栏目信息业务方法
	 * 
	 * @param SysWebsiteColumns
	 *            栏目信息对象
	 * @return 系统返回信息对象 SysRetrunMessage
	 * @throws HSKException
	 *             事务异常
	 */
	public SysRetrunMessage saveSysWebsiteColumns(SysWebsiteColumns sysWebsiteColumns) throws HSKException;

	/**
	 * 获取列表方法
	 * 
	 * @param SysWebsiteColumns
	 *            作为查询条件栏目信息对象
	 * @return 栏目信息分页集合 PagerModel
	 * @throws HSKException
	 *             事务异常
	 */
	public PagerModel GetPMSysWebsiteColumns(SysWebsiteColumns sysWebsiteColumns) throws HSKException;

	/**
	 * 添加网站条目
	 * 
	 * @param sysWebsiteColumns
	 * @return
	 * @throws HSKException
	 */
	public SysWebsiteColumns addSysWebsiteColumns(SysWebsiteColumns sysWebsiteColumns) throws HSKException;

	public SysWebsiteColumns getSysWebsiteColumnsList(SysWebsiteColumns sysWebsiteColumns) throws HSKException;

	/**
	 * 根据网站ID获取栏目树
	 * 
	 * @param sysWebsiteColumns
	 *            {@link SysWebsiteColumns#getMwiId()}
	 * @return
	 * @throws HSKException
	 */
	public List getSysWebsiteColumnsTree(Integer id) throws HSKException;
	
	public List<Map<String, Object>> getSiteColumnsTree(Integer id, Integer mwiId, String type) throws HSKException;
	/**
	 * 根据上级栏目ID查询下级所有栏目信息
	 * @param sysSwcId
	 * @return
	 * @throws HSKException
	 */
	public List<SysWebsiteColumns> getSysWebsiteColumnsListBySysSwcId(Integer sysSwcId) throws HSKException;

}
