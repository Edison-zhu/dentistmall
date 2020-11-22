var _valId;
var _divId;
var _listId;
var _listDivId;

function initOneUploadImg(valId, divId, isView, options) {
    var imgCode = $("#" + valId).val();
    if (imgCode == null || imgCode == "") {
        if (isView != null && isView == '1') {
        } else {
            let str = "<p class=\"add\" id=\"" + valId + "_add\"></p><input type=\"file\" id=\"" + valId + "_file\" class=\"butyfile\"";
            if(options !== undefined && options!== null && options.accept !== undefined) {
                str += ' accept="'+ options.accept +'"';
            }
            str += " onchange=\"submitOneFile(this,'" + valId + "','" + divId + "'";
            if(options !== undefined && options!== null) {
                str += ", " + options.size + ", '" + options.acceptType + "', " + options.limit + "";
            }
            str += ")\" value=\"浏览\" /></div>";
            $("#" + divId).html(str);
            $("#" + divId).addClass("addLi");
        }
    } else {
        $.supper("doservice", {
            "service": "FileService.GetOneFileInfo", "data": "fileCode=" + imgCode, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    initOneImgData(jsondata.obj, valId, divId, isView);
                } else if (jsondata.code == '2') {
                    $.supper("alert", {title: '操作提示', msg: jsondata.meg});
                }
            }
        });
    }
}

function openSelImgFile(valId) {
    $("#" + valId + "_file").trigger("click");
}

function submitOneFile(file, valId, divId, size, acceptType, limit) {
    _valId = valId;
    _divId = divId;
    var form = new FormData();
    var img = file.files[0];
    if(acceptType !== undefined && acceptType !== null && acceptType.toString().indexOf(img.type.split('/')[0]) > 0){
        $.supper("alert", {title: '操作提示', msg: '格式错误'});
        return;
    }
    if(size !== undefined && size !== null && size * 1024 <= img.size) {
        $.supper("alert", {title: '操作提示', msg: '大小不可超过' + size + 'M'});
        return;
    }
    form.append('file', file.files[0]);
    var xhr = new XMLHttpRequest();
    var att_upfile_url = $.supper('getServicePath', {
        "service": "FileService.addfile"
    });
    xhr.open("POST", att_upfile_url);
    xhr.addEventListener("load", fileOneImgUploadSuccess, false);
    xhr.send(form);//上传文件
}

function deleteOneImg(fileCode, fileId, valId, divId) {
    $.supper("doservice", {
        "service": "FileService.deletefile", "data": "fileCode=" + fileCode, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                str = "<p class=\"add\" id=\"" + valId + "_add\"></p><input type=\"file\" id=\"" + valId + "_file\" class=\"butyfile\" onchange=\"submitOneFile(this,'" + valId + "','" + divId + "')\" value=\"浏览\" /></div>";
                $("#" + divId).html(str);
                $("#" + divId).addClass("addLi");
                $("#" + valId + "_add").attr("onclick", "openSelImgFile('" + valId + "')");
                $("#" + valId).val("");
            } else if (jsondata.code == '2') {
                $.supper("alert", {title: '操作提示', msg: jsondata.meg});
            }
        }
    });
}

function fileOneImgUploadSuccess(evt) {
    var jsondata = JSON.parse(evt.target.responseText);
    if (jsondata.code == '1') {
        initOneImgData(jsondata.obj, _valId, _divId);
    } else if (jsondata.code == '2') {
        $.supper("alert", {title: '操作提示', msg: jsondata.meg});
    }
}

function initOneImgData(data, valId, divId, isView) {
    var str = "<img src=" + data.rootPath + " >";
    if (isView != null && isView == '1') {
    } else
        str += "<a href=\"javascript:deleteOneImg('" + data.fileCode + "','" + data.fileId + "','" + valId + "','" + divId + "')\" class=\"imgdel\"></a>";
    $("#" + divId).html(str);
    $("#" + divId).removeClass("addLi");
    $("#" + valId).val(data.fileCode);
}


function initListUploadImg(listId, listDivId, isView, options) {
    if (isView != null && isView == '1') {
    } else {
        var str = "<li class =\"addLi\"><p class=\"add\"></p>"
            + "<input type=\"file\" class=\"butyfile\" value=\"浏览\"";
        if(options !== undefined && options!== null && options.accept !== undefined) {
			str += ' accept="'+ options.accept +'"';
		}
        str += " onchange=\"submitListFile(this,'" + listId + "','" + listDivId + "'";
		if(options !== undefined && options!== null) {
			str += ", " + options.size + ", '" + options.acceptType + "', " + options.limit + "";
		}
		str += ")\"/></li>";
        $("#" + listDivId).html(str);
    }
    var listIds = $("#" + listId).val();
    if (listIds != null && listIds != "") {
        $.supper("doservice", {
            "service": "FileService.GetListFileInfo", "data": "fileCodes=" + listIds, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    for (var i = 0; i < jsondata.obj.length; i++)
                        initListImgData(jsondata.obj[i], listId, listDivId, '1', isView, options == undefined ? undefined : options.limit);
                } else if (jsondata.code == '2') {
                    $.supper("alert", {title: '操作提示', msg: jsondata.meg});
                }
            }
        });
    }
}

function submitListFile(file, listId, listDivId, size, acceptType, limit) {
    _listId = listId;
    _listDivId = listDivId;
    var imgs = $("#" + listId).val();
    if (limit !== undefined && limit !== null) {
        if (imgs != '' && imgs.split(',').length >= limit) {
            $.supper("alert", {title: '操作提示', msg: '最多上传' + limit + '张'});
            return;
        }
    } else if (imgs.split(',').length >= 3) {
        $.supper("alert", {title: '操作提示', msg: '最多上传三张'});
        return;
    }
    var form = new FormData();
    var img = file.files[0];
    if(acceptType !== undefined && acceptType !== null && acceptType.toString().indexOf(img.type.split('/')[0]) > 0){
		$.supper("alert", {title: '操作提示', msg: '格式错误'});
		return;
	}
    if(size !== undefined && size !== null && size * 1024 <= img.size) {
		$.supper("alert", {title: '操作提示', msg: '大小不可超过' + size + 'M'});
		return;
	}
    form.append('file', file.files[0]);
    var xhr = new XMLHttpRequest();
    var att_upfile_url = $.supper('getServicePath', {
        "service": "FileService.addfile"
    });
    xhr.open("POST", att_upfile_url);
    xhr.addEventListener("load", fileUploadListSuccess, false);
    xhr.send(form);//上传文件
}

function deleteListImgs(fileCode, fileId, listId, listDivId, limit) {
    $.supper("doservice", {
        "service": "FileService.deletefile", "data": "fileCode=" + fileCode, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $("#" + fileCode).remove();
                var listVal = $("#" + listId).val() + ",";
                listVal = listVal.replace(fileCode + ",", "");
                if (listVal != null && listVal != "")
                    listVal = listVal.substring(0, listVal.length - 1);
                $("#" + listId).val(listVal);
                if (limit !== undefined && limit !== null) {
                    if (imgs != '' && listVal.split(',').length >= limit) {
                        $('.addLi').hide();
                    } else {
                        $('.addLi').show();
                    }
                } else if (listVal.split(',').length >= 3) {
                    $('.addLi').hide();
                } else {
                    $('.addLi').show();
                }
            } else if (jsondata.code == '2') {
                $.supper("alert", {title: '操作提示', msg: jsondata.meg});
            }
        }
    });
}

function fileUploadListSuccess(evt) {
    var jsondata = JSON.parse(evt.target.responseText);
    if (jsondata.code == "1")
        initListImgData(jsondata.obj, _listId, _listDivId);
    else if (jsondata.code == '2') {
        $.supper("alert", {title: '操作提示', msg: jsondata.meg});
    }
}

function initListImgData(data, listId, listDivId, isInit, isView, limit) {
    var str = "<li id='" + data.fileCode + "'><img src=" + data.rootPath + " >";
    if (isView != null && isView == '1') {
    } else
        str += "<a href=\"javascript:deleteListImgs('" + data.fileCode + "','" + data.fileCode + "','" + listId + "','" + listDivId + "'," + limit + ")\" class=\"imgdel\"></a>";
    str += "</li>";
    str += $("#" + listDivId).html();
    $("#" + listDivId).html(str);
    if (isInit != null && isInit == '1') {
    } else {
        var listVal = $("#" + listId).val();
        if (listVal == null || listVal == "")
            listVal += data.fileCode;
        else
            listVal += "," + data.fileCode;
        $("#" + listId).val(listVal);
        if (limit !== undefined && limit !== null) {
            if (imgs != '' && listVal.split(',').length >= limit) {
                $('.addLi').hide();
            } else {
                $('.addLi').show();
            }
        } else if (listVal.split(',').length >= 3) {
            $('.addLi').hide();
        } else {
            $('.addLi').show();
        }
    }
}