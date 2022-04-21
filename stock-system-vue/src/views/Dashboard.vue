<template>
  <div>
    <span>股票代码：</span>
    <el-input placeholder="请输入股票代码"
              v-model="request.data"
              style="width: 100px; padding-right: 8px"
              clearable>
    </el-input>
    <el-select v-model="value"
               placeholder="股票证券公司"
               style="width: 100px; padding-right: 8px">
      <el-option v-for="item in options"
                 :key="item.value"
                 :label="item.label"
                 :value="item.value">
      </el-option>
    </el-select>
    <el-button type="primary"
               size="medium"
               @click="getPredictData"
               :loading="logining">
      上传
      <i class="el-icon-upload el-icon--right"></i>
    </el-button>

    <div id="charts"
         v-show="this.respond!=null">

      <div class="risk">
        <span>您的风险类型为：</span>
        <el-tag :key="risk"
                effect="plain">
          {{ risk }}
        </el-tag>
      </div>

      <div class="risk">
        <span>系统给予的建议：</span>
        <el-tag :key="suggestion"
                type="danger"
                effect="plain">
          {{ suggestion }}
        </el-tag>
      </div>

      <div class="charts_in"
           id="chart"></div>
      <div class="charts_in"
           id="chart_turnover"></div>
      <div class="charts_in"
           id="chart_quantity"></div>
      <div class="charts_in"
           id="chart_forehead"></div>
    </div>
  </div>
</template>

<style>
#charts > .charts_in {
  padding: 15px;
  width: 560px;
  height: 400px;
  margin: auto;
  float: left;
}
.risk,
.el-button {
  margin: 15px 0;
}
</style>

<script>
import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "Dashboard",
  components: {
    HelloWorld,
  },
  created: function () {
    var score = JSON.parse(
      window.sessionStorage.getItem("user")
    )["score"]
    this.score = score;

    // 判断风险类型
    switch (true) {
      case (score < 0):
        this.risk = "请进行问卷调查";
        break;
      case (score < 0.2):
        this.risk = "保守型";
        break;
      case (score < 0.4):
        this.risk = "稳健型";
        break;
      case (score < 0.6):
        this.risk = "平衡型";
        break;
      case (score < 0.8):
        this.risk = "成长型";
        break;
      case (score < 1):
        this.risk = "进取型";
        break;
      default:
        this.risk = "未知";
        break;
    }
  },
  data () {
    return {
      request: {
        data: "601058",
        predictDate: 30,
      },
      respond: null,
      logining: false,
      risk: null,
      suggestion: null,
      score: null,

      options: [{
        value: '.SZ',
        label: '深股通'
      }, {
        value: '.SH',
        label: '沪股通'
      }, {
        value: '.HK',
        label: '港股通',
        disabled: true
      }],
      value: '.SH',
      stock: "601058",
    };
  },
  methods: {
    getPredictData () {
      this.logining = true;
      this.request.data = this.stock + this.value;
      this.$get("/data", this.request).then((result) => {
        this.respond = result.value.data;
        this.initCharts();
        this.putSuggestion();
      }).catch((err) => {
        console.error();
      });
      this.logining = false;
      // const res=await this.$post('/',this.list)
    },
    initCharts () {
      // 基于准备好的dom，初始化echarts实例
      var myChart = this.$echarts.init(
        document.getElementById("chart"),
        "light"
      );
      // 指定图表的配置项和数据
      var option = {
        tooltip: {},
        legend: {},
        xAxis: { name: "日期", type: "category" },
        yAxis: { name: "价格", scale: true },
        dataset: {
          source: this.respond,
        },
        title: {
          text: "股价",
          // x: "center",
        },
        series: [
          {
            type: "line",
            name: "开盘价",
            encode: {
              x: 0,
              y: 1,
            },
          },
          {
            type: "line",
            name: "收盘价",
            encode: {
              x: 0,
              y: 2,
            },
          },
        ],
      };
      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);

      var myChart2 = this.$echarts.init(
        document.getElementById("chart_forehead"),
        "light"
      );
      var option = {
        tooltip: {},
        legend: {},
        xAxis: { name: "日期", type: "category" },
        yAxis: { name: "元", scale: true },
        dataset: {
          source: this.respond,
        },
        title: {
          text: "涨跌额",
        },
        series: [
          {
            type: "line",
            encode: {
              x: 0,
              y: 3,
            },
          },
        ],
      };
      myChart2.setOption(option);

      var myChart3 = this.$echarts.init(
        document.getElementById("chart_turnover"),
        "light"
      );
      var option = {
        tooltip: {},
        legend: {},
        xAxis: { name: "日期", type: "category" },
        yAxis: { name: "千元", scale: true },
        dataset: {
          source: this.respond,
        },
        title: {
          text: "成交额",
        },
        series: [
          {
            type: "line",
            encode: {
              x: 0,
              y: 6,
            },
          },
        ],
      };
      myChart3.setOption(option);

      var myChart3 = this.$echarts.init(
        document.getElementById("chart_quantity"),
        "light"
      );
      var option = {
        tooltip: {},
        legend: {},
        xAxis: { name: "日期", type: "category" },
        yAxis: { name: "手", scale: true },
        dataset: {
          source: this.respond,
        },
        title: {
          text: "成交量",
        },
        series: [
          {
            type: "line",
            encode: {
              x: 0,
              y: 5,
            },
          },
        ],
      };
      myChart3.setOption(option);
    },
    putSuggestion () {
      var sum = 0;
      for (var i = 0; i < this.respond.length; i++) {
        sum += this.respond[i][3]
      }
      sum /= this.respond.length;
      var data = 1.0 * this.score * sum;
      console.log(data);

      // 判断建议类型
      switch (true) {
        case (data < -0.5):
          this.suggestion = "不应买入";
          break;
        case (data < 0):
          this.suggestion = "不建议买入";
          break;
        case (data < 0.5):
          this.suggestion = "可以尝试买入";
          break;
        case (data < 1):
          this.suggestion = "建议买入";
          break;
        default:
          this.suggestion = "未知";
          break;
      }
    },
  },
};
</script>
