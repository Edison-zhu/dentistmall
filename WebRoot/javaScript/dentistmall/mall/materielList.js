var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdMaterielInfoService.getPagerModelObject";
var _all_deleteAction = "MdMaterielInfoService.deleteObject";
var _all_deleteAllAction = "MdMaterielInfoService.deleteAllObject";

var _all_win_datagrid;
var _all_win_url_edit = "/jsp/dentistmall/mall/updateMateriel.jsp";
var _all_table_Id = "wmsMiId";
var _all_edit_icon = "gears";
var _all_edit_title = "商品信息维护";
var _all_edit_width = 800;
var _all_edit_height =400;
var _all_datagrid_height ;
var _dblClick = function (data, row, col) {
	main_edit(data.wmsMiId);
}

var _searchForm={ 
		lineNum:1,
		columnNum:2,
		control:[],
		groupTag:[], 
		hiddenitems:[
		             {title:"物料类型",name:"matType",type:"hidden", placeholder:"物料类型"},
		],
		items:[ 
		       {title:"商品编号",name:"matCode",type:"text", placeholder:"请输入商品编码或型号编号"},
			   {title:"商品名称",name:"matName",type:"text",placeholder:"商品名称"}
			   
		]	
	}

var _MainSearchForm={ 
		lineNum:1,
		columnNum:2,
		control:[],
		groupTag:[], 
		hiddenitems:[
		             {title:"物料类型",name:"matType",type:"hidden", placeholder:"物料类型"},
		],
		items:[ 
			   {title:"商品名称",name:"matName",type:"text",placeholder:"输入商品名称,回车查询"},
			   {title:"供应商名称",name:"applicantName",type:"text", placeholder:"输入供应商名称,回车查询"}
			   
		]	
	}
	 
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"查询",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search}, 
		       	{title:"添加",id:"win_but_add",icon:"plus", colourStyle:"primary",rounded:true,clickE:main_add},
		        {title:"全部导出",id:"win_but_export",icon:"download", colourStyle:"success",rounded:true,clickE:export_all}
		       ] 
	} 

var _MainToolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"查询",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search},
		        {title:"全部导出",id:"win_but_export",icon:"download", colourStyle:"success",rounded:true,clickE:export_all}
		       ] 
	} 

var _DataGrid = {
	cols: [
		{title: '商品编码', sortable: true, name: 'matCode', width: 80, align: 'center'},
		{title: '商品名称', sortable: true, name: 'matName', width: 80, align: 'center'},
		{title: '供应商', sortable: true, name: 'applicantName', width: 80, align: 'center'},
		{title: '品牌', sortable: true, name: 'brand', width: 80, align: 'center'},
		{title: '单价', sortable: true, name: 'money1', width: 50, align: 'center', renderer: formatMoney},
		{title: '单位', sortable: true, name: 'basicUnit', width: 50, align: 'center'},
		{title: '状态', sortable: true, name: 'state', width: 50, align: 'center', impCode: 'PAR171023031139920'},
		{title: '操作', name: 'control', width: 80, align: 'center', renderer: control}
	]
	, remoteSort: false
	, name: 'materielListGrid'
	, height: _all_datagrid_height
	, url: getMMGridUrl()
	, dblClickFunc: _dblClick
	, mmPaginatorOpt: _all_win_datagrid_pg
}

function formatMoney(val,item,rowIndex){
	if(val != null && val!="")
		return toDecimal2(val);
	else
		return "";
}
/**
 * 页面初始化函数
 */
$(function(){ 
	
  _all_div_hidden.xform('createhidden',_searchForm.hiddenitems);
  var organizaType=$("#organizaType").val();
  if(organizaType =='100'){
	  _all_win_searchForm.xform('createForm',_searchForm); 
	  _all_win_tools_but.xtoolbar('create',_Toolbar); 
  }
  else{
	  _all_win_searchForm.xform('createForm',_MainSearchForm); 
	  _all_win_tools_but.xtoolbar('create',_MainToolbar); 
  }
	  
  
  _all_datagrid_height=   $(window).height()-_all_win_searchForm.height()-64-95; 
  
  _DataGrid.height=_all_datagrid_height;
  
  _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_DataGrid); 
  
  main_search();
  initTree();
  //设置查询按钮图标跟随文字水平居中,按钮长度
	  $("#win_but_search").css("width","95px");
	  $("#win_but_search").css("vertical-align","middle");
	  $("#win_but_add").css("width","95px");
	  $("#win_but_add").css("vertical-align","middle");
//设置搜索框字体大小
  if($("#matName").val() !=null){
	  $("#matName").css("font-size","13px");
  }
//设置搜索框字体大小
  if($("#applicantName").val() !=null){
	  $("#applicantName").css("font-size","13px");
  }
//新增回车查询 2019-12-3 yanglei
	 $("#applicantName").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#win_but_search").trigger("click");
		  }
	});
	 $("#matName").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#win_but_search").trigger("click");
		  }
	});
});  

function control(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);
	if(item.state=='1')
		str += "<a onclick=\"update_state('"+  idvalue+"','2')\"  class='btn btn-danger  btn-xs'>下架</a>&nbsp;&nbsp;";
	else if(item.state=='2')
		str += "<a onclick=\"update_state('"+  idvalue+"','1')\"  class='btn btn-info  btn-xs'>上架</a>&nbsp;&nbsp;";
	str += "<a onclick=\"main_edit('"+  idvalue+"')\"  class='btn btn-warning  btn-xs'>修改</a>&nbsp;&nbsp;";
   return str;
}

/**
 * 获取数据查询地址
 * @returns
 */
function getMMGridUrl(){	
	var data = _all_win_searchForm.serialize();
	var att_url= $.supper("getServicePath", {"service":_all_queryAction, "data":data}); 
	return att_url;
}

/**
 * 对主表进行查询（刷新）操作
 */
function main_search(){
	_all_win_datagrid.opts.url = getMMGridUrl();
	_all_win_datagrid.load();
}

/**
 * 添加记录操作事件
 */
function main_add(){ 
		var att_url= $.supper("getServicePath", {url:_all_win_url_edit});
		//	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search}); 
		 $.supper("showTtemWin",{ "url":att_url,"title":_all_edit_title});
}

/**
 * 修改记录操作事件
 * @param id  选中行记录主键值
 */
function main_edit(id){
	eval("var data= '"+_all_table_Id+"="+id+"'");
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_edit});
	// var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search});
		$.supper("showTtemWin",{ "url":att_url,"title":_all_edit_title});
}

function update_state(id,state){
	eval("var vdata= '"+_all_table_Id+"="+id+"'");
	vdata +="&state="+state;
	$.supper("confirm",{ title:"修改操作", msg:"确认修改状态操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdMaterielInfoService.updateObjectState","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:main_search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

/**
 * 删除选中行记录
 * @param id 选中行记录主键值
 */
function main_delete(id){ 
	eval("var vdata= '"+_all_table_Id+"="+id+"'"); 
	$.supper("confirm",{ title:"删除操作", msg:"确认删除记录操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":_all_deleteAction,"data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:main_search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}  
/**
 * 删除多条选中行记录
 */
function main_allDelete(){ 
	var rows=_all_win_datagrid.selectedRows();
	if(rows ==null || rows.length ==0){
		$.supper("alert",{ title:"操作提示", msg:"请先选择要操作的数据记录！"});
		return;
	}
	var rbsIds="";
	for(var i =0 ;i < rows.length;i++){
		eval('rbsIds += rows[i].'+_all_table_Id+'+","; ' ); 
	}
	rbsIds = rbsIds.substring(0,rbsIds.length-1);	
	$.supper("confirm",{ title:"批量删除操作", msg:"确认删除记录操作？", yesE:function(){		 
		eval("var vdata= '"+_all_table_Id+"s="+rbsIds+"'"); 		
		$.supper("doservice", {"service":_all_deleteAllAction,"data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:main_search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

var setting;
var lastExpandNode;
function initTree(){
	//设置树形数据开始
	setting = {
		data: {
		        simpleData: {
			        enable:true,
			        idKey: "id",
			        pIdKey: "pId",
			        isParent: "isParent"
		       	}
	       	},
	    async: {  
	            enable: true, 
	            url:$.supper("getServicePath", {"service":"MdMaterielTypeService.getTreeListByMdMmtId"}),  //获取异步数据的地址
	            autoParam:["id"],  
	            dataFilter: filter //设置数据的展现形式  
	      },
		  callback: {//增加点击事件
            	beforeClick: function(treeId, treeNode) {
            		if(treeNode.id > 0){
            			$("#matType").val("/"+treeNode.id+"/");
                		main_search();
            		}else{
            			$("#matType").val("");
                		main_search();
            		}
            		
	            }
	        }
         };
	var zNodes =[{id:0, pId:"", name:"商品类别列表", isParent:true}];  
	$.fn.zTree.init($("#tree"), setting, zNodes);  
	//自动展现第一层树
	var zTree = $.fn.zTree.getZTreeObj("tree"); 
	var node = zTree.getNodesByParam("id","0");
	lastExpandNode=node;
	zTree.expandNode(node[0],  true, false, false); 
	
}
//设置数据的展现形式 
function filter(treeId, parentNode, childNodes) {  
	if (!childNodes) return null;  
	for (var i=0, l=childNodes.length; i<l; i++) {  
		childNodes[i].name = childNodes[i].name.replace('','');  
	}  
	return childNodes;  
} 

function export_all(){
	var vdata = _all_win_searchForm.serialize();
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdMaterielInfoService.exportList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			if (jsondata.obj != undefined)
				newTab.location.href=jsondata.obj.path;
			else
				$.supper("alert",{ title:"操作提示", msg:"已下线，请重新登录！"});
		} else if (jsondata.code == "2") {
			$.supper("alert",{ title:"操作提示", msg:"已下线，请重新登录！"});
		} else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}


 