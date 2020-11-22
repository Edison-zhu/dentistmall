package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/8/5 13:05
 */
@Entity
@Table(name = "md_model_format_attr_info")
public class MdModelFormatAttrInfoEntity {
    private Integer mdMfAttrId;
    private Integer modelMmfId;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;
    private Integer canSearch;
    private String attrContent;
    private Integer mmaMxId;
    private Integer mmaId;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "md_mf_attr_id")
    public Integer getMdMfAttrId() {
        return mdMfAttrId;
    }

    public void setMdMfAttrId(Integer mdMfAttrId) {
        this.mdMfAttrId = mdMfAttrId;
    }

    @Basic
    @Column(name = "model_mmf_id")
    public Integer getModelMmfId() {
        return modelMmfId;
    }

    public void setModelMmfId(Integer modelMmfId) {
        this.modelMmfId = modelMmfId;
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
    @Column(name = "can_search")
    public Integer getCanSearch() {
        return canSearch;
    }

    public void setCanSearch(Integer canSearch) {
        this.canSearch = canSearch;
    }

    @Column(name = "attr_content")
    public String getAttrContent() {
        return attrContent;
    }

    public void setAttrContent(String attrContent) {
        this.attrContent = attrContent;
    }

    @Column(name = "mmamx_id")
    public Integer getMmaMxId() {
        return mmaMxId;
    }

    public void setMmaMxId(Integer mmaMxId) {
        this.mmaMxId = mmaMxId;
    }

    @Column(name = "mma_id")
    public Integer getMmaId() {
        return mmaId;
    }

    public void setMmaId(Integer mmaId) {
        this.mmaId = mmaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdModelFormatAttrInfoEntity that = (MdModelFormatAttrInfoEntity) o;
        return mdMfAttrId == that.mdMfAttrId &&
                Objects.equals(modelMmfId, that.modelMmfId) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editRen, that.editRen) &&
                Objects.equals(editDate, that.editDate) &&
                Objects.equals(canSearch, that.canSearch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdMfAttrId, modelMmfId, createRen, createDate, editRen, editDate, canSearch);
    }
}
