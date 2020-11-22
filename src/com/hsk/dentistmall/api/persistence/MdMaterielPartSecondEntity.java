package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/5/12 9:25
 */
@Entity
@Table(name = "md_materiel_part_second")
public class MdMaterielPartSecondEntity {
    private Integer mdpsId;
    private String mdpsCode;
    private String mdpsName;
    private Integer mdpId;
    private Integer seq;
    private Integer needShow;
    private Integer suiId;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;
    private Integer hasChild;

    private String orderStr;

    private String secondPartCode;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mdps_id")
    public Integer getMdpsId() {
        return mdpsId;
    }

    public void setMdpsId(Integer mdpsId) {
        this.mdpsId = mdpsId;
    }

    @Basic
    @Column(name = "mdps_code")
    public String getMdpsCode() {
        return mdpsCode;
    }

    public void setMdpsCode(String mdpsCode) {
        this.mdpsCode = mdpsCode;
    }

    @Basic
    @Column(name = "mdps_name")
    public String getMdpsName() {
        return mdpsName;
    }

    public void setMdpsName(String mdpsName) {
        this.mdpsName = mdpsName;
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

    @Column(name = "second_part_code")
    public String getSecondPartCode() {
        return secondPartCode;
    }

    public void setSecondPartCode(String secondPartCode) {
        this.secondPartCode = secondPartCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MdMaterielPartSecondEntity that = (MdMaterielPartSecondEntity) o;

        if (mdpsId != that.mdpsId) return false;
        if (mdpsCode != null ? !mdpsCode.equals(that.mdpsCode) : that.mdpsCode != null) return false;
        if (mdpsName != null ? !mdpsName.equals(that.mdpsName) : that.mdpsName != null) return false;
        if (seq != null ? !seq.equals(that.seq) : that.seq != null) return false;
        if (needShow != null ? !needShow.equals(that.needShow) : that.needShow != null) return false;
        if (suiId != null ? !suiId.equals(that.suiId) : that.suiId != null) return false;
        if (createRen != null ? !createRen.equals(that.createRen) : that.createRen != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (editRen != null ? !editRen.equals(that.editRen) : that.editRen != null) return false;
        if (editDate != null ? !editDate.equals(that.editDate) : that.editDate != null) return false;
        if (hasChild != null ? !hasChild.equals(that.hasChild) : that.hasChild != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mdpsId;
        result = 31 * result + (mdpsCode != null ? mdpsCode.hashCode() : 0);
        result = 31 * result + (mdpsName != null ? mdpsName.hashCode() : 0);
        result = 31 * result + (seq != null ? seq.hashCode() : 0);
        result = 31 * result + (needShow != null ? needShow.hashCode() : 0);
        result = 31 * result + (suiId != null ? suiId.hashCode() : 0);
        result = 31 * result + (createRen != null ? createRen.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (editRen != null ? editRen.hashCode() : 0);
        result = 31 * result + (editDate != null ? editDate.hashCode() : 0);
        result = 31 * result + (hasChild != null ? hasChild.hashCode() : 0);
        return result;
    }
}
