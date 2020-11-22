$(function(){
	initDataGrid();

	//回车查询
	 $("#matName").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#button3").trigger("click");
		  }
	});
	 $("#mmfName").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#button3").trigger("click");
		  }
	});
});
var mmg ;
var rowsTemp={};
var queryAction = "MdMaterielInfoService.getMyPagerModelObject";
var allInvAction = "MdMaterielInfoService.getPagerModelAllIventory";
var initDataGrid =  function(){
	    var cols = [
	                {title:'物料编号', name:'matCode', width:80, align: 'center'},
			       	{title:'物料名称', name:'matName', width:80, align: 'center'},
			       	{title:'规格', name:'mmfName', width:80, align: 'center'},
					//2020 4月19 去掉单价显示
			       	// {title:'单价', name:'price', width:40, align: 'center'},
			       	{title:'单位', name:'basicUnit', width:30, align: 'center'},
			       	//增加入库数量
			       	{title:'入库数量', name:'number2', width:40, align: 'center',renderer:formateInp},
			       	{title: '操作', name: 'operation', width:20, align: 'center',renderer:control}
	            ];
	    var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	          mmg = $('#datagrid1').mmGrid({
	              height:'auto'
	        	  ,cols: cols
	              , method: 'post'
	              , remoteSort:true
	              ,url : att_mmgurl
	              , sortName: 'SECUCODE'
	              , sortStatus: 'F'
	              , multiSelect: true
	              , checkCol: true
	              , fullWidthRows: true
	              , autoLoad: false
	              , plugins: [
	                  $('#pg').mmPaginator({})
	              ]
	          });
	          mmg.load();
}
/**
 * 增加确认按钮
 * @param val
 * @param item
 * @param rowIndex
 * @returns {String}
 */
function control(val,item,rowIndex){
	var str = "";
	item.describe1="";
	str += '<input type="button" class="btn btn-primary btn-xs" value="确认" style="margin-right: 5px" onclick="addToMdOutrOrder(' + JSON.stringify(item).replace(/"/g, '&quot;') + ', ' + item.mmfId + ')" />';
   return str;
}
function addToMdOutrOrder(item, mmfId) {
	var matNumber = $("#" + mmfId + "Inp").val();
	item.matNumber = matNumber;
	rowsTemp[mmfId]=item;
	layer.msg('成功加入申领信息中，关闭页面可查看');
}

function formateInp(val,item,rowIndex){
	var btton_pre = '<input type="button" value="-" id="'+item.mmfId+'Min" onclick="minWareNum('+item.mmfId+')" />';
	var tt = "<input type=\"text\" id=\""+item.mmfId+"Inp\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" style=\"width:40px\;text-align: center\" value='0'/>";
	var button_next = '<input type="button" value="+" id="'+item.mmfId+'Add" onclick="addWareNum('+item.mmfId+')" />';
	tt = btton_pre + tt + button_next;
	return tt;
}
function minWareNum(mmfId){
	checkCol: true
	var base_num = $("#" + mmfId + "Inp").val();
	base_num--;
	if(base_num <= 1) {
		base_num = 1;
	}
	$("#" + mmfId + "Inp").val(base_num);
}
function addWareNum(mmfId) {
	var base_num = $("#" + mmfId + "Inp").val()
	base_num++;
	$("#" + mmfId + "Inp").val(base_num);
}
function formatPackInp(val,item,rowIndex){
	var tt = "<input type=\"text\" id=\""+item.mmfId+"Pack\" style=\"width:80px\"/>";
	return tt;
}

function search2(){
	var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = att_mmgurl;
    mmg.load();
}

function  closeWin(){
	$.supper("closeWin");
	rowsTemp = {};
}
//取消按钮
function beforeClose() {
	var rows = []
	$.each(rowsTemp, function (key, value) {
		rows.push(value);
	});
	if(rows != null || rows.length > 0) {
		$.supper("setProductArray", {"name":"selMaterielInfoArray", "value":rows});
	}
	closeWin();
}

function save(){
	var rows=mmg.selectedRows();
	var matNames = ''
	if(rows != null && rows.length > 0 && rows[0] != null){
		for(var i=0;i < rows.length;i ++){
			var mmfId=rows[i].mmfId;
			var matCode=rows[i].matCode;
			var matName=rows[i].matName;
			var mmfName=rows[i].mmfName;
			var price=rows[i].price;
			var shu=$("#"+mmfId+"Inp").val();
			rows[i].matNumber=shu;
			matNames += rows[i].wmsMiId + ','
		}
	}
	$.each(rowsTemp, function (key, value) {
		var needAdd = true;
		rows.forEach(function (value1) {
			if(key == value1.mmfId){
				needAdd = false;
				return false;
			}
		})
		if(needAdd === true) {
			matNames += value.wmsMiId + ','
			rows.push(value);
		}
	});
	if(rows==null || rows.length <=0){
		$.supper("alert",{ title:"操作提示", msg:"请选择物料！"});
		return;
	}
	if (matNames != '') {
		matNames = matNames.substring(0, matNames.length - 1)
	}
	$.supper("doservice", {
		"service": allInvAction,
		"ifloading": 1,
		"options": {"type": "post", "data": 'matNames=' + matNames},
		"BackE": function (jsondata) {
			if (jsondata.code == "1") {
				let data = jsondata.obj
				for (let index = 0; index < rows.length; index ++) {
					if (index >= data.length) {
						rows[index].allInventory = 0
					} else {
						rows[index].allInventory = data[index]
					}
				}
				setTimeout(function () {
					$.supper("setProductArray", {"name": "selMaterielInfoArray", "value": rows});
					closeWin();
				}, 200)

			} else {
				$.supper("alert", {
					title: "操作提示",
					msg: "操作失败！"
				});
			}
		}
	});

}


