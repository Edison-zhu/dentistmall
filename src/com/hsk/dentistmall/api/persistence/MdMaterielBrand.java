package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/5/13 13:30
 */
@Entity
@Table(name = "md_materiel_brand")
public class MdMaterielBrand {
    private Integer  mbdId;
    private String mbdName;
    private String mbdManufacturer;
    private String state;
    private String createRen;
    private Date createDate;

    private String lessen_filecode;
    private String filecode;
    private String describe;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "mbd_id")
    public Integer getMbdId() {
        return mbdId;
    }

    public void setMbdId(Integer mbdId) {
        this.mbdId = mbdId;
    }
    @Basic
    @Column(name = "mbd_name")
    public String getMbdName() {
        return mbdName;
    }

    public void setMbdName(String mbdName) {
        this.mbdName = mbdName;
    }
    @Basic
    @Column(name = "mbd_manufacturer")
    public String getMbdManufacturer() {
        return mbdManufacturer;
    }

    public void setMbdManufacturer(String mbdManufacturer) {
        this.mbdManufacturer = mbdManufacturer;
    }
    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
//
//    @Override
//    public String toString() {
//        return "MdMaterielBrand{" +
//                "mbdId=" + mbdId +
//                ", mbdName='" + mbdName + '\'' +
//                ", mbdManufacturer='" + mbdManufacturer + '\'' +
//                ", state='" + state + '\'' +
//                ", createRen='" + createRen + '\'' +
//                ", createDate=" + createDate +
//                '}';
//    }
    @Basic
    @Column(name = "lessen_filecode")
    public String getLessen_filecode() {
        return lessen_filecode;
    }

    public void setLessen_filecode(String lessen_filecode) {
        this.lessen_filecode = lessen_filecode;
    }
    @Basic
    @Column(name = "filecode")
    public String getFilecode() {
        return filecode;
    }
    public void setFilecode(String filecode) {
        this.filecode = filecode;
    }
    @Basic
    @Column(name = "describes")
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }

//    public MdMaterielBrand(Integer mbdId, String mbdName, String mbdManufacturer, String state, String createRen, Date createDate, String lessen_filecode, String filecode, String describe) {
//        this.mbdId = mbdId;
//        this.mbdName = mbdName;
//        this.mbdManufacturer = mbdManufacturer;
//        this.state = state;
//        this.createRen = createRen;
//        this.createDate = createDate;
//        this.lessen_filecode = lessen_filecode;
//        this.filecode = filecode;
//        this.describe = describe;
//    }
}
