 (function ($) {
jQuery.fn.extend({
 xcombox:function(options,val){
 options = options || {};
		if (typeof options == "string") {
		switch (options) {  		         
				case "addcombox":
				return  addcombox(this,val);
				case "setXcombox":
				return setXcombox();
			}
		}
 }
});  
 function  setXcombox(){
		 	   var allobj=$(".xcombox");	  
		 	   var parobj=$.grep(allobj, function(n,i){
		 	   				var jqobj=$(n);
		 	   				var parentcomm=jqobj.attr("parentcomm");
		 	        		if(parentcomm){
		 	        				return n;
		 	        		}else{
									//处理没有级联关系的或者级联关系在最上层的下拉控件
									addcombox(jqobj,{"width":jqobj.attr("width"),"impCode":jqobj.attr("impcode")});
                                 } 
				 		}); 
				 	 //处理没有级联关系的或者级联关系在最上层的下拉控件  
				 	 var arrayobj=[];
			 	   $.each(parobj , function(i,obj){
			 	        var jqobj=$(obj); 
			 	        var parentcomm=jqobj.attr("parentcomm");
			 	        var pobj=$("#"+parentcomm);
			 	        var subval={};
			 	       	subval.impCode=jqobj.attr("impcode");
			 	       	subval.width=jqobj.attr("width");
			 	       	subval.paramChar=[{"name":"parentId","value":pobj.val()}];
			 	        addcombox(jqobj,subval);
				       //为父控件添加一个onSelect事件  
				        var option=pobj.combobox("options"); 
					    option.onSelect=xonSelect;
					    //为父亲控件添加子控件变量数组
					    var subcomm=pobj.attr("subcomm")?(pobj.attr("subcomm")+","+jqobj.attr("id")):jqobj.attr("id"); 
					    pobj.attr("subcomm",subcomm);
					  }); 
 }

 
function xonSelect(record){
		var substr=$(this).attr("subcomm");
		var array_subcomm=substr.split(",");
		for(var i=0;i<array_subcomm.length;i++){
		   subcomm=array_subcomm[i];
		   var jqobj=$("#"+subcomm); 
			 var subval={};
			 	       	subval.impCode=jqobj.attr("impcode");
			 	       	subval.width=jqobj.attr("width");
			 	       	subval.paramChar=[{"name":"parentId","value":record.id}];
			 	        addcombox(jqobj,subval);
		}  
}


	//添加下拉框对象
 function addcombox(opt,val){ 
  var dataChar="impCode="+val.impCode;
  if(val.paramChar){
   var paramChar="";
         $.each(val.paramChar, function(i,obj){
              paramChar+=obj.name+"Char121_colon"+obj.value+"Char390_semi";
		}); 
    
     dataChar+="&paramChar="+paramChar;
   }
	var urlstr=$.supper("getServicePath",{
	 						"service":"supperService",
						    "func":"getParam",
						    "data":dataChar
	});
   val.method="get";
	 if(val.type=="box"||val.type==undefined){ 
	 	 val.url=urlstr;
	 	 val.valueField="id" ;
	 	 val.textField="text";
	 	 
	 	 /*
	 	 if(val.subcomm){
	 	   //如果有关联的子下拉控件
	 	       var subobj=$("#"+val.subcomm);
	 	       var subval={};
	 	       subval.impCode=subobj.attr("impcode");
	 	       subval.width=subobj.attr("width");
	 	       subval.paramChar=[{"name":"parentId","value":opt.val()}];
	 	        addcombox(subobj,subval);
	 	 }
	 	 */
	 	 val.onLoadSuccess=SetValue;
	 	
		 opt.combobox(val);  
		}
	 if (val.type=="tree"){
			opt.combotree(urlstr);  
			}	
	 if (val.type=="grid"){
				opt.combogrid(urlstr);  
			}
	}
	
	function SetValue(){
	   var  separator= $(this).combobox('options').separator ;
	    var multiple= $(this).combobox('options').multiple ;
	    if(multiple ){
	        var values=$(this).combobox('getValues'); 
	        if(values.length==1&&values[0]){
	        var arrayval=values[0].split(separator);
	        $(this).combobox('setValues',arrayval); 
	        }
	    }
	} 
 
})(jQuery);