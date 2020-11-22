package com.hsk.xframe.web.webSite.service;

import java.util.List;

import com.hsk.dentistmall.api.persistence.MdCommentMaterielView;
import com.hsk.dentistmall.api.persistence.MdCommentSupplierView;
import com.hsk.dentistmall.api.persistence.MdCommentTypeView;
import com.hsk.dentistmall.api.persistence.MdSiteComment;
import com.hsk.dentistmall.web.shopping.model.MdCommentModel;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysWebsiteColumns;

/**
 * MdSiteComment业务操作接口类 
 * @author  作者:张曙
 */
public interface IMdSiteCommentService {
	
	/**
	 * 查找MdSiteComment对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mscId  Integer类型(md_materiel_format表主键)
	  * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */	
	public SysRetrunMessage findFormObject(Integer mscId,Integer mscType,Integer swcId) throws HSKException;

	
	/**
	 * 查找MdSiteComment对象记录，用于弹出添加、修改窗口时候初始化数据
	 * @param  mscId  Integer类型(md_materiel_format表主键)
	 * @return MdSiteComment md_materiel_format表记录
	 * @throws HSKException
	 */	
	public MdSiteComment findObject(Integer mscId) throws HSKException;
	
	/**
	 * 根据md_materiel_format表主键删除MdSiteComment对象记录
     * @param  mscId  Integer类型(md_materiel_format表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteObject(Integer mscId) throws HSKException;
	
	/**
	 * 根据md_materiel_format表主键删除多条MdSiteComment对象记录
     * @param  mscIds  Integer类型(md_materiel_format表主键)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteAllObject(String mscIds) throws HSKException;
	
	 /**
	 * 新增或修改md_materiel_format表记录 ,如果md_materiel_format表主键MdSiteComment.mscId为空就是添加，如果非空就是修改
     * @param  att_MdSiteComment  MdSiteComment类型(md_materiel_format表记录)
     * @return SysRetrunMessage 对象记录
	 * @throws HSKException
	 */
	public SysRetrunMessage   saveOrUpdateObject( MdSiteComment att_MdSiteComment) throws HSKException;
	/**
	 * 查询MdCommentMaterielView分页列表
	 * @param mdCommentMaterielView
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdCommentMaterielViewPager(MdCommentMaterielView mdCommentMaterielView)  throws HSKException;
	/**
	 * 查询MdCommentSupplierView分页列表
	 * @param mdCommentSupplierView
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdCommentSupplierViewPager(MdCommentSupplierView mdCommentSupplierView)  throws HSKException;
	/**
	 * 查询MdCommentTypeView分页列表
	 * @param mdCommentTypeView
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getMdCommentTypeViewPager(MdCommentTypeView mdCommentTypeView) throws HSKException;
	/**
	 * 根据栏目节点ID获取内容列表
	 * @param swcId
	 * @param number
	 * @return
	 * @throws HSKException
	 */
	public List getMdCommentInfoListBySwcId(Integer swcId,Integer number)  throws HSKException;
	/**
	 * 根据栏目ID查询栏目与内容组合的实体类
	 * @param swcId
	 * @param number
	 * @return
	 * @throws HSKException
	 */
	public MdCommentModel getMdCommentModelBySwcId(SysWebsiteColumns sysWebsiteColumns,Integer number)  throws HSKException;
}	
