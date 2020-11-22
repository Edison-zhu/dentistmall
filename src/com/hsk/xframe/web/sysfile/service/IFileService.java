package com.hsk.xframe.web.sysfile.service;

import java.util.List;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.persistence.SysFileFolderInfoEntity;
import com.hsk.xframe.api.persistence.SysFileInfo;
import com.hsk.xframe.api.persistence.SysSystemInfo;

public interface IFileService {
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

	/************************* 相册管理相关（新的文件上传功能） ********************************/
	/* by yangfeng 2020.07.29 */
	/**
	 * 上传文件
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage addFile(Integer fileFolderId) throws HSKException;
	/**
	 * 删除文件
	 * @param fileCode
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage deleteFile(String fileCode, Integer fileFolderId) throws HSKException;
	/**
	 * 查看文件
	 * @param fileCode
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getOneFileInfo(String fileCode, Integer fileFolderId) throws HSKException;
	/**
	 * 根据文件codes集合查询文件列表
	 * @param fileCodes
	 * @return
	 * @throws HSKException
	 */
	public SysRetrunMessage getListFileInfo(String fileCodes, Integer fileFolderId) throws HSKException;

	public List<SysFileInfo> getListFileInfoByCodes(String fileCodes, Integer fileFolderId) throws HSKException;

	public SysRetrunMessage deleteFileByPath(String filePath, Integer fileFolderId) throws Exception;

	/**
	 * 获取文件分页对象
	 * @param fileFolderId
	 * @return
	 * @throws HSKException
	 */
	PagerModel getSysFileInfoPagerModel(Integer fileFolderId) throws HSKException;

	/**
	 * 批量/单个更新文件的所属文件名
	 * @param fileIds
	 * @param fileFolderId
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage updateSysFileInfoBatch(String fileIds, Integer fileFolderId) throws HSKException;

	/**
	 * 批量/单个删除文件
	 * @param fileIds
	 * @param fileFolderId
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage deleteSysFileInfoBatch(String fileIds, Integer fileFolderId) throws HSKException;

	/** yangfeng 2020.07.29
	 * 保存文件夹
	 * @param sysFileFolderInfoEntity
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage saveSysFileFolderInfo(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKException;

	/** yangfeng 2020.07.29
	 * 更新文件夹
	 * @param sysFileFolderInfoEntity
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage updateFileFolderInfo(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKException;

	/**
	 * 删除文件夹
	 * @param sysFileFolderInfoEntity
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage deleteFileFolderInfo(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKException;

	/**
	 * 获取文件夹信息
	 * @param fileFolderId
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage findFormFileFolderInfo(Integer fileFolderId) throws HSKException;

	/**
	 * 获取文件夹分页对象
	 * @param sysFileFolderInfoEntity
	 * @return
	 * @throws HSKException
	 */
	PagerModel getFileFolderInfoPagerModel(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKException;

	/**
	 * 获取文件夹列表对象
	 * @param sysFileFolderInfoEntity
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage getFileFolderInfoList(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKException;

	/**
	 * 获取文件夹的第一个文件
	 * @param fileFolderId
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage getFileFolderInfoFirstImg(Integer fileFolderId) throws HSKException;

	/**
	 * 获取文件夹下的文件数量
	 * @param fileFolderId
	 * @return
	 * @throws HSKException
	 */
	SysRetrunMessage getFileInfoCount(Integer fileFolderId) throws HSKException;
}
