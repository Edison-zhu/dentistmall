var app = new Vue({
    el: '#addBrand',
    data(){
        return{
            tableData:[],
            currentPage:1,
            pageSize:10,
            total: 0,
            img:"",
            actionsUrl:'/dentistmall/doService.htm?1=1&service=fileService&func=addFile&fileFolderId=1&jsonSysDateId=VXVOITFHQD',
            actionsUrls:'/dentistmall/doService.htm?1=1&service=fileService&func=addFile&fileFolderId=1&jsonSysDateId=VXVOITFHQD',
            imageUrl: '',
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
            fileList: [],
            fileLists:[],
            imgId:[],
            imgIds:[],

        }
    },
    created() {

    },
    mounted() {

    },
    methods:{
        handleAvatar(res, file) {
            console.log("品牌logo")
            for (let i=0;i<res.obj.length;i++){
                this.imgId.push(res.obj[i].fileId)
            }

        },
        handleAvatarSuccessz(res, file){
            console.log("111")
            for (let i=0;i<res.obj.length;i++){
                this.imgIds.push(res.obj[i].fileId)
            }
        },
        handleAvatarSuccess(res, file) {
            console.log(res)
            this.imageUrl = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
            console.log("111")
            const isJPG = file.type === 'image/jpeg';
            const idJpg = file.type === 'image/png';
            const isLt2M = file.size / 1024 / 1024 < 2;

            // if (!isJPG && !idJpg) {
            //     this.$message.error('上传头像图片只能是 JPG 格式!');
            // }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        querySubs(){
            console.log("111")
            if(this.parameter.paraName == ""){
                this.$message.error("请将品牌信息填写完整！")
                return
            }
            if (this.imgId.length == 0){
                this.$message.error("图片logo必须上传！")
                return
            }
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'saveOrUpdateMdMaterielBrand',
                lessenFilecodeId:that.imgId.join(","),
                filecodeId:that.imgIds.join(","),
                mbdName:that.parameter.paraName,
                describe:that.parameter.paraColumn,
                state:that.parameter.parathewayv,
                mbdManufacturer:that.parameter.paraSort
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code==1){
                    that.$message.success("添加品牌成功")
                    that.imgIds = []
                    that.imgId = []
                    that.showTtemWin()
                }else if(jsondata.code==0){
                    that.$message.error("服务端异常，请联系管理员！")
                }else if (jsondata.code==2){
                    that.$message.error("品牌名称重复！")
                }
            });
        },
        //查询方法
        querySub(){
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'getMdMaterielBrandInfo',
                mbdName:this.parameter.paraName,//品牌名称
                lessenFilecodeId:'',//
                filecodeId:'',//
                describe:this.parameter.paraColumn,
                state:this.parameter.parathewayv,//
                mbdManufacturer:this.parameter.paraSort,
                }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                that.tableData = jsondata.items
                that.total = jsondata.total
            });
        },
        //跳转添加品牌
        showTtemWin(){
            const a = {
                "url":"/dentistmall/html/brandManage.html",
                "title":"品牌管理",
            }
            if(a.url != null && a.url !=""){
                var title = a.title?a.title:"";
                parent.window.addMenuItem(a.url,title);
            }
            console.log("111")
            const param = {
                "url":"/dentistmall/html/component/addBrand.html",
                "title":"添加品牌",
            };
            if(param.url != null && param.url !=""){
                var title = param.title?param.title:"";
                parent.window.closeMenuItem(param.url,title);
            }
        },
        //复选框按钮
        toggleSelection(rows) {
            console.log("111")
            if (rows) {
                rows.forEach(function(row)  {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange(val) {
            console.log("222")
            this.multipleSelection = val;
            // console.log(JSON.stringify(this.multipleSelection))
        },
        currentChange(currentPage) {
            this.page = currentPage;
            this.selectQuery();
        },
        handleSizeChange(val){
            this.pageSize = val;
            this.currentPage = 1;
            this.selectQuery(1, val);
            // console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val){
            this.currentPage = val;
            this.selectQuery(val, this.pageSize);
            console.log(`当前页: ${val}`);
        },
        handleRemoves(){
            this.imgId = []
        },
        handleRemove(file, fileList) {

            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        }
    }
})