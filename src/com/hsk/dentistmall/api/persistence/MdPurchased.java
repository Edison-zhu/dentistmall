package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "md_purchased")
public class MdPurchased {
        //purchased_id
        private Integer purchasedId;
        //wms_mi_id
        private Integer wmsMiId;
        //sui_id
        private Integer suiId;
        //ph_state
        private String phState;
        //price
        private Double price;

        private Integer mcCount;
        private Integer mmfId;
        private String createRen;
        private Date createDate;
        private String editRen;
        private Date editDate;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "purchased_id")
    public Integer getPurchasedId() {
        return purchasedId;
    }

    public void setPurchasedId(Integer purchasedId) {
        this.purchasedId = purchasedId;
    }
    @Column(name = "wms_mi_id")
    public Integer getWmsMiId() {
        return wmsMiId;
    }

    public void setWmsMiId(Integer wmsMiId) {
        this.wmsMiId = wmsMiId;
    }
    @Column(name = "sui_id")
    public Integer getSuiId() {
        return suiId;
    }

    public void setSuiId(Integer suiId) {
        this.suiId = suiId;
    }
    @Column(name = "ph_state", length = 32)
    public String getPhState() {
        return phState;
    }

    public void setPhState(String phState) {
        this.phState = phState;
    }
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    @Column(name = "mc_count")
    public Integer getMcCount() {
        return mcCount;
    }

    public void setMcCount(Integer mcCount) {
        this.mcCount = mcCount;
    }
    @Column(name = "mmf_id")
    public Integer getMmfId() {
        return mmfId;
    }

    public void setMmfId(Integer mmfId) {
        this.mmfId = mmfId;
    }
    @Column(name = "create_ren", length = 512)
    public String getCreateRen() {
        return createRen;
    }

    public void setCreateRen(String createRen) {
        this.createRen = createRen;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", length = 0)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Column(name = "edit_ren", length = 512)
    public String getEditRen() {
        return editRen;
    }

    public void setEditRen(String editRen) {
        this.editRen = editRen;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "edit_date", length = 0)
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }



}
