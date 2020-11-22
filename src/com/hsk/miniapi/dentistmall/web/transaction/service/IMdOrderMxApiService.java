package com.hsk.miniapi.dentistmall.web.transaction.service;

import java.util.*;

import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.service.IDSTService;
import com.hsk.exception.HSKException;

/**
 * transaction业务操作接口类
 *
 * @author 作者:admin
 * @version 版本信息:v1.0   创建时间: 2017-09-25 14:17:00
 */
public interface IMdOrderMxApiService {


    /**
     * 查找MdOrderMx对象记录，用于弹出添加、修改窗口时候初始化数据
     *
     * @param momId Integer类型(md_order_mx表主键)
     * @return SysRetrunMessage 对象记录
     * @throws HSKException
     */
    public SysRetrunMessage findFormObject(Integer momId) throws HSKException;


    /**
     * 查找MdOrderMx对象记录，用于弹出添加、修改窗口时候初始化数据
     *
     * @param momId Integer类型(md_order_mx表主键)
     * @return MdOrderMx md_order_mx表记录
     * @throws HSKException
     */
    public MdOrderMx findObject(Integer momId) throws HSKException;

    /**
     * 根据md_order_mx表主键删除MdOrderMx对象记录
     *
     * @param momId Integer类型(md_order_mx表主键)
     * @return SysRetrunMessage 对象记录
     * @throws HSKException
     */
    public SysRetrunMessage deleteObject(Integer momId) throws HSKException;


    /**
     * 根据md_order_mx表主键删除多条MdOrderMx对象记录
     *
     * @param momIds Integer类型(md_order_mx表主键)
     * @return SysRetrunMessage 对象记录
     * @throws HSKException
     */
    public SysRetrunMessage deleteAllObject(String momIds) throws HSKException;

    /**
     * 新增或修改md_order_mx表记录 ,如果md_order_mx表主键MdOrderMx.momId为空就是添加，如果非空就是修改
     *
     * @param att_MdOrderMx MdOrderMx类型(md_order_mx表记录)
     * @return SysRetrunMessage 对象记录
     * @throws HSKException
     */
    public SysRetrunMessage saveOrUpdateObject(MdOrderMx att_MdOrderMx) throws HSKException;

    /**
     * 根据MdOrderMx对象作为对(md_order_mx表进行查询，获取分页对象
     *
     * @param att_MdOrderMx MdOrderMx类型(md_order_mx表记录)
     * @return PagerModel  分页对象
     * @throws HSKException
     */
    public PagerModel getPagerModelObject(MdOrderMx att_MdOrderMx) throws HSKException;

    /**
     * 根据订单ID查询订单明细列表
     *
     * @param moiId
     * @return
     * @throws HSKException
     */
    public PagerModel getMdOrderMxListByMoiId(Integer moiId) throws HSKException;

    public PagerModel getMdOrderMxListByMoiIdForEnter(Integer moiId) throws HSKException;

    /**
     * 根据订单ID查询订单明细
     *
     * @param moiId
     * @return
     * @throws HSKException
     */
    PagerModel getOrderMxListByMoiId(Integer moiId, Integer limit, Integer page) throws HSKException;

    PagerModel getOrderMxListBySearch(Integer moiId, Integer limit, Integer page, String matName, Integer state) throws HSKException;

    SysRetrunMessage getOrderMxListByMoiId2(Integer moiId) throws HSKException;

    //根据商品名称搜索
    PagerModel getOrderMxName(Integer moiId, String matName, Integer limit, Integer page) throws HSKException;

}