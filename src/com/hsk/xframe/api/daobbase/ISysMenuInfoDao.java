package com.hsk.xframe.api.daobbase;

import java.util.List;
import java.util.Map;

import com.hsk.exception.HSKDBException;

/**
 * 
 * @author 张曙
 * 菜单管理DAO
 */
public interface ISysMenuInfoDao {
	/**
	 * 根据复菜单查询所有子菜单，当父菜单为空时查询第一子节点
	 * @param parentId
	 * @return
	 * @throws HSKDBException
	 */
	public List<Map<String,Object>> getSysMenuInfoMapListByParentId(Integer parentId) throws HSKDBException;
	/**
	 * 获取该节点下的最大序号
	 * @param parentId
	 * @return
	 * @throws HSKDBException
	 */
	public int getMaxOrderByParentId(Integer smenuId) throws HSKDBException;
	
	/**
	 * 查询整个树列表
	 * @param ssiId
	 * @return
	 * @throws HSKDBException
	 */
	public List<Map<String,Object>> getSysMenuInfoMapList() throws HSKDBException;
 
	
}