<template>
  <div>
    <el-row>欢迎用户 {{user.name}}</el-row>
  </div>
</template>

<script>

export default {
  name: 'home',
  computed: {
    user () {
      return JSON.parse(
        window.sessionStorage.getItem("user")
      )
    }
  }
}
</script>

<style lang="scss" scoped>
.el-row {
  margin-top: 30px;
  font-size: 30px;
  font-weight: bold;
}
</style>
