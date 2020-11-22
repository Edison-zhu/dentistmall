package com.hsk.xframe.api.persistence;

 
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.*;

 
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_PARAMETER" )
public class SysParameter implements java.io.Serializable {

	// Fields

	private Integer sparId;
	private Integer sysSparId;
	private String paramCode;
	private String paramName;
	private String paramValue;
	private String paramIcon;
	private Integer relevantId;
	private Integer paramOrderNumber;
	private Integer printNum;
	private String barcode;
	private String barcodeFilecode;
	private Integer ifLoad;
	private String paramNode;
	private String paramRelevantNode;
	private String paramStr10;
	private String paramStr02;
	private String paramStr01;
	private String paramStr03;
	private String paramStr04;
	private String paramStr05;
	private String paramStr06;
	private String paramStr07;
	private String paramStr08;
	private String paramStr09;
	private Date paramInt01;
	private Date paramInt02;
	private Date paramInt03;
	private Date paramInt04;
	private Date paramInt05;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;

	// Constructors

	/** default constructor */
	public SysParameter() {
	}

	/** minimal constructor */
	public SysParameter(Integer sparId) {
		this.sparId = sparId;
	}

	/** full constructor */
	public SysParameter(Integer sparId, Integer sysSparId, String paramCode,
			String paramName, String paramValue, Integer relevantId,
			Integer paramOrderNumber, Integer printNum, String barcode,
			String barcodeFilecode, Integer ifLoad, String paramNode,
			String paramRelevantNode, String paramStr10, String paramStr02,
			String paramStr01, String paramStr03, String paramStr04,
			String paramStr05, String paramStr06, String paramStr07,
			String paramStr08, String paramStr09, Date paramInt01,
			Date paramInt02, Date paramInt03, Date paramInt04,
			Date paramInt05, String createRen, Date createDate,
			String editRen, Date editDate) {
		this.sparId = sparId;
		this.sysSparId = sysSparId;
		this.paramCode = paramCode;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.relevantId = relevantId;
		this.paramOrderNumber = paramOrderNumber;
		this.printNum = printNum;
		this.barcode = barcode;
		this.barcodeFilecode = barcodeFilecode;
		this.ifLoad = ifLoad;
		this.paramNode = paramNode;
		this.paramRelevantNode = paramRelevantNode;
		this.paramStr10 = paramStr10;
		this.paramStr02 = paramStr02;
		this.paramStr01 = paramStr01;
		this.paramStr03 = paramStr03;
		this.paramStr04 = paramStr04;
		this.paramStr05 = paramStr05;
		this.paramStr06 = paramStr06;
		this.paramStr07 = paramStr07;
		this.paramStr08 = paramStr08;
		this.paramStr09 = paramStr09;
		this.paramInt01 = paramInt01;
		this.paramInt02 = paramInt02;
		this.paramInt03 = paramInt03;
		this.paramInt04 = paramInt04;
		this.paramInt05 = paramInt05;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
	}

	// Property accessors
	@Id 
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SPAR_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getSparId() {
		return this.sparId;
	}

	public void setSparId(Integer sparId) {
		this.sparId = sparId;
	}

	@Column(name = "SYS_SPAR_ID", precision = 22, scale = 0)
	public Integer getSysSparId() {
		return this.sysSparId;
	}

	public void setSysSparId(Integer sysSparId) {
		this.sysSparId = sysSparId;
	}

	@Column(name = "PARAM_CODE", length = 32)
	public String getParamCode() {
		return this.paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	@Column(name = "PARAM_NAME", length = 125)
	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Column(name = "PARAM_VALUE", length = 32)
	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	@Column(name = "RELEVANT_ID", precision = 22, scale = 0)
	public Integer getRelevantId() {
		return this.relevantId;
	}

	public void setRelevantId(Integer relevantId) {
		this.relevantId = relevantId;
	}

	@Column(name = "PARAM_ORDER_NUMBER", precision = 22, scale = 0)
	public Integer getParamOrderNumber() {
		return this.paramOrderNumber;
	}

	public void setParamOrderNumber(Integer paramOrderNumber) {
		this.paramOrderNumber = paramOrderNumber;
	}

	@Column(name = "PRINT_NUM", precision = 22, scale = 0)
	public Integer getPrintNum() {
		return this.printNum;
	}

	public void setPrintNum(Integer printNum) {
		this.printNum = printNum;
	}

	@Column(name = "BARCODE", length = 64)
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Column(name = "BARCODE_FILECODE", length = 512)
	public String getBarcodeFilecode() {
		return this.barcodeFilecode;
	}

	public void setBarcodeFilecode(String barcodeFilecode) {
		this.barcodeFilecode = barcodeFilecode;
	}

	@Column(name = "IF_LOAD", precision = 22, scale = 0)
	public Integer getIfLoad() {
		return this.ifLoad;
	}

	public void setIfLoad(Integer ifLoad) {
		this.ifLoad = ifLoad;
	}

	@Column(name = "PARAM_NODE", length = 64)
	public String getParamNode() {
		return this.paramNode;
	}

	public void setParamNode(String paramNode) {
		this.paramNode = paramNode;
	}

	@Column(name = "PARAM_RELEVANT_NODE", length = 64)
	public String getParamRelevantNode() {
		return this.paramRelevantNode;
	}

	public void setParamRelevantNode(String paramRelevantNode) {
		this.paramRelevantNode = paramRelevantNode;
	}

	@Column(name = "PARAM_STR10", length = 512)
	public String getParamStr10() {
		return this.paramStr10;
	}

	public void setParamStr10(String paramStr10) {
		this.paramStr10 = paramStr10;
	}

	@Column(name = "PARAM_STR02", length = 512)
	public String getParamStr02() {
		return this.paramStr02;
	}

	public void setParamStr02(String paramStr02) {
		this.paramStr02 = paramStr02;
	}

	@Column(name = "PARAM_STR01", length = 512)
	public String getParamStr01() {
		return this.paramStr01;
	}

	public void setParamStr01(String paramStr01) {
		this.paramStr01 = paramStr01;
	}

	@Column(name = "PARAM_STR03", length = 512)
	public String getParamStr03() {
		return this.paramStr03;
	}

	public void setParamStr03(String paramStr03) {
		this.paramStr03 = paramStr03;
	}

	@Column(name = "PARAM_STR04", length = 512)
	public String getParamStr04() {
		return this.paramStr04;
	}

	public void setParamStr04(String paramStr04) {
		this.paramStr04 = paramStr04;
	}

	@Column(name = "PARAM_STR05", length = 512)
	public String getParamStr05() {
		return this.paramStr05;
	}

	public void setParamStr05(String paramStr05) {
		this.paramStr05 = paramStr05;
	}

	@Column(name = "PARAM_STR06", length = 512)
	public String getParamStr06() {
		return this.paramStr06;
	}

	public void setParamStr06(String paramStr06) {
		this.paramStr06 = paramStr06;
	}

	@Column(name = "PARAM_STR07", length = 512)
	public String getParamStr07() {
		return this.paramStr07;
	}

	public void setParamStr07(String paramStr07) {
		this.paramStr07 = paramStr07;
	}

	@Column(name = "PARAM_STR08", length = 512)
	public String getParamStr08() {
		return this.paramStr08;
	}

	public void setParamStr08(String paramStr08) {
		this.paramStr08 = paramStr08;
	}

	@Column(name = "PARAM_STR09", length = 512)
	public String getParamStr09() {
		return this.paramStr09;
	}

	public void setParamStr09(String paramStr09) {
		this.paramStr09 = paramStr09;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PARAM_INT01", length = 11)
	public Date getParamInt01() {
		return this.paramInt01;
	}

	public void setParamInt01(Date paramInt01) {
		this.paramInt01 = paramInt01;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PARAM_INT02", length = 11)
	public Date getParamInt02() {
		return this.paramInt02;
	}

	public void setParamInt02(Date paramInt02) {
		this.paramInt02 = paramInt02;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PARAM_INT03", length = 11)
	public Date getParamInt03() {
		return this.paramInt03;
	}

	public void setParamInt03(Date paramInt03) {
		this.paramInt03 = paramInt03;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PARAM_INT04", length = 11)
	public Date getParamInt04() {
		return this.paramInt04;
	}

	public void setParamInt04(Date paramInt04) {
		this.paramInt04 = paramInt04;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PARAM_INT05", length = 11)
	public Date getParamInt05() {
		return this.paramInt05;
	}

	public void setParamInt05(Date paramInt05) {
		this.paramInt05 = paramInt05;
	}

	@Column(name = "CREATE_REN", length = 512)
	public String getCreateRen() {
		return this.createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 11)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "EDIT_REN", length = 512)
	public String getEditRen() {
		return this.editRen;
	}

	public void setEditRen(String editRen) {
		this.editRen = editRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EDIT_DATE", length = 11)
	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	@Column(name = "PARAM_ICON", length = 100)
	public String getParamIcon() {
		return paramIcon;
	}

	public void setParamIcon(String paramIcon) {
		this.paramIcon = paramIcon;
	}
	
}