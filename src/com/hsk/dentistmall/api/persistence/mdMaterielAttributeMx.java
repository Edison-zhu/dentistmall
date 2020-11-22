package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

//参数表
@Entity
@Table(name = "md_materiel_attribute_mx")
public class mdMaterielAttributeMx {
        private Integer mmamxId;

        private Integer mmaId;

        private String mmamxName;

        private String attribute;

        private String iconCode;

        private String state;

        private String createRen;

        private Date createDate;

        private String editRen;

        private Date editDate;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mmamx_id")
    public Integer getMmamxId() {
        return mmamxId;
    }

    public void setMmamxId(Integer mmamxId) {
        this.mmamxId = mmamxId;
    }
    @Basic
    @Column(name = "mma_id")
    public Integer getMmaId() {
        return mmaId;
    }

    public void setMmaId(Integer mmaId) {
        this.mmaId = mmaId;
    }
    @Basic
    @Column(name = "mmamx_name")
    public String getMmamxName() {
        return mmamxName;
    }

    public void setMmamxName(String mmamxName) {
        this.mmamxName = mmamxName;
    }
    @Basic
    @Column(name = "attribute")
    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    @Basic
    @Column(name = "iconCode")
    public String getIconCode() {
        return iconCode;
    }

    public void setIconCode(String iconCode) {
        this.iconCode = iconCode;
    }
    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "createRen")
    public String getCreateRen() {
        return createRen;
    }

    public void setCreateRen(String createRen) {
        this.createRen = createRen;
    }
    @Basic
    @Column(name = "createDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Basic
    @Column(name = "editRen")
    public String getEditRen() {
        return editRen;
    }

    public void setEditRen(String editRen) {
        this.editRen = editRen;
    }
    @Basic
    @Column(name = "editDate")
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }
}