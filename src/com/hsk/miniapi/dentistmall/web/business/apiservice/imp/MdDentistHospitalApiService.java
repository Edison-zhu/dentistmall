package com.hsk.miniapi.dentistmall.web.business.apiservice.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdCompanyGroupApiDao;
import com.hsk.miniapi.xframe.api.daobbase.ISysSalesmanApiDao;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.persistence.SysSalesManEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdDentalClinicApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdDentistHospitalApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdOrderInfoApiDao;
import com.hsk.dentistmall.api.persistence.MdCompanyGroup;
import com.hsk.dentistmall.api.persistence.MdDentalClinic;
import com.hsk.dentistmall.api.persistence.MdDentistHospital;
import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.miniapi.dentistmall.web.business.apiservice.IMdDentistHospitalApiService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.IorgApiDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;


/** 
  business业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:17
 */
 
@Service
public class MdDentistHospitalApiService extends DSTApiService implements IMdDentistHospitalApiService {
   /**
   *业务处理dao类  mdDentistHospitalDao 
   */
	@Autowired
	protected IMdDentistHospitalApiDao mdDentistHospitalDao;
	@Autowired
	protected IMdDentalClinicApiDao mdDentalClinicDao;

	@Autowired
	protected IorgApiDao  orgDao;
	@Autowired
	protected IMdOrderInfoApiDao mdOrderInfoDao;
	@Autowired
	private ISysSalesmanApiDao sysSalesmanDao;
	@Autowired
	private IMdCompanyGroupApiDao mdCompanyGroupDao;

	/**
	 * 根据md_dentist_hospital表主键删除MdDentistHospital对象记录
     * @param  rbsId  Integer类型(md_dentist_hospital表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer rbsId) throws HSKException{
	        SysRetrunMessage srm = new SysRetrunMessage(1l);

		try {
			MdDentistHospital att_MdDentistHospital = new MdDentistHospital();
			if (rbsId != null) {
				att_MdDentistHospital = mdDentistHospitalDao.findMdDentistHospitalById(rbsId);
				if(att_MdDentistHospital.getRbaId() != null) {
					MdCompanyGroup group = new MdCompanyGroup();
					group.setRbaId(att_MdDentistHospital.getRbaId());
					group = (MdCompanyGroup) this.getOne(group);
					att_MdDentistHospital.setSalesmanIds(group.getSalesmanIds());
					att_MdDentistHospital.setSalesName(group.getSalesName());
				}
			}else {
				SysUserInfo sui = this.GetOneSessionAccount();
				if (sui.getOrganizaType().equals("20001")) {
					att_MdDentistHospital.setRbaId(sui.getOldId());
					MdCompanyGroup group = new MdCompanyGroup();
					group.setRbaId(sui.getOldId());
					group = (MdCompanyGroup) this.getOne(group);
					att_MdDentistHospital.setRbaName(group.getRbaName());
					att_MdDentistHospital.setSalesmanIds(group.getSalesmanIds());
					att_MdDentistHospital.setSalesName(group.getSalesName());
				}
			}

			srm.setObj(att_MdDentistHospital);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
		}
		return srm;
	} 

    /**
	 * 根据md_dentist_hospital表主键删除MdDentistHospital对象记录
     * @param  rbsId  Integer类型(md_dentist_hospital表主键)
	 * @throws HSKException
	 */

	public MdDentistHospital findObject(Integer rbsId) throws HSKException{
			MdDentistHospital  att_MdDentistHospital=new MdDentistHospital();		
			try{
				if(rbsId!= null)
					att_MdDentistHospital= mdDentistHospitalDao.findMdDentistHospitalById( rbsId) ;
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdDentistHospital;
	}
	
	 
	private void deleteOrgId(Integer rbsId)throws HSKException{
		//删除 组织id
		 SysOrgGx sysOrgGx=new SysOrgGx ();
		 sysOrgGx.setOldId(rbsId); 
		 sysOrgGx.setOrganizaType("20002");
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
	 * 根据md_dentist_hospital表主键删除MdDentistHospital对象记录
     * @param  rbsId  Integer类型(md_dentist_hospital表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer rbsId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
			 //查看医院下是否有未完成的订单  
			   MdOrderInfo mdOrderInfo = new MdOrderInfo();
			   mdOrderInfo.setProcessStatus_str("1,2,3,4");
			   mdOrderInfo.setOrderState("1");
			   mdOrderInfo.setPurchaseType("2");
			   mdOrderInfo.setRbsId(rbsId);
			   List<MdOrderInfo> orderList = mdOrderInfoDao.getListByMdOrderInfo(mdOrderInfo);
			   if(orderList != null && orderList.size() > 0){
				   srm.setCode(0l);
				   srm.setMeg("该医院下有未完成的订单，不允许删除！");
				   return srm;
			   }
			   MdDentistHospital att_MdDentistHospital= mdDentistHospitalDao.findMdDentistHospitalById( rbsId) ;
			   //查看该集团下是否有门诊存在 
				SysOrgGx sysOrgGx = new SysOrgGx();
				sysOrgGx.setOldId(att_MdDentistHospital.getRbsId());
				sysOrgGx.setOrganizaType("20002");
				List<SysOrgGx> list_t = this.getList(sysOrgGx);
				if(list_t!= null && list_t.size() > 0){
					sysOrgGx = list_t.get(0);
					SysOrgGx sog = new SysOrgGx();
					sog.setOrgGxId(sysOrgGx.getOrgGxId());
					sog.setOrganizaType("20003");
					String rbbIds = this.orgDao.getOldIdStrByParent(sog);
					if(rbbIds != null && !rbbIds.equals("")){
						MdDentalClinic mdDentalClinic = new MdDentalClinic();
						mdDentalClinic.setState(1);
						mdDentalClinic.setRbbId_str(rbbIds);
						List<MdDentalClinic> hospitalList = mdDentalClinicDao.getListByMdDentalClinic(mdDentalClinic);
						if(hospitalList != null && hospitalList.size() > 0){
							   srm.setCode(0l);
							   srm.setMeg("该集团下有门诊信息，不允许删除！");
							   return srm;
						   }
					}
				}
				att_MdDentistHospital.setState(0);
				mdDentistHospitalDao.saveOrUpdateMdDentistHospital(att_MdDentistHospital);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}
	
	/**
	 * 根据md_dentist_hospital表主键删除多条MdDentistHospital对象记录
     * @param  rbsIds  Integer类型(md_dentist_hospital表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String rbsIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = rbsIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					Integer rbsId = Integer.valueOf(did);
					mdDentistHospitalDao.deleteMdDentistHospitalById(rbsId);
					deleteOrgId(rbsId);
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
	 
	 private Integer att_mxxOrgGxId=5;
	 /**
	 * 新增或修改md_dentist_hospital表记录 ,如果md_dentist_hospital表主键MdDentistHospital.RbsId为空就是添加，如果非空就是修改
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
     * @return MdDentistHospital  修改后的MdDentistHospital对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject(
			MdDentistHospital att_MdDentistHospital) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			Integer orgid = 1;
			Integer new_salesId = null; //= Integer.parseInt(att_MdDentistHospital.getSalesmanIds() == null ? "0" : att_MdDentistHospital.getSalesmanIds());
			Integer rbsId = att_MdDentistHospital.getRbsId();
			SysUserInfo sui = this.GetOneSessionAccount();
			orgid = (sui != null) ? (sui.getOrgGxId() != null ? (sui.getOrgGxId())
					: 1) : 1;
			if(sui.getUserType() != null && sui.getUserType() == 6){
				SysSalesManEntity sysSalesManEntity = new SysSalesManEntity();
				sysSalesManEntity.setSalesAccount(sui.getAccount());
				sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
				att_MdDentistHospital.setSalesmanIds(sysSalesManEntity.getSalesmanId().toString());
				att_MdDentistHospital.setSalesName(sysSalesManEntity.getSalesName());
				if (rbsId == null) {
					att_MdDentistHospital.setVerifyState(1);
					att_MdDentistHospital.setState(2);
				}
			}
			if (att_MdDentistHospital.getVerifyState() != null && att_MdDentistHospital.getVerifyState() != 3) {
				att_MdDentistHospital.setState(2);
			}
			if(att_MdDentistHospital.getRbaId() != null){
				MdCompanyGroup mdCompanyGroup = mdCompanyGroupDao.findMdCompanyGroupById(att_MdDentistHospital.getRbaId());
				String salesIdStr = mdCompanyGroup.getSalesmanIds();
				if(salesIdStr == null || salesIdStr.equals("")){
					salesIdStr = "0";
				}
				new_salesId = Integer.parseInt(salesIdStr);
				att_MdDentistHospital.setSalesmanIds(mdCompanyGroup.getSalesmanIds());
				att_MdDentistHospital.setSalesName(mdCompanyGroup.getSalesName());

			}
			if (rbsId == null) {
				att_MdDentistHospital.setCreateDate(new Date());
				int id = mdDentistHospitalDao
						.saveMdDentistHospital(att_MdDentistHospital);
				SysOrgGx sysOrgGx = new SysOrgGx();
				sysOrgGx.setOldCode(att_MdDentistHospital.getRbsCode());
				sysOrgGx.setOldId(id);
				sysOrgGx.setOrganizaType("20002");
				sysOrgGx.setNodeName(att_MdDentistHospital.getRbsName());
				sysOrgGx.setMxxOrgGxId((orgid == 1) ? att_mxxOrgGxId : orgid);
				orgDao.saveOrgInfo(sysOrgGx);
			}else if(att_MdDentistHospital.getState()==2){
				//查看医院下是否有未完成的订单  
				   MdOrderInfo mdOrderInfo = new MdOrderInfo();
				   mdOrderInfo.setProcessStatus_str("1,2,3,4");
				   mdOrderInfo.setOrderState("1");
				   mdOrderInfo.setPurchaseType("2");
				   mdOrderInfo.setRbsId(rbsId);
				   List<MdOrderInfo> orderList = mdOrderInfoDao.getListByMdOrderInfo(mdOrderInfo);
				   if(orderList != null && orderList.size() > 0){
					   srm.setCode(0l);
					   srm.setMeg("该医院下有未完成的订单，不允许删除！");
					   return srm;
				   }
				   //查看该集团下是否有门诊存在 
					SysOrgGx sysOrgGx = new SysOrgGx();
					sysOrgGx.setOldId(rbsId);
					sysOrgGx.setOrganizaType("20002");
					List<SysOrgGx> list_t = this.getList(sysOrgGx);
					if(list_t!= null && list_t.size() > 0){
						sysOrgGx = list_t.get(0);
						SysOrgGx sog = new SysOrgGx();
						sog.setOrgGxId(sysOrgGx.getOrgGxId());
						sog.setOrganizaType("20003");
						String rbbIds = this.orgDao.getOldIdStrByParent(sog);
						if(rbbIds != null && !rbbIds.equals("")){
							MdDentalClinic mdDentalClinic = new MdDentalClinic();
							mdDentalClinic.setState(1);
							mdDentalClinic.setRbbId_str(rbbIds);
							List<MdDentalClinic> hospitalList = mdDentalClinicDao.getListByMdDentalClinic(mdDentalClinic);
							if(hospitalList != null && hospitalList.size() > 0){
								   srm.setCode(0l);
								   srm.setMeg("该集团下有门诊信息，不允许删除！");
								   return srm;
							   }
						}
					}
			}

			MdDentistHospital old_mdDentalClinic = mdDentistHospitalDao.findMdDentistHospitalById(rbsId);
			Integer old_salesId = null;
			if(old_mdDentalClinic != null){
				old_salesId = Integer.parseInt(old_mdDentalClinic.getSalesmanIds() == null ? "0" : old_mdDentalClinic.getSalesmanIds());
			}

			mdDentistHospitalDao.saveOrUpdateMdDentistHospital(att_MdDentistHospital);
			srm.setObj(att_MdDentistHospital);
			if(sui.getOrganizaType().equals("0") || sui.getOrganizaType().equals("200")) {
				rbsId = att_MdDentistHospital.getRbsId();
				updateSalesMan(new_salesId, old_salesId, rbsId.toString());
				updateCompanyGroupSalesMan(new_salesId, att_MdDentistHospital.getSalesName(), rbsId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
		return srm;
	}

	/**
	 * 创建或者更新时，绑定组织下其他组织业务员
	 * @param salesmanId
	 * @param salesName
	 * @param rbsId
	 */
	private void updateCompanyGroupSalesMan(Integer salesmanId, String salesName, Integer rbsId) {
		try {
			//查看该集团下是否有门诊存在
			//查看该集团下是否有门诊存在
			SysOrgGx sysOrgGx = new SysOrgGx();
			sysOrgGx.setOldId(rbsId);
			sysOrgGx.setOrganizaType("20002");
			List<SysOrgGx> list_t = this.getList(sysOrgGx);
			if(list_t!= null && list_t.size() > 0){
				sysOrgGx = list_t.get(0);
				SysOrgGx sog = new SysOrgGx();
				sog.setOrgGxId(sysOrgGx.getOrgGxId());
				sog.setOrganizaType("20003");
				String rbbIds = this.orgDao.getOldIdStrByParent(sog);
				if(rbbIds != null && !rbbIds.equals("")){
					MdDentalClinic mdDentalClinic = new MdDentalClinic();
					mdDentalClinic.setState(1);
					mdDentalClinic.setRbbId_str(rbbIds);
					List<MdDentalClinic> hospitalList = mdDentalClinicDao.getListByMdDentalClinic(mdDentalClinic);
					for (MdDentalClinic mdDentalClinic1 : hospitalList){
						String salesmanIdStr = mdDentalClinic1.getSalesmanIds();
//						if(salesmanIdStr != null && !salesmanIdStr.equals("")){
//							continue;
//						}
						salesmanIdStr = salesmanId.toString();
						mdDentalClinic1.setSalesmanIds(salesmanIdStr);
						mdDentalClinic1.setSalesName(salesName);
						mdDentalClinicDao.saveOrUpdateMdDentalClinic(mdDentalClinic1);
					}
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新组织下的业务员
	 * @param salesmanId
	 * @param old_salesId
	 * @param rbsId
	 */
	private void updateSalesMan(Integer salesmanId, Integer old_salesId, String rbsId) {
		//修改绑定的业务员
		try {

			SysSalesManEntity sysSalesManEntity = null;
			if (old_salesId != null && old_salesId != 0) {
				//解除旧业务员的绑定
				sysSalesManEntity = new SysSalesManEntity();
				sysSalesManEntity.setSalesmanId(old_salesId);
				sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
				String rbsIds = sysSalesManEntity.getRbaId();
				if(rbsIds != null) {
					rbsIds = rbsIds.replace(rbsId, "");
					rbsIds = rbsId.replace(",,", ",");
					sysSalesManEntity.setRbsId(rbsIds);
				}
				sysSalesmanDao.saveSalesman(sysSalesManEntity);
			}
			if (salesmanId != null) {
				//添加新的业务员
				if (salesmanId != old_salesId) {
					sysSalesManEntity = new SysSalesManEntity();
					sysSalesManEntity.setSalesmanId(salesmanId);
					sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
					String rbsIds = sysSalesManEntity.getRbbId();
					if (rbsIds != null) {
						rbsIds = rbsIds.replace(rbsId, "");
						rbsIds = rbsIds.replace(",,", ",");
					} else {
						rbsIds = "";
					}
					rbsIds += rbsId + ",";
					sysSalesManEntity.setRbsId(rbsIds);
					sysSalesmanDao.saveSalesman(sysSalesManEntity);
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
	}
	
	 
	/**
	 *根据MdDentistHospital对象作为对(md_dentist_hospital表进行查询，获取分页对象
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdDentistHospital att_MdDentistHospital) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdDentistHospital>());
			  try{
				  SysUserInfo account = this.GetOneSessionAccount();
				  if(account.getUserType() != null && account.getUserType() == 6){
					  SysSalesManEntity sysSalesManEntity = new SysSalesManEntity();
					  sysSalesManEntity.setSalesAccount(account.getAccount());
					  sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
					  att_MdDentistHospital.setSalesmanIds(sysSalesManEntity.getSalesmanId().toString());
					  att_MdDentistHospital.setSalesName(sysSalesManEntity.getSalesName());
				  }
				  if(att_MdDentistHospital.getState()==null)
					  att_MdDentistHospital.setState_str("1,2");
				  att_MdDentistHospital.setTab_like("rbsCode,rbsName");
				  att_MdDentistHospital.setTab_order("rbsId desc");
				  String orgIdStr = this.getOrgIdStr();
				  if(orgIdStr != null && !orgIdStr.equals("")){
					  att_MdDentistHospital.setRbsId_str(orgIdStr);
					  pm=mdDentistHospitalDao.getPagerModelByMdDentistHospital(att_MdDentistHospital);
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
				sog.setOrgGxId(5);
			else
				sog.setOrgGxId(account.getOrgGxId());
			sog.setOrganizaType("20002");
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
			MdDentistHospital mdDentistHospital = mdDentistHospitalDao.findMdDentistHospitalById(id);
			if (mdDentistHospital != null) {
				mdDentistHospital.setVerifyState(verifyState);
				Integer state = 2;
				if (verifyState == 3){
					state = 1;
				}
				mdDentistHospital.setState(state);
				if (verifyRemark != null && !verifyRemark.equals("")) {
					mdDentistHospital.setVerifyRemark(verifyRemark);
				}
				mdDentistHospitalDao.updateMdDentistHospitalById(mdDentistHospital.getRbsId(), mdDentistHospital);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sm.setCode(2l);
//			throw new HSKException(e);
		}
		return sm;
	}
}