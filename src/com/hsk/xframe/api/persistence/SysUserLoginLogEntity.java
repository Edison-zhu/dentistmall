package com.hsk.xframe.api.persistence;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2019/12/16 15:23
 * 用户登录日志
 */
@Entity
@Table(name = "sys_user_login_log")
public class SysUserLoginLogEntity {
    private Integer suiLgId;
    private Integer suiId;
    private Date loginDate;
    private Integer loginType;
    //增加测试IP
    private String ip;
    @Basic
    @Column(name = "ip")
    public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Id
    @Column(name = "sui_lg_id")
    @GeneratedValue(strategy = IDENTITY)
    public Integer getSuiLgId() {
        return suiLgId;
    }

    public void setSuiLgId(Integer suiLgId) {
        this.suiLgId = suiLgId;
    }

    @Basic
    @Column(name = "sui_id")
    public Integer getSuiId() {
        return suiId;
    }

    public void setSuiId(Integer suiId) {
        this.suiId = suiId;
    }

    @Basic
    @Column(name = "login_date")
    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Basic
    @Column(name = "login_type")
    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUserLoginLogEntity that = (SysUserLoginLogEntity) o;

        if (suiLgId != null ? !suiLgId.equals(that.suiLgId) : that.suiLgId != null) return false;
        if (suiId != null ? !suiId.equals(that.suiId) : that.suiId != null) return false;
        if (loginDate != null ? !loginDate.equals(that.loginDate) : that.loginDate != null) return false;
        if (loginType != null ? !loginType.equals(that.loginType) : that.loginType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = suiLgId != null ? suiLgId.hashCode() : 0;
        result = 31 * result + (suiId != null ? suiId.hashCode() : 0);
        result = 31 * result + (loginDate != null ? loginDate.hashCode() : 0);
        result = 31 * result + (loginType != null ? loginType.hashCode() : 0);
        return result;
    }
}
