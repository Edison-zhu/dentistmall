package com.hsk.miniapi.dentistmall.api.daobbase.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdEnterOutCountApiDao;
import com.hsk.dentistmall.api.persistence.MdEnterOutCount;
import com.hsk.dentistmall.api.persistence.MdInventoryView;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;

@Component
public class MdEnterOutCountApiDao extends SupperDao implements IMdEnterOutCountApiDao {

	@Override
	public Integer saveMdEnterWarehouse(MdEnterOutCount att_MdEnterOutCount) throws HSKDBException {
		return this.newObject(att_MdEnterOutCount);
	}

	@Override
	public List<MdEnterOutCount> getMdEnterWarehouseList(String monthTime,MdInventoryView att_MdInventoryView) throws HSKDBException {
		String hql="from MdEnterOutCount where count_time='"+monthTime+"'";
		if(att_MdInventoryView.getRbaId() != null)
			hql += " and rbaId="+att_MdInventoryView.getRbaId();
		if(att_MdInventoryView.getRbsId() != null)
			hql += " and rbsId="+att_MdInventoryView.getRbsId();
		if(att_MdInventoryView.getRbbId() != null)
			hql += " and rbbId="+att_MdInventoryView.getRbbId();
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<Map<String, Object>> getInvemtoryEnterCount(String monthTime,MdInventoryView att_MdInventoryView) throws HSKDBException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String,Object>> reList = new ArrayList<Map<String,Object>>();
		try {
			Date x = sdf.parse(monthTime+"-01");
			Calendar cal = Calendar.getInstance();
			cal.setTime(x);
			cal.add(Calendar.MONTH, 1);
			x=cal.getTime();
			String end = sdf.format(x);
			String sql ="SELECT b.wi_id,SUM(a.number1) as quantity,SUM(a.number1*a.price) as money FROM md_enter_Warehouse_mx a,md_INVENTORY b,MD_ITEM_MX c WHERE b.ITEM_KEY_ID=c.item_key_id AND a.wms_mi_id=c.wms_mi_id AND a.mmf_id=c.mmf_id "
					+"AND a.create_date >='"+monthTime+"-01 00:00:00' AND a.create_date<='"+end+"-01 00:00:00' ";
			if(att_MdInventoryView.getRbaId() != null)
				sql += " and b.rba_id="+att_MdInventoryView.getRbaId();
			if(att_MdInventoryView.getRbsId() != null)
				sql += " and b.rbs_id="+att_MdInventoryView.getRbsId();
			if(att_MdInventoryView.getRbbId() != null)
				sql += " and b.rbb_id="+att_MdInventoryView.getRbbId();
			sql +=" GROUP BY b.wi_id";
			reList = this.getJdbcDao().query(sql);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reList;
	}

	@Override
	public List<Map<String, Object>> getNowInvemtoryCount(MdInventoryView att_MdInventoryView) throws HSKDBException {
		String sql="SELECT SUM(a.quantity) as quantity,SUM(a.quantity*a.price) as money,a.wi_id FROM MD_INVENTORY_EXTEND a,md_INVENTORY b WHERE a.wi_id=b.wi_id ";
		if(att_MdInventoryView.getRbaId() != null)
			sql += " and b.rba_id="+att_MdInventoryView.getRbaId();
		if(att_MdInventoryView.getRbsId() != null)
			sql += " and b.rbs_id="+att_MdInventoryView.getRbsId();
		if(att_MdInventoryView.getRbbId() != null)
			sql += " and b.rbb_id="+att_MdInventoryView.getRbbId();
		sql +=" GROUP BY a.wi_id";
		return this.getJdbcDao().query(sql);
	}
	
	

}
