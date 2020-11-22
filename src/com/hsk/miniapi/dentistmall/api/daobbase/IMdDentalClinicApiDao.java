package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_dental_clinic表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:17
 */
public interface IMdDentalClinicApiDao {
	
	/**
	 * 根据md_dental_clinic表主键查找MdDentalClinic对象记录
	 * @param  RbbId  Integer类型(md_dental_clinic表主键)
	 * @return MdDentalClinic md_dental_clinic表记录
	 * @throws HSKDBException
	 */	
	public MdDentalClinic findMdDentalClinicById(Integer RbbId) throws HSKDBException;
	
	/**
	 * 根据md_dental_clinic表主键删除MdDentalClinic对象记录
     * @param  RbbId  Integer类型(md_dental_clinic表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdDentalClinicById(Integer RbbId) throws HSKDBException;
	 
	/**
 	 * 根据md_dental_clinic表主键修改MdDentalClinic对象记录
	 * @param  RbbId  Integer类型(md_dental_clinic表主键)
	 * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
	 * @return MdDentalClinic  修改后的MdDentalClinic对象记录
	 * @throws HSKDBException
	 */
	public  MdDentalClinic updateMdDentalClinicById(Integer RbbId, MdDentalClinic att_MdDentalClinic) throws HSKDBException;

	/**
	 * 新增md_dental_clinic表 记录
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
     * @return md_dental_clinic表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdDentalClinic(MdDentalClinic att_MdDentalClinic) throws HSKDBException;

	 /**
	 * 新增或修改md_dental_clinic表记录 ,如果md_dental_clinic表主键MdDentalClinic.RbbId为空就是添加，如果非空就是修改
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
     * @return MdDentalClinic  修改后的MdDentalClinic对象记录
	 * @throws HSKDBException
	 */
	public  MdDentalClinic saveOrUpdateMdDentalClinic(MdDentalClinic att_MdDentalClinic) throws HSKDBException;

	/**
	 *根据MdDentalClinic对象作为对(md_dental_clinic表进行查询，获取分页对象
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdDentalClinic(MdDentalClinic att_MdDentalClinic) throws HSKDBException;

    /**
	 *根据MdDentalClinic对象作为对(md_dental_clinic表进行查询，获取分页对象
     * @param  att_MdDentalClinic  MdDentalClinic类型(md_dental_clinic表记录)
     * @return List<MdDentalClinic>  列表对象
	 * @throws HSKDBException
	 */
	public List<MdDentalClinic> getListByMdDentalClinic(MdDentalClinic att_MdDentalClinic) throws HSKDBException;

}