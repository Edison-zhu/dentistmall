package com.hsk.dentistmall.api.daobbase.imp;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.persistence.*;
import com.hsk.supper.dto.SystemContext;
import org.springframework.stereotype.Component;

import com.hsk.dentistmall.api.daobbase.IMdInventoryExtendDao;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;

@Component
public class MdInventoryExtendDao extends SupperDao implements IMdInventoryExtendDao {

    @Override
    public MdInventoryExtend getMdInventoryExtendByWiId(Integer wzId, String relatedCode) throws HSKDBException {
        String hql = "from MdInventoryExtend where 1=1";
        if (wzId != null)
            hql += " and wiId = " + wzId;
        if (relatedCode != null && !relatedCode.equals(""))
            hql += " and relatedCode='" + relatedCode + "'";
        List<MdInventoryExtend> list = this.getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0)
            return list.get(0);
        return null;
    }

    @Override
    public List<MdInventoryExtend> getMdInventoryExtendByWiIdToSel(Integer wiId, String relatedCode, String startDate, String endDate) throws HSKDBException {
        String hql = "from MdInventoryExtend where wiId='" + wiId + "'";
        if (relatedCode != null && !relatedCode.trim().equals(""))
            hql += " and relatedCode like '%" + relatedCode.trim() + "%'";
        if (startDate != null && !startDate.trim().equals(""))
            hql += " and createDate >='" + startDate.trim() + " 00:00:00'";
        if (endDate != null && !endDate.trim().equals(""))
            hql += " and createDate <='" + endDate.trim() + " 23:59:59'";
        hql += " and baseNumber>0 order by createDate";
        List<MdInventoryExtend> list = this.getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public List<MdInventoryExtend> getAllList() throws HSKDBException {
        String hql = "from MdInventoryExtend order by createDate";
        List<MdInventoryExtend> list = this.getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public PagerModel getMdInventoryViewPager(MdInventoryExtendView mdInventoryExtendView, String startDate, String endDate) throws HSKDBException {
        String hql = "from MdInventoryExtendView where state=1";
        if (mdInventoryExtendView != null && mdInventoryExtendView.getWiId() != null)
            hql += " and wiId='" + mdInventoryExtendView.getWiId() + "'";
        if (mdInventoryExtendView != null && mdInventoryExtendView.getRelatedCode() != null && !mdInventoryExtendView.getRelatedCode().trim().equals(""))
            hql += " and relatedCode like '%" + mdInventoryExtendView.getRelatedCode().trim() + "%'";
        if (mdInventoryExtendView != null && mdInventoryExtendView.getMatType() != null && !mdInventoryExtendView.getMatType().trim().equals(""))
            hql += " and matType like '%/" + mdInventoryExtendView.getMatType().trim() + "/%'";
        if (mdInventoryExtendView != null && mdInventoryExtendView.getTypeName() != null && !mdInventoryExtendView.getTypeName().trim().equals(""))
            hql += " and typeName like '%" + mdInventoryExtendView.getTypeName().trim() + "%'";
        if (mdInventoryExtendView != null && mdInventoryExtendView.getBrand() != null && !mdInventoryExtendView.getBrand().trim().equals(""))
            hql += " and brand like '%" + mdInventoryExtendView.getBrand().trim() + "%'";
        if (startDate != null && !startDate.trim().equals(""))
            hql += " and createDate >='" + startDate.trim() + " 00:00:00'";
        if (endDate != null && !endDate.trim().equals(""))
            hql += " and createDate <='" + endDate.trim() + " 23:59:59'";
        hql += " order by baseNumber desc,createDate";
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public List<MdInventoryExtend> getExtendEnterViewPagerModel(Integer wiId, String relatedCode, String startDate, String endDate, Integer billType, String receiptDatetime) throws HSKDBException {
//        String hql = "from MdInventoryEnterwarehouserViewEntity where wiId='" + wiId + "'";
        
        String sql = "SELECT DISTINCT a.mie_id AS mieId, a.wi_id AS wiId, a.base_number AS baseNumber, a.related_code AS relatedCode," +
                "date_format( a.create_date, '%Y-%m-%d %H:%i:%s' ) AS createDate, a.QUANTITY AS quantity, a.split_quantity AS splitQuantity, a.brand AS brand, a.Basic_unit AS basicUnit," +
                "a.unit AS unit, c.product_factory AS productFactory, c.product_time AS productTime, date_format( c.product_valitime, '%Y-%m-%d %H:%i:%s' ) AS productValitime," +
                "c.product_name AS productName, c.batch_certNo AS batchCertNo, d.ratio as ratio, c.edit_ren as editRen " +
                "FROM  md_inventory_extend a left join md_inventory d on a.wi_id = d.wi_id LEFT JOIN md_enter_warehouse b ON  a.related_code = b.Billcode " +
                "LEFT JOIN md_enter_warehouse_mx c ON b.wew_id = c.wew_id  AND  a.wms_mi_id = c.wms_mi_id where 1=1 and a.wi_id = " + wiId;
        
        if (relatedCode != null && !relatedCode.trim().equals(""))
            sql += " and a.related_code like '%" + relatedCode.trim() + "%'";
        if (startDate != null && !startDate.trim().equals(""))
            sql += " and createDate >='" + startDate.trim() + " 00:00:00'";
        if (endDate != null && !endDate.trim().equals(""))
            sql += " and createDate <='" + endDate.trim() + " 23:59:59'";
        if (billType != null) {
            sql += " and b.bill_type = '" + billType + "'";
        }
        if (receiptDatetime != null && !receiptDatetime.equals("")) {
            sql += " and b.create_date like '%" + receiptDatetime + "%'";
        }
        sql += " and (a.base_number>0 or a.quantity > 0) group by a.mie_id order by createDate";



//        List<MdInventoryExtend> list = this.getHibernateTemplate().find(hql);
        List<MdInventoryExtend> list = this.getJdbcDao().query(sql);
        return list;
    }

    @Override
    public List<MdInventoryEnterwarehouserViewEntity> getExtendEnterWarehouseViewPagerModel(Integer wiId, String relatedCode, String startDate, String endDate) throws HSKDBException {
        String hql = "from MdInventoryEnterwarehouserViewEntity where wiId='" + wiId + "'";
        if (relatedCode != null && !relatedCode.trim().equals(""))
            hql += " and relatedCode like '%" + relatedCode.trim() + "%'";
        if (startDate != null && !startDate.trim().equals(""))
            hql += " and createDate >='" + startDate.trim() + " 00:00:00'";
        if (endDate != null && !endDate.trim().equals(""))
            hql += " and createDate <='" + endDate.trim() + " 23:59:59'";
        hql += " and baseNumber>0 order by createDate";
        List<MdInventoryEnterwarehouserViewEntity> list = this.getHibernateTemplate().find(hql);
        return list;
    }

    @Override
    public PagerModel getMdInventoryExtendByCheckInventory(MdInventoryCheckViewEntity mdCheckInventoryEntity) throws HSKDBException {
        String hql = "from MdInventoryCheckViewEntity where 1=1";
        if (mdCheckInventoryEntity.getCiId() != null) {
            hql += " and ciId = " + mdCheckInventoryEntity.getCiId();
        }
        if (mdCheckInventoryEntity.getSearchName() != null && !mdCheckInventoryEntity.getSearchName().equals("")) {
            hql += " and (mmfName like '%" + mdCheckInventoryEntity.getSearchName() + "%'" +
                    " or matName like '%" + mdCheckInventoryEntity.getSearchName() + "%'" +
                    " or mmfId in (select mmfId from MdMaterielFormat where mmfCode like '%" + mdCheckInventoryEntity.getSearchName() + "%'))";
        }
        if (mdCheckInventoryEntity.getMdpId() != null)
            hql += " and mdpId = " + mdCheckInventoryEntity.getMdpId();
        if (mdCheckInventoryEntity.getMdpsId() != null)
            hql += " and mdpsId = " + mdCheckInventoryEntity.getMdpsId();
        if (mdCheckInventoryEntity.getBrand() != null && !mdCheckInventoryEntity.getBrand().equals(""))
            hql += " and brand like '%" + mdCheckInventoryEntity.getBrand() + "%'";
        if (mdCheckInventoryEntity.getBatchCode() != null && !mdCheckInventoryEntity.getBatchCode().equals(""))
            hql += " and batchCode like '%" + mdCheckInventoryEntity.getBatchCode() + "%'";
        if (mdCheckInventoryEntity.getCheckPart() != null && mdCheckInventoryEntity.getCheckParts() != null) {
            hql += " and (mdpId in (" + mdCheckInventoryEntity.getCheckPart() + ")";
            hql += " or mdpsId in (" + mdCheckInventoryEntity.getCheckParts() + "))";
        } else if (mdCheckInventoryEntity.getCheckPart() != null)
            hql += " and mdpId in (" + mdCheckInventoryEntity.getCheckPart() + ")";
        else if (mdCheckInventoryEntity.getCheckParts() != null)
            hql += " and mdpsId in (" + mdCheckInventoryEntity.getCheckParts() + ")";
        hql += " GROUP BY cieId";
        hql += " order by cieId, isCheck";
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public Integer getMdInventoryExtendByCheckInventoryCount(MdInventoryCheckViewEntity mdCheckInventoryEntity) throws HSKDBException {
        String hql = "select count(t.cie_id) as count from (select cie_id from md_inventory_check_view where 1=1";
        if (mdCheckInventoryEntity.getCiId() != null) {
            hql += " and ci_id = " + mdCheckInventoryEntity.getCiId();
        }
        if (mdCheckInventoryEntity.getSearchName() != null && !mdCheckInventoryEntity.getSearchName().equals("")) {
            hql += " and (mmf_name like '%" + mdCheckInventoryEntity.getSearchName() + "%'" +
                    " or mat_name like '%" + mdCheckInventoryEntity.getSearchName() + "%'" +
                    " or mmf_id in (select mmf_id from md_materiel_format where mmf_code like '%" + mdCheckInventoryEntity.getSearchName() + "%'))";
        }
        if (mdCheckInventoryEntity.getMdpId() != null)
            hql += " and mdp_id = " + mdCheckInventoryEntity.getMdpId();
        if (mdCheckInventoryEntity.getMdpsId() != null)
            hql += " and mdps_id = " + mdCheckInventoryEntity.getMdpsId();
        if (mdCheckInventoryEntity.getBrand() != null && !mdCheckInventoryEntity.getBrand().equals(""))
            hql += " and brand like '%" + mdCheckInventoryEntity.getBrand() + "%'";
        if (mdCheckInventoryEntity.getBatchCode() != null && !mdCheckInventoryEntity.getBatchCode().equals(""))
            hql += " and batch_code like '%" + mdCheckInventoryEntity.getBatchCode() + "%'";
        hql += " and (1=1";
        if (mdCheckInventoryEntity.getCheckPart() != null && !mdCheckInventoryEntity.getCheckPart().equals(""))
            hql += " and mdp_id in (" + mdCheckInventoryEntity.getCheckPart() + ")";
        if (mdCheckInventoryEntity.getCheckParts() != null && !mdCheckInventoryEntity.getCheckParts().equals(""))
            hql += " or mdps_id in (" + mdCheckInventoryEntity.getCheckParts() + ")";
        hql += ")";
        hql += " GROUP BY cie_id";
        hql += " order by cie_id, is_check) as t";
        List<Map<String, Object>> list = this.getJdbcDao().query(hql);
        if (list == null || list.isEmpty())
            return 0;
        Map<String, Object> map = list.get(0);
        if (map.isEmpty() || map.get("count") == null || map.get("count").toString().equals(""))
            return 0;
        return Integer.parseInt(map.get("count").toString());
    }

    @Override
    public MdCheckInventoryEntity findCheckInventory(MdCheckInventoryEntity mdCheckInventoryEntity) throws HSKDBException {
        String hql = getCheckHql(mdCheckInventoryEntity, new StringBuffer("from MdCheckInventoryEntity where 1=1"));

        List<MdCheckInventoryEntity> list = this.getHibernateTemplate().find(hql);
        if (list.isEmpty()) {
            return null;
        }
        Object obj = list.get(0);
        if (obj == null) {
            return null;
        }
        return (MdCheckInventoryEntity) obj;
    }

    @Override
    public PagerModel getMdCheckInventory(MdCheckInventoryEntity mdCheckInventoryEntity) throws HSKDBException {
        String hql = getCheckHql(mdCheckInventoryEntity, new StringBuffer("from MdCheckInventoryEntity where 1=1"));

        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public PagerModel getPagerModelPA(MdInventoryPriceAdjustmentEntity mdInventoryPriceAdjustmentEntity) throws HSKDBException {
        String hql = getPAHql(mdInventoryPriceAdjustmentEntity, null);
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public PagerModel getPagerModelPAEx(MdInventoryPriceAdjustmentEntity mdInventoryPriceAdjustmentEntity) throws HSKDBException {
        String hql = getPAHql(mdInventoryPriceAdjustmentEntity, new StringBuffer("from MdInventoryExtendPaExEntity where 1=1"));
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public PagerModel getPagerModelMdInventoryExtend(MdInventoryExtendView att_mdInventoryView) throws HSKDBException {
        StringBuffer sb= new StringBuffer("from MdInventoryExtendView where state=1");
        if(att_mdInventoryView.getRbaId() != null)
            sb.append(" and rbaId="+att_mdInventoryView.getRbaId());
        if(att_mdInventoryView.getRbsId() != null)
            sb.append(" and rbsId="+att_mdInventoryView.getRbsId());
        if(att_mdInventoryView.getRbbId() != null)
            sb.append(" and rbbId="+att_mdInventoryView.getRbbId());
        if (att_mdInventoryView.getMdpId() != null) {
            sb.append(" and mdpId = " + att_mdInventoryView.getMdpId());
        }
        if (att_mdInventoryView.getMdpsId() != null) {
            sb.append(" and mdpsId = " + att_mdInventoryView.getMdpsId());
        }


        if (att_mdInventoryView.getSearchName() != null && !att_mdInventoryView.getSearchName().equals("")) {
            sb.append(" and (mmfName like '%" + att_mdInventoryView.getSearchName() + "%'" +
                    " or matName like '%" + att_mdInventoryView.getSearchName() + "%'" +
                    " or mmfId  like '%" + att_mdInventoryView.getSearchName() + "%')");
//                    " or mmfId in (select mmfId from MdMaterielFormat where mmfCode like '%" + att_mdInventoryView.getSearchName() + "%'))");
        }
        if (att_mdInventoryView.getBrand() != null && !att_mdInventoryView.getBrand().equals("")) {
            sb.append(" and brand like '%" + att_mdInventoryView.getBrand() + "%'");
        }
//        if (att_mdInventoryView.getCheckPart() != null) {
//            Integer checkPart = att_mdInventoryView.getCheckPart();
//            if (checkPart == 1) {
//                sb.append(" and mdpId in (select mdpId from MdMaterielPartEntity)");
//            } else if (checkPart == 2) {
//                sb.append(" and mdpsId in (select mdpsId from MdMaterielPartSecondEntity)");
//            }
//        }
        if (att_mdInventoryView.getCheckPart() != null && att_mdInventoryView.getCheckParts() != null) {
             sb.append(" and (mdpId in (" + att_mdInventoryView.getCheckPart() + ")");
             sb.append(" or mdpsId in (" + att_mdInventoryView.getCheckParts() + "))");
        } else if (att_mdInventoryView.getCheckPart() != null)
            sb.append(" and mdpId in (" + att_mdInventoryView.getCheckPart() + ")");
        else if (att_mdInventoryView.getCheckParts() != null)
            sb.append(" and mdpsId in (" + att_mdInventoryView.getCheckParts() + ")");

        if (att_mdInventoryView.getExcludeMieIds() != null && !att_mdInventoryView.getExcludeMieIds().equals("")) {
            sb.append(" and mieId not in (" + att_mdInventoryView.getExcludeMieIds() + ")");
        }
        sb.append(" order by editDate desc");
        return this.getHibernateDao().findByPage(sb.toString());
    }

    @Override
    public PagerModel getPagerModelPAWithSave(String mddIds, String mdnIds) throws HSKDBException {
        StringBuffer sb = new StringBuffer("from MdMaterielNormDetailViewEntity where 1=1");
        if (mddIds != null && !mddIds.equals("")) {
            sb.append(" and mddId in (" + mddIds + ")");
        }
        if (mdnIds != null) {
            sb.append(" and mdnIds in (" + mdnIds + ")");
        }
        return this.getHibernateDao().findByPage(sb.toString());
    }

    @Override
    public PagerModel getMdInventoryLogMxList(MdInventoryEnOutLogViewEntity mdInventoryEnOutLogViewEntity, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException {
        String sql = "select * from md_inventory_en_out_log_view where 1=1";// MdInventoryEnOutLogViewEntity where 1=1";
        if (mdInventoryEnOutLogViewEntity.getWiId() != null) {
            sql += " and wiId = " + mdInventoryEnOutLogViewEntity.getWiId();
        }
        if (mdInventoryEnOutLogViewEntity.getCreateDate1() != null && !mdInventoryEnOutLogViewEntity.getCreateDate1().equals("")) {
            sql += " and createDate1 like '%" + mdInventoryEnOutLogViewEntity.getCreateDate1() + "%'";
        }
        if (mdInventoryEnOutLogViewEntity.getBrand() != null && !mdInventoryEnOutLogViewEntity.getBrand().equals("")) {
            sql += " and (brand like '%" + mdInventoryEnOutLogViewEntity.getBrand() + "%'" +
                    " or brand like '%" + mdInventoryEnOutLogViewEntity.getBrand().toUpperCase() + "%')";
        }
//        if (rbaId != null) {
//            sql += " and (enterRba =" + rbaId + " and outRba = " + rbaId +  ")";
//        }
//        if (rbsId != null) {
//            sql += " and (enterRbs =" + rbsId + " and outRbs = " + rbsId +  ")";
//        }
//        if (rbbId != null) {
//            sql += " and (enterRbb =" + rbbId + " and outRbb = " + rbbId +  ")";
//        }
        sql += " GROUP BY cCode,mmfId ORDER BY createDate1 desc";
        List<Map<String, Object>> cList = this.getJdbcDao().query("select count(*) as totalCount from (" + sql + ") as t");
        sql += " limit " + ((SystemContext.getOffset() == 0 ? 1 : SystemContext.getOffset()) - 1) * SystemContext.getPageSize() + "," + SystemContext.getPageSize();
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        PagerModel pm = new PagerModel();
        pm.setRows(list);
        pm.setItems(list);

        Integer count = 0;
        if (cList == null || cList.isEmpty())
            count = 0;
        Map<String, Object> map = cList.get(0);
        if (map == null || map.isEmpty())
            count = 0;
        else
            count = Integer.parseInt(map.get("totalCount").toString());
        pm.setTotalCount(count);
        pm.setTotal(count);
//        if (SystemContext.getOffset(), SystemContext.getPageSize())
        return pm;
    }

    @Override
    public PagerModel getMdInventoryEnOutLogMxList(MdInventoryEnOutLogViewEntity mdInventoryEnOutLogViewEntity, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException {
        String sql = "select c.* from (SELECT a.c_code AS cCode, a.mmf_id AS mmfId1, a.create_date AS createDate1, a.number AS number, a.stype AS stype, a.cur_number AS curNumber," +
                "e.mie_id AS mieId, a.mx_id AS mxId, e.wi_id AS wiId, e.wms_mi_id AS wmsMiId, e.mmf_id AS mmfId, e.price AS price, e.base_price AS basePrice, e.retail_price AS retailPrice," +
                "e.QUANTITY AS QUANTITY, e.split_quantity AS splitQuantity, e.Basic_unit AS BasicUnit, e.unit AS unit, e.base_number AS baseNumber, a.related_code AS relatedCode," +
                "e.purchase_user AS purchaseUser, date_format( e.create_date, '%Y-%m-%d %H:%i:%s' ) AS createDate, date_format( e.edit_date, '%Y-%m-%d %H:%i:%s' ) AS editDate," +
                "e.mat_name AS matName, e.mmf_name AS mmfName, e.mat_name2 AS matName2, e.norm2 AS norm2, e.mat_type AS matType, e.mat_type1 AS matType1," +
                "e.mat_type2 AS matType2, e.mat_type3 AS matType3, e.product_name AS productName, e.brand AS brand, e.Label_info AS LabelInfo," +
                "e.applicant_name AS applicantName, e.state AS state, e.batch_code AS batchCode, e.mdp_id AS mdpId, e.mdn_id AS mdnId, e.mdd_id AS mddId," +
                "e.mdps_id AS mdpsId, e.mdn_code AS mdnCode, e.mdd_name AS mddName, e.mdn_norm AS mdnNorm, e.mdn_node AS mdnNode, a.create_ren as createRen " +
                "FROM md_inventory_extend e LEFT JOIN ((" +
                "SELECT b.wew_mx_id AS mx_id, a.Billcode AS c_code, a.Relation_billCode AS related_code, b.cur_number AS cur_number, b.mmf_id AS mmf_id," +
                "date_format( a.receipt_datetime, '%Y-%m-%d %H:%i:%s' ) AS create_date, b.mat_number AS number, 1 AS stype, b.create_ren as create_ren " +
                "FROM md_enter_warehouse a LEFT JOIN md_enter_warehouse_mx b ON a.wew_id = b.wew_id ) UNION ALL" +
                " ( SELECT d.wow_mx_id AS mx_id, c.wow_code AS c_code, c.RELATED_BILL1 AS related_code, d.cur_number AS cur_number, d.mmf_id AS mmf_id, date_format( c.FINSH_DATE, '%Y-%m-%d %H:%i:%s' ) AS create_date," +
                "d.base_number AS number, 2 AS stype , d.create_ren as create_ren " +
                "FROM md_out_warehouse c LEFT JOIN md_out_warehouse_mx d ON c.wow_id = d.wow_id " +
                ")) a ON a.mmf_id = e.mmf_id) as c where 1=1 ";// MdInventoryEnOutLogViewEntity where 1=1";
        if (mdInventoryEnOutLogViewEntity.getWiId() != null) {
            sql += " and c.wiId = " + mdInventoryEnOutLogViewEntity.getWiId();
        }
        if (mdInventoryEnOutLogViewEntity.getCreateDate1() != null && !mdInventoryEnOutLogViewEntity.getCreateDate1().equals("")) {
            sql += " and c.createDate1 like '%" + mdInventoryEnOutLogViewEntity.getCreateDate1() + "%'";
        }
        if (mdInventoryEnOutLogViewEntity.getBrand() != null && !mdInventoryEnOutLogViewEntity.getBrand().equals("")) {
            sql += " and (c.brand like '%" + mdInventoryEnOutLogViewEntity.getBrand() + "%'" +
                    " or c.brand like '%" + mdInventoryEnOutLogViewEntity.getBrand().toUpperCase() + "%')";
        }
        if (mdInventoryEnOutLogViewEntity.getStype() != null)
            sql += " and c.stype = " + mdInventoryEnOutLogViewEntity.getStype();
        if (mdInventoryEnOutLogViewEntity.getCreateRen() != null && !mdInventoryEnOutLogViewEntity.getCreateRen().equals(""))
            sql += " and c.createRen like '%" + mdInventoryEnOutLogViewEntity.getCreateRen() + "%'";
//        if (rbaId != null) {
//            sql += " and (enterRba =" + rbaId + " and outRba = " + rbaId +  ")";
//        }
//        if (rbsId != null) {
//            sql += " and (enterRbs =" + rbsId + " and outRbs = " + rbsId +  ")";
//        }
//        if (rbbId != null) {
//            sql += " and (enterRbb =" + rbbId + " and outRbb = " + rbbId +  ")";
//        }
        sql += " GROUP BY cCode,mmfId ORDER BY createDate1";
        List<Map<String, Object>> cList = this.getJdbcDao().query("select count(*) as totalCount from (" + sql + ") as t");
        sql += " limit " + ((SystemContext.getOffset() == 0 ? 1 : SystemContext.getOffset()) - 1) * SystemContext.getPageSize() + "," + SystemContext.getPageSize();
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        PagerModel pm = new PagerModel();
        pm.setRows(list);
        pm.setItems(list);

        Integer count = 0;
        if (cList == null || cList.isEmpty())
            count = 0;
        Map<String, Object> map = cList.get(0);
        if (map == null || map.isEmpty())
            count = 0;
        else
            count = Integer.parseInt(map.get("totalCount").toString());
        pm.setTotalCount(count);
        pm.setTotal(count);
//        if (SystemContext.getOffset(), SystemContext.getPageSize())
        return pm;
    }

    @Override
    public PagerModel getMdInventoryMergeLog(MdInventoryMergeLogEntity mdInventoryMergeLogEntity) throws HSKDBException {
        StringBuffer sb = new StringBuffer("from MdInventoryMergeLogEntity where 1=1");

        if (mdInventoryMergeLogEntity.getWiId() != null) {
            sb.append(" and wiId = " + mdInventoryMergeLogEntity.getWiId());
        }
        if (mdInventoryMergeLogEntity.getMdpId() != null) {
            sb.append(" and wmsMiId in (select wmsMiId from MdMaterielInfo where mdpId = " + mdInventoryMergeLogEntity.getMdpId() + ")");
//            sb.append(" and mdpId = " + mdInventoryMergeLogEntity.getMdpId());
        }
        if (mdInventoryMergeLogEntity.getMdpsId() != null) {
            sb.append(" and wmsMiId in (select wmsMiId from MdMaterielInfo where mdpsId = " + mdInventoryMergeLogEntity.getMdpId() + ")");
//            sb.append(" and mdpsId = " + mdInventoryMergeLogEntity.getMdpsId());
        }
        if (mdInventoryMergeLogEntity.getSearchName() != null && !mdInventoryMergeLogEntity.getSearchName().equals("")) {
            sb.append(" and (wmsMiId in (select wmsMiId from MdMaterielInfo where aliasName like '%" + mdInventoryMergeLogEntity.getSearchName() + "%')" +
                    " or mmfId in (select mmfId from MdMaterielFormat where mmfCode like '%" + mdInventoryMergeLogEntity.getSearchName() + "%')" +
                    " or mmfName like '%" + mdInventoryMergeLogEntity.getSearchName() + "%'" +
                    " or matName like '%" + mdInventoryMergeLogEntity.getSearchName() + "%'" +
                    " or brand like '%" + mdInventoryMergeLogEntity.getSearchName() + "%')");
        }

        return this.getHibernateDao().findByPage(sb.toString());
    }

    @Override
    public List<Map<String, Object>> getBrandDistinct(Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType, String checkPart, String checkParts) throws HSKDBException {
        String sql = "select distinct a.brand as brand from md_inventory_extend a left join md_inventory b on a.wi_id = b.wi_id " +
                " left join md_materiel_info c on a.wms_mi_id = c.wms_mi_id " +
                "where 1=1 and a.brand is not null and a.brand <> ''";
        if (rbaId != null) {
            sql += " and b.rba_id = " + rbaId;
        }
        if (rbsId != null) {
            sql += " and b.rbs_id = " + rbsId;
        }
        if (rbbId != null) {
            sql += " and b.rbb_id = " + rbbId;
        }
        if (purchaseType != null && !purchaseType.equals("")) {
            sql += " and b.purchase_type = '" + purchaseType + "'";
        }
        if (checkPart != null)
            sql += " and (c.mdp_id in (" + checkPart + ")";
        if (checkParts != null)
            sql += " or c.mdps_id in (" + checkParts + ")";
        if (checkPart != null)
            sql += ")";
        return this.getJdbcDao().query(sql);
    }

    @Override
    public List<Map<String, Object>> getCheckBrandDistinct(Integer ciId, Integer rbaId, Integer rbsId, Integer rbbId, String purchaseType) throws HSKDBException {
        String sql = "select distinct brand as brand from md_inventory_check_view where 1=1 and brand is not null and brand <> ''";
        if (ciId != null) {
            sql += " and ci_id = " + ciId;
        }
        if (rbaId != null) {
            sql += " and rba_id = " + rbaId;
        }
        if (rbsId != null) {
            sql += " and rbs_id = " + rbsId;
        }
        if (rbbId != null) {
            sql += " and rbb_id = " + rbbId;
        }
        if (purchaseType != null && !purchaseType.equals("")) {
            sql += " and purchase_type = '" + purchaseType + "'";
        }
        sql += " GROUP BY cie_id";
        return this.getJdbcDao().query(sql);
    }

    @Override
    public Map<String, Object> getMdInventoryBaseNumberAndAvgPrice(MdMaterielFormatView mdMaterielFormatView, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException {
        String sql = "select sum(base_number) as base_number, avg_price as avg_price from md_inventory_extend_view where 1=1";
        Integer purchaseType = null;
        if (mdMaterielFormatView.getPurchaseType() != null && !mdMaterielFormatView.getPurchaseType().equals("")) {
            purchaseType = Integer.parseInt(mdMaterielFormatView.getPurchaseType()) - 1;
        }
        if (purchaseType > 0) {
            sql += " and purchase_type = '" + purchaseType.toString() + "'";
            if (rbaId != null)
               sql += " and rba_id = " + rbaId;
            if (rbsId != null)
                sql += " and rbs_id = " + rbsId;
            if (rbbId != null)
                sql += " and rbb_id = " + rbbId;

        }
        if (mdMaterielFormatView.getMmfId() != null) {
            sql += " and (mmf_id = " + mdMaterielFormatView.getMmfId() + ")";// + " or" +
                    //" mmf_id in (select mmf_id from md_inventory_extend where link_mmf_id = " + mdMaterielFormatView.getMmfId() + "))";
        }
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return null;
        return list.get(0);
    }

    @Override
    public List<Map<String, Object>> getMdInventoryBaseNumberAndAvgPriceByList(String purchaseTypes, String mmfIds, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException {
        String sql = "select SUM( base_number -( QUANTITY * ratio )) as split_quantity_sum, sum(base_number) as base_number, sum(quantity) as quantity, ratio as ratio, avg_price as avg_price, mmf_id as mmf_id from md_inventory_extend_view where 1=1";
        if (purchaseTypes != null && !purchaseTypes.equals("")) {
            sql += " and purchase_type in (" + purchaseTypes + ")";
            if (rbaId != null)
                sql += " and rba_id = " + rbaId;
            if (rbsId != null)
                sql += " and rbs_id = " + rbsId;
            if (rbbId != null)
                sql += " and rbb_id = " + rbbId;

        }
        if (mmfIds != null && !mmfIds.equals("")) {
            sql += " and (mmf_id in (" + mmfIds + ") )";//or" +
                   // " mmf_id in (select mmf_id from md_inventory_extend where link_mmf_id in (" + mmfIds + ")))";
        }
        sql += " group by mmf_id";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        return list;
    }

    private String getHql(MdInventoryExtend mdInventoryExtend) {
        StringBuffer sb = new StringBuffer("from MdInventoryExtend where 1=1");

        if (mdInventoryExtend.getMdnNorm() != null && !mdInventoryExtend.getMdnNorm().equals("")) {
            sb.append(" and mdnNorm like '%" + mdInventoryExtend.getMdnNorm() + "%'");
        }
        if (mdInventoryExtend.getMdnCode() != null && !mdInventoryExtend.getMdnCode().equals("")) {
            sb.append(" and mdnCode like '%" + mdInventoryExtend.getMdnCode() + "%'");
        }
        if (mdInventoryExtend.getMddName() != null && !mdInventoryExtend.getMddName().equals("")) {
            sb.append(" and mddName like '%" + mdInventoryExtend.getMddName() + "%'");
        }
        if (mdInventoryExtend.getMdpId() != null) {
            sb.append(" and mdpId = " + mdInventoryExtend.getMdpId());
        }
        if (mdInventoryExtend.getMdpsId() != null) {
            sb.append(" and mdpsId = " + mdInventoryExtend.getMdpsId());
        }
        return sb.toString();
    }

    private String getPAHql(MdInventoryPriceAdjustmentEntity mdInventoryPriceAdjustmentEntity, StringBuffer stringBuffer) {
        StringBuffer sb;
        if (stringBuffer == null) {
            sb =  new StringBuffer("from MdInventoryPriceAdjustmentEntity where 1=1");
        } else {
            sb = stringBuffer;
        }
        if (mdInventoryPriceAdjustmentEntity.getPaiId() != null) {
            sb.append(" and paiId = " + mdInventoryPriceAdjustmentEntity.getPaiId());
        }
        if (mdInventoryPriceAdjustmentEntity.getRbaId() != null) {
            sb.append(" and rbaId = " + mdInventoryPriceAdjustmentEntity.getRbaId());
        }
        if (mdInventoryPriceAdjustmentEntity.getRbsId() != null) {
            sb.append(" and rbsId = " + mdInventoryPriceAdjustmentEntity.getRbsId());
        }
        if (mdInventoryPriceAdjustmentEntity.getRbbId() != null) {
            sb.append(" and rbbId = " + mdInventoryPriceAdjustmentEntity.getRbbId());
        }
        if (mdInventoryPriceAdjustmentEntity.getPaiCode() != null && !mdInventoryPriceAdjustmentEntity.getPaiCode().equals("")) {
            sb.append(" and paiCode like '%" + mdInventoryPriceAdjustmentEntity.getPaiCode() + "%'");
        }
        if (mdInventoryPriceAdjustmentEntity.getPaiType() != null && !mdInventoryPriceAdjustmentEntity.getPaiType().equals("")) {
            sb.append(" and paiType like '" + mdInventoryPriceAdjustmentEntity.getPaiType() + "'");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (mdInventoryPriceAdjustmentEntity.getStartDate() != null && !mdInventoryPriceAdjustmentEntity.getStartDate().equals("")) {
            String date = mdInventoryPriceAdjustmentEntity.getStartDate();
            String start = "";
            String end = "";
            if (date.indexOf("~") >= 0) {
                if (date.split("~")[0].indexOf(" ") >= 0) {
                    start = date.split("~")[0];
                    end = date.split("~")[1];
                } else {
                    start = date.split("~")[0];
                    end = date.split("~")[1];
                }
                sb.append(" and createDate >= '" + start + "'" +
                        " and createDate <= '" + end + "'");
            } else {
                sb.append(" and createDate = '" + mdInventoryPriceAdjustmentEntity.getStartDate() + "'");
            }
        }
        if (mdInventoryPriceAdjustmentEntity.getEndDate() != null) {
            sb.append(" and createDate <= '" + sdf.format(mdInventoryPriceAdjustmentEntity.getEndDate()) + "'");
        }
        if (mdInventoryPriceAdjustmentEntity.getSearchName() != null && !mdInventoryPriceAdjustmentEntity.getSearchName().equals("")) {
            sb.append(" and paiId in (select paiId from MdInventoryExtendPaExEntity where mmfName like '" + mdInventoryPriceAdjustmentEntity.getSearchName() + "%'" +
                    " or mmfCode like '%" + mdInventoryPriceAdjustmentEntity.getSearchName() + "%')");
        }

        sb.append(" order by createDate desc");

        return sb.toString();
    }

    private String getCheckHql(MdCheckInventoryEntity mdCheckInventoryEntity, StringBuffer stringBuffer) {
        StringBuffer sb;
        if (stringBuffer == null) {
            sb = new StringBuffer("from MdInventoryExtend where 1=1");
        } else {
            sb = stringBuffer;
        }
        if (mdCheckInventoryEntity.getCiId() != null) {
            sb.append(" and ciId = " + mdCheckInventoryEntity.getCiId());
        }
        if (mdCheckInventoryEntity.getRbaId() != null) {
            sb.append(" and rbaId = " + mdCheckInventoryEntity.getRbaId());
        }
        if (mdCheckInventoryEntity.getRbsId() != null) {
            sb.append(" and rbsId = " + mdCheckInventoryEntity.getRbsId());
        }
        if (mdCheckInventoryEntity.getRbbId() != null) {
            sb.append(" and rbbId = " + mdCheckInventoryEntity.getRbbId());
        }

        if (mdCheckInventoryEntity.getCheckName() != null && !mdCheckInventoryEntity.getCheckName().equals("")) {
            if (mdCheckInventoryEntity.getLikeTab() != null && mdCheckInventoryEntity.getLikeTab().indexOf("checkName") >= 0) {
				sb.append(" and checkName like '%" + mdCheckInventoryEntity.getCheckName() + "%'");
            } else {
                sb.append(" and checkName = '" + mdCheckInventoryEntity.getCheckName() + "'");
            }
        }

        if (mdCheckInventoryEntity.getCheckCode() != null && !mdCheckInventoryEntity.getCheckCode().equals("")) {
            if (mdCheckInventoryEntity.getLikeTab().indexOf("checkCode") >= 0) {
                sb.append(" and checkCode like '%" + mdCheckInventoryEntity.getCheckCode() + "%'");
            } else {
                sb.append(" and checkCode = '" + mdCheckInventoryEntity.getCheckCode() + "'");
            }
        }
        if (mdCheckInventoryEntity.getCheckType() != null && !mdCheckInventoryEntity.getCheckType().equals("")) {
            sb.append(" and checkType = '" + mdCheckInventoryEntity.getCheckType() + "'");
        }

        if (mdCheckInventoryEntity.getCheckTypeStr() != null && !mdCheckInventoryEntity.getCheckTypeStr().equals("")) {
            sb.append(" and checkType in (" + mdCheckInventoryEntity.getCheckTypeStr() + ")");
        }
        if (mdCheckInventoryEntity.getCheckPart() != null && !mdCheckInventoryEntity.getCheckPart().equals("")) {
            sb.append(" and checkPart = '" + mdCheckInventoryEntity.getCheckPart() + "'");
        }
        if (mdCheckInventoryEntity.getCheckParts() != null && !mdCheckInventoryEntity.getCheckParts().equals("")) {
            sb.append(" and checkParts = '" + mdCheckInventoryEntity.getCheckParts() + "'");
        }
        if (mdCheckInventoryEntity.getRemark() != null && !mdCheckInventoryEntity.getRemark().equals("")) {
            sb.append(" and remark = '" + mdCheckInventoryEntity.getRemark() + "'");
        }
        if (mdCheckInventoryEntity.getCreateRen() != null && !mdCheckInventoryEntity.getCreateRen().equals("")) {
			if (mdCheckInventoryEntity.getLikeTab().indexOf("createRen") >= 0) {
				sb.append(" and createRen like '%" + mdCheckInventoryEntity.getCreateRen() + "%'");
			} else {
				sb.append(" and createRen = '" + mdCheckInventoryEntity.getCreateRen() + "'");
			}
        }
        if (mdCheckInventoryEntity.getEditRen() != null && !mdCheckInventoryEntity.getEditRen().equals("")) {
            sb.append(" and editRen = '" + mdCheckInventoryEntity.getEditRen() + "'");
        }
        if (mdCheckInventoryEntity.getMatName() != null && !mdCheckInventoryEntity.getMatName().equals("")) {
            sb.append(" and matName = '" + mdCheckInventoryEntity.getMatName() + "'");
        }
        if (mdCheckInventoryEntity.getBrand() != null && !mdCheckInventoryEntity.getBrand().equals("")) {
            sb.append(" and brand = '" + mdCheckInventoryEntity.getBrand() + "'");
        }
        if (mdCheckInventoryEntity.getBatchCode() != null && !mdCheckInventoryEntity.getBatchCode().equals("")) {
            sb.append(" and batchCode = '" + mdCheckInventoryEntity.getBatchCode() + "'");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (mdCheckInventoryEntity.getCiId() != null) {
            sb.append(" and createDate = '" + sdf.format(mdCheckInventoryEntity.getCreateDate()) + "'");
        }
        if (mdCheckInventoryEntity.getCiId() != null) {
            sb.append(" and editDate = '" + sdf.format(mdCheckInventoryEntity.getEditDate()) + "'");
        }

        if (mdCheckInventoryEntity.getStartDate() != null && !mdCheckInventoryEntity.getStartDate().equals("")) {
            String date = mdCheckInventoryEntity.getStartDate();
            String start = "";
            String end = "";
            if (date.indexOf("~") >= 0) {
                if (date.split("~")[0].indexOf(" ") >= 0) {
                    start = date.split("~")[0];
                    end = date.split("~")[1];
                } else {
                    start = date.split("~")[0] + " 00:00:00";
                    end = date.split("~")[1] + " 23:59:59";
                }
                sb.append(" and createDate >= '" + start + "'" +
                        " and createDate <= '" + end + "'");
            } else {
                sb.append(" and createDate = '" + mdCheckInventoryEntity.getStartDate() + "'");
            }
        }

        if (mdCheckInventoryEntity.getExcludeCiIds() != null && !mdCheckInventoryEntity.getExcludeCiIds().equals("")) {
            sb.append(" and ciId not in (" + mdCheckInventoryEntity.getExcludeCiIds() + ")");
        }

        sb.append(" order by createDate desc");

        return sb.toString();
    }

    @Override
    public void updateMdInventoryExtendZero(Integer wiId) throws HSKDBException {
        if (wiId == null)
            return;
        String sql = "update md_inventory_extend set quantity = 0, base_number = 0 where wi_id = " + wiId;
        this.getJdbcDao().execute(sql);
    }
}
