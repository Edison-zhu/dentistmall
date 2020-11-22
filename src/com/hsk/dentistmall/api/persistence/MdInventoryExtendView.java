package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import java.util.Date;

import javax.persistence.*;

/**
 * MdInventoryExtendView entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_inventory_extend_view")
public class MdInventoryExtendView{

	private Integer mieId;
	private Integer wiId;
	private Integer wmsMiId;
	private Integer mmfId;
	private Double price;
	private Double basePrice;
	private Double quantity;
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
	private Integer rbaId;
	private Integer rbsId;
	private Integer rbbId;
	private String purchaseType;
	private Double allNumber;
	private String itemKeyId;
	private String typeName;
	private Double warningShu;
	private String state;
	private String mmfIdsStr;

	private Double splitQuantity;
	private String mdpId;
	private String mdpsId;
	private Double avgPrice;
	private Double avgRetailPrice;

	private String mmfCode;
	private String mdpName;
	private String mdpsName;
	private String searchName;
	private Integer linkWmsMiId;
	private Integer linkMmfId;

	private String checkPart;
	private String checkParts;
	private String excludeMieIds;
	private String mieIds;
	private String wiIds;

	private String matCode1;

	private Double ratio;

	// Constructors

	/** default constructor */
	public MdInventoryExtendView() {
	}

	/** minimal constructor */
	public MdInventoryExtendView(Integer mieId) {
		this.mieId = mieId;
	}

	/** full constructor */
	public MdInventoryExtendView(Integer mieId, Integer wiId,
			Integer wmsMiId, Integer mmfId, Double price, Double basePrice,
			Double quantity, String basicUnit, String unit, Double baseNumber,
			String relatedCode, String purchaseUser, Date createDate,
			Date editDate, String matName, String mmfName,
			String matName2, String norm2, String matType, String matType1,
			String matType2, String matType3, String productName, String brand,
			String labelInfo, String applicantName, Integer rbaId,
			Integer rbsId, Integer rbbId, String purchaseType,
			Double allNumber, String itemKeyId, String typeName) {
		this.mieId = mieId;
		this.wiId = wiId;
		this.wmsMiId = wmsMiId;
		this.mmfId = mmfId;
		this.price = price;
		this.basePrice = basePrice;
		this.quantity = quantity;
		this.basicUnit = basicUnit;
		this.unit = unit;
		this.baseNumber = baseNumber;
		this.relatedCode = relatedCode;
		this.purchaseUser = purchaseUser;
		this.createDate = createDate;
		this.editDate = editDate;
		this.matName = matName;
		this.mmfName = mmfName;
		this.matName2 = matName2;
		this.norm2 = norm2;
		this.matType = matType;
		this.matType1 = matType1;
		this.matType2 = matType2;
		this.matType3 = matType3;
		this.productName = productName;
		this.brand = brand;
		this.labelInfo = labelInfo;
		this.applicantName = applicantName;
		this.rbaId = rbaId;
		this.rbsId = rbsId;
		this.rbbId = rbbId;
		this.purchaseType = purchaseType;
		this.allNumber = allNumber;
		this.itemKeyId = itemKeyId;
		this.typeName = typeName;
	}

	@Column(name = "ratio")
	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	// Property accessors
	@Id
	@Column(name = "mie_id", nullable = false)
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

	@Column(name = "price", precision = 10)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "base_price", precision = 10, scale = 4)
	public Double getBasePrice() {
		return this.basePrice;
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
	
	@Column(name = "warning_shu" )
	  public Double getWarningShu() {
			return warningShu;
		}

		public void setWarningShu(Double warningShu) {
			this.warningShu = warningShu;
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

	@Column(name = "mat_name2", length = 100)
	public String getMatName2() {
		return this.matName2;
	}

	public void setMatName2(String matName2) {
		this.matName2 = matName2;
	}

	@Column(name = "norm2", length = 100)
	public String getNorm2() {
		return this.norm2;
	}

	public void setNorm2(String norm2) {
		this.norm2 = norm2;
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

	@Column(name = "Label_info", length = 100)
	public String getLabelInfo() {
		return this.labelInfo;
	}

	public void setLabelInfo(String labelInfo) {
		this.labelInfo = labelInfo;
	}

	@Column(name = "applicant_name", length = 100)
	public String getApplicantName() {
		return this.applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
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

	@Column(name = "all_number", precision = 10, scale = 4)
	public Double getAllNumber() {
		return this.allNumber;
	}

	public void setAllNumber(Double allNumber) {
		this.allNumber = allNumber;
	}

	@Column(name = "item_key_id", length = 32)
	public String getItemKeyId() {
		return this.itemKeyId;
	}

	public void setItemKeyId(String itemKeyId) {
		this.itemKeyId = itemKeyId;
	}

	@Column(name = "type_name", length = 302)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Column(name = "state", length = 32)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	//20191205 yangfeng ����mmfid�ַ�����
	@Transient
	public String getMmfIdsStr() {
		return mmfIdsStr;
	}

	public void setMmfIdsStr(String mmfIdsStr) {
		this.mmfIdsStr = mmfIdsStr;
	}

	@Column(name = "mdp_id")
	public String getMdpId() {
		return mdpId;
	}

	public void setMdpId(String mdpId) {
		this.mdpId = mdpId;
	}

	@Column(name = "mdps_id")
	public String getMdpsId() {
		return mdpsId;
	}

	public void setMdpsId(String mdpsId) {
		this.mdpsId = mdpsId;
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

	@Column(name = "split_quantity")
	public Double getSplitQuantity() {
		return splitQuantity;
	}

	public void setSplitQuantity(Double splitQuantity) {
		this.splitQuantity = splitQuantity;
	}

	@Formula("(SELECT a.mmf_code FROM md_materiel_format a WHERE a.mmf_id= mmf_id)")
	public String getMmfCode() {
		return mmfCode;
	}

	public void setMmfCode(String mmfCode) {
		this.mmfCode = mmfCode;
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

	@Transient
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
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

	public void setExcludeMieIds(String excludeMieIds) {
        this.excludeMieIds = excludeMieIds;
    }
	@Transient
    public String getExcludeMieIds() {
        return excludeMieIds;
    }

    @Transient
    public String getMieIds() {
        return mieIds;
    }

    public void setMieIds(String mieIds) {
        this.mieIds = mieIds;
    }


	private String logoPath;
	private Double retailPrice;
	private Double allPrice;
	private Double allRetailPrice;
	private String batchCode;
	private String valiedDate;
	private String basicUnitAccuracy; // 注册证号有效期
	private String backPrinting; //注册证号
	private String norm;

	@Transient
	public String getNorm() {
		return mmfName;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	@Column(name = "batch_code")
	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	@Formula("(SELECT date_format( a.valied_date, '%Y-%m-%d %H:%i:%s' ) FROM md_materiel_info a where a.wms_mi_id = wms_mi_id)")
	public String getValiedDate() {
		return valiedDate;
	}

	public void setValiedDate(String valiedDate) {
		this.valiedDate = valiedDate;
	}

	@Formula("(SELECT a.Basic_unit_accuracy FROM md_materiel_info a where a.wms_mi_id = wms_mi_id)")
	public String getBasicUnitAccuracy() {
		return basicUnitAccuracy;
	}

	public void setBasicUnitAccuracy(String basicUnitAccuracy) {
		this.basicUnitAccuracy = basicUnitAccuracy;
	}

	@Formula("(SELECT a.back_printing FROM md_materiel_info a where a.wms_mi_id = wms_mi_id)")
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

//	@Formula("(SELECT a.retail_price FROM md_materiel_format a where a.mmf_id = mmf_id)")
	@Column(name = "retail_price")
	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}


//	@Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code = (select b.lessen_filecode from md_materiel_info b where b.wms_mi_id = wms_mi_id))")1
	@Transient
	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

    public void setWiIds(String wiIds) {
        this.wiIds = wiIds;
    }
	@Transient
    public String getWiIds() {
        return wiIds;
    }

	@Formula("(SELECT a.mat_code FROM md_materiel_info a where a.wms_mi_id = wms_mi_id)")
	public String getMatCode1() {
		return matCode1;
	}

	public void setMatCode1(String matCode1) {
		this.matCode1 = matCode1;
	}
}