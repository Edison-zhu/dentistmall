package com.hsk.dentistmall.api.persistence;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * author: yangfeng
 * time: 2020/8/3 16:12
 */
@Entity
@Table(name = "md_model_type_link_info")
public class MdModelTypeLinkInfoEntity {
    private Integer mdMtId;
    private Integer firstId;
    private Integer secondId;
    private Integer thirdId;
    private Integer suiId;
    private String createRen;
    private Date createDate;
    private String editRen;
    private Date editDate;
    private Integer rbaId;
    private Integer rbsId;
    private Integer rbbId;

    private String firstName;
    private String secondName;
    private String thirdName;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "md_mt_id")
    public Integer getMdMtId() {
        return mdMtId;
    }

    public void setMdMtId(Integer mdMtId) {
        this.mdMtId = mdMtId;
    }

    @Basic
    @Column(name = "first_id")
    public Integer getFirstId() {
        return firstId;
    }

    public void setFirstId(Integer firstId) {
        this.firstId = firstId;
    }

    @Basic
    @Column(name = "second_id")
    public Integer getSecondId() {
        return secondId;
    }

    public void setSecondId(Integer secondId) {
        this.secondId = secondId;
    }

    @Basic
    @Column(name = "third_id")
    public Integer getThirdId() {
        return thirdId;
    }

    public void setThirdId(Integer thirdId) {
        this.thirdId = thirdId;
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
    @Column(name = "rba_id")
    public Integer getRbaId() {
        return rbaId;
    }

    public void setRbaId(Integer rbaId) {
        this.rbaId = rbaId;
    }

    @Basic
    @Column(name = "rbs_id")
    public Integer getRbsId() {
        return rbsId;
    }

    public void setRbsId(Integer rbsId) {
        this.rbsId = rbsId;
    }

    @Basic
    @Column(name = "rbb_id")
    public Integer getRbbId() {
        return rbbId;
    }

    public void setRbbId(Integer rbbId) {
        this.rbbId = rbbId;
    }

    @Formula("(select a.mmt_name from md_materiel_type a where a.mmt_id = first_id)")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Formula("(select a.mmt_name from md_materiel_type a where a.mmt_id = second_id)")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Formula("(select a.mmt_name from md_materiel_type a where a.mmt_id = third_id)")
    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MdModelTypeLinkInfoEntity that = (MdModelTypeLinkInfoEntity) o;
        return mdMtId == that.mdMtId &&
                Objects.equals(firstId, that.firstId) &&
                Objects.equals(secondId, that.secondId) &&
                Objects.equals(thirdId, that.thirdId) &&
                Objects.equals(suiId, that.suiId) &&
                Objects.equals(createRen, that.createRen) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(editRen, that.editRen) &&
                Objects.equals(editDate, that.editDate) &&
                Objects.equals(rbaId, that.rbaId) &&
                Objects.equals(rbsId, that.rbsId) &&
                Objects.equals(rbbId, that.rbbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mdMtId, firstId, secondId, thirdId, suiId, createRen, createDate, editRen, editDate, rbaId, rbsId, rbbId);
    }
}
