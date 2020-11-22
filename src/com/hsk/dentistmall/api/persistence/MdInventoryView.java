package com.hsk.dentistmall.api.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "md_inventory_view")
public class MdInventoryView {
	
	private Integer wiId;
	private Integer rbaId;
	private Integer rbsId;
	private Integer rbbId;

	private String purchaseType;
	private Integer wmsMiId;
	private Integer mmfId;
	private String itemKeyId;
	private String state;
	private Double ratio;
	private Double quantity;
	private String basicUnit;
	private String unit;
	private Double baseNumber;
	private Double warningShu;
	private Date createDate;
	private Date editDate;
	private Double maxShu;
	private Integer minDay;
	private String matName;
	private String matCode;
	private String mmfName;
	private Integer wmsMiId2;
	private Integer mmfId2;
	private String lessenFilecode;
	private String logoPath;
	private Integer mdpId;
	private Integer mdpsId;
	private String brand;
	private String mmfCode;
	private Integer mieId;

	private String mdpName;
	private String mdpsName;
	
	private Integer micId;
	private String tabOrder;
	private String itemKeyIdStr;
	private String matNamePy;
	private String mmfNamePy;
	private String productFactory;
	private String productName;
	private String wmsMiIds;

	private String searchName;
	private String excludeWiIds;

	private Double avgPrice;
	private Double avgRetailPrice;

	private Integer dateIsNot; // ��Ч��Ԥ��
//	private Integer noInv; // �޿��

	private Date valiedDate;
	private Integer warningDay;
	private String mieIds;
	private String wiIds;
	private Double splitQuantity;
	private String searchMatName;
	private String searchMmfName;
	private Integer wowMxId;
	private Double baseNumber1;
	private Double splitNumber1;
	private String checkPart;
	private String checkParts;
	private String valiedDate1;

	// Constructors

	/** default constructor */
	public MdInventoryView() {
	}

	/** minimal constructor */
	public MdInventoryView(Integer wiId) {
		this.wiId = wiId;
	}

	/** full constructor */
	public MdInventoryView(Integer wiId, Integer rbaId, Integer rbsId,
			Integer rbbId, String purchaseType, Integer wmsMiId, Integer mmfId,
			String itemKeyId, String state, Double ratio, Double quantity,
			String basicUnit, String unit, Double baseNumber,
			Double warningShu, Date createDate, Date editDate,
			Double maxShu, Integer minDay, String matName, String mmfName,
			Integer wmsMiId2, Integer mmfId2) {
		this.wiId = wiId;
		this.rbaId = rbaId;
		this.rbsId = rbsId;
		this.rbbId = rbbId;
		this.purchaseType = purchaseType;
		this.wmsMiId = wmsMiId;
		this.mmfId = mmfId;
		this.itemKeyId = itemKeyId;
		this.state = state;
		this.ratio = ratio;
		this.quantity = quantity;
		this.basicUnit = basicUnit;
		this.unit = unit;
		this.baseNumber = baseNumber;
		this.warningShu = warningShu;
		this.createDate = createDate;
		this.editDate = editDate;
		this.maxShu = maxShu;
		this.minDay = minDay;
		this.matName = matName;
		this.mmfName = mmfName;
		this.wmsMiId2 = wmsMiId2;
		this.mmfId2 = mmfId2;
	}

	@Column(name = "product_name")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	// Property accessors
	@Id
	@Column(name = "wi_id", nullable = false)
	public Integer getWiId() {
		return this.wiId;
	}

	public void setWiId(Integer wiId) {
		this.wiId = wiId;
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

	@Column(name = "purchase_type", length = 32)
	public String getPurchaseType() {
		return this.purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
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

	@Column(name = "ITEM_KEY_ID", length = 32)
	public String getItemKeyId() {
		return this.itemKeyId;
	}

	public void setItemKeyId(String itemKeyId) {
		this.itemKeyId = itemKeyId;
	}

	@Column(name = "state", length = 32)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ratio", precision = 10, scale = 4)
	public Double getRatio() {
		return this.ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	@Column(name = "QUANTITY", precision = 10, scale = 4)
	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "Basic_unit", length = 64)
	public String getBasicUnit() {
		return this.basicUnit;
	}

	public void setBasicUnit(String basicUnit) {
		this.basicUnit = basicUnit;
	}

	@Column(name = "unit", length = 64)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "base_number", precision = 10, scale = 4)
	public Double getBaseNumber() {
		return this.baseNumber;
	}

	public void setBaseNumber(Double baseNumber) {
		this.baseNumber = baseNumber;
	}

	@Column(name = "warning_shu", precision = 10, scale = 4)
	public Double getWarningShu() {
		return this.warningShu;
	}

	public void setWarningShu(Double warningShu) {
		this.warningShu = warningShu;
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

	@Column(name = "max_shu", precision = 10, scale = 4)
	public Double getMaxShu() {
		return this.maxShu;
	}

	public void setMaxShu(Double maxShu) {
		this.maxShu = maxShu;
	}

	@Column(name = "min_day")
	public Integer getMinDay() {
		return this.minDay;
	}

	public void setMinDay(Integer minDay) {
		this.minDay = minDay;
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

	@Column(name = "wms_mi_id2")
	public Integer getWmsMiId2() {
		return this.wmsMiId2;
	}

	public void setWmsMiId2(Integer wmsMiId2) {
		this.wmsMiId2 = wmsMiId2;
	}

	@Column(name = "mmf_id2")
	public Integer getMmfId2() {
		return this.mmfId2;
	}

	public void setMmfId2(Integer mmfId2) {
		this.mmfId2 = mmfId2;
	}
	@Transient
	public String getTabOrder() {
		return tabOrder;
	}

	public void setTabOrder(String tabOrder) {
		this.tabOrder = tabOrder;
	}
	@Transient
	public String getItemKeyIdStr() {
		return itemKeyIdStr;
	}

	public void setItemKeyIdStr(String itemKeyIdStr) {
		this.itemKeyIdStr = itemKeyIdStr;
	}
	@Transient
	public Integer getMicId() {
		return micId;
	}

	public void setMicId(Integer micId) {
		this.micId = micId;
	}
	
	@Column(name = "mat_name_py", length = 100)
	public String getMatNamePy() {
		return matNamePy;
	}

	public void setMatNamePy(String matNamePy) {
		this.matNamePy = matNamePy;
	}
	@Column(name = "mmf_name_py", length = 100)
	public String getMmfNamePy() {
		return mmfNamePy;
	}

	public void setMmfNamePy(String mmfNamePy) {
		this.mmfNamePy = mmfNamePy;
	}
//	@Formula("(SELECT a.mat_code FROM md_materiel_info a WHERE a.wms_mi_id= wms_mi_id2)")
	@Column(name = "mat_code", length = 100)
	public String getMatCode() {
		return matCode;
	}

	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}

	@Column(name = "product_factory")
	public String getProductFactory() {
		return productFactory;
	}

	public void setProductFactory(String productFacory) {
		this.productFactory = productFacory;
	}

	@Transient
	public String getWmsMiIds() {
		return wmsMiIds;
	}

	public void setWmsMiIds(String wmsMiIds) {
		this.wmsMiIds = wmsMiIds;
	}

	private String safeIsNot;
	@Transient
	public String getSafeIsNot() {
		return safeIsNot;
	}

	public void setSafeIsNot(String safeIsNot) {
		this.safeIsNot = safeIsNot;
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

	@Transient
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}


	public void setExcludeWiIds(String excludeWiIds) {
		this.excludeWiIds = excludeWiIds;
	}
	@Transient
	public String getExcludeWiIds() {
		return excludeWiIds;
	}

	@Column(name = "mdp_id")
	public Integer getMdpId() {
		return mdpId;
	}

	public void setMdpId(Integer mdpId) {
		this.mdpId = mdpId;
	}

	@Column(name = "mdps_id")
	public Integer getMdpsId() {
		return mdpsId;
	}

	public void setMdpsId(Integer mdpsId) {
		this.mdpsId = mdpsId;
	}

	@Formula("(SELECT a.mdp_name FROM md_materiel_part a WHERE a.mdp_id= mdp_id)")
	public String getMdpName() {
		return mdpName;
	}

	public void setMdpName(String mdpName) {
		this.mdpName = mdpName;
	}

	@Formula("(SELECT a.mdps_name FROM md_materiel_part_second a WHERE a.mdps_id= mdps_id)")
	public String getMdpsName() {
		return mdpsName;
	}

	public void setMdpsName(String mdpsName) {
		this.mdpsName = mdpsName;
	}

	@Column(name = "brand")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "mmf_code")
	public String getMmfCode() {
		return mmfCode;
	}

	public void setMmfCode(String mmfCode) {
		this.mmfCode = mmfCode;
	}

	@Column(name = "mie_id")
	public Integer getMieId() {
		return mieId;
	}

	public void setMieId(Integer mieId) {
		this.mieId = mieId;
	}

    @Column(name = "avg_price")
	public Double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}

	@Column(name = "avg_retail_price")
	public Double getAvgRetailPrice() {
		return avgRetailPrice;
	}

	public void setAvgRetailPrice(Double avgRetailPrice) {
		this.avgRetailPrice = avgRetailPrice;
	}

	@Transient
	public Integer getDateIsNot() {
		return dateIsNot;
	}

	public void setDateIsNot(Integer dateIsNot) {
		this.dateIsNot = dateIsNot;
	}

//	@Transient
//	public Integer getNoInv() {
//		return noInv;
//	}
//
//	public void setNoInv(Integer noInv) {
//		this.noInv = noInv;
//	}

	@Column(name = "valied_date")
	public Date getValiedDate() {
		return valiedDate;
	}

	public void setValiedDate(Date valiedDate) {
		this.valiedDate = valiedDate;
	}

	@Column(name = "warning_day")
	public Integer getWarningDay() {
		return warningDay;
	}

	public void setWarningDay(Integer warningDay) {
		this.warningDay = warningDay;
	}


    public void setMieIds(String mieIds) {
        this.mieIds = mieIds;
    }
	@Transient
    public String getMieIds() {
        return mieIds;
    }

	public void setWiIds(String wiIds) {
		this.wiIds = wiIds;
	}
	@Transient
	public String getWiIds() {
		return wiIds;
	}

    public void setSplitQuantity(Double splitQuantity) {
        this.splitQuantity = splitQuantity;
    }
	@Transient
    public Double getSplitQuantity() {
        return splitQuantity;
    }

    private Double price;
	private Double retailPrice;
	private Double allPrice;
	private Double allRetailPrice;
	private String batchCode;
	private String basicUnitAccuracy; // ע��֤����Ч��
	private String backPrinting; //ע��֤��
	private String norm;
	private String matCode1;

	@Transient
	public String getNorm() {
		return mmfName;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

//	@Formula("(SELECT a.batch_code FROM md_materiel_info a where a.wms_mi_id = wms_mi_id2)")
	@Transient
	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	@Formula("(SELECT a.Basic_unit_accuracy FROM md_materiel_info a where a.wms_mi_id = wms_mi_id2)")
	public String getBasicUnitAccuracy() {
		return basicUnitAccuracy;
	}

	public void setBasicUnitAccuracy(String basicUnitAccuracy) {
		this.basicUnitAccuracy = basicUnitAccuracy;
	}

//	@Formula("(SELECT a.back_printing FROM md_materiel_info a where a.wms_mi_id = wms_mi_id2)")
	@Transient
	public String getBackPrinting() {
		return backPrinting;
	}

	public void setBackPrinting(String backPrinting) {
		this.backPrinting = backPrinting;
	}

	@Transient
	public Double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}

	@Transient
	public Double getAllRetailPrice() {
		return allRetailPrice;
	}

	public void setAllRetailPrice(Double allRetailPrice) {
		this.allRetailPrice = allRetailPrice;
	}

//	@Formula("(SELECT a.retail_price FROM md_materiel_format a where a.mmf_id = mmf_id2)")
	@Column(name = "retail_price")
	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	@Column(name = "price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Formula("(SELECT a.mat_code FROM md_materiel_info a where a.wms_mi_id = wms_mi_id2)")
	public String getMatCode1() {
		return matCode1;
	}

	public void setMatCode1(String matCode1) {
		this.matCode1 = matCode1;
	}

    public void setSearchMatName(String searchMatName) {
        this.searchMatName = searchMatName;
    }
	@Transient
    public String getSearchMatName() {
        return searchMatName;
    }

	public void setSearchMmfName(String searchMmfName) {
		this.searchMmfName = searchMmfName;
	}
	@Transient
	public String getSearchMmfName() {
		return searchMmfName;
	}

    public void setWowMxId(Integer wowMxId) {
        this.wowMxId = wowMxId;
    }
	@Transient
    public Integer getWowMxId() {
        return wowMxId;
    }

    public void setBaseNumber1(Double baseNumber1) {
        this.baseNumber1 = baseNumber1;
    }
	@Transient
    public Double getBaseNumber1() {
        return baseNumber1;
    }

	public void setSplitNumber1(Double splitNumber1) {
		this.splitNumber1 = splitNumber1;
	}
	@Transient
	public Double getSplitNumber1() {
		return splitNumber1;
	}

	@Transient
	public String getCheckPart() {
		return checkPart;
	}

	public void setCheckPart(String checkPart) {
		this.checkPart = checkPart;
	}

	@Transient
	public String getCheckParts() {
		return checkParts;
	}

	public void setCheckParts(String checkParts) {
		this.checkParts = checkParts;
	}

	public void setValiedDate1(String valiedDate1) {
		this.valiedDate1 = valiedDate1;
	}
	@Transient
	public String getValiedDate1() {
		return valiedDate1;
	}
}
