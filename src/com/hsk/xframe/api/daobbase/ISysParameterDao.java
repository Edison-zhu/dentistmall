package com.hsk.xframe.api.daobbase;


import java.util.List;

import com.hsk.exception.HSKDBException;
import com.hsk.xframe.api.persistence.SysParameter;

 
public interface ISysParameterDao {
	
	public List<SysParameter> getSysParameterList(Integer sysSparId) throws HSKDBException;
	 
	public int getMaxOrderByParentId(Integer sparId) throws HSKDBException;
}
