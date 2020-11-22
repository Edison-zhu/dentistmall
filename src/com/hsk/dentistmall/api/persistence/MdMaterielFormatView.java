package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * author: yangfeng
 * time: 2020/6/2 9:09
 */
@Entity
@Table(name = "md_materiel_format_view")
public class MdMaterielFormatView {
    private Integer wmsMiId;
    private Integer wzId;
    private String purchaseType;
    private Integer mdWmsMiId;
    private String applicantName;
    private String phoneNumber;
    private String corporateDomicile;
    private String scopeBusiness;
    private String productName;
    private String productFactory;
    private String brand;
    private String matCode;
    private String matName;
    private String pyName;
    private Double money1;
    private Double money2;
    private Double money3;
    private Double money4;
    private Double money5;
    private Double number1;
    private Double number2;
    private Double number3;
    private Double number4;
    private Double number5;
    private String basicUnit;
    private String batchRule;
    private Integer validPeriod;
    private Integer alertDay;
    private String norm;
    private String matType;
    private String matType1;
    private String matType2;
    private String matType3;
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
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;
    private Integer xid;
    private String cfca01Filecode;
    private String cfca02Filecode;
    private String cfca03Filecode;
    private String cfca04Filecode;
    private String cfca05Filecode;
    private String cfca06Filecode;
    private String cfca01Date;
    private String cfca02Date;
    private String aliasName;
    private String inventoryLocation;
    private Integer mdpId;
    private Integer mdpsId;
    private String basicCoefficent;
    private String splitUnit;
    private Date valiedDate;
    private String standard;
    private String materielName;
    private String ingredient;
    private String productUse;
    private String batchCode;
    private String remark;
//    private Double baseNumber;
    private Integer mmfId;
    private String mmfCode;
    private String mmfName;
    private String mmfState;
    private Double retailPrice;
    private Double price;
    private String typeName;
    private String wmsMiIds;
    private String mmfIds;
//    private Double avgPrice;

    //外部关联字段
    private String lessenFilePath;
    //与数据库无关联字段
    private String countShu;
    private String mcSplitCount;
    private String countMoney;
    private String searchName;

    private Double allInventory;
    private Double avgPrice;
    private Double baseNumber;
    private Double quantity1;
    private Double ratio;

    @Basic
    @Column(name = "wms_mi_id")
    public Integer getWmsMiId() {
        return wmsMiId;
    }

    public void setWmsMiId(Integer wmsMiId) {
        this.wmsMiId = wmsMiId;
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
    @Column(name = "product_factory")
    public String getProductFactory() {
        return productFactory;
    }

    public void setProductFactory(String productFactory) {
        this.productFactory = productFactory;
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
    @Column(name = "number1")
    public Double getNumber1() {
        return number1;
    }

    public void setNumber1(Double number1) {
        this.number1 = number1;
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
    @Column(name = "Basic_unit")
    public String getBasicUnit() {
        return basicUnit;
    }

    public void setBasicUnit(String basicUnit) {
        this.basicUnit = basicUnit;
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
    @Column(name = "NORM")
    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
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
    @Column(name = "alias_name")
    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    @Basic
    @Column(name = "inventory_location")
    public String getInventoryLocation() {
        return inventoryLocation;
    }

    public void setInventoryLocation(String inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

    @Basic
    @Column(name = "mdp_id")
    public Integer getMdpId() {
        return mdpId;
    }

    public void setMdpId(Integer mdpId) {
        this.mdpId = mdpId;
    }

    @Basic
    @Column(name = "mdps_id")
    public Integer getMdpsId() {
        return mdpsId;
    }

    public void setMdpsId(Integer mdpsId) {
        this.mdpsId = mdpsId;
    }

    @Basic
    @Column(name = "basic_coefficent")
    public String getBasicCoefficent() {
        return basicCoefficent;
    }

    public void setBasicCoefficent(String basicCoefficent) {
        this.basicCoefficent = basicCoefficent;
    }

    @Basic
    @Column(name = "split_unit")
    public String getSplitUnit() {
        return splitUnit;
    }

    public void setSplitUnit(String splitUnit) {
        this.splitUnit = splitUnit;
    }

    @Basic
    @Column(name = "valied_date")
    public Date getValiedDate() {
        return valiedDate;
    }

    public void setValiedDate(Date valiedDate) {
        this.valiedDate = valiedDate;
    }

    @Basic
    @Column(name = "standard")
    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    @Basic
    @Column(name = "materiel_name")
    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    @Basic
    @Column(name = "ingredient")
    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Basic
    @Column(name = "product_use")
    public String getProductUse() {
        return productUse;
    }

    public void setProductUse(String productUse) {
        this.productUse = productUse;
    }

    @Basic
    @Column(name = "batch_code")
    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

//    @Basic
//    @Column(name = "base_number")
//    public Double getBaseNumber() {
//        return baseNumber;
//    }
//
//    public void setBaseNumber(Double baseNumber) {
//        this.baseNumber = baseNumber;
//    }

    @Basic
    @Id
    @Column(name = "mmf_id")
    public Integer getMmfId() {
        return mmfId;
    }

    public void setMmfId(Integer mmfId) {
        this.mmfId = mmfId;
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
    @Column(name = "mmf_name")
    public String getMmfName() {
        return mmfName;
    }

    public void setMmfName(String mmfName) {
        this.mmfName = mmfName;
    }

    @Basic
    @Column(name = "mmf_state")
    public String getMmfState() {
        return mmfState;
    }

    public void setMmfState(String mmfState) {
        this.mmfState = mmfState;
    }

    @Basic
    @Column(name = "retail_price")
    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
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
    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

//    @Basic
//    @Column(name = "avg_price")
//    public Double getAvgPrice() {
//        return avgPrice;
//    }
//
//    public void setAvgPrice(Double avgPrice) {
//        this.avgPrice = avgPrice;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdMaterielFormatView that = (MdMaterielFormatView) o;
        return wmsMiId == that.wmsMiId &&
                mmfId == that.mmfId &&
                Objects.equals(wzId, that.wzId) &&
                Objects.equals(purchaseType, that.purchaseType) &&
                Objects.equals(mdWmsMiId, that.mdWmsMiId) &&
                Objects.equals(applicantName, that.applicantName) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(corporateDomicile, that.corporateDomicile) &&
                Objects.equals(scopeBusiness, that.scopeBusiness) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productFactory, that.productFactory) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(matCode, that.matCode) &&
                Objects.equals(matName, that.matName) &&
                Objects.equals(pyName, that.pyName) &&
                Objects.equals(money1, that.money1) &&
                Objects.equals(money2, that.money2) &&
                Objects.equals(money3, that.money3) &&
                Objects.equals(money4, that.money4) &&
                Objects.equals(money5, that.money5) &&
                Objects.equals(number1, that.number1) &&
                Objects.equals(number2, that.number2) &&
                Objects.equals(number3, that.number3) &&
                Objects.equals(number4, that.number4) &&
                Objects.equals(number5, that.number5) &&
                Objects.equals(basicUnit, that.basicUnit) &&
                Objects.equals(batchRule, that.batchRule) &&
                Objects.equals(validPeriod, that.validPeriod) &&
                Objects.equals(alertDay, that.alertDay) &&
                Objects.equals(norm, that.norm) &&
                Objects.equals(matType, that.matType) &&
                Objects.equals(matType1, that.matType1) &&
                Objects.equals(matType2, that.matType2) &&
                Objects.equals(matType3, that.matType3) &&
                Objects.equals(labelInfo, that.labelInfo) &&
                Objects.equals(lessenFilecode, that.lessenFilecode) &&
                Objects.equals(describe1, that.describe1) &&
                Objects.equals(describe2, that.describe2) &&
                Objects.equals(barCode, that.barCode) &&
                Objects.equals(barCodeFilecode, that.barCodeFilecode) &&
                Objects.equals(state, that.state) &&
                Objects.equals(wzState, that.wzState) &&
                Objects.equals(basicUnitAccuracy, that.basicUnitAccuracy) &&
                Objects.equals(backPrinting, that.backPrinting) &&
                Objects.equals(serialNumber, that.serialNumber) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editRen, that.editRen) &&
                Objects.equals(editDate, that.editDate) &&
                Objects.equals(xid, that.xid) &&
                Objects.equals(cfca01Filecode, that.cfca01Filecode) &&
                Objects.equals(cfca02Filecode, that.cfca02Filecode) &&
                Objects.equals(cfca03Filecode, that.cfca03Filecode) &&
                Objects.equals(cfca04Filecode, that.cfca04Filecode) &&
                Objects.equals(cfca05Filecode, that.cfca05Filecode) &&
                Objects.equals(cfca06Filecode, that.cfca06Filecode) &&
                Objects.equals(cfca01Date, that.cfca01Date) &&
                Objects.equals(cfca02Date, that.cfca02Date) &&
                Objects.equals(aliasName, that.aliasName) &&
                Objects.equals(inventoryLocation, that.inventoryLocation) &&
                Objects.equals(mdpId, that.mdpId) &&
                Objects.equals(mdpsId, that.mdpsId) &&
                Objects.equals(basicCoefficent, that.basicCoefficent) &&
                Objects.equals(splitUnit, that.splitUnit) &&
                Objects.equals(valiedDate, that.valiedDate) &&
                Objects.equals(standard, that.standard) &&
                Objects.equals(materielName, that.materielName) &&
                Objects.equals(ingredient, that.ingredient) &&
                Objects.equals(productUse, that.productUse) &&
                Objects.equals(batchCode, that.batchCode) &&
                Objects.equals(remark, that.remark) &&
//                Objects.equals(baseNumber, that.baseNumber) &&
                Objects.equals(mmfCode, that.mmfCode) &&
                Objects.equals(mmfName, that.mmfName) &&
                Objects.equals(mmfState, that.mmfState) &&
                Objects.equals(retailPrice, that.retailPrice) &&
                Objects.equals(price, that.price) &&
                Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wmsMiId, wzId, purchaseType, mdWmsMiId, applicantName, phoneNumber, corporateDomicile, scopeBusiness, productName, productFactory, brand, matCode, matName, pyName, money1, money2, money3, money4, money5, number1, number2, number3, number4, number5, basicUnit, batchRule, validPeriod, alertDay, norm, matType, matType1, matType2, matType3, labelInfo, lessenFilecode, describe1, describe2, barCode, barCodeFilecode, state, wzState, basicUnitAccuracy, backPrinting, serialNumber, createRen, createDate, editRen, editDate, xid, cfca01Filecode, cfca02Filecode, cfca03Filecode, cfca04Filecode, cfca05Filecode, cfca06Filecode, cfca01Date, cfca02Date, aliasName, inventoryLocation, mdpId, mdpsId, basicCoefficent, splitUnit, valiedDate, standard, materielName, ingredient, productUse, batchCode, remark, mmfId, mmfCode, mmfName, mmfState, retailPrice, price, typeName);
    }

    @Transient
    public void setWmsMiIds(String wmsMiIds) {
        this.wmsMiIds = wmsMiIds;
    }

    @Transient
    public String getWmsMiIds() {
        return wmsMiIds;
    }

    public void setMmfIds(String mmfIds) {
        this.mmfIds = mmfIds;
    }

    @Transient
    public String getMmfIds() {
        return mmfIds;
    }

    @Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code= lessen_filecode)")
    public String getLessenFilePath() {
        return lessenFilePath;
    }

    public void setLessenFilePath(String lessenFilePath) {
        this.lessenFilePath = lessenFilePath;
    }

    @Transient
    public String getCountShu() {
        return countShu;
    }

    public void setCountShu(String countShu) {
        this.countShu = countShu;
    }
    @Transient
    public String getMcSplitCount() {
        return mcSplitCount;
    }

    public void setMcSplitCount(String mcSplitCount) {
        this.mcSplitCount = mcSplitCount;
    }

    @Transient
    public String getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(String countMoney) {
        this.countMoney = countMoney;
    }

    @Transient
    public Double getAllInventory() {
        return allInventory;
    }

    public void setAllInventory(Double allInventory) {
        this.allInventory = allInventory;
    }

    @Transient
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

	//添加库存数量显示
	private Double quantity;
	@Formula("(SELECT SUM(e.QUANTITY) FROM md_inventory_extend_view e WHERE e.mmf_id =mmf_id)")
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	private Double splitqQuantitySum;

    @Formula("(SELECT SUM(e.base_number-(e.QUANTITY*e.ratio)) FROM md_inventory_extend_view e WHERE e.mmf_id =mmf_id)")
    public Double getSplitqQuantitySum() {
        return splitqQuantitySum;
    }
    public void setSplitqQuantitySum(Double splitqQuantitySum) {
        this.splitqQuantitySum = splitqQuantitySum;
    }




    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }
    @Transient
    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setBaseNumber(Double baseNumber) {
        this.baseNumber = baseNumber;
    }
    @Transient
    public Double getBaseNumber() {
        return baseNumber;
    }

    public void setQuantity1(Double quantity1) {
        this.quantity1 = quantity1;
    }
    @Transient
    public Double getQuantity1() {
        return quantity1;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
    @Transient
    public Double getRatio() {
        return ratio;
    }

//    //后台购物车排序
//    private Date editDate1;
//    @Formula(" (SELECT t1.edit_date FROM md_carts t1 WHERE t1.mmf_id=mdmateriel0_.mmf_id)")
//    public Date getEditDate1() {
//        return editDate1;
//    }
//
//    public void setEditDate1(Date editDate1) {
//        this.editDate1 = editDate1;
//    }
}
