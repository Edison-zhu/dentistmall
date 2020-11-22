package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * author: yangfeng
 * time: 2020/5/28 14:01
 */
@Entity
@Table(name = "md_inventory_en_out_log_view")
public class MdInventoryEnOutLogViewEntity {
    private Integer mxId;
    private Integer mmfId1;
    private String createDate1;
    private Double number;
    private Long stype;
    private Integer mieId;
    private Integer wiId;
    private Integer wmsMiId;
    private Integer mmfId;
    private Double price;
    private Double basePrice;
    private Double retailPrice;
    private Double quantity;
    private Double splitQuantity;
    private String basicUnit;
    private String unit;
    private Double baseNumber;
    private String relatedCode;
    private String purchaseUser;
    private Date createDate;
    private Date editDate;
    private String matName;
    private String mmfName;
    private String matName2;
    private String norm2;
    private String matType;
    private String matType1;
    private String matType2;
    private String matType3;
    private String productName;
    private String brand;
    private String labelInfo;
    private String applicantName;
    private String state;
    private String batchCode;
    private Integer mdpId;
    private Integer mdnId;
    private Integer mddId;
    private Integer mdpsId;
    private String mdnCode;
    private String mddName;
    private String mdnNorm;
    private String mdnNode;
    private String cCode;
    private String searchName;
    private String createRen;
    private String productValitime;
    private String backPrinting;
    private String batchCode1;
    private String valiedDate1;

    @Column(name = "product_valitime")
    public String getProductValitime() {
        return productValitime;
    }

    public void setProductValitime(String productValitime) {
        this.productValitime = productValitime;
    }

    @Basic
    @Column(name = "mx_id")
    public Integer getMxId() {
        return mxId;
    }

    public void setMxId(Integer mxId) {
        this.mxId = mxId;
    }

    @Id
    @Column(name = "c_code")
    public String getCCode() {
        return cCode;
    }

    public void setCCode(String cCode) {
        this.cCode = cCode;
    }

    @Basic
    @Column(name = "mmf_id1")
    public Integer getMmfId1() {
        return mmfId1;
    }

    public void setMmfId1(Integer mmfId1) {
        this.mmfId1 = mmfId1;
    }

    @Basic
    @Column(name = "create_date1")
    public String getCreateDate1() {
        return createDate1;
    }

    public void setCreateDate1(String createDate1) {
        this.createDate1 = createDate1;
    }

    @Basic
    @Column(name = "number")
    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    @Basic
    @Column(name = "stype")
    public Long getStype() {
        return stype;
    }

    public void setStype(Long stype) {
        this.stype = stype;
    }

    @Basic
    @Column(name = "mie_id")
    public Integer getMieId() {
        return mieId;
    }

    public void setMieId(Integer mieId) {
        this.mieId = mieId;
    }

    @Basic
    @Column(name = "wi_id")
    public Integer getWiId() {
        return wiId;
    }

    public void setWiId(Integer wiId) {
        this.wiId = wiId;
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
    @Column(name = "mmf_id")
    public Integer getMmfId() {
        return mmfId;
    }

    public void setMmfId(Integer mmfId) {
        this.mmfId = mmfId;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "base_price")
    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    @Basic
    @Column(name = "retail_price")
    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Basic
    @Column(name = "QUANTITY")
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "split_quantity")
    public Double getSplitQuantity() {
        return splitQuantity;
    }

    public void setSplitQuantity(Double splitQuantity) {
        this.splitQuantity = splitQuantity;
    }

    @Basic
    @Column(name = "Basic_unit")
    public String getBasicUnit() {
        return basicUnit;
    }

    public void setBasicUnit(String basicUnit) {
        this.basicUnit = basicUnit;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
    @Column(name = "related_code")
    public String getRelatedCode() {
        return relatedCode;
    }

    public void setRelatedCode(String relatedCode) {
        this.relatedCode = relatedCode;
    }

    @Basic
    @Column(name = "purchase_user")
    public String getPurchaseUser() {
        return purchaseUser;
    }

    public void setPurchaseUser(String purchaseUser) {
        this.purchaseUser = purchaseUser;
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
    @Column(name = "edit_date")
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    @Basic
    @Column(name = "mat_name")
    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    @Basic
    @Column(name = "mmf_name")
    public String getMmfName() {
        return mmfName;
    }

    public void setMmfName(String mmfName) {
        this.mmfName = mmfName;
    }

    @Basic
    @Column(name = "mat_name2")
    public String getMatName2() {
        return matName2;
    }

    public void setMatName2(String matName2) {
        this.matName2 = matName2;
    }

    @Basic
    @Column(name = "norm2")
    public String getNorm2() {
        return norm2;
    }

    public void setNorm2(String norm2) {
        this.norm2 = norm2;
    }

    @Basic
    @Column(name = "mat_type")
    public String getMatType() {
        return matType;
    }

    public void setMatType(String matType) {
        this.matType = matType;
    }

    @Basic
    @Column(name = "mat_type1")
    public String getMatType1() {
        return matType1;
    }

    public void setMatType1(String matType1) {
        this.matType1 = matType1;
    }

    @Basic
    @Column(name = "mat_type2")
    public String getMatType2() {
        return matType2;
    }

    public void setMatType2(String matType2) {
        this.matType2 = matType2;
    }

    @Basic
    @Column(name = "mat_type3")
    public String getMatType3() {
        return matType3;
    }

    public void setMatType3(String matType3) {
        this.matType3 = matType3;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "Label_info")
    public String getLabelInfo() {
        return labelInfo;
    }

    public void setLabelInfo(String labelInfo) {
        this.labelInfo = labelInfo;
    }

    @Basic
    @Column(name = "applicant_name")
    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
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
    @Column(name = "batch_code")
    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    @Basic
    @Column(name = "mdp_id")
    public Integer getMdpId() {
        return mdpId;
    }

    public void setMdpId(Integer mdpId) {
        this.mdpId = mdpId;
    }

    @Basic
    @Column(name = "mdn_id")
    public Integer getMdnId() {
        return mdnId;
    }

    public void setMdnId(Integer mdnId) {
        this.mdnId = mdnId;
    }

    @Basic
    @Column(name = "mdd_id")
    public Integer getMddId() {
        return mddId;
    }

    public void setMddId(Integer mddId) {
        this.mddId = mddId;
    }

    @Basic
    @Column(name = "mdps_id")
    public Integer getMdpsId() {
        return mdpsId;
    }

    public void setMdpsId(Integer mdpsId) {
        this.mdpsId = mdpsId;
    }

    @Basic
    @Column(name = "mdn_code")
    public String getMdnCode() {
        return mdnCode;
    }

    public void setMdnCode(String mdnCode) {
        this.mdnCode = mdnCode;
    }

    @Basic
    @Column(name = "mdd_name")
    public String getMddName() {
        return mddName;
    }

    public void setMddName(String mddName) {
        this.mddName = mddName;
    }

    @Basic
    @Column(name = "mdn_norm")
    public String getMdnNorm() {
        return mdnNorm;
    }

    public void setMdnNorm(String mdnNorm) {
        this.mdnNorm = mdnNorm;
    }

    @Basic
    @Column(name = "mdn_node")
    public String getMdnNode() {
        return mdnNode;
    }

    public void setMdnNode(String mdnNode) {
        this.mdnNode = mdnNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdInventoryEnOutLogViewEntity that = (MdInventoryEnOutLogViewEntity) o;
        return mieId == that.mieId &&
                Objects.equals(mmfId1, that.mmfId1) &&
                Objects.equals(createDate1, that.createDate1) &&
                Objects.equals(number, that.number) &&
                Objects.equals(stype, that.stype) &&
                Objects.equals(wiId, that.wiId) &&
                Objects.equals(wmsMiId, that.wmsMiId) &&
                Objects.equals(mmfId, that.mmfId) &&
                Objects.equals(price, that.price) &&
                Objects.equals(basePrice, that.basePrice) &&
                Objects.equals(retailPrice, that.retailPrice) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(splitQuantity, that.splitQuantity) &&
                Objects.equals(basicUnit, that.basicUnit) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(baseNumber, that.baseNumber) &&
                Objects.equals(relatedCode, that.relatedCode) &&
                Objects.equals(purchaseUser, that.purchaseUser) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editDate, that.editDate) &&
                Objects.equals(matName, that.matName) &&
                Objects.equals(mmfName, that.mmfName) &&
                Objects.equals(matName2, that.matName2) &&
                Objects.equals(norm2, that.norm2) &&
                Objects.equals(matType, that.matType) &&
                Objects.equals(matType1, that.matType1) &&
                Objects.equals(matType2, that.matType2) &&
                Objects.equals(matType3, that.matType3) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(labelInfo, that.labelInfo) &&
                Objects.equals(applicantName, that.applicantName) &&
                Objects.equals(state, that.state) &&
                Objects.equals(batchCode, that.batchCode) &&
                Objects.equals(mdpId, that.mdpId) &&
                Objects.equals(mdnId, that.mdnId) &&
                Objects.equals(mddId, that.mddId) &&
                Objects.equals(mdpsId, that.mdpsId) &&
                Objects.equals(mdnCode, that.mdnCode) &&
                Objects.equals(mddName, that.mddName) &&
                Objects.equals(mdnNorm, that.mdnNorm) &&
                Objects.equals(mdnNode, that.mdnNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mmfId1, createDate1, number, stype, mieId, wiId, wmsMiId, mmfId, price, basePrice, retailPrice, quantity, splitQuantity, basicUnit, unit, baseNumber, relatedCode, purchaseUser, createDate, editDate, matName, mmfName, matName2, norm2, matType, matType1, matType2, matType3, productName, brand, labelInfo, applicantName, state, batchCode, mdpId, mdnId, mddId, mdpsId, mdnCode, mddName, mdnNorm, mdnNode);
    }

    @Transient
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    @Transient
    public String getCreateRen() {
        return createRen;
    }

    public void setCreateRen(String createRen) {
        this.createRen = createRen;
    }

    public void setBackPrinting(String backPrinting) {
        this.backPrinting = backPrinting;
    }
    @Transient
    public String getBackPrinting() {
        return backPrinting;
    }

    public void setBatchCode1(String batchCode1) {
        this.batchCode1 = batchCode1;
    }
    @Transient
    public String getBatchCode1() {
        return batchCode1;
    }

    public void setValiedDate1(String valiedDate1) {
        this.valiedDate1 = valiedDate1;
    }
    @Transient
    public String getValiedDate1() {
        return valiedDate1;
    }
}
