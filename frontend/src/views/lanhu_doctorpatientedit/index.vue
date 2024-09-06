<template>
  <div class="page">
    <div class="nav-bar_1 flex-row">
      <img
        class="icon_1"
        referrerpolicy="no-referrer"
        src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png"
        @click="goBack"
      />
      <span class="text_1">就诊</span>
    </div>
    <div class="text-wrapper_1 flex-col">
      <span class="text_2">病例</span>
    </div>
    <div class="section_1 flex-col">
      <textarea
        class="case-input"
        placeholder="在此处填写病例"
        v-model="caseText"
      ></textarea>
    </div>
    <div class="search-wrapper">
      <img
        class="search-icon"
        referrerpolicy="no-referrer"
        src="./assets/img/MasterDDSSlicePNGeb27a623a83b9c4ef3c27833cd3fab91.png"
      />
      <input
        class="styled-input"
        placeholder="输入药品名称"
        v-model="searchText"
        @input="fetchSearchResults"
      />
      <div v-if="searchResults.length || noResultsFound" class="dropdown-results">
        <div v-if="noResultsFound" class="no-results">未搜到对应结果</div>
        <div
          v-for="result in searchResults"
          :key="result.id"
          class="result-item flex-row"
        >
          <img :src="result.image" alt="药品图片" class="drug-image" />
          <div class="result-details flex-col">
            <div class="drug-name">{{ result.name }} </div>
            <div class="drug-info">
              <span>剩余数量: {{ result.stock }}</span>
              <span class="drug-price">价格: ¥{{ result.price }}</span>
            </div>
          </div>
          <input
            type="number"
            class="quantity-input"
            v-model.number="result.selectedQuantity"
            placeholder="数量"
            min="0"
            @input="updateSelectedQuantity"
          />
        </div>
      </div>
    </div>
    <!-- 将完成按钮固定在底部 -->
    <div class="section_2 flex-col" style="position: fixed; bottom: 0; left: 0; width: 100%;">
      <button class="button_1 flex-col" @click="postdataToBack()">
        <span class="text_4">完成</span>
      </button>
    </div>
    <div class="section_3 flex-col"></div>

    <!-- 自定义弹窗 -->
    <div v-if="showAlert" class="alert-popup">
      <div class="alert-content">
        <p>{{ alertMessage }}</p>
        <button @click="closeAlert">确定</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      caseText: "", // Variable to receive the input for sending to the backend
      searchText: "", // 搜索框输入的内容
      searchResults: [], // 存储搜索结果
      noResultsFound: false, // 是否显示“未搜到对应结果”
      patientId: null, // 从路由参数中获取病人ID
      selectedMedication: null, // 用于保存选中的药品信息
      showAlert: false, // 控制弹窗显示
      alertMessage: "", // 弹窗消息内容
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    async fetchSearchResults() {
      if (this.searchText.trim() === "") {
        this.searchResults = [];
        this.noResultsFound = false;
        return;
      }

      try {
        const formData = new FormData();
        formData.append("medicationName", this.searchText);

        const response = await axios.post("/admin/searchmedi", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });

        if (response.data.code === 1 && response.data.data.medications.length > 0) {
          this.searchResults = response.data.data.medications.map((med) => ({
            ...med,
            selectedQuantity: 0, // 初始化选择数量为0
          }));
          this.noResultsFound = false;
        } else {
          this.searchResults = [];
          this.noResultsFound = true;
        }
      } catch (error) {
        console.error("Error fetching search results:", error);
        this.searchResults = [];
        this.noResultsFound = true;
      }
    },
    updateSelectedQuantity() {
      // 只允许选一个药品
      this.selectedMedication = this.searchResults.find(result => result.selectedQuantity > 0);
      if (this.selectedMedication) {
        this.searchResults.forEach(result => {
          if (result.id !== this.selectedMedication.id) {
            result.selectedQuantity = 0;
          }
        });
      }
    },
    async postdataToBack() {
      if (!this.caseText || !this.selectedMedication || this.selectedMedication.selectedQuantity === 0) {
        this.alertMessage = "请填写病例并选择药品。";
        this.showAlert = true;
        return;
      }

      const formData = new FormData();
      formData.append("diagnosis", this.caseText);
      formData.append("medicationId", this.selectedMedication.id);
      formData.append("quantity", this.selectedMedication.selectedQuantity);
      formData.append("patientId", this.patientId);

      try {
        const response = await axios.post("/doctor/payment", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });

        if (response.data.code === 1) {
          this.alertMessage = "数据提交成功";
          this.showAlert = true;
        } else {
          console.error("数据提交失败:", response.data.msg);
        }
      } catch (error) {
        console.error("数据提交失败:", error);
      }
    },
    closeAlert() {
      this.showAlert = false;
      this.$router.go(-1); // 返回上一页
    }
  },
  mounted() {
    // 获取从路由传递过来的 patientId 参数
    this.patientId = this.$route.query.patientId;
  },
};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
