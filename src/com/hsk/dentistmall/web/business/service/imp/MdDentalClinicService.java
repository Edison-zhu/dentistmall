package com.hsk.dentistmall.web.business.service.imp;

import java.util.*;

import com.hsk.xframe.api.daobbase.ISysSalesmanDao;
import com.hsk.xframe.api.persistence.SysSalesManEntity;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.IorgDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.dentistmall.web.business.service.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;


/**
  business业务操作实现类
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:17
 */

@Service
public class  MdDentalClinicService extends DSTService implements IMdDentalClinicService {
   /**
   *业务处理dao类  mdDentalClinicDao
   */
	@Autowired
	protected IMdDentalClinicDao mdDentalClinicDao;
	@Autowired
	protected IMdCompanyGroupDao mdCompanyGroupDao;
	@Autowired
	protected IMdDentistHospitalDao mdDentistHospitalDao;

	@Autowired
	protected IorgDao  orgDao;
	@Autowired
	protected IMdOrderInfoDao mdOrderInfoDao;
	@Autowired
	protected ISysSalesmanDao sysSalesmanDao;
 /**
	 * 根据md_dental_clinic表主键删除MdDentalClinic对象记录
     * @param  rbbId  Integer类型(md_dental_clinic表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer rbbId) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);

		try {
			MdDentalClinic att_MdDentalClinic = new MdDentalClinic();
			if (rbbId != null) {
				att_MdDentalClinic = mdDentalClinicDao.findMdDentalClinicById(rbbId);
				if(att_MdDentalClinic.getRbaId() != null) {
					MdCompanyGroup group = new MdCompanyGroup();
					group.setRbaId(att_MdDentalClinic.getRbaId());
					att_MdDentalClinic.setSalesmanIds(group.getSalesmanIds());
					att_MdDentalClinic.setSalesName(group.getSalesName());
				} else if(att_MdDentalClinic.getRbsId() != null){
					MdDentistHospital mdDentistHospital = new MdDentistHospital();
					mdDentistHospital.setRbsId(att_MdDentalClinic.getRbsId());
					mdDentistHospital = (MdDentistHospital) this.getOne(mdDentistHospital);
					att_MdDentalClinic.setSalesmanIds(mdDentistHospital.getSalesmanIds());
					att_MdDentalClinic.setSalesName(mdDentistHospital.getSalesName());
				}
			}else {
				SysUserInfo sui = this.GetOneSessionAccount();
				if (sui.getOrganizaType().equals("20001")) {
					att_MdDentalClinic.setRbaId(sui.getOldId());
					MdCompanyGroup group = new MdCompanyGroup();
					group.setRbaId(sui.getOldId());
					group = (MdCompanyGroup) this.getOne(group);
					att_MdDentalClinic.setRbaName(group.getRbaName());
					att_MdDentalClinic.setSalesmanIds(group.getSalesmanIds());
					att_MdDentalClinic.setSalesName(group.getSalesName());
				} else if (sui.getOrganizaType().equals("20002")) {
					att_MdDentalClinic.setRbsId(sui.getOldId());
					MdDentistHospital mdDentistHospital = new MdDentistHospital();
					mdDentistHospital.setRbsId(sui.getOldId());
					mdDentistHospital = (MdDentistHospital) this.getOne(mdDentistHospital);
					att_MdDentalClinic.setRbsName(mdDentistHospital.getRbsName());
					att_MdDentalClinic.setRbaName(mdDentistHospital.getRbaName());
					att_MdDentalClinic.setRbaId(mdDentistHospital.getRbaId());
					att_MdDentalClinic.setSalesmanIds(mdDentistHospital.getSalesmanIds());
					att_MdDentalClinic.setSalesName(mdDentistHospital.getSalesName());
				}
				MdDentistHospital mdDentistHospital = new MdDentistHospital();
			}

			srm.setObj(att_MdDentalClinic);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
		}
		return srm;
	}

    /**
	 * 根据md_dental_clinic表主键删除MdDentalClinic对象记录
     * @param  rbbId  Integer类型(md_dental_clinic表主键)
	 * @throws HSKException
	 */

	public MdDentalClinic findObject(Integer rbbId) throws HSKException{
			MdDentalClinic  att_MdDentalClinic=new MdDentalClinic();
			try{
		        att_MdDentalClinic= mdDentalClinicDao.findMdDentalClinicById( rbbId) ;
				} catch (HSKDBException e) {
					e.printStackTrace();
					throw new  HSKException(e);
				}
				return  att_MdDentalClinic;
	}

	private void deleteOrgId(Integer rbbId)throws HSKException{
		//删除 组织id
		 SysOrgGx sysOrgGx=new SysOrgGx ();
		 sysOrgGx.setOldId(rbbId);
		 sysOrgGx.setOrganizaType("20003");
		try {
			List<SysOrgGx> list_t = this.getList(sysOrgGx);
			for(SysOrgGx did:list_t){
		    	 this.deleteObjects(did);
		     }
		} catch (HSKDBException e) {
			e.printStackTrace();
			throw  new HSKException(e);
		}

	}


	 /**
	 * 根据md_dental_clinic表主键删除MdDentalClinic对象记录
     * @param  rbbId  Integer类型(md_dental_clinic表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer rbbId) throws HSKException{
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{
			   //查看门诊下是否有未完成的订单
			   MdOrderInfo mdOrderInfo = new MdOrderInfo();
			   mdOrderInfo.setProcessStatus_str("1,2,3,4,7");
			   mdOrderInfo.setOrderState("1");
			   mdOrderInfo.setPurchaseType("3");
			   mdOrderInfo.setRbsId(rbbId);
			   List<MdOrderInfo> orderList = mdOrderInfoDao.getListByMdOrderInfo(mdOrderInfo);
			   if(orderList != null && orderList.size() > 0){
				   srm.setCode(0l);
				   srm.setMeg("该门诊下有未完成的订单，不允许删除！");
				   return srm;
			   }
			   MdDentalClinic att_MdDentalClinic= mdDentalClinicDao.findMdDentalClinicById( rbbId) ;
			   att_MdDentalClinic.setState(0);
			   mdDentalClinicDao.saveOrUpdateMdDentalClinic(att_MdDentalClinic);
			 } catch (Exception e) {
					e.printStackTrace();
					throw new  HSKException(e);
		     }
		   return srm;
	}

	/**
	 * 根据md_dental_clinic表主键删除多条MdDentalClinic对象记录
     * @param  RbbIds  Integer类型(md_dental_clinic表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String rbbIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = rbbIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					Integer rbbId = Integer.valueOf(did);
					mdDentalClinicDao.deleteMdDentalClinicById(rbbId);
					deleteOrgId(rbbId);

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

	 private Integer att_mxxOrgGxId=6;
	 /**
	 * 新增或修改md_dental_clinic表记录 ,如果md_dental_clinic表主键MdDentalClinic.RbbId为空就是添加，如果非空就是修改
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
     * @return MdDentalClinic  修改后的MdDentalClinic对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject(MdDentalClinic att_MdDentalClinic, String listFilecode)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			Integer orgid = 1;
			Integer new_salesId = null;// Integer.parseInt(att_MdDentalClinic.getSalesmanIds() == null ? "0" : att_MdDentalClinic.getSalesmanIds());
			Integer rbbId = att_MdDentalClinic.getRbbId();
			SysUserInfo sui = this.GetOneSessionAccount();
			if(att_MdDentalClinic.getRbaId() != null){
				MdCompanyGroup mdCompanyGroup = mdCompanyGroupDao.findMdCompanyGroupById(att_MdDentalClinic.getRbaId());
				String salesIdStr = mdCompanyGroup.getSalesmanIds();
				if(salesIdStr == null || salesIdStr.equals("")){
					salesIdStr = "0";
				}
				new_salesId = Integer.parseInt(salesIdStr);
				att_MdDentalClinic.setSalesmanIds(mdCompanyGroup.getSalesmanIds());
				att_MdDentalClinic.setSalesName(mdCompanyGroup.getSalesName());
			} else if(att_MdDentalClinic.getRbsId() != null){
				MdDentistHospital mdDentistHospital = mdDentistHospitalDao.findMdDentistHospitalById(att_MdDentalClinic.getRbaId());
				String salesIdStr = mdDentistHospital.getSalesmanIds();
				if(salesIdStr == null || salesIdStr.equals("")){
					salesIdStr = "0";
				}
				new_salesId = Integer.parseInt(salesIdStr);
				att_MdDentalClinic.setSalesmanIds(mdDentistHospital.getSalesmanIds());
				att_MdDentalClinic.setSalesName(mdDentistHospital.getSalesName());
			}
			orgid = (sui != null) ? (sui.getOrgGxId() != null ? (sui.getOrgGxId())
					: 1) : 1;
			if(sui.getUserType() != null && sui.getUserType() == 6){
				SysSalesManEntity sysSalesManEntity = new SysSalesManEntity();
				sysSalesManEntity.setSalesAccount(sui.getAccount());
				sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
				att_MdDentalClinic.setSalesmanIds(sysSalesManEntity.getSalesmanId().toString());
				att_MdDentalClinic.setSalesName(sysSalesManEntity.getSalesName());
				if (rbbId == null) {
					att_MdDentalClinic.setVerifyState(1);
					att_MdDentalClinic.setState(2);
				}
			}

			if (att_MdDentalClinic.getVerifyState() != null && att_MdDentalClinic.getVerifyState() != 3) {
				att_MdDentalClinic.setState(2);
			}
//			if (att_MdDentalClinic.getRbsId() == null) {
//				att_MdDentalClinic.setRbsId((sui != null&&sui.getOldId()!=null)?sui.getOldId():null);
//			}
			if (rbbId == null) {
				att_MdDentalClinic.setCreateDate(new Date());
				int id = mdDentalClinicDao
						.saveMdDentalClinic(att_MdDentalClinic);
				SysOrgGx sysOrgGx = new SysOrgGx();
				sysOrgGx.setOldCode(att_MdDentalClinic.getRbbCode());
				sysOrgGx.setOldId(id);
				sysOrgGx.setOrganizaType("20003");
				sysOrgGx.setNodeName(att_MdDentalClinic.getRbbName());
				sysOrgGx.setMxxOrgGxId((orgid == 1) ? att_mxxOrgGxId : orgid);
				orgDao.saveOrgInfo(sysOrgGx);
			}else if(att_MdDentalClinic.getState()==2){
				//查看门诊下是否有未完成的订单
				   MdOrderInfo mdOrderInfo = new MdOrderInfo();
				   mdOrderInfo.setProcessStatus_str("1,2,3,4,7");
				   mdOrderInfo.setOrderState("1");
				   mdOrderInfo.setPurchaseType("3");
				   mdOrderInfo.setRbsId(rbbId);
				   List<MdOrderInfo> orderList = mdOrderInfoDao.getListByMdOrderInfo(mdOrderInfo);
				   if(orderList != null && orderList.size() > 0){
					   srm.setCode(0l);
					   srm.setMeg("该门诊下有未完成的订单，不允许删除！");
					   return srm;
				   }
			}

			if(listFilecode != null){
				String[] fileCodeArray = listFilecode.trim().split(",");
				if(fileCodeArray.length >= 1 && fileCodeArray[0] != null && !fileCodeArray[0].equals("")){
					att_MdDentalClinic.setLogo(fileCodeArray[0]);
				}
			}

			MdDentalClinic old_mdDentalClinic = mdDentalClinicDao.findMdDentalClinicById(rbbId);
			Integer old_salesId = null;
			if(old_mdDentalClinic != null){
				old_salesId = Integer.parseInt((old_mdDentalClinic.getSalesmanIds() == null || old_mdDentalClinic.getSalesmanIds().equals("")) ? "0" : old_mdDentalClinic.getSalesmanIds());
			}

			mdDentalClinicDao.saveOrUpdateMdDentalClinic(att_MdDentalClinic);
			srm.setObj(att_MdDentalClinic);
			if(sui.getOrganizaType().equals("0") || sui.getOrganizaType().equals("200")) {
				rbbId = att_MdDentalClinic.getRbbId();
				updateSalesMan(new_salesId, old_salesId, rbbId.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
		return srm;
	}

	/**
	 * 更新组织下的业务员
	 * @param salesmanId
	 * @param old_salesId
	 * @param rbbId
	 */
	private void updateSalesMan(Integer salesmanId, Integer old_salesId, String rbbId) {
		//修改绑定的业务员
		try {

			SysSalesManEntity sysSalesManEntity = null;
			if (old_salesId != null && old_salesId != 0) {
				//解除旧业务员的绑定
				sysSalesManEntity = new SysSalesManEntity();
				sysSalesManEntity.setSalesmanId(old_salesId);
				sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
				String rbbIds = sysSalesManEntity.getRbbId();
				if(rbbIds != null) {
					rbbIds = rbbIds.replace(rbbId, "");
					rbbIds = rbbIds.replace(",,", ",");
					sysSalesManEntity.setRbbId(rbbIds);
				}
				sysSalesmanDao.saveSalesman(sysSalesManEntity);
			}
			if (salesmanId != null) {
				//添加新的业务员
				if (salesmanId != old_salesId) {
					sysSalesManEntity = new SysSalesManEntity();
					sysSalesManEntity.setSalesmanId(salesmanId);
					sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
					String rbbIds = sysSalesManEntity.getRbbId();
					if(rbbIds != null) {
						rbbIds = rbbIds.replace(rbbId, "");
						rbbIds = rbbIds.replace(",,", ",");
					} else {
						rbbIds = "";
					}
					rbbIds += rbbId + ",";
					sysSalesManEntity.setRbbId(rbbIds);
					sysSalesmanDao.saveSalesman(sysSalesManEntity);
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
	}

	/**
	 *根据MdDentalClinic对象作为对(md_dental_clinic表进行查询，获取分页对象
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject (MdDentalClinic att_MdDentalClinic) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdDentalClinic>());
			  try{
				  SysUserInfo account = this.GetOneSessionAccount();
				  if(account.getUserType() != null && account.getUserType() == 6){
					  SysSalesManEntity sysSalesManEntity = new SysSalesManEntity();
					  sysSalesManEntity.setSalesAccount(account.getAccount());
					  sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
					  att_MdDentalClinic.setSalesmanIds(sysSalesManEntity.getSalesmanId().toString());
					  att_MdDentalClinic.setSalesName(sysSalesManEntity.getSalesName());
				  }
				  if(att_MdDentalClinic.getState()==null)
					  att_MdDentalClinic.setState_str("1,2");
				  att_MdDentalClinic.setTab_like("rbbCode,rbbName");
				  att_MdDentalClinic.setTab_order("rbbId desc");
				  String orgIdStr = this.getOrgIdStr();
				  if(orgIdStr != null && !orgIdStr.equals("")){
					  att_MdDentalClinic.setRbbId_str(orgIdStr);
					  pm=mdDentalClinicDao.getPagerModelByMdDentalClinic(att_MdDentalClinic);
				  }
			    } catch (Exception e) {
					e.printStackTrace();
		        }
		return pm;
	}



	public String getOrgIdStr() throws     HSKException {
		SysUserInfo account =this.GetOneSessionAccount();
		if(account!=null){
			SysOrgGx sog=new SysOrgGx();
			if(account.getOrgGxId()==1)
				sog.setOrgGxId(6);
			else
				sog.setOrgGxId(account.getOrgGxId());
			sog.setOrganizaType("20003");
			try {
				return this.orgDao.getOldIdStrByParent(sog);
			} catch (HSKDBException e) {
				e.printStackTrace();
				throw new HSKException(e);
			}
		}
		return null;
	}
	@Override
	public SysRetrunMessage updateVerify(Integer id, Integer verifyState, String verifyRemark) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try {
			MdDentalClinic mdDentalClinic = mdDentalClinicDao.findMdDentalClinicById(id);
			if (mdDentalClinic != null) {
				mdDentalClinic.setVerifyState(verifyState);
				Integer state = 2;
				if (verifyState == 3){
					state = 1;
				}
				mdDentalClinic.setState(state);
				if (verifyRemark != null && !verifyRemark.equals("")) {
					mdDentalClinic.setVerifyRemark(verifyRemark);
				}
				mdDentalClinicDao.updateMdDentalClinicById(mdDentalClinic.getRbbId(), mdDentalClinic);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sm.setCode(2l);
//			throw new HSKException(e);
		}
		return sm;
	}

}