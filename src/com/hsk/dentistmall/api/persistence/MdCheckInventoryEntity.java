package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/5/26 11:23
 */
@Entity
@Table(name = "md_check_inventory")
public class MdCheckInventoryEntity {
    private Integer ciId;
    private Integer rbaId;
    private Integer rbsId;
    private Integer rbbId;
    private String checkName;
    private String checkCode;
    private String checkType;
    private String checkPart;
    private String checkParts;
    private String remark;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;
    private String matName;
    private String brand;
    private String batchCode;

    private String likeTab;
    private String startDate;
    private Integer allCheck;
    private Integer checkCount;
    private Integer noCheckCount;
    private String excludeCiIds;
    private String checkTypeStr;
    private String checkPartCombine;

    @Transient
    public String getCheckPartCombine() {
        return checkPartCombine;
    }

    public void setCheckPartCombine(String checkPartCombine) {
        this.checkPartCombine = checkPartCombine;
    }

    @Formula("(SELECT count(a.ci_id) FROM md_check_inventory_ex a WHERE a.ci_id= ci_id)")
    public Integer getAllCheck() {
        return allCheck;
    }

    public void setAllCheck(Integer allCheck) {
        this.allCheck = allCheck;
    }

    @Formula("(SELECT count(a.ci_id)  FROM md_check_inventory_ex a WHERE a.ci_id= ci_id and a.check_inventory = 1)")
    public Integer getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(Integer checkCount) {
        this.checkCount = checkCount;
    }

    @Formula("(SELECT count(a.ci_id)  FROM md_check_inventory_ex a WHERE a.ci_id= ci_id and (a.check_inventory <> 1 or a.check_inventory is null))")
    public Integer getNoCheckCount() {
        return noCheckCount;
    }

    public void setNoCheckCount(Integer noCheckCount) {
        this.noCheckCount = noCheckCount;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ci_id")
    public Integer getCiId() {
        return ciId;
    }

    public void setCiId(Integer ciId) {
        this.ciId = ciId;
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
    @Column(name = "check_name")
    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    @Basic
    @Column(name = "check_code")
    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @Basic
    @Column(name = "check_type")
    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    @Basic
    @Column(name = "check_part")
    public String getCheckPart() {
        return checkPart;
    }

    public void setCheckPart(String checkPart) {
        this.checkPart = checkPart;
    }

    @Column(name = "check_type_s")
    public String getCheckParts() {
        return checkParts;
    }

    public void setCheckParts(String checkParts) {
        this.checkParts = checkParts;
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
    @Column(name = "mat_name")
    public String getMatName() {
        return matName;
    }

    public void setMatName(String mddName) {
        this.matName = mddName;
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
    @Column(name = "batch_code")
    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    @Transient
    public String getLikeTab() {
        return likeTab;
    }

    public void setLikeTab(String likeTab) {
        this.likeTab = likeTab;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdCheckInventoryEntity that = (MdCheckInventoryEntity) o;
        return ciId == that.ciId &&
                Objects.equals(rbaId, that.rbaId) &&
                Objects.equals(rbsId, that.rbsId) &&
                Objects.equals(rbbId, that.rbbId) &&
                Objects.equals(checkCode, that.checkCode) &&
                Objects.equals(checkType, that.checkType) &&
                Objects.equals(checkPart, that.checkPart) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editRen, that.editRen) &&
                Objects.equals(editDate, that.editDate) &&
                Objects.equals(matName, that.matName) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(batchCode, that.batchCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ciId, rbaId, rbsId, rbbId, checkCode, checkType, checkPart, remark, createRen, createDate, editRen, editDate, matName, brand, batchCode);
    }

    @Transient
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    public void setExcludeCiIds(String excludeCiIds) {
        this.excludeCiIds = excludeCiIds;
    }
    @Transient
    public String getExcludeCiIds() {
        return excludeCiIds;
    }

    @Transient
    public String getCheckTypeStr() {
        return checkTypeStr;
    }

    public void setCheckTypeStr(String checkTypeStr) {
        this.checkTypeStr = checkTypeStr;
    }
}
