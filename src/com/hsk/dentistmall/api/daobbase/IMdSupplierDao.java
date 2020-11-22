package com.hsk.dentistmall.api.daobbase;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_supplier表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:17:00
 */
public interface IMdSupplierDao{
	
	/**
	 * 根据md_supplier表主键查找MdSupplier对象记录
	 * @param  WzId  Integer类型(md_supplier表主键)
	 * @return MdSupplier md_supplier表记录
	 * @throws HSKDBException
	 */	
	public MdSupplier findMdSupplierById(Integer WzId) throws HSKDBException;
	
	/**
	 * 根据md_supplier表主键删除MdSupplier对象记录
     * @param  WzId  Integer类型(md_supplier表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdSupplierById(Integer WzId) throws HSKDBException;
	 
	/**
 	 * 根据md_supplier表主键修改MdSupplier对象记录
	 * @param  WzId  Integer类型(md_supplier表主键)
	 * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
	 * @return MdSupplier  修改后的MdSupplier对象记录
	 * @throws HSKDBException
	 */
	public  MdSupplier updateMdSupplierById(Integer WzId,MdSupplier att_MdSupplier) throws HSKDBException;
	
	/**
	 * 新增md_supplier表 记录
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
     * @return md_supplier表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdSupplier(MdSupplier att_MdSupplier) throws HSKDBException;
	
	 /**
	 * 新增或修改md_supplier表记录 ,如果md_supplier表主键MdSupplier.WzId为空就是添加，如果非空就是修改
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
     * @return MdSupplier  修改后的MdSupplier对象记录
	 * @throws HSKDBException
	 */
	public  MdSupplier saveOrUpdateMdSupplier( MdSupplier att_MdSupplier) throws HSKDBException;
	
	/**
	 *根据MdSupplier对象作为对(md_supplier表进行查询，获取分页对象
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdSupplier (MdSupplier att_MdSupplier) throws HSKDBException;
	/**
	 * 根据搜索名称查询商家信息
	 * @param searchName
	 * @param state
	 * @return
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdSupplierSearch(String searchName,String state) throws HSKDBException;
	
    /**
	 *根据MdSupplier对象作为对(md_supplier表进行查询，获取分页对象
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
     * @return List<MdSupplier>  列表对象
	 * @throws HSKDBException 
	 */
	public List<MdSupplier> getListByMdSupplier ( MdSupplier att_MdSupplier) throws HSKDBException;

	MdSupplierTemp findMdSupplierByIdNoLogin(Integer wzId) throws HSKDBException;
}