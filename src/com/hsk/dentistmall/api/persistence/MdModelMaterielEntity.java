package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/7/31 9:45
 */
@Entity
@Table(name = "md_model_materiel")
public class MdModelMaterielEntity {
    private Integer wmsModelId;
    private Integer wzId;
    private String purchaseType;
    private Integer mdWmsMiId;
    private String applicantName;
    private String phoneNumber;
    private String corporateDomicile;
    private String scopeBusiness;
    private String productName;
    private String productFactory;
    private String productPlace;
    private String brand;
    private String modelMatCode;
    private String modelMatName;
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
    private Integer matType1;
    private Integer matType2;
    private Integer matType3;
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
    private Double weight;
    private String validPeriodUnit;
    private String weightUnit;
    private String mbdId;
    private String commonName;
    private String introduction;
    private String keyWord;
    private Integer suiId;
    private String sysFileIds;
    private Integer refCount;
    private List<String> pathList;
    private List<String> brandList;
    private String sellUnit;
    private Integer canSearch;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "wms_model_id")
    public Integer getWmsModelId() {
        return wmsModelId;
    }

    public void setWmsModelId(Integer wmsMiId) {
        this.wmsModelId = wmsMiId;
    }

    @Column(name = "sys_file_ids")
    public String getSysFileIds() {
        return sysFileIds;
    }

    public void setSysFileIds(String sysFileIds) {
        this.sysFileIds = sysFileIds;
    }

    @Column(name = "sui_id")
    public Integer getSuiId() {
        return suiId;
    }

    public void setSuiId(Integer suiId) {
        this.suiId = suiId;
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
    @Column(name = "product_place")
    public String getProductPlace() {
        return productPlace;
    }

    public void setProductPlace(String productPlace) {
        this.productPlace = productPlace;
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
    @Column(name = "model_mat_code")
    public String getModelMatCode() {
        return modelMatCode;
    }

    public void setModelMatCode(String matCode) {
        this.modelMatCode = matCode;
    }

    @Basic
    @Column(name = "model_mat_name")
    public String getModelMatName() {
        return modelMatName;
    }

    public void setModelMatName(String matName) {
        this.modelMatName = matName;
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
    public Integer getMatType1() {
        return matType1;
    }

    public void setMatType1(Integer matType1) {
        this.matType1 = matType1;
    }

    @Basic
    @Column(name = "mat_type2")
    public Integer getMatType2() {
        return matType2;
    }

    public void setMatType2(Integer matType2) {
        this.matType2 = matType2;
    }

    @Basic
    @Column(name = "mat_type3")
    public Integer getMatType3() {
        return matType3;
    }

    public void setMatType3(Integer matType3) {
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

    @Basic
    @Column(name = "weight")
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "valid_period_unit")
    public String getValidPeriodUnit() {
        return validPeriodUnit;
    }

    public void setValidPeriodUnit(String validPeriodUnit) {
        this.validPeriodUnit = validPeriodUnit;
    }

    @Basic
    @Column(name = "weight_unit")
    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    @Basic
    @Column(name = "mbd_id")
    public String getMbdId() {
        return mbdId;
    }

    public void setMbdId(String mbdId) {
        this.mbdId = mbdId;
    }

    @Basic
    @Column(name = "common_name")
    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "key_word")
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Column(name = "sell_unit")
    public String getSellUnit() {
        return sellUnit;
    }

    public void setSellUnit(String sellUnit) {
        this.sellUnit = sellUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdModelMaterielEntity that = (MdModelMaterielEntity) o;
        return wmsModelId == that.wmsModelId &&
                Objects.equals(wzId, that.wzId) &&
                Objects.equals(purchaseType, that.purchaseType) &&
                Objects.equals(mdWmsMiId, that.mdWmsMiId) &&
                Objects.equals(applicantName, that.applicantName) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(corporateDomicile, that.corporateDomicile) &&
                Objects.equals(scopeBusiness, that.scopeBusiness) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productFactory, that.productFactory) &&
                Objects.equals(productPlace, that.productPlace) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(modelMatCode, that.modelMatCode) &&
                Objects.equals(modelMatName, that.modelMatName) &&
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
                Objects.equals(weight, that.weight) &&
                Objects.equals(validPeriodUnit, that.validPeriodUnit) &&
                Objects.equals(weightUnit, that.weightUnit) &&
                Objects.equals(mbdId, that.mbdId) &&
                Objects.equals(commonName, that.commonName) &&
                Objects.equals(introduction, that.introduction) &&
                Objects.equals(keyWord, that.keyWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wmsModelId, wzId, purchaseType, mdWmsMiId, applicantName, phoneNumber, corporateDomicile, scopeBusiness, productName, productFactory, productPlace, brand, modelMatCode, modelMatName, pyName, money1, money2, money3, money4, money5, number1, number2, number3, number4, number5, basicUnit, batchRule, validPeriod, alertDay, norm, matType, matType1, matType2, matType3, labelInfo, lessenFilecode, describe1, describe2, barCode, barCodeFilecode, state, wzState, basicUnitAccuracy, backPrinting, serialNumber, createRen, createDate, editRen, editDate, xid, cfca01Filecode, cfca02Filecode, cfca03Filecode, cfca04Filecode, cfca05Filecode, cfca06Filecode, cfca01Date, cfca02Date, aliasName, inventoryLocation, mdpId, mdpsId, basicCoefficent, splitUnit, valiedDate, standard, materielName, ingredient, productUse, batchCode, remark, weight, validPeriodUnit, weightUnit, mbdId, commonName, introduction, keyWord);
    }

    private String searchName;
    private String mbdIds;
    private String imgIds;
    private String matTypeName1;
    private String matTypeName2;
    private String matTypeName3;
    private String mbdName;
    private String brandName;
    private String rootPath;

    @Transient
    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Transient
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Formula("(select a.mbd_name from md_materiel_brand a where a.mbd_id = mbd_id)")
    public String getMbdName() {
        return mbdName;
    }

    public void setMbdName(String mbdName) {
        this.mbdName = mbdName;
    }

    @Formula("(select a.mmt_name from md_materiel_type a where a.mmt_id = mat_type1)")
    public String getMatTypeName1() {
        return matTypeName1;
    }

    public void setMatTypeName1(String matTypeName1) {
        this.matTypeName1 = matTypeName1;
    }

    @Formula("(select a.mmt_name from md_materiel_type a where a.mmt_id = mat_type2 and a.md_mmt_id = mat_type1)")
    public String getMatTypeName2() {
        return matTypeName2;
    }

    public void setMatTypeName2(String matTypeName2) {
        this.matTypeName2 = matTypeName2;
    }

    @Formula("(select a.mmt_name from md_materiel_type a where a.mmt_id = mat_type3 and a.md_mmt_id = mat_type2)")
    public String getMatTypeName3() {
        return matTypeName3;
    }

    public void setMatTypeName3(String matTypeName3) {
        this.matTypeName3 = matTypeName3;
    }

    @Transient
    public String getImgIds() {
        return imgIds;
    }

    public void setImgIds(String imgIds) {
        this.imgIds = imgIds;
    }

    @Transient
    public String getMbdIds() {
        return mbdIds;
    }

    public void setMbdIds(String mbdIds) {
        this.mbdIds = mbdIds;
    }

    @Transient
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public void setRefCount(Integer refCount) {
        this.refCount = refCount;
    }
    @Transient
    public Integer getRefCount() {
        return refCount;
    }

    public void setPathList(List<String> pathList) {
        this.pathList = pathList;
    }
    @Transient
    public List<String> getPathList() {
        return pathList;
    }

    public void setBrandList(List<String> brandList) {
        this.brandList = brandList;
    }
    @Transient
    public List<String> getBrandList() {
        return brandList;
    }

    public void setCanSearch(Integer canSearch) {
        this.canSearch = canSearch;
    }
    @Column(name = "can_search")
    public Integer getCanSearch() {
        return canSearch;
    }
}
