package com.hsk.dentistmall.web.outOrder.service;

import com.hsk.dentistmall.api.persistence.MdInventoryCollect;
import com.hsk.dentistmall.api.persistence.MdOutOrder;
import com.hsk.dentistmall.api.persistence.MdOutOrderMx;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

/**
 * outOrder业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-11-13 11:22:28
 */
public interface IMdOutOrderService{


		/**
	 * 查找MdOutOrder对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mooId  Integer类型(md_out_order表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer mooId) throws HSKException;

	SysRetrunMessage findFormObjectByWowId(Integer wowId) throws HSKException;
	
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
	public SysRetrunMessage   saveOrUpdateObject( MdOutOrder att_MdOutOrder) throws HSKException;
	
	/**
	 *根据MdOutOrder对象作为对(md_out_order表进行查询，获取分页对象
     * @param  att_MdOutOrder  MdOutOrder类型(md_out_order表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdOutOrder att_MdOutOrder) throws HSKException;
	PagerModel getPagerMdOutOrderDistinct(MdOutOrder att_MdOutOrder, String orderTimeStart, String orderTimeEnd, String outTimeStart, String outTimeEnd, String distinctName)throws HSKException;
	/**
	 * 
	 * @param att_MdOutOrder
	 * @param wiIds
	 * @param shus
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveMdOutOrder(MdOutOrder att_MdOutOrder,String wiIds,String shus)  throws HSKException;

	/**
	 * 20191125 yangfeng 修改明细表
	 * @param att_MdOutOrder
	 * @param shus
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage updateMdOutOrderMx(MdOutOrder att_MdOutOrder,String shus, String momIds) throws HSKException;
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
	public PagerModel getPagerMdOutOrder (MdOutOrder att_MdOutOrder, String orderTimeStart, String orderTimeEnd, String outTimeStart, String outTimeEnd) throws HSKException;

	PagerModel getPagerMdOutInfo(MdOutOrder att_MdOutOrder, String orderTimeStart, String orderTimeEnd, String outTimeStart, String outTimeEnd, Integer warning) throws HSKException;

	PagerModel getPagerMdOutOrderFlowState(MdOutOrder mdOutOrder) throws HSKException;
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
	
	
	public SysRetrunMessage exportList(MdOutOrder attMdOutOrder,MdOutOrderMx att_MdOutOrderMx,String momIds) throws HSKException;
	
	//增加申领出库页面的批量导出功能
	public SysRetrunMessage exportListPi(MdOutOrder attMdOutOrder,MdOutOrderMx att_MdOutOrderMx,String momIds) throws HSKException;
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
	public PagerModel sevenClaimant(Integer limit,Integer page) throws HSKException;
	//展示申领中，部分出库，已完成数据
	public SysRetrunMessage ClaimPartialOut() throws HSKException;
	//弹出页面数据
	public PagerModel sevenOutMx(Integer mooId,Integer limit,Integer page) throws HSKException;
	//增加已读方法
	public SysRetrunMessage   saveRead(String checkStr) throws HSKException;
	/**
	 *增加物料信息维护中型号编码是否可操作
	 */
	public SysRetrunMessage getMatCodeReadOnly(Integer wmsMiId) throws HSKException;

	//领料申领管理列表
	public PagerModel getPicking(String searchDdName,String searchDdStartTime,String selectState1,Integer date,Integer state,Integer limit,Integer page) throws HSKException;
	//领料申领管理列表明细
	public PagerModel getPickingMx(Integer mooId,Integer limit,Integer page) throws HSKException;
	//新增领料
	public PagerModel getPagerModelBySearchName(String searchName,Integer wmsMiId,Integer mmtId,String brand, Integer mdpId, Integer mdpsId) throws HSKDBException;
	//保存收藏
	public SysRetrunMessage saveCollect(Integer wmsMiId,String mmfId) throws HSKException;
	//显示规格
	public PagerModel getmmfMx(Integer wmsMiId) throws HSKDBException;
	public SysRetrunMessage seachCollect(Integer wmsMiId,String mmfId) throws HSKException;

	//查询收藏列表
	public PagerModel getCollectList(MdInventoryCollect mdInventoryCollect) throws HSKException;
	//移除单个收藏
	public SysRetrunMessage deleteMicId(Integer micId) throws HSKException;
	//移除本账号所有收藏
	public SysRetrunMessage delectAllObjectBySuiId() throws HSKException;
	//从收藏一键加入物料清单
	public SysRetrunMessage oneClickJoin(String wmsIds,String mmfIds1) throws HSKException;
	//通过商品名称关键字查收藏
	public PagerModel searchMdFavoritesBySearch(String searchName) throws HSKException;
	//添加购物车
	public SysRetrunMessage addCarts(Integer mcId,Integer wmsMiId ,String mmfId, String shus, String splitShus, Double price) throws HSKException;
	//保存申领
	public SysRetrunMessage savePicking(String numberSums,String  mmfIdSums ,String wmsIdSums,String remarks,String splitQuantitys1) throws HSKException;
	//查询物料清单个数
	public SysRetrunMessage countCollect1() throws HSKException;
	//查询添加物料明细
	public PagerModel getPickingXq(Integer mooId,String gjz,Integer limit,Integer page) throws HSKException;
	//查询申领管理各状态个数
	public SysRetrunMessage getPickingStateCount(String searchDdName,String searchDdStartTime) throws HSKException;
	//查询领料申请明细申请订单
	public SysRetrunMessage getPickingOrderInfo(Integer mooId) throws HSKException;
	//再次申请物料清单
	public SysRetrunMessage saveReapply(Integer mooId1) throws HSKException;
	//修改申领数量
	public SysRetrunMessage saveBaseNumber(String momIds,String baseNumbers,String remarks,String splitQuantitys) throws HSKException;
	//收藏夹一件加入购物车
	public SysRetrunMessage addCarts2(Integer mcId,String wmsMiId ,String mmfId, Integer shu, Double price) throws HSKException;
	//查询申领单日志
	public PagerModel getMdoutLog(Integer mooId,Integer limit,Integer page) throws HSKException;
	//删除申领详情中的规格商品
	public SysRetrunMessage saveDeletemomId(String momIds) throws HSKException;
	//根据规格ID查询库存
	public PagerModel selectMmfId(Integer mmfId) throws HSKException;

	//当有多个规格时  进行收藏判断
	public SysRetrunMessage getCollectMx(Integer wmsMiId,String  mmfIds) throws HSKException;

	public SysRetrunMessage saveInventoryMaterielAliasName(Integer wmsMiId, String aliasName) throws HSKException;
	SysRetrunMessage saveDeleteInventoryMaterielAliasName(Integer wmsMiId, String aliasName) throws HSKException;
	}