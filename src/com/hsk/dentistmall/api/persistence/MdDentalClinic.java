package com.hsk.dentistmall.api.persistence;

import javax.persistence.*;

import org.hibernate.annotations.Formula;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

/**
 * 根据md_dental_clinic表结构自动生成持久化对象<B> com.hsk.dentistmall.api.persistence.MdDentalClinic</B>
 * <hr/>
 * ===============数据库表字段属性==========
 *  <table>
 * <tr><th>字段名称:</th><td>牙医门诊id(rbbId)</td><th>字段类型:</th><td>Integer(主键)</td></tr>
 * <tr><th>字段名称:</th><td>牙医医院id(rbsId)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>牙医门诊名称(rbbName)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>牙医门诊编号(rbbCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>代理地址(address)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>省(province)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>市(city)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>地区(area)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>联系人(connUser)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>联系人电话(connPhone)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>联系人法人身份证(connCertificateNo)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>创建人(createRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>创建时间(createDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>修改人(editRen)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>修改时间(editDate)</td><th>字段类型:</th><td>Date(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>流程状态(flowState)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>状态:1正常，2删除(state)</td><th>字段类型:</th><td>Integer(可以为空)</td></tr>
 * <tr><th>字段名称:</th><td>银行账号(bankCode)</td><th>字段类型:</th><td>String(可以为空)</td></tr>
 * </table>
 * ===============无关字段属性===============
 *  <table>
 * 	<tr><th>字段名称:</th><td>牙医门诊id(rbbId)	</td><th>属性名称:</th><td>rbbId</td></tr>
 * 	<tr><th>字段名称:</th><td>牙医医院id(rbsId)	</td><th>属性名称:</th><td>rbsId</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>创建时间(createDate)	</td><th>属性名称:</th><td>createDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>修改时间(editDate)	</td><th>属性名称:</th><td>editDate</td></tr>
 * 	<tr><th>字段名称:</th><td>流程状态(flowState)	</td><th>属性名称:</th><td>flowState</td></tr>
 * 	<tr><th>字段名称:</th><td>状态:1正常，2删除(state)	</td><th>属性名称:</th><td>state</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_like)	</td><th>属性名称:</th><td>tab_like</td></tr>
 * 	<tr><th>字段名称:</th><td>(tab_order)	</td><th>属性名称:</th><td>tab_order</td></tr>
 * </table>
 * <hr/>
 *
 * @author 作者:admin
 * @version v1.0 创建时间: 2017-09-25 14:21:16
 */


@SuppressWarnings("serial")
@Entity
@Table(name = "md_dental_clinic")
public class MdDentalClinic {
///===============数据库表字段属性begin==========
    /**
     * 牙医门诊id(rbbId):字段类型为Integer
     */
    private Integer rbbId;

    /**
     * 牙医医院id(rbsId):字段类型为Integer
     */
    private Integer rbsId;

    /**
     * 牙医门诊名称(rbbName):字段类型为String
     */
    private String rbbName;

    /**
     * 牙医门诊编号(rbbCode):字段类型为String
     */
    private String rbbCode;

    /**
     * 代理地址(address):字段类型为String
     */
    private String address;

    /**
     * 省(province):字段类型为String
     */
    private String province;

    /**
     * 市(city):字段类型为String
     */
    private String city;

    /**
     * 地区(area):字段类型为String
     */
    private String area;

    /**
     * 联系人(connUser):字段类型为String
     */
    private String connUser;

    /**
     * 联系人电话(connPhone):字段类型为String
     */
    private String connPhone;

    /**
     * 联系人法人身份证(connCertificateNo):字段类型为String
     */
    private String connCertificateNo;

    /**
     * 创建人(createRen):字段类型为String
     */
    private String createRen;

    /**
     * 创建时间(createDate):字段类型为Date
     */
    private Date createDate;

    /**
     * 修改人(editRen):字段类型为String
     */
    private String editRen;

    /**
     * 修改时间(editDate):字段类型为Date
     */
    private Date editDate;

    /**
     * 流程状态(flowState):字段类型为Integer
     */
    private Integer flowState;

    /**
     * 状态:1正常，2删除(state):字段类型为Integer
     */
    private Integer state;

    /**
     * 银行账号(bankCode):字段类型为String
     */
    private String bankCode;

    /**
     * 所属医院名称
     */
    private Integer rbaId;
    private String rbsName;

    private String rbaName;

    /**
     * 备注(remark):字段类型为String
     */
    private String remark;

    private String salesmanIds;

    private String salesName;
    private String salesPhone;
    private Integer verifyState;
    private String verifyRemark;
    private String logo;

    @Formula("(SELECT a.rbs_name FROM md_dentist_hospital a  WHERE a.rbs_id=rbs_id  )")
    public String getRbsName() {
        return rbsName;
    }

    public void setRbsName(String rbsName) {
        this.rbsName = rbsName;
    }

    @Formula("(SELECT a.rba_name FROM md_company_group a  WHERE a.rba_id=rba_id  )")
    public String getRbaName() {
        return rbaName;
    }

    public void setRbaName(String rbaName) {
        this.rbaName = rbaName;
    }

    /**
     * 设置牙医门诊id(rbb_id)字段方法 (该字段是主键)
     *
     * @return 返回 <b>MdDentalClinic.RbbId</b>的值
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rbb_id")
    public Integer getRbbId() {
        return this.rbbId;
    }

    /**
     * 设置 rbb_id字段方法
     *
     * @param att_rbbId 输入<b>MdDentalClinic.rbbId</b>字段的值
     */
    public void setRbbId(Integer att_rbbId) {
        this.rbbId = att_rbbId;
    }

    /**
     * 设置牙医医院id(rbs_id)字段方法
     *
     * @return 返回 <b>MdDentalClinic.RbsId</b>的值
     */
    @Column(name = "rbs_id")
    public Integer getRbsId() {
        return this.rbsId;
    }

    /**
     * 设置 rbs_id字段方法
     *
     * @param att_rbsId 输入<b>MdDentalClinic.rbsId</b>字段的值
     */
    public void setRbsId(Integer att_rbsId) {
        this.rbsId = att_rbsId;
    }


    @Column(name = "rba_id")
    public Integer getRbaId() {
        return rbaId;
    }

    public void setRbaId(Integer rbaId) {
        this.rbaId = rbaId;
    }

    /**
     * 设置牙医门诊名称(rbb_name)字段方法
     *
     * @return 返回 <b>MdDentalClinic.RbbName</b>的值
     */
    @Column(name = "rbb_name")
    public String getRbbName() {
        return this.rbbName;
    }

    /**
     * 设置 rbb_name字段方法
     *
     * @param att_rbbName 输入<b>MdDentalClinic.rbbName</b>字段的值
     */
    public void setRbbName(String att_rbbName) {
        this.rbbName = att_rbbName;
    }

    /**
     * 设置牙医门诊编号(rbb_code)字段方法
     *
     * @return 返回 <b>MdDentalClinic.RbbCode</b>的值
     */
    @Column(name = "rbb_code")
    public String getRbbCode() {
        return this.rbbCode;
    }

    /**
     * 设置 rbb_code字段方法
     *
     * @param att_rbbCode 输入<b>MdDentalClinic.rbbCode</b>字段的值
     */
    public void setRbbCode(String att_rbbCode) {
        this.rbbCode = att_rbbCode;
    }

    /**
     * 设置备注(remark)字段方法
     *
     * @return 返回 <b>MdCompanyGroup.Remark</b>的值
     */
    @Column(name = "remark")
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置 remark字段方法
     *
     * @param att_remark 输入<b>MdCompanyGroup.remark</b>字段的值
     */
    public void setRemark(String att_remark) {
        this.remark = att_remark;
    }

    /**
     * 设置代理地址(address)字段方法
     *
     * @return 返回 <b>MdDentalClinic.Address</b>的值
     */
    @Column(name = "address")
    public String getAddress() {
        return this.address;
    }

    /**
     * 设置 address字段方法
     *
     * @param att_address 输入<b>MdDentalClinic.address</b>字段的值
     */
    public void setAddress(String att_address) {
        this.address = att_address;
    }

    /**
     * 设置省(province)字段方法
     *
     * @return 返回 <b>MdDentalClinic.Province</b>的值
     */
    @Column(name = "province")
    public String getProvince() {
        return this.province;
    }

    /**
     * 设置 province字段方法
     *
     * @param att_province 输入<b>MdDentalClinic.province</b>字段的值
     */
    public void setProvince(String att_province) {
        this.province = att_province;
    }

    /**
     * 设置市(city)字段方法
     *
     * @return 返回 <b>MdDentalClinic.City</b>的值
     */
    @Column(name = "city")
    public String getCity() {
        return this.city;
    }

    /**
     * 设置 city字段方法
     *
     * @param att_city 输入<b>MdDentalClinic.city</b>字段的值
     */
    public void setCity(String att_city) {
        this.city = att_city;
    }

    /**
     * 设置地区(area)字段方法
     *
     * @return 返回 <b>MdDentalClinic.Area</b>的值
     */
    @Column(name = "area")
    public String getArea() {
        return this.area;
    }

    /**
     * 设置 area字段方法
     *
     * @param att_area 输入<b>MdDentalClinic.area</b>字段的值
     */
    public void setArea(String att_area) {
        this.area = att_area;
    }

    /**
     * 设置联系人(conn_user)字段方法
     *
     * @return 返回 <b>MdDentalClinic.ConnUser</b>的值
     */
    @Column(name = "conn_user")
    public String getConnUser() {
        return this.connUser;
    }

    /**
     * 设置 conn_user字段方法
     *
     * @param att_connUser 输入<b>MdDentalClinic.connUser</b>字段的值
     */
    public void setConnUser(String att_connUser) {
        this.connUser = att_connUser;
    }

    /**
     * 设置联系人电话(conn_phone)字段方法
     *
     * @return 返回 <b>MdDentalClinic.ConnPhone</b>的值
     */
    @Column(name = "conn_phone")
    public String getConnPhone() {
        return this.connPhone;
    }

    /**
     * 设置 conn_phone字段方法
     *
     * @param att_connPhone 输入<b>MdDentalClinic.connPhone</b>字段的值
     */
    public void setConnPhone(String att_connPhone) {
        this.connPhone = att_connPhone;
    }

    /**
     * 设置联系人法人身份证(conn_certificate_no)字段方法
     *
     * @return 返回 <b>MdDentalClinic.ConnCertificateNo</b>的值
     */
    @Column(name = "conn_certificate_no")
    public String getConnCertificateNo() {
        return this.connCertificateNo;
    }

    /**
     * 设置 conn_certificate_no字段方法
     *
     * @param att_connCertificateNo 输入<b>MdDentalClinic.connCertificateNo</b>字段的值
     */
    public void setConnCertificateNo(String att_connCertificateNo) {
        this.connCertificateNo = att_connCertificateNo;
    }

    /**
     * 设置创建人(create_ren)字段方法
     *
     * @return 返回 <b>MdDentalClinic.CreateRen</b>的值
     */
    @Column(name = "create_ren")
    public String getCreateRen() {
        return this.createRen;
    }

    /**
     * 设置 create_ren字段方法
     *
     * @param att_createRen 输入<b>MdDentalClinic.createRen</b>字段的值
     */
    public void setCreateRen(String att_createRen) {
        this.createRen = att_createRen;
    }

    /**
     * 设置创建时间(create_date)字段方法
     *
     * @return 返回 <b>MdDentalClinic.CreateDate</b>的值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置 create_date字段方法
     *
     * @param att_createDate 输入<b>MdDentalClinic.createDate</b>字段的值
     */
    public void setCreateDate(Date att_createDate) {
        this.createDate = att_createDate;
    }

    /**
     * 设置修改人(edit_ren)字段方法
     *
     * @return 返回 <b>MdDentalClinic.EditRen</b>的值
     */
    @Column(name = "edit_ren")
    public String getEditRen() {
        return this.editRen;
    }

    /**
     * 设置 edit_ren字段方法
     *
     * @param att_editRen 输入<b>MdDentalClinic.editRen</b>字段的值
     */
    public void setEditRen(String att_editRen) {
        this.editRen = att_editRen;
    }

    /**
     * 设置修改时间(edit_date)字段方法
     *
     * @return 返回 <b>MdDentalClinic.EditDate</b>的值
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "edit_date")
    public Date getEditDate() {
        return this.editDate;
    }

    /**
     * 设置 edit_date字段方法
     *
     * @param att_editDate 输入<b>MdDentalClinic.editDate</b>字段的值
     */
    public void setEditDate(Date att_editDate) {
        this.editDate = att_editDate;
    }

    /**
     * 设置流程状态(flow_state)字段方法
     *
     * @return 返回 <b>MdDentalClinic.FlowState</b>的值
     */
    @Column(name = "flow_state")
    public Integer getFlowState() {
        return this.flowState;
    }

    /**
     * 设置 flow_state字段方法
     *
     * @param att_flowState 输入<b>MdDentalClinic.flowState</b>字段的值
     */
    public void setFlowState(Integer att_flowState) {
        this.flowState = att_flowState;
    }

    /**
     * 设置状态:1正常，2删除(state)字段方法
     *
     * @return 返回 <b>MdDentalClinic.State</b>的值
     */
    @Column(name = "state")
    public Integer getState() {
        return this.state;
    }

    /**
     * 设置 state字段方法
     *
     * @param att_state 输入<b>MdDentalClinic.state</b>字段的值
     */
    public void setState(Integer att_state) {
        this.state = att_state;
    }

    /**
     * 设置银行账号(bank_code)字段方法
     *
     * @return 返回 <b>MdDentalClinic.BankCode</b>的值
     */
    @Column(name = "bank_code")
    public String getBankCode() {
        return this.bankCode;
    }

    /**
     * 设置 bank_code字段方法
     *
     * @param att_bankCode 输入<b>MdDentalClinic.bankCode</b>字段的值
     */
    public void setBankCode(String att_bankCode) {
        this.bankCode = att_bankCode;
    }

    @Column(name = "salesman_ids")
    public String getSalesmanIds() {
        return salesmanIds;
    }

    public void setSalesmanIds(String salesmanIds) {
        this.salesmanIds = salesmanIds;
    }
    ///===============数据库表字段属性end==========

///===============数据库表无关子段字段属性begin==========
    /**
     * 牙医门诊id(rbb_id):字段类型为String
     */
    private String rbbId_str;
    /**
     * 牙医医院id(rbs_id):字段类型为String
     */
    private String rbsId_str;
    /**
     * 创建时间(create_date):字段类型为Date
     */
    private Date createDate_start;
    /**
     * 创建时间(create_date):字段类型为Date
     */
    private Date createDate_end;
    /**
     * 修改时间(edit_date):字段类型为Date
     */
    private Date editDate_start;
    /**
     * 修改时间(edit_date):字段类型为Date
     */
    private Date editDate_end;
    /**
     * 流程状态(flow_state):字段类型为String
     */
    private String flowState_str;
    /**
     * 状态:1正常，2删除(state):字段类型为String
     */
    private String state_str;
    /**
     * ():字段类型为String
     * md_dental_clinic表里不用like作为条件的字符串
     */
    private String Tab_like;
    /**
     * ():字段类型为String
     * md_dental_clinic表里order by 字符串
     */
    private String Tab_order;

    /**
     * 设置rbbId字段方法
     *
     * @return 返回 <b>MdDentalClinic.rbbId</b>的值
     */
    @Transient
    public String getRbbId_str() {
        return this.rbbId_str;
    }

    /**
     * 设置 rbb_id字段方法
     *
     * @param att_rbbId 输入<b>MdDentalClinic.rbbId</b>字段的值
     */
    public void setRbbId_str(String att_rbbId_str) {
        this.rbbId_str = att_rbbId_str;
    }

    /**
     * 设置rbsId字段方法
     *
     * @return 返回 <b>MdDentalClinic.rbsId</b>的值
     */
    @Transient
    public String getRbsId_str() {
        return this.rbsId_str;
    }

    /**
     * 设置 rbs_id字段方法
     *
     * @param att_rbsId 输入<b>MdDentalClinic.rbsId</b>字段的值
     */
    public void setRbsId_str(String att_rbsId_str) {
        this.rbsId_str = att_rbsId_str;
    }

    /**
     * 设置createDate字段方法
     *
     * @return 返回 <b>MdDentalClinic.createDate</b>的值
     */
    @Transient
    public Date getCreateDate_start() {
        return this.createDate_start;
    }

    /**
     * 设置 create_date字段方法
     *
     * @param att_createDate 输入<b>MdDentalClinic.createDate</b>字段的值
     */
    public void setCreateDate_start(Date att_createDate_start) {
        this.createDate_start = att_createDate_start;
    }

    /**
     * 设置createDate字段方法
     *
     * @return 返回 <b>MdDentalClinic.createDate</b>的值
     */
    @Transient
    public Date getCreateDate_end() {
        return this.createDate_end;
    }

    /**
     * 设置 create_date字段方法
     *
     * @param att_createDate 输入<b>MdDentalClinic.createDate</b>字段的值
     */
    public void setCreateDate_end(Date att_createDate_end) {
        this.createDate_end = att_createDate_end;
    }

    /**
     * 设置editDate字段方法
     *
     * @return 返回 <b>MdDentalClinic.editDate</b>的值
     */
    @Transient
    public Date getEditDate_start() {
        return this.editDate_start;
    }

    /**
     * 设置 edit_date字段方法
     *
     * @param att_editDate 输入<b>MdDentalClinic.editDate</b>字段的值
     */
    public void setEditDate_start(Date att_editDate_start) {
        this.editDate_start = att_editDate_start;
    }

    /**
     * 设置editDate字段方法
     *
     * @return 返回 <b>MdDentalClinic.editDate</b>的值
     */
    @Transient
    public Date getEditDate_end() {
        return this.editDate_end;
    }

    /**
     * 设置 edit_date字段方法
     *
     * @param att_editDate 输入<b>MdDentalClinic.editDate</b>字段的值
     */
    public void setEditDate_end(Date att_editDate_end) {
        this.editDate_end = att_editDate_end;
    }

    /**
     * 设置flowState字段方法
     *
     * @return 返回 <b>MdDentalClinic.flowState</b>的值
     */
    @Transient
    public String getFlowState_str() {
        return this.flowState_str;
    }

    /**
     * 设置 flow_state字段方法
     *
     * @param att_flowState 输入<b>MdDentalClinic.flowState</b>字段的值
     */
    public void setFlowState_str(String att_flowState_str) {
        this.flowState_str = att_flowState_str;
    }

    /**
     * 设置state字段方法
     *
     * @return 返回 <b>MdDentalClinic.state</b>的值
     */
    @Transient
    public String getState_str() {
        return this.state_str;
    }

    /**
     * 设置 state字段方法
     *
     * @param att_state 输入<b>MdDentalClinic.state</b>字段的值
     */
    public void setState_str(String att_state_str) {
        this.state_str = att_state_str;
    }

    /**
     * 设置tab_like字段方法
     *
     * @return 返回 <b>MdDentalClinic.tab_like</b>的值
     */
    @Transient
    public String getTab_like() {
        return this.Tab_like;
    }

    /**
     * 设置 字段方法
     *
     * @param att_tab_like 输入<b>MdDentalClinic.tab_like</b>字段的值
     */
    public void setTab_like(String att_Tab_like) {
        this.Tab_like = att_Tab_like;
    }

    /**
     * 设置tab_order字段方法
     *
     * @return 返回 <b>MdDentalClinic.tab_order</b>的值
     */
    @Transient
    public String getTab_order() {
        return this.Tab_order;
    }

    /**
     * 设置 字段方法
     *
     * @param att_tab_order 输入<b>MdDentalClinic.tab_order</b>字段的值
     */
    public void setTab_order(String att_Tab_order) {
        this.Tab_order = att_Tab_order;
    }

    @Column(name = "sales_name")
    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    @Formula("(SELECT a.sales_phone FROM sys_sales_man a WHERE a.salesman_id in (salesman_ids))")
    public String getSalesPhone() {
        return salesPhone;
    }

    public void setSalesPhone(String salesPhone) {
        this.salesPhone = salesPhone;
    }

    public void setVerifyState(Integer verifyState) {
        this.verifyState = verifyState;
    }
    @Column(name = "verify_state")
    public Integer getVerifyState() {
        return verifyState;
    }

    public void setVerifyRemark(String verifyRemark) {
        this.verifyRemark = verifyRemark;
    }
    @Column(name = "verify_remark")
    public String getVerifyRemark() {
        return verifyRemark;
    }

    @Column(name = "logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    ///===============数据库表无关子段字段属性end==========
} 