var tt=1;
var tl;
var noticeSpeed=500;
var colleTime=5000;
$(function(){
	createMenu();
	getUserControl();
	$('.J_menuItem').on('click', menuItem);
	$('.J_menuTabs').on('click', '.J_menuTab i', closeTab);
	$('.J_tabCloseOther').on('click', closeOtherTabs);
	$('.J_tabShowActive').on('click', showActiveTab);
	$('.J_menuTabs').on('click', '.J_menuTab', activeTab);
	$('.J_menuTabs').on('dblclick', '.J_menuTab', refreshTab);

    // 左移按扭
    $('.J_tabLeft').on('click', scrollTabLeft);

    // 右移按扭
    $('.J_tabRight').on('click', scrollTabRight);

    // 关闭全部
    $('.J_tabCloseAll').on('click', function () {
        $('.page-tabs-content').children("[data-id]").not(":first").each(function () {
            $('.J_iframe[data-id="' + $(this).data('id') + '"]').remove();
            $(this).remove();
        });
        $('.page-tabs-content').children("[data-id]:first").each(function () {
            $('.J_iframe[data-id="' + $(this).data('id') + '"]').show();
            $(this).addClass("active");
        });
        $('.page-tabs-content').css("margin-left", "0");
    });
    createNotice();
    initNews();
    var timer1=window.setTimeout(initNews,5*60*1000);
    //首页
	if (_user_type == 6) {
		return;
	}
    if (_win_main_orgGxId==1) {
    	addMenuItem("/dentistmall/jsp/main/homePage.jsp","首页");
	}if (_win_main_orgGxId==121&&_user_role==3) {
		addMenuItem("/dentistmall/jsp/main/homePageWarehouse.jsp","首页");
	}
	if (_win_main_orgGxId==121&&_user_role==4) {
		addMenuItem("/dentistmall/jsp/main/homePageClaimant.jsp","首页");
	}
	if (_user_org_type==100&&_user_role==1) {
		addMenuItem("/dentistmall/jsp/main/homePageSupplier.jsp","首页");
	}
});
function rollnews(){
	 var $noticelist=$(".m-notice .noticelist");
     if(tt==tl+1){
         $noticelist.css("top","0px");
         tt=1;
         $noticelist.animate({"top":-44+"px"},noticeSpeed,function(){ tt++ });
     }else {
         $noticelist.animate({"top":tt*-44+"px"},noticeSpeed,function(){
             tt++;
         });
     }
 }
function createNotice(){
	$.supper("doservice", {"service":"SysNoticeInfoService.getSysNoticeInfoList","BackE":function (jsondata) {
		if(jsondata!=null && jsondata.length > 0){
			var str = "";
			for(var i =0;i < jsondata.length;i++){
				str += "<li class=\"list\"><a href=\"noticeInfo.htm?sniId="+jsondata[i].sniId+"\" target=\"_blank\" >系统公告："+jsondata[i].title+"</a></li>";
			}
			$("#noticelist").html(str);
			tl=$(".m-notice li.list").length;
			var $noticelist=$(".m-notice .noticelist");
			$(".m-notice  li.list:first").clone().appendTo($noticelist);
			var tx= setInterval(rollnews,colleTime);
		}
 	}});
}
function createMenu(){
	$.supper("doservice", {"service":"SysUserService.getSysUserMenuList","BackE":function (jsondata) {
		if (jsondata.code == "1") {
			if(jsondata.obj.length > 0){
				var str = "";
				for(var i =0;i < jsondata.obj.length;i++){
					if(2 <= _user_type && _user_type <= 4 && _user_role == 4){
						jsondata.obj[i].name = jsondata.obj[i].name == '库存管理' ? '申领管理' : jsondata.obj[i].name;
					}
					str +="<li class='liMen'><a id='menuLi" + jsondata.obj[i].icon + "' href=\"#\"><i class=\"fa "+jsondata.obj[i].icon+"\"></i><span class=\"nav-label\">"+jsondata.obj[i].name
						+"</span><span class=\"fa arrow\"></span></a>";
					if(jsondata.obj[i].child != null)
						str += initMenu(jsondata.obj[i].child);
					str += "</li>";
				}
				$("#side-menu").append(str);
			}
			$('#side-menu').metisMenu();
			$('.J_menuItem').on('click', menuItem);

			//20191122 yangfeng 增加登录后默认打开页面
			var needTrigger = true;
			var triggerMenuId = '';
			if(_user_type == 6) {

			}else if(2 <= _user_type && _user_type <= 4){
				switch (_user_role) {
					case '4'://申领人Claimant
						triggerMenuId = 'homePageClaimant.js';
						needTrigger = true;
						break;
					case '3'://仓库人  //新增仓管首页 20202-03-26 yanglei
						triggerMenuId = 'homePageWarehouse.js';
						needTrigger = true;
						break;
				}
			} else if(_user_type == 2 || _user_org_type == 100){
				triggerMenuId = 'homePageSupplier.js';
				needTrigger = true;
			}
			// else if (_user_type == 1 ||_user_org_type == 100) {
			//
			// }
			if(needTrigger && $('#JmenuItem_' + triggerMenuId) !== null && $('#JmenuItem_' + triggerMenuId) !== undefined) {
				$('#JmenuItem_' + triggerMenuId).closest('.liMen').children('a').click();
				$('#JmenuItem_' + triggerMenuId).click();
			}
		}
 	}});
}

function getUserControl(){
	$.supper("doservice", {"service":"SysUserService.getSysUserControlList","BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("setProductArray", {"name":"UserControlList", "value":jsondata.obj});
		}
 	}});
}
//
function initMenu(data){
	var str = "<ul class=\"nav nav-second-level\">";
	for(var i =0;i < data.length;i++){
		str+= "<li> ";
		if(data[i].menuType=='4'){
			str+="<a  href=\""+data[i].filePath+"\" target=\"_blank\" data-index=\"0\">";
		}else if(data[i].menuType=='3'){
			str+="<a  href=\"/dentistmall/jsp/sys/sysinfo/viewMedio.jsp?filePath="+data[i].filePath+"\" target=\"_blank\" data-index=\"0\">";
		}else {
			var menuId = data[i].address;
			menuId = menuId.substring(menuId.lastIndexOf('/') + 1, menuId.lastIndexOf('.'));
			str += "<a class=\"J_menuItem\" id='JmenuItem_" + menuId + "' href=\"/dentistmall" + data[i].address + "\" data-index=\"0\">";
		}
		str +="<i class=\"fa "+data[i].icon+"\"></i>"+data[i].name;
		if(data[i].child != null)
			str +="<span class=\"fa arrow\"></span>";
		str += "</a>";
		if(data[i].child != null)
			str += initMenu(data[i].child);
		str += "</a></li>";
		
	}
	str += "</ul>";
	
	return str;
}

function closeMenuItem(dataUrl) {
	//移除存在的标签
	$('.J_menuTab').each(function () {
		if ($(this).data('id') == dataUrl) {
			// $(this).remove();
			$(this).find('i').click();
		}
	});
}

function addMenuItem(dataUrl,menuName) {
    // 获取标识数据
    var flag = true;
    var dataIndex = 100;
    if (dataUrl == undefined || $.trim(dataUrl).length == 0)return false;

    // 选项卡菜单已存在
    $('.J_menuTab').each(function () {
        if ($(this).data('id') == dataUrl) {
        	$(this).remove();
            
        }
    });

    // 选项卡菜单不存在
    	var str='';
    	if ("首页"!=menuName) {
    		str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
		}else{
			str= '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + '</a>';
		}
        $('.J_menuTab').removeClass('active');

        // 添加选项卡对应的iframe
        var str1 = '<iframe class="J_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
        $('.J_mainContent').find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);

        //显示loading提示
        var loading = layer.load();

        $('.J_mainContent iframe:visible').load(function () {
            //iframe加载完成后隐藏loading提示
            layer.close(loading);
        });
        // 添加选项卡
        $('.J_menuTabs .page-tabs-content').append(str);
        scrollToTab($('.J_menuTab.active'));
    return false;
}

function loginOut(){
	$.supper("doservice", {"service":"SysLoginService.ReceptionCancellation", "BackE":function (jsondata) {
		window.location.href = "admin.htm";
	}});
}

function updatePass(){
	var att_url = "";
	var att_url= $.supper("getServicePath", {url:"/jsp/sys/sysuser/updatePass"});
	var tt_win=$.supper("showWin",{url:att_url,width:600,height:400,icon:"fa fa-user",title:"更改密码"}); 
}

function editUser(suiId){
	var data = "suiId="+suiId; 
	var att_url= $.supper("getServicePath", {"service":"SysUserService.findSysUser", "data":data,url:"/jsp/sys/sysuser/editMyInfo"});
	var tt_win=$.supper("showWin",{url:att_url,title:"用户信息",icon:"fa-group",width:800,height:500}); 
}

function onkeydowns(){ 
	var all_win_index_array = $.supper("getProductArray", "all_win_index_array");	
	if($.isEmptyObject(all_win_index_array)){
		var index = top.window.layer.getFrameIndex(window.name); //获取窗口索引 
		if(index){
			top.window.parent.layer.close(index);
		}
		return ;
	} 
	var index = all_win_index_array.pop();
	top.window.parent.layer.close(index);
	 $.supper("setProductArray", {"name":"all_win_index_array", "value":all_win_index_array});
	
}

function initNews(){
	$.supper("doservice", {"service":"MdNewsInfoService.getMdNewsInfoList","BackE":function (jsondata) {
		if (jsondata.code == "1") {
			var newsList = jsondata.obj;
			if(newsList != null && newsList.length > 0){
				$("#warningCount").html(newsList.length);
				var str = "";
				for(var i =0;i < newsList.length && i<10;i++){
					str += "<li id=\"_li"+newsList[i].orderId+"\"> <a href=\"javascript:toViewNews('"+newsList[i].orderId+"','"+newsList[i].newsType+"')\">";
					str +="<div><i class=\"fa fa-envelope fa-fw\"></i> "+newsList[i].content+"</div></a></li>";
					
				}
				if(newsList.length >10){
					str += "<li> <div class=\"text-center link-block\"><a class=\"J_menuItem\" href=\"javascript:viewAll()\">";
					str +="<strong>查看所有 </strong></a></div></li>";
				}
				$("#newsList").html(str);
			}else{
				$("#warningCount").html("");
				$("#newsList").html("");
			}
		}
 	}});
}

function toViewNews(moiId,newsType){
	var vdata="moiId="+moiId;
	var toUrl = "";
	var tilte="";
	if(newsType=='1'){
		tilte="订单发货";
		toUrl="/jsp/dentistmall/transaction/sendOrderInfo";
	}
	else if(newsType=='1'||newsType=='2' || newsType=='4' || newsType=='5'){
		tilte="订单查看";
		toUrl="/jsp/dentistmall/transaction/viewSupplierOrderInfo";
	}else if(newsType=='3' || newsType=='6'){
		toUrl="/jsp/dentistmall/transaction/receiveOrderInfo";
		tilte="订单确认";
	}else if(newsType=='7'){
		toUrl="/jsp/dentistmall/storage/inventoryNewsList";
		tilte="缺货提醒";
	}else if(newsType=='8'){
		toUrl="/jsp/dentistmall/transaction/viewCkOrderInfo";
		tilte="订单查看";
	}
	var warningCount= $("#warningCount").html();
	if(warningCount != null && warningCount != "" && warningCount != "0"){
		var count  = parseInt(warningCount);
		count=count-1;
		if(count >0)
			$("#warningCount").html(count);
		else
			$("#warningCount").html("");
	}
	$("#_li"+moiId).remove();
	var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:toUrl});
	addMenuItem(att_url,tilte);
}

function viewAll(){
	var att_url= $.supper("getServicePath", {url:"/jsp/dentistmall/transaction/orderInfoList.jsp"});
	addMenuItem(att_url,"订单维护");
}

function updateHeard(){
	var att_url= $.supper("getServicePath", {url:"/jsp/sys/sysuser/updateHeard.jsp"});
	var tt_win=$.supper("showWin",{url:att_url,width:600,height:600,BackE:showHeard,icon:"fa fa-user",title:"头像上传"}); 
}

function showHeard(){
	$.supper("doservice", {"service":"SysUserService.showHeard", "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			if(jsondata.obj != null && jsondata.obj != '')
				$("#heardImg").attr('src',jsondata.obj); 
			else
				$("#heardImg").attr('src',"img/tou.png"); 
		}
	}});
}