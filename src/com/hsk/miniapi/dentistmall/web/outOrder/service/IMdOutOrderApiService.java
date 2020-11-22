package com.hsk.miniapi.dentistmall.web.outOrder.service;

import com.hsk.dentistmall.api.persistence.MdOutOrder;
import com.hsk.dentistmall.api.persistence.MdOutOrderMx;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

/**
 * outOrder业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-11-13 11:22:28
 */
public interface IMdOutOrderApiService {


		/**
	 * 查找MdOutOrder对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mooId  Integer类型(md_out_order表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer mooId) throws HSKException;
	
	/**
	 * 查找MdOutOrder对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mooId  Integer类型(md_out_order表主键)
	 * @return MdOutOrder md_out_order表记录
	 * @throws HSKException
	 */	
	public MdOutOrder findObject(Integer mooId) throws HSKException;
	
	/**
	 * 根据md_out_order表主键删除MdOutOrder对象记录
     * @param  mooId  Integer类型(md_out_order表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer mooId) throws HSKException;
	
	
	
	/**
	 * 根据md_out_order表主键删除多条MdOutOrder对象记录
     * @param  mooIds  Integer类型(md_out_order表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String mooIds) throws HSKException;
	
	 /**
	 * 新增或修改md_out_order表记录 ,如果md_out_order表主键MdOutOrder.mooId为空就是添加，如果非空就是修改
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdOutOrder att_MdOutOrder) throws HSKException;

	/**
	 *根据MdOutOrder对象作为对(md_out_order表进行查询，获取分页对象
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdOutOrder att_MdOutOrder) throws HSKException;
	PagerModel getPagerMdOutOrderDistinct(MdOutOrder att_MdOutOrder, String orderTimeStart, String orderTimeEnd, String outTimeStart, String outTimeEnd, String distinctName)throws HSKException;
	/**
	 *
	 * @param att_MdOutOrder
	 * @param wiIds
	 * @param shus
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveMdOutOrder(MdOutOrder att_MdOutOrder, String wiIds, String shus)  throws HSKException;

	/**
	 * 20191125 yangfeng 修改明细表
	 * @param att_MdOutOrder
	 * @param shus
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage updateMdOutOrderMx(MdOutOrder att_MdOutOrder, String shus, String momIds) throws HSKException;
	/**
	 * 关闭申领单
	 * @param sggId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage updateObjectFlowState(Integer mooId) throws HSKException;
	/**
	 * 查询出具需要的申领单列表
	 * @param att_MdOrderInfo
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getPagerMdOutOrder(MdOutOrder att_MdOutOrder, String orderTimeStart, String orderTimeEnd, String outTimeStart, String outTimeEnd) throws HSKException;
	/**
	 * 导出申领单详情
	 * @param wowId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage exportInfo(Integer mooId) throws HSKException;
	/**
	 * 新增导出申领单详情 用于立即出库的导出之中
	 * @param wowId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage exportInfoApply(Integer mooId) throws HSKException;


	public SysRetrunMessage exportList(MdOutOrder attMdOutOrder, MdOutOrderMx att_MdOutOrderMx, String momIds) throws HSKException;

	//增加申领出库页面的批量导出功能
	public SysRetrunMessage exportListPi(MdOutOrder attMdOutOrder, MdOutOrderMx att_MdOutOrderMx, String momIds) throws HSKException;
	/**
	 * 复制申领单信息
	 * @param mooId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage copyFormObject(Integer mooId) throws HSKException;
	/**
	 * 添加申领出库信息统计报表
	 */
	public PagerModel departmentStatisticalReport(Integer value) throws HSKException;
	/**
	 *
	 * 添加5大模块的数据
	 */
	public SysRetrunMessage countDepartmentReport() throws HSKException ;
	//添加导出申领出库信息
	//exportCumulativeWarning
	public SysRetrunMessage exportCumulativeWarning() throws HSKException;
	public PagerModel sevenClaimant() throws HSKException;
	//展示申领中，部分出库，已完成数据
	public SysRetrunMessage ClaimPartialOut() throws HSKException;
	//弹出页面数据
	public PagerModel sevenOutMx(Integer mooId) throws HSKException;
	//增加已读方法
	public SysRetrunMessage   saveRead(String checkStr) throws HSKException;
}