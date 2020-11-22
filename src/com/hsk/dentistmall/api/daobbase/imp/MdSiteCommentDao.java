package com.hsk.dentistmall.api.daobbase.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.hsk.dentistmall.api.daobbase.IMdSiteCommentDao;
import com.hsk.dentistmall.api.persistence.MdCommentMaterielView;
import com.hsk.dentistmall.api.persistence.MdCommentSupplierView;
import com.hsk.dentistmall.api.persistence.MdCommentTypeView;
import com.hsk.dentistmall.api.persistence.MdSiteComment;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysWebsiteComment;

/**
 * md_materiel_type表数据库层面操作实现类 
 * @author Administrator
 *
 */
@Component
public class MdSiteCommentDao extends SupperDao implements IMdSiteCommentDao {

	@Override
	public MdSiteComment findMdSiteCommentById(Integer mscId)throws HSKDBException {
		MdSiteComment mdSiteComment = new MdSiteComment();
		if(mscId != null){
			mdSiteComment.setMscId(mscId);
			Object obj = this.getOne(mdSiteComment);
			if(obj != null)
				mdSiteComment=(MdSiteComment) obj;
		}
		return mdSiteComment;
	}

	@Override
	public void deleteMdSiteCommentById(Integer mscId) throws HSKDBException {
		MdSiteComment mdSiteComment = new MdSiteComment();
		if(mscId != null){
			mdSiteComment.setMscId(mscId);
			Object obj = this.getOne(mdSiteComment);
			if(obj != null)
				this.deleteObject(obj);
		}
	}

	@Override
	public MdSiteComment updateMdSiteCommentById(Integer mscId,MdSiteComment mdSiteComment) throws HSKDBException {
		if(mscId!=null){
			mdSiteComment.setMscId(mscId);
		   	Object obj=	this.getOne(mdSiteComment);
			if(obj!=null){							 
					this.updateObject(obj);
			}
		}
		return  mdSiteComment;
	}

	@Override
	public Integer saveMdSiteComment(MdSiteComment mdSiteComment)throws HSKDBException {
		return this.newObject(mdSiteComment);
	}

	@Override
	public MdSiteComment saveOrUpdateMdSiteComment(MdSiteComment mdSiteComment) throws HSKDBException {
		this.getHibernateTemplate().saveOrUpdate(mdSiteComment);
		return mdSiteComment;
	}

	@Override
	public PagerModel getMdCommentMaterielViewPager(MdCommentMaterielView mdCommentMaterielView) throws HSKDBException {
		String hql = "from MdCommentMaterielView where 1=1";
		if(mdCommentMaterielView != null && mdCommentMaterielView.getSwcId() != null)
			hql += " and swcId="+mdCommentMaterielView.getSwcId();
		if(mdCommentMaterielView != null && mdCommentMaterielView.getMatName() != null && !mdCommentMaterielView.getMatName().trim().equals(""))
			hql += " and matName like '%"+mdCommentMaterielView.getMatName().trim()+"%'";
		if(mdCommentMaterielView != null && mdCommentMaterielView.getState() != null && !mdCommentMaterielView.getState().trim().equals(""))
			hql += " and state = '"+mdCommentMaterielView.getState().trim()+"'";
		if(mdCommentMaterielView != null && mdCommentMaterielView.getWzState() != null && !mdCommentMaterielView.getWzState().trim().equals(""))
			hql += " and wzState = '"+mdCommentMaterielView.getWzState().trim()+"'";
		if(mdCommentMaterielView != null && mdCommentMaterielView.getCommState() != null && !mdCommentMaterielView.getCommState().trim().equals(""))
			hql += " and commState = '"+mdCommentMaterielView.getCommState().trim()+"'";
		return this.getHibernateDao().findByPage(hql); 
	}

	@Override
	public PagerModel getMdCommentSupplierViewPager(
			MdCommentSupplierView mdCommentSupplierView) throws HSKDBException {
		String hql = "from MdCommentSupplierView where 1=1";
		if(mdCommentSupplierView != null && mdCommentSupplierView.getSwcId() != null)
			hql += " and swcId="+mdCommentSupplierView.getSwcId();
		if(mdCommentSupplierView != null && mdCommentSupplierView.getApplicantName() != null && !mdCommentSupplierView.getApplicantName().trim().equals(""))
			hql += " and applicantName like '%"+mdCommentSupplierView.getApplicantName().trim()+"%'";
		if(mdCommentSupplierView != null && mdCommentSupplierView.getState() != null && !mdCommentSupplierView.getState().trim().equals(""))
			hql += " and state = '"+mdCommentSupplierView.getState().trim()+"'";
		if(mdCommentSupplierView != null && mdCommentSupplierView.getCommState() != null && !mdCommentSupplierView.getCommState().trim().equals(""))
			hql += " and commState = '"+mdCommentSupplierView.getCommState().trim()+"'";
		return this.getHibernateDao().findByPage(hql); 
	}

	@Override
	public PagerModel getMdCommentTypeViewPager(
			MdCommentTypeView mdCommentTypeView) throws HSKDBException {
		String hql = "from MdCommentTypeView where 1=1";
		if(mdCommentTypeView != null && mdCommentTypeView.getSwcId() != null)
			hql += " and swcId="+mdCommentTypeView.getSwcId();
		if(mdCommentTypeView != null && mdCommentTypeView.getMmtName() != null && !mdCommentTypeView.getMmtName().trim().equals(""))
			hql += " and mmtName like '%"+mdCommentTypeView.getMmtName().trim()+"%'";
		if(mdCommentTypeView != null && mdCommentTypeView.getState() != null && !mdCommentTypeView.getState().trim().equals(""))
			hql += " and state = '"+mdCommentTypeView.getState().trim()+"'";
		if(mdCommentTypeView != null && mdCommentTypeView.getCommState() != null && !mdCommentTypeView.getCommState().trim().equals(""))
			hql += " and commState = '"+mdCommentTypeView.getCommState().trim()+"'";
		return this.getHibernateDao().findByPage(hql); 
	}

	@Override
	public int getMaxOrderByParentId(Integer swcId) throws HSKDBException {
		int re =0;
		String sql = "SELECT MAX(serial_comm) AS shu FROM md_site_comment WHERE swc_id='"+swcId+"'";
		List<Map<String,Object>> reList = this.getJdbcDao().query(sql);
		if(reList != null && reList.size() > 0){
			re = reList.get(0).get("shu") != null ? (Integer.parseInt(reList.get(0).get("shu").toString())):0;
		}  
		return re;
	}

	@Override
	public List<MdCommentMaterielView> getMdCommentMaterielViewListBySwcId(
			Integer swcId,Integer number) throws HSKDBException {
		String hql=" from MdCommentMaterielView t where t.swcId= "+swcId+" and state='1' and commState='1' and wzState='1' order by t.serialComm,t.mscId desc";
		if(number==null){
			return (List<MdCommentMaterielView>) this.getHibernatesession().createQuery(hql).list();
		}else{
			return (List<MdCommentMaterielView>) this.getHibernatesession().createQuery(hql).setFirstResult(0).setMaxResults(number).list();
		}
	}

	@Override
	public List<MdCommentSupplierView> getMdCommentSupplierViewListBySwcId(
			Integer swcId,Integer number) throws HSKDBException {
		String hql=" from MdCommentSupplierView t where t.swcId= "+swcId+" and state='1' and commState='1' order by t.serialComm,t.mscId desc";
		if(number==null){
			return (List<MdCommentSupplierView>) this.getHibernatesession().createQuery(hql).list();
		}else{
			return (List<MdCommentSupplierView>) this.getHibernatesession().createQuery(hql).setFirstResult(0).setMaxResults(number).list();
		}
	}

	@Override
	public List<MdCommentTypeView> getMdCommentTypeViewListBySwcId(Integer swcId,Integer number)
			throws HSKDBException {
		String hql=" from MdCommentTypeView t where t.swcId= "+swcId+" and state='1' and commState='1' order by t.serialComm,t.mscId desc";
		if(number==null){
			return (List<MdCommentTypeView>) this.getHibernatesession().createQuery(hql).list();
		}else{
			return (List<MdCommentTypeView>) this.getHibernatesession().createQuery(hql).setFirstResult(0).setMaxResults(number).list();
		}
	}

}
