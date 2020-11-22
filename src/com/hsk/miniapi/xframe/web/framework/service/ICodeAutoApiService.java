package com.hsk.miniapi.xframe.web.framework.service;

import java.util.List;

import com.hsk.exception.HSKException;
import com.hsk.miniapi.xframe.service.IDSTApiService;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.dto.freeMarker.FMFieldInfo;
import com.hsk.xframe.api.dto.freeMarker.FMPojoInfo;
import com.hsk.xframe.api.dto.freeMarker.SysCodeInfo;
import com.hsk.xframe.api.persistence.SysClassColumns;
import com.hsk.xframe.api.persistence.SysClassInfo;
import com.hsk.xframe.api.service.IDSTService;


/**
 * 自动化业务处理类接口
 * 
 * @author xun
 * @version v1.0
 * 
 */
public interface ICodeAutoApiService extends IDSTApiService {

	public void CreatePojoClass(SysClassInfo classInfo, List<SysClassColumns> listClassColumns, String Pack_Project) throws HSKException;


	public void CreateDaoClass(SysClassInfo classInfo, List<SysClassColumns> listClassColumns, String Pack_Project) throws HSKException;


	/**
	 * 创建pojo的javaBean对象
	 *
	 * @param saveFile
	 *            需要保存的持久化对象目录地址
	 * @param tableName
	 *            需要创建的表名称
	 * @param UserName
	 *            表所在的用户
	 * @throws HSKException
	 *             抛出业务异常
	 */
	public void CreatePojoFile(String saveFile, String tableName,
                               String UserName) throws HSKException;

	/**
	 * 创建Dao文件业务处理类
	 *
	 * @param saveFile
	 *            需要保存的持久化对象目录地址
	 * @param tableName
	 *            需要创建的表名称
	 * @param UserName
	 *            表所在的用户
	 * @throws HSKException
	 *             抛出业务异常
	 */
	public void CreateDaoFile(String saveFile, String pojoPackage, String tableName, String UserName)
			throws HSKException;
	/**
	 * 获取表对象列表
		 * @param UserName
	 *            表所在的用户
	 * @return  返回表对象列表
	 * @throws HSKException  抛出业务异常
	 */
	public List<FMPojoInfo> getTableList(String UserName)throws HSKException;


	/**
	 * 获取一张表的字段属性列表
	 * @param TableName 表名称
	 * @return  字段列表
	 * @throws HSKException 抛出业务异常
	 */
	public  List<FMFieldInfo> getFieldList(String TableName, String UserName)throws HSKException;


	public  PagerModel getPagerModelsysClass(SysClassInfo sci)throws HSKException;


	public  SysClassInfo getsysClass(SysClassInfo sci)throws HSKException;


	public  SysRetrunMessage getOnesysClass(SysClassInfo sci)throws HSKException;

	public  PagerModel getListClassColumns(SysClassInfo sci)throws HSKException;

	public  SysRetrunMessage initClassColumns(SysClassInfo sci)throws HSKException;

	public  SysRetrunMessage saveOrUpdatesysClass(SysCodeInfo sci)throws HSKException;

	public  SysRetrunMessage delsysClass(SysClassInfo sci)throws HSKException;

	public  SysRetrunMessage delsysClassColumns(SysClassColumns sci)throws HSKException;

	public  SysRetrunMessage delListsysClassColumns(List<SysClassColumns> list_sci)throws HSKException;

	 public  SysClassColumns   getClassColumns(SysClassColumns sccs) throws HSKException;

	 public  SysRetrunMessage  saveOrUpdateClassColumns(SysClassColumns sccs)throws HSKException;


	 public  SysRetrunMessage  createClassFile(SysClassInfo sci)throws HSKException;

	 public SysRetrunMessage createClassListFile(List<SysClassInfo> listsci, String runtype)throws HSKException;
		
	
}
