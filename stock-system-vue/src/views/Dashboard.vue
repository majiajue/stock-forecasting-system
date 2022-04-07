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
               @click="getPredictData">
      上传
      <i class="el-icon-upload el-icon--right"></i>
    </el-button>
    <div id="charts"
         v-if="this.respond!=null">
      <div class="charts_in"
           id="chart"></div>
    </div>
  </div>
</template>

<style>
#charts > .charts_in {
  padding: 15px;
  width: 1200px;
  height: 800px;
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
    };
  },
  methods: {
    async getPredictData () {
      this.$post_model("/", this.request).then((res) => {
        this.respond = res.data;
        this.initCharts();
      });
      // const res=await this.$post('/',this.list)
      // console.log(res.result)
      // 601058.SH
    },
    async initCharts () {
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
    },
  },
};
</script>
