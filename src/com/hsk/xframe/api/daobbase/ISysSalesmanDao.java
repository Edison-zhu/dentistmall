package com.hsk.xframe.api.daobbase;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysSalesManEntity;

import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/1/3 15:21
 */
public interface ISysSalesmanDao {

    void saveSalesman(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    PagerModel getPagerModelObject(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    SysSalesManEntity getObject(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    SysSalesManEntity getSalesManInfo(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    PagerModel getSalesManViewPagerModel(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    void updateSysSalesmanBySalesId(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    void deleteObject(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    void deleteAllObject(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    PagerModel getPageModelInfo(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    Integer getPageModelInfoLeadedCount(SysSalesManEntity sysSalesManEntity, Integer i) throws HSKDBException;

    Integer getPageModelInfoCompanyCount(SysSalesManEntity sysSalesManEntity, Integer i) throws HSKDBException;


    List<Map<String, Object>> exportSalesMan(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    //导出管理员登陆下查看关联下导出全部
    List<Map<String, Object>> exportSalesManAdmin(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    //导出管理员下的导出全部代理商
    List<Map<String, Object>> exportSalesManAll(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    //根据名称查ID
    List<Map<String, Integer>> selectSalesManId(Integer salesAccount) throws HSKDBException;

    //根据名称查ID
    List<Map<String, Integer>> selectSalesManId1(Integer orgId) throws HSKDBException;

    List<Map<String, Object>> getSalesManLoanMxList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1,
                                                    String moneyFw1, String moneyFw2, String gongYingS, String caiGou, String jiGouMenZhen, Integer selectValue,
                                                    String dateInput7, String dateInput8, Integer moiId, Integer limit, Integer page) throws HSKDBException;

    Integer totalAll() throws HSKDBException;

    Double totalLanMoney(Integer lanState) throws HSKDBException;

    PagerModel getPagerModelSupplier(Integer salesmanId) throws HSKDBException;

    Integer getPagerModelInfoSupplierCount(SysSalesManEntity sysSalesManEntity, Integer i) throws HSKDBException;
}
