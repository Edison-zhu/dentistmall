package com.hsk.xframe.api.daobbase;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysSameProductEntity;
import com.sun.corba.se.spi.ior.ObjectKey;

import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/3/24 13:56
 */
public interface ISysSameProductDao {
    /**
     * 保存数据
     * @param sysSameProductEntity
     * @throws HSKDBException
     */
    void saveSameProduct(SysSameProductEntity sysSameProductEntity) throws HSKDBException;

    /**
     * 获取数据
     * @param sysSameProductEntity
     * @return
     * @throws HSKDBException
     */
    PagerModel getPagerModelObject(SysSameProductEntity sysSameProductEntity) throws HSKDBException;

    /**
     * 获取数据 返回类型应该是同款对象
     * @param sameProductCode
     * @return
     * @throws HSKDBException
     */
    List<SysSameProductEntity> getObjectBySPId(String sameProductCode) throws HSKDBException;

    /**
     * 检查是否拥有一样的商品或者其他相关方法
     * @param sysSameProductEntity
     * @return
     * @throws HSKDBException
     */
    SysSameProductEntity getObjectBySPIdAndWmsMiId(SysSameProductEntity sysSameProductEntity) throws HSKDBException;
    List<Map<String, Object>> getMaterielInfoByWmsMiId(SysSameProductEntity sysSameProductEntity) throws HSKDBException;

    PagerModel getSameProductViewPagerModel(SysSameProductEntity sysSameProductEntity) throws HSKDBException;

    /**
     * 获取同款所有商品 返回的数据应该是商品对象
     * @param sysSameProductEntity
     * @return
     * @throws HSKDBException
     */
    PagerModel getSameProductProducts(SysSameProductEntity sysSameProductEntity) throws HSKDBException;

    void updateSysSameProductBySPId(SysSameProductEntity sysSameProductEntity) throws HSKDBException;

    /**
     * 删除商品
     * @param sameProductCode
     * @param wmsMiId
     * @throws HSKDBException
     */
    void deleteObject(String sameProductCode, String wmsMiId) throws HSKDBException;

    /**
     * 删除同款商品所有商品
     * @param sameProductCode
     * @throws HSKDBException
     */
    void deleteAllObject(String sameProductCode) throws HSKDBException;

    Integer countSameProducts(String sameProductCode) throws HSKDBException;

    /**
     * 获取同款编号下的商品
     * @param sysSameProductCode
     * @param wmsMiId
     * @return
     * @throws HSKDBException
     */
    List<Map<String, Object>> getSameProductProductList(String sysSameProductCode, String wmsMiId) throws HSKDBException;

    void addSameProduct(SysSameProductEntity sysSameProductEntity) throws HSKDBException;

    PagerModel getSameProductMoreInfo(SysSameProductEntity sysSameProductEntity) throws HSKDBException;

    List<Map<String, Object>> getAllPagerModelObjectDistinct(SysSameProductEntity sysSameProductEntity, String distinctName) throws HSKDBException;

    List<Map<String, Object>> getSameProductProductOnlyWmsMiIdList(Integer id, String sysSameProductCode, String wmsMiIds) throws HSKDBException;

    List<Map<String, Object>> getSameProductProductOnlyMatNameList(Integer id, String sysSameProductCode, String wmsMiId) throws HSKDBException;

    Integer countSameProductsOnlyWmsMiId(Integer id, String sysSameProductCode, String wmsMiId) throws HSKDBException;

    Integer countSameProductsOnlyMatName(Integer id, String sysSameProductCode, String wmsMiId) throws HSKDBException;

    Integer getProductByState(String wmsMiId, Integer state) throws HSKDBException;
    //导出同款商品
    List<Map<String,Object>> exportSameCommodity(String sameProductCode) throws HSKDBException;
    //导出同款商品商品个数，上下架次数
    List<Map<String,Object>>  countMatnameState(String sameProductCode)throws HSKDBException;
    }
