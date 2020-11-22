package com.hsk.xframe.api.daobbase;

import com.hsk.dentistmall.api.persistence.MdCheckInventoryEntity;
import com.hsk.dentistmall.api.persistence.MdMaterielInfo;
import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.dentistmall.api.persistence.MdOrderMx;
import com.hsk.exception.HSKDBException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IExpoetExcelDao {
    //查询订单明细表里面的数据 导出报表
    public List<Map<String,Object>> getMxListbyOrderAndMx(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx, String moiIds) throws HSKDBException;
    //查询订单明细表中 orderCode出现的次数
    public Integer orderCodeCount(String orderCode,String orderCodeNext) throws HSKDBException;

    //导出首页7张表的数据
    public List<Map<String, Object>> getSevenListCountAll(Date Date1, Date Date2) throws HSKDBException;
    //导出首页7张表的数据
    public List<Map<String, Object>> getSevenListCountAll1(Date Date1, Date Date2) throws HSKDBException;
    //查询表中类型数据sql
    public  Integer getSevenListCountAll1Count(String type,String Date) throws HSKDBException;

    public List<Map<String, Object>> getSalesManLoanMxList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1, String moneyFw1, String moneyFw2, String gongYingS, String caiGou, String jiGouMenZhen, Integer selectValue, String dateInput7, String dateInput8, Integer moiId) throws HSKDBException;
    //导出领料员申领管理批量导出物料清单和导出单个物料清单
    public List<Map<String, Object>> exportPick(Integer  moiId) throws HSKDBException;
    //导出全部入库管理模块sql
    public List<Map<String, Object>> exportWarehousingList(String warehousCode, Integer select1, String remarks, String billCode, String operationDate,Integer desc, Integer rbaId, Integer rbsId, Integer rbbId, Integer suiId, String purchaseType) throws HSKDBException;
    //全部入库详情
    public List<Map<String, Object>> exportWarehousingListMx(Integer wewId,String wmsMiIds, MdMaterielInfo att_MdMaterielInfo) throws HSKDBException;
    //导出盘点管理
    public List<Map<String, Object>> exportCheckInvent(Integer ciId,String ciIds,String searchName,String brand,String batchCode,String checkCode,String checkType,String createRen,String startDate) throws HSKDBException;

    public List<Map<String, Object>> exportInventoryView(String wiId,String InventoryStart,String InventoryEnd,String searchName,String brand,String createDate1,Integer rba,Integer rbs,Integer rbb,String purchaseType) throws HSKDBException;
    //仓管角色导出入库管理模块报表  调整完善报表中的数据
    public List<Map<String, Object>> exportWarehousingInfoList(Integer wewId,String warehousCode,String select1,String remarks,String operationDate,String wewId1) throws HSKDBException;
    //仓管导出调价
    public List<Map<String, Object>> exportPriceAdjustmentInv(String paiIds,String checkCode,String searchName,String select) throws HSKDBException;
    public Double exportPriceAdjustmentInv2(Integer mmfId,Integer rbaId, Integer rbsId, Integer rbbId,String purchaseType) throws HSKDBException;
    //仓管导出出库
    public List<Map<String, Object>> exportOutWarehousing(Integer mooId,Integer wowId,String mooIds,String wowIds,String mooCode,String customer,String customerName,String orderTimeStart,String orderTimeEnd,String outTimeStart,String outTimeEnd,String flowState) throws HSKDBException;
    public List<Map<String, Object>> exportOutWarehousingMooIdAndWowId(Integer mooId,Integer wowId) throws HSKDBException;
    //库存导出日志中的出库明细
    public List<Map<String, Object>> exportWzidExcel(Integer wiId) throws HSKDBException;
    //仓管导出出库批量导出
    public List<Map<String, Object>> plExportOutWarehousing(Integer stateMooId,String mooIds,String wowIds,String mooCode,String customer,String customerName,String orderTimeStart,String orderTimeEnd,String outTimeStart,String outTimeEnd,String flowState) throws HSKDBException;
    public List<Map<String, Object>> exportmMdMateriel(String wmsMiIds) throws HSKDBException;
    public List<Map<String, Object>> exportmMdMaterielModel(String wmsModelIds) throws HSKDBException;
    }
