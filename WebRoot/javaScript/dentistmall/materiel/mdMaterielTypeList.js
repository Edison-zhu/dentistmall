var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");
/*
 * 修改部分begin
 * */
var _all_queryAction = "MdMaterielTypeService.getPagerModelObject";
var _all_deleteAction = "MdMaterielTypeService.deleteObject";
var _all_deleteAllAction = "MdMaterielTypeService.deleteAllObject";
var _all_win_datagrid;
var _all_win_url_edit = "/jsp/dentistmall/materiel/editMdMaterielType.jsp";
var _all_table_Id = "mmtId";
var _all_edit_icon = "th-list";
var _all_edit_title = "商品分类";
var _all_edit_width = 800;
var _all_edit_height = 500;
var _all_datagrid_height ;
var _dblClick = function (data, row, col) {
	main_edit(data.mmtId);
}

var _searchForm={ 
		lineNum:1,
		columnNum:2,
		control:[],
		groupTag:[],
		hiddenitems:[
					{name:"mdMmtId",id:"mdMmtId",value:"0",type:"hidden"} 
		             ],
		items:[{title:"类型编号",name:"mmtCode",type:"text", placeholder:"输入之后回车查询"},
			   {title:"类型名称",name:"mmtName",type:"text",placeholder:"输入之后回车查询"}
		]	
}

var _DataGrid={
	cols: [
		{title: '类型编号', sortable: true, name: 'mmtCode', width: 100, align: 'left'},
		{title: '类型名称', sortable: true, name: 'mmtName', width: 80, align: 'center'},
		{title: '状态', sortable: true, name: 'state', width: 80, align: 'center', impCode: 'PAR170926033732594'},
		{title: '操作', name: 'control', width: 150, align: 'center', renderer: control}
	]
	, remoteSort: false
	, height: _all_datagrid_height
	, url: getMMGridUrl()
	// , sortName:'SsnCode'
	, dblClickFunc: _dblClick
	, mmPaginatorOpt: _all_win_datagrid_pg
}

/*
 * 修改部分end
 * */
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"查询",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search}, 
		       	{title:"添加",id:"win_but_add",icon:"plus", colourStyle:"primary",rounded:true,clickE:main_add},
		       	{title:"批量删除",id:"win_but_alldel",icon:"close", colourStyle:"success",rounded:true,clickE:main_allDelete},
		       	{title:"生成文件",id:"win_but_json",icon:"download", colourStyle:"info",rounded:true,clickE:main_json},
		       ] 
	}

/**
 * 页面初始化函数
 */
$(function(){ 
  _all_win_searchForm.xform('createForm',_searchForm); 
  _all_div_hidden.xform('createhidden',_searchForm.hiddenitems);
  _all_win_tools_but.xtoolbar('create',_Toolbar); 
  //根据页面调整高度
  _all_datagrid_height=   $(window).height()-_all_win_searchForm.height()-64-95; 
  _DataGrid.height=_all_datagrid_height;
  _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_DataGrid); 
  initTree();
  main_search();
  //设置搜索框字体大小
  if($("#mmtCode").val() !=null){
	  $("#mmtCode").css("font-size","13px");
  }
//设置搜索框字体大小
  if($("#mmtName").val() !=null){
	  $("#mmtName").css("font-size","13px");
  }
  /*$("#mmtName").css("margin-left","1px");*/
  //设置按钮宽度与图标随文字居中
	  $("#win_but_search").css("width","95px");
	  $("#win_but_search").css("vertical-align","middle");
	  $("#win_but_add").css("width","95px");
	  $("#win_but_add").css("vertical-align","middle");
  
  //新增回车查询 2019-12-3 yanglei
	 $("#mmtCode").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#win_but_search").trigger("click");
		  }
	});
	 $("#mmtName").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#win_but_search").trigger("click");
		  }
	});
});  

function control(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);
	str += "<a onclick=\"main_edit('"+  idvalue+"')\"  class='btn btn-warning  btn-xs'>修改</a>&nbsp;&nbsp;";
	str += "<a onclick=\"main_delete('"+  idvalue+"')\"  class='btn btn-danger  btn-xs'>删除</a> ";  
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
	if($("#mdMmtId").val() ==null || ""==$("#mdMmtId").val()){
		$("#mdMmtId").val("0");
	}
	_all_win_datagrid.opts.url = getMMGridUrl();
	_all_win_datagrid.load();
}
/**
 * 添加记录操作事件
 */
function main_add(){
	var data="1=1";
	if(""!=$("#mdMmtId").val() && $("#mdMmtId").val() >0){
		data="mdMmtId="+$("#mdMmtId").val();
	}
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_edit});
	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:loadCurrentTree}); 
} 
/**
 * 修改记录操作事件
 * @param id  选中行记录主键值
 */
function main_edit(id){
	eval("var data= '"+_all_table_Id+"="+id+"'");
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_edit});
	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:loadCurrentTree});  
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
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:loadCurrentTree});
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
/**
 * 生成json文件
 */
function main_json(){ 
	$.supper("confirm",{ title:"生成操作", msg:"确认生成文件操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdMaterielTypeService.saveDataToJosn","BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！"});
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
            		lastExpandNode = treeNode;
            		$("#mdMmtId").val(treeNode.id);
            		main_search();
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

//当增加树的数据后刷新当前节点
function loadCurrentTree(){
	 var zTree = $.fn.zTree.getZTreeObj("tree"); 
	 if(lastExpandNode != null)  
      	{  
            var zTree = $.fn.zTree.getZTreeObj("tree");  
            if(lastExpandNode != null)//刷新当前节点
            	zTree.reAsyncChildNodes(lastExpandNode, "refresh");  
        }  
	 main_search(); 
}
//当修改树的数据后刷新当前节点的父节点
function loadParentTree(){
	 var zTree = $.fn.zTree.getZTreeObj("tree"); 
	 if(lastExpandNode != null)  
      	{  
            var zTree = $.fn.zTree.getZTreeObj("tree");  
            if(lastExpandNode.getParentNode() != null)//刷新当前节点的父节点
           		zTree.reAsyncChildNodes(lastExpandNode.getParentNode(), "refresh");  
        } 
	 main_search(); 
}

 
