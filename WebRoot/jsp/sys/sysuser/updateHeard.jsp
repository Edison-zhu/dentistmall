<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
   <title>优牙库</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 <%@ include file="/jsp/link.jsp"%>
  </head> 
  
  <body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		<!-- 隐藏域begin -->
		<input type="hidden" id="imageRotate" name="imgSrc"/>
		<!--隐藏区域end -->
		
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
			   <div class="form-group" styel="">
					  <div class="imageBox" >
						   <div class="thumbBox"></div>
						   <div class="spinner" style="display: none">Loading...</div>
					  </div>
						  <div class="action"> 
						    <div class="new-contentarea tc"> <a href="javascript:void(0)" class="upload-img">
						      <label for="upload-file">上传图像</label>
						      </a>
						      <input type="file" class="" name="upload-file" id="upload-file" />
						    </div>
						    <input type="button" id="btnZoomIn" class="Btnsty_peyton" value="+"  >
						    <input type="button" id="btnZoomOut" class="Btnsty_peyton" value="-" >
						    <!-- input type="button" id="btnCrop"  class="Btnsty_peyton" value="裁切" -->
						   
						  </div>
						  <div class="cropped"></div>
						</div>
			</div> 
		 <!-- 正文部分end -->
		 <!-- 工具栏部分begin -->
				<div class="panel-footer">
		           <div class="form-group">
			           <div class="col-sm-offset-5 col-sm-10"> 
			           <button type="button"  onclick="save()" class="btn btn-primary">保存</button>
			           </div>
			      </div>
       		</div>
       	<!-- 工具栏部分end -->	 
    </div> 
 </form>
 <!-- 表单部分end-->
</body>
   
</html>
<script>
var cropper;
$(window).load(function() {
	var options =
	{
		thumbBox: '.thumbBox',
		spinner: '.spinner',
		imgSrc: 'img/default.jpg'
	}
	cropper = $('.imageBox').cropbox(options);
	$('#upload-file').on('change', function(){
		var reader = new FileReader();
		reader.onload = function(e) {
			options.imgSrc = e.target.result;
			cropper = $('.imageBox').cropbox(options);
		}
		reader.readAsDataURL(this.files[0]);
		this.files = [];
	})
	$('#btnCrop').on('click', function(){
		var img = cropper.getDataURL();
		$('#imageRotate').val(img);
		$('.cropped').html('');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
	})
	$('#btnZoomIn').on('click', function(){
		cropper.zoomIn();
	})
	$('#btnZoomOut').on('click', function(){
		cropper.zoomOut();
	})
});
function  closeWin(){
  	$.supper("closeWin");
}
function save(){
   		var img = cropper.getDataURL();
		$('#imageRotate').val(img);
   		var data =$('#accountForm').serialize();
   		$.supper("doservice", {"service":"SysUserService.updateHeard", "options":{"data":data,"type":"post"},"dotry":"true", "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:closeWin});
				}else
					$.supper("alert",{ title:"操作提示", msg:jsondata.meg});
 		}});
   		
   }
</script>