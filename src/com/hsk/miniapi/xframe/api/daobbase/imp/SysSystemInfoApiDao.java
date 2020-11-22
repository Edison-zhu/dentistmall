package com.hsk.miniapi.xframe.api.daobbase.imp;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.miniapi.xframe.api.daobbase.ISysSystemInfoApiDao;
import com.hsk.xframe.api.persistence.SysSystemInfo;

/**
 * 
 * @author 张曙
 *
 */
@Repository
public class SysSystemInfoApiDao extends SupperDao implements ISysSystemInfoApiDao {

	 
	public Integer newSysSystemInfo(SysSystemInfo sysSystemInfo)
			throws HSKDBException {
		return (Integer) this.getHibernateDao().save(sysSystemInfo);
	}

	 
	public void updateSysSystemInfo(SysSystemInfo sysSystemInfo)
			throws HSKDBException {
		Session sess=	this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		sess.clear(); ;
		this.getHibernateDao().update(sysSystemInfo);
	}

	 
	public void delSysSystemInfo(SysSystemInfo sysSystemInfo)
			throws HSKDBException {
		this.getHibernateDao().delete(sysSystemInfo);
	}

	 
	public SysSystemInfo findSysSystemInfoById(Integer ssiId)
			throws HSKDBException {
		SysSystemInfo sysSystemInfo = new SysSystemInfo(ssiId);
		return (SysSystemInfo) this.getOne(sysSystemInfo);
	}

	 
	public SysSystemInfo findSysSystemInfoByModel(SysSystemInfo sysSystemInfo)
			throws HSKDBException {
		return (SysSystemInfo) this.getOne(sysSystemInfo);
	}

	 
	public PagerModel getSysSystemInfoPage(SysSystemInfo sysSystemInfo)
			throws HSKDBException {
		String hql = "from SysSystemInfo where 1=1";
		if(sysSystemInfo != null && sysSystemInfo.getSysCode() != null && !sysSystemInfo.getSysCode().trim().equals(""))
			hql += " and sysCode like '%"+sysSystemInfo.getSysCode().trim()+"%'";
		if(sysSystemInfo != null && sysSystemInfo.getSysName() != null && !sysSystemInfo.getSysName().trim().equals(""))
			hql += " and sysName like '%"+sysSystemInfo.getSysName().trim()+"%'";
		if(sysSystemInfo != null && sysSystemInfo.getSysName() != null && !sysSystemInfo.getSysName().trim().equals(""))
			hql += " and sysName like '%"+sysSystemInfo.getSysName().trim()+"%'";
		hql += "order by createTime";
		return this.getHibernateDao().findByPage(hql);
	}

	@Override
	public List<SysSystemInfo> getSysSystemInfoList() throws HSKDBException {
		String hql = "from SysSystemInfo where 1=1 order by createTime";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<SysSystemInfo> findeListSystemInfo(SysSystemInfo sysSystemInfo)
			throws HSKException {
		// TODO Auto-generated method stub
		return null;
	}

}
