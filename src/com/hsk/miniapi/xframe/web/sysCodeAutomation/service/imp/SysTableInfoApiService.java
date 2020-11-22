package com.hsk.miniapi.xframe.web.sysCodeAutomation.service.imp;

import java.util.ArrayList;

import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.ISysTableInfoApiDao;
import com.hsk.xframe.api.persistence.SysTableInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.miniapi.xframe.web.sysCodeAutomation.service.ISysTableInfoApiService;

@Service
public class SysTableInfoApiService extends DSTApiService implements
		ISysTableInfoApiService {
	@Autowired
	private ISysTableInfoApiDao sysTableInfoDao;

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
