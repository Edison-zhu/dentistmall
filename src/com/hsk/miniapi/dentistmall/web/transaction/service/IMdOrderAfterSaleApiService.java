package com.hsk.miniapi.dentistmall.web.transaction.service;

import com.hsk.dentistmall.api.persistence.MdOrderAfterSaleEntity;
import com.hsk.dentistmall.api.persistence.MdSupplier;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUserInfo;

import java.util.List;

/**
 * author: yangfeng
 * time: 2019/12/26 16:38
 * 售后
 */
public interface IMdOrderAfterSaleApiService {
    SysRetrunMessage saveMdOrderSaleAfter(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKException;

    SysRetrunMessage getMdOrderSaleAfterOnlyByMasId(Integer masId) throws HSKException;

    MdOrderAfterSaleEntity getMdIrderSaleAfterObjectByMasId(Integer masId) throws HSKException;

    SysRetrunMessage getMdOrderSaleAfterByMasId(Integer masId, SysUserInfo account) throws HSKException;

    PagerModel getMdOrderSaleAfterPageModel(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKException;

    PagerModel getMdOrderSaleAfterList(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKException;

    PagerModel getMdOrderSaleAfterMxList(Integer masId) throws HSKException;

    PagerModel getMdOrderSaleAfterMxByMasId(Integer masCode, Integer limit, Integer page) throws HSKException;

    SysRetrunMessage updateMdOrderSaleAfterState(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKException;

    SysRetrunMessage updateMdOrderByAfterState(Integer moiId) throws HSKException;

    MdSupplier getMdOrderSaleAfterSupplier(Integer masId, SysUserInfo account) throws HSKException;

    PagerModel getOrderAfterSalePager(Integer moiId, Integer afterSale) throws HSKException;

    PagerModel getOrderAfterSaleMxModelByMasId(Integer moiId, String searchName, Integer limit, Integer page) throws HSKException;

    SysRetrunMessage countOrderAfterSale(Integer moiId) throws HSKException;
    //导出售后商品
     SysRetrunMessage exportLierAsmx(Integer masId) throws HSKException;

    PagerModel findOrderAfterSaleBySearch(Integer moiId, String searchAsState, String processStatus2, String searchName, String placeOrderTime, String searchAsType, Integer limit, Integer page) throws HSKException;

    Integer getMdOrderSaleAfterMxCountByMasId(Integer masId) throws HSKException;

    Double getMdOrderSaleAfterMxMoneyByMasId(Integer masId) throws HSKException;
}
