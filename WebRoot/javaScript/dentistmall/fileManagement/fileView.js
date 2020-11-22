var _fileFolderId;//本相册ID
var _up_fileFolderId;//上传相册ID
var _zy_fileFolderId;//转移相册ID
var _delet_fileFolderId;//删除相册ID
var _vieFileinfo = $.supper("getProductArray", "vieFileinfo");//带过来的相册信息
var upload_File_box = $("#upload_File_box");//上传弹窗
var transfer_File_box = $("#transfer_File_box");//转移弹窗
var _fileIds = [];//选中ID数组
var _fileIdsstr;//选中ID字符串
var _file_id;//单个图片ID
var _file_id_all = '';//当前页所有图片ID
var _currentList;
var _newupload = false;

$(function () {
    _fileFolderId = _vieFileinfo.fileFolderId;
    _up_fileFolderId = _fileFolderId;
    _zy_fileFolderId = _fileFolderId;
    _delet_fileFolderId = _fileFolderId;
    getCountPics();//获取相册内图片数量
    getFileinfo();//获取相册内容
    getFileList();//获取相册图片
    // 上传图片
    initListUploadImg22("listFilecode", "imglist", null, {
        accept: 'image/png,image/jpg,image/jpeg,image/gif',
        acceptType: 'image',
        size: 3 * 1024/*KB*/
    });

    getFileNamelist2();//获取转移相册名字列表

})

// 返回
function backTo() {
    let view_url = '/jsp/dentistmall/fileManagement/fileView.jsp';
    var att_url = $.supper("getServicePath", {url: view_url});
    $.supper("closeTtemWin", {"url": att_url});
    $.supper("showTtemWin", {
        "url": '/dentistmall/jsp/dentistmall/fileManagement/fileManagement.jsp',
        "title": '商品模型相册管理'
    });
}

// 刷新
function getFileListUpdate() {
    $("#filesList").html('');
    var loading = layer.load();
    setTimeout(function () {
        layer.close(loading);
    }, 300);
    getFileList();//获取相册图片
}

// 获取相册图片
function getFileList() {
    var vdata = 'fileFolderId=' + _fileFolderId;
    $.supper('initPagination', {
            id: "Pagination",
            service: "fileService.getSysFileInfoPagerModel",
            data: vdata,
            limit: 24,
            isAjax: "1",
            "BackE": initFileList
        }
    );
}

// 初始化图片列表
function initFileList(jsondata) {
    _currentList = jsondata;
    var list = jsondata;
    var str = "";
    if (list.length > 0) {
        for (var i = 0; i < list.length; i++) {
            _file_id_all += list[i].fileId + ",";
            str += '<li>';
            str += '<div class="img" onclick="viewFile(\'' + list[i].rootPath + '\')"><img src="' + list[i].rootPath + '" /></div>';
            if (list[i].imgWidth == undefined || list[i].imgWidth == null && list[i].imgWidth == '') {
                list[i].imgWidth = '未知'
            }
            if (list[i].imgHeight == undefined || list[i].imgHeight == null && list[i].imgHeight == '') {
                list[i].imgHeight = '未知'
            }
            if (list[i].fileSize == undefined || list[i].fileSize == null && list[i].fileSize == '') {
                list[i].fileSize = '未知'
            }
            // str += ' <div class="sizeln">' + list[i].imgWidth + '*' + list[i].imgHeight + 'px (' + list[i].fileSize + 'kb)</div>';
            var _fileSize = '';
            if(list[i].fileSize < 1048576){
                _fileSize = (list[i].fileSize / 1024).toFixed(1) + 'kb' ;
            }else {
                var _fileSize0 = list[i].fileSize / 1024;
                _fileSize = (_fileSize0 / 1024).toFixed(1) + 'M' ;
            }
            str += ' <div class="sizeln">' + list[i].imgWidth + '*' + list[i].imgHeight + 'px (' + _fileSize + ')</div>';
            str += '<div class="btnbox">';
            str += '<input type="checkbox" name="selectitem_(\'' + list[i].fileId + '\')" class="selectItem"  id="selectitem_(\'' + list[i].fileId + '\')" onclick="checkItem(\'' + list[i].fileId + '\')">';
            str += '<div class="btntext" onclick="open_transfer_File_Box(\'' + list[i].fileId + '\')">转移相册</div>';
            str += '<div class="btntext" onclick="deleteOneFile(\'' + list[i].fileCode + '\')">删除图片</div>';
            str += '</div>';
            str += '</li>';
        }
    } else {
        str += '<div>暂无图片！</div>';
    }
    $("#filesList").html(str);
}

function viewFile(file) {
    var str = '<img src="' + file + '" width="600px" height="auto" />';
    layer.open({
        title: '预览图片',
        area: '600px',
        type: 1,
        content: str
    });
}

//全选当前页
function checkAllFile1(selector, fileId) {
    if (selector.checked === true) {
        $(".selectItem").prop("checked", true);
        _fileIdsstr = _file_id_all;
    } else {
        $(".selectItem").prop("checked", false);
        _fileIdsstr = '';
    }
}

function checkAllFile2(selector, fileId) {
    if (selector.checked === true) {
        $(".selectItem").prop("checked", true);
        _fileIdsstr = _file_id_all;
    } else {
        $(".selectItem").prop("checked", false);
        _fileIdsstr = '';
    }
}


//选择
function checkItem(item) {
    if (_fileIds.length == 0) {
        _fileIds.push(item);
    } else {
        var haha = false;
        for (var i = 0; i < _fileIds.length; i++) {
            if (_fileIds[i] == item) {
                _fileIds.splice(i, 1)
                haha = false;
            } else {
                haha = true;
            }
        }
        if (haha == true) {
            _fileIds = _fileIds.concat(item)
        }
    }
    _fileIdsstr = _fileIds.join(',');

}

// 打开批量转移相册
function open_transfer_File_Box_all() {
    if (_fileIdsstr == '' || _fileIdsstr == undefined || _fileIdsstr == null) {
        $.supper("alert", {title: '操作提示', msg: '请选择转移的图片！'});
        return
    } else {
        layer.open({
            title: '批量转移相册',
            area: '660px',
            type: 1,
            content: transfer_File_box
        });
    }
}


// 打开转移相册
function open_transfer_File_Box(fileId) {
    _file_id = fileId;
    _fileIdsstr = fileId;
    layer.open({
        title: '转移相册',
        area: '660px',
        type: 1,
        content: transfer_File_box
    });
}

// 获取转移相册名字列表
function getFileNamelist2() {
    var vdata = 'searchName=';
    $.supper("doservice", {
        "service": "fileService.getFileFolderInfoPagerModel", "data": vdata, "BackE": function (jsondata) {
            // $("#transfer_file_id").append("<option value=\"\">请选择转移相册</option>");
            var arr = jsondata.items;
            for (var i = 0; i < arr.length; i++) {
                if (arr[i].fileFolderId === _fileFolderId) {
                    $("#transfer_file_id").append('<option value="' + arr[i].fileFolderId + '" selected="selected">' + arr[i].fileFolderName + '</option>');
                } else {
                    $("#transfer_file_id").append('<option value="' + arr[i].fileFolderId + '">' + arr[i].fileFolderName + '</option>');
                }
            }
        }
    });
}

// 选择转移图片的相册
function select_Transfer_File_Name() {
    _zy_fileFolderId = $("#transfer_file_id").val();
}

// 确定转移
function do_transfer_File() {
    var vdata = 'fileIds=' + _fileIdsstr + '&fileFolderId=' + _zy_fileFolderId;
    $.supper("doservice", {
        "service": "fileService.updateSysFileInfoBatch", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $.supper("alert", {title: '操作提示', msg: '转移成功！'});
                getCountPics();//获取相册内图片数量
                getFileList();//获取相册图片
                close_Upload_File_Box();
                $("#allSelectFile1").prop("checked", false);
                $("#allSelectFile2").prop("checked", false);
            } else if (jsondata.code == "2") {
                $.supper("alert", {title: '操作提示', msg: '没有文件id！'});
                return
            } else if (jsondata.code == "3") {
                $.supper("alert", {title: '操作提示', msg: '没有文件夹id！'});
                return
            } else {
                $.supper("alert", {title: '操作提示', msg: '系统错误！'});
                return
            }
        }
    });
}

// 单张图片删除
function deleteOneFile(fileCode) {
    $.supper("confirm", {
        title: "删除操作", msg: "确定要删除图片吗？", yesE: function () {
            var vdata = 'fileCode=' + fileCode + '&fileFolderId=' + _delet_fileFolderId;
            $.supper("doservice", {
                "service": "fileService.deleteFile", "data": vdata, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: '操作提示', msg: '删除成功！'});
                        getCountPics();//获取相册内图片数量
                        getFileList();//获取相册图片
                    } else if (jsondata.code == "2") {
                        $.supper("alert", {title: '操作提示', msg: '文件编号有误！'});
                        return
                    } else {
                        $.supper("alert", {title: '操作提示', msg: '系统错误！'});
                        return
                    }
                }
            });
        }
    });
}

// 批量图片删除
function batch_Deletion_Files() {
    if (_fileIdsstr == '' || _fileIdsstr == undefined || _fileIdsstr == null) {
        $.supper("alert", {title: '操作提示', msg: '请选择删除的图片！'});
        return
    } else {
        $.supper("confirm", {
            title: "删除操作", msg: "确定要删除图片吗？", yesE: function () {
                var vdata = 'fileIds=' + _fileIdsstr + '&fileFolderId=' + _delet_fileFolderId;
                $.supper("doservice", {
                    "service": "fileService.deleteSysFileInfoBatch", "data": vdata, "BackE": function (jsondata) {
                        if (jsondata.code == "1") {
                            $.supper("alert", {title: '操作提示', msg: '删除成功！'});
                            getCountPics();//获取相册内图片数量
                            getFileList();//获取相册图片
                            $("#allSelectFile1").prop("checked", false);
                            $("#allSelectFile2").prop("checked", false);
                        } else if (jsondata.code == "2") {
                            $.supper("alert", {title: '操作提示', msg: '文件编号有误！'});
                            return
                        } else {
                            $.supper("alert", {title: '操作提示', msg: '系统错误！'});
                            return
                        }
                    }
                });
            }
        });
    }
}

// 获取相册内图片数量
function getCountPics() {
    var vdata = 'fileFolderId=' + _fileFolderId;
    $.supper("doservice", {
        "service": "fileService.getFileInfoCount", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $("#countPics").html(jsondata.obj)
            } else {
                $("#countPics").html(0)
            }
        }
    });
}

// 获取相册内容
function getFileinfo() {
    var vdata = 'fileFolderId=' + _fileFolderId;
    $.supper("doservice", {
        "service": "fileService.findFormFileFolderInfo", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $("#thisFileName").html(jsondata.obj.fileFolderName)
            } else {
                $("#thisFileName").html('')
            }
        }
    });
}


// 打开上传窗口
function open_Upload_File_Box() {
    $("#upload_file_id").find("option").remove();
    getFileNamelist();//获取相册名字列表
    layer.open({
        title: '上传图片',
        area: '660px',
        type: 1,
        content: upload_File_box
    });
}

// 关闭上传窗口
function close_Upload_File_Box() {
    layer.closeAll();
}

// 获取相册名字列表
function getFileNamelist() {
    var vdata = 'searchName=';
    $.supper("doservice", {
        "service": "fileService.getFileFolderInfoPagerModel", "data": vdata, "BackE": function (jsondata) {
            // $("#upload_file_id").append("<option value=\"\">请选择上传相册</option>");
            var arr = jsondata.items;
            for (var i = 0; i < arr.length; i++) {
                if (arr[i].fileFolderId === _fileFolderId) {
                    $("#upload_file_id").append('<option value="' + arr[i].fileFolderId + '" selected="selected">' + arr[i].fileFolderName + '</option>');
                } else {
                    $("#upload_file_id").append('<option value="' + arr[i].fileFolderId + '">' + arr[i].fileFolderName + '</option>');
                }
            }
        }
    });
}

// 选择上传图片的相册
function select_Upload_File_Name() {
    _up_fileFolderId = $("#upload_file_id").val();
}


// 上传图片
var _listId;
var _listDivId;

function initListUploadImg22(listId, listDivId, isView, options) {
    if (isView != null && isView == '1') {
    } else {
        var str = "<li ><form id='videoForm'><i class=\"layui-icon layui-icon-picture\"></i>&nbsp;&nbsp;选择上传文件"
            + "<input id='uploadPicinput'  multiple=\"multiple\"  type=\"file\" class=\"butyfile\" value=\"浏览\"";
        if (options !== undefined && options !== null && options.accept !== undefined) {
            str += ' accept="' + options.accept + '"';
        }
        str += " onchange=\"submitListFile22(this,'" + listId + "','" + listDivId + "'";
        if (options !== undefined && options !== null) {
            str += ", " + options.size + ", '" + options.acceptType + "', " + options.limit + "";
        }
        str += ")\"/></form></li>";
        $("#" + listDivId).html(str);
    }
}

var _xhr;
var _form;

function submitListFile22(file, listId, listDivId, size, acceptType, limit) {
    _listId = listId;
    _listDivId = listDivId;
    _form = new FormData();
    if (file.files.length > 9) {
        $.supper("alert", {title: '操作提示', msg: '一次最多同时上传9张！'});
        return
    } else {
        var picnum = '已选' + file.files.length + '张图片';
        $("#selectPicNum").html(picnum);
        for (var i = 0; i < file.files.length; i++) {
            _form.append('file', file.files[i]);
        }
        _xhr = new XMLHttpRequest();
        _newupload = true;
    }
}

// 上传图片确定按钮
function do_Upload_File() {
    if (_newupload == true) {
        if ($("#uploadPicinput")[0].files.length <= 0) {
            $.supper("alert", {title: '操作提示', msg: '请选择需上传的图片！'});
            return
        } else {
            var vdata = 'fileFolderId=' + _up_fileFolderId;
            var att_upfile_url = $.supper('getServicePath', {
                "service": "FileService.addFile", "data": vdata
            });
            _xhr.open("POST", att_upfile_url);
            _xhr.addEventListener("load", fileUploadListSuccess, false);
            _xhr.send(_form);//上传文件
        }
    } else {
        $.supper("alert", {title: '操作提示', msg: '请选择需上传的图片！'});
        return
    }
}

function fileUploadListSuccess(evt) {
    var jsondata = JSON.parse(evt.target.responseText);
    if (jsondata.code == "1") {
        getCountPics();//获取相册内图片数量
        getFileList();//获取相册图片
        $.supper("alert", {title: '操作提示', msg: '上传成功！'});
        _newupload = false;
        $("#selectPicNum").html('');
        layer.closeAll();
    } else if (jsondata.code == '2') {
        $.supper("alert", {title: '操作提示', msg: jsondata.meg});
    } else {
        $.supper("alert", {title: '操作提示', msg: '系统错误！'});
    }
}








