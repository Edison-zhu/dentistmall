package com.hsk.xframe.api.persistence;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/4/21 9:41
 */
@Entity
@Table(name = "sys_feedback")
public class SysFeedbackEntity {
    private Integer fbId;
    private Integer suiId;
    private String fbValue;
    private Date createDate;
    private String createRen;
    private String editRen;
    private Date editDate;

    //2020 05 08新增字段
    //类型 有小程序，后台App反馈过来的类型
    private String type;
    //问题类型，反馈，建议等类型
    private String questionType;
    //状态
    private Integer state;
    //处理日志
    private String processingLog;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fb_id")
    public Integer getFbId() {
        return fbId;
    }

    public void setFbId(Integer fbId) {
        this.fbId = fbId;
    }

    @Column(name = "sui_id")
    public Integer getSuiId() {
        return suiId;
    }

    public void setSuiId(Integer suiId) {
        this.suiId = suiId;
    }

    @Basic
    @Column(name = "fb_value")
    public String getFbValue() {
        return fbValue;
    }

    public void setFbValue(String fbValue) {
        this.fbValue = fbValue;
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
    @Column(name = "create_ren")
    public String getCreateRen() {
        return createRen;
    }

    public void setCreateRen(String createRen) {
        this.createRen = createRen;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name = "question_type")
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "processing_log")
    public String getProcessingLog() {
        return processingLog;
    }

    public void setProcessingLog(String processingLog) {
        this.processingLog = processingLog;
    }
}
