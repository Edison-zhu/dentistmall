package com.hsk.miniapi.xframe.api.daobbase.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.miniapi.xframe.api.daobbase.ISysFieldInfoApiDao;
import com.hsk.xframe.api.persistence.SysFieldInfo;
import com.hsk.xframe.api.persistence.SysWebsiteColumns;

@Repository
public class SysFieldInfoApiDao extends SupperDao implements ISysFieldInfoApiDao {
 
	public Integer newSysFieldInfo(SysFieldInfo st) throws HSKDBException {
		return  this.newObject(st); 
	} 
	 
	public void updateSysFieldInfo(SysFieldInfo st) throws HSKDBException {
		this.updateObject(st);
		   return;
	}
 
	public void delSysFieldInfo(SysFieldInfo st) throws HSKDBException {
		this.deleteObject(st);

	} 
 
	public SysFieldInfo findSysFieldInfoById(Integer id)
			throws HSKDBException {
		SysFieldInfo  sti=new SysFieldInfo();
		sti.setSfieldId(id);
		return (SysFieldInfo) this.getHibernateDao().find(sti); 
	} 
 
	public SysFieldInfo findSysFieldInfoByModel(SysFieldInfo st)
			throws HSKDBException {
		return (SysFieldInfo) this.getHibernateDao().find(st); 
	}
 
	public List<Map<String, Object>> getSysFieldInfoMapList(SysFieldInfo st)
			throws HSKDBException { 
		return  null;
		//this.getHibernateDao().find(st);;
	}
 
	public PagerModel getSysFieldInfoPageParam(SysFieldInfo st)
			throws HSKDBException {
		return this.getPagerModel(st); 
	}

}
