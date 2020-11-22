package com.hsk.miniapi.xframe.web.sysOrg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.dto.model.TreeNode;
import com.hsk.xframe.api.persistence.SysCompany;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.miniapi.xframe.web.sysOrg.service.ISysOrgApiService;

@Service
public class SysOrgApiService extends DSTApiService implements ISysOrgApiService {

	@Override
	public List<Map<String, Object>> getOrgTree(Integer id) throws HSKException {
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		SysOrgGx sysOrgGx = new SysOrgGx();
		try {
			sysOrgGx.setMxxOrgGxId(id);
			List<SysOrgGx> list = this.getList(sysOrgGx);
			if(list != null && list.size() > 0){
				for(SysOrgGx org : list){
					Map<String, Object> treeMap = new HashMap<String,Object>();
					treeMap.put("id", org.getOrgGxId());
					treeMap.put("name", org.getNodeName());
					treeMap.put("pId", org.getMxxOrgGxId());
					if(org.getOrganizaType().toString().equals("1")){//是根节点并且有子节点
						treeMap.put("isParent",true);
					}else
						treeMap.put("isParent",false);
					treeList.add(treeMap);
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return treeList;
	}

	@Override
	public PagerModel getSysCompanyPager(SysCompany sysCompany)throws HSKException {
		PagerModel model = new PagerModel();
		try {
			model = this.getPagerModel(sysCompany);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public SysRetrunMessage saveSysCompany(SysCompany sysCompany)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			if(sysCompany.getWzId() != null)
				this.updateObject(sysCompany);
			else{
				sysCompany.setState("1");
				this.newObject(sysCompany);
			}
			srm.setMeg("操作成功!");
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysCompany findSysCompany(Integer wzId,Integer orgGxId) throws HSKException {
		SysCompany sysCompany = new SysCompany();
		try{
			if(wzId != null){
				sysCompany.setWzId(wzId);
				sysCompany = (SysCompany) this.getOne(sysCompany);
			}else{
				sysCompany.setOrgGxId(orgGxId);
				sysCompany.setEducation(CreateCodeUtil.getSysCompanyNo());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sysCompany;
	}

	@Override
	public SysRetrunMessage updateSysCompanyState(Integer wzId, String state)throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysCompany sysCompany = new SysCompany(wzId);
			sysCompany = (SysCompany) this.getOne(sysCompany);
			sysCompany.setState(state);
			this.updateObject(sysCompany);
		}catch(Exception e){
			e.printStackTrace();
		}
		return srm;
	}
	
	public SysRetrunMessage findSysOrgGx(Integer oldId, String organizaType)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysOrgGx sysOrgGx = new SysOrgGx();
			sysOrgGx.setOldId(oldId);
			sysOrgGx.setOrganizaType(organizaType);
			sysOrgGx = (SysOrgGx) this.getOne(sysOrgGx);
			srm.setObj(sysOrgGx);
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
		}
		return srm;
	}

 
	public List<TreeNode> getUserOrgTree(Integer id) throws HSKException {
		List<TreeNode> list_bakc = new ArrayList<TreeNode>();
		if (id == null) {
			SysUserInfo sui = GetOneSessionAccount();
			if (sui == null || sui.getOrgGxId() == null
					|| sui.getOrgGxId() == 1) {
				id = 1;
			} else {
				id = sui.getOrgGxId();
			}
			TreeNode root = new TreeNode();

			SysOrgGx sysOrgGxroot = new SysOrgGx();
			sysOrgGxroot.setOrgGxId(id);
			Object sobj = this.getOne(sysOrgGxroot);
			root.setId(id.toString());
			if (sobj != null) {
				sysOrgGxroot = (SysOrgGx) sobj;
				root.setName(sysOrgGxroot.getNodeName());
				root.setOpen(true);
				root.setIsParent("true");
			} else {
				root.setName("管理平台");
				root.setOpen(true);
				root.setIsParent("true");
			}
			root.setAttributes(sysOrgGxroot);
			list_bakc.add(root);
		}
		SysOrgGx sysOrgGx = new SysOrgGx();
		try {
			sysOrgGx.setMxxOrgGxId(id);
			List<SysOrgGx> list = this.getList(sysOrgGx);
			if (list != null && list.size() > 0) {
				for (SysOrgGx org : list) {
					TreeNode row = new TreeNode();
					row.setId(org.getOrgGxId().toString());
					row.setName(org.getNodeName());
					row.setpId(org.getMxxOrgGxId().toString());
					row.setAttributes(org);
					Integer cot = org.getMxxOrgcount();
					if (cot != null && cot > 0) {
						row.setIsParent("true");
					} else {
						row.setIsParent("false");
					}
					list_bakc.add(row);
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return list_bakc;
	}
	

}
