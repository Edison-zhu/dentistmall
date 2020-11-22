package com.hsk.dentistmall.web.business.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hsk.xframe.api.daobbase.ISysSalesmanDao;
import com.hsk.xframe.api.persistence.SysSalesManEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.api.daobbase.IMdCompanyGroupDao;
import com.hsk.dentistmall.api.daobbase.IMdDentalClinicDao;
import com.hsk.dentistmall.api.daobbase.IMdDentistHospitalDao;
import com.hsk.dentistmall.api.daobbase.IMdOrderInfoDao;
import com.hsk.dentistmall.api.persistence.MdCompanyGroup;
import com.hsk.dentistmall.api.persistence.MdDentalClinic;
import com.hsk.dentistmall.api.persistence.MdDentistHospital;
import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.dentistmall.web.business.service.IMdCompanyGroupService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.IorgDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;


/** 
  business业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:16
 */
 
@Service
public class  MdCompanyGroupService extends DSTService implements IMdCompanyGroupService {	
   /**
   *业务处理dao类  mdCompanyGroupDao 
   */
	@Autowired
	protected IMdCompanyGroupDao mdCompanyGroupDao;
	@Autowired
	protected IMdDentistHospitalDao mdDentistHospitalDao;
	@Autowired
	protected IMdDentalClinicDao mdDentalClinicDao;
	
	@Autowired
	protected IorgDao  orgDao;
	@Autowired
	protected IMdOrderInfoDao mdOrderInfoDao;
	@Autowired
	protected ISysSalesmanDao sysSalesmanDao;


 /**
	 * 根据md_company_group表主键删除MdCompanyGroup对象记录
     * @param  rbaId  Integer类型(md_company_group表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer rbaId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);
			  	
			try{
		   			MdCompanyGroup     att_MdCompanyGroup= mdCompanyGroupDao.findMdCompanyGroupById( rbaId) ;
					srm.setObj(att_MdCompanyGroup);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	} 

    /**
	 * 根据md_company_group表主键删除MdCompanyGroup对象记录
     * @param  rbaId  Integer类型(md_company_group表主键)
	 * @throws HSKException
	 */

	public MdCompanyGroup findObject(Integer rbaId) throws HSKException{
			MdCompanyGroup  att_MdCompanyGroup=new MdCompanyGroup();		
			try{
				if(rbaId != null)
					att_MdCompanyGroup= mdCompanyGroupDao.findMdCompanyGroupById( rbaId) ;
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdCompanyGroup;
	}
	
	 
	private void deleteOrgId(Integer rbaId)throws HSKException{
		//删除 组织id
		 SysOrgGx sysOrgGx=new SysOrgGx ();
		 sysOrgGx.setOldId(rbaId); 
		 sysOrgGx.setOrganizaType("20001");
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
	 * 根据md_company_group表主键删除MdCompanyGroup对象记录
     * @param  rbaId  Integer类型(md_company_group表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer rbaId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
			   //查看集团下是否有未完成的订单  
			   MdOrderInfo mdOrderInfo = new MdOrderInfo();
			   mdOrderInfo.setProcessStatus_str("1,2,3,4");
			   mdOrderInfo.setOrderState("1");
			   mdOrderInfo.setPurchaseType("1");
			   mdOrderInfo.setRbaId(rbaId);
			   List<MdOrderInfo> orderList = mdOrderInfoDao.getListByMdOrderInfo(mdOrderInfo);
			   if(orderList != null && orderList.size() > 0){
				   srm.setCode(0l);
				   srm.setMeg("该集团下有未完成的订单，不允许删除！");
				   return srm;
			   }
			   MdCompanyGroup     att_MdCompanyGroup= mdCompanyGroupDao.findMdCompanyGroupById( rbaId) ;
			   //查看该集团下是否有医院、门诊存在 
				SysOrgGx sysOrgGx = new SysOrgGx();
				sysOrgGx.setOldId(att_MdCompanyGroup.getRbaId());
				sysOrgGx.setOrganizaType("20001");
				List<SysOrgGx> list_t = this.getList(sysOrgGx);
				if(list_t!= null && list_t.size() > 0){
					sysOrgGx = list_t.get(0);
					SysOrgGx sog = new SysOrgGx();
					sog.setOrgGxId(sysOrgGx.getOrgGxId());
					sog.setOrganizaType("20002");
					String rbsIds = this.orgDao.getOldIdStrByParent(sog);
					if(rbsIds != null && !rbsIds.equals("")){
						MdDentistHospital mdDentistHospital = new MdDentistHospital();
						mdDentistHospital.setState(1);
						mdDentistHospital.setRbsId_str(rbsIds);
						List<MdDentistHospital> hospitalList = mdDentistHospitalDao.getListByMdDentistHospital(mdDentistHospital);
						if(hospitalList != null && hospitalList.size() > 0){
							   srm.setCode(0l);
							   srm.setMeg("该集团下有医院信息，不允许删除！");
							   return srm;
						   }
					}
					
					sog = new SysOrgGx();
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
				att_MdCompanyGroup.setState(0);
				mdCompanyGroupDao.saveOrUpdateMdCompanyGroup(att_MdCompanyGroup);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}
	
	/**
	 * 根据md_company_group表主键删除多条MdCompanyGroup对象记录
     * @param  RbaIds  Integer类型(md_company_group表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String rbaIds) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = rbaIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					Integer rbaId = Integer.valueOf(did);
					mdCompanyGroupDao.deleteMdCompanyGroupById(rbaId);
					deleteOrgId(rbaId);
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
	 
    private  String  att_level="1";
    private Integer att_mxxOrgGxId=4;
	 /**
	 * 新增或修改md_company_group表记录 ,如果md_company_group表主键MdCompanyGroup.RbaId为空就是添加，如果非空就是修改
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
     * @return MdCompanyGroup  修改后的MdCompanyGroup对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject(MdCompanyGroup att_MdCompanyGroup, String listFilecode)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			Integer orgid = 1;
			String saleIdStr = att_MdCompanyGroup.getSalesmanIds();
			if(saleIdStr == null || saleIdStr.equals("")){
				saleIdStr = "0";
			}
			Integer new_salesId = Integer.parseInt(saleIdStr);
			Integer rbaId = att_MdCompanyGroup.getRbaId();
			SysUserInfo sui = this.GetOneSessionAccount();
			orgid = (sui != null) ? (sui.getOrgGxId() != null ? (sui.getOrgGxId())
					: 1) : 1;
			if(sui.getUserType() != null && sui.getUserType() == 6){
				SysSalesManEntity sysSalesManEntity = new SysSalesManEntity();
				sysSalesManEntity.setSalesAccount(sui.getAccount());
				sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
				att_MdCompanyGroup.setSalesmanIds(sysSalesManEntity.getSalesmanId().toString());
				att_MdCompanyGroup.setSalesName(sysSalesManEntity.getSalesName());
				if (rbaId == null ) {
					att_MdCompanyGroup.setVerifyState(1);
					att_MdCompanyGroup.setState(2);
				}
			}

			if (att_MdCompanyGroup.getVerifyState() != null && att_MdCompanyGroup.getVerifyState() != 3) {
				att_MdCompanyGroup.setState(2);
			}

			if (rbaId == null) {
				att_MdCompanyGroup.setCreateDate(new Date());
				int id = mdCompanyGroupDao
						.saveMdCompanyGroup(att_MdCompanyGroup);
				SysOrgGx sysOrgGx = new SysOrgGx();
				sysOrgGx.setOldCode(att_MdCompanyGroup.getRbaCode());
				sysOrgGx.setOldId(id);
				sysOrgGx.setOrganizaType("20001");
				sysOrgGx.setNodeName(att_MdCompanyGroup.getRbaName());
				if (att_MdCompanyGroup.getLevel() != null
						&& !"".equals(att_MdCompanyGroup.getLevel())
						&& !att_level.equals(att_MdCompanyGroup.getLevel())) {
//					sysOrgGx.setMxxOrgGxId(att_MdCompanyGroup.getLevelId());
					sysOrgGx.setMxxOrgGxId((orgid == 1) ? att_mxxOrgGxId
							: orgid);
				}else{
					sysOrgGx.setMxxOrgGxId((orgid == 1) ? att_mxxOrgGxId
							: orgid);
				}
				orgDao.saveOrgInfo(sysOrgGx);
			}else if(att_MdCompanyGroup.getState()==2){
				//查看集团下是否有未完成的订单  
				   MdOrderInfo mdOrderInfo = new MdOrderInfo();
				   mdOrderInfo.setProcessStatus_str("1,2,3,4");
				   mdOrderInfo.setOrderState("1");
				   mdOrderInfo.setPurchaseType("1");
				   mdOrderInfo.setRbaId(rbaId);
				   List<MdOrderInfo> orderList = mdOrderInfoDao.getListByMdOrderInfo(mdOrderInfo);
				   if(orderList != null && orderList.size() > 0){
					   srm.setCode(0l);
					   srm.setMeg("该集团下有未完成的订单，不允许设置为禁用状态！");
					   return srm;
				   }
				//查看该集团下是否有医院、门诊存在
				SysOrgGx sysOrgGx = new SysOrgGx();
				sysOrgGx.setOldId(rbaId);
				sysOrgGx.setOrganizaType("20001");
				List<SysOrgGx> list_t = this.getList(sysOrgGx);
				if (list_t != null && list_t.size() > 0) {
					sysOrgGx = list_t.get(0);
					SysOrgGx sog = new SysOrgGx();
					sog.setOrgGxId(sysOrgGx.getOrgGxId());
					sog.setOrganizaType("20002");
					String rbsIds = this.orgDao.getOldIdStrByParent(sog);
					if (rbsIds != null && !rbsIds.equals("")) {
						MdDentistHospital mdDentistHospital = new MdDentistHospital();
						mdDentistHospital.setState(1);
						mdDentistHospital.setRbsId_str(rbsIds);
						List<MdDentistHospital> hospitalList = mdDentistHospitalDao.getListByMdDentistHospital(mdDentistHospital);
						if (hospitalList != null && hospitalList.size() > 0) {
							srm.setCode(0l);
							srm.setMeg("该集团下有医院信息，不允许设置为禁用状态！");
							return srm;
						}
					}

					sog = new SysOrgGx();
					sog.setOrgGxId(sysOrgGx.getOrgGxId());
					sog.setOrganizaType("20003");
					String rbbIds = this.orgDao.getOldIdStrByParent(sog);
					if (rbbIds != null && !rbbIds.equals("")) {
						MdDentalClinic mdDentalClinic = new MdDentalClinic();
						mdDentalClinic.setState(1);
						mdDentalClinic.setRbbId_str(rbbIds);
						List<MdDentalClinic> hospitalList = mdDentalClinicDao.getListByMdDentalClinic(mdDentalClinic);
						if (hospitalList != null && hospitalList.size() > 0) {
							srm.setCode(0l);
							srm.setMeg("该集团下有门诊信息，不允许设置为禁用状态！");
							return srm;
						}
					}
				}
			}

			MdCompanyGroup old_mdDentalClinic = mdCompanyGroupDao.findMdCompanyGroupById(rbaId);
			Integer old_salesId = null;
			if(old_mdDentalClinic != null){
				old_salesId = Integer.parseInt((old_mdDentalClinic.getSalesmanIds() == null || old_mdDentalClinic.getSalesmanIds().equals("")) ? "0" : old_mdDentalClinic.getSalesmanIds());
			}

			if(listFilecode != null){
				String[] fileCodeArray = listFilecode.trim().split(",");
				if(fileCodeArray.length >= 1 && fileCodeArray[0] != null && !fileCodeArray[0].equals("")){
					att_MdCompanyGroup.setLogo(fileCodeArray[0]);
				}
			}

			mdCompanyGroupDao.saveOrUpdateMdCompanyGroup(att_MdCompanyGroup);
			srm.setObj(att_MdCompanyGroup);
			if(sui.getOrganizaType().equals("0") || sui.getOrganizaType().equals("200")) {
				rbaId = att_MdCompanyGroup.getRbaId();
				updateSalesMan(new_salesId, old_salesId, rbaId.toString());
				updateCompanyGroupSalesMan(new_salesId, att_MdCompanyGroup.getSalesName(), rbaId);
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
	 * @param rbaId
	 */
	private void updateCompanyGroupSalesMan(Integer salesmanId, String salesName, Integer rbaId) {
		try {
			//查看该集团下是否有医院、门诊存在
			SysOrgGx sysOrgGx = new SysOrgGx();
			sysOrgGx.setOldId(rbaId);
			sysOrgGx.setOrganizaType("20001");
			List<SysOrgGx> list_t = null;
			list_t = this.getList(sysOrgGx);
			if (list_t != null && list_t.size() > 0) {
				sysOrgGx = list_t.get(0);
				SysOrgGx sog = new SysOrgGx();
				sog.setOrgGxId(sysOrgGx.getOrgGxId());
				sog.setOrganizaType("20002");
				String rbsIds = this.orgDao.getOldIdStrByParent(sog);
				if (rbsIds != null && !rbsIds.equals("")) {
					MdDentistHospital mdDentistHospital = new MdDentistHospital();
					mdDentistHospital.setState(1);
					mdDentistHospital.setRbsId_str(rbsIds);
					List<MdDentistHospital> hospitalList = mdDentistHospitalDao.getListByMdDentistHospital(mdDentistHospital);
					for (MdDentistHospital mdDentistHospital1 : hospitalList){
						String salesmanIdStr = mdDentistHospital.getSalesmanIds();
//						if(salesmanIdStr != null && !salesmanIdStr.equals("")){
//							continue;
//						}
						salesmanIdStr = salesmanId.toString();
						mdDentistHospital1.setSalesmanIds(salesmanIdStr);
						mdDentistHospital1.setSalesName(salesName);
						mdDentistHospitalDao.saveOrUpdateMdDentistHospital(mdDentistHospital1);
					}
				}
				sog = new SysOrgGx();
				sog.setOrgGxId(sysOrgGx.getOrgGxId());
				sog.setOrganizaType("20003");
				String rbbIds = this.orgDao.getOldIdStrByParent(sog);
				if (rbbIds != null && !rbbIds.equals("")) {
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
	 * @param rbaId
	 */
	private void updateSalesMan(Integer salesmanId, Integer old_salesId, String rbaId) {
		//修改绑定的业务员
		try {

			SysSalesManEntity sysSalesManEntity = null;
			if (old_salesId != null && old_salesId != 0) {
				//解除旧业务员的绑定
				sysSalesManEntity = new SysSalesManEntity();
				sysSalesManEntity.setSalesmanId(old_salesId);
				sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
				String rbaIds = sysSalesManEntity.getRbaId();
				if(rbaIds != null) {
					rbaIds = rbaIds.replace(rbaId, "");
					rbaIds = rbaIds.replace(",,", ",");
					sysSalesManEntity.setRbaId(rbaIds);
				}
				sysSalesmanDao.saveSalesman(sysSalesManEntity);
			}
			if (salesmanId != null) {
				//添加新的业务员
				if (salesmanId != old_salesId) {
					sysSalesManEntity = new SysSalesManEntity();
					sysSalesManEntity.setSalesmanId(salesmanId);
					sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
					String rbaIds = sysSalesManEntity.getRbbId();
					if(rbaIds != null) {
						rbaIds = rbaIds.replace(rbaId, "");
						rbaIds = rbaIds.replace(",,", ",");
					} else {
						rbaIds = "";
					}

					rbaIds += rbaId + ",";
					sysSalesManEntity.setRbaId(rbaIds);
					sysSalesmanDao.saveSalesman(sysSalesManEntity);
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
	}
	 
	/**
	 *根据MdCompanyGroup对象作为对(md_company_group表进行查询，获取分页对象
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdCompanyGroup att_MdCompanyGroup) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdCompanyGroup>());
			  try{
			  	SysUserInfo account = this.GetOneSessionAccount();
			  	if(account.getUserType() != null && account.getUserType() == 6){
			  		SysSalesManEntity sysSalesManEntity = new SysSalesManEntity();
			  		sysSalesManEntity.setSalesAccount(account.getAccount());
					sysSalesManEntity = sysSalesmanDao.getObject(sysSalesManEntity);
					att_MdCompanyGroup.setSalesmanIds(sysSalesManEntity.getSalesmanId().toString());
					att_MdCompanyGroup.setSalesName(sysSalesManEntity.getSalesName());
				}
				  if(att_MdCompanyGroup.getState()==null)
					  att_MdCompanyGroup.setState_str("1,2");
				  att_MdCompanyGroup.setTab_like("rbaCode,rbaName");
				  att_MdCompanyGroup.setTab_order("rbaId desc");
				  att_MdCompanyGroup.setRbaId_str(this.getOrgIdStr());
					pm=mdCompanyGroupDao.getPagerModelByMdCompanyGroup(att_MdCompanyGroup);
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
				sog.setOrgGxId(4);
			else
				sog.setOrgGxId(account.getOrgGxId());
			sog.setOrganizaType("20001");
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
			MdCompanyGroup mdCompanyGroup = mdCompanyGroupDao.findMdCompanyGroupById(id);
			if (mdCompanyGroup != null) {
				mdCompanyGroup.setVerifyState(verifyState);
				Integer state = 2;
				if (verifyState == 3){
					state = 1;
				}
				mdCompanyGroup.setState(state);
				if (verifyRemark != null && !verifyRemark.equals("")) {
					mdCompanyGroup.setVerifyRemark(verifyRemark);
				}
				mdCompanyGroupDao.updateMdCompanyGroupById(mdCompanyGroup.getRbaId(), mdCompanyGroup);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sm.setCode(2l);
//			throw new HSKException(e);
		}
		return sm;
	}
}