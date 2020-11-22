package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * author: yangfeng
 * time: 2020/6/2 21:30
 */
@Entity
@Table(name = "md_inventory_check_view")
public class MdInventoryCheckViewEntity {
    private Integer cieId;
    private Integer mieId1;
    private Integer checkInventory;
    private String checkRemark;
    private Integer nowNumber;
    private Integer nowSplitNumber;
    private Integer ciId;
    private Double baseNumber;
    private Double splitNumber;
    private Integer mieId;
    private Integer wiId;
    private Integer wmsMiId;
    private Integer mmfId;
    private Double price;
    private Double basePrice;
    private Double quantity;
    private String basicUnit;
    private String unit;
    private Double baseNumber1;
    private String relatedCode;
    private String purchaseUser;
    private Date createDate;
    private Date editDate;
    private String matName;
    private String mmfName;
    private String matName2;
    private String norm2;
    private String matType;
    private String matType1;
    private String matType2;
    private String matType3;
    private String productName;
    private String brand;
    private String labelInfo;
    private String applicantName;
    private String state;
    private Integer mdpId;
    private Integer mdpsId;
    private Double splitQuantity;
    private Integer rbaId;
    private Integer rbsId;
    private Integer rbbId;
    private String purchaseType;
    private Double allNumber;
    private Double warningShu;
    private String itemKeyId;
    private Double maxShu;
    private Integer minDay;
    private String typeName;
    private Double avgPrice;
    private Integer isCheck;

    private String mmfCode;
    private String mdpName;
    private String mdpsName;
    private String searchName;

    private Double ratio;
    private String batchCode;

    @Basic
    @Id
    @Column(name = "cie_id")
    public Integer getCieId() {
        return cieId;
    }

    public void setCieId(Integer cieId) {
        this.cieId = cieId;
    }

    @Basic
    @Column(name = "mie_id1")
    public Integer getMieId1() {
        return mieId1;
    }

    public void setMieId1(Integer mieId1) {
        this.mieId1 = mieId1;
    }

    @Basic
    @Column(name = "check_inventory")
    public Integer getCheckInventory() {
        return checkInventory;
    }

    public void setCheckInventory(Integer checkInventory) {
        this.checkInventory = checkInventory;
    }

    @Basic
    @Column(name = "check_remark")
    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    @Basic
    @Column(name = "now_number")
    public Integer getNowNumber() {
        return nowNumber;
    }

    public void setNowNumber(Integer nowNumber) {
        this.nowNumber = nowNumber;
    }

    @Basic
    @Column(name = "now_split_number")
    public Integer getNowSplitNumber() {
        return nowSplitNumber;
    }

    public void setNowSplitNumber(Integer nowSplitNumber) {
        this.nowSplitNumber = nowSplitNumber;
    }

    @Basic
    @Column(name = "ci_id")
    public Integer getCiId() {
        return ciId;
    }

    public void setCiId(Integer ciId) {
        this.ciId = ciId;
    }

    @Basic
    @Column(name = "base_number")
    public Double getBaseNumber() {
        return baseNumber;
    }

    public void setBaseNumber(Double baseNumber) {
        this.baseNumber = baseNumber;
    }

    @Basic
    @Column(name = "split_number")
    public Double getSplitNumber() {
        return splitNumber;
    }

    public void setSplitNumber(Double splitNumber) {
        this.splitNumber = splitNumber;
    }

    @Basic
    @Column(name = "mie_id")
    public Integer getMieId() {
        return mieId;
    }

    public void setMieId(Integer mieId) {
        this.mieId = mieId;
    }

    @Basic
    @Column(name = "wi_id")
    public Integer getWiId() {
        return wiId;
    }

    public void setWiId(Integer wiId) {
        this.wiId = wiId;
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
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "base_price")
    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    @Basic
    @Column(name = "QUANTITY")
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "base_number1")
    public Double getBaseNumber1() {
        return baseNumber1;
    }

    public void setBaseNumber1(Double baseNumber1) {
        this.baseNumber1 = baseNumber1;
    }

    @Basic
    @Column(name = "related_code")
    public String getRelatedCode() {
        return relatedCode;
    }

    public void setRelatedCode(String relatedCode) {
        this.relatedCode = relatedCode;
    }

    @Basic
    @Column(name = "purchase_user")
    public String getPurchaseUser() {
        return purchaseUser;
    }

    public void setPurchaseUser(String purchaseUser) {
        this.purchaseUser = purchaseUser;
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
    @Column(name = "edit_date")
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
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
    @Column(name = "mmf_name")
    public String getMmfName() {
        return mmfName;
    }

    public void setMmfName(String mmfName) {
        this.mmfName = mmfName;
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
    @Column(name = "Label_info")
    public String getLabelInfo() {
        return labelInfo;
    }

    public void setLabelInfo(String labelInfo) {
        this.labelInfo = labelInfo;
    }

    @Basic
    @Column(name = "applicant_name")
    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
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
    @Column(name = "split_quantity")
    public Double getSplitQuantity() {
        return splitQuantity;
    }

    public void setSplitQuantity(Double splitQuantity) {
        this.splitQuantity = splitQuantity;
    }

    @Basic
    @Column(name = "rba_id")
    public Integer getRbaId() {
        return rbaId;
    }

    public void setRbaId(Integer rbaId) {
        this.rbaId = rbaId;
    }

    @Basic
    @Column(name = "rbs_id")
    public Integer getRbsId() {
        return rbsId;
    }

    public void setRbsId(Integer rbsId) {
        this.rbsId = rbsId;
    }

    @Basic
    @Column(name = "rbb_id")
    public Integer getRbbId() {
        return rbbId;
    }

    public void setRbbId(Integer rbbId) {
        this.rbbId = rbbId;
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
    @Column(name = "all_number")
    public Double getAllNumber() {
        return allNumber;
    }

    public void setAllNumber(Double allNumber) {
        this.allNumber = allNumber;
    }

    @Basic
    @Column(name = "warning_shu")
    public Double getWarningShu() {
        return warningShu;
    }

    public void setWarningShu(Double warningShu) {
        this.warningShu = warningShu;
    }

    @Basic
    @Column(name = "item_key_id")
    public String getItemKeyId() {
        return itemKeyId;
    }

    public void setItemKeyId(String itemKeyId) {
        this.itemKeyId = itemKeyId;
    }

    @Basic
    @Column(name = "max_shu")
    public Double getMaxShu() {
        return maxShu;
    }

    public void setMaxShu(Double maxShu) {
        this.maxShu = maxShu;
    }

    @Basic
    @Column(name = "min_day")
    public Integer getMinDay() {
        return minDay;
    }

    public void setMinDay(Integer minDay) {
        this.minDay = minDay;
    }

    @Basic
    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Basic
    @Column(name = "avg_price")
    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    @Basic
    @Column(name = "is_check")
    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    @Formula("(SELECT a.mmf_code FROM md_materiel_format a WHERE a.mmf_id= mmf_id)")
    public String getMmfCode() {
        return mmfCode;
    }

    public void setMmfCode(String mmfCode) {
        this.mmfCode = mmfCode;
    }

    @Formula("(SELECT a.mdp_name FROM md_materiel_part a WHERE a.mdp_id= mdp_id)")
    public String getMdpName() {
        return mdpName;
    }

    public void setMdpName(String mdpName) {
        this.mdpName = mdpName;
    }

    @Formula("(SELECT a.mdps_name FROM md_materiel_part_second a WHERE a.mdps_id= mdps_id)")
    public String getMdpsName() {
        return mdpsName;
    }

    public void setMdpsName(String mdpsName) {
        this.mdpsName = mdpsName;
    }

    @Transient
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    @Column(name = "ratio")
    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    @Column(name = "batch_code")
    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdInventoryCheckViewEntity that = (MdInventoryCheckViewEntity) o;
        return cieId == that.cieId &&
                nowSplitNumber == that.nowSplitNumber &&
                Objects.equals(mieId1, that.mieId1) &&
                Objects.equals(checkInventory, that.checkInventory) &&
                Objects.equals(checkRemark, that.checkRemark) &&
                Objects.equals(nowNumber, that.nowNumber) &&
                Objects.equals(ciId, that.ciId) &&
                Objects.equals(baseNumber, that.baseNumber) &&
                Objects.equals(splitNumber, that.splitNumber) &&
                Objects.equals(mieId, that.mieId) &&
                Objects.equals(wiId, that.wiId) &&
                Objects.equals(wmsMiId, that.wmsMiId) &&
                Objects.equals(mmfId, that.mmfId) &&
                Objects.equals(price, that.price) &&
                Objects.equals(basePrice, that.basePrice) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(basicUnit, that.basicUnit) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(baseNumber1, that.baseNumber1) &&
                Objects.equals(relatedCode, that.relatedCode) &&
                Objects.equals(purchaseUser, that.purchaseUser) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editDate, that.editDate) &&
                Objects.equals(matName, that.matName) &&
                Objects.equals(mmfName, that.mmfName) &&
                Objects.equals(matName2, that.matName2) &&
                Objects.equals(norm2, that.norm2) &&
                Objects.equals(matType, that.matType) &&
                Objects.equals(matType1, that.matType1) &&
                Objects.equals(matType2, that.matType2) &&
                Objects.equals(matType3, that.matType3) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(labelInfo, that.labelInfo) &&
                Objects.equals(applicantName, that.applicantName) &&
                Objects.equals(state, that.state) &&
                Objects.equals(mdpId, that.mdpId) &&
                Objects.equals(mdpsId, that.mdpsId) &&
                Objects.equals(splitQuantity, that.splitQuantity) &&
                Objects.equals(rbaId, that.rbaId) &&
                Objects.equals(rbsId, that.rbsId) &&
                Objects.equals(rbbId, that.rbbId) &&
                Objects.equals(purchaseType, that.purchaseType) &&
                Objects.equals(allNumber, that.allNumber) &&
                Objects.equals(warningShu, that.warningShu) &&
                Objects.equals(itemKeyId, that.itemKeyId) &&
                Objects.equals(maxShu, that.maxShu) &&
                Objects.equals(minDay, that.minDay) &&
                Objects.equals(typeName, that.typeName) &&
                Objects.equals(avgPrice, that.avgPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cieId, mieId1, checkInventory, checkRemark, nowNumber, nowSplitNumber, ciId, baseNumber, splitNumber, mieId, wiId, wmsMiId, mmfId, price, basePrice, quantity, basicUnit, unit, baseNumber1, relatedCode, purchaseUser, createDate, editDate, matName, mmfName, matName2, norm2, matType, matType1, matType2, matType3, productName, brand, labelInfo, applicantName, state, mdpId, mdpsId, splitQuantity, rbaId, rbsId, rbbId, purchaseType, allNumber, warningShu, itemKeyId, maxShu, minDay, typeName, avgPrice);
    }


    private String checkPart;
    private String checkParts;

    @Transient
    public String getCheckPart() {
        return checkPart;
    }

    public void setCheckPart(String checkPart) {
        this.checkPart = checkPart;
    }

    @Transient
    public String getCheckParts() {
        return checkParts;
    }

    public void setCheckParts(String checkParts) {
        this.checkParts = checkParts;
    }
}
