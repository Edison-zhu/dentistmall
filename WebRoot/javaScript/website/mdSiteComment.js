var _mscType;
var _mscId;
var _swcId;
$(function(){  
	_mscType= $.supper("getParam", "mszType");
	_mscType = _mscType-1;
	_mscId= $.supper("getParam", "mscId");
	_swcId= $.supper("getParam", "swcId");
	if(_mscType==1){
		$("#lname").html("商品名称");
	}else if(_mscType==2){
		$("#lname").html("供应商名称");
	}else if(_mscType==3){
		$("#lname").html("类别");
	}
	initData();
});

function initData(){
	var data = "mscType="+_mscType+"&swcId="+_swcId;
	if(_mscId != null && _mscId !="")
		data +="&mscId="+_mscId;
	$.supper("doservice", {"service":"MdSiteCommentService.findFormObject","data":data,"BackE":function (jsondata) {
		if (jsondata.code == "1") {
			if(jsondata.obj.mscId != null){
				$("#mscId").val(jsondata.obj.mscId);
				$("#swcId").val(jsondata.obj.swcId);
				$("#commId").val(jsondata.obj.commId);
				$("#mscType").val(jsondata.obj.mscType);
				$("#serialComm").val(jsondata.obj.serialComm);
				if(_mscType==1){
					$("#commName").val(jsondata.obj.matName);
				}else if(_mscType==2){
					$("#commName").val(jsondata.obj.applicantName);
				}else if(_mscType==3){
					$("#commName").val(jsondata.obj.mmtName);
				}
				$("#content").html(jsondata.obj.content);
				$("input[name='commState'][value='"+jsondata.obj.commState+"']").attr("checked",true)
				
			}else{
				$("#serialComm").val(jsondata.obj.serialComm);
				$("#swcId").val(_swcId);
				$("#mscType").val(_mscType);
			}
		}
	}});
}

function selComm(){
	if(_mscType==1){
		var att_url= $.supper("getServicePath", {url:"/jsp/website/selMaterielList.jsp"});
		var tt_win=$.supper("showWin",{url:att_url,title:"选择商品信息",icon:"fa-th-list",width:800,height:600,BackE:function () {
			var selMaterielInfo = $.supper("getProductArray", "selMaterielInfo");
			if(selMaterielInfo != null && selMaterielInfo.wmsMiId != null){
				$("#commId").val(selMaterielInfo.wmsMiId);
				$("#commName").val(selMaterielInfo.matName);
				$.supper("setProductArray", {"name":"selMaterielInfo", "value":null});
			}
	 	}}); 
	}else if(_mscType==2){
		var att_url= $.supper("getServicePath", {url:"/jsp/website/selSupplierList.jsp"});
		var tt_win=$.supper("showWin",{url:att_url,title:"选择供应商",icon:"fa-th-list",width:800,height:600,BackE:function () {
			var selSupplierInfo = $.supper("getProductArray", "selSupplierInfo");
			if(selSupplierInfo != null && selSupplierInfo.wzId != null){
				$("#commId").val(selSupplierInfo.wzId);
				$("#commName").val(selSupplierInfo.applicantName);
				$.supper("setProductArray", {"name":"selSupplierInfo", "value":null});
			}
	 	}}); 
	}else if(_mscType==3){
		var att_url= $.supper("getServicePath", {url:"/jsp/website/selMdMaterielType.jsp"});
		var tt_win=$.supper("showWin",{url:att_url,title:"选择商品分类",icon:"fa-th-list",width:800,height:600,BackE:function () {
			var selMdMaterielType = $.supper("getProductArray", "selMdMaterielType");
			if(selMdMaterielType != null && selMdMaterielType.id != null){
				$("#commId").val(selMdMaterielType.id);
				$("#commName").val(selMdMaterielType.name);
				$.supper("setProductArray", {"name":"selMdMaterielType", "value":null});
			}
	 	}});
	}
}

function save(){
	var commName = $("#commName").val();
   	if(commName == null || commName == ""){
   		if(_mscType==1){
   			$.supper("alert",{ title:"操作提示", msg:"请选择商品！"});
   		}else if(_mscType==2){
   			$.supper("alert",{ title:"操作提示", msg:"请选择供应商！"});
   		}else if(_mscType==3){
   			$.supper("alert",{ title:"操作提示", msg:"请选择类别！"});
   		}
   		
   		return;
   	}
   	
   	var data=$("#accountForm").serialize();
   	$.supper("doservice", {"service":"MdSiteCommentService.saveOrUpdateObject", "ifloading":1,"options":{"type":"post","data":data}, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:closeWin});
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
function  closeWin(){
	$.supper("closeWin"); 
}