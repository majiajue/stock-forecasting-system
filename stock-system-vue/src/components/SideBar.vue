<template>
  <el-row class="tac">
    <el-col :span="12">
      <h5>导航栏</h5>
      <el-menu class="el-menu-vertical-demo"
               @open="handleOpen"
               @close="handleClose">

        <router-link to="/qs">
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">调查问卷</span>
          </el-menu-item>
        </router-link>

        <router-link to="/dashboard">
          <el-menu-item index="2"
                        :disabled="this.user.score == -1">
            <i class="el-icon-document"></i>
            <span slot="title">股票预测</span>
          </el-menu-item>
        </router-link>

        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-setting"></i>
            <span slot="title">设置</span>
          </template>
          <el-menu-item-group title="关于用户">
            <router-link to="/edit">
              <el-menu-item index="3-1">修改个人信息</el-menu-item>
            </router-link>
          </el-menu-item-group>
          <el-menu-item-group title="关于系统">
            <el-menu-item index="3-2">关于我们</el-menu-item>
            <el-menu-item index="3-3">意见反馈</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-col>
  </el-row>
</template> 

<script>
export default {

  computed: {
    user () {
      return JSON.parse(
        window.sessionStorage.getItem("user")
      )
    },
  },

  data () {
    return {
    }
  },

  methods: {
    // 折叠 handle 打开时
    handleOpen (key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>


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
