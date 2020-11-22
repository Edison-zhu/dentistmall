
$(function () {
	$.supper("initUpFile", {id:"uploadButton", codeId:"websitIconFilecode"});
	$.supper("initUpFile", {id:"bg_uploadButton01", codeId:"backgroundPictureFileCode01"});
	$.supper("initUpFile", {id:"bg_uploadButton02", codeId:"backgroundPictureFileCode02"});
	$.supper("initUpFile", {id:"bg_uploadButton03", codeId:"backgroundPictureFileCode03"});
});

function closeWin() {
	$.supper("closeWin");
}
function validate() {
	var websitName = $("#websitName").val();
	var websitecode = $("#websitecode").val();
	var rootPath = $("#rootPath").val();
	var indexPath = $("#indexPath").val();
	var websitIconFilecode = $("#websitIconFilecode").val();
	var versionInformation = $("#versionInformation").val();
	var backgroundPictureFileCode01 = $("#backgroundPictureFileCode01").val();
	var backgroundPictureFileCode02 = $("#backgroundPictureFileCode02").val();
	var backgroundPictureFileCode03 = $("#backgroundPictureFileCode03").val();
	if (websitName ==null || websitName=="") {
		$.supper("alert",{ title:"操作提示", msg:"网站名称不能为空！"});
		return false;
	}
	if (websitIconFilecode ==null || websitIconFilecode=="") {
		$.supper("alert",{ title:"操作提示", msg:"网站图标不能为空！"});
		return false;
	}
	
	if (rootPath ==null || rootPath=="") {
		$.supper("alert",{ title:"操作提示", msg:"根目录地址不能为空！"});
		return false;
	}
	
	if (indexPath ==null || indexPath=="") {
		$.supper("alert",{ title:"操作提示", msg:"首页地址不能为空！"});
		return false;
	}
	
	if (versionInformation ==null || versionInformation=="") {
		$.supper("alert",{ title:"操作提示", msg:"版本信息不能为空！"});
		return false;
	}
	
	return true;
}

function save() {
	if (validate()) {
		var data =$('#accountForm').serialize();
		$.supper("doservice", {"service":"SysWebsiteInfoService.saveSysWebsiteInfo", "ifloading":1,"data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:closeWin});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
		}});
	}

}


