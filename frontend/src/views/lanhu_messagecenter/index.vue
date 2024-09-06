<template>
  <div class="page flex-col">
    <div class="block_1 flex-row">
      <div class="box_1 flex-col">
        <div class="block_5 flex-col">
          <div class="block_6 flex-row">
            <div
              class="image-text_9 flex-col justify-between"
              @click="navigatorForPage('lanhu_index')"
            >
              <img
                class="label_3"
                referrerpolicy="no-referrer"
                src="./assets/img/MasterDDSSlicePNG1eaee350e2fc65b0b8af655d4ae1afaf.png"
              />
              <span class="text-group_11">首页</span>
            </div>
            <div
              class="image-text_10 flex-col justify-between"
              @click="navigatorForPage('lanhu_myclinic')"
            >
              <img
                class="label_4"
                referrerpolicy="no-referrer"
                src="./assets/img/MasterDDSSlicePNG0c2614613e9e46d496069fa035db8fa2.png"
              />
              <span class="text-group_12">我的诊室</span>
            </div>
            <div
              class="image-text_11 flex-col justify-between"
              @click="navigatorForPage('lanhu_messagecenter')"
            >
              <img
                class="label_5"
                referrerpolicy="no-referrer"
                src="./assets/img/MasterDDSSlicePNGc58e99ea2918854e17ab0309141a773e.png"
              />
              <span class="text-group_13">信息中心</span>
            </div>
            <div
              class="image-text_12 flex-col justify-between"
              @click="navigatorForPage('lanhu_personalcenter')"
            >
              <img
                class="label_6"
                referrerpolicy="no-referrer"
                src="./assets/img/MasterDDSSlicePNG915a6b73b6430954e82fdb59ccebba61.png"
              />
              <span class="text-group_14">个人中心</span>
            </div>
          </div>
        </div>
      </div>
      <div class="box_2 flex-col">
        <div class="text-wrapper_1 flex-row">
          <span class="text_1">消息</span>
        </div>
        <div
          class="section_1 flex-row justify-between"
          @click="handleServiceMessageClick"
        >
          <div class="image-wrapper">
            <img
              class="image_1"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG4e41f4022a8ae91c13bfdc7e9dd13cdb.png"
            />
            <div v-if="!serviceMessage.isRead" class="red-dot"></div>
          </div>
          <div class="box_3 flex-col justify-between">
            <div class="text-wrapper_2 flex-row justify-between">
              <span class="text_2">服务消息</span>
              <span class="text_3">{{ serviceMessage.time }}</span>
            </div>
            <span class="text_4">
              {{ !serviceMessage.isRead ? "您有一条新的预约提醒消息，点此查看详情" : truncateText(serviceMessage.content, 10) }}
            </span>
          </div>
        </div>
        <div class="section_2 flex-row">
          <div class="box_4 flex-col"></div>
        </div>
        <div
          class="section_3 flex-row"
          @click="handleSystemMessageClick"
        >
          <div class="image-wrapper">
            <img
              class="image_2"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNGa7c2d0c3390b2c0c04ad00f28bd49643.png"
            />
            <div v-if="!systemMessage.isRead" class="red-dot"></div>
          </div>
          <div class="text-group_5 flex-col justify-between">
            <span class="text_5">系统消息</span>
            <span class="text_6">
              {{ !systemMessage.isRead ? "您有一条新的系统消息，点此查看详情" : truncateText(systemMessage.content, 10) }}
            </span>
          </div>
          <span class="text_7">{{ systemMessage.time }}</span>
        </div>
        <div class="section_4 flex-row">
          <div class="group_1 flex-col"></div>
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
      serviceMessage: {
        content: "",
        time: "",
        isRead: false,
      },
      systemMessage: {
        content: "",
        time: "",
        isRead: false,
      },
    };
  },
  created() {
    this.fetchMessages();
  },
  methods: {
	  navigateTo(route) {
	        if (route === "lanhu_messagecenterservicemessages") {
	          this.serviceMessage.isRead = true;  // 标记为已读
	          this.$router.push({ path: route });
	        } else if (route === "lanhu_messagecentersystemmessages") {
	          this.systemMessage.isRead = true;  // 标记为已读
	          this.$router.push({ path: route });
	        }
          else {
	          this.$router.push({ path: route });
	        }
	      },
    navigatorForPage(pageName){
      if (this.$route.name !== pageName) {
            this.$router.push({ name: pageName });
          }
    },
    async fetchMessages() {
      try {
        const response = await axios.get('/api/messages'); // 假设后端API路径
        const { serviceMessage, systemMessage } = response.data;

        // 设置服务消息
        this.serviceMessage = serviceMessage;

        // 设置系统消息
        this.systemMessage = systemMessage;

      } catch (error) {
        console.error("Failed to fetch messages", error);
      }
    },
    truncateText(text, maxLength) {
      return text.length > maxLength ? text.substring(0, maxLength) + "..." : text;
    },
    async handleServiceMessageClick() {
      if (!this.serviceMessage.isRead) {
        this.serviceMessage.isRead = true;  // 标记为已读
        await this.updateMessageStatus(this.serviceMessage.id, true);
      }
      this.$router.push({ path: 'lanhu_messagecenterservicemessages' });
    },
    async handleSystemMessageClick() {
      if (!this.systemMessage.isRead) {
        this.systemMessage.isRead = true;  // 标记为已读
        await this.updateMessageStatus(this.systemMessage.id, true);
      }
      this.$router.push({ path: 'lanhu_messagecentersystemmessages' });
    },
    async updateMessageStatus(messageId, isRead) {
      try {
        await axios.post(`/api/messages/${messageId}/read`, { isRead });
      } catch (error) {
        console.error("Failed to update message status", error);
      }
    },
  },
};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
