package com.hsk.xframe.web.sysCodeAutomation.service.imp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.ISysTableInfoDao;
import com.hsk.xframe.api.persistence.SysTableInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.web.sysCodeAutomation.service.ISysTableInfoService;

@Service
public class SysTableInfoService extends DSTService implements
		ISysTableInfoService {
	@Autowired
	private ISysTableInfoDao sysTableInfoDao;

	public PagerModel getTableList(SysTableInfo sti) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList());
		try {
			pm = sysTableInfoDao.getSysTableInfoPageParam(sti);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	public SysRetrunMessage saveTable(SysTableInfo sti) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			if (sti.getStableId() == null) {
				sysTableInfoDao.newSysTableInfo(sti);
			} else {
				sysTableInfoDao.updateSysTableInfo(sti);
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
		} 
		return srm;
	}

 
	public SysTableInfo getOneSysTableInfo(Integer id) throws HSKException {
		SysTableInfo  sti=new SysTableInfo();
		try{
			sti=sysTableInfoDao.findSysTableInfoById(id);	
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
		return sti;
	}

}
