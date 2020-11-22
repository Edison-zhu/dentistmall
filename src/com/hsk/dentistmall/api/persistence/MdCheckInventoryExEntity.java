package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/5/26 14:54
 */
@Entity
@Table(name = "md_check_inventory_ex")
public class MdCheckInventoryExEntity {
    private Integer cieId;
    private Integer mieId;
    private Integer checkInventory;
    private String checkRemark;
    private Integer nowNumber;
    private Integer nowSplitNumber;
    private Integer ciId;
    private Double baseNumber;
    private Double splitNumber;
    private Integer isCheck;
    private Integer wiId;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cie_id")
    public Integer getCieId() {
        return cieId;
    }

    public void setCieId(Integer cieId) {
        this.cieId = cieId;
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
    @Column(name = "is_check")
    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdCheckInventoryExEntity that = (MdCheckInventoryExEntity) o;
        return cieId == that.cieId &&
                nowSplitNumber == that.nowSplitNumber &&
                Objects.equals(mieId, that.mieId) &&
                Objects.equals(checkInventory, that.checkInventory) &&
                Objects.equals(checkRemark, that.checkRemark) &&
                Objects.equals(nowNumber, that.nowNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cieId, mieId, checkInventory, checkRemark, nowNumber, nowSplitNumber);
    }

    @Column(name = "wi_id")
    public Integer getWiId() {
        return wiId;
    }

    public void setWiId(Integer wiId) {
        this.wiId = wiId;
    }
}
