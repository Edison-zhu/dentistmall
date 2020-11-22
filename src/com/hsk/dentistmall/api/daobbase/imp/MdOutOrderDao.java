package com.hsk.dentistmall.api.daobbase.imp;

import java.text.SimpleDateFormat;
import java.util.*;

import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;

import com.hsk.xframe.api.persistence.SysUserInfo;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.dentistmall.api.persistence.*;

/** 
 * md_out_order表数据库层面操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-11-13 11:22:28
 */
@Component
public class  MdOutOrderDao extends SupperDao implements IMdOutOrderDao {	

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
	//通过ID查询申领日志表
	public MdOutOrderLog findMdOutOrderLogById(Integer moologId) throws HSKDBException{
		MdOutOrderLog  att_MdOutOrderLog=new MdOutOrderLog();
		if(moologId!=null){
			att_MdOutOrderLog.setMoologId(moologId);
			Object obj=	this.getOne(att_MdOutOrderLog);
			if(obj!=null){
				att_MdOutOrderLog=(MdOutOrderLog) obj;
			}
		}
		return  att_MdOutOrderLog;
	}
	//保存申领日志表
	public Integer saveMdOutOrderLog(MdOutOrderLog att_MdOutOrderLog) throws HSKDBException{
		return this.newObject(att_MdOutOrderLog);
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

	@Override
	public PagerModel getPagerModelOutWare(MdOutOrder att_MdOutOrder, Integer rbaId, Integer rbsId, Integer rbbId, Integer warning) throws HSKDBException {
//		StringBuffer sbuffer = new StringBuffer("select c.* from (select b.moo_id as mooId, date_format(b.order_time, '%Y-%m-%d %H:%i:%s' ) AS orderTime, b.moo_code AS mooCode, b.group_name AS groupName, b.user_name AS userName," +
//				"b.number1 AS number1, b.number2 AS number2, b.flow_state AS flowState, a.wow_id AS wowId, a.wow_remarks AS wowRemarks," +
//				"a.wow_type AS wowType, a.wow_code AS wowCode, a.receiving_object AS receivingObject, date_format( a.finsh_date, '%Y-%m-%d %H:%i:%s' ) AS finishDate," +
//				"a.RELATED_BILL1 AS relatedBill1 , a.rba_id as rbaId, a.rbs_id as rbsId, a.rbb_id as rbbId, a.purchase_type as purchaseType, a.create_date as createDate," +
//				" NULL as moiId, NULL as wiId, NULL as wewId" +
//				" FROM md_out_order b LEFT JOIN md_out_warehouse a ON b.moo_code = a.RELATED_BILL1 GROUP BY b.moo_id" +
//				" UNION ALL" +
//				" SELECT NULL as mooId, a.create_date AS orderTime, NULL AS mooCode, a.group_name AS groupName, a.user_name AS userName, a.base_number AS number1, a.base_number AS number2, 4 AS flowState," +
//				" a.wow_id AS wowId, a.wow_remarks AS wowRemarks, a.wow_type AS wowType, a.wow_code AS wowCode, a.receiving_object AS receivingObject," +
//				" date_format( a.finsh_date, '%Y-%m-%d %H:%i:%s' ) AS finishDate, a.RELATED_BILL1 AS relatedBill1 , a.rba_id as rbaId, a.rbs_id as rbsId," +
//				" a.rbb_id as rbbId, a.purchase_type as purchaseType, a.create_date as createDate, a.moi_id as moiId, a.wi_id as wiId, a.wew_id as wewId" +
//				" FROM md_out_warehouse a " +
//				" WHERE LEFT ( RELATED_BILL1, 2 ) <> 'CK'  OR RELATED_BILL1 IS NULL) c where 1=1");
//		if (rbaId != null)
//			sbuffer.append(" and c.rbaId = " + rbaId);
//		if (rbsId != null)
//			sbuffer.append(" and c.rbsId = " + rbsId);
//		if (rbbId != null)
//			sbuffer.append(" and c.rbbId = " + rbbId);
//
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		if (att_MdOutOrder.getOrderTime_start() != null || att_MdOutOrder.getOrderTime_end() != null) {
//			if (att_MdOutOrder.getOrderTime_start() != null) {
//				sbuffer.append(" and  (c.orderTime>='" + sdf.format(att_MdOutOrder.getOrderTime_start()) + " 00:00:00'");
//			}
//			//时间类型结束条件处理 申领时间(orderTime)
//			if (att_MdOutOrder.getOrderTime_end() != null) {
//				sbuffer.append(" and  c.orderTime<='" + sdf.format(att_MdOutOrder.getOrderTime_end()) + " 23:59:59'");
//			}
//			sbuffer.append(")");
//		}
//
//		if (att_MdOutOrder.getOutTimeStart() != null || att_MdOutOrder.getOrderTime_end() != null) {
//			if(att_MdOutOrder.getOutTimeStart() != null) {
//				sbuffer.append(" and (c.createDate >= '" + sdf.format(att_MdOutOrder.getOutTimeStart()) + " 00:00:00'");
//			}
//			if(att_MdOutOrder.getOrderTime_end() != null) {
//				sbuffer.append(" and c.createDate <= '" + sdf.format(att_MdOutOrder.getOrderTime_end()) + " 23:59:59'");
//			}
//			sbuffer.append(")");
//		}
//
//		if (att_MdOutOrder.getMooCode() != null &&
//				!"".equals(att_MdOutOrder.getMooCode().trim())) {
//			sbuffer.append(" and c.mooCode  like '%" + att_MdOutOrder.getMooCode().toUpperCase() + "%'");
//		}
//		//业务人员 /申领人/外借人---相关人员(userName)
//		if (att_MdOutOrder.getUserName() != null &&
//				!"".equals(att_MdOutOrder.getUserName().trim())) {
//			sbuffer.append(" and c.userName  like '%" + att_MdOutOrder.getUserName() + "%'");
//		}
//		//业务人员所属部门/部门(groupName)
//		if (att_MdOutOrder.getGroupName() != null &&
//				!"".equals(att_MdOutOrder.getGroupName().trim())) {
//			sbuffer.append(" and c.groupName  like '%" + att_MdOutOrder.getGroupName() + "%'");
//		}
//
//		//流程状态(flowState)
//		if (att_MdOutOrder.getFlowState() != null &&
//				!"".equals(att_MdOutOrder.getFlowState().trim())) {
//			sbuffer.append(" and c.flowState   ='" + att_MdOutOrder.getFlowState() + "'");
//		}
//
//		sbuffer.append(" order by c.orderTime desc");
//		getSql(att_MdOutOrder, rbaId, rbsId, rbbId);
		String hql = getSql(att_MdOutOrder, rbaId, rbsId, rbbId, warning);
		List<Map<String, Object>> cList = this.getJdbcDao().query("select count(*) as totalCount from (" + hql + ") as t");
		hql += " limit " + ((SystemContext.getOffset() == 0 ? 1 : SystemContext.getOffset()) - 1) * SystemContext.getPageSize() + "," + SystemContext.getPageSize();
		List<Map<String, Object>> list = this.getJdbcDao().query(hql);
		PagerModel pm = new PagerModel();
		pm.setRows(list);
		pm.setItems(list);

		Integer count = 0;
		if (cList == null || cList.isEmpty())
			count = 0;
		Map<String, Object> map = cList.get(0);
		if (map == null || map.isEmpty())
			count = 0;
		else
			count = Integer.parseInt(map.get("totalCount").toString());
		pm.setTotalCount(count);
		pm.setTotal(count);
		return pm;
	}

	private String getSql(MdOutOrder att_MdOutOrder, Integer rbaId, Integer rbsId, Integer rbbId, Integer warning) {
		StringBuffer sbuffer = new StringBuffer("select c.* from (select b.moo_id as mooId, date_format(b.order_time, '%Y-%m-%d %H:%i:%s' ) AS orderTime, b.moo_code AS mooCode, b.group_name AS groupName, b.user_name AS userName," +
				"b.number1 AS number1, b.number2 AS number2, b.number6 as number6, b.number7 as number7, b.flow_state AS flowState, a.wow_id AS wowId, a.wow_remarks AS wowRemarks," +
				"a.wow_type AS wowType, a.wow_code AS wowCode, a.receiving_object AS receivingObject, date_format( a.finsh_date, '%Y-%m-%d %H:%i:%s' ) AS finishDate," +
				"a.RELATED_BILL1 AS relatedBill1 , b.rba_id as rbaId, b.rbs_id as rbsId, b.rbb_id as rbbId, b.purchase_type as purchaseType, a.create_date as createDate," +
				" NULL as moiId, NULL as wiId, NULL as wewId, b.create_ren as createRen, null as missNumber, b.state as state " +
				" FROM md_out_order b LEFT JOIN md_out_warehouse a ON b.moo_code = a.RELATED_BILL1 where (b.number1 > 0 or b.number6 > 0) and b.flow_state <> 4 GROUP BY b.moo_id" +
				" UNION ALL" +
				" SELECT NULL as mooId, if(d.order_time is null, date_format(a.create_date, '%Y-%m-%d %H:%i:%s' ), date_format(d.order_time, '%Y-%m-%d %H:%i:%s' )) AS orderTime, a.RELATED_BILL1 AS mooCode, a.group_name AS groupName, if(d.user_name is null, a.user_name, d.user_name) AS userName, " +
//				" if(d.number1 is null, a.base_number, d.number1) AS number1, a.base_number AS number2, if(d.flow_state is null, 4, d.flow_state) AS flowState," +
				" a.base_number AS number1, a.base_number AS number2, d.number6 as number6, d.number7 as number7, 4 AS flowState," +
				" a.wow_id AS wowId, a.wow_remarks AS wowRemarks, a.wow_type AS wowType, a.wow_code AS wowCode, a.receiving_object AS receivingObject," +
				" date_format( a.finsh_date, '%Y-%m-%d %H:%i:%s' ) AS finishDate, a.RELATED_BILL1 AS relatedBill1 , a.rba_id as rbaId, a.rbs_id as rbsId," +
				" a.rbb_id as rbbId, a.purchase_type as purchaseType, a.create_date as createDate, a.moi_id as moiId, a.wi_id as wiId, a.wew_id as wewId, a.create_ren as createRen," +
				" 0 as missNumber, 1 as state " +
				"FROM md_out_warehouse a left join md_out_order d on d.moo_code = a.RELATED_BILL1) c where 1=1 ");// +
//				"FROM md_out_warehouse a) c where 1=1 ");
				//" WHERE LEFT ( RELATED_BILL1, 2 ) <> 'CK'  OR RELATED_BILL1 IS NULL) c where 1=1");
		if (rbaId != null)
			sbuffer.append(" and c.rbaId = " + rbaId);
		if (rbsId != null)
			sbuffer.append(" and c.rbsId = " + rbsId);
		if (rbbId != null)
			sbuffer.append(" and c.rbbId = " + rbbId);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sbuffer.append(" and (1=1");
		if (att_MdOutOrder.getOrderTime_start() != null) {
			sbuffer.append(" and  c.orderTime>='" + sdf.format(att_MdOutOrder.getOrderTime_start()) + "'");
		}
		//时间类型结束条件处理 申领时间(orderTime)
		if (att_MdOutOrder.getOrderTime_end() != null) {
			sbuffer.append(" and  c.orderTime<='" + sdf.format(att_MdOutOrder.getOrderTime_end()) + "'");
		}
		sbuffer.append(")");
		sbuffer.append(" and (1=1");
		if (att_MdOutOrder.getOutTimeStart() != null) {
			sbuffer.append(" and c.finishDate >= '" + sdf.format(att_MdOutOrder.getOutTimeStart()) + "'");
		}
		if (att_MdOutOrder.getOutTimeEnd() != null) {
			sbuffer.append(" and c.finishDate <= '" + sdf.format(att_MdOutOrder.getOutTimeEnd()) + "'");
		}
		sbuffer.append(")");

		if (att_MdOutOrder.getMooCode() != null &&
				!"".equals(att_MdOutOrder.getMooCode().trim())) {
			sbuffer.append(" and c.mooCode  like '%" + att_MdOutOrder.getMooCode().toUpperCase() + "%'");
		}
		//业务人员 /申领人/外借人---相关人员(userName)
		if (att_MdOutOrder.getUserName() != null &&
				!"".equals(att_MdOutOrder.getUserName().trim())) {
			sbuffer.append(" and c.userName  like '%" + att_MdOutOrder.getUserName() + "%'");
		}
		//业务人员所属部门/部门(groupName)
		if (att_MdOutOrder.getGroupName() != null &&
				!"".equals(att_MdOutOrder.getGroupName().trim())) {
			sbuffer.append(" and c.groupName  like '%" + att_MdOutOrder.getGroupName() + "%'");
		}

		//流程状态(flowState)
		if (att_MdOutOrder.getFlowState() != null &&
				!"".equals(att_MdOutOrder.getFlowState().trim())) {
			sbuffer.append(" and c.flowState   ='" + att_MdOutOrder.getFlowState() + "'");
		}
		if (att_MdOutOrder.getWowType() != null) {
			if (att_MdOutOrder.getWowType().equals("2"))
				sbuffer.append(" and c.wowType in (2,4)");
			else if (att_MdOutOrder.getWowType().equals("1"))
				sbuffer.append(" and (c.wowType = 1 or c.wowType is null)");
			else
				sbuffer.append(" and c.wowType = " + att_MdOutOrder.getWowType());
		}
		if (att_MdOutOrder.getMooId_str() != null && !att_MdOutOrder.getMooId_str().equals(""))
			sbuffer.append(" and c.mooId in (" + att_MdOutOrder.getMooId_str() + ")");
		if (warning != null)
			sbuffer.append("");
		sbuffer.append(" and state = 1");
		sbuffer.append(" order by c.orderTime desc");
		return sbuffer.toString();
	}

	/**
	 * 根据MdOutOrder对象获取Hql字符串
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
	 *  @return hql字符串
	 */
	private String  getHql(MdOutOrder att_MdOutOrder, StringBuffer sbuffer){
		if(sbuffer == null) {
			sbuffer = new StringBuffer(" from  MdOutOrder  where  1=1  ");
//			if (att_MdOutOrder.)
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
			if (likeStr != null && !"".equals(likeStr.trim()) && likeStr.indexOf("orderTime") != -1)
				sbuffer.append(" and orderTime like '" + sdf.format(att_MdOutOrder.getOrderTime()) + "%'");
			else
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
	public List<Map<String, Object>> departmentStatisticalReport(Integer value,Integer rbaId,Integer rbsId,Integer rbbId) throws HSKDBException {
		Date orderDate=new Date();
		Date yesterday=new Date(orderDate.getTime()- 24*60*60*1000);
		Date sevendays=new Date(orderDate.getTime()- 24*60*60*1000*7);
		Date month=new Date(orderDate.getTime()- 24*60*60*1000*20-24*60*60*1000*10);
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String sql="SELECT user_name as userName,COUNT(user_name) as count FROM md_out_order WHERE 1 = 1";
		if (rbaId!=null){
			sql+=" AND rba_id="+rbaId;
		}
		if (rbsId!=null){
			sql+=" AND rbs_id="+rbsId;
		}
		if (rbbId!=null){
			sql+=" AND rbb_id="+rbbId;
		}
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
	public Double outStock(Integer SuiId, Integer rbaId, Integer rbsId, Integer rbbId)throws HSKDBException{
		String sql="SELECT COUNT(moo_code) as number1 from md_out_order where 1=1 and state=1 AND flow_state = '2' and number1 > 0 ";
//		sql+="  AND rbs_id="+SuiId;
		if (rbaId != null) {
			sql += " and rba_id = " + rbaId;
		}
		if (rbsId != null) {
			sql += " and rbs_id = " + rbsId;
		}
		if (rbbId != null) {
			sql += " and rbb_id = " + rbbId;
		}
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Double.parseDouble(list.get(0).get("number1").toString());
		}

		return 0.00;
	}
	//订单待入库
	public Double warehouse(Integer SuiId, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType)throws HSKDBException{
		String sql="select COUNT(*) as expectNumber FROM md_order_info WHERE 1=1  AND order_state = '1'";//  AND Process_status = '5' and number1 > 0";
		sql += " and (Process_status not in (6) and number2 is not null and (number3<number2 or number3 is null))";
//		sql+=" AND rbs_id="+rbsId;
		if (rbaId != null) {
			sql += " and rba_id = " + rbaId;
		}
		if (rbsId != null) {
			sql += " and rbs_id = " + rbsId;
		}
		if (rbbId != null) {
			sql += " and rbb_id = " + rbbId;
		}
		if (purchaseType != null)
			sql += " and purchase_type = '" + purchaseType + "'";
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Double.parseDouble(list.get(0).get("expectNumber").toString());
		}
		return 0.00;
	}
	//退货出库
	public Double returnStock(Integer SuiId, Integer rbaId, Integer rbsId, Integer rbbId)throws HSKDBException{
		String sql="SELECT count(*) as baseNumber from  md_out_warehouse  mo where 1=1 AND (mo.wow_type=2 or mo.wow_type=4) AND mo.state=1";
//		sql+=" AND mo.rbs_id="+rbsId;
//		sql+=" AND mo.sui_id="+SuiId;
		if (rbaId != null) {
			sql += " and rba_id = " + rbaId;
		}
		if (rbsId != null) {
			sql += " and rbs_id = " + rbsId;
		}
		if (rbbId != null) {
			sql += " and rbb_id = " + rbbId;
		}
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
	public Double stockAlarm(Integer SuiId, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType)throws HSKDBException{
	String  sql="SELECT COUNT(*) as waringBasenumber FROM md_inventory mv where 1=1 AND mv.state=1 AND ( mv.base_number > mv.max_shu OR mv.base_number < mv.warning_shu ) ";
//		sql+=" AND mv.rbs_id= "+rbsId;
		if (rbaId != null) {
			sql += " and rba_id = " + rbaId;
		}
		if (rbsId != null) {
			sql += " and rbs_id = " + rbsId;
		}
		if (rbbId != null) {
			sql += " and rbb_id = " + rbbId;
		}
		if (purchaseType != null)
			sql += " and purchase_type = '" + purchaseType + "'";
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
	public Map<String, Object> safetyStockAlarm(Integer SuiId, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException {
		String sql = "SELECT COUNT( DISTINCT mor.moo_id ) AS LackNumber, group_CONCAT( DISTINCT mor.moo_id ) as mooIds " +
				"FROM md_out_order mor LEFT JOIN md_out_order_mx b ON mor.moo_id = b.moo_id LEFT JOIN md_inventory c ON c.ITEM_KEY_ID = b.ITEM_KEY_ID " +
				"WHERE mor.flow_state IN ( 2, 3 )  AND mor.state = '1'  AND ( mor.number1 - mor.number2 ) > 0  AND c.quantity <= 0";
//		sql+=" AND mor.rbs_id = "+rbsId;
		if (rbaId != null) {
			sql += " and mor.rba_id = " + rbaId;
		}
		if (rbsId != null) {
			sql += " and mor.rbs_id = " + rbsId;
		}
		if (rbbId != null) {
			sql += " and mor.rbb_id = " + rbbId;
		}
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	//导出缺少数量
	public  List<Map<String, Object>> exportCumulativeWarning() throws HSKDBException{
		String sql="SELECT t2.wms_mi_id, t2.mat_name AS matName,t2.NORM as norm, (IFNULL(t2.base_number,0) -IFNULL(t2.number1,0)) as lack ,t3.Label_info AS mmiLable,t3.Basic_unit, t4.mmf_code AS mmfCode FROM  md_out_order t1  ";
		sql+=" LEFT JOIN md_out_order_mx t2 ON t1.moo_id=t2.moo_id";
		sql+=" LEFT JOIN md_materiel_info t3 ON t2.wms_mi_id = t3.wms_mi_id ";
		sql+=" LEFT JOIN md_materiel_format t4 ON  t2.wms_mi_id=t4.wms_mi_id ";
		sql+=" WHERE t1.flow_state IN ( 2, 3 )  AND t1.state = '1' AND (IFNULL(t2.base_number,0) >IFNULL(t2.number1,0)) AND t2.NORM=t4.mmf_name " ;
		return this.getJdbcDao().query(sql);
	}
	//导出安全预警
	public  List<Map<String, Object>> SafetyEarlyWarning() throws HSKDBException{
		String sql="SELECT t2.wms_mi_id, t2.mat_name AS matName,t2.NORM as norm, (IFNULL(t2.base_number,0) -IFNULL(t2.number1,0)) as lack ,t3.Label_info AS mmiLable,t3.Basic_unit,t4.mmf_code AS mmfCode FROM  md_inventory mv  ";
		sql+=" LEFT JOIN md_out_order_mx t2 ON mv.ITEM_KEY_ID=t2.ITEM_KEY_ID";
		sql+=" LEFT JOIN md_materiel_info t3 ON t2.wms_mi_id = t3.wms_mi_id ";
		sql+=" LEFT JOIN md_materiel_format t4 ON  t2.wms_mi_id=t4.wms_mi_id ";
		sql+=" WHERE 1=1 AND ( mv.purchase_type LIKE '%2%' )  AND mv.state = '1'  AND (IFNULL( mv.warning_shu, 0 )-IFNULL( mv.base_number, 0 )>0) AND (IFNULL( t2.base_number, 0 ) > IFNULL( t2.number1, 0 ))   AND mv.rbs_id = 22 " ;
		return this.getJdbcDao().query(sql);
	}
	//7天实时申领动态
	public List<Map<String, Object>> sevenClaimant(Integer limit, Integer page,Integer suiId) throws HSKDBException{
		Date date =new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sevenDate=new Date(date.getTime()- 24*60*60*1000*7);
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
//		sql+=" AND mdoutorder0_.rbs_id = 22";
		sql+=" AND mdoutorder0_.sui_id = '"+SuiId+"'";
//		sql+=" AND mdoutorder0_.state = '1'";
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
//		sql+=" AND mdoutorder0_.rbs_id = 22";
		sql+=" AND mdoutorder0_.sui_id = '"+SuiId+"'";
//		sql+=" AND mdoutorder0_.state = '1'";
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
//		sql+=" AND mdoutorder0_.rbs_id = 22";
		sql+=" AND mdoutorder0_.sui_id = '"+SuiId+"'";
//		sql+=" AND mdoutorder0_.state = '1'";
		sql+=" AND mdoutorder0_.flow_state IN(4)";
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Double.parseDouble(list.get(0).get("CountMooCode3").toString());
		}
		return 0.00;
	}
	//7天实时申领动态 详情
	public List<Map<String, Object>> sevenOutMx(Integer mooId,Integer limit,Integer page) throws HSKDBException{
		Date date =new Date();
		String sql=" ";
		sql+="select t1.mat_name matName, t1.NORM as norm,t1.base_number as baseNumber,(t1.base_number-IF(number1 is null, 0, number1)) as lackNumber FROM md_out_order_mx t1 where 1=1 ";
		sql+=" AND t1.moo_id= "+mooId;
		sql+=" AND  t1.base_number > IF ( number1 IS NULL, 0, number1 )";
		if(limit != null && page != null) {
			sql += " limit " + (page - 1) * limit + "," + limit;
		}
		return this.getJdbcDao().query(sql);
	}
	@Override
	public Integer getMatCodeReadOnly1(Integer wmsMiId,Integer suiId) throws HSKDBException {
		String sql = "SELECT COUNT(t2.wew_mx_id) AS count1 FROM md_enter_warehouse t1  LEFT JOIN md_enter_warehouse_mx t2 on t1.wew_id=t2.wew_id LEFT JOIN md_materiel_info t3 on t2.wms_mi_id=t3.wms_mi_id WHERE 1=1 ";
		sql+=" AND t1.rbs_id = 22 AND t1.state = '1' AND t1.purchase_type = '2'  AND t1.bill_type = '1' ";
		sql+=" AND t1.sui_id ="+ suiId ;
		sql+=" AND t3.wms_mi_id ="+wmsMiId;
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Integer.parseInt(list.get(0).get("count1").toString());
		}
		return 0;
	}
	@Override
	public Integer getMatCodeReadOnly2(Integer wmsMiId,Integer suiId) throws HSKDBException {
		String sql = "SELECT COUNT(t2.wow_mx_id) as count2 FROM md_out_warehouse t1 LEFT JOIN md_out_warehouse_mx t2 ON t1.wow_id=t2.wow_id LEFT JOIN md_materiel_info t3 on t2.wms_mi_id=t3.wms_mi_id WHERE 1=1 ";
		sql+=" AND t1.rbs_id = 22 AND t1.purchase_type = '2' AND t1.COMPANY_type = '1' AND t1.state = '1' ";
		sql+=" t1.sui_id = "+suiId;
		sql+=" t1.wms_mi_id = "+wmsMiId;
		return 0;
	}
	@Override
	public List<Map<String, Object>> getPicking(String searchDdName,String date1,String date2,Integer date,String state,Integer suiId,Integer limit,Integer page) throws HSKDBException{
//		Date date =new Date();
		String sql=" SELECT DISTINCT t1.moo_id as mooId, t1.moo_code as mooCode,t1.flow_state as flowState,t1.order_time as orderTime FROM md_out_order t1 LEFT JOIN md_out_order_mx t2 ON t1.moo_id=t2.moo_id LEFT JOIN md_materiel_info t3 ON t3.wms_mi_id=t2.wms_mi_id";
		sql+=" WHERE 1=1 ";
		if (searchDdName!=null&&!"".equals(searchDdName)){
			sql+=" AND (t1.moo_code like '%"+searchDdName+"%'";
			sql+=" OR t2.mat_name LIKE '%"+searchDdName+"%'";
			sql+=" OR t2.NORM LIKE '%"+searchDdName+"%' OR t3.alias_name LIKE '%"+searchDdName+"%')";
		}if (date1!=null&&!date1.equals("")&&date2!=null&&!date2.equals("")){
			sql+="AND t1.order_time<='"+date2+" 23:59:59' AND t1.order_time>='"+date1+" 00:00:00'";
		}
		if (state!=null&&!state.equals("")){
			if (state=="1"||state.equals("1")){
				sql+=" AND t1.flow_state =t1.flow_state";
			}else {
				sql+=" AND t1.flow_state in("+state+")";
			}
		}
		sql+=" AND t1.sui_id="+suiId;
		sql+=" order by t1.order_time desc";
		if(limit != null && page != null) {
			sql += " limit " + (page - 1) * limit + "," + limit;
		}
		return this.getJdbcDao().query(sql);
	}

	public List<Map<String, Object>> getPickingMx(Integer mooId, String gjz, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException {
		String sql = " SELECT t1.moo_id as mooId, t1.wms_mi_id as wmsMiId,t2.brand as brand, t2.basic_coefficent as ratio,t1.mmf_id as mmfId,t1.mom_id as momId,t1.split_quantity as splitQuantity,t2.split_unit as splitUnit,t1.mat_name as matName,t1.NORM as norm,t1.Basic_unit as basicUnit,t2.mat_code as matCode,t2.product_name as productName,t1.base_number as baseNumber,t1.split_quantity AS splitbasenumber,t1.number1 as number1,t1.split_number1 as splitNumber1,t3.root_path AS rootPath,t5.order_time AS orderTime,t4.flow_state as flowState,t4.remarks AS remarks," +
				" t5.wow_remarks AS wowRemarks, mi.quantity AS quantity," +
				"mi.base_number AS split_quantity1 from md_out_order_mx t1 ";
		sql += " LEFT JOIN md_materiel_info t2 ON t1.wms_mi_id =t2.wms_mi_id ";
		sql += " LEFT JOIN sys_file_info t3 on t2.lessen_filecode=t3.file_code";
		sql += " LEFT JOIN md_out_order t4 ON t1.moo_id=t4.moo_id";
		sql += " LEFT JOIN md_out_warehouse t5 ON t4.moo_code=t5.RELATED_BILL1";
		sql += " left join md_inventory mi on mi.ITEM_KEY_ID = t1.ITEM_KEY_ID left join md_inventory_extend mie on mie.wi_id = mi.wi_id ";
		sql += " WHERE 1=1 ";
		if (mooId != null) {
			sql += " AND t1.moo_id=" + mooId;
		}
		if (gjz != null && !gjz.equals("")) {
			sql += " AND (lower(t1.mat_name) LIKE '%" + gjz + "%' OR lower(t1.mat_code) LIKE '%" + gjz + "%' OR lower(t1.NORM) LIKE '%" + gjz + "%' OR t2.py_name LIKE '%" + gjz + "%' OR t2.alias_name LIKE '%" + gjz + "%')";
		}
		if (rbaId != null)
			sql += " and mi.rba_id = " + rbaId;
		if (rbsId != null)
			sql += " and mi.rbs_id = " + rbsId;
		if (rbbId != null)
			sql += " and mi.rbb_id = " + rbbId;
//		if (purchaseType != null && !purchaseType.equals(""))
//			sql += " and mi.purchase_type = '" + purchaseType + "'";
//		sql += " and mie.mmf_id = t1.mmf_id";
		sql+=" GROUP BY t1.mom_id";
		return this.getJdbcDao().query(sql);
	}

	public List<Map<String, Object>> getMdoutLog(Integer mooId1,Integer limit,Integer page) throws HSKDBException{
		String sql=" SELECT moo_id as mooId,create_date as createDate,create_ren as  createRen,operation_state as operationState,create_log as createLog FROM md_out_order_log WHERE 1=1";
		if (mooId1!=null){
			sql+=" AND moo_id="+mooId1;
		}
		if(limit != null && page != null) {
			sql += " limit " + (page - 1) * limit + "," + limit;
		}
		return this.getJdbcDao().query(sql);
	}

	public List<Map<String, Object>> getPickingXq(Integer mooId) throws HSKDBException{
		String sql=" SELECT t1.moo_id as mooId, t1.mat_name as matName,t1.NORM as norm,t1.Basic_unit as basicUnit,t2.mat_code as matCode,t2.product_name as productName,t1.base_number as baseNumber,t1.number1 as number1,t3.root_path AS rootPath,t4.QUANTITY as quantity from md_out_order_mx t1 ";
		sql+=" LEFT JOIN md_materiel_info t2 ON t1.wms_mi_id =t2.wms_mi_id ";
		sql+=" LEFT JOIN sys_file_info t3 on t2.lessen_filecode=t3.file_code";
		sql+=" LEFT JOIN md_inventory_extend t4 ON t4.mmf_id= t1.mmf_id";
		sql+=" WHERE 1=1 ";
		if (mooId!=null){
			sql+="AND t1.moo_id="+mooId;
		}
		return this.getJdbcDao().query(sql);
	}
	public PagerModel getPagerModelBySearchName(String searchName,Integer wmsMiId,Integer mmtId,String brand, Integer mdpId, Integer mdpsId, Integer rbaId, Integer rbsId, Integer rbbId, Integer wzId, String purchaseType) throws HSKDBException {
		StringBuffer hql = new StringBuffer("SELECT a.wms_mi_id AS wmsMiId, a.wz_id AS wzId, a.purchase_type AS purchaseType, a.md_wms_mi_id AS mdWmsMiId," +
				"a.applicant_Name AS applicantName, a.Phone_number AS phoneNumber, a.Corporate_domicile AS corporateDomicile, a.scope_business AS scopeBusiness," +
				"a.product_name AS productName, a.product_factory AS productFactory, a.product_place AS productPlace, a.brand AS brand," +
				"a.mat_code AS matCode, a.mat_name AS matName, a.py_name AS pyName, a.money1 AS money1, a.money2 AS money2, a.money3 AS money3," +
				"a.money4 AS money4, a.money5 AS money5, a.number1 AS number1, a.number2 AS number2, a.number3 AS number3, a.number4 AS number4," +
				"a.number5 AS number5, a.Basic_unit AS basicUnit, a.Batch_rule AS batchRule, a.VALID_PERIOD AS valiedPeriod, a.ALERT_DAY AS alertDay," +
				"a.NORM AS norm, a.mat_type AS matType, a.mat_type1 AS matType1, a.mat_type2 AS matType2, a.mat_type3 AS matType3, a.Label_info AS labelInfo," +
				"a.lessen_filecode AS lessenFilecode, a.describe1 AS describe1, a.describe2 AS describe2, a.barCode AS barCode, a.barCode_filecode AS barCodeFilecode," +
				"a.state AS state, a.wz_state AS wzState, a.Basic_unit_accuracy AS basicUnitAccuracy, a.back_Printing AS backPrinting," +
				"a.Serial_number AS serialNumber, a.create_ren AS createRen, a.create_date AS createDate, a.edit_ren AS editRen," +
				"a.edit_date AS editDate, a.xid AS xid, a.cfca01_filecode AS cfca01Filecode, a.cfca02_filecode AS cfca02Filecode, a.cfca03_filecode AS cfca03Filecode, a.cfca04_filecode AS cfca04Filecode," +
				"a.cfca05_filecode AS cfca05Filecode, a.cfca06_filecode AS cfca06Filecode, a.cfca01_date AS cfca01Date," +
				"a.cfca02_date AS cfca02Date, a.alias_name AS aliasName, a.inventory_location AS inventoryLocation," +
				"a.mdp_id AS mdpId, a.mdps_id AS mdpsId, a.basic_coefficent AS basicCoefficent, a.split_unit AS splitUnit," +
				"a.valied_date AS valiedDate, a.standard AS standard, a.materiel_name AS materielName, a.ingredient AS ingredient," +
				"a.product_use AS productUse, a.batch_code AS batchCode, a.remark AS remark, a.weight AS weight, a.valid_period_unit AS validPeriodUnit, a.weight_unit AS weightUnit, " +
				"b.link_wms_mi_id as linkWmsMiId, b.alias_name as aliasName3,SUM( b.QUANTITY ) AS base_numbers, (SELECT SUM(e.base_number-(e.QUANTITY*e.ratio)) FROM md_inventory_extend_view e WHERE e.wms_mi_id =a.wms_mi_id) AS splitBaseNumber " +
//				"(SELECT root_path FROM sys_file_info WHERE file_code = a.lessen_filecode) as lessenFilePath " +
				"FROM" +
				" md_materiel_info a left join md_inventory_view b on a.wms_mi_id = b.wms_mi_id2  where 1=1");// left join md_materiel_info c on c.wms_mi_id = b.link_wms_mi_id where 1=1");
//		StringBuffer hql = new StringBuffer("from MdMaterielInfo where 1=1");
		if (searchName!=null&&!searchName.equals("")){
				hql.append(" AND (upper(a.mat_name) like '%" + searchName.trim().toUpperCase()
						+ "%' or a.norm like '%" + searchName.trim().toUpperCase()
						+ "%' or upper(a.py_name) like '%" + searchName.trim().toUpperCase()
						+ "%' or upper(a.mat_code) like '%" + searchName.trim()
						+ "%' or (a.py_name) like '%" + searchName.trim().toUpperCase()
						+ "%' or (a.mat_code) like '%" + searchName.trim()
						+ "%' or a.brand like '%" + searchName.trim()
						+ "%' or a.alias_name like '%" + searchName.trim()
						+ "%' or b.alias_name like '%" + searchName.trim() + "%')");
		}
		if (wmsMiId!=null){
			hql.append(" AND a.wms_mi_id="+wmsMiId);
		}
//		hql.append(" and a.wms_mi_id = b.wms_mi_id2");
		if (mmtId!=null){
			hql.append(" AND a.mat_type like'%"+mmtId+"%'");
		}
		if (brand!=null&&!brand.equals("")){
			hql.append(" and a.brand in (" + brand + ")");
//			if (brand.equals("0")){
//
//			}else if (brand.equals("1")){
//				hql.append(" AND a.brand ='麦迪康'");
//			}else if (brand.equals("2")){
//				hql.append(" AND a.brand ='登士柏'");
//			}else if (brand.equals("3")){
//				hql.append(" AND a.brand ='美国3M'");
//			}
		}
		if (mdpId != null)
			hql.append(" and a.mdp_id = " + mdpId);
		if (mdpsId != null)
			hql.append(" and a.mdps_id = " + mdpsId);
//		hql.append(" AND a.wms_mi_id = b.wms_mi_id2");
		hql.append(" AND ((1=1");
		if (rbaId != null)
			hql.append(" and b.rba_id = " + rbaId);
		if (rbsId != null)
			hql.append(" and b.rbs_id = " + rbsId);
		if (rbbId != null)
			hql.append(" and b.rbb_id = " + rbbId);

		hql.append(")");
		hql.append(" or (1=1 ");
		if (wzId != null) {
			hql.append(" and a.wz_id = " + wzId);
		}
		if (purchaseType != null && !purchaseType.equals(""))
			hql.append(" and a.purchase_type = '" + purchaseType + "'");
		hql.append("))");
		if (purchaseType != null && !purchaseType.equals(""))
			hql.append(" and b.purchase_type = '" + (Integer.parseInt(purchaseType) - 1) + "'");
		hql.append(" AND a.state =1  AND b.wms_mi_id2 IS NOT NULL");
//		hql.append(" AND ( a.mat_type IS NOT NULL OR a.mdp_id IS NOT NULL OR a.mdps_id IS NOT NULL)");
		hql.append(" group by a.wms_mi_id");
		String sql = hql.toString();
		List<Map<String, Object>> cList = this.getJdbcDao().query("select count(*) as totalCount from (" + sql + ") as t");
		sql += " limit " + ((SystemContext.getOffset() == 0 ? 1 : SystemContext.getOffset()) - 1) * SystemContext.getPageSize() + "," + SystemContext.getPageSize();
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		PagerModel pm = new PagerModel();
		pm.setRows(list);
		pm.setItems(list);

		Integer count = 0;
		if (cList == null || cList.isEmpty())
			count = 0;
		Map<String, Object> map = cList.get(0);
		if (map == null || map.isEmpty())
			count = 0;
		else
			count = Integer.parseInt(map.get("totalCount").toString());
		pm.setTotalCount(count);
		pm.setTotal(count);

//		PagerModel p = this.getHibernateDao().findByPage(hql.toString());
		return pm;
	}

	public PagerModel getMmfMx(Integer wmsMiId, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException {
		StringBuffer hql = new StringBuffer("from MdMaterielFormat where 1=1");
		if (wmsMiId!=null){
//			hql.append(" AND wmsMiId="+wmsMiId);
			hql.append(" and mmfId in (select mmfId from MdInventoryExtend where wmsMiId = " + wmsMiId);
			hql.append(" and wiId in (select wiId from MdInventory where 1=1");
			if (rbaId != null || rbsId != null || rbbId != null) {
				if (rbaId != null)
					hql.append(" and rbaId = " + rbaId);
				if (rbsId != null)
					hql.append(" and rbsId = " + rbsId);
				if (rbbId != null)
					hql.append(" and rbbId = " + rbbId);
			}
			hql.append(")");
			hql.append(")");
		}
		PagerModel p = this.getHibernateDao().findByPage(hql.toString());
		return p;
	}

	public PagerModel getCollectList(MdInventoryCollect mdInventoryCollect) throws HSKDBException {
		StringBuffer hql = new StringBuffer("from MdInventoryCollect where 1=1");
		if (mdInventoryCollect.getSuiId()!=null){
			hql.append(" AND suiId="+mdInventoryCollect.getSuiId());
			hql.append(" order by mic_id desc");
		}
		PagerModel p = this.getHibernateDao().findByPage(hql.toString());
		return p;
	}

	public Integer returnWiId(Integer wmsMiId)throws HSKDBException{
		String sql="SELECT t1.wi_id as wiId from md_inventory_view t1 WHERE 1=1";
		if (wmsMiId!=null&&!wmsMiId.equals("")){
			sql+=" AND t1.wms_mi_id2="+wmsMiId;
		}
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Integer.parseInt(list.get(0).get("wiId").toString());
		}
		return 0;
	}
	public Integer returnMmfId(Integer wmsMiId,Integer rbaId,Integer rbsId,Integer rbbId,Integer wzId,String purchaseType)throws HSKDBException{
		String sql="SELECT t1.mmf_id2 as mmfId FROM md_inventory_view t1 WHERE 1=1  ";
		if (wmsMiId!=null&&!wmsMiId.equals("")){
			sql+=" AND t1.wms_mi_id2="+wmsMiId;
		}
		if (rbaId != null)
			sql+=" AND t1.rba_id="+rbaId;
		if (rbsId != null)
			sql+=" AND t1.rbs_id="+rbsId;
		if (rbbId != null)
			sql+=" AND t1.rbb_id="+rbbId;
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Integer.parseInt(list.get(0).get("mmfId").toString());
		}
		return 0;
	}
	public Integer seachCollect(MdInventoryCollect mdInventoryCollect)throws HSKDBException{
		String sql="SELECT COUNT(mic_id) as count FROM md_inventory_collect where 1=1 ";
		if (mdInventoryCollect.getSuiId()!=null&&!mdInventoryCollect.getSuiId().equals("")){
			sql+=" AND sui_id="+mdInventoryCollect.getSuiId();
		}
		if (mdInventoryCollect.getWiId()!=null&&!mdInventoryCollect.getWiId().equals("")){
			sql+=" AND wi_id="+mdInventoryCollect.getWiId();
		}
		if (mdInventoryCollect.getWmsMiId()!=null&&!mdInventoryCollect.getWmsMiId().equals("")){
			sql+=" AND wms_mi_id="+mdInventoryCollect.getWmsMiId();
		}
		if (mdInventoryCollect.getMmfId()!=null&&!mdInventoryCollect.getMmfId().equals("")){
			sql+=" AND mmf_id="+mdInventoryCollect.getMmfId();
		}
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Integer.parseInt(list.get(0).get("count").toString());
		}
		return 0;
	}
	public Integer seachCollectId(MdInventoryCollect mdInventoryCollect)throws HSKDBException{
		String sql="SELECT mic_id as micId FROM md_inventory_collect where 1=1 ";
		if (mdInventoryCollect.getSuiId()!=null&&!mdInventoryCollect.getSuiId().equals("")){
			sql+=" AND sui_id="+mdInventoryCollect.getSuiId();
		}
		if (mdInventoryCollect.getWiId()!=null&&!mdInventoryCollect.getWiId().equals("")){
			sql+=" AND wi_id="+mdInventoryCollect.getWiId();
		}
		if (mdInventoryCollect.getWmsMiId()!=null&&!mdInventoryCollect.getWmsMiId().equals("")){
			sql+=" AND wms_mi_id="+mdInventoryCollect.getWmsMiId();
		}
		if (mdInventoryCollect.getMmfId()!=null&&!mdInventoryCollect.getMmfId().equals("")){
			sql+=" AND mmf_id="+mdInventoryCollect.getMmfId();
		}
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Integer.parseInt(list.get(0).get("micId").toString());
		}
		return 0;
	}
	//删除全部收藏
	@Override
	public void delectAllObjectBySuiId(Integer suiId) throws HSKDBException {
		String sql = "delete from md_inventory_collect where sui_id='"+suiId + "'";
		this.getJdbcDao().execute(sql);
	}
	//通过商品名查收藏
	@Override
	public PagerModel searchMdFavoritesBySearch(Integer suiId, String searchName) throws HSKDBException {
		String sql =" SELECT a.mic_id as micId,a.wi_id as wiId,a.sui_id as suiId,a.state as state,a.content as content,a.create_ren as createRen,a.create_date as createDate,a.edit_ren as editRen, ";
				sql+=" a.edit_date as editDate, a.wms_mi_id as wmsMiId,a.mmf_id as mmfId,b.mat_name as matName,c.root_path as lessenFilecode,d.mmf_name as mmfName,b.mat_code as matCode,b.Basic_unit as basicUnit,b.brand as brand,b.product_name as product_name ";
				sql+=" FROM md_inventory_collect a ";
				sql+=" LEFT JOIN  md_materiel_info b ON a.wms_mi_id = b.wms_mi_id ";
				sql+=" LEFT JOIN sys_file_info c ON b.lessen_filecode = c.file_code ";
				sql+=" LEFT JOIN md_materiel_format d ON a.mmf_id=d.mmf_id";
				sql+=" where a.wms_mi_id = b.wms_mi_id and b.state='1' and a.sui_id="+suiId;
					if(searchName !=null && !searchName.trim().equals("")) {
						sql += " and (b.applicant_name like '%" + searchName.trim() + "%'";
						sql += " or b.mat_name like '%" + searchName.trim() + "%'";
						sql += " or upper(b.mat_name) like '%"+searchName.trim().toUpperCase()
								+"%' or b.norm like '%"+searchName.trim().toUpperCase()
								+"%' or upper(b.py_name) like '%"+searchName.trim().toUpperCase()
								+"%' or upper(b.product_name) like '%"+searchName.trim()
								+"%' or b.brand like '%"+searchName.trim()
								+"%' or b.alias_name like '%"+searchName.trim()+"%')";
			//			sql += " or mat_name like '%" + searchName.trim() + "%'";
					}
		sql += " order by a.create_date desc";
		return this.getJdbcDao().findByPage(sql);
	}
	@Override
	public MdInventoryView findMdInventoryViewByMdInventoryView(
			MdInventoryView att_MdInventoryView) throws HSKDBException {
		MdInventoryView view = new MdInventoryView();
		String hql = "from MdInventoryView where state=1";
		if (att_MdInventoryView.getRbaId() != null)
			hql += " and rbaId=" + att_MdInventoryView.getRbaId();
		if (att_MdInventoryView.getRbsId() != null)
			hql += " and rbsId=" + att_MdInventoryView.getRbsId();
		if (att_MdInventoryView.getRbbId() != null)
			hql += " and rbbId=" + att_MdInventoryView.getRbbId();
		if (att_MdInventoryView.getMmfId() != null) {
			hql += " and mmfId2 = " + att_MdInventoryView.getMmfId();
		}
		if (att_MdInventoryView.getPurchaseType() != null && !att_MdInventoryView.getPurchaseType().equals("")) {
			hql += " and purchaseType = '" + att_MdInventoryView.getPurchaseType() + "'";
		}
		if (att_MdInventoryView.getMmfId2()!=null){
			hql += " and mmfId2=" + att_MdInventoryView.getMmfId2();
		}
		if (att_MdInventoryView.getItemKeyId() != null)
			hql += " and itemKeyId=" + att_MdInventoryView.getItemKeyId();

		List<MdInventoryView> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0)
			view = list.get(0);
		return view;
	}

	public Integer countCollect1(Integer suiId)throws HSKDBException{
		/*String sql="SELECT COUNT(mc_id) as mcId FROM md_carts  WHERE sui_id="+suiId +" AND mmf_id !=0";
		上面这句为查询物料清单数量，因为有测试数据，为了避免删除这数据出现问题，查询时加了以下限制，避开测试数据
		* */
		String sql="SELECT COUNT(mc_id) as mcId FROM md_carts t1 LEFT JOIN md_materiel_info t2 ON t1.wms_mi_id=t2.wms_mi_id  WHERE sui_id="+suiId +" AND mmf_id !=0 AND t2.purchase_type!=4";
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Integer.parseInt(list.get(0).get("mcId").toString());
		}
		return  0;
	}

//	SELECT  t1.QUANTITY AS quantity,t1.Basic_unit Basic_unit,t1.split_quantity AS split_quantity,t1.unit AS unit,t2.mmf_code AS mmt_code FROM md_inventory_extend  t1  LEFT JOIN md_materiel_format t2 ON t1.mmf_id=t2.mmf_id  WHERE 1=1

//	public List countCollect1(Integer suiId)throws HSKDBException{
//		String sql="SELECT COUNT(mc_id) as mcId FROM md_carts WHERE sui_id="+suiId +" AND mmf_id !=0";
//		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
//		if(list != null && list.size() > 0){
//			return Integer.parseInt(list.get(0).get("mcId").toString());
//		}
//		return  0;
//	}
public List<Map<String, Object>> selectMmfId(Integer mmfId) throws HSKDBException{
	String sql=" SELECT t1.mmf_id, SUM(t1.QUANTITY) AS quantity,t1.Basic_unit Basic_unit,SUM(t1.split_quantity) AS split_quantity,t1.unit AS unit,t2.mmf_code AS mmt_code FROM md_inventory_extend  t1  LEFT JOIN md_materiel_format t2 ON t1.mmf_id=t2.mmf_id ";
	sql+=" WHERE 1=1 ";
	if (mmfId!=null){
		sql+="AND t1.mmf_id="+mmfId;
	}
	return this.getJdbcDao().query(sql);
}

	public Integer getPickingStateCount(String searchDdName,String date1,String date2,Integer state,Integer suiId )throws HSKDBException{
//		String sql="SELECT COUNT(mc_id) as mcId FROM md_carts WHERE sui_id="+suiId;
		String sql=" SELECT COUNT(DISTINCT t1.moo_id) AS count FROM md_out_order t1 LEFT JOIN md_out_order_mx t2 ON t1.moo_id = t2.moo_id WHERE 1 = 1 ";
		if (state.equals(1)){
			sql+=" AND t1.flow_state=t1.flow_state";
		}else if (state.equals(2)){//申请中
			sql+=" AND t1.flow_state="+state;
		}
		else if (state.equals(3)){//部分发货
			sql+=" AND t1.flow_state="+state;
		}
		else if (state.equals(4)){//已完成
			sql+=" AND t1.flow_state="+state;
		}
		else if (state.equals(5)){//撤销
			sql+=" AND t1.flow_state="+state;
		}
		if (searchDdName!=null&&!"".equals(searchDdName)){
			sql+=" AND (t1.moo_code like '%"+searchDdName+"%'";
			sql+=" OR t2.mat_name LIKE '%"+searchDdName+"%'";
			sql+=" OR t2.NORM LIKE '%"+searchDdName+"%')";
		}if (date1!=null&&!date1.equals("")&&date2!=null&&!date2.equals("")){
			sql+=" AND t1.order_time<='"+date2+" 23:59:59' AND t1.order_time>='"+date1+" 23:59:59'";
		}
		sql+="  AND t1.sui_id="+suiId;
		List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
		if(list != null && list.size() > 0){
			return Integer.parseInt(list.get(0).get("count").toString());
		}
		return  0;
	}
	public List<Map<String, Object>> getPickingOrderInfo(Integer mooId) throws HSKDBException{
			String sql=" SELECT moo_code,order_time,flow_state,remarks FROM md_out_order ";
			sql+=" WHERE 1=1 ";
			if (mooId!=null){
				sql+="AND moo_id="+mooId;
			}
			return this.getJdbcDao().query(sql);
		}
		//通过物料名找Id
		public List<Map<String, Integer>> getmmfWmsId(String  matName,String mmfName) throws HSKDBException{
			String sql=" SELECT t1.wms_mi_id as wmsMiId,t2.mmf_id as mmfId FROM md_materiel_info t1 ";
			sql+=" LEFT JOIN md_materiel_format t2 ON t1.wms_mi_id=t2.wms_mi_id ";
			if (matName!=null&&!matName.equals("")){
				sql+=" WHERE t1.mat_name = '"+matName+"'";
			}
			if (mmfName!=null&&!mmfName.equals("")){
				sql+=" AND t2.mmf_name ='"+mmfName+"'";
			}
			sql+=" LIMIT 1";
			return this.getJdbcDao().query(sql);
		}
//	@Override
//	public List<Map<String, Object>> getPicking(String searchDdName,String date1,String date2,Integer date,Integer state,Integer limit,Integer page) throws HSKDBException{
////		Date date =new Date();
//		String sql=" SELECT DISTINCT t1.moo_id as mooId, t1.moo_code as mooCode,t1.flow_state as flowState,t1.order_time as orderTime FROM md_out_order t1 LEFT JOIN md_out_order_mx t2 ON t1.moo_id=t2.moo_id ";
//		sql+=" WHERE 1=1 ";
//		if (searchDdName!=null&&!"".equals(searchDdName)){
//			sql+=" AND (t1.moo_code like '%"+searchDdName+"%'";
//			sql+=" OR t2.mat_name LIKE '%"+searchDdName+"%'";
//			sql+=" OR t2.NORM LIKE '%"+searchDdName+"%')";
//		}if (date1!=null&&!date1.equals("")&&date2!=null&&!date2.equals("")){
//			sql+="AND t1.order_time<='"+date2+" 23:59:59' AND t1.order_time>='"+date1+" 23:59:59'";
//		}
//		if(limit != null && page != null) {
//			sql += " limit " + (page - 1) * limit + "," + limit;
//		}
//		return this.getJdbcDao().query(sql);
//	}

	//根据ID查询收藏
	public MdInventoryCollect findMdInventoryCollectById(Integer micId) throws HSKDBException{
		MdInventoryCollect  att_MdInventoryCollect=new MdInventoryCollect();
		if(micId!=null){
			att_MdInventoryCollect.setMicId(micId);
			Object obj=	this.getOne(att_MdInventoryCollect);
			if(obj!=null){
				att_MdInventoryCollect=(MdInventoryCollect) obj;
			}
		}
		return  att_MdInventoryCollect;
	}

	@Override
	public MdOutOrder findMdOutOrderByMdOutOrder(MdOutOrder mdOutOrder) throws HSKDBException {
		String sql = getHql(mdOutOrder, null);
		List<MdOutOrder> list = this.getHibernateTemplate().find(sql);
		if (list == null && list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public Double getCountQuantity(Integer wmsMiId, Integer mmfId, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException {
		String sql = "SELECT ROUND(SUM(b.QUANTITY),0) as quantity  FROM md_inventory b WHERE 1=1";
		if (rbaId != null)
			sql += " and rba_id = " + rbaId;
		if (rbsId != null)
			sql += " and rbs_id = " + rbsId;
		if (rbbId != null)
			sql += " and rbb_id = " + rbbId;
//		if (rbaId != null || rbsId != null || rbbId != null) {
		sql += " and wi_id in (select wi_id from md_inventory_extend where 1=1";
		if (wmsMiId != null)
			sql += " and wms_mi_id = " + wmsMiId;
		if (mmfId != null)
			sql += " and mmf_id = " + mmfId;
		sql += ")";
//		}
		if (purchaseType != null && !purchaseType.equals(""))
			sql += " and purchase_type = '" + (Integer.parseInt(purchaseType) - 1) + "'";
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list == null && list.isEmpty())
			return 0d;
		Map<String, Object> map = list.get(0);
		if (map == null && map.isEmpty())
			return 0d;
		if (map.get("quantity") == null)
			return 0d;

		return Double.parseDouble(map.get("quantity").toString());
	}

	@Override
	public Double getCountBaseNumber(Integer wmsMiId, Integer mmfId, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException {
		String sql = "SELECT ROUND(SUM(b.base_number),0) as baseNumber  FROM md_inventory b WHERE 1=1";
		if (rbaId != null)
			sql += " and rba_id = " + rbaId;
		if (rbsId != null)
			sql += " and rbs_id = " + rbsId;
		if (rbbId != null)
			sql += " and rbb_id = " + rbbId;
//		if (rbaId != null || rbsId != null || rbbId != null) {
		sql += " and wi_id in (select wi_id from md_inventory_extend where 1=1";
		if (wmsMiId != null)
			sql += " and wms_mi_id = " + wmsMiId;
		if (mmfId != null)
			sql += " and mmf_id = " + mmfId;
		sql += ")";
//		}
		if (purchaseType != null && !purchaseType.equals(""))
			sql += " and purchase_type = '" + (Integer.parseInt(purchaseType) - 1) + "'";
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list == null && list.isEmpty())
			return 0d;
		Map<String, Object> map = list.get(0);
		if (map == null && map.isEmpty())
			return 0d;
		if (map.get("baseNumber") == null)
			return 0d;

		return Double.parseDouble(map.get("baseNumber").toString());
	}

	@Override
	public List<Map<String, Object>> getCountBaseNumberAndQuantity(String wmsMiIds, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException {
		String sql = "SELECT ROUND( SUM( a.QUANTITY ), 0 ) AS quantitys,ROUND( SUM( IFNULL(a.split_quantity,0)), 0 ) AS splitQuantitys, ROUND(SUM(b.base_number),0) as baseNumber, a.wms_mi_id as wmsMiId  FROM md_inventory b " +
				"left join md_inventory_extend a on a.wi_id = b.wi_id WHERE 1=1";
		if (rbaId != null)
			sql += " and b.rba_id = " + rbaId;
		if (rbsId != null)
			sql += " and b.rbs_id = " + rbsId;
		if (rbbId != null)
			sql += " and b.rbb_id = " + rbbId;
		if (wmsMiIds != null && !wmsMiIds.equals(""))
			sql += " and a.wms_mi_id in (" + wmsMiIds + ")";
		if (purchaseType != null && !purchaseType.equals(""))
			sql += " and b.purchase_type = '" + (Integer.parseInt(purchaseType) - 1) + "'";
		sql += " group by a.wms_mi_id";
		return this.getJdbcDao().query(sql);
	}
}