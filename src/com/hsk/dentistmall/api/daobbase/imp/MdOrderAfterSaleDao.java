package com.hsk.dentistmall.api.daobbase.imp;

import com.hsk.dentistmall.api.daobbase.IMdOrderAfterSaleDao;
import com.hsk.dentistmall.api.persistence.MdOrderAfterSaleEntity;
import com.hsk.dentistmall.api.persistence.MdOrderAfterSaleExtendEntity;
import com.hsk.dentistmall.api.persistence.MdOrderAfterSaleMxEntity;
import com.hsk.dentistmall.api.persistence.MdSupplier;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2019/12/26 16:51
 */
@Component
public class MdOrderAfterSaleDao extends SupperDao implements IMdOrderAfterSaleDao {
    @Override
    public MdOrderAfterSaleEntity saveMdOrderSaleAfter(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKDBException {
        mdOrderAfterSaleEntity = (MdOrderAfterSaleEntity) this.getHibernatesession().merge(mdOrderAfterSaleEntity);
        this.getHibernateDao().saveOrUpdate(mdOrderAfterSaleEntity);
//        this.newObject(mdOrderAfterSaleEntity);
        return mdOrderAfterSaleEntity;
    }

    @Override
    public Integer saveMdOrderSaleAfterMx(MdOrderAfterSaleMxEntity mdOrderAfterSaleMxEntity) throws HSKDBException {
        return this.newObject(mdOrderAfterSaleMxEntity);
    }

    @Override
    public MdOrderAfterSaleEntity getMdOrderSaleAfterByMasId(Integer suiId, Integer masId) throws HSKDBException {
        MdOrderAfterSaleEntity mdOrderAfterSaleEntity = new MdOrderAfterSaleEntity();
        if(masId == null){
            return mdOrderAfterSaleEntity;
        }
        mdOrderAfterSaleEntity.setMasId(masId);
        if(suiId != null){
            mdOrderAfterSaleEntity.setSuiId(suiId);
        }

        Object obj = this.getOne(mdOrderAfterSaleEntity);
        if(obj != null){
            mdOrderAfterSaleEntity = (MdOrderAfterSaleEntity)obj;
        }
        return mdOrderAfterSaleEntity;
    }

    @Override
    public MdOrderAfterSaleEntity getMdOrderSaleAfter(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKDBException {
        return (MdOrderAfterSaleEntity)this.getOne(mdOrderAfterSaleEntity);
    }

    @Override
    public PagerModel getMdOrderSaleAfterPageModel(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKDBException {
        String hql = getHql(mdOrderAfterSaleEntity);
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public List<MdOrderAfterSaleEntity> getMdOrderSaleAfterList(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKDBException {
        String hql = getHql(mdOrderAfterSaleEntity);
        return this.getHibernateTemplate().find(hql);
    }

    @Override
    public List<MdOrderAfterSaleEntity> getMdOrderSaleAfterListById(Integer suiId, Integer moiId) throws HSKDBException {
        return null;
    }

    private String getHql(MdOrderAfterSaleEntity mdOrderAfterSaleEntity){
        String hql = "from MdOrderAfterSaleEntity where 1=1";
        if(mdOrderAfterSaleEntity.getSuiId() != null){
            hql += " and suiId=" + mdOrderAfterSaleEntity.getSuiId();
        }
        if(mdOrderAfterSaleEntity.getMoiId() != null){
            hql += " and moiId=" + mdOrderAfterSaleEntity.getMoiId();
        }
        if(mdOrderAfterSaleEntity.getMasCode() != null && !mdOrderAfterSaleEntity.getMasCode().equals("")){
            hql += " and mas_code=" + mdOrderAfterSaleEntity.getMasCode();
        }
        return hql;
    }

    @Override
    public List<Map<String, Object>> getMdOrderSaleAfterMxList(Integer masId, String searchName, Integer limit, Integer page) throws HSKDBException {
//        String sql = "select a.* from md_order_after_sale_mx where 1=1";
        String sql = "SELECT a.*, e.after_sale, d.mmf_code,c.root_path AS 'less_file_path', (SELECT f.param_name from sys_parameter f,sys_parameter g WHERE f.param_value= e.after_sale and f.sys_spar_id=g.spar_id and g.param_code='PAR191230112633225') as after_sale_name" +
                " FROM md_order_after_sale_mx e left join md_order_mx a on e.mom_id = a.mom_id left join md_materiel_format d on a.mmf_id = d.mmf_id ,md_materiel_info b "
                + "LEFT JOIN sys_file_info c ON b.lessen_filecode=c.file_code "
                + "WHERE a.wms_mi_id=b.wms_mi_id";
//        sql += " and moi_id in ("+moiId+") order by a.mom_id";
        if(masId != null){
            sql += " and e.mas_id=" + masId;
        }
        if (searchName != null && !searchName.equals("")){
            sql += " and a.mat_name like '%" + searchName + "%'";
        }
        sql += " order by e.mas_id";
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }

    @Override
    public List<MdOrderAfterSaleMxEntity> getMdOrderSaleASMxList(Integer masId) throws HSKDBException {
        String hql = "from MdOrderAfterSaleMxEntity where masId = " + masId;
        return this.getHibernateTemplate().find(hql);
    }

    @Override
    public void updateMdOrderSaleAfterSale(MdOrderAfterSaleEntity mdOrderAfterSaleEntity) throws HSKDBException {
        if (mdOrderAfterSaleEntity == null){
            return;
        }
        this.getHibernateTemplate().update(mdOrderAfterSaleEntity);
    }

    @Override
    public void updateMdOrderSaleAfterSaleMx(MdOrderAfterSaleMxEntity mdOrderAfterSaleMxEntity) throws HSKDBException {
        if (mdOrderAfterSaleMxEntity == null){
            return;
        }
        this.getHibernateTemplate().update(mdOrderAfterSaleMxEntity);
    }


    @Override
    public List<Map<String, Object>>  getMdOrderSaleAfterSupplier(Integer suiId, Integer masId) throws HSKDBException {
        String sql = "select b.wz_id from md_order_after_sale a left join md_order_info b on a.moi_id = b.moi_id where a.sui_id=" + suiId + " and mas_id=" + masId;
        return this.getJdbcDao().query(sql);
    }

    @Override
    public MdOrderAfterSaleEntity saveApplyASAddress(Integer sui_id, Integer masId, String expressName, String expressCode) throws HSKDBException {
        MdOrderAfterSaleEntity mdOrderAfterSaleEntity = new MdOrderAfterSaleEntity();
        mdOrderAfterSaleEntity.setSuiId(sui_id);
        mdOrderAfterSaleEntity.setMasId(masId);
        mdOrderAfterSaleEntity = (MdOrderAfterSaleEntity)this.getOne(mdOrderAfterSaleEntity);

        mdOrderAfterSaleEntity.setExpressCode(expressCode);
        mdOrderAfterSaleEntity.setExpressName(expressName);
        this.getHibernateDao().saveOrUpdate(mdOrderAfterSaleEntity);
        return mdOrderAfterSaleEntity;
    }

    @Override
    public void deleteAfterSale(Integer sui_id, Integer masId) throws HSKDBException {
        MdOrderAfterSaleEntity mdOrderAfterSaleEntity = new MdOrderAfterSaleEntity();
        mdOrderAfterSaleEntity.setSuiId(sui_id);
        mdOrderAfterSaleEntity.setMasId(masId);
        this.getHibernateDao().delete(mdOrderAfterSaleEntity);
    }

    @Override
    public void deleteAfterSaleMx(Integer masId) throws HSKDBException {
        MdOrderAfterSaleMxEntity mdOrderAfterSaleEntity = new MdOrderAfterSaleMxEntity();
        mdOrderAfterSaleEntity.setMasId(masId);
        this.getHibernateDao().delete(mdOrderAfterSaleEntity);
    }

    @Override
    public PagerModel getMdOrderSaleAfterPageModelByMoiId(Integer suiId, Integer moiId, Integer afterSale) throws HSKDBException {
        String hql = "from MdOrderAfterSaleEntity where moiId = " + moiId;
        if(suiId != null){
            hql += " and suiId = " + suiId;
        }
        if(afterSale != null){
            if(afterSale == 3){
                hql += " and asState in (3, 4, 6) ";
            } else {
                hql += " and afterSale = " + afterSale;
            }
        }
        hql += " order by createDate desc";
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public List<Map<String, Object>> getMdOrderSaleAfterMxListCountByMasId(Integer masId, String searchName) throws HSKDBException {
        String sql = "select count(mas_mx_id) as total_count from md_order_after_sale_mx e left join md_order_mx a on e.mom_id = a.mom_id left join md_materiel_format d on a.mmf_id = d.mmf_id ," +
                "md_materiel_info b "
                + "LEFT JOIN sys_file_info c ON b.lessen_filecode=c.file_code "
                + "WHERE a.wms_mi_id=b.wms_mi_id and mas_id = " + masId;
        if (searchName != null && !searchName.equals("")){
            sql += " and e.mat_name like '%" + searchName + "%'";
        }
        return this.getJdbcDao().query(sql);
    }

    @Override
    public Integer countOrderInfoByAfterSaleState(Integer suiId, Integer moiId, String afterSaleState) throws HSKDBException {
        String sql = "select count(mas_id) as total_count from md_order_after_sale where moi_id = " + moiId;
        if(suiId != null){
            sql += " and sui_id=" + suiId;
        }
        if(afterSaleState != null && !afterSaleState.equals("")){
            if(afterSaleState.equals("3")){
                sql += " and as_state in (3, 4, 6) ";
            }else {
                sql += " and after_sale = " + afterSaleState;
            }
        }
        List<Map<String, Object>> map = this.getJdbcDao().query(sql);
        if(map != null && map.size() > 0){
            Integer count = Integer.parseInt(map.get(0).get("total_count").toString());
            return count;
        }
        return 0;
    }

    @Override
    public void saveMdOrderSaleAfterEx(MdOrderAfterSaleExtendEntity mdOrderAfterSaleExtendEntity) throws HSKDBException {
        this.getHibernateDao().saveOrUpdate(mdOrderAfterSaleExtendEntity);
    }

    @Override
    public PagerModel getMdOrderSaleAfterExPageModel(Integer masId) throws HSKDBException {
        String hql = "from MdOrderAfterSaleExtendEntity where masId = " + masId + " order by ctlDate desc";
        return this.getHibernateDao().findByPage(hql);
    }
    @Override
	
  //导出售后订单
    public List<Map<String, Object>> exportOrderAfterMx(Integer masId) throws HSKDBException{
    	String sql="SELECT  t1.moi_id, t1.mas_code ,t1.after_sale,t1.remarks,t1.create_date,t2.order_code,t3.`ctl_date`,t5.node_name FROM  md_order_after_sale  t1 LEFT JOIN md_order_info t2 ON t1.moi_id=t2.moi_id LEFT JOIN md_order_after_sale_extend t3 ON t1.mas_id=t3.`mas_id` LEFT JOIN sys_user_info t4 ON t4.sui_id=t1.sui_id LEFT JOIN  sys_org_gx t5 ON t5.org_gx_id=t4.org_gx_id WHERE 1=1 ";
    	sql+="AND t1.mas_id='"+masId+"'";
    	return this.getJdbcDao().query(sql);
    }
  //导出售后订单2
    public List<Map<String, Object>> exportOrderAfterMx2(Integer masId) throws HSKDBException{
    	String sql="SELECT t1.after_sale,t1.base_number,t2.Basic_unit, t2.mat_name,t2.NORM,t2.mat_number,t2.number2,t2.Unit_money,t3.product_name FROM md_order_after_sale_mx t1 LEFT JOIN md_order_mx t2 ON t1.mom_id=t2.mom_id LEFT JOIN md_materiel_info t3 ON t2.wms_mi_id =t3.wms_mi_id WHERE 1=1";
    	sql+=" AND t1.mas_id='"+masId+"'";
    	System.out.println("sql"+sql);
    	return this.getJdbcDao().query(sql);
    }

    @Override
    public void deleteAfterSaleMxAll(Integer masId) throws HSKDBException {
        if(masId == null){
            return;
        }
        String sql = "delete from md_order_after_sale_mx where mas_id=" + masId;
        this.getJdbcDao().execute(sql);
    }

    @Override
    public List<MdOrderAfterSaleMxEntity> getMdOrderSaleASMxListByMomIds(String newMomIds) throws HSKDBException {
        String hql = "from MdOrderAfterSaleMxEntity where 1=1";
        if(newMomIds == null || newMomIds.equals("")){
            return null;
        }
        hql += " and momId in (" + newMomIds + ")";
        return this.getHibernateTemplate().find(hql);
    }

    @Override
    public List<MdOrderAfterSaleEntity> getMdOrderSaleAfterMoiId(Integer sui_id, Integer moiId) throws HSKDBException {
        String hql = "from MdOrderAfterSaleEntity where moiId = " + moiId + " and (afterSale != 6 and asState != 4)";
        if(sui_id != null){
            hql += " and suiId = " + sui_id;
        }
//        if(afterSale != null && !afterSale.equals("")){
//            hql += " and afterSale = " + afterSale;
//        }
        hql += " order by createDate desc";
        return this.getHibernateTemplate().find(hql);
    }

    @Override
    public PagerModel getMdOrderSaleAfterPageModelBySearch(Integer suiId, Integer moiId, String searchAsState, String searchName, String placeOrderTime, String searchAsType) throws HSKDBException {
        String hql = "from MdOrderAfterSaleEntity where moiId = " + moiId;
        if(suiId != null){
            hql += " and suiId = " + suiId;
        }
        if (searchAsState != null && !searchAsState.trim().equals("")) {//查询待发货
            if (searchAsState.equals("3")) {
                hql += " and asState in (3, 4, 6) ";
            } else {
                hql += " and afterSale = " + searchAsState;
            }
//            hql += " and (asState like '%" + searchAsState + "%')";
        }
		if(searchName != null && !searchName.trim().equals("")){
			hql += " and (masCode like '%" + searchName.trim().toUpperCase() + "%' or" +
                    " masId in (select masId from MdOrderAfterSaleMxEntity where matName like '%" + searchName.trim().toUpperCase() + "%'))";
		}
		if(placeOrderTime != null && !placeOrderTime.trim().equals("")){
			hql += " and (createDate >= '" + placeOrderTime.split("~")[0] + " 00:00:00' and createDate <= '" + placeOrderTime.split("~")[1] +" 23:59:59')";
		}
		if(searchAsType != null && !searchAsType.equals("")){
		    if(searchAsType.equals("3")){
                hql += " and asState in (3, 4, 6) ";
            }else {
                hql += " and afterSale like '%" + searchAsType + "%'";
            }
		}
        hql += " order by createDate desc";
        return this.getHibernateDao().findByPage(hql);
    }

    @Override
    public List<Map<String, Object>> getMdOrderSaleAfterMxListBySearch(Integer suiId, Integer moiId, String searchAsState, String searchName, Integer limit, Integer page) throws HSKDBException {
        String sql = "SELECT DISTINCT e.mas_id " +
                " FROM md_order_after_sale_mx e " +
                " left join md_order_after_sale c on c.mas_id = e. mas_id";
        if (suiId != null) {
            sql += " and c.sui_id = " + suiId;
        }
        sql += " left join md_order_mx a on e.mom_id = a.mom_id left join md_materiel_format d on a.mmf_id = d.mmf_id ,md_materiel_info b WHERE 1=1";
        if (searchName != null && !searchName.equals("")) {
            sql += " and a.mat_name like '%" + searchName.trim().toUpperCase() + "%'";
        }
        if (searchAsState != null && !searchAsState.trim().equals("")) {//查询待发货
            sql += " and (e.after_sale like '%" + searchAsState + "%')";
        }
        if(moiId != null) {
            sql += " and c.moi_id = " + moiId;
        }
        sql += " and a.wms_mi_id=b.wms_mi_id";
        sql += " limit " + (page - 1) * limit + "," + limit;
        return this.getJdbcDao().query(sql);
    }

    @Override
    public List<Map<String, Object>> getMdOrderSaleAfterMxList(String masIds, String searchName) throws HSKDBException {
        String sql = "SELECT a.*, e.mas_id, e.after_sale, d.mmf_code,c.root_path AS 'less_file_path', (SELECT f.param_name from sys_parameter f,sys_parameter g WHERE f.param_value= e.after_sale and f.sys_spar_id=g.spar_id and g.param_code='PAR191230112633225') as after_sale_name" +
                " FROM md_order_after_sale_mx e left join md_order_mx a on e.mom_id = a.mom_id left join md_materiel_format d on a.mmf_id = d.mmf_id ,md_materiel_info b "
                + "LEFT JOIN sys_file_info c ON b.lessen_filecode=c.file_code "
                + "WHERE a.wms_mi_id=b.wms_mi_id";
        if(masIds != null && !masIds.equals("")){
            sql += " and e.mas_id in (" + masIds + ")";
        }
        sql += " order by e.mas_id";
        return this.getJdbcDao().query(sql);
    }

    @Override
    public Integer getMdOrderSaleASMXCount(Integer suiId, Integer moiId, Integer afterSale) throws HSKDBException {
        String sql = "SELECT count(DISTINCT a.mom_id ) AS as_count FROM md_order_after_sale_mx a LEFT JOIN md_order_after_sale b ON a.mas_id = b.mas_id WHERE 1=1 ";
        if(suiId != null){
            sql += " and b.sui_id = " + suiId;
        }
        if(moiId != null) {
            sql += " and b.moi_id = " + moiId;
        }
        if(afterSale != null) {
            sql += " and b.as_state = " + afterSale;
        }
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if(list.size() <= 0){
            return 0;
        }
        Map<String, Object> map = list.get(0);
        if(map == null){
            return 0;
        }
        Integer count = Integer.parseInt(map.get("as_count") == null ? "0" : map.get("as_count").toString());
        return count;
    }

    @Override
    public Integer getMdOrderSaleAfterMxCountByMasId(Integer masId) throws HSKDBException {
        String sql = "select count(mas_id) as as_count from md_order_after_sale_mx where mas_id = " + masId;
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if(list.isEmpty()){
            return 0;
        }
        Map<String, Object> map = list.get(0);
        if(map.isEmpty()){
            return 0;
        }
        Integer count = Integer.parseInt(map.get("as_count") == null ? "0" : map.get("as_count").toString());
        return count;
    }

    @Override
    public Double getMdOrderSaleAfterMxMoneyByMasId(Integer masId) throws HSKDBException {
        String sql = "select SUM(Total_money) as money from md_order_mx where mom_id in (select mom_id from md_order_after_sale_mx where mas_id = " + masId + ")";
        List<Map<String, Object>> list = this.getJdbcDao().query(sql);
        if(list.size() <= 0){
            return 0d;
        }
        Map<String, Object> map = list.get(0);
        if(map == null){
            return 0d;
        }
        Double count = Double.parseDouble(map.get("money") == null ? "0" : map.get("money").toString());
        return count;
    }
}
