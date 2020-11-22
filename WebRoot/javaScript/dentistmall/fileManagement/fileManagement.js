var _all_datagrid_height;
var _all_win_datagrid;
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_queryAction = 'fileService.getFileFolderInfoPagerModel';//相册列表接口
var _fileFolderId = '';
var _edit = false;

var _DataGrid = {
    cols: [
        {title: '编号', sortable: true, name: 'fileFolderId', width: 10, align: 'center'},
        {title: '相册名称', sortable: true, name: 'fileFolderName', width: 80, align: 'center',},
        {title: '相册封面', name: 'imgPath', width: 40, align: 'center', renderer: getPicCover},
        {title: '图片数量', sortable: true, name: 'imgCount', width: 40, align: 'center'},
        {title: '描述', name: 'fileFolderDescribe', width: 150, align: 'center'},
        {title: '操作', name: 'checkInventory', width: 100, align: 'center', renderer: control},
    ]
    , remoteSort: false
    , multiSelect: true
    // , width: '100%'
    , height: _all_datagrid_height
    , gridtype: '2'
    , nowrap: true
    , checkCol: false
    , url: getMMGridUrl()
    , mmPaginatorOpt: _all_win_datagrid_pg
}

// 相册封面
function getPicCover(val, item, rowIndex) {
    let str = '';
    if (item.imgPath != '' && item.imgPath != null && item.imgPath != undefined) {
        str = '<img src="' + item.imgPath + '" style="width: 40px; height: 40px;" />';
    } else {
        str = '<span>暂无封面</span>';
    }
    return str;
}

// 操作列
function control(val, item, rowIndex) {
    let str = '';
    str += "<a onclick=\"goto_View_File(" + item.fileFolderId + ")\"  class='btn btn-success  btn-xs' style='width: 60px'>查看</a>&nbsp;&nbsp;";
    str += "<a onclick=\"editFile(" + item.fileFolderId + ")\"  class='btn btn-success  btn-xs' style='width: 60px'>编辑</a>&nbsp;&nbsp;";
    if (item.imgCount > 0) {
        str += '';
    } else {
        str += "<a onclick=\"deleteFile(" + item.fileFolderId + ")\"  class='btn btn-success  btn-xs' style='width: 60px'>删除</a>&nbsp;&nbsp;";
    }
    return str;
}

// 查看相册
function goto_View_File(fileFolderId) {
    let view_url = '/jsp/dentistmall/fileManagement/fileView.jsp';
    let att_title = "商品相册查看及维护";
    let att_userType = "1";
    var att_url = $.supper("getServicePath", {url: view_url});
    $.supper("setProductArray", {"name": "vieFileinfo", "value": {fileFolderId: fileFolderId, url: att_url}});
    $.supper("showTtemWin", {"url": att_url, "title": att_title});
}

// 编辑相册
function editFile(fileFolderId) {
    var vdata = 'fileFolderId=' + fileFolderId;
    $.supper("doservice", {
        "service": "fileService.findFormFileFolderInfo", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $("#file_name").val(jsondata.obj.fileFolderName);
                $("#file_desc").val(jsondata.obj.fileFolderDescribe);
                _fileFolderId = fileFolderId;
                _edit = true;
                layer.open({
                    title: '编辑相册',
                    area: '660px',
                    type: 1,
                    content: _add_File_box
                });
            } else {
                $.supper("alert", {title: "操作提示", msg: "系统错误！"});
            }
        }
    });
}

// 删除相册
function deleteFile(fileFolderId) {
    layer.alert('确定要删除此相册吗?', {
            title: '相册删除',
            icon: 1,
            skin: 'layer-ext-moon',
            btn: ['确定', '取消']
        },
        function (index) {
            var vdata = 'fileFolderId=' + fileFolderId;
            $.supper("doservice", {
                "service": "fileService.deleteFileFolderInfo", "data": vdata, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "删除成功！"});
                        main_search();
                        layer.close(index);
                    } else if (jsondata.code == "2") {
                        $.supper("alert", {title: "操作提示", msg: "该相册下有图片不可删除！"});
                        layer.close(index);
                    } else {
                        $.supper("alert", {title: "操作提示", msg: "删除失败！"});
                        layer.close(index);
                    }
                }
            });
        }
    )
}

//获取数据列表
function getMMGridUrl() {
    // let data = $('#searchName').val();
    let data = 'searchName=' + $('#searchName').val();
    let att_url = $.supper("getServicePath", {"service": _all_queryAction, "data": data});
    return att_url;
}

function main_search() {
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
    // _all_win_datagrid.on('loadSuccess', function (e, data) {
    //   console.log('111------------->',data)
    // });

}

function initCount() {
    $.supper("doservice", {
        "service": "fileService.getFileFolderInfoPagerModel", "data": '', "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                let val = jsondata.obj;
            }
        }
    });
}

$(function () {
    _all_datagrid_height = $(window).height() - 400;
    _DataGrid.height = _all_datagrid_height;
    _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);
    main_search();
})

var _add_File_box = $("#add_File_box");

// 打开新建相册
function open_Add_File_Box() {
    _edit = false;
    layer.open({
        title: '新建相册',
        area: '660px',
        type: 1,
        content: _add_File_box
    });
}

// 关闭新建相册
function close_Add_File_Box() {
    layer.closeAll();
}

// 新建相册 /修改相册
function Add_File() {
    var _file_name = $("#file_name").val();
    var _file_desc = $("#file_desc").val();
    if (_file_name === '' || _file_name === null || _file_name === undefined) {
        layer.msg('相册名称不能为空!');
        return false
    }
    var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
    if (pattern.test(_file_name)) {
        $.supper("alert", {title: "操作提示", msg: "相册名称不可输入特殊字符！"});
        return true;
    }
    if (_file_name.length < 2) {
        layer.msg('相册名称不可少于2个字!');
        return false
    }
    if (_file_name.length > 30) {
        layer.msg('相册名称不可多于30个字!');
        return false
    }
    // if (pattern.test(_file_desc)) {
    //     $.supper("alert", {title: "操作提示", msg: "相册描述不可输入特殊字符！"});
    //     return true;
    // }
    if (_file_desc.length > 50) {
        layer.msg('相册描述不可多于50个字!');
        return false
    }
    if (_edit == true) {
        // console.log('修改-----------------------')
        var vdata = "fileFolderName=" + _file_name + "&fileFolderDescribe=" + _file_desc + "&fileFolderId=" + _fileFolderId;
        $.supper("doservice", {
            "service": "fileService.updateFileFolderInfo", "data": vdata, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    $.supper("alert", {title: "操作提示", msg: "修改成功！", BackE: main_search});
                    layer.closeAll();
                    $("#file_name").val('');
                    $("#file_desc").val('');
                } else {
                    $.supper("alert", {title: "操作提示", msg: "修改失败！"});
                }
            }
        });
    } else {
        // console.log('新增-----------------------')
        var vdata = "fileFolderName=" + _file_name + "&fileFolderDescribe=" + _file_desc;
        $.supper("doservice", {
            "service": "fileService.saveSysFileFolderInfo", "data": vdata, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    $.supper("alert", {title: "操作提示", msg: "添加成功！", BackE: main_search});
                    layer.closeAll();
                    $("#file_name").val('');
                    $("#file_desc").val('');
                } else {
                    $.supper("alert", {title: "操作提示", msg: "添加失败！"});
                }
            }
        });
    }
}

