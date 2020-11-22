var mmg;
var  all_opt_data={"list_sysClassColumns":[]};
var all_fieldType_json=[{"id":"integer","name":"整型"},{"id":"varchar2","name":"字符串"},{"id":"TIMESTAMP","name":"日期"}];
var all_state_json=[{"id":"1","name":"是"},{"id":"2","name":"否"}];
var  all_search_json=[];
var _dblClick = function (data, row, col) {
	addOrUpdateClassColumns(row);
}

function formatestate(val,item,rowIndex){
	if(val){
		var back="";
		$.each( all_state_json, function(nindex, nvalue){ 
		  	if(val&&nvalue.id&&val.toLowerCase()==nvalue.id.toLowerCase()){
		  		back=	nvalue.name;
		  		return true;
		  	} 
		});
	    return back;
	}else {
		return "";
	} 
}


$(function(){   
	initDataGrid();  
	initSelect();
	search();
});


function initSelect(){ 
	
	var att_tableName=$("#tableNameTemp").val();
	var att_tableNode=$("#tableNode").val();
	var att_tableCode=$("#tableCode").val();
	var att_tableCode=$("#tableCode").val();
	var att_SClassName=$("#SClassName").val(); 
	
	var att_data="sysCode="+$("#sysCode").val();
		$.supper("doservice", {"service":"TableInfoService.questNodeTable","data":att_data,options:{async:false}, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				if(att_tableName!=""){
				jsondata.obj.push({"text":att_SClassName,"id":att_tableName,"name":att_tableName+"("+att_tableNode+")","attributes":{"tableNode":att_tableNode,"tableCode":att_tableCode}});
				}
				all_search_json=jsondata.obj;
				loadSelect($("#tableName"),all_search_json);  
				$("#tableName").find("option[value='"+att_tableName+"']").attr("selected",true);
				 
			}
	}});
	 
}

function loadSelect(opt,att_data){	   
	 var itemroot = new Option("请选择", "");  
	     opt.append(itemroot); 
	      if(att_data!=null&&att_data.length>0){
	    	   for(var i=0 ;i<att_data.length;i++){
	    		   var item_node=att_data[i];
	    		   var item = new Option(item_node.name, item_node.id);  
	    		   opt.append(item);
	    	   }
	      } 
}


function show_table_info(opt){
	var att_value=opt.options[opt.options.selectedIndex].value;
	
	$.each(all_search_json, function(i, n){
		  if(n.id==att_value){
			  $("#tableNode").val(n.attributes.tableNode);
			  $("#tableCode").val(n.attributes.tableCode);
			  $("#SClassComment").val(n.attributes.tableNode);
			  $("#SClassName").val(n.text);
			  $("#dbType").val(n.attributes.dbType);
			  $("#seqstr").val(n.attributes.seqstr);
			  $("#pkName").val(n.attributes.pkName);  
			  initCodeList();
			  return true;
		  }
   }); 
}


function initDataGrid() {
	var cols = [
		{title: '属性编号', name: 'sccCode', width: 50, align: 'center'},
		{title: '属性名称', name: 'sccName', width: 30, align: 'center'},
//	                {title:'入参数字符串', name:'inParamStr', width: 30, align: 'center'},
//	                {title:'入参数说明', name:'inParamComment', width: 80, align: 'center'},
//	                {title:'出参数字符串', name:'outParamStr', width: 30, align: 'center'},
//	                {title:'出参数说明', name:'outParamComment', width: 80, align: 'center'},	                
		{title: '字段编号', name: 'fieldCode', width: 30, align: 'center'},
		{title: '字段说明', name: 'fieldNode', width: 30, align: 'center'},
		{title: '字段名称', name: 'fieldName', width: 30, align: 'center'},
		{title: '字段类型', name: 'dataType', width: 30, align: 'center'},
		{title: '整数位', name: 'fieldLength', width: 10, align: 'center'},
		{title: '小数位', name: 'fieldPrecision', width: 10, align: 'center'},
		{title: '默认值', name: 'defaultValue', width: 20, align: 'center'},
		{title: '是否空', name: 'ifNull', width: 10, align: 'center', renderer: formatestate},
		{title: '是否主键', name: 'ifPk', width: 10, align: 'center', renderer: formatestate},
		{title: '是否无字段', name: 'ifTransient', width: 10, align: 'center', renderer: formatestate},
		{title: '操作', name: 'sysCode', width: 100, align: 'center', renderer: control}
	];

	mmg = $('#datagrid1').mmGrid({
		//   height:$(window).height()-190	              ,
		height: 400,
		//	width:500,
		cols: cols
		, method: 'get'
		, remoteSort: true
		, sortName: 'SECUCODE'
		, sortStatus: 'asc'
		, multiSelect: true
		, checkCol: false
		, fullWidthRows: true
		, autoLoad: false
		, dblClickFunc: _dblClick
		, plugins: [
			$('#pg').mmPaginator({})
		]
	});

}

 

function control(val,item,rowIndex){
var str = "";
 str += "<a onclick=\"addOrUpdateClassColumns('"+rowIndex+"')\" class='btn btn-warning'>修改</a>&nbsp;&nbsp;";
str += " <a onclick=\"deleteClassColumns('"+item.sccId+"')\" class='btn btn-danger'>删除</a> "; 
return str;
}


function  closeWin(){
	$.supper("closeWin"); 
}

var queryAction="CodeAutoService.getListClassColumns";
function search(){ 
	var sciId=$("#sciId").val();
	if(sciId==null||sciId==''){
		mmg.load([]); 
	}else {
	     var data ='sciId='+sciId; 
		 var att_mmgurl = $.supper("getServicePath", {"service":queryAction,"data":data});
			mmg.opts.url = att_mmgurl;
			mmg.load(); 
	}
}

function initUrl(){
	var str = "";
	var systemIp = $("#systemIp").val();
	var systemPort = $("#systemPort").val();
	var projectName = $("#projectName").val();
	if(systemIp != null && systemIp!= "" && systemPort != null && systemPort!= "" && projectName != null && projectName!= ""){
		str += "http://"+systemIp+":"+systemPort+"/"+projectName;
	}
	$("#systemUrl").val(str);
}

function save(){
   		var SClassName = $("#SClassName").val();
   		if(SClassName == null || SClassName == ""){
   			$.supper("alert",{ title:"操作提示", msg:"系统类名称不能为空！"});
   			return;
   		}
   		 
   		var data =$('#accountForm').serialize();
   		var grid_data=mmg.rows();
   		all_opt_data.list_sysClassColumns=grid_data;
   		
   		$.supper("doservice", {"service":"CodeAutoService.saveOrUpdatesysClass",paramContent:all_opt_data.list_sysClassColumns, options:{async:false},"ifloading":1,"data":data, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert",{ title:"操作提示", msg:"操作成功！"});
					//, BackE:closeWin
				}else
					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 		}});		
}


function initCodeList(){
	var tableCode=$("#tableCode").val();
	if(tableCode!=null&&tableCode!=""){
		var att_data="sciId="+$("#sciId").val()+"&tableCode="+tableCode;
		$.supper("doservice", {"service":"CodeAutoService.initClassColumns",data:att_data,  options:{async:false},"ifloading":1, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				mmg.load(jsondata.obj); 
				$.supper("alert",{ title:"操作提示", msg:"操作成功！" }); 			
			}
			
		}});
	}else {
		$.supper("alert",{ title:"操作错误", msg:"请先选择表对象！" }); 	
		
	}
}
function deleteClassColumns(sccId){
	var vdata = "sccId="+sccId;
	$.supper("confirm",{ title:"删除类属性", msg:"确认删除类属性？" ,yesE:function(){
		 
		$.supper("doservice", {"service":"CodeAutoService.delsysClassColumns","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:function(){
					var grid_data=mmg.rows();
					if(grid_data!=null&&grid_data.length>0){
						for(var i=0;i<grid_data.length;i++){
							if(grid_data[i].sccId==sccId){
								mmg.removeRow(i);
							}
						}  
					}else {
						mmg.load([]); 
					}
				}});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}



function deleteClassColumnsList(){	
	var rows=mmg.selectedRows();
	if(rows==null&&rows.length<=0){
		$.supper("alert",{ title:"操作提示", msg:"请先选择一条数据！"});
		return ;
	}
	$.supper("confirm",{ title:"删除类属性", msg:"确认删除类属性？" ,yesE:function(){
		 
		$.supper("doservice", {"service":"CodeAutoService.delListsysClassColumns",paramContent:rows, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！",
					BackE:function(){
					
						if(grid_data!=null&&grid_data.length>0){
							for(var j=0;j<rows.length;j++){
								var sccId=rows[j];
								var grid_data=mmg.rows();
								for(var i=0;i<grid_data.length;i++){
									
									if(grid_data[i].sccId==sccId){
										mmg.removeRow(i);
									}
								} 
							} 
						}else {
							mmg.load([]); 
						}
					}
				
				});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}


function addOrUpdateClassColumns(rowIndex){
	var data="";
	var grid_data=mmg.rows();
	if(rowIndex!=null&&grid_data!=null&&grid_data.lenght>=rowIndex){
		data=grid_data[rowIndex];	
	}
	var  editquert="CodeAutoService.getClassColumns";
	var  aurl="/jsp/sysCode/editPojoCol";
	var att_url= $.supper("getServicePath", {"service":editquert,"data":data,url:aurl});
	var tt_win=$.supper("showWin",{url:att_url,icon:"fa fa-flag",title:"类属性信息",width:800,height:600,BackE:function(){ 
		
		
		var row_data= $.supper("getProductArray", "win_pojo_ClassColumns"); 
		
		if(row_data!=null&&row_data.sccId!=null){ 
			var grid_data=mmg.rows();
			var sccId=row_data.sccId;
			if(grid_data!=null&&grid_data.length>0){
				for(var i=0;i<grid_data.length;i++){
					if(grid_data[i].sccId==sccId){
						addRow.updateRow(row_data,i); 
					}
				}  
			}  
		}else 		if(row_data!=null&&row_data.sccId==null){
			addRow.removeRow(row_data,0); 
		}
		
		
	}});
	
}


