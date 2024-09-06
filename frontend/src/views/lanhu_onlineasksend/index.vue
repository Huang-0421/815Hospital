<template>
  <div class="page flex-col">
    <div class="block_1 flex-row">
      <div class="box_1 flex-col">
        <div class="section_1 flex-row">
          <img
            @click="navigateTo('/lanhu_onlineask')"
            class="label_1"
            referrerpolicy="no-referrer"
            src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png"
          />
          <span class="text_1">在线问诊</span>
          <img
            @click="submitQuestion"
            class="image_1"
            referrerpolicy="no-referrer"
            src="./assets/img/回复.png"
          />
        </div>
        <span class="text_2">{{ currentTime }}</span>
		<div class="box_3 flex-row">
		  <el-input
		    type="textarea"
		    :autosize="{ minRows: 2, maxRows: 4 }"
		    placeholder="请输入您的问题..."
		    v-model="inputText"
		  ></el-input>
		</div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      constants: {},
      inputText: '',
      currentTime: '' // 添加currentTime
    };
  },
  
  methods: {
    updateTime() {
      const date = new Date();
      this.currentTime = date.toLocaleTimeString('en-GB', { hour: '2-digit', minute: '2-digit' });
    },
    navigateTo(url) {
      if (url) {
        this.$router.push(url);
      }
    },
    async submitQuestion() {
      if (!this.inputText.trim()) {
        alert("请先输入问题！");
        return;
      }

      console.log("用户问题:", this.inputText);
      
      // 创建 FormData 对象
      const formData = new FormData();
      formData.append('question', this.inputText);

      try {
        const response = await axios.post('http://192.168.1.191:8080/patient/ask', formData, {
          withCredentials: true,
        });

        // 检查返回的状态
        if (response.data.code === 1) {
          console.log("问题发送成功");
          this.inputText = ''; // 清空文本框
        } else {
          console.error("发送失败:", response.data.msg);
          alert("发送失败: " + response.data.msg);
        }
      } catch (error) {
        console.error("请求失败:", error);
        alert("请求失败，请稍后再试。");
      }
    }
  },
  
  mounted() {
    this.updateTime();
    setInterval(this.updateTime, 1000); // 每秒更新时间
  }
};
</script>

<style scoped lang="css" src="./assets/index.rem.css"></style>
