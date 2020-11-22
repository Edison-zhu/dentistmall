var _listId;
var _listDivId;

function initListUploadImg22(listId, listDivId, isView, options) {
    if (isView != null && isView == '1') {
    } else {
        var str = "<li ><i class=\"layui-icon layui-icon-picture\"></i>&nbsp;&nbsp;选择上传文件"
            + "<input  multiple=\"multiple\"  type=\"file\" class=\"butyfile\" value=\"浏览\"";
        if (options !== undefined && options !== null && options.accept !== undefined) {
            str += ' accept="' + options.accept + '"';
        }
        str += " onchange=\"submitListFile22(this,'" + listId + "','" + listDivId + "'";
        if (options !== undefined && options !== null) {
            str += ", " + options.size + ", '" + options.acceptType + "', " + options.limit + "";
        }
        str += ")\"/></li>";
        $("#" + listDivId).html(str);
    }
}

function submitListFile22(file, listId, listDivId, size, acceptType, limit) {
    _listId = listId;
    _listDivId = listDivId;
    var form = new FormData();
    if (file.files.length > 9) {
        $.supper("alert", {title: '操作提示', msg: '一次最多同时上传9张！'});
        return
    } else {
        for (var i = 0; i < file.files.length; i++) {
            form.append('file', file.files[i]);
        }
        var xhr = new XMLHttpRequest();
        var att_upfile_url = $.supper('getServicePath', {
            "service": "FileService.addFile"
        });
        xhr.open("POST", att_upfile_url);
        xhr.addEventListener("load", fileUploadListSuccess, false);
        xhr.send(form);//上传文件
    }
}

function fileUploadListSuccess(evt) {
    var jsondata = JSON.parse(evt.target.responseText);
    console.log('上传回调----------------->', jsondata)
    if (jsondata.code == "1") {
        // $.supper("alert", {title: '操作提示', msg: '上传成功！'});
    } else if (jsondata.code == '2') {
        $.supper("alert", {title: '操作提示', msg: jsondata.meg});
    }
}
