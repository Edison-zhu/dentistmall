package com.hsk.xframe.api.daobbase;

import java.util.List;

import org.apache.avalon.framework.component.ComponentException;

import com.hsk.exception.HSKException;
import com.hsk.xframe.api.dto.freeMarker.FMFieldInfo;
 

/**
 * 字段业务处理类接口
 * 
 * @author xun
 * @version v1.0
 * 
 */
public interface IFieldInfoDao {
/**
 * 查询表结构中的字符列表
 * @param tableName 表名称
 * @param UserName  用户名称
 * @return 字段列表
 * @throws ComponentException 抛出dao业务处理异常
 */
	public List<FMFieldInfo> queryListFieldInfo(String tableName,
			String UserName) throws HSKException;

}
