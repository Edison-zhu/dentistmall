package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@Table(name = "md_item_mx_view")
public class MdItemMxView {
	private Integer mimId;
	private Integer itemKeyId;
	private Integer mmfId;
	private Integer wmsMiId;
	private Integer rbaId;
	private Integer rbsId;
	private Integer rbbId;
	private String purchaseType;
	private String mmfName;
	private String matName;
	private String applicantName;
	private String basicUnit;
	private String splitUnit;
	private String brand;
	private String typeName;
	private Integer linkMmfId;
	private Integer linkWmsMiId;
	private String lessenFilecode;
	private String mmfCode;
	private String logoPath;
	private Double avgPrice;
	private Double retailPrice;
	private Integer wiId;
	private Double baseNumber;
	private String addWiIds;
	private String itemKeyIds;

	private Double ratio;
	private Double quantity;

	// Constructors

	/** default constructor */
	public MdItemMxView() {
	}

	/** minimal constructor */
	public MdItemMxView(Integer mimId) {
		this.mimId = mimId;
	}

	/** full constructor */
	public MdItemMxView(Integer mimId, Integer itemKeyId, Integer mmfId,
			Integer wmsMiId, Integer rbaId, Integer rbsId, Integer rbbId,
			String mmfName, String matName, String basicUnit, String brand,
			String typeName) {
		this.mimId = mimId;
		this.itemKeyId = itemKeyId;
		this.mmfId = mmfId;
		this.wmsMiId = wmsMiId;
		this.rbaId = rbaId;
		this.rbsId = rbsId;
		this.rbbId = rbbId;
		this.mmfName = mmfName;
		this.matName = matName;
		this.basicUnit = basicUnit;
		this.brand = brand;
		this.typeName = typeName;
	}

	// Property accessors
	@Id
	@Column(name = "mim_id", nullable = false)
	public Integer getMimId() {
		return this.mimId;
	}

	public void setMimId(Integer mimId) {
		this.mimId = mimId;
	}

	@Column(name = "item_key_id")
	public Integer getItemKeyId() {
		return this.itemKeyId;
	}

	public void setItemKeyId(Integer itemKeyId) {
		this.itemKeyId = itemKeyId;
	}

	@Column(name = "mmf_id")
	public Integer getMmfId() {
		return this.mmfId;
	}

	public void setMmfId(Integer mmfId) {
		this.mmfId = mmfId;
	}

	@Column(name = "wms_mi_id")
	public Integer getWmsMiId() {
		return this.wmsMiId;
	}

	public void setWmsMiId(Integer wmsMiId) {
		this.wmsMiId = wmsMiId;
	}

	@Column(name = "rba_id")
	public Integer getRbaId() {
		return this.rbaId;
	}

	public void setRbaId(Integer rbaId) {
		this.rbaId = rbaId;
	}

	@Column(name = "rbs_id")
	public Integer getRbsId() {
		return this.rbsId;
	}

	public void setRbsId(Integer rbsId) {
		this.rbsId = rbsId;
	}

	@Column(name = "rbb_id")
	public Integer getRbbId() {
		return this.rbbId;
	}

	public void setRbbId(Integer rbbId) {
		this.rbbId = rbbId;
	}
	
	@Column(name = "purchase_type")
	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	@Column(name = "mmf_name", length = 100)
	public String getMmfName() {
		return this.mmfName;
	}

	public void setMmfName(String mmfName) {
		this.mmfName = mmfName;
	}

	@Column(name = "mat_name", length = 512)
	public String getMatName() {
		return this.matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}
	
	@Column(name = "applicant_Name", length = 512)
	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	@Column(name = "Basic_unit", length = 64)
	public String getBasicUnit() {
		return this.basicUnit;
	}

	public void setBasicUnit(String basicUnit) {
		this.basicUnit = basicUnit;
	}

	@Column(name = "split_unit")
	public String getSplitUnit() {
		return splitUnit;
	}

	public void setSplitUnit(String splitUnit) {
		this.splitUnit = splitUnit;
	}

	@Column(name = "brand", length = 100)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "type_name", length = 302)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public void setLinkMmfId(Integer linkMmfId) {
		this.linkMmfId = linkMmfId;
	}
	@Column(name = "link_mmf_id")
	public Integer getLinkMmfId() {
		return linkMmfId;
	}


	public void setLinkWmsMiId(Integer linkWmsMiId) {
		this.linkWmsMiId = linkWmsMiId;
	}
	@Column(name = "link_wms_mi_id")
	public Integer getLinkWmsMiId() {
		return linkWmsMiId;
	}

	@Column(name = "lessen_filecode")
	public String getLessenFilecode() {
		return lessenFilecode;
	}

	public void setLessenFilecode(String lessenFilecode) {
		this.lessenFilecode = lessenFilecode;
	}

	@Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code= lessen_filecode)")
	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	@Column(name = "mmf_code")
	public String getMmfCode() {
		return mmfCode;
	}

	public void setMmfCode(String mmfCode) {
		this.mmfCode = mmfCode;
	}

	@Column(name = "avg_price")
	public Double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}

	@Column(name = "retail_price")
	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	@Column(name = "wi_id")
	public Integer getWiId() {
		return wiId;
	}

	public void setWiId(Integer wiId) {
		this.wiId = wiId;
	}

	@Column(name = "base_number")
	public Double getBaseNumber() {
		return baseNumber;
	}

	public void setBaseNumber(Double baseNumber) {
		this.baseNumber = baseNumber;
	}


	public void setItemKeyIds(String itemKeyIds) {
		this.itemKeyIds = itemKeyIds;
	}
	@Transient
	public String getItemKeyIds() {
		return itemKeyIds;
	}

	@Column(name = "ratio")
	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	@Column(name = "quantity")
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
}
