package com.hsk.dentistmall.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MD_ITEM_MX")
public class MdItemMx {
	
	private Integer mimId;
	private Integer itemKeyId;
	private Integer wmsMiId;
	private Integer mmfId;
	private Integer baseNumber;
	private Integer linkWmsMiId;
	private Integer linkMmfId;
	private Integer isMain;
	
	public MdItemMx(){
		
	}
	
	public MdItemMx(Integer mimId){
		this.mimId=mimId;
	}
	
	public MdItemMx(Integer mimId, Integer itemKeyId, Integer wmsMiId,
			Integer mmfId) {
		super();
		this.mimId = mimId;
		this.itemKeyId = itemKeyId;
		this.wmsMiId = wmsMiId;
		this.mmfId = mmfId;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mim_id", unique = true, nullable = false)
	public Integer getMimId() {
		return mimId;
	}
	public void setMimId(Integer mimId) {
		this.mimId = mimId;
	}
	@Column(name = "item_key_id", unique = true, nullable = false)
	public Integer getItemKeyId() {
		return itemKeyId;
	}
	public void setItemKeyId(Integer itemKeyId) {
		this.itemKeyId = itemKeyId;
	}
	@Column(name = "wms_mi_id", unique = true, nullable = false)
	public Integer getWmsMiId() {
		return wmsMiId;
	}
	public void setWmsMiId(Integer wmsMiId) {
		this.wmsMiId = wmsMiId;
	}
	@Column(name = "mmf_id", unique = true, nullable = false)
	public Integer getMmfId() {
		return mmfId;
	}
	public void setMmfId(Integer mmfId) {
		this.mmfId = mmfId;
	}
	@Column(name = "base_number")
	public Integer getBaseNumber() {
		return baseNumber;
	}

	public void setBaseNumber(Integer baseNumber) {
		this.baseNumber = baseNumber;
	}

	@Column(name = "link_wms_mi_id")
	public Integer getLinkWmsMiId() {
		return linkWmsMiId;
	}

	public void setLinkWmsMiId(Integer linkWmsMiId) {
		this.linkWmsMiId = linkWmsMiId;
	}

	@Column(name = "link_mmf_id")
	public Integer getLinkMmfId() {
		return linkMmfId;
	}

	public void setLinkMmfId(Integer linkMmfId) {
		this.linkMmfId = linkMmfId;
	}

	@Column(name = "is_main")
	public Integer getIsMain() {
		return isMain;
	}

	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}
}
