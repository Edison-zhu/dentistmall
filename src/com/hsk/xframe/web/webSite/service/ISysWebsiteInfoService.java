package com.hsk.xframe.web.webSite.service;

import java.util.List;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.dto.model.websit.WebSite;
import com.hsk.xframe.api.dto.model.websit.WebSiteItem;
import com.hsk.xframe.api.persistence.SysWebsiteColumns;
import com.hsk.xframe.api.persistence.SysWebsiteInfo;

/**
 * 网站信息业务接口类
 * 
 * @author cxd
 * @version v1.0 2015-02-27
 *
 */
public interface ISysWebsiteInfoService {
	 /**
	  *  删除网站信息业务方法
	  * @param mwiId     主键
	  * @return 系统返回信息对象 SysRetrunMessage
	  * @throws HSKException  事务异常
	  */
	public SysRetrunMessage deleteSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo) throws HSKException;
	/**
	 * 提交网站信息业务方法
	 * @param MxxzWebsiteInfo 网站信息对象
	 * @return  系统返回信息对象 SysRetrunMessage
	 * @throws HSKException 事务异常
	 */
	public SysRetrunMessage saveSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo)throws HSKException;
	
	/**
	 * 获取列表方法
	 * @param MxxzWebsiteInfo 作为查询条件网站信息对象
	 * @return 网站信息分页集合 PagerModel
	 * @throws HSKException 事务异常
	 */
	public PagerModel GetPMSysWebsiteInfo (SysWebsiteInfo sysWebsiteInfo)throws HSKException;
	
	/**
	 * 获取网站信息对象
	 * @param mwiId    主键
	 * @return 编码对象
	 * @throws HSKException  事务异常
	 */
	public  SysWebsiteInfo GetOneSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo) throws HSKException;
}
