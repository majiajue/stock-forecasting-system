<template>
  <el-row>
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
  </el-row>
</template>

<script>
export default {
  name: "qs1",
  created: function () {
    this.questionnaire = [
      {
        title: "1、您投资中可耐受何种程度的风险波动",
        type: "radio",
        data: [
          { value: '1', des: "低" },
          { value: '2', des: "中" },
          { value: '3', des: "高" },
        ]
      },
      {
        title: "2、您的投资目标是",
        type: "radio",
        data: [
          { value: '1', des: "尽可能保证本金安全，不在乎收益率比较低" },
          { value: '2', des: "追求一定的收益，可以承担一定的投资风险" },
          { value: '3', des: "追求较多的收益产生，可以承担较大的投资风险" },
        ]
      },
      {
        title: "3、您可以接受的最长投资期限为",
        type: "radio",
        data: [
          { value: '1', des: "1年以内" },
          { value: '2', des: "1-3年" },
          { value: '3', des: "3-5年" },
          { value: '4', des: "5年以上" },
        ]
      },
      {
        title: "4、您想投资什么产品",
        type: "checkbox",
        data: [
          { value: '0.5', des: "存款和国债" },
          { value: '1', des: "债券保险等固定收益类" },
          { value: '2', des: "股票基金等权益类" },
          { value: '3', des: "商品及衍生品类和其他高风险产品" },
        ]
      },
      {
        title: "5.您的投资知识可描述为",
        type: "radio",
        data: [
          { value: '1', des: "有限" },
          { value: '2', des: "一般" },
          { value: '3', des: "丰富" },
        ]
      },
      {
        title: "6、您有多少年的投资经验",
        type: "radio",
        data: [
          { value: '1', des: "1年以内" },
          { value: '2', des: "1-3年" },
          { value: '3', des: "3-5年" },
          { value: '4', des: "5年以上" },
        ]
      },
      {
        title: "7、您的投资经验有哪些",
        type: "checkbox",
        data: [
          { value: '0.5', des: "出银行存储外，基本没有其他投资经验" },
          { value: '1', des: "购买过债券、保险等理财产品" },
          { value: '2', des: "参与过股票、基金等产品的交易" },
          { value: '3', des: "参与过权证、期货、期权等产品的交易" },
        ]
      },
      {
        title: "8、您每月开销最大的是",
        type: "radio",
        data: [
          { value: '1', des: "还信用卡等消费" },
          { value: '2', des: "还车贷、房贷" },
          { value: '3', des: "日常花销" },
        ]
      },
      {
        title: "9、您的收入主要来自",
        type: "radio",
        data: [
          { value: '1', des: "工资奖金、劳务报酬" },
          { value: '2', des: "生产经营所得" },
          { value: '3', des: "利息、股息、转让等金融性资产收入" },
          { value: '4', des: "出租、出售房地产等非金融收入" },
        ]
      },
      {
        title: "10、您的工作是",
        type: "radio",
        data: [
          { value: '5', des: "党政机关及事业单位" },
          { value: '4', des: "一般企业单位" },
          { value: '3', des: "蓝领" },
          { value: '0.5', des: "在校学生" },
          { value: '1.5', des: "自由职业" },
          { value: '1', des: "无固定职业" },
        ]
      },
    ];

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
  data () {
    return {
      questionnaire: [],
      answer: [],
      total_score: 0,
    };
  },
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
