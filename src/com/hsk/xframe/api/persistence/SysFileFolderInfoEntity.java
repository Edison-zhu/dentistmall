package com.hsk.xframe.api.persistence;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/7/29 14:54
 */
@Entity
@Table(name = "sys_file_folder_info")
public class SysFileFolderInfoEntity {
    private Integer fileFolderId;
    private String fileFolderCode;
    private String fileFolderName;
    private String fileFolderPath;
    private Double fileFolderSize;
    private String rootPath;
    private String visitRen;
    private Date visitDatetime;
    private String imgFolderState;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;
    private String fileFolderDescribe;
    private Integer suiId;
    private Integer rbaId;
    private Integer rbsId;
    private Integer rbbId;
    private String searchName;
    private String fileFolderIds;
    private Integer imgCount;
    private String imgPath;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "file_folder_id")
    public Integer getFileFolderId() {
        return fileFolderId;
    }

    public void setFileFolderId(Integer fileFolderId) {
        this.fileFolderId = fileFolderId;
    }

    @Basic
    @Column(name = "file_folder_code")
    public String getFileFolderCode() {
        return fileFolderCode;
    }

    public void setFileFolderCode(String fileFolderCode) {
        this.fileFolderCode = fileFolderCode;
    }

    @Basic
    @Column(name = "file_folder_name")
    public String getFileFolderName() {
        return fileFolderName;
    }

    public void setFileFolderName(String fileFolderName) {
        this.fileFolderName = fileFolderName;
    }

    @Basic
    @Column(name = "file_folder_path")
    public String getFileFolderPath() {
        return fileFolderPath;
    }

    public void setFileFolderPath(String fileFolderPath) {
        this.fileFolderPath = fileFolderPath;
    }

    @Basic
    @Column(name = "file_folder_size")
    public Double getFileFolderSize() {
        return fileFolderSize;
    }

    public void setFileFolderSize(Double fileFolderSize) {
        this.fileFolderSize = fileFolderSize;
    }

    @Basic
    @Column(name = "root_path")
    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Basic
    @Column(name = "visit_ren")
    public String getVisitRen() {
        return visitRen;
    }

    public void setVisitRen(String visitRen) {
        this.visitRen = visitRen;
    }

    @Basic
    @Column(name = "visit_datetime")
    public Date getVisitDatetime() {
        return visitDatetime;
    }

    public void setVisitDatetime(Date visitDatetime) {
        this.visitDatetime = visitDatetime;
    }

    @Basic
    @Column(name = "img_folder_state")
    public String getImgFolderState() {
        return imgFolderState;
    }

    public void setImgFolderState(String imgFolderState) {
        this.imgFolderState = imgFolderState;
    }

    @Basic
    @Column(name = "create_ren")
    public String getCreateRen() {
        return createRen;
    }

    public void setCreateRen(String createRen) {
        this.createRen = createRen;
    }

    @Basic
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "edit_ren")
    public String getEditRen() {
        return editRen;
    }

    public void setEditRen(String editRen) {
        this.editRen = editRen;
    }

    @Basic
    @Column(name = "edit_date")
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    @Basic
    @Column(name = "file_folder_describe")
    public String getFileFolderDescribe() {
        return fileFolderDescribe;
    }

    public void setFileFolderDescribe(String fileFolderDescribe) {
        this.fileFolderDescribe = fileFolderDescribe;
    }

    @Basic
    @Column(name = "sui_id")
    public Integer getSuiId() {
        return suiId;
    }

    public void setSuiId(Integer suiId) {
        this.suiId = suiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysFileFolderInfoEntity that = (SysFileFolderInfoEntity) o;
        return fileFolderId == that.fileFolderId &&
                Objects.equals(fileFolderCode, that.fileFolderCode) &&
                Objects.equals(fileFolderName, that.fileFolderName) &&
                Objects.equals(fileFolderPath, that.fileFolderPath) &&
                Objects.equals(fileFolderSize, that.fileFolderSize) &&
                Objects.equals(rootPath, that.rootPath) &&
                Objects.equals(visitRen, that.visitRen) &&
                Objects.equals(visitDatetime, that.visitDatetime) &&
                Objects.equals(imgFolderState, that.imgFolderState) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editRen, that.editRen) &&
                Objects.equals(editDate, that.editDate) &&
                Objects.equals(fileFolderDescribe, that.fileFolderDescribe) &&
                Objects.equals(suiId, that.suiId);
    }

    public void setRbaId(Integer rbaId) {
        this.rbaId = rbaId;
    }
    @Column(name = "rba_id")
    public Integer getRbaId() {
        return rbaId;
    }

    public void setRbsId(Integer rbsId) {
        this.rbsId = rbsId;
    }
    @Column(name = "rbs_id")
    public Integer getRbsId() {
        return rbsId;
    }

    public void setRbbId(Integer rbbId) {
        this.rbbId = rbbId;
    }
    @Column(name = "rbb_id")
    public Integer getRbbId() {
        return rbbId;
    }

    @Transient
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public void setFileFolderIds(String fileFolderIds) {
        this.fileFolderIds = fileFolderIds;
    }
    @Transient
    public String getFileFolderIds() {
        return fileFolderIds;
    }

    public void setImgCount(Integer imgCount) {
        this.imgCount = imgCount;
    }
    @Transient
    public Integer getImgCount() {
        return imgCount;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    @Transient
    public String getImgPath() {
        return imgPath;
    }
}
