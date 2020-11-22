var app = new Vue({
    el: '#para',
    data(){
        return{
            tableData:[],
            currentPage:1,
            pageSize:10,
            total: 0,
            multipleSelection: [],
            dialogVisible:false,
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
            queryFrom:{
                selectFrom:'',
                isShow:'',
                isMust:'',
            },
            options: [
                {value: '',label: '全部'},
                {value: '1', label: '显示'},
                {value: '2', label: '不显示'},
                ],
            optiones: [
                {value: '',label: '全部'},
                {value: '1', label: '是'},
                {value: '2', label: '否'}
            ],
            drawers:false,
            parameters:{
                paraRelateds:'',
                paraName:"",
                paraSearch:'',
                paraRelated:'',
                paratheway:'',
                paratheways:'',
                parathewayv:'',
                paraColumn:'',
                paraSort:'',
                mustEdit:'0',
                showA:'1',

            },
            chooseAttr:[],
            dynamicTags: [],
            dynamicTagsToo:[],
            inputVisible: false,
            inputVisibles:false,
            inputValue: '',
            inputValues:'',
            loading: false,
            saves:'',
            num: -1,
            words: '',
            mmpId:''
        }
    },
    created() {

    },
    mounted() {
        this.selectQuery();
    },
    methods:{
        querySub(){
            console.log(this.mmpId)
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'saveOrUpdateMdMaterielParameter',
                mmpId:that.mmpId,
                mmpName:that.parameter.paraName,
                isSearch:that.parameter.paraSearch,
                relation:that.parameter.paraRelated,
                optionaValue:that.dynamicTagsToo.join(","),
                mmpSort:that.parameter.paraSort,
                inputMode:that.parameter.paratheway,
                isRequired:that.parameter.isRequired,
                state:that.parameter.state
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1 ){
                    that.$message.success("提交成功")
                    that.dialogVisible = false
                    that.selectQuery()
                }
            });
        },
        refish(){
            this.queryFrom.selectFrom = ''
            this.queryFrom.isShow = ''
            this.queryFrom.isMust = ''
            this.selectQuery()
        },
        boxsChange(){
            this.dynamicTags = []
            console.log(this.parameters.paratheway)
        },
        backAttr(){
            this.drawers = false
            this.dynamicTags = []
        },
        addPara(){
            if (this.parameters.paraName == ''){
                this.$message.error('请填写参数名！')
                return
            }
            if (this.parameters.paratheway == ''){
                this.$message.error('请选择参数录入方式！')
                return
            }
            if (this.parameters.paratheway != 1){
                if (this.dynamicTags.length == 0){
                    this.$message.error('请填写值列表！')
                    return
                }
            }
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'saveOrUpdateMdMaterielParameter',
                mmpName:that.parameters.paraName,
                isSearch:that.parameters.parathewayv,
                relation:that.parameters.paraSearch,
                optionaValue:that.dynamicTags.join(","),
                mmpSort:that.parameters.paraSort,
                inputMode:that.parameters.paratheway,
                state:that.parameter.parathewayv,
                isRequired:that.parameters.mustEdit,
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1 ){
                    that.$message.success("新增成功")
                    that.drawers = false
                    that.parameters.paraName = ''
                    that.parameters.parathewayv = ''
                    that.parameters.paraSearch = ''
                    that.dynamicTags = []
                    that.parameters.paratheway = ''
                    that.parameters.mustEdit=''
                    that.selectQuery()
                }else if (jsondata.code == 2){
                    that.$message.error("参数名已存在！")
                }
            });
        },
        chooesPara(row){
            for (let i=0;i<this.dynamicTags.length;i++){
                if (row.mmpName == this.dynamicTags[i]){
                    this.$message.error("插入的可选值重复")
                    return
                }
            }
            this.dynamicTags.push(row.mmpName)
        },
        editRow(row){
            this.dynamicTagsToo.length = 0
            this.mmpId = row.mmpCode
            this.parameter.paraSort = ''
            console.log(row)
            this.dialogVisible = true
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'findMdMaterielParameter',
                mmpId:row.mmpCode
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1 ){
                    that.parameter.paraName = jsondata.obj.mmpName
                    that.parameter.paraSearch = jsondata.obj.isSearch
                    if (jsondata.obj.optionaValue != ''){
                        that.dynamicTagsToo = jsondata.obj.optionaValue.split(",")
                    }

                    that.parameter.paratheway = jsondata.obj.inputMode
                    that.parameter.paraRelated = jsondata.obj.relation
                    that.parameter.paraSort = jsondata.obj.mmpSort
                    that.parameter.isRequired = jsondata.obj.isRequired
                    that.parameter.state = jsondata.obj.state
                }
            });
        },
        deleteRowS(){
            let that = this
            let mmpCode = [];
            for (let i = 0;i<this.multipleSelection.length;i++){
                mmpCode.push(this.multipleSelection[i].mmpCode)
            }
            var param = {
                service: 'mdMaterielInfoService',
                func: 'deleteMdMaterielParameter',
                mmpId:mmpCode.join(",")
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1){
                    that.$message.success("删除成功")
                    that.selectQuery()
                }else {
                    that.$message.error(jsondata.meg)
                }
            });
        },
         showTtemWin(){
              this.drawers = true
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
        handleInput(tag, index) {
            let words = this.words;
            if (words) {
                this.dynamicTagsToo[index] = words;
            }
            this.dynamicTagsToo = this.unique(this.dynamicTagsToo);
            this.words = '';
            this.num = -1;
        },
        handleClose(tag) {
            this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
        },
        handleCloses(tag) {
            this.dynamicTagsToo.splice(this.dynamicTags.indexOf(tag), 1);
        },

        showInput() {
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
                this.$refs.saveTagInput.$refs.input.focus();
            });
        },

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
            let inputValue = this.inputValues;
            if (inputValue) {
                this.dynamicTagsToo.push(inputValue);
            }
            this.inputVisibles = false;
            this.inputValues = '';
        },
        //查询表格数据
        selectQuery(){
            this.loading = true
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'getMdMaterielParameter',
                mmpName:that.queryFrom.selectFrom,
                isRequired:that.queryFrom.isMust,
                state:that.queryFrom.isShow,
                page:that.currentPage,
                limit:that.pageSize,}
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                that.tableData = jsondata.items
                that.total = jsondata.total
                that.loading = false
            });
        },
        deleteRow(row){
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'deleteMdMaterielParameter',
                mmpId:row.mmpCode
                }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1){
                    that.$message.success("删除成功")
                    that.selectQuery()
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
        isRequired(row){
            if (row.isRequired == 1){
                return "是"
            }else {
                return "否"
            }
        },
        state(row){
            if (row.state == 1){
                return "显示"
            }else {
                return "不显示"
            }
        }
    }
})