package com.hsk.xframe.web.sysInfo.service.impl; 
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.ISysSystemInfoDao;
import com.hsk.xframe.api.dto.model.TreeNode;
import com.hsk.xframe.api.persistence.SysSystemInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.AutoCodeUtils;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.xframe.web.sysInfo.service.ISystemInfoService;
import com.hsk.xframe.web.sysModelObject.service.IModelObjectService;

@Service
public class SystemInfoService extends DSTService implements ISystemInfoService {
	@Autowired
	private ISysSystemInfoDao sysSystemInfoDao;

	@Autowired
	private IModelObjectService modelObjectService;

	public PagerModel getSystemInfoPager(SysSystemInfo sysSystemInfo)
			throws HSKException {
		PagerModel model = new PagerModel();
		try {
			model = sysSystemInfoDao.getSysSystemInfoPage(sysSystemInfo);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return model;
	}
	
// @Transactional(rollbackFor=HSKException.class)
	public SysRetrunMessage saveSystemInfo(SysSystemInfo sysSystemInfo)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			if (sysSystemInfo.getSsiId() != null) {
				SysSystemInfo old_sysi = sysSystemInfoDao
						.findSysSystemInfoById(sysSystemInfo.getSsiId());
				if (old_sysi.getProjectName() != sysSystemInfo.getProjectName()) {
					delPath(old_sysi.getSavePath());
					newPath(sysSystemInfo);
					modelObjectService.updateRootModel(
							sysSystemInfo.getSysCode(),
							sysSystemInfo.getProjectName());
				}
				sysSystemInfoDao.updateSysSystemInfo(sysSystemInfo);
			} else {
				sysSystemInfo.setCreateTime(new Date());
				// 缺少创建人
				// sysSystemInfo.setCreateRen();			
				// 系统文件夹创建
				newPath(sysSystemInfo);
				sysSystemInfoDao.newSysSystemInfo(sysSystemInfo);
				// 系统结构创建
				modelObjectService.initRootModel(sysSystemInfo.getSysCode(),
						sysSystemInfo.getProjectName());

			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败！");
			throw new HSKException(e);
		}
		return srm;
	}

	public void newPath(SysSystemInfo ssi) {
		String porName = ssi.getProjectName();
		String rootPath = SystemContext.updown_File_path;
		String rowpath = rootPath + "\\codeauto\\" + porName + "\\"
				+ ssi.getSysCode();
		if (!new File(rowpath).exists()) {
			new File(rowpath).mkdirs();
		}
		ssi.setProjectRootPath("\\codeauto\\" + porName + "\\");
		ssi.setSavePath("\\codeauto\\" + porName + "\\"+ ssi.getSysCode());
	}

	public void delPath(String workspaceRootPath) {
		if (workspaceRootPath != null) {
			File file = new File(workspaceRootPath);
			if (file.exists()) {
				AutoCodeUtils.deleteFile(file);
			}
		}
	}

	public SysSystemInfo findSystemInfo(Integer ssiId) throws HSKException {
		SysSystemInfo sysSystemInfo = new SysSystemInfo();
		try {
			if (ssiId != null) {
				sysSystemInfo = sysSystemInfoDao.findSysSystemInfoById(ssiId);
			} else{ 
				sysSystemInfo.setSystemtype(2);
				sysSystemInfo.setSysCode(CreateCodeUtil.getSysInfoNo());
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return sysSystemInfo;
	}
	

	public SysRetrunMessage delSystemInfo(Integer ssiId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		if (ssiId != null) {
			try {
				SysSystemInfo sysSystemInfo = sysSystemInfoDao
						.findSysSystemInfoById(ssiId);
				String workspaceRootPath = sysSystemInfo.getSavePath();
				delPath(workspaceRootPath);
				modelObjectService.removeRootModel(sysSystemInfo.getSysCode());
				sysSystemInfoDao.delSysSystemInfo(sysSystemInfo);
			} catch (HSKDBException e) {
				e.printStackTrace();
				srm.setCode(0L);
				srm.setMeg("操作失败!" + e.getMessage());
			}
		} else {
			srm.setCode(0L);
			srm.setMeg("操作失败,主键为空!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage copySystemInfo(SysSystemInfo sysSystemInfo)
			throws HSKException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysRetrunMessage getAllSystemInfo() throws HSKException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysSystemInfo> findeListSystemInfo(SysSystemInfo sysSystemInfo)
			throws HSKException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysRetrunMessage testIOSystemInfo(SysSystemInfo sysSystemInfo)
			throws HSKException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysRetrunMessage runSetMaping() throws HSKException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysRetrunMessage updateSetMaping(String datetimeCode, String mapCode)
			throws HSKException {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public  SysRetrunMessage  findeListNodeSystemInfo() throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		List<TreeNode> list_back=new ArrayList<TreeNode>();
		SysSystemInfo ssi =new SysSystemInfo ();
		try {
			List<SysSystemInfo> list_obj=this.getList(ssi);
			if(list_obj!=null&&list_obj.size()>0){
				 for(SysSystemInfo did :list_obj){
					 TreeNode tn=new TreeNode();
					 tn.setId(did.getSysCode());
					 tn.setName(did.getSysName());
					 list_back.add(tn);
				 }
			}
			srm.setObj(list_back);
		} catch (HSKDBException e) {
			 srm.setCode(0l);
			 srm.setMeg(e.getMessage());
			e.printStackTrace();
		}
		return srm;
	}

}
