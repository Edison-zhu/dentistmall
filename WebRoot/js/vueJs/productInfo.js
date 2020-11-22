const app = new Vue({

    el: '#app',
    // components: {
    //     'my-component': httpVueLoader('../html/component/drawer.vue')
    // },
    data() {
        return {
            key: false,
            setting: {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "pId",
                        isParent: "isParent"
                    }
                },
                view: {
                    showLine: false,
                    dblClickExpand: false,
                    addDiyDom: this.addDiyDom,
                    nameIsHTML: true,
                    selectedMulti: false
                },
                callback: {
                    onClick: this.onClick,                //节点点击事件
                    onCollapse: this.onCollapse,           //点击图标按钮节点 折叠后 异步加载子数据
                    onExpand: this.zTreeOnAsyncSuccess    //点击图标按钮节点 展开后 异步加载子数据
                },
                async: {
                    enable: true, // 开启异步加载
                    url: "/dentistmall/doService.htm?1=1&service=mdMaterielTypeService&func=getTreeListByMdMmtId&jsonSysDateId=VXVOITFHQD", //对应的后台请求路径
                    autoParam: ["id"] // 异步加载时需要自动提交父节点属性的参数
                },
            },
            zNodes: [{id: 0, pId: "", name: "全部", isParent: true}],
            value: true,
            switchValue: '',
            state2: '',
            restaurants: [],
            restaurantses: [],
            showState: '',//状态下拉框
            currentPage4: '',
            currentPage: 1,
            currentPages:1,
            pageSize: 10,
            pageSizes:10,
            drawer: false,
            direction: 'rtl',
            ruleForms: {},
            //右侧弹窗相关数据
            drawer2: false,
            drawers: false,
            drawer3: false,
            innerDrawer: false,
            dialogImageUrl: '',
            dialogVisible: false,
            autoUpload: true,//自动上传
            imageUrl: null,//模型数据，用于上传图片完成后图片预览
            activeName: 'first',//添加/编辑窗口Tab标签名称
            dialogFormVisible: false,
            dialogVisible3: false,
            dialogVisible4: false,
            dialogVisible5: false,
            dialogVisible6: false,
 			dialogFormVisible6:false,
            dialogFormVisible7:false,
            p_show: false,
            p_showToo: false,
            dialogVisible2: false,
            itemMenus: [],
            itemMenusToo: [],
            itemMenusThree: [],
            showData: "",
            showDataToo: "",
            showDataThree: '',
            current: '-1',
            current1: '-1',
            current2: '-1',
            kindData: {
                firstId: '',
                secondId: '',
                thirdId: '',
            },
            editInfo: {
                connonName: '',
                subTitle: '',
                introduction: '',
                productName: '',
                productNum: '',
                remark: '',
            },
            addModels: {
                attributes: '',
                specifications: '',
                retrieval: '',
                chooseBrand: '',
                sale: '',
                specificationsToo:''
            },
            dialogFrom: {
                wmsModelId: '',
                attribute: '',
                Specifications: '',
            },
            wmsModelId: '',
            activeButton: '',
            edit: false,
            modelId: [],
            modelId3: [],
            isEditStreet: false, // 编辑街道
            flag: true,
            communityData: [],
            recentKind: '',//最近使用的商品分类
            actionsUrl: '/dentistmall/doService.htm?1=1&service=fileService&func=addFile&fileFolderId=1&jsonSysDateId=VXVOITFHQD',
            queryFrom: {
                paraName: "",
                paraSearch: [],
                paraRelated: '',
            },

            guige: [],
            gridData: [],
            gridDatas: [],
            gridDatas2: [],
            gridDatas3: [],
            mmfName: '',
            multiple: '',

            formDate: "",
            fileList: [
            ],
            swichOff: '1',
            fileId: [],
            total: 0,
            totals:0,
            options: [{
                value: '',
                label: '所有'
            }, {
                value: '1',
                label: '显示'
            }, {
                value: '2',
                label: '不显示'
            },],
            values: '',
            tableData: [],
            multipleSelection: [],
            multipleSelection2: [],
            multipleSelections:[],
            checkBox: {
                registration: '',
                Validity: '',
                ItemNo: '',
                ProductStandards: '',
                Material: '',
                Productpackaging: '',
                manufacturer: '',
                Origin: '',
                Mainingredients: '',
                ProductUsage: '',
                Shelflife: '',
                weight: '',
                instructions: ''
            },

            moreCanshu: {
                mmpIds: '',
                mmpContents: '',
                addPara: '',
            },
            loading: false,
            tableLoading:false,

            selectItem: [],
            bangding: '',
            bangding1: [''],
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
            shuxing2s: [
                {}
            ],
            shuxing3: [
                {}
            ],
            searchs: '',
            dynamicTags: [],
            dynamicTagses: [],
            dynamicTagsestoo:[],
            dynamicTagsesthree:[],
            inputVisible: false,
            inputValue: '',
            num: -1,
            nums:-1,
            numses:-1,
            words: '',
            wordsToo:'',
            wordsThree:'',

            attrId: '',

            canshuId: [],
            multiples: true,
            multiplesToo:false,
            submitId:'',
            subparaId:'',
            subpattrId:[],
            Kindvalue:[{
                value:'',
                label:''
            }],
            Kindvalues:[],
            matType1:'',
            matType2:'',
            matType3:'',
            logWmsModelId: 0,
            KindKeys: '',
            imgs: [], // 图片列表
            currentImgPage: 1, // 当前页码
            imgPageSize: 10, // 显示条数
            sysFolderList: [], // 文件夹列表
            FolderList: '', // 选择后的文件夹
            sysFfId: 0, // 文件夹id
            selectedImg: [], // 选择的图片信息
            rootPathList: [], // 选择后的路径信息
            imgLimit: 6, // 图片限制，动态改变
            fixImgLimit: 6, // 图片固定限制，根据需求改变
            checkboxGroup: [], // 暂时不用
            imgCheck: [], // 暂时不用
            brandList:[{}],
            uintList:[{}],
            brandListMosel:'',
            brandloading:false,
            AttrList:[],
            editAttrList:[],
            AttrListToo:[],
            sellUint:'',
            allow:true,
            filterable:true,
            totalImage:'',
            imageCheck:[],
            maxCheck:6,
            mustEdit:[],
            lastStepBtn: false,
            sysFolderListlabel:''

        }
    },
    computed:{
        eleDateNew() {
            return JSON.parse(JSON.stringify(this.imgs))
        },
    },
    watch:{
        total(val, olVal){
            if (val !== olVal){
                this.currentPage = 1
                this.selectQuery()
            }
        },
        imgLimit(val, olVal){
            console.log('我变化了', val, olVal)
        },
    },
    created() {
    },
    mounted() {
        this.loadAll();
        this.freshArea()
        this.selectQuery()
        // this.querySearch()
        this.restaurantses = this.loadKind()
    },
    methods: {
        // 打开图片选择框
        openImgSelect() {
            this.maxCheck = 6
            this.dialogVisible6 = true;

            this.initImgList(1);
            // this.selectFolder(1)
            this.initFolerList();
            this.FolderList = 1
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
                for (let idx in that.imgs)
                    that.imgs[idx].checked = false;
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
        removeByVal(arrylist , val) {
            for(var i = 0; i < arrylist .length; i++) {
                if(arrylist [i] == val) {
                    arrylist.splice(i, 1);
                    break;
                }
            }
        },
        handleCloseImage(tag,index){
            this.fileList.splice(this.fileList.indexOf(tag),1)
            this.rootPathList.splice(this.rootPathList.indexOf(tag), 1);
            this.selectedImg.splice(index,1)

            console.log(this.selectedImg)
            console.log(this.rootPathList)
        },
        // 选择线上图片值改变的时候
        imgChange(value,index,item,imageCheck) {

            if (this.selectedImg.length != this.rootPathList.length+1){
                this.rootPathList.splice(this.rootPathList.indexOf(item.rootPath),1)
                // this.handleCloseImage(item.rootPath,index)
                // var index = this.selectedImg.indexOf(item.fileId);
                // this.selectedImg.splice(index,1)
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


            console.log(this.rootPathList)
            console.log(this.selectedImg)
            // if (this.rootPathList.length > this.fixImgLimit) {
            //     this.rootPathList.splice(this.fixImgLimit);
            // }
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
        //复选框按钮
        toggleSelection(rows) {
            console.log("111")
            if (rows) {
                rows.forEach(function (row) {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange(val) {
            console.log("222")
            this.multipleSelection = val;
        },
        handleSelectionChanges(val){
            this.multipleSelections = val;
            console.log("属性多选框")
        },
        handleSelectcanshu(val) {

        },
        chakna() {
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'getmdMaterielAttributeInfo',
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                that.gridDatas3 = jsondata.items
            });
            this.dialogVisible5 = true
        },
        //查询表格数据
        selectQuery() {
            this.tableLoading = true
            console.log("回车查询")
            let that = this
            var param = {
                service: 'mdModelMaterielService',
                func: 'getMdModelMaterielPagerModel',
                searchName:that.queryFrom.paraName,
                mbdIds:that.queryFrom.paraSearch,
                state:that.queryFrom.paraRelated,
                matType1:that.matType1,
                matType2:that.matType2,
                matType3:that.matType3,
                page: this.currentPage,
                limit: this.pageSize,
            }
            if (that.queryFrom.paraSearch instanceof Array){
                 param.mbdIds = this.queryFrom.paraSearch.join(",")
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                that.tableData = jsondata.items
                that.total = jsondata.total
                that.tableLoading = false
            });
        },
        handleInputConfirm() {
            console.log("111")
            let inputValue = this.inputValue;
            for (let i = 0; i < this.dynamicTags.length; i++) {
                if (inputValue == this.dynamicTags[i]) {
                    this.$message.error("插入的可选值重复")
                    return
                }
            }
            if (inputValue) {
                this.dynamicTags.push(inputValue);
            }
            this.inputVisible = false;
            this.inputValue = '';
        },
        handleClose(tag) {
            this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
        },
        handleClosees(tag) {
            this.dynamicTagses.splice(this.dynamicTagses.indexOf(tag), 1);
            if (this.dynamicTagses.length == 0){
                this.addModels.retrieval = ''
            }
        },
        handleCloseesToo(tag){
            this.dynamicTagsestoo.splice(this.dynamicTagsestoo.indexOf(tag), 1);
        },
        handleCloseThree(tag){
            this.dynamicTagsesthree.splice(this.dynamicTagsesthree.indexOf(tag), 1);
        },
        showInput() {
            console.log("222")
            this.inputVisible = true;
            this.$nextTick(_ => {
                this.$refs.saveTagInput.$refs.input.focus();
            });
        },
        handleInput(tag, index) {
            this.dynamicTags.splice(index,1);
            let words = this.words;
            for (let i =0;i<this.dynamicTags.length;i++){
                if (this.dynamicTags[i] == words){
                    this.$message.error('规格名重复！');
                    this.dynamicTags.splice(index,0,tag)
                    return
                }
            }
            this.dynamicTags.splice(index,0,tag)
            if (words) {
                this.dynamicTags[index] = words;
            }
            // this.dynamicTags = this.unique(this.dynamicTags);
            this.words = '';
            this.num = -1;
        },
        handleInputs(tag, index) {
             this.dynamicTagsesthree.splice(index,1);
            console.log("111")
            let words = this.wordsToo;
            for (let i =0;i<this.dynamicTagsesthree.length;i++){
                    if (this.dynamicTagsesthree[i] == words){
                        this.$message.error('规格名重复！');
                        this.dynamicTagsesthree.splice(index,0,tag)
                        return
                    }
            }

            this.dynamicTagsesthree.splice(index,0,tag)
            if (words) {
                this.dynamicTagsesthree[index] = words;
            }
            // this.dynamicTagsesthree = this.unique(this.dynamicTagsesthree);
            this.wordsToo = '';
            this.nums = -1;
        },
        handleInputses(tag, index) {
            let words = this.wordsThree;
            if (words) {
                this.dynamicTagsestoo[index] = words;
            }
            this.dynamicTagsestoo = this.unique(this.dynamicTagsestoo);
            this.wordsThree = '';
            this.numses = -1;
        },
        // 数组去重
        unique(arr) {
            let x = new Set(arr);
            return [...x];
        },
        editTag(tag, index) {
            this.num = index;
            this.$nextTick(_ => {
                this.$refs.editInput[0].focus();
            });
            this.words = tag;
        },
        editTags(tag, index) {
            this.nums = index;
            this.$nextTick(_ => {
                this.$refs.editInputs[0].focus();
            });
            this.wordsToo = tag;
        },
        editTagsToo(tag, index) {
            this.numses = index;
            this.$nextTick(_ => {
                this.$refs.editInputses[0].focus();
            });
            this.wordsThree = tag;
        },
        uploadFile(file) {
            this.formDate.append('file', file.file);
        },
        subPicForm() {
            this.formDate = new FormData()
            this.$refs.upload.submit();
            this.formDate.append('WS_CODE', "12133");

        },
        deleteAll() {
            let that = this
            let wmsModelIds = '';
            this.multipleSelection.forEach(multipleSelection => {
                wmsModelIds += multipleSelection.wmsModelId + ',';
            })
            if (wmsModelIds == '') {
                that.$message.warning('请选择删除的数据！');
                return;
            }
            wmsModelIds = wmsModelIds.substring(0, wmsModelIds.lastIndexOf(","));

            var param = {
                service: 'mdModelMaterielService',
                func: 'deleteMdModelMaterielBatch',
                wmsModelIds: wmsModelIds,
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1) {
                    that.$message.success('删除成功！');
                }else if (jsondata.code == 0){
                    that.$message.error("操作失败请联系管理员！")
                }
                that.selectQuery();
            });
        },
        // 导出excel
        export_all() {
            let that = this
            let wmsModelIds = '';
            this.multipleSelection.forEach(multipleSelection => {
                wmsModelIds += multipleSelection.wmsModelId + ',';
            })
            wmsModelIds = wmsModelIds.substring(0, wmsModelIds.lastIndexOf(","));
            if (wmsModelIds == ''){
                that.$message.success('请选择数据！');
                return
            }
            var param = {
                service: 'enterWarehouseExportService',
                func: 'exportmMdMaterielModel',
                wmsModelIds: wmsModelIds,
            }
            var newTab = window.open('about:blank');
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata) {

                    that.$message.success('导出成功！');
                    newTab.location.href = jsondata.obj.path;
                }
                that.selectQuery();
            });
        },
        addwmsModel() {
            let that = this
            var param = {
                service: 'mdModelMaterielFormatService',
                func: 'updateModelFormats',
                wmsModelId: this.submitId,
                // mmfIds:this.subpattrId.join(","),//规格id
                 mdAttrIds:this.attrId,//属性
                attrContents:this.dynamicTagsestoo.join(","),
                mmfNames: this.dynamicTagsesthree.join(","),
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1) {
                    that.$message.success('保存成功！');
                    that.addModels.specifications = ''
                    that.dialogVisible2 = false
                    that.shuxiangs = []
                }else {
                    that.$message.error("规格参数必须填写！")
                }
            });
        },
        add(i) {
            console.log(i)
            if (i == 1) {
                if (this.addModels.attributes != ''){
                    this.shuxiang.push();
                }
            } else if (i == 2) {
                if (this.addModels.specifications == '') {
                    this.$message.error('规格名不能为空！');
                    return;
                }
                if (this.dynamicTags.indexOf(this.addModels.specifications) >= 0) {
                    this.$message.error('规格名重复！');
                    return;
                }
                this.dynamicTags.push(this.addModels.specifications)
            } else if (i == 3) {
                if (this.addModels.specificationsToo == '') {
                    this.$message.error('规格名不能为空！');
                    return;
                }
                if (this.dynamicTagsesthree.indexOf(this.addModels.specificationsToo) >= 0) {
                    this.$message.error('规格名重复！');
                    return;
                }
                this.dynamicTagsesthree.push(this.addModels.specificationsToo)
                this.modelId.push(this.dialogFrom.Specifications)
                this.modelId3.push(this.dialogFrom.Specifications)
            }

        },
        clean(index, i) {
            console.log("111")
            if (i == 1) {
                this.shuxiang.splice(index, 1);
            } else if (i == 2) {
                this.guige.splice(index, 1);
            } else if (i == 3) {
                this.modelId.splice(index, 1)
            }
        },
        editClean() {
            this.flag = false
        },
        stateFormat(row, column) {
            if (row.state == 1) {
                return '显示'
            } else {
                return '不显示'
            }
        },
        openLog(wmsModelId) {
            this.logWmsModelId = wmsModelId
            this.currentPages = 1
            this.logInfo(1)
        },
        logInfo(wmsModelId) {
            let that = this;
            this.dialogVisible = true
            var param = {
                service: 'mdModelMaterielService',
                func: 'getMdModelOperateLodPagerModel',
                page: this.currentPages,
                limit: this.pageSizes,
                wmsModelId: this.logWmsModelId,
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                that.gridData = jsondata.items
                that.totals = jsondata.total
            });
        },
        handleSelect(item) {
            console.log(item);
        },
        querySearch(queryString, cb) {
            var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
            // 调用 callback 返回建议列表的数据
            cb(results);
        },
        createFilter(queryString) {
            return (restaurants) => {
                return (restaurants.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
            };
        },
        loadAll() {
            let that = this
            var param = {service: 'mdMaterielInfoService', func: 'getBrandList', brand: this.state2}
            let url = utils.getServicePathByObj(param);
            var data = []
            utils.wxRequest(url, null, 'post', function (jsondata) {
                for (let i = 0; i < jsondata.rows.length; i++) {
                    that.restaurants[i] = jsondata.rows[i]
                }
            });
            return this.restaurants
        },
        querySearchAsync(queryString, cb) {
            var restaurantses = this.restaurantses;
            var resultses = queryString ? restaurantses.filter(this.createFilter(queryString)) : restaurantses;
            // 调用 callback 返回建议列表的数据
            cb(resultses);
        },
        createFilters(queryString) {
            return (restaurantses) => {
                return (restaurantses.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
            };
        },

        //获取最近使用的分类
        loadKind() {
            let that = this;
            console.log("888")
            var param = {service: 'mdModelMaterielService', func: 'getMdMaterielTypeLink', searchName: ''}
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                for (let i = 0; i < jsondata.obj.length; i++) {
                    let a = jsondata.obj[i].name//.split(">")
                    let b = jsondata.obj[i].mmtId//.split(",")
                    var vote = {
                        value:b,
                        label:a,
                    }
                    // vote.label.push(a)
                    that.Kindvalue.push(vote)
                    // that.Kindvalue.shift()
                }
            });
            // return this.restaurantses
        },
        selectValueChange(value) {
            // 选择最近分类后，自动处理一级二级三级，都是在请求回调中处理数据
            let label = value.label;
            let keys = value.value;
            let names = label.split('>');
            let ids = keys.split(',');
            if (ids.length >= 1) {
                this.selectMenu({mmtName: names[0], mmtId: ids[0]}, -1, ids, names)
            }
        },
        cancels(){
            console.log("111")
            this.dialogVisible2 = false
            this.dynamicTagsestoo = []
            this.dynamicTagsesthree = []
        },
        //规格修改
        editg(row) {
            this.subparaId = ''
            this.dynamicTagsestoo = []
            this.dynamicTagsesthree = []
            this.subpattrId = []
            this.submitId = row.wmsModelId
            let that = this;
            this.dialogVisible2 = true
            var param = {
                service: 'mdModelMaterielFormatService',
                func: 'getMdModelAttrs',
                wmsModelId: row.wmsModelId
            }
            //获取关联属性id
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                for (let i = 0;i<jsondata.obj.length;i++){
                    that.shuxiangs = jsondata.obj[i].mmaName
                    that.attrId = jsondata.obj[i].attrId
                    that.dynamicTagsestoo = jsondata.obj[i].attrContent.split(",")
                }
            });
            //获取商品规格信息
            var dataId = {
                service: 'mdModelMaterielFormatService',
                func: 'getMdModelMaterielFormatList',
                wmsModelId: row.wmsModelId
            }
            let data = utils.getServicePathByObj(dataId);
            utils.wxRequest(data, null, 'post', function (jsondata) {
                for (let i = 0;i<jsondata.length;i++){
                    if (jsondata[i].attrId !== ''){
                        that.subpattrId.push(jsondata[i].attrId)
                    }
                    if (jsondata[i].mmfName !== ''){
                        that.dynamicTagsesthree.push(jsondata[i].mmfName)
                    }
                }

            });
        },
        morePara() {

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
            this.dialogVisible3 = true
        },
        handleEdit() {

        },
        handleCanshu(row,index) {
            this.$refs.multipleTables.toggleRowSelection(row);
            var vote = {
                name: '',
                value: [],
                id: '',
                state: '',//是否多选
                index:'',//当前行
                clearable:'',//是否可清空
                create:'',//是否可创建
                isRequired:''//是否必填
            };
            if (row.inputMode == 1) {
                vote.create = true
                vote.state = false
            }else if(row.inputMode == 2) {
                vote.create = false
                vote.state = true
            }else if (row.inputMode == 3){
                vote.create = false
                vote.state = false
            }

            vote.id = row.mmpCode;
            vote.name = row.mmpName;
            vote.isRequired = row.isRequired
            if (row.optionaValue !== ''){
                vote.value = row.optionaValue.split(",");
            }
            vote.index = index
            for (let i=0;i<this.selectItem.length;i++){
                if (index == this.selectItem[i].index){
                    this.$message.error("该参数已配置，请勿重复配置！")
                    return
                }
            }
            this.mustEdit.push(row.isRequired)
            this.selectItem.push(vote)
            console.log(this.selectItem)
            this.canshuId.push(row.mmpCode)
            this.moreCanshu.mmpIds = row.mmpCode
        },
        changeStatus(val, index) {
            // console.log(val,index)
             console.log(this.bangding1)
            // console.log(val)
        },
        changeStatusToo(val, index) {
            console.log(val)
        },
        subAttr(i){
            if (i == 1){
                if (this.AttrList.length == 0 || this.AttrList == ''){
                    this.$message.error("请配置属性值！")
                    return
                }
                this.dynamicTagses = []
                console.log("多选值列表"+this.AttrList)
                if (Array.isArray(this.AttrList) == true){
                    this.dynamicTagses = this.AttrList
                }else {
                    this.dynamicTagses.push(this.AttrList)
                }
                this.dialogFormVisible6 = false
                this.AttrList = ''
            }else {
                this.dynamicTagsestoo = []
                if (Array.isArray(this.editAttrList) == true){
                    if (this.editAttrList.length == 0){
                        this.$message.error("请配置属性值")
                        return
                    }
                    this.dynamicTagsestoo = this.editAttrList
                }else {
                    if (this.editAttrList == ''){
                        this.$message.error("请配置属性值")
                        return
                    }
                    this.dynamicTagsestoo.push(this.editAttrList)
                }
                this.dialogFormVisible7 = false
                this.editAttrList = ''
            }

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
                this.dialogFormVisible7 = false
                this.dialogFormVisible6 = false
                this.AttrList = ''
            }

        },
        liebiaoCalcel(){
            console.log("111")
            if (Array.isArray(this.editAttrList) == true){
                if (this.editAttrList.length == 0 ){
                    this.$message.error("请配置属性值")
                    return
                }
            }else if (this.editAttrList == ''){
                this.$message.error("请配置属性值")
                return

            }else {
                this.dialogFormVisible7 = false
                this.editAttrList = ''
            }
        },
        cancels(){
            console.log("取消按钮")
            this.dialogVisible2 = false
            this.shuxiangs = ''
        },
        changeAttr(val,i) {
            if (val == ''){
                this.dynamicTagses.length = 0
                this.attrId = ''
                this.dynamicTagsestoo.length = 0
            }else {
                console.log("打开弹出框")
                if (i == 1){
                    this.dialogFormVisible6 = true
                }else {
                    this.dialogFormVisible7 = true
                }
                //关联属性id拼接
                this.attrId=val
                this.AttrListToo = ''
                console.log("关联属性id"+this.attrId)
                let that = this;
                let param = {
                    service: 'mdMaterielInfoService',
                    func: 'findAttributeOptionalValue',
                    mmaId: val,
                }
                // that.dialogFormVisible7 = true
                let url = utils.getServicePathByObj(param);

                utils.wxRequest(url, param, 'post', function (jsondata) {
                    if (jsondata.code == 1) {
                        if (jsondata.obj.optionalValues != ''){
                            var strs = jsondata.obj.optionalValues
                            that.AttrListToo = strs.split(",")
                        }else {
                            that.dynamicTagses = []
                        }
                        if (jsondata.obj.isOptional == '1' ){
                            that.multiplesToo = false
                            that.filterable = false
                            that.allow = false
                            console.log("唯一")
                        }
                        if (jsondata.obj.isOptional == '2' ){
                            that.multiplesToo = false
                            that.filterable = false
                            that.allow = false
                            console.log("单选")
                        }
                        if (jsondata.obj.isOptional == '3'){
                            that.multiplesToo = true
                            that.filterable = false
                            that.allow = false
                            console.log("多选")
                        }
                        if (jsondata.obj.inputModel == '1'){
                            that.multiplesToo = false
                            that.filterable = true
                            that.allow = true
                            console.log("手动模式")
                        }
                        if (jsondata.obj.isOptional == '3' && jsondata.obj.newAdd == '1'){
                            that.multiplesToo = true
                            that.filterable = true
                            that.allow = true
                            console.log("多选并且手动创建")
                        }
                        if (jsondata.obj.isOptional == '2' && jsondata.obj.newAdd == '1'){
                            that.multiplesToo = false
                            that.filterable = true
                            that.allow = true
                            console.log("单选并且手动创建")
                        }

                    }
                })
            }

        },
        handleDelete(index, row) {
            let that = this;
            let param = {
                service: 'mdModelMaterielFormatService',
                func: 'deleteMdModelFormat',
                wmsModelId: row.modelMmfId,
                mmfId: row.modelMmfId
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, param, 'post', function (jsondata) {
                if (jsondata.code == 1) {
                    that.$message.success("删除成功")
                    that.editg();
                }
            })
        },
        editRow(row, i) {
            if (i == 1) {
                window.sessionStorage.setItem("user", JSON.stringify(row))
                const param = {
                    "url": "/dentistmall/html/component/productDetails.html",
                    "title": "查看详情",
                };
                if (param.url != null && param.url != "") {
                    var title = param.title ? param.title : "";
                    parent.window.addMenuItem(param.url, title);
                }
            } else {
                window.sessionStorage.setItem("user", JSON.stringify(row))
                const param = {
                    "url": "/dentistmall/html/component/editProduct.html",
                    "title": "编辑模型",
                };
                if (param.url != null && param.url != "") {
                    var title = param.title ? param.title : "";
                    parent.window.addMenuItem(param.url, title);
                }
            }

        },
        handleButtonClick(index) {
            console.log("444")
            this.activeButton = index
            console.log(index)
        },
        handleEditStreet() {
            console.log("222")
            this.isEditStreet = true
        },
        handleSaveStreet() {
            console.log("333")
            this.isEditStreet = false
        },
        switchChange(row) {
            console.log(JSON.stringify(row.wmsModelId))
            let that = this;
            var param = {
                service: 'mdModelMaterielService',
                func: 'updateMdModelMaterielState',
                wmsModelId: row.wmsModelId
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1) {
                    that.$message.success('模型状态更新成功');
                }
            });
        },
        beforeUpload(file){
            if (this.rootPathList.length >=6){
                this.$message.error("商品图片只能上传六张！")
                return false
            }
        },
        handleAvatarSuccess(res, file) {
                for (let i = 0; i <= res.obj.length; i++) {
                    this.selectedImg.push(res.obj[i].fileId)
                    // this.fileId.push(res.obj[i].fileId)
                    this.rootPathList.push(res.obj[i].rootPath);
                }
            console.log(this.fileId)
        },
        //获取菜单
        async freshArea() {
            //数据渲染到菜单
            $.fn.zTree.init($("#tree"), this.setting, this.zNodes);
            var zTree = $.fn.zTree.getZTreeObj("tree");
            var node = zTree.getNodesByParam("id", "0");
            zTree.expandNode(node[0], true, false, false);
        },
        //点击节点
        onClick(event, treeId, treeNode) {
              // alert(treeNode.id + ", " + treeNode.name);
            console.log(JSON.stringify(treeNode))
            if (treeNode.level == 1){
                this.matType1=treeNode.id
            }
            if (treeNode.level == 2){
                this.matType2=treeNode.id
            }
            if (treeNode.level == 3){
                this.matType3=treeNode.id
            }
            if (treeNode.level == 0){
                this.matType1 = ''
                this.matType2 = ''
                this.matType3 = ''
            }
            this.selectQuery()
        },
        zTreeOnAsyncSuccess(event, treeId, treeNode) {
            this.addNodes(treeNode);
        },
        //点击展开图标 --加载子菜单
        async addNodes(treeNode) {

            const param = {
                "service": "mdMaterielTypeService",
                "func": "getPagerModelObject",
                "mdMmtId": treeNode.id,
            };
            // console.log(param)
            const treeObj = $.fn.zTree.getZTreeObj("tree");
            const parentZNode = treeObj.getNodeByParam("id", treeNode.id, null);//获取指定父节点
            const childNodes = treeObj.transformToArray(treeNode);//获取子节点集合
            //点击事件后将子节点清空后在进行拼接
            treeNode.children = [];
            //因为子节点还包括组织，所以这里需要筛选一下
            if (treeNode.isParent) {
                let url = utils.getServicePathByObj(param);
                await utils.wxRequest(url, null, 'post', function (jsondata) {
                    const result = jsondata.items
                    this.zNodes = result
                    //数据渲染到菜单
                    const childrenData = result
                    if (childrenData) {
                        const childrenData = result
                        // treeObj.refresh();
                        // treeObj.addNodes(parentZNode, childrenData, false);    //添加节点
                        //this.key = true
                    }
                })
            }
        },
        addModel() {
            console.log("111")
            this.drawers = true;
            this.selectMenus()
            this.queryMenus()
            this.selectMenuToos()
            // this.restaurantses = this.loadKind()
        },
        queryMenus() {

            let that = this
            const param = {
                "service": "mdMaterielTypeService",
                "func": "getPagerModelObject",
                "mdMmtId": "0"
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                const result = jsondata.items
                that.itemMenus = result

            })
        },
        selectMenuToos() {
            this.p_showToo = true
            let that = this
            const param = {
                "service": "mdMaterielTypeService",
                "func": "getPagerModelObject",
                "mdMmtId": '2225'
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                const result = jsondata.items
                that.itemMenusThree = result
            })
        },
        //查询二级菜单方法
        selectMenus(item, index) {
            let that = this
            const param = {
                "service": "mdMaterielTypeService",
                "func": "getPagerModelObject",
                "mdMmtId": '2200'
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                const result = jsondata.items
                that.itemMenusToo = result
            })
        },
        //查询二级菜单方法
        selectMenu(item, index, ids, names) {
            this.showDataToo = ''
            this.showDataThree = ''
            this.current1 = '-1'
            this.current2 = '-1'
            console.log("第一级id" + this.kindData.firstId)
            this.current = index;
            this.p_show = true
            this.p_showToo = false
            this.showData = item.mmtName
            let mdMmtId = item.mmtId
            this.kindData.firstId = item.mmtId;
            let that = this
            const param = {
                "service": "mdMaterielTypeService",
                "func": "getPagerModelObject",
                "mdMmtId": mdMmtId
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                const result = jsondata.items
                that.itemMenusToo = result
                // 最近使用自动处理
                if (ids != undefined) {
                    that.kindData.firstId = ids[0];
                    let index = -1;
                    for (let idx in that.itemMenus) {
                        if (that.itemMenus[idx].mmtName == names[0]) {
                            index = idx;
                            break;
                        }
                    }
                    that.current = index
                    // 存在第二级
                    if (ids.length >= 2) {
                        that.selectMenuToo({mmtName: names[1], mmtId: ids[1]}, index, ids, names)
                    }
                }
            })
            this.kindData.firstId = item.mmtId;
        },
        //查询三级菜单方法
        selectMenuToo(item, index, ids, names) {
            this.current2 = '-1'
            this.showDataThree = ''
            console.log("点击二级菜单方法")
            this.current1 = index;
            this.p_showToo = true
            this.showDataToo = item.mmtName
            let mdMmtId = item.mmtId
            this.kindData.secondId = item.mmtId;
            // 如果一级分类没有选择，自动选择一级分类
            // if (this.kindData.firstId == '') {
            if (ids == undefined) {
                for (let idx in this.itemMenusThree) {
                    if (this.itemMenus[idx].mmtId == item.mdMmtId) {
                        this.current = idx;
                        this.kindData.secondId = this.itemMenus[idx].mmtId;
                        // this.showDataThree = this.itemMenus[idx].mmtName;
                        this.showData = this.itemMenus[idx].mmtName
                        break;
                    }
                }
            }else {

            }
            let that = this
            const param = {
                "service": "mdMaterielTypeService",
                "func": "getPagerModelObject",
                "mdMmtId": mdMmtId
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                const result = jsondata.items
                that.itemMenusThree = result
                // 自动选择最近分类
                if (ids != undefined) {
                    // 自动选择二级分类
                    that.kindData.secondId = ids[1];
                    let index = -1;
                    for (let idx in that.itemMenusToo) {
                        if (that.itemMenusToo[idx].mmtName == names[1]) {
                            index = idx;
                            break;
                        }
                    }
                    that.current1 = index
                    // 存在第三级
                    if (ids.length >= 3) {
                        that.selectMenuThree({mmtName: names[2], mmtId: ids[2]}, index, ids, names)
                    }
                }
            })
            this.kindData.secondId = item.mmtId
        },
        //最后一级菜单
        selectMenuThree(item, index, ids, names) {
            this.current2 = index;
            this.showDataThree = item.mmtName
            this.kindData.thirdId = item.mmtId;
            let secondId = -1;

            if (ids == undefined) {
                // 如果二级分类没有选择，自动选择
                // if (this.kindData.secondId == '') {
                for (let idx in this.itemMenusThree) {
                    if (this.itemMenusToo[idx].mmtId == item.mdMmtId) {
                        this.current1 = idx;
                        this.kindData.secondId = this.itemMenusToo[idx].mmtId;
                        secondId = this.itemMenusToo[idx].mdMmtId;
                        this.showDataToo = this.itemMenusToo[idx].mmtName;
                        break;
                    }
                }
                // }
                // 如果一级分类没有选择，自动选择
                // if (this.kindData.firstId == '') {
                for (let idx in this.itemMenusThree) {
                    if (this.itemMenus[idx].mmtId == secondId) {
                        this.current = idx;
                        this.kindData.firstId = this.itemMenus[idx].mmtId;
                        this.showData = this.itemMenus[idx].mmtName;
                        break;
                    }
                }
            }
            // }
            // this.kindData.thirdId = ids[2];
            // 选择最近分类后，如果存在第三级，则自动选择
            if (ids != undefined) {
                if (ids.length >= 3) {
                    let index = -1;
                    for (let idx in this.itemMenusThree) {
                        if (this.itemMenusThree[idx].mmtName == names[2]) {
                            index = idx;
                            break;
                        }
                    }
                    this.current2 = index
                }
            }
            console.log(item)
        },

        closeDrawer() {
            this.drawers = false
        },
        //图片上传方法
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },

        nextStep(i) {
            if (i == 1) {
                this.drawers = false
            } else if (i == 2) {
                let that = this
                this.editInfo.productNum = ''
                if (this.showData == "" || this.showDataToo == '' || this.showDataThree == '') {
                    this.$message.error("您当前还未选择完整分类，请将分类选择完整！")
                    return
                }
                //获取商品编号
                const params = {
                    "service": "mdModelMaterielService",
                    "func": "getNewCode",
                    "firstId": this.kindData.firstId,
                    "secondId": this.kindData.secondId,
                    "thirdId": this.kindData.thirdId
                }
                let urls = utils.getServicePathByObj(params);
                utils.wxRequest(urls, null, 'post', function (jsondata) {
                    console.log("111")
                    if (jsondata.code == 1) {
                        that.editInfo.productNum = jsondata.obj
                    }
                })
                this.drawers = false
                this.drawer2 = true
                //提交选择的商品分类
                const param = {
                    "service": "mdModelMaterielService",
                    "func": "saveMdMaterielType",
                    "firstId": this.kindData.firstId,
                    "secondId": this.kindData.secondId,
                    "thirdId": this.kindData.thirdId
                }
                let url = utils.getServicePathByObj(param);
                utils.wxRequest(url, null, 'post', function (jsondata) {
                    if (jsondata) {
                        that.drawers = false
                        that.drawer2 = true
                    }
                })
            } else if (i == 3) {
                this.drawer2 = false
                this.drawers = true
            } else if (i == 4) {
                this.fileId = this.fileId.concat(this.selectedImg);
                if (this.editInfo.connonName == '') {
                    this.$message.error("请填写商品通用名称！")
                    return
                } else if (this.editInfo.subTitle == '') {
                    this.$message.error("请填写商品副标题！")
                    return
                } else if (this.editInfo.introduction == '') {
                    this.$message.error("请填写商品介绍！")
                    return
                } else if (this.editInfo.productName == '') {
                    this.$message.error("请填写商品关键词！")
                    return
                } else if (this.editInfo.remark == '') {
                    this.$message.error("请填写商品备注！")
                    return
                } else if (this.fileId.length == 0) {
                    this.$message.error("请上传商品图片")
                    return
                }

                let that = this
                const param = {
                    "service": "mdModelMaterielService",
                    "func": "saveMdModelMaterielInfo",
                    "matType1": this.kindData.firstId,
                    "matType2": this.kindData.secondId,
                    "matType3": this.kindData.thirdId,
                    "modelMatName": this.editInfo.connonName,
                    "aliasName": this.editInfo.subTitle,
                    "introduction": this.editInfo.introduction,
                    "modelMatCode": this.editInfo.productNum,
                    "imgIds": this.fileId.join(","),
                    "keyWord": this.editInfo.productName,
                    "remark": this.editInfo.remark,
                    "state": this.swichOff,
                    "mbdId": this.brandListMosel,
                    'sellUnit': that.sellUint
                }
                let url = utils.getServicePathByObj(param);
                utils.wxRequest(url, null, 'post', function (jsondata) {
                    if (jsondata.code == 1) {
                        that.wmsModelId = jsondata.obj.wmsModelId
                        that.$message.success("保存成功")
                        that.drawer2 = false
                        that.drawers = false
                        that.drawer3 = true
                    } else {
                        that.$message.error(jsondata.meg)
                        return
                    }
                })

            } else if (i == 5) {
                if (this.wmsModelId == '') {
                    this.drawer2 = false
                    this.drawers = false
                    this.drawer3 = false
                    this.nextStep(2)
                    this.shuxing2 = []
                } else {
                    let that = this
                    const param = {
                        "service": "mdModelMaterielService",
                        "func": "deleteMdModelMateriel",
                        "wmsModelId": this.wmsModelId
                    }
                    let url = utils.getServicePathByObj(param);
                    utils.wxRequest(url, null, 'post', function (jsondata) {
                        if (jsondata.code == 1) {
                            that.drawer2 = false
                            that.drawers = false
                            that.drawer3 = false
                            that.nextStep(2)
                            that.shuxing2 = []
                        } else {
                            that.$message.error(jsondata.meg)
                        }
                    })
                }
            } else if (i == 6) {
                console.log("测试必填参数！")
                let that = this
                for (let j = 0; j < that.bangding1.length; j++) {
                    if (that.bangding1[that.mustEdit.indexOf("1")] == '') {
                        let inde = that.mustEdit.indexOf("1")
                        that.$message.error("配置参数列表中存在必填参数"+that.selectItem[inde].name+"未填写，请检查后提交！")
                        return
                    }
                }
                if (that.dynamicTags.length == 0) {
                    that.$message.error('请填规格信息！')
                    return
                }

                if (that.sellUint == '') {
                    that.$message.error('请填写销售单位！')
                    return
                }

               this.saveBrand();

            } else if (i == 7) {
                this.drawer2 = false
                this.drawers = true
            }

        },
        saveParameter(that) {
            //配置更多属性内容
            for (let i = 0; i < that.bangding1.length; i++) {
                if (that.bangding1[i] instanceof Array) {
                    that.bangding1[i].join(',')
                }
            }
            const data1 = {
                "service": "mdModelMaterielService",
                "func": "saveMdModelMaterielInfoParameter",
                wmsModelId: that.wmsModelId,  //模型id
                mmpIds: that.canshuId.join(','),
                mmpContents: encodeURIComponent(this.bangding1.join('#'))
            }
            let data = utils.getServicePathByObj(data1);
            utils.wxRequest(data, null, 'post', function (jsonData) {
                if (jsonData.code == 1) {
                    that.updateMdModelMaterielInfo()
                    that.drawers = false
                    that.drawer2 = false
                    that.drawer3 = false
                    that.showData = ''
                    that.showDataToo = ''
                    that.showDataThree = ''
                    that.current = -1;
                    that.current1 = -1;
                    that.current2 = -1;
                    that.editInfo = {}
                    that.fileId = []
                    that.rootPathList = []
                    that.fileList = []
                    that.shuxiang.length = 0
                    that.addModels.specifications = ''
                    that.selectedImg = []
                    that.brandListMosel = ''
                    that.dynamicTags = []
                    that.attrId = []
                    that.dynamicTagses = []
                    that.addModels.retrieval = ''
                    that.sellUint = ''
                    that.bangding1 = [];
                    that.canshuId = [];
                    that.bangding1.length = 0;
                    that.canshuId.length = 0;
                    that.selectItem.length = 0;
                    that.selectQuery()
                } else if (jsonData.code == 3) {
                    // that.handledrawer3Close()
                    that.$message.error(jsonData.meg + "请重新填写参数")
                }
                that.lastStepBtn = false
            }, true)
        },
        saveFormat(that) {
            //提交参数信息
            const param1 = {
                "service": "mdModelMaterielFormatService",
                "func": "saveMdModelFormat",
                wmsModelId: this.wmsModelId,  //模型id
                mmfNames: this.dynamicTags.join(","),//商品规格数组
                mdAttrIds: this.attrId,//关联属性id
                canSearch: this.addModels.retrieval,//能否进行检索
                attrContents: this.dynamicTagses.join(',')//属性内容拼接
            }
            let urls = utils.getServicePathByObj(param1);
            utils.wxRequest(urls, null, 'post', function (jsondata) {
                if (jsondata.code = 1) {
                    // that.$message.success("提交成功")
                    that.saveParameter(that);
                } else {
                    that.$message.error(jsondata.meg)
                    that.lastStepBtn = false
                }
            }, true)
        },
        saveSellUnit(that) {
            const data10 = {
                service: "mdModelMaterielService",
                func: "saveMdModelMaterielSellUnit",
                wmsModelId: that.wmsModelId,  //模型id
                sellUnit: that.sellUint
            }
            let data11 = utils.getServicePathByObj(data10);
            utils.wxRequest(data11, null, 'post', function (jsonData) {
                if (jsonData.code == 1) {
                    that.saveFormat(that);
                    console.log("提交成功")
                } else {
                    that.$message.error(jsondata.meg)
                    that.lastStepBtn = false
                }
            }, true)
        },
        saveBrand() {
            let that = this
            that.lastStepBtn = true;
            //提交品牌
            const data8 = {
                service: "mdModelMaterielService",
                func: "saveMdModelMaterielBrand",
                wmsModelId: that.wmsModelId,  //模型id
                mbdIds: that.brandListMosel + ''
            }
            let data9 = utils.getServicePathByObj(data8);
            utils.wxRequest(data9, null, 'post', function (jsonData) {
                if (jsonData.code == 1 || jsonData.code == 2) {
                    // that.saveMdModelMaterielSellUnit()
                    that.saveSellUnit(that);
                    console.log("提交成功")
                } else {
                    that.$message.error(jsondata.meg)
                    that.lastStepBtn = false;
                }
            }, true)
        },
        //
        saveMdModelMaterielSellUnit(){
            let that = this
            const data10={
                service: "mdModelMaterielService",
                func: "saveMdModelMaterielSellUnit",
                wmsModelId: that.wmsModelId,  //模型id
                sellUnit:that.sellUint
            }
            let data11 = utils.getServicePathByObj(data10);
            utils.wxRequest(data11,null,'post',function (jsonData){
                if (jsonData.code == 1){
                    console.log("提交成功")
                }
            })
        },
        //更新商品模型
        updateMdModelMaterielInfo(){


        },
        selectBrand(){
            this.brandloading = true
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'getMdMaterielBrandInfo',
                state:'1',
                page:'1',
                limit:'100',}
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
               that.brandList = jsondata.items
                that.brandloading = false
            });
        },
        selectUint(){
            let that = this
            var param = {
                service: 'sysUnitParamService',
                func: 'getSysUnitParamSellUnit',
               }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                that.uintList = jsondata.items
            });
        },
        selectSel(){
            this.brandloading = true
            console.log("回车查询")
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'getMdMaterielBrandInfo',
                mbdName:'',
                state:'',
                page:this.currentPage,
                limit:this.pageSize,}
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                that.brandList = jsondata.items
                that.brandloading = false
            });
        },
        selectAttr(){
            console.log("chaxun")
            this.shuxing2 = []
            this.shuxing2s = []
            let that = this
            //查询属性相关信息
            const params = {
                "service": "mdMaterielInfoService",
                "func": "getmdMaterielAttributeInfo",
                "state":"1",
                "limit":'',
                "page":''
            }
            let urls = utils.getServicePathByObj(params);
            utils.wxRequest(urls, null, 'post', function (jsondata) {

                for (let i = 0; i < jsondata.items.length; i++) {
                    let attr = {
                        value: jsondata.items[i].mmaCode,
                        label: jsondata.items[i].mmaName,
                        optionalValue: jsondata.items[i].optionalValue
                    };
                    that.shuxing2.push(attr)
                    that.shuxing2s.push(attr)
                }
            })
            //删除数组第一个元素
            //  that.shuxing2.shift()
            // that.shuxing2s.shift()
            //
            console.log(that.shuxing2s)
        },
        boxChange(i) {
            console.log(this.checkBox.registration)
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },

        currentChange(currentPage) {
            this.page = currentPage;
            this.selectQuery();
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.currentPage = 1;
            this.selectQuery(1, val);
            // console.log(`每页 ${val} 条`);
        },
        handleSizeChanges(val) {
            this.pageSizes = val;
            this.currentPages = 1;
            this.logInfo();
            // this.logInfo(1, val);
            // console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.selectQuery(val, this.pageSize);
            console.log(`当前页: ${val}`);
        },
        handleCurrentChanges(val) {
            this.currentPages = val;
            this.logInfo();
            // this.logInfo(val, this.pageSizes);
            console.log(`当前页: ${val}`);
        },
        refish() {
            this.queryFrom.paraName = ''
            this.queryFrom.paraRelated = ''
            this.queryFrom.paraSearch = ''
            this.selectQuery()

        },
        handledrawer2Close(){
            console.log("关闭方法")
            this.drawer2 = false
            this.showData = ''
            this.showDataToo=''
            this.showDataThree = ''
            this.editInfo.connonName = ''
            this.editInfo.subTitle = ''
            this.editInfo.introduction = ''
            this.editInfo.productNum = ''
            this.current1 = '-1'
            this.current = '-1'
            this.current2 = '-1'
            this.rootPathList.length = 0
            this.editInfo.productName = ''
            this.editInfo.remark = ''
            this.swichOff = ''

        },
        handledrawer3Close(){
            let that = this
            const params = {
                "service": "mdModelMaterielService",
                "func": "deleteMdModelMateriel",
                "wmsModelId":this.wmsModelId,
            }
            let urls = utils.getServicePathByObj(params);
            utils.wxRequest(urls, null, 'post', function (jsondata) {
                that.drawers = false
                that.drawer2 = false
                that.drawer3 = false
                that.dynamicTags = []
                that.attrId = []
                that.dynamicTagses = []
                that.showData = ''
                that.showDataToo = ''
                that.showDataThree = ''
                that.current = -1;
                that.current1 = -1;
                that.current2 = -1;
                that.editInfo = {}
                that.fileId = []
                that.rootPathList = []
                that.fileList = []
                that.shuxiang.length = 0
                that.sellUint = ''
                that.addModels.specifications = ''
                that.selectedImg = []
                that.brandListMosel = ''
                that.addModels.retrieval = ''
                that.drawer3 = false
                that.lastStepBtn = false
                that.bangding1 = [];
                that.canshuId = [];
                that.bangding1.length = 0;
                that.canshuId.length = 0;
                that.selectItem.length = 0;
                that.selectQuery()
            })
        },
    }
})