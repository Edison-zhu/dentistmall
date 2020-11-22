var app = new Vue({
    el: '#apps',
    data() {
        return {
            matType1: '',
            matType2: '',
            matType3: '',
            wzId: '',
            suiId: '',
            total:'',
            totals:0,
            gridDatas: [],
            tableData: [],
            pathList: [],
            modelId: {
                aliasName: '',
                modelMatName: '',
                introduction: '',
                modelMatCode: '',
                commodityIcon: '',
                keyWord: '',
                remark: '',
                state: '',
                Brand: '',
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
                weight: '',
                otherinstructions: '',
                matTypeName1: '',
                matTypeName2: '',
                matTypeName3: '',
            },
            user: {},
            shuxiang: [
                {
                    value: '',
                    label: '',
                    optionalValue: ''
                }
            ],
            shuxing2: [
                {}
            ],
            para: [],
            shuxing2s: [],
            dynamicTagses: [],
            dynamicTags: [],
            dynamicTagsId: [],
            num: -1,
            words: '',
            selectItem: [],
            bangding1: [''],
            dialogVisible3: false,
            dialogFormVisible6: false,
            gridDatas2: [],
            searchs: '',
            moreCanshu: {
                mmpIds: '',
                mmpContents: '',
                addPara: '',
            },
            canshuId: [],
            ModelcanshuId: [],
            attrId: [],
            tableDatas: [],
            AttrList: '1',
            multiplesToo: '',
            AttrListToo: [],
            addModels: {
                attributes: '',
                specifications: '',
                retrieval: '',
                chooseBrand: '',
                sale: '',
            },
            sele: '',
            brandloading: false,
            brandListMosel: '',
            brandList: [{}],
            sellUint: '',
            imgs: [], // 图片列表
            currentImgPage: 1, // 当前页码
            imgPageSize: 10, // 显示条数
            sysFolderList: [], // 文件夹列表
            FolderList: [], // 选择后的文件夹
            sysFfId: 0, // 文件夹id
            selectedImg: [], // 选择的图片信息
            rootPathList: [], // 选择后的路径信息
            imgLimit: 6, // 图片限制，动态改变
            fixImgLimit: 6, // 图片固定限制，根据需求改变
            checkboxGroup: [], // 暂时不用
            imgCheck: [], // 暂时不用
            actionsUrl: '/dentistmall/doService.htm?1=1&service=fileService&func=addFile&fileFolderId=1&jsonSysDateId=VXVOITFHQD',
            dialogVisible6: false, // 选择图片弹出框
            fileId: [], // 文件id
            fileList: [], // 文件列表
            imgIds: [],
            createDate: '',

            upPicList: [], // 上传的图片最终列表
            finalPicList: [],  //最终图片列表
            finalPicIdSelect: [], //选择图片ID
            finalPicIdUpload: [], //上传图片ID
            finalPicId: [],  //最终图片ID
            allow:'',
            filterable:'',
            code1:'',
            code2:'',
            code3:'',
            maxCheck:6,
            checkeds:[],
            sysFolderListlabel:'',
            brandId:'',
            isRequired:'',
            mustEdit:[],

        }
    },
    computed:{
        eleDateNew() {
            return JSON.parse(JSON.stringify(this.imgs))
        },
    },
    created() {

    },
    mounted() {
        this.selectBrand()
        this.querySub();
        this.querySelect()
        this.editg()
        this.getAttr()
    },
    methods: {
        deleteFinalPic(e,item){
            this.finalPicList.splice(this.rootPathList.indexOf(item), 1);
            this.selectedImg.splice(e,1)
            // this.fileList.splice(this.fileList.indexOf(item),1)
            // console.log('删除----------------->', e)
            // var aaa = this.finalPicList;
            // for (var i = 0; i < aaa.length; i++) {
            //     if (i == e){
            //         aaa.splice(i,1)
            //     }
            // }
            // this.finalPicList = aaa;
            // var bbb = this.finalPicId;
            // for (var i = 0; i < bbb.length; i++) {
            //     if (i == e){
            //         bbb.splice(i,1)
            //     }
            // }
            // this.finalPicId = bbb;
        },
        //选择图片窗口完成按钮
        finalPic() {
            //图片列表
            // var aaa = this.rootPathList.concat(this.upPicList);
            // var bbb = this.finalPicList;
            // var ccc =[];
            // for (var i = 0; i < aaa.length; i++){
            //     var flag = true;
            //     for (var j = 0; j < bbb.length; j++){
            //         if (aaa[i] == bbb[j]){
            //             flag = false;
            //         }
            //     }
            //     if (flag == true) {
            //         ccc.push(aaa[i]);
            //     }
            // }
            // var list = bbb.concat(ccc);
            // // this.finalPicList = this.finalPicList.concat(ccc);
            // if(list.length > 6){
            //     // console.log('finalPicList----11------>', this.finalPicList)
            //     this.$message.error('图片最多保存6张！');
            //     return
            // }else {
            //     this.finalPicList = list;
            //     // console.log('finalPicList----22------>', this.finalPicList)
            // }

            //ID列表
            // var id1 = this.finalPicIdSelect;
            // var id2=id1.filter((value,index,arr)=>{
            //     return arr.indexOf(value)==index
            // })
            // var ida = id2.concat(this.finalPicIdUpload);
            // var idb = this.finalPicId;
            // var idc =[];
            // for (var i = 0; i < ida.length; i++){
            //     var flag2 = true;
            //     for (var j = 0; j < idb.length; j++){
            //         if (ida[i] == idb[j]){
            //             flag2 = false;
            //         }
            //     }
            //     if (flag2 == true) {
            //         idc.push(ida[i]);
            //     }
            // }
            // if (this.finalPicId[0] == ''){
            //     this.finalPicId.length=0
            // }
            // this.finalPicId = this.finalPicId.concat(idc);
            // console.log('finalPicId---------->', this.finalPicId)
            // this.selectedImg.length == 0
        },
        // 打开图片选择框
        openImgSelect() {
            this.maxCheck = 6
            this.dialogVisible6 = true;
            this.initImgList(1);
            // this.selectFolder(1)
            this.initFolerList();
            this.FolderList = 1
            console.log(this.selectedImg)
        },
        // 刷新图片
        initImgList(folderId) {
            let that = this
            var param = {
                service: 'fileService',
                func: 'getSysFileInfoPagerModel',
                limit: this.imgPageSize,
                page: this.currentImgPage
            }
            if (folderId != undefined && folderId != 0) {
                param.fileFolderId = folderId;
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                console.log("打开图片")
                that.imgs = jsondata.items;
                for (let i=0;i<that.imgs.length;i++){
                    that.imgs[i].fileId = that.imgs[i].fileId+''
                }
                that.totalImage = jsondata.totalCount;
            });
        },
        // 分页展示条数改变
        handleImgSizeChange(value) {
            this.imgPageSize = value;
            this.currentImgPage = 1;
            this.initImgList(this.sysFfId);
        },
        // 分页下一页上一页
        handleCurrentImgChange(value) {
            this.currentImgPage = value;
            this.initImgList(this.sysFfId);
        },
        // 刷新文件夹列表
        initFolerList() {
            let that = this
            var param = {
                service: 'fileService',
                func: 'getFileFolderInfoList'
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == '1') {
                    that.sysFolderList = jsondata.obj;
                    for (let idx in that.sysFolderList) {
                        that.sysFolderListlabel = that.sysFolderList[0].fileFolderName
                        that.sysFolderList[idx].label = that.sysFolderList[idx].fileFolderName
                        that.sysFolderList[idx].value = that.sysFolderList[idx].fileFolderId
                    }
                }
            });
        },
        folderFocus() {
            // this.initFolerList()
        },
        // 选择文件夹
        selectFolder(val) {
            console.log("选择文件夹")
            console.log(this.FolderList)
            let ffId = val;
            this.sysFfId = ffId;
            this.initImgList(this.sysFfId);
            let obj = {};
            obj = this.sysFolderList.find((item)=>{//遍历list的数据
                return item.value === val;//筛选出匹配数据
            });
            this.sysFolderListlabel = obj.label
        },
        handleCloseImage(tag,index){
            this.fileList.splice(this.fileList.indexOf(tag),1)
            this.rootPathList.splice(this.rootPathList.indexOf(tag), 1);
            this.selectedImg.splice(index,1)

            console.log(this.selectedImg)
            console.log(this.rootPathList)
        },
        // 选择线上图片值改变的时候
        imgChange(index,item) {
            if (this.selectedImg.length != this.rootPathList.length+1){
                this.rootPathList.splice(this.rootPathList.indexOf(item.rootPath),1)
            }else {
                var picarr=[];
                let imgs = this.imgs;
                for (var idx in imgs) {
                    if (this.selectedImg.indexOf(imgs[idx].fileId) >= 0) {
                        picarr.push(imgs[idx].rootPath);
                    }
                }
                let tempArray = [];
                for (var idx in picarr) {
                    if (this.rootPathList.indexOf(picarr[idx]) < 0) {
                        tempArray.push(picarr[idx]);
                    }
                }
                this.rootPathList = this.rootPathList.concat(tempArray);
            }
            console.log(this.selectedImg)

            // var picarr=[];
            // let imgs = this.imgs;
            // for (var idx in imgs) {
            //         picarr.push(imgs[idx].rootPath);
            // }
            // let tempArray = [];
            // for (var idx in picarr) {
            //     if (this.finalPicList.indexOf(picarr[idx]) < 0) {
            //         tempArray.push(picarr[idx]);
            //     }
            // }
            // this.finalPicList = this.finalPicList.concat(tempArray);
        },
        // 取消选中
        deSelectImg(fileId) {
            this.imgCheck[fileId] = false
        },
        testclc() {
            let imgs = this.imgs
            console.log(imgs, "======")
            for (let idx in imgs) {
                if (this.selectedImg.indexOf(imgs[idx].fileId) >= 0)
                    imgs[idx].checked = false;
            }

            for (let idx in imgs) {
                imgs[idx].checked = false;
            }
            this.imgs = imgs
            console.log(this.imgs);
            // this.imgs = [];
            // for (let idx in imgs)
            //     this.imgs.push(imgs[idx])
        },
        beforeUpload(file){
            if (this.rootPathList.length >=6){
                this.$message.error("商品图片只能上传六张！")
                return false
            }
        },
        handleAvatarSuccess(res, file) {
            for (let i = 0; i <= res.obj.length; i++) {
                this.selectedImg.push(res.obj[i].fileId+'')
                // this.fileId.push(res.obj[i].fileId)
                this.rootPathList.push(res.obj[i].rootPath);
            }
            console.log(this.fileId)
            console.log(this.selectedImg)
        },
        handlePreview(file) {
            console.log(file);
        },
        handleRemove(file, fileList) {

            console.log(file, fileList);
        },
        selectAttr() {
            console.log("chaxun")
            this.shuxing2 = []
            this.shuxing2s = []
            let that = this
            //查询属性相关信息
            const params = {
                "service": "mdMaterielInfoService",
                "func": "getmdMaterielAttributeInfo",
                "state":"1",
                "limit": '',
                "page": ''
            }
            let urls = utils.getServicePathByObj(params);
            utils.wxRequest(urls, null, 'post', function (jsondata) {
                var b = []
                b = jsondata.items
                let a = []
                for (let i = 0; i < b.length; i++) {
                    var attr = {
                        value: b[i].mmaCode,
                        label: b[i].mmaName,
                        optionalValue: b[i].optionalValue
                    };
                    that.shuxing2.push(attr)
                    that.shuxing2s.push(attr)
                }
            })
            //删除数组第一个元素
            that.shuxing2.shift()
            that.shuxing2s.shift()
            //
            console.log(that.shuxing2s)
        },
        add() {
            if (this.sele == '') {
                this.$message.error('规格名不能为空！');
                return;
            }
            if (this.dynamicTags.indexOf(this.sele) >= 0) {
                this.$message.error('规格名重复！');
                return;
            }
            this.dynamicTags.push(this.sele)
        },
        subAttr() {
            if (this.AttrList.length == 0 || this.AttrList == ''){
                this.$message.error("请配置可选值！")
                return
            }
            this.dynamicTagses = []
            console.log("多选值列表" + this.AttrList)
            if (Array.isArray(this.AttrList) == true) {
                this.dynamicTagses = this.AttrList
            } else {
                this.dynamicTagses.push(this.AttrList)
            }
            this.dialogFormVisible6 = false
            this.AttrList = ''
        },
        cancel(){
            console.log("111")
            if (Array.isArray(this.AttrList) == true){
                if (this.AttrList.length == 0 ){
                    this.$message.error("请配置属性值")
                    return
                }
            }else if (this.AttrList == ''){
                this.$message.error("请配置属性值")
                return

            }else {
                this.dialogFormVisible6 = false
                this.AttrList = ''
            }
        },
        changeAttr(val) {
            if (val == ''){
                this.dynamicTagses.length = 0
                this.attrId = ''

            }else{
                //关联属性id拼接
                this.attrId = val
                this.AttrListToo = []
                console.log("关联属性id" + this.attrId)
                let that = this;
                let param = {
                    service: 'mdMaterielInfoService',
                    func: 'findAttributeOptionalValue',
                    mmaId: val,
                }
                that.dialogFormVisible6 = true
                let url = utils.getServicePathByObj(param);

                utils.wxRequest(url, param, 'post', function (jsondata) {
                    if (jsondata.code == 1) {
                        if (jsondata.obj.optionalValues != '') {
                            that.AttrListToo = jsondata.obj.optionalValues.split(",")
                        } else {
                            that.dynamicTagses = []
                        }
                        if (jsondata.obj.isOptional == '1') {
                            that.multiplesToo = false
                            // that.filterable = false
                            // that.allow = false
                            console.log("唯一")
                        }
                        if (jsondata.obj.isOptional == '2') {
                            that.multiplesToo = false
                            // that.filterable = false
                            // that.allow = false
                            console.log("单选属性")
                        }
                        if (jsondata.obj.isOptional == '3') {
                            that.multiplesToo = true
                            // that.filterable = true
                            that.allow = false
                            console.log("复选模式")
                        }
                        if (jsondata.obj.inputModel == '1' && jsondata.obj.isOptional == '2') {
                            that.multiplesToo = false
                            that.filterable = true
                            that.allow = true
                            console.log("手动模式")
                        }
                        if (jsondata.obj.inputModel == '1' && jsondata.obj.isOptional == '1') {
                            that.multiplesToo = false
                            that.filterable = true
                            that.allow = true
                            console.log("手动模式")
                        }
                        if (jsondata.obj.inputModel == '1' && jsondata.obj.isOptional == '3') {
                            that.multiplesToo = true
                            that.filterable = true
                            that.allow = true
                            console.log("手动模式")
                        }



                    }
                })
            }

        },
        selectBrand() {
            this.brandloading = true
            console.log("查询品牌")
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'getMdMaterielBrandInfo',
                state:'1',
                page: '1',
                limit: '10',
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                that.brandList = jsondata.items

                that.brandloading = false
            });
        },
        selectBrands(val){
            this.brandId = val
        },
        handleCanshu(row) {
            console.log("查询参数")
            var vote = {
                name: '',
                value: [],
                id: '',
                state: '',
                create: ''
            };
            if (row.inputMode == 1) {
                vote.create = true
                vote.state = false
            } else if (row.inputMode == 2) {
                vote.create = false
                vote.state = true
            } else if (row.inputMode == 3) {
                vote.create = false
                vote.state = false
            }
            vote.id = row.mmpCode;
            vote.name = row.mmpName;
            vote.isRequired = row.isRequired
            vote.value = row.optionaValue.split(",");
            for (let i = 0; i < this.selectItem.length; i++) {
                if (row.mmpCode == this.selectItem[i].id) {
                    this.$message.error("该参数已配置，请勿重复配置！")
                    return
                }
            }
            this.selectItem.push(vote)
            this.canshuId.push(row.mmpCode)

            this.moreCanshu.mmpIds = row.mmpCode
            // this.dialogVisible4 = true
            console.log(JSON.stringify(this.selectItem))
        },
        handleSelectcanshu(val) {

        },
        changeStatus(val, index) {
            console.log(this.para)

        },
        changeStatusToo(val, index) {
            console.log(val)
        },
        morePara() {
            this.dialogVisible3 = true
            console.log("111")
            let that = this;
            var param = {
                service: 'mdMaterielInfoService',
                func: 'getMdMaterielParameter',
                state:'1',
                page: '',
                limit: ''
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, param, 'post', function (jsondata) {
                that.gridDatas2 = jsondata.items

            });
        },
        queryAttr() {

        },
        handleInput(tag, index) {
            let words = this.words;
            if (words) {
                this.dynamicTags[index] = words;
            }
            this.dynamicTags = this.unique(this.dynamicTags);
            this.words = '';
            this.num = -1;
        },
        editTag(tag, index) {
            this.num = index;
            this.$nextTick(_ => {
                this.$refs.editInput[0].focus();
            });
            this.words = tag;
        },
        handleClosees(tag) {
            this.dynamicTagses.splice(this.dynamicTagses.indexOf(tag), 1);
        },
        //查询主信息
        querySub() {
            console.log("查询主信息")
            this.user = JSON.parse(window.sessionStorage.getItem("user"))
            let that = this
            var param = {
                service: 'mdModelMaterielService',
                func: 'getMdModelMaterielInfo',
                wmsModelId: that.user.wmsModelId
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {

                that.modelId = jsondata.obj
                that.brandId = jsondata.obj.mbdId
                // that.brandListMosel = jsondata.obj.mbdId
                that.brandListMosel = jsondata.obj.mbdName
                that.modelMatCode = jsondata.obj.modelMatCode
                that.matType1 = jsondata.obj.matType1
                that.matType2 = jsondata.obj.matType2
                that.matType3 = jsondata.obj.matType3
                that.imgIds.push(jsondata.obj.imgIds)
                that.wzId = jsondata.obj.wzId
                that.suiId = jsondata.obj.suiId
                that.suiId = jsondata.obj.suiId
                // that.finalPicId = jsondata.obj.imgIds.split(",")
                if (jsondata.obj.imgIds != ""){
                    // let a = jsondata.obj.imgIds
                    that.selectedImg=jsondata.obj.imgIds.split(',')
                }
                // for (let i =0;i<that.selectedImg.length;i++){
                //     that.selectItem[i] = that.selectItem[i]-0
                // }

                for (let i = 0; i < jsondata.obj.pathList.length; i++) {
                    let imgList = {
                        name: '',
                        url: jsondata.obj.pathList[i],
                    }
                    that.pathList.push(imgList)
                    that.finalPicList.push(jsondata.obj.pathList[i])
                    that.rootPathList.push(jsondata.obj.pathList[i])

                }
                that.createDate = jsondata.obj.createDate
                that.createRen = jsondata.obj.createRen
                that.sellUint = jsondata.obj.sellUnit
            });
        },
        handleAvatar(res, file) {
            console.log("图片上传成功回调")
            for (let i = 0; i < res.obj.length; i++) {
                this.finalPicId.push(res.obj[i].fileId)
            }
        },
        handleClick() {

        },
        querySelect() {
            console.log('获取关联id')
            let that = this
            var param = {
                service: 'mdModelMaterielService',
                func: 'getMdModelMaterielInfoList',
                wmsModelId: that.user.wmsModelId
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                for (let i = 0; i < jsondata.obj.length; i++) {
                    that.canshuId[i] = jsondata.obj[i].mmpId
                    that.ModelcanshuId[i] = jsondata.obj[i].mdMpId
                    var vote = {
                        state: '',//是否多选
                        index: '',//当前行
                        clearable: '',//是否可清空
                        create: '',//是否可创建
                        name: jsondata.obj[i].mmpName,
                        value: '',
                        id: jsondata.obj[i].mmpId,
                    };
                    if (jsondata.obj[i].inputMode == 1) {
                        vote.create = true
                        vote.state = false
                    } else if (jsondata.obj[i].inputMode == 2) {
                        vote.create = false
                        vote.state = true
                    } else if (jsondata.obj[i].inputMode == 3) {
                        vote.create = false
                        vote.state = false
                    }
                    if (jsondata.obj[i].paramOptions.value != '') {
                        vote.value = jsondata.obj[i].paramOptions.value.split(",")
                    }
                    that.selectItem.push(vote)
                    if (jsondata.obj[i].inputMode == 2) {
                        that.para.push(jsondata.obj[i].mmpContent.split(","))
                    } else {
                        that.para.push(jsondata.obj[i].mmpContent)
                    }

                }
            });
        },
        // 数组去重
        unique(arr) {
            let x = new Set(arr);
            return [...x];
        },
        handleClose(tag) {
            this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
        },
        editg() {
            console.log("商品规格")
            this.user = JSON.parse(window.sessionStorage.getItem("user"))
            let that = this;
            this.dialogVisible2 = true
            var param = {
                service: 'mdModelMaterielFormatService',
                func: 'getMdModelMaterielFormatList',
                wmsModelId: this.user.wmsModelId
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                for (let i = 0; i < jsondata.length; i++) {
                    that.dynamicTags[i] = jsondata[i].mmfName
                    // that.dynamicTagsId[i] = jsondata[i].modelMmfId

                    // that.dynamicTags.push(jsondata[i].mmfName)
                }
            });
        },
        getAttr() {
            console.log("查询属性名称")
            this.user = JSON.parse(window.sessionStorage.getItem("user"))
            let that = this;
            this.dialogVisible2 = true
            var param = {
                service: 'mdModelMaterielFormatService',
                func: 'getMdModelAttrs',
                wmsModelId: this.user.wmsModelId
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {

                for (let i = 0; i < jsondata.obj.length; i++) {
                    if (jsondata.obj[i].canSearch !== 1) {
                        that.addModels.retrieval = '0'
                    } else {
                        that.addModels.retrieval = '1'
                    }
                    that.shuxiang = jsondata.obj[i].mmaName
                    that.attrId = jsondata.obj[i].attrId
                    that.dynamicTagses = jsondata.obj[i].attrContent.split(",")
                }
            });
        },
        getPara() {
            this.user = JSON.parse(window.sessionStorage.getItem("user"))
            let that = this;
            this.dialogVisible2 = true
            var param = {
                service: 'mdModelMaterielService',
                func: 'getMdModelMaterielInfoList',
                wmsModelId: this.user.wmsModelId
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                console.log("获取模型库已选参数")
            });
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
        goBack() {
            const a = {
                "url": "/dentistmall/html/productInfo.html",
                "title": "商品库管理",
            }
            if (a.url != null && a.url != "") {
                var title = a.title ? a.title : "";
                parent.window.addMenuItem(a.url, title);
            }
            console.log("111")
            const param = {
                "url": "/dentistmall/html/component/editProduct.html",
                "title": "编辑模型",
            };
            if (param.url != null && param.url != "") {
                var title = param.title ? param.title : "";
                parent.window.closeMenuItem(param.url, title);
            }
        },
        editBack(){
            const a = {
                "url": "/dentistmall/html/productInfo.html",
                "title": "商品库管理",
            }
            if (a.url != null && a.url != "") {
                var title = a.title ? a.title : "";
                parent.window.addMenuItem(a.url, title);
            }
            console.log("111")
            const param = {
                "url": "/dentistmall/html/component/editProduct.html",
                "title": "编辑模型",
            };
            if (param.url != null && param.url != "") {
                var title = param.title ? param.title : "";
                parent.window.closeMenuItem(param.url, title);
            }
        },
        editPro() {
                if (this.sellUint == ''){
                    this.$message.error('请填写销售单位！');
                    return
                }
                if (this.selectedImg.length == 0) {
                    this.$message.error('图片至少上传1张！');
                    return
                }

                let that = this;
                let param = {
                    'service': 'mdModelMaterielService',
                    'func': 'updateMdModelMaterielInfo',
                    wmsModelId: this.user.wmsModelId,
                    matType1: this.matType1,
                    matType2: this.matType2,
                    matType3: this.matType3,
                    modelMatName: this.modelId.modelMatName,
                    aliasName: this.modelId.aliasName,
                    introduction: this.modelId.introduction,
                    modelMatCode: this.modelMatCode,
                    // imgIds:this.imgIds.join(","),
                    imgIds: this.selectedImg.join(","),
                    keyWord: this.modelId.keyWord,
                    remark: this.modelId.remark,
                    state: this.modelId.state,
                    sellUnit:this.sellUint,
                    wzId: this.wzId,
                    suiId: this.suiId,
                    createDate: this.createDate,
                    createRen: this.createRen,
                    mbdId:this.brandId
                }
                let url = utils.getServicePathByObj(param);
                utils.wxRequest(url, null, 'post', function (jsondata) {
                    if (jsondata.code ==1){
                        // setTimeout(function () {
                        //     that.editBack();
                        // },3000);
                        // setTimeout(that.editBack(),5000)
                        // that.$message.sueccess("基本信息保存成功,请等待…")
                        that.saveFormat(that);
                    }else {
                        that.$message.error("保存出错，请联系管理员！")
                    }
                    console.log("提交按钮")
                });
        },
        saveFormat(that) {
            //更新模型库规格
            let data = {
                service: 'mdModelMaterielFormatService',
                func: 'updateModelFormats',
                wmsModelId: that.user.wmsModelId,
                // mmfIds:this.dynamicTagsId.join(","),
                mmfNames: that.dynamicTags.join(","),//商品规格数组
                mdAttrIds: that.attrId,//关联属性id
                canSearch: that.addModels.retrieval,//能否进行检索
                attrContents: that.dynamicTagses.join(",")//属性内容拼接
            }
            let urls = utils.getServicePathByObj(data);
            utils.wxRequest(urls, null, 'post', function (jsondata) {
                that.code2 = jsondata.code
                console.log("提交按钮")
                that.saveParameter(that);

            });
        },
        saveParameter(that) {
            //配置更多参数内容
            for (let i = 0; i < that.para.length; i++) {
                if (that.para[i] instanceof Array) {
                    that.para[i].join(',')
                }
            }
            const data1 = {
                "service": "mdModelMaterielService",
                "func": "updateMdModelMaterielInfoParameter",
                wmsModelId: that.user.wmsModelId,  //模型id
                mmpIds: that.canshuId.join(','),
                // mdMpIds:that.ModelcanshuId.join(','),
                mmpContents: encodeURIComponent(that.para.join('#'))
            }
            let data2 = utils.getServicePathByObj(data1);
            utils.wxRequest(data2, null, 'post', function (jsonData) {
                if (jsonData.code == 1) {
                    // that.$message.success("保存成功")
                    console.log("提交成功")
                    that.editBack();
                }else if (jsonData.code == 0){
                    that.$message.error("保存失败，请联系管理员！")
                }else if (jsonData.code == 3){
                    that.$message.error(jsonData.meg)
                }
            })
        }
    }
})