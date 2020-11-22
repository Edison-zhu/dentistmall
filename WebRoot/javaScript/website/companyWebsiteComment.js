$(function(){ 
	KindEditor_init(); 
});
var editorObj = new Array(); 
function KindEditor_init(keval){
	var att_keval_afterChange =keval?keval.afterChange:null;
  $.each($(".KindEditor"), function(i, n){
              var k_name=n.id;
               if(k_name){
  				 	var editor = KindEditor.create('textarea[id="'+k_name+'"]',{
  				 		cssData:'body {font-family: "微软雅黑"; font: 14px/1.5}',
  				 		cssPath : '/dentistmall/jsplugins//kindeditor/plugins/code/prettify.css',
				      	uploadJson : '/dentistmall/jsp/upload_json.htm',
						fileManagerJson : '/dentistmall/jsp/file_manager_json.htm',   
					    allowFileManager: true,
					 	autoHeightMode : false ,
					 	afterChange : att_keval_afterChange,
					 	afterCreate : function() { 
					          this.sync(); 
					         }, 
				         afterBlur:function(){ 
				             this.sync();
				         }   
					});
				editorObj[i]=editor; 	
			  } 
	 });
}	 
 
function  all_sync(){
      $.each(editorObj, function(i, n_editor){ 
     		 n_editor.sync();  
	 });
}
 
function getKindEditor(att_id){
          $.each($(".X_kindereditor"), function(i, n){
              var k_name=n.id;
               if(k_name==att_id){ 
				  return  editorObj[i] ; 	
			  } 
	 }); 
}	

function ac(){
	var nums=this.count(); 
	if(nums>1000){
		lenth_matText=false;
	}else {
		lenth_matText=true;
	}
	$("#word_count1").html(nums);
}

function validate(){

	var newsTitle = $("#newsTitle").val();
	var serialNumber = $("#serialNumber").val();
	 
	 if(CheckUtil.isNull(newsTitle)){
		 Messager.info("标题不能为空!");
		 return false;
	 }
	 
/*	 if(CheckUtil.isNull(newscode)){
		 Messager.info("新闻编码不能为空!");
		 return false;
	 }
	 if(CheckUtil.isNull(createRen)){
		 Messager.info("创建人不能为空!");
		 return false;
	 }
	 
	 if(CheckUtil.isNull(newsComment)){
		 Messager.info("新闻内容不能为空!");
		 return false;
	 }
	 //正整数
	 
		 
	 }*/
	 if(serialNumber){
		 
		 if(!CheckUtil.isPlusInteger(serialNumber)){
			 Messager.info("序号必须为正整数!");
			 return false;
		 }
	 }
	return true;
}

function  save(){  
	if(!validate()){
		return ;
	}
	 var att_data=$("#accountForm").serialize();
	 
	 $.supper("doservice", {"service":"SysWebsiteCommentService.saveSysWebsiteComment", "ifloading":1,"options":{"type":"post","data":att_data}, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert", {
					title : "操作提示",
					msg : "操作成功！",
					BackE : function(){
						$("#swmId").val(jsondata.obj.swmId);
						$("#newscode").val(jsondata.obj.newscode);
						$("#orgGxId").val(jsondata.obj.swcId);
						$("#createDatetime").val(jsondata.obj.createDatetime);
					}});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
		}});
}
 