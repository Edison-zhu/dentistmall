package com.hsk.dentistmall.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

/**
 * MdMaterielFormat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_materiel_format")
public class MdMaterielFormat implements Serializable {

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

	private Double ratio;


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
	private Double retailPrice;


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
	public MdMaterielFormat() {
	}
	
	public MdMaterielFormat(Integer mmfId) {
		this.mmfId=mmfId;
	}

	/** full constructor */
	public MdMaterielFormat(Integer mmfId,Integer wmsMiId, String mmfCode, String mmfName,
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


    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }
	@Column(name = "retail_price")
    public Double getRetailPrice() {
        return retailPrice;
    }

    private Double quantity;
	private Double baseNumber;

	@Transient
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Transient
	public Double getBaseNumber() {
		return baseNumber;
	}

	public void setBaseNumber(Double baseNumber) {
		this.baseNumber = baseNumber;
	}

	@Transient
	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	private Double splitQuantity;
	@Formula("(SELECT SUM(e.base_number-(e.QUANTITY*e.ratio)) FROM md_inventory_extend_view e WHERE e.mmf_id =mmf_id)")
	public Double getSplitQuantity() {
		return splitQuantity;
	}

	public void setSplitQuantity(Double splitQuantity) {
		this.splitQuantity = splitQuantity;
	}
}