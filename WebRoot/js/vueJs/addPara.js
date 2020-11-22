var app = new Vue({
    el: '#addpara',
    data(){
        return{
            tableData:[],
            currentPage:1,
            pageSize:10,
            total: 0,
            multipleSelection: [],
            parameter:{
                paraName:"",
                paraSearch:'',
                paraRelated:'',
                paratheway:'',
                paratheways:'',
                parathewayv:'',
                paraColumn:'',
                paraSort:''
            },
        }
    },
    created() {

    },
    mounted() {

    },
    methods:{
        querySub(){
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'saveOrUpdateMdMaterielParameter',
                mmpName:that.parameter.paraName,
                isSearch:that.parameter.paraSearch,
                relation:'',
                optionaValue:'',
                state:'',
                }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {

            });
        },
    }
})