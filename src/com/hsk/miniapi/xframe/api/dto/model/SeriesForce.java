package com.hsk.miniapi.xframe.api.dto.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.series.force.Category;
import com.github.abel533.echarts.series.force.Link;
import com.github.abel533.echarts.series.force.Node;
import com.github.abel533.echarts.style.ItemStyle;

@SuppressWarnings("serial")
public class SeriesForce extends Series<com.hsk.xframe.api.dto.model.SeriesForce> {

	@SuppressWarnings("unused")
	private String type = "force"; 
	private String name;
	private Boolean ribbonType = false;
	private List<Category> categories;
	private ItemStyle itemStyle;
	private Integer minRadius;
	private Integer maxRadius;
	private Boolean useWorker;
	private Double scaling;
	private Double gravity;
	private Object roam;
	@SuppressWarnings("rawtypes")
	private List nodes;
	private List<Link> links;

	public SeriesForce() {
	}

	  

	@SuppressWarnings("rawtypes")
	public SeriesForce nodes(List nodes) {
		this.nodes = nodes;
		return this;
	}

	@SuppressWarnings("unchecked")
	public SeriesForce nodes(Link[] values) {
		if ((values == null) || (values.length == 0)) {
			return this;
		}
		nodes.addAll(Arrays.asList(values));
		return this;
	}

 

	public SeriesForce categories(List<Category> categories) {
		this.categories = categories;
		return this;
	}

	public SeriesForce categories(Category... values) {
		List<Category> att_categories=new ArrayList<Category>();
		for (Category arg : values) {  
			att_categories.add(arg); 
        } 
		categories=new ArrayList<Category>();
		categories.addAll( att_categories); 
		return this; 
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getRibbonType() {
		return ribbonType;
	}

	public void setRibbonType(Boolean ribbonType) {
		this.ribbonType = ribbonType;
	}

	public ItemStyle getItemStyle() {
		return itemStyle;
	}

	public void setItemStyle(ItemStyle itemStyle) {
		this.itemStyle = itemStyle;
	}

	public Integer getMinRadius() {
		return minRadius;
	}

	public void setMinRadius(Integer minRadius) {
		this.minRadius = minRadius;
	}

	public Integer getMaxRadius() {
		return maxRadius;
	}

	public void setMaxRadius(Integer maxRadius) {
		this.maxRadius = maxRadius;
	}

	public Boolean getUseWorker() {
		return useWorker;
	}

	public void setUseWorker(Boolean useWorker) {
		this.useWorker = useWorker;
	}

	public Double getScaling() {
		return scaling;
	}

	public void setScaling(Double scaling) {
		this.scaling = scaling;
	}

	public Double getGravity() {
		return gravity;
	}

	public void setGravity(Double gravity) {
		this.gravity = gravity;
	}

	public Object getRoam() {
		return roam;
	}

	public void setRoam(Object roam) {
		this.roam = roam;
	}

	public List getNodes() {
		return nodes;
	}

	public void setNodes(List nodes) {
		this.nodes = nodes;
	}

	public List<Link> getLinks() {
		return this.links;
	}

	public void setLinks(List<Link> list_link) {
		this.links = list_link;
	}

}
