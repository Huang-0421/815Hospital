<template>
  <div class="page flex-col">

  
    <div class="box_1 flex-row">
      <div class="block_1 flex-col">
        <div class="back-button-wrapper" >
          <button class="button_innernursing" @click="goBack">
            <img
              class="image_1"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNGb3ea18f7a9df8b31682748391d9a6e76.png"
            />
          </button>
		  <span class="box_1_text">健康知识</span>
        </div>
        <div class="box_2 flex-col justify-between">
          <!-- 医疗知识 1 -->
          <div class="group_1 flex-row" @click="navigateToPage('lanhu_medicalknowledgeinner1')">
            <div class="image-wrapper_1 flex-col">
              <img
                class="image_2"
                referrerpolicy="no-referrer"
                src="./assets/img/fever.png"
              />
            </div>
            <div class="text-group_1 flex-col justify-between">
              <span class="text_1">发烧</span>
              <span class="text_2">发烧发热疼痛</span>
            </div>
            <img
              class="thumbnail_1"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG9bc9f6b084958f50d4f97bb14fcee135.png"
            />
          </div>
          <!-- 医疗知识 2 -->
          <div class="group_2 flex-row" @click="navigateToPage('lanhu_medicalknowledgeinner2')">
            <img
              class="image_3"
              referrerpolicy="no-referrer"
              src="./assets/img/breathe.png"
            />
            <div class="text-group_1 flex-col justify-between">
              <span class="text_1">呼吸问题</span>
              <span class="text_2">呼吸苦难，呼吸不畅</span>
            </div>
            <img
              class="thumbnail_1"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG9bc9f6b084958f50d4f97bb14fcee135.png"
            />
          </div>
          <!-- 医疗知识 3 -->
          <div class="group_3 flex-row" @click="navigateToPage('lanhu_medicalknowledgeinner3')">
            <img
              class="image_4"
              referrerpolicy="no-referrer"
              src="./assets/img/stomach.png"
            />
            <div class="text-group_3 flex-col justify-between">
              <span class="text_1">肚子痛胃痛</span>
              <span class="text_2"> 消化不良 、 吞咽困难 、 恶心呕吐 </span>
            </div>
            <img
              class="thumbnail_1"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG9bc9f6b084958f50d4f97bb14fcee135.png"
            />
          </div>
          <!-- 医疗知识 4 -->
          <div class="group_4 flex-row" @click="navigateToPage('lanhu_medicalknowledgeinner4')">
            <img
              class="image_5"
              referrerpolicy="no-referrer"
              src="./assets/img/redcell.png"
            />
            <div class="text-group_4 flex-col justify-between">
              <span class="text_1">贫血</span>
              <span class="text_2">贫血 、 休克</span>
            </div>
            <img
              class="thumbnail_1"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG9bc9f6b084958f50d4f97bb14fcee135.png"
            />
          </div>
          <!-- 医疗知识 5 -->
          <div class="group_5 flex-row" @click="navigateToPage('lanhu_medicalknowledgeinner5')">
            <img
              class="image_6"
              referrerpolicy="no-referrer"
              src="./assets/img/paixie.png"
            />
            <div class="text-group_5 flex-col justify-between">
              <span class="text_1">排泄异常</span>
              <span class="text_2">便血 、 黄疸 、 排尿异常</span>
            </div>
            <img
              class="thumbnail_1"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG9bc9f6b084958f50d4f97bb14fcee135.png"
            />
          </div>
        </div>  
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    goBack() {
      // 实现返回上一页的逻辑
      this.$router.push('/lanhu_index');
    },
    navigateToPage(pageName) {
      // 实现页面跳转的逻辑
      // 这里的 pageName 可以根据传递的参数进行不同页面的跳转
      this.$router.push({ name: pageName });
    }
  }
};
</script>
<style scoped lang="css" src="./assets/index.rem.css" />