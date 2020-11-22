var app = new Vue({
    el: '#apps',
    data() {
        return {
            tableData: [],
            currentPage: 1,
            pageSize: 10,
            total: 0,
            multipleSelection: [],
            mmtId1:'',
            mmtId2:'',
            mmtId3:'',
            matType1: '',
            matType2: '',
            matType3: '',
            readonly: true,
            modelId:{
                aliasName:'',
                matTypeName1:'',
                modelMatName: '',
                introduction: '',
                modelMatCode: '',
                commodityIcon: '',
                keyWord: '',
                remark: '',
                state: '',
                brand: '',
                units: '',
                registrationNumber: '',
                Registration: '',
                itemNo: '',
                productStandards: '',
                material: '',
                productPackaging: '',
                manufacturer: '',
                origin: '',
                mainingredients: '',
                ProductUsage: '',
                Shelflife: '',
                otherinstructions: '',
                mbdName:'',
            },
            selectItem:[{

            }],
            bangding1:[],
            user: {},
            shuxiang: [
                {
                    value: '',
                    label: '',
                    optionalValue: ''
                }
            ],
            shuxiangs: [
                {
                    value: '',
                    label: '',
                    optionalValue: ''
                }
            ],
            shuxing2: [
                {}
            ],
            dynamicTagses: [],
            addModels: {
                attributes: '',
                specifications: '',

                chooseBrand: '',
                sale: '',
            },
            dynamicTags: [],
            brandListMosel:'',
            brandList:[{}],
            sellUint:'',
            image:[],
            canSearch: '',
        }
    },
    created() {
        this.getGuige()
    },
    mounted() {
        this.querySub();
        this.querySelect();
        this.getGuige()
        this.selectAttr()
        // this.getAttr()
    },
    methods: {
        querySelect(){
            let that = this
            var param = {
                service: 'mdModelMaterielService',
                func: 'getMdModelMaterielInfoList',
                wmsModelId:that.user.wmsModelId
            }
            let url = utils.getServicePathByObj(param);
            var vote = {
                name: '',
                value: [],
                id: '',
                state: '',
            };
            utils.wxRequest(url, null, 'post', function (jsondata) {
                that.selectItem = jsondata.obj
                for (let i = 0;i<jsondata.obj.length;i++){
                    that.bangding1.push(jsondata.obj[i].mmpContent)
                }
                console.log(that.selectItem)
            });
        },
        querySub() {
            console.log("获取图片")
            this.user = JSON.parse(window.sessionStorage.getItem("user"))
            let that = this
            var param = {
                service: 'mdModelMaterielService',
                func: 'getMdModelMaterielInfo',
                wmsModelId:that.user.wmsModelId
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                for (let i =0;i<jsondata.obj.pathList.length;i++){
                    that.image[i] = jsondata.obj.pathList[i]
                }
                that.sellUint = jsondata.obj.sellUnit
                that.modelId = jsondata.obj
                that.mmtId1 = jsondata.obj.matType1
                that.mmtId2 = jsondata.obj.matType2
            });
        },
        getGuige(){
            let that = this
            var param = {
                service: 'mdModelMaterielFormatService',
                func: 'getMdModelMaterielFormatList',
                wmsModelId:that.user.wmsModelId
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                console.log("获取规格")
                for (let i = 0;i<jsondata.length;i++){
                    that.dynamicTags.push(jsondata[i].mmfName)
                    // that.dynamicTags[i] = jsondata[i].mmfName
                    // that.addModels.specifications = jsondata[i].mmfName.split(",")
                }

            });
        },
        selectAttr(){
            console.log("获取属性")
            let that = this
            var dataId = {
                service: 'mdModelMaterielFormatService',
                func: 'getMdModelAttrs',
                wmsModelId:that.user.wmsModelId
            }
            let data = utils.getServicePathByObj(dataId);
            utils.wxRequest(data, null, 'post', function (jsondata) {
                for (let i = 0;i<jsondata.obj.length;i++){
                    if (jsondata.obj[i].canSearch !==1){
                        that.canSearch = '0'
                    }else {
                        that.canSearch = '1'
                    }
                    // that.canSearch = jsondata.obj[i].canSearch
                    that.shuxiang = jsondata.obj[i].mmaName
                    that.dynamicTagses=jsondata.obj[i].attrContent.split(",")
                }
                console.log("多选框的值"+that.canSearch)
            });
        },
        changeStatus(val, index) {

        },
        changeStatusToo(val, index) {
            console.log(val)
        },
        showTtemWin() {
            console.log("111")
            const param = {
                "url": "/dentistmall/html/addPara.html",
                "title": "添加参数",
            };
            if (param.url != null && param.url != "") {
                var title = param.title ? param.title : "";
                parent.window.addMenuItem(param.url, title);
            }
        },
        goBack(){
            const a = {
                "url":"/dentistmall/html/productInfo.html",
                "title":"商品库管理",
            }
            if(a.url != null && a.url !=""){
                var title = a.title?a.title:"";
                parent.window.addMenuItem(a.url,title);
            }
            console.log("111")
            const param = {
                "url":"/dentistmall/html/component/productDetails.html",
                "title":"查看详情",
            };
            if(param.url != null && param.url !=""){
                var title = param.title?param.title:"";
                parent.window.closeMenuItem(param.url,title);
            }
        }

    }
})