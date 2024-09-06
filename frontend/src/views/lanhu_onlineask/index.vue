
<template>
  <div class="page flex-col">
    <div class="block_1 flex-col">
      <div class="nav-bar_1 flex-row">
        <img
          @click="navigateTo('/lanhu_index')"
          class="icon_1"
          referrerpolicy="no-referrer"
          src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png"
        />
        <span class="text_1">在线问诊</span>
      </div>
      <div class="section_1 flex-row">
        <div class="image-text_1 flex-row justify-between">
          <img
            class="icon_2"
            referrerpolicy="no-referrer"
            src="./assets/img/MasterDDSSlicePNG4a26c374446493e9221af402d829ee3c.png"
          />
          <span class="text-group_1">您有2条历史消息</span>
        </div>
      </div>
    </div>
    <div class="block_2 flex-col">
      <div class="text-wrapper_2 flex-row justify-between">
        <span class="text_4">问题</span>
        <span class="text_5">{{ latestQuestion || '暂无问题' }}</span>
      </div>
      <div class="text-wrapper_3 flex-row justify-between">
        <span class="text_6">医生回答</span>
        <span class="text_7">
          {{ latestAnswer}}
        </span>
      </div>
      <div class="group_1 flex-col"></div>
    </div>
    <div class="block_3 flex-col">
      <div class="text-wrapper_5 flex-row justify-between">
        <span class="text_11">问题</span>
        <span class="text_12">{{ previousQuestion || '暂无问题'  }}</span>
      </div>
      <div class="text-wrapper_3 flex-row justify-between">
        <span class="text_6">医生回答</span>
        <span class="text_7">
          {{ previousAnswer}}
        </span>
      </div>
      <div class="section_2 flex-col"></div>
    </div>
    <div>
      <button class="button_1 flex-col" @click="navigateTo('/lanhu_onlineasksend')">
        <span class="text_15">新建消息</span>
      </button>
      <div class="box_1 flex-col"></div>
    </div>
    <div class="block_5 flex-col"></div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      latestQuestion: '',
      latestAnswer: '',
      previousQuestion: '',
      previousAnswer: ''
    };
  },
  methods: {
    async fetchQuestionsAndAnswers() {
      try {
        const response = await axios.get('/patient/showqst', {
          withCredentials: true
        });
  
        if (response.data.code === 1) {
          const { askAnswer1, askAnswer2 } = response.data.data;
  
          // 检查最新的问答内容，如果为空则显示 "暂无问题"
          this.latestQuestion = askAnswer1 ? askAnswer1.question : '暂无问题';
          this.latestAnswer = askAnswer1 ? askAnswer1.answer : '';
  
          // 检查之前的问答内容，如果为空则显示 "暂无问题"
          this.previousQuestion = askAnswer2 ? askAnswer2.question : '暂无问题';
          this.previousAnswer = askAnswer2 ? askAnswer2.answer : '';
        } else {
          console.error('获取数据失败:', response.data.msg);
        }
      } catch (error) {
        console.error('请求失败:', error);
      }
    },
    navigateTo(url) {
      if (url) {
        this.$router.push(url);
      }
    }
  },

  mounted() {
    this.fetchQuestionsAndAnswers();
  }
};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
