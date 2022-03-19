<template>
  <div>
    <el-input
      placeholder="请输入股票代码"
      v-model="request.data"
      style="width: 200px; padding-right: 8px"
      clearable
    >
    </el-input>
    <el-button type="primary" size="medium" @click="getPredictData"
      >上传<i class="el-icon-upload el-icon--right"></i
    ></el-button>
    <el-button type="primary" size="medium" @click="initCharts"
      >test<i class="el-icon-upload el-icon--right"></i
    ></el-button>
    <div v-if="respond != null">
      {{ respond[0] }}
    </div>

    <div id="main" style="width: 600px; height: 400px"></div>
  </div>
</template>
<script>
import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "Dashboard",
  components: {
    HelloWorld,
  },
  data() {
    return {
      request: {
        data: null,
        predictDate: 10,
      },
      respond: null,
    };
  },
  methods: {
    async getPredictData() {
      this.$post("/", this.request).then((res) => {
        this.respond = res.data;
      });
      // const res=await this.$post('/',this.list)
      // console.log(res.result)
      // 601058.SH
    },
    async initCharts() {
      // 基于准备好的dom，初始化echarts实例
      var myChart = this.$echarts.init(document.getElementById("main"));

      // 指定图表的配置项和数据
      var option = {
        title: {
          text: "ECharts 入门示例",
        },
        tooltip: {},
        legend: {
          data: ["销量"],
        },
        xAxis: {
          data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"],
        },
        yAxis: {},
        series: [
          {
            name: "销量",
            type: "bar",
            data: [5, 20, 36, 10, 10, 20],
          },
        ],
      };

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    },
  },
};
</script>
