package com.hsk.miniapi.dentistmall.web.business.apiservice;

import com.hsk.dentistmall.api.persistence.*;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.exception.HSKException;

/**
 * business业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:17
 */
public interface IMdEnclosureApiService {


		/**
	 * 查找MdEnclosure对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  rbenId  Integer类型(md_enclosure表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer rbenId) throws HSKException;

	
	/**
	 * 查找MdEnclosure对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  rbenId  Integer类型(md_enclosure表主键)
	 * @return MdEnclosure md_enclosure表记录
	 * @throws HSKException
	 */	
	public MdEnclosure findObject(Integer rbenId) throws HSKException;
	
	/**
	 * 根据md_enclosure表主键删除MdEnclosure对象记录
     * @param  rbenId  Integer类型(md_enclosure表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer rbenId) throws HSKException;
	
	
	
	/**
	 * 根据md_enclosure表主键删除多条MdEnclosure对象记录
     * @param  rbenIds  Integer类型(md_enclosure表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String rbenIds) throws HSKException;
	
	 /**
	 * 新增或修改md_enclosure表记录 ,如果md_enclosure表主键MdEnclosure.rbenId为空就是添加，如果非空就是修改
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdEnclosure att_MdEnclosure) throws HSKException;

	/**
	 *根据MdEnclosure对象作为对(md_enclosure表进行查询，获取分页对象
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdEnclosure att_MdEnclosure) throws HSKException;
	
}