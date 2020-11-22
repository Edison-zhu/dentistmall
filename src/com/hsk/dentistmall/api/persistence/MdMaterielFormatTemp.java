package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * MdMaterielFormat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_materiel_format_temp")
public class MdMaterielFormatTemp implements java.io.Serializable {

	// Fields

	private Integer mmfId;
	private Integer wmsMiId;
	private String mmfCode;
	private String mmfName;
	private Double price;
	private String remark;
	private String state;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;
	private Double oldPrice;
	private String mmfIds;


	/**
	 *厂家
	 */
		private String applicantName;
		/**
		 * 品名
		 */
		private String productName;
		/**
		 * 品牌
		 */
		private String brand;
		/**
		 * 单位
		 */
		private String unit;
		/**
		 * 包装单位
		 */
		private String packingUnit;


	// Constructors
		@Column(name = "applicant_Name" )
	   public String getApplicantName() {
			return applicantName;
		}

		public void setApplicantName(String applicantName) {
			this.applicantName = applicantName;
		}

		@Column(name = "product_name" )
		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		@Column(name = "brand" )
		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		@Column(name = "unit" )
		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		@Column(name = "packing_unit" )
		public String getPackingUnit() {
			return packingUnit;
		}

		public void setPackingUnit(String packingUnit) {
			this.packingUnit = packingUnit;
		}




	/** default constructor */
	public MdMaterielFormatTemp() {
	}

	public MdMaterielFormatTemp(Integer mmfId) {
		this.mmfId=mmfId;
	}

	/** full constructor */
	public MdMaterielFormatTemp(Integer mmfId, Integer wmsMiId, String mmfCode, String mmfName,
                                Double price, String remark, String state, String createRen,
                                Date createDate, String editRen, Date editDate) {
		this.mmfId=mmfId;
		this.wmsMiId = wmsMiId;
		this.mmfCode = mmfCode;
		this.mmfName = mmfName;
		this.price = price;
		this.remark = remark;
		this.state = state;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mmf_id", unique = true, nullable = false)
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

	@Column(name = "mmf_code", length = 100)
	public String getMmfCode() {
		return this.mmfCode;
	}

	public void setMmfCode(String mmfCode) {
		this.mmfCode = mmfCode;
	}

	@Column(name = "mmf_name", length = 100)
	public String getMmfName() {
		return this.mmfName;
	}

	public void setMmfName(String mmfName) {
		this.mmfName = mmfName;
	}

	@Column(name = "price", precision = 10)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "state", length = 30)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "create_ren", length = 512)
	public String getCreateRen() {
		return this.createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "edit_ren", length = 512)
	public String getEditRen() {
		return this.editRen;
	}

	public void setEditRen(String editRen) {
		this.editRen = editRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_date")
	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	@Transient
	public Double getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}
	@Transient
	public String getMmfIds() {
		return mmfIds;
	}

	public void setMmfIds(String mmfIds) {
		this.mmfIds = mmfIds;
	}
	
	  private String matName;
	  
	@Formula("(select t.mat_name from md_materiel_info t where t.wms_mi_id=wms_mi_id)")
	public String getMatName() {
		return matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}
	  
	 
	  
	
}