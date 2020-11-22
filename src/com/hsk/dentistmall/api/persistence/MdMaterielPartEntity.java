package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/5/12 8:09
 */
@Entity
@Table(name = "md_materiel_part")
public class MdMaterielPartEntity {
    private Integer mdpId;
    private String mdpCode;
    private String mdpName;
    private Integer seq;
    private Integer needShow;
    private Integer suiId;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;
    private Integer hasChild;

    private String orderStr;

    private Integer rbaId;
    private Integer rbsId;
    private Integer rbbId;

    private String partCode;
    private Integer isDefault;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mdp_id")
    public Integer getMdpId() {
        return mdpId;
    }

    public void setMdpId(Integer mdpId) {
        this.mdpId = mdpId;
    }

    @Basic
    @Column(name = "mdp_code")
    public String getMdpCode() {
        return mdpCode;
    }

    public void setMdpCode(String mdpCode) {
        this.mdpCode = mdpCode;
    }

    @Basic
    @Column(name = "mdp_name")
    public String getMdpName() {
        return mdpName;
    }

    public void setMdpName(String mdpName) {
        this.mdpName = mdpName;
    }

    @Basic
    @Column(name = "seq")
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Basic
    @Column(name = "need_show")
    public Integer getNeedShow() {
        return needShow;
    }

    public void setNeedShow(Integer needShow) {
        this.needShow = needShow;
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
    @Column(name = "has_child")
    public Integer getHasChild() {
        return hasChild;
    }

    public void setHasChild(Integer hasChild) {
        this.hasChild = hasChild;
    }

    @Transient
    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
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

    @Column(name = "part_code")
    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    @Column(name = "is_default")
    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MdMaterielPartEntity that = (MdMaterielPartEntity) o;

        if (mdpId != that.mdpId) return false;
        if (mdpCode != null ? !mdpCode.equals(that.mdpCode) : that.mdpCode != null) return false;
        if (mdpName != null ? !mdpName.equals(that.mdpName) : that.mdpName != null) return false;
        if (seq != null ? !seq.equals(that.seq) : that.seq != null) return false;
        if (needShow != null ? !needShow.equals(that.needShow) : that.needShow != null) return false;
        if (createRen != null ? !createRen.equals(that.createRen) : that.createRen != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (editRen != null ? !editRen.equals(that.editRen) : that.editRen != null) return false;
        if (editDate != null ? !editDate.equals(that.editDate) : that.editDate != null) return false;
        if (hasChild != null ? !hasChild.equals(that.hasChild) : that.hasChild != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mdpId;
        result = 31 * result + (mdpCode != null ? mdpCode.hashCode() : 0);
        result = 31 * result + (mdpName != null ? mdpName.hashCode() : 0);
        result = 31 * result + (seq != null ? seq.hashCode() : 0);
        result = 31 * result + (needShow != null ? needShow.hashCode() : 0);
        result = 31 * result + (createRen != null ? createRen.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (editRen != null ? editRen.hashCode() : 0);
        result = 31 * result + (editDate != null ? editDate.hashCode() : 0);
        result = 31 * result + (hasChild != null ? hasChild.hashCode() : 0);
        return result;
    }
}
