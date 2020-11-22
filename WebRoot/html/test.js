// import utils  from '../js/util'
// import {getServicePath} from "../js/util";

function find111Page() {
    //分页参数
    var param = {
        service: 'sysWebsiteHotSearchService',
        func: 'getSysWebsiteHotSearchList',
        // currentPage:this.pagination.currentPage,//页码
        page:1,//每页显示的记录数
        limit:20//查询条件
    };
    //请求后台
    let url = utils.getServicePathByObj(param);
    console.log(url)
    utils.wxRequest(url, null, 'post', function (jsondata) {
        console.log(jsondata);
    });
    // axios.post("/setmeal/findPage.do",param).then((response)=> {
    //     //为模型数据赋值，基于VUE的双向绑定展示到页面
    //     this.dataList = response.data.rows;
    //     this.pagination.total = response.data.total;
    // });
}