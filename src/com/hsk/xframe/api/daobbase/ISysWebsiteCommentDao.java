package com.hsk.xframe.api.daobbase;

import java.util.List;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysWebsiteComment;

/**
 * 内容信息Dao接口类
 * 
 * @author cxd
 * @author yc.chen @2015-3-12
 * @version v1.0 2015-02-27
 * 
 *
 */
public interface ISysWebsiteCommentDao {
	/**
	 * 添加一条内容信息记录的Dao方法
	 * 
	 * @param mxxzAccount
	 *            内容信息
	 * @return 新建记录的主键值Integer类型
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public Integer NewSysWebsiteComment(SysWebsiteComment sysWebsiteComment)
			throws HSKDBException;

	/**
	 * 根据主键删除一条内容信息记录的Dao方法
	 * 
	 * @param mwmId
	 *            记录的主键值
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public void DeleteSysWebsiteComment(SysWebsiteComment sysWebsiteComment) throws HSKDBException;

	/**
	 * 根据主键值修改一条内容信息记录的Dao方法
	 * 
	 * @param mwmId
	 *            主键值
	 * @param MxxzWebsiteInfo
	 *            需要修改的对象记录
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public void UpdateSysWebsiteComment(SysWebsiteComment sysWebsiteComment)
			throws HSKDBException;

	/**
	 * 根据主键值查询一条内容信息记录的Dao方法
	 * 
	 * @param mwmId
	 *            主键值
	 * @return MxxzWebsiteInfo 内容信息记录
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public SysWebsiteComment findOneSysWebsiteComment(SysWebsiteComment sysWebsiteComment) throws HSKDBException;

	/**
	 * 根据条件检索出内容信息数据集
	 * 
	 * @param MxxzWebsiteInfo
	 *            检索条件
	 * @return 内容信息集合 List<MxxzWebsiteInfo>
	 * @throws HSKDBException
	 *             数据库层存储异常
	 */
	public List<SysWebsiteComment> findListSysWebsiteComment(SysWebsiteComment sysWebsiteComment)
			throws HSKDBException;
	
	
	public List<SysWebsiteComment> findListSysWebsiteComment(SysWebsiteComment sysWebsiteComment,int count, boolean isOrder)
			throws HSKDBException;
	
	public List<SysWebsiteComment> findListSysWebsiteComment(SysWebsiteComment sysWebsiteComment,boolean isOrder)
			throws HSKDBException;
	/**
	 * 根据条件检索出内容信息的分页集合
	 * @param MxxzWebsiteInfo  检索条件
	 * @return 内容信息分页集合 PagerModel
	 * @throws HSKDBException  数据库层存储异常
	 */
	public PagerModel findPagerModelSysWebsiteComment(SysWebsiteComment sysWebsiteComment)
	throws HSKDBException;
	/**
	 * 根据条件检索出内容信息的分页集合
	 * @param mxxzWebsitComment
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel findPagerModelSysWebsiteCommentForSql(SysWebsiteComment sysWebsiteComment)
			throws HSKDBException;
	
	/**
	 * 内容置顶
	 * @param mxxzWebsitComment
	 * @return
	 * @throws HSKDBException
	 */
	public int setSysWebsiteCommentToTop(SysWebsiteComment sysWebsiteComment)
			throws HSKDBException;
	
	
	/**
	 * 根据栏目查找内容统计
	 * @param itemId
	 * @return
	 * @throws HSKDBException
	 */
	public int findSysWebsiteCommentCountByItem(String  itemId)
			throws HSKDBException;

	/**
	 * 根据网站栏目的swcId查询网站内容分页
	 * @param swcId
	 * @return
	 * @throws HSKDBException
	 */
	public List<SysWebsiteComment> findPagerModelSysWebsiteCommentById(Integer swcId,Integer number)throws HSKDBException;
	/**
	 * 根据网站内容的swmId查询网站内容详情
	 * @param swmId
	 * @return
	 * @throws HSKDBException
	 */
	public SysWebsiteComment findSysWebsiteCommentBySwmId(Integer swmId)throws HSKDBException;
	/**
	 * 根据父节点获取内容详情
	 * @param mszId
	 * @param number
	 * @param type
	 * @return
	 * @throws HSKDBException
	 */
	public List<SysWebsiteComment> findSysWebsiteCommentByParentId(Integer swcId,Integer number,String ifRecommend)throws HSKDBException;
	/**
	  * 根据父节点获取内容详情分页信息
	 * @param swcId
	 * @param number
	 * @param type
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel findSysWebsiteCommentPageByParentId(Integer swcId,String ifRecommend,String notShowIds)throws HSKDBException;
	
	/**
	 * 获取该节点下的最大序号
	 * @param swcId
	 * @return
	 * @throws HSKDBException
	 */
	public int getMaxOrderByParentId(Integer swcId) throws HSKDBException;
}
