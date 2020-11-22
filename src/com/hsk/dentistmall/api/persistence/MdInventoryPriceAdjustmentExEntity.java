package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/6/2 8:10
 */
@Entity
@Table(name = "md_inventory_price_adjustment_ex")
public class MdInventoryPriceAdjustmentExEntity {
    private Integer paieId;
    private Integer paiId;
    private Integer mmfId;
    private Integer mdpId;
    private Integer mdpsId;
    private Integer wmsMiId;
    private Integer mieId;
    private String baseUnit;
    private Double avgPrice;
    private Double origionRetailPrice;
    private Double retailPrice;
    private Double percent;
    private String mmfCode;
    private String mmfName;

    @Id
    @GeneratedValue(strategy = IDENTITY)
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

    @Basic
    @Column(name = "mmf_id")
    public Integer getMmfId() {
        return mmfId;
    }

    public void setMmfId(Integer mmfId) {
        this.mmfId = mmfId;
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
    @Column(name = "wms_mi_id")
    public Integer getWmsMiId() {
        return wmsMiId;
    }

    public void setWmsMiId(Integer wmsMiId) {
        this.wmsMiId = wmsMiId;
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
    @Column(name = "base_unit")
    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdInventoryPriceAdjustmentExEntity that = (MdInventoryPriceAdjustmentExEntity) o;
        return paieId == that.paieId &&
                Objects.equals(paiId, that.paiId) &&
                Objects.equals(mmfId, that.mmfId) &&
                Objects.equals(mdpId, that.mdpId) &&
                Objects.equals(mdpsId, that.mdpsId) &&
                Objects.equals(wmsMiId, that.wmsMiId) &&
                Objects.equals(mieId, that.mieId) &&
                Objects.equals(baseUnit, that.baseUnit) &&
                Objects.equals(avgPrice, that.avgPrice) &&
                Objects.equals(origionRetailPrice, that.origionRetailPrice) &&
                Objects.equals(retailPrice, that.retailPrice) &&
                Objects.equals(percent, that.percent) &&
                Objects.equals(mmfCode, that.mmfCode) &&
                Objects.equals(mmfName, that.mmfName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paieId, paiId, mmfId, mdpId, mdpsId, wmsMiId, mieId, baseUnit, avgPrice, origionRetailPrice, retailPrice, percent, mmfCode, mmfName);
    }
}
