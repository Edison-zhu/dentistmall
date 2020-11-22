package com.hsk.miniapi.xframe.web.sysControlParam.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.ISysControlParamApiDao;
import com.hsk.xframe.api.persistence.SysControlParam;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.miniapi.xframe.web.sysControlParam.service.ISysControlParamApiService;

@Service
public class SysControlParamApiService extends DSTApiService implements ISysControlParamApiService {
	
	@Autowired
	private ISysControlParamApiDao sysControlParamDao;
	
	@Override
	public SysRetrunMessage saveSysControlParamList(String controlId,String paramNames, String paramSource) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo account = this.GetOneSessionAccount();
		SysControlParam sysControlParam = new SysControlParam();
		sysControlParam.setSuiId(account.getSuiId());
		sysControlParam.setControlId(controlId);
		sysControlParam.setParamSource(paramSource);
		try{
			//先删除
			sysControlParamDao.delSysControlParamList(sysControlParam);
			//再保存
			if(paramNames != null && !paramNames.trim().equals("")){
				String[] names = paramNames.trim().split(",");
				for(String name :names){
					SysControlParam obj = new SysControlParam();
					obj.setSuiId(account.getSuiId());
					obj.setControlId(controlId);
					obj.setParamSource(paramSource);
					obj.setParamName(name);
					this.newObject(obj);
				}
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
	public SysRetrunMessage getMyUserSysControlParamList(SysControlParam sysControlParam) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		List<SysControlParam> list = new ArrayList<SysControlParam>();
		SysUserInfo account = this.GetOneSessionAccount();
		sysControlParam.setSuiId(account.getSuiId());
		try {
			list = sysControlParamDao.getSysControlParamList(sysControlParam);
			srm.setObj(list);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
		}
		return srm;
	}

	@Override
	public SysRetrunMessage getSysControlParamList(SysControlParam sysControlParam) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			List<SysControlParam> list = sysControlParamDao.getSysControlParamList(sysControlParam);
			srm.setObj(list);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
		}
		return srm;
	}

}
