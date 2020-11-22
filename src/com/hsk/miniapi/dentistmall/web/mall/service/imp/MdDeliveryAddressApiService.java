package com.hsk.miniapi.dentistmall.web.mall.service.imp;

import java.util.*;

import com.hsk.miniapi.SessionUtil;
import com.hsk.miniapi.xframe.api.daobbase.ISysUserInfoApiDao;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.persistence.SysUserInfo;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage; 
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.miniapi.dentistmall.web.mall.service.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.miniapi.dentistmall.api.daobbase.*;


/** 
  mall业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:22:54
 */
 
@Service
public class MdDeliveryAddressApiService extends DSTApiService implements IMdDeliveryAddressApiService {
   /**
   *业务处理dao类  mdDeliveryAddressDao 
   */
	@Autowired
	protected IMdDeliveryAddressApiDao mdDeliveryAddressDao;
	@Autowired
	private ISysUserInfoApiDao sysUserInfoDao;

 /**
	 * 根据md_delivery_address表主键删除MdDeliveryAddress对象记录
     * @param  mdaId  Integer类型(md_delivery_address表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer mdaId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);
			  	
			try{
		   			MdDeliveryAddress     att_MdDeliveryAddress= mdDeliveryAddressDao.findMdDeliveryAddressById( mdaId) ;
					srm.setObj(att_MdDeliveryAddress);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	} 

    /**
	 * 根据md_delivery_address表主键删除MdDeliveryAddress对象记录
     * @param  mdaId  Integer类型(md_delivery_address表主键)
	 * @throws HSKException
	 */

	public MdDeliveryAddress findObject(Integer mdaId) throws HSKException{
			MdDeliveryAddress  att_MdDeliveryAddress=new MdDeliveryAddress();		
			try{
		        att_MdDeliveryAddress= mdDeliveryAddressDao.findMdDeliveryAddressById( mdaId) ;
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdDeliveryAddress;
	}
	
	 
	 /**
	 * 根据md_delivery_address表主键删除MdDeliveryAddress对象记录
     * @param  mdaId  Integer类型(md_delivery_address表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer mdaId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
				mdDeliveryAddressDao.deleteMdDeliveryAddressById(mdaId);
			   SysUserInfo sysUserInfo = this.getApiSessionAccount();

			   MdDeliveryAddress address = new MdDeliveryAddress();
			   address.setSuiId(sysUserInfo.getSuiId());
			   List<MdDeliveryAddress> list = mdDeliveryAddressDao.getListByMdDeliveryAddress(address);
			   if (list == null || list.isEmpty()) {

			   } else {
				   address = list.get(0);
				   address.setIfDefault("1");
				   address = mdDeliveryAddressDao.saveOrUpdateMdDeliveryAddress(address);
				   if (address.getIfDefault() != null && address.getIfDefault().equals("1")) {
					   mdDeliveryAddressDao.updateMdDeliveryAddressDefualt(address.getMdaId().toString());
				   }
			   }
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}
	
	/**
	 * 根据md_delivery_address表主键删除多条MdDeliveryAddress对象记录
     * @param  MdaIds  Integer类型(md_delivery_address表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String mdaIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = mdaIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					mdDeliveryAddressDao.deleteMdDeliveryAddressById(Integer
							.valueOf(did));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			HSKException hse = new HSKException(e);
			hse.setDisposeType("01");
			throw hse;
		} catch (HSKDBException e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
		return srm;	  
	 }
	 
 
	 /**
	 * 新增或修改md_delivery_address表记录 ,如果md_delivery_address表主键MdDeliveryAddress.MdaId为空就是添加，如果非空就是修改
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
     * @return MdDeliveryAddress  修改后的MdDeliveryAddress对象记录
	 * @throws HSKDBException
	  */
	 public SysRetrunMessage saveOrUpdateObject(MdDeliveryAddress att_MdDeliveryAddress) throws HSKException {
		 SysRetrunMessage srm = new SysRetrunMessage(1l);
		 try {
			 SysUserInfo sysUserInfo = this.getApiSessionAccount();
			 att_MdDeliveryAddress.setSuiId(sysUserInfo.getSuiId());

			 MdDeliveryAddress address = new MdDeliveryAddress();
			 address.setSuiId(sysUserInfo.getSuiId());
			 List<MdDeliveryAddress> list = mdDeliveryAddressDao.getListByMdDeliveryAddress(address);
			 if (list == null || list.isEmpty()) {
			 	att_MdDeliveryAddress.setIfDefault("1");
			 }

			 att_MdDeliveryAddress = mdDeliveryAddressDao.saveOrUpdateMdDeliveryAddress(att_MdDeliveryAddress);
			 if (att_MdDeliveryAddress.getIfDefault() != null && att_MdDeliveryAddress.getIfDefault().equals("1")) {
				mdDeliveryAddressDao.updateMdDeliveryAddressDefualt(att_MdDeliveryAddress.getMdaId().toString());
			 }

			 srm.setObj(att_MdDeliveryAddress);
		 } catch (Exception e) {
			 e.printStackTrace();
			 throw new HSKException(e);
		 }
		 return srm;
	 }

	@Override
	public SysRetrunMessage updateMdDeliveryIfDefualt(Integer mdaId, String ifDefualt) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			MdDeliveryAddress att_MdDeliveryAddress = mdDeliveryAddressDao.findMdDeliveryAddressById(mdaId);

			if (att_MdDeliveryAddress == null) {
				srm.setCode(0L);
				return srm;
			}

			att_MdDeliveryAddress.setIfDefault(ifDefualt);
			att_MdDeliveryAddress = mdDeliveryAddressDao.updateMdDeliveryAddressById(mdaId, att_MdDeliveryAddress);
			if (att_MdDeliveryAddress.getIfDefault() != null && att_MdDeliveryAddress.getIfDefault().equals("1")) {
				mdDeliveryAddressDao.updateMdDeliveryAddressDefualt(att_MdDeliveryAddress.getMdaId().toString());
			}


			srm.setObj(att_MdDeliveryAddress);


		} catch (Exception e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
		return srm;
	}

	/**
	 *根据MdDeliveryAddress对象作为对(md_delivery_address表进行查询，获取分页对象
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdDeliveryAddress att_MdDeliveryAddress) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdDeliveryAddress>());
			  try{
				  SysUserInfo sysUserInfo = this.getApiSessionAccount();
				  att_MdDeliveryAddress.setSuiId(sysUserInfo.getSuiId());
				  att_MdDeliveryAddress.setTab_like("addressee");
				  att_MdDeliveryAddress.setTab_order("ifDefault");
					pm=mdDeliveryAddressDao.getPagerModelByMdDeliveryAddress(att_MdDeliveryAddress);
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
		return pm;
	}

	@Override
	public SysRetrunMessage getMdDeliveryAddressByUser() throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		MdDeliveryAddress att_MdDeliveryAddress = new MdDeliveryAddress();
		try {
			SysUserInfo account = this.getApiSessionAccount();
			if(account!=null && account.getSuiId() !=null){
				att_MdDeliveryAddress.setSuiId(account.getSuiId());
				List<MdDeliveryAddress> addressList = new ArrayList<MdDeliveryAddress>();
				try {
					att_MdDeliveryAddress.setTab_order("ifDefault");
					addressList = mdDeliveryAddressDao.getListByMdDeliveryAddress(att_MdDeliveryAddress);
					boolean noDefault = true;
					MdDeliveryAddress mdDeliveryAddress1 = null;
					for (MdDeliveryAddress mdDeliveryAddress : addressList) {
						mdDeliveryAddress1 = mdDeliveryAddress;
						if (mdDeliveryAddress.getIfDefault().equals("1")) {
							noDefault = false;
						} else {
							noDefault = true;
						}
					}
					if (noDefault && addressList != null && !addressList.isEmpty()) {
						addressList.get(0).setIfDefault("1");
					}
					srm.setObj(addressList);
				} catch (HSKDBException e) {
					e.printStackTrace();
					srm.setCode(0l);
				}
			}
		} catch (Exception e) {
			srm.setCode(0l);
			e.printStackTrace();
		}

		return srm;
	} 
	 
}