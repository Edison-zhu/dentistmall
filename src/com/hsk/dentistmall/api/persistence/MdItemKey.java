package com.hsk.dentistmall.api.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MdItemKey entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_item_key")
public class MdItemKey implements java.io.Serializable {

	// Fields

	private Integer itemKeyId;
	private Integer rbaId;
	private Integer rbsId;
	private Integer rbbId;
	private String purchaseType;
	private String matName;
	private String mmfName;
	private String matType;
	private String matType1;
	private String matType2;
	private String matType3;
	private String productName;
	private String brand;
	private String labelInfo;
	private String matNamePy;
	private String mmfNamePy;

	// Constructors

	/** default constructor */
	public MdItemKey() {
	}

	/** full constructor */
	public MdItemKey(Integer rbaId, Integer rbsId, Integer rbbId,
			String purchaseType, String matName, String mmfName,
			String matType, String matType1, String matType2, String matType3,
			String productName, String brand, String labelInfo) {
		this.rbaId = rbaId;
		this.rbsId = rbsId;
		this.rbbId = rbbId;
		this.purchaseType = purchaseType;
		this.matName = matName;
		this.mmfName = mmfName;
		this.matType = matType;
		this.matType1 = matType1;
		this.matType2 = matType2;
		this.matType3 = matType3;
		this.productName = productName;
		this.brand = brand;
		this.labelInfo = labelInfo;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "item_key_id", unique = true, nullable = false)
	public Integer getItemKeyId() {
		return this.itemKeyId;
	}

	public void setItemKeyId(Integer itemKeyId) {
		this.itemKeyId = itemKeyId;
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
	
	

}