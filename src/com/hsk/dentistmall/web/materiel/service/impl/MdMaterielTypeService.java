package com.hsk.dentistmall.web.materiel.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.api.daobbase.IMdMaterielInfoDao;
import com.hsk.dentistmall.api.daobbase.IMdMaterielTypeDao;
import com.hsk.dentistmall.api.persistence.MdMaterielInfo;
import com.hsk.dentistmall.api.persistence.MdMaterielType;
import com.hsk.dentistmall.web.materiel.service.IMdMaterielTypeService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.supper.until.json.JsonUtil;
import com.hsk.xframe.api.service.imp.DSTService;

/** 
	MdMaterielType业务操作实现类 
* @author  作者:admin
* @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:16
*/
@Service
public class MdMaterielTypeService extends DSTService implements IMdMaterielTypeService {
	/**
	   *业务处理dao类  mdMaterielTypeDao 
	   */
		@Autowired
		protected IMdMaterielTypeDao mdMaterielTypeDao;
		@Autowired
		protected IMdMaterielInfoDao mdMaterielInfoDao;
		private static String JSON_URL="/javaScript/dentistmall/mdMaterielType.js";


	 /**
		 * 根据md_materiel_type表主键删除MdMaterielType对象记录
	     * @param  mmtId  Integer类型(md_materiel_type表主键)
		 * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */

		public SysRetrunMessage findFormObject(Integer mmtId) throws HSKException{
		        SysRetrunMessage srm=new SysRetrunMessage(1l);
				  	
				try{
			   			MdMaterielType     att_MdMaterielType= mdMaterielTypeDao.findMdMaterielTypeById( mmtId) ;
						srm.setObj(att_MdMaterielType);
					} catch (HSKDBException e) {
						e.printStackTrace(); 
						srm.setCode(0l);
						srm.setMeg(e.getMessage()); 
					}
					 return srm ;
		} 

	    /**
		 * 根据md_materiel_type表主键删除MdMaterielType对象记录
	     * @param  mmtId  Integer类型(md_materiel_type表主键)
		 * @throws HSKException
		 */

		public MdMaterielType findObject(Integer mmtId) throws HSKException{
				MdMaterielType  att_MdMaterielType=new MdMaterielType();		
				try{
			        att_MdMaterielType= mdMaterielTypeDao.findMdMaterielTypeById( mmtId) ;
					} catch (HSKDBException e) {
						e.printStackTrace(); 
						throw new  HSKException(e);
					}
					return  att_MdMaterielType;
		}
		
		 
		 /**
		 * 根据md_materiel_type表主键删除MdMaterielType对象记录
	     * @param  mmtId  Integer类型(md_materiel_type表主键)
	     * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */
		public SysRetrunMessage deleteObject(Integer mmtId) throws HSKException{  
			    SysRetrunMessage srm=new SysRetrunMessage(1l);
			   try{ 
					mdMaterielTypeDao.deleteMdMaterielTypeById(mmtId);
					writJsonData();
				 } catch (Exception e) {
						e.printStackTrace(); 
						throw new  HSKException(e);
			     }
			   return srm;  
		}
		
		/**
		 * 根据md_materiel_type表主键删除多条MdMaterielType对象记录
	     * @param  RbaIds  Integer类型(md_materiel_type表主键)
	     * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */
		public SysRetrunMessage deleteAllObject(String mmtIds) throws HSKException{
			SysRetrunMessage srm = new SysRetrunMessage(1l);
			try {
				String[] ids = mmtIds.split(",");
				for (String did : ids) {
					if (did != null && !"".equals(did.trim())) {
						mdMaterielTypeDao.deleteMdMaterielTypeById(Integer
								.valueOf(did));
					}
				}
				writJsonData();
			} catch (NumberFormatException e) {
				e.printStackTrace();
				HSKException hse = new HSKException(e);
				hse.setDisposeType("01");
				throw hse;
			} catch (Exception e) {
				e.printStackTrace();
				throw new HSKException(e);
			}
			return srm;	  
		 }
		 
	 
		 /**
		 * 新增或修改md_materiel_type表记录 ,如果md_materiel_type表主键MdMaterielType.RbaId为空就是添加，如果非空就是修改
	     * @param  att_MdMaterielType  MdMaterielType类型(md_materiel_type表记录)
	     * @return MdMaterielType  修改后的MdMaterielType对象记录
		 * @throws HSKDBException
		 */
		public SysRetrunMessage saveOrUpdateObject( MdMaterielType att_MdMaterielType) throws HSKException{
				 SysRetrunMessage srm = new SysRetrunMessage(1l);
				  try{
					    String level="1";
					    String path="";
					  	if(att_MdMaterielType.getMdMmtId()!=null){
					  		MdMaterielType pMdMaterielType = mdMaterielTypeDao.findMdMaterielTypeById(att_MdMaterielType.getMdMmtId());
					  		if(pMdMaterielType.getMmtLevel().equals("1"))
					  			level="2";
					  		else if(pMdMaterielType.getMmtLevel().equals("2"))
					  			level="3";
					  		path = pMdMaterielType.getMmtPath();
					  	}
					  	if(level.equals("1"))
					  		path="/";
					  	att_MdMaterielType.setMmtLevel(level);
					  	if(att_MdMaterielType.getMmtId()!= null){
					  		att_MdMaterielType.setMmtPath(path+att_MdMaterielType.getMmtId()+"/");
					  		mdMaterielTypeDao.saveOrUpdateMdMaterielType(att_MdMaterielType); 
					  	}else{
					  		mdMaterielTypeDao.saveOrUpdateMdMaterielType(att_MdMaterielType); 
					  		att_MdMaterielType.setMmtPath(path+att_MdMaterielType.getMmtId()+"/");
					  		mdMaterielTypeDao.saveOrUpdateMdMaterielType(att_MdMaterielType); 
					  	}
						srm.setObj(att_MdMaterielType);
						this.writJsonData();
				    } catch (Exception e) {
						e.printStackTrace(); 
						throw new HSKException(e);
			        }
				return srm;	  
		}
		
		 
		/**
		 *根据MdMaterielType对象作为对(md_materiel_type表进行查询，获取分页对象
	     * @param  att_MdMaterielType  MdMaterielType类型(md_materiel_type表记录)
	     * @return PagerModel  分页对象
		 * @throws HSKException 
		 */
		public PagerModel getPagerModelObject (MdMaterielType att_MdMaterielType) throws HSKException{
			PagerModel pm=new PagerModel(new ArrayList<MdMaterielType>());
				  try{
						pm=mdMaterielTypeDao.getPagerModelByMdMaterielType(att_MdMaterielType);
				    } catch (Exception e) {
						e.printStackTrace(); 
			        }
			return pm;
		}

		@Override
		public List<Map<Object, Object>> getTreeListByMdMmtId(Integer id)throws HSKException {
			List<Map<Object, Object>> treeList = new ArrayList<Map<Object, Object>>();
			try {
				
				List<Map<Object, Object>> list = mdMaterielTypeDao.getTreeListByMdMmtId(id);
				if(list != null && list.size() > 0){
					for(Map<Object, Object> map : list){
						Map<Object, Object> treeMap = new HashMap<Object,Object>();
						treeMap.put("id", map.get("mmt_id"));
						treeMap.put("name", map.get("mmt_name"));
						if(id==null)
							treeMap.put("pId", 0);
						else
							treeMap.put("pId", id);
						if(Integer.parseInt(map.get("shu").toString()) > 0)
							treeMap.put("isParent", true);
						else
							treeMap.put("isParent", false);
						treeList.add(treeMap);
					}
				}
			} catch (HSKDBException e) {
				e.printStackTrace();
			}
			return treeList;
		} 
		/**
		 * 将商品分类信息写入JSON 文件中
		 * @throws Exception 
		 */
		private void writJsonData() throws Exception{
			MdMaterielType  att_MdMaterielType=new MdMaterielType();
			att_MdMaterielType.setState("1");
			List<Map<String,Object>> reList = new ArrayList<Map<String,Object>>();
			List<MdMaterielType> mdMaterielTypeList = mdMaterielTypeDao.getListByMdMaterielType(att_MdMaterielType);
			if(mdMaterielTypeList != null && mdMaterielTypeList.size() > 0)
				reList = this.initJsonData(mdMaterielTypeList, null);
			//ObjectMapper mapper = new ObjectMapper();
			String str = "var _sortList="+JsonUtil.getjsonstr(reList);
			FileOutputStream fos = new FileOutputStream(request.getSession().getServletContext().getRealPath("/")+ JSON_URL);   
		    OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");   
		    osw.write(str);
		    osw.flush();   
		}
		
		private List<Map<String,Object>> initJsonData(List<MdMaterielType> mdMaterielTypeList,Integer pId){
			List<Map<String,Object>> reList = new ArrayList<Map<String,Object>>();
			if(pId==null){
				for(MdMaterielType mdMaterielType : mdMaterielTypeList){
					if(mdMaterielType.getMdMmtId() == null){
						Map<String,Object> reMap = new HashMap<String,Object>();
						reMap.put("id", mdMaterielType.getMmtId());
						reMap.put("name", mdMaterielType.getMmtName());
						List<Map<String,Object>> childList = initJsonData(mdMaterielTypeList,mdMaterielType.getMmtId());
						if(childList != null && childList.size() > 0)
							reMap.put("childList", childList);
						reList.add(reMap);
					}
				}
			}else{
				for(MdMaterielType mdMaterielType : mdMaterielTypeList){
					if(mdMaterielType.getMdMmtId() !=null && mdMaterielType.getMdMmtId().equals(pId)){
						Map<String,Object> reMap = new HashMap<String,Object>();
						reMap.put("id", mdMaterielType.getMmtId());
						reMap.put("name", mdMaterielType.getMmtName());
						reMap.put("pId", mdMaterielType.getMdMmtId());
						List<Map<String,Object>> childList = initJsonData(mdMaterielTypeList,mdMaterielType.getMmtId());
						if(childList != null && childList.size() > 0)
							reMap.put("childList", childList);
						reList.add(reMap);
					}
				}
			}
			return reList;
		}

		@Override
		public SysRetrunMessage saveDataToJosn() throws HSKException {
			SysRetrunMessage srm = new SysRetrunMessage(1l);
			try{
				this.writJsonData();
			}catch(Exception e){
				srm.setCode(0l);
				srm.setMeg("操作失败!");
			}
			return srm;
		}

		@Override
		public List<MdMaterielType> getListObject(
				MdMaterielType att_MdMaterielType) throws HSKException {
			List<MdMaterielType> reList = new ArrayList<MdMaterielType>();
			att_MdMaterielType.setState("1");
			try {
				reList = mdMaterielTypeDao.getListByMdMaterielType(att_MdMaterielType);
			} catch (HSKDBException e) {
				e.printStackTrace();
			}
			return reList;
		}

		@Override
		public List<MdMaterielType> getListObjectByMatName(
				MdMaterielType att_MdMaterielType, String matName)
				throws HSKException {
			List<MdMaterielType> reList = new ArrayList<MdMaterielType>();
			att_MdMaterielType.setState("1");
			try {
				if(matName!= null && !matName.trim().equals("")){
					MdMaterielInfo mdMaterielInfo = new MdMaterielInfo();
					mdMaterielInfo.setPurchaseType("1");
					mdMaterielInfo.setState("1");
					mdMaterielInfo.setMatName(matName.trim());
					mdMaterielInfo.setTab_like("matName");
					List<MdMaterielInfo> infoList = mdMaterielInfoDao.getListByMdMaterielInfo(mdMaterielInfo);
					if(infoList != null && infoList.size() > 0){
						String ids=",";
						for(MdMaterielInfo info : infoList){
							if(!ids.contains(","+info.getMdWmsMiId()+","))
							 ids +=info.getMdWmsMiId()+",";
						}
						ids = ids.substring(1, ids.length()-1);
						att_MdMaterielType.setMmtIds(ids);
					}else
						return reList;
				}
				reList = mdMaterielTypeDao.getListByMdMaterielType(att_MdMaterielType);
			} catch (HSKDBException e) {
				e.printStackTrace();
			}
			return reList;
		}
}
