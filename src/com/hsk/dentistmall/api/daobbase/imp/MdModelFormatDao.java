package com.hsk.dentistmall.api.daobbase.imp;

import com.hsk.dentistmall.api.daobbase.IMdModelFormatDao;
import com.hsk.dentistmall.api.persistence.MdModelFormatAttrInfoEntity;
import com.hsk.dentistmall.api.persistence.MdModelFormatEntity;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/8/3 14:11
 */
@Component
public class MdModelFormatDao extends SupperDao implements IMdModelFormatDao {
    @Override
    public List<MdModelFormatEntity> getMdModelFormatList(MdModelFormatEntity mdModelFormatEntity) throws HSKDBException {
        String hql = this.getHql(mdModelFormatEntity);
        return this.getHibernateTemplate().find(hql);
    }

    @Override
    public Integer getMdModelFormatMmfNameCount(MdModelFormatEntity mdModelFormatEntity) throws HSKDBException {
        String hql = "select count(*) as count from md_model_format where 1=1";
        if (mdModelFormatEntity.getWmsModelId() != null)
            hql += " and wms_model_id = " + mdModelFormatEntity.getWmsModelId();
        if (mdModelFormatEntity.getMmfName() != null && !mdModelFormatEntity.getMmfName().equals(""))
            hql += " and mmf_name = '" + mdModelFormatEntity.getMmfName() + "'";
        if (mdModelFormatEntity.getModelMmfId() != null)
            hql += " and model_mmf_id <> " + mdModelFormatEntity.getModelMmfId();
        hql += " limit 1";
        List<Map<String, Object>> list = this.getJdbcDao().query(hql);
        if (list == null || list.isEmpty())
            return 0;
        Map<String, Object> map = list.get(0);
        if (map == null || map.isEmpty())
            return 0;
        if (map.get("count") == null)
            return 0;
        return Integer.parseInt(map.get("count").toString());
    }

    @Override
    public Integer getMdModelFormatMmfNameCountByString(Integer wmsModelId, String mmfIds, String mmfNames) throws HSKDBException {
        String hql = "select count(*) as count from md_model_format where 1=1";
        if (wmsModelId != null)
            hql += " and wms_model_id = " + wmsModelId;
        if (mmfNames != null && !mmfNames.equals("")) {
            mmfNames = mmfNames.replace(",", "','");
            mmfNames = "'" + mmfNames + "'";
            hql += " and mmf_name in (" + mmfNames + ")";
        }
        if (mmfIds != null && !mmfIds.equals(""))
            hql += " and model_mmf_id not in (" + mmfIds + ")";
        hql += " limit 1";
        List<Map<String, Object>> list = this.getJdbcDao().query(hql);
        if (list == null || list.isEmpty())
            return 0;
        Map<String, Object> map = list.get(0);
        if (map == null || map.isEmpty())
            return 0;
        if (map.get("count") == null)
            return 0;
        return Integer.parseInt(map.get("count").toString());
    }

    @Override
    public void deleteMdFormatAttrInfo(String modelMmfIds) throws HSKDBException {
        if (modelMmfIds == null || modelMmfIds.equals(""))
            return;
        String sql = "delete from md_model_format_attr_info where 1=1 and model_mmf_id in (" + modelMmfIds + ")";
        this.getJdbcDao().execute(sql);
    }

    @Override
    public void deleteMdFormats(Integer wmsModelId, String mmfIds) throws HSKDBException {
        if (wmsModelId == null || mmfIds == null || mmfIds.equals(""))
            return;
        String sql = "delete from md_model_format where 1=1 and wms_model_id = " + wmsModelId + " and model_mmf_id in (" + mmfIds + ")";
        this.getJdbcDao().execute(sql);
    }

    @Override
    public List<Map<String, String>> getMdModelFormatAttrInfoList(String mmfIds) throws HSKDBException {
        if (mmfIds == null || mmfIds.equals("")) {
            return new ArrayList<>();
        }
        String sql = "select distinct a.attr_content as attrContent, a.mma_id as attrId, c.isOptional as isOptional, c.new_add as newAdd," +
                " c.input_model as inputModel, c.mma_name as mmaName, a.can_search as canSearch from md_model_format_attr_info a" +
                " left join md_materiel_attribute c on c.mma_id = a.mma_id where 1=1";
        if (mmfIds != null && !mmfIds.equals(""))
            sql += " and a.model_mmf_id in (" + mmfIds + ")";
        return this.getJdbcDao().query(sql);
    }

    @Override
    public List<Map<String, Object>> getMdModelFormatAttrInfoCanSearchList(String mmfIds) throws HSKDBException {
        if (mmfIds == null || mmfIds.equals("")) {
            return new ArrayList<>();
        }
        String sql = "select distinct a.model_mmf_id as mmfId, a.can_search as canSearch from md_model_format_attr_info a" +
                " left join md_materiel_attribute c on c.mma_id = a.mma_id where 1=1";
        if (mmfIds != null && !mmfIds.equals(""))
            sql += " and a.model_mmf_id in (" + mmfIds + ")";
        return this.getJdbcDao().query(sql);
    }

    private String getHql(MdModelFormatEntity mdModelFormatEntity) {
        StringBuffer sb = new StringBuffer("from MdModelFormatEntity where 1=1");
        if (mdModelFormatEntity.getWmsModelId() != null)
            sb.append(" and wmsModelId = " + mdModelFormatEntity.getWmsModelId());
        if (mdModelFormatEntity.getMmfName() != null && !mdModelFormatEntity.getMmfName().equals(""))
            sb.append(" and mmfName = '" + mdModelFormatEntity.getMmfName() + "'");
        sb.append(" order by createDate");
        return sb.toString();
    }
}
