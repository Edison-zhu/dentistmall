package com.hsk.dentistmall.api.persistence;

import org.apache.poi.hslf.extractor.ImageExtractor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/8/5 9:31
 */
@Entity
@Table(name = "md_model_operate_log")
public class MdModelOperateLogEntity {
    private Integer mdMoLogId;
    private String operateName;
    private Integer suiId;
    private String operateUser;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;
    private Integer mdModelId;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "md_mo_log_id")
    public Integer getMdMoLogId() {
        return mdMoLogId;
    }

    public void setMdMoLogId(Integer mdMoLogId) {
        this.mdMoLogId = mdMoLogId;
    }

    @Basic
    @Column(name = "operate_name")
    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
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
    @Column(name = "operate_user")
    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
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

    @Column(name = "md_model_id")
    public Integer getMdModelId() {
        return mdModelId;
    }

    public void setMdModelId(Integer mdModelId) {
        this.mdModelId = mdModelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdModelOperateLogEntity that = (MdModelOperateLogEntity) o;
        return mdMoLogId == that.mdMoLogId &&
                Objects.equals(operateName, that.operateName) &&
                Objects.equals(suiId, that.suiId) &&
                Objects.equals(operateUser, that.operateUser) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editRen, that.editRen) &&
                Objects.equals(editDate, that.editDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdMoLogId, operateName, suiId, operateUser, createRen, createDate, editRen, editDate);
    }
}
