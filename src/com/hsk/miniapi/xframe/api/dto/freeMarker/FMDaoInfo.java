package com.hsk.miniapi.xframe.api.dto.freeMarker;

import java.util.List;

import com.hsk.xframe.api.dto.freeMarker.CodeConfig;
import com.hsk.xframe.api.dto.freeMarker.FMFieldInfo;
import com.hsk.xframe.api.utils.freeMarker.AutoCodeUtils;


/**
 * Dao类业务信息对象
 * 
 * @author xun
 * @version v1.0
 * 
 */
public class FMDaoInfo {
	/**
	 * Dao类业务信息对象初始化
	 */
	public FMDaoInfo() {
	}

	/**
	 * Dao类业务信息对象初始化
	 * 
	 * @param fmp
	 */

	public FMDaoInfo(String ls_savePath, String ls_pojoPath, String tableName,
			String Authror, String Version,String CommentText, List<FMFieldInfo> listPmf) {
		this.commentText=CommentText;
		this.daoPath = ls_savePath;
		this.pojoPath = ls_pojoPath;
		this.setAuthorText(Authror);
		this.setVersionText(Version);
		this.setTableName(tableName);
		this.setPojoPackage(AutoCodeUtils.FilePathToPackage(ls_pojoPath));
		// 设置 项目根文件路径
		this.setProjectPath(System.getProperty("user.dir") + "//");
		this.setDaoInterfaceClassName("I" + AutoCodeUtils.toCamelCase(tableName)
				+ "Dao");
		this.setDaoClassName(AutoCodeUtils.toCapitalizeCamelCase(tableName) + "Dao");
		// 设置Dao包名称
		this.setDaoPackage(AutoCodeUtils.FilePathToPackage(ls_savePath) + ".imp");
		this.setDaoInterfacePackage(AutoCodeUtils.FilePathToPackage(ls_savePath));
		if (this.daoPackage == null) {
			this.daoPackage = CodeConfig.DefaultDaoPackageName + ".imp";
		}
		if (this.daoInterfacePackage == null) {
			this.daoInterfacePackage = CodeConfig.DefaultDaoPackageName;
		}
		// 设置Dao接口模板ftl文件根路径
		this.setFtlPath(this.getProjectPath() + "config//ftl//");
		// 设置dao接口模板文件
		this.ftlDaoName = "dao.ftl";
		// 设置dao业务实现模板文件
		this.ftlInterfaceDaoName = "interfacedao.ftl";
		/**
		 * pojo持久化对象类名
		 */
		this.pojoClassName=AutoCodeUtils.toCamelCase(this.getTableName());
		/**
		 * 首字符大写持久化对象类名
		 */
		this.caseClassName=AutoCodeUtils.toCapitalizeCamelCase(this.getTableName());
		// 获取pk字段
		for (FMFieldInfo did : listPmf) {
			if ("PRI".equals(did.getColumnKey())) {
				this.setPkField(did);
			}
		}
	}
/**
 * 表说明
 */
	private String commentText;
	
	/**
	 * 持久化对象首字符大写
	 */
	private String caseClassName;
	/**
	 * dao业务处理包路径
	 */
	private String daoPath;
	/**
	 * pojo文件存放路径
	 */
	private String pojoPath;

	/**
	 * 项目根路径
	 */
	private String projectPath;
	/**
	 * 版本信息
	 */
	private String versionText;
	/**
	 * 作者说明
	 */
	private String authorText;
	/**
	 * pk字段对象
	 */
	private FMFieldInfo pkField;
	/**
	 * pojo包名称
	 */
	private String pojoPackage;
	/**
	 * 表名称
	 */
	private String tableName;
	/**
	 * 持久化对象名称
	 */
	private String pojoClassName;
	/**
	 * dao接口包名称
	 */
	private String daoInterfacePackage;
	/**
	 * dao包名称
	 */
	private String daoPackage;
	/**
	 * dao类名称
	 */
	private String daoClassName;
	/**
	 * dao接口类名称
	 */
	private String daoInterfaceClassName;

	/**
	 * ftl模板文件夹目录
	 */
	private String ftlPath;

	/**
	 * dao的模板文件名称
	 */
	private String ftlDaoName;
	/**
	 * dao接口模板文件名称
	 */
	private String ftlInterfaceDaoName;

	public FMFieldInfo getPkField() {
		return pkField;
	}

	public void setPkField(FMFieldInfo pkField) {
		this.pkField = pkField;
	}

	public String getPojoPackage() {
		return pojoPackage;
	}

	public void setPojoPackage(String pojoPackage) {
		this.pojoPackage = pojoPackage;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPojoClassName() {
		return pojoClassName;
	}

	public void setPojoClassName(String pojoClassName) {
		this.pojoClassName = pojoClassName;
	}

	public String getDaoInterfacePackage() {
		return daoInterfacePackage;
	}

	public void setDaoInterfacePackage(String daoInterfacePackage) {
		this.daoInterfacePackage = daoInterfacePackage;
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getDaoClassName() {
		return daoClassName;
	}

	public void setDaoClassName(String daoClassName) {
		this.daoClassName = daoClassName;
	}

	public String getDaoInterfaceClassName() {
		return daoInterfaceClassName;
	}

	public void setDaoInterfaceClassName(String daoInterfaceClassName) {
		this.daoInterfaceClassName = daoInterfaceClassName;
	}

	public String getFtlPath() {
		return ftlPath;
	}

	public void setFtlPath(String ftlPath) {
		this.ftlPath = ftlPath;
	}

	public String getFtlDaoName() {
		return ftlDaoName;
	}

	public void setFtlDaoName(String ftlDaoName) {
		this.ftlDaoName = ftlDaoName;
	}

	public String getFtlInterfaceDaoName() {
		return ftlInterfaceDaoName;
	}

	public void setFtlInterfaceDaoName(String ftlInterfaceDaoName) {
		this.ftlInterfaceDaoName = ftlInterfaceDaoName;
	}

	public String getVersionText() {
		return versionText;
	}

	public void setVersionText(String versionText) {
		this.versionText = versionText;
	}

	public String getAuthorText() {
		return authorText;
	}

	public void setAuthorText(String authorText) {
		this.authorText = authorText;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getDaoPath() {
		return daoPath;
	}

	public void setDaoPath(String daoPath) {
		this.daoPath = daoPath;
	}

	public String getPojoPath() {
		return pojoPath;
	}

	public void setPojoPath(String pojoPath) {
		this.pojoPath = pojoPath;
	}

	public String getCaseClassName() {		
		return caseClassName;
	}

	public void setCaseClassName(String caseClassName) {
		this.caseClassName = caseClassName;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

}
