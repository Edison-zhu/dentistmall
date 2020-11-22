package com.hsk.miniapi.xframe.web.sysrole.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.ISysRoleInfoApiDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysUserInfoApiDao;
import com.hsk.xframe.api.persistence.SysGxControlUser;
import com.hsk.xframe.api.persistence.SysGxRoleControl;
import com.hsk.xframe.api.persistence.SysGxRoleMenu;
import com.hsk.xframe.api.persistence.SysGxRoleUser;
import com.hsk.xframe.api.persistence.SysGxUserMenu;
import com.hsk.xframe.api.persistence.SysRoleInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.miniapi.xframe.web.sysrole.service.ISysRoleApiService;

@Service
public class SysRoleApiService extends DSTApiService implements ISysRoleApiService {
	@Autowired
	private ISysRoleInfoApiDao sysRoleInfoDao;
	@Autowired
	private ISysUserInfoApiDao sysUserInfoDao;
	
	@Override
	public List<Map<String, Object>> getSysRoleList(Integer id)
			throws HSKException {
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		try{
			List<SysRoleInfo> dataList = sysRoleInfoDao.getSysRoleInfoTreeList(id);
			if(dataList != null && dataList.size() > 0){//形成组织机构树形结构
				for(SysRoleInfo sysRoleInfo : dataList){
					Map<String, Object> treeMap = new HashMap<String,Object>();
					treeMap.put("id", sysRoleInfo.getSroleId());
					treeMap.put("name", sysRoleInfo.getMenuName());
					if(sysRoleInfo.getSysSroleId() != null)
						treeMap.put("pId", sysRoleInfo.getSysSroleId());
					if(sysRoleInfo.getSysSroleId() == null){//是根节点并且有子节点
						treeMap.put("isParent",true);
					}else
						treeMap.put("isParent",false);
					treeList.add(treeMap);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return treeList;
	}
	@Override
	public List<Map<String, Object>> getAllRoleTree() throws HSKException{
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		try{
			SysRoleInfo info = new SysRoleInfo();
			List<SysRoleInfo> dataList = this.getList(info);
			if(dataList != null && dataList.size() > 0){//形成组织机构树形结构
				for(SysRoleInfo sysRoleInfo : dataList){
					Map<String, Object> treeMap = new HashMap<String,Object>();
					treeMap.put("id", sysRoleInfo.getSroleId());
					treeMap.put("name", sysRoleInfo.getMenuName());
					if(sysRoleInfo.getSysSroleId() != null)
						treeMap.put("pId", sysRoleInfo.getSysSroleId());
					if(sysRoleInfo.getSysSroleId() == null){//是根节点并且有子节点
						treeMap.put("isParent",true);
					}else
						treeMap.put("isParent",false);
					treeList.add(treeMap);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return treeList;
	}
	
	@Override
	public SysRetrunMessage saveSysRole(SysRoleInfo sysRoleInfo)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			if(sysRoleInfo.getSroleId() != null)
				this.updateObject(sysRoleInfo);
			else{
				this.newObject(sysRoleInfo);
				if(sysRoleInfo.getSysSroleId() != null){
					SysGxRoleMenu sysGxRoleMenu = new SysGxRoleMenu();
					sysGxRoleMenu.setSroleId(sysRoleInfo.getSysSroleId());
					List<SysGxRoleMenu> sysGxRoleMenuList = this.getList(sysGxRoleMenu);
					if(sysGxRoleMenuList != null && sysGxRoleMenuList.size() > 0){
						for(SysGxRoleMenu obj : sysGxRoleMenuList){
							SysGxRoleMenu newObj = new SysGxRoleMenu();
							newObj.setSmenuId(obj.getSmenuId());
							newObj.setSroleId(sysRoleInfo.getSroleId());
							this.newObject(newObj);
						}
					}
					
					SysGxRoleControl sysGxRoleControl = new SysGxRoleControl();
					sysGxRoleControl.setSroleId(sysRoleInfo.getSysSroleId());
					List<SysGxRoleControl> sysGxRoleControlList = this.getList(sysGxRoleControl);
					if(sysGxRoleControlList != null && sysGxRoleControlList.size() > 0){
						for(SysGxRoleControl obj : sysGxRoleControlList){
							SysGxRoleControl newObj = new SysGxRoleControl();
							newObj.setSconId(obj.getSconId());
							newObj.setIfOperate(obj.getIfOperate());
							newObj.setPropertiesSet(obj.getPropertiesSet());
							newObj.setSroleId(sysRoleInfo.getSroleId());
							this.newObject(newObj);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage delSysRole(Integer sroleId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			//检查是否可以被删除
			SysRoleInfo checkSysRoleInfo = new SysRoleInfo();
			checkSysRoleInfo.setSysSroleId(sroleId);
			List<SysRoleInfo> checkSysRoleInfoList = this.getList(checkSysRoleInfo);
			if(checkSysRoleInfoList != null && checkSysRoleInfoList.size() > 0){
				srm.setCode(0L);
				srm.setMeg("该岗位下有子岗位不允许删除！");
				return srm;
			}
			SysGxRoleUser sysGxRoleUser = new SysGxRoleUser();
			sysGxRoleUser.setSroleId(sroleId);
			List<SysGxRoleUser> checkSysGxRoleUserList=this.getList(sysGxRoleUser);
			if(checkSysGxRoleUserList != null && checkSysGxRoleUserList.size() > 0){
				srm.setCode(0L);
				srm.setMeg("该岗位已经被分配不允许删除！");
				return srm;
			}
			SysRoleInfo sysRoleInfo = new SysRoleInfo(sroleId);
			sysRoleInfo = (SysRoleInfo) this.getOne(sysRoleInfo);
			this.deleteObjects(sysRoleInfo);
			sysRoleInfoDao.delSysRoleMenuList(sroleId, null);
			sysRoleInfoDao.delSysRoleControlList(sroleId, null);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRoleInfo findSysRole(Integer sroleId,Integer sysSroleId) throws HSKException {
		SysRoleInfo sysRoleInfo = new SysRoleInfo();
		try {
			if(sroleId != null){
				sysRoleInfo.setSroleId(sroleId);
				sysRoleInfo = (SysRoleInfo) this.getOne(sysRoleInfo);
			}else{
				sysRoleInfo.setMenuCode(CreateCodeUtil.getSysRoleNo());
				sysRoleInfo.setSysSroleId(sysSroleId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysRoleInfo;
	}
	
	public SysRetrunMessage getRoleMenuListByRoleId(Integer sroleId)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1);
		try {
			SysGxRoleMenu sysRoleMenu = new SysGxRoleMenu();
			sysRoleMenu.setSroleId(sroleId);
			List<SysGxRoleMenu> list = this.getList(sysRoleMenu);
			srm.setObj(list);
		} catch (Exception e) {
			srm.setCode(Long.valueOf(0));
			srm.setMeg(e.getMessage());
			e.printStackTrace();
		}
		return srm;
	}
	
	public SysRetrunMessage saveSysRoleMenus(Integer sroleId, String smenuIds)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1);
		try {
			SysGxRoleMenu sysRoleMenu = new SysGxRoleMenu();
			sysRoleMenu.setSroleId(sroleId);
			List<SysGxRoleMenu> oldRoleMenuList = this.getList(sysRoleMenu);
			if(smenuIds != null && !smenuIds.equals("")){//如果有选中菜单
				String[] newEmIdList = smenuIds.split(",");
				List<Integer> addList = new ArrayList<Integer>();
				List<Integer> delList = new ArrayList<Integer>();
				if(oldRoleMenuList != null && oldRoleMenuList.size() > 0){//如果之前又菜单
					//查找新增的
					for(String EmId : newEmIdList){
						boolean isHave = false;
						for(SysGxRoleMenu info : oldRoleMenuList){
							if(info.getSmenuId().toString().equals(EmId)){
								isHave = true;
								break;
							}
						}
						if(!isHave)
							addList.add(Integer.parseInt(EmId));
					}
					//查找删除的
					for(SysGxRoleMenu info : oldRoleMenuList){
						boolean isHave = false;
						for(String EmId : newEmIdList){
							if(info.getSmenuId().toString().equals(EmId)){
								isHave = true;
								break;
							}
						}
						if(!isHave)
							delList.add(info.getSmenuId());
					}
					
				}else{//如果之前没有菜单
					for(String EmId : newEmIdList)
						addList.add(Integer.parseInt(EmId));
				}
				//删除
				if(delList != null && delList.size() > 0){
					String dels = "";
					for(Integer emId : delList)
						dels += emId + ",";
					dels = dels.substring(0, dels.length()-1);
					sysRoleInfoDao.delSysRoleMenuList(sroleId,dels);
					sysUserInfoDao.delUserMenus(null, sroleId, dels, null);//删除这个用户中有的该菜单
				}
				//新增
				if(addList != null && addList.size() > 0){
					SysGxRoleUser userRole = new SysGxRoleUser();
					userRole.setSroleId(sroleId);
					//查找所有使用这个角色的用户
					List<SysGxRoleUser> userRoleList = this.getList(userRole);
					for(Integer emId : addList){
						SysGxRoleMenu model = new SysGxRoleMenu();
						model.setSroleId(sroleId);
						model.setSmenuId(emId);
						this.newObject(model);
						//循环为使用这个角色的用户增加该菜单
						if(userRoleList != null && userRoleList.size() > 0){
							for(SysGxRoleUser sysUserRole : userRoleList){
								SysGxUserMenu sysUserMenu = new SysGxUserMenu();
								sysUserMenu.setSroleId(sroleId);
								sysUserMenu.setSmenuId(emId);
								sysUserMenu.setSuiId(sysUserRole.getSuiId());
								this.newObject(sysUserMenu);
							}
						}
					}
				}
			}else{//如果没有选择菜单,则全部删除
				sysRoleInfoDao.delSysRoleMenuList(sroleId,null);
			}
			srm.setCode(1l);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage getRoleControlListByRoleId(Integer sroleId,Integer smenuId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1);
		try {
			List<Map<String,Object>> list = sysRoleInfoDao.getSysRoleControlList(sroleId, smenuId);
			srm.setObj(list);
		} catch (Exception e) {
			srm.setCode(Long.valueOf(0));
			e.printStackTrace();
		}
		return srm;
	}

	@Override
	public SysRetrunMessage saveSysRoleControls(Integer sroleId,String sconIds, String oprates) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1);
		try {
			if(sconIds != null && !sconIds.trim().equals("")){//防止全部删除
				sysRoleInfoDao.delSysRoleControlList(sroleId, sconIds);//删除角色与控件的关系
				sysUserInfoDao.delUserControls(null, sroleId, sconIds, null);//删除用户与控件的关系
				String[] sconIdArray = sconIds.split(",");
				String[] oprateArray = oprates.split(",");
				List<SysGxRoleControl> addSysGxRoleControlList = new ArrayList<SysGxRoleControl>();
				for(int i = 0; i < sconIdArray.length;i++){
					if(!oprateArray[i].equals("0")){//如果不是隐藏则保存
						SysGxRoleControl roleControl = new SysGxRoleControl();
						roleControl.setSroleId(sroleId);
						roleControl.setSconId(Integer.parseInt(sconIdArray[i]));
						roleControl.setIfOperate(Integer.parseInt(oprateArray[i]));
						this.newObject(roleControl);
						addSysGxRoleControlList.add(roleControl);
					}
				}
				//查看是否有需要添加的权限
				if(addSysGxRoleControlList != null && addSysGxRoleControlList.size() > 0){
					SysGxRoleUser userRole = new SysGxRoleUser();
					userRole.setSroleId(sroleId);
					//查找所有使用这个角色的用户
					List<SysGxRoleUser> userRoleList = this.getList(userRole);
					for(SysGxRoleControl roleControl : addSysGxRoleControlList){
						//循环为使用这个角色的用户增加该菜单
						if(userRoleList != null && userRoleList.size() > 0){
							for(SysGxRoleUser sysUserRole : userRoleList){
								SysGxControlUser sysGxControlUser = new SysGxControlUser();
								sysGxControlUser.setSroleId(sroleId);
								sysGxControlUser.setIfOperate(roleControl.getIfOperate());
								sysGxControlUser.setType(1);
								sysGxControlUser.setSconId(roleControl.getSconId());
								sysGxControlUser.setSuiId(sysUserRole.getSuiId());
								this.newObject(sysGxControlUser);
							}
						}
					}
				}
			}
			srm.setCode(1l);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}
}
