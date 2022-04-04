<template>
  <el-row class="tac">
    <el-col :span="12">
      <h5>导航栏</h5>
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
      >
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>导航一</span>
          </template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="1-1">选项1</el-menu-item>
            <el-menu-item index="1-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="1-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">选项4</template>
            <el-menu-item index="1-4-1">选项1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <router-link to="/">
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">Home</span>
          </el-menu-item>
        </router-link>
        <router-link to="/dashboard">
          <!-- disabled -->
          <el-menu-item index="3">
            <i class="el-icon-document"></i>
            <span slot="title">Dashboard</span>
          </el-menu-item>
        </router-link>
        <router-link to="/about">
          <el-menu-item index="4">
            <i class="el-icon-setting"></i>
            <span slot="title">About</span>
          </el-menu-item>
        </router-link>
      </el-menu>
    </el-col>
  </el-row>
</template> 


<style>
/* :not() 用来匹配不符合一组选择器的元素 */
/*  在 separated 模式下，相邻的单元格都拥有独立的边框；在 collapsed 模式下，相邻单元格共享边框 */
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  height: 600px;
}
/* 去掉原有链接文字下划线 */
a {
  text-decoration: none;
}
/* 去掉点击时的下划线 */
.router-link {
  text-decoration: none;
}
</style>

<script>
export default {
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>
