<template>
  <div class="page flex-col">
    <div class="block_1 flex-col">
      <div class="group_1 flex-row">
        <img
          class="label_1"
          referrerpolicy="no-referrer"
          @click="goBack"
          src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png"
        />
        <span class="text_1">地址管理</span>
        <span class="text_2" @click="goToManagement">退出管理</span>
      </div>
    </div>
    <div class="block_2 flex-col">
      <div class="list_1 flex-col">
        <div
          class="list-items_1 flex-col"
          v-for="(item, index) in addresses"
          :key="index"
        >
          <span class="text_3" v-html="item.shortAddress"></span>
          <div class="box_1 flex-row justify-between">
            <div class="group_2 flex-col justify-between">
              <span class="text_4" v-html="item.detailedAddress"></span>
              <div class="section_1 flex-row">
                <span class="text_5" v-html="item.recipientName"></span>
                <span class="text_6" v-html="item.recipientPhone"></span>
				<div v-if="item.isDefault === 1 || item.tag" class="text-wrapper_1 flex-col">
					<span class="text_7">{{ item.isDefault === 1 ? '默认' : item.tag }}</span>
				</div>
                
              </div>
            </div>
            <!-- 静态按钮 -->
            <img
              class="label_2"
              referrerpolicy="no-referrer"
              :src="getImageUrl(item)"
            />
          </div>
          <div class="box_2 flex-row">
            <div
              class="image-text_1 flex-row justify-between"
              @click="setAsDefault(index)"
            >
              <!-- 单选框图标 -->
              <img
                class="label_3"
                referrerpolicy="no-referrer"
                :src="getSelectionIcon(item)"
              />
              <!-- 单选框文字 -->
              <span class="text-group_1">{{ actionText0 }}</span>
            </div>
            <div class="text-wrapper_2 flex-col" @click="deleteAddress(index, item.id)">
              <span class="text_8">{{ actionText1 }}</span>
            </div>
            <div class="text-wrapper_3 flex-col">
              <span class="text_9">{{ actionText2 }}</span>
            </div>
          </div>
          <div class="box_3 flex-col"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      addresses: [
		  {
		  		shortAddress: "四川省 广元市 利州区 万缘街道",
		  		detailedAddress: "白龙路与万龙路交叉口西北80米老街社区三期",
		  		recipientName: "王钟强",
		  		recipientPhone: "13350492788",
		  		tag: "公司",
		  		isDefault: 1, // 新增属性
		  	},
		  	{
		  		shortAddress: "四川省 广元市 利州区 万缘街道",
		  		detailedAddress: "白龙路与万龙路交叉口西北80米老街社区三期",
		  		recipientName: "张三",
		  		recipientPhone: "13350490000",
		  		tag: "家",
		  		isDefault: 0,
		  	},
	  ], // 初始化为空数组
      selectedIcon: "https://lanhu-dds-backend.oss-cn-beijing.aliyuncs.com/merge_image/imgs/16174af823b64780bc67cfd0f914a8d7_mergeImage.png", // 选中状态图标
      unselectedIcon: "https://lanhu-oss.lanhuapp.com/MasterDDSSlicePNG7d0f3f35df2869dd1c3a3aea04931480.png", // 未选中状态图标
      actionText0: "设为默认",
      actionText1: "删除",
      actionText2: "复制",
    };
  },
  created() {
    this.fetchAddresses();
  },
  methods: {
    goBack() {
      this.$router.push('/lanhu_personalcenter'); // 跳转到lanhu_personalcenter/index.vue
    },
    goToManagement() {
      this.$router.push('/lanhu_addressmanagement'); // 跳转到lanhu_addressmanagement/index.vue
    },
    fetchAddresses() {
      // 假设通过API获取地址列表
      this.$http.get('/api/addresses').then((response) => {
        this.addresses = response.data;
      });
    },
    setAsDefault(index) {
      this.addresses.forEach((item, i) => {
        item.isDefault = i === index; // 只允许一个默认
        if (item.isDefault) {
          item.tag = "默认";
        }
      });
      // 更新到后端（假设 API 有这个功能）
      const selectedAddress = this.addresses[index];
      this.$http.post('/api/addresses/set-default', { id: selectedAddress.id });
    },
    deleteAddress(index, id) {
      // 删除地址
      this.$http.delete(`/api/addresses/${id}`).then(() => {
        this.addresses.splice(index, 1); // 从列表中移除
      });
    },
    getImageUrl(item) {
      return item.isDefault
        ? "https://lanhu-oss.lanhuapp.com/MasterDDSSlicePNG357db931aedccd11f62a4d34841a664a.png"
        : "https://lanhu-oss.lanhuapp.com/MasterDDSSlicePNG7d0f3f35df2869dd1c3a3aea04931480.png";
    },
    getSelectionIcon(item) {
      return item.isDefault ? this.selectedIcon : this.unselectedIcon;
    },
  },
};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
