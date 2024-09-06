<template>
  <div class="page flex-col">
    <div class="box_1 flex-col">
      <div class="block_1 flex-col">
            <!-- 带有返回功能的按钮 -->
			<button class='back-button' @click="goBack">
				<img class="img_button" src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png" />
			</button>    
        <span class='text_1'>来院导航</span>
      </div>
      <div id="container"></div>
    </div>
  </div>
</template>

<script>
import AMapLoader from '@amap/amap-jsapi-loader';

export default {
  name: "gaode",
  data() {
    return {
      map: null,
    };
  },
  methods: {
    goBack() {
      this.$router.push('/lanhu_index'); // 返回到上一页
    },
    initMap() {
      AMapLoader.load({
        key: "877eec62f6799db9a51c03458644cdc1",
        version: "2.0",
        plugins: ['AMap.Maptype', 'AMap.Scale', 'AMap.HawkEye', 'AMap.Driving'], // 确保插件包含 AMap.Driving
      })
      .then((AMap) => {
        this.map = new AMap.Map("container", {
          viewMode: "2D",
          zoom: 15,
          center: [106.29915, 29.592566],
        });
      
        // 这里调用插件
        AMap.plugin("AMap.Driving", function () {
          var driving = new AMap.Driving({
            //驾车路线规划策略，0是速度优先的策略
            policy: 0, 
            //map 指定将路线规划方案绘制到对应的 AMap.Map 对象上
            map: this.map,
            //panel 指定将结构化的路线详情数据显示的对应的 DOM 上，传入值需是 DOM 的 ID
            panel: "container", 
          });
          var points = [
            { keyword: "北京市地震局（公交站）", city: "北京" },
            { keyword: "亦庄文化园（地铁站）", city: "北京" },
          ];
          //搜索完成后，将自动绘制路线到地图上
          driving.search(points);
        });
      })
      .catch((e) => {
        console.log(e);
      });

      
        
    },
  },
  mounted() {
    // 挂载阶段调用，DOM初始化完成进行地图初始化
    this.initMap();
  },
};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />

<style scoped>
#container {
  width: 90%;
  height: 75%;
  margin: 0.75rem auto;
  border: 2px solid red;
}
</style>
