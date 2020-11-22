package com.hsk.dentistmall.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "md_enter_out_count" )
public class MdEnterOutCount {
	private Integer meocId;
	private Integer wiId;
	private Integer rbaId;
	private Integer rbsId;
	private Integer rbbId;
	private String chCode;
	private String chDm;
	private String matName;
	private String norm;
	private String unit;
	private String oldNum;
	private String oldPrice;
	private String oldMoney;
	private String inNum;
	private String inPrice;
	private String inMoney;
	private String outNum;
	private String outPrice;
	private String outMoney;
	private String num;
	private String price;
	private String money;
	private String countTime;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "meoc_id")
	public Integer getMeocId() {
		return meocId;
	}
	public void setMeocId(Integer meocId) {
		this.meocId = meocId;
	}
	@Column(name = "wi_id")
	public Integer getWiId() {
		return wiId;
	}
	public void setWiId(Integer wiId) {
		this.wiId = wiId;
	}
	@Column(name = "rba_id")
	public Integer getRbaId() {
		return rbaId;
	}
	public void setRbaId(Integer rbaId) {
		this.rbaId = rbaId;
	}
	@Column(name = "rbs_id")
	public Integer getRbsId() {
		return rbsId;
	}
	public void setRbsId(Integer rbsId) {
		this.rbsId = rbsId;
	}
	@Column(name = "rbb_id")
	public Integer getRbbId() {
		return rbbId;
	}
	public void setRbbId(Integer rbbId) {
		this.rbbId = rbbId;
	}
	@Column(name = "ch_code")
	public String getChCode() {
		return chCode;
	}
	public void setChCode(String chCode) {
		this.chCode = chCode;
	}
	@Column(name = "ch_dm")
	public String getChDm() {
		return chDm;
	}
	public void setChDm(String chDm) {
		this.chDm = chDm;
	}
	@Column(name = "mat_name")
	public String getMatName() {
		return matName;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	@Column(name = "norm")
	public String getNorm() {
		return norm;
	}
	public void setNorm(String norm) {
		this.norm = norm;
	}
	@Column(name = "unit")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "old_num")
	public String getOldNum() {
		return oldNum;
	}
	public void setOldNum(String oldNum) {
		this.oldNum = oldNum;
	}
	@Column(name = "old_price")
	public String getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(String oldPrice) {
		this.oldPrice = oldPrice;
	}
	@Column(name = "old_money")
	public String getOldMoney() {
		return oldMoney;
	}
	public void setOldMoney(String oldMoney) {
		this.oldMoney = oldMoney;
	}
	@Column(name = "in_num")
	public String getInNum() {
		return inNum;
	}
	public void setInNum(String inNum) {
		this.inNum = inNum;
	}
	@Column(name = "in_price")
	public String getInPrice() {
		return inPrice;
	}
	public void setInPrice(String inPrice) {
		this.inPrice = inPrice;
	}
	@Column(name = "in_money")
	public String getInMoney() {
		return inMoney;
	}
	public void setInMoney(String inMoney) {
		this.inMoney = inMoney;
	}
	@Column(name = "out_num")
	public String getOutNum() {
		return outNum;
	}
	public void setOutNum(String outNum) {
		this.outNum = outNum;
	}
	@Column(name = "out_price")
	public String getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(String outPrice) {
		this.outPrice = outPrice;
	}
	@Column(name = "out_money")
	public String getOutMoney() {
		return outMoney;
	}
	public void setOutMoney(String outMoney) {
		this.outMoney = outMoney;
	}
	@Column(name = "num")
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	@Column(name = "price")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Column(name = "money")
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	@Column(name = "count_time")
	public String getCountTime() {
		return countTime;
	}
	public void setCountTime(String countTime) {
		this.countTime = countTime;
	}
}
