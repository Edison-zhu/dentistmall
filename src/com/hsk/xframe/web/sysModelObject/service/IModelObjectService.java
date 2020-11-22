package com.hsk.xframe.web.sysModelObject.service;

 
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.SysRetrunMessage; 

public interface IModelObjectService {
 	
	public SysRetrunMessage initRootModel(String sysCode,String proName) throws HSKException;
	
	
	public SysRetrunMessage removeRootModel(String sysCode ) throws HSKException;
	public SysRetrunMessage updateRootModel(String sysCode,String proName ) throws HSKException;
}
