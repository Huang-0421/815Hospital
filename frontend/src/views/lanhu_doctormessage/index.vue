<template>
  <div class="page flex-col">
    <div class="group_1 flex-row">
      <div class="message-input">
        <input
          v-model="newMessage"
          type="text"
          placeholder="输入消息..."
          @keydown.enter="sendMessage"
        />
        <button @click="sendMessage" class="send-button">发送</button>
      </div>
      <div class="tab-bar_1 flex-row">
        <div class="tab-bar-item_1 flex-col justify-between" @click="navigateToPage('lanhu_doctorindex')">
          <img
            class="icon_1"
            referrerpolicy="no-referrer"
            src="./assets/img/MasterDDSSlicePNG93b8fa7cd3c4e4c44db16f046e89700d.png"
          />
          <span class="text_1">首页</span>
        </div>
        <div class="tab-bar-item_2 flex-col justify-between" @click="navigateToPage('lanhu_doctormessage')">
          <img
            class="icon_2"
            referrerpolicy="no-referrer"
            src="./assets/img/MasterDDSSlicePNGb2b50b7dab584ffa908193b8ee111520.png"
          />
          <span class="text_2">消息</span>
        </div>
      </div>

      <div class="message-list">
        <div
          v-for="message in bossmessages"
          :key="message.messageID"
          class="message-item"
        >
          <div class="title">
            <img
              class="single-avatar_2"
              referrerpolicy="no-referrer"
              :src="message.img"
            />
            <span class="messagename">老板</span>
          </div>
          <span class="text_4">{{ message.content }}</span>
        </div>
        <div
          v-for="message in messages"
          :key="message.messageID"
          class="message-item"
        >
          <div class="title">
            <img
              class="single-avatar_2"
              referrerpolicy="no-referrer"
              :src="message.img"
            />
            <span class="messagename">{{ message.name }}</span>
            <input type="radio" v-model="selectedMessage" :value="message" />
          </div>
          <span class="text_4">{{ message.content }}</span>
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
      newMessage: '',
      bossmessages: [],
      messages: [],
      selectedMessage: null, // 选中的消息
    };
  },
  methods: {
    navigateToPage(pageName) {
      if (this.$route.name !== pageName) {
        this.$router.push({ name: pageName });
      }
    },
    async fetchMessages() {
      try {
        const response = await axios.get('/doctor/showinhome');
        if (response.data.code === 1) {
          const data = response.data.data;

          data.forEach((item) => {
            if (item.patientName === 'boss') {
              this.bossmessages.push({
                img: item.image,
                name: '老板',
                content: item.content,
                messageID: Math.random(), // 使用随机数作为消息ID
              });
            } else {
              this.messages.push({
                img: item.image,
                name: item.patientName,
                content: item.question,
                messageID: item.askanswerId,
              });
            }
          });
        } else {
          console.error('获取消息失败:', response.data.msg);
        }
      } catch (error) {
        console.error('请求失败:', error);
      }
    },
    async sendMessage() {
      if (!this.selectedMessage) {
        alert('请先选择一个消息');
        return;
      }

      // 使用 FormData 构造表单数据
      const formData = new FormData();
      formData.append('answer', this.newMessage);
      formData.append('askanswerId', this.selectedMessage.messageID);

      try {
        const response = await axios.post('/doctor/answer', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });

        if (response.data.code === 1) {
          this.removeMessage();
        } else {
          console.error('消息发送失败:', response.data.msg);
        }
      } catch (error) {
        console.error('请求失败:', error);
      }

      this.newMessage = '';
      this.selectedMessage = null;
    },
    removeMessage() {
      if (this.selectedMessage) {
        const messageID = this.selectedMessage.messageID;

        this.messages = this.messages.filter(message => message.messageID !== messageID);
        this.selectedMessage = null;

        console.log(`消息ID为 ${messageID} 的消息已被移除`);
      } else {
        console.log('没有选中的消息需要移除');
      }
    },
  },
  mounted() {
    this.fetchMessages();
  },
};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
