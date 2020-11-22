var mmg ;
var setting;
var _userType;
var _organizaType;
var _dblClick = function (data, row, col) {
	editUser(data.suiId);
}

$(function(){   
	_userType = $.supper("getParam", "userType"); 
	if(_userType==""||_userType==null||_userType==undefined ){
		_userType=2;
	}
	$("#userType").val(_userType);
 	initTree();
 	initGrid();
 	
 	  /**
	 * 改变查询,添加按钮样式
	 */
	$("#win_but_search").css("width","95px");
	$("#win_but_search").css("vertical-align","middle");
	
	$("#win_but_add").css("width","95px");
	$("#win_but_add").css("vertical-align","middle");
  
});
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
	                url:$.supper("getServicePath", {"service":"SysOrgService.getUserOrgTree"}),  //获取异步数据的地址
	                autoParam:["id"],  
	                dataFilter: filter //设置数据的展现形式  
	      },
	    callback: {//增加点击事件
	    	
	            	beforeClick: function(treeId, treeNode) {
		                $("#orgGxId").val(treeNode.id);
		                _organizaType=treeNode.attributes.organizaType;
		                search();
		            }
	      			,onAsyncSuccess:zTreeOnAsyncSuccess
		        }
	     };  	
	 $.fn.zTree.init($("#tree"), setting, null);  
     //设置树形数据结束
     $("#tree").css("height",$(window).height()-120);     
}

/**
 * tree控件加载成功后调用
 * @param event
 * @param treeId
 * @param treeNode
 * @param msg
 */
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	 var zTree = $.fn.zTree.getZTreeObj("tree"); 
     var nodes = zTree.getNodes();
     if (nodes.length>0) {
   	  zTree.selectNode(nodes[0]);
   	  zTree.expandNode(nodes[0],  true, false, false); 
   	  _organizaType=nodes[0].attributes.organizaType;
   	  $("#orgGxId").val(nodes[0].id);
   	  search();
     }
};



//设置数据的展现形式 
function filter(treeId, parentNode, childNodes) {  
	if (!childNodes) return null;  
	for (var i=0, l=childNodes.length; i<l; i++) {  
		childNodes[i].name = childNodes[i].name.replace('','');  
	}  
	return childNodes;  
}

function initGrid() {
	var cols = [
		{title: '用户名称', name: 'userName', width: 80, align: 'center'},
		{title: '用户账号', name: 'account', width: 80, align: 'center'},
		{title: '手机号', name: 'phoneNumber', width: 80, align: 'center'},
		{title: '岗位', name: 'userRole', width: 150, align: 'center', renderer: fmateRole},
		{title: '状态', name: 'state', width: 50, align: 'center', renderer: fmateState},
		{title: '安全码', name: 'openSecurity', width: 50, align: 'center', renderer: securityCode},
		{title: '操作', name: 'control', width: 180, align: 'center', renderer: control}

	];
	mmg = $('#datagrid1').mmGrid({
		height: $(window).height() - 200
		, cols: cols
		, method: 'get'
		, remoteSort: true
		, url: $.supper("getServicePath", {"service": "SysUserService.getSysUserPager"})
		, multiSelect: true
		, checkCol: false
		, showBackboard: false
		, fullWidthRows: true
		, autoLoad: false
		, dblClickFunc: _dblClick
		, plugins: [
			$('#pg').mmPaginator({})
		]
	});
}


/**用户修改信息 begn**/
function control(val,item,rowIndex){

	console.log(item)
	var str = "";
	if(item.state == '1')
		str += "<a onclick=\"updateState('"+item.suiId+"','2')\" class='btn btn-success btn-xs'>禁用</a>&nbsp;&nbsp;";
	else
		str += "<a onclick=\"updateState('"+item.suiId+"','1')\" class='btn btn-info btn-xs'>启用</a>&nbsp;&nbsp;";
	str += "<a onclick=\"editUser('"+item.suiId+"')\" class='btn btn-warning btn-xs'>修改</a>&nbsp;&nbsp;";
	str += "<a onclick=\"delUser('"+item.suiId+"')\" class='btn btn-danger btn-xs'>删除</a>&nbsp;&nbsp;";
	if (item.userRole.search('2') != -1){
		str += "<a onclick=\"resetSafetyCode('"+item.suiId+"')\" class='btn btn-danger btn-xs'>重置安全码</a>&nbsp;&nbsp;";
	}

	str += "<a onclick=\"editUserPass('"+item.suiId+"')\" class='btn btn-default btn-xs'>初始密码</a>";
   return str;
}

function fmateRole(val,item,rowIndex){
	var str = "";
	if(val.indexOf("1")>=0)
		str += "管理员，";
	if(val.indexOf("2")>=0)
		str += "采购员，";
	if(val.indexOf("3")>=0)
		str += "仓库管理员，";
	if(val.indexOf("4")>=0)
		str += "领料员，";
	if(str != "")
		str = str.substring(0,str.length-1);
   return str;
}
// 重置安全码
function resetSafetyCode(suiId) {
	var data = "suiId=" + suiId;
	$.supper("doservice", {
		"service": "sysUserService.updateSysUser", "data": data, "BackE": function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert", {title: "操作提示", msg: "安全码重置成功,新安全码为:123456！"});
				// $("#hids2").show()
			} else {
				$.supper("alert", {title: "操作提示", msg: "安全码重置失败！"});
			}
		}
	});
}

function updateState(suiId,state){
	var vdata = "suiId="+suiId+"&state="+state;
	$.supper("doservice", {"service":"SysUserService.updateSysUsate","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

function editUserPass(suiId){
	$.supper("confirm",{ title:"初始化密码", msg:"确认初始化密码？", yesE:function(){
		var vdata ="suiId="+suiId;
		$.supper("doservice", {"service":"SysUserService.updateSysUserPass","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

function editUser(suiId){
	 
	var orgGxId = $("#orgGxId").val();
	
	if("100"==_organizaType||"200"==_organizaType||"0"==_organizaType){ 
		$.supper("alert",{ title:"操作提示", msg:"请选择公司节点"});
		return ;
	}	
	
	var data = "organizaType="+(_organizaType?_organizaType:"")+"&userType="+(_userType?_userType:"")+"&orgGxId="+orgGxId+"&";
	
	if(suiId != null && suiId != ''){
		
		data += "&suiId="+suiId; 
		var att_title="用户信息";
		var att_url= $.supper("getServicePath", {"service":"SysUserService.findSysUser", "data":data,url:"/jsp/sys/sysuser/editstaff"});
		var tt_win=$.supper("showWin",{url:att_url,title:att_title,icon:"fa-group",width:800,height:500,BackE:search}); 
	}else{
		var att_title="用户信息";
		var att_url= $.supper("getServicePath", {"service":"SysUserService.findSysUser", "data":data,url:"/jsp/sys/sysuser/editstaff"});
		var tt_win=$.supper("showWin",{url:att_url,title:att_title,icon:"fa-group",width:800,height:500,BackE:search}); 
	}
}

function delUser(suiId){
	var vdata = "suiId="+suiId;
	$.supper("confirm",{ title:"删除用户", msg:"确认删除用户？", yesE:function(){
		$.supper("doservice", {"service":"SysUserService.delSysUser","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

/**用户修改信息 end**/
function fmateState(val,item,rowIndex){
	if(val=='2')
		return "禁用";
	else if(val=='1')
		return "启用";
}
function securityCode(val,item,rowIndex){
	if(item.userRole.search('2') != -1){
		var str=''
		if (item.openSecurity =='1'){
			str += "<a onclick=\"offSecurity('"+item.suiId+"')\" class='btn btn-danger btn-xs'>禁用</a>&nbsp;&nbsp;";
		}else {
			str += "<a onclick=\"openSecurity('"+item.suiId+"')\" class='btn btn-success btn-xs'>启用</a>&nbsp;&nbsp;";
		}
		return str
	}
}
function offSecurity(suiId){
	console.log("111")
	var data = "suiId=" + suiId;
	data += '&openSecurity='+''
	$.supper("doservice", {
		"service": "sysUserService.updateSysUserSecurityCode", "data": data, "BackE": function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert", {title: "操作提示", msg: "操作成功！",BackE:search});
				initGrid();
			}
		}
	});
}
function openSecurity(suiId){
	var data = "suiId=" + suiId;
	data += '&openSecurity='+'1'
	$.supper("doservice", {
		"service": "sysUserService.updateSysUserSecurityCode", "data": data, "BackE": function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert", {title: "操作提示", msg: "操作成功！",BackE:search});
				initGrid();
			}
		}
	});
}
function search(){
	var queryAction = "SysUserService.getSysUserPager";
	var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = att_mmgurl;
	mmg.load(); 
}
function formateState(val,item,rowIndex){
	if(val=='2')
		return "禁用";
	else if(val=='1')
		return "启用";
}
function formateControl(val,item,rowIndex){
	var str = "";
	if(item.state == '1')
		str += "<a onclick=\"updateState('"+item.wzId+"','2')\" class='btn btn-success btn-xs'>禁用</a>&nbsp;&nbsp;";
	else
		str += "<a onclick=\"updateState('"+item.wzId+"','1')\" class='btn btn-info btn-xs'>启用</a>&nbsp;&nbsp;";
		str += "<a onclick=\"editCompany('"+item.wzId+"')\" class='btn btn-warning btn-xs'>修改</a>&nbsp;&nbsp;";
	return str;
}
		
function editCompany(wzId){
	if(wzId != null && wzId != ''){
		var data = "wzId="+wzId; 
		var att_url= $.supper("getServicePath", {"service":"SysOrgService.findSysCompany", "data":data,url:"sys/sysorg/editSysCompany"});
		var tt_win=$.supper("showWin",{url:att_url,title:"组织信息",icon:"fa-sitemap",width:800,height:500,BackE:search}); 
	}else{
		var orgGxId = $("#orgGxId").val();
		if(orgGxId ==null || orgGxId==""){
			$.supper("alert",{ title:"操作提示", msg:"请选择组织类型!"});
			return;
		}
		var data="orgGxId="+orgGxId;
		var att_url= $.supper("getServicePath", {"service":"SysOrgService.findSysCompany","data":data,url:"sys/sysorg/editSysCompany"});
		var tt_win=$.supper("showWin",{url:att_url,title:"组织信息",icon:"fa-sitemap",width:800,height:500,BackE:search}); 
	}
}

function updateState(wzId,state){
	var vdata = "wzId="+wzId+"&state="+state;
	$.supper("doservice", {"service":"SysOrgService.updateSysCompanyState","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}