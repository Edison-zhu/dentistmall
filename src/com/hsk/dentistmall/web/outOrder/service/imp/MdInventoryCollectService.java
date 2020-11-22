package com.hsk.dentistmall.web.outOrder.service.imp;


import com.hsk.dentistmall.api.daobbase.IMdInventoryDao;
import com.hsk.dentistmall.api.persistence.MdInventoryCollect;
import com.hsk.dentistmall.api.persistence.MdInventoryCollectView;
import com.hsk.dentistmall.web.outOrder.service.IMdInventoryCollectService;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MdInventoryCollectService extends DSTService implements IMdInventoryCollectService {
	
	@Autowired
	private IMdInventoryDao mdInventoryDao;

	@Override
	public SysRetrunMessage saveOrUpdateObject(MdInventoryCollect att_MdInventoryCollect) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			att_MdInventoryCollect.setSuiId(account.getSuiId());
			att_MdInventoryCollect.setState("1");
			this.newObject(att_MdInventoryCollect);
			srm.setObj(att_MdInventoryCollect);
		}catch(Exception e){
			e.printStackTrace();
		}
		return srm;
	}

	@Override
	public SysRetrunMessage deleteObject(Integer micId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			MdInventoryCollect mdInventoryCollect = new MdInventoryCollect();
			mdInventoryCollect.setMicId(micId);
			mdInventoryCollect = (MdInventoryCollect) this.getOne(mdInventoryCollect);
			this.deleteObjects(mdInventoryCollect);
		}catch(Exception e){
			e.printStackTrace();
		}
		return srm;
	}

	@Override
	public PagerModel getPagerModelObject(MdInventoryCollectView mdInventoryCollectView) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList<MdInventoryCollectView>());
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			mdInventoryCollectView.setSuiId(account.getSuiId());
			pm = mdInventoryDao.getPagerModelByMdInventoryCollectView(mdInventoryCollectView);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}


}
