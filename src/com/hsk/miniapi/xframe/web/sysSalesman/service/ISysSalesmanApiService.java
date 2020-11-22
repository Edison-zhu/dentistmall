package com.hsk.miniapi.xframe.web.sysSalesman.service;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysSalesManEntity;

/**
 * author: yangfeng
 * time: 2020/1/3 15:08
 */
public interface ISysSalesmanApiService {

    SysRetrunMessage saveSalesman(SysSalesManEntity sysSalesManEntity) throws HSKException;

    PagerModel getPagerModelObject(SysSalesManEntity sysSalesManEntity) throws HSKException;

    PagerModel getPageModelInfo(SysSalesManEntity sysSalesManEntity) throws HSKException;

    SysSalesManEntity getObject(Integer salesmanId) throws HSKException;

    PagerModel getSalesManViewPagerModel(SysSalesManEntity sysSalesManEntity) throws HSKException;

    SysRetrunMessage updateSysSalesmanBySalesId(SysSalesManEntity sysSalesManEntity) throws HSKException;

    SysRetrunMessage deleteObject(SysSalesManEntity sysSalesManEntity) throws HSKException;

    SysRetrunMessage deleteAllObject(SysSalesManEntity sysSalesManEntity) throws HSKException;

    SysRetrunMessage updateVerify(Integer id, Integer verifyState, String verifyRemark) throws HSKException;

    SysRetrunMessage getPageModelInfoCount(SysSalesManEntity sysSalesManEntity) throws HSKException;
    //添加业务员代理发展统计表
    SysRetrunMessage exportSalesMan(SysSalesManEntity sysSalesManEntity) throws HSKException;

    /**
     * 更新地址
     * @param account
     * @param delivery 地址拼接，逗号隔开
     * @return
     * @throws HSKException
     */
    SysRetrunMessage updateDelivery(String account, String delivery, String salesAddress) throws HSKException;

    SysRetrunMessage getDelivery(String account) throws HSKException;

}
