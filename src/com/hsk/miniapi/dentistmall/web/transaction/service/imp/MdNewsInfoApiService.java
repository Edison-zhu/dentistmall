package com.hsk.miniapi.dentistmall.web.transaction.service.imp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.persistence.MdMaterielFormatView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdNewsInfoApiDao;
import com.hsk.dentistmall.api.persistence.MdNewsInfo;
import com.hsk.miniapi.dentistmall.web.transaction.service.IMdNewsInfoApiService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;

@Service
public class MdNewsInfoApiService extends DSTApiService implements IMdNewsInfoApiService {
	
	@Autowired
	protected IMdNewsInfoApiDao mdNewsInfoDao;

	@Override
	public SysRetrunMessage getMdNewsInfoList() throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo account = this.GetOneSessionAccount();
		List<MdNewsInfo> newsList = new ArrayList<MdNewsInfo>();
		try {
			if(account != null){
				  
				  if(account.getOrganizaType().equals("20001") || account.getOrganizaType().equals("20002") || account.getOrganizaType().equals("20003")){
					  if(account.getUserRole().contains("2")){//采购员
						  //获取所有订单入库提醒
						  MdNewsInfo mdNewsInfo = new MdNewsInfo();
						  mdNewsInfo.setUiId(account.getSuiId());
						  mdNewsInfo.setUiType(2);
						  List<MdNewsInfo> list = mdNewsInfoDao.getMdNewsInfoList(mdNewsInfo);
						  newsList.addAll(list);
						  //获取所有缺货提醒
						  mdNewsInfo = new MdNewsInfo();  
						  mdNewsInfo.setUiId(account.getOldId());
						  if(account.getOrganizaType().equals("20001")){
							mdNewsInfo.setUiType(4);
						  }else if(account.getOrganizaType().equals("20002")){
							mdNewsInfo.setUiType(5);
						  }else if(account.getOrganizaType().equals("20003")){
								mdNewsInfo.setUiType(6);
						  }
						  mdNewsInfo.setIsView(1);
						  mdNewsInfo.setNewsType(7);
						  list = mdNewsInfoDao.getMdNewsInfoList(mdNewsInfo);
						  newsList.addAll(list);
					  }else if(account.getUserRole().contains("3")){//仓库管理员
						  //获取所有入库提醒
						  MdNewsInfo mdNewsInfo = new MdNewsInfo();
						  mdNewsInfo.setUiId(account.getOldId());
						  if(account.getOrganizaType().equals("20001")){
							mdNewsInfo.setUiType(4);
						  }else if(account.getOrganizaType().equals("20002")){
							mdNewsInfo.setUiType(5);
						  }else if(account.getOrganizaType().equals("20003")){
								mdNewsInfo.setUiType(6);
						  }
						  mdNewsInfo.setIsView(1);
						  mdNewsInfo.setNewsType(8);
						  List<MdNewsInfo> list = mdNewsInfoDao.getMdNewsInfoList(mdNewsInfo);
						  newsList.addAll(list);
					  }
				  }if(account.getOrganizaType().equals("100")){
					  MdNewsInfo mdNewsInfo = new MdNewsInfo();
					  mdNewsInfo.setUiId(account.getOldId());
					  mdNewsInfo.setUiType(1);
					  mdNewsInfo.setIsView(1);
					  List<MdNewsInfo> list = mdNewsInfoDao.getMdNewsInfoList(mdNewsInfo);
						newsList.addAll(list);
				  }
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		srm.setObj(newsList);
		return srm;
	}

	@Override
	public SysRetrunMessage saveInventoryNew(Integer mmfId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			MdNewsInfo mdNewsInfo = new MdNewsInfo();
			SysUserInfo account = this.GetOneSessionAccount();
			MdMaterielFormatView view= new MdMaterielFormatView();
			view.setMmfId(mmfId);
			view = (MdMaterielFormatView) this.getOne(view);
			mdNewsInfo.setUiId(account.getOldId());
			if(account.getOrganizaType().equals("20001")){
				mdNewsInfo.setUiType(4);
			}else if(account.getOrganizaType().equals("20002")){
				mdNewsInfo.setUiType(5);
			}else if(account.getOrganizaType().equals("20003")){
				mdNewsInfo.setUiType(6);
			}
			mdNewsInfo.setOrderId(mmfId);
			mdNewsInfo.setNewsType(7);//订单发货
			List<MdNewsInfo> checkList = mdNewsInfoDao.getMdNewsInfoList(mdNewsInfo);
			if(checkList != null && checkList.size() > 0){
				if(sdf.format(checkList.get(0).getCreateDate()).equals(sdf.format(new Date()))){
					srm.setCode(0l);
					srm.setMeg("每个商品每天只能提醒一次！");
					return srm;
				}
			}
			mdNewsInfo.setIsView(1);
			mdNewsInfo.setCreateDate(new Date());
			mdNewsInfo.setContent("商品 "+view.getMatName()+"("+view.getMmfName()+")"+"库存缺少，请注意采购！");
			mdNewsInfoDao.saveMdNewsInfo(mdNewsInfo);
		} catch (Exception e) {
			e.printStackTrace(); 
			srm.setCode(0l);
			srm.setMeg("操作失败!");
	    }
		return srm;
	}

	@Override
	public PagerModel getInventoryNewPagerModel(MdNewsInfo mdNewsInfo, MdMaterielFormatView mdMaterielFormatView) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList<Map<Object,Object>>());
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			mdNewsInfo.setUiId(account.getOldId());
			String purchaseType="";
			Integer oldId;
			if(account.getOrganizaType().equals("20001")){
				mdNewsInfo.setUiType(4);
				purchaseType="1";
			}else if(account.getOrganizaType().equals("20002")){
				mdNewsInfo.setUiType(5);
				purchaseType="2";
			}else if(account.getOrganizaType().equals("20003")){
				mdNewsInfo.setUiType(6);
				purchaseType="3";
			}
			oldId = account.getOldId();
			pm = mdNewsInfoDao.getInventoryNewsPagerModel(mdNewsInfo, mdMaterielFormatView,purchaseType,oldId);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return pm;
	}

	@Override
	public SysRetrunMessage saveMdNewsInfoView(Integer mniId, Integer isView) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			MdNewsInfo mdNewsInfo = mdNewsInfoDao.findMdNewsInfoById(mniId);
			mdNewsInfo.setIsView(isView);
			mdNewsInfoDao.saveOrUpdateMdNewsInfo(mdNewsInfo);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage getInventoryNewList() throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo account = this.GetOneSessionAccount();
		List<MdNewsInfo> newsList = new ArrayList<MdNewsInfo>();
		try {
			if(account != null){
				MdNewsInfo mdNewsInfo = new MdNewsInfo();
				if(account.getOrganizaType().equals("20001") || account.getOrganizaType().equals("20002") || account.getOrganizaType().equals("20003")){
					mdNewsInfo = new MdNewsInfo();  
					mdNewsInfo.setUiId(account.getOldId());
					if(account.getOrganizaType().equals("20001")){
						mdNewsInfo.setUiType(4);
					}else if(account.getOrganizaType().equals("20002")){
						mdNewsInfo.setUiType(5);
					}else if(account.getOrganizaType().equals("20003")){
						mdNewsInfo.setUiType(6);
					}
					mdNewsInfo.setIsView(1);
					mdNewsInfo.setNewsType(7);
					List<MdNewsInfo> list = mdNewsInfoDao.getMdNewsInfoList(mdNewsInfo);
					newsList.addAll(list);
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		srm.setObj(newsList);
		return srm;
	}

}
