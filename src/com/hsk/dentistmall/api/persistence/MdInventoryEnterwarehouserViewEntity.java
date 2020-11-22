package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;

/**
 * author: yangfeng
 * time: 2019/12/6 10:43
 */
@Entity
@Table(name = "md_inventory_enterwarehouser_view")
public class MdInventoryEnterwarehouserViewEntity {
    private Integer mieId;
    private Integer wiId;
    private Double baseNumber;
    private String relatedCode;
    private Date createDate;
    private String productFactory;
    private Date productTime;
    private Date productValitime;
    private String productName;
    private String batchCertNo;

    private String brand;
    private Double quantity;
    private Double splitQuantity;
    private String basicUnit;
    private String unit;
    private Double ratio;

    @Column(name = "ratio")
    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    @Column(name = "basic_unit")
    public String getBasicUnit() {
        return basicUnit;
    }

    public void setBasicUnit(String basicUnit) {
        this.basicUnit = basicUnit;
    }

    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "quantity")
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Column(name = "split_quantity")
    public Double getSplitQuantity() {
        return splitQuantity;
    }

    public void setSplitQuantity(Double splitQuantity) {
        this.splitQuantity = splitQuantity;
    }

    @Basic
    @Id
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
    @Column(name = "base_number")
    public Double getBaseNumber() {
        return baseNumber;
    }

    public void setBaseNumber(Double baseNumber) {
        this.baseNumber = baseNumber;
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
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "batch_certNo")
    public String getBatchCertNo() {
        return batchCertNo;
    }

    public void setBatchCertNo(String batchCertNo) {
        this.batchCertNo = batchCertNo;
    }
}
