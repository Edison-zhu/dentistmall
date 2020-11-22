package com.hsk.dentistmall.api.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

/**
 * MdCommentMateriel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "md_comment_materiel")
public class MdCommentMaterielView implements java.io.Serializable {
	
	private Integer wmsMiId;
	private Integer wzId;
	private String purchaseType;
	private Integer mdWmsMiId;
	private String applicantName;
	private String phoneNumber;
	private String corporateDomicile;
	private String scopeBusiness;
	private String productName;
	private String brand;
	private String matCode;
	private String matName;
	private Double money1;
	private Double money2;
	private Double money3;
	private Double money4;
	private Double money5;
	private Double number1;
	private Double number2;
	private Double number3;
	private Double number4;
	private Double number5;
	private String basicUnit;
	private String batchRule;
	private Integer validPeriod;
	private Integer alertDay;
	private String norm;
	private String matType;
	private String matType1;
	private String matType2;
	private String matType3;
	private String labelInfo;
	private String lessenFilecode;
	private String describe1;
	private String describe2;
	private String barCode;
	private String barCodeFilecode;
	private String state;
	private String basicUnitAccuracy;
	private String backPrinting;
	private Integer serialNumber;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;
	private Integer mscId;
	private Integer swcId;
	private Integer commId;
	private String mscType;
	private Integer serialComm;
	private String content;
	private String commState;
	private String wzState;
	private String pyName;
	
	private String lessenFilePath;

	// Constructors

	/** default constructor */
	public MdCommentMaterielView() {
	}

	/** minimal constructor */
	public MdCommentMaterielView(Integer mscId) {
		this.mscId = mscId;
	}

	/** full constructor */
	public MdCommentMaterielView(Integer wmsMiId, Integer wzId,
			String purchaseType, Integer mdWmsMiId, String applicantName,
			String phoneNumber, String corporateDomicile, String scopeBusiness,
			String productName, String brand, String matCode, String matName,
			Double money1, Double money2, Double money3, Double money4,
			Double money5, Double number1, Double number2, Double number3,
			Double number4, Double number5, String basicUnit, String batchRule,
			Integer validPeriod, Integer alertDay, String norm, String matType,
			String matType1, String matType2, String matType3,
			String labelInfo, String lessenFilecode, String describe1,
			String describe2, String barCode, String barCodeFilecode,
			String state, String basicUnitAccuracy, String backPrinting,
			Integer serialNumber, String createRen, Date createDate,
			String editRen, Date editDate, Integer mscId, Integer swcId,
			Integer commId, String mscType, Integer serialComm, String content,
			String commState) {
		super();
		this.wmsMiId = wmsMiId;
		this.wzId = wzId;
		this.purchaseType = purchaseType;
		this.mdWmsMiId = mdWmsMiId;
		this.applicantName = applicantName;
		this.phoneNumber = phoneNumber;
		this.corporateDomicile = corporateDomicile;
		this.scopeBusiness = scopeBusiness;
		this.productName = productName;
		this.brand = brand;
		this.matCode = matCode;
		this.matName = matName;
		this.money1 = money1;
		this.money2 = money2;
		this.money3 = money3;
		this.money4 = money4;
		this.money5 = money5;
		this.number1 = number1;
		this.number2 = number2;
		this.number3 = number3;
		this.number4 = number4;
		this.number5 = number5;
		this.basicUnit = basicUnit;
		this.batchRule = batchRule;
		this.validPeriod = validPeriod;
		this.alertDay = alertDay;
		this.norm = norm;
		this.matType = matType;
		this.matType1 = matType1;
		this.matType2 = matType2;
		this.matType3 = matType3;
		this.labelInfo = labelInfo;
		this.lessenFilecode = lessenFilecode;
		this.describe1 = describe1;
		this.describe2 = describe2;
		this.barCode = barCode;
		this.barCodeFilecode = barCodeFilecode;
		this.state = state;
		this.basicUnitAccuracy = basicUnitAccuracy;
		this.backPrinting = backPrinting;
		this.serialNumber = serialNumber;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
		this.mscId = mscId;
		this.swcId = swcId;
		this.commId = commId;
		this.mscType = mscType;
		this.serialComm = serialComm;
		this.content = content;
		this.commState = commState;
	}
	// Property accessors
	@Id
	@Column(name = "msc_id", nullable = false)
	public Integer getMscId() {
		return this.mscId;
	}

	public void setMscId(Integer mscId) {
		this.mscId = mscId;
	}
	@Column(name = "wms_mi_id")
	public Integer getWmsMiId() {
		return this.wmsMiId;
	}

	public void setWmsMiId(Integer wmsMiId) {
		this.wmsMiId = wmsMiId;
	}

	@Column(name = "wz_id")
	public Integer getWzId() {
		return this.wzId;
	}

	public void setWzId(Integer wzId) {
		this.wzId = wzId;
	}

	@Column(name = "purchase_type", length = 32)
	public String getPurchaseType() {
		return this.purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	@Column(name = "md_wms_mi_id")
	public Integer getMdWmsMiId() {
		return this.mdWmsMiId;
	}

	public void setMdWmsMiId(Integer mdWmsMiId) {
		this.mdWmsMiId = mdWmsMiId;
	}

	@Column(name = "applicant_Name", length = 64)
	public String getApplicantName() {
		return this.applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	@Column(name = "Phone_number", length = 32)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "Corporate_domicile", length = 64)
	public String getCorporateDomicile() {
		return this.corporateDomicile;
	}

	public void setCorporateDomicile(String corporateDomicile) {
		this.corporateDomicile = corporateDomicile;
	}

	@Column(name = "scope_business", length = 400)
	public String getScopeBusiness() {
		return this.scopeBusiness;
	}

	public void setScopeBusiness(String scopeBusiness) {
		this.scopeBusiness = scopeBusiness;
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

	@Column(name = "mat_code", length = 32)
	public String getMatCode() {
		return this.matCode;
	}

	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}

	@Column(name = "mat_name", length = 512)
	public String getMatName() {
		return this.matName;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}

	@Column(name = "money1", precision = 10, scale = 4)
	public Double getMoney1() {
		return this.money1;
	}

	public void setMoney1(Double money1) {
		this.money1 = money1;
	}

	@Column(name = "money2", precision = 10, scale = 4)
	public Double getMoney2() {
		return this.money2;
	}

	public void setMoney2(Double money2) {
		this.money2 = money2;
	}

	@Column(name = "money3", precision = 10, scale = 4)
	public Double getMoney3() {
		return this.money3;
	}

	public void setMoney3(Double money3) {
		this.money3 = money3;
	}

	@Column(name = "money4", precision = 10, scale = 4)
	public Double getMoney4() {
		return this.money4;
	}

	public void setMoney4(Double money4) {
		this.money4 = money4;
	}

	@Column(name = "money5", precision = 10, scale = 4)
	public Double getMoney5() {
		return this.money5;
	}

	public void setMoney5(Double money5) {
		this.money5 = money5;
	}

	@Column(name = "number1", precision = 10, scale = 4)
	public Double getNumber1() {
		return this.number1;
	}

	public void setNumber1(Double number1) {
		this.number1 = number1;
	}

	@Column(name = "number2", precision = 10, scale = 4)
	public Double getNumber2() {
		return this.number2;
	}

	public void setNumber2(Double number2) {
		this.number2 = number2;
	}

	@Column(name = "number3", precision = 10, scale = 4)
	public Double getNumber3() {
		return this.number3;
	}

	public void setNumber3(Double number3) {
		this.number3 = number3;
	}

	@Column(name = "number4", precision = 10, scale = 4)
	public Double getNumber4() {
		return this.number4;
	}

	public void setNumber4(Double number4) {
		this.number4 = number4;
	}

	@Column(name = "number5", precision = 10, scale = 4)
	public Double getNumber5() {
		return this.number5;
	}

	public void setNumber5(Double number5) {
		this.number5 = number5;
	}

	@Column(name = "Basic_unit", length = 64)
	public String getBasicUnit() {
		return this.basicUnit;
	}

	public void setBasicUnit(String basicUnit) {
		this.basicUnit = basicUnit;
	}

	@Column(name = "Batch_rule", length = 32)
	public String getBatchRule() {
		return this.batchRule;
	}

	public void setBatchRule(String batchRule) {
		this.batchRule = batchRule;
	}

	@Column(name = "VALID_PERIOD")
	public Integer getValidPeriod() {
		return this.validPeriod;
	}

	public void setValidPeriod(Integer validPeriod) {
		this.validPeriod = validPeriod;
	}

	@Column(name = "ALERT_DAY")
	public Integer getAlertDay() {
		return this.alertDay;
	}

	public void setAlertDay(Integer alertDay) {
		this.alertDay = alertDay;
	}

	@Column(name = "NORM", length = 60)
	public String getNorm() {
		return this.norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
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

	@Column(name = "Label_info", length = 64)
	public String getLabelInfo() {
		return this.labelInfo;
	}

	public void setLabelInfo(String labelInfo) {
		this.labelInfo = labelInfo;
	}

	@Column(name = "lessen_filecode", length = 512)
	public String getLessenFilecode() {
		return this.lessenFilecode;
	}

	public void setLessenFilecode(String lessenFilecode) {
		this.lessenFilecode = lessenFilecode;
	}

	@Column(name = "describe1", length = 512)
	public String getDescribe1() {
		return this.describe1;
	}

	public void setDescribe1(String describe1) {
		this.describe1 = describe1;
	}

	@Column(name = "describe2", length = 512)
	public String getDescribe2() {
		return this.describe2;
	}

	public void setDescribe2(String describe2) {
		this.describe2 = describe2;
	}

	@Column(name = "barCode", length = 64)
	public String getBarCode() {
		return this.barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	@Column(name = "barCode_filecode", length = 512)
	public String getBarCodeFilecode() {
		return this.barCodeFilecode;
	}

	public void setBarCodeFilecode(String barCodeFilecode) {
		this.barCodeFilecode = barCodeFilecode;
	}

	@Column(name = "state", length = 32)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "Basic_unit_accuracy", length = 16)
	public String getBasicUnitAccuracy() {
		return this.basicUnitAccuracy;
	}

	public void setBasicUnitAccuracy(String basicUnitAccuracy) {
		this.basicUnitAccuracy = basicUnitAccuracy;
	}

	@Column(name = "back_Printing", length = 128)
	public String getBackPrinting() {
		return this.backPrinting;
	}

	public void setBackPrinting(String backPrinting) {
		this.backPrinting = backPrinting;
	}

	@Column(name = "Serial_number")
	public Integer getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
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

	@Column(name = "swc_id")
	public Integer getSwcId() {
		return this.swcId;
	}

	public void setSwcId(Integer swcId) {
		this.swcId = swcId;
	}

	@Column(name = "comm_id")
	public Integer getCommId() {
		return this.commId;
	}

	public void setCommId(Integer commId) {
		this.commId = commId;
	}

	@Column(name = "msc_type", length = 10)
	public String getMscType() {
		return this.mscType;
	}

	public void setMscType(String mscType) {
		this.mscType = mscType;
	}

	@Column(name = "serial_comm")
	public Integer getSerialComm() {
		return this.serialComm;
	}

	public void setSerialComm(Integer serialComm) {
		this.serialComm = serialComm;
	}

	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "comm_state", length = 32)
	public String getCommState() {
		return this.commState;
	}

	public void setCommState(String commState) {
		this.commState = commState;
	}
	
	@Column(name = "wz_state", length = 32)
	public String getWzState() {
		return wzState;
	}

	public void setWzState(String wzState) {
		this.wzState = wzState;
	}
	@Column(name = "py_name", length = 100)
	public String getPyName() {
		return pyName;
	}

	public void setPyName(String pyName) {
		this.pyName = pyName;
	}

	@Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code= lessen_filecode)")
	public String getLessenFilePath() {
		return lessenFilePath;
	}

	public void setLessenFilePath(String lessenFilePath) {
		this.lessenFilePath = lessenFilePath;
	}
	

}