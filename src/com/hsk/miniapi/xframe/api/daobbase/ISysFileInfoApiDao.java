package com.hsk.miniapi.xframe.api.daobbase;

import java.util.List;

import com.hsk.exception.HSKDBException;
import com.hsk.xframe.api.persistence.SysFileInfo;

/**
 * 文件管理接口
 * @author Administrator
 *
 */
public interface ISysFileInfoApiDao {
	
	public SysFileInfo getSysFileInfoByFileCode(String fileCode) throws HSKDBException;
	
	/**
	 * 根据fileCode集合获取文件集合
	 * @param fileCodes
	 * @return
	 * @throws HSKDBException
	 */
	public List<SysFileInfo> getSysFileInfoByCodes(String fileCodes) throws HSKDBException;	

}
