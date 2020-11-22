package com.hsk.xframe.web.exportExcels.service;

import com.hsk.dentistmall.api.persistence.MdCheckInventoryEntity;
import com.hsk.supper.dto.comm.SysRetrunMessage;

public interface IEnterWarehouseExportService {

    //导出仓管入库报表
    public SysRetrunMessage exportWarehousingList(String warehousCode, Integer select1, String remarks, String billCode, String operationDate, Integer desc, Integer limit, Integer page) throws Exception;
    //入库详情报表
    public SysRetrunMessage exportWarehousingListMx(Integer wewId,String wmsMiIds) throws Exception;
    //导出盘点管理
    public SysRetrunMessage exportCheckInvent(Integer ciId,String ciIds,String searchName,String brand,String batchCode,String checkCode,String checkType,String createRen,String startDate) throws Exception;
    //导出库存
    public SysRetrunMessage exportInventoryView(String wiId,String InventoryStart,String InventoryEnd,String searchName,String brand,String createDate1,String batchCode,String checkCode,String checkType,String createRen,String startDate) throws Exception;
    //仓管角色导出入库管理模块报表  调整完善报表中的数据
    public SysRetrunMessage exportWarehousingInfoList(Integer wewId,String warehousCode,String select1,String remarks,String operationDate,String wewId1) throws Exception;
    //仓管导出调价报表
    public SysRetrunMessage exportPriceAdjustmentInv(String paiIds,String checkCode,String searchName,String select) throws Exception;
    //出库更换报表模板
    public SysRetrunMessage exportOutWarehousing(Integer mooId,Integer wowId,String mooIds,String wowIds,String mooCode,String customer,String customerName,String orderTimeStart,String orderTimeEnd,String outTimeStart,String outTimeEnd,String flowState) throws Exception;
    //导出物料日志中的表格
    public SysRetrunMessage exportWzidExcel(Integer wiId,String matCode,String matName,String quantity) throws Exception;
    //重新整理批量导出出库报表统计
    public SysRetrunMessage plExportOutWarehousing(Integer count,Integer stateMooId,String mooIds,String wowIds,String mooCode,String customer,String customerName,String orderTimeStart,String orderTimeEnd,String outTimeStart,String outTimeEnd,String flowState) throws Exception;
    //导出商品模型报表模板
    public SysRetrunMessage exportmMdMaterielModel(String wmsModelIds) throws Exception;
    //导出商品管理商品明细表模板
    public SysRetrunMessage exportmMdMateriel(String wmsMiIds) throws Exception;
}
