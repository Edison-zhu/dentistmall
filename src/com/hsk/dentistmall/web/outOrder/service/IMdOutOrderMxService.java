package com.hsk.dentistmall.web.outOrder.service;

import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.persistence.MdInventoryView;
import com.hsk.dentistmall.api.persistence.MdInventoryViewEx;
import com.hsk.dentistmall.api.persistence.MdOutOrderMx;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

/**
 * outOrder业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-11-13 11:22:28
 */
public interface IMdOutOrderMxService{


		/**
	 * 查找MdOutOrderMx对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  momId  Integer类型(md_out_order_mx表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer momId) throws HSKException;

	
	/**
	 * 查找MdOutOrderMx对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  momId  Integer类型(md_out_order_mx表主键)
	 * @return MdOutOrderMx md_out_order_mx表记录
	 * @throws HSKException
	 */	
	public MdOutOrderMx findObject(Integer momId) throws HSKException;
	
	/**
	 * 根据md_out_order_mx表主键删除MdOutOrderMx对象记录
     * @param  momId  Integer类型(md_out_order_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer momId) throws HSKException;
	
	
	
	/**
	 * 根据md_out_order_mx表主键删除多条MdOutOrderMx对象记录
     * @param  momIds  Integer类型(md_out_order_mx表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String momIds) throws HSKException;
	
	 /**
	 * 新增或修改md_out_order_mx表记录 ,如果md_out_order_mx表主键MdOutOrderMx.momId为空就是添加，如果非空就是修改
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject( MdOutOrderMx att_MdOutOrderMx) throws HSKException;
	
	/**
	 *根据MdOutOrderMx对象作为对(md_out_order_mx表进行查询，获取分页对象
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdOutOrderMx att_MdOutOrderMx) throws HSKException;
	/**
	 * 根据申领单ID查询申领单明细列表
	 * @param mooId
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdOutOrderMxListByMooId(Integer mooId)  throws HSKException;
	
	/**
	 * 根据商品名称,规格 模糊查询
	 *yanglei 
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdOutOrderMxListByName(String str)  throws HSKException;
	public PagerModel getOutOrderMxListByMooId(Integer mooId, String matName, String mmfName)throws HSKException;
	/**
	 * 获取出库明细库存信息
	 * @param mooId
	 * @return
	 * @throws HSKException
	 */
	public List<MdInventoryViewEx> getOutOrderMxInventory(Integer mooId)throws HSKException;
	
	
}