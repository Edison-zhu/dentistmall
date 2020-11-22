var _all_accountForm = $("#accountForm");
var _all_div_hidden = $("#win_form_edithidden");
var _all_div_body = $("#win_form_body");
var _all_div_img = $('#win_form_img');
var _all_win_tools_but = $("#win_edit_toolbar");

/***
 * 修改部分begin
 */
var _rbbId;
var _all_table_Id = "sniId";
var _all_saveAction = "SysNoticeInfoService.saveOrUpdateObject";
var _all_questAction = "SysNoticeInfoService.findFormObject";
var _saveForm = {
    lineNum: 3,
    columnNum: 2,
    control: [],
    groupTag: [],
    hiddenitems: [
        {name: "sniId", id: "sniId", value: "", type: "hidden"},
        {title: '创建时间', name: 'createDate', type: "hidden"},
        {title: '创建人', name: 'createRen', type: "hidden"},
        {title: '修改时间', name: 'editDate', type: "hidden"},
        {title: '修改人', name: 'editRen', type: "hidden"}
    ],
    items: [
        {
            title: '公告编号',
            name: 'sniCode',
            placeholder: "公告编号",
            width: 120,
            align: 'center',
            readOnly: true,
            prefixCode: "SNI"
        },
        {title: '标题', name: 'title', placeholder: "标题", width: 80, align: 'center', ariaRequired: true},
        {
            title: '状态',
            name: 'state',
            type: "select",
            width: 80,
            align: 'center',
            impCode: "PAR170926033732594",
            ariaRequired: true
        },
        {
            title: '发布渠道',
            name: 'belongType',
            type: "select",
            width: 80,
            align: 'center',
            ariaRequired: true,
			data: [{id: 1, name: 'PC商城'}, {id: 2, name: '小程序商城'}, {id: 3, name: '代理商小程序'}]
        },
        // {
        //     title: '活动封面',
        //     name: 'noticeImage',
		// 	type: 'userdefined',
		// 	width: 80,
        //     align: 'center',
		// 	renderer: rendererLogo
        // }
    ]
};

var _saveImgForm = {
	lineNum: 1,
	columnNum: 1,
	control: [],
	groupTag: [],
	hiddenitems: [
	],
	items: [
		{
			title: '活动封面',
			name: 'noticeImage',
			type: 'userdefined',
			width: 80,
			align: 'center',
			renderer: rendererLogo
		}
	]
};

function rendererLogo(val,item,rowIndex) {
	let str = '';
	str = '<div id="asImg" class="asImg">' +
		'<div class="ibox float-e-margins" style="overflow:hidden;margin-top:5px">' +

		'<div class="ibox-content">' +
		'<div class="form-group" styel="">' +
		'<div class="awards-imgs">' +
		'<ul class="imgul f-cb" id="imglist">' +
		'</ul>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'<div class="ibox-title" style="border:0px">' +
		'<h5><span style="color: lightgrey;margin-left: 5px">格式：png,jpg,gif;大小:不超过3M</span>' +
		'</h5>' +
		'</div>' +

		'<input type="hidden" id="lessenFilecode" name="lessenFilecode"/>' +
		'<input type="hidden" id="listFilecode" name="noticeImage"' +
		' value=""/>' +
		'</div>' +
		'</div>';
	return str;
}


var _Toolbar = {
    toolBarId: "win_edit_toolbar",
    items: [
        {title: "提交", id: "win_but_save", icon: "save", colourStyle: "primary", clickE: save},
        {title: "关闭", id: "win_but_add", icon: "close", colourStyle: "default", clickE: closeWin}
    ]
};

function initFormHidden() {
    _all_div_hidden.xform('createhidden', _saveForm.hiddenitems);

}


function initForm() {
    _rbbId = $.supper("getParam", _all_table_Id);
    var att_data = _all_table_Id + "=" + _rbbId;
    _all_div_body.xform('createForm', _saveForm);
	// _all_div_img.xform('createForm', _saveImgForm);
	if (_rbbId != null) {
		_all_accountForm.xform('loadAjaxForm', {'ActionUrl': _all_questAction, "data": att_data});
	}

}


function initToolBar() {
    _all_win_tools_but.xtoolbar('create', _Toolbar);
}

$(function () {
    initFormHidden();
    initForm();
    initToolBar();
    KindEditor_init();
    //优化按钮
    $("#win_but_save").css("width", "95px");
    $("#win_but_add").css("width", "95px");

	initOneUploadImg("lessenFilecode","lessenFileDiv");
	initListUploadImg("listFilecode","imglist", null, {accept: 'image/png,image/jpg,image/jpeg,image/gif', acceptType: 'image', size: 3 * 1024/*KB*/});
});

var editorObj = new Array();

function KindEditor_init(keval) {
    var att_keval_afterChange = keval ? keval.afterChange : null;
    $.each($(".KindEditor"), function (i, n) {
        var k_name = n.id;
        if (k_name) {
            var editor = KindEditor.create('textarea[id="' + k_name + '"]', {
                cssData: 'body {font-family: "微软雅黑"; font: 14px/1.5}',
                cssPath: '/dentistmall/js/plugins/kindeditor/plugins/code/prettify.css',
                uploadJson: '/dentistmall/jsp/upload_json.htm',
                fileManagerJson: '/dentistmall/jsp/file_manager_json.htm',
                allowFileManager: true,
                autoHeightMode: false,
                afterChange: att_keval_afterChange,
                afterCreate: function () {
                    this.sync();
                },
                afterBlur: function () {
                    this.sync();
                }
            });
            editorObj[i] = editor;
        }
    });

}

function all_sync() {
    $.each(editorObj, function (i, n_editor) {
        n_editor.sync();
    });
}

function getKindEditor(att_id) {
    $.each($(".X_kindereditor"), function (i, n) {
        var k_name = n.id;
        if (k_name == att_id) {
            return editorObj[i];
        }
    });
}

function closeWin() {
    $.supper("closeWin");
}

function save() {
    if (_all_div_body.xform('checkForm')) {
        var data = _all_accountForm.serialize();
        console.log(data)
        // var data1 =
        $.supper("doservice", {
            "service": _all_saveAction,
            "ifloading": 1,
            "options": {"type": "post", "data": data},
            "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    $.supper("alert", {
                        title: "操作提示",
                        msg: "操作成功！",
                        BackE: closeWin
                    });
                } else
                    $.supper("alert", {
                        title: "操作提示",
                        msg: "操作失败！"
                    });
            }
        });
    }
}

 
