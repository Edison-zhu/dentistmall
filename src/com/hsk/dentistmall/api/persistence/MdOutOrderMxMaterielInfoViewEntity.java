package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;
import java.util.Date;

/**
 * author: yangfeng
 * time: 2019/12/12 8:57
 */
@Entity
@Table(name = "md_out_order_mx_materiel_info_view")
public class MdOutOrderMxMaterielInfoViewEntity {
    private Integer momId;
    private Integer mooId;
    private Integer wmsMiId;
    private Integer mmfId;
    private String matCode;
    private String matName;
    private String norm;
    private String basicUnit;
    private String itemKeyId;
    private Double baseNumber;
    private String inputMode;
    private String lineNo;
    private String soi;
    private String supplier;
    private String extendPropc1;
    private String extendPropc2;
    private String extendPropc3;
    private String extendPropc4;
    private String extendPropc5;
    private String extendPropc6;
    private String extendPropc7;
    private String extendPropc8;
    private String extendPropc9;
    private String extendPropc10;
    private Double number1;
    private Double number2;
    private Double number3;
    private Double number4;
    private Double number5;
    private String remarks;
    private Date editDate;
    private String editRen;
    private Date createDate;
    private String createRen;
    private String pyName;

    @Id
    @Basic
    @Column(name = "mom_id")
    public Integer getMomId() {
        return momId;
    }

    public void setMomId(Integer momId) {
        this.momId = momId;
    }

    @Basic
    @Column(name = "moo_id")
    public Integer getMooId() {
        return mooId;
    }

    public void setMooId(Integer mooId) {
        this.mooId = mooId;
    }

    @Basic
    @Column(name = "wms_mi_id")
    public Integer getWmsMiId() {
        return wmsMiId;
    }

    public void setWmsMiId(Integer wmsMiId) {
        this.wmsMiId = wmsMiId;
    }

    @Basic
    @Column(name = "mmf_id")
    public Integer getMmfId() {
        return mmfId;
    }

    public void setMmfId(Integer mmfId) {
        this.mmfId = mmfId;
    }

    @Basic
    @Column(name = "mat_code")
    public String getMatCode() {
        return matCode;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    @Basic
    @Column(name = "mat_name")
    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    @Basic
    @Column(name = "NORM")
    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    @Basic
    @Column(name = "Basic_unit")
    public String getBasicUnit() {
        return basicUnit;
    }

    public void setBasicUnit(String basicUnit) {
        this.basicUnit = basicUnit;
    }

    @Basic
    @Column(name = "ITEM_KEY_ID")
    public String getItemKeyId() {
        return itemKeyId;
    }

    public void setItemKeyId(String itemKeyId) {
        this.itemKeyId = itemKeyId;
    }

    @Basic
    @Column(name = "base_number")
    public Double getBaseNumber() {
        return baseNumber;
    }

    public void setBaseNumber(Double baseNumber) {
        this.baseNumber = baseNumber;
    }

    @Basic
    @Column(name = "Input_mode")
    public String getInputMode() {
        return inputMode;
    }

    public void setInputMode(String inputMode) {
        this.inputMode = inputMode;
    }

    @Basic
    @Column(name = "LINE_NO")
    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    @Basic
    @Column(name = "SOI")
    public String getSoi() {
        return soi;
    }

    public void setSoi(String soi) {
        this.soi = soi;
    }

    @Basic
    @Column(name = "SUPPLIER")
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Basic
    @Column(name = "EXTEND_PROPC1")
    public String getExtendPropc1() {
        return extendPropc1;
    }

    public void setExtendPropc1(String extendPropc1) {
        this.extendPropc1 = extendPropc1;
    }

    @Basic
    @Column(name = "EXTEND_PROPC2")
    public String getExtendPropc2() {
        return extendPropc2;
    }

    public void setExtendPropc2(String extendPropc2) {
        this.extendPropc2 = extendPropc2;
    }

    @Basic
    @Column(name = "EXTEND_PROPC3")
    public String getExtendPropc3() {
        return extendPropc3;
    }

    public void setExtendPropc3(String extendPropc3) {
        this.extendPropc3 = extendPropc3;
    }

    @Basic
    @Column(name = "EXTEND_PROPC4")
    public String getExtendPropc4() {
        return extendPropc4;
    }

    public void setExtendPropc4(String extendPropc4) {
        this.extendPropc4 = extendPropc4;
    }

    @Basic
    @Column(name = "EXTEND_PROPC5")
    public String getExtendPropc5() {
        return extendPropc5;
    }

    public void setExtendPropc5(String extendPropc5) {
        this.extendPropc5 = extendPropc5;
    }

    @Basic
    @Column(name = "EXTEND_PROPC6")
    public String getExtendPropc6() {
        return extendPropc6;
    }

    public void setExtendPropc6(String extendPropc6) {
        this.extendPropc6 = extendPropc6;
    }

    @Basic
    @Column(name = "EXTEND_PROPC7")
    public String getExtendPropc7() {
        return extendPropc7;
    }

    public void setExtendPropc7(String extendPropc7) {
        this.extendPropc7 = extendPropc7;
    }

    @Basic
    @Column(name = "EXTEND_PROPC8")
    public String getExtendPropc8() {
        return extendPropc8;
    }

    public void setExtendPropc8(String extendPropc8) {
        this.extendPropc8 = extendPropc8;
    }

    @Basic
    @Column(name = "EXTEND_PROPC9")
    public String getExtendPropc9() {
        return extendPropc9;
    }

    public void setExtendPropc9(String extendPropc9) {
        this.extendPropc9 = extendPropc9;
    }

    @Basic
    @Column(name = "EXTEND_PROPC10")
    public String getExtendPropc10() {
        return extendPropc10;
    }

    public void setExtendPropc10(String extendPropc10) {
        this.extendPropc10 = extendPropc10;
    }

    @Basic
    @Column(name = "number1")
    public Double getNumber1() {
        return number1;
    }

    public void setNumber1(Double number1) {
        this.number1 = number1;
    }

    @Basic
    @Column(name = "number2")
    public Double getNumber2() {
        return number2;
    }

    public void setNumber2(Double number2) {
        this.number2 = number2;
    }

    @Basic
    @Column(name = "number3")
    public Double getNumber3() {
        return number3;
    }

    public void setNumber3(Double number3) {
        this.number3 = number3;
    }

    @Basic
    @Column(name = "number4")
    public Double getNumber4() {
        return number4;
    }

    public void setNumber4(Double number4) {
        this.number4 = number4;
    }

    @Basic
    @Column(name = "number5")
    public Double getNumber5() {
        return number5;
    }

    public void setNumber5(Double number5) {
        this.number5 = number5;
    }

    @Basic
    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "edit_date")
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
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
    @Temporal(TemporalType.TIMESTAMP)
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
    @Column(name = "py_name")
    public String getPyName() {
        return pyName;
    }

    public void setPyName(String pyName) {
        this.pyName = pyName;
    }

///===============数据库表无关子段字段属性begin==========
    /**
     *ID(mom_id):字段类型为String
     */
    private String momId_str;
    /**
     *申领单信息(moo_id):字段类型为String
     */
    private String mooId_str;
    /**
     *物料信息表id(wms_mi_id):字段类型为String
     */
    private String wmsMiId_str;
    /**
     *规格ID(mmf_id):字段类型为String
     */
    private String mmfId_str;
    /**
     *修改时间(edit_date):字段类型为Date
     */
    private Date editDate_start;
    /**
     *修改时间(edit_date):字段类型为Date
     */
    private Date editDate_end;
    /**
     *创建时间(create_date):字段类型为Date
     */
    private Date createDate_start;
    /**
     *创建时间(create_date):字段类型为Date
     */
    private Date createDate_end;
    /**
     *():字段类型为String
     *md_out_order_mx表里不用like作为条件的字符串
     */
    private String Tab_like;
    /**
     *():字段类型为String
     *md_out_order_mx表里order by 字符串
     */
    private String Tab_order;
    /**
     *设置momId字段方法
     *@return  返回 <b>MdOutOrderMx.momId</b>的值
     */
    @Transient
    public String getMomId_str(){
        return this.momId_str;
    }
    /**
     *设置 mom_id字段方法
     *@param att_momId 输入<b>MdOutOrderMx.momId</b>字段的值
     */
    public void setMomId_str(String att_momId_str){
        this.momId_str = att_momId_str;
    }
    /**
     *设置mooId字段方法
     *@return  返回 <b>MdOutOrderMx.mooId</b>的值
     */
    @Transient
    public String getMooId_str(){
        return this.mooId_str;
    }
    /**
     *设置 moo_id字段方法
     *@param att_mooId 输入<b>MdOutOrderMx.mooId</b>字段的值
     */
    public void setMooId_str(String att_mooId_str){
        this.mooId_str = att_mooId_str;
    }
    /**
     *设置wmsMiId字段方法
     *@return  返回 <b>MdOutOrderMx.wmsMiId</b>的值
     */
    @Transient
    public String getWmsMiId_str(){
        return this.wmsMiId_str;
    }
    /**
     *设置 wms_mi_id字段方法
     *@param att_wmsMiId 输入<b>MdOutOrderMx.wmsMiId</b>字段的值
     */
    public void setWmsMiId_str(String att_wmsMiId_str){
        this.wmsMiId_str = att_wmsMiId_str;
    }
    /**
     *设置mmfId字段方法
     *@return  返回 <b>MdOutOrderMx.mmfId</b>的值
     */
    @Transient
    public String getMmfId_str(){
        return this.mmfId_str;
    }
    /**
     *设置 mmf_id字段方法
     *@param att_mmfId 输入<b>MdOutOrderMx.mmfId</b>字段的值
     */
    public void setMmfId_str(String att_mmfId_str){
        this.mmfId_str = att_mmfId_str;
    }
    /**
     *设置editDate字段方法
     *@return  返回 <b>MdOutOrderMx.editDate</b>的值
     */
    @Transient
    public Date getEditDate_start(){
        return this.editDate_start;
    }
    /**
     *设置 edit_date字段方法
     *@param att_editDate 输入<b>MdOutOrderMx.editDate</b>字段的值
     */
    public void setEditDate_start(Date att_editDate_start){
        this.editDate_start = att_editDate_start;
    }
    /**
     *设置editDate字段方法
     *@return  返回 <b>MdOutOrderMx.editDate</b>的值
     */
    @Transient
    public Date getEditDate_end(){
        return this.editDate_end;
    }
    /**
     *设置 edit_date字段方法
     *@param att_editDate 输入<b>MdOutOrderMx.editDate</b>字段的值
     */
    public void setEditDate_end(Date att_editDate_end){
        this.editDate_end = att_editDate_end;
    }
    /**
     *设置createDate字段方法
     *@return  返回 <b>MdOutOrderMx.createDate</b>的值
     */
    @Transient
    public Date getCreateDate_start(){
        return this.createDate_start;
    }
    /**
     *设置 create_date字段方法
     *@param att_createDate 输入<b>MdOutOrderMx.createDate</b>字段的值
     */
    public void setCreateDate_start(Date att_createDate_start){
        this.createDate_start = att_createDate_start;
    }
    /**
     *设置createDate字段方法
     *@return  返回 <b>MdOutOrderMx.createDate</b>的值
     */
    @Transient
    public Date getCreateDate_end(){
        return this.createDate_end;
    }
    /**
     *设置 create_date字段方法
     *@param att_createDate 输入<b>MdOutOrderMx.createDate</b>字段的值
     */
    public void setCreateDate_end(Date att_createDate_end){
        this.createDate_end = att_createDate_end;
    }
    /**
     *设置tab_like字段方法
     *@return  返回 <b>MdOutOrderMx.tab_like</b>的值
     */
    @Transient
    public String getTab_like(){
        return this.Tab_like;
    }
    /**
     *设置 字段方法
     *@param att_tab_like 输入<b>MdOutOrderMx.tab_like</b>字段的值
     */
    public void setTab_like(String att_Tab_like){
        this.Tab_like = att_Tab_like;
    }
    /**
     *设置tab_order字段方法
     *@return  返回 <b>MdOutOrderMx.tab_order</b>的值
     */
    @Transient
    public String getTab_order(){
        return this.Tab_order;
    }
    /**
     *设置 字段方法
     *@param att_tab_order 输入<b>MdOutOrderMx.tab_order</b>字段的值
     */
    public void setTab_order(String att_Tab_order){
        this.Tab_order = att_Tab_order;
    }
    ///===============数据库表无关子段字段属性end==========
///===============数据库表自定义无关子段字段属性========
    private Integer wiId;
    private Double quantity;
    @Transient
    public Integer getWiId() {
        return wiId;
    }

    public void setWiId(Integer wiId) {
        this.wiId = wiId;
    }
    @Transient
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
///===============数据库表自定义无关子段字段属性end=====
}
