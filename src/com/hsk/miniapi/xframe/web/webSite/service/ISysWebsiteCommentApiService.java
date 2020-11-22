package com.hsk.miniapi.xframe.web.webSite.service;
import java.util.List;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysWebsiteComment;

/**
 * 内容信息业务接口类
 *
 */
public interface ISysWebsiteCommentApiService {
	 /**
	  *  删除内容信息业务方法
	  * @param mwiId     主键
	  * @return 系统返回信息对象 SysRetrunMessage
	  * @throws HSKException  事务异常
	  */
	public SysRetrunMessage deleteSysWebsiteComment(SysWebsiteComment sysWebsiteComment) throws HSKException;
	/**
	 * 提交内容信息业务方法
	 * @param SysWebsitComment 内容信息对象
	 * @return  系统返回信息对象 SysRetrunMessage
	 * @throws HSKException 事务异常
	 */
	public SysRetrunMessage saveSysWebsiteComment(SysWebsiteComment sysWebsiteComment)throws HSKException;
	
	/**
	 * 获取列表方法
	 * @param SysWebsitComment 作为查询条件内容信息对象
	 * @return 内容信息分页集合 PagerModel
	 * @throws HSKException 事务异常
	 */
	public PagerModel GetPMSysWebsiteComment(SysWebsiteComment sysWebsiteComment)throws HSKException;

	/**
	 * 获取内容信息对象
	 * @param mwiId    主键
	 * @return 编码对象
	 * @throws HSKException  事务异常
	 */
	public  SysWebsiteComment GetOneSysWebsitComment(SysWebsiteComment sysWebsiteComment) throws HSKException;
	/**
	 * 添加网站内容 forward
	 * @param sysWebsiteComment
	 * @return
	 * @throws HSKException
	 */
	public  SysWebsiteComment addSysWebsitComment(SysWebsiteComment sysWebsiteComment) throws HSKException;
	/**
	 * 置顶设置
	 * @param sysWebsiteComment
	 * @return
	 * @throws HSKException
	 */
	public  SysRetrunMessage setSysWebsitCommentToTop(SysWebsiteComment sysWebsiteComment) throws HSKException;
	/**
	 * 根据栏目ID获取内容信息列表
	 * @param swcId 栏目ID
	 * @param number 数量
	 * @return
	 * @throws HSKException
	 */
	public List<SysWebsiteComment> getSysWebsiteCommentListBySwcId(Integer swcId, Integer number) throws HSKException;
}
