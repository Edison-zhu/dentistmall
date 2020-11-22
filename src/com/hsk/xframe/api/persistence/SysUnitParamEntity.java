package com.hsk.xframe.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/5/9 16:17
 */
@Entity
@Table(name = "sys_unit_param")
public class SysUnitParamEntity {
    private Integer upId;
    private Integer supId;
    private String unitName;
    private Integer needShow;
    private String belongType;
    private String createRen;
    private Date createDate;
    private Integer suiId;
    private String editeRen;
    private Date editeDate;
    private Integer state;
    private String unitType;
    private String unitCode;
    private Integer isLink;

    private String sUnitName;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "up_id")
    public Integer getUpId() {
        return upId;
    }

    public void setUpId(Integer upId) {
        this.upId = upId;
    }

    @Column(name = "sup_id")
    public Integer getSupId() {
        return supId;
    }

    public void setSupId(Integer supId) {
        this.supId = supId;
    }

    @Basic
    @Column(name = "unit_name")
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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
    @Column(name = "belong_type")
    public String getBelongType() {
        return belongType;
    }

    public void setBelongType(String belongType) {
        this.belongType = belongType;
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
    @Column(name = "sui_id")
    public Integer getSuiId() {
        return suiId;
    }

    public void setSuiId(Integer suiId) {
        this.suiId = suiId;
    }

    @Basic
    @Column(name = "edite_ren")
    public String getEditeRen() {
        return editeRen;
    }

    public void setEditeRen(String editeRen) {
        this.editeRen = editeRen;
    }

    @Basic
    @Column(name = "edite_date")
    public Date getEditeDate() {
        return editeDate;
    }

    public void setEditeDate(Date editeDate) {
        this.editeDate = editeDate;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "unit_type")
    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    @Basic
    @Column(name = "unit_code")
    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @Column(name = "is_link")
    public Integer getIsLink() {
        return isLink;
    }

    public void setIsLink(Integer isLink) {
        this.isLink = isLink;
    }

    @Formula("(SELECT a.unit_name FROM sys_unit_param a WHERE a.up_id= sup_id)")
    public String getSUnitName() {
        return sUnitName;
    }

    public void setSUnitName(String sUnitName) {
        this.sUnitName = sUnitName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUnitParamEntity that = (SysUnitParamEntity) o;

        if (upId != that.upId) return false;
        if (unitName != null ? !unitName.equals(that.unitName) : that.unitName != null) return false;
        if (needShow != null ? !needShow.equals(that.needShow) : that.needShow != null) return false;
        if (belongType != null ? !belongType.equals(that.belongType) : that.belongType != null) return false;
        if (createRen != null ? !createRen.equals(that.createRen) : that.createRen != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (suiId != null ? !suiId.equals(that.suiId) : that.suiId != null) return false;
        if (editeRen != null ? !editeRen.equals(that.editeRen) : that.editeRen != null) return false;
        if (editeDate != null ? !editeDate.equals(that.editeDate) : that.editeDate != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (unitType != null ? !unitType.equals(that.unitType) : that.unitType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = upId;
        result = 31 * result + (unitName != null ? unitName.hashCode() : 0);
        result = 31 * result + (needShow != null ? needShow.hashCode() : 0);
        result = 31 * result + (belongType != null ? belongType.hashCode() : 0);
        result = 31 * result + (createRen != null ? createRen.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (suiId != null ? suiId.hashCode() : 0);
        result = 31 * result + (editeRen != null ? editeRen.hashCode() : 0);
        result = 31 * result + (editeDate != null ? editeDate.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (unitType != null ? unitType.hashCode() : 0);
        return result;
    }
}
