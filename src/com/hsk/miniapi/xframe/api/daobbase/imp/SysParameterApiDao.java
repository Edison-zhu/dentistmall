package com.hsk.miniapi.xframe.api.daobbase.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysParameterApiDao;
import com.hsk.xframe.api.persistence.SysParameter;

@Repository
public class SysParameterApiDao extends SupperDao implements ISysParameterApiDao {

	@Override
	public List<SysParameter> getSysParameterList(Integer sysSparId) throws HSKDBException {
		String hql ="from SysParameter where 1=1";
		if(sysSparId != null)
			hql += " and sysSparId="+sysSparId;
		else
			hql += " and sysSparId is null";
		hql += " order by paramOrderNumber";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public int getMaxOrderByParentId(Integer sparId) throws HSKDBException {
		int re =0;
		String sql = "SELECT MAX(param_order_number) AS shu FROM sys_parameter WHERE 1=1";
		if(sparId != null)
			sql += " and sys_spar_id='"+sparId+"' ";
		else
			sql += " and sys_spar_id is null";
		List<Map<String,Object>> reList = this.getJdbcDao().query(sql);
		if(reList != null && reList.size() > 0){
			re = reList.get(0).get("shu") != null ? (Integer.parseInt(reList.get(0).get("shu").toString())):0;
		}
		return re;
	}

}
