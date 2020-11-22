package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;
//参数表
@Entity
@Table(name = "md_materiel_parameter")
public class MdMaterielParameter {
        private Integer mmpId;
        private String mmpName;
        private String isRequired;
        private String state;
        private String optiona_value;
        private String createRen;
        private Date createDate;
        private String editRen;
        private Date editDate;
        private String isSearch;
        private String relation;
        private String mmpSort;
        private String inputMode;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mmp_id")
    public Integer getMmpId() {
        return mmpId;
    }

    public void setMmpId(Integer mmpId) {
        this.mmpId = mmpId;
    }
    @Basic
    @Column(name = "mmp_name")
    public String getMmpName() {
        return mmpName;
    }

    public void setMmpName(String mmpName) {
        this.mmpName = mmpName;
    }
    @Basic
    @Column(name = "isRequired")
    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
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
    @Column(name = "optiona_value")
    public String getOptiona_value() {
        return optiona_value;
    }

    public void setOptiona_value(String optiona_value) {
        this.optiona_value = optiona_value;
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

    @Basic
    @Column(name = "isSearch")
    public String getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(String isSearch) {
        this.isSearch = isSearch;
    }
    @Basic
    @Column(name = "relation")
    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
    @Basic
    @Column(name = "mmp_sort")
    public String getMmpSort() {
        return mmpSort;
    }

    public void setMmpSort(String mmpSort) {
        this.mmpSort = mmpSort;
    }
    @Basic
    @Column(name = "inputMode")
    public String getInputMode() {
        return inputMode;
    }

    public void setInputMode(String inputMode) {
        this.inputMode = inputMode;
    }
}