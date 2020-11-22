package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * MdInventoryExtend entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_inventory_extend")
public class MdInventoryExtend implements Serializable {

	// Fields

	private Integer mieId;
	private Integer wiId;
	private Integer wmsMiId;
	private Integer mmfId;
	private Double price;
	private Double basePrice;
	private Double quantity;
	private Double splitQuantity;
	private String unit;
	private String basicUnit;
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

	private Integer mdpId;
	private String mdpName;
	private Integer mdpsId;
	private String mdpsName;
	private Integer mddId;
	private String mddName;
	private Integer mdnId;
	private String mdnNorm;
	private String mdnNode;

	private String mdnCode;

	private Double retailPrice;

	private Integer rbaId;
	private Integer rbsId;
	private Integer rbbId;
	private String backPrinting;
	private Integer wewMxId;
	private Integer linkWmsMiId;
	private Integer linkMmfId;
	private String batchCode;

	private String aliasName;

	// Constructors

	/** default constructor */
	public MdInventoryExtend() {
	}

	/** full constructor */
	public MdInventoryExtend(Integer wiId, Integer wmsMiId, Integer mmfId,
			Double quantity, String basicUnit, Double baseNumber,
			String relatedCode, String purchaseUser, Date createDate,
			Date editDate) {
		this.wiId = wiId;
		this.wmsMiId = wmsMiId;
		this.mmfId = mmfId;
		this.quantity = quantity;
		this.basicUnit = basicUnit;
		this.baseNumber = baseNumber;
		this.relatedCode = relatedCode;
		this.purchaseUser = purchaseUser;
		this.createDate = createDate;
		this.editDate = editDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mie_id", unique = true, nullable = false)
	public Integer getMieId() {
		return this.mieId;
	}

	public void setMieId(Integer mieId) {
		this.mieId = mieId;
	}

	@Column(name = "wi_id")
	public Integer getWiId() {
		return this.wiId;
	}

	public void setWiId(Integer wiId) {
		this.wiId = wiId;
	}

	@Column(name = "wms_mi_id")
	public Integer getWmsMiId() {
		return this.wmsMiId;
	}

	public void setWmsMiId(Integer wmsMiId) {
		this.wmsMiId = wmsMiId;
	}

	@Column(name = "mmf_id")
	public Integer getMmfId() {
		return this.mmfId;
	}

	public void setMmfId(Integer mmfId) {
		this.mmfId = mmfId;
	}
	
	@Column(name = "price", precision = 10, scale = 4)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	@Column(name = "base_price", precision = 10, scale = 4)
	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	@Column(name = "QUANTITY", precision = 10, scale = 4)
	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "split_quantity", precision = 10, scale = 4)
	public Double getSplitQuantity() {
		return splitQuantity;
	}

	public void setSplitQuantity(Double splitQuantity) {
		this.splitQuantity = splitQuantity;
	}

	@Column(name = "unit" )
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "Basic_unit", length = 64)
	public String getBasicUnit() {
		return this.basicUnit;
	}

	public void setBasicUnit(String basicUnit) {
		this.basicUnit = basicUnit;
	}

	@Column(name = "base_number", precision = 10, scale = 4)
	public Double getBaseNumber() {
		return this.baseNumber;
	}

	public void setBaseNumber(Double baseNumber) {
		this.baseNumber = baseNumber;
	}

	@Column(name = "related_code", length = 50)
	public String getRelatedCode() {
		return this.relatedCode;
	}

	public void setRelatedCode(String relatedCode) {
		this.relatedCode = relatedCode;
	}

	@Column(name = "purchase_user", length = 100)
	public String getPurchaseUser() {
		return this.purchaseUser;
	}

	public void setPurchaseUser(String purchaseUser) {
		this.purchaseUser = purchaseUser;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_date", length = 0)
	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	
	@Column(name = "mat_name", length = 100)
	public String getMatName() {
		return this.matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}

	@Column(name = "mmf_name", length = 100)
	public String getMmfName() {
		return this.mmfName;
	}

	public void setMmfName(String mmfName) {
		this.mmfName = mmfName;
	}

	@Column(name = "mat_type", length = 32)
	public String getMatType() {
		return this.matType;
	}

	public void setMatType(String matType) {
		this.matType = matType;
	}

	@Column(name = "mat_type1", length = 32)
	public String getMatType1() {
		return this.matType1;
	}

	public void setMatType1(String matType1) {
		this.matType1 = matType1;
	}

	@Column(name = "mat_type2", length = 32)
	public String getMatType2() {
		return this.matType2;
	}

	public void setMatType2(String matType2) {
		this.matType2 = matType2;
	}

	@Column(name = "mat_type3", length = 32)
	public String getMatType3() {
		return this.matType3;
	}

	public void setMatType3(String matType3) {
		this.matType3 = matType3;
	}

	@Column(name = "product_name", length = 100)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "brand", length = 100)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "Label_info", length = 64)
	public String getLabelInfo() {
		return this.labelInfo;
	}

	public void setLabelInfo(String labelInfo) {
		this.labelInfo = labelInfo;
	}
	@Column(name = "mat_name2", length = 100)
	public String getMatName2() {
		return matName2;
	}

	public void setMatName2(String matName2) {
		this.matName2 = matName2;
	}
	@Column(name = "norm2", length = 100)
	public String getNorm2() {
		return norm2;
	}

	public void setNorm2(String norm2) {
		this.norm2 = norm2;
	}
	@Column(name = "applicant_name", length = 100)
	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	@Column(name = "state", length = 100)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "mdp_id")
	public Integer getMdpId() {
		return mdpId;
	}

	public void setMdpId(Integer mdpId) {
		this.mdpId = mdpId;
	}

	@Column(name = "mdd_id")
	public Integer getMddId() {
		return mddId;
	}

	public void setMddId(Integer mddId) {
		this.mddId = mddId;
	}

	@Formula("(SELECT a.mdp_name FROM md_materiel_part a WHERE a.mdp_id= mdp_id)")
	public String getMdpName() {
		return mdpName;
	}

	public void setMdpName(String mdpName) {
		this.mdpName = mdpName;
	}

	@Column(name = "mdn_node")
	public String getMdnNode() {
		return mdnNode;
	}

	public void setMdnNode(String mdnNode) {
		this.mdnNode = mdnNode;
	}

	@Column(name = "mdps_id")
	public Integer getMdpsId() {
		return mdpsId;
	}

	public void setMdpsId(Integer mdpsId) {
		this.mdpsId = mdpsId;
	}

	@Column(name = "mdn_id")
	public Integer getMdnId() {
		return mdnId;
	}

	public void setMdnId(Integer mddnId) {
		this.mdnId = mdnId;
	}

	@Formula("(SELECT a.mdps_name FROM md_materiel_part_second a WHERE a.mdps_id= mdps_id)")
	public String getMdpsName() {
		return mdpsName;
	}

	public void setMdpsName(String mdpsName) {
		this.mdpsName = mdpsName;
	}

	@Column(name = "retail_price")
	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	@Column(name = "mdn_code")
	public String getMdnCode() {
		return mdnCode;
	}

	public void setMdnCode(String mdnCode) {
		this.mdnCode = mdnCode;
	}

	@Column(name = "mdd_name")
	public String getMddName() {
		return mddName;
	}

	public void setMddName(String mddName) {
		this.mddName = mddName;
	}

	@Column(name = "mdn_norm")
	public String getMdnNorm() {
		return mdnNorm;
	}

	public void setMdnNorm(String mdnNorm) {
		this.mdnNorm = mdnNorm;
	}

	@Transient
	public Integer getRbaId() {
		return rbaId;
	}

	public void setRbaId(Integer rbaId) {
		this.rbaId = rbaId;
	}

	@Transient
	public Integer getRbsId() {
		return rbsId;
	}

	public void setRbsId(Integer rbsId) {
		this.rbsId = rbsId;
	}

	@Transient
	public Integer getRbbId() {
		return rbbId;
	}

	public void setRbbId(Integer rbbId) {
		this.rbbId = rbbId;
	}

    public void setBackPrinting(String backPrinting) {
        this.backPrinting = backPrinting;
    }

	@Column(name = "back_Printing")
    public String getBackPrinting() {
        return backPrinting;
    }

	public void setWewMxId(Integer wewMxId) {
		this.wewMxId = wewMxId;
	}
	@Column(name = "wew_mx_id")
	public Integer getWewMxId() {
		return wewMxId;
	}


	public void setLinkWmsMiId(Integer linkWmsMiId) {
		this.linkWmsMiId = linkWmsMiId;
	}
	@Column(name = "link_wms_mi_id")
	public Integer getLinkWmsMiId() {
		return linkWmsMiId;
	}


	public void setLinkMmfId(Integer linkMmfId) {
		this.linkMmfId = linkMmfId;
	}
 	@Column(name = "link_mmf_id")
	public Integer getLinkMmfId() {
		return linkMmfId;
	}

	@Column(name = "batch_code")
	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	@Column(name = "alias_name")
	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
}