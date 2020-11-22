package com.hsk.miniapi.xframe.api.daobbase.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysMenuInfoApiDao;

/**
 * 
 * @author 张曙
 *
 */
@Repository
public class SysMenuInfoApiDao extends SupperDao implements ISysMenuInfoApiDao {

	@Override
	public List<Map<String,Object>> getSysMenuInfoMapListByParentId(Integer parentId)
			throws HSKDBException {
		String sql = "SELECT t.menu_name,t.smenu_id,t.sys_smenu_id,(SELECT COUNT(1) FROM sys_menu_info a WHERE a.sys_smenu_id=t.smenu_id) AS shu FROM sys_menu_info t where 1=1";
		
		if(parentId != null)
			sql += " and t.sys_smenu_id = '"+parentId+"'";
		else
			sql += " and (t.sys_smenu_id is null or t.sys_smenu_id='')";
		return this.getJdbcDao().query(sql);
	}
	@Override
	public int getMaxOrderByParentId(Integer smenuId) throws HSKDBException {
		int re =0;
		String sql = "SELECT MAX(menu_order_code) AS shu FROM sys_menu_info WHERE sys_smenu_id='"+smenuId+"'";
		List<Map<String,Object>> reList = this.getJdbcDao().query(sql);
		if(reList != null && reList.size() > 0){
			re = reList.get(0).get("shu") != null ? (Integer.parseInt(reList.get(0).get("shu").toString())):0;
		}  
		return re;
	}
	@Override
	public List<Map<String, Object>> getSysMenuInfoMapList()
			throws HSKDBException {
		String sql = "SELECT t.menu_name,t.smenu_id,t.sys_smenu_id,(SELECT COUNT(1) FROM sys_menu_info a WHERE a.sys_smenu_id=t.smenu_id) AS shu FROM sys_menu_info t";
		return this.getJdbcDao().query(sql);
	}

}
