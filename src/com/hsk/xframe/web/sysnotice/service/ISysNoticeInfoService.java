package com.hsk.xframe.web.sysnotice.service;

import java.util.List;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysNoticeInfo;

/**
 * SysNoticeInfo业务操作接口类 
 * @author  作者:张曙
 */
public interface ISysNoticeInfoService {
	
	/**
	 * 查找SysNoticeInfo对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  sniId  Integer类型(md_materiel_type表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer sniId) throws HSKException;

	
	/**
	 * 查找SysNoticeInfo对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  sniId  Integer类型(md_materiel_type表主键)
	 * @return SysNoticeInfo md_materiel_type表记录
	 * @throws HSKException
	 */	
	public SysNoticeInfo findObject(Integer sniId) throws HSKException;
	
	/**
	 * 根据md_materiel_type表主键删除SysNoticeInfo对象记录
     * @param  sniId  Integer类型(md_materiel_type表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer sniId) throws HSKException;
	
	/**
	 * 根据md_materiel_type表主键删除多条SysNoticeInfo对象记录
     * @param  sniIds  Integer类型(md_materiel_type表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String sniIds) throws HSKException;
	
	 /**
	 * 新增或修改md_materiel_type表记录 ,如果md_materiel_type表主键SysNoticeInfo.sniId为空就是添加，如果非空就是修改
     * @param  att_SysNoticeInfo  SysNoticeInfo类型(md_materiel_type表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject( SysNoticeInfo att_SysNoticeInfo) throws HSKException;
	/**
	 * 更改数据状态
	 * @param sniId
	 * @param state
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage updateObjectState(Integer sniId,String state) throws HSKException;
	/**
	 *根据SysNoticeInfo对象作为对(md_materiel_type表进行查询，获取分页对象
     * @param  att_SysNoticeInfo  SysNoticeInfo类型(md_materiel_type表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (SysNoticeInfo att_SysNoticeInfo) throws HSKException;
	/**
	 * 获取公告列表信息
	 * @return
	 * @throws HSKException
	 */
	public List<SysNoticeInfo> getSysNoticeInfoList() throws HSKException;
}
