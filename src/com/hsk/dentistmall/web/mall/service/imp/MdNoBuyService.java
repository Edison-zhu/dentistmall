package com.hsk.dentistmall.web.mall.service.imp;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.dentistmall.api.daobbase.IMdNoBuyDao;
import com.hsk.dentistmall.api.persistence.MdNoBuy;
import com.hsk.dentistmall.web.mall.service.IMdNoBuyService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;

/** 
	MdNoBuy业务操作实现类 
* @author  作者:admin
* @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:16
*/
@Service
public class MdNoBuyService extends DSTService implements IMdNoBuyService {
	/**
	   *业务处理dao类  mdNoBuyDao 
	   */
		@Autowired
		protected IMdNoBuyDao mdNoBuyDao;


	 /**
		 * 根据md_materiel_type表主键删除MdNoBuy对象记录
	     * @param  mnbId  Integer类型(md_materiel_type表主键)
		 * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */

		public SysRetrunMessage findFormObject(Integer mnbId) throws HSKException{
		        SysRetrunMessage srm=new SysRetrunMessage(1l);
				  	
				try{
			   			MdNoBuy     att_MdNoBuy= mdNoBuyDao.findMdNoBuyById( mnbId) ;
						srm.setObj(att_MdNoBuy);
					} catch (HSKDBException e) {
						e.printStackTrace(); 
						srm.setCode(0l);
						srm.setMeg(e.getMessage()); 
					}
					 return srm ;
		} 

	    /**
		 * 根据md_materiel_type表主键删除MdNoBuy对象记录
	     * @param  mnbId  Integer类型(md_materiel_type表主键)
		 * @throws HSKException
		 */

		public MdNoBuy findObject(Integer mnbId) throws HSKException{
				MdNoBuy  att_MdNoBuy=new MdNoBuy();		
				try{
			        att_MdNoBuy= mdNoBuyDao.findMdNoBuyById( mnbId) ;
					} catch (HSKDBException e) {
						e.printStackTrace(); 
						throw new  HSKException(e);
					}
					return  att_MdNoBuy;
		}
		
		 
		 /**
		 * 根据md_materiel_type表主键删除MdNoBuy对象记录
	     * @param  mnbId  Integer类型(md_materiel_type表主键)
	     * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */
		public SysRetrunMessage deleteObject(Integer mnbId) throws HSKException{  
			    SysRetrunMessage srm=new SysRetrunMessage(1l);
			   try{ 
					mdNoBuyDao.deleteMdNoBuyById(mnbId);
				 } catch (Exception e) {
						e.printStackTrace(); 
						throw new  HSKException(e);
			     }
			   return srm;  
		}
		
		/**
		 * 根据md_materiel_type表主键删除多条MdNoBuy对象记录
	     * @param  RbaIds  Integer类型(md_materiel_type表主键)
	     * @return SysRetrunMessage 对象记录
		 * @throws HSKException
		 */
		public SysRetrunMessage deleteAllObject(String mnbIds) throws HSKException{
			SysRetrunMessage srm = new SysRetrunMessage(1l);
			try {
				String[] ids = mnbIds.split(",");
				for (String did : ids) {
					if (did != null && !"".equals(did.trim())) {
						mdNoBuyDao.deleteMdNoBuyById(Integer
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
		 * 新增或修改md_materiel_type表记录 ,如果md_materiel_type表主键MdNoBuy.RbaId为空就是添加，如果非空就是修改
	     * @param  att_MdNoBuy  MdNoBuy类型(md_materiel_type表记录)
	     * @return MdNoBuy  修改后的MdNoBuy对象记录
		 * @throws HSKDBException
		 */
		public SysRetrunMessage saveOrUpdateObject( MdNoBuy att_MdNoBuy) throws HSKException{
				 SysRetrunMessage srm = new SysRetrunMessage(1l);
				  try{
						  mdNoBuyDao.saveOrUpdateMdNoBuy(att_MdNoBuy); 
						  srm.setObj(att_MdNoBuy);
				    } catch (Exception e) {
						e.printStackTrace(); 
						throw new HSKException(e);
			        }
				return srm;	  
		}
		
		 
		/**
		 *根据MdNoBuy对象作为对(md_materiel_type表进行查询，获取分页对象
	     * @param  att_MdNoBuy  MdNoBuy类型(md_materiel_type表记录)
	     * @return PagerModel  分页对象
		 * @throws HSKException 
		 */
		public PagerModel getPagerModelObject (MdNoBuy att_MdNoBuy) throws HSKException{
			PagerModel pm=new PagerModel(new ArrayList<MdNoBuy>());
				  try{
						pm=mdNoBuyDao.getPagerModelByMdNoBuy(att_MdNoBuy);
				    } catch (Exception e) {
						e.printStackTrace(); 
			        }
			return pm;
		}

		@Override
		public PagerModel getMdNoBuyMapPagerModel(String applicantName,
				String matName) throws HSKException {
			  PagerModel pm=new PagerModel(new ArrayList<Map<String,Object>>());
			  try{
				  SysUserInfo sysUserInfo = this.GetOneShoppingSessionAccount();
					pm=mdNoBuyDao.getMdNoBuyMapPagerModel(null,null,sysUserInfo.getSuiId());
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
			  return pm;
		}

	@Override
	public SysRetrunMessage delectAllObjectBySuiId() throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo sysUserInfo = this.GetOneShoppingSessionAccount();
			Integer suiId = sysUserInfo.getSuiId();
			mdNoBuyDao.deleteMdNoBuyBySuiId(suiId);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			throw new HSKException(e);
		}
		return srm;
	}

	@Override
	public SysRetrunMessage deleteMdNoBuyBySuiIdsAndMnbIds(String mnbIds) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try{
			SysUserInfo sysUserInfo = this.GetOneShoppingSessionAccount();
			Integer suiId = sysUserInfo.getSuiId();
			mdNoBuyDao.deleteMdNoBuyBySuiIdsAndMnbIds(suiId, mnbIds);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(0l);
			throw new HSKException(e);
		}
		return srm;
	}

	@Override
	public PagerModel searchMdNoBuyBySearch(String searchName) throws HSKException {
		PagerModel pm=new PagerModel(new ArrayList<Map<String,Object>>());
		try{
			pm = mdNoBuyDao.searchMdNoBuyBySearch(this.GetOneSessionAccount().getSuiId(), searchName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
}
