var _dd_search_action = 'ShoppingService.getOrderMxName';
var _all_limit_number = 5;
var moiId = null;
var masCode = null;
var masId = null;
var newAs = null;
var maxMoney = 0;
var _expressMoney = 0;
var _momIds = '';
$(function(){
    moiId = $('#moiId').val();
    masCode = $('#masCode').val();
    newAs = $('#newAs').val();
    masId = $('#masId').val();
    _momIds = $('#momIds').val();
    totalRefund = Number($('#refundMoneInp').val());
    asTotalCount = $('#asCout').text();
    initMatList();
    // initOderListUtil({server: 'ShoppingService.getOrderMxModelByMoiId'});

    // $('#returnsLabel').on('click', function () {
    //     $('#moneyTr').show();
    // });
    // $('#refundLabel').on('click', function () {
    //     $('#moneyTr').hide();
    // });
    $('#refundMoneInp').on('keyup', function () {
        $(this).val($(this).val().replace(/[^0-9.]/g,''));
    });
    $('#refundMoneInp').on('blur', function () {
        let mon = $(this).val();
        if(CheckUtil.isPlusFloat(mon) === false || mon <= 0){
            mon = 0;
        }else if(mon >= maxMoney){
            mon = maxMoney;
        }
        $('#reMoney').text('￥' + toDecimal2(mon));
        $(this).val(toDecimal2(mon));
    });

    initOneUploadImg("lessenFilecode","lessenFileDiv");
    initListUploadImg("listFilecode","imglist", null, {accept: 'image/png,image/jpg,image/jpeg,image/gif', acceptType: 'image', size: 3 * 1024/*KB*/});

    var $gaddress = $('#addressInfo');
    var $addressMask = $('#addressId');
    fLogin = {
        /* 打开弹窗 */
        'openLogin': function () {
            $gaddress.css({'top': '200px', 'left': ($(window).width() - $gaddress.width()) / 2 + 'px'}).fadeIn();
            $addressMask.fadeIn();
        },
        /* 关闭弹窗 */
        'closeLogin': function () {
            $gaddress.fadeOut();
            $addressMask.fadeOut();
        }
    };
    $('#addressClose').click(fLogin.closeLogin);
});
// function canceles(){
//     window.location.href = 'ddlb.htm'
// }
function initMatList(){
    var str = "";
    var vdata="moiId=" + moiId;
    $.supper("doservice", {'service':"ShoppingService.getOrderPagerOne",'data':vdata, "BackE": initAfterSaleList, ifloading: true});
}
function initAfterSaleList(datalist) {
    initAfterSaleOrder(datalist.items, true);
}