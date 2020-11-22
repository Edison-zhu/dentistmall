package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/5/13 13:30
 */
@Entity
@Table(name = "md_materiel_part_detail_log")
public class MdMaterielPartDetailLogEntity {
    private Integer mddlId;
    private String logName;
    private String log;
    private String createRen;
    private Date createDate;
    private Integer suiId;
    private Integer wmsMiId;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mddl_id")
    public Integer getMddlId() {
        return mddlId;
    }

    public void setMddlId(Integer mddlId) {
        this.mddlId = mddlId;
    }

    @Basic
    @Column(name = "log_name")
    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    @Basic
    @Column(name = "log")
    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
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

    @Column(name = "wms_mi_id")
    public Integer getWmsMiId() {
        return wmsMiId;
    }

    public void setWmsMiId(Integer wmsMiId) {
        this.wmsMiId = wmsMiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MdMaterielPartDetailLogEntity that = (MdMaterielPartDetailLogEntity) o;

        if (mddlId != that.mddlId) return false;
        if (logName != null ? !logName.equals(that.logName) : that.logName != null) return false;
        if (log != null ? !log.equals(that.log) : that.log != null) return false;
        if (createRen != null ? !createRen.equals(that.createRen) : that.createRen != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (suiId != null ? !suiId.equals(that.suiId) : that.suiId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = mddlId;
        result = 31 * result + (logName != null ? logName.hashCode() : 0);
        result = 31 * result + (log != null ? log.hashCode() : 0);
        result = 31 * result + (createRen != null ? createRen.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (suiId != null ? suiId.hashCode() : 0);
        return result;
    }
}
