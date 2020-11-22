package com.hsk.xframe.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * MxxzWebsiteInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_website_info" )
public class SysWebsiteInfo implements java.io.Serializable {

	// Fields

	private Integer swiId;
	private String websitName;
	private String websitComment;
	private String rootPath;
	private String state;
	private String websitIconFilecode;
	private String templateDirectory;
	private String indexPath;
	
	private String websitecode;
	private String backgroundPictureFileCode01;
	private String backgroundPictureFileCode02;
	private String backgroundPictureFileCode03;
	private String versionInformation;
	

	// Constructors

	/** default constructor */
	public SysWebsiteInfo() {
	}

	/** minimal constructor */
	public SysWebsiteInfo(Integer swiId) {
		this.swiId = swiId;
	}

	/** full constructor */
	public SysWebsiteInfo(Integer swiId, String websitName,
			String websitComment, String rootPath, String state,
			String websitIconFilecode, String templateDirectory,
			String indexPath) {
		this.swiId = swiId;
		this.websitName = websitName;
		this.websitComment = websitComment;
		this.rootPath = rootPath;
		this.state = state;
		this.websitIconFilecode = websitIconFilecode;
		this.templateDirectory = templateDirectory;
		this.indexPath = indexPath;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "swi_id", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getSwiId() {
		return this.swiId;
	}

	public void setSwiId(Integer swiId) {
		this.swiId = swiId;
	}

	@Column(name = "websit_name", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getWebsitName() {
		return this.websitName;
	}

	public void setWebsitName(String websitName) {
		this.websitName = websitName;
	}

	@Column(name = "websit_comment", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	public String getWebsitComment() {
		return this.websitComment;
	}

	public void setWebsitComment(String websitComment) {
		this.websitComment = websitComment;
	}

	@Column(name = "root_path", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public String getRootPath() {
		return this.rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	@Column(name = "state", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "websit_icon_filecode", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getWebsitIconFilecode() {
		return this.websitIconFilecode;
	}

	public void setWebsitIconFilecode(String websitIconFilecode) {
		this.websitIconFilecode = websitIconFilecode;
	}

	@Column(name = "Template_directory", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public String getTemplateDirectory() {
		return this.templateDirectory;
	}

	public void setTemplateDirectory(String templateDirectory) {
		this.templateDirectory = templateDirectory;
	}

	@Column(name = "index_path", unique = false, nullable = true, insertable = true, updatable = true, length = 128)
	public String getIndexPath() {
		return this.indexPath;
	}

	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}
    
	
	@Column(name = "websiteCode", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getWebsitecode() {
		return websitecode;
	}

	public void setWebsitecode(String websitecode) {
		this.websitecode = websitecode;
	}
     
	@Column(name = "Background_picture_fileCode01", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getBackgroundPictureFileCode01() {
		return backgroundPictureFileCode01;
	}

	public void setBackgroundPictureFileCode01(String backgroundPictureFileCode01) {
		this.backgroundPictureFileCode01 = backgroundPictureFileCode01;
	}
	@Column(name = "Background_picture_fileCode02", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getBackgroundPictureFileCode02() {
		return backgroundPictureFileCode02;
	}

	public void setBackgroundPictureFileCode02(String backgroundPictureFileCode02) {
		this.backgroundPictureFileCode02 = backgroundPictureFileCode02;
	}
	@Column(name = "Background_picture_fileCode03", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getBackgroundPictureFileCode03() {
		return backgroundPictureFileCode03;
	}

	public void setBackgroundPictureFileCode03(String backgroundPictureFileCode03) {
		this.backgroundPictureFileCode03 = backgroundPictureFileCode03;
	}
	@Column(name = "version_information", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getVersionInformation() {
		return versionInformation;
	}

	public void setVersionInformation(String versionInformation) {
		this.versionInformation = versionInformation;
	}
	/**
	 * yanglei 2019-12-05
     * ():字段类型为String
     * md_order_info表里不用like作为条件的字符串
     */
    private String Tab_like;
	 /**
     * 设置tab_like字段方法
     *
     * @return 返回 <b>MdOrderInfo.tab_like</b>的值
     */
    @Transient
    public String getTab_like() {
        return this.Tab_like;
    }

    /**
     * 设置 字段方法
     *
     * @param att_tab_like 输入<b>MdOrderInfo.tab_like</b>字段的值
     */
    public void setTab_like(String att_Tab_like) {
        this.Tab_like = att_Tab_like;
    }
    /**
     * 设置tab_order字段方法
     *
     * @return 返回 <b>MdOrderInfo.tab_order</b>的值
     */
    /**
     * ():字段类型为String
     * md_order_info表里order by 字符串
     */
    private String Tab_order;
    @Transient
    public String getTab_order() {
        return this.Tab_order;
    }

    /**
     * 设置 字段方法
     *
     * @param att_tab_order 输入<b>MdOrderInfo.tab_order</b>字段的值
     */
    public void setTab_order(String att_Tab_order) {
        this.Tab_order = att_Tab_order;
    }

}