<template>
  <div class="page flex-col">
    <div class="nav-bar_1 flex-row">
      <img
        class="icon_1"
        referrerpolicy="no-referrer"
        src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png"
        @click="navigateToPage('lanhu_doctormessage')"
      />
      <span class="text_1">{{ Author }}</span>
    </div>
    <div class="message-list">
      <div
        v-for="message in messages"
        :key="message.id"
        class="message-item"
      >
        <img
          class="single-avatar_2"
          referrerpolicy="no-referrer"
          :src="message.img"
        />
        <br />
        <span class="text_4">{{ message.content }}</span>
      </div>
    </div>
    <!-- 留言输入框 -->
    <div class="message-input">
      <input
        v-model="newMessage"
        type="text"
        placeholder="输入消息..."
        @keydown.enter="sendMessage"
      />
      <button @click="sendMessage" class="send-button">发送</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newMessage: '',
      messages: [
        {
          id: 1,
          author: '张三',
          content: '欢迎来到校园活动发布管理平台！',
          img: require('./assets/img/MasterDDSSlicePNG19a66335348de3a73e61cd1ce84fa2e9.png'),
        },
        {
          id: 2,
          author: '张三',
          content: '你报名参加的狼人杀活动将在明天开始，请准时参加。',
          img: require('./assets/img/MasterDDSSlicePNG19a66335348de3a73e61cd1ce84fa2e9.png'),
        },
        {
          id: 3,
          author: '张三',
          content: '你报名参加的狼人杀活动将在明天开始，请准时参加。',
          img: require('./assets/img/MasterDDSSlicePNG19a66335348de3a73e61cd1ce84fa2e9.png'),
        },
        {
          id: 3,
          author: '张三',
          content: '你报名参加的狼人杀活动将在明天开始，请准时参加。',
          img: require('./assets/img/MasterDDSSlicePNG19a66335348de3a73e61cd1ce84fa2e9.png'),
        },
        {
          id: 3,
          author: '张三',
          content: '你报名参加的狼人杀活动将在明天开始，请准时参加。',
          img: require('./assets/img/MasterDDSSlicePNG19a66335348de3a73e61cd1ce84fa2e9.png'),
        },
        {
          id: 3,
          author: '张三',
          content: '你报名参加的狼人杀活动将在明天开始，请准时参加。',
          img: require('./assets/img/MasterDDSSlicePNG19a66335348de3a73e61cd1ce84fa2e9.png'),
        },
        
      ],
    };
  },
  computed: {
    Author() {
      return this.messages[0].author;
    },
  },
  methods: {
    navigateToPage(pageName) {
      this.$router.push({ name: pageName });
    },
    sendMessage() {
      const content = this.newMessage.trim();
      if (content) {
        // 添加新留言并标记为发送的消息
        this.messages.push({
          id: this.messages.length + 1,
          author: '张三',
          content: content,
          img: require('./assets/img/MasterDDSSlicePNG19a66335348de3a73e61cd1ce84fa2e9.png'),
          isSent: true, // 标记为发送的消息
        });
        // 清空输入框
        this.newMessage = '';
      }
    },
  },
};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />