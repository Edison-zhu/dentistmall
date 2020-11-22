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
@Table(name = "md_model_brand_info")
public class MdModelBrandInfoEntity {
    private Integer mdMbId;
    private Integer mdModelId;
    private Integer mbdId;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "md_mb_id")
    public Integer getMdMbId() {
        return mdMbId;
    }

    public void setMdMbId(Integer mdMbId) {
        this.mdMbId = mdMbId;
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
    @Column(name = "mbd_id")
    public Integer getMbdId() {
        return mbdId;
    }

    public void setMbdId(Integer mbdId) {
        this.mbdId = mbdId;
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
        MdModelBrandInfoEntity that = (MdModelBrandInfoEntity) o;
        return mdMbId == that.mdMbId &&
                Objects.equals(mdModelId, that.mdModelId) &&
                Objects.equals(mbdId, that.mbdId) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editRen, that.editRen) &&
                Objects.equals(editDate, that.editDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdMbId, mdModelId, mbdId, createRen, createDate, editRen, editDate);
    }
}
