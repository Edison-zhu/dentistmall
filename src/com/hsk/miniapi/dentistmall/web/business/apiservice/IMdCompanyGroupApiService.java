package com.hsk.miniapi.dentistmall.web.business.apiservice;

import com.hsk.dentistmall.api.persistence.*;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.exception.HSKException;

/**
 * business业务操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:16
 */
public interface IMdCompanyGroupApiService {


	/**
	 * 查找MdCompanyGroup对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  rbaId  Integer类型(md_company_group表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer rbaId) throws HSKException;

	
	/**
	 * 查找MdCompanyGroup对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  rbaId  Integer类型(md_company_group表主键)
	 * @return MdCompanyGroup md_company_group表记录
	 * @throws HSKException
	 */	
	public MdCompanyGroup findObject(Integer rbaId) throws HSKException;
	
	/**
	 * 根据md_company_group表主键删除MdCompanyGroup对象记录
     * @param  rbaId  Integer类型(md_company_group表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer rbaId) throws HSKException;
	
	
	
	/**
	 * 根据md_company_group表主键删除多条MdCompanyGroup对象记录
     * @param  rbaIds  Integer类型(md_company_group表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String rbaIds) throws HSKException;
	
	 /**
	 * 新增或修改md_company_group表记录 ,如果md_company_group表主键MdCompanyGroup.rbaId为空就是添加，如果非空就是修改
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject(MdCompanyGroup att_MdCompanyGroup) throws HSKException;

	/**
	 *根据MdCompanyGroup对象作为对(md_company_group表进行查询，获取分页对象
     * @param  att_MdCompanyGroup  MdCompanyGroup类型(md_company_group表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject(MdCompanyGroup att_MdCompanyGroup) throws HSKException;

	/**
	 * 修改审核
	 * @param id
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage updateVerify(Integer id, Integer verifyState, String verifyRemark) throws HSKException;
	
}