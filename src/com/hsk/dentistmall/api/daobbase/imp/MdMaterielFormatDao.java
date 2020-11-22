package com.hsk.dentistmall.api.daobbase.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import com.hsk.dentistmall.api.daobbase.IMdMaterielFormatDao;
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
public class MdMaterielFormatDao extends SupperDao implements IMdMaterielFormatDao {

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
	public PagerModel getPagerModelByMdMaterielFormat1(MdMaterielFormat mdMaterielFormat) throws HSKDBException {
		String hql ="from MdMaterielFormat where 1=1";
		if(mdMaterielFormat != null && mdMaterielFormat.getMmfIds()!= null && !mdMaterielFormat.getMmfIds().trim().equals(""))
			hql += " and mmfId in ("+mdMaterielFormat.getMmfIds().trim()+")";
		if(mdMaterielFormat != null && mdMaterielFormat.getWmsMiId() != null)
			hql += " and wmsMiId="+mdMaterielFormat.getWmsMiId();
		if(mdMaterielFormat != null && mdMaterielFormat.getState() != null && !mdMaterielFormat.getState().trim().equals(""))
			hql += " and state = '"+mdMaterielFormat.getState().trim()+"'";
		if (mdMaterielFormat.getMmfName() != null && !mdMaterielFormat.getMmfName().equals("")) {
			hql += " and (mmfName like '%" + mdMaterielFormat.getMmfName() + "%'" +
					" or mmfCode like '%" + mdMaterielFormat.getMmfName() + "%')";
		}
		hql += " and mmfName <> '' and mmfName is not null";
		hql +=" order by mmfName, mmfId";
		return this.getHibernateDao().findByPage(hql);
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
		if (mdMaterielFormat.getMmfName() != null && !mdMaterielFormat.getMmfName().equals("")) {
			hql += " and (mmfName like '%" + mdMaterielFormat.getMmfName() + "%'" +
					" or mmfCode like '%" + mdMaterielFormat.getMmfName() + "%')";
		}
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
	public List<MdMaterielFormat> findMdMaterielFormatByMffCode(String mmfCode, Integer wmsMiId, Integer mmfId, String purchaseType, Integer wzId) throws HSKDBException {
		String sql = "from MdMaterielFormat where 1 = 1";
		if (mmfId != null)
			sql += " and mmfId <> " + mmfId;
		if(mmfCode != null && !mmfCode.trim().equals("")){
			sql += " and mmfCode='" + mmfCode + "'";
		}
		sql += " and (1=1";
		if (wmsMiId != null) {
			sql += " and wmsMiId in (select wmsMiId from MdMaterielInfo where wmsMiId = " + wmsMiId + ")";
//			if (purchaseType != null && !purchaseType.equals("")) {
//				sql += " and wmsMiId in (select wmsMiId from MdMaterielInfo where purchaseType = '" + purchaseType + "'";
//				if (wzId != null) {
//					sql += " and wzId = " + wzId;
//				}
//				sql += ") ";
//			}
		}
		sql += " or wmsMiId in (select wmsMiId from MdMaterielInfo where 1=1";
		if (purchaseType != null && !purchaseType.equals("")) {
			sql += " and purchaseType = '" + purchaseType + "'";
		}
		if (wzId != null) {
			sql += " and wzId = " + wzId;
		}
		sql += ") ";
		sql += ")";

		List<MdMaterielFormat> mdMaterielFormats = this.getHibernateTemplate().find(sql);
		return mdMaterielFormats;
	}

	@Override
	public List<MdMaterielFormat> findMdMaterielFormatByMffName(String mmfName, Integer wmsMiId, Integer mmfId, String purchaseType, Integer wzId) throws HSKDBException {
		if (wmsMiId == null)
			return null;
		String sql = "from MdMaterielFormat where 1 = 1";
		if (mmfId != null) {
			sql += " and mmfId <> " + mmfId;
		}
		if(mmfName != null && !mmfName.trim().equals("")){
			sql += " and mmfName= '" + mmfName + "'";
		}
		if (wmsMiId != null)
			sql += " and wmsMiId = " + wmsMiId;

//		if (purchaseType != null && !purchaseType.equals("")) {
//			sql += " and wmsMiId in (select wmsMiId from MdMaterielInfo where purchaseType = '" + purchaseType + "'";
//			if (wzId != null) {
//				sql += " and wzId = " + wzId;
//			}
//			sql += ") ";
//		}
		List<MdMaterielFormat> mdMaterielFormats = this.getHibernateTemplate().find(sql);
		return mdMaterielFormats;
	}

	@Override
	public List<MdMaterielFormat> findMdMaterielFormatByMffName1(String formatName, Integer wmsMiId) throws HSKDBException {
		String sql = "from MdMaterielFormat where 1 = 1";
		if(formatName != null && !formatName.trim().equals("")){
			formatName = formatName.replace(",", "','");
			formatName = "'" + formatName + "'";
			sql += " and mmfName in (" + formatName + ")";
		}
		if (wmsMiId != null)
			sql += " and wmsMiId = " + wmsMiId;
		List<MdMaterielFormat> mdMaterielFormats = this.getHibernateTemplate().find(sql);
		return mdMaterielFormats;
	}

	@Override
	public List<Map<String, Object>> getMdMaterielFormatMapList(Integer mmfId) throws HSKDBException {
		if (mmfId == null)
			return null;
		String sql = "select create_ren as createRen, create_date as createDate from md_materiel_format where mmf_id = " + mmfId;
		return this.getJdbcDao().query(sql);
	}
}
