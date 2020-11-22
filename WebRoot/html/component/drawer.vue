<template>
  <div>
    <el-drawer :visible.sync="drawers" size="80%">
    <el-steps :active="1" finish-status="success" simple>
      <el-step title="1、选择商品分类"></el-step>
      <el-step title="2、填写商品信息"></el-step>
      <el-step title="3、填写属性提交模型"></el-step>
    </el-steps>
    <div>
      <el-container>
        <el-aside width="200px" style="line-height: 418px">选择商品分类</el-aside>
        <el-main>
          <div style="line-height: 10px">
            最近使用的商品分类
            <el-select v-model="value" clearable placeholder="请选择">
            </el-select>
          </div>

          <div style="display: inline-block;margin-top: 20px;padding-left: 10px">
            <el-card class="box-card" style="float: left">
              <div slot="header" class="clearfix">
                <span>选择一级分类</span>
              </div>
              <div v-for="(item,index) in itemMenus" :key="index" class="text item">
                <li @click="selectMenu(item,index)":class="{active:index==current}">
                  {{item.mmtName}}
                </li>
              </div>
            </el-card>
            <img src="../img/jiantou4.png" style="float: left;width: 94px;height: 89px;margin-top: 130px"v-if="p_show">
            <el-card class="box-card" style="float: left;margin-left: 10px" v-if="p_show">
              <div slot="header" class="clearfix">
                <span>选择二级分类</span>
              </div>
              <div v-for="(item,index) in itemMenusToo" :key="index" class="text item" style="cursor: pointer">
                <a @click="selectMenuToo(item)">
                  {{item.mmtName}}
                </a>
              </div>
            </el-card>
            <img src="../img/jiantou4.png" style="float: left;width: 94px;height: 89px;margin-top: 130px"v-if="p_showToo">
            <el-card class="box-card" style="float: left;margin-left: 10px;" v-if="p_showToo">
              <div slot="header" class="clearfix">
                <span style="color: #66b1ff;text-align: center">选择三级分类</span>
              </div>
              <div v-for="(item,index) in itemMenusThree" :key="index" class="text item">
                <a @click="selectMenuToo(item)">
                  {{item.mmtName}}
                </a>
              </div>
            </el-card>
          </div>
          <div style="margin-top: 20px">
            <el-row>
              <el-col :span="24">
                <div>
                  您当前选择的分类是：<span style="color: red">{{this.showData+"/"}}{{this.showDataToo}}</span>
                </div>
              </el-col>
            </el-row>
          </div>
          <div style="margin-top: 20px">
            <el-button type="primary" @click="closeDrawer()" style="width: 181.68px">返回</el-button>
            <el-button type="primary" @click="submitForm2">下一步,填写商品属性</el-button>
          </div>
        </el-main>

      </el-container>
    </div>
    </el-drawer>
    <el-drawer>
    <el-steps :active="2" finish-status="success" simple style="margin-top: -15px">
      <el-step title="1、选择商品分类"></el-step>
      <el-step title="2、填写商品信息"></el-step>
      <el-step title="3、填写属性提交模型"></el-step>
    </el-steps>
    <el-container>
      <el-aside width="200px" style="line-height: 418px">Aside</el-aside>
      <el-main>
        <div>
          <el-row>
            <el-col :span="24">
              <div>
                <span style="color: red">*</span>商品分类：<span style="color: red">{{showData+"/"}}{{this.showDataToo}}</span>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-main>
    </el-container>
    </el-drawer>
  </div>
</template>
<script rel="script" src="../../js/vueJs/productInfo.js?v=11"></script>
<script>
module.exports = {
  data: function () {
    return {
      drawers:true
    }
  },
  methods: {
    submitForm2() {
      console.log("111")
    }
  },

}
</script>

<style>

</style>