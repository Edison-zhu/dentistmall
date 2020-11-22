package com.hsk.dentistmall.api.daobbase.imp;

import java.util.List;

import com.hsk.dentistmall.api.persistence.MdMaterielFormatView;
import org.springframework.stereotype.Component;

import com.hsk.dentistmall.api.daobbase.IMdNewsInfoDao;
import com.hsk.dentistmall.api.persistence.MdNewsInfo;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.supper.dto.comm.PagerModel;

@Component
public class MdNewsInfoDao extends SupperDao implements IMdNewsInfoDao {

	@Override
	public MdNewsInfo findMdNewsInfoById(Integer mniId)throws HSKDBException {
		MdNewsInfo mdNewsInfo = new MdNewsInfo();
		if(mniId != null){
			mdNewsInfo.setMniId(mniId);
			Object obj = this.getOne(mdNewsInfo);
			if(obj != null)
				mdNewsInfo=(MdNewsInfo) obj;
		}
		return mdNewsInfo;
	}

	@Override
	public void deleteMdNewsInfoById(Integer mniId) throws HSKDBException {
		MdNewsInfo mdNewsInfo = new MdNewsInfo();
		if(mniId != null){
			mdNewsInfo.setMniId(mniId);
			Object obj = this.getOne(mdNewsInfo);
			if(obj != null)
				this.deleteObject(obj);
		}
	}

	@Override
	public MdNewsInfo updateMdNewsInfoById(Integer mniId,MdNewsInfo mdNewsInfo) throws HSKDBException {
		if(mniId!=null){
			mdNewsInfo.setMniId(mniId);
		   	Object obj=	this.getOne(mdNewsInfo);
			if(obj!=null){							 
					this.updateObject(obj);
			}
		}
		return  mdNewsInfo;
	}

	@Override
	public Integer saveMdNewsInfo(MdNewsInfo mdNewsInfo)throws HSKDBException {
		String hql = "from MdNewsInfo where uiId="+mdNewsInfo.getUiId()+" and uiType="+mdNewsInfo.getUiType() 
				+" and orderId="+mdNewsInfo.getOrderId()+" and isView=1";
		List<MdNewsInfo> infoList = this.getHibernateTemplate().find(hql);
		if(infoList != null && infoList.size() > 0){
			for(MdNewsInfo info : infoList){
				info.setIsView(0);
				this.updateObject(info);
			}
		}
		return this.newObject(mdNewsInfo);
	}

	@Override
	public MdNewsInfo saveOrUpdateMdNewsInfo(MdNewsInfo mdNewsInfo) throws HSKDBException {
		this.getHibernateTemplate().saveOrUpdate(mdNewsInfo);
		return mdNewsInfo;
	}

	@Override
	public void updateMdNewsInfoViewState(MdNewsInfo mdNewsInfo)
			throws HSKDBException {
		String hql = "from MdNewsInfo where uiId="+mdNewsInfo.getUiId()+" and uiType="+mdNewsInfo.getUiType() 
				+" and orderId="+mdNewsInfo.getOrderId()+" and isView=1";
		List<MdNewsInfo> infoList = this.getHibernateTemplate().find(hql);
		if(infoList != null && infoList.size() > 0){
			for(MdNewsInfo info : infoList){
				info.setIsView(2);
				this.updateObject(info);
			}
		}
	}

	@Override
	public List<MdNewsInfo> getMdNewsInfoList(MdNewsInfo mdNewsInfo)
			throws HSKDBException {
		String hql = "from MdNewsInfo where 1=1";
		if(mdNewsInfo.getOrderId() != null)
			hql += " and orderId="+mdNewsInfo.getOrderId();
		if(mdNewsInfo.getUiId() != null)
			hql += " and uiId="+mdNewsInfo.getUiId();
		if(mdNewsInfo.getUiType() != null)
			hql += " and uiType="+mdNewsInfo.getUiType();
		if(mdNewsInfo.getIsView() != null)
			hql += " and isView="+mdNewsInfo.getIsView();
		if(mdNewsInfo.getNewsType() != null)
			hql += " and newsType="+mdNewsInfo.getNewsType();
		hql += " order by createDate desc";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public PagerModel getInventoryNewsPagerModel(MdNewsInfo mdNewsInfo, MdMaterielFormatView mdMaterielFormatView, String purchaseType, Integer oldId) throws HSKDBException {
		String sql ="SELECT b.*,DATE_FORMAT(a.create_date,'%Y-%m-%d %H:%i:%s') AS new_date,a.mni_id,a.is_view,c.root_path as lessen_file_path,d.quantity,d.all_number,d.max_shu,d.min_day,d.warning_shu "
				+"FROM md_news_info a,md_materiel_format_view b "
				+"left join sys_file_info c on b.lessen_filecode=c.file_code "
				+"left join md_inventory_extend_view d on d.mmf_id=b.mmf_id and d.purchase_type='"+purchaseType+"'";
		if(purchaseType.equals("1"))
			sql+=" and d.rba_id="+oldId;
		else if(purchaseType.equals("2"))
			sql+=" and d.rbs_id="+oldId;
		else if(purchaseType.equals("3"))
			sql+=" and d.rbb_id="+oldId;
		sql +=" WHERE a.order_id=b.mmf_id AND a.news_type=7";
		if(mdNewsInfo.getOrderId() != null)
			sql += " and a.order_id="+mdNewsInfo.getOrderId();
		if(mdNewsInfo.getUiId() != null)
			sql += " and a.ui_id="+mdNewsInfo.getUiId();
		if(mdNewsInfo.getUiType() != null)
			sql += " and a.ui_type="+mdNewsInfo.getUiType();
		if(mdMaterielFormatView != null && mdMaterielFormatView.getMatName() != null && !mdMaterielFormatView.getMatName().trim().equals(""))
			sql += " and mat_name like '%"+mdMaterielFormatView.getMatName().trim()+"%'";
		if(mdMaterielFormatView != null && mdMaterielFormatView.getMmfName() != null && !mdMaterielFormatView.getMmfName().trim().equals(""))
			sql += " and mmf_name like '%"+mdMaterielFormatView.getMmfName().trim()+"%'";
		if(mdMaterielFormatView != null && mdMaterielFormatView.getApplicantName() != null && !mdMaterielFormatView.getApplicantName().trim().equals(""))
			sql += " and applicant_name like '%"+mdMaterielFormatView.getApplicantName().trim()+"%'";
		sql += " and is_view in (1,2)";
		sql += " order by a.create_date desc";
		return this.getJdbcDao().findByPage(sql);
	}
}
