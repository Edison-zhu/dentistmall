 $(function(){   
	initDataGrid();
	search();
});


var mmg ;
var queryAction = "OpenParkInfoService.getPMOpenParkInfo";
var _dbleClick = function (data, row, col) {
	viewSys(data.mapiId);
}

var initDataGrid =  function(){
	    var cols = [
	                {title:'园区编号', name:'mapiId', width: 100, align: 'center'},
	                {title:'园区名称', name:'parkName' ,width:100,  align:'center' },	                
	                {title:'创建时间', name:'createDate', width: 80, align: 'center'},
	                {title:'管理员账号' ,name:'accountCode',width:50,  align:'center' },
	                {title:'状态' ,name:'state',width:50,  align:'center' ,renderer:controlstate},
	                {title:'操作' ,name:'state',width:100,  align:'center',renderer:control}
	            ];
	  
	            mmg = $('#datagrid1').mmGrid({
	              height:$(window).height()-190
	              , cols: cols
	              , method: 'get'
	              , remoteSort:true
	              , sortName: 'SECUCODE'
	              , sortStatus: 'asc'
	              , multiSelect: true
	              , checkCol: false
	              , fullWidthRows: true
	              , autoLoad: false
					, dblClickFunc: _dbleClick
	              , plugins: [
	                  $('#pg').mmPaginator({})
	              ]
	          });  
}


function search(){  
    var data = $("#queryForm").serialize(); 
    var att_mmgurl =  $.supper("getServicePath", {"service":queryAction,"data":data});
    mmg.opts.url = att_mmgurl;
    mmg.load();
    ///
	var mapiId =$("#mapiId").val();
    var data = "mapiId="+mapiId; 
	$.supper("doservice", {"service":"OpenParkInfoService.getOpenParkInfo", "data":data, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			var data=jsondata.obj;
			if(data){
					for(var i=1;i<7;i++){
						var paraname="data.doset"+i;
						eval("var tt= "+paraname+"?"+paraname+":'0'");
						 if(tt=="0"){
							  $("#doset"+i ).html("<p>已经完成设置！</p>");
						 }else {
							  $("#doset"+i ).html("<p>未进行设置！</p>");
							//var divstr= ' <button type="button" onclick="createParkInfo('+i+');" class="btn btn-round btn-primary btn-sm fa fa-plus">   ';
							//$("#doset"+i ).html(divstr);
						 }
					}
				
			}
		} 
	}});     
}


function controlstate(val,item,rowIndex){
	var   ls_back="";
	if(val==1){
		ls_back= "待审批";
	}
	if(val==2){
		ls_back= "审批通过";
	}
	if(val==3){
		ls_back= "审批不通过";
	}
	return ls_back;
}

function control(val,item,rowIndex){ 
   var   ls_back=" <a onclick=\"viewSys('"+item.mapiId+"')\" class='btn btn-primary'>查看</a>&nbsp;&nbsp; ";
	if(val==1){
		ls_back+="<a onclick=\"setSys('"+item.mapiId+"',2)\" class='btn btn-success'>通过</a>&nbsp;&nbsp;";
		ls_back+="<a onclick=\"setSys('"+item.mapiId+"',3)\" class='btn btn-danger'>不通过</a>&nbsp;&nbsp;";
	} 
	return ls_back;
   
   
} 

function viewSys(mapiId){
	var data="";
	if(mapiId != null && mapiId != ''){
		var data = "mapiId="+mapiId; 	 
	}
	var att_url= $.supper("getServicePath", {"service":"OpenParkInfoService.getApplyParkInfo","data":data,url:"/jsp/sysinfo/viewOpenPark"});
	var tt_win=$.supper("showWin",{url:att_url,width:800,height:680,BackE:search,title:"开园申请信息"}); 
}

function editSys(mapiId){
	var data="";
	if(mapiId != null && mapiId != ''){
		var data = "mapiId="+mapiId; 	 
	}
	var att_url= $.supper("getServicePath", {"service":"OpenParkInfoService.getApplyParkInfo","data":data,url:"/jsp/sysinfo/editOpenPark"});
	var tt_win=$.supper("showWin",{url:att_url,width:600,height:380,BackE:search,title:"开园申请信息"}); 
}
 

function setSys(mapiId,att_type){
	 var data = "mapiId="+mapiId+"&state="+att_type; 
		$.supper("doservice", {"service":"OpenParkInfoService.approvalParkInfo", "data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
		}});
	 
}

function createParkInfo(att_type){ 
	var mapiId =$("#mapiId").val();
	 var data = "mapiId="+mapiId+"&suppleType="+att_type; 
	$.supper("doservice", {"service":"OpenParkInfoService.supplementOpenPark", "data":data, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	}});
	
}
