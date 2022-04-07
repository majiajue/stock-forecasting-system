<template>
  <el-container style="height: 100%">
    <el-aside width="auto">
      <SideBar />
    </el-aside>
    <el-container>
      <el-header>基于时间序列预测的股票预测系统</el-header>
      <el-main>
        <router-view />
      </el-main>
      <el-footer>
        Copyright ©2022
        <el-link href="https://github.com/YeQiuO">WangZheWen</el-link>, All
        Rights Reserved.
      </el-footer>
    </el-container>
  </el-container>
</template>

<style>
.el-header {
  margin-top: 10px;
  font-size: 25px;
  font-weight: bold;
}
.el-header,
.el-footer {
  text-align: center;
}
body > .el-container {
  margin-bottom: 40px;
}
::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;
  height: 0;
}
</style>

<script>
import SideBar from "@/components/SideBar.vue";

export default {
  components: {
    SideBar,
  },
};
</script>