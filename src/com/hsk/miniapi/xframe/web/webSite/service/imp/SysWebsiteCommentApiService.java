package com.hsk.miniapi.xframe.web.webSite.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.hsk.xframe.api.persistence.SysSwitchEntity;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.persistence.SysWebsiteCommentTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.ISysFileInfoApiDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysWebsiteCommentApiDao;
import com.hsk.xframe.api.persistence.SysWebsiteComment;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.miniapi.xframe.web.webSite.service.ISysWebsiteCommentApiService;

@Service
public class SysWebsiteCommentApiService extends DSTApiService implements ISysWebsiteCommentApiService {
	
	@Autowired
	private ISysWebsiteCommentApiDao sysWebsiteCommentDao;
	@Autowired
	private ISysFileInfoApiDao sysFileInfoDao;

	@Override
	public SysRetrunMessage deleteSysWebsiteComment(
			SysWebsiteComment sysWebsiteComment) throws HSKException {
		SysRetrunMessage srm =new SysRetrunMessage(1l);
		try {
			sysWebsiteCommentDao.DeleteSysWebsiteComment(sysWebsiteComment);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage saveSysWebsiteComment(
			SysWebsiteComment sysWebsiteComment) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		Date createDate  =  sysWebsiteComment.getCreateDatetime();
		if(createDate == null){
			sysWebsiteComment.setCreateDatetime(new Date());
		}
		try {
			if (sysWebsiteComment.getSwmId() == null) {
				
				String ss= CreateCodeUtil.getNo("WM");
				sysWebsiteComment.setNewscode(ss);
				sysWebsiteCommentDao.NewSysWebsiteComment(sysWebsiteComment);
			} else {
				sysWebsiteCommentDao.UpdateSysWebsiteComment(sysWebsiteComment);
			}
			srm.setObj(sysWebsiteComment);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public PagerModel GetPMSysWebsiteComment(SysWebsiteComment sysWebsiteComment)
			throws HSKException {
		PagerModel pm = new PagerModel();
		try {
			pm = sysWebsiteCommentDao.findPagerModelSysWebsiteComment(sysWebsiteComment);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public SysWebsiteComment GetOneSysWebsitComment(
			SysWebsiteComment sysWebsiteComment) throws HSKException {
		SysWebsiteComment comment = null;
		try {
			comment = sysWebsiteCommentDao.findOneSysWebsiteComment(sysWebsiteComment);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return comment;
	}

	@Override
	public SysWebsiteComment addSysWebsitComment(SysWebsiteComment sysWebsiteComment) throws HSKException {
		if(sysWebsiteComment.getSwmId()==null){
			//设置序列号
			try {
				Integer order = sysWebsiteCommentDao.getMaxOrderByParentId(sysWebsiteComment.getSwcId())+1;
				sysWebsiteComment.setSerialNumber(order);
			} catch (HSKDBException e) {
				e.printStackTrace();
			}
		}
		return sysWebsiteComment;
	}

	@Override
	public SysRetrunMessage setSysWebsitCommentToTop(
			SysWebsiteComment sysWebsiteComment) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			sysWebsiteCommentDao.setSysWebsiteCommentToTop(sysWebsiteComment);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public List<SysWebsiteComment> getSysWebsiteCommentListBySwcId(Integer swcId, Integer number) throws HSKException {
		List<SysWebsiteComment> reList = new ArrayList<SysWebsiteComment>();
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			SysSwitchEntity sysSwitchEntity = this.getApiSessionSwitch(1);
			if (sysUserInfo == null && sysSwitchEntity != null && Objects.equals(sysSwitchEntity.getState(), 1)) {
				reList = sysWebsiteCommentDao.findPagerModelSysWebsiteCommentByIdNoLogin(swcId, number);
				return reList;
			} else {
				reList = sysWebsiteCommentDao.findPagerModelSysWebsiteCommentById(swcId, number);
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return reList;
	}

}
