package com.hsk.xframe.api.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

@SuppressWarnings("serial")
@Entity
@Table(name = "sys_menu_info")
public class SysMenuInfo {
	
	private Integer smenuId;
	private Integer sysSmenuId;
	private String menuName;
	private String menuIcon;
	private String menuAddree;
	private String menuState;
	private String menuType;
	private String menuCode;
	private String linkAddree;
	private Integer menuOrderCode;
	private String menuPath;
	private String fileCode;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;
	
	private String filePath;
	
	public SysMenuInfo(){
		
	}
	
	public SysMenuInfo(Integer smenuId){
		this.smenuId = smenuId;
	}

	public SysMenuInfo(Integer smenuId, Integer sysSmenuId, String menuName,
			String menuIcon, String menuAddree, String menuState,
			String menuType, String menuCode, String linkAddree,
			Integer menuOrderCode, String menuPath, String createRen,
			Date createDate, String editRen, Date editDate) {
		super();
		this.smenuId = smenuId;
		this.sysSmenuId = sysSmenuId;
		this.menuName = menuName;
		this.menuIcon = menuIcon;
		this.menuAddree = menuAddree;
		this.menuState = menuState;
		this.menuType = menuType;
		this.menuCode = menuCode;
		this.linkAddree = linkAddree;
		this.menuOrderCode = menuOrderCode;
		this.menuPath = menuPath;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "smenu_id", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getSmenuId() {
		return smenuId;
	}

	public void setSmenuId(Integer smenuId) {
		this.smenuId = smenuId;
	}
	
	@Column(name = "sys_smenu_id", unique = true, nullable = true, insertable = true, updatable = true)
	public Integer getSysSmenuId() {
		return sysSmenuId;
	}

	public void setSysSmenuId(Integer sysSmenuId) {
		this.sysSmenuId = sysSmenuId;
	}
	@Column(name = "menu_name", unique = false, nullable = true, insertable = true, updatable = true, length = 125)
	public String getMenuName() {
		return menuName;
	}
	
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	@Column(name = "menu_icon", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	@Column(name = "menu_addree", unique = false, nullable = true, insertable = true, updatable = true, length = 215)
	public String getMenuAddree() {
		return menuAddree;
	}

	public void setMenuAddree(String menuAddree) {
		this.menuAddree = menuAddree;
	}
	@Column(name = "menu_state", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getMenuState() {
		return menuState;
	}

	public void setMenuState(String menuState) {
		this.menuState = menuState;
	}
	@Column(name = "menu_type", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	@Column(name = "menu_code", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	@Column(name = "link_addree", unique = false, nullable = true, insertable = true, updatable = true, length = 125)
	public String getLinkAddree() {
		return linkAddree;
	}

	public void setLinkAddree(String linkAddree) {
		this.linkAddree = linkAddree;
	}
	@Column(name = "menu_order_code", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getMenuOrderCode() {
		return menuOrderCode;
	}

	public void setMenuOrderCode(Integer menuOrderCode) {
		this.menuOrderCode = menuOrderCode;
	}
	@Column(name = "menu_path", unique = false, nullable = true, insertable = true, updatable = true, length = 512)
	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	@Column(name = "create_ren", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCreateRen() {
		return createRen;
	}

	public void setCreateRen(String createRen) {
		this.createRen = createRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name = "edit_ren", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getEditRen() {
		return editRen;
	}

	public void setEditRen(String editRen) {
		this.editRen = editRen;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_date")
	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	@Column(name = "file_code", unique = false, nullable = true, insertable = true, updatable = true, length = 125)
	public String getFileCode() {
		return fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	@Formula("(SELECT a.root_path FROM sys_file_info a WHERE a.file_code= file_code)")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
