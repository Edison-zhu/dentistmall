package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/7/31 9:45
 */
@Entity
@Table(name = "md_model_format")
public class MdModelFormatEntity {
    private Integer modelMmfId;
    private Integer wmsModelId;
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
    private String unit;
    private String packingUnit;
    private Double retailPrice;
    private String canSearch;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "model_mmf_id")
    public Integer getModelMmfId() {
        return modelMmfId;
    }

    public void setModelMmfId(Integer modelMmfId) {
        this.modelMmfId = modelMmfId;
    }

    @Basic
    @Column(name = "wms_model_id")
    public Integer getWmsModelId() {
        return wmsModelId;
    }

    public void setWmsModelId(Integer wmsModelId) {
        this.wmsModelId = wmsModelId;
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
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
    @Column(name = "retail_price")
    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdModelFormatEntity that = (MdModelFormatEntity) o;
        return modelMmfId == that.modelMmfId &&
                Objects.equals(wmsModelId, that.wmsModelId) &&
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
                Objects.equals(unit, that.unit) &&
                Objects.equals(packingUnit, that.packingUnit) &&
                Objects.equals(retailPrice, that.retailPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelMmfId, wmsModelId, mmfCode, mmfName, price, remark, state, createRen, createDate, editRen, editDate, applicantName, brand, productName, unit, packingUnit, retailPrice);
    }

    public void setCanSearch(String canSearch) {
        this.canSearch = canSearch;
    }
    @Transient
    public String getCanSearch() {
        return canSearch;
    }
}
