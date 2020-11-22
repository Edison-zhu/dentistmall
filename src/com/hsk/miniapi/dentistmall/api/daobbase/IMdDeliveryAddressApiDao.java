package com.hsk.miniapi.dentistmall.api.daobbase;

import java.util.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_delivery_address表数据库层面操作接口类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:22:54
 */
public interface IMdDeliveryAddressApiDao {
	
	/**
	 * 根据md_delivery_address表主键查找MdDeliveryAddress对象记录
	 * @param  MdaId  Integer类型(md_delivery_address表主键)
	 * @return MdDeliveryAddress md_delivery_address表记录
	 * @throws HSKDBException
	 */	
	public MdDeliveryAddress findMdDeliveryAddressById(Integer MdaId) throws HSKDBException;
	
	/**
	 * 根据md_delivery_address表主键删除MdDeliveryAddress对象记录
     * @param  MdaId  Integer类型(md_delivery_address表主键)
	 * @throws HSKDBException
	 */
	public void deleteMdDeliveryAddressById(Integer MdaId) throws HSKDBException;
	 
	/**
 	 * 根据md_delivery_address表主键修改MdDeliveryAddress对象记录
	 * @param  MdaId  Integer类型(md_delivery_address表主键)
	 * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
	 * @return MdDeliveryAddress  修改后的MdDeliveryAddress对象记录
	 * @throws HSKDBException
	 */
	public  MdDeliveryAddress updateMdDeliveryAddressById(Integer MdaId, MdDeliveryAddress att_MdDeliveryAddress) throws HSKDBException;

	/**
	 * 新增md_delivery_address表 记录
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
     * @return md_delivery_address表主键值
	 * @throws HSKDBException
	 */
	public Integer saveMdDeliveryAddress(MdDeliveryAddress att_MdDeliveryAddress) throws HSKDBException;

	 /**
	 * 新增或修改md_delivery_address表记录 ,如果md_delivery_address表主键MdDeliveryAddress.MdaId为空就是添加，如果非空就是修改
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
     * @return MdDeliveryAddress  修改后的MdDeliveryAddress对象记录
	 * @throws HSKDBException
	 */
	public  MdDeliveryAddress saveOrUpdateMdDeliveryAddress(MdDeliveryAddress att_MdDeliveryAddress) throws HSKDBException;

	void updateMdDeliveryAddressDefualt(String excludeMaIds) throws HSKDBException;

	/**
	 *根据MdDeliveryAddress对象作为对(md_delivery_address表进行查询，获取分页对象
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
     * @return PagerModel  分页对象
	 * @throws HSKDBException
	 */
	public PagerModel getPagerModelByMdDeliveryAddress(MdDeliveryAddress att_MdDeliveryAddress) throws HSKDBException;

    /**
	 *根据MdDeliveryAddress对象作为对(md_delivery_address表进行查询，获取分页对象
     * @param  att_MdDeliveryAddress  MdDeliveryAddress类型(md_delivery_address表记录)
     * @return List<MdDeliveryAddress>  列表对象
	 * @throws HSKDBException
	 */
	public List<MdDeliveryAddress> getListByMdDeliveryAddress(MdDeliveryAddress att_MdDeliveryAddress) throws HSKDBException;

}