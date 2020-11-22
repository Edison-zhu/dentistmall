package com.hsk.dentistmall.api.daobbase.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hsk.dentistmall.api.daobbase.IMdMaterielTypeDao;
import com.hsk.dentistmall.api.persistence.MdMaterielType;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_materiel_type表数据库层面操作实现类 
 * @author Administrator
 *
 */
@Component
public class MdMaterielTypeDao extends SupperDao implements IMdMaterielTypeDao {

	@Override
	public MdMaterielType findMdMaterielTypeById(Integer mmtId)throws HSKDBException {
		MdMaterielType mdMaterielType = new MdMaterielType();
		if(mmtId != null){
			mdMaterielType.setMmtId(mmtId);
			Object obj = this.getOne(mdMaterielType);
			if(obj != null)
				mdMaterielType=(MdMaterielType) obj;
		}
		return mdMaterielType;
	}

	@Override
	public void deleteMdMaterielTypeById(Integer mmtId) throws HSKDBException {
		MdMaterielType mdMaterielType = new MdMaterielType();
		if(mmtId != null){
			mdMaterielType.setMmtId(mmtId);
			Object obj = this.getOne(mdMaterielType);
			if(obj != null)
				this.deleteObject(obj);
		}
	}

	@Override
	public MdMaterielType updateMdMaterielTypeById(Integer mmtId,MdMaterielType mdMaterielType) throws HSKDBException {
		if(mmtId!=null){
			mdMaterielType.setMmtId(mmtId);
		   	Object obj=	this.getOne(mdMaterielType);
			if(obj!=null){							 
					this.updateObject(obj);
			}
		}
		return  mdMaterielType;
	}

	@Override
	public Integer saveMdMaterielType(MdMaterielType mdMaterielType)throws HSKDBException {
		return this.newObject(mdMaterielType);
	}

	@Override
	public MdMaterielType saveOrUpdateMdMaterielType(MdMaterielType mdMaterielType) throws HSKDBException {
		this.getHibernateTemplate().saveOrUpdate(mdMaterielType);
		return mdMaterielType;
	}

	@Override
	public PagerModel getPagerModelByMdMaterielType(MdMaterielType mdMaterielType) throws HSKDBException {
		String Hql=this.getHql(mdMaterielType);
		return this.getHibernateDao().findByPage(Hql); 
	}

	@Override
	public List<MdMaterielType> getListByMdMaterielType(MdMaterielType mdMaterielType) throws HSKDBException {
		String Hql=this.getHql( mdMaterielType); 
		List<MdMaterielType> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	
	private String getHql(MdMaterielType mdMaterielType){
		String hql ="from MdMaterielType where 1=1";
		if(mdMaterielType != null && mdMaterielType.getMdMmtId() != null && mdMaterielType.getMdMmtId()>0)
			hql += " and mdMmtId="+mdMaterielType.getMdMmtId();
		else if(mdMaterielType != null && mdMaterielType.getMdMmtId() != null && mdMaterielType.getMdMmtId()<=0)
			hql += " and mdMmtId is null";
		if(mdMaterielType != null && mdMaterielType.getMmtCode() != null && !mdMaterielType.getMmtCode().trim().equals(""))
			hql += "and mmtCode like '%"+mdMaterielType.getMmtCode().trim()+"%'";
		if(mdMaterielType != null && mdMaterielType.getMmtName() != null && !mdMaterielType.getMmtName().trim().equals(""))
			hql += "and mmtName like '%"+mdMaterielType.getMmtName().trim()+"%'";
		if(mdMaterielType != null && mdMaterielType.getMmtLevel() != null && !mdMaterielType.getMmtLevel().trim().equals(""))
			hql += "and mmtLevel = '"+mdMaterielType.getMmtLevel().trim()+"'";
		if(mdMaterielType != null && mdMaterielType.getMmtPath() != null && !mdMaterielType.getMmtPath().trim().equals(""))
			hql += "and mmtPath like '%"+mdMaterielType.getMmtPath().trim()+"%'";
		if(mdMaterielType != null && mdMaterielType.getState() != null && !mdMaterielType.getState().trim().equals(""))
			hql += "and state = '"+mdMaterielType.getState().trim()+"'";
		if(mdMaterielType != null && mdMaterielType.getMmtIds() != null && !mdMaterielType.getMmtIds().trim().equals(""))
			hql += "and mmtId in("+mdMaterielType.getMmtIds().trim()+")";
		hql +=" order by mmtId";
		return hql;
	}

	@Override
	public List<Map<Object, Object>> getTreeListByMdMmtId(Integer mdMmtId)throws HSKDBException {
		List<Map<Object, Object>> reList = new ArrayList<Map<Object, Object>>();
		String sql = "select a.mmt_id,a.mmt_name,(select count(1) from md_materiel_type b where b.md_mmt_id=a.mmt_id) as shu from md_materiel_type a where 1=1";
		if(mdMmtId != null && mdMmtId >0)
			sql += " and a.md_mmt_id="+mdMmtId +" order by mmt_id";
		else
			sql += " and (a.md_mmt_id is null or a.md_mmt_id=0) order by mmt_id";
		reList = this.getJdbcDao().query(sql);
		return reList;
	}

}
