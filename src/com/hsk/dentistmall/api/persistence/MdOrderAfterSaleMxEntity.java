package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2019/12/26 14:26
 */
@Entity
@Table(name = "md_order_after_sale_mx")
public class MdOrderAfterSaleMxEntity {
    private Integer masMxId;
    private Integer momId;
    private Integer masId;
    private Integer wmsMiId;
    private Integer mmfId;
    private String matCode;
    private String matName;
    private String norm;
    private String basicUnit;
    private Double price;
    private Double baseNumber;
    private Double originNumber;
    private String supplier;
    private String remarks;
    private Date editDate;
    private String editRen;
    private Date createDate;
    private String createRen;
    private Integer afterSale;

    /**
     * 与表无关字段
     * @return
     */
    private String afterSaleName;

    @Id
    @Column(name = "mas_mx_id")
    @GeneratedValue(strategy = IDENTITY)
    public Integer getMasMxId() {
        return masMxId;
    }

    public void setMasMxId(Integer masMxId) {
        this.masMxId = masMxId;
    }

    @Basic
    @Column(name = "mom_id")
    public Integer getMomId() {
        return momId;
    }

    public void setMomId(Integer momId) {
        this.momId = momId;
    }

    @Basic
    @Column(name = "mas_id")
    public Integer getMasId() {
        return masId;
    }

    public void setMasId(Integer masId) {
        this.masId = masId;
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
    @Column(name = "norm")
    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
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
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
    @Column(name = "origin_number")
    public Double getOriginNumber() {
        return originNumber;
    }

    public void setOriginNumber(Double originNumber) {
        this.originNumber = originNumber;
    }

    @Basic
    @Column(name = "SUPPLIER")
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Basic
    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
    @Column(name = "edit_ren")
    public String getEditRen() {
        return editRen;
    }

    public void setEditRen(String editRen) {
        this.editRen = editRen;
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
    @Column(name = "after_sale")
    public Integer getAfterSale() {
        return afterSale;
    }

    public void setAfterSale(Integer afterSale) {
        this.afterSale = afterSale;
    }

    @Formula("(SELECT a.param_name FROM sys_parameter a,sys_parameter b WHERE a.param_value= after_sale and a.sys_spar_id=b.spar_id and b.param_code='PAR191224031732706')")
    public String getAfterSaleName() {
        return afterSaleName;
    }

    public void setAfterSaleName(String afterSaleName) {
        this.afterSaleName = afterSaleName;
    }
}
