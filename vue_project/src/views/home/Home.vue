<template>
  <div class="home">
    <!-- 1. 顶部区域布局 -->
    <div class="header">
      <div class="item">
        热门医生
        <div class="num">{{ hotDoctors }}</div>
      </div>
      <div class="item">
        医生在岗人数
        <div class="num">{{ doctorsOnDuty }}</div>
      </div>
      <div class="item">
        医生在岗比例
        <div class="num">{{ doctorsOnDutyRatio }}</div>
      </div>
      <div class="item">
        今日门诊数量
        <div class="num">{{ todaysOutpatients }}</div>
      </div>
    </div>

    <!-- 2. 访问数据统计 -->
    <div class="content">
      <div class="time-info" id="box13" style="width: 580px; height: 340px">
        <div id="charts" style="width: 100%; height: 340px">
          <AgeChart></AgeChart>
        </div>
      </div>
      <div class="area" id="box1" style="width: 360px; height: 340px">
        <div id="charts" style="width: 100%; height: 340px">
          <ProductSalesEcharts></ProductSalesEcharts>
        </div>
      </div>
    </div>
    <div style="background-color: white; margin-top: 58px">
      <BookTimeLine></BookTimeLine>
    </div>
    <div class="content">
      <div class="time-info" style="width: 580px; height: 340px">
        <div style="width: 100%; height: 340px">
          <RoomCol></RoomCol>
        </div>
      </div>
      <div class="area" style="width: 360px; height: 340px">
        <div class="gender-pie-container">
          <GenderPie></GenderPie>
        </div>
      </div>
    </div>

    <div class="heatmap-container">
      <Heatmap></Heatmap>
    </div>
  </div>
</template>

<script>
import AgeChart from "./echarts/AgeChart.vue";
import ProductSalesEcharts from "./echarts/ProductSalesRatio.vue";
import Heatmap from "./echarts/test.vue";
import GenderPie from "./echarts/GenderPie.vue";
import RoomCol from "./echarts/RoomCol.vue";
import BookTimeLine from "./echarts/BookTimeLine.vue";
import axios from "axios";

export default {
  data() {
    return {
      // 将 data 改为函数，返回一个对象
      hotDoctors: 0, // 热门医生数量
      doctorsOnDuty: 0, // 医生在岗人数
      doctorsOnDutyRatio: 0, // 医生在岗比例
      todaysOutpatients: 0, // 今日门诊数量
    };
  },
  mounted() {
    console.log("Component mounted"); // 用于调试生命周期钩子
    this.fetchData(); // 在组件挂载时获取数据
  },
  components: {
    AgeChart,
    ProductSalesEcharts,
    Heatmap,
    GenderPie,
    RoomCol,
    BookTimeLine,
  },
  methods: {
    async fetchData() {
      try {
        const response = await this.$axios.get("/admin/showall"); ///admin/showall
        console.log(response); // 用于调试，检查返回的数据结构
        const data = response.data.data;

        this.hotDoctors = 20; // 热门医生数量保持为0
        this.doctorsOnDuty = data.count;
        this.doctorsOnDutyRatio = data.rate;
        console.log(this.doctorsOnDuty, this.doctorsOnDutyRatio); // 检查这些变量

        this.todaysOutpatients = 37; // 今日门诊数量保持为0
      } catch (error) {
        console.error("获取数据失败:", error);
      }
    },
  },
};
</script>

<style lang="less" scoped>
.home {
  height: 760px;
}
.header {
  display: flex;
  padding-right: 30px;
  .item {
    flex: 1;
    height: 100px;
    padding: 10px;
    background: #fff;
    border-radius: 10px;
    margin-left: 20px;
    margin-right: 20px;
    font-weight: bold;
    color: #fff;
    position: relative;
    .num {
      font-size: 22px;
      margin: 10px;
      color: #ffffff; /* 修改为黑色，以确保文本可见 */
    }
    .bottom {
      position: absolute;
      border-top: 1px solid rgb(246, 245, 245);
      padding: 10px 20px;
      bottom: 0;
      right: 0;
      left: 0;
      color: #fff;
      font-weight: normal;
    }
  }
  .item:nth-child(1) {
    background-image: linear-gradient(#df887d, #88554d);
  }
  .item:nth-child(2) {
    background-image: linear-gradient(#409eff, #2e556e);
  }
  .item:nth-child(3) {
    background-image: linear-gradient(#b54fa8, #713c7a);
  }
  .item:nth-child(4) {
    background-image: linear-gradient(#1cd2f1, #39717a);
  }
}

// 图表
.content {
  margin: 20px;
  padding-left: 0px;
  display: flex;
  height: 320px;
  .time-info {
    flex: 1.6;
    background: #fff;
    margin-right: 20px;
    padding: 10px;
  }
  .area {
    flex: 1;
    background: #fff;
    padding: 10px;
  }
}

//内容
.home-footer {
  display: flex;
  padding-left: 20px;
  margin-top: 70px;
  .box-card {
    flex: 1;
    margin-right: 30px;
    span {
      font-weight: 600;
    }
  }
}
</style>