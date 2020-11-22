package com.hsk.miniapi.xframe.web.sysSameProduct.service;

import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysSameProductEntity;

import java.util.List;

/**
 * author: yangfeng
 * time: 2020/3/24 15:00
 */
public interface ISysSameProductApiService {
    /**
     * 保存数据
     * @param sysSameProductEntity
     * @throws HSKException
     */
    SysRetrunMessage saveSameProduct(SysSameProductEntity sysSameProductEntity) throws HSKException;

    SysRetrunMessage saveSameProductByWmsMiIds(SysSameProductEntity sysSameProductEntity) throws HSKException;

    /**
     * 获取数据
     * @param sysSameProductEntity
     * @return
     * @throws HSKException
     */
    PagerModel getPagerModelObject(SysSameProductEntity sysSameProductEntity) throws HSKException;

    /**
     * 获取数据 返回类型应该是同款对象
     * @param sameProductCode
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getObjectBySPId(String sameProductCode) throws HSKException;

    /**
     * 检查是否拥有一样的商品或者其他相关方法
     * @param sysSameProductEntity
     * @return
     * @throws HSKException
     */
    SysRetrunMessage getObjectBySPIdAndWmsMiId(SysSameProductEntity sysSameProductEntity) throws HSKException;

    PagerModel getSameProductViewPagerModel(SysSameProductEntity sysSameProductEntity) throws HSKException;

    /**
     * 获取同款所有商品 返回的数据应该是商品对象
     * @param sysSameProductEntity
     * @return
     * @throws HSKException
     */
    PagerModel getSameProductProducts(SysSameProductEntity sysSameProductEntity) throws HSKException;

    void updateSysSameProductBySPId(SysSameProductEntity sysSameProductEntity) throws HSKException;

    /**
     * 删除商品
     * @param sameProductCode
     * @param wmsMiId
     * @throws HSKException
     */
    SysRetrunMessage deleteObject(String sameProductCode, String wmsMiId) throws HSKException;

    /**
     * 删除同款商品所有商品
     * @param sameProductCode
     * @throws HSKException
     */
    SysRetrunMessage deleteAllObject(String sameProductCode) throws HSKException;

    SysRetrunMessage countSameProducts(String sameProductCode) throws HSKException;

    SysRetrunMessage getAllPagerModelObjectDistinct(SysSameProductEntity sysSameProductEntity, String distinctName) throws HSKException;

    //导出同款商品
    public SysRetrunMessage exportSameCommodity(String sameProductCode) throws HSKException;
}
