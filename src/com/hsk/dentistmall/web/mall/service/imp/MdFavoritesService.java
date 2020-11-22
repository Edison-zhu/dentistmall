package com.hsk.dentistmall.web.mall.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.daobbase.IMdMaterielFormatDao;
import com.hsk.dentistmall.api.persistence.MdMaterielFormat;
import com.hsk.xframe.api.persistence.SysUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.api.daobbase.IMdDeliveryAddressDao;
import com.hsk.dentistmall.api.daobbase.IMdFavoritesDao;
import com.hsk.dentistmall.api.daobbase.imp.MDFavoritesDao;
import com.hsk.dentistmall.api.persistence.MdDeliveryAddress;
import com.hsk.dentistmall.api.persistence.MdFavorites;
import com.hsk.dentistmall.web.mall.service.IMdFavoritesService;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.service.imp.DSTService;

@Service
public class MdFavoritesService extends DSTService implements IMdFavoritesService{
	@Autowired
	protected IMdFavoritesDao mdFavoritesDao;
	@Autowired
	protected IMdMaterielFormatDao mdMaterielFormatDao;
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
			SysUserInfo sysUserInfo = this.GetOneShoppingSessionAccount();
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
			SysUserInfo sysUserInfo = this.GetOneShoppingSessionAccount();
			Integer suiId = sysUserInfo.getSuiId();
			mdFavoritesDao.deleteMdFavoritesBySuiIdsAndMfIds(suiId, mfIds);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			throw new  HSKException(e);
		}
		return srm;
	}

	//批量管理  移除收藏夹里面的

	@Override
	public SysRetrunMessage deleteMdCollectBySuiIdsAndMfIds(String mfIds) throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		try{
			SysUserInfo sysUserInfo = this.GetOneSessionAccount();
			Integer suiId = sysUserInfo.getSuiId();
			mdFavoritesDao.deleteMdCollectBySuiIdsAndMfIds(suiId, mfIds);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			throw new  HSKException(e);
		}
		return srm;
	}

	//批量移除复选框后台收藏夹数据
	public SysRetrunMessage deleteMdCollectBySuiIdsAndMcIds(String micIds) throws HSKException{
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		try{
			SysUserInfo sysUserInfo = this.GetOneSessionAccount();
			Integer suiId = sysUserInfo.getSuiId();
			mdFavoritesDao.deleteMdCollectBySuiIdsAndMicIds(suiId, micIds);
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
				pm=mdFavoritesDao.getMdFavoritesMapPagerModel(applicantName, matName, this.GetOneSessionAccount().getSuiId());
		    } catch (Exception e) {
				e.printStackTrace(); 
	        }
	return pm;
	}

	@Override
	public PagerModel searchMdFavoritesBySearch(String searchName) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<Map<String,Object>>());
		try{
			pm=mdFavoritesDao.searchMdFavoritesBySearch(this.GetOneSessionAccount().getSuiId(), searchName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}


	@Override
	public SysRetrunMessage saveMdFavorites(Integer wmsMiId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			//先检查是否之前已经收藏过
			MdFavorites checkMdFavorites= new MdFavorites();
			checkMdFavorites.setSuiId(this.GetOneSessionAccount().getSuiId());
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
			att_MdFavorites.setSuiId(this.GetOneSessionAccount().getSuiId());
			att_MdFavorites.setWmsMiId(wmsMiId);
			att_MdFavorites.setMfState("1");
			att_MdFavorites.setMmfId(mdMaterielFormat.getMmfId());
			this.newObject(att_MdFavorites);
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
		SysUserInfo sysUserInfo = this.GetOneSessionAccount();
		if(sysUserInfo == null){
			srm.setCode(2l);
			return srm;
		}
		try {
			MdFavorites checkFav = new MdFavorites();
			checkFav.setSuiId(this.GetOneSessionAccount().getSuiId());
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


