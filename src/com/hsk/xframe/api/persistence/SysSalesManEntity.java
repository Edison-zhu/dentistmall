package com.hsk.xframe.api.persistence;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/1/3 15:02
 */
@Entity
@Table(name = "sys_sales_man")
public class SysSalesManEntity {
    private Integer salesmanId;
    private Integer orgGxId;
    private Integer wzId;
    private String rbaId;
    private String rbsId;
    private String rbbId;
    private Integer state;
    private String salesCode;
    private String salesName;
    private String salesAccount;
    private String salesPassword;
    private String salesPhone;
    private String salesAddress;
    private String salesArea;
    private String salesWechat;
    private String salesQq;
    private String salesEmail;
    private Date createDate;
    private String createRen;
    private Date editDate;
    private String editRen;
    private String describes;
    private String purchaseType;
    private String salesCompany;
    private Integer salesType;
    private String agentCompany;
    private Integer leaderId;
    private String leaderName;

    private String province;
    private String city;
    private String area;
    private String createDateStart;
    private Integer exludeId;
    private Integer excludeType;
    private Integer verifyState;
    private String verifyRemark;
    private String userTypeStr;
    private String excludeTypes;

    @Id
    @Column(name = "salesman_id")
    @GeneratedValue(strategy = IDENTITY)
    public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }

    @Basic
    @Column(name = "org_gx_id")
    public Integer getOrgGxId() {
        return orgGxId;
    }

    public void setOrgGxId(Integer orgGxId) {
        this.orgGxId = orgGxId;
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
    @Column(name = "rba_id")
    public String getRbaId() {
        return rbaId;
    }

    public void setRbaId(String rbaId) {
        this.rbaId = rbaId;
    }

    @Basic
    @Column(name = "rbs_id")
    public String getRbsId() {
        return rbsId;
    }

    public void setRbsId(String rbsId) {
        this.rbsId = rbsId;
    }

    @Basic
    @Column(name = "rbb_id")
    public String getRbbId() {
        return rbbId;
    }

    public void setRbbId(String rbbId) {
        this.rbbId = rbbId;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "sales_code")
    public String getSalesCode() {
        return salesCode;
    }

    public void setSalesCode(String salesCode) {
        this.salesCode = salesCode;
    }

    @Basic
    @Column(name = "sales_name")
    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    @Basic
    @Column(name = "sales_account")
    public String getSalesAccount() {
        return salesAccount;
    }

    public void setSalesAccount(String salesAccount) {
        this.salesAccount = salesAccount;
    }

    @Basic
    @Column(name = "sales_password")
    public String getSalesPassword() {
        return salesPassword;
    }

    public void setSalesPassword(String salesPassword) {
        this.salesPassword = salesPassword;
    }

    @Basic
    @Column(name = "sales_phone")
    public String getSalesPhone() {
        return salesPhone;
    }

    public void setSalesPhone(String salesPhone) {
        this.salesPhone = salesPhone;
    }

    @Basic
    @Column(name = "sales_address")
    public String getSalesAddress() {
        return salesAddress;
    }

    public void setSalesAddress(String salesAddress) {
        this.salesAddress = salesAddress;
    }

    @Basic
    @Column(name = "sales_area")
    public String getSalesArea() {
        return salesArea;
    }

    public void setSalesArea(String salesArea) {
        this.salesArea = salesArea;
    }

    @Basic
    @Column(name = "sales_wechat")
    public String getSalesWechat() {
        return salesWechat;
    }

    public void setSalesWechat(String salesWechat) {
        this.salesWechat = salesWechat;
    }

    @Basic
    @Column(name = "sales_qq")
    public String getSalesQq() {
        return salesQq;
    }

    public void setSalesQq(String salesQq) {
        this.salesQq = salesQq;
    }

    @Basic
    @Column(name = "sales_email")
    public String getSalesEmail() {
        return salesEmail;
    }

    public void setSalesEmail(String salesEmail) {
        this.salesEmail = salesEmail;
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
    @Column(name = "edit_date")
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
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
    @Column(name = "describes")
    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @Basic
    @Column(name = "purchase_type")
    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    @Basic
    @Column(name = "sales_company")
    public String getSalesCompany() {
        return salesCompany;
    }

    public void setSalesCompany(String salesCompany) {
        this.salesCompany = salesCompany;
    }

    @Column(name = "sales_type")
    public Integer getSalesType() {
        return salesType;
    }

    public void setSalesType(Integer sales_type) {
        this.salesType = sales_type;
    }

    @Column(name = "agent_company")
    public String getAgentCompany() {
        return agentCompany;
    }

    public void setAgentCompany(String agent_company) {
        this.agentCompany = agent_company;
    }

    @Column(name = "leader_id")
    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leader_id) {
        this.leaderId = leader_id;
    }

    @Column(name = "leader_name")
    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leader_name) {
        this.leaderName = leader_name;
    }

    @Transient
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Transient
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Transient
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Transient
    public String getCreateDateStart() {
        return createDateStart;
    }

    public void setCreateDateStart(String createDateStart) {
        this.createDateStart = createDateStart;
    }

    @Transient
    public Integer getExludeId() {
        return exludeId;
    }

    public void setExludeId(Integer exludeId) {
        this.exludeId = exludeId;
    }

    public void setVerifyState(Integer verifyState) {
        this.verifyState = verifyState;
    }

    @Column(name = "verify_state")
    public Integer getVerifyState() {
        return verifyState;
    }

    public void setVerifyRemark(String verifyRemark) {
        this.verifyRemark = verifyRemark;
    }
    @Column(name = "verify_remark")
    public String getVerifyRemark() {
        return verifyRemark;
    }

    @Transient
    public String getUserTypeStr() {
        return userTypeStr;
    }

    public void setUserTypeStr(String userTypeStr) {
        this.userTypeStr = userTypeStr;
    }

    @Transient
    public Integer getExcludeType() {
        return excludeType;
    }

    public void setExcludeType(Integer excludeType) {
        this.excludeType = excludeType;
    }

    @Transient
    public String getExcludeTypes() {
        return excludeTypes;
    }

    public void setExcludeTypes(String excludeTypes) {
        this.excludeTypes = excludeTypes;
    }
}
