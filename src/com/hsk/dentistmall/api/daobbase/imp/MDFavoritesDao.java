package com.hsk.dentistmall.api.daobbase.imp;


import java.util.List;


import org.springframework.stereotype.Component;
import com.hsk.dentistmall.api.daobbase.IMdFavoritesDao;
import com.hsk.dentistmall.api.persistence.MdFavorites;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;
@Component
public class MDFavoritesDao extends SupperDao implements IMdFavoritesDao{

	public MdFavorites findMdFavoritesById(Integer mfId) throws HSKDBException {
		MdFavorites mdFavorites = new MdFavorites();
		if(mfId != null){
			mdFavorites.setMfId(mfId);
			Object obj = this.getOne(mdFavorites);
			if(obj != null)
				mdFavorites=(MdFavorites) obj;
		}
		return mdFavorites;
	}

	public void deleteMdFavoritesById(Integer mfId) throws HSKDBException {
		// TODO Auto-generated method stub
		MdFavorites mdFavorites = new MdFavorites();
		if(mfId != null){
			mdFavorites.setMfId(mfId);
			Object obj = this.getOne(mdFavorites);
			if(obj != null)
				this.deleteObject(obj);
		}
	}

	@Override
	public void delectAllObjectBySuiId(Integer suiId) throws HSKDBException {
		String sql = "delete from md_favorites where sui_id='"+suiId + "'";
		this.getJdbcDao().execute(sql);
	}

	@Override
	public void deleteMdFavoritesBySuiIdsAndMfIds(Integer suiId, String mfIds) throws HSKDBException {
		String sql = "delete from md_favorites where sui_id='"+suiId + "' and mf_id in (" + mfIds + ")";
		this.getJdbcDao().execute(sql);
	}
	//批量移除后台收藏数据
	@Override
	public void deleteMdCollectBySuiIdsAndMfIds(Integer suiId, String mfIds) throws HSKDBException {
		String sql = "delete from md_inventory_collect where sui_id='"+suiId + "' and mmf_id in (" + mfIds + ")";
		this.getJdbcDao().execute(sql);
	}

	//批量移除复选框后台收藏数据
	public void deleteMdCollectBySuiIdsAndMicIds(Integer suiId, String micIds) throws HSKDBException{
		String sql = "delete from md_inventory_collect where sui_id='"+suiId + "' and mic_id in (" + micIds + ")";
		this.getJdbcDao().execute(sql);
	}

	public MdFavorites updateMdFavoritesById(Integer mfId,MdFavorites mdFavorites) throws HSKDBException {
		// TODO Auto-generated method stub
		if(mfId!=null){
			mdFavorites.setMfId(mfId);
		   	Object obj=	this.getOne(mdFavorites);
			if(obj!=null){							 
					this.updateObject(obj);
			}
		}
		return  mdFavorites;
	}

	public Integer saveMdFavorites(MdFavorites mdFavorites)throws HSKDBException {
		// TODO Auto-generated method stub
		return this.newObject(mdFavorites);
	}

	public MdFavorites saveOrUpdateMdFavorites(MdFavorites mdFavorites)throws HSKDBException {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(mdFavorites);
		
		return mdFavorites;
	}

	public PagerModel getPagerModelByMdFavorites(MdFavorites mdFavorites)throws HSKDBException {
		// TODO Auto-generated method stub
		
	    String Hql = this.getHql(mdFavorites);
		
		return this.getHibernateDao().findByPage(Hql);
	}

	public List<MdFavorites> getListByMdFavorites(MdFavorites mdFavorites)throws HSKDBException {
		// TODO Auto-generated method stub
		String Hql=this.getHql( mdFavorites); 
		List<MdFavorites> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	private String getHql(MdFavorites mdFavorites){
		String hql ="";
		return hql;
	}
	
	
	
	@Override
	public PagerModel getMdFavoritesMapPagerModel(String applicantName,
			String matName, Integer suiId) throws HSKDBException {
		String sql ="SELECT a.sui_id,a.mf_id, d.mmf_code,d.mmf_name,b.wz_id,b.mat_name, b.applicant_name, b.brand ,b.money1 , b.basic_unit ,b.state,b.wms_mi_id,c.root_path as 'lessen_file_path'"
                +" FROM md_favorites a left join md_materiel_format d on a.mmf_id = d.mmf_id ,md_materiel_info b left join sys_file_info c on b.lessen_filecode=c.file_code"
                +" where a.wms_mi_id = b.wms_mi_id and b.state='1' and a.sui_id="+suiId;
		if(applicantName !=null && !applicantName.trim().equals(""))
			sql += " and applicant_name like '%"+applicantName.trim()+"%'";
		if(matName !=null && !matName.trim().equals(""))
			sql += " and mat_name like '%"+matName.trim()+"%'";
		if(matName !=null && !matName.trim().equals(""))
			sql += " and mat_name like '%"+matName.trim()+"%'";
		sql += " order by mf_id";
		return this.getJdbcDao().findByPage(sql);
	}

	@Override
	public PagerModel searchMdFavoritesBySearch(Integer suiId, String searchName) throws HSKDBException {
		String sql ="SELECT a.sui_id,a.mf_id,b.wz_id,b.mat_name, b.applicant_name, b.brand ,b.money1 , b.basic_unit ,b.state,b.wms_mi_id,c.root_path as 'lessen_file_path'"
				+" FROM md_favorites a left join md_materiel_info b on a.wms_mi_id = b.wms_mi_id left join sys_file_info c on b.lessen_filecode=c.file_code"
				+" where a.wms_mi_id = b.wms_mi_id and b.state='1' and a.sui_id="+suiId;
		if(searchName !=null && !searchName.trim().equals("")) {
			sql += " and (applicant_name like '%" + searchName.trim() + "%'";
			sql += " or mat_name like '%" + searchName.trim() + "%'";
            sql += " or upper(mat_name) like '%"+searchName.trim().toUpperCase()
                    +"%' or norm like '%"+searchName.trim().toUpperCase()
                    +"%' or upper(py_name) like '%"+searchName.trim().toUpperCase()
                    +"%' or upper(product_name) like '%"+searchName.trim()
                    +"%' or brand like '%"+searchName.trim()
                    +"%' or alias_name like '%"+searchName.trim()+"%')";
//			sql += " or mat_name like '%" + searchName.trim() + "%'";
		}
		sql += " order by a.create_date";
		return this.getJdbcDao().findByPage(sql);
	}

}
