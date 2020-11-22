package com.hsk.dentistmall.web.evaluate.service.imp;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage; 
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.dentistmall.web.evaluate.service.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;


/** 
  evaluate业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2018-03-13 09:18:19
 */
 
@Service
public class  MdEvaluateService extends DSTService implements IMdEvaluateService {	
   /**
   *业务处理dao类  mdEvaluateDao 
   */
	@Autowired
	protected IMdEvaluateDao mdEvaluateDao;
	
	@Autowired
	protected	IMdMaterielFormatDao mdMaterielFormatDao;
	
	@Autowired
	protected	IMdMaterielInfoDao mdMaterielInfoDao;


 /**
	 * 根据md_evaluate表主键删除MdEvaluate对象记录
     * @param  mevaId  Integer类型(md_evaluate表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer mevaId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);
			  	
			try{
		   			MdEvaluate     att_MdEvaluate= mdEvaluateDao.findMdEvaluateById( mevaId) ;
					srm.setObj(att_MdEvaluate);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	} 

    /**
	 * 根据md_evaluate表主键删除MdEvaluate对象记录
     * @param  mevaId  Integer类型(md_evaluate表主键)
	 * @throws HSKException
	 */

	public MdEvaluate findObject(Integer mevaId) throws HSKException{
			MdEvaluate  att_MdEvaluate=new MdEvaluate();		
			try{
		        att_MdEvaluate= mdEvaluateDao.findMdEvaluateById( mevaId) ;
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdEvaluate;
	}
	
	 
	 /**
	 * 根据md_evaluate表主键删除MdEvaluate对象记录
     * @param  mevaId  Integer类型(md_evaluate表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer mevaId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
				mdEvaluateDao.deleteMdEvaluateById(mevaId);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}
	
	/**
	 * 根据md_evaluate表主键删除多条MdEvaluate对象记录
     * @param  MevaIds  Integer类型(md_evaluate表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String mevaIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = mevaIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					mdEvaluateDao.deleteMdEvaluateById(Integer
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
	 * 新增或修改md_evaluate表记录 ,如果md_evaluate表主键MdEvaluate.MevaId为空就是添加，如果非空就是修改
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
     * @return MdEvaluate  修改后的MdEvaluate对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject( MdEvaluate att_MdEvaluate) throws HSKException{
			 SysRetrunMessage srm = new SysRetrunMessage(1l);
			  try{
				  
				  if(att_MdEvaluate.getMevaId()==null){
					  att_MdEvaluate.setState("1");
				 
					 SysUserInfo sui= this.GetOneSessionAccount();
					 if(sui==null){
						 srm.setCode(0l);
						  srm.setMeg("未系统登录！");
						  return srm;
					 }
//					  点评时间	evaluate_time	timestamp			FALSE	FALSE	FALSE
//					  点评人昵称	evaluate_ren	varchar(128)	128		FALSE	FALSE	FALSE
//					  点评人账号	evaluate_zh	varchar(64)	64		FALSE	FALSE	FALSE
					 att_MdEvaluate.setSuiId(sui.getSuiId());
					  att_MdEvaluate.setEvaluateTime(new Date()); 
					  att_MdEvaluate.setEvaluateRen(sui.getUserName());
					  att_MdEvaluate.setEvaluateZh(sui.getAccount());
					  att_MdEvaluate.setOrgName(sui.getOrgName());
					  att_MdEvaluate.setEvaluatePhoneNumber(sui.getPhoneNumber());
					  att_MdEvaluate.setBarCodePath(sui.getBarCodePath());
						//型号信息
//					  规格编号	mmf_code	varchar(100)	100		FALSE	FALSE	FALSE
//					  规格名称	mmf_name	varchar(100)	100		FALSE	FALSE	FALSE
//					  规格说明	remark	varchar(200)	200		FALSE	FALSE	FALSE
//					  价格	price	numeric(10,2)	10	2	FALSE	FALSE	FALSE
					  MdMaterielFormat mmft=  mdMaterielFormatDao.findMdMaterielFormatById(att_MdEvaluate.getMmfId());
					  if(mmft==null){
						  srm.setCode(0l);
						  srm.setMeg("未能找到规格信息！");
						  return srm;
					  }
					  att_MdEvaluate.setMmfCode(mmft.getMmfCode());
					  att_MdEvaluate.setMmfName(mmft.getMmfName());
					  att_MdEvaluate.setRemark(mmft.getRemark());
					  att_MdEvaluate.setPrice(mmft.getPrice());
					  //商品信息
//					  企业名称	applicant_Name	varchar(64)	64		FALSE	FALSE	FALSE
//					  法人手机号码	Phone_number	varchar(32)	32		FALSE	FALSE	FALSE
//					  企业住所地	Corporate_domicile	varchar(64)	64		FALSE	FALSE	FALSE
//					  生产厂家	product_name	varchar(100)	100		FALSE	FALSE	FALSE
//					  物料编码	mat_code	varchar(32)	32		FALSE	FALSE	FALSE
//					  物料名称	mat_name	varchar(512)	512		FALSE	FALSE	FALSE
					  MdMaterielInfo mminfo=  mdMaterielInfoDao.findMdMaterielInfoById(mmft.getWmsMiId());
					  if(mminfo==null){
						  srm.setCode(0l);
						  srm.setMeg("未能找到商品信息！");
						  return srm;
					  }
					  att_MdEvaluate.setApplicantName(mminfo.getApplicantName());
					  att_MdEvaluate.setPhoneNumber(mminfo.getPhoneNumber());
					  
					  att_MdEvaluate.setCorporateDomicile(mminfo.getCorporateDomicile());
					  
					  /**
						 * yanglei
						 * 修改字段ProductNumber 改为ProductFactory get这个字段时用到
						 */
					  att_MdEvaluate.setProductName(mminfo.getProductFactory()); 
					  att_MdEvaluate.setMatCode(mminfo.getMatCode());
					  att_MdEvaluate.setMatName(mminfo.getMatName()); 
				  }
					mdEvaluateDao.saveOrUpdateMdEvaluate(att_MdEvaluate); 
					srm.setObj(att_MdEvaluate);
			    } catch (Exception e) {
					e.printStackTrace(); 
					throw new HSKException(e);
		        }
			return srm;	  
	}
	
	 
	/**
	 *根据MdEvaluate对象作为对(md_evaluate表进行查询，获取分页对象
     * @param  att_MdEvaluate  MdEvaluate类型(md_evaluate表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdEvaluate att_MdEvaluate) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdEvaluate>());
			  try{
				  // if(att_MdEvaluate.getWmsMiId()!=null){
				  att_MdEvaluate.setTab_like("matName,mmfName,evaluateZh,evaluateRen");
					pm=mdEvaluateDao.getPagerModelByMdEvaluate(att_MdEvaluate);
				 //  } 
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
		return pm;
	}

 
	public PagerModel getEvaluateListPagerModelObject(MdEvaluate att_MdEvaluate)
			throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdEvaluate>());
		  try{ 
				pm=mdEvaluateDao.getPagerModelByCount(att_MdEvaluate); 
		    } catch (Exception e) {
				e.printStackTrace(); 
	        }
	return pm;
	}

	 
	public SysRetrunMessage getFormatList(Integer wmsMiId) throws HSKException {
		 SysRetrunMessage srm = new SysRetrunMessage(1l);
		  try{
			  MdMaterielFormat mdMaterielFormat=new MdMaterielFormat();
			  mdMaterielFormat.setWmsMiId(wmsMiId);
			  List<MdMaterielFormat> list_obj=mdMaterielFormatDao.getListByMdMaterielFormat(mdMaterielFormat);
			  srm.setObj(list_obj);
		    } catch (Exception e) {
				e.printStackTrace(); 
				throw new HSKException(e);
	        }
		return srm;	  
	} 
	 
}