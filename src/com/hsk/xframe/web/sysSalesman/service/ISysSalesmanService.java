package com.hsk.xframe.web.sysSalesman.service;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysSalesManEntity;

import java.io.IOException;

/**
 * author: yangfeng
 * time: 2020/1/3 15:08
 */
public interface ISysSalesmanService {

    SysRetrunMessage saveSalesman(SysSalesManEntity sysSalesManEntity) throws HSKException;

    PagerModel getPagerModelObject(SysSalesManEntity sysSalesManEntity) throws HSKException;

    PagerModel getPageModelInfo(SysSalesManEntity sysSalesManEntity) throws HSKException;

    SysSalesManEntity getObject(Integer salesmanId) throws HSKException;

    PagerModel getSalesManViewPagerModel(SysSalesManEntity sysSalesManEntity) throws HSKException;

    SysRetrunMessage updateSysSalesmanBySalesId(SysSalesManEntity sysSalesManEntity) throws HSKException;

    SysRetrunMessage updateSysSalesmanStateBySalesId(SysSalesManEntity sysSalesManEntity) throws HSKException;

    SysRetrunMessage deleteObject(SysSalesManEntity sysSalesManEntity) throws HSKException;

    SysRetrunMessage deleteAllObject(SysSalesManEntity sysSalesManEntity) throws HSKException;

    SysRetrunMessage updateVerify(Integer id, Integer verifyState, String verifyRemark) throws HSKException;

    SysRetrunMessage getPageModelInfoCount(SysSalesManEntity sysSalesManEntity) throws HSKException;

    //添加业务员代理发展统计表
    SysRetrunMessage exportSalesMan(SysSalesManEntity sysSalesManEntity, String salesmanIds, String states1) throws HSKException;

    //管理员导出查看关联下的导出全部
    SysRetrunMessage exportSalesManAdmin(SysSalesManEntity sysSalesManEntity) throws HSKException;

    //管理员导出全部代理商
    SysRetrunMessage exportSalesManAll(SysSalesManEntity sysSalesManEntity) throws HSKException;

    //业务员导出代理商
    SysRetrunMessage exportSalesManAgent(SysSalesManEntity sysSalesManEntity) throws HSKException;

    PagerModel getSalesManLoanMxList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1, String moneyFw1,
                                     String moneyFw2, String gongYingS, String caiGou, String jiGouMenZhen, Integer selectValue, String dateInput7, String dateInput8, Integer moiId, Integer limit, Integer page) throws HSKException;

    SysRetrunMessage saveLoan(String checkStr,String loanMoney) throws HSKException;

    SysRetrunMessage getTotalMoney() throws HSKException;

    /**
     * 查询供应商业务员的供应商
     * @return
     * @throws HSKException
     */
    PagerModel getSalesManSupplier(Integer salesmanId) throws HSKException;
}
