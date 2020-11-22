package com.hsk.miniapi.xframe.service.imp;

import com.hsk.dentistmall.api.persistence.MdCompanyGroup;
import com.hsk.dentistmall.api.persistence.MdDentalClinic;
import com.hsk.dentistmall.api.persistence.MdDentistHospital;
import com.hsk.dentistmall.api.persistence.MdSupplier;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.miniapi.SessionUtil;
import com.hsk.miniapi.xframe.api.daobbase.ISysUserInfoApiDao;
import com.hsk.miniapi.xframe.service.IDSTApiService;
import com.hsk.supper.dao.IsupperDao;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.config.HqlConfig;
import com.hsk.supper.service.imp.SupperService;
import com.hsk.xframe.api.daobbase.ISysSalesmanDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysSalesManEntity;
import com.hsk.xframe.api.persistence.SysSwitchEntity;
import com.hsk.xframe.api.persistence.SysUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 基础业务处理函数实现类
 * 
 * @author xun
 * @version v1.0 2015-02-23
 * 
 */
@Service 
public class DSTApiService extends SupperService implements IDSTApiService {
	@Autowired
	private IsupperDao supperDao;
	public static String TEMP_EXPORT_PATH = "/export/";
	public static String EXPORT_PATH= "/fileInfo/updown/export/";
	@Autowired
	public ISysUserInfoApiDao sysUserInfoDao;
	@Autowired
	public ISysSalesmanDao sysSalesmanDao;
	
	@Override
	public SysUserInfo GetOneSessionAccount() throws HSKException {
		//SysUserInfo account =new SysUserInfo();
		SysUserInfo account = this.getApiSessionAccount();
		
		return account;
	}
	
	public SysUserInfo GetOneShoppingSessionAccount() throws HSKException {
		SysUserInfo account = this.getApiSessionAccount();
		
		return account;
	}
	
	public String getOrgIdStr(String[] organizaType) throws HSKException {
		SysUserInfo account =new SysUserInfo();
		Object  obj = this.getApiSessionAccount();;
		if(obj!=null){
			account=(SysUserInfo) obj; 
			 SysOrgGx sog=new SysOrgGx();
			 sog.setOrgGxId(account.getOrgGxId());
			Object objorg= this.getOne(sog);
			if(objorg!=null){
				sog=(SysOrgGx) objorg;
			}
			SysOrgGx searchsog=new SysOrgGx();
		//	searchsog.setOldIdPath(oldIdPath)
	//	List<SysOrgGx>	listsog=this.getList(searchsog);
			
		}
		return null;
	}
	
	@Override
	public void SetSessionAccount(SysUserInfo account) throws HSKException {
		this.getSession().setAttribute(SystemContext.sessionUser, account); 
//		if(CommonUtil.USER_SESSION_MAP.containsKey(account.getSuiId()) && CommonUtil.USER_SESSION_MAP.get(account.getSuiId()) != null){
//    		HttpSession session = CommonUtil.USER_SESSION_MAP.get(account.getSuiId());
//    		session.invalidate();
//    		CommonUtil.USER_SESSION_MAP.remove(account.getSuiId());
//    	}
//		CommonUtil.USER_SESSION_MAP.put(account.getSuiId(), this.getSession());
		
	}
	
	public void SetShoppingSessionAccount(SysUserInfo account) throws HSKException {
		this.getSession().setAttribute("shoppingAccount", account); 
	}
	
	public Object getCompObj() throws HSKException{
		Object obj=null;
		SysUserInfo sysUserInfo = this.GetOneSessionAccount();
		if(sysUserInfo != null && sysUserInfo.getOrgGxId() != null){
			if(sysUserInfo.getUserType() ==2){//供应商
				MdSupplier mdSupplier = new MdSupplier();
				mdSupplier.setWzId(sysUserInfo.getOrgGxId());
				obj = this.getOne(mdSupplier);
			}else if(sysUserInfo.getUserType() ==3){//集团
				MdCompanyGroup mdCompanyGroup = new MdCompanyGroup();
				mdCompanyGroup.setRbaId(sysUserInfo.getOrgGxId());
				obj = this.getOne(mdCompanyGroup);
			}else if(sysUserInfo.getUserType() ==4){//医院
				MdDentistHospital mdDentistHospital = new MdDentistHospital();
				mdDentistHospital.setRbsId(sysUserInfo.getOrgGxId());
				obj = this.getOne(mdDentistHospital);
			}else if(sysUserInfo.getUserType() ==5){//门诊
				MdDentalClinic mdDentalClinic = new MdDentalClinic();
				mdDentalClinic.setRbbId(sysUserInfo.getOrgGxId());
				obj = this.getOne(mdDentalClinic);
			}
		}
		return obj;
	}
	/**
	 * 删除实体信息
	 * @param val
	 * @throws HSKDBException
	 */
	public void deleteObjects(Object val) throws HSKDBException {
		supperDao.deleteObject(val);
	}
	/**
	 * 新建实体信息
	 * @param val
	 * @return
	 * @throws HSKDBException
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public Integer newObject(Object val) throws Exception {
		Field[] fields = val.getClass().getDeclaredFields();
		SysUserInfo account =   this.GetOneSessionAccount();
		if(fields != null && fields.length > 0){
			for(Field field : fields){
				if(field.getName().equals("createDate")){
					Method m = val.getClass().getDeclaredMethod("setCreateDate",Date.class);
					m.invoke(val, new Date());
				}
				if(field.getName().equals("createRen")){
					Method m = val.getClass().getDeclaredMethod("setCreateRen",String.class);
					m.invoke(val, account.getUserName());
				}
				if(field.getName().equals("editDate")){
					Method m = val.getClass().getDeclaredMethod("setEditDate",Date.class);
					m.invoke(val, new Date());
				}
				if(field.getName().equals("editRen")){
					Method m = val.getClass().getDeclaredMethod("setEditRen",String.class);
					m.invoke(val, account.getUserName());
				}
			}
		}
		return supperDao.newObject(val);
	}
	/**
	 * 修改实体信息
	 * @param val
	 * @throws HSKDBException
	 */
	public void updateObject(Object val) throws Exception {
		Field[] fields = val.getClass().getDeclaredFields();
		SysUserInfo account = this.GetOneSessionAccount();
		if(fields != null && fields.length > 0){
			for(Field field : fields){
				if(field.getName().equals("setEditDate")){
					Method m = val.getClass().getDeclaredMethod("setEditDate",Date.class);
					m.invoke(val, new Date());
				}
				if(field.getName().equals("setEditRen")){
					Method m = val.getClass().getDeclaredMethod("setEditRen",String.class);
					m.invoke(val, account.getUserName());
				}
			}
		}
		supperDao.updateObject(val);
	}
 
	/**
	 * 查找实体信息
	 */
	public Object getOne(Object val){
		return supperDao.getOne(val);
	}
	/**
	 * 查询实体集合
	 * @param val
	 * @return
	 * @throws HSKDBException
	 */
	public <T> List<T> getList(T obj) throws HSKDBException{
		return  supperDao.getList(obj);
	}
	
	/**
	 * 查询实体集合
	 * @param val
	 * @return
	 * @throws HSKDBException
	 */
	public <T> List<T> getList(T obj,HqlConfig conf) throws HSKDBException{
		return  supperDao.getList(obj,conf);
	}
	
	/**
	 * 获取分页对象
	 * @param obj
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModel(Object obj) throws HSKDBException{
		return  supperDao.getPagerModel(obj);
	}

	@Override
	public SysUserInfo getApiSessionAccount() throws HSKException {
		SysUserInfo sysUserInfo = new SysUserInfo();
		try {
			String sessionId = request.getHeader("sessionId");
			String accountName = SessionUtil.getLocalAccount(sessionId);
			if (sessionId == null || accountName == null) {
				return null;
			}
			sysUserInfo.setAccount(accountName);
			sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
		} catch (Exception e) {
			sysUserInfo = null;
			e.printStackTrace();
		}
		return sysUserInfo;
	}

	@Override
	public SysSwitchEntity getApiSessionSwitch(Integer switchId) throws HSKException {
		if (switchId == null)
			return null;
		SysSwitchEntity sysSwitchEntity = new SysSwitchEntity();
		try {
			sysSwitchEntity.setSwitchId(switchId);
			sysSwitchEntity = (SysSwitchEntity) this.getOne(sysSwitchEntity);
			if (sysSwitchEntity == null)
				return null;
		} catch (Exception e) {
			sysSwitchEntity = null;
			e.printStackTrace();
		}
		return sysSwitchEntity;
	}

	@Override
	public SysSalesManEntity getApiSessionSalesMan() throws HSKException {
		SysSalesManEntity salesManEntity = null;
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			if (sysUserInfo == null)
				return null;
			String accountName = sysUserInfo.getAccount();
			salesManEntity = new SysSalesManEntity();
			salesManEntity.setSalesAccount(accountName);
			salesManEntity = sysSalesmanDao.getSalesManInfo(salesManEntity);
		} catch (Exception e){
			e.printStackTrace();
		}
		return salesManEntity;
	}
	@Override
	public boolean checkNeedSecurity() throws HSKException {
		boolean needSecurity = true;
		try {
			SysUserInfo account = this.GetOneShoppingSessionAccount();
			if (account == null)
				return true;
			Map<String, Object> map = sysUserInfoDao.getSysUserInfoOpenSecurityBySuiId(account.getSuiId());
			Object objNeed = map.get("needSecurity");
			Object objOpen = map.get("openSecurity");
			if (objOpen == null || objOpen.toString().equals("") || !Objects.equals(Integer.parseInt(objOpen.toString()), 1)) // 没有开启安全码功能
				return false;
			if (objNeed == null || objNeed.toString().equals("")) // 需要安全码
				return false;
			Integer need = Integer.parseInt(objNeed.toString());
			if (Objects.equals(need, 1))
				needSecurity = true; // 需要安全码
			else
				needSecurity = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return needSecurity;
	}
}
