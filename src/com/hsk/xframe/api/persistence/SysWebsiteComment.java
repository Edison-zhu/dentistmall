package com.hsk.xframe.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

/**
 * MxxzWebsitComment entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sys_websit_comment")
public class SysWebsiteComment implements java.io.Serializable {

	// Fields

	public SysWebsiteComment(Integer swmId, Integer swcId, String state,
			String newsTitle, String newsKeyword, String newsBrief,
			String newsComment, String newsIconFileCode,
			String newsIllustratedFileCode, Integer serialNumber,
			String ifRecommend, Date createDatetime, String createRen,
			String newscode, String newPictureFilecode01,
			String newPictureFilecode02, String newPictureFilecode03,
			Date startTime, Date endTime, String organizer, String marker,
			String phone, String linkman, String email, String address,
			String linkUrl,String remark,String projectName) {
		super();
		this.swmId = swmId;
		this.swcId = swcId;
		this.state = state;
		this.newsTitle = newsTitle;
		this.newsKeyword = newsKeyword;
		this.newsBrief = newsBrief;
		this.newsComment = newsComment;
		this.newsIconFileCode = newsIconFileCode;
		this.newsIllustratedFileCode = newsIllustratedFileCode;
		this.serialNumber = serialNumber;
		this.ifRecommend = ifRecommend;
		this.createDatetime = createDatetime;
		this.createRen = createRen;
		this.newscode = newscode;
		this.newPictureFilecode01 = newPictureFilecode01;
		this.newPictureFilecode02 = newPictureFilecode02;
		this.newPictureFilecode03 = newPictureFilecode03;
		this.startTime = startTime;
		this.endTime = endTime;
		this.organizer = organizer;
		this.marker = marker;
		this.phone = phone;
		this.linkman = linkman;
		this.email = email;
		this.address = address;
		this.linkUrl = linkUrl;
		this.projectName = projectName;
	}

	private Integer swmId;
	private Integer swcId;
	private String state;
	private String newsTitle;
	private String newsKeyword;
	private String newsBrief;
	private String newsComment;
	private String newsIconFileCode;
	private String newsIllustratedFileCode;
	private Integer serialNumber;
	private String ifRecommend;
	
	private Date createDatetime;
	private Date writeDate;
	private String createRen;
	
	private String newscode;
	
	private String newPictureFilecode01;
	private String newPictureFilecode02;
	private String newPictureFilecode03;
	/**
	 * 新增字段
	 */
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private String organizer;//主办方
	private String marker;//承办方
	private String phone;//联系电话
	private String linkman;//联系人
	private String email;//邮箱
	private String address;//详细地址
	private String linkUrl;//链接地址
	private String projectName;//项目名称

	/**
	 * 扩展字段
	 */
	private String rootNewsIconFilePath;
	private String rootNewsIllustratedFilePath;
	
	private Integer showType;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getEndTime() {
		return endTime;
	}

	

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(name = "organizer", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	@Column(name = "marker", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}
	@Column(name = "phone", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "linkman", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	@Column(name = "email", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "address", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "linkUrl", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	

	

	// Constructors

	/** default constructor */
	public SysWebsiteComment() {
	}

	/** minimal constructor */
	public SysWebsiteComment(Integer swmId) {
		this.swmId = swmId;
	}

	/** full constructor */
	public SysWebsiteComment(Integer swmId, Integer swcId, String state,
			String newsTitle, String newsKeyword, String newsBrief,
			String newsComment, String newsIconFileCode,
			String newsIllustratedFileCode, Integer serialNumber,
			String ifRecommend,String remark) {
		this.swmId = swmId;
		this.swcId = swcId;
		this.state = state;
		this.newsTitle = newsTitle;
		this.newsKeyword = newsKeyword;
		this.newsBrief = newsBrief;
		this.newsComment = newsComment;
		this.newsIconFileCode = newsIconFileCode;
		this.newsIllustratedFileCode = newsIllustratedFileCode;
		this.serialNumber = serialNumber;
		this.ifRecommend = ifRecommend;
	}
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "swm_id", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getSwmId() {
		return this.swmId;
	}

	public void setSwmId(Integer swmId) {
		this.swmId = swmId;
	}

	@Column(name = "swc_id", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getSwcId() {
		return this.swcId;
	}

	public void setSwcId(Integer swcId) {
		this.swcId = swcId;
	}

	@Column(name = "state", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "news_title", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getNewsTitle() {
		return this.newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	@Column(name = "news_keyword", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getNewsKeyword() {
		return this.newsKeyword;
	}

	public void setNewsKeyword(String newsKeyword) {
		this.newsKeyword = newsKeyword;
	}

	@Column(name = "news_brief", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	public String getNewsBrief() {
		return this.newsBrief;
	}

	public void setNewsBrief(String newsBrief) {
		this.newsBrief = newsBrief;
	}

	@Column(name = "news_comment", unique = false, nullable = true, insertable = true, updatable = true, length = 10240)
	public String getNewsComment() {
		return this.newsComment;
	}

	public void setNewsComment(String newsComment) {
		this.newsComment = newsComment;
	}

	@Column(name = "news_icon_fileCode", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getNewsIconFileCode() {
		return this.newsIconFileCode;
	}

	public void setNewsIconFileCode(String newsIconFileCode) {
		this.newsIconFileCode = newsIconFileCode;
	}

	@Column(name = "News_illustrated_fileCode", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getNewsIllustratedFileCode() {
		return this.newsIllustratedFileCode;
	}

	public void setNewsIllustratedFileCode(String newsIllustratedFileCode) {
		this.newsIllustratedFileCode = newsIllustratedFileCode;
	}

	@Column(name = "Serial_number", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name = "if_Recommend", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getIfRecommend() {
		return this.ifRecommend;
	}

	public void setIfRecommend(String ifRecommend) {
		this.ifRecommend = ifRecommend;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_datetime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
    
	@Column(name = "create_ren", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getCreateRen() {
		return createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}
	@Column(name = "newsCode", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getNewscode() {
		return newscode;
	}

	public void setNewscode(String newscode) {
		this.newscode = newscode;
	}

	
	
	@Column(name = "new_picture_fileCode01", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getNewPictureFilecode01() {
		return newPictureFilecode01;
	}

	public void setNewPictureFilecode01(String newPictureFilecode01) {
		this.newPictureFilecode01 = newPictureFilecode01;
	}
	@Column(name = "new_picture_fileCode02", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getNewPictureFilecode02() {
		return newPictureFilecode02;
	}

	public void setNewPictureFilecode02(String newPictureFilecode02) {
		this.newPictureFilecode02 = newPictureFilecode02;
	}
	@Column(name = "new_picture_fileCode03", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getNewPictureFilecode03() {
		return newPictureFilecode03;
	}

	public void setNewPictureFilecode03(String newPictureFilecode03) {
		this.newPictureFilecode03 = newPictureFilecode03;
	}
	@Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code= news_icon_fileCode)")
	public String getRootNewsIconFilePath() {
		return rootNewsIconFilePath;
	}

	public void setRootNewsIconFilePath(String rootNewsIconFilePath) {
		this.rootNewsIconFilePath = rootNewsIconFilePath;
	}
	@Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code= News_illustrated_fileCode)")
	public String getRootNewsIllustratedFilePath() {
		return rootNewsIllustratedFilePath;
	}

	public void setRootNewsIllustratedFilePath(String rootNewsIllustratedFilePath) {
		this.rootNewsIllustratedFilePath = rootNewsIllustratedFilePath;
	}
	@Column(name = "project_name", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Transient
	public Integer getShowType() {
		showType = 0;
		if(startTime != null){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = sdf.format(startTime);
				Date now = new Date();
				if(now.before(sdf.parse(startDate)))
					showType = 1;
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "write_date", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
}