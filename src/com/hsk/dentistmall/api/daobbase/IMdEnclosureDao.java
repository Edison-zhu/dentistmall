package com.hsk.dentistmall.api.daobbase;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_enclosure表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:17
 */
public interface IMdEnclosureDao{
	
	/**
	 * 根据md_enclosure表主键查找MdEnclosure对象记录
	 * @param  RbenId  Integer类型(md_enclosure表主键)
	 * @return MdEnclosure md_enclosure表记录
	 * @throws HSKDBException
	 */	
	public MdEnclosure findMdEnclosureById(Integer RbenId) throws HSKDBException;
	
	/**
	 * 根据md_enclosure表主键删除MdEnclosure对象记录
     * @param  RbenId  Integer类型(md_enclosure表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdEnclosureById(Integer RbenId) throws HSKDBException;
	 
	/**
 	 * 根据md_enclosure表主键修改MdEnclosure对象记录
	 * @param  RbenId  Integer类型(md_enclosure表主键)
	 * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
	 * @return MdEnclosure  修改后的MdEnclosure对象记录
	 * @throws HSKDBException
	 */
	public  MdEnclosure updateMdEnclosureById(Integer RbenId,MdEnclosure att_MdEnclosure) throws HSKDBException;
	
	/**
	 * 新增md_enclosure表 记录
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
     * @return md_enclosure表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdEnclosure(MdEnclosure att_MdEnclosure) throws HSKDBException;
	
	 /**
	 * 新增或修改md_enclosure表记录 ,如果md_enclosure表主键MdEnclosure.RbenId为空就是添加，如果非空就是修改
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
     * @return MdEnclosure  修改后的MdEnclosure对象记录
	 * @throws HSKDBException
	 */
	public  MdEnclosure saveOrUpdateMdEnclosure( MdEnclosure att_MdEnclosure) throws HSKDBException;
	
	/**
	 *根据MdEnclosure对象作为对(md_enclosure表进行查询，获取分页对象
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdEnclosure (MdEnclosure att_MdEnclosure) throws HSKDBException;
	
    /**
	 *根据MdEnclosure对象作为对(md_enclosure表进行查询，获取分页对象
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
     * @return List<MdEnclosure>  列表对象
	 * @throws HSKDBException 
	 */
	public List<MdEnclosure> getListByMdEnclosure ( MdEnclosure att_MdEnclosure) throws HSKDBException;
	/**
	 * 按照条件删除数据
	 * @param att_MdEnclosure
	 * @throws HSKDBException
	 */
	public void delMdEnclosureList(MdEnclosure att_MdEnclosure) throws HSKDBException;

}