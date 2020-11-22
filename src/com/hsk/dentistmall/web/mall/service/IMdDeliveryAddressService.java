package com.hsk.dentistmall.web.mall.service;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.service.IDSTService;
import com.hsk.exception.HSKException;

/**
 * mall业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:22:54
 */
public interface IMdDeliveryAddressService{


		/**
	 * 查找MdDeliveryAddress对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mdaId  Integer类型(md_delivery_address表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer mdaId) throws HSKException;

	
	/**
	 * 查找MdDeliveryAddress对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mdaId  Integer类型(md_delivery_address表主键)
	 * @return MdDeliveryAddress md_delivery_address表记录
	 * @throws HSKException
	 */	
	public MdDeliveryAddress findObject(Integer mdaId) throws HSKException;
	
	/**
	 * 根据md_delivery_address表主键删除MdDeliveryAddress对象记录
     * @param  mdaId  Integer类型(md_delivery_address表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer mdaId) throws HSKException;
	
	
	
	/**
	 * 根据md_delivery_address表主键删除多条MdDeliveryAddress对象记录
     * @param  mdaIds  Integer类型(md_delivery_address表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String mdaIds) throws HSKException;
	
	 /**
	 * 新增或修改md_delivery_address表记录 ,如果md_delivery_address表主键MdDeliveryAddress.mdaId为空就是添加，如果非空就是修改
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject( MdDeliveryAddress att_MdDeliveryAddress) throws HSKException;
	
	/**
	 *根据MdDeliveryAddress对象作为对(md_delivery_address表进行查询，获取分页对象
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdDeliveryAddress att_MdDeliveryAddress) throws HSKException;
	/**
	 * 根据当前登陆用户查询收货地址列表
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getMdDeliveryAddressByUser() throws HSKException;
	
}