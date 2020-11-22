package com.hsk.dentistmall.api.daobbase;

import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.exception.HSKDBException;
import org.hsqldb.HsqlException;

import java.util.List;
import java.util.Map;

public interface IHomePageSupplierDao {
    //今日下单数量
    public Integer CountOrers(Integer oldId) throws HSKDBException;
    //今日销售额
    public Double placeOrderMoneys(Integer oldId) throws HSKDBException;
    //昨日销售额
    public Double RetreatPlaceOrderMoney(Integer oldId) throws HSKDBException;
    //本月销售额
    public Double RetreatPlaceOrderMoney2(Integer oldId) throws HSKDBException;
    //本月退款额
    public Double RetreatPlaceOrderMoney3(Integer oldId) throws HSKDBException;
    //待办事项
    public Integer countOrderInfoByProcessStateTwo(Integer PurchaseId,String processState, MdOrderInfo att_MdOrderInfo,Integer afterSale) throws HSKDBException;

    //增加交易实时战报中的今日销售额
    public Double TransactionCountMoney(Integer OldId) throws HSKDBException;
    //增加交易实时战报中的今日订单
    public Integer TransactionCount(Integer OldId) throws HSKDBException;
    //增加交易实战是战报中的今日下单百分比
    public Integer PercentageCount(Integer OldId) throws HSKDBException;
    //交易实时战报
    public List<Map<String, Object>> getOrderMxListByTransaction(Integer limit, Integer page,Integer oldId) throws HSKDBException;
    //商品排行榜 top10
    public List<Map<String, Object>> getOrderMxListmaterielTop(Integer limit, Integer page,Integer oldId) throws HSKDBException;
    //商品排行榜中收藏数据
    public List<Map<String, String>> getOrderMxListmaterielTopFavorites(String wmsiId) throws HSKDBException;
    //商品总览
    public Integer materielState(Integer state,Integer oldId) throws HSKDBException;
    //商品总览
    public Integer allYg(Integer state,Integer oldId) throws HSKDBException;
    //交易收支中的数据
    public Double branchleft1(Integer oldId) throws HSKDBException;
    public Double branchleft2(Integer oldId) throws HSKDBException;
    //交易收支中List数据
    public List<Map<String, Object>> getBranchMxList(String dateInput1,String dateInput2,Integer selectGuanjian,String inputGuanjian,Integer zhiFu,Integer state,String jinE1,String jinE2,Integer ziJin,Integer limit,Integer page,Integer oldId) throws HSKDBException;
    public List<Map<String, Object>> getBranchCount(Integer oldId,Integer settlement,Integer asState) throws HSKDBException;

    //增加财务工作台数据
    public List<Map<String, Object>> getBranchWorkList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1, String moneyFw1, String moneyFw2, String gongYingS,String caiGou,Integer selectValue,Integer moiId, Integer limit, Integer page,Integer isState) throws HSKDBException;
    public Double getFinaceLeftRight(Integer state,Integer value) throws HSKDBException;
    public Double getFinaceLeftRight1(Integer state,Integer value) throws HSKDBException;
    //各个供应商条形图
    public List<Map<String, Object>> getDateMxlistString(String dateInput3,String dateInput4,Integer settlement,String applicantName) throws HSKDBException;
    //增加判断型号编码是否可操作1 入库的数据
    public Integer getMatCodeReadOnly1(Integer wmsMiId,Integer suiId) throws HSKDBException;
    //增加判断型号编码是否可操作1 出库的数据
    public Integer getMatCodeReadOnly2(Integer wmsMiId,Integer suiId) throws HSKDBException;
    //库存预警中sql
    public List<Map<String, Integer>> getSafetyNewId(String input1,String input2,Integer rbaId,Integer rbsId,Integer rbbId,String PurchaseType,Integer isNotState) throws HSKDBException;
    //导出业务员中列名的次数用于向下合并
    public Integer saleManCount(String saleMan) throws HSKDBException;
    //导出业务员中列名的次数用于向下合并2
    public Integer saleManCount2(Integer saleMan) throws HSKDBException;

    //汇总三条数据

    public Integer totalAll() throws HSKDBException;
    public Double totalMoney1() throws HSKDBException;
    public Double totalMoney2() throws HSKDBException;
}
