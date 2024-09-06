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
      <div class="text-wrapper_1 flex-col"><span class="text_2">婴儿，小年龄儿童</span></div>
      <span class="text_3">护理方法</span>
      <div class="text-wrapper_2 flex-col">
        <span class="paragraph_1">
          一、新生儿日常护理要点
        <br />
          1. 皮肤护理：新生儿的皮肤娇嫩，需保持清洁干燥。每天进行温水洗澡，避免使用香皂或沐浴露，以免刺激皮肤。洗澡后，用柔软的毛巾轻轻拍干，不要用力擦拭。
        <br />
          2. 脐带护理：脐带未脱落前，每天用 75% 的酒精消毒脐带根部，保持脐带干燥，避免感染。一旦发现脐带红肿或有异味，应及时就医。
        <br />
          3. 眼部护理：新生儿眼部可能会有少量分泌物，用清洁的棉球或纱布蘸取温开水，从内眼角向外眼角轻轻擦拭。
        <br />
          4. 鼻腔护理：宝宝鼻腔狭窄，容易堵塞。可以用细棉签蘸取温水，轻轻旋转清洁鼻腔。
        <br />
          5. 衣物选择：为新生儿选择透气性好、柔软舒适的棉质衣物，避免穿着过多或过少，以保持适宜的体温。
        <br />
          二、新生儿健康观察与问题应对
        <br />
          1. 黄疸观察：新生儿可能出现生理性黄疸，一般在一周内自行消退。若黄疸持续加重或消退后再次出现，应及时就医。
        <br />
          2. 体温监测：新生儿体温调节能力较差，需密切监测体温。保持室内温度适宜，避免过冷或过热。
        <br />
          3. 大小便观察：注意观察新生儿大小便的次数、颜色和性状。若出现异常，如便秘、腹泻、血便等，应及时就医。
        <br />
          4. 预防接种：按照国家免疫规划，及时为新生儿接种相关疫苗，预防传染病。
        <br />
          三、科学喂养与睡眠安排策略
        <br />
          1. 母乳喂养：母乳喂养是最佳选择，能提供宝宝所需营养和免疫物质。按需哺乳，不限制时间和次数。哺乳姿势要正确，保持宝宝头和身体的直线。
        <br />
          2. 配方奶喂养：若无法母乳喂养，可选择适合新生儿的配方奶。按照说明书上的比例冲调，避免过浓或过稀。喂奶前要检查奶温，以免烫伤宝宝。
        <br />
          3. 睡眠安排：新生儿睡眠时间长，但昼夜节律尚未形成。为宝宝创造一个安静、舒适的睡眠环境，避免光线和噪音的干扰。睡眠时保持仰卧姿势，避免使用过软的卧具和枕头。
        <br />
          总之，新生儿护理需要细心和耐心。通过掌握日常护理要点、健康观察方法以及科学喂养与睡眠安排策略，新手父母可以更好地照顾宝宝，为宝宝的健康成长打下坚实基础。如有任何疑虑或问题，请及时咨询专业医生或儿科专家。
          <br />
        </span>

      </div>
    </div>

    <div class="block_2 flex-col">
      <span class="text_4">婴儿护理</span>
      <div class="box_1 flex-row justify-between">

        <div class="text-wrapper_3 flex-col"><span class="text_5">婴儿</span></div>
        <div class="text-wrapper_4 flex-col"><span class="text_6">儿童</span></div>
        <div class="text-wrapper_3 flex-col"><span class="text_5">小孩</span>
      </div>
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