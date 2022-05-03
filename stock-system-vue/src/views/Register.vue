<template>
  <div class="login-container">
    <el-form :model="user"
             :rules="rules"
             status-icon
             ref="user"
             label-position="left"
             label-width="80px"
             class="login-page">

      <h3 class="title">
        <i class="el-icon-cpu el-icon--left"></i>用户注册
        <el-button round
                   size="mini"
                   @click="back">
          登录
        </el-button>
      </h3>

      <el-form-item prop="name"
                    label="用户名">
        <el-input type="text"
                  v-model="user.name"
                  auto-complete="off"
                  placeholder="用户名"></el-input>
      </el-form-item>

      <el-form-item prop="password"
                    label="用户名">
        <el-input type="text"
                  v-model="user.password"
                  auto-complete="off"
                  placeholder="密码"></el-input>
      </el-form-item>

      <el-form-item prop="birthday"
                    label="出生日期">
        <el-date-picker v-model="user.birthday"
                        type="date"
                        style="width: 100%;"
                        placeholder="选择日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item style="width: 100%"
                    label-width="0px">
        <el-button type="primary"
                   style="width: 100%;"
                   @click="register"
                   :loading="logining">注册</el-button>
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
      user: {
        name: "",
        password: "",
        birthday: "",
      },
      value1: '',
      // 表单验证规则
      rules: {
        name: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur"
          },
        ],
        birthday: [
          {
            required: true,
            message: "请选择出生日期",
            trigger: "blur"
          },
        ],
      },
      // 记住密码
      checked: false,
    };
  },

  methods: {
    // 跳转登录界面
    back () {
      this.$router.push({ path: "/login" });
    },
    register () {
      this.$post("/user/register", this.user).then((result) => {
        if (result.success) {
          this.$router.push({ path: "/login" });
        }
      }).catch((err) => {
        console.error(err);
      });
    }
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
.title > .el-button {
  float: right;
}
</style>