package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.util.*;
import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_company_group表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:16
 */
@Component
public class MdCompanyGroupApiDao extends SupperDao implements IMdCompanyGroupApiDao {

	/**
	 * 根据md_company_group表主键查找MdCompanyGroup对象记录
	 * 
	 * @param  RbaId  Integer类型(md_company_group表主键)
	 * @return MdCompanyGroup md_company_group表记录
	 * @throws HSKDBException
	 */	
	public MdCompanyGroup findMdCompanyGroupById(Integer RbaId) throws HSKDBException{
				MdCompanyGroup  att_MdCompanyGroup=new MdCompanyGroup();				
				if(RbaId!=null){
					att_MdCompanyGroup.setRbaId(RbaId);	
				    Object obj=	this.getOne(att_MdCompanyGroup);
					if(obj!=null){
						att_MdCompanyGroup=(MdCompanyGroup) obj;
					}
				}
				return  att_MdCompanyGroup;
	}
	 /**
	  * 根据md_company_group表主键删除MdCompanyGroup对象记录
	  * @param  RbaId  Integer类型(md_company_group表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdCompanyGroupById(Integer RbaId) throws HSKDBException{ 
		 MdCompanyGroup  att_MdCompanyGroup=new MdCompanyGroup();	
		  if(RbaId!=null){
					  att_MdCompanyGroup.setRbaId(RbaId);
				  	  Object obj=	this.getOne(att_MdCompanyGroup);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_company_group表主键修改MdCompanyGroup对象记录
     * @param  RbaId  Integer类型(md_company_group表主键)
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
     * @return MdCompanyGroup  修改后的MdCompanyGroup对象记录
	 * @throws HSKDBException
	 */
	public  MdCompanyGroup updateMdCompanyGroupById(Integer RbaId,MdCompanyGroup att_MdCompanyGroup) throws HSKDBException{
		  if(RbaId!=null){
					att_MdCompanyGroup.setRbaId(RbaId);
				   	Object obj=	this.getOne(att_MdCompanyGroup);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdCompanyGroup;
	}
	
	/**
	 * 新增md_company_group表 记录
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdCompanyGroup(MdCompanyGroup att_MdCompanyGroup) throws HSKDBException{
		 return this.newObject(att_MdCompanyGroup);
	} 
		
	/**
	 * 新增或修改md_company_group表记录 ,如果md_company_group表主键MdCompanyGroup.RbaId为空就是添加，如果非空就是修改
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
	 * @throws HSKDBException
	 */
	public  MdCompanyGroup saveOrUpdateMdCompanyGroup(MdCompanyGroup att_MdCompanyGroup) throws HSKDBException{
		att_MdCompanyGroup = (MdCompanyGroup)getHibernatesession().merge(att_MdCompanyGroup);
		  this.getHibernateTemplate().saveOrUpdate(att_MdCompanyGroup);
		  return att_MdCompanyGroup;
	}
	
	/**
	 *根据MdCompanyGroup对象作为对(md_company_group表进行查询，获取列表对象
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
     * @return List<MdCompanyGroup>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdCompanyGroup> getListByMdCompanyGroup(MdCompanyGroup att_MdCompanyGroup) throws HSKDBException{
		String Hql=this.getHql( att_MdCompanyGroup); 
		List<MdCompanyGroup> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	/**
	 *根据MdCompanyGroup对象作为对(md_company_group表进行查询，获取分页对象
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
     * @return List<MdCompanyGroup>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdCompanyGroup(MdCompanyGroup att_MdCompanyGroup)
			throws HSKDBException {
		String Hql=this.getHql(att_MdCompanyGroup);
		return this.getHibernateDao().findByPage(Hql); 
	} 
	/**
	 * 根据MdCompanyGroup对象获取Hql字符串
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
	 *  @return hql字符串
	 */
	private String getHql(MdCompanyGroup att_MdCompanyGroup) {
		StringBuffer sbuffer = new StringBuffer(" from  MdCompanyGroup  where  1=1  ");
		String likeStr = att_MdCompanyGroup.getTab_like();
		String orderStr = att_MdCompanyGroup.getTab_order();

		//*****************无关字段处理begin*****************
		//处理in条件 集团公司id(rbaId)
		if (att_MdCompanyGroup.getRbaId_str() != null &&
				!"".equals(att_MdCompanyGroup.getRbaId_str().trim())) {
			String intStr = att_MdCompanyGroup.getRbaId_str().trim();
			String[] arrayStr = intStr.split(",");

			if (arrayStr.length > 0) {
				sbuffer.append(" and ( ");
				for (int i = 0; i < arrayStr.length; i++) {
					String did = arrayStr[i];
					if (i == arrayStr.length - 1) {
						sbuffer.append("  rbaId=" + did + "   ");
					} else {
						sbuffer.append("  rbaId=" + did + " or ");
					}
				}
				sbuffer.append(" ) ");
			}

		}
		//处理in条件 流程状态(1.待经理审核，2经理审核不通过，3经理审核通过)(flowState)
		if (att_MdCompanyGroup.getFlowState_str() != null &&
				!"".equals(att_MdCompanyGroup.getFlowState_str().trim())) {
			String intStr = att_MdCompanyGroup.getFlowState_str().trim();
			String[] arrayStr = intStr.split(",");

			if (arrayStr.length > 0) {
				sbuffer.append(" and ( ");
				for (int i = 0; i < arrayStr.length; i++) {
					String did = arrayStr[i];
					if (i == arrayStr.length - 1) {
						sbuffer.append("  flowState=" + did + "   ");
					} else {
						sbuffer.append("  flowState=" + did + " or ");
					}
				}
				sbuffer.append(" ) ");
			}

		}
		//处理in条件 状态:1正常，2删除(state)
		if (att_MdCompanyGroup.getState_str() != null &&
				!"".equals(att_MdCompanyGroup.getState_str().trim())) {
			String intStr = att_MdCompanyGroup.getState_str().trim();
			String[] arrayStr = intStr.split(",");

			if (arrayStr.length > 0) {
				sbuffer.append(" and ( ");
				for (int i = 0; i < arrayStr.length; i++) {
					String did = arrayStr[i];
					if (i == arrayStr.length - 1) {
						sbuffer.append("  state=" + did + "   ");
					} else {
						sbuffer.append("  state=" + did + " or ");
					}
				}
				sbuffer.append(" ) ");
			}

		}
		//时间类型开始条件处理  创建时间(createDate)
		if (att_MdCompanyGroup.getCreateDate_start() != null) {
			sbuffer.append(" and  createDate<='" + att_MdCompanyGroup.getCreateDate_start() + "'");
		}
		//时间类型结束条件处理 创建时间(createDate)
		if (att_MdCompanyGroup.getCreateDate_end() != null) {
			sbuffer.append(" and  createDate>'" + att_MdCompanyGroup.getCreateDate_end() + "'");
		}
		//时间类型开始条件处理  修改时间(editDate)
		if (att_MdCompanyGroup.getEditDate_start() != null) {
			sbuffer.append(" and  editDate<='" + att_MdCompanyGroup.getEditDate_start() + "'");
		}
		//时间类型结束条件处理 修改时间(editDate)
		if (att_MdCompanyGroup.getEditDate_end() != null) {
			sbuffer.append(" and  editDate>'" + att_MdCompanyGroup.getEditDate_end() + "'");
		}
		//*****************无关字段处理end*****************
		//*****************数据库字段处理begin*************
		//集团公司id(rbaId)
		if (att_MdCompanyGroup.getRbaId() != null) {
			sbuffer.append(" and rbaId=" + att_MdCompanyGroup.getRbaId());
		}
		//集团公司编号(rbaCode)
		if (att_MdCompanyGroup.getRbaCode() != null &&
				!"".equals(att_MdCompanyGroup.getRbaCode().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("rbaCode") != -1) {
				sbuffer.append(" and rbaCode  like '%" + att_MdCompanyGroup.getRbaCode().toUpperCase() + "%'");
			} else {
				sbuffer.append(" and rbaCode   ='" + att_MdCompanyGroup.getRbaCode() + "'");
			}
		}
		//集团公司名称(rbaName)
		if (att_MdCompanyGroup.getRbaName() != null &&
				!"".equals(att_MdCompanyGroup.getRbaName().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("rbaName") != -1) {
				sbuffer.append(" and rbaName  like '%" + att_MdCompanyGroup.getRbaName() + "%'");
			} else {
				sbuffer.append(" and rbaName   ='" + att_MdCompanyGroup.getRbaName() + "'");
			}
		}
		//集团公司级别(level)
		if (att_MdCompanyGroup.getLevel() != null &&
				!"".equals(att_MdCompanyGroup.getLevel().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("level") != -1) {
				sbuffer.append(" and level  like '%" + att_MdCompanyGroup.getLevel() + "%'");
			} else {
				sbuffer.append(" and level   ='" + att_MdCompanyGroup.getLevel() + "'");
			}
		}
		//公司所在地址(address)
		if (att_MdCompanyGroup.getAddress() != null &&
				!"".equals(att_MdCompanyGroup.getAddress().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("address") != -1) {
				sbuffer.append(" and address  like '%" + att_MdCompanyGroup.getAddress() + "%'");
			} else {
				sbuffer.append(" and address   ='" + att_MdCompanyGroup.getAddress() + "'");
			}
		}
		//省(province)
		if (att_MdCompanyGroup.getProvince() != null &&
				!"".equals(att_MdCompanyGroup.getProvince().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("province") != -1) {
				sbuffer.append(" and province  like '%" + att_MdCompanyGroup.getProvince() + "%'");
			} else {
				sbuffer.append(" and province   ='" + att_MdCompanyGroup.getProvince() + "'");
			}
		}
		//市(city)
		if (att_MdCompanyGroup.getCity() != null &&
				!"".equals(att_MdCompanyGroup.getCity().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("city") != -1) {
				sbuffer.append(" and city  like '%" + att_MdCompanyGroup.getCity() + "%'");
			} else {
				sbuffer.append(" and city   ='" + att_MdCompanyGroup.getCity() + "'");
			}
		}
		//地区(area)
		if (att_MdCompanyGroup.getArea() != null &&
				!"".equals(att_MdCompanyGroup.getArea().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("area") != -1) {
				sbuffer.append(" and area  like '%" + att_MdCompanyGroup.getArea() + "%'");
			} else {
				sbuffer.append(" and area   ='" + att_MdCompanyGroup.getArea() + "'");
			}
		}
		//负责人(personName)
		if (att_MdCompanyGroup.getPersonName() != null &&
				!"".equals(att_MdCompanyGroup.getPersonName().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("personName") != -1) {
				sbuffer.append(" and personName  like '%" + att_MdCompanyGroup.getPersonName() + "%'");
			} else {
				sbuffer.append(" and personName   ='" + att_MdCompanyGroup.getPersonName() + "'");
			}
		}
		//电话(phoneNumber)
		if (att_MdCompanyGroup.getPhoneNumber() != null &&
				!"".equals(att_MdCompanyGroup.getPhoneNumber().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("phoneNumber") != -1) {
				sbuffer.append(" and phoneNumber  like '%" + att_MdCompanyGroup.getPhoneNumber() + "%'");
			} else {
				sbuffer.append(" and phoneNumber   ='" + att_MdCompanyGroup.getPhoneNumber() + "'");
			}
		}
		//邮箱(email)
		if (att_MdCompanyGroup.getEmail() != null &&
				!"".equals(att_MdCompanyGroup.getEmail().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("email") != -1) {
				sbuffer.append(" and email  like '%" + att_MdCompanyGroup.getEmail() + "%'");
			} else {
				sbuffer.append(" and email   ='" + att_MdCompanyGroup.getEmail() + "'");
			}
		}
		//备注(remark)
		if (att_MdCompanyGroup.getRemark() != null &&
				!"".equals(att_MdCompanyGroup.getRemark().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("remark") != -1) {
				sbuffer.append(" and remark  like '%" + att_MdCompanyGroup.getRemark() + "%'");
			} else {
				sbuffer.append(" and remark   ='" + att_MdCompanyGroup.getRemark() + "'");
			}
		}
		//营业执照(businessNo)
		if (att_MdCompanyGroup.getBusinessNo() != null &&
				!"".equals(att_MdCompanyGroup.getBusinessNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("businessNo") != -1) {
				sbuffer.append(" and businessNo  like '%" + att_MdCompanyGroup.getBusinessNo() + "%'");
			} else {
				sbuffer.append(" and businessNo   ='" + att_MdCompanyGroup.getBusinessNo() + "'");
			}
		}
		//营业执照文件(businessFile)
		if (att_MdCompanyGroup.getBusinessFile() != null &&
				!"".equals(att_MdCompanyGroup.getBusinessFile().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("businessFile") != -1) {
				sbuffer.append(" and businessFile  like '%" + att_MdCompanyGroup.getBusinessFile() + "%'");
			} else {
				sbuffer.append(" and businessFile   ='" + att_MdCompanyGroup.getBusinessFile() + "'");
			}
		}
		//负责人身份证(personCertificateNo)
		if (att_MdCompanyGroup.getPersonCertificateNo() != null &&
				!"".equals(att_MdCompanyGroup.getPersonCertificateNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("personCertificateNo") != -1) {
				sbuffer.append(" and personCertificateNo  like '%" + att_MdCompanyGroup.getPersonCertificateNo() + "%'");
			} else {
				sbuffer.append(" and personCertificateNo   ='" + att_MdCompanyGroup.getPersonCertificateNo() + "'");
			}
		}
		//负责人身份证文件(personCertificateFile)
		if (att_MdCompanyGroup.getPersonCertificateFile() != null &&
				!"".equals(att_MdCompanyGroup.getPersonCertificateFile().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("personCertificateFile") != -1) {
				sbuffer.append(" and personCertificateFile  like '%" + att_MdCompanyGroup.getPersonCertificateFile() + "%'");
			} else {
				sbuffer.append(" and personCertificateFile   ='" + att_MdCompanyGroup.getPersonCertificateFile() + "'");
			}
		}
		//组织机构代码证(organizationNo)
		if (att_MdCompanyGroup.getOrganizationNo() != null &&
				!"".equals(att_MdCompanyGroup.getOrganizationNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("organizationNo") != -1) {
				sbuffer.append(" and organizationNo  like '%" + att_MdCompanyGroup.getOrganizationNo() + "%'");
			} else {
				sbuffer.append(" and organizationNo   ='" + att_MdCompanyGroup.getOrganizationNo() + "'");
			}
		}
		//组织机构代码证文件(organizationFile)
		if (att_MdCompanyGroup.getOrganizationFile() != null &&
				!"".equals(att_MdCompanyGroup.getOrganizationFile().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("organizationFile") != -1) {
				sbuffer.append(" and organizationFile  like '%" + att_MdCompanyGroup.getOrganizationFile() + "%'");
			} else {
				sbuffer.append(" and organizationFile   ='" + att_MdCompanyGroup.getOrganizationFile() + "'");
			}
		}
		//代理协议(agentAgreement)
		if (att_MdCompanyGroup.getAgentAgreement() != null &&
				!"".equals(att_MdCompanyGroup.getAgentAgreement().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("agentAgreement") != -1) {
				sbuffer.append(" and agentAgreement  like '%" + att_MdCompanyGroup.getAgentAgreement() + "%'");
			} else {
				sbuffer.append(" and agentAgreement   ='" + att_MdCompanyGroup.getAgentAgreement() + "'");
			}
		}
		//代理协议文件(agentFile)
		if (att_MdCompanyGroup.getAgentFile() != null &&
				!"".equals(att_MdCompanyGroup.getAgentFile().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("agentFile") != -1) {
				sbuffer.append(" and agentFile  like '%" + att_MdCompanyGroup.getAgentFile() + "%'");
			} else {
				sbuffer.append(" and agentFile   ='" + att_MdCompanyGroup.getAgentFile() + "'");
			}
		}
		//开户银行(bankName)
		if (att_MdCompanyGroup.getBankName() != null &&
				!"".equals(att_MdCompanyGroup.getBankName().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("bankName") != -1) {
				sbuffer.append(" and bankName  like '%" + att_MdCompanyGroup.getBankName() + "%'");
			} else {
				sbuffer.append(" and bankName   ='" + att_MdCompanyGroup.getBankName() + "'");
			}
		}
		//银行卡号(bankNo)
		if (att_MdCompanyGroup.getBankNo() != null &&
				!"".equals(att_MdCompanyGroup.getBankNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("bankNo") != -1) {
				sbuffer.append(" and bankNo  like '%" + att_MdCompanyGroup.getBankNo() + "%'");
			} else {
				sbuffer.append(" and bankNo   ='" + att_MdCompanyGroup.getBankNo() + "'");
			}
		}
		//开户人(bankUser)
		if (att_MdCompanyGroup.getBankUser() != null &&
				!"".equals(att_MdCompanyGroup.getBankUser().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("bankUser") != -1) {
				sbuffer.append(" and bankUser  like '%" + att_MdCompanyGroup.getBankUser() + "%'");
			} else {
				sbuffer.append(" and bankUser   ='" + att_MdCompanyGroup.getBankUser() + "'");
			}
		}
		//账号类型(bankType)
		if (att_MdCompanyGroup.getBankType() != null &&
				!"".equals(att_MdCompanyGroup.getBankType().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("bankType") != -1) {
				sbuffer.append(" and bankType  like '%" + att_MdCompanyGroup.getBankType() + "%'");
			} else {
				sbuffer.append(" and bankType   ='" + att_MdCompanyGroup.getBankType() + "'");
			}
		}
		//开户支行名称(branchName)
		if (att_MdCompanyGroup.getBranchName() != null &&
				!"".equals(att_MdCompanyGroup.getBranchName().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("branchName") != -1) {
				sbuffer.append(" and branchName  like '%" + att_MdCompanyGroup.getBranchName() + "%'");
			} else {
				sbuffer.append(" and branchName   ='" + att_MdCompanyGroup.getBranchName() + "'");
			}
		}
		//手机号(balancePhone)
		if (att_MdCompanyGroup.getBalancePhone() != null &&
				!"".equals(att_MdCompanyGroup.getBalancePhone().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("balancePhone") != -1) {
				sbuffer.append(" and balancePhone  like '%" + att_MdCompanyGroup.getBalancePhone() + "%'");
			} else {
				sbuffer.append(" and balancePhone   ='" + att_MdCompanyGroup.getBalancePhone() + "'");
			}
		}
		//证件类型(certificateType)
		if (att_MdCompanyGroup.getCertificateType() != null &&
				!"".equals(att_MdCompanyGroup.getCertificateType().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("certificateType") != -1) {
				sbuffer.append(" and certificateType  like '%" + att_MdCompanyGroup.getCertificateType() + "%'");
			} else {
				sbuffer.append(" and certificateType   ='" + att_MdCompanyGroup.getCertificateType() + "'");
			}
		}
		//证件号码(certificateNo)
		if (att_MdCompanyGroup.getCertificateNo() != null &&
				!"".equals(att_MdCompanyGroup.getCertificateNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("certificateNo") != -1) {
				sbuffer.append(" and certificateNo  like '%" + att_MdCompanyGroup.getCertificateNo() + "%'");
			} else {
				sbuffer.append(" and certificateNo   ='" + att_MdCompanyGroup.getCertificateNo() + "'");
			}
		}
		//支行所在省(bankProvince)
		if (att_MdCompanyGroup.getBankProvince() != null &&
				!"".equals(att_MdCompanyGroup.getBankProvince().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("bankProvince") != -1) {
				sbuffer.append(" and bankProvince  like '%" + att_MdCompanyGroup.getBankProvince() + "%'");
			} else {
				sbuffer.append(" and bankProvince   ='" + att_MdCompanyGroup.getBankProvince() + "'");
			}
		}
		//支行所在市(bankCity)
		if (att_MdCompanyGroup.getBankCity() != null &&
				!"".equals(att_MdCompanyGroup.getBankCity().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("bankCity") != -1) {
				sbuffer.append(" and bankCity  like '%" + att_MdCompanyGroup.getBankCity() + "%'");
			} else {
				sbuffer.append(" and bankCity   ='" + att_MdCompanyGroup.getBankCity() + "'");
			}
		}
		//汇出方银行卡号(fromBankNo)
		if (att_MdCompanyGroup.getFromBankNo() != null &&
				!"".equals(att_MdCompanyGroup.getFromBankNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("fromBankNo") != -1) {
				sbuffer.append(" and fromBankNo  like '%" + att_MdCompanyGroup.getFromBankNo() + "%'");
			} else {
				sbuffer.append(" and fromBankNo   ='" + att_MdCompanyGroup.getFromBankNo() + "'");
			}
		}
		//是否行内帐号:1是；2否(inBank)
		if (att_MdCompanyGroup.getInBank() != null &&
				!"".equals(att_MdCompanyGroup.getInBank().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("inBank") != -1) {
				sbuffer.append(" and inBank  like '%" + att_MdCompanyGroup.getInBank() + "%'");
			} else {
				sbuffer.append(" and inBank   ='" + att_MdCompanyGroup.getInBank() + "'");
			}
		}
		//流程状态(1.待经理审核，2经理审核不通过，3经理审核通过)(flowState)
		if (att_MdCompanyGroup.getFlowState() != null) {
			sbuffer.append(" and flowState=" + att_MdCompanyGroup.getFlowState());
		}
		//状态:1正常，2删除(state)
		if (att_MdCompanyGroup.getState() != null) {
			sbuffer.append(" and state=" + att_MdCompanyGroup.getState());
		}
		//网点号(银行号)(pointNo)
		if (att_MdCompanyGroup.getPointNo() != null &&
				!"".equals(att_MdCompanyGroup.getPointNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("pointNo") != -1) {
				sbuffer.append(" and pointNo  like '%" + att_MdCompanyGroup.getPointNo() + "%'");
			} else {
				sbuffer.append(" and pointNo   ='" + att_MdCompanyGroup.getPointNo() + "'");
			}
		}
		//创建人(createRen)
		if (att_MdCompanyGroup.getCreateRen() != null &&
				!"".equals(att_MdCompanyGroup.getCreateRen().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("createRen") != -1) {
				sbuffer.append(" and createRen  like '%" + att_MdCompanyGroup.getCreateRen() + "%'");
			} else {
				sbuffer.append(" and createRen   ='" + att_MdCompanyGroup.getCreateRen() + "'");
			}
		}
		//创建时间(createDate)
		if (att_MdCompanyGroup.getCreateDate() != null) {
			sbuffer.append(" and  createDate='" + att_MdCompanyGroup.getCreateDate() + "'");
		}
		//修改人(editRen)
		if (att_MdCompanyGroup.getEditRen() != null &&
				!"".equals(att_MdCompanyGroup.getEditRen().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("editRen") != -1) {
				sbuffer.append(" and editRen  like '%" + att_MdCompanyGroup.getEditRen() + "%'");
			} else {
				sbuffer.append(" and editRen   ='" + att_MdCompanyGroup.getEditRen() + "'");
			}
		}
		//修改时间(editDate)
		if (att_MdCompanyGroup.getEditDate() != null) {
			sbuffer.append(" and  editDate='" + att_MdCompanyGroup.getEditDate() + "'");
		}

		//业务代理相关
		if(att_MdCompanyGroup.getSalesmanIds() != null && !att_MdCompanyGroup.getSalesmanIds().equals("")){
			sbuffer.append(" and salesmanIds in (" + att_MdCompanyGroup.getSalesmanIds() + ")");
		}
		if(att_MdCompanyGroup.getSalesName() != null && !att_MdCompanyGroup.getSalesName().equals("")){
			sbuffer.append(" and salesName = '" + att_MdCompanyGroup.getSalesName() + "'");
		}
		//*****************数据库字段处理end***************
		if (orderStr != null && !"".equals(orderStr.trim())) {
			sbuffer.append(" order by " + orderStr);
		}
						 /*
						 else {
							  sbuffer.append( " order by  RbaId   desc " );
					      }
					      */

		return sbuffer.toString();
	}
}