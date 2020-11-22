package com.hsk.miniapi.dentistmall.web.storage.service.imp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsk.dentistmall.api.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.miniapi.dentistmall.api.daobbase.IMdInventoryApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdInventoryExtendApiDao;
import com.hsk.miniapi.dentistmall.api.daobbase.IMdItemKeyApiDao;
import com.hsk.dentistmall.api.security.ReadExcel;
import com.hsk.dentistmall.web.storage.model.ErrorImpDataModel;
import com.hsk.miniapi.dentistmall.web.storage.service.IMdInventoryApiService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.miniapi.xframe.api.daobbase.IorgApiDao;
import com.hsk.xframe.api.persistence.SysFileInfo;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.CheckUtil;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.xframe.api.utils.freeMarker.ExportExcel;
import com.hsk.xframe.api.utils.freeMarker.PingYinUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;


/** 
  storage业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:14:55
 */
 
@Service
public class MdInventoryApiService extends DSTApiService implements IMdInventoryApiService {
   /**
   *业务处理dao类  mdInventoryDao 
   */
	@Autowired
	protected IMdInventoryApiDao mdInventoryDao;
	@Autowired
	protected IMdInventoryExtendApiDao mdInventoryExtendDao;
	@Autowired
	protected IorgApiDao orgDao;
	@Autowired
	private IMdItemKeyApiDao mdItemKeyDao;

 /**
	 * 根据md_inventory表主键删除MdInventory对象记录
     * @param  wiId  Integer类型(md_inventory表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer wiId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);
			  	
			try{
					MdInventoryView     att_MdInventory= mdInventoryDao.findMdInventoryViewById( wiId) ;
					srm.setObj(att_MdInventory);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	} 

    /**
	 * 根据md_inventory表主键删除MdInventory对象记录
     * @param  wiId  Integer类型(md_inventory表主键)
	 * @throws HSKException
	 */

	public MdInventoryView findObject(Integer wiId) throws HSKException{
		MdInventoryView  att_MdInventory=new MdInventoryView();		
			try{
		        att_MdInventory= mdInventoryDao.findMdInventoryViewById(wiId) ;
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdInventory;
	}
	
	 
	 /**
	 * 根据md_inventory表主键删除MdInventory对象记录
     * @param  wiId  Integer类型(md_inventory表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer wiId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
			   MdInventory att_MdInventory = mdInventoryDao.findMdInventoryById(wiId);
			   att_MdInventory.setState("0");
			   mdInventoryDao.saveOrUpdateMdInventory(att_MdInventory);
				//mdInventoryDao.deleteMdInventoryById(wiId);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}
	
	/**
	 * 根据md_inventory表主键删除多条MdInventory对象记录
     * @param  WiIds  Integer类型(md_inventory表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String wiIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = wiIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					mdInventoryDao.deleteMdInventoryById(Integer
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
	 * 新增或修改md_inventory表记录 ,如果md_inventory表主键MdInventory.WiId为空就是添加，如果非空就是修改
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
     * @return MdInventory  修改后的MdInventory对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject( MdInventory att_MdInventory) throws HSKException{
			 SysRetrunMessage srm = new SysRetrunMessage(1l);
			  try{
					mdInventoryDao.saveOrUpdateMdInventory(att_MdInventory); 
					srm.setObj(att_MdInventory);
			    } catch (Exception e) {
					e.printStackTrace(); 
					throw new HSKException(e);
		        }
			return srm;	  
	}
	
	 
	/**
	 *根据MdInventory对象作为对(md_inventory表进行查询，获取分页对象
     * @param  att_MdInventory  MdInventory类型(md_inventory表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException
	 */
	public PagerModel getPagerModelObject (MdInventory att_MdInventory) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdInventory>());
			  try{
					pm=mdInventoryDao.getPagerModelByMdInventory(att_MdInventory);
			    } catch (Exception e) {
					e.printStackTrace();
		        }
		return pm;
	}

	@Override
	public PagerModel getPagerViewObject(MdInventoryView att_MdInventoryView,String sort)
			throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdInventoryView>());
		  try{
			  SysUserInfo account = this.GetOneSessionAccount();
			  if(account.getOrganizaType().equals("20001")){
				  att_MdInventoryView.setRbaId(account.getOldId());
				  att_MdInventoryView.setPurchaseType("1");
			  }else if(account.getOrganizaType().equals("20002")){
				  att_MdInventoryView.setRbsId(account.getOldId());
				  att_MdInventoryView.setPurchaseType("2");
				  
			  }else if(account.getOrganizaType().equals("20003")){
				  att_MdInventoryView.setRbbId(account.getOldId());
				  att_MdInventoryView.setPurchaseType("3");
			  }
			  	if(sort != null && !sort.trim().equals("")){
			  		sort = sort.replace(".", ",");
			  		String[] array = sort.trim().split(",");
			  		att_MdInventoryView.setTabOrder(array[0]+" "+array[1]);
			  	}
				pm=mdInventoryDao.getPagerModelByMdInventoryView(att_MdInventoryView);
				List<MdInventoryView> reList = pm.getRows();
				if(reList!=null && reList.size() > 0){
					//查询收藏列表
					MdInventoryCollect mdInventoryCollect = new MdInventoryCollect();
					mdInventoryCollect.setSuiId(account.getSuiId());
					mdInventoryCollect.setState("1");
					List<MdInventoryCollect> collectList = this.getList(mdInventoryCollect);
					if(collectList != null && collectList.size() > 0){
						for(MdInventoryView view : reList){
							for(MdInventoryCollect collect : collectList){
								if(view.getWiId().equals(collect.getWiId())){
									view.setMicId(collect.getMicId());
									break;
								}
							}
						}
					}
				}
				pm.setRows(reList);
				pm.setItems(reList);
		    } catch (Exception e) {
				e.printStackTrace(); 
	        }
	return pm;
	}

	@Override
	public SysRetrunMessage saveWanning(Integer wiId, Double maxShu, Integer minDay, Double wanningShu,Double ratio,String baseUnit,String matName,String mmfName)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			MdInventory  att_MdInventory= mdInventoryDao.findMdInventoryById( wiId) ;
			MdItemKey mdItemKey = new MdItemKey();
			mdItemKey.setItemKeyId(Integer.parseInt(att_MdInventory.getItemKeyId()));
			mdItemKey = (MdItemKey) this.getOne(mdItemKey);
			mdItemKey.setMmfName(mmfName);
			mdItemKey.setMatName(matName);
			String mmfNamePy = "";
			String matNamePy = "";
			if(!mmfName.trim().equals("")){
				  for(int i =0;i < mmfName.trim().length();i++)
					  mmfNamePy+=PingYinUtil.getLetterFormChinese(mmfName.trim().charAt(i));
			}
			if(!matName.trim().equals("")){
				  for(int i =0;i < matName.trim().length();i++)
					  matNamePy+=PingYinUtil.getLetterFormChinese(matName.trim().charAt(i));
			}
			mdItemKey.setMatNamePy(matNamePy);
			mdItemKey.setMmfNamePy(mmfNamePy);
			this.updateObject(mdItemKey);
			att_MdInventory.setWarningShu(wanningShu);
			att_MdInventory.setMaxShu(maxShu);
			att_MdInventory.setMinDay(minDay);
			att_MdInventory.setRatio(ratio);
			att_MdInventory.setBasicUnit(baseUnit);
			att_MdInventory.setBaseNumber(att_MdInventory.getQuantity()*att_MdInventory.getRatio());
			MdInventoryExtend mdInventoryExtend = new MdInventoryExtend();
			mdInventoryExtend.setWiId(wiId);
			List<MdInventoryExtend> mdInventoryExtendList = this.getList(mdInventoryExtend);
			if(mdInventoryExtendList != null && mdInventoryExtendList.size() > 0){
				for(MdInventoryExtend extend : mdInventoryExtendList){
					extend.setBaseNumber(extend.getQuantity()*att_MdInventory.getRatio());
					extend.setBasicUnit(baseUnit);
					this.updateObject(extend);
				}
			}
			mdInventoryDao.saveOrUpdateMdInventory(att_MdInventory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return srm;
	}

	@Override
	public SysRetrunMessage exportList(MdInventoryView att_MdInventoryView)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo account = this.GetOneSessionAccount();
			  if(account.getOrganizaType().equals("20001")){
				  att_MdInventoryView.setRbaId(account.getOldId());
			  }else if(account.getOrganizaType().equals("20002")){
				  att_MdInventoryView.setRbsId(account.getOldId());
				  
			  }else if(account.getOrganizaType().equals("20003")){
				  att_MdInventoryView.setRbbId(account.getOldId());
			  }
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			DecimalFormat df= new DecimalFormat("######0.00"); 
			DecimalFormat df2= new DecimalFormat("######0"); 
			Map<String, Object> dataMap = new HashMap<String,Object>();
			int rowCount=4;
			dataMap.put("orgName", account.getOrgName());
			dataMap.put("timeArea", sdf.format(new Date()));
			dataMap.put("userName", account.getUserName());
			List<MdInventoryView> list = mdInventoryDao.getListByMdInventoryView(att_MdInventoryView);
			List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
			int index=0;
			  if(list != null && list.size() > 0){
				  for(MdInventoryView obj: list){
					  index++;
					  Map<String,String> reMap=new HashMap<String,String>();
					  reMap.put("index", index+"");
					  reMap.put("matName",obj.getMatName());
					 // reMap.put("brand", obj.getBrand());
					  reMap.put("mmfName", obj.getMmfName());
					  //reMap.put("typeName", obj.getTypeName()!=null?obj.getTypeName():"");
					  reMap.put("quantity", df2.format(obj.getQuantity()));
					  reMap.put("basicUnit", obj.getBasicUnit());
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
				Template t = configuration.getTemplate("exportInventoryList.ftl");
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
			
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
		}
		return srm;
	}

	@Override
	public PagerModel getExtendPagerModel(Integer wiId,String relatedCode, String startDate,String endDate) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdInventoryExtend>());
		  try{
				List<MdInventoryExtend> list=mdInventoryExtendDao.getMdInventoryExtendByWiIdToSel(wiId,relatedCode, startDate, endDate);
				pm.setItems(list);
				pm.setRows(list);
		    } catch (Exception e) {
				e.printStackTrace(); 
	        }
		  return pm;
	}

	@Override
	public PagerModel getExtendEnterViewPagerModel(Integer wiId, String relatedCode, String startDate, String endDate) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdInventoryExtend>());
		try{
			List<MdInventoryExtend> list=mdInventoryExtendDao.getExtendEnterViewPagerModel(wiId,relatedCode, startDate, endDate);
			pm.setItems(list);
			pm.setRows(list);
			pm.setTotalCount(list.size());
			pm.setTotal(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public SysRetrunMessage saveImpData(String fileCode) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			Date star_date=new Date();
			//查看当前组织是否存在集团、医院
			SysUserInfo account = this.GetOneSessionAccount();
			SysOrgGx sysOrgGx=new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String,String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			Integer one=null;
			Integer two=null;
			Integer three=null;
			String purchaseType="";
			if(account.getOrganizaType().equals("20001")){//如果当前账户为集团账户
				one = account.getOldId();
				purchaseType="2";
			}else if(account.getOrganizaType().equals("20002")){//如果当前账户为医院账户
				if(orgMap.containsKey("one"))//如果存在上级集团
					one = Integer.parseInt(orgMap.get("one"));
				two = account.getOldId();
				purchaseType="3";
			}else if(account.getOrganizaType().equals("20003")){//如果当前账户为门诊账户
				if(orgMap.containsKey("one"))//如果存在上级集团
					one = Integer.parseInt(orgMap.get("one"));
				if(orgMap.containsKey("tow"))//如果存在上级医院
					two = Integer.parseInt(orgMap.get("tow"));
				three = account.getOldId();
				purchaseType="4";
			}
			//文件读取
			SysFileInfo sfi=new SysFileInfo();
			sfi.setFileCode(fileCode);
		    Object obj=this.getOne(sfi);
		    if(obj!=null){
		    	sfi=(SysFileInfo) obj;
		    	ReadExcel re=new ReadExcel();
		    	List<List<String>> list_read= re.readExcel(sfi.getFilePath());
		    	List<Object> errorList = new ArrayList<Object>();
		    	int tureCount=0;
		    	for(List<String> did:list_read){
	    			 if(did != null && did.size() > 0){
	    				 String errorStr=this.checkImpData(did);
	    				 if(errorStr.equals("")){
	    					 MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
	    					 mdMaterielInfo.setWzId(account.getOldId());
	    					 mdMaterielInfo.setPurchaseType(purchaseType);
	    					 mdMaterielInfo.setMatCode(CreateCodeUtil.getNo("MAT"));
	    					 mdMaterielInfo.setMatName(did.get(0).trim());
	    					 mdMaterielInfo.setApplicantName(did.get(2));
	    					 
	    					 /**
	    						 * yanglei
	    						 * 修改字段ProductNumber 改为ProductFactory set这个字段时用到
	    						 */
	    					 mdMaterielInfo.setProductFactory(did.get(3));
	    					 mdMaterielInfo.setBrand(did.get(4));
	    					 mdMaterielInfo.setBasicUnit(did.get(5));
	    					 mdMaterielInfo.setNorm(did.get(1).trim());
	    					 mdMaterielInfo.setLabelInfo(did.get(6));
	    					 mdMaterielInfo.setMoney1(Double.parseDouble(did.get(7).trim()));
	    					 mdMaterielInfo.setState("1");
	    					 this.newObject(mdMaterielInfo);
	    					 MdMaterielFormat mdMaterielFormat = new MdMaterielFormat();
	    					 mdMaterielFormat.setWmsMiId(mdMaterielInfo.getWmsMiId());
	    					 mdMaterielFormat.setMmfCode(CreateCodeUtil.getNo("MMF"));
	    					 mdMaterielFormat.setMmfName(did.get(1).trim());
	    					 mdMaterielFormat.setPrice(Double.parseDouble(did.get(7).trim()));
	    					 mdMaterielFormat.setState("1");
	    					 this.newObject(mdMaterielFormat);
	    					 MdItemKey mdItemKey = new MdItemKey();
	    					 mdItemKey.setRbaId(one);//从session中获取集团ID
	    					 mdItemKey.setRbsId(two);//从session中获取医院ID
	    					 mdItemKey.setRbbId(three);//从session中获取门诊ID
	    					 mdItemKey.setPurchaseType((Integer.parseInt(purchaseType)-1)+"");//从session中采购单位类型
	    					 mdItemKey.setMatName(mdMaterielInfo.getMatName());
	    					 mdItemKey.setMmfName(mdMaterielFormat.getMmfName());
	    					 String mmfNamePy = "";
	    					 String matNamePy = "";
	    					 if(!mdMaterielFormat.getMmfName().trim().equals("")){
		    					 for(int i =0;i < mdMaterielFormat.getMmfName().trim().length();i++)
		    						 mmfNamePy+=PingYinUtil.getLetterFormChinese(mdMaterielFormat.getMmfName().trim().charAt(i));
	    					 }
	    					 if(!mdMaterielInfo.getMatName().trim().equals("")){
		    					 for(int i =0;i < mdMaterielInfo.getMatName().trim().length();i++)
		    						 matNamePy+=PingYinUtil.getLetterFormChinese(mdMaterielInfo.getMatName().trim().charAt(i));
	    					 }
	    					 mdItemKey.setMatNamePy(matNamePy);
	    					 mdItemKey.setMmfNamePy(mmfNamePy);
	    					 this.newObject(mdItemKey);
	    					 MdItemMx new_MdItemMx = new MdItemMx();
	    					 new_MdItemMx.setItemKeyId(mdItemKey.getItemKeyId());
	    					 new_MdItemMx.setMmfId(mdMaterielFormat.getMmfId());
	    					 new_MdItemMx.setWmsMiId(mdMaterielInfo.getWmsMiId());
	    					 this.newObject(new_MdItemMx);
	    					//增加库存
	    					MdInventory att_MdInventory = new MdInventory();
	    					att_MdInventory.setRbaId(one);//从session中获取集团ID
	    					att_MdInventory.setRbsId(two);//从session中获取医院ID
	    					att_MdInventory.setRbbId(three);//从session中获取门诊ID
	    					att_MdInventory.setUnit(mdMaterielInfo.getBasicUnit());
	    					att_MdInventory.setPurchaseType((Integer.parseInt(purchaseType)-1)+"");//从session中采购单位类型
	    					//att_MdInventory.setWmsMiId(mdMaterielInfo.getWmsMiId());
	    					//att_MdInventory.setMmfId(mdMaterielFormat.getMmfId());
	    					att_MdInventory.setItemKeyId(mdItemKey.getItemKeyId()+"");
	    					att_MdInventory.setState("1");
	    					att_MdInventory.setBasicUnit(mdMaterielInfo.getBasicUnit());
	    					att_MdInventory.setQuantity(Double.parseDouble(did.get(8).trim()));
	    					att_MdInventory.setRatio(1d);
	    					att_MdInventory.setBaseNumber(att_MdInventory.getQuantity());
	    					this.newObject(att_MdInventory);
	    					//保存库存明细信息
	    					MdInventoryExtend att_MdInventoryExtend=new MdInventoryExtend();
	    					att_MdInventoryExtend.setWiId(att_MdInventory.getWiId());
	    					att_MdInventoryExtend.setWmsMiId(mdMaterielInfo.getWmsMiId());
	    					att_MdInventoryExtend.setMmfId(mdMaterielFormat.getMmfId());
	    					att_MdInventoryExtend.setBasicUnit(att_MdInventory.getBasicUnit());
	    					att_MdInventoryExtend.setUnit(att_MdInventory.getUnit());
	    					att_MdInventoryExtend.setQuantity(att_MdInventory.getQuantity());
	    					att_MdInventoryExtend.setBaseNumber(att_MdInventory.getBaseNumber());
	    					att_MdInventoryExtend.setPrice(mdMaterielFormat.getPrice());
	    					att_MdInventoryExtend.setBasePrice(mdMaterielFormat.getPrice());
	    					att_MdInventoryExtend.setRelatedCode(null);
	    					att_MdInventoryExtend.setPurchaseUser(null);
	    					att_MdInventoryExtend.setCreateDate(new Date());
	    					att_MdInventoryExtend.setEditDate(new Date());
	    					att_MdInventoryExtend.setMmfName(mdMaterielFormat.getMmfName());
	    					att_MdInventoryExtend.setMatName(mdMaterielInfo.getMatName());
	    					/**
	    					 * yanglei
	    					 * 修改字段ProductNumber 改为ProductFactory get这个字段时用到
	    					 */
	    					att_MdInventoryExtend.setProductName(mdMaterielInfo.getProductFactory());
	    					att_MdInventoryExtend.setBrand(mdMaterielInfo.getBrand());
	    					att_MdInventoryExtend.setLabelInfo(mdMaterielInfo.getLabelInfo());
	    					att_MdInventoryExtend.setApplicantName(mdMaterielInfo.getApplicantName());
	    					att_MdInventoryExtend.setState("1");
	    					this.newObject(att_MdInventoryExtend);
	    					tureCount++;
	    				 }else{
	    					 ErrorImpDataModel errorImpDataModel = new ErrorImpDataModel();
	    					 errorImpDataModel.setMatName(did.get(0));
	    					 if(did.size() >1)
	    						 errorImpDataModel.setMmfName(did.get(1));
	    					 if(did.size() >2)
	    						 errorImpDataModel.setApplicantName(did.get(2));
	    					 if(did.size() >3)
	    						 errorImpDataModel.setProductName(did.get(3));
	    					 if(did.size() >4)
	    						 errorImpDataModel.setBrand(did.get(4));
	    					 if(did.size() >5)
	    						 errorImpDataModel.setUnit(did.get(5));
	    					 if(did.size() >6)
	    						 errorImpDataModel.setMatType(did.get(6));
	    					 if(did.size() >7)
	    						 errorImpDataModel.setPrice(did.get(7));
	    					 if(did.size() >8)
	    						 errorImpDataModel.setNum(did.get(8));
	    					errorImpDataModel.setErrorStr(errorStr);
	    					errorList.add(errorImpDataModel);
	    				 }
	    			 }
	    		 }
		    	Map<String,Object> reMap = new HashMap<String,Object>();
		    	reMap.put("rightCount", tureCount);
		    	if(errorList.size()>0){
	    			 reMap.put("errorCount", errorList.size());
	    			 //创建错误文件
	    			 srm.setCode(2l);
	    			 Calendar now = Calendar.getInstance();
	    			 long l = now.getTimeInMillis();
	    			 String tmpPath = SystemContext.updown_File_path+TEMP_EXPORT_PATH;//获得工程运行web的目录
	    			 File file = new File(tmpPath);
	    			 if(!file.exists()) {
	    				file.mkdirs();
	    			 }
	    		     String realPath = tmpPath +l+ ".xls";
	    		     ExportExcel<Object> ex = new ExportExcel<Object>();
	    			 String[] headers = {"物料名称","型号名称","供应商","生产厂家","品牌","单位","物料类别","单价","数量","错误信息"};
	    			 OutputStream out = null;
	    			 out = new FileOutputStream(realPath);
	    			 ex.exportExcel("错误数据",headers, errorList, out,"yyyy-MM-dd HH:mm:ss");
	    			 out.close();
	    			 String rootUrl = request.getContextPath() + EXPORT_PATH+l+ ".xls";
	    			 reMap.put("rootPath", rootUrl);
	 				 reMap.put("filePath", realPath);
	    		 }
		    	Date end_date=new Date();
	    		long interval = (end_date.getTime() - star_date.getTime())/1000;
	    		reMap.put("timeCount", interval);
	    		srm.setObj(reMap);
		    }
	    } catch (Exception e) {
			e.printStackTrace(); 
			srm.setCode(0l);
			srm.setMeg("操作失败！");
			throw new  HSKException(e);
        }
		return srm;
	}
	
	private String checkImpData(List<String> did){
		String errorStr="";
		if(did.get(0)==null || "".equals(did.get(0).trim()))
			errorStr="物料名称为空";
		else if(did.size() ==1 || did.get(1)==null || "".equals(did.get(1).trim()))
			errorStr="型号名称为空";
		else if(did.size() <8 || did.get(7)==null || "".equals(did.get(7).trim())){
			errorStr="单价为空";
		}else if(!CheckUtil.isNumeric(did.get(7).trim())){
			errorStr="单价格式不正确";
		}else if(did.size() < 9 || did.get(8)==null || "".equals(did.get(8).trim())){
			errorStr="数量为空";
		}else if(!CheckUtil.isNumeric(did.get(8).trim())){
			errorStr="数量格式不正确";
		}
		return errorStr;
	}

	@Override
	public SysRetrunMessage deleteExtendObject(Integer mieId)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			MdInventoryExtend att_MdInventoryExtend=new MdInventoryExtend();
			att_MdInventoryExtend.setMieId(mieId);
			att_MdInventoryExtend=(MdInventoryExtend) this.getOne(att_MdInventoryExtend);
			att_MdInventoryExtend.setState("0");
			this.updateObject(att_MdInventoryExtend);
		}catch(Exception e){
			e.printStackTrace();
		}
		return srm;
	}

	@Override
	public PagerModel getExtendViewPagerModel(MdInventoryExtendView mdInventoryExtendView, String startDate,String endDate) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<MdInventoryExtendView>());
		try{
			pm= mdInventoryExtendDao.getMdInventoryViewPager(mdInventoryExtendView, startDate, endDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public SysRetrunMessage addItemKeyMx(Integer wiId, String addWiIds)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			MdInventory  att_MdInventory= mdInventoryDao.findMdInventoryById( wiId) ;
			Double quantityAll = 0d;
			Double baseNumberAll = 0d;
			String[] addWiIdArray = addWiIds.split(",");
			for(String addWiId : addWiIdArray){
				MdInventory add_MdInventory= mdInventoryDao.findMdInventoryById(Integer.parseInt(addWiId)) ;
				if(!add_MdInventory.getRatio().equals(att_MdInventory.getRatio())){
					Exception e = new Exception("折算系数不一致不允许合并");
					throw new  HSKException(e);
				}
				MdItemMx addItemMx = new MdItemMx();
				addItemMx.setItemKeyId(Integer.parseInt(add_MdInventory.getItemKeyId()));
				List<MdItemMx> addItemMxList = this.getList(addItemMx);
				if(addItemMxList != null && addItemMxList.size() > 0){
					for(MdItemMx mx : addItemMxList){
						mx.setItemKeyId(Integer.parseInt(att_MdInventory.getItemKeyId()));
						this.updateObject(mx);
					}
					//修改库存明细
					MdInventoryExtend mdInventoryExtend = new MdInventoryExtend();
					mdInventoryExtend.setWiId(add_MdInventory.getWiId());
					List<MdInventoryExtend> mdInventoryExtendList = this.getList(mdInventoryExtend);
					if(mdInventoryExtendList != null && mdInventoryExtendList.size() > 0){
						for(MdInventoryExtend extend : mdInventoryExtendList){
							extend.setBasicUnit(att_MdInventory.getBasicUnit());
							extend.setWiId(att_MdInventory.getWiId());
							quantityAll += extend.getQuantity();
							baseNumberAll += extend.getBaseNumber();
							this.updateObject(extend);
						}
					}
				}
				MdItemKey addItemKey = new MdItemKey();
				addItemKey.setItemKeyId(Integer.parseInt(add_MdInventory.getItemKeyId()));
				addItemKey = (MdItemKey) this.getOne(addItemKey);
				this.deleteObjects(addItemKey);
				this.deleteObjects(add_MdInventory);
			}
			att_MdInventory.setBaseNumber(att_MdInventory.getBaseNumber() + baseNumberAll);
			att_MdInventory.setQuantity(att_MdInventory.getBaseNumber()/att_MdInventory.getRatio());
			this.updateObject(att_MdInventory);
			
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("折算系数不一致不允许合并!");
			throw new  HSKException(e);
		}
		return srm;
	}

	@Override
	public SysRetrunMessage delItemKeyMx(Integer wiId, Integer mimId)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			//查询现有的库存
			MdInventory  att_MdInventory= mdInventoryDao.findMdInventoryById( wiId) ;
			//查询itemkey
			MdItemKey att_MdItemKey = new MdItemKey();
			att_MdItemKey.setItemKeyId(Integer.parseInt(att_MdInventory.getItemKeyId()));
			att_MdItemKey = (MdItemKey) this.getOne(att_MdItemKey);
			//查询itemkey明细
			MdItemMx att_MdItemMx = new MdItemMx();
			att_MdItemMx.setMimId(mimId);
			att_MdItemMx = (MdItemMx) this.getOne(att_MdItemMx);
			//查询商品信息
			MdMaterielFormatView formatView = new MdMaterielFormatView();
			formatView.setMmfId(att_MdItemMx.getMmfId());
			formatView = (MdMaterielFormatView) this.getOne(formatView);
			//新建库存信息
			MdItemKey new_MdItemKey= new MdItemKey();
			new_MdItemKey.setRbaId(att_MdItemKey.getRbaId());
			new_MdItemKey.setRbsId(att_MdItemKey.getRbsId());
			new_MdItemKey.setRbbId(att_MdItemKey.getRbbId());
			new_MdItemKey.setPurchaseType(att_MdItemKey.getPurchaseType());
			new_MdItemKey.setMatName(formatView.getMatName());
			new_MdItemKey.setMmfName(formatView.getMmfName());
			String mmfNamePy = "";
			String matNamePy = "";
			if(!formatView.getMmfName().trim().equals("")){
				  for(int i =0;i < formatView.getMmfName().trim().length();i++)
					  mmfNamePy+=PingYinUtil.getLetterFormChinese(formatView.getMmfName().trim().charAt(i));
			}
			if(!formatView.getMatName().trim().equals("")){
				  for(int i =0;i < formatView.getMatName().trim().length();i++)
					  matNamePy+=PingYinUtil.getLetterFormChinese(formatView.getMatName().trim().charAt(i));
			}
			new_MdItemKey.setMatNamePy(matNamePy);
			new_MdItemKey.setMmfNamePy(mmfNamePy);
			
			this.newObject(new_MdItemKey);
			MdInventory new_MdInventory=new MdInventory();
			new_MdInventory.setRbaId(att_MdInventory.getRbaId());
			new_MdInventory.setRbsId(att_MdInventory.getRbsId());
			new_MdInventory.setRbbId(att_MdInventory.getRbbId());
			new_MdInventory.setPurchaseType(att_MdInventory.getPurchaseType());
			new_MdInventory.setItemKeyId(new_MdItemKey.getItemKeyId()+"");
			new_MdInventory.setState("1");
			new_MdInventory.setRatio(att_MdInventory.getRatio());
			new_MdInventory.setUnit(att_MdInventory.getUnit());
			new_MdInventory.setBasicUnit(att_MdInventory.getBasicUnit());
			new_MdInventory.setWarningShu(att_MdInventory.getWarningShu());
			new_MdInventory.setMaxShu(att_MdInventory.getMaxShu());
			new_MdInventory.setMinDay(att_MdInventory.getMinDay());
			this.newObject(new_MdInventory);
			att_MdItemMx.setItemKeyId(new_MdItemKey.getItemKeyId());
			this.updateObject(att_MdItemMx);
			//修改库存明细
			MdInventoryExtend mdInventoryExtend = new MdInventoryExtend();
			mdInventoryExtend.setWiId(wiId);
			mdInventoryExtend.setMmfId(att_MdItemMx.getMmfId());
			List<MdInventoryExtend> mdInventoryExtendList = this.getList(mdInventoryExtend);
			if(mdInventoryExtendList != null && mdInventoryExtendList.size() > 0){
				Double baseNumberAll = 0d;
				for(MdInventoryExtend extend : mdInventoryExtendList){
					extend.setBasicUnit(att_MdInventory.getBasicUnit());
					extend.setWiId(new_MdInventory.getWiId());
					baseNumberAll += extend.getBaseNumber();
					this.updateObject(extend);
				}
				//修改库存数量
				new_MdInventory.setBaseNumber(baseNumberAll);
				new_MdInventory.setQuantity(baseNumberAll/new_MdInventory.getRatio());
				this.updateObject(new_MdInventory);
				att_MdInventory.setBaseNumber(att_MdInventory.getBaseNumber()-baseNumberAll);
				att_MdInventory.setQuantity(att_MdInventory.getBaseNumber()/att_MdInventory.getRatio());
				this.updateObject(att_MdInventory);
			}
					
		}catch(Exception e){
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("操作失败!");
			throw new  HSKException(e);
		}
		return srm;
	}

	@Override
	public PagerModel getItemKeyMxList(Integer wiId) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList<MdItemMxView>());
		try{
			MdInventory  att_MdInventory= mdInventoryDao.findMdInventoryById( wiId) ;
			MdItemMxView itemMx = new MdItemMxView();
			itemMx.setItemKeyId(Integer.parseInt(att_MdInventory.getItemKeyId()));
			List<MdItemMxView> itemMxList = this.getList(itemMx);
			pm.setItems(itemMxList);
			pm.setRows(itemMxList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pm;
	}
}