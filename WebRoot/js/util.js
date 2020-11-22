// import axios from './axios'



//const baseUrl = 'http://192.168.1.98:8080/'//本地
//const baseUrl = 'http://192.168.1.115:8080/'//杨枫
//const baseUrl = 'http://192.168.1.119:8080/'//本地119
// const baseUrl = 'https://show.cnyyk.cn/'//测试服
//const baseUrl = 'https://www.cnyyk.cn/'//正式
var test = window.location.protocol;
var test2 = window.location.host;
const baseUrl = `${test}//${test2}/`;
// const baseUrl = 'http://localhost:8080/'//测试服
// const baseUrl = 'https://47.103.1.219:8081/'//47
// const baseUrl = 'http://192.168.1.93:8080/'//杨磊

const urlApi = `${baseUrl}dentistmall/doService.htm?1=1` // 需要token
const noFilterApi = `${baseUrl}dentistmall/doNoFilterApi.htm?1=1`  //不需要token
const weChatLoginApi = `${baseUrl}dentistmall/weChatLoginApi.htm?1=1`//不需要token
const loginApi = `${baseUrl}dentistmall/shoppingLoginApi.htm?1=1` // 不需要token
const checkLoginApi = `${baseUrl}dentistmall/doApi.htm?1=1&service=sysLoginApiService&func=getCheckLogin`

const instance = (url, data, method = 'post', func, isLoading) => new Promise((resolve, reject) => {
    console.log(url);
    console.log(test);
    console.log(test2);
    if (method === void 0) { method = "POST"; }
    if (url === undefined || url === null) {url = checkLoginApi}
    const token = '';//wx.getStorageSync('token')
    const sessionId = '';//wx.getStorageSync('sessionId')
    axios({
        url: url,
        method: method,
        data: data,
        async: isLoading == undefined ? true : !isLoading,
        header: {
            token: `Breazer ${token}`,
            sessionId: sessionId
        },
        success: function (res) {
            resolve(res);
        },
        "fail": function (e) {
            reject(e)
        }
    }).then((response) => {
        const jsonData = response.data
        if (func !== undefined) { func(jsonData) }
    }).catch((err) => {
        const error = {
            err,
            code: -1
        }
        if (func !== undefined) { func(error) }
    });
    // axios({
    //   method,
    //   headers: {
    //   //   // 跨域请求 这个配置不能少
    //     'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
    //     Accept: 'application/json',
    //     token: `Breazer ${localStorage.getItem('token')}`
    //   },
    //   // data,
    //   // headers: { 'Access-Control-Allow-Origin': 'http://localhost:8081' },
    //   url
    //   // url: urlApi + url
    // }).then((response) => {
    //   resolve(response)
    // }).catch((err) => {
    //   reject(err)
    // })
})

const getServicePath = (val) => {
    let strBuffer = ''
    const ajaxUrl = urlApi
    strBuffer += ajaxUrl
    if (val.service) {
        // 1.处理service字符串
        const attrService = val.service.split('.')
        if (attrService.length === 1) {
            val.service = attrService[0]
        }
        if (attrService.length > 1) {
            val.service = attrService[attrService.length - 2]
            val.func = attrService[attrService.length - 1]
        }
        val.service = (val.service).replace(/\s[A-Z]/g, $1 => $1.toLowerCase()).replace(/^[A-Z]/, $1 => $1.toLowerCase())
        strBuffer += `&service=${val.service}`
    }
    if (val.func) {
        strBuffer += `&func=${val.func}`
    }

    if (val.data) {
        strBuffer += `&${val.data}`
    }
    return strBuffer
}

function getParam(obj) {
    let path = ''
    for (const key in obj) {
        // eslint-disable-next-line no-prototype-builtins
        if(typeof obj[key] === 'object' ){
            path += getParam(obj[key])
        } else  if (obj.hasOwnProperty(key)) {
            path += `${key}=${obj[key]}&`
        }
    }
    return path
}

function getPath(obj, url) {
    let path = ''
    // eslint-disable-next-line no-restricted-syntax
    path += getParam(obj)
    path = path.substring(0, path.length - 1)
    path = `${url}&${path}`
    return path
}

const getServicePathByObj = obj => getPath(obj, urlApi)

const getNoFilterServicePathByObj = obj => getPath(obj, noFilterApi)

const getWeChatLoginServicePathByObj = obj => getPath(obj, weChatLoginApi)

const getLoginServicePathByObj = obj => getPath(obj, loginApi)

const formatTime = date => {
  var date = new Date()
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatDate = date => {
  const year = date.year + 1900
  const month = date.month + 1
  const day = date.day
  const hour = date.hours
  const minute = date.minutes
  const second = date.seconds
  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

const axiosWx = function (url, data, callBack, method) {
  if (method === void 0) { method = "POST"; }
  wx.request({
      url: url,
      method: method,
      data: data,
      success: function (res) {
          callBack(res);
      },
      "fail": function (e) {
          console.log(e, 999);
          if (e.errMsg === "request:fail abort")
              return;
          wx.showModal({
              title: '提示',
              content: '请求失败,请检查网络',
              showCancel: false,
              confirmColor: '#0f77ff',
              success: function (_res) {
              }
          });
      }
  });
};


// 访问api
const wxRequest = (url, data, method, func, isLoading) => {
  const p = instance(url, data, method, func, isLoading)
  p.then((response) => {
    const jsonData = response.data
    if (func !== undefined) { func(jsonData) }
  }).catch((err) => {
    const error = {
      err,
      code: -1
    }
    if (func !== undefined) { func(error) }
  })
}

const checkLogin = (func) => {
  wxRequest(null, null, 'POST', func)
}

// 获取完整的请求地址，传入的是字符串
// const getServicePath = (val) => {
//   const path = axios.getServicePath(val)
//   return path
// }
//
// // 另一种获取完整请求地址，传入是类,目前使用带ByObj的方法
// /**
//  * 获取doApi，需要token验证
//  * @param {*} val
//  */
// const getServicePathByObj = (val) => {
//   const path = axios.getServicePathByObj(val)
//   return path
// }
//
// /**
//  * 获取weChatLoginApi，登陆相关的接口，不需要token
//  * @param {*} val
//  */
// const getWeChatLoginServicePathByObj = (val) => {
//   const path = axios.getWeChatLoginServicePathByObj(val)
//   return path
// }
//
// /**
//  * 获取shoppingLoginApi，登陆相关的接口，不需要token
//  * @param {*} val
//  */
// const getLoginServicePathByObj = (val) => {
//   const path = axios.getLoginServicePathByObj(val)
//   return path
// }
//
// /**
//  * 获取doNoFilterApi，不需要token的
//  * @param {*} val
//  */
// const getNoFilterServicePathByObj = (val) => {
//   const path = axios.getNoFilterServicePathByObj(val)
//   return path
// }

// 获取页面传递的值，弃用
const getParams = (selector) => {
  const params = selector.$route.params
  return params
}

const showToast = (title, icon, duration, mask) => {
  wx.showToast({
    title: title,
    icon: icon,
    duration: duration === undefined ? 2000 : duration,
    mask: mask === undefined ? false : mask
  })
}

utils = {
    formatTime: formatTime,
    formatDate,
    // axios: axios,
    wxRequest,
    getServicePath,
    getParams,
    getServicePathByObj,
    getWeChatLoginServicePathByObj,
    getLoginServicePathByObj,
    getNoFilterServicePathByObj,
    showToast,
    checkLogin
}
// module.exports = {
//   formatTime: formatTime,
//   formatDate,
//   axios: axios,
//   wxRequest,
//   getServicePath,
//   getParams,
//   getServicePathByObj,
//   getWeChatLoginServicePathByObj,
//   getLoginServicePathByObj,
//   getNoFilterServicePathByObj,
//   showToast,
//   checkLogin
// }
