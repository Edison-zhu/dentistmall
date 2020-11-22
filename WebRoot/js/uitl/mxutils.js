 
/*
 * 异步请求处理
 */

RPC = function(){
	
	 var me = this;
	 
	 me.test = function(){
		 alert("test");
	 };
	 
	 me.get =  function(url,p_data,p_callback){
		 var data = null;
		 var callback = null;
		 
		 if($.isFunction(p_data)){
			 callback = p_data;
		 }
		 else{
			 data = p_data;
			 callback = p_callback;
		 }
		 $.supper("doservice", {
			 "service":url, 
			 "data":data, 
			 "BackE":callback
		 });
	};
	
	 me.post =  function(url,p_data,p_callback){
		 var data = null;
		 var callback = null;
		 
		 if($.isFunction(p_data)){
			 callback = p_data;
		 }
		 else{
			 data = p_data;
			 callback = p_callback;
		 }
		 var AjaxUrl = $.supper("getbasepath") + "doService.htm?1=1";
		 
		 var index  = url.lastIndexOf(".");
		 var serviceVal = url.substring(0,index);
		 var funVal = url.substring(index+1);
		 
		 serviceVal = (serviceVal).replace(/\s[A-Z]/g, function ($1) {
				return $1.toLowerCase();
			}).replace(/^[A-Z]/, function ($1) {
				return $1.toLowerCase();
			});
		 
		 var doservice ="service="+ serviceVal +"&func=" + funVal;
		  data = doservice + "&"+ data ;
		 
		 $.post(AjaxUrl,data,p_callback);
		 
	};
		
	 me.getAndBox = function (url,data,callback){
			me.get(url,data,function(json){
				
				if (json.code == "1") {
					$.supper("alert",{title:"操作提示",msg:"操作成功！", "BackE":callback});
				}else{
					$.supper("alert",{title:"操作提示",msg:json.meg});
				}
				
			});
			
		};
		
		 me.postAndBox = function (url,data,callback){
				me.post(url,data,function(json){
					
					if (json.code == "1") {
						$.supper("alert",{title:"操作提示",msg:"操作成功！", "BackE":callback});
					}else{
						$.supper("alert",{title:"操作提示",msg:json.meg});
					}
					
				});
				
			};
		
	/**
	 * 
	 * @param url 后台处理action
	 * 
	 * @param data 参数
	 * 
	 * @param jspPath 参数可为null 
	 *                请求转发 jsp path
	 * @returns
	 */
	 me.getUrl = function (url,data,jspPath){
			
		    var att_url= $.supper("getServicePath", {"service":url, "data":data,url:jspPath});
			
			return att_url;
			
		};
		
	 me.getUrlByForm = function (url,formId){
		    var form  = "#"+formId;
		    var data = $(form).serialize();
		    var url = me.getUrl(url,data);
			return url;
			
		};
	
	
} ;
var rpc =  new RPC();

//---------------------------------------------------------------------------------------

FormUtilClass = function (){
	var me = this;
	
	/**
	 * 
	 * @param name 单选按钮的name值
	 * @param val  选中的值
	 */
	me.setRadioVal = function(name,val){
		 $("input[name='"+name+"'][value="+val+"]").attr("checked",true);
	}
	
	
	
}

var FormUtil = new FormUtilClass();

//-----------------------------------------------------------------------------------------
/**
 * 消息提示框
 */
MessagerBox = function (){
	
  var me = this;
  
  me.info = function (msg,callback){
	  
	  $.supper("alert",{title:"提示消息",msg:msg, "BackE":callback});
	  
  }
  me.alert = function (msg,callback){
	  
	  $.supper("alert",{title:"提示消息",msg:msg, "BackE":callback});
	  
  }
  
	
}

var Messager = new MessagerBox();



/*
 * ------------------------------------------------------------------------------------
 */
/**
 * 表单验证代码
 */

/**
 * 判断一个对象是否是字符串 string 类型。
 */
function $isString(p_value)
{
    return typeof(p_value) == "string";
};

function $isDate(p_value)
{
    return p_value != null && p_value.constructor == Date;
};

Date.format = function(p_value, p_formatString)
{
    if (p_value != null)
    {
        var text;
        if (!p_formatString)
        {
            text = "yyyy-MM-dd HH:mm:ss";
        }
        else
        {
            text = p_formatString;
        }
        var yy = p_value.getYear();
        var M = p_value.getMonth() + 1;
        var d = p_value.getDate();
        var h = p_value.getHours() % 12;
        var H = p_value.getHours();
        var m = p_value.getMinutes();
        var s = p_value.getSeconds();
    
        var yyyy = p_value.getFullYear();
        var MM = (M<10?"0"+M:M);
        var dd = (d<10?"0"+d:d);
        var hh = (h<10?"0"+h:h);
        var HH = (H<10?"0"+H:H);
        var mm = (m<10?"0"+m:m);
        var ss = (s<10?"0"+s:s);
        text = text.replace("yyyy", yyyy).replace("MM", MM).replace("dd", dd);
        text = text.replace("HH", HH).replace("hh", hh).replace("mm", mm).replace("ss", ss);
        text = text.replace("yy", yy).replace("M", M).replace("d", d);
        text = text.replace("H", H).replace("h", h).replace("m", m).replace("s", s);
        return text;
    }
    else
    {
        return "";
    }
};

Date.formatLaydate = function(p_value, p_formatString)
{
	if (p_value != null)
    {
        var text;
        if (!p_formatString)
        {
            text = "YYYY-MM-DD HH:mm:ss";
        }
        else
        {
            text = p_formatString;
        }
        
        var yy = p_value.getYear();
        var M = p_value.getMonth() + 1;
        var d = p_value.getDate();
        var h = p_value.getHours() % 12;
        var H = p_value.getHours();
        var m = p_value.getMinutes();
        var s = p_value.getSeconds();
    	
        var yyyy = p_value.getFullYear();
        var MM = (M<10?"0"+M:M);
        var dd = (d<10?"0"+d:d);
        var hh = (h<10?"0"+h:h);
        var HH = (H<10?"0"+H:H);
        var mm = (m<10?"0"+m:m);
        var ss = (s<10?"0"+s:s);
        
        text = text.replace("YYYY", yyyy).replace("MM", MM).replace("DD", dd);
        text = text.replace("HH", HH).replace("hh", hh).replace("mm", mm).replace("ss", ss);
        text = text.replace("YY", yy).replace("M", M).replace("D", d);
        text = text.replace("H", H).replace("h", h).replace("m", m).replace("s", s);
        return text;
    }
    else
    {
        return "";
    }
};


/**
 * 提供一个检查字符串格式的工具类。该类在运行时唯一静态实例是 <b>mx.utils.CheckUtil</b> 对象。
 */
CheckUtilClass = function()
{
    var me = this;
    
    /**
     * 提供一个方法，判断参数是否为空。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isNull = function(p_str)
    {
        if (null == p_str || "" == p_str.trim())
        {
            return true;
        }
        else
        {
            return false;
        }
    };

    /**
     * 提供一个方法，判断参数是否全是数字。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isDigit = function(p_str)
    {   
        var patrn = /^-{0,1}\d{1,16}\.{0,1}\d{0,10}$/;
        return patrn.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否是整数。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isInteger = function(p_str)
    {
        var patrn = /^([+-]?)(\d+)$/;
        return patrn.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否为正整数。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isPlusInteger = function(p_str)
    {
        var patrn = /^([+]?)(\d+)$/;
        return patrn.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否为负整数。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isMinusInteger = function(p_str)
    {
        var patrn = /^-(\d+)$/;
        return patrn.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否为浮点数。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isFloat = function(p_str)
    {
        var patrn = /^([+-]?)\d*\.\d+$/;
        return patrn.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否为正浮点数。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isPlusFloat = function(p_str)
    {
        var patrn = /^([+]?)\d*\.\d+$/;
        return patrn.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否为负浮点数。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isMinusFloat = function(p_str)
    {
        var patrn = /^-\d*\.\d+$/;
        return patrn.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否仅中文。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isChinese = function(p_str)
    {
    	if(!$isString(p_str)){
         	return false;
        }
        var patrn = /^[\u4E00-\u9FA5\uF900-\uFA2D]+$/;
        return patrn.test(p_str);
    };
    
    /**
     * 提供一个方法，判断参数是否仅英文字母。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isLetter = function(p_str)
    {
    	if(!$isString(p_str)){
         	return false;
        }
    	var reg=/^[a-zA-Z]+$/;     
        return reg.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否仅ACSII字符。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isAcsii = function(p_str)
    {
    	if(!$isString(p_str)){
         	return false;
        }
        var patrn = /^[\x00-\xFF]+$/;
        return patrn.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否手机号码。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isMobile = function(p_str)
    {
    	if(!$isString(p_str)){
         	return false;
        }
        var patrn = /^1\d{10}$/;
        return patrn.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否电话号码。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isPhone = function(p_str)
    {
    	if(!$isString(p_str)){
         	return false;
        }
        var patrn = /^(0[\d]{2,3}-)?\d{6,8}(-\d{3,4})?$/;
        return patrn.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否URL地址。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isUrl = function(p_str)
    {
    	if(!$isString(p_str)){
         	return false;
        }
    	var patrn = /^((http(s)?|ftp|telnet|news|rtsp|mms):\/\/)?(((\w(\-*\w)*\.)+[a-zA-Z]{2,4})|localhost|(((1\d\d|2([0-4]\d|5[0-5])|[1-9]\d|\d)\.){3}(1\d\d|2([0-4]\d|5[0-5])|[1-9]\d|\d)))(:\d{1,5})?(\/+.*)*$/;
        return patrn.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否电邮地址。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isEmail = function(p_str)
    {
    	if(!$isString(p_str)){
         	return false;
        }
        var patrn = /^[\w-]+@[\w-]+(\.[\w-]+)+$/;
        return patrn.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否邮编。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isZipCode = function(p_str)
    {
    	if(!$isString(p_str)){
         	return false;
        }
        var patrn = /^\d{6}$/;
        return patrn.test(p_str);
    };

    /**
     * 提供一个方法，判断参数是否合法日期。
     * 该方法可匹配的值类型包括：
     * <ul>
     *      <li><b>yyyy-MM-dd</b></li>
     *      <li><b>yyyy-MM</b></li>
     * </ul>
     * @param p_str
     *            要检查的值。
     */
    me.isDate = function(p_str)
    {
    	// 由于校验仅处理字符串信息，先将日期类型值转为日期格式字符串
    	if ($isDate(p_str))
    	{
    		try
    		{
    			p_str = Date.format(p_str, "yyyy-MM-dd");
    		}
    		catch(e)
    		{
    			return false;
    		}
    	}
    	
    	if(!$isString(p_str)){
         	return false;
        }
    	
        if (!/^\d{4}((\.|\/|\-)\d{1,2}){1,2}$/.test(p_str))
        {
            return false;
        }
        var r = p_str.match(/\d{1,4}/g);
        if (r == null)
        {
            return false;
        }
    	var d = new Date(r[0], r[1] - 1, r[2]);
        return (d.getFullYear() == r[0] && (d.getMonth() + 1) == r[1] && d.getDate() == r[2]);
       
    };
	
    /**
     * 提供一个方法，判断参数是否是合法日期时间
     * 该方法可匹配的值类型包括：
     * <ul>
     *      <li><b>yyyy-MM-dd hh:mm:ss</b></li>
     *      <li><b>yyyy-MM-dd hh:mm</b></li>
     * </ul>
     * @param p_str
     *            要检查的值。
     */
    me.isDateTime = function(p_str)
    {
    	// 由于校验仅处理字符串信息，先将日期类型值转为日期格式字符串
    	if ($isDate(p_str))
    	{
    		try
    		{
    			p_str = Date.format(p_str, "yyyy-MM-dd HH:mm:ss");
    		}
    		catch(e)
    		{
    			return false;
    		}
    	}
    	if(!$isString(p_str))
    	{
         	return false;
        }
    	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2})(:(\d{1,2})){1,2}$/;
        var r = p_str.match(reg);  
        if(r==null)
        {
        	return false;
        }
        else
        {
        	 r = p_str.match(/\d{1,4}/g);
             if (r == null)
             {
                 return false;
             }
             if(r.length < 5)
             {
            	 return false;
             }
             else if(r.length == 5)
             {
            	 var d = new Date(r[0], r[1] - 1, r[2],r[3],r[4]);
            	 return (d.getFullYear() == r[0] 
            	         && (d.getMonth() + 1) == r[1] 
            	         && d.getDate() == r[2] 
            	         && d.getHours() == r[3] 
            	         && d.getMinutes() == r[4]);
             }
             else if(r.length > 5)
             {
            	 d = new Date(r[0], r[1] - 1, r[2],r[3],r[4],r[5]);
            	 return (d.getFullYear() == r[0] 
    	         && (d.getMonth() + 1) == r[1] 
    	         && d.getDate() == r[2] 
    	         && d.getHours() == r[3] 
    	         && d.getMinutes() == r[4]
            	 && d.getSeconds() == r[5]);
             }
             else
             {
            	 return false; 
             }
        }   
    };
    
    /**
     * 提供一个方法，半段参数是否为合法时间 (hh:mm:ss)
     */    
    me.isTime = function(p_str)  
	{   
    	if ($isDate(p_str))
    	{
    		try
    		{
    			p_str = p_str.toTimeString().split(" ")[0];
    		}
    		catch(e)
    		{
    			return false;
    		}
    	}
    	if(!$isString(p_str)){
         	return false;
        }
	    var reg=/^(20|21|22|23|[0-1]\d)(\:[0-5][0-9]){0,2}$/;
	    return reg.test(p_str);
	};     

    /**
     * 提供一个方法，判断参数是否包含 SQL 关键字，用于防止 SQL 注入攻击。
     * 
     * @param p_str
     *            要检查的值。
     */
    me.isSqlInjection = function(p_str)
    {
    	var regex = /(\sand\s)|(\sor\s)|(\slike\s)|(select\s)|(insert\s)|(delete\s)|(update\s[\s\S].*\sset)|(create\s)|(\stable)|(\sexec)|(declare)|(\struncate)|(\smaster)|(\sbackup)|(\smid)|(\scount)|(\sadd\s)|(\salter\s)|(\sdrop\s)|(\sfrom\s)|(\struncate\s)|(\sunion\s)|(\sjoin\s)|(')/;
        var result = [];
        var successful = !regex.test(p_str);
        var matches = p_str.match(regex);
        var hint = p_str;
        if (matches && matches[0])
        {
        	hint = matches[0];
        }
        
        return {successful: successful, hint: hint};
    };
    
    /**
     * 提供一个方法，判断字符串是否为 IP 地址。
     */
    me.isIP = function(p_str)
	{
    	if(!$isString(p_str)){
         	return false;
        }
    	var patrn = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-4])$/;
    	return patrn.test(p_str);
    };
    
      /**
     * 提供一个方法，判断字符串是否为身份证号码。
     */
    me.isIDCard = function(p_str)
	{
    	if(!$isString(p_str)){
         	return false;
        }
        var patrn = /^((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\d{4})((((19|20)(([02468][048])|([13579][26]))0229))|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))((\d{3}(x|X))|(\d{4}))$/;    
        return patrn.test(p_str);
    };
   	
    me.isMaxValue = function(p_str, p_maxValue, p_isFloat)
    {
    	if (me.isDigit(p_str))
    	{
    		if (typeof p_isFloat == "undefined")
    		{
    			p_isFloat = false;
    		}
    		var value = p_isFloat ? parseFloat(p_str): parseInt(p_str);
    		var maxValue = p_isFloat ? parseFloat(p_maxValue): parseInt(p_maxValue);
    		return value > maxValue ? false: true;
    	}
    	return false;
    };
    
    me.isMinValue = function(p_str, p_minValue, p_isFloat)
    {
    	if (me.isDigit(p_str))
    	{
    		if (typeof p_isFloat == "undefined")
    		{
    			p_isFloat = false;
    		}
    		var value = p_isFloat ? parseFloat(p_str): parseInt(p_str);
    		var minValue = p_isFloat ? parseFloat(p_minValue): parseInt(p_minValue);
    		return value < minValue ? false: true;
    	}
    	return false;
    };
    
    me.isMaxLength = function(p_str, p_maxLength)
    {
		if(p_str == null)
		{
			p_str = "";
		}
		var str = p_str;
    	if (! $isString(p_str))
    	{
    		str = p_str.toString();
    	}
    	var maxLength = parseInt(p_maxLength);
    	return str.length > maxLength? false : true;
    };
    
    me.isMinLength = function(p_str, p_minLength)
    {
		if(p_str == null)
		{
			p_str = "";
		}
		var str = p_str;
    	if (! $isString(p_str))
    	{
    		str = p_str.toString();
    	}
    	var minLength = parseInt(p_minLength);
    	return str.length < minLength? false : true;
    };
    
    return me;
};


CheckUtil = new CheckUtilClass();


