package com.hsk.xframe.web.window;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.dto.freeMarker.SysCodeInfo;
import com.hsk.xframe.api.persistence.SysClassColumns;
import com.hsk.xframe.api.persistence.SysClassInfo;
import com.hsk.xframe.api.persistence.SysTableInfo;
import com.hsk.xframe.api.utils.freeMarker.AutoCodeUtils;
import com.hsk.xframe.web.framework.service.ICodeAutoService;
import com.hsk.xframe.web.sysTableInfo.Service.ITableInfoService;

public class RunSrc {
	static String file_Path = "";
	static ApplicationContext ctx;
	static String sysCode = "SYS1702081104765";
	static String dbName = "iwms";
	static String axt="admin";
	static  String vis="v1.0";

	public static void main(String[] args) {  
		
	 
		
		String relativelyPath = System.getProperty("user.dir")
				+ "\\config\\spring\\";
		String[] locations = { relativelyPath + "applicationContext.xml",
				relativelyPath + "applicationContextservlet.xml",
				relativelyPath + "ContextEhCache.xml" };
		System.out.println(relativelyPath);
		RunSrc.ctx = new FileSystemXmlApplicationContext(locations);
		SystemContext.DB_Type = "oracle";
		SystemContext.updown_File_path="D:\\01_2017_tools\\01java\\code";
		
		SystemContext.xml_File_path=System.getProperty("user.dir")+"\\config\\";
		try {
			RunSrc.createListTableInfo();
			System.out
					.println("=================="+SystemContext.updown_File_path+"==End===========================");
		} catch (HSKException e) {
			e.printStackTrace();
		}

	}

	public static void createListTableInfo() throws HSKException {
//		String[] tableName = new String[] {  "WMS_MOVE_DOC","WMS_MOVE_DOC_mx","WMS_INVENTORY_EXTEND","WMS_INVENTORY"
//		,"wms_Production_plans","wms_Production_plans_Mx","wms_Production_technology",
//		"wms_Out_Warehouse_mx" ,"wms_Out_Warehouse",
//		"WMS_TASK","WMS_TASK_mx",
//		"wms_definition"
//		};
//		 
//		String[] pkName = new String[] { "wmd_id","wmdm_id","wie_id","wi_id"
//		,"wpp_id","wpp_mx_id","wpt_id",
//		"wow_mx_id","wow_id" ,
//		 "wt_id" ,"wtm_id",
//		"md_id"
//				};
//		String[] serviceName = new String[] {"WmsMoveDoc","WmsMoveDoc","WmsInventory","WmsInventory"
//				,"wmsProduction","wmsProduction","wmsProduction" ,
//				"WmsOutWarehouse","WmsOutWarehouse",
//				"WmsTask","WmsTask",
//		"wmsDefinition"
//					};
	 

		String[] tableName = new String[] {"wms_check_info" }; 
		String[] pkName = new String[] { "wch_id" };
		String[] serviceName = new String[] {"WmsCheck"};
		
		
		 
		List<SysClassInfo> listsci=new ArrayList<SysClassInfo>();
		for (int i = 0; i < tableName.length; i++) {
			RunSrc.createTableInfo(tableName[i].toUpperCase(), pkName[i].toUpperCase(),serviceName[i],listsci);
		}
		ICodeAutoService tableSer = (ICodeAutoService) RunSrc.ctx
				.getBean("codeAutoService");
		if(listsci!=null&&listsci.size()>0){
			tableSer.createClassListFile(listsci,"1");
		}

	}

	public static  void  createTableInfo(String tableName, String pkName,String serviceName,List<SysClassInfo> listsci)
			throws HSKException {
		ITableInfoService tableSer = (ITableInfoService) RunSrc.ctx
				.getBean("tableInfoService");
		SysTableInfo stable = new SysTableInfo();
		stable.setSysCode(RunSrc.sysCode);
		SysTableInfo sti = tableSer.questTable(stable);
		sti.setTableName(tableName);
		sti.setPkName(pkName);
		sti.setDbName(RunSrc.dbName);
		tableSer.saveTable(sti);  
		 
	    listsci.add(RunSrc.createPojo(sti));
		 listsci.add(RunSrc.createDao(sti));
		// listsci.add(RunSrc.createServiceObj(sti,serviceName));
		 
	}

	public static SysClassInfo createPojo(SysTableInfo stable) throws HSKException {
		SysClassInfo att_sci = new SysClassInfo();
		att_sci.setSysCode(RunSrc.sysCode);
		att_sci.setSClassType("pojo");
		ICodeAutoService tableSer = (ICodeAutoService) RunSrc.ctx
				.getBean("codeAutoService");
		SysCodeInfo att_code = new SysCodeInfo();
		SysClassInfo sci_init = tableSer.getsysClass(att_sci);
		att_code.setSysClassInfo(sci_init);

		List<SysClassColumns> list_sysClassColumns = new ArrayList<SysClassColumns>(); 
		sci_init.setAuthortext(RunSrc.axt);
		sci_init.setVersiontext(RunSrc.vis);
		sci_init.setTableCode(stable.getTableCode());
		sci_init.setTableName(stable.getTableName());
		sci_init.setTableNode(stable.getTableNode());
		sci_init.setSClassComment(stable.getTableNode());
		sci_init.setSClassName(AutoCodeUtils.toCapitalizeCamelCase(stable
				.getTableName()));
		sci_init.setDbType(stable.getDbType());
		sci_init.setSeqstr(stable.getSeqstr());
		sci_init.setPkName(stable.getPkName());		
		SysRetrunMessage  srm=tableSer.initClassColumns(sci_init);
		if("1".equals( srm.getCode().toString())){
			 list_sysClassColumns=	(List<SysClassColumns>) srm.getObj();
			 att_code.setList_sysClassColumns(list_sysClassColumns);
			tableSer.saveOrUpdatesysClass(att_code);
			
		}
		return sci_init;
	}
	
	public static SysClassInfo createDao(SysTableInfo stable  ) throws HSKException {
		ICodeAutoService tableSer = (ICodeAutoService) RunSrc.ctx
				.getBean("codeAutoService");
		SysClassInfo att_sci = new SysClassInfo();
		att_sci.setSysCode(RunSrc.sysCode);
		att_sci.setSClassType("dao"); 
		
		SysCodeInfo att_code = new SysCodeInfo();
		SysClassInfo sci_init = tableSer.getsysClass(att_sci);
		att_code.setSysClassInfo(sci_init);
		List<SysClassColumns> list_sysClassColumns = new ArrayList<SysClassColumns>(); 
 
		sci_init.setAuthortext(RunSrc.axt);
		sci_init.setVersiontext(RunSrc.vis);
		sci_init.setTableCode(stable.getTableCode());
		sci_init.setTableName(stable.getTableName());
		sci_init.setTableNode(stable.getTableNode());		
		sci_init.setSClassComment(stable.getTableNode());
		sci_init.setSClassComment2(stable.getTableNode());
		sci_init.setSClassComment3(stable.getTableNode());
		
		sci_init.setSClassName(AutoCodeUtils.toCapitalizeCamelCase(stable
				.getTableName())+"Dao");
		sci_init.setSClassName2("I"+AutoCodeUtils.toCapitalizeCamelCase(stable
				.getTableName())+"Dao");
		
		sci_init.setSClassName3(AutoCodeUtils.toCapitalizeCamelCase(stable
				.getTableName()));	 
		sci_init.setDbType(stable.getDbType());
		sci_init.setSeqstr(stable.getSeqstr());
		sci_init.setPkName(stable.getPkName());	
		
		SysRetrunMessage  srm=tableSer.initClassColumns(sci_init);
		if("1".equals( srm.getCode().toString())){
			 list_sysClassColumns=	(List<SysClassColumns>) srm.getObj();
			 att_code.setList_sysClassColumns(list_sysClassColumns);
			tableSer.saveOrUpdatesysClass(att_code); 
		}
		return sci_init;
	}
	
	public static SysClassInfo createServiceObj(SysTableInfo stable ,String serviceName) throws HSKException {
		ICodeAutoService tableSer = (ICodeAutoService) RunSrc.ctx
				.getBean("codeAutoService");
		SysClassInfo att_sci = new SysClassInfo();
		att_sci.setSysCode(RunSrc.sysCode);
		att_sci.setSClassType("service"); 
		
		SysCodeInfo att_code = new SysCodeInfo();
		SysClassInfo sci_init = tableSer.getsysClass(att_sci);
		att_code.setSysClassInfo(sci_init);
		List<SysClassColumns> list_sysClassColumns = new ArrayList<SysClassColumns>(); 
	 
		  
		sci_init.setAuthortext(RunSrc.axt);
		sci_init.setVersiontext(RunSrc.vis);
		sci_init.setTableCode(stable.getTableCode());
		sci_init.setTableName(stable.getTableName());
		sci_init.setTableNode(stable.getTableNode());
		
		sci_init.setSClassComment(stable.getTableNode());
		sci_init.setSClassComment2(stable.getTableNode());
		sci_init.setSClassComment3(stable.getTableNode());
		sci_init.setSClassComment4(stable.getTableNode()+"Dao接口");
		
		
		sci_init.setSClassName(AutoCodeUtils.toCapitalizeCamelCase(stable
				.getTableName())+"Service");
		sci_init.setSClassName2("I"+AutoCodeUtils.toCapitalizeCamelCase(stable
				.getTableName())+"Service");
		
		sci_init.setSClassName3(AutoCodeUtils.toCapitalizeCamelCase(stable
				.getTableName()));	
		sci_init.setSClassName4("I"+AutoCodeUtils.toCapitalizeCamelCase(stable
				.getTableName())+"Dao");
		
		
		sci_init.setServicename(serviceName);
		
		sci_init.setDbType(stable.getDbType());
		sci_init.setSeqstr(stable.getSeqstr());
		sci_init.setPkName(stable.getPkName());	 
		att_code.setList_sysClassColumns(list_sysClassColumns);
		tableSer.saveOrUpdatesysClass(att_code); 
		return sci_init;
	} 

}
