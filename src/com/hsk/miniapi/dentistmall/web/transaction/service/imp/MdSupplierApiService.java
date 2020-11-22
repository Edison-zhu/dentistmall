package com.hsk.miniapi.dentistmall.web.transaction.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdMaterielInfoApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdOrderInfoApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdSupplierApiDao;
import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.dentistmall.api.persistence.MdSupplier;
import com.hsk.miniapi.dentistmall.web.transaction.service.IMdSupplierApiService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.IorgApiDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.PingYinUtil;


/** 
  transaction业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:17:00
 */
 
@Service
public class MdSupplierApiService extends DSTApiService implements IMdSupplierApiService {
   /**
   *业务处理dao类  mdSupplierDao 
   */
	@Autowired
	protected IMdSupplierApiDao mdSupplierDao;
	
	private Integer att_mxxOrgGxId=2;
	@Autowired
	protected IorgApiDao  orgDao;
	@Autowired
	protected IMdOrderInfoApiDao mdOrderInfoDao;
	@Autowired
	protected IMdMaterielInfoApiDao mdMaterielInfoDao;


 /**
	 * 根据md_supplier表主键删除MdSupplier对象记录
     * @param  wzId  Integer类型(md_supplier表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer wzId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);
			  	
			try{
		   			MdSupplier     att_MdSupplier= mdSupplierDao.findMdSupplierById( wzId) ;
					srm.setObj(att_MdSupplier);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	} 

    /**
	 * 根据md_supplier表主键删除MdSupplier对象记录
     * @param  wzId  Integer类型(md_supplier表主键)
	 * @throws HSKException
	 */

	public MdSupplier findObject(Integer wzId) throws HSKException{
			MdSupplier  att_MdSupplier=new MdSupplier();		
			try{
		        att_MdSupplier= mdSupplierDao.findMdSupplierById( wzId) ;
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdSupplier;
	}
	
	 
	 /**
	 * 根据md_supplier表主键删除MdSupplier对象记录
     * @param  wzId  Integer类型(md_supplier表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wzId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
			   //查看供应商下是否有未完成的订单  
			   MdOrderInfo mdOrderInfo = new MdOrderInfo();
			   mdOrderInfo.setProcessStatus_str("1,2,3,4,7");
			   mdOrderInfo.setOrderState("1");
			   mdOrderInfo.setWzId(wzId);
			   List<MdOrderInfo> orderList = mdOrderInfoDao.getListByMdOrderInfo(mdOrderInfo);
			   if(orderList != null && orderList.size() > 0){
				   srm.setCode(0l);
				   srm.setMeg("该供应商下有未完成的订单，不允许删除！");
				   return srm;
			   }
			   MdSupplier att_MdSupplier= mdSupplierDao.findMdSupplierById( wzId) ;
			   att_MdSupplier.setState("0");
			   mdSupplierDao.saveOrUpdateMdSupplier(att_MdSupplier);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}
	
	/**
	 * 根据md_supplier表主键删除多条MdSupplier对象记录
     * @param  WzIds  Integer类型(md_supplier表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wzIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = wzIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					mdSupplierDao.deleteMdSupplierById(Integer
							.valueOf(did));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			HSKException hse = new HSKException(e);
			hse.setDisposeType("01");
			throw hse;
		} catch (HSKDBException e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
		return srm;	  
	 }
	 
 
	 /**
	 * 新增或修改md_supplier表记录 ,如果md_supplier表主键MdSupplier.WzId为空就是添加，如果非空就是修改
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
     * @return MdSupplier  修改后的MdSupplier对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject( MdSupplier att_MdSupplier) throws HSKException{
			 SysRetrunMessage srm = new SysRetrunMessage(1l);
			  try{
				  Integer orgid = 1;
				  SysUserInfo sui = this.GetOneSessionAccount();
				  orgid = (sui != null) ? (sui.getOrgGxId() != null ? (sui.getOrgGxId())
							: 1) : 1;
				  	if(att_MdSupplier.getWzId() ==null){
				  		int id = mdSupplierDao.saveMdSupplier(att_MdSupplier);
				  		SysOrgGx sysOrgGx = new SysOrgGx();
						sysOrgGx.setOldId(id);
						sysOrgGx.setOrganizaType("100");
						sysOrgGx.setNodeName(att_MdSupplier.getApplicantName());
						sysOrgGx.setMxxOrgGxId((orgid == 1) ? att_mxxOrgGxId: orgid);
						orgDao.saveOrgInfo(sysOrgGx);
						att_MdSupplier.setOrgGxId(sysOrgGx.getOrgGxId());
				  	}else if(att_MdSupplier.getState().equals("2")){
				  		 //查看供应商下是否有未完成的订单  
						 MdOrderInfo mdOrderInfo = new MdOrderInfo();
						 mdOrderInfo.setProcessStatus_str("1,2,3,4,7");
						 mdOrderInfo.setOrderState("1");
						 mdOrderInfo.setWzId(att_MdSupplier.getWzId());
						 List<MdOrderInfo> orderList = mdOrderInfoDao.getListByMdOrderInfo(mdOrderInfo);
						 if(orderList != null && orderList.size() > 0){
							  srm.setCode(0l);
							  srm.setMeg("该供应商下有未完成的订单，不允许设置为禁用状态！");
							  return srm;
						  }
				  	}
				  	String pyName = "";
					if(!att_MdSupplier.getApplicantName().trim().equals("")){
						  for(int i =0;i < att_MdSupplier.getApplicantName().trim().length();i++)
							  pyName+=PingYinUtil.getLetterFormChinese(att_MdSupplier.getApplicantName().trim().charAt(i));
					}
					att_MdSupplier.setPyName(pyName);
					mdSupplierDao.saveOrUpdateMdSupplier(att_MdSupplier); 
					mdMaterielInfoDao.updateMdMaterielInfoApplicantName(att_MdSupplier.getApplicantName(), att_MdSupplier.getWzId());
					//修改商品的商家状态
					mdMaterielInfoDao.updateMatWzState(att_MdSupplier.getWzId(), att_MdSupplier.getState());
					srm.setObj(att_MdSupplier);
			    } catch (Exception e) {
					e.printStackTrace(); 
					throw new HSKException(e);
		        }
			return srm;	  
	}
	
	 
	/**
	 *根据MdSupplier对象作为对(md_supplier表进行查询，获取分页对象
     * @param  att_MdSupplier  MdSupplier类型(md_supplier表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdSupplier att_MdSupplier) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdSupplier>());
			  try{
				  if(att_MdSupplier.getState()==null || att_MdSupplier.getState().trim().equals(""))
					  att_MdSupplier.setState_str("1,2");
				  att_MdSupplier.setTab_like("legalCertificateNo,applicantName");
				  att_MdSupplier.setTab_order("wzId desc");
					pm=mdSupplierDao.getPagerModelByMdSupplier(att_MdSupplier);
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
		return pm;
	} 
	 
}