<template>
  <div class="page ">
    <!-- 左上角返回按钮 -->
    <div class="image-wrapper_1  back-button" >
      <button class="button_innernursing" @click="goBack">
        <img
          class="image_1"
          referrerpolicy="no-referrer"
          src="./assets/img/MasterDDSSlicePNGb3ea18f7a9df8b31682748391d9a6e76.png"
        />
      </button>
      
    </div>

    <div class="block_1 flex-col">
      <span class="text_1">护理对象</span>
      <div class="text-wrapper_1 flex-col"><span class="text_2">老年人</span></div>
      <span class="text_3">护理方法</span>
      <div class="text-wrapper_2 flex-col">
        <span class="paragraph_1">
          1. 每天清扫房间 1 次，室内应无蝇、无鼠、无蟑螂、无臭虫，保持室内空气新鲜，无异味。及时送餐送水到房间。特殊情况应给予喂水、喂饭。
        <br />
          2. 服装、床铺定期换洗，晾晒。冬、春、秋季每周 1 次，夏季经常换洗。为老人整理床铺，保持床铺的整洁。
        <br />
          3. 帮助老人起床穿衣、睡前脱衣。
        <br />
          4. 每半月换洗一次床上用品被罩、床单、枕巾（必要时随时换洗）。
        <br />
          5. 协助老人做好个人洗漱、定期理发、修剪指甲，保持个人卫生。全身洗澡春秋季每周 1 次、夏季每周 2 次、冬季每周或每半月 1 次。保持老人仪表端正，口腔护理清洁无异味。
        <br />
          6. 毛巾、洗脸盆应经常清洗，早晨起床后帮助老人洗漱，晚上帮助老人洗脚。
        <br />
          7. 提醒、帮助老人如厕，及时冲洗马桶。便器每周消毒 1 次。必要时要帮助老人排便。
        <br />
          8. 在医生和护理人员的指导下，指导老人使用拐杖、步行器、轮椅。视老人身体情况，带老人到户外适当活动。
        <br />
          9. 定期检查老人食品有无过期、腐烂，提醒老人及时处理。
        <br />
          10. I°褥疮发生率低于 5%，Ⅱ°褥疮发生率为零，入院前发生严重低蛋白血症，全身高度浮肿、癌症晚期、恶液质等患者除外。对因病情不能翻身而患褥疮的情况应有详细记录，并尽可能提供防护措施。
        <br />
          11. 服务人员 24 小时值班，实行程序化个案护理。视情况调整护理方案。
          <br />
        </span>

      </div>
    </div>

    <div class="block_2 flex-col">
      <span class="text_4">老人护理</span>
      <div class="box_1 flex-row justify-between">

        <div class="text-wrapper_3 flex-col"><span class="text_5">老人</span></div>
        <div class="text-wrapper_4 flex-col"><span class="text_6">照顾老人</span></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {

  methods: {
    goBack() {
      // 这里留出链接供你填写
      this.$router.push('/lanhu_nursingpropaganda'); 
    }
  }
};
</script>


<style scoped lang="css" src="./assets/index.rem.css"></style> <!-- 外部样式文件 -->