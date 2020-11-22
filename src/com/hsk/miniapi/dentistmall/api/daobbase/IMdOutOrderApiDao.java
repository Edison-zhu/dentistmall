package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_out_order表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-11-13 11:22:28
 */
public interface IMdOutOrderApiDao {
	
	/**
	 * 根据md_out_order表主键查找MdOutOrder对象记录
	 * @param  MooId  Integer类型(md_out_order表主键)
	 * @return MdOutOrder md_out_order表记录
	 * @throws HSKDBException
	 */	
	public MdOutOrder findMdOutOrderById(Integer MooId) throws HSKDBException;
	
	/**
	 * 根据md_out_order表主键删除MdOutOrder对象记录
     * @param  MooId  Integer类型(md_out_order表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdOutOrderById(Integer MooId) throws HSKDBException;
	 
	/**
 	 * 根据md_out_order表主键修改MdOutOrder对象记录
	 * @param  MooId  Integer类型(md_out_order表主键)
	 * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
	 * @return MdOutOrder  修改后的MdOutOrder对象记录
	 * @throws HSKDBException
	 */
	public  MdOutOrder updateMdOutOrderById(Integer MooId, MdOutOrder att_MdOutOrder) throws HSKDBException;

	/**
	 * 新增md_out_order表 记录
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return md_out_order表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdOutOrder(MdOutOrder att_MdOutOrder) throws HSKDBException;

	 /**
	 * 新增或修改md_out_order表记录 ,如果md_out_order表主键MdOutOrder.MooId为空就是添加，如果非空就是修改
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return MdOutOrder  修改后的MdOutOrder对象记录
	 * @throws HSKDBException
	 */
	public  MdOutOrder saveOrUpdateMdOutOrder(MdOutOrder att_MdOutOrder) throws HSKDBException;

	/**
	 *根据MdOutOrder对象作为对(md_out_order表进行查询，获取分页对象
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdOutOrder(MdOutOrder att_MdOutOrder) throws HSKDBException;

    /**
	 *根据MdOutOrder对象作为对(md_out_order表进行查询，获取分页对象
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return List<MdOutOrder>  列表对象
	 * @throws HSKDBException
	 */
	public List<MdOutOrder> getListByMdOutOrder(MdOutOrder att_MdOutOrder) throws HSKDBException;

    PagerModel getPagerModelByMdOutOrderDistinct(MdOutOrder att_mdOutOrder, String distinctName) throws HSKDBException;

	/**
	 * 统计报表中的数据
	 */
	public List<Map<String,Object>> departmentStatisticalReport(Integer value) throws HSKDBException;

	//待申领出库数据
	public Double outStock(Integer SuiId)throws HSKDBException;
	//订单待入库
	public Double warehouse(Integer SuiId, Integer rbsId)throws HSKDBException;
	//退货出库
	public Double returnStock(Integer SuiId, Integer rbsId)throws HSKDBException;
	//申领库存告警
	public Double stockAlarm(Integer SuiId, Integer rbsId)throws HSKDBException;
	//安全库存告警
	public Double safetyStockAlarm(Integer SuiId, Integer rbsId)throws HSKDBException;
	//申领出库缺少数量
	public List<Map<String, Object>> exportCumulativeWarning() throws HSKDBException;
	//7天动态数据
	public List<Map<String, Object>> sevenClaimant(Integer limit, Integer page, Integer suiId) throws HSKDBException;
	//申领人申领中订单
	public Double Claim(Integer SuiId)throws HSKDBException;
	//申领人部分出库订单
	public Double Partial(Integer SuiId)throws HSKDBException;
	//申领人已完成出库订单
	public Double CompleteOut(Integer SuiId)throws HSKDBException;
	//7天数据详情
	public List<Map<String, Object>> sevenOutMx(Integer mooId) throws HSKDBException;

}