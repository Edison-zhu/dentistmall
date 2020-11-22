package com.hsk.dentistmall.web.business.service;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.service.IDSTService;
import com.hsk.exception.HSKException;

/**
 * business业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:17
 */
public interface IMdDentistHospitalService{


		/**
	 * 查找MdDentistHospital对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  rbsId  Integer类型(md_dentist_hospital表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer rbsId) throws HSKException;

	
	/**
	 * 查找MdDentistHospital对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  rbsId  Integer类型(md_dentist_hospital表主键)
	 * @return MdDentistHospital md_dentist_hospital表记录
	 * @throws HSKException
	 */	
	public MdDentistHospital findObject(Integer rbsId) throws HSKException;
	
	/**
	 * 根据md_dentist_hospital表主键删除MdDentistHospital对象记录
     * @param  rbsId  Integer类型(md_dentist_hospital表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer rbsId) throws HSKException;
	
	
	
	/**
	 * 根据md_dentist_hospital表主键删除多条MdDentistHospital对象记录
     * @param  rbsIds  Integer类型(md_dentist_hospital表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String rbsIds) throws HSKException;
	
	 /**
	 * 新增或修改md_dentist_hospital表记录 ,如果md_dentist_hospital表主键MdDentistHospital.rbsId为空就是添加，如果非空就是修改
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject( MdDentistHospital att_MdDentistHospital, String listFilecode) throws HSKException;
	
	/**
	 *根据MdDentistHospital对象作为对(md_dentist_hospital表进行查询，获取分页对象
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdDentistHospital att_MdDentistHospital) throws HSKException;
	/**
	 * 修改审核
	 * @param id
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage updateVerify(Integer id, Integer verifyState, String verifyRemark) throws HSKException;
}