<template>
  <el-row v-show="this.questionnaire.length!=0">
    <div class="qs"
         v-for="(item, i) in questionnaire"
         :key="i">

      <el-card v-if="item.type==='radio'">
        <span>{{ item.title }}</span>
        <div class="ans">
          <el-radio v-for="(option, idx) in item.data"
                    :key="idx"
                    v-model="answer[i]"
                    :label="option.value">
            {{ option.des }}
          </el-radio>
        </div>
      </el-card>

      <el-card v-if="item.type==='checkbox'">
        <span>{{ item.title }}</span>
        <div class="ans">
          <el-checkbox-group v-model="answer[i]">
            {{item.data}}
            <el-checkbox v-for="(option, idx) in item.data"
                         :key="idx"
                         :label="option.value">
              {{option.des}}
            </el-checkbox>
          </el-checkbox-group>
        </div>
      </el-card>

    </div>

    <div>
      <el-button type="primary"
                 size="medium"
                 @click="submit">
        提交问卷
        <i class="el-icon-finished el-icon--right"></i>
      </el-button>
    </div>
  </el-row>
</template>

<script>
export default {
  name: "qs1",
  mounted: function () {
    this.$get("/questionnaire/get").then((result) => {
      this.questionnaire = result
    }).catch((err) => {
      console.error(err);
    });

    // 初始化问卷参数
    for (var i = 0; i < this.questionnaire.length; i++) {
      if (this.questionnaire[i]['type'] === 'radio') {
        this.answer.push(null)
        var maxInt = 0
        for (var j = 0; j < this.questionnaire[i]['data'].length; j++) {
          var temp = parseInt(this.questionnaire[i]['data'][j]['value']);
          if (temp > maxInt) {
            maxInt = temp
          }
        }
        this.total_score += temp;
      } else if (this.questionnaire[i]['type'] === 'checkbox') {
        this.questionnaire[i]['title'] += ' (多选)';
        this.answer.push([]);

        for (var j = 0; j < this.questionnaire[i]['data'].length; j++) {
          this.total_score += parseInt(this.questionnaire[i]['data'][j]['value']);
        }
      }
    }

  },
  computed: {
    user () {
      return JSON.parse(
        window.sessionStorage.getItem("user")
      )
    }
  },
  data () {
    return {
      questionnaire: [],
      answer: [],
      total_score: 0,
    };
  },
  methods: {

    submit () {
      // 弹窗
      this.$confirm('您将提交问卷回答, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.check(this.answer)) {

          var score = this.score(this.answer);
          var score_all = this.score_all(this.questionnaire);
          this.$get("/user/questionnaire", { score: score == 0 ? 0 : (score / score_all).toFixed(2), userId: this.user.id }).then((result) => {
            this.$message({
              type: 'success',
              message: '提交成功!'
            });
            var user = JSON.parse(
              window.sessionStorage.getItem("user")
            )
            user.score = score == 0 ? 0 : (score / score_all).toFixed(2);
            window.sessionStorage.setItem("user", JSON.stringify(user));
            this.$router.replace({ path: '/' }).then(
              location.reload()
            )

          }).catch((err) => {
            console.error(err);
          });
        } else {
          this.$message({
            type: 'info',
            message: '请填写完所有问题后提交'
          });
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '提交已取消'
        });
      });
    },

    score (arr) {
      var sum = 0;
      for (var i = 0; i < arr.length; i++) {
        if (Array.isArray(arr[i])) {
          sum += this.score(arr[i])
        } else {
          sum += parseInt(arr[i]);
        }
      }
      return sum;
    },

    score_all (arr) {
      var sum = 0;
      for (var i = 0; i < arr.length; i++) {
        sum += arr[i].score;
      }
      return sum;
    },

    check (arr) {
      for (var i = 0; i < arr.length; i++) {
        if (Array.isArray(arr[i])) {
          this.score(arr[i])
        } else {
          if (arr[i] == null) {
            return false
          }
        }
      }
      return true;
    }

  }
};
</script>

<style lang="scss" scoped>
.qs {
  margin: 12px 0;
}
.ans {
  margin-top: 8px;
}
</style>
