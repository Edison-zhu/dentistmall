package com.hsk.miniapi.dentistmall.web.mall.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hsk.miniapi.SessionUtil;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdMaterielFormatApiDao;
import com.hsk.dentistmall.api.persistence.MdMaterielFormat;
import com.hsk.miniapi.xframe.api.daobbase.ISysUserInfoApiDao;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.persistence.SysUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdDeliveryAddressApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdFavoritesApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.imp.MDFavoritesApiDao;
import com.hsk.dentistmall.api.persistence.MdDeliveryAddress;
import com.hsk.dentistmall.api.persistence.MdFavorites;
import com.hsk.miniapi.dentistmall.web.mall.service.IMdFavoritesApiService;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;

@Service
public class MdFavoritesApiService extends DSTApiService implements IMdFavoritesApiService{
	@Autowired
	protected IMdFavoritesApiDao mdFavoritesDao;
	@Autowired
	protected IMdMaterielFormatApiDao mdMaterielFormatDao;

	@Override
	public SysRetrunMessage deleteObject(Integer mfId) throws HSKException {
		// TODO Auto-generated method stub
		 SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
			   mdFavoritesDao.deleteMdFavoritesById(mfId);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}

	@Override
	public SysRetrunMessage delectAllObjectBySuiId() throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		try{
			String sessionId = request.getHeader("sessionId");
			String accountName = SessionUtil.getLocalAccount(sessionId);
			SysUserInfo sysUserInfo = new SysUserInfo();
			sysUserInfo.setAccount(accountName);
			sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
			Integer suiId = sysUserInfo.getSuiId();
			mdFavoritesDao.delectAllObjectBySuiId(suiId);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			throw new  HSKException(e);
		}
		return srm;
	}

	@Override
	public SysRetrunMessage deleteMdFavoritesBySuiIdsAndMfIds(String mfIds) throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		try{
			String sessionId = request.getHeader("sessionId");
			String accountName = SessionUtil.getLocalAccount(sessionId);
			SysUserInfo sysUserInfo = new SysUserInfo();
			sysUserInfo.setAccount(accountName);
			sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
			Integer suiId = sysUserInfo.getSuiId();
			mdFavoritesDao.deleteMdFavoritesBySuiIdsAndMfIds(suiId, mfIds);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			throw new  HSKException(e);
		}
		return srm;
	}


	@Override
	public PagerModel getPagerModelObject(MdFavorites att_MdFavorites)throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdDeliveryAddress>());
		  try{
				pm=mdFavoritesDao.getPagerModelByMdFavorites(att_MdFavorites);
		    } catch (Exception e) {
				e.printStackTrace(); 
	        }
	return pm;
}


	@Override
	public PagerModel getMdFavoritesMapPagerModel(String applicantName,
			String matName) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<Map<String,Object>>());
		  try{
			  String sessionId = request.getHeader("sessionId");
			  String accountName = SessionUtil.getLocalAccount(sessionId);
			  SysUserInfo sysUserInfo = new SysUserInfo();
			  sysUserInfo.setAccount(accountName);
			  sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
			  Integer suiid = sysUserInfo.getSuiId();
				pm=mdFavoritesDao.getMdFavoritesMapPagerModel(applicantName, matName, suiid);
		    } catch (Exception e) {
				e.printStackTrace(); 
	        }
	return pm;
	}

	@Override
	public PagerModel searchMdFavoritesBySearch(String searchName) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<Map<String,Object>>());
		try{
			String sessionId = request.getHeader("sessionId");
			String accountName = SessionUtil.getLocalAccount(sessionId);
			SysUserInfo sysUserInfo = new SysUserInfo();
			sysUserInfo.setAccount(accountName);
			sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
			Integer suiid = sysUserInfo.getSuiId();
			pm=mdFavoritesDao.searchMdFavoritesBySearch(suiid, searchName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}


	@Override
	public SysRetrunMessage saveMdFavorites(Integer wmsMiId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			Integer suiid = sysUserInfo.getSuiId();
			//先检查是否之前已经收藏过
			MdFavorites checkMdFavorites= new MdFavorites();
			checkMdFavorites.setSuiId(suiid);
			checkMdFavorites.setWmsMiId(wmsMiId);
			checkMdFavorites = (MdFavorites) this.getOne(checkMdFavorites);
			//如果已经收藏过则先删除
			if(checkMdFavorites != null && checkMdFavorites.getMfId() !=null){
				this.deleteObjects(checkMdFavorites);
				srm.setCode(2l);
				return srm;
			}
			MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
			mdMaterielFormat.setWmsMiId(wmsMiId);
			mdMaterielFormat.setState("1");
			List<MdMaterielFormat> list = mdMaterielFormatDao.getListByMdMaterielFormat(mdMaterielFormat);
			if(list != null && list.size() > 0) {
				mdMaterielFormat = list.get(0);
			}
			//添加新的收藏记录
			MdFavorites att_MdFavorites = new MdFavorites();
			att_MdFavorites.setSuiId(suiid);
			att_MdFavorites.setWmsMiId(wmsMiId);
			att_MdFavorites.setMfState("1");
			att_MdFavorites.setMmfId(mdMaterielFormat.getMmfId());
			mdFavoritesDao.saveMdFavorites(att_MdFavorites);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage checkFavorites(Integer wmsMiId) throws HSKException	{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String sessionId = request.getHeader("sessionId");
			String accountName = SessionUtil.getLocalAccount(sessionId);
			SysUserInfo sysUserInfo = new SysUserInfo();
			sysUserInfo.setAccount(accountName);
			sysUserInfo = sysUserInfoDao.getSysUserInfoByObj(sysUserInfo);
			Integer suiid = sysUserInfo.getSuiId();
			MdFavorites checkFav = new MdFavorites();
			checkFav.setSuiId(suiid);
			checkFav.setWmsMiId(wmsMiId);
			checkFav = (MdFavorites)this.getOne(checkFav);
			if(checkFav == null || checkFav.getMfId() == null){ // 未收藏
				srm.setCode(2l);
			}
		} catch (Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败！");
		}
		return srm;
	}
}


