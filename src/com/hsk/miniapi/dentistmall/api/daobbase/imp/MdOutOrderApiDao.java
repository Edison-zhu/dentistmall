package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.text.SimpleDateFormat;
import java.util.*;
import com.hsk.supper.dto.comm.PagerModel;

import com.hsk.xframe.api.persistence.SysUserInfo;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.miniapi.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_out_order表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-11-13 11:22:28
 */
@Component
public class MdOutOrderApiDao extends SupperDao implements IMdOutOrderApiDao {

	/**
	 * 根据md_out_order表主键查找MdOutOrder对象记录
	 * 
	 * @param  MooId  Integer类型(md_out_order表主键)
	 * @return MdOutOrder md_out_order表记录
	 * @throws HSKDBException
	 */	
	public MdOutOrder findMdOutOrderById(Integer MooId) throws HSKDBException{
				MdOutOrder  att_MdOutOrder=new MdOutOrder();				
				if(MooId!=null){
					att_MdOutOrder.setMooId(MooId);	
				    Object obj=	this.getOne(att_MdOutOrder);
					if(obj!=null){
						att_MdOutOrder=(MdOutOrder) obj;
					}
				}
				return  att_MdOutOrder;
	}
	 /**
	  * 根据md_out_order表主键删除MdOutOrder对象记录
	  * @param  MooId  Integer类型(md_out_order表主键)
	  * @throws HSKDBException
	  */
	public void deleteMdOutOrderById(Integer MooId) throws HSKDBException{ 
		 MdOutOrder  att_MdOutOrder=new MdOutOrder();	
		  if(MooId!=null){
					  att_MdOutOrder.setMooId(MooId);
				  	  Object obj=	this.getOne(att_MdOutOrder);
					  if(obj!=null){
							this.deleteObject(obj); 
					  }
			}
	}

	/**
 	 * 根据md_out_order表主键修改MdOutOrder对象记录
     * @param  MooId  Integer类型(md_out_order表主键)
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return MdOutOrder  修改后的MdOutOrder对象记录
	 * @throws HSKDBException
	 */
	public  MdOutOrder updateMdOutOrderById(Integer MooId,MdOutOrder att_MdOutOrder) throws HSKDBException{
		  if(MooId!=null){
					att_MdOutOrder.setMooId(MooId);
				   	Object obj=	this.getOne(att_MdOutOrder);
					if(obj!=null){							 
							this.updateObject(obj);
					}
			}
			return  att_MdOutOrder;
	}
	
	/**
	 * 新增md_out_order表 记录
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
	 * @throws HSKDBException
	 */
	public Integer saveMdOutOrder(MdOutOrder att_MdOutOrder) throws HSKDBException{
		 return this.newObject(att_MdOutOrder);
	} 
		
	/**
	 * 新增或修改md_out_order表记录 ,如果md_out_order表主键MdOutOrder.MooId为空就是添加，如果非空就是修改
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
	 * @throws HSKDBException
	 */
	public  MdOutOrder saveOrUpdateMdOutOrder(MdOutOrder att_MdOutOrder) throws HSKDBException{
		  this.getHibernateTemplate().saveOrUpdate(att_MdOutOrder);
		  return att_MdOutOrder;
	}
	
	/**
	 *根据MdOutOrder对象作为对(md_out_order表进行查询，获取列表对象
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return List<MdOutOrder>  列表对象
	 * @throws HSKDBException 
	 */
	 @SuppressWarnings("unchecked")
	public List<MdOutOrder> getListByMdOutOrder(MdOutOrder att_MdOutOrder) throws HSKDBException{
		String Hql=this.getHql( att_MdOutOrder, null);
		List<MdOutOrder> list=this.getHibernateTemplate().find(Hql);
		//list.add((MdOutOrder) Restrictions.sqlRestriction("select 123"));
		return  list;
	}

	@Override
	public PagerModel getPagerModelByMdOutOrderDistinct(MdOutOrder att_mdOutOrder, String distinctName) throws HSKDBException {
	 	StringBuffer sbuffer = new StringBuffer("select distinct " + distinctName + " from  MdOutOrder  where  1=1  ");
		String Hql=this.getHql(att_mdOutOrder, sbuffer);
		return this.getHibernateDao().findByPage(Hql);
	}

	/**
	 *根据MdOutOrder对象作为对(md_out_order表进行查询，获取分页对象
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return List<MdOutOrder>  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdOutOrder(MdOutOrder att_MdOutOrder)
			throws HSKDBException {
		String Hql=this.getHql(att_MdOutOrder, null);
		return this.getHibernateDao().findByPage(Hql); 
	} 
	/**
	 * 根据MdOutOrder对象获取Hql字符串
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
	 *  @return hql字符串
	 */
	private String  getHql(MdOutOrder att_MdOutOrder, StringBuffer sbuffer){
		if(sbuffer == null) {
			sbuffer = new StringBuffer(" from  MdOutOrder  where  1=1  ");
		}
		String likeStr = att_MdOutOrder.getTab_like();
		String orderStr = att_MdOutOrder.getTab_order();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		//*****************无关字段处理begin*****************
		//处理in条件 ID(mooId)
		if (att_MdOutOrder.getFlowState_str() != null && !"".equals(att_MdOutOrder.getFlowState_str().trim())) {
			String intStr = att_MdOutOrder.getFlowState_str().trim();
			sbuffer.append(" and flowState in (" + intStr + ") ");

		}
		if (att_MdOutOrder.getMooId_str() != null &&
				!"".equals(att_MdOutOrder.getMooId_str().trim())) {
			String intStr = att_MdOutOrder.getMooId_str().trim();
			String[] arrayStr = intStr.split(",");

			if (arrayStr.length > 0) {
				sbuffer.append(" and ( ");
				for (int i = 0; i < arrayStr.length; i++) {
					String did = arrayStr[i];
					if (i == arrayStr.length - 1) {
						sbuffer.append("  mooId=" + did + "   ");
					} else {
						sbuffer.append("  mooId=" + did + " or ");
					}
				}
				sbuffer.append(" ) ");
			}

		}
		//处理in条件 集团公司id(rbaId)
		if (att_MdOutOrder.getRbaId_str() != null &&
				!"".equals(att_MdOutOrder.getRbaId_str().trim())) {
			String intStr = att_MdOutOrder.getRbaId_str().trim();
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
		if (att_MdOutOrder.getRbsId_str() != null &&
				!"".equals(att_MdOutOrder.getRbsId_str().trim())) {
			String intStr = att_MdOutOrder.getRbsId_str().trim();
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
		if (att_MdOutOrder.getRbbId_str() != null &&
				!"".equals(att_MdOutOrder.getRbbId_str().trim())) {
			String intStr = att_MdOutOrder.getRbbId_str().trim();
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
		//处理in条件 操作人ID(suiId)
		if (att_MdOutOrder.getSuiId_str() != null &&
				!"".equals(att_MdOutOrder.getSuiId_str().trim())) {
			String intStr = att_MdOutOrder.getSuiId_str().trim();
			String[] arrayStr = intStr.split(",");

			if (arrayStr.length > 0) {
				sbuffer.append(" and ( ");
				for (int i = 0; i < arrayStr.length; i++) {
					String did = arrayStr[i];
					if (i == arrayStr.length - 1) {
						sbuffer.append("  suiId=" + did + "   ");
					} else {
						sbuffer.append("  suiId=" + did + " or ");
					}
				}
				sbuffer.append(" ) ");
			}

		}
		//时间类型开始条件处理  申领时间(orderTime)
		if (att_MdOutOrder.getOrderTime_start() != null) {
			sbuffer.append(" and  orderTime>='" + sdf.format(att_MdOutOrder.getOrderTime_start()) + " 00:00:00'");
		}
		//时间类型结束条件处理 申领时间(orderTime)
		if (att_MdOutOrder.getOrderTime_end() != null) {
			sbuffer.append(" and  orderTime<='" + sdf.format(att_MdOutOrder.getOrderTime_end()) + " 23:59:59'");
		}
		//时间类型开始条件处理  修改时间(editDate)
		if (att_MdOutOrder.getEditDate_start() != null) {
			sbuffer.append(" and  editDate<='" + att_MdOutOrder.getEditDate_start() + "'");
		}
		//时间类型结束条件处理 修改时间(editDate)
		if (att_MdOutOrder.getEditDate_end() != null) {
			sbuffer.append(" and  editDate>'" + att_MdOutOrder.getEditDate_end() + "'");
		}
		//时间类型开始条件处理  创建时间(createDate)
		if (att_MdOutOrder.getCreateDate_start() != null) {
			sbuffer.append(" and  createDate<='" + att_MdOutOrder.getCreateDate_start() + "'");
		}
		//时间类型结束条件处理 创建时间(createDate)
		if (att_MdOutOrder.getCreateDate_end() != null) {
			sbuffer.append(" and  createDate>'" + att_MdOutOrder.getCreateDate_end() + "'");
		}
		//*****************无关字段处理end*****************
		//*****************数据库字段处理begin*************
		//ID(mooId)
		if (att_MdOutOrder.getMooId() != null) {
			sbuffer.append(" and mooId=" + att_MdOutOrder.getMooId());
		}
		//集团公司id(rbaId)
		if (att_MdOutOrder.getRbaId() != null) {
			sbuffer.append(" and rbaId=" + att_MdOutOrder.getRbaId());
		}
		//牙医医院id(rbsId)
		if (att_MdOutOrder.getRbsId() != null) {
			sbuffer.append(" and rbsId=" + att_MdOutOrder.getRbsId());
		}
		//牙医门诊id(rbbId)
		if (att_MdOutOrder.getRbbId() != null) {
			sbuffer.append(" and rbbId=" + att_MdOutOrder.getRbbId());
		}
		//操作人ID(suiId)
		if (att_MdOutOrder.getSuiId() != null) {
			sbuffer.append(" and suiId=" + att_MdOutOrder.getSuiId());
		}
		//采购单位类型(purchaseType)
		if (att_MdOutOrder.getPurchaseType() != null &&
				!"".equals(att_MdOutOrder.getPurchaseType().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("purchaseType") != -1) {
				sbuffer.append(" and purchaseType  like '%" + att_MdOutOrder.getPurchaseType() + "%'");
			} else {
				sbuffer.append(" and purchaseType   ='" + att_MdOutOrder.getPurchaseType() + "'");
			}
		}
		//申领单号(mooCode)
		if (att_MdOutOrder.getMooCode() != null &&
				!"".equals(att_MdOutOrder.getMooCode().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("mooCode") != -1) {
				sbuffer.append(" and mooCode  like '%" + att_MdOutOrder.getMooCode().toUpperCase() + "%'");
			} else {
				sbuffer.append(" and mooCode   ='" + att_MdOutOrder.getMooCode() + "'");
			}
		}
		//业务人员 /申领人/外借人---相关人员(userName)
		if (att_MdOutOrder.getUserName() != null &&
				!"".equals(att_MdOutOrder.getUserName().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("userName") != -1) {
				sbuffer.append(" and userName  like '%" + att_MdOutOrder.getUserName() + "%'");
			} else {
				sbuffer.append(" and userName   ='" + att_MdOutOrder.getUserName() + "'");
			}
		}
		//业务人员所属部门/部门(groupName)
		if (att_MdOutOrder.getGroupName() != null &&
				!"".equals(att_MdOutOrder.getGroupName().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("groupName") != -1) {
				sbuffer.append(" and groupName  like '%" + att_MdOutOrder.getGroupName() + "%'");
			} else {
				sbuffer.append(" and groupName   ='" + att_MdOutOrder.getGroupName() + "'");
			}
		}
		//申领时间(orderTime)
		if (att_MdOutOrder.getOrderTime() != null) {
			sbuffer.append(" and  orderTime='" + att_MdOutOrder.getOrderTime() + "'");
		}
		//状态(state)
		if (att_MdOutOrder.getState() != null &&
				!"".equals(att_MdOutOrder.getState().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("state") != -1) {
				sbuffer.append(" and state  like '%" + att_MdOutOrder.getState() + "%'");
			} else {
				sbuffer.append(" and state   ='" + att_MdOutOrder.getState() + "'");
			}
		}
		//流程状态(flowState)
		if (att_MdOutOrder.getFlowState() != null &&
				!"".equals(att_MdOutOrder.getFlowState().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("flowState") != -1) {
				sbuffer.append(" and flowState  like '%" + att_MdOutOrder.getFlowState() + "%'");
			} else {
				sbuffer.append(" and flowState   ='" + att_MdOutOrder.getFlowState() + "'");
			}
		}
		//修改时间(editDate)
		if (att_MdOutOrder.getEditDate() != null) {
			sbuffer.append(" and  editDate='" + att_MdOutOrder.getEditDate() + "'");
		}
		//修改人(editRen)
		if (att_MdOutOrder.getEditRen() != null &&
				!"".equals(att_MdOutOrder.getEditRen().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("editRen") != -1) {
				sbuffer.append(" and editRen  like '%" + att_MdOutOrder.getEditRen() + "%'");
			} else {
				sbuffer.append(" and editRen   ='" + att_MdOutOrder.getEditRen() + "'");
			}
		}
		//创建时间(createDate)
		if (att_MdOutOrder.getCreateDate() != null) {
			sbuffer.append(" and  createDate='" + att_MdOutOrder.getCreateDate() + "'");
		}
		//创建人(createRen)
		if (att_MdOutOrder.getCreateRen() != null &&
				!"".equals(att_MdOutOrder.getCreateRen().trim())) {
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("createRen") != -1) {
				sbuffer.append(" and createRen  like '%" + att_MdOutOrder.getCreateRen() + "%'");
			} else {
				sbuffer.append(" and createRen   ='" + att_MdOutOrder.getCreateRen() + "'");
			}
		}

		if (att_MdOutOrder.getOutTimeStart() != null || att_MdOutOrder.getOrderTime_end() != null) {
			sbuffer.append(" and mooCode in (select relatedBill1 from MdOutWarehouse where 1 = 1 ");
			if(att_MdOutOrder.getOutTimeStart() != null) {
				sbuffer.append(" and createDate >= '" + sdf.format(att_MdOutOrder.getOutTimeStart()) + "'");
			}
			if(att_MdOutOrder.getOrderTime_end() != null) {
				sbuffer.append(" and createDate <= '" + sdf.format(att_MdOutOrder.getOrderTime_end()) + "'");
			}
			sbuffer.append(")");
		}


		//*****************数据库字段处理end***************
		if (orderStr != null && !"".equals(orderStr.trim())) {
			sbuffer.append(" order by " + orderStr);
		}
						 /*
						 else {
							  sbuffer.append( " order by  MooId   desc " );
					      }
					      */

		return sbuffer.toString();
	}
	/**
	 * yanglei 2020-3-25
	 */
	public List<Map<String, Object>> departmentStatisticalReport(Integer value) throws HSKDBException {
		Date orderDate=new Date();
		Date yesterday=new Date(orderDate.getTime()- 24*60*60*1000);
		Date sevendays=new Date(orderDate.getTime()- 24*60*60*1000*7);
		Date month=new Date(orderDate.getTime()- 24*60*60*1000*20-24*60*60*1000*10);
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String sql="SELECT user_name as userName,COUNT(user_name) as count FROM md_out_order WHERE 1 = 1";
		//order_time>"2020-01-01 10:14:44"  GROUP BY user_name
		if (value==0){
			sql+=" AND order_time>='"+sf.format(orderDate)+" 00:00:00'";
			sql+=" AND order_time<='"+sf.format(orderDate)+" 23:59:59'";
		}if (value==1){
			sql+=" AND order_time>='"+sf.format(yesterday)+" 00:00:00'";
			sql+=" AND order_time<='"+sf.format(yesterday)+" 23:59:59'";
		}if (value==2){
			sql+=" AND order_time>='"+sf.format(sevendays)+" 00:00:00'";
			sql+=" AND order_time<='"+sf.format(orderDate)+" 23:59:59'";
		}
		if (value==3){
			sql+=" AND order_time>='"+sf.format(month)+" 00:00:00'";
			sql+=" AND order_time<='"+sf.format(orderDate)+" 23:59:59'";
		}
		sql+=" GROUP BY user_name ORDER BY COUNT(user_name)";
		return this.getJdbcDao().query(sql);
	}

	//待申领出库数据
	public Double outStock(Integer SuiId)throws HSKDBException{
		String sql="SELECT COUNT(moo_code) as number1 from md_out_order where 1=1 and flow_state in(2,3) AND state=1 AND rbs_id = 22 AND flow_state = '2' ";
		sql+="  AND rbs_id="+SuiId;
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Double.parseDouble(list.get(0).get("number1").toString());
		}

		return 0.00;
	}
	//订单待入库
	public Double warehouse(Integer SuiId,Integer rbsId)throws HSKDBException{
		String sql="select COUNT(*) as expectNumber FROM md_order_info WHERE 1=1  AND purchase_type = '2'  AND order_state = '1'  AND Process_status = '4'";
		sql+=" AND rbs_id="+rbsId;
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Double.parseDouble(list.get(0).get("expectNumber").toString());
		}
		return 0.00;
	}
	//退货出库
	public Double returnStock(Integer SuiId,Integer rbsId)throws HSKDBException{
		String sql="SELECT SUM(mo.base_number) as baseNumber from  md_outwarehouse_outorder  mo where 1=1 AND mo.purchase_type ='2' AND mo.COMPANY_type='2' AND mo.state=1";
		sql+=" AND mo.rbs_id="+rbsId;
		sql+=" AND mo.sui_id="+SuiId;
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if (list==null||list.size()<=0){
			return 0.00;
		}
		Object obj = list.get(0).get("baseNumber");
		if (obj!=null){
			return Double.parseDouble(obj.toString());
		}
		return 0.00;
	}
	//申领库存告警
	public Double stockAlarm(Integer SuiId,Integer rbsId)throws HSKDBException{
	String  sql="SELECT COUNT(mv.warning_shu-mv.base_number) as waringBasenumber FROM md_inventory mv where 1=1 AND mv.state=1  AND ( mv.purchase_type LIKE '%2%' ) AND (mv.warning_shu-mv.base_number)>=0";
		sql+=" AND mv.rbs_id= "+rbsId;
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if (list==null||list.size()<=0){
			return 0.00;
		}
		Object obj = list.get(0).get("waringBasenumber");
		if (obj!=null){
			return Double.parseDouble(obj.toString());
		}
		return 0.00;
	}
	//安全库存告警
	public Double safetyStockAlarm(Integer SuiId,Integer rbsId)throws HSKDBException{
		String sql="SELECT COUNT(mor.number1-mor.number2) as LackNumber from md_out_order mor where mor.flow_state IN ( 2, 3 ) AND mor.state = '1'  AND  mor.number1-mor.number2>0";
		sql+=" AND mor.rbs_id = "+rbsId;
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if (list==null||list.size()<=0){
			return 0.00;
		}
		if (list.get(0).get("LackNumber")!=null){
			return Double.parseDouble(list.get(0).get("LackNumber").toString());
		}
		return 0.00;
	}

	//导出缺少数量
	public  List<Map<String, Object>> exportCumulativeWarning() throws HSKDBException{
		String sql="SELECT t2.wms_mi_id, t2.mat_name AS matName,t2.NORM as norm, (IFNULL(t2.base_number,0) -IFNULL(t2.number1,0)) as lack ,t3.Label_info AS mmiLable,t4.mmf_code AS mmfCode FROM  md_out_order t1  ";
		sql+=" LEFT JOIN md_out_order_mx t2 ON t1.moo_id=t2.moo_id";
		sql+=" LEFT JOIN md_materiel_info t3 ON t2.wms_mi_id = t3.wms_mi_id ";
		sql+=" LEFT JOIN md_materiel_format t4 ON  t2.wms_mi_id=t4.wms_mi_id ";
		sql+=" WHERE t1.flow_state IN ( 2, 3 )  AND t1.state = '1' AND (IFNULL(t2.base_number,0) >IFNULL(t2.number1,0)) AND t2.NORM=t4.mmf_name " ;
		return this.getJdbcDao().query(sql);
	}
	//7天实时申领动态
	public List<Map<String, Object>> sevenClaimant(Integer limit, Integer page,Integer suiId) throws HSKDBException{
		Date date =new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sevenDate=new Date(date.getTime()- 24*60*60*1000*7-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10-24*60*60*1000*20-24*60*60*1000*10);
		String sql="SELECT mdoutorder0_.moo_id as mooId,mdoutorder0_.flow_state AS flowState,mdoutorder0_.moo_code AS mooCode,mdoutorder0_.number1 AS number1,mdoutorder0_.number2 AS number2,mdoutorder0_.order_time AS orderTime,mdoutorder0_.remarks AS remarks FROM ";
		sql+=" md_out_order mdoutorder0_  WHERE 1 = 1 ";
		sql+=" AND mdoutorder0_.rbs_id = 22 ";
		sql+=" AND mdoutorder0_.sui_id = '"+suiId+" '";
		sql+=" AND mdoutorder0_.purchase_type = '2'";
		sql+=" AND mdoutorder0_.state = '1' ";
		sql+=" AND (mdoutorder0_.IsItRead <>'1' OR mdoutorder0_.IsItRead  is null) ";
		sql+= " AND mdoutorder0_.order_time >= '"+sdf.format(sevenDate)+" 00:00:00 '";
		sql+= " AND mdoutorder0_.order_time <= '"+sdf.format(date)+" 23:59:59 '";
		sql+="ORDER BY mdoutorder0_.order_time DESC ";
		if(limit != null && page != null) {
			sql += " limit " + (page - 1) * limit + "," + limit;
		}
		return this.getJdbcDao().query(sql);
	}
	//申领人申领中订单
	public Double Claim(Integer SuiId)throws HSKDBException{
		String sql="SELECT COUNT(mdoutorder0_.moo_code) AS CountMooCode1 FROM md_out_order mdoutorder0_  where 1=1";
		sql+=" AND mdoutorder0_.rbs_id = 22";
		sql+=" AND mdoutorder0_.sui_id = '"+SuiId+"'";
		sql+=" AND mdoutorder0_.state = '1'";
		sql+=" AND mdoutorder0_.flow_state IN(2)";
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Double.parseDouble(list.get(0).get("CountMooCode1").toString());
		}
		return 0.00;
	}
	//申领人部分出库订单
	public Double Partial(Integer SuiId)throws HSKDBException{
		String sql="SELECT COUNT(mdoutorder0_.moo_code) AS CountMooCode2 FROM md_out_order mdoutorder0_  where 1=1";
		sql+=" AND mdoutorder0_.rbs_id = 22";
		sql+=" AND mdoutorder0_.sui_id = '"+SuiId+"'";
		sql+=" AND mdoutorder0_.state = '1'";
		sql+=" AND mdoutorder0_.flow_state IN(3)";
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Double.parseDouble(list.get(0).get("CountMooCode2").toString());
		}
		return 0.00;
	}
	//申领人已完成出库订单
	public Double CompleteOut(Integer SuiId)throws HSKDBException{
		String sql="SELECT COUNT(mdoutorder0_.moo_code) AS CountMooCode3 FROM md_out_order mdoutorder0_  where 1=1";
		sql+=" AND mdoutorder0_.rbs_id = 22";
		sql+=" AND mdoutorder0_.sui_id = '"+SuiId+"'";
		sql+=" AND mdoutorder0_.state = '1'";
		sql+=" AND mdoutorder0_.flow_state IN(4)";
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Double.parseDouble(list.get(0).get("CountMooCode3").toString());
		}
		return 0.00;
	}
	//7天实时申领动态 详情
	public List<Map<String, Object>> sevenOutMx(Integer mooId) throws HSKDBException{
		Date date =new Date();
		String sql=" ";
		sql+="select t1.mat_name matName, t1.NORM as norm,t1.base_number as baseNumber,(t1.base_number-IF(number1 is null, 0, number1)) as lackNumber FROM md_out_order_mx t1 where 1=1 ";
		sql+=" AND t1.moo_id= "+mooId;
		return this.getJdbcDao().query(sql);
	}

}