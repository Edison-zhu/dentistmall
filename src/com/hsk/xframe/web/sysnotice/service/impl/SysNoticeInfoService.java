package com.hsk.xframe.web.sysnotice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.ISysNoticeInfoDao;
import com.hsk.xframe.api.persistence.SysNoticeInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.web.sysnotice.service.ISysNoticeInfoService;

/** 
	SysNoticeInfo业务操作实现类 
* @author  作者:admin
* @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:16
*/
@Service
public class SysNoticeInfoService extends DSTService implements ISysNoticeInfoService {
	/**
	   *业务处理dao类  sysNoticeInfoDao 
	   */
		@Autowired
		protected ISysNoticeInfoDao sysNoticeInfoDao;


	 /**
		 * 根据md_materiel_type表主键删除SysNoticeInfo对象记录
	     * @param  sniId  Integer类型(md_materiel_type表主键)
		 * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */

		public SysRetrunMessage findFormObject(Integer sniId) throws HSKException{
		        SysRetrunMessage srm=new SysRetrunMessage(1l);
				  	
				try{
			   			SysNoticeInfo     att_SysNoticeInfo= sysNoticeInfoDao.findSysNoticeInfoById( sniId) ;
						srm.setObj(att_SysNoticeInfo);
					} catch (HSKDBException e) {
						e.printStackTrace(); 
						srm.setCode(0l);
						srm.setMeg(e.getMessage()); 
					}
					 return srm ;
		} 

	    /**
		 * 根据md_materiel_type表主键删除SysNoticeInfo对象记录
	     * @param  sniId  Integer类型(md_materiel_type表主键)
		 * @throws HSKException
		 */

		public SysNoticeInfo findObject(Integer sniId) throws HSKException{
				SysNoticeInfo  att_SysNoticeInfo=new SysNoticeInfo();		
				try{
			        att_SysNoticeInfo= sysNoticeInfoDao.findSysNoticeInfoById( sniId) ;
					} catch (HSKDBException e) {
						e.printStackTrace(); 
						throw new  HSKException(e);
					}
					return  att_SysNoticeInfo;
		}
		
		 
		 /**
		 * 根据md_materiel_type表主键删除SysNoticeInfo对象记录
	     * @param  sniId  Integer类型(md_materiel_type表主键)
	     * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */
		public SysRetrunMessage deleteObject(Integer sniId) throws HSKException{  
			    SysRetrunMessage srm=new SysRetrunMessage(1l);
			   try{ 
					sysNoticeInfoDao.deleteSysNoticeInfoById(sniId);
				 } catch (Exception e) {
						e.printStackTrace(); 
						throw new  HSKException(e);
			     }
			   return srm;  
		}
		
		/**
		 * 根据md_materiel_type表主键删除多条SysNoticeInfo对象记录
	     * @param  RbaIds  Integer类型(md_materiel_type表主键)
	     * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */
		public SysRetrunMessage deleteAllObject(String sniIds) throws HSKException{
			SysRetrunMessage srm = new SysRetrunMessage(1l);
			try {
				String[] ids = sniIds.split(",");
				for (String did : ids) {
					if (did != null && !"".equals(did.trim())) {
						sysNoticeInfoDao.deleteSysNoticeInfoById(Integer
								.valueOf(did));
					}
				}
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
		 * 新增或修改md_materiel_type表记录 ,如果md_materiel_type表主键SysNoticeInfo.RbaId为空就是添加，如果非空就是修改
	     * @param  att_SysNoticeInfo  SysNoticeInfo类型(md_materiel_type表记录)
	     * @return SysNoticeInfo  修改后的SysNoticeInfo对象记录
		 * @throws HSKDBException
		 */
		public SysRetrunMessage saveOrUpdateObject( SysNoticeInfo att_SysNoticeInfo) throws HSKException{
				 SysRetrunMessage srm = new SysRetrunMessage(1l);
				  try{
					  	  if(att_SysNoticeInfo.getSniId()!=null)
					  		  this.updateObject(att_SysNoticeInfo);
					  	  else
					  		  this.newObject(att_SysNoticeInfo);
						  srm.setObj(att_SysNoticeInfo);
				    } catch (Exception e) {
						e.printStackTrace(); 
						throw new HSKException(e);
			        }
				return srm;	  
		}
		/**
		 * 更改数据状态
		 * @param sniId
		 * @param state
		 * @return
		 * @throws HSKException
		 */
		@Override
		public SysRetrunMessage updateObjectState(Integer sniId, String state)
				throws HSKException {
			SysRetrunMessage srm = new SysRetrunMessage(1l);
			try{
				SysNoticeInfo att_SysNoticeInfo = sysNoticeInfoDao.findSysNoticeInfoById(sniId);
				att_SysNoticeInfo.setState(state);
				this.updateObject(att_SysNoticeInfo);
			}catch(Exception e){
				e.printStackTrace();
				srm.setCode(0l);
				srm.setMeg("操作失败!");
			}
			return srm;
		}
		 
		/**
		 *根据SysNoticeInfo对象作为对(md_materiel_type表进行查询，获取分页对象
	     * @param  att_SysNoticeInfo  SysNoticeInfo类型(md_materiel_type表记录)
	     * @return PagerModel  分页对象
		 * @throws HSKException 
		 */
		public PagerModel getPagerModelObject (SysNoticeInfo att_SysNoticeInfo) throws HSKException{
			PagerModel pm=new PagerModel(new ArrayList<SysNoticeInfo>());
				  try{
						pm=sysNoticeInfoDao.getPagerModelBySysNoticeInfo(att_SysNoticeInfo);
				    } catch (Exception e) {
						e.printStackTrace(); 
			        }
			return pm;
		}

		@Override
		public List<SysNoticeInfo> getSysNoticeInfoList() throws HSKException {
			List<SysNoticeInfo> reList = new ArrayList<SysNoticeInfo>();
			try{
				SysNoticeInfo att_SysNoticeInfo = new SysNoticeInfo();
				att_SysNoticeInfo.setState("1");
				reList = sysNoticeInfoDao.getListBySysNoticeInfo(att_SysNoticeInfo);
			}catch(Exception e){
				e.printStackTrace();
			}
			return reList;
		}
}
