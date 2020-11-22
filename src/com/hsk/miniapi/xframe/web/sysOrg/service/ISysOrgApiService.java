package com.hsk.miniapi.xframe.web.sysOrg.service;

import java.util.List;
import java.util.Map;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.dto.model.TreeNode;
import com.hsk.xframe.api.persistence.SysCompany;

/**
 * 组织管理service
 * @author 张曙
 *
 */
public interface ISysOrgApiService {
	/**
	 * 获取树信息
	 * @param id
	 * @return
	 * @throws HSKException
	 */
	public List<Map<String, Object>> getOrgTree(Integer id) throws HSKException;
	/**
	 * 获取组织分页信息
	 * @param sysCompany
	 * @return
	 * @throws HSKException
	 */
	public PagerModel getSysCompanyPager(SysCompany sysCompany) throws HSKException;
	/**
	 * 保存组织信息
	 * @param sysCompany
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage saveSysCompany(SysCompany sysCompany) throws HSKException;
	/**
	 * 查看组织信息
	 * @param wzId
	 * @return
	 * @throws HSKException
	 */
	public SysCompany findSysCompany(Integer wzId, Integer orgGxId) throws HSKException;
	/**
	 * 修改组织状态
	 * @param wzId
	 * @param state
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage updateSysCompanyState(Integer wzId, String state) throws HSKException;

	public SysRetrunMessage findSysOrgGx(Integer oldId, String organizaType)throws HSKException;
	
	
	
	
	/**
	 * 获取用户关系组织树信息
	 * @param id
	 * @return
	 * @throws HSKException
	 */
	public List<TreeNode> getUserOrgTree(Integer id) throws HSKException;
	
	
	
}
