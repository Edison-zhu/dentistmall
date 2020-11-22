package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/5/27 8:35
 */
@Entity
@Table(name = "md_inventory_price_adjustment")
public class MdInventoryPriceAdjustmentEntity {
    private Integer paiId;
    private String paiCode;
    private String paiType;
    private Double paiPercent;
    private String remark;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;
    private Integer suiId;
    private Integer rbaId;
    private Integer rbsId;
    private Integer rbbId;

    private String startDate;
    private String endDate;

    private String searchName;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "pai_id")
    public Integer getPaiId() {
        return paiId;
    }

    public void setPaiId(Integer paiId) {
        this.paiId = paiId;
    }

    @Basic
    @Column(name = "pai_code")
    public String getPaiCode() {
        return paiCode;
    }

    public void setPaiCode(String paiCode) {
        this.paiCode = paiCode;
    }

    @Basic
    @Column(name = "pai_type")
    public String getPaiType() {
        return paiType;
    }

    public void setPaiType(String paiType) {
        this.paiType = paiType;
    }

    @Basic
    @Column(name = "pai_percent")
    public Double getPaiPercent() {
        return paiPercent;
    }

    public void setPaiPercent(Double paiPercent) {
        this.paiPercent = paiPercent;
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
    @Column(name = "sui_id")
    public Integer getSuiId() {
        return suiId;
    }

    public void setSuiId(Integer suiId) {
        this.suiId = suiId;
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

    @Transient
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Transient
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Transient
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdInventoryPriceAdjustmentEntity that = (MdInventoryPriceAdjustmentEntity) o;
        return paiId == that.paiId &&
                Objects.equals(paiCode, that.paiCode) &&
                Objects.equals(paiType, that.paiType) &&
                Objects.equals(paiPercent, that.paiPercent) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editRen, that.editRen) &&
                Objects.equals(editDate, that.editDate) &&
                Objects.equals(suiId, that.suiId) &&
                Objects.equals(rbaId, that.rbaId) &&
                Objects.equals(rbsId, that.rbsId) &&
                Objects.equals(rbbId, that.rbbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paiId, paiCode, paiType, paiPercent, remark, createRen, createDate, editRen, editDate, suiId, rbaId, rbsId, rbbId);
    }
}
