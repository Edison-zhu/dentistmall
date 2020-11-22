package com.hsk.xframe.api.persistence;

 
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SysSystemInfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_SYSTEM_INFO" )
public class SysSystemInfo implements java.io.Serializable {

	// Fields

	private Integer ssiId;
	private Integer swsId;
	private Integer sysSsiId;
	private String sysName;
	private String sysCode;
	private Date createTime;
	private String createRen;
	private String systemIp;
	private Integer systemPort;
	private String systemService;
	private String charset;
	private Integer orderNumber;
	private String systemUrl;
	private String systemComment;
	private Integer systemtype;
	private String sysIotype;
	private String projectName;
	private String projectRootPath;
	private String savePath;
	
	private String dbuserName;
	private String dbName;
	private String dbType;
	// Constructors

	/** default constructor */
	public SysSystemInfo() {
	}

	/** minimal constructor */
	public SysSystemInfo(Integer ssiId) {
		this.ssiId = ssiId;
	}

	/** full constructor */
	public SysSystemInfo(Integer ssiId, Integer swsId, Integer sysSsiId,
			String sysName, String sysCode, Date createTime, String createRen,
			String systemIp, Integer systemPort, String systemService,
			String charset, Integer orderNumber, String systemUrl,
			String systemComment, Integer systemtype, String sysIotype,
			String projectName, String projectRootPath, String savePath) {
		this.ssiId = ssiId;
		this.swsId = swsId;
		this.sysSsiId = sysSsiId;
		this.sysName = sysName;
		this.sysCode = sysCode;
		this.createTime = createTime;
		this.createRen = createRen;
		this.systemIp = systemIp;
		this.systemPort = systemPort;
		this.systemService = systemService;
		this.charset = charset;
		this.orderNumber = orderNumber;
		this.systemUrl = systemUrl;
		this.systemComment = systemComment;
		this.systemtype = systemtype;
		this.sysIotype = sysIotype;
		this.projectName = projectName;
		this.projectRootPath = projectRootPath;
		this.savePath = savePath;
	}

	// Property accessors
 	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SSI_ID", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getSsiId() {
		return this.ssiId;
	}

	public void setSsiId(Integer ssiId) {
		this.ssiId = ssiId;
	}
	
	
	@Column(name = "DBUSER_NAME", length = 128)
	public String getDbuserName() {
		return this.dbuserName;
	}

	public void setDbuserName(String dbuserName) {
		this.dbuserName = dbuserName;
	}

	@Column(name = "DB_NAME", length = 64)
	public String getDbName() {
		return this.dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	@Column(name = "SWS_ID", precision = 22, scale = 0)
	public Integer getSwsId() {
		return this.swsId;
	}

	public void setSwsId(Integer swsId) {
		this.swsId = swsId;
	}

	@Column(name = "SYS_SSI_ID", precision = 22, scale = 0)
	public Integer getSysSsiId() {
		return this.sysSsiId;
	}

	public void setSysSsiId(Integer sysSsiId) {
		this.sysSsiId = sysSsiId;
	}

	@Column(name = "SYS_NAME", length = 128)
	public String getSysName() {
		return this.sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	@Column(name = "SYS_CODE", length = 32)
	public String getSysCode() {
		return this.sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_REN", length = 32)
	public String getCreateRen() {
		return this.createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}

	@Column(name = "SYSTEM_IP", length = 64)
	public String getSystemIp() {
		return this.systemIp;
	}

	public void setSystemIp(String systemIp) {
		this.systemIp = systemIp;
	}

	@Column(name = "SYSTEM_PORT", precision = 22, scale = 0)
	public Integer getSystemPort() {
		return this.systemPort;
	}

	public void setSystemPort(Integer systemPort) {
		this.systemPort = systemPort;
	}

	@Column(name = "SYSTEM_SERVICE", length = 128)
	public String getSystemService() {
		return this.systemService;
	}

	public void setSystemService(String systemService) {
		this.systemService = systemService;
	}

	@Column(name = "CHARSET", length = 64)
	public String getCharset() {
		return this.charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Column(name = "ORDER_NUMBER", precision = 22, scale = 0)
	public Integer getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name = "SYSTEM_URL", length = 215)
	public String getSystemUrl() {
		return this.systemUrl;
	}

	public void setSystemUrl(String systemUrl) {
		this.systemUrl = systemUrl;
	}

	@Column(name = "SYSTEM_COMMENT", length = 512)
	public String getSystemComment() {
		return this.systemComment;
	}

	public void setSystemComment(String systemComment) {
		this.systemComment = systemComment;
	}

	@Column(name = "SYSTEMTYPE", precision = 22, scale = 0)
	public Integer getSystemtype() {
		return this.systemtype;
	}

	public void setSystemtype(Integer systemtype) {
		this.systemtype = systemtype;
	}

	@Column(name = "SYS_IOTYPE", length = 16)
	public String getSysIotype() {
		return this.sysIotype;
	}

	public void setSysIotype(String sysIotype) {
		this.sysIotype = sysIotype;
	}

	@Column(name = "PROJECT_NAME", length = 100)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "PROJECT_ROOT_PATH", length = 512)
	public String getProjectRootPath() {
		return this.projectRootPath;
	}

	public void setProjectRootPath(String projectRootPath) {
		this.projectRootPath = projectRootPath;
	}

	@Column(name = "SAVE_PATH", length = 512)
	public String getSavePath() {
		return this.savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	@Column(name = "db_type")
	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	
	
}