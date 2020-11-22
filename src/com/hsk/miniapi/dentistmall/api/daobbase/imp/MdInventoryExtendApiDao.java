package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.util.List;

import com.hsk.dentistmall.api.persistence.MdInventoryEnterwarehouserViewEntity;
import org.springframework.stereotype.Component;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdInventoryExtendApiDao;
import com.hsk.dentistmall.api.persistence.MdInventoryExtend;
import com.hsk.dentistmall.api.persistence.MdInventoryExtendView;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;

@Component
public class MdInventoryExtendApiDao extends SupperDao implements IMdInventoryExtendApiDao {

	@Override
	public MdInventoryExtend getMdInventoryExtendByWiId(Integer wzId,String relatedCode) throws HSKDBException {
		String hql = "from MdInventoryExtend where wiId='"+wzId+"' and relatedCode='"+relatedCode+"'";
		List<MdInventoryExtend> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() >0)
			return list.get(0);
		return null;
	}

	@Override
	public List<MdInventoryExtend> getMdInventoryExtendByWiIdToSel(Integer wiId,String relatedCode,  String startDate, String endDate) throws HSKDBException {
		String hql = "from MdInventoryExtend where wiId='"+wiId+"'";
		if(relatedCode != null && !relatedCode.trim().equals(""))
			hql += " and relatedCode like '%"+relatedCode.trim()+"%'";
		if(startDate !=null && !startDate.trim().equals(""))
			hql += " and createDate >='"+startDate.trim()+" 00:00:00'";
		if(endDate !=null && !endDate.trim().equals(""))
			hql += " and createDate <='"+endDate.trim()+" 23:59:59'";
		hql += " and baseNumber>0 order by createDate";
		List<MdInventoryExtend> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<MdInventoryExtend> getAllList() throws HSKDBException {
		String hql = "from MdInventoryExtend order by createDate";
		List<MdInventoryExtend> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public PagerModel getMdInventoryViewPager(MdInventoryExtendView mdInventoryExtendView, String startDate,String endDate) throws HSKDBException {
		String hql = "from MdInventoryExtendView where state=1";
		if(mdInventoryExtendView != null && mdInventoryExtendView.getWiId()!=null)
			hql += " and wiId='"+mdInventoryExtendView.getWiId()+"'";
		if(mdInventoryExtendView != null && mdInventoryExtendView.getRelatedCode() != null && !mdInventoryExtendView.getRelatedCode().trim().equals(""))
			hql += " and relatedCode like '%"+mdInventoryExtendView.getRelatedCode().trim()+"%'";
		if(mdInventoryExtendView != null && mdInventoryExtendView.getMatType() != null && !mdInventoryExtendView.getMatType().trim().equals(""))
			hql += " and matType like '%/"+mdInventoryExtendView.getMatType().trim()+"/%'";
		if(mdInventoryExtendView != null && mdInventoryExtendView.getTypeName() != null && !mdInventoryExtendView.getTypeName().trim().equals(""))
			hql += " and typeName like '%"+mdInventoryExtendView.getTypeName().trim()+"%'";
		if(mdInventoryExtendView != null && mdInventoryExtendView.getBrand() != null && !mdInventoryExtendView.getBrand().trim().equals(""))
			hql += " and brand like '%"+mdInventoryExtendView.getBrand().trim()+"%'";
		if(startDate !=null && !startDate.trim().equals(""))
			hql += " and createDate >='"+startDate.trim()+" 00:00:00'";
		if(endDate !=null && !endDate.trim().equals(""))
			hql += " and createDate <='"+endDate.trim()+" 23:59:59'";
		hql += " order by baseNumber desc,createDate";
		return this.getHibernateDao().findByPage(hql);
	}

	@Override
	public List<MdInventoryExtend> getExtendEnterViewPagerModel(Integer wiId, String relatedCode, String startDate, String endDate) throws HSKDBException {
		String hql = "from MdInventoryEnterwarehouserViewEntity where wiId='"+wiId+"'";
		if(relatedCode != null && !relatedCode.trim().equals(""))
			hql += " and relatedCode like '%"+relatedCode.trim()+"%'";
		if(startDate !=null && !startDate.trim().equals(""))
			hql += " and createDate >='"+startDate.trim()+" 00:00:00'";
		if(endDate !=null && !endDate.trim().equals(""))
			hql += " and createDate <='"+endDate.trim()+" 23:59:59'";
		hql += " and baseNumber>0 order by createDate";
		List<MdInventoryExtend> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<MdInventoryEnterwarehouserViewEntity> getExtendEnterWarehouseViewPagerModel(Integer wiId, String relatedCode, String startDate, String endDate) throws HSKDBException {
		String hql = "from MdInventoryEnterwarehouserViewEntity where wiId='"+wiId+"'";
		if(relatedCode != null && !relatedCode.trim().equals(""))
			hql += " and relatedCode like '%"+relatedCode.trim()+"%'";
		if(startDate !=null && !startDate.trim().equals(""))
			hql += " and createDate >='"+startDate.trim()+" 00:00:00'";
		if(endDate !=null && !endDate.trim().equals(""))
			hql += " and createDate <='"+endDate.trim()+" 23:59:59'";
		hql += " and baseNumber>0 order by createDate";
		List<MdInventoryEnterwarehouserViewEntity> list = this.getHibernateTemplate().find(hql);
		return list;
	}

}
