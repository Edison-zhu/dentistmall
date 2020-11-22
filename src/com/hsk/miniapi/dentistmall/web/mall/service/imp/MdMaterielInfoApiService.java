package com.hsk.miniapi.dentistmall.web.mall.service.imp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.hsk.dentistmall.api.daobbase.IMdSupplierDao;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.daobbase.ISysFileInfoDao;
import com.hsk.xframe.api.persistence.SysFileInfo;
import com.hsk.xframe.api.persistence.SysFileInfoTemp;
import com.hsk.xframe.api.persistence.SysSwitchEntity;
import org.jbpm.api.Execution;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage; 
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.PingYinUtil;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.miniapi.dentistmall.web.mall.service.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.miniapi.dentistmall.api.daobbase.*;

import freemarker.template.Configuration;
import freemarker.template.Template;


/** 
  mall业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-29 10:30:22
 */
 
@Service
public class MdMaterielInfoApiService extends DSTApiService implements IMdMaterielInfoApiService {
   /**
   *业务处理dao类  mdMaterielInfoDao 
   */
	@Autowired
	protected IMdMaterielInfoApiDao mdMaterielInfoDao;
	@Autowired
	private IMdEnclosureApiDao mdEnclosureDao;
	@Autowired
	protected IMdMaterielFormatApiDao mdMaterielFormatDao;
	@Autowired
	protected IMdSupplierDao mdSupplierDao;
	@Autowired
	private ISysFileInfoDao sysFileInfoDao;

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
			   		srm.setMeg("此物料已被入库暂时无法删除!");
			   		return srm;
			   	}
			   	MdInventory mdInventory = new MdInventory();
			   	mdInventory.setWmsMiId(wmsMiId);
			   	List<MdInventory> mdInventoryList = this.getList(mdInventory);
			   	if(mdInventoryList != null && mdInventoryList.size() > 0){
			   		srm.setCode(0l);
			   		srm.setMeg("此物料已被入库暂时无法删除!");
			   		return srm;
			   	}
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
		try{
		     att_MdMaterielInfo= mdMaterielInfoDao.findMdMaterielInfoById( wmsMiId) ;
		     att_MdMaterielInfo.setState(state);
		     mdMaterielInfoDao.saveOrUpdateMdMaterielInfo(att_MdMaterielInfo);
			} catch (HSKDBException e) {
				e.printStackTrace(); 
				throw new  HSKException(e);
			}
		return srm;
	} 
	
	/**
	 * 根据md_materiel_info表主键删除多条MdMaterielInfo对象记录
     * @param  WmsMiIds  Integer类型(md_materiel_info表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wmsMiIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
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
			if (att_MdMaterielInfo.getWmsMiId() == null) {
				SysUserInfo account = this.GetOneSessionAccount();
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
			MdSupplier mdSupplier = new MdSupplier();
			mdSupplier.setWzId(att_MdMaterielInfo.getWzId());
			mdSupplier = (MdSupplier) this.getOne(mdSupplier);
			att_MdMaterielInfo.setWzState(mdSupplier.getState());
			String pyName = "";
			if (!att_MdMaterielInfo.getMatName().trim().equals("")) {
				for (int i = 0; i < att_MdMaterielInfo.getMatName().trim().length(); i++)
					pyName += PingYinUtil.getLetterFormChinese(att_MdMaterielInfo.getMatName().trim().charAt(i));
			}
			att_MdMaterielInfo.setPyName(pyName);
			mdMaterielInfoDao.saveOrUpdateMdMaterielInfo(att_MdMaterielInfo);
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
		PagerModel pm=new PagerModel(new ArrayList<MdMaterielInfo>());
			  try{
				  SysUserInfo account = this.GetOneSessionAccount();
				  if(account.getOrganizaType().equals("100")){//供应商增加
					  att_MdMaterielInfo.setPurchaseType("1");
					  att_MdMaterielInfo.setWzId(account.getOldId());
				  }else if(account.getOrganizaType().equals("20001")){//集团增加
					  att_MdMaterielInfo.setPurchaseType("2");
					  att_MdMaterielInfo.setWzId(account.getOldId());
				  }else if(account.getOrganizaType().equals("20002")){//医院增加
					  att_MdMaterielInfo.setPurchaseType("3");
					  att_MdMaterielInfo.setWzId(account.getOldId());
				  }else if(account.getOrganizaType().equals("20003")){//门诊增加
					  att_MdMaterielInfo.setPurchaseType("4");
					  att_MdMaterielInfo.setWzId(account.getOldId());
				  }else//管理员查看所有供应商商品
					  att_MdMaterielInfo.setPurchaseType("1");
				  att_MdMaterielInfo.setTab_like("matCode,matName,applicantName,matType,labelInfo");
				  pm=mdMaterielInfoDao.getPagerModelByMdMaterielInfo(att_MdMaterielInfo);
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
			  if(account.getOrganizaType().equals("100"))//供应商增加
				  mdMaterielFormatView.setPurchaseType("1");
			  else if(account.getOrganizaType().equals("20001"))//集团增加
				  mdMaterielFormatView.setPurchaseType("2");
			  else if(account.getOrganizaType().equals("20002"))//医院增加
				  mdMaterielFormatView.setPurchaseType("3");
			  else if(account.getOrganizaType().equals("20003"))//门诊增加
				  mdMaterielFormatView.setPurchaseType("4");
			  mdMaterielFormatView.setWzId(account.getOldId());
			  mdMaterielFormatView.setState("1");
				pm=mdMaterielInfoDao.getPagerModelByMdMdMaterielFormatView(mdMaterielFormatView);
		    } catch (Exception e) {
				e.printStackTrace(); 
	        }
		return pm;
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
					  reMap.put("matName", obj.getMatName());
					  reMap.put("applicantName", obj.getApplicantName()!=null?obj.getApplicantName():"");
					  reMap.put("brand", obj.getBrand()!=null?obj.getBrand():"");
					  reMap.put("productName", obj.getProductName()!=null?obj.getProductName():"");
					  reMap.put("typeName", obj.getTypeName()!=null?obj.getTypeName():"");
					  reMap.put("basicUnit", obj.getBasicUnit()!=null?obj.getBasicUnit():"");
					  reMap.put("mmfName", obj.getMmfName()!=null?obj.getMmfName():"");
					  reMap.put("price", obj.getPrice()!=null?obj.getPrice().toString():"");
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
	public SysRetrunMessage getMaterielXiangxi(Integer wmsMiId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1l);
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			SysUserInfo sysUserInfo = this.getApiSessionAccount();
			SysSwitchEntity sysSwitchEntity = this.getApiSessionSwitch(1);
			if (sysUserInfo == null && sysSwitchEntity != null && Objects.equals(sysSwitchEntity.getState(), 1)) {
				MdMaterielInfoTemp matInfo = mdMaterielInfoDao.findMdMaterielInfoByIdNoLogin(wmsMiId);
				MdEnclosure mdEnclosure = new MdEnclosure();
				mdEnclosure.setWmsMiId(wmsMiId);
				List<MdEnclosure> mdEnclosureList = mdEnclosureDao.getListByMdEnclosureNoLogin(mdEnclosure);
				String listFilecode = "";
				if (mdEnclosureList != null && mdEnclosureList.size() > 0) {
					for (MdEnclosure obj : mdEnclosureList)
						listFilecode += obj.getFileCode() + ",";
					listFilecode = listFilecode.substring(0, listFilecode.length() - 1);
				}
				matInfo.setListFilecode(listFilecode);
				map.put("mat", matInfo);

				MdSupplierTemp mdSupplier = mdSupplierDao.findMdSupplierByIdNoLogin(matInfo.getWzId());
				List<SysFileInfoTemp> list = new ArrayList<SysFileInfoTemp>();
				if (matInfo.getListFilecode() != null && !matInfo.getListFilecode().trim().equals("")) {
					String codes = "";
					if (listFilecode != null && !listFilecode.trim().equals("")) {
						String[] fileCodeArray = listFilecode.split(",");
						for (String fileCode : fileCodeArray)
							codes += "'" + fileCode + "',";
						codes = codes.substring(0, codes.length() - 1);
						list = sysFileInfoDao.getSysFileInfoByCodesNoLogin(codes);
					}
					map.put("imgs", list);
				}
				if (mdSupplier != null && mdSupplier.getCustoms() != null && !mdSupplier.getCustoms().trim().equals("")) {
					String[] cusList = mdSupplier.getCustoms().split(",");
					map.put("cusList", cusList);
				}
				map.put("supplier", mdSupplier);

				List<MdMaterielFormatTemp> flist = new ArrayList<MdMaterielFormatTemp>();
				MdMaterielFormatTemp att_MdMaterielFormat = new MdMaterielFormatTemp();
				att_MdMaterielFormat.setWmsMiId(wmsMiId);
				att_MdMaterielFormat.setState("1");
				flist = mdMaterielFormatDao.getListByMdMaterielFormatNoLogin(att_MdMaterielFormat);
				for (MdMaterielFormatTemp mdMaterielFormat : flist) {
					String mmfName = mdMaterielFormat.getMmfName();
					if (mmfName == null) {
						continue;
					}
					mmfName = mmfName.replace("\"", "&quot;");
					mmfName = mmfName.replace("'", "&apos;");
					mdMaterielFormat.setMmfName(mmfName);
				}

				map.put("formatList", flist);
			} else {
				MdMaterielInfo matInfo = mdMaterielInfoDao.findMdMaterielInfoById(wmsMiId);
				MdEnclosure mdEnclosure = new MdEnclosure();
				mdEnclosure.setWmsMiId(wmsMiId);
				List<MdEnclosure> mdEnclosureList = mdEnclosureDao.getListByMdEnclosure(mdEnclosure);
				String listFilecode = "";
				if (mdEnclosureList != null && mdEnclosureList.size() > 0) {
					for (MdEnclosure obj : mdEnclosureList)
						listFilecode += obj.getFileCode() + ",";
					listFilecode = listFilecode.substring(0, listFilecode.length() - 1);
				}
				matInfo.setListFilecode(listFilecode);
				map.put("mat", matInfo);

				MdSupplier mdSupplier = mdSupplierDao.findMdSupplierById(matInfo.getWzId());
				List<SysFileInfo> list = new ArrayList<SysFileInfo>();
				if (matInfo.getListFilecode() != null && !matInfo.getListFilecode().trim().equals("")) {
					String codes = "";
					if (listFilecode != null && !listFilecode.trim().equals("")) {
						String[] fileCodeArray = listFilecode.split(",");
						for (String fileCode : fileCodeArray)
							codes += "'" + fileCode + "',";
						codes = codes.substring(0, codes.length() - 1);
						list = sysFileInfoDao.getSysFileInfoByCodes(codes);
					}
					map.put("imgs", list);
				}
				if (mdSupplier != null && mdSupplier.getCustoms() != null && !mdSupplier.getCustoms().trim().equals("")) {
					String[] cusList = mdSupplier.getCustoms().split(",");
					map.put("cusList", cusList);
				}
				map.put("supplier", mdSupplier);

				List<MdMaterielFormat> flist = new ArrayList<MdMaterielFormat>();
				MdMaterielFormat att_MdMaterielFormat = new MdMaterielFormat();
				att_MdMaterielFormat.setWmsMiId(wmsMiId);
				att_MdMaterielFormat.setState("1");
				flist = mdMaterielFormatDao.getListByMdMaterielFormat(att_MdMaterielFormat);
				for (MdMaterielFormat mdMaterielFormat : flist) {
					String mmfName = mdMaterielFormat.getMmfName();
					if (mmfName == null) {
						continue;
					}
					mmfName = mmfName.replace("\"", "&quot;");
					mmfName = mmfName.replace("'", "&apos;");
					mdMaterielFormat.setMmfName(mmfName);
				}

				map.put("formatList", flist);
			}

			sm.setObj(map);
		}catch (Exception e){
			sm.setCode(0l);
			e.printStackTrace();
		}
		return sm;
	}
}