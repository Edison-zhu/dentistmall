package com.hsk.xframe.api.persistence;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/8/7 16:21
 */
@Entity
@Table(name = "sys_switch")
public class SysSwitchEntity {
    private Integer switchId;
    private String switchName;
    private Integer state;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "switch_id")
    public Integer getSwitchId() {
        return switchId;
    }

    public void setSwitchId(Integer switchId) {
        this.switchId = switchId;
    }

    @Basic
    @Column(name = "switch_name")
    public String getSwitchName() {
        return switchName;
    }

    public void setSwitchName(String switchName) {
        this.switchName = switchName;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysSwitchEntity that = (SysSwitchEntity) o;
        return switchId == that.switchId &&
                Objects.equals(switchName, that.switchName) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(switchId, switchName, state);
    }
}
