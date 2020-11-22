var _controlParams=[];
$(function(){  
	initIconUl(20);
	$(".hotBank-list-ico").bind('click',function(){
      $('#chose_input').val($(this).attr('title'));
      $('.bank_xl').hide();
      $("#chose_bank").parent('div').find('img').addClass('jt_xz');
    });
    $("#chose_bank").click(function(){
       $('.bank_xl').show();
       $("#chose_bank").parent('div').find('img').removeClass('jt_xz');
    });
	initControlParam();
});
function initIconUl(liSize){
	var str="";
	for(var i=0;i<liSize;i++){
		var att_img= $.supper("getbasepath")+"img/param/"+i+".png";
		str +="<li class=\"hotBank-list\" ><a href=\"javascript:selIcon('"+i+".png')\" class=\"hotBank-list-ico\">"
		+"<img src=\""+att_img+"\" style=\"height:20px\" alt=\"\"/>";
	}
	$("#iconUl").html(str);
}
function initControlParam(){
	var sparId=$("#sparId").val();
	if(sparId != null && sparId != ""){
		var data = "suiId="+sparId+"&paramSource=1";
		$.supper("doservice", {"service":"SysControlParamService.getSysControlParamList","data":data,"BackE":function (jsondata) {
			if(jsondata.code ==1 && jsondata.obj != null && jsondata.obj.length >0){
				for(var i =0 ;i < jsondata.obj.length;i++){
					var cid = jsondata.obj[i].controlId;
					var pName = jsondata.obj[i].paramName;
					var pNode = jsondata.obj[i].paramNode;
					var pOrder = jsondata.obj[i].paramOrder;
					var pType = jsondata.obj[i].paramType;
					$("#"+cid+"Name").val(pName);
					$("#"+cid+"Order").val(pOrder);
					if(pType !="2"){
						$("#"+cid+"Content").val(pNode);
					}
				}
			}
		}});
	}
	
}
function save(){
	var paramName = $("#paramName").val();
   	if(paramName == null || paramName == ""){
   		$.supper("alert",{ title:"操作提示", msg:"大类名称不能为空！"});
   		return;
   	}
   	var paramIcon = $("#paramIcon").val();
   	if(paramIcon == null || paramIcon == ""){
   		$.supper("alert",{ title:"操作提示", msg:"大类图标不能为空！"});
   		return;
   	}
   	var paramNode = $("#paramNode").val();
   	if(paramNode != null && paramNode.length > 200){
   		$.supper("alert",{ title:"操作提示", msg:"大类说明不能超过200字！"});
   		return;
   	}
   
   	//组装下拉选择
   	var isError=false;
   	_controlParams=[];
   	$(".selSet").each(function(i,v){
		var cid = $(v).val();
		var pName = $("#"+cid+"Name").val();
		var pNode = $("#"+cid+"Content").val();
		var pOrder = $("#"+cid+"Order").val();
		if(pName != null && pName != ""){
			if(pNode ==null || pNode==""){
				$.supper("alert",{ title:"操作提示", msg:"请输入选项内容！"});
				isError=true;
				return;
			}
			
			if(pOrder ==null || pOrder==""){
				$.supper("alert",{ title:"操作提示", msg:"请输入序号！"});
				isError=true;
				return;
			}
			var controlParam={
				controlId:cid,
				paramName:pName,
				paramNode:pNode,
				paramOrder:pOrder,
				paramType:'1'
			};
			_controlParams.push(controlParam);
		}
		
	});
	if(isError)
		return;
	
	$(".textSet").each(function(i,v){
		var cid = $(v).val();
		var pName = $("#"+cid+"Name").val();
		var pOrder = $("#"+cid+"Order").val();
		if(pName != null && pName != ""){
			if(pOrder ==null || pOrder==""){
				$.supper("alert",{ title:"操作提示", msg:"请输入序号！"});
				isError=true;
				return;
			}
			var controlParam={
				controlId:cid,
				paramName:pName,
				paramOrder:pOrder,
				paramType:'2'
			};
			_controlParams.push(controlParam);
		}
		
	});
	if(isError)
		return;
	$(".timeSet").each(function(i,v){
		var cid = $(v).val();
		var pName = $("#"+cid+"Name").val();
		var dValue = $("#"+cid+"Content").val();
		var pOrder = $("#"+cid+"Order").val();
		if(pName != null && pName != ""){
			if(dValue ==null || dValue==""){
				$.supper("alert",{ title:"操作提示", msg:"请输入时间格式！"});
				isError=true;
				return;
			}
			if(pOrder ==null || pOrder==""){
				$.supper("alert",{ title:"操作提示", msg:"请输入序号！"});
				isError=true;
				return;
			}
			var controlParam={
				controlId:cid,
				paramName:pName,
				paramNode:dValue,
				paramOrder:pOrder,
				paramType:'3'
			};
			_controlParams.push(controlParam);
		}
		
	});
	if(isError)
		return;
	var data =$('#accountForm').serialize();
	var controlIds="";
   	var paramNames="";
   	var paramTypes="";
   	var paramOrders="";
   	var paramNodes="";
   	if(_controlParams != null && _controlParams.length >0){
   		for(var i =0;i < _controlParams.length;i++){
   			controlIds+=_controlParams[i].controlId+",";
   			paramNames+=_controlParams[i].paramName+",";
   			paramTypes+=_controlParams[i].paramType+",";
   			paramOrders+=_controlParams[i].paramOrder+",";
   			paramNodes+=(_controlParams[i].paramNode!=null?_controlParams[i].paramNode:"")+",";
   		}
   		controlIds=controlIds.substring(0,controlIds.length-1);
   		paramNames=paramNames.substring(0,paramNames.length-1);
   		paramTypes=paramTypes.substring(0,paramTypes.length-1);
   		paramOrders=paramOrders.substring(0,paramOrders.length-1);
   		paramNodes=paramNodes.substring(0,paramNodes.length-1);
   	}
	data+="&controlIds="+controlIds+"&paramNames="+paramNames+"&paramTypes="+paramTypes+"&paramOrders="+paramOrders+"&paramNodes="+paramNodes;
   	$.supper("doservice", {"service":"SysParameterService.saveOneSysParameter", "ifloading":1,"options":{"type":"post","data":data}, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:closeWin});
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
function selIcon(inconName){
	$("#paramIcon").val(inconName);
}
function  closeWin(){
	$.supper("closeWin"); 
}

function controlSelShow(isShow){
	if(isShow==1){
		$("#showSel").hide();
		$("#hideSel").show();
		$(".selShow").show();
	}else{
		$("#showSel").show();
		$("#hideSel").hide();
		$(".selShow").hide();
	}
}
function controlTextShow(isShow){
	if(isShow==1){
		$("#showText").hide();
		$("#hideText").show();
		$(".textShow").show();
	}else{
		$("#showText").show();
		$("#hideText").hide();
		$(".textShow").hide();
	}
}
function controlTimeShow(isShow){
	if(isShow==1){
		$("#showTime").hide();
		$("#hideTime").show();
		$(".timeShow").show();
	}else{
		$("#showTime").show();
		$("#hideTime").hide();
		$(".timeShow").hide();
	}
}