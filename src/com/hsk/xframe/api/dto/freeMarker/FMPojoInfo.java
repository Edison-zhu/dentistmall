package com.hsk.xframe.api.dto.freeMarker;

import java.util.ArrayList;
import java.util.List;
import com.hsk.xframe.api.utils.freeMarker.AutoCodeUtils;

 
 

public class FMPojoInfo {

	public FMPojoInfo() {
	}

	/**
	 * 初始化FMpojoInfo 对象
	 * 
	 * @param savePath
	 *            pojo需要存储的目录
	 * @param TableName
	 *            表名称
	 * @param authorText
	 *            作者说明
	 * @param versionText
	 *            版本说明
	 * @param commentText
	 *            表备注
	 * @param listPmf
	 *            字段列表
	 */
	public FMPojoInfo(String savePath, String TableName, String authorText,
			String versionText, String commentText, List<FMFieldInfo> listPmf) {
		this.init(savePath, TableName, authorText, versionText, commentText,
				listPmf);
	}

	public void init(String savePath, String TableName, String authorText,
			String versionText, String commentText, List<FMFieldInfo> listPmf) {
		this.savePath = savePath;
		this.tableName = TableName;
		this.authorText = authorText;
		this.versionText = versionText;
		this.commentText = commentText;
		this.listPmf = listPmf;
		// 设置ClassName
		this.className = AutoCodeUtils.toCamelCase(TableName);
		// 设置 项目根文件路径
		this.projectPath = System.getProperty("user.dir") + "//";
		// 设置模板ftl文件根路径
		this.ftlPath = this.projectPath + "config//ftl//";
		// 设置ftl文件名名称
		this.ftlName = "pojo.ftl";
		if (savePath != null) {
			this.packageName = AutoCodeUtils.FilePathToPackage(savePath);
			if (this.packageName == null) {
				this.packageName = CodeConfig.DefaultPojoPackageName;
			}
		} else {
			savePath = CodeConfig.DefaultPojoPath;
			this.packageName = CodeConfig.DefaultPojoPackageName;
		}
	}

	/**
	 * 包地址
	 */
	private String packageName;

	/**
	 * pojo持久化对象的模板ftl文件根路径
	 */
	private String ftlPath;
	/**
	 * ftl文件名名称
	 */
	private String ftlName;

	/**
	 * 项目根文件路径
	 */
	private String projectPath;
	/**
	 * 需要生产的pojo文件的跟路径
	 */
	private String savePath;
	/**
	 * 表名称
	 */
	private String tableName;
	/**
	 * class对象名称
	 */
	private String className;
	/**
	 * 作者
	 */
	private String authorText;

	/**
	 * 版本说明
	 */
	private String versionText;

	/**
	 * 表备注
	 */
	private String commentText;
	
	/**
	 * 主键名
	 */
	private String pkName;
	
	/**
	 * 服务名
	 */
	private String serviceName;

	/**
	 * 该表的字段列表
	 */
	private List<FMFieldInfo> listPmf = new ArrayList<FMFieldInfo>();

	
	
	
	
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getFtlPath() {
		return ftlPath;
	}

	public void setFtlPath(String ftlPath) {
		this.ftlPath = ftlPath;
	}

	public String getFtlName() {
		return ftlName;
	}

	public void setFtlName(String ftlName) {
		this.ftlName = ftlName;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAuthorText() {
		return authorText;
	}

	public void setAuthorText(String authorText) {
		this.authorText = authorText;
	}

	public String getVersionText() {
		return versionText;
	}

	public void setVersionText(String versionText) {
		this.versionText = versionText;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public List<FMFieldInfo> getListPmf() {
		return listPmf;
	}

	public void setListPmf(List<FMFieldInfo> listPmf) {
		this.listPmf = listPmf;
	}
	
	

}
