package com.hsk.xframe.api.daobbase.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.until.javaclass.ClassUtil;
import com.hsk.xframe.api.daobbase.ISysWebsiteCommentDao;
import com.hsk.xframe.api.persistence.SysWebsiteColumns;
import com.hsk.xframe.api.persistence.SysWebsiteComment;

/**
 * 内容信息Dao实现类
 * 
 * @author cxd
 * @author yc.chen @2015-3-12
 * @version v1.0 2015-02-27
 * 
 */
@Repository
public class SysWebsiteCommentDao extends SupperDao implements ISysWebsiteCommentDao {

	@Override
	public Integer NewSysWebsiteComment(SysWebsiteComment sysWebsiteComment)
			throws HSKDBException {
		return (Integer) this.getHibernateDao().save(sysWebsiteComment);
	}

	@Override
	public void DeleteSysWebsiteComment(SysWebsiteComment sysWebsiteComment)
			throws HSKDBException {
		this.getHibernateDao().delete(sysWebsiteComment);
	}

	@Override
	public void UpdateSysWebsiteComment(SysWebsiteComment sysWebsiteComment)
			throws HSKDBException {
		this.getHibernateDao().saveOrUpdate(sysWebsiteComment);
	}

	@Override
	public SysWebsiteComment findOneSysWebsiteComment(SysWebsiteComment sysWebsiteComment) throws HSKDBException {
		return (SysWebsiteComment)this.getOne(sysWebsiteComment);
	}

	@Override
	public List<SysWebsiteComment> findListSysWebsiteComment(
			SysWebsiteComment sysWebsiteComment) throws HSKDBException {
		return this.getHibernateDao().find(sysWebsiteComment);
	}

	@Override
	public List<SysWebsiteComment> findListSysWebsiteComment(SysWebsiteComment sysWebsiteComment, int count, boolean isOrder) throws HSKDBException {
		String hql  = ClassUtil.getHQL(sysWebsiteComment);
		if(isOrder){
			hql = hql + " order by serialNumber DESC";
		}
		List result = null;
		if(count > 0){
			PagerModel pm  = this.getHibernateDao().findByPage(hql, 1, count);
			result = pm.getRows();
		}else{
			result = this.getHibernateDao().findColByHqlQuery(hql);
		}
		return result;
	}

	@Override
	public List<SysWebsiteComment> findListSysWebsiteComment(
			SysWebsiteComment sysWebsiteComment, boolean isOrder)
			throws HSKDBException {
		return this.findListSysWebsiteComment(sysWebsiteComment, -1, isOrder);
	}

	@Override
	public PagerModel findPagerModelSysWebsiteComment(
			SysWebsiteComment sysWebsiteComment) throws HSKDBException {
		String hql = "from SysWebsiteComment where 1=1";
		if(sysWebsiteComment != null && sysWebsiteComment.getSwcId() != null)
			hql += " and swcId=" + sysWebsiteComment.getSwcId();
		if(sysWebsiteComment != null && sysWebsiteComment.getState() != null && ! "".equals(sysWebsiteComment.getState().trim()))
			hql += " and state=" + sysWebsiteComment.getState().trim();
		if(sysWebsiteComment != null && sysWebsiteComment.getNewsTitle() != null && !sysWebsiteComment.getNewsTitle().trim().equals(""))
			hql += " and newsTitle like '%" + sysWebsiteComment.getNewsTitle().trim() + "%'";
		hql += " order by createDatetime desc";
		return this.getHibernateDao().findByPage(hql);
	}

	@Override
	public PagerModel findPagerModelSysWebsiteCommentForSql(
			SysWebsiteComment sysWebsiteComment) throws HSKDBException {
		String sql =" SELECT * FROM sys_websit_comment t WHERE 1=1 ";
		
		String title  =  sysWebsiteComment.getNewsTitle();
		if(StringUtils.isNotEmpty(title)){
			sql = sql +  " AND  news_title LIKE  '%"+title+"%' ";
		}
		sql = sql + "ORDER BY t.create_datetime desc ";
		
		return this.getJdbcDao().findByPage(sql);
	}

	@Override
	public int setSysWebsiteCommentToTop(SysWebsiteComment sysWebsiteComment)
			throws HSKDBException {
		int  id =  sysWebsiteComment.getSwmId();
		String sql ="UPDATE sys_websit_comment t SET t.serial_number = 1  WHERE t.swm_id = "+id;
		int rows = this.getJdbcTemplate().update(sql);
		return rows;
	}

	@Override
	public int findSysWebsiteCommentCountByItem(String itemId)
			throws HSKDBException {
		String sql =" SELECT count(*) FROM sys_websit_comment t WHERE 1=1 and t.swc_id= "+ itemId;
		int rows  = this.getJdbcTemplate().queryForInt(sql);
		return rows;
	}

	@Override
	public List<SysWebsiteComment> findPagerModelSysWebsiteCommentById(Integer swcId, Integer number) throws HSKDBException {
		String hql=" from SysWebsiteComment t where t.swcId= "+swcId+" and state=1 order by t.serialNumber,t.createDatetime desc";
		if(number==null){
			return (List<SysWebsiteComment>) this.getHibernatesession().createQuery(hql).list();
		}else{
			return (List<SysWebsiteComment>) this.getHibernatesession().createQuery(hql).setFirstResult(0).setMaxResults(number).list();
		}
	}

	@Override
	public SysWebsiteComment findSysWebsiteCommentBySwmId(Integer swmId)
			throws HSKDBException {
		SysWebsiteComment model  = new  SysWebsiteComment();
		model.setSwmId(swmId);
		return (SysWebsiteComment) this.getOne(model);
	}

	@Override
	public List<SysWebsiteComment> findSysWebsiteCommentByParentId(
			Integer swcId, Integer number, String ifRecommend)
			throws HSKDBException {
		String hql = "from SysWebsiteColumns where sysSwcId="+swcId;
		List<SysWebsiteColumns> culumnsList = this.getHibernateTemplate().find(hql);
		if(culumnsList != null && culumnsList.size() > 0){
			String culumns = "";
			for(SysWebsiteColumns sc : culumnsList){
				culumns += sc.getSwcId()+",";
			}
			culumns = culumns.substring(0, culumns.length() -1);
			hql = "from SysWebsiteComment t where t.swcId in ("+culumns+") and state=2";
			if(ifRecommend != null && !ifRecommend.equals(""))
				hql += " and ifRecommend='"+ifRecommend+"' ";
			hql += " order by t.createDatetime DESC ";
			return (List<SysWebsiteComment>) this.getHibernatesession().createQuery(hql).setFirstResult(0).setMaxResults(number).list();
		}else{
			 hql=" from SysWebsiteComment t where t.swcId= "+swcId +" and state=2";
			 if(ifRecommend != null && !ifRecommend.equals(""))
					hql += " and ifRecommend='"+ifRecommend+"' ";
				hql += " order by t.createDatetime DESC";
				 if(number != null && !number.equals(""))
					 return (List<SysWebsiteComment>) this.getHibernatesession().createQuery(hql).setFirstResult(0).setMaxResults(number).list();
				 else 
					 return (List<SysWebsiteComment>) this.getHibernatesession().createQuery(hql).list();
		}
	}

	@Override
	public PagerModel findSysWebsiteCommentPageByParentId(Integer swcId,
			String ifRecommend, String notShowIds) throws HSKDBException {
		PagerModel pagerModel = new PagerModel();
		String hql = "from SysWebsiteColumns where sysSwcId="+swcId;
		if(notShowIds != null && !notShowIds.equals(""))
			hql += " and swcId not in ("+notShowIds+")";
		List<SysWebsiteColumns> culumnsList = this.getHibernateTemplate().find(hql);
		if(culumnsList != null && culumnsList.size() > 0){
			String culumns = "";
			for(SysWebsiteColumns sc : culumnsList){
				culumns += sc.getSwcId()+",";
			}
			culumns = culumns.substring(0, culumns.length() -1);
			hql = "from SysWebsiteComment t where t.swcId in ("+culumns+")";
			if(ifRecommend != null && !ifRecommend.equals(""))
				hql += " and ifRecommend='"+ifRecommend+"' ";
			hql += " order by t.createDatetime desc";
			pagerModel = this.getHibernateDao().findByPage(hql);
		}else{
			 hql=" from SysWebsiteComment t where t.swcId= "+swcId;
			 if(ifRecommend != null && !ifRecommend.equals(""))
					hql += " and ifRecommend='"+ifRecommend+"' ";
				hql += " order by t.createDatetime desc";
				pagerModel = this.getHibernateDao().findByPage(hql);
		}
		return pagerModel;
	}

	@Override
	public int getMaxOrderByParentId(Integer swcId) throws HSKDBException {
		int re =0;
		String sql = "SELECT MAX(serial_number) AS shu FROM sys_websit_comment WHERE swc_id='"+swcId+"'";
		List<Map<String,Object>> reList = this.getJdbcDao().query(sql);
		if(reList != null && reList.size() > 0){
			re = reList.get(0).get("shu") != null ? (Integer.parseInt(reList.get(0).get("shu").toString())):0;
		}  
		return re;
	}

}
