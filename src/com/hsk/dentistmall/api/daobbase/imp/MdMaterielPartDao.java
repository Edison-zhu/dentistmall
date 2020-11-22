package com.hsk.dentistmall.api.daobbase.imp;

import com.hsk.dentistmall.api.daobbase.IMdMaterielPartDao;
import com.hsk.dentistmall.api.persistence.MdMaterielPartEntity;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.dto.model.TreeNode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author: yangfeng
 * time: 2020/5/12 8:13
 */
@Repository
public class MdMaterielPartDao extends SupperDao implements IMdMaterielPartDao {
    private String getHql(MdMaterielPartEntity mdMaterielPartEntity) {
        StringBuilder sb = new StringBuilder("from MdMaterielPartEntity where 1=1");
        if (mdMaterielPartEntity.getRbaId() != null)
            sb.append(" and rbaId=" + mdMaterielPartEntity.getRbaId());
        if (mdMaterielPartEntity.getRbsId() != null)
            sb.append(" and rbsId=" + mdMaterielPartEntity.getRbsId());
        if (mdMaterielPartEntity.getRbbId() != null)
            sb.append(" and rbbId=" + mdMaterielPartEntity.getRbbId());
        if (mdMaterielPartEntity.getMdpId() != null) {
            sb.append(" and mdpId = " + mdMaterielPartEntity.getMdpId());
        }

        if (mdMaterielPartEntity.getMdpCode() != null && !mdMaterielPartEntity.getMdpCode().equals("")) {
            sb.append(" and mdpCode = '" + mdMaterielPartEntity.getMdpCode() + "'");
        }

        if (mdMaterielPartEntity.getMdpName() != null && !mdMaterielPartEntity.getMdpName().equals("")) {
            sb.append(" and mdpName = '" + mdMaterielPartEntity.getMdpName() + "'");
        }

        if (mdMaterielPartEntity.getSeq() != null) {
            sb.append(" and seq = " + mdMaterielPartEntity.getSeq());
        }

        if (mdMaterielPartEntity.getNeedShow() != null) {
            sb.append(" and needShow = " + mdMaterielPartEntity.getNeedShow());
        }

        if (mdMaterielPartEntity.getCreateRen() != null && !mdMaterielPartEntity.getCreateRen().equals("")) {
            sb.append(" and createRen = '" + mdMaterielPartEntity.getCreateRen() + "'");
        }

        if (mdMaterielPartEntity.getEditRen() != null && !mdMaterielPartEntity.getEditRen().equals("")) {
            sb.append(" and editRen = '" + mdMaterielPartEntity.getEditRen() + "'");
        }

        if (mdMaterielPartEntity.getHasChild() != null) {
            sb.append(" and hasChild = " + mdMaterielPartEntity.getHasChild());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (mdMaterielPartEntity.getCreateDate() != null) {
            sb.append(" and createDate = '" + sdf.format(mdMaterielPartEntity.getCreateDate()) + "'");
        }
        if (mdMaterielPartEntity.getEditDate() != null) {
            sb.append(" and editDate = '" + sdf.format(mdMaterielPartEntity.getEditDate()) + "'");
        }
//        if (mdMaterielPartEntity.getOrderStr() != null && !mdMaterielPartEntity.getOrderStr().equals("")) {
//            sb.append(" order by " + mdMaterielPartEntity.getOrderStr());
//        } else {
        sb.append(" order by seq");
//            sb.append(" order by mdpId");
//        }

        return sb.toString();
    }

    private String getSql(MdMaterielPartEntity mdMaterielPartEntity, Integer limit, Integer page) {
        StringBuilder sb = new StringBuilder("select * from md_materiel_part where 1=1");
        if (mdMaterielPartEntity.getRbaId() != null)
            sb.append(" and rba_id=" + mdMaterielPartEntity.getRbaId());
        if (mdMaterielPartEntity.getRbsId() != null)
            sb.append(" and rbs_id=" + mdMaterielPartEntity.getRbsId());
        if (mdMaterielPartEntity.getRbbId() != null)
            sb.append(" and rbb_id=" + mdMaterielPartEntity.getRbbId());
        if (mdMaterielPartEntity.getMdpId() != null) {
            sb.append(" and mdp_id = " + mdMaterielPartEntity.getMdpId());
        }

        if (mdMaterielPartEntity.getMdpCode() != null && !mdMaterielPartEntity.getMdpCode().equals("")) {
            sb.append(" and mdp_code = '" + mdMaterielPartEntity.getMdpCode() + "'");
        }

        if (mdMaterielPartEntity.getMdpName() != null && !mdMaterielPartEntity.getMdpName().equals("")) {
            sb.append(" and mdp_name = '" + mdMaterielPartEntity.getMdpName() + "'");
        }

        if (mdMaterielPartEntity.getSeq() != null) {
            sb.append(" and seq = " + mdMaterielPartEntity.getSeq());
        }

        if (mdMaterielPartEntity.getNeedShow() != null) {
            sb.append(" and need_show = " + mdMaterielPartEntity.getNeedShow());
        }

        if (mdMaterielPartEntity.getCreateRen() != null && !mdMaterielPartEntity.getCreateRen().equals("")) {
            sb.append(" and create_ren = '" + mdMaterielPartEntity.getCreateRen() + "'");
        }

        if (mdMaterielPartEntity.getEditRen() != null && !mdMaterielPartEntity.getEditRen().equals("")) {
            sb.append(" and edit_ren = '" + mdMaterielPartEntity.getEditRen() + "'");
        }

        if (mdMaterielPartEntity.getHasChild() != null) {
            sb.append(" and has_child = " + mdMaterielPartEntity.getHasChild());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (mdMaterielPartEntity.getCreateDate() != null) {
            sb.append(" and create_date = '" + sdf.format(mdMaterielPartEntity.getCreateDate()) + "'");
        }
        if (mdMaterielPartEntity.getEditDate() != null) {
            sb.append(" and edit_date = '" + sdf.format(mdMaterielPartEntity.getEditDate()) + "'");
        }
//        if (mdMaterielPartEntity.getOrderStr() != null && !mdMaterielPartEntity.getOrderStr().equals("")) {
//            sb.append(" order by " + mdMaterielPartEntity.getOrderStr());
//        } else {
        sb.append(" order by seq");
//            sb.append(" order by mdp_id");
//        }
        if (limit != null && page != null) {
            sb.append(" limit " + (page - 1) * limit + "," + limit);
        }

        return sb.toString();
    }

    @Override
    public MdMaterielPartEntity findMdMateriel(MdMaterielPartEntity mdMaterielPartEntity) throws HSKDBException {
        String sql = getHql(mdMaterielPartEntity);
        List<MdMaterielPartEntity> list = this.getHibernateTemplate().find(sql);
        if (list.isEmpty()) {
            return null;
        }
        Object obj = list.get(0);
        if (obj == null) {
            return null;
        }
        return (MdMaterielPartEntity) obj;
    }

    @Override
    public void deleteObjects(String mdpIds) throws HSKDBException {
        if (mdpIds == null || mdpIds.equals("")) {
            return;
        }
        String sql = "delete from md_materiel_part where mdp_id in (" + mdpIds + ")";
        this.getJdbcDao().execute(sql);
    }

    @Override
    public MdMaterielPartEntity saveOrUpdateMaterielPart(MdMaterielPartEntity mdMaterielPartEntity) throws HSKDBException {
        this.getHibernateDao().saveOrUpdate(mdMaterielPartEntity);
        return mdMaterielPartEntity;
    }

    @Override
    public PagerModel getMaterielPartPagerModel(MdMaterielPartEntity mdMaterielPartEntity) throws HSKDBException {
        String hql = getHql(mdMaterielPartEntity);
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public List<Map<String, Object>> getMaterielPartMapList(MdMaterielPartEntity mdMaterielPartEntity, Integer limit, Integer page) throws HSKDBException {
        String sql = getSql(mdMaterielPartEntity, limit, page);
        return this.getJdbcDao().query(sql);
    }

    @Override
    public List findSitePart(Integer mdpId, final Integer mdpsId, Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException {
        String sql = "SELECT t.mdp_id id,t.mdp_name 'text', t.part_code partCode,t.mdp_code as mdpCode FROM md_materiel_part t WHERE 1=1 ";
        if (rbaId != null) {
            sql += " and rba_id = " + rbaId;
        }
        if (rbsId != null) {
            sql += " and rbs_id = " + rbsId;
        }
        if (rbbId != null) {
            sql += " and rbb_id = " + rbbId;
        }

        if (mdpId != null) {
            sql += " and mdp_id = " + mdpId;
        }
        sql += " order by seq";
        List<TreeNode> resultTree = this.getJdbcTemplate().query(sql, new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                TreeNode node = new TreeNode();
                String code = rs.getString("partCode") == null ? "" : (rs.getString("partCode") + " ");
                node.setId(rs.getString("id"));
                node.setText(code + rs.getString("text"));
                node.setName(code + rs.getString("text"));
                try {
                    //栏目标签
                    int count = getMaterielPartCount(Integer.parseInt(node.getId()));
                    List tagsList = new ArrayList();
                    tagsList.add(count);
                    node.setTags(tagsList);

                    List<TreeNode> childs = findSecondSiteTree(Integer.parseInt(node.getId()), mdpsId);
                    if (childs != null && childs.size() > 0) {
                        node.setChildren(childs);
                        node.setNodes(childs);
                        node.setIsParent("true");
                    }
                } catch (HSKDBException e) {
                    e.printStackTrace();
                }

                return node;
            }
        });


        return resultTree;
    }

    @Override
    public Integer getMaterielPartCount(Integer mdpId) throws HSKDBException {
        String sql = " SELECT count(*) FROM md_materiel_part t WHERE 1=1 and t.mdp_id= " + mdpId;
        int rows = this.getJdbcTemplate().queryForInt(sql);
        return rows;
    }

    @Override
    public Integer getMaterielPartSecondCount(Integer mdpsId) throws HSKDBException {
        String sql = " SELECT count(*) FROM md_materiel_part_second t WHERE 1=1 and t.mdps_id= " + mdpsId;
        int rows = this.getJdbcTemplate().queryForInt(sql);
        return rows;
    }

    @Override
    public List findSecondSiteTree(Integer mdpId, Integer mdpsId) throws HSKDBException {
        String sql = "SELECT t.mdps_id id,t.mdps_name 'text',t.mdps_code as mdpCode, t.second_part_code secondPartCode FROM md_materiel_part_second t WHERE 1=1 ";
        if (mdpId != null) {
            sql += " and mdp_id = " + mdpId;
        }
        if (mdpsId != null) {
            sql += " and mdps_id = " + mdpsId;
        }
        sql += " order by seq";
        List<TreeNode> resultTree = this.getJdbcTemplate().query(sql, new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                TreeNode node = new TreeNode();
                String code = rs.getString("secondPartCode") == null ? "" : (rs.getString("secondPartCode") + " ");
                node.setId(rs.getString("id"));
                node.setText(code + rs.getString("text"));
                node.setName(code + rs.getString("text"));
                try {
                    //栏目标签
                    int count = getMaterielPartSecondCount(Integer.parseInt(node.getId()));
                    List tagsList = new ArrayList();
                    tagsList.add(count);
                    node.setTags(tagsList);
                } catch (HSKDBException e) {
                    e.printStackTrace();
                }

                return node;
            }
        });
        return resultTree;
    }

    @Override
    public void saveOrUpdateMaterielPartSeq(Integer mdpIdBefore, Integer seqBefore, Integer mdpIdAfter, Integer seqAfter, MdMaterielPartEntity mdMaterielPartEntity) throws HSKDBException {
        List<String> sqlList = new ArrayList<String>();
        String limitStr = "";
        if (mdMaterielPartEntity.getRbaId() != null) {
            limitStr += " and rba_id = " + mdMaterielPartEntity.getRbaId() ;
        }
        if (mdMaterielPartEntity.getRbsId() != null) {
            limitStr += " and rbs_id = " + mdMaterielPartEntity.getRbsId();
        }
        if (mdMaterielPartEntity.getRbbId() != null) {
            limitStr += " and rbb_id = " + mdMaterielPartEntity.getRbbId();
        }
        sqlList.add("update md_materiel_part set seq = " + seqAfter + " where mdp_id = " + (mdpIdBefore) + limitStr);
        sqlList.add("update md_materiel_part set seq = " + seqBefore + " where mdp_id = " + (mdpIdAfter) + limitStr);
        this.getJdbcDao().batchUpdate(sqlList);
    }

    @Override
    public void saveOrUpdateMaterielPartSeqBatch(Integer mdpIdBefore, Integer seqBefore, Integer mdpIdAfter, Integer seqAfter, MdMaterielPartEntity mdMaterielPartEntity) throws HSKDBException {
        List<String> sqlList = new ArrayList<String>();
        String limitStr = "";
        if (mdMaterielPartEntity.getRbaId() != null) {
            limitStr += " and rba_id = " + mdMaterielPartEntity.getRbaId() ;
        }
        if (mdMaterielPartEntity.getRbsId() != null) {
            limitStr += " and rbs_id = " + mdMaterielPartEntity.getRbsId();
        }
        if (mdMaterielPartEntity.getRbbId() != null) {
            limitStr += " and rbb_id = " + mdMaterielPartEntity.getRbbId();
        }
        sqlList.add("update md_materiel_part set seq = " + seqAfter + " where mdp_id = " + (mdpIdBefore) + limitStr);

        sqlList.add("update md_materiel_part set seq = seq " + (seqBefore > seqAfter ? "+ 1" : "- 1") +
                " where " + (seqBefore > seqAfter ? "seq >= " + seqAfter + " and seq < " + seqBefore : "seq <= " + seqAfter + " and seq > " + seqBefore) +
                " and mdp_id != " + mdpIdBefore + limitStr);
        this.getJdbcDao().batchUpdate(sqlList);
    }

    @Override
    public Map<String, Object> finMdMaterielPartByUpDown(Integer upDown, MdMaterielPartEntity mdMaterielPartEntity, Integer length) throws HSKDBException {
        String sql;
        String limitStr = "";
        if (mdMaterielPartEntity.getRbaId() != null) {
            limitStr += " and rba_id = " + mdMaterielPartEntity.getRbaId() ;
        }
        if (mdMaterielPartEntity.getRbsId() != null) {
            limitStr += " and rbs_id = " + mdMaterielPartEntity.getRbsId();
        }
        if (mdMaterielPartEntity.getRbbId() != null) {
            limitStr += " and rbb_id = " + mdMaterielPartEntity.getRbbId();
        }
        switch (upDown) {
            case 0: //向上seq变大
                sql = "select mdp_id, seq from md_materiel_part where seq < " + mdMaterielPartEntity.getSeq() + limitStr + " order by seq desc limit 1";
                break;
            case 1: //向下seq变小
                sql = "select mdp_id, seq from md_materiel_part where seq > " + mdMaterielPartEntity.getSeq() + limitStr + " order by seq limit 1";
                break;
            case 2: //置顶seq变大
                sql = "select mdp_id, seq from md_materiel_part where seq < " + mdMaterielPartEntity.getSeq() + limitStr + " order by seq limit " + (length == null ? 1 : length);
                break;
            case 3: //置底seq变小
                sql = "select mdp_id, seq from md_materiel_part where seq > " + mdMaterielPartEntity.getSeq() + limitStr + " order by seq desc limit " + (length == null ? 1 : length);
                break;
            default:
                sql = "select mdp_id, seq from md_materiel_part where mdp_id = " + mdMaterielPartEntity.getSeq() + limitStr;
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
    public Integer getMaterielPartMaxSeq(MdMaterielPartEntity mdMaterielPartEntity) throws HSKDBException {
        String sql = "select max(seq) seq from md_materiel_part where 1=1";
        if (mdMaterielPartEntity.getRbaId() != null) {
            sql += " and rba_id = " + mdMaterielPartEntity.getRbaId() ;
        }
        if (mdMaterielPartEntity.getRbsId() != null) {
            sql += " and rbs_id = " + mdMaterielPartEntity.getRbsId();
        }
        if (mdMaterielPartEntity.getRbbId() != null) {
            sql += " and rbb_id = " + mdMaterielPartEntity.getRbbId();
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
    public Integer getMaterielPartSecondByMdpIdCount(Integer mdpId) {
        if (mdpId == null) {
            return 0;
        }
        String sql = " SELECT count(*) FROM md_materiel_part_second t WHERE 1=1 and t.mdp_id= " + mdpId;
        int rows = this.getJdbcTemplate().queryForInt(sql);
        return rows;
    }

    @Override
    public PagerModel getMaterielNormDetailPagerModel(Integer mdpId, Integer mdpsId, String searchName) throws HSKDBException {
        StringBuffer sb = new StringBuffer("from MdMaterielNormDetailViewEntity where 1=1");
        if (mdpId != null) {
            sb.append(" and mdpId = " + mdpId);
        }
        if (mdpsId != null) {
            sb.append(" and mdpsId = " + mdpsId);
        }
        if (searchName != null && !searchName.equals("")) {
            sb.append(" and (mdnCode like '%" + searchName + "%'" +
                    " or mddName like '%" + searchName+ "%')");
        }
        return this.getHibernateDao().findByPage(sb.toString());
    }

    @Override
    public List getMdMaterielPartList(Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException {
        String sql = "SELECT t.mdp_id,t.mdp_name FROM md_materiel_part t WHERE 1=1 ";
        if (rbaId != null) {
            sql += " and rba_id = " + rbaId;
        }
        if (rbsId != null) {
            sql += " and rbs_id = " + rbsId;
        }
        if (rbbId != null) {
            sql += " and rbb_id = " + rbbId;
        }

        List<Map<String, Object>> resultTree = this.getJdbcTemplate().query(sql, new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<String, Object> node = new HashMap<>();

                Integer mdpId = Integer.parseInt(rs.getString("mdp_id"));
                List l = getMdMaterielSecondList(mdpId);

                node.put("value", rs.getString("mdp_id"));
                node.put("name", rs.getString("mdp_name"));
                node.put("group", 1);
                node.put("children", l);
                return node;
            }
        });
//        String sql1 = "select t.mdps_id, t.mdps_name, t.mdp_id from md_materiel_part_second t where t.mdp_id in (select mdp_id from md_materiel_part where 1=1";
//        if (rbaId != null) {
//            sql1 += " and rba_id = " + rbaId;
//        }
//        if (rbsId != null) {
//            sql1 += " and rbs_id = " + rbsId;
//        }
//        if (rbbId != null) {
//            sql1 += " and rbb_id = " + rbbId;
//        }
//        sql1 += ")";
//        List<Map<String, String>> resultTree1 = this.getJdbcTemplate().query(sql1, new RowMapper() {
//            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Map<String, String> node = new HashMap<>();
//                node.put("id", rs.getString("mdps_id"));
//                node.put("name", rs.getString("mdps_name"));
//                node.put("mdp_id", rs.getString("mdp_id"));
//                return node;
//            }
//        });
//        resultTree.addAll(resultTree1);
        return resultTree;
    }

    private List getMdMaterielSecondList(final Integer mdpId) {
        String sql1 = "select t.mdps_id, t.mdps_name, t.mdp_id from md_materiel_part_second t where t.mdp_id = " + mdpId;
        List<Map<String, Object>> resultTree1 = this.getJdbcTemplate().query(sql1, new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<String, Object> node = new HashMap<>();
                node.put("value", rs.getString("mdps_id") + "_" + mdpId);
                node.put("name", rs.getString("mdps_name"));
                node.put("group", 2);
//                node.put("mdp_id", rs.getString("mdp_id"));
                return node;
            }
        });
        return resultTree1;
    }

    @Override
    public Integer getMaterielInfoCount(String mdpIds) throws HSKDBException {
        if (mdpIds == null || mdpIds.equals("")) {
            return 0;
        }
        String sql = " SELECT count(*) FROM md_materiel_info t WHERE 1=1 and t.mdp_id in (" + mdpIds + ")";
        int rows = this.getJdbcTemplate().queryForInt(sql);
        return rows;
    }

    @Override
    public Integer findMdMaterielIdByName(String mdpName) throws HSKDBException {
        if (mdpName == null || mdpName.equals(""))
            return null;
        String sql = "select mdp_id as mdpId from md_materiel_part where mdp_name = '" + mdpName + "'";
        List<Map<String, String>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return null;
        Map<String, String> map = list.get(0);
        if (map == null || map.isEmpty())
            return null;
        return Integer.parseInt(map.get("mdpId"));
    }

    @Override
    public Map<String, Object> getMdMaterielPartLatest(Integer rbaId, Integer rbsId, Integer rbbId) throws HSKDBException {
        String sql = "select * from md_materiel_part where 1=1";
        if (rbaId != null) {
            sql += " and rba_id = " + rbaId;
        }
        if (rbsId != null) {
            sql += " and rbs_id = " + rbsId;
        }
        if (rbbId != null) {
            sql += " and rbb_id = " + rbbId;
        }
        sql += " order by mdp_id desc limit 1";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return null;
        return list.get(0);
    }
}
