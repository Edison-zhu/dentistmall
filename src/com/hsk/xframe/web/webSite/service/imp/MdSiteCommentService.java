package com.hsk.xframe.web.webSite.service.imp;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.api.daobbase.IMdSiteCommentDao;
import com.hsk.dentistmall.api.persistence.MdCommentMaterielView;
import com.hsk.dentistmall.api.persistence.MdCommentSupplierView;
import com.hsk.dentistmall.api.persistence.MdCommentTypeView;
import com.hsk.dentistmall.api.persistence.MdSiteComment;
import com.hsk.dentistmall.web.shopping.model.MdCommentModel;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.ISysUserLoginLogDao;
import com.hsk.xframe.api.daobbase.ISysWebsiteCommentDao;

import com.hsk.xframe.api.persistence.SysWebsiteColumns;
import com.hsk.xframe.api.persistence.SysWebsiteComment;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.web.webSite.service.IMdSiteCommentService;

/** 
	MdSiteComment业务操作实现类 
* @author  作者:admin
* @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:16
*/
@Service
public class MdSiteCommentService extends DSTService implements IMdSiteCommentService {
	/**
	   *业务处理dao类  mdSiteCommentDao 
	   */
		@Autowired
		protected IMdSiteCommentDao mdSiteCommentDao;
		@Autowired
		private ISysWebsiteCommentDao sysWebsiteCommentDao;

	 /**
		 * 根据md_materiel_type表主键删除MdSiteComment对象记录
	     * @param  mscId  Integer类型(md_materiel_type表主键)
		 * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */

		public SysRetrunMessage findFormObject(Integer mscId,Integer mscType,Integer swcId) throws HSKException{
		        SysRetrunMessage srm=new SysRetrunMessage(1l);
				try{
					Object obj=null;
					if(mscId != null){
						if(mscType==1){//商品
							MdCommentMaterielView view = new MdCommentMaterielView();
							view.setMscId(mscId);
							obj =  this.getOne(view);
						}else if(mscType==2){//商家
							MdCommentSupplierView view = new MdCommentSupplierView();
							view.setMscId(mscId);
							obj =  this.getOne(view);
						}else if(mscType==3){//分类
							MdCommentTypeView view = new MdCommentTypeView();
							view.setMscId(mscId);
							obj =  this.getOne(view);
						}
					}else{
						MdSiteComment  att_MdSiteComment=new MdSiteComment();
						Integer order = mdSiteCommentDao.getMaxOrderByParentId(swcId)+1;
						att_MdSiteComment.setSerialComm(order);
						obj = att_MdSiteComment;
					}
					srm.setObj(obj);
				} catch (Exception e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				return srm ;
		} 

	    /**
		 * 根据md_materiel_type表主键删除MdSiteComment对象记录
	     * @param  mscId  Integer类型(md_materiel_type表主键)
		 * @throws HSKException
		 */

		public MdSiteComment findObject(Integer mscId) throws HSKException{
				MdSiteComment  att_MdSiteComment=new MdSiteComment();		
				try{
			        att_MdSiteComment= mdSiteCommentDao.findMdSiteCommentById( mscId) ;
					} catch (HSKDBException e) {
						e.printStackTrace(); 
						throw new  HSKException(e);
					}
					return  att_MdSiteComment;
		}
		
		 
		 /**
		 * 根据md_materiel_type表主键删除MdSiteComment对象记录
	     * @param  mscId  Integer类型(md_materiel_type表主键)
	     * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */
		public SysRetrunMessage deleteObject(Integer mscId) throws HSKException{  
			    SysRetrunMessage srm=new SysRetrunMessage(1l);
			   try{ 
					mdSiteCommentDao.deleteMdSiteCommentById(mscId);
				 } catch (Exception e) {
						e.printStackTrace(); 
						throw new  HSKException(e);
			     }
			   return srm;  
		}
		
		/**
		 * 根据md_materiel_type表主键删除多条MdSiteComment对象记录
	     * @param  RbaIds  Integer类型(md_materiel_type表主键)
	     * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */
		public SysRetrunMessage deleteAllObject(String mscIds) throws HSKException{
			SysRetrunMessage srm = new SysRetrunMessage(1l);
			try {
				String[] ids = mscIds.split(",");
				for (String did : ids) {
					if (did != null && !"".equals(did.trim())) {
						mdSiteCommentDao.deleteMdSiteCommentById(Integer
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
		 * 新增或修改md_materiel_type表记录 ,如果md_materiel_type表主键MdSiteComment.RbaId为空就是添加，如果非空就是修改
	     * @param  att_MdSiteComment  MdSiteComment类型(md_materiel_type表记录)
	     * @return MdSiteComment  修改后的MdSiteComment对象记录
		 * @throws HSKDBException
		 */
		public SysRetrunMessage saveOrUpdateObject( MdSiteComment att_MdSiteComment) throws HSKException{
				 SysRetrunMessage srm = new SysRetrunMessage(1l);
				  try{
						mdSiteCommentDao.saveOrUpdateMdSiteComment(att_MdSiteComment); 
						srm.setObj(att_MdSiteComment);
				    } catch (Exception e) {
						e.printStackTrace(); 
						throw new HSKException(e);
			        }
				return srm;	  
		}

		@Override
		public PagerModel getMdCommentMaterielViewPager(MdCommentMaterielView mdCommentMaterielView) throws HSKException {
			PagerModel pm=new PagerModel(new ArrayList<MdCommentMaterielView>());
			  try{
					pm=mdSiteCommentDao.getMdCommentMaterielViewPager(mdCommentMaterielView);
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
			  return pm;
		}

		@Override
		public PagerModel getMdCommentSupplierViewPager(MdCommentSupplierView mdCommentSupplierView) throws HSKException {
			PagerModel pm=new PagerModel(new ArrayList<MdCommentSupplierView>());
			  try{
					pm=mdSiteCommentDao.getMdCommentSupplierViewPager(mdCommentSupplierView);
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
			  return pm;
		}

		@Override
		public PagerModel getMdCommentTypeViewPager(MdCommentTypeView mdCommentTypeView) throws HSKException {
			PagerModel pm=new PagerModel(new ArrayList<MdCommentTypeView>());
			  try{
					pm=mdSiteCommentDao.getMdCommentTypeViewPager(mdCommentTypeView);
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
			  return pm;
		}

		@Override
		public List getMdCommentInfoListBySwcId(Integer swcId, Integer number)throws HSKException {
			
			List reList = new ArrayList();
			SysWebsiteColumns sysWebsiteColumns = new SysWebsiteColumns();
			sysWebsiteColumns.setSwcId(swcId);
			sysWebsiteColumns = (SysWebsiteColumns) this.getOne(sysWebsiteColumns);
			try{
				if(sysWebsiteColumns.getMszType()==2){
					reList = mdSiteCommentDao.getMdCommentMaterielViewListBySwcId(swcId, number);
				}else if(sysWebsiteColumns.getMszType()==3){
					reList = mdSiteCommentDao.getMdCommentSupplierViewListBySwcId(swcId, number);
				}else if(sysWebsiteColumns.getMszType()==4){
					reList = mdSiteCommentDao.getMdCommentTypeViewListBySwcId(swcId, number);
				}
			}catch (Exception e) {
				e.printStackTrace(); 
	        }
			return reList;
		}

		@Override
		public MdCommentModel getMdCommentModelBySwcId(SysWebsiteColumns sysWebsiteColumns,Integer number) throws HSKException {
			MdCommentModel reModel = new MdCommentModel();
			try{
				List<SysWebsiteComment> childMapList = new ArrayList<SysWebsiteComment>();
				List<SysWebsiteComment> childWordList= new ArrayList<SysWebsiteComment>();
				reModel.setSwcId(sysWebsiteColumns.getSwcId());
				reModel.setLinkUrl(sysWebsiteColumns.getLinkUrl());
				reModel.setColumnName(sysWebsiteColumns.getColumnName());
				reModel.setMszType(sysWebsiteColumns.getMszType());
				List reList = new ArrayList();
				if(sysWebsiteColumns.getMszType()==2){
					reList = mdSiteCommentDao.getMdCommentMaterielViewListBySwcId(sysWebsiteColumns.getSwcId(), number);
				}else if(sysWebsiteColumns.getMszType()==3){
					reList = mdSiteCommentDao.getMdCommentSupplierViewListBySwcId(sysWebsiteColumns.getSwcId(), number);
				}else if(sysWebsiteColumns.getMszType()==4){
					reList = mdSiteCommentDao.getMdCommentTypeViewListBySwcId(sysWebsiteColumns.getSwcId(), number);
				}
				reModel.setChildList(reList);
				SysWebsiteColumns pSysWebsiteColumns= new SysWebsiteColumns();
				pSysWebsiteColumns.setSysSwcId(sysWebsiteColumns.getSwcId());
				pSysWebsiteColumns.setState("1");
				List<SysWebsiteColumns> childSysWebsiteColumnsList = this.getList(pSysWebsiteColumns);
				for(SysWebsiteColumns obj : childSysWebsiteColumnsList){
					if(obj.getMszType()==5){//推荐图片
						SysWebsiteComment sysWebsiteComment = new SysWebsiteComment();
						sysWebsiteComment.setSwcId(obj.getSwcId());
						sysWebsiteComment.setState("1");
						List<SysWebsiteComment> commList = sysWebsiteCommentDao.findListSysWebsiteComment(sysWebsiteComment,0,true);
						if(commList != null && commList.size() >0)
							childMapList.addAll(commList);
					}else if(obj.getMszType()==6){//推荐文字
						SysWebsiteComment sysWebsiteComment = new SysWebsiteComment();
						sysWebsiteComment.setSwcId(obj.getSwcId());
						sysWebsiteComment.setState("1");
						List<SysWebsiteComment> commList = sysWebsiteCommentDao.findListSysWebsiteComment(sysWebsiteComment,0,true);
						if(commList != null && commList.size() >0)
							childWordList.addAll(commList);
					}
				}
				reModel.setChildMapList(childMapList);
				reModel.setChildWordList(childWordList);
			}catch (Exception e) {
				e.printStackTrace(); 
	        }
			return reModel;
		}
}
