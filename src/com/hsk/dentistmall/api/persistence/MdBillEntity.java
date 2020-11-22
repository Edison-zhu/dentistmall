package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/9/3 9:50
 */
@Entity
@Table(name = "md_bill")
public class MdBillEntity {
    private Integer billId;
    private Integer billType;
    private String billHead;
    private Integer billKind;
    private String billCode;
    private String bankAccount;
    private String bankCode;
    private String registerAddress;
    private String registerPhone;
    private Integer moiId;
    private Integer suiId;
    private Integer deliveryId;
    private String billContent;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;
    private Integer wzId;
    private String applicantName;
    private String orderCode;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bill_id")
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    @Basic
    @Column(name = "bill_type")
    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    @Basic
    @Column(name = "bill_head")
    public String getBillHead() {
        return billHead;
    }

    public void setBillHead(String billHead) {
        this.billHead = billHead;
    }

    @Basic
    @Column(name = "bill_kind")
    public Integer getBillKind() {
        return billKind;
    }

    public void setBillKind(Integer billKind) {
        this.billKind = billKind;
    }

    @Basic
    @Column(name = "bill_code")
    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    @Basic
    @Column(name = "bank_account")
    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Basic
    @Column(name = "bank_code")
    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @Basic
    @Column(name = "register_address")
    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    @Basic
    @Column(name = "register_phone")
    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    @Basic
    @Column(name = "moi_id")
    public Integer getMoiId() {
        return moiId;
    }

    public void setMoiId(Integer moiId) {
        this.moiId = moiId;
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
    @Column(name = "delivery_id")
    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    @Basic
    @Column(name = "bill_content")
    public String getBillContent() {
        return billContent;
    }

    public void setBillContent(String billContent) {
        this.billContent = billContent;
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

    @Column(name = "wz_id")
    public Integer getWzId() {
        return wzId;
    }

    public void setWzId(Integer wzId) {
        this.wzId = wzId;
    }

    @Column(name = "applicant_name")
    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    @Column(name = "order_code")
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdBillEntity that = (MdBillEntity) o;
        return billId == that.billId &&
                Objects.equals(billType, that.billType) &&
                Objects.equals(billHead, that.billHead) &&
                Objects.equals(billKind, that.billKind) &&
                Objects.equals(billCode, that.billCode) &&
                Objects.equals(bankAccount, that.bankAccount) &&
                Objects.equals(bankCode, that.bankCode) &&
                Objects.equals(registerAddress, that.registerAddress) &&
                Objects.equals(registerPhone, that.registerPhone) &&
                Objects.equals(moiId, that.moiId) &&
                Objects.equals(suiId, that.suiId) &&
                Objects.equals(deliveryId, that.deliveryId) &&
                Objects.equals(billContent, that.billContent) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editRen, that.editRen) &&
                Objects.equals(editDate, that.editDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, billType, billHead, billKind, billCode, bankAccount, bankCode, registerAddress, registerPhone, moiId, suiId, deliveryId, billContent, createRen, createDate, editRen, editDate);
    }

    // 无关字段
    private String timeStart;
    private String timeEnd;

    @Transient
    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    @Transient
    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
