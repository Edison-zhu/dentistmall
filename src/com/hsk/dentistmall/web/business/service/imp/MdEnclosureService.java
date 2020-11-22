package com.hsk.dentistmall.web.business.service.imp;

import java.util.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage; 
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.dentistmall.web.business.service.*;
import com.hsk.dentistmall.api.persistence.*;
import com.hsk.dentistmall.api.daobbase.*;


/** 
  business业务操作实现类 
 * @author  作者:admin
 * @version  版本信息:v1.0   创建时间: 2017-09-25 14:21:17
 */
 
@Service
public class  MdEnclosureService extends DSTService implements IMdEnclosureService {	
   /**
   *业务处理dao类  mdEnclosureDao 
   */
	@Autowired
	protected IMdEnclosureDao mdEnclosureDao;


 /**
	 * 根据md_enclosure表主键删除MdEnclosure对象记录
     * @param  rbenId  Integer类型(md_enclosure表主键)
	 * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */

	public SysRetrunMessage findFormObject(Integer rbenId) throws HSKException{
	        SysRetrunMessage srm=new SysRetrunMessage(1l);
			  	
			try{
		   			MdEnclosure     att_MdEnclosure= mdEnclosureDao.findMdEnclosureById( rbenId) ;
					srm.setObj(att_MdEnclosure);
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					srm.setCode(0l);
					srm.setMeg(e.getMessage()); 
				}
				 return srm ;
	} 

    /**
	 * 根据md_enclosure表主键删除MdEnclosure对象记录
     * @param  rbenId  Integer类型(md_enclosure表主键)
	 * @throws HSKException
	 */

	public MdEnclosure findObject(Integer rbenId) throws HSKException{
			MdEnclosure  att_MdEnclosure=new MdEnclosure();		
			try{
		        att_MdEnclosure= mdEnclosureDao.findMdEnclosureById( rbenId) ;
				} catch (HSKDBException e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
				}
				return  att_MdEnclosure;
	}
	
	 
	 /**
	 * 根据md_enclosure表主键删除MdEnclosure对象记录
     * @param  rbenId  Integer类型(md_enclosure表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer rbenId) throws HSKException{  
		    SysRetrunMessage srm=new SysRetrunMessage(1l);
		   try{ 
				mdEnclosureDao.deleteMdEnclosureById(rbenId);
			 } catch (Exception e) {
					e.printStackTrace(); 
					throw new  HSKException(e);
		     }
		   return srm;  
	}
	
	/**
	 * 根据md_enclosure表主键删除多条MdEnclosure对象记录
     * @param  RbenIds  Integer类型(md_enclosure表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String rbenIds) throws HSKException{
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			String[] ids = rbenIds.split(",");
			for (String did : ids) {
				if (did != null && !"".equals(did.trim())) {
					mdEnclosureDao.deleteMdEnclosureById(Integer
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
	 * 新增或修改md_enclosure表记录 ,如果md_enclosure表主键MdEnclosure.RbenId为空就是添加，如果非空就是修改
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
     * @return MdEnclosure  修改后的MdEnclosure对象记录
	 * @throws HSKDBException
	 */
	public SysRetrunMessage saveOrUpdateObject( MdEnclosure att_MdEnclosure) throws HSKException{
			 SysRetrunMessage srm = new SysRetrunMessage(1l);
			  try{
					mdEnclosureDao.saveOrUpdateMdEnclosure(att_MdEnclosure); 
					srm.setObj(att_MdEnclosure);
			    } catch (Exception e) {
					e.printStackTrace(); 
					throw new HSKException(e);
		        }
			return srm;	  
	}
	
	 
	/**
	 *根据MdEnclosure对象作为对(md_enclosure表进行查询，获取分页对象
     * @param  att_MdEnclosure  MdEnclosure类型(md_enclosure表记录)
     * @return PagerModel  分页对象
	 * @throws HSKException 
	 */
	public PagerModel getPagerModelObject (MdEnclosure att_MdEnclosure) throws HSKException{
		PagerModel pm=new PagerModel(new ArrayList<MdEnclosure>());
			  try{
					pm=mdEnclosureDao.getPagerModelByMdEnclosure(att_MdEnclosure);
			    } catch (Exception e) {
					e.printStackTrace(); 
		        }
		return pm;
	} 
	 
}