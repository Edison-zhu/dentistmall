package com.hsk.dentistmall.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Formula;

/**
 * 根据md_order_info表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdOrderInfo</B>
 * <hr/>
 * ===============数据库表字段属性==========
 *  <table>
 * <tr><th>字段名称:</th><td>订单信息id(moiId)</td><th>字段类型:</th><td>Integer(主键)</td></tr>
 * <tr><th>字段名称:</th><td>供应商id(wzId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>集团公司id(rbaId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>牙医医院id(rbsId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>牙医门诊id(rbbId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>采购单位类型(purchaseType)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>订单编号(orderCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>下单时间(placeOrderTime)</td><th>字段类型:</th><td>Date(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>下单金额(placeOrderMoney)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>订单实际金额(actualMoney)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>商品数量(commodityNumber)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>订单状态(orderState)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>流程状态(1打开,2结束,3操作)(processStatus)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>采购商名称(purchaseUnit)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>采购人帐号(purchaseAccount)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>所在区域(location)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>配送地址(deliveryAddress)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>邮编(zipCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>收件人(addressee)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>收件联系电话(addresseeTelephone)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>收件人手机(addresseeMobile)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>预计到货天数(daysArrival)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>价格1(money1)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>价格1(money2)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>价格1(money3)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>价格1(money4)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>价格1(money5)</td><th>字段类型:</th><td>Double(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>数量1(number1)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>数量1(number2)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>数量1(number3)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>数量1(number4)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>数量1(number5)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>法人手机号码(phoneNumber)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>法人姓名(fullName)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>法人邮箱(mailbox)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>企业住所地(corporateDomicile)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>企业类型(enterpriseType)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>经营范围(scopeBusiness)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>证照号码(licenseNumber)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>收货状态(receivingStatus)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr>
 * </table>
 * ===============无关字段属性===============
 *  <table>
 * 	<tr><th>字段名称:</th><td>订单信息id(moiId)	</td><th>属性名称:</th><td>moiId</td></tr>
 * 	<tr><th>字段名称:</th><td>供应商id(wzId)	</td><th>属性名称:</th><td>wzId</td></tr>
 * 	<tr><th>字段名称:</th><td>集团公司id(rbaId)	</td><th>属性名称:</th><td>rbaId</td></tr>
 * 	<tr><th>字段名称:</th><td>牙医医院id(rbsId)	</td><th>属性名称:</th><td>rbsId</td></tr>
 * 	<tr><th>字段名称:</th><td>牙医门诊id(rbbId)	</td><th>属性名称:</th><td>rbbId</td></tr>
 * 	<tr><th>字段名称:</th><td>下单时间(placeOrderTime)	</td><th>属性名称:</th><td>placeOrderTime</td></tr>
 * 	<tr><th>字段名称:</th><td>下单时间(placeOrderTime)	</td><th>属性名称:</th><td>placeOrderTime</td></tr>
 * 	<tr><th>字段名称:</th><td>商品数量(commodityNumber)	</td><th>属性名称:</th><td>commodityNumber</td></tr>
 * 	<tr><th>字段名称:</th><td>数量1(number1)	</td><th>属性名称:</th><td>number1</td></tr>
 * 	<tr><th>字段名称:</th><td>数量1(number2)	</td><th>属性名称:</th><td>number2</td></tr>
 * 	<tr><th>字段名称:</th><td>数量1(number3)	</td><th>属性名称:</th><td>number3</td></tr>
 * 	<tr><th>字段名称:</th><td>数量1(number4)	</td><th>属性名称:</th><td>number4</td></tr>
 * 	<tr><th>字段名称:</th><td>数量1(number5)	</td><th>属性名称:</th><td>number5</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 *
 * @author 作者:admin
 * @version v1.0 创建时间: 2017-09-28 17:16:59
 */


@SuppressWarnings("serial")
@Entity
@Table(name = "md_order_info")
public class MdOrderInfo {
///===============数据库表字段属性begin==========
    /**
     * 订单信息id(moiId):字段类型为Integer
     */
    private Integer moiId;

    /**
     * 供应商id(wzId):字段类型为Integer
     */
    private Integer wzId;

    /**
     * 集团公司id(rbaId):字段类型为Integer
     */
    private Integer rbaId;

    /**
     * 牙医医院id(rbsId):字段类型为Integer
     */
    private Integer rbsId;

    /**
     * 牙医门诊id(rbbId):字段类型为Integer
     */
    private Integer rbbId;
    /**
     * 采购人ID
     */
    private Integer purchaseId;

    /**
     * 采购单位类型(purchaseType):字段类型为String
     */
    private String purchaseType;

    /**
     * 订单编号(orderCode):字段类型为String
     */
    private String orderCode;

    /**
     * 下单时间(placeOrderTime):字段类型为Date
     */
    private Date placeOrderTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 下单金额(placeOrderMoney):字段类型为Double
     */
    private Double placeOrderMoney;

    /**
     * 订单实际金额(actualMoney):字段类型为Double
     */
    private Double actualMoney;

    /**
     * 商品数量(commodityNumber):字段类型为Integer
     */
    private Integer commodityNumber;

    /**
     * 订单状态(orderState):字段类型为String
     */
    private String orderState;

    /**
     * 流程状态(1打开,2结束,3操作)(processStatus):字段类型为String
     */
    private String processStatus;

    /**
     * 采购商名称(purchaseUnit):字段类型为String
     */
    private String purchaseUnit;

    /**
     * 采购人帐号(purchaseAccount):字段类型为String
     */
    private String purchaseAccount;

    /**
     * 所在区域(location):字段类型为String
     */
    private String location;
    /**
     * 省(province):字段类型为String
     */
    private String province;

    /**
     * 市(city):字段类型为String
     */
    private String city;

    /**
     * 地区(area):字段类型为String
     */
    private String area;
    /**
     * 配送地址(deliveryAddress):字段类型为String
     */
    private String deliveryAddress;
    /**
     * 是否需要发票
     */
    private String needBill;
    /**
     * 发票类型
     */
    private String billType;
    /**
     * 发票抬头
     */
    private String billHeard;
    /**
     * 纳税人识别号
     */
    private String billCode;
    /**
     * 快递公司
     */
    private String expressName;
    /**
     * 快递单号
     */
    private String expressCode;

    private String expressDate;
    /**
     * 邮编(zipCode):字段类型为String
     */
    private String zipCode;

    /**
     * 收件人(addressee):字段类型为String
     */
    private String addressee;

    /**
     * 收件联系电话(addresseeTelephone):字段类型为String
     */
    private String addresseeTelephone;

    /**
     * 收件人手机(addresseeMobile):字段类型为String
     */
    private String addresseeMobile;

    /**
     * 预计到货天数(daysArrival):字段类型为Double
     */
    private Double daysArrival;

    /**
     * 价格1(money1):字段类型为Double
     */
    private Double money1;

    /**
     * 价格1(money2):字段类型为Double
     */
    private Double money2;

    /**
     * 价格1(money3):字段类型为Double
     */
    private Double money3;

    /**
     * 价格1(money4):字段类型为Double
     */
    private Double money4;

    /**
     * 价格1(money5):字段类型为Double
     */
    private Double money5;

    /**
     * 数量1(number1):字段类型为Integer
     */
    private Double number1;

    /**
     * 数量1(number2):字段类型为Integer
     */
    private Double number2;

    /**
     * 数量1(number3):字段类型为Integer
     */
    private Double number3;

    /**
     * 数量1(number4):字段类型为Integer
     */
    private Double number4;

    /**
     * 数量1(number5):字段类型为Integer
     */
    private Double number5;

    /**
     * 法人手机号码(phoneNumber):字段类型为String
     */
    private String phoneNumber;

    /**
     * 法人姓名(fullName):字段类型为String
     */
    private String fullName;

    /**
     * 法人邮箱(mailbox):字段类型为String
     */
    private String mailbox;

    /**
     * 企业住所地(corporateDomicile):字段类型为String
     */
    private String corporateDomicile;

    /**
     * 企业类型(enterpriseType):字段类型为String
     */
    private String enterpriseType;

    /**
     * 经营范围(scopeBusiness):字段类型为String
     */
    private String scopeBusiness;

    /**
     * 证照号码(licenseNumber):字段类型为String
     */
    private String licenseNumber;

    /**
     * 收货状态(receivingStatus):字段类型为String
     */
    private String receivingStatus;

    /**
     * 创建人(createRen):字段类型为String
     */
    private String createRen;

    /**
     * 创建时间(createDate):字段类型为Date
     */
    private Date createDate;

    /**
     * 修改人(editRen):字段类型为String
     */
    private String editRen;

    /**
     * 修改时间(editDate):字段类型为Date
     */
    private Date editDate;

    /**
     * 20200415增加 支付单号 支付时间 结算状态 结算金额等字段
     */

    private Integer settlement;

    private Double  settlementMoney;

    private String settlementLog;
    //结算时间
    private Date settlementDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "settlement_date")
    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    /**
     * 20191221 yangfeng 增加支付方式
     * 支付方式：1：支付宝；2：微信；3：月结
     * 售后：1：退货；2：退款；3：完成
     */
    private Integer payType;
    private String payTypeName;
    private Integer afterSale;
    private String afterSaleName;
    private String oldProcessStatus;

    private String expressNameAndCode;

    private String supplierPhoneNumber;
    private Integer loanState;
    private Double loanMoney;
    private String loanLog;

    /**
     * 设置订单信息id(moi_id)字段方法 (该字段是主键)
     *
     * @return 返回 <b>MdOrderInfo.MoiId</b>的值
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "moi_id")
    public Integer getMoiId() {
        return this.moiId;
    }

    /**
     * 设置 moi_id字段方法
     *
     * @param att_moiId 输入<b>MdOrderInfo.moiId</b>字段的值
     */
    public void setMoiId(Integer att_moiId) {
        this.moiId = att_moiId;
    }

    /**
     * 设置供应商id(wz_id)字段方法
     *
     * @return 返回 <b>MdOrderInfo.WzId</b>的值
     */
    @Column(name = "wz_id")
    public Integer getWzId() {
        return this.wzId;
    }

    /**
     * 设置 wz_id字段方法
     *
     * @param att_wzId 输入<b>MdOrderInfo.wzId</b>字段的值
     */
    public void setWzId(Integer att_wzId) {
        this.wzId = att_wzId;
    }

    /**
     * 设置集团公司id(rba_id)字段方法
     *
     * @return 返回 <b>MdOrderInfo.RbaId</b>的值
     */
    @Column(name = "rba_id")
    public Integer getRbaId() {
        return this.rbaId;
    }

    /**
     * 设置 rba_id字段方法
     *
     * @param att_rbaId 输入<b>MdOrderInfo.rbaId</b>字段的值
     */
    public void setRbaId(Integer att_rbaId) {
        this.rbaId = att_rbaId;
    }

    /**
     * 设置牙医医院id(rbs_id)字段方法
     *
     * @return 返回 <b>MdOrderInfo.RbsId</b>的值
     */
    @Column(name = "rbs_id")
    public Integer getRbsId() {
        return this.rbsId;
    }

    /**
     * 设置 rbs_id字段方法
     *
     * @param att_rbsId 输入<b>MdOrderInfo.rbsId</b>字段的值
     */
    public void setRbsId(Integer att_rbsId) {
        this.rbsId = att_rbsId;
    }

    /**
     * 设置牙医门诊id(rbb_id)字段方法
     *
     * @return 返回 <b>MdOrderInfo.RbbId</b>的值
     */
    @Column(name = "rbb_id")
    public Integer getRbbId() {
        return this.rbbId;
    }

    /**
     * 设置 rbb_id字段方法
     *
     * @param att_rbbId 输入<b>MdOrderInfo.rbbId</b>字段的值
     */
    public void setRbbId(Integer att_rbbId) {
        this.rbbId = att_rbbId;
    }

    @Column(name = "purchase_id")
    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**
     * 设置采购单位类型(purchase_type)字段方法
     *
     * @return 返回 <b>MdOrderInfo.PurchaseType</b>的值
     */
    @Column(name = "purchase_type")
    public String getPurchaseType() {
        return this.purchaseType;
    }

    /**
     * 设置 purchase_type字段方法
     *
     * @param att_purchaseType 输入<b>MdOrderInfo.purchaseType</b>字段的值
     */
    public void setPurchaseType(String att_purchaseType) {
        this.purchaseType = att_purchaseType;
    }

    /**
     * 设置订单编号(order_code)字段方法
     *
     * @return 返回 <b>MdOrderInfo.OrderCode</b>的值
     */
    @Column(name = "order_code")
    public String getOrderCode() {
        return this.orderCode;
    }

    /**
     * 设置 order_code字段方法
     *
     * @param att_orderCode 输入<b>MdOrderInfo.orderCode</b>字段的值
     */
    public void setOrderCode(String att_orderCode) {
        this.orderCode = att_orderCode;
    }

    /**
     * 设置下单时间(Place_order_time)字段方法
     *
     * @return 返回 <b>MdOrderInfo.PlaceOrderTime</b>的值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Place_order_time")
    public Date getPlaceOrderTime() {
        return this.placeOrderTime;
    }

    /**
     * 设置 Place_order_time字段方法
     *
     * @param att_placeOrderTime 输入<b>MdOrderInfo.placeOrderTime</b>字段的值
     */
    public void setPlaceOrderTime(Date att_placeOrderTime) {
        this.placeOrderTime = att_placeOrderTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 设置下单金额(place_order_money)字段方法
     *
     * @return 返回 <b>MdOrderInfo.PlaceOrderMoney</b>的值
     */
    @Column(name = "place_order_money")
    public Double getPlaceOrderMoney() {
        return this.placeOrderMoney;
    }

    /**
     * 设置 place_order_money字段方法
     *
     * @param att_placeOrderMoney 输入<b>MdOrderInfo.placeOrderMoney</b>字段的值
     */
    public void setPlaceOrderMoney(Double att_placeOrderMoney) {
        this.placeOrderMoney = att_placeOrderMoney;
    }

    /**
     * 设置订单实际金额(Actual_money)字段方法
     *
     * @return 返回 <b>MdOrderInfo.ActualMoney</b>的值
     */
    @Column(name = "Actual_money")
    public Double getActualMoney() {
        return this.actualMoney;
    }

    /**
     * 设置 Actual_money字段方法
     *
     * @param att_actualMoney 输入<b>MdOrderInfo.actualMoney</b>字段的值
     */
    public void setActualMoney(Double att_actualMoney) {
        this.actualMoney = att_actualMoney;
    }

    /**
     * 设置商品数量(commodity_number)字段方法
     *
     * @return 返回 <b>MdOrderInfo.CommodityNumber</b>的值
     */
    @Column(name = "commodity_number")
    public Integer getCommodityNumber() {
        return this.commodityNumber;
    }

    /**
     * 设置 commodity_number字段方法
     *
     * @param att_commodityNumber 输入<b>MdOrderInfo.commodityNumber</b>字段的值
     */
    public void setCommodityNumber(Integer att_commodityNumber) {
        this.commodityNumber = att_commodityNumber;
    }

    /**
     * 设置订单状态(order_state)字段方法
     *
     * @return 返回 <b>MdOrderInfo.OrderState</b>的值
     */
    @Column(name = "order_state")
    public String getOrderState() {
        return this.orderState;
    }

    /**
     * 设置 order_state字段方法
     *
     * @param att_orderState 输入<b>MdOrderInfo.orderState</b>字段的值
     */
    public void setOrderState(String att_orderState) {
        this.orderState = att_orderState;
    }

    /**
     * 设置流程状态(1打开,2结束,3操作)(Process_status)字段方法
     *
     * @return 返回 <b>MdOrderInfo.ProcessStatus</b>的值
     */
    @Column(name = "Process_status")
    public String getProcessStatus() {
        return this.processStatus;
    }

    /**
     * 设置 Process_status字段方法
     *
     * @param att_processStatus 输入<b>MdOrderInfo.processStatus</b>字段的值
     */
    public void setProcessStatus(String att_processStatus) {
        this.processStatus = att_processStatus;
    }

    /**
     * 设置采购商名称(Purchase_unit)字段方法
     *
     * @return 返回 <b>MdOrderInfo.PurchaseUnit</b>的值
     */
    @Column(name = "Purchase_unit")
    public String getPurchaseUnit() {
        return this.purchaseUnit;
    }

    /**
     * 设置 Purchase_unit字段方法
     *
     * @param att_purchaseUnit 输入<b>MdOrderInfo.purchaseUnit</b>字段的值
     */
    public void setPurchaseUnit(String att_purchaseUnit) {
        this.purchaseUnit = att_purchaseUnit;
    }

    /**
     * 设置采购人帐号(purchase_Account)字段方法
     *
     * @return 返回 <b>MdOrderInfo.PurchaseAccount</b>的值
     */
    @Column(name = "purchase_Account")
    public String getPurchaseAccount() {
        return this.purchaseAccount;
    }

    /**
     * 设置 purchase_Account字段方法
     *
     * @param att_purchaseAccount 输入<b>MdOrderInfo.purchaseAccount</b>字段的值
     */
    public void setPurchaseAccount(String att_purchaseAccount) {
        this.purchaseAccount = att_purchaseAccount;
    }

    /**
     * 设置所在区域(Location)字段方法
     *
     * @return 返回 <b>MdOrderInfo.Location</b>的值
     */
    @Column(name = "Location")
    public String getLocation() {
        return this.location;
    }

    /**
     * 设置 Location字段方法
     *
     * @param att_location 输入<b>MdOrderInfo.location</b>字段的值
     */
    public void setLocation(String att_location) {
        this.location = att_location;
    }

    /**
     * 设置省(province)字段方法
     *
     * @return 返回 <b>MdDentalClinic.Province</b>的值
     */
    @Column(name = "province")
    public String getProvince() {
        return this.province;
    }

    /**
     * 设置 province字段方法
     *
     * @param att_province 输入<b>MdDentalClinic.province</b>字段的值
     */
    public void setProvince(String att_province) {
        this.province = att_province;
    }

    /**
     * 设置市(city)字段方法
     *
     * @return 返回 <b>MdDentalClinic.City</b>的值
     */
    @Column(name = "city")
    public String getCity() {
        return this.city;
    }

    /**
     * 设置 city字段方法
     *
     * @param att_city 输入<b>MdDentalClinic.city</b>字段的值
     */
    public void setCity(String att_city) {
        this.city = att_city;
    }

    /**
     * 设置地区(area)字段方法
     *
     * @return 返回 <b>MdDentalClinic.Area</b>的值
     */
    @Column(name = "area")
    public String getArea() {
        return this.area;
    }

    /**
     * 设置 area字段方法
     *
     * @param att_area 输入<b>MdDentalClinic.area</b>字段的值
     */
    public void setArea(String att_area) {
        this.area = att_area;
    }

    @Column(name = "need_bill")
    public String getNeedBill() {
        return needBill;
    }

    public void setNeedBill(String needBill) {
        this.needBill = needBill;
    }

    @Column(name = "bill_type")
    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    @Column(name = "bill_heard")
    public String getBillHeard() {
        return billHeard;
    }

    public void setBillHeard(String billHeard) {
        this.billHeard = billHeard;
    }

    @Column(name = "bill_code")
    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    @Column(name = "express_name")
    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    @Column(name = "express_code")
    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    @Column(name = "settlement")
    public Integer getSettlement() {
        return settlement;
    }

    public void setSettlement(Integer settlement) {
        this.settlement = settlement;
    }
    @Column(name = "settlement_money")
    public Double getSettlementMoney() {
        return settlementMoney;
    }

    public void setSettlementMoney(Double settlementMoney) {
        this.settlementMoney = settlementMoney;
    }
    @Column(name = "settlement_log")
    public String getSettlementLog() {
        return settlementLog;
    }

    public void setSettlementLog(String settlementLog) {
        this.settlementLog = settlementLog;
    }
    /**
     * 设置配送地址(Delivery_address)字段方法
     *
     * @return 返回 <b>MdOrderInfo.DeliveryAddress</b>的值
     */
    @Column(name = "Delivery_address")
    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    /**
     * 设置 Delivery_address字段方法
     *
     * @param att_deliveryAddress 输入<b>MdOrderInfo.deliveryAddress</b>字段的值
     */
    public void setDeliveryAddress(String att_deliveryAddress) {
        this.deliveryAddress = att_deliveryAddress;
    }

    /**
     * 设置邮编(zip_code)字段方法
     *
     * @return 返回 <b>MdOrderInfo.ZipCode</b>的值
     */
    @Column(name = "zip_code")
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * 设置 zip_code字段方法
     *
     * @param att_zipCode 输入<b>MdOrderInfo.zipCode</b>字段的值
     */
    public void setZipCode(String att_zipCode) {
        this.zipCode = att_zipCode;
    }

    /**
     * 设置收件人(Addressee)字段方法
     *
     * @return 返回 <b>MdOrderInfo.Addressee</b>的值
     */
    @Column(name = "Addressee")
    public String getAddressee() {
        return this.addressee;
    }

    /**
     * 设置 Addressee字段方法
     *
     * @param att_addressee 输入<b>MdOrderInfo.addressee</b>字段的值
     */
    public void setAddressee(String att_addressee) {
        this.addressee = att_addressee;
    }

    /**
     * 设置收件联系电话(Addressee_telephone)字段方法
     *
     * @return 返回 <b>MdOrderInfo.AddresseeTelephone</b>的值
     */
    @Column(name = "Addressee_telephone")
    public String getAddresseeTelephone() {
        return this.addresseeTelephone;
    }

    /**
     * 设置 Addressee_telephone字段方法
     *
     * @param att_addresseeTelephone 输入<b>MdOrderInfo.addresseeTelephone</b>字段的值
     */
    public void setAddresseeTelephone(String att_addresseeTelephone) {
        this.addresseeTelephone = att_addresseeTelephone;
    }

    /**
     * 设置收件人手机(Addressee_Mobile)字段方法
     *
     * @return 返回 <b>MdOrderInfo.AddresseeMobile</b>的值
     */
    @Column(name = "Addressee_Mobile")
    public String getAddresseeMobile() {
        return this.addresseeMobile;
    }

    /**
     * 设置 Addressee_Mobile字段方法
     *
     * @param att_addresseeMobile 输入<b>MdOrderInfo.addresseeMobile</b>字段的值
     */
    public void setAddresseeMobile(String att_addresseeMobile) {
        this.addresseeMobile = att_addresseeMobile;
    }

    /**
     * 设置预计到货天数(Days_arrival)字段方法
     *
     * @return 返回 <b>MdOrderInfo.DaysArrival</b>的值
     */
    @Column(name = "Days_arrival")
    public Double getDaysArrival() {
        return this.daysArrival;
    }

    /**
     * 设置 Days_arrival字段方法
     *
     * @param att_daysArrival 输入<b>MdOrderInfo.daysArrival</b>字段的值
     */
    public void setDaysArrival(Double att_daysArrival) {
        this.daysArrival = att_daysArrival;
    }

    /**
     * 设置价格1(money1)字段方法
     *
     * @return 返回 <b>MdOrderInfo.Money1</b>的值
     */
    @Column(name = "money1")
    public Double getMoney1() {
        return this.money1;
    }

    /**
     * 设置 money1字段方法
     *
     * @param att_money1 输入<b>MdOrderInfo.money1</b>字段的值
     */
    public void setMoney1(Double att_money1) {
        this.money1 = att_money1;
    }

    /**
     * 设置价格1(money2)字段方法
     *
     * @return 返回 <b>MdOrderInfo.Money2</b>的值
     */
    @Column(name = "money2")
    public Double getMoney2() {
        return this.money2;
    }

    /**
     * 设置 money2字段方法
     *
     * @param att_money2 输入<b>MdOrderInfo.money2</b>字段的值
     */
    public void setMoney2(Double att_money2) {
        this.money2 = att_money2;
    }

    /**
     * 设置价格1(money3)字段方法
     *
     * @return 返回 <b>MdOrderInfo.Money3</b>的值
     */
    @Column(name = "money3")
    public Double getMoney3() {
        return this.money3;
    }

    /**
     * 设置 money3字段方法
     *
     * @param att_money3 输入<b>MdOrderInfo.money3</b>字段的值
     */
    public void setMoney3(Double att_money3) {
        this.money3 = att_money3;
    }
    

    /**
     * 设置价格1(money4)字段方法
     *
     * @return 返回 <b>MdOrderInfo.Money4</b>的值
     */
    @Column(name = "money4")
    public Double getMoney4() {
        return this.money4;
    }

    /**
     * 设置 money4字段方法
     *
     * @param att_money4 输入<b>MdOrderInfo.money4</b>字段的值
     */
    public void setMoney4(Double att_money4) {
        this.money4 = att_money4;
    }

    /**
     * 设置价格1(money5)字段方法
     *
     * @return 返回 <b>MdOrderInfo.Money5</b>的值
     */
    @Column(name = "money5")
    public Double getMoney5() {
        return this.money5;
    }

    /**
     * 设置 money5字段方法
     *
     * @param att_money5 输入<b>MdOrderInfo.money5</b>字段的值
     */
    public void setMoney5(Double att_money5) {
        this.money5 = att_money5;
    }

    /**
     * 设置数量1(number1)字段方法
     *
     * @return 返回 <b>MdOrderInfo.Number1</b>的值
     */
    @Column(name = "number1")
    public Double getNumber1() {
        return this.number1;
    }

    /**
     * 设置 number1字段方法
     *
     * @param att_number1 输入<b>MdOrderInfo.number1</b>字段的值
     */
    public void setNumber1(Double att_number1) {
        this.number1 = att_number1;
    }

    /**
     * 设置数量1(number2)字段方法
     *
     * @return 返回 <b>MdOrderInfo.Number2</b>的值
     */
    @Column(name = "number2")
    public Double getNumber2() {
        return this.number2;
    }

    /**
     * 设置 number2字段方法
     *
     * @param att_number2 输入<b>MdOrderInfo.number2</b>字段的值
     */
    public void setNumber2(Double att_number2) {
        this.number2 = att_number2;
    }

    /**
     * 设置数量1(number3)字段方法
     *
     * @return 返回 <b>MdOrderInfo.Number3</b>的值
     */
    @Column(name = "number3")
    public Double getNumber3() {
        return this.number3;
    }

    /**
     * 设置 number3字段方法
     *
     * @param att_number3 输入<b>MdOrderInfo.number3</b>字段的值
     */
    public void setNumber3(Double att_number3) {
        this.number3 = att_number3;
    }

    /**
     * 设置数量1(number4)字段方法
     *
     * @return 返回 <b>MdOrderInfo.Number4</b>的值
     */
    @Column(name = "number4")
    public Double getNumber4() {
        return this.number4;
    }

    /**
     * 设置 number4字段方法
     *
     * @param att_number4 输入<b>MdOrderInfo.number4</b>字段的值
     */
    public void setNumber4(Double att_number4) {
        this.number4 = att_number4;
    }

    /**
     * 设置数量1(number5)字段方法
     *
     * @return 返回 <b>MdOrderInfo.Number5</b>的值
     */
    @Column(name = "number5")
    public Double getNumber5() {
        return this.number5;
    }

    /**
     * 设置 number5字段方法
     *
     * @param att_number5 输入<b>MdOrderInfo.number5</b>字段的值
     */
    public void setNumber5(Double att_number5) {
        this.number5 = att_number5;
    }

    /**
     * 设置法人手机号码(Phone_number)字段方法
     *
     * @return 返回 <b>MdOrderInfo.PhoneNumber</b>的值
     */
    @Column(name = "Phone_number")
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * 设置 Phone_number字段方法
     *
     * @param att_phoneNumber 输入<b>MdOrderInfo.phoneNumber</b>字段的值
     */
    public void setPhoneNumber(String att_phoneNumber) {
        this.phoneNumber = att_phoneNumber;
    }

    /**
     * 设置法人姓名(Full_name)字段方法
     *
     * @return 返回 <b>MdOrderInfo.FullName</b>的值
     */
    @Column(name = "Full_name")
    public String getFullName() {
        return this.fullName;
    }

    /**
     * 设置 Full_name字段方法
     *
     * @param att_fullName 输入<b>MdOrderInfo.fullName</b>字段的值
     */
    public void setFullName(String att_fullName) {
        this.fullName = att_fullName;
    }

    /**
     * 设置法人邮箱(mailbox)字段方法
     *
     * @return 返回 <b>MdOrderInfo.Mailbox</b>的值
     */
    @Column(name = "mailbox")
    public String getMailbox() {
        return this.mailbox;
    }

    /**
     * 设置 mailbox字段方法
     *
     * @param att_mailbox 输入<b>MdOrderInfo.mailbox</b>字段的值
     */
    public void setMailbox(String att_mailbox) {
        this.mailbox = att_mailbox;
    }

    /**
     * 设置企业住所地(Corporate_domicile)字段方法
     *
     * @return 返回 <b>MdOrderInfo.CorporateDomicile</b>的值
     */
    @Column(name = "Corporate_domicile")
    public String getCorporateDomicile() {
        return this.corporateDomicile;
    }

    /**
     * 设置 Corporate_domicile字段方法
     *
     * @param att_corporateDomicile 输入<b>MdOrderInfo.corporateDomicile</b>字段的值
     */
    public void setCorporateDomicile(String att_corporateDomicile) {
        this.corporateDomicile = att_corporateDomicile;
    }

    /**
     * 设置企业类型(enterprise_type)字段方法
     *
     * @return 返回 <b>MdOrderInfo.EnterpriseType</b>的值
     */
    @Column(name = "enterprise_type")
    public String getEnterpriseType() {
        return this.enterpriseType;
    }

    /**
     * 设置 enterprise_type字段方法
     *
     * @param att_enterpriseType 输入<b>MdOrderInfo.enterpriseType</b>字段的值
     */
    public void setEnterpriseType(String att_enterpriseType) {
        this.enterpriseType = att_enterpriseType;
    }

    /**
     * 设置经营范围(scope_business)字段方法
     *
     * @return 返回 <b>MdOrderInfo.ScopeBusiness</b>的值
     */
    @Column(name = "scope_business")
    public String getScopeBusiness() {
        return this.scopeBusiness;
    }

    /**
     * 设置 scope_business字段方法
     *
     * @param att_scopeBusiness 输入<b>MdOrderInfo.scopeBusiness</b>字段的值
     */
    public void setScopeBusiness(String att_scopeBusiness) {
        this.scopeBusiness = att_scopeBusiness;
    }

    /**
     * 设置证照号码(License_number)字段方法
     *
     * @return 返回 <b>MdOrderInfo.LicenseNumber</b>的值
     */
    @Column(name = "License_number")
    public String getLicenseNumber() {
        return this.licenseNumber;
    }

    /**
     * 设置 License_number字段方法
     *
     * @param att_licenseNumber 输入<b>MdOrderInfo.licenseNumber</b>字段的值
     */
    public void setLicenseNumber(String att_licenseNumber) {
        this.licenseNumber = att_licenseNumber;
    }

    /**
     * 设置收货状态(Receiving_status)字段方法
     *
     * @return 返回 <b>MdOrderInfo.ReceivingStatus</b>的值
     */
    @Column(name = "Receiving_status")
    public String getReceivingStatus() {
        return this.receivingStatus;
    }

    /**
     * 设置 Receiving_status字段方法
     *
     * @param att_receivingStatus 输入<b>MdOrderInfo.receivingStatus</b>字段的值
     */
    public void setReceivingStatus(String att_receivingStatus) {
        this.receivingStatus = att_receivingStatus;
    }

    /**
     * 设置创建人(create_ren)字段方法
     *
     * @return 返回 <b>MdOrderInfo.CreateRen</b>的值
     */
    @Column(name = "create_ren")
    public String getCreateRen() {
        return this.createRen;
    }

    /**
     * 设置 create_ren字段方法
     *
     * @param att_createRen 输入<b>MdOrderInfo.createRen</b>字段的值
     */
    public void setCreateRen(String att_createRen) {
        this.createRen = att_createRen;
    }

    /**
     * 设置创建时间(create_date)字段方法
     *
     * @return 返回 <b>MdOrderInfo.CreateDate</b>的值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置 create_date字段方法
     *
     * @param att_createDate 输入<b>MdOrderInfo.createDate</b>字段的值
     */
    public void setCreateDate(Date att_createDate) {
        this.createDate = att_createDate;
    }

    /**
     * 设置修改人(edit_ren)字段方法
     *
     * @return 返回 <b>MdOrderInfo.EditRen</b>的值
     */
    @Column(name = "edit_ren")
    public String getEditRen() {
        return this.editRen;
    }

    /**
     * 设置 edit_ren字段方法
     *
     * @param att_editRen 输入<b>MdOrderInfo.editRen</b>字段的值
     */
    public void setEditRen(String att_editRen) {
        this.editRen = att_editRen;
    }

    /**
     * 设置修改时间(edit_date)字段方法
     *
     * @return 返回 <b>MdOrderInfo.EditDate</b>的值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "edit_date")
    public Date getEditDate() {
        return this.editDate;
    }

    /**
     * 设置 edit_date字段方法
     *
     * @param att_editDate 输入<b>MdOrderInfo.editDate</b>字段的值
     */
    public void setEditDate(Date att_editDate) {
        this.editDate = att_editDate;
    }

    @Column(name = "pay_type")
    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    @Column(name = "after_sale")
    public Integer getAfterSale() {
        return afterSale;
    }

    public void setAfterSale(Integer afterSale) {
        this.afterSale = afterSale;
    }

    @Formula("(SELECT a.PARAM_NAME FROM sys_parameter a, sys_parameter b where a.SYS_SPAR_ID = '131' and a.PARAM_VALUE = after_sale and a.SYS_SPAR_ID = b.spar_id and b.param_code = 'PAR191224031732706')")
    public String getAfterSaleName() {
        return afterSaleName;
    }

    public void setAfterSaleName(String afterSaleName) {
        this.afterSaleName = afterSaleName;
    }

    /**
     * 通过param_code查找param_name，获取到支付方式
     * @return
     */
    @Formula("(SELECT a.PARAM_NAME FROM sys_parameter a, sys_parameter b where a.SYS_SPAR_ID = '127' and a.PARAM_VALUE = pay_type and a.SYS_SPAR_ID = b.spar_id and b.param_code = 'PAR191222092351998')")
    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    @Column(name = "old_process_status")
    public String getOldProcessStatus() {
        return oldProcessStatus;
    }

    public void setOldProcessStatus(String oldProcessStatus) {
        this.oldProcessStatus = oldProcessStatus;
    }

    ///===============数据库表字段属性end==========

///===============数据库表无关子段字段属性begin==========
    /**
     * 订单信息id(moi_id):字段类型为String
     */
    private String moiId_str;
    /**
     * 供应商id(wz_id):字段类型为String
     */
    private String wzId_str;
    /**
     * 集团公司id(rba_id):字段类型为String
     */
    private String rbaId_str;
    /**
     * 牙医医院id(rbs_id):字段类型为String
     */
    private String rbsId_str;
    /**
     * 牙医门诊id(rbb_id):字段类型为String
     */
    private String rbbId_str;
    /**
     * 下单时间(Place_order_time):字段类型为Date
     */
    private Date placeOrderTime_start;
    /**
     * 下单时间(Place_order_time):字段类型为Date
     */
    private Date placeOrderTime_end;
    /**
     * 商品数量(commodity_number):字段类型为String
     */
    private String commodityNumber_str;
    /**
     * 数量1(number1):字段类型为String
     */
    private String number1_str;
    /**
     * 数量1(number2):字段类型为String
     */
    private String number2_str;
    /**
     * 数量1(number3):字段类型为String
     */
    private String number3_str;
    /**
     * 数量1(number4):字段类型为String
     */
    private String number4_str;
    /**
     * 数量1(number5):字段类型为String
     */
    private String number5_str;
    /**
     * 创建时间(create_date):字段类型为Date
     */
    private Date createDate_start;
    /**
     * 创建时间(create_date):字段类型为Date
     */
    private Date createDate_end;
    /**
     * 修改时间(edit_date):字段类型为Date
     */
    private Date editDate_start;
    /**
     * 修改时间(edit_date):字段类型为Date
     */
    private Date editDate_end;
    /**
     * 支付方式 字段方法
     */
    
    private String pay_type_str;
	/**
     * ():字段类型为String
     * md_order_info表里不用like作为条件的字符串
     */
    private String Tab_like;
    /**
     * ():字段类型为String
     * md_order_info表里order by 字符串
     */
    private String Tab_order;

    private Double expressMoney;
    
    private Double experssSupper;
    @Formula("(SELECT a.no_express_money FROM md_supplier a WHERE a.wz_id = wz_id)")
    public Double getExperssSupper() {
		return experssSupper;
	}

	public void setExperssSupper(Double experssSupper) {
		this.experssSupper = experssSupper;
	}

	@Formula("(SELECT a.express_money FROM md_supplier a WHERE a.wz_id = wz_id)")
    public Double getExpressMoney() {
        return expressMoney;
    }

    public void setExpressMoney(Double expressMoney) {
        this.expressMoney = expressMoney;
    }
    

    /**
     * 设置moiId字段方法
     *
     * @return 返回 <b>MdOrderInfo.moiId</b>的值
     */
    @Transient
    public String getMoiId_str() {
        return this.moiId_str;
    }

    /**
     * 设置 moi_id字段方法
     *
     * @param att_moiId 输入<b>MdOrderInfo.moiId</b>字段的值
     */
    public void setMoiId_str(String att_moiId_str) {
        this.moiId_str = att_moiId_str;
    }

    /**
     * 设置wzId字段方法
     *
     * @return 返回 <b>MdOrderInfo.wzId</b>的值
     */
    @Transient
    public String getWzId_str() {
        return this.wzId_str;
    }

    /**
     * 设置 wz_id字段方法
     *
     * @param att_wzId 输入<b>MdOrderInfo.wzId</b>字段的值
     */
    public void setWzId_str(String att_wzId_str) {
        this.wzId_str = att_wzId_str;
    }

    /**
     * 设置rbaId字段方法
     *
     * @return 返回 <b>MdOrderInfo.rbaId</b>的值
     */
    @Transient
    public String getRbaId_str() {
        return this.rbaId_str;
    }

    /**
     * 设置 rba_id字段方法
     *
     * @param att_rbaId 输入<b>MdOrderInfo.rbaId</b>字段的值
     */
    public void setRbaId_str(String att_rbaId_str) {
        this.rbaId_str = att_rbaId_str;
    }

    /**
     * 设置rbsId字段方法
     *
     * @return 返回 <b>MdOrderInfo.rbsId</b>的值
     */
    @Transient
    public String getRbsId_str() {
        return this.rbsId_str;
    }

    /**
     * 设置 rbs_id字段方法
     *
     * @param att_rbsId 输入<b>MdOrderInfo.rbsId</b>字段的值
     */
    public void setRbsId_str(String att_rbsId_str) {
        this.rbsId_str = att_rbsId_str;
    }

    /**
     * 设置rbbId字段方法
     *
     * @return 返回 <b>MdOrderInfo.rbbId</b>的值
     */
    @Transient
    public String getRbbId_str() {
        return this.rbbId_str;
    }

    /**
     * 设置 rbb_id字段方法
     *
     * @param att_rbbId 输入<b>MdOrderInfo.rbbId</b>字段的值
     */
    public void setRbbId_str(String att_rbbId_str) {
        this.rbbId_str = att_rbbId_str;
    }

    /**
     * 设置placeOrderTime字段方法
     *
     * @return 返回 <b>MdOrderInfo.placeOrderTime</b>的值
     */
    @Transient
    public Date getPlaceOrderTime_start() {
        return this.placeOrderTime_start;
    }

    /**
     * 设置 Place_order_time字段方法
     *
     * @param att_placeOrderTime 输入<b>MdOrderInfo.placeOrderTime</b>字段的值
     */
    public void setPlaceOrderTime_start(Date att_placeOrderTime_start) {
        this.placeOrderTime_start = att_placeOrderTime_start;
    }

    /**
     * 设置placeOrderTime字段方法
     *
     * @return 返回 <b>MdOrderInfo.placeOrderTime</b>的值
     */
    @Transient
    public Date getPlaceOrderTime_end() {
        return this.placeOrderTime_end;
    }

    /**
     * 设置 Place_order_time字段方法
     *
     * @param att_placeOrderTime 输入<b>MdOrderInfo.placeOrderTime</b>字段的值
     */
    public void setPlaceOrderTime_end(Date att_placeOrderTime_end) {
        this.placeOrderTime_end = att_placeOrderTime_end;
    }

    /**
     * 设置commodityNumber字段方法
     *
     * @return 返回 <b>MdOrderInfo.commodityNumber</b>的值
     */
    @Transient
    public String getCommodityNumber_str() {
        return this.commodityNumber_str;
    }

    /**
     * 设置 commodity_number字段方法
     *
     * @param att_commodityNumber 输入<b>MdOrderInfo.commodityNumber</b>字段的值
     */
    public void setCommodityNumber_str(String att_commodityNumber_str) {
        this.commodityNumber_str = att_commodityNumber_str;
    }

    /**
     * 设置number1字段方法
     *
     * @return 返回 <b>MdOrderInfo.number1</b>的值
     */
    @Transient
    public String getNumber1_str() {
        return this.number1_str;
    }

    /**
     * 设置 number1字段方法
     *
     * @param att_number1 输入<b>MdOrderInfo.number1</b>字段的值
     */
    public void setNumber1_str(String att_number1_str) {
        this.number1_str = att_number1_str;
    }

    /**
     * 设置number2字段方法
     *
     * @return 返回 <b>MdOrderInfo.number2</b>的值
     */
    @Transient
    public String getNumber2_str() {
        return this.number2_str;
    }

    /**
     * 设置 number2字段方法
     *
     * @param att_number2 输入<b>MdOrderInfo.number2</b>字段的值
     */
    public void setNumber2_str(String att_number2_str) {
        this.number2_str = att_number2_str;
    }

    /**
     * 设置number3字段方法
     *
     * @return 返回 <b>MdOrderInfo.number3</b>的值
     */
    @Transient
    public String getNumber3_str() {
        return this.number3_str;
    }

    /**
     * 设置 number3字段方法
     *
     * @param att_number3 输入<b>MdOrderInfo.number3</b>字段的值
     */
    public void setNumber3_str(String att_number3_str) {
        this.number3_str = att_number3_str;
    }

    /**
     * 设置number4字段方法
     *
     * @return 返回 <b>MdOrderInfo.number4</b>的值
     */
    @Transient
    public String getNumber4_str() {
        return this.number4_str;
    }

    /**
     * 设置 number4字段方法
     *
     * @param att_number4 输入<b>MdOrderInfo.number4</b>字段的值
     */
    public void setNumber4_str(String att_number4_str) {
        this.number4_str = att_number4_str;
    }

    /**
     * 设置number5字段方法
     *
     * @return 返回 <b>MdOrderInfo.number5</b>的值
     */
    @Transient
    public String getNumber5_str() {
        return this.number5_str;
    }

    /**
     * 设置 number5字段方法
     *
     * @param att_number5 输入<b>MdOrderInfo.number5</b>字段的值
     */
    public void setNumber5_str(String att_number5_str) {
        this.number5_str = att_number5_str;
    }

    /**
     * 设置createDate字段方法
     *
     * @return 返回 <b>MdOrderInfo.createDate</b>的值
     */
    @Transient
    public Date getCreateDate_start() {
        return this.createDate_start;
    }

    /**
     * 设置 create_date字段方法
     *
     * @param att_createDate 输入<b>MdOrderInfo.createDate</b>字段的值
     */
    public void setCreateDate_start(Date att_createDate_start) {
        this.createDate_start = att_createDate_start;
    }

    /**
     * 设置createDate字段方法
     *
     * @return 返回 <b>MdOrderInfo.createDate</b>的值
     */
    @Transient
    public Date getCreateDate_end() {
        return this.createDate_end;
    }

    /**
     * 设置 create_date字段方法
     *
     * @param att_createDate 输入<b>MdOrderInfo.createDate</b>字段的值
     */
    public void setCreateDate_end(Date att_createDate_end) {
        this.createDate_end = att_createDate_end;
    }

    /**
     * 设置editDate字段方法
     *
     * @return 返回 <b>MdOrderInfo.editDate</b>的值
     */
    @Transient
    public Date getEditDate_start() {
        return this.editDate_start;
    }

    /**
     * 设置 edit_date字段方法
     *
     * @param att_editDate 输入<b>MdOrderInfo.editDate</b>字段的值
     */
    public void setEditDate_start(Date att_editDate_start) {
        this.editDate_start = att_editDate_start;
    }

    /**
     * 设置editDate字段方法
     *
     * @return 返回 <b>MdOrderInfo.editDate</b>的值
     */
    @Transient
    public Date getEditDate_end() {
        return this.editDate_end;
    }

    /**
     * 设置 edit_date字段方法
     *
     * @param att_editDate 输入<b>MdOrderInfo.editDate</b>字段的值
     */
    public void setEditDate_end(Date att_editDate_end) {
        this.editDate_end = att_editDate_end;
    }
    /**
     * 设置支付方式字段
     */
    @Transient
	public String getPay_type_str() {
		return pay_type_str;
	}

	public void setPay_type_str(String pay_type_str) {
		this.pay_type_str = pay_type_str;
	}

    /**
     * 设置tab_like字段方法
     *
     * @return 返回 <b>MdOrderInfo.tab_like</b>的值
     */
    @Transient
    public String getTab_like() {
        return this.Tab_like;
    }

    /**
     * 设置 字段方法
     *
     * @param att_tab_like 输入<b>MdOrderInfo.tab_like</b>字段的值
     */
    public void setTab_like(String att_Tab_like) {
        this.Tab_like = att_Tab_like;
    }

    /**
     * 设置tab_order字段方法
     *
     * @return 返回 <b>MdOrderInfo.tab_order</b>的值
     */
    @Transient
    public String getTab_order() {
        return this.Tab_order;
    }

    /**
     * 设置 字段方法
     *
     * @param att_tab_order 输入<b>MdOrderInfo.tab_order</b>字段的值
     */
    public void setTab_order(String att_Tab_order) {
        this.Tab_order = att_Tab_order;
    }


    ///===============数据库表无关子段字段属性end==========
    private String applicantName;
    private String processStatus_str;
    private String processStatusName;
    private String sheet;
    //三个字段方法
    private String rbaName;
    private String rbsName;
    private String rbbName;
    @Formula("(SELECT g.rba_name FROM md_company_group g WHERE g.rba_id=rba_id)")
    public String getRbaName() {
		return rbaName;
	}

	public void setRbaName(String rbaName) {
		this.rbaName = rbaName;
	}
	 @Formula("(SELECT dh.rbs_name FROM md_dentist_hospital dh WHERE dh.rbs_id=rbs_id)")
	public String getRbsName() {
		return rbsName;
	}

	public void setRbsName(String rbsName) {
		this.rbsName = rbsName;
	}
	 @Formula("(SELECT dc.rbb_name FROM md_dental_clinic dc WHERE dc.rbb_id=rbb_id)")
	public String getRbbName() {
		return rbbName;
	}

	public void setRbbName(String rbbName) {
		this.rbbName = rbbName;
	}

	/**
     * 商品名称字段方法
     */
    private String matName;
    /**
     * 商品名称字段方法 用于查询订单名称时使用
     */
    @Transient
    public String getMatName() {
		return matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}

	//添加采购人名称
    private String purchaseName;
   @Formula("(SELECT su.user_name FROM sys_user_info su WHERE su.sui_id=purchase_id)")
   public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

/* *//**
     * 2019-12-19
     * yanglei
     * 增加商品名称字段 @Formula 用于订单编号与关键字查询
     * @return
     *//*
    private String matName;
    @Formula("(SELECT mx.mat_name FROM md_order_mx mx WHERE mx.moo")
	public String getMatName() {
		return matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}*/
	@Transient
	public String getSheet() {
		return sheet;
	}

	public String setSheet(String sheet) {
		return this.sheet = sheet;
	}
    
    private List<Map<String, Object>> orderMxList;

    @Column(name = "applicant_name")
    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    @Formula("(SELECT a.param_name FROM sys_parameter a,sys_parameter b WHERE a.param_value= process_status and a.sys_spar_id=b.spar_id and b.param_code='PAR171023031218563')")
    public String getProcessStatusName() {
        return processStatusName;
    }
    
    public void setProcessStatusName(String processStatusName) {
        this.processStatusName = processStatusName;
    }

    @Transient
    public String getProcessStatus_str() {
        return processStatus_str;
    }

    public void setProcessStatus_str(String processStatus_str) {
        this.processStatus_str = processStatus_str;
    }

    @Transient
    public List<Map<String, Object>> getOrderMxList() {
        return orderMxList;
    }

    public void setOrderMxList(List<Map<String, Object>> orderMxList) {
        this.orderMxList = orderMxList;
    }

    public void setOrderMxList(HashMap orderMxList) {
    }

    @Transient
    public String getExpressNameAndCode() {
        return expressNameAndCode;
    }

    public void setExpressNameAndCode(String expressNameAndCode) {
        this.expressNameAndCode = expressNameAndCode;
    }

    @Formula("(select a.phone_number from md_supplier a where a.wz_id = wz_id)")
    public String getSupplierPhoneNumber() {
        return supplierPhoneNumber;
    }

    public void setSupplierPhoneNumber(String supplierPhoneNumber) {
        this.supplierPhoneNumber = supplierPhoneNumber;
    }

    @Column(name = "express_date")
    public String getExpressDate() {
        return expressDate;
    }

    public void setExpressDate(String expressDate) {
        this.expressDate = expressDate;
    }

    public void setLoanState(Integer loanState) {
        this.loanState = loanState;
    }
    @Column(name = "loan_state")
    public Integer getLoanState() {
        return loanState;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }
    @Column(name = "loan_money")
    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanLog(String loanLog) {
        this.loanLog = loanLog;
    }
    @Column(name = "loan_log")
    public String getLoanLog() {
        return loanLog;
    }

    private String enterCode;
    private String enterDate;
    private String enterType;

    @Transient
    public String getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(String enterDate) {
        this.enterDate = enterDate;
    }

    @Transient
    public String getEnterType() {
        return enterType;
    }

    public void setEnterType(String enterType) {
        this.enterType = enterType;
    }

    //    @Formula("(select a.bill_code from md_enter_warehouse a where a.relation_billCode = order_code)")
    @Transient
    public String getEnterCode() {
        return enterCode;
    }

    public void setEnterCode(String enterCode) {
        this.enterCode = enterCode;
    }
}