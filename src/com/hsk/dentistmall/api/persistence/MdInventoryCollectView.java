package com.hsk.dentistmall.api.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "md_inventory_collect_view")
public class MdInventoryCollectView {
	
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
	private Integer suiId;
	private Integer micId;
	private String matName;
	private String mmfName;
	private String matNamePy;
	private String mmfNamePy;
	private String productName;

	// Constructors

	/** default constructor */
	public MdInventoryCollectView() {
	}

	/** minimal constructor */
	public MdInventoryCollectView(Integer micId) {
		this.micId = micId;
	}

	/** full constructor */
	public MdInventoryCollectView(Integer wiId, Integer rbaId, Integer rbsId,
			Integer rbbId, String purchaseType, Integer wmsMiId, Integer mmfId,
			String itemKeyId, String state, Double ratio, Double quantity,
			String basicUnit, String unit, Double baseNumber,
			Double warningShu, Date createDate, Date editDate,
			Double maxShu, Integer minDay, Integer suiId, Integer micId,
			String matName, String mmfName) {
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
		this.suiId = suiId;
		this.micId = micId;
		this.matName = matName;
		this.mmfName = mmfName;
	}

	// Property accessors
	@Id
	@Column(name = "mic_id", nullable = false)
	public Integer getMicId() {
		return this.micId;
	}

	public void setMicId(Integer micId) {
		this.micId = micId;
	}
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

	@Column(name = "create_date", length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

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

	@Column(name = "sui_id")
	public Integer getSuiId() {
		return this.suiId;
	}

	public void setSuiId(Integer suiId) {
		this.suiId = suiId;
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

	@Column(name = "product_name")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	//库存数量
//	private
}
