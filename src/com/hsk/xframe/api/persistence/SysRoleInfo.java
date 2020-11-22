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

@SuppressWarnings("serial")
@Entity
@Table(name = "sys_role_info")
public class SysRoleInfo {
	
	private Integer sroleId;
	private Integer sysSroleId;
	private String menuName;
	private String menuCode;
	private String menuComment;
	private String createRen;
	private Date createDate;
	private String editRen;
	private Date editDate;
	
	public SysRoleInfo(){
		
	}
	
	public SysRoleInfo(Integer sroleId){
		this.sroleId = sroleId;
	}
	
	public SysRoleInfo(Integer sroleId, Integer sysSroleId, String menuName,
			String menuCode, String menuComment, String createRen,
			Date createDate, String editRen, Date editDate) {
		super();
		this.sroleId = sroleId;
		this.sysSroleId = sysSroleId;
		this.menuName = menuName;
		this.menuCode = menuCode;
		this.menuComment = menuComment;
		this.createRen = createRen;
		this.createDate = createDate;
		this.editRen = editRen;
		this.editDate = editDate;
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "srole_id", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getSroleId() {
		return sroleId;
	}
	public void setSroleId(Integer sroleId) {
		this.sroleId = sroleId;
	}
	@Column(name = "sys_srole_id")
	public Integer getSysSroleId() {
		return sysSroleId;
	}
	public void setSysSroleId(Integer sysSroleId) {
		this.sysSroleId = sysSroleId;
	}
	@Column(name = "menu_name", unique = false, nullable = true, insertable = true, updatable = true, length = 64)
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	@Column(name = "menu_code", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	@Column(name = "menu_comment", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public String getMenuComment() {
		return menuComment;
	}
	public void setMenuComment(String menuComment) {
		this.menuComment = menuComment;
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
}
