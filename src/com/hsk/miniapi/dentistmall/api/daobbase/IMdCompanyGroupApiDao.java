package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_company_group表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:16
 */
public interface IMdCompanyGroupApiDao {
	
	/**
	 * 根据md_company_group表主键查找MdCompanyGroup对象记录
	 * @param  RbaId  Integer类型(md_company_group表主键)
	 * @return MdCompanyGroup md_company_group表记录
	 * @throws HSKDBException
	 */	
	public MdCompanyGroup findMdCompanyGroupById(Integer RbaId) throws HSKDBException;
	
	/**
	 * 根据md_company_group表主键删除MdCompanyGroup对象记录
     * @param  RbaId  Integer类型(md_company_group表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdCompanyGroupById(Integer RbaId) throws HSKDBException;
	 
	/**
 	 * 根据md_company_group表主键修改MdCompanyGroup对象记录
	 * @param  RbaId  Integer类型(md_company_group表主键)
	 * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
	 * @return MdCompanyGroup  修改后的MdCompanyGroup对象记录
	 * @throws HSKDBException
	 */
	public  MdCompanyGroup updateMdCompanyGroupById(Integer RbaId, MdCompanyGroup att_MdCompanyGroup) throws HSKDBException;

	/**
	 * 新增md_company_group表 记录
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
     * @return md_company_group表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdCompanyGroup(MdCompanyGroup att_MdCompanyGroup) throws HSKDBException;

	 /**
	 * 新增或修改md_company_group表记录 ,如果md_company_group表主键MdCompanyGroup.RbaId为空就是添加，如果非空就是修改
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
     * @return MdCompanyGroup  修改后的MdCompanyGroup对象记录
	 * @throws HSKDBException
	 */
	public  MdCompanyGroup saveOrUpdateMdCompanyGroup(MdCompanyGroup att_MdCompanyGroup) throws HSKDBException;

	/**
	 *根据MdCompanyGroup对象作为对(md_company_group表进行查询，获取分页对象
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdCompanyGroup(MdCompanyGroup att_MdCompanyGroup) throws HSKDBException;

    /**
	 *根据MdCompanyGroup对象作为对(md_company_group表进行查询，获取分页对象
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
     * @return List<MdCompanyGroup>  列表对象
	 * @throws HSKDBException
	 */
	public List<MdCompanyGroup> getListByMdCompanyGroup(MdCompanyGroup att_MdCompanyGroup) throws HSKDBException;

}