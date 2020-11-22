package com.hsk.xframe.api.daobbase.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.xframe.api.daobbase.ISysControlParamDao;
import com.hsk.xframe.api.persistence.SysControlParam;

/**
 * 
 * @author 张曙
 *
 */
@Repository
public class SysControlParamDao extends SupperDao implements ISysControlParamDao {

	@Override
	public List<SysControlParam> getSysControlParamList(SysControlParam sysControlParam) throws HSKDBException {
		String hql = "from SysControlParam where 1=1";
		if(sysControlParam != null && sysControlParam.getSuiId() != null)
			hql += " and suiId="+sysControlParam.getSuiId();
		if(sysControlParam != null && sysControlParam.getParamType() != null && !sysControlParam.getParamType().trim().equals(""))
			hql += " and paramType ='"+sysControlParam.getParamType().trim()+"'";
		if(sysControlParam != null && sysControlParam.getParamSource() != null && !sysControlParam.getParamSource().trim().equals(""))
			hql += " and paramSource ='"+sysControlParam.getParamSource().trim()+"'";
		if(sysControlParam != null && sysControlParam.getControlId() != null && !sysControlParam.getControlId().trim().equals(""))
			hql += " and controlId ='"+sysControlParam.getControlId().trim()+"'";
		hql +=" order by paramOrder";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public void delSysControlParamList(SysControlParam sysControlParam)
			throws HSKDBException {
		String sql = "delete from sys_control_param where 1=1";
		boolean flag =false;//防止全部删除
		if(sysControlParam != null && sysControlParam.getSuiId() != null){
			sql += " and sui_id="+sysControlParam.getSuiId();
			flag = true;
		}
		if(sysControlParam != null && sysControlParam.getParamType() != null && !sysControlParam.getParamType().trim().equals("")){
			sql += " and param_type ='"+sysControlParam.getParamType().trim()+"'";
			flag = true;
		}
		if(sysControlParam != null && sysControlParam.getControlId() != null && !sysControlParam.getControlId().trim().equals("")){
			sql += " and control_id ='"+sysControlParam.getControlId().trim()+"'";
			flag = true;
		}
		if(sysControlParam != null && sysControlParam.getParamSource() != null && !sysControlParam.getParamSource().trim().equals("")){
			sql += " and param_source ='"+sysControlParam.getParamSource().trim()+"'";
			flag = true;
		}
		if(flag)
			this.getJdbcDao().execute(sql);
	}

 
	 

}
