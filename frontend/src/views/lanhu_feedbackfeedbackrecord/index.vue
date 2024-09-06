<template>
  <div class="page flex-col">
    <div class="group_1 flex-col">
      <div class="block_1 flex-row">
        <img
          class="label_1"
          referrerpolicy="no-referrer"
          @click="goBack"
          src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png"
        />
        <span class="text_1">反馈记录</span>
      </div>
    </div>
    <div class="list_1 flex-col">
      <div class="list-items_1 flex-col" v-for="(item, index) in loopData0" :key="index">
        <div class="text-wrapper_1 flex-row justify-between">
          <span class="text_2" v-html="item.submitTime"></span>
          <span class="text_3" v-html="item.issueType"></span>
        </div>
        <div class="text-wrapper_2">
          <span class="text_4">问题描述</span>
          <span class="text_5" v-html="item.issueDescription"></span>
        </div>
        <div class="block_2"></div>
        <span class="text_8" :style="{ color: item.statusColor }" v-html="item.status"></span>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        loopData0: [],
      };
    },
    mounted() {
      axios.get('/patient/showfeedback')
        .then(response => {
          if (response.data.code === 1) {
            this.loopData0 = response.data.data.map(item => ({
              submitTime: item.createTime,
              issueType: item.type,
              issueDescription: item.content,
              status: '已提交',
              statusColor: 'rgba(73,185,173,1)',
            }));

            // 判断loopData0是否为空
            if (this.loopData0.length === 0) {
              this.$router.push({ name: 'lanhu_feedbackfeedbackrecordempty' });
            }
          } else {
            console.error('获取数据失败', response.data.msg);
          }
        })
        .catch(error => {
          console.error('获取数据失败', error);
        });
    },
    methods: {
      goBack() {
        this.$router.go(-1);
      }
    }
  };
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
