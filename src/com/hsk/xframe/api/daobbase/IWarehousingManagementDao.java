package com.hsk.xframe.api.daobbase;

import com.hsk.dentistmall.api.persistence.MdEnterWarehouse;
import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.dentistmall.api.persistence.MdOrderMx;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;

import java.util.List;
import java.util.Map;

public interface IWarehousingManagementDao {
    public List<Map<String,Object>> getWarehousingList(String warehousCode, Integer select1, String remarks, String billCode, String operationDate,Integer desc,
                                                       Integer rbaId, Integer rbsId, Integer rbbId, Integer suiId, String purchaseType, Integer limit, Integer page,String wewId1) throws HSKDBException;

    public MdEnterWarehouse findWareById(Integer wewId) throws HSKDBException;

    public void  deleteWare(Integer wewId) throws HSKDBException;

    //订单入库弹出框数据
    public List<Map<String,Object>> getPagerModelByEnterMdOrderInfo(MdOrderInfo att_MdOrderInfo,String cjDate,Integer limit, Integer page) throws HSKDBException;
    //新增入库数据
    public List<Map<String,Object>> getAddgetWarehousingMx(Integer moiId,Integer state1,Integer limit,Integer page) throws HSKDBException;

    void deleteEnterMx(Integer wewId) throws HSKDBException;

    MdOrderMx findMdOrderMx(String relationBillCode, Integer mmfId) throws HSKDBException;

    void deleteEnterMxByMxId(Integer wewMxId) throws HSKDBException;
}
