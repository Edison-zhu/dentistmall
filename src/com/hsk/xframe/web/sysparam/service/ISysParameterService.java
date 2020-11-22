package com.hsk.xframe.web.sysparam.service;

import java.util.List;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.dto.model.TreeNode;
import com.hsk.xframe.api.persistence.SysParameter;
import com.hsk.xframe.api.service.IDSTService;
 
public interface ISysParameterService extends IDSTService{
	/**
	 * 获取所有大类信息
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getOneSysParameter() throws HSKException;
	/**
	 * 获取编码分页信息
	 * @param sysParameter
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getSysParameterPager(SysParameter sysParameter) throws HSKException;
	/**
	 * 保存大类信息
	 * @param sysParameter
	 * @param controlIds
	 * @param paramNames
	 * @param paramTypes
	 * @param paramOrders
	 * @param paramNodes
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveOneSysParameter(SysParameter sysParameter,String controlIds,String paramNames,String paramTypes,String paramOrders,String paramNodes) throws HSKException;
	/**
	 * 保存编码信息
	 * @param sysParameter
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveSysParameter(SysParameter sysParameter) throws HSKException;
	/**
	 * 查看编码信息
	 * @param sparId
	 * @param sysSparId
	 * @return
	 * @throws HSKException
	 */
	public SysParameter findSysParameter(Integer sparId,Integer sysSparId) throws HSKException;
	/**
	 * 删除编码信息
	 * @param sparId
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage delSysParameter(Integer sparId) throws HSKException;
	/**
	 * 根据大类编号获取所有子类
	 * @param parentCode
	 * @return
	 * @throws HSKException
	 */
	public List<SysParameter> getParameterListByParentCode(String parentCode) throws HSKException;
	
	/**
	 * 
	 * @param parentCode
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getTreeNodeListByParentCode(String parentCode) throws HSKException;
	
	 
	public SysRetrunMessage getNewCode(String prefix)throws HSKException;
	
	
	public SysParameter findSysParameterByCode(String pathCode)throws HSKException;
}
