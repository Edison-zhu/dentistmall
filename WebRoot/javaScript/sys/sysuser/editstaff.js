$(function () {

});

function closeWin() {
    $.supper("closeWin");
}


var checkA = 0;

function checkAccount() {
    var account = $("#account").val();
    if (account != null && account != "") {
        var data = "account=" + account;
        var suiId = $("#suiId").val();
        if (suiId != null && suiId != "") {
            data += "&suiId=" + suiId;
        }
        $.supper("doservice", {
            "service": "SysUserService.checkSysUser", "data": data, "BackE": function (jsondata) {
                if (jsondata.code == "2") {
                    $("#accountCheck").show();
                    checkA = 1;
                } else {
                    $("#accountCheck").hide();
                    checkA = 0;
                }
            }
        });
    } else {
        checkA = 0;
        $("#accountCheck").hide();

    }
}

function save() {
    var account = $("#account").val();
    if (account == null || account == "") {
        $.supper("alert", {title: "操作提示", msg: "用户账号不能为空！"});
        return;
    }
    if (checkA == 1) {
        $.supper("alert", {title: "操作提示", msg: "用户名称已经存在！"});
        return;
    }
    var userName = $("#userName").val();
    if (userName == null || userName == "") {
        $.supper("alert", {title: "操作提示", msg: "用户名称不能为空！"});
        return;
    }
    var userRoles = "";
    $('input[name="userRoles"]:checked').each(function () {
        userRoles += $(this).val() + ",";
    });
    if (userRoles == null || userRoles == "") {
        $.supper("alert", {title: "操作提示", msg: "请选择用户角色！"});
        return;
    }
    userRoles = userRoles.substring(0, userRoles.length - 1);
    $("#userRole").val(userRoles);

    $('input[name="openSecurity"]:checked').each(function () {
        $("#checkboxThreeInput").val('1');
    });
    var data = $('#accountForm').serialize();
    $.supper("doservice", {
        "service": "SysUserService.saveSysUser",
        "ifloading": 1,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: closeWin});
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });

}

function hidclick() {
    if ($("#hid1").is(':hidden')) {
        $("#hid1").show();
        $("#hid1s").show();
    } else {
        $("#hid1").hide();
        $("#hid1s").hide();
        $("#checkboxThreeInput").prop("checked", false);
        $("#checkboxThreeInput").val('');
    }
}
function labelClick(){
    var descCheck = $("#checkboxThreeInput").is(":checked");
    console.log(descCheck)
    if (descCheck == false){
        $("#hid1s").show();
    }else {
        $("#hid1s").hide()
    }
    // $('input[name="openSecurity"]:checked').each(function () {
    //     $("#hid1s").show();
    // });
}

// 重置安全码
function resetSafetyCode(suiId) {
    var data = "suiId=" + suiId;
    $.supper("doservice", {
        "service": "sysUserService.updateSysUser", "data": data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $.supper("alert", {title: "操作提示", msg: "安全码重置成功,新安全码为:123456！"});
                // $("#hids2").show()
            } else {
                $.supper("alert", {title: "操作提示", msg: "安全码重置失败！"});
            }
        }
    });
}
