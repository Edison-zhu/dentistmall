package com.hsk.miniapi.xframe.web.sysfile.service;

import java.util.List;

import com.hsk.exception.HSKException;
import com.hsk.xframe.api.persistence.SysFileInfo;
import com.hsk.supper.dto.comm.SysRetrunMessage;

public interface IFileApiService {
	/**
	 * 上传文件
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage addfile() throws HSKException;
	/**
	 * 删除文件
	 * @param fileCode
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage deletefile(String fileCode) throws HSKException;
	/**
	 * 查看文件
	 * @param fileCode
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage GetOneFileInfo(String fileCode) throws HSKException;
	/**
	 * 根据文件codes集合查询文件列表
	 * @param fileCodes
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage GetListFileInfo(String fileCodes) throws HSKException;
	
	public List<SysFileInfo> GetListFileInfoByCodes(String fileCodes) throws HSKException;
	
	public SysRetrunMessage deletefileByPath(String filePath) throws Exception;

}
