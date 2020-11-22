/**
  初始化select控件
  	  //控件id
	  //下拉框数据
	  //是否显示搜索框
	  //是否显示关闭按钮
	  //最大选中数
	  //标题默认
	  //样式名称以"，"隔开
	  //控件宽度
	  //搜索框 显示提醒信息
	  //是否全选按钮
	  //是否隐藏无效记录
	  例子
	  var opt={"id":"myselect",
	  			"selectData":att_data,
				 "liveSearch": true,
				 "doneButton":true,
   				//"maxOptions": 5 
				title:"请选择文本",
				classStr:"show-tick",
				width:699,
				liveSearchPlaceholder:"输入条件提示信息",
				actionsBox:true,
				hideDisabled:true
	   			};
	  
    initXSelect(opt);
  -------------------
  selectData数据格式
    id:主键
    name:显示子段
    disabled:是否有效
    data_subtext :辅助说明文字
    children: 子列表子段，格式（selectData数据格式）
     
  **/



  function initXSelect(optStr){
	  var att_name= optStr.name?optStr.name:optStr.id;
	  var att_id= optStr.id?optStr.id:att_name;
	  var opt= optStr.opt?optStr.opt:$("#"+att_id);
	  
	  
	 var selectOpt=$("<select name='"+att_name+"' id='"+att_id+"' type='xselect'  multiple></select>");
	 
	 var classArray=optStr.classStr.split(","); //字符分割 
	for (i=0;i<classArray.length ;i++ ) 
	{ 
	  selectOpt.addClass(classArray[i]);
	}  
	 opt.before(selectOpt);
	 opt.remove(); 
	 if(jQuery.type(optStr.selectData) == "array"){
		 	$.each(optStr.selectData, function(i, n){
				
				if(n.children){
					var att_dis="";
					if(n.disabled){
							   att_dis="  disabled='disabled'  ";
					} 
				 var datasubtext="";
					if(n.data_subtext){
						 datasubtext="data-subtext='"+n.data_subtext+"'";
					} 
					var chilOpt=$("<optgroup  "+att_dis+ datasubtext+" label='"+n.name+"'></optgroup>");
					selectOpt.append(chilOpt);
					$.each(n.children, function(cili, ciln){
								 appendSelectOption(chilOpt,ciln);
						});
									 	
				 }else { 
					 appendSelectOption(selectOpt,n);
				 }
		    }); 
	}
	 selectOpt.selectpicker(optStr); 
	 selectOpt.data("option",optStr);
	 return selectOpt;
  }
  
  
  function getSelectOpton(id){
	 var att_option= $("#"+id).data("option");
	 var att_value=$("#"+id).val();
	 if(att_value==null){
		 return null;
	 }
	 if(att_option.selectData==undefined){
		 return null;
	 }
	 var myArray=[];
	 var classArray=att_value; //字符分割 
		for (var i=0;i<classArray.length ;i++ ) 
		{ 
		   var  valueOne=classArray[i];
		   var did=getB(valueOne,att_option.selectData);
		   if(did!=undefined){
			   myArray.push(did);
		   }
		}  
		return myArray;
  }
  
   function getB(classArray,selectData){
	   
	   for(var j=0;j<selectData.length ;j++){
		   if(selectData[j].id==classArray){
			    return selectData[j].attributes;
		   }
	   } 
   } 
  
  function appendSelectOption(selectOpt,val){
	  		var att_dis="";
			if(val.disabled){
					   att_dis="disabled";
			} 
			var datasubtext="";
		    if(val.data_subtext){
					   datasubtext="data-subtext='"+val.data_subtext+"'";
			}
			selectOpt.append("<option  "+att_dis+"  "+datasubtext+"  value='"+val.id+"'>"+val.name+"</option>");
     }