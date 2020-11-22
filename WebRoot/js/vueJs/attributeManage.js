var app = new Vue({
    el: '#para',
    data(){
        return{
            tableData:[],
            tableDatas:[],
            chooseAttr:[],
            chooseAttrs:[],
            editAttr:[],
            editAttrs:[],
            currentPage:1,
            pageSize:10,
            total: 0,
            totals:0,
            currentPages:1,
            pageSizes:5,
            multipleSelection: [],
            dialogVisible:false,
            drawers:false,
            parameter:{
                mmaName:"",
                screenType:'',
                isOptional:'',
                isSearch:'',
                inputModel:'',
                parathewayv:'',
                optionalValue:'',
                paraSort:'',
                newAdd:'',
            },
            parameters:{
                paraRelateds:'',
                paraName:"",
                paraSearch:'',
                paraRelated:'',
                paratheway:'',
                paratheways:'1',
                parathewayv:'',
                paraColumn:'',
                mmaSort:'',
                state:'1'
            },
            queryFrom:{
                selectFrom:'',
                isShow:'',
                isMust:'',
            },
            options: [
                {value: '', label: '所有'},
                {value: '1', label: '是'},
                {value: '2', label: '否'}],
            optiones: [
                {value: '', label: '所有'},
                {value: '1', label: '唯一'},
                {value: '2', label: '单选'},
                {value: '3', label: '复选'}
            ],
            mmaCode:'',
            dynamicTags: [],
            dynamicTagsToo: [],
            inputVisible: false,
            inputValue: '',
            inputVisibles: false,
            inputValues: '',
            tableShow:false,
            loading: false,
            draShow:false,
        }
    },
    created() {
        this.selectColor()
    },
    mounted() {
        this.selectQuery();
    },
    methods:{
        handleInputConfirm() {
            console.log("111")
            let inputValue = this.inputValue;
            for (let i=0;i<this.dynamicTags.length;i++){
                if (inputValue == this.dynamicTags[i]){
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
        handleInputConfirms() {
            console.log("111")
            let inputValues = this.inputValues;
            for (let i=0;i<this.dynamicTagsToo.length;i++){
                if (inputValues == this.dynamicTagsToo[i]){
                    this.$message.error("插入的可选值重复")
                    return
                }
            }
            if (inputValues) {
                this.dynamicTagsToo.push(inputValues);
            }
            this.inputVisibles = false;
            this.inputValues = '';
        },

        radioChange(){
            console.log(this.parameters.paratheways)
            if (this.parameters.paratheways == 2){
                this.tableShow = true
            }else {
                this.tableShow = false
            }
        },
        handleClose(tag) {
            this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
        },
        handleCloses(tag) {
            this.dynamicTagsToo.splice(this.dynamicTagsToo.indexOf(tag), 1);
        },
        showInput() {
            if (this.parameters.paraRelateds == 1 && this.dynamicTags.length>=1){
                this.$message.error("唯一属性只能添加一个可选值！")
                return
            }
            console.log("222")
            this.inputVisible = true;
            this.$nextTick(_ => {
                this.$refs.saveTagInput.$refs.input.focus();
            });
        },
        showInputs() {
            console.log("222")
            this.inputVisibles = true;

            this.$nextTick(_ => {
                this.$refs.saveTagInputs.$refs.input.focus();
            });
        },
        chooesAttr(row,i){
            if (i==1){
                let inputValue = row.mmamxName;
                for (let i=0;i<this.dynamicTagsToo.length;i++){
                    if (inputValue == this.dynamicTagsToo[i]){
                        this.$message.error("插入的可选值重复")
                        return
                    }
                }
                this.dynamicTagsToo.push(row.mmamxName)
            }
            console.log("111")
            if (i==2){
                let inputValue = row.mmamxName;
                for (let i=0;i<this.dynamicTags.length;i++){
                    if (inputValue == this.dynamicTags[i]){
                        this.$message.error("插入的可选值重复")
                        return
                    }
                }
                this.dynamicTags.push(row.mmamxName)
            }else {

            }
        },
        diaaddAttr(i){
            if (i == 2){
                this.chooseAttrs.push(this.parameters.paraColumn)
            }else {
                this.editAttrs.push(this.parameter.optionalValue)
            }
        },
        editRow(row){
            this.mmaCode = row.mmaCode
            this.dialogVisible = true
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'findAttributeOptionalValue',
                mmaId:row.mmaCode,
               }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                 that.parameter = jsondata.obj
                if(jsondata.obj.optionalValues !== ''){
                    that.dynamicTagsToo = jsondata.obj.optionalValues.split(",")
                }
            });
        },
        showTtemWins(){
            this.selectQuery()
            this.refish()
            this.drawers = true
        },
        refish(){
            this.queryFrom.selectFrom = ''
            this.queryFrom.isShow = ''
            this.queryFrom.isMust = ''
            this.selectQuery()
        },
        //查询表格数据
        selectQuery(){
            this.loading = true
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'getmdMaterielAttributeInfo',
                mmaName:this.queryFrom.selectFrom,
                state:this.queryFrom.isShow,
                isOptional:this.queryFrom.isMust,
                page:this.currentPage,
                limit:this.pageSize,}
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                that.tableData = jsondata.items
                that.total = jsondata.total
                that.loading = false
            });
        },
        selectQuerys(){
            console.log("111")
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'getmdMaterielAttributeInfo',
                page:this.currentPages,
                limit:this.pageSizes}
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                that.tableDatas = jsondata.items
                that.totals = jsondata.total
            });
        },
        selectColor(){
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'getmdMaterielAttributeMxInfo',
                state:'1',
                page:this.currentPages,
                limit:this.pageSizes}
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                that.tableDatas = jsondata.items
                that.totals = jsondata.total
            });
        },
        editColor(){
            console.log("111")
            const param = {
                "url":"/dentistmall/html/component/editColor.html",
                "title":"颜色属性",
            };
            if(param.url != null && param.url !=""){
                var title = param.title?param.title:"";
                parent.window.addMenuItem(param.url,title);
            }
        },
        addAttr(){
            if(this.parameters.paraName == ''){
                this.$message.error("请填写属性名称！")
                return
            }
            if(this.parameters.paratheway == ''){
                this.$message.error("请选择属性录入方式！")
                return
            }
            if(this.parameters.paratheway == 2){
                if (this.dynamicTags.length == 0){
                    this.$message.error("请添加属性可选值！")
                    return
                }
            }
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'saveOrUpdateMdMaterielAttribute',
                mmaName:that.parameters.paraName,
                isOptional:that.parameters.paraRelateds,
                inputModel:that.parameters.paratheway,
                isSearch:that.parameters.parathewayv,
                optionalValue:that.dynamicTags.join(","),//值列表this.dynamicTags
                mmaSort:that.parameters.paraSort,
                screenType:that.parameters.paratheways,
                newAdd:that.parameters.paraSearch,
                state:that.parameters.state,
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1){
                    that.$message.success("添加成功")
                    that.drawers = false;
                    that.dynamicTags = []
                    that.parameters.paraName = ''
                    that.parameters.paratheways = ''
                    that.parameters.parathewayv = ''
                    that.parameters.paratheway = ''
                    that.parameters.paraRelateds = ''
                    that.parameters.paraSearch = ''
                    that.parameters.paraSort = ''
                    that.tableShow = false
                    that.selectQuery()
                }else if (jsondata.code == 2){
                    that.$message.error("新增的属性名重复！")
                }
            });
        },
        addAttrs(){
            let that = this
            console.log(that.mmaCode)
            if (that.mmaCode == ''){
                return
            }
            var param = {
                service: 'mdMaterielInfoService',
                func: 'saveOrUpdateMdMaterielAttribute',
                mmaId:that.mmaCode,
                mmaName:that.parameter.mmaName,
                isOptional:that.parameter.isOptional,
                inputModel:that.parameter.inputModel,
                isSearch:that.parameter.isSearch,
                optionalValue:that.dynamicTagsToo.join(","),
                screenType:that.parameter.screenType,
                newAdd:that.parameter.newAdd,
                mmaSort:that.parameter.mmaSort,
                state:that.parameter.state
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1){
                    that.$message.success("修改成功")
                    that.dialogVisible = false
                    that.selectQuery();
                }else if (jsondata.code == 0){
                    that.$message.error("系统出错，请联系管理员！")
                }else if (jsondata.code == 2){
                    that.$message.error("属性名重复！")
                }
            });
        },
        deleteRowS(){
            let that = this
            let mmaCode = [];
            for (let i = 0;i<this.multipleSelection.length;i++){
                mmaCode.push(this.multipleSelection[i].mmaCode)
            }
            var param = {
                service: 'mdMaterielInfoService',
                func: 'deleteMaterielAttribute',
                mmaIds:mmaCode.join(",")
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1){
                    that.$message.success("删除成功")
                    that.selectQuery()
                }else if (jsondata.code ==3){
                    that.$message.error("该属性被引用无法删除！")
                }
            });
        },
        deleteRow(row){
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'deleteMaterielAttribute',
                mmaIds:row.mmaCode
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1){
                    that.$message.success("删除成功")
                    that.selectQuery()
                }else if (jsondata.code ==3){
                    that.$message.error("该属性被引用无法删除！")
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
        },
        currentChange(currentPage) {
            console.log("111")
            this.page = currentPage;
            this.selectQuery();
        },
        handleSizeChange(val){
            console.log("222")
            this.pageSize = val;
            this.currentPage = 1;
            this.selectQuery(1, val);
            // console.log(`每页 ${val} 条`);
        },
        handleSizeChanges(val){
            console.log("222")
            this.pageSizes = val;
            this.currentPages = 1;
            this.selectColor(1, val);
            // console.log(`每页 ${val} 条`);
        },
        changeCurr(){
            this.currentPage = ''
        },
        handleCurrentChange(val){
            console.log("333")
            this.currentPage = val;
            this.selectQuery(val, this.pageSize);
            console.log(`当前页: ${val}`);
        },
        handleCurrentChanges(val){
            console.log("333")
            this.currentPages = val;
            this.selectColor(val, this.pageSizes);
            console.log(`当前页: ${val}`);
        },
        isOptional(row, column){
            if (row.isOptional == 1) {
                return '唯一属性'
            } else if(row.isOptional == 2)  {
                return '单选属性'
            }else {
                return "复选属性"
            }
        },
        inputModel(row,column){
            if (row.inputModel == 1) {
                return '手动录入'
            } else if(row.inputModel == 2)  {
                return '从列表选择'
            }
        },
        stateSetting(row,column){
            if (row.state == 1) {
                return '启用'
            } else if(row.state == 2)  {
                return '禁用'
            }
        },
        switchChange(row) {
            console.log(JSON.stringify(row))
            let that = this;
            var param = {
                service: 'mdMaterielInfoService',
                func: 'saveOrUpdateMdMaterielAttributeState',
                mmaId: row.mmaCode,
                state:row.state
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1) {
                    that.$message.success('更新成功');
                }
            });
        },
    }
})