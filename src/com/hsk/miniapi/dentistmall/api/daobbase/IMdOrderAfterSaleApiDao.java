package com.hsk.miniapi.dentistmall.api.daobbase;

import com.hsk.dentistmall.api.persistence.MdOrderAfterSaleEntity;
import com.hsk.dentistmall.api.persistence.MdOrderAfterSaleExtendEntity;
import com.hsk.dentistmall.api.persistence.MdOrderAfterSaleMxEntity;
import com.hsk.dentistmall.api.persistence.MdSupplier;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2019/12/26 16:38
 * 售后
 */
public interface IMdOrderAfterSaleApiDao {
    MdOrderAfterSaleEntity saveMdOrderSaleAfter(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKDBException;

    Integer saveMdOrderSaleAfterMx(MdOrderAfterSaleMxEntity mdOrderAfterSaleMxEntity) throws HSKDBException;

    MdOrderAfterSaleEntity getMdOrderSaleAfterByMasId(Integer suiId, Integer masId) throws HSKDBException;

    MdOrderAfterSaleEntity getMdOrderSaleAfter(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKDBException;

    PagerModel getMdOrderSaleAfterPageModel(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKDBException;

    List<MdOrderAfterSaleEntity> getMdOrderSaleAfterList(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKDBException;

    List<MdOrderAfterSaleEntity> getMdOrderSaleAfterListById(Integer suiId, Integer moiId) throws HSKDBException;

    List<Map<String, Object>> getMdOrderSaleAfterMxList(Integer masId, String searchName, Integer limit, Integer page) throws HSKDBException;

    List<MdOrderAfterSaleMxEntity> getMdOrderSaleASMxList(Integer masId) throws HSKDBException;

    void updateMdOrderSaleAfterSale(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKDBException;

    void updateMdOrderSaleAfterSaleMx(MdOrderAfterSaleMxEntity mdOrderAfterSaleMxEntity) throws HSKDBException;

    List<Map<String, Object>>  getMdOrderSaleAfterSupplier(Integer suiId, Integer masId) throws HSKDBException;

    MdOrderAfterSaleEntity saveApplyASAddress(Integer sui_id, Integer masId, String expressName, String expressCode) throws HSKDBException;

    void deleteAfterSale(Integer sui_id, Integer masId) throws HSKDBException;

    void deleteAfterSaleMx(Integer masId) throws HSKDBException;

    PagerModel getMdOrderSaleAfterPageModelByMoiId(Integer suiId, Integer moiId, Integer afterSale) throws HSKDBException;

    List<Map<String, Object>> getMdOrderSaleAfterMxListCountByMasId(Integer masId, String searchName) throws HSKDBException;

    Integer countOrderInfoByAfterSaleState(Integer suiId, Integer moiId, String afterSaleState) throws HSKDBException;

    /**
     * 售后记录
     */
    void saveMdOrderSaleAfterEx(MdOrderAfterSaleExtendEntity mdOrderAfterSaleExtendEntity) throws HSKDBException;

    PagerModel getMdOrderSaleAfterExPageModel(Integer masId) throws HSKDBException;
    
    //导出售后订单
    List<Map<String, Object>> exportOrderAfterMx(Integer masId) throws HSKDBException; 
    //导出售后订单2
    List<Map<String, Object>> exportOrderAfterMx2(Integer moiId) throws HSKDBException;

    void deleteAfterSaleMxAll(Integer masId) throws HSKDBException;

    List<MdOrderAfterSaleMxEntity> getMdOrderSaleASMxListByMomIds(String newMomIds) throws HSKDBException;

    List<MdOrderAfterSaleEntity> getMdOrderSaleAfterMoiId(Integer sui_id, Integer moiId) throws HSKDBException;

    PagerModel getMdOrderSaleAfterPageModelBySearch(Integer suiId, Integer moiId, String searchAsState, String searchName, String placeOrderTime, String searchAsType) throws HSKDBException;

    List<Map<String, Object>> getMdOrderSaleAfterMxListBySearch(Integer suiId, Integer moiId, String searchAsState, String searchName, Integer limit, Integer page) throws HSKDBException;

    List<Map<String, Object>> getMdOrderSaleAfterMxList(String masIds, String searchName) throws HSKDBException;
	//更具moiid查询售后明细数目
    Integer getMdOrderSaleASMXCount(Integer suiId, Integer moiId, Integer afterSale) throws HSKDBException;

    Integer getMdOrderSaleAfterMxCountByMasId(Integer masId) throws HSKDBException;

    Double getMdOrderSaleAfterMxMoneyByMasId(Integer masId) throws HSKDBException;

    PagerModel getMdOrderSaleAfterPageModelBySearch1(Integer suiId, Integer moiId, String masCode, String moCode, String serchaName) throws HSKDBException;

    List<Map<String, Object>> getMdOrderSaleAfterMxList1(Integer masId, Integer moiId, String masCode, String moCode, String serchaName, Integer limit, Integer page) throws HSKDBException;

    List<Map<String, Object>> getMdOrderSaleAfterMxListCountByMasId1(Integer masId, Integer moiId, String masCode, String moCode, String serchaName) throws HSKDBException;

    List<Map<String, Object>> getMdOrderSaleAfterMxListSuccess(MdOrderAfterSaleEntity afterSaleEntity, Object o, Integer limit, Integer page) throws HSKDBException;

    Integer getMdOrderSaleASMXSuccessCount(MdOrderAfterSaleEntity afterSaleEntity) throws HSKDBException;
}
