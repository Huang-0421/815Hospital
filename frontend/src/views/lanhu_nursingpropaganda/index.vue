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
		  <span class="box_1_text">护理知识</span>
        </div>
        <div class="box_2 flex-col justify-between">
          <!-- 护理知识 1 -->
          <div class="group_1 flex-row" @click="navigateToPage('lanhu_nursingpropangandainner1')">
            <div class="image-wrapper_1 flex-col">
              <img
                class="image_2"
                referrerpolicy="no-referrer"
                src="./assets/img/oldmen.png"
              />
            </div>
            <div class="text-group_1 flex-col justify-between">
              <span class="text_1">老人护理</span>
              <span class="text_2">关爱老人，人人有责</span>
            </div>
            <img
              class="thumbnail_1"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG5b6e607f15bf3044d6d9ce12c02b435f.png"
            />
          </div>
          <!-- 护理知识 2 -->
          <div class="group_2 flex-row" @click="navigateToPage('lanhu_nursingpropangandainner2')">
            <img
              class="image_3"
              referrerpolicy="no-referrer"
              src="./assets/img/pregnant.png"
            />
            <div class="text-group_2 flex-col justify-between">
              <span class="text_1">产后护理</span>
              <span class="text_2">产后须知</span>
            </div>
            <img
              class="thumbnail_2"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG5b6e607f15bf3044d6d9ce12c02b435f.png"
            />
          </div>
          <!-- 护理知识 3 -->
          <div class="group_3 flex-row" @click="navigateToPage('lanhu_nursingpropangandainner3')">
            <img
              class="image_4"
              referrerpolicy="no-referrer"
              src="./assets/img/babycare.png"
            />
            <div class="text-group_3 flex-col justify-between">
              <span class="text_1">婴儿护理</span>
              <span class="text_2">保护好自家孩子</span>
            </div>
            <img
              class="thumbnail_3"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG5b6e607f15bf3044d6d9ce12c02b435f.png"
            />
          </div>
          <!-- 护理知识 4 -->
          <div class="group_4 flex-row" @click="navigateToPage('lanhu_nursingpropangandainner4')">
            <img
              class="image_5"
              referrerpolicy="no-referrer"
              src="./assets/img/outside.png"
            />
            <div class="text-group_4 flex-col justify-between">
              <span class="text_1">外科手术</span>
              <span class="text_2">关于外科手术的术后修养，护理指南</span>
            </div>
            <img
              class="thumbnail_4"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG5b6e607f15bf3044d6d9ce12c02b435f.png"
            />
          </div>
          <!-- 护理知识 5 -->
          <div class="group_5 flex-row" @click="navigateToPage('lanhu_nursingpropangandainner5')">
            <img
              class="image_6"
              referrerpolicy="no-referrer"
              src="./assets/img/inside.png"
            />
            <div class="text-group_5 flex-col justify-between">
              <span class="text_1">内科手术</span>
              <span class="text_2">关于内科手术的术后修养，护理指南</span>
            </div>
            <img
              class="thumbnail_5"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG5b6e607f15bf3044d6d9ce12c02b435f.png"
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