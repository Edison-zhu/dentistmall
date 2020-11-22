package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/8/4 13:16
 */
@Entity
@Table(name = "md_model_file_info")
public class MdModelFileInfoEntity {
    private Integer mdMfId;
    private Integer mdModelId;
    private String fileCode;
    private Integer fileId;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "md_mf_id")
    public Integer getMdMfId() {
        return mdMfId;
    }

    public void setMdMfId(Integer mdMfId) {
        this.mdMfId = mdMfId;
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
    @Column(name = "file_code")
    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    @Basic
    @Column(name = "file_id")
    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdModelFileInfoEntity that = (MdModelFileInfoEntity) o;
        return mdMfId == that.mdMfId &&
                Objects.equals(mdModelId, that.mdModelId) &&
                Objects.equals(fileCode, that.fileCode) &&
                Objects.equals(fileId, that.fileId) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editRen, that.editRen) &&
                Objects.equals(editDate, that.editDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdMfId, mdModelId, fileCode, fileId, createRen, createDate, editRen, editDate);
    }
}
