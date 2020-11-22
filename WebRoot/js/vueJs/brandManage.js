var app = new Vue({
    el: '#para',
    data(){
        return{
            tableData:[],
            currentPage:1,
            pageSize:10,
            total: 0,
            multipleSelection: [],
            drawers:false,
            queryFrom:{
                brand:'',
                showIndex:''
            },
            parameter:{
                paraName:"",
                paraSearch:'',
                paraRelated:'',
                paratheway:'',
                paratheways:'',
                parathewayv:'',
                paraColumn:'',
                paraSort:'',
                lessenFileCode:'',//品牌logo
                fileCode:'',//品牌大图
                fileCodes:[]//品牌大图列表
            },
            actionsUrl:'/dentistmall/doService.htm?1=1&service=fileService&func=addFile&fileFolderId=1&jsonSysDateId=VXVOITFHQD',
            actionsUrls:'/dentistmall/doService.htm?1=1&service=fileService&func=addFile&fileFolderId=1&jsonSysDateId=VXVOITFHQD',
            fileList: [
                {name: '', url: ''}
                ],
            fileListType:'',

            fileLists: [
                {name: '', url: ''}
            ],

            options: [{
                value: '',
                label: '全部'
            }, {
                value: '1',
                label: '显示'
            }, {
                value: '2',
                label: '不显示'
            }],
            imgId:[],
            imgIds:[],
            mbdId:'',
            isShow:'',
            loading: false,
            read:false,
            xianshi:true,
            brandname:'',
            fileLogoId:'',
            fileId:'',
            fileCode:'',
        }
    },
    created() {

    },
    mounted() {
        this.Clickquery()
    },
    methods:{

        Clickquery(){
            this.loading = true
            console.log("回车查询")
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'getMdMaterielBrandInfo',
                mbdName:this.queryFrom.brand,
                state:this.queryFrom.showIndex,
                page:this.currentPage,
                limit:this.pageSize,}
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                that.tableData = jsondata.items
                that.total = jsondata.total
                that.loading = false
            });
        },
        refish(){
            this.queryFrom.brand = ''
            this.queryFrom.showIndex=''
            this.Clickquery()
        },
        findBrand(row){
            this.editRow(row)
            this.drawers = true;
            this.isShow = false
            this.read = true
        },
        switchChange(row){
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'saveBrandState',
                mbdId:row.mbaCode,
                state:row.state
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1){
                    that.$message.success('修改成功！');
                }else {
                    that.$message.error(jsondata.meg)
                }
                that.Clickquery()
            });
            console.log("111")
        },
        //删除品牌
        deleteBrand(row){
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'deleteMdMaterielBrand',
                //删除品牌传参调整
                mbdIds:row.mbaCode
                }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1){
                    that.$message.success('删除成功！');
                }else {
                    that.$message.error(jsondata.meg)
                }
                that.Clickquery()
            });
        },
        //编辑品牌
        editRow(row){
            console.log("899")
            this.fileList = []
            this.fileLists = []
            this.read = false
            this.isShow = true
            this.drawers = true;
            let that = this
            that.mbdId = row.mbaCode
            var param = {
                service: 'mdMaterielInfoService',
                func: 'getUpdateMdMaterielBrand',
                mbdId:row.mbaCode
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {

                that.parameter.paraName = jsondata.obj.mbdName
                that.brandname = jsondata.obj.mbdName
                that.parameter.paraColumn = jsondata.obj.describe
                that.parameter.paraSort = jsondata.obj.mbdManufacturer
                that.parameter.parathewayv = jsondata.obj.state
                that.parameter.lessenFileCode = jsondata.obj.lessenFileCode
                that.parameter.fileCode = jsondata.obj.fileCode
                var voye = {
                    // name:jsondata.obj.describe,//品牌logo
                    url:jsondata.obj.lessenFileCode, //品牌大图
                    id:jsondata.obj.mbdId
                }
                //宋文文 2020-08-14 修改 start
                console.log('jsondata.obj------------------->',jsondata.obj)
                var voyes = [];
                var aaa = jsondata.obj.fileCode.split(',');
                aaa.length = aaa.length-1;
                for (var i in aaa){
                    voyes[i] = {
                        url:aaa[i], //品牌大图
                        id:jsondata.obj.mbdId
                    }
                }
                that.fileLists = voyes;
                that.parameter.fileCodes = voyes;
                console.log('that.parameter.fileCodes------------------->',that.parameter.fileCodes)
                //宋文文 2020-08-14 修改 end

                //原代码
                // var voyes = {
                //     // name:jsondata.obj.describe,//品牌logo
                //     url:jsondata.obj.fileCode, //品牌大图
                //     id:jsondata.obj.mbdId
                // }
                that.fileList.push(voye)
                // that.fileLists.push(voyes)   //原代码
                if (voye.url == " "){
                    that.fileList.length = 0
                }
                // //2020-08-19 yanglei
                //原代码
                // if (voyes.url == " "){
                //     that.fileLists.length = 0
                // }

            });
        },
        deleteBara(){
            let that = this
            let mbaCode = [];
            for (let i = 0;i<this.multipleSelection.length;i++){
                mbaCode.push(this.multipleSelection[i].mbaCode)
            }
            var param = {
                service: 'mdMaterielInfoService',
                func: 'deleteMdMaterielBrand',
                mbdIds:mbaCode.join(",")
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1){
                    that.$message.success("删除成功")
                    that.Clickquery()
                }else if (jsondata.code == 2){
                    that.$message.error("品牌被引用，无法删除！")
                }else {
                    that.$message.error("操作出错，请联系管理员！")
                }
            });
        },
        addBrand(){
            console.log("111")
            const param = {
                "url":"/dentistmall/html/component/addBrand.html",
                "title":"添加品牌",
            };
            if(param.url != null && param.url !=""){
                var title = param.title?param.title:"";
                parent.window.addMenuItem(param.url,title);
            }
        },
        //品牌logo
        handleAvatarSuccess(res, file) {
                console.log("图片上传成功回调")
                for (let i=0;i<res.obj.length;i++){
                    this.imgId.push(res.obj[i].fileId)
                    this.fileList.push(res.obj[i].fileId)
                }

                this.fileListType = false
            console.log(this.fileList)
        },
        //品牌大图
        handleAvatar(res, file) {
            console.log("图片上传成功回调")
            for (let i=0;i<res.obj.length;i++){
                this.imgIds.push(res.obj[i].fileId)
            }
        },

        querySubs(){
            console.log("111")
            if (this.fileList.length == 0){
                this.$message.error("请上传品牌logo！")
                return
            }
            if (this.parameter.paraName == ''){
                this.$message.error("请输入品牌名称！")
                return
            }
            var params = {
                service: 'mdMaterielInfoService',
                func: 'deleteBrandLogo',
                mbdId:this.fileId,
                fileCode:2
            }
            let urls = utils.getServicePathByObj(params);
            utils.wxRequest(urls, null, 'post', function (jsondata) {
                if (jsondata.code==1){
                }
            });
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'saveOrUpdateMdMaterielBrand',
                mbdId:that.mbdId,
                lessenFilecodeId:that.imgId.join(","),
                filecodeId:that.imgIds.join(","),
                mbdName:that.parameter.paraName,
                describe:that.parameter.paraColumn,
                state:that.parameter.parathewayv,
                mbdManufacturer:that.parameter.paraSort
            }
            // if (that.brandname == that.parameter.paraName){
            //     param.mbdName = ""
            // }
            let url = utils.getServicePathByObj(param);
            console.log('编辑提交得参数-------------->',param)
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code==1){
                    that.Clickquery()
                    that.$message.success("编辑品牌成功")
                    that.imgIds = []
                    that.imgId = []
                    that.parameter.paraName = ''
                    that.parameter.paraColumn = ''
                    that.parameter.parathewayv = ''
                    that.parameter.parathewayv = ''

                    that.drawers = false
                }else {
                    that.$message.error(jsondata.meg)
                }
            });
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
            this.Clickquery();
        },
        handleSizeChange(val){
            this.pageSize = val;
            this.currentPage = 1;
            this.Clickquery(1, val);
            // console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val){
            this.currentPage = val;
            this.Clickquery(val, this.pageSize);
            console.log(`当前页: ${val}`);
        },
        handleRemove(file, fileList) {
            file.id = this.fileLogoId
            // let that = this
            // var param = {
            //     service: 'mdMaterielInfoService',
            //     func: 'deleteBrandLogo',
            //     mbdId:file.id,
            //     fileCode:1
            // }
            // let url = utils.getServicePathByObj(param);
            // utils.wxRequest(url, null, 'post', function (jsondata) {
            //     that.fileListType = true
            //     if (jsondata.code==1){
            //         that.imgId = []
            //         this.fileList = []
            //     }
            // });
        },
        handleRemoves(file, fileLists) {
            this.fileId = file.id
            this.fileCode = 2
            // let that = this
            // var param = {
            //     service: 'mdMaterielInfoService',
            //     func: 'deleteBrandLogo',
            //     mbdId:file.id,
            //     fileCode:2
            // }
            // let url = utils.getServicePathByObj(param);
            // utils.wxRequest(url, null, 'post', function (jsondata) {
            //     if (jsondata.code==1){
            //     }
            // });
            // console.log(this.fileListType)
        },
        handlePreview(file) {
            console.log(file);
        },
        // 限制输入框输入的字符数
        widthCheck (str, len) {
            var temp = 0
            for (var i = 0; i < str.value.length; i++) {
                if (/[\u4e00-\u9fa5]/.test(str.value[i])) {
                    temp += 2
                } else {
                    temp++
                }
                if (temp > len) {
                    str.value = str.value.substr(0, i)
                }
            }
        }
    }
})
