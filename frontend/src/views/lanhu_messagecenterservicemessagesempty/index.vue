<template>
  <div class="page flex-col">
    <div class="box_1 flex-col">
      <div class="box_2 flex-row">
        <img
          class="label_1" @click="goBack"
          referrerpolicy="no-referrer"
          src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png"
        />
        <span class="text_1">服务消息</span>
      </div>
    </div>
    <div class="text-wrapper_1 flex-col"><span class="text_2">当前没有消息</span></div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      constants: {}
    };
  },
  methods: {
	  goBack() {
	  	// 返回上一页，不保存信息
	  	this.$router.push({ path: 'lanhu_messagecenter' });
	  },
  }
};
</script>
<style scoped lang="css" src="./assets/index.rem.css" />