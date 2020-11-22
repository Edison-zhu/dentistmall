package com.hsk.xframe.api.dto.model;

import java.util.List;


/**
 * 
 * @author yc.chen
 * @version 1.0 2015-03-10
 *
 */
public class TreeNode {
	
	private String id ;
	private String pId ;
	
	private String text ;
	private String name ;
	private String type;
	private String datatype;
	
	
	private List tags ;
	
	private boolean open ;
	private String isParent;
	
	private List<TreeNode> nodes ;
	
	private List<TreeNode> children ;
	
	private Object attributes;
	
	
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getPId() {
		return pId;
	}

	public void setPId(String id) {
		pId = id;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getTags() {
		return tags;
	}

	public void setTags(List tags) {
		this.tags = tags;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
 

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public List<TreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	

	
	

	
	
	
	
	

}
