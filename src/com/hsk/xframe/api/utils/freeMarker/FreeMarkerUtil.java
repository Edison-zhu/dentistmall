package com.hsk.xframe.api.utils.freeMarker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.xframe.api.dto.freeMarker.FMDaoInfo;
import com.hsk.xframe.api.dto.freeMarker.FMPojoInfo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 生成pojo对象class类
 * 
 * @version v1.0
 * @author xun
 * 
 */
public class FreeMarkerUtil {
	/**
	 * 根据模板模型生成持久化对象
	 * 
	 * @param fmpi
	 *            模板對象
	 * @throws Exception
	 *             抛出Exception异常
	 */
	public static void PoJoProcess(FMPojoInfo fmpi) throws HSKException {
		FreeMarkerUtil fmu = new FreeMarkerUtil();

		try {
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(fmpi.getFtlPath()));
			Template template = cfg.getTemplate(fmpi.getFtlName(),
					SystemContext.Default_Encoding);
			// 1初始化参数
			Map<String, Object> root = new HashMap<String, Object>();
			// 2项目根路径
			String projectPath = fmpi.getProjectPath();
			// 3pojo文件名
			String fileName = fmpi.getClassName() + ".java";
			// 4 pojo生成的文件路径
			String savePath = fmpi.getSavePath();
			root.put("module", fmpi);
			root.put("date", new Date());
			// 5 生成pojo文件
			fmu.buildTemplate(root, projectPath, savePath, fileName, template);
		} catch (IOException e) {
			e.printStackTrace();
			throw new HSKException(e);
		} catch (TemplateException e) {
			e.printStackTrace();
			throw new HSKException(e);

		} catch (Exception e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
	}

	/**
	 * 根据模板模型生成Dao业务处理类
	 * 
	 * @param fmpi
	 *            模板對象
	 * @throws Exception
	 *             抛出Exception异常
	 */
	public static void DaoProcess(FMDaoInfo fmpi) throws HSKException {
		FreeMarkerUtil fmu = new FreeMarkerUtil();

		try {
			// 1.1初始化参数
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(fmpi.getFtlPath()));
			Template template = cfg.getTemplate(fmpi.getFtlInterfaceDaoName(),
					SystemContext.Default_Encoding);
			Map<String, Object> root = new HashMap<String, Object>();
			// 1.2项目根路径
			String projectPath = fmpi.getProjectPath();
			// 1.3pojo文件名
			String fileName = fmpi.getDaoInterfaceClassName()+".java";
			// 1.4 Dao接口生成的文件路径
			String savePath =fmpi.getDaoPath();
			root.put("module", fmpi);
			root.put("date", new Date());
			// 1.5 生成Dao接口类文件
			fmu.buildTemplate(root, projectPath, savePath, fileName, template);

			// 2.1初始化参数
			Configuration	cfg1 = new Configuration();
			cfg1.setDirectoryForTemplateLoading(new File(fmpi.getFtlPath()));
			Template 	template1 = cfg.getTemplate(fmpi.getFtlDaoName(),
					SystemContext.Default_Encoding);
			// 2.2Dao业务处理实现类文件名
			fileName = fmpi.getDaoClassName() + ".java"; 
			savePath = fmpi.getDaoPath()+"/imp/";
			root = new HashMap<String, Object>();
			root.put("module", fmpi);
			root.put("date", new Date());
			// 2.3 生成Dao实现类文件
			fmu.buildTemplate(root, projectPath, savePath, fileName, template1);

		} catch (IOException e) {
			e.printStackTrace();
			throw new HSKException(e);
		} catch (TemplateException e) {
			e.printStackTrace();
			throw new HSKException(e);

		} catch (Exception e) {
			e.printStackTrace();
			throw new HSKException(e);
		}
	}

	/**
	 * 执行生成持久化对象文件
	 * 
	 * @param root
	 *            输入参数
	 * @param projectPath
	 *            项目路径
	 * @param savePath
	 *            持久化对象保存地址
	 * @param fileName
	 *            持久化对象
	 * @param template
	 *            模板Template
	 */
	public void buildTemplate(Map<String, Object> root, String projectPath,
			String savePath, String fileName, Template template)
			throws TemplateException, IOException {
		String realFileName = projectPath + savePath + fileName;
		String realSavePath = projectPath + "/" + savePath;
		
		File newsDir = new File(realSavePath);
		if (!newsDir.exists()) {
			newsDir.mkdirs();
		}
		Writer out = new OutputStreamWriter(new FileOutputStream(realFileName),
				SystemContext.Default_Encoding);
		template.process(root, out);
	}
	
 

	public static String transformType(String inputStr) {
		if (inputStr == null) {
			return "String";
		}
		String dbtype = SystemContext.DB_Type;
		String lo_back = null;
		if ("oracle".equals(dbtype)) {
			switch (OracleDataType.getDataType(inputStr)) {
			 
			case oracle_int:
				lo_back = "Integer";
				break;
			case oracle_integer:
				lo_back = "Integer";
				break; 
			case oracle_number:
				lo_back = "Integer";
				break;
			case oracle_varchar:
				lo_back = "String";
				break;
			case oracle_varchar2:
				lo_back = "String";
				break;	
			case oracle_char:
				lo_back = "String";
				break;
			case oracle_double:
				lo_back = "Double";
				break;
			case oracle_timestamp:
				lo_back = "Date";
				break;
			case oracle_date:
				lo_back = "Date";
				break;
			case oracle_blob:
				lo_back = "Blob";
				break;
			case oracle_long:
				lo_back = "Long";
				break;
			}
		}
		if ("mysql".equals(dbtype)) {
			switch (MySqlDataType.getDataType(inputStr)) {
			case mysql_int:
				lo_back = "Integer";
				break;
			case mysql_decimal:
				lo_back = "Double";
				break;
			case mysql_double:
				lo_back = "Double"; 
				break;	 
			case mysql_longtext:
				lo_back = "Integer";
				break;
			case mysql_integer:
				lo_back = "Integer";
				break;
			case mysql_smallint:
				lo_back = "Integer";
				break; 
			case mysql_bigint:
				lo_back = "Integer";
				break; 
			case mysql_blob:
				lo_back = "String";
				break; 
			case mysql_char:
				lo_back = "String";
				break;  
			case mysql_bit:
				lo_back = "String";
				break; 
			case mysql_text:
				lo_back = "String";
				break; 
			case mysql_varchar:
				lo_back = "String";
				break;
			case mysql_datetime:
				lo_back = "Date";
				break;
			case mysql_timestamp:
				lo_back = "Date";
				break;	 
			case mysql_time:
				lo_back = "Date";
				break;
			case mysql_tinyint:
				lo_back="Boolean";
				break;
			}
		}
		
		return lo_back;
	}

	public void buildTemplateByPath(Map<String, Object> root, String savePath,
			String fileName, Template template) throws Exception {
		File newsDir = new File(savePath);
		if (!newsDir.exists()) {
			newsDir.mkdirs();
		}
		String realFileName = savePath + "/" + fileName;
		Writer out = new OutputStreamWriter(new FileOutputStream(realFileName),
				SystemContext.Default_Encoding);
		template.process(root, out);
		out.close(); 
	}

	 
	public static void deleteAllFileDir(String filePath) {
		File dir=new File(filePath);
		FreeMarkerUtil.deleteDir(dir);
		File dirzip=new File(filePath+".zip");
		if (dirzip.exists()) {
			dirzip.delete();
		}
	}
	
	public static boolean deleteDir(File dir) {
	        if (dir.isDirectory()) {
	            String[] children = dir.list();
	            //递归删除目录中的子目录下
	            for (int i=0; i<children.length; i++) {
	                boolean success = deleteDir(new File(dir, children[i]));
	                if (!success) {
	                    return false;
	                }
	            }
	        }
	        // 目录此时为空，可以删除
	        return dir.delete();
	    }

}
