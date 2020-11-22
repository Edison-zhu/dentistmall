package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * MdFavorites entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_carts")
public class MdCarts implements java.io.Serializable {

	// Fields
	private Integer mcId;
	private Integer suiId;
	private Integer wmsMiId;
	private Integer mmfId;
	private Integer mcCount;
	private Integer mcSplitCount;
	private Double price;
	private String mcState;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;
	//前后台类型
	private Integer type;

	// Constructors

	/** default constructor */
	public MdCarts() {
	}
	public MdCarts(Integer mcId){
		this.mcId=mcId;
	}

	/** full constructor */
	public MdCarts(Integer wmsMiId, Integer mcId, Integer suiId,
                   String mcState, String createRen,
                   Date createDate, String editRen, Date editDate) {
		this.wmsMiId = wmsMiId;
		this.mcId = mcId;
		this.suiId = suiId;
		this.mcState = mcState;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mc_id")
	public Integer getMcId() {
		return this.mcId;
	}

	public void setMcId(Integer mcId) {
		this.mcId = mcId;
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
		return mmfId;
	}

	public void setMmfId(Integer mmfId) {
		this.mmfId = mmfId;
	}

	@Column(name = "mc_count")
	public Integer getMcCount() {
		return mcCount;
	}

	public void setMcCount(Integer mcCount) {
		this.mcCount = mcCount;
	}

	@Column(name = "mc_split_count")
	public Integer getMcSplitCount() {
		return mcSplitCount;
	}

	public void setMcSplitCount(Integer mcSplitCount) {
		this.mcSplitCount = mcSplitCount;
	}

	@Column(name = "price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "sui_id")
	public Integer getSuiId() {
		return this.suiId;
	}

	public void setSuiId(Integer suiId) {
		this.suiId = suiId;
	}

	@Column(name = "mc_state", length = 32)
	public String getMcState() {
		return this.mcState;
	}

	public void setMcState(String mcState) {
		this.mcState = mcState;
	}

	@Column(name = "create_ren", length = 512)
	public String getCreateRen() {
		return this.createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_date", length = 0)
	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}