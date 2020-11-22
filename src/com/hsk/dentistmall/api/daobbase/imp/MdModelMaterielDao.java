package com.hsk.dentistmall.api.daobbase.imp;

import com.hsk.dentistmall.api.daobbase.IMdModelMaterielDao;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/7/31 10:24
 */
@Component
public class MdModelMaterielDao extends SupperDao implements IMdModelMaterielDao {
    @Override
    public PagerModel getMdModelMaterielPagerModel(MdModelMaterielEntity mdModelMaterielEntity) throws HSKDBException {
        String hql = this.getHql(mdModelMaterielEntity);
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public List<Map<String, Object>> getMdModelMaterielRefCount(String mdModelIds) throws HSKDBException {
        String sql = "select count(*) as count, md_model_id as mdModelId from md_materiel_info where 1=1";
        if (mdModelIds != null && !mdModelIds.equals(""))
            sql += " and md_model_id in (" + mdModelIds + ")";
        sql += " group by md_model_id";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return null;
        return list;
    }

    @Override
    public List<Map<String, Object>> getMdModelMaterielFilePath(String mdModelIds) throws HSKDBException {
        String sql = "select b.root_path as rootPath, a.md_model_id as mdModelId, a.file_id as fileId from md_model_file_info a left join sys_file_info b on a.file_id = b.file_id where 1=1";
        if (mdModelIds != null && !mdModelIds.equals(""))
            sql += " and a.md_model_id in (" + mdModelIds + ")";
        sql += " and (b.is_delete = 1 or b.is_delete is null)";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null && list.isEmpty())
            return null;
        return list;
    }

    @Override
    public List<Map<String, Object>> getMdModelMaterielBrand(String mdModelIds) throws HSKDBException {
        String sql = "select b.mbd_name as brandName, a.md_model_id as mdModelId from md_model_brand_info a left join md_materiel_brand b on a.mbd_id = b.mbd_id where 1=1";
        if (mdModelIds != null && !mdModelIds.equals(""))
            sql += " and a.md_model_id in (" + mdModelIds + ")";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null && list.isEmpty())
            return null;
        return list;
    }

    @Override
    public List<Map<String, Object>> getMdModelMaterielRefCountOnly(String mdModelIds) throws HSKDBException {
        String sql = "select count(*) as count, md_model_id as mdModelId from md_materiel_info where 1=1";
        if (mdModelIds != null && !mdModelIds.equals(""))
            sql += " and md_model_id in (" + mdModelIds + ")";
        sql += " group by md_model_id";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return null;
        return list;
    }

    @Override
    public Integer getMdModelMaterielRefCountOnly(Integer mdModelId) throws HSKDBException {
        String sql = "select count(*) as count from md_materiel_info where 1=1";
        if (mdModelId != null)
            sql += " and md_model_id = " + mdModelId;
        sql += " group by md_model_id";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        Map<String, Object> map = list.get(0);
        if (map == null || map.isEmpty())
            return 0;
        return Integer.parseInt(map.get("count").toString());
    }

    @Override
    public void deleteMdModelMateruel(String wmsModelIds) throws HSKDBException {
        if (wmsModelIds == null || wmsModelIds.equals(""))
            return;
        String sql = "delete from md_model_materiel where 1=1";
        sql += " and wms_model_id in (" + wmsModelIds + ")";
        this.getJdbcDao().execute(sql);
    }

    @Override
    public List<MdMaterielType> getMdMaterielTypeFirst(Integer mmtId) throws HSKDBException {
        String sql = "from MdMaterielType where 1=1";
        if (mmtId != null)
            sql += " and mmtId = " + mmtId;
        sql += " and mmtLevel = 1 and state = 1";
        return this.getHibernateTemplate().find(sql);
    }

    @Override
    public List<MdMaterielType> getMdMaterielTypeSecond(Integer mmtId, Integer firstId) throws HSKDBException {
        String sql = "from MdMaterielType where 1=1";
        if (mmtId != null)
            sql += " and mmtId = " + mmtId;
        if (firstId != null)
            sql += " and mdMmtId = " + firstId;
        sql += " and mmtLevel = 2 and state = 1";
        return this.getHibernateTemplate().find(sql);
    }

    @Override
    public List<MdMaterielType> getMdMaterielTypeThird(Integer mmtId, Integer secondId, Integer firstId) throws HSKDBException {
        String sql = "from MdMaterielType where 1=1";
        if (mmtId != null)
            sql += " and mmtId = " + mmtId;
        if (secondId != null)
            sql += " and mdMmtId = " + secondId;
        if (secondId != null)
            sql += " and mdMmtId in (select mmtId from MdMaterielType where mdMmtId = " + firstId + " and mmtLevel = 2 and state = 1)";
        sql += " and mmtLevel = 3 and state = 1";
        return this.getHibernateTemplate().find(sql);
    }

    @Override
    public List<Map<String, Object>> getMdMaterielTypeLinkList(String searchName, Integer suiId) throws HSKDBException {
        String sql = "select a.* from (select group_concat(b.mmt_name SEPARATOR '>') as name, group_concat(b.mmt_id) as mmtId  from md_model_type_link_info a, md_materiel_type b where ((a.first_id = b.mmt_id or a.second_id = b.mmt_id or a.third_id = b.mmt_id)";
        if (suiId != null)
            sql += " and a.sui_id = " + suiId;
        sql += ")";
        sql += " group by a.first_id, a.second_id, a.third_id) as a where 1=1";
        if (searchName != null && !searchName.equals(""))
            sql += " and name like '%" + searchName + "%'";

        return this.getJdbcDao().query(sql);
    }

    @Override
    public Integer getMdModelTypeLinkId(MdModelTypeLinkInfoEntity mdModelTypeLinkInfoEntity) throws HSKDBException {
        String sql = "select md_mt_id as mdMtId from md_model_type_link_info where 1=1";
        if (mdModelTypeLinkInfoEntity.getRbaId() != null)
            sql += " and rba_id = " + mdModelTypeLinkInfoEntity.getRbaId();
        if (mdModelTypeLinkInfoEntity.getRbsId() != null)
            sql += " and rbs_id = " + mdModelTypeLinkInfoEntity.getRbsId();
        if (mdModelTypeLinkInfoEntity.getRbbId() != null)
            sql += " and rbb_id = " + mdModelTypeLinkInfoEntity.getRbbId();
        if (mdModelTypeLinkInfoEntity.getSuiId() != null)
            sql += " and sui_id = " + mdModelTypeLinkInfoEntity.getSuiId();
        sql += " order by create_date limit 10";
        List<Map<String ,Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return -1;
        if (list.size() < 10)
            return -1;
        Map<String, Object> map = list.get(0);
        if (map == null || map.isEmpty())
            return -1;
        return Integer.parseInt(map.get("mdMtId").toString());
    }

    @Override
    public Map<String, String> getMdModelMaterielLastCode() throws HSKDBException {
        String sql = "select model_mat_code as modelMatCode from md_model_materiel where 1=1";
        sql += " order by wms_model_id desc limit 1";
        List<Map<String, String>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return null;
        Map<String, String> map = list.get(0);
        if (map == null || map.isEmpty())
            return null;
        return map;
    }

    @Override
    public Integer getMdModelMaterielCodeCountCheck(String matCode, Integer wmsModelId) throws HSKDBException {
        String sql = "select wms_model_id from md_model_materiel where 1=1";
        if (matCode != null && !matCode.equals(""))
            sql += " and model_mat_code = '" + matCode + "'";
        if (wmsModelId != null)
            sql += " and wms_model_id <> " + wmsModelId;
        sql += " limit 1";
        List<Map<String, String>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        Map<String, String> map = list.get(0);
        if (map == null || map.isEmpty())
            return 0;
        return list.size();
    }

    @Override
    public void deleteMdModelFileInfo(Integer wmsModelId) throws HSKDBException {
        if (wmsModelId == null)
            return;
        String sql = "delete from md_model_file_info where 1=1 and md_model_id = " + wmsModelId;
        this.getJdbcDao().execute(sql);
    }

    @Override
    public void deleteMdModelFileInfo(String wmsModelIds) throws HSKDBException {
        if (wmsModelIds == null || wmsModelIds.equals(""))
            return;
        String sql = "delete from md_model_file_info where 1=1 and md_model_id in (" + wmsModelIds + ")";
        this.getJdbcDao().execute(sql);
    }

    @Override
    public void deleteMdModelBrand(String wmsModelIds) throws HSKDBException {
        if (wmsModelIds == null || wmsModelIds.equals(""))
            return;
        String sql = "delete from md_model_brand_info where 1=1 and md_model_id in (" + wmsModelIds + ")";
        this.getJdbcDao().execute(sql);
    }

    @Override
    public List<MdMaterielParameter> getMdModelParameterListByMmpIds(String mmpIdStr) throws HSKDBException {
        String hql = "from MdMaterielParameter where 1=1";
        if (mmpIdStr != null && !mmpIdStr.equals(""))
            hql += " and mmpId in (" + mmpIdStr + ")";
        return this.getHibernateTemplate().find(hql);
    }

    @Override
    public List<MdModelParameterInfoEntity> getMdModelParameterListByModelId(Integer wmsModelId) throws HSKDBException {
        String hql = "from MdModelParameterInfoEntity where 1=1";
        if (wmsModelId != null)
            hql += " and mdModelId = " + wmsModelId;
        return this.getHibernateTemplate().find(hql);
    }

    @Override
    public Integer getMdModelMaterielNameChange(String modelMatName, Integer wmsModelId) throws HSKDBException {
        String sql = "select wms_model_id from md_model_materiel where 1=1";
        if (modelMatName != null && !modelMatName.equals(""))
            sql += " and model_mat_name = '" + modelMatName + "'";
        if (wmsModelId != null)
            sql += " and wms_model_id = " + wmsModelId;
        sql += " limit 1";
        List<Map<String, String>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        Map<String, String> map = list.get(0);
        if (map == null || map.isEmpty())
            return 0;
        return list.size();
    }

    @Override
    public PagerModel getMdModelOpratePagerModel(Integer wmsModelId) throws HSKDBException {
        String hql = "from MdModelOperateLogEntity where 1=1";
        if (wmsModelId != null)
            hql += " and mdModelId = " + wmsModelId;
        hql += " order by createDate desc";
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public Integer getMdMaterielTypeLinkCount(MdModelTypeLinkInfoEntity mdModelTypeLinkInfoEntity) throws HSKDBException {
        String sql = "select count(*) as count from md_model_type_link_info where 1=1";
        if (mdModelTypeLinkInfoEntity.getFirstId() != null)
            sql += " and first_id = " + mdModelTypeLinkInfoEntity.getFirstId();
        else
            sql += " and first_id is null";
        if (mdModelTypeLinkInfoEntity.getSecondId() != null)
            sql += " and second_id = " + mdModelTypeLinkInfoEntity.getSecondId();
        else
            sql += " and second_id is null";
        if (mdModelTypeLinkInfoEntity.getThirdId() != null)
            sql += " and third_id = " + mdModelTypeLinkInfoEntity.getThirdId();
        else
            sql += " and third_id is null";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        Map<String, Object> map = list.get(0);
        if (map == null || map.isEmpty())
            return 0;
        if (map.get("count") == null)
            return  0;
        return Integer.parseInt(map.get("count").toString());
    }

    @Override
    public Integer getMdModelMaterielMatNameCountCheck(String matName, Integer wmsModelId) throws HSKDBException {
        String sql = "select wms_model_id from md_model_materiel where 1=1";
        if (matName != null && !matName.equals(""))
            sql += " and model_mat_name = '" + matName + "'";
        if (wmsModelId != null)
            sql += " and wms_model_id <> " + wmsModelId;
        sql += " limit 1";
        List<Map<String, String>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        Map<String, String> map = list.get(0);
        if (map == null || map.isEmpty())
            return 0;
        return list.size();
    }

    @Override
    public void deleteMdModelFormat(String wmsModelIds) throws HSKDBException {
        if (wmsModelIds == null || wmsModelIds.equals(""))
            return;
        String sql = "update md_model_format set wms_model_id = null where wms_model_id in (" + wmsModelIds + ")";
        this.getJdbcDao().execute(sql);
    }

    @Override
    public void deleteMdModelLog(String wmsModelIds) throws HSKDBException {
        if (wmsModelIds == null || wmsModelIds.equals(""))
            return;
        String sql = "delete from md_model_operate_log where md_model_id in (" + wmsModelIds + ")";
        this.getJdbcDao().execute(sql);
    }

    @Override
    public List<Map<String, Object>> getImgIds(Integer wmsModelId, String imgIds) throws HSKDBException {
        String sql = "select file_id as imgIds from md_model_file_info where md_model_id = " + wmsModelId;
        return this.getJdbcDao().query(sql);
    }

    @Override
    public void deleteMdModelParameter(String wmsModelId) throws HSKDBException {
        if (wmsModelId == null || wmsModelId.equals(""))
            return;
        String sql = "delete from md_model_parameter_info where md_model_id in (" + wmsModelId + ")";
        this.getJdbcDao().execute(sql);
    }

    @Override
    public Integer getMdModelMaterielStateChange(String state, Integer wmsModelId) throws HSKDBException {
        String sql = "select wms_model_id from md_model_materiel where 1=1";
        if (state != null && !state.equals(""))
            sql += " and state = '" + state + "'";
        if (wmsModelId != null)
            sql += " and wms_model_id = " + wmsModelId;
        sql += " limit 1";
        List<Map<String, String>> list = this.getJdbcDao().query(sql);
        if (list == null || list.isEmpty())
            return 0;
        Map<String, String> map = list.get(0);
        if (map == null || map.isEmpty())
            return 0;
        return list.size();
    }

    private String getHql(MdModelMaterielEntity mdModelMaterielEntity) {
        StringBuffer sb = new StringBuffer("from MdModelMaterielEntity where 1=1");
        if (mdModelMaterielEntity.getSearchName() != null && !mdModelMaterielEntity.getSearchName().equals("")) {
            sb.append(" and (modelMatName like '%" + mdModelMaterielEntity.getSearchName() + "%'" +
                    " or modelMatName like '%" + mdModelMaterielEntity.getSearchName().toUpperCase() + "%'" +
                    " or aliasName like '%" + mdModelMaterielEntity.getSearchName() + "%'" +
                    " or aliasName like '%" + mdModelMaterielEntity.getSearchName().toUpperCase() + "%'" +
                    " or keyWord like '%" + mdModelMaterielEntity.getSearchName() + "%'" +
                    " or keyWord like '%" + mdModelMaterielEntity.getSearchName().toUpperCase() + "%'" +
                    " or modelMatCode like '%" + mdModelMaterielEntity.getSearchName() + "%'" +
                    " or modelMatCode like '%" + mdModelMaterielEntity.getSearchName().toUpperCase() + "%')");
        }
        if (mdModelMaterielEntity.getMbdIds() != null && !mdModelMaterielEntity.getMbdIds().equals(""))
            sb.append(" and mbdId in (" + mdModelMaterielEntity.getMbdIds() + ")");
        if (mdModelMaterielEntity.getState() != null && !mdModelMaterielEntity.getState().equals(""))
            sb.append(" and state = " + mdModelMaterielEntity.getState());
        if (mdModelMaterielEntity.getMatType1() != null)
            sb.append(" and matType1 = " + mdModelMaterielEntity.getMatType1());
        if (mdModelMaterielEntity.getMatType2() != null)
            sb.append(" and matType2 = " + mdModelMaterielEntity.getMatType2());
        if (mdModelMaterielEntity.getMatType3() != null)
            sb.append(" and matType3 = " + mdModelMaterielEntity.getMatType3());

        sb.append(" order by createDate desc");
        return sb.toString();
    }
}
