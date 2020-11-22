package com.hsk.miniapi.dentistmall.web.transaction.service;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.service.IDSTService;
import com.hsk.exception.HSKException;

/**
 * transaction业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:17:00
 */
public interface IMdSupplierApiService {


		/**
	 * 查找MdSupplier对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wzId  Integer类型(md_supplier表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer wzId) throws HSKException;

	
	/**
	 * 查找MdSupplier对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  wzId  Integer类型(md_supplier表主键)
	 * @return MdSupplier md_supplier表记录
	 * @throws HSKException
	 */	
	public MdSupplier findObject(Integer wzId) throws HSKException;
	
	/**
	 * 根据md_supplier表主键删除MdSupplier对象记录
     * @param  wzId  Integer类型(md_supplier表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wzId) throws HSKException;
	
	
	
	/**
	 * 根据md_supplier表主键删除多条MdSupplier对象记录
     * @param  wzIds  Integer类型(md_supplier表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wzIds) throws HSKException;
	
	 /**
	 * 新增或修改md_supplier表记录 ,如果md_supplier表主键MdSupplier.wzId为空就是添加，如果非空就是修改
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdSupplier att_MdSupplier) throws HSKException;

	/**
	 *根据MdSupplier对象作为对(md_supplier表进行查询，获取分页对象
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdSupplier att_MdSupplier) throws HSKException;
	
}