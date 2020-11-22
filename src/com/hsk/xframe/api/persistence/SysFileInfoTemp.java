package com.hsk.xframe.api.persistence;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * SysFileInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_FILE_INFO_TEMP")
public class SysFileInfoTemp implements java.io.Serializable {

	// Fields

	private Integer fileId;
	private String fileCode;
	private String fileName;
	private String filePath;
	private Double fileSize;
	private String fileType;
	private String rootPath;
	private String visitRen;
	private Date visitDatetime;
	private String fileState;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;

	// Constructors

	/** default constructor */
	public SysFileInfoTemp() {
	}

	/** minimal constructor */
	public SysFileInfoTemp(Integer fileId) {
		this.fileId = fileId;
	}

	/** full constructor */
	public SysFileInfoTemp(Integer fileId, String fileCode, String fileName,
                           String filePath, Double fileSize, String fileType, String rootPath,
                           String visitRen, Date visitDatetime, String fileState,
                           String createRen, Date createDate, String editRen,
                           Date editDate) {
		this.fileId = fileId;
		this.fileCode = fileCode;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.rootPath = rootPath;
		this.visitRen = visitRen;
		this.visitDatetime = visitDatetime;
		this.fileState = fileState;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "FILE_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	@Column(name = "FILE_CODE", length = 50)
	public String getFileCode() {
		return this.fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	@Column(name = "FILE_NAME", length = 215)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_PATH", length = 512)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "FILE_SIZE", precision = 10)
	public Double getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "FILE_TYPE", length = 32)
	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "ROOT_PATH", length = 512)
	public String getRootPath() {
		return this.rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	@Column(name = "VISIT_REN", length = 64)
	public String getVisitRen() {
		return this.visitRen;
	}

	public void setVisitRen(String visitRen) {
		this.visitRen = visitRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VISIT_DATETIME", length = 11)
	public Date getVisitDatetime() {
		return this.visitDatetime;
	}

	public void setVisitDatetime(Date visitDatetime) {
		this.visitDatetime = visitDatetime;
	}

	@Column(name = "FILE_STATE", length = 32)
	public String getFileState() {
		return this.fileState;
	}

	public void setFileState(String fileState) {
		this.fileState = fileState;
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

}