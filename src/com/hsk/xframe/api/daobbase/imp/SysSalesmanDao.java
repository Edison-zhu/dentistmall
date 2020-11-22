package com.hsk.xframe.api.daobbase.imp;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.ISysSalesmanDao;
import com.hsk.xframe.api.persistence.SysSalesManEntity;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.web.sysUser.service.ISysUserService;
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
public class SysSalesmanDao extends SupperDao implements ISysSalesmanDao {
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
    public SysSalesManEntity getSalesManInfo(SysSalesManEntity sysSalesManEntity) throws HSKDBException {
        String hql = "from SysSalesManEntity where 1=1";
        if (sysSalesManEntity != null && sysSalesManEntity.getSalesAccount() != null && !sysSalesManEntity.getSalesAccount().trim().equals(""))
            hql += " and salesAccount='" + sysSalesManEntity.getSalesAccount().trim() + "'";
        List<SysSalesManEntity> userList = this.getHibernateTemplate().find(hql);
        if (userList != null && userList.size() > 0)
            return userList.get(0);
        return null;
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
        if (sysSalesManEntity.getExcludeType() != null) {
            buffer.append(" and salesType <>" + sysSalesManEntity.getExcludeType());
        }
        if (sysSalesManEntity.getExcludeTypes() != null && !sysSalesManEntity.getExcludeTypes().equals("")) {
            buffer.append(" and salesType not in (" + sysSalesManEntity.getExcludeTypes() + ")");
        }
        if (sysSalesManEntity.getLeaderId() != null) {
            buffer.append(" and leaderId = " + sysSalesManEntity.getLeaderId());
        }
        if (sysSalesManEntity.getLeaderName() != null && !sysSalesManEntity.getLeaderName().equals("")) {
            buffer.append(" and leaderName like '%" + sysSalesManEntity.getLeaderName() + "%'");
        }
        if (sysSalesManEntity.getUserTypeStr() != null && !sysSalesManEntity.getUserTypeStr().equals("")) {
            if (sysSalesManEntity.getUserTypeStr().equals("0")) {
                buffer.append(" and salesType not in (1,2)");
            } else {
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
        String sql = "SELECT * FROM ( SELECT sales_name, agent_company, NULL AS rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM sys_sales_man WHERE leader_id = " + sysSalesManEntity.getSalesmanId() +
                " UNION ALL" +
                " SELECT NULL AS sales_name, NULL AS agent_company, rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM md_company_group WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )" +
                " UNION ALL " +
                " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, rbs_name, NULL AS rbb_name, create_date, state FROM md_dentist_hospital WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )" +
                " UNION ALL" +
                " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, NULL AS rbs_name, rbb_name, create_date, state FROM md_dental_clinic WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )";
        if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 3) {
            sql += " UNION ALL" +
                    " SELECT NULL AS sales_name, CONCAT(applicant_name, '(供应商)') AS agent_company, NULL AS rba_name, NULL AS rbs_name,  NULL AS rbb_name, sales_time AS create_date, state FROM md_supplier WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )";
        }
        sql += ") AS a where 1=1";
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
                " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, NULL AS rbs_name, rbb_name, create_date, state FROM md_dental_clinic WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )";
//        if (sysSalesManEntity.getSalesmanId() != null) {
//            sql += " and a.salesman_id = " + sysSalesManEntity.getSalesmanId();
//        }
//        if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 3) {
//            sql += " UNION ALL" +
//                    " SELECT NULL AS sales_name, CONCAT(applicant_name, '(供应商)') AS agent_company, NULL AS rba_name, NULL AS rbs_name,  NULL AS rbb_name, sales_time AS create_date, state FROM md_supplier WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )";
//        }
        sql += ") AS a where 1=1";
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
    public List<Map<String, Object>> exportSalesMan(SysSalesManEntity sysSalesManEntity) throws HSKDBException {
        String sql = "";
        if (sysSalesManEntity.getSalesmanId() != 0) {
            sql += "SELECT * FROM ( SELECT sales_name, agent_company, NULL AS rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM sys_sales_man WHERE leader_id = " + sysSalesManEntity.getSalesmanId() +
                    " UNION ALL" +
                    " SELECT NULL AS sales_name, NULL AS agent_company, rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM md_company_group WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )" +
                    " UNION ALL " +
                    " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, rbs_name, NULL AS rbb_name, create_date, state FROM md_dentist_hospital WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )" +
                    " UNION ALL" +
                    " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, NULL AS rbs_name, rbb_name, create_date, state FROM md_dental_clinic WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )";
            if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 3) {
                sql += " UNION ALL" +
                        " SELECT NULL AS sales_name, CONCAT(applicant_name, '(供应商)') AS agent_company, NULL AS rba_name, NULL AS rbs_name,  NULL AS rbb_name, sales_time AS create_date, state FROM md_supplier WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )";
            }
            sql += ") AS a where 1=1";
        } else {
            sql += "SELECT * FROM ( SELECT sales_name, agent_company, NULL AS rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM sys_sales_man WHERE leader_id = leader_id" +
                    " UNION ALL" +
                    " SELECT NULL AS sales_name, NULL AS agent_company, rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM md_company_group WHERE FIND_IN_SET( " + "salesman_ids" + ", salesman_ids )" +
                    " UNION ALL " +
                    " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, rbs_name, NULL AS rbb_name, create_date, state FROM md_dentist_hospital WHERE FIND_IN_SET( " + "salesman_ids" + ", salesman_ids )" +
                    " UNION ALL" +
                    " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, NULL AS rbs_name, rbb_name, create_date, state FROM md_dental_clinic WHERE FIND_IN_SET( " + "salesman_ids" + ", salesman_ids )";
            if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 3) {
                sql += " UNION ALL" +
                        " SELECT NULL AS sales_name, CONCAT(applicant_name, '(供应商)') AS agent_company, NULL AS rba_name, NULL AS rbs_name,  NULL AS rbb_name, sales_time AS create_date, state FROM md_supplier WHERE FIND_IN_SET( " + "salesman_ids" + ", salesman_ids )";
            }
            sql += ") AS a where 1=1";
        }
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
        sql += " order by a.create_date ";
        return this.getJdbcDao().query(sql);
    }

    //导出管理员登陆下查看关联下导出全部
    public List<Map<String, Object>> exportSalesManAdmin(SysSalesManEntity sysSalesManEntity) throws HSKDBException {
        String sql = "";
        sql += "SELECT * FROM ( SELECT sales_name, agent_company, NULL AS rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM sys_sales_man WHERE leader_id = " + sysSalesManEntity.getSalesmanId() +
                " UNION ALL" +
                " SELECT NULL AS sales_name, NULL AS agent_company, rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM md_company_group WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )" +
                " UNION ALL " +
                " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, rbs_name, NULL AS rbb_name, create_date, state FROM md_dentist_hospital WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )" +
                " UNION ALL" +
                " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, NULL AS rbs_name, rbb_name, create_date, state FROM md_dental_clinic WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )";
        if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 3) {
            sql += " UNION ALL" +
                    " SELECT NULL AS sales_name, CONCAT(applicant_name, '(供应商)') AS agent_company, NULL AS rba_name, NULL AS rbs_name,  NULL AS rbb_name, sales_time AS create_date, state FROM md_supplier WHERE FIND_IN_SET( " + sysSalesManEntity.getSalesmanId() + ", salesman_ids )";
        }
        sql += ") AS a where 1=1";
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
        sql += " order by a.create_date ";
        return this.getJdbcDao().query(sql);
    }

    //导出管理员登陆下查看关联下导出全部
    public List<Map<String, Object>> exportSalesManAll(SysSalesManEntity sysSalesManEntity) throws HSKDBException {
        String sql = "";
        sql += "SELECT * FROM ( SELECT sales_name, agent_company, NULL AS rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM sys_sales_man WHERE leader_id = leader_id" +
                " UNION ALL" +
                " SELECT NULL AS sales_name, NULL AS agent_company, rba_name, NULL AS rbs_name, NULL AS rbb_name, create_date, state FROM md_company_group WHERE FIND_IN_SET( " + "salesman_ids" + ", salesman_ids )" +
                " UNION ALL " +
                " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, rbs_name, NULL AS rbb_name, create_date, state FROM md_dentist_hospital WHERE FIND_IN_SET( " + "salesman_ids" + ", salesman_ids )" +
                " UNION ALL" +
                " SELECT NULL AS sales_name, NULL AS agent_company, NULL AS rba_name, NULL AS rbs_name, rbb_name, create_date, state FROM md_dental_clinic WHERE FIND_IN_SET( " + "salesman_ids" + ", salesman_ids )";
        if (sysSalesManEntity.getSalesType() != null && sysSalesManEntity.getSalesType() == 3) {
            sql += " UNION ALL" +
                    " SELECT NULL AS sales_name, CONCAT(applicant_name, '(供应商)') AS agent_company, NULL AS rba_name, NULL AS rbs_name,  NULL AS rbb_name, sales_time AS create_date, state FROM md_supplier WHERE FIND_IN_SET( " + "salesman_ids" + ", salesman_ids )";
        }
        sql += ") AS a where 1=1";

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
        sql += " order by a.create_date ";
        return this.getJdbcDao().query(sql);
    }

    public List<Map<String, Integer>> selectSalesManId(Integer salesAccount) throws HSKDBException {
        String sql = "SELECT salesman_id FROM sys_sales_man  WHERE 1 = 1 AND leader_id= " + salesAccount;
        return this.getJdbcDao().query(sql);
    }

    public List<Map<String, Integer>> selectSalesManId1(Integer orgId) throws HSKDBException {
        String sql = "SELECT salesman_id FROM sys_sales_man  WHERE 1 = 1 AND org_gx_id= " + orgId;
        return this.getJdbcDao().query(sql);
    }

    @Override
    public List<Map<String, Object>> getSalesManLoanMxList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1, String moneyFw1, String moneyFw2, String gongYingS, String caiGou, String jiGouMenZhen,
                                                           Integer selectValue, String dateInput7, String dateInput8, Integer moiId, Integer limit, Integer page) throws HSKDBException {
        String sql = "SELECT t5.sales_name, t5.sales_type, t2.sales_name as cs_name, t3.sales_name as hs_name, t4.sales_name as cls_name," +
                " t1.settlement, t1.moi_id as moi_id, t1.rba_id, t1.rbs_id, t1.rbb_id,t1.loan_log ,t1.Process_status as process_status,t1.order_code, t1.place_order_time, t1.pay_type as pay_type,t1.pay_code," +
                "t1.pay_date,t1.place_order_money,t1.actual_money,t1.purchase_unit,t1.applicant_name,t1.loan_state,t1.loan_money, t1.settlement FROM md_order_info t1 " +
                " left join md_company_group t2 on t1.rba_id = t2.rba_id" +
                " left join md_dentist_hospital t3 on t3.rbs_id = t1.rbs_id" +
                " left join md_dental_clinic t4 on t4.rbb_id = t1.rbb_id," +
                " sys_sales_man t5" +
                " where (t5.salesman_id in (t2.salesman_ids) or t5.salesman_id in (t3.salesman_ids) or t5.salesman_id in (t4.salesman_ids))";
        if (orderCodeGJ != null && orderCodeGJ != "") {
            sql += " AND t1.order_code LIKE '%" + orderCodeGJ.toUpperCase() + "%'";
        }
        if (moiId != null) {
            sql += " AND t1.moi_id=" + moiId;
        }
        if (dateInput1 != null && dateInput2 != null && dateInput1 != "" && dateInput2 != "") {
            sql += " AND t1.Place_order_time> '" + dateInput1 + " 00:00:00'";
            sql += " AND t1.Place_order_time< '" + dateInput2 + " 23:59:59'";
        }
        if (selectValue != null && selectValue != 0) {
            if (selectValue != 3) {
                sql += " AND t1.loan_state=" + selectValue;
            } else {
                sql += " AND (t1.loan_state = 3 or t1.loan_state is null)";
            }
        }
        if (selectValue1 != null && selectValue1 != 0) {
            if (selectValue1 != 7) {
                sql += " and t1.process_status = " + selectValue1;
            } else if (selectValue1 == 8) {
                sql += " and t1.settlement = 1";
            } else if (selectValue1 == 9) {
                sql += " and t1.settlement <> 1";
            } else {
                sql += " and (t1.after_sale is not null and t1.after_sale <> 0)";
            }
        }
        if (moneyFw1 != null && moneyFw1 != "" && moneyFw2 != null && moneyFw2 != "") {
            sql += " AND t1.place_order_money >" + moneyFw1;
            sql += " AND t1.place_order_money <" + moneyFw2;
        }
        if (gongYingS != null && gongYingS != "") {
            sql += " AND t1.applicant_Name LIKE '%" + gongYingS + "%'";
        }
        if (caiGou != null && caiGou != "") {
            sql += " AND t5.salesman_id = " + caiGou;
        }
        if (jiGouMenZhen != null) {
            sql += " and t1.purchase_unit like '%" + jiGouMenZhen + "%'";
        }
//        if (moiId != null) {
//        } else {
//            sql += " AND t1.Process_status in(5,6) ";
//            sql += " AND NOT ISNULL(t1.Actual_money ) ";
//        }
        if (limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }

        return this.getJdbcDao().query(sql);
    }

    @Override
    public Integer totalAll() throws HSKDBException {
        String sql = "SELECT count(t1.moi_id) as count FROM md_order_info t1 " +
                " left join md_company_group t2 on t1.rba_id = t2.rba_id" +
                " left join md_dentist_hospital t3 on t3.rbs_id = t1.rbs_id" +
                " left join md_dental_clinic t4 on t4.rbb_id = t1.rbb_id," +
                " sys_sales_man t5" +
                " where (t5.salesman_id in (t2.salesman_ids) or t5.salesman_id in (t3.salesman_ids) or t5.salesman_id in (t4.salesman_ids))";
        List<Map<Object, Object>> list = this.getJdbcDao().query(sql);
        if (!list.isEmpty()) {
            return Integer.parseInt(list.get(0).get("count").toString());
        }
        return 0;
    }

    @Override
    public Double totalLanMoney(Integer lanState) throws HSKDBException {
        String sql = "SELECT sum(t1.Actual_money) as money FROM md_order_info t1 " +
                " left join md_company_group t2 on t1.rba_id = t2.rba_id" +
                " left join md_dentist_hospital t3 on t3.rbs_id = t1.rbs_id" +
                " left join md_dental_clinic t4 on t4.rbb_id = t1.rbb_id," +
                " sys_sales_man t5" +
                " where (t5.salesman_id in (t2.salesman_ids) or t5.salesman_id in (t3.salesman_ids) or t5.salesman_id in (t4.salesman_ids))";
        if (lanState != null) {
            sql += " and loan_state=" + lanState;
        }
        List<Map<Object, Object>> list = this.getJdbcDao().query(sql);
        if (list.isEmpty()) {
            return 0d;
        }
        if (list.get(0).get("money") != null) {
            return Double.parseDouble(list.get(0).get("money").toString());
        }
        return 0d;
    }

    @Override
    public PagerModel getPagerModelSupplier(Integer salesmanId) throws HSKDBException {
        String hql = "from MdSupplier where state = 1";
        if (salesmanId != null) {
            hql += " where salesmanId = '" + salesmanId + "'";
        }
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public Integer getPagerModelInfoSupplierCount(SysSalesManEntity sysSalesManEntity, Integer i) throws HSKDBException {
        String sql = "select count(wz_id) as count from md_supplier where 1=1";
        if (sysSalesManEntity.getSalesmanId() != null) {
            sql += " and salesman_ids = '" + sysSalesManEntity.getSalesmanId() + "'";
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


}
