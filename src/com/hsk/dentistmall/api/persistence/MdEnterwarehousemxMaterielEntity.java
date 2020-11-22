package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * author: yangfeng
 * time: 20191020
 * md_enterwarehousemx_materiel: 物料入库信息详细表与物料信息表关联视图
 */
@Entity
@Table(name = "md_enterwarehousemx_materiel")
public class MdEnterwarehousemxMaterielEntity {
    private Integer wewMxId;
    private Integer wewId;
    private Integer wmsMiId;
    private Integer mmfId;
    private String itemKeyId;
    private Integer lineNumber;
    private Date placeOrderTime;
    private String productFactory;
    private Date productTime;
    private Date productValitime;
    private String packageWay;
    private String batchCertNo;
    private Double price;
    private Double matNumber;
    private Double number1;
    private String purchaseUnit;
    private String matCode;
    private String matName;
    private String matName2;
    private String basicUnit;
    private String norm;
    private String norm2;
    private String matType;
    private String matType1;
    private String matType2;
    private String matType3;
    private Date receiptDatetime;
    private String describes;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;
    private Integer wzId;
    private String purchaseType;
    private Integer mdWmsMiId;
    private String applicantName;
    private String phoneNumber;
    private String corporateDomicile;
    private String scopeBusiness;
    //更改包装方式字段
    private String productName;
    private String brand;
    private String pyName;
    private Double money1;
    private Double money2;
    private Double money3;
    private Double money4;
    private Double money5;
    private Double number2;
    private Double number3;
    private Double number4;
    private Double number5;
    private String batchRule;
    private Integer validPeriod;
    private Integer alertDay;
    private String labelInfo;
    private String lessenFilecode;
    private String describe1;
    private String describe2;
    private String barCode;
    private String barCodeFilecode;
    private String state;
    private String wzState;
    private String basicUnitAccuracy;
    private String backPrinting;
    private Integer serialNumber;
    private Integer xid;
    private String cfca01Filecode;
    private String cfca02Filecode;
    private String cfca03Filecode;
    private String cfca04Filecode;
    private String cfca05Filecode;
    private String cfca06Filecode;
    private String cfca01Date;
    private String cfca02Date;
    //0612更改视图之后 新增字段
    private String aliasName;
    private Date valiedDate;
    private String standard;
    private String materielName;
    private  String ingredient;
    private String productUse;
    private String validPeriodUnit;
    private String weight;
    private  String batchCode;
    private String remark;

    private String mmfName;
    private String mmfCode;
    private Double retailMoney; //入库详细保存的
    private Double retailPrice; //规格物品的
    private Double orderNumber;
    private Double sendNumber;
    private String unit;
    private Double splitQuantity;
    private String basicCoefficent;

    private Integer linkWmsMiId;
    private Integer linkMmfId;

    private String mmfCode1;
    private String matName1;

    private Double ratio1;

    @Column(name = "ratio1")
    public Double getRatio1() {
        return ratio1;
    }

    public void setRatio1(Double ratio1) {
        this.ratio1 = ratio1;
    }

    @Formula("(SELECT f.mat_name FROM md_materiel_info f where f.wms_mi_id = link_wms_mi_id)")
    public String getMatName1() {
        return matName1;
    }

    public void setMatName1(String matName1) {
        this.matName1 = matName1;
    }

    @Formula("(SELECT f.mmf_code FROM md_materiel_format f where f.mmf_id = link_mmf_id)")
    public String getMmfCode1() {
        return mmfCode1;
    }

    public void setMmfCode1(String mmfCode1) {
        this.mmfCode1 = mmfCode1;
    }
    @Column(name = "link_wms_mi_id")
    public Integer getLinkWmsMiId() {
        return linkWmsMiId;
    }
    public void setLinkWmsMiId(Integer linkWmsMiId) {
        this.linkWmsMiId = linkWmsMiId;
    }
    @Column(name = "link_mmf_id")
    public Integer getLinkMmfId() {
        return linkMmfId;
    }

    public void setLinkMmfId(Integer linkMmfId) {
        this.linkMmfId = linkMmfId;
    }

    @Basic
    @Id
    @Column(name = "wew_mx_id")
    public Integer getWewMxId() {
        return wewMxId;
    }

    public void setWewMxId(Integer wewMxId) {
        this.wewMxId = wewMxId;
    }

    @Basic
    @Column(name = "wew_id")
    public Integer getWewId() {
        return wewId;
    }

    public void setWewId(Integer wewId) {
        this.wewId = wewId;
    }

    @Basic
    @Column(name = "wms_mi_id")
    public Integer getWmsMiId() {
        return wmsMiId;
    }

    public void setWmsMiId(Integer wmsMiId) {
        this.wmsMiId = wmsMiId;
    }

    @Basic
    @Column(name = "mmf_id")
    public Integer getMmfId() {
        return mmfId;
    }

    public void setMmfId(Integer mmfId) {
        this.mmfId = mmfId;
    }

    @Basic
    @Column(name = "ITEM_KEY_ID")
    public String getItemKeyId() {
        return itemKeyId;
    }

    public void setItemKeyId(String itemKeyId) {
        this.itemKeyId = itemKeyId;
    }

    @Basic
    @Column(name = "Line_number")
    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Basic
    @Column(name = "Place_order_time")
    public Date getPlaceOrderTime() {
        return placeOrderTime;
    }

    public void setPlaceOrderTime(Date placeOrderTime) {
        this.placeOrderTime = placeOrderTime;
    }

    @Basic
    @Column(name = "product_factory")
    public String getProductFactory() {
        return productFactory;
    }

    public void setProductFactory(String productFactory) {
        this.productFactory = productFactory;
    }

    @Basic
    @Column(name = "product_time")
    public Date getProductTime() {
        return productTime;
    }

    public void setProductTime(Date productTime) {
        this.productTime = productTime;
    }

    @Basic
    @Column(name = "product_valitime")
    public Date getProductValitime() {
        return productValitime;
    }

    public void setProductValitime(Date productValitime) {
        this.productValitime = productValitime;
    }

    @Basic
    @Column(name = "product_name_enter")
    public String getPackageWay() {
        return packageWay;
    }

    public void setPackageWay(String packageWay) {
        this.packageWay = packageWay;
    }

    @Basic
    @Column(name = "batch_certNo")
    public String getBatchCertNo() {
        return batchCertNo;
    }

    public void setBatchCertNo(String batchCertNo) {
        this.batchCertNo = batchCertNo;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "mat_number")
    public Double getMatNumber() {
        return matNumber;
    }

    public void setMatNumber(Double matNumber) {
        this.matNumber = matNumber;
    }

    @Basic
    @Column(name = "number1")
    public Double getNumber1() {
        return number1;
    }

    public void setNumber1(Double number1) {
        this.number1 = number1;
    }

    @Basic
    @Column(name = "Purchase_unit")
    public String getPurchaseUnit() {
        return purchaseUnit;
    }

    public void setPurchaseUnit(String purchaseUnit) {
        this.purchaseUnit = purchaseUnit;
    }

    @Basic
    @Column(name = "mat_code")
    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    @Basic
    @Column(name = "mat_name")
    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    @Basic
    @Column(name = "mat_name2")
    public String getMatName2() {
        return matName2;
    }

    public void setMatName2(String matName2) {
        this.matName2 = matName2;
    }

    @Basic
    @Column(name = "Basic_unit")
    public String getBasicUnit() {
        return basicUnit;
    }

    public void setBasicUnit(String basicUnit) {
        this.basicUnit = basicUnit;
    }

    @Basic
    @Column(name = "NORM")
    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    @Basic
    @Column(name = "norm2")
    public String getNorm2() {
        return norm2;
    }

    public void setNorm2(String norm2) {
        this.norm2 = norm2;
    }

    @Basic
    @Column(name = "mat_type")
    public String getMatType() {
        return matType;
    }

    public void setMatType(String matType) {
        this.matType = matType;
    }

    @Basic
    @Column(name = "mat_type1")
    public String getMatType1() {
        return matType1;
    }

    public void setMatType1(String matType1) {
        this.matType1 = matType1;
    }

    @Basic
    @Column(name = "mat_type2")
    public String getMatType2() {
        return matType2;
    }

    public void setMatType2(String matType2) {
        this.matType2 = matType2;
    }

    @Basic
    @Column(name = "mat_type3")
    public String getMatType3() {
        return matType3;
    }

    public void setMatType3(String matType3) {
        this.matType3 = matType3;
    }

    @Basic
    @Column(name = "receipt_datetime")
    public Date getReceiptDatetime() {
        return receiptDatetime;
    }

    public void setReceiptDatetime(Date receiptDatetime) {
        this.receiptDatetime = receiptDatetime;
    }

    @Basic
    @Column(name = "describes")
    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @Basic
    @Column(name = "create_ren")
    public String getCreateRen() {
        return createRen;
    }

    public void setCreateRen(String createRen) {
        this.createRen = createRen;
    }

    @Basic
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "edit_ren")
    public String getEditRen() {
        return editRen;
    }

    public void setEditRen(String editRen) {
        this.editRen = editRen;
    }

    @Basic
    @Column(name = "edit_date")
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    @Basic
    @Column(name = "wz_id")
    public Integer getWzId() {
        return wzId;
    }

    public void setWzId(Integer wzId) {
        this.wzId = wzId;
    }

    @Basic
    @Column(name = "purchase_type")
    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    @Basic
    @Column(name = "md_wms_mi_id")
    public Integer getMdWmsMiId() {
        return mdWmsMiId;
    }

    public void setMdWmsMiId(Integer mdWmsMiId) {
        this.mdWmsMiId = mdWmsMiId;
    }

    @Basic
    @Column(name = "applicant_Name")
    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    @Basic
    @Column(name = "Phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "Corporate_domicile")
    public String getCorporateDomicile() {
        return corporateDomicile;
    }

    public void setCorporateDomicile(String corporateDomicile) {
        this.corporateDomicile = corporateDomicile;
    }

    @Basic
    @Column(name = "scope_business")
    public String getScopeBusiness() {
        return scopeBusiness;
    }

    public void setScopeBusiness(String scopeBusiness) {
        this.scopeBusiness = scopeBusiness;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "py_name")
    public String getPyName() {
        return pyName;
    }

    public void setPyName(String pyName) {
        this.pyName = pyName;
    }

    @Basic
    @Column(name = "money1")
    public Double getMoney1() {
        return money1;
    }

    public void setMoney1(Double money1) {
        this.money1 = money1;
    }

    @Basic
    @Column(name = "money2")
    public Double getMoney2() {
        return money2;
    }

    public void setMoney2(Double money2) {
        this.money2 = money2;
    }

    @Basic
    @Column(name = "money3")
    public Double getMoney3() {
        return money3;
    }

    public void setMoney3(Double money3) {
        this.money3 = money3;
    }

    @Basic
    @Column(name = "money4")
    public Double getMoney4() {
        return money4;
    }

    public void setMoney4(Double money4) {
        this.money4 = money4;
    }

    @Basic
    @Column(name = "money5")
    public Double getMoney5() {
        return money5;
    }

    public void setMoney5(Double money5) {
        this.money5 = money5;
    }

    @Basic
    @Column(name = "number2")
    public Double getNumber2() {
        return number2;
    }

    public void setNumber2(Double number2) {
        this.number2 = number2;
    }

    @Basic
    @Column(name = "number3")
    public Double getNumber3() {
        return number3;
    }

    public void setNumber3(Double number3) {
        this.number3 = number3;
    }

    @Basic
    @Column(name = "number4")
    public Double getNumber4() {
        return number4;
    }

    public void setNumber4(Double number4) {
        this.number4 = number4;
    }

    @Basic
    @Column(name = "number5")
    public Double getNumber5() {
        return number5;
    }

    public void setNumber5(Double number5) {
        this.number5 = number5;
    }

    @Basic
    @Column(name = "Batch_rule")
    public String getBatchRule() {
        return batchRule;
    }

    public void setBatchRule(String batchRule) {
        this.batchRule = batchRule;
    }

    @Basic
    @Column(name = "VALID_PERIOD")
    public Integer getValidPeriod() {
        return validPeriod;
    }

    public void setValidPeriod(Integer validPeriod) {
        this.validPeriod = validPeriod;
    }

    @Basic
    @Column(name = "ALERT_DAY")
    public Integer getAlertDay() {
        return alertDay;
    }

    public void setAlertDay(Integer alertDay) {
        this.alertDay = alertDay;
    }

    @Basic
    @Column(name = "Label_info")
    public String getLabelInfo() {
        return labelInfo;
    }

    public void setLabelInfo(String labelInfo) {
        this.labelInfo = labelInfo;
    }

    @Basic
    @Column(name = "lessen_filecode")
    public String getLessenFilecode() {
        return lessenFilecode;
    }

    public void setLessenFilecode(String lessenFilecode) {
        this.lessenFilecode = lessenFilecode;
    }

    @Basic
    @Column(name = "describe1")
    public String getDescribe1() {
        return describe1;
    }

    public void setDescribe1(String describe1) {
        this.describe1 = describe1;
    }

    @Basic
    @Column(name = "describe2")
    public String getDescribe2() {
        return describe2;
    }

    public void setDescribe2(String describe2) {
        this.describe2 = describe2;
    }

    @Basic
    @Column(name = "barCode")
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Basic
    @Column(name = "barCode_filecode")
    public String getBarCodeFilecode() {
        return barCodeFilecode;
    }

    public void setBarCodeFilecode(String barCodeFilecode) {
        this.barCodeFilecode = barCodeFilecode;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "wz_state")
    public String getWzState() {
        return wzState;
    }

    public void setWzState(String wzState) {
        this.wzState = wzState;
    }

    @Basic
    @Column(name = "Basic_unit_accuracy")
    public String getBasicUnitAccuracy() {
        return basicUnitAccuracy;
    }

    public void setBasicUnitAccuracy(String basicUnitAccuracy) {
        this.basicUnitAccuracy = basicUnitAccuracy;
    }

    @Basic
    @Column(name = "back_Printing")
    public String getBackPrinting() {
        return backPrinting;
    }

    public void setBackPrinting(String backPrinting) {
        this.backPrinting = backPrinting;
    }

    @Basic
    @Column(name = "Serial_number")
    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Basic
    @Column(name = "xid")
    public Integer getXid() {
        return xid;
    }

    public void setXid(Integer xid) {
        this.xid = xid;
    }

    @Basic
    @Column(name = "cfca01_filecode")
    public String getCfca01Filecode() {
        return cfca01Filecode;
    }

    public void setCfca01Filecode(String cfca01Filecode) {
        this.cfca01Filecode = cfca01Filecode;
    }

    @Basic
    @Column(name = "cfca02_filecode")
    public String getCfca02Filecode() {
        return cfca02Filecode;
    }

    public void setCfca02Filecode(String cfca02Filecode) {
        this.cfca02Filecode = cfca02Filecode;
    }

    @Basic
    @Column(name = "cfca03_filecode")
    public String getCfca03Filecode() {
        return cfca03Filecode;
    }

    public void setCfca03Filecode(String cfca03Filecode) {
        this.cfca03Filecode = cfca03Filecode;
    }

    @Basic
    @Column(name = "cfca04_filecode")
    public String getCfca04Filecode() {
        return cfca04Filecode;
    }

    public void setCfca04Filecode(String cfca04Filecode) {
        this.cfca04Filecode = cfca04Filecode;
    }

    @Basic
    @Column(name = "cfca05_filecode")
    public String getCfca05Filecode() {
        return cfca05Filecode;
    }

    public void setCfca05Filecode(String cfca05Filecode) {
        this.cfca05Filecode = cfca05Filecode;
    }

    @Basic
    @Column(name = "cfca06_filecode")
    public String getCfca06Filecode() {
        return cfca06Filecode;
    }

    public void setCfca06Filecode(String cfca06Filecode) {
        this.cfca06Filecode = cfca06Filecode;
    }

    @Basic
    @Column(name = "cfca01_date")
    public String getCfca01Date() {
        return cfca01Date;
    }

    public void setCfca01Date(String cfca01Date) {
        this.cfca01Date = cfca01Date;
    }

    @Basic
    @Column(name = "cfca02_date")
    public String getCfca02Date() {
        return cfca02Date;
    }

    public void setCfca02Date(String cfca02Date) {
        this.cfca02Date = cfca02Date;
    }

    @Basic
    @Column(name = "mmf_name")
    public String getMmfName() {
        return mmfName;
    }

    public void setMmfName(String mmfName) {
        this.mmfName = mmfName;
    }

    @Basic
    @Column(name = "mmf_code")
    public String getMmfCode() {
        return mmfCode;
    }

    public void setMmfCode(String mmfCode) {
        this.mmfCode = mmfCode;
    }

    @Basic
    @Column(name = "retail_money")
    public Double getRetailMoney() {
        return retailMoney;
    }

    public void setRetailMoney(Double retailMoney) {
        this.retailMoney = retailMoney;
    }

    @Basic
    @Column(name = "retail_price")
    public Double getRetailPrice() {
        return retailPrice;
    }


    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public void setOrderNumber(Double orderNumber) {
        this.orderNumber = orderNumber;
    }
    @Transient
    public Double getOrderNumber() {
        return orderNumber;
    }


    public void setSendNumber(Double sendNumber) {
        this.sendNumber = sendNumber;
    }
    @Transient
    public Double getSendNumber() {
        return sendNumber;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "split_quantity")
    public Double getSplitQuantity() {
        return splitQuantity;
    }

    public void setSplitQuantity(Double splitQuantity) {
        this.splitQuantity = splitQuantity;
    }

    @Basic
    @Column(name = "basic_coefficent")
    public String getBasicCoefficent() {
        return basicCoefficent;
    }

    public void setBasicCoefficent(String basicCoefficent) {
        this.basicCoefficent = basicCoefficent;
    }
    @Column(name = "alias_name")
    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
    @Column(name = "valied_date")
    public Date getValiedDate() {
        return valiedDate;
    }

    public void setValiedDate(Date valiedDate) {
        this.valiedDate = valiedDate;
    }
    @Column(name = "standard")
    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
    @Column(name = "materiel_name")
    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }
    @Column(name = "ingredient")
    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
    @Column(name = "product_use")
    public String getProductUse() {
        return productUse;
    }

    public void setProductUse(String productUse) {
        this.productUse = productUse;
    }
    @Column(name = "valid_period_unit")
    public String getValidPeriodUnit() {
        return validPeriodUnit;
    }

    public void setValidPeriodUnit(String validPeriodUnit) {
        this.validPeriodUnit = validPeriodUnit;
    }
    @Column(name = "weight")
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    @Column(name = "batch_code")
    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    //编辑入库时使用  --momId   --2020-07-07  yanglei
    private Integer momId;
    @Transient
    public Integer getMomId() {
        return momId;
    }

    public void setMomId(Integer momId) {
        this.momId = momId;
    }
}
