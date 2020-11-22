package com.hsk.miniapi.xframe.web.sysfile.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.supper.until.web.WebUtils;
import com.hsk.miniapi.xframe.api.daobbase.ISysFileInfoApiDao;
import com.hsk.xframe.api.persistence.SysFileInfo;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.miniapi.xframe.service.imp.DSTApiService;
import com.hsk.xframe.api.utils.freeMarker.RandomCodeUtils;
import com.hsk.miniapi.xframe.web.sysfile.service.IFileApiService;

@Service
public class FileApiService extends DSTApiService implements IFileApiService {
	
	@Autowired
	private ISysFileInfoApiDao sysFileInfoDao;
	
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

}
