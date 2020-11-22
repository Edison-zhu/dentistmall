package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/1/5 15:33
 */
@Entity
@Table(name = "md_order_after_sale_extend")
public class MdOrderAfterSaleExtendEntity {
    private Integer masExId;
    private Integer masId;
    private String orgName;
    private String supplierCtlContent;
    private String buyerCtlContent;
    private Date ctlDate;
    private String asRemarks;
    
    private Double as_money;
    private String asName;

    private Double money;

    @Id
    @Column(name = "mas_ex_id")
    @GeneratedValue(strategy = IDENTITY)
    public Integer getMasExId() {
        return masExId;
    }

    public void setMasExId(Integer masExId) {
        this.masExId = masExId;
    }

    @Basic
    @Column(name = "mas_id")
    public Integer getMasId() {
        return masId;
    }

    public void setMasId(Integer masId) {
        this.masId = masId;
    }

    @Basic
    @Column(name = "org_name")
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Basic
    @Column(name = "supplier_ctl_content")
    public String getSupplierCtlContent() {
        return supplierCtlContent;
    }

    public void setSupplierCtlContent(String supplierCtlContent) {
        this.supplierCtlContent = supplierCtlContent;
    }

    @Basic
    @Column(name = "buyer_ctl_content")
    public String getBuyerCtlContent() {
        return buyerCtlContent;
    }

    public void setBuyerCtlContent(String buyerCtlContent) {
        this.buyerCtlContent = buyerCtlContent;
    }

    @Basic
    @Column(name = "ctl_date")
    public Date getCtlDate() {
        return ctlDate;
    }

    public void setCtlDate(Date ctlDate) {
        this.ctlDate = ctlDate;
    }

    @Basic
    @Column(name = "as_remarks")
    public String getAsRemarks() {
        return asRemarks;
    }

    public void setAsRemarks(String asRemarks) {
        this.asRemarks = asRemarks;
    }

    @Formula("(SELECT a.after_sale_money FROM md_order_after_sale a WHERE a.mas_id = mas_id)")
    public Double getAs_money() {
        return as_money;
    }

    public void setAs_money(Double as_money) {
        this.as_money = as_money;
    }

    @Formula("(SELECT a.param_name FROM sys_parameter a,sys_parameter b, md_order_after_sale c WHERE c.mas_id = mas_id and a.param_value= c.after_sale and a.sys_spar_id=b.spar_id and b.param_code='PAR191224031732706')")
    public String getAsName() {
        return asName;
    }

    public void setAsName(String asName) {
        this.asName = asName;
    }

    @Column(name = "money")
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
