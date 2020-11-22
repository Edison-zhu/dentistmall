package com.hsk.miniapi.xframe.api.daobbase;

import java.util.List;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.dto.model.TreeNode;
import com.hsk.xframe.api.persistence.SysWebsiteColumns;
import com.hsk.xframe.api.persistence.SysWebsiteInfo;

/**
 * 网站信息Dao接口类
 * 
 * @author cxd
 * @version v1.0 2015-02-27
 *
 */
public interface ISysWebsiteInfoApiDao {
	/**
	 * 添加一条网站信息记录的Dao方法
	 * 
	 * @param sysWebsiteInfo
	 *            网站信息
	 * @return 新建记录的主键值Integer类型
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public Integer NewSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo)
			throws HSKDBException;

	/**
	 * 根据主键删除一条网站信息记录的Dao方法
	 * 
	 * @param uiId
	 *            记录的主键值
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public void DeleteSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo) throws HSKDBException;

	/**
	 * 根据主键值修改一条网站信息记录的Dao方法
	 * 
	 * @param uiId
	 *            主键值
	 * @param sysWebsiteInfo
	 *            需要修改的对象记录
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public void UpdateSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo)
			throws HSKDBException;

	/**
	 * 根据 主键值查询和网站编码查询查询一条网站信息记录
	 * 
	 * @param sysWebsiteInfo
	 *           
	 * @return MxxzWebsiteInfo 网站信息记录
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public SysWebsiteInfo findOneSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo) throws HSKDBException;

	/**
	 * 根据条件检索出网站信息数据集
	 * 
	 * @param MxxzWebsiteInfo
	 *            检索条件
	 * @return 网站信息集合 List<MxxzWebsiteInfo>
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public List<SysWebsiteInfo> findListSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo)
			throws HSKDBException;
	/**
	 * 根据条件检索出网站信息的分页集合
	 * @param MxxzWebsiteInfo  检索条件
	 * @return 网站信息分页集合 PagerModel
	 * @throws HSKDBException  数据库层存储异常
	 */
	public PagerModel findPagerModelSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo) throws HSKDBException;
	/**
	 * 获取网站列表(用于树)
	 * @param maxRows
	 * @return
	 * @throws HSKDBException
	 */
	public List<TreeNode> findListSysWebsiteInfo(int maxRows)
			throws HSKDBException;
	
	/**
	 * 是否含有子节点
	 * @param mxxzWebsiteInfo
	 * @return 含有子节点返回 true
	 * @throws HSKDBException
	 */
	public boolean isHasChildren(SysWebsiteInfo sysWebsiteInfo)
			throws HSKDBException;
	
	/**
	 * 通过网站ID查询网站下的栏目列表
	 * 
	 * @param mxxzWebsiteInfo
	 * @return
	 * @throws HSKDBException
	 */
	public List<SysWebsiteColumns> findListSysWebsiteItems(SysWebsiteInfo sysWebsiteInfo)
			throws HSKDBException;
	
	
	
	
	
	
}
