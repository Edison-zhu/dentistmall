package com.hsk.dentistmall.web.mall.service.imp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hsk.xframe.api.daobbase.ILogDao;
import com.hsk.xframe.api.daobbase.ISysFileInfoDao;
import com.hsk.xframe.api.daobbase.IorgDao;
import com.hsk.xframe.api.daobbase.imp.LogDao;
import com.hsk.xframe.api.daobbase.imp.SysFileInfoDao;
import com.hsk.xframe.api.persistence.SysFileInfo;
import com.hsk.xframe.api.persistence.SysOrgGx;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage; 
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.PingYinUtil;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.dentistmall.web.mall.service.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;

import freemarker.template.Configuration;
import freemarker.template.Template;


/** 
  mall业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-29 10:30:22
 */
 
@Service
public class  MdMaterielInfoService extends DSTService implements IMdMaterielInfoService {	
   /**
   *业务处理dao类  mdMaterielInfoDao 
   */
	@Autowired
	protected IMdMaterielInfoDao mdMaterielInfoDao;
	@Autowired
	private IMdEnclosureDao mdEnclosureDao;
	@Autowired
	protected IMdMaterielFormatDao mdMaterielFormatDao;
	@Autowired
	protected IMdInventoryDao mdInventoryDao;
	@Autowired
	protected IMdInventoryExtendDao mdInventoryExtendDao;
	@Autowired
	protected IOperatorsMaterielInfoDao operatorsMaterielInfoDao;
	@Autowired
	protected ILogDao logDao;
	@Autowired
	protected ISysFileInfoDao fileInfoDao;
	@Autowired
	protected IorgDao orgDao;

 /**
	 * 根据md_materiel_info表主键删除MdMaterielInfo对象记录
     * @param  wmsMiId  Integer类型(md_materiel_info表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer wmsMiId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);
			  	
			try{
		   			MdMaterielInfo     att_MdMaterielInfo= mdMaterielInfoDao.findMdMaterielInfoById( wmsMiId) ;
		   			MdEnclosure mdEnclosure = new MdEnclosure();
		   			mdEnclosure.setWmsMiId(wmsMiId);
		   			List<MdEnclosure> mdEnclosureList = mdEnclosureDao.getListByMdEnclosure(mdEnclosure);
		   			String listFilecode="";
		   			if(mdEnclosureList != null && mdEnclosureList.size() > 0){
		   				for(MdEnclosure obj : mdEnclosureList)
		   					listFilecode += obj.getFileCode()+",";
		   				listFilecode = listFilecode.substring(0, listFilecode.length()-1);
		   			}
		   			att_MdMaterielInfo.setListFilecode(listFilecode);
					srm.setObj(att_MdMaterielInfo);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	} 

    /**
	 * 根据md_materiel_info表主键删除MdMaterielInfo对象记录
     * @param  wmsMiId  Integer类型(md_materiel_info表主键)
	 * @throws HSKException
	 */

	public MdMaterielInfo findObject(Integer wmsMiId) throws HSKException{
			MdMaterielInfo  att_MdMaterielInfo=new MdMaterielInfo();		
			try{
			        att_MdMaterielInfo= mdMaterielInfoDao.findMdMaterielInfoById( wmsMiId) ;
			        MdEnclosure mdEnclosure = new MdEnclosure();
		   			mdEnclosure.setWmsMiId(wmsMiId);
		   			List<MdEnclosure> mdEnclosureList = mdEnclosureDao.getListByMdEnclosure(mdEnclosure);
		   			String listFilecode="";
		   			if(mdEnclosureList != null && mdEnclosureList.size() > 0){
		   				for(MdEnclosure obj : mdEnclosureList)
		   					listFilecode += obj.getFileCode()+",";
		   				listFilecode = listFilecode.substring(0, listFilecode.length()-1);
		   			}
		   			att_MdMaterielInfo.setListFilecode(listFilecode);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdMaterielInfo;
	}
	
	 
	 /**
	 * 根据md_materiel_info表主键删除MdMaterielInfo对象记录
     * @param  wmsMiId  Integer类型(md_materiel_info表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wmsMiId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
			   	MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
			   	mdMaterielFormat.setWmsMiId(wmsMiId);
			   	List<MdMaterielFormat> formatList = this.getList(mdMaterielFormat);
			   	if(formatList != null && formatList.size() > 0){
			   		srm.setCode(0l);
					srm.setMeg("此物料下由单位信息暂时无法删除!");
			   		return srm;
			   	}
			   SysUserInfo account = this.GetOneSessionAccount();
			   SysOrgGx sysOrgGx = new SysOrgGx();
			   sysOrgGx.setOrgGxId(account.getOrgGxId());
			   Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			   Integer rbaId = null;
			   Integer rbsId = null;
			   Integer rbbId = null;
			   if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
				   rbaId = account.getOldId();
			   } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
				   if (orgMap.containsKey("one")) {//如果存在上级集团
					   rbaId = Integer.parseInt(orgMap.get("one"));
				   }
				   rbsId = account.getOldId();
			   } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
				   if (orgMap.containsKey("one")) {//如果存在上级集团
					   rbaId = Integer.parseInt(orgMap.get("one"));
				   }
				   if (orgMap.containsKey("tow")) {//如果存在上级医院
					   rbsId = Integer.parseInt(orgMap.get("tow"));
				   }
				   rbbId = account.getOldId();
			   }

			   Integer count = mdMaterielInfoDao.getOutWarehouseRef(wmsMiId, rbaId, rbsId, rbbId);
			   if (count > 0) {
				   srm.setCode(0l);
				   srm.setMeg("此物料已出库暂时无法删除!");
				   return srm;
			   }
//			   	MdInventory mdInventory = new MdInventory();
//			   	mdInventory.setWmsMiId(wmsMiId);
//			   	List<MdInventory> mdInventoryList = this.getList(mdInventory);
//			   	if(mdInventoryList != null && mdInventoryList.size() > 0){
//			   		srm.setCode(0l);
//			   		srm.setMeg("此物料已被入库暂时无法删除!");
//			   		return srm;
//			   	}
				mdMaterielInfoDao.deleteMdMaterielInfoById(wmsMiId);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}

	@Override
	public SysRetrunMessage deleteObjectInventory(Integer wmsMiId) throws HSKException{
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		try{
//			MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
//			mdMaterielFormat.setWmsMiId(wmsMiId);
//			List<MdMaterielFormat> formatList = this.getList(mdMaterielFormat);
//			if(formatList != null && formatList.size() > 0){
//				srm.setCode(0l);
//				srm.setMeg("该商品下有单位信息不允许删除!");
//				return srm;
//			}
			SysUserInfo account = this.GetOneSessionAccount();
			SysOrgGx sysOrgGx = new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			Integer rbaId = null;
			Integer rbsId = null;
			Integer rbbId = null;
			if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
				rbaId = account.getOldId();
			} else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
				if (orgMap.containsKey("one")) {//如果存在上级集团
					rbaId = Integer.parseInt(orgMap.get("one"));
				}
				rbsId = account.getOldId();
			} else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
				if (orgMap.containsKey("one")) {//如果存在上级集团
					rbaId = Integer.parseInt(orgMap.get("one"));
				}
				if (orgMap.containsKey("tow")) {//如果存在上级医院
					rbsId = Integer.parseInt(orgMap.get("tow"));
				}
				rbbId = account.getOldId();
			}

			Integer count = mdMaterielInfoDao.getOutWarehouseRef(wmsMiId, rbaId, rbsId, rbbId);
			if (count > 0) {
				srm.setCode(0l);
				srm.setMeg("此物料已出库暂时无法删除!");
				return srm;
			}
//			MdInventoryExtend mdInventory = new MdInventoryExtend();
//			mdInventory.setWmsMiId(wmsMiId);
//			List<MdInventoryExtend> mdInventoryList = this.getList(mdInventory);
//			if(mdInventoryList != null && mdInventoryList.size() > 0){
//				srm.setCode(0l);
//				srm.setMeg("此物料已被入库暂时无法删除!");
//				return srm;
//			}
			mdMaterielInfoDao.deleteMdMaterielInfoById(wmsMiId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new  HSKException(e);
		}
		return srm;
	}
	
	@Override
	public SysRetrunMessage updateObjectState(Integer wmsMiId, String state)
			throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		MdMaterielInfo  att_MdMaterielInfo=new MdMaterielInfo();
		try {
			att_MdMaterielInfo = mdMaterielInfoDao.findMdMaterielInfoById(wmsMiId);
			att_MdMaterielInfo.setState(state);
			mdMaterielInfoDao.saveOrUpdateMdMaterielInfo(att_MdMaterielInfo);

			MdMaterielPartDetailLogEntity mdMaterielPartDetailLogEntity = new MdMaterielPartDetailLogEntity();
			String logName = "";
			String log = "";
			if (state.equals("1")){
				logName = "启用";
				log = "启用物料";
			} else {
				logName = "停用";
				log = "停用物料";
			}
			mdMaterielPartDetailLogEntity.setLogName(logName);
			mdMaterielPartDetailLogEntity.setLog(log);
			mdMaterielPartDetailLogEntity.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
			this.newObject(mdMaterielPartDetailLogEntity);
		} catch (HSKDBException e) {
			e.printStackTrace();
			throw new HSKException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
		return srm;
	} 
	
	/**
	 * 根据md_materiel_info表主键删除多条MdMaterielInfo对象记录
     * @param  wmsMiIds  Integer类型(md_materiel_info表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wmsMiIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			SysOrgGx sysOrgGx = new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			Integer rbaId = null;
			Integer rbsId = null;
			Integer rbbId = null;
			if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
				rbaId = account.getOldId();
			} else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
				if (orgMap.containsKey("one")) {//如果存在上级集团
					rbaId = Integer.parseInt(orgMap.get("one"));
				}
				rbsId = account.getOldId();
			} else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
				if (orgMap.containsKey("one")) {//如果存在上级集团
					rbaId = Integer.parseInt(orgMap.get("one"));
				}
				if (orgMap.containsKey("tow")) {//如果存在上级医院
					rbsId = Integer.parseInt(orgMap.get("tow"));
				}
				rbbId = account.getOldId();
			}


			String[] ids = wmsMiIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					Integer wmsMiId = Integer.valueOf(did);
					MdMaterielInfo att_MdMaterielInfo= mdMaterielInfoDao.findMdMaterielInfoById( wmsMiId) ;
					MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
				   	mdMaterielFormat.setWmsMiId(wmsMiId);
				   	List<MdMaterielFormat> formatList = this.getList(mdMaterielFormat);
				   	if(formatList != null && formatList.size() > 0){
				   		srm.setCode(0l);
				   		srm.setMeg(att_MdMaterielInfo.getMatName()+"下有单位信息不允许删除!");
				   		return srm;
				   	}
				   	MdInventory mdInventory = new MdInventory();
				   	mdInventory.setWmsMiId(wmsMiId);
				   	List<MdInventory> mdInventoryList = this.getList(mdInventory);
				   	if(mdInventoryList != null && mdInventoryList.size() > 0){
				   		srm.setCode(0l);
				   		srm.setMeg(att_MdMaterielInfo.getMatName()+"该商品下有库存信息不允许删除!");
				   		return srm;
				   	}
				   	Integer count = mdMaterielInfoDao.getOutWarehouseRef(wmsMiId, rbaId, rbsId, rbbId);
					if (count > 0) {
						srm.setCode(0l);
						srm.setMeg("此物料已出库暂时无法删除!");
						return srm;
					}
					this.deleteObjects(att_MdMaterielInfo);
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

	 @Override
	public SysRetrunMessage deleteAllObjectInventory(String wmsMiIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = wmsMiIds.split(",");
			SysUserInfo account = this.GetOneSessionAccount();
			SysOrgGx sysOrgGx = new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			Integer rbaId = null;
			Integer rbsId = null;
			Integer rbbId = null;
			if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
				rbaId = account.getOldId();
			} else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
				if (orgMap.containsKey("one")) {//如果存在上级集团
					rbaId = Integer.parseInt(orgMap.get("one"));
				}
				rbsId = account.getOldId();
			} else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
				if (orgMap.containsKey("one")) {//如果存在上级集团
					rbaId = Integer.parseInt(orgMap.get("one"));
				}
				if (orgMap.containsKey("tow")) {//如果存在上级医院
					rbsId = Integer.parseInt(orgMap.get("tow"));
				}
				rbbId = account.getOldId();
			}


			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					Integer wmsMiId = Integer.valueOf(did);
					MdMaterielInfo att_MdMaterielInfo= mdMaterielInfoDao.findMdMaterielInfoById( wmsMiId) ;
//					MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
//					mdMaterielFormat.setWmsMiId(wmsMiId);
//					List<MdMaterielFormat> formatList = this.getList(mdMaterielFormat);
//					if(formatList != null && formatList.size() > 0){
//						srm.setCode(0l);
//						srm.setMeg(att_MdMaterielInfo.getMatName()+"下有单位信息不允许删除!");
//						return srm;
//					}
					Integer count = mdMaterielInfoDao.getOutWarehouseRef(wmsMiId, rbaId, rbsId, rbbId);
					if (count > 0) {
						srm.setCode(0l);
						srm.setMeg("此物料已出库暂时无法删除!");
						return srm;
					}
//					MdInventory mdInventory = new MdInventory();
//					mdInventory.setWmsMiId(wmsMiId);
//					List<MdInventory> mdInventoryList = this.getList(mdInventory);
//					if(mdInventoryList != null && mdInventoryList.size() > 0){
//						srm.setCode(0l);
//						srm.setMeg(att_MdMaterielInfo.getMatName()+"此物料已被入库暂时无法删除!");
//						return srm;
//					}
					this.deleteObjects(att_MdMaterielInfo);
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
	 * 新增或修改md_materiel_info表记录 ,如果md_materiel_info表主键MdMaterielInfo.WmsMiId为空就是添加，如果非空就是修改
     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
     * @return MdMaterielInfo  修改后的MdMaterielInfo对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject( MdMaterielInfo att_MdMaterielInfo) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			MdMaterielPartDetailLogEntity mdMaterielPartDetailLogEntity = new MdMaterielPartDetailLogEntity();

			Integer count = mdMaterielInfoDao.getMatCodeCount(att_MdMaterielInfo.getMatCode(), att_MdMaterielInfo.getWmsMiId());
			if (count > 0) {
				srm.setCode(2L);
				return srm;
			}
			SysUserInfo account = this.GetOneSessionAccount();
			if (att_MdMaterielInfo.getWmsMiId() == null) {
				mdMaterielPartDetailLogEntity.setLogName("创建");
				mdMaterielPartDetailLogEntity.setLog("创建物料");

				if (account.getOrganizaType().equals("100")) {//供应商增加
					att_MdMaterielInfo.setPurchaseType("1");
					att_MdMaterielInfo.setApplicantName(account.getOrgName());
				} else if (account.getOrganizaType().equals("20001")) {//集团增加
					att_MdMaterielInfo.setPurchaseType("2");
				} else if (account.getOrganizaType().equals("20002"))//医院增加
					att_MdMaterielInfo.setPurchaseType("3");
				else if (account.getOrganizaType().equals("20003"))//门诊增加
					att_MdMaterielInfo.setPurchaseType("4");
				att_MdMaterielInfo.setWzId(account.getOldId());
				att_MdMaterielInfo.setState("1");
				att_MdMaterielInfo.setCreateDate(new Date());
				att_MdMaterielInfo.setCreateRen(account.getAccount());
			} else {
//				MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
//				mdMaterielInfo.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
//				mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
				String aliasName = mdMaterielInfoDao.getMdMaterielAliasName(att_MdMaterielInfo.getWmsMiId());
				String newAliasName = att_MdMaterielInfo.getAliasName();
				if (aliasName == null && newAliasName == null)
					att_MdMaterielInfo.setAliasName(null);
				else if (newAliasName.equals(""))
					att_MdMaterielInfo.setAliasName(null);
				else if (att_MdMaterielInfo.getAliasName().equals(aliasName))
					att_MdMaterielInfo.setAliasName(aliasName);
				att_MdMaterielInfo.setEditDate(new Date());
				att_MdMaterielInfo.setEditRen(account.getAccount());
				mdMaterielPartDetailLogEntity.setLogName("修改");
				mdMaterielPartDetailLogEntity.setLog("修改物料");
			}
			//保存商品类别路径:/1级ID/2级ID/3级ID
			if (att_MdMaterielInfo.getMdWmsMiId() != null)
				att_MdMaterielInfo.setMatType("/" + att_MdMaterielInfo.getMatType1() + "/" + att_MdMaterielInfo.getMatType2() + "/" + att_MdMaterielInfo.getMdWmsMiId() + "/");
			if (att_MdMaterielInfo.getWmsMiId() != null) {
				//保存商品规格信息
				MdMaterielFormat att_MdMaterielFormat = new MdMaterielFormat();
				att_MdMaterielFormat.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
				List<MdMaterielFormat> formatList = mdMaterielFormatDao.getListByMdMaterielFormat(att_MdMaterielFormat);
				if (formatList != null && formatList.size() > 0) {
					String str = "";
					for (MdMaterielFormat format : formatList)
						str += format.getMmfName() + "、";
					str = str.substring(0, str.length() - 1);
					att_MdMaterielInfo.setNorm(str);
					att_MdMaterielInfo.setMoney1(formatList.get(0).getPrice());
				}
			}
//			SysUserInfo sysUserInfo=this.GetOneSessionAccount();
//			sysUserInfo.getOrgGxId();
//			if (sysUserInfo.getOrgGxId()==1){
//				att_MdMaterielInfo.setWzState(att_MdMaterielInfo.getState());
//			}else {
				MdSupplier mdSupplier = new MdSupplier();
				mdSupplier.setWzId(att_MdMaterielInfo.getWzId());
				mdSupplier = (MdSupplier) this.getOne(mdSupplier);
				att_MdMaterielInfo.setWzState(mdSupplier.getState());
//			}
			String pyName = "";
			if (!att_MdMaterielInfo.getMatName().trim().equals("")) {
				for (int i = 0; i < att_MdMaterielInfo.getMatName().trim().length(); i++)
					pyName += PingYinUtil.getLetterFormChinese(att_MdMaterielInfo.getMatName().trim().charAt(i));
			}
			att_MdMaterielInfo.setPyName(pyName);
			att_MdMaterielInfo = mdMaterielInfoDao.saveOrUpdateMdMaterielInfo(att_MdMaterielInfo);
			MdEnclosure mdEnclosure = new MdEnclosure();
			mdEnclosure.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
			mdEnclosureDao.delMdEnclosureList(mdEnclosure);
			if (att_MdMaterielInfo.getListFilecode() != null && !att_MdMaterielInfo.getListFilecode().trim().equals("")) {
				String[] fileCodeArray = att_MdMaterielInfo.getListFilecode().trim().split(",");
				for (String fileCode : fileCodeArray) {
					MdEnclosure obj = new MdEnclosure();
					obj.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
					obj.setFileCode(fileCode);
					mdEnclosureDao.saveOrUpdateMdEnclosure(obj);
				}
			}
			srm.setObj(att_MdMaterielInfo);

//			SysUserInfo account = this.GetOneSessionAccount();
//			Integer rbaId = null;
//			Integer rbsId = null;
//			Integer rbbId = null;
//			String purchaseType = null;
//			if(account.getOrganizaType().equals("20001")){
//				rbaId = account.getOldId();
//				purchaseType = "1";
//			}else if(account.getOrganizaType().equals("20002")){
//				rbsId = account.getOldId();
//				purchaseType = "2";
//			}else if(account.getOrganizaType().equals("20003")){
//				rbbId = account.getOldId();
//				purchaseType = "3";
//			}
//			MdInventoryExtend mdInventoryExtend = new MdInventoryExtend();
//			mdInventoryExtend.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
////			List<MdInventory> list = this.getList(mdInventoryExtend);
//			mdInventoryDao.updateMdInventoryRatioByInventoryExtend(mdInventoryExtend, rbaId, rbsId, rbbId, purchaseType, att_MdMaterielInfo.getBasicCoefficent());

			mdMaterielPartDetailLogEntity.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
			this.newObject(mdMaterielPartDetailLogEntity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
		return srm;
	}

	@Override
	public SysRetrunMessage saveOrUpdateObject1(MdMaterielInfo att_MdMaterielInfo) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			MdMaterielPartDetailLogEntity mdMaterielPartDetailLogEntity = new MdMaterielPartDetailLogEntity();

			Integer count = mdMaterielInfoDao.getMatCodeCount(att_MdMaterielInfo.getMatCode(), att_MdMaterielInfo.getWmsMiId());
			if (count > 0) {
				srm.setCode(2L);
				return srm;
			}

			SysUserInfo account = this.GetOneSessionAccount();
			String purchaseType = "";
			if (account.getOrganizaType().equals("100")) {//供应商增加
				purchaseType = "1";
			} else if (account.getOrganizaType().equals("20001")) {//集团增加
				purchaseType = "2";
			} else if (account.getOrganizaType().equals("20002"))//医院增加
				purchaseType = "3";
			else if (account.getOrganizaType().equals("20003"))//门诊增加
				purchaseType = "4";
			count = mdMaterielInfoDao.getMatNameCount(att_MdMaterielInfo.getMatName(), att_MdMaterielInfo.getWmsMiId(), purchaseType, account.getOldId());
			if (count > 0) {
				srm.setCode(3L);
				return srm;
			}

			if (att_MdMaterielInfo.getWmsMiId() == null) {
				mdMaterielPartDetailLogEntity.setLogName("创建");
				mdMaterielPartDetailLogEntity.setLog("创建物料");

				if (account.getOrganizaType().equals("100")) {//供应商增加
					att_MdMaterielInfo.setPurchaseType("1");
					att_MdMaterielInfo.setApplicantName(account.getOrgName());
				} else if (account.getOrganizaType().equals("20001")) {//集团增加
					att_MdMaterielInfo.setPurchaseType("2");
				} else if (account.getOrganizaType().equals("20002"))//医院增加
					att_MdMaterielInfo.setPurchaseType("3");
				else if (account.getOrganizaType().equals("20003"))//门诊增加
					att_MdMaterielInfo.setPurchaseType("4");
				att_MdMaterielInfo.setWzId(account.getOldId());
				att_MdMaterielInfo.setState("1");
				att_MdMaterielInfo.setCreateDate(new Date());
				att_MdMaterielInfo.setCreateRen(account.getAccount());
			} else {
//				MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
//				mdMaterielInfo.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
//				mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
				SysOrgGx sysOrgGx = new SysOrgGx();
				sysOrgGx.setOrgGxId(account.getOrgGxId());
				Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
				Integer rbaId = null;
				Integer rbsId = null;
				Integer rbbId = null;
				if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
					rbaId = account.getOldId();
				} else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
					if (orgMap.containsKey("one")) {//如果存在上级集团
						rbaId = Integer.parseInt(orgMap.get("one"));
					}
					rbsId = account.getOldId();
				} else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
					if (orgMap.containsKey("one")) {//如果存在上级集团
						rbaId = Integer.parseInt(orgMap.get("one"));
					}
					if (orgMap.containsKey("tow")) {//如果存在上级医院
						rbsId = Integer.parseInt(orgMap.get("tow"));
					}
					rbbId = account.getOldId();
				}
				count = mdMaterielInfoDao.getOutWarehouseRef(att_MdMaterielInfo.getWmsMiId(), rbaId, rbsId, rbbId);
				if (count > 0) {
					srm.setCode(0l);
					srm.setMeg("此物料已出库暂时无法删除!");
					return srm;
				}
				String aliasName = mdMaterielInfoDao.getMdMaterielAliasName(att_MdMaterielInfo.getWmsMiId());
				String newAliasName = att_MdMaterielInfo.getAliasName();
				if (aliasName == null && newAliasName == null)
					att_MdMaterielInfo.setAliasName(null);
				else if (newAliasName.equals(""))
					att_MdMaterielInfo.setAliasName(null);
				else if (att_MdMaterielInfo.getAliasName().equals(aliasName))
					att_MdMaterielInfo.setAliasName(aliasName);
				att_MdMaterielInfo.setEditDate(new Date());
				att_MdMaterielInfo.setEditRen(account.getAccount());
				mdMaterielPartDetailLogEntity.setLogName("修改");
				mdMaterielPartDetailLogEntity.setLog("修改物料");
			}
			//保存商品类别路径:/1级ID/2级ID/3级ID
			if (att_MdMaterielInfo.getMdWmsMiId() != null)
				att_MdMaterielInfo.setMatType("/" + att_MdMaterielInfo.getMatType1() + "/" + att_MdMaterielInfo.getMatType2() + "/" + att_MdMaterielInfo.getMdWmsMiId() + "/");
//			MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
//			mdMaterielFormat.setMmfName(att_MdMaterielInfo.getNorm());
//			if (att_MdMaterielInfo.getWmsMiId() != null) {
//				//保存商品规格信息
//				MdMaterielFormat att_MdMaterielFormat = new MdMaterielFormat();
//				att_MdMaterielFormat.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
//				List<MdMaterielFormat> formatList = mdMaterielFormatDao.getListByMdMaterielFormat(att_MdMaterielFormat);
//				if (formatList != null && formatList.size() > 0) {
//					String str = "";
//					for (MdMaterielFormat format : formatList)
//						str += format.getMmfName() + "、";
//					str = str.substring(0, str.length() - 1);
//					att_MdMaterielInfo.setNorm(str);
//					att_MdMaterielInfo.setMoney1(formatList.get(0).getPrice());
//				}
//			}
//			SysUserInfo sysUserInfo=this.GetOneSessionAccount();
//			sysUserInfo.getOrgGxId();
//			if (sysUserInfo.getOrgGxId()==1){
//				att_MdMaterielInfo.setWzState(att_MdMaterielInfo.getState());
//			}else {
			MdSupplier mdSupplier = new MdSupplier();
			mdSupplier.setWzId(att_MdMaterielInfo.getWzId());
			mdSupplier = (MdSupplier) this.getOne(mdSupplier);
			att_MdMaterielInfo.setWzState(mdSupplier.getState());
//			}
			String pyName = "";
			if (!att_MdMaterielInfo.getMatName().trim().equals("")) {
				for (int i = 0; i < att_MdMaterielInfo.getMatName().trim().length(); i++)
					pyName += PingYinUtil.getLetterFormChinese(att_MdMaterielInfo.getMatName().trim().charAt(i));
			}
			att_MdMaterielInfo.setPyName(pyName);
			att_MdMaterielInfo = mdMaterielInfoDao.saveOrUpdateMdMaterielInfo(att_MdMaterielInfo);
			MdEnclosure mdEnclosure = new MdEnclosure();
			mdEnclosure.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
			mdEnclosureDao.delMdEnclosureList(mdEnclosure);
			if (att_MdMaterielInfo.getListFilecode() != null && !att_MdMaterielInfo.getListFilecode().trim().equals("")) {
				String[] fileCodeArray = att_MdMaterielInfo.getListFilecode().trim().split(",");
				for (String fileCode : fileCodeArray) {
					MdEnclosure obj = new MdEnclosure();
					obj.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
					obj.setFileCode(fileCode);
					mdEnclosureDao.saveOrUpdateMdEnclosure(obj);
				}
			}
			srm.setObj(att_MdMaterielInfo);

//			SysUserInfo account = this.GetOneSessionAccount();
//			Integer rbaId = null;
//			Integer rbsId = null;
//			Integer rbbId = null;
//			String purchaseType = null;
//			if(account.getOrganizaType().equals("20001")){
//				rbaId = account.getOldId();
//				purchaseType = "1";
//			}else if(account.getOrganizaType().equals("20002")){
//				rbsId = account.getOldId();
//				purchaseType = "2";
//			}else if(account.getOrganizaType().equals("20003")){
//				rbbId = account.getOldId();
//				purchaseType = "3";
//			}
//			MdInventoryExtend mdInventoryExtend = new MdInventoryExtend();
//			mdInventoryExtend.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
////			List<MdInventory> list = this.getList(mdInventoryExtend);
//			mdInventoryDao.updateMdInventoryRatioByInventoryExtend(mdInventoryExtend, rbaId, rbsId, rbbId, purchaseType, att_MdMaterielInfo.getBasicCoefficent());

			mdMaterielPartDetailLogEntity.setWmsMiId(att_MdMaterielInfo.getWmsMiId());
			this.newObject(mdMaterielPartDetailLogEntity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
		return srm;
	}

	/**
	 *根据MdMaterielInfo对象作为对(md_materiel_info表进行查询，获取分页对象
     * @param  att_MdMaterielInfo  MdMaterielInfo类型(md_materiel_info表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdMaterielInfo att_MdMaterielInfo) throws HSKException{
		PagerModel pm = new PagerModel(new ArrayList<MdMaterielInfo>());
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			if (account.getOrganizaType().equals("100")) {//供应商增加
				att_MdMaterielInfo.setPurchaseType("1");
				att_MdMaterielInfo.setWzId(account.getOldId());
			} else if (account.getOrganizaType().equals("20001")) {//集团增加
				att_MdMaterielInfo.setPurchaseType("2");
				att_MdMaterielInfo.setWzId(account.getOldId());
			} else if (account.getOrganizaType().equals("20002")) {//医院增加
				att_MdMaterielInfo.setPurchaseType("3");
				att_MdMaterielInfo.setWzId(account.getOldId());
			} else if (account.getOrganizaType().equals("20003")) {//门诊增加
				att_MdMaterielInfo.setPurchaseType("4");
				att_MdMaterielInfo.setWzId(account.getOldId());
			} else//管理员查看所有供应商商品
				att_MdMaterielInfo.setPurchaseType("1");
			if (att_MdMaterielInfo.getMatCode()!=null&&!att_MdMaterielInfo.getMatCode().equals("")){
				String WmsMiId=mdMaterielInfoDao.getMdMaterielformat(att_MdMaterielInfo.getMatCode());
					att_MdMaterielInfo.setMmfCodeWmsMiId(WmsMiId);
			}
			att_MdMaterielInfo.setTab_like("matCode,matName,applicantName,matType,labelInfo");
			pm = mdMaterielInfoDao.getPagerModelByMdMaterielInfo(att_MdMaterielInfo);

			List<MdMaterielInfo> list = pm.getItems();
			List<MdMaterielFormat> formatList = new ArrayList<>();
			MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
			Integer wmsMiId = 0;
			for (MdMaterielInfo mdMaterielInfo : list) {
				wmsMiId = mdMaterielInfo.getWmsMiId();
				mdMaterielFormat.setWmsMiId(wmsMiId);
				formatList = this.getList(mdMaterielFormat); // mdMaterielFormatDao.getListByMdMaterielFormat(mdMaterielFormat);
				mdMaterielInfo.setNormList(formatList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public PagerModel getMyPagerModelObject(MdMaterielFormatView mdMaterielFormatView)
			throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdMaterielFormatView>());
		  try{
			  SysUserInfo account = this.GetOneSessionAccount();
			  Integer rbaId = null;
			  Integer rbsId = null;
			  Integer rbbId = null;
			  if (account.getOrganizaType().equals("100")) {//供应商增加

				  mdMaterielFormatView.setPurchaseType("1");
			  } else if (account.getOrganizaType().equals("20001")) {//集团增加
				  rbaId = account.getOldId();
				  mdMaterielFormatView.setPurchaseType("2");
			  } else if (account.getOrganizaType().equals("20002")) {//医院增加
				  rbsId = account.getOldId();
				  mdMaterielFormatView.setPurchaseType("3");
			  } else if (account.getOrganizaType().equals("20003")) {//门诊增加
				  rbbId = account.getOldId();
				  mdMaterielFormatView.setPurchaseType("4");
			  }
			  mdMaterielFormatView.setWzId(account.getOldId());
			  mdMaterielFormatView.setState("1");
			  pm = mdMaterielInfoDao.getPagerModelByMdMdMaterielFormatView(mdMaterielFormatView);

			  List<Map<String, Object>> list = pm.getItems();
			  Map<String, Object> map;
			  Double baseNumber = 0d;
			  Double quantity = 0d;
			  Double ratio = 0d;
			  Double avgPrice = 0d;

			  String purchaseTypes = "";
			  List<Integer> purchaseList = new ArrayList<>();
			  String mmfIds = "";
			  String wmsMiIds = "";
			  List<Integer> mmfIdList = new ArrayList<>();
			  List<Integer> wmsMiIdList = new ArrayList<>();
			  for (Map<String, Object> mdMaterielFormatView1 : list) {
			  	if (mdMaterielFormatView1.get("purchaseType") != null && !mdMaterielFormatView1.get("purchaseType").equals(""))
				  	purchaseList.add(Integer.parseInt(mdMaterielFormatView1.get("purchaseType").toString()) - 1);
			  	if (mdMaterielFormatView1.get("mmfId") != null && !mdMaterielFormatView1.get("mmfId").toString().equals(""))
			  		mmfIdList.add(Integer.parseInt(mdMaterielFormatView1.get("mmfId").toString()));
			  	if (mdMaterielFormatView1.get("wmsMiId") != null && !mdMaterielFormatView1.get("wmsMiId").toString().equals(""))
			  		wmsMiIdList.add(Integer.parseInt(mdMaterielFormatView1.get("wmsMiId").toString()));
			  }
			  if (purchaseList != null && !purchaseList.isEmpty()) {
			  	purchaseTypes = StringUtils.join(purchaseList.toArray(), ",");
			  }
			  if (mmfIdList != null && !mmfIdList.isEmpty()) {
			  	mmfIds = StringUtils.join(mmfIdList.toArray(), ",");
			  }
			  if (wmsMiIdList != null && !wmsMiIdList.isEmpty())
			  	wmsMiIds = StringUtils.join(wmsMiIdList.toArray(), ",");


			  List<Map<String, Object>> fileList = mdMaterielInfoDao.getLessenFilePath(wmsMiIds, null, null, null, null);
			  Integer wmsMiId = null;
			  for (Map<String, Object> mdMaterielFormatView1 : list) {
			  	for (Map<String, Object> maps : fileList) {
					if (maps.get("wmsMiId") == null && maps.get("wmsMiId").toString().trim().equals("")) {

					} else {
						wmsMiId = Integer.parseInt(maps.get("wmsMiId").toString());
						if (Objects.equals(mdMaterielFormatView1.get("wmsMiId"), wmsMiId)) {
							mdMaterielFormatView1.put("lessenFilePath", maps.get("lessenFilePath").toString());
							break;
						}
					}
				}
			  }

			  List<Map<String, Object>> mapList = mdInventoryExtendDao.getMdInventoryBaseNumberAndAvgPriceByList(purchaseTypes, mmfIds, rbaId, rbsId, rbbId);

			  Integer mmfId = null;
			  for (Map<String, Object> mdMaterielFormatView1 : list) {
				  mdMaterielFormatView1.put("avgPrice", mdMaterielFormatView1.get("price"));
				  for (Map<String, Object> maps : mapList) {
					  if (maps.isEmpty() || maps.get("mmf_id") == null)
						  continue;
					  mmfId = Integer.parseInt(maps.get("mmf_id").toString());

					  if (mdMaterielFormatView1.get("mmfId") != null && !mdMaterielFormatView1.get("mmfId").toString().equals("") && Objects.equals(Integer.parseInt(mdMaterielFormatView1.get("mmfId").toString()), mmfId)) {
						  if (maps.get("base_number") != null && !maps.get("base_number").toString().equals("")) {
							  baseNumber = Double.parseDouble(maps.get("base_number").toString());
							  mdMaterielFormatView1.put("baseNumber", (baseNumber));
						  }
						  if (maps.get("quantity") != null && !maps.get("quantity").toString().equals("")) {
							  quantity = Double.parseDouble(maps.get("quantity").toString());
							  mdMaterielFormatView1.put("quantity1", quantity);
						  }
						  if (maps.get("ratio") != null && !maps.get("ratio").toString().equals("")) {
							  ratio = Double.parseDouble(maps.get("ratio").toString());
							  mdMaterielFormatView1.put("ratio", ratio);
						  }
						  if (maps.get("avg_price") != null && !maps.get("avg_price").toString().equals("")) {
							  avgPrice = Double.parseDouble(maps.get("avg_price").toString());
							  mdMaterielFormatView1.put("avgPrice", avgPrice);
						  } else {
							  mdMaterielFormatView1.put("avgPrice", mdMaterielFormatView1.get("price"));
						  }
						  if (maps.get("split_quantity_sum") != null && !maps.get("split_quantity_sum").toString().equals("")) {
							  mdMaterielFormatView1.put("splitQuantitySum", Double.parseDouble(maps.get("split_quantity_sum").toString()));
						  }
						  break;
					  }
				  }
			  }


//			  List<MdMaterielFormatView> list = pm.getItems();
//			  MdInventoryView att_MdInventoryView;
//			  for (MdMaterielFormatView mdMaterielFormatView1 : list) {
//				  att_MdInventoryView = new MdInventoryView();
//				  att_MdInventoryView.setMatName(mdMaterielFormatView1.getMatName());
//				  if (account.getOrganizaType().equals("20001")) {
//					  att_MdInventoryView.setRbaId(account.getOldId());
//					  att_MdInventoryView.setPurchaseType("1");
//				  } else if (account.getOrganizaType().equals("20002")) {
//					  att_MdInventoryView.setRbsId(account.getOldId());
//					  att_MdInventoryView.setPurchaseType("2");
//
//				  } else if (account.getOrganizaType().equals("20003")) {
//					  att_MdInventoryView.setRbbId(account.getOldId());
//					  att_MdInventoryView.setPurchaseType("3");
//				  }
//				  Double allInve = mdInventoryDao.getPagerModelAllIventoryByMdInventoryView(att_MdInventoryView);
//				  mdMaterielFormatView1.setAllInventory(allInve);
//			  }

		    } catch (Exception e) {
				e.printStackTrace(); 
	        }
		return pm;
	}

	@Override
	public PagerModel getMyPagerModelObjectWithString(String wmsMiIds, String mmfIds) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdMaterielFormatView>());
		try{
			MdMaterielFormatView mdMaterielFormatView = new MdMaterielFormatView();
			SysUserInfo account = this.GetOneSessionAccount();
			Integer rbaId = null;
			Integer rbsId = null;
			Integer rbbId = null;
			if (account.getOrganizaType().equals("100")) {//供应商增加

				mdMaterielFormatView.setPurchaseType("1");
			} else if (account.getOrganizaType().equals("20001")) {//集团增加
				rbaId = account.getOldId();
				mdMaterielFormatView.setPurchaseType("2");
			} else if (account.getOrganizaType().equals("20002")) {//医院增加
				rbsId = account.getOldId();
				mdMaterielFormatView.setPurchaseType("3");
			} else if (account.getOrganizaType().equals("20003")) {//门诊增加
				rbbId = account.getOldId();
				mdMaterielFormatView.setPurchaseType("4");
			}
			mdMaterielFormatView.setWzId(account.getOldId());
			mdMaterielFormatView.setState("1");
			mdMaterielFormatView.setWmsMiIds(wmsMiIds);
			mdMaterielFormatView.setMmfIds(mmfIds);
			pm = mdMaterielInfoDao.getPagerModelByMdMdMaterielFormatView(mdMaterielFormatView);

			List<Map<String, Object>> list = pm.getItems();
			Map<String, Object> map;
			Double baseNumber = 0d;
			Double quantity = 0d;
			Double ratio = 0d;
			Double avgPrice = 0d;

			String purchaseTypes = "";
			List<Integer> purchaseList = new ArrayList<>();
			String mmfIds1 = "";
			String wmsMiIds1 = "";
			List<Integer> mmfIdList = new ArrayList<>();
			List<Integer> wmsMiIdList = new ArrayList<>();
			for (Map<String, Object> mdMaterielFormatView1 : list) {
				if (mdMaterielFormatView1.get("purchaseType") != null && !mdMaterielFormatView1.get("purchaseType").equals(""))
					purchaseList.add(Integer.parseInt(mdMaterielFormatView1.get("purchaseType").toString()) - 1);
				if (mdMaterielFormatView1.get("mmfId") != null && !mdMaterielFormatView1.get("mmfId").toString().equals(""))
					mmfIdList.add(Integer.parseInt(mdMaterielFormatView1.get("mmfId").toString()));
				if (mdMaterielFormatView1.get("wmsMiId") != null && !mdMaterielFormatView1.get("wmsMiId").toString().equals(""))
					wmsMiIdList.add(Integer.parseInt(mdMaterielFormatView1.get("wmsMiId").toString()));
			}
			if (purchaseList != null && !purchaseList.isEmpty()) {
				purchaseTypes = StringUtils.join(purchaseList.toArray(), ",");
			}
			if (mmfIdList != null && !mmfIdList.isEmpty()) {
				mmfIds1 = StringUtils.join(mmfIdList.toArray(), ",");
			}

			if (wmsMiIdList != null && !wmsMiIdList.isEmpty())
				wmsMiIds1 = StringUtils.join(wmsMiIdList.toArray(), ",");


			List<Map<String, Object>> fileList = mdMaterielInfoDao.getLessenFilePath(wmsMiIds1, null, null, null, null);
			Integer wmsMiId = null;
			for (Map<String, Object> mdMaterielFormatView1 : list) {
				for (Map<String, Object> maps : fileList) {
					if (maps.get("wmsMiId") == null && maps.get("wmsMiId").toString().trim().equals("")) {

					} else {
						wmsMiId = Integer.parseInt(maps.get("wmsMiId").toString());
						if (Objects.equals(mdMaterielFormatView1.get("wmsMiId"), wmsMiId)) {
							mdMaterielFormatView1.put("lessenFilePath", maps.get("lessenFilePath").toString());
							break;
						}
					}
				}
			}

			List<Map<String, Object>> mapList = mdInventoryExtendDao.getMdInventoryBaseNumberAndAvgPriceByList(purchaseTypes, mmfIds1, rbaId, rbsId, rbbId);

			Integer mmfId = null;
			for (Map<String, Object> mdMaterielFormatView1 : list) {
				mdMaterielFormatView1.put("avgPrice", mdMaterielFormatView1.get("price"));
				for (Map<String, Object> maps : mapList) {
					if (maps.isEmpty() || maps.get("mmf_id") == null)
						continue;
					mmfId = Integer.parseInt(maps.get("mmf_id").toString());

					if (mdMaterielFormatView1.get("mmfId") != null && !mdMaterielFormatView1.get("mmfId").toString().equals("") && Objects.equals(Integer.parseInt(mdMaterielFormatView1.get("mmfId").toString()), mmfId)) {
						if (maps.get("base_number") != null && !maps.get("base_number").toString().equals("")) {
							baseNumber = Double.parseDouble(maps.get("base_number").toString());
							mdMaterielFormatView1.put("baseNumber", (baseNumber));
						}
						if (maps.get("quantity") != null && !maps.get("quantity").toString().equals("")) {
							quantity = Double.parseDouble(maps.get("quantity").toString());
							mdMaterielFormatView1.put("quantity1", quantity);
						}
						if (maps.get("ratio") != null && !maps.get("ratio").toString().equals("")) {
							ratio = Double.parseDouble(maps.get("ratio").toString());
							mdMaterielFormatView1.put("ratio", ratio);
						}
						if (maps.get("avg_price") != null && !maps.get("avg_price").toString().equals("")) {
							avgPrice = Double.parseDouble(maps.get("avg_price").toString());
							mdMaterielFormatView1.put("avgPrice", avgPrice);
						} else {
							mdMaterielFormatView1.put("avgPrice", mdMaterielFormatView1.get("price"));
						}
						if (maps.get("split_quantity_sum") != null && !maps.get("split_quantity_sum").toString().equals("")) {
							mdMaterielFormatView1.put("splitQuantitySum", Double.parseDouble(maps.get("split_quantity_sum").toString()));
						}
						break;
					}
				}
			}
//
//			for (MdMaterielFormatView mdMaterielFormatView1 : list) {
//				map = mdInventoryExtendDao.getMdInventoryBaseNumberAndAvgPrice(mdMaterielFormatView1, rbaId, rbsId, rbbId);
//				if (map == null || map.isEmpty())
//					continue;
//				if (map.get("base_number") != null && !map.get("base_number").toString().equals("")) {
//					baseNumber = Double.parseDouble(map.get("base_number").toString());
//					mdMaterielFormatView1.setBaseNumber(baseNumber);
//				}
//				if (map.get("avg_price") != null && !map.get("avg_price").toString().equals("")) {
//					avgPrice = Double.parseDouble(map.get("avg_price").toString());
//					mdMaterielFormatView1.setAvgPrice(avgPrice);
//				} else {
//					mdMaterielFormatView1.setAvgPrice(mdMaterielFormatView1.getPrice());
//				}
//
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public PagerModel getMyPagerModelObjectBySomeCase(MdMaterielInfo mdMaterielInfo) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList());
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			Integer rbaId = null;
			Integer rbsId = null;
			Integer rbbId = null;
			String purchaseType = null;
			if (account.getOrganizaType().equals("20001")) {//集团增加
				rbaId = account.getOldId();
				purchaseType = "2";
			} else if (account.getOrganizaType().equals("20002")) {//医院增加
				rbsId = account.getOldId();
				purchaseType = "3";
			} else if (account.getOrganizaType().equals("20003")) {//门诊增加
				rbbId = account.getOldId();
				purchaseType = "4";
			}
			mdMaterielInfo.setPurchaseType(purchaseType);
			mdMaterielInfo.setWzId(account.getOldId());
			pm = mdMaterielInfoDao.getPagerModelByMdMaterielInfoBySomeCase(mdMaterielInfo, rbaId, rbsId, rbbId);
			List<Map<String, Object>> list = pm.getItems();
			String mmfIds = "";
			for (Map<String, Object> map : list) {
				if (map.get("mmfId") != null && !map.get("mmfId").toString().equals(""))
					mmfIds += map.get("mmfId").toString() + ",";
			}
			if (!mmfIds.equals("")) {
				Object obj = null;
				Integer mmfId = null;
				mmfIds = mmfIds.substring(0, mmfIds.length() - 1);
				List<Map<String, Object>> quantities = mdMaterielInfoDao.getInventoryQuantity(mmfIds, rbaId, rbsId, rbbId);
				for (Map<String, Object> map : quantities) {
					obj = map.get("mmfId");
					mmfId = (obj != null && !obj.toString().equals("")) ? Integer.parseInt(obj.toString()) : null;
					for (Map<String, Object> entity : list) {
						if (entity.get("mmfId") != null && !entity.get("mmfId").toString().equals("") && Objects.equals(mmfId, Integer.parseInt(entity.get("mmfId").toString()))) {
							entity.put("quantity", map.get("quantity"));
							entity.put("baseNumber", map.get("baseNumber"));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public SysRetrunMessage getPagerModelAllIventory(String matNames) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			MdMaterielInfo att_MdInventoryView = new MdMaterielInfo();
			att_MdInventoryView.setWmsMiId_str(matNames);
			String[] names = matNames.split(",");
			List<Double> list = new ArrayList<Double>();
			List<MdMaterielInfo> mdInventoryViews = mdMaterielInfoDao.getListByMdMaterielInfo(att_MdInventoryView);

			String matName = "";
			for (MdMaterielInfo mdInventoryView : mdInventoryViews) {
				if (matName.indexOf(mdInventoryView.getMatName() + ",") >= 0){
					continue;
				}
				matName += mdInventoryView.getMatName() + ",";

			}
			if (!matName.equals("")) {
				MdInventoryView mdInventoryView = new MdInventoryView();
				if (account.getOrganizaType().equals("20001")) {
					mdInventoryView.setRbaId(account.getOldId());
					mdInventoryView.setPurchaseType("1");
				} else if (account.getOrganizaType().equals("20002")) {
					mdInventoryView.setRbsId(account.getOldId());
					mdInventoryView.setPurchaseType("2");

				} else if (account.getOrganizaType().equals("20003")) {
					mdInventoryView.setRbbId(account.getOldId());
					mdInventoryView.setPurchaseType("3");
				}
				matName = matName.substring(0, matName.length() - 1);
				List<Map<String, Object>> mapList = mdInventoryDao.getPagerModelAllIventoryByMdInventoryViewMatName(mdInventoryView, matName);
				boolean needZero = false;
				for (String name : names) {
					for (Map<String, Object> map : mapList){
						if (map.get("wms_mi_id2").toString().equals(name)) {
							needZero = false;
							list.add(Double.parseDouble(map.get("allCount").toString() == null ? "0" : map.get("allCount").toString()));
						} else {
							needZero = true;
						}
					}
					if (needZero) {
						list.add(0d);
					}
				}
			}
			sm.setObj(list);
		}catch (Exception e){
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public List<MdMaterielInfo> getHotMdMaterielInfoListBySize(MdMaterielInfo att_MdMaterielInfo, Integer size)throws HSKException {
		List<MdMaterielInfo> reList = new ArrayList<MdMaterielInfo>();
		att_MdMaterielInfo.setState("1");
		att_MdMaterielInfo.setTab_order("number1 desc");
		try {
			List<MdMaterielInfo> list = mdMaterielInfoDao.getListByMdMaterielInfo(att_MdMaterielInfo);
			if(list != null && list.size() > 0){
				for(int i=0;i < size && i < list.size();i++){
					reList.add(list.get(i));
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return reList;
	}

	@Override
	public List<MdMaterielInfo> getMdMaterielInfoByLabelInfo(String labelInfo)
			throws HSKException {
		List<MdMaterielInfo> reList = new ArrayList<MdMaterielInfo>();
		if(labelInfo != null && !labelInfo.trim().equals("")){
			MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
			mdMaterielInfo.setLabelInfo(labelInfo.trim());
			mdMaterielInfo.setState("1");
			mdMaterielInfo.setTab_like("labelInfo");
			mdMaterielInfo.setTab_order("labelInfo");
			try {
				String lableNames=",";
				List<MdMaterielInfo>  list = mdMaterielInfoDao.getListByMdMaterielInfo(mdMaterielInfo);
				if(list != null && list.size() > 0){
					for(MdMaterielInfo info : list){
						if(!lableNames.contains(","+info.getLabelInfo()+",")){
							lableNames+=info.getLabelInfo()+",";
							reList.add(info);
						}
					}
				}
			} catch (HSKDBException e) {
				e.printStackTrace();
			}
		}
		return reList;
	}

	@Override
	public SysRetrunMessage exportList(MdMaterielFormatView mdMaterielFormatView)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			  SysUserInfo account = this.GetOneSessionAccount();
			  if (account == null) {
			  	srm.setCode(2L);
			  	return srm;
			  }
			  if(account.getOrganizaType().equals("100")){//供应商增加
				  mdMaterielFormatView.setPurchaseType("1");
				  mdMaterielFormatView.setWzId(account.getOldId());
			  }else if(account.getOrganizaType().equals("20001")){//集团增加
				  mdMaterielFormatView.setPurchaseType("2");
				  mdMaterielFormatView.setWzId(account.getOldId());
			  }else if(account.getOrganizaType().equals("20002")){//医院增加
				  mdMaterielFormatView.setPurchaseType("3");
				  mdMaterielFormatView.setWzId(account.getOldId());
			  }else if(account.getOrganizaType().equals("20003")){//门诊增加
				  mdMaterielFormatView.setPurchaseType("4");
				  mdMaterielFormatView.setWzId(account.getOldId());
			  }else//管理员查看所有供应商商品
				  mdMaterielFormatView.setPurchaseType("1");
			  mdMaterielFormatView.setState("1");
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			  DecimalFormat df= new DecimalFormat("######0.00");   
			  Map<String, Object> dataMap = new HashMap<String,Object>();
			  int rowCount=3;
			  mdMaterielFormatView.setTypeName(mdMaterielFormatView.getLabelInfo());
			  List<MdMaterielFormatView> list = mdMaterielInfoDao.getListModelByMdMdMaterielFormatView(mdMaterielFormatView);
			  List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
			  int index=0;
			  
			  if(list != null && list.size() > 0){
				  for(MdMaterielFormatView obj: list){
					  index++;
					  Map<String,String> reMap=new HashMap<String,String>();
					  reMap.put("index", index+"");
					  reMap.put("matName", obj.getMatName()!=null?obj.getMatName():"");
					  reMap.put("applicantName", obj.getApplicantName()!=null?obj.getApplicantName():"");
					  reMap.put("brand", obj.getBrand()!=null?obj.getBrand():"");
					  reMap.put("productName", obj.getProductName()!=null?obj.getProductName():"");
					  reMap.put("typeName", obj.getTypeName()!=null?obj.getTypeName():"");
					  reMap.put("basicUnit", obj.getBasicUnit()!=null?obj.getBasicUnit():"");
					  reMap.put("mmfName", obj.getMmfName()!=null?obj.getMmfName():"");
					  reMap.put("price", obj.getPrice()!=null?obj.getPrice().toString():"");
//					  obj.getMmfId();
					  reMap.put("mmfCode", obj.getMmfCode()!=null?obj.getMmfCode():"");
//					  mdma
					  reList.add(reMap);
				  }
				  rowCount+=list.size();
			  }
			  dataMap.put("rowCount", rowCount);
			  dataMap.put("mxList", reList);
				Configuration configuration = new Configuration();
				configuration.setDefaultEncoding("UTF-8");
				// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以servlet，classpath，数据库装载，
				// 这里我们的模板是放在org.cnzjw.template包下面
				configuration.setClassForTemplateLoading(this.getClass(),
						"/ftl");
				Template t = configuration.getTemplate("exportProductList.ftl");
				// 输出文档路径及名称
				Calendar now = Calendar.getInstance();
				long l = now.getTimeInMillis();
				String tmpPath = SystemContext.updown_File_path+TEMP_EXPORT_PATH;//获得工程运行web的目录
				File file = new File(tmpPath);
				if(!file.exists()) {
					file.mkdirs();
				}
		        String realPath = tmpPath +l+ ".xls";
				File outFile = new File(realPath);
				Writer out = null;
				out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(outFile),"UTF-8"));
				t.process(dataMap, out);
				out.close();
				String rootUrl = request.getContextPath() + EXPORT_PATH+l+ ".xls";
				Map<String,String> map = new HashMap<String,String>();
				map.put("path", rootUrl);
				map.put("fileName", l+ ".xls");
				srm.setObj(map);
		    } catch (Exception e) {
				srm.setCode(0L);
				e.printStackTrace();
	        }
		return srm;
	}

	@Override
	public List<Map<String,Object>>  getMatNameList(String matName) throws HSKException {
		List<Map<String,Object>>  reList = new ArrayList<Map<String,Object>> ();
		try{
			if(matName!=null && !matName.trim().equals(""))
				reList = mdMaterielInfoDao.getMatNameList(matName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return reList;
	}

	@Override
	public SysRetrunMessage getMdMaterielInfoByMatName(String matName)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			MdMaterielInfo info = new MdMaterielInfo();
			info.setMatName(matName.trim());
			info.setTab_order("wmsMiId desc");
			List<MdMaterielInfo> infoList = mdMaterielInfoDao.getListByMdMaterielInfo(info);
			if(infoList!= null && infoList.size() > 0)
				srm.setObj(infoList.get(0));
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
		}
		return srm;
	}

	@Override
	public SysRetrunMessage savePy() throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			MdItemKey new_MdItemKey=new MdItemKey();
			List<MdItemKey> list = this.getList(new_MdItemKey);
			for(MdItemKey key : list){
				String mmfNamePy = "";
				String matNamePy = "";
				if(!key.getMmfName().trim().equals("")){
					for(int i =0;i < key.getMmfName().trim().length();i++)
						mmfNamePy+=PingYinUtil.getLetterFormChinese(key.getMmfName().trim().charAt(i));
				}
				if(!key.getMatName().trim().equals("")){
					for(int i =0;i < key.getMatName().trim().length();i++)
						matNamePy+=PingYinUtil.getLetterFormChinese(key.getMatName().trim().charAt(i));
				}
				key.setMatNamePy(matNamePy);
				key.setMmfNamePy(mmfNamePy);
				this.newObject(key);
			}
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public SysRetrunMessage saveAliasName(Integer wmsMiId, String aliasName) throws HSKException {
		aliasName=aliasName.trim();
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		if (wmsMiId == null) {
			sm.setCode(2L); //无数据
			return sm;
		}
		if (aliasName == null || aliasName.equals("")) {
			sm.setCode(3L); //空字符串
			return sm;
		}
		try {
			MdMaterielInfo mdMaterielInfo = mdMaterielInfoDao.findMdMaterielInfoById(wmsMiId);
			if (mdMaterielInfo == null) {
				sm.setCode(2L); //无数据
				return sm;
			}
			if (mdMaterielInfo.getAliasName() != null && !mdMaterielInfo.getAliasName().equals("")) {
				String[] aliasNames = mdMaterielInfo.getAliasName().split(",");
				if (aliasNames.length >= 3) {
					sm.setCode(6L);
					return sm;
				}
				if (Arrays.asList(aliasNames).contains(aliasName)) {
					sm.setCode(4L);
					return sm;
				}
			}
			if (mdMaterielInfo.getAliasName() != null && !mdMaterielInfo.getAliasName().equals("")) {
				mdMaterielInfo.setAliasName(mdMaterielInfo.getAliasName() + "," + aliasName);
			} else {
				mdMaterielInfo.setAliasName(aliasName);
			}

			this.updateObject(mdMaterielInfo);
//			sm.setObj(aliasName);
			sm.setObj(mdMaterielInfo.getAliasName());
//			mdMaterielInfoDao.updateMdMaterielInfoById(mdMaterielInfo.getWmsMiId(), mdMaterielInfo);
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}
	public SysRetrunMessage saveDeleteAliasName(Integer wmsMiId, String aliasName) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		String aliasName1=aliasName;
		try {
			MdMaterielInfo mdMaterielInfo = mdMaterielInfoDao.findMdMaterielInfoById(wmsMiId);
			String aliasNames=mdMaterielInfo.getAliasName();

			Pattern p = Pattern.compile(".*,\\s*(.*)");
			Matcher m = p.matcher(aliasNames);
			if (m.find())
//				System.out.println();

			if (aliasName.equals(m.group(1))){
				aliasName=","+aliasName;
			}else {
				aliasName=aliasName+",";
			}
			aliasNames=aliasNames.replace(aliasName,"");
			mdMaterielInfo.setAliasName(aliasNames);
			if (mdMaterielInfo.getAliasName().equals(""))
				mdMaterielInfo.setAliasName(null);
			this.updateObject(mdMaterielInfo);
			srm.setObj(mdMaterielInfo.getAliasName());
	} catch (Exception e) {
			srm.setCode(0l);
		e.printStackTrace();
	}
		return srm;
	}

	@Override
	public PagerModel getPagerViewLogObject(Integer wmsMiId) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList<MdMaterielPartDetailLogEntity>());
		try {
			pm = mdMaterielInfoDao.getPagerModelMdMaterielLog(wmsMiId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public SysRetrunMessage updateMaterielPart(String wmsMiIds, Integer mdpsId, Integer mdpId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			if (mdpId == null) {
				sm.setCode(2L);
				return sm;
			}
			String[] wmsMiIdArray = wmsMiIds.split(",");
			Integer wmsMiId = null;
			MdMaterielInfo mdMaterielInfo;
			for (String wmsMiIdS : wmsMiIdArray) {
				wmsMiId = Integer.parseInt(wmsMiIdS);
				mdMaterielInfo = new MdMaterielInfo();
				mdMaterielInfo.setWmsMiId(wmsMiId);
				mdMaterielInfo = (MdMaterielInfo) this.getOne(mdMaterielInfo);
				if (mdMaterielInfo == null)
					continue;
				mdMaterielInfo.setMdpId(mdpId);
				if (mdpsId != null && mdpsId != 0)
					mdMaterielInfo.setMdpsId(mdpsId);
				this.updateObject(mdMaterielInfo);
			}
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getPriceCount(Integer paiId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			Map<String, Object> map = mdMaterielInfoDao.getPriceAdjustmentCount(paiId);
			sm.setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
			sm.setCode(0L);
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getMatCodeCount(String matCode, Integer wmsMiId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			Integer count = mdMaterielInfoDao.getMatCodeCount(matCode, wmsMiId);
			if (count > 0)
				sm.setCode(2L);
		} catch (Exception e) {
			e.printStackTrace();
			sm.setCode(0L);
		}
		return sm;
	}

	/*
	* 2020-07-29
	* yanglei
	* 运营人员获取商品信息列表
	* */
	public PagerModel getOperatorsPagerModelObject (MdMaterielInfo att_MdMaterielInfo) throws HSKException{
		PagerModel pm = new PagerModel(new ArrayList<MdMaterielInfo>());
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			if (account.getOrganizaType().equals("100")) {//供应商增加
				att_MdMaterielInfo.setPurchaseType("1");
				att_MdMaterielInfo.setWzId(account.getOldId());
			} else if (account.getOrganizaType().equals("20001")) {//集团增加
				att_MdMaterielInfo.setPurchaseType("2");
				att_MdMaterielInfo.setWzId(account.getOldId());
			} else if (account.getOrganizaType().equals("20002")) {//医院增加
				att_MdMaterielInfo.setPurchaseType("3");
				att_MdMaterielInfo.setWzId(account.getOldId());
			} else if (account.getOrganizaType().equals("20003")) {//门诊增加
				att_MdMaterielInfo.setPurchaseType("4");
				att_MdMaterielInfo.setWzId(account.getOldId());
			} else//管理员查看所有供应商商品
//				att_MdMaterielInfo.setPurchaseType("1");
			att_MdMaterielInfo.setTab_like("matCode,matName,applicantName,matType,labelInfo");
			pm = operatorsMaterielInfoDao.getPagerModelByMdMaterielInfo(att_MdMaterielInfo);
			List<MdMaterielInfo> list = pm.getItems();
			List<MdMaterielFormat> formatList = new ArrayList<>();
			MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
			Integer wmsMiId = 0;
			for (MdMaterielInfo mdMaterielInfo : list) {
				wmsMiId = mdMaterielInfo.getWmsMiId();
				mdMaterielFormat.setWmsMiId(wmsMiId);
				formatList = this.getList(mdMaterielFormat); // mdMaterielFormatDao.getListByMdMaterielFormat(mdMaterielFormat);
				mdMaterielInfo.setNormList(formatList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	public  SysRetrunMessage getMdMaterielInfoCount() throws HSKException{
		SysRetrunMessage sm = new SysRetrunMessage(1L);
//		Map<String,String> setMap=new HashMap<>();
		Map<String, String> SeMap = new HashMap<String, String>();
		try {
			Integer shelvesCount=operatorsMaterielInfoDao.getMdMaterielInfoCount(1);
			Integer offShelfCount=operatorsMaterielInfoDao.getMdMaterielInfoCount(2);
			Integer allCount=operatorsMaterielInfoDao.getMdMaterielInfoCount(null);
			SeMap.put("shelvesCount",shelvesCount.toString());
			SeMap.put("offShelfCount",offShelfCount.toString());
			SeMap.put("allCount",allCount.toString());
			sm.setObj(SeMap);
		} catch (Exception e) {
			e.printStackTrace();
			sm.setCode(0L);
		}
		return sm;
	}
	public PagerModel getMdmaterielFormatList(Integer wmsmiId,String mmfCode,Integer limit,Integer page) throws HSKException{
		PagerModel pm=new PagerModel();
		try {
			List<Map<String,Object>> mxList =operatorsMaterielInfoDao.getMdmaterielFormatList(wmsmiId,mmfCode,limit,page);
			List<Map<String,Object>> mxList1 =operatorsMaterielInfoDao.getMdmaterielFormatList(wmsmiId,mmfCode,null,null);
			Integer mxListCount1=mxList1.size();
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxListCount1);
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
		e.printStackTrace(); }
		return pm;
	}
	public PagerModel getBrandList(String brand) throws HSKException{
		PagerModel pm=new PagerModel();
		try {
		List<Map<String,Object>> mxList = operatorsMaterielInfoDao.getBrandList(brand);
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxList.size());
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace(); }
		return pm;
	}
	public PagerModel getApplicantNameList(String applicantName) throws HSKException{
		PagerModel pm=new PagerModel();
		try {
			List<Map<String,Object>> mxList = operatorsMaterielInfoDao.getApplicantNameList(applicantName);
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxList.size());
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace(); }
		return pm;
	}
	//运营人员批量上下架
	public SysRetrunMessage saveMdMaterielInfo(String wmsmiIds,String matRemovalReasons) throws HSKException{
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		SysUserInfo account = this.GetOneSessionAccount();
		try {
			if (wmsmiIds!=null&&!wmsmiIds.equals("")){
				wmsmiIds = wmsmiIds.substring(0,wmsmiIds.length() - 1);
				String[] wmsmiIdArr = wmsmiIds.split(",");
				for (int i = 0; i <wmsmiIdArr.length; i++) {
					Integer wmsmiId= Integer.parseInt(wmsmiIdArr[i]);
					MdMaterielInfo mdMaterielInfo=operatorsMaterielInfoDao.findMdMaterielInfoById(wmsmiId);
					if (mdMaterielInfo.getState()!="1"&&!mdMaterielInfo.getState().equals("1")){

					}else {
						mdMaterielInfo.setState("3");
						mdMaterielInfo.setMatRemovalReasons(matRemovalReasons);
						operatorsMaterielInfoDao.updateMdMaterielInfoById(wmsmiId,mdMaterielInfo);
						MdMaterielPartDetailLogEntity mdMaterielPartDetailLogEntity=new MdMaterielPartDetailLogEntity();
						mdMaterielPartDetailLogEntity.setWmsMiId(wmsmiId);
						mdMaterielPartDetailLogEntity.setLogName("违规下架");
						mdMaterielPartDetailLogEntity.setLog(matRemovalReasons);
						mdMaterielPartDetailLogEntity.setCreateDate(new Date());
						mdMaterielPartDetailLogEntity.setSuiId(account.getSuiId());
						mdMaterielPartDetailLogEntity.setCreateRen(account.getUserName());
						logDao.saveMdMaterielPartDetailLog(mdMaterielPartDetailLogEntity);
					}
				}
			}else {
				sm.setCode(2L);
				sm.setMeg("请选择违规下架的商品");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sm.setCode(0L);
		}
		return sm;
	}
	public PagerModel getMdMaterielInfoLog(Integer wmsmiId,Integer limit,Integer page) throws HSKException{
		PagerModel pm=new PagerModel();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<Map<String,Object>> mxList = operatorsMaterielInfoDao.getMdMaterielInfoLog(wmsmiId,limit,page);
			if (mxList!=null&&mxList.size()>0){
				for (Map<String, Object> map : mxList) {
					Object createDate=map.get("createDate");
					if (createDate!=null&&!createDate.equals("")){
						map.put("createDate",sdf.format(createDate));
					}else {
						map.put("createDate","");
					}
				}
			}
			List<Map<String,Object>> mxList1 = operatorsMaterielInfoDao.getMdMaterielInfoLog(wmsmiId,null,null);
			Integer mxListCount1=mxList1.size();
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxListCount1);
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace(); }
		return pm;
	}
	//查看品牌管理中 品牌列表
	public PagerModel getMdMaterielBrandInfo(String mbdName,Integer state,Integer limit,Integer page) throws HSKException{
		PagerModel pm=new PagerModel();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<Map<String,Object>> mxList = operatorsMaterielInfoDao.getMdMaterielBrandInfo(mbdName,state,limit,page);
			if (mxList!=null&&mxList.size()>0){
				for (Map<String, Object> map : mxList) {
					Object createDate=map.get("createDate");
					if (createDate!=null&&!createDate.equals("")){
						map.put("createDate",sdf.format(createDate));
					}else {
						map.put("createDate","");
					}


					Object state1=map.get("state");
					if (state1!=null&&!state1.equals("")){
						map.put("state",state1.toString());
					}else {
						map.put("state","1");
					}

					Object mdpIdObj=map.get("mbaCode");
					Integer countState=0;
					if (mdpIdObj!=null&&!mdpIdObj.equals("")){
						Integer mdpId=Integer.parseInt(mdpIdObj.toString());
						countState=operatorsMaterielInfoDao.getMdMaterielState(mdpId);
					}
					map.put("countState",countState);
				}
			}
			List<Map<String,Object>> mxList1 = operatorsMaterielInfoDao.getMdMaterielBrandInfo(mbdName,state,null,null);
			Integer mxListCount1=mxList1.size();
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxListCount1);
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace(); }
		return pm;
	}

	public SysRetrunMessage saveOrUpdateMdMaterielBrand(Integer mbdId,Integer lessenFilecodeId,String filecodeId, String  mbdName,String describe,String state,String mbdManufacturer ) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo account = this.GetOneSessionAccount();
		try {
			if (mbdId==null){
				Integer count=operatorsMaterielInfoDao.findMdMaterielBrandInfoByName(mbdName);
				if (count>0){
					srm.setCode(2L);
					srm.setMeg("已存在品牌名");
					return srm;
				}
				MdMaterielBrand mdMaterielBrand=new MdMaterielBrand();
				mdMaterielBrand.setMbdName(mbdName);
				mdMaterielBrand.setMbdManufacturer(mbdManufacturer);
				if (state==""||state==null){
					mdMaterielBrand.setState("1");
				}else if (state.equals("1")){
					mdMaterielBrand.setState("1");
				}else {
					mdMaterielBrand.setState("2");
				}
				mdMaterielBrand.setDescribe(describe);
				SysFileInfo sysFileInfo=fileInfoDao.findFileIdInfoById(lessenFilecodeId);
				mdMaterielBrand.setLessen_filecode(sysFileInfo.getFileCode());

//				SysFileInfo sysFileInfo2=fileInfoDao.findFileIdInfoById(filecodeId);
//				mdMaterielBrand.setFilecode(sysFileInfo2.getFileCode());
				if (filecodeId!=null&&!filecodeId.equals("")){
					String filecodeIds="";
					String[] filecodeIdArr = filecodeId.split(",");
					for (int i = 0; i <filecodeIdArr.length; i++) {
						Integer filecodeIdInt=Integer.parseInt(filecodeIdArr[i]);
						SysFileInfo sysFileInfo2=fileInfoDao.findFileIdInfoById(filecodeIdInt);
						filecodeIds+=sysFileInfo2.getFileCode()+",";
						mdMaterielBrand.setFilecode(filecodeIds);
					}
				}else {
					mdMaterielBrand.setFilecode(null);
				}
				mdMaterielBrand.setCreateRen(account.getUserName());
				mdMaterielBrand.setCreateDate(new Date());
				this.newObject(mdMaterielBrand);
//				SysFileInfo sysFileInfo=new SysFileInfo();
//				sysFileInfo.setFileCode();
			}else {
				MdMaterielBrand mdMaterielBrand=operatorsMaterielInfoDao.findMdMaterielBrandInfoById(mbdId);
				Integer count=operatorsMaterielInfoDao.findMdMaterielBrandInfoByName(mbdName);
				if (count>1){
					srm.setCode(2L);
					srm.setMeg("已存在品牌名");
					return srm;
				}
				mdMaterielBrand.setMbdName(mbdName);
				mdMaterielBrand.setMbdManufacturer(mbdManufacturer);
//				mdMaterielBrand.setState(state);
				if (state==""||state==null||state.equals("1")){
					mdMaterielBrand.setState("1");
				}else {
					mdMaterielBrand.setState("2");
				}
				mdMaterielBrand.setDescribe(describe);
				if (lessenFilecodeId!=null){
					SysFileInfo sysFileInfo=fileInfoDao.findFileIdInfoById(lessenFilecodeId);
					mdMaterielBrand.setLessen_filecode(sysFileInfo.getFileCode());
				}
				if (filecodeId!=null&&!filecodeId.equals("")){
					String filecodeIds="";
					String[] filecodeIdArr = filecodeId.split(",");
					for (int i = 0; i <filecodeIdArr.length; i++) {
						Integer filecodeIdInt=Integer.parseInt(filecodeIdArr[i]);
						SysFileInfo sysFileInfo2=fileInfoDao.findFileIdInfoById(filecodeIdInt);
						filecodeIds+=sysFileInfo2.getFileCode()+",";
					}
					mdMaterielBrand.setFilecode(filecodeIds);
				}
				operatorsMaterielInfoDao.updateMaterielBrandInfoById(mbdId,mdMaterielBrand);
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0L);
		}
		return srm;
	}
	//品牌开关
	//品牌状态修改
	public SysRetrunMessage saveBrandState(Integer mbdId,String state) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			if (mbdId!=null){
				MdMaterielBrand att_MdMaterielBrand=operatorsMaterielInfoDao.findMdMaterielBrandInfoById(mbdId);
				att_MdMaterielBrand.setState(state);
				this.updateObject(att_MdMaterielBrand);
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0L);
			throw new HSKException(e);
		}
		return srm;
	}
	//删除品牌
	public SysRetrunMessage deleteMdMaterielBrand(String mbdIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			if (mbdIds != null&&!mbdIds.equals("")) {
				String[] mbdIdArr=mbdIds.split(",");
				for (String mbdIdStr :mbdIdArr) {
					Integer mbdId=Integer.parseInt(mbdIdStr);
				Integer count = operatorsMaterielInfoDao.getMdMaterielBrandRefCount(mbdId);
				if (count > 0) {
					srm.setCode(2L); // 被引用
					srm.setMeg("品牌被引用");
					return srm;
				}
					operatorsMaterielInfoDao.deleteMaterielBrandInfoById(mbdId);
				}

//				Integer count = operatorsMaterielInfoDao.getMdMaterielBrandRefCount(mbdId);
//				if (count > 0) {
//					srm.setCode(2L); // 被引用
//					srm.setMeg("品牌被引用");
//					return srm;
//				}
//				operatorsMaterielInfoDao.deleteMaterielBrandInfoById(mbdId);
			}else {
				srm.setCode(2L);
				srm.setMeg("请选择需要删除的数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0L);
			throw new HSKException(e);
		}
		return srm;
	}
	//编辑页面品牌数据接口
	public  SysRetrunMessage getUpdateMdMaterielBrand(Integer mbdId) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		Map<String, String> SeMap = new HashMap<String, String>();
		try {
			MdMaterielBrand mdMaterielBrand=operatorsMaterielInfoDao.findMdMaterielBrandInfoById(mbdId);
			SeMap.put("mbdId",mbdId.toString());
			SeMap.put("mbdName",mdMaterielBrand.getMbdName());
			String fileCodes="";
			String fileCodeNames="";
			if (mdMaterielBrand.getFilecode()!=null&&!mdMaterielBrand.getFilecode().equals("")){
				String[] filecodeArr = mdMaterielBrand.getFilecode().split(",");
				for (int i = 0; i <filecodeArr.length; i++) {
//					Integer filecodeIdInt=Integer.parseInt(filecodeArr[i]);
					SysFileInfo sysFileInfo=fileInfoDao.getSysFileInfoByFileCode(filecodeArr[i]);
					fileCodes+=sysFileInfo.getRootPath()+",";
					fileCodeNames+=sysFileInfo.getFileName()+",";
				}
				SeMap.put("fileCode",fileCodes);
				SeMap.put("fileCodeName",fileCodeNames);
			}else {
				SeMap.put("fileCode"," ");
				SeMap.put("fileCodeName"," ");
			}
			if (mdMaterielBrand.getLessen_filecode()!=null&&!mdMaterielBrand.getLessen_filecode().equals("")){
				SysFileInfo sysFileInfo2= fileInfoDao.getSysFileInfoByFileCode(mdMaterielBrand.getLessen_filecode());
				SeMap.put("lessenFileCode",sysFileInfo2.getRootPath());
				SeMap.put("lessenFileCodeName",sysFileInfo2.getFileName());
			}else {
				SeMap.put("lessenFileCode"," ");
				SeMap.put("lessenFileCodeName"," ");
			}
			SeMap.put("describe",mdMaterielBrand.getDescribe());
			SeMap.put("state",mdMaterielBrand.getState());
			SeMap.put("mbdManufacturer",mdMaterielBrand.getMbdManufacturer());
			srm.setObj(SeMap);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0L);
		}
		return srm;
	}
	//通过品牌删除图片接口
	public  SysRetrunMessage deleteBrandLogo(Integer mbdId,Integer fileCode) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			if (fileCode!=null){
			if (fileCode==1){
				MdMaterielBrand mdMaterielBrand=operatorsMaterielInfoDao.findMdMaterielBrandInfoById(mbdId);
				mdMaterielBrand.setLessen_filecode(null);
				this.updateObject(mdMaterielBrand);
			}else if (fileCode==2){
				MdMaterielBrand mdMaterielBrand=operatorsMaterielInfoDao.findMdMaterielBrandInfoById(mbdId);
				String[] filecodeArr = mdMaterielBrand.getFilecode().split(",");
				String filecodes="";
				for (int i = 0; i <filecodeArr.length; i++) {
					if (i==0){
						filecodes+="";
					}else {
						filecodes+=filecodeArr[i]+",";
					}
				}
				mdMaterielBrand.setFilecode(filecodes);
				this.updateObject(mdMaterielBrand);
			}
			else if (fileCode==3){
				MdMaterielBrand mdMaterielBrand=operatorsMaterielInfoDao.findMdMaterielBrandInfoById(mbdId);
				String[] filecodeArr = mdMaterielBrand.getFilecode().split(",");
				String filecodes="";
				for (int i = 0; i <filecodeArr.length; i++) {
					if (i==1){
						filecodes+="";
					}else {
						filecodes+=filecodeArr[i]+",";
					}
				}
				mdMaterielBrand.setFilecode(filecodes);
				this.updateObject(mdMaterielBrand);
			}
			else if (fileCode==4){
				MdMaterielBrand mdMaterielBrand=operatorsMaterielInfoDao.findMdMaterielBrandInfoById(mbdId);
				String[] filecodeArr = mdMaterielBrand.getFilecode().split(",");
				String filecodes="";
				for (int i = 0; i <filecodeArr.length; i++) {
					if (i==2){
						filecodes+="";
					}else {
						filecodes+=filecodeArr[i]+",";
					}
				}
				mdMaterielBrand.setFilecode(filecodes);
				this.updateObject(mdMaterielBrand);
			}
			else if (fileCode==5){
				MdMaterielBrand mdMaterielBrand=operatorsMaterielInfoDao.findMdMaterielBrandInfoById(mbdId);
				String[] filecodeArr = mdMaterielBrand.getFilecode().split(",");
				String filecodes="";
				for (int i = 3; i <filecodeArr.length; i++) {
					if (i==1){
						filecodes+="";
					}else {
						filecodes+=filecodeArr[i]+",";
					}
				}
				mdMaterielBrand.setFilecode(filecodes);
				this.updateObject(mdMaterielBrand);
			}
			else if (fileCode==6){
				MdMaterielBrand mdMaterielBrand=operatorsMaterielInfoDao.findMdMaterielBrandInfoById(mbdId);
				String[] filecodeArr = mdMaterielBrand.getFilecode().split(",");
				String filecodes="";
				for (int i = 0; i <filecodeArr.length; i++) {
					if (i==4){
						filecodes+="";
					}else {
						filecodes+=filecodeArr[i]+",";
					}
				}
				mdMaterielBrand.setFilecode(filecodes);
				this.updateObject(mdMaterielBrand);
			}
			else if (fileCode==7){
				MdMaterielBrand mdMaterielBrand=operatorsMaterielInfoDao.findMdMaterielBrandInfoById(mbdId);
				String[] filecodeArr = mdMaterielBrand.getFilecode().split(",");
				String filecodes="";
				for (int i = 5; i <filecodeArr.length; i++) {
					if (i==1){
						filecodes+="";
					}else {
						filecodes+=filecodeArr[i]+",";
					}
				}
				mdMaterielBrand.setFilecode(filecodes);
				this.updateObject(mdMaterielBrand);
			}
			}else {
				srm.setCode(2L);
				srm.setMeg("请选择删除的图片");
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0L);
		}
		return srm;
		}

	//查看参数管理中 参数列表
	public PagerModel getMdMaterielParameter(String mmpName,String isRequired,String state,Integer limit,Integer page) throws HSKException{
		PagerModel pm=new PagerModel();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<Map<String,Object>> mxList = operatorsMaterielInfoDao.getMdMaterielParameter(mmpName,isRequired,state,limit,page);
			if (mxList!=null&&mxList.size()>0){
				for (Map<String, Object> map : mxList) {
					Object createDate=map.get("createDate");
					if (createDate!=null&&!createDate.equals("")){
						map.put("createDate",sdf.format(createDate));
					}else {
						map.put("createDate","");
					}
					Object editDate=map.get("editDate");
					if (editDate!=null&&!editDate.equals("")){
						map.put("editDate",sdf.format(editDate));
					}else {
						map.put("editDate","");
					}
				}
			}
			List<Map<String,Object>> mxList1 = operatorsMaterielInfoDao.getMdMaterielParameter(mmpName,isRequired,state,null,null);
			Integer mxListCount1=mxList1.size();
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxListCount1);
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace(); }
		return pm;
	}
	//新增参数接口
	public SysRetrunMessage saveOrUpdateMdMaterielParameter(Integer mmpId,String isRequired,String mmpName,String isSearch,String relation,String optionaValue,String state,String mmpSort,String inputMode) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo account = this.GetOneSessionAccount();
		try {
			if (mmpId==null){
				Integer count=operatorsMaterielInfoDao.findMdMaterielParameterInfoByName(mmpName);
				if (count>0){
					srm.setCode(2L);
					srm.setMeg("已存在参数名");
					return srm;
				}
				MdMaterielParameter mdMaterielParameter=new MdMaterielParameter();
				mdMaterielParameter.setIsRequired(isRequired);
				mdMaterielParameter.setMmpName(mmpName);
				mdMaterielParameter.setIsSearch(isSearch);
				mdMaterielParameter.setRelation(relation);
				mdMaterielParameter.setOptiona_value(optionaValue);
				if (state!=null&&!state.equals("")){
					if (state.equals("1")){
						mdMaterielParameter.setState("1");
					}else {
						mdMaterielParameter.setState("2");
					}
				}else {
					mdMaterielParameter.setState("1");
				}
				mdMaterielParameter.setMmpSort(mmpSort);
				mdMaterielParameter.setInputMode(inputMode);
				this.newObject(mdMaterielParameter);
			}else {
				MdMaterielParameter mdMaterielParameter=operatorsMaterielInfoDao.findMdMaterielParameterById(mmpId);
				if (!mmpName.equals(mdMaterielParameter.getMmpName())&&mmpName!=mdMaterielParameter.getMmpName()){
					Integer count=operatorsMaterielInfoDao.findMdMaterielParameterInfoByName(mmpName);
					if (count>0){
						srm.setCode(2L);
						srm.setMeg("已存在参数名");
						return srm;
					}
				}
				mdMaterielParameter.setIsRequired(isRequired);
				mdMaterielParameter.setMmpName(mmpName);
				mdMaterielParameter.setIsSearch(isSearch);
				mdMaterielParameter.setRelation(relation);
				mdMaterielParameter.setOptiona_value(optionaValue);
				if (state!=null&&!state.equals("")){
					if (state.equals("1")){
						mdMaterielParameter.setState("1");
					}else {
						mdMaterielParameter.setState("2");
					}
				}else {
					mdMaterielParameter.setState("1");
				}
				mdMaterielParameter.setMmpSort(mmpSort);
				mdMaterielParameter.setInputMode(inputMode);
				mdMaterielParameter.setEditRen(account.getUserName());
				mdMaterielParameter.setEditDate(new Date());
				operatorsMaterielInfoDao.updateMdMaterielParameterById(mmpId,mdMaterielParameter);
			}
			} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0L);
		}
		return srm;
	}

//	//新增参数接口
//	public SysRetrunMessage saveOrUpdateMdMaterielParameter2() throws HSKException{
//		SysRetrunMessage srm = new SysRetrunMessage(1l);
//		SysUserInfo account = this.GetOneSessionAccount();
//		try {
//			if (2>1) {
//				throw new Exception();
//			}
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			srm.setCode(0L);
////			throw new  HSKException(e);
//		}
//		return srm;
//	}
	//删除参数接口
	public SysRetrunMessage deleteMdMaterielParameter(String mmpId) throws HSKException{
//		Integer mmpId=Integer.parseInt(mmpId);
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		if (mmpId == null || mmpId.equals("")) {
			srm.setCode(3L); // 传入参数有误
			return srm;
		}
		try{
			// 查询是否有被引用的参数
			Integer count = operatorsMaterielInfoDao.getMdMaterielParameterRefCountOne(mmpId);
			if (count > 0) {
				srm.setCode(2L); // 存在引用
				srm.setMeg("参数被引用");
				return srm;
			}
			if (mmpId!=null&&!mmpId.equals("")){
				operatorsMaterielInfoDao.deleteMdMaterielParameterById(mmpId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0L);
			throw new  HSKException(e);
		}
		return srm;
	}
	//查看属性列表接口2
	public PagerModel getmdMaterielAttributeInfo(String mmaName,String state,String isOptional,Integer limit,Integer page) throws HSKException{
		PagerModel pm=new PagerModel();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<Map<String,Object>> mxList = operatorsMaterielInfoDao.getmdMaterielAttributeInfo(mmaName,state,isOptional,limit,page);
			if (mxList!=null&&mxList.size()>0){
				for (Map<String, Object> map : mxList) {
					Object createDate=map.get("createDate");
					if (createDate!=null&&!createDate.equals("")){
						map.put("createDate",sdf.format(createDate));
					}else {
						map.put("createDate","");
					}
					Object editDate=map.get("editDate");
					if (editDate!=null&&!editDate.equals("")){
						map.put("editDate",sdf.format(editDate));
					}else {
						map.put("editDate","");
					}
				}
			}
			List<Map<String,Object>> mxList1 = operatorsMaterielInfoDao.getmdMaterielAttributeInfo(mmaName,state,isOptional,null,null);
			Integer mxListCount1=mxList1.size();
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxListCount1);
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace(); }
		return pm;
	}
	//属性详情列表接口
	public PagerModel getmdMaterielAttributeMxInfo(Integer mmaId,String mmamxName,String state,String isOptional,Integer limit,Integer page) throws HSKException{
		PagerModel pm=new PagerModel();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			List<Map<String,Object>> mxList = operatorsMaterielInfoDao.getmdMaterielAttributeMxInfo(mmaId,mmamxName,state,isOptional,limit,page);
			if (mxList!=null&&mxList.size()>0){
				for (Map<String, Object> map : mxList) {
					Object createDate=map.get("createDate");
					if (createDate!=null&&!createDate.equals("")){
						map.put("createDate",sdf.format(createDate));
					}else {
						map.put("createDate","");
					}
					Object editDate=map.get("editDate");
					if (editDate!=null&&!editDate.equals("")){
						map.put("editDate",sdf.format(editDate));
					}else {
						map.put("editDate","");
					}
				}
			}
			List<Map<String,Object>> mxList1 = operatorsMaterielInfoDao.getmdMaterielAttributeMxInfo(mmaId,mmamxName,state,isOptional,null,null);
			Integer mxListCount1=mxList1.size();
			pm.setItems(mxList);
			pm.setRows(mxList);
			pm.setTotal(mxListCount1);
			pm.setTotalCount(mxList.size());
		} catch (HSKDBException e) {
			e.printStackTrace(); }
		return pm;
	}
	//新增或编辑属性接口
	public SysRetrunMessage saveOrUpdateMdMaterielAttribute(mdMaterielAttribute mdMaterielAttribute) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo account = this.GetOneSessionAccount();
		try {
			if (mdMaterielAttribute.getMmaId()!=null){
//				mdMaterielAttribute att_mdMaterielAttribute=operatorsMaterielInfoDao.findMdMaterielAttributeById(mdMaterielAttribute.getMmaId());
//				if (att_mdMaterielAttribute.getMmaName().equals(mdMaterielAttribute.getMmaName())&&att_mdMaterielAttribute.getMmaName()!=mdMaterielAttribute.getMmaName()){
					Integer count=operatorsMaterielInfoDao.findMdMaterielAttributeInfoByName(mdMaterielAttribute.getMmaName(),mdMaterielAttribute.getMmaId());
					if (count>0){
						srm.setCode(2L);
						srm.setMeg("已存在属性名");
						return srm;
					}
//				}
				this.updateObject(mdMaterielAttribute);
//				operatorsMaterielInfoDao.updateMdMaterielAttributeById(mdMaterielAttribute.getMmaId(),mdMaterielAttribute);
			}else {
				if (mdMaterielAttribute.getMmaName()!=null&&!mdMaterielAttribute.getMmaName().equals("")){
					Integer count=operatorsMaterielInfoDao.findMdMaterielAttributeInfoByName(mdMaterielAttribute.getMmaName(),null);
					if (count>0){
						srm.setCode(2L);
						srm.setMeg("已存在属性名");
						return srm;
					}
					this.newObject(mdMaterielAttribute);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0L);
		}
		return srm;
	}
	//通过属性Id修改状态
	public SysRetrunMessage saveOrUpdateMdMaterielAttributeState(Integer  mmaId,String state) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			if (mmaId!=null){
				mdMaterielAttribute att_mdMaterielAttribute=operatorsMaterielInfoDao.findMdMaterielAttributeById(mmaId);
				att_mdMaterielAttribute.setState(state);
				this.updateObject(att_mdMaterielAttribute);
			}else {
				srm.setCode(0L);
				srm.setMeg("请选择操作数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0L);
		}
		return srm;
	}

	@Override
	public SysRetrunMessage getNewCode() throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			Integer wzId = account.getOldId();
			Integer count = mdMaterielInfoDao.getMdMaterielInfoCountByCHH(wzId);
			count += 1;
			String code = String.format("%0" + 5 + "d", count);
			sm.setObj(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sm;
	}

	//批量删除属性接口
	public SysRetrunMessage deleteMaterielAttribute(String mmaIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo account = this.GetOneSessionAccount();
		try {
			if (mmaIds!=null&&!mmaIds.equals("")){
				Integer count = operatorsMaterielInfoDao.getMdMaterielAttrRefCount(mmaIds);
				if (count > 0) {
					srm.setCode(3L); // 存在引用
					srm.setMeg("属性被引用");
					return srm;
				}
//				mmaIds = mmaIds.substring(0,mmaIds.length() -1);
				String[] mmaIdArr=mmaIds.split(",");
				for (String mmaIdStr :mmaIdArr) {
					Integer mmaId=Integer.parseInt(mmaIdStr);
					operatorsMaterielInfoDao.deleteMdMaterielAttributerById(mmaId);
				}
			}else {
				srm.setCode(2L);
				srm.setMeg("请选择需要删除的数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0L);
		}
		return  srm;
	}
	//修改属性详情接口
	public SysRetrunMessage saveOrUpdateMdMaterielAttributeMx(mdMaterielAttributeMx att_mdMaterielAttributeMx,Integer fileId) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo account = this.GetOneSessionAccount();
		try {
			if (att_mdMaterielAttributeMx.getMmamxId()!=null){
				SysFileInfo sysFileInfo=fileInfoDao.findSysFileInfoById(fileId);
				att_mdMaterielAttributeMx.setIconCode(sysFileInfo.getFileCode());
					this.updateObject(att_mdMaterielAttributeMx);
			}else {
				att_mdMaterielAttributeMx.setEditRen(account.getUserName());
				att_mdMaterielAttributeMx.setEditDate(new Date());
				this.newObject(att_mdMaterielAttributeMx);
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0L);
		}
		return srm;
	}
//	//修改属性可选值接口中的数据  与修改方法同时调用
//	public SysRetrunMessage saveOrUpdateMdMaterielAttributeMx1(mdMaterielAttributeMx att_mdMaterielAttributeMx) throws HSKException{
//		SysRetrunMessage srm = new SysRetrunMessage(1l);
//		try {
//			String mmaName=	operatorsMaterielInfoDao.getMmaName(att_mdMaterielAttributeMx.getMmaId());
//			mdMaterielAttribute att_mdMaterielAttribute=operatorsMaterielInfoDao.findMdMaterielAttributeById(att_mdMaterielAttributeMx.getMmaId());
//			att_mdMaterielAttribute.setOptionalValue(mmaName);
//			this.updateObject(att_mdMaterielAttribute);
//		}catch (Exception e) {
//				e.printStackTrace();
//				srm.setCode(0L);
//			}
//		return srm;
//	}

	//删除属性详情接口
	public SysRetrunMessage deleteMaterielAttributeMx(String mmamxId) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysUserInfo account = this.GetOneSessionAccount();
		try {
			if (mmamxId!=null&&!mmamxId.equals("")){
				String[] mmamxIdArr=mmamxId.split(",");
					for (String mmamxIdStr :mmamxIdArr) {
						Integer mmamxIdInt=Integer.parseInt(mmamxIdStr);
						Integer count = operatorsMaterielInfoDao.getMdMaterielAttrMxRefCount(mmamxIdInt);
						if (count > 0) {
							srm.setCode(3L); // 被引用
							srm.setMeg("属性被引用");
							return srm;
						}
						operatorsMaterielInfoDao.deleteMdMaterielAttributeMxById(mmamxIdInt);
					}
			}else {
				srm.setCode(2L);
				srm.setMeg("请选择需要删除的数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0L);
		}
		return srm;
	}
	//通过属性ID查询可选属性值
	public  SysRetrunMessage findAttributeOptionalValue(Integer mmaId) throws HSKException{
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		Map<String, String> SeMap = new HashMap<String, String>();
		try {
			mdMaterielAttribute att_mdMaterielAttribute=operatorsMaterielInfoDao.findMdMaterielAttributeById(mmaId);
			SeMap.put("optionalValues",att_mdMaterielAttribute.getOptionalValue());
			SeMap.put("mmaId",att_mdMaterielAttribute.getMmaId().toString());
			SeMap.put("mmaName",att_mdMaterielAttribute.getMmaName());
			SeMap.put("inputModel",att_mdMaterielAttribute.getInputModel());
			SeMap.put("isSearch",att_mdMaterielAttribute.getIsSearch());
			SeMap.put("isOptional",att_mdMaterielAttribute.getIsOptional());
			if (att_mdMaterielAttribute.getMmaSort()!=null&&!att_mdMaterielAttribute.getMmaSort().equals("")&&!att_mdMaterielAttribute.getMmaSort().equals("undefined")){
				SeMap.put("mmaSort",att_mdMaterielAttribute.getMmaSort());
			}else {
				SeMap.put("mmaSort"," ");
			}
			SeMap.put("newAdd",att_mdMaterielAttribute.getNewAdd());
			SeMap.put("state",att_mdMaterielAttribute.getState());
			SeMap.put("screenType",att_mdMaterielAttribute.getScreenType());
			sm.setObj(SeMap);
		} catch (Exception e) {
			e.printStackTrace();
			sm.setCode(0L);
		}
		return sm;
	}
	//通过参数 查询编辑参数的接口
	public  SysRetrunMessage findMdMaterielParameter(Integer mmpId) throws HSKException{
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		Map<String, String> SeMap = new HashMap<String, String>();
		try {
			MdMaterielParameter att_MdMaterielParameter=operatorsMaterielInfoDao.findMdMaterielParameterById(mmpId);
			SeMap.put("mmpName",att_MdMaterielParameter.getMmpName());
			SeMap.put("isSearch",att_MdMaterielParameter.getIsSearch());
			SeMap.put("relation",att_MdMaterielParameter.getRelation());
			SeMap.put("inputMode",att_MdMaterielParameter.getInputMode());
			SeMap.put("optionaValue",att_MdMaterielParameter.getOptiona_value());
			SeMap.put("mmpSort",att_MdMaterielParameter.getMmpSort());
			SeMap.put("isRequired",att_MdMaterielParameter.getIsRequired());
			SeMap.put("state",att_MdMaterielParameter.getState());
			sm.setObj(SeMap);
		} catch (Exception e) {
			e.printStackTrace();
			sm.setCode(0L);
		}
		return sm;
	}
}