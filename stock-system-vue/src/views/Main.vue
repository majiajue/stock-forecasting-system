<template>
  <el-container style="height: 100%">
    <el-aside width="auto">
      <SideBar />
    </el-aside>
    <el-container>
      <el-header>Header 待开发</el-header>
      <el-main> 
          <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<style>
.el-header {
  background-color: rgb(204, 255, 248);
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