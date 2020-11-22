package com.hsk.xframe.web.sysTableInfo.Service;

import java.util.List;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.dto.freeMarker.FMPojoInfo;
import com.hsk.xframe.api.dto.model.TreeNode;
import com.hsk.xframe.api.persistence.SysFieldInfo;
import com.hsk.xframe.api.persistence.SysTableInfo;

public interface ITableInfoService {

	//表对象tree控件查看
	public  List<TreeNode> getlistNodeTable(String sysCode)throws HSKException;
	//字段对象grid控件查看	
	public  PagerModel getPagerModelFieldforTable(SysTableInfo stable)throws HSKException;
	
	//表信息grid查看
	public  PagerModel getPagerModelTableforDB(String sysCode)throws HSKException;
	/**
	 * 从数据库中获取表对象下拉列表
	 * @param sysCode  系统编号
	 *  @param ifall  是否显示全部  null或者2表示显示，1表示显示部分（不显示添加过表对象的记录）
	 * @return
	 * @throws HSKException
	 */
	public  SysRetrunMessage getNodeTableforDB(String sysCode,String ifall)throws HSKException;
	
	//表对象删除
	public  SysRetrunMessage delTable(SysTableInfo stable)throws HSKException;
	
	//添加表对象
	public  SysRetrunMessage saveTable(SysTableInfo stable)throws HSKException;
	
	
	//检索字段对象
	 	public  SysTableInfo questTable(SysTableInfo stable)throws HSKException;
	//修改字段对象
	public  SysRetrunMessage saveOrUpdateTable(SysTableInfo stable)throws HSKException;
	
	
	
	//检索字段对象
 	public  SysFieldInfo questField(SysFieldInfo field)throws HSKException;
	//修改字段对象
	public  SysRetrunMessage saveOrUpdateField(SysFieldInfo field)throws HSKException;
	
	public  SysRetrunMessage questNodeTable(SysTableInfo stable)throws HSKException;
	
	public  SysRetrunMessage saveTableList(String sysCode, String ifPojo,String ifDao,String ifService, List<FMPojoInfo> fmp)throws HSKException;
	
	
}
