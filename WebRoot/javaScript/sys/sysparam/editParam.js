var _sysSparId;
var _sparId;
var _controlParamList=[];
$(function(){  
	_sysSparId= $.supper("getParam", "sysSparId");
	_sparId= $.supper("getParam", "sparId");
	initControlParam();
});
function initControlParam(){
	var data = "suiId="+_sysSparId+"&paramSource=1";
	$.supper("doservice", {"service":"SysControlParamService.getSysControlParamList","data":data,"BackE":function (jsondata) {
		if(jsondata.code ==1 && jsondata.obj != null && jsondata.obj.length >0){
			var strs=[];
			_controlParamList=jsondata.obj;
			for(var i =0 ;i < _controlParamList.length;i++){
				var cid = _controlParamList[i].controlId;
				var pName = _controlParamList[i].paramName;
				var pNode = _controlParamList[i].paramNodeJson;
				var pType = _controlParamList[i].paramType;
				if(pType=="1"){//如果是select选项
					var options=pNode.split(",");
					var str="";
					str +="<label class=\"control-label col-sm-2\">"+pName+"</label>";
					str +="<div class=\"controls col-sm-3\">";
					str += "<select class=\"form-control\" name=\""+cid+"\" id=\""+cid+"\">";
					for(var j=0;j<options.length;j++){
						var ss=options[j].split(":");
						str += "<option value=\""+ss[0]+"\">"+ss[1]+"</option>";
					}
					str +="</select></div>";
					strs.push(str);
				}else if(pType=="2"){//如果是输入框
					var str="";
					str +="<label class=\"control-label col-sm-2\">"+pName+"</label>";
					str +="<div class=\"controls col-sm-3\">";
					str += "<input type=\"text\" class=\"form-control\" name= \""+cid+"\" id=\""+cid+"\"  placeholder=\""+pName+"\">";
					str +="</div>";
					strs.push(str);
				}else if(pType=="3"){//时间选择框
					var str="";
					str +="<label class=\"control-label col-sm-2\">"+pName+"</label>";
					str +="<div class=\"controls col-sm-3\">";
					str += "<input type=\"text\" class=\"form-control\" name= \""+cid+"\" id=\""+cid+"\"  readonly=true placeholder=\""+pName+"\""
							+" onclick=\"laydate({istime: true, format: '"+pNode+"'})\">";
					str +="</div>";
					strs.push(str);
				}
			}
			var sHtml="";
			for(var i =0 ;i < strs.length;i++){
				if(i%2==0)
					sHtml+="<div class=\"form-group\">";
				sHtml+=strs[i];
				if(i%2!=0 || i==(strs.length-1))
					sHtml+="</div>";
			}
			$("#formBody").append(sHtml);
		}
		initData();
	}});
}
function initData(){
	var data = "sysSparId="+_sysSparId;
	if(_sparId != null && _sparId !="")
		data +="&sparId="+_sparId;
	$.supper("doservice", {"service":"SysParameterService.findSysParameter","data":data,"BackE":function (jsondata) {
		$.supper("initForm",{id:"accountForm",obj:jsondata});
		if(_controlParamList != null && _controlParamList.length>0){
			$.each(jsondata,function(name,value) {
				for(var i =0 ;i < _controlParamList.length;i++){
					if(name==_controlParamList[i].controlId){
						if(value != null && _controlParamList[i].paramType != '3'){
							$("#"+_controlParamList[i].controlId).val(value);
							break;
						}else if(value != null && _controlParamList[i].paramType == '3'){
							$("#"+_controlParamList[i].controlId).val(Date.formatLaydate(new Date(value.replace(/-/g,"/")),_controlParamList[i].paramNode));
							break;
						}
					}
				}
			});
			
		}
		
	}});
}

function save(){
	var paramName = $("#paramName").val();
   	if(paramName == null || paramName == ""){
   		$.supper("alert",{ title:"操作提示", msg:"编码名称不能为空！"});
   		return;
   	}
   	var paramValue = $("#paramValue").val();
   	if(paramValue == null || paramValue == ""){
   		$.supper("alert",{ title:"操作提示", msg:"编码值不能为空！"});
   		return;
   	}
   	var paramOrderNumber = $("#paramOrderNumber").val();
   	if(paramOrderNumber == null || paramOrderNumber == ""){
   		$.supper("alert",{ title:"操作提示", msg:"编码序号不能为空！"});
   		return;
   	}
   	var data=$("#accountForm").serialize();
   	$.supper("doservice", {"service":"SysParameterService.saveSysParameter", "ifloading":1,"options":{"type":"post","data":data}, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:closeWin});
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
function  closeWin(){
	$.supper("closeWin"); 
}