package com.hsk.miniapi.xframe.api.daobbase.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysFileInfoApiDao;
import com.hsk.xframe.api.persistence.SysFileInfo;

@Repository
public class SysFileInfoApiDao extends SupperDao implements ISysFileInfoApiDao {

	@Override
	public SysFileInfo getSysFileInfoByFileCode(String fileCode)
			throws HSKDBException {
		String hql="from SysFileInfo where fileCode='"+fileCode+"'";
		List<SysFileInfo> list=(List<SysFileInfo>) this.getHibernatesession().createQuery(hql).list();
		if(list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public List<SysFileInfo> getSysFileInfoByCodes(String fileCodes)
			throws HSKDBException {
		String hql = "from SysFileInfo where fileCode in ("+fileCodes+")";
		return this.getHibernateTemplate().find(hql);
	}

}
