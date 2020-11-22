var mmg;
var  all_opt_data={"list_sysClassColumns":[]};
var all_fieldType_json=[{"id":"integer","name":"整型"},{"id":"varchar2","name":"字符串"},{"id":"TIMESTAMP","name":"日期"}];
var all_state_json=[{"id":"1","name":"是"},{"id":"2","name":"否"}];
var  all_search_json=[];

function formatestate(val,item,rowIndex){
	if(val){
		var back="";
		$.each( all_state_json, function(nindex, nvalue){ 
		  	if(val&&nvalue.id&&val.toLowerCase()==nvalue.id.toLowerCase()){
		  		back=	nvalue.name;
		  		return true;
		  	} 
		});
	    return back;
	}else {
		return "";
	} 
}

$(function(){     
	initSelect(); 
});

function initSelect(){ 	
	var att_tableName=$("#tableNameTemp").val(); 
	
	var att_data="sysCode="+$("#sysCode").val();
		$.supper("doservice", {"service":"TableInfoService.questNodeTable","data":att_data,options:{async:false}, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				all_search_json=jsondata.obj;
				loadSelect($("#tableName"),all_search_json);  
				$("#tableName").find("option[value='"+att_tableName+"']").attr("selected",true);
			}
	}});
		
} 

function loadSelect(opt,att_data){	   
	 var itemroot = new Option("请选择", "");  
	     opt.append(itemroot); 
	      if(att_data!=null&&att_data.length>0){
	    	   for(var i=0 ;i<att_data.length;i++){
	    		   var item_node=att_data[i];
	    		   var item = new Option(item_node.name, item_node.id);  
	    		   opt.append(item);
	    	   }
	      } 
}
//
//function changeAllText(){
//	var att_servicename=$("#servicename").val();
//	var att_tableName= $("#tableName").val();
//	
//	if(att_servicename!=""&&att_tableName!=""){
//		
//	}
//	
//}

function show_table_info(opt){
	var att_value=opt.options[opt.options.selectedIndex].value;
	
	$.each(all_search_json, function(i, n){
		  if(n.id==att_value){
			  $("#tableNode").val(n.attributes.tableNode);
			  $("#tableCode").val(n.attributes.tableCode);
			  
			  $("#SClassComment").val(n.attributes.tableNode);
			  $("#SClassComment2").val(n.attributes.tableNode);
			  $("#SClassComment3").val(n.attributes.tableNode);
			  $("#SClassComment4").val(n.attributes.tableNode+"Dao接口");
			  var  cname=n.text;
			 
			  $("#SClassName").val(cname+"Service");
			  $("#SClassName2").val("I"+cname+"Service");
			  
			  $("#SClassName3").val(cname);
			  $("#SClassName4").val("I"+cname+"Dao");
			  
			  $("#dbType").val(n.attributes.dbType);
			  $("#seqstr").val(n.attributes.seqstr);
			  $("#pkName").val(n.attributes.pkName);  
			  initCodeList();
			  return true;
		  }
   }); 
}

 


function  closeWin(){
	$.supper("closeWin"); 
}

function save(){
   		var SClassName = $("#SClassName").val();
   		if(SClassName == null || SClassName == ""){
   			$.supper("alert",{ title:"操作提示", msg:"系统类名称不能为空！"});
   			return;
   		}
   		var servicename = $("#servicename").val();
   		if(servicename == null || servicename == ""){
   			$.supper("alert",{ title:"操作提示", msg:"业务名称不能为空！"});
   			return;
   		}
   		
   		 
   		var data =$('#accountForm').serialize(); 
   		
   		$.supper("doservice", {"service":"CodeAutoService.saveOrUpdatesysClass",options:{async:false},"ifloading":1,"data":data, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert",{ title:"操作提示", msg:"操作成功！"});
					//, BackE:closeWin
				}else
					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 		}});		
}



