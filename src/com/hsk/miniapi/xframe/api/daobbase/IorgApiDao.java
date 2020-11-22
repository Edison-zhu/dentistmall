package com.hsk.miniapi.xframe.api.daobbase;

import java.util.Map;

import com.hsk.exception.HSKDBException;
import com.hsk.xframe.api.persistence.SysOrgGx;

/**
 * 组织关系dao业务类
 * @author Administrator
 *
 */
public interface IorgApiDao {
	/**
	 * 添加组织信息对象
	 * @param sysOrgGx
	 * @return
	 * @throws HSKDBException
	 */
	public int saveOrgInfo(SysOrgGx sysOrgGx) throws HSKDBException ;


	/**
	 * 获取下级组织id 字符串
	 * @param sysOrgGx
	 * @return
	 * @throws HSKDBException
	 */
	public String  getOrgStr(SysOrgGx sysOrgGx) throws HSKDBException ;

	/**
	 *
	 * @param sysOrgGx
	 * @return
	 * @throws HSKDBException
	 */
	public String  getOldIdStr(SysOrgGx sysOrgGx) throws HSKDBException ;

	public String  getOldIdStrByParent(SysOrgGx sysOrgGx) throws HSKDBException ;
	/**
	 * 根据组织ID查询后3级
	 * @param sysOrgGx
	 * @return
	 * @throws HSKDBException
	 */
	public Map<String,String> getOldThreeMap(SysOrgGx sysOrgGx) throws HSKDBException ;
	/**
	 * 根据组织ID查询名称字符串集合
	 * @param sysOrgGx
	 * @return
	 * @throws HSKDBException
	 */
	public String getNameStr(SysOrgGx sysOrgGx) throws HSKDBException ;
}
