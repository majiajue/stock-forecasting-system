<template>
  <div>
    <div class="title">
      <span>非常感谢您提出的宝贵意见！</span>
    </div>
    <el-input type="textarea"
              placeholder="请输入建议"
              v-model="textarea"
              maxlength="100"
              show-word-limit
              rows="10">
    </el-input>
    <el-button type="primary"
               style="width: 100%"
               @click="submit"
               plain
               :loading="logining">提交</el-button>
  </div>
</template>

<script>

export default {
  name: 'suggest',
  data () {
    return {
      textarea: "",
      logining: false
    }
  },
  methods: {
    submit () {
      this.logining = true;
      this.$post("/suggest/insert", {
        createBy: JSON.parse(
          window.sessionStorage.getItem("user")
        ).name, recommendation: this.textarea
      }).then((result) => {
        this.$message({
          type: 'success',
          message: '提交成功!'
        });

        this.logining = false;
      }).catch((err) => {
        this.$message({
          type: 'info',
          message: '请填写完所有问题后提交'
        });
        this.logining = false;
      });
    }
  }
}
</script>

<style scoped>
.title,
.el-button {
  margin: 15px 0;
}
</style>