package com.hsk.dentistmall.api.daobbase;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_dentist_hospital表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:17
 */
public interface IMdDentistHospitalDao{
	
	/**
	 * 根据md_dentist_hospital表主键查找MdDentistHospital对象记录
	 * @param  RbsId  Integer类型(md_dentist_hospital表主键)
	 * @return MdDentistHospital md_dentist_hospital表记录
	 * @throws HSKDBException
	 */	
	public MdDentistHospital findMdDentistHospitalById(Integer RbsId) throws HSKDBException;
	
	/**
	 * 根据md_dentist_hospital表主键删除MdDentistHospital对象记录
     * @param  RbsId  Integer类型(md_dentist_hospital表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdDentistHospitalById(Integer RbsId) throws HSKDBException;
	 
	/**
 	 * 根据md_dentist_hospital表主键修改MdDentistHospital对象记录
	 * @param  RbsId  Integer类型(md_dentist_hospital表主键)
	 * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
	 * @return MdDentistHospital  修改后的MdDentistHospital对象记录
	 * @throws HSKDBException
	 */
	public  MdDentistHospital updateMdDentistHospitalById(Integer RbsId,MdDentistHospital att_MdDentistHospital) throws HSKDBException;
	
	/**
	 * 新增md_dentist_hospital表 记录
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
     * @return md_dentist_hospital表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdDentistHospital(MdDentistHospital att_MdDentistHospital) throws HSKDBException;
	
	 /**
	 * 新增或修改md_dentist_hospital表记录 ,如果md_dentist_hospital表主键MdDentistHospital.RbsId为空就是添加，如果非空就是修改
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
     * @return MdDentistHospital  修改后的MdDentistHospital对象记录
	 * @throws HSKDBException
	 */
	public  MdDentistHospital saveOrUpdateMdDentistHospital( MdDentistHospital att_MdDentistHospital) throws HSKDBException;
	
	/**
	 *根据MdDentistHospital对象作为对(md_dentist_hospital表进行查询，获取分页对象
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdDentistHospital (MdDentistHospital att_MdDentistHospital) throws HSKDBException;
	
    /**
	 *根据MdDentistHospital对象作为对(md_dentist_hospital表进行查询，获取分页对象
     * @param  att_MdDentistHospital  MdDentistHospital类型(md_dentist_hospital表记录)
     * @return List<MdDentistHospital>  列表对象
	 * @throws HSKDBException 
	 */
	public List<MdDentistHospital> getListByMdDentistHospital ( MdDentistHospital att_MdDentistHospital) throws HSKDBException;

}