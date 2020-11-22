package com.hsk.xframe.api.persistence;

import com.hsk.dentistmall.api.persistence.MdMaterielInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/3/24 13:53
 */
@Entity
@Table(name = "sys_same_product")
public class SysSameProductEntity implements Serializable {
    private Integer id;
    private String sysSameProductCode;
    private String wmsMiId;
    private Integer spState;
    private Date createDate;
    private Date editDate;
    private String createRen;
    private String editRen;

    private String wmsMiIdString;
    private String applicationName;
    private List<Map<String, Object>> materielList;
    private Integer count;
    private Integer state;
    private MdMaterielInfo materiel;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sys_same_product_code")
    public String getSysSameProductCode() {
        return sysSameProductCode;
    }

    public void setSysSameProductCode(String sysSameProductCode) {
        this.sysSameProductCode = sysSameProductCode;
    }

    @Basic
    @Column(name = "wms_mi_id")
    public String getWmsMiId() {
        return wmsMiId;
    }

    public void setWmsMiId(String wmsMiId) {
        this.wmsMiId = wmsMiId;
    }

    @Basic
    @Column(name = "sp_state")
    public Integer getSpState() {
        return spState;
    }

    public void setSpState(Integer spState) {
        this.spState = spState;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "edit_date")
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
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
    @Column(name = "edit_ren")
    public String getEditRen() {
        return editRen;
    }

    public void setEditRen(String editRen) {
        this.editRen = editRen;
    }

    @Transient
    public String getWmsMiIdString() {
        return wmsMiIdString;
    }

    public void setWmsMiIdString(String wmsMiIdString) {
        this.wmsMiIdString = wmsMiIdString;
    }

    @Transient
    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @Transient
    public List<Map<String, Object>> getMaterielList() {
        return materielList;
    }

    public void setMaterielList(HashMap getMaterielList) {
    }

    public void setMaterielList(List<Map<String, Object>> mapList) {
        this.materielList = mapList;
    }

    @Transient
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Transient
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setMateriel(MdMaterielInfo materiel) {
        this.materiel = materiel;
    }

    @Transient
    public MdMaterielInfo getMateriel() {
        return materiel;
    }
}
