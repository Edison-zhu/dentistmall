$(function(){
	initQhCount();
	$.supper('initPagination',{id:"Pagination",service:"MdNewsInfoService.getInventoryNewPagerModel",limit:20,isAjax:"1","BackE":initList});
	var timer1=window.setTimeout(initQhCount,5*60*1000); 
});
function initList(dataList){
	var str = "";
	if(dataList != null && dataList.length > 0){
		for(var i =0;i < dataList.length;i++){
			var data = dataList[i];
			var minTime="-";
			if(data.min_day!=null && data.min_day!=""){
				var cdate= new Date(data.new_date);
				cdate.setTime(cdate.getTime()+parseInt(data.min_day)*24*60*60*1000);  
				minTime = cdate.getFullYear()+"-"+((cdate.getMonth()+1) >= 10 ? (cdate.getMonth()+1):('0'+(cdate.getMonth()+1)))+"-"+(cdate.getDate() >= 10 ? cdate.getDate():('0'+cdate.getDate()))
			}
			str += "<div class=\"order-info\" style='height: 135px;'><div class=\"left2\"><a href=\"xiangxi.htm?wmsMiId="+data.wms_mi_id+"\" target=\"_blank\"><img src=\""+data.lessen_file_path+"\" width=\"68\" height=\"68\"></a>";
			str += "<ul><li class=\"name\"><a href=\"xiangxi.htm?wmsMiId="+data.wms_mi_id+"\" target=\"_blank\">"+data.mat_name+"("+data.mmf_name+")"+"</a></li>";
			str += "<li class=\"type\">供应商：<strong><a href=\"pcjxiangxi.htm?wzId="+data.wz_id+"\" target=\"_blank\">"+data.applicant_Name+"</a></strong></li>";
			str += "<li class=\"type\">型号：<strong>"+data.mmf_name+"</strong></li>";
			str += '<li class="type">编号：'+data.mmf_code+'</li>';
			str += "</ul></div>";
			str += "<div class=\"right2\"><div class=\"money\">￥"+toDecimal2(data.price)
				+"</div><div class=\"unit\">"+data.Basic_unit+"</div><div class=\"number\">"+data.all_number+"</div><div class=\"number\">"+(data.warning_shu!=null?data.warning_shu:"-")+"</div>"
				+"<div class=\"number\">"+minTime+"</div>";
			if(data.is_view=='1')
				str +="<div class=\"unit\"><a href=\"javascript:main_shure('"+data.mni_id+"','"+data.mmf_id+"','"+data.price+"')\" class=\"btn\">确认</a></div></div>";
			else
				str +="<div class=\"unit\" style=\"margin-left:10px\"><a href=\"javascript:main_delete('"+data.mni_id+"')\" class=\"btn\" >删除</a></div></div>";
			str +="</div>";
		}
	}
	$("#divList").html(str);
}

function reloadData(){
    $.supper('paginationReload',{"data":"", id: 'Pagination'});
    initQhCount();
}
function initQhCount(){
	$.supper("doservice", {"service" : "MdNewsInfoService.getInventoryNewList","BackE" : function(jsondata) {
			if (jsondata.code == "1") {
				if(jsondata.obj != null){
					if(jsondata.obj.length >0)
						$("#newsCount").html(jsondata.obj.length);
					else
						$("#newsCount").html("");
				}
			}
		}
	});
}

function main_shure(mniId,mmfId,price){
	var data="mniId="+mniId+"&isView=2";
	$.supper("confirm",{ title:"确认操作", msg:"是否确认购买？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdNewsInfoService.saveMdNewsInfoView","data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				reloadData();
				addOrder(mmfId,price);
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	},noE:function(){ 
		$.supper("doservice", {"service":"MdNewsInfoService.saveMdNewsInfoView","data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:reloadData});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
	
}

function addOrder(mmfId,price){
	var data = 'mmfId=' + mmfId + '&price=' + price;
	$.supper("doservice", {
		"service": _save_carts_action, 'data': data, "BackE": function (jsondata) {
			if (jsondata.code == "1") {
				window.location.href = "gwc.htm";
			}else{
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
			}
		}
	});
}
function main_delete(mniId){
	var data="mniId="+mniId+"&isView=0";
	$.supper("confirm",{ title:"删除操作", msg:"确认删除操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdNewsInfoService.saveMdNewsInfoView","data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:reloadData});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
	
}