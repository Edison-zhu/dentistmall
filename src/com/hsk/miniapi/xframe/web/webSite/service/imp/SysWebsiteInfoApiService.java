package com.hsk.miniapi.xframe.web.webSite.service.imp;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.ISysFileInfoApiDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysWebsiteInfoApiDao;
import com.hsk.xframe.api.persistence.SysFileInfo;
import com.hsk.xframe.api.persistence.SysWebsiteInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.miniapi.xframe.web.webSite.service.ISysWebsiteInfoApiService;

/**
 * 网站信息业务实现类
 */
@Service
public class SysWebsiteInfoApiService extends DSTApiService implements ISysWebsiteInfoApiService {

	@Autowired
	private ISysWebsiteInfoApiDao sysWebsiteInfoDao;
	@Autowired
	private ISysFileInfoApiDao sysFileInfoDao;
	
	
	@Override
	public SysRetrunMessage deleteSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			
			boolean has = sysWebsiteInfoDao.isHasChildren(sysWebsiteInfo);
			if (has) {
				srm.setCode(0l);
				srm.setMeg("此网站含有栏目,请先删除栏目,再做此操作!");
				return srm;
			}
			sysWebsiteInfoDao.DeleteSysWebsiteInfo(sysWebsiteInfo);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}
	@Override
	public SysRetrunMessage saveSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			if (sysWebsiteInfo.getSwiId() == null) {
				String ss = CreateCodeUtil.getNo("WS");
				sysWebsiteInfo.setWebsitecode(ss);
				sysWebsiteInfoDao.NewSysWebsiteInfo(sysWebsiteInfo);
			} else {
				sysWebsiteInfoDao.UpdateSysWebsiteInfo(sysWebsiteInfo);
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}
	@Override
	public PagerModel GetPMSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo)
			throws HSKException {
		PagerModel pm = new PagerModel();
		try {
			sysWebsiteInfo.setTab_like("websitName");
			pm = sysWebsiteInfoDao.findPagerModelSysWebsiteInfo(sysWebsiteInfo);
			
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return pm;
	}
	@Override
	public SysWebsiteInfo GetOneSysWebsiteInfo(SysWebsiteInfo sysWebsiteInfo)
			throws HSKException {
		try {
			sysWebsiteInfo = sysWebsiteInfoDao.findOneSysWebsiteInfo(sysWebsiteInfo);
			String wsIconFileCode = sysWebsiteInfo.getWebsitIconFilecode();
			SysFileInfo fileIcon = sysFileInfoDao.getSysFileInfoByFileCode(wsIconFileCode);
			this.getModel().addAttribute("websitIconFilecode", fileIcon);

			String bg1FileCode = sysWebsiteInfo.getBackgroundPictureFileCode01();
			SysFileInfo filebg1 = sysFileInfoDao.getSysFileInfoByFileCode(bg1FileCode);
			this.getModel().addAttribute("backgroundPictureFileCode01", filebg1);

			String bg2FileCode = sysWebsiteInfo.getBackgroundPictureFileCode02();
			SysFileInfo filebg2 = sysFileInfoDao.getSysFileInfoByFileCode(bg2FileCode);
			this.getModel().addAttribute("backgroundPictureFileCode02", filebg2);

			String bg3FileCode = sysWebsiteInfo.getBackgroundPictureFileCode03();
			SysFileInfo filebg3 = sysFileInfoDao.getSysFileInfoByFileCode(bg3FileCode);
			this.getModel().addAttribute("backgroundPictureFileCode03", filebg3);

		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return sysWebsiteInfo;
	}
	
}
