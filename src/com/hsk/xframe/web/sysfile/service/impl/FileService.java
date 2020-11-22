package com.hsk.xframe.web.sysfile.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.xframe.api.daobbase.IorgDao;
import com.hsk.xframe.api.persistence.SysFileFolderInfoEntity;
import com.hsk.xframe.api.persistence.SysOrgGx;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jbpm.pvm.internal.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.supper.until.web.WebUtils;
import com.hsk.xframe.api.daobbase.ISysFileInfoDao;
import com.hsk.xframe.api.persistence.SysFileInfo;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.RandomCodeUtils;
import com.hsk.xframe.web.sysfile.service.IFileService;

import javax.imageio.ImageIO;

@Service
public class FileService extends DSTService implements IFileService {
	
	@Autowired
	private ISysFileInfoDao sysFileInfoDao;
	@Autowired
	private IorgDao orgDao;
	
	private final String dirName = "upFile";
	
	@Override
	public SysRetrunMessage addfile() throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1);
		this.getModel().addAttribute(WebUtils.contentType, WebUtils.CONTENTTYPE_TEXTHTML);
		this.getModel().addAttribute(WebUtils.charset, WebUtils.CONTENT_CHARSET_UTF8);
		String fieldName = (request.getAttribute("fileType")!= null && !request.getAttribute("fileType").toString().trim().equals(""))?request.getAttribute("fileType").toString():"file";
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
		extMap.put("file", "html,txt,xls,xlsx,word,doc,docx,gif,jpg,jpeg,png,bmp,rar,zip,pdf,ppt,pptx,avi,rmvb,rm,swf,flv,mp4");
		extMap.put("xlsfile", "xls,xlsx");
		extMap.put("rarfile", "rar,zip");
		if (!ServletFileUpload.isMultipartContent(request)) {
			srm.setCode(Long.valueOf(2));
			srm.setMeg("请选择文件。");
			return srm;
		}

		String savePath = SystemContext.updown_File_path + "/";
		String rootUrl = request.getContextPath() + "/fileInfo/updown/";

		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			srm.setCode(2l);
			srm.setMeg("上传目录不存在。");
			return srm;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			srm.setCode(2l);
			srm.setMeg("上传目录没有写权限。");
			return srm;
		}
		SysUserInfo ma = this.GetOneSessionAccount();
		String UserCode = (ma == null) ? null : ma.getAccount();
		// 创建文件夹
		UserCode = UserCode == null ? "admin" : UserCode;
		savePath += this.dirName + "/" + UserCode + "/";
		rootUrl += this.dirName + "/" + UserCode + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		// 最大文件大小
		long maxSize = 100000000;
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");

		StringBuffer nameBuf = new StringBuffer();
		try {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				String fileName = item.getName();
				if (fileName.indexOf("/") != -1) {
					fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
				}
				nameBuf.append(fileName + "");
				@SuppressWarnings("unused")
				long fileSize = item.getSize();
				if (!item.isFormField()) {
					// 检查文件大小
					if (item.getSize() > maxSize) {
						srm.setCode(Long.valueOf(2));
						srm.setMeg("上传文件大小超过限制。");
						return srm;
					}
					// 检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if (!Arrays.<String> asList(extMap.get(fieldName).split(",")).contains(fileExt)) {
						srm.setCode(Long.valueOf(2));
						srm.setMeg("上传文件扩展名是不允许的扩展名。 ");
						return srm;
					}
					fileName = fileName.substring(0, fileName.lastIndexOf("."));
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//					String newFileName = fileName + "_" + df.format(new Date()) + "_" + new Random().nextInt(1000) + "."
//							+ fileExt;
					String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
					File uploadedFile = new File(savePath, newFileName);
					// 存储文件
					SysFileInfo sysFileInfo=new SysFileInfo();
					sysFileInfo.setFileName(nameBuf.toString());
					sysFileInfo.setFilePath(savePath + newFileName);
					sysFileInfo.setFileType(fileExt);
					sysFileInfo.setRootPath(rootUrl + newFileName);
					sysFileInfo.setFileState("1");
					String fileCode = RandomCodeUtils.getRandomString(32);
					sysFileInfo.setFileCode(fileCode);
					this.newObject(sysFileInfo);
					item.write(uploadedFile);
					srm.setObj(sysFileInfo);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			srm.setCode(Long.valueOf(0));
			srm.setMeg(e1.getMessage());
		}
		return srm;
	}

	@Override
	public SysRetrunMessage deletefile(String fileCode) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1);
		if (fileCode == null) {
			srm.setCode(Long.valueOf(0));
			srm.setMeg("输出的文件编号有错！");
			return srm;
		}
		SysFileInfo sysFileInfo=new SysFileInfo();
		sysFileInfo.setFileCode(fileCode);
		sysFileInfo=(SysFileInfo) this.getOne(sysFileInfo);
		//this.deleteObjects(sysFileInfo);
		return srm;
	}

	@Override
	public SysRetrunMessage GetOneFileInfo(String fileCode) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1);
		try {
			if (fileCode == null) {
				srm.setCode(Long.valueOf(0));
				srm.setMeg("输出的文件编号有错！");
				return srm;
			}
			SysFileInfo sysFileInfo=new SysFileInfo();
			sysFileInfo.setFileCode(fileCode);
			sysFileInfo=(SysFileInfo) this.getOne(sysFileInfo);
			srm.setObj(sysFileInfo);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(Long.valueOf(0));
			srm.setMeg(e.getMessage());
		}
		return srm;
	}

	@Override
	public SysRetrunMessage GetListFileInfo(String fileCodes)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			List<SysFileInfo> list = new ArrayList<SysFileInfo>();
			String codes="";
			if(fileCodes != null && !fileCodes.trim().equals("")){
				String[] fileCodeArray = fileCodes.split(",");
				for(String fileCode : fileCodeArray)
					codes += "'"+fileCode+"',";
				codes = codes.substring(0, codes.length()-1);
				list = sysFileInfoDao.getSysFileInfoByCodes(codes);
			}
			
			srm.setObj(list);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("查询失败!");
		}
		return srm;
	}

	@Override
	public List<SysFileInfo> GetListFileInfoByCodes(String fileCodes)
			throws HSKException {
		List<SysFileInfo> list = new ArrayList<SysFileInfo>();
		try {
			
			String codes="";
			if(fileCodes != null && !fileCodes.trim().equals("")){
				String[] fileCodeArray = fileCodes.split(",");
				for(String fileCode : fileCodeArray)
					codes += "'"+fileCode+"',";
				codes = codes.substring(0, codes.length()-1);
				list = sysFileInfoDao.getSysFileInfoByCodes(codes);
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public SysRetrunMessage deletefileByPath(String filePath) throws Exception {
		SysRetrunMessage srm = new SysRetrunMessage(1);
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
			srm.setMeg("ok");
		} else {
			srm.setCode(Long.valueOf(0));
			srm.setMeg("文件不存在！");
		}
		return srm;
	}
	/************************* 相册管理相关（新的文件上传功能） ********************************/
	@Override
	public SysRetrunMessage addFile(Integer fileFolderId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1);
		this.getModel().addAttribute(WebUtils.contentType, WebUtils.CONTENTTYPE_TEXTHTML);
		this.getModel().addAttribute(WebUtils.charset, WebUtils.CONTENT_CHARSET_UTF8);
		String fieldName = (request.getAttribute("fileType")!= null && !request.getAttribute("fileType").toString().trim().equals(""))?request.getAttribute("fileType").toString():"file";
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
		extMap.put("file", "html,txt,xls,xlsx,word,doc,docx,gif,jpg,jpeg,png,bmp,rar,zip,pdf,ppt,pptx,avi,rmvb,rm,swf,flv,mp4");
		extMap.put("xlsfile", "xls,xlsx");
		extMap.put("rarfile", "rar,zip");
		if (!ServletFileUpload.isMultipartContent(request)) {
			srm.setCode(Long.valueOf(2));
			srm.setMeg("请选择文件。");
			return srm;
		}

		String savePath = SystemContext.updown_File_path + "/";
		String rootUrl = request.getContextPath() + "/fileInfo/updown/";

		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			srm.setCode(2l);
			srm.setMeg("上传目录不存在。");
			return srm;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			srm.setCode(2l);
			srm.setMeg("上传目录没有写权限。");
			return srm;
		}
		SysUserInfo ma = this.GetOneSessionAccount();
		String UserCode = (ma == null) ? null : ma.getAccount();
		// 创建文件夹
		SysFileFolderInfoEntity sysFileFolderInfoEntity = new SysFileFolderInfoEntity();
		sysFileFolderInfoEntity.setFileFolderId(fileFolderId);
		sysFileFolderInfoEntity = (SysFileFolderInfoEntity) this.getOne(sysFileFolderInfoEntity);
		UserCode = sysFileFolderInfoEntity == null ? "公共资源" : sysFileFolderInfoEntity.getFileFolderName();
		savePath += this.dirName + "/" + UserCode + "/";
		rootUrl += this.dirName + "/" + UserCode + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		// 最大文件大小
		long maxSize = 100000000;
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");

		StringBuffer nameBuf = new StringBuffer();
		try {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			List<SysFileInfo> list = new ArrayList<>();
			String fName = "";
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				BufferedImage bi = ImageIO.read(item.getInputStream());
				Integer imgWidth = bi.getHeight();
				Integer imgHeight = bi.getWidth();
				String fileName = item.getName();
				if (fileName.indexOf("/") != -1) {
					fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
				}
				nameBuf.append(fileName + "");
				fName = fileName;
				@SuppressWarnings("unused")
				long fileSize = item.getSize();
				if (!item.isFormField()) {
					// 检查文件大小
					if (item.getSize() > maxSize) {
						srm.setCode(Long.valueOf(2));
						srm.setMeg("上传文件大小超过限制。");
						return srm;
					}
					// 检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if (!Arrays.<String> asList(extMap.get(fieldName).split(",")).contains(fileExt)) {
						srm.setCode(Long.valueOf(2));
						srm.setMeg("上传文件扩展名是不允许的扩展名。 ");
						return srm;
					}
					fileName = fileName.substring(0, fileName.lastIndexOf("."));
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//					String newFileName = fileName + "_" + df.format(new Date()) + "_" + new Random().nextInt(1000) + "."
//							+ fileExt;
					String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
					File uploadedFile = new File(savePath, newFileName);
					// 存储文件
					SysFileInfo sysFileInfo=new SysFileInfo();
//					sysFileInfo.setFileName(nameBuf.toString());
					sysFileInfo.setFileName(fName);
					sysFileInfo.setFilePath(savePath + newFileName);
					sysFileInfo.setFileType(fileExt);
					sysFileInfo.setRootPath(rootUrl + newFileName);
					sysFileInfo.setFileState("1");
					String fileCode = RandomCodeUtils.getRandomString(32);
					sysFileInfo.setFileCode(fileCode);
					sysFileInfo.setSysFfId(fileFolderId);
					sysFileInfo.setFileSize(fileSize + "");
					sysFileInfo.setIsDelete(1);
					sysFileInfo.setImgWidth(imgWidth.toString());
					sysFileInfo.setImgHeight(imgHeight.toString());
					this.newObject(sysFileInfo);
					item.write(uploadedFile);
					list.add(sysFileInfo);
				}
				srm.setObj(list);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			srm.setCode(Long.valueOf(0));
			srm.setMeg(e1.getMessage());
		}
		return srm;
	}

	@Override
	public SysRetrunMessage deleteFile(String fileCode, Integer fileFolderId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1);
		try {
			if (fileCode == null) {
				srm.setCode(2L);
				srm.setMeg("输出的文件编号有错！");
				return srm;
			}
			SysFileInfo sysFileInfo = new SysFileInfo();
			sysFileInfo.setFileCode(fileCode);
			sysFileInfo.setSysFfId(fileFolderId);
			sysFileInfo = (SysFileInfo) this.getOne(sysFileInfo);
			sysFileInfo.setIsDelete(0);
			this.deleteObjects(sysFileInfo);
		} catch (Exception e) {
			srm.setCode(0L);
			e.printStackTrace();
		}
		return srm;
	}

	@Override
	public SysRetrunMessage getOneFileInfo(String fileCode, Integer fileFolderId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1);
		try {
			if (fileCode == null) {
				srm.setCode(Long.valueOf(0));
				srm.setMeg("输出的文件编号有错！");
				return srm;
			}
			SysFileInfo sysFileInfo=new SysFileInfo();
			sysFileInfo.setFileCode(fileCode);
			sysFileInfo.setSysFfId(fileFolderId);
			sysFileInfo=(SysFileInfo) this.getOne(sysFileInfo);
			srm.setObj(sysFileInfo);
		} catch (Exception e) {
			e.printStackTrace();
			srm.setCode(Long.valueOf(0));
			srm.setMeg(e.getMessage());
		}
		return srm;
	}

	@Override
	public SysRetrunMessage getListFileInfo(String fileCodes, Integer fileFolderId) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			Map<String, Object> map = this.getAccountBelong();
			List<SysFileInfo> list = new ArrayList<SysFileInfo>();
			String codes="";
			if(fileCodes != null && !fileCodes.trim().equals("")){
				String[] fileCodeArray = fileCodes.split(",");
				for(String fileCode : fileCodeArray)
					codes += "'"+fileCode+"',";
				codes = codes.substring(0, codes.length()-1);
				list = sysFileInfoDao.getSysFileInfoByCodesByFileFolderId(codes, fileFolderId, map);
			}

			srm.setObj(list);
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg("查询失败!");
		}
		return srm;
	}

	@Override
	public List<SysFileInfo> getListFileInfoByCodes(String fileCodes, Integer fileFolderId) throws HSKException {
		List<SysFileInfo> list = new ArrayList<SysFileInfo>();
		try {
			Map<String, Object> map = this.getAccountBelong();
			String codes="";
			if(fileCodes != null && !fileCodes.trim().equals("")){
				String[] fileCodeArray = fileCodes.split(",");
				for(String fileCode : fileCodeArray)
					codes += "'"+fileCode+"',";
				codes = codes.substring(0, codes.length()-1);
				list = sysFileInfoDao.getSysFileInfoByCodesByFileFolderId(codes, fileFolderId, map);
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public SysRetrunMessage deleteFileByPath(String filePath, Integer fileFolderId) throws Exception {
		SysRetrunMessage srm = new SysRetrunMessage(1);
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
			srm.setMeg("ok");
		} else {
			srm.setCode(Long.valueOf(0));
			srm.setMeg("文件不存在！");
		}
		return srm;
	}

	@Override
	public PagerModel getSysFileInfoPagerModel(Integer fileFolderId) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList());
		try {
			Map<String, Object> map = this.getAccountBelong();
			pm = sysFileInfoDao.getSysFileInfoPagerNodel(fileFolderId, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public SysRetrunMessage updateSysFileInfoBatch(String fileIds, Integer fileFolderId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		if (fileIds == null || fileIds.equals("")) {
			sm.setCode(2L); // 传入的文件id是空
			return sm;
		}
		if (fileFolderId == null) {
			sm.setCode(3L); // 传入的文件夹名是空
			return sm;
		}
		try {
			String[] fileIdArray = fileIds.split(",");
			Integer fileId = null;
			SysFileInfo sysFileInfo;
			for (String fileIdS : fileIdArray) {
				if (fileIds.equals(""))
					continue;
				fileId = Integer.parseInt(fileIdS);
				sysFileInfo = new SysFileInfo();
				sysFileInfo.setFileId(fileId);
				sysFileInfo = (SysFileInfo) this.getOne(sysFileInfo);
				if (sysFileInfo == null)
					continue;
				sysFileInfo.setSysFfId(fileFolderId);
				this.updateObject(sysFileInfo);
			}
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage deleteSysFileInfoBatch(String fileIds, Integer fileFolderId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		if (fileIds == null || fileIds.equals("")) {
			sm.setCode(2L); // 传入的文件id是空
			return sm;
		}
		if (fileFolderId == null) {
			sm.setCode(3L); // 传入的文件夹名是空
			return sm;
		}
		try {
			String[] fileIdArray = fileIds.split(",");
			Integer fileId = null;
			SysFileInfo sysFileInfo;
			for (String fileIdS : fileIdArray) {
				if (fileIds.equals(""))
					continue;
				fileId = Integer.parseInt(fileIdS);
				sysFileInfo = new SysFileInfo();
				sysFileInfo.setFileId(fileId);
				sysFileInfo.setSysFfId(fileFolderId);
				sysFileInfo = (SysFileInfo) this.getOne(sysFileInfo);
				if (sysFileInfo == null)
					continue;
				sysFileInfo.setIsDelete(0);
				this.updateObject(sysFileInfo);
			}
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage saveSysFileFolderInfo(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			SysOrgGx sysOrgGx = new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			Integer rbaId = null;
			Integer rbsId = null;
			Integer rbbId = null;
			if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
				rbaId = account.getOldId();
			} else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
				if (orgMap.containsKey("one")) {//如果存在上级集团
					rbaId = Integer.parseInt(orgMap.get("one"));
				}
				rbsId = account.getOldId();
			} else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
				if (orgMap.containsKey("one")) {//如果存在上级集团
					rbaId = Integer.parseInt(orgMap.get("one"));
				}
				if (orgMap.containsKey("tow")) {//如果存在上级医院
					rbsId = Integer.parseInt(orgMap.get("tow"));
				}
				rbbId = account.getOldId();
			}
			sysFileFolderInfoEntity.setRbaId(rbaId);
			sysFileFolderInfoEntity.setRbsId(rbsId);
			sysFileFolderInfoEntity.setRbbId(rbbId);
			sysFileFolderInfoEntity.setSuiId(account.getSuiId());
			Integer count = sysFileInfoDao.getSysFileFolderInfoCount(sysFileFolderInfoEntity, null);
			if (count > 0) {
				sm.setCode(2L); // 此账号下存在同名相册名
				return sm;
			}
//			sysFileFolderInfoEntity.setCreateDate(new Date());
//			sysFileFolderInfoEntity.setCreateRen(account.getAccount());
//			sysFileFolderInfoEntity.setEditDate(new Date());
//			sysFileFolderInfoEntity.setEditRen(account.getAccount());
			this.newObject(sysFileFolderInfoEntity);
			sm.setObj(sysFileFolderInfoEntity);
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage updateFileFolderInfo(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			SysUserInfo account = this.GetOneSessionAccount();
			SysOrgGx sysOrgGx = new SysOrgGx();
			sysOrgGx.setOrgGxId(account.getOrgGxId());
			Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
			Integer rbaId = null;
			Integer rbsId = null;
			Integer rbbId = null;
			if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
				rbaId = account.getOldId();
			} else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
				if (orgMap.containsKey("one")) {//如果存在上级集团
					rbaId = Integer.parseInt(orgMap.get("one"));
				}
				rbsId = account.getOldId();
			} else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
				if (orgMap.containsKey("one")) {//如果存在上级集团
					rbaId = Integer.parseInt(orgMap.get("one"));
				}
				if (orgMap.containsKey("tow")) {//如果存在上级医院
					rbsId = Integer.parseInt(orgMap.get("tow"));
				}
				rbbId = account.getOldId();
			}
			sysFileFolderInfoEntity.setRbaId(rbaId);
			sysFileFolderInfoEntity.setRbsId(rbsId);
			sysFileFolderInfoEntity.setRbbId(rbbId);
			sysFileFolderInfoEntity.setSuiId(account.getSuiId());
			Integer count = sysFileInfoDao.getSysFileFolderInfoCount(sysFileFolderInfoEntity, sysFileFolderInfoEntity.getFileFolderId());
			if (count > 0) {
				sm.setCode(2L); // 此账号下存在同名相册名
				return sm;
			}
			SysFileFolderInfoEntity sysFileFolderInfoEntity1 = new SysFileFolderInfoEntity();
			sysFileFolderInfoEntity1.setFileFolderId(sysFileFolderInfoEntity.getFileFolderId());
			sysFileFolderInfoEntity1 = (SysFileFolderInfoEntity) this.getOne(sysFileFolderInfoEntity1);
			sysFileFolderInfoEntity1.setRbaId(sysFileFolderInfoEntity.getRbaId());
			sysFileFolderInfoEntity1.setRbbId(sysFileFolderInfoEntity.getRbbId());
			sysFileFolderInfoEntity1.setRbsId(sysFileFolderInfoEntity.getRbsId());
			sysFileFolderInfoEntity1.setFileFolderName(sysFileFolderInfoEntity.getFileFolderName());
			sysFileFolderInfoEntity1.setFileFolderDescribe(sysFileFolderInfoEntity.getFileFolderDescribe());
			sysFileFolderInfoEntity1.setSuiId(account.getSuiId());
			this.updateObject(sysFileFolderInfoEntity1);
			sm.setObj(sysFileFolderInfoEntity);
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage deleteFileFolderInfo(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		if (sysFileFolderInfoEntity.getFileFolderId() == null){
			sm.setCode(3L); // 没有id
			return sm;
		}
		try {
			Map<String, Object> map = this.getAccountBelong();
			Integer count = sysFileInfoDao.getSysFileFolderInfoRef(sysFileFolderInfoEntity, map);
			if (count > 0) {
				sm.setCode(2L); // 此文件夹下存在图片
				return sm;
			}
			this.deleteObjects(sysFileFolderInfoEntity);
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage findFormFileFolderInfo(Integer fileFolderId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		if (fileFolderId == null) {
			sm.setCode(2L); // 没有文件夹id
			return sm;
		}
		try {
			SysFileFolderInfoEntity sysFileFolderInfoEntity = new SysFileFolderInfoEntity();
			sysFileFolderInfoEntity.setFileFolderId(fileFolderId);
			sysFileFolderInfoEntity = (SysFileFolderInfoEntity) this.getOne(sysFileFolderInfoEntity);
			sm.setObj(sysFileFolderInfoEntity);
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public PagerModel getFileFolderInfoPagerModel(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKException {
		PagerModel pm = new PagerModel(new ArrayList());
		try {
			Map<String, Object> map = this.getAccountBelong();
			if (map.get("rbaId") != null && !map.get("rbaId").toString().equals(""))
				sysFileFolderInfoEntity.setRbaId(Integer.parseInt(map.get("rbaId").toString()));
			if (map.get("rbsId") != null && !map.get("rbsId").toString().equals(""))
				sysFileFolderInfoEntity.setRbsId(Integer.parseInt(map.get("rbsId").toString()));
			if (map.get("rbbId") != null && !map.get("rbbId").toString().equals(""))
				sysFileFolderInfoEntity.setRbbId(Integer.parseInt(map.get("rbbId").toString()));
			pm = sysFileInfoDao.getSysFileFolderInfoPagerModel(sysFileFolderInfoEntity);
			List<SysFileFolderInfoEntity> list = pm.getItems();
			String fileFolderIds = "";
			for (SysFileFolderInfoEntity entity : list) {
				fileFolderIds += entity.getFileFolderId() + ",";
			}
			if (!fileFolderIds.equals("")) {
				fileFolderIds = fileFolderIds.substring(0, fileFolderIds.length() - 1);
				sysFileFolderInfoEntity.setFileFolderIds(fileFolderIds);
				List<Map<String, Object>> countMap = sysFileInfoDao.getSysFileFolderInfoRefCountMap(sysFileFolderInfoEntity, map);
				for (SysFileFolderInfoEntity entity : list) {
					entity.setImgCount(0);
					entity.setImgPath("");
					if (countMap == null)
						continue;
					for (Map<String, Object> mapString : countMap) {
						if(mapString.get("fileFolderId") != null && !mapString.get("fileFolderId").toString().equals("")) {
							if (Objects.equals(entity.getFileFolderId(), Integer.parseInt(mapString.get("fileFolderId").toString()))) {
								entity.setImgCount(Integer.parseInt(mapString.get("count").toString()));
								entity.setImgPath(mapString.get("rootPath").toString());
								break;
							}
						}
					}
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return pm;
	}

	@Override
	public SysRetrunMessage getFileFolderInfoList(SysFileFolderInfoEntity sysFileFolderInfoEntity) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			Map<String, Object> map = this.getAccountBelong();
			if (map.get("rbaId") != null && !map.get("rbaId").toString().equals(""))
				sysFileFolderInfoEntity.setRbaId(Integer.parseInt(map.get("rbaId").toString()));
			if (map.get("rbsId") != null && !map.get("rbsId").toString().equals(""))
				sysFileFolderInfoEntity.setRbsId(Integer.parseInt(map.get("rbsId").toString()));
			if (map.get("rbbId") != null && !map.get("rbbId").toString().equals(""))
				sysFileFolderInfoEntity.setRbbId(Integer.parseInt(map.get("rbbId").toString()));
			List<SysFileFolderInfoEntity> list = sysFileInfoDao.getSysFileFolderInfoList(sysFileFolderInfoEntity);
			sm.setObj(list);
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getFileFolderInfoFirstImg(Integer fileFolderId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			Map<String, Object> map = this.getAccountBelong();
			String rootPath = sysFileInfoDao.getSysFileFolderInfoFirstImg(fileFolderId, map);
			sm.setObj(rootPath);
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}

	@Override
	public SysRetrunMessage getFileInfoCount(Integer fileFolderId) throws HSKException {
		SysRetrunMessage sm = new SysRetrunMessage(1L);
		try {
			Map<String, Object> map = this.getAccountBelong();
			Integer count = sysFileInfoDao.getSysFileInfoCount(fileFolderId, map);
			sm.setObj(count);
		} catch (Exception e) {
			sm.setCode(0L);
			e.printStackTrace();
		}
		return sm;
	}
}
