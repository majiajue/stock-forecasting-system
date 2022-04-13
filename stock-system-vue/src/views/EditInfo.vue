<template>
  <div>
    <el-form ref="form"
             :model="user"
             label-width="80px">
      <el-form-item label="用户名">
        <el-input type="text"
                  v-model="user.name"
                  auto-complete="off"
                  placeholder="请填写用户名"></el-input>
      </el-form-item>

      <el-form-item label="用户密码">
        <el-input type="password"
                  v-model="user.password"
                  auto-complete="off"
                  placeholder="请填写密码"></el-input>
      </el-form-item>
      <el-form-item label="出生时间">
        <el-col :span="11">
          <el-date-picker type="date"
                          placeholder="选择日期"
                          v-model="user.birthday"
                          style="width: 100%;"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"
                   @click="onSubmit">提交修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

export default {
  computed: {

  },

  created: function () {
    this.user = JSON.parse(
      window.sessionStorage.getItem("user")
    )
  },
  data () {
    return {
      user: null,
    }
  },

  methods: {
    onSubmit () {
      this.$post("/user/update", this.user).then((result) => {
        sessionStorage.setItem("user", JSON.stringify(this.user));
        this.$message({
          type: 'success',
          message: '修改成功!'
        });
      }).catch((err) => {
        this.$message({
          type: 'info',
          message: '修改出错' + err
        });
      });
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
