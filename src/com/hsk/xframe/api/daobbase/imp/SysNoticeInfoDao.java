package com.hsk.xframe.api.daobbase.imp;

import java.util.List;
import org.springframework.stereotype.Component;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.daobbase.ISysNoticeInfoDao;
import com.hsk.xframe.api.persistence.SysNoticeInfo;

/**
 * md_no_buy表数据库层面操作实现类 
 * @author Administrator
 *
 */
@Component
public class SysNoticeInfoDao extends SupperDao implements ISysNoticeInfoDao {

	@Override
	public SysNoticeInfo findSysNoticeInfoById(Integer sniId)throws HSKDBException {
		SysNoticeInfo sysNoticeInfo = new SysNoticeInfo();
		if(sniId != null){
			sysNoticeInfo.setSniId(sniId);
			Object obj = this.getOne(sysNoticeInfo);
			if(obj != null)
				sysNoticeInfo=(SysNoticeInfo) obj;
		}
		return sysNoticeInfo;
	}

	@Override
	public void deleteSysNoticeInfoById(Integer sniId) throws HSKDBException {
		SysNoticeInfo sysNoticeInfo = new SysNoticeInfo();
		if(sniId != null){
			sysNoticeInfo.setSniId(sniId);
			Object obj = this.getOne(sysNoticeInfo);
			if(obj != null)
				this.deleteObject(obj);
		}
	}

	@Override
	public SysNoticeInfo updateSysNoticeInfoById(Integer sniId,SysNoticeInfo sysNoticeInfo) throws HSKDBException {
		if(sniId!=null){
			sysNoticeInfo.setSniId(sniId);
		   	Object obj=	this.getOne(sysNoticeInfo);
			if(obj!=null){							 
					this.updateObject(obj);
			}
		}
		return  sysNoticeInfo;
	}

	@Override
	public Integer saveSysNoticeInfo(SysNoticeInfo sysNoticeInfo)throws HSKDBException {
		return this.newObject(sysNoticeInfo);
	}

	@Override
	public SysNoticeInfo saveOrUpdateSysNoticeInfo(SysNoticeInfo sysNoticeInfo) throws HSKDBException {
		this.getHibernateTemplate().saveOrUpdate(sysNoticeInfo);
		return sysNoticeInfo;
	}

	@Override
	public PagerModel getPagerModelBySysNoticeInfo(SysNoticeInfo sysNoticeInfo) throws HSKDBException {
		String Hql=this.getHql(sysNoticeInfo);
		return this.getHibernateDao().findByPage(Hql); 
	}

	@Override
	public List<SysNoticeInfo> getListBySysNoticeInfo(SysNoticeInfo sysNoticeInfo) throws HSKDBException {
		String Hql=this.getHql( sysNoticeInfo); 
		List<SysNoticeInfo> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	
	private String getHql(SysNoticeInfo sysNoticeInfo){
		String hql ="from SysNoticeInfo where 1=1";
		if(sysNoticeInfo != null && sysNoticeInfo.getSniCode() != null && !sysNoticeInfo.getSniCode().trim().equals(""))
			hql += " and sniCode like '%"+sysNoticeInfo.getSniCode().trim()+"%'";
		if(sysNoticeInfo != null && sysNoticeInfo.getTitle() != null && !sysNoticeInfo.getTitle().trim().equals(""))
			hql += " and title like '%"+sysNoticeInfo.getState().trim()+"%'";
		if(sysNoticeInfo != null && sysNoticeInfo.getState() != null && !sysNoticeInfo.getState().trim().equals("") )
			hql += " and state in ("+sysNoticeInfo.getState().trim()+")";
		hql +=" order by sniId desc";
		return hql;
	}


}
