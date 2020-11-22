package com.hsk.miniapi.xframe.api.daobbase;

import java.util.List;
import java.util.Map;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.dto.model.TreeNode;
import com.hsk.xframe.api.persistence.SysWebsiteColumns;
import com.hsk.xframe.api.persistence.SysWebsiteInfo;

/**
 * 栏目信息Dao接口类
 * 
 * @author cxd
 * @version v1.0 2015-02-27
 *
 */
public interface ISysWebsiteColumnsApiDao {
	
	/**
	 * 添加一条栏目对象记录的Dao方法
	 * 
	 * @param siteColumns
	 *            栏目对象
	 * @return 新建记录的主键值Integer类型
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public Integer NewSysWebSiteColumns(SysWebsiteColumns siteColumns) throws HSKDBException;

	/**
	 * 根据主键删除一条栏目对象记录的Dao方法
	 * 
	 * @param mszId
	 *            记录的主键值
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public void DeleteSysWebsiteColumns(SysWebsiteColumns siteColumns) throws HSKDBException;

	/**
	 * 根据主键值修改一条栏目对象记录的Dao方法
	 * 
	 * @param MxxzSiteColumns
	 *            需要修改的对象记录
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public void UpdateMSysWebsiteColumns(SysWebsiteColumns siteColumns)throws HSKDBException;

	/**
	 * 根据主键值查询一条栏目对象记录的Dao方法
	 * 
	 * @param uiId
	 *              主键值 或者 栏目编码
	 * @return MxxzSiteColumns 栏目对象记录
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public SysWebsiteColumns findOneSysWebsiteColumns(SysWebsiteColumns siteColumns) throws HSKDBException;

	/**
	 * 根据条件检索出栏目对象数据集
	 * 
	 * @param MxxzSiteColumns
	 *            检索条件
	 * @return 栏目对象集合 List<MxxzSiteColumns>
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public List<SysWebsiteColumns> findListSysWebsiteColumns(SysWebsiteColumns siteColumns)
			throws HSKDBException;
	
	public List<SysWebsiteColumns> findListSysWebsiteColumns(SysWebsiteColumns siteColumns, int count, boolean isOrder)
			throws HSKDBException;

	/**
	 * 根据条件检索出栏目对象的分页集合
	 *
	 * @param MxxzSiteColumns
	 *            检索条件
	 * @return 栏目对象分页集合 PagerModel
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public PagerModel findPagerModelSysWebsiteColumns(SysWebsiteColumns siteColumns)
			throws HSKDBException;

	public List<Map<Object, Object>> findSysWebsiteColumnsList(Integer swcId, Integer swiId)
			throws HSKDBException;

	/**
	 * 根据网站ID获取栏目树
	 * @param webSiteId 网站ID
	 * @param columnId 条目ID
	 * @return
	 * @throws HSKDBException
	 */
	public List<TreeNode> findSysWebsiteColumnsTree(String webSiteId, String columnId)
			throws HSKDBException;

	/**
	 * 此栏目是否含有子栏目
	 * @param siteColumns
	 * @return 含有子节点返回 true
	 * @throws HSKDBException
	 */
	public boolean isHasChildrenItem(SysWebsiteColumns siteColumns)
			throws HSKDBException;
	/**
	 * 此栏目是否含有内容
	 * @param siteColumns
	 * @return
	 * @throws HSKDBException
	 */
	public boolean isHasContent(SysWebsiteColumns siteColumns)
			throws HSKDBException;

	/**
	 * 通过网站ID查询网站下的栏目列表
	 * @param
	 * @throws HSKDBException
	 */
	public List<SysWebsiteColumns> findListSysWebsiteColumnsBySite(SysWebsiteInfo sysWebsiteInfo)
			throws HSKDBException;
	/**
	 * 根据父节点查询对应子节点集合
	 * @param mwiId
	 * @param number
	 * @return
	 * @throws HSKDBException
	 */
	public List<SysWebsiteColumns> findMxxzSiteColumnsByParentId(Integer swcId, Integer number)
			throws HSKDBException ;
	/**
	 * 获取子栏目id集合
	 * @param sysSwcId
	 * @param number
	 * @param swcId
	 * @return
	 * @throws HSKDBException
	 */
	public Integer[] findColumnIdsByCondition(Integer sysSwcId, Integer number, Integer swcId)
			throws HSKDBException;

	/**
	 * 获取子栏目集合
	 * @param sysSwcId
	 * @param number
	 * @param swcId
	 * @return
	 * @throws HSKDBException
	 */
	public List<SysWebsiteColumns> findColumnsByCondition(Integer sysSwcId, Integer number, Integer swcId)
			throws HSKDBException ;
	/**
	 * 获取子栏目集合
	 * @param mxxMszId
	 * @param number
	 * @param swcId
	 * @param state 是否推荐
	 * @return
	 * @throws HSKDBException
	 */
	public List<SysWebsiteColumns> findListByCondition(Integer sysSwcId, Integer number, Integer swcId, Integer state)
			throws HSKDBException;
	
	/**
	 * 获取该节点下的最大序号
	 * @param sysSwcId
	 * @return
	 * @throws HSKDBException
	 */
	public int getMaxOrderByParentId(Integer sysSwcId) throws HSKDBException;
}
