package com.hsk.dentistmall.api.daobbase.imp;

import java.util.List;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.springframework.stereotype.Component;

import com.hsk.dentistmall.api.daobbase.IMdItemKeyDao;
import com.hsk.dentistmall.api.persistence.MdItemKey;
import com.hsk.dentistmall.api.persistence.MdItemMx;
import com.hsk.dentistmall.api.persistence.MdItemMxView;
import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.xframe.api.utils.freeMarker.PingYinUtil;

@Component
public class MdItemKeyDao extends SupperDao implements IMdItemKeyDao {
	
	@Override
	public MdItemMxView newMdItemKey(MdItemMxView mdItemMxView, Integer linkMmfId, Integer linkWmsMiId) throws HSKDBException, BadHanyuPinyinOutputFormatCombination {
		MdItemMxView checkKey = this.getMdItemKeyView(mdItemMxView);
		if(checkKey==null){
			MdItemKey new_MdItemKey=new MdItemKey();
			new_MdItemKey.setRbaId(mdItemMxView.getRbaId());
			new_MdItemKey.setRbsId(mdItemMxView.getRbsId());
			new_MdItemKey.setRbbId(mdItemMxView.getRbbId());
			new_MdItemKey.setPurchaseType(mdItemMxView.getPurchaseType());
			new_MdItemKey.setMatName(mdItemMxView.getMatName());
			new_MdItemKey.setMmfName(mdItemMxView.getMmfName());
			String mmfNamePy = "";
			String matNamePy = "";
			if(!new_MdItemKey.getMmfName().trim().equals("")){
				for(int i =0;i < new_MdItemKey.getMmfName().trim().length();i++)
					mmfNamePy+=PingYinUtil.getLetterFormChinese(new_MdItemKey.getMmfName().trim().charAt(i));
			}
			if(!new_MdItemKey.getMatName().trim().equals("")){
				for(int i =0;i < new_MdItemKey.getMatName().trim().length();i++)
					matNamePy+=PingYinUtil.getLetterFormChinese(new_MdItemKey.getMatName().trim().charAt(i));
			}
			new_MdItemKey.setMatNamePy(matNamePy);
			new_MdItemKey.setMmfNamePy(mmfNamePy);
			this.newObject(new_MdItemKey);
			MdItemMx new_MdItemMx = new MdItemMx();
			new_MdItemMx.setItemKeyId(new_MdItemKey.getItemKeyId());
//			if (linkMmfId != null)
//				new_MdItemMx.setLinkMmfId(linkMmfId);
//			if (linkWmsMiId != null)
//				new_MdItemMx.setLinkWmsMiId(linkWmsMiId);
			new_MdItemMx.setMmfId(mdItemMxView.getMmfId());
			new_MdItemMx.setWmsMiId(mdItemMxView.getWmsMiId());
			new_MdItemMx.setIsMain(1);
			this.newObject(new_MdItemMx);
			mdItemMxView.setItemKeyId(new_MdItemKey.getItemKeyId());
		}else {
			MdItemMx new_MdItemMx = new MdItemMx();
			new_MdItemMx.setItemKeyId(checkKey.getItemKeyId());
			new_MdItemMx = (MdItemMx) this.getOne(new_MdItemMx);
//			if (linkMmfId != null)
//				new_MdItemMx.setLinkMmfId(linkMmfId);
//			if (linkWmsMiId != null)
//				new_MdItemMx.setLinkWmsMiId(linkWmsMiId);
			new_MdItemMx.setMmfId(mdItemMxView.getMmfId());
			new_MdItemMx.setWmsMiId(mdItemMxView.getWmsMiId());
			this.updateObject(new_MdItemMx);
			mdItemMxView = checkKey;
//			if (linkMmfId != null)
//				mdItemMxView.setLinkMmfId(linkMmfId);
//			if (linkWmsMiId != null)
//				mdItemMxView.setLinkWmsMiId(linkWmsMiId);
		}
		return mdItemMxView;
	}
	
	@Override
	public MdItemMxView getMdItemKeyView(MdItemMxView mdItemMxView) throws HSKDBException {
		String hql ="from MdItemMxView where 1=1";
		if(mdItemMxView !=null && mdItemMxView.getRbaId()!=null)
			hql += " and rbaId ='"+mdItemMxView.getRbaId()+"'";
		else
			hql += " and rbaId is null";
		
		if(mdItemMxView !=null && mdItemMxView.getRbsId()!=null)
			hql += " and rbsId ='"+mdItemMxView.getRbsId()+"'";
		else
			hql += " and rbsId is null";
		
		if(mdItemMxView !=null && mdItemMxView.getRbbId()!=null)
			hql += " and rbbId ='"+mdItemMxView.getRbbId()+"'";
		else
			hql += " and rbbId is null";
		
		if(mdItemMxView !=null && mdItemMxView.getPurchaseType()!=null)
			hql += " and purchaseType ='"+mdItemMxView.getPurchaseType()+"'";
		else
			hql += " and purchaseType is null";
		if(mdItemMxView !=null && mdItemMxView.getMmfId()!=null)
			hql += " and mmfId ='"+mdItemMxView.getMmfId()+"'";
		List<MdItemMxView> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
}
