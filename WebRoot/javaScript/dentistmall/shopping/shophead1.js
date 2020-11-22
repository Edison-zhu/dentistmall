$.supper('setbasepath');
var fLogin;
var _searchType = 'mat';
var _isLayer = false;
var tt = 1;
var tl;
var noticeSpeed = 500;
var colleTime = 5000;
var _save_carts_action = 'ShoppingService.addCarts';
var _save_cartscount_action = 'ShoppingService.getCartsCountAndPrice';
var _check_cart_action = 'ShoppingService.checkCarts';



$(document).ready(function () {
    tl = $(".m-notice li.list").length;
    var $noticelist = $(".m-notice .noticelist");
    $(".m-notice  li.list:first").clone().appendTo($noticelist);
    var tx = setInterval(rollnews, colleTime);

    //
    // if (_isCar == '1')
    //     $(".shopping-cart").show();
    // else if (_isCar == '2')
    //     $(".to-index-page").show();
    // else if (_isCar == '3')
    //     $(".to-shopping-cart").show();
    // else if (_isCar == '4')
    //     $(".to-order-list").show();
    $(".select-box-div").click(function () {
        var selectInput = $(this).find('.select-box-input');
        var selectUl = $(this).find("ul");
        if (selectUl.css("display") == "none") {
            if (selectUl.height() > 200) {
                selectUl.css({height: "200" + "px", "overflow-y": "scroll"})
            }
            selectUl.fadeIn("100");
            selectUl.hover(function () {
            }, function () {
                selectUl.fadeOut("100");
            });
            selectUl.find("li").click(function () {
                selectInput.val($(this).text());
                selectUl.fadeOut("100");
            }).hover(function () {
                $(this).addClass("hover");
            }, function () {
                $(this).removeClass("hover");
            });
        } else {
            selectUl.fadeOut("fast");
        }
    });
    $('body').css("padding-top", "142px");
    var $glogin = $('#g-login');
    var $mask = $('#g-loginId');
    fLogin = {
        /* 打开弹窗 */
        'openLogin': function () {
            $glogin.css({'top': '100px', 'left': ($(window).width() - $glogin.width()) / 2 + 'px'}).fadeIn();
            $mask.fadeIn();
        },
        /* 关闭弹窗 */
        'closeLogin': function () {
            $glogin.fadeOut();
            $mask.fadeOut();
        }
    };
    $('#m-login').click(fLogin.openLogin); //打开弹窗监听
    $('#loginClose').click(fLogin.closeLogin); //关闭弹窗监听
    countCar();


    // 设置自定义历史记录
    $('#searchName').on('focus', function (e) {
        var searchNames = cookie.get(_searchType);
        var nameArray = searchNames == undefined ? [] : searchNames.split(',');
        var str = '';
        var src = '';
        nameArray.forEach(function (value) {
            if (_searchType == 'supplier') {
                src = "hzcj.htm?searchName=" + value;
            } else if (_searchType == 'mat') {
                src = "liebiao.htm?searchName=" + value;
            }
            str += '<li><a onclick="setSearchName($(this).text())" href="' + src + '">' + value + '</a></li>';
        });
        $('.hidden-search').show();
        $('#hidden-search').html(str);
        var hiddenSearch = $('.hidden-search').css('height');
        hiddenSearch = hiddenSearch.substring(0, hiddenSearch.length - 2);
        if(hiddenSearch >= '200'){
            $('.hidden-search').css('height', '300px');
        }
    });
    $('#searchName').on('blur', function () {
        setTimeout(function () {
            $('#hidden-search-div').hide();
        }, 100);
    });
    var hiddenWidth = $('#input-container').css('width')
    hiddenWidth = hiddenWidth.substring(0, hiddenWidth.length - 2);
    hiddenWidth -= 18;
    $('#hidden-search').css('width', hiddenWidth + 'px');
    regreshViewFavAndCarts();
    countCollect1();

});
// function countCollect1() {
//     alert(1234);
// }

function regreshViewFavAndCarts() {
    setTimeout(function () {
        $('span[id^="index-new-product-fav-"]').each(function (index, item) {
            var wmsMId = $(this).attr('name');
            checkViewFav(wmsMId, 'index-new-product-fav');
        });
        $('span[id^="index-product-operation-fav-"]').each(function (index, item) {
            var wmsMId = $(this).attr('name');
            checkViewFav(wmsMId, 'index-product-operation-fav');
        });
        $('span[id^="index-new-product-cart-"]').each(function (index, item) {
            var wmsMId = $(this).attr('name');
            if (wmsMId == null || wmsMId == undefined || wmsMId == '') {
                return;
            }
            setCar(wmsMId, 'index-new-product-cart', true);
        });
        $('span[id^="index-product-operation-cart-"]').each(function (index, item) {
            var wmsMId = $(this).attr('name');
            if (wmsMId == null || wmsMId == undefined || wmsMId == '') {
                return;
            }
            setCar(wmsMId, 'index-product-operation-cart', true);
        });
    }, 200);
}
function setSearchName(text) {
    $('#searchName').val(text);
}
function rollnews() {
    var $noticelist = $(".m-notice .noticelist");
    if (tt == tl + 1) {
        $noticelist.css("top", "0px");
        tt = 1;
        $noticelist.animate({"top": -44 + "px"}, noticeSpeed, function () {
            tt++
        });
    } else {
        $noticelist.animate({"top": tt * -44 + "px"}, noticeSpeed, function () {
            tt++;
        });
    }
}

function ReceptionLogin() {
    var userName = $("#userName").val();
    var password = $("#password").val();
    if (userName == null || userName == "") {
        $("#errorId").show();
        $("#errorId").text("请输入用户名！");
        return;
    }
    if (password == null || password == "") {
        $("#errorId").show();
        $("#errorId").text("请输入密码！");
        return;
    }
    var data = $('#loginForm').serialize();
    $.supper("doservice", {
        "service": "SysLoginService.shoppingLogin",
        "options": {"data": data, "type": "post"},
        "dotry": "true",
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                location.reload();
                //window.open("index.htm");
            } else {
                $("#errorId").show();
                $("#errorId").text(jsondata.meg);
            }
        }
    });
}

function getOut() {
    $.supper("doservice", {
        "service": "SysLoginService.ReceptionCancellation", "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                if (_isCar == 2)
                    window.location.href = "index.htm";
                else
                    location.reload();
            }
        }
    });
}

function countCar() {
    if (isLoging() === false) {
        return;
    }
	var isLogin = $("#isLogin").val();
    $.supper("doservice", {
        "service": _save_cartscount_action, "BackE": function (jsondata) {
            var items = jsondata.items;
            if (items.length <= 0) {
                $("#destoon_cart").html("0");
                $("#destoon_money_cart").html("0");
                return;
            }
            var item = items[0];
            var totalCount = item.total_count;
            var price = item.price;
            if(price === undefined || price === null || price === ''){
                price = 0;
            }
            if (isLogin == "1") {
                $("#destoon_money_cart").html(toDecimal2(price));
                $("#destoon_cart").html(totalCount);
            } else {
                $("#destoon_cart").html("0");
                $("#destoon_money_cart").html("0");
            }
        }
    });
}

function showLogin() {
    fLogin.openLogin();
}

//检查是否登录
function isLoging() {
    var isLogin = $("#isLogin").val();
    if (isLogin != "1") {
        return false;
    }
    return true;
}

// 更新收藏状态
function updateFavorites(wmsMiId, elemId) {
    var data = "wmsMiId=" + wmsMiId;
    var uVF = this.updateViewFavorites;
    $.supper("doservice", {
        "service": "MdFavoritesService.saveMdFavorites",
        "ifloading": 1,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            var jsonCode = uVF(jsondata, wmsMiId, elemId);
            if (jsonCode == "1") {
                layer.msg('成功加入收藏夹！');
            } else if (jsonCode == "2") {
                layer.msg('成功移出收藏夹！');
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
            }
        }
    });
}
function updateXiangxiFavorite(wmsMiId) {
    var data = "wmsMiId=" + wmsMiId;
    $.supper("doservice", {
        "service": "MdFavoritesService.saveMdFavorites",
        "ifloading": 1,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                layer.msg('成功加入收藏夹！');
                updateXiangxiFav(wmsMiId);
            } else if (jsondata.code == "2") {
                layer.msg('成功移出收藏夹！');
                updateXiangxiFavnot(wmsMiId);
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
            }
        }
    });
}

function checkFavorites(wmsMiId, elemId) {
    if (isLoging() === false) {
        return;
    }
    var data = "wmsMiId=" + wmsMiId;
    var uVF = this.updateViewFavorites;
    $.supper("doservice", {
        "service": "MdFavoritesService.checkFavorites",
        "ifloading": 0,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            uVF(jsondata, wmsMiId, elemId);
        }
    });
}

//更新页面收藏图标
function updateViewFavorites(jsonData, wmsMiId, elemId) {
    var jsonCode = "1";
    if (jsonData.code == "1") {
        updateViewFavoritesExtendIs(wmsMiId, elemId);
    } else if (jsonData.code == "2") {
        jsonCode = "2";
        updateViewFavoritesExtendNot(wmsMiId, elemId);
    } else {
        jsonCode = "0"
    }
    return jsonCode;
}

function updateViewFavoritesExtendIs(wmsMiId, elemId) {
    if(elemId === undefined || elemId === null || elemId === ''){
        elemId = 'new-product-fav-';
    }

    if ($('#' + elemId + '-' + wmsMiId) !== undefined && $('#' + elemId + '-' + wmsMiId) !== null) {
        $('#' + elemId + '-' + wmsMiId).attr('class', 'icon-favorite-solid');
    }
    // if ($('#product-operation-fav-' + wmsMiId) !== null && $('#product-operation-fav-' + wmsMiId) !== undefined) {
    //     $('#product-operation-fav-' + wmsMiId).attr('class', 'icon-favorite-solid');
    // }
}

function updateXiangxiFav() {
    if ($("#fav") !== undefined && $("#fav") !== null) {
        $("#fav").attr("class", "iconfont icon-infav");
        $("#favtext").text("已收藏");
    }
}
function updateXiangxiFavnot() {
    if ($("#fav") !== undefined && $("#fav") !== null) {
        $("#fav").attr("class", "iconfont icon-nofav");
        $("#favtext").text("加入收藏夹");
    }
}

function updateViewFavoritesExtendNot(wmsMiId, elemId) {
    if(elemId === undefined || elemId === null || elemId === ''){
        elemId = 'new-product-fav-';
    }

    if ($('#' + elemId + '-' + wmsMiId) !== undefined && $('#' + elemId + '-' + wmsMiId) !== null) {
        $('#' + elemId + '-' + wmsMiId).attr('class', 'icon-favorite');
    }
    // if ($('#product-operation-fav-' + wmsMiId) !== null && $('#' + elemId + '-fav-' + wmsMiId) !== undefined) {
    //     $('#product-operation-fav-' + wmsMiId).attr('class', 'icon-favorite');
    // }
}

//更新收藏状态
function setFavorites(wmsMiId, elemId) {
    if (isLoging() == false) {
        //$.supper("alert",{ title:"操作提示", msg:"请先登录商城！"});
        fLogin.openLogin();
        return;
    }
    updateFavorites(wmsMiId, elemId);
}
function setXiangxiFavorites(wmsMiId){
    if (isLoging() == false) {
        //$.supper("alert",{ title:"操作提示", msg:"请先登录商城！"});
        fLogin.openLogin();
        return;
    }
    updateXiangxiFavorite(wmsMiId);
}

// 检查是否收藏
function checkViewFav(wmsMiId, elemId) {
    if (isLoging() == false) {
        return;
    }
    checkFavorites(wmsMiId, elemId);
}
function checkXiangxiFav(wmsMiId) {
    if (isLoging() == false) {
        return;
    }
    var data = "wmsMiId=" + wmsMiId;
    $.supper("doservice", {
        "service": "MdFavoritesService.checkFavorites",
        "ifloading": 0,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                updateXiangxiFav(wmsMiId);
            } else if (jsondata.code == "2") {
                updateXiangxiFavnot(wmsMiId);
            }
        }
    });
}

//检查是否加入购物车
function checkAlreadyCart(mmfId, wmsMiId, elemId) {
    if (isLoging() === false) {
        return;
    }
    var data = 'mmfId=' + mmfId;
    $.supper("doservice", {
        "service": _check_cart_action, 'data': data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                updateViewCarts(jsondata, wmsMiId, elemId);
            }
        }
    });
}

//更新购物车状态
function updateViewCarts(jsonData, wmsMiId, elemId) {
    var jsonCode = "1";
    if (jsonData.code == "1") {
        if ($('#' + elemId + '-' + wmsMiId) !== undefined && $('#' + elemId + '-' + wmsMiId) !== null) {
            $('#' + elemId + '-' + wmsMiId).attr('class', 'icon-shopping-cart-solid add-product-cart');
        }
        // if ($('#product-operation-cart-' + wmsMiId) !== null && $('#product-operation-cart-' + wmsMiId) !== undefined) {
        //     $('#product-operation-cart-' + wmsMiId).attr('class', 'icon-shopping-cart-solid add-product-cart');
        // }
    } else if (jsonData.code == "2") {
        jsonCode = "2";
        if ($('#' + elemId + '-' + wmsMiId) !== undefined && $('#' + elemId + '-' + wmsMiId) !== null) {
            $('#' + elemId + '-' + wmsMiId).attr('class', 'icon-shopping-cart add-product-cart');
        }
        // if ($('#product-operation-cart-' + wmsMiId) !== null && $('#product-operation-cart-' + wmsMiId) !== undefined) {
        //     $('#product-operation-cart-' + wmsMiId).attr('class', 'icon-shopping-cart add-product-cart');
        // }
    } else {
        jsonCode = "0"
    }
    return jsonCode;
}

function setCar(wmsMiId, elemId, isInit) {
    if (isLoging() == false) {
        if(isInit === true) {
            return;
        }
    }
    var data = "wmsMiId=" + wmsMiId;
    var uVC = this.updateViewCarts;
    $.supper("doservice", {
        "service": "ShoppingService.getMatFormat",
        "data": data,
        "BackE": function (jsondata) {
            if (isInit === true) {
                checkAlreadyCart(jsondata.obj.mmfId, wmsMiId, elemId);
            } else {
                var jsonCode = uVC(jsondata, wmsMiId, elemId);
                if (jsonCode == "1") {
                    addOneOrder(jsondata.obj.mmfId, jsondata.obj.price);
                } else {
                    _isLayer = true;
                    $.supper("alert", {
                        title: "操作提示", msg: "操作失败！", BackE: function () {
                            _isLayer = false;
                        }
                    });
                }
            }
        }
    });
}

function addOneOrder(mmfId, price) {
    if (isLoging() == false) {
        //$.supper("alert",{ title:"操作提示", msg:"请先登录商城！"});
        fLogin.openLogin();
        return;
    }
    var data = 'mmfId=' + mmfId + '&price=' + price;
    $.supper("doservice", {
        "service": _save_carts_action, 'data': data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                layer.msg('成功加入购物车！');
                countCar();
            }
        }
    });
}

function checkSearch(type) {
    _searchType = type;
}


function saveSearchCookie(searchName){
    var searchNames = cookie.get(_searchType);
    var searchNameArray = searchNames == undefined ? [] : searchNames.split(',');
    var index = $.inArray(searchName, searchNameArray);
    if (searchName !== undefined && searchName !== null && searchName !== "" && searchNameArray.length <= 20 && index < 0) {
        if (searchNames !== undefined && searchNames !== '') {
            searchNames += ',' + searchName;
        } else {
            searchNames = searchName + ',';
        }
        // searchNames = searchNames.substring(0, searchNames.length-1);
        cookie.set(_searchType, searchNames, '30');
    }
}
// function shoucangs() {
//
// }
function shoucangs() {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/myCollect.jsp"});
    $.supper("showTtemWin",{ "url":att_url,"title":"我的收藏"});
}

function shenqings() {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/picking.jsp"});
    $.supper("showTtemWin",{ "url":att_url,"title":"我的申请单"});
}
function wuliaos() {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/myWuliaoCar.jsp"});
    $.supper("showTtemWin",{ "url":att_url,"title":"购物车"});
}
function jumpPick(searchName1) {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/addPicking.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{searchName1:searchName1 }});
    $.supper("showTtemWin",{ "url":att_url,"title":"新增领料"});
}

function jump() {
   var searchName1=$("#searchName").val();
    jumpPick(searchName1);
}
$('#searchName').bind('keyup', function(event) {
    console.log("=====>")
    if (event.keyCode == "13") {
        //回车查询
        $('#search_button').click();
    }
});
function countCollect1() {
    var vdata='';
    $.supper("doservice", {"service":"MdOutOrderService.countCollect1","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                // alert(jsondata.obj);
                $("#countCollects").html(jsondata.obj)
            }else{

            }
        }});
}

// function shoucangs() {
//     var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/storage/editMdOutOrder.jsp"});
//     $.supper("showTtemWin",{ "url":att_url,"title":"添加申领"});
// }