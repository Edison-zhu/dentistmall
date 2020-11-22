package com.hsk.xframe.web.webSite.service.imp;

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
import com.hsk.xframe.api.daobbase.ISysWebsiteColumnsDao;
import com.hsk.xframe.api.daobbase.ISysWebsiteCommentDao;
import com.hsk.xframe.api.persistence.SysWebsiteColumns;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.xframe.web.webSite.service.ISysWebsiteColumnsService;

@Service
public class SysWebsiteColumnsService extends DSTService implements ISysWebsiteColumnsService {
	
	@Autowired
	private ISysWebsiteColumnsDao sysWebsiteColumnsDao;
	@Autowired
	private ISysWebsiteCommentDao sysWebsiteCommentDao;

	@Override
	public SysWebsiteColumns GetOneSysWebsiteColumns(
			SysWebsiteColumns sysWebsiteColumns) throws HSKException {
		try {
			sysWebsiteColumns = sysWebsiteColumnsDao.findOneSysWebsiteColumns(sysWebsiteColumns);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return sysWebsiteColumns;
	}

	@Override
	public SysRetrunMessage deleteSysWebsiteColumns(SysWebsiteColumns sysWebsiteColumns) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			boolean has = sysWebsiteColumnsDao.isHasChildrenItem(sysWebsiteColumns);
			if (has) {
				srm.setCode(0l);
				srm.setMeg("此栏目含有子栏目,请先删除子栏目!");
				return srm;
			}
			boolean hasContent = sysWebsiteColumnsDao.isHasContent(sysWebsiteColumns);
			if (hasContent) {
				srm.setCode(0l);
				srm.setMeg("此栏目含有内容,请先删除内容信息!");
				return srm;
			}

			sysWebsiteColumnsDao.DeleteSysWebsiteColumns(sysWebsiteColumns);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage saveSysWebsiteColumns(
			SysWebsiteColumns sysWebsiteColumns) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			if (sysWebsiteColumns.getSwcId() == null) {
				String ss = CreateCodeUtil.getNo("WC");
				sysWebsiteColumns.setColumnscode(ss);
				sysWebsiteColumnsDao.NewSysWebSiteColumns(sysWebsiteColumns);
			} else {
				sysWebsiteColumnsDao.UpdateMSysWebsiteColumns(sysWebsiteColumns);
			}
		} catch (HSKDBException e) {
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public PagerModel GetPMSysWebsiteColumns(SysWebsiteColumns sysWebsiteColumns)
			throws HSKException {
		PagerModel pm = new PagerModel();
		try {
			pm = sysWebsiteColumnsDao.findPagerModelSysWebsiteColumns(sysWebsiteColumns);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return pm;
	}
	
	public List<Map<String, Object>> getSiteColumnsTree(Integer id, Integer mwiId, String type) throws HSKException {
		List<Map<Object, Object>> sysSiteColumnsList = new ArrayList<Map<Object, Object>>();
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		try {
			sysSiteColumnsList = sysWebsiteColumnsDao.findSysWebsiteColumnsList(id, mwiId);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		if (sysSiteColumnsList != null && sysSiteColumnsList.size() > 0) {// 形成组织机构树形结构
			for (Map<Object, Object> map : sysSiteColumnsList) {
				Map<String, Object> treeMap = new HashMap<String, Object>();
				treeMap.put("id", map.get("swc_id"));
				treeMap.put("name", map.get("column_name"));
				treeMap.put("pId", id);
				treeMap.put("mszType", map.get("msz_type"));
				treeMap.put("isParent", true);
				if (!map.get("shu").toString().equals("0")) {// 是根节点并且有子节点
					treeMap.put("isParent", true);
				} else
					treeMap.put("isParent", false);

				treeList.add(treeMap);
			}
		}

		return treeList;
	}
	
	@Override
	public SysWebsiteColumns addSysWebsiteColumns(SysWebsiteColumns sysWebsiteColumns) throws HSKException {
		if(sysWebsiteColumns == null || sysWebsiteColumns.getSwcId() == null){
			//设置序列号
			try {
				String order = (sysWebsiteColumnsDao.getMaxOrderByParentId(sysWebsiteColumns.getSysSwcId())+1)+"";
				sysWebsiteColumns.setSerialNumber(order);
			} catch (HSKDBException e) {
				e.printStackTrace();
			}
		}
		return sysWebsiteColumns;
	}

	@Override
	public SysWebsiteColumns getSysWebsiteColumnsList(
			SysWebsiteColumns sysWebsiteColumns) throws HSKException {
		return sysWebsiteColumns;
	}

	@Override
	public List getSysWebsiteColumnsTree(Integer id)
			throws HSKException {
		String columnId ="";
		if(id!= null && id > 0)
			columnId = id+"";
		else
			columnId=null;
		List result = null;
		try {
			result = sysWebsiteColumnsDao.findSysWebsiteColumnsTree(null, columnId);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<SysWebsiteColumns> getSysWebsiteColumnsListBySysSwcId(
			Integer sysSwcId) throws HSKException {
		List<SysWebsiteColumns> reList = new ArrayList<SysWebsiteColumns>();
		try {
			reList = sysWebsiteColumnsDao.findMxxzSiteColumnsByParentId(sysSwcId, null);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}		
		return reList;
	}

}
