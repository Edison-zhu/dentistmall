package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.text.SimpleDateFormat;
import java.util.*;
import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_out_warehouse表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:55
 */
@Component
public class MdOutWarehouseApiDao extends SupperDao implements IMdOutWarehouseApiDao {

	/**
	 * 根据md_out_warehouse表主键查找MdOutWarehouse对象记录
	 * 
	 * @param  WowId  Integer类型(md_out_warehouse表主键)
	 * @return MdOutWarehouse md_out_warehouse表记录
	 * @throws HSKDBException
	 */	
	public MdOutWarehouse findMdOutWarehouseById(Integer WowId) throws HSKDBException{
				MdOutWarehouse  att_MdOutWarehouse=new MdOutWarehouse();				
				if(WowId!=null){
					att_MdOutWarehouse.setWowId(WowId);	
				    Object obj=	this.getOne(att_MdOutWarehouse);
					if(obj!=null){
						att_MdOutWarehouse=(MdOutWarehouse) obj;
					}
				}
				return  att_MdOutWarehouse;
	}
	 /**
	  * 根据md_out_warehouse表主键删除MdOutWarehouse对象记录
	  * @param  WowId  Integer类型(md_out_warehouse表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdOutWarehouseById(Integer WowId) throws HSKDBException{ 
		 MdOutWarehouse  att_MdOutWarehouse=new MdOutWarehouse();	
		  if(WowId!=null){
					  att_MdOutWarehouse.setWowId(WowId);
				  	  Object obj=	this.getOne(att_MdOutWarehouse);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_out_warehouse表主键修改MdOutWarehouse对象记录
     * @param  WowId  Integer类型(md_out_warehouse表主键)
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
     * @return MdOutWarehouse  修改后的MdOutWarehouse对象记录
	 * @throws HSKDBException
	 */
	public  MdOutWarehouse updateMdOutWarehouseById(Integer WowId,MdOutWarehouse att_MdOutWarehouse) throws HSKDBException{
		  if(WowId!=null){
					att_MdOutWarehouse.setWowId(WowId);
				   	Object obj=	this.getOne(att_MdOutWarehouse);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdOutWarehouse;
	}
	
	/**
	 * 新增md_out_warehouse表 记录
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdOutWarehouse(MdOutWarehouse att_MdOutWarehouse) throws HSKDBException{
		 return this.newObject(att_MdOutWarehouse);
	} 
		
	/**
	 * 新增或修改md_out_warehouse表记录 ,如果md_out_warehouse表主键MdOutWarehouse.WowId为空就是添加，如果非空就是修改
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
	 * @throws HSKDBException
	 */
	public  MdOutWarehouse saveOrUpdateMdOutWarehouse(MdOutWarehouse att_MdOutWarehouse) throws HSKDBException{
		  this.getHibernateTemplate().saveOrUpdate(att_MdOutWarehouse);
		  return att_MdOutWarehouse;
	}
	
	/**
	 *根据MdOutWarehouse对象作为对(md_out_warehouse表进行查询，获取列表对象
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
     * @return List<MdOutWarehouse>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdOutWarehouse> getListByMdOutWarehouse(MdOutWarehouse att_MdOutWarehouse) throws HSKDBException{
		String Hql=this.getHql( att_MdOutWarehouse, null, null);
		List<MdOutWarehouse> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}

	@Override
	public PagerModel getPagerModelObjectDistinct(MdOutWarehouse att_mdOutWarehouse, Map<String, Object> map, String distinctName) throws HSKDBException {
		StringBuffer sbuffer = new StringBuffer("select distinct " + distinctName + " from MdOutwarehouseOutorderEntity where 1=1 ");
		String Hql=this.getHql(att_mdOutWarehouse, sbuffer, map);
		return this.getHibernateDao().findByPage(Hql);
	}

	/**
	 *根据MdOutWarehouse对象作为对(md_out_warehouse表进行查询，获取分页对象
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
     * @return List<MdOutWarehouse>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdOutWarehouse(MdOutWarehouse att_MdOutWarehouse, Map<String, Object> map)
			throws HSKDBException {
		StringBuffer sbuffer = new StringBuffer("from MdOutwarehouseOutorderEntity where 1=1 ");
		String Hql=this.getHql(att_MdOutWarehouse, sbuffer, map);
		return this.getHibernateDao().findByPage(Hql); 
	} 
	/**
	 * 根据MdOutWarehouse对象获取Hql字符串
     * @param  att_MdOutWarehouse  MdOutWarehouse类型(md_out_warehouse表记录)
	 *  @return hql字符串
	 */
	private String  getHql(MdOutWarehouse att_MdOutWarehouse, StringBuffer sbuffer, Map<String, Object> map){
		if(sbuffer == null) {
			sbuffer = new StringBuffer(" from  MdOutWarehouse  where  1=1 ");
		}
		String likeStr = att_MdOutWarehouse.getTab_like();
		String orderStr = att_MdOutWarehouse.getTab_order();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//*****************无关字段处理begin*****************
		//处理in条件 出库单信息表id(wowId)
		if (att_MdOutWarehouse.getWowId_str() != null &&
				!"".equals(att_MdOutWarehouse.getWowId_str().trim())) {
			String intStr = att_MdOutWarehouse.getWowId_str().trim();
			String[] arrayStr = intStr.split(",");

			if (arrayStr.length > 0) {
				sbuffer.append(" and ( ");
				for (int i = 0; i < arrayStr.length; i++) {
					String did = arrayStr[i];
					if (i == arrayStr.length - 1) {
						sbuffer.append("  wowId=" + did + "   ");
					} else {
						sbuffer.append("  wowId=" + did + " or ");
					}
				}
				sbuffer.append(" ) ");
			}

		}
		//处理in条件 集团公司id(rbaId)
		if (att_MdOutWarehouse.getRbaId_str() != null &&
				!"".equals(att_MdOutWarehouse.getRbaId_str().trim())) {
			String intStr = att_MdOutWarehouse.getRbaId_str().trim();
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
		//处理in条件 牙医医院id(rbsId)
		if (att_MdOutWarehouse.getRbsId_str() != null &&
				!"".equals(att_MdOutWarehouse.getRbsId_str().trim())) {
			String intStr = att_MdOutWarehouse.getRbsId_str().trim();
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
		//处理in条件 牙医门诊id(rbbId)
		if (att_MdOutWarehouse.getRbbId_str() != null &&
				!"".equals(att_MdOutWarehouse.getRbbId_str().trim())) {
			String intStr = att_MdOutWarehouse.getRbbId_str().trim();
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


		//时间类型开始条件处理  完成时间(finshDate)
		if (att_MdOutWarehouse.getFinshDate_start() != null) {
			sbuffer.append(" and  finshDate>='" + sdf.format(att_MdOutWarehouse.getFinshDate_start()) + " 00:00:00'");
		}

		//时间类型结束条件处理 完成时间(finshDate)
		if (att_MdOutWarehouse.getFinshDate_end() != null) {
			sbuffer.append(" and  finshDate<='" + sdf.format(att_MdOutWarehouse.getFinshDate_end()) + " 23:59:59'");
		}
		//时间类型开始条件处理  生效时间(activeTime)
		if (att_MdOutWarehouse.getActiveTime_start() != null) {
			sbuffer.append(" and  activeTime<='" + att_MdOutWarehouse.getActiveTime_start() + "'");
		}
		//时间类型结束条件处理 生效时间(activeTime)
		if (att_MdOutWarehouse.getActiveTime_end() != null) {
			sbuffer.append(" and  activeTime>'" + att_MdOutWarehouse.getActiveTime_end() + "'");
		}
		//时间类型开始条件处理  修改时间(editDate)
		if (att_MdOutWarehouse.getEditDate_start() != null) {
			sbuffer.append(" and  editDate<='" + att_MdOutWarehouse.getEditDate_start() + "'");
		}
		//时间类型结束条件处理 修改时间(editDate)
		if (att_MdOutWarehouse.getEditDate_end() != null) {
			sbuffer.append(" and  editDate>'" + att_MdOutWarehouse.getEditDate_end() + "'");
		}
		//时间类型开始条件处理  创建时间(createDate)
		if (att_MdOutWarehouse.getCreateDate_start() != null) {
			sbuffer.append(" and  createDate<='" + att_MdOutWarehouse.getCreateDate_start() + "'");
		}
		//时间类型结束条件处理 创建时间(createDate)
		if (att_MdOutWarehouse.getCreateDate_end() != null) {
			sbuffer.append(" and  createDate>'" + att_MdOutWarehouse.getCreateDate_end() + "'");
		}
		//*****************无关字段处理end*****************
		//*****************数据库字段处理begin*************
		//出库单信息表id(wowId)
		if (att_MdOutWarehouse.getWowId() != null) {
			sbuffer.append(" and wowId=" + att_MdOutWarehouse.getWowId());
		}
		//集团公司id(rbaId)
		if (att_MdOutWarehouse.getRbaId() != null) {
			sbuffer.append(" and rbaId=" + att_MdOutWarehouse.getRbaId());
		}
		//牙医医院id(rbsId)
		if (att_MdOutWarehouse.getRbsId() != null) {
			sbuffer.append(" and rbsId=" + att_MdOutWarehouse.getRbsId());
		}
		//牙医门诊id(rbbId)
		if (att_MdOutWarehouse.getRbbId() != null) {
			sbuffer.append(" and rbbId=" + att_MdOutWarehouse.getRbbId());
		}
		//牙医门诊id(rbbId)
		if (att_MdOutWarehouse.getSuiId() != null) {
			sbuffer.append(" and suiId=" + att_MdOutWarehouse.getSuiId());
		}
		//采购单位类型(purchaseType)
		if (att_MdOutWarehouse.getPurchaseType() != null &&
				!"".equals(att_MdOutWarehouse.getPurchaseType().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("purchaseType") != -1) {
				sbuffer.append(" and purchaseType  like '%" + att_MdOutWarehouse.getPurchaseType() + "%'");
			} else {
				sbuffer.append(" and purchaseType   ='" + att_MdOutWarehouse.getPurchaseType() + "'");
			}
		}
		//货主(ower)
		if (att_MdOutWarehouse.getOwer() != null &&
				!"".equals(att_MdOutWarehouse.getOwer().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("ower") != -1) {
				sbuffer.append(" and ower  like '%" + att_MdOutWarehouse.getOwer() + "%'");
			} else {
				sbuffer.append(" and ower   ='" + att_MdOutWarehouse.getOwer() + "'");
			}
		}
		//单据类型(companyType)
		if (att_MdOutWarehouse.getCompanyType() != null &&
				!"".equals(att_MdOutWarehouse.getCompanyType().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("companyType") != -1) {
				sbuffer.append(" and companyType  like '%" + att_MdOutWarehouse.getCompanyType().toUpperCase() + "%'");
			} else {
				sbuffer.append(" and companyType   ='" + att_MdOutWarehouse.getCompanyType() + "'");
			}
		}
		//发货单号(wowCode)
		if (att_MdOutWarehouse.getWowCode() != null &&
				!"".equals(att_MdOutWarehouse.getWowCode().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("wowCode") != -1) {
				sbuffer.append(" and wowCode  like '%" + att_MdOutWarehouse.getWowCode().toUpperCase() + "%'");
			} else {
				sbuffer.append(" and wowCode   ='" + att_MdOutWarehouse.getWowCode() + "'");
			}
		}
		//销售订单号(relatedBill1)
		if (att_MdOutWarehouse.getRelatedBill1() != null &&
				!"".equals(att_MdOutWarehouse.getRelatedBill1().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("relatedBill1") != -1) {
				sbuffer.append(" and relatedBill1  like '%" + att_MdOutWarehouse.getRelatedBill1().toUpperCase() + "%'");
			} else {
				sbuffer.append(" and relatedBill1   ='" + att_MdOutWarehouse.getRelatedBill1() + "'");
			}
		}
		//相关单号2(relatedBill2)
		if (att_MdOutWarehouse.getRelatedBill2() != null &&
				!"".equals(att_MdOutWarehouse.getRelatedBill2().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("relatedBill2") != -1) {
				sbuffer.append(" and relatedBill2  like '%" + att_MdOutWarehouse.getRelatedBill2().toUpperCase() + "%'");
			} else {
				sbuffer.append(" and relatedBill2   ='" + att_MdOutWarehouse.getRelatedBill2() + "'");
			}
		}
		//相关单号3(relatedBill3)
		if (att_MdOutWarehouse.getRelatedBill3() != null &&
				!"".equals(att_MdOutWarehouse.getRelatedBill3().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("relatedBill3") != -1) {
				sbuffer.append(" and relatedBill3  like '%" + att_MdOutWarehouse.getRelatedBill3() + "%'");
			} else {
				sbuffer.append(" and relatedBill3   ='" + att_MdOutWarehouse.getRelatedBill3().toUpperCase() + "'");
			}
		}
		//收货方(customer)
		if (att_MdOutWarehouse.getCustomer() != null &&
				!"".equals(att_MdOutWarehouse.getCustomer().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("customer") != -1) {
				sbuffer.append(" and customer  like '%" + att_MdOutWarehouse.getCustomer() + "%'");
			} else {
				sbuffer.append(" and customer   ='" + att_MdOutWarehouse.getCustomer() + "'");
			}
		}
		//收货人姓名(customerName)
		if (att_MdOutWarehouse.getCustomerName() != null &&
				!"".equals(att_MdOutWarehouse.getCustomerName().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("customerName") != -1) {
				sbuffer.append(" and customerName  like '%" + att_MdOutWarehouse.getCustomerName() + "%'");
			} else {
				sbuffer.append(" and customerName   ='" + att_MdOutWarehouse.getCustomerName() + "'");
			}
		}
		//状态(1:打开,2生效,3:失效4:拣货中,5:分配中,6:作业中,7作业完成)(state)
		if (att_MdOutWarehouse.getState() != null &&
				!"".equals(att_MdOutWarehouse.getState().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("state") != -1) {
				sbuffer.append(" and state  like '%" + att_MdOutWarehouse.getState() + "%'");
			} else {
				sbuffer.append(" and state   ='" + att_MdOutWarehouse.getState() + "'");
			}
		}
		//描述(description)
		if (att_MdOutWarehouse.getDescription() != null &&
				!"".equals(att_MdOutWarehouse.getDescription().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("description") != -1) {
				sbuffer.append(" and description  like '%" + att_MdOutWarehouse.getDescription() + "%'");
			} else {
				sbuffer.append(" and description   ='" + att_MdOutWarehouse.getDescription() + "'");
			}
		}
		//完成时间(finshDate)
		if (att_MdOutWarehouse.getFinshDate() != null) {
			sbuffer.append(" and  finshDate='" + att_MdOutWarehouse.getFinshDate() + "'");
		}
		//业务人员 /申领人/外借人---相关人员(userId)
		if (att_MdOutWarehouse.getUserName() != null &&
				!"".equals(att_MdOutWarehouse.getUserName().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("userName") != -1) {
				sbuffer.append(" and userName  like '%" + att_MdOutWarehouse.getUserName() + "%'");
			} else {
				sbuffer.append(" and userName   ='" + att_MdOutWarehouse.getUserName() + "'");
			}
		}
		//业务人员所属部门/部门(groupId)
		if (att_MdOutWarehouse.getGroupName() != null &&
				!"".equals(att_MdOutWarehouse.getGroupName().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("groupName") != -1) {
				sbuffer.append(" and groupName  like '%" + att_MdOutWarehouse.getGroupName() + "%'");
			} else {
				sbuffer.append(" and groupName   ='" + att_MdOutWarehouse.getGroupName() + "'");
			}
		}
		//生效时间(activeTime)
		if (att_MdOutWarehouse.getActiveTime() != null) {
			sbuffer.append(" and  activeTime='" + att_MdOutWarehouse.getActiveTime() + "'");
		}
		//修改时间(editDate)
		if (att_MdOutWarehouse.getEditDate() != null) {
			sbuffer.append(" and  editDate='" + att_MdOutWarehouse.getEditDate() + "'");
		}
		//修改人(editRen)
		if (att_MdOutWarehouse.getEditRen() != null &&
				!"".equals(att_MdOutWarehouse.getEditRen().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("editRen") != -1) {
				sbuffer.append(" and editRen  like '%" + att_MdOutWarehouse.getEditRen() + "%'");
			} else {
				sbuffer.append(" and editRen   ='" + att_MdOutWarehouse.getEditRen() + "'");
			}
		}
		//创建时间(createDate)
		if (att_MdOutWarehouse.getCreateDate() != null) {
			sbuffer.append(" and  createDate='" + att_MdOutWarehouse.getCreateDate() + "'");
		}
		//创建人(createRen)
		if (att_MdOutWarehouse.getCreateRen() != null &&
				!"".equals(att_MdOutWarehouse.getCreateRen().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("createRen") != -1) {
				sbuffer.append(" and createRen  like '%" + att_MdOutWarehouse.getCreateRen() + "%'");
			} else {
				sbuffer.append(" and createRen   ='" + att_MdOutWarehouse.getCreateRen() + "'");
			}
		}

		if(att_MdOutWarehouse.getOrderTime_start() != null){
			sbuffer.append(" and orderTime >= '" + sdf.format(att_MdOutWarehouse.getOrderTime_start()) + "'");
		}
		if(att_MdOutWarehouse.getOrderTime_end() != null){
			sbuffer.append(" and orderTime <= '" + sdf.format(att_MdOutWarehouse.getOrderTime_end()) + "'");
		}

		if (map != null && map.get("flowState").toString() != null && !map.get("flowState").toString().equals("")) {
			sbuffer.append(" and relatedBill1 in (select mooCode from MdOutOrder where 1 = 1 ");
			sbuffer.append(" and flowState = " + map.get("flowState").toString());
			sbuffer.append(")");
		}

		//*****************数据库字段处理end***************
		if (orderStr != null && !"".equals(orderStr.trim())) {
			sbuffer.append(" order by " + orderStr);
		}
						 /*
						 else {
							  sbuffer.append( " order by  WowId   desc " );
					      }
					      */

		return sbuffer.toString();
	}
}