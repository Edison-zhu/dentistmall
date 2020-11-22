package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MdInventoryCollect entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_inventory_collect")
public class MdInventoryCollect implements java.io.Serializable {

	// Fields

	private Integer micId;
	private Integer wiId;
	private Integer suiId;
	private String state;
	private String content;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;

	//增加收藏物料ID 与规格Id
	private Integer wmsMiId;
	private Integer mmfId;

	// Constructors

	/** default constructor */
	public MdInventoryCollect() {
	}

	/** full constructor */
	public MdInventoryCollect(Integer wiId, Integer suiId, String state,
			String content, String createRen, Date createDate,
			String editRen, Date editDate) {
		this.wiId = wiId;
		this.suiId = suiId;
		this.state = state;
		this.content = content;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mic_id", unique = true, nullable = false)
	public Integer getMicId() {
		return this.micId;
	}

	public void setMicId(Integer micId) {
		this.micId = micId;
	}

	@Column(name = "wi_id")
	public Integer getWiId() {
		return this.wiId;
	}

	public void setWiId(Integer wiId) {
		this.wiId = wiId;
	}

	@Column(name = "sui_id")
	public Integer getSuiId() {
		return this.suiId;
	}

	public void setSuiId(Integer suiId) {
		this.suiId = suiId;
	}

	@Column(name = "state", length = 32)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "create_ren", length = 512)
	public String getCreateRen() {
		return this.createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}

	@Column(name = "create_date", length = 0)
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

	@Column(name = "edit_date", length = 0)
	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	@Column(name = "wms_mi_id", length = 0)
	public Integer getWmsMiId() {
		return wmsMiId;
	}

	public void setWmsMiId(Integer wmsMiId) {
		this.wmsMiId = wmsMiId;
	}
	@Column(name = "mmf_id", length = 0)
	public Integer getMmfId() {
		return mmfId;
	}

	public void setMmfId(Integer mmfId) {
		this.mmfId = mmfId;
	}


	/**
	 * 子查询字段
	 */
	private String matName;

	private String lessenFilecode;

	private String MmfName;

	private String matCode;

	private String BasicUnit;

	@Formula("(SELECT a.mat_name  FROM md_materiel_info a WHERE a.wms_mi_id= wms_mi_id)")
	public String getMatName() {
		return matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}
	@Formula("(SELECT c.root_path FROM md_materiel_info a LEFT JOIN sys_file_info c on a.lessen_filecode=c.file_code WHERE a.wms_mi_id= wms_mi_id)")
	public String getLessenFilecode() {
		return lessenFilecode;
	}

	public void setLessenFilecode(String lessenFilecode) {
		this.lessenFilecode = lessenFilecode;
	}
	@Formula("(SELECT b.mmf_name  FROM md_materiel_format b WHERE b.mmf_id= mmf_id)")
	public String getMmfName() {
		return MmfName;
	}

	public void setMmfName(String mmfName) {
		MmfName = mmfName;
	}
	@Formula("(SELECT a.mat_code  FROM md_materiel_info a WHERE a.wms_mi_id= wms_mi_id)")
	public String getMatCode() {
		return matCode;
	}

	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}
	@Formula("(SELECT a.Basic_unit  FROM md_materiel_info a WHERE a.wms_mi_id= wms_mi_id)")
	public String getBasicUnit() {
		return BasicUnit;
	}

	public void setBasicUnit(String basicUnit) {
		BasicUnit = basicUnit;
	}
	@Formula("(SELECT a.brand  FROM md_materiel_info a WHERE a.wms_mi_id= wms_mi_id)")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	@Formula("(SELECT a.product_name  FROM md_materiel_info a WHERE a.wms_mi_id= wms_mi_id)")
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	private String brand;

	private String product_name;

	private String aliasName;

	private String splitNumber;

	private String splitUnit;
	@Formula("(SELECT a.split_unit  FROM md_materiel_info a WHERE a.wms_mi_id= wms_mi_id)")
	public String getSplitUnit() {
		return splitUnit;
	}

	public void setSplitUnit(String splitUnit) {
		this.splitUnit = splitUnit;
	}

	@Formula("(SELECT ROUND((SUM(t1.QUANTITY)-SUM( t1.base_number*t1.ratio)),0) FROM md_inventory_extend_view t1 WHERE t1.wms_mi_id = mdinventor0_.wms_mi_id)")
	public String getSplitNumber() {
		return splitNumber;
	}

	public void setSplitNumber(String splitNumber) {
		this.splitNumber = splitNumber;
	}

	@Formula("(SELECT a.alias_name  FROM md_materiel_info a WHERE a.wms_mi_id= wms_mi_id)")
	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	private Double sumQuantity;
	@Formula("(SELECT SUM(t1.QUANTITY) FROM md_inventory_extend_view t1 WHERE t1.wms_mi_id = wms_mi_id)")
	public Double getSumQuantity() {
		return sumQuantity;
	}
	public void setSumQuantity(Double sumQuantity) {
		this.sumQuantity = sumQuantity;
	}
}