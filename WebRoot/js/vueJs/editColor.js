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
                showIndex:'',
                attr:''
            },
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
            attr:JSON.parse(window.sessionStorage.getItem("attrbute")),
            options: [{
                value: '',
                label: '全部'
            },{
                value: '1',
                label: '可用'
            }, {
                value: '2',
                label: '不可用'
            }],
            optionses: [{
                value: '',
                label: '所有'
            },{
                value: '1',
                label: '唯一'
            }, {
                value: '2',
                label: '单选'
            },{
                value: '3',
                label: '复选'
            }],
            dialogFormVisible:false,
            form: {
                name: '',
                region: '',
                date1: '',
                date2: '',
                delivery: false,
                resource: '',
                desc: ''
            },
            color1: '#409EFF',
            color: 'rgba(255, 69, 0, 0.68)',
            predefineColors: [
                '#ff4500',
                '#ff8c00',
                '#ffd700',
                '#90ee90',
                '#00ced1',
                '#1e90ff',
                '#c71585',
                'rgba(255, 69, 0, 0.68)',
                'rgb(255, 120, 0)',
                'hsv(51, 100, 98)',
                'hsva(120, 40, 94, 0.5)',
                'hsl(181, 100%, 37%)',
                'hsla(209, 100%, 56%, 0.73)',
                '#c7158577'
            ],
            loading: false,
            mmamxId:'',

        }
    },
    created() {
        this.Clickquery()
    },
    mounted() {
        this.Clickquery()
        // this.attr = window.sessionStorage.getItem("attrbute")

    },
    methods:{
        Clickquery(){
            this.loading = true
            console.log(111)
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'getmdMaterielAttributeMxInfo',
                mmamxName:that.queryFrom.brand,
                state:that.queryFrom.showIndex,
                isOptional:that.queryFrom.attr,
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
        //删除品牌
        deleteColor(row){
            console.log(row)
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'deleteMaterielAttributeMx',
                mmamxId:row.mmamxCode
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code == 1){
                    that.$message.success('删除成功！');
                     that.Clickquery()
                }else if (jsondata.code == 3){
                    that.$message.error("属性被引用无法删除")
                }
            });
        },
        colorChange(val){
            console.log(this.parameter.paraRelated)
        },
        subColor(){
            if (this.parameter.paraName == ''){
                this.$message.error("请填写颜色名称")
                return
            }
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'saveOrUpdateMdMaterielAttributeMx',
                mmamxName:that.parameter.paraName,
                attribute:encodeURIComponent(that.parameter.paraRelated),
                state:that.parameter.parathewayv
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code ==1){
                    that.$message.success("提交成功")
                    that.drawers = false
                    that.Clickquery()
                    that.parameter = {}
                }
            });
        },
        //编辑颜色
        editColor(row){
            this.mmamxId = row.mmamxCode
            this.dialogFormVisible = true;
            this.form.name = row.mmamxName
            this.form.region = row.attribute
            this.form.date2 = row.state
            console.log(row)

        },
        edits(){
            let that = this
            var param = {
                service: 'mdMaterielInfoService',
                func: 'saveOrUpdateMdMaterielAttributeMx',
                mmamxId:that.mmamxId,
                mmamxName:that.form.name,
                attribute:encodeURIComponent(that.form.region),
                state:that.form.date2
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                if (jsondata.code ==1){
                    that.$message.success("修改成功")
                   that.dialogFormVisible = false
                    that.Clickquery()
                }
            });
        },
        deleteColors(){
            let that = this
            let mbaCode = [];
            for (let i = 0;i<this.multipleSelection.length;i++){
                mbaCode.push(this.multipleSelection[i].mmamxCode)
            }
            var param = {
                service: 'mdMaterielInfoService',
                func: 'deleteMaterielAttributeMx',
                mmamxId:mbaCode.join(",")
            }
            let url = utils.getServicePathByObj(param);
            utils.wxRequest(url, null, 'post', function (jsondata) {
                console.log("111")
                if (jsondata.code == 1){
                    that.$message.success('删除成功！');
                    that.Clickquery()
                }else if (jsondata.code == 3){
                    that.$message.error("属性被引用无法删除")
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
        stateFor(row){
            if (row.state == 1){
                return "可用"
            }else {
                return "不可用"
            }
        }
    }
})