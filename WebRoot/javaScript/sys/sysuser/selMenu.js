var suiId;
$(function () {
	suiId = $.supper("getParam", "suiId");
	initMenuTree();
	initGrid();
});
function initMenuTree(){
	//设置树形数据开始
	var setting = {
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				isParent: "isParent"
			}
		},
		check:{
			checkstyle: "checkbox",
			enable: true
		},
        callback: {//增加点击事件
        	beforeClick: function(treeId, treeNode) {
            	$("#smenuId").val(treeNode.id);
            	search();
            }
        }
	};
	//设置树的初始数据
	var zNodes =[];
	$.supper("doservice", {"service":"SysMenuService.getAllSysMenuList","BackE":function (jsondata) {
		if (jsondata) {
			zNodes = jsondata;
			$.fn.zTree.init($("#tree"), setting, zNodes);  
    		//设置树形数据结束
    		selMenu();
		}
	}});
    
}

function selMenu(){
	var zTree = $.fn.zTree.getZTreeObj("tree"); 
	$.supper("doservice", {"service":"SysUserService.getUserMenu", "data":"suiId="+suiId,"BackE":function (jsondata) {
		if (jsondata.code == "1") {
			
			if(jsondata.obj != null && jsondata.obj.length > 0){
				
				for(var i = 0; i < jsondata.obj.length;i++){
					var node = zTree.getNodeByParam("id",jsondata.obj[i].smenuId);
					zTree.checkNode(node,true,false,false);
				}
			}
			
		}
	}});
}

function initGrid(){
	var cols = [
	           { title:'编号', name:'sconCode' ,width:80,  align:'center' },
	           { title:'名称' ,name:'sconName',width:30,  align:'center' },
	           { title:'类别', name:'sconType' ,width:30,  align:'center',renderer:formateType},
	           {title:'操作', name:'control', width: 80, align: 'center',renderer:formateControl}
	           ];  
	mmg = $('#datagrid1').mmGrid({
	       		height:$(window).height()-200
	            , cols: cols
	            , method: 'get'
	            , remoteSort:true
	            , multiSelect: true
	            , checkCol: false
	            , fullWidthRows: true
	            ,showBackboard:false
	            , autoLoad: false
	            , plugins: [
	                $('#pg').mmPaginator({})
	            ]
	 });  
	 mmg.on("loadSuccess",getSel);
	 mmg.load([]); 
}
function search(){
	var smenuId=$("#smenuId").val();
	var att_mmgurl = $.supper("getServicePath", {"service":"SysMenuService.getSysControlInfoPager","data":"smenuId="+smenuId});
	mmg.opts.url = att_mmgurl;
	mmg.load(); 
}
function formateType(val,item,rowIndex){
	if(val=='div')
		return "显示层";
	else if(val=='button')
		return "按钮";
	else if(val=='img')
		return "图片";
	else if(val=='input')
		return "输入框";
	else if(val=='raddio')
		return "单选框";
	else if(val=='checkbox')
		return "多选框";
	else if(val=='select')
		return "下拉选择框";
	else
		return "其他";
}
function formateControl(val,item,rowIndex){
	var str = "<select id=\""+item.sconId+"\">"
			+"<option value='0' selected >隐藏 </option>"
			+"<option value='1'>显示</option>"
			+"<option value='2'>只读</option>"
			+"</select>";
	return str;
}

function getSel(){
	var smenuId = $("#smenuId").val();
	if(smenuId != null && smenuId != ""){
		var data = "suiId="+suiId+"&smenuId="+smenuId;
		
		$.supper("doservice", {"service":"SysUserService.getUserControl", "data":data,"BackE":function (jsondata) {
			if (jsondata.code == "1") {
				var rows = mmg.rows();
				
				if(rows != null && rows.length > 0 && jsondata.obj != null && jsondata.obj.length >0){
					for(var i = 0; i < rows.length;i++){
						var flag =false;
						for(var j =0;j < jsondata.obj.length;j ++){
							if(rows[i].sconId==jsondata.obj[j].SCON_ID){
								$("#"+rows[i].sconId).val(jsondata.obj[j].IF_OPERATE);
								flag= true;
								break;
							}
						}
						if(!flag)
							$("#"+rows[i].sconId).val("0");
					}
				}else{
					for(var i = 0; i < rows.length;i++)
						$("#"+rows[i].sconId).val("0");
				}
			}
		}});
	}
}

function closeWin(){
	$.supper("closeWin");
}

function saveMenu(){
	var zTree = $.fn.zTree.getZTreeObj("tree"); 
	var nodes = zTree.getCheckedNodes(true);
	var data = "suiId="+suiId;
	if(nodes != null && nodes.length > 0){
		var str = "";
		for(var i = 0; i < nodes.length ; i++)
			str += nodes[i].id + ",";
		str = str.substring(0,str.length - 1);
		data += "&smenuIds="+str;
	}
	$.supper("doservice", {"service":"SysUserService.saveUserMenu", "data":data,"BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！"});
		}
	}});
	
}


function saveControl(){
	var rows = mmg.rows();
	var sconIds = "";
	var oprates="";
	if(rows != null && rows.length > 0){
		for(var i = 0; i < rows.length;i++){
			sconIds += rows[i].sconId+",";
			oprates += $("#"+ rows[i].sconId).val()+",";
		}
		sconIds = sconIds.substring(0, sconIds.length-1);
		oprates = oprates.substring(0, oprates.length-1);
	}
	if(sconIds == null || sconIds == ""){
		$.supper("alert",{ title:"操作提示", msg:"请选择控件信息！"});
		return;
	}
	var data = "suiId="+suiId+"&sconIds="+sconIds+"&oprates="+oprates;
	$.supper("doservice", {"service":"SysUserService.saveUserControl", "data":data,"BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！"});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
		}});
	
}