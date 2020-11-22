package com.hsk.miniapi.dentistmall.web.evaluate.service;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.service.IDSTService;
import com.hsk.exception.HSKException;

/**
 * evaluate业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2018-03-13 09:18:19
 */
public interface IMdEvaluateApiService {


		/**
	 * 查找MdEvaluate对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mevaId  Integer类型(md_evaluate表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer mevaId) throws HSKException;
	
	
	public SysRetrunMessage getFormatList(Integer wmsMiId) throws HSKException;
	
	/**
	 * 查找MdEvaluate对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mevaId  Integer类型(md_evaluate表主键)
	 * @return MdEvaluate md_evaluate表记录
	 * @throws HSKException
	 */	
	public MdEvaluate findObject(Integer mevaId) throws HSKException;
	
	/**
	 * 根据md_evaluate表主键删除MdEvaluate对象记录
     * @param  mevaId  Integer类型(md_evaluate表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer mevaId) throws HSKException;
	
	
	
	/**
	 * 根据md_evaluate表主键删除多条MdEvaluate对象记录
     * @param  mevaIds  Integer类型(md_evaluate表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String mevaIds) throws HSKException;
	
	 /**
	 * 新增或修改md_evaluate表记录 ,如果md_evaluate表主键MdEvaluate.mevaId为空就是添加，如果非空就是修改
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdEvaluate att_MdEvaluate) throws HSKException;

	/**
	 *根据MdEvaluate对象作为对(md_evaluate表进行查询，获取分页对象
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdEvaluate att_MdEvaluate) throws HSKException;

	/**
	 * 获取评价统计分页对象
	 * @param att_MdEvaluate
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getEvaluateListPagerModelObject(MdEvaluate att_MdEvaluate) throws HSKException;
	
}