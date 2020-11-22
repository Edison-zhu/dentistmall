package com.hsk.xframe.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysClassInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_CLASS_INFO")
public class SysClassInfo implements java.io.Serializable {

	// Fields

	private Integer sciId;
	private String SClassName;
	private String SClassComment;
	private String SClassCode;
	private String authortext;
	private String versiontext;
	private String packagename;
	private String ftlname;
	private String projectpath;
	private String savepath;
	private String SClassType;
	private String tableName;
	private String tableCode;
	private String tableNode;
	private String sysCode;
	private String dbType;
	private String ftlpath;
	private String proPagename;
	private String seqstr;
	private String SClassName2;
	private String SClassComment2;
	private String SClassCode2;
	private String packagename2;
	private String ftlname2;
	private String savepath2;
	private String SClassName3;
	private String SClassComment3;
	private String packagename3;
	private String pkName;
	private String zipName;
	private String zipFilepath;
	private String zipRootpth;
	private String SClassName4;
	private String SClassComment4;
	private String packagename4;
	private String SClassName5;
	private String SClassComment5;
	private String packagename5;
	private String SClassName6;
	private String SClassComment6;
	private String packagename6;
	private String SClassName7;
	private String SClassComment7;
	private String packagename7;
	private String SClassName8;
	private String SClassComment8;
	private String packagename8;
	private String SClassName9;
	private String SClassComment9;
	private String packagename9;
	private String servicename;
	private String colValue1;
	private String colValue2;
	private String colValue3;
	private String colValue4;
	private String colValue5;
	private String colValue6;

	// Constructors

	/** default constructor */
	public SysClassInfo() {
	}

	/** minimal constructor */
	public SysClassInfo(Integer sciId) {
		this.sciId = sciId;
	}

	/** full constructor */
	public SysClassInfo(Integer sciId, String SClassName, String SClassComment,
			String SClassCode, String authortext, String versiontext,
			String packagename, String ftlname, String projectpath,
			String savepath, String SClassType, String tableName,
			String tableCode, String tableNode, String sysCode, String dbType,
			String ftlpath, String proPagename, String seqstr,
			String SClassName2, String SClassComment2, String SClassCode2,
			String packagename2, String ftlname2, String savepath2,
			String SClassName3, String SClassComment3, String packagename3,
			String pkName, String zipName, String zipFilepath,
			String zipRootpth, String SClassName4, String SClassComment4,
			String packagename4, String SClassName5, String SClassComment5,
			String packagename5, String SClassName6, String SClassComment6,
			String packagename6, String SClassName7, String SClassComment7,
			String packagename7, String SClassName8, String SClassComment8,
			String packagename8, String SClassName9, String SClassComment9,
			String packagename9, String servicename, String colValue1,
			String colValue2, String colValue3, String colValue4,
			String colValue5, String colValue6) {
		this.sciId = sciId;
		this.SClassName = SClassName;
		this.SClassComment = SClassComment;
		this.SClassCode = SClassCode;
		this.authortext = authortext;
		this.versiontext = versiontext;
		this.packagename = packagename;
		this.ftlname = ftlname;
		this.projectpath = projectpath;
		this.savepath = savepath;
		this.SClassType = SClassType;
		this.tableName = tableName;
		this.tableCode = tableCode;
		this.tableNode = tableNode;
		this.sysCode = sysCode;
		this.dbType = dbType;
		this.ftlpath = ftlpath;
		this.proPagename = proPagename;
		this.seqstr = seqstr;
		this.SClassName2 = SClassName2;
		this.SClassComment2 = SClassComment2;
		this.SClassCode2 = SClassCode2;
		this.packagename2 = packagename2;
		this.ftlname2 = ftlname2;
		this.savepath2 = savepath2;
		this.SClassName3 = SClassName3;
		this.SClassComment3 = SClassComment3;
		this.packagename3 = packagename3;
		this.pkName = pkName;
		this.zipName = zipName;
		this.zipFilepath = zipFilepath;
		this.zipRootpth = zipRootpth;
		this.SClassName4 = SClassName4;
		this.SClassComment4 = SClassComment4;
		this.packagename4 = packagename4;
		this.SClassName5 = SClassName5;
		this.SClassComment5 = SClassComment5;
		this.packagename5 = packagename5;
		this.SClassName6 = SClassName6;
		this.SClassComment6 = SClassComment6;
		this.packagename6 = packagename6;
		this.SClassName7 = SClassName7;
		this.SClassComment7 = SClassComment7;
		this.packagename7 = packagename7;
		this.SClassName8 = SClassName8;
		this.SClassComment8 = SClassComment8;
		this.packagename8 = packagename8;
		this.SClassName9 = SClassName9;
		this.SClassComment9 = SClassComment9;
		this.packagename9 = packagename9;
		this.servicename = servicename;
		this.colValue1 = colValue1;
		this.colValue2 = colValue2;
		this.colValue3 = colValue3;
		this.colValue4 = colValue4;
		this.colValue5 = colValue5;
		this.colValue6 = colValue6;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SCI_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getSciId() {
		return this.sciId;
	}

	public void setSciId(Integer sciId) {
		this.sciId = sciId;
	}

	@Column(name = "S_CLASS_NAME", length = 64)
	public String getSClassName() {
		return this.SClassName;
	}

	public void setSClassName(String SClassName) {
		this.SClassName = SClassName;
	}

	@Column(name = "S_CLASS_COMMENT", length = 512)
	public String getSClassComment() {
		return this.SClassComment;
	}

	public void setSClassComment(String SClassComment) {
		this.SClassComment = SClassComment;
	}

	@Column(name = "S_CLASS_CODE", length = 64)
	public String getSClassCode() {
		return this.SClassCode;
	}

	public void setSClassCode(String SClassCode) {
		this.SClassCode = SClassCode;
	}

	@Column(name = "AUTHORTEXT", length = 64)
	public String getAuthortext() {
		return this.authortext;
	}

	public void setAuthortext(String authortext) {
		this.authortext = authortext;
	}

	@Column(name = "VERSIONTEXT", length = 64)
	public String getVersiontext() {
		return this.versiontext;
	}

	public void setVersiontext(String versiontext) {
		this.versiontext = versiontext;
	}

	@Column(name = "PACKAGENAME", length = 128)
	public String getPackagename() {
		return this.packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	@Column(name = "FTLNAME", length = 512)
	public String getFtlname() {
		return this.ftlname;
	}

	public void setFtlname(String ftlname) {
		this.ftlname = ftlname;
	}

	@Column(name = "PROJECTPATH", length = 512)
	public String getProjectpath() {
		return this.projectpath;
	}

	public void setProjectpath(String projectpath) {
		this.projectpath = projectpath;
	}

	@Column(name = "SAVEPATH", length = 512)
	public String getSavepath() {
		return this.savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	@Column(name = "S_CLASS_TYPE", length = 32)
	public String getSClassType() {
		return this.SClassType;
	}

	public void setSClassType(String SClassType) {
		this.SClassType = SClassType;
	}

	@Column(name = "TABLE_NAME", length = 64)
	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "TABLE_CODE", length = 32)
	public String getTableCode() {
		return this.tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	@Column(name = "TABLE_NODE", length = 125)
	public String getTableNode() {
		return this.tableNode;
	}

	public void setTableNode(String tableNode) {
		this.tableNode = tableNode;
	}

	@Column(name = "SYS_CODE", length = 32)
	public String getSysCode() {
		return this.sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	@Column(name = "DB_TYPE", length = 16)
	public String getDbType() {
		return this.dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	@Column(name = "FTLPATH", length = 512)
	public String getFtlpath() {
		return this.ftlpath;
	}

	public void setFtlpath(String ftlpath) {
		this.ftlpath = ftlpath;
	}

	@Column(name = "PRO_PAGENAME", length = 512)
	public String getProPagename() {
		return this.proPagename;
	}

	public void setProPagename(String proPagename) {
		this.proPagename = proPagename;
	}

	@Column(name = "SEQSTR", length = 512)
	public String getSeqstr() {
		return this.seqstr;
	}

	public void setSeqstr(String seqstr) {
		this.seqstr = seqstr;
	}

	@Column(name = "S_CLASS_NAME2", length = 64)
	public String getSClassName2() {
		return this.SClassName2;
	}

	public void setSClassName2(String SClassName2) {
		this.SClassName2 = SClassName2;
	}

	@Column(name = "S_CLASS_COMMENT2", length = 512)
	public String getSClassComment2() {
		return this.SClassComment2;
	}

	public void setSClassComment2(String SClassComment2) {
		this.SClassComment2 = SClassComment2;
	}

	@Column(name = "S_CLASS_CODE2", length = 64)
	public String getSClassCode2() {
		return this.SClassCode2;
	}

	public void setSClassCode2(String SClassCode2) {
		this.SClassCode2 = SClassCode2;
	}

	@Column(name = "PACKAGENAME2", length = 128)
	public String getPackagename2() {
		return this.packagename2;
	}

	public void setPackagename2(String packagename2) {
		this.packagename2 = packagename2;
	}

	@Column(name = "FTLNAME2", length = 512)
	public String getFtlname2() {
		return this.ftlname2;
	}

	public void setFtlname2(String ftlname2) {
		this.ftlname2 = ftlname2;
	}

	@Column(name = "SAVEPATH2", length = 512)
	public String getSavepath2() {
		return this.savepath2;
	}

	public void setSavepath2(String savepath2) {
		this.savepath2 = savepath2;
	}

	@Column(name = "S_CLASS_NAME3", length = 64)
	public String getSClassName3() {
		return this.SClassName3;
	}

	public void setSClassName3(String SClassName3) {
		this.SClassName3 = SClassName3;
	}

	@Column(name = "S_CLASS_COMMENT3", length = 512)
	public String getSClassComment3() {
		return this.SClassComment3;
	}

	public void setSClassComment3(String SClassComment3) {
		this.SClassComment3 = SClassComment3;
	}

	@Column(name = "PACKAGENAME3", length = 128)
	public String getPackagename3() {
		return this.packagename3;
	}

	public void setPackagename3(String packagename3) {
		this.packagename3 = packagename3;
	}

	@Column(name = "PK_NAME", length = 64)
	public String getPkName() {
		return this.pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	@Column(name = "ZIP_NAME", length = 512)
	public String getZipName() {
		return this.zipName;
	}

	public void setZipName(String zipName) {
		this.zipName = zipName;
	}

	@Column(name = "ZIP_FILEPATH", length = 512)
	public String getZipFilepath() {
		return this.zipFilepath;
	}

	public void setZipFilepath(String zipFilepath) {
		this.zipFilepath = zipFilepath;
	}

	@Column(name = "ZIP_ROOTPTH", length = 512)
	public String getZipRootpth() {
		return this.zipRootpth;
	}

	public void setZipRootpth(String zipRootpth) {
		this.zipRootpth = zipRootpth;
	}

	@Column(name = "S_CLASS_NAME4", length = 64)
	public String getSClassName4() {
		return this.SClassName4;
	}

	public void setSClassName4(String SClassName4) {
		this.SClassName4 = SClassName4;
	}

	@Column(name = "S_CLASS_COMMENT4", length = 512)
	public String getSClassComment4() {
		return this.SClassComment4;
	}

	public void setSClassComment4(String SClassComment4) {
		this.SClassComment4 = SClassComment4;
	}

	@Column(name = "PACKAGENAME4", length = 128)
	public String getPackagename4() {
		return this.packagename4;
	}

	public void setPackagename4(String packagename4) {
		this.packagename4 = packagename4;
	}

	@Column(name = "S_CLASS_NAME5", length = 64)
	public String getSClassName5() {
		return this.SClassName5;
	}

	public void setSClassName5(String SClassName5) {
		this.SClassName5 = SClassName5;
	}

	@Column(name = "S_CLASS_COMMENT5", length = 512)
	public String getSClassComment5() {
		return this.SClassComment5;
	}

	public void setSClassComment5(String SClassComment5) {
		this.SClassComment5 = SClassComment5;
	}

	@Column(name = "PACKAGENAME5", length = 128)
	public String getPackagename5() {
		return this.packagename5;
	}

	public void setPackagename5(String packagename5) {
		this.packagename5 = packagename5;
	}

	@Column(name = "S_CLASS_NAME6", length = 64)
	public String getSClassName6() {
		return this.SClassName6;
	}

	public void setSClassName6(String SClassName6) {
		this.SClassName6 = SClassName6;
	}

	@Column(name = "S_CLASS_COMMENT6", length = 512)
	public String getSClassComment6() {
		return this.SClassComment6;
	}

	public void setSClassComment6(String SClassComment6) {
		this.SClassComment6 = SClassComment6;
	}

	@Column(name = "PACKAGENAME6", length = 128)
	public String getPackagename6() {
		return this.packagename6;
	}

	public void setPackagename6(String packagename6) {
		this.packagename6 = packagename6;
	}

	@Column(name = "S_CLASS_NAME7", length = 64)
	public String getSClassName7() {
		return this.SClassName7;
	}

	public void setSClassName7(String SClassName7) {
		this.SClassName7 = SClassName7;
	}

	@Column(name = "S_CLASS_COMMENT7", length = 512)
	public String getSClassComment7() {
		return this.SClassComment7;
	}

	public void setSClassComment7(String SClassComment7) {
		this.SClassComment7 = SClassComment7;
	}

	@Column(name = "PACKAGENAME7", length = 128)
	public String getPackagename7() {
		return this.packagename7;
	}

	public void setPackagename7(String packagename7) {
		this.packagename7 = packagename7;
	}

	@Column(name = "S_CLASS_NAME8", length = 64)
	public String getSClassName8() {
		return this.SClassName8;
	}

	public void setSClassName8(String SClassName8) {
		this.SClassName8 = SClassName8;
	}

	@Column(name = "S_CLASS_COMMENT8", length = 512)
	public String getSClassComment8() {
		return this.SClassComment8;
	}

	public void setSClassComment8(String SClassComment8) {
		this.SClassComment8 = SClassComment8;
	}

	@Column(name = "PACKAGENAME8", length = 128)
	public String getPackagename8() {
		return this.packagename8;
	}

	public void setPackagename8(String packagename8) {
		this.packagename8 = packagename8;
	}

	@Column(name = "S_CLASS_NAME9", length = 64)
	public String getSClassName9() {
		return this.SClassName9;
	}

	public void setSClassName9(String SClassName9) {
		this.SClassName9 = SClassName9;
	}

	@Column(name = "S_CLASS_COMMENT9", length = 512)
	public String getSClassComment9() {
		return this.SClassComment9;
	}

	public void setSClassComment9(String SClassComment9) {
		this.SClassComment9 = SClassComment9;
	}

	@Column(name = "PACKAGENAME9", length = 128)
	public String getPackagename9() {
		return this.packagename9;
	}

	public void setPackagename9(String packagename9) {
		this.packagename9 = packagename9;
	}

	@Column(name = "SERVICENAME", length = 128)
	public String getServicename() {
		return this.servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	@Column(name = "COL_VALUE1", length = 64)
	public String getColValue1() {
		return this.colValue1;
	}

	public void setColValue1(String colValue1) {
		this.colValue1 = colValue1;
	}

	@Column(name = "COL_VALUE2", length = 64)
	public String getColValue2() {
		return this.colValue2;
	}

	public void setColValue2(String colValue2) {
		this.colValue2 = colValue2;
	}

	@Column(name = "COL_VALUE3", length = 64)
	public String getColValue3() {
		return this.colValue3;
	}

	public void setColValue3(String colValue3) {
		this.colValue3 = colValue3;
	}

	@Column(name = "COL_VALUE4", length = 64)
	public String getColValue4() {
		return this.colValue4;
	}

	public void setColValue4(String colValue4) {
		this.colValue4 = colValue4;
	}

	@Column(name = "COL_VALUE5", length = 64)
	public String getColValue5() {
		return this.colValue5;
	}

	public void setColValue5(String colValue5) {
		this.colValue5 = colValue5;
	}

	@Column(name = "COL_VALUE6", length = 64)
	public String getColValue6() {
		return this.colValue6;
	}

	public void setColValue6(String colValue6) {
		this.colValue6 = colValue6;
	}

}