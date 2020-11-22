package com.hsk.dentistmall.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "md_price_log")
public class MdPriceLog {
	
	private Integer mplId;
	private Integer mmfId;
	private Double price;
	private Integer suiId;
	private Date changeDate;
	private String mmfIds;
	
	public MdPriceLog(){
		
	}
	
	public MdPriceLog(Integer mplId){
		this.mplId=mplId;
	}
	
	public MdPriceLog(Integer mplId, Integer mmfId, Double price,
			Integer suiId, Date changeDate) {
		super();
		this.mplId = mplId;
		this.mmfId = mmfId;
		this.price = price;
		this.suiId = suiId;
		this.changeDate = changeDate;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mpl_id", nullable = false)
	public Integer getMplId() {
		return mplId;
	}
	public void setMplId(Integer mplId) {
		this.mplId = mplId;
	}
	@Column(name = "mmf_id", nullable = false)
	public Integer getMmfId() {
		return mmfId;
	}
	public void setMmfId(Integer mmfId) {
		this.mmfId = mmfId;
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
		return suiId;
	}
	public void setSuiId(Integer suiId) {
		this.suiId = suiId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "change_date" ) 
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
	@Transient
	public String getMmfIds() {
		return mmfIds;
	}

	public void setMmfIds(String mmfIds) {
		this.mmfIds = mmfIds;
	}
}
