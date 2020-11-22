package com.hsk.xframe.api.daobbase.imp;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.daobbase.ISysWebsiteColumnsDao;
import com.hsk.xframe.api.daobbase.ISysWebsiteInfoDao;
import com.hsk.xframe.api.dto.model.TreeNode;
import com.hsk.xframe.api.persistence.SysWebsiteColumns;
import com.hsk.xframe.api.persistence.SysWebsiteInfo;

/**
 * 网站信息Dao实现类
 * 
 */
@Repository
public class SysWebsiteInfoDao extends SupperDao implements ISysWebsiteInfoDao {
	@Autowired
	private ISysWebsiteColumnsDao sysWebsiteColumnsDao;
	
	@Override
	public Integer NewSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo)
			throws HSKDBException {
		return (Integer) this.getHibernateDao().save(sysWebsiteInfo);
	}

	@Override
	public void DeleteSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo)
			throws HSKDBException {
		this.getHibernateDao().delete(sysWebsiteInfo);
	}

	@Override
	public void UpdateSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo)
			throws HSKDBException {
		this.getHibernateDao().saveOrUpdate(sysWebsiteInfo);
	}

	@Override
	public SysWebsiteInfo findOneSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo)
			throws HSKDBException {
		return (SysWebsiteInfo)this.getOne(sysWebsiteInfo);
	}

	@Override
	public List<SysWebsiteInfo> findListSysWebsiteInfo(
			SysWebsiteInfo sysWebsiteInfo) throws HSKDBException {
		return this.getHibernateDao().find(sysWebsiteInfo);
	}

	@Override
	public PagerModel findPagerModelSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo)
			throws HSKDBException {
		String Hql=this.getHql(sysWebsiteInfo);
		return this.getHibernateDao().findByPage(Hql);
	}

	@Override
	public List<TreeNode> findListSysWebsiteInfo(int maxRows)
			throws HSKDBException {
		List resultList  = null;
		if(maxRows > 0 && maxRows < 1000){
			
			String sql = "SELECT t.swi_id id ,t.websit_name  'text' FROM  sys_website_info t  LIMIT 0,"+maxRows;
			
			RowMapper  rm  = new BeanPropertyRowMapper(TreeNode.class);
			resultList  = this.getJdbcTemplate().query(sql,rm);
			
		}
		
		return resultList;
	}

	@Override
	public boolean isHasChildren(SysWebsiteInfo sysWebsiteInfo)
			throws HSKDBException {
		int id = sysWebsiteInfo.getSwiId();
		String sql ="SELECT COUNT(*) FROM sys_website_columns t WHERE t.swi_id = "+ id;
		int rows  = this.getJdbcTemplate().queryForInt(sql);
		
		return rows > 0 ? true : false ;
	}

	@Override
	public List<SysWebsiteColumns> findListSysWebsiteItems(
			SysWebsiteInfo sysWebsiteInfo) throws HSKDBException {
		return sysWebsiteColumnsDao.findListSysWebsiteColumnsBySite(sysWebsiteInfo);
	}
	/**
	 * yanglei
	 * 2019-12-05
	 * 添加hql方法
	 * @param sysWebsiteInfo
	 * @return
	 */
	private String  getHql(SysWebsiteInfo sysWebsiteInfo){
		 StringBuffer sbuffer = new StringBuffer( " from  SysWebsiteInfo  where  1=1  ");
		 String likeStr=  sysWebsiteInfo.getTab_like();
		 String orderStr= sysWebsiteInfo.getTab_order();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
		 if(sysWebsiteInfo.getWebsitName()!=null&&
				  !"".equals(sysWebsiteInfo.getWebsitName().trim())){
					  if(likeStr!=null&&!"".equals(likeStr.trim())&&likeStr.indexOf("websitName")!=-1){
						  sbuffer.append( " and websitName  like '%"+sysWebsiteInfo.getWebsitName()+"%'"   );
					  }else {
						  sbuffer.append( " and websitName   ='"+sysWebsiteInfo.getWebsitName()+"'"   );
					  }	  		
		 }
		//*****************数据库字段处理end***************
	  		if(orderStr!=null&&!"".equals(orderStr.trim())){
							 sbuffer.append( " order by "+orderStr);
				 }	
		 return  sbuffer.toString(); 
	}

}
