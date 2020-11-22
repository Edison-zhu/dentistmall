package com.hsk.xframe.api.daobbase.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.xframe.api.daobbase.ISysRoleInfoDao;
import com.hsk.xframe.api.persistence.SysGxRoleControl;
import com.hsk.xframe.api.persistence.SysGxRoleMenu;
import com.hsk.xframe.api.persistence.SysRoleInfo;
/**
 * 
 * @author 张曙
 *
 */
@Repository
public class SysRoleInfoDao extends SupperDao implements ISysRoleInfoDao {

	@Override
	public List<SysRoleInfo> getSysRoleInfoTreeList(Integer sysSroleId) throws HSKDBException {
		String hql = "from SysRoleInfo where 1=1";
		if(sysSroleId != null)
			hql += " and sysSroleId="+sysSroleId;
		else
			hql += " and sysSroleId is null";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public void delSysRoleMenuList(Integer sroleId, String menuIds)
			throws HSKDBException {
		String sql = "delete from sys_gx_role_menu where srole_id="+sroleId;
		if(menuIds != null && !menuIds.equals(""))
			sql += " and smenu_id in ("+menuIds+")";
		this.getJdbcDao().execute(sql);
	}

	@Override
	public List<Map<String, Object>> getSysRoleControlList(Integer sroleId,Integer smenuId) throws HSKDBException {
		String sql = "select a.scon_id,a.if_operate from "
					+"sys_gx_role_control a,sys_control_info b where a.scon_id=b.scon_id and a.srole_id="+sroleId+" and b.smenu_id="+smenuId;
		return this.getJdbcDao().query(sql);
	}

	@Override
	public void delSysRoleControlList(Integer sroleId, String sconIds)throws HSKDBException {
		String sql = "delete from sys_gx_role_control a where a.srole_id="+sroleId+" and a.scon_id in ("+sconIds+")";
		this.getJdbcDao().execute(sql);
	}

	@Override
	public List<SysGxRoleMenu> getSysGxRoleMenuBySroleIds(String sroleIds)
			throws HSKDBException {
		String hql = "from SysGxRoleMenu where sroleId in ("+sroleIds+")";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<SysGxRoleControl> getSysGxRoleControlBySroleIds(String sroleIds)
			throws HSKDBException {
		String hql = "from SysGxRoleControl where sroleId in ("+sroleIds+")";
		return this.getHibernateTemplate().find(hql);
	}

}
