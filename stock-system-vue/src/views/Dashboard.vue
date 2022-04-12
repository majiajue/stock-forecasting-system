<template>
  <div>
    <span>股票代码：</span>
    <el-input placeholder="请输入股票代码"
              v-model="request.data"
              style="width: 200px; padding-right: 8px"
              clearable>
    </el-input>
    <el-button type="primary"
               size="medium"
               @click="getPredictData"
               :loading="logining">
      上传
      <i class="el-icon-upload el-icon--right"></i>
    </el-button>
    <div id="charts"
         v-show="this.respond!=null">
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
  width: 600px;
  height: 400px;
  margin: auto;
}
</style>

<script>
import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "Dashboard",
  components: {
    HelloWorld,
  },
  data () {
    return {
      request: {
        data: "601058.SH",
        predictDate: 30,
      },
      respond: null,
      logining: false
    };
  },
  methods: {
    getPredictData () {
      this.logining = true;
      this.$get("/data", this.request).then((result) => {
        this.respond = result.value.data;
        this.initCharts();
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
  },
};
</script>
