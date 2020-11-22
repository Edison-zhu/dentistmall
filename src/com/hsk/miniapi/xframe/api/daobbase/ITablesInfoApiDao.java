/**
 * 
 */
package com.hsk.miniapi.xframe.api.daobbase;

import java.util.List;
 

import com.hsk.exception.HSKException;
import com.hsk.xframe.api.dto.freeMarker.FMPojoInfo;


/**
 * 表对象业务处理类接口
 * @author xun
 * @version v1.0 
 *
 */
public interface ITablesInfoApiDao {

	/**
	 * 查询表对象信息，用于生成持久化对象
	 * @param tableName 表名称
	 *  @param UserName 用户名称
	 * @return 表对象 
	 * @throws ComponentException  抛出dao业务处理异常
	 */
	public  FMPojoInfo queryTableInfo(String tableName, String UserName) throws HSKException;
	
	/**
	 * 查询出所有的表对象列表
	 * @param UserName 用户名称
	 * @return  表对象列表
	 * @throws ComponentException 抛出dao业务处理异常
	 */
	public List<FMPojoInfo> queryListTableInfo(String UserName) throws HSKException;
	
	
	
}
