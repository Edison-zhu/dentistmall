package com.hsk.xframe.api.daobbase.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.daobbase.ISysTableInfoDao;
import com.hsk.xframe.api.persistence.SysTableInfo;


@Repository
public class SysTableInfoDao extends SupperDao  implements  ISysTableInfoDao {
	 
	public Integer newSysTableInfo(SysTableInfo st) throws HSKDBException {
		return  this.newObject(st); 
	}
 
	public void updateSysTableInfo(SysTableInfo st) throws HSKDBException {
		   this.updateObject(st);
		   return;
	}
 
	public void delSysTableInfo(SysTableInfo st) throws HSKDBException {
		 this.deleteObject(st);
	}
	 
	public SysTableInfo findSysTableInfoById(Integer id)
			throws HSKDBException {
		SysTableInfo  sti=new SysTableInfo();
		sti.setStableId(id);
		return (SysTableInfo) this.getHibernateDao().find(sti); 
	}
	 
	public SysTableInfo findSysTableInfoByModel(SysTableInfo st)
			throws HSKDBException {
		return (SysTableInfo) this.getHibernateDao().find(st); 
	}
 
	public List<Map<String, Object>> getSysTableInfoMapList(SysTableInfo st)
			throws HSKDBException {
		 this.getHibernateDao().find(st);
		return null;
	}

	public PagerModel getSysTableInfoPageParam(SysTableInfo st)
			throws HSKDBException {
		return this.getPagerModel(st); 
	}

}
