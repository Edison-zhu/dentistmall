package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/8/4 16:17
 */
@Entity
@Table(name = "md_model_parameter_info")
public class MdModelParameterInfoEntity {
    private Integer mdMpId;
    private Integer mdModelId;
    private Integer mmpId;
    private String mmpName;
    private String mmpContent;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;
    private Map<String, String> paramOptions;
    private String inputMode;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "md_mp_id")
    public Integer getMdMpId() {
        return mdMpId;
    }

    public void setMdMpId(Integer mdMpId) {
        this.mdMpId = mdMpId;
    }

    @Basic
    @Column(name = "md_model_id")
    public Integer getMdModelId() {
        return mdModelId;
    }

    public void setMdModelId(Integer mdModelId) {
        this.mdModelId = mdModelId;
    }

    @Basic
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
    @Column(name = "mmp_content")
    public String getMmpContent() {
        return mmpContent;
    }

    public void setMmpContent(String mmpContent) {
        this.mmpContent = mmpContent;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdModelParameterInfoEntity that = (MdModelParameterInfoEntity) o;
        return mdMpId == that.mdMpId &&
                Objects.equals(mdModelId, that.mdModelId) &&
                Objects.equals(mmpId, that.mmpId) &&
                Objects.equals(mmpName, that.mmpName) &&
                Objects.equals(mmpContent, that.mmpContent) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editRen, that.editRen) &&
                Objects.equals(editDate, that.editDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdMpId, mdModelId, mmpId, mmpName, mmpContent, createRen, createDate, editRen, editDate);
    }

    public void setParamOptions(Map<String, String> paramOptions) {
        this.paramOptions = paramOptions;
    }

    @Transient
    public Map<String, String> getParamOptions() {
        return paramOptions;
    }

    public void setInputMode(String inputMode) {
        this.inputMode = inputMode;
    }
    @Transient
    public String getInputMode() {
        return inputMode;
    }
}
