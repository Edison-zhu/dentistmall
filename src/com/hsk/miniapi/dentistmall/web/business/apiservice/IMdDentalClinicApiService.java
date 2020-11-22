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
public interface IMdDentalClinicApiService {


		/**
	 * 查找MdDentalClinic对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  rbbId  Integer类型(md_dental_clinic表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer rbbId) throws HSKException;

	
	/**
	 * 查找MdDentalClinic对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  rbbId  Integer类型(md_dental_clinic表主键)
	 * @return MdDentalClinic md_dental_clinic表记录
	 * @throws HSKException
	 */	
	public MdDentalClinic findObject(Integer rbbId) throws HSKException;
	
	/**
	 * 根据md_dental_clinic表主键删除MdDentalClinic对象记录
     * @param  rbbId  Integer类型(md_dental_clinic表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer rbbId) throws HSKException;
	
	
	
	/**
	 * 根据md_dental_clinic表主键删除多条MdDentalClinic对象记录
     * @param  rbbIds  Integer类型(md_dental_clinic表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String rbbIds) throws HSKException;
	
	 /**
	 * 新增或修改md_dental_clinic表记录 ,如果md_dental_clinic表主键MdDentalClinic.rbbId为空就是添加，如果非空就是修改
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdDentalClinic att_MdDentalClinic) throws HSKException;

	/**
	 *根据MdDentalClinic对象作为对(md_dental_clinic表进行查询，获取分页对象
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdDentalClinic att_MdDentalClinic) throws HSKException;
	/**
	 * 修改审核
	 * @param id
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage updateVerify(Integer id, Integer verifyState, String verifyRemark) throws HSKException;
}