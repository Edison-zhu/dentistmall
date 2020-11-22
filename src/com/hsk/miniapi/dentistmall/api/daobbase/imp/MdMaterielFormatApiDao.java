package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.persistence.MdMaterielFormatTemp;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdMaterielFormatApiDao;
import com.hsk.dentistmall.api.persistence.MdMaterielFormat;
import com.hsk.dentistmall.api.persistence.MdPriceLog;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_materiel_type表数据库层面操作实现类 
 * @author Administrator
 *
 */
@Component
public class MdMaterielFormatApiDao extends SupperDao implements IMdMaterielFormatApiDao {

	@Override
	public MdMaterielFormat findMdMaterielFormatById(Integer mmfId)throws HSKDBException {
		MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
		if(mmfId != null){
			mdMaterielFormat.setMmfId(mmfId);
			Object obj = this.getOne(mdMaterielFormat);
			if(obj != null)
				mdMaterielFormat=(MdMaterielFormat) obj;
		}
		return mdMaterielFormat;
	}

	@Override
	public void deleteMdMaterielFormatById(Integer mmfId) throws HSKDBException {
		MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
		if(mmfId != null){
			mdMaterielFormat.setMmfId(mmfId);
			Object obj = this.getOne(mdMaterielFormat);
			if(obj != null)
				this.deleteObject(obj);
		}
	}

	@Override
	public MdMaterielFormat updateMdMaterielFormatById(Integer mmfId,MdMaterielFormat mdMaterielFormat) throws HSKDBException {
		if(mmfId!=null){
			mdMaterielFormat.setMmfId(mmfId);
		   	Object obj=	this.getOne(mdMaterielFormat);
			if(obj!=null){							 
					this.updateObject(obj);
			}
		}
		return  mdMaterielFormat;
	}

	@Override
	public Integer saveMdMaterielFormat(MdMaterielFormat mdMaterielFormat)throws HSKDBException {
		return this.newObject(mdMaterielFormat);
	}

	@Override
	public MdMaterielFormat saveOrUpdateMdMaterielFormat(MdMaterielFormat mdMaterielFormat) throws HSKDBException {
		this.getHibernateTemplate().saveOrUpdate(mdMaterielFormat);
		return mdMaterielFormat;
	}

	@Override
	public PagerModel getPagerModelByMdMaterielFormat(MdMaterielFormat mdMaterielFormat) throws HSKDBException {
		String Hql=this.getHql(mdMaterielFormat);
		return this.getHibernateDao().findByPage(Hql); 
	}

	@Override
	public List<MdMaterielFormat> getListByMdMaterielFormat(MdMaterielFormat mdMaterielFormat) throws HSKDBException {
		String Hql=this.getHql( mdMaterielFormat);
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		hibernateTemplate.setMaxResults(Integer.MAX_VALUE);
		List<MdMaterielFormat> list=hibernateTemplate.find(Hql);
		return  list;
	}
	
	private String getHql(MdMaterielFormat mdMaterielFormat){
		String hql ="from MdMaterielFormat where 1=1";
		if(mdMaterielFormat != null && mdMaterielFormat.getMmfIds()!= null && !mdMaterielFormat.getMmfIds().trim().equals(""))
			hql += " and mmfId in ("+mdMaterielFormat.getMmfIds().trim()+")";
		if(mdMaterielFormat != null && mdMaterielFormat.getWmsMiId() != null)
			hql += " and wmsMiId="+mdMaterielFormat.getWmsMiId();
		if(mdMaterielFormat != null && mdMaterielFormat.getState() != null && !mdMaterielFormat.getState().trim().equals(""))
			hql += " and state = '"+mdMaterielFormat.getState().trim()+"'";
		hql +=" order by mmfName, mmfId";
		return hql;
	}

	@Override
	public List<MdPriceLog> getListMdPriceLog(MdPriceLog mdPriceLog)throws HSKDBException {
		String hql ="from MdPriceLog where 1=1";
		if(mdPriceLog != null && mdPriceLog.getMmfIds()!= null && !mdPriceLog.getMmfIds().trim().equals(""))
			hql += " and mmfId in ("+mdPriceLog.getMmfIds().trim()+")";
		if(mdPriceLog.getMmfId() != null)
			hql += " and mmfId='"+mdPriceLog.getMmfId()+"'";
		hql += " order by changeDate";
		List<MdPriceLog> list=this.getHibernateTemplate().find(hql);
		return  list;
	}

	@Override
	public List<MdMaterielFormat> findMdMaterielFormatByMffCode(String mmfCode) throws HSKDBException {
		String sql = "from MdMaterielFormat where 1 = 1";
		if(mmfCode != null && !mmfCode.trim().equals("")){
			sql += " and mmfCode=" + mmfCode;
		}
		List<MdMaterielFormat> mdMaterielFormats = this.getHibernateTemplate().find(sql);
		return mdMaterielFormats;
	}

	@Override
	public List<MdMaterielFormatTemp> getListByMdMaterielFormatNoLogin(MdMaterielFormatTemp att_mdMaterielFormat) throws HSKDBException {
		String Hql=this.getTempHql( att_mdMaterielFormat);
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		hibernateTemplate.setMaxResults(Integer.MAX_VALUE);
		List<MdMaterielFormatTemp> list=hibernateTemplate.find(Hql);
		return  list;
	}
	private String getTempHql(MdMaterielFormatTemp mdMaterielFormat){
		String hql ="from MdMaterielFormatTemp where 1=1";
		if(mdMaterielFormat != null && mdMaterielFormat.getMmfIds()!= null && !mdMaterielFormat.getMmfIds().trim().equals(""))
			hql += " and mmfId in ("+mdMaterielFormat.getMmfIds().trim()+")";
		if(mdMaterielFormat != null && mdMaterielFormat.getWmsMiId() != null)
			hql += " and wmsMiId="+mdMaterielFormat.getWmsMiId();
		if(mdMaterielFormat != null && mdMaterielFormat.getState() != null && !mdMaterielFormat.getState().trim().equals(""))
			hql += " and state = '"+mdMaterielFormat.getState().trim()+"'";
		hql +=" order by mmfName, mmfId";
		return hql;
	}

}
