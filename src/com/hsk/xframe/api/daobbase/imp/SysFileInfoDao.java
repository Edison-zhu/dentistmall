package com.hsk.xframe.api.daobbase.imp;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.hsk.dentistmall.api.persistence.MdMaterielInfo;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.persistence.SysFileFolderInfoEntity;
import com.hsk.xframe.api.persistence.SysFileInfoTemp;
import org.springframework.stereotype.Repository;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.xframe.api.daobbase.ISysFileInfoDao;
import com.hsk.xframe.api.persistence.SysFileInfo;

@Repository
public class SysFileInfoDao extends SupperDao implements ISysFileInfoDao {
	private String getFolderSql(SysFileFolderInfoEntity sysFileFolderInfoEntity) {
		StringBuffer sb = new StringBuffer("from SysFileFolderInfoEntity where 1=1");
		if (sysFileFolderInfoEntity.getSuiId() != null)
			sb.append(" and suiId = " + sysFileFolderInfoEntity.getSuiId());
		if (sysFileFolderInfoEntity.getRbaId() != null)
			sb.append(" and rbaId = " + sysFileFolderInfoEntity.getRbaId());
		if (sysFileFolderInfoEntity.getRbsId() != null)
			sb.append(" and rbsId = " + sysFileFolderInfoEntity.getRbsId());
		if (sysFileFolderInfoEntity.getRbbId() != null)
			sb.append(" and rbbId = " + sysFileFolderInfoEntity.getRbbId());
		if (sysFileFolderInfoEntity.getFileFolderName() != null && !sysFileFolderInfoEntity.getFileFolderName().equals(""))
			sb.append(" and fileFolderName = '" + sysFileFolderInfoEntity.getFileFolderName() + "'");
		if (sysFileFolderInfoEntity.getSearchName() != null && !sysFileFolderInfoEntity.getSearchName().equals(""))
			sb.append(" and fileFolderName like '%" + sysFileFolderInfoEntity.getSearchName() + "%'");
		return sb.toString();
	}
	@Override
	public SysFileInfo findSysFileInfoById(Integer sysFfId) throws HSKDBException{
		SysFileInfo sysFileInfo = new SysFileInfo();
			if (sysFfId != null) {
				sysFileInfo.setSysFfId(sysFfId);
			Object obj = this.getOne(sysFileInfo);
			if (obj != null) {
				sysFileInfo = (SysFileInfo) obj;
			}
		}
		return sysFileInfo;
	}
	//根据文件ID查询文件内容
	@Override
	public SysFileInfo findFileIdInfoById(Integer fileId) throws HSKDBException{
		SysFileInfo sysFileInfo = new SysFileInfo();
		if (fileId != null) {
			sysFileInfo.setFileId(fileId);
			Object obj = this.getOne(sysFileInfo);
			if (obj != null) {
				sysFileInfo = (SysFileInfo) obj;
			}
		}
		return sysFileInfo;
	}

	@Override
	public SysFileInfo getSysFileInfoByFileCode(String fileCode)
			throws HSKDBException {
		String hql="from SysFileInfo where fileCode='"+fileCode+"'";
		hql += " and (isDelete = 1 or isDelete is null)";
		List<SysFileInfo> list=(List<SysFileInfo>) this.getHibernatesession().createQuery(hql).list();
		if(list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public List<SysFileInfo> getSysFileInfoByCodes(String fileCodes)
			throws HSKDBException {
		String hql = "from SysFileInfo where fileCode in ("+fileCodes+")";
		hql += " and (isDelete = 1 or isDelete is null)";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public List<SysFileInfoTemp> getSysFileInfoByCodesNoLogin(String codes) throws HSKDBException {
		String hql = "from SysFileInfoTemp where fileCode in ("+codes+")";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public Integer getSysFileFolderInfoCount(SysFileFolderInfoEntity sysFileFolderInfoEntity, Integer sysFfId) throws HSKDBException {
		String sql = getFolderSql(sysFileFolderInfoEntity);
		if (sysFfId != null)
			sql += " and fileFolderId <> " + sysFfId;
//		sql += " limit 1";
		List<SysFileFolderInfoEntity> list = this.getHibernateTemplate().find(sql);
		if (list == null || list.isEmpty())
			return 0;
		return 1;
	}

	@Override
	public Integer getSysFileFolderInfoRef(SysFileFolderInfoEntity sysFileFolderInfoEntity, Map<String, Object> map) throws HSKDBException {
		String hql = "from SysFileInfo where 1=1";
		if (sysFileFolderInfoEntity.getFileFolderId() != null)
			hql += " and sysFfId = " + sysFileFolderInfoEntity.getFileFolderId();
		if (map != null && !map.isEmpty()) {
			hql += " and sysFfId in (select fileFolderId from SysFileFolderInfoEntity where 1=1";
			if (map.get("rbaId") != null && !map.get("rbaId").equals(""))
				hql += " and rbaId = " + Integer.parseInt(map.get("rbaId").toString());
			if (map.get("rbsId") != null && !map.get("rbsId").equals(""))
				hql += " and rbsId = " + Integer.parseInt(map.get("rbsId").toString());
			if (map.get("rbbId") != null && !map.get("rbbId").equals(""))
				hql += " and rbbId = " + Integer.parseInt(map.get("rbbId").toString());
			hql += ")";
		}
		hql += " and (isDelete = 1 or isDelete is null)";
		List<SysFileInfo> list = this.getHibernateTemplate().find(hql);
		if (list == null || list.isEmpty())
			return 0;
		return 1;
	}

	@Override
	public PagerModel getSysFileFolderInfoPagerModel(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKDBException {
		String hql = getFolderSql(sysFileFolderInfoEntity);
		hql += " order by if(isnull(createDate), 0, 1), createDate desc";
		return this.getHibernateDao().findByPage(hql);
	}

	@Override
	public List<SysFileFolderInfoEntity> getSysFileFolderInfoList(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKDBException {
		String hql = getFolderSql(sysFileFolderInfoEntity);
		hql += " order by if(isnull(createDate), 0, 1), createDate desc";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public Integer getSysFileInfoCount(Integer fileFolderId, Map<String, Object> map) throws HSKDBException {
		String hql = "from SysFileInfo where 1=1";
		if (fileFolderId != null)
			hql += " and sysFfId = " + fileFolderId;
		if (map != null && !map.isEmpty()) {
			hql += " and sysFfId in (select fileFolderId from SysFileFolderInfoEntity where 1=1";
			if (map.get("rbaId") != null && !map.get("rbaId").equals(""))
				hql += " and rbaId = " + Integer.parseInt(map.get("rbaId").toString());
			if (map.get("rbsId") != null && !map.get("rbsId").equals(""))
				hql += " and rbsId = " + Integer.parseInt(map.get("rbsId").toString());
			if (map.get("rbbId") != null && !map.get("rbbId").equals(""))
				hql += " and rbbId = " + Integer.parseInt(map.get("rbbId").toString());
			if (fileFolderId != null)
				hql += " and fileFolderId = " + fileFolderId;
			hql += ")";
		}
		hql += " and (isDelete = 1 or isDelete is null)";
		List<SysFileInfo> list = this.getHibernateTemplate().find(hql);
		if (list == null || list.isEmpty())
			return 0;
		return list.size();
	}

	@Override
	public List<SysFileInfo> getSysFileInfoByCodesByFileFolderId(String codes, Integer fileFolderId, Map<String, Object> map) throws HSKDBException {
		String hql = "from SysFileInfo where 1=1";
		if (fileFolderId != null)
			hql += " and sysFfId = " + fileFolderId;
		if (map != null && !map.isEmpty()) {
			hql += " and sysFfId in (select fileFolderId from SysFileFolderInfoEntity where 1=1";
			if (map.get("rbaId") != null && !map.get("rbaId").equals(""))
				hql += " and rbaId = " + Integer.parseInt(map.get("rbaId").toString());
			if (map.get("rbsId") != null && !map.get("rbsId").equals(""))
				hql += " and rbsId = " + Integer.parseInt(map.get("rbsId").toString());
			if (map.get("rbbId") != null && !map.get("rbbId").equals(""))
				hql += " and rbbId = " + Integer.parseInt(map.get("rbbId").toString());
			if (fileFolderId != null)
				hql += " and fileFolderId = " + fileFolderId;
			hql += ")";
		}
		hql += " and (isDelete = 1 or isDelete is null)";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public String getSysFileFolderInfoFirstImg(Integer fileFolderId, Map<String, Object> map) throws HSKDBException {
		String hql = "select root_path as rootPath from sys_file_info where 1=1";
		if (fileFolderId != null)
			hql += " and sys_ff_id = " + fileFolderId;
		if (map != null && !map.isEmpty()) {
			hql += " and sys_ff_id in (select file_folder_id from sys_file_folder_info where 1=1";
			if (map.get("rbaId") != null && !map.get("rbaId").equals(""))
				hql += " and rba_id = " + Integer.parseInt(map.get("rbaId").toString());
			if (map.get("rbsId") != null && !map.get("rbsId").equals(""))
				hql += " and rbs_id = " + Integer.parseInt(map.get("rbsId").toString());
			if (map.get("rbbId") != null && !map.get("rbbId").equals(""))
				hql += " and rbb_id = " + Integer.parseInt(map.get("rbbId").toString());
			if (fileFolderId != null)
				hql += " and file_folder_id = " + fileFolderId;
			hql += ")";
		}
		hql += " and (is_delete = 1 or is_delete is null)";
		hql += " limit 1";
		List<Map<String, String>> list = this.getJdbcDao().query(hql);
		if (list == null || list.isEmpty())
			return null;
		return list.get(0).get("rootPath");
	}

	@Override
	public PagerModel getSysFileInfoPagerNodel(Integer fileFolderId, Map<String, Object> map) throws HSKDBException {
		String hql = "from SysFileInfo where 1=1";
		if (fileFolderId != null) {
			if (Objects.equals(fileFolderId, 1))
				hql += "and (sysFfId is null or sysFfId = 1)";
			else
				hql += " and sysFfId = " + fileFolderId;
		}
		if (map != null && !map.isEmpty()) {
			hql += " and sysFfId in (select fileFolderId from SysFileFolderInfoEntity where 1=1";
			if (map.get("rbaId") != null && !map.get("rbaId").equals(""))
				hql += " and rbaId = " + Integer.parseInt(map.get("rbaId").toString());
			if (map.get("rbsId") != null && !map.get("rbsId").equals(""))
				hql += " and rbsId = " + Integer.parseInt(map.get("rbsId").toString());
			if (map.get("rbbId") != null && !map.get("rbbId").equals(""))
				hql += " and rbbId = " + Integer.parseInt(map.get("rbbId").toString());
			if (fileFolderId != null)
				hql += " and fileFolderId = " + fileFolderId;
//			if (map.get("suiId") != null)
//				hql += " and suiId = " + map.get("suiId");
			hql += ")";
		}
		hql += " and (isDelete = 1 or isDelete is null)";
		hql += " order by createDate desc";
		return this.getHibernateDao().findByPage(hql);
	}

	@Override
	public List<Map<String, Object>> getSysFileFolderInfoRefCountMap(SysFileFolderInfoEntity sysFileFolderInfoEntity, Map<String, Object> map) throws HSKDBException {
		String hql = "select count(*) as count, sys_ff_id as fileFolderId, root_path as rootPath from sys_file_info where 1=1";
		if (sysFileFolderInfoEntity.getFileFolderIds() != null && !sysFileFolderInfoEntity.getFileFolderIds().equals(""))
			hql += " and sys_ff_id in (" + sysFileFolderInfoEntity.getFileFolderIds() + ")";
		if (map != null && !map.isEmpty()) {
			hql += " and sys_ff_id in (select file_folder_id from sys_file_folder_info where 1=1";
			if (map.get("rbaId") != null && !map.get("rbaId").equals(""))
				hql += " and rba_id = " + Integer.parseInt(map.get("rbaId").toString());
			if (map.get("rbsId") != null && !map.get("rbsId").equals(""))
				hql += " and rbs_id = " + Integer.parseInt(map.get("rbsId").toString());
			if (map.get("rbbId") != null && !map.get("rbbId").equals(""))
				hql += " and rbb_id = " + Integer.parseInt(map.get("rbbId").toString());
			if (sysFileFolderInfoEntity.getFileFolderIds() != null && !sysFileFolderInfoEntity.getFileFolderIds().equals(""))
				hql += " and file_folder_id in (" + sysFileFolderInfoEntity.getFileFolderIds() + ")";
			hql += ")";
		}
		hql += " and (is_delete = 1 or is_delete is null) group by sys_ff_id";
		List<Map<String, Object>> list = this.getJdbcDao().query(hql);
		if (list == null || list.isEmpty())
			return null;
		return list;
	}

	@Override
	public List<Map<String, Object>> getSysFileFolderInfoRefImgMap(SysFileFolderInfoEntity sysFileFolderInfoEntity, Map<String, Object> map) throws HSKDBException {
		String hql = "select root_path as rootPath from sys_file_info where 1=1";
		if (sysFileFolderInfoEntity.getFileFolderId() != null)
			hql += " and sys_ff_id = " + sysFileFolderInfoEntity.getFileFolderId();
		if (map != null && !map.isEmpty()) {
			hql += " and sys_ff_id in (select file_folder_id from sys_file_folder_info where 1=1";
			if (map.get("rbaId") != null && !map.get("rbaId").equals(""))
				hql += " and rba_id = " + Integer.parseInt(map.get("rbaId").toString());
			if (map.get("rbsId") != null && !map.get("rbsId").equals(""))
				hql += " and rbs_id = " + Integer.parseInt(map.get("rbsId").toString());
			if (map.get("rbbId") != null && !map.get("rbbId").equals(""))
				hql += " and rbb_id = " + Integer.parseInt(map.get("rbbId").toString());
			if (sysFileFolderInfoEntity.getFileFolderId() != null)
				hql += " and file_folder_id = " + sysFileFolderInfoEntity.getFileFolderId();
			hql += ")";
		}
		hql += " and (is_delete = 1 or is_delete is null)";
		hql += " limit 1";
		List<Map<String, Object>> list = this.getJdbcDao().query(hql);
		if (list == null || list.isEmpty())
			return null;
		return list;
	}

	@Override
	public List<SysFileInfo> getSysFileInfoByFileId(String imgIds) throws HSKDBException {
		if (imgIds == null || imgIds.equals(""))
			return null;
		String hql = "from SysFileInfo where 1=1";
		if (imgIds != null && !imgIds.equals(""))
			hql += " and fileId in (" + imgIds + ")";
		return this.getHibernateTemplate().find(hql);
	}
}
