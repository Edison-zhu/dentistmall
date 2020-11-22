package com.hsk.dentistmall.api.daobbase;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_evaluate表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2018-03-13 14:44:35
 */
public interface IMdEvaluateDao{
	
	/**
	 * 根据md_evaluate表主键查找MdEvaluate对象记录
	 * @param  MevaId  Integer类型(md_evaluate表主键)
	 * @return MdEvaluate md_evaluate表记录
	 * @throws HSKDBException
	 */	
	public MdEvaluate findMdEvaluateById(Integer MevaId) throws HSKDBException;
	
	/**
	 * 根据md_evaluate表主键删除MdEvaluate对象记录
     * @param  MevaId  Integer类型(md_evaluate表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdEvaluateById(Integer MevaId) throws HSKDBException;
	 
	/**
 	 * 根据md_evaluate表主键修改MdEvaluate对象记录
	 * @param  MevaId  Integer类型(md_evaluate表主键)
	 * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
	 * @return MdEvaluate  修改后的MdEvaluate对象记录
	 * @throws HSKDBException
	 */
	public  MdEvaluate updateMdEvaluateById(Integer MevaId,MdEvaluate att_MdEvaluate) throws HSKDBException;
	
	/**
	 * 新增md_evaluate表 记录
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
     * @return md_evaluate表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdEvaluate(MdEvaluate att_MdEvaluate) throws HSKDBException;
	
	 /**
	 * 新增或修改md_evaluate表记录 ,如果md_evaluate表主键MdEvaluate.MevaId为空就是添加，如果非空就是修改
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
     * @return MdEvaluate  修改后的MdEvaluate对象记录
	 * @throws HSKDBException
	 */
	public  MdEvaluate saveOrUpdateMdEvaluate( MdEvaluate att_MdEvaluate) throws HSKDBException;
	
	/**
	 *根据MdEvaluate对象作为对(md_evaluate表进行查询，获取分页对象
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdEvaluate (MdEvaluate att_MdEvaluate) throws HSKDBException;
	
    /**
	 *根据MdEvaluate对象作为对(md_evaluate表进行查询，获取分页对象
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
     * @return List<MdEvaluate>  列表对象
	 * @throws HSKDBException 
	 */
	public List<MdEvaluate> getListByMdEvaluate ( MdEvaluate att_MdEvaluate) throws HSKDBException;
	

	public PagerModel getPagerModelByCount(MdEvaluate att_MdEvaluate)
			throws HSKDBException ;

}