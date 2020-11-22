package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

//参数表
@Entity
@Table(name = "md_materiel_attribute")
public class mdMaterielAttribute {
       private Integer mmaId;

       private String mmaName;

       private String isOptional;

       private String inputModel;

       private String optionalValue;

       private String state;

       private Date createDate;

       private String createRen;

       private String editRen;

       private Date editDate;

       private String isSearch;

       private String mmaSort;

       private String screenType;

       private String newAdd;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mma_id")
    public Integer getMmaId() {
        return mmaId;
    }

    public void setMmaId(Integer mmaId) {
        this.mmaId = mmaId;
    }
    @Basic
    @Column(name = "mma_name")
    public String getMmaName() {
        return mmaName;
    }

    public void setMmaName(String mmaName) {
        this.mmaName = mmaName;
    }
    @Basic
    @Column(name = "isOptional")
    public String getIsOptional() {
        return isOptional;
    }
    public void setIsOptional(String isOptional) {
        this.isOptional = isOptional;
    }
    @Basic
    @Column(name = "input_model")
    public String getInputModel() {
        return inputModel;
    }

    public void setInputModel(String inputModel) {
        this.inputModel = inputModel;
    }
    @Basic
    @Column(name = "optional_value")
    public String getOptionalValue() {
        return optionalValue;
    }

    public void setOptionalValue(String optionalValue) {
        this.optionalValue = optionalValue;
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
    @Column(name = "createDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
    @Column(name = "mma_sort")
    public String getMmaSort() {
        return mmaSort;
    }

    public void setMmaSort(String mmaSort) {
        this.mmaSort = mmaSort;
    }

    @Basic
    @Column(name = "screen_type")
    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    @Basic
    @Column(name = "new_add")
    public String getNewAdd() {
        return newAdd;
    }
    public void setNewAdd(String newAdd) {
        this.newAdd = newAdd;
    }
}