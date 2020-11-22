package com.hsk.dentistmall.api.daobbase.imp;

import java.util.*;
import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_dental_clinic表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:17
 */
@Component
public class  MdDentalClinicDao extends SupperDao implements IMdDentalClinicDao {	

	/**
	 * 根据md_dental_clinic表主键查找MdDentalClinic对象记录
	 * 
	 * @param  RbbId  Integer类型(md_dental_clinic表主键)
	 * @return MdDentalClinic md_dental_clinic表记录
	 * @throws HSKDBException
	 */	
	public MdDentalClinic findMdDentalClinicById(Integer RbbId) throws HSKDBException{
				MdDentalClinic  att_MdDentalClinic=new MdDentalClinic();				
				if(RbbId!=null){
					att_MdDentalClinic.setRbbId(RbbId);	
				    Object obj=	this.getOne(att_MdDentalClinic);
					if(obj!=null){
						att_MdDentalClinic=(MdDentalClinic) obj;
					}
				}
				return  att_MdDentalClinic;
	}
	 /**
	  * 根据md_dental_clinic表主键删除MdDentalClinic对象记录
	  * @param  RbbId  Integer类型(md_dental_clinic表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdDentalClinicById(Integer RbbId) throws HSKDBException{ 
		 MdDentalClinic  att_MdDentalClinic=new MdDentalClinic();	
		  if(RbbId!=null){
					  att_MdDentalClinic.setRbbId(RbbId);
				  	  Object obj=	this.getOne(att_MdDentalClinic);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_dental_clinic表主键修改MdDentalClinic对象记录
     * @param  RbbId  Integer类型(md_dental_clinic表主键)
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
     * @return MdDentalClinic  修改后的MdDentalClinic对象记录
	 * @throws HSKDBException
	 */
	public  MdDentalClinic updateMdDentalClinicById(Integer RbbId,MdDentalClinic att_MdDentalClinic) throws HSKDBException{
		  if(RbbId!=null){
					att_MdDentalClinic.setRbbId(RbbId);
				   	Object obj=	this.getOne(att_MdDentalClinic);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdDentalClinic;
	}
	
	/**
	 * 新增md_dental_clinic表 记录
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdDentalClinic(MdDentalClinic att_MdDentalClinic) throws HSKDBException{
		 return this.newObject(att_MdDentalClinic);
	} 
		
	/**
	 * 新增或修改md_dental_clinic表记录 ,如果md_dental_clinic表主键MdDentalClinic.RbbId为空就是添加，如果非空就是修改
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
	 * @throws HSKDBException
	 */
	public  MdDentalClinic saveOrUpdateMdDentalClinic(MdDentalClinic att_MdDentalClinic) throws HSKDBException{
		att_MdDentalClinic = (MdDentalClinic)getHibernatesession().merge(att_MdDentalClinic);
		  this.getHibernateTemplate().saveOrUpdate(att_MdDentalClinic);
		  return att_MdDentalClinic;
	}
	
	/**
	 *根据MdDentalClinic对象作为对(md_dental_clinic表进行查询，获取列表对象
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
     * @return List<MdDentalClinic>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdDentalClinic> getListByMdDentalClinic(MdDentalClinic att_MdDentalClinic) throws HSKDBException{
		String Hql=this.getHql( att_MdDentalClinic); 
		List<MdDentalClinic> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	/**
	 *根据MdDentalClinic对象作为对(md_dental_clinic表进行查询，获取分页对象
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
     * @return List<MdDentalClinic>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdDentalClinic(MdDentalClinic att_MdDentalClinic)
			throws HSKDBException {
		String Hql=this.getHql(att_MdDentalClinic);
		return this.getHibernateDao().findByPage(Hql); 
	} 
	/**
	 * 根据MdDentalClinic对象获取Hql字符串
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
	 *  @return hql字符串
	 */
	private String getHql(MdDentalClinic att_MdDentalClinic) {
		StringBuffer sbuffer = new StringBuffer(" from  MdDentalClinic  where  1=1  ");
		String likeStr = att_MdDentalClinic.getTab_like();
		String orderStr = att_MdDentalClinic.getTab_order();

		//*****************无关字段处理begin*****************
		//处理in条件 牙医门诊id(rbbId)
		if (att_MdDentalClinic.getRbbId_str() != null &&
				!"".equals(att_MdDentalClinic.getRbbId_str().trim())) {
			String intStr = att_MdDentalClinic.getRbbId_str().trim();
			String[] arrayStr = intStr.split(",");

			if (arrayStr.length > 0) {
				sbuffer.append(" and ( ");
				for (int i = 0; i < arrayStr.length; i++) {
					String did = arrayStr[i];
					if (i == arrayStr.length - 1) {
						sbuffer.append("  rbbId=" + did + "   ");
					} else {
						sbuffer.append("  rbbId=" + did + " or ");
					}
				}
				sbuffer.append(" ) ");
			}

		}
		//处理in条件 牙医医院id(rbsId)
		if (att_MdDentalClinic.getRbsId_str() != null &&
				!"".equals(att_MdDentalClinic.getRbsId_str().trim())) {
			String intStr = att_MdDentalClinic.getRbsId_str().trim();
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
		//时间类型开始条件处理  创建时间(createDate)
		if (att_MdDentalClinic.getCreateDate_start() != null) {
			sbuffer.append(" and  createDate<='" + att_MdDentalClinic.getCreateDate_start() + "'");
		}
		//时间类型结束条件处理 创建时间(createDate)
		if (att_MdDentalClinic.getCreateDate_end() != null) {
			sbuffer.append(" and  createDate>'" + att_MdDentalClinic.getCreateDate_end() + "'");
		}
		//时间类型开始条件处理  修改时间(editDate)
		if (att_MdDentalClinic.getEditDate_start() != null) {
			sbuffer.append(" and  editDate<='" + att_MdDentalClinic.getEditDate_start() + "'");
		}
		//时间类型结束条件处理 修改时间(editDate)
		if (att_MdDentalClinic.getEditDate_end() != null) {
			sbuffer.append(" and  editDate>'" + att_MdDentalClinic.getEditDate_end() + "'");
		}
		//处理in条件 流程状态(flowState)
		if (att_MdDentalClinic.getFlowState_str() != null &&
				!"".equals(att_MdDentalClinic.getFlowState_str().trim())) {
			String intStr = att_MdDentalClinic.getFlowState_str().trim();
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
		if (att_MdDentalClinic.getState_str() != null &&
				!"".equals(att_MdDentalClinic.getState_str().trim())) {
			String intStr = att_MdDentalClinic.getState_str().trim();
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
		//*****************无关字段处理end*****************
		//*****************数据库字段处理begin*************
		//牙医门诊id(rbbId)
		if (att_MdDentalClinic.getRbbId() != null) {
			sbuffer.append(" and rbbId=" + att_MdDentalClinic.getRbbId());
		}
		//牙医医院id(rbsId)
		if (att_MdDentalClinic.getRbsId() != null) {
			sbuffer.append(" and rbsId=" + att_MdDentalClinic.getRbsId());
		}
		if (att_MdDentalClinic.getRbaId() != null) {
			sbuffer.append(" and rbaId=" + att_MdDentalClinic.getRbaId());
		}
		//牙医门诊名称(rbbName)
		if (att_MdDentalClinic.getRbbName() != null &&
				!"".equals(att_MdDentalClinic.getRbbName().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("rbbName") != -1) {
				sbuffer.append(" and rbbName  like '%" + att_MdDentalClinic.getRbbName() + "%'");
			} else {
				sbuffer.append(" and rbbName   ='" + att_MdDentalClinic.getRbbName() + "'");
			}
		}
		//牙医门诊编号(rbbCode)
		if (att_MdDentalClinic.getRbbCode() != null &&
				!"".equals(att_MdDentalClinic.getRbbCode().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("rbbCode") != -1) {
				sbuffer.append(" and rbbCode  like '%" + att_MdDentalClinic.getRbbCode().toUpperCase() + "%'");
			} else {
				sbuffer.append(" and rbbCode   ='" + att_MdDentalClinic.getRbbCode() + "'");
			}
		}
		//代理地址(address)
		if (att_MdDentalClinic.getAddress() != null &&
				!"".equals(att_MdDentalClinic.getAddress().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("address") != -1) {
				sbuffer.append(" and address  like '%" + att_MdDentalClinic.getAddress() + "%'");
			} else {
				sbuffer.append(" and address   ='" + att_MdDentalClinic.getAddress() + "'");
			}
		}
		//省(province)
		if (att_MdDentalClinic.getProvince() != null &&
				!"".equals(att_MdDentalClinic.getProvince().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("province") != -1) {
				sbuffer.append(" and province  like '%" + att_MdDentalClinic.getProvince() + "%'");
			} else {
				sbuffer.append(" and province   ='" + att_MdDentalClinic.getProvince() + "'");
			}
		}
		//市(city)
		if (att_MdDentalClinic.getCity() != null &&
				!"".equals(att_MdDentalClinic.getCity().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("city") != -1) {
				sbuffer.append(" and city  like '%" + att_MdDentalClinic.getCity() + "%'");
			} else {
				sbuffer.append(" and city   ='" + att_MdDentalClinic.getCity() + "'");
			}
		}
		//地区(area)
		if (att_MdDentalClinic.getArea() != null &&
				!"".equals(att_MdDentalClinic.getArea().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("area") != -1) {
				sbuffer.append(" and area  like '%" + att_MdDentalClinic.getArea() + "%'");
			} else {
				sbuffer.append(" and area   ='" + att_MdDentalClinic.getArea() + "'");
			}
		}
		//联系人(connUser)
		if (att_MdDentalClinic.getConnUser() != null &&
				!"".equals(att_MdDentalClinic.getConnUser().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("connUser") != -1) {
				sbuffer.append(" and connUser  like '%" + att_MdDentalClinic.getConnUser() + "%'");
			} else {
				sbuffer.append(" and connUser   ='" + att_MdDentalClinic.getConnUser() + "'");
			}
		}
		//联系人电话(connPhone)
		if (att_MdDentalClinic.getConnPhone() != null &&
				!"".equals(att_MdDentalClinic.getConnPhone().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("connPhone") != -1) {
				sbuffer.append(" and connPhone  like '%" + att_MdDentalClinic.getConnPhone() + "%'");
			} else {
				sbuffer.append(" and connPhone   ='" + att_MdDentalClinic.getConnPhone() + "'");
			}
		}
		//联系人法人身份证(connCertificateNo)
		if (att_MdDentalClinic.getConnCertificateNo() != null &&
				!"".equals(att_MdDentalClinic.getConnCertificateNo().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("connCertificateNo") != -1) {
				sbuffer.append(" and connCertificateNo  like '%" + att_MdDentalClinic.getConnCertificateNo() + "%'");
			} else {
				sbuffer.append(" and connCertificateNo   ='" + att_MdDentalClinic.getConnCertificateNo() + "'");
			}
		}
		//创建人(createRen)
		if (att_MdDentalClinic.getCreateRen() != null &&
				!"".equals(att_MdDentalClinic.getCreateRen().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("createRen") != -1) {
				sbuffer.append(" and createRen  like '%" + att_MdDentalClinic.getCreateRen() + "%'");
			} else {
				sbuffer.append(" and createRen   ='" + att_MdDentalClinic.getCreateRen() + "'");
			}
		}
		//创建时间(createDate)
		if (att_MdDentalClinic.getCreateDate() != null) {
			sbuffer.append(" and  createDate='" + att_MdDentalClinic.getCreateDate() + "'");
		}
		//修改人(editRen)
		if (att_MdDentalClinic.getEditRen() != null &&
				!"".equals(att_MdDentalClinic.getEditRen().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("editRen") != -1) {
				sbuffer.append(" and editRen  like '%" + att_MdDentalClinic.getEditRen() + "%'");
			} else {
				sbuffer.append(" and editRen   ='" + att_MdDentalClinic.getEditRen() + "'");
			}
		}
		//修改时间(editDate)
		if (att_MdDentalClinic.getEditDate() != null) {
			sbuffer.append(" and  editDate='" + att_MdDentalClinic.getEditDate() + "'");
		}
		//流程状态(flowState)
		if (att_MdDentalClinic.getFlowState() != null) {
			sbuffer.append(" and flowState=" + att_MdDentalClinic.getFlowState());
		}
		//状态:1正常，2删除(state)
		if (att_MdDentalClinic.getState() != null) {
			sbuffer.append(" and state=" + att_MdDentalClinic.getState());
		}
		//银行账号(bankCode)
		if (att_MdDentalClinic.getBankCode() != null &&
				!"".equals(att_MdDentalClinic.getBankCode().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("bankCode") != -1) {
				sbuffer.append(" and bankCode  like '%" + att_MdDentalClinic.getBankCode() + "%'");
			} else {
				sbuffer.append(" and bankCode   ='" + att_MdDentalClinic.getBankCode() + "'");
			}
		}

		//业务代理相关
		if(att_MdDentalClinic.getSalesmanIds() != null && !att_MdDentalClinic.getSalesmanIds().equals("")){
			sbuffer.append(" and salesmanIds in (" + att_MdDentalClinic.getSalesmanIds() + ")");
		}
		if(att_MdDentalClinic.getSalesName() != null && !att_MdDentalClinic.getSalesName().equals("")){
			sbuffer.append(" and salesName = '" + att_MdDentalClinic.getSalesName() + "'");
		}
		//*****************数据库字段处理end***************
		if (orderStr != null && !"".equals(orderStr.trim())) {
			sbuffer.append(" order by " + orderStr);
		}
						 /*
						 else {
							  sbuffer.append( " order by  RbbId   desc " );
					      }
					      */

		return sbuffer.toString();
	}
}