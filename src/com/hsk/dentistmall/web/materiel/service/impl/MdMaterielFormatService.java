package com.hsk.dentistmall.web.materiel.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.internal.Objects;
import com.hsk.dentistmall.api.daobbase.IMdMaterielInfoDao;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.api.daobbase.IMdMaterielFormatDao;
import com.hsk.dentistmall.web.materiel.service.IMdMaterielFormatService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.service.imp.DSTService;

/** 
	MdMaterielFormat业务操作实现类 
* @author  作者:admin
* @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:16
*/
@Service
public class MdMaterielFormatService extends DSTService implements IMdMaterielFormatService {
	/**
	 * 业务处理dao类  mdMaterielFormatDao
	 */
	@Autowired
	protected IMdMaterielFormatDao mdMaterielFormatDao;
	@Autowired
	protected IMdMaterielInfoDao mdMaterielInfoDao;


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
	public SysRetrunMessage checkFormMmfCode(String mmfCode, Integer wmsMiId, Integer mmfId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		if(mmfCode == null && !mmfCode.equals("")){
			srm.setCode(3l);
			return srm;
		}
//		if (wmsMiId == null) {
//			srm.setCode(2l);
//			return srm;
//		}
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			if (account == null) {
				srm.setCode(4L); //未登录
				return srm;
			}
			String purchaseType = null;
			Integer wzId = null;
			if (account.getOrganizaType().equals("100")) {//供应商增加
				purchaseType = "1";
				wzId = account.getOldId();
			} else if (account.getOrganizaType().equals("20001")) {
				purchaseType = "2";
				wzId = account.getOldId();
			} else if (account.getOrganizaType().equals("20002")) {
				purchaseType = "3";
				wzId = account.getOldId();
			} else if (account.getOrganizaType().equals("20003")) {
				purchaseType = "4";
				wzId = account.getOldId();
			}
//			MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
//			mdMaterielInfo.setPurchaseType(purchaseType);
//			mdMaterielInfo.setWmsMiId(wmsMiId);
//			mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
//			if (mdMaterielInfo == null) {
//				srm.setCode(5L); // 商品非本账户可操作
//				return srm;
//			}
			List<MdMaterielFormat> att_MdMaterielFormats = mdMaterielFormatDao.findMdMaterielFormatByMffCode(mmfCode, wmsMiId, mmfId, purchaseType, wzId);
			if (att_MdMaterielFormats != null && att_MdMaterielFormats.size() > 0) {
				srm.setObj(att_MdMaterielFormats);
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

	@Override
	public SysRetrunMessage checkFormMmfName(String mmfName, Integer wmsMiId, Integer mmfId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		if(mmfName == null && !mmfName.equals("")){
			srm.setCode(3l);
			return srm;
		}
//		if (wmsMiId == null) {
//			srm.setCode(3l);
//			return srm;
//		}
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			if (account == null) {
				srm.setCode(4L); //未登录
				return srm;
			}
			String purchaseType = null;
			Integer wzId = null;
			if (account.getOrganizaType().equals("100")) {//供应商增加
				purchaseType = "1";
				wzId = account.getOldId();
			} else if (account.getOrganizaType().equals("20001")) {
				purchaseType = "2";
				wzId = account.getOldId();
			} else if (account.getOrganizaType().equals("20002")) {
				purchaseType = "3";
				wzId = account.getOldId();
			} else if (account.getOrganizaType().equals("20003")) {
				purchaseType = "4";
				wzId = account.getOldId();
			}
//			MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
//			mdMaterielInfo.setPurchaseType(purchaseType);
//			mdMaterielInfo.setWmsMiId(wmsMiId);
//			mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
//			if (mdMaterielInfo == null) {
//				srm.setCode(5L); // 商品非本账户可操作
//				return srm;
//			}
			Integer mId = null;
			if (mmfId != null)
				mId = mmfId;
			List<MdMaterielFormat> att_MdMaterielFormats = mdMaterielFormatDao.findMdMaterielFormatByMffName(mmfName, wmsMiId, mId, purchaseType, wzId);
			if (att_MdMaterielFormats != null && att_MdMaterielFormats.size() > 0) {
				srm.setObj(att_MdMaterielFormats);
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
					  MdMaterielPartDetailLogEntity mdMaterielPartDetailLogEntity = new MdMaterielPartDetailLogEntity();
					  SysUserInfo account = this.GetOneSessionAccount();
					  if(att_MdMaterielFormat.getMmfName() != null) {
					  	if (att_MdMaterielFormat.getMmfName().equals("")) {
					  		srm.setCode(3L);
					  		return srm;
						}

						  if (account == null) {
							  srm.setCode(4L); //未登录
							  return srm;
						  }
						  String purchaseType = null;
						  Integer wzId = null;
						  if (account.getOrganizaType().equals("100")) {//供应商增加
							  purchaseType = "1";
							  wzId = account.getOldId();
						  } else if (account.getOrganizaType().equals("20001")) {
							  purchaseType = "2";
							  wzId = account.getOldId();
						  } else if (account.getOrganizaType().equals("20002")) {
							  purchaseType = "3";
							  wzId = account.getOldId();
						  } else if (account.getOrganizaType().equals("20003")) {
							  purchaseType = "4";
							  wzId = account.getOldId();
						  }
//						  MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
//						  mdMaterielInfo.setPurchaseType(purchaseType);
//						  mdMaterielInfo.setWmsMiId(att_MdMaterielFormat.getWmsMiId());
//						  mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
//						  if (mdMaterielInfo == null) {
//							  srm.setCode(5L); // 商品非本账户可操作
//							  return srm;
//						  }
						  List<MdMaterielFormat> att_MdMaterielFormats = mdMaterielFormatDao.findMdMaterielFormatByMffName(att_MdMaterielFormat.getMmfName(), att_MdMaterielFormat.getWmsMiId(), att_MdMaterielFormat.getMmfId(), purchaseType, wzId);
						  if (att_MdMaterielFormats != null && att_MdMaterielFormats.size() > 0) {
							  srm.setObj(att_MdMaterielFormats);
							  srm.setCode(2L);
							  return srm;
						  }

						  att_MdMaterielFormats = mdMaterielFormatDao.findMdMaterielFormatByMffCode(att_MdMaterielFormat.getMmfCode(), att_MdMaterielFormat.getWmsMiId(), att_MdMaterielFormat.getMmfId(), purchaseType, wzId);
						  if (att_MdMaterielFormats != null && att_MdMaterielFormats.size() > 0) {
							  srm.setObj(att_MdMaterielFormats);
							  srm.setCode(3L);
							  return srm;
						  }
					  }

				  	if (att_MdMaterielFormat.getMmfId() == null) {
				  		att_MdMaterielFormat.setCreateDate(new Date());
				  		att_MdMaterielFormat.setCreateRen(account.getAccount());
						mdMaterielPartDetailLogEntity.setLogName("创建");
						mdMaterielPartDetailLogEntity.setLog("创建规格");
					} else {
				  		List<Map<String, Object>> list = mdMaterielFormatDao.getMdMaterielFormatMapList(att_MdMaterielFormat.getMmfId());
				  		if (list != null && !list.isEmpty()) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Map<String, Object> entity = list.get(0);
							if (entity.get("createRen") != null)
				  				att_MdMaterielFormat.setCreateRen(entity.get("createRen").toString());
							if (entity.get("createDate") != null && !entity.get("createDate").toString().equals(""))
				  				att_MdMaterielFormat.setCreateDate(sdf.parse(entity.get("createDate").toString()));
						}
				  		att_MdMaterielFormat.setEditDate(new Date());
				  		att_MdMaterielFormat.setEditRen(account.getAccount());
						mdMaterielPartDetailLogEntity.setLogName("修改");
						mdMaterielPartDetailLogEntity.setLog("修改规格");
					}
						mdMaterielFormatDao.saveOrUpdateMdMaterielFormat(att_MdMaterielFormat);
						if (att_MdMaterielFormat.getPrice()!=null&&!att_MdMaterielFormat.getPrice().equals("")){
							if(!Objects.equal(att_MdMaterielFormat.getPrice(), att_MdMaterielFormat.getOldPrice())){
								MdPriceLog mdPriceLog = new MdPriceLog();
								mdPriceLog.setChangeDate(new Date());
								mdPriceLog.setMmfId(att_MdMaterielFormat.getMmfId());
								mdPriceLog.setPrice(att_MdMaterielFormat.getPrice());
								mdPriceLog.setSuiId(this.GetOneSessionAccount().getSuiId());
								this.newObject(mdPriceLog);
							}
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

					  mdMaterielPartDetailLogEntity.setWmsMiId(att_MdMaterielFormat.getWmsMiId());
					  this.newObject(mdMaterielPartDetailLogEntity);
				    } catch (Exception e) {
						e.printStackTrace(); 
						throw new HSKException(e);
			        }
				return srm;	  
		}

	@Override
	public SysRetrunMessage saveOrUpdateObject1(MdMaterielFormat att_MdMaterielFormat) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			MdMaterielPartDetailLogEntity mdMaterielPartDetailLogEntity = new MdMaterielPartDetailLogEntity();
			SysUserInfo account = this.GetOneSessionAccount();

			List<MdMaterielFormat> mdMaterielFormats = mdMaterielFormatDao.findMdMaterielFormatByMffName1(null, att_MdMaterielFormat.getWmsMiId());


			if (mdMaterielFormats == null || mdMaterielFormats.isEmpty()) {
				att_MdMaterielFormat.setCreateDate(new Date());
				att_MdMaterielFormat.setCreateRen(account.getAccount());
				att_MdMaterielFormat.setMmfCode(CreateCodeUtil.getNo("MMF"));
				if (att_MdMaterielFormat.getMmfName() != null && !att_MdMaterielFormat.getMmfName().equals("")) {
					mdMaterielPartDetailLogEntity.setLogName("创建");
					mdMaterielPartDetailLogEntity.setLog("创建规格");
				}
				mdMaterielFormatDao.saveOrUpdateMdMaterielFormat(att_MdMaterielFormat);
			} else {
				MdMaterielFormat mdMaterielFormat = mdMaterielFormats.get(0);
				if (!att_MdMaterielFormat.getMmfName().equals(mdMaterielFormat.getMmfName()) && !att_MdMaterielFormat.getMmfName().equals("")) {
					mdMaterielPartDetailLogEntity.setLogName("修改");
					mdMaterielPartDetailLogEntity.setLog("修改规格");
				}
				mdMaterielFormat.setMmfName(att_MdMaterielFormat.getMmfName().equals("") ? null : att_MdMaterielFormat.getMmfName());
				mdMaterielFormatDao.saveOrUpdateMdMaterielFormat(mdMaterielFormat);
			}
			srm.setObj(att_MdMaterielFormat);
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
				for(MdMaterielFormat format : formatList) {
					if (format.getMmfName() != null)
						str += format.getMmfName() + "、";
				}
				if (str.equals("")) {
					att_MdMaterielInfo.setNorm(null);
					att_MdMaterielInfo.setMoney1(null);
				} else {
					str = str.substring(0, str.length() - 1);
					att_MdMaterielInfo.setNorm(str);
					att_MdMaterielInfo.setMoney1(0d);
				}
			}else{
				att_MdMaterielInfo.setNorm(null);
				att_MdMaterielInfo.setMoney1(null);
			}
			this.updateObject(att_MdMaterielInfo);

			if (mdMaterielPartDetailLogEntity != null) {
				mdMaterielPartDetailLogEntity.setWmsMiId(att_MdMaterielFormat.getWmsMiId());
				this.newObject(mdMaterielPartDetailLogEntity);
			}
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
				MdMaterielPartDetailLogEntity mdMaterielPartDetailLogEntity = new MdMaterielPartDetailLogEntity();
				if (state.equals("1")) {
					mdMaterielPartDetailLogEntity.setLogName("启用");
					mdMaterielPartDetailLogEntity.setLog("启用规格");
				} else {
					mdMaterielPartDetailLogEntity.setLogName("停用");
					mdMaterielPartDetailLogEntity.setLog("停用规格");
				}
				mdMaterielPartDetailLogEntity.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
				this.newObject(mdMaterielPartDetailLogEntity);
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
	public PagerModel getPagerModelObject1(MdMaterielFormat att_MdMaterielFormat) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdMaterielFormat>());
		try{
			pm=mdMaterielFormatDao.getPagerModelByMdMaterielFormat1(att_MdMaterielFormat);
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
	public PagerModel getMdMaterielFormatPagerModelByWmsMiId(Integer wmsMiId, Integer mmfId) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdMaterielFormat>());
		try{
			MdMaterielFormat att_MdMaterielFormat = new MdMaterielFormat();
			att_MdMaterielFormat.setWmsMiId(wmsMiId);
			att_MdMaterielFormat.setMmfId(mmfId);
			pm = mdMaterielFormatDao.getPagerModelByMdMaterielFormat(att_MdMaterielFormat);
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

	@Override
	public SysRetrunMessage saveOrUpdateBatchObject(Integer wmsMiId, String mmfIds, String prices, String retailPrices, String mmfCodes, String mmfNames) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			if (account == null) {
				sm.setCode(4L); //未登录
				return sm;
			}
			String purchaseType = null;
			Integer wzId = null;
			if (account.getOrganizaType().equals("100")) {//供应商增加
				purchaseType = "1";
				wzId = account.getOldId();
			} else if (account.getOrganizaType().equals("20001")) {
				purchaseType = "2";
				wzId = account.getOldId();
			} else if (account.getOrganizaType().equals("20002")) {
				purchaseType = "3";
				wzId = account.getOldId();
			} else if (account.getOrganizaType().equals("20003")) {
				purchaseType = "4";
				wzId = account.getOldId();
			}
//			MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
//			mdMaterielInfo.setPurchaseType(purchaseType);mdMaterielInfo.setWmsMiId(wmsMiId);
//			mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
//			if (mdMaterielInfo == null) {
//				sm.setCode(5L); // 商品非本账户可操作
//				return sm;
//			}

			String[] mmfIdList = mmfIds.split(",");
			String[] priceList = prices.split(",");
			String[] retailPriceList = retailPrices.split(",");
			String[] mmfCodeList = mmfCodes.split(",");
			String[] mmfNameList = mmfNames.split(",");
			if (mmfCodeList.length == 0 && mmfCodeList[0].equals("")) {
				return sm;
			}
			if (mmfNameList.length == 0 && mmfNameList[0].equals(""))
				return sm;
			if (priceList.length == 0 && priceList[0].equals(""))
				return sm;
			if (retailPriceList.length == 0 && retailPriceList.equals(""))
				return sm;
			Integer mmfId;
			Double price = 0d;
			Double retailPrice = 0d;
			String mmfCode;
			String mmfName;
			Integer idx = 0;
			List<MdMaterielFormat> att_MdMaterielFormats;
			MdMaterielFormat mdMaterielFormat;

			MdMaterielPartDetailLogEntity mdMaterielPartDetailLogEntity;


			//更新数据
			for (String mmfIdStr : mmfIdList) {
				mmfCode = mmfCodeList.length > idx ? mmfCodeList[idx] : "";
				mmfName = mmfNameList.length > idx ? mmfNameList[idx] : "";
				mdMaterielFormat = new MdMaterielFormat();
				mmfId = Integer.parseInt(mmfIdStr);
				mdMaterielFormat = mdMaterielFormatDao.findMdMaterielFormatById(mmfId);
				if (!mmfCode.equals("")) {
					att_MdMaterielFormats = mdMaterielFormatDao.findMdMaterielFormatByMffCode(mmfCodeList[idx], wmsMiId, null, purchaseType, wzId);
					if (att_MdMaterielFormats != null && att_MdMaterielFormats.size() > 0) {
						sm.setCode(2L);
						throw new HSKException("同名");
					}
					mdMaterielFormat.setMmfCode(mmfCode);
				}
				if (!mmfName.equals("")) {
					att_MdMaterielFormats = mdMaterielFormatDao.findMdMaterielFormatByMffName(mmfName, mdMaterielFormat.getWmsMiId(), mdMaterielFormat.getMmfId(), purchaseType, wzId);
					if (att_MdMaterielFormats != null && !att_MdMaterielFormats.isEmpty()) {
						sm.setCode(3L);
						throw new HSKException("同名");
					}
					if (!mmfName.equals(mdMaterielFormat.getMmfName()))
						mdMaterielFormat.setMmfName(mmfName);
				}

				mdMaterielPartDetailLogEntity = new MdMaterielPartDetailLogEntity();

				mdMaterielFormat.setMmfId(mmfId);
				if (!priceList[idx].equals("-1") && !priceList[idx].equals("")) {
					price = Double.valueOf(priceList[idx]);
					mdMaterielFormat.setPrice(price);
				}
				if (!retailPriceList[idx].equals("-1") && !retailPriceList[idx].equals("")) {
					retailPrice = Double.valueOf(retailPriceList[idx]);
					mdMaterielFormat.setRetailPrice(retailPrice);
					mdMaterielPartDetailLogEntity.setLogName("修改");
					mdMaterielPartDetailLogEntity.setLog("调价");
				}
				mdMaterielFormatDao.saveOrUpdateMdMaterielFormat(mdMaterielFormat);


//				if (state.equals("1")) {
//					mdMaterielPartDetailLogEntity.setLogName("启用");
//					mdMaterielPartDetailLogEntity.setLog("启用规格");
//				} else {
//					mdMaterielPartDetailLogEntity.setLogName("停用");
//					mdMaterielPartDetailLogEntity.setLog("停用规格");
//				}
				mdMaterielPartDetailLogEntity.setWmsMiId(mdMaterielFormat.getWmsMiId());
				this.newObject(mdMaterielPartDetailLogEntity);
				idx ++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (!e.getMessage().equals("同名"))
				sm.setCode(0L);
			e.printStackTrace();
//			throw new HSKException("同名");
		}
		return sm;
	}
}
