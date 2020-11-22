package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.util.List;

import com.hsk.exception.HSKException;
import org.springframework.stereotype.Component;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdNoBuyApiDao;
import com.hsk.dentistmall.api.persistence.MdNoBuy;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;

/**
 * md_no_buy表数据库层面操作实现类 
 * @author Administrator
 *
 */
@Component
public class MdNoBuyApiDao extends SupperDao implements IMdNoBuyApiDao {

	@Override
	public MdNoBuy findMdNoBuyById(Integer mnbId)throws HSKDBException {
		MdNoBuy mdNoBuy = new MdNoBuy();
		if(mnbId != null){
			mdNoBuy.setMnbId(mnbId);
			Object obj = this.getOne(mdNoBuy);
			if(obj != null)
				mdNoBuy=(MdNoBuy) obj;
		}
		return mdNoBuy;
	}

	@Override
	public void deleteMdNoBuyById(Integer mnbId) throws HSKDBException {
		MdNoBuy mdNoBuy = new MdNoBuy();
		if(mnbId != null){
			mdNoBuy.setMnbId(mnbId);
			Object obj = this.getOne(mdNoBuy);
			if(obj != null)
				this.deleteObject(obj);
		}
	}

	@Override
	public void deleteMdNoBuyBySuiId(Integer suiId) throws HSKDBException {
		String sql = "delete from md_no_buy where sui_id='"+suiId + "'";
		this.getJdbcDao().execute(sql);
	}

	@Override
	public void deleteMdNoBuyBySuiIdsAndMnbIds(Integer suiId, String mnbIds) throws HSKDBException {
		String sql = "delete from md_no_buy where sui_id='"+suiId + "' and mnb_id in (" + mnbIds + ")";
		this.getJdbcDao().execute(sql);
	}

	@Override
	public MdNoBuy updateMdNoBuyById(Integer mnbId,MdNoBuy mdNoBuy) throws HSKDBException {
		if(mnbId!=null){
			mdNoBuy.setMnbId(mnbId);
		   	Object obj=	this.getOne(mdNoBuy);
			if(obj!=null){							 
					this.updateObject(obj);
			}
		}
		return  mdNoBuy;
	}

	@Override
	public Integer saveMdNoBuy(MdNoBuy mdNoBuy)throws HSKDBException {
		return this.newObject(mdNoBuy);
	}

	@Override
	public MdNoBuy saveOrUpdateMdNoBuy(MdNoBuy mdNoBuy) throws HSKDBException {
		this.getHibernateTemplate().saveOrUpdate(mdNoBuy);
		return mdNoBuy;
	}

	@Override
	public PagerModel getPagerModelByMdNoBuy(MdNoBuy mdNoBuy) throws HSKDBException {
		String Hql=this.getHql(mdNoBuy);
		return this.getHibernateDao().findByPage(Hql); 
	}

	@Override
	public List<MdNoBuy> getListByMdNoBuy(MdNoBuy mdNoBuy) throws HSKDBException {
		String Hql=this.getHql( mdNoBuy); 
		List<MdNoBuy> list=this.getHibernateTemplate().find(Hql);
		return  list;
	}
	
	private String getHql(MdNoBuy mdNoBuy){
		String hql ="from MdNoBuy where 1=1";
		if(mdNoBuy != null && mdNoBuy.getSuiId() != null )
			hql += " and mdMmtId="+mdNoBuy.getSuiId();
		if(mdNoBuy != null && mdNoBuy.getMmfId() != null )
			hql += " and mmfId="+mdNoBuy.getMmfId();
		if(mdNoBuy != null && mdNoBuy.getState() != null && !mdNoBuy.getState().trim().equals("") )
			hql += " and state in ("+mdNoBuy.getState().trim()+")";
		hql +=" order by mnbId desc";
		return hql;
	}

	@Override
	public void delMdNoBuyByStrs(Integer suiId, String mmfIds)
			throws HSKDBException {
		String sql = "delete from md_no_buy where sui_id="+suiId+" and mmf_id in ("+mmfIds+")";
		this.getJdbcDao().execute(sql);
	}
	
	@Override
	public PagerModel getMdNoBuyMapPagerModel(String applicantName,
			String matName, Integer suiId) throws HSKDBException {
		String sql ="SELECT a.sui_id,a.mnb_id,b.wz_id,d.mmf_id,d.mmf_name,d.mmf_code,b.mat_name, b.applicant_name, b.brand ,b.money1 , "
				+"b.basic_unit ,b.state,b.wms_mi_id,c.root_path as 'lessen_file_path'"
                +" FROM md_no_buy a,md_materiel_format d,md_materiel_info b left join sys_file_info c on b.lessen_filecode=c.file_code"
                +" where a.mmf_id=d.mmf_id and d.wms_mi_id = b.wms_mi_id and b.state='1' and a.sui_id="+suiId;
		if(applicantName !=null && !applicantName.trim().equals(""))
			sql += " and applicant_name like '%"+applicantName.trim()+"%'";
		if(matName !=null && !matName.trim().equals(""))
			sql += " and mat_name like '%"+matName.trim()+"%'";
		if(matName !=null && !matName.trim().equals(""))
			sql += " and mat_name like '%"+matName.trim()+"%'";
		sql += " order by mnb_id";
		return this.getJdbcDao().findByPage(sql);
	}

	@Override
	public PagerModel searchMdNoBuyBySearch(Integer suiId, String searchName) throws HSKDBException {
		String sql ="SELECT a.sui_id,a.mnb_id,b.wz_id,d.mmf_id,d.mmf_name,b.mat_name, b.applicant_name, b.brand ,b.money1 , "
				+"b.basic_unit ,b.state,b.wms_mi_id,c.root_path as 'lessen_file_path'"
				+" FROM md_no_buy a left join md_materiel_format d on a.mmf_id=d.mmf_id left join md_materiel_info b on d.wms_mi_id = b.wms_mi_id left join sys_file_info c on b.lessen_filecode=c.file_code"
				+" where b.state='1' and a.sui_id="+suiId;
		if(searchName !=null && !searchName.trim().equals("")) {
			sql += " and (b.applicant_name like '%" + searchName.trim() + "%'";
			sql += " or b.mat_name like '%" + searchName.trim() + "%'";
			sql += " or upper(b.mat_name) like '%"+searchName.trim().toUpperCase()
					+"%' or b.norm like '%"+searchName.trim().toUpperCase()
					+"%' or upper(b.py_name) like '%"+searchName.trim().toUpperCase()
					+"%' or upper(b.product_name) like '%"+searchName.trim()
					+"%' or b.brand like '%"+searchName.trim()
					+"%' or b.alias_name like '%"+searchName.trim()+"%')";
//			sql += " and mat_name like '%" + searchName.trim() + "%'";
		}
		sql += " order by a.create_date";
		return this.getJdbcDao().findByPage(sql);
	}
}
