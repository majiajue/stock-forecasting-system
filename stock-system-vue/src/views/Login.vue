<template>
  <div class="login-container">
    <el-form :model="ruleForm"
             :rules="rules2"
             status-icon
             ref="ruleForm"
             label-position="left"
             label-width="0px"
             class="demo-ruleForm login-page">
      <h3 class="title">
        <i class="el-icon-cpu el-icon--left"></i>系统登录
      </h3>
      <el-form-item prop="username">
        <el-input type="text"
                  v-model="ruleForm.username"
                  auto-complete="off"
                  placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password"
                  v-model="ruleForm.password"
                  auto-complete="off"
                  placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox v-model="checked"
                   class="rememberme">记住密码</el-checkbox>
      <el-form-item style="width: 100%">
        <el-button type="primary"
                   style="width: 100%"
                   @click="handleSubmit"
                   :loading="logining">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      // 按钮加载提示
      logining: false,
      // 表单数据对象
      ruleForm: {
        username: "admin",
        password: "123456",
      },
      // 表单验证规则
      rules2: {
        username: [
          {
            required: true,
            message: "please enter your account",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "enter your password",
            trigger: "blur"
          },
        ],
      },
      // 记住密码
      checked: false,
    };
  },

  methods: {
    // 登录
    handleSubmit (event) {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.logining = true;
          if (this.ruleForm.username === "admin" && this.ruleForm.password === "123456") {
            this.logining = false;
            sessionStorage.setItem("user", this.ruleForm);
            this.$router.push({ path: "/" });
          } else {
            this.logining = false;
            this.$alert("用户名或密码错误", "登陆失败", {
              confirmButtonText: "ok",
            });
          }
        } else {
          console.log("登陆出错!");
          return false;
        }
      });
    },
  },
};
</script>

<style scoped>
.login-container {
  width: 100%;
  height: 100%;
}
.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}
</style>