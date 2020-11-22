package com.hsk.xframe.api.persistence;

import javax.persistence.*;

/**
 * author: yangfeng
 * time: 2019/12/2 16:45
 */
@Entity
@Table(name = "sys_website_hotsearch")
public class SysWebsiteHotsearchEntity {
    private int hotSId;
    private String hotTitle;
    private Integer hotCount;

    @Id
    @Column(name = "hot_s_id")
    public int getHotSId() {
        return hotSId;
    }

    public void setHotSId(int hotSId) {
        this.hotSId = hotSId;
    }

    @Basic
    @Column(name = "hot_title")
    public String getHotTitle() {
        return hotTitle;
    }

    public void setHotTitle(String hotTitle) {
        this.hotTitle = hotTitle;
    }

    @Basic
    @Column(name = "hot_count")
    public Integer getHotCount() {
        return hotCount;
    }

    public void setHotCount(Integer hotCount) {
        this.hotCount = hotCount;
    }
}
