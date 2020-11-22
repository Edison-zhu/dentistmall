package com.hsk.xframe.api.daobbase.imp;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.daobbase.ISysSameProductDao;
import com.hsk.xframe.api.persistence.SysSameProductEntity;
import com.hsk.xframe.api.persistence.SysUserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/3/24 14:17
 */
@Repository
public class SysSameProductDao extends SupperDao implements ISysSameProductDao {
    @Override
    public void saveSameProduct(SysSameProductEntity sysSameProductEntity) throws HSKDBException {
        this.getHibernateDao().saveOrUpdate(sysSameProductEntity);
    }

    private String getHql(SysSameProductEntity sysSameProductEntity, StringBuilder buffer) {
        StringBuilder sb;
        if (buffer != null) {
            sb = new StringBuilder(buffer);
        } else {
//            sb = new StringBuilder("from SysSameProductEntity where 1=1");
            sb = new StringBuilder("from SysSameProductEntity as a where 1=1");
        }

        if (sysSameProductEntity.getSysSameProductCode() != null && !sysSameProductEntity.getSysSameProductCode().equals("")) {
            if (buffer != null) {
                sb.append(" and (FIND_IN_SET(a.wmsMiId, select wmsMiId from SysSameProductEntity where sysSameProductCode like '%" + sysSameProductEntity.getSysSameProductCode() + "%'))");
            } else {
//                sb.append(" and (" +
//                        "sysSameProductCode like '%" + sysSameProductEntity.getSysSameProductCode() + "%'" +
//                        " or wmsMiId like '%" + sysSameProductEntity.getSysSameProductCode() + "%'" +
//                        ")");
                sb.append(" and (" +
                        "a.sysSameProductCode like '%" + sysSameProductEntity.getSysSameProductCode() + "%'" +
                        " or a.wmsMiId like '%" + sysSameProductEntity.getSysSameProductCode() + "%'" +
                        " or (select b.wmsMiId from MdMaterielInfo as b where b.matName like '%" + sysSameProductEntity.getSysSameProductCode() + "%' and find_in_set(b.wmsMiId, a.wmsMiId) > 0) " +
                        "  is not null" +
                        ")");
            }
        }
        if (sysSameProductEntity.getWmsMiId() != null) {
            sb.append(" and a.wmsMiId = " + sysSameProductEntity.getWmsMiId());
        }
        if (sysSameProductEntity.getWmsMiIdString() != null && !sysSameProductEntity.getWmsMiIdString().equals("")) {
            sb.append(" and a.wmsMiId in " + sysSameProductEntity.getWmsMiIdString());
        }
        if (sysSameProductEntity.getSpState() != null) {
            sb.append(" and a.spState = " + sysSameProductEntity.getSpState());
        }
        if (sysSameProductEntity.getApplicationName() != null && !sysSameProductEntity.getApplicationName().equals("")) {
            sb.append(" and a.wmsMiId in (select wmsMiId from MdMaterielInfo where applicantName like '%" + sysSameProductEntity.getApplicationName() + "%')");
        }

        sb.append(" order by a.createDate");

        return sb.toString();
    }

    @Override
    public PagerModel getPagerModelObject(SysSameProductEntity sysSameProductEntity) throws HSKDBException {
        String hql = getHql(sysSameProductEntity, null);
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public List<SysSameProductEntity> getObjectBySPId(String sameProductCode) throws HSKDBException {
        String hql = "from SysSameProductEntity where sysSameProductCode = " + sameProductCode;
        return this.getHibernateTemplate().find(hql);
    }

    @Override
    public SysSameProductEntity getObjectBySPIdAndWmsMiId(SysSameProductEntity sysSameProductEntity) throws HSKDBException {
        SysSameProductEntity sysSameProductEntity1 = (SysSameProductEntity) this.getOne(sysSameProductEntity);
        if (sysSameProductEntity != null) {
            return sysSameProductEntity1;
        }
        return null;
    }

    @Override
    public PagerModel getSameProductViewPagerModel(SysSameProductEntity sysSameProductEntity) throws HSKDBException {
        return null;
    }

    @Override
    public PagerModel getSameProductProducts(SysSameProductEntity sysSameProductEntity) throws HSKDBException {
        String hql = getHql(sysSameProductEntity, new StringBuilder("from MdMaterielInfo as a where 1=1"));
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public PagerModel getSameProductMoreInfo(SysSameProductEntity sysSameProductEntity) throws HSKDBException {
        String sql = "SELECT a.*,/* e.applicant_name,*/c.root_path AS 'less_file_path' FROM md_materiel_info a " +
                " LEFT JOIN sys_file_info c ON a.lessen_filecode=c.file_code" +
                " left join md_supplier e on e.wz_id = a.wz_id" +
                " left join sys_same_product b on b.sys_same_product_code like '%" + sysSameProductEntity.getSysSameProductCode() + "%'";
        sql += " where 1=1";
        if (sysSameProductEntity.getSysSameProductCode() != null || !sysSameProductEntity.getSysSameProductCode().equals("")) {
            sql += " and FIND_IN_SET(a.wms_mi_id, b.wms_mi_id)";
        }
        if (sysSameProductEntity.getApplicationName() != null && !sysSameProductEntity.getApplicationName().equals("")) {
            sql += " and a.applicant_name like '%" + sysSameProductEntity.getApplicationName() + "%'";
        }
        if (sysSameProductEntity.getState() != null) {
            sql += " and a.state = " + sysSameProductEntity.getState();
        }
        sql += " order by a.wms_mi_id";
        return this.getJdbcDao().findByPage(sql);
    }

    @Override
    public void updateSysSameProductBySPId(SysSameProductEntity sysSameProductEntity) throws HSKDBException {
        this.getHibernateTemplate().update(sysSameProductEntity);
    }

    @Override
    public void deleteObject(String sameProductCode, String wmsMiId) throws HSKDBException {
        if ((sameProductCode == null || sameProductCode.equals("")) || wmsMiId == null) {
            return;
        }
        String sql = "delete from sys_same_product where sys_same_product_code = " + sameProductCode + " and wms_mi_id = " + wmsMiId;
        this.getJdbcDao().execute(sql);
    }

    @Override
    public void deleteAllObject(String sameProductCode) throws HSKDBException {
        if (sameProductCode == null || sameProductCode.equals("")) {
            return;
        }
        String sql = "delete from sys_same_product where sys_same_product_code = '" + sameProductCode + "'";
        this.getJdbcDao().execute(sql);
    }

    @Override
    public Integer countSameProducts(String sameProductCode) throws HSKDBException {
        if (sameProductCode == null || sameProductCode.equals("")) {
            return 0;
        }
        String sql = "select count(b.wms_mi_id) as count from sys_same_product as a, md_materiel_info as b where sys_same_product_code like '%" + sameProductCode + "%'" +
                " and FIND_IN_SET(b.wms_mi_id, a.wms_mi_id) > 0";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list.size() <= 0) {
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
    public List<Map<String, Object>> getSameProductProductList(String sysSameProductCode, String wmsMiId) throws HSKDBException {
        String sql = "SELECT a.*, e.applicant_name,c.root_path AS 'less_file_path' FROM md_materiel_info a " +
                " LEFT JOIN sys_file_info c ON a.lessen_filecode=c.file_code" +
                " left join md_supplier e on e.wz_id = a.wz_id" +
                " left join sys_same_product b ";
        if (wmsMiId != null && !wmsMiId.equals("")) {
            sql += " on a.wms_mi_id in (" + wmsMiId + ")";
        }
        sql += " where b.sys_same_product_code like '%" + sysSameProductCode + "%'";
        return this.getJdbcDao().query(sql);
    }

    @Override
    public void addSameProduct(SysSameProductEntity sysSameProductEntity) throws HSKDBException {
        this.getHibernateDao().save(sysSameProductEntity);
    }

    @Override
    public List<Map<String, Object>> getAllPagerModelObjectDistinct(SysSameProductEntity sysSameProductEntity, String distinctName) throws HSKDBException {
        String sql = "select distinct " + distinctName + " as " + distinctName + " from md_materiel_info a ";
        if (sysSameProductEntity.getSysSameProductCode() != null && !sysSameProductEntity.getSysSameProductCode().equals("")) {
            sql += " left join sys_same_product b on b.sys_same_product_code like '%" + sysSameProductEntity.getSysSameProductCode() + "%' ";
            sql += " where FIND_IN_SET(a.wms_mi_id, b.wms_mi_id)";
        } else {
            sql += " where FIND_IN_SET(a.wms_mi_id, (select GROUP_CONCAT(b.wms_mi_id) from sys_same_product b))";
        }

        sql += " order by a.create_date";
        return this.getJdbcDao().query(sql);
    }

    @Override
    public List<Map<String, Object>> getMaterielInfoByWmsMiId(SysSameProductEntity sysSameProductEntity) throws HSKDBException {
        String sql = "SELECT a.*, e.applicant_name,c.root_path AS 'less_file_path' FROM md_materiel_info a " +
                " LEFT JOIN sys_file_info c ON a.lessen_filecode=c.file_code" +
                " left join md_supplier e on e.wz_id = a.wz_id";
        if (sysSameProductEntity.getWmsMiIdString() != null && !sysSameProductEntity.getWmsMiIdString().equals("")) {
            sql += " where a.wms_mi_id = " + sysSameProductEntity.getWmsMiIdString();
        }
        return this.getJdbcDao().query(sql);
    }

    @Override
    public List<Map<String, Object>> getSameProductProductOnlyWmsMiIdList(Integer id, String sysSameProductCode, String wmsMiIds) throws HSKDBException {
        String sql = "SELECT a.*, e.applicant_name,c.root_path AS 'less_file_path' FROM md_materiel_info a " +
                " LEFT JOIN sys_file_info c ON a.lessen_filecode=c.file_code" +
                " left join md_supplier e on e.wz_id = a.wz_id" +
                " left join sys_same_product b on b.id = " + id;
        sql += " where (a.wms_mi_id like '%" + sysSameProductCode + "%' or a.mat_name like '%" + sysSameProductCode + "%')";
        sql += " and a.wms_mi_id in (" + wmsMiIds + ")";
        sql += " order by b.create_date desc limit 0,3";
        return this.getJdbcDao().query(sql);
    }

    @Override
    public List<Map<String, Object>> getSameProductProductOnlyMatNameList(Integer id, String sysSameProductCode, String wmsMiId) throws HSKDBException {
        String sql = "SELECT a.*, e.applicant_name,c.root_path AS 'less_file_path' FROM md_materiel_info a " +
                " LEFT JOIN sys_file_info c ON a.lessen_filecode=c.file_code" +
                " left join md_supplier e on e.wz_id = a.wz_id" +
                " left join sys_same_product b on b.id = " + id;
        sql += " where (a.mat_name like '%" + sysSameProductCode + "%')";
        sql += " and a.wms_mi_id in (" + wmsMiId + ")";
        sql += " order by b.create_date desc limit 0,3";
        return this.getJdbcDao().query(sql);
    }

    @Override
    public Integer countSameProductsOnlyWmsMiId(Integer id, String sysSameProductCode, String wmsMiId) throws HSKDBException {
        String sql = "SELECT count(a.wms_mi_id) as count FROM md_materiel_info a " +
                " left join sys_same_product b on b.id = " + id;
        sql += " where a.wms_mi_id in (" + wmsMiId + ")";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list.size() <= 0) {
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
    public Integer countSameProductsOnlyMatName(Integer id, String sysSameProductCode, String wmsMiId) throws HSKDBException {
        String sql = "SELECT a.*, e.applicant_name,c.root_path AS 'less_file_path' FROM md_materiel_info a " +
                " left join sys_same_product b on b.id = " + id;
        sql += " where (a.mat_name like '%" + sysSameProductCode + "%')";
        sql += " and a.wms_mi_id in (" + wmsMiId + ")";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list.size() <= 0) {
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
    public Integer getProductByState(String wmsMiId, Integer state) throws HSKDBException {
        String sql = "select count(wms_mi_id) as count from md_materiel_info where state = " + state + " and wms_mi_id in (" + wmsMiId + " )";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if (list.size() <= 0) {
            return 0;
        }
        Map<String, Object> map = list.get(0);
        if (map == null) {
            return 0;
        }
        Integer count = Integer.valueOf(map.get("count").toString());
        return count;
    }
    //导出同款商品
    public List<Map<String,Object>> exportSameCommodity(String sameProductCode) throws HSKDBException{
        String sql="SELECT  t3.mat_name as matName,t1.mmf_name as mmfName,t3.applicant_Name as applicantName,t1.wms_mi_id as mmfId,t1.price as price,t3.state as state FROM md_materiel_format t1 LEFT JOIN md_materiel_info t3 on t1.wms_mi_id =t3.wms_mi_id ,sys_same_product t2  ";
        sql+=" WHERE 1=1 AND FIND_IN_SET(t1.wms_mi_id,t2.wms_mi_id ) ";
//        if (sysSameProductEntity.getWmsMiId() != null) {
//           sql+= " and t1.wmsMiId = " + sysSameProductEntity.getWmsMiId();
//        }
//        if (sysSameProductEntity.getWmsMiIdString() != null && !sysSameProductEntity.getWmsMiIdString().equals("")) {
//            sql+=" and t1.wmsMiId in " + sysSameProductEntity.getWmsMiIdString();
//        }
//        if (sysSameProductEntity.getSpState() != null) {
//            sql+=" and t2.sp_state = " + sysSameProductEntity.getSpState();
//        }
//        if (sysSameProductEntity.getApplicationName() != null && !sysSameProductEntity.getApplicationName().equals("")) {
//            sql+=" and t1.wmsMiId in (select wmsMiId from MdMaterielInfo where applicantName like '%" + sysSameProductEntity.getApplicationName() + "%')";
//        }
//        if (sysSameProductEntity.getSysSameProductCode()!=null){
//            sql+=" AND t2.sys_same_product_code = '"+sysSameProductEntity.getSysSameProductCode()+"'";
//        }
        sql+=" AND t2.sys_same_product_code = '"+ sameProductCode+"'";
        return this.getJdbcDao().query(sql);
    }
    //导出同款商品商品个数，上下架次数
    public List<Map<String,Object>>  countMatnameState(String sameProductCode)throws HSKDBException{
        String sql="SELECT COUNT(t3.mat_name) AS matNameCount, SUM(case when t3.state =1 then t3.state =1 else 0 end) as state1,t2.wms_mi_id as wmsMiId,";
        sql+=" SUM(case when t3.state =2 then t3.state =2 else 0 end) as state2,";
        sql+="SUM(case when t3.state =0 then t3.state =0 else 0 end) as state0 ";
        sql+="FROM md_materiel_format t1 LEFT JOIN md_materiel_info t3 ON t1.wms_mi_id = t3.wms_mi_id,sys_same_product t2 WHERE 1= 1 AND FIND_IN_SET( t1.wms_mi_id, t2.wms_mi_id) ";
//        if (sysSameProductEntity.getWmsMiId() != null) {
//            sql+= " and t1.wmsMiId = " + sysSameProductEntity.getWmsMiId();
//        }
//        if (sysSameProductEntity.getWmsMiIdString() != null && !sysSameProductEntity.getWmsMiIdString().equals("")) {
//            sql+=" and t1.wmsMiId in " + sysSameProductEntity.getWmsMiIdString();
//        }
//        if (sysSameProductEntity.getSpState() != null) {
//            sql+=" and t2.sp_state = " + sysSameProductEntity.getSpState();
//        }
//        if (sysSameProductEntity.getApplicationName() != null && !sysSameProductEntity.getApplicationName().equals("")) {
//            sql+=" and t1.wmsMiId in (select wmsMiId from MdMaterielInfo where applicantName like '%" + sysSameProductEntity.getApplicationName() + "%')";
//        }

//        if (sysSameProductEntity.getSysSameProductCode()!=null){
//            sql+=" AND t2.sys_same_product_code = '"+sysSameProductEntity.getSysSameProductCode()+"'";
//        }
        sql+=" AND t2.sys_same_product_code = '"+ sameProductCode+"'";
        return this.getJdbcDao().query(sql);

    }
}
