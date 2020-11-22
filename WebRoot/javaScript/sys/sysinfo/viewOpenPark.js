$(function(){
	initForm();
});


function  closeWin(){
	$.supper("closeWin");
}

function initForm(){
  var val=$("#state").val();
  var   ls_back="";
	if(val==1){
		ls_back= "待审批";
	}
	if(val==2){
		ls_back= "审批通过";
	}
	if(val==3){
		ls_back= "审批不通过";
	}
	$("#att_state").html(ls_back);
} 


function setParkInfo(){
	
}