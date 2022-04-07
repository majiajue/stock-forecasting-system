<template>
  <el-container>
    <el-aside width="auto">
      <SideBar />
    </el-aside>
    <el-container>
      <el-header>
        基于时间序列预测的股票预测系统
        <el-button round
                   size="mini"
                   @click="logout">
          <i class="el-icon-help"></i>
          登出
        </el-button>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
      <el-footer>
        Copyright ©2022
        <el-link href="https://github.com/YeQiuO">WangZheWen</el-link>
        , All Rights Reserved.
      </el-footer>
    </el-container>
  </el-container>
</template>


<script>
import SideBar from "@/components/SideBar.vue";

export default {
  components: {
    SideBar,
  },

  methods: {
    // 登录
    logout () {
      sessionStorage.clear();
      this.$router.push({ path: "/login" });
    }
  },
};
</script>

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
.el-main {
  padding: 0 20px;
}
.el-header > .el-button {
  float: right;
  font-size: 11px;
  font-weight: lighter;
}
/* .el-container {
  min-height: 100%;
}
body > .el-container {
  margin-bottom: 40px;
} */
::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;
  height: 0;
}
</style>