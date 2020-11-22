package com.hsk.xframe.web.exportExcels.service;

import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.dentistmall.api.persistence.MdOrderMx;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysSalesManEntity;

public interface IExportExcelService {
    SysRetrunMessage exportSalesMan1(SysSalesManEntity sysSalesManEntity, String salesmanIds, String states1) throws Exception;
    //管理员导出全部代理商
    SysRetrunMessage exportSalesManAll(SysSalesManEntity sysSalesManEntity) throws Exception;
    //交易管理下-全部订单，全部导出功能
    public SysRetrunMessage exportCgOrderList(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx, String moiIds) throws Exception;
    //订单维护下-批量导出对账单
    public SysRetrunMessage exportDzOrderList(MdOrderInfo att_MdOrderInfo,MdOrderMx att_MdOrderMx,String moiIds) throws Exception;
    //导出业务员下面的所有导出
    public SysRetrunMessage exportSalesManAgent(SysSalesManEntity sysSalesManEntity) throws Exception;
    //管理员导出查看关联下的导出全部
    public SysRetrunMessage exportSalesManAdmin(SysSalesManEntity sysSalesManEntity) throws Exception;
    public SysRetrunMessage exportSalesMan(SysSalesManEntity sysSalesManEntity) throws Exception;
    //管理员导出首页全部订单
    public SysRetrunMessage exportSevenDayTwo() throws Exception;
    //管理员业务放款导出放款明
    public SysRetrunMessage exportLoanMxList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1, String moneyFw1,
                                             String moneyFw2, String gongYingS, String caiGou, String jiGouMenZhen, Integer selectValue,
                                             String dateInput7, String dateInput8, Integer moiId) throws Exception;

    //导出领料员申领管理批量导出物料清单和导出单个物料清单
    public SysRetrunMessage exportPick(String  moiIds) throws Exception;
}
