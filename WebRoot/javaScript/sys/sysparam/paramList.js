var mmg;
var _sparId;
var _controlParamList=[];
var _dblClick = function (data, row, col) {
	editParam(data.sparId);
}
$(function(){   
	initOneParam();
});
function addOneParam(){
	var att_url= $.supper("getServicePath", {"service":"SysParameterService.findSysParameter",url:"sys/sysparam/editOneParam"});
	var tt_win=$.supper("showWin",{url:att_url,width:800,height:600,icon:"fa-codepen",title:"编码信息",BackE:initOneParam}); 
}
function initOneParam(){
	 $.supper("doservice", {"service":"SysParameterService.getOneSysParameter","BackE":function (Att_jsonData) {
	 	if(Att_jsonData.code=='1'){
	 		jsonData =Att_jsonData.obj; 
		 	var pobj=$("#pobjT");
			pobj.html("");
			var  paramObjstr="";
			for(var i=0;i<jsonData.length;i++){
					var row=jsonData[i];
					var num = Math.random(); 
					num = Math.ceil(num *21);  
					var att_img= $.supper("getbasepath")+"img/param/"+(row.paramIcon?(row.paramIcon):(num+".png")); 
					if(row.paramNode != null && row.paramNode.length >8)
						row.paramNode=row.paramNode.substring(0,8)+"...";
					paramObjstr+="<div class=\"col-sm-3 col-md-2\" style=\"height:150px\"><a  class=\"thumbnail\" style=\"line-height:1\" href=\"#\" ondblclick=\"javascript:viewList( "+row.sparId+",'"+row.paramName+"')\"  ><img src=\""+att_img+"\"   alt=\"图标\"></img>"+
				 				" <div class=\"caption\"><h3 class=\"text-center text-danger\"><input type=\"radio\" name=\"param\" value=\""+row.sparId+"\"/>"
				 				+row.paramName+"</h3><p class=\"text-success\">"+(row.paramNode==null?"&nbsp;":row.paramNode)+" </div> </a>  </div>";
				}
				pobj.append($(paramObjstr)); 
	 	}
	}});  
}
function editOneParam(){
	var sparId = $("input[name='param']:checked").val();
	if(sparId ==null || sparId==""){
		$.supper("alert",{ title:"操作提示", msg:"请要修改的编码！"});
  		return;
	}
	var data="sparId="+sparId;
	var att_url= $.supper("getServicePath", {"service":"SysParameterService.findSysParameter","data":data,url:"sys/sysparam/editOneParam"});
	var tt_win=$.supper("showWin",{url:att_url,width:800,height:600,icon:"fa-codepen",title:"编码信息",BackE:initOneParam}); 
}
function delOneParam(){
	var sparId = $("input[name='param']:checked").val();
	if(sparId ==null || sparId==""){
		$.supper("alert",{ title:"操作提示", msg:"请要删除的编码！"});
  		return;
	}
	var data="sparId="+sparId;
	$.supper("confirm",{ title:"删除编码", msg:"确认删除编码？", yesE:function(){
		$.supper("doservice", {"service":"SysParameterService.delSysParameter","data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:initOneParam});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}
function viewList(sparId,paramName){
	if (sparId == 146) {
		toUnitView();
		return;
	}
	$("#oneName").html(paramName);
	$("#DataTables_Table_0_wrapper").html("<table id=\"datagrid1\" class=\"mmg\" ></table><div id=\"pg\" style=\"text-align: right;\"></div> ");
	_sparId=sparId;
	$("#childCode").show();
	$("#oneCode").hide();
	initControlParam();
}
// 跳转到单位详细信息页面
function toUnitView() {
	var att_url= $.supper("getServicePath", {url:'/jsp/sys/sysparam/unitParamParent.jsp'});
	$.supper("showTtemWin",{ "url":att_url,"title":'单位分类管理'});
}
function initDataGrid(cols){
	var att_mmgurl = $.supper("getServicePath", {
		"service": "SysParameterService.getSysParameterPager",
		"data": "sysSparId=" + _sparId
	});
	mmg = $('#datagrid1').mmGrid({
		cols: cols
		, method: 'get'
		, remoteSort: true
		, url: att_mmgurl
		, sortName: 'SECUCODE'
		, sortStatus: 'asc'
		, multiSelect: false
		, checkCol: false
		, showBackboard: false
		, fullWidthRows: true
		, autoLoad: false
		, dblClickFunc: _dblClick
		, plugins: [
			$('#pg').mmPaginator({})
		]
	});
	mmg.load();
}

function initControlParam(){
	var data = "suiId="+_sparId+"&paramSource=1";
	var cols = [
	    			{title:'序号', name:'paramOrderNumber', width: 50, align: 'center'},
	                {title:'编码名称', name:'paramName', width: 80, align: 'center'},
	                {title:'编码编号', name:'paramCode' ,width:120,  align:'center' },
	                {title:'编码值', name:'paramValue' ,width:60,  align:'center' }
	            ];  
	$.supper("doservice", {"service":"SysControlParamService.getSysControlParamList","data":data,"BackE":function (jsondata) {
		if(jsondata.code ==1 && jsondata.obj != null && jsondata.obj.length >0){
			_controlParamList=jsondata.obj;
			for(var i =0 ;i < _controlParamList.length;i++){
				var cid = _controlParamList[i].controlId;
				var pName = _controlParamList[i].paramName;
				var pNode = _controlParamList[i].paramNodeJson;
				var pType = _controlParamList[i].paramType;
				var col={title:pName, name:cid, width: 100, align: 'center',renderer:showContrlParam};
				cols.push(col);
			}
		}
		cols.push({title:'操作' ,name:'control',width:100,  align:'center',renderer:control});
		initDataGrid(cols);
	}});
}

function control(val,item,rowIndex){
	var str = "";
	str += "<a onclick=\"editParam('"+item.sparId+"')\" class='btn btn-warning btn-xs'>修改</a>&nbsp;&nbsp;";
	str += "<a onclick=\"delParam('"+item.sparId+"')\" class='btn btn-danger btn-xs'>删除</a>&nbsp;&nbsp;";
   return str;
}
function showContrlParam(val,item,rowIndex,name){
   var str = "";
   for(var i =0 ;i < _controlParamList.length;i++){
   		if(name==_controlParamList[i].controlId){
   			if(_controlParamList[i].paramType=='1' && val != null){
   				var options=_controlParamList[i].paramNodeJson.split(",");
				for(var j=0;j<options.length;j++){
					var ss=options[j].split(":");
					if(ss[0]==val)
						str = ss[1];
				}
   			}else if(_controlParamList[i].paramType=='2')
   				str=val;
   			else if(_controlParamList[i].paramType=='3' && val != null && val !="")
   				str = Date.formatLaydate(new Date(val.replace(/-/g,"/")),_controlParamList[i].paramNode);
   			break;
   		}
   }
   return str;
}
function editParam(sparId){
	var att_url="";
	var data="";
	if(sparId != null)
		data="sparId="+sparId+"&sysSparId="+_sparId;
	else
		data="sysSparId="+_sparId;
	att_url= $.supper("getServicePath", {"data":data,url:"sys/sysparam/editParam"});
	var tt_win=$.supper("showWin",{url:att_url,width:800,height:600,icon:"fa-codepen",title:"编码信息",BackE:search}); 
}
function delParam(sparId){
	var data="sparId="+sparId;
	$.supper("confirm",{ title:"删除编码", msg:"确认删除编码？", yesE:function(){
		$.supper("doservice", {"service":"SysParameterService.delSysParameter","data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}
function backOne(){
	$("#childCode").hide();
	$("#oneCode").show();
}
function search(){
	var att_mmgurl =  $.supper("getServicePath", {"service":"SysParameterService.getSysParameterPager","data":"sysSparId="+_sparId});
	mmg.opts.url = att_mmgurl;
    mmg.load(); 
}