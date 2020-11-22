package com.hsk.miniapi.dentistmall.api.daobbase.imp;


import com.hsk.dentistmall.api.persistence.MdPurchased;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdCartsApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdFavoritesApiDao;
import com.hsk.dentistmall.api.persistence.MdCarts;
import com.hsk.dentistmall.api.persistence.MdFavorites;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class MDCartsApiDao extends SupperDao implements IMdCartsApiDao{

	public MdCarts findMdCartsById(Integer suiId, Integer mcId) throws HSKDBException {
		MdCarts mdCarts = new MdCarts();
		if(mcId != null){
			mdCarts.setMcId(mcId);
			Object obj = this.getOne(mdCarts);
			if(obj != null)
				mdCarts=(MdCarts) obj;
		}
		return mdCarts;
	}

	@Override
	public List<MdCarts> findMdCartsByMmfIdHql(Integer suiId, Integer mmfId) throws HSKDBException {
		String hql = "from MdCarts where mmfId = " + mmfId + " and suiId=" + suiId;
		MdCarts mdCarts = new MdCarts();
		mdCarts.setSuiId(suiId);
		mdCarts.setMmfId(mmfId);
		List<MdCarts> list = this.getHibernateDao().find(mdCarts);
		return list;
	}

	@Override
	public List<Map<String, Object>> findMdCartsByMmfId(Integer suiId, Integer mmfId) throws HSKDBException {
		String sql = "select * from md_carts where mmf_id=" + mmfId + " and sui_id=" + suiId;
		return this.getJdbcDao().query(sql);
	}

	@Override
	public List<MdCarts> findMdCartsAll(Integer suiId) throws HSKDBException {
		String hql = "from MdCarts where suiId=" + suiId;
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		hibernateTemplate.setMaxResults(Integer.MAX_VALUE);
		return hibernateTemplate.find(hql);
	}

	public void deleteMdCartsById(Integer suiId, Integer mcId) throws HSKDBException {
		// TODO Auto-generated method stub
		MdCarts mdCarts = new MdCarts();
		if(mcId != null){
			mdCarts.setMcId(mcId);
			Object obj = this.getOne(mdCarts);
			if(obj != null)
				this.deleteObject(obj);
		}
	}

	@Override
	public void deleteMdCartsAll(Integer suiId) throws HSKDBException {
		String sql = "delete from md_carts where sui_id='"+suiId + "'";
		this.getJdbcDao().execute(sql);
	}

	@Override
	public void deleteMdCartsBySuiIdsAndMcIds(Integer suiId, String mcIds) throws HSKDBException {
		String sql = "delete from md_carts where sui_id='"+suiId + "' and mc_id in (" + mcIds + ")";
		this.getJdbcDao().execute(sql);
	}

	public MdCarts updateMdCartsById(Integer mcId,MdCarts mdCarts) throws HSKDBException {
		// TODO Auto-generated method stub
		if(mcId!=null){
			mdCarts.setMcId(mcId);
		   	Object obj=	this.getOne(mdCarts);
			if(obj!=null){							 
					this.updateObject(obj);
			}
		}
		return  mdCarts;
	}

	@Override
	public List<Map<String, Object>> getCartsCountAndPrice(Integer sui_id) throws HSKDBException {
		String sql = "select count(mc_id) as total_count, SUM(mc_count*price) as price from md_carts where sui_id=" + sui_id;
		return this.getJdbcDao().query(sql);
	}
	
	
	
	@Override
	public void deleteMdCartsBySuiIdsAndMfIds(Integer sui_id, String mmfIds) throws HSKDBException {
		String sql = "delete from md_carts where sui_id=" + sui_id;
		if(mmfIds != null && !mmfIds.equals("")){
			sql += " and mmf_id in (" + mmfIds + ")";
		}
		this.getJdbcDao().execute(sql);
	}

	public Integer saveMdCarts(MdCarts mdCarts)throws HSKDBException {
		// TODO Auto-generated method stub
		return this.newObject(mdCarts);
	}

	public MdCarts saveOrUpdateMdCarts(MdCarts mdCarts)throws HSKDBException {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(mdCarts);
		
		return mdCarts;
	}

	@Override
	public Integer getCartCountOnlyByMmfId(Integer mmfId, Integer suiId) throws HSKDBException {
		String sql = "select count(*) as count from md_carts where 1=1";
		if (mmfId != null) {
			sql += " and mmf_id = " + mmfId;
		}
		if (suiId != null) {
			sql += " and sui_id = " + suiId;
		}
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list.isEmpty()) {
			return 0;
		}
		Map<String, Object> map = list.get(0);
		if (map.isEmpty()) {
			return  0;
		}
		Integer count = Integer.parseInt(map.get("count").toString());
		return count;
	}



	public List<Map<String, Object>> getPurchasedInfo(Integer suiId) throws HSKDBException{
		String sql=" SELECT t1.purchased_id AS purchasedId, t1.wms_mi_id AS wmsMiId,t1.sui_id AS suiId,t1.price,t1.ph_state AS phState,t1.create_ren,t1.create_date,t1.edit_ren,t1.edit_date,t1.mc_count AS mcCount,t1.mmf_id AS mmfId FROM  md_purchased t1 WHERE 1=1";
		if (suiId!=null){
			sql+="  AND sui_id="+suiId;
		}
		sql+=" ORDER BY t1.purchased_id DESC";
//		if(limit != null && page != null) {
//			sql += " limit " + (page - 1) * limit + "," + limit;
//		}
		return this.getJdbcDao().query(sql);
	}
	public Integer getPurchasedMmfId(Integer mmfId,Integer suiId) throws HSKDBException{
		String sql = "select * from md_purchased where 1=1";
		if (mmfId != null)
			sql += " and mmf_id ="+mmfId;
		if (suiId!=null){
			sql += " and sui_id ="+suiId;
		}
		List<Map<String, Object>> list = this.getJdbcDao().query(sql);
		if (list == null || list.isEmpty())
			return 0;
		return list.size();
	}
	public MdPurchased findPurchasedMmfId(Integer suiId, Integer mmfId) throws HSKDBException {
		MdPurchased mdPurchased = new MdPurchased();
		if(mmfId != null&&suiId!=null){
			mdPurchased.setMmfId(mmfId);
			mdPurchased.setSuiId(suiId);
			Object obj = this.getOne(mdPurchased);
			if(obj != null)
				mdPurchased=(MdPurchased) obj;
		}
		return mdPurchased;
	}
	//查询已购商品列表2
	public List<Map<String, Object>> getPurchasedInfoList(Integer mmfId,Integer suiId,String matNameAndCode,Integer limit,Integer page,Integer date1) throws HSKDBException{
		String sql=" SELECT t2.mat_name,t1.mmf_name,t1.mmf_code,t1.price,t2.wms_mi_id,t1.mmf_id,t3.edit_date,t2.state,t3.purchased_id,(SELECT t4.root_path FROM sys_file_info t4 WHERE t4.file_code=t2.lessen_filecode ) AS lessenFilecode FROM md_materiel_format t1 LEFT JOIN md_materiel_info t2 ON t1.wms_mi_id=t2.wms_mi_id LEFT JOIN md_purchased t3 ON t1.mmf_id=t3.mmf_id WHERE 1=1";
		if (suiId!=null){
			sql+="  AND sui_id="+suiId;
		}
		if (matNameAndCode!=null&&!matNameAndCode.equals("")){
//			sql+=" AND ( t2.mat_name LIKE '%"+matNameAndCode+"%' OR t1.mmf_code LIKE '%"+matNameAndCode+"%')";
			sql+=" AND ( t2.mat_name LIKE  UPPER('%"+matNameAndCode+"%') OR t1.mmf_code LIKE UPPER('%"+matNameAndCode+"%'))";
		}
		if (date1==1){
			sql+=" ORDER BY t3.edit_date DESC";
		}else if (date1==2){
			sql+=" ORDER BY t3.edit_date ASC";
		}
		if(limit != null && page != null) {
			sql += " limit " + (page - 1) * limit + "," + limit;
		}
		return this.getJdbcDao().query(sql);
	}
	public void deletePurchasedInfoById(Integer suiId,Integer purchasedId) throws HSKDBException{
		MdPurchased mdPurchased=new MdPurchased();
		if (purchasedId!=null){
			mdPurchased.setPurchasedId(purchasedId);
			Object obj = this.getOne(mdPurchased);
			if(obj != null)
				this.deleteObject(obj);
		}
	}
}
