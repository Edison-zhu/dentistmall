 //校验是否全由数字组成


function isDigit(s)
{
var patrn=/^[0-9]{1,20}$/;
if (!patrn.exec(s)) return false
return true
}

//校验登录名：只能输入5-20个以字母开头、可带数字、“_”、“.”的字串


function isRegisterUserName(s)
{
var patrn=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
if (!patrn.exec(s)) return false
return true
}

//校验用户姓名：只能输入1-30个以字母开头的字串


function isTrueName(s)
{
var patrn=/^[a-zA-Z]{1,30}$/;
if (!patrn.exec(s)) return false
return true
}

//校验密码：只能输入6-20个字母、数字、下划线


function isPasswd(s)
{
var patrn=/^(w){6,20}$/;
if (!patrn.exec(s)) return false
return true
}

//校验普通电话、传真号码：可以“+”开头，除数字外，可含有“-”


function isTel(s)
{
//var patrn=/^[+]{0,1}(d){1,3}[ ]?([-]?(d){1,12})+$/;
var patrn=/^[+]{0,1}(d){1,3}[ ]?([-]?((d)|[ ]){1,12})+$/;
if (!patrn.exec(s)) return false
return true
}

//校验手机号码：必须以数字开头，除数字外，可含有“-”


function isMobil(s)
{
var patrn=/^[+]{0,1}(d){1,3}[ ]?([-]?((d)|[ ]){1,12})+$/;
if (!patrn.exec(s)) return false
return true
}

//校验邮政编码


function isPostalCode(s)
{
//var patrn=/^[a-zA-Z0-9]{3,12}$/;
var patrn=/^[a-zA-Z0-9 ]{3,12}$/;
if (!patrn.exec(s)) return false
return true
}
 

//校验是否IP地址


function isIP(s) //by zergling
{
var patrn=/^[0-9.]{1,20}$/;
if (!patrn.exec(s)) return false
return true
} 
//校验邮箱地址格式是否合法
function isMail(s) //by zergling
{
	var patrn= /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	alert(patrn);
	if (!patrn.exec(s)) return false
	return true
} 


 var Validate = { 
            isTel: function (s) {
                var patrn = /^((\+?86)|(\(\+86\)))?\d{3,4}-\d{7,8}(-\d{3,4})?$/
                if (!patrn.exec(s)) return false
                return true
            },

            isMobile: function (value) {
                var validateReg = /^((\+?86)|(\(\+86\)))?1\d{10}$/;
                return validateReg.test(value);
            },

            cellPhone: function (opt) {
                var cellPhoneNumber =opt.val();
                if (!Validate.isMobile(cellPhoneNumber)) { 
                    $.supper("alert",{ title:"操作提示", msg:"手机号码格式不正确！"}); 
                    return false;
                } else {
                   return true;
                }
            }, 
            telePhone: function () {
                var telePhoneNumber = $("#txttelePhone").val();
                if (!Validate.isTel(telePhoneNumber)) { 
                     $.supper("alert",{ title:"操作提示", msg:"电话号码格式不正确！"}); 
                    return false;
                } else {
                   return true;
                }
            },
            email: function (opt) {
                var mail = opt.val();
                alert(Validate.isMail(mail));
                if (!Validate.isMail(mail)) { 
                     $.supper("alert",{ title:"操作提示", msg:"邮箱格式不正确！"}); 
                    return false;
                } else {
                   return true;
                }
            }
             
        }