package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * author: yangfeng
 * time: 2020/6/2 15:25
 */
@Entity
@Table(name = "md_inventory_extend_pa_ex")
public class MdInventoryExtendPaExEntity {
    private Integer mmfId;
    private Integer wmsMiId;
    private String mmfCode;
    private String mmfName;
    private Double price;
    private String remark;
    private String state;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;
    private String applicantName;
    private String brand;
    private String productName;
    private String basicUnit;
    private String packingUnit;
    private Integer paieId;
    private Integer paiId;
    private Double avgPrice;
    private Double avgPrice1;
    private Double origionRetailPrice;
    private Double retailPrice;
    private Double percent;
    private Double baseNumber;
    private String batchCode;
    private String backPrinting;
    private String matName;
    private String matCode;
    private Integer rbbId;
    private Integer rbsId;
    private Integer rbaId;
    private Double quantity1;
    private Double ratio;
//    private String purchaseType;

    @Column(name = "rbb_id")
    public Integer getRbbId() {
        return rbbId;
    }

    public void setRbbId(Integer rbbId) {
        this.rbbId = rbbId;
    }

    @Column(name = "rbs_id")
    public Integer getRbsId() {
        return rbsId;
    }

    public void setRbsId(Integer rbsId) {
        this.rbsId = rbsId;
    }

    @Column(name = "rba_id")
    public Integer getRbaId() {
        return rbaId;
    }

    public void setRbaId(Integer rbaId) {
        this.rbaId = rbaId;
    }

//    @Column(name = "purchase_type")
//    public String getPurchaseType() {
//        return purchaseType;
//    }
//
//    public void setPurchaseType(String purchaseType) {
//        this.purchaseType = purchaseType;
//    }

    @Basic
    @Column(name = "mmf_id")
    public Integer getMmfId() {
        return mmfId;
    }

    public void setMmfId(Integer mmfId) {
        this.mmfId = mmfId;
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
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
    @Column(name = "applicant_Name")
    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
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
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "basic_unit")
    public String getBasicUnit() {
        return basicUnit;
    }

    public void setBasicUnit(String basicUnit) {
        this.basicUnit = basicUnit;
    }

    @Basic
    @Column(name = "packing_unit")
    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit;
    }

    @Basic
    @Id
    @Column(name = "paie_id")
    public Integer getPaieId() {
        return paieId;
    }

    public void setPaieId(Integer paieId) {
        this.paieId = paieId;
    }

    @Basic
    @Column(name = "pai_id")
    public Integer getPaiId() {
        return paiId;
    }

    public void setPaiId(Integer paiId) {
        this.paiId = paiId;
    }

    @Transient
    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    @Transient
    public Double getAvgPrice1() {
        return avgPrice1;
    }

    public void setAvgPrice1(Double avgPrice1) {
        this.avgPrice1 = avgPrice1;
    }

    @Basic
    @Column(name = "origion_retail_price")
    public Double getOrigionRetailPrice() {
        return origionRetailPrice;
    }

    public void setOrigionRetailPrice(Double origionRetailPrice) {
        this.origionRetailPrice = origionRetailPrice;
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
    @Column(name = "percent")
    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    @Transient
    public Double getBaseNumber() {
        return baseNumber;
    }

    public void setBaseNumber(Double baseNumber) {
        this.baseNumber = baseNumber;
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
    @Column(name = "back_Printing")
    public String getBackPrinting() {
        return backPrinting;
    }

    public void setBackPrinting(String backPrinting) {
        this.backPrinting = backPrinting;
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
    @Column(name = "mat_code")
    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdInventoryExtendPaExEntity that = (MdInventoryExtendPaExEntity) o;
        return paieId == that.paieId &&
                Objects.equals(mmfId, that.mmfId) &&
                Objects.equals(wmsMiId, that.wmsMiId) &&
                Objects.equals(mmfCode, that.mmfCode) &&
                Objects.equals(mmfName, that.mmfName) &&
                Objects.equals(price, that.price) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(state, that.state) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editRen, that.editRen) &&
                Objects.equals(editDate, that.editDate) &&
                Objects.equals(applicantName, that.applicantName) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(basicUnit, that.basicUnit) &&
                Objects.equals(packingUnit, that.packingUnit) &&
                Objects.equals(paiId, that.paiId) &&
                Objects.equals(avgPrice, that.avgPrice) &&
                Objects.equals(origionRetailPrice, that.origionRetailPrice) &&
                Objects.equals(retailPrice, that.retailPrice) &&
                Objects.equals(percent, that.percent) &&
//                Objects.equals(baseNumber, that.baseNumber) &&
                Objects.equals(batchCode, that.batchCode) &&
                Objects.equals(backPrinting, that.backPrinting) &&
                Objects.equals(matName, that.matName) &&
                Objects.equals(matCode, that.matCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mmfId, wmsMiId, mmfCode, mmfName, price, remark, state, createRen, createDate, editRen, editDate, applicantName, brand, productName, basicUnit, packingUnit, paieId, paiId, avgPrice, origionRetailPrice, retailPrice, percent, batchCode, backPrinting, matName, matCode);
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
}
