 var all_SClassType_json=[{"id":"1","name":"pojo"},{"id":"2","name":"dao"},{"id":"3","name":"service"}];
 var mmg ;
 var queryAction = "CodeAutoService.getPagerModelsysClass";
var _dblClick = function(data, row, col){
	editSys(data.sciId, data.SClassType);
}
$(function(){   
	initSelect();
	initDataGrid(); 
	search();
	
});


function initSelect(){ 
	$.supper("doservice", {"service":"SystemInfoService.findeListNodeSystemInfo",options:{async:false}, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			loadSelect($("#sysCode"),jsondata.obj);
		}
}});	
}

function loadSelect(opt,att_data){	   
      if(att_data!=null&&att_data.length>0){
    	   for(var i=0 ;i<att_data.length;i++){
    		   var item_node=att_data[i];
    		   var item = new Option(item_node.name, item_node.id);  
    		   opt.append(item);
    	   }
      } 
}


 function initDataGrid() {
	 var cols = [
		 {title: '系统类名称', name: 'SClassName', width: 100, align: 'center'},
		 {title: '系统类编号', name: 'SClassCode', width: 100, align: 'center'},
		 {title: '类型', name: 'SClassType', width: 100, align: 'center'},
		 {title: '类备注', name: 'SClassCode', width: 80, align: 'center'},
		 {title: '作者', name: 'authortext', width: 50, align: 'center'},
		 {title: '版本', name: 'versiontext', width: 50, align: 'center'},
		 {title: '包名', name: 'packagename', width: 50, align: 'center'},
		 {title: '项目路径', name: 'projectpath', width: 50, align: 'center'},
		 {title: '压缩文件名', name: 'zipName', width: 50, align: 'center', renderer: formatZipName},
		 {title: '操作', name: 'sysCode', width: 150, align: 'center', renderer: control}
	 ];

	 mmg = $('#datagrid1').mmGrid({
		 height: $(window).height() - 190
		 , cols: cols
		 , method: 'get'
		 , remoteSort: true
		 , sortName: 'SECUCODE'
		 , sortStatus: 'asc'
		 , multiSelect: true
		 , nowrap: true
		 , fullWidthRows: true
		 , autoLoad: false
		 , checkCol: true
		 , dblClickFunc: _dblClick
		 , plugins: [
			 $('#pg').mmPaginator({})
		 ]
	 });
 }

function search(){ 
    var data =$('#queryForm').serialize(); 
	 var att_mmgurl = $.supper("getServicePath", {"service":queryAction,"data":data});
		mmg.opts.url = att_mmgurl;
		mmg.load(); 
}

 function controltype(val,item,rowIndex){
	 var ls_back=""; 
  if(val!=undefined){
    	$.each( all_SClassType_json, function(nindex, nvalue){ 
 		  	if(val&&nvalue.id&&val.toLowerCase()==nvalue.id.toLowerCase()){ 
 		  		ls_back=nvalue.name+"";
 		  		return ls_back;
 		  	} 
 	});
  }
	return ls_back;	 
 }
 
function control(val,item,rowIndex){
	var str = "";
	str += "<a onclick=\"editSys('"+item.sciId+"','"+item.SClassType+"')\" class='btn btn-warning  btn-xs'>修改</a>&nbsp;&nbsp;";
	str += " <a onclick=\"delSys('"+item.sciId+"')\" class='btn btn-danger  btn-xs'>删除</a> "; 
	str += " <a onclick=\"createZip('"+item.sciId+"')\" class='btn btn-danger  btn-xs'>生成zip</a> "; 
   return str;
}


function formatZipName(val,item,rowIndex){
	var str = "";
	if(val&&""!=val){
		str="<a href='"+item.zipRootpth+"' >"+val+"</a>"
	}
   return str;
}

function listDataZip(){
	var  data_array= mmg.selectedRows(); 
	if(data_array&&data_array.length>0){
		var str = "";
			$.each( data_array, function(nindex, nvalue){ 
				str+=nvalue.SClassName+"("+nvalue.SClassCode+"),";
			});
			if(str!=""){
				str=str.substring(0, str.length-1);
			}
		$.supper("confirm",{ title:"生成类文件", msg:" <p> ======= <b/> "+str+" <b/> ======= </p> 这些类需要进行文件生成？" ,yesE:function(){
			 var datalist={"listsci":data_array}; 			 
			$.supper("doservice", {"service":"CodeAutoService.createClassListFile",paramContent:datalist , "BackE":function (jsondata) {
				if (jsondata.code == "1"&&jsondata.obj) {
					$.supper("alert",{ title:"操作提示", msg:"操作成功！点击==>><a href='"+jsondata.obj+"'>这里</a><===进行下载 ", BackE:search});
				}else
					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
		 	}});
			
		}}); 
		
	}else {
		$.supper("alert",{ title:"操作提示", msg:"请先选中一条记录！"});
	}
	
}

function createZip(sciId){
	var vdata = "sciId="+sciId;
	$.supper("confirm",{ title:"生成类文件", msg:"确认进行类文件生成？" ,yesE:function(){
		 
		$.supper("doservice", {"service":"CodeAutoService.createClassFile","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}}); 
}

function getUrl(SClassType){
	if(SClassType=='pojo'){
		return "/jsp/sysCode/editCodePojoInfo";
	}
	if(SClassType=='dao'){
		return "/jsp/sysCode/editCodeDaoInfo";
		}
	if(SClassType=='service'){
		return "/jsp/sysCode/editCodeServiceInfo";
	}
}

function editSys(sciId,SClassType){
	
	var  editquert="CodeAutoService.getsysClass";
	var  aurl=getUrl(SClassType);
	var att_sysCode=$("#sysCode").val();
	var att_data="SClassType="+SClassType+"&sysCode="+att_sysCode;
	if(sciId != null && sciId != ''){
		att_data += "&sciId="+sciId; 
	} 
	 var att_url= $.supper("getServicePath", {"service":editquert,"data":att_data,url:aurl});
	 var tt_win=$.supper("showWin",{url:att_url,maxmin:true,icon:"fa fa-flag",title:"系统类信息",width:800,height:600,BackE:search}); 
	 top.window.layer.full(tt_win);
}

function delSys(sciId){
	var vdata = "sciId="+sciId;
	$.supper("confirm",{ title:"删除系统类", msg:"确认删除系统类？" ,yesE:function(){
		 
		$.supper("doservice", {"service":"CodeAutoService.delsysClass","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}
