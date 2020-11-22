package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.util.*;
import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_dentist_hospital表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:17
 */
@Component
public class MdDentistHospitalApiDao extends SupperDao implements IMdDentistHospitalApiDao {

	/**
	 * 根据md_dentist_hospital表主键查找MdDentistHospital对象记录
	 * 
	 * @param  RbsId  Integer类型(md_dentist_hospital表主键)
	 * @return MdDentistHospital md_dentist_hospital表记录
	 * @throws HSKDBException
	 */	
	public MdDentistHospital findMdDentistHospitalById(Integer RbsId) throws HSKDBException{
				MdDentistHospital  att_MdDentistHospital=new MdDentistHospital();				
				if(RbsId!=null){
					att_MdDentistHospital.setRbsId(RbsId);	
				    Object obj=	this.getOne(att_MdDentistHospital);
					if(obj!=null){
						att_MdDentistHospital=(MdDentistHospital) obj;
					}
				}
				return  att_MdDentistHospital;
	}
	 /**
	  * 根据md_dentist_hospital表主键删除MdDentistHospital对象记录
	  * @param  RbsId  Integer类型(md_dentist_hospital表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdDentistHospitalById(Integer RbsId) throws HSKDBException{ 
		 MdDentistHospital  att_MdDentistHospital=new MdDentistHospital();	
		  if(RbsId!=null){
					  att_MdDentistHospital.setRbsId(RbsId);
				  	  Object obj=	this.getOne(att_MdDentistHospital);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_dentist_hospital表主键修改MdDentistHospital对象记录
     * @param  RbsId  Integer类型(md_dentist_hospital表主键)
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
     * @return MdDentistHospital  修改后的MdDentistHospital对象记录
	 * @throws HSKDBException
	 */
	public  MdDentistHospital updateMdDentistHospitalById(Integer RbsId,MdDentistHospital att_MdDentistHospital) throws HSKDBException{
		  if(RbsId!=null){
					att_MdDentistHospital.setRbsId(RbsId);
				   	Object obj=	this.getOne(att_MdDentistHospital);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdDentistHospital;
	}
	
	/**
	 * 新增md_dentist_hospital表 记录
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdDentistHospital(MdDentistHospital att_MdDentistHospital) throws HSKDBException{
		 return this.newObject(att_MdDentistHospital);
	} 
		
	/**
	 * 新增或修改md_dentist_hospital表记录 ,如果md_dentist_hospital表主键MdDentistHospital.RbsId为空就是添加，如果非空就是修改
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
	 * @throws HSKDBException
	 */
	public  MdDentistHospital saveOrUpdateMdDentistHospital(MdDentistHospital att_MdDentistHospital) throws HSKDBException{
		att_MdDentistHospital = (MdDentistHospital)getHibernatesession().merge(att_MdDentistHospital);
		  this.getHibernateTemplate().saveOrUpdate(att_MdDentistHospital);
		  return att_MdDentistHospital;
	}
	
	/**
	 *根据MdDentistHospital对象作为对(md_dentist_hospital表进行查询，获取列表对象
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
     * @return List<MdDentistHospital>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdDentistHospital> getListByMdDentistHospital(MdDentistHospital att_MdDentistHospital) throws HSKDBException{
		String Hql=this.getHql( att_MdDentistHospital); 
		List<MdDentistHospital> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	/**
	 *根据MdDentistHospital对象作为对(md_dentist_hospital表进行查询，获取分页对象
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
     * @return List<MdDentistHospital>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdDentistHospital(MdDentistHospital att_MdDentistHospital)
			throws HSKDBException {
		String Hql=this.getHql(att_MdDentistHospital);
		return this.getHibernateDao().findByPage(Hql); 
	} 
	/**
	 * 根据MdDentistHospital对象获取Hql字符串
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
	 *  @return hql字符串
	 */
	private String getHql(MdDentistHospital att_MdDentistHospital) {
		StringBuffer sbuffer = new StringBuffer(" from  MdDentistHospital  where  1=1  ");
		String likeStr = att_MdDentistHospital.getTab_like();
		String orderStr = att_MdDentistHospital.getTab_order();

		//*****************无关字段处理begin*****************
		//处理in条件 牙医医院id(rbsId)
		if (att_MdDentistHospital.getRbsId_str() != null &&
				!"".equals(att_MdDentistHospital.getRbsId_str().trim())) {
			String intStr = att_MdDentistHospital.getRbsId_str().trim();
			String[] arrayStr = intStr.split(",");

			if (arrayStr.length > 0) {
				sbuffer.append(" and ( ");
				for (int i = 0; i < arrayStr.length; i++) {
					String did = arrayStr[i];
					if (i == arrayStr.length - 1) {
						sbuffer.append("  rbsId=" + did + "   ");
					} else {
						sbuffer.append("  rbsId=" + did + " or ");
					}
				}
				sbuffer.append(" ) ");
			}

		}
		//处理in条件 集团公司id(rbaId)
		if (att_MdDentistHospital.getRbaId_str() != null &&
				!"".equals(att_MdDentistHospital.getRbaId_str().trim())) {
			String intStr = att_MdDentistHospital.getRbaId_str().trim();
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
		//时间类型开始条件处理  创建时间(createDate)
		if (att_MdDentistHospital.getCreateDate_start() != null) {
			sbuffer.append(" and  createDate<='" + att_MdDentistHospital.getCreateDate_start() + "'");
		}
		//时间类型结束条件处理 创建时间(createDate)
		if (att_MdDentistHospital.getCreateDate_end() != null) {
			sbuffer.append(" and  createDate>'" + att_MdDentistHospital.getCreateDate_end() + "'");
		}
		//时间类型开始条件处理  修改时间(editDate)
		if (att_MdDentistHospital.getEditDate_start() != null) {
			sbuffer.append(" and  editDate<='" + att_MdDentistHospital.getEditDate_start() + "'");
		}
		//时间类型结束条件处理 修改时间(editDate)
		if (att_MdDentistHospital.getEditDate_end() != null) {
			sbuffer.append(" and  editDate>'" + att_MdDentistHospital.getEditDate_end() + "'");
		}
		//处理in条件 流程状态(flowState)
		if (att_MdDentistHospital.getFlowState_str() != null &&
				!"".equals(att_MdDentistHospital.getFlowState_str().trim())) {
			String intStr = att_MdDentistHospital.getFlowState_str().trim();
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
		if (att_MdDentistHospital.getState_str() != null &&
				!"".equals(att_MdDentistHospital.getState_str().trim())) {
			String intStr = att_MdDentistHospital.getState_str().trim();
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
		//处理in条件 申请人(applyUserId)
		if (att_MdDentistHospital.getApplyUserId_str() != null &&
				!"".equals(att_MdDentistHospital.getApplyUserId_str().trim())) {
			String intStr = att_MdDentistHospital.getApplyUserId_str().trim();
			String[] arrayStr = intStr.split(",");

			if (arrayStr.length > 0) {
				sbuffer.append(" and ( ");
				for (int i = 0; i < arrayStr.length; i++) {
					String did = arrayStr[i];
					if (i == arrayStr.length - 1) {
						sbuffer.append("  applyUserId=" + did + "   ");
					} else {
						sbuffer.append("  applyUserId=" + did + " or ");
					}
				}
				sbuffer.append(" ) ");
			}

		}
		//*****************无关字段处理end*****************
		//*****************数据库字段处理begin*************
		//牙医医院id(rbsId)
		if (att_MdDentistHospital.getRbsId() != null) {
			sbuffer.append(" and rbsId=" + att_MdDentistHospital.getRbsId());
		}
		//集团公司id(rbaId)
		if (att_MdDentistHospital.getRbaId() != null) {
			sbuffer.append(" and rbaId=" + att_MdDentistHospital.getRbaId());
		}
		//牙医医院编号(rbsCode)
		if (att_MdDentistHospital.getRbsCode() != null &&
				!"".equals(att_MdDentistHospital.getRbsCode().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("rbsCode") != -1) {
				sbuffer.append(" and rbsCode  like '%" + att_MdDentistHospital.getRbsCode().toUpperCase() + "%'");
			} else {
				sbuffer.append(" and rbsCode   ='" + att_MdDentistHospital.getRbsCode() + "'");
			}
		}
		//牙医医院名(rbsName)
		if (att_MdDentistHospital.getRbsName() != null &&
				!"".equals(att_MdDentistHospital.getRbsName().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("rbsName") != -1) {
				sbuffer.append(" and rbsName  like '%" + att_MdDentistHospital.getRbsName() + "%'");
			} else {
				sbuffer.append(" and rbsName   ='" + att_MdDentistHospital.getRbsName() + "'");
			}
		}
		//牙医医院行业(industry)
		if (att_MdDentistHospital.getIndustry() != null &&
				!"".equals(att_MdDentistHospital.getIndustry().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("industry") != -1) {
				sbuffer.append(" and industry  like '%" + att_MdDentistHospital.getIndustry() + "%'");
			} else {
				sbuffer.append(" and industry   ='" + att_MdDentistHospital.getIndustry() + "'");
			}
		}
		//牙医医院地址(address)
		if (att_MdDentistHospital.getAddress() != null &&
				!"".equals(att_MdDentistHospital.getAddress().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("address") != -1) {
				sbuffer.append(" and address  like '%" + att_MdDentistHospital.getAddress() + "%'");
			} else {
				sbuffer.append(" and address   ='" + att_MdDentistHospital.getAddress() + "'");
			}
		}
		//省(province)
		if (att_MdDentistHospital.getProvince() != null &&
				!"".equals(att_MdDentistHospital.getProvince().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("province") != -1) {
				sbuffer.append(" and province  like '%" + att_MdDentistHospital.getProvince() + "%'");
			} else {
				sbuffer.append(" and province   ='" + att_MdDentistHospital.getProvince() + "'");
			}
		}
		//市(city)
		if (att_MdDentistHospital.getCity() != null &&
				!"".equals(att_MdDentistHospital.getCity().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("city") != -1) {
				sbuffer.append(" and city  like '%" + att_MdDentistHospital.getCity() + "%'");
			} else {
				sbuffer.append(" and city   ='" + att_MdDentistHospital.getCity() + "'");
			}
		}
		//地区(area)
		if (att_MdDentistHospital.getArea() != null &&
				!"".equals(att_MdDentistHospital.getArea().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("area") != -1) {
				sbuffer.append(" and area  like '%" + att_MdDentistHospital.getArea() + "%'");
			} else {
				sbuffer.append(" and area   ='" + att_MdDentistHospital.getArea() + "'");
			}
		}
		//所有人(legalPerson)
		if (att_MdDentistHospital.getLegalPerson() != null &&
				!"".equals(att_MdDentistHospital.getLegalPerson().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("legalPerson") != -1) {
				sbuffer.append(" and legalPerson  like '%" + att_MdDentistHospital.getLegalPerson() + "%'");
			} else {
				sbuffer.append(" and legalPerson   ='" + att_MdDentistHospital.getLegalPerson() + "'");
			}
		}
		//所有人电话(legalPhone)
		if (att_MdDentistHospital.getLegalPhone() != null &&
				!"".equals(att_MdDentistHospital.getLegalPhone().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("legalPhone") != -1) {
				sbuffer.append(" and legalPhone  like '%" + att_MdDentistHospital.getLegalPhone() + "%'");
			} else {
				sbuffer.append(" and legalPhone   ='" + att_MdDentistHospital.getLegalPhone() + "'");
			}
		}
		//所有人法人身份证(legalCertificateNo)
		if (att_MdDentistHospital.getLegalCertificateNo() != null &&
				!"".equals(att_MdDentistHospital.getLegalCertificateNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("legalCertificateNo") != -1) {
				sbuffer.append(" and legalCertificateNo  like '%" + att_MdDentistHospital.getLegalCertificateNo() + "%'");
			} else {
				sbuffer.append(" and legalCertificateNo   ='" + att_MdDentistHospital.getLegalCertificateNo() + "'");
			}
		}
		//联系人(connUser)
		if (att_MdDentistHospital.getConnUser() != null &&
				!"".equals(att_MdDentistHospital.getConnUser().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("connUser") != -1) {
				sbuffer.append(" and connUser  like '%" + att_MdDentistHospital.getConnUser() + "%'");
			} else {
				sbuffer.append(" and connUser   ='" + att_MdDentistHospital.getConnUser() + "'");
			}
		}
		//联系人电话(connPhone)
		if (att_MdDentistHospital.getConnPhone() != null &&
				!"".equals(att_MdDentistHospital.getConnPhone().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("connPhone") != -1) {
				sbuffer.append(" and connPhone  like '%" + att_MdDentistHospital.getConnPhone() + "%'");
			} else {
				sbuffer.append(" and connPhone   ='" + att_MdDentistHospital.getConnPhone() + "'");
			}
		}
		//联系人法人身份证(connCertificateNo)
		if (att_MdDentistHospital.getConnCertificateNo() != null &&
				!"".equals(att_MdDentistHospital.getConnCertificateNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("connCertificateNo") != -1) {
				sbuffer.append(" and connCertificateNo  like '%" + att_MdDentistHospital.getConnCertificateNo() + "%'");
			} else {
				sbuffer.append(" and connCertificateNo   ='" + att_MdDentistHospital.getConnCertificateNo() + "'");
			}
		}
		//营业执照(businessNo)
		if (att_MdDentistHospital.getBusinessNo() != null &&
				!"".equals(att_MdDentistHospital.getBusinessNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("businessNo") != -1) {
				sbuffer.append(" and businessNo  like '%" + att_MdDentistHospital.getBusinessNo() + "%'");
			} else {
				sbuffer.append(" and businessNo   ='" + att_MdDentistHospital.getBusinessNo() + "'");
			}
		}
		//营业执照文件(businessFile)
		if (att_MdDentistHospital.getBusinessFile() != null &&
				!"".equals(att_MdDentistHospital.getBusinessFile().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("businessFile") != -1) {
				sbuffer.append(" and businessFile  like '%" + att_MdDentistHospital.getBusinessFile() + "%'");
			} else {
				sbuffer.append(" and businessFile   ='" + att_MdDentistHospital.getBusinessFile() + "'");
			}
		}
		//负责人身份证(personCertificateNo)
		if (att_MdDentistHospital.getPersonCertificateNo() != null &&
				!"".equals(att_MdDentistHospital.getPersonCertificateNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("personCertificateNo") != -1) {
				sbuffer.append(" and personCertificateNo  like '%" + att_MdDentistHospital.getPersonCertificateNo() + "%'");
			} else {
				sbuffer.append(" and personCertificateNo   ='" + att_MdDentistHospital.getPersonCertificateNo() + "'");
			}
		}
		//负责人身份证文件(personCertificateFile)
		if (att_MdDentistHospital.getPersonCertificateFile() != null &&
				!"".equals(att_MdDentistHospital.getPersonCertificateFile().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("personCertificateFile") != -1) {
				sbuffer.append(" and personCertificateFile  like '%" + att_MdDentistHospital.getPersonCertificateFile() + "%'");
			} else {
				sbuffer.append(" and personCertificateFile   ='" + att_MdDentistHospital.getPersonCertificateFile() + "'");
			}
		}
		//组织机构代码证(organizationNo)
		if (att_MdDentistHospital.getOrganizationNo() != null &&
				!"".equals(att_MdDentistHospital.getOrganizationNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("organizationNo") != -1) {
				sbuffer.append(" and organizationNo  like '%" + att_MdDentistHospital.getOrganizationNo() + "%'");
			} else {
				sbuffer.append(" and organizationNo   ='" + att_MdDentistHospital.getOrganizationNo() + "'");
			}
		}
		//组织机构代码证文件(organizationFile)
		if (att_MdDentistHospital.getOrganizationFile() != null &&
				!"".equals(att_MdDentistHospital.getOrganizationFile().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("organizationFile") != -1) {
				sbuffer.append(" and organizationFile  like '%" + att_MdDentistHospital.getOrganizationFile() + "%'");
			} else {
				sbuffer.append(" and organizationFile   ='" + att_MdDentistHospital.getOrganizationFile() + "'");
			}
		}
		//账号类型(bankType)
		if (att_MdDentistHospital.getBankType() != null &&
				!"".equals(att_MdDentistHospital.getBankType().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("bankType") != -1) {
				sbuffer.append(" and bankType  like '%" + att_MdDentistHospital.getBankType() + "%'");
			} else {
				sbuffer.append(" and bankType   ='" + att_MdDentistHospital.getBankType() + "'");
			}
		}
		//开户人(bankUser)
		if (att_MdDentistHospital.getBankUser() != null &&
				!"".equals(att_MdDentistHospital.getBankUser().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("bankUser") != -1) {
				sbuffer.append(" and bankUser  like '%" + att_MdDentistHospital.getBankUser() + "%'");
			} else {
				sbuffer.append(" and bankUser   ='" + att_MdDentistHospital.getBankUser() + "'");
			}
		}
		//开户银行(bankName)
		if (att_MdDentistHospital.getBankName() != null &&
				!"".equals(att_MdDentistHospital.getBankName().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("bankName") != -1) {
				sbuffer.append(" and bankName  like '%" + att_MdDentistHospital.getBankName() + "%'");
			} else {
				sbuffer.append(" and bankName   ='" + att_MdDentistHospital.getBankName() + "'");
			}
		}
		//提现方式(cashType)
		if (att_MdDentistHospital.getCashType() != null &&
				!"".equals(att_MdDentistHospital.getCashType().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("cashType") != -1) {
				sbuffer.append(" and cashType  like '%" + att_MdDentistHospital.getCashType() + "%'");
			} else {
				sbuffer.append(" and cashType   ='" + att_MdDentistHospital.getCashType() + "'");
			}
		}
		//创建人(createRen)
		if (att_MdDentistHospital.getCreateRen() != null &&
				!"".equals(att_MdDentistHospital.getCreateRen().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("createRen") != -1) {
				sbuffer.append(" and createRen  like '%" + att_MdDentistHospital.getCreateRen() + "%'");
			} else {
				sbuffer.append(" and createRen   ='" + att_MdDentistHospital.getCreateRen() + "'");
			}
		}
		//创建时间(createDate)
		if (att_MdDentistHospital.getCreateDate() != null) {
			sbuffer.append(" and  createDate='" + att_MdDentistHospital.getCreateDate() + "'");
		}
		//修改人(editRen)
		if (att_MdDentistHospital.getEditRen() != null &&
				!"".equals(att_MdDentistHospital.getEditRen().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("editRen") != -1) {
				sbuffer.append(" and editRen  like '%" + att_MdDentistHospital.getEditRen() + "%'");
			} else {
				sbuffer.append(" and editRen   ='" + att_MdDentistHospital.getEditRen() + "'");
			}
		}
		//修改时间(editDate)
		if (att_MdDentistHospital.getEditDate() != null) {
			sbuffer.append(" and  editDate='" + att_MdDentistHospital.getEditDate() + "'");
		}
		//流程状态(flowState)
		if (att_MdDentistHospital.getFlowState() != null) {
			sbuffer.append(" and flowState=" + att_MdDentistHospital.getFlowState());
		}
		//状态:1正常，2删除(state)
		if (att_MdDentistHospital.getState() != null) {
			sbuffer.append(" and state=" + att_MdDentistHospital.getState());
		}
		//银行账号(bankCode)
		if (att_MdDentistHospital.getBankCode() != null &&
				!"".equals(att_MdDentistHospital.getBankCode().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("bankCode") != -1) {
				sbuffer.append(" and bankCode  like '%" + att_MdDentistHospital.getBankCode() + "%'");
			} else {
				sbuffer.append(" and bankCode   ='" + att_MdDentistHospital.getBankCode() + "'");
			}
		}
		//申请人(applyUserId)
		if (att_MdDentistHospital.getApplyUserId() != null) {
			sbuffer.append(" and applyUserId=" + att_MdDentistHospital.getApplyUserId());
		}
		//业务代理相关
		if(att_MdDentistHospital.getSalesmanIds() != null && !att_MdDentistHospital.getSalesmanIds().equals("")){
			sbuffer.append(" and salesmanIds in (" + att_MdDentistHospital.getSalesmanIds() + ")");
		}
		if(att_MdDentistHospital.getSalesName() != null && !att_MdDentistHospital.getSalesName().equals("")){
			sbuffer.append(" and salesName = '" + att_MdDentistHospital.getSalesName() + "'");
		}
		//*****************数据库字段处理end***************
		if (orderStr != null && !"".equals(orderStr.trim())) {
			sbuffer.append(" order by " + orderStr);
		}
						 /*
						 else {
							  sbuffer.append( " order by  RbsId   desc " );
					      }
					      */

		return sbuffer.toString();
	}
}