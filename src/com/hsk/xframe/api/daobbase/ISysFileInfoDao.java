package com.hsk.xframe.api.daobbase;

import java.util.List;
import java.util.Map;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysFileFolderInfoEntity;
import com.hsk.xframe.api.persistence.SysFileInfo;
import com.hsk.xframe.api.persistence.SysFileInfoTemp;

/**
 * 文件管理接口
 * @author Administrator
 *
 */
public interface ISysFileInfoDao {
	//
	public SysFileInfo findSysFileInfoById(Integer sysFfId) throws HSKDBException;

	public SysFileInfo getSysFileInfoByFileCode(String fileCode) throws HSKDBException;

	/**
	 * 根据fileCode集合获取文件集合
	 * @param fileCodes
	 * @return
	 * @throws HSKDBException
	 */
	public List<SysFileInfo> getSysFileInfoByCodes(String fileCodes) throws HSKDBException;

    List<SysFileInfoTemp> getSysFileInfoByCodesNoLogin(String codes) throws HSKDBException;

    Integer getSysFileFolderInfoCount(SysFileFolderInfoEntity sysFileFolderInfoEntity, Integer sysFfId) throws HSKDBException;

	/**
	 * 删除文件夹
	 * @param fileFolderId
	 * @return
	 * @throws HSKDBException
	 */
	Integer getSysFileFolderInfoRef(SysFileFolderInfoEntity sysFileFolderInfoEntity, Map<String, Object> map) throws HSKDBException;

	/**
	 * 获取分页对象
	 * @param sysFileFolderInfoEntity
	 * @return
	 * @throws HSKDBException
	 */
	PagerModel getSysFileFolderInfoPagerModel(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKDBException;

	List<SysFileFolderInfoEntity> getSysFileFolderInfoList(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKDBException;

	Integer getSysFileInfoCount(Integer fileFolderId, Map<String, Object> map) throws HSKDBException;

	List<SysFileInfo> getSysFileInfoByCodesByFileFolderId(String codes, Integer fileFolderId, Map<String, Object> map) throws HSKDBException;

	String getSysFileFolderInfoFirstImg(Integer fileFolderId, Map<String, Object> map) throws HSKDBException;

    PagerModel getSysFileInfoPagerNodel(Integer fileFolderId, Map<String, Object> map) throws HSKDBException;

	List<Map<String, Object>> getSysFileFolderInfoRefCountMap(SysFileFolderInfoEntity sysFileFolderInfoEntity, Map<String, Object> map) throws HSKDBException;

	List<Map<String, Object>> getSysFileFolderInfoRefImgMap(SysFileFolderInfoEntity sysFileFolderInfoEntity, Map<String, Object> map) throws HSKDBException;

    List<SysFileInfo> getSysFileInfoByFileId(String imgIds) throws HSKDBException;

	//根据文件ID查询文件内容
	public SysFileInfo findFileIdInfoById(Integer fileId) throws HSKDBException;
}
