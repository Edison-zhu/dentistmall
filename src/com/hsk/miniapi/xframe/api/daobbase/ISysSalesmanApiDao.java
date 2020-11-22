package com.hsk.miniapi.xframe.api.daobbase;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.xframe.api.persistence.SysSalesManEntity;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/1/3 15:21
 */
public interface ISysSalesmanApiDao {

    void saveSalesman(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    PagerModel getPagerModelObject(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    SysSalesManEntity getObject(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    PagerModel getSalesManViewPagerModel(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    void updateSysSalesmanBySalesId(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    void deleteObject(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    void deleteAllObject(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    PagerModel getPageModelInfo(SysSalesManEntity sysSalesManEntity) throws HSKDBException;

    Integer getPageModelInfoLeadedCount(SysSalesManEntity sysSalesManEntity, Integer i) throws HSKDBException;
    Integer getPageModelInfoCompanyCount(SysSalesManEntity sysSalesManEntity, Integer i) throws HSKDBException;


    List<Map<String, Object>> exportSalesMan(SysSalesManEntity sysSalesManEntity) throws HSKDBException;
}
