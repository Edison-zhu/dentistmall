package com.hsk.miniapi.dentistmall.web.materiel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdMaterielFormatApiDao;
import com.hsk.dentistmall.api.persistence.MdInventory;
import com.hsk.dentistmall.api.persistence.MdMaterielFormat;
import com.hsk.dentistmall.api.persistence.MdMaterielInfo;
import com.hsk.dentistmall.api.persistence.MdOrderMx;
import com.hsk.dentistmall.api.persistence.MdPriceLog;
import com.hsk.miniapi.dentistmall.web.materiel.service.IMdMaterielFormatApiService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;

/** 
	MdMaterielFormat业务操作实现类 
* @author  作者:admin
* @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:16
*/
@Service
public class MdMaterielFormatApiService extends DSTApiService implements IMdMaterielFormatApiService {
	/**
	 * 业务处理dao类  mdMaterielFormatDao
	 */
	@Autowired
	protected IMdMaterielFormatApiDao mdMaterielFormatDao;


	/**
	 * 根据md_materiel_type表主键删除MdMaterielFormat对象记录
	 *
	 * @param mmfId Integer类型(md_materiel_type表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer mmfId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);

		try {
			MdMaterielFormat att_MdMaterielFormat = mdMaterielFormatDao.findMdMaterielFormatById(mmfId);
			att_MdMaterielFormat.setOldPrice(att_MdMaterielFormat.getPrice());
			srm.setObj(att_MdMaterielFormat);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
		}
		return srm;
	}

	@Override
	public SysRetrunMessage checkFormMmfCode(String mmfCode) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		if(mmfCode == null && !mmfCode.equals("")){
			srm.setCode(3l);
			return srm;
		}
		try {
			List<MdMaterielFormat> att_MdMaterielFormats = mdMaterielFormatDao.findMdMaterielFormatByMffCode(mmfCode);
			if (att_MdMaterielFormats != null && att_MdMaterielFormats.size() > 0) {
				return srm;
			}
			srm.setCode(2l);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
		}
		return srm;
	}

	/**
		 * 根据md_materiel_type表主键删除MdMaterielFormat对象记录
	     * @param  mmfId  Integer类型(md_materiel_type表主键)
		 * @throws HSKException
		 */

		public MdMaterielFormat findObject(Integer mmfId) throws HSKException{
				MdMaterielFormat  att_MdMaterielFormat=new MdMaterielFormat();		
				try{
			        att_MdMaterielFormat= mdMaterielFormatDao.findMdMaterielFormatById( mmfId) ;
			        att_MdMaterielFormat.setOldPrice(att_MdMaterielFormat.getPrice());
					} catch (HSKDBException e) {
						e.printStackTrace(); 
						throw new  HSKException(e);
					}
					return  att_MdMaterielFormat;
		}
		
		 
		 /**
		 * 根据md_materiel_type表主键删除MdMaterielFormat对象记录
	     * @param  mmfId  Integer类型(md_materiel_type表主键)
	     * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */
		public SysRetrunMessage deleteObject(Integer mmfId) throws HSKException{  
			    SysRetrunMessage srm=new SysRetrunMessage(1l);
			   try{ 
				   MdInventory mdInventory = new MdInventory();
				   	mdInventory.setMmfId(mmfId);
				   	List<MdInventory> mdInventoryList = this.getList(mdInventory);
				   	if(mdInventoryList != null && mdInventoryList.size() > 0){
				   		srm.setCode(0l);
				   		srm.setMeg("该规格下有库存信息不允许删除!");
				   		return srm;
				   	}
				   	
				   	MdOrderMx mdOrderMx = new MdOrderMx();
				   	mdOrderMx.setMmfId(mmfId);
				   	List<MdOrderMx> mdOrderMxList = this.getList(mdOrderMx);
				   	if(mdOrderMxList != null && mdOrderMxList.size() > 0){
				   		srm.setCode(0l);
				   		srm.setMeg("该规格下有订单信息不允许删除!");
				   		return srm;
				   	}
				   
				   MdMaterielFormat att_MdMaterielFormat= mdMaterielFormatDao.findMdMaterielFormatById( mmfId) ;
				   Integer wmsMiId = att_MdMaterielFormat.getWmsMiId();
				   this.deleteObjects(att_MdMaterielFormat);
				    //修改商品的规格
					MdMaterielInfo att_MdMaterielInfo = new MdMaterielInfo();
					att_MdMaterielInfo.setWmsMiId(wmsMiId);
					att_MdMaterielInfo = (MdMaterielInfo) this.getOne(att_MdMaterielInfo);
					MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
					mdMaterielFormat.setWmsMiId(wmsMiId);
					List<MdMaterielFormat> formatList=mdMaterielFormatDao.getListByMdMaterielFormat(mdMaterielFormat);
					if(formatList != null && formatList.size() > 0){
						  String str="";
						  for(MdMaterielFormat format : formatList)
							  str += format.getMmfName()+"、";
						  str = str.substring(0, str.length()-1);
						  att_MdMaterielInfo.setNorm(str);
						  att_MdMaterielInfo.setMoney1(formatList.get(0).getPrice());
					}else{
						att_MdMaterielInfo.setNorm(null);
						att_MdMaterielInfo.setMoney1(null);
					}
					this.updateObject(att_MdMaterielInfo);
					
				 } catch (Exception e) {
						e.printStackTrace(); 
						throw new  HSKException(e);
			     }
			   return srm;  
		}
		
		/**
		 * 根据md_materiel_type表主键删除多条MdMaterielFormat对象记录
	     * @param  RbaIds  Integer类型(md_materiel_type表主键)
	     * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */
		public SysRetrunMessage deleteAllObject(String mmfIds) throws HSKException{
			SysRetrunMessage srm = new SysRetrunMessage(1l);
			try {
				String[] ids = mmfIds.split(",");
				for (String did : ids) {
					if (did != null && !"".equals(did.trim())) {
						mdMaterielFormatDao.deleteMdMaterielFormatById(Integer
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
		 * 新增或修改md_materiel_type表记录 ,如果md_materiel_type表主键MdMaterielFormat.RbaId为空就是添加，如果非空就是修改
	     * @param  att_MdMaterielFormat  MdMaterielFormat类型(md_materiel_type表记录)
	     * @return MdMaterielFormat  修改后的MdMaterielFormat对象记录
		 * @throws HSKDBException
		 */
		public SysRetrunMessage saveOrUpdateObject( MdMaterielFormat att_MdMaterielFormat) throws HSKException{
				 SysRetrunMessage srm = new SysRetrunMessage(1l);
				  try{
						mdMaterielFormatDao.saveOrUpdateMdMaterielFormat(att_MdMaterielFormat); 
						if(!att_MdMaterielFormat.getPrice().equals(att_MdMaterielFormat.getOldPrice())){
							MdPriceLog mdPriceLog = new MdPriceLog();
							mdPriceLog.setChangeDate(new Date());
							mdPriceLog.setMmfId(att_MdMaterielFormat.getMmfId());
							mdPriceLog.setPrice(att_MdMaterielFormat.getPrice());
							mdPriceLog.setSuiId(this.GetOneSessionAccount().getSuiId());
							this.newObject(mdPriceLog);
						}
						//修改商品的规格
						MdMaterielInfo att_MdMaterielInfo = new MdMaterielInfo();
						att_MdMaterielInfo.setWmsMiId(att_MdMaterielFormat.getWmsMiId());
						att_MdMaterielInfo = (MdMaterielInfo) this.getOne(att_MdMaterielInfo);
						MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
						mdMaterielFormat.setWmsMiId(att_MdMaterielFormat.getWmsMiId());
						mdMaterielFormat.setState("1");
						List<MdMaterielFormat> formatList=mdMaterielFormatDao.getListByMdMaterielFormat(mdMaterielFormat);
						if(formatList != null && formatList.size() > 0){
							  String str="";
							  for(MdMaterielFormat format : formatList)
								  str += format.getMmfName()+"、";
							  str = str.substring(0, str.length()-1);
							  att_MdMaterielInfo.setNorm(str);
							  att_MdMaterielInfo.setMoney1(formatList.get(0).getPrice());
						}else{
							att_MdMaterielInfo.setNorm(null);
							att_MdMaterielInfo.setMoney1(null);
						}
						this.updateObject(att_MdMaterielInfo);
						srm.setObj(att_MdMaterielFormat);
				    } catch (Exception e) {
						e.printStackTrace(); 
						throw new HSKException(e);
			        }
				return srm;	  
		}
		
		@Override
		public SysRetrunMessage updateObjectState(Integer mmfId, String state)
				throws HSKException {
			SysRetrunMessage srm=new SysRetrunMessage(1l);
			MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
			try{
				mdMaterielFormat= mdMaterielFormatDao.findMdMaterielFormatById(mmfId) ;
				mdMaterielFormat.setState(state);
				mdMaterielFormatDao.saveOrUpdateMdMaterielFormat(mdMaterielFormat);
				//修改商品的规格
				MdMaterielInfo att_MdMaterielInfo = new MdMaterielInfo();
				att_MdMaterielInfo.setWmsMiId(mdMaterielFormat.getWmsMiId());
				att_MdMaterielInfo = (MdMaterielInfo) this.getOne(att_MdMaterielInfo);
				MdMaterielFormat mdMaterielFormat2 = new MdMaterielFormat();
				mdMaterielFormat2.setWmsMiId(mdMaterielFormat.getWmsMiId());
				mdMaterielFormat2.setState("1");
				List<MdMaterielFormat> formatList=mdMaterielFormatDao.getListByMdMaterielFormat(mdMaterielFormat2);
				if(formatList != null && formatList.size() > 0){
					  String str="";
					  for(MdMaterielFormat format : formatList)
						  str += format.getMmfName()+"、";
					  str = str.substring(0, str.length()-1);
					  att_MdMaterielInfo.setNorm(str);
					  att_MdMaterielInfo.setMoney1(formatList.get(0).getPrice());
				}else{
					att_MdMaterielInfo.setNorm(null);
					att_MdMaterielInfo.setMoney1(null);
				}
				this.updateObject(att_MdMaterielInfo);
			} catch (Exception e) {
				e.printStackTrace(); 
				throw new  HSKException(e);
			}
			return srm;
		} 
		 
		/**
		 *根据MdMaterielFormat对象作为对(md_materiel_type表进行查询，获取分页对象
	     * @param  att_MdMaterielFormat  MdMaterielFormat类型(md_materiel_type表记录)
	     * @return PagerModel  分页对象
		 * @throws HSKException 
		 */
		public PagerModel getPagerModelObject (MdMaterielFormat att_MdMaterielFormat) throws HSKException{
			PagerModel pm=new PagerModel(new ArrayList<MdMaterielFormat>());
				  try{
						pm=mdMaterielFormatDao.getPagerModelByMdMaterielFormat(att_MdMaterielFormat);
				    } catch (Exception e) {
						e.printStackTrace(); 
			        }
			return pm;
		}

		@Override
		public PagerModel getMdMaterielFormatListByWmsMiId(Integer wmsMiId)throws HSKException {
			PagerModel pm=new PagerModel(new ArrayList<MdMaterielFormat>());
			  try{
					  MdMaterielFormat att_MdMaterielFormat = new MdMaterielFormat();
					  att_MdMaterielFormat.setWmsMiId(wmsMiId);
					 List<MdMaterielFormat> list=mdMaterielFormatDao.getListByMdMaterielFormat(att_MdMaterielFormat);
					 pm.setItems(list);
					 pm.setRows(list);
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
			  return pm;
		}

		@Override
		public List<MdMaterielFormat> getFormatListByWmsMiId(Integer wmsMiId)
				throws HSKException {
			List<MdMaterielFormat> list = new ArrayList<MdMaterielFormat>();
			MdMaterielFormat att_MdMaterielFormat = new MdMaterielFormat();
			att_MdMaterielFormat.setWmsMiId(wmsMiId);
			att_MdMaterielFormat.setState("1");
			try {
				list=mdMaterielFormatDao.getListByMdMaterielFormat(att_MdMaterielFormat);
				for (MdMaterielFormat mdMaterielFormat : list){
					String mmfName = mdMaterielFormat.getMmfName();
					if(mmfName == null){
						continue;
					}
					mmfName = mmfName.replace("\"", "&quot;");
					mmfName = mmfName.replace("'", "&apos;");
					mdMaterielFormat.setMmfName(mmfName);
				}
			} catch (HSKDBException e) {
				e.printStackTrace();
			}
			return list;
		}

		@Override
		public SysRetrunMessage getFormatPriceList(String mmfIds)
				throws HSKException {
			SysRetrunMessage srm = new SysRetrunMessage(1l);
			try {
				List<Map<String,Object>> reList = new ArrayList<Map<String,Object>>();
				MdMaterielFormat att_MdMaterielFormat = new MdMaterielFormat();
				att_MdMaterielFormat.setMmfIds(mmfIds);
				List<MdMaterielFormat> list =mdMaterielFormatDao.getListByMdMaterielFormat(att_MdMaterielFormat);
				MdPriceLog mdPriceLog = new MdPriceLog();
				mdPriceLog.setMmfIds(mmfIds);
				List<MdPriceLog> logList = mdMaterielFormatDao.getListMdPriceLog(mdPriceLog);
				for(MdMaterielFormat format:list){
					Map<String,Object> reMap = new HashMap<String,Object>();
					reMap.put("name", format.getMmfName());
					List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
					for(MdPriceLog log : logList){
						if(log.getMmfId().equals(format.getMmfId())){
							 Map<String,Object> dataMap = new HashMap<String,Object>();
							 dataMap.put("time", log.getChangeDate().getTime());
							 dataMap.put("val",log.getPrice());
							 dataList.add(dataMap);
						}
					}
					if(dataList != null && dataList.size() > 0){
						Map<String,Object> nowMap = new HashMap<String,Object>();
						nowMap.put("time", (new Date()).getTime());
						nowMap.put("val",dataList.get(dataList.size()-1).get("val"));
						dataList.add(nowMap);
					}
					reMap.put("dataList", dataList);
					reList.add(reMap);
				}
				srm.setObj(reList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return srm;
		}
}
