package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;

/**
 * author: yangfeng
 * time: 2019/11/26 15:39
 */
@Entity
@Table(name = "md_outwarehouse_outorder")
public class MdOutwarehouseOutorderEntity {
    private Integer wowId;
    private Integer rbaId;
    private Integer rbsId;
    private Integer rbbId;
    private Integer suiId;
    private String purchaseType;
    private String ower;
    private String supplierName;
    private String consignee;
    private String companyType;
    private String wowCode;
    private String relatedBill1;
    private String relatedBill2;
    private String relatedBill3;
    private String customer;
    private String customerName;
    private String state;
    private Double baseNumber;
    private String description;
    private Date finshDate;
    private Date orderTime;
    private String userName;
    private String groupName;
    private Date activeTime;
    private Date editDate;
    private String editRen;
    private Date createDate;
    private String createRen;
    private Double allNumber;
    private Double alreadyNumber;
    private String flowState;

    @Basic
    @Id
    @Column(name = "wow_id")
    public Integer getWowId() {
        return wowId;
    }

    public void setWowId(Integer wowId) {
        this.wowId = wowId;
    }

    @Basic
    @Column(name = "rba_id")
    public Integer getRbaId() {
        return rbaId;
    }

    public void setRbaId(Integer rbaId) {
        this.rbaId = rbaId;
    }

    @Basic
    @Column(name = "rbs_id")
    public Integer getRbsId() {
        return rbsId;
    }

    public void setRbsId(Integer rbsId) {
        this.rbsId = rbsId;
    }

    @Basic
    @Column(name = "rbb_id")
    public Integer getRbbId() {
        return rbbId;
    }

    public void setRbbId(Integer rbbId) {
        this.rbbId = rbbId;
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
    @Column(name = "purchase_type")
    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    @Basic
    @Column(name = "ower")
    public String getOwer() {
        return ower;
    }

    public void setOwer(String ower) {
        this.ower = ower;
    }

    @Basic
    @Column(name = "Supplier_name")
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Basic
    @Column(name = "consignee")
    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    @Basic
    @Column(name = "COMPANY_type")
    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    @Basic
    @Column(name = "wow_code")
    public String getWowCode() {
        return wowCode;
    }

    public void setWowCode(String wowCode) {
        this.wowCode = wowCode;
    }

    @Basic
    @Column(name = "RELATED_BILL1")
    public String getRelatedBill1() {
        return relatedBill1;
    }

    public void setRelatedBill1(String relatedBill1) {
        this.relatedBill1 = relatedBill1;
    }

    @Basic
    @Column(name = "RELATED_BILL2")
    public String getRelatedBill2() {
        return relatedBill2;
    }

    public void setRelatedBill2(String relatedBill2) {
        this.relatedBill2 = relatedBill2;
    }

    @Basic
    @Column(name = "RELATED_BILL3")
    public String getRelatedBill3() {
        return relatedBill3;
    }

    public void setRelatedBill3(String relatedBill3) {
        this.relatedBill3 = relatedBill3;
    }

    @Basic
    @Column(name = "CUSTOMER")
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Basic
    @Column(name = "CUSTOMER_name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
    @Column(name = "base_number")
    public Double getBaseNumber() {
        return baseNumber;
    }

    public void setBaseNumber(Double baseNumber) {
        this.baseNumber = baseNumber;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "FINSH_DATE")
    public Date getFinshDate() {
        return finshDate;
    }

    public void setFinshDate(Date finshDate) {
        this.finshDate = finshDate;
    }

    @Basic
    @Column(name = "order_time")
    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Basic
    @Column(name = "USER_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "GROUP_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "ACTIVE_TIME")
    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
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
    @Column(name = "all_number")
    public Double getAllNumber() {
        return allNumber;
    }

    public void setAllNumber(Double allNumber) {
        this.allNumber = allNumber;
    }

    @Basic
    @Column(name = "already_number")
    public Double getAlreadyNumber() {
        return alreadyNumber;
    }

    public void setAlreadyNumber(Double alreadyNumber) {
        this.alreadyNumber = alreadyNumber;
    }

    @Basic
    @Column(name = "flow_state")
    public String getFlowState() {
        return flowState;
    }

    public void setFlowState(String flowState) {
        this.flowState = flowState;
    }
}
