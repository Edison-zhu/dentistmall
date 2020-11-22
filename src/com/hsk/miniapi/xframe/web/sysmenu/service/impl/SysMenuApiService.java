package com.hsk.miniapi.xframe.web.sysmenu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.ISysMenuInfoApiDao;
import com.hsk.xframe.api.persistence.SysControlInfo;
import com.hsk.xframe.api.persistence.SysMenuInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.miniapi.xframe.web.sysmenu.service.ISysMenuApiService;

/**
 * 
 * @author 张曙
 *
 */
@Service
public class SysMenuApiService extends DSTApiService implements ISysMenuApiService {
	@Autowired
	private ISysMenuInfoApiDao sysMenuInfoDao;
	
	@Override
	public List<Map<String, Object>> getSysMenuList(Integer id)throws HSKException {
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		try {
			menuList = sysMenuInfoDao.getSysMenuInfoMapListByParentId(id);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		if(menuList != null && menuList.size() > 0){//形成组织机构树形结构
			for(Map<String,Object> map : menuList){
				Map<String, Object> treeMap = new HashMap<String,Object>();
				treeMap.put("id", map.get("smenu_id"));
				treeMap.put("name", map.get("menu_name"));
				if(map.get("sys_smenu_id") != null)
					treeMap.put("pId", map.get("sys_smenu_id"));
				if(!map.get("shu").toString().equals("0")){//是根节点并且有子节点
					treeMap.put("isParent",true);
				}else
					treeMap.put("isParent",false);
				treeList.add(treeMap);
			}
		}
		return treeList;
	}

	@Override
	public SysRetrunMessage saveSysMenu(SysMenuInfo sysMenuInfo)
			throws HSKException {
		SysRetrunMessage mess = new SysRetrunMessage(1l);
		try{
			if(sysMenuInfo.getSmenuId() != null)
				this.updateObject(sysMenuInfo);
			else
				this.newObject(sysMenuInfo);
		}catch(Exception e){
			e.printStackTrace();
			mess.setCode(0L);
			mess.setMeg("操作失败！");
		}
		return mess;
	}

	@Override
	public SysRetrunMessage delSysMenu(Integer smenuId) throws HSKException {
		SysRetrunMessage mess = new SysRetrunMessage(1l);
		try{
			SysMenuInfo sysMenuInfo = new SysMenuInfo();
			sysMenuInfo.setSysSmenuId(smenuId);
			List<SysMenuInfo> childList = this.getList(sysMenuInfo);
			if(childList != null && childList.size() > 0){
				mess.setCode(0L);
				mess.setMeg("该菜单下有子菜单不允许删除！");
			}else{
				sysMenuInfo = new SysMenuInfo(smenuId);
				sysMenuInfo = (SysMenuInfo) this.getOne(sysMenuInfo);
				if(sysMenuInfo.getSysSmenuId() == null){
					mess.setCode(0L);
					mess.setMeg("系统节点不允许删除！");
				}else
					this.deleteObjects(sysMenuInfo);
			}
		}catch(Exception e){
			e.printStackTrace();
			mess.setCode(0L);
			mess.setMeg("删除失败！");
		}
		return mess;
	}

	@Override
	public SysRetrunMessage findSysMenuInfo(Integer smenuId, Integer sysSmenuId)
			throws HSKException {
		SysRetrunMessage mess = new SysRetrunMessage(1l);
		SysMenuInfo sysMenuInfo = new SysMenuInfo();
		SysMenuInfo parentMenuInfo = new SysMenuInfo();
		try{
			if(smenuId != null){
				sysMenuInfo.setSmenuId(smenuId);
				sysMenuInfo = (SysMenuInfo) this.getOne(sysMenuInfo);
				if(sysMenuInfo.getSysSmenuId() != null){
					parentMenuInfo.setSmenuId(sysMenuInfo.getSysSmenuId());
					parentMenuInfo = (SysMenuInfo) this.getOne(parentMenuInfo);
				}
			}
			else{
				sysMenuInfo.setSysSmenuId(sysSmenuId);
				sysMenuInfo.setMenuCode(CreateCodeUtil.getSysMenuNo());
				parentMenuInfo.setSmenuId(sysSmenuId);
				parentMenuInfo = (SysMenuInfo) this.getOne(parentMenuInfo);
				int order = sysMenuInfoDao.getMaxOrderByParentId(sysMenuInfo.getSysSmenuId());
				sysMenuInfo.setMenuOrderCode(order+1);
			}
			
			Map<String,Object> reMap = new HashMap<String,Object>();
			reMap.put("info", sysMenuInfo);
			reMap.put("pinfo", parentMenuInfo);
			mess.setObj(reMap);
		}catch(Exception e){
			e.printStackTrace();
			mess.setCode(0L);
			mess.setMeg("操作失败!");
		}
		return mess;
	}

	@Override
	public PagerModel getSysControlInfoPager(SysControlInfo sysControlInfo)
			throws HSKException {
		PagerModel model = new PagerModel();
		try {
			model = this.getPagerModel(sysControlInfo);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public SysRetrunMessage saveSysControlInfo(SysControlInfo sysControlInfo)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			if(sysControlInfo.getSconId() != null)
				this.updateObject(sysControlInfo);
			else
				this.newObject(sysControlInfo);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysControlInfo findSysControlInfo(Integer sconId, Integer smenuId)
			throws HSKException {
		SysControlInfo sysControlInfo = new SysControlInfo();
		try{
			if(sconId != null){
				sysControlInfo.setSconId(sconId);
				sysControlInfo = (SysControlInfo) this.getOne(sysControlInfo);
			}else{
				sysControlInfo.setSmenuId(smenuId);
				sysControlInfo.setSconCode(CreateCodeUtil.getSysControlInfoNo());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sysControlInfo;
	}

	@Override
	public SysRetrunMessage delSysControlInfo(Integer sconId)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysControlInfo sysControlInfo = new SysControlInfo();
			sysControlInfo.setSconId(sconId);
			sysControlInfo = (SysControlInfo) this.getOne(sysControlInfo);
			this.deleteObjects(sysControlInfo);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0L);
			srm.setMeg("删除失败！");
		}
		return srm;
	}

	@Override
	public List<Map<String, Object>> getAllSysMenuList() throws HSKException {
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		try {
			menuList = sysMenuInfoDao.getSysMenuInfoMapList();
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		if(menuList != null && menuList.size() > 0){//形成组织机构树形结构
			for(Map<String,Object> map : menuList){
				Map<String, Object> treeMap = new HashMap<String,Object>();
				treeMap.put("id", map.get("smenu_id"));
				treeMap.put("name", map.get("menu_name"));
				if(map.get("sys_smenu_id") != null)
					treeMap.put("pId", map.get("sys_smenu_id"));
				if(!map.get("shu").toString().equals("0")){//是根节点并且有子节点
					treeMap.put("isParent",true);
				}else
					treeMap.put("isParent",false);
				treeList.add(treeMap);
			}
		}
		return treeList;
	}

}
