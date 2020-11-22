package com.hsk.xframe.api.daobbase.imp;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.daobbase.ISysUnitParamDao;
import com.hsk.xframe.api.dto.model.TreeNode;
import com.hsk.xframe.api.persistence.SysUnitParamEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/5/9 16:28
 */
@Repository
public class SysUnitParamDao extends SupperDao implements ISysUnitParamDao {

    private String getHql(SysUnitParamEntity sysUnitParamEntity, StringBuilder sql) {
        StringBuilder sb;
        if (sql == null) {
            sb = new StringBuilder("from SysUnitParamEntity where 1=1");
        } else {
            sb = sql;
        }

        if (sysUnitParamEntity.getUpId() != null) {
            sb.append(" and upId = " + sysUnitParamEntity.getUpId());
        }
        if (sysUnitParamEntity.getSupId() != null) {
            sb.append(" and supId = " + sysUnitParamEntity.getSupId());
        }
        if (sysUnitParamEntity.getNeedShow() != null) {
            sb.append(" and needShow = " + sysUnitParamEntity.getNeedShow());
        }
        if (sysUnitParamEntity.getUnitName() != null && !sysUnitParamEntity.getUnitName().equals("")) {
            sb.append(" and unitName = '" + sysUnitParamEntity.getUnitName() + "'");
        }
        if (sysUnitParamEntity.getBelongType() != null && !sysUnitParamEntity.getBelongType().equals("")) {
            String belongType = sysUnitParamEntity.getBelongType();
            String[] belongTypes = belongType.split(",");
            if (belongTypes.length > 0) {
                sb.append(" and belongType in (");
                for (String bt : belongTypes) {
                    sb.append("'" + bt + "',");
                }
                sb = sb.delete(sb.length() - 1, sb.length());
                sb.append(")");
            } else {
                sb.append(" and belongType = '" + sysUnitParamEntity.getBelongType() + "'");
            }
        }
        if (sysUnitParamEntity.getCreateRen() != null && !sysUnitParamEntity.getCreateRen().equals("")) {
            sb.append(" and createRen = '" + sysUnitParamEntity.getCreateRen() + "'");
        }
        if (sysUnitParamEntity.getSuiId() != null) {
            sb.append(" and suiId = " + sysUnitParamEntity.getSuiId());
        }
        if (sysUnitParamEntity.getEditeRen() != null && !sysUnitParamEntity.getEditeRen().equals("")) {
            sb.append(" and editRen = '" + sysUnitParamEntity.getEditeRen() + "'");
        }
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd mm:HH:ss");
        if (sysUnitParamEntity.getCreateDate() != null) {
            sb.append(" and createDate = " + sd.format(sysUnitParamEntity.getCreateDate()));
        }
        if (sysUnitParamEntity.getEditeDate() != null) {
            sb.append(" and editDate = " + sd.format(sysUnitParamEntity.getEditeDate()));
        }
        if (sysUnitParamEntity.getState() != null) {
            sb.append(" and state = " + sysUnitParamEntity.getState());
        }
        if (sysUnitParamEntity.getUnitType() != null && !sysUnitParamEntity.getUnitType().equals("")) {
            sb.append(" and unitType = '" + sysUnitParamEntity.getUnitType() + "'");
        }
        sb.append(" order by upId");
        return sb.toString();
    }

    @Override
    public void deleteSysUnitParamById(Integer upId) throws HSKDBException {
        String sql = "delete from sys_unit_param where up_id=" + upId;
        this.getJdbcDao().execute(sql);
    }

    @Override
    public void deleteSysUnitParamByIds(String upIds) throws HSKDBException {
        String sql = "delete from sys_unit_param where up_id in (" + upIds + ")";
        this.getJdbcDao().execute(sql);
    }

    @Override
    public PagerModel getPagetModelByObject(SysUnitParamEntity sysUnitParamEntity) throws HSKDBException {
        String hql = this.getHql(sysUnitParamEntity, new StringBuilder(" from SysUnitParamEntity where supId is not NULL"));
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public PagerModel getSysUnitParamParentPagerModel(SysUnitParamEntity sysUnitParamEntity) throws HSKDBException {
        String hql = this.getHql(sysUnitParamEntity, new StringBuilder(" from SysUnitParamEntity where supId is NULL"));
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public SysUnitParamEntity saveOrUpdateSysUnitParam(SysUnitParamEntity sysUnitParamEntity) throws HSKDBException {
        this.getHibernateTemplate().saveOrUpdate(sysUnitParamEntity);
        return sysUnitParamEntity;
    }

    @Override
    public SysUnitParamEntity findSysUnitParam(Integer upId, Integer supId) throws HSKDBException {
        if (upId == null) {
            return null;
        }
        SysUnitParamEntity sysUnitParamEntity = new SysUnitParamEntity();
        sysUnitParamEntity.setUpId(upId);
        if (supId != null) {
            sysUnitParamEntity.setSupId(supId);
        }
        Object obj = this.getOne(sysUnitParamEntity);
        if (obj == null) {
            return null;
        }
        return (SysUnitParamEntity) obj;
    }

    @Override
    public SysUnitParamEntity findSysUnitParamParentByName(SysUnitParamEntity sysUnitParamEntity) throws HSKDBException {
        String sql = "from SysUnitParamEntity where supId is null";
        if (sysUnitParamEntity.getUpId() != null) {
            sql += " and upId = " + sysUnitParamEntity.getUpId();
        }
        if (sysUnitParamEntity.getUnitName() != null && !sysUnitParamEntity.getUnitName().equals("")) {
            sql += " and unitName = '" + sysUnitParamEntity.getUnitName() + "'";
        }
        PagerModel pm = this.getHibernateDao().findByPage(sql);
        List<SysUnitParamEntity> list = pm.getItems();
        if (list.isEmpty()) {
            return null;
        }
        return sysUnitParamEntity;
    }

    @Override
    public List<Map<String, Object>> getUnitParamBelongTypeByIdOrAll(Integer upId) throws HSKDBException {
        String sql = "select distinct unit_name from sys_unit_param where 1=1";
        if (upId != null) {
            sql += " and up_id = (select sup_id from sys_unit_param where up_id = " + upId + ")";
        }
        return this.getJdbcDao().query(sql);
    }

    @Override
    public SysUnitParamEntity findSysUnitParamByName(SysUnitParamEntity sysUnitParamEntity) throws HSKDBException {
        String sql = "from SysUnitParamEntity where 1=1";
        if (sysUnitParamEntity.getUpId() != null) {
            sql += " and upId = " + sysUnitParamEntity.getUpId();
        }
        if (sysUnitParamEntity.getUnitName() != null && !sysUnitParamEntity.getUnitName().equals("")) {
            sql += " and unitName = '" + sysUnitParamEntity.getUnitName() + "'";
        }
        if (sysUnitParamEntity.getUnitCode() != null && !sysUnitParamEntity.getUnitCode().equals("")) {
            sql += " and unitCode = '" + sysUnitParamEntity.getUnitCode() + "'";
        }
        if (sysUnitParamEntity.getSupId() != null) {
            sql += " and supId = " + sysUnitParamEntity.getSupId();
        }
        PagerModel pm = this.getHibernateDao().findByPage(sql);
        List<SysUnitParamEntity> list = pm.getItems();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SysUnitParamEntity> findSysUnitParamChild(Integer upId) throws HSKDBException {
        SysUnitParamEntity sysUnitParamEntity = new SysUnitParamEntity();
        sysUnitParamEntity.setSupId(upId);
        String hql = getHql(sysUnitParamEntity, null);
        return this.getHibernateTemplate().find(hql);
    }

    @Override
    public List findSitePart() throws HSKDBException {
        String sql = "SELECT t.up_id id,t.unit_name 'text',t.unit_code as mdpCode FROM sys_unit_param t WHERE 1=1 and sup_id is null";
        List<TreeNode> resultTree = this.getJdbcTemplate().query(sql, new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                TreeNode node = new TreeNode();
                node.setId(rs.getString("id"));
                node.setText(rs.getString("text"));
                node.setName(rs.getString("text"));
                try {
                    //栏目标签
                    int count = getSysUnitParamParentCount();
                    List tagsList = new ArrayList();
                    tagsList.add(count);
                    node.setTags(tagsList);

                    List<TreeNode> childs = findSysUnitParamChildTree(Integer.parseInt(node.getId()));
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
    public Integer getSysUnitParamParentCount() throws HSKDBException {
        String sql =" SELECT count(*) FROM sys_unit_param";
        int rows  = this.getJdbcTemplate().queryForInt(sql);
        return rows;
    }

    @Override
    public Integer getSysUnitParamChildCount(Integer supId) throws HSKDBException {
        String sql =" SELECT count(*) FROM sys_unit_param where sup_id = " + supId;
        int rows  = this.getJdbcTemplate().queryForInt(sql);
        return rows;
    }

    @Override
    public List findSysUnitParamChildTree(final Integer upId) throws HSKDBException {
        String sql = "SELECT t.up_id id,t.unit_name 'text',t.unit_code as mdpCode FROM sys_unit_param t WHERE 1=1 and sup_id = " + upId;
        List<TreeNode> resultTree = this.getJdbcTemplate().query(sql, new RowMapper() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                TreeNode node = new TreeNode();
                node.setId(rs.getString("id"));
                node.setText(rs.getString("text"));
                node.setName(rs.getString("text"));
                try {
                    //栏目标签
                    int count = getSysUnitParamChildCount(upId);
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


}
