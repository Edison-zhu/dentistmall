package com.hsk.xframe.web.framework.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.supper.dto.config.HqlConfig;
import com.hsk.supper.until.javaclass.ClassUtil;
import com.hsk.xframe.api.daobbase.IFieldInfoDao;
import com.hsk.xframe.api.daobbase.ITablesInfoDao;
import com.hsk.xframe.api.dto.freeMarker.CodeConfig;
import com.hsk.xframe.api.dto.freeMarker.FMDaoInfo;
import com.hsk.xframe.api.dto.freeMarker.FMFieldInfo;
import com.hsk.xframe.api.dto.freeMarker.FMPojoInfo;
import com.hsk.xframe.api.dto.freeMarker.SysCodeInfo;
import com.hsk.xframe.api.persistence.SysClassColumns;
import com.hsk.xframe.api.persistence.SysClassInfo;
import com.hsk.xframe.api.persistence.SysFieldInfo;
import com.hsk.xframe.api.persistence.SysSystemInfo;
import com.hsk.xframe.api.persistence.SysTableInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.api.utils.freeMarker.AutoCodeUtils;
import com.hsk.xframe.api.utils.freeMarker.CreateCodeUtil;
import com.hsk.xframe.api.utils.freeMarker.FreeMarkerUtil;
import com.hsk.xframe.api.utils.freeMarker.ZipUtil;
import com.hsk.xframe.web.framework.service.ICodeAutoService;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 自动化业务处理函数
 * 
 * @author xun
 * 
 */
@Service
public class CodeAutoService extends DSTService implements ICodeAutoService {

	@Autowired
	protected IFieldInfoDao fieldInfoDao;
	@Autowired
	protected ITablesInfoDao tablesInfoDao;

	@Override
	public void CreatePojoFile(String saveFile, String tableName,
			String UserName) throws HSKException {
		
		
		// 获取表对象
		try {
			FMPojoInfo fmpi = tablesInfoDao.queryTableInfo(tableName, UserName);
			List<FMFieldInfo> list_pmfi = fieldInfoDao.queryListFieldInfo(
					tableName, UserName);

			String authorText = "xun";
			String versionText = "v1.0版本";
			FMPojoInfo lo_create = new FMPojoInfo(saveFile,
					fmpi.getTableName(), authorText, versionText,
					fmpi.getCommentText(), list_pmfi);
			FreeMarkerUtil.PoJoProcess(lo_create);
		} catch (HSKException e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
	}

	@Override
	public void CreateDaoFile(String saveFile, String pojoPackage,
			String tableName, String UserName) throws HSKException {
		// 获取表对象
		try {
			FMPojoInfo fmpi = tablesInfoDao.queryTableInfo(tableName, UserName);
			List<FMFieldInfo> list_pmfi = fieldInfoDao.queryListFieldInfo(
					tableName, UserName);

			String authorText = "xun";
			String versionText = "v1.0版本";
			FMDaoInfo lo_create = new FMDaoInfo(saveFile, pojoPackage,
					tableName, authorText, versionText, fmpi.getCommentText(),
					list_pmfi);
			FreeMarkerUtil.DaoProcess(lo_create);
		} catch (HSKException e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
	}

	@Override
	public List<FMPojoInfo> getTableList(String UserName) throws HSKException {
		try {
			return tablesInfoDao.queryListTableInfo(UserName);
		} catch (HSKException e) {
			e.printStackTrace();
			throw new HSKException(e);
		}

	}

	@Override
	public List<FMFieldInfo> getFieldList(String TableName, String UserName)
			throws HSKException {
		try {
			return fieldInfoDao.queryListFieldInfo(TableName,
					(UserName != null) ? UserName : CodeConfig.DbUserName);
		} catch (HSKException e) {
			e.printStackTrace();
			throw new HSKException(e);
		}

	}

	public PagerModel getPagerModelsysClass(SysClassInfo sci)
			throws HSKException {
		PagerModel model = new PagerModel(new ArrayList());
		try {
			model = this.getPagerModel(sci);
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return model;
	}

	public PagerModel getListClassColumns(SysClassInfo sci) throws HSKException {
		PagerModel model = new PagerModel(new ArrayList());
		if (sci.getSciId() != null) {
			SysClassColumns scc = new SysClassColumns();
			scc.setSciId(sci.getSciId());
			try {
				model = this.getPagerModel(scc);
			} catch (HSKDBException e) {
				e.printStackTrace();
			}
		}
		return model;
	}

	public SysRetrunMessage getOnesysClass(SysClassInfo sci)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		SysCodeInfo scode = new SysCodeInfo();
		if (sci.getSciId() != null) {
			SysClassInfo sciobj = new SysClassInfo();
			Object obj = this.getOne(sciobj);
			if (obj != null) {
				sciobj = (SysClassInfo) obj;
				scode.setSysClassInfo(sciobj);
			}
			SysClassColumns scc = new SysClassColumns();
			scc.setSciId(sci.getSciId());
			try {
				List<SysClassColumns> list_scc = this.getList(scc);
				scode.setList_sysClassColumns(list_scc);
			} catch (HSKDBException e) {
				e.printStackTrace();
			}
		}
		srm.setObj(scode);
		return srm;
	}

	
	private  SysClassInfo  changeServiceClass(SysClassInfo sci){
		SysSystemInfo ssi = new SysSystemInfo();
		ssi.setSysCode(sci.getSysCode());
	 
		Object obj = this.getOne(ssi);
		if (obj != null) {
			ssi = (SysSystemInfo) obj;		
		String serviceName=sci.getServicename();
		String TableName=sci.getTableName();
		String packagename="com.hsk."+ssi.getProjectName()+".web."+sci.getServicename()+".service.imp";
		sci.setPackagename(packagename);
		String packagename2="com.hsk."+ssi.getProjectName()+".web."+sci.getServicename()+".service";
		sci.setPackagename2(packagename2);  
		sci.setSavepath2("/"+packagename2.replace(".", "/"));  
		sci.setSavepath("/"+packagename.replace(".", "/")); 
		}
		return sci;
	}
	
	public SysRetrunMessage saveOrUpdatesysClass(SysCodeInfo sci)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			if (sci.getSysClassInfo() != null
					&& sci.getSysClassInfo().getSciId() != null) {
				// 修改操作
				this.updateObject(sci.getSysClassInfo());
				Integer par_id = sci.getSysClassInfo().getSciId();
				if (sci.getList_sysClassColumns() != null
						&& sci.getList_sysClassColumns().size() > 0) {
					for (SysClassColumns did : sci.getList_sysClassColumns()) {
						this.delsysClassColumns(did);
						did.setSciId(par_id);
						this.newObject(did);
					}
				}

			} else if (sci.getSysClassInfo() != null) {
				// 添加操作
				sci.getSysClassInfo().setColValue2(sci.getSysClassInfo().getPkName());
				sci.getSysClassInfo().setColValue3(AutoCodeUtils.toCamelCase(sci.getSysClassInfo().getPkName()));
				sci.getSysClassInfo().setPkName(AutoCodeUtils.toCapitalizeCamelCase( sci.getSysClassInfo().getPkName()));
				 
				SysClassInfo att_sci =sci.getSysClassInfo();
				if("service".equals(att_sci.getSClassType())){
					att_sci=	this.changeServiceClass(att_sci);
					String col1=att_sci.getSClassName4();
					String pax=col1.substring(1, 2);
					col1=col1.substring(2, col1.length());
					col1=pax.toLowerCase()+col1;
					att_sci.setColValue1(col1);
				}if("controller".equals(att_sci.getSClassType())){
//					att_sci=	this.changeServiceClass(att_sci);
//					String col1=att_sci.getSClassName4();
//					String pax=col1.substring(1, 2);
//					col1=col1.substring(2, col1.length());
//					col1=pax.toLowerCase()+col1;
//					att_sci.setColValue1(col1);
				}
				Integer par_id = this.newObject(att_sci);
				if (sci.getList_sysClassColumns() != null
						&& sci.getList_sysClassColumns().size() > 0) {
					for (SysClassColumns did : sci.getList_sysClassColumns()) {
						did.setSciId(par_id);
						this.newObject(did);
					}
				}
			} else {
				srm.setCode(0l);
				srm.setMeg("系统类信息为空！");
			}
		} catch (Exception e) {
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
			e.printStackTrace();
		}
		return srm;
	}

	public SysRetrunMessage delsysClass(SysClassInfo sci) throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		if (sci.getSciId() != null) {
			try {
				SysClassInfo sciobj = new SysClassInfo();
				sciobj.setSciId(sci.getSciId());
				Object obj = this.getOne(sciobj);
				if (obj != null) {
					sciobj = (SysClassInfo) obj;
					String filePath=SystemContext.updown_File_path+sciobj.getProjectpath()+sciobj.getSClassCode();
					FreeMarkerUtil.deleteAllFileDir( filePath);
					this.deleteObjects(sciobj);
				}
				SysClassColumns scc = new SysClassColumns();
				scc.setSciId(sci.getSciId());
				List list_scc = this.getList(scc);
				if (list_scc != null && list_scc.size() > 0) {
					this.getHibernateDao().deleteAll(list_scc);
				}
			} catch (HSKDBException e) {
				e.printStackTrace();
				srm.setCode(0l);
				srm.setMeg(e.getMessage());
			}
		}else  if(sci.getSysCode()!=null){
			String[] ids=sci.getSysCode().split(",");
			for(String id:ids){
				try {
					SysClassInfo sciobj = new SysClassInfo();
					sciobj.setSciId(Integer.valueOf(id));
					Object obj = this.getOne(sciobj);
					if (obj != null) {
						sciobj = (SysClassInfo) obj;
						String filePath=SystemContext.updown_File_path+sciobj.getProjectpath()+sciobj.getSClassCode();
						FreeMarkerUtil.deleteAllFileDir( filePath);
						this.deleteObjects(sciobj);
					}
					SysClassColumns scc = new SysClassColumns();
					scc.setSciId(sci.getSciId());
					List list_scc = this.getList(scc);
					if (list_scc != null && list_scc.size() > 0) {
						this.getHibernateDao().deleteAll(list_scc);
					}
				} catch (HSKDBException e) {
					e.printStackTrace();
					srm.setCode(0l);
					srm.setMeg(e.getMessage());
				}
				
				
			}
		}
		return srm;
	}

	public SysRetrunMessage delsysClassColumns(SysClassColumns sci)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		try {
			if (sci.getSccId() != null) {
				SysClassColumns scc = new SysClassColumns();
				scc.setSccId(sci.getSccId());
				Object obj = this.getOne(scc);
				if (obj != null) {
					scc = (SysClassColumns) obj;
					this.deleteObjects(scc);
				}
			}
		} catch (HSKDBException e) {
			e.printStackTrace();
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
		}

		return srm;
	}

	public SysClassInfo getsysClass(SysClassInfo sci) throws HSKException {
		SysClassInfo scode = null;
		if (sci.getSciId() != null) {
			SysClassInfo sciobj = new SysClassInfo();
			sciobj.setSciId(sci.getSciId());
			Object obj = this.getOne(sciobj);
			if (obj != null) {
				scode = (SysClassInfo) obj;
			}
		}
		if (scode == null) {
			SysSystemInfo ssi = new SysSystemInfo();
			ssi.setSysCode(sci.getSysCode());
			Object obj = this.getOne(ssi);
			if (obj != null) {
				ssi = (SysSystemInfo) obj;
			}
			scode = new SysClassInfo();
			scode.setSysCode(ssi.getSysCode());
			scode.setSClassType(sci.getSClassType());
			scode.setSClassCode(CreateCodeUtil.getSysClassNo());
			scode.setSClassCode2(CreateCodeUtil.getSysClassNo());
			if("pojo".equals(scode.getSClassType())){
				String pname= "com.hsk."+ssi.getProjectName()+".api.persistence";
				scode.setPackagename(pname); 
				scode.setSavepath("/"+pname.replace(".", "/")); 
				
				scode.setProjectpath(ssi.getProjectRootPath());
				scode.setFtlname("pojo.ftl");
				scode.setFtlpath("ftl\\"+ssi.getDbType()+"\\");
			}else if("dao".equals(scode.getSClassType())){
				scode.setProjectpath(ssi.getProjectRootPath());
				 String packagename2="com.hsk."+ssi.getProjectName()+".api.daobbase";
				scode.setPackagename2(packagename2);
				scode.setSavepath2("/"+packagename2.replace(".", "/")); 
				
				String packagename="com.hsk."+ssi.getProjectName()+".api.daobbase.imp";
				scode.setPackagename(packagename);
				scode.setSavepath("/"+packagename.replace(".", "/")); 
				
				String packagename3= "com.hsk."+ssi.getProjectName()+".api.persistence";
				scode.setPackagename3(packagename3); 
				
				scode.setFtlname("dao.ftl");
				scode.setFtlname2("idao.ftl");
				scode.setFtlpath("ftl\\"+ssi.getDbType()+"\\");				
				
			}else if("service".equals(scode.getSClassType())){
 
				String packagename3= "com.hsk."+ssi.getProjectName()+".api.persistence";
				scode.setPackagename3(packagename3); 				
				
				String packagename4="com.hsk."+ssi.getProjectName()+".api.daobbase";
			    scode.setPackagename4(packagename4);
			    
			    scode.setFtlname("service.ftl");
				scode.setFtlname2("iservice.ftl");
				scode.setFtlpath("ftl\\"+ssi.getDbType()+"\\");	
				scode.setProjectpath(ssi.getProjectRootPath());
			}else if("controller".equals(scode.getSClassType())){
				
				String packagename3= "com.hsk."+ssi.getProjectName()+".api.persistence";
				scode.setPackagename3(packagename3); 				
			 
			    
			    scode.setFtlname("controller.ftl"); 
				scode.setFtlpath("ftl\\"+ssi.getDbType()+"\\");	
				scode.setProjectpath(ssi.getProjectRootPath());
				
			}
		 
			
		}
		return scode;
	}

	public SysRetrunMessage initClassColumns(SysClassInfo sci)
			throws HSKException {
		SysRetrunMessage srm = new SysRetrunMessage(1l);
		List<SysClassColumns> list_obj=new ArrayList<SysClassColumns>();
		srm.setObj(list_obj);
		try {
			if (sci.getSciId() != null && !sci.getSciId().equals("")) {
					SysClassColumns scc = new SysClassColumns();
					scc.setSciId(sci.getSciId()); 
					List list_model = this.getList(scc);
					if (list_model != null && list_model.size() > 0) {
						srm.setCode(0l);
						srm.setMeg("SciId=" + sci.getSciId() + "还有未删除的类属性记录!");
						return srm;
					}    
			 } 
			if(sci.getTableCode()!=null&&!"".equals(sci.getTableCode())){
				 list_obj=this.getClasscol(sci.getTableCode() ); 
				 srm.setObj(list_obj);
			}
		} catch (HSKDBException e) {
			srm.setCode(0l);
			srm.setMeg(e.getMessage());
			e.printStackTrace();
		} 
		return srm;
	}

	public List<SysClassColumns> getClasscol(String tableCode) {
		SysTableInfo sti = new SysTableInfo();
		List<SysClassColumns> list_scc = new ArrayList<SysClassColumns>();
		sti.setTableCode(tableCode);
		Object obj = this.getOne(sti);

		if (obj != null) {
			sti = (SysTableInfo) obj;
		} else {
			return list_scc;
		}
		SysFieldInfo sfi = new SysFieldInfo();
		sfi.setStableId(sti.getStableId());
	 
		try {
			HqlConfig conf =new HqlConfig ();
			conf.setOrderFieldASC("orderNumber"); 
			List<SysFieldInfo> list_obj = this.getList(sfi, conf);		

			if (list_obj != null && list_obj.size() > 0) {
			
				for (SysFieldInfo did : list_obj) {
					SysClassColumns row = new SysClassColumns();
					row.setFieldCode(did.getFieldCode());
					row.setFieldLength(did.getFieldLength());
					row.setFieldName(did.getFieldName());
					row.setFieldNode(did.getFieldNode()!=null?(did.getFieldNode().trim()):null);
					row.setFieldPrecision(did.getFieldPrecision());
					row.setIfNull(did.getIfNull());
					row.setIfPk(did.getIfPk());
					row.setDefaultValue(did.getDefaultValue());
					row.setDataType(did.getDataType());
					row.setIfTransient("2");
					row.setFormula("2"); 
					row.setOrderNumber(did.getOrderNumber());
					// ////////////////////
					String sccName = AutoCodeUtils.toCamelCase(did
							.getFieldName());
					
					String inParamStr=AutoCodeUtils.toCapitalizeCamelCase(did
							.getFieldName());
					row.setOutParamStr(sccName);
					row.setInParamStr(inParamStr);
					row.setSccName(sccName);
					
					if ("Integer".equals(row.getDataType())) {
						try {
							SysClassColumns rowNum = (SysClassColumns) ClassUtil
									.HskCloneEasy(row);
							//String sccName_Str=AutoCodeUtils.toCamelCase(rowNum.getFieldName())+"_str";							
							//String inParamStr_str=AutoCodeUtils.toCapitalizeCamelCase(rowNum.getFieldName()) + "_str";
						 
 							rowNum.setOutParamStr(sccName+"_str");
							rowNum.setInParamStr(inParamStr+"_str");
//							rowNum.setSccName(sccName_Str); 
							
							rowNum.setDataType("String");
							rowNum.setIfTransient("1"); 
							list_scc.add(rowNum);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					if ("Date".equals(row.getDataType())) {
						// 开始时间
						try {
							SysClassColumns rowstart = (SysClassColumns) ClassUtil
									.HskCloneEasy(row); 
							
							//String inParamStr_str=AutoCodeUtils.toCapitalizeCamelCase(rowstart.getFieldName()) + "_start";
							//String sccName_Str = AutoCodeUtils.toCamelCase(rowstart.getFieldName() )+ "_start";
//							rowstart.setOutParamStr(sccName_Str);
							rowstart.setOutParamStr(sccName+"_start");
							rowstart.setInParamStr(inParamStr+ "_start");
//							rowstart.setSccName(sccName_Str); 
							 
							rowstart.setIfTransient("1");
							list_scc.add(rowstart);
						} catch (Exception e) {
							e.printStackTrace();
						}
						// 结束时间
						try {
							SysClassColumns rowend = (SysClassColumns) ClassUtil
									.HskCloneEasy(row);
							 
						//	String inParamStr_str=AutoCodeUtils.toCapitalizeCamelCase(rowend.getFieldName()) + "_end";
						//	String sccName_Str = AutoCodeUtils.toCamelCase(rowend.getFieldName() )+ "_end";
//							rowend.setOutParamStr(sccName_Str);
							rowend.setOutParamStr(sccName+"_end");
							rowend.setInParamStr(inParamStr+ "_end");
//							rowend.setSccName(sccName_Str);  
							
							rowend.setIfTransient("1"); 
							list_scc.add(rowend);
						} catch (Exception e) {
							e.printStackTrace();
						}

					} 
					list_scc.add(row);
				}
			}
			
			SysClassColumns rowlike = new SysClassColumns();
		    rowlike.setSccName("tab_like");
		    rowlike.setInParamStr("Tab_like");
		    rowlike.setOutParamStr("Tab_like");
			rowlike.setDataType("String");
			rowlike.setFormula("2");
			rowlike.setIfTransient("1"); 
			list_scc.add(rowlike);
			
			
			SysClassColumns roworder = new SysClassColumns();
			roworder.setSccName( "tab_order");
			roworder.setInParamStr("Tab_order");
			roworder.setOutParamStr("Tab_order");
			roworder.setDataType("String");
			roworder.setFormula("2");
			roworder.setIfTransient("1"); 
			list_scc.add(roworder);
			 int  nums=0;
			for(SysClassColumns did:list_scc){
//				did.setOrderNumber(nums++);
				did.setSccCode(CreateCodeUtil.getSysClassColumnsNo());
			}
			
		} catch (HSKDBException e) {
			e.printStackTrace();
		}
		return list_scc;
	}

 
	public SysClassColumns getClassColumns(SysClassColumns sccs)
			throws HSKException {
		 if(sccs!=null&&sccs.getSccId()!=null&&!"".equals(sccs.getSccId())){			 
			 return sccs;
		 }else {
			 sccs.setSccCode(CreateCodeUtil.getSysClassColumnsNo());			
		 }
		return sccs;
	}

	 
	public SysRetrunMessage saveOrUpdateClassColumns(SysClassColumns sccs)
			throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		srm.setObj(sccs);
		if(sccs.getSciId()==null  ){
		 srm.setCode(0l);
		 srm.setMeg("类id为空");
		 return srm;
		}
		try {
		if(sccs.getSccId()!=null){		
				this.updateObject(sccs);			
		}else {
			this.newObject(sccs);
		}
		} catch (Exception e) {
			 srm.setCode(0l);
			 srm.setMeg(e.getMessage());
			e.printStackTrace();
		} 
		return srm;
	}

	@Override
	public SysRetrunMessage delListsysClassColumns(
			List<SysClassColumns> list_sci) throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		srm.setObj(list_sci); 
		try {
			if(list_sci!=null&&list_sci.size()>0){
				for(SysClassColumns sccs:list_sci){
					if(sccs.getSccId()!=null){					 
						this.deleteObjects(sccs);
					}
				}
			}
		} catch (Exception e) {
			 srm.setCode(0l);
			 srm.setMeg(e.getMessage());
			e.printStackTrace();
		} 
		return srm;
	}

	@Override
	public void CreatePojoClass(SysClassInfo classInfo,
			List<SysClassColumns> listClassColumns,String  Pack_Project) throws HSKException {
		if(listClassColumns==null){
			 return  ;
		}
		
		Map<String, Object> root=new HashMap<String,Object>();
		String FtlPath ;
		String FtlName="pojo.ftl";
		String fileName=null;
		String projectPath = null;
		String savePath;
		
		//根据数据库类型进行区分
		String  dbType=classInfo.getDbType();
		
		dbType=(dbType==null||"".equals( dbType))?"mysql":dbType;
//		if("mysql".equals( dbType)){
//			//获取ftl文件地址
//			//获取包名
//			//获取类名
//			//拼装map对象
//			 
//		}else if("oracle".equals(dbType)){
			//获取ftl文件地址			 
			  FtlPath=SystemContext.xml_File_path+ classInfo.getFtlpath(); 
			//获取类名
			  fileName=classInfo.getSClassName()+".java";			  
			  projectPath=SystemContext.updown_File_path+classInfo.getProjectpath(); 
			  String propath=(Pack_Project==null?classInfo.getSClassCode():classInfo.getSysCode());
			  savePath =SystemContext.updown_File_path+classInfo.getProjectpath()+propath+classInfo.getSavepath();
			  if (!new File(savePath).exists()) {
					new File(savePath).mkdirs();
				}
			//拼装map对象
			root.put("date", new Date());
			root.put("module", classInfo);
			//生产class文件
			root.put("listCol", listClassColumns);
			try {
				this.getClassForMap(root, savePath, fileName, FtlPath, FtlName);
			} catch (Exception e) { 
				e.printStackTrace();
				Pack_Project="1";
			}		
//		}  
		//打包zip文件 
		if(Pack_Project==null){
			this.newZipfile(projectPath, fileName, classInfo);
		}
	}
	
	private void  newZipfile(String projectPath,String fileName, SysClassInfo classInfo){
		
		String pro=projectPath+classInfo.getSClassCode();
		if(projectPath!=null&&fileName!=null){ 
			ZipUtil.zipFiles(pro, pro+".zip");
		}
		//修改SysClassInfo对象
		if(classInfo.getSciId()!=null){
			 
			 try { 
					classInfo.setZipName(classInfo.getSClassCode()+".zip");
					classInfo.setZipFilepath(pro+".zip");
				StringBuffer att_requrl=this.getRequest().getRequestURL();
				String att_urls=att_requrl.toString();
				att_urls=att_urls.substring(0,att_urls.indexOf("/doService.htm")); 
			//	"/fileInfo/updown\"codeauto\wms
				classInfo.setZipRootpth(att_urls+"/fileInfo/updown"+classInfo.getProjectpath()+classInfo.getSClassCode()+".zip"); 
					this.updateObject(classInfo);
				} catch (Exception e) { 
					e.printStackTrace();
				} 
		}
		
	}
	/**
	 *  通过ftl生成文件 
	 * @param root  配置参数
	 * @param savePath  存储路径
	 * @param fileName  存储文件名
	 * @param FtlPath  ftl文件路径
	 * @param FtlName  ftl文件名称 
	 * @throws Exception  异常 
	 */
	private void  getClassForMap( Map<String, Object> root, String savePath,String fileName,String FtlPath ,String FtlName) throws Exception{
		    FreeMarkerUtil fmu = new FreeMarkerUtil();
 
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(FtlPath));
			Template template = cfg.getTemplate(FtlName,
					SystemContext.Default_Encoding); 
			root.put("date", new Date());
			// 5 生成pojo文件
			fmu.buildTemplateByPath(  root,  savePath,   fileName,   template); 
	}

 
	public void CreateDaoClass(SysClassInfo classInfo,
			List<SysClassColumns> listClassColumns, String Pack_Project)
			throws HSKException {
		if(listClassColumns==null){			 
			 return  ;
		}
		Map<String, Object> root=new HashMap<String,Object>();
		String FtlPath ;
		String FtlName="dao.ftl";
		String FtlName2="idao.ftl";
		String fileName=null;
		String fileName2=null;
		String projectPath = null;
		String savePath;
		String savePath2=null; 
		//根据数据库类型进行区分
		String  dbType=classInfo.getDbType(); 
		dbType=(dbType==null||"".equals( dbType))?"mysql":dbType;
//		if("mysql".equals( dbType)){
//			//获取ftl文件地址
//			//获取包名
//			//获取类名
//			//拼装map对象
//			 
//		}else if("oracle".equals(dbType)){
			      FtlPath=SystemContext.xml_File_path+ classInfo.getFtlpath(); 
				  //获取类名
				  fileName=classInfo.getSClassName()+".java"; 
				  fileName2=classInfo.getSClassName2()+".java"; 
				  FtlName=classInfo.getFtlname();
				  FtlName2=classInfo.getFtlname2(); 
				  projectPath=SystemContext.updown_File_path+classInfo.getProjectpath(); 
				  String propath=(Pack_Project==null?classInfo.getSClassCode():classInfo.getSysCode());
				  savePath =SystemContext.updown_File_path+classInfo.getProjectpath()+propath+classInfo.getSavepath();
				  savePath2 =SystemContext.updown_File_path+classInfo.getProjectpath()+propath+classInfo.getSavepath2();
				  
				  if (!new File(savePath).exists()) {
						new File(savePath).mkdirs();
					}
			//拼装map对象
			root.put("date", new Date());
			root.put("module", classInfo);
			root.put("listCol", listClassColumns);
			//生产class文件
			try {
				this.getClassForMap(root, savePath2, fileName2, FtlPath, FtlName2);
				this.getClassForMap(root, savePath, fileName, FtlPath, FtlName); 
			} catch (Exception e) { 
				e.printStackTrace();
				Pack_Project="1";
			} 
//		}  
		//打包zip文件  	
			if(Pack_Project==null){
				this.newZipfile(projectPath, fileName, classInfo);
			} 
	}
	
	public void CreateControlerClass(SysClassInfo classInfo,  String Pack_Project)
			throws HSKException {
		Map<String, Object> root=new HashMap<String,Object>();
		String FtlPath ;
		String FtlName="controller.ftl"; 
		String fileName=null;
		String fileName2=null;
		String projectPath = null;
		String savePath;
		String savePath2=null; 
		//根据数据库类型进行区分
		String  dbType=classInfo.getDbType(); 
		dbType=(dbType==null||"".equals( dbType))?"mysql":dbType;
		FtlPath=SystemContext.xml_File_path+ classInfo.getFtlpath();
//		if("mysql".equals( dbType)){
//			//获取ftl文件地址
//			//获取包名
//			//获取类名
//			//拼装map对象
//			 
//		}else if("oracle".equals(dbType)){
				  //获取类名
				  fileName=classInfo.getSClassName()+".java"; 
				  fileName2=classInfo.getSClassName2()+".java"; 
				  FtlName=classInfo.getFtlname(); 
				  
				  projectPath=SystemContext.updown_File_path+classInfo.getProjectpath(); 
				  
				  String propath=(Pack_Project==null?classInfo.getSClassCode():classInfo.getSysCode());
				  savePath =SystemContext.updown_File_path+classInfo.getProjectpath()+propath+classInfo.getSavepath();
				  savePath2 =SystemContext.updown_File_path+classInfo.getProjectpath()+propath+classInfo.getSavepath2();
				  
				  if (!new File(savePath).exists()) {
						new File(savePath).mkdirs();
					}
				 
			//拼装map对象
			root.put("date", new Date());
			root.put("module", classInfo);
			 
			//生产class文件
			try { 
			  this.getClassForMap(root, savePath, fileName, FtlPath, FtlName); 
			} catch (Exception e) { 
				e.printStackTrace();
				Pack_Project="1";
			} 
			
//		}  
		//打包zip文件  	
			if(Pack_Project==null){
				this.newZipfile(projectPath, fileName, classInfo);
			} 
		
	}
	
	public void CreateServiceClass(SysClassInfo classInfo,  String Pack_Project)
			throws HSKException {
		 
		Map<String, Object> root=new HashMap<String,Object>();
		String FtlPath ;
		String FtlName="service.ftl";
		String FtlName2="iservice.ftl";
		String fileName=null;
		String fileName2=null;
		String projectPath = null;
		String savePath;
		String savePath2=null; 
		//根据数据库类型进行区分
		String  dbType=classInfo.getDbType(); 
		dbType=(dbType==null||"".equals( dbType))?"mysql":dbType;
//		if("mysql".equals( dbType)){
//			//获取ftl文件地址
//			//获取包名
//			//获取类名
//			//拼装map对象
//			 
//		}else if("oracle".equals(dbType)){
			
			   FtlPath=SystemContext.xml_File_path+ classInfo.getFtlpath(); 
				  //获取类名
				  fileName=classInfo.getSClassName()+".java"; 
				  fileName2=classInfo.getSClassName2()+".java"; 
				  FtlName=classInfo.getFtlname();
				  FtlName2=classInfo.getFtlname2(); 
				  
				  projectPath=SystemContext.updown_File_path+classInfo.getProjectpath(); 
				  
				  String propath=(Pack_Project==null?classInfo.getSClassCode():classInfo.getSysCode());
				  savePath =SystemContext.updown_File_path+classInfo.getProjectpath()+propath+classInfo.getSavepath();
				  savePath2 =SystemContext.updown_File_path+classInfo.getProjectpath()+propath+classInfo.getSavepath2();
				  
				  if (!new File(savePath).exists()) {
						new File(savePath).mkdirs();
					}
				 
			//拼装map对象
			root.put("date", new Date());
			root.put("module", classInfo);
			 
			//生产class文件
			try {
				this.getClassForMap(root, savePath2, fileName2, FtlPath, FtlName2);
			  this.getClassForMap(root, savePath, fileName, FtlPath, FtlName); 
			} catch (Exception e) { 
				e.printStackTrace();
				Pack_Project="1";
			} 
			
//		}  
		//打包zip文件  	
			if(Pack_Project==null){
				this.newZipfile(projectPath, fileName, classInfo);
			} 
	}
	

	public SysRetrunMessage createClassListFile(List<SysClassInfo> listsci,String runtype){
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		String projectPath=null;
		String sysCode=null;
		int nums=1;
		if(listsci!=null&&listsci.size()>0){
			for(SysClassInfo sci:listsci){
				SysClassInfo classInfo=new SysClassInfo() ;
				classInfo.setSciId(sci.getSciId());
				Object obj=this.getOne(classInfo);
				
				 if(obj==null){
					 srm.setCode(0l);
					 srm.setMeg("未找到相应的类记录");
					 continue;
				 }
				 classInfo=(SysClassInfo) obj;
				 String sct=  classInfo.getSClassType();
				sct=(sct==null)?"pojo":sct; 
				List<SysClassColumns> listClassColumns=null;
				SysClassColumns  scc=new SysClassColumns();
				scc.setSciId(classInfo.getSciId());
				try {
					listClassColumns=this.getList(scc);
				} catch (HSKDBException e) {			 
					e.printStackTrace();
				}
			 
				projectPath=classInfo.getProjectpath();
				sysCode=classInfo.getSysCode();
				if(nums==1){
					String filePath=SystemContext.updown_File_path+projectPath+sysCode;
					FreeMarkerUtil.deleteAllFileDir( filePath);
					nums=2;
				}
				try {
					if("pojo".equals(sct)){
							 this.CreatePojoClass(classInfo, listClassColumns, "1"); 
					}else if("dao".equals(sct)){
							this.CreateDaoClass(classInfo, listClassColumns, "1");
					}else if("service".equals(sct)){
						this.CreateServiceClass(classInfo, "1");
					}
				} catch (HSKException e) { 
					e.printStackTrace();
				}
			}
			//打包项目文件夹
			if(projectPath!=null&&sysCode!=null){ 
				String filePath=SystemContext.updown_File_path+projectPath+sysCode;
				
				ZipUtil.zipFiles(filePath, filePath+".zip");
				if(runtype==null){
				StringBuffer att_requrl=this.getRequest().getRequestURL();
				String att_urls=att_requrl.toString();
				att_urls=att_urls.substring(0,att_urls.indexOf("/doService.htm")); 
			//	"/fileInfo/updown\"codeauto\wms
				att_urls= att_urls+"/fileInfo/updown"+projectPath+sysCode+".zip"; 
				
				srm.setObj(att_urls);
				}
			}
		} 
		return srm;		
	}
	
	
	public SysRetrunMessage createClassFile(SysClassInfo sci)
			throws HSKException {
		SysRetrunMessage srm=new SysRetrunMessage(1l);
		
		SysClassInfo classInfo=new SysClassInfo() ;
		classInfo.setSciId(sci.getSciId());
		Object obj=this.getOne(classInfo);
		
		 if(obj==null){
			 srm.setCode(0l);
			 srm.setMeg("未找到相应的类记录");
			 return srm;
		 }
		 classInfo=(SysClassInfo) obj;
		 String sct=  classInfo.getSClassType();
			sct=(sct==null)?"pojo":sct;
		 
		
		List<SysClassColumns> listClassColumns=null;
		SysClassColumns  scc=new SysClassColumns();
		scc.setSciId(classInfo.getSciId());
		try {
			listClassColumns=this.getList(scc);
		} catch (HSKDBException e) {			 
			e.printStackTrace();
		}
	
		String filePath=SystemContext.updown_File_path+classInfo.getProjectpath()+classInfo.getSClassCode();
		FreeMarkerUtil.deleteAllFileDir( filePath);
		if("pojo".equals(sct)){
				this.CreatePojoClass(classInfo, listClassColumns, null);
		}else if("dao".equals(sct)){
				this.CreateDaoClass(classInfo, listClassColumns, null);
		}else if("service".equals(sct)){
			this.CreateServiceClass(classInfo, null);
			
		}else if("controller".equals(sct)){
			this.CreateControlerClass(classInfo, null);
			
		}
		
		return srm;
	}

}
