package com.hsk.dentistmall.api.daobbase;

import java.util.*;

import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_out_order_mx表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-11-13 11:22:28
 */
public interface IMdOutOrderMxDao{
	
	/**
	 * 根据md_out_order_mx表主键查找MdOutOrderMx对象记录
	 * @param  MomId  Integer类型(md_out_order_mx表主键)
	 * @return MdOutOrderMx md_out_order_mx表记录
	 * @throws HSKDBException
	 */	
	public MdOutOrderMx findMdOutOrderMxById(Integer MomId) throws HSKDBException;
	
	/**
	 * 根据md_out_order_mx表主键删除MdOutOrderMx对象记录
     * @param  MomId  Integer类型(md_out_order_mx表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdOutOrderMxById(Integer MomId) throws HSKDBException;
	 
	/**
 	 * 根据md_out_order_mx表主键修改MdOutOrderMx对象记录
	 * @param  MomId  Integer类型(md_out_order_mx表主键)
	 * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
	 * @return MdOutOrderMx  修改后的MdOutOrderMx对象记录
	 * @throws HSKDBException
	 */
	public  MdOutOrderMx updateMdOutOrderMxById(Integer MomId,MdOutOrderMx att_MdOutOrderMx) throws HSKDBException;
	
	/**
	 * 新增md_out_order_mx表 记录
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
     * @return md_out_order_mx表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdOutOrderMx(MdOutOrderMx att_MdOutOrderMx) throws HSKDBException;
	
	 /**
	 * 新增或修改md_out_order_mx表记录 ,如果md_out_order_mx表主键MdOutOrderMx.MomId为空就是添加，如果非空就是修改
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
     * @return MdOutOrderMx  修改后的MdOutOrderMx对象记录
	 * @throws HSKDBException
	 */
	public  MdOutOrderMx saveOrUpdateMdOutOrderMx( MdOutOrderMx att_MdOutOrderMx) throws HSKDBException;
	
	/**
	 *根据MdOutOrderMx对象作为对(md_out_order_mx表进行查询，获取分页对象
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException 
	 */
	public PagerModel getPagerModelByMdOutOrderMx (MdOutOrderMx att_MdOutOrderMx) throws HSKDBException;
	
    /**
	 *根据MdOutOrderMx对象作为对(md_out_order_mx表进行查询，获取分页对象
     * @param  att_MdOutOrderMx  MdOutOrderMx类型(md_out_order_mx表记录)
     * @return List<MdOutOrderMx>  列表对象
	 * @throws HSKDBException 
	 */
	public List<MdOutOrderMx> getListByMdOutOrderMx ( MdOutOrderMx att_MdOutOrderMx) throws HSKDBException;

	/**
	 * 根据MdOutOrderMxMaterielInfoViewEntity申领出库与物料表关联查询
	 * @param att_mdOutOrderMx
	 * @return
	 * @throws HSKDBException
	 */
	List<MdOutOrderMxMaterielInfoViewEntity> getListByMdOutOrderMxMaterielInfoView(MdOutOrderMxMaterielInfoViewEntity att_mdOutOrderMx) throws HSKDBException;
	
	public List<Map<Object,Object>> getMxListByOrder(MdOutOrder att_MdOutOrder,MdOutOrderMx att_MdOutOrderMx) throws HSKDBException;
	
}