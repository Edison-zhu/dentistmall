package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/5/31 14:57
 */
@Entity
@Table(name = "md_inventory_merge_log")
public class MdInventoryMergeLogEntity {
    private Integer miMeId;
    private Integer wiId;
    private String matName;
    private Integer wmsMiId;
    private Integer mmfId;
    private String mmfName;
    private String logo;
    private Double baseNumber;
    private String basicUnit;
    private String unit;
    private String brand;
    private String productFactory;
    private Double avgPrice;
    private Double retailPrice;
    private Date createDate;
    private String createRen;
    private Integer suiId;
    private String mmfCode;
    private Integer mdpId;
    private Integer mdpsId;
    private Double curNumber;

    private String mmfCode1;
    private String logoPath;

    private String searchName;

    private Double quantity;

    @Column(name = "quantity")
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mi_me_id")
    public Integer getMiMeId() {
        return miMeId;
    }

    public void setMiMeId(Integer miMeId) {
        this.miMeId = miMeId;
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
    @Column(name = "mat_name")
    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
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
    @Column(name = "mmf_name")
    public String getMmfName() {
        return mmfName;
    }

    public void setMmfName(String mmfName) {
        this.mmfName = mmfName;
    }

    @Basic
    @Column(name = "logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
    @Column(name = "basic_unit")
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
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
    @Column(name = "avg_price")
    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
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
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
    @Column(name = "sui_id")
    public Integer getSuiId() {
        return suiId;
    }

    public void setSuiId(Integer suiId) {
        this.suiId = suiId;
    }

    @Column(name = "mmf_code")
    public String getMmfCode1() {
        return mmfCode1;
    }

    public void setMmfCode1(String mmfCode1) {
        this.mmfCode1 = mmfCode1;
    }

    @Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code = (select b.lessen_filecode from md_materiel_info b where b.wms_mi_id = wms_mi_id))")
    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    @Formula("(SELECT a.mmf_code FROM md_materiel_format a WHERE a.mmf_id = mmf_id)")
    public String getMmfCode() {
        return mmfCode;
    }

    public void setMmfCode(String mmfCode) {
        this.mmfCode = mmfCode;
    }

    @Column(name = "mdp_id")
    public Integer getMdpId() {
        return mdpId;
    }

    public void setMdpId(Integer mdpId) {
        this.mdpId = mdpId;
    }

    @Column(name = "mdps_id")
    public Integer getMdpsId() {
        return mdpsId;
    }

    public void setMdpsId(Integer mdpsId) {
        this.mdpsId = mdpsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdInventoryMergeLogEntity that = (MdInventoryMergeLogEntity) o;
        return miMeId == that.miMeId &&
                Objects.equals(wiId, that.wiId) &&
                Objects.equals(matName, that.matName) &&
                Objects.equals(wmsMiId, that.wmsMiId) &&
                Objects.equals(mmfId, that.mmfId) &&
                Objects.equals(mmfName, that.mmfName) &&
                Objects.equals(logo, that.logo) &&
                Objects.equals(baseNumber, that.baseNumber) &&
                Objects.equals(basicUnit, that.basicUnit) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(productFactory, that.productFactory) &&
                Objects.equals(avgPrice, that.avgPrice) &&
                Objects.equals(retailPrice, that.retailPrice) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(suiId, that.suiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(miMeId, wiId, matName, wmsMiId, mmfId, mmfName, logo, baseNumber, basicUnit, unit, brand, productFactory, avgPrice, retailPrice, createDate, createRen, suiId);
    }

    public void setCurNumber(Double curNumber) {
        this.curNumber = curNumber;
    }
    @Column(name = "cur_number")
    public Double getCurNumber() {
        return curNumber;
    }

    @Transient
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
}
