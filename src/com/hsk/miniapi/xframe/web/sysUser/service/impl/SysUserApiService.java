package com.hsk.miniapi.xframe.web.sysUser.service.impl;

import java.io.File;
import java.util.*;

import com.hsk.dentistmall.api.daobbase.IMdCompanyGroupDao;
import com.hsk.dentistmall.api.daobbase.IMdDentalClinicDao;
import com.hsk.dentistmall.api.daobbase.IMdDentistHospitalDao;
import com.hsk.dentistmall.api.persistence.MdCompanyGroup;
import com.hsk.dentistmall.api.persistence.MdDentalClinic;
import com.hsk.dentistmall.api.persistence.MdDentistHospital;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.daobbase.ISysSalesmanDao;
import com.hsk.xframe.api.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.ISysRoleInfoApiDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysUserInfoApiDao;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.CommonUtil;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.xframe.api.utils.freeMarker.ImageBase64Util;
import com.hsk.xframe.api.utils.freeMarker.RandomCodeUtils;
import com.hsk.miniapi.xframe.web.sysUser.service.ISysUserApiService;

@Service
public class SysUserApiService extends DSTApiService implements ISysUserApiService {
	@Autowired
	private ISysUserInfoApiDao sysUserInfoDao;
	@Autowired
	private ISysRoleInfoApiDao sysRoleInfoDao;
	@Autowired
	ISysSalesmanDao sysSalesmanDao;
	@Autowired
	protected IMdCompanyGroupDao mdCompanyGroupDao;
	@Autowired
	protected IMdDentistHospitalDao mdDentistHospitalDao;
	@Autowired
	protected IMdDentalClinicDao mdDentalClinicDao;
	
	public PagerModel getSysUserPager(SysUserInfo sysUserInfo)
			throws HSKException { 
		PagerModel model = new PagerModel();
		try { 
			if(sysUserInfo != null&& sysUserInfo.getOrgGxId() ==null){
				if(sysUserInfo.getOldId() == null && sysUserInfo.getOrganizaType()==null){					 
					SysUserInfo account = this.GetOneSessionAccount();
					sysUserInfo.setOrgGxId(account.getOrgGxId());
				}
			}else{
				if(sysUserInfo.getUserType()==null||"1".equals(sysUserInfo.getUserType().toString()) ) {
					sysUserInfo.setUserType(null);
					sysUserInfo.setUserRole(" userRole in (1)");
				} else {
					sysUserInfo.setUserType(null);
					sysUserInfo.setUserRole("userRole  not in  (1)");
				}
			}
			if(sysUserInfo.getState() ==null){
				sysUserInfo.setState_str("1,2");
				sysUserInfo.setState(null);
			}
			model = sysUserInfoDao.getSysUserInfoPage(sysUserInfo);
			
//			if(sysUserInfo != null && sysUserInfo.getUserType() != null && sysUserInfo.getOrgGxId() !=null){//如果拥有userType;以及公司ID
//				model = sysUserInfoDao.getSysUserInfoPage(sysUserInfo);
//			}if(sysUserInfo != null && sysUserInfo.getOldId() != null && sysUserInfo.getOrganizaType() !=null&&sysUserInfo.getUserType() != null ){
//				//通过oldId;以及公司OrganizaType 确定组织id（OrgGxId）
//				//通过
//				model = sysUserInfoDao.getSysUserInfoPage(sysUserInfo); 
//			}else {//如果没有userType;以及公司ID
//				SysUserInfo account = this.GetOneSessionAccount();
//				if(account != null && account.getSuiId() != null){
//					//sysUserInfo.setUserType(account.getUserType());
//					sysUserInfo.setOrgGxId(account.getOrgGxId());
//					model = sysUserInfoDao.getSysUserInfoPage(sysUserInfo); 
//				}
//			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public SysRetrunMessage saveSysUser(SysUserInfo sysUserInfo)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			SysUserInfo account = this.GetOneSessionAccount();

			if(sysUserInfo.getOrgGxId()==null&&sysUserInfo.getOrganizaType()!=null&&sysUserInfo.getOldId()!=null){
				SysOrgGx sog=new SysOrgGx();
				sog.setOrganizaType(sysUserInfo.getOrganizaType());
				sog.setOldId(sysUserInfo.getOldId());
				Object obj=	this.getOne(sog);
				if(obj!=null){
					sog= (SysOrgGx) obj;
					sysUserInfo.setOrgGxId(sog.getOrgGxId());
				}
			}


			if (account != null && account.getSuiId() != null) {
				if (sysUserInfo.getSuiId() != null) {
					this.updateObject(sysUserInfo);
					if (account.getUserType() != null && account.getUserType() == 6) {
						SysSalesManEntity salesManEntity = new SysSalesManEntity();
						salesManEntity.setSalesAccount(account.getAccount());
						salesManEntity = sysSalesmanDao.getSalesManInfo(salesManEntity);
						salesManEntity.setSalesPhone(sysUserInfo.getPhoneNumber());
						salesManEntity.setSalesName(sysUserInfo.getUserName());
						sysSalesmanDao.updateSysSalesmanBySalesId(salesManEntity);

						String saleName = salesManEntity.getSalesName();
						MdCompanyGroup mdCompanyGroup = new MdCompanyGroup();
						mdCompanyGroup.setSalesmanIds(salesManEntity.getSalesmanId().toString());
						List<MdCompanyGroup> listCom = mdCompanyGroupDao.getListByMdCompanyGroup(mdCompanyGroup);
						for (MdCompanyGroup item : listCom) {
							if (!item.getSalesName().equals(saleName)){
								item.setSalesName(saleName);
								mdCompanyGroupDao.updateMdCompanyGroupById(item.getRbaId(), item);
							}
						}

						MdDentistHospital mdDentistHospital = new MdDentistHospital();
						mdDentistHospital.setSalesmanIds(salesManEntity.getSalesmanId().toString());
						List<MdDentistHospital> listDen = mdDentistHospitalDao.getListByMdDentistHospital(mdDentistHospital);
						for (MdDentistHospital item : listDen) {
							if (!item.getSalesName().equals(saleName)){
								item.setSalesName(saleName);
								mdDentistHospitalDao.updateMdDentistHospitalById(item.getRbsId(), item);
							}
						}

						MdDentalClinic mdDentalClinic = new MdDentalClinic();
						mdDentalClinic.setSalesmanIds(salesManEntity.getSalesmanId().toString());
						List<MdDentalClinic> listCli = mdDentalClinicDao.getListByMdDentalClinic(mdDentalClinic);
						for (MdDentalClinic item : listCli) {
							if (!item.getSalesName().equals(saleName)){
								item.setSalesName(saleName);
								mdDentalClinicDao.updateMdDentalClinicById(item.getRbbId(), item);
							}
						}
					}
				}
				else {

					SysUserInfo pui = new SysUserInfo();
					pui.setAccount(sysUserInfo.getAccount());
					SysUserInfo obj = this.sysUserInfoDao
							.getSysUserInfoByName(pui);
					if (obj != null) {
						srm.setCode(0l);
						srm.setMeg(sysUserInfo.getAccount() + "以存在，请重新输入！！");
						return srm;
					}
					sysUserInfo.setState(1);
					sysUserInfo.setPassword("123456");
					this.newObject(sysUserInfo);
				}
				if (sysUserInfo.getUserRole() != null && !"".equals(sysUserInfo.getUserRole())) {
					String[] userRoleArray = sysUserInfo.getUserRole().split(
							",");
					String roleIds = "";
					if (sysUserInfo.getUserType() != null && sysUserInfo.getUserType() == 6){
						if (sysUserInfo.getUserRole().equals("1"))
							roleIds += CommonUtil.YWY_ROLE + ","; //业务员
						else if (sysUserInfo.getUserRole().equals("2"))
							roleIds += CommonUtil.DLS_ROLE + ","; //代理商
						else if (sysUserInfo.getUserRole().equals("3"))
							roleIds += CommonUtil.SUPPLIER_DLS_ROLE + ","; //代理商
					} else if ("0".equals(sysUserInfo.getOrganizaType())) {
						for (String str : userRoleArray) {
							if (str.equals("1"))
								roleIds += CommonUtil.GYS_ADMIN_ROLE + ",";// 供应商管理员角色
							else if (str.equals("2"))
								roleIds += CommonUtil.GYS_ROLE + ",";// 供应商普通角色
						}
					}else if ("100".equals(sysUserInfo.getOrganizaType())) {// 供应商用户
						for (String str : userRoleArray) {
							if (str.equals("1"))
								roleIds += CommonUtil.GYS_ADMIN_ROLE + ",";// 供应商管理员角色
							else if (str.equals("2"))
								roleIds += CommonUtil.GYS_ROLE + ",";// 供应商普通角色
						}
					} else {
						// 集团、医院、门诊用户
						// if(sysUserInfo.getUserType() >2){}
						for (String str : userRoleArray) {
							if (str.equals("1")) {
								if ("20001".equals(sysUserInfo
										.getOrganizaType()))
									roleIds += CommonUtil.JT_ADMIN_ROLE + ",";// 集团管理员角色
								else if ("20002".equals(sysUserInfo
										.getOrganizaType()))
									roleIds += CommonUtil.YY_ADMIN_ROLE + ",";// 医院管理员角色
								else if ("20003".equals(sysUserInfo
										.getOrganizaType()))
									roleIds += CommonUtil.MZ_ADMIN_ROLE + ",";// 门诊管理员角色
							} else if (str.equals("2"))
								roleIds += CommonUtil.CGY_ROLE + ",";// 采购员角色
							else if (str.equals("3"))
								roleIds += CommonUtil.CK_ADMIN_ROLE + ",";// 仓库管理员角色
							else if (str.equals("4"))
								roleIds += CommonUtil.LLY_ROLE + ",";// 领料员角色
						}
					}
					// 自动赋予用户角色菜单信息
					roleIds = roleIds.substring(0, roleIds.length() - 1);
					this.saveUserRole(sysUserInfo.getSuiId(), roleIds);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
			throw new  HSKException(e);
		}
		return srm;
	}

	public SysRetrunMessage updateSysUserState(Integer suiId, Integer state)
			throws HSKException {
		
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo sysUserInfo = new SysUserInfo(suiId);
			sysUserInfo = (SysUserInfo) this.getOne(sysUserInfo);
			sysUserInfo.setState(state);
			this.updateObject(sysUserInfo);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage delSysUser(Integer suiId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo sysUserInfo = new SysUserInfo(suiId);
			sysUserInfo = (SysUserInfo) this.getOne(sysUserInfo);
			sysUserInfo.setState(0);
			this.updateObject(sysUserInfo);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}
	
	@Override
	public SysUserInfo findSysUser(SysUserInfo sysUserInfo) throws HSKException {
		if(sysUserInfo.getSuiId() != null){
			sysUserInfo = new SysUserInfo(sysUserInfo.getSuiId());
			sysUserInfo = (SysUserInfo) this.getOne(sysUserInfo);
		}else{
			sysUserInfo.setUserCode(CreateCodeUtil.getSysUserNo());
		}
		return sysUserInfo;
	}

	@Override
	public SysRetrunMessage checkSysUser(SysUserInfo sysUserInfo)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo checkUser = sysUserInfoDao.getSysUserInfoByName(sysUserInfo);
			if(checkUser != null && (sysUserInfo.getSuiId() == null || !checkUser.getSuiId().equals(sysUserInfo.getSuiId())))
				srm.setCode(2l);
		}catch(Exception e){
			e.printStackTrace();
		}
		return srm;
	}
	
	@Override
	public SysRetrunMessage updateSysUserPass(Integer suiId)
			throws HSKException {
		
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo sysUserInfo = new SysUserInfo(suiId);
			sysUserInfo = (SysUserInfo) this.getOne(sysUserInfo);
			sysUserInfo.setPassword("123456");
			this.updateObject(sysUserInfo);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage updateLoginUserPass(String oldPwd, String newPwd)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo sysUserInfo = this.GetOneSessionAccount();
		if(!sysUserInfo.getPassword().equals(oldPwd)){
			srm.setCode(0l);
			srm.setMeg("当前密码不正确！");
		}else{
			sysUserInfo.setPassword(newPwd);
			try {
				this.updateObject(sysUserInfo);
			} catch (Exception e) {
				e.printStackTrace();
				srm.setCode(0l);
				srm.setMeg("操作失败！");
			}
		}
		return srm;
	}
	
	@Override
	public SysRetrunMessage getSysUserList(SysUserInfo sysUserInfo)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			List<SysUserInfo> list = this.getList(sysUserInfo);
			srm.setObj(list);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("查询失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage getUserRole(Integer suiId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysGxRoleUser sysGxRoleUser = new SysGxRoleUser();
			sysGxRoleUser.setSuiId(suiId);
			List<SysGxRoleUser> list = this.getList(sysGxRoleUser);
			srm.setObj(list);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage getUserMenu(Integer suiId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysGxUserMenu sysGxUserMenu = new SysGxUserMenu();
			sysGxUserMenu.setSuiId(suiId);
			List<SysGxUserMenu> list = this.getList(sysGxUserMenu);
			srm.setObj(list);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage getUserControl(Integer suiId, Integer smenuId)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			List<Map<String,Object>> list = sysUserInfoDao.getSysUserControlList(suiId, smenuId);
			srm.setObj(list);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage saveUserRole(Integer suiId, String sroleIds)throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			//查看该用户原来的角色
			SysGxRoleUser sysGxRoleUser = new SysGxRoleUser();
			sysGxRoleUser.setSuiId(suiId);
			List<SysGxRoleUser> oldRoleList = this.getList(sysGxRoleUser);
			String newRoleIds ="";
			String delRoleIds="";
			List<Integer> newRoleIdArray= new ArrayList<Integer>();
			if(sroleIds != null && !sroleIds.trim().equals("")){
				String[] sroleIdArray = sroleIds.split(",");
				//查看新增的岗位
				for(String sroleIdStr : sroleIdArray){
					Integer sroleId = Integer.parseInt(sroleIdStr);
					boolean isFind=false;
					if(oldRoleList != null && oldRoleList.size() > 0){
						for(SysGxRoleUser oldRole : oldRoleList){
							if(sroleId.equals(oldRole.getSroleId())){
								isFind=true;
								break;
							}
						}
					}
					if(!isFind){
						newRoleIds+=sroleId+",";
						newRoleIdArray.add(sroleId);
					}
				}
				//查看删除岗位
				if(oldRoleList != null && oldRoleList.size() > 0){
					for(SysGxRoleUser oldRole : oldRoleList){
						boolean isFind=false;
						for(String sroleIdStr : sroleIdArray){
							Integer sroleId = Integer.parseInt(sroleIdStr);
							if(sroleId.equals(oldRole.getSroleId())){
								isFind=true;
								break;
							}
						}
						if(!isFind)
							delRoleIds+=oldRole.getSroleId()+",";
					}
				}
			}else{//删除所有岗位
				if(oldRoleList != null && oldRoleList.size() > 0){
					for(SysGxRoleUser oldRole : oldRoleList)
						delRoleIds+=oldRole.getSroleId()+",";
				}
			}
			//新增用户岗位信息
			if(newRoleIds != null && !newRoleIds.trim().equals("")){
				//保存用户改为信息
				for(Integer roleId : newRoleIdArray){
					SysGxRoleUser roleUser = new SysGxRoleUser();
					roleUser.setSroleId(roleId);
					roleUser.setSuiId(suiId);
					this.newObject(roleUser);
				}
				newRoleIds =newRoleIds.substring(0, newRoleIds.length()-1);
				//保存用户菜单信息
				List<SysGxRoleMenu> sysGxRoleMenuList = sysRoleInfoDao.getSysGxRoleMenuBySroleIds(newRoleIds);
				if(sysGxRoleMenuList != null && sysGxRoleMenuList.size() >0){
					for(SysGxRoleMenu sysGxRoleMenu : sysGxRoleMenuList){
						SysGxUserMenu sysGxUserMenu = new SysGxUserMenu();
						sysGxUserMenu.setSuiId(suiId);
						sysGxUserMenu.setSroleId(sysGxRoleMenu.getSroleId());
						sysGxUserMenu.setSmenuId(sysGxRoleMenu.getSmenuId());
						sysGxUserMenu.setType(1);
						this.newObject(sysGxUserMenu);
					}
				}
				//保存用户岗位信息
				List<SysGxRoleControl> sysGxRoleControlList = sysRoleInfoDao.getSysGxRoleControlBySroleIds(newRoleIds);
				if(sysGxRoleControlList != null && sysGxRoleControlList.size() >0){
					for(SysGxRoleControl sysGxRoleControl : sysGxRoleControlList){
						SysGxControlUser sysGxControlUser = new SysGxControlUser();
						sysGxControlUser.setSuiId(suiId);
						sysGxControlUser.setSroleId(sysGxRoleControl.getSroleId());
						sysGxControlUser.setSconId(sysGxRoleControl.getSconId());
						sysGxControlUser.setIfOperate(sysGxRoleControl.getIfOperate());
						sysGxControlUser.setPropertiesSet(sysGxRoleControl.getPropertiesSet());
						sysGxControlUser.setType(1);
						this.newObject(sysGxControlUser);
					}
				}
				//保存用户物料信息
				//保存用户库区信息
			}
			//删除原有的岗位信息
			if(delRoleIds != null && !delRoleIds.trim().equals("")){
				delRoleIds =delRoleIds.substring(0, delRoleIds.length()-1);
				sysUserInfoDao.delUserRoleByUsertAndRoles(suiId, delRoleIds);
				sysUserInfoDao.delUserMenus(suiId, null, null, delRoleIds);
				sysUserInfoDao.delUserControls(suiId, null, null, delRoleIds);
				//删除用户物料信息
				//删除用户库区信息
			}	
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage saveUserMenu(Integer suiId, String smenuIds)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			//查看该用户原来的菜单
			SysGxUserMenu sysGxUserMenu = new SysGxUserMenu();
			sysGxUserMenu.setSuiId(suiId);
			List<SysGxUserMenu> oldMenuList = this.getList(sysGxUserMenu);
			String delSmenuIds="";
			List<Integer> newMenuArray= new ArrayList<Integer>();
			if(smenuIds != null && !smenuIds.trim().equals("")){
				String[] smenuIdArray = smenuIds.split(",");
				//查看新增的菜单
				for(String smenuIdStr : smenuIdArray){
					Integer smenuId = Integer.parseInt(smenuIdStr);
					boolean isFind=false;
					if(oldMenuList != null && oldMenuList.size() > 0){
						for(SysGxUserMenu oldMenu : oldMenuList){
							if(smenuId.equals(oldMenu.getSmenuId())){
								isFind=true;
								break;
							}
						}
					}
					if(!isFind)
						newMenuArray.add(smenuId);
				}
				//查看删除菜单
				if(oldMenuList != null && oldMenuList.size() > 0){
					for(SysGxUserMenu oldMenu : oldMenuList){
						boolean isFind=false;
						for(String smenuIdStr : smenuIdArray){
							Integer smenuId = Integer.parseInt(smenuIdStr);
							if(smenuId.equals(oldMenu.getSmenuId())){
								isFind=true;
								break;
							}
						}
						if(!isFind)
							delSmenuIds+=oldMenu.getSmenuId()+",";
					}
				}
			}else{//删除所有菜单
				if(oldMenuList != null && oldMenuList.size() > 0){
					for(SysGxUserMenu oldMenu : oldMenuList)
						delSmenuIds+=oldMenu.getSmenuId()+",";
				}
			}
			//新增用户菜单信息
			if(newMenuArray != null && newMenuArray.size() > 0){
				//保存用户菜单信息
				for(Integer smenuId : newMenuArray){
					SysGxUserMenu userMenu = new SysGxUserMenu();
					userMenu.setSuiId(suiId);
					userMenu.setSmenuId(smenuId);
					userMenu.setType(2);
					this.newObject(userMenu);
						
				}
			}
			//删除原有的菜单信息
			if(delSmenuIds != null && !delSmenuIds.trim().equals("")){
				delSmenuIds =delSmenuIds.substring(0, delSmenuIds.length()-1);
				sysUserInfoDao.delUserMenus(suiId, null, delSmenuIds, null);
			}	
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage saveUserControl(Integer suiId, String sconIds,String oprates) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			//查看该用户原来的控件
			List<SysGxControlUser> oldControlList = sysUserInfoDao.getSysGxControlUserListBySconIds(suiId, sconIds);
			//新增
			List<Map<String,Integer>> newControlList = new ArrayList<Map<String,Integer>>();
			//修改
			List<SysGxControlUser> editControlList = new ArrayList<SysGxControlUser>();
			//删除
			String delSconIds="";
			if(sconIds != null && !sconIds.trim().equals("")){
				String[] sconIdArray = sconIds.split(",");
				String[] oprateArray = oprates.split(",");
				//查看新增，修改的
				for(int i=0;i < sconIdArray.length;i++){
					Integer sconId = Integer.parseInt(sconIdArray[i]);
					Integer ifOperate=Integer.parseInt(oprateArray[i]);
					if(ifOperate != 0){//如果不是隐藏
						boolean isFind=false;
						if(oldControlList != null && oldControlList.size() > 0){
							for(SysGxControlUser controlUser : oldControlList){
								if(sconId.equals(controlUser.getSconId())){//如果找到
									if(controlUser.getIfOperate() != ifOperate){//如果操作不一样则为修改
										controlUser.setIfOperate(ifOperate);
										editControlList.add(controlUser);
									}
									isFind=true;
									break;
								}
							}
						}
						if(!isFind){
							Map<String,Integer> newControl = new HashMap<String,Integer>();
							newControl.put("sconId", sconId);
							newControl.put("ifOperate", ifOperate);
							newControlList.add(newControl);
						}
					}
				}
				
				//查看删除的
				if(oldControlList != null && oldControlList.size() > 0){
					for(SysGxControlUser controlUser : oldControlList){
						for(int i=0;i < sconIdArray.length;i++){
							Integer sconId = Integer.parseInt(sconIdArray[i]);
							Integer ifOperate=Integer.parseInt(oprateArray[i]);
							if(ifOperate.equals(0) && sconId.equals(controlUser.getSconId())){
								delSconIds+=sconId+",";
								break;
							}
						}
					}
				}
			}
			if(newControlList != null && newControlList.size() > 0){
				for(Map<String,Integer> map : newControlList){
					SysGxControlUser sysGxControlUser = new SysGxControlUser();
					sysGxControlUser.setIfOperate(map.get("ifOperate"));
					sysGxControlUser.setSconId(map.get("sconId"));
					sysGxControlUser.setSuiId(suiId);
					sysGxControlUser.setType(2);
					this.newObject(sysGxControlUser);
				}
			}
			
			if(editControlList != null && editControlList.size() > 0){
				for(SysGxControlUser sysGxControlUser : editControlList){
					sysGxControlUser.setType(2);
					sysGxControlUser.setSroleId(null);
					this.updateObject(sysGxControlUser);
				}
			}
			if(delSconIds != null && !delSconIds.trim().equals("")){
				delSconIds = delSconIds.substring(0, delSconIds.length()-1);
				sysUserInfoDao.delUserControls(suiId, null, delSconIds, null);
			}
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public void toSel(Integer suiId) throws HSKException {
		this.getModel().addAttribute("suiId", suiId);
	}

	@Override
	public SysRetrunMessage getSysUserMenuList() throws HSKException {
		SysUserInfo sysUserInfo = this.GetOneSessionAccount();
		SysRetrunMessage srm = new SysRetrunMessage(1);
		try {
			List<Map<Object,Object>> list = sysUserInfoDao.getSysUserMenuListBySuiId(sysUserInfo.getSuiId());
			srm.setObj(combinationMenu(list,null));
		} catch (HSKDBException e) {
			srm.setCode(Long.valueOf(0));
			srm.setMeg(e.getMessage());
			e.printStackTrace();
		}
		return srm;
	}
	
	//组合菜单
		private List<Map<String, Object>> combinationMenu(List<Map<Object,Object>> list,String pid){
			List<Map<String, Object>> reList = new ArrayList<Map<String, Object>>();
			for(Map<Object,Object> info : list){
				if(pid==null && info.get("SYS_SMENU_ID")==null){
					Map<String, Object> reMap= new HashMap<String,Object>();
					reMap.put("name", info.get("MENU_NAME"));
					reMap.put("icon", info.get("MENU_ICON"));
					reMap.put("address", info.get("MENU_ADDREE"));
					reMap.put("filePath", info.get("ROOT_PATH"));
					List<Map<String, Object>> childList = this.combinationMenu(list,info.get("SMENU_ID").toString());
					if(childList != null && childList.size() > 0)
						reMap.put("child", childList);
					reList.add(reMap);
				}else if(pid != null && info.get("SYS_SMENU_ID") != null && info.get("SYS_SMENU_ID").toString().equals(pid)){
					Map<String, Object> reMap= new HashMap<String,Object>();
					reMap.put("name", info.get("MENU_NAME"));
					reMap.put("icon", info.get("MENU_ICON"));
					reMap.put("address", info.get("MENU_ADDREE"));
					reMap.put("menuType", info.get("MENU_TYPE"));
					reMap.put("filePath", info.get("ROOT_PATH"));
					List<Map<String, Object>> childList = this.combinationMenu(list,info.get("SMENU_ID").toString());
					if(childList != null && childList.size() > 0)
						reMap.put("child", childList);
					reList.add(reMap);
				}
			}
			return reList;
		}

		 
		public SysRetrunMessage getSysUserControlList() throws HSKException {
			SysUserInfo sysUserInfo = this.GetOneSessionAccount();
			SysRetrunMessage srm = new SysRetrunMessage(1);
			try {
				List<Map<Object,Object>> list = sysUserInfoDao.getSysUserControlListBySuiId(sysUserInfo.getSuiId());
				srm.setObj(list);
			} catch (HSKDBException e) {
				srm.setCode(Long.valueOf(0));
				srm.setMeg(e.getMessage());
				e.printStackTrace();
			}
			return srm;
		}

		
		public void saveBD() throws HSKException {
//			try {
//			SysUserInfo user01 = new SysUserInfo();
//			user01.setUserCode("123456789012345");
//			 sysUserInfoDao.saveUser(user01);
			this.getJdbcTemplate().update(" INSERT INTO  sys_user_info (user_code)VALUES ( '123456789012345') ");
			System.out.println("user1 success");
//			SysUserInfo user02 = new SysUserInfo();
//			user02.setUserCode("123456789012345678901234567890123");
//			sysUserInfoDao.saveUser(user02);
			this.getJdbcTemplate().update(" INSERT INTO  sys_user_info (user_code)VALUES ( '123456789012345678901234567890123') ");
			System.out.println("user2 success");
			
//			} catch (HSKDBException e) {
//				new HSKException(e);
//				e.printStackTrace();
//			}
		}

		@Override
		public SysRetrunMessage saveWebSysUser(SysUserInfo sysUserInfo)
				throws HSKException {
			SysRetrunMessage srm = new SysRetrunMessage(1l);
			try {
				this.updateObject(sysUserInfo);
				this.SetShoppingSessionAccount(sysUserInfo);
			} catch (Exception e) {
				srm.setCode(0l);
				srm.setMeg("操作失败!");
				e.printStackTrace();
			}
			
			return srm;
		}
		private final String dirName = "upFile";
		@Override
		public SysRetrunMessage updateHeard(String imgSrc) throws HSKException {
			SysRetrunMessage srm = new SysRetrunMessage(1);
			try{
				SysUserInfo ma = this.GetOneSessionAccount();
				String savePath = SystemContext.updown_File_path + "/";
				String fileName="header"+System.currentTimeMillis();
				String rootUrl = request.getContextPath() + "/fileInfo/updown/";
				// 检查目录
				File uploadDir = new File(savePath);
				if (!uploadDir.isDirectory()) {
					srm.setCode(0l);
					srm.setMeg("上传目录不存在。");
					return srm;
				}
				// 检查目录写权限
				if (!uploadDir.canWrite()) {
					srm.setCode(0l);
					srm.setMeg("上传目录没有写权限。");
					return srm;
				}
				String UserCode = (ma == null) ? null : ma.getAccount();
				// 创建文件夹
				UserCode = UserCode == null ? "admin" : UserCode;
				savePath += this.dirName + "/" + UserCode + "/";
				rootUrl += this.dirName + "/" + UserCode + "/";
				File saveDirFile = new File(savePath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				String fileDesk=ImageBase64Util.GenerateImage(imgSrc,fileName,savePath);
				SysFileInfo sysFileInfo=new SysFileInfo();
				sysFileInfo.setFileName(fileName+".png");
				sysFileInfo.setFilePath(fileDesk);
				sysFileInfo.setFileType(".png");
				sysFileInfo.setRootPath(rootUrl+fileName+".png");
				sysFileInfo.setFileState("1");
				String fileCode = RandomCodeUtils.getRandomString(32);
				sysFileInfo.setFileCode(fileCode);
				this.newObject(sysFileInfo);
				ma.setBarCode(fileCode);
				ma.setBarCodePath(rootUrl+fileName+".png");
				this.updateObject(ma);
				this.SetSessionAccount(ma);
			}catch(Exception e){
				e.printStackTrace();
			}
			return srm;
		}

		@Override
		public SysRetrunMessage showHeard() throws HSKException {
			SysRetrunMessage srm = new SysRetrunMessage(1L);
			String heardPath ="";
			if(this.GetOneSessionAccount().getBarCodePath() != null)
				heardPath = this.GetOneSessionAccount().getBarCodePath().toString();
			srm.setObj(heardPath);
			return srm;
		}

	@Override
	public SysRetrunMessage updateSecurityCode(String oldSecurity, String newSecurity) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			SysUserInfo account = this.getApiSessionAccount();
			if (account == null) {
				sm.setCode(2L);
				sm.setMeg("未登录");
				return sm;
			}
			Map<String, Object> openSecurity = sysUserInfoDao.getSysUserInfoOpenSecurityBySuiId(account.getSuiId());
			if (openSecurity == null || openSecurity.isEmpty() || openSecurity.get("openSecurity") == null || openSecurity.get("openSecurity").toString().equals("0")) {
				sm.setCode(3L);
				sm.setMeg("您的安全码功能未开启，可联系机构管理员在后台开启");
				return sm;
			} else {
				account.setOpenSecurity(1);
			}
			if (openSecurity.get("securityCode") != null && !openSecurity.get("securityCode").toString().equals(oldSecurity)) {
				sm.setCode(4L);
				sm.setMeg("旧安全码有错误");
				return sm;
			}
			if (oldSecurity.equals(newSecurity)) {
				sm.setCode(5L);
				sm.setMeg("旧安全码与新安全码不能一样");
				return sm;
			}
			account.setSecurityCode(newSecurity);
			this.updateObject(account);

		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getOpenSecurity() throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			SysUserInfo account = this.GetOneShoppingSessionAccount();
			if (account == null) {
				sm.setCode(3L);
				sm.setMeg("未登录");
				return sm;
			}
			boolean need = this.checkNeedSecurity();
			if (!need) {
				sm.setCode(2L); // 未开启
			}
			Map<String, Object> map = sysUserInfoDao.getSysUserInfoOpenSecurityBySuiId(account.getSuiId());
			Object objNeed = map.get("needSecurity");
			Object objOpen = map.get("openSecurity");
			if (objOpen == null || objOpen.toString().equals("") || !Objects.equals(Integer.parseInt(objOpen.toString()), 1)) // 没有开启安全码功能
				sm.setObj(0);
			else
				sm.setObj(1);
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage updateOpenSecurityState(String securityCode) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			SysUserInfo account = this.getApiSessionAccount();
			if (account == null) {
				sm.setCode(2L);
				sm.setMeg("未登录");
				return sm;
			}
			Map<String, Object> openSecurity = sysUserInfoDao.getSysUserInfoOpenSecurityBySuiId(account.getSuiId());
			if (openSecurity == null || openSecurity.isEmpty() || openSecurity.get("openSecurity") == null || openSecurity.get("openSecurity").toString().equals("0")) {
				sm.setCode(3L);
				sm.setMeg("未开启安全码");
				return sm;
			}
			if (openSecurity.get("securityCode") == null) {
				sm.setCode(3L);
				sm.setMeg("未开启安全码");
				return sm;
			}
			if (!openSecurity.get("securityCode").toString().equals(securityCode)) {
				sm.setCode(4L);
				sm.setMeg("安全码验证失败");
				return sm;
			}
			account.setNeedSecurity(0);
			this.updateObject(account);
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}
}
