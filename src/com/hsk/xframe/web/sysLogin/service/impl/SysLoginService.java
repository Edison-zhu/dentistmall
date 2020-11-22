package com.hsk.xframe.web.sysLogin.service.impl;

import com.hsk.xframe.api.daobbase.ISysUserLoginLogDao;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.api.daobbase.IMdCompanyGroupDao;
import com.hsk.dentistmall.api.daobbase.IMdDentalClinicDao;
import com.hsk.dentistmall.api.daobbase.IMdDentistHospitalDao;
import com.hsk.dentistmall.api.daobbase.IMdSupplierDao;
import com.hsk.dentistmall.api.persistence.MdCompanyGroup;
import com.hsk.dentistmall.api.persistence.MdDentalClinic;
import com.hsk.dentistmall.api.persistence.MdDentistHospital;
import com.hsk.dentistmall.api.persistence.MdSupplier;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.ISysFileInfoDao;
import com.hsk.xframe.api.daobbase.ISysUserInfoDao;
import com.hsk.xframe.api.persistence.SysFieldInfo;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.web.sysLogin.service.ISysLoginService;

/**
 * 
 * @author 张曙
 *
 */
@Service
public class SysLoginService extends DSTService implements ISysLoginService {
	
	@Autowired
	private ISysUserInfoDao sysUserInfoDao;
	@Autowired
	protected IMdCompanyGroupDao mdCompanyGroupDao;
	@Autowired
	protected IMdDentistHospitalDao mdDentistHospitalDao;
	@Autowired
	protected IMdDentalClinicDao mdDentalClinicDao;
	@Autowired
	protected IMdSupplierDao mdSupplierDao;
	@Autowired
	ISysUserLoginLogDao sysUserLoginLogDao;
	
	//测试Ip存入数据库
	@Autowired
	protected ISysFileInfoDao sysFileInfoDao;
	@Override
	public SysRetrunMessage sysLogin(String userName, String password)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try { 
			SysUserInfo sysUserInfo = sysUserInfoDao.userLogin(userName);
			if(sysUserInfo == null || !sysUserInfo.getState().equals(1)){
				srm.setCode(0l);
				srm.setMeg("该用户不存在!");
				return srm;
			}
			if(!password.equals(sysUserInfo.getPassword())){
				srm.setCode(0l);
				srm.setMeg("密码错误!");
				return srm;
			}
			if(sysUserInfo.getOrganizaType().equals("100")){
				MdSupplier mdSupplier = mdSupplierDao.findMdSupplierById(sysUserInfo.getOldId());
				if(!mdSupplier.getState().equals("1")){
					srm.setCode(0l);
					srm.setMeg("该用户不存在!");
					return srm;
				}
			}else if(sysUserInfo.getOrganizaType().equals("20001")){
				MdCompanyGroup mdCompanyGroup = mdCompanyGroupDao.findMdCompanyGroupById(sysUserInfo.getOldId());
				if(mdCompanyGroup==null || !mdCompanyGroup.getState().equals(1)){
						srm.setCode(0l);
						srm.setMeg("该用户不存在!");
						return srm;
					}
			}else if(sysUserInfo.getOrganizaType().equals("20002")){
					MdDentistHospital mdDentistHospital = mdDentistHospitalDao.findMdDentistHospitalById(sysUserInfo.getOldId());
					if(mdDentistHospital==null || !mdDentistHospital.getState().equals(1)){
						srm.setCode(0l);
						srm.setMeg("该用户不存在!");
						return srm;
					}
			}else if(sysUserInfo.getOrganizaType().equals("20003")){
					MdDentalClinic mdDentalClinic = mdDentalClinicDao.findMdDentalClinicById(sysUserInfo.getOldId());
					if(mdDentalClinic==null ||  !mdDentalClinic.getState().equals(1)){
						srm.setCode(0l);
						srm.setMeg("该用户不存在!");
						return srm;
					}
			}
			
			if(sysUserInfo.getOrganizaType().equals("2002") || sysUserInfo.getOrganizaType().equals("2003") || sysUserInfo.getOrganizaType().equals("2004")){
				if(sysUserInfo.getUserRole().contains("2"))
					this.SetShoppingSessionAccount(sysUserInfo);
				else
					this.SetShoppingSessionAccount(null);
			}else
				this.SetShoppingSessionAccount(null);
			this.SetSessionAccount(sysUserInfo);
			srm.setMeg("登录成功!");
			
			/**
			 *获取用户ip 并保存
			 */
			//start
				String ip = request.getHeader("x-forwarded-for");
				if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				}
				if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				}
				if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				}
			//FileWriter writer;
	       /* try {
	            writer = new FileWriter("D:\\tomcat\\apache-tomcat-7.0.63\\ip.txt",true);
	            Date date=new Date();
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            writer.write("登录时间"+sdf.format(date)+"\t"+"用户ip地址:"+ip+"\r\n");
	            writer.flush();
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            }*/
            sysUserLoginLogDao.addSysUserLoginLogAnotherTrans(sysUserInfo.getSuiId(), 2,ip);
        } catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("查询失败!");
		}
		return srm;
	}
	
	@Override
	public SysRetrunMessage shoppingLogin(String userName, String password)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try { 
			SysUserInfo sysUserInfo = sysUserInfoDao.userLogin(userName);
			
			if(sysUserInfo == null || !sysUserInfo.getState().equals(1)){
				srm.setCode(0l);
				srm.setMeg("该用户不存在!");
				return srm;
			}
			if(!sysUserInfo.getOrganizaType().equals("2002") && sysUserInfo.getOrganizaType().equals("2003") && sysUserInfo.getOrganizaType().equals("2004")){
				srm.setCode(0l);
				srm.setMeg("该用户不存在!");
				return srm;
			}
			if(!sysUserInfo.getUserRole().contains("2")){
				srm.setCode(0l);
				srm.setMeg("该用户不存在!");
				return srm;
			}
			if(!password.equals(sysUserInfo.getPassword())){
				srm.setCode(0l);
				srm.setMeg("密码错误!");
				return srm;
			}
			if(sysUserInfo.getOrganizaType().equals("20001")){
				MdCompanyGroup mdCompanyGroup = mdCompanyGroupDao.findMdCompanyGroupById(sysUserInfo.getOldId());
				if(mdCompanyGroup==null || !mdCompanyGroup.getState().equals(1)){
						srm.setCode(0l);
						srm.setMeg("该用户不存在!");
						return srm;
					}
			}else if(sysUserInfo.getOrganizaType().equals("20002")){
					MdDentistHospital mdDentistHospital = mdDentistHospitalDao.findMdDentistHospitalById(sysUserInfo.getOldId());
					if(mdDentistHospital==null || !mdDentistHospital.getState().equals(1)){
						srm.setCode(0l);
						srm.setMeg("该用户不存在!");
						return srm;
					}
			}else if(sysUserInfo.getOrganizaType().equals("20003")){
					MdDentalClinic mdDentalClinic = mdDentalClinicDao.findMdDentalClinicById(sysUserInfo.getOldId());
					if(mdDentalClinic==null ||  !mdDentalClinic.getState().equals(1)){
						srm.setCode(0l);
						srm.setMeg("该用户不存在!");
						return srm;
					}
			}
			sysUserInfo.setNeedSecurity(1);
			sysUserInfoDao.updateUserNeedSecurity(sysUserInfo.getNeedSecurity(), sysUserInfo.getSuiId());
			this.SetSessionAccount(sysUserInfo);
			this.SetShoppingSessionAccount(sysUserInfo);
			srm.setMeg("登录成功!");
			/**
			 *获取用户ip 并保存
			 */
			//start
				String ip = request.getHeader("x-forwarded-for");
				if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				}
				if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				}
				if (ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				}
            sysUserLoginLogDao.addSysUserLoginLogAnotherTrans(sysUserInfo.getSuiId(), 1, ip);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("查询失败!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return srm;
	}
	
	@Override
	public SysRetrunMessage ReceptionCancellation()
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1);
		SysUserInfo account = this.GetOneSessionAccount();
		this.SetSessionAccount(null);
		this.SetShoppingSessionAccount(null);
//		if(CommonUtil.USER_SESSION_MAP.containsKey(account.getSuiId()) && CommonUtil.USER_SESSION_MAP.get(account.getSuiId()) != null){
//    		HttpSession session = CommonUtil.USER_SESSION_MAP.get(account.getSuiId());
//    		session.invalidate();
//    		CommonUtil.USER_SESSION_MAP.remove(account.getSuiId());
//    	}
		return srm;
	}

	@Override
	public void addSysUserLoginShoppingLog(Integer suiId,String ip) throws HSKException {
		try {
			sysUserLoginLogDao.addSysUserLoginLog(suiId, 1,ip);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addSysUserLoginSysLog(Integer suiId,String ip) throws HSKException {
		try {
			sysUserLoginLogDao.addSysUserLoginLog(suiId, 2,ip);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
	}
}
