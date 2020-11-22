package com.hsk.miniapi.xframe.api.dto.freeMarker;

import java.util.ArrayList;
import java.util.List;

import com.hsk.xframe.api.persistence.SysClassColumns;
import com.hsk.xframe.api.persistence.SysClassInfo;

public class SysCodeInfo {
	
 private SysClassInfo  sysClassInfo=new SysClassInfo() ; 
 private List<SysClassColumns> list_sysClassColumns=new ArrayList<SysClassColumns>();
public SysClassInfo getSysClassInfo() {
	return sysClassInfo;
}
public void setSysClassInfo(SysClassInfo sysClassInfo) {
	this.sysClassInfo = sysClassInfo;
}
public List<SysClassColumns> getList_sysClassColumns() {
	return list_sysClassColumns;
}
public void setList_sysClassColumns(List<SysClassColumns> list_sysClassColumns) {
	this.list_sysClassColumns = list_sysClassColumns;
}
 
 
 
 
}
