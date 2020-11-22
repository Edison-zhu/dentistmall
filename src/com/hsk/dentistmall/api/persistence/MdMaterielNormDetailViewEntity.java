package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * author: yangfeng
 * time: 2020/5/27 15:11
 */
@Entity
@Table(name = "md_materiel_norm_detail_view")
public class MdMaterielNormDetailViewEntity {
    private Integer mdnId;
    private Integer mddId;
    private String mdnNorm;
    private Double buyMoney;
    private Double sellMoney;
    private String mdnCode;
    private String mddCode;
    private Integer state;
    private String createRen;
    private Date createDate;
    private Integer suiId;
    private String editRen;
    private Date editDate;
    private String mdnNode;
    private Integer mdpId;
    private Integer mdpsId;
    private String mddName;
    private String brand;
    private String productName;
    private String basicUnit;
    private Double avgPrice;
    private String bathCode;
    private String backPrinting;
    private Integer baseNumber;

    @Basic
    @Id
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
    @Column(name = "mdn_norm")
    public String getMdnNorm() {
        return mdnNorm;
    }

    public void setMdnNorm(String mdnNorm) {
        this.mdnNorm = mdnNorm;
    }

    @Basic
    @Column(name = "buy_money")
    public Double getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(Double buyMoney) {
        this.buyMoney = buyMoney;
    }

    @Basic
    @Column(name = "sell_money")
    public Double getSellMoney() {
        return sellMoney;
    }

    public void setSellMoney(Double sellMoney) {
        this.sellMoney = sellMoney;
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
    @Column(name = "mdd_code")
    public String getMddCode() {
        return mddCode;
    }

    public void setMddCode(String mddCode) {
        this.mddCode = mddCode;
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

    @Basic
    @Column(name = "mdn_node")
    public String getMdnNode() {
        return mdnNode;
    }

    public void setMdnNode(String mdnNode) {
        this.mdnNode = mdnNode;
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
    @Column(name = "mdps_id")
    public Integer getMdpsId() {
        return mdpsId;
    }

    public void setMdpsId(Integer mdpsId) {
        this.mdpsId = mdpsId;
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
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
    @Column(name = "basic_unit")
    public String getBasicUnit() {
        return basicUnit;
    }

    public void setBasicUnit(String basicUnit) {
        this.basicUnit = basicUnit;
    }

    @Basic
    @Column(name = "avg_price")
    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double buyAvg) {
        this.avgPrice = buyAvg;
    }

    @Basic
    @Column(name = "batch_code")
    public String getBathCode() {
        return bathCode;
    }

    public void setBathCode(String bathCode) {
        this.bathCode = bathCode;
    }

    @Basic
    @Column(name = "back_printing")
    public String getBackPrinting() {
        return backPrinting;
    }

    public void setBackPrinting(String backPrinting) {
        this.backPrinting = backPrinting;
    }

    @Basic
    @Column(name = "base_number")
    public Integer getBaseNumber() {
        return baseNumber;
    }

    public void setBaseNumber(Integer baseNumber) {
        this.baseNumber = baseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdMaterielNormDetailViewEntity that = (MdMaterielNormDetailViewEntity) o;
        return mdnId == that.mdnId &&
                Objects.equals(mddId, that.mddId) &&
                Objects.equals(mdnNorm, that.mdnNorm) &&
                Objects.equals(buyMoney, that.buyMoney) &&
                Objects.equals(sellMoney, that.sellMoney) &&
                Objects.equals(mdnCode, that.mdnCode) &&
                Objects.equals(mddCode, that.mddCode) &&
                Objects.equals(state, that.state) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(suiId, that.suiId) &&
                Objects.equals(editRen, that.editRen) &&
                Objects.equals(editDate, that.editDate) &&
                Objects.equals(mdnNode, that.mdnNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdnId, mddId, mdnNorm, buyMoney, sellMoney, mdnCode, mddCode, state, createRen, createDate, suiId, editRen, editDate, mdnNode);
    }
}
