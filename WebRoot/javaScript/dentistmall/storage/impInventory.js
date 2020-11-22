var _errorFile;
var _errorRootFile;
$(function(){
	$.supper('initUpFile',{id:"upButton",codeId:"fileCode",data:"fileType=xlsfile"}); 
});


function closeWin(){
  delFile();
  $.supper("closeWin");
}

function save(){
	var fileCode=$("#fileCode").val();
	if(fileCode ==null || fileCode==""){
		$.supper("alert",{ title:"操作提示", msg:"请上传导入文件！"});
		return;
	}
	var data ="fileCode="+fileCode;
   	$.supper("doservice", {"service":"MdInventoryService.saveImpData1", "ifloading":1,"data":data, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$("#drmb").hide();
			$("#drwj").hide();
			$("#allImp").show();
			$("#impBu").hide();
			$("#cxImp").show();
			$("#timeCount1").html(jsondata.obj.timeCount?jsondata.obj.timeCount:"0");
			_errorFile="";
			_errorRootFile="";
		}else if (jsondata.code == "2") {
			$("#drmb").hide();
			$("#drwj").hide();
			$("#sectionImp").show();
			$("#impBu").hide();
			$("#cxBu").show();
			$("#xzError").show();
			$("#rightCount").html(jsondata.obj.rightCount?jsondata.obj.rightCount:"0");
			$("#errorCount").html(jsondata.obj.errorCount?jsondata.obj.errorCount:"0");
			$("#timeCount2").html(jsondata.obj.timeCount?jsondata.obj.timeCount:"0");
			_errorFile=jsondata.obj.filePath;
			_errorRootFile=jsondata.obj.rootPath;
		}else if (jsondata.code == "3"){
			$('#showMessage').show();
			$('#showMessage').text('提示：序列号第' + jsondata.obj + '数据不正确。');
			// $.supper("alert",{ title:"操作提示", msg:"第" + jsondata.obj + "行，不存在该物料！"});
		}else if (jsondata.code == "4"){
			$.supper("alert",{ title:"操作提示", msg: jsondata.meg});
		} else{
			_errorFile="";
			_errorRootFile="";
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
		}
 	}});
}

function cxImp(){
	//回复待上传文件
	var impCode=$("#fileCode").val();
	if(impCode != null && impCode != "")
		$.supper("deleteUpFile",{fileCode:impCode,id:"upButton",codeId:"fileCode"});
	$("#drmb").show();
	$("#drwj").show();
	$("#sectionImp").hide();
	$("#allImp").hide();
	$("#impBu").show();
	$("#cxBu").hide();
	$("#xzError").hide();
	delFile();
}

function delFile(){
	if(_errorFile != null && _errorFile !=""){
		var data="filePath="+_errorFile;
		$.supper("doservice", {"service":"FileService.deletefileByPath","data":data});
	}
}

function expError(){
	if(_errorFile != null && _errorFile !=""){
		var newTab=window.open('about:blank');
		newTab.location.href=_errorRootFile;
	}
}
