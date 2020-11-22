package com.hsk.xframe.api.daobbase.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.until.javaclass.ClassUtil;
import com.hsk.xframe.api.daobbase.ISysWebsiteColumnsDao;
import com.hsk.xframe.api.daobbase.ISysWebsiteCommentDao;
import com.hsk.xframe.api.dto.model.TreeNode;
import com.hsk.xframe.api.persistence.SysWebsiteColumns;
import com.hsk.xframe.api.persistence.SysWebsiteInfo;

/**
 * 栏目信息Dao实现类
 * 
 */
@SuppressWarnings("unchecked")
@Repository
public class SysWebSiteColumnsDao extends SupperDao implements
		ISysWebsiteColumnsDao {
	@Autowired
	private ISysWebsiteCommentDao sysWebsiteCommentDao;
	
	@Override
	public Integer NewSysWebSiteColumns(SysWebsiteColumns siteColumns)
			throws HSKDBException {
		return (Integer) this.getHibernateDao().save(siteColumns);
	}

	@Override
	public void DeleteSysWebsiteColumns(SysWebsiteColumns siteColumns)
			throws HSKDBException {
		this.getHibernateDao().delete(siteColumns);
	}

	@Override
	public void UpdateMSysWebsiteColumns(SysWebsiteColumns siteColumns)
			throws HSKDBException {
		this.getHibernateDao().saveOrUpdate(siteColumns);
	}

	@Override
	public SysWebsiteColumns findOneSysWebsiteColumns(
		SysWebsiteColumns siteColumns) throws HSKDBException {
		//非法状态
		if(siteColumns == null ){
			return new SysWebsiteColumns();
		}
		Integer id  = siteColumns.getSwcId();
		String itemCode  = siteColumns.getColumnscode();
		if(id == null && StringUtils.isBlank(itemCode) ){
			return siteColumns;
		}
		return (SysWebsiteColumns) this.getOne(siteColumns);
	}

	@Override
	public List<SysWebsiteColumns> findListSysWebsiteColumns(
			SysWebsiteColumns siteColumns) throws HSKDBException {
		return this.getHibernateDao().find(siteColumns);
	}

	@Override
	public List<SysWebsiteColumns> findListSysWebsiteColumns(
			SysWebsiteColumns siteColumns, int count, boolean isOrder)
			throws HSKDBException {
		String hql  = ClassUtil.getHQL(siteColumns);
		if(isOrder){
			hql = hql + " order by serialNumber";
			
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
	public PagerModel findPagerModelSysWebsiteColumns(
			SysWebsiteColumns siteColumns) throws HSKDBException {
		return this.getHibernateDao().findByPage(siteColumns);
	}

	@Override
	public List<Map<Object, Object>> findSysWebsiteColumnsList(Integer swcId,
			Integer swiId) throws HSKDBException {
		String sql = "";
		if(swcId == null || swcId == 0){
			sql = "SELECT a.swc_id,a.column_name,a.msz_type,a.sys_swc_id,(SELECT COUNT(1) FROM sys_website_columns b WHERE b.sys_swc_id=a.swc_id) AS shu FROM sys_website_columns a WHERE a.sys_swc_id IS NULL "
				+" order by swc_id";
		}else{
			sql = "select a.swc_id,a.column_name,a.msz_type,a.sys_swc_id,(select count(1) from sys_website_columns b where b.sys_swc_id=a.swc_id) as shu from sys_website_columns a where a.sys_swc_id='"+swcId+"' order by swc_id";
		}
		return this.getJdbcDao().query(sql);
	}

	@Override
	public List<TreeNode> findSysWebsiteColumnsTree(final String webSiteId,String columnId) throws HSKDBException {
		String idWhere = columnId == null ? " and (t.sys_swc_id = '' OR t.sys_swc_id IS NULL)":" and t.sys_swc_id = "+columnId;
		String sql = "SELECT t.swc_id id,t.column_name 'text',t.columnsCode FROM sys_website_columns t WHERE 1=1 " + idWhere;
		if(webSiteId != null && !webSiteId.equals(""))
			sql += " and t.swi_id = '"+webSiteId;
		
		List<TreeNode> resultTree = this.getJdbcTemplate().query(sql,new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TreeNode node = new TreeNode();
				node.setId(rs.getString("id"));
				node.setText(rs.getString("text"));
				node.setName(rs.getString("text"));
				try {
					//栏目标签
					int count= sysWebsiteCommentDao.findSysWebsiteCommentCountByItem(node.getId());
					List tagsList  = new ArrayList();
					tagsList.add(count);
					node.setTags(tagsList);
					
					List<TreeNode> childs = findSysWebsiteColumnsTree(webSiteId,node.getId());
					if(childs != null && childs.size() > 0){
						node.setChildren(childs);
						node.setNodes(childs);
						node.setIsParent("true");
					}
				} catch (HSKDBException e) {
					e.printStackTrace();
				}
				
				return node;
			}
		});
		
		
		return resultTree;
	}

	@Override
	public boolean isHasChildrenItem(SysWebsiteColumns siteColumns)
			throws HSKDBException {
		int id = siteColumns.getSwcId();
		String sql = "SELECT COUNT(*) FROM sys_website_columns t WHERE t.sys_swc_id = "+id;
		
		int rows  = this.getJdbcTemplate().queryForInt(sql);
		
		return rows > 0 ? true : false;
	}

	@Override
	public boolean isHasContent(SysWebsiteColumns siteColumns)
			throws HSKDBException {
		int id = siteColumns.getSwcId();
		String sql = "SELECT COUNT(*) FROM sys_websit_comment t WHERE t.swc_id = "+id;
		
		int rows  = this.getJdbcTemplate().queryForInt(sql);
		
		return rows > 0 ? true : false;
	}

	@Override
	public List<SysWebsiteColumns> findListSysWebsiteColumnsBySite(
			SysWebsiteInfo sysWebsiteInfo) throws HSKDBException {
		String sql = "";
		int  id = sysWebsiteInfo.getSwiId();
		sql  = "SELECT * FROM sys_website_columns t WHERE t.state ='1' and t.swi_id = "+id+" ORDER BY t.Serial_number LIMIT 0,3";
		RowMapper  rm  =  new BeanPropertyRowMapper(SysWebsiteColumns.class);
		List<SysWebsiteColumns> list = this.getJdbcTemplate().query(sql,rm);
		return list;
	}

	@Override
	public List<SysWebsiteColumns> findMxxzSiteColumnsByParentId(Integer swcId,
			Integer number) throws HSKDBException {
		List<SysWebsiteColumns> list=null;
		String hql=" from SysWebsiteColumns t where t.sysSwcId= "+swcId+" and state=1 order by t.serialNumber";
		if(number==null){
			list=(List<SysWebsiteColumns>) this.getHibernatesession().createQuery(hql).list();
		}else{
			list=(List<SysWebsiteColumns>) this.getHibernatesession().createQuery(hql).setFirstResult(0).setMaxResults(number).list();
		}
		return list;
	}

	@Override
	public Integer[] findColumnIdsByCondition(Integer sysSwcId, Integer number,
			Integer swcId) throws HSKDBException {
		List<SysWebsiteColumns> list=null;
		String hql=" from SysWebsiteColumns t where t.mxxMszId= "+sysSwcId ;
		if(swcId!=null){
			hql+=" and swcId !="+swcId; 
		}
		hql+=" order by t.serialNumber ";
		if(number==null){
			list=(List<SysWebsiteColumns>) this.getHibernatesession().createQuery(hql).list();
		}else{
			list=(List<SysWebsiteColumns>) this.getHibernatesession().createQuery(hql).setFirstResult(0).setMaxResults(number).list();
		}
		Integer[] ids;
		if(list!=null&&list.size()>0){
			int len=list.size();
			ids=new Integer[len];
			for(int i=0;i<len;i++){
				ids[i]=list.get(i).getSwcId();
			}
		}else{
			ids=null;
		}
		return ids;
	}

	@Override
	public List<SysWebsiteColumns> findColumnsByCondition(Integer sysSwcId,
			Integer number, Integer swcId) throws HSKDBException {
		List<SysWebsiteColumns> list=null;
		String hql=" from SysWebsiteColumns t where t.sysSwcId= "+sysSwcId ;
		if(swcId!=null){
			hql+=" and swcId !="+swcId; 
		}
		hql+=" order by t.serialNumber ";
		if(number==null){
			list=(List<SysWebsiteColumns>) this.getHibernatesession().createQuery(hql).list();
		}else{
			list=(List<SysWebsiteColumns>) this.getHibernatesession().createQuery(hql).setFirstResult(0).setMaxResults(number).list();
		}
		return list;
	}

	@Override
	public List<SysWebsiteColumns> findListByCondition(Integer sysSwcId,
			Integer number, Integer swcId, Integer state) throws HSKDBException {
		List<SysWebsiteColumns> list=null;
		String hql=" from SysWebsiteColumns t where t.sysSwcId= "+sysSwcId ;
		if(swcId!=null){
			hql+=" and swcId !="+swcId; 
		}
		if(state!=null){
			hql+=" and state ="+state; 
		}
		hql+=" order by t.serialNumber ";
		if(number==null){
			list=(List<SysWebsiteColumns>) this.getHibernatesession().createQuery(hql).list();
		}else{
			list=(List<SysWebsiteColumns>) this.getHibernatesession().createQuery(hql).setFirstResult(0).setMaxResults(number).list();
		}
		return list;
	}
	
	@Override
	public int getMaxOrderByParentId(Integer sysSwcId) throws HSKDBException {
		int re =0;
		String sql ="";
		if(sysSwcId !=  null && sysSwcId> 0)
			sql = "SELECT MAX(serial_number) AS shu FROM sys_website_columns WHERE sys_swc_id='"+sysSwcId+"'";
		else
			sql = "SELECT MAX(serial_number) AS shu FROM sys_website_columns WHERE sys_swc_id is null";
		List<Map<String,Object>> reList = this.getJdbcDao().query(sql);
		if(reList != null && reList.size() > 0){
			re = reList.get(0).get("shu") != null ? (Integer.parseInt(reList.get(0).get("shu").toString())):0;
		}  
		return re;
	}
	
}
