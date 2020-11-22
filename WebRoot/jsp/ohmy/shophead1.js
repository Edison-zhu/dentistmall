$.supper('setbasepath');
var fLogin;
var _searchType = 'mat';
var _isLayer = false;
var tt = 1;
var tl;
var noticeSpeed = 500;
var colleTime = 5000;

$(document).ready(function () {
    if (_isCar == '1')
        $(".shopping-cart").show();
    else if (_isCar == '2')
        $(".to-index-page").show();
    else if (_isCar == '3')
        $(".to-shopping-cart").show();
    else if (_isCar == '4')
        $(".to-order-list").show();

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

});