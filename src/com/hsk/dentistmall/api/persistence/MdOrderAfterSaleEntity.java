package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2019/12/26 14:26
 */
@Entity
@Table(name = "md_order_after_sale")
public class MdOrderAfterSaleEntity {
    private Integer masId;
    private Integer moiId;
    private Integer suiId;
    private String masCode;
    private String userAddress;
    private String userPhone;
    private String supplierLocation;
    private String supplierName;
    private String supplierPhone;
    private String supplierAddress;
    private Double afterSaleMoney;
    private Integer asState;
    private Integer afterSale;
    private String asImg1;
    private String asImg2;
    private String asImg3;
    private String afterSaleRemarks;
    private String expressName;
    private String expressCode;
    private String editName;
    private Date editDate;
    private String createName;
    private String createPhone;
    private String createNickname;
    private Date createDate;
    private String remarks;
    private String refuse;
    /**
     * 与表无关字段
     */
    private String asStateName;
    private String afterSaleName;
    private String asImg1Path;
    private String asImg2Path;
    private String asImg3Path;
    private List<Map<String, Object>> asMxList;
    private String orderCode;
    private String applicantName;
    private String applicantPhone;

    @Id
    @Column(name = "mas_id")
    @GeneratedValue(strategy = IDENTITY)
    public Integer getMasId() {
        return masId;
    }

    public void setMasId(Integer masId) {
        this.masId = masId;
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
    @Column(name = "mas_code")
    public String getMasCode() {
        return masCode;
    }

    public void setMasCode(String masCode) {
        this.masCode = masCode;
    }

    @Basic
    @Column(name = "user_address")
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Basic
    @Column(name = "user_phone")
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @Column(name = "supplier_location")
    public String getSupplierLocation() {
        return supplierLocation;
    }

    public void setSupplierLocation(String supplierLocation) {
        this.supplierLocation = supplierLocation;
    }

    @Basic
    @Column(name = "supplier_name")
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Basic
    @Column(name = "supplier_phone")
    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    @Basic
    @Column(name = "supplier_address")
    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    @Basic
    @Column(name = "after_sale_money")
    public Double getAfterSaleMoney() {
        return afterSaleMoney;
    }

    public void setAfterSaleMoney(Double afterSaleMoney) {
        this.afterSaleMoney = afterSaleMoney;
    }

    @Basic
    @Column(name = "as_state")
    public Integer getAsState() {
        return asState;
    }

    public void setAsState(Integer asState) {
        this.asState = asState;
    }

    @Basic
    @Column(name = "after_sale")
    public Integer getAfterSale() {
        return afterSale;
    }

    public void setAfterSale(Integer afterSale) {
        this.afterSale = afterSale;
    }

    @Basic
    @Column(name = "as_img1")
    public String getAsImg1() {
        return asImg1;
    }

    public void setAsImg1(String asImg1) {
        this.asImg1 = asImg1;
    }

    @Basic
    @Column(name = "as_img2")
    public String getAsImg2() {
        return asImg2;
    }

    public void setAsImg2(String asImg2) {
        this.asImg2 = asImg2;
    }

    @Basic
    @Column(name = "as_img3")
    public String getAsImg3() {
        return asImg3;
    }

    public void setAsImg3(String asImg3) {
        this.asImg3 = asImg3;
    }

    @Basic
    @Column(name = "after_sale_remarks")
    public String getAfterSaleRemarks() {
        return afterSaleRemarks;
    }

    public void setAfterSaleRemarks(String afterSaleRemarks) {
        this.afterSaleRemarks = afterSaleRemarks;
    }

    @Basic
    @Column(name = "express_name")
    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    @Basic
    @Column(name = "express_code")
    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    @Basic
    @Column(name = "edit_name")
    public String getEditName() {
        return editName;
    }

    public void setEditName(String editName) {
        this.editName = editName;
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
    @Column(name = "create_name")
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Basic
    @Column(name = "create_phone")
    public String getCreatePhone() {
        return createPhone;
    }

    public void setCreatePhone(String createPhone) {
        this.createPhone = createPhone;
    }

    @Basic
    @Column(name = "create_nickname")
    public String getCreateNickname() {
        return createNickname;
    }

    public void setCreateNickname(String createNickname) {
        this.createNickname = createNickname;
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
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "refuse")
    public String getRefuse() {
        return refuse;
    }

    public void setRefuse(String refuse) {
        this.refuse = refuse;
    }

    @Formula("(SELECT a.param_name FROM sys_parameter a,sys_parameter b WHERE a.param_value= as_state and a.sys_spar_id=b.spar_id and b.param_code='PAR191230112633225')")
    public String getAsStateName() {
        return asStateName;
    }

    public void setAsStateName(String asStateName) {
        this.asStateName = asStateName;
    }

    @Formula("(SELECT a.param_name FROM sys_parameter a,sys_parameter b WHERE a.param_value= after_sale and a.sys_spar_id=b.spar_id and b.param_code='PAR191224031732706')")
    public String getAfterSaleName() {
        return afterSaleName;
    }

    public void setAfterSaleName(String afterSaleName) {
        this.afterSaleName = afterSaleName;
    }

    @Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code = as_img1)")
    public String getAsImg1Path() {
        return asImg1Path;
    }

    public void setAsImg1Path(String asImg1Path) {
        this.asImg1Path = asImg1Path;
    }
    @Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code = as_img2)")
    public String getAsImg2Path() {
        return asImg2Path;
    }

    public void setAsImg2Path(String asImg2Path) {
        this.asImg2Path = asImg2Path;
    }
    @Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code = as_img3)")
    public String getAsImg3Path() {
        return asImg3Path;
    }

    public void setAsImg3Path(String asImg3Path) {
        this.asImg3Path = asImg3Path;
    }

    @Transient
    public List<Map<String, Object>> getAsMxList() {
        return asMxList;
    }
    public void setAsMxList(List<Map<String, Object>> asMxList) {
        this.asMxList = asMxList;
    }
    public void setAsMxList(HashMap orderMxList) {
    }

    @Formula("(SELECT a.order_code FROM md_order_info a WHERE a.moi_id = moi_id)")
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Formula("(select a.applicant_name from md_order_info a where a.moi_id = moi_id)")
    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    @Formula("(select a.phone_number from md_supplier a where a.wz_id = (select c.wz_id from md_order_info c where c.moi_id = moi_id))")
    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }
}
