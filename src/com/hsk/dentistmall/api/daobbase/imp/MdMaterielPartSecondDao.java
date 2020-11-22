package com.hsk.dentistmall.api.daobbase.imp;

import com.hsk.dentistmall.api.daobbase.IMdMaterielPartSecondDao;
import com.hsk.dentistmall.api.persistence.MdMaterielPartEntity;
import com.hsk.dentistmall.api.persistence.MdMaterielPartSecondEntity;
import com.hsk.dentistmall.api.persistence.MdMaterielPartSecondEntity;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/5/12 9:32
 */
@Repository
public class MdMaterielPartSecondDao extends SupperDao implements IMdMaterielPartSecondDao {
    private String getHql(MdMaterielPartSecondEntity mdMaterielPartSecondEntity) {
        StringBuilder sb = new StringBuilder("from MdMaterielPartSecondEntity where 1=1");
        if (mdMaterielPartSecondEntity.getMdpsId() != null) {
            sb.append(" and mdpsId = " + mdMaterielPartSecondEntity.getMdpsId());
        }
        if (mdMaterielPartSecondEntity.getMdpId() != null) {
            sb.append(" and mdpId = " + mdMaterielPartSecondEntity.getMdpId());
        }
        if (mdMaterielPartSecondEntity.getMdpsCode() != null && !mdMaterielPartSecondEntity.getMdpsCode().equals("")) {
            sb.append(" and mdpsCode = '" + mdMaterielPartSecondEntity.getMdpsCode() + "'");
        }

        if (mdMaterielPartSecondEntity.getMdpsName() != null && !mdMaterielPartSecondEntity.getMdpsName().equals("")) {
            sb.append(" and mdpsName = '" + mdMaterielPartSecondEntity.getMdpsName() + "'");
        }

        if (mdMaterielPartSecondEntity.getSeq() != null) {
            sb.append(" and seq = " + mdMaterielPartSecondEntity.getSeq());
        }

        if (mdMaterielPartSecondEntity.getNeedShow() != null) {
            sb.append(" and needShow = " + mdMaterielPartSecondEntity.getNeedShow());
        }

        if (mdMaterielPartSecondEntity.getCreateRen() != null && !mdMaterielPartSecondEntity.getCreateRen().equals("")) {
            sb.append(" and createRen = '" + mdMaterielPartSecondEntity.getCreateRen() + "'");
        }

        if (mdMaterielPartSecondEntity.getEditRen() != null && !mdMaterielPartSecondEntity.getEditRen().equals("")) {
            sb.append(" and editRen = '" + mdMaterielPartSecondEntity.getEditRen() + "'");
        }

        if (mdMaterielPartSecondEntity.getHasChild() != null) {
            sb.append(" and hasChild = " + mdMaterielPartSecondEntity.getHasChild());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (mdMaterielPartSecondEntity.getCreateDate() != null) {
            sb.append(" and createDate = '" + sdf.format(mdMaterielPartSecondEntity.getCreateDate()) + "'");
        }
        if (mdMaterielPartSecondEntity.getEditDate() != null) {
            sb.append(" and editDate = '" + sdf.format(mdMaterielPartSecondEntity.getEditDate()) + "'");
        }
//        if (mdMaterielPartSecondEntity.getOrderStr() != null && !mdMaterielPartSecondEntity.getOrderStr().equals("")) {
//            sb.append(" order by " + mdMaterielPartSecondEntity.getOrderStr());
//        } else {
        sb.append(" order by seq");
//            sb.append(" order by mdpsId");
//        }
        return sb.toString();
    }

    private String getSql(MdMaterielPartSecondEntity mdMaterielPartSecondEntity, Integer limit, Integer page) {
        StringBuilder sb = new StringBuilder("select * from md_materiel_part_second where 1=1");
        if (mdMaterielPartSecondEntity.getMdpsId() != null) {
            sb.append(" and mdps_id = " + mdMaterielPartSecondEntity.getMdpsId());
        }
        if (mdMaterielPartSecondEntity.getMdpId() != null) {
            sb.append(" and mdp_id = " + mdMaterielPartSecondEntity.getMdpId());
        }
        if (mdMaterielPartSecondEntity.getMdpsCode() != null && !mdMaterielPartSecondEntity.getMdpsCode().equals("")) {
            sb.append(" and mdps_code = '" + mdMaterielPartSecondEntity.getMdpsCode() + "'");
        }

        if (mdMaterielPartSecondEntity.getMdpsName() != null && !mdMaterielPartSecondEntity.getMdpsName().equals("")) {
            sb.append(" and mdps_name = '" + mdMaterielPartSecondEntity.getMdpsName() + "'");
        }

        if (mdMaterielPartSecondEntity.getSeq() != null) {
            sb.append(" and seq = " + mdMaterielPartSecondEntity.getSeq());
        }

        if (mdMaterielPartSecondEntity.getNeedShow() != null) {
            sb.append(" and need_show = " + mdMaterielPartSecondEntity.getNeedShow());
        }

        if (mdMaterielPartSecondEntity.getCreateRen() != null && !mdMaterielPartSecondEntity.getCreateRen().equals("")) {
            sb.append(" and create_ren = '" + mdMaterielPartSecondEntity.getCreateRen() + "'");
        }

        if (mdMaterielPartSecondEntity.getEditRen() != null && !mdMaterielPartSecondEntity.getEditRen().equals("")) {
            sb.append(" and edit_ren = '" + mdMaterielPartSecondEntity.getEditRen() + "'");
        }

        if (mdMaterielPartSecondEntity.getHasChild() != null) {
            sb.append(" and has_child = " + mdMaterielPartSecondEntity.getHasChild());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (mdMaterielPartSecondEntity.getCreateDate() != null) {
            sb.append(" and create_date = '" + sdf.format(mdMaterielPartSecondEntity.getCreateDate()) + "'");
        }
        if (mdMaterielPartSecondEntity.getEditDate() != null) {
            sb.append(" and edit_date = '" + sdf.format(mdMaterielPartSecondEntity.getEditDate()) + "'");
        }
//        if (mdMaterielPartSecondEntity.getOrderStr() != null && !mdMaterielPartSecondEntity.getOrderStr().equals("")) {
//            sb.append(" order by " + mdMaterielPartSecondEntity.getOrderStr());
//        } else {
        sb.append(" order by seq");
//            sb.append(" order by mdps_id");
//        }
        if (limit != null && page != null) {
            sb.append(" limit " + (page - 1) * limit + "," + limit);
        }
        return sb.toString();
    }

    @Override
    public MdMaterielPartSecondEntity findMdMateriel(MdMaterielPartSecondEntity mdMaterielPartSecondEntity) throws HSKDBException {
        String sql = getHql(mdMaterielPartSecondEntity);
        List<MdMaterielPartSecondEntity> list = this.getHibernateTemplate().find(sql);
        if (list.isEmpty()) {
            return null;
        }
        Object obj = list.get(0);
        if (obj == null) {
            return null;
        }
        return (MdMaterielPartSecondEntity) obj;
    }

    @Override
    public void deleteObjects(Integer mdpId, String mdpIds) throws HSKDBException {
//        if (mdpId == null || mdpId.equals("")) {
//            return;
//        }
        if (mdpIds == null || mdpIds.equals("")) {
            return;
        }
        String sql = "delete from md_materiel_part_second where mdps_id in (" + mdpIds + ")";
        if (mdpId != null) {
            sql += " and mdp_id = " + mdpId;
        }
        this.getJdbcDao().execute(sql);
    }

    @Override
    public MdMaterielPartSecondEntity saveOrUpdateMaterielPart(MdMaterielPartSecondEntity mdMaterielPartSecondEntity) throws HSKDBException {
        this.getHibernateDao().saveOrUpdate(mdMaterielPartSecondEntity);
        return mdMaterielPartSecondEntity;
    }

    @Override
    public PagerModel getMaterielPartPagerModel(MdMaterielPartSecondEntity mdMaterielPartSecondEntity) throws HSKDBException {
        String hql = getHql(mdMaterielPartSecondEntity);
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public List<Map<String, Object>> getMaterielPartMapList(MdMaterielPartSecondEntity mdMaterielPartSecondEntity, Integer limit, Integer page) throws HSKDBException {
        String sql = getSql(mdMaterielPartSecondEntity, limit, page);
        return this.getJdbcDao().query(sql);
    }

    @Override
    public void saveOrUpdateMaterielPartSeq(Integer mdpsIdBefore, Integer seqBefore, Integer mdpsIdAfter, Integer seqAfte) throws HSKDBException {
        List<String> sqlList = new ArrayList<String>();
        sqlList.add("update md_materiel_part_second set seq = " + seqAfte + " where mdps_id = " + (mdpsIdBefore));
        sqlList.add("update md_materiel_part_second set seq = " + seqBefore + " where mdps_id = " + (mdpsIdAfter));
        this.getJdbcDao().batchUpdate(sqlList);
    }

    @Override
    public void saveOrUpdateMaterielPartSeqBatch(Integer mdpsIdBefore, Integer seqBefore, Integer mdpsIdAfter, Integer seqAfter) throws HSKDBException {
        List<String> sqlList = new ArrayList<String>();
        sqlList.add("update md_materiel_part_second set seq = " + seqAfter + " where mdps_id = " + (mdpsIdBefore));

        sqlList.add("update md_materiel_part_second set seq = seq " + (seqBefore > seqAfter ? "+ 1" : "- 1") +
                " where " + (seqBefore > seqAfter ? "seq >= " + seqAfter + " and seq < " + seqBefore : "seq <= " + seqAfter + " and seq > " + seqBefore) +
                " and mdps_id != " + mdpsIdBefore);
        this.getJdbcDao().batchUpdate(sqlList);
    }

    @Override
    public Integer getMaterielPartMaxSeq(Integer mdpId) throws HSKDBException {
        String sql = "select max(seq) seq from md_materiel_part_second";
        if (mdpId != null) {
            sql += " where mdp_id = " + mdpId;
        }
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list.isEmpty()) {
            return -1;
        }
        Map<String, Object> map = list.get(0);
        if (map.isEmpty()) {
            return -1;
        }
        if (map.get("seq") == null)
            return -1;
        Integer maxSeq = Integer.parseInt(map.get("seq").toString());
        return maxSeq;
    }

    @Override
    public Map<String, Object> finMdMaterielPartSecondByUpDown(Integer upDown, Integer seqBefore, Integer length) throws HSKDBException {
        String sql;
        switch (upDown) {
            case 0: //向上seq变大
                sql = "select mdps_id, seq from md_materiel_part_second where seq < " + seqBefore + " order by seq desc limit 1";
                break;
            case 1: //向下seq变小
                sql = "select mdps_id, seq from md_materiel_part_second where seq > " + seqBefore + " order by seq limit 1";
                break;
            case 2: //置顶seq变大
                sql = "select mdps_id, seq from md_materiel_part_second where seq < " + seqBefore + " order by seq limit " + (length == null ? 1 : length);
                break;
            case 3: //置底seq变小
                sql = "select mdps_id, seq from md_materiel_part_second where seq > " + seqBefore + " order by seq desc limit " + (length == null ? 1 : length);
                break;
            default:
                sql = "select mdps_id, seq from md_materiel_part_second where seq = " + seqBefore;
        }
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list.isEmpty()) {
            return null;
        }
        Map<String, Object> map = list.get(0);
        if (map.isEmpty()) {
            return null;
        }
        return map;
    }

    @Override
    public Integer getMaterielInfoCount(Integer mdpId, String mdpsIds) throws HSKDBException {
        if (mdpId == null || mdpsIds == null || mdpsIds.equals("")) {
            return 0;
        }
        String sql = " SELECT count(*) FROM md_materiel_info t WHERE 1=1 and t.mdp_id= " + mdpId + " and mdps_id in (" + mdpsIds + ")";
        int rows = this.getJdbcTemplate().queryForInt(sql);
        return rows;
    }

    @Override
    public List<Map<String, Object>> getMaterielPartMapListByMdpsId(String checkParts) throws HSKDBException {
        String sql = "select mdp_id, mdps_id from md_materiel_part_second where 1=1";
        if (checkParts != null && !checkParts.equals(""))
            sql += " and mdps_id in (" + checkParts + ")";

        return this.getJdbcDao().query(sql);
    }

    @Override
    public Integer findMdMaterielIdByName(String mdpsName) throws HSKDBException {
        if (mdpsName == null || mdpsName.equals(""))
            return null;
        String sql = "select mdps_id as mdpsId from md_materiel_part_second where mdps_name = '" + mdpsName + "'";
        List<Map<String, String>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return null;
        Map<String, String> map = list.get(0);
        if (map == null || map.isEmpty())
            return null;
        return Integer.parseInt(map.get("mdpsId"));
    }

    @Override
    public Integer getMaterielPartSecondCount(Integer mdpId) throws HSKDBException {
        String sql = "from MdMaterielPartSecondEntity where 1=1";
        if (mdpId != null)
            sql += " and mdpId = " + mdpId;
        List<MdMaterielPartSecondEntity> list = this.getHibernateTemplate().find(sql);
        return list.size();
    }

    @Override
    public Map<String, Object> getMdMaterielPartSecondLatest(Integer mdpId, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException {
        String sql = "select * from md_materiel_part_second where 1=1";
        if (mdpId != null)
            sql += " and mdp_id = " + mdpId;
        sql += " order by mdps_id desc limit 1";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return null;
        return list.get(0);
    }
}
