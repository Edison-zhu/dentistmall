package com.hsk.dentistmall.web.transaction.service;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//财务工作台
public interface IFinancialWorkbenchService {
    //列表数据
    public PagerModel getFinanciaBranchMxList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1, String moneyFw1, String moneyFw2, String gongYingS, String caiGou,Integer selectValue,Integer moiId, Integer limit, Integer page) throws HSKException;
    //批量结算方法
    public SysRetrunMessage savePlJieSuan(String checkStr,String settlementMoney) throws HSKException;
    //首页待结算金额与已结算金额数据
    public SysRetrunMessage getFinanceLeftRight(Integer value) throws HSKException;
    //统计汇总
    public SysRetrunMessage getTotalMoney() throws HSKException;
    public PagerModel getDateMxlistString(String dateInput3,String dateInput4) throws HSKException;
    //供应商交易分析2
//    public PagerModel getDateMxlistString2(String dateInput3,String dateInput4) throws HSKException;


    public SysRetrunMessage exportFinanciaBranchMxList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1, String moneyFw1, String moneyFw2, String gongYingS, String caiGou,Integer selectValue,Integer moiId)throws HSKException;

    public SysRetrunMessage exportapplicName(String dateInput3, String dateInput4)throws HSKException;

//    public SysRetrunMessage exportPoi(String dateInput3)throws IOException;
}