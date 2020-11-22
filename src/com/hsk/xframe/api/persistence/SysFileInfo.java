package com.hsk.xframe.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SysFileInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_FILE_INFO")
public class SysFileInfo implements Serializable {

	// Fields

	private Integer fileId;
	private String fileCode;
	private String fileName;
	private String filePath;
	private String fileSize;
	private String fileType;
	private String rootPath;
	private String visitRen;
	private Date visitDatetime;
	private String fileState;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;
	private Integer sysFfId;
	private Integer isDelete;
	private String imgWidth;
	private String imgHeight;

	// Constructors

	/** default constructor */
	public SysFileInfo() {
	}

	/** minimal constructor */
	public SysFileInfo(Integer fileId) {
		this.fileId = fileId;
	}

	/** full constructor */
	public SysFileInfo(Integer fileId, String fileCode, String fileName,
			String filePath, String fileSize, String fileType, String rootPath,
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
	public String getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(String fileSize) {
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

	@Column(name = "sys_ff_id")
	public Integer getSysFfId() {
		return sysFfId;
	}

	public void setSysFfId(Integer sysFfId) {
		this.sysFfId = sysFfId;
	}

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
	@Column(name = "is_delete")
    public Integer getIsDelete() {
        return isDelete;
    }

    @Column(name = "img_width")
	public String getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}

	@Column(name = "img_height")
	public String getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}
}