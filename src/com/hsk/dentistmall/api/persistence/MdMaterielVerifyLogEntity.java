package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/7/30 13:33
 */
@Entity
@Table(name = "md_materiel_verify_log")
public class MdMaterielVerifyLogEntity {
    private Integer mdVerifyLogId;
    private Integer mdVerifyId;
    private String verifyRen;
    private String verifyInfo;
    private String verifyRes;
    private Date createDate;
    private String createRen;
    private Integer wmsMiId;
    private Integer wmsModelId;
    private Integer wzId;
    private Integer verifyType;
    private Integer verifySuiId;
    private Integer suiId;
    private Integer verifyState;
    private Date verifyDate;

    @Column(name = "verify_date")
    public Date getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(Date verifyDate) {
        this.verifyDate = verifyDate;
    }

    @Column(name = "verify_state")
    public Integer getVerifyState() {
        return verifyState;
    }

    public void setVerifyState(Integer verifyState) {
        this.verifyState = verifyState;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "md_verify_log_id")
    public Integer getMdVerifyLogId() {
        return mdVerifyLogId;
    }

    public void setMdVerifyLogId(Integer mdVerifyLogId) {
        this.mdVerifyLogId = mdVerifyLogId;
    }

    @Basic
    @Column(name = "md_verify_id")
    public Integer getMdVerifyId() {
        return mdVerifyId;
    }

    public void setMdVerifyId(Integer mdVerifyId) {
        this.mdVerifyId = mdVerifyId;
    }

    @Basic
    @Column(name = "verify_ren")
    public String getVerifyRen() {
        return verifyRen;
    }

    public void setVerifyRen(String verifyRen) {
        this.verifyRen = verifyRen;
    }

    @Basic
    @Column(name = "verify_info")
    public String getVerifyInfo() {
        return verifyInfo;
    }

    public void setVerifyInfo(String verifyInfo) {
        this.verifyInfo = verifyInfo;
    }

    @Basic
    @Column(name = "verify_res")
    public String getVerifyRes() {
        return verifyRes;
    }

    public void setVerifyRes(String verifyRes) {
        this.verifyRes = verifyRes;
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
    @Column(name = "create_ren")
    public String getCreateRen() {
        return createRen;
    }

    public void setCreateRen(String createRen) {
        this.createRen = createRen;
    }

    @Basic
    @Column(name = "wms_mi_id")
    public Integer getWmsMiId() {
        return wmsMiId;
    }

    public void setWmsMiId(Integer wmsMiId) {
        this.wmsMiId = wmsMiId;
    }

    @Basic
    @Column(name = "wms_model_id")
    public Integer getWmsModelId() {
        return wmsModelId;
    }

    public void setWmsModelId(Integer wmsModelId) {
        this.wmsModelId = wmsModelId;
    }

    @Basic
    @Column(name = "wz_id")
    public Integer getWzId() {
        return wzId;
    }

    public void setWzId(Integer wzId) {
        this.wzId = wzId;
    }

    @Basic
    @Column(name = "verify_type")
    public Integer getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(Integer verifyType) {
        this.verifyType = verifyType;
    }

    @Basic
    @Column(name = "verify_sui_id")
    public Integer getVerifySuiId() {
        return verifySuiId;
    }

    public void setVerifySuiId(Integer verifySuiId) {
        this.verifySuiId = verifySuiId;
    }

    @Basic
    @Column(name = "sui_id")
    public Integer getSuiId() {
        return suiId;
    }

    public void setSuiId(Integer suiId) {
        this.suiId = suiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdMaterielVerifyLogEntity that = (MdMaterielVerifyLogEntity) o;
        return mdVerifyLogId == that.mdVerifyLogId &&
                mdVerifyId == that.mdVerifyId &&
                Objects.equals(verifyRen, that.verifyRen) &&
                Objects.equals(verifyInfo, that.verifyInfo) &&
                Objects.equals(verifyRes, that.verifyRes) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(wmsMiId, that.wmsMiId) &&
                Objects.equals(wmsModelId, that.wmsModelId) &&
                Objects.equals(wzId, that.wzId) &&
                Objects.equals(verifyType, that.verifyType) &&
                Objects.equals(verifySuiId, that.verifySuiId) &&
                Objects.equals(suiId, that.suiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdVerifyLogId, mdVerifyId, verifyRen, verifyInfo, verifyRes, createDate, createRen, wmsMiId, wmsModelId, wzId, verifyType, verifySuiId, suiId);
    }
}
