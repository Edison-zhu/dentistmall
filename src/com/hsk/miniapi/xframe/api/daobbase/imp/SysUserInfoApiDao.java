package com.hsk.miniapi.xframe.api.daobbase.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hsk.xframe.api.persistence.SysFeedbackEntity;
import org.springframework.stereotype.Repository;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.until.javaclass.ClassUtil;
import com.hsk.miniapi.xframe.api.daobbase.ISysUserInfoApiDao;
import com.hsk.xframe.api.persistence.SysGxControlUser;
import com.hsk.xframe.api.persistence.SysUserInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author 张曙
 *
 */
@Repository
public class SysUserInfoApiDao extends SupperDao implements ISysUserInfoApiDao {

	@Override
	public SysUserInfo userLogin(String userName) throws HSKDBException {
	
		String hql = "from SysUserInfo where state=1 and account='"+userName+"'";
		List<SysUserInfo> reList = this.getHibernateTemplate().find(hql);
		if(reList != null && reList.size() > 0)
			return reList.get(0);
		return null;
	}

	public SysUserInfo userLoginPhoneNumber(String userName) throws HSKDBException{
		String hql = "from SysUserInfo where state=1 and user_phone_number='"+userName+"'";
		List<SysUserInfo> reList = this.getHibernateTemplate().find(hql);
		if(reList != null && reList.size() > 0)
			return reList.get(0);
		return null;
	}

	public Integer PhoneNumberCount(String userName) throws HSKDBException{
		String sql=" SELECT COUNT(*) as phoneCount  FROM sys_user_info WHERE user_phone_number='"+userName+"'";
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list.size() <= 0) {
			return 0;
		}
		Map<String, Object> map = list.get(0);
		if (map == null) {
			return 0;
		}
		return Integer.parseInt(map.get("phoneCount").toString());
	}
	
	public SysUserInfo getSysUserInfoByObj(SysUserInfo sysUserInfo)
			throws HSKDBException {
		if (sysUserInfo == null) {
			return null;
		}
		String hql = "from SysUserInfo where 1=1";
		if(sysUserInfo.getAccount() != null && !sysUserInfo.getAccount().trim().equals(""))
			hql += " and account='"+sysUserInfo.getAccount().trim()+"'";
		if (sysUserInfo.getPhoneNumber() != null && !sysUserInfo.getPhoneNumber().trim().equals(""))
			hql += " and phoneNumber = '" + sysUserInfo.getPhoneNumber() + "'";
		if (sysUserInfo.getThirdParty() != null && !sysUserInfo.getThirdParty().trim().equals("")) {
			hql += " and thirdParty = '" + sysUserInfo.getThirdParty() + "'";
		}
		List<SysUserInfo> userList = this.getHibernateTemplate().find(hql);
		if(userList != null && userList.size() > 0)
			return userList.get(0);
		return null;
	}


	
	@Override
	public SysUserInfo getSysUserInfoByName(SysUserInfo sysUserInfo)
			throws HSKDBException {
		String hql = "from SysUserInfo where 1=1";
		if(sysUserInfo != null && sysUserInfo.getAccount() != null && !sysUserInfo.getAccount().trim().equals(""))
			hql += " and account='"+sysUserInfo.getAccount().trim()+"'";
		List<SysUserInfo> userList = this.getHibernateTemplate().find(hql);
		if(userList != null && userList.size() > 0)
			return userList.get(0);
		return null;
	}
	
	@Override
	public void delUserRoleByUsertAndRoles(Integer suiId, String dels)
			throws HSKDBException {
		String sql = "DELETE FROM sys_gx_role_user where sui_id="+suiId;
		if(dels != null)
			sql += " and srole_id in ("+dels+")";
		this.getJdbcDao().execute(sql);
	}
	@Override
	public void delUserMenus(Integer suiId, Integer sroleId, String menuIds,String roleIds) throws HSKDBException {
		 
		String sql = "DELETE FROM sys_gx_user_menu where 1=1";
		if(suiId != null)
			sql += " and sui_id ="+suiId+"";
		if(sroleId != null)
			sql += " and srole_id ="+sroleId+"";
		if(menuIds != null && !menuIds.equals(""))
			sql += " and smenu_id in ("+menuIds+")";
		if(roleIds != null && !roleIds.equals(""))
			sql += " and srole_id in ("+roleIds+")";
		this.getJdbcDao().execute(sql);
	}

	@Override
	public void delUserControls(Integer suiId, Integer sroleId, String sconIds,String roleIds) throws HSKDBException {
		String sql = "DELETE FROM sys_gx_control_user where 1=1";
		if(suiId != null)
			sql += " and sui_id ="+suiId+"";
		if(sroleId != null)
			sql += " and srole_id ="+sroleId+"";
		if(sconIds != null && !sconIds.equals(""))
			sql += " and scon_id in ("+sconIds+")";
		if(roleIds != null && !roleIds.equals(""))
			sql += " and srole_id in ("+roleIds+")";
		this.getJdbcDao().execute(sql);
	}

	@Override
	public List<Map<String, Object>> getSysUserControlList(Integer suiId,Integer smenuId) throws HSKDBException {
		String sql = "select a.scon_id,a.if_operate from "
				+"sys_gx_control_user a,sys_control_info b where a.scon_id=b.scon_id and a.sui_id="+suiId+" and b.smenu_id="+smenuId;
		return this.getJdbcDao().query(sql);
	}

	@Override
	public List<SysGxControlUser> getSysGxControlUserListBySconIds(Integer suiId, String sconIds) throws HSKDBException {
		 String hql ="from SysGxControlUser where suiId="+suiId+" and sconId in ("+sconIds+")";
		 return this.getHibernateTemplate().find(hql); 
	}

	@Override
	public List<Map<Object, Object>> getSysUserMenuListBySuiId(Integer suiId)
			throws HSKDBException {
		String sql= "SELECT distinct b.menu_name,b.smenu_id,b.sys_smenu_id,b.menu_addree,b.menu_icon,b.menu_order_code,b.menu_type,c.root_path "
			+"FROM sys_gx_user_menu a,sys_menu_info b left join sys_file_info c on c.file_code=b.file_code"
			+" WHERE a.sui_id="+suiId+" AND a.smenu_id=b.smenu_id ORDER BY b.menu_order_code";
		return this.getJdbcDao().query(sql);
	}

	@Override
	public List<Map<Object, Object>> getSysUserControlListBySuiId(Integer suiId)
			throws HSKDBException {
		String sql= "SELECT distinct a.scon_id,b.scon_code,a.if_operate FROM sys_gx_control_user a,sys_control_info b WHERE a.sui_id="+suiId+" AND a.scon_id=b.scon_id";
		return this.getJdbcDao().query(sql);
	}

	@Override
	public void saveUser(SysUserInfo user01) throws HSKDBException {
		this.newObject(user01);
		
	}

	@Override
	public PagerModel getSysUserInfoPage(SysUserInfo user)
			throws HSKDBException {
		String ur="";
		if(user.getUserRole()!=null){
			ur=user.getUserRole();
			user.setUserRole(null);
		}
		 String hql=ClassUtil.getHQL(user);
		 StringBuffer sbuffer = new StringBuffer();
		 String att_state=user.getState_str();
		  
			if(att_state!=null&&!"".equals(att_state.trim())){
				hql+= " and  tempA.state in ("+att_state+")";
			}		 
			if(ur!=null&&!"".equals(ur)){
				hql+= " and  tempA."+ur;
			}
 
		return  this.getHibernateDao().findByPage(hql);
	}

	@Override
	public void saveFeedBack(SysFeedbackEntity feedbackEntity, SysUserInfo sysUserInfo) throws HSKDBException {
		this.newObject(feedbackEntity);
	}

	@Override
	public Map<String, Object> getSysUserInfoOpenSecurityBySuiId(Integer suiId) throws HSKDBException {
		if (suiId == null) {
			return null;
		}
		String sql = "select open_security as openSecurity, security_code as securityCode, need_security as needSecurity from sys_user_info where sui_id = " + suiId;
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list == null || list.isEmpty())
			return null;
		Map<String, Object> map = list.get(0);
		return map;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateUserNeedSecurity(Integer needSecurity, Integer suiId) throws HSKDBException {
		String sql = "update sys_user_info set need_security = " + needSecurity + " where sui_id = " + suiId;
		this.getJdbcDao().execute(sql);
	}
}
