package com.hsk.miniapi.xframe.api.daobbase;

import java.util.List;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysNoticeInfo;

public interface ISysNoticeInfoApiDao {
	/**
	 * 根据主键查询SysNoticeInfo实体
	 * @param sniId
	 * @return
	 * @throws HSKDBException
	 */
	public SysNoticeInfo findSysNoticeInfoById(Integer sniId) throws HSKDBException;
	/**
	 * 根据主键删除SysNoticeInfo实体
	 * @param sniId
	 * @throws HSKDBException
	 */
	public void deleteSysNoticeInfoById(Integer sniId) throws HSKDBException;
	/**
	 * 根据主键修改SysNoticeInfo实体
	 * @param sniId
	 * @param sysNoticeInfo
	 * @return
	 * @throws HSKDBException
	 */
	public SysNoticeInfo updateSysNoticeInfoById(Integer sniId, SysNoticeInfo sysNoticeInfo) throws HSKDBException;
	/**
	 * 新建SysNoticeInfo实体
	 * @param sysNoticeInfo
	 * @return
	 * @throws HSKDBException
	 */
	public Integer saveSysNoticeInfo(SysNoticeInfo sysNoticeInfo) throws HSKDBException;
	/**
	 * 新增或修改SysNoticeInfo实体
	 * @param sysNoticeInfo
	 * @return
	 * @throws HSKDBException
	 */
	public SysNoticeInfo saveOrUpdateSysNoticeInfo(SysNoticeInfo sysNoticeInfo) throws HSKDBException;
	/**
	 * 查询SysNoticeInfo实体分页信息
	 * @param sysNoticeInfo
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelBySysNoticeInfo(SysNoticeInfo sysNoticeInfo) throws HSKDBException;
	/**
	 * 查询SysNoticeInfo实体list信息
	 * @param sysNoticeInfo
	 * @return
	 * @throws HSKDBException
	 */
	public List<SysNoticeInfo> getListBySysNoticeInfo(SysNoticeInfo sysNoticeInfo) throws HSKDBException;

}
