package com.hsk.miniapi.xframe.api.daobbase.imp;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.ISysSalesmanApiDao;
import com.hsk.xframe.api.persistence.SysSalesManEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/1/3 15:21
 */
@Repository
public class SysSalesmanApiDao extends SupperDao implements ISysSalesmanApiDao {
    @Override
    public void saveSalesman(SysSalesManEntity sysSalesManEntity) throws HSKDBException {
        this.getHibernateDao().saveOrUpdate(sysSalesManEntity);
    }

    @Override
    public PagerModel getPagerModelObject(SysSalesManEntity sysSalesManEntity) throws HSKDBException {
        String hql = getHql(sysSalesManEntity);

        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public SysSalesManEntity getObject(SysSalesManEntity sysSalesManEntity) throws HSKDBException {
        return (SysSalesManEntity) this.getOne(sysSalesManEntity);
    }

    @Override
    public PagerModel getSalesManViewPagerModel(SysSalesManEntity sysSalesManEntity) throws HSKDBException {
        String hql = getHql(sysSalesManEntity);

        return this.getHibernateDao().findByPage(hql);
    }

    private String getHql(SysSalesManEntity sysSalesManEntity) {
        StringBuffer buffer = new StringBuffer("from SysSalesManEntity where 1 = 1");

        if (sysSalesManEntity.getSalesmanId() != null) {
            buffer.append(" and salesmanId=" + sysSalesManEntity.getSalesmanId());
        }

        if (sysSalesManEntity.getWzId() != null) {
            buffer.append(" and wzId=" + sysSalesManEntity.getWzId());
        }
        if (sysSalesManEntity.getOrgGxId() != null) {
            buffer.append(" and orgGxId=" + sysSalesManEntity.getOrgGxId());
        }
        if (sysSalesManEntity.getRbaId() != null && !sysSalesManEntity.getRbaId().equals("")) {
            buffer.append(" and rbaId=" + sysSalesManEntity.getRbaId());
        }
        if (sysSalesManEntity.getRbsId() != null && !sysSalesManEntity.getRbsId().equals("")) {
            buffer.append(" and rbsId=" + sysSalesManEntity.getRbsId());
        }
        if (sysSalesManEntity.getRbbId() != null && !sysSalesManEntity.getRbbId().equals("")) {
            buffer.append(" and rbbId=" + sysSalesManEntity.getRbbId());
        }
        if (sysSalesManEntity.getState() != null) {
            buffer.append(" and state=" + sysSalesManEntity.getState());
        }
        if (sysSalesManEntity.getSalesCode() != null && !sysSalesManEntity.getSalesCode().equals("")) {
            buffer.append(" and salesCode like '%" + sysSalesManEntity.getSalesCode() + "%'");
        }
        if (sysSalesManEntity.getSalesName() != null && !sysSalesManEntity.getSalesName().equals("")) {
            buffer.append(" and  salesName like '%" + sysSalesManEntity.getSalesName() + "%'");
        }
        if (sysSalesManEntity.getSalesAccount() != null && !sysSalesManEntity.getSalesAccount().equals("")) {
            buffer.append(" and  salesAccount like '%" + sysSalesManEntity.getSalesAccount() + "%'");
        }
        if (sysSalesManEntity.getSalesPassword() != null && !sysSalesManEntity.getSalesPassword().equals("")) {
            buffer.append(" and  salesPassword like '%" + sysSalesManEntity.getSalesPassword() + "%'");
        }
        if (sysSalesManEntity.getSalesPhone() != null && !sysSalesManEntity.getSalesPhone().equals("")) {
            buffer.append(" and  salesPhone like '%" + sysSalesManEntity.getSalesPhone() + "%'");
        }
        if (sysSalesManEntity.getSalesAddress() != null && !sysSalesManEntity.getSalesAddress().equals("")) {
            buffer.append(" and  salesAddress like '%" + sysSalesManEntity.getSalesAddress() + "%'");
        }
        if (sysSalesManEntity.getSalesArea() != null && !sysSalesManEntity.getSalesArea().equals("")) {
            buffer.append(" and  salesArea like '%" + sysSalesManEntity.getSalesArea() + "%'");
        }
        if (sysSalesManEntity.getSalesWechat() != null && !sysSalesManEntity.getSalesWechat().equals("")) {
            buffer.append(" and  salesWeChat like '%" + sysSalesManEntity.getSalesWechat() + "%'");
        }
        if (sysSalesManEntity.getSalesQq() != null && !sysSalesManEntity.getSalesQq().equals("")) {
            buffer.append(" and  salesQq like '%" + sysSalesManEntity.getSalesQq() + "%'");
        }
        if (sysSalesManEntity.getSalesEmail() != null && !sysSalesManEntity.getSalesEmail().equals("")) {
            buffer.append(" and  salesEmail like '%" + sysSalesManEntity.getSalesEmail() + "%'");
        }
        if (sysSalesManEntity.getCreateDate() != null) {
            buffer.append(" and createDate = " + sysSalesManEntity.getCreateDate());
        }
        if (sysSalesManEntity.getCreateRen() != null && !sysSalesManEntity.getCreateRen().equals("")) {
            buffer.append(" and  createRen like '%" + sysSalesManEntity.getCreateRen() + "%'");
        }
        if (sysSalesManEntity.getEditDate() != null) {
            buffer.append(" and editDate" + sysSalesManEntity.getEditDate());
        }
        if (sysSalesManEntity.getEditRen() != null && !sysSalesManEntity.getEditRen().equals("")) {
            buffer.append(" and  editRen like '%" + sysSalesManEntity.getEditRen() + "%'");
        }
        if (sysSalesManEntity.getDescribes() != null && !sysSalesManEntity.getDescribes().equals("")) {
            buffer.append(" and  describes like '%" + sysSalesManEntity.getDescribes() + "%'");
        }
        if (sysSalesManEntity.getPurchaseType() != null) {
            buffer.append(" and  purchaseType =" + sysSalesManEntity.getPurchaseType());
        }
        if (sysSalesManEntity.getSalesCompany() != null && !sysSalesManEntity.getSalesCompany().equals("")) {
            buffer.append(" and  salesCompany like '%" + sysSalesManEntity.getSalesCompany() + "%'");
        }
        if (sysSalesManEntity.getCreateDateStart() != null && !sysSalesManEntity.getCreateDateStart().equals("")) {
            String createDate = sysSalesManEntity.getCreateDateStart();
            String[] createDateArray = createDate.split("~");
            String createDateStart = createDateArray[0].trim();
            String createDateEnd = createDateArray[1].trim();
            buffer.append(" and (createDate >= '" + createDateStart + " 00:00:00' and createDate <= '" + createDateEnd + " 23:59:59')");
        }
        if (sysSalesManEntity.getSalesType() != null) {
            buffer.append(" and sales_type = " + sysSalesManEntity.getSalesType());
        }
        if (sysSalesManEntity.getAgentCompany() != null && !sysSalesManEntity.getAgentCompany().equals("")) {
            buffer.append(" and agent_company like '%" + sysSalesManEntity.getAgentCompany() + "%'");
        }
        if (sysSalesManEntity.getExludeId() != null) {
            buffer.append(" and salesmanId <> " + sysSalesManEntity.getExludeId());
        }
        if (sysSalesManEntity.getLeaderId() != null) {
            buffer.append(" and leaderId = " + sysSalesManEntity.getLeaderId());
        }
        if (sysSalesManEntity.getLeaderName() != null && !sysSalesManEntity.getLeaderName().equals("")) {
            buffer.append(" and leaderName like '%" + sysSalesManEntity.getLeaderName() + "%'");
        }
        if (sysSalesManEntity.getUserTypeStr() != null && !sysSalesManEntity.getUserTypeStr().equals("")){
            if (sysSalesManEntity.getUserTypeStr().equals("0")){
                buffer.append(" and salesType not in (1,2)");
            }else {
                buffer.append(" and salesType in (" + sysSalesManEntity.getUserTypeStr() + ")");
            }
        }
        buffer.append(" order by createDate desc");
        return buffer.toString();
    }

    @Override
    public void updateSysSalesmanBySalesId(SysSalesManEntity sysSalesManEntity) throws HSKDBException {
        sysSalesManEntity = (SysSalesManEntity) this.getHibernatesession().merge(sysSalesManEntity);
        this.getHibernateDao().saveOrUpdate(sysSalesManEntity);
    }

    @Override
    public void deleteObject(SysSalesManEntity sysSalesManEntity) throws HSKDBException {
        this.getHibernateDao().delete(sysSalesManEntity);
    }

    @Override
    public void deleteAllObject(SysSalesManEntity sysSalesManEntity) throws HSKDBException {

    }

    @Override
    public PagerModel getPageModelInfo(SysSalesManEntity sysSalesManEntity) throws HSKDBException {
        if (sysSalesManEntity == null) {
            return null;
        }
        System.out.println("sysSalesManEntity+"+sysSalesManEntity);
        String sql = "SELECT * FROM ( SELECT sales_name, agent_company, NULL AS rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM sys_sales_man WHERE leader_id = " + sysSalesManEntity.getSalesmanId() +
                " UNION ALL" +
                " SELECT NULL AS sales_name, NULL AS agent_company, rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM md_company_group WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )" +
                " UNION ALL " +
                " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, rbs_name, NULL AS rbb_name, create_date, state FROM md_dentist_hospital WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )" +
                " UNION ALL" +
                " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, NULL AS rbs_name, rbb_name, create_date, state FROM md_dental_clinic WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )) AS a where 1=1";
//        if (sysSalesManEntity.getSalesmanId() != null) {
//            sql += "and a.leader_id=" + sysSalesManEntity.getSalesmanId();
//        }
        if (sysSalesManEntity.getSalesName() != null && !sysSalesManEntity.getSalesName().equals("")) {
            sql += " and (a.sales_name like '%" + sysSalesManEntity.getSalesName() + "%'" +
                    " or a.agent_company like '%" + sysSalesManEntity.getSalesName() + "%'" +
                    " or a.rba_name like '%" + sysSalesManEntity.getSalesName() + "%'" +
                    " or a.rbs_name like '%" + sysSalesManEntity.getSalesName() + "%'" +
                    "or a.rbb_name like '%" + sysSalesManEntity.getSalesName() + "%')";
        }
        if (sysSalesManEntity.getState() != null) {
            sql += " and a.state = " + sysSalesManEntity.getState();
        }
        sql += " order by a.create_date desc";
        return this.getJdbcDao().findByPage(sql);
    }

    @Override
    public Integer getPageModelInfoLeadedCount(SysSalesManEntity sysSalesManEntity, Integer i) throws HSKDBException {
        String sql = "select count(sales_name) as count from sys_sales_man where 1=1";
        if (sysSalesManEntity.getSalesmanId() != null) {
            sql += " and leader_id = " + sysSalesManEntity.getSalesmanId();
        }
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.size() <= 0) {
            return 0;
        }
        Map<String, Object> map = list.get(0);
        if (map == null) {
            return 0;
        }
        Integer count = Integer.valueOf(map.get("count").toString());
        return count;
    }

    @Override
    public Integer getPageModelInfoCompanyCount(SysSalesManEntity sysSalesManEntity, Integer i) throws HSKDBException {
        if (sysSalesManEntity.getSalesmanId() == null) {
            return 0;
        }
        String sql = "select count(*) as count from (" +
                "SELECT NULL AS sales_name, NULL AS agent_company, rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM md_company_group WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )" +
                " UNION ALL " +
                " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, rbs_name, NULL AS rbb_name, create_date, state FROM md_dentist_hospital WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )" +
                " UNION ALL" +
                " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, NULL AS rbs_name, rbb_name, create_date, state FROM md_dental_clinic WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )) AS a where 1=1";
//        if (sysSalesManEntity.getSalesmanId() != null) {
//            sql += " and a.salesman_id = " + sysSalesManEntity.getSalesmanId();
//        }
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.size() <= 0) {
            return 0;
        }
        Map<String, Object> map = list.get(0);
        if (map == null) {
            return 0;
        }
        Integer count = Integer.valueOf(map.get("count").toString());
        return count;
    }
    //导出业务员报表
    public List<Map<String, Object>> exportSalesMan(SysSalesManEntity sysSalesManEntity) throws HSKDBException{
        String sql = "SELECT * FROM ( SELECT sales_name, agent_company, NULL AS rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM sys_sales_man WHERE leader_id = " + sysSalesManEntity.getSalesmanId() +
                " UNION ALL" +
                " SELECT NULL AS sales_name, NULL AS agent_company, rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM md_company_group WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )" +
                " UNION ALL " +
                " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, rbs_name, NULL AS rbb_name, create_date, state FROM md_dentist_hospital WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )" +
                " UNION ALL" +
                " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, NULL AS rbs_name, rbb_name, create_date, state FROM md_dental_clinic WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )) AS a where 1=1";
//        if (sysSalesManEntity.getSalesmanId() != null) {
//            sql += "and a.leader_id=" + sysSalesManEntity.getSalesmanId();
//        }
        if (sysSalesManEntity.getSalesName() != null && !sysSalesManEntity.getSalesName().equals("")) {
            sql += " and (a.sales_name like '%" + sysSalesManEntity.getSalesName() + "%'" +
                    " or a.agent_company like '%" + sysSalesManEntity.getSalesName() + "%'" +
                    " or a.rba_name like '%" + sysSalesManEntity.getSalesName() + "%'" +
                    " or a.rbs_name like '%" + sysSalesManEntity.getSalesName() + "%'" +
                    "or a.rbb_name like '%" + sysSalesManEntity.getSalesName() + "%')";
        }
        if (sysSalesManEntity.getState() != null) {
            sql += " and a.state = " + sysSalesManEntity.getState();
        }
        sql += " order by a.create_date desc";
        System.out.println(sql);
        return this.getJdbcDao().query(sql);
    }

}
