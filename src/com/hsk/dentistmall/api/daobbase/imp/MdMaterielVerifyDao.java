package com.hsk.dentistmall.api.daobbase.imp;

import com.hsk.dentistmall.api.daobbase.IMdMaterielVerifyDao;
import com.hsk.dentistmall.api.persistence.MdMaterielVerifyEntity;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/7/30 14:37
 */
@Component
public class MdMaterielVerifyDao extends SupperDao implements IMdMaterielVerifyDao {
    @Override
    public Integer getMdMaterielVerifyCountByWmsMiId(Integer wmsMiId, Map<String, Object> map) throws HSKDBException {
        String sql = "select md_verify_id from md_materiel_verify where 1=1";
        if (wmsMiId != null)
            sql += " and wms_mi_id = " + wmsMiId;
        if (map != null && !map.isEmpty()) {
            sql += " and wms_mi_id in (select wms_mi_id from md_materiel_info where 1=1)";
            if (map.get("purchaseType") != null && !map.get("purchaseType").equals(""))
                sql += " and purchase_type = '" + Integer.parseInt(map.get("purchaseType").toString()) + "'";
            if (wmsMiId != null)
                sql += " and wms_mi_id = " + wmsMiId;
        }
        sql += " and is_error <> 1 or is_error is null";
        sql += " limit 1";
        List<Map<String, String>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        return list.size();
    }

    @Override
    public Integer getMdMaterielVerifyCountByVerifyId(Integer verifyId) throws HSKDBException {
        String sql = "select md_verify_id from md_materiel_verify where 1=1";
        if (verifyId != null)
            sql += " and verify_id" + verifyId;
        sql += " and is_error <> 1 or is_error is null";
        sql += " limit 1";
        List<Map<String, String>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        return list.size();
    }

    @Override
    public Integer getMdMaterielVerifyCount(Integer verifyType) throws HSKDBException {
        String sql = "select count(*) as count from md_materiel_verify where 1=1";
        if (verifyType != null) {
            if (verifyType != 6)
                sql += " and verify_type = " + verifyType;
            else
                sql += " and verify_state = 2";
        }
        List<Map<String, String>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        Map<String, String> map = list.get(0);
        if (map == null || map.isEmpty())
            return 0;
        if (map.get("count") == null || map.get("count").equals(""))
            return 0;
        return Integer.parseInt(map.get("count"));
    }

    @Override
    public PagerModel getMdMaterielVerifyPagerModel(MdMaterielVerifyEntity mdMaterielVerifyEntity) throws HSKDBException {
        String hql = this.getHql(mdMaterielVerifyEntity);
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public List<MdMaterielVerifyEntity> getMdMaterielVerifyList(MdMaterielVerifyEntity mdMaterielVerifyEntity) throws HSKDBException {
        String hql = this.getHql(mdMaterielVerifyEntity);
        return this.getHibernateTemplate().find(hql);
    }

    private String getHql(MdMaterielVerifyEntity mdMaterielVerifyEntity) {
        StringBuffer sb = new StringBuffer("from MdMaterielVerifu where 1=1");
        if (mdMaterielVerifyEntity.getVerifyState() != null) {
            sb.append(" and verifyState = " + mdMaterielVerifyEntity.getVerifyState());
        }
        if (mdMaterielVerifyEntity.getVerifyState() != null)
            sb.append(" and verifyState = " + mdMaterielVerifyEntity.getVerifyState());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (mdMaterielVerifyEntity.getSearchDate() != null && !mdMaterielVerifyEntity.getSearchDate().equals("")) {
            String searchDate = mdMaterielVerifyEntity.getSearchDate();
            String[] dateA = searchDate.split("~");
            if (dateA.length >= 1)
                sb.append(" and (editDate >= '" + sdf.format(dateA[0]) + " 00:00:00'");
            if (dateA.length >= 2)
                sb.append(" or editDate >= '" + sdf.format(dateA[1]) + " 00:00:00'");
            if (dateA.length >= 1)
                sb.append(")");
        }
        return null;
    }
}
