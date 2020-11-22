package com.hsk.xframe.web.modifyprice.service;

import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.IDSTService;

import java.util.ArrayList;

public interface IModifypriceService extends IDSTService {
        //查询订单入库 物料入库列表
        public PagerModel getWarehousingList(String warehousCode, Integer select1, String remarks, String billCode, String operationDate,Integer desc, Integer limit, Integer page,String wewId1) throws HSKException;
        //删除入库
        public SysRetrunMessage deleteWare(Integer wewId) throws HSKException;
        SysRetrunMessage deleteWareMx(Integer wewMxId) throws HSKException;

        //弹出订单入库数据
        public PagerModel getPagerModelEnterOrder(String gjz,String cgDate,String cgRen,Integer limit, Integer page) throws HSKException;
        //新增入库数据
        public PagerModel getAddgetWarehousingMx(Integer moiId,Integer state1,Integer limit,Integer page) throws HSKException;

        //保存入库
        public SysRetrunMessage saveWarehousing(Integer moiIds,String warehousCode,String orderWarehous,String remarks,String billCode,Double number2s,Double lsjg) throws HSKException;
        //保存入库明细
        public SysRetrunMessage saveWarehousingMx(Integer m1,Integer wmsMiId,Integer mmfId,Integer mmtId,String matCode,String matName,String brand,String mmfName,String mmtCode,String basicUnit,Double matNumber,Double number2,Double rksl,String productName,Double unitMoney,Double lsj,String ph,String yxq,String backPrinting,Integer wewId2) throws Exception;
       //保存入库明细
//        public SysRetrunMessage saveWarehousingMx(String mmtCode,String basicUnit,Double matNumber,Double number2,Double rksl,String productName,Double unitMoney,Double lsj,String ph,String yxq,String backPrinting,Integer wewId2)throws HSKException;
        //改变状态
        public SysRetrunMessage saveChangeState(Integer StateMomId)throws HSKException;


        public SysRetrunMessage saveWarehousingMx1(String wewId2,String yxq) throws Exception;

}

